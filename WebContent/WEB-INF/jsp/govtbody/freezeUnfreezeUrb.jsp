<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css">	</link>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>
<link rel="stylesheet" href="<%=contextpthval %>/css/stateFreeze.css"></link>
<script type='text/javascript'>
</script>
<script>
/* modify by sunita on 27-07-2015 */
var checkedCounter=0;
$(document).ready(function(){
	
	 $(":checkbox").change(function(){
	        var value = $(this).val();
	        var id = $(this).attr('id');
	        if(this.checked){
	        	checkedCounter++;
	        	 if (id =='f'+value){
	 	        	$('#u'+value).prop('checked',false);
	 	        	$('#lgdtext'+value).prop('disabled',true);
	 	        	var lbType=$('#lbLrType').val();
	 	        	lgdDwrStateFreezeService.doesLbChildInDraftExist(value,lbType, {
	 	        		callback : handleVillageList,
	 	       			errorHandler : handleVillageError,
	 	       			arg :id
	 	        		
	 	        	});
	 			 }	        
	 	        if(id =='u'+value){
	 	        	$('#f'+value).prop('checked',false);
	 	        	$('#lgdtext'+value).prop('disabled',false);
	 	        	/* Removing validation in case of unfreeze by sunita on 15-07-2015
	 	        	var lbType=$('#lbLrType').val();
	 	        	lgdDwrStateFreezeService.doesLbChildInDraftExist(value,lbType, {
	 	        		callback : handleVillageList,
	 	       			errorHandler : handleVillageError,
	 	       			arg :id
	 	        		
	 	        	}); */
	 			 }	        	
	        }	   
	        if(!this.checked){
	        	checkedCounter--;
	        	if(id =='u'+value){
	        		$('#lgdtext'+value).prop('disabled',true);
	        	}
	        }
	 });
	
	
	$("#example").dataTable({
		"fnDrawCallback" : function() {
			$('tr').each(function() {
				$(this).removeClass("odd even");
			});
		},
	});
	
	$("#dialog").dialog({
		autoOpen : false,
		height : 450,
		width : 650,
		modal : true,
		title : 'LocalBody Draft/Unfreez List',
		show : {
			effect : "blind",
			duration : 1000,
		},
		hide : {
			effect : "explode",
			duration : 1000
		}
	});
	
	
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
			$('#allStateFreezeid').attr('action', "alldistrictFreezePri.htm?<csrf:token uri='alldistrictFreezePri.htm'/>").submit(); 
		} 
		 return status;
	}
	
	var SubmitState = function() {
		$('#statusByStateUser').val("1");
		$('#allStateFreezeid').attr('action', "freezeStateForPri.htm?<csrf:token uri='freezeStateForPri.htm'/>").submit();
	};
	
	var SubmitStateUnfreeze = function() {
		$('#statusByStateUser').val("0");
		$('#allStateFreezeid').attr('action', "freezeStateForPri.htm?<csrf:token uri='freezeStateForPri.htm'/>").submit();
	};
	
	var handleVillageList =function(data,id){
		if(data.length > 0){
			$("#dialog" ).dialog( "open" );
			$("#tblSample > tbody").empty();
			$('#'+id).prop('checked',false);
			$.each(data, function(key, obj) {
				 var name=obj.lbName;
				 var vcode=obj.lblc;
				 var row = $("<tr>");
				 row.append( $("<td class='tblRowB'>").text(vcode) );
				 row.append( $("<td class='tblRowB'>").text(name) );	
				 row.append( $("<td class='tblRowB'>").text(status) );		 
				 $("#tblSample").append(row);					
			});		
		}
		if(data.length == 0){
				var dcode=$('#'+id).val();
				var lbType=$('#lbLrType').val();
				lgdDwrStateFreezeService.doesLbChildInFreezExist(dcode,lbType, {
		        		callback : handleLocalBodyList,
		       			errorHandler : handleVillageError,
		       			arg :id
		        		
	        	});
			
		}
		
		
	};
	
	var handleVillageError = function (){
			alert("No data Found");
	};
	
	var removeErrorClass = function(id) {
			$('#'+id).removeAttr( 'style' );
	};
	
	function handleLocalBodyList(data,id) {
		if(data.length > 0){
			$("#dialog" ).dialog( "open" );
			$("#tblSample > tbody").empty();
			$('#'+id).prop('checked',false);
			$.each(data, function(key, obj) {
				 var name=obj.lbName;
				 var vcode=obj.lblc;
				 var status =obj.status;
				 var row = $("<tr>");
				 row.append( $("<td class='tblRowB'>").text(vcode) );
				 row.append( $("<td class='tblRowB'>").text(name) );
				 row.append( $("<td class='tblRowB'>").text(status) );				 
				 $("#tblSample").append(row);					
			});		
		}
		
	}
	
	
	function fnOpenConformDialog(id) {		
		if(id=="but1"){
   		 $("#dialog-confirm").html('<spring:message code="Msg.AreyouSureToFreezeState"  htmlEscape="true"></spring:message>');
   		 }
		/* modify by sunita on 27-07-2015 */
		if(id=="but2"){
			if (checkedCounter==0)
				$("#dialog-confirm").html('<spring:message code="Please select Freeze/Unfreeze any district"  htmlEscape="true"></spring:message>');	
			 else  
	             $("#dialog-confirm").html('<spring:message code="Msg.SelectedDistrictWillFreezeUnfreeze"  htmlEscape="true"></spring:message>');
	    	}  
		
		if(id=="but4"){
    		$("#dialog-confirm").html('<spring:message code="Msg.AreyouSuretoUnfreezeState"  htmlEscape="true"></spring:message>');
    	}
		
	    $("#dialog-confirm").dialog({
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
	    });
	}

	
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body class="dt-example">

