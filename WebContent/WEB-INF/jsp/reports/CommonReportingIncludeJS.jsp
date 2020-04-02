 <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">
 <script src="resource/dashboard-resource/plugins/datatables/jquery.dataTables.min.js"></script>
 <script src="resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrStateService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrBlockService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrReportService.js"></script>
<script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>

<script type='text/javascript'>
$(document).ready(function() {

	 $('#dialogBX').draggable({
		    handle: ".modal-header"
		});
	
	/* $('#example').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });  */
	jQuery.validator.addMethod("customMandate", function(value, element) {
		var status = true;
		var radioVal = $('input:radio[name="searchCriteriaType"]').filter(":checked").val();
		if(radioVal == "N"){
			var entityCode = $('#entityCode').val(), entityName = $('#entityName').val();
			if(isEmptyObject(entityCode) && isEmptyObject(entityName)){
				status = false;
			}
		} 
		return status;
	});
	jQuery.validator.addMethod("customMandateState", function(value, element) {
		var status = true;
		var radioVal=""
		if($('input:radio[name="searchCriteriaType"]').length>0){
			radioVal= $('input:radio[name="searchCriteriaType"]').filter(":checked").val();
		}else{
			radioVal="P";
		}
		if(radioVal == "P"){
			if(isEmptyObject($('#ddSourceState').val())){
				status = false;
			}
		} 
		return status;
	});
	jQuery.validator.addMethod("customMandateDist", function(value, element) {
		var status = true;
		var radioVal=""
			if($('input:radio[name="searchCriteriaType"]').length>0){
				radioVal= $('input:radio[name="searchCriteriaType"]').filter(":checked").val();
			}else{
				radioVal="P";
			}
		if(radioVal == "P"){
			if(isEmptyObject($('#ddSourceDistrict').val())){
				status = false;
			}
		} 
		return status;
	});
	jQuery.validator.addMethod("customMandateSubDist", function(value, element) {
		var status = true;
		var radioVal=""
			if($('input:radio[name="searchCriteriaType"]').length>0){
				radioVal= $('input:radio[name="searchCriteriaType"]').filter(":checked").val();
			}else{
				radioVal="P";
			}
		if(radioVal == "P"){
			if(isEmptyObject($('#ddSourceSubDistrict').val())){
				status = false;
			}
		} 
		return status;
	});
	
	jQuery.validator.addMethod("customMandateBlock", function(value, element) {
		var status = true;
		var radioVal=""
			if($('input:radio[name="searchCriteriaType"]').length>0){
				radioVal= $('input:radio[name="searchCriteriaType"]').filter(":checked").val();
			}else{
				radioVal="P";
			}
		if(radioVal == "P"){
			if(isEmptyObject($('#ddSourceBlock').val())){
				status = false;
			}
		} 
		return status;
	});
	
	 
	 $('form').each(function(){
        if($(this).attr('id') == "genericReportingEntity"){
        	$("#genericReportingEntity").validate({
                ignoreTitle: true ,
                submitHandler: function (form) {
                	form.submit();
                } 
            }); 
    		validationForm(); 
    	}
    });
	
	$("#entityCode").numeric({ decimal: false, negative: false }, function() {
		this.value = ""; this.focus(); 
	});
});
	
	
 var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
	if( isEmptyObject(entityCode) ){
		/* customAlert("No Record(s) found."); */
		$('#customAlertbody').text("No Record(s) found.");
		$('#customAlert').modal('show');
		return false;
	}
	/* $("#myIframe").contents().find("body").html('');
	$("#dialogBX").dialog({
		dialogClass: 'noTitleStuff',
	    modal: true,
	    width:950,
	    height: 700,
	    resizable:false ,
	    "open": function(){
	    	showLoadingImage();
	    	 $('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");  
             $("#myIframe").load(function(){
            	 hideLoadingImage(); 
		    });  
    	},
    });	
	$(".ui-dialog-titlebar").removeClass('ui-widget-header'); */
	//$("#dialogBXTitle").text('GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )');
	
	 $('#myIframe').attr('src',  cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");
	 /*  $("#myIframe").load(function(){
		  hideLoadingImage(); 
   });  */
	$('#dialogBX').modal('show'); 
}; 



