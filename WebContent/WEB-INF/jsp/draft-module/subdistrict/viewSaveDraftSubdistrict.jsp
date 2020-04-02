
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
<script src="js/pdf.js"></script>
<script src="js/pdf.worker.js"></script>
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
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message htmlEscape="true" code="label.view.draft.subdistrict"></spring:message></h3>
		      </div><!-- /.box-header -->

				<form:form action="buildSelection.htm" method="post" id="draftSubdistrictForm" commandName="draftGovermentOrderForm" class="form-horizontal" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
				<input type="hidden" name="filename" value="" />
			    
			    <div class="form-group">
					<label  class="col-sm-3 control-label" for="deptId"><spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message></label>
				  	<div class="col-sm-6">
				  		<c:out value="${storeSubdistrictCoverageDetails[DistNameEng]}"/>
				  	</div>
				</div>
			    
			
					
					<br/>
					<!-- General Details of Section Started-->
					<div class="box-header subheading">
	                  		<h4 class="box-title"><spring:message htmlEscape="true"  code="Legend.GENERALDETAILOFNEWSUBDISTRICT"></spring:message><c:out value="(${draftGovermentOrderForm.formAction})" /></h4>
	               	    </div><!-- /.box-header -->
	               	    
					 <div class="form-group" style="padding-left: 30px;">
									<table id="example" class="data_grid" width="90%">
														<thead>
															<tr>
																
																<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
																<c:if test="${draftGovermentOrderForm.formAction eq isPublish}">
																	<th><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></th>
																</c:if>
																<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></th>
																<th><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></th>
																<th><spring:message htmlEscape="true" code="Label.expend.subdistict.coverage"></spring:message></th>
																
																
															</tr>
														</thead>
														<tbody>
															
															<c:forEach var="objects" items="${storedSubdistrictForms}" varStatus="counter">
															 
															 <c:set var="compIndex" value="${counter.count-1}" />
																<tr id="trdetials">
																	
																	<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
																	<c:if test="${draftGovermentOrderForm.formAction eq isPublish}">
																		<td><c:out value="${objects.subDistrictCode}" escapeXml="true"></c:out></td>
																	</c:if>
																	<td><c:out value="${objects.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
																	<td><c:out value="${objects.subdistrictNameLocal}" escapeXml="true"></c:out></td>
																	
																	<td align="center" >
																		<a href="#" onclick="toggleSubComponent('${compIndex}');">
																			<img id="toggle${compIndex }" src="images/expend.png" width="18" height="19" border="0"/>
																		</a>
																	</td>
																	
																</tr>
															
																<tr  class="slide${compIndex}"  style="display: none;">
																	<td colspan="4">
																		<table class="data_grid" width="100%">
																			<thead>
																				<tr>
																					<td colspan="5">Subdistrict Coverage Details</td>
																				</tr>
																				<tr class="cec_heading_subcomp">
																						<th><spring:message code="Label.SNO"/></th>
																						<th><spring:message code="Label.SUBDISTRICTCODE"/></th>
																						<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH"/></th>
																						<th><spring:message code="Label.COVERAGETYPE"/></th>
																						<th></th>
																				</tr>
										
										
																			</thead>
																			
																			<tbody>
																					<c:set var="idAsString" value="${objects.subdistrictNameEnglish}${entityTypeSubdistrict}" />
																					<!-- <script>alert('${idAsString}');</script> -->
														                 			 <c:forEach var="subObj" items="${storeSubdistrictCoverageDetails[idAsString.toString()]}" varStatus="sno">
																					 <c:set var="subCompIndex" value="${sno.count-1}" />
																						<tr class="cec_columns1">
																							<td align="center"><esapi:encodeForHTMLAttribute>${sno.count}</esapi:encodeForHTMLAttribute></td>
																							<td><esapi:encodeForHTMLAttribute>${subObj.subdistrictCode}</esapi:encodeForHTMLAttribute></td>
																							<td><esapi:encodeForHTMLAttribute>${subObj.subdistrictNameEnglish}</esapi:encodeForHTMLAttribute></td>
																							<td ><esapi:encodeForHTMLAttribute>${subObj.coverageType}</esapi:encodeForHTMLAttribute></td>
																							<td ></td>
																						</tr>
																					
																					</c:forEach> 
																				
																			</tbody>
																			
																			<thead>
																				<tr>
																					<td colspan="5">Village Coverage Details</td>
																				</tr>
																				<tr class="cec_heading_subcomp">
																						<th><spring:message code="Label.SNO"/></th>
																						<th><spring:message code="Label.VILLAGECODE"/></th>
																						<th><spring:message code="Label.VILLAGENAMEINENGLISH"/></th>
																						<th><spring:message code="Label.COVERAGETYPE"/></th>
																						<th><spring:message code="Label.CONTRIBUTINGSUBDISTRICT"/></th>
																				</tr>
										
										
																			</thead>
																			
																			<tbody>
																				<c:set var="idAsString" value="${objects.subdistrictNameEnglish}${entityTypeVillage}" />
														                 			<c:forEach var="subObj" items="${storeSubdistrictCoverageDetails[idAsString.toString()]}" varStatus="sno">
																					 <c:set var="subCompIndex" value="${sno.count-1}" />
																						<tr class="cec_columns1">
																							<td align="center"><esapi:encodeForHTMLAttribute>${sno.count}</esapi:encodeForHTMLAttribute></td>
																							<td><esapi:encodeForHTMLAttribute>${subObj.villageCode}</esapi:encodeForHTMLAttribute></td>
																							<td><esapi:encodeForHTMLAttribute>${subObj.villageNameEnglish}</esapi:encodeForHTMLAttribute></td>
																							<td ><esapi:encodeForHTMLAttribute>${subObj.coverageType}</esapi:encodeForHTMLAttribute></td>
																							<td ><esapi:encodeForHTMLAttribute>${subObj.partSubdistrict}</esapi:encodeForHTMLAttribute></td>
																						</tr>
																					
																					</c:forEach> 
																				
																			</tbody>
																		
																	</table>
																	</td>
																</tr>	
																
															</c:forEach>
														</tbody>
													</table>
									
									</div>
					
						<div class="box-header subheading">
	                  		<h4 class="box-title"><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message><c:out value="(${draftGovermentOrderForm.formAction})" /></h4>
	               	    </div><!-- /.box-header -->
	               	    
					 <div class="form-group" style="padding-left: 30px;">
					
				
										<table class="data_grid" width="90%" >
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
													<td><esapi:encodeForHTMLAttribute>${draftGovermentOrderForm.orderNo}</esapi:encodeForHTMLAttribute></td>
													<td><fmt:formatDate pattern="dd/MM/yyyy" value="${draftGovermentOrderForm.orderDate}" /></td>
													<td ><fmt:formatDate pattern="dd/MM/yyyy" value="${draftGovermentOrderForm.effectiveDate}" /></td>
													<td ><fmt:formatDate pattern="dd/MM/yyyy" value="${draftGovermentOrderForm.gazPubDate}" /></td>
													
													<td >	<a href="#" onclick="retrieveFile1('${draftGovermentOrderForm.fileTimestamp}');">
																<img src="images/gvt.order.png" width="18" height="19" border="0"/>
															</a>
													</td>
													<td>
													
													
													</td>
												</tr>
											</tbody>
									</table>
					</div>
					
					
				<br/>
				 <div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
				<c:choose>
				<c:when test="${isView eq true }">
				 <input class="btn btn-primary" id="btnFormActionBack" type="button" value="<spring:message htmlEscape="true" code="Button.BACK"/>"  onclick="callActionUrl('manageDraftSubdistrict.htm')"/>
				</c:when>
				<c:otherwise>
				<%-- <c:if test="${draftGovermentOrderForm.formAction eq isDraft}">
					<form:hidden path="paramCode" value="${groupId}" />
					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true" code="Button.SP"/>"  onclick="callActionUrl('publishDraftSubdistrict.htm')"/>
				</c:if> --%>
				</c:otherwise>
				
				</c:choose>
				<input class="btn btn-danger" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
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

