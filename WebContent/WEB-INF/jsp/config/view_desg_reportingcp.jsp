<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>

<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWREPORTING"></spring:message></h3>
		      </div><!-- /.box-header -->
		      
		      
		      
		
				   <form:form action="home.htm" method="POST"  class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="home.htm"/>"/>
					
					<div class="box-body">
								<div class="form-group col-sm-12"">
                           			<label class="control-label"><c:out value="${successMsg}" /></label>
                            	</div>
								
								<table class="data_grid" width="70%">
									<tr>
										<th width="20%">Local Government Type</th>
										<th width="20%">Designation</th>
										<th width="20%">ReportTo</th>
									</tr>
								 <c:forEach var="NewDesig" varStatus="fullRow" items="${designationTier}">
									<tr>
										<td width="20%"><c:out value="${NewDesig.nomenclatureEnglish}" escapeXml="true"></c:out></td>
										<td width="20%"><c:out value="${NewDesig.designation}" escapeXml="true"></c:out></td>
										<td width="20%"><c:out value="${NewDesig.reportTo}" escapeXml="true"></c:out></td>
									</tr>
								  </c:forEach>
								  </table>
								  <br/> <br/>
								   <div class="box-footer">
		            					<div class="col-sm-offset-2 col-sm-10">
		                 					<div class="pull-right">
		                 					
		                 					 <button type="submit"  class="btn btn-danger" ><spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
		                 					</div>
		                 				</div>
		                 			</div>
		                 			
		                 </div>
							
				  	</form:form>
				  <script src="/LGD/JavaScriptServlet"></script>	
			  </div>
		</section>
	</div>
	</section>
</body>
</html>

