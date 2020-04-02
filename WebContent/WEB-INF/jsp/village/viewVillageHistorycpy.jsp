<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</title>

<script type="text/javascript">

		</script>
</head>
<body>



  <section class="content">
	 <div class="row">
		 <section class="col-lg-12">
			 <div class="box">
				 <div class="box-header with-border">
					<label><h3 class="box-title"><spring:message htmlEscape="true" code="Label.MANAGEVILLAGE"></spring:message></h3></label>
		         </div>	
						  
	<div class="box-body">
		 <div class="box-header subheading">
            <h4><spring:message htmlEscape="true" code="Label.VILLAGEHISTORY"></spring:message></h4>
         </div>
		
		 <div class="row">
		   <div class="col-sm-12 ">
			  <c:if test="${! empty villageHistory}">
				  <table class="table table-striped table-bordered" cellspacing="0" width="100%">
						 <thead>
					         <th><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
					         <th><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></th>
					         <th><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message></th>
					         <th><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></th>
					         <th><spring:message htmlEscape="true" code="Label.ACTIVEVILLAGE"></spring:message></th>
		                     <th><spring:message htmlEscape="true" code="Label.CREATEDFROM"></spring:message></th>
						</thead>
					<tbody>					    
				         <c:forEach var="villageHistory" varStatus="listVillageRow" items="${villageHistory}">
							<tr>
								<td><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
								<td><c:out value="${villageHistory.villageCode}" escapeXml="true"></c:out></td>
								<td><c:out value="${villageHistory.villageVersion}" escapeXml="true"></c:out></td>														
								<td><c:out value="${villageHistory.villageNameEnglish}" escapeXml="true"></c:out></td>
								<td><c:out value="${villageHistory.lrReplaces}" escapeXml="true"></c:out></td>
								<td><c:out value="${villageHistory.active}" escapeXml="true"></c:out></td>
						   </tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>
				
				<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty villageHistory}">
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<spring:message htmlEscape="true" code="Error.noresult"></spring:message>
							</div>
						</div>
					</c:if>
				</c:if> 
			</div>
		</div>		
				<div class="box-footer">
					<div class="col-sm-offset-2 col-sm-10">
		 				 <div class="pull-right">					
								<!-- BACK button removed - client requirement -->
								<button style="width: 80px;" type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" ><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>						   
						</div>
					</div>
				</div>
			</div>
		 </div>
       </section>
    </div>
  </section>
 </body>
</html>

