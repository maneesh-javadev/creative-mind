
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="application/pdf; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes_new.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<c:set var="entityTypeSubdistrict" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString()%>"></c:set>
<c:set var="entityTypeVillage" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.ENTITY_TYPE_VILLAGE.toString()%>"></c:set>
<c:set var="isPublish" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.FORM_ACTION_PUBLISH.toString()%>"></c:set>
<c:set var="isDraft" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.FORM_ACTION_DRAFT.toString()%>"></c:set>
<c:set var="DistNameEng" value="<%=in.nic.pes.lgd.draft.constant.DraftConstant.DISTRICT_NAME_ENGLISH.toString()%>"></c:set>

<title><spring:message htmlEscape="true" code="label.view.draft.subdistrict"></spring:message></title>

<script>

$(document).ready(function(){
	toggleSubComponent=function(id){
		$(".slide"+id).slideToggle("slow"); 
		//alert($("#toggle"+id).attr('src'));
		var imageObj=$("#toggle"+id);
		if(($(imageObj).attr('src').indexOf("expend.png"))>-1){
			var newSrc = $(imageObj).attr("src").replace("expend", "collapse");
			$(imageObj).attr("src",newSrc);
		}else{
		
			var newSrc = $(imageObj).attr("src").replace("collapse", "expend");
			$(imageObj).attr("src",newSrc);
		}
		 
	};
	
	  $("#btnFormActionPublish").click(function() {
		  validateGeneralDetails(); 
	  });
});

callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=draftSubdistrictForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=draftSubdistrictForm]' ).attr('method','post');
	$( 'form[id=draftSubdistrictForm]' ).submit();
};

var retrieveFile1 = function (filename) {
$(window).attr("location","downloadReportGO.do?filename="+ filename +"&<csrf:token uri='downloadReportGO.do'/>");
}


</script>
</head>
<body>
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="label.view.draft.subdistrict"></spring:message></h3>
					</div>
					<form:form action="buildSelection.htm" method="post" id="draftSubdistrictForm" commandName="draftGovermentOrderForm" class="form-horizontal" >
				  <input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
				<input type="hidden" name="filename" value="" />
						<div class="box-body">
							<div class="form-group">
		                    	
		                      	<div class="col-sm-6">
		                      	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message></label>
		                        	<c:out value="${StoreSubdistrictDetails[DistNameEng]}"/>
		                      	</div>
		                    </div>
		                   
		                   <div class="box-header subheading">
		                     <spring:message htmlEscape="true"  code="label.previous.subdistrict.details"></spring:message>
		                   </div>
		                    <table id="example" class="table table-bordered table-hover" width="100%">
														<thead>
															<tr>
																
																<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.SUBDISTRICTVERSION" htmlEscape="true"></spring:message></th>
																<th><spring:message code="label.minor.version" htmlEscape="true"></spring:message></th>
																
																<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></th>
																
																
															</tr>
														</thead>
														<tbody>
															
															<c:forEach var="objects" items="${StoreSubdistrictDetails['subdistrictList']}" varStatus="counter">
															 
														
																<tr >
																	
																	<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.subdistrictVersion}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.minorVersion}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.subdistrictNameLocal}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.aliasEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.aliasLocal}" escapeXml="true"></c:out></td>
																	
																	
																</tr>
															
														
																
															</c:forEach>
														</tbody>
													</table>
													<div class="box-header subheading">
													  <h4><spring:message htmlEscape="true"  code="label.subdistrict.change.details"></spring:message><c:out value="(${draftManageSubdistrictForm.formAction})" /></h4>
													</div>
													
													<table id="example" class="table table-bordered table-hover" width="100%">
														<thead>
															<tr>
																
																<th><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></th>
																<c:if test="${draftManageSubdistrictForm.formAction eq 'Publish'}">
																<th><spring:message code="Label.SUBDISTRICTVERSION" htmlEscape="true"></spring:message></th>
																</c:if>
																<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></th>
															
																
																
																
																
															</tr>
														</thead>
														<tbody>
															
															
																<tr >
																	
																	
																	<td><c:out value="${draftManageSubdistrictForm.subdistrictCode}" escapeXml="true"></c:out></td>
																	<c:if test="${draftManageSubdistrictForm.formAction eq 'Publish'}">
																		<td><c:out value="${draftManageSubdistrictForm.subdistrictVersion}" escapeXml="true"></c:out></td>
																	</c:if>
																	
																	<td><c:out value="${draftManageSubdistrictForm.changeSubdistrictNameEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${draftManageSubdistrictForm.subdistrictNameLocal}" escapeXml="true"></c:out></td>
																	<td><c:out value="${draftManageSubdistrictForm.aliasEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${draftManageSubdistrictForm.aliasLocal}" escapeXml="true"></c:out></td>
																	
																</tr>
															
									
														</tbody>
													</table>
									<div class="box-header subheading">
									  <h4><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></h4>
									</div>
									
									<table class="table table-bordered table-hover" width="100%">
											<thead>
												<tr >
														<th><spring:message code="Label.ORDERNO"/></th>
														<th><spring:message code="Label.ORDERDATE"/></th>
														<th><spring:message code="Label.EFFECTIVEDATE"/></th>
														<th><spring:message code="Label.GAZPUBDATE"/></th>
														<th><spring:message code="Label.UPLOADEDGOVTORDER"/></th>
												</tr>
										
										   </thead>
																			
											<tbody>
												<tr >
													<td><esapi:encodeForHTMLAttribute>${draftManageSubdistrictForm.orderNo}</esapi:encodeForHTMLAttribute></td>
													<td><fmt:formatDate pattern="dd/MM/yyyy" value="${draftManageSubdistrictForm.orderDate}" /></td>
													<td ><fmt:formatDate pattern="dd/MM/yyyy" value="${draftManageSubdistrictForm.effectiveDate}" /></td>
													<td ><fmt:formatDate pattern="dd/MM/yyyy" value="${draftManageSubdistrictForm.gazPubDate}" /></td>
													
													<td >
													<c:choose>
													<c:when test="${draftManageSubdistrictForm.templateId ne null and draftManageSubdistrictForm.templateId>0 and draftManageSubdistrictForm.formAction ne 'Publish'}">
													<c:out value="Uploaded Government Order not avilable when manage subdistrict using template in draft" />
													</c:when>
													<c:otherwise>
													<a href="#" onclick="retrieveFile1('${draftManageSubdistrictForm.fileTimestamp}');">
																<img src="images/gvt.order.png" width="18" height="19" border="0"/>
															</a>
													</c:otherwise>
													</c:choose>	
													
													
													</td>
													<td>
													
													
													</td>
												</tr>
											</tbody>
										</table>
										
										<div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
		                           		<c:choose>
											<c:when test="${isView eq true }">
											<%--  <button class="btn btn-warning" id="btnFormActionBack" type="button"   onclick="callActionUrl('manageDraftSubdistrict.htm')"><spring:message htmlEscape="true" code="Button.BACK"/></button> --%>
											</c:when>
											<c:otherwise>
											
											</c:otherwise>
											
											</c:choose>
											<button class="btn btn-danger" id="btnActionClose" type="button"  onclick="callActionUrl('home.htm')"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
		             
		                        	</div>
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

