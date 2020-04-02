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
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	
	</script>
</head>
<body>
	<section class="content">
	<div class="row">
	<section class="col-lg-12">
      <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VIEWSUBDISTHIST"></spring:message></h3>
                </div>
                <div class="box-body">
                 <c:if test="${! empty subDistrictHistory}">	
                  <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.SUBDISTRICTHISTORY"></spring:message></h4></div>
                  <table  class="table table-bordered table-hover">
						     <tr>
									<td width="6%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
									<td width="16%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></td>
									<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message></td>
									<td width="35%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
									 <td width="22%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ACTIVESUBDISTRICT"></spring:message></td>
								</tr>
									<tr>
								
								</tr>			
							<c:forEach var="subDistrictHistoryDetail" varStatus="listSubDistrictRow" items="${subDistrictHistory}">
								<tr>
									<td width="6%"><c:out value="${listSubDistrictRow.index+1}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictCode}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictVersion}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${subDistrictHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
								</tr>
							</c:forEach>
						
					</table>
				  </c:if>
				  
				  
		  <c:if test="${fn:length(viewPage) > 0}"> 
				<c:if test="${empty subDistrictHistory}">
				<div id="divData">
					<spring:message htmlEscape="true" code="Error.noresult"></spring:message>
					</div>
				</c:if>
			</c:if>
			
			
                  <div class="box-footer">                    
                   <button class="btn btn-danger pull-right" id="btnActionClose" type="button" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
                  </div><!-- /.box-footer -->
              
              </div>
       </section>
                </div>
            </section>
</body>
</html>