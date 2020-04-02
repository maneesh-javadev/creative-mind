<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<style type="text/css">
.redborder {
	border: 1px solid red;
}
</style>
<script type="text/javascript">var cPath="<%=contextpthval%>";
</script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'>
	
</script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDesignationDwr.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/js/designation_department.js'>
	
</script>
<script type='text/javascript'>
	function validate() {
		if (document.getElementById('orgType').selectedIndex <= 0) {
			alert("Please select an Organization Type.");
			return false;
		} else if (document.getElementById('olc').selectedIndex <= 0) {
			alert("Please select an Organization.");
			return false;
		}  else if (document.getElementById('orgLocatedLevelCode').selectedIndex <= 0) {
			alert("Please select an Organization Level.");
			return false;
		} 
		else {
			return true;
		}
	}
</script>
</head>

<body>
	<div id="frmcontent">
		<div class="frmhd">

			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.ReorderOrgDesig"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">
				<%-- //these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li>
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message> </a></li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="get_designation_department_reorder.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="get_designation_department_reorder.htm"/>" />
				<form:hidden path="designationType" />
				<form:hidden path="flowName" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="block">
								<ul class="blocklist">
									<li><label for="" id="lblOrgTypelIst"><spring:message htmlEscape="true" code="Label.ORGTYPELIST"></spring:message></label> <span class="errormsg">*</span>
										<div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
											<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><form:errors path="*" cssClass="errorBox"></form:errors></<label>
											</div>
										</div> <br>
									<form:select path="orgType" style="width: 200px" id="orgType" class="combofield" onchange="getOrgbyTypeAtLevel(this.value,1,'${slcCode}')" htmlEscape="true">
											<form:option value="0">
												<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
											</form:option>
											<form:options items="${orgType}" itemLabel="orgType" itemValue="orgTypeCode" />
										</form:select>
										<div id="errorOrgType" style="height: 15px; padding-top: 3px;" class="errormsg"></div></li>
								</ul>
								<ul>
									<li><label id="lblOrgList"><spring:message htmlEscape="true" code="Label.ORGLIST"></spring:message></label> <span class="errormsg">*</span></li>
								</ul>
								<ul class="blocklist">
									<li><form:select id="olc" path="olc" style="width: 200px" class="combofield" onchange="getOrgAtLevel(this.value,1)">
											<form:option value="0">
												<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
											</form:option>
											<form:options items="${organization}" itemLabel="orgName" itemValue="orgCode" />
										</form:select>
										<div id="errorOlc" style="height: 15px; padding-top: 3px;" class="errormsg"></div></li>
								</ul>
								<ul>
									<li><label id="lblOrgLevelList"><spring:message htmlEscape="true" code="Label.ORGLEVEL"></spring:message></label> <span class="errormsg">*</span></li>
								</ul>
								<ul class="blocklist">
									<li><select id="orgLocatedLevelCode" name="orgLocatedLevelCode" style="width: 200px" class="combofield">
											<option value="0">
												<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
											</option>
									</select>
										<div id="errorOrgLevel" style="height: 15px; padding-top: 3px;" class="errormsg"></div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>

	<div class="btnpnl">
		<ul class="listing">
			<li>
			<input id="submit" type="submit" name="Submit" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" onclick="return validate();" /> 
			<input type="button" name="Clear" value="Clear" onclick="go('reorder_designaton_master_state.htm');" /> 
			<input type="button" name="Submit33" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'" />
			</li>
		</ul>
		<div>
	</div>
		</form:form>
	</div>
	</div>

</body>
</html>
