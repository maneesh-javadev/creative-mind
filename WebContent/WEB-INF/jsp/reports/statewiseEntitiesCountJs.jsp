<style>
/* Center the loader */
#loader {
  position: absolute;
  left: 50%;
  top: 50%;
  z-index: 1;
  width: 150px;
  height: 150px;
  margin: -75px 0 0 -75px;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

/* Add animation to "page content" */
.animate-bottom {
  position: relative;
  -webkit-animation-name: animatebottom;
  -webkit-animation-duration: 1s;
  animation-name: animatebottom;
  animation-duration: 1s
}

@-webkit-keyframes animatebottom {
  from { bottom:-100px; opacity:0 } 
  to { bottom:0px; opacity:1 }
}

@keyframes animatebottom { 
  from{ bottom:-100px; opacity:0 } 
  to{ bottom:0; opacity:1 }
}
</style>
<script>

var parentArrMap=new Map();
var parentNameArrMap=new Map();
var pnameMap=new Map();
var lbbackurl=new Map();

showHideDiv=function(parent_type,parent_code,category,parent_entity_name,level){
	var table = $('#mainTable').DataTable();
	if(parent_type=='C'){
		$("#mainDevx").show();
		 $("#dynamicDIV").empty();
		  parentArrMap=new Map();
		  parentNameArrMap=new Map();
		  pnameMap=new Map();
		  lbbackurl=new Map();
		  lbbackurl.set(parent_type,("showHideDiv('"+parent_type+"',null,null,null,null"));
		  table.fixedHeader.enable();
		  document.getElementById("mainTable").focus();
		 
	}else{
		$("#mainDevx").hide();
		table.fixedHeader.disable();
		pnameMap.set(parent_type,parent_entity_name);
		parentArrMap.set(parent_type,parent_type+":"+parent_code+":"+category);
		showPatentwiseChildDetail(parent_type,parent_code,category,parent_entity_name,level);
		document.getElementById("dynamicDIV").focus();
		
		
	}
};


