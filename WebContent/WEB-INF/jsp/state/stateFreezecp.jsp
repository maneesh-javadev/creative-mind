<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script> 
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<link rel="stylesheet" href="<%=contextpthval %>/css/stateFreeze.css"></link>
<script type='text/javascript'>
</script>
<script>
var checkedCounter=0;
$(document).ready(function(){
	
	jQuery.validator.setDefaults({
	    debug: true,
	    success: "valid"
	  });
	
	$("#btnConfirm").click(function() {
		SubmitPage();
	  });
	
	 $( ":checkbox" ).change(function(){
	        var value = $(this).val();
	        var id = $(this).attr('id');
	        if(this.checked){
	        	checkedCounter++;
	        	 if (id =='f'+value){
	 	        	$('#u'+value).prop( 'checked' , false );
	 	        	$('#lgdtext'+value).prop( 'disabled' , true );
	 	        	lgdDwrStateFreezeService.does_entity_child_in_draft_exist_fn(value, {
	 	        		callback : handleVillageList,
	 	       			errorHandler : handleVillageError,
	 	       			arg :id
	 	        		
	 	        	});
	 			 }	        
	 	        if(id =='u'+value){
	 	        	$('#f'+value).prop('checked',false);
	 	        	$('#lgdtext'+value).prop('disabled',false);
	 			 }	        	
	        }	   
	          if(!this.checked){
	        	  checkedCounter--;
	        	if(id =='u'+value){
	        		$('#lgdtext'+value).prop('disabled',true);
	        	}
	        }  
	 });
	
	
	/* $("#example").dataTable({
		//"bPaginate": false,
		"fnDrawCallback" : function() {
			$('tr').each(function() {
				$(this).removeClass("odd even");
			});
		},
	}); */
	
	
	 $("#example").dataTable();
	/* $("#dialog").dialog({
		autoOpen : false,
		height : 450,
		width : 650,
		modal : true,
		title : '<spring:message code="Lable.VillageDraftList"  htmlEscape="true"></spring:message>',
		show : {
			effect : "blind",
			duration : 1000,
		},
		hide : {
			effect : "explode",
			duration : 1000
		}
	}); */
	
	if(isParseJson('${enableFreeze}')) {
		$('#but1').prop('disabled',true);
		$('#but4').hide();
		
	}
	
	if(isParseJson('${showHideButton}')) {
		$('#but2').prop('disabled',true);
		$('#but1').hide();
		$('#but4').show();
	}	
	else{
		$('#but4').hide();
	}
	
	
	
	//$("#example_wrapper").css("width","950px");
});


