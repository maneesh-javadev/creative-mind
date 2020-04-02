<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
		        <h3 class="box-title"><spring:message code="Label.STATEHISTORY" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		 --%>
		
			<form name="form1" id="form1" method="post" action="globalviewstateforcitizen.do" >
			
		
			<div class="box-body">
			
			
			<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true" text="History Details " ></spring:message></h4>
            </div><!-- /.box-header -->
               	    
					
							 <table class="table table-bordered table-hover">
								
									
										     <tr >
												<td width="10%" ><spring:message htmlEscape="true" code="Label.SNO" text="Serail No"></spring:message></td>
												<td width="15%" ><spring:message htmlEscape="true" code="Label.STATECODE" text="STATE CODE"></spring:message></td>
												<td width="15%" ><spring:message htmlEscape="true" code="Label.STATEVERSION"></spring:message></td>
												<td width="50%" ><spring:message htmlEscape="true" code="Label.STATENAMEINENGLISH"></spring:message></td>
												<td width="10%" ><spring:message htmlEscape="true" code="Label.ACTIVESTATE" text="Status"></spring:message></td>
											
								           </tr>
								
								    		  <c:forEach var="stateHistoryDetail" varStatus="listDistrictRow" items="${stateHistoryDetail}">
											 <tr class="tblRowB">
											    <td><c:out value="${listDistrictRow.index+1}" escapeXml="true"></c:out></td>
										        <td align="left"><c:out value="${stateHistoryDetail.stateCode}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${stateHistoryDetail.stateVersion}" escapeXml="true"></c:out></td>
											    <td align="left"><c:out value="${stateHistoryDetail.stateNameEnglish}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${stateHistoryDetail.lrReplaces}" escapeXml="true"></c:out></td>
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
 <c:if test="${! empty msgid}">
	<script>
	  alert('${msgid}');
	</script>
</c:if>
</body>
</html>

