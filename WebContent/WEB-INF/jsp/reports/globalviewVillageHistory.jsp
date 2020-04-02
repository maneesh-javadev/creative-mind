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
		        <h3 class="box-title"><spring:message code="Label.VILLAGEHISTORY" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		 --%>
		     
			<form name="form1" id="form1" method="post" action="globalviewstateforcitizen.do" >
				<input type="hidden" name="fileLocation" id="fileLocation" />
				<input type="hidden" name="timeStatmp" id="timeStatmp" />
				<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
			
		
			<div class="box-body">
			
			
			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" text="Hisory Details " ></spring:message></h4>
            </div><!-- /.box-header -->
               	    
					
							                <table class="table table-striped table-bordered dataTable">
								
										       <tr>
												<td width="10%"><spring:message htmlEscape="true" code="Label.SNO" ></spring:message></td>
												<td width="20%"><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message></td>
												<td width="20%"><spring:message htmlEscape="true" code="Label.VILLAGEVERSION"></spring:message></td>
												<td width="40%"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												<td width="10%"><spring:message htmlEscape="true" code="Label.GAZETTESTATUS"></spring:message></td>
									           </tr>
								
								    		   <c:forEach var="villageHistoryDetail" varStatus="listDistrictRow" items="${villageHistoryDetail}">
											 	<tr>
											    <td><c:out value="${listDistrictRow.index+1}" escapeXml="true"></c:out></td>
										        <td align="left"><c:out value="${villageHistoryDetail.villageCode}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${villageHistoryDetail.villageVersion}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${villageHistoryDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${villageHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
												</tr>
												</c:forEach>					    
									  </td>
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

