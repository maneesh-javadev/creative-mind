
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>



<title><spring:message htmlEscape="true"  code="Label.MANAGESUBDISTRICT"></spring:message></title>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/dwrDraftSubdistrictService.js'></script>
<script>
$(document).ready(function() {
	var dt = $('#example').DataTable({
		"bJQueryUI": false,
		"order": [[ 1, "asc" ]]
	});
	
	$("#btnFormActionGet").click(function() {
		
		 var districtElement=$( '#districtCode');
			if(isEmptyObject($( districtElement ).val())){
				$( districtElement ).addClass("error");
				$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
					$(districtElement).focus();
					firstErrorElement=true;
			}else{
			 callActionUrl('manageSubdistrict.htm');
		 }
		
	  });
	
	
	$("#districtCode").change(function() {
		$( this ).removeClass("error");
		$( '#err' + $( this ).attr('id') ).text("");
	 });
	
	
});
	
	
	
	
	callActionUrl=function(url){
	 	$( 'form[id=draftSubdistrictForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=draftSubdistrictForm]' ).attr('method','post');
		$( 'form[id=draftSubdistrictForm]' ).submit();
	};

	var processLinkActions = function (paramCode,url){
	$('#paramCode').val(paramCode);
	callActionUrl(url);
	};

	var processOldLinkActions = function (paramCode,url){
		$('#oldParamCode').val(paramCode);
		callActionUrl(url);
		};
		
		
		var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
			if( isEmptyObject(entityCode) ){
				customAlert("No Record(s) found.");
				return false;
			}
			$("#myIframe").contents().find("body").html('');
			$("#dialogBX").dialog({
				dialogClass: 'noTitleStuff',
			    modal: true,
			    width:950,
			    height: 700,
			    resizable:false ,
			    "open": function(){
			    	showLoadingImage();
			    	 $('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");  
		             $("#myIframe").load(function(){
		            	 hideLoadingImage(); 
				    });  
		    	},
		    });	
			$(".ui-dialog-titlebar").removeClass('ui-widget-header');
		};
		
		var callGISMapView = function ( subdistrictCode,subdistrictNameEnglish,isShowOnlyBoundary){
			dwrDraftSubdistrictService.getMappedSubDistrictForGIS(parseInt(subdistrictCode),subdistrictNameEnglish,isShowOnlyBoundary, {
				callback : function( result ){
					//alert(result);
					if("mapNtFin" == result)
						alert("Map is not finalised !");
					else{
						 if("FAILED" == result){
							customAlert(result);
						}else{
							$("#dialogBX").dialog({
								title:' GIS Map View of '+subdistrictNameEnglish+' SubDistrict',
							    modal: true,
							    width:950,
							    height: 700,
							    resizable:false,
							    open: function(ev, ui){
							    	 showLoadingImage();
									
						             $('#myIframe').attr('src', result);
						             $("#myIframe").load(function(){
						            	 hideLoadingImage(); 
								    });
						    	}
							});	
					} 
				}
				},
				errorHandler : function( errorString, exception){
						customAlert(exception);
				},
				async : true
			});		
		};

