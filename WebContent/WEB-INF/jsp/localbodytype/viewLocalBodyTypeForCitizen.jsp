<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script type="text/javascript">
function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = 'viewLBTPagination.htm';
	document.forms['form1'].submit();
}

/* function go1(id)
{
	var url="viewlbtbystatecode.htm?id="+id;
	window.location=url;
	} */
dwr.engine.setActiveReverseAjax(true);
</script>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<div id="frmcontent">
			 <div class="frmhd">
				    <table width="100%" class="tbl_no_brdr">
					      <tr>
						             <td><spring:message htmlEscape="true"  code="Label.VIEWLOCALBODYTYPE"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						           <%--//this link is not working   <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
					     </tr>
				  </table>
		  </div>
			<div class="clear"></div>
			<div class="frmpnlbrdr">
				<form:form action="viewlocalgovttype.htm" id="form1" method="POST" commandName="viewGovtType">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewlocalgovttype.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt" align="center">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SEARCHCRITERIA"></spring:message></div>
							<table width="280" class="tbl_no_brdr">
								<tr>
									<%-- <td width="200" align="right" class="lblBold"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPECODE"></spring:message></td>
									<td><label><form:input type="text" class="frmfield" onkeypress="validateNumericKeys(event)" path="code" id="code" /> </label></td> --%>
				
									<td width="170" align="right" class="lblBold"><spring:message htmlEscape="true"  code="Label.LOCALBODYCAT"></spring:message></td>
										<td align="right">
											<form:select path="category" class="combofield" id="category">
										                 <form:option value="N" label="Select" />
												         <form:option value="P" label="Panchayat" />
												         <form:option value="T" label="Traditional" />
												         <form:option value="R" label="Rural" />
												         <form:option value="U" label="Urban" />
											</form:select></td>
								     </tr>
								    <tr>
									        <td>&nbsp;</td>
								   </tr>
								<tr>
	 <!-- correctly aligned no. of records label 22/05/2012 -->
	<td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td>
								</tr>

								<tr>
									<td height="50" colspan="4" align="center">
									                  <label><input type="button" name="Submit" class="btn" onclick="excludeTopSelectAndSubmit('category');checkTextInput('code');doSubmit('form1','Please select a local body type to be viewed');" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" /></label>
									                  <label><input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:go('viewlocalgovttype.htm');" /></label>
									                  <label><input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('home.htm');"/></label>
									</td>
								</tr>
							</table>
						</div>
					</div>
				   </div>	
				
				<c:if test="${! empty SEARCH_GOVTTYPE_RESULTS_KEY}">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%" align="center">
									<table class="tbl_with_brdr" width="98%" align="center">
										<tr class="tblRowTitle tblclear">
											        <td rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
											        <td width="20%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.LOCALBODYTYPECODE"></spring:message></td>
											        <td width="31%" rowspan="2"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></td>
											        <td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
										</tr>
										
										<tr class="tblRowTitle tblclear">

											       <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
											       <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
											       <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
											       <td width="15%" align="center"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
											       <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></td>
										</tr>
										
											<c:forEach var="stateWiseEntityDetails" varStatus="listGovtTypeRow" items="${SEARCH_GOVTTYPE_RESULTS_KEY}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${listGovtTypeRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${stateWiseEntityDetails.villageCode}" escapeXml="true"></c:out></td>
													<%-- <td align="left"><c:out value="${stateWiseEntityDetails.localBodyTypeName}"></c:out></td> --%>
													<td align="center"><a href="viewLocalBodyTypeDetail.htm?id=${stateWiseEntityDetails.villageCode}"><img src="images/view.png" width="18" height="19" border="0" /></a></td>
													<td align="center"><a href="modifyLoacalGovTypebyId.htm?id=${stateWiseEntityDetails.villageCode}"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td>
													<td align="center"><a href="#"><img src="images/delete.png" width="18" height="19" border="0" /></a></td>
													<td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td>
													<td align="center"><a href="viewLocalBodyTypeHistory.htm?id=${stateWiseEntityDetails.villageCode}"><img src="images/history.png" width="18" height="19" border="0" /></a></td>
												</tr>
											</c:forEach>										
									</table>
								</td>
							</tr>
						<%-- <tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
								<td height="30" align="right"><table width="301">
										<tr>
											<!-- <td width="99" align="right" class="pageno">(1 - 50 of 464)</td> -->
											<td width="96" align="right" class="pre"><a href="#"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
											<td width="24" align="center" class="pageno">|</td>
											<td width="51" align="right" class="nxt tblclear"><a href="#"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
											<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
										</tr>
								</table> --%>
								
								<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
									       <td  align="right"><table width="301" class="tbl_no_brdr">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										 </table>
									  </td>
								    </tr>
								
								
								</td>
							</tr>
						  </table>
					   </div>
				     </div>
				   </c:if>
				   
				   <c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_GOVTTYPE_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">					
							<tr>
								<td colspan="4" align="center"><spring:message htmlEscape="true"  code="error.NOLOCALBODYTYPEFOUND"></spring:message></td>
							</tr>					
						</table>
						</div>
				     </div>
				   </c:if>
			     </c:if>
				 <input type="hidden" name="direction" id="direction" value="0" />
			     <input type="hidden" name="pageType" value="L" />
			   </form:form>
			   <script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
</body>
</html>

