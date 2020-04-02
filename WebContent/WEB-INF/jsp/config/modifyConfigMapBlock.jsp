<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script src="js/configmap.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>


	<title><spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
	</title> <script type="text/javascript">
		var checkedRadio;
		function ClearRd(chkRds, idname, chkRdu) {
     
			var chkRd = document.getElementById(chkRds);
			var chkRd1 = document.getElementById(chkRdu);

			if (chkRd.checked) {
				document.getElementById(idname).disabled = false;
				document.getElementById(idname).style.display = 'block';
				document.getElementById(idname).style.visibility = 'visible';
				//		document.getElementById('blockbaseurl_error').style.display='block';
				

			} else if (chkRd1.checked) {
				document.getElementById(idname).disabled = true;
				document.getElementById(idname).style.display = 'none';
				document.getElementById(idname).style.visibility = 'hidden';
				 document.getElementById('blockbaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('blockbaseurl_error').style.display = 'none';

			}
		}

		
		
function validateblockAll()
{
	var obj=document.getElementById('blockupload');	
	
	var patternu;
	patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	
//alert("mid"+val);
	var flag=true;
		if(obj.checked)
		{
			document.getElementById('blockbaseurl').style.visibility = 'hidden';
			document.getElementById('blockbaseurl').style.display = 'none';
			 document.getElementById('blockbaseurl_error').style.visibility = 'hidden';	
			 document.getElementById('blockbaseurl_error').style.display = 'none';
			
		}
	else
		{
		//alert("me");
		//alert(document.getElementById('baseUrl_' + pos).value);
		 if(document.getElementById('blockbaseurl').value=="")
			 {
			 //alert("inside");Base URL is Required
			  document.getElementById('blockbaseurl_error').innerHTML="Base URL is Required";
			 document.getElementById('blockbaseurl_error').style.visibility = 'visible';	
			 document.getElementById('blockbaseurl_error').style.display = 'block';
			
			 flag=false;
			   
			 }
		 else
			 {
			
			 if(!patternu.test(document.getElementById('blockbaseurl').value))
			 {
				 document.getElementById('blockbaseurl_error').innerHTML="Base URL is not proper format";
				 document.getElementById('blockbaseurl_error').style.visibility = 'visible';	
				 document.getElementById('blockbaseurl_error').style.display = 'block';
				 flag=false;
			 }
			 else
				 {
				 document.getElementById('blockbaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('blockbaseurl_error').style.display = 'none';
				 }
			 }
		}
		
		
	
	return flag;	
	}
/* function loadPage() {
			
			document.getElementById('blockbaseurl').disabled = true;
		
			ClearAll();
		} */
		
		function ClearAll() {

			
			ClearRd('blockseperateserver','blockbaseurl','blockupload');

		}
		
		
		if ( window.addEventListener ) { 
		    window.addEventListener( "load", ClearAll, false );
		 }
		 else 
		    if ( window.attachEvent ) { 
		       window.attachEvent( "onload", ClearAll );
		 } else 
		       if ( window.onLoad ) {
		          window.onload = ClearAll;
		 } 
		

		 function doSubmit()
		 {
		 document.getElementById('savebtn').disabled=true;
		 document.forms['form1'].submit();
		 } 
	</script>
</head>
<body > <!-- onload="ClearAll();"> -->

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont">
							<spring:message htmlEscape="true"  code="error.blank.commonAlert"></spring:message>
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



	<div class="clear"></div>
	<div class="frmhd">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></td>
				<td>&nbsp;</td>
				<%-- //this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
							code="Label.HELP"></spring:message> --%>
				</a>
				</td>

			</tr>
		</table>
	</div>
	<div class="frmpnlbrdr">
		<form:form action="modifyBlockUpdate.htm" id="form1" method="POST"
			commandName="modifyconfigMapCmd">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="modifyBlockUpdate.htm"/>" />
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message>
					</div>

					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td width="14%">&nbsp;</td>
							<td width="86%" height="30" valign="top" class="lblBold">
									<%-- <spring:message htmlEscape="true"  code="Label.TYPEOFLANDREGION "></spring:message> </label> --%>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><c:forEach var="viewConfigMapLandRegion"
									varStatus="administratorUnitRow"
									items="${modifyconfigMapCmd.viewConfigMapLandRegion}">
									<table width="800" class="tbl_no_brdr">
										<tr>
											<c:if
													test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'B')}">
											<td width="100">
													<label> <spring:message htmlEscape="true"  code="Label.BLOCK"></spring:message>
													</label>
											</td>
											<td width="20" align="right"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="blockupload" 
														name="<c:out value="${status.expression}" escapeXml="true"/>" value="true"
														<c:if test="${status.value == true}">checked</c:if>
														onclick="ClearRd('blockseperateserver','blockbaseurl','blockupload');" />
												</spring:bind></td>
											<td width="100"><label> <spring:message htmlEscape="true" 
														code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td width="20" align="right"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="blockseperateserver" 
														name="<c:out value="${status.expression}" escapeXml="true"/>" value="false"
														<c:if test="${status.value == false}">checked</c:if>
														onclick="ClearRd('blockseperateserver','blockbaseurl','blockupload');" />
												</spring:bind></td>
											<td width="150"><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
													<input type="text" id="blockbaseurl" class="frmfield"
														name="<c:out value='${status.expression}'/>"
														value="<c:out value='${status.value}' escapeXml="true"/>"
														maxlength="100"
														onfocus="validateOnFocus('blockbaseurl');" 
														onblur="vlidateOnblur('blockbaseurl','1','15','c');"
														 />
													<!-- onfocus="show_msg('blockbaseurl')" onblur="blockmap()" -->
													<div class="errormsg" id="blockbaseurl_error" style="color: red;"> </div>
												</spring:bind> <spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind>
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind>
											</td>
											<form:errors path="isbaseUrl" cssClass="error" />
											</c:if>
										</tr>
										<tr>
											<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}">
												<td>
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].configurationMapLandregionCode">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind>
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
												</spring:bind>
												</td>
											</c:if>
										</tr>
									</table>
								</c:forEach></td>
						</tr>
					</table>
					<div class="errormsg"></div>
				</div>
			</div>
			<div class="btnpnl">
				<label> <input type="submit" id="savebtn" class="btn"
					name="Submit"
					value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"
					onclick="return validateblockAll();" /> </label> <label> <input
					type="submit" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
			</div>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</body>
</html>