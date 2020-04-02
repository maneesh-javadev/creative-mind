
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="application/pdf; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
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
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="label.view.draft.subdistrict"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="buildSelection.htm" method="post" id="draftSubdistrictForm" commandName="draftGovermentOrderForm" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
				<input type="hidden" name="filename" value="" />
			    
				<div id="divCenterAligned" class="form_stylings">
					<div class="form_block">
						<div class="col_1">
							<%-- <h4><spring:message htmlEscape="true"  code="Label.Select.Criteria"></spring:message></h4> --%>
							<ul class="form_body">
								<li>
									<label class="inline"> 
										<spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message>
										<%-- <c:out value="${storeSubdistrictCoverageDetails[DistNameEng]}"/> --%>
									</label>
									<label> 
										<c:out value="${StoreSubdistrictDetails[DistNameEng]}"/>
									</label>
									
								</li>
							</ul>
						 </div>
					</div>
			</div>
					
					<br/>
					
					<!-- Previous  Details of subdistrict Started-->
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="label.previous.subdistrict.details"></spring:message></h4>
							<ul class="form_body">
								<li>
									<table id="example" class="data_grid" width="100%">
														<thead>
															<tr>
																
																<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.SUBDISTRICTVERSION" htmlEscape="true"></spring:message></th>
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
																	<td><c:out value="${objects.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.subdistrictNameLocal}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.aliasEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.aliasLocal}" escapeXml="true"></c:out></td>
																	
																	
																</tr>
															
														
																
															</c:forEach>
														</tbody>
													</table>
									
									
								</li>
							</ul>
						 </div>
					</div>
					<!-- General Details of Section Started-->
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="label.subdistrict.change.details"></spring:message><c:out value="(${draftManageSubdistrictForm.formAction})" /></h4>
							<ul class="form_body">
								<li>
									<table id="example" class="data_grid" width="100%">
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
															
															
																<tr id="trdetials">
																	
																	
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
									
									
								</li>
							</ul>
						 </div>
					</div>
					
					<div class="form_block">
						<div class="col_1">
							<h4><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></h4>
							<ul class="form_body">
									<li>
										<table class="data_grid" width="100%">
											<thead>
												<tr class="cec_heading_subcomp">
														<th><spring:message code="Label.ORDERNO"/></th>
														<th><spring:message code="Label.ORDERDATE"/></th>
														<th><spring:message code="Label.EFFECTIVEDATE"/></th>
														<th><spring:message code="Label.GAZPUBDATE"/></th>
														<th><spring:message code="Label.UPLOADEDGOVTORDER"/></th>
												</tr>
										
										   </thead>
																			
											<tbody>
												<tr class="cec_columns1">
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
									</li>
							</ul>
						</div>
					</div>
					
					
				<br/>
				<%-- <c:choose>
				<c:when test="${isView eq true }">
				 <input class="bttn" id="btnFormActionBack" type="button" value="<spring:message htmlEscape="true" code="Button.BACK"/>"  onclick="callActionUrl('manageDraftSubdistrict.htm')"/>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
				
				</c:choose> --%>
				
				
	
				<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

