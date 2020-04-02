<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<script src="js/validation.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datepicker/demos.css" />

<script type="text/javascript" language="javascript">
function formSubmit(){
	var error=0;
	var itr=0;
	var stateL=document.getElementById('stateL');
	var districtL=document.getElementById('districtL');
	var subDistrictL=document.getElementById('subDistrictL');
	var villageL=document.getElementById('villageL');

	if(document.getElementById('ministryName').value==0 || document.getElementById('ministryName').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Ministry from the list.");
	}
	if(itr==0 && (document.getElementById('deptOrgCode').value==0 || document.getElementById('deptOrgCode').value==""||document.getElementById('deptOrgCode').value=="Select")){
		error=1;
		itr=1;
		alert("Kindly select the Department from the list.");
	}
	if(itr==0 && document.getElementById('orgType').value==0 || document.getElementById('orgType').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Organization Type from the list.");
	}
	if(itr==0 && document.getElementById('orgTypeName').value==""){
		error=1;
		itr=1;
		alert("Kindly fill the Organization name.");
	}
	/* if(itr==0 && !stateL.checked && !districtL.checked && !subDistrictL.checked && !villageL.checked){
		error=1;
		itr=1;
		alert("Kindly select either of the locations.");
	} */
	if(itr==0 && districtL.checked && !subDistrictL.checked && !villageL.checked){
		if(!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
	}
	
	if(subDistrictL.checked && !villageL.checked){
		if(!stateL.checked && !districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State and District level as well.");
		}
		if(itr==0 &&!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		if(itr==0 &&!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
		itr=0;
	}
	if(villageL.checked){
		if(!stateL.checked && !districtL.checked && !subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State, District and Sub District level as well.");
		}
		if(itr==0 && !stateL.checked && !districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State and District level as well.");
		}
		if(itr==0 &&!subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the Sub District level as well.");
		}
		if(itr==0 &&!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		if(itr==0 &&!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
	}
	if (error==0){
		document.forms['createOrganization'].submit();
	}
}

	
	
function isNext(id){
	document.getElementById('btnSave').value="Save";
	if (document.getElementById(id).checked) {
		document.getElementById('btnSave').value="Next";
	} 
}
</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div></td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent">
		<div class="frmhd">


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.DELETEORGANIZATIONCENTER" htmlEscape="true" text="Delete Organization"></spring:message></td>
					<td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a></td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="abolishOrganization.htm" method="POST" enctype="multipart/form-data" commandName="viewDept">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="abolishOrganization.htm"/>" />
				<form:input type="hidden" path="orgCode" name="orgCode" />
				<div id="cat">
					<div class="frmtxt">

						<table width="100%" class="tbl_no_brdr">

							<c:if test="${! empty listMinistry}">

								<c:forEach var="listMinistry" items="${listMinistry}">
									<tr>
										<td width="14%">&nbsp;</td>
										<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORGCODE"></spring:message><br /> <label class="lblPlain"> <c:out value="${listMinistry.orgCode}" escapeXml="true"></c:out>
										</label>
											<div class="errormsg"></div></td>
									</tr>
									<tr>
										<td width="14%">&nbsp;</td>
										<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORGNAMEINENG"></spring:message><br /> <label class="lblPlain"> <c:out value="${listMinistry.orgName}" escapeXml="true"></c:out>
										</label>
											<div class="errormsg"></div></td>
									</tr>
									
									<tr>
										<td width="14%">&nbsp;</td>
										<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORGNAMEINLOCAL"></spring:message><br /> <label class="lblPlain"> <c:out value="${listMinistry.orgNameLocal}" escapeXml="true"></c:out>
										</label>
											<div class="errormsg"></div></td>
									</tr>
									
									<tr>
										<td width="14%">&nbsp;</td>
										<td class="lblBold"><spring:message htmlEscape="true" code="Label.ORGSHORTNAMEINENG"></spring:message><br /> <label class="lblPlain"> <c:out value="${listMinistry.shortName}" escapeXml="true"></c:out>
										</label>
											<div class="errormsg"></div></td>
									</tr>

								</c:forEach>
							</c:if>

						</table>
					</div>
					<!--Begining of Govt Order Details  -->

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
							</div>

							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" rowspan="14"><form:hidden path="orderCode" id="hdnOrderCode" value="${viewOrgState.orderCode}"/></td>
									<td width="86%"><label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br /> <form:input path="orderNo" id="OrderNo" type="text" style="width: 120px" class="frmfield"
											onkeypress="return validateAlphanumericforOrder(event);" /> <span class="error" id="OrderNo_error"></span>&nbsp;&nbsp;&nbsp;<span> <form:errors htmlEscape="true" path="orderNo" class="errormsg"></form:errors>
									</span></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br /> <form:input path="orderDate" id="OrderDate" type="text" class="frmfield" style="width: 120px"
											readonly="true" htmlEscape="true" /> <span class="error" id="OrderDate_error"></span>&nbsp;&nbsp;&nbsp;<span> <form:errors htmlEscape="true" path="orderDate" class="errormsg"></form:errors>
									</span></td>

								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span class="errormsg">*</span><br /> <form:input id="EffectiveDate" path="effectiveDate" type="text" class="frmfield"
											style="width: 120px" readonly="true" htmlEscape="true" /> <span class="error" id="EffectiveDate_error"></span>&nbsp;&nbsp;&nbsp;<span> <form:errors htmlEscape="true" path="effectiveDate" class="errormsg"></form:errors>
									</span></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label> <br /> <form:input id="GazPubDate" path="gazPubDate" type="text" class="frmfield" style="width: 120px" onkeypress="validateNumeric();" />
										<span class="error" id="GazPubDate_error"></span></td>


								</tr>
								<%--  <tr>

									<td><div id="titleId">
											<form:hidden id="showTitle" path="showTitle" name="showTitle"
												class="frmfield" />
										</div>
										<div id="hiddentitle"></div>
										<br />
										<div id="hideAttachmentUtilDiv">
											
											<br /> <br />
											<div id="alreadyAttachedFiles">
												<c:if test="${! empty Attached_File_Meta_Data_List}">
													<table width="100%" class="tbl_with_brdr" align="center"
														id="tid">
														<tr class="frmhd">
															<!-- 	<spring:message htmlEscape="true"  code="addAttachment.tableheading" /> -->
														</tr>
														<tr class="tblRowTitle tblclear">
															<td><spring:message htmlEscape="true"  code="addAttachment.filetitle" />
															</td>
															<td><spring:message htmlEscape="true"  code="addAttachment.filename" />
															</td>
															<td><spring:message htmlEscape="true"  code="addAttachment.filesize" />
															</td>
															<td><spring:message htmlEscape="true" 
																	code="addAttachment.filecontenttype" />
															</td>
															<td><spring:message htmlEscape="true" 
																	code="addAttachment.fileflagfordeletion" />
															</td>
														</tr>
														<c:forEach var="abc"
															items="${Attached_File_Meta_Data_List}">

															<tr id="row${abc.attachmentId}" class="tblRowB">

																<td><c:out value="${abc.fileTitle}" escapeXml="false"></c:out></td>
																<td><c:out value="${abc.fileName}" escapeXml="false"></c:out></td>
																<td><c:out value="${abc.fileSize}" escapeXml="false"></c:out></td>
																<td><c:out value="${abc.fileContentType}" escapeXml="false"></c:out></td>

																<td width="10%"><a href="#"><img
																		src="images/delete.png" name="deleteAttachment"
																		width="18" height="19" border="0"
																		onclick="{hideThisRow(${abc.attachmentId}),holdAttachmentID(${abc.attachmentId}),holdFilePath('${abc.fileTimestamp}'),holdFileSize(${abc.fileSize })}" />
																</a>
																	<div id="deleteID"></div>
																	<div id="deletePath"></div>
																	<div id="deleteFileSize"></div></td>
															</tr>

														</c:forEach>
													</table>
												</c:if>
									</td>
								</tr> --%>
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
								<td><label> <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.DELETE"></spring:message>" onclick="return onCsave();" />
								</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /></label></td>
							</tr>
						</table>
					</div>

				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
	</td>

</body>
</html>