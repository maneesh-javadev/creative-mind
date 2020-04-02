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
var checkedCounter=0;
$(document).ready(function(){	
	
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
	
	
	$("#example").dataTable({
		//"bPaginate": false,
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
		title : '<spring:message code="Lable.VillageDraftList"  htmlEscape="true"></spring:message>',
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
			$("#dialog" ).dialog( "open" );
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
		if(id=="but1"){
   		 $("#dialog-confirm").html('<spring:message code="Msg.AreyouSureToFreezeState"  htmlEscape="true"></spring:message>');
   		 }
		/* added by sunita on 22-07-2015 */
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
				<form:form action="allStateFreeze.htm" id="allStateFreezeid" commandName="stateFreeze" method="post">
									  <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="allStateFreeze.htm"/>" />
									  <input type="hidden" htmlEscape="true" id="userid" name="userid" value="<c:out value='${userid}' escapeXml='true'></c:out>"></input>
									  <input type="hidden" htmlEscape="true" id="dcpdesValues" name="dcodes"></input>
									  <input type="hidden" htmlEscape="true" id="statusByStateUser" name="statusByStateUser"></input>									  
									  <input type="hidden" htmlEscape="true" id="ip" name="ip" value="<c:out value='${ip}' escapeXml='true'></c:out>"></input>
									  	<!--  added by sunita 0n 16-07-2015 -->
									 <form:input type="hidden" path="lbLrType" /> 
									  
									  <div id="dialog" title="Draft Village" style="display: none">
                                                   <label><spring:message code="Lable.VillagePendingInDraft"  htmlEscape="true"></spring:message></label>
                                                   <br></br>
                                                   <hr />
                                                   <br></br>
                                                   <table id="tblSample" class="data_grid" width="80%">  
                                                    	<thead> 
	                                                    	<tr class="tblRowTitle tblclear">
	                                                    		<th><spring:message code="Label.VILLAGECODE"  htmlEscape="true"></spring:message></th>  
	                                                   			<th><spring:message code="Label.VILLAGENAMEINENGLISH"  htmlEscape="true"></spring:message></th> 
	                                                    	</tr> 
                                                   		</thead>    
                                                   		<tbody></tbody>                                        
                                                   </table>   
							 		  </div>
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<ul class="form_body">
								<li><h4><spring:message code="label.district.wise.freeze.unfreeze.status" htmlEscape="true"></spring:message></h4>
									<table id="example" class="display" cellspacing="0" width="100%">
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
														<c:out value="${districtlist.updateddate}" escapeXml="true"></c:out>
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
															    	<!-- added by sunita on 15-07-2015 -->
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
							</li>
						</ul>
							 <div class="but">							 			  	
									      <input type="button" value="State Freeze" id="but1"  onclick="fnOpenConformDialog(this.id); "/>
									      <input type="button" value="State Unfreeze" id="but4" onclick="fnOpenConformDialog(this.id); "/>
									      <input type="button" value="Freeze/Unfreeze Districts"  id="but2" onclick="fnOpenConformDialog(this.id);" />
									      <input type="button" value="Close"  id="but3" onclick="javascript:go('home.htm');" />
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