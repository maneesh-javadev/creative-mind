<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/jquery-1.7.js" type="text/javascript"	language="javascript"></script>
<script src="js/jquery.MultiFile.js" type="text/javascript"	language="javascript"></script>
<script src="js/attachedFiles.js" type="text/javascript"	language="javascript"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>



<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>

<title><spring:message htmlEscape="true"  code="Label.MODIFYSTATE"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script> -->
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<!-- <link rel="stylesheet" href="datepicker/demos.css" /> -->


<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	function show_msg(Field_msg)
	{
		var hint='#'+Field_msg+"_msg";
		var error='#'+Field_msg+"_error";
		$("#"+Field_msg).removeClass("error_fld");
		$("#"+Field_msg).removeClass("error_msg");
		$(hint).show();
		$(error).hide();
			
	}
	//OfficialAddress1 
	function officialAddress()
{

	if(document.getElementById("OfficialAddress").value == "" )
	{
		document.getElementById("OfficialAddress_error").innerHTML="State Name in English is Required";
		$("#OfficialAddress_error").show();
		$("#OfficialAddress_msg").hide();
		$("#OfficialAddress").addClass("error_fld");
		$("#OfficialAddress").addClass("error_msg");
		return false;

	}
	else 
	{
		$("#OfficialAddress_msg").hide();
		return true;
			
	}
	
}
	function getGisNodes()
	{
		if (document.getElementById('txtlatitude').value!='')
			{
				var gisList = document.getElementById('txtlatitude').value.split(':');
				//i=gisList.length;

				document.getElementById('txtlatitude').value = gisList[0].split(',')[0];
				document.getElementById('txtLongitude').value = gisList[0].split(',')[1];
				
				for (var k = 1; k <gisList.length; k++)
					{
						addgisnodes1();
						document.getElementById('lati' + k).value = gisList[k].split(',')[0];
						document.getElementById('longi' + k).value = gisList[k].split(',')[1];
					}
			}
	}
	
	</script>
