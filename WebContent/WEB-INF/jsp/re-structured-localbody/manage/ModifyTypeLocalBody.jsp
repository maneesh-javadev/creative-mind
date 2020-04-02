<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>	
<script type="text/javascript">
var validationForm = function (){
	$("#localBodyNameEnglish" ).rules( "add", {
		  required: true,
		  maxlength: 200,
		  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" , //Allow '(' and ')' brackets in Name of the Localbody (In English) @Pooja @date 08-10-15
		  messages: {
		    required : "<spring:message code='error.blank.localBodyNameInEn' htmlEscape='true'/>",
		    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
		    regex    : "<spring:message code='Error.invalidchar' htmlEscape='true'/>"
		  }
	});
	$("#selLocalBodyType" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.select.LBTYPE' htmlEscape='true'/>" }
	});
 	$("#orderNo" ).rules( "add", {
 		required : true, 
 		maxlength: 60,
 		regex: "[\#\%&\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]" ,
 		messages: { 
 			required: "<spring:message code='ORDERNUMBER.REQUIRED' htmlEscape='true'/>",
 			maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
 			regex    : "<spring:message code='Error.invalidegovordrno' htmlEscape='true'/>"
 		}
	});
 	$("#formDateOrderDate" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.required.ORDERDATE' htmlEscape='true'/>" }
	});
 	$("#formDateEffectiveDate" ).rules( "add", {
 		required : true,  
 		messages: { required: "<spring:message code='error.required.EFFECTIVEDATE' htmlEscape='true'/>" }
	});
 	<c:if test="${isGovernmentOrderUpload}">
 		if($_checkEmptyObject($("#orderCode").val())){
		    $("#gazettePublicationUpload" ).rules( "add", {
		 		required : true, 
		 		fileUploadValidate : true,
		 		messages: { 
		 			required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>",
		 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
		 		}
			});
		}
 	</c:if>
};

var customSubmitValidation = function (form){
	dwrRestructuredLocalBodyService.checkLocalBodyNameExist($('#localBodyNameEnglish').val(), $('#selLocalBodyType').val(), 0, parseInt(_JS_STATE_CODE), $('#paramLBCode').val(), {
			callback : function(result) {
				if(_JS_SUCCESS_CONSTANT == result){
					disableAllButtons();
					form.submit();
				} else {
				$( "#errLBNameEnglish" ).text( result );//"<spring:message code='error.exist.lgd_LBNameInEn' htmlEscape='true'/>");
				$( "#localBodyNameEnglish" ).focus();
				return false;
			}	
		},
		async : true
	});
};
</script>
</head>
<body class="dt-example">

<!-- Main Form Styling starts -->

 <section class="content">
	<div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		       <h3 class="box-title"><spring:message code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->

	
				<form:form class="form-horizontal" action="publishModifyTypeLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="publishModifyTypeLocalBody.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode" id="paramLBCode"/>
					<div class="box-body">
					<table class="table table-bordered table-hover">
					<c:if test="${localBodyForm.isdisturbed}">	
						<tr>
							  <td><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message></td>
							  <td>
									 <img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
							  </td>
						</tr>
					</c:if>
					<tr>
						  <td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></td>
						  <td>
								<c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out>
						  </td>
					</tr>
					<tr>
						  <td><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></td>
						  <td>
								<c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out>
						  </td>
					</tr>
					<tr>
						  <td><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></td>
						  <td>
								<c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out>
						  </td>
					</tr>
					<tr>
						  <td><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></td>
						  <td>
								<c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out>
						  </td>
					</tr>	
					</table>
					</div>
					<br/>
					<!-- Block for Show General Details of Local Government Body Ends. -->		
				
				
					<!-- Modify Parent of Local Body Started-->
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.MODIFYURBANLOCALBODYTYPE" htmlEscape="true"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    
					<div class="form-group">
							  <label  class="col-sm-3 control-label" for="localBodyTypeName">
							  		<spring:message	code="Label.SELECTEDTYPEOFLOCALBODY" htmlEscape="true"></spring:message>
							  </label>
							  <div class="col-sm-6">
								 <input type="text" id="localBodyTypeName" value="${localBodyTypeName}" disabled="disabled" class="form-control" />
							  </div>
					</div>
					
					<div class="form-group">
						  <label  class="col-sm-3 control-label" for="localBodyTypeName"> 
						  		<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
						  		<span class="mandatory">*</span>
						  </label>
						  <div class="col-sm-6">
							 <form:input path="localBodyNameEnglish" id="localBodyNameEnglish" class="form-control" />
							<span class="errormessage" id="errLBNameEnglish"></span>
							<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="error"/>
						  </div>
					</div>
					
					<div class="form-group">
						  <label  class="col-sm-3 control-label" for="localBodyTypeName"> 
						  		<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
						  		<span class="mandatory">*</span>
						  </label>
						  <div class="col-sm-6">
							<form:select path="localBodyTypeCode" id="selLocalBodyType" class="form-control">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<c:forEach var="lbUrbanTypes" items="${urbanLocalBodyTypes}">
											<form:option value="${lbUrbanTypes.localBodyTypeCode}">
												<c:out value="${lbUrbanTypes.name}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
							</form:select>
							<form:errors htmlEscape="true" path="localBodyTypeCode" cssClass="error"/>		
						  </div>
					</div>
					<!-- General Details of Local Body Ends Here-->
					
					<!-- Block for Government Order Details Started -->
					<div style="padding-left: 30px;"><%@include file="../ExistingGovernmentOrderCpy.jsp"%>	</div>
					<br/>
					
					<!-- Block for Government Order Details Ends Here-->
				<div class="box-footer">
     				<div class="col-sm-offset-2 col-sm-10">
      			 		<div class="pull-right">	
							<div style="display: none" id="drafthide"><input class="btn btn-success" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/> 
							</div>
							<input class="btn btn-success" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
							<input class="btn btn-danger" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
						</div>
					</div>
				</div>
				</form:form>
			</div>
		</section>
		<!-- Page Content ends -->
	</div>
	</section>
	<!-- Main Form Styling ends -->
</body>
</html>