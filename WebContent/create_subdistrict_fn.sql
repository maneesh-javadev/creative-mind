-- Function: create_subdistrict_fn(integer, integer, character varying, character varying, character varying, character varying, integer, character varying, text, character varying, character varying, character varying, timestamp without time zone, timestamp without time zone, timestamp without time zone, character varying, character varying, character varying, integer)

-- DROP FUNCTION create_subdistrict_fn(integer, integer, character varying, character varying, character varying, character varying, integer, character varying, text, character varying, character varying, character varying, timestamp without time zone, timestamp without time zone, timestamp without time zone, character varying, character varying, character varying, integer);

CREATE OR REPLACE FUNCTION create_subdistrict_fn(p_district_code integer, p_userid integer, p_subdistrict_name_english character varying, p_subdistrict_name_local character varying, p_alias_english character varying, p_alias_local character varying, p_census_2011_code integer, p_sscode character varying, p_coordinates text, p_headquarter_name_english character varying, headquarter_local_name character varying, p_order_no character varying, p_order_date timestamp without time zone, p_effective_date timestamp without time zone, p_gaz_pub_date timestamp without time zone, list_of_subdistrict_full character varying, list_of_subdistrict_part character varying, list_of_villages_full character varying, p_map_attachment_code integer)
  RETURNS text AS
