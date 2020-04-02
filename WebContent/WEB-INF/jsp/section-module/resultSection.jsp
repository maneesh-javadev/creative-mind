
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
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3>
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
			<ul class="item_list">
				<%-- <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form  id="sectionForm">
				
					
					<div class="form_block">
						<div class="col_1">
							<h4><c:out value="${msgid}"></c:out></h4>
							<ul class="form_body">
								<li>
								<c:if test="${listShow eq true}">
								<table class="data_grid" width="100%">
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
								</li>
						   </ul>
						 </div>
					</div>
				
				
				
				<input class="bttn"  onclick="callActionUrl('home.htm')" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" />
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

