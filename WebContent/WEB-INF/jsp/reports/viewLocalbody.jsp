<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="RedesignedGlobalLocalbodyReportJS.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" class="init">


 function selLevel(){
		  var state=$('#ddSourceState').val();
		 $( '#divCreateDynamicHierarchy' ).empty();
		 $( '#localBodyType' ).val('');
		 $( '#ddSourceState' ).removeClass("error");
		buildLocalbodyHierarchy(state);
    }
 
 
  function selLocalBody(){
	  var type=$('#lbTypeHierarchy').val();
	    $( '#divCreateDynamicHierarchy' ).empty();
		$( '#errorLbTypeHierarchy' ).text("");
		$( '#lbTypeHierarchy' ).removeClass("error");
		registerCallLocalBodyType();
	 
  }
  
  function typeHierarchy(obj){
	  var hierarchycode=$('#localBodyType');
	  $( '#errorLocalBodyType' ).text("");
  	  $( '#localBodyType').removeClass("error");
  	registerCallDynamicHierarchy(obj);	
	  
  }
  
  
  $(document).ready(function() {
	  
	  $('#dialogBX').draggable({
		    handle: ".modal-header"
		});
	  
	  $('#example').dataTable({
	        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
	         "scrollX": true
	    });
  });	

  
  
  
  var callGISMapView = function ( localBodyCode, localBodyName,isShowOnlyBoundary,updateApprovedGP ){
		//alert("updateApprovedGP:"+updateApprovedGP);
		dwrRestructuredLocalBodyService.getMappedLBsForGIS(parseInt(localBodyCode),localBodyName,isShowOnlyBoundary,updateApprovedGP, {
			callback : function( result ){
				
				//alert(result);
				if("FAILED" == result){
					alert("GIS Coverage details not available");
				}else{
					
					$("#dialogBXTitle").text('GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )');
					
					 $('#myIframe').attr('src', result);
					/*  $("#myIframe").load(function(){
		            	alert("test"); 
				    }); */
					$('#dialogBX').modal('show'); 
					
					/* $("#dialogBX").dialog({
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
					});	 */
				}
			},
			errorHandler : function( errorString, exception){
					alert(exception);
			},
			async : true
		});		
	};
	
	$('#example').dataTable({
        "lengthMenu": [[ 50,100, -1], [ 50,100, "All"]],
         "scrollX": true
    });
	
	
	function submitData(){
		
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
	
		var captchaAnswer=$("#captchaAnswer" );
    	if( $_checkEmptyObject(captchaAnswer.val()) ){
    		$(captchaAnswer).addClass("error");
    		$( '#captchaerrorMessage' ).text("Please Enter CAPTCHA in the textbox");
    		return false;
    	
    	}
    	
    	buildHierachyMessage();
		callActionUrl('globalViewLocalBodyForCitizen.do');
		
	}

	  
	
</script>

</head>
<body>
	
<section class="content">

