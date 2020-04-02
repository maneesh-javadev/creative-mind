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
		"scrollX": true,
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
			 alert("Kindly Freeze Unfreeze any Local Body !");
			/*  $('#dialog-confirm').dialog('close'); */
			$('#modelclose').trigger('click'); 
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
			 /* $("#dialog1" ).prop('style','display:block'); */
			 /*$("#dialog1").dialog({
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
			$("#dialog1" ).dialog( "open" ); */
			$('#dialog1').modal('show');
			$('#'+id).prop('checked',false);
		}/* else
			{
			lgdDwrStateFreezeService.checkParentStatus($('#'+id).val(),'${category}', {
        		callback : handleParentStatus,
       			errorHandler : handleVillageError,
       			arg :id	        		
        	});	
			} */
	
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
		dialog2id=id;
		$('#dialog2').modal('show');
	   /*  $("#dialog-confirm").dialog({
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
	    }); */
	}
	
	var dialog2id;
	
	function dialog2Confirm(){
		if(dialog2id=="but2"){
    		SubmitPage();
    	}
    	if(dialog2id=="but3"){
    		$('#modelclose').trigger('click'); 
    	} 
	}
	/* Added by Kirandeep 18/05/2015 for parent level freeze conditions */
	var handleParentStatus = function(data,id) {		
	
		if(data == true) {
			/*  $("#dialog1" ).prop('style','display:block'); */
			 /*$("#dialog1").dialog({
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
			$("#dialog1" ).dialog( "open" ); */
			$('#dialog1').modal('show');
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
	
		callActionUrl=function(url){
		    $( 'form[id=alllbfreeze]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=alllbfreeze]' ).attr('method','post');
			$( 'form[id=alllbfreeze]' ).submit();
		};
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body >
		<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="alllbfreeze.htm" id="alllbfreeze" name="alllbfreeze" commandName="stateFreeze" method="post">
								<input type="hidden" id="dcpdesValues" name="allData" value=""></input>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Freeze/Unfreeze Status list of <c:out value="${titleMeaage}" escapeXml="true"></c:out></h3>
                                </div>
                            <div id="divCenterAligned" >	   
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
							   
							   <div class="modal fade" id="dialog1" role="dialog">
							    <div class="modal-dialog">
							    
							      <!-- Modal content-->
							      <div class="modal-content">
							        <div class="modal-header">
							          <button type="button" class="close" data-dismiss="modal">&times;</button>
							          <h4 class="modal-title">Message</h4>
							        </div>
							        <div class="modal-body">
							           <table id="tblSample" align="center">
                                        	<thead> 
                                         	<tr>
                                         		<td><b><spring:message text="District is Freezed for this LocalBody" code="Label.localBodyFreezeDistrictValidationMsg"></spring:message></b></td>
                                         	</tr> 
                                       		</thead>    
                                       	<tbody></tbody>                                        
                                      </table>
							        </div>
							        <div class="modal-footer">
							          <button type="button" class="btn btn-default"  onclick="modelClose();"  data-dismiss="modal">Ok</button>
							        </div>
							      </div>
							      
							    </div>
							  </div>
						
                        <div class="box-body">
                         
						
						
			
							<table id="example" class="table table-bordered table-striped dataTable no-footer" width="100%" cellspacing="0">
													<thead>
														<tr>
															<th colspan="5">
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
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="" disabled="disabled" /> Freeze 
																	    	<input type="checkbox" id="u${localBodyCode}" name="dcodes2" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"    /> Unfreeze
																	    														     	
																	    </c:when>
																	    <c:when test="${status == 0}">	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>" /> Freeze
																	    	
																	    	<input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" value="Unfreez" disabled="disabled" /> Unfreeze 													     	
																	    </c:when>			
																	    <c:when test="${status == null or status == 0}">																    	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1"  value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"  /> Freeze<!-- disabled="disabled" /> Freeze  -->
																	    	
																	    	 <input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" disabled="disabled" /> Unfreeze 													     	
																	    </c:when>			
																	    <c:when test="${status == null and status == 1}">																    	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"  /> Freeze
																	    	
																	    	 <input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" value="${coverageLblc.entityCode}" /> Unfreeze 													     	
																	    </c:when>
																	    <c:when test="${status == null and status == null}">																    	
																	    	<input type="checkbox" id="f${localBodyCode}" name="dcodes1" value="<c:out value='${localBodyCode}' escapeXml='true'></c:out>"  /> Freeze
																	    	
																	    	<input type="checkbox" id="u${coverageLblc.entityCode}" name="dcodes2" value="${coverageLblc.entityCode}" /> Unfreeze												     	
																	    </c:when>										  
																	</c:choose>
																</td>																					
															</tr>										
													</c:forEach>																				
												</tbody>
												<tfoot>
													<tr>
														<th colspan="5">
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
							
		
 				 
                 </div> 
             
                       
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="button"  class="btn btn-success"   id="but2" onclick="fnOpenConformDialog(this.id);" >Save</button>
				  <c:choose>
				  	<c:when test="${formCategory == 'R'}">
				  	   <button type="button"  class="btn btn-warning" id="butback" onclick="javascript:go('lBodyFreezeRural.htm?<csrf:token uri='lBodyFreezeRural.htm'/>');" >Back</button>
				  	</c:when>
				  	<c:when test="${formCategory == 'U'}">
				  	     <button type="button"  class="btn btn-warning" id="butback" onclick="javascript:go('lBodyFreezeUrban.htm?<csrf:token uri='lBodyFreezeUrban.htm'/>');" >Back</button>
				  	   </c:when>
				  	<c:when test="${formCategory == 'T'}">
				  	     <button type="button"  class="btn btn-warning" id="butback" onclick="javascript:go('lBodyFreezeTribe.htm?<csrf:token uri='lBodyFreezeTribe.htm'/>');" >Back</button>
				  	 </c:when>
			
				  <c:otherwise> 
				  
				  
				  </c:otherwise>
				    </c:choose>
				  <button type="button"  class="btn btn-danger"   id="but3" onclick="callActionUrl('home.htm')" >Close</button>
				 
				
				  <!-- <div id="dialog-confirm"></div> -->
				  
				 
				  
                 </div>
                 
           </div>   
       </div> 
       </div>
        <div class="modal fade" id="dialog2" role="dialog">
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
				          	<button type="button" class="btn btn-danger"  id="modelclose"  data-dismiss="modal">No</button>
				        </div>
				      </div>
				      
				    </div>
				  </div>
				</div>
		</form:form>
</section>
</div>
</section>
   
   
	<script src="/LGD/JavaScriptServlet"></script>
		</body>
	</html>