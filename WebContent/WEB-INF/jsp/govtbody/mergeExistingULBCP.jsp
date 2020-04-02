<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/mergeUlb.js"></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesByParentcode.dwr'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body>


<section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.MergeLB" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->


		<form:form action="mergeUrbanLb.htm" method="POST" commandName="localGovtFormData" id="viewWardForm" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mergeUrbanLb.htm"/>" />
			<input type="hidden" name="govfilePath" id="govfilePath" />
			<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
			<input type="hidden" name="captchastatus" id="captchastatus" value="<c:out value='${flag1}' escapeXml='true'></c:out>" />
			<input type="hidden" name="listformat" id="listformat" value="" />
			<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
			<input type="hidden" name="operationCode" id="operationCode" value="2" />

			
			<div id='LgdLBType' >
			
			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></h4>
			</div>	
				
				<div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"/><span class="mandatory">*</span></label>
					  <div class="col-sm-6">
							<form:select
									path="lgd_LBTypeName" id="ddLgdLBType" multiple="multiple" htmlEscape="true"
									onchange="getLBSubTypeList(this.value,1)" class="form-control">
									<c:forEach var="localBodyTypelist" varStatus="var"
										items="${localBodyType}">
										<form:option id="typeCode" htmlEscape="true"
											value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><esapi:encodeForHTML>${localBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTML></form:option>
									</c:forEach>
							</form:select>
						
						   
					  </div>
				</div>
				
				<div id="AvailableULbs">
				
				
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></h4>
					</div>	
				
				<div class="box-body dept_list_button" >
					<div class="ms_container row"   >
						<div class="ms_selectable col-sm-4 form-group">
							<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message></label>
							<form:select
									name="select9"  id="availablelb" htmlEscape="true"
									path="lgd_LBDistrictAtVillage" multiple="multiple"
									class="form-control" 
									onclick="checkcode(this.value);">
									<%-- <form:option value="" htmlEscape="true">
									</form:option> --%>
							</form:select>
							<form:errors htmlEscape="true" path="lgd_LBDistrictAtVillage" class="mandatory"></form:errors>
								
						</div>
						<div class="ms_buttons col-sm-2">
							<input type="button" id="btnaddVillageFull"  class="btn btn-primary btn-xs btn-block" name="Submit4" value="<spring:message htmlEscape='true'  code='Label.SELECT'></spring:message>" onclick="addItemVillage('choosenlb','availablelb','',false);resetList();" />
							<input 	type="button" id="btnremoveOneVillage" name="Submit4" 	value=" &lt; " class="btn btn-primary btn-xs btn-block" onclick="removeItem('choosenlb','availablelb',false)" />														
							<input 	type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn btn-primary btn-xs btn-block"  onclick="removeAll('choosenlb','availablelb',false)" />	
						</div>
						<div class="ms_selection col-sm-4">
							<div class="form-group">
								<label for="ddDestVillageRLBs"><spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message></label><br/> 
								<form:select
									name="select4" id="choosenlb"  multiple="multiple"
									path="choosenlb" class="form-control"  htmlEscape="true"
									
									onclick="checkcode(this.value)">
								</form:select>	
								
							</div>
						</div>
					</div>
					
					</div>	
							
								
							
							
							
       
						</div>
					</div>
					


					<div  id="divmergeRLB">
					
						<div class="box-header subheading">
							<h4 class="box-title"><spring:message	code="Label.USelectULBMerge" htmlEscape="true"></spring:message></h4>
						</div>
					
						<div class="form-group">
							<label  class="col-sm-3 control-label" ><spring:message htmlEscape="true"  code="Label.SELURBANTYPEBODY"/><span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<form:select id="ddUrbanLocalBodyType" path="lgd_LBTypeName"
											size="1" class="form-control" style="width: 200px" htmlEscape="true"
											onchange="getLBSubTypeList(this.value,2)">
											<form:option value="0" htmlEscape="true">
												<spring:message code="Label.SELECTLOCALBODYTYPE"
													htmlEscape="true"></spring:message>
											</form:option>
											<form:options items="${localBodyType}" htmlEscape="true"
												itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
										</form:select>
						
						   
							</div>
						</div>
					
						<div class="form-group">
							<label  class="col-sm-3 control-label" ><spring:message htmlEscape="true"  code="Label.SELURBANLOCALBODY"/><span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<form:select
											id="ddUrbanLocalBody" path="localBodyNameEnglish" size="1"
											class="form-control" style="width: 200px" htmlEscape="true"
											onchange="askForUpgrade(this);">
											<form:option value="0" htmlEscape="true">
												<spring:message code="Label.SELURBANLOCALBODY"
													htmlEscape="true"></spring:message>
											</form:option>
										</form:select>
						
						   
							</div>
						</div>
						
						<div class="form-group" id="trForUpgrade1" style="display: none;">
						  <label  class="col-sm-2 control-label" for="sel1"><spring:message code="Label.ASKQUESTION"
									htmlEscape="true" text="Do you want to upgrade?"></spring:message></label>
						  <div class="col-sm-6">
							   <label class="radio-inline">	
							 <form:radiobutton
									id="upgradeType" path="lbtypeLevel" value="Y"
									onclick="selectForUpgrade(this)" /> 
									<spring:message
									code="App.YES" htmlEscape="true" text="Yes" />
							 </label>
							 
							  <label class="radio-inline">	
								<form:radiobutton
									id="upgradeType" path="lbtypeLevel" value="N"
									onclick="selectForUpgrade(this)" />
								<spring:message code="App.NO" htmlEscape="true" text="No" /> 
							 </label>
							 
							
						  </div>
					   </div>
							   
					   <div class="form-group" id="trForUpgrade2" style="display: none;">
							<label  class="col-sm-3 control-label" ><spring:message
									code="Label.SELUPGRADEURBANLOCALBODY" htmlEscape="true"
									text="Select for upgrade"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<form:select
									id="ddUrbanLocalBodyTypeForUpgrade" htmlEscape="true"
									path="localbodySubtype" size="1" class="form-control"
									style="width: 200px;" disabled="true">
									<form:option value="0" htmlEscape="true">
										<spring:message code="Label.SELECTLOCALBODYTYPE"
											htmlEscape="true"></spring:message>
									</form:option>
									<form:options items="${urbanlocalbodyType}"
										itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
								</form:select>
						
						   
							</div>
						</div>
							  
							
					</div>
					
					<div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
								<input type="submit"  onclick="return getData();"	name="Submit" class="btn btn-success"	value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
								<input type="button" name="Submit6" class="btn btn-danger" 	value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"	onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
	                      		
	                        </div>
	                     </div>   
                   </div>

                 
                    
				
			

		</form:form>

	</div>
	</section>
	</div>
	</section>

</body>
</html>