<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>
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
 	$("#localBodyNameLocal" ).rules( "add", {
 		  maxlength: 100,
		  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,//Allow '(' and ')' brackets in Name of Localbody (In Local) @Pooja @date 08-10-15
		  messages: {
		    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
		    regex    : "<spring:message code='Error.localbodylocal' htmlEscape='true'/>"
		  }
	});
 	$("#localBodyAliasEnglish" ).rules( "add", {
 		  maxlength: 50,
		  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\(\)\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,
		  messages: {
		    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
		    regex    : "<spring:message code='Error.invalidchar' htmlEscape='true'/>"
		  }
	});
 	$("#localBodyAliasLocal" ).rules( "add", {
 		  maxlength: 50,
		  regex: "[#%&\/\~\!\@\$\^\*\_\+\`\=\\(\)\{\}\\[\\]\|\\\\:\,\;\'\"\<\>\?]" ,
		  messages: {
		    maxlength: jQuery.format("<spring:message code='Error.Sizevalid' htmlEscape='true'/>"),
		    regex    : "<spring:message code='Error.lbaliaslocal' htmlEscape='true'/>"
		  }
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
	dwrRestructuredLocalBodyService.checkLocalBodyNameExist($('#localBodyNameEnglish').val(), '${localBodyForm.localBodyTypeCode}', '${localBodyForm.parentLocalBodyCode}', 
															parseInt(_JS_STATE_CODE), $('#paramLBCode').val(), {
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
	<section class="content">
		<div class="row">
       		<section class="col-lg-12 ">
           		<div class="box">
   					<div class="box-header with-border">
                		<h3 class="box-title"><spring:message htmlEscape="true" code="Label.CORRECTGOVTLOCALBODY.RENAME"></spring:message></h3>
	                </div>
	                <div class="box-body">
	                
	                <form:form action="publishRenameLocalBody.htm" class="form-horizontal" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
						<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="publishRenameLocalBody.htm"/>" />
						<form:hidden path="processAction"/>
						<form:hidden path="localBodyCreationType"/>
						<form:hidden path="localBodyCode" id="paramLBCode"/>
						<form:hidden path="localBodyTypeCode"/>
						<form:hidden path="parentLocalBodyCode"/>
					
						<div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
                    	</div> 
                    	
                    	<table  class="table table-bordered table-hover" cellspacing="0" width="100%">
		                	    <tbody>					    
									<c:if test="${localBodyTableObj.isdisturbed}">
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.DISTURBEDSTATE"></spring:message></td>
										<td><img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" /></td>
									</tr>
									
									</c:if>
									<tr>
									 	<td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message></td>
										<td><c:out value="${localBodyForm.localBodyNameEnglish}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
									 	<td><spring:message htmlEscape="true"  code="Label.LOCALBODYNAMELOCAL"></spring:message></td>
										<td><c:out value="${localBodyForm.localBodyNameLocal}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LOCALBODYALIASENGLISH"></spring:message></td>
										<td><c:out value="${localBodyForm.localBodyAliasEnglish}" escapeXml="true"></c:out></td>
									</tr>
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.LOCALBODYALIASLOCAL"></spring:message></td>
										<td><c:out value="${localBodyForm.localBodyAliasLocal}" escapeXml="true"></c:out></td>
									</tr>
		    						 <tr>
	    								<td><spring:message htmlEscape="true"  code="Label.list.previous.names"></spring:message></td>
			    						<td>
			    						<a href="#" id="showPreviousNames"  onclick="callPreviousNamesView('${localBodyForm.localBodyCode}')"> 
												<spring:message htmlEscape="true"  code="Label.view.previous.names"></spring:message>
											</a>
			    						</td>
			    					</tr>
				   				</tbody>	
		                	</table>
                    	
                    	
                    		<div class="modal fade" id="myModal1">
	  							<div class="modal-dialog" role="document" style="width:950px;">
	    							<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">Local Body</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										</div>
									      <div class="modal-body">
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>S.No.</th>
														<th><spring:message code='LOCALBODYNAMEENGLISH' htmlEscape='true'/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="pName" items="${previousNames}" varStatus="counter">
														<tr>	
															<td><c:out value="${counter.count}" escapeXml="true"/></td>
															<td><c:out value="${pName}" escapeXml="true"/></td>
														</tr>	
													</c:forEach>
												</tbody>
											</table>
									      </div>
									      <div class="modal-footer">
									        <div class="col-sm-offset-2 col-sm-10">
								            	<div class="pull-right">
												<button class="btn btn-dengar" type="button" data-dismiss="modal" id="goClose">Close</button>	
								            	</div>
								            </div>
									      </div>
						   			 </div>
						  		</div>
							</div>
                    	
                    	
                    	<div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.MODIFYLOCALBODYNAME"></spring:message></h4>
                    	</div> 
                    	<div class="form-group">
                 			<label for="districtAliasInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"></spring:message><span class="mandate">*</span></label>
	                      	<div class="col-sm-6">
	                      		<form:input path="localBodyNameEnglish" class="form-control" id="localBodyNameEnglish" maxlength="200" htmlEscape="true" />
								<span class="errormsg" id="errLBNameEnglish"></span>
								<form:errors htmlEscape="true" path="localBodyNameEnglish" cssClass="error"/>
	                      	</div>
                    	</div>
                    	<div class="form-group">
                 			<label for="districtAliasInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></label>
	                      	<div class="col-sm-6">
	                      		<form:input path="localBodyNameLocal" class="form-control" id="localBodyNameLocal" maxlength="100" htmlEscape="true"/>	
								<form:errors htmlEscape="true" path="localBodyNameLocal" cssClass="error"/>
	                      	</div>
                    	</div>
                    	
                    	<div class="form-group">
                 			<label for="districtAliasInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label>
	                      	<div class="col-sm-6">
	                      		<form:input path="localBodyAliasEnglish" class="form-control" id="localBodyAliasEnglish" maxlength="50" htmlEscape="true"/>
								<form:errors htmlEscape="true" path="localBodyAliasEnglish" cssClass="error"/>
	                      	</div>
                    	</div>
                    	
                    	<div class="form-group">
                 			<label for="districtAliasInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label>
	                      	<div class="col-sm-6">
	                      		<form:input path="localBodyAliasLocal"  class="form-control" id="localBodyAliasLocal" maxlength="50" htmlEscape="true"/>
								<form:errors htmlEscape="true" path="localBodyAliasLocal" cssClass="error"/>
	                      	</div>
                    	</div>
                    	
                    	 <%@include file="../ExistingGovernmentOrderCpy.jsp"%>
                    	
                    	
                    	
                    	
                    	<div class="box-footer">
                     		<div class="col-sm-offset-2 col-sm-10">
                       			<div class="pull-right">
                       				<div style="display: none" id="drafthide">
                       				<input class="btn btn-success" id="btnFormActionSaveDraft" type="submit" value="<spring:message code='Button.DRAFT' htmlEscape='true'/>" param="DRAFT"/> 
                       				</div>
									<input class="btn btn-success" id="btnFormActionSave" type="submit" value="<spring:message htmlEscape="true" code="Button.SP"/>" param="PUBLISH"/>
									<input class="btn btn-danger" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
									
                        		</div>
                    		 </div>   
                  		</div>
                    	
					</form:form>
	                
	                
	                </div>
	             </div>
	          </section>
	      </div>
	 </section>
</body>
</html>