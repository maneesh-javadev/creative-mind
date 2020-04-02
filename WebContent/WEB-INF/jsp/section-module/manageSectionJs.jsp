<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/js/bootstrap-dialog.min.js"></script>

<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js"></script>
<script type='text/javascript'src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>

<c:set var="entityTypeLB" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.PARENT_TYPE_LOCALBODY.toString()%>"></c:set>
<c:set var="entityTypeOrg" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.PARENT_TYPE_ORGANIZATION.toString()%>"></c:set>
<c:set var="entitySpecific" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.ENTITY_SPECIFIC.toString()%>"></c:set>
<c:set var="entityFull" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.ENTITY_FULL.toString()%>"></c:set>
<c:set var="entityFullTopLB" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.ENTITY_FULL_WITH_TOP_LOCALBODY.toString()%>"></c:set>
<c:set var="orgTypeCodeMinist" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_MINIST.toString()%>"></c:set>
<c:set var="orgTypeCodeDept" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()%>"></c:set>
<c:set var="orgTypeCodeOrg" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_ORG.toString()%>"></c:set>
<c:set var="isCenter" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.IS_CENTER.toString()%>"></c:set>

<script>

/**
 * Declare local variable using in script
 */
var _JS_STATE_CODE='${sectionForm.slc}';
var _select_lbname;
var _TOP_LOCALBODY_FLAG=false;
var _JS_SELECT_OLC;
var isCenter=false;
var _pause_time=500;
var isCenter=('${sectionForm.isCenterorstate}'=='${isCenter}')?true:false;
var orgListIdArray=["orgType","org","orgLevel","orgOffice","specificOrgList"];
var orgCenterListIdArray=["ministryId","centerDeptId","centerOrgId","orgLevel","orgOffice","specificOrgList"];
var deptCenterListIdArray=["ministryId","centerDeptId","orgLevel","orgOffice","specificOrgList"];

$(document).ready(function() {

	showHiheStateorCenterDiv();
	
	
	 $("#btnFormActionGet").click(function() {
		  validateGeneralDetails(); 
	  });
	 
	
	 
	 var dt = $('#example').DataTable({
			"bJQueryUI": false,
			
		});
	 
	 var dt = $('#reactiveTable').DataTable({
			"bJQueryUI": false,
			
		});
	 
		
});


/**
 * The {@code showHideOption} used show and build localbody or organization div
 * according to parent type value 'G' for localbody, 'O' for organization
 */
showHideOption=function(){
	$("#errrparentType").text("");
	if ($("#lbEntityType").is(':checked')) {
		$("#divEntityTypeOrg").hide(); 
		$("#divEntityTypeLB").show(); 
		$("#parentType").val($("#lbEntityType").val())
		buildEntityTypeLocalbodyDiv();
	}else  if ($("#orgEntityType").is(':checked')){
		$("#divEntityTypeLB").hide(); 
		$("#divEntityTypeOrg").show();
		$("#parentType").val($("#orgEntityType").val())
		emptyDetails("G",false);
		buildEntityTypeOrganisationDiv();
		
	}
};


/**
 * The {@code buildEntityTypeLocalbodyDiv} used to build localbody elements
 */
buildEntityTypeLocalbodyDiv=function(){
	 $("#dynamicLbStructure").empty();
	 var divTemplate = $("#dynamicLbStructure");
	 var isDWRCallRequired = true;
	 
	 var templateUL = $("<div/>");
	     templateUL.addClass('box-body');
	     
	 // Added Li Elements for UL
	 var templateLIHierarchy = $("<div class=form-group/>");
		    
	 // Added Label Elements
	 var templateLabelHierarchy = $("<label class=col-sm-3 control-label/>");
		 templateLabelHierarchy.html("<spring:message htmlEscape='true' code='Label.Selecthierarchylevel'/>  <span class='mandatory'>*</span>");
		 
	 var templateDivGP = $("<div class=col-sm-6/>");
			
	// Added Html Select Elements
		var templateSelectHierarchy = $("<select class=form-control/>");
		templateSelectHierarchy.attr("id", "lbTypeHierarchy" );
		templateSelectHierarchy.attr("name", "lbTypeHierarchy" );
		$(templateSelectHierarchy).change(function() {
			$( this ).removeClass("error");
			$( '#error' + $( this ).attr('id') ).text("");
			if(!$_checkEmptyObject($( this ).val())){
				registerCallLocalBodyType();	
			}
		});
	
		// Added HTML SPAN Element
		var templateErrorHierarchy = $("<span/>");
		templateErrorHierarchy.attr("id", "error" + $(templateSelectHierarchy).attr('id'));
		templateErrorHierarchy.attr("class", "errormsg");
		
		
		 var templateLILBType = $("<div class=form-group/>");
		    
		 // Added Label Elements
		 var templateLabelLBType = $("<label class=col-sm-3 control-label/>");
			 templateLabelLBType.html("<spring:message htmlEscape='true' code='Label.SELECTLOCALBODYTYPE'/>  <span class='mandatory'>*</span>");
		
		 var templateDivGPL = $("<div class=col-sm-6/>");
			 
		// Added Html Select Elements
			var templateSelectLBType = $("<select class=form-control/>");
			templateSelectLBType.attr("id", "localBodyType" );
			templateSelectLBType.attr("name", "localBodyType" );
			$(templateSelectLBType).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(!$_checkEmptyObject($( this ).val())){
					registerCallDynamicHierarchy(this);	
				}
			});
		
			// Added HTML SPAN Element
			var templateErrorLBType = $("<span/>");
			templateErrorLBType.attr("id", "error" + $(templateSelectLBType).attr('id'));
			templateErrorLBType.attr("class", "errormsg");
		
			
			 var templateLIDiv = $("<div/>");
			 var templateDiv = $("<div/>");
			 templateDiv.attr("id", "divCreateDynamicHierarchy");
			 
			
			 
			 
		
			templateLIHierarchy.append(templateLabelHierarchy);
			templateLIHierarchy.append(templateDivGP);
			templateDivGP.append(templateSelectHierarchy);
			templateDivGP.append(templateErrorHierarchy);
		    templateUL.append(templateLIHierarchy);
		
		    templateLILBType.append(templateLabelLBType);
		    templateLILBType.append(templateDivGPL);
		    templateDivGPL.append(templateSelectLBType);
		    templateDivGPL.append(templateErrorLBType);
		    templateUL.append(templateLILBType);
		
		    templateLIDiv.append(templateDiv);
		    templateUL.append(templateLIDiv);
		    
		    divTemplate.append(templateUL);
		
		    buildLocalbodyHierarchy(_JS_STATE_CODE);
		
		
			
};

/**
 * The {@code populateLBType} used to fill localbody hierarchy 
 */
