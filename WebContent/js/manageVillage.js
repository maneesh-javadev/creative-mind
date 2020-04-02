


$(document).ready(function() {
	
		jQuery.validator.addMethod("customMandateDist", function(value, element) {
		var status = true;
		if(isEmptyObject($('#ddSourceDistrict').val())){
			status = false;
		}
		return status;
		});
	
		jQuery.validator.addMethod("customMandateSubDist", function(value, element) {
		var status = true;
		if(isEmptyObject($('#ddSourceSubDistrict').val())){
				status = false;
		} 
		return status;
		});
	    
		$("#ddSourceDistrict").change(function() {
			$('#ddSourceSubDistrict option[value != ""]').remove();
			if(isEmptyObject($(this).val()))return false;
			buildSubDistrict($(this).val());
		});
		
		
		$( '#actionFetchDetails' ).click(function() {
			validationForm();
	 		var districtName = $("#ddSourceDistrict option:selected").text();
	 		var subDistrictName = $("#ddSourceSubDistrict option:selected").text();
	 		$("#entitesForMsg").val( subDistrictName + "," + districtName );
		});
		
		if(_JS_DISTRICT_CODE>0){
			$('#ddSourceSubDistrict option[value != ""]').remove();
			buildSubDistrict(_JS_DISTRICT_CODE);
		}
		
		
		
		 $('form').each(function(){
		        if($(this).attr('id') == "form1"){
		        	$("#form1").validate({
		                ignoreTitle: true ,
		                submitHandler: function (form) {
		                	form.submit();
		                } 
		            }); 
		    		validationForm(); 
		    	}
		    });
		
	});


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


var validationForm = function (){
	
 	$("#ddSourceDistrict" ).rules( "add", {
		  customMandateDist : true, messages: {customMandateDist : "Please select a District."}
	}); 
 	
 	$("#ddSourceSubDistrict" ).rules( "add", {
 		customMandateSubDist : true, messages: {customMandateSubDist : "Please select a Sub-District."}
	}); 
 	
}




