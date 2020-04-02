<!-- added by chandra on 07 August 2014 -->
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
<script src="js/setParentOrgUnit.js"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
	</script>

<script>
$(document).ready(function() {
		$('#modelYes').click(function() {
			document.forms['form1'].method = "post";
			document.forms['form1'].action = "setOrgParentChilds.htm?<csrf:token uri='setOrgParentChilds.htm'/>";
			document.forms['form1'].submit();
		});
		
		
		$('#clear').click(function() {
			$('#topLevelEntityType').attr('selectedIndex', 0);
			$('#orgCode').find('option:gt(0)').remove();
			$('#orgType').find('option:gt(0)').remove();
			$("#parentLevelOrg").empty();
			$("#sourceOrgList").empty();
			$("#contributedOrgist").empty();
			$('#dialog-clear').modal('hide');
		});
});


 
 


</script>
<c:if test="${isParentOrgUnitSaved}">
		<script>
		$(function() {		
			$('#sucessAlert').modal('show');
			getTopLevelEntityByType('${orgUnitForm.orgType}');
			getParentLevelEntity_Load('${orgUnitForm.orgCode}');
			getparentOrganizations_load('${orgUnitForm.orgTypeCode}','${orgUnitForm.orgCode}');
			setTimeout(function(){
				$("#orgCode option[value='${orgUnitForm.orgCode}']").attr("selected", "selected");
				$("#orgType option[value='${orgUnitForm.orgTypeCode}']").attr("selected", "selected");
				
			},300);
			
			
			
			
			/* var parentAdminCode='${deptAdminUnit.parentAdminCode}';
			var parentCategory='${deptAdminUnit.parentCategory}';
			setTimeout(function(){
				$("#parentUnit option[value='" + parentAdminCode+"-"+parentCategory+ "']").attr("selected", "selected");
				
			},200); */
		});
		</script>
	</c:if>

