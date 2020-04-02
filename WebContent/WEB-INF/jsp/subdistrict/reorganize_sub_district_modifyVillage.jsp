<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>
<head>
<title><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<!-- <link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" />
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	 -->
	<script type="text/javascript">

		function loadPage() {
			var mypath = window.location.href;

		
			
			var mySplitResult = mypath.split("&");

			if (mySplitResult[1] != "") {
				var type = mySplitResult[1].replace("type=", "");

				
				
				
			} 

		}
	</script>
	
</head>
<body onload='toggledisplay2("chkchvillage","changevillage"); onLoadSelectDisturbed();'>
<div id="frmcontent">
					<div class="frmhd">
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                         <%--//these links are not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					<div class="clear"></div>
					<div class="frmpnlbrdr">
						<form:form action="reorganize_subdistrict_modify_village.htm" method="POST" commandName="modifyVillageCmd" id="frmModifyVillage" enctype="multipart/form-data">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="reorganize_subdistrict_modify_village.htm"/>"/>
							  <div class="frmpnlbg">
                                <div class="frmtxt">
                                  <div class="frmhdtitle"><spring:message code="Label.MODIFYVILLAGEOPTION" htmlEscape="true"></spring:message></div>
                                    <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="86%"><spring:message code="Label.MODIFYVILLAGEOPTION" htmlEscape="true"></spring:message><br />
                                          <table width="259" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td width="20" height="25" class="tblclear"><label>
                                               <form:radiobutton path="correction" type="radio" id='chkcrvillage' value="true" 
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage")'/>
                                                 </label></td>
                                              <td width="83" class="tblclear"><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></td>
                                              <td width="20" class="tblclear"><label>
                                                <form:radiobutton path="correction"  type="radio"  id='chkchvillage' value="false"
                                                 onclick='toggledisplay2("chkchvillage","changevillage")' />
                                              </label></td>
                                              <td width="136" class="tblclear"><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message></td>
                                            </tr>
                                          </table>
                                      <div class="errormsg"> </div></td>
                                    </tr>
                                  </table>
                                </div>
						      </div>
							  <div   id='correctionvillage' class="frmpnlbg" style=" visibility: hidden; display:none">
							  <div class="frmtxt" >
								  <div class="frmhdtitle"  ><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${modifyVillageCmd.listVillageDetails}">
                                    <tr>
                                      <td width="14%" rowspan="14">&nbsp;</td>
                                      <td><spring:message	code="Label.MVILLAGENAMEENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              </spring:bind>   
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.MVILLAGENAMELOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly/>
								           </spring:bind>	
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">							
							               <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly/>	
                                           </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">							
							  				<input type="text"  class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly/>
										</spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    
                                    <tr>
                                      <td width="86%"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].census2011Code">							
											<input type="text" onkeypress="validateNumericKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
										 </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].sscode">							
								           <input type="text" onkeypress="validateNumericKeys(event)"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
								          </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
                                      <table class="tbl_no_brdr">
                                      	<tr>
                                      		<td class="tblclear"><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message><br />
															<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].longitude">							
								            <input type="text" onkeypress="validateNumericKeys(event)"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
								          </spring:bind><div class="errormsg"></div></td>
                                      		<td class="tblclear"><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message><br />
															 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].latitude">							
								           <input type="text" onkeypress="validateNumericKeys(event)"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
								          </spring:bind>
								          <div class="errormsg"></div> </td>
								          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].mapCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind>
										</tr>
                                      	
                                      	<%-- <tr><td><spring:message code="Label.MAPVILLAGE" htmlEscape="true"></spring:message><br />
										  <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].imagePath">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" onfocus="this.blur()" readonly/>	
								          </spring:bind>
								           <div class="errormsg"></div></td>
                                      	</tr> --%>
                                      </table>
                                                     
									  
                                    </tr>
                                     <tr>
                                    <td class="tblclear">
                                    <table width="100%" class="tbl_no_brdr" align="center">								
								<tr>
								    
									<td width="80" ><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
									<label>
                                        <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">							
								            <input type="text"  onkeypress="validateNumericAlphaKeys();" id="OrderNo1" onblur="validateOrdeNo1();" onfocus="show_msg('OrderNo1');"
								            name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	              
					    	               </spring:bind><span class="msg" id="OrderNo_msg1"><spring:message code="Msg.ORDERNO" htmlEscape="true"></spring:message></span><span class="error" id="OrderNo_error1"></span>
										   <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind></label><div class="errormsg"></div>
                                       </td>
								</tr>
								<tr>
									<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<label>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">							
								        <input type="text"  id="OrderDate1" style="width: 100px"
														onfocus="show_msg('OrderDate1');"
														onblur="validateOrdeDate1();"
														onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	             </spring:bind></label><span class="msg" id="OrderDate_msg1"><spring:message code="Msg.ORDERDATE" htmlEscape="true"></spring:message></span> <span class="error" id="OrderDate_error1"></span>
												 <div class="errormsg">
									</td>

								</tr>
								<tr>
									<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<label>
										  <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">	
										<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>					
								            <input type="text" id="EffectiveDate1"  style="width: 100px"
														onfocus="show_msg('EffectiveDate1');"
														onblur="validateEffecDate1();"
														onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	              
					    	               </spring:bind></label> <span class="msg" id="EffectiveDate_msg1"><spring:message code="Msg.EFFECTIVEDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="EffectiveDate_error1"></span>
													<div class="errormsg"></div>
									</td>
								</tr>
								<tr>
									<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<label>
												<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].gazPubDatecr">							
								            <input type="text" id="gazPubDatecr" style="width: 100px"
														onfocus="show_msg('gazPubDatecr');"
														onblur="validateGazPubDate1();"
														onkeyup="validateNumeric();"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	              
					    	               </spring:bind></label>
					    	                <span class="msg" id="GazPubDate_msg1"><spring:message code="Msg.GAZPUBDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="GazPubDate_error1"></span>
													<div class="errormsg"></div>
									</td>
								</tr>

								 <tr>
									<td><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><br />
									<label>
									<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderPathcr">		
									   <input type="text" class="frmfield"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	               </spring:bind></label><div class="errormsg"></div>
								
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
                                    <input type="submit" name="Submit" class="btn" value="<spring:message code="App.UPDATE" htmlEscape="true"></spring:message>" />
                                    </label>
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message code="App.CANCEL" htmlEscape="true"></spring:message>" onclick="javascript:go('new_subdistrict.htm');"/> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
							</div>
							<div  id='changevillage'  class="frmpnlbg" style="visibility: visible; display:block">
							  <div class="frmtxt">
								  <div class="frmhdtitle"  visibility: hidden;><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								   <c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${modifyVillageCmd.listVillageDetails}">
                                   
                                    
                                    <tr>
                                      <td width="14%" rowspan="4">&nbsp;</td>
                                      <td width="86%"><spring:message	code="Label.MVILLAGENAMEENGLISH" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglishCh">							
								            <input type="text" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" id="OfficialAddress" cssClass="frmfield" onfocus="show_msg('OfficialAddress')" onblur="officialAddress()"/>							
					    	                <span class="msg" id="OfficialAddress_msg">please enter  the Village Name in English here</span>
										    <span class="error" id="OfficialAddress_error"></span>
										  </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.MVILLAGENAMELOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocalCh">							
								           <input type="text" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
								           </spring:bind>	
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglishCh">							
							               <input type="text" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
                                           </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocalCh">							
							  				<input type="text" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                     </c:forEach>
                                  </table>
                                  <table width="100%" class="tbl_no_brdr" align="center">
								<tr>
									<td width="14%" rowspan="9">&nbsp;</td>
									<td width="86%"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><span class="errormsg">*</span> <br />
                                          <form:input path="orderCode" id="orderCode" type="hidden"
											class="frmfield" />
										<form:input path="orderNo" id="OrderNo" type="text" onkeypress="validateNumericKeys(event)"  htmlEscape="true"
											class="frmfield"
											onblur="validateOrdeNo();" onfocus="show_msg('OrderNo');" />
										<span class="msg" id="OrderNo_msg"><spring:message code="Msg.ORDERNO" htmlEscape="true"></spring:message></span><span class="error" id="OrderNo_error"></span>
										
										<div class="errormsg"> <form:errors htmlEscape="true"  path="orderNo" ></form:errors></div></td>
								</tr>
								<tr>
									<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span> <br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2"  class="tblclear"><form:input
														path="orderDate" id="OrderDate" type="text"
														class="frmfield"
														onfocus="show_msg('OrderDate');"  htmlEscape="true"
														onblur="validateOrdeDate();"
														onkeypress="validateNumeric();" /> <span class="msg" id="OrderDate_msg"><spring:message code="Msg.ORDERDATE" htmlEscape="true"></spring:message></span> <span class="error" id="OrderDate_error"></span>
												 <div class="errormsg">  <form:errors htmlEscape="true"  path="orderDate" ></form:errors></div></td>
											</tr>
										</table>
										 
										
									</td>

								</tr>
								<tr>
									<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span> <br />
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td colspan="2" class="tblclear"><form:input
														id="EffectiveDate" path="ordereffectiveDate" type="text"
														class="frmfield"
														onfocus="show_msg('EffectiveDate');"  htmlEscape="true"
														onblur="validateEffecDate();"
														onkeypress="validateNumeric();" /> <span class="msg" id="EffectiveDate_msg"><spring:message code="Msg.EFFECTIVEDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="EffectiveDate_error"></span>
													<div class="errormsg"> <form:errors htmlEscape="true"  path="ordereffectiveDate" ></form:errors></div>
													</td>

											</tr>
										</table>
										 
										
									</td>
								</tr>
								<tr>
									<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span> <br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2" class="tblclear"><form:input
														id="GazPubDate" path="gazPubDate" type="text"
														class="frmfield"
														onfocus="show_msg('GazPubDate');"  htmlEscape="true"
														onblur="validateGazPubDate();"
														onkeyup="validateNumeric();" />  <span class="msg" id="GazPubDate_msg"><spring:message code="Msg.GAZPUBDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="GazPubDate_error"></span>
													<div class="errormsg"> <form:errors htmlEscape="true"  path="gazPubDate" ></form:errors></div>
													</td>

											</tr>
										</table>
											  
										
									</td>
								</tr>

								<tr>
									<td><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<form:input id="filGovernmentOrder" path="filePath"	class="frmfield" type="file" size="25"  htmlEscape="true"
										onfocus="show_msg('filGovernmentOrder');" onblur="validateSFile();" /> 
										<span class="msg" id="filGovernmentOrder_msg">
										<spring:message code="Msg.UPLOADGOVTORDER" htmlEscape="true"></spring:message> </span>
										 <span class="error" id="filGovernmentOrder_error"></span>
									</td>
								</tr>
							</table> 
							  </div>
						 
						  <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                               <tr>
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn" value="<spring:message code="App.UPDATE"></spring:message>" />
                                    </label>
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message code="App.CANCEL"></spring:message>" onclick="javascript:go('new_subdistrict.htm');"/> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
					       </div>
						</form:form>
					</div></div>
</body>
</html>