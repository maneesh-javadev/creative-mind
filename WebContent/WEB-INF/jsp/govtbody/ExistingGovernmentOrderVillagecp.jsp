 <% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script> --%>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.min.js"></script>
<!--  <script type="text/javascript" src="datepicker/jquery-1.6.2.js" charset="utf-8"></script>
<script type="text/javascript" src="datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.widget.js"></script>
<script type="text/javascript" src="datepicker/jquery.ui.datepicker.js"></script> -->
<!-- <script type="text/javascript" src="datepicker/calender.js"></script> -->
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" /> -->
<!-- <link rel="stylesheet" href="datepicker/demos.css" /> -->

<script type="text/javascript">

<!--
/**
 * The {@code ready} initialized once page started excuting 
 * and invoke all on load events.
 */ 
$(document).ready(function() {
	$("#bdateParamFrom").datetimepicker({
		format: 'dd-mm-yyyy',
		startView : 'month',
		endDate: '+0d',
        autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	}).on('changeDate', function(selected){
	 $('#bdateParamTo').datetimepicker('setStartDate', Date.parse( selected.date));
	});
	
	$("#bdateParamTo").datetimepicker({
		format: 'dd-mm-yyyy',
		startView : 'month',
		endDate: '+0d',
        autoclose: true,
		minView : 'month',
		pickerPosition : "bottom-left",
		EndDate:'0',
	}).on('changeDate', function(selected){
	 $('#bdateParamTo').datetimepicker('setEndDate', Date.parse( selected.date));
	});
   
	setDefaultDates();
	
	/* <c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
		$("#templateId").change(function() {
			CKEDITOR.instances.editorTemplateDetails.insertHtml('');
			$("#divCKEditor").hide();
			var templateCode = $("option:selected", this).val();
			if(!$_checkEmptyObject(templateCode)){
				dwrRestructuredLocalBodyService.getGovernmentOrderTemplate(parseInt(templateCode), {
					callback : populateGovernmentOrderTemplate,
					errorHandler : function (){
						customAlert("<spring:message code='Error.errfetchnggotemplate' htmlEscape='true'/>");
					},
					async : true
				});
			}
		});
	</c:if> */
    $('#actionExistingGO').click(function() {
    	
		selectExistingGovernmentOrder();
	});
    $('#actionNewGO').click(function() {
    	$("#checkExistingGovtOrder").val('N');
    	$("#OrderNo").removeAttr("readonly");
		$("#orderCode, #OrderNo").val("");
		$("#OrderDate").val("");
		/* $('#bOrderDate').datetimepicker('setStartDate', null);
		$('#bOrderDate').datetimepicker('setStartDate', '0'); */
		$("#EffectiveDate").val("");
		/* $('#bEffectiveDate').datetimepicker('setStartDate', null);
		$('#bEffectiveDate').datetimepicker('setStartDate', '0'); */
		$("#GazPubDate").val("");
		/* $('#bGazPubDate').datetimepicker('setStartDate', null);
		$('#bGazPubDate').datetimepicker('setStartDate', '0'); */
		 $("#elementTable1").append('<tr><td><input type="hidden" id="checkNewOrExist" /></td></tr>');
	    if($.browser.msie){
			$("#attachFile2").replaceWith($("#attachFile2").clone(true));
	    } else {
	    	$("#attachFile2").val('');
	    }
	    if($("#divExistGovtFileUpload")!=null)
	    	$("#divExistGovtFileUpload").hide();
		$("#divGazettePublicationUpload").show();
		setDefaultDates();
	});
	$('#fetchGODetails').click(function() {
		return buildGovernmentOrderDetails();
	});
	$('#goClose').click(function() {
	
		$('#divExistingOrderDeatils').modal('hide');
	});
});

/**
 * The {@code setDefaultDates} set default setting for 
 * government order details.
 */