</head>
<body>
	<section class="content">
    	<div class="row">
        	<section class="col-lg-12 ">
	        	<div class="box ">
	       			<div class="box-header with-border">
	           			<h3 class="box-title"><spring:message code="Label.ParentOrgUnit" htmlEscape="true"	text="Set Parent Org Unit"></spring:message></h3>
	       			</div>
		            <div class="box-body">
		                
		               	<form:form action="setParentOrgUnit.htm" class="form-horizontal" method="POST" id="form1" commandName="orgUnitForm">
					   		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="setParentOrgUnit.htm"/>" />
							<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
							<input type="hidden" name="parentOrgCode" id='parentOrgCode' />
							<input type="hidden" name="childOrgCode" id='childOrgCode' /> 
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELOrganization" text="Select Organization Type"></spring:message><span class="mandatory">*</span></label>
									<div id="topLevelType" style="color: red;"></div>
									<div class="col-sm-6">
										<form:select htmlEscape="true" path="orgType" class="form-control" id="topLevelEntityType" onchange="getTopLevelEntityByType(this.value)">
											<c:choose>
												<c:when test="${isUserEntityDefiner}">
													<form:option value="2" htmlEscape="true">
														<spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message>
													</form:option>
												</c:when>
												<c:otherwise>
													<form:option value="0" htmlEscape="true">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
													<form:option value="2" htmlEscape="true">
														<spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message>
													</form:option>
													<form:option value="3" htmlEscape="true">
														<spring:message code="Label.SELECTORG" text="ORGANIZATION"></spring:message>
													</form:option>
												</c:otherwise>
											</c:choose>
										</form:select> 
									</div>
				                </div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELOrganization" text="Select Organization"></spring:message><span class="mandatory">*</span></label>
									<div id="parentAdminOrg" style="color: red;"></div>
									<div class="col-sm-6">
										<form:select htmlEscape="true" path="orgCode" class="form-control" id="orgCode" onchange="getParentLevelEntity()" >
											<c:choose>
												<c:when test="${isUserEntityDefiner}">
													<form:options items="${UserEntityDefinerOrg}" itemLabel="orgName" itemValue="olc"/>
												</c:when>
												<c:otherwise>
													<form:option value="0" htmlEscape="true">
														<spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>
												</c:otherwise>
											</c:choose>
										</form:select>
									</div>
									<div class="errormsg">
											<form:errors htmlEscape="true" path="orgCode"></form:errors>
										</div>
				                </div>
							
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELOrgLevel" text="Select Level  of Organization"></spring:message><span class="mandatory">*</span></label>
									<div id="parentAdminOrgLevel" style="color: red;"></div>
									<div class="col-sm-6">
										<form:select htmlEscape="true" path="orgTypeCode" class="form-control" onchange="getparentOrganizations(this.value);" id="orgType">
											<c:choose>
												<c:when test="${isUserEntityDefiner}">
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>
													<form:options items="${deptwiseAdminUnitLevelList}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode"/>
												</c:when>
												<c:otherwise>
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>
												</c:otherwise>
											</c:choose>
											
										</form:select>
									</div>
								</div>
				            
				            <div class="form-group">
				            	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTPARENTLEVELORG" text="Select Organization Unit"></spring:message><span class="mandatory">*</span></label>
								<div id="parentOrganization" style="color: red;"></div>
								<div class=" col-sm-6">
									<select id='parentLevelOrg' name="parentLevelOrg" size="7" style="width: 500px; background: none repeat scroll 0 0 #fbf9f0;" onchange="clearOrgsUnitsData();">
								    </select>
								</div>
								<div class="ms_buttons col-sm-2">
									<button name="button2" class="btn btn-primary" type="button" onclick="getChildOrganizations()">Get all child level Organization Units</button>
								</div>
				            </div>
				            <div class="box-header subheading">
		                          <h4><spring:message code="Label.SELECTPARENTLEVELORG" text="Select Child Level Organization"></spring:message></h4>
				             </div>
				            
				           <div class="ms_container row" style="margin-left: 10px;">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.AVAILABLECDHILDORG" text="Available Child level Organization Units"></spring:message></label>
										<div id="childOrganization" style="color: red;"></div>
										<select multiple="multiple" id='sourceOrgList' class="form-control" name="sourceOrgList">
										</select>
										
											
										
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="src2Target1" onclick="addOrgUnits('contributedOrgist','sourceOrgList');" class="btn btn-primary btn-xs btn-block"> <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="target2Src2" onclick="removeOrgUnits('contributedOrgist', 'sourceOrgList');" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="target2Src2" onclick="getSpecificChildOrganizations();" class="btn btn-primary btn-xs btn-block"> Shift suitable Child Org Units
										</button>
									</div>
									<div class="ms_selection col-sm-5">
										<label><spring:message htmlEscape="true" code="Label.CONTRIBUTECDHILDORG" text="Selected Child level Organization Units"></spring:message></label>
										<select id='contributedOrgist' multiple="multiple" class="form-control" name="contributedOrgist">
										</select>

										
                                	</div>
				                  </div> 
				            
				            
				            
					</div>
							
						<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                            <button type="button" id="submit1" name="Submit" class="btn btn-success" onclick="ValidateAndSubmitforEntity();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVEMappin" text="Save Mapping" htmlEscape="true"></spring:message></button>
									<button type="button" id="Submit6" class="btn btn-warning" onclick="emptyElements()"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
									<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
		                        </div>
		                     </div>   
                 		</div>	
                 		
                <!-- <div id="dialog-confirm" title=" Set Parent Org Unit ?"
					style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert"
							style="float: left; margin: 0 7px 20px 0;"></span> The selected
						    mapping would be saved. Do you wish to continue?
					</p>
				</div> -->
				<!-- <div id="dialog-clear" title=" Set Parent Org Unit ?"
					style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert"
							style="float: left; margin: 0 7px 20px 0;"></span> All the
						details entered in the form would be cleared. Do you still wish to
						continue?
					</p>
				</div> -->
				
			<div class="modal fade" id="dialog-confirm" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Set Parent Org Unit</h4>
			        </div>
			        <div class="modal-body" id="customAlertbody">
			            <p>The selected mapping would be saved. Do you wish to continue?</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" id="modelYes"  data-dismiss="modal">Yes</button>
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
			      
			    </div>
			  </div>
			  
  
			  <div class="modal fade" id="dialog-clear" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Set Parent Org Unit</h4>
			        </div>
			        <div class="modal-body" id="customAlertbodyy">
			           <p>All the details entered in the form would be cleared. Do you still wish to
									continue?</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" id="clear" data-dismiss="modal" >Yes</button>
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
			      
			    </div>
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
  
		                </form:form>
		            </div>
		        </div>
			</section>
		</div>
	</section>
	
</body>
</html>