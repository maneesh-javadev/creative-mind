<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message code="Label.MODIFYMINISTRY" htmlEscape="true"></spring:message></title>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script>

<!-- Modified by Pooja on 09-07-2015 -->
/* $(document)
.ready(
		function() {
			$("#frmModifyVillage")
					.validate(
							{
								rules : {
									ministryNamecr : {
										required : true,
										onlyLetterSpace : true
									},
									shortministryName : {
										shortName : true
									}
								},
								messages : {
									ministryNamecr : {
										required : "<font color='red'><br><spring:message code='MINISTRYNAME.REQUIRED' text='Please enter ministry name'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.valid.ministryName' text='Please enter value in correct format'/></font>"
									},
									shortministryName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		}); */

		$(document).ready(function() {	
			$("#btnSave").click(function() {	
				clearFormErrors();
				var ministryName = $("#ministryName").val();	
				var ministryShortName = $("#ministryshortName").val();
				if(ministryName ==''){
					$('#ministryName_errorMsg').html("Please Enter Ministry Name");
					return false;
				}
				else{
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryName)){
					$('#ministryName_errorMsg').html("Please Enter value in Correct format");
					return false;
				}
				}
				if(ministryShortName != ''){
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryShortName)){
					$('#ministryShortName_errorMsg').html("Short Name is invalid");
					return false;
				}
				}
				return true;
			});
		});
		function clearFormErrors(){
			$('#ministryName_errorMsg').html("");
			$('#ministryShortName_errorMsg').html("");
		}
</script>
<script type="text/javascript">
	function validateMinistryCorrection()
	{
		
		
		var errors = new Array();
		var error = false;


			errors[0] = vlidateOnblur('ministryName','1','15','c');


		  if(errors[0]==true){
			  error = true;
		  }
		
			if(error == true)
			{
			
			showClientSideError();
		
			return false;
			}
		else
			{
				return true;
			}
					
/* 		var msg=null;	
				if (!MinistryName()) {
					if(msg!=null)
					{
						msg=msg+"Ministry Name is Required"+ '\n';	
					}
					else
						{
						msg="Ministry Name is Required"+ '\n';	
						}
				}
										
				if(msg!=null)
					{
					alert(msg);
					return false;
				}
				else{
					
					return true;
				}
				
			return false; */
	}
	
/////////////////////////////////////////	
	function chkVillageOnLoad(){
		$("#ministryName_error").hide();
		$("#ministryNamecr_error").hide();
		$("#OrderNo_error").hide();
		$("#OrderDate_error").hide();
		$("#EffectiveDate_error").hide();
		$("#filGovernmentOrder_error").hide();
	}	
	

	</script>
