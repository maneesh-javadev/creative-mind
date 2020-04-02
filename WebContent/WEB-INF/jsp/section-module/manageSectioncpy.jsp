
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="manageSectionJs.jsp"%>

<title><spring:message htmlEscape="true"  code="Label.MANAGESECTION"></spring:message></title>
</head>
<body>
	
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MANAGESECTION"></spring:message></h3>
				</div>
					
				
				<form:form class="form-horizontal" action="manageSection.htm" method="post" id="sectionForm" commandName="sectionForm">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="manageSection.htm"/>" />
				<form:hidden path="sectionCode" id="paramSectionCode"/>
				<form:hidden path="sectionVersion" id="paramSectionVersion"/>
				<form:hidden path="sectionNameEnglish" id="paramSctionName"/>
				  
				
	  
	  <div class="box-body" id="stateBody" style="Display:none">				       
		<div class="col-sm-12">
			<div class="box-header subheading"><h4><spring:message code="Label.select.manage.entity" htmlEscape="true"></spring:message></h4></div>
		
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>							
					  <div class="col-sm-4">	
						<label class="radio-inline"><input type="radio" id="lbEntityType" value="${entityTypeLB}" onclick="showHideOption();" name="parentTypeOption"/><spring:message code='common.localBody' htmlEscape='true'></spring:message></label>				           
				     </div>
								
					 <div class="col-sm-4">
						 <label class="radio-inline"><input type="radio" id="orgEntityType" value="${entityTypeOrg}" onclick="showHideOption();" name="parentTypeOption" /><spring:message code="Label.Organization" htmlEscape="true" ></spring:message></label>
					 </div>
			   </div>	
			   
			    <form:hidden path="parentType" />
				<span class="errormessage" id="errrparentType"></span>
				<form:errors htmlEscape="true" path="parentType" cssClass="error"/>
		
			   <div id="divEntityTypeLB" style="display: none;">
				<div id="dynamicLbStructure"></div>
				<div id="divSpecificFull"></div>
				<div id="divLBSpecificBlock"></div>
			   </div>
											
				<div id="divEntityTypeOrg" style="display: none;">
					<div id="dynamicOrgStructure"></div>
					<div id="divSpecificFullOrg"></div>
					<div id="divOrgSpecificBlock"></div>
				</div>
						
				
	
			  
  	  	 </div>	
	  </div>
	  
	  <div id="centerBody" >
					<div class="col_1">
					  <h4><spring:message code="Label.select.manage.entity" htmlEscape="true"></spring:message></h4> 
						<div id="divEntityTypeOrgCenter" style="display: none;">
							<div id="dynamicOrgStructureCenter"></div>
							<div id="deptorOrg"></div>
							<div id="divSpecificFullOrgCenter"></div>
							<div id="divOrgSpecificBlockCenter" class="col_1"></div>
						</div>
					</div>
				</div>
	  <div class="box-footer">
				  <div class="col-sm-offset-2 col-sm-10"> 
					  <div class="pull-right">
						  <button style="width: 100px;" id="btnFormActionGet" type="button" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
						  <button style="width: 80px;" id="btnActionClose" type="button" class="btn btn-danger" onclick="callActionUrl('home.htm')"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"/></button>
					</div>
				</div>
	 </div>
	  
		<c:if test="${searchFlag}">
		  <div class="box-body" >
			  <h3><label><spring:message code="Label.section.details" htmlEscape="true"></spring:message></label></h3>				
			    <table class="table table-striped table-bordered dataTable" id="reactiveTable" class="display" cellspacing="0" width="100%">
				  <thead>
					<tr>
						<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.section.code" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.section.name.english" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.section.name.local" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.RESTORE" htmlEscape="true"></spring:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="objects" items="${sectionForm.sectionDeleteList}" varStatus="counter">
					  <tr id="trdetials">
						<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
						<td><c:out value="${objects.sectionCode}" escapeXml="true"></c:out></td>
						<td style="word-break:break-all"><c:out value="${objects.sectionNameEnglish}" escapeXml="true"></c:out></td>
						<td style="word-break:break-all"><c:out value="${objects.sectionNameLocal}" escapeXml="true"></c:out></td>
						<td align="center"><a href="#" onclick="processLinkActions('${objects.sectionCode}','${objects.sectionVersion}','${objects.sectionNameEnglish}', 'reActiveSection.htm');"><img src="images/Restores.png" width="32" height="32" border="0"/></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>			
			
		  <div class="box-body" >
			  <h6><spring:message code="Label.deleted.section.details" htmlEscape="true"></spring:message></h6>				
			    <table class="table table-striped table-bordered dataTable" id="example" class="display" cellspacing="0" width="100%">
				  <thead>
					<tr>
						<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.section.code" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.section.name.english" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.section.name.local" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.MODIFY" htmlEscape="true"></spring:message></th>
						<th><spring:message code="Label.DELETE" htmlEscape="true"></spring:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="objects" items="${sectionForm.sectionList}" varStatus="counter">
					  <tr id="trdetials">
						<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
						<td><c:out value="${objects.sectionPK.sectionCode}" escapeXml="true"></c:out></td>
						<td style="word-break:break-all"><c:out value="${objects.sectionNameEnglish}" escapeXml="true"></c:out></td>
						<td style="word-break:break-all"><c:out value="${objects.sectionNameLocal}" escapeXml="true"></c:out></td>
						<td align="center"><a href="#" onclick="processLinkActions('${objects.sectionPK.sectionCode}',null,'${objects.sectionNameEnglish}', 'updateSection.htm');"><img src="images/view.png" width="18" height="19" border="0"/></a></td>
						<td align="center"><a href="#" onclick="processLinkActions('${objects.sectionPK.sectionCode}',null,'${objects.sectionNameEnglish}', 'deleteSection.htm');"><img src="images/delete.png" width="18" height="19" border="0"/></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
					
	<div class="box-footer">
	 <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
			<button type="button"id="btnActionClose" onclick="callActionUrl('home.htm')" class="btn btn-danger" ><i class="fa fa-times-circle"></i><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
		</div>
	 </div>
  </div>	
</c:if>

<c:if test="${searchFlag eq false}">
	<div>
		<div>
			<h2><spring:message code="Label.section.not.found.given.criteria" htmlEscape="true"></spring:message></h2>
		</div>
	</div>
</c:if>

	</form:form>
 </div>
</section>
</div>
</section>	
	
</body>
</html>

