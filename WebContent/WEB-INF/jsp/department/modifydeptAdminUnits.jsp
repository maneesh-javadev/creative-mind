<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script src="js/departmentAdminUnit.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
			$(document).ready(function() {
				var childRecords=new Boolean();
				 childRecords =  '${deptAdminUnitForm.childExist}'; 
				 var parentAdminCode = document.getElementById("parentAdminCode").value; 
				 $('#parentUnit').val(parentAdminCode);
				 if(childRecords == "false")
					 {
				 document.getElementById("parentUnit").disabled=true;
					 }
				 else
					 {
				 var parentcodeForUnitLevel='<c:out value="${deptAdminUnitForm.adminUnitCode}" escapeXml="true"></c:out>';
				 var selObj = document.getElementById("parentUnit");
				 for (var j = 0; j < selObj.options.length; j++) {
				 if (selObj.options[j].value == parentcodeForUnitLevel)
				 selObj.remove(j);
				 }
			 }
				/*  Added by Deepti  on 09-05-2016 */
				 var isChecked ="${deptAdminUnitForm.overlapUnit }";
				 if(isChecked == 'Y'){
					 document.getElementById("overlapUnityes").checked =true;
				 }
				 else if(isChecked =='N'){
					 document.getElementById("overlapUnitno").checked =true;
				 }
				 
	       });
			
			function existEntityList(){
				var isChecked ="${deptAdminUnitForm.overlapUnit }";
				var adminUnitCode='${deptAdminUnitForm.adminUnitCode}';
		    	if(isChecked == 'Y'){
		    		lgdDwrOrganizationService.getEntityList(adminUnitCode, {
		    			callback : function(data) {
		    				if(data.length>0){
		    					alert("You Can't modify this field as admin unit entities are already defined under this level");
		    					document.getElementById("overlapUnityes").checked =true;
		    				}
		    			}
		    		});
				 }
		    	
		      }
	
	</script>
<style>
</style>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">

			<h3 class="subtitle">
				<spring:message code="Label.ModifyAdminUnit" htmlEscape="true"
					text="Modify Administrative Unit Level"></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%--//these links are not working <li><a href="#" rel="toggle[cat]"
					data-openimage="images/minus.jpg"
					data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
						border="0" /></a></li>

				<li><a href="#" class="frmhelp"><spring:message
							htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
 --%>
			</ul>

		</div>
		<!-- <div class="clear"> -->
		<div class="frmpnlbrdr">
			<form:form action="modifyDeptAdmitUnit.htm" method="POST" id="form1"
				commandName="deptAdminUnit">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyDeptAdmitUnit.htm"/>" />
				<input type="hidden" name="stateCode" id="stateCode"
					value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				<form:hidden path="adminUnitCode"
					value="${deptAdminUnitForm.adminUnitCode}" htmlEscape="true" />
				<form:hidden path="createdby" value="${deptAdminUnitForm.createdby}"
					htmlEscape="true" />
				<input type="hidden" id="parentAdminCode"
					value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>" />
				<input type="hidden" id="parentCode" value="0" />
				<input type="hidden" id="childRecords"
					value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>" />
				<input type="hidden" id="adminUnitName"
					value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish }' escapeXml='true'></c:out>" />
				<input type="hidden" id="adminUnitNameLocal"
					value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>" />
				<input type="hidden" id="adminUnitCodehidden"
					value="<c:out value='${deptAdminUnitForm.administrationUnit }' escapeXml='true'></c:out>" />
					
				<!-- added by deepti on 09-05-2016	 -->
				<input type="hidden" id="overlapUnithidden" value="<c:out value='${deptAdminUnitForm.overlapUnit }' escapeXml='true'></c:out>" />
				
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GenDetailAdminUnits"
									htmlEscape="true" text="General Details of Admin Unit Level"></spring:message>
							</div>

							<div>
								<ul class="blocklist">
									<li><label><spring:message
												code="Label.AdminUnitLevelEng"
												text="Administrative Unit Level (In English)"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input
											path="adminLevelNameEng" id="adminLevelNameEng"
											onblur="chekModifyNameValidatons(this.value);"
											name="adminLevelNameEng" htmlEscape="true" class="frmfield"
											style="width: 150px"
											value="${deptAdminUnitForm.adminLevelNameEnglish }"
											maxlength="100" />
										<div class="errormsg">
											<form:errors htmlEscape="true" path="adminLevelNameEng"></form:errors>
										</div>
										<div id="UniqueDeptAdminError" class="errormsg"></div></li>
									<li><label><spring:message
												code="Label.AdminUnitLevelLoc"
												text="Administrative Unit Level (In Local)"></spring:message></label>
										<br /> <form:input path="adminLevelNameLocal"
											id="adminLevelNameLocal" name="adminLevelNameLocal"
											htmlEscape="true" class="frmfield" style="width: 150px"
											value="${deptAdminUnitForm.adminLevelNameLocal }"
											maxlength="100" />

										<div class="errormsg">
											<form:errors htmlEscape="true" path="adminLevelNameLocal"></form:errors>
										</div>
										<div class="errormsg"></div> <span class="errormsg"
										id="ddDestDistrict_error"> </span></li>
									<li><label><spring:message
												code="Label.SELDEPTPARENTUNIT"
												text="Select Parent Administrative Unit Level"
												htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
										<label> <form:select htmlEscape="true"
												path="parentAdminCode" class="combofield" id="parentUnit"
												onclick="clearDivs()" style="width: 150px">
												<form:option value="0" htmlEscape="true">
													<spring:message code="Label.SELECT" text="-slect"></spring:message>
												</form:option>
												<form:options items="${deptUnitExists}"
													itemLabel="adminLevelNameEng" itemValue="adminUnitCode"
													htmlEscape="true" />
											</form:select> <br /></label>

										<div class="errormsg">
											<form:errors htmlEscape="true" path="parentAdminCode"></form:errors>
										</div>
										<div id="parentAdminUnit" style="color: red;"></div></li>
									<li><label> <spring:message  text=" Is  covered area of the Admin Unit entities is overlapping or not"
												htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
										<ul class="listing">
											<li><label> <form:radiobutton id="overlapUnityes" path="overlapUnit" htmlEscape="true" value="Y"  />
											</label><spring:message code="Label.YES"/> <label> <form:radiobutton id="overlapUnitno" path="overlapUnit" htmlEscape="true" value="N"  onclick="existEntityList();"/>
											</label><spring:message code="Label.NO"/></li>
										</ul></li>
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
							confirmed to Modify Admin Unit ?
						</p>
					</div>

					<div class="btnpnl">

						<label> <input type="button" class="btn" name="Submit"
							onclick="ValidateAndSubmitforupdate()" id="submit1"
							value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
						</label> <label><input type="button" class="btn" id="Submit6"
							value="<spring:message code="Button.CLEAR"></spring:message>"
							onclick="emptymodifyElements()" /></label> <label><input
							type="button" name="Submit6" class="btn"
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