</head>
<body onload='getGisNodes();toggledisplay2("chkchvillage","changevillage"); onLoadSelectDisturbed();'>
<div id="frmcontent">
					<div class="frmhd">
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message htmlEscape="true"  code="Label.MODIFYSTATE"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                        <%--//these links are not working   <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Button.HELP"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					<div class="clear"></div>
<div class="frmpnlbrdr">
						<form:form action="modifyStateAction.htm?disturb=${disturb}" method="POST" commandName="modifyStateCmd" id="frmModifyState" enctype="multipart/form-data">
							  <div class="frmpnlbg">
                                <div class="frmtxt">
                                  <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.MODIFYSTATEOPTION"></spring:message></div>
                                    <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="86%"><label><spring:message htmlEscape="true"  code="Label.MODIFYSTATEOPTION"></spring:message></label><br />
                                          <table width="259" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td width="20" height="25" class="tblclear"><label>
                                               <form:radiobutton path="correction" type="radio" id='chkcrvillage' value="true" 
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage")'/>
                                                 </label></td>
                                              <td width="83" class="tblclear"><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></td>
                                              <td width="20" class="tblclear"><label>
                                                <form:radiobutton path="correction"  type="radio"  id='chkchvillage' value="false"
                                                 onclick='toggledisplay2("chkchvillage","changevillage")' />
                                              </label></td>
                                              <td width="136" class="tblclear"><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></td>
                                            </tr>
                                          </table>
                                      <div class="errormsg"> </div></td>
                                    </tr>
                                  </table>
                                </div>
						      </div>
							  <div   id='correctionvillage' class="frmpnlbg" style="visibility: hidden; display:none">
							  <div class="frmtxt" >
								  <div class="frmhdtitle"  ><label><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></label> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${modifyStateCmd.listStateDetails}">
								   <tr>
								      <td width="14%" rowspan="14">&nbsp;</td>
                                      <td><label><spring:message htmlEscape="true"  code="Label.STATEORUT"></spring:message></label><br />
                                      <c:if test="${fn:containsIgnoreCase(listStateDetails.stateOrUt,'S')}">
                                          <label>
                                          <input type="text" class="frmfield"  value="State" onfocus="this.blur()" readonly/></c:if>
                                           <c:if test="${fn:containsIgnoreCase(listStateDetails.stateOrUt,'U')}">
                                          <label>
                                          <input type="text" class="frmfield"  value="Union Territory" onfocus="this.blur()" readonly/></c:if>
                                        
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr> 
                                    <tr>
                                      
                                      <td><label><spring:message htmlEscape="true" 	code="Label.MSTATENAMEENGLISH"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>   
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.MSTATENAMELOCAL"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onfocus="this.blur()" readonly/>
								           </spring:bind>	
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.ALIASENGLISH"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].aliasEnglish">							
							               <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onfocus="this.blur()" readonly/>	
                                           </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.ALIASLOCAL"></spring:message></label><br />
                                          <label>
                                         <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].aliasLocal">							
							  				<input type="text" id="txtAliaslocal"  class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onfocus="this.blur()" readonly/>
										</spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.SHORTNAMEENGLISH"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].shortName">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" onfocus="this.blur()" readonly/>	
								          </spring:bind>
								          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind>
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr> 
                                    
                                    <tr>
                                      <td width="86%"><label><spring:message htmlEscape="true"  code="Label.CENSUSCODE2011"></spring:message></label><br />
                                          <label>
                                         <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].census2011Code">							
											<input type="text" id="txtCensus" onkeypress="validateNumericAlphaKeys();"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>	
										 </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message></label><br />
                                        <table width=83% class="tbl_no_brdr">
                                        <tr>
                                         <td width="13%" id="lbllatitude" ><spring:message htmlEscape="true"  code="Label.LATITUDE"></spring:message></td>
                                           <td>
                                           <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].lati">							
								            <input type="text" id="txtlatitude"  name="lati" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	              </spring:bind>
					    	              
                                         </label> </td>  
                                         
                                          <td  width="14%" id="lbllongitude" ><spring:message htmlEscape="true"  code="Label.LONGITUDE"></spring:message></td>
                                           <td>
                                            <label>
                                             <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].longi">							
								            <input type="text" id="txtLongitude" name="longi" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	                  </spring:bind>
                                           </label><input type="button" name="Submit3" value="<spring:message htmlEscape="true"  code="Button.ADDNODES"></spring:message>" onclick="addgisnodes1()" /> </td>
                                           </tr>
                                           <tr> <td colspan="4"><div id="addgisnodes"></div></td></tr>
											 
                                      </table></td></tr>
                                <tr>
                                    <td class="tblclear">
                                    <table width="100%" class="tbl_no_brdr" align="center">								
								<tr>
								    
									<td width="80" ><label><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message></label><span class="errormsg">*</span><br />
									<label>
                                        <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderNocr">							
								            <input type="text" maxlength="20" onkeypress="validateNumericAlphaKeys();" id="OrderNo1" onblur="validateOrdeNo1();" onfocus="show_msg('OrderNo1');"
								            name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	              
					    	               </spring:bind><span class="msg" id="OrderNo_msg1"><spring:message htmlEscape="true"  code="Msg.ORDERNO"></spring:message></span><span class="error" id="OrderNo_error1"></span>
										   <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderCodecr">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind></label><div class="errormsg"></div>
                                       </td>
								</tr>
								<tr>
									<td><label><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message></label><span class="errormsg">*</span><br />
										<label>
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].orderDatecr">							
								        <input type="text"  id="OrderDate1" style="width: 100px"
														onfocus="show_msg('OrderDate1');"
														onblur="validateOrdeDate1();" onchange="setEffectiveDate1(this.value);"
														onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	             </spring:bind></label> <span class="msg" id="OrderDate_msg1"><spring:message htmlEscape="true"  code="Msg.ORDERDATE"></spring:message></span> <span class="error" id="OrderDate_error1"></span>
												 <div class="errormsg">
									</td>

								</tr>
								<tr>
									<td><label><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message></label><span class="errormsg">*</span><br />
										<label>
										  <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].ordereffectiveDatecr">	
										<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>					
								            <input type="text" id="EffectiveDate1"  style="width: 100px"
														onfocus="show_msg('EffectiveDate1');"
														onblur="validateEffecDate1();"
														onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	              
					    	               </spring:bind></label>  <span class="error"
													id="EffectiveDate_error1"></span>
													<div class="errormsg"></div>
									</td>
								</tr>
								<tr>
									<td><label><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message></label><span class="errormsg">*</span><br />
										<label>
												<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].gazPubDatecr">							
								            <input type="text" id="gazPubDatecr" style="width: 100px"
														onfocus="show_msg('gazPubDatecr');"
														onblur="validateGazPubDate1();"
														onkeyup="validateNumeric();"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	              
					    	               </spring:bind></label>             <span class="error"
													id="GazPubDate_error1"></span>
													<div class="errormsg"></div>
									</td>
								</tr>

								 <tr>
									<td><label><spring:message htmlEscape="true"  code="Label.UPLOADGOVTORDER"></spring:message></label><span class="errormsg">*</span><br />
									<form:input id="filGovernmentOrder1" path="filePathcr"	class="frmfield" type="file" size="25"
										onfocus="show_msg('filGovernmentOrder1');" onblur="validateSFile();" /> 
										 <span class="error" id="filGovernmentOrder1_error"></span>
									</td>
								</tr>						
								<tr>
									<td>&nbsp;</td>
								</tr>								
							</table>
                                    </td>
                                    </tr>
                                    
                                   </c:forEach>
                                 </table>
							  </div>
							    <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return validateAllGov1();"   />
                                    </label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
																	id="btnClear" onclick="resetVillageFormCr();" />
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('home.htm');"/>
                                     </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
							</div>
							<div  id='changevillage' class="frmpnlbg"  style="visibility: visible; display:block">
							  <div class="frmtxt">
								  <div class="frmhdtitle"  visibility: hidden;><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								   <c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${modifyStateCmd.listStateDetails}">
                                   
                                    
                                    <tr>
                                      <td width="14%" rowspan="4">&nbsp;</td>
                                      <td width="86%"><label><spring:message htmlEscape="true" 	code="Label.MSTATENAMEENGLISH"></spring:message></label><span class="errormsg">*</span><br />
                                          <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglishch">							
								            <input type="text" class="frmfield" maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" id="OfficialAddress" cssClass="frmfield" onfocus="show_msg('OfficialAddress')" onblur="officialAddress()"/>							
					    	                <span class="msg" id="OfficialAddress_msg">please enter  the State Name in English here</span>
										    <span class="error" id="OfficialAddress_error"></span>
					    	               </spring:bind>
					    	               <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind>
										<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind>
                                          </label>
                                          <div class="errormsg"> <%-- <font color="red"><form:errors htmlEscape="true"  path="villageNameEnglishCh" /></font> --%> </div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.MSTATENAMELOCAL"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameLocalch">							
								           <input type="text" maxlength="50" id="txtNameLocal" onkeypress="validateCharKeys(event)" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
								           </spring:bind>	
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                     <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.ALIASENGLISH"></spring:message></label><br />
                                          <label>
                                         <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].aliasEnglishch">							
							               <input type="text" maxlength="50" id="txtAliasEnglish" onkeypress="validateCharKeys(event)" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>	
                                           </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr> 
                                     <tr>
                                      <td><label><spring:message htmlEscape="true"  code="Label.ALIASLOCAL"></spring:message></label><br />
                                          <label>
                                           <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].aliasLocalch">							
							  				<input type="text" id="txtAliasLocal" maxlength="50" onkeypress="validateCharKeys(event)" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										   </spring:bind>
										    
										  </label>
                                         <!--  <div class="errormsg"></div> --></td>
                                    </tr> 
                                     </c:forEach>
                                  </table>
                              <table width="100%" class="tbl_no_brdr">
								<tr>
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
													<spring:message htmlEscape="true"  code='<c:out value="${validationError}" escapeXml="true"></c:out>'/>
												</c:if>
										</div></td>
									</tr>
										</table></td>
								</tr>

								<tr>
									<td width="14%" rowspan="20">&nbsp;</td>
									<td width="86%"><label><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input path="orderNo"
											id="OrderNo" type="text" class="frmfield"
											onblur="hideErrorMsges();" style="width: 140px"
											onkeypress="validateNumericAlphaKeys();" /> <span
										class="errormsg" id="OrderNo_error"></span>
										<form:errors htmlEscape="true"  path="orderNo" cssClass="errormsg" ></form:errors>
										</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									 <td><label><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input path="orderDate"
											id="OrderDate" type="text" class="frmfield"
											style="width: 140px" onblur="hideErrorMsges();"
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
									<td><label><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" path="ordereffectiveDate" type="text"
											class="frmfield" style="width: 140px"
											onblur="hideErrorMsges();" onkeypress="validateNumeric();" /><span
										class="errormsg" id="EffectiveDate_error"></span>
										<form:errors htmlEscape="true"  path="effectiveDate" cssClass="errormsg" ></form:errors>
										</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input id="GazPubDate"
											path="gazPubDate" type="text" class="frmfield"
											style="width: 140px" onblur="hideErrorMsges();"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="GazPubDate_error"></span> <form:errors htmlEscape="true"  path="gazPubDate"
											cssClass="errormsg"></form:errors>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

								<tr>

									<td><div id="titleId">
									<form:hidden id="showTitle" path="showTitle" name="showTitle" class="frmfield" /></div><div id="hiddentitle"></div><br/>
										<div id="hideAttachmentUtilDiv">
											<div id="fileId">
												<label><spring:message htmlEscape="true"  code="Label.UPLOADGOVTORDER"></spring:message></label>
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
															<!-- 	<spring:message htmlEscape="true"  code="addAttachment.tableheading" /> -->
														</tr>
														<tr class="tblRowTitle tblclear">
															<td><spring:message htmlEscape="true"  code="addAttachment.filetitle" />
															</td>
															<td><spring:message htmlEscape="true"  code="addAttachment.filename" />
															</td>
															<td><spring:message htmlEscape="true"  code="addAttachment.filesize" />
															</td>
															<td><spring:message htmlEscape="true" 
																	code="addAttachment.filecontenttype" /></td>
															<td><spring:message htmlEscape="true" 
																	code="addAttachment.fileflagfordeletion" /></td>
														</tr>
														<c:forEach var="abc"
															items="${Attached_File_Meta_Data_List}">

															<tr id="row${abc.attachmentId}" class="tblRowB">

																<td><c:out value="${abc.fileTitle }" escapeXml="true"></c:out></td>
																<td><c:out value="${abc.fileName }" escapeXml="true"></c:out></td>
																<td><c:out value="${abc.fileSize }" escapeXml="true"></c:out></td>
																<td><c:out value="${abc.fileContentType }" escapeXml="true"></c:out></td>

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
								</tr>
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
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return validateAllGov();"/>
                                    </label>
                                    <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
																	id="btnClear" onclick="resetVillageModifyForm();" />
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
                                  
                                  </td>
                                </tr>
                              </table>
					      </div>
						  </div>
						  
						</form:form>
					</div></div>
</body>
</html>