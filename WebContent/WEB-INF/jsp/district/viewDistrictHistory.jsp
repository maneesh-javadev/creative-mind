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

<script type="text/javascript">
 
		</script>

</head>
<body >
		<div id="frmcontent">
			<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWDISTHIST"></spring:message></h3>
			<ul class="listing">
				<%--//this link is not working <li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message> </a></li> --%>
			</ul>
			</div>
			
			<div class="clear"></div>
			<div class="frmpnlbrdr">	
			<c:if test="${! empty districtHistory}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DISTRICTHISTORY"></spring:message></div>
							 <table width="100%" class="tbl_with_brdr">
								<tr>
									<td width="14%" align="center">
											     <tr class="tblRowTitle tblclear">
												         <td width="6%"  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												         <td width="16%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTCODE"></spring:message></td>
												         <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTVERSION"></spring:message></td>
												         <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
												          <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVEDISTRICT"></spring:message></td>
									                     <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDFROM"></spring:message></td>
									                   <%--   <td width="10%" rowspan="2"><spring:message htmlEscape="true"  code="Label.CREATEDBY"></spring:message></td> --%>
									                     <%-- <td width="30%" rowspan="2"><spring:message htmlEscape="true"  code="Label.FLAGCODE"></spring:message></td> --%>
												</tr>
											<tr><td><br/></td></tr>
											
								    								    
									         <c:forEach var="districtHistoryDetail" varStatus="listDistrictRow" items="${districtHistory}">
												  <tr class="tblRowB">
														<td width="6%"><c:out value="${listDistrictRow.index+1}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtHistoryDetail.districtCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtHistoryDetail.districtVersion}" escapeXml="true"></c:out></td>														
														<td align="left"><c:out value="${districtHistoryDetail.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtHistoryDetail.active}" escapeXml="true"></c:out></td>
														
									                    <td align="left"><c:out value="${districtHistoryDetail.lrReplaces}"></c:out></td>
									                  <%--   <td align="left"><c:out value="${districtHistoryDetail.lrReplacedby}"></c:out></td> --%>
									                    <%-- <td align="left"><c:out value="${districtHistoryDetail.flagCode}"></c:out></td> --%>
												  </tr>
											</c:forEach>
								   									
								 </td>
							  </tr>
 						   </table>
						</div>
				       <div class="btnpnl">
				       	<div class="block">
				       		<ul class="blocklist">
				       			<li>
				       				<label> <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose"/> </label>
				       			</li>
				       		</ul>
				       	</div>
				    </div>
			     </div>
			</c:if>
		</div>
		
		 <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty districtHistory}">
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
							<div class="block">
					       		<ul class="blocklist">
					       			<li>
					       				<spring:message htmlEscape="true" code="Error.noresult"></spring:message>
					       			</li>
					       		</ul>
				    	   	</div>
						</div>
					</div>
				</c:if>
			</c:if> 
		</div>

</body>
</html>

