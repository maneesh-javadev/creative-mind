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
	<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
	<script src="js/departmentAdminUnit.js"></script>
     <script src="js/common.js"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

	<script type="text/javascript" language="javascript">
	
	
	$(document).ready(function(){
		$('#form1 input[type=text]').attr("autocomplete",'off');
	});
	
	
		dwr.engine.setActiveReverseAjax(true);
	</script>
	<c:if test="${isdeptAdminUnitSaved}">
		<script>
		$(function() {		
			$('#sucessAlert').modal('show');
			var parentAdminCode='${deptAdminUnit.parentAdminCode}';
			var parentCategory='${deptAdminUnit.parentCategory}';
			setTimeout(function(){
				$("#parentUnit option[value='" + parentAdminCode+"#"+parentCategory+ "']").attr("selected", "selected");
				
			},200);
		});
		
		
		
		</script>
	</c:if>
	
	
	
	
</head>
<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form  action="saveDepartmenAdmitUnits.htm" method="POST" id="form1" commandName="deptAdminUnit" class="form-horizontal">
				       <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveDepartmenAdmitUnits.htm"/>" />
					    <input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}'  escapeXml='true'></c:out>"/>
						<form:hidden htmlEscape="true" path="buttonClicked" value="" />	
						<form:hidden htmlEscape="true" path="parentAdminCode"  />
						<form:hidden htmlEscape="true" path="parentCategory" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.GenDetailAdminUnits" htmlEscape="true" text="General Details of Admin Unit "></spring:message></h3>
                                </div>
                                 
                        <div class="box-body">
                        
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
									<form:input path="adminLevelNameEng" id="adminLevelNameEng" onblur="chekNameValidatons(this.value);" name="adminLevelNameEng" htmlEscape="true" class="form-control"
												 value="" maxlength="100"/>
								     <div class="errormsg"><form:errors htmlEscape="true" path="adminLevelNameEng"></form:errors>
									</div>
									<div id="UniqueDeptAdminError" style="color: red;"></div> 
								</div>
							</div>
								
							<div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.AdminUnitLevelLoc" text="Administrative Unit Level (In Local)"></spring:message>  <!-- <span class="mandatory">*</span> --></label>
								<div class="col-sm-6" >
									 <form:input path="adminLevelNameLocal" id="adminLevelNameLocal" name="adminLevelNameLocal" htmlEscape="true" class="form-control"   maxlength="100"/>
								    <div class="errormsg"> <form:errors htmlEscape="true" path="adminLevelNameLocal"></form:errors> </div>
								     <div class="errormsg"></div> <span class="errormsg" id="ddDestDistrict_error"> </span>
								</div>
						</div>  
						
							<div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.SELDEPTPARENTUNIT" text="Select Parent Administrative Unit Level" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
									<select htmlEscape="true" id="parentUnit"  onclick="clearDivs();checkForCountryLevel()" class="form-control">
										
										<option value="-1">Select</option>
				        				<optgroup id="landRegionUnits" label="Land Regions Unit Levels">
				        				
				        						<c:if test="${isCenterUser}">
				        							<c:forEach var="obj" items="${deptUnitExists}">
								        			   <c:if test="${obj.unitLevelType eq 'C'}">
								        			   
								        					<option value="${obj.adminUnitCode}#${obj.unitLevelType}"><c:out value="${obj.adminLevelNameEng}" escapeXml="true"></c:out></option>
														</c:if>
								        			</c:forEach>
				        						</c:if>
							        			<c:forEach var="obj" items="${deptUnitExists}">
							        			   <c:if test="${obj.unitLevelType eq 'L'}">
							        					<option value="${obj.adminUnitCode}#${obj.unitLevelType}"><c:out value="${obj.adminLevelNameEng}" escapeXml="true"></c:out></option>
													</c:if>
							        			</c:forEach>
				        				</optgroup>
				        				<optgroup id="adminUnits" label="Administrative Unit Levels">
				        					<c:forEach var="obj" items="${deptUnitExists}">
							        			   <c:if test="${obj.unitLevelType eq 'A'}">
							        				<option value="${obj.adminUnitCode}<c:out value="#"/>${obj.unitLevelType}"><c:out value="${obj.adminLevelNameEng}" escapeXml="true"></c:out></option>
													</c:if>
							        		</c:forEach>
				        				</optgroup>
				        				<c:if test="${not isCenterUser}">
										<optgroup id=" localbBodyType" label=" Local Body Types">
						        			<c:forEach var="localBody" items="${localBodyTypes}">
						        				<option value="${localBody.localBodyTypeCode}<c:out value="#G"/>"><c:out value="${localBody.name}" escapeXml="true"></c:out></option>
						        			
						        			</c:forEach>
				        				
				        				</optgroup>
				        				</c:if>
										
									</select>
											<div class="errormsg"> <form:errors htmlEscape="true" path="parentAdminCode"></form:errors> </div>
											<div id="parentAdminUnit" style="color: red;"></div>
								</div>
						    </div> 
						
						
						<div class="form-group">
							 <label class="col-sm-3 control-label"><spring:message  text="Is  covered area of the Admin Unit entities is overlapping or not" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							 <label class="radio-inline">
							    <form:radiobutton id="overlapUnityes" path="overlapUnit" htmlEscape="true" value="Y"  /><spring:message code="Label.YES"/>
							 </label>
							 		<label class="radio-inline">
							 		<form:radiobutton id="overlapUnitno" path="overlapUnit" htmlEscape="true" value="N"  checked="checked"/><spring:message code="Label.NO"/></label>
							
						   </div>
						</div>
						 
                   </div> 
             
                     
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-success" onclick="ValidateAndSubmit()" id="save" name="Submit"  ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
	               <button type="button" class="btn btn-success"  onclick="ValidateAndSubmitPublish()" id="submit1" name="Publish"  ><spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message></button>
	               <button type="button" class="btn btn-warning" id="Submit6"  onclick="emptyElements()" ><spring:message code="Button.CLEAR"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
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
		
		<div class="modal fade" id="sucessAlert" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Message</h4>
		        </div>
		        <div class="modal-body">
		           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
				   <esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
		        </div>
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