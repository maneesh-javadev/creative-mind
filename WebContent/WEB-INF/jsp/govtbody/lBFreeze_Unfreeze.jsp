<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script src="js/common.js"></script>
<script src="js/successMessage.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css"></link>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>
<link rel="stylesheet" href="<%=contextpthval %>/css/stateFreeze.css"></link>
<script type='text/javascript'>
</script>
<script>

$(document).ready(function(){	
	$(":checkbox").change(function(){
	  var value = $(this).val();
	
	     var id = $(this).attr('id');
	        if(this.checked){
	        	 if (id =='f'+value){
	        		// alert("in"+value);
	        		 //alert( $("#lbCoverage"+value).val() );
	        		 
	        		 var lbCoverage=$("#lbCoverage"+value).val();
	        		 if(lbCoverage==null || (lbCoverage!=null && lbCoverage.trim()=="")){
	        			 $('#f'+value).prop('checked',false);
	        			 customAlert("Please define coverage of selected localbody first.");
	        		 }else{
	        			 $('#u'+value).prop('checked',false);
	 	 	        	$('#lgdtext'+value).prop('disabled',true);
	 	 	        	lgdDwrStateFreezeService.chechUncheckLocalBodyFreeze(value, {
	 	 	        		callback : handleVillageList,
	 	 	       			errorHandler : handleVillageError,
	 	 	       			arg :id
	 	 	        		
	 	 	        	});
	        		 }
	        		
	 			 }	        
	 	        /* if(id =='u'+value){
	 	        	
	 	        	
	 	        	lgdDwrStateFreezeService.chechUncheckLocalBodyFreeze(value, {
	 	        		callback : handleVillageList,
	 	       			errorHandler : handleVillageError,
	 	       			arg :id
	 	        		
	 	        	}); 	        	
	 	        	
	 	        	
	 	        	$('#f'+value).prop('checked',false);
	 	        	$('#lgdtext'+value).prop('disabled',false);
	 			 }	         */	
	        }	   
	        if(!this.checked){
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
	
	
	
	
	if(isParseJson('${enableFreeze}')) {
		$('#but1').prop('disabled',true);
		$('#but4').hide();
		
	}
	
	if(isParseJson('${showHideButton}')) {
		$('#but2').prop('disabled',true);
		$('#but1').hide();
		$('#but4').show();
	}	
});


function fetchTraineeValues(){
	 
	 var array_tableData = ""; 
	 var status=true;
	 var oTable=$('#example').dataTable();
	 var rows = oTable.dataTable().fnGetNodes();
	 for(var i=0;i<rows.length;i++)
      {
		 var nominationId1 = $(rows[i]).attr("id");
		 var chek=$(rows[i]).find('#f'+nominationId1).is(":checked");
		 if(chek){
			var dstring	= nominationId1;
	        	dstring	+= "_"+"F";
	        	dstring	+= "_"+ "NA" +":";        	 
	        	array_tableData	+= dstring;
	        } 
        var chek1=$(rows[i]).find('#u'+nominationId1).is(":checked");
        if(chek1) {
       	   	var dstring   = nominationId1;
				dstring  += "_"+"U";
				dstring  += "_"+"NA"+":";
	        	array_tableData	+= dstring; 
	       }         
      }
	 if(status){
		 if(array_tableData==""){
			 customAlert("Kindly Freeze Unfreeze any Local Body !");
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
			$('#alllbfreeze').attr('action', "alllbfreeze.htm?<csrf:token uri='alllbfreeze.htm'/>").submit(); 
		} 
		 return status;
	}
	
	function ClosePage(){
		$('#alllbfreeze').attr('action', "home.do?<csrf:token uri='home.do'/>").submit();
	}
	
	/* Changed by Kirandeep 18/05/2015 for parent level freeze conditions */
	var handleVillageList = function(data,id){
		
		if(data==false){
			$("#dialog1" ).prop('style','display:block');
			$("#dialog1").dialog({
				autoOpen : false,
				height : 150,
				width : 300,
				modal : true,
				title : 'Local Body Draft Mode Status',
				show : {
					effect : "blind",
					duration : 1000,
				},
				hide : {
					effect : "explode",
					duration : 1000
				}
			});
			$("#dialog1" ).dialog( "open" );
			$('#'+id).prop('checked',false);
		}else
			{
			lgdDwrStateFreezeService.checkParentStatus($('#'+id).val(),'${category}', {
        		callback : handleParentStatus,
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
	
	
	function fnOpenConformDialog(id) {
		var state= '<c:out value="${lbfreeze.stateName}" escapeXml="true"></c:out>';
		var district= '<c:out value="${lbfreeze.entity_name_english}" escapeXml="true"></c:out>';
		if(id=="but2"){
    		$("#dialog-confirm").html("Selected Localbodies will be Freeze/Unfreeze by the System, Are you Sure ?");
    	}
		if(id=="but3"){
			if(district == null || district == "")
				{
    				$("#dialog-confirm").html("Close Localbody Freeze/UnFreeze ("+state+") ?");
				} else {
					$("#dialog-confirm").html("Close Localbody Freeze/UnFreeze ("+district+") ?");
				}
    	}
		var title;
		if(district == null || district == "")
		{
			title = "Confirm Local bodies Freeze/Unfreeze ("+state+")";
		} else {
			title = "Confirm Local bodies Freeze/Unfreeze ("+district+")";
		}
	    $("#dialog-confirm").dialog({
	        resizable: false,
	        modal: true,
	        title: title,
	        height: 250,
	        width: 400,
	        buttons: {
	            "Yes": function () {
	            	if(id=="but2"){
	            		SubmitPage();
	            	}
	            	if(id=="but3"){
	            		ClosePage();
	            	} 
	            },
	                "No": function () {
	                $(this).dialog('close');	               
	            }
	        }
	    });
	}
	
	/* Added by Kirandeep 18/05/2015 for parent level freeze conditions */
	var handleParentStatus = function(data,id) {		
	
		if(data == true) {
			$("#dialog1" ).prop('style','display:block');
			$("#dialog1").dialog({
				autoOpen : false,
				height : 150,
				width : 300,
				modal : true,
				title : 'Local Body Draft Mode Status',
				show : {
					effect : "blind",
					duration : 1000,
				},
				hide : {
					effect : "explode",
					duration : 1000
				}
			});
			$("#dialog1" ).dialog( "open" );
			$('#'+id).prop('checked',false);
		}			
	};
	
	 /* Added by Kirandeep 18/05/2015 for parent level freeze conditions */
	/*var handleParentStatusForDraft = function(data,id){		
		alert(data+"2");
		if(data == true) {			
			$("#dialog1" ).prop('style','display:block');
			$("#dialog1").dialog({
				autoOpen : false,
				height : 150,
				width : 300,
				modal : true,
				title : 'Local Body Draft Mode Status',
				show : {
					effect : "blind",
					duration : 1000,
				},
				hide : {
					effect : "explode",
					duration : 1000
				}
			});
			$("#dialog1" ).dialog( "open" );
			$('#'+id).prop('checked',false);
		} else {
			$("#dialog" ).prop('style','display:block');
			$("#dialog").dialog({
				autoOpen : false,
				height : 150,
				width : 300,
				modal : true,
				title : 'Local Body Draft Mode Status',
				show : {
					effect : "blind",
					duration : 1000,
				},
				hide : {
					effect : "explode",
					duration : 1000
				}
			});
			$("#dialog" ).dialog( "open" );
			$('#'+id).prop('checked',false);
		}		
	};
	 */
	 
	 backtoGetMethod=function(){
			document.forms['alllbfreeze'].method = "POST";
			document.forms['alllbfreeze'].action ="localBodyFreezeBack.htm?<csrf:token uri='localBodyFreezeBack.htm'/>";
			document.forms['alllbfreeze'].submit();
		};
	
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body class="dt-example">
		<div class="form_stylings">
			<div class="header">
			
			
				<h3>Freeze/Unfreeze Status list of <c:out value="${titleMeaage}" escapeXml="true"></c:out></h3>
				<ul class="item_list">
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
				</ul>
			</div>
			
					<div>
						<div class="form_container">
							<form:form action="alllbfreeze.htm" id="alllbfreeze" name="alllbfreeze" commandName="stateFreeze" method="post">
								<input type="hidden" id="dcpdesValues" name="allData" value=""></input>
								<div id="divCenterAligned" class="form_stylings">					
									<%-- <div class="form_block">
										<div class="col_1">
											<div class="table-main">
											  <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="AllStateFreeze.htm"/>"/>
											    <div class="lgd-table">
											      <div class="specified">
											        <div class="reddish">
											          <div></div>
											           <b class="boldCharacter"><spring:message text="Freezed Local Bodies"></spring:message></b>
											        </div>
											        <div class="greenish">
											          <div></div>
											          <b class="boldCharacter"><spring:message text="Un-Freeze Local Bodies"></spring:message></b>
											        </div>
											      </div>
											      <div class="clear"></div>									     
											    </div>
											</div>
				   							<div id="dialog" title="Local Body" style="display: none">
                                                <br></br><br></br>
                                                 <table id="tblSample" align="center"><!--  width="50%"> -->  
                                                   	<thead> 
                                                    	<tr>
                                                    		<td><b><spring:message text="Cannot Freeze Local body as it is Draft"></spring:message></b></td>
                                                    	</tr> 
                                                  		</thead>    
                                                  	<tbody></tbody>                                        
                                                 </table>   
										   </div>
										   	<div class="clear"></div>
										</div>
									</div>	 --%>
								<div id="dialog" title="Local Body" style="display: none">
 					<br></br><br></br>
                                     <table id="tblSample" align="center">
                                        	<thead> 
                                         	<tr>
                                         		<td><b><spring:message text="Cannot Freeze Local body as it is Draft Mode"></spring:message></b></td>
                                         	</tr> 
                                       		</thead>    
                                       	<tbody></tbody>                                        
                                      </table>   
							   </div>
							   
							   <div id="dialog1" title="Local Body" style="display: none">
 					<br></br>
                                     <table id="tblSample" align="center">
                                        	<thead> 
                                         	<tr>
                                         		<td><b><spring:message text="District is Freezed for this LocalBody" code="Label.localBodyFreezeDistrictValidationMsg"></spring:message></b></td>
                                         	</tr> 
                                       		</thead>    
                                       	<tbody></tbody>                                        
                                      </table>   
							   </div>
								</div>
								<br></br>
								<div class="form_block">
									<div class="col_1 overflow_horz">
										<ul class="form_body">
											<li>
											
												<table id="example" class="display" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th colspan="7">
																<div class="lgd-table">
													      			<div class="specified">
													        			<div class="reddish">
													          				<div></div>
													           				  <b class="boldCharacter"><spring:message text="Freezed Local Bodies"></spring:message></b>
													        			</div>
													        			<div class="greenish">
													          				<div></div>
													          				<b class="boldCharacter"><spring:message text="Un-Freeze Local Bodies"></spring:message></b>
													        			</div>
													      			</div>
													    		</div>
															</th>
														</tr>
														<tr>
															<th><spring:message code="Lable.Serialno" text="Serial No." htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.LocalBodyCode" text="Local Body Code" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.LOCALBODYNAMEINENG" text="Local Body Name English" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Lable.LocalBodyCoverage" text="Local Body Coverage" htmlEscape="true"></spring:message></th>
															<%-- <th><spring:message code="" text="LbLC" htmlEscape="true"></spring:message></th> --%>
															<th><spring:message code="Label.Freeze" text="Freeze" htmlEscape="true"></spring:message></th>
															<%-- <th><spring:message code="Label.ModifyName" htmlEscape="true"></spring:message></th> --%>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="freezeDataForLocalbody" varStatus="freezeRow" items="${freezeDataForLocalbodyList}">
															<c:set var="status" value="${freezeDataForLocalbody.status}" />
															<c:set var="lbCode" value="${freezeDataForLocalbody.lblc}" />
															<c:set var="localBodyCode" value="${freezeDataForLocalbody.localBodyCode}" />
															<c:choose>
																	    <c:when test="${status eq 1}">
																	     	<c:set var="style" value="background-color:#fa8787" />
																	     </c:when>
																	    <c:when test="${status eq 0}">
																	     	<c:set var="style" value="background-color:#59ff90" />
																	     	
																	     </c:when>	
																	     <c:otherwise>
																	     <c:set var="style" value="none" />
																	     </c:otherwise>
																	     														  
															</c:choose>
															<tr id="${localBodyCode}"  style="${style}">
																	
																<td>
																	<c:out value="${freezeRow.index+1}" escapeXml="true"></c:out>
																</td>
																<td id="dcode${freezeRow.index+1}">
																	<c:out value="${localBodyCode}" escapeXml="true"></c:out>
																</td>
																<td id="dname${freezeRow.index+1}">
																	<c:out value="${freezeDataForLocalbody.localBodyName}" escapeXml="true"></c:out>
																</td>
																 <td id="lgdtext${freezeRow.index+1}"> 
																	<c:out value="${freezeDataForLocalbody.localBodyCoverage}" escapeXml="true"></c:out>
																 </td> 
																<td>					
																	<input type="hidden" id="lbCoverage${localBodyCode}" value="${freezeDataForLocalbody.localBodyCoverage}" />
																	<c:choose>
																		
																	    <c:when test="${status == 1}">	
																	    	<%-- <input type="checkbox" id="f${coverageLblc.entityCode}" name="dcodes1" value="" disabled="disabled" /> Freeze --%>	
																	    	<input type="checkbox" id="u${localBodyCode}" name="dcodes2" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"    /> Unfreeze
																	    														     	
																	    </c:when>
																	    <c:when test="${status == 0}">	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>" /> Freeze
																	    	
																	    	<%-- <input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" value="Unfreez" disabled="disabled" /> Unfreeze --%>														     	
																	    </c:when>			
																	    <c:when test="${status == null or status == 0}">																    	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1"  value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"  /> Freeze<!-- disabled="disabled" /> Freeze  -->
																	    	
																	    	<%-- <input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" disabled="disabled" /> Unfreeze --%>														     	
																	    </c:when>			
																	    <c:when test="${status == null and status == 1}">																    	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"  /> Freeze
																	    	
																	    	<%-- <input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" value="${coverageLblc.entityCode}" /> Unfreeze --%>														     	
																	    </c:when>
																	    <c:when test="${status == null and status == null}">																    	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"  /> Freeze
																	    	
																	    	<%-- <input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" value="${coverageLblc.entityCode}" /> Unfreeze --%>														     	
																	    </c:when>										  
																	</c:choose>
																</td>																					
															</tr>										
													</c:forEach>																				
												</tbody>
												<tfoot>
													<tr>
														<th colspan="7">
															<div class="lgd-table">
												      			<div class="specified">
												        			<div class="reddish">
												          				<div></div>
												           				  <b class="boldCharacter"><spring:message text="Freezed Local Bodies"></spring:message></b>
												        			</div>
												        			<div class="greenish">
												          				<div></div>
												          				<b class="boldCharacter"><spring:message text="Un-Freeze Local Bodies"></spring:message></b>
												        			</div>
												      			</div>
												    		</div>
														</th>
													</tr>
												</tfoot>
											</table>									
										</li>
									</ul>
										 <div class="but">							 			  	
										      <input type="button" value="Save" name="" id="but2" onclick="fnOpenConformDialog(this.id);" />
										       <input type="button" value="Back" name="" id="butback" onclick="backtoGetMethod();" />
										      <input type="button" value="Close" name="" id="but3" onclick="fnOpenConformDialog(this.id);" />
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