function fetchTraineeValues(){
	 
	 var array_tableData = ""; 
	 var status = true;
	 var oTable = $( '#example' ).dataTable();
	 var rows = oTable.dataTable().fnGetNodes();
	 for(var i = 0; i < rows.length; i++) {
		 var datatableRowId = $(rows[i]).attr( 'id' ); 
		 if(!$(rows[i]).find('#f' + datatableRowId).is( ':disabled' )){
			 var freezeChecked = $(rows[i]).find('#f' + datatableRowId).is( ':checked' );
	         if( freezeChecked ){
		        	var dstring	= datatableRowId;
		        	dstring	+= "_" + "F";
		        	dstring	+= "_" + "NA" + ":";        	 
		        	array_tableData	+= dstring;        	
			 	}
		 }
		 if(!$(rows[i]).find('#u' + datatableRowId).is( ':disabled' )){
			var unfreezeChecked = $(rows[i]).find('#u' + datatableRowId).is(":checked");
		    if(unfreezeChecked) {
		        	var reason   =$(rows[i]).find('#lgdtext' +  datatableRowId).val();
				 	if(reason.length <= 0){
				 			$(rows[i]).find('#lgdtext' + datatableRowId).addClass("error");//css("border","1px solid red");       			 			
				 			status = false;
				 	}
					var dstring   = datatableRowId;
					dstring  += "_" + "U";
					dstring  += "_" + reason + ":";
				    array_tableData	+= dstring;        	 
		           }
		 }
               
       }
	 if(!status){
		 customAlert("Enter reason for Unfreez");
		 $('#dialog-confirm').dialog('close');
		 return false;
	 }
	  if(status){
		 if(array_tableData==""){
			 /* customAlert("Kindly Freeze Unfreeze any Districts"); */
			 $('#dialog-confirm').dialog('close');
			 return false;
		 } 
		 $('#dcpdesValues').val(array_tableData);
		 return true;
	 }
	 
}
	
	function SubmitPage() {
		var status=fetchTraineeValues();
		 if(status){
			$('#allStateFreezeid').attr('action', "allStateFreeze.htm?<csrf:token uri='allStateFreeze.htm'/>").submit(); 
		} 
		 return status;
	}
	
	var SubmitState = function() {
		$('#statusByStateUser').val("1");
		$('#allStateFreezeid').attr('action', "freezeState.htm?<csrf:token uri='freezeState.htm'/>").submit();
	};
	
	var SubmitStateUnfreeze = function() {
		$('#statusByStateUser').val("0");
		$('#allStateFreezeid').attr('action', "freezeState.htm?<csrf:token uri='freezeState.htm'/>").submit();
	};
	
	var handleVillageList =function(data,id){
		if(data.length > 0){
			/* $("#dialog" ).dialog( "open" ); */
			$('#dialog').modal('show');
			$("#tblSample > tbody").empty();
			$('#'+id).prop('checked',false);
			$.each(data, function(key, obj) {
				 var name=obj.villageNameEnglish;
				 var vcode=obj.villageCode;
				 var row = $("<tr>");
				 row.append( $("<td class='tblRowB'>").text(vcode) );
				 row.append( $("<td class='tblRowB'>").text(name) );			 
				 $("#tblSample").append(row);					
			});		
		}
	};
	
	var handleVillageError = function (){
			alert("No data Found");
	};
	
	var removeErrorClass = function(id) {
			$('#'+id).removeAttr( 'style' );
	};
	
	
	function fnOpenConformDialog(id) {		
		var otpflag=true
				if(id=="but1"){
					var userotp=$("#userOTP").val();
					
					if(userotp.length<6)
						{
						alert("otp is required");
						otpflag=false;
						}else{
			   		 $("#dialog-confirm").html('<spring:message code="Msg.AreyouSureToFreezeState"  htmlEscape="true"></spring:message>');
			   		 $("#dialogYes").show();
			   		 }
		   		 }
				/* modify by sunita on 27-07-2015 */
				if(id=="but2"){
					if (checkedCounter==0)
						{
						   $("#dialog-confirm").html('<spring:message code="Please select Freeze/Unfreeze any district"  htmlEscape="true"></spring:message>');	
					       $("#dialogYes").hide();
						}
					 else {
						    $("#dialog-confirm").html('<spring:message code="Msg.SelectedDistrictWillFreezeUnfreeze"  htmlEscape="true"></spring:message>');
			                $("#dialogYes").show();
						 
					      } 
			            
			    	}  
				
				if(id=="but4"){
					var userotp=$("#userOTP").val();
					
					if(userotp.length<6)
						{
						alert("otp is required");
						otpflag=false;
						}else{
		    		  $("#dialog-confirm").html('<spring:message code="Msg.AreyouSuretoUnfreezeState"  htmlEscape="true"></spring:message>');
		    		  $("#dialogYes").show();
						}
		    	}
				
				
				dialog2id=id;
				if(otpflag==true){
					$('#dialog2').modal('show');
				}
		    	
			
		
		
		
	   /*  $("#dialog-confirm").dialog({
	        resizable: false,
	        modal: true,
	        title: '<spring:message code="Msg.ConformlandRegionsFreezeUnfreeze"  htmlEscape="true"></spring:message>',
	        height: 250,
	        width: 400,
	        buttons: {
	            "Yes": function () {
	            	if(id=="but1"){
	            		SubmitState();
	            	}
	            	if(id=="but2"){
	            		SubmitPage();
	            	}
	            	if(id=="but4"){
	            		SubmitStateUnfreeze();
	            	}
	            },
	                "No": function () {
	                $(this).dialog('close');	               
	            }
	        }
	    }); */
	}
var dialog2id;
	
	function dialog2Confirm(){
		if(dialog2id=="but1"){
    		SubmitState();
    	}
    	if(dialog2id=="but2"){
    		$('#otpModule').modal('show');
    		
    	}
    	if(dialog2id=="but4"){
    		SubmitStateUnfreeze();
    	}
	}
	
	
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
					$("#msgsendOTP").html("OTP has been sent to your Nodal Officer's Mobile Number successfully");
				}else{
					$("#msgsendOTP").html("Some problem send OTP please contact");
				}
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	};
	
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body >

