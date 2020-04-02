<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
</head>
<body>
	<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
               <form:form action="modifyLandRegion.htm" method="POST" commandName="configureMapForm">
			     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLandRegion.htm"/>" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
    
                  
     				   <tr class="active">
							<th ><spring:message htmlEscape="true" code="Label.LANDREGIONENTITY" ></spring:message></th>
							<th ><spring:message htmlEscape="true" code="Label.uploadnseparate" ></spring:message></th>
							<th ><spring:message htmlEscape="true" code="Label.BASEURL" ></spring:message></th>
						</tr>
					<tbody>
                        <c:forEach var="viewConfigMapLandRegion" varStatus="administratorUnitRow" items="${configureMapForm.viewConfigMapLandRegion}">
							<tr>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.STATE"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'D')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>

											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>&nbsp;</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'T')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>

											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'V')}">
									<td><label> <spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message>
									</label></td>
									<c:choose>
										<c:when test="${viewConfigMapLandRegion.ismapupload==true}">
											<td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td align="center">-</td>
										</c:when>
										<c:otherwise>
											<td><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>

							</tr>
						</c:forEach>
                  </tbody>
               </table>
               </div>
               
                  <!-- /.box-body -->
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			             <button type="submit" class="btn btn-success" name="Submit" > <spring:message htmlEscape="true"  code="Button.Modify"></spring:message></button>
			             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
  </div>
</section>
<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>