<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%@include file="../common/taglib_includes.jsp"%>

</head>
<body >


<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <%-- <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.DISTRICTHISTORY" htmlEscape="true"></spring:message></h3>
		      </div> --%><!-- /.box-header -->
		
		
			<form name="form1" id="form1" method="post" action="globalviewstateforcitizen.do" >
			
		
			<div class="box-body">
			
			
			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" text="History Details " ></spring:message></h4>
            </div><!-- /.box-header -->
               	    
					
							 <table class="table table-striped table-bordered dataTable">
								
									
										     <tr >
												<td width="10%" ><spring:message htmlEscape="true" code="Label.SNO" text="Serail No"></spring:message></td>
												<td width="20%" ><spring:message htmlEscape="true" code="Label.DISTRICTCODE" text="DISTRICT CODE"></spring:message></td>
												<td width="20%"><spring:message htmlEscape="true" code="Label.DISTRICTVERSION"></spring:message></td>
												<td width="40%" ><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
												<td width="10%"><spring:message htmlEscape="true" code="Label.ACTIVESTATE" text="Status"></spring:message></td>
											
								           </tr>
								
								    		   <c:forEach var="listDistrictDetail" varStatus="listDistrictRow" items="${listDistrictDetail}">
											 <tr class="tblRowB">
											    <td><c:out value="${listDistrictRow.index+1}" escapeXml="true"></c:out></td>
										        <td align="left"><c:out value="${listDistrictDetail.districtCode}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${listDistrictDetail.districtVersion}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${listDistrictDetail.districtNameEnglish}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${listDistrictDetail.lrReplaces}" escapeXml="true"></c:out></td>
												
											 </tr>
											</c:forEach>	
										<tr>					    
								     </tr>
 						   </table>
						</div>
				      
			   
			     </form>
			
		</div>
		 
</section>
</div>
</section>
</body>
</html>

