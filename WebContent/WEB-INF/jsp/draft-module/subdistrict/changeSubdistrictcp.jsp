
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%@include file="../govermentOrder/draftExistingGovernmentOrderJs.jsp"%>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
 <script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script> 
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<%@include file="changeSubdistrictJs.jsp"%>


<title><spring:message htmlEscape="true" code="label.edit.draft.subdistrict"></spring:message></title>

<script>

function getDistrictData(){
	 var districtElement=$( '#districtCode');
		if(isEmptyObject($( districtElement ).val())){
			$( districtElement ).addClass("error");
			$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
				$(districtElement).focus();
				firstErrorElement=true;
				
		}else{
		 callActionUrl('manageSubdistrict.htm');
	 }
}


$("#Submit3").click(function() {
	
	 var districtElement=$( '#districtCode');
		if(isEmptyObject($( districtElement ).val())){
			$( districtElement ).addClass("error");
			$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
				$(districtElement).focus();
				firstErrorElement=true;
		}else{
		 callActionUrl('manageSubdistrict.htm');
	 }
	
 });

</script>



</head>
<body>
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYSUBDISTRICT"></spring:message></h3>
					</div>
			<form:form action="buildSubdistrictDraftChange.htm" method="post" id="draftManageSubdistrictForm" commandName="draftManageSubdistrictForm" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSubdistrictDraftChange.htm"/>" />
				<input type="hidden" name="subdistrictNameEnglish" id="subdistrictOld" value="${draftManageSubdistrictForm.subdistrictNameEnglish}" />
				<form:hidden path="formAction"	id="formAction"/>
				<form:hidden path="districtCode"  />
				<form:hidden path="subdistrictCode" value="${draftManageSubdistrictForm.subdistrictCode}" />
				<form:hidden path="groupId"  />
				<form:hidden path="editMode" />
				<form:hidden path="draftCode" />
						<div class="box-body">
								<div class="box-header subheading">
				                  <h4>Modify Sub District</h4>
				                </div>
								<div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message>
										<span class="mandatory">*</span></label>
			                      	<div class="col-sm-6">
			                        	<form:input path="changeSubdistrictNameEnglish" id="subdistrictNameEnglish"  maxlength="50" htmlEscape="true" class="form-control" placeholder="Enter Subdistrict Name English" />
			                      	    <span class="errormessage">Allowed characters are A-Z,a-z and Space</span><br/>
									<span class="mandatory" id="errsubdistrictNameEnglish"></span>
									<form:errors htmlEscape="true" path="changeSubdistrictNameEnglish" cssClass="error"/>
			                      	</div>
			                    </div>
			                    
				                    <div class="form-group">
				                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMELOCAL"></spring:message></label>
				                      	<div class="col-sm-6">
				                        	<form:input path="subdistrictNameLocal" id="subdistrictNameLocal" maxlength="50" class="form-control" htmlEscape="true"  />
									<span class="mandatory" id="errsubdistrictNameLocal"></span>
									<form:errors htmlEscape="true" path="subdistrictNameLocal" cssClass="error"/>
				                      	</div>
				                    </div>
			                    
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ALIASENGLISH"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:input path="aliasEnglish" id="aliasEnglish" maxlength="50" htmlEscape="true" class="form-control" />
									<span class="mandatory" id="erraliasEnglish"></span>
									<form:errors htmlEscape="true" path="aliasEnglish" cssClass="error"/>
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ALIASLOCAL"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:input path="aliasLocal" id="aliasLocal" maxlength="50" htmlEscape="true" class="form-control" />
									<span class="mandatory" id="erraliasLocal"></span>
									<form:errors htmlEscape="true" path="aliasLocal" cssClass="error"/>
			                      	</div>
			                    </div>
			                    
			                    
			                    <!-- 			------modal start---		 -->	
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
							    		<span class="mandatory" id="goErrOrderNo"></span>
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
							 
							    		<span class="mandatory" id="goErrFromDate"></span>
		    		</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"> <spring:message code='Label.TODATE' htmlEscape='true'/> </label>
					<div class="col-sm-6">
		    		      <div class="input-group date datepicker" id="bdateParamTo">
			                        		<input type="text" id="dateParamTo" readonly="readonly" class="form-control"/><span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
						  </div>
						 
						 <span class="mandatory" id="goErrToDate"></span>
		    		</div>
				</div>
				
		<div class="box-body">	
	   	<table id="tblGornmentOrderDetails" class="table table-bordered table-hover"  style="display:none">
												<thead>
													<tr>
														<th width="5%"><!-- Header for Radio Button --></th>
														<th width="10%"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message></th>
														<th width="10%"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></th>
														<th width="10%"><spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></th>
														<th width="15%"><spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></th>
														<th width="15%"><spring:message code='Label.UGO' htmlEscape='true'/></th>
													</tr>
												</thead>
											</table>
											
											
					</div>						
	   
        </div>
        <div class="modal-footer">
          <button  class="btn btn-info" type="button" id="fetchGODetails" value="" > Fetch Gornment Order</button>
          <button type="button" class="btn btn-default" id="goClose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
