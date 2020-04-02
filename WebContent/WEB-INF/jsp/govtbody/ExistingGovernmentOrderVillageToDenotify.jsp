<% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/govtOrderService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>

<style>
	/* This is the style for the trigger icon. The margin-bottom value causes the icon to shift down to center it. */
	.ui-datepicker-trigger {
		margin-left:1px;
		margin-top: 8px;
		margin-bottom: -3px;
	}
	.textHead{color: #3B5998;}
	</style>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
<link href="<%=contextPath%>/external/jqueryCustom/css/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
<script type="text/javascript">
<!--
/**
 * The {@code ready} initialized once page started excuting 
 * and invoke all on load events.
 */ 
$(document).ready(function() {
	$('#dateParamFrom').datepicker({
		numberOfMonths: 2,
		onSelect: function(selected) {
			$('#dateParamTo').datepicker("option","minDate", selected);
		},
		dateFormat: 'dd/mm/yy',
		showOn: 'both',
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	});
    $('#dateParamTo').datepicker({
        numberOfMonths: 2,
        onSelect: function(selected) {
           $('#dateParamFrom').datepicker("option","maxDate", selected);
        },
        dateFormat: 'dd/mm/yy',
		showOn: 'both',
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
    }); 
    
   <%--  $("[id^=formDate]").datepicker({
		dateFormat: 'dd/mm/yy',
		showOn: 'both',
		onSelect: function(selected) {
			var elementId = $(this).attr('id');
			if("formDateOrderDate" == elementId){
				$('#formDateEffectiveDate').val(selected);
				$('#formDateEffectiveDate').datepicker("option","minDate", selected);
				<c:if test="${isGovernmentOrderUpload}">
					$("#formDateGazPubDate").datepicker("option","minDate", selected);
				</c:if>
			}
			if("formDateEffectiveDate"  == elementId){
				$('#formDateOrderDate').datepicker("option","maxDate", selected);
				<c:if test="${isGovernmentOrderUpload}">
					$("#formDateGazPubDate").datepicker("option","minDate", selected);
				</c:if>
			}
			<c:if test="${isGovernmentOrderUpload}">
				if("formDateGazPubDate"  == elementId){
					$('#formDateOrderDate').datepicker("option","maxDate", selected);
					$("#formDateEffectiveDate").datepicker("option","maxDate", selected);
				}	
			</c:if>
		},
		buttonImage: "<%=contextPath%>/images/calender.gif",
		buttonImageOnly: true,
	}); --%>
    
    $('#actionExistingGO').click(function() {
    	$("#orderCode").val("").attr("readonly", "readonly");
    	$("#OrderNo").val("").attr("readonly", "readonly");
    	$("#OrderDate").val("").attr("readonly", "readonly");
    	$("#EffectiveDate").val("").attr("readonly", "readonly");
    	$("#GazPubDate").val("").attr("readonly", "readonly");
    	$("#gazPubDatecr").val("").attr("readonly", "readonly");
    	
    	//$("#attachFile2").val("");
    	$("#divGazettePublicationUpload").hide(); 
    	//$("#OrderDate").attr("disabled", "true");
    	//$("#EffectiveDate").attr("disabled", "true");
    	//$("#GazPubDate").attr("disabled", "true");
    	//$("#gazPubDatecr").attr("disabled", "true");
    	$("#isExistGovt").val("Y");
    	
    	selectExistingGovernmentOrder();
	});
    $('#actionNonExistingGO').click(function() {
    	$("#orderCode").val("");
    	$("#OrderNo").val("").removeAttr("readonly");;
    	$("#OrderDate").val("");
    	$("#EffectiveDate").val("");
    	$("#GazPubDate").val("");
    	$("#gazPubDatecr").val("");
    	
    	$("#attachFile2").val("");
    	$("#divGazettePublicationUpload").show();
    	//$("#orderCode").removeAttr("disabled");
    	//$("#OrderNo").removeAttr("disabled");
    	//$("#OrderDate").removeAttr("disabled");
    	//$("#EffectiveDate").removeAttr("disabled");
    	//$("#GazPubDate").removeAttr("disabled");
    	//$("#gazPubDatecr").removeAttr("disabled");
    	//$("#attachFile2").removeAttr("disabled");
    	$("#isExistGovt").val("N");
    });
	$('#fetchGODetails').click(function() {
		return buildGovernmentOrderDetails();
	});
	$('#goClose').click(function() {
		$('#divExistingOrderDeatils').dialog('close');
	});
});
 
/**
 * The {@code selectExistingGovernmentOrder} used to display dialog box 
 * to show Government Order Details.
 */  
var selectExistingGovernmentOrder = function (){
	$( "#divExistingOrderDeatils" ).dialog({
    	title : "Find Existing Government Order(s)",
    	resizable: false,
      	width:900,
      	height:700,
      	modal: true
    });
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
	if(jQuery.isEmptyObject(paramOrderNo) && jQuery.isEmptyObject(paramFromDate) && jQuery.isEmptyObject(paramToDate)){
		$('#goErrOrderNo').text("Please Enter Order No.");
		$('#goErrFromDate').text("Please Select From Date.");
		$('#goErrToDate').text("Please Select To Date.");
		return false;	
	}
	if(jQuery.isEmptyObject(paramOrderNo)){
		if(jQuery.isEmptyObject(paramFromDate)){
			$('#goErrFromDate').text("Please Select From Date.");
			return false;
		}
		if(jQuery.isEmptyObject(paramToDate)){
			$('#goErrToDate').text("Please Select To Date.");
			return false;
		}
	}	
	//DWR call to fetch Government Order Details 
	govtOrderService.fetchExistingGovernmentOrder(paramOrderNo, paramFromDate , paramToDate, {
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
					setGovernmentOrderDetailsToForm(obj.orderCode,
													obj.orderNo,
													$.datepicker.formatDate('dd-mm-yy', obj.orderDate),
													$.datepicker.formatDate('dd-mm-yy', obj.effectiveDate),
													$.datepicker.formatDate('dd-mm-yy', obj.gazPubDate));
					$('#divExistingOrderDeatils').dialog('close');							
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
				if(!jQuery.isEmptyObject(obj.orderPath)){
					$( templateTDOrderPath ).html("<a id='downloadFile' href='#'>" + obj.orderPath + "</a>");
				}
				$( templateTR ).append( templateTDOrderCode ).append( templateTDOrderNo ).append( templateTDOrderDate );
				$( templateTR ).append( templateTDEffectiveDate ).append( templateTDGazPubDate ).append( templateTDOrderPath );
				$( templateTBODY ).append( templateTR );
			});
			$( '#tblGornmentOrderDetails' ).append( templateTBODY );
			
			
		    $('#downloadFile').click(function(){
		    	alert(this);
		    /* 	$.fileDownload('restuctureCreatePRILocalBody.htm', {
		            preparingMessageHtml: "We are preparing your report, please wait...",
		            failMessageHtml: "There was a problem generating your report, please try again."
		        });
		        return false; //this is critical to stop the click event which will trigger a normal file download! */
		        

		    	$.fileDownload('C:/Users/Public/Pictures/Sample Pictures/Desert.jpg', {
		    	    successCallback: function () {
		    	        customAlert('You just got a file download dialog or ribbon for this URL ');
		    	    },
		    	    failCallback: function () {
		    	    	customAlert('Your file download just failed for this URL:\r\n' +
		    	                	'Here was the resulting error HTML: \r\n');
		    	    }
		    	});

			});
		},
		errorHandler : function (){
			customAlert("Invalid Request Initiated, Error While Fetching Government Order Details.");
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
var setGovernmentOrderDetailsToForm = function (orderCode, orderNo, orderDate, effectiveDate, gazPubDate) {
	
	$("#orderCode").val(orderCode).attr("readonly", "readonly");
	$("#OrderNo").val(orderNo).attr("readonly", "readonly");
	$("#OrderDate").val(orderDate).attr("readonly", "readonly");
	//$("#OrderDate").attr("disabled", "true");
	$("#EffectiveDate").val(effectiveDate).attr("readonly", "readonly");
	//$("#EffectiveDate").attr("disabled", "true");
	$("#GazPubDate").val(gazPubDate).attr("readonly", "readonly");
	//$("#GazPubDate").attr("disabled", "true");
	$("#gazPubDatecr").val(gazPubDate).attr("readonly", "readonly");
	//$("#gazPubDatecr").attr("disabled", "true");
	
	
		/*  $("#formDateGazPubDate").val(gazPubDate).attr("readonly", "readonly");
		$("#gazettePublicationUpload").replaceWith($("#gazettePublicationUpload").clone()); 
 */
};
//-->
</script>
<!-- Select From Existing Local Government Order Started -->
<div id="divExistingOrderDeatils" class="form_stylings" style="display: none;">
	<ul class="form_body blocklist" >
		<li>
			<label class="inline">
				Order Number
		    </label>
		    <input type="text" id="paramOrderNo" />
		    <span class="errormessage" id="goErrOrderNo"></span>
		</li>
		<li>
			<label class="inline">
				From Date
		    </label>
		    <input type="text" id="dateParamFrom" readonly="readonly"/>
		    <span class="errormessage" id="goErrFromDate"></span>
		</li>
		<li>
			<label class="inline">
				To Date
		    </label>
		    <input type="text" id="dateParamTo" readonly="readonly"/>
		    <span class="errormessage" id="goErrToDate"></span>
		</li>
		<li>
		    <label class="inline">&nbsp;</label>
			<input class="bttn" type="button" id="fetchGODetails" value="Fetch Government Order" />
			<input class="bttn" type="button" id="goClose" value="Close" />	
		</li>
	</ul>	
	<br/>
	<table id="tblGornmentOrderDetails" class="data_grid" width="100%" style="display: none;">
		<thead>
			<tr>
				<th><!-- Header for Radio Button --></th>
				<th><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></th>
				<th><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></th>
				<th><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></th>
				<th><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></th>
				<th>Uploaded Government Order</th>
			</tr>
		</thead>
	</table>
</div>
<!-- Select From Existing Local Government Order Ends -->

<!-- Block for Government Order Details Started -->	
<div class="form_block">
	<div class="col_1">
		<ul class="listing">
				<li>
					<input type="radio" class="bttn"  name="checkExistingGovtOrder" id="actionExistingGO" value="Y"/><spring:message code="Label.SelExistGovtOrder" />
					<input type="radio" class="bttn"  name="checkExistingGovtOrder" id="actionNonExistingGO" value="N" checked="checked" /><spring:message code="Label.SelNONExistGovtOrder"/>
				</li>
			<li>
			<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
			<!-- <li>
						<div id="divGazettePublicationUpload">
						
				   			<label>	
							<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
							( Allowed File Extensions[gif,jpg,pdf,png,jpeg] Max File Size[5 MB] )
							<span class="mandate">*</span>								
							</label>
							<form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file"/>
							<form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="error"/>
						</div>	
					</li> -->
		</ul>
	</div>
</div>
<!-- Block for Government Order Details Ends Here-->	