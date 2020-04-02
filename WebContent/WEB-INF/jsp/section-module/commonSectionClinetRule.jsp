<!--  include DWR interface #started  -->
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js"></script>
<script type='text/javascript'src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<!--  include DWR interface #end  -->

<!--  define jsp variable #started  -->
<c:set var="entityTypeLB" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.PARENT_TYPE_LOCALBODY.toString()%>"></c:set>
<c:set var="entityTypeOrg" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.PARENT_TYPE_ORGANIZATION.toString()%>"></c:set>
<c:set var="entitySpecific" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.ENTITY_SPECIFIC.toString()%>"></c:set>
<c:set var="entityFull" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.ENTITY_FULL.toString()%>"></c:set>
<c:set var="entityFullTopLB" value="<%=in.nic.pes.lgd.section.rule.SectionConstant.ENTITY_FULL_WITH_TOP_LOCALBODY.toString()%>"></c:set>
<c:set var="orgTypeCodeMinist" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_MINIST.toString()%>"></c:set>
<c:set var="orgTypeCodeDept" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()%>"></c:set>
<c:set var="orgTypeCodeOrg" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_ORG.toString()%>"></c:set>
<c:set var="isCenter" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.IS_CENTER.toString()%>"></c:set>
<!--  define jsp variable #end  -->

<!--  define custom css #started  -->
<style>
#maincontainer .ms_section_container input[type="text"]{
width: 110%;
padding:6px;
border:1px solid #ccc;
background:#fafafa;
font-family:'Open Sans', arial;
border-radius:5px;
-moz-border-radius:5px;
-webkit-border-radius:5px;
-khtml-border-radius:5px;
-o-border-radius:5px;
-ms-border-radius:5px;
font-size:12px;
color:#666;
transition:all ease-in-out 0.3s;
-moz-box-sizing:border-box;
-webkit-box-sizing:border-box;
resize:none;
box-sizing:border-box;
*behavior: url(css/boxsizing.htc);
}

#maincontainer .ms_section_buttons .bttn {
	background: #999;
	color: #fff;R
	padding: 2px 8px;
	font-size: 12px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	-khtml-border-radius: 3px;
	-o-border-radius: 3px;
	-ms-border-radius: 3px;
	border-radius: 3px;
	display: block;
	margin: 2px auto;
	 width: 69px;
}

#maincontainer .ms_section_container {
	font-family: 'Open Sans', arial;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

#maincontainer .ms_section_container select[multiple] {
	width: 110%;
	padding: 8px;
	border: 3px solid #ccc;
	height: 120px;
	background: #fafafa;
	font-family: 'Open Sans', arial;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-khtml-border-radius: 5px;
	-o-border-radius: 5px;
	-ms-border-radius: 5px;
	font-size: 12px;
	color: #666;
	transition: all ease-in-out 0.3s;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	resize: none;
	box-sizing: border-box;
	overflow-y: scroll;
}



#maincontainer .ms_section_buttons {
    float: left;
    margin-top: 60px;
    padding: 0 20px;
    text-align: center;
    width: 20%;
}
</style>
<!--  define custom css #end  -->

<!-- define main Javascript #started -->
<script>

/**
 * Declare local variable using in script
 */
var _JS_STATE_CODE='${sectionForm.slc}';
var _TOP_LOCALBODY_FLAG=false;
var _pause_time=500; //pause time to load component after return from server
var _options_list = []; //keep list of data after searching
var _select_lbname; 
var _JS_SELECT_OLC;
var orgListIdArray=["orgType","org","orgLevel","orgOffice"];  //organization hierachchy list for state level
var orgCenterListIdArray=["ministryId","centerDeptId","centerOrgId","orgLevel","orgOffice"]; //organization hierachchy list for center level
var deptCenterListIdArray=["ministryId","centerDeptId","orgLevel","orgOffice"]; //department hierachchy list for center level
var spaceCount=0; 
var isCenter=('${sectionForm.isCenterorstate}'=='${isCenter}')?true:false; // if center level then true else false

/**
 * on page load calling #started
 */