var buildLocalbodyHierarchy = function(stateCode){
	removeAllOptions('lbTypeHierarchy')
	dwrRestructuredLocalBodyService.getLBTypeHierarchyStateWiseDetials(parseInt(stateCode), {
		callback : function( result ) {
			var options = $("#lbTypeHierarchy"); 
			var option = $("<option/>");
			$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
			options.append(option);
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				var _obj_value=obj.localBodySetupCode+","+obj.localBodySetupVersion+","+obj.hierarchyType;
				option.val(_obj_value).text(obj.localBodyTypeHierarchy);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
};

var registerCallLocalBodyType = function() {
	
	emptyDetails("G",true);
	$('#localBodyType option[value != ""]').remove();
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchyWithCategory(parseInt(setupCode), parseInt(setupVersion), {
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
	 $("#divCreateDynamicHierarchy").empty();
	 removeAllOptions('localBodyType');
	 var options = $("#localBodyType");
	var option = $("<option/>");
	$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
	options.append(option);
	jQuery.each(result, function(index, obj) {
		var option = $("<option />");
		var setOptValue = obj.localBodyTypeCode + "_" + obj.tierSetupCode + "_" + obj.parentTierSetupCode + "_" + obj.lbLevel+"_"+obj.lbCategory;
		option.val(setOptValue).text(obj.name);
		/* if (_JS_LOCAL_BODY_CREATION_TYPE != obj.lbType) {
			option.attr("disabled", true);
		} */
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
	 emptyDetails("G",true);
	 $("#divCreateDynamicHierarchy").empty();
	 var divTemplate = $("#divCreateDynamicHierarchy");
	 var isDWRCallRequired = true;
	 var localBodyTypeElement = $('#localBodyType option[value != ""]');
	 
	 var selecedLbTypeCode=obj.value.split("_")[0];
	 
	 $(localBodyTypeElement).each(function(index){
		 var localbodyTypeArray = $(this).val().split("_");
		 var localbodyTypeCode = localbodyTypeArray[0];
		 var tiersetupCode = localbodyTypeArray[1];
		 var parentTiersetupCode = localbodyTypeArray[2];
		 var category= localbodyTypeArray[4];
		 //alert(category);
		 if(obj.selectedIndex-1==index){
			 _select_lbname= $(this).text();;
			   // alert(_select_lbname+":"+obj.selectedIndex+":"+index); 
		 }
		 
		// alert("obj.value:"+obj.value+"localbodyTypeCode:"+localbodyTypeCode+"objArray[0]:"+selecedLbTypeCode);
		 //alert((parseInt(selecedLbTypeCode) >= parseInt(localbodyTypeCode )));
		 
		 if( ( category!="U" && (parseInt(selecedLbTypeCode) >= parseInt(localbodyTypeCode ))) || (category=="U" && (parseInt(selecedLbTypeCode) == parseInt(localbodyTypeCode) ) ) ){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<div/>");
		    templateUL.addClass('box-body');
		    
		    // Added Li Elements for UL
			var templateLI = $("<div class=form-group/>");
			
		    // Added Label Elements
			var templateLabel = $("<label class=col-sm-3 control-label/>");
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandatory'>*</span>");
			
			var templateDivGPLB = $("<div class=col-sm-6/>");
			
			// Added Html Select Elements
			var templateSelect = $("<select class=form-control/>");
			templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
			templateSelect.attr("name", "localBodyLevelCodes");
			$(templateSelect).change(function() {
				 emptyDetails("G",true);
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(index != (obj.selectedIndex - 1)){
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
			templateLI.append(templateDivGPLB);
			templateDivGPLB.append(templateSelect);
			templateDivGPLB.append(templateError);
			templateUL.append(templateLI);
			divTemplate.append(templateUL);
		 }
	});
};




/**
 * The {@code configureLocalBodyTypeHierarchy} function used to configue 
 * child nodes local bodies based on Parent LB code.
 */
var configureLocalBodyTypeHierarchy = function(object){
	 emptyDetails("G",true);
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
	
		dwrRestructuredLocalBodyService.getDistrictPanchayatList(parseInt(localbodyTypeCode), parseInt(_JS_STATE_CODE), null, null,{
			callback : callbackHandlerForDPOptions,
			callbackArg : elementTemplate,
			async : true
		});	
	
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
 * The {@code buildEntityTypeOrganisationDiv} used to build Organisation elements
 */
buildEntityTypeOrganisationDiv=function(){
	 $("#dynamicOrgStructure").empty();
	 var divTemplate = $("#dynamicOrgStructure");
	 var isDWRCallRequired = true;
	 
	 
	     
	 // Added Li Elements for UL
	 var templateLIOrgType = $("<div class=form-group/>");
		    
	 // Added Label Elements
	 var templateLabelOrgType = $("<label class=col-sm-3 control-label/>");
		 templateLabelOrgType.html("<spring:message htmlEscape='true' code='Label.selOrgType'/>  <span class='mandatory'>*</span>");
		
	 var templateDivC = $("<div class=col-sm-6/>");
	 
	// Added Html Select Elements
		var templateSelectOrgType = $("<select class=form-control/>");
		templateSelectOrgType.attr("id", "orgType" );
		templateSelectOrgType.attr("name", "orgLevelCodes" );
		$(templateSelectOrgType).change(function() {
			$( this ).removeClass("error");
			$( '#error' + $( this ).attr('id') ).text("");
			emptyOrgList('org')
			if(!$_checkEmptyObject($( this ).val())){
				fillOrganisationList($( this ).val());	
			}
		});
	
		// Added HTML SPAN Element
		var templateErrorOrgType = $("<span/>");
		templateErrorOrgType.attr("id", "error" + $(templateSelectOrgType).attr('id'));
		templateErrorOrgType.attr("class", "errormsg");
		
		
		 var templateLIOrg = $("<div class=form-group/>");
		    
		 // Added Label Elements
		 var templateLabelOrg = $("<label class=col-sm-3 control-label/>");
			 templateLabelOrg.html("<spring:message htmlEscape='true' code='Label.selDeptorORg'/>  <span class='mandatory'>*</span>");
			 
		 var templateDivCO = $("<div class=col-sm-6/>");
				
		// Added Html Select Elements
			var templateSelectOrg = $("<select class=form-control/>");
			templateSelectOrg.attr("id", "org" );
			templateSelectOrg.attr("name", "orgLevelCodes" );
			$(templateSelectOrg).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				emptyOrgList('orgLevel');
				if(!$_checkEmptyObject($( this ).val())){
					fillLevelofOrganisation($( this ).val());	
				}
			});
		
			// Added HTML SPAN Element
			var templateErrorOrg = $("<span/>");
			templateErrorOrg.attr("id", "error" + $(templateSelectOrg).attr('id'));
			templateErrorOrg.addClass('errormsg');
		
			
			
			 var templateLIOrgLevel = $("<div class=form-group/>");
			    
			 // Added Label Elements
			 var templateLabelOrgLevel = $("<label class=col-sm-3 control-label/>");
				 templateLabelOrgLevel.html("<spring:message htmlEscape='true' code='Label.selUnitLevels'/>  <span class='mandatory'>*</span>");
				 
			 var templateDivCOL = $("<div class=col-sm-6/>");
					
			// Added Html Select Elements
				var templateSelectOrgLevel = $("<select class=form-control/>");
				templateSelectOrgLevel.attr("id", "orgLevel" );
				templateSelectOrgLevel.attr("name", "orgLevelCodes" );
				$(templateSelectOrgLevel).change(function() {
					$( this ).removeClass("error");
					$( '#error' + $( this ).attr('id') ).text("");
					emptyOrgList('orgOffice');
					if(!$_checkEmptyObject($( this ).val())){
						fillOfficeofOrganisationLevel($( this ).val());	
					}
				});
				// Added HTML SPAN Element
				var templateErrorOrgLevel = $("<span/>");
				templateErrorOrgLevel.attr("id", "error" + $(templateSelectOrgLevel).attr('id'));
				templateErrorOrgLevel.addClass('errormsg');
			
			
				 var templateLIOrgOffice = $("<div class=form-group/>");
				    
				 // Added Label Elements
				 var templateLabelOrgOffice = $("<label class=col-sm-3 control-label/>");
					 templateLabelOrgOffice.html("<spring:message htmlEscape='true' code='Label.selOfficeName'/>  <span class='mandatory'>*</span>");
					 
				 var templateDivCOLO = $("<div class=col-sm-6/>");
						
				// Added Html Select Elements
					var templateSelectOrgOffice = $("<select class=form-control/>");
					templateSelectOrgOffice.attr("id", "orgOffice" );
					templateSelectOrgOffice.attr("name", "orgLevelCodes" );
					$(templateSelectOrgOffice).change(function() {
						$( this ).removeClass("error");
						$( '#error' + $( this ).attr('id') ).text("");
						if(!$_checkEmptyObject($( this ).val())){
							fillOrgSpecificList($( this ).val());	
						}
					});
					// Added HTML SPAN Element
					var templateErrorOrgOffice = $("<span/>");
					templateErrorOrgOffice.attr("id", "error" + $(templateSelectOrgOffice).attr('id'));
					templateErrorOrgOffice.addClass('errormsg');
				
			 
					
					var templateLIOrgUnits = $("<div class=form-group/>");
				    
					 // Added Label Elements
					 var templateLabelOrgUnits = $("<label class=col-sm-3 control-label/>");
						 templateLabelOrgUnits.html("<spring:message htmlEscape='true' code='Label.select.org.unit'/>  <span class='mandatory'>*</span>");
						 
					 var templateDivCOLOG = $("<div class=col-sm-6/>");
							
					// Added Html Select Elements
						var templateSelectOrgUnits = $("<select class=form-control/>");
						templateSelectOrgUnits.attr("id", "specificOrgList" );
						templateSelectOrgUnits.attr("name", "orgLevelCodes" );
						$(templateSelectOrgUnits).change(function() {
							$( this ).removeClass("error");
							$( '#error' + $( this ).attr('id') ).text("");
							
						});
						// Added HTML SPAN Element
						var templateErrorOrgUnits = $("<span/>");
						templateErrorOrgUnits.attr("id", "error" + $(templateSelectOrgUnits).attr('id'));
						templateErrorOrgUnits.addClass('errormsg');		
					
		
			templateLIOrgType.append(templateLabelOrgType);
			templateLIOrgType.append(templateDivC);
			templateDivC.append(templateSelectOrgType);
			templateDivC.append(templateErrorOrgType);
			divTemplate.append(templateLIOrgType);
		
		    templateLIOrg.append(templateLabelOrg);
		    templateLIOrg.append(templateDivCO);
		    templateDivCO.append(templateSelectOrg);
		    templateDivCO.append(templateErrorOrg);
		    divTemplate.append(templateLIOrg);
		
		    templateLIOrgLevel.append(templateLabelOrgLevel);
		    templateLIOrgLevel.append(templateDivCOL);
		    templateDivCOL.append(templateSelectOrgLevel);
		    templateDivCOL.append(templateErrorOrgLevel);
		    divTemplate.append(templateLIOrgLevel);
		 
		    templateLIOrgOffice.append(templateLabelOrgOffice);
		    templateLIOrgOffice.append(templateDivCOLO);
		    templateDivCOLO.append(templateSelectOrgOffice);
		    templateDivCOLO.append(templateErrorOrgOffice);
		    divTemplate.append(templateLIOrgOffice);
		
		    
		    templateLIOrgUnits.append(templateLabelOrgUnits);
		    templateLIOrgUnits.append(templateDivCOLOG);
		    templateDivCOLOG.append(templateSelectOrgUnits);
		    templateDivCOLOG.append(templateErrorOrgUnits);
		    divTemplate.append(templateLIOrgUnits);
		    
		    //divTemplate.append(templateUL);
		
           
		    
		    
		
		    fillOrganisationType();
			
};



/**
 * The {@code fillOrganisationType} used to fill Organisation type List
 */
fillOrganisationType=function(){
	var options = $("#orgType"); 
	var option = $("<option/>");
	$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
	options.append(option);
	option = $("<option/>");
	$(option).val("${orgTypeCodeDept}").text("<spring:message code='Label.DEPARTMENT' htmlEscape='true'/>");
	options.append(option);
	option = $("<option/>");
	$(option).val("${orgTypeCodeOrg}").text("<spring:message code='Label.Organization' htmlEscape='true'/>");
	options.append(option);
};


/**
 * The {@code fillOrganisationList} used to fill Organisation List
 */
fillOrganisationList=function(orgType){
	
	 emptyOrgList('org')
	
		lgdAdminDepatmentDwr.getOrganizationbyCriteria(parseInt(_JS_STATE_CODE), parseInt(orgType),{
			callback : function( result ) {
				var options = $("#org"); 
				var option = $("<option/>");
				$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				options.append(option);
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					
					option.val(obj.olc).text(obj.orgName);
					options.append(option);
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	
	
	
};

/**
 * The {@code fillLevelofOrganisation} used to fill Level of Organization(District,Subdistrict,Village,Block and administrative unit)
 */
fillLevelofOrganisation=function(org){
	emptyOrgList('orgLevel');
	
		_JS_SELECT_OLC=org;
		lgdAdminDepatmentDwr.getUnitLevelbyOrganization( parseInt(org),  {
		callback : function( result ) {
				var options = $("#orgLevel"); 
				var option = $("<option/>");
				$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				options.append(option);
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					
					option.val(obj.adminUnitCode).text(obj.adminLevelNameEng);
					options.append(option);
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	
	
	
};

/**
 * The {@code fillOfficeofOrganisationLevel} used to fill office in per level of organisation
 */
fillOfficeofOrganisationLevel=function(orgLevel){
	emptyOrgList('orgOffice');
	
		lgdAdminDepatmentDwr.getOfficeNamebyLevel(parseInt(_JS_SELECT_OLC), parseInt(orgLevel), {
			callback : function( result ) {
				var options = $("#orgOffice"); 
				var option = $("<option/>");
				$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				options.append(option);
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					
					option.val(obj.orgLocatedLevelCode).text(obj.adminLevelNameEng);
					options.append(option);
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	
	
	
};

/**
 * The {@code fillOrgSpecificList} used fill organisation specific List
 */
fillOrgSpecificList=function(orgLocatedLevelCode){
	
	removeAllOptions('specificOrgList')
	lgdDwrOrganizationService.getOrganizationeUnitsByorglocatedlevelcode(parseInt(orgLocatedLevelCode), {
			callback : function(result) {
				var options = $("#specificOrgList");
				var option = $("<option/>");
				$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				options.append(option);
				
				
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					(option).val(obj.orgUnitCode).text(obj.orgUnitName);
					options.append(option);
					
				});
				
				
			},
			async : true
		});	
	
	
};

/**
 * The {@code emptyOrgList} used clear all child combo box when select parent combo box and remove organzation specific block and unselect organisation specific/full radio button
 */
emptyOrgList=function(parentId){
	 var emptyFlag=false;
		if(isCenter){
			 var orgType= $("#orgType").val();
			 if(orgType==parseInt('${orgTypeCodeDept}')){
				 for(i=(deptCenterListIdArray.length-1);i>=0;i--){
						
						if(parentId==deptCenterListIdArray[i] && !(emptyFlag)){
							emptyFlag=true;
						}
						
						if(!emptyFlag){
							removeAllOptions(deptCenterListIdArray[i]);
						}
					}
			 }else{
				 for(i=(orgCenterListIdArray.length-1);i>=0;i--){
						
						if(parentId==orgCenterListIdArray[i] && !(emptyFlag)){
							emptyFlag=true;
						}
						
						if(!emptyFlag){
							removeAllOptions(orgCenterListIdArray[i]);
						}
					}
			 }
			
			$("#divOrgSpecificBlockCenter").empty();
		}else{
			for(i=0;i<orgListIdArray.length;i++){
				
				if(parentId==orgListIdArray[i] && !(emptyFlag)){
					emptyFlag=true;
				}
				
				if(emptyFlag){
					removeAllOptions(orgListIdArray[i]);
				}
			}
			$("#divOrgSpecificBlock").empty();
		}
		
		$("#orgSpcific").prop('checked', false);
		$("#orgFull").prop('checked', false);
		$("#unselect").prop('checked', true);
};











/**
 * validate Client rule all method define here  #started
 */

 validateGeneralDetails=function(){
	
	 $( "span[id^=error]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	
	errors[0]=validateParentType();
	//errors[2]=validatePinCode();
	if ($("#lbEntityType").is(':checked')) {
		errors[1]=!validatLbHierarchy();
		
		
		
	}else{
		errors[1]=!validatOrgHierarchy();
		
	}
	
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	if(error){
		//alert("get value");
		callActionUrl('manageSection.htm');
	} 
	
	
	
};

validateParentType=function(){
	if(isCenter){
		return true;
	}else{
		var parentType=$("#parentType").val();
		if($_checkEmptyObject(parentType)){
			$( '#errrparentType').text("<spring:message code='label.choose.parent.type' htmlEscape='true'/>");
			return false;
		}
		return true;
	}
};

/* 
* The {@code validatLbHierarchy} used validate localbody element before childparentflag any option select.
*/
var validatLbHierarchy=function(){
	var _error_flag=false;
	
	var llbTypeHierarchyElement = $( '#lbTypeHierarchy' );
	if($_checkEmptyObject($( llbTypeHierarchyElement ).val())){
		_error_flag=true;
		$( llbTypeHierarchyElement ).addClass("error");
		$( '#error' + $( llbTypeHierarchyElement ).attr('id') ).text("<spring:message code='Label.Selecthierarchylevel' htmlEscape='true'/>");	 
	}else{
		var localBodyTypeElement = $( '#localBodyType' );
		if($_checkEmptyObject($( localBodyTypeElement ).val())){
			_error_flag=true;
			$( localBodyTypeElement ).addClass("error");
			$( '#error' + $( localBodyTypeElement ).attr('id') ).text("<spring:message code='Label.SELLOCALBODYTYPE' htmlEscape='true'/>");	 
		}else{
			
			var selectedlocalBodyType = $( localBodyTypeElement ).val();
			var lbTypeArray = selectedlocalBodyType.split("_");
			var parent  = lbTypeArray[2];
			var lbTypeCode  = lbTypeArray[0]; 
			var element = $( '[name = localBodyLevelCodes]' );
			var localBodyElement = $( element )[$( element ).length - 1];
			if(!validateLBCode(localBodyElement)){
				_error_flag=true;
			}
			//alert((isParseJson(parent)==null)+":"+isParseJson(parent));
			/* if(!(isParseJson(parent)==null)){
				var element = $( '[name = localBodyLevelCodes]' );
				var localBodyElement = $( element )[$( element ).length - 1];
				if(!validateLBCode(localBodyElement)){
					_error_flag=true;
				}
			}else{
				_TOP_LOCALBODY_FLAG=true;
				$("#parentCode").val(lbTypeCode);
				//$("#lbFull").prop('checked', false);
			} */
			
		}
	}
	
	
	return _error_flag;
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
				$( '#error' + $( this ).attr('id') ).text("<spring:message code='Label.SELECT' htmlEscape='true'/>"+" "+"<spring:message code='common.localBody' htmlEscape='true'/>");	 
			 }
		 });
		 return false;
	}
	return true; 
};



validatOrgHierarchy=function(){
	 var _error_flag=false;
	 if(isCenter){
		 var orgType= $("#orgType");
			if($_checkEmptyObject($(orgType).val())){
				_error_flag=true;
				$( orgType ).addClass("error");
				$( '#error' + $( orgType ).attr('id') ).text("<spring:message htmlEscape='true' code='Label.selOrgType'/>");
			}else{
				if($(orgType).val()==parseInt('${orgTypeCodeDept}')){
					 var errorMsgArray=["<spring:message htmlEscape='true' code='Label.SELMIN'/>","<spring:message htmlEscape='true' code='Label.SELDEPT'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"];
					 for(var i=0;i<deptCenterListIdArray.length;i++){
							var orgElement=$( '#'+deptCenterListIdArray[i] );
							if($_checkEmptyObject($( orgElement ).val())){
								_error_flag=true;
								$( orgElement ).addClass("error");
								$( '#error' + $( orgElement ).attr('id') ).text(errorMsgArray[i]);
							}
						}

				}else{
					 var errorMsgArray=["<spring:message htmlEscape='true' code='Label.SELMIN'/>","<spring:message htmlEscape='true' code='Label.SELDEPT'/>","<spring:message htmlEscape='true' code='Label.SELORG'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"];
					 for(var i=0;i<orgCenterListIdArray.length;i++){
							var orgElement=$( '#'+orgCenterListIdArray[i] );
							if($_checkEmptyObject($( orgElement ).val())){
								_error_flag=true;
								$( orgElement ).addClass("error");
								$( '#error' + $( orgElement ).attr('id') ).text(errorMsgArray[i]);
							}
						}

				}
					
			}
	 }else{
		 var errorMsgArray=["<spring:message code='Label.selOrgType' htmlEscape='true'/>","<spring:message code='Label.selDeptorORg' htmlEscape='true'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"];
			for(var i=0;i<orgListIdArray.length;i++){
				var orgElement=$( '#'+orgListIdArray[i] );
				if($_checkEmptyObject($( orgElement ).val())){
					_error_flag=true;
					$( orgElement ).addClass("error");
					$( '#error' + $( orgElement ).attr('id') ).text(errorMsgArray[i]);
				}
			}
	
	 }
	 return _error_flag;
	
		
};

validateOrgSelectedList=function(){
	//alert($('#selectedLbList').children().length);
	if (($("#orgSpcific").is(':checked')) && ($('#selectedOrgList').children().length == 0)) {
		$( '#errorselectedOrgList').text("<spring:message code='label.select.orgspecific.list' htmlEscape='true'/>");
		return false;
	}
	return true;
};


/* The {@code removeAllOptions} used to clear drop down box based on their id. 
*/
removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};

/**
 * The {@code emptyDetails} used clear dynamic div and unselect childparentflag options
 */
emptyDetails=function(type,partFlag){
	_TOP_LOCALBODY_FLAG=false;
	if(type=="G"){
		if(!partFlag){
			$("#dynamicLbStructure").empty();
			$("#divSpecificFull").empty();
		}else{
			$("#lbSpcific").prop('checked', false);
			$("#lbFull").prop('checked', false);
			$("#unselect").prop('checked', true);
		
			
		}
		
		$("#divLBSpecificBlock").empty();
	}
};
/* The {@code removeAllOptions} used to clear drop down box based on their id. 
*/
removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};

/* The {@code $_checkEmptyObject} used to check object / element  
* is empty or undefined.
*/
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=sectionForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=sectionForm]' ).attr('method','post');
	$( 'form[id=sectionForm]' ).submit();
};

var processLinkActions = function (sectionCode,sectionVersion,sectionName, url){
	
	//deltabPos=$("#reactiveTable").position();
	//alert(deltabPos.left())
	$('#paramSectionCode').val(sectionCode);
	$('#paramSctionName').val(sectionName);
	
	if(url=="reActiveSection.htm"){
		
		 BootstrapDialog.show({
	         title: 'Section restore confirm',
	         message: "Are you sure to restore "+sectionName+" Section",
	         buttons: [{
	             label: 'Yes',
	             action: function(dialog) {
	            	 $('#paramSectionVersion').val(sectionVersion);
		                callActionUrl(url);
	    				 dialog.close();
	             }
	         }, {
	             label: 'NO',
	             action: function(dialog) {
	            	 dialog.close();
	             }
	         }]
	     });
		
		/* 
		 $("#dialog-confirm").html("Are you sure to restore "+sectionName+" Section");
			$("#dialog-confirm").dialog({
		        resizable: false,
		        modal: true,
		        title: "Section restore confirm",
		        height: 150,
		        width: 300,
		       // position: [deltabPos.left,deltabPos.top-100],
		        buttons: {
		            "Yes": function () {
		                $(this).dialog('close');
		                $('#paramSectionVersion').val(sectionVersion);
		                callActionUrl(url);
		                
		            },
		                "No": function () {
		                $(this).dialog('close');
		                
		                
		            }
		        },
		        
			 position: "center"
		    });	
		 */
		
	
	}else if(url=="deleteSection.htm"){
		
		 BootstrapDialog.show({
	         title: 'Section Delete confirm',
	         message: "Are you sure to delete "+sectionName+" Section",
	         buttons: [{
	             label: 'Yes',
	             action: function(dialog) {
	            	 $('#paramSectionVersion').val(sectionVersion);
		                callActionUrl(url);
	    				 dialog.close();
	             }
	         }, {
	             label: 'NO',
	             action: function(dialog) {
	            	 dialog.close();
	             }
	         }]
	     });
		
		
		
	}else{
		callActionUrl(url);
	}
	
	
};

 


/*
 *  # stared
 * all method are below used to build ,fill elements and show error message
 *  after return from server error
 */
loadElementandShowError=function(){
	if(isCenter){
		buildEntityTypeOrganisationCenterDiv();
		
		if(!loadCenterOrganisationHierarchy()){
			loadOrgspecific();
		}
	}else{
		var load_parent_type='${sectionForm.parentType}';
		if($_checkEmptyObject(load_parent_type)){
			$( '#errrparentType').text("<spring:message code='Label.section.choose.parent.type' htmlEscape='true'/>");	 
		}else if(load_parent_type=="${entityTypeLB}"){
			$("#divEntityTypeLB").show(); 
			$("#lbEntityType").prop('checked', true);
			buildEntityTypeLocalbodyDiv();
			setTimeout(function(){
				
			var load_lbTypeHierarchy='${sectionForm.lbTypeHierarchy}';
			if($_checkEmptyObject(load_lbTypeHierarchy)){
				$( '#lbTypeHierarchy' ).addClass("error");
				$( '#errorlbTypeHierarchy').text("<spring:message code='Label.SELlBTYPEHOERARCHY' htmlEscape='true'/>");	 
			}else{
				setTimeout(function(){
					$("#lbTypeHierarchy option[value='" +load_lbTypeHierarchy + "']").attr("selected", "selected");
				},_pause_time);
				
				var optVaues = load_lbTypeHierarchy.split(',');
				var setupCode = optVaues[0]; // local body setup code
				var setupVersion = optVaues[1]; // local body setup version
			
				dwrRestructuredLocalBodyService.buildLocalBodyHierarchyWithCategory(parseInt(setupCode), parseInt(setupVersion), {
					callback : function(result) {
						populateLBType(result);
						var _load_localBodyType='${sectionForm.localBodyType}';
						//alert("_load_localBodyType:"+_load_localBodyType);
						if($_checkEmptyObject(_load_localBodyType)){
							$( '#localBodyType' ).addClass("error");
							$( '#errorlocalBodyType').text("<spring:message code='SELLOCALBODYTYPE' htmlEscape='true'/>");	
						}else{
							$("#localBodyType option[value='${sectionForm.localBodyType}']").attr("selected", "selected");
							loadDynamicHierarchy($('#localBodyType').get(0));
							setTimeout(function(){
				            loadlbspecific();
			                 },_pause_time);
							
						}
						
					},
					errorHandler : errorLbTypeProcess,
					async : true
				});
				
			}
			
			},_pause_time);
		}else if(load_parent_type=="${entityTypeOrg}"){
			buildEntityTypeOrganisationDiv();
			$("#divEntityTypeOrg").show(); 
			$("#orgEntityType").prop('checked', true);
			if(!loadOrganisationHierarchy()){
				loadOrgspecific();
			}
			 
			
		}
	}
	
};


var loadDynamicHierarchy = function(obj) {
	emptyDetails("G",true);
	 $("#divCreateDynamicHierarchy").empty();
	 var divTemplate = $("#divCreateDynamicHierarchy");
	 var isDWRCallRequired = true;
	 var localBodyTypeElement = $('#localBodyType option[value != ""]');
	 
	 var localbodyLevelCodes =  '${sectionForm.localBodyLevelCodes}';
	// alert(localbodyLevelCodes);
	 var lbcodeArray=localbodyLevelCodes.split(",");
	 var selIndex=0;
	 $(localBodyTypeElement).each(function(index){
		 var localbodyTypeArray = $(this).val().split("_");
		 var localbodyTypeCode = localbodyTypeArray[0];
		 var tiersetupCode = localbodyTypeArray[1];
		 var parentTiersetupCode = localbodyTypeArray[2];
		 var category= localbodyTypeArray[4];
		 //alert(category);
		// alert("lb"+index+":"+lbcodeArray[index]);
		 var selecedLbTypeCode=obj.value.split("_")[0];
		if( ( category!="U" && (parseInt(selecedLbTypeCode) >= parseInt(localbodyTypeCode ))) || (category=="U" && (parseInt(selecedLbTypeCode) == parseInt(localbodyTypeCode) ) ) ){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<div/>");
		    templateUL.addClass('box-body');
		    
		    // Added Li Elements for UL
			var templateLI = $("<div class=form-group/>");
			
		    // Added Label Elements
			var templateLabel = $("<label class=col-sm-3 control-label/>");
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandatory'>*</span>");
			
			var templateDivCOLL = $("<div class=col-sm-6/>");
			
			
			// Added Html Select Elements
			var templateSelect = $("<select class=form-control/>");
			templateSelect.attr("id", "localBodyLevelCodes_" + tiersetupCode + "_" + parentTiersetupCode);
			templateSelect.attr("name", "localBodyLevelCodes");
			$(templateSelect).change(function() {
				 emptyDetails("G",true);
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
			templateLI.append(templateDivCOLL);
			templateDivCOLL.append(templateSelect);
			templateDivCOLL.append(templateError);
			templateUL.append(templateLI);
			divTemplate.append(templateUL);
			
			if(!isDWRCallRequired && !($_checkEmptyObject(lbcodeArray[index-1]))){
				 //alert(lbcodeArray[index-1]);
				 buildParentwisePanchayatOptions($( templateSelect ).attr('id'),lbcodeArray[index-1]);
				}
			 if($_checkEmptyObject(lbcodeArray[selIndex])){
					$( templateSelect ).addClass("error");
					$( '#error' + $( templateSelect ).attr('id') ).text("<spring:message code='Label.select' htmlEscape='true'/> "+localbodyTypeName);	 
				 }else{
					 var _value=lbcodeArray[selIndex];
					 setTimeout(function(){
						 $("#" + $( templateSelect ).attr('id') +" option[value='" + _value + "']").attr("selected", "selected");
						 
						},200);
					
			
				 }
			 selIndex++;
			
		 }
	});
};


loadOrganisationHierarchy=function(){
	
	
	var orgLevelCodes='${sectionForm.orgLevelCodes}';
	var _error_flag=false;
	//alert(orgLevelCodes);
	if($_checkEmptyObject(orgLevelCodes)){
		_error_flag=true;
		$( '#orgType' ).addClass("error");
		$( '#errororgType').text("<spring:message code='Label.Please' htmlEscape='true'/>"+" "+"<spring:message code='Label.selOrgType' htmlEscape='true'/>");	
	}else{
		 
		 var parent=null;
		var errorMsgArray=["<spring:message code='Label.selOrgType' htmlEscape='true'/>","<spring:message code='Label.selDeptorORg' htmlEscape='true'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"];
		var orgLevelCodeArray=orgLevelCodes.split(",");
		for(var i=0;i<orgListIdArray.length;i++){
			var orgElement=$( '#'+orgListIdArray[i] );
			//alert("orgLevelCodeArray[i]:"+orgLevelCodeArray[i]);
			if(orgListIdArray[i]=="org"){
				fillOrganisationList(parent);
				
			}else if(orgListIdArray[i]=="orgLevel"){
				fillLevelofOrganisation(parent);
			}else if(orgListIdArray[i]=="orgOffice"){
				fillOfficeofOrganisationLevel(parent);
			} else if(orgListIdArray[i]=="specificOrgList"){
				fillOrgSpecificList(parent);
			} 
			
			parent=orgLevelCodeArray[i];
			
			
			
		} 
		
		for(var i=0;i<orgListIdArray.length;i++){
			var orgElement=$( '#'+orgListIdArray[i] );
			
			if($_checkEmptyObject(orgLevelCodeArray[i])){
				_error_flag=true;
				$( orgElement ).addClass("error");
				$( '#error' + $( orgElement ).attr('id') ).text("<spring:message code='Label.Please' htmlEscape='true'/>"
						+" "+errorMsgArray[i]);
			}
			else{
				var _value=orgLevelCodeArray[i];
				if(i==(orgListIdArray.length-1)){
					_time=1500;
				}
				fillAndSelectOrgElements(orgElement,_value,200);
			}
		}
		return _error_flag;
	}
	
	
};

 fillAndSelectOrgElements=function(orgElement,selvalue,_time){
	
		setTimeout(function(){
			 $("#" + $( orgElement ).attr('id') +" option[value='" +selvalue + "']").attr("selected", "selected");
			 
			 },_time);
	
}; 


showHiheStateorCenterDiv=function(){
	
	if(isCenter){
		$("#stateBody").hide(); 
		$("#centerBody").show(); 
		$("#divEntityTypeOrgCenter").show(); 
		
		buildEntityTypeOrganisationCenterDiv();
	}else{
		$("#stateBody").show(); 
		$("#centerBody").hide(); 
		
	}
		
	
};

buildEntityTypeOrganisationCenterDiv=function(){
	 $("#dynamicOrgStructureCenter").empty();
	 var divTemplate = $("#dynamicOrgStructureCenter");
	 var isDWRCallRequired = true;
	 
	 var templateUL = $("<div/>");
	     templateUL.addClass('box-body');
	     
	 // Added Li Elements for UL
	 var templateLIOrgType = $("<div class=form-group/>");
		    
	 // Added Label Elements
	 var templateLabelOrgType = $("<label class=col-sm-3 control-label/>");
		 templateLabelOrgType.html("<spring:message htmlEscape='true' code='Label.selOrgType'/>  <span class='mandatory'>*</span>");
		 
	 var templateDivO = $("<div class=col-sm-6/>");
		 
		 var templateSelectOrgType = $("<select class=form-control/>");
			templateSelectOrgType.attr("id", "orgType" );
			templateSelectOrgType.attr("name", "orgTypeCode" );
			$(templateSelectOrgType).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(!$_checkEmptyObject($( this ).val())){
					buildDeptorOrgCenterDiv($( this ).val());
				}
			});
		
			// Added HTML SPAN Element
			var templateErrorOrgType = $("<span/>");
			templateErrorOrgType.attr("id", "error" + $(templateSelectOrgType).attr('id'));
			templateErrorOrgType.attr("class", "errormsg");
			
			templateDivO.append(templateSelectOrgType);
			templateDivO.append(templateErrorOrgType);
			templateLIOrgType.append(templateLabelOrgType);
			templateLIOrgType.append(templateDivO);
			
		    templateUL.append(templateLIOrgType);
		    
		    divTemplate.append(templateUL);
		    fillOrganisationType();
};

buildDeptorOrgCenterDiv=function(orgType){
	 $("#deptorOrg").empty();
	 var divTemplate = $("#deptorOrg");
	 var isDWRCallRequired = true;
	 
	 var templateUL = $("<div/>");
	     templateUL.addClass('box-body');
	     
	 // Added Li Elements for UL
	 var templateLIDeptorOrg = $("<div class=form-group/>");
		    
	 // Added Label Elements
		 var templateLabelDeptorOrg = $("<label class=col-sm-3 control-label/>");
		 templateLabelDeptorOrg.html("<spring:message htmlEscape='true' code='Label.SELMIN'/>  <span class='mandatory'>*</span>");
		 
		 var templateDivOR = $("<div class=col-sm-6/>")
		 
		 var templateSelectDeptorOrg = $("<select class=form-control/>");
			templateSelectDeptorOrg.attr("id", "ministryId" );
			templateSelectDeptorOrg.attr("name", "orgLevelCodes" );
			$(templateSelectDeptorOrg).change(function() {
				emptyOrgList($( this ).attr('id'));
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(!$_checkEmptyObject($( this ).val())){
					fillCenterOrganisationList(parseInt('${orgTypeCodeDept}'),$( this ).val(),$(templateSelectDept));	
				}
			});
		
			// Added HTML SPAN Element
			var templateErrorDeptorOrg = $("<span/>");
			templateErrorDeptorOrg.attr("id", "error" + $(templateSelectDeptorOrg).attr('id'));
			templateErrorDeptorOrg.attr("class", "errormsg");
			
			templateLIDeptorOrg.append(templateLabelDeptorOrg);
			templateLIDeptorOrg.append(templateDivOR);
			templateDivOR.append(templateSelectDeptorOrg);
			templateDivOR.append(templateErrorDeptorOrg);
		    templateUL.append(templateLIDeptorOrg);
		    
		    
			 // Added Li Elements for UL
			 var templateLIDept = $("<div class=form-group/>");
				    
			 // Added Label Elements
				 var templateLabelDept = $("<label class=col-sm-3 control-label/>");
				 templateLabelDept.html("<spring:message htmlEscape='true' code='Label.SELDEPT'/>  <span class='mandatory'>*</span>");
				 
				 var templateDivORG = $("<div class=col-sm-6/>");
				 
				 var templateSelectDept = $("<select class=form-control/>");
					templateSelectDept.attr("id", "centerDeptId" );
					templateSelectDept.attr("name", "orgLevelCodes" );
					$(templateSelectDept).change(function() {
						emptyOrgList($( this ).attr('id'));
						$( this ).removeClass("error");
						$( '#error' + $( this ).attr('id') ).text("");
						if(!$_checkEmptyObject($( this ).val())){
							 if(orgType!=2){
								 fillCenterOrganisationList(parseInt('${orgTypeCodeOrg}'),$( this ).val(),$(templateSelectOrg));	 
							 }else{
								 fillLevelofOrganisation($( this ).val()); 
							 }
							
							
						}
					});
				
					// Added HTML SPAN Element
					var templateErrorDept = $("<span/>");
					templateErrorDept.attr("id", "error" + $(templateSelectDept).attr('id'));
					templateErrorDept.attr("class", "errormsg");
					
					templateLIDept.append(templateLabelDept);
					templateLIDept.append(templateDivORG);
					templateDivORG.append(templateSelectDept);
					templateDivORG.append(templateErrorDept);
				    templateUL.append(templateLIDept);
				    
				    if(orgType!=2){
				    	var templateLIOrg = $("<div class=form-group/>");
					    
						 // Added Label Elements
							 var templateLabelOrg = $("<label class=col-sm-3 control-label/>");
							 templateLabelOrg.html("<spring:message htmlEscape='true' code='Label.SELORG'/>  <span class='mandatory'>*</span>");
							 
							 var templateDivORGA = $("<div class=col-sm-6/>");
							 
							 var templateSelectOrg = $("<select class=form-control/>");
								templateSelectOrg.attr("id", "centerOrgId" );
								templateSelectOrg.attr("name", "orgLevelCodes" );
								$(templateSelectOrg).change(function() {
									emptyOrgList($( this ).attr('id'));
									$( this ).removeClass("error");
									$( '#error' + $( this ).attr('id') ).text("");
								if(!$_checkEmptyObject($( this ).val())){
									 fillLevelofOrganisation($( this ).val()); 	
									}
								});
							
								// Added HTML SPAN Element
								var templateErrorOrg = $("<span/>");
								templateErrorOrg.attr("id", "error" + $(templateSelectOrg).attr('id'));
								templateErrorOrg.attr("class", "errormsg");
								
								templateLIOrg.append(templateLabelOrg);
								templateLIOrg.append(templateDivORGA);
								templateDivORGA.append(templateSelectOrg);
								templateDivORGA.append(templateErrorOrg);
							    templateUL.append(templateLIOrg);  
				    }
				    
				    var templateLIOrgLevel = $("<div class=form-group/>");
				    
					 // Added Label Elements
					 var templateLabelOrgLevel = $("<label class=col-sm-3 control-label/>");
						 templateLabelOrgLevel.html("<spring:message htmlEscape='true' code='Label.selUnitLevels'/>  <span class='mandatory'>*</span>");
						 
					 var templateDivORGAN = $("<div class=col-sm-6/>")
							
					// Added Html Select Elements
						var templateSelectOrgLevel = $("<select class=form-control/>");
						templateSelectOrgLevel.attr("id", "orgLevel" );
						templateSelectOrgLevel.attr("name", "orgLevelCodes" );
						$(templateSelectOrgLevel).change(function() {
							emptyOrgList($( this ).attr('id'));
							$( this ).removeClass("error");
							$( '#error' + $( this ).attr('id') ).text("");
							emptyOrgList('orgOffice');
							if(!$_checkEmptyObject($( this ).val())){
								fillOfficeofOrganisationLevel($( this ).val());	
							}
						});
						// Added HTML SPAN Element
						var templateErrorOrgLevel = $("<span/>");
						templateErrorOrgLevel.attr("id", "error" + $(templateSelectOrgLevel).attr('id'));
						templateErrorOrgLevel.addClass('errormsg');
					
						 templateLIOrgLevel.append(templateLabelOrgLevel);
						 templateLIOrgLevel.append(templateDivORGAN);
						 templateDivORGAN.append(templateSelectOrgLevel);
						 templateDivORGAN.append(templateErrorOrgLevel);
						    templateUL.append(templateLIOrgLevel);
					
						 var templateLIOrgOffice = $("<div class=form-group/>");
						    
						 // Added Label Elements
						 var templateLabelOrgOffice = $("<label class=col-sm-3 control-label/>");
							 templateLabelOrgOffice.html("<spring:message htmlEscape='true' code='Label.selOfficeName'/>  <span class='mandatory'>*</span>");
								
						 var templateDivORGANA = $("<div class=col-sm-6/>");

						 // Added Html Select Elements
							var templateSelectOrgOffice = $("<select class=form-control/>");
							templateSelectOrgOffice.attr("id", "orgOffice" );
							templateSelectOrgOffice.attr("name", "orgLevelCodes" );
							$(templateSelectOrgOffice).change(function() {
								$( this ).removeClass("error");
								$( '#error' + $( this ).attr('id') ).text("");
								fillOrgSpecificList($( this ).val());	
							});
							// Added HTML SPAN Element
							var templateErrorOrgOffice = $("<span/>");
							templateErrorOrgOffice.attr("id", "error" + $(templateSelectOrgOffice).attr('id'));
							templateErrorOrgOffice.addClass('errormsg');
							
							 var templateDivorgOffice=$("<div/>"); 
							 templateDivorgOffice.addClass("col-sm-6");
						
							
							 templateDivorgOffice.append(templateSelectOrgOffice);
							 templateDivorgOffice.append(templateErrorOrgOffice);
							templateLIOrgOffice.append(templateLabelOrgOffice);
							templateLIOrgOffice.append(templateDivorgOffice);
						    templateUL.append(templateLIOrgOffice);
						    
						    var templateLIOrgUnits = $("<div class=form-group/>");
						    
							 // Added Label Elements
							 var templateLabelOrgUnits = $("<label class=col-sm-3 control-label/>");
								 templateLabelOrgUnits.html("<spring:message htmlEscape='true' code='Label.select.org.unit'/>  <span class='mandatory'>*</span>");
								 
							 var templateDivORGANAI = $("<div class=col-sm-6/>");
									
							// Added Html Select Elements
								var templateSelectOrgUnits = $("<select class=form-control/>");
								templateSelectOrgUnits.attr("id", "specificOrgList" );
								templateSelectOrgUnits.attr("name", "orgLevelCodes" );
								$(templateSelectOrgUnits).change(function() {
									$( this ).removeClass("error");
									$( '#error' + $( this ).attr('id') ).text("");
									
								});
								// Added HTML SPAN Element
								var templateErrorOrgUnits = $("<span/>");
								templateErrorOrgUnits.attr("id", "error" + $(templateSelectOrgUnits).attr('id'));
								templateErrorOrgUnits.addClass('errormsg'); 
				    
								  templateLIOrgUnits.append(templateLabelOrgUnits);
								  templateLIOrgUnits.append(templateDivORGANAI);
								  templateDivORGANAI.append(templateSelectOrgUnits);
								  templateDivORGANAI.append(templateErrorOrgUnits);
								    templateUL.append(templateLIOrgUnits);
						    
			divTemplate.append(templateUL);
		    
			
		
			
			fillCenterOrganisationList(parseInt('${orgTypeCodeMinist}'),null,$(templateSelectDeptorOrg));
		    
		    
		    
};


fillCenterOrganisationList=function(orgType,parentOrgCode,templateEntity){
	removeAllOptions($(templateEntity).attr('id'))
	lgdAdminDepatmentDwr.getCenterOrganizationbyCriteria(parseInt(orgType),parentOrgCode,{
			callback : function( result ) {
				
				var option = $("<option/>");
				$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				templateEntity.append(option);
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					
					option.val(obj.olc).text(obj.orgName);
					templateEntity.append(option);
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	
	
	
};


loadCenterOrganisationHierarchy=function(){
	
	var orgType='${sectionForm.orgTypeCode}';
	var orgLevelCodes='${sectionForm.orgLevelCodes}';
	var _error_flag=false;
	var _time=400;
	if($_checkEmptyObject(orgType)){
		_error_flag=true;
		$( '#orgType' ).addClass("error");
		$( '#errororgType').text("<spring:message code='Label.Please' htmlEscape='true'/>"+" "+"<spring:message code='Label.selOrgType' htmlEscape='true'/>");	
	}else{
		var orgLevelCodeArray=orgLevelCodes.split(","); 
		$("#orgType option[value='" +orgType + "']").attr("selected", "selected");
		buildDeptorOrgCenterDiv(orgType);
		if(orgType==2){
			var errorMsgArray=["<spring:message htmlEscape='true' code='Label.SELMIN'/>","<spring:message htmlEscape='true' code='Label.SELDEPT'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"];
			for(var i=0;i<deptCenterListIdArray.length;i++){
				var orgElement=$( '#'+deptCenterListIdArray[i] );
				if(deptCenterListIdArray[i]=="ministryId"){
					fillCenterOrganisationList(parseInt('${orgTypeCodeMinist}'),null,$('#'+deptCenterListIdArray[i]));
					
				}else if(deptCenterListIdArray[i]=="centerDeptId"){
					fillCenterOrganisationList(parseInt('${orgTypeCodeDept}'),parseInt(parent),$('#'+deptCenterListIdArray[i]));
					
				}else if(deptCenterListIdArray[i]=="orgLevel"){
					fillLevelofOrganisation(parent);
				}else if(deptCenterListIdArray[i]=="orgOffice"){
					fillOfficeofOrganisationLevel(parent);
				} else if(deptCenterListIdArray[i]=="specificOrgList"){
					fillOrgSpecificList(parent);
				}  
				
				if($_checkEmptyObject(orgLevelCodeArray[i])){
					_error_flag=true;
					$( orgElement ).addClass("error");
					$( '#error' + $( orgElement ).attr('id') ).text("<spring:message code='Label.Please' htmlEscape='true'/>"
							+" "+errorMsgArray[i]);
				}
				else{
					if(deptCenterListIdArray[i]=="specificOrgList"){
						_time=1500;
					}
					fillAndSelectOrgElements(orgElement,orgLevelCodeArray[i],_time);
					parent=orgLevelCodeArray[i];
					
				}
			} 
		} else{
			var errorMsgArray=["<spring:message htmlEscape='true' code='Label.SELMIN'/>","<spring:message htmlEscape='true' code='Label.SELDEPT'/>","<spring:message htmlEscape='true' code='Label.SELORG'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"];
			for(var i=0;i<orgCenterListIdArray.length;i++){
				var orgElement=$( '#'+orgCenterListIdArray[i] );
				if(orgCenterListIdArray[i]=="ministryId"){
					fillCenterOrganisationList(parseInt('${orgTypeCodeMinist}'),null,$('#'+orgCenterListIdArray[i]));
					
				}else if(orgCenterListIdArray[i]=="centerDeptId"){
					fillCenterOrganisationList(parseInt('${orgTypeCodeDept}'),parseInt(parent),$('#'+orgCenterListIdArray[i]));
					
				}else if(orgCenterListIdArray[i]=="centerOrgId"){
					fillCenterOrganisationList(parseInt('${orgTypeCodeOrg}'),parseInt(parent),$('#'+orgCenterListIdArray[i]));
					
				}else if(orgCenterListIdArray[i]=="orgLevel"){
					fillLevelofOrganisation(parent);
				}else if(orgCenterListIdArray[i]=="orgOffice"){
					fillOfficeofOrganisationLevel(parent);
				}else if(orgCenterListIdArray[i]=="specificOrgList"){
					fillOrgSpecificList(parent);
				}  
				
				
				
				if($_checkEmptyObject(orgLevelCodeArray[i])){
					_error_flag=true;
					$( orgElement ).addClass("error");
					$( '#error' + $( orgElement ).attr('id') ).text("<spring:message code='Label.Please' htmlEscape='true'/>"
							+" "+errorMsgArray[i]);
				}
				else{
					
					
					fillAndSelectOrgElements(orgElement,orgLevelCodeArray[i],_time);
					parent=orgLevelCodeArray[i];
					
				}
			} 
		}
	}
		return _error_flag;
};

</script>

<!--#stared page return from server with error then call loadElementandShowError  method -->
<c:if test="${serverError eq true or serverLoad eq true}">
			<script>
			$(window).load(function () {
				loadElementandShowError();
			}); 
			</script>
</c:if>
<!--#end page return from server with error then call loadElementandShowError  method -->