showPatentwiseChildDetail=function(parent_type,parent_code,category,parent_entity_name,level){
	$("#loader").show();
	dwrReportService.getParentwiseChildDetils(parent_type,parseInt(parent_code),category,level, {
		callback : function( result ) {
			$("#loader").hide();
			
			if(category=='L'){
			
						
				if(parent_type=='S'){
					parentNameArrMap.set(parent_type,parent_entity_name);
					createDistrictDetails(result,parent_entity_name);
				}else if(parent_type=='D'){
					parentNameArrMap.set(parent_type,parent_entity_name+":"+parentNameArrMap.get('S'));
					createSubdistrictDetails(result,parent_type,parent_code,category);
				}else if(parent_type=='T' ){
					parentNameArrMap.set(parent_type,parent_entity_name+":"+parentNameArrMap.get('S')+":"+parentNameArrMap.get('D'));
					createVillageDetails(result,parent_type,parent_code,category);
				}
			
			
			}else if(category=='P'){
			
					if(parent_type=='S'  && level=='D'){
						parentNameArrMap.set(parent_type,parent_entity_name);
						lbbackurl.set(parent_type,("showHideDiv('"+parent_type+"',"+parent_code+",'"+category+"','"+parent_entity_name+"','"+level+"')"));
						createDistrictPanchayatDetails(result,parent_entity_name);
						
					}else if((parent_type=='D' ||parent_type=='S') && level=='I'){
						if(parent_type=='S'){
							parentNameArrMap.set(parent_type,parent_entity_name);
						}else if(parent_type=='D'){
							parentNameArrMap.set(parent_type,parent_entity_name+":"+parentNameArrMap.get('S'));
						}
						
						lbbackurl.set(parent_type,("showHideDiv('"+parent_type+"',"+parent_code+",'"+category+"','"+parent_entity_name+"','"+level+"')"));
						createIntermediatePanchayatDetails(result,parent_type,parent_code,category);
					}
					else if(parent_type=='T' && level=='V'){
						lbbackurl.set(parent_type,("showHideDiv('"+parent_type+"',"+parent_code+",'"+category+"','"+parent_entity_name+"','"+level+"')"));
							if (lbbackurl.has('D')){
								parentNameArrMap.set(parent_type,parent_entity_name+":"+parentNameArrMap.get('S')+":"+parentNameArrMap.get('D'));
							}else if (lbbackurl.has('S')){
								parentNameArrMap.set(parent_type,parent_entity_name+":"+parentNameArrMap.get('S'));
							}
						
						createVillagePanchayatDetails(result,parent_type,parent_code,category);
					}
			}else if(category=='U'){
				
				parentNameArrMap.set(parent_type,parent_entity_name);
				createUrbanDetails(result,parent_type,parent_code,category);
				
			}
            else if(category=='W'){
				parentNameArrMap.set(parent_type,parent_entity_name);

            	if(parent_type ='U')
            		{
					lbbackurl.set(parent_type,("showHideDiv('"+parent_type+"',"+parent_code+",'"+category+"','"+parent_entity_name+"','"+level+"')"));

            		}

				createWardDetails(result,parent_type,parent_code,category);
				
				
				
			}
			
			
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
};


createDistrictDetails=function(data,parent_entity_name){
	 var total_no_of_tlbs =0;
	 var total_no_of_villages= 0;
	 $("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 
	 var parentNameArr=parentNameArrMap.get("S");
	 
	  var h3Template=$("<h4/>");
	 h3Template.html("<center>Districts of "+parentNameArr+" State</center>");
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>District Code </b>','right'));
	 trTemplate.append(createTD('<b>District Name(In English) </b>','left'));
	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	 trTemplate.append(createTD('<b>No Of Sub-Districts </b>','right'));
	 trTemplate.append(createTD('<b>No Of Villages </b>','right'));
	
	 tableTemplate.append(trTemplate);
	
	 
	 
	 jQuery.each(data, function(index, obj) {
		
		
		
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		trTemplate.append(createFNTD(obj.noOfTlc,"showHideDiv('D',"+obj.entityCode+",'L','"+obj.entityNameEnglish+"')","right"));
		trTemplate.append(createTD(obj.noOfVlc,'right'));
		tableTemplate.append(trTemplate);	
		
		total_no_of_tlbs = total_no_of_tlbs+ obj.noOfTlc;
		total_no_of_villages =total_no_of_villages + obj.noOfVlc ;
	});
	 	trTemplate = $("<TR/>");
	 	/* trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right')); */
		trTemplate.append('<td colspan="5" style="text-align:center;font-weight:bold;margin:0 0 0 0;padding:20px 0 11px 0;">Total</td>');
		trTemplate.append(createTD('<b>'+total_no_of_tlbs+'</b>','right'));
		trTemplate.append(createTD('<b>'+total_no_of_villages+'</b>','right'));
		tableTemplate.append(trTemplate);
		
	 
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	$( inputTemplate ).attr("onclick", "showHideDiv('C',null,null,null,null)");
	divTemplate.append(inputTemplate);
	/*  $('#dynamicExample').DataTable( {
	    	
	    	"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"aoColumns":[null,null,null,null,null,{"bSortable": false}, null],
	    	searching: false,
	   		paging: false,
	    	ordering: false,
	        info:     false,
	    	dom: 'Bfrtip',
	    	 buttons: [
	    		 		
	    		 
	    		 		{
	    		 			 title: 'State Wise administrative unit information',
	    	        	     extend: 'pdfHtml5',
	    	                 orientation: 'landscape',
	    	                 pageSize: 'LEGAL'
	    	               
	    	            },       
	    	           
	    	            {
	    	                extend: 'excel',
	    	               
	    	            },
	    	            {
	    	                extend: 'print',
	    	                
	    	            }
	    	            
	    	 
	    	]
	    } );
	
	
	$("#dynamicExample").dataTable(); */
	
	
};


createSubdistrictDetails=function(data){
	 var total_no_of_villages= 0;
	 $("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 var parentNameArr=parentNameArrMap.get("D").split(":");
	 
	  var h3Template=$("<h4/>");
	 h3Template.html("<center>Sub-Districts of "+parentNameArr[0]+" (District),"+parentNameArr[1]+" State</center>");
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>Sub-District Code </b>','right'));
	 trTemplate.append(createTD('<b>Sub-District Name(In English) </b>','left'));
	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	 trTemplate.append(createTD('<b>No Of Villages </b>','right'));
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		trTemplate.append(createFNTD(obj.noOfVlc,"showHideDiv('T',"+obj.entityCode+",'L','"+obj.entityNameEnglish+"')","right"));
		total_no_of_villages =total_no_of_villages + obj.noOfVlc 
		
		
		
		
		
		tableTemplate.append(trTemplate);		
	});
	 trTemplate = $("<TR/>");
	 	/* trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right')); */
		trTemplate.append('<td colspan="5" style="text-align:center;font-weight:bold;margin:0 0 0 0;padding:20px 0 11px 0;">Total</td>');
		
		trTemplate.append(createTD('<b>'+total_no_of_villages+'</b>','right'));
		tableTemplate.append(trTemplate);
		
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	var parentArr=parentArrMap.get("S").split(":");
	$( inputTemplate ).attr("onclick", "showHideDiv('"+parentArr[0]+"',"+parentArr[1]+",'"+parentArr[2]+"','"+pnameMap.get('S')+"')");
	divTemplate.append(inputTemplate);
	 
};

createVillageDetails=function(data,parent_type,parent_code,category){
	 $("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 var parentNameArr=parentNameArrMap.get("T").split(":");
	 
	  var h3Template=$("<h4/>");
	 h3Template.html("<center>Villages of "+parentNameArr[0]+" (Sub-District),"+parentNameArr[2]+" (District),"+parentNameArr[1]+" State</center>");
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>Village Code </b>','right'));
	 trTemplate.append(createTD('<b>Village Name (In English) </b>','left'));
	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		tableTemplate.append(trTemplate);		
	});
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	var parentArr=parentArrMap.get("D").split(":");
	$( inputTemplate ).attr("onclick", "showHideDiv('"+parentArr[0]+"',"+parentArr[1]+",'"+parentArr[2]+"','"+pnameMap.get('S')+"')");
	divTemplate.append(inputTemplate);
	 
};

createDistrictPanchayatDetails=function(data,parent_entity_name){
	var  total_no_of_bps = 0;
	var total_no_of_vpsn = 0;
	 $("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 
	 
	 var parentNameArr=parentNameArrMap.get("S");
	 
	  var h3Template=$("<h4/>");
	 h3Template.html("<center>District Panchayat of "+parentNameArr+" State</center>");
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>LGD Code </b>','right'));
	 trTemplate.append(createTD('<b>District Panchayat Name(In English) </b>','left'));
	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	 trTemplate.append(createTD('<b>No. Of Intermediate Panchayats </b>','right'));
	 trTemplate.append(createTD('<b>No. Of Village Panchayats </b>','right'));
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		trTemplate.append(createFNTD(obj.noOfTlc,"showHideDiv('D',"+obj.entityCode+",'P','"+obj.entityNameEnglish+"','I')","right"));
		if(obj.noOfTlc==null){
		trTemplate.append(createFNTD(obj.noOfVlc,"showHideDiv('T',"+obj.entityCode+",'P','"+obj.entityNameEnglish+"','V')","right"));
		}else{
		trTemplate.append(createTD(obj.noOfVlc,'right'));
		}
		total_no_of_bps =total_no_of_bps + obj.noOfTlc;
		total_no_of_vpsn =total_no_of_vpsn +obj.noOfVlc ;
		tableTemplate.append(trTemplate);		
	});
	 trTemplate = $("<TR/>");
	 	/* trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right')); */
		trTemplate.append('<td colspan="5" style="text-align:center;font-weight:bold;margin:0 0 0 0;padding:20px 0 11px 0;">Total</td>');
		trTemplate.append(createTD('<b>'+total_no_of_bps+'</b>','right'));
		trTemplate.append(createTD('<b>'+total_no_of_vpsn+'</b>','right'));
		tableTemplate.append(trTemplate);
		
	
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	$( inputTemplate ).attr("onclick", "showHideDiv('C',null,null,null,null)");
	divTemplate.append(inputTemplate);
	
	 
};

createIntermediatePanchayatDetails=function(data,parent_type,parent_code,category){
	 var total_no_of_vpsn=0;
	$("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 var h3Template=$("<h4/>");
	 if(parent_type=='S'){
		 var parentNameArr=parentNameArrMap.get("S");
		 h3Template.html("<center>Inertmediate Panchyat of "+parentNameArr+" State</center>");
	 }else{
		 var parentNameArr=parentNameArrMap.get("D").split(":");
		h3Template.html("<center>Inertmediate Panchyat of "+parentNameArr[0]+" (District Panchayat),"+parentNameArr[1]+" State</center>");
	 }
	
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>LGD Code </b>','right'));
	 trTemplate.append(createTD('<b>Intermediate Panchayat Name(In English) </b>','left'));
	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	 trTemplate.append(createTD('<b>No. Of Village Panchayats </b>','right'));
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		trTemplate.append(createFNTD(obj.noOfVlc,"showHideDiv('T',"+obj.entityCode+",'P','"+obj.entityNameEnglish+"','V')","right"));
		tableTemplate.append(trTemplate);	
		 total_no_of_vpsn =total_no_of_vpsn+obj.noOfVlc ;
		 
	});
	
	 trTemplate = $("<TR/>");
	 	/* trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right'));
		trTemplate.append(createTD('','right')); */
		trTemplate.append('<td colspan="5" style="text-align:center;font-weight:bold;margin:0 0 0 0;padding:20px 0 11px 0;">Total</td>');
		trTemplate.append(createTD('<b>'+total_no_of_vpsn+'</b>','right'));
		tableTemplate.append(trTemplate);
		
	 
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	 if (lbbackurl.has('D')){
		var parentArr=  lbbackurl.get("S");
	}else{
		var parentArr=  "showHideDiv('C',null,null,null,null)";
	}
	$( inputTemplate ).attr("onclick", parentArr);
	divTemplate.append(inputTemplate);
	 
};

