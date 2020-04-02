<% String contextPath = request.getContextPath();%>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/ckeditor/ckeditor_components.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resource/dashboard-resource/dist/js/date.format.min.js"></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>
<style>
	/* This is the style for the trigger icon. The margin-bottom value causes the icon to shift down to center it. */
	.ui-datepicker-trigger {
		margin-left:1px;
		margin-top: 8px;
		margin-bottom: -3px;
	}
	.textHead{color: #3B5998;}
</style>
<script type="text/javascript">

/**
 * The {@code ready} initialized once page started excuting 
 * and invoke all on load events.
 */ 
$(document).ready(function() {
	
//setDefaultDates();
	
});


 /**
  * The {@code $_checkEmptyObject} used to check object / element  
  * is empty or undefined.
  */
 var $_checkEmptyObject = function(obj) {
 	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
 		return true;
 	}
 	return false;
 };

 
var setDefaultDates = function (_js_previous_ED){
	
	
	 $("#bformDateOrderDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
			endDate: '+0d',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			EndDate:'0',
		}).on('changeDate', function(selected){
		//alert((selected.date).format('d/m/Y'));
		var momentDate = moment(_js_previous_ED, 'DD-MM-YYYY').toDate();//moment(_js_previous_ED, 'DD/MM/YYYY').toDate();
			<c:if test="${empty GOV_ORDER_ACTION}">
			if(!$_checkEmptyObject(_js_previous_ED)){
				if(Date.parse(_js_previous_ED ) <Date.parse( selected.date )){
					_js_previous_ED = selected.date;
					$('#formDateEffectiveDate').val((selected.date).format('d-m-Y'));	
				}
			}else{
				$('#formDateEffectiveDate').val((selected.date).format('d-m-Y'));
			}
		</c:if>
		
		if(!$_checkEmptyObject(_js_previous_ED)){
			
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', momentDate );
		}else{
			
		$('#bformDateEffectiveDate').datetimepicker('setStartDate', (selected.date).format('d-m-Y'));
		}
		$('#bformDateGazPubDate').datetimepicker('setStartDate',(selected.date).format('d-m-Y'));
		

	    });
	 
	 $("#bformDateEffectiveDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
			StartDate:_js_previous_ED,
			EndDate:'0',
		}).on('changeDate', function(selected){
		
			$('#bformDateOrderDate').datetimepicker('setEndDate',(selected.date).format('d-m-Y'));
			
	    });

	 <c:if test="${isGovernmentOrderUpload}">
		 $("#bformDateGazPubDate").datetimepicker({
			format: 'dd-mm-yyyy',
			startView : 'month',
	        autoclose: true,
			minView : 'month',
			pickerPosition : "bottom-left",
		}).on('changeDate', function(selected){
				var parsedDate = Date.parse( selected.date );
				$('#bformDateOrderDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));
				if(!$_checkEmptyObject(_js_previous_ED)){
					if(Date.parse( _js_previous_ED ) < parsedDate){	
						$('#bformDateEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));
					}
				} else {
				$('#bformDateEffectiveDate').datetimepicker('setEndDate', (selected.date).format('d-m-Y'));
				}
				if( parsedDate > new Date() ){
					//$('#bformDateOrderDate').datetimepicker('setEndDate', '0');
					//$('#bformDateEffectiveDate').datetimepicker('setEndDate', '0');
				}
		});
	</c:if> 
	 
	
}; 
 
 
var resetAllMessage = function () {
	$('[id^=goErr]').text('');
};



//-->
</script>



<!-- Block for Government Order Details Started -->	
	<div class="box-header subheading">
        <h4><spring:message htmlEscape="true" code="Label.GOVTORDERDETAILS"></spring:message><c:out value="(Optionals)" /></h4>
    </div>
   
    <div class="form-group">
    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></label>
      	<div class="col-sm-6">
      		<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
			<form:input path="orderNo" id="orderNo" maxlength="60" htmlEscape="true" class="form-control"/>
			<br/>
			<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
			<span class="errormessage" id="errorderNo"></span>
			<input type="hidden" id="checkNewOrExistGovtOrder"/>
      	</div>
    </div>
	<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></label>
      	<div class="col-sm-6">
      	
      		<div class="input-group date datepicker" id="bformDateOrderDate">
      			<form:input path="orderDate" id="formDateOrderDate" readonly="true" class="form-control"/>
      			<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      		</div>
			
			<form:errors htmlEscape="true" path="orderDate" cssClass="error"/>
			<span class="errormessage" id="errformDateOrderDate"></span>
			
      	</div>
    </div><br/>
    <div class="form-group">
    	<label class="col-sm-3 control-label"><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
					      </label>
      	<div class="col-sm-6">

      	 <div class="input-group date datepicker" id="bformDateEffectiveDate">
      		<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="true" class="form-control"/>
      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      	</div>
      		
						<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> :
						 <span id="divParamEffectiveDate"></span> )</strong>
					
			<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
			<br/>
			<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>
			<span class="errormessage" id="errformDateEffectiveDate"></span>
      	</div>
    </div>
    <c:choose>
		<c:when test="${govtOrderConfig eq 'govtOrderUpload'}">
			<div class="form-group">
		    	<label class="col-sm-3 control-label"><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>	</label>
		      	<div class="col-sm-6">
		      	 <div class="input-group date datepicker" id="bformDateGazPubDate">
		      		<form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true" class="form-control"/>
		      		<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
		      		</div>
					<br/>
					<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
		      	</div>
		    </div>
		    <c:if test="${(empty localGovtBodyForm.orderCode) }">
		    	<c:set var="fileName" value=""/>
				<c:if test="${not empty localBodyForm.orderPath}">
					<c:set var="substrng" value="${fn:substring(localBodyForm.orderPath, fn:indexOf(localBodyForm.orderPath, '_'), fn:indexOf(localBodyForm.orderPath, '.'))}" />
					<c:set var="fileName" value="${fn:replace(localBodyForm.orderPath, substrng, '')}" />
				</c:if>
				<a id="attachedUploadedFile" href="downloadLBGovernementOrder.htm?filename=${localBodyForm.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
					<c:out value="${fileName}"/>
				</a>
				<form:hidden path="orderPath"/>
				<br/><br/>
				</c:if>
				<div class="form-group" id="divGazettePublicationUpload" <c:if test="${not empty localGovtBodyForm.orderCode}">style="display: none;"</c:if>>
			      	<label class="col-sm-3 control-label">
			      		<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
						<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
							
			      	</label>
			      	<div class="col-sm-6">
			      		<form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file" class="form-control"/>
						<form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="error"/>
						<span class="errormessage" id="errgazettePublicationUpload"></span>
			      	</div>
		    	</div>
		    
		</c:when>
		<c:otherwise>
			<%-- <div class="form-group">
		      	<label class="col-sm-3 control-label">
		      		<spring:message htmlEscape="true" code="Label.SELGOT"></spring:message>
						
		      	</label>
		      	<div class="col-sm-6">
		      		<form:select path="templateId" id="templateId" class="form-control">
						<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
						<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
					</form:select>
		      	</div>
	    	</div>
	    	<div class="form-group" id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display: none;"</c:if>>
		      	<div class="col-sm-12">
		      		<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" class="form-control"/>
		      	</div>
	    	</div> --%>
		</c:otherwise>
	</c:choose>
	
				    
<!-- Block for Government Order Details Ends Here-->	

