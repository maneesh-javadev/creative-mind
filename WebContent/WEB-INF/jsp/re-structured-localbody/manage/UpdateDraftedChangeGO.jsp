<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>	
<script type="text/javascript">
$(document).ready(function() {
	$("#btnAddLatLong").click(function() {
		buildLatitudeLongitude('', '');
	});
});
var validationForm = function (){
	$("#orderNo" ).rules( "add", {
 		required : true, 
 		maxlength: 60,
 		regex: "[\#\%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]" ,
 		messages: { 
 			required: "<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>",
 			maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
 			regex    : "<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>"
 		}
	});
 	$("#formDateOrderDate" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>" }
	});
 	$("#formDateEffectiveDate" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>" }
	});
 	<c:if test="${isGovernmentOrderUpload}">
 		if($_checkEmptyObject($("#orderCode").val())){
 			if( isParseJson("${not modifyProcess}") ){
			    $("#gazettePublicationUpload" ).rules( "add", {
			 		required : true, 
			 		fileUploadValidate : true,
			 		messages: { 
			 			required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>",
			 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
			 		}
				});
 			}
		}
 	</c:if>
 	
 	$("[name=longitude]" ).rules( "add", {
		 customRanges : [[32, 98]],
		 messages: { 
			customRanges: "<spring:message code='Error.longituderng' htmlEscape='true'/>" 
		 }
	}); 
	$("[name=latitude]" ).rules( "add", {
		customRanges : [[6, 38]],
		messages: { 
			customRanges: "<spring:message code='Error.latituderng' htmlEscape='true'/>" 
		}
	});
	$("#mapUpload" ).rules( "add", {
		fileUploadValidateMap : true,
		messages: { 
			fileUploadValidateMap: "<spring:message code='Error.invalidmapfile' htmlEscape='true'/>"
		}
	});
};