createVillagePanchayatDetails=function(data,parent_type,parent_code,category){
	
	$("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 if (lbbackurl.has('D')){
		var parentNameArr=parentNameArrMap.get("T").split(":");
	    var h3Template=$("<h4/>");
		h3Template.html("<center>Villages Panchayat of "+parentNameArr[0]+" (Inertmediate Panchyat),"+parentNameArr[2]+" (District Panchayat),"+parentNameArr[1]+" State</center>");
	}else if (lbbackurl.has('S')){
				var parentNameArr=parentNameArrMap.get("T").split(":");
				var h3Template=$("<h4/>");
			/* 	alert(lbbackurl.get('S').split(",")[4]);
				alert(lbbackurl.get('S').split(",")[4].indexOf("D")); */
				if(lbbackurl.get('S').split(",")[4].indexOf("D")>0){
					h3Template.html("<center>Villages Panchayat of "+parentNameArr[0]+" (District Panchyat),"+parentNameArr[1]+" State</center>");
					
				}else{
					h3Template.html("<center>Villages Panchayat of "+parentNameArr[0]+" (Inertmediate Panchyat),"+parentNameArr[1]+" State</center>");
				}
	}
	
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>LGD Code </b>','right'));
	 trTemplate.append(createTD('<b>Village Panchyat Name (In English) </b>','left'));
	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		tableTemplate.append(trTemplate);		
	});
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	if (lbbackurl.has('D')){
		var parentArr=  lbbackurl.get("D");
	}else if (lbbackurl.has('S')){
		var parentArr=  lbbackurl.get("S");
	}
		
	
	$( inputTemplate ).attr("onclick", parentArr);
	divTemplate.append(inputTemplate);
	 
};

