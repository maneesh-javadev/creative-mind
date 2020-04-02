<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css"></link>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);	
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#example,#example1").dataTable({
		
	});
	$('.dataTables_wrapper').css('min-height','210px');
	
	/*------------ for govt order erros hide----------------*/

	$("#OrderNo_error").hide();
	$("#OrderNo_error1").hide();
	$("#OrderNo_error2").hide(); 
	$("#OrderDate_error").hide();
	$("#EffectiveDate_error").hide();
	$("#templateList_error").hide();
	$("#category_error").hide();
	$("#OrderNo_msg").hide();
	$("#OrderDate_msg").hide();
	$("#EffectiveDate_msg").hide();
	$("#GazPubDate_msg").hide();
	$("#GazPubDate_error").hide();
	$("#error_ulb_gp").hide();
	$("#error_rlb_gp").hide();
	
});

function getValidatedForm() {
	checkFileDetails();
         if(validateULBS() && validateRLBS() && validateGovtOrderDetailsForVil()) {
        	 if(customDialog()) {
        		 return true;
        	 }
         }
}

function validateULBS() {
	var array_tableData = ""; 
	if($('.cb1').is(':checked')) {
	var rows = $('#tblCoveragedLBDetails > tbody > tr');
  	 for(var i = 0; i < rows.length; i++) {
  		 var datatableRowId = $(rows[i]).attr( 'id' ); 
  		 var isChecked = $(rows[i]).find('#cb1' + datatableRowId).is( ':checked' );
  	         if( isChecked ) {
  		        	var dstring	= $("#cb1"+datatableRowId).val();
  		        	dstring	+= ",";        	 
  		        	array_tableData	+= dstring;
  		        	
  			 	}
         }
  	var index = array_tableData.lastIndexOf(",");
  	$("#ulbsList").val(array_tableData.substr(0,index));
  	 $("#error_ulb_gp").hide();
  	 return true;
	} else {
		$("#error_ulb_gp").html("Please select at least 1 ULB coverage to de-notify !");
		$("#error_ulb_gp").show();
		return true;
	}
}
function validateRLBS() {
	var array_tableData = ""; 
	if($('.cb2').is(':checked')) {
	var oTable = $( '#example1' ).dataTable();
  	var rows = oTable.dataTable().fnGetNodes();
  	 for(var i = 0; i < rows.length; i++) {
  		 var datatableRowId = $(rows[i]).attr( 'id' ); 
  		 var isChecked = $(rows[i]).find('#cb2' + datatableRowId).is( ':checked' );
  	         if( isChecked ) {
  		        	var dstring	= $("#cb2"+datatableRowId).val();
  		        	dstring	+= ",";        	 
  		        	array_tableData	+= dstring;        	
  			 	}
         }
  	var index = array_tableData.lastIndexOf(",");
  	$("#rlbsList").val(array_tableData.substr(0,index));
  	$("#error_rlb_gp").hide();
  	return true;
	} else {
		$("#error_rlb_gp").html("Please select at least 1 Rural localbody to de-notify !");
		$("#error_rlb_gp").show();
		return true;
	}
}

