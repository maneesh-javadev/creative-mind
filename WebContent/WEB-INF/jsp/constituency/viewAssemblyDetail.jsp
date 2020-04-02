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
<body>

	<div id="frmcontent">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td width="100%" valign="top" class="tblclear">
					<div class="frmhd">
						<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.ASSCONSTITUENCYDETAIL"></spring:message></h3>
						<%-- //this link is not working <ul class="listing">
							<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message> </a></li>
						</ul> --%>
					</div>
					
					<div class="frmpnlbrdr">
						<form:form action="modifySubDistrictAction.htm" method="POST" commandName="modifyassemblyConstituencyCmd" id="frmModifySubDistrict">
							<div class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true"  code="Label.ASSCONSTITUENCYDETAIL"></spring:message>
									</div>
									<c:forEach var="Parliamentconstituencymodify" varStatus="listSubdistrictDetailsRow" items="${modifyassemblyConstituencyCmd.listAssemblyFormDetail}">
										<div   class="block">
											<ul class="blocklist">
												<li>
													<spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCYNAMEINENG"></spring:message><br />
														<label class="lblPlain"> <spring:bind
																path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].acNameEnglish">&nbsp;
																					<c:out value="${status.value}" escapeXml="true"></c:out>
															</spring:bind> </label>
														<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCYNAMEINLOCAL"></spring:message><br />
														<label class="lblPlain"> <spring:bind
																path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].acNameLocal">&nbsp;
												                                     <c:out
																	value="${status.value}" escapeXml="true"></c:out>
															</spring:bind> </label>
														<div class="errormsg"></div>
												</li>
												<li>
													<spring:message htmlEscape="true" code="Label.ECICODE"></spring:message><br /> <label class="lblPlain"> <spring:bind path="modifyassemblyConstituencyCmd.listAssemblyFormDetail[${listSubdistrictDetailsRow.index}].eciCode">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
													</spring:bind> </label>
												</li>
											</ul>
										</div>
									</c:forEach>
								</div>
								
								<div class="btnpnl">
									<ul class="listing">
										<li>
											<label> <input type="button" name="Close"
											class="btn"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											id="btnClose"
											onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
											</label>
										</li>
									</ul>
								</div>
							</div>
						</form:form>
					</div>
				</td>
			</tr>
		</table>

	</div>
</body>
</html>