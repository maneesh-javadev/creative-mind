<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 --><!-- <script type="text/javascript" src="js/createDistrict.js"></script> -->
  <script src="js/Parliament.js"></script>
 
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>

<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);



function resetStandardCodeForm()
{
	document.getElementById('cencouscode').value="";
	document.getElementById('statespecificcode').value="";

}

function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = 'ParliamentConstituencyPagination.htm';
	document.forms['form1'].submit();
}
function validate()
{
			document.forms['form1'].submit();
		
}
/* function validateform()

{
		var districtName=document.getElementById('districtNameInEn').value;
	
	if(districtName=="")
	{
	alert("Please Enter Name Of the Districit");
	document.getElementById('districtNameInEn').focus();
	return false;
	}
	return true;
} */


function selectDistrict(id,name)
{     
	
		if (id.match('PART')=='PART'){
		
	var selObj=document.getElementById('ddDestDistrict');	
	
	//var selObj2=document.getElementById('surveyListNew');
	//var selObj=document.getElementById('subDistrictListNew');	
	/*var subDistrict="";
		for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrict +=selObj.options[i].value+",";
	 }*/
		lgdDwrStateService.getDistricts(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
		
	}else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part State From the List");
	}
	}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	
	var fieldId = 'ddDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	

	dwr.util.addOptions(fieldId, data,valueText,nameText);
	
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}
	//DWR Dropdownlist Population


	//DWR Dropdownlist Population

	

function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
	
</script>
</head>

