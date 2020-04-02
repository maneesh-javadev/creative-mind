
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="commonSectionClinetRule.jsp"%>

<title><spring:message htmlEscape="true"  code="Label.CREATENEWSECTION"></spring:message></title>
</head>
<body>

<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message htmlEscape="true" code="Label.CREATENEWSECTION"></spring:message></h3>
				</div>
					
				<form:form class="form-horizontal" action="buildSelection.htm" method="post" id="sectionForm" commandName="sectionForm">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
				  
	<div class="box-body">				       
		<div class="col-sm-12">
			<div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.GENERALDETAILNEWSECTION"></spring:message></h4></div>
		
				<div class="form-group">							
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SECTIONNAMEENGLISH"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">					           
				          <form:input path="sectionNameEnglish" id="sectionNameEnglish" class="form-control" htmlEscape="true" placeholder="Enter Section Name (In English)" /><br/>
							<span class="mandatory">Allowed characters are A-Z,a-z,0-9,/,-,Space,comma,dot,brackets()</span>
							<span class="errormessage" id="errsectionNameEnglish"></span>
							<form:errors htmlEscape="true" path="sectionNameEnglish" cssClass="error"/>
						</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SECTIONNAMELOCAL"></spring:message><span class="mandatory">*</span></label>
						 <div class="col-sm-6">
					 		<form:input path="sectionNameLocal" id="sectionNameLocal" class="form-control" htmlEscape="true"/><br/>
						    <form:errors htmlEscape="true" path="sectionNameLocal" cssClass="error"/>						
				  		</div>
			   </div>		
		
			   <div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SECTIONNAMESHORT"></spring:message></label>
						 <div class="col-sm-6">
					 		<form:input path="sectionShortName" id="sectionShortName" class="form-control" maxlength="20" htmlEscape="true"/><br/>
							<form:errors htmlEscape="true" path="sectionShortName" cssClass="error"/>			
				  		</div>
			   </div>
		</div>	
	  </div>
	  
	  <div class="box-body" id="stateBody" style="Display:none">				       
		<div class="col-sm-12">
			<div class="box-header subheading"><h4><spring:message code="Label.select.entity" htmlEscape="true"></spring:message></h4></div>
		
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>							
					  <div class="col-sm-4">	
						<label class="radio-inline"><input type="radio"  id="lbEntityType" value="${entityTypeLB}" onclick="showHideOption();" name="parentTypeOption"  /> <spring:message code='common.localBody' htmlEscape='true'></spring:message></label>				           
				     </div>
								
					 <div class="col-sm-4">
						 <label class="radio-inline"><input type="radio"  id="orgEntityType" value="${entityTypeOrg}" onclick="showHideOption();" name="parentTypeOption" /><spring:message code="Label.Organization" htmlEscape="true" ></spring:message></label>
					 </div>
			   </div>	
			   
			   	<form:hidden path="parentType" />
				<span class="errormessage" id="errrparentType"></span>
				<form:errors htmlEscape="true" path="parentType" cssClass="error"/>	
		
			    <div id="divEntityTypeLB" style="display: none;">
					<div id="dynamicLbStructure" ></div>
					<div id="divSpecificFull"></div>
					<div id="divLBSpecificBlock" class="col_1"></div>
				</div>
											
				<div id="divEntityTypeOrg" style="display: none;">
					<div id="dynamicOrgStructure"></div>
					<div id="divSpecificFullOrg"></div>
					<div id="divOrgSpecificBlock" class="col_1"></div>
				</div>
				
				
	
			  
  	  	 </div>	
	  </div>
	  
	  <div class="box-body" id="centerBody" >
								<div class="col_1">
									<%-- <h4><spring:message code="Label.select.under.lbnorg" htmlEscape="true"></spring:message></h4> --%>
										
										
											<div id="divEntityTypeOrgCenter" style="display: none;">
											<div id="dynamicOrgStructureCenter"></div>
											<div id="deptorOrg"></div>
											<div id="divSpecificFullOrgCenter"></div>
											<div id="divOrgSpecificBlockCenter" class="col_1">
											</div>
										
											</div>
						</div>
				
				</div>
	  
	  <div class="box-footer">
				  <div class="col-sm-offset-2 col-sm-10"> 
					  <div class="pull-right">
						  <button style="width: 80px;" id="btnFormActionSave" type="button" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.SAVE"/></button>
						  <button style="width: 80px;" id="btnActionClose" type="button" class="btn btn-danger" onclick="callActionUrl('home.htm')"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"/></button>
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

