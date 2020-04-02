<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>

<script>
callActionUrl=function(url){
		//alert(url);
		$( 'form[id=sectionForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=sectionForm]' ).attr('method','post');
		$( 'form[id=sectionForm]' ).submit();
	};
</script>

<title><spring:message htmlEscape="true"  code="Label.CREATENEWSECTION"></spring:message></title>
</head>
<body>
	
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
					<h3 class="box-title">
					  <c:choose>
					  
						<c:when test="${opeartion eq 'C'}">
							<spring:message htmlEscape="true" code="Label.CREATENEWSECTION"></spring:message>
						</c:when>
						<c:when test="${opeartion eq 'U'}">
							<spring:message htmlEscape="true" code="Label.UPDATESECTION"></spring:message>
						</c:when>
						<c:when test="${opeartion eq 'D'}">
							<spring:message htmlEscape="true" code="Label.DELETESECTION"></spring:message>
						</c:when>
						<c:when test="${opeartion eq 'R'}">
							<spring:message htmlEscape="true" code="Label.RESTORESECTION"></spring:message>
						</c:when>
						
					  </c:choose>
					</h3>
				</div>
					
				<form:form class="form-horizontal" id="sectionForm">
					<br /><h5><c:out value="${msgid}"></c:out></h5>
				  
					<c:if test="${listShow}">
						
						<table class="table table-bordered table-hover" width="100%">
							<thead>
								<tr>
									<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"/></th>
									<th><spring:message	code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"/></th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${failureResult}" var="obj">
									<tr>
										<td><c:out value="${obj.key}"></c:out></td>
										<td><c:out value="${obj.value}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
								
					</c:if>
	  				 
	 		<div class="box-footer">
				<div class="col-sm-offset-2 col-sm-10"> 
					 <div class="pull-right">
						 <button type="button" class="btn btn-danger" onclick="callActionUrl('home.htm')"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"/></button>
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