</div>
				<!-- 		------modal end--    -->
			                    
			                    
			   <div class="box-header subheading">
					            <h4><spring:message htmlEscape="true"  code="Legend.GENERALDETAILOFNEWSUBDISTRICT"></spring:message></h4>
					        </div>
						
							<c:if test="${isGovernmentOrderUpload}">
								<div class="form-group" style="display:none;">
								
			                      	<div class="col-sm-10">
			                        	<button type="button" class="btn btn-primary" id="actionExistingGO"><spring:message code='Label.selectexistinggo' htmlEscape='true'/></button>
										<button type="button" class="btn btn-primary" id="actionNewGO"><spring:message code='Label.selectnewgo' htmlEscape='true'/></button>
			                      	</div>
			                    </div>
		                    </c:if>                    
			                    
			                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<form:hidden path="orderCode" id="orderCode" htmlEscape="true"/>
									<form:input path="orderNo" id="orderNo" class="form-control" value="${draftManageSubdistrictForm.orderNo}" maxlength="60" htmlEscape="true"/>
										<span class="mandatory" id="errorderNo"></span>
										<br/>
										<form:errors htmlEscape="true" path="orderNo" cssClass="error"/>
										<input type="hidden" id="checkNewOrExistGovtOrder"/>
								                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                      	
		                      	<div class="input-group date datepicker" id="bformDateOrderDate">
								 		<fmt:formatDate var="orderDateValue" value="${draftManageSubdistrictForm.orderDate}" pattern="dd/MM/yyyy" /> 
										<form:input path="orderDate" id="formDateOrderDate" value="${orderDateValue}" readonly="true" class="form-control"  onchange="setEffectiveDate(this.value);"/>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
										
		                      		
									<br/>
									<span class="mandatory" id="errformDateOrderDate"></span>
									<form:errors htmlEscape="true" path="orderDate"  cssClass="error"/>
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label">
		                    		<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										<c:if test="${not empty draftManageSubdistrictForm.iParamEffectiveDate}">
											<strong>( <spring:message	code="Label.Previous.Version.Effective.Date" htmlEscape="true"></spring:message> : <fmt:formatDate pattern="dd/MM/yyyy" value="${draftManageSubdistrictForm.iParamEffectiveDate}" /> )</strong>
										</c:if>
										<span class="mandatory">*</span>			
								</label>
		                      	<div class="col-sm-6">
		                      	   <div class="input-group date datepicker" id="bformDateEffectiveDate">
								 		<fmt:formatDate var="effectiveDateValue" value="${draftManageSubdistrictForm.effectiveDate}" pattern="dd/MM/yyyy" /> 
										<form:input path="effectiveDate" id="formDateEffectiveDate" readonly="true" value="${effectiveDateValue}" class="form-control"/>
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
		                      	
		                      	<form:hidden path="iParamEffectiveDate" htmlEscape="true"/><!-- Previous Version Effective Date -->
										<br/>
										<span class="mandatory" id="errformDateEffectiveDate"></span>
										<form:errors htmlEscape="true" path="effectiveDate" cssClass="error"/>
		                      	</div>
		                    </div>
		                    <c:choose>
				<c:when test="${isGovernmentOrderUpload}">
					<div class="form-group">
			   			<label class="col-sm-3 control-label">	
						<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
						</label>
						<div class="col-sm-6">
							 <div class="input-group date datepicker" id="bformDateGazPubDate">
								 		<fmt:formatDate var="gazPubDateValue" value="${draftManageSubdistrictForm.gazPubDate}" pattern="dd/MM/yyyy" /> 
								         <form:input	 path="gazPubDate" id="formDateGazPubDate" readonly="true" value="${gazPubDateValue}" class="form-control" />
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
										
								
								<br/>
								<form:errors htmlEscape="true" path="gazPubDate" cssClass="error"/>
						</div>
						
					</div>
					<div>
						<c:if test="${(empty draftManageSubdistrictForm.orderCode) and (modifyProcess or checkedServerValidation)}">
							<c:set var="fileName" value=""/>
							<c:if test="${not empty draftManageSubdistrictForm.orderPath}">
								<c:set var="substrng" value="${fn:substring(draftManageSubdistrictForm.orderPath, fn:indexOf(draftManageSubdistrictForm.orderPath, '_'), fn:indexOf(draftManageSubdistrictForm.orderPath, '.'))}" />
								<c:set var="fileName" value="${fn:replace(draftManageSubdistrictForm.orderPath, substrng, '')}" />
							</c:if>
							<a id="attachedUploadedFile" href="downloadLBGovernementOrder.htm?filename=${draftManageSubdistrictForm.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
								<c:out value="${fileName}"/>
							</a>
							<form:hidden path="orderPath"/>
							<br/><br/>
						</c:if>
						<div id="divGazettePublicationUpload" <c:if test="${not empty draftManageSubdistrictForm.orderCode}">style="display: none;"</c:if>>
							<div class="form-group">
							 <label class="col-sm-3 control-label">	
								<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"/>
								<spring:message code='Label.allowedfileexe' htmlEscape='true'/>
								<span class="mandatory">*</span>								
							</label>
							<div class="col-sm-6">
							
							
							
							
							 <form:input path="gazettePublicationUpload" id="gazettePublicationUpload" type="file" class="form-control" onclick="return validateFileUpload();"/>
							  <form:errors htmlEscape="true" path="gazettePublicationUpload" cssClass="error"/>
							  <span class="mandatory" id="errgazettePublicationUpload"></span><br/>
							</div>
							</div>
							
							<c:if test="${draftManageSubdistrictForm.editMode}">
							
								<table id="attachFile" class="table table-bordered table-hover">
								<tr>
								<th><spring:message code="Label.FILENAME" htmlEscape="true"/></th>
								<th><spring:message code="Label.FILESIZE" htmlEscape="true"/></th>
								<th><spring:message code="Label.CONTENTTYPE" htmlEscape="true"/></th>
								<th><spring:message code="Label.MARKDELETEFILE" htmlEscape="true"/></th>
								</tr>
								<tr>
								<td><c:out value="${draftManageSubdistrictForm.fileName}" /></td>
								<td><c:out value="${draftManageSubdistrictForm.fileSize}" /></td>
								<td><c:out value="${draftManageSubdistrictForm.fileContentType}" /></td>
								<td>
										<a href="#" onclick="deleteExistUploadFile();">
											<img src="images/delete.png" width="18" height="19" border="0"/>
										</a>
								</td>
								</tr>
								</table>
							</c:if>
						</div>		
					</div>	
				</c:when>
				<c:otherwise>
					<div class="form-group">
			   			<label class="col-sm-3 control-label">	
						<spring:message htmlEscape="true" code="Label.SELGOT"></spring:message>
						<span class="mandatory">*</span>								
						</label>
						<div class="col-sm-6">
						  <form:select path="templateId" id="templateId" class="form-control">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
							<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
						</form:select>
						</div>
						
					</div>	
					<div class="form-group">
					<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6" id="divCKEditor" <c:if test="${not (modifyProcess or checkedServerValidation)}">style="display: none;"</c:if>>
							<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor"/>
						</div>
			   		</div>
				</c:otherwise>
			</c:choose>
			
			  
			                   
							<div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
		                           		<div style="display: none" id="drafthide">
		                           			<button class="btn btn-success" id="btnFormActionSaveDraft" type="button"  ><spring:message htmlEscape="true" code="Button.DRAFT"/></button> 
										</div>
											<c:if test="${!draftManageSubdistrictForm.editMode}">
												<button class="btn btn-success" id="btnFormActionPublish" type="button"  ><spring:message htmlEscape="true" code="Button.SP"/></button>
											</c:if>
											<button class="btn btn-danger" id="btnActionClose" type="button" onclick="callActionUrl('home.htm')"><spring:message htmlEscape="true" code="Button.CLOSE"/></button>
											<button type="button" name="Submit3" class="btn btn-info" onclick="getDistrictData()" <csrf:token uri='home.htm'/>'" ><spring:message code="Button.BACK" htmlEscape="true"></spring:message></button>
		                        	</div>
		                    	 </div>   
	                  		</div>
	                  		<input type="hidden" name="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}'/>"/>
							<input type="hidden" name="type" value="<c:out value='${type}'/>"/>
						</div>
					</form:form>
					<script src="/LGD/JavaScriptServlet"></script>
				</div>
			</section>
<!-- -------------confirm dialog---------			 -->
			
 <div class="modal fade" id="customAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Section Delete confirm</h4>
        </div>
        <div class="modal-body" id="customAlertbody">
         
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" id="yes">Yes</button>
           <button type="button" class="btn btn-default" data-dismiss="modal" >Cancel</button>
        </div>
      </div>
      
    </div>
  </div>
		</div>
	</section>
</body>
</html>

