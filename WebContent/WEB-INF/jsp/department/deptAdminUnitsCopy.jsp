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
	
	<script src="js/departmentAdminUnit.js"></script>
     <script src="js/common.js"></script>
	
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>
	<style>
</style>
</head>
<body>
	
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title"><spring:message htmlEscape="true" code="Label.NewAdminLevel"></spring:message></h3>
		            </div>
		            <div class="box-body">
						<div class="box-header subheading">
	                              <h4><spring:message htmlEscape="true" code="Label.GENERALDETAILNEWDISTRICT"></spring:message></h4>
	                    </div>
	                    <form:form action="saveDepartmenAdmitUnits.htm" method="POST" id="form1" commandName="deptAdminUnit">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveDepartmenAdmitUnits.htm"/>" />
							<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
							<form:hidden htmlEscape="true" path="buttonClicked" value="" />	
							
							<div class="form-group">
                 				<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message><span class="mandatory">*</span></label>
	                      		<div class="col-sm-6">
		                      	<form:hidden path="operation" value="C" /> <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
		                        <form:input path="adminLevelNameEng" class="form-control" id="adminLevelNameEng" onblur="chekNameValidatons(this.value);" name="adminLevelNameEng" htmlEscape="true" class="frmfield"
												style="width: 150px" value="" maxlength="100"/>	
		                        <div class="errormsg">
										<form:errors htmlEscape="true" path="adminLevelNameEng"></form:errors>
									</div>
											<div id="UniqueDeptAdminError" style="color: red;"></div>
	                      		</div>
                    	   </div>
							
	                    </form:form>
	                    
	                </div>
	            </div>
	         </section>
		</div>
	</section>
</body>
</html>