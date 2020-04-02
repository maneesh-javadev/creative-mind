<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0"> 
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>
<c:set var="PRI_PROCESS_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_PANCHAYAT.toString()%>"></c:set>


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
<body>
	
	<section class="content">
		 <div class="row">
		 	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title">
		            	<c:out value="Manage "/>
		            	<c:choose>
		            	<c:when test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE }">
		            	<c:out value="Urban "/> 
		            	</c:when>
		            	<c:when test="${PRI_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE }">
		            	<c:out value="PRI "/>
		            	</c:when>
		            	<c:otherwise>
		            	<c:out value="Traditional "/>
		            	</c:otherwise>
		            	</c:choose>
		            	
		            	 <c:out value=" Local Government Body"/> 
		            	 </h3>
		            </div>
		            <div class="box-body">
	            		<div class="box-header subheading">
                        	<h4>Search Criteria</h4>
		                </div>
		                
		                <form:form method="POST" class="form-horizontal" id="formCDraftedLB" commandName="criteriaLocalBodies">
							<form:hidden path="localBodyCreationType"/>
							<form:hidden path="localBodyCode" id="paramLBCode"/>
							<form:hidden path="isGISCoverage" id="paramGisCoverage"/>
							
							<!-- <div id="dialogBX" style="display: none;">
								<iframe id="myIframe" width="910" height="650"></iframe>
							</div> -->
							
							<c:if test="${URBAN_PROCESS_CONSTANT ne LOCAL_BODY_CREATION_TYPE }">
								<div class="form-group">
                     				<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.Selecthierarchylevel"></spring:message><span class="mandatory">*</span></label>
                      				<div class="col-sm-6">
	                      				<form:select path="lbTypeHierarchy" id="lbTypeHierarchy" class="form-control">
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
                      				</div>
                  				</div>
							</c:if>
							
							
							<div class="form-group">
                     				<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
                      				<div class="col-sm-6">
	                      				<form:select path="paramLocalBodyTypeCode" id="localBodyType" class="form-control">>
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
                      				</div>
                  			</div>
							<div id="divCreateDynamicHierarchy">
										<!-- This Div used to generate Hierarchy -->
							</div>
							
							<div class="box-footer">
				                     <div class="col-sm-offset-2 col-sm-10">
				                       <div class="pull-right">
				                            <button type="button" id="actionFetchLBDetails" class="btn btn-success"><i class="fa fa-floppy-o"></i>Fetch Local Bodies</button>
											<button type="button" id="actionSearchClose" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
				                        </div>
				                     </div>   
                  			</div>
							
							<c:if test="${searchResult}">
							<div class="row">
					    		<div class="col-sm-12 ">
									<div class="box-header subheading">
			                        	<h4>Local Government Body Details</h4>
					                </div>
				                
					                <table id="example" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%">
											<thead>
												<tr>
													<!-- <th></th> -->
													<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.VIEW" htmlEscape="true"></spring:message></th>
													<th><spring:message code="LABEL.CORRECTION.LOCALBODY" text="Correction Govt. Order Dtls" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.ModifyName" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.Changecoveredarea" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.Mapcoveredarea" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.CHANGECOVERAGETYPE" htmlEscape="true"></spring:message></th>
													<th><spring:message code="Label.CHANGE.EFFECTIVE.DATE" htmlEscape="true"></spring:message></th>
													<c:choose>
														<c:when test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE}">
															<th>Modify Localbody Type</th>
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
														<%-- <td>
															<c:if test="${objects.isDrafted}">
																<img id="showDetails" src="<%=contextpthval%>/resources-localbody/images/details_open.png" width="18" height="19" border="0"/>
															</c:if>
														</td> --%>
														<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
														<td><c:out value="${objects.localBodyCode}" escapeXml="true"></c:out></td>
														<td style="word-break:break-all"><c:out value="${objects.localBodyNameEnglish}" escapeXml="true"></c:out></td>
															<td align="center">
															<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callViewPublishedLocalBody.htm');">
																<i class="fa fa-eye" aria-hidden="true"></i>
															</a>
														</td>
														<td align="center">
																<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callLocalBodyCorrection.htm');">
																<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
															</a>
														</td>
														<td align="center">
															<c:if test="${not objects.isDrafted}">
																<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callModifyNamePublishedLocalBody.htm');">
																	<i class="fa fa-pencil" aria-hidden="true"></i>
																</a>
															</c:if>	
														</td>
														<td align="center">
															<c:if test="${not objects.isDrafted}">
																<c:choose>
																	<c:when test="${objects.isLandRegion}">
																		<a href="#"  onclick="processLinkActions('${objects.localBodyCode}', 'callChangeCoveredAreaPublishedLocalBody.htm');">
																			<i class="fa fa-pencil"  aria-hidden="true"></i>
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
																	<i class="fa fa-pencil"  aria-hidden="true"></i>
																</a>
															</c:if>
														</td>
														
														<td align="center">
															<c:if test="${not objects.isDrafted}">
																<c:choose>
																	<c:when test="${objects.isLandRegion}">
																		<a href="#"  onclick="processLinkActions('${objects.localBodyCode}', 'callChangeCoverageTypePublishedLocalBody.htm');">
																			<i class="fa fa-pencil"  aria-hidden="true"></i>
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
																<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'modifyLbChangeEffectiveDate.htm');">
																	<i class="fa fa-pencil"  aria-hidden="true"></i>
																</a>
															</c:if>
														</td>
														<c:choose>
															<c:when test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE}">
																<td align="center">
																	<c:if test="${not objects.isDrafted}">
																		<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callModifyTypePublishedLocalBody.htm');">
																		<i class="fa fa-pencil"  aria-hidden="true"></i>
																		</a>
																	</c:if>
																</td>
															</c:when>
															<c:otherwise>
																<c:if test="${modifyParentRequired}">
																	<td align="center">
																		<c:if test="${not objects.isDrafted}">
																			<a href="#" onclick="processLinkActions('${objects.localBodyCode}', 'callModifyParentPublishedLocalBody.htm');">
																				<i class="fa fa-pencil"  aria-hidden="true"></i>
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
				                </div>
				                </div>
				                
							</c:if>
							
		                </form:form>
		                <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="910" height="650"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
							</div>
		                
		            </div>
		        </div>
		    </section>
		 </div>
   </section>
</body>

</html>