<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String allowedNoOfAttachment = "5";
	String location = "C:\\files";
	String allowedFileType = "gif,jpg,txt,docx,png";
	String allowedFileSize = "5";
	String title = "no";%>

<%!String contextPath;%>


<title>E-Panchayat</title>
<script src="js/jquery-1.7.js" type="text/javascript"
	language="javascript"></script>
<script src="js/jquery.MultiFile.js" type="text/javascript"
	language="javascript"></script>
<script src="js/attachedFiles.js" type="text/javascript"
	language="javascript"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script type="text/javascript" language="javascript"> 
function previewVillageGovtOrder()
{
	document.getElementById('GovtOrder').action='previewCreateNewVillage.htm';
	document.getElementById('GovtOrder').submit();
}
function validateSFile1(){

	if(document.getElementById('loadfile').value=="")
		{				
			document.getElementById("loadfile_error").innerHTML="Please upload at least one file as Government Order";
			$("#loadfile_error").show();			
			$("#loadfile").addClass("error_fld");
			$("#loadfile").addClass("error_msg");
			return false;

		}
	else 
	{
		$("#loadfile_error").hide();		
		return true;	
	}	
		
}
function validateGovtOrder()
{
	
	var msg=null;	
	
	if (!validateOrdeNo()) {
		
	msg="Please Enter Order Number"+ '\n';	
		
	}	
	if (!validateOrdeDate()) {
		if(msg!=null)
		{
			msg=msg+"Please Enter Order Date"+ '\n';	
		}
		else
			{
			msg="Please Enter Order Date"+ '\n';	
			}
	}
	if (!validateEffecDate()) {
		if(msg!=null)
		{
			msg=msg+"Please Enter Effective Date"+ '\n';	
		}
		else
			{
			msg="Please Enter Effective Date"+ '\n';	
			}
	}
	if (!validateGazPubDate()) {
		if(msg!=null)
		{
			msg=msg+"Please Enter Gazetted Publication Date"+ '\n';	
		}
		else
			{
			msg="Please Enter Gazetted Publication Date"+ '\n';	
			}
	}
	if (!validateSFile1()) {
		if(msg!=null)
		{
			msg=msg+"Please Upload at least one File"+ '\n';	
		}
		else
			{
			msg="Please Upload at least one File"+ '\n';	
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

return false;
} 

</script>

</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.UGO"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
					</td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
								code="Button.HELP"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form id="GovtOrder" commandName="addVillageNew"
				action="createpdf.htm" method="POST"
				enctype="multipart/form-data">
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" rowspan="20">&nbsp;</td>
									<td width="86%"><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message><span class="errormsg">*</span><br />
										<form:input path="orderNo" id="OrderNo" type="text"
											class="frmfield"
											onkeypress="validateNumericAlphaKeys();" /> <span
										class="errormsg" id="OrderNo_error"></span>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message><span class="errormsg">*</span><br />
										<form:input path="orderDate" id="OrderDate" type="text"
											class="frmfield" style="width: 120px"
											
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="OrderDate_error"></span></td>

								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message><span class="errormsg">*</span><br />
										<form:input htmlEscape="true" id="EffectiveDate" path="effectiveDate"
											type="text" class="frmfield" style="width: 120px"
											 onkeypress="validateNumeric();" /><span
										class="errormsg" id="EffectiveDate_error"></span>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message><span class="errormsg">*</span><br />
										<form:input id="GazPubDate" path="gazPubDate" type="text"
											class="frmfield" style="width: 120px"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="GazPubDate_error"></span>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<td><spring:message htmlEscape="true"  code="Label.SELGOT"></spring:message><br />
										<form:select htmlEscape="true"  path="templateList" id="templateList">
											<form:option value="0"><spring:message htmlEscape="true" code="Error.templateselectblock" ></spring:message></form:option>
											<form:options items="${templateList}"
												itemLabel="templateNameEnglish"
												itemValue="templateCode"></form:options>
										</form:select> <span class="errormsg" id="filGovernmentOrder_error"></span>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>

								</tr>

								<tr>

									<td><%-- <label><input type="submit" name="Submit2"
											class="btn" style="width: 80" onclick="return validateGovtOrder();"
											value="<spring:message htmlEscape="true"  code="Button.SAVEPUB"></spring:message>"
											id="btnSave" /> 
											</label> --%>
											<label><input type="submit" name="Submit2"
											class="btn" style="width: 80" onclick="return previewVillageGovtOrder();"
											value="<spring:message htmlEscape="true"  code="Button.NEXT"></spring:message>"
											id="btnSave" /> 
											</label>
											<%-- <c:if test="${fromCreateVillage=='fromCreateVillage'}"> 
											<label><input type="submit" name="Submit2"
											class="btn" style="width: 80" onclick="return previewVillageGovtOrder();"
											value="<spring:message htmlEscape="true"  code="Button.PREVIEW"></spring:message>"
											id="btnSave" /> 
											</label>
											</c:if> --%>
											<label> 
											<input type="button"
											name="Submit2" class="btn"
											value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
											id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
											</label>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>

								</tr>
							</table>
						</div>

					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>