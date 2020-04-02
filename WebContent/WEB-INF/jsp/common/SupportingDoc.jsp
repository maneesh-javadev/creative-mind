
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>

	<link href="${pageContext.request.contextPath}/resource/homebody-resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/homebody-resource/css/lgd.css" rel="stylesheet">

    <!-- Bootstrap Core JavaScript -->
    <script src="resource/homebody-resource/js/bootstrap.min.js"></script>





<div class="modal-content">
<c:choose>
	<c:when test="${empty msg}">
			<div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal">&times;</button>
			                <h4 class="modal-title"><c:out value="SUPPORTING DOCUMENT -${filename}" escapeXml="true"></c:out></h4>
			            </div>
			            <div class="modal-body">
			                <p><strong>File Last Modified:</strong> ${lastmodified}</p>
			                <p><strong>File Description:${filedescription}
			                </p>
			                 <c:if test="${docindex gt 0}">
      						 <p><strong>File Type: </strong><c:out value=".${filetype}" escapeXml="true"></c:out> <i class="${icon}" aria-hidden="true"></i></p>
       						</c:if>
			             
			               <c:choose>
							<c:when test="${docindex eq -1}">
				        		<p class="text-center"><a href="${link}" class="download btn btn-success" role="button"><i class="fa fa-download" aria-hidden="true"></i> Play</a></p>
				       		</c:when>
				       		<c:otherwise>
				       			<p><strong>File size:</strong> <c:out value="${length} MB" escapeXml="true"/></p>
				       			 <p class="text-center"><a href="${link}?<csrf:token uri='${link}'/>" class="download btn btn-success" role="button"><i class="fa fa-download" aria-hidden="true"></i> Download File</a></p>
				       		</c:otherwise>
				   		</c:choose>
			            </div>
			            
			             <div class="modal-footer">
			                <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
			            </div>
			            </c:when>
			            <c:when test="${!empty msg}">
  	<div class="modal-body">
  	<p><i class="fa fa-exclamation-triangle" aria-hidden="true"></i><strong><c:out value="${msg}" escapeXml="true"></c:out> </strong></p>
  	</div>
  	</c:when>
			           
</c:choose>

</div>





