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
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalBodyService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" language="javascript">

function manageLocalGovt(url,id)
{
	dwr.util.setValue('localgovtId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit(); 
} 
</script>
</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<div id="frmcontent">
			 <div class="frmhd">
				<h3 class="subtitle">
					View Local Body Type
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
				<form:form action="viewlocalgovttype.htm" id="form1" method="POST" commandName="viewGovtType">
				
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="viewlocalgovttype.htm"/>" />
				
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message code="Label.SEARCHCRITERIA"></spring:message></div>
							 <ul class = "blocklist">
							 	<li>
									 <label for = "category"><spring:message  htmlEscape="true" code="Label.LOCALBODYCAT"></spring:message></label>
									 <span class="errormsg">*</span>
									<form:select path="category" class="combofield" id="category" htmlEscape="true" >
								                 <form:option value="N" label="Select" htmlEscape="true"/>
										         <form:option value="P" label="Panchayat" htmlEscape="true" />
										         <form:option value="T" label="Traditional" htmlEscape="true" />
										         <form:option value="R" label="Rural" htmlEscape="true"/>
										         <form:option value="U" label="Urban" htmlEscape="true" />
									</form:select>
								</li>
									<!-- correctly aligned no. of records label 22/05/2012 -->
								<!-- <td colspan="5" class="lblBold" align="right" > -->
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
									<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
										<form:option value="5" label="5" htmlEscape="true" />
										<form:option value="10" label="10" htmlEscape="true" />
										<form:option value="25" label="25" selected="selected" htmlEscape="true" />
										<form:option value="50" label="50" htmlEscape="true"/>
										<form:option value="100" label="100" htmlEscape="true" />
									</form:select>
								   </label>
								</li>
								<li>
									<label><input
											type="button" name="Submit" class="btn"
											onclick="excludeTopSelectAndSubmit('category');doSubmit('form1','Please select a local body type to be viewed');"
											value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
									</label> <label><input type="button" name="Submit2" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
											onclick="javascript:location.href='viewlocalgovttype.htm?<csrf:token uri='viewlocalgovttype.htm'/>';" />
									</label> <label><input type="button" name="Submit3" class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
									</label>
								</li>
							</ul>
						</div>
					</div>
			   </div>	
				
				<c:if test="${! empty SEARCH_GOVTTYPE_RESULTS_KEY}">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<!-- <table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%" align="center"> -->
								<ul class ="blocklist">
									<li>
									<table class="tbl_with_brdr" width="98%" align="center">
										<tr class="tblRowTitle tblclear">
											        <td rowspan="2"><spring:message code="Label.SNO"></spring:message></td>
											        <td width="20%" rowspan="2"><spring:message	code="Label.LOCALBODYTYPECODE"></spring:message></td>
											        <td width="31%" rowspan="2"><spring:message code="Label.LOCALBODYTYPE"></spring:message></td>
											        <td colspan="6" align="center"><spring:message code="Label.ACTION"></spring:message></td>
										</tr>
										
										<tr class="tblRowTitle tblclear">

											       <td width="6%" align="center"><spring:message code="Label.VIEW"></spring:message></td>
											       <td width="6%" align="center"><spring:message code="Label.MODIFY"></spring:message></td>
											       <!--<td width="6%" align="center"><spring:message code="Label.DELETE"></spring:message></td>
											       <td width="15%" align="center"><spring:message code="Label.GOVTORDER"></spring:message></td>-->
											       <td width="6%" align="center"><spring:message code="Label.HISTORY"></spring:message></td>
										</tr>
										
											<c:forEach var="listGovtTypeDetails" varStatus="listGovtTypeRow" items="${SEARCH_GOVTTYPE_RESULTS_KEY}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${listGovtTypeRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${listGovtTypeDetails.localBodyTypeCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listGovtTypeDetails.localBodyTypeName}" escapeXml="true"></c:out></td>
											<%-- 	<td align="center"><a href="viewLocalBodyTypeDetail.htm?id=${listGovtTypeDetails.localBodyTypeCode}&<csrf:token uri="viewLocalBodyTypeDetail.htm"/>"><img src="images/view.png" width="18" height="19" border="0" /></a></td>
											 --%>	
											 		<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageLocalGovt('viewLocalBodyTypeDetail.htm',${listGovtTypeDetails.localBodyTypeCode});" width="18" height="19" border="0" /></a></td>
													
			<%-- 									<td align="center"><a href="modifyLoacalGovTypebyId.htm?id=${listGovtTypeDetails.localBodyTypeCode}&<csrf:token uri="modifyLoacalGovTypebyId.htm"/>"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td>
			 --%>									
			 								 		<td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageLocalGovt('modifyLoacalGovTypebyId.htm',${listGovtTypeDetails.localBodyTypeCode});" width="18" height="19" border="0" /></a></td>
													<!--<td align="center"><a href="#"><img src="images/delete.png" width="18" height="19" border="0" /></a></td>
													<td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td>-->
										<%-- 		<td align="center"><a href="viewLocalBodyTypeHistory.htm?id=${listGovtTypeDetails.localBodyTypeCode}&<csrf:token uri="modifyLoacalGovTypebyId.htm"/>"><img src="images/history.png" width="18" height="19" border="0" /></a></td>
										 --%>		
										 	 		<td  width="8%" align="center"><a href="#"><img src="images/history.png" onclick="javascript:manageLocalGovt('viewLocalBodyTypeHistory.htm',${listGovtTypeDetails.localBodyTypeCode});" width="18" height="19" border="0" /></a></td>
												</tr>
											</c:forEach>
											<form:input path="localgovtId" type="hidden" name="localgovtId" id="localgovtId"  />											
									</table>
								<!-- </td>
							</tr>
						<tr>
							<td>&nbsp;</td>
						</tr> -->
						</li>
						
						<li>
						<!-- <tr>
								<td height="30" align="right"> -->
								<!-- <table width="301">
										<tr> -->
											<!-- <td width="99" align="right" class="pageno">(1 - 50 of 464)</td> -->
										<div width="96" align="right" class="pre">	<!-- <td width="96" align="right" class="pre"> --><a href="#"><spring:message htmlEscape="true" code="Label.PREVIOUS"></spring:message></a></div>
											<div width="24" align="center" class="pageno"><!-- <td width="24" align="center" class="pageno">|</td> --></div>
											<div width="51" align="right" class="nxt tblclear"><!-- <td width="51" align="right" class="nxt tblclear"> --><a href="#"><spring:message htmlEscape="true" code="Label.NEXT"></spring:message></a><!-- </td> --></div>
											<div width="16" align="right" class="nxt tblclear"><!-- <td width="16" align="right" class="nxt tblclear"> -->&nbsp;<!-- </td> --></div>
										<!-- </tr>
								</table></td> -->
						</li>
							<!-- </tr>
						  </table> -->
						  </ul>
					   </div>
				     </div>
				   </c:if>
				   
				   <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_GOVTTYPE_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<ul class = "blocklist">
								<li>
									<div align="center"><spring:message htmlEscape="true" code="error.NOLOCALBODYTYPEFOUND"></spring:message></div>
								</li>
							</ul>					
						</div>
				     </div>
				   </c:if>
			     </c:if>
				   
			   </form:form>
			   <script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
</body>
</html>

 