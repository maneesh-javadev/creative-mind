
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="createHabitaionJs.jsp"%>

<title><spring:message htmlEscape="true"  code="label.create.habitation"></spring:message></title>
</head>
<body>
		<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="buildHabitation.htm" method="post" id="habitationForm" commandName="habitationForm"  class="form-horizontal">
				
				    <input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildHabitation.htm"/>" />
					<input type="hidden" value="<c:out value='${habitationForm.parentTypeName }' />" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="label.create.habitation"></spring:message></h3>
                                </div>
                                
                   <div class="box-body">
                     
                      	<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="label.habitation.name.eng"></spring:message>
										<span class="mandate">*</span>
									</label>
									<div class="col-sm-6">
										 <form:input path="habitationNameEnglish"  class="form-control" id="habitationNameEnglish" maxlength="200" htmlEscape="true"  autocomplete="off"/>
										<br/><span>Allowed characters are A-Z,a-z,0-9,/,-,Space,comma,dot,brackets()</span>
										<span class="mandatory" id="errhabitationNameEnglish"></span>
										<form:errors htmlEscape="true" path="habitationNameEnglish" cssClass="error"/>
									
									</div>
								</div>
								
								
							<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="label.habitation.name.local"></spring:message>
									</label>
									<div class="col-sm-6">
										<form:input path="habitationNameLocal" class="form-control" id="habitationNameLocal" maxlength="200" htmlEscape="true" autocomplete="off"/>	
									  <br/>
									<form:errors htmlEscape="true" path="habitationNameLocal" cssClass="error"/>
									
									</div>
								</div>
								
								
								
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message>
									</label>
									<div class="col-sm-6">
									
									
									   <div class="input-group date datepicker">
								 			 <form:input path="effectiveDate" class="form-control" id="effectiveDate"  readonly="true" htmlEscape="true" maxlength="10"/>	
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div> 
									
										
									<br/>
									<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>	
									
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message>
									</label>
									<div class="col-sm-6">
										<form:input path="sscode" id="sscode" class="form-control"  maxlength="10" htmlEscape="true" autocomplete="off"/>	
									<br/>
									<form:errors htmlEscape="true" path="sscode" cssClass="error"/>		
									
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-sm-3 control-label">
										<spring:message htmlEscape="true" code="Label.habitation.parent.type"></spring:message>
									</label>
									<div class="col-sm-6">
										<form:input path="parentTypeName" class="form-control" id="parentTypeName" maxlength="1" htmlEscape="true" disabled="true" autocomplete="off"/>	
									<br/>
									<form:hidden path="parentType" />
									<form:errors htmlEscape="true" path="parentType" cssClass="error"/>				
									
									</div>
								</div>
								
								
								<div class="form-group" >
									<label class="col-sm-3 control-label">
										<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
												<span class="mandate">*</span>
									</label>
									<div class="col-sm-6">
										<form:select path="paramLocalBodyTypeCode" id="localBodyType" class="form-control">
												<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
												
													 <c:forEach items="${localBodyTypeList}" var="objLBTypes">\
													 <c:set var="optValue" value="${objLBTypes.localBodyTypeCode}_${objLBTypes.tierSetupCode}_${objLBTypes.parentTierSetupCode}_${objLBTypes.lbLevel}" />
													 
													<c:choose>
													<c:when test="${objLBTypes.localBodyTypeCode eq 3}">
													<form:option selected="true" value="${optValue}">
															<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
														</form:option>
													</c:when>
													<c:otherwise>
													<form:option value="${optValue}">
															<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
														</form:option>
													</c:otherwise>
													</c:choose>
														
													</c:forEach> 
												</form:select>
											<span class="errormessage" id="errorLocalBodyType"></span>		
									
									</div>
								</div>
								
								<div id="divCreateDynamicHierarchy" style="display:none;">
											<!-- This Div used to generate Hierarchy -->
								</div>
								
								
								
								<div id="divDisplayVillage" style="display: none;">
											
											<div class="form-group">
												<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
												<div class="col-sm-6">
												 <form:select path="" class="form-control" id="ddSourceDistrict">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
												</form:select> 
												<br/>
												<span class="mandatory" id="errorddSourceDistrict"></span>
												</div>
												
												
											</div>
											
											<div class="form-group">
												<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message></label>
												<div class="col-sm-6" ><form:select path="" class="form-control" id="ddSourceSubDistrict">
													<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												</form:select> 
												<br/><span class="mandatory" id="errorddSourceSubDistrict"></span>
											   </div>
											</div>
											
											<div class="form-group">
												<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTVILLAGE"></spring:message></label>
												<div class=" col-sm-6" ><form:select path="paramVillageCode" class="form-control" id="ddSourceVillage">
													<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												</form:select> 
												<br/><span class="errormessage" id="errorddSourceVillage"></span>
											
											</div>
											</div>
										</div>
											
                      </div>
                   
          
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                       <input class="btn btn-success" id="btnFormActionSave" type="button" value="<spring:message htmlEscape="true" code="Button.SAVE"/>" />
				       <input class="btn btn-danger" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
						
                 </div>
           </div>   
       </div> 

       
       
       
       
       
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>

