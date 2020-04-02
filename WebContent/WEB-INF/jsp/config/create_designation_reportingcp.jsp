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
<section class="content">
	<div class="row">
     	<section class="col-lg-12">
                <form:form id="designationReportingForm" name="designationReportingForm" action="create_designation_reporting.htm" method="post" commandName="designationReportingForm"  >
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="create_designation_reporting.htm"/>"/>
					<input type="hidden" name="reportTo" id="reportTo" value=""/>
				   	<input type="hidden" id="txLBReporting" value="" />
				   	<input type="hidden" name="designation" id="designation" value="" />
                    <div class="box">
                    <div class="box-header with-border">
                      <h3><spring:message htmlEscape="true"  code="Label.ADDREPORTINGSTRUCTURE"></spring:message></h3>
                    </div>
                    
                    <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.DESIGNATIONREPORTING"></spring:message></h4></div>
                        <div class="box-body">
                          
                          <div class="form-group col-sm-12">
                            <label class="control-label"><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message> </label>
                           <div style="width:40% ">
                            <form:select path="lgTypeName" id="tierSetupCode" class="form-control" htmlEscape="true"
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
                            </div>
                          </div>
                          
                          
                          <div id="ReportTable">
                          
                          <div class="form-group col-sm-4">
                            <label class="control-label"><spring:message htmlEscape="true"  code="Label.TD"></spring:message></label>
                            <div>
                               <input id="ddDesignationName0" type="text" disabled="true"   class="form-control" />
	                          			<form:input path="desig" id="designationCode0" type="hidden" />
	                          			<div style="height:15px; padding-top:3px;" class="errormsg"></div>
                            </div>
                          </div>
                          
                          <div class="form-group col-sm-4">
                            <label class="control-label"><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message></label>
                            <div >
                             	<form:select path="lgTypeCode" name="lgTypeCode" id="lgTypeCode" class="form-control" htmlEscape="true" onchange="displayLoadingImage();getDesignation1(this.value.split('%')[0]);hideLoadingImage();" disabled="true">
	                                   	<form:option value="0" htmlEscape="true">Select</form:option>
	                                  			<form:options items="${lgT}" itemLabel="localBodyTypeName" itemValue="temp" htmlEscape="true"/>
	                                    </form:select>	
	                                    <div class="errormsg" id="lgTypeCode_error">
										<spring:message htmlEscape="true" code="Error.SELECTDES"></spring:message>
								       	</div>
	                                  	<div style="height:15px; padding-top:3px;" class="errormsg"></div>
                            
                            </div>
                          </div>
                          
                          <div class="form-group col-sm-4" >
                            <label class="control-label"><spring:message htmlEscape="true"  code="Label.REPORTTO"></spring:message></label>
                            <div >
                             <form:select path="report" class="form-control"  id="RdesignationCode0" disabled="true" htmlEscape="true"></form:select>
	                       				<div class="errormsg" id="designationCode0_error">
										<spring:message htmlEscape="true" code="Label.SELECT "></spring:message> <spring:message htmlEscape="true" code="Label.LGTYPE"></spring:message>
						       			</div>
				              			<div style="height:15px; padding-top:3px;" class="errormsg"></div>
                            
                            </div>
                          </div>
                          
                          
                          <div class="form-group col-sm-12"">
                           <label class="control-label"><spring:message htmlEscape="true"  code="Label.OtherDesignation" text="Other Designation(s)"></spring:message></label>
                            
                          </div>
                          
                     </div>
                          
                   </div>
                  <div class="box-footer">
		            <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
						 <button type="submit"  id="saveReport" name="Submit" class="btn btn-success" onclick="return populate()" disabled="true" ><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
					  		<c:choose>
					  			<c:when test="${rtype=='P'}"><c:set var="clearURL" value="create_designation_reporting_panchayat.htm"></c:set></c:when>
					  			<c:when test="${rtype=='T'}"><c:set var="clearURL" value="create_designation_reporting_traditional.htm"></c:set></c:when>
					  			<c:when test="${rtype=='U'}"><c:set var="clearURL" value="create_designation_reporting_urban.htm"></c:set></c:when>
					  		</c:choose>
							<button type="button" name="Clear" class="btn btn-warning" value="Clear" onclick="go('${clearURL}');" >Clear</button>
	                        <button type="button" name="Submit33" class="btn btn-danger" onclick="javascript:window.location.href='home.htm'" ><spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
                    
                    </form:form>
             
                    </section>
                </div>
            </section>
 </body>
</html>