</script>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.MANAGESUBDISTRICT"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="manageSubdistrict.htm" method="post" id="draftSubdistrictForm" commandName="draftSubdistrictForm" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="manageSubdistrict.htm"/>" />
				<form:hidden path="paramCode" /> 
				<input type="hidden" name="id" id="oldParamCode" />
				<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
				</div>
					<div class="form_block">
						<div class="col_1">
							<%-- <h4><spring:message htmlEscape="true"  code="Label.Select.Criteria"></spring:message></h4> --%>
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message>
										<span class="mandate">*</span>
									</label>
									
									<form:select path="districtCode" id="districtCode">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
										<c:forEach  var="obj" items="${districtList}">
											 <c:choose>
											 	<c:when test="${obj.operation_state == 'F'.charAt(0)}">
											 		 <form:option value="${obj.districtCode}" disabled="true">${obj.districtNameEnglish}</form:option>
											 	</c:when>
											 	<c:otherwise>
											 		<form:option value="${obj.districtCode}">${obj.districtNameEnglish}</form:option>
											 	</c:otherwise>	 
											 </c:choose>	
													  
										</c:forEach>
									</form:select>
									<span class="errormessage" id="errdistrictCode"></span>
									<form:errors htmlEscape="true" path="districtCode" cssClass="error"/>
								</li>
							</ul>
						 </div>
					</div>
					<br/>
					
					<input class="bttn" id="btnFormActionGet" type="button" value="<spring:message htmlEscape="true" code="Button.GET"/>" />
					<input class="bttn" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
					<br/>
					<br/>
					<c:if test="${searchResult}">
									<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h4>Sub-District Details</h4>
							<ul class="form_body">
								<li>
									<table id="example" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												
												<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.VIEW" htmlEscape="true"></spring:message></th>
												
												<th><spring:message code="Label.HISTORY" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></th>
												
												<th><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message></th>
												<th>GIS Map View</th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objects" items="${draftSubdistrictList}" varStatus="counter">
												<tr id="trdetials">
														<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
														<td><c:out value="${objects.subdistrictCode}" escapeXml="true"></c:out></td>
														<td style="word-break:break-all">
														<c:out value="${objects.subdistrictNameEnglish}" escapeXml="true"></c:out>
														<c:if test="${objects.operation_state == 'F'.charAt(0)}">
														(Subdistrict is being used as Drafted)
														</c:if>
														</td>
														<td style="word-break:break-all"><c:out value="${objects.subdistrictNameLocal}" escapeXml="true"></c:out></td>
													<c:choose>
													<c:when test="${objects.operation_state == 'A'.charAt(0)}">
														
														<%-- <td align="center">
															<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'viewSubDistrictDetail.htm');">
																<img src="images/view.png" width="18" height="19" border="0"/>
															</a>
														</td>
														<td align="center">
															<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'viewSubDistrictHistory.htm');">
																<img src="images/history.png" width="18" height="19" border="0"/>
															</a>
														</td> --%>
														<td width="8%" align="center">
													     		<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictDetail.htm', 'id');"/>
															</td>
															<td width="8%" align="center">
																<img src="images/history.png" width="18" height="19" border="0" alt="View Details"  
																	 onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictHistory.htm', 'id');"/>
															</td>
														<td align="center">
															<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'modifySubdistrictCrbyId.htm');">
																	<img src="images/edit.png" width="18" height="19" border="0"/>
															</a>
															
														</td>
														<td align="center">
															<a href="#" onclick="processLinkActions('${objects.subdistrictCode}', 'changeSubdistrictDraft.htm');">
																	<img src="images/edit.png" width="18" height="19" border="0"/>
															</a>
															
														</td>
														<td align="center">
 		 												
														<img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" onclick="callGISMapView('${objects.subdistrictCode}','${objects.subdistrictNameEnglish}','MSDT')" />
														
														</td>
													</c:when>
													<c:otherwise>
												<td width="8%" align="center">
													     		<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictDetail.htm', 'id');"/>
															</td>
															<td width="8%" align="center">
																<img src="images/history.png" width="18" height="19" border="0" alt="View Details"  
																	 onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictHistory.htm', 'id');"/>
															</td>
														<td align="center">
															<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'modifySubdistrictCrbyId.htm');">
																	<img src="images/edit.png" width="18" height="19" border="0"/>
															</a>
															
														</td>
														<td align="center">
 		 												
														</td>
														<td align="center"></td>
														<td align="center"></td>
													</c:otherwise>
													</c:choose>
													
													
													
												</tr>	
											</c:forEach>
										</tbody>
									</table>
									
								</li>
							</ul>
						</div>
					</div>
					<!-- Block for Drafted Local Government Body Details in Tabular Format Ends Here. -->
					</c:if>
					
				
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

