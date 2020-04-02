<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<title><spring:message code="Label.VIEWLOCALGOVTBODYDET" htmlEscape="true"></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body onload="loadviewPage();">
		<div id="frmcontent">
			<div class="frmhd">
				 <table width="100%" class="tbl_no_brdr">
                        <tr>
                                <td><spring:message code="Label.SEARCH" htmlEscape="true"></spring:message></td>
                                <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
<%--          //this link is not working    <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a></td>
 --%>                       </tr>
				</table>
			</div>
			<div class="clear"></div>
			<div class="frmpnlbrdr">
				<form:form action="viewlocalgovtbody.htm" method="POST" commandName="localGovtBodyForm">	
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="viewlocalgovtbody.htm"/>" />
				    <div id="cat">	
					   <div class="frmpnlbg">
						  <div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message code="Label.SEARCHLOCALGOVTBODY" htmlEscape="true"></spring:message></div><br/><br/>
							
							  <table width="800" class="tbl_no_brdr">
								<tr>
										<td width="125" class="lblBold"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message>
										<form:select path="localBodyTypeCode" class="combofield" id="ddLBTypeCode">	
										<form:option value="0" class="lblBold"><spring:message code="Label.SELLOCALGOVTBODY" htmlEscape="true"></spring:message></form:option>					
										<form:options  items="${lgtLocalBodyType}" itemValue="localBodyTypeCode" itemLabel="nomenclatureEnglish"></form:options>
											
										</form:select>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<td height="50" colspan="4" align="center"><label>
											<input type="submit"  name="Submit"  class="btn" value="<spring:message code="Button.GETDATA" htmlEscape="true"></spring:message>" onclick="return validateViewLocalBody();"/> </label><label>
								</tr>
							</table>
						</div>
					</div>
					</div>
					</form:form>	
					
						
					<div class="frmpnlbg">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
												<td width="15%" rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></td>
												<td colspan="21%" rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></td>
                                                <td colspan="15%" rowspan="2">No. Of Child</td>
                                                <td width="6%"><spring:message code="Label.GETCHILD" htmlEscape="true"></spring:message></td>
											</tr>
 
							
										
								<c:if test="${! empty LocalGovtBodyList}">
									<c:forEach var="lgdLocalGovtBodyList" varStatus="listLocalBodyRow" items="${LocalGovtBodyList}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
														 <td><c:out value="${lgdLocalGovtBodyList.localBodyCode}" escapeXml="true"></c:out></td> 
														<td align="left"><c:out value="${lgdLocalGovtBodyList.localBodyNameEnglish}" escapeXml="true"></c:out></td>
														<%-- <td align="left"><c:out value="${lgdLocalGovtBodyList.districtNameLocal}"></c:out></td> --%> 
														<td align="center"><a href="viewChildLocalBody.htm?id=${lgdLocalGovtBodyList.localBodyTypeCode}"><img	src="images/gvt.order.png" width="18" height="19" border="0" /></a></td>
														<!-- <td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td> -->

													</tr>
												</c:forEach>
											</c:if> 
											
										</table>
									</td>
								</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
 						<c:if test="${empty LocalGovtBodyList}">
								<tr>
									<td colspan="5" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
								</tr>
								</c:if> 
								<tr>
									<td height="30" align="right"><table width="301" class="tbl_no_brdr">
											<tr>
												<!-- <td width="99" align="right" class="pageno">(1 - 50 of 464)</td> -->
												<td width="96" align="right" class="pre"><a href="#"><spring:message code="Button.PREVIOUS"  ></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#"><spring:message code="Button.NEXT"  ></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div> 
			</div>
		</div>
</body>
</html>

