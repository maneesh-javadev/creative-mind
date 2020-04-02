<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdAdminDepatmentDwr.js'></script>

<script type='text/javascript'>

	 /**
	  * The {@code ready} initialized once page started excuting 
	  * and invoke all on load events.
	  */ 
	 
	 $(document).ready(function() {	
		 
		$("#ministryId" ).change(function() {			
			getOrganisationListForCenterLevel($(this).val());
		});
		/* $('#btnFormActionSave').attr("disabled",false); */
		$("#btnAddOfficeDetail").click(function() {			
			var rowCount = $('.data_grid tr').length-1;			
			var enterNoOfRows=parseInt($("#noOfRow").val());	
			if(isNaN(enterNoOfRows)) 
				{
					enterNoOfRows=1;
				}
			if((rowCount+enterNoOfRows)>100){
				var rowLeft=100-rowCount;
				$("#noOfRowErr").text("Number of rows should not more than "+rowLeft);		
				return false;
			}
			buildOfficeDetail();
			$("#noOfRow").val("");
		});	
		
		$('[id^=btnRemoveOfficeDetail]').click(function() {
			$(this).closest('.rows').remove();
		});
		
		$("#noOfRow").on('keyup', function(e){
			$("#noOfRowErr").text("");
		}).numeric({ decimal: false, negative: false }, function() {
			this.value = ""; this.focus(); 
		});
		
		$("#getdata" ).click(function() {
			 var orgElement=$("#orgCombo");
			 $("#orgCode").text("");	
			
			 $("[name=departmentName]").closest('.row').remove();
			 $("[name=departmentNameEnglish]").closest('.rows').remove();
			 if (!(jQuery.type( $(("#ministryId")) ) === "undefined")) {
				 if(!validateOrgCode($("#ministryId"))){						
					 $("#ministrycode").text("It is mandatory to Select a Ministry"); 
				 }
			} 
			if (!(jQuery.type( orgElement ) === "undefined")) {
				 if(validateOrgCode(orgElement)){							
						getSaveOrganisationList($(orgElement).val());
						
						$("#addSubOffyc").show();
						$("#buttonFormAction").show();
						
				 }else{
					 $("#orgCode").text("It is mandatory to Select a Oragnization/Department"); 
				 }
			} 
			
		});
		
		$("#orgCombo").change(function(){
			$("#addSubOffyc").hide(); 
			$("#buttonFormAction").hide();
			$("#noOfRow").val("");
			$("#noOfRowErr").text("");
		});
		
		$('[name=departmentName]').change(function() {			
			 var modifyOfficeArray=new Array();
			if(modifyOfficeNameMap.has($(this).attr("id"))){
				modifyOfficeNameMap.delete($(this).attr("id"));
			}
			modifyOfficeNameMap.set($(this).attr("id"),$(this).val());				
			modifyOfficeNameMap.forEach(function(value, key) {
				modifyOfficeArray.push(key+"_"+value);		
				}, modifyOfficeNameMap); 			
			$( "[id=updatedSubOfficeList]" ).val(modifyOfficeArray);
			
		});
		
	
		 $('#btnFormActionClr').click(function() {
			 $("#addSubOffyc").hide(); 
			 $("#buttonFormAction").hide();				 
			 $(".rows").remove();
			 $(".row").remove();
			 $('#orgCombo').val("").attr("selected","selected");
			 if (!(jQuery.type( $(("#ministryId")) ) === "undefined")) {
				 $('#ministryId').val("").attr("selected","selected");
				} 
			 $('#noOfRow').val('');
		 });
		  
		 $("INPUT[name^=departmentName]").keypress(function(event){
			 /* regex modified by pooja on 03-07-2015 */
				var regex = new RegExp("^[a-zA-Z\\/\\-\\.\\(\\)\\s]+$");
				 if(!event)event = window.event;		
				 var n = event.keyCode;
			        if (!((n == 8)              // backspace
			        || (n == 46)                // delete
			        || (n == 9)					//tab
			        || (n >= 35 && n <= 40)     // arrow keys/home/end			       
			        || (n >= 112 && n <=122)  //f1 to f12
			        )
			        ) {	
			    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);	
			    
			    if (!regex.test(key) ) {
			       event.preventDefault();
			       return false;
			    }
			        }
			});
		 
		 if('${errFlag}'!=0){
			 $("#addSubOffyc").show(); 
			 $("#buttonFormAction").show();
		 }
		 
		 $('#btnFormActionSave').click(function() {
			
			 var isValid = true;	  
	            $(".blankoffycname").remove();
	             if($('.data_grid tr').length-1==0){
	            	isValid=false;
	            	customAlert("Add Atleast One Sub Office");
	            }
	             
	            else{
			 	        $('.data_grid tr td input').each(function() {       	
			 	            if ($.trim($(this).val()) == '') {       
			 	                isValid = false;       	
			 	                $(this).after("<div class=blankoffycname><b class=boldCharacter><font color=red>Enter Sub Office Name</font></b></div>");   
			 	            }
  	       					 });    
	           		 }
	             
  	     		   if (isValid == false)
  	     			   {
  	     				 $('#btnFormActionSave').attr("disabled",false); 
  	     					return false; 
  	     			   }
  	     		   else{
  	     			$("#selectdOrgName").val($("#orgCombo :selected").text());
  	     			 $( "#organisationunit" ).submit();
  	     			//$('#btnFormActionSave').attr("disabled",true);
  	     			
  	     		   }
  	        	 	
  	     		  
  	        	return false;
		 });
		 
		
		
	});
	  
	  

	/**
	 * The {@code getOrganisationListForCenterLevel} used create select box options at 	
	 *Oragnaisation dropdown box	 
	 */
	var getOrganisationListForCenterLevel = function (ministryCode) {
		var orgCombo = $( "SELECT[id=orgCombo]" );
		$(orgCombo).find("option").remove();
		 lgdDwrOrganizationService.getDepartmentListByMinistry(ministryCode, {
			callback : function(result){
				var option = $("<option />");
				var optionText = "Select";	
				option.val("").text(optionText);
				orgCombo.append(option);
				jQuery.each(result, function(index, obj) {
					 option = $("<option />");
					 optionText = obj.orgName;					
					option.val(obj.orgCode).text(optionText);
					orgCombo.append(option);
				});
			},
			async : true
		});
	};
	
	/**
	 * The {@code getSaveOrganisationList} used create select box options at 	
	 *save data	 
	 */
	 var modifyOfficeNameMap=new Map();
	var getSaveOrganisationList = function (orgDeptCode) {
		 var stateCode='${stateCode}';
		 $("[name=departmentName]").closest('.row').remove();
		 lgdAdminDepatmentDwr.getOrganizationUnitList(orgDeptCode,stateCode, {
			callback : function(result){
				jQuery.each(result, function(index, obj) {					
					 var template = '<tr class=row>' 
						 + '<td><input type="text" name="departmentName" id="'+obj.orgUnitCode+'"  maxlength="198" value="'+obj.orgUnitName+'" style="width: 700px;"/></td><td></td>'			  
					     + '</tr>'; 
					$("#tbodyOfficeDetail").before(template);
					 $('#'+obj.orgUnitCode).change(function() {
							 var modifyOfficeArray=new Array();
							if(modifyOfficeNameMap.has($(this).attr("id"))){
								modifyOfficeNameMap.delete($(this).attr("id"));
							}
							modifyOfficeNameMap.set($(this).attr("id"),$(this).val());				
							modifyOfficeNameMap.forEach(function(value, key) {
								modifyOfficeArray.push(key+"_"+value);		
								}, modifyOfficeNameMap); 
							
							$( "[id=updatedSubOfficeList]" ).val(modifyOfficeArray);	
							
						});
					 $('#'+obj.orgUnitCode).keypress(function(event) {
						 /* regex modified by pooja on 03-07-2015 */
						 var regex = new RegExp("^[a-zA-Z\\/\\-\\.\\(\\)\\s]+$");	
						 if(!event)event = window.event;		
						 var n = event.keyCode;
					        if (!((n == 8)              // backspace
					        || (n == 46)                // delete
					        || (n == 9)					//tab
					        || (n >= 35 && n <= 40)     // arrow keys/home/end			       
					        || (n >= 112 && n <=122)  //f1 to f12
					        )
					        ) {		
						    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);		 
						    if (!regex.test(key) ) {
						       event.preventDefault();
						       return false;
						    }
					        }
					});
				});
			},
			async : true
		});
	};
	
	/**
	 * The {@code buildOfficeDetail} function used to create dynamic  
	 * row elements to store office name and local name.
	 */
	 var counter = 1;
	var buildOfficeDetail = function() {
		
		var noOfRows=$("#noOfRow" ).val();
		if(noOfRows==''){
			noOfRows=1;
		}		
		for(var i=1;i<=noOfRows;i++){
			 var template = '<tr class=rows>' 
				 + '<td><input type="text" class="form-control" name="departmentNameEnglish" id="officeName' + counter + '"  maxlength="198" style="width: 700px;"/></td>'				  
			     +'<td width="7%" style="text-align: center;"><a href="#"  id="removerow_' + counter + '"><img src="images/delete.png" width="18" height="19" border="0" /></a></td>'					   
			     + '</tr>'; 
			 $("#tbodyOfficeDetail").append(template);
			 $('#removerow_'+counter).click(function() {
				$(this).closest('.rows').remove();				
			});
			
			 
			$("INPUT[name=departmentNameEnglish]").keypress(function(event){
				if(!event)event = window.event;	
				/* regex modified by pooja on 03-07-2015 */
				var regex = new RegExp("^[a-zA-Z\\/\\-\\.\\(\\)\\s]+$");	
			     var n = event.keyCode;
			        if (!((n == 8)              // backspace
			        || (n == 46)                // delete
			        || (n == 9)					//tab
			        || (n >= 35 && n <= 40)     // arrow keys/home/end			       
			        || (n >= 112 && n <=122)  //f1 to f12
			        )
			        ) {			       
				 var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);	
				    if (!regex.test(key) ) {
				       event.preventDefault();
				       return false;
				    }
			        }
				}); 
		
			counter++;
		}
	};

		
	/**
	 * The {@code validateOrgCode} function used to check for valid  
	 * Organization Code
	 */
	 var validateOrgCode = function (Object){
		if($_checkEmptyObject($( Object ).val())){
				 
			 return false;
		}
		return true; 
	}; 
	var $_checkEmptyObject = function(obj) {
		if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
			return true;
		}
		return false;
	};
	
</script>