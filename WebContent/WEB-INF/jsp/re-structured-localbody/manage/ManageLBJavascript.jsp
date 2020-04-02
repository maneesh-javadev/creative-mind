<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<c:set var="URBAN_PROCESS_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>

<%@include file="../CommonClientRules.jsp" %>
<script type="text/javascript">

	/**
	 * Defined Local Body Ceartion Type and State Code set in Controller Map Attribute. 
	 */
	var _JS_LOCAL_BODY_CREATION_TYPE = '${LOCAL_BODY_CREATION_TYPE}';
	//var _JS_STATE_CODE = '${stateCode}';
	var _JS_URBAN_CONSTANT = '${URBAN_PROCESS_CONSTANT}';

	
	var checkNotUrbanProcess = function (){
		if(_JS_URBAN_CONSTANT != _JS_LOCAL_BODY_CREATION_TYPE){
			return true;
		}
		return false;
	};
	
	/**
	 * Listening for 'message' from GIS. Mandatory to define this method before
	 * registaration of event listener.
	 * @link window.addEventListener("message", receiveMessageFromGIS, false)
	 *
	 */
      var receiveMessageFromGIS = function (event){  
		
		//if (event.origin == "http://10.1.43.239" ) {
       		var dataFromGIS = event.data;//'12345:Y';
       	// alert(dataFromGIS);
			 if(! $_checkEmptyObject( dataFromGIS )){
       			var dataArray = dataFromGIS.split(":");
				if("N" == dataArray[1]){
					$('#dialogBX').dialog('close');
					return false;
				}
				callGISCoverage(parseInt(dataArray[0]),'callChangeCoveredAreaPublishedLocalBody.htm','Y');
				/* dwrRestructuredLocalBodyService.saveGISStatus( dataFromGIS , {
       				callback : function( result ){
       					if("SUCCESS" == result){
       						$('#dialogBX').dialog('close');
							customAlert('Map has been saved successfully.');
       					} else {
							$('#dialogBX').dialog('close');
						}
       				},
       				async : true
       			});	 */	
       		} 
		//}
    	}
		
	/**
	 * Register event listner for GIS Communication.
	 */
	window.addEventListener("message", receiveMessageFromGIS, false);
	
	/**
	 * The {@code ready} initialized once page started excuting 
	 * and invoke all on load events.
	 */
	$(document).ready(function() {
		
		$( 'INPUT[id^=btnNewFormActionSave]' ).click(function() {
			validateFormdeails();
		});
		
		  var $confirm = $("#modalConfirmYesNo");
		$("#btnYesConfirmYesNo").off('click').click(function () {
			
	        $confirm.modal("hide");
	        formSubmitChangeParent();
	    });
	    $("#btnNoConfirmYesNo").off('click').click(function () {
	      
	        $confirm.modal("hide");
	    });
		
		if(checkNotUrbanProcess()){
			if ($.parseJSON('${!searchResult}') && '${fn:length(lbTypeHierarchy) eq 1}') {
				registerCallLocalBodyType();
			}
			$("#lbTypeHierarchy").change(function() {
				$( '#divCreateDynamicHierarchy' ).empty();
				$( '#errorLbTypeHierarchy' ).text("");
				$( this ).removeClass("error");
				registerCallLocalBodyType();
			});	
		}
		$("#localBodyType").change(function() {
			$( '#errorLocalBodyType' ).text("");
			$( this ).removeClass("error");
			if(checkNotUrbanProcess()){
				registerCallDynamicHierarchy(this);	
			}
		});
		$('form').each(function(){
	        //alert(); // or simple this.id
	        if($(this).attr('id') == "localBodyForm"){
	    		$("#localBodyForm").validate({
	                ignoreTitle: true ,
	                submitHandler: function (form) {
	                	customSubmitValidation(form);
	       			},
	                invalidHandler: function(form, validator) {
	                    var errors = validator.numberOfInvalids();
	                    if (errors) {
	                      var message = errors == 1
	                        ? 'Please correct the following error:\n'
	                        : 'Please correct the following ' + errors + ' errors.\n';
	                      var errors = "";
	                      if (validator.errorList.length > 0) {
	                          for (var x = 0; x < validator.errorList.length; x++) {
	                              errors += "\n\u25CF " + validator.errorList[x].message;
	                          }
	                      }
	                      //alert(message + errors);
	                    }
	                  } 
	            }); 
	    		validationForm(); 
	    		
	    		$( 'INPUT[id^=btnFormAction]' ).click(function() {
	    			<c:if test="${isGovernmentOrderUpload}">
	    				if(!$_checkEmptyObject($("#orderCode").val())){
	    	 				$( "#gazettePublicationUpload" ).rules( "remove" );
	    					$( "#gazettePublicationUpload" ).removeClass( "error" );
	    	 			}
	    	 		</c:if>
	    	 		$( 'SELECT[name^=contributing] option').attr("selected", "selected");
	    	 		validationForm();
	    	 		$('[name = processAction]').val($(this).attr('param'));
	    		});
	    	}
	    })
	});

	/**
	 * The {@code registerCallLocalBodyType} function to call dwr event
	 * to retrieve List of Local Body Types.
	 * @param setupCode
	 * @param setupVersion
	 */
	var registerCallLocalBodyType = function() {
		$('#localBodyType option[value != ""]').remove();
		var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
		if (!$_checkEmptyObject(_lb_type_value)) {
			var optVaues = _lb_type_value.split(',');
			var setupCode = optVaues[0]; // local body setup code
			var setupVersion = optVaues[1]; // local body setup version
			dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
				callback : populateLBType,
				errorHandler : errorLbTypeProcess,
				async : true
			});
		}
	};

	/**
	 * The {@code populateLBType} used for build drop-down box with 
	 * Local Body Type Values. 
	 */
	var populateLBType = function(result) {
		var options = $("#localBodyType");
		jQuery.each(result, function(index, obj) {
			var option = $("<option />");
			var setOptValue = obj.localBodyTypeCode + "_" + obj.tierSetupCode + "_" + obj.parentTierSetupCode + "_" + obj.lbLevel;
			option.val(setOptValue).text(obj.name);
			if (_JS_LOCAL_BODY_CREATION_TYPE != obj.lbType) {
				option.attr("disabled", true);
			}
			options.append(option);
		});
	};

	/**
	 * The {@code errorLbTypeProcess} called when error 
	 * occured while DWR call {@link buildLocalBodyHierarchy}.
	 */
	var errorLbTypeProcess = function() {
		$("#errorLocalBodyType").html("<spring:message code='Error.invalidlbtype' htmlEscape='true'/>");
	};
	
	/**
	 * The {@code registerCallDynamicHierarchy} function to call dwr event
	 * to build dynamic hierarchy based on local body type.
	 */
	var registerCallDynamicHierarchy = function(obj) {
		 $("#divCreateDynamicHierarchy").empty();
		 var divTemplate = $("#divCreateDynamicHierarchy");
		 var isDWRCallRequired = true;
		 var localBodyTypeElement = $('#localBodyType option[value != ""]');
		 
		 $(localBodyTypeElement).each(function(index){
			 var localbodyTypeArray = $(this).val().split("_");
			 var localbodyTypeCode = localbodyTypeArray[0];
			 var tiersetupCode = localbodyTypeArray[1];
			 var parentTiersetupCode = localbodyTypeArray[2];
			 if((obj.selectedIndex - 1 > index) && (obj.value != localbodyTypeCode )){
				 
			    var localbodyTypeName = $(this).text();
			    
			   /*  var templateUL = $("<div/>");
			    templateUL.addClass('form-group'); */
			    
			    // Added Li Elements for UL
				var templateLI = $("<div class=form-group/>");
				
			    // Added Label Elements
				var templateLabel = $("<label/>");
				templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandatory'>*</span>");
				templateLabel.attr("class", "col-sm-3 control-label");
				var templateDivLBLC = $("<div class=col-sm-6/>");
				
				// Added Html Select Elements
				var templateSelect = $("<select class=form-control/>");
				templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
				templateSelect.attr("name", "localBodyLevelCodes");
				$(templateSelect).change(function() {
					$( this ).removeClass("error");
					$( '#error' + $( this ).attr('id') ).text("");
					if(index != (obj.selectedIndex - 2)){
						configureLocalBodyTypeHierarchy(this);	
					}
				});
				
				var options = $(templateSelect);
				options.append($("<option />").val("").text("<spring:message htmlEscape='true' code='Label.SELECT'/>"));
				if(isDWRCallRequired){
					isDWRCallRequired = false;
					buildDistrictPanchayatOptions(options, localbodyTypeCode);
				}
				
				var templateError = $("<span/>");
				templateError.attr("id", "error" + $(templateSelect).attr('id'));
				templateError.attr("class", "errormsg");
				
				// Adding Dynamic Elements to Template Div.
				templateLI.append(templateLabel);
				templateLI.append(templateDivLBLC);
				templateDivLBLC.append(templateSelect);
				templateDivLBLC.append(templateError);
				/* templateUL.append(templateLI); */
				divTemplate.append(templateLI);
			 }
		});
	};
	
	/**
	 * The {@code configureLocalBodyTypeHierarchy} function used to configue 
	 * child nodes local bodies based on Parent LB code.
	 */
	var configureLocalBodyTypeHierarchy = function(object){
		var localBodyCode = $(object).val();
		var elementIdArray =  $(object).attr('id').split("_");
		var selectedElementId = elementIdArray[0]; 
		var selectedElementTiersetupCode = elementIdArray[1];
		var childNode = null;
		var nextParent = selectedElementTiersetupCode;
		
		$('#localBodyType option[value != ""]').each(function(index){
			
			var localbodyTypeArray = $(this).val().split("_");
			var tiersetupCode = localbodyTypeArray[1];
			var parentTiersetupCode = localbodyTypeArray[2];
			
			//Used to remove child nodes of selected Local Body.
			if(nextParent != tiersetupCode && selectedElementTiersetupCode != tiersetupCode){
				$('#' + selectedElementId + "_" + tiersetupCode + "_" + nextParent +' option[value != ""]').remove();
			}
			nextParent = tiersetupCode;
			
			//Used for assign element Id to next child nodes of selected Local Body.
			if(selectedElementTiersetupCode == parentTiersetupCode){
				childNode  = selectedElementId + "_" + tiersetupCode + "_" + selectedElementTiersetupCode;
			}
		});
		if(!$_checkEmptyObject(localBodyCode)){
			buildParentwisePanchayatOptions(childNode, localBodyCode);
		}
	};
	
 /**
	 * The {@code buildDistrictPanchayatOptions} used create select box options at 
	 * District Panchayat Level. 
	 * @param elementTemplate (Element Template for given select box instance)
	 * @param localbodyTypeCode
	 */
	var buildDistrictPanchayatOptions = function(elementTemplate, localbodyTypeCode) {
		if(isDistrictUser()){
			dwrRestructuredLocalBodyService.getDistrictPanchayatListForDistrictUser(parseInt(localbodyTypeCode), parseInt(_JS_DISTRICT_CODE), null, null,{
				callback : callbackHandlerForDPOptions,
				callbackArg : elementTemplate,
				async : true
			});	
		}else{
			dwrRestructuredLocalBodyService.getDistrictPanchayatList(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), null, null,{
				callback : callbackHandlerForDPOptions,
				callbackArg : elementTemplate,
				async : true
			});	
		}
	};
	
	/**
	 * The {@code callbackHandlerForDPOptions} used create select box options at 
	 * Local Body Level using selected local body code for district and state users. 
	 * @param result Object Array returns from call back
	 * @param elementTemplate (Element Template for given select box instance)
	 */
	var callbackHandlerForDPOptions = function (result, elementTemplate){
		jQuery.each(result, function(index, obj) {
			var optionText = obj.localBodyNameEnglish;
			var option = $("<option />");
			if (obj.isDisturbed) {
				option.attr("disabled", true);
				optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
			}
			if (obj.isUsed) {
				option.attr("disabled", true);
				optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
			}
			option.val(obj.localBodyCode).text(optionText);
			elementTemplate.append(option);
		});
	};
	 	
	/**
	 * The {@code buildParentwisePanchayatOptions} used create select box options at 
	 * Local Body Level using selected local body code as parent. 
	 * @param elementNode (Element Template for given select box instance)
	 * @param localBodyCode
	 */
	var buildParentwisePanchayatOptions = function (elementNode , localBodyCode){
		dwrRestructuredLocalBodyService.getParentwiseLocalBody(parseInt(localBodyCode), null, null, {
			callback : function(result){
				var options = $("#" + elementNode);
				jQuery.each(result, function(index, obj) {
					var optionText = obj.localBodyNameEnglish;
					var option = $("<option />");
					if (obj.isDisturbed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
						
					}
					if (obj.isUsed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
					}
					option.val(obj.localBodyCode).text(optionText);
					options.append(option);
				});
			},
			async : true
		});	
	};
	
	/**
	 * The {@code validateLBCode} function used to check for valid  
	 * local body code at different levels.
	 */
	var validateLBCode = function (localBodyElement){
		if($_checkEmptyObject($( localBodyElement ).val())){
			 var elements = $( '[name = localBodyLevelCodes]' );
			 $(elements).each(function(index){
				 
				 if($_checkEmptyObject($( this ).val())){
					$( this ).addClass("error");
					$( '#error' + $( this ).attr('id') ).text("<spring:message code='Label.SELLOCALBODYTYPE' htmlEscape='true'/>");	 
				 }
			 });
			 return false;
		}
		return true; 
	};
	
	var callGISMapView = function ( localBodyCode, localBodyName,isShowOnlyBoundary,updateApprovedGP ){
		//alert("updateApprovedGP:"+updateApprovedGP);
		dwrRestructuredLocalBodyService.getMappedLBsForGIS(parseInt(localBodyCode),localBodyName,isShowOnlyBoundary,updateApprovedGP, {
			callback : function( result ){
				//alert(result);
				if("FAILED" == result){
					customAlert(result);
				}else{
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
					
					$("#dialogBXTitle").text('GIS Map View ( Local Body Code : ' + localBodyCode + ' , Local Body Name : ' + localBodyName + ' )');
					
					 $('#myIframe').attr('src', result);
					/*  $("#myIframe").load(function(){
		            	alert("test"); 
				    }); */
					$('#dialogBX').modal('show'); 
				}
			},
			errorHandler : function( errorString, exception){
					customAlert(exception);
			},
			async : true
		});		
	};
	
	var callPreviousNamesView = function ( localBodyCode ){
		
		$('#myModal1').modal('show'); 
		
		/* $("#divPreviousNames").dialog({
			title:'List of modified names of Local Body',
		    modal: true,
		    width:500,
		    height: 500,
		    resizable:false
		});	 */
	};