createUrbanDetails=function(data,parent_code,parent_entity_name){
	 $("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 
	 var parentNameArr=parentNameArrMap.get("S");
	 
	  var h3Template=$("<h4/>");
	 h3Template.html("<center>Urban Localbodies of "+parentNameArr+" State</center>");
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>LGD Code </b>','right'));
	 trTemplate.append(createTD('<b>Urban Localbody Name(In English) </b>','left'));
	 trTemplate.append(createTD('<b>Urban Localbody Type </b>','left'));

	 trTemplate.append(createTD('<b>Census 2001 Code </b>','left'));
	 trTemplate.append(createTD('<b>Census 2011 Code </b>','left'));
	 trTemplate.append(createTD('<b>No. of Wards </b>','right'));

	 
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		
		trTemplate.append(createTD(obj.entityCode,'right'));
		trTemplate.append(createTD(obj.entityNameEnglish,'left'));
		trTemplate.append(createTD(obj.census2001Code,'left'));
		trTemplate.append(createTD(obj.census2011Code,'left'));
		trTemplate.append(createTD(obj.noOfTlc,'left'));
		trTemplate.append(createFNTD(obj.noOfVlc,"showHideDiv('U',"+obj.entityCode+",'W','"+obj.entityNameEnglish+"')","right"));
		
		tableTemplate.append(trTemplate);		
	});
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	$( inputTemplate ).attr("onclick", "showHideDiv('C',null,null,null,null)");
	divTemplate.append(inputTemplate);
	
	 
};




