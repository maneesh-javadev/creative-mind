<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%!int pageNumber = 1;%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!--Expend and Collaps Section of Form-->
<script type="text/javascript" src="js/animatedcollapse.js"></script>
</head>
<link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet"
	type="text/css" />
<body>
	<form:form action="modifyLbSetup.htm" method="GET" commandName="lGSetupForm"> 
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLbSetup.htm"/>"/>
	<div id="maincontainer" class="resize">
		</br>
		
	<c:if test="${localBodySubtypeList.size()>0}">
			
			<div class="frmtxt" align="center">
				<div style="width: 132px; top: -9px; left: 12px" class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.VST"></spring:message></div>


				<table width="100%" border="0" cellpadding="0" cellspacing="0">

					<tr>
						<td colspan="3" valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px">&nbsp;</td>
					</tr>
					<c:if test="${localBodySubtypeList.size()>0}">
					<tr align="center">
						<td valign="top" style="padding: 0px">
						<table width="80%" class="tbl_with_brdr" >
								<tr class="tblRowTitleBold" align="left">
									<td align="left"><spring:message htmlEscape="true"  code="Label.STNE"></spring:message></td>
									<td align="left"><spring:message htmlEscape="true"  code="Label.STNL"></spring:message></td>
								</tr>


								<c:forEach var="current" varStatus="currentRow" items="${localBodySubtypeList}">
									<tr class="tblRowB" align="left">
										<td align="left"><c:out value="${current.subtypeNameEng}" escapeXml="true"></c:out></td>
	          							<td align="left"><c:out value="${current.subtypeNameLocal}" escapeXml="true"></c:out></td>
										
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					</c:if>
				</table>
			</div>
		</c:if>
		
			<c:if test="${subType.size()>0}">
			
			<div class="frmtxt">
				<div style="width: 132px; top: -9px; left: 12px" class="frmhdtitle">Sub Type</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="3" valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px"><table width="100%"
								border="0" cellpadding="0" cellspacing="1" bgcolor="#dfdfdf">
								<tr class="tblRowTitleBold">
									<td align="left"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPENAME"></spring:message></td>
									<td align="left"><spring:message htmlEscape="true"  code="Label.STNIE"></spring:message></td>
									<td align="left"><spring:message htmlEscape="true"  code="Label.STNIL"></spring:message></td>
								</tr>
								<c:forEach var="subType" varStatus="listVillageDetailsRow" items="${addVillageNew.subType}">

									<tr class="tblRowB">
										<spring:bind
											path="addVillageNew.subType[${listVillageDetailsRow.index}].localBodyTypeName">

											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>

										<spring:bind
											path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameEng">
											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>
										<spring:bind
											path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameLocal">
											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>
                                  	</tr>
								</c:forEach>
								<tr>

								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="30" colspan="3" align="right"><table width="301"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<!--  <td align="right" class="pageno">(1 - 50 of 464)</td>  -->
									<td width="100" align="right" class="pre"><a href="#">Previous</a>
									</td>
									<td width="1" align="center" class="pageno">|</td>
									<td width="50" align="right" class="nxt" style="padding: 0px"><a
										href="#">Next</a>
									</td>
									<td width="8" align="right" class="nxt" style="padding: 0px">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			
	</div>
	</c:if>
	<table>
			<tr>
			<td width="50%"></td>
<!-- BACK button removed - client requirement -->
				<label><input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'" id="btnClose" /> </label>
			<td width="40%"></td>
			</tr>
			</table>
	</br></br><!-- <input type="submit" Value="Modify" ></input> -->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!-- <a href="modify_lgsetup.htm">Modify Local Government Setup</a> --></br></br>
</form:form>
 <script src="/LGD/JavaScriptServlet"></script>
</body>
</html>