$BODY$
  
  DECLARE v_dlc DISTRICT.dlc%TYPE;
  DECLARE v_district_name_english DISTRICT.DISTRICT_NAME_ENGLISH%TYPE;
  DECLARE max_transaction_id ENTITY_TRANSACTIONS.TID%TYPE;
  DECLARE max_subdistrict_code SUBDISTRICT.SUBDISTRICT_CODE%TYPE;
  DECLARE max_order_code GOVERNMENT_ORDER.ORDER_CODE%TYPE;
  DECLARE max_id GOVERNMENT_ORDER_ENTITYWISE.ID%TYPE;
  DECLARE max_tlc SUBDISTRICT.TLC%TYPE;
  DECLARE SUBDISTRICT_ROW SUBDISTRICT%rowtype;
  DECLARE VILLAGE_ROW VILLAGE%rowtype;
  DECLARE max_lr_id LANDREGION_REPLACES.ID%TYPE;
  DECLARE max_lr_replaces LANDREGION_REPLACES.LR_REPLACES%TYPE;
  DECLARE max_lr_replacedby LANDREGION_REPLACEDBY.LR_REPLACEDBY%TYPE;
  DECLARE max_headquarter_code HEADQUARTERS.headquarter_code%TYPE;
  DECLARE ENTITY_REMARK TEXT;
  DECLARE V_SLC INTEGER;
  DECLARE max_lr_replacedby_part landregion_replacedby.lr_replacedby%TYPE;--by amit on 25-july-2013
  DECLARE max_lrrb_id landregion_replacedby.id%TYPE;--by amit on 25-july-2013
  DECLARE PART_SUBDIST_CNT INTEGER; --BY AMIT ON 07-AUGUST-2013
  DECLARE V_FLAG_CODE INTEGER;--BY AMIT ON 07-AUGUST-2013

  BEGIN
        ENTITY_REMARK:='Subdistrict '||p_subdistrict_name_english||' is created by taking contribution from Subdistrict ';
    	SELECT DLC,DISTRICT_NAME_ENGLISH,SLC INTO v_dlc,v_district_name_english,V_SLC FROM DISTRICT WHERE DISTRICT_CODE=p_district_code AND ISACTIVE=TRUE; --SLC ADDED BY AMIT ON 05-FEB-2013
    	
     	IF NOT FOUND THEN
    		RAISE EXCEPTION 'DISTRICT ISN''T ACTIVE OR DOES NOT EXIST';
 	END IF;
 	
 	IF (SELECT CAST(COUNT(*) AS INT) FROM SUBDISTRICT WHERE DLC = V_DLC AND isactive = TRUE AND UPPER(BTRIM(subdistrict_name_english)) =BTRIM(p_subdistrict_name_english))> 0 THEN
		RAISE EXCEPTION 'SAME NAMED SUBDISTRICT ALREADY EXISTS IN DISTRICT %',v_district_name_english;
	END IF;
	
	IF (p_district_code IS NULL OR p_userid IS NULL OR p_subdistrict_name_english IS NULL OR p_order_no IS NULL OR p_order_date IS NULL OR p_effective_date IS NULL OR list_of_subdistrict_part IS NULL OR list_of_villages_full IS NULL) THEN
	    RAISE EXCEPTION 'PLEASE PROVIDE ALL THE MANDATORY FIELDS';
	END IF;
	
	--SELECT COALESCE(MAX(tid)+1,1) INTO max_transaction_id FROM ENTITY_TRANSACTIONS;
	select nextval('entitytransactions_seq') INTO max_transaction_id;
	SELECT COALESCE(MAX(subdistrict_code)+1,1) INTO max_subdistrict_code FROM SUBDISTRICT;
	SELECT COALESCE(MAX(tlc)+1,1) INTO max_tlc FROM SUBDISTRICT;
	--SELECT COALESCE(MAX(order_code)+1,1) INTO max_order_code FROM GOVERNMENT_ORDER;
	select nextval('governmentorder_seq') INTO max_order_code;	
	--SELECT COALESCE(MAX(id)+1,1) INTO max_id FROM GOVERNMENT_ORDER_ENTITYWISE;
	SELECT nextval('governmentorderentitywise_seq') INTO max_id;
	--SELECT COALESCE(MAX(headquarter_code)+1,1) INTO max_headquarter_code FROM HEADQUARTERS;
	SELECT nextval('headquarter_seq') INTO max_headquarter_code;
	
	SELECT (CASE p_subdistrict_name_local WHEN '' THEN NULL ELSE p_subdistrict_name_local END),
	       (CASE p_alias_english WHEN '' THEN NULL ELSE p_alias_english END), 
	       (CASE p_alias_local WHEN '' THEN NULL ELSE p_alias_local END),
	       (CASE p_census_2011_code WHEN NULL THEN NULL ELSE p_census_2011_code END),
	       (CASE p_sscode WHEN '' THEN NULL ELSE p_sscode END),
	       (CASE p_coordinates WHEN ':' THEN NULL ELSE p_coordinates END),
	       (CASE p_headquarter_name_english WHEN '' THEN NULL ELSE p_headquarter_name_english END),
	       (CASE headquarter_local_name WHEN '' THEN NULL ELSE headquarter_local_name END),
	       (CASE p_gaz_pub_date WHEN null THEN NULL ELSE p_gaz_pub_date END)
	       
	       
	 INTO
	 p_subdistrict_name_local,
	 p_alias_english,
	 p_alias_local,
	 p_census_2011_code,
	 p_sscode,
	 p_coordinates,
	 p_headquarter_name_english,
	 headquarter_local_name,
	 p_gaz_pub_date;
	 --list_of_subdistrict_full;

	/* Inserting Record in Transaction Table. */
	 select (case list_of_subdistrict_full when '' then '123456789' else  list_of_subdistrict_full end) into list_of_subdistrict_full; 
	 select (case list_of_subdistrict_full when null then '123456789' else  list_of_subdistrict_full end) into list_of_subdistrict_full;
	 --code inserted by Amit on 22-jan-2013 
	 select (case list_of_subdistrict_part when '' then '123456789' else  list_of_subdistrict_part end) into list_of_subdistrict_part; 
	 select (case list_of_subdistrict_part when null then '123456789' else  list_of_subdistrict_part end) into list_of_subdistrict_part;
	 --code inserted by Amit on 22-jan-2013
	 
	 INSERT INTO 
	  entity_transactions
	   (
	    tid, 
	    operation_code, 
	    "level",
	    effective_date,
	    isactive,
	    SLC, --SLC ADDED BY AMIT ON 05-FEB-2013
	    entity_type,
	    entity_code
	   )
	 VALUES 
	    (
	    max_transaction_id, 
	    16, 
	    'T',
	    p_effective_date,
	    TRUE,
	    V_SLC, --SLC ADDED BY AMIT ON 05-FEB-2013
	    'T',
	    max_subdistrict_code
   	    );
   	    /* Inserting Record in Transaction Table. */
   	    
   	    /* Inserting Record in landregion_replaces table */
   	    -- select COALESCE(MAX(lr_replaces)+1,1), COALESCE(MAX(id)+1,1) FROM landregion_replaces INTO max_lr_replaces, max_lr_id;
   	    select COALESCE(MAX(lr_replaces)+1,1) FROM landregion_replaces INTO max_lr_replaces;
		SELECT nextval('landregionreplaces_seq') INTO max_lr_id;
   	     
   	     FOR SUBDISTRICT_ROW IN
   	      	SELECT DISTINCT * FROM SUBDISTRICT WHERE ISACTIVE=TRUE AND SUBDISTRICT_CODE IN (select CAST(regexp_split_to_table(list_of_subdistrict_full||','||list_of_subdistrict_part, E'\\,') AS INT)) LOOP
   	      	
		     INSERT INTO landregion_replaces
		     (
			 id, 
			 lr_replaces, 
			 lrlc, 
			 entity_type,
			 lrlc_version --By amit on 25-july-2013
		     )
		    VALUES 
		     (   
			 max_lr_id, 
			 max_lr_replaces, 
			 SUBDISTRICT_ROW.tlc, 
			 'T',
			 SUBDISTRICT_ROW.subdistrict_version--added by amit on 25-july-2013
		     );

		   --SELECT max_lr_id+1 INTO max_lr_id; --Commented By Amit on 22-Jan-2013
			SELECT nextval('landregionreplaces_seq') INTO max_lr_id;--By Amit on 22-Jan-2013
		   UPDATE  
		     localbody
		   SET
		    isdisturbed = TRUE
		   FROM 
		    subdistrict, 
		    lb_covered_landregion
		    
		   WHERE 
		    subdistrict.tlc = lb_covered_landregion.lrlc AND
		    lb_covered_landregion.lb_covered_region_code = localbody.lb_covered_region_code AND
		    subdistrict.isactive = TRUE AND 
		    localbody.isactive = TRUE AND 
		    lb_covered_landregion.isactive = TRUE AND 
		    lb_covered_landregion.land_region_type = 'T' AND 
		    subdistrict.subdistrict_code = SUBDISTRICT_ROW.subdistrict_code;

	    END LOOP;
		/* if (list_of_subdistrict_full='12345678' ) then 
		  list_of_subdistrict_full='';
		 end if; */
		 
		 
	     --BY AMIT ON 07-AUGUST-2013
	     V_FLAG_CODE:=67;
	     IF list_of_subdistrict_full ='123456789' THEN 
	     	SELECT COUNT(1) INTO PART_SUBDIST_CNT FROM SUBDISTRICT WHERE ISACTIVE=TRUE AND SUBDISTRICT_CODE IN (select CAST(regexp_split_to_table(list_of_subdistrict_part, E'\\,') AS INT));
	     		IF PART_SUBDIST_CNT =1 THEN 
	     			V_FLAG_CODE:=68;
	     		ELSE 
	     			V_FLAG_CODE:=67;
	     		END IF;	
	     END IF;
	     
	     --BY AMIT ON 07-AUGUST-2013		 
		 
   	    
   	    INSERT INTO 
   	     SUBDISTRICT
   	      (
   	         subdistrict_code,
	         subdistrict_version,
	         subdistrict_name_english,
	         subdistrict_name_local,
	         tlc,
	         dlc,
	         alias_english,
	         alias_local,
	         map_attachment_code,
	         census_2001_code,
	         census_2011_code,
	         sscode,
	         lr_replaces,
	         effective_date,
	         lastupdated,
	         lastupdatedby,
	         createdon,
	         createdby,
	         isactive,
	         lr_replacedby,
	         flag_code,
	         islocked,
	         transaction_id,
	         subdistrict_district_code,
	         coordinates,
  		 warningflag
  		 )
  		 VALUES
  		 (
  		  max_subdistrict_code,
  		  1,
  		  p_subdistrict_name_english,
  		  P_subdistrict_name_local,
  		  max_tlc,
  		  v_dlc,
  		  p_alias_english,
  		  p_alias_local,
  		  p_map_attachment_code,
  		  NULL,
  		  p_census_2011_code,
  		  p_sscode,
  		  max_lr_replaces,
  		  'now',
  		  'now',
  		  p_userid,
  		  'now',
  		  p_userid,
  		  true,
  		  null,
  		  --null,COMMENTED BY AMIT ON 07-AUGUST-2013
  		  V_FLAG_CODE, --ADDED BY AMIT ON 07-AUGUST-2013
  		  false,
  		  max_transaction_id,
  		  p_district_code,
  		  p_coordinates,
  		  false
  		  );
  		  
  		  --select COALESCE(MAX(lr_replacedby)+1,1), COALESCE(MAX(id)+1,1) FROM landregion_replacedby INTO max_lr_replacedby, max_lr_id;
  		  select COALESCE(MAX(lr_replacedby)+1,1) FROM landregion_replacedby INTO max_lr_replacedby;
  		  max_lr_replacedby_part:=max_lr_replacedby+1; --by amit on 25-july-2013
  		  --SELECT nextval('landregionreplacedby_seq') INTO max_lr_id; commented by amit on 25-july-2013
  		  SELECT nextval('landregionreplacedby_seq') INTO max_lrrb_id; 
  		  		  
		  INSERT INTO landregion_replacedby
		  (
		   id, 
		   lr_replacedby, 
		   lrlc, 
		   entity_type,
		   lrlc_version--added by amit kumar on 25-july-2013
		  )
		  VALUES 
		  (   
		   --max_lr_id, commented by amit on 25-july-2013
		   max_lrrb_id, --added by amit on 25-july-2013
		   max_lr_replacedby, 
		   max_tlc, 
		   'T',
		   1 --added by amit kumar on 25-july-2013
	          );
	          
	          IF p_headquarter_name_english IS NOT NULL THEN 
			  INSERT INTO HEADQUARTERS
			  (
			     headquarter_code ,
			     headquarter_version ,
			     lrlc ,
			     region_type ,
			     headquarter_name_english ,
			     headquarter_local_name ,
			     lblc ,
			     remarks ,
			     isactive 
			   )
			   VALUES 
			   (
			    max_headquarter_code,
			    1,
			    max_tlc,
			    'T',
			    p_headquarter_name_english,
			    headquarter_local_name,
			    NULL,
			    NULL,
			    TRUE
			    );
	          
	          END IF;
	          /*if list_of_subdistrict_full is not null then*/
	            if list_of_subdistrict_full !='123456789' then
	           
		     FOR SUBDISTRICT_ROW IN
			SELECT DISTINCT * FROM SUBDISTRICT WHERE ISACTIVE=TRUE AND SUBDISTRICT_CODE IN (select CAST(regexp_split_to_table(list_of_subdistrict_full, E'\\,') AS INT)) LOOP
				ENTITY_REMARK:=ENTITY_REMARK||SUBDISTRICT_ROW.subdistrict_name_english||'(Full),'; 
		        FOR VILLAGE_ROW IN 
		          SELECT DISTINCT * FROM VILLAGE WHERE ISACTIVE=TRUE AND TLC=SUBDISTRICT_ROW.TLC LOOP 
		            INSERT INTO 
			    village
			    (
			     village_code, 
			     village_version, 
			     village_name_english, 
			     village_name_local, 
			     vlc, 
			     tlc, 
			     alias_english, 
			     alias_local, 
			     census_2001_code, 
			     census_2011_code, 
			     sscode, 
			     lr_replaces, 
			     map_attachment_code, 
			     effective_date, 
			     lastupdated, 
			     lastupdatedby, 
			     createdon, 
			     createdby, 
			     village_status, 
			     isactive, 
			     lr_replacedby, 
			     flag_code, 
			     islocked, 
			     transaction_id, 
			     dlc, 
			     slc, 
			     coordinates, 
			     warningflag
			    )
			  VALUES 
 			    (
 			     VILLAGE_ROW.village_code,
 			     VILLAGE_ROW.village_version +1,
 			     VILLAGE_ROW.village_name_english,
 			     VILLAGE_ROW.village_name_local,
 			     VILLAGE_ROW.vlc,
 			     max_tlc,
 			     VILLAGE_ROW.alias_english,
 			     VILLAGE_ROW.alias_local,
 			     VILLAGE_ROW.census_2001_code,
 			     VILLAGE_ROW.census_2011_code,
 			     VILLAGE_ROW.sscode,
 			     --VILLAGE_ROW.lr_replaces, COMMENTED BY AMIT ON 07-AUGUST-2013
 			     NULL,--ADDED BY AMIT ON 07-AUGUST-2013
 			     VILLAGE_ROW.map_attachment_code,
 			     'NOW',
 			     'NOW',
 			     p_userid,
 			     VILLAGE_ROW.createdon,
 			     VILLAGE_ROW.createdby,
 			     VILLAGE_ROW.village_status,
 			     TRUE,
 			     --VILLAGE_ROW.lr_replacedby,COMMENTED BY AMIT ON 07-AUGUST-2013
 			     NULL,--ADDED BY AMIT ON 07-AUGUST-2013
 			     --VILLAGE_ROW.flag_code,COMMENTED BY AMIT ON 07-AUGUST-2013
 			     69, --ADDED BY AMIT ON 07-AUGUST-2013
 			     FALSE,
 			     max_transaction_id,
 			     VILLAGE_ROW.dlc,
 			     VILLAGE_ROW.slc,
 			     VILLAGE_ROW.coordinates,
 			     --FALSE COMMENTED BY AMIT ON 07-AUGUST-2013
 			     VILLAGE_ROW.warningflag --ADDED BY AMIT ON 07-AUGUST-2013
 			     );
 			     
 			     UPDATE VILLAGE SET ISACTIVE=FALSE,lastupdated='NOW',lastupdatedby=p_userid WHERE village_code=VILLAGE_ROW.village_code AND village_version=VILLAGE_ROW.village_version;
 			     
 			 END LOOP;
 			
 			INSERT INTO 
			 SUBDISTRICT
			 (
			 subdistrict_code,
			 subdistrict_version,
			 subdistrict_name_english,
			 subdistrict_name_local,
			 tlc,
			 dlc,
			 alias_english,
			 alias_local,
			 map_attachment_code,
			 census_2001_code,
			 census_2011_code,
			 sscode,
			 lr_replaces,
			 effective_date,
			 lastupdated,
			 lastupdatedby,
			 createdon,
			 createdby,
			 isactive,
			 lr_replacedby,
			 flag_code,
			 islocked,
			 transaction_id,
			 subdistrict_district_code,
			 coordinates,
			 warningflag
  		         )
 			 VALUES
 			 (
 			  SUBDISTRICT_ROW.subdistrict_code,
 			  SUBDISTRICT_ROW.subdistrict_version+1,
 			  SUBDISTRICT_ROW.subdistrict_name_english,
 			  SUBDISTRICT_ROW.subdistrict_name_local,
 			  SUBDISTRICT_ROW.tlc,
 			  SUBDISTRICT_ROW.dlc,
 			  SUBDISTRICT_ROW.alias_english,
 			  SUBDISTRICT_ROW.alias_local,
 			  SUBDISTRICT_ROW.map_attachment_code,
 			  SUBDISTRICT_ROW.census_2001_code,
 			  SUBDISTRICT_ROW.census_2011_code,
 			  SUBDISTRICT_ROW.sscode,
 			  NULL,
 			  'NOW',
 			  'NOW',
 			  p_userid,
 			  SUBDISTRICT_ROW.createdon,
 			  SUBDISTRICT_ROW.createdby,
 			  FALSE,
 			  --max_lr_replacedby,commented by amit on 25-july-2013
 			  NULL,--added by amit on 25-july-2013
 			  --NULL,COMMENTED BY AMIT ON 07-AUGUST-2013
 			  70,--ADDED BY AMIT ON 07-AUGUST-2013
 			  FALSE,
 			  max_transaction_id,
 			  SUBDISTRICT_ROW.subdistrict_district_code,
 			  SUBDISTRICT_ROW.coordinates,
 			  FALSE
 			  );
 			  
 			  --UPDATE SUBDISTRICT SET ISACTIVE=FALSE,lastupdated='NOW',lastupdatedby=p_userid WHERE subdistrict_code=SUBDISTRICT_ROW.subdistrict_code AND subdistrict_version=SUBDISTRICT_ROW.subdistrict_version;coomented by amit on 25-july-2013
 			  UPDATE SUBDISTRICT SET ISACTIVE=FALSE,lr_replacedby=max_lr_replacedby,lastupdated='NOW',lastupdatedby=p_userid WHERE subdistrict_code=SUBDISTRICT_ROW.subdistrict_code AND subdistrict_version=SUBDISTRICT_ROW.subdistrict_version;
 		  END LOOP;
 		END IF;	  
 			  
	          if list_of_subdistrict_part is not null then
	           
		     FOR SUBDISTRICT_ROW IN
			SELECT DISTINCT * FROM SUBDISTRICT WHERE ISACTIVE=TRUE AND SUBDISTRICT_CODE IN (select CAST(regexp_split_to_table(list_of_subdistrict_part, E'\\,') AS INT)) LOOP
			ENTITY_REMARK:=ENTITY_REMARK||SUBDISTRICT_ROW.subdistrict_name_english||'(Part),';
		        FOR VILLAGE_ROW IN 
		          SELECT DISTINCT * FROM VILLAGE WHERE ISACTIVE=TRUE AND TLC=SUBDISTRICT_ROW.TLC AND village_code IN (select CAST(regexp_split_to_table(list_of_villages_full, E'\\,') AS INT))  LOOP 
		            INSERT INTO 
			    village
			    (
			     village_code, 
			     village_version, 
			     village_name_english, 
			     village_name_local, 
			     vlc, 
			     tlc, 
			     alias_english, 
			     alias_local, 
			     census_2001_code, 
			     census_2011_code, 
			     sscode, 
			     lr_replaces, 
			     map_attachment_code, 
			     effective_date, 
			     lastupdated, 
			     lastupdatedby, 
			     createdon, 
			     createdby, 
			     village_status, 
			     isactive, 
			     lr_replacedby, 
			     flag_code, 
			     islocked, 
			     transaction_id, 
			     dlc, 
			     slc, 
			     coordinates, 
			     warningflag
			    )
			  VALUES 
 			    (
 			     VILLAGE_ROW.village_code,
 			     VILLAGE_ROW.village_version +1,
 			     VILLAGE_ROW.village_name_english,
 			     VILLAGE_ROW.village_name_local,
 			     VILLAGE_ROW.vlc,
 			     max_tlc,
 			     VILLAGE_ROW.alias_english,
 			     VILLAGE_ROW.alias_local,
 			     VILLAGE_ROW.census_2001_code,
 			     VILLAGE_ROW.census_2011_code,
 			     VILLAGE_ROW.sscode,
 			     --VILLAGE_ROW.lr_replaces,COMMENTED BY AMIT ON 07-AUGUST-2013
 			     NULL,--ADDED BY AMIT ON 07-AUGUST-2013
 			     VILLAGE_ROW.map_attachment_code,
 			     'NOW',
 			     'NOW',
 			     p_userid,
 			     VILLAGE_ROW.createdon,
 			     VILLAGE_ROW.createdby,
 			     VILLAGE_ROW.village_status,
 			     TRUE,
 			     --VILLAGE_ROW.lr_replacedby,COMMENTED BY AMIT ON 07-AUGUST-2013
 			     NULL,--ADDED BY AMIT ON 07-AUGUST-2013
 			     --VILLAGE_ROW.flag_code,COMMENTED BY AMIT ON 07-AUGUST-2013
 			     69,--ADDED BY AMIT ON 07-AUGUST-2013
 			     FALSE,
 			     max_transaction_id,
 			     VILLAGE_ROW.dlc,
 			     VILLAGE_ROW.slc,
 			     VILLAGE_ROW.coordinates,
 			     --FALSE COMMENTED BY AMIT ON 07-AUGUST-2013
 			     VILLAGE_ROW.warningflag --ADDED BY AMIT ON 07-AUGUST-2013
 			     );
 			     
 			     UPDATE VILLAGE SET ISACTIVE=FALSE,lastupdated='NOW',lastupdatedby=p_userid WHERE village_code=VILLAGE_ROW.village_code AND village_version=VILLAGE_ROW.village_version;
 			     
 			 END LOOP;
 			
			--added by amit kumar on 25-july-2013
			select nextval('landregionreplacedby_seq')  INTO max_lrrb_id;
			INSERT INTO landregion_replacedby
			(
			   id, 
			   lr_replacedby, 
			   lrlc, 
			   entity_type,
			   lrlc_version
			)
			VALUES 
			(   
			   max_lrrb_id, 
			   max_lr_replacedby_part, 
			   max_tlc, 
			   'T',
			   1
		        );
			select nextval('landregionreplacedby_seq')  INTO max_lrrb_id;
			INSERT INTO landregion_replacedby
			(
			   id, 
			   lr_replacedby, 
			   lrlc, 
			   entity_type,
			   lrlc_version
			)
			VALUES 
			(   
			   max_lrrb_id, 
			   max_lr_replacedby_part, 
			   SUBDISTRICT_ROW.tlc, 
			   'T',
			   SUBDISTRICT_ROW.subdistrict_version+1
		        );
 			--added by amit kumar on 25-july-2013
 			
 			INSERT INTO 
			 SUBDISTRICT
			 (
			 subdistrict_code,
			 subdistrict_version,
			 subdistrict_name_english,
			 subdistrict_name_local,
			 tlc,
			 dlc,
			 alias_english,
			 alias_local,
			 map_attachment_code,
			 census_2001_code,
			 census_2011_code,
			 sscode,
			 lr_replaces,
			 effective_date,
			 lastupdated,
			 lastupdatedby,
			 createdon,
			 createdby,
			 isactive,
			 lr_replacedby,
			 flag_code,
			 islocked,
			 transaction_id,
			 subdistrict_district_code,
			 coordinates,
			 warningflag
  		         )
 			 VALUES
 			 (
 			  SUBDISTRICT_ROW.subdistrict_code,
 			  SUBDISTRICT_ROW.subdistrict_version+1,
 			  SUBDISTRICT_ROW.subdistrict_name_english,
 			  SUBDISTRICT_ROW.subdistrict_name_local,
 			  SUBDISTRICT_ROW.tlc,
 			  SUBDISTRICT_ROW.dlc,
 			  SUBDISTRICT_ROW.alias_english,
 			  SUBDISTRICT_ROW.alias_local,
 			  SUBDISTRICT_ROW.map_attachment_code,
 			  SUBDISTRICT_ROW.census_2001_code,
 			  SUBDISTRICT_ROW.census_2011_code,
 			  SUBDISTRICT_ROW.sscode,
 			  NULL,
 			  'NOW',
 			  'NOW',
 			  p_userid,
 			  SUBDISTRICT_ROW.createdon,
 			  SUBDISTRICT_ROW.createdby,
 			  TRUE,
 			  NULL,--added by amit on 25-july-2013
 			  --max_lr_replacedby, commented by amit on 25-july-2013
 			  --NULL, COMMENTED BY AMIT ON 07-AUGUST-2013
 			  71,--ADDED BY AMIT ON 07-AUGUST-2013
 			  FALSE,
 			  max_transaction_id,
 			  SUBDISTRICT_ROW.subdistrict_district_code,
 			  SUBDISTRICT_ROW.coordinates,
 			  --FALSE --commented by amit for setting warnning flag on 09-apr-2013
 			  (CASE WHEN ((SUBDISTRICT_ROW.coordinates IS NOT NULL) OR (SUBDISTRICT_ROW.map_attachment_code IS NOT NULL)) THEN TRUE ELSE FALSE END) --by amit for setting warnning flag on 09-apr-2013
 			  );
 			  
 			  --UPDATE SUBDISTRICT SET ISACTIVE=FALSE,lastupdated='NOW',lastupdatedby=p_userid WHERE subdistrict_code=SUBDISTRICT_ROW.subdistrict_code AND subdistrict_version=SUBDISTRICT_ROW.subdistrict_version; coomented by amit on 25-july-2013
 			  UPDATE SUBDISTRICT SET ISACTIVE=FALSE,lr_replacedby=max_lr_replacedby_part,lastupdated='NOW',lastupdatedby=p_userid WHERE subdistrict_code=SUBDISTRICT_ROW.subdistrict_code AND subdistrict_version=SUBDISTRICT_ROW.subdistrict_version; --added by amit kumar on 25-july-2013
 			  max_lr_replacedby_part:=max_lr_replacedby_part+1;--added by amit kumar on 25-july-2013
 		  END LOOP;
 		END IF;	
	 INSERT INTO 
	  government_order
	   (
	    order_code, 
	    order_no, 
	    order_date, 
	    effective_date, 
	    gaz_pub_date, 
	    issued_by, 
	    order_path, 
	     xml_order_path, 
	    xml_db_path, 
	    user_id, 
	    description, 
	    "level", 
	    status, 
	    template_code, 
	    createdby, 
	    createdon, 
	    lastupdated, 
	    lastupdatedby
	    )
	 VALUES 
	   (
	    max_order_code, 
	    p_order_no, 
	    p_order_date, 
	    p_effective_date, 
	    p_gaz_pub_date,
	    'GOVERNER',
	    NULL,  
	    null, 
	    null, 
	    p_userid, 
	    null, 
	    null, 
	    'A', 
	     null, 
	    p_userid, 
	    'now', 
	    'now', 
	    p_userid
	   );

	 INSERT INTO 
	  government_order_entitywise
	   (
	    id, 
	    order_code, 
	    entity_code, 
	    entity_version, 
	     entity_type
	   )
	 VALUES 
	   (
	    max_id, 
	    max_order_code, 
	    max_subdistrict_code, 
	    1, 
	    'T'
	   ); 			
           ENTITY_REMARK:=ENTITY_REMARK||'on date '||p_effective_date||'.';
           
           update entity_transactions set description=ENTITY_REMARK where tid=max_transaction_id;
    
  /*RETURN max_subdistrict_code;*/
    RETURN ((SELECT CAST(max_subdistrict_code AS TEXT))||','||(SELECT CAST(max_order_code AS TEXT)));
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION create_subdistrict_fn(integer, integer, character varying, character varying, character varying, character varying, integer, character varying, text, character varying, character varying, character varying, timestamp without time zone, timestamp without time zone, timestamp without time zone, character varying, character varying, character varying, integer)
  OWNER TO postgres;
