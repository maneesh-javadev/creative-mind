<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<script src="js/validation.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datepicker/demos.css" />

<script type="text/javascript">
function loadPage() {
		var mypath = window.location.href;

		document.getElementById('btnback').style.visibility='visible';
		document.getElementById('btnCancel').style.visibility='visible';
		document.getElementById('btnClose').style.visibility='hidden';
		var mySplitResult = mypath.split("&");

		if(mySplitResult[1]!="")
			{
		var type=mySplitResult[1].replace("type=","");
		

		if(type=='S')
			{
			document.getElementById('btnback').style.visibility='hidden';
			document.getElementById('btnCancel').style.visibility='hidden';
			document.getElementById('btnClose').style.visibility='visible';
			
			}
		else
			{
			document.getElementById('btnback').style.visibility='visible';
			document.getElementById('btnCancel').style.visibility='visible';
			document.getElementById('btnClose').style.visibility='hidden';
			}
			}
	
		
		
}
		</script>
</head>
<body onload="loadPage();">
	<div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td width="100%" valign="top" class="tblclear">

					<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><spring:message htmlEscape="true" code="Label.DELETEDEPTCENTER"></spring:message></td>
								<%--//this link is not working <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> </a></td> --%>
							</tr>
						</table>
					</div>
					<div class="frmpnlbrdr">
						<form:form action="abolishDepartment.htm" method="POST" enctype="multipart/form-data" commandName="viewDept">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="abolishDepartment.htm"/>" />
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true" code="Label.DELETEDEPTCENTER"></spring:message>
									</div>
									<table width="100%" class="tbl_no_brdr">
										<c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${viewDept.listMinistry}">
											<tr>
												<td width="14%" rowspan="54">&nbsp;</td>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.DEPTCODE"></spring:message><br /> <label class="lblPlain"> <spring:bind path="viewDept.listMinistry[${listMinistryDetailsRow.index}].orgCode">&nbsp;
																				 <c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind>
												</label>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.DEPTNAMEINENG"></spring:message><br /> <label class="lblPlain"> <spring:bind path="viewDept.listMinistry[${listMinistryDetailsRow.index}].orgName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind>
												</label>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.DEPTSHORTNAMEINENG"></spring:message><br /> <label class="lblPlain"> <spring:bind path="viewDept.listMinistry[${listMinistryDetailsRow.index}].shortName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind>
												</label>
													<div class="errormsg"></div></td>
											</tr>

											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.DEPTNAMEINLOCAL"></spring:message><br /> <label class="lblPlain"> <spring:bind path="viewDept.listMinistry[${listMinistryDetailsRow.index}].orgNameLocal">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind>
												</label>
													<div class="errormsg"></div></td>
											</tr>

											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORGLEVEL"></spring:message><br /> <label class="lblPlain"> <spring:bind path="viewDept.listMinistry[${listMinistryDetailsRow.index}].orgLevel">&nbsp;
																				<c:if test="${fn:containsIgnoreCase(status.value,'C')}">Centre</c:if>
															<c:if test="${fn:containsIgnoreCase(status.value,'S')}">State</c:if>

														</spring:bind>
												</label>
													<div class="errormsg"></div></td>
											</tr>



											<tr>
												<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORGTYPE"></spring:message><br /> <label class="lblPlain"> <spring:bind path="viewDept.listMinistry[${listMinistryDetailsRow.index}].organizationType.orgType">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
														</spring:bind>
												</label>
													<div class="errormsg"></div></td>
											</tr>




										</c:forEach>
									</table>
								</div>
								<!--Begining of Govt Order Details  -->

								<div>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
										</div>

										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="14">
												<form:hidden path="orderCode" id="hdnOrderCode" value="${viewDept.orderCode}" /></td>
												<td width="86%"><label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br />
												 <form:input path="orderNo" id="OrderNo" type="text" style="width: 120px" class="frmfield" onkeypress="return validateAlphanumericforOrder(event);" />
												 <span class="error" id="OrderNo_error"></span>&nbsp;&nbsp;&nbsp;<span> <form:errors htmlEscape="true" path="orderNo" class="errormsg"></form:errors>
												</span></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br />
												<form:input path="orderDate" id="OrderDate" type="text" class="frmfield" style="width: 120px" 	readonly="true" htmlEscape="true" />
												<span class="error" id="OrderDate_error"></span>&nbsp;&nbsp;&nbsp;<span> <form:errors htmlEscape="true" path="orderDate" class="errormsg"></form:errors>
												</span></td>

											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br /> 
												<form:input id="EffectiveDate" path="effectiveDate" type="text" class="frmfield" 	style="width: 120px" readonly="true" htmlEscape="true" />
												<span class="error" id="EffectiveDate_error"></span>&nbsp;&nbsp;&nbsp;<span>
												<form:errors htmlEscape="true" path="effectiveDate" class="errormsg"></form:errors>
												</span></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label> <br />
												<form:input id="GazPubDate" path="gazPubDate" type="text" class="frmfield" style="width: 120px" onkeypress="validateNumeric();" />
													<span class="error" id="GazPubDate_error"></span></td>


											</tr>
											<tr>

												<td><div id="titleId">
														<form:hidden id="showTitle" path="showTitle" name="showTitle" class="frmfield" />
													</div>
													<div id="hiddentitle"></div> <br />
													<div id="hideAttachmentUtilDiv">

														<br /> <br />
														<div id="alreadyAttachedFiles">
															<c:if test="${! empty Attached_File_Meta_Data_List}">
																<table width="100%" class="tbl_with_brdr" align="center" id="tid">
																	<tr class="frmhd">
																		<!-- 	<spring:message htmlEscape="true"  code="addAttachment.tableheading" /> -->
																	</tr>
																	<tr class="tblRowTitle tblclear">
																		<td><spring:message htmlEscape="true" code="addAttachment.filetitle" /></td>
																		<td><spring:message htmlEscape="true" code="addAttachment.filename" /></td>
																		<td><spring:message htmlEscape="true" code="addAttachment.filesize" /></td>
																		<td><spring:message htmlEscape="true" code="addAttachment.filecontenttype" /></td>
																		<td><spring:message htmlEscape="true" code="addAttachment.fileflagfordeletion" /></td>
																	</tr>
																	<c:forEach var="abc" items="${Attached_File_Meta_Data_List}">

																		<tr id="row${abc.attachmentId}" class="tblRowB">

																			<td><c:out value="${abc.fileTitle}" escapeXml="true"></c:out></td>
																			<td><c:out value="${abc.fileName}" escapeXml="true"></c:out></td>
																			<td><c:out value="${abc.fileSize}" escapeXml="true"></c:out></td>
																			<td><c:out value="${abc.fileContentType}" escapeXml="true"></c:out></td>

																			<td width="10%"><a href="#"><img src="images/delete.png" name="deleteAttachment" width="18" height="19" border="0"
																					onclick="{hideThisRow(${abc.attachmentId}),holdAttachmentID(${abc.attachmentId}),holdFilePath('${abc.fileTimestamp}'),holdFileSize(${abc.fileSize })}" /> </a>
																				<div id="deleteID"></div>
																				<div id="deletePath"></div>
																				<div id="deleteFileSize"></div></td>
																		</tr>

																	</c:forEach>
																</table>
															</c:if></td>
											</tr>
											<tr>
												<td class="tblclear"><%@ include file="../common/update_attachment.jspf"%></td>
											</tr>
										</table>
									</div>
								</div>
								<!-- End of Govt Order Details  -->

								<div class="btnpnl">
									<table width="100%" class="tbl_no_brdr">
										<tr>
											<td><label> <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.DELETE"></spring:message>" />
											</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /></label>
											</td>
										</tr>
									</table>
								</div>
							</div>
					</div>
					</div>
				</td>
			</tr>
		</table>
		</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
</body>
</html>