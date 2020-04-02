<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message code="Label.MODIFYMINISTRY" htmlEscape="true"></spring:message></title>
<script>

<!-- Modified by Pooja on 09-07-2015 -->
/* $(document)
.ready(
		function() {
			$("#frmModifyVillage")
					.validate(
							{
								rules : {
									ministryNamecr : {
										required : true,
										onlyLetterSpace : true
									},
									shortministryName : {
										shortName : true
									}
								},
								messages : {
									ministryNamecr : {
										required : "<font color='red'><br><spring:message code='MINISTRYNAME.REQUIRED' text='Please enter ministry name'/></font>",
										onlyLetterSpace : "<font color='red'><br><spring:message code='error.valid.ministryName' text='Please enter value in correct format'/></font>"
									},
									shortministryName : {
										shortName : "<font color='red'><br><spring:message code='error.common.short.name' text='Short Name is invalid'/></font>"
									}
								}
							});
		}); */

		$(document).ready(function() {	
			$("#btnSave").click(function() {	
				clearFormErrors();
				var ministryName = $("#ministryName").val();	
				var ministryShortName = $("#ministryshortName").val();
				if(ministryName ==''){
					$('#ministryName_errorMsg').html("Please Enter Ministry Name");
					return false;
				}
				else{
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryName)){
					$('#ministryName_errorMsg').html("Please Enter value in Correct format");
					return false;
				}
				}
				if(ministryShortName != ''){
					var nameReg = /^[a-zA-Z\s]+$/;
					if (!nameReg.test(ministryShortName)){
					$('#ministryShortName_errorMsg').html("Short Name is invalid");
					return false;
				}
				}
				return true;
			});
		});
		function clearFormErrors(){
			$('#ministryName_errorMsg').html("");
			$('#ministryShortName_errorMsg').html("");
		}
</script>
<script type="text/javascript">
	function validateMinistryCorrection()
	{
		
		
		var errors = new Array();
		var error = false;


			errors[0] = vlidateOnblur('ministryName','1','15','c');


		  if(errors[0]==true){
			  error = true;
		  }
		
			if(error == true)
			{
			
			showClientSideError();
		
			return false;
			}
		else
			{
				return true;
			}
					
/* 		var msg=null;	
				if (!MinistryName()) {
					if(msg!=null)
					{
						msg=msg+"Ministry Name is Required"+ '\n';	
					}
					else
						{
						msg="Ministry Name is Required"+ '\n';	
						}
				}
										
				if(msg!=null)
					{
					alert(msg);
					return false;
				}
				else{
					
					return true;
				}
				
			return false; */
	}
	
/////////////////////////////////////////	
	function chkVillageOnLoad(){
		$("#ministryName_error").hide();
		$("#ministryNamecr_error").hide();
		$("#OrderNo_error").hide();
		$("#OrderDate_error").hide();
		$("#EffectiveDate_error").hide();
		$("#filGovernmentOrder_error").hide();
	}	
	

	</script>
