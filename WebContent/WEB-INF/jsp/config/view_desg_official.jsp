<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%!int pageNumber = 1;%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayati</title>
<!--Expend and Collaps Section of Form-->
<script type="text/javascript" src="js/animatedcollapse.js"></script>
</head>
<link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet"
	type="text/css" />
<body>
	<%-- <form:form action="" method="POST" commandName="addVillageNew"> --%>
	<div id="maincontainer" class="resize">
		</br>	
	
	<c:if test="${designationOfficial.size() > 0}">
	<%-- <c:if test="${fn:length(listVillageDetailsRow) < 0}">    --%>
	 <div class="frmtxt">
				<div style="width: 132px; top: -9px; left: 12px" class="frmhdtitle">Designation</div>


				<table width="100%" class="tbl_no_brdr">

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
									<td align="left">Nomenclature English</td>
									<td align="left">Designation Name</td>
									<td align="left">Designation Name Local</td>
									<td align="lefts">Is Top Designation</td>
									<td align="lefts">Is Multiple</td>
								</tr>


								<c:forEach var="designationOfficial"
									varStatus="listVillageDetailsRow"
									items="${addVillageNew.designationOfficial}">

									<tr class="tblRowB">
										<spring:bind htmlEscape="true"
											path="addVillageNew.designationOfficial[${listVillageDetailsRow.index}].NomenclatureEnglish">

											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>

										<spring:bind htmlEscape="true"
											path="addVillageNew.designationOfficial[${listVillageDetailsRow.index}].DesignationName">
											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>
										<spring:bind htmlEscape="true"
											path="addVillageNew.designationOfficial[${listVillageDetailsRow.index}].DesignationNameLocal">
											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>
										<spring:bind htmlEscape="true"
											path="addVillageNew.designationOfficial[${listVillageDetailsRow.index}].IsTopDesignation">
											<td>
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></td>
										</spring:bind>
										<spring:bind htmlEscape="true"
											path="addVillageNew.designationOfficial[${listVillageDetailsRow.index}].IsMultiple">
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
								class="tbl_no_brdr">
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
		<c:if test="${subType==null}">
		<c:if test="${commonTier==null}">
	</br></br><a href="designation_hierarchy_panchayat.htm">Add Designation Elected</a></br></br>
	</c:if>
	</c:if>
	
</body>
</html>