</head>
<body onload='chkVillageOnLoad();'>

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<Div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><Div class="failur"></div></td>

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
					<td><div class="errorfont">
							<spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message>
						</div></td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>



	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><label><spring:message code="Label.MODIFYMINISTRY" htmlEscape="true"></spring:message></label>&nbsp;</h3>
			<ul class="listing">
				<%--//this link is not working <li>
					<a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form action="modifyMinistryAction.htm" method="POST" commandName="modifyMinistryCmd" id="frmModifyVillage" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyMinistryAction.htm"/>" />
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
						</div>
						<div class="block">
							<div  >
								<ul class="blocklist">
									<li>
										<label for="ministryName"><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message></label><span class="errormsg">*</span></br>
										<form:input path="ministryNamecr" class="frmfield" style="width: 300px;" id="ministryName" cssClass="frmfield"
										onfocus="validateOnFocus('ministryName');show_msg('ministryName')" onblur="vlidateOnblur('ministryName','1','15','c');" htmlEscape="true" maxlength="200" /> <%-- onfocus="show_msg('ministryName')" onblur="MinistryName()"--%> <span class="error"
										id="ministryName_error">Error.Ministrynamerequired<spring:message code="Error.Ministrynamerequired" htmlEscape="true"></spring:message></span> <form:errors path="ministryNamecr" class="errormsg" htmlEscape="true"></form:errors>
										<span class="errormsg" id="ministryName_errorMsg"></span>
										<div class="errormsg"></div>
									</li>
									<li>
										<label for="ministryshortName"><spring:message code="Label.MINISTRYSHORTNAME" htmlEscape="true"></spring:message></label> <br />
										<form:input path="shortministryName" class="frmfield" style="width: 120px" id="ministryshortName" cssClass="frmfield" htmlEscape="true" maxlength="10"/>
										<form:input path="orgCode" class="frmfield" type="hidden" htmlEscape="true"/> <form:input path="orgTypeCode" class="frmfield" type="hidden" htmlEscape="true" /> <form:input path="localBodySpecific" class="frmfield" type="hidden" htmlEscape="true" /> <form:input path="orgVersion"
										class="frmfield" type="hidden" htmlEscape="true"/> <form:errors path="shortministryName" class="errormsg" htmlEscape="true"></form:errors>
										<span class="errormsg" id="ministryShortName_errorMsg"></span>
										<div class="errormsg"></div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="btnpnl">
						<ul class="listing">
							<li>
								<label> <input type="submit" name="Submit" id="btnSave" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"  />
								</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:go('home.htm');" />
								</label>
							</li>
						</ul>
					</div>
					<div id='changevillage' class="frmpnlbg" style="visibility: hidden; display: none">
						<div class="frmtxt">
							<div class="frmhdtitle" visibility:hidden;>
								<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
							</div>
							<div class="block"  >
								<ul class="blocklist">
									<li>
										<label><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /> <label> <form:input path="ministryName" class="frmfield" id="ministryNamecr" cssClass="frmfield" onfocus="validateOnFocus('ministryNamecr');show_msg('ministryNamecr')" onblur="vlidateOnblur('ministryNamecr','1','15','c');" htmlEscape="true" />
										</label> <span class="error" id="ministryNamecr_error"><spring:message code="Error.Ministrynamerequired" htmlEscape="true"></spring:message></span> <form:errors path="ministryName" class="errormsg" htmlEscape="true"></form:errors>
										<div class="errormsg"></div>			
									</li>
									<li>
										<form:hidden id="requiredTitle" path="requiredTitle" htmlEscape="true" name="requiredTitle" value="<%=requiredTitle %>" />
										<form:hidden path="allowedFileType" id="allowedFileType" htmlEscape="true" name="allowedFileType" value="<%=allowedFileType%>" />
										<form:hidden path="allowedTotalFileSize" id="allowedTotalFileSize" htmlEscape="true" name="allowedTotalFileSize" value="<%=allowedTotalFileSize %>" />
										<form:hidden path="allowedIndividualFileSize" id="allowedIndividualFileSize" htmlEscape="true" name="allowedIndividualFileSize" value="<%=allowedIndividualFileSize %>" />
										<form:hidden path="uniqueTitle" id="uniqueTitle" name="uniqueTitle" htmlEscape="true" value="<%=uniqueTitle %>" />
										<form:hidden path="allowedNoOfAttachment" id="allowedNoOfAttachment" htmlEscape="true" name="allowedNoOfAttachment" value="<%=allowedNoOfAttachment %>" /> <input type="hidden" name="hiddenAllowedNoOfAttachment" id="hiddenAllowedNoOfAttachment" value="<%=allowedNoOfAttachment %>" />
										<form:hidden path="uploadLocation" id="uploadLocation" name="uploadLocation" htmlEscape="true" value="<%=uploadLocation %>" />
										<div class="errormsg">
											<c:if test="${! empty validationError}">
												<spring:message code='<c:out value="${validationError}" escapeXml="true"></c:out>' />
											</c:if>
										</div>
									</li>
									<li>
										<label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /> <label><form:input path="orderNo" id="OrderNo" type="text" class="frmfield" onfocus="validateOnFocus('OrderNo');" onblur="vlidateOnblur('OrderNo','1','15','c');" onkeypress="validateNumericAlphaKeys();" htmlEscape="true" /> </label> <span class="errormsg" id="OrderNo_error">Order No. is required</span> <form:errors path="orderNo" cssClass="errormsg" htmlEscape="true"></form:errors>
										<div class="errormsg"></div>
									</li>
									<li>
										<label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /> <form:input path="orderDate" id="OrderDate" type="text" class="frmfield" onfocus="validateOnFocus('OrderDate');" onblur="vlidateOnblur('OrderDate','1','15','c');" onchange="setEffectiveDate(this.value);" onkeypress="validateNumeric();" htmlEscape="true" /> <span class="errormsg" id="OrderDate_error">Order date is required</span>
										<div class="errormsg"></div> <form:errors path="orderDate" cssClass="errormsg" htmlEscape="true"></form:errors>
									</li>
									<li>
										<label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /> <form:input id="EffectiveDate" path="ordereffectiveDate" type="text" class="frmfield" onfocus="validateOnFocus('EffectiveDate');" onblur="vlidateOnblur('EffectiveDate','1','15','c');" onkeypress="validateNumeric();" htmlEscape="true" /> <span class="errormsg" id="EffectiveDate_error">Effective date is required</span>
										<div class="errormsg"></div> <form:errors path="ordereffectiveDate" cssClass="errormsg" htmlEscape="true"></form:errors>
									</li>
									<li>
										<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label><br /> <form:input id="GazPubDate" path="gazPubDate" type="text" class="frmfield" onkeypress="validateNumeric();" htmlEscape="true"/>
										<div class="errormsg"></div> <form:errors path="gazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors>
									</li>
									<li>
											<div id="titleId">
												<form:hidden id="showTitle" path="showTitle" name="showTitle" class="frmfield" htmlEscape="true" />
											</div>
											<div id="hiddentitle"></div>
											<br />
											<div id="hideAttachmentUtilDiv">
												<div id="fileId">
													<label><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message></label> <span class="errormsg">*</span> <br /> <input type='hidden' name='attachmentNumber' value='"<%=allowedNoOfAttachment%>"' /> <input
														type="file" name="attachedFile" id="filGovernmentOrder" class="multi frmfield" onblur="vlidateOnblur('filGovernmentOrder','1','15','c');" onfocus="validateOnFocus('filGovernmentOrder');" maxlength="<%=allowedNoOfAttachment%>"
														style="width: 280px" accept="<%=allowedFileType%>" onclick="{return holdTitle()}" ; /> <span class="errormsg" id="filGovernmentOrder_error">Please upload at least one file as Government Order</span>
													<form:errors path="attachedFile" cssClass="errormsg" htmlEscape="true"></form:errors>
												</div>
												<br /> <br />
												<div id="alreadyAttachedFiles">
													<c:if test="${! empty Attached_File_Meta_Data_List}">
														<table width="100%" class="tbl_with_brdr" align="center" id="tid">
															<tr class="frmhd">
																<!-- 	<spring:message code="addAttachment.tableheading" /> -->
															</tr>
															<tr class="tblRowTitle tblclear">
																<td><spring:message code="addAttachment.filetitle" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.filename" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.filesize" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.filecontenttype" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.fileflagfordeletion" htmlEscape="true" /></td>
															</tr>
															<c:forEach var="abc" items="${Attached_File_Meta_Data_List}">
	
																<tr id="row${abc.attachmentId}" class="tblRowB">
	
																	<td><c:out value="${abc.fileTitle }" escapeXml="true"></c:out></td>
																	<td><c:out value="${abc.fileName }" escapeXml="true"></c:out></td>
																	<td><c:out value="${abc.fileSize }" escapeXml="true"></c:out></td>
																	<td><c:out value="${abc.fileContentType }" escapeXml="true"></c:out></td>
	
																	<td width="10%"><a href="#"><img src="images/delete.png" name="deleteAttachment" width="18" height="19" border="0"
																			onclick="{hideThisRow(${abc.attachmentId}),holdAttachmentID(${abc.attachmentId}),holdFilePath('${abc.fileTimestamp}'),holdFileSize(${abc.fileSize })}" /> </a>
																		<div id="deleteID"></div>
																		<div id="deletePath"></div>
																		<div id="deleteFileSize"></div></td>
																</tr>

														</c:forEach>
													</table>
												</c:if>
												</div>
												</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="btnpnl">
							<ul>
								<li>
									<label> <input type="submit" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateMinistryChange();" />
									</label> <label> <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
  </div>
</body>
</html>