<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
		rel="stylesheet" type="text/css" />
	<script src="js/departmentAdminUnitRevenue.js"></script>
     <script src="js/common.js"></script>
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>
	<style>
</style>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
				<h3 class="subtitle"><spring:message text="New Revenue Level" htmlEscape="true" ></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      
					 				        <li>
					 				        				<a href="#" rel="toggle[cat]"	data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
					 				        </li>
					 				     <%--//these links are not working    <li>
					 				        			<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
					 				        </li> --%>
					 	
					 			        </ul>
		
		</div>
		<div class="clear"></div>
			<div class="frmpnlbrdr">
				<form:form action="saveDepartmenAdmitUnits.htm" method="POST"
					id="form1" commandName="deptAdminUnit">
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="saveDepartmenAdmitUnits.htm"/>" />
					<input type="hidden" name="stateCode" id="stateCode"
						value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
						<form:hidden htmlEscape="true" path="buttonClicked" value="" />	
					<div id="cat">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.GenDetailAdminUnitsRevenue" htmlEscape="true" text="General Details of Revenue Level "></spring:message>
								</div>
								
								<div >
									<ul class="blocklist" >
										<li>
												<label><spring:message code="Label.AdminUnitLevelEngRevnue" text="Add New Revenue Level (In English)"></spring:message>
												</label><span class="errormsg">*</span><br /> <br />
												<form:input path="adminLevelNameEng" id="adminLevelNameEng" onblur="chekNameValidatons(this.value);"
												name="adminLevelNameEng" htmlEscape="true" class="frmfield"
												style="width: 150px" value="" maxlength="100"/> <br /> <br />
												<div class="errormsg">
										<form:errors htmlEscape="true" path="adminLevelNameEng"></form:errors>
									</div>
											<div id="UniqueDeptAdminError" style="color: red;"></div> 
										
										</li>
										<li>
											<label><spring:message code="Label.AdminUnitLevelLocRevenue" text="Add New Revenue Level (In Local)"></spring:message></label>
											<br /> <br />  <form:input path="adminLevelNameLocal" id="adminLevelNameLocal" name="adminLevelNameLocal" htmlEscape="true" class="frmfield" style="width: 150px"  maxlength="100"/>
											 <br /> <br />
                                <div class="errormsg">
										<form:errors htmlEscape="true" path="adminLevelNameLocal"></form:errors>
									</div>
											<div class="errormsg"></div> <span class="errormsg"
											id="ddDestDistrict_error"> </span>
										
										</li>
										<li>
												<label><spring:message code="Label.SELDEPTPARENTUNITREVENUE" text="Select Parent Revenue Level" htmlEscape="true"></spring:message></label>
												<span class="errormsg">*</span><br />
											<br /> <label> <form:select htmlEscape="true" path="parentAdminCode" class="combofield" id="parentUnit" onclick="clearDivs()" style="width: 150px">
												<!-- modify by sunita on 10-07-2015	 -->
													 <form:option value="-1">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option> 
													<form:options items="${deptUnitExists}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" htmlEscape="true" />
												</form:select> <br /> <br /></label>
												<div class="errormsg">
										<form:errors htmlEscape="true" path="parentAdminCode"></form:errors>
									</div>
											<div id="parentAdminUnit" style="color: red;"></div>
										
										</li>
										 <li>
											<label> <spring:message  text="Is covered area of the Revenue entities is overlapping or not" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
											<ul class="listing">
												<li><label> <form:radiobutton id="overlapUnityes" path="overlapUnit" htmlEscape="true" value="Y"  /> </label> <spring:message code="Label.YES"/>
												<label> <form:radiobutton id="overlapUnitno" path="overlapUnit" htmlEscape="true" value="N"  checked="checked"/> </label> <spring:message code="Label.NO"/></li>
											</ul>  
										</li> 
											
										<li></li>
										<li></li>
										<li></li>
									
									</ul>
									<div class="clear"></div>
								</div>
								
								
							</div>
						</div>
						<div id="dialog-confirm" title="Administrative Units ?"
							style="display: none">
							<p>
								<span class="ui-icon ui-icon-alert"
									style="float: left; margin: 0 7px 20px 0;"></span> Are you
								confirmed to Save Admin Unit ?
							</p>
						</div>
                            <!-- added by kirandeep on 18/06/2014 -->
						<div class="btnpnl">

					
											<label> <input type="button" class="bttn"
												onclick="ValidateAndSubmit()" id="save" name="Submit"
												value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
											</label>
											<label> <input type="button" class="bttn"
												onclick="ValidateAndSubmitPublish()" id="submit1" name="Publish"
												value="<spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message>" />
											</label>
											 <label><input type="button" class="bttn" id="Submit6"
												value="<spring:message code="Button.CLEAR"></spring:message>"
												onclick="emptyElements()" /></label> <label><input
												type="button" name="Submit6" class="bttn"
												value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
								
						</div>



					</div>
				</form:form>
			</div>
			<script src="/LGD/JavaScriptServlet"></script>


		<!-- </div> -->
	</div>

</body>
</html>