</head>
<body onload='chkVillageOnLoad();'>

	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                  <form:form action="modifyMinistryAction.htm" method="POST" commandName="modifyMinistryCmd" id="frmModifyVillage" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyMinistryAction.htm"/>" />
		              
				       
     <div class="box"  id="cat">
     <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.MODIFYMINISTRY" htmlEscape="true"></spring:message></h3>
                                </div>
        <div class="box-header subheading">
                          <h4><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
                        </div>
       <div class="box-body">
       
          
        
            
             <div class="form-group">
               <label class="col-sm-3 control-label" ><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message></label>
               <div class="col-sm-6">
                 	<form:input path="ministryNamecr" class="form-control"  id="ministryName" 
										onfocus="validateOnFocus('ministryName');show_msg('ministryName')" onblur="vlidateOnblur('ministryName','1','15','c');" htmlEscape="true" maxlength="200" /> <%-- onfocus="show_msg('ministryName')" onblur="MinistryName()"--%> <span class="error"
										id="ministryName_error">Error.Ministrynamerequired<spring:message code="Error.Ministrynamerequired" htmlEscape="true"></spring:message></span> <form:errors path="ministryNamecr" class="errormsg" htmlEscape="true"></form:errors>
										<span class="errormsg" id="ministryName_errorMsg"></span>
										<div class="errormsg"></div>
               </div>
             </div>
             
              
              <div class="form-group">
               <label class="col-sm-3 control-label" > <spring:message code="Label.MINISTRYSHORTNAME" htmlEscape="true"></spring:message></label>
               <div class="col-sm-6">
                 	<form:input path="shortministryName" class="form-control"  id="ministryshortName" htmlEscape="true" maxlength="10"/>
										<form:input path="orgCode" class="form-control" type="hidden" htmlEscape="true"/> 
					                    <form:input path="orgTypeCode" class="form-control" type="hidden" htmlEscape="true" />
					 					<form:input path="localBodySpecific" class="form-control" type="hidden" htmlEscape="true" />
					          		   <form:input path="orgVersion" class="form-control" type="hidden" htmlEscape="true"/> 
					          		   <form:errors path="shortministryName" class="errormsg" htmlEscape="true"></form:errors>
										<span class="errormsg" id="ministryShortName_errorMsg"></span>
										<div class="errormsg"></div>
               </div>
             </div>
             
         </div>    
           
         <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                	<button type="submit" name="Submit" id="btnSave" class="btn btn-success"   ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
					<button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                 </div>
           </div>   
       </div> 
           
           
     </div>      
         
        
      
        
        
        <div id='changevillage' class="box" style="visibility: hidden; display: none">
						<div class="box-body">
							<div class="box-header subheading" >
								<h4><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message></h4>
							</div>
							
									<div class="form-group">
											<label class="col-sm-3 control-label"><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
											 <div class="col-sm-6"> <form:input path="ministryName" class="form-control" id="ministryNamecr" onfocus="validateOnFocus('ministryNamecr');show_msg('ministryNamecr')" onblur="vlidateOnblur('ministryNamecr','1','15','c');" htmlEscape="true" />
											 <span class="error" id="ministryNamecr_error"><spring:message code="Error.Ministrynamerequired" htmlEscape="true"></spring:message></span>
											  <form:errors path="ministryName" class="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										</div>			
									</div>
									
									
									<div class="form-group">
											<label class="col-sm-3 control-label"><spring:message code="Label.MINISTRYNAME" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
											 <div class="col-sm-6"> <form:input path="ministryName" class="form-control" id="ministryNamecr" onfocus="validateOnFocus('ministryNamecr');show_msg('ministryNamecr')" onblur="vlidateOnblur('ministryNamecr','1','15','c');" htmlEscape="true" />
											 <span class="error" id="ministryNamecr_error"><spring:message code="Error.Ministrynamerequired" htmlEscape="true"></spring:message></span>
											  <form:errors path="ministryName" class="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										</div>
										
										<form:hidden id="requiredTitle" path="requiredTitle" htmlEscape="true" name="requiredTitle" value="<%=requiredTitle %>" />
										<form:hidden path="allowedFileType" id="allowedFileType" htmlEscape="true" name="allowedFileType" value="<%=allowedFileType%>" />
										<form:hidden path="allowedTotalFileSize" id="allowedTotalFileSize" htmlEscape="true" name="allowedTotalFileSize" value="<%=allowedTotalFileSize %>" />
										<form:hidden path="allowedIndividualFileSize" id="allowedIndividualFileSize" htmlEscape="true" name="allowedIndividualFileSize" value="<%=allowedIndividualFileSize %>" />
										<form:hidden path="uniqueTitle" id="uniqueTitle" name="uniqueTitle" htmlEscape="true" value="<%=uniqueTitle %>" />
										<form:hidden path="allowedNoOfAttachment" id="allowedNoOfAttachment" htmlEscape="true" name="allowedNoOfAttachment" value="<%=allowedNoOfAttachment %>" /> <input type="hidden" name="hiddenAllowedNoOfAttachment" id="hiddenAllowedNoOfAttachment" value="<%=allowedNoOfAttachment %>" />
										<form:hidden path="uploadLocation" id="uploadLocation" name="uploadLocation" htmlEscape="true" value="<%=uploadLocation %>" />			
									</div>
									
									<div class="form-group">
									  <div class="errormsg">
											<c:if test="${! empty validationError}">
												<spring:message code='<c:out value="${validationError}" escapeXml="true"></c:out>' />
											</c:if>
										</div>
									</div>
										
										
									
									<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
										<div class="col-sm-6">
											<form:input path="orderNo" id="OrderNo" type="text" class="form-control" onfocus="validateOnFocus('OrderNo');" onblur="vlidateOnblur('OrderNo','1','15','c');" onkeypress="validateNumericAlphaKeys();" htmlEscape="true" /> 
											 <span class="errormsg" id="OrderNo_error">Order No. is required</span> 
											<form:errors path="orderNo" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
										<div class="col-sm-6">
											 <form:input path="orderDate" id="OrderDate" type="text" class="form-control" onfocus="validateOnFocus('OrderDate');" onblur="vlidateOnblur('OrderDate','1','15','c');" onchange="setEffectiveDate(this.value);" onkeypress="validateNumeric();" htmlEscape="true" />
											<span class="errormsg" id="OrderDate_error">Order date is required</span>
										    <div class="errormsg"></div> <form:errors path="orderDate" class="errormsg" htmlEscape="true"></form:errors>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
										<div class="col-sm-6">
											 <form:input id="EffectiveDate" path="ordereffectiveDate" type="text" class="form-control" onfocus="validateOnFocus('EffectiveDate');" onblur="vlidateOnblur('EffectiveDate','1','15','c');" onkeypress="validateNumeric();" htmlEscape="true" /> 
											  <span class="errormsg" id="EffectiveDate_error">Effective date is required</span>
										      <div class="errormsg"></div> <form:errors path="ordereffectiveDate" cssClass="errormsg" htmlEscape="true"></form:errors>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
										<div class="col-sm-6">
											 <form:input id="GazPubDate" path="gazPubDate" type="text" class="frmfield" onkeypress="validateNumeric();" htmlEscape="true"/>
											 <div class="errormsg"></div> <form:errors path="gazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors>
										</div>
									</div>
							
									
									
											
								<div id="hideAttachmentUtilDiv">
									<form:hidden id="showTitle" path="showTitle" name="showTitle" class="frmfield" htmlEscape="true" />
								    <div id="fileId" class="form-group">
										<label class="col-sm-3 control-label"><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message> <span class="errormsg">*</span></label>
										 
										 <div class="col-sm-6">
												 <input type='hidden' name='attachmentNumber' value='"<%=allowedNoOfAttachment%>"' /> 
												 <input type="file" name="attachedFile" id="filGovernmentOrder" class="form-control" onblur="vlidateOnblur('filGovernmentOrder','1','15','c');" onfocus="validateOnFocus('filGovernmentOrder');" maxlength="<%=allowedNoOfAttachment%>"
																 accept="<%=allowedFileType%>" onclick="{return holdTitle()}" ; /> 
														<span class="errormsg" id="filGovernmentOrder_error">Please upload at least one file as Government Order</span>
														<form:errors path="attachedFile" cssClass="errormsg" htmlEscape="true"></form:errors>
										  </div> 		
										</div>
												<br /> <br />
												<div id="alreadyAttachedFiles">
													<c:if test="${! empty Attached_File_Meta_Data_List}">
														<table  class="table table-bordered table-hover" align="center" id="tid">
														
															<tr>
																<td><spring:message code="addAttachment.filetitle" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.filename" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.filesize" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.filecontenttype" htmlEscape="true" /></td>
																<td><spring:message code="addAttachment.fileflagfordeletion" htmlEscape="true" /></td>
															</tr>
															<c:forEach var="abc" items="${Attached_File_Meta_Data_List}">
	
																<tr id="row${abc.attachmentId}" >
	
																	<td><c:out value="${abc.fileTitle }" escapeXml="true"></c:out></td>
																	<td><c:out value="${abc.fileName }" escapeXml="true"></c:out></td>
																	<td><c:out value="${abc.fileSize }" escapeXml="true"></c:out></td>
																	<td><c:out value="${abc.fileContentType }" escapeXml="true"></c:out></td>
	
																	<td width="10%"><a href="#" onclick="{hideThisRow(${abc.attachmentId}),holdAttachmentID(${abc.attachmentId}),holdFilePath('${abc.fileTimestamp}'),holdFileSize(${abc.fileSize })}" >
																		<i class="fa fa-trash-o" aria-hidden="true"></i> </a>
																		<div id="deleteID"></div>
																		<div id="deletePath"></div>
																		<div id="deleteFileSize"></div></td>
																</tr>

														</c:forEach>
													</table>
												</c:if>
												</div>
												</div>
									
							
						</div>
						 		<div class="box-footer">   
						 		    <button type="submit" name="Submit" class="btn btn-success pull-right"  onclick="return validateMinistryChange();" ><spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>                 
                      				<button type="submit" class="btn btn-success pull-right" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                  				</div>
						
					</div>
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>