var customSubmitValidation = function (form){
	disableAllButtons();
	form.submit();
};
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.MODIFYGOVTLOCALBODY.GO" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="publishModifyGovOrderLB.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="publishModifyGovOrderLB.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode"/>
					<form:hidden path="id" id="paramLBCode"/>
					
					
					<!-- Block for Show General Details of Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<c:if test="${localBodyForm.isdisturbed}">
										<li>
											<label class="inline">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										    <label >
										    		<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </label>
										</li>
									</c:if>
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyForm.stateSpecificCode}" escapeXml="true"></c:out>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Show General Details of Local Government Body Ends. -->		
				
					<!-- Block for Government Order Details Started -->
					<%@include file="../ExistingGovernmentOrder.jsp"%>
					<br/>
					<div class="form_block">
						<div class="col_1">
							<h4>Government Order Uploaded File Details</h4>
							<ul class="form_body">
								<li>
									<table class="data_grid" width="50%">
										<thead>
											<tr>
												<th><spring:message code="Label.FILENAME" htmlEscape="true"/></th>
												<th><spring:message code="Label.FILESIZE" htmlEscape="true"/></th>
												<th><spring:message code="Label.CONTENTTYPE" htmlEscape="true"/></th>
											</tr>
										</thead>
										<tbody >
											<tr>
												<td>
													<c:set var="fileNameGO" value=""/>
													<c:if test="${not empty localBodyForm.orderPath}">
														<c:set var="substrngGO" value="${fn:substring(localBodyForm.orderPath, fn:indexOf(localBodyForm.orderPath, '_'), fn:indexOf(localBodyForm.orderPath, '.'))}" />
														<c:set var="fileNameGO" value="${fn:replace(localBodyForm.orderPath, substrngGO, '')}" />
													</c:if>
													<a id="attachedUploadedFile" href="downloadLBGovernementOrder.htm?filename=${localBodyForm.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
														<c:out value="${fileNameGO}"/>
													</a>
												</td>
												<td>
													<c:out value="${localBodyForm.orderFileSize}"></c:out>
												</td>
												<td>
													<c:out value="${localBodyForm.orderFileContentType}"></c:out>
												</td>
											</tr>
										</tbody>
									</table>
								</li>
							</ul>
						</div>
					</div>
					<br/>
					<!-- Block for Government Order Details Ends Here-->
					
					<!-- Block for GIS Nodes Started -->
				<div class="form_block">
					<div class="col_1">
						<h4><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></h4>
						<ul class="form_body">
							<li>
								<div class="long_latitude" >
									
									<div class="row">
										<div class="col"><label><spring:message code='Label.longituderange' htmlEscape='true'/></label></div>
										<div class="col"><label><spring:message code='Label.latituderange' htmlEscape='true'/></label></div>
									</div>
									<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
										<div class="col"><form:input path="longitude" id="longitude" maxlength="6"/></div>
										<div class="col"><form:input path="latitude" id="latitude" maxlength="6"/></div>
										<div class="col"><input type="button" class="bttn addmore" id="btnAddLatLong" value="Add More"/></div>
									</div>
									<div id="divLatitudeLongitude">
										<!-- Adding Dynamic Rows for Latitude and Longitude -->
									</div>
									<br/>
									<form:errors htmlEscape="true" path="longitude" cssClass="error"/>
									<form:errors htmlEscape="true" path="latitude" cssClass="error"/>	 
								</div>
							</li>
							<li>
								<!-- <div id="divMapUpload" style="display: none;"> -->
								<c:if test="${isMapUploadModifyGO}">
									<form:hidden path="mapUploadPath"/>
									<label>	
										<spring:message code="Label.UPLOADMAP" htmlEscape="true"/>
										<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
									</label>
									<form:input path="mapUpload" id="mapUpload" type="file"/>
									<br/><br/>
								</c:if>
								<!-- </div>	 -->
							</li>	
						</ul>
					</div>
				</div>
				<br/>
				<div class="form_block">
						<div class="col_1">
							<h4>Map Uploaded File Details</h4>
							<ul class="form_body">
								<li>
									<table class="data_grid" width="50%">
										<thead>
											<tr>
												<th><spring:message code="Label.FILENAME" htmlEscape="true"/></th>
												<th><spring:message code="Label.FILESIZE" htmlEscape="true"/></th>
												<th><spring:message code="Label.CONTENTTYPE" htmlEscape="true"/></th>
											</tr>
										</thead>
										<tbody >
											<tr>
												<td>
													<c:set var="mapFileName" value=""/>
													<c:set var="mapSubstrng" value="${fn:substring(localBodyForm.mapUploadPath, fn:indexOf(localBodyForm.mapUploadPath, '_'), fn:indexOf(localBodyForm.mapUploadPath, '.'))}" />
													<c:set var="mapFileName" value="${fn:replace(localBodyForm.mapUploadPath, mapSubstrng, '')}" />
													<a href="downloadLBMap.htm?filename=${localBodyForm.mapUploadPath}&<csrf:token uri='downloadLBMap.htm'/>"><c:out value="${mapFileName}"/></a>
													<form:input path="orderPath"/>
												</td>
												<td>
													<c:out value="${localBodyForm.mapFileSize}"></c:out>
												</td>
												<td>
													<c:out value="${localBodyForm.mapFileContentType}"></c:out>
												</td>
											</tr>
										</tbody>
									</table>
								</li>
							</ul>
						</div>
					</div>
					<br/>
				<!-- Block for GIS Nodes Ends Here -->
						
					<input class="bttn" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
					<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
					<input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
<script type='text/javascript'>
	$(window).load(function () {
		setDefaultDates();
		latitudelongitudeOnload();
		$("#orderNo").removeAttr("readonly");
		$("#bformDateEffectiveDate").datepicker('remove');
		$("#actionExistingGO, #actionNewGO").hide();
		$("#divGazettePublicationUpload").show();
	}); 
</script>
</html>