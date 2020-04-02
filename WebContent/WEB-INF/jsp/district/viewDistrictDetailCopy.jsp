<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="str1"  />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
 
		</script>
		
		
	<!-- <script language="javascript">
callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=frmModifyVillage]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=frmModifyVillage]' ).attr('method','post');
	$( 'form[id=frmModifyVillage]' ).submit();
}; 
</script> -->

</head>
	<body>
	<section class="content">
    	<div class="row">
              <section class="col-lg-12 ">
	              	<div class="box ">
	           			<div class="box-header with-border">
	
	               			<%-- <h3 class="box-title"><spring:message htmlEscape="true" code="Label.DISTRICT"></spring:message></h3> --%>
	               			<%-- <h3 class="box-title"><spring:message htmlEscape="true" code="Label.MANAGEDISTRICT"></spring:message></h3> --%>
	           			</div>
		                <div class="box-body">
							<div class="box-header subheading">
                            	<h4><spring:message htmlEscape="true" code="Label.DISTRICTDETAIL"></spring:message></h4>
		                    </div>
		                    
		                    <form:form action="viewDistrictAction.htm" class="form-horizontal" method="POST" commandName="districtView" id="frmViewDistrict">

								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewDistrictAction.htm"/>"/>
					            
					
								<c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${districtView.listDistrictDetails}">
								
								
									<table class="table table-bordered table-hover">
    
									    <tbody>
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.DISTRICTCODE"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
									      </tr>
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.DISTRICTVERSION"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtVersion">&nbsp;
												<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
									      </tr>
									      <tr>
				                              <td><spring:message htmlEscape="true"  code="label.minor.version"></spring:message></td>
				                              <td><spring:bind  path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].minorVersion">&nbsp;
				                               <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
				                          </tr>
									      
									      
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEENG"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">&nbsp;
											  <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
									      </tr>
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.DISTRICTNAMELOCAL"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocal">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
									      </tr>
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.DISTRICTALIASENGLISH"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind> </td>
									      </tr>
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.DISTRICTALIASLOCAL"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocal">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
									      </tr>
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.HEADQUARTER"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].headquarterName">&nbsp;							
				                                    <c:out value="${status.value}" escapeXml="true"></c:out></spring:bind></td>
									      </tr>
									      
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].sscode">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
													</spring:bind></td>
									      </tr>
									      
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderNocr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											   		</spring:bind></td>
									      </tr>
									      
									      
									      
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].orderDatecr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											   		</spring:bind></td>
									      </tr>
									      
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message></td>
									        <td><spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].ordereffectiveDatecr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
											  		</spring:bind></td>
									      </tr>
									      
									      
									      <tr>
									        <td><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message></td>
									        <td>
									        	<spring:bind path="districtView.listDistrictDetails[${listDistrictDetailsRow.index}].gazPubDatecr">&nbsp;
													<c:out value="${status.value}" escapeXml="true"></c:out>
										   		</spring:bind>
									   		</td>
									      </tr>
									      
									    </tbody>
  								</table>
								
								
								</c:forEach>
								
								<c:if test="${! empty districtHistory}">
					    		
					    		 <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.DISTRICTHISTORY"></spring:message></h4></div>
				                  <table  class="table table-bordered table-hover">
								<table class="table table-striped table-bordered" cellspacing="0" width="100%">
											     <thead>
												         <th><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												         <th><spring:message htmlEscape="true"  code="Label.DISTRICTCODE"></spring:message></th>
												         <th><spring:message htmlEscape="true"  code="Label.DISTRICTVERSION"></spring:message></th>
												         <th><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINENGLISH"></spring:message></th>
												       <%--    <th><spring:message htmlEscape="true"  code="Label.ACTIVEDISTRICT"></spring:message></th>
									                     <th><spring:message htmlEscape="true"  code="Label.CREATEDFROM"></spring:message></th> --%>
									                     <th>Status</th>
												</thead>
								    			<tbody>					    
									         <c:forEach var="districtHistoryDetail" varStatus="listDistrictRow" items="${districtHistory}">
												  <tr>
														<td><c:out value="${listDistrictRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${districtHistoryDetail.districtCode}" escapeXml="true"></c:out></td>
														<td><c:out value="${districtHistoryDetail.districtVersion}" escapeXml="true"></c:out></td>														
														<td><c:out value="${districtHistoryDetail.districtNameEnglish}" escapeXml="true"></c:out></td>
														<%-- <td><c:out value="${districtHistoryDetail.active}" escapeXml="true"></c:out></td> --%>
														
									                    <td><c:out value="${districtHistoryDetail.lrReplaces}"></c:out></td>
												  </tr>
											</c:forEach>
								   			</tbody>						
								 
 						   </table>
					    	</c:if>
					    	<div>
					
					
					</div>
					
					<%-- <div class="box-footer">
	                <div class="col-sm-offset-2 col-sm-10">
		             <div class="pull-right">
					    <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='viewdistrict.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i><spring:message htmlEscape="true" code="Button.BACK"></spring:message></button>
			            <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
					</div>
					</div>
					</div> --%>
					
							</form:form>
		                    <script src="/LGD/JavaScriptServlet"></script>
	                    </div>
					</div>
			 </section>
		</div>
	</section>
		
		
		
	</body>
	</html>