var viewLandRegionGISMapInPopup = function (lvCode, inParam, localGovBodyType,entityType)	{
	
	dwrReportService.getEntitywiseParentDetails(entityType, parseInt(inParam), {
  	 	callback: function( obj ) {
  	 		$("#dialogBXTitle").text(obj);
  	 		$('#myIframe').attr('src',  "showGisView.do" + "?lvCode=" + lvCode +"&inParam=" + inParam + "&localGovBodyType=" + entityType + "&<csrf:token uri='showGisView.do'/>");
  	 		$('#dialogBX').modal('show'); 
  	 	
  	 	},
  	 	errorHandler : function(exception) { customAlert(exception); },
		async : true
		});
	
	
	
}; 


 var displayMap = function (inputCode, mapLevel, lrlb, vpFlag, vpStateCode) {
	var sharedObject = {};
	sharedObject.inputparam = inputCode;
	sharedObject.levelCode = mapLevel;
	sharedObject.localGovBodyType = lrlb;
	sharedObject.vpFlag = vpFlag;
	sharedObject.stateCode = vpStateCode;
	var sOptions = "dialogWidth:940px; dialogHeight:660px; dialogLeft: 300; dialogTop: 350; scroll:no; center:yes; resizable: no; status:no; edge:sunken;unadorned :yes";
	
	if(window.showModalDialog){
		showModalDialog("gisMapViewInModal.do", sharedObject, sOptions);
	} else {
		var modal=window.open("gisMapViewInModal.do", null, sOptions);
		 modal.dialogArguments = sharedObject;
		 
	}
}; 

var retrieveFile1 = function (entityCode, entityType) {
	lgdDwrStateService.getGovtOrderByEntityCode( parseInt(entityCode), entityType, {
  	 	callback: function( result ) {
  	 		
  	 		if(result == null){
  	 			/* customAlert("No Government Order found."); */
  	 			$('#customAlertbody').text('No Government Order found');
  	 			$('#customAlert').modal('show');
  	 		} else {
  	 			var filePath = result[0].filelocation;
  				lgdDwrStateService.openFile(filePath, {
  				  	callback: function( innerResult ) {
  				  		if(innerResult == null ) {
  						 	/* customAlert("File has been moved or deleted."); */
  				  		$('#customAlertbody').innerHtml='File has been moved or deleted';
  				  	    $('#customAlert').modal('show');
  						} else {
  							if(innerResult.length > 5)	{
  								var message = innerResult.substring(0,5);
  								if(message == "ERROR") {
  									/* customAlert( innerResult ); */
  									$('#customAlertbody').text(innerResult);
  					  	 			$('#customAlert').modal('show');
  									
  								}else{
  									var filename= innerResult.split('/').pop();
  									$(window).attr("location","downloadReportGO.do?filename="+ filename +"&<csrf:token uri='downloadReportGO.do'/>");
  								}
  				 			}
  						}
  				  	},	
  					errorHandler : function(exception) {
  						/* customAlert(exception); */
  						$('#customAlertbody').text(exception);
			  	 			$('#customAlert').modal('show');
  					
  					},
  					async : true  
  			  	});
  	 		}
  	 	},
  	 	errorHandler : function(exception) { customAlert(exception); },
		async : true
		});
}


var buildDistrict = function(stateCode){
	lgdDwrDistrictService.getDistrictList(parseInt(stateCode), {
		callback : function( result ) {
			var options = $("#ddSourceDistrict"); 
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.districtCode).text(obj.districtNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Vinay"); alert(exception); },
		async : true
	});
};

var buildSubDistrict = function(districtCode){
	lgdDwrSubDistrictService.getSubDistrictList(parseInt(districtCode), {
		callback : function( result ) {
			var options = $("#ddSourceSubDistrict"); 
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Bauwa"); alert(exception); },
		async : true
	});
};

var buildBlock = function(districtCode){
	lgdDwrBlockService.getBlockListbyDistrict(parseInt(districtCode), {
		callback : function( result ) {
			var options = $("#ddSourceBlock"); 
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.blockCode).text(obj.blockNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Bauwa"); alert(exception); },
		async : true
	});
}; 
</script>

  <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;height:700px">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h3 class="modal-title" id="dialogBXTitle">View Details</h3>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="910" height="650"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
							</div>