createWardDetails=function(data,parent_type,parent_code,category){
	 $("#dynamicDIV").empty();
	 var divTemplate = $("#dynamicDIV");
	 $( divTemplate ).attr("class", "table-responsive");
	 
	 var parentNameArr1=parentNameArrMap.get("S").split(":");
	 var parentNameArr2=parentNameArrMap.get("U").split(":");
	 
	  var h3Template=$("<h4/>");
	 h3Template.html("<center>Wards of <b> "+parentNameArr2+" </b> Urban Localbodies   ,<b> "+parentNameArr1+" </b> State</center>");
	 
	 divTemplate.append(h3Template);
	 
	 var tableTemplate = $("<table/>");
	 $( tableTemplate ).attr("id", "dynamicExample");
	 $( tableTemplate ).attr("class", "table table-striped table-bordered dataTable");
	 var trTemplate = $("<TR/>");
	
	 trTemplate.append(createTD('<b>S.No. </b>','center'));
	 trTemplate.append(createTD('<b>Ward Number</b>','center'));
	 trTemplate.append(createTD('<b>Ward Code </b>','center'));
	 trTemplate.append(createTD('<b>Ward Name (In English) </b>','center'));
	
	
	 
	 tableTemplate.append(trTemplate);
	 
	 jQuery.each(data, function(index, obj) {
		trTemplate = $("<TR/>");
		trTemplate.append(createTD(index+1,'center'));
		trTemplate.append(createTD(obj.census2001Code,'center'));
		trTemplate.append(createTD(obj.entityCode,'center'));
		trTemplate.append(createTD(obj.entityNameEnglish,'center'));
		
		tableTemplate.append(trTemplate);		
	});
	divTemplate.append(tableTemplate);
	inputTemplate= $("<INPUT/>");
	$( inputTemplate ).attr("type", "button");
	$( inputTemplate ).attr("value", "BACK");
	
	var parentArr=parentArrMap.get("S").split(":");
	$( inputTemplate ).attr("onclick", "showHideDiv('"+parentArr[0]+"',"+parentArr[1]+",'"+parentArr[2]+"','"+pnameMap.get('S')+"')");
	
	 
	divTemplate.append(inputTemplate);
	
	
};


createTD=function(val,attribute){
	 var tdTemplate = $("<TD/>");
	 $( tdTemplate ).html(val);
	$( tdTemplate ).attr("align", attribute);
	
	 return tdTemplate;
};

createFNTD=function(val,fun,attribute){
	var tdTemplate = $("<TD/>");
	$( tdTemplate ).attr("align", attribute);
	
	 templateLabel = $("<label />");
	 templateLabel.html(val);
	 
	 templateAnchor=$("<A/>");
	 var href = "javascript:void(0);";
	 $( templateAnchor ).attr("href", href);
	$( templateAnchor ).attr("onclick", fun);
	templateAnchor.append(val);
	
	tdTemplate.append(templateAnchor);
	 return tdTemplate;
};




</script>