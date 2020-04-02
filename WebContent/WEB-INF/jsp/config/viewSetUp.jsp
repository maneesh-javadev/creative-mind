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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!--Expend and Collaps Section of Form-->
<script type="text/javascript" src="js/animatedcollapse.js"></script>
<link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<%-- <form:form action="" method="POST" commandName="addVillageNew"> --%>
	<div id="maincontainer" class="resize">
		</br>
		
	<c:if test="${commonTier.size()>0}">
			
			<div class="frmtxt">
				<div style="width: 132px; top: -9px; left: 12px" class="frmhdtitle">Tier Setup</div>


				<table width="100%" border="0" cellpadding="0" cellspacing="0">

					<tr>
						<td colspan="3" valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px">&nbsp;</td>
					</tr>
					<tr>
						<td valign="top" style="padding: 0px">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dfdfdf">
								<tr class="tblRowTitleBold">
								    <td align="left">Local Body Type</td>
									<td align="left">Nomenclature English</td>
									<td align="left">Nomenclature Local</td>
									<td align="left">Parent Local Body Type</td>
								</tr>


								<c:forEach var="commonTier" varStatus="listVillageDetailsRow"
									items="${addVillageNew.commonTier}">
									<tr class="tblRowB">
										<spring:bind htmlEscape="true"
											path="addVillageNew.commonTier[${listVillageDetailsRow.index}].nomenclatureEnglish">
											<td>
												<option name="${status.expression}" />
													<c:out value="${status.value}" escapeXml="true"></c:out>
												</option> </td>
										</spring:bind>
										<spring:bind htmlEscape="true"
											path="addVillageNew.commonTier[${listVillageDetailsRow.index}].localBodyTypeName">
											<td>
												<option name="${status.expression}" />
												<input type="text" value="<c:out value='${status.value}' escapeXml='true'></c:out>" />
<%-- 												<c:out value="${status.value}"></c:out> --%>
												</option></td>
										</spring:bind>
										<spring:bind htmlEscape="true"
											path="addVillageNew.commonTier[${listVillageDetailsRow.index}].nomenclature_local">
											<td><option name="${status.expression}" />
											<input type="text" value="<c:out value='${status.value}' escapeXml='true'></c:out>"/>
<%-- 												<c:out value="${status.value}"></c:out> --%>
												</option></td>
										</spring:bind>
										
										<spring:bind htmlEscape="true"
											path="addVillageNew.commonTier[${listVillageDetailsRow.index}].parentLocalBodyTypeCode">
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
						<td height="30" colspan="3" align="right">
						<table width="301" border="0" cellspacing="0" cellpadding="0">
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
									<td align="left">Local Body Type Name</td>
									<td align="left">Sub Type Name (In English)</td>
									<td align="left">Sub Type Name (In Local)</td>
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
	
	</br></br><a href="modify_lgsetup.htm">Modify Local Government Setup</a></br></br>

</body>
</html>