</script>
<script language="javascript">
//---------------------------------- to select all checkboxes ---------------------------//
$(function(){
	$("#coveredall1").click(function() {
			$('.cb1').attr('checked',this.checked);
		});
		$(".cb1").click(function() {
			if($(".cb1").length==$(".cb1:checked").length) {
					$("#coveredall1").attr("checked","checked");
				} else {
							$("#coveredall1").removeAttr("checked");
					   }
		});
	
	$("#coveredall2").click(function() {
		$('.cb2').attr('checked',this.checked);
	});
	$(".cb2").click(function() {
		if($(".cb2").length==$(".cb2:checked").length) {
				$("#coveredall2").attr("checked","checked");
			} else {
						$("#coveredall2").removeAttr("checked");
				   }
	});	
	   
});
function customDialog () {
	$("#dialog-confirm").html('<spring:message code="Error.dialog-confirm-denotify"  htmlEscape="true"></spring:message>');
	$("#dialog-confirm").dialog({
        resizable: true,
        modal: true,
        title: '<spring:message code="Msg.ConformDenotifyULBRLB"  htmlEscape="true"></spring:message>',
        height: 200,
        width: 400,
        buttons: {
            "Yes": function () {
            	document.denotifyULBRLB.submit();
                return true;
            },
                "No": function () {
                $(this).dialog('close');	               
            }
        }
    });
}
function checkFileDetails() {
	if (document.getElementById('attachFile2').value != "") {
		// check whether browser fully supports all File API
	    if (window.File && window.FileReader && window.FileList && window.Blob)
	    {
	        // get the file size and file type from file input field
	        var fsize = $('#attachFile2')[0].files[0].size;
	        var ftype = $('#attachFile2')[0].files[0].type;
	        var fname = $('#attachFile2')[0].files[0].name;
	       if(fsize < 5242880) {
	    	   switch(ftype)
	           {
	               case 'image/png':
	               case 'image/gif':
	               case 'image/jpeg':
	               case 'image/pjpeg':
	               case 'pdf':
	            	   return true;
	                   break;
	               default:
	            	   customAlert('<spring:message code="errorMessage.addAttachment.allowedFileMimeTypeLB"  htmlEscape="true"></spring:message>');
	               	   document.getElementById("attachFile2").value="";
	           }   
	       } else {
	    	   customAlert('<spring:message code="errorMessage.addAttachment.allowedIndividualFileSize"  htmlEscape="true"></spring:message>');
	    	   document.getElementById("attachFile2").value="";
	    	   return true;
	       }
	    }else{
	    	 customAlert('Please upgrade your browser, because your current browser lacks some new features we need !');
	    	 return true;
	    }
	} else {
    	document.getElementById("attachFile2_error").innerHTML = "Please upload at aleast one file as Government Order !";
		$("#attachFile2_error").show();
		$("#attachFile2").addClass("error_fld");
		$("#attachFile2").addClass("error_msg");
		return true;
    }
}	
</script>
</head>	
		<body>
		<div class="form_stylings">
			<div class="header">
				<h3><spring:message code="Label.DenotifyGPsTitle"/></h3>
				<ul class="item_list">
					<%--//this link is not working <li>
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
					</li> --%>
					<li>
						<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return true;">Help</a>
					</li>
				</ul>
			</div>
			<div class="page_content">
				<div class="form_container">
					<form:form action="denotifyULBRLBVillage.htm" method="post" commandName="denotifyGP" id="form1" enctype="multipart/form-data" name="denotifyULBRLB">
						<form:hidden id="ulbsList" htmlEscape="true" path="ulbsListData"/>
						<form:hidden id="rlbsList" htmlEscape="true" path="rlbsListData"/>
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="denotifyULBRLBVillage.htm"/>" />
						<form:hidden path="transactionid" value="${tranId}" htmlEscape="true"/>
						<form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}" />
						<form:hidden htmlEscape="true" path="operation" value="C" />
					<div class="form_block">
							<div id="error_ulb_gp" class="errormsg"></div>
							<div class="col_1 overflow_horz">
								<ul class="form_body">
											<li>
												<h3>Details of ULB in which the selected GP got merged :</h3></br>
												<table id="example" class="display" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><spring:message code="Label.Serialno" text="Serial No." htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.ULBCode" text="ULB Code" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.URBANLBNAME" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.URBANLBNAMELOCAL" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.ULBCOVERAGE" htmlEscape="true"></spring:message></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="deNotifiedVPList" varStatus="listDepartmentRow" items="${getVPULBList }">
															<tr>
																<td class="sorting" tabindex="0" aria-controls="example">
																	 <c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.localBodyCode}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.lBNameEnglish}" escapeXml="true"></c:out>
																</td>
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:out value="${deNotifiedVPList.lBNameLocal}" escapeXml="true"></c:out>
																 </td> 
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:if test="${ not empty getCoveragedData}">
																	<div id="divCoveragedLBDeatils" class="form_stylings">
																		<div class="form_block">
																				<div class="col_1">
																					<ul class="form_body">
																						<li>
																							<table id="tblCoveragedLBDetails" class="data_grid" width="100%">
																								<thead>
																									<tr>
																										<th>S.No.</th>
																										<th><spring:message code="App.TYPEOFLANDREGION" text="LRTYPE"/></th>
																										<th><spring:message code="Label.COVERAGETYPE" /></th>
																										<th><spring:message code="Label.COVERAGEDETAIL"/></th>
																										<th><input type="checkbox" name="covered" id="coveredall1" class="linkdis masterTooltip" title="Select All"/></th>
																									</tr>
																								</thead>
																								<tbody>
																									<c:forEach var="varCoveragedEntity" items="${getCoveragedData}" varStatus="counter">
																										<tr id="${counter.index+1}">
																											<td><c:out value="${counter.index+1}" escapeXml="true"></c:out></td>
																											<td><c:out value="${varCoveragedEntity[1]}" escapeXml="true"></c:out></td>
																											<td><c:out value="${varCoveragedEntity[2]}" escapeXml="true"></c:out></td>
																											<td><c:out value="${varCoveragedEntity[3]}" escapeXml="true"></c:out></td>
																											<td><input type="checkbox" class="cb1" value="<c:out value='${varCoveragedEntity[0]}' escapeXml='true'></c:out>" name="covered" id="cb1${counter.index+1}"/></td>
																										</tr>
																									</c:forEach>
																								</tbody>
																							</table>
																						</li>
																					</ul>	
																				</div>
																			</div>
																			<br/>
																		</div>
																	</c:if>
																 </td>																				
															</tr>										
														</c:forEach>																				
												</tbody>
												
											</table>									
										</li>
									</ul>
							</div>
						</div>
					<div class="form_block">
							<div id="error_rlb_gp" class="errormsg"></div>
							<div class="col_1 overflow_horz">
								<ul class="form_body">
											<li>
												<h3>Invalidated Village Panchayats and their coverage details :</h3></br>
												<table id="example1" class="display" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><spring:message code="Label.Serialno" text="Serial No." htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.RLBCode" text="RLB Code" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.RLBANLBNAME" text="RLB Name(English)" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.RLBANLBNAMELOCAL" text="RLB Name(Local)" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.coveredvillage" htmlEscape="true"></spring:message></th>
															<th><input type="checkbox" name="covered" id="coveredall2" class="linkdis masterTooltip" title="Select All"/></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="deNotifiedVPList" varStatus="counter" items="${getVpRLBList }">
															<tr id="${counter.index+1}">
																<td class="sorting" tabindex="0" aria-controls="example">
																	 <c:out value="${counter.index+1}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.localBodyCode}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.lBNameEnglish}" escapeXml="true"></c:out>
																</td>
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:out value="${deNotifiedVPList.lBNameLocal}" escapeXml="true"></c:out>
																 </td> 
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:out value="${deNotifiedVPList.coveraged}" escapeXml="true"></c:out>
																 </td>		
																 <td><input type="checkbox" class="cb2" value="<c:out value='${deNotifiedVPList.localBodyCode}' escapeXml='true'></c:out>" name="covered" id="cb2${counter.index+1}"/></td>																		
															</tr>									
														</c:forEach>																				
												</tbody>
												
											</table>									
										</li>
									</ul>
							</div>
						</div>
						<!-- Govt Order Config -->
					<div class="form_block">
							<div class="col_1">
								<ul class="form_body">
									<li>
										<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
									 <%@ include file="../govtbody/ExistingGovernmentOrderVillageToDenotify.jsp"%> 
									<br /> <br />
									<%@ include file="../govtbody/GovtOrderUploadForm.jsp"%> 							
							</li>
							</ul>
						</div>
					</div></br>
						<!-- End of Govt Order Config -->
						<input type="button" id="createDenotify" name="submmit" value="De-Notify" onclick="getValidatedForm();"></input>
						<input type="button" id="close" name="close" value="Close" onclick="javascript:go('home.htm');"/>
						<div id="dialog-confirm"></div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>