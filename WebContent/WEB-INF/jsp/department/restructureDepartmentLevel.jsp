<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@ include file="restructureDeptLevelJs.jsp"%>

<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->

</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message htmlEscape="true" code='${formTitle}'></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>

		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<c:if test="${!empty deptList }">
				<form:form commandName="restructureDeptLevelForm" action="rebuildSetupAdminDepartment.htm" method="POST" id="deptform">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rebuildSetupAdminDepartment.htm"/>" />
					<form:hidden path="organizationFlow" />
					<div id="cat">
						<div class="frmpnlbg" id='showbystatelinelevel'>
							<div class="frmtxt">
								<div class="frmhdtitle">
									<%-- <spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message> --%>
								</div>

								<div>
									<ul class="blocklist">
										<li><label for="deptId"><spring:message htmlEscape="true" code="${selectCreteria}" /></label><span class="errormsg">*</span> <form:select path="olc" class="combofield" id="deptId">
												<form:option selected="selected" value="" label="select" />
												<form:options items="${deptList}" itemValue="olc" itemLabel="orgName"  />
											</form:select></li>
									</ul>

								</div>
							</div>
						</div>
						<div class="btnpnl">
							<input type="button" id="btnGet" value="<spring:message htmlEscape="true"  code="Button.GET"/>" onclick="getDeptSetup();" /> <input type="button" id="close" value="<spring:message htmlEscape="true"  code="Button.CLOSE"/>"
								onclick="javascript:go('home.htm');" />
						</div>
					</div>
				</form:form>
			</c:if>
			<c:if test="${empty deptList }">
				<form:form id="adminOrgDeptForm" commandName="restructureDeptLevelForm">
					<input type="hidden" id="hierarchySequence" name="hierarchySequence" />
					<form:input type="hidden" path="adminUnitCodes" id="adminUnitCodes" />
					<input type="hidden" name="olc" value="<c:out value='${restructureDeptLevelForm.olc}' escapeXml='true'></c:out>"/>
					<input type="hidden" name="organizationFlow" value="<c:out value='${restructureDeptLevelForm.organizationFlow}' escapeXml='true'></c:out>"/>
					<div class="frmpnlbg" style="background: #FFFFFF;">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message htmlEscape="true" code="${extendHierarchy}" /></label>
							</div>
							<div>
								<div class="container1">
									<div class="inner_left">
										<ul class="blocklist" style="visibility:hidden">

											<li><label><spring:message htmlEscape="true" code="Label.parentUnitLevels" /></label> </br/> <select id="adminLevelNameLocal" name="adminLevelNameLocal" 
											class="combofield" style="width: 200px; visibility:hidden" onChange="getChildofExistingParent(this.value,0)">
													<option value="-1">Select</option>
													<optgroup id="parentLRUnitLevel" label="Land Regions Unit Levels">
														<c:forEach var="parentLRUnitLevel" items="${parentLandRegionUnitLevels}">
															<option value="${parentLRUnitLevel.adminUnitCode}"><c:out value="${parentLRUnitLevel.adminLevelNameEng}" escapeXml="true"></c:out></option>
														</c:forEach>
													</optgroup>
													<optgroup id="parentAdUnitLevel" label="Local Body Codes">
														<c:forEach var="localBodyCode" items="${localBodyCodes}">
															<option value="${localBodyCode.adminUnitCode}"><c:out value="${localBodyCode.adminLevelNameEng}" escapeXml="true"></c:out></option>
														</c:forEach>
													</optgroup>
													<optgroup id="parentAdUnitLevel" label="Administrative Unit Levels">
														<c:forEach var="parentAdUnitLevel" items="${parentAdminUnitLevels}">
															<option value="${parentAdUnitLevel.adminUnitCode}"><c:out value="${parentAdUnitLevel.adminLevelNameEng}" escapeXml="true"></c:out></option>
														</c:forEach>
													</optgroup>
													
											</select> <!-- <input  type="checkbox" id="chk" title="Check to add as Parent" checked="checked" disabled="disabled" style="display: none;"/> --> <br></br>
												<ul id="browser" class="filetree"></ul></li>
											<li><label><spring:message htmlEscape="true" code="Label.UnitLevels" />
											</label> <br/> 
											<select id="adminLevelNameLocalNew" name="adminLevelNameLocalNew" class="combofield" style="width: 200px;">
													<option value="-1">Select</option>
													 <optgroup id="landRegionUnits" label="Land Regions Unit Levels">
														
													</optgroup>
													<optgroup id="adminUnits" label="Administrative Unit Levels">
															
													</optgroup> 
													<optgroup id="localBody" label="Local Body">
															
													</optgroup>   
											</select>
											 <%-- <br></br> <input type="button" id="btnBuildHrchy" onclick="addIteminHierarchy();" value="<spring:message htmlEscape="true"  code="${extendHierarchy}"/>" /> --%>



												<ul id="browser" class="filetree"></ul></li>


										</ul>
										<%-- <input type="button" id="btnBuildHrchy" onclick="addIteminHierarchy();" value="<spring:message htmlEscape="true"  code="${extendHierarchy}"/>" />  --%>
										
									</div>
									<div class="inner_right">
										<div class="tree" id="base"></div>
									</div>
									<div class="clear"></div>
								</div>

							</div>
							<div style="display:none;">
							    <div id="demo2_tip">
							       
							    </div>
							</div>

							<br />
						</div>
					</div>
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code='${deptOrgOperationalAt}' />
							</div>

							<ul class="blocklist">
								<li>
								 <c:if test="${! empty stateWiseAdminUnit}">
										<c:set var="tempString" value="{" />
										<c:forEach var="stateWiseAdminUnit" items="${stateWiseAdminUnit}">
											<c:set var="tempString" value='${tempString}"${stateWiseAdminUnit.adminUnitCode}":${stateWiseAdminUnit.parentAdminCode},' />
										</c:forEach>
										<c:set var="tempString" value="${fn:substring(tempString, 0, fn:length(tempString)-1)}" />
										<c:set var="tempString" value="${tempString}}" />
										<script type='text/javascript'>createStateWiseAdminUnits('${tempString}');</script>
									</c:if>
									
									<!--default tree view on start page ---- -->
									
								<c:if test="${! empty existingDeptSetup}">
								
										<div id="hierarchyCheckBox">
										
											<div id="hierarchyData"class="container"">
										
											  </div>
											<c:set var="existAdminCode" value="{" />
											<c:forEach var="existingDeptSetup" items="${existingDeptSetup}">
												<c:set var="existAdminCode" value='${existAdminCode}"${existingDeptSetup.adminUnitCode}":${existingDeptSetup.parentAdminCode},' />
												<%-- <input type="checkbox" name="updateHierarchy" value="<c:out value='${existingDeptSetup.adminUnitCode}' escapeXml='true'></c:out>"/>&nbsp;
															 <c:out value="${existingDeptSetup.adminLevelNameEng}" /> --%>
															
											
											<script type='text/javascript'>reBuildHierarchy('${existingDeptSetup.adminUnitCode}','${existingDeptSetup.adminLevelNameEng}','${existingDeptSetup.slc}','${existingDeptSetup.hierarchy}', '${existingDeptSetup.localBodyLevel}','${existingDeptSetup.orgLocatedLevelCode}'); </script>
												<!-- <script type='text/javascript'> addDataInHierarchyList('${existingDeptSetup.adminUnitCode}','${existingDeptSetup.adminLevelNameEng}','${existingDeptSetup.hierarchy}');	</script> -->
											<!-- 	<script type='text/javascript'> createGraph('${existingDeptSetup.orgLocatedLevelCode}','${existingDeptSetup.adminLevelNameEng}','${existingDeptSetup.parentOrgLocatedLevelCode}','${existingDeptSetup.adminUnitCode}');</script> -->
											</c:forEach>
											
											
											<c:set var="existAdminCode" value="${fn:substring(existAdminCode, 0, fn:length(existAdminCode)-1)}" />
											<c:set var="existAdminCode" value="${existAdminCode}}" />
											<script type='text/javascript'>createExistAdminUnit('${existAdminCode}');</script>
										</div>
									</c:if></li>
								

							</ul>
							<div class="clear"></div>
						</div>
					</div>

					<div class="btnpnl">
						<input type="button" id="btnCreateDept" value="<spring:message htmlEscape="true" code="${extendEntityButton}"/>" onclick="return validateFormadminOrgDeptForm();" />
						<%--  <input type="button" id="clear" value="<spring:message htmlEscape="true"  code="Button.CLEAR"/>" onclick="return submitFormPostMethod('rebuildSetupAdminDepartment');" />  --%>
							<input type="button" id="close" value="<spring:message htmlEscape="true"  code="Button.CLOSE"/>"	onclick="javascript:go('home.htm');" />


					</div>
					
					<div id="actionDiv" style="display: none;">
							<div id="nestActionDiv">
								<img
									src='<%=contextpthval%>/images/close.png'
									alt="close" title="Close" id="close" align="right" style="margin-right: -10px;margin-top: -5px;"
									onclick="closeAction()" /><br></br>

								<div style="padding-left: 20px; padding-top: 10px;"
									align="center">
									
									<div style="width: 230px; height: 350px; overflow-x: auto;">
										<table id="workingTable" style="width: 100%">
											<thead>
												<tr>
													<th align="left"><h1>Unit Levels</h1></th>
													
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<!-- <div style="position: absolute; bottom: 0; right: 40px;">
									<input type="button" value="Close" onclick="closeAction()" />
								</div> -->
							</div>
						</div>

				</form:form>
				<%--  <c:if test="${! empty existingDeptSetup}">
				<c:forEach var="existingDeptSetup" items="${existingDeptSetup}">
				<script type='text/javascript'>reBuildHierarchy('${existingDeptSetup.adminUnitCode}','${existingDeptSetup.adminLevelNameEng}','${existingDeptSetup.slc}','${existingDeptSetup.parentAdminCode}'); </script>
			</c:forEach>
			
			</c:if> --%>
			</c:if>
		</div>
	</div>