<body onload="">
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.StandardCodeDETAIL" htmlEscape="true"></spring:message></td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<form:form action="modifyStandardCodeAction.htm?disturb=${disturb}" method="POST" commandName="Standard_Update">
 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyStandardCodeAction.htm"/>"/>
 
			<table width="100%" class="tbl_no_brdr">
			
			</table>
	</div>

		<div class="frmpnlbg">
			<div class="frmtxt">


					<div class="frmhdtitle">
						<spring:message code="Label.StandardCodeDETAIL" htmlEscape="true"></spring:message>
				
               
			
		</div>
		<div   id='changevillage' class="frmpnlbg">
							  <div class="frmtxt">
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listVillageDetails" varStatus="listSubdistrictDetailsRow" items="${Standard_Update.listVillageDetails}">
								  
								  
								  
								  <tr>
                                      <td width="14%" rowspan="6">&nbsp;</td>
                                      <td width="86%"><label><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
                                          <label>
                                           <spring:bind path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageCode">							
								            <input type="text" class="frmfield" maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"   id="subdistrictname" cssClass="frmfield" onfocus="show_msg('subdistrictname')" onblur="subdistrictName()"htmlEscape="true" readonly/>							
					    	                  <span class="error" id="subdistrictname_error"></span>							
					    	               </spring:bind>
					    	               
					    	              
										  
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    
                                    <tr>
                                     
                                      <td width="86%"><label><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
                                          <label>
                                           <spring:bind path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageNameEnglish">							
								            <input type="text" class="frmfield" maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"   id="subdistrictname" cssClass="frmfield" onfocus="show_msg('subdistrictname')" onblur="subdistrictName()"htmlEscape="true"  readonly/>							
					    	                  <span class="error" id="subdistrictname_error"></span>							
					    	               </spring:bind>
					    	               
					    	              
										  
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                   
                                     <tr>
                                    
                                      <td width="86%"><label><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></label><span class="errormsg"></span><br />
                                          <label>
                                           <spring:bind path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageNameLocal">							
								            <input type="text" class="frmfield" id="statespecificcode" maxlength="5" onkeypress="validateforLATIandLONGI(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"   id="subdistrictname" cssClass="frmfield" onfocus="show_msg('subdistrictname')" onblur="subdistrictName()"htmlEscape="true" />							
					    	                  <span class="error" id="subdistrictname_error"></span>							
					    	               </spring:bind>
					    	               
					    	              
										  
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    
                                     <tr>
                                    
                                      <td width="86%"><label><spring:message code="Label.CENCUSCODES" htmlEscape="true"></spring:message></label><span class="errormsg"></span><br />
                                          <label>
                                           <spring:bind path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].census2001Code">							
								            <input type="text" class="frmfield" id="cencouscode"  maxlength="50" onkeypress="validateNumericKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"   id="subdistrictname" cssClass="frmfield" onfocus="show_msg('subdistrictname')" onblur="subdistrictName()"htmlEscape="true" />							
					    	                  <span class="error" id="subdistrictname_error"></span>							
					    	               </spring:bind>
					    	               
					    	              
										  
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    
                                    <%-- <tr>
                                      <td width="14%" rowspan="6">&nbsp;</td>
                                      <td width="86%"><label><spring:message code="App.VILLAGENAMES" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
                                          <label>
                                           <spring:bind path="Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].villageNameEnglish">							
								            <input type="text" class="frmfield" maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />"   id="subdistrictname" cssClass="frmfield" onfocus="show_msg('subdistrictname')" onblur="subdistrictName()"htmlEscape="true" readonly/>							
					    	                  <span class="error" id="subdistrictname_error"></span>							
					    	               </spring:bind>
					    	               
					    	              
										  
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="App.CENCUSCODES" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                           <spring:bind path="{Standard_Update.listVillageDetails[${listSubdistrictDetailsRow.index}].census2001Code">							
								            <input type="text" class="frmfield" id="txtNameLocal"  maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" htmlEscape="true" />						
					    	               </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    
                                  
                                     
                                     <tr>
                                      <td><label><spring:message code="App.STATESPECIFICCODES" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                           <spring:bind path="{Standard_Update.listVillageDetails[${listSubdyistrictDetailsRow.index}].sscode">							
								            <input type="text" class="frmfield" id="txtAliasEnglish" onkeypress="validateNumericKeys(event)" maxlength="50" onfocus="show_msg('txtAliasEnglish')" onblur="Cns2011Code()" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" htmlEscape="true" />					
					    	                </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr> --%>
                                  
                                  
                                     </c:forEach>
                                       </table>
							<table width="100%" class="tbl_no_brdr">
								<%-- <tr>
									<td colspan="2">
										<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><form:hidden id="requiredTitle" path="requiredTitle" name="requiredTitle" value="<%=requiredTitle %>" /></td>
										<td><form:hidden path="allowedFileType" id="allowedFileType" name="allowedFileType" value="<%=allowedFileType%>"  /></td>
										<td><form:hidden path="allowedTotalFileSize" id="allowedTotalFileSize" name="allowedTotalFileSize" value="<%=allowedTotalFileSize %>"  /></td>
										<td><form:hidden path="allowedIndividualFileSize" id="allowedIndividualFileSize" name="allowedIndividualFileSize" value="<%=allowedIndividualFileSize %>"  /></td>
										<td><form:hidden path="uniqueTitle" id="uniqueTitle" name="uniqueTitle" value="<%=uniqueTitle %>"  /></td>
										<td><form:hidden path="allowedNoOfAttachment" id="allowedNoOfAttachment" name="allowedNoOfAttachment" value="<%=allowedNoOfAttachment %>" />
											<input type="hidden" name="hiddenAllowedNoOfAttachment" id="hiddenAllowedNoOfAttachment" value="<%=allowedNoOfAttachment %>"/>
										</td>
										<td><form:hidden path="uploadLocation" id="uploadLocation" name="uploadLocation" value="<%=uploadLocation %>" /></td>
										
										<td><div class="errormsg">
												<c:if test="${! empty validationError}">
													<spring:message code="${validationError}"/>
												</c:if>
										</div></td>
									</tr>
										</table></td>
								</tr>
 --%>
								<%-- <tr>
									<td width="14%" rowspan="20">&nbsp;</td>
									<td width="86%"><label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input path="orderNo"
											id="OrderNo" type="text" class="frmfield"
											onblur="hideErrorMsges();"
											onkeypress="validateNumericAlphaKeys();" /> <span
										class="errormsg" id="OrderNo_error"></span>
										<form:errors htmlEscape="true"  path="orderNo" cssClass="errormsg" ></form:errors>
										</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									 <td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input path="orderDate"
											id="OrderDate" type="text" class="frmfield"
											 onblur="hideErrorMsges();"
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="OrderDate_error"></span>
										<form:errors htmlEscape="true"  path="orderDate" cssClass="errormsg" ></form:errors>
									</td> 

								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								 <tr>
									<td><label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" path="effectiveDate" type="text"
											class="frmfield" 
											onblur="hideErrorMsges();" onkeypress="validateNumeric();" /><span
										class="errormsg" id="EffectiveDate_error"></span>
										<form:errors htmlEscape="true"  path="effectiveDate" cssClass="errormsg" ></form:errors>
										</td>
								</tr> 
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input id="GazPubDate"
											path="gazPubDate" type="text" class="frmfield"
											 onblur="hideErrorMsges();"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="GazPubDate_error"></span> <form:errors htmlEscape="true"  path="gazPubDate"
											cssClass="errormsg"></form:errors>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								
								<tr>
									<td><label><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
									<form:input id="filGovernmentOrder1" path="filePathcr"	class="frmfield" type="file" size="25"
										onfocus="show_msg('filGovernmentOrder1');" onblur="validateSFile();" /> 
										 
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
 --%>
								<%-- <tr>

									<td><div id="titleId">
									<form:hidden id="showTitle" path="showTitle" name="showTitle" class="frmfield" /></div><div id="hiddentitle"></div><br/>
										<div id="hideAttachmentUtilDiv">
											<div id="fileId">
											<label>	<spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message></label>
												<span class="errormsg">*</span> <br /> <input type='hidden'
													name='attachmentNumber'
													value='"<%=allowedNoOfAttachment%>"' /> <input type="file"
													name="attachedFile" id="loadfile" class="multi frmfield"
													onblur="hideErrorMsges();"
													maxlength="<%=allowedNoOfAttachment%>" style="width: 280px"
													accept="<%=allowedFileType%>"
													onclick="{return holdTitle()}" /> <span class="errormsg"
													id="loadfile_error"></span>
												<form:errors htmlEscape="true"  path="attachedFile" cssClass="errormsg"></form:errors>
											</div>
											<br /> <br />
											<div id="alreadyAttachedFiles">
												<c:if test="${! empty Attached_File_Meta_Data_List}">
													<table width="100%" class="tbl_with_brdr" align="center"
														id="tid">
														<tr class="frmhd">
															<!-- 	<spring:message code="addAttachment.tableheading" /> -->
														</tr>
														<tr class="tblRowTitle tblclear">
															<td><spring:message code="addAttachment.filetitle" />
															</td>
															<td><spring:message code="addAttachment.filename" />
															</td>
															<td><spring:message code="addAttachment.filesize" />
															</td>
															<td><spring:message
																	code="addAttachment.filecontenttype" /></td>
															<td><spring:message
																	code="addAttachment.fileflagfordeletion" /></td>
														</tr>
														<c:forEach var="abc"
															items="${Attached_File_Meta_Data_List}">

															<tr id="row${abc.attachmentId}" class="tblRowB">

																<td>${abc.fileTitle }</td>
																<td>${abc.fileName }</td>
																<td>${abc.fileSize }</td>
																<td>${abc.fileContentType }</td>

																<td width="10%"><a href="#"><img
																		src="images/delete.png" name="deleteAttachment"
																		width="18" height="19" border="0"
																		onclick="{hideThisRow(${abc.attachmentId}),holdAttachmentID(${abc.attachmentId}),holdFilePath('${abc.fileTimestamp}'),holdFileSize(${abc.fileSize })}" />
																</a>
																	<div id="deleteID"></div>
																	<div id="deletePath"></div>
																	<div id="deleteFileSize"></div>
																</td>
															</tr>

														</c:forEach>
													</table>
												</c:if>
									</td>
								</tr> --%>
								<tr>
									<td>&nbsp;</td>

								</tr>
								<tr>
									<td>&nbsp;</td>

								</tr>
							</table>
									                                  
							  </div>
						
						   
						
					 <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td><label>
                                    <input type="submit" class="btn" name="Submit" value="<spring:message code="Button.SAVE"></spring:message>"  />
                                    </label>
                                    <input
									type="button" name="Submit3" class="btn"
									value="<spring:message code="Button.CLEAR"></spring:message>"
									id="btnClear" onclick="resetStandardCodeForm();" />
                                    </label>
                                      <label> <input type="button" name="Cancel"
							class="btn" value="<spring:message code="Button.CLOSE"></spring:message>"
						    id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" /> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
				</div>		
		<br /> <br /> <br />
		</td>

		</tr>
		
		
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
	</div>
	</div>


	</div>
	</td>
	</tr>
	</table>
	</div>
	</div>
	</div>
	</form:form>
		<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	
	</div>
	</div>

</body>
</html>