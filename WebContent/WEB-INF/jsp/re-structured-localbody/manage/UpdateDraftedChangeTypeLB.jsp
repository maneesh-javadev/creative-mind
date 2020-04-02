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
		  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,//Allow '(' and ')' brackets in Name of the Localbody (In English) @Pooja @date 08-10-15
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
 		if( isParseJson("${not modifyProcess}") ){
		    $("#gazettePublicationUpload" ).rules( "add", {
		 		required : true, 
		 		fileUploadValidate : true,
		 		messages: { 
		 			required: "<spring:message code='GOVERMENTORDER.REQUIRED' htmlEscape='true'/>",
		 			fileUploadValidate: "<spring:message code='Error.invalidgofile' htmlEscape='true'/>"
		 		}
			});
		} else {
 				$("#gazettePublicationUpload" ).rules( "add", {
	 		 		fileUploadValidate : true,
	 		 		messages: { 
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
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.MODIFYGOVTLOCALBODY.CT" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
			<%--//these links are not working 	<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="publishModifyTypeLocalBody.htm" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="publishModifyTypeLocalBody.htm"/>" />
					<form:hidden path="processAction"/>
					<form:hidden path="localBodyCreationType"/>
					<form:hidden path="localBodyCode"/>
					<form:hidden path="id" id="paramLBCode"/>
					
					
					<!-- Block for Show General Details of Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<c:if test="${previousLBTableObject.isdisturbed}">
										<li>
											<label class="inline">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										    <label >
										    		<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </label>
										</li>
									</c:if>
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${previousLBTableObject.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${previousLBTableObject.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${previousLBTableObject.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${previousLBTableObject.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${previousLBTableObject.stateSpecificCode}" escapeXml="true"></c:out>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Show General Details of Local Government Body Ends. -->		
				
				
					<!-- Modify Parent of Local Body Started-->
					 <div class="form_block">
						<div class="col_1">
							<h4><spring:message code="Label.MODIFYURBANLOCALBODYTYPE" htmlEscape="true"></spring:message></h4>
							<ul class="form_body">
								<li>
									<label>
										<spring:message	code="Label.SELECTEDTYPEOFLOCALBODY" htmlEscape="true"></spring:message>
									</label>
									<input type="text" id="localBodyTypeName" value="${localBodyTypeName}" disabled="disabled"/>
								</li>
								<li>
									<label>
										<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:input path="localBodyNameEnglish" id="localBodyNameEnglish"/>
									<span class="errormessage" id="errLBNameEnglish"></span>
									<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="error"/>
								</li>
								<li>
									<label>
										<spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
										<span class="mandate">*</span>
									</label>
									<form:select path="localBodyTypeCode" id="selLocalBodyType">
										<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
										<c:forEach var="lbUrbanTypes" items="${urbanLocalBodyTypes}">
											<form:option value="${lbUrbanTypes.localBodyTypeCode}">
												<c:out value="${lbUrbanTypes.name}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>
									<form:errors htmlEscape="true" path="localBodyTypeCode" cssClass="error"/>									
								</li>
							</ul>
						</div>
					</div>  
					<br/>
					<!-- General Details of Local Body Ends Here-->
					
					<!-- Block for Government Order Details Started -->
					<%@include file="../ExistingGovernmentOrder.jsp"%>	
					<br/>
					<!-- Block for Government Order Details Ends Here-->	
					<input class="bttn" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/>
					<input class="bttn" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
					<input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>