<div class="form_stylings">

		<div class="header">
					<h3></h3>
					<ul class="item_list">
						<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
						<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
					</ul>
		</div>
			
		<div class="page_content">
			<div class="form_container">
				<form:form  action="allStateFreeze.htm" id="allStateFreezeid" commandName="stateFreeze" method="post">
					  <input htmlEscape="true" type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="allStateFreeze.htm"/>"/>
					  <input htmlEscape="true" type="hidden" id="userid" name="userid" value="<c:out value='${userid}' escapeXml='true'></c:out>"></input>
					  <input htmlEscape="true" type="hidden" id="dcpdesValues" name="dcodes" value=""></input>
					  <input htmlEscape="true" type="hidden" id="statusByStateUser" name="statusByStateUser" value=""></input>
					  <form:input path="lbLrType" type="hidden" id="lbLrType" />
					  <input htmlEscape="true" type="hidden" id="ip" name="ip" value="<c:out value='${ip}' escapeXml='true'></c:out>"></input>
					  <div id="dialog" title="Draft Localbody" style="display: none">
                                               <label><spring:message code="Msg.LocalBodyPendingInDraft"  htmlEscape="true"></spring:message></label>                                                   
                                               <br></br>
                                               <hr />
                                               <br></br>
                                               <table id="tblSample" class="data_grid" width="80%">  
                                                	<thead> 
                                                 	<tr class="tblRowTitle tblclear">
                                                 		<th><spring:message code="label.StateFU.localbodyCode"  htmlEscape="true"></spring:message></th>  
                                                			<th><spring:message code="label.StateFU.localbodyNamme"  htmlEscape="true"></spring:message></th> 
                                                 	</tr> 
                                               		</thead>    
                                               		<tbody></tbody>                                        
                                               </table>   
					 </div>
					<br></br>
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<ul class="form_body">
								<li><h4><spring:message code="Label.districtWiseFreezeunfreezeUrb" htmlEscape="true"></spring:message></h4>
									<table id="example" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th colspan="7">
													<div class="lgd-table">
										      			<div class="specified">
									        			    <div class="reddish">
										          				<div></div>
										           				<b class="boldCharacter"><spring:message code="label.freezeByStateUser" htmlEscape="true"></spring:message></b>
										        			</div>
										        			<div class="greenish">
										          				<div></div>
										          				<b class="boldCharacter"><spring:message code="label.UnfreezeByStateUser" htmlEscape="true"></spring:message></b>
										        			</div>
									      				</div>
										    		</div>
												</th>
											</tr>
											<tr>
												<th><spring:message code="label.stateFreeze.serial.no"  htmlEscape="true"></spring:message></th>
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
												<c:set var="stateStatus" value="${districtlist.status_by_state_user}" />
												<c:set var="districtStatus" value="${districtlist.status}" />	
												<c:set var="districtCode" value="${districtlist.districtCode}" />
												<tr id="${districtCode}" 
															<c:choose>
															    <c:when test="${stateStatus eq 1}">
															     	style="background-color:#fa8787"
															    </c:when>
															    <c:when test="${stateStatus eq 0}">
															     	style="background-color:#59ff90" 
															    </c:when>															  
															</c:choose>>
													<td>
														<c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out> 
													</td>
													<td id="dcode${listDepartmentRow.index+1}">
														<c:out value="${districtCode}" escapeXml="true"></c:out>
													</td>
													<td id="dname${listDepartmentRow.index+1}">
														<c:out value="${districtlist.district_name_english}" escapeXml="true"></c:out>
													</td>
													<c:if test="${districtStatus eq 1}">
													<td id="dstatus${listDepartmentRow.index+1}">
														<c:out value="Freezed"></c:out>
													</td>
													</c:if>
													<c:if test="${districtStatus eq 0}">
													<td id="dstatus${listDepartmentRow.index+1}">
														<c:out value="UnFreezed"></c:out>
													</td>
													</c:if>
													<c:if test="${districtStatus eq null}">
													<td id="dstatus${listDepartmentRow.index+1}">
														<c:out value="N/A"></c:out>
													</td>
													</c:if>
													<td id="lupdateddate${listDepartmentRow.index+1}">
														<c:out value="${districtlist.updateddate}" escapeXml="true"></c:out>
													</td>
													<td> 
														<textarea id="lgdtext${districtCode}" name="reason" onfocus="removeErrorClass('lgdtext${districtlist.reason}')"  disabled="disabled"><c:out value="${districtlist.reason}" escapeXml="true"></c:out></textarea>
													</td>
													<td>					
														<c:choose>
															    <c:when test="${stateStatus eq 1 and districtStatus eq 0}" >	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="" checked="checked" disabled="disabled"  /> Freeze	
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Unfreeze														     	
															    </c:when>
															    
															   <c:when test="${stateStatus eq 0 and districtStatus eq 1}" >	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Freeze	
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" value="" checked="checked"  disabled="disabled"/> Unfreeze														     	
															    </c:when>
															    
															   <c:when test="${stateStatus eq 1 and districtStatus eq 1}" >		
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="" checked="checked" disabled="disabled"/> Freeze	
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Unfreeze														     	
															    </c:when>
															    
															     <c:when test="${stateStatus eq 0 and districtStatus eq 0}" >		
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Freeze	
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" checked="checked" disabled="disabled"   /> Unfreeze														     	
															    </c:when>	
															    <c:when test="${stateStatus eq null and districtStatus eq 0}">																    	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Freeze
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" disabled="disabled" /> Unfreeze														     	
															    </c:when>			
															    <c:when test="${stateStatus eq null and districtStatus eq 1}">																    	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Freeze
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Unfreeze														     	
															    </c:when>
															    <c:when test="${stateStatus eq null and districtStatus eq null}">																    	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/> Freeze
															    	<!-- added by sunita on 17-07-2015 -->
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" disabled="disabled" /> Unfreeze														     	
															    </c:when>
															     <c:when test="${stateStatus eq 1 and districtStatus eq null}" >	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1" value="" checked="checked" disabled="disabled"  /> Freeze	
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="<c:out value='${districtCode}' escapeXml='true'></c:out>" /> Unfreeze														     	
															    </c:when>	
															    <c:when test="${stateStatus eq 0 and districtStatus eq null}" >	
															    	<input type="checkbox" id="f${districtCode}" name="dcodes1"  /> Freeze	
															    	<input type="checkbox" id="u${districtCode}" name="dcodes2" value="" checked="checked" disabled="disabled"  /> Unfreeze														     	
															    </c:when>	
 									  
														</c:choose>
													</td>																					
											</tr>										
										</c:forEach>																				
									</tbody>
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="lgd-table">
									      			<div class="specified">
									        			    <div class="reddish">
										          				<div></div>
										           				<b class="boldCharacter"><spring:message code="label.freezeByStateUser" htmlEscape="true"></spring:message></b>
										        			</div>
										        			<div class="greenish">
										          				<div></div>
										          				<b class="boldCharacter"><spring:message code="label.UnfreezeByStateUser" htmlEscape="true"></spring:message></b>
										        			</div>
									      			</div>
									    		</div>
											</td>
										</tr>
									</tfoot>
								</table>									
							</li>
						</ul>
							 <div class="but">							 			  	
									      <input type="button" value="State Freeze" name="" id="but1"  onclick="fnOpenConformDialog(this.id); "/>
									      <input type="button" value="State Unfreeze" name="" id="but4"  onclick="fnOpenConformDialog(this.id); "/>
									      <input type="button" value="Save" name="" id="but2" onclick="fnOpenConformDialog(this.id);" />
									      <input type="button" value="Close" name="" id="but3" onclick="javascript:go('home.htm');" />
									      <div id="dialog-confirm"></div>
							  </div>
						</div>
				</div>		
			 </form:form>	
			</div>
		</div>		
</div>
</body>
</html>