var setDefaultDates = function (){
	 
	 $("#bOrderDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
		//alert((selected.date).format('d/m/Y'));
		<c:if test="${empty GOV_ORDER_ACTION}">
			$('#EffectiveDate').val((selected.date).format('d-m-Y'));	
		</c:if>
		 $('#bEffectiveDate').datetimepicker('setStartDate',(selected.date).format('d-m-Y'));
		$('#bGazPubDate').datetimepicker('setStartDate',(selected.date).format('d-m-Y')); 
		});
	 
	 $("#bEffectiveDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
			 $('#bOrderDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y')); 
			
	    });

	 <c:if test="${govtOrderConfig == 'govtOrderUpload'}">
	
	 $("#bGazPubDate").datetimepicker({
		 	format: 'dd-mm-yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
			 $('#bOrderDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));
			$('#bEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));	
			var parsedDate = Date.parse( selected.date );
				
				if( parsedDate > new Date() ){
					//$('#bOrderDate').datetimepicker('setEndDate', '0');
					//$('#bEffectiveDate').datetimepicker('setEndDate', '0');
				} 
		});
	</c:if> 
	 
}; 
 
/**
 * The {@code replaceTemplateVariables} used to dynamically replace template
 * variables with actual data.
 * @param templateDescription
 * @param array
 * @param isInput
 */ 
/* var replaceTemplateVariables = function (templateDescription, array, isInput){
	$.map( array, function( value, key ) {
		var elementVal = "";
		if(value != null){
			if(isInput){
				if(jQuery.type( $("#" + value).val() ) != "undefined"){
					elementVal = $("#" + value).val();
				}	
			}else{
				var eleId = null;
				if("localBodyLevelCodes" == value){
					eleId = "[id^=" + value + "]";
				} else {
					eleId = "#" + value.replace("," , ",#");
					if(!$_checkEmptyObject($(eleId).attr('multiple'))){
						$(eleId ).find("option").attr("selected", "selected");	
					}
				}
				elementVal = $(eleId).find('option:selected').map(function () {
			        return $(this).text();
			    }).get().join(',');
			}	
		}
		templateDescription = templateDescription.replace(key, elementVal);
	});
	return templateDescription;
}; */

/**
 * The {@code getDynamicTemplate} used to create dynamic arrays for 
 * variables mapped with elements objects.
 * @param templateDescription
 */
/* var getDynamicTemplate = function(templateDescription){
	var commonValriableArray = {
			"{NameoftheNewLocalGovtBody}" : "localBodyNameEnglish",  
			"{AliasNameoftheNewLocalGovtBody}" : "localBodyAliasEnglish",  
			"{OrderNo}" : "orderNo",
			"{OrderDate}" : "OrderDate",
			"{EffectiveDate}" : "EffectiveDate",
			"{PESAActImplemented(Yes/No)}" : "pesaActImpl"
	};	
	templateDescription = replaceTemplateVariables(templateDescription, commonValriableArray, true);
	if(_JS_LOCAL_BODY_CREATION_TYPE == _JS_URBAN_CONSTANT){
		var urbanVarArray = {
				"{LocalGovtType}" : "localBodyType",
				"{ContributingVillages}": null,
				"{ParentofNewLocalGovtBody}" : null
		}
		templateDescription = replaceTemplateVariables(templateDescription, urbanVarArray, false);
		if(_JS_IS_DISRTICT_LEVEL_LOCAL_BODY){
			templateDescription = replaceTemplateVariables(templateDescription, {"{ContributingDistricts}" : "coverageLBContributingDisttLevel,coverageLRContributingUnmappedDisttLevel"}, false);
		}else{
			templateDescription = replaceTemplateVariables(templateDescription, {"{ContributingSubDistricts}" : "coverageLBContributingDisttLevel,coverageLRContributingUnmappedDisttLevel"}, false);
		}
	} else {
		var panchayatVarArray = {
				"{LocalGovtType}" : "localBodyType",
				"{ContributingDistricts}" : "coverageLBContributingDisttLevel,coverageLRContributingUnmappedDisttLevel",
				"{ContributingSubDistricts}" : "coverageLBContributingSubdisttLevel,coverageLRContributingUnmappedIntermediateLevel",
				"{ContributingVillages}": "coverageLBContributingVillageLevel,coverageLRContributingUnmappedVillageLevel",
				"{ParentofNewLocalGovtBody}" : "localBodyLevelCodes"
		}
		templateDescription = replaceTemplateVariables(templateDescription, panchayatVarArray, false);
	}
	return templateDescription;
}; */
 
/**
 * The {@code populateGovernmentOrderTemplate} used to instanciate 
 * ckeditor template has been assigned to panchayat type.
 * @param lbAttribuetesObj
 */ 
/* var populateGovernmentOrderTemplate = function(lbAttribuetesObj) {
	if(!$_checkEmptyObject(lbAttribuetesObj.templateSource.templateDescription)){
		var templateDescription = lbAttribuetesObj.templateSource.templateDescription;
				
		CKEDITOR.instances['editorTemplateDetails'].setData(getDynamicTemplate(templateDescription));
		$("#divCKEditor").show();	
	}
}; */  
 
/**
 * The {@code selectExistingGovernmentOrder} used to display dialog box 
 * to show Government Order Details.
 */  
 var selectExistingGovernmentOrder = function (){
		/* $( "#divExistingOrderDeatils" ).dialog({
	    	title : "<spring:message code='Label.findexistingGo' htmlEscape='true'/>",
	    	resizable: false,
	      	width:900,
	      	height:700,
	      	modal: true
	    }); */
	    $('#divExistingOrderDeatils').modal('show');
	};

/**
 * The {@code resetAllMessage} used to reset all error messages 
 * starts with 'goErr' element ids.
 */  
var resetAllMessage = function () {
	$('[id^=goErr]').text('');
};

/**
 * The {@code buildGovernmentOrderDetails} used to build local government order details
 * in tabular format to dispaly and option to pick  individual
 * existing order no attach with new entity. 
 * starts with 'goErr' element ids.
 * @throws errorHandler (display error message) 
 */ 
var buildGovernmentOrderDetails = function () {
	resetAllMessage();
	$( '#tblGornmentOrderDetails > tbody').empty();
	
	var paramOrderNo  = $('#paramOrderNo').val();
	var paramFromDate = $('#dateParamFrom').val();
	var paramToDate   = $('#dateParamTo').val();
	if($_checkEmptyObject(paramOrderNo) && $_checkEmptyObject(paramFromDate) && $_checkEmptyObject(paramToDate)){
		$('#goErrOrderNo').text("<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>");
		$('#goErrFromDate').text("<spring:message code='error.select.FROMDATE' htmlEscape='true'/>");
		$('#goErrToDate').text("<spring:message code='error.select.TODATE' htmlEscape='true'/>");
		return false;	
	}
	if($_checkEmptyObject(paramOrderNo)){
		if($_checkEmptyObject(paramFromDate)){
			$('#goErrFromDate').text("<spring:message code='error.select.FROMDATE' htmlEscape='true'/>");
			return false;
		}
		if($_checkEmptyObject(paramToDate)){
			$('#goErrToDate').text("<spring:message code='error.select.TODATE' htmlEscape='true'/>");
			return false;
		}
	}	
	//DWR call to fetch Government Order Details 
	dwrRestructuredLocalBodyService.fetchExistingGovernmentOrder(paramOrderNo, paramFromDate , paramToDate, parseInt( '${stateCode}' ), {
		callback : function(result){
			$( '#tblGornmentOrderDetails' ).show();
			var templateTBODY = $("<tbody/>");
			jQuery.each(result, function(index, obj) {
				var templateTR = $("<tr/>");
				var templateTDOrderCode = $("<td/>");
				var templateRadio = $( "<input />" );
				templateRadio.attr("type", "radio");
				templateRadio.attr("name", "orderCode");
				$( templateRadio ).val(obj.orderCode);
				$( templateTDOrderCode ).append(templateRadio);
				$( templateRadio ).click(function() {
					$("#checkExistingGovtOrder").val('Y');
					setGovernmentOrderDetailsToForm(obj.orderCode,
													obj.orderNo,
													$.datepicker.formatDate('dd-mm-yy', obj.orderDate),
													$.datepicker.formatDate('dd-mm-yy', obj.effectiveDate),
													$.datepicker.formatDate('dd-mm-yy', obj.gazPubDate),
													obj.fileName,
													obj.fileTimestamp);
					$('#divExistingOrderDeatils').modal('hide');								
				});
				var templateTDOrderNo 		= $("<td/>");
				var templateTDOrderDate 	= $("<td/>");
				var templateTDEffectiveDate = $("<td/>");
				var templateTDGazPubDate 	= $("<td/>");
				var templateTDOrderPath 	= $("<td/>");
				
				$( templateTDOrderNo ).html(obj.orderNo);
				$( templateTDOrderDate ).text($.datepicker.formatDate('dd-mm-yy', obj.orderDate));
				$( templateTDEffectiveDate ).text($.datepicker.formatDate('dd-mm-yy', obj.effectiveDate));
				$( templateTDGazPubDate ).text($.datepicker.formatDate('dd-mm-yy', obj.gazPubDate));
				if(!$_checkEmptyObject(obj.orderPath)){
					$( templateTDOrderPath ).html("<a href='downloadLBGovernementOrder.htm?filename=" + obj.fileTimestamp + "&<csrf:token uri='downloadLBGovernementOrder.htm'/>'>" + obj.fileName + " </a>");
				}
				$( templateTR ).append( templateTDOrderCode ).append( templateTDOrderNo ).append( templateTDOrderDate );
				$( templateTR ).append( templateTDEffectiveDate ).append( templateTDGazPubDate ).append( templateTDOrderPath );
				$( templateTBODY ).append( templateTR );
			});
			$( '#tblGornmentOrderDetails' ).append( templateTBODY );
		},
		errorHandler : function (){
			customAlert("<spring:message code='Error.invalidrequest' htmlEscape='true'/>");
		},
		async : true
	});
};

/**
 * The {@code setGovernmentOrderDetailsToForm} used to set all attributes of government order
 * details to corresponding display pages.
 * @param orderCode 
 * @param orderNo 
 * @param orderDate 
 * @param effectiveDate 
 * @param gazPubDate
 */ 
var setGovernmentOrderDetailsToForm = function (orderCode, orderNo, orderDate, effectiveDate, gazPubDate, fileName, fileTimestamp) {
	$("#orderCode").val(orderCode).attr("readonly", "readonly");
	$("#OrderNo").val(orderNo).attr("readonly", "readonly");
	$("#OrderDate").val(orderDate).attr("readonly", "readonly");
	$("#EffectiveDate").val(effectiveDate).attr("readonly", "readonly");
	/* $("#OrderDate").datepicker( "destroy" );
	$("#EffectiveDate").datepicker( "destroy" ); */
	<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
		$("#GazPubDate").val(gazPubDate).attr("readonly", "readonly");
		//$("#GazPubDate").datepicker( "destroy" );
		$("#checkNewOrExist").remove();
		if($("#checkNewOrExist")!=null)
			$("#checkNewOrExist").remove();
		if($.browser.msie){
	    	$("#attachFile2").replaceWith($("#attachFile2").clone(true));
	    } else {
	    	$("#attachFile2").val('');
	    }
		<%-- Modified by pooja on 19-11-2015 for showing existing uploaded govt. order file --%>
		if ($("#divExistGovtFileUpload") != null) {
			$("#divfileUpload").remove();
			$("#divExistGovtFileUpload")
					.append(
							'<div id="divfileUpload"><label><spring:message code='Label.UGO' htmlEscape='true'/></label>&nbsp;&nbsp;<a href="downloadLBGovernementOrder.htm?filename='+fileTimestamp+'&<csrf:token uri='downloadLBGovernementOrder.htm'/>">'+fileName+'</a></div>');
			$("#divExistGovtFileUpload").show();
		}
		$("#divGazettePublicationUpload").hide();
	</c:if>
	};
	//-->

	var $_checkEmptyObject = function(obj) {
		if (jQuery.type(obj) === "undefined"
				|| (obj == null || $.trim(obj).length == 0)) {
			return true;
		}
		return false;
	};
</script>


  <!-- Modal -->
  <div class="modal fade" id="divExistingOrderDeatils" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Existing Local Government Order</h4>
        </div>
        <div class="modal-body">
                <div class="box-header subheading">
                    <h4 >Search By Government Order No</h4>
                </div>	
                <div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.GOvordernumber' htmlEscape='true'/> </label>
					<div class="col-sm-6">
		    		     <input type="text" id="paramOrderNo"  maxlength="200" class="form-control"/>
							    		<span class="errormessage" id="goErrOrderNo"></span>
		    		</div>
				</div>
				<div class="box-header subheading">
                    <h4 >Search By Government Order Date</h4>
                </div>	
                <div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.FROMDATE' htmlEscape='true'/>  </label>
					<div class="col-sm-6">
		    		   <div class="input-group date datepicker" id="bdateParamFrom">
			                        		<input type="text" id="dateParamFrom" readonly="readonly" class="form-control"/>
			                      <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
							  </div> 
							    		<span class="errormessage" id="goErrFromDate"></span>
		    		</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.TODATE' htmlEscape='true'/> </label>
					<div class="col-sm-6">
		    		      <div class="input-group date datepicker" id="bdateParamTo">
			          <input type="text" id="dateParamTo" readonly="readonly" class="form-control"/><span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
						  </div>
						 <span class="errormessage" id="goErrToDate"></span>
		    		</div>
				</div>
				
				
	   	<table id="tblGornmentOrderDetails" class="table table-bordered table-hover" cellspacing="0" width="100%" style="display:none">
												<thead>
													<tr>
														<th><!-- Header for Radio Button --></th>
														<th><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></th>
														<th><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></th>
														<th><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></th>
														<th><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></th>
														<th><spring:message code='Label.UGO' htmlEscape='true'/></th>
													</tr>
												</thead>
											</table>
	   
        </div>
        <div class="modal-footer">
          <button  class="btn btn-info" type="button" id="fetchGODetails" value="" > Fetch Gornment Order</button>
          <button type="button" class="btn btn-default" id="goClose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
</div>
  
  
  
  
  
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
				<div class="form-group">
				<label class="col-sm-1"></label>
				<div class="col-sm-6" style="display:none;">
				   <button type="button" class="btn btn-primary "  id="actionExistingGO" data-toggle="modal" data-target="#divExistingOrderDeatils" ><spring:message code='Label.selectexistinggo' htmlEscape='true'/> </button>
					 <button type="button" class="btn btn-primary "  id="actionNewGO" value="<spring:message code='Label.selectnewgo' htmlEscape='true'/>" ><spring:message code='Label.selectnewgo' htmlEscape='true'/> </button>
					<input type="hidden" name="checkExistingGovtOrder" id="checkExistingGovtOrder" value="N"/>
					<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
				</div>
		     </div>
			</c:if>
		

<!-- Block for Government Order Details Ends Here-->	

 <c:if test="${(not empty orderCode) && (orderCode > 0)}"> 
	<script type='text/javascript'>
		$(window).load(function () {
		
			$("#OrderNo").attr("readonly", "readonly");
			//$("#OrderDate").datepicker( "destroy" );
			$("#OrderDate").attr("readonly","readonly");
			//$("#EffectiveDate").datepicker( "destroy" );
			$("#EffectiveDate").attr("readonly","readonly");
			//$("#GazPubDate").datepicker( "destroy" );
			$("#GazPubDate").attr("readonly","readonly");
		}); 
	</script>
</c:if>

