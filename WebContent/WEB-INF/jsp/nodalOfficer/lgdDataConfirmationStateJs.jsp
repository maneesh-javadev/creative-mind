<script>
var action="N";
//setStateFreezebtn();
$(document).ready(function() {
	
	
	
	$("#btnConfirm").click(function() {
		var url=null;
		if(action=='D'){
			url="saveDistrictUnfreezebyState.htm";
		}else if(action=='F'){
			url="saveStateFreezebyState.htm";
		}else if(action=='U'){
			url="saveStateUnFreezebyStateNew.htm";
		}else if(action=='SU' || action=='SF'){
			url="saveStateFreezebyOnlyAtState.htm";
		}
		if(url!=null){
			submitFormFn(url); 
		}
		
	  });
	
	
	
	
	
});

jQuery.validator.setDefaults({
    debug: true,
    success: "valid"
  });

function validateNumber(e, id) {
	$("#err"+id).html("");
	var n = e.charCode;
	if ((n >= 48 && n <= 57) || (n == 0)) {

	} else {
		e.preventDefault();
		$("#" + id + "_type_error").fadeIn(1000, function() {
			$("#" + id + "_type_error").fadeOut(1000);
		});
	}
}

genrateOTP=function(userId){
	lgdDwrStateService.sendOTPForLGDDataConfirmation(userId, {
		callback : function( result ) {
			
			if($.parseJSON( result )){
				$("#msgsendOTP").html("OTP has been sent successfully to your nodal officer's mobile number & email id");
			}else{
				$("#msgsendOTP").html("Some problem send OTP please contact");
			}
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
};



validateDistrictUnFreezeStateFn=function(){
	var unFrrezeFlag=false;
		$.each($("input[id^='unfreezeStatus']"), function(){  
			if(!$(this).is(':disabled'))
			{
				temp=$( this ).attr('id');
				temp=temp.substr(14,temp.length);
				var txtArealen=$("#remark"+temp).val().length;
				if($(this).is(':checked')&& txtArealen>0 ){
					 unFrrezeFlag=true;
					$('#otpModule').modal('show');
					action='D';
					
				}
				
			}
	        
	    });
		
		if(!unFrrezeFlag){
			 $("#customAlertbody").text("please select any freeze district with remark to unfreeze action");
			 $("#customAlert").modal('show');
		}
	};
	
	
	/* setStateFreezebtn=function(){
		$( "#savestateFreeze" ).prop( "disabled", false );
		var frrezeFlag=true;
		$.each($("input[id^='unfreezeStatus']"), function(){  
			if(!$(this).is(':disabled'))
			{
			frrezeFlag=false;
			
			}
	        
	    });
		
		if(frrezeFlag){
			$( "#savestateFreeze" ).prop( "disabled", false );
			
		}
	} */
	
	
	validateFreezeFn=function(){
		
		$('#otpModule').modal('show');	
		action='F';
		};
		
		
		validateUnFreezeFn=function(){
			
			$('#otpModule').modal('show');	
			action='U';
			};
			
			
submitFormFn=function(url){
	if(action=='SF' || action=='SU' ){
		$( "#savestateUnFreeze" ).prop( "disabled", true );
		$( "#savestateFreeze" ).prop( "disabled", true );
		$( "#btnActionClose" ).prop( "disabled", true );
	}else{
		$( "#savestateUnFreeze" ).prop( "disabled", true );
		$( "#savestateFreeze" ).prop( "disabled", true );
		$( "#saveConfirmLGDData" ).prop( "disabled", true );
		$( "#btnActionClose" ).prop( "disabled", true );
	}
	
	callActionUrl(url); 
};

callActionUrl=function(url){
 	
	$( 'form[id=lgdDataConfirmationFrom]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=lgdDataConfirmationFrom]' ).attr('method','post');
	$( 'form[id=lgdDataConfirmationFrom]' ).submit();
};


var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName,distname)	{
	if( isEmptyObject(entityCode) ){
		/* customAlert("No Record(s) found."); */
		$('#customAlertbody').text("No Record(s) found.");
		$('#customAlert').modal('show');
		return false;
	}
	$('#dialogBXTitle').text("Urban Localbodies of "+distname+" District");
	 $('#myIframe').attr('src',  cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");
	$('#dialogBX').modal('show'); 
}; 


</script>
<!--  -------------------------------------- -->
 <div class="modal fade" id="customAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><c:out value="District wise Freeze/Unfreeze status of ${pname}" ></c:out></h4>
        </div>
        <div class="modal-body" id="customAlertbody">
         
        </div>
        <div class="modal-footer">
         
           <button type="button" class="btn btn-default" data-dismiss="modal" >Cancel</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- --------------------------------------------- -->
  <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;height:460px">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="910" height="450"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
							</div>