<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message code="label.district.wise.freeze.unfreeze.status" htmlEscape="true"/></h3>
					</div>
					<form:form action="allStateFreeze.htm" id="allStateFreezeid" commandName="stateFreeze" method="post" class="form-horizontal">
					 	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="allStateFreeze.htm"/>" />
					  	<input type="hidden" htmlEscape="true" id="userid" name="userid" value="<c:out value='${userid}' escapeXml='true'></c:out>"></input>
					  	<input type="hidden" htmlEscape="true" id="dcpdesValues" name="dcodes"></input>
					  	<input type="hidden" htmlEscape="true" id="statusByStateUser" name="statusByStateUser"></input>									  
					  	<input type="hidden" htmlEscape="true" id="ip" name="ip" value="<c:out value='${ip}' escapeXml='true'></c:out>"></input>
					  	<form:input type="hidden" path="lbLrType" />
					  	<div class="modal fade" id="dialog" role="dialog">
						    <div class="modal-dialog">
						      <div class="modal-content">
						        <div class="modal-header">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title">Draft Village</h4>
						        </div>
						        <div class="modal-body" id="customAlertbody">
						        	<label><spring:message code="Lable.VillagePendingInDraft"  htmlEscape="true"></spring:message></label>
		                        	
		                        	<hr />
		                        	<br></br>
		                        	<table id="tblSample" class="table table-bordered table-hover" width="100%" cellspacing="0">  
		                         		<thead> 
		                          			<tr class="tblRowTitle tblclear">
		                          				<th><spring:message code="Label.VILLAGECODE"  htmlEscape="true"></spring:message></th>  
		                         				<th><spring:message code="Label.VILLAGENAMEINENGLISH"  htmlEscape="true"></spring:message></th> 
		                          			</tr> 
		                        		</thead>    
		                        		<tbody></tbody>                                        
		                        	</table>
						        </div>
						        <div class="modal-footer">
						          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
						        </div>
						      </div>
						    </div>
						</div>
						<div class="box-body">
		 		  		<table id="example" class="table table-bordered table-striped dataTable no-footer" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th colspan="7">
										<div class="lgd-table">
							      			<div class="specified">
							        			<div class="reddish">
							          				<div></div>
							           				<b class="boldCharacter"><spring:message code="label.DistrictFreezedByStateUser" htmlEscape="true"></spring:message></b>
							        			</div>
							        			<div class="greenish">
							          				<div></div>
							          				<b class="boldCharacter"><spring:message code="label.DistrictUnfreezeByStateUser" htmlEscape="true"></spring:message></b>
							        			</div>
							        			<div class="bluish">
							          				<div></div>
							          				<b class="boldCharacter"><spring:message code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
							        			</div>
							      			</div>
							    		</div>
									</th>
								</tr>
								<tr>
									<th><spring:message code="Label.SNO"  htmlEscape="true"></spring:message></th>
									<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></th>
									<th><spring:message code="Label.DISTRICTNAMEINENGLISH"  htmlEscape="true"></spring:message></th>
									<th><spring:message code="label.stateFreeze.action.by.district"  htmlEscape="true"></spring:message></th>
									<th><spring:message code="label.stateFreeze.date" htmlEscape="true"></spring:message></th>
									<th><spring:message code="Label.stateFreeze.remarks"  htmlEscape="true"></spring:message></th>
									<th><spring:message code="label.stateFreeze.action" htmlEscape="true"></spring:message></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="districtlist" varStatus="listDepartmentRow" items="${districtList}">
									<tr id="${districtlist.districtCode}" 
										<c:choose>
									 		 <c:when test="${showHideButton}">
									 			style="background-color:#CEF6F5"
										    </c:when> 
										 	<c:otherwise>
												    <c:if test="${districtlist.status_by_state_user == 1}">
												     	style="background-color:#fa8787"
												    </c:if>
												    <c:if test="${districtlist.status_by_state_user == 0}">
												     	style="background-color:#59ff90" 
												    </c:if>
											</c:otherwise>
										</c:choose>>		
										<td>
											<c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out> 
										</td>
										<td id="dcode${listDepartmentRow.index+1}">
											<c:out value="${districtlist.districtCode}" escapeXml="true"></c:out>
										</td>
										<td id="dname${listDepartmentRow.index+1}">
											<c:out value="${districtlist.district_name_english}" escapeXml="true"></c:out>
										</td>
										<c:if test="${districtlist.status == 1}">
											<td id="dstatus${listDepartmentRow.index+1}">
												<c:out value="Freezed"></c:out>
											</td>
										</c:if>
										<c:if test="${districtlist.status == 0}">
											<td id="dstatus${listDepartmentRow.index+1}">
												<c:out value="UnFreezed"></c:out>
											</td>
										</c:if>
										<c:if test="${districtlist.status == null}">
											<td id="dstatus${listDepartmentRow.index+1}">
												<c:out value="N/A"></c:out>
											</td>
										</c:if>
										<td id="lupdateddate${listDepartmentRow.index+1}">
											 <fmt:formatDate pattern = "dd-MM-yyyy"   value = "${districtlist.updateddate}" />
										</td>
										<td> 
											<textarea id="lgdtext${districtlist.districtCode}" name="reason" onfocus="removeErrorClass('lgdtext${districtlist.reason}')" disabled="disabled"><c:out value="${districtlist.reason}" escapeXml="true"></c:out></textarea>
										</td>
										<td>					
											<c:choose>
											    <c:when test="${districtlist.status_by_state_user == 1 }">	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="" disabled="disabled" checked="checked"/> Freeze	
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" value="<c:out value='${districtlist.districtCode}' escapeXml='true'></c:out>"/> Unfreeze														     	
											    </c:when>
											    <c:when test="${districtlist.status_by_state_user == 0  and districtlist.status == null}">	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="<c:out value='${districtlist.districtCode}'/>" /> Freeze
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" value="Unfreez" disabled="disabled" checked="checked"/> Unfreeze														     	
											    </c:when>	
											     <c:when test="${districtlist.status_by_state_user == 0  and districtlist.status == 1}">	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="<c:out value='${districtlist.districtCode}'/>" /> Freeze
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" value="<c:out value='${districtlist.districtCode}'/>" /> Unfreeze														     	
											    </c:when>
													
											    <c:when test="${districtlist.status_by_state_user == null and districtlist.status == 0}">																    	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="<c:out value='${districtlist.districtCode}'/>" /> Freeze
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" disabled="disabled" /> Unfreeze														     	
											    </c:when>			
											    <c:when test="${districtlist.status_by_state_user == null and districtlist.status == 1}">																    	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="<c:out value='${districtlist.districtCode}'/>"/> Freeze
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" value="<c:out value='${districtlist.districtCode}'/>" /> Unfreeze														     	
											    </c:when>
											    <c:when test="${districtlist.status_by_state_user == null and districtlist.status == null}">																    	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="<c:out value='${districtlist.districtCode}'/>" /> Freeze
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" value="<c:out value='${districtlist.districtCode}'/>" disabled="disabled"/> Unfreeze														     	
											    </c:when>
											     <c:when test="${districtlist.status_by_state_user == 0 and districtlist.status == 0}">																    	
											    	<input type="checkbox" id="f${districtlist.districtCode}" name="dcodes1" value="<c:out value='${districtlist.districtCode}'/>" /> Freeze
											    	<input type="checkbox" id="u${districtlist.districtCode}" name="dcodes2" value="<c:out value='${districtlist.districtCode}'/>" disabled="disabled"/> Unfreeze														     	
											    </c:when>											  
											</c:choose>
										</td>																					
									</tr>										
								</c:forEach>																				
							</tbody>
							<tfoot>
								<tr>
									<td colspan="7">
										<div class="lgd-table">
							      			<div class="specified">
							        			<div class="reddish">
							          				<div></div>
							           				<b class="boldCharacter"><spring:message code="label.DistrictFreezedByStateUser" htmlEscape="true"></spring:message></b>
							        			</div>
							        			<div class="greenish">
							          				<div></div>
							          				<b class="boldCharacter"><spring:message code="label.DistrictUnfreezeByStateUser" htmlEscape="true"></spring:message></b>
							        			</div>
							        			<div class="bluish">
							          				<div></div>
							          				<b class="boldCharacter"><spring:message code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
							        			</div>
							      			</div>
							    		</div>
									</td>
								</tr>
							</tfoot>
						</table>
						
             <br/>
             <br/>
                   <c:if test="${stateStatus eq 1}">
					<!-- <div class="box-header subheading">
                  		<h4 class="box-title"></h4>
               	    </div> -->  <!-- /.box-header -->
               	    
               	       <div class="form-group">
	                    	<label class="col-sm-5 control-label">${freezeDistrict.stateStatus}<c:out value="The data available/entered in LGD is up-to-date and correct. " escapeXml="true"></c:out></label>
	                      	<div class="col-sm-6">
	                        	  <button name="sendOTP" type="button" class="btn btn-success" onclick="genrateOTP('${userId}');" <c:if test="${freezeDistrict.districtStatus eq 'Freeze'}">Disabled='true'</c:if>>  Confirm</button>
									   <div id="msgsendOTP" style="color: green;"></div>
	                      	</div>
	                    </div>
	                    <br/>
                           <br/>
                        <div class="form-group">
	                    	<label class="col-sm-5 control-label"><c:out value="Enter OTP" escapeXml="true"></c:out><span class="mandatory">*</span></label>
	                      	<div class="col-sm-6">
	                        	  <form:input path= "userOTP" pattern = "^[0-9\\s]+$" class="form-control" id="userOTP" maxlength="6" required = "true" placeholder="Enter OTP" onkeypress="validateNumber(event,'userOTP')" autocomplete="off"/>
	                               <form:errors htmlEscape="true" path="userOTP" cssClass="errormsg"/>
	                               <div id="userOTPErr" style="color: red;font-size: x-small;"></div>
	                      	</div>
	                    </div>
					</c:if>  
					</div>   
     <br/> <br/>
						<div class="box-footer">
				           <div class="col-sm-offset-2 col-sm-10">
				           		<div class="pull-right">
				                  <input type="button" class="btn btn-primary" value="State Freeze" id="but1"  onclick="fnOpenConformDialog(this.id); "/>
							      <input type="button" class="btn btn-primary" value="State Unfreeze" id="but4" onclick="fnOpenConformDialog(this.id); "/>
							      <input type="button" class="btn btn-success" value="Freeze/Unfreeze Districts"  id="but2" onclick="fnOpenConformDialog(this.id);" />
							      <input type="button" class="btn btn-danger" value="Close"  id="but3" onclick="javascript:go('home.htm');" />
				                 <div class="modal fade" id=dialog2 role="dialog">
								    <div class="modal-dialog">
								      <!-- Modal content-->
								      <div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								          <h4 class="modal-title">Message</h4>
								        </div>
								        <div class="modal-body" id="dialog-confirm">
								        </div>
								        <div class="modal-footer">
								          	<button type="button" class="btn btn-success" onclick="dialog2Confirm();"  data-dismiss="modal">Yes</button>
								          	<button type="button" class="btn btn-danger"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">No</button>
								        </div>
								      </div>
								      
								    </div>
				  				</div>
                 </div>
           </div>   
       </div> 
     </div>   
         <!-- dialog box  -->
          <div class="modal fade" id="otpModule" tabindex="-1" role="dialog" >
							<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					  				<button type="button" class="close" data-dismiss="modal">&times;</button>
					  				<b><c:out value="District Unfreeze" /></b>
					   				</div>
					  				<div class="modal-body" >
							       			  <div class="form-group">
											  <label  class="col-sm-4 lgd_updation_message" for="sel1">
											  <c:out value="To Unfrreze District level you need to genrate OTP" escapeXml="true"></c:out></p>
											  </label>
											  <div class="col-sm-4">
											   <button name="sendOTP" type="button" class="btn btn-success" onclick="genrateOTP('${lgdDataConfirmation.userId}');" <c:if test="${isConfirmDataSame}">Disabled='true'</c:if>>  Genrate OTP</button>
											   <div id="msgsendOTP" style="color: green;"></div>
											  </div>
											  
											</div>	
											<br/>	
						                     <div class="form-group">
					                                <label class="col-sm-4 control-label">Enter OTP <span class="mandatory">*</span></label>
						                               <div class="col-sm-4">
						                               <form:input path= "userOTP" pattern = "^[0-9\\s]+$" class="form-control" id="userOTP" maxlength="6" required = "true" placeholder="Enter OTP" onkeypress="validateNumber(event,'userOTP')" autocomplete="off" />
						                               
						                               <div id="userOTPErr" style="color: red;font-size: x-small;"></div>
						                               
						                               </div> 
					                        </div>
					  				</div>
					     			 <div class="modal-footer">
					     			 	<button type="button" class="btn btn-default" data-dismiss="modal" id="btnConfirm">Confirm</button>
          								 <button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
					     			</div>
								</div>
							  </div>
						</div>
						<!-- end of dialog -->
             
    </form:form>      
   </section>
   </div>
   </section>
</body>
</html>