<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>

<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css">	
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>	
<style type="text/css">
td.details-control {
    background: url('resources-localbody/images/details_open.png') no-repeat center center;
    cursor: pointer;
}
tr.details td.details-control {
    background: url('resources-localbody/images/details_close.png') no-repeat center center;
    color: blue;
}
</style>	
<script type="text/javascript" language="javascript" class="init">

	function format ( d ) {
    	return '<span class="errormsg">INFO : Local Body has been used as Draft.</span>';
	}
 

	$(document).ready(function() {
		var dt = $('#example').DataTable({
			"bJQueryUI": false,
			"order": [[ 1, "asc" ]]
		});
		
		// Array to track the ids of the details displayed rows
	    var detailRows = [];
	 
	    $('#example tbody').on( 'click', '#showDetails', function () {
	        var tr = $(this).closest('tr');
	        var row = dt.row( tr );
	        var idx = $.inArray( tr.attr('id'), detailRows );
	 	    if ( row.child.isShown() ) {
	            tr.removeClass( 'details' );
	            row.child.hide();
	            // Remove from the 'open' array
	            detailRows.splice( idx, 1 );
	        } else {
	            tr.addClass( 'details' );
	            row.child( format( row.data() ) ).show();
	            // Add to the 'open' array
	            if ( idx === -1 ) {
	                detailRows.push( tr.attr('id') );
	            }
	        }
	    });
	    
	    // On each draw, loop over the `detailRows` array and show any child rows
	    dt.on( 'draw', function () {
	        $.each( detailRows, function ( i, id ) {
	            $('#'+id+' td:first-child').trigger( 'click' );
	        } );
	    } );
		
		$( "#actionFetchLBDetails" ).click(function() {
			if(checkNotUrbanProcess()){
				var lbTypeHierarchylement = $( '#lbTypeHierarchy' );
				if( $_checkEmptyObject($( lbTypeHierarchylement ).val()) ){
					$(lbTypeHierarchylement).addClass("error");
					$( '#errorLbTypeHierarchy' ).text("<spring:message code='error.CHOOSEHIERARCHY' htmlEscape='true'/>");
					return false;
				}
			}
			var localBodyTypeElement = $( '#localBodyType' );
			var selectedlocalBodyType = $( localBodyTypeElement ).val();
			if( $_checkEmptyObject(selectedlocalBodyType) ){
				$(localBodyTypeElement).addClass("error");
				$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
				return false;
			}
			if(checkNotUrbanProcess()){
				var element = $( '[name = localBodyLevelCodes]' );
				var localBodyElement = $( element )[$( element ).length - 1];
				if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
					return false;
				}
			}
			callActionUrl('getLocalBodyDetailsForManage.htm');
		});
		$( "#actionSearchClose" ).click(function() {
			callActionUrl('home.htm');
		});
		$( '[id = gisMapView]' ).click(function() {
			var localBodyCode = $(this).attr('paramGPCode');
			var localBodyName = $(this).attr('paramLBNameEng');
			callGISMapView( localBodyCode, localBodyName,false,false);
		});
	});
	var processLinkActions = function (lbCode, url){
		$('#paramLBCode').val(lbCode);
		callActionUrl(url);
	};
	var callActionUrl = function (url) {
		$( 'form[id=formCDraftedLB]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
		$( 'form[id=formCDraftedLB]' ).attr('method','post');
		$( 'form[id=formCDraftedLB]' ).submit();
	};
	
	var callGISCoverage=function(lbCode,url,gisCoverage){
		$('#paramLBCode').val(lbCode);
		$('#paramGisCoverage').val(gisCoverage);
		
		callActionUrl(url);
	};
	
	
</script>
</head>
<body class="dt-example">
	
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form method="POST" id="formCDraftedLB" commandName="criteriaLocalBodies">
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<form:hidden path="isGISCoverage" id="paramGisCoverage"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4>Search Criteria</h4>
								<ul class="form_body" >
									<c:if test="${URBAN_PROCESS_CONSTANT ne LOCAL_BODY_CREATION_TYPE }">
										<li>
											<label>
												<spring:message code='Label.Selecthierarchylevel' htmlEscape='true'/>
												<span class="mandate">*</span>
											</label>
											<form:select path="lbTypeHierarchy" id="lbTypeHierarchy">
												<c:if test="${fn:length(lbTypeHierarchy) ne 1}">
		   											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
												</c:if>
												<c:forEach items="${lbTypeHierarchy}" var="objHierarchy" >
													<form:option value="${objHierarchy.localBodySetupCode},${objHierarchy.localBodySetupVersion}">
														<c:out value="${objHierarchy.localBodyTypeHierarchy}" escapeXml="true"></c:out>
													</form:option>
												</c:forEach>
											</form:select>
											<span class="errormsg" id="errorLbTypeHierarchy"></span>
										</li>
									</c:if>
									<li>
										<label>
											<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
											<span class="mandate">*</span>
										</label>
										<form:select path="paramLocalBodyTypeCode" id="localBodyType">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
											<c:if test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE }">
												<c:forEach items="${localBodyTypeList}" var="objLBTypes">
													<form:option value="${objLBTypes.localBodyTypeCode}">
														<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
													</form:option>
												</c:forEach>
											</c:if>
										</form:select>
										<span class="errormsg" id="errorLocalBodyType"></span>
									</li>
									<div id="divCreateDynamicHierarchy">
										<!-- This Div used to generate Hierarchy -->
									</div>
									
									<li>
									    <label class="inline">&nbsp;</label>
										<input class="bttn" type="button" id="actionFetchLBDetails" value="Fetch Local Bodies" />
										<input class="bttn" type="button" id="actionSearchClose" value="Close" />	
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Search Criteria for Drafted Local Government Body Ends. -->			
				
					<c:if test="${searchResult}">
					<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h4>Local Government Body Details</h4>
							<ul class="form_body">
								<li>
									<table id="example" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th></th>
												<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.VIEW" htmlEscape="true"></spring:message></th>
												<%-- <th style="word-break:break-all"><spring:message code="Label.GovernmentOrderCorrection" htmlEscape="true"></spring:message></th> --%>
												<th><spring:message code="Label.ModifyName" htmlEscape="true"></spring:message></th>
												<th><spring:message code="Label.Changecoveredarea" htmlEscape="true"></spring:message></th>
												
												<th><spring:message code="Label.Mapcoveredarea" htmlEscape="true"></spring:message></th>
												<c:choose>
													<c:when test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE}">
														<th><spring:message code="Label.Label.Modifytype" htmlEscape="true"></spring:message></th>
													</c:when>
													<c:otherwise>
														<c:if test="${modifyParentRequired}">
															<th><spring:message code="Label.Modifyparent" htmlEscape="true"></spring:message></th>
														</c:if>
													</c:otherwise>
												</c:choose>
												
												<th>GIS Map View</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objects" items="${publishedLocalBodies}" varStatus="counter">
												<tr id="trdetials">
													<td >
														<c:if test="${objects.isDrafted}">
															<img id="showDetails" src="<%=contextpthval%>/resources-localbody/images/details_open.png" width="18" height="19" border="0"/>
														</c:if>
													</td>
													<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
													<td><c:out value="${objects.localBodyCode}" escapeXml="true"></c:out></td>
													<td style="word-break:break-all"><c:out value="${objects.localBodyNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center">
														<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callViewPublishedLocalBody.htm');">
															<img src="images/view.png" width="18" height="19" border="0"/>
														</a>
													</td>
													<%-- <td align="center">
														<c:if test="${not objects.isDrafted}">
															<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callChangeGOPublishedLocalBody.htm');">
																<img src="images/edit.png" width="18" height="19" border="0"/>
															</a>
														</c:if>
													</td> --%>
													<td align="center">
														<c:if test="${not objects.isDrafted}">
															<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callModifyNamePublishedLocalBody.htm');">
																<img src="images/edit.png" width="18" height="19" border="0"/>
															</a>
														</c:if>	
													</td>
													<td align="center">
														<c:if test="${not objects.isDrafted}">
															<c:choose>
																<c:when test="${objects.isLandRegion}">
																	<a href="#"  onclick="processLinkActions('${objects.localBodyCode}', 'callChangeCoveredAreaPublishedLocalBody.htm');">
																		<img src="images/edit.png" width="18" height="19" border="0"/>
																	</a>
																</c:when>
																<c:otherwise>
																	<span class="errormsg"><spring:message code="Error.coverage.unavailable" htmlEscape="true"></spring:message></span>
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
													
													<td align="center" >
														<c:if test="${not objects.isDrafted}">
															<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callMapCoveredAreaPublishedLocalBody.htm');">
																<img src="images/edit.png" width="18" height="19" border="0"/>
															</a>
														</c:if>
													</td>
													<c:choose>
														<c:when test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE}">
															<td align="center">
																<c:if test="${not objects.isDrafted}">
																	<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callModifyTypePublishedLocalBody.htm');"><img src="images/edit.png" width="18" height="19" border="0"/></a>
																</c:if>
															</td>
														</c:when>
														<c:otherwise>
															<c:if test="${modifyParentRequired}">
																<td align="center">
																	<c:if test="${not objects.isDrafted}">
																		<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callModifyParentPublishedLocalBody.htm');">
																			<img  src="images/edit.png" width="18" height="19" border="0"/>
																		</a>
																	</c:if>
																</td>
															</c:if>
														</c:otherwise>
													</c:choose>
													<td  align="center">
														<c:if test="${not objects.isDrafted}">
															<img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" paramGPCode="${objects.localBodyCode}" paramLBNameEng="${objects.localBodyNameEnglish}" onclick="callGISMapView('${objects.localBodyCode}','${objects.localBodyNameEnglish}',false,false)" />
														</c:if>
													</td>
												</tr>	
											</c:forEach>
										</tbody>
									</table>
									
								</li>
							</ul>
						</div>
					</div>
					<!-- Block for Drafted Local Government Body Details in Tabular Format Ends Here. -->
					<br/>
					</c:if>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>

</html>