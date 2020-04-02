
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<%-- <c:if test='${page eq save}'>				
			 <tr>
					   			<td align="left">
					   			
					   			Page Saved
								</td>
								</tr>
		</c:if>		
		<c:if test='${page eq delete}'>				
			 <tr>
					   			<td align="left">
					   			
					   			Page Deleted
								</td>
								</tr>
		</c:if>		 --%>

<body>
	<br />
	<br />
	<br />
	<br />
	<br />
	<c:if test="${page == 1 && flag == true}">
		<h1 align="center">	<spring:message htmlEscape="true" code="Error.emailsubscribe"></spring:message></h1>
	</c:if>

	<c:if test="${page == 0 && flag == true}">
		<h1 align="center"><spring:message htmlEscape="true" code="Error.emailunsubscribe"></spring:message></h1>
	</c:if>
</body>
</html>