</script>
<c:if test="${(URBAN_PROCESS_CONSTANT ne LOCAL_BODY_CREATION_TYPE) and searchResult }">
	<script type='text/javascript'>
		$(window).load(function () {
			$('#localBodyType option[value != ""]').remove();
			var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
			if (!$_checkEmptyObject(_lb_type_value)) {
				var optVaues = _lb_type_value.split(',');
				var setupCode = optVaues[0]; // local body setup code
				var setupVersion = optVaues[1]; // local body setup version
				dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
					callback : function(result) {
						populateLBType(result);
						$("#localBodyType option[value='${criteriaLocalBodies.paramLocalBodyTypeCode}']").attr("selected", "selected");
						registerCallDynamicHierarchy($('#localBodyType').get(0));
						setTimeout(function(){
							var localbodyLevelCodes =  '${criteriaLocalBodies.localBodyLevelCodes}'.split(",");
							var localBodyLevelElement = $("SELECT[name='localBodyLevelCodes']");
							var elementCount = $(localBodyLevelElement).size()-1;
							$(localBodyLevelElement).each(function(index){
								$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
								if(index < elementCount ){
									configureLocalBodyTypeHierarchy(this);	
								}
								if(index == elementCount ){
									var elementId = $(this).attr('id');
									setTimeout(function(){
										$("#" + elementId +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
									},200);
								}
							});
						}, 200);
					},
					errorHandler : errorLbTypeProcess,
					async : true
				});
			}
		}); 
	</script>
</c:if>
<!--#stared page return from server with error then call loadElementandShowError  method -->
<c:if test="${criteriaLocalBodies.updateApprovedGP eq true}">
			<script>
			$(window).load(function () {
				callGISMapView( '${criteriaLocalBodies.localBodyCode}','${criteriaLocalBodies.localBodyNameEnglish}',false,true);
			}); 
			</script>
</c:if>
<!--#end page return from server with error then call loadElementandShowError  method -->
