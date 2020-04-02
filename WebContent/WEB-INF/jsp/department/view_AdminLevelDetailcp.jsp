<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(document).ready(function() {
		var type = document.getElementById("viewType").value;
		if (type == 0) {
			document.getElementById('UnitLevel').style.visibility = 'visible';
			document.getElementById('UnitLevel').style.display = 'block';
			document.getElementById('UnitEntity').style.visibility = 'hidden';
			document.getElementById('UnitEntity').style.display = 'none';
		} else if (type == 1) {
			document.getElementById('UnitEntity').style.visibility = 'visible';
			document.getElementById('UnitEntity').style.display = 'block';
			document.getElementById('UnitLevel').style.visibility = 'hidden';
			document.getElementById('UnitLevel').style.display = 'none';
		}
	});
</script>

</head>
<body>
	<section class="content">
       <div class="row">
          <section class="col-lg-12">
  <div class="box">
             
		  <input type="hidden" name="viewType" id="viewType" value="<c:out value='${viewType}' escapeXml='true'></c:out>"/>
			<div id="UnitLevel"  style="visibility: hidden; display: none;">
		                <div class="box-header with-border">
		                  <h3 class="box-title"><spring:message code="Label.AdminUnit"	htmlEscape="true" text="Administrative Unit Level">	</spring:message></h3>
		                </div>
		                
		                <div class="box-header subheading">
										<h4><spring:message code="Label.AdminUnitsDetails" htmlEscape="true" text="Details of Admin Unit Level"></spring:message></h4>
									</div>
									<div><font size="3"><c:out value="${successMsg}" escapeXml="true"></c:out></font></div>
			<div class="box-body">
		              <table class="table table-bordered table-hover">
		    
						    <tbody>
						      <tr>
						        <td><spring:message htmlEscape="true" code="Label.AdminUnitCode" text="Administratin Unit Code"></spring:message></td>
						        <td><c:out value="${adminUnitObject.adminUnitCode}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message code="Label.AdminUnitLevelEng" text="Administrative Unit Level (In English)"></spring:message></td>
						        <td><c:out  value="${adminUnitObject.adminLevelNameEnglish}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message code="Label.AdminUnitLevelLoc" text="Administrative Unit Level (In Local)"></spring:message></td>
						        <td><c:out value="${adminUnitObject.adminLevelNameLocal}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message code="Label.DEPTPARENTUNIT" text="Parent Administrative Unit Level" htmlEscape="true"></spring:message></td>
						        <td> <c:out  value="${adminUnitObject.parentAdminLevelName}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message  text=" Is  covered area of the Admin Unit entities is overlapping or not"
												htmlEscape="true"></spring:message></td>
						        <td><c:choose><c:when test="${adminUnitObject.overlapUnit eq  Y}">
												<c:out value="Yes" escapeXml="true"></c:out> </c:when>
												 <c:otherwise><c:out value="No" escapeXml="true"></c:out> </c:otherwise>
											    </c:choose></td>
						      </tr>
						      
						    </tbody>
		 			   </table>
		               </div>
		                  <!-- /.box-body -->
		                  <div class="box-footer">                    
		                     <button type="button" class="btn btn-danger  pull-right" name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
		                  </div><!-- /.box-footer -->
		              
		              </div>
		              
 <!-- Admin entity  form   ----------------- -->
		              
		   <div id="UnitEntity"  style="visibility: hidden; display: none;">
		                <div class="box-header with-border">
		                  <h3 class="box-title"><spring:message code="Label.AdminEntity" htmlEscape="true" text="Administrative Unit Entity"></spring:message></h3>
		                </div>
		                
		                <div class="box-header subheading">
										<h4><spring:message code="Label.AdminEntityDetails" htmlEscape="true" text="Details of Admin Entity"></spring:message></h4>
									</div>
									<div><font size="3"><c:out value="${successMsg}" escapeXml="true"></c:out></font></div>
			  <div class="box-body">
		              <table class="table table-bordered table-hover">
		    
						    <tbody>
						      <tr>
						        <td><spring:message htmlEscape="true" code="Label.AdminEntityCode" text="Administratin Unit Entity Code"></spring:message></td>
						        <td><c:out value="${adminUnitObject.adminUnitCode}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message code="Label.AdminUnitEntityEng" text="Administrative Unit Entity (In English)"></spring:message></td>
						        <td><c:out value="${adminUnitObject.adminLevelNameEnglish}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message code="Label.AdminUnitEntityLoc" text="Administrative Unit Entity (In Local)"></spring:message></td>
						        <td><c:out	value="${adminUnitObject.adminLevelNameLocal}" escapeXml="true"></c:out></td>
						      </tr>
						      <tr>
						        <td><spring:message code="Label.SELDEPTPARENTUNITENTITY" text="Parent Administrative Unit Entity" htmlEscape="true"></spring:message></td>
						        <td>  <c:out value="${adminUnitObject.parentAdminLevelName}" escapeXml="true"></c:out></td>
						      </tr>
						     
						    </tbody>
		 			   </table>
		 			   
		 			   
		 			   <c:if test="${! empty entityCoverageData}">
								<label><h2><spring:message code="Label.COVEREDAREA" htmlEscape="true"></spring:message> </h2> </label>
									<table class="table table-bordered table-hover">
										<tr >
											<td ><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
											<td ><spring:message code="Label.ENTITYTYPE" htmlEscape="true"></spring:message></td>
											<td ><spring:message code="Label.ENTITYNAMEENGLISH" htmlEscape="true"></spring:message></td>
			                                <td width="15%"><spring:message code="Label.TYPE" htmlEscape="true"></spring:message></td>
		                                </tr>
		                         <c:forEach var="adminEntityDetail" varStatus="row" items="${entityCoverageData}">
									<tr >
										<td><c:out value="${row.count}" escapeXml="true" /></td>
										<td><c:out value="${adminEntityDetail.entityTypeName}" escapeXml="true"></c:out></td>
										<td><c:out value="${adminEntityDetail.entityName}" escapeXml="true"></c:out></td>
										<td><c:out value="${adminEntityDetail.coverageType}" escapeXml="true"></c:out></td>
									</tr>
								</c:forEach>
							</table>
					    </c:if>
		               </div>
		               
		               
		                  <!-- /.box-body -->
		                  <div class="box-footer">                    
		                     <button type="button" class="btn btn-danger pull-right" name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
		                  </div><!-- /.box-footer -->
		              
		              </div>
		              </div>
            </section>
          </div>
       </section>
</body>
</html>