<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
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
												<td><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message>
												</td>
												<%-- //this link is not working <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a> --%>
												</td>
											</tr>
										</table>
									</div>
									<div class="frmpnlbrdr">
										<form:form action="viewMinistryAction.htm" method="POST" commandName="viewMinistry">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewMinistryAction.htm"/>"/>
											<div class="frmpnlbg">
												<div class="frmtxt">
													<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.MINISTRYDETAIL"></spring:message>
													</div>
													<table width="100%" class="tbl_no_brdr">
														<c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${viewMinistry.listMinistry}">
															<tr>
																<td width="14%" rowspan="54">&nbsp;</td>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTCODE"></spring:message><br />
																	<label class="lblPlain"> <spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].orgCode">&nbsp;
																				 <c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div></td>
															</tr>
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].orgName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div></td>
															</tr>
															<tr>
																<td class="lblBold"><spring:message htmlEscape="true"  code="Label.DEPTSHORTNAMEINENG"></spring:message><br />
																	      <label class="lblPlain"> <spring:bind path="viewMinistry.listMinistry[${listMinistryDetailsRow.index}].shortName">&nbsp;
																				<c:out value="${status.value}" escapeXml="true"></c:out>
																		</spring:bind> </label>
																	<div class="errormsg"></div></td>
															</tr>
														

														</c:forEach>
													</table>
												</div>

												<div class="btnpnl">
													<table width="100%" class="tbl_no_brdr">
														<tr>
															<td>
<!-- BACK button removed - client requirement -->
												       <label><input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label>
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
		<script src="/LGD/JavaScriptServlet"></script>	
</body>
</html>