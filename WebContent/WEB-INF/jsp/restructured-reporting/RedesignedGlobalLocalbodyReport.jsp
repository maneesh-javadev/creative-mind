<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="RedesignedGlobalLocalbodyReportJS.jsp"%>

<script type="text/javascript" class="init">
	$(document).ready(function() {
	   
		$('#example').DataTable({
			"bJQueryUI" : false
		});
		
		$("[id^=searchBy]").change(function() {
			$('#' + $(this).attr('paramShow')).show();
			$('#' + $(this).attr('paramHide')).hide();
			$('#entityCode').val('');
			$('#entityName').val('');
			$('#ddSourceState').val('');
			
		});
		
		$( '[id = gisMapView]' ).click(function() {
			var localBodyCode = $(this).attr('paramGPCode');
			var localBodyName = $(this).attr('paramLBNameEng');
			callGISMapView( localBodyCode, localBodyName,true,false);
		});
		
	    $("#ddSourceState").change(function() {
	    $( '#divCreateDynamicHierarchy' ).empty();
	    $( '#localBodyType' ).val('');
	    
	    $( this ).removeClass("error");
	    $( '#error' + $( this ).attr('id') ).text("");
	   	buildLocalbodyHierarchy($(this).val());
	    });
	    
	    $("#lbTypeHierarchy").change(function() {
			$( '#divCreateDynamicHierarchy' ).empty();
			$( '#errorLbTypeHierarchy' ).text("");
			$( this ).removeClass("error");
			registerCallLocalBodyType();
		});	
	    
	    $("#localBodyType").change(function() {
	    	$( '#errorLocalBodyType' ).text("");
	    	$( this ).removeClass("error");
	    	registerCallDynamicHierarchy(this);	
	    });
	    
	    $("#entityCode").change(function() {
	    	$(entityCode).removeClass("error");
			$(entityName).removeClass("error");
			$( '#errorentityCodeorentityName' ).text("");
	    });
	    
	    $("#entityName").change(function() {
	    	$(entityCode).removeClass("error");
			$(entityName).removeClass("error");
			$( '#errorentityCodeorentityName' ).text("");
	    });

	    $( "#actionFetchDetails" ).click(function() {
	    	var radioVal = $('input:radio[name="searchCriteriaType"]').filter(":checked").val();
	    	if(radioVal == "N"){
				var entityCode = $('#entityCode'), entityName = $('#entityName');
				if($_checkEmptyObject(entityCode.val()) && $_checkEmptyObject(entityName.val())){
					$(entityCode).addClass("error");
					$(entityName).addClass("error");
					$( '#errorentityCodeorentityName' ).text("Please enter Localbody Code or enter Localbody Name");
					return false;	
				
				}
			} else{
				var stateId=$("#ddSourceState");
		    	if( $_checkEmptyObject($( stateId ).val()) ){
					$(stateId).addClass("error");
					$( '#errorddSourceState' ).text("<spring:message code='Label.SELECTSTATE' htmlEscape='true'/>");
					return false;
				}
		    	
		    	var lbTypeHierarchylement = $( '#lbTypeHierarchy' );
					if( $_checkEmptyObject($( lbTypeHierarchylement ).val()) ){
						$(lbTypeHierarchylement).addClass("error");
						$( '#errorLbTypeHierarchy' ).text("<spring:message code='error.CHOOSEHIERARCHY' htmlEscape='true'/>");
						return false;
					}
				
				var localBodyTypeElement = $( '#localBodyType' );
				var selectedlocalBodyType = $( localBodyTypeElement ).val();
				if( $_checkEmptyObject(selectedlocalBodyType) ){
					$(localBodyTypeElement).addClass("error");
					$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
					return false;
				}
				
					var element = $( '[name = localBodyLevelCodes]' );
					var localBodyElement = $( element )[$( element ).length - 1];
					if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
						return false;
					}
				
			}
	    	
	    	var captchaAnswer=$("#captchaAnswer" );
	    	if( $_checkEmptyObject(captchaAnswer.val()) ){
	    		$(captchaAnswer).addClass("error");
	    		$( '#errorcaptchaAnswer' ).text("<spring:message code='error.enter.captcha' htmlEscape='true'/>");
	    		return false;
	    	
	    	}
	    	buildHierachyMessage();
			callActionUrl('globalViewLocalBodyForCitizen.do');
		});
	    	var callGISMapView = function ( localBodyCode, localBodyName,isShowOnlyBoundary,updateApprovedGP ){
		//alert("updateApprovedGP:"+updateApprovedGP);
		dwrRestructuredLocalBodyService.getMappedLBsForGIS(parseInt(localBodyCode),localBodyName,isShowOnlyBoundary,updateApprovedGP, {
			callback : function( result ){
				
				//alert(result);
				if("FAILED" == result){
					customAlert("GIS Coverage details not available");
				}else{
					$("#dialogBX").dialog({
						title:'GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )',
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
			},
			errorHandler : function( errorString, exception){
					customAlert(exception);
			},
			async : true
		});		
	};
	
	    $("#entityCode").numeric({ decimal: false, negative: false }, function() {
			this.value = ""; this.focus(); 
		});
	});
	
	
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message></h3>
		</div>
		<div class="page_content">
			<div class="form_container">
				<form:form action="globalViewLocalBodyForCitizen.do" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalViewLocalBodyForCitizen.do"/>" />
					<form:hidden  path="entitesForMessage" id="entitesForMsg"/>
					<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
					</div>
					<c:choose>
					<c:when test="${showSearchHierarchy}">
						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message htmlEscape="true" code="Label.FilterLBOption"></spring:message></h4>
									<ul class="form_body" >
										<li>
											<form:radiobutton path="searchCriteriaType" id='searchByName' value="N" checked="checked" paramShow="displayNameCode" paramHide="displayHierarchy"/>
											<spring:message code="Label.SEARCHBYNAME"/>&nbsp;&nbsp;
									
											<form:radiobutton path="searchCriteriaType" id='searchByHierarchy' value="P" paramShow="displayHierarchy" paramHide="displayNameCode"/>
											<spring:message code="Label.SEARCHBYHIERARCHY"/>
										</li>
									</ul>	
									<h4><!-- Used header for blank head, please dont remove.  --></h4>
									<ul class="form_body" >
										<div id="displayNameCode">
											<li>
												<label><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></label>
												<form:input id="entityCode" path="paramEntityCode" />
												
											</li>
											<li>
												<label><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"></spring:message></label>
												<form:input id="entityName" path="paramEntityName" />
												
											</li>
											<span class="errormessage" id="errorentityCodeorentityName"></span>
										</div>
										<div id="displayHierarchy" style="display: none;">
											<li>
												<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
												<form:select path="paramStateCode" class="combofield" id="ddSourceState" onchange="error_remove();">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select>
												<span class="errormessage" id="errorddSourceState"></span>
											</li>
											<li>
											<label>
												<spring:message code='Label.Selecthierarchylevel' htmlEscape='true'/>
												<span class="mandate">*</span>
											</label>
											<form:select path="" id="lbTypeHierarchy">
												<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
											</form:select>
											<span class="errormessage" id="errorLbTypeHierarchy"></span>
										</li>
										<li>
										<label>
											<spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'/>
											<span class="mandate">*</span>
										</label>
										<form:select path="localBodyTypeParam" id="localBodyType">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
											<c:if test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE }">
												<c:forEach items="${localBodyTypeList}" var="objLBTypes">
													<form:option value="${objLBTypes.localBodyTypeCode}">
														<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
													</form:option>
												</c:forEach>
											</c:if>
										</form:select>
										<span class="errormessage" id="errorLocalBodyType"></span>
									</li>
										<li>
											<div id="divCreateDynamicHierarchy">
												<!-- This Div used to generate Hierarchy -->
											</div>
									
										</li>
										</div>
										
										<li>
											<label><!-- Used Label, please dont remove. --></label>
											<img src="captchaImage" alt="Captcha Image" />
										</li>
										<li>
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandate">*</span>
											</label>
											<form:input	path="captchaAnswer" id="captchaAnswer" autocomplete="off" />
											<span class="errormessage" id="errorcaptchaAnswer"></span>
											<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
											</c:if>
										</li>
										<li>
										    <label class="inline">&nbsp;</label>
											<input type="button" id="actionFetchDetails" value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />
											<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>	
										</li>
									</ul>
								</div>
							</div>
						</div>
					</c:when>
				    <c:otherwise>
					  <div class="form_stylings">
							<div class="form_block" id="divData">
								<div class="col_1">
									<h4>
										<c:choose>
											<c:when test="${radioButton eq 'P'}">
												<c:out value="${genericReportingEntity.entitesForMessage}"/>		
											</c:when>
											<c:otherwise>Localbody Details</c:otherwise>
										</c:choose>
									</h4>
									<ul class="form_body" >
										<li>
											<table id="example2" class="display" cellspacing="0" width="100%">
												<thead>
													<tr>
														<th ><spring:message code="Label.SNO"/></th>
														<th ><spring:message code="Label.LOCALBODYCODE"/></th>
														<th ><spring:message code="Label.LOCALBODYNAMEINENG"/></th>
														
														
														<th ><spring:message code="Label.CENSUS2001"/></th>
														<th ><spring:message code="Label.CENSUSCODE2011"/></th>
														
														<th ><spring:message htmlEscape="true" code="Label.VIEW.DETAIL"/></th>
														<th ><spring:message htmlEscape="true" code="Label.VIEW.History" text="View History"/></th>		
														<th ><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"/></th>
														<th>GIS Map View</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="result" varStatus="rowstatus" items="${SEARCH_Localbody_RESULTS_KEY}">
														<tr >
															<td width="5%"><c:out value="${rowstatus.count}"/></td>
															<td><c:out value="${result.localBodyCode}"></c:out></td>
															<td align="left"><c:out value="${result.localBodyNameEng}"></c:out></td>
															
															<td align="left"><c:out value="${result.census2001Code}"></c:out></td>
															<td align="left"><c:out value="${result.census2011Code}"></c:out></td>
															
													     	<td width="8%" align="center">
													     		<img src="images/view.png" width="18" height="19" border="0" alt="View Details" 
													     			 onclick="javascript:viewEntityDetailsInPopup('${result.localBodyCode}', 'viewEntityDetail.do', 'code');"/>
															</td>
															<td width="8%" align="center">
																<img src="images/history.png" width="18" height="19" border="0" alt="View Details"  
																	 onclick="javascript:viewEntityDetailsInPopupForHistory(${result.localBodyCode}, 'viewLocalBodyHistoryReports.do', 'lbCode');"/>
															</td>
															<td width="10%" align="center">
																<c:if test="${result.fileName ne '' }">
																		<img src="images/gvt.order.png" onclick="javascript:retrieveFile1('${result.localBodyCode}', 'G');" 
																			 width="18" height="19" border="0" alt="View Details"/>
															    </c:if>
														    </td>
														    
														    <td  align="center">
														    <c:if test="${result.gisMapStatus eq 'A' }">
																<img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" paramGPCode="${result.localBodyCode}" paramLBNameEng="${result.localBodyNameEng}"/>
																</c:if>
															</td>
															
													   		
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</li>
									</ul>	
								<!-- </div>
							</div> -->
						</div>
						</div>
						</div>
						<div class="buttons center" id="showbutton">
							<input type="button" value="<spring:message code="Button.BACK"/>" onclick="javascript:location.href='globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>'"/>
							<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
						</div> 
					</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
 	<!-- <script type='text/javascript'>
		$(window).load(function () {
			var radioObj = $('input:radio[name="searchCriteriaType"]').filter('[value="<c:out value='${radioButton}'/>"]') ;
			$('#' + radioObj.attr('paramShow')).show();
			$('#' + radioObj.attr('paramHide')).hide();
			if(isParseJson('<c:out value="${not empty captchaFaliedMessage and not captchaFaliedMessage}"/>') && 
			   isParseJson('<c:out value="${not empty genericReportingEntity.paramStateCode}"/>')){
				buildDistrict('<c:out value="${genericReportingEntity.paramStateCode}"/>');
				setTimeout(function(){
					$("#ddSourceDistrict option[value='<c:out value="${genericReportingEntity.paramDistrictCode}"/>']").attr("selected", "selected");
					buildSubDistrict('<c:out value="${genericReportingEntity.paramDistrictCode}"/>');
					setTimeout(function(){
						$("#ddSourceSubDistrict option[value='<c:out value="${genericReportingEntity.paramSubDistrictCode}"/>']").attr("selected", "selected");
					}, 200);
				}, 200);
			}
		});
	</script>	 -->	
</body>
</html>