$(document).ready(function() {
	/* JS */
	
		showHideStateorCenterDiv();
	
	
	
	/**
	 * The sectionNameEnglish keypress event allow only alphanumbric and some special character{} . 
	 */
	 $("INPUT[name^=sectionNameEnglish]").keypress(function(e){
		// get the key that was pressed
		
		$( '#errsectionNameEnglish').text(""); 	
		var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
		
		//alert(key);
			if(key == 13 && this.nodeName.toLowerCase() == "input")
			{
				return true;
			}
			else if(key == 13)
			{
				return false;
			}
			var allow = false;
			// allow Ctrl+A
			if((e.ctrlKey && key == 97 /* firefox */) || (e.ctrlKey && key == 65) /* opera */) return true;
			// allow Ctrl+X (cut)
			if((e.ctrlKey && key == 120 /* firefox */) || (e.ctrlKey && key == 88) /* opera */) return true;
			// allow Ctrl+C (copy)
			if((e.ctrlKey && key == 99 /* firefox */) || (e.ctrlKey && key == 67) /* opera */) return true;
			// allow Ctrl+Z (undo)
			if((e.ctrlKey && key == 122 /* firefox */) || (e.ctrlKey && key == 90) /* opera */) return true;
			// allow or deny Ctrl+V (paste), Shift+Ins
			if((e.ctrlKey && key == 118 /* firefox */) || (e.ctrlKey && key == 86) /* opera */
			|| (e.shiftKey && key == 45)) return false;
			
			if(this.value.length==0) //intial letter 
			{
				if(key>64 && key<91 /* A-Z */  || key>96 && key<123  /*  a-z */ ){
					
					return true;
				}else{
					$( '#errsectionNameEnglish').text("<spring:message code='error.first.letter.must.be.alphabet' htmlEscape='true'/>"); 
					return false;
				}
			}else{
				if(
						
						key==126 /* ~ */ ||
						key==96 /* ` */ ||
						key==33 /* ! */ ||
						key==64 /* @ */ ||
						key==35 /* # */ ||
						key==36 /* $ */ ||
						key==37 /* % */ ||
						key==94 /* ^ */ ||
						key==38 /* & */ ||
						key==42 /* * */ ||
						key==95 /* - */ ||
						key==43 /* + */ ||
						key==61 /* = */ ||
						key==123 /* { */ ||
						key==125 /* } */ ||
						key==91 /* [ */ ||
						key==93 /* ] */ ||
						key==124 /* | */ ||
						key==92 /* \ */ ||
						key==39 /* ' */ ||
						key==34 /* " */ ||
						key==58 /* : */ ||
						key==59 /* ; */ ||
						key==60 /* < */ ||
						key==62 /* > */ ||
						key==63 /* ? */
						
						
					){
					$( '#errsectionNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>"); 
					return false;
					
				}
				
				if(key==32)/* Space Key */
				{
					if(spaceCount>0){
						$( '#errsectionNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
						return false;
					}
					spaceCount++;
				}else {
					spaceCount=0;
				}
			}
		
	 });
	 
	 
	 	/**
		 * The sectionNameEnglish keyup event allow first character must be alphbet and capital . 
		 */
	  $("INPUT[name^=sectionNameEnglish]").keyup(function(e){
		 var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
		 if(this.value.length==1) //intial letter 
		{
		 if(key>64 && key<91 ){
			this.value=this.value.toUpperCase();
			}
		}
	 }); 
	  
	  loadSearchText=function(){
		 
		  $("INPUT[name^=searchText]").keyup(function(e){
			 // alert(this.value);
			 var specificLbList=$("#specificLbList");
			  var options = $(specificLbList).empty().data('options');
              var search_text = $(this).val().trim().toLowerCase();
             // var regex = new RegExp(search,"gi");
            
              $.each(_options_list, function(i) {
                  var option = _options_list[i];
                  _option_text=option.text.toLowerCase();
                  _match_text=_option_text.substr(0,search_text.length);
                  if(_match_text.search(search_text) !==-1) {
                      $(specificLbList).append(
                         $('<option>').text(option.text).val(option.value)
                      );
                  }
              });
              sortListBox('specificLbList');
	           
		  });
	  };
	  
	  loadSearchTextOrg=function(){
			 
		  $("INPUT[name^=searchTextOrg]").keyup(function(e){
			 // alert(this.value);
			 var specificOrgList=$("#specificOrgList");
			  var options = $(specificOrgList).empty().data('options');
              var search_text = $(this).val().trim().toLowerCase();
             // var regex = new RegExp(search,"gi");
            
              $.each(_options_list, function(i) {
                  var option = _options_list[i];
                  _option_text=option.text.toLowerCase();
                  _match_text=_option_text.substr(0,search_text.length);
                  if(_match_text.search(search_text) !==-1) {
                      $(specificOrgList).append(
                         $('<option>').text(option.text).val(option.value)
                      );
                  }
              });
	           
		  });
	  };
	  
	  
	  $("#btnFormActionSave").click(function() {
		  validateGeneralDetails(); 
	  });
	 
	   
	
	
}); 

/**
 * on page load calling #end
 */
	 
 
 /**
  * The {@code showHideStateorCenterDiv} used show State or Center level Div 
  */
 showHideStateorCenterDiv=function(){
		
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
 
 
 
getlbSpecificList=function(){
	_options_list = [];
	 $('#specificLbList option').each(function() { 
		  _options_list.push({value: $(this).val(), text: $(this).text()});
	  });
};

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


/**
 * Localbody Section below
 */

/**
 * The {@code buildEntityTypeLocalbodyDiv} used to build localbody elements
 */
buildEntityTypeLocalbodyDiv=function(){
	 $("#dynamicLbStructure").empty();
	 //Dynamic div created
	 var divTemplate = $("#dynamicLbStructure");
	 var isDWRCallRequired = true;
	 
	 var templateUL = $("<div/>");
	     templateUL.addClass('box-body');
	     
	 // Added Li Elements for UL
	 var templateLIHierarchy = $("<div/>");
	 templateLIHierarchy.addClass("form-group");
	 
		    
	 // Added Label Elements
	 var templateLabelHierarchy = $("<label/>");
	     templateLabelHierarchy.addClass("col-sm-3 control-label");
		 templateLabelHierarchy.html("<spring:message htmlEscape='true' code='Label.Selecthierarchylevel'/>  <span class='mandatory'>*</span>");
		
		 
	   var templateDiv=$("<div/>"); 
	       templateDiv.addClass("col-sm-6");
	       
	 // Added Html Select Elements
	 var templateSelectHierarchy = $("<select/>");
	 templateSelectHierarchy.addClass("form-control");
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
	
	// Component add to li element 
		templateLIHierarchy.append(templateLabelHierarchy);
		templateLIHierarchy.append(templateDiv);
		templateDiv.append(templateSelectHierarchy);
		templateDiv.append(templateErrorHierarchy);
	// LI Element add to UL element
		templateUL.append(templateLIHierarchy);
		
		
	var templateLILBType = $("<div/>");
		templateLILBType.addClass("form-group");   
		
	// Added Label Elements
	var templateLabelLBType = $("<label/>");
		templateLabelLBType.addClass("col-sm-3 control-label");
		templateLabelLBType.html("<spring:message htmlEscape='true' code='Label.SELECTLOCALBODYTYPE'/>  <span class='mandatory'>*</span>");
			
	var templateCOLLBType = $("<div class=col-sm-6/>");
		
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
		
	// Component add to li element
		templateLILBType.append(templateLabelLBType);
		templateLILBType.append(templateCOLLBType);
		templateCOLLBType.append(templateSelectLBType);
		templateCOLLBType.append(templateErrorLBType);
	// LI Element add to UL element
	    templateUL.append(templateLILBType);
		
			
	var templateLIDiv = $("<div/>");
	var templateDiv = $("<div/>");
	 	templateDiv.attr("id", "divCreateDynamicHierarchy");
		
	 // Component add to li element
	 	templateLIDiv.append(templateDiv);
	 // LI Element add to UL element
		templateUL.append(templateLIDiv);
		    
	//UL element add to dynamic div	
		 divTemplate.append(templateUL);
		
	 /**
	  *   create dynamic spacific and full block
	  */ 
     $("#divSpecificFull").empty();
	 var divSpecificFull = $("#divSpecificFull");
		    
     var templateDivlonglatitude = $("<div/>");
	 templateDivlonglatitude.attr("class", "long_latitude");
	 
	 var templateDivrow = $("<div/>");
	 templateDivrow.attr("class", "row");
	 
	 var templateh4 = $("<h4/>");
	 templateh4.html("<spring:message htmlEscape='true' code='Label.lb.fullorpart'/>  <span class='mandatory'>*</span>");
	 
	 var templateDivcol1 = $("<div/>");
	 templateDivcol1.attr("class", "col");
	 
	 var templatelbSpcific = $("<input/>");
	 templatelbSpcific.attr("id", "lbSpcific");
	 templatelbSpcific.attr("name", "lbSpcificFullRadio");
	 templatelbSpcific.attr("type", "radio");
	 templatelbSpcific.attr("value", "${entitySpecific}");
	 templatelbSpcific.attr("onclick", "hideShowSpecificFullOption()");
	 
	 templateSpanError=$("<span/>")
	 templateSpanError.attr("id", "errorChildParent");
	 templateSpanError.addClass('errormsg');
	
	 
	 templateSpan=$("<span/>")
	  templateSpan.html("<spring:message htmlEscape='true' code='Label.NO'/>");
	 
	 var templatelabel = $("<label/>");
	 templatelabel.append(templatelbSpcific);
	 templatelabel.append(templateSpan);
	 templatelabel.append(templateSpanError);
	 
	 templateDivcol1.append(templatelabel);
	
	 var templateDivcol2 = $("<div/>");
	 templateDivcol2.attr("class", "col");
	 
	 var templatelbFull = $("<input/>");
	 templatelbFull.attr("id", "lbFull");
	 templatelbFull.attr("name", "lbSpcificFullRadio");
	 templatelbFull.attr("type", "radio");
	 templatelbFull.attr("value", "${entityFull}");
	 templatelbFull.attr("onclick", "hideShowSpecificFullOption()");
	 
	 var templatelbunselect = $("<input/>");
	 templatelbunselect.attr("id", "unselect");
	 templatelbunselect.attr("name", "lbSpcificFullRadio");
	 templatelbunselect.attr("type", "radio");
	 templatelbunselect.attr("value", "unselect");
	 templatelbunselect.attr("style", "Display:none");
	 templatelbunselect.prop('checked', true);
	 
	 var templatefullparthidden = $("<input/>");
	 templatefullparthidden.attr("id", "parentChildFlg");
	 templatefullparthidden.attr("name", "parentChildFlg");
	 templatefullparthidden.attr("type", "hidden");
	 
	 var templateentityCodehidden = $("<input/>");
	 templateentityCodehidden.attr("id", "parentCode");
	 templateentityCodehidden.attr("name", "parentCode");
	 templateentityCodehidden.attr("type", "hidden");
	 
	 templateSpan2=$("<span/>")
	 templateSpan2.html("<spring:message htmlEscape='true' code='Label.YES'/>");
	
	 var templatelabel2 = $("<label/>");
	 templatelabel2.append(templatelbFull);
	 templatelabel2.append(templateSpan2);
	 templatelabel2.append(templatelbunselect);
	 templatelabel2.append(templatefullparthidden);
	 templatelabel2.append(templateentityCodehidden);
	
	
	 templateDivcol2.append(templatelabel2);
	
	 templateDivrow.append(templateh4);
	 templateDivrow.append(templateDivcol1);
	 templateDivrow.append(templateDivcol2);
	 
	 templateDivlonglatitude.append(templateDivrow);
	 divSpecificFull.append(templateDivlonglatitude);
    
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
		 
		 if((obj.selectedIndex - 1 > index) && (obj.value != localbodyTypeCode ) &&(category!="U") ){
			 
		    var localbodyTypeName = $(this).text();
		    
		    var templateUL = $("<div/>");
		    templateUL.addClass('box-body');
		    
		    // Added Li Elements for UL
			var templateLI = $("<div/>");
				templateLI.addClass("form-group");
			
		    // Added Label Elements
			var templateLabel = $("<label/>");
				templateLabel.addClass("col-sm-3 control-label");
			templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandatory'>*</span>");
			
			var templateDivGP = $("<div class=col-sm-6/>");
			
			// Added Html Select Elements
			var templateSelect = $("<select/>");
				templateSelect.addClass("form-control");
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
			templateLI.append(templateDivGP);
			templateDivGP.append(templateSelect);
			templateDivGP.append(templateError);
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




/* 
* The {@code hideShowSpecificFullOption} used hide or show childparentflag options.
*/
hideShowSpecificFullOption=function(){
	if(validatLbHierarchy()){
		$("#lbSpcific").prop('checked', false);
		$("#lbFull").prop('checked', false);
		$("#unselect").prop('checked', true);
		
	}else{
		/* get localbody code and localbody type code value */
		 var localBodyTypeElement = $( '#localBodyType' );
			var selectedlocalBodyType = $( localBodyTypeElement ).val();
			var lbTypeArray = selectedlocalBodyType.split("_");
			var parent  = lbTypeArray[2];
			var lbTypeCode  = lbTypeArray[0]; 
			var parentLblc=null;
			if((isParseJson(parent)!=null)){
				var lbCodeArray=  document.getElementsByName('localBodyLevelCodes'); 
				var lbSelect=lbCodeArray[lbCodeArray.length - 1];
				var parentLblc=lbSelect.options[lbSelect.selectedIndex].value
			}
				// alert("parentLblc:"+parentLblc)

		
				if ($("#lbSpcific").is(':checked')) {
					$("#parentChildFlg").val($("#lbSpcific").val());
					buildLBSpecificList(lbTypeCode,parentLblc);
					
				}else  if ($("#lbFull").is(':checked')){
					if(_TOP_LOCALBODY_FLAG){
						$("#parentChildFlg").val('${entityFullTopLB}');
						$("#parentCode").val(lbTypeCode);
						
					}else{
						$("#parentChildFlg").val($("#lbFull").val());
						$("#parentCode").val(parentLblc);
					}
					$("#divLBSpecificBlock").empty();
				}	
		
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
			//alert((isParseJson(parent)==null)+":"+isParseJson(parent));
			if(!(isParseJson(parent)==null)){
				var element = $( '[name = localBodyLevelCodes]' );
				var localBodyElement = $( element )[$( element ).length - 1];
				if(!validateLBCode(localBodyElement)){
					_error_flag=true;
				}
			}else{
				_TOP_LOCALBODY_FLAG=true;
				$("#parentCode").val(lbTypeCode);
				//$("#lbFull").prop('checked', false);
			}
			
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


/**
 * The {@code buildLBSpecificList} function used to build  specific localbody elements 
 * localbody type code and prentlblc are parameter.
 */
buildLBSpecificList=function(lbTypeCode,parentLblc){
	
	$("#divLBSpecificBlock").empty();
	 var templateDiv  = $("#divLBSpecificBlock");
	 templateDiv.addClass("col_1");
	 
	 /* var divLBSpecificList = $("<div/>");
	 divLBSpecificList.attr("id", "divLBSpecificList"); */
	 
	 var templateUL = $("<div/>");
     templateUL.addClass('box-body');
     
     // Added Li Elements for UL
	 var templateLI = $("<div/>");
	 templateLI.attr("id", "firstLevelUnmappedLRDiv" );
	 
     
     
	 var templateDivmsContainer = $("<div/>");
	 templateDivmsContainer.addClass('ms_container row');
	
	 /*    div ms_selectable #started */
	 var templateDivmsSelectable = $("<div/>");
	 templateDivmsSelectable.addClass("ms_selectable col-sm-5 form-group");
	 

	 var templateLabelSelectable = $("<label/>");
	 templateLabelSelectable.html("<spring:message htmlEscape='true' code='Label.SELECT'/> "+_select_lbname);
	 
	 var templateSearchInput=$("<input class=form-control/>");
	 templateSearchInput.attr("id", "searchText" );
	 templateSearchInput.attr("name", "searchText" );
	 templateSearchInput.attr("type", "text" );
	// templateSearchInput.attr("size", "52%" );
	 templateSearchInput.attr("placeholder", "Enter search text to filter below list" );
	
	
	 
	 var templateSelectSpecificLB = $("<select class=form-control/>");
	 templateSelectSpecificLB.attr("id", "specificLbList" );
	 templateSelectSpecificLB.attr("name", "specificLbList" );
	 templateSelectSpecificLB.attr("multiple", "multiple" );
	 
	 templateDivmsSelectable.append(templateLabelSelectable);
	 templateDivmsSelectable.append(templateSearchInput);
	 templateDivmsSelectable.append(templateSelectSpecificLB); 
	 /*    div ms_selectable #end */
	 
	  /*    div ms_buttons #started */
	 var templateDivmsButton = $("<div/>");
	 templateDivmsButton.addClass("ms_buttons col-sm-2");
	 
	 var templateBR=$("<br/>");
	 
	 var templatelbuttonwhole = $("<input/>");	
	 templatelbuttonwhole.addClass("btn btn-primary btn-xs btn-block");
	 templatelbuttonwhole.attr("type", "button");
	 templatelbuttonwhole.attr("value", ">>");
	 templatelbuttonwhole.attr("onclick", "moveElement('WHOLE')");
	 
	 var templatelbuttonback = $("<input/>");	 
	 templatelbuttonback.addClass("btn btn-primary btn-xs btn-block");
	 templatelbuttonback.attr("type", "button");
	 templatelbuttonback.attr("value", "<");
	 templatelbuttonback.attr("onclick", "moveElement('BACK')");
	 
	 var templatelbuttonreset = $("<input/>");	 
	 templatelbuttonreset.addClass("btn btn-primary btn-xs btn-block");
	 templatelbuttonreset.attr("type", "button");
	 templatelbuttonreset.attr("value", "<<");
	 templatelbuttonreset.attr("onclick", "moveElement('RESET')");
	 
	 var templatelbuttonpart = $("<input/>");	 
	 templatelbuttonpart.addClass("btn btn-primary btn-xs btn-block");
	 templatelbuttonpart.attr("type", "button");
	 templatelbuttonpart.attr("value", ">");
	 templatelbuttonpart.attr("onclick", "moveElement('PART')");
	 
	
	 templateDivmsButton.append(templateBR);
	 templateDivmsButton.append(templatelbuttonpart);
	 templateDivmsButton.append(templatelbuttonwhole);
	 templateDivmsButton.append(templatelbuttonback);
	 templateDivmsButton.append(templatelbuttonreset);
	 
	 /*    div ms_buttons #end */
	 
	 /*    div ms_selection #started */
	 var templateDivmsSelection = $("<div/>");
	 templateDivmsSelection.addClass("ms_selection col-sm-5");
	 
	 var templateDivmsSelectFG = $("<div/>");
	 templateDivmsSelectFG.addClass("form-group");
	 
	 var templateLabelSelection = $("<label/>");
	 templateLabelSelection.html("<spring:message htmlEscape='true' code='Label.Selected'/> "+_select_lbname );
	 
	
	 var templateBR=$("<br/>");
	 var templateBR1=$("<br/>");
	 
	  var templateSelectSelection = $("<select class=form-control/>");
	  templateSelectSelection.attr("id", "selectedLbList" );
	  templateSelectSelection.attr("name", "selectedLbList" );
	  templateSelectSelection.attr("multiple", "multiple" );
	  
	 templateSpanError=$("<span/>")
	 templateSpanError.attr("id", "errrselectedLbList");
	 templateSpanError.addClass('errormsg');
			
	 templateDivmsSelectFG.append(templateDivmsSelection);
	/*  templateDivmsSelection.append(templateBR);
	 templateDivmsSelection.append(templateBR1); */
	 templateDivmsSelection.append(templateLabelSelection);
	 templateDivmsSelection.append(templateSelectSelection); 
	 templateDivmsSelection.append(templateSpanError); 
	  
	 /*    div ms_selection #end */
	 
	 /*    div clear #started */
	  var templateDivclear= $("<div/>");
	  templateDivclear.addClass("clear");
	 /*    div clear #end */
	 
	  templateDivmsContainer.append(templateDivmsSelectable);
	  templateDivmsContainer.append(templateDivmsButton);
	  templateDivmsContainer.append(templateDivmsSelection);
	  templateDivmsContainer.append(templateDivclear);
	  
	  
	  templateLI.append(templateDivmsContainer);
	  templateUL.append(templateLI);
	  templateDiv.append(templateUL);
	 /*  templateDiv.append(divLBSpecificList); */
	 //alert("lbTypeCode:"+lbTypeCode);
	 //alert("parentLblc:"+parentLblc);
		loadSearchText();
	 //$('#specificLbList').filterByText($('#searchText'), true);
	  fillLbSpecificList(lbTypeCode,parentLblc);
};

	
/**
 * The {@code fillLbSpecificList} function used to fill  specific localbody list .
 */
fillLbSpecificList=function(lbTypeCode,parentLblc){
	
	removeAllOptions('specificLbList')
	lgdDwrlocalBodyService.getLocalBodyListStateCategoryWise(lbTypeCode,_JS_STATE_CODE,parentLblc,null,'', {
			callback : function(result) {
				
				var options = $("#specificLbList");
				
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					if (obj.isDisturbed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
					}
					if (obj.isUsed) {
						option.attr("disabled", true);
						optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
					}
					(option).val(obj.localBodyCode).text(obj.localBodyNameEng);
					options.append(option);
					
				});
				
				getlbSpecificList();
			},
			async : true
		});	
	
	
};


/**
 *  Organisation Element Script #started
 */

	/**
	 * The {@code buildEntityTypeOrganisationDiv} used to build Organisation elements
	 */
	buildEntityTypeOrganisationDiv=function(){
		 $("#dynamicOrgStructure").empty();
		 var divTemplate = $("#dynamicOrgStructure");
		 var isDWRCallRequired = true;
		 
		 var templateUL = $("<div/>");
		     templateUL.addClass('box-body');
		     
		 // Added Li Elements for UL
		 var templateLIOrgType = $("<div class=form-group/>");
			    
		 // Added Label Elements
		 var templateLabelOrgType = $("<label class=col-sm-3 control-label/>");
			 templateLabelOrgType.html("<spring:message htmlEscape='true' code='Label.selOrgType'/>  <span class='mandatory'>*</span>");
			 
		var templateDivOrg = $("<div class=col-sm-6/>");
				
		// Added Html Select Elements
		var templateSelectOrgType = $("<select class=form-control/>");
			templateSelectOrgType.attr("id", "orgType" );
			templateSelectOrgType.attr("name", "orgLevelCodes" );
			$(templateSelectOrgType).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				if(!$_checkEmptyObject($( this ).val())){
					fillOrganisationList($( this ).val());	
				}
			});
		
		// Added HTML SPAN Element
		var templateErrorOrgType = $("<span/>");
			templateErrorOrgType.attr("id", "error" + $(templateSelectOrgType).attr('id'));
			templateErrorOrgType.attr("class", "errormsg");
			
			// Component add to li element
			templateLIOrgType.append(templateLabelOrgType);
			templateLIOrgType.append(templateDivOrg);
			templateDivOrg.append(templateSelectOrgType);
			templateDivOrg.append(templateErrorOrgType);
			// LI Element add to UL element
		    templateUL.append(templateLIOrgType);	
			
			
		var templateLIOrg = $("<div class=form-group/>");
			    
		// Added Label Elements
		var templateLabelOrg = $("<label class=col-sm-3 control-label/>");
			templateLabelOrg.html("<spring:message htmlEscape='true' code='Label.selDeptorORg'/>  <span class='mandatory'>*</span>");
			
		var templateDivorga = $("<div class=col-sm-6>");
					
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
			
		// Component add to li element
		  	templateLIOrg.append(templateLabelOrg);
		    templateLIOrg.append(templateDivorga);
		    templateDivorga.append(templateSelectOrg);
		    templateDivorga.append(templateErrorOrg);
		 // LI Element add to UL element
		    templateUL.append(templateLIOrg);
		 
		
		var templateLIOrgLevel = $("<div class=form-group/>");
		
		// Added Label Elements
		 var templateLabelOrgLevel = $("<label class=col-sm-3 control-label/>");
			 templateLabelOrgLevel.html("<spring:message htmlEscape='true' code='Label.selUnitLevels'/>  <span class='mandatory'>*</span>");
			 
		var templateDivOrgan = $("<div class=col-sm-6/>");
						
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
			
		// Component add to li element
			templateLIOrgLevel.append(templateLabelOrgLevel);
		    templateLIOrgLevel.append(templateDivOrgan);
		    templateDivOrgan.append(templateSelectOrgLevel);
		    templateDivOrgan.append(templateErrorOrgLevel);
		// LI Element add to UL element  
		    templateUL.append(templateLIOrgLevel);
		
		
		var templateLIOrgOffice = $("<div class=form-group/>");
		    
		// Added Label Elements
		var templateLabelOrgOffice = $("<label class=col-sm-3 control-label/>");
			templateLabelOrgOffice.html("<spring:message htmlEscape='true' code='Label.selOfficeName'/>  <span class='mandatory'>*</span>");
			
		var templateDIvOrgana = $("<div class=col-sm-6>");
				
		// Added Html Select Elements
		var templateSelectOrgOffice = $("<select class=form-control/>");
			templateSelectOrgOffice.attr("id", "orgOffice" );
			templateSelectOrgOffice.attr("name", "orgLevelCodes" );
			$(templateSelectOrgOffice).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				
			});
			
		// Added HTML SPAN Element
		var templateErrorOrgOffice = $("<span/>");
			templateErrorOrgOffice.attr("id", "error" + $(templateSelectOrgOffice).attr('id'));
			templateErrorOrgOffice.addClass('errormsg');
			
		// Component add to li element
		    templateLIOrgOffice.append(templateLabelOrgOffice);
		    templateLIOrgOffice.append(templateDIvOrgana);
		    templateDIvOrgana.append(templateSelectOrgOffice);
		    templateDIvOrgana.append(templateErrorOrgOffice);
		// LI Element add to UL element
		    templateUL.append(templateLIOrgOffice);
			
			    
		    divTemplate.append(templateUL);
		
		  /**
		  *   create dynamic spacific and full block
		  */	
		     $("#divSpecificFullOrg").empty();
			 var divSpecificFullOrg = $("#divSpecificFullOrg");
		    
		    var templateDivlonglatitude = $("<div/>");
		    templateDivlonglatitude.addClass('long_latitude');
			
			 
			 var templateDivrow = $("<div/>");
			 templateDivrow.addClass('row');
			 //templateDivrow.attr("class", "row");
			 
			 var templateh4 = $("<h4/>");
			 templateh4.html("<spring:message htmlEscape='true' code='Label.org.fullorpart'/>  <span class='mandatory'>*</span>");
			 
			 var templateDivcol1 = $("<div/>");
			 templateDivcol1.addClass('col');
			
			 
			 var templateorgSpcific = $("<input/>");
			 templateorgSpcific.attr("id", "orgSpcific");
			 templateorgSpcific.attr("name", "orgSpcificFullRadio");
			 templateorgSpcific.attr("type", "radio");
			 templateorgSpcific.attr("value", "${entitySpecific}");
			 templateorgSpcific.attr("onclick", "hideShowSpecificFullOptionOrg()");
			 
			 templateSpanError=$("<span/>")
			 templateSpanError.attr("id", "errorChildParent");
			 templateSpanError.addClass('errormsg');
			
			
			 
			 templateSpan=$("<span/>")
			  templateSpan.html("<spring:message htmlEscape='true' code='Label.NO'/>");
			 
			 var templatelabel = $("<label/>");
			 templatelabel.append(templateorgSpcific);
			 templatelabel.append(templateSpan);
			 templatelabel.append(templateSpanError);
			 
			 templateDivcol1.append(templatelabel);
			
			  var templateDivcol2 = $("<div/>");
			 templateDivcol2.attr("class", "col");
			 
			 var templateorgFull = $("<input/>");
			 templateorgFull.attr("id", "orgFull");
			 templateorgFull.attr("name", "orgSpcificFullRadio");
			 templateorgFull.attr("type", "radio");
			 templateorgFull.attr("value", "${entityFull}");
			 templateorgFull.attr("onclick", "hideShowSpecificFullOptionOrg()");
			 
			 var templateorgunselect = $("<input/>");
			 templateorgunselect.attr("id", "unselect");
			 templateorgunselect.attr("name", "orgSpcificFullRadio");
			 templateorgunselect.attr("type", "radio");
			 templateorgunselect.attr("value", "unselect");
			 templateorgunselect.attr("style", "Display:none");
			 templateorgunselect.prop('checked', true);
			 
			 var templatefullparthidden = $("<input/>");
			 templatefullparthidden.attr("id", "parentChildFlg");
			 templatefullparthidden.attr("name", "parentChildFlg");
			 templatefullparthidden.attr("type", "hidden");
			 
			 var templateentityCodehidden = $("<input/>");
			 templateentityCodehidden.attr("id", "parentCode");
			 templateentityCodehidden.attr("name", "parentCode");
			 templateentityCodehidden.attr("type", "hidden");
			 
			 templateSpan2=$("<span/>")
			  templateSpan2.html("<spring:message htmlEscape='true' code='Label.YES'/>");
			 
			
			 
			 var templatelabel2 = $("<label/>");
			 templatelabel2.append(templateorgFull);
			 templatelabel2.append(templateSpan2);
			 templatelabel2.append(templateorgunselect);
			 templatelabel2.append(templatefullparthidden);
			 templatelabel2.append(templateentityCodehidden);
			
			
			 templateDivcol2.append(templatelabel2); 
			
			 templateDivrow.append(templateh4);
			 templateDivrow.append(templateDivcol1);
			 templateDivrow.append(templateDivcol2); 
			 
			 templateDivlonglatitude.append(templateDivrow);
			 divSpecificFullOrg.append(templateDivlonglatitude);
		    
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
		//emptyOrgList('orgLevel');
		
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
	 * The {@code validatOrgHierarchy} used validate all drop down of organisation 
	 */
	/* var validatOrgHierarchy=function(){
		var _error_flag=false;
		var errorMsgArray=["<spring:message code='Label.selOrgType' htmlEscape='true'/>","<spring:message code='Label.selDeptorORg' htmlEscape='true'/>","<spring:message code='Label.selUnitLevels' htmlEscape='true'/>","<spring:message code='Label.selOfficeName' htmlEscape='true'/>"]
		for(i=0;i<orgListIdArray.length;i++){
			var orgElement=$( '#'+orgListIdArray[i] );
			if($_checkEmptyObject($( orgElement ).val())){
				_error_flag=true;
				$( orgElement ).addClass("error");
				$( '#error' + $( orgElement ).attr('id') ).text(errorMsgArray[i]);
			}
		}
		
		return _error_flag;
		
	}; */
	
	
	/**
	 * The {@code hideShowSpecificFullOptionOrg} used show and hide organisation speific/full block
	 */
	hideShowSpecificFullOptionOrg=function(){
		if(validatOrgHierarchy()){
			$("#orgSpcific").prop('checked', false);
			$("#orgFull").prop('checked', false);
			$("#unselect").prop('checked', true);
			
		}else{
			
			         var orgLocatedLevelCode=$("#orgOffice").val()
					if ($("#orgSpcific").is(':checked')) {
						$("#parentChildFlg").val($("#orgSpcific").val());
						buildOrgSpecificList(orgLocatedLevelCode);
						
					}else  if ($("#orgFull").is(':checked')){
						
							$("#parentChildFlg").val($("#orgFull").val());
							$("#parentCode").val(orgLocatedLevelCode);
							$("#divOrgSpecificBlock").empty();
							if(isCenter){
								 $("#divOrgSpecificBlockCenter").empty();
							}
					}	
			
		}
	};
	
	/**
	 * The {@code buildOrgSpecificList} used build organisation specific block
	 */
	buildOrgSpecificList=function(orgLocatedLevelCode){
		 $("#divOrgSpecificBlock").empty();
		 var templateDiv  = $("#divOrgSpecificBlock");
		if(isCenter){
			 $("#divOrgSpecificBlockCenter").empty();
			 var templateDiv  = $("#divOrgSpecificBlockCenter");
		} 
		 templateDiv.addClass("col_1");
		
		
		 
		 /* var divLBSpecificList = $("<div/>");
		 divLBSpecificList.attr("id", "divLBSpecificList"); */
		 
		 var templateUL = $("<div/>");
	     templateUL.addClass('box-body');
	     
	     // Added Li Elements for UL
		 var templateLI = $("<div/>");
		 templateLI.attr("id", "firstLevelUnmappedLRDiv" );
		 
	     
	     
		 var templateDivmsContainer = $("<div/>");
		 templateDivmsContainer.addClass('ms_container row');
		 
		 /*    div ms_selectable #started */
		 var templateDivmsSelectable = $("<div/>");
		 templateDivmsSelectable.addClass("ms_selectable col-sm-5 form-group");
		 
	
		 var templateLabelSelectable = $("<label/>");
		 templateLabelSelectable.html("<spring:message htmlEscape='true' code='Label.select.org.unit'/> ");
		 
		 var templateSearchInput=$("<input class=form-control/>");
		 templateSearchInput.attr("id", "searchTextOrg" );
		 templateSearchInput.attr("name", "searchTextOrg" );
		 templateSearchInput.attr("type", "text" );
		 //templateSearchInput.attr("size", "52%" );
		 templateSearchInput.attr("placeholder", "Enter search text to filter below list" );
		
		
		 
		 var templateSelectSpecificOrg = $("<select class=form-control/>");
		 templateSelectSpecificOrg.attr("id", "specificOrgList" );
		 templateSelectSpecificOrg.attr("name", "specificOrgList" );
		 templateSelectSpecificOrg.attr("multiple", "multiple" );
		 
		 templateDivmsSelectable.append(templateLabelSelectable);
		 templateDivmsSelectable.append(templateSearchInput);
		 templateDivmsSelectable.append(templateSelectSpecificOrg); 
		 /*    div ms_selectable #end */
		 
		  /*    div ms_buttons #started */
		 var templateDivmsButton = $("<div/>");
		 templateDivmsButton.addClass("ms_buttons col-sm-2");
		 
		 var templateBR=$("<br/>");
		 
		 var templatelbuttonwhole = $("<input/>");	
		 templatelbuttonwhole.addClass("btn btn-primary btn-xs btn-block");
		 templatelbuttonwhole.attr("type", "button");
		 templatelbuttonwhole.attr("value", ">>");
		 templatelbuttonwhole.attr("onclick", "moveElementOrg('WHOLE')");
		 
		 var templatelbuttonback = $("<input/>");	 
		 templatelbuttonback.addClass("btn btn-primary btn-xs btn-block");
		 templatelbuttonback.attr("type", "button");
		 templatelbuttonback.attr("value", "<");
		 templatelbuttonback.attr("onclick", "moveElementOrg('BACK')");
		 
		 var templatelbuttonreset = $("<input/>");	 
		 templatelbuttonreset.addClass("btn btn-primary btn-xs btn-block");
		 templatelbuttonreset.attr("type", "button");
		 templatelbuttonreset.attr("value", "<<");
		 templatelbuttonreset.attr("onclick", "moveElementOrg('RESET')");
		 
		 var templatelbuttonpart = $("<input/>");	 
		 templatelbuttonpart.addClass("btn btn-primary btn-xs btn-block");
		 templatelbuttonpart.attr("type", "button");
		 templatelbuttonpart.attr("value", ">");
		 templatelbuttonpart.attr("onclick", "moveElementOrg('PART')");
		 
		 templateDivmsButton.append(templateBR);
		 templateDivmsButton.append(templatelbuttonpart);
		 templateDivmsButton.append(templatelbuttonwhole);
		 templateDivmsButton.append(templatelbuttonback);
		 templateDivmsButton.append(templatelbuttonreset);
		 
		 /*    div ms_buttons #end */
		 
		 /*    div ms_selection #started */
		 var templateDivmsSelection = $("<div/>");
		 templateDivmsSelection.addClass("ms_selection col-sm-5");
		 
		
		 
		 var templateBR=$("<br/>");
		 var templateBR1=$("<br/>");
		 
		 var templateDivmsSelectFG = $("<div/>");
		 templateDivmsSelectFG.addClass("form-group");
		 
		 var templateLabelSelection = $("<label/>");
		 templateLabelSelection.html("<spring:message htmlEscape='true' code='Label.selected.org.unit'/> ");
		 
		
		
		 
		  var templateSelectSelection = $("<select class=form-control/>");
		  templateSelectSelection.attr("id", "selectedOrgList" );
		  templateSelectSelection.attr("name", "selectedOrgList" );
		  templateSelectSelection.attr("multiple", "multiple" );
		  
		 templateSpanError=$("<span/>")
		 templateSpanError.attr("id", "errorselectedOrgList");
		 templateSpanError.addClass('errormsg');
				
		 templateDivmsSelectFG.append(templateDivmsSelection);
		  /* templateDivmsSelection.append(templateBR);
		  templateDivmsSelection.append(templateBR1); */
		  templateDivmsSelection.append(templateLabelSelection);
		  templateDivmsSelection.append(templateSelectSelection); 
		  templateDivmsSelection.append(templateSpanError); 
		  
		 /*    div ms_selection #end */
		 
		 /*    div clear #started */
		  var templateDivclear= $("<div/>");
		  templateDivclear.addClass("clear");
		 /*    div clear #end */
		 
		  templateDivmsContainer.append(templateDivmsSelectable);
		  templateDivmsContainer.append(templateDivmsButton);
		  templateDivmsContainer.append(templateDivmsSelection);
		  templateDivmsContainer.append(templateDivclear);
		  
		  
		  templateLI.append(templateDivmsContainer);
		  templateUL.append(templateLI);
		  templateDiv.append(templateUL);
		 /*  templateDiv.append(divLBSpecificList); */
		 //alert("lbTypeCode:"+lbTypeCode);
		 //alert("parentLblc:"+parentLblc);
			loadSearchTextOrg();
		 //$('#specificLbList').filterByText($('#searchText'), true);
		  fillOrgSpecificList(orgLocatedLevelCode);
	};
	
	
	/**
	 * The {@code getOrgSpecificList} used fill organisation specific List into _options_list
	 */
	getOrgSpecificList=function(){
		_options_list = [];
		 $('#specificOrgList option').each(function() { 
			  _options_list.push({value: $(this).val(), text: $(this).text()});
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
					
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.orgUnitCode).text(obj.orgUnitName);
						options.append(option);
						
					});
					
					getOrgSpecificList();
				},
				async : true
			});	
		
		
	};


