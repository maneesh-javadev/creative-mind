<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>
    <body>
			<div id="frmcontent">
			<div class="frmhd">
				<h3 class="subtitle">
					<spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYHIST"></spring:message>
				</h3>
				<ul class="listing">
					<li>
						<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
					</li>
					<%--//this link is not working <li> 
						<a href="#" class="frmhelp"><spring:message code="Label.HELP"></spring:message></a>
					</li> --%>
				</ul>
			</div>
			<div class="clear"></div>
			<div class="frmpnlbrdr">	
			<c:if test="${! empty SEARCH_GOVTTYPE_HISTORY_KEY}">	
			  <div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPEHISTORY"></spring:message></div>
					   <table width="80%" class="tbl_with_brdr"> 
						 <tr>
							<td width="15%" align="center">
								<tr class="tblRowTitle tblclear">
									<td width="7%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPECODE"></spring:message></td>
									<td width="30%" rowspan="2"><spring:message htmlEscape="true"  code="Label.LOCALGOVTBODY"></spring:message></td>
									<td width="24%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CATEGORY"></spring:message></td>
									<td width="24%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVE"></spring:message></td>
								</tr>
								<tr>
									<td><br />
									</td>
								</tr> 
								
									<c:forEach var="listLocalBodyTypeHistory" varStatus="listLocalBodyTypeRow" items="${SEARCH_GOVTTYPE_HISTORY_KEY}">
										<tr class="tblRowB">
											<td width="6%"><c:out value="${listLocalBodyTypeRow.index+1}" escapeXml="true"></c:out></td>
											<td align="center"><c:out value="${listLocalBodyTypeHistory.localBodyTypeCode}" escapeXml="true"></c:out></td>
											<td align="left"><c:out value="${listLocalBodyTypeHistory.localBodyTypeName}" escapeXml="true"></c:out></td>
											<td><c:out value="${listLocalBodyTypeHistory.category}" escapeXml="true"></c:out></td>
											<td><c:out value="${listLocalBodyTypeHistory.isactive}" escapeXml="true"></c:out></td>
										</tr>
									</c:forEach>
								
								
								<%-- <c:if test="${empty SEARCH_GOVTTYPE_HISTORY_KEY}">
									<tr>
										<td colspan="1" align="left">No Results found</td>
									</tr>
								</c:if> --%>
								
								</td>
						    </tr>
					    </table>
					    
					    
					  
					    
					    
				     </div>
				      <div class="btnpnl">
							<!-- BACK button removed - client requirement -->
						 	<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
						</div>
					</div>
				</c:if>
				</div>
				
				  <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_GOVTTYPE_HISTORY_KEY}">
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<ul class = "listing">
									<li>
										<div colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></div>
									</li>
								</ul>
							</div>
						</div>
					</c:if>
				</c:if> 
			</div>
</body>
</html>

