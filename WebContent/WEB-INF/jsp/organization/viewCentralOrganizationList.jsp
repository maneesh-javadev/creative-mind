<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>
<script type="text/javascript">

function manageOrganization(url,id) {
	dwr.util.setValue('organizationId', id, {	escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function getData() {
	var ministryName = document.getElementById('ddMinistry');
	var deptName = document.getElementById('ddDepartment');
	
	if(ministryName.value == "" || ministryName.value == "0") { 
		alert('Please select ministry');
		return false;
	} else if(deptName.value == "" || deptName.value == "0") { 
		alert('Please select depertment');
		return false;
	} else {
		document.forms['form1'].submit();
		return true;
	}
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

</script>
</head>
<body oncontextmenu="return false">
	<div id="frmcontent">
		<div class="frmhd">
					<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWORG"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      		<%--//these links are not working  <li>
					 				      					<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				      		
					 				      		</li>
					 				        
					 				        <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
		
		
			
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="vieworganizationbydepartmentcodeCenter.htm" id="form1" method="POST" commandName="viewDept">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="vieworganizationbydepartmentcodeCenter.htm"/>" />
				<div id="cat">
					<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
					<div class="frmpnlbg" id='showbycentrelevel'>
						<div class="frmtxt" align="center">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
							</div>
							
							<div >
									<ul class="blockklist">
												
												<li>
													<label><spring:message htmlEscape="true" code="Label.SELMIN"></spring:message></label><span class="errormsg">*</span><br />								
													<form:select path="ministryName" style="width: 150px" class="combofield" 	id="ddMinistry" onchange="getDepartmentListByMinistry(this.value)" htmlEscape="true">
													<form:option value="" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
													<form:options items="${userMinistryList}" itemLabel="orgName" itemValue="organizationPK.orgCode" htmlEscape="true"/>
													</form:select> <form:errors htmlEscape="true" path="ministryName" class="errormsg"></form:errors>
												</li>
												<li>
													<label><spring:message htmlEscape="true" code="Label.SELDEPT"></spring:message></label><span class="errormsg">*</span><br />
									
													<form:select path="deptName" style="width: 150px" class="combofield" id="ddDepartment" htmlEscape="true">
													<form:options items="${departmentList}" itemLabel="orgName" itemValue="organizationPK.orgCode" htmlEscape="true"/>
													</form:select>
												
												</li>
												<li></li>
												<li></li>
												
									</ul>
									
									<div class="btnpnl">
											<!-- modified by sushil -->
												<label>
										<input type="button" name="Submit" class="btn" onclick="getData();" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
										</label><label>
										<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='vieworganization.htm?<csrf:token uri='vieworganization.htm'/>';" />
										</label>
										<label>
										<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
										</label>
									
									
									
									</div>
								<div class="clear"></div>
							
							</div>
							
							
						</div>
					</div>
				</div>

				<!-- code starts of list organization -->
				<c:if test="${! isSearchPage}">
				<c:choose>
					<c:when test="${! empty  listOrganization}">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" align="center">
											<table class="tbl_with_brdr" width="98%" align="center">
												<tr class="tblRowTitle tblclear">
													<td width="5%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
													<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.ORGCODE"></spring:message></td>
													<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.ORGNAMEINENG"></spring:message></td>
													<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ORGSHORTNAMEINENG"></spring:message></td> --%>
													<%-- <td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.MINISTRYNAMEINENG"></spring:message></td> --%>
													<td colspan="6" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
												</tr>
												<tr class="tblRowTitle tblclear">

													<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></td>
													<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.MODIFY"></spring:message></td>
													<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></td>
													<%-- <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MAP"></spring:message></td> --%>
												</tr>

												<c:forEach var="listOrganization" varStatus="listOrganizationRow" items="${listOrganization}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listOrganizationRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${listOrganization.orgCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listOrganization.orgName}" escapeXml="true"></c:out>(<c:out value="${listOrganization.organizationType.orgType}" escapeXml="true"></c:out>)</td>
														<td width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageOrganization('viewOrganizationCenterDetail.htm',${listOrganization.orgCode});" width="18" height="19" border="0" /></a></td>
														<td width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageOrganization('modifyOrganizationCentral.htm',${listOrganization.orgCode});" width="18" height="19" border="0" /></a></td>
														<td width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageOrganization('DeleteOrganizationCenterDetail.htm',${listOrganization.orgCode});" width="18" height="19" border="0" /></a></td>
													</tr>
												</c:forEach>
												<form:input path="organizationId" type="hidden" name="organizationId" id="organizationId" />
											</table>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>

								</table>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<script>
									alert("No data found");
								</script>
					</c:otherwise>
				</c:choose>
				</c:if>
				<!-- code end of list organization -->
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>