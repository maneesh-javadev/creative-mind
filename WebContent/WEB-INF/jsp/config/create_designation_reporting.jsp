<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<script type="text/javascript" src="js/create_designation_reporting.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDesignationDwr.js'></script>
<script src="js/common.js"></script>	
<script language="javascript" type="text/javascript">



</script>

</head>

<body>
<div id="frmcontent">
		<div class="frmhd" >
			<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.ADDREPORTINGSTRUCTURE"></spring:message></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>
				<%-- //these links are not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				</li>
				<li>
					<a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP" ></spring:message></a>
				</li> --%>
			</ul>
		</div>
		<!-- <div class="clear"></div> -->
		<div class="frmpnlbrdr">
			<form:form id="designationReportingForm" name="designationReportingForm" action="create_designation_reporting.htm" method="post" commandName="designationReportingForm" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="create_designation_reporting.htm"/>"/>
			<input type="hidden" name="reportTo" id="reportTo" value=""/>
		   	<input type="hidden" id="txLBReporting" value="" />
		   	<input type="hidden" name="designation" id="designation" value="" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DESIGNATIONREPORTING"></spring:message></div>
							<ul class="listing">
								<li>
									<label><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message></label><br />
		                                  <form:select path="lgTypeName" id="tierSetupCode" class="frmsfield" style="width:200px;" htmlEscape="true"
		                                  		onchange="hideAll();
		                                  				  displayLoadingImage();
		                                  				  getOfficalDesignation(this.value.split('%')[0]);
		                                  				  getLocalBodyParentList(this.value.split('%')[0]);
		                                  				  getParentTierSetupList(this.value.split('%')[0]);
		                                  				  hideLoadingImage();
		                                  				  setEnableButton();" > 
		                                    <form:option value="0" htmlEscape="true">Select</form:option>
		                                    <form:options items="${lgT}" itemLabel="localBodyTypeName" itemValue="temp" htmlEscape="true"/>
		                                  </form:select>
		                                  <div class="errormsg" id="tierSetupCode_error">
		                                  	<spring:message htmlEscape="true" code="Error.SELECTLOCALBODY"></spring:message>
									      </div>
		                                  <div style="height:15px; padding-top:3px;" class="errormsg"></div>
								</li>
							</ul>
							<div id="ReportTable">
								<ul class="listing">
									<li>
										<label><spring:message htmlEscape="true"  code="Label.TD"></spring:message></label><br />
										<input id="ddDesignationName0" type="text" disabled="true"  style="width:200px;" class="frmfield" />
	                          			<form:input path="desig" id="designationCode0" type="hidden" />
	                          			<div style="height:15px; padding-top:3px;" class="errormsg"></div>
									</li>
									<li>
										<label><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message></label><br />
										<form:select path="lgTypeCode" name="lgTypeCode" id="lgTypeCode" class="frmsfield" htmlEscape="true" style="width:200px;" onchange="displayLoadingImage();getDesignation1(this.value.split('%')[0]);hideLoadingImage();" disabled="true">
	                                   	<form:option value="0" htmlEscape="true">Select</form:option>
	                                  			<form:options items="${lgT}" itemLabel="localBodyTypeName" itemValue="temp" htmlEscape="true"/>
	                                    </form:select>	
	                                    <div class="errormsg" id="lgTypeCode_error">
										<spring:message htmlEscape="true" code="Error.SELECTDES"></spring:message>
								       	</div>
	                                  	<div style="height:15px; padding-top:3px;" class="errormsg"></div>
									</li>
									<li>
										<label><spring:message htmlEscape="true"  code="Label.REPORTTO"></spring:message></label><br />
										<form:select path="report" class="frmsfield" style="width:200px;" id="RdesignationCode0" disabled="true" htmlEscape="true"></form:select>
	                       				<div class="errormsg" id="designationCode0_error">
										<spring:message htmlEscape="true" code="Label.SELECT "></spring:message> <spring:message htmlEscape="true" code="Label.LGTYPE"></spring:message>
						       			</div>
				              			<div style="height:15px; padding-top:3px;" class="errormsg"></div>
									</li>
								</ul>
								<ul class="blocklist">
									<li>
										<label><spring:message htmlEscape="true"  code="Label.OtherDesignation" text="Other Designation(s)"></spring:message></label><br />
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div>
					<ul>
						<li>
							<input type="submit"  id="saveReport" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return populate()" disabled="true" style="width: 100px;"/>
					  		<c:choose>
					  			<c:when test="${rtype=='P'}"><c:set var="clearURL" value="create_designation_reporting_panchayat.htm"></c:set></c:when>
					  			<c:when test="${rtype=='T'}"><c:set var="clearURL" value="create_designation_reporting_traditional.htm"></c:set></c:when>
					  			<c:when test="${rtype=='U'}"><c:set var="clearURL" value="create_designation_reporting_urban.htm"></c:set></c:when>
					  		</c:choose>
							<input type="button" name="Clear" value="Clear" onclick="go('${clearURL}');" style="width: 100px;"/>
	                        <input type="button" name="Submit33" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'" style="width: 100px;"/>
						</li>
					</ul>
				</div>
			</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
  </div>
 </body>
</html>