<!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	
                
                <!-- form start -->
                <form:form action="globalViewLocalBodyForCitizen.do" class="form-horizontal" method="POST" id="genericReportingEntity" commandName="genericReportingEntity">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="globalViewLocalBodyForCitizen.do"/>" />
						<form:hidden path="entitesForMessage" id="entitesForMsg"/>
							<!-- div id="dialogBX" style="display: none;">
								<iframe id="myIframe" width="910" height="650"></iframe>
							</div> -->
					<c:choose>
					<c:when test="${showSearchHierarchy}">
					<div class="box">
				            <div class="box-header with-border">
				              <h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message></h3>
				            </div><!-- /.box-header -->
					<div  id="divCenterAligned">
						<div class="box-body">
		                 <%--  <div id="displayNameCode" class="form-group ">
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityCode" path="paramEntityCode" class="form-control" /><br>
		                     </div>
	                       
		                      <label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:input id="entityName" path="paramEntityName" class="form-control" /><br>
		                     </div>
		                     	<span class="mandatory" id="errorentityCodeorentityName"></span>
	                       </div> --%> 
	                       
	                       <div id="displayHierarchy"  >
	                        <div class="form-group">
		                      <label class="col-sm-4 control-label" for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="paramStateCode" class="form-control" id="ddSourceState" onchange="selLevel();">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select>
												<span class="mandatory" id="errorddSourceState"></span>
		                     </div>
		                     </div>
		                     
		                      <div class="form-group">
		                      <label class="col-sm-4 control-label"><spring:message code='Label.Selecthierarchylevel' htmlEscape='true'></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="" id="lbTypeHierarchy" class="form-control" onchange="selLocalBody();">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
								</form:select>
								<span class="mandatory" id="errorLbTypeHierarchy"></span>
		                     </div>
		                     </div>
		                   <div class="form-group">
		                     <label class="col-sm-4 control-label"><spring:message code='Label.SELECTLOCALBODYTYPE' htmlEscape='true'></spring:message><span class="mandatory">*</span></label>
		                      <div class="col-sm-6">
		                      	<form:select path="localBodyTypeParam" id="localBodyType" class="form-control" onchange="typeHierarchy(this);">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<c:if test="${URBAN_PROCESS_CONSTANT eq LOCAL_BODY_CREATION_TYPE }">
											<c:forEach items="${localBodyTypeList}" var="objLBTypes">
												<form:option value="${objLBTypes.localBodyTypeCode}">
													<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
												</form:option>
											</c:forEach>
										</c:if>
								</form:select>
								
								<span class="mandatory" id="errorLocalBodyType"></span>
		                     </div>
		                     </div>
		                  </div>
		                  
		                  	<div id="divCreateDynamicHierarchy">
									<!-- This Div used to generate Hierarchy -->
							</div>
	                       
	                      <div class="box-body">
		                    <div class="form-group">
		  <%-- <label  class="col-sm-4 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message> <span class="mandatory">*</span></label> --%>
		   
		    <label  class="col-sm-4 control-label" for="sel1"></label>
		    <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
		                   <div class="form-group">
		   <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px" maxlength="5"/>
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" cssStyle="color:red"/>
		                     <span class="mandatory" id="captchaerrorMessage"></span>
		                     
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
		                       </div>
	                    	</div>
	                 	
					  </div>
					  </div>
					  
						<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		<button type="button" class="btn btn-success " id="actionFetchDetails" onclick="submitData();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
		                   </div>
		                   </div>
		                  </c:when>
		                  <c:otherwise>
		                  
		                 <div class="box">
		                  
		                  
		                 	<div class="box-body" id="divData">
							 <div class="box-header subheading"> <h4>
								
								<c:out value="${genericReportingEntity.entitesForMessage}"/>	
							 </h4></div>
							     <div class="table-responsive ">
							 
				                   <table class="table table-bordered table-striped dataTable no-footer" id="example" >
										<thead>
											<tr>
												<th ><spring:message code="Label.SNO"></spring:message></th>
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
												     	<a href="#" onclick="javascript:viewEntityDetailsInPopup('${result.localBodyCode}', 'viewEntityDetail.do', 'code');"><i class="fa fa-eye" aria-hidden="true"></i>
												     	</a>
												     		
														</td>
														<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopupForHistory(${result.localBodyCode}, 'viewLocalBodyHistoryReports.do', 'lbCode');"><i class="fa fa-history" aria-hidden="true"></i>
														</a>
														</td>
														<td width="10%" align="center">
															<c:if test="${result.fileName ne '' }">
															<a href="#" onclick="javascript:retrieveFile1('${result.localBodyCode}', 'G');"><i class="fa fa-file-text-o" aria-hidden="true"></i>
															</a>
																	
														    </c:if>
													    </td>
													    
													    <td  align="center">
													    <c:if test="${result.gisMapStatus eq 'A' }">
													  	  <a href="#" id ="gisMapView"  onclick="callGISMapView('${result.localBodyCode}','${result.localBodyNameEng}',true,false);" ><i class="fa fa-map-marker" aria-hidden="true"></i>  </a>
														</c:if>	
														</td>
														
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
				           	 </div>
				           	 
				           	 <!-- Button for Page after POST method @Ashish 18 aUG -->	
					  
				 <div class="box-footer" id="showbutton">
                    <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
							<button type="button" class="btn btn-primary" onclick="javascript:location.href='globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>'"><spring:message code="Button.BACK"/></button>
							<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
				  </div>
				           	 
		               </div>
		                 </c:otherwise>
		             </c:choose>
		                  
					
				  </form:form>
					<!-- <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
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
							</div> -->
		</section>
		</div>
	</div>
</section>

 
 		
</body>
</html>