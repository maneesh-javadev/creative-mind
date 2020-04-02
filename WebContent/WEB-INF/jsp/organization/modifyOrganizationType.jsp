<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrMinistryService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" language="javascript">

/* function validSearch()
{
	alert("hi");
	 var type=document.getElementById('orgType').value;
	var code=document.getElementById('code').value;
	alert(type);
	if(type!="" || type!=null)
		{
		
		for(var i=0;i<type.length;i++)
			{
			 var ch=type.charAt(i);
			 alert(ch);
			  if(((ch >= 65) && (ch <= 90)) || ((ch >= 97) && (ch <= 122)))
					 {
				 
					 }
			 else
				 {
				  alert("Organization Type must contain letters only!");
				  return false;
				 }
			}
		}
	
		
 	 if(code!="" || code!=null)
 		{
	 		
 		for(var i=0;i<type.length;i++)
		{
		 var ch=code.charAt(i);
		 if ((ch >= 48) && (ch <= 58) || (ch == 45))
				 {
			 
				 }
		 else
			 {
			  alert("Organization Type Code must contain Number only!");
			  return false;
			 }
		}
 		
 		
 		 
	  
	return true;
}
 */
function manageOrgType(url,id)
{
	
	 var conf=confirm('Are you sure to delete Organization Type');
	 if(conf==true)
		 {
		 dwr.util.setValue('orgTypeId', id, {	escapeHtml : false	});
			//document.getElementById('form1').method = "GET";
			document.getElementById('form1').action = url;
			document.getElementById('form1').submit();
		 }
		
		 
	
}


function editOrgType(url,id)
{
	dwr.util.setValue('orgTypeId', id, {	escapeHtml : false	});
	document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

/* function getData()
{
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
} */
</script>

</head>
<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">
	<div id="frmcontent">
	
	<div class="frmhd">
	<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWORGTYPE"></spring:message></h3>
		<ul class="listing">
			<li>
				<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
			</li>
			<%--//these links are not working  <li>
				<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
			</li>
			<li>
				<a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message></a>
			</li> --%>
		</ul>
	</div>
	
	
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="modifyOrganizationType.htm" id="form1" method="POST"
				commandName="orgTypeForm">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyOrganizationType.htm"/>" />
				
				<div id="cat">
				<c:if test="${empty organizationTypeDetails}">
					<div class="frmpnlbg">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
							</div>
							<br /> <br />

							<ul class="listing">
								<li>	
									<label for="code"><spring:message
												htmlEscape="true" code="Label.ORGTYPECODE"></spring:message>
									</label>
									
									
									<form:input type="text" id="code" htmlEscape="true"
												onkeypress="validateOnlyDigit(event)" path="code"
												class="frmfield" />  <form:errors path="code" htmlEscape="true"
											class="errormsg"></form:errors>
									</li>
									
									<li>
									<label for="orgType"><spring:message
												htmlEscape="true" code="Label.ORGTYPENAMEINENG"></spring:message>
									</label>
									
								
									
									
									<form:input type="text" htmlEscape="true"
												id="orgType" onkeypress="validateCharKeys(event)"
												path="orgType" class="frmfield" />  <form:errors htmlEscape="true"
											path="orgType" class="errormsg"></form:errors>
									</li>
									</ul>
									
									<div class="btnpnl">
									
											<input type="submit"  name="Submit" class="btn"  
											value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
									        <input type="button" name="Submit2" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
											onclick="javascript:location.href='modifyOrganizationType.htm?<csrf:token uri='modifyOrganizationType.htm'/>';" />
											<input type="button" name="Submit3" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									
									</div>
									
						</div>
					</div>
					</c:if>
				</div>

				<c:if test="${! empty organizationTypeDetails}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="16%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.ORGTYPECODE"></spring:message>
												</td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.ORGTYPENAMEINENG"></spring:message>
												</td>
												<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINLOCAL"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.MINISTRYSHORTNAMEINENG"></spring:message> --%>
												</td>
												<td colspan="2" align="center"><spring:message
														htmlEscape="true" code="Label.ACTION"></spring:message>
												</td>
											</tr>
											<tr class="tblRowTitle tblclear">

												<%-- <td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.VIEW"></spring:message>
												</td> --%>
												<td width="6%" align="center"><spring:message
														htmlEscape="true" code="Label.MODIFY"></spring:message>
												</td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
												<%-- <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MAP"></spring:message></td> --%>
											</tr>

											<c:forEach var="organizationTypeDetails" varStatus="OrgTypeRow"
												items="${organizationTypeDetails}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${OrgTypeRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${organizationTypeDetails.orgTypeCode}" escapeXml="true"></c:out>
													</td>
													<td align="left"><c:out
															value="${organizationTypeDetails.orgType}" escapeXml="true"></c:out>
													</td>
													
													          <%--  <td align="center"><a href="viewMinistryDetail.htm?id=${ministrydetail.orgCode}&<csrf:token uri="viewMinistryDetail.htm"/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
										
													<td width="8%" align="center"><a href="#"><img
															src="images/view.png"
															onclick="javascript:manageMinistry('viewMinistryDetail.htm',${ministrydetail.orgCode});"
															width="18" height="19" border="0" />
													</a>
													</td> --%>

													 <td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:editOrgType('editOrganizationType.htm',${organizationTypeDetails.orgTypeCode});" width="18" height="19" border="0" /></a>
													 
													</td>
															 <%--   <td align="center"><a href="modifyMinistry.htm?id=${ministrydetail.orgCode}&<csrf:token uri="modifyMinistry.htm"/>"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td> 
										
													<td width="8%" align="center"><a href="#"><img
															src="images/edit.png"
															onclick="javascript:manageMinistry('modifyMinistry.htm',${ministrydetail.orgCode});"
															width="18" height="19" border="0" />
													</a>
													</td> --%>


													<td align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageOrgType('deleteOrganizationType.htm',${organizationTypeDetails.orgTypeCode});" width="18" height="19" border="0" /></a></td>
														<%-- <td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td>
														<td align="center"><a href="viewBlockHistory.htm?id=${listBlockDetails.blockCode}"><img	src="images/history.png" width="18" height="19" border="0" /></a></td>
														<td align="center"><a href="#"><img src="images/map.png" width="18" height="19" /></a></td> --%>
												</tr>
											</c:forEach>
										 <form:input htmlEscape="true" path="orgTypeId" type="hidden" name="orgTypeId" id="orgTypeId" /> 
										</table></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

								<tr>
								<td>
								<table align="center"><tr><td> <label><input type="button" name="Submit3" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label></td></tr></table>
								</td>
								</tr>
						<%-- <tr>
									<td height="30" align="right"><table width="301" class="tbl_no_brdr">
											<tr>
												<!-- <td width="99" align="right" class="pageno">(1 - 50 of 464)</td> -->
												<td width="96" align="right" class="pre"><a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr> --%>

							</table>
						</div>
					</div>
				</c:if>

				<%-- <c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty ministrydetail}">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><spring:message
												htmlEscape="true" code="error.NOMINISTRYFOUND"></spring:message>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</c:if> --%>
			

			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div> 
	</div>
</body>
</html>

