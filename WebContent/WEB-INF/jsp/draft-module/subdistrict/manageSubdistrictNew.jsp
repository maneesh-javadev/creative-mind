
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
	
	function getDistrictData(){
		 var districtElement=$( '#districtCode');
			if(isEmptyObject($( districtElement ).val())){
				$( districtElement ).addClass("error");
				$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
					$(districtElement).focus();
					firstErrorElement=true;
			}else{
			 callActionUrl('manageSubdistrict.htm');
		 }
	}
	
	
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
		
			/* $( 'form[id=draftSubdistrictForm]' ).attr('action', cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");
			$( 'form[id=draftSubdistrictForm]' ).attr('method','post');
			$( 'form[id=draftSubdistrictForm]' ).submit(); */
			
			$('#myIframe').attr('src',  cusurl + "?" + paramName + "=" + entityCode+ "&<csrf:token uri='" + cusurl + "'/>");
			$('#dialogBXTitle').text('<spring:message code="Label.MANAGESUBDISTRICT" htmlEscape="true"></spring:message>');
			$('#dialogBX').modal('show'); 
			
			
			
			
			/* $("#myIframe").contents().find("body").html('');
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
			$(".ui-dialog-titlebar").removeClass('ui-widget-header'); */
		};
		
		var callGISMapView = function ( subdistrictCode,subdistrictNameEnglish,isShowOnlyBoundary){
			
			dwrDraftSubdistrictService.getMappedSubDistrictForGIS(parseInt(subdistrictCode),subdistrictNameEnglish,isShowOnlyBoundary, {
				callback : function( result ){
					//alert(result);
					if("mapNtFin" == result)
						alert("Map is not finalised !");
					else{
						 if("FAILED" == result){
							alert(result);
						}else{
							
							$("#adialogBXTitle").text(' GIS Map View of '+subdistrictNameEnglish+' SubDistrict');
							 $('#myIframe').attr('src', result);
							$('#dialogBX').modal('show');	
							
							
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
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MANAGESUBDISTRICT"></spring:message></h3>
					</div>
					<form:form action="manageSubdistrict.htm" method="post" id="draftSubdistrictForm" commandName="draftSubdistrictForm" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="manageSubdistrict.htm"/>" />
						<form:hidden path="paramCode" /> 
						<input type="hidden" name="id" id="oldParamCode" path="subdistrcitCode"  />
						<br/>
						<div class="box-body">
							<div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<form:select path="districtCode" id="districtCode" class="form-control">
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
		                      	</div>
		                    </div>
		                    <div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
		                           		<button class="btn btn-success" id="btnFormActionGet" type="button" onclick="getDistrictData()" ><spring:message htmlEscape="true" code="Button.GETDATA"/></button>
										<button class="btn btn-danger" id="btnActionClose" type="button" onclick="callActionUrl('home.htm')"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
		                        	</div>
		                    	 </div>   
	                  		</div>
	                  		<c:if test="${searchResult}">
		                  		<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
		                  			<div class="row">
		                  				<div class="col-sm-12 ">
		                  					<div class="table-responsive">
		                  						<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.VIEW" htmlEscape="true"></spring:message></th>
															
															<th><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.CHANGE.EFFECTIVE.DATE" htmlEscape="true"></spring:message></th>
															<!-- <th>GIS Map View</th> -->
														</tr>
													</thead>
													<tbody>
														<c:forEach var="objects" items="${draftSubdistrictList}" varStatus="counter">
															<tr id="trdetials">
																<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
																<td><c:out value="${objects.subdistrictCode}" escapeXml="true"></c:out></td>
																<td style="word-break:break-all">
																	<c:out value="${objects.subdistrictNameEnglish}" escapeXml="true"></c:out>
																	<c:if test="${objects.operation_state == 'F'.charAt(0)}">(Subdistrict is being used as Drafted)</c:if>
																</td>
																<td style="word-break:break-all"><c:out value="${objects.subdistrictNameLocal}" escapeXml="true"></c:out></td>
																<c:choose>
																	<c:when test="${objects.operation_state == 'A'.charAt(0)}">
																		<td width="8%" align="center">
																		<a href="#" onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictDetail.htm', 'id');"><i class="fa fa-eye" aria-hidden="true"></i>
																		</a>
																     		
																		</td>
																		
																		<td align="center">
																			<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'modifySubdistrictCrbyId.htm');">
																				<i class="fa fa-pencil" aria-hidden="true"></i>

																			</a>
																		</td>
																		<td align="center">
																			<a href="#" onclick="processLinkActions('${objects.subdistrictCode}', 'changeSubdistrictDraft.htm');">
																				<i class="fa fa-pencil" aria-hidden="true"></i>

																			</a>
																		</td>
																		<%-- <td align="center">
																		   <a href="#" onclick="callGISMapView('${objects.subdistrictCode}','${objects.subdistrictNameEnglish}','MSDT')" ><i class="fa fa-map-marker" aria-hidden="true"></i></a>
																		

																		</td> --%>
																	</c:when>
																	<c:otherwise>
																		<td width="8%" align="center">
																		      <a href="#" onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictDetail.htm', 'id');"><i class="fa fa-eye" aria-hidden="true"></i>
																		      </a>
																		</td>
																		<td width="8%" align="center">
																		 <a href="#" onclick="javascript:viewEntityDetailsInPopup('${objects.subdistrictCode}', 'viewSubDistrictHistory.htm', 'id');"><i class="fa fa-history" aria-hidden="true"></i>
																		 </a>
																		</td>
																		<td align="center">
																			<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'modifySubdistrictCrbyId.htm');">
																				<i class="fa fa-pencil" aria-hidden="true"></i>

																			</a>
																		</td>
																		<!-- <td align="center"></td> -->
																		<td align="center"></td>
																		<td align="center"></td>
																		</c:otherwise>
																</c:choose>
																<td align="center">
																			<a href="#" onclick="processOldLinkActions('${objects.subdistrictCode}', 'modifySubdistrictChangeEffectiveDate.htm');">
																				<i class="fa fa-pencil" aria-hidden="true"></i>

																			</a>
																</td>
															</tr>	
														</c:forEach>
													</tbody>
		                  						</table>
		                  					</div>
		                  				</div>
		                  			</div>
		                  		</div>
	                  		</c:if>
                  		</div>
                  		<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
						<div class="modal-dialog" style="width:1050px;height:700px">
								<div class="modal-content">
					  				<div class="modal-header">
					   				  <button type="button" class="close" data-dismiss="modal">&times;</button>
					    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
					  				</div>
					  				<div class="modal-body" id="dialogBXbody">
					        			<iframe id="myIframe" width="1000" height="700"></iframe>
					  				</div>
					     			 <div class="modal-footer">
					        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      
					      			</div>
							</div>
						</div>
					</div>
	                </form:form>
				</div>
			</section>
		</div>
	</section>
<script>
      $(function () {
        $("#example").DataTable();
      });
    </script>
</body>
</html>

