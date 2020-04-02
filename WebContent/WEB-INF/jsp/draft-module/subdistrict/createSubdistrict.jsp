
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@include file="../../common/taglib_includes.jsp"%>
	<%@include file="../../common/dwr.jsp"%>
	<title><spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message></title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<%@include file="createDraftSubdistrictJs.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Title.CREATENEWSUBDISTRICT"></spring:message></h3>
					</div>
					<form:form action="buildSelection.htm" method="post" id="draftSubdistrictForm" commandName="draftSubdistrictForm" enctype="multipart/form-data" class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
					<form:hidden path="selectDistrictCode" value="${draftSubdistrictForm.selectDistrictCode}"/>
					<form:hidden path="formAction"	id="formAction"/>
					<br/>
					<div class="box-body">
	                    <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
	                      	<div class="col-sm-6">
	                        	<form:select path="districtCode" id="districtCode" class="form-control" >
	                        	<c:if test="${draftSubdistrictForm.entityType eq 'S' }">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
								</c:if>
									<c:forEach  var="obj" items="${districtList}">
										 <c:choose>
										 	<c:when test="${obj.operation_state == 'F'.charAt(0)}">
										 		 <form:option value="${obj.districtCode}" disabled="true">${obj.districtNameEnglish}</form:option>
										 	</c:when>
										 	<c:otherwise>
										 		<form:option value="${obj.districtCode}">${obj.districtNameEnglish}</form:option>
										 	</c:otherwise>	 
										 </c:choose>	
									</c:forEach>
								</form:select>
								<span class="mandatory" id="errdistrictCode"></span>
								<form:errors htmlEscape="true" path="districtCode" cssClass="error"/>
	                      	</div>
	                    </div>
							<div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Legend.GENERALDETAILOFNEWSUBDISTRICT"></spring:message></h4>
			                </div>
		                <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.NAMEOFNEWSUBDISTRICTENGLISH"></spring:message><span class="mandatory">*</span></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="subdistrictNameEnglish" id="subdistrictNameEnglish" name="subdistrictNameEnglish" maxlength="50" htmlEscape="true" placeholder="Enter Subdistrict (In English)" class="form-control"/>
	                      		<span class="mandatory">Allowed characters are A-Z,a-z and Space</span>
								<span class="mandatory" id="errsubdistrictNameEnglish"></span>
								<form:errors htmlEscape="true" path="subdistrictNameEnglish" cssClass="error"/>
	                      	</div>
	                    </div>
	                     <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.NAMEOFNEWSUBDISTRICTLOCAL"></spring:message></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="subdistrictNameLocal" id="subdistrictNameLocal" maxlength="50" htmlEscape="true" class="form-control"/>
								<span class="mandatory" id="errsubdistrictNameLocal"></span>
								<form:errors htmlEscape="true" path="subdistrictNameLocal" cssClass="error"/>
	                      	</div>
	                    </div>
	                     <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ALIASENGLISH"></spring:message></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="aliasEnglish" id="aliasEnglish" maxlength="50" htmlEscape="true" class="form-control"/>
								<span class="mandatory" id="erraliasEnglish"></span>
								<form:errors htmlEscape="true" path="aliasEnglish" cssClass="error"/>
	                      	</div>
	                    </div>
	                    <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ALIASLOCAL"></spring:message></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="aliasLocal" id="aliasLocal" maxlength="50" htmlEscape="true" class="form-control"/>
								<span class="mandatory" id="erraliasLocal"></span>
								<form:errors htmlEscape="true" path="aliasLocal" cssClass="error"/>
	                      	</div>
	                    </div>
	                     <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="sscode" id="sscode" maxlength="7" htmlEscape="true" class="form-control"/>
								<span class="mandatory" id="errsscode"></span>
								<form:errors htmlEscape="true" path="sscode" cssClass="error"/>
	                      	</div>
	                    </div>
							<div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Label.HEADQUARTER"></spring:message></h4>
			                </div>
		                <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTHEADQUARTERENGLISH"></spring:message></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="headquarterNameEnglish" id="headquarterNameEnglish" maxlength="50" htmlEscape="true" class="form-control"/>
								<span class="mandatory" id="errheadquarterNameEnglish"></span>
								<form:errors htmlEscape="true" path="headquarterNameEnglish" cssClass="error"/>
	                      	</div>
	                    </div>
	                    <div class="form-group">
	                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message></label>
	                      	<div class="col-sm-6">
	                        	<form:input path="headquarterNameLocal" id="headquarterNameLocal" maxlength="50" htmlEscape="true" class="form-control"/>
								<span class="mandatory" id="errheadquarterNameLocal"></span>
								<form:errors htmlEscape="true" path="headquarterNameLocal" cssClass="error"/>
	                      	</div>
	                    </div>
							<%-- <div class="box-header subheading">
			                  <h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
			                </div>
		                <div class="form-group">
	                      	<div class="col-sm-4">
	                        	<label><spring:message code='Label.longituderange' htmlEscape='true'/></label><br/>
	                        	<form:input path="longitude" id="longitude" maxlength="6" class="form-control"/>
	                      	</div>
	                      	<div class="col-sm-4">
	                        	<label><spring:message code='Label.latituderange' htmlEscape='true'/></label>
								<form:input path="latitude" id="latitude" maxlength="6" class="form-control"/>
	                      	</div>
	                      	<div class="col-sm-4">
	                        	<br/><button class="btn btn-success " id="btnAddLatLong"><i class="fa fa-floppy-o"></i> Add More</button>
	                      	</div>
	                    </div> --%>
		            
							<div class="box-header subheading">
			                  <h4><spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message></h4>
			                </div>
		                <div class="ms_container row" style="margin-left: 10px;">
		                	<div class="ms_selectable col-sm-5 form-group">
		                    	<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"/></label>
		                    	<form:select path="" id="subdistrictList" multiple="multiple" class="form-control"/>
	                      	</div>
	                      	<div class="ms_buttons col-sm-2"><br/>
			                      	<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"  onclick="moveElement('FULL')" >Select Full Sub Districts &gt;&gt;</button>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"  onclick="moveElement('BACK')">&lt;</button>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"  onclick="moveElement('RESET')">&lt;&lt;</button>
									<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"  onclick="moveElement('PART')" >Select Part Sub Districts &gt;&gt;</button>
	                      		
	                      	</div>
	                      	<div class="ms_selection col-sm-5">
	                      		<div class="form-group">
	                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"/></label>
									<form:select path="contibutingSubdistrict" id="contibutingSubdistrict" multiple="multiple" class="form-control"/>
									<span class="mandatory" id="errcontibutingSubdistrict"></span>
									<form:errors htmlEscape="true" path="contibutingSubdistrict" cssClass="error"/>	</br>
									<button type="button" class="btn btn-primary " id="fetchVillageList" ><spring:message htmlEscape="true" code="Button.GETPARTSUBDISTRICT"/></button>
	                      		</div>
	                      	</div>
	                    </div>
	                   
	                    <div class="ms_container row" style="margin-left: 10px;">
		                	<div class="ms_selectable col-sm-5 form-group">
		                    	<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
		                    	<form:select path="" id="villageList" multiple="multiple" class="form-control"/>
		                    	<p style="font-size: 10px;color: red;">*Village Name-Village Code (Sub-District)</p>
								<span class="mandatory" id="errvillageList"></span>
	                      	</div>
	                      	<div class="ms_buttons col-sm-2"><br/><br/>
	                      	<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"   onclick="moveElementVillage('FULL')">Select Villages&gt;&gt;</button>
							<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"    onclick="moveElementVillage('BACK')">&lt;</button>
							<button class="btn btn-primary btn-xs btn-block" id="btnEventCoverage" type="button"   onclick="moveElementVillage('RESET')">&lt;&lt;</button>
	                      	</div>
	                      	<div class="ms_selection col-sm-5">
	                      		<div class="form-group">
	                      			<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"/></label>
									<form:select path="contibutingVillage" id="contibutingVillage" multiple="multiple" class="form-control"/>
									<br/>
									<form:errors htmlEscape="true" path="contibutingVillage" cssClass="error"/>	
									<span class="errormessage" id="errcontibutingVillage"></span>
	                      		</div>
	                      	</div>
	                    </div>
	                    <div class="box-footer">
	                    	<div class="col-sm-offset-2 col-sm-10">
	                    		<div class="pull-right">
	                           		<%--Add Another Functionality Disabled Checked Later <button class="btn btn-primary" id="btnFormActionAddAnoter" type="button"  ><spring:message htmlEscape="true" code="Button.ADDANOTHER"/></button> --%>
				                    <button class="btn btn-success" id="btnFormActionProceestoSave" type="button"  ><spring:message htmlEscape="true" code="button.proccessing.to.save"/></button>
				                    <button class="btn btn-danger" id="btnActionClose" type="button"  onclick="callActionUrl('home.htm')"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
	                        	</div>
	                    	 </div>   
                  		</div>
		            </div>
		            
		            
						
						
						
					</form:form>
				</div>
			</section>
		</div>
	</section>
	<c:if test="${draftSubdistrictForm.entityType eq 'D' }">
	<script>
	getDraftSubdistrictList(parseInt('${draftSubdistrictForm.entityCode}'),subdistrictListPart,subdistrictListFull); 
	</script>
	
	 
	</c:if>
</body>
</html>