</body>

<script src="js/MultiNestedList.js"></script>
<link rel="stylesheet" href="css/myStyleCss.css">
</html>
<style >

.anchortag{
margin: 15px;
font-size:12px;

}
.headertd{
margin:25px 0px 2px 0pxpx;
	font-size:14px;
	color:black;
}
 .myButton {
	-moz-box-shadow: 3px 4px 0px 0px #899599;
	-webkit-box-shadow: 3px 4px 0px 0px #899599;
	box-shadow: 3px 4px 0px 0px #899599;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ededed), color-stop(1, #bab1ba));
	background:-moz-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:-webkit-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:-o-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:-ms-linear-gradient(top, #ededed 5%, #bab1ba 100%);
	background:linear-gradient(to bottom, #ededed 5%, #bab1ba 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed', endColorstr='#bab1ba',GradientType=0);
	background-color:#ededed;
	-moz-border-radius:15px;
	-webkit-border-radius:15px;
	border-radius:15px;
	border:1px solid #d6bcd6;
	display:inline-block;
	cursor:pointer;
	color:black;
	font-family:Arial;
	font-size:15px;
	padding:7px 25px;
	text-decoration:none;
	text-shadow:0px 1px 0px #e1e2ed;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #bab1ba), color-stop(1, #ededed));
	background:-moz-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:-webkit-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:-o-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:-ms-linear-gradient(top, #bab1ba 5%, #ededed 100%);
	background:linear-gradient(to bottom, #bab1ba 5%, #ededed 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#bab1ba', endColorstr='#ededed',GradientType=0);
	background-color:#bab1ba;
}
.myButton:active {
	position:relative;
	top:1px;
} 
<style>
    
    #actionDiv {
	background-color: rgba(255, 255, 255, 0.5);
	width: 90%;
	height: 90%;
	position: fixed;
	top: 0%;
	left: 10%;
}

#nestActionDiv {
	background-color: rgba(255, 255, 255, 255);
	width: 250px;
	height: 400px;
	border-width: 2px;
	border-color: gray;
	border-style: solid;
	-webkit-border-radius: 50px;
	position: fixed;
	top: 30%;
	left: 70%;
}

#close {
	width: 55px;
	height: 30px;
}

#close:hover {
	width: 55px;
	height: 35px;
}
  
  </style>
  
</style>