/**
 *  Organisation Element Script #end
 */

 
 
/*
 *  Center Organisation Element Script #started 
 */
 


	/*
	 * The {@code buildEntityTypeOrganisationCenterDiv} used create and fill dynamic component for center div
	 */ 
	buildEntityTypeOrganisationCenterDiv=function(){
		 $("#dynamicOrgStructureCenter").empty();
		 var divTemplate = $("#dynamicOrgStructureCenter");
		 var isDWRCallRequired = true;
		 
		
		 
		 var tempDivFormGroup = $("<div/>");
		 tempDivFormGroup.addClass('form-group');
	     
	    
		 var templateLabelOrgType = $("<label/>");
		 templateLabelOrgType.addClass("col-sm-3 control-label");
		 templateLabelOrgType.html("<spring:message htmlEscape='true' code='Label.selOrgType'/>  <span class='mandatory'>*</span>");
	
		 var templateDivCol=$("<div/>"); 
		 templateDivCol.addClass("col-sm-6");
			 
		 var templateSelectOrgType = $("<select/>");
			 templateSelectOrgType.attr("id", "orgType" );
			 templateSelectOrgType.attr("name", "orgTypeCode" );
			 templateSelectOrgType.addClass("form-control");
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
			
			templateDivCol.append(templateSelectOrgType);
			templateDivCol.append(templateErrorOrgType);
			tempDivFormGroup.append(templateLabelOrgType);
			tempDivFormGroup.append(templateDivCol);
		    
		    
		    divTemplate.append(tempDivFormGroup);
		    fillOrganisationType();
	};



	/**
	 * The {@code buildDeptorOrgCenterDiv} used create and fill dynamic Deparment or Organisation div for center 
	 */ 
	buildDeptorOrgCenterDiv=function(orgType){
		 
		
		 $("#deptorOrg").empty();
		 var divTemplate = $("#deptorOrg");
		 var isDWRCallRequired = true;
		 
		 var tempDivFormGroup = $("<div/>");
		 tempDivFormGroup.addClass('form-group');
	     
	    
		 var templateLabelOrgType = $("<label/>");
		 templateLabelOrgType.addClass("col-sm-3 control-label");
		 templateLabelOrgType.html("<spring:message htmlEscape='true' code='Label.SELMIN'/>  <span class='mandatory'>*</span>");
	
		 var templateDivCol=$("<div/>"); 
		 templateDivCol.addClass("col-sm-6");
		 
		 var templateSelectDeptorOrg = $("<select/>");
			 templateSelectDeptorOrg.attr("id", "ministryId" );
			 templateSelectDeptorOrg.attr("name", "orgLevelCodes" );
			 templateSelectDeptorOrg.addClass("form-control");
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
			
		// Component add to li element 
			templateDivCol.append(templateSelectDeptorOrg);
			templateDivCol.append(templateErrorDeptorOrg);
			tempDivFormGroup.append(templateLabelOrgType);
			tempDivFormGroup.append(templateDivCol);
			divTemplate.append(tempDivFormGroup);
		// LI Element add to UL element
		   
			    
		
		 var tempDivFormGroup1 = $("<div/>");
		 tempDivFormGroup1.addClass('form-group');
	     
	    
		 var templateLabelOrgType1 = $("<label/>");
		 templateLabelOrgType1.addClass("col-sm-3 control-label");
		 templateLabelOrgType1.html("<spring:message htmlEscape='true' code='Label.SELDEPT'/>  <span class='mandatory'>*</span>");
	
		 var templateDivCol1=$("<div/>"); 
		 templateDivCol1.addClass("col-sm-6");
		 
		 // Added HTML Select Elements	 
		var templateSelectDept = $("<select/>");
			templateSelectDept.attr("id", "centerDeptId" );
			templateSelectDept.attr("name", "orgLevelCodes" );
			templateSelectDept.addClass("form-control");
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
			
		// Component add to li element 
			templateDivCol1.append(templateSelectDept);
			templateDivCol1.append(templateErrorDept);
			tempDivFormGroup1.append(templateLabelOrgType1);
			tempDivFormGroup1.append(templateDivCol1);
			divTemplate.append(tempDivFormGroup1);
		// LI Element add to UL element
		   
					    
		    if(orgType!=2){
		    	
		    	 var tempDivFormGroup2 = $("<div/>");
				 tempDivFormGroup2.addClass('form-group');
			     
			    
				 var templateLabelOrgType2 = $("<label/>");
				 templateLabelOrgType2.addClass("col-sm-3 control-label");
				 templateLabelOrgType2.html("<spring:message htmlEscape='true' code='Label.SELORG'/>  <span class='mandatory'>*</span>");
			
				 var templateDivCol2=$("<div/>"); 
				 templateDivCol2.addClass("col-sm-6");
		    	
				 // Added HTML Select Elements	 
				 var templateSelectOrg = $("<select/>");
					 templateSelectOrg.attr("id", "centerOrgId" );
					 templateSelectOrg.attr("name", "orgLevelCodes" );
					 templateSelectOrg.addClass("form-control");
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
					
				// Component add to li element
					templateDivCol2.append(templateSelectOrg);
					templateDivCol2.append(templateErrorOrg);
					tempDivFormGroup2.append(templateLabelOrgType2);
					tempDivFormGroup2.append(templateDivCol2);
					divTemplate.append(tempDivFormGroup2);
				// LI Element add to UL element
				   
		    }
		    
		 
		    var tempDivFormGroup3 = $("<div/>");
			 tempDivFormGroup3.addClass('form-group');
		     
		    
			 var templateLabelOrgType3 = $("<label/>");
			 templateLabelOrgType3.addClass("col-sm-3 control-label");
			 templateLabelOrgType3.html("<spring:message htmlEscape='true' code='Label.selUnitLevels'/>  <span class='mandatory'>*</span>");
		
			 var templateDivCol3=$("<div/>"); 
			 templateDivCol3.addClass("col-sm-6");
		
		
								
		// Added Html Select Elements
		var templateSelectOrgLevel = $("<select/>");
			templateSelectOrgLevel.attr("id", "orgLevel" );
			templateSelectOrgLevel.attr("name", "orgLevelCodes" );
			templateSelectOrgLevel.addClass("form-control");
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
			
		// Component add to li element
		 	templateDivCol3.append(templateSelectOrgLevel);
			templateDivCol3.append(templateErrorOrgLevel);
			tempDivFormGroup3.append(templateLabelOrgType3);
			tempDivFormGroup3.append(templateDivCol3);
			
		// LI Element add to UL element
		    divTemplate.append(tempDivFormGroup3);
						
		// Added Li Elements for UL 
		
		   var tempDivFormGroup4 = $("<div/>");
			 tempDivFormGroup4.addClass('form-group');
		     
		    
			 var templateLabelOrgType4 = $("<label/>");
			 templateLabelOrgType4.addClass("col-sm-3 control-label");
			 templateLabelOrgType4.html("<spring:message htmlEscape='true' code='Label.selOfficeName'/>  <span class='mandatory'>*</span>");
		
			 var templateDivCol4=$("<div/>"); 
			 templateDivCol4.addClass("col-sm-6");
		
						
		// Added Html Select Elements
		var templateSelectOrgOffice = $("<select/>");
			templateSelectOrgOffice.attr("id", "orgOffice" );
			templateSelectOrgOffice.attr("name", "orgLevelCodes" );
			templateSelectOrgOffice.addClass("form-control");
			$(templateSelectOrgOffice).change(function() {
				$( this ).removeClass("error");
				$( '#error' + $( this ).attr('id') ).text("");
				
			});
			
		// Added HTML SPAN Element
		var templateErrorOrgOffice = $("<span/>");
			templateErrorOrgOffice.attr("id", "error" + $(templateSelectOrgOffice).attr('id'));
			templateErrorOrgOffice.addClass('errormsg');
			
		// Component add to li element
		templateDivCol4.append(templateSelectOrgOffice);
			templateDivCol4.append(templateErrorOrgOffice);
			tempDivFormGroup4.append(templateLabelOrgType4);
			tempDivFormGroup4.append(templateDivCol4);
	    // LI Element add to UL element
		   divTemplate.append(tempDivFormGroup4);
					    
							    
			
			    
				
			/* create dynamic spacific and full block */	
			 $("#divOrgSpecificBlockCenter").empty();
		     $("#divSpecificFullOrgCenter").empty();
			 var divSpecificFullOrg = $("#divSpecificFullOrgCenter");
			 
			 
		   
		    var templateDivlBoxheader = $("<div/>");
		    templateDivlBoxheader.addClass('box-header subheading');
			
			
			 
			 var templateh4 = $("<h4/>");
			 templateh4.addClass('box-title');
			 templateh4.html("<spring:message htmlEscape='true' code='Label.org.fullorpart'/>  <span class='mandate'>*</span>");
			 
			 templateDivlBoxheader.append(templateh4);
			 
			 
			   var tempDivFormGroupradio = $("<div/>");
			   tempDivFormGroupradio.addClass('form-group');
			   
				 var templateLabelradio = $("<label/>");
				 templateLabelradio.addClass("col-sm-1 control-label");
				 
				 var templateDivradio=$("<div/>"); 
				 templateDivradio.addClass("col-sm-6");
				 
				 
				 var templateLabelradioinline1 = $("<label/>");
				 templateLabelradioinline1.addClass("radio-inline");
				 
				 var templateorgSpcific = $("<input/>");
				 templateorgSpcific.attr("id", "orgSpcific");
				 templateorgSpcific.attr("name", "orgSpcificFullRadio");
				 templateorgSpcific.attr("type", "radio");
				 templateorgSpcific.attr("value", "${entitySpecific}");
				 templateorgSpcific.attr("onclick", "hideShowSpecificFullOptionOrg()");
				 
				 templateSpanError=$("<span/>")
				 templateSpanError.attr("id", "errorChildParent");
				 templateSpanError.addClass('errormsg');
				
				
				 
				 templateSpan=$("<span/>")
				 templateSpan.html("<spring:message htmlEscape='true' code='Label.NO'/>");
				 
				 templateLabelradioinline1.append(templateorgSpcific);
				 templateLabelradioinline1.append(templateSpan);
				 templateLabelradioinline1.append(templateSpanError);
				 
				 
				 var templateLabelradioinline2 = $("<label/>");
				 templateLabelradioinline2.addClass("radio-inline");
				 
				 
				 var templateorgFull = $("<input/>");
				 templateorgFull.attr("id", "orgFull");
				 templateorgFull.attr("name", "orgSpcificFullRadio");
				 templateorgFull.attr("type", "radio");
				 templateorgFull.attr("value", "${entityFull}");
				 templateorgFull.attr("onclick", "hideShowSpecificFullOptionOrg()");
				 
				 var templateorgunselect = $("<input/>");
				 templateorgunselect.attr("id", "unselect");
				 templateorgunselect.attr("name", "orgSpcificFullRadio");
				 templateorgunselect.attr("type", "radio");
				 templateorgunselect.attr("value", "unselect");
				 templateorgunselect.attr("style", "Display:none");
				 templateorgunselect.prop('checked', true);
				 
				 var templatefullparthidden = $("<input/>");
				 templatefullparthidden.attr("id", "parentChildFlg");
				 templatefullparthidden.attr("name", "parentChildFlg");
				 templatefullparthidden.attr("type", "hidden");
				 
				 var templateentityCodehidden = $("<input/>");
				 templateentityCodehidden.attr("id", "parentCode");
				 templateentityCodehidden.attr("name", "parentCode");
				 templateentityCodehidden.attr("type", "hidden");
				 
				 templateSpan2=$("<span/>")
				  templateSpan2.html("<spring:message htmlEscape='true' code='Label.YES'/>");
				 
				 templateLabelradioinline2.append(templateorgFull);
				 templateLabelradioinline2.append(templateSpan2);
				 templateLabelradioinline2.append(templateorgunselect);
				 templateLabelradioinline2.append(templatefullparthidden);
				 templateLabelradioinline2.append(templateentityCodehidden);
				 
				 templateDivradio.append(templateLabelradioinline1);
				 templateDivradio.append(templateLabelradioinline2);
				 
				 tempDivFormGroupradio.append(templateLabelradio);
				 tempDivFormGroupradio.append(templateDivradio);
				 
				 divSpecificFullOrg.append(templateDivlBoxheader);
				 divSpecificFullOrg.append(tempDivFormGroupradio);
			 
			 
			
			 
			
			 
			/* 
			 var templatelabel = $("<label/>");
			 templatelabel.append(templateorgSpcific);
			 templatelabel.append(templateSpan);
			 templatelabel.append(templateSpanError);
			 
			 templateDivcol1.append(templatelabel);
			
			  var templateDivcol2 = $("<div/>");
			 templateDivcol2.attr("class", "col");
			 
			
			 
			 templateSpan2=$("<span/>")
			  templateSpan2.html("<spring:message htmlEscape='true' code='Label.YES'/>");
			 
			
			 
			 var templatelabel2 = $("<label/>");
			 templatelabel2.append(templateorgFull);
			 templatelabel2.append(templateSpan2);
			 templatelabel2.append(templateorgunselect);
			 templatelabel2.append(templatefullparthidden);
			 templatelabel2.append(templateentityCodehidden);
			
			
			 templateDivcol2.append(templatelabel2); 
			
			 templateDivrow.append(templateh4);
			 templateDivrow.append(templateDivcol1);
			 templateDivrow.append(templateDivcol2); 
			 
			 templateDivlonglatitude.append(templateDivrow);
			 divSpecificFullOrg.append(templateDivlonglatitude); */
			
			
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


/**
 *  Center Organisation Element Script #end 
 */ 
 
 
 
 
 
/**
 *  @ Utility Function #started 
 */

 
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

 
 
 /**
  * The {@code moveElement} function used move items from one listbox to another,
  * based on copyid,pasteId and action(parameter)
  */
 moveElement=function(action){
 	var copyId=null;
 	var pasteId=null;
 	if(action=="WHOLE" ||action=="PART" ){
 		copyId='specificLbList';
 		pasteId='selectedLbList';
 	}else if(action=="BACK" ||action=="RESET" ){
 		copyId='selectedLbList';
 		pasteId='specificLbList'; 
 	}
 	if(action=="BACK"){
 		addlbSpecifcList('selectedLbList');
 	}else if(action=="RESET"){
 		addlbSpecifcListALL('selectedLbList');
 	}
 	if(action=="WHOLE" ||action=="RESET" ){
 		$('#'+copyId+' option').clone().appendTo('#'+pasteId);
 		removeAllOptions(copyId);
 	}else if(action=="BACK" ||action=="PART" ){
 		$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
 	}
 	sortListBox(copyId);
 	sortListBox(pasteId);
 	if(action=="WHOLE" ||action=="PART" ){
 		removelbSpecifcList('selectedLbList');
 	}	
 	
 };


 /**
  * The {@code moveElement} function used move items from one listbox to another,
  * based on copyid,pasteId and action(parameter)
  */
 moveElementOrg=function(action){
 	var copyId=null;
 	var pasteId=null;
 	if(action=="WHOLE" ||action=="PART" ){
 		copyId='specificOrgList';
 		pasteId='selectedOrgList';
 	}else if(action=="BACK" ||action=="RESET" ){
 		copyId='selectedOrgList';
 		pasteId='specificOrgList'; 
 	}
 	if(action=="BACK"){
 		addlbSpecifcList('selectedOrgList');
 	}else if(action=="RESET"){
 		addlbSpecifcListALL('selectedOrgList');
 	}
 	
 	if(action=="WHOLE" ||action=="RESET" ){
 		$('#'+copyId+' option').clone().appendTo('#'+pasteId);
 		removeAllOptions(copyId);
 	}else if(action=="BACK" ||action=="PART" ){
 		$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
 	}
 	sortListBox(copyId);
 	sortListBox(pasteId);	
 	if(action=="WHOLE" ||action=="PART" ){
 		removelbSpecifcList('selectedOrgList');
 	}	
 	
 };

 
 /**
  * The {@code addlbSpecifcList} function used update option list for search after back some element to list
  */
 addlbSpecifcList=function(id){

 	$('#'+id+' option:selected').each(function() { 
 	_options_list.push({value:$(this).val(), text: $(this).text()});
 	});
 };

 /**
  * The {@code addlbSpecifcListALL} function used update option list for search after back all element to list
  */
 addlbSpecifcListALL=function(id){

 	$('#'+id+'  option').each(function() { 
 	_options_list.push({value:$(this).val(), text: $(this).text()});
 	});
 };

 /**
  * The {@code removelbSpecifcList} function used update option list for search after whole and  part all element to list
  */
 removelbSpecifcList=function(id){
 	var _search_patt="";
 	var _temp_list = [];
 	
 		$('#'+id+'  option').each(function() { 
 			_search_patt=_search_patt+$(this).val()+",";
 			
 			});
 		
 		$.each(_options_list, function(key, obj) {
 			if(!_search_patt.contains(obj.value)){
 				_temp_list.push({value:obj.value, text: obj.text});
 			}	
 		  });
 		
 		_options_list=_temp_list;
 	
 	
 };
 /**
  * The {@code sortListBox} function used sort items of listbox ,
  * based on listbox id
  */
 sortListBox=function(id){
 	 var $r = $("#"+id+" option");
     $r.sort(function(a, b) {
         if (a.text < b.text) return -1;
         if (a.text == b.text) return 0;
         return 1;
     });
     $($r).remove();
     $("#"+id).append($($r));
     
     
 };
 
 
 
/**
 *  @ Utility Function #end 
 */

 
 
 
 
 
/**
 * validate Client rule all method define here  #started
 */

 validateGeneralDetails=function(){
	 $( "span[id^=error]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	errors[0]= validateSelectionNameEng();
	errors[1]=validateParentType();
	//errors[2]=validatePinCode();
	if ($("#lbEntityType").is(':checked')) {
		errors[2]=!validatLbHierarchy();
		errors[3]=validateChildParentFlag();
		errors[4]=validateLBSelectedList();
		
		
	}else{
		errors[2]=!validatOrgHierarchy();
		errors[3]=validateChildParentFlag();
		errors[4]=validateOrgSelectedList();
	}
	
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	if(error){
		if ($("#lbEntityType").is(':checked')) {
			 $("#selectedLbList option").prop("selected",true);
		}else{
			 $("#selectedOrgList option").prop("selected",true);
		}
		$( "#btnFormActionSave" ).prop( "disabled", true );
		
		callActionUrl('buildSection.htm');
	}
	
	
	
}

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
 
 validatePinCode=function(){
	 var pinCode=$("#sectionNameEnglish");
	 if(!$_checkEmptyObject($(pinCode).val())){
		 var re = /[^0-9]/g;    
		 if ( re.test($(pinCode).val()) ){ 
			 $( '#error' + $(pinCode).attr('id') ).text("<spring:message code='label.pin.code.allow.numberic' htmlEscape='true'/>");
			 return false;
		 }
	 }
	 
	 return true;
 };

validateSelectionNameEng=function(){
	
	var re = new RegExp("^[a-zA-Z0-9 \.\,\(\)\/-\]+$", "g");
	 var SectionNameEng=$("#sectionNameEnglish").val();
	 //alert(SectionNameEng);
	 //alert(SectionNameEng.charAt(0).charCodeAt());
	 if($_checkEmptyObject(SectionNameEng)){
		 $( '#errsectionNameEnglish').text("<spring:message code='Label.section.name.required' htmlEscape='true'/>");
		 return false;
	 }else{
		 if(!re.test(SectionNameEng)){
			 $( '#errsectionNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>");	 
			 return false;
		 } else{
			 var key=SectionNameEng.charAt(0).charCodeAt();
			 //alert(key);
			 if(!(key>64 && key<91)){
				 $( '#errsectionNameEnglish').text("<spring:message code='error.first.letter.must.be.alphabet' htmlEscape='true'/>");	
				 return false;
			 }else{
				 for(i=0;i<SectionNameEng.length;i++){
					 key=SectionNameEng.charAt(i).charCodeAt();
					 if(key==32)/* Space Key */
						{
							if(spaceCount>0){
								$( '#errsectionNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
								return false;
							}
							spaceCount++;
						}else {
							spaceCount=0;
						}
				 }
			 }
			 
			 
		 }
	 }
	 return true;
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
}

validateChildParentFlag=function(){
	var parentChildFlg=$("#parentChildFlg").val();
	if($_checkEmptyObject(parentChildFlg)){
		var _error_msg="<spring:message code='label.choose.parent.child.flag' htmlEscape='true'/>";
		/* if ($("#lbEntityType").is(':checked')) {
			_error_msg="<spring:message code='label.choose.parent.child.flag' htmlEscape='true'/>";
		} */
		
		$( '#errorChildParent').text(_error_msg);
		return false;
	}
	return true;
};

validateLBSelectedList=function(){
	//alert($('#selectedLbList').children().length);
	if (($("#lbSpcific").is(':checked')) && ($('#selectedLbList').children().length == 0)) {
		$( '#errrselectedLbList').text("<spring:message code='label.select.lbspecific.list' htmlEscape='true'/>");
		return false;
	}
	return true;
};

validateOrgSelectedList=function(){
	//alert($('#selectedLbList').children().length);
	if (($("#orgSpcific").is(':checked')) && ($('#selectedOrgList').children().length == 0)) {
		$( '#errorselectedOrgList').text("<spring:message code='label.select.orgspecific.list' htmlEscape='true'/>");
		return false;
	}
	return true;
};
/**label.choose.parent.child.flag
 * validate Client rule all method define above  #end
 */
 
 callActionUrl=function(url){
	 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
		document.forms['sectionForm'].method = "POST";
	    document.forms['sectionForm'].submit(); */
	   
	    $( 'form[id=sectionForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=sectionForm]' ).attr('method','post');
		$( 'form[id=sectionForm]' ).submit();
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
				buildEntityTypeLocalbodyDiv();
				setTimeout(function(){
					$("#divEntityTypeLB").show(); 
					$("#lbEntityType").prop('checked', true);
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
	
	
	loadOrgspecific=function(){
		var load_parent_child_flag='${sectionForm.parentChildFlg}';
				if($_checkEmptyObject(load_parent_child_flag)){
					$( '#errorChildParent').text("<spring:message code='Label.section.choose.parent.child.flag' htmlEscape='true'/>");	 
				}else if(load_parent_child_flag=="${entitySpecific}"){
					/* get localbody code and localbody type code value */
					//alert("in");
					setTimeout(function(){
					$("#orgSpcific").prop('checked', true);
					$("#parentChildFlg").val(load_parent_child_flag);
					var orgLevelCodes='${sectionForm.orgLevelCodes}';
					var orgLevelCodeArray=orgLevelCodes.split(",");
					var orgLocatedLevelCode=parseInt(orgLevelCodeArray[3]);
					if(isCenter){
						orgLocatedLevelCode=parseInt(orgLevelCodeArray[4]);
					}
					//setTimeout(function(){
					buildOrgSpecificList(orgLocatedLevelCode);
					},_pause_time);
					} else{
						$("#orgFull").prop('checked', true);
						$("#parentChildFlg").val(load_parent_child_flag);
						
					}
	
		 
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
				} 
				
				if($_checkEmptyObject(orgLevelCodeArray[i])){
					_error_flag=true;
					$( orgElement ).addClass("error");
					$( '#error' + $( orgElement ).attr('id') ).text("<spring:message code='Label.Please' htmlEscape='true'/>"
							+" "+errorMsgArray[i]);
				}
				else{
					//alert("parent:"+parent);	 
					
					/* setTimeout(function(){
						 $("#" + $( orgElement ).attr('id') +" option[value='" +selvalue + "']").attr("selected", "selected");
						 
						},200); */
					fillAndSelectOrgElements(parent,orgElement,orgLevelCodeArray[i]);
					//alert("orgLevelCodeArray["+i+"]:"+orgLevelCodeArray[i]);
					parent=orgLevelCodeArray[i];
					
				}
			} 
			
			return _error_flag;
		}
		
		
	};
	
	 fillAndSelectOrgElements=function(parent,orgElement,selvalue){
		
			setTimeout(function(){
				 $("#" + $( orgElement ).attr('id') +" option[value='" +selvalue + "']").attr("selected", "selected");
				 
				},200);
		
	}; 
	
	loadlbspecific=function(){
		var load_parent_child_flag='${sectionForm.parentChildFlg}';
				if($_checkEmptyObject(load_parent_child_flag)){
					$( '#errorChildParent').text("<spring:message code='Label.section.choose.parent.child.flag' htmlEscape='true'/>");	 
				}else if(load_parent_child_flag=="${entitySpecific}"){
					/* get localbody code and localbody type code value */
					//alert("in");
					//setTimeout(function(){
					$("#lbSpcific").prop('checked', true);
					$("#parentChildFlg").val(load_parent_child_flag);
					var localBodyTypeElement = $( '#localBodyType' );
						var selectedlocalBodyType = $( localBodyTypeElement ).val();
						var lbTypeArray = selectedlocalBodyType.split("_");
						var parent  = lbTypeArray[2];
						var lbTypeCode  = lbTypeArray[0]; 
						var parentLblc=null;
						if((isParseJson(parent)!=null)){
							var lbCodeArray=  document.getElementsByName('localBodyLevelCodes'); 
							var lbSelect=lbCodeArray[lbCodeArray.length - 1];
							var parentLblc=lbSelect.options[lbSelect.selectedIndex].value;
						}
							 //alert("parentLblc:"+parentLblc+"lbTypeCode:"+lbTypeCode);
		                 //setTimeout(function(){
			             buildLBSpecificList(lbTypeCode,parentLblc);
		                 //},_pause_time);
					} else{
						$("#lbFull").prop('checked', true);
						$("#parentChildFlg").val(load_parent_child_flag);
						
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
		 
		 $(localBodyTypeElement).each(function(index){
			 var localbodyTypeArray = $(this).val().split("_");
			 var localbodyTypeCode = localbodyTypeArray[0];
			 var tiersetupCode = localbodyTypeArray[1];
			 var parentTiersetupCode = localbodyTypeArray[2];
			 var category= localbodyTypeArray[4];
			 //alert(category);
			// alert("lb"+index+":"+lbcodeArray[index]);
			 if(obj.selectedIndex-1==index){
				 _select_lbname= $(this).text();;
				   // alert(_select_lbname+":"+obj.selectedIndex+":"+index); 
			 }
			if((obj.selectedIndex - 1 > index) && (obj.value != localbodyTypeCode ) &&(category!="U") ){
				 
			    var localbodyTypeName = $(this).text();
			    
			    var templateUL = $("<ul/>");
			    templateUL.addClass('form_body');
			    
			    // Added Li Elements for UL
				var templateLI = $("<li/>");
				
			    // Added Label Elements
				var templateLabel = $("<label/>");
				templateLabel.html("<spring:message htmlEscape='true' code='Label.SELECT'/> " + localbodyTypeName + " <span class='mandate'>*</span>");
				
				// Added Html Select Elements
				var templateSelect = $("<select/>");
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
				templateLI.append(templateSelect);
				templateLI.append(templateError);
				templateUL.append(templateLI);
				divTemplate.append(templateUL);
				
				if(!isDWRCallRequired && !($_checkEmptyObject(lbcodeArray[index-1]))){
					 //alert(lbcodeArray[index-1]);
					 buildParentwisePanchayatOptions($( templateSelect ).attr('id'),lbcodeArray[index-1]);
					}
				 if($_checkEmptyObject(lbcodeArray[index])){
						$( templateSelect ).addClass("error");
						$( '#error' + $( templateSelect ).attr('id') ).text("<spring:message code='Label.select' htmlEscape='true'/> "+localbodyTypeName);	 
					 }else{
						 setTimeout(function(){
							 $("#" + $( templateSelect ).attr('id') +" option[value='" + lbcodeArray[index] + "']").attr("selected", "selected");
							 
							},200);
						
				
					 }
				
			 }
		});
	};
	
	
	
	loadCenterOrganisationHierarchy=function(){
		
		var orgType='${sectionForm.orgTypeCode}';
		var orgLevelCodes='${sectionForm.orgLevelCodes}';
		var _error_flag=false;
		
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
					} 
					
					if($_checkEmptyObject(orgLevelCodeArray[i])){
						_error_flag=true;
						$( orgElement ).addClass("error");
						$( '#error' + $( orgElement ).attr('id') ).text("<spring:message code='Label.Please' htmlEscape='true'/>"
								+" "+errorMsgArray[i]);
					}
					else{
						
						fillAndSelectOrgElements(parent,orgElement,orgLevelCodeArray[i]);
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
					} 
					
					if($_checkEmptyObject(orgLevelCodeArray[i])){
						_error_flag=true;
						$( orgElement ).addClass("error");
						$( '#error' + $( orgElement ).attr('id') ).text("<spring:message code='Label.Please' htmlEscape='true'/>"
								+" "+errorMsgArray[i]);
					}
					else{
						
						fillAndSelectOrgElements(parent,orgElement,orgLevelCodeArray[i]);
						parent=orgLevelCodeArray[i];
						
					}
				} 
			}
		}
			return _error_flag;
	};



/*
 *  # end
 * all method are above used to build ,fill elements and show error message
 *  after return from server error
 */
 
 
 
</script>

<!--#stared page return from server with error then call loadElementandShowError  method -->
<c:if test="${serverError eq true}">
			<script>
			$(window).load(function () {
				loadElementandShowError();
			}); 
			</script>
</c:if>
<!--#end page return from server with error then call loadElementandShowError  method -->
