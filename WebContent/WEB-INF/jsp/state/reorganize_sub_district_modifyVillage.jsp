<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<head>
<title><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
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
			document.getElementById("OfficialAddress_error").innerHTML="Village Name in English is Required";
			$("#OfficialAddress_error").show();
			$("#OfficialAddress_msg").hide();
			$("#OfficialAddress").addClass("error_fld");
			$("#OfficialAddress").addClass("error_msg");
			return true;

		}
		else 
		{
			$("#OfficialAddress_msg").hide();
			return true;
				
		}
	}
	</script>
</head>
<body onload='toggledisplay2("chkchvillage","changevillage")'>
<div id="frmcontent">
					<div class="frmhd">
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                          <td width="50" align="right"><a href="#" class="frmhelp">Help</a></td>
                        </tr>
                      </table>
					</div>
					<div class="clear"></div>
<div class="frmpnlbrdr">
						<form:form action="" method="POST" commandName="modifyVillageCmd" id="frmModifyVillage">
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
                                               <form:radiobutton path="correction" type="radio" id='chkcrvillage' value="true"  htmlEscape="true"
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage")'/>
                                                 </label></td>
                                              <td width="83" class="tblclear"><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message></td>
                                              <td width="20" class="tblclear"><label>
                                                <form:radiobutton path="correction"  type="radio"  id='chkchvillage' value="true" htmlEscape="true"
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
							  <div   id='correctionvillage' style="margin: 20px 20px 0px 20px; background:#F7F7F7; padding: 10px; visibility: hidden; display:none">
							  <div class="frmtxt" >
								  <div class="frmhdtitle"  ><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${modifyVillageCmd.listVillageDetails}">
                                    <tr>
                                      <td width="14%" rowspan="14">&nbsp;</td>
                                      <td><spring:message	code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>   
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message><br />
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
											<input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
										 </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].sscode">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
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
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
								          </spring:bind></td>
                                      		<td><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message><br />
															 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].latitude">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
								          </spring:bind></td>
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
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
                                      	</tr>
                                      </table>
                                                     
									  <div class="errormsg"></div>  </td>
                                    </tr>
                                    <tr>
                                    <td class="tblclear">
                                    <table width="100%" class="tbl_no_brdr" align="center">								
								<tr>
								    
									<td width="80" ><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><br />
									<label>
                                        <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNo">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
					    	                <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>
										</spring:bind></label><div class="errormsg"></div>
                                       </td>
								</tr>
								<tr>
									<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><br />
										<label>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDate">							
								        <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	             </spring:bind></label><div class="errormsg"></div>
									</td>

								</tr>
								<tr>
									<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><br />
										<label>
												<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDate">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind></label><div class="errormsg"></div>
									</td>
								</tr>
								<tr>
									<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><br />
										<label>
												<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].gazPubDate">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind></label><div class="errormsg"></div>
									</td>
								</tr>

								<tr>
									<td><spring:message code="Label.UGO" htmlEscape="true"></spring:message><br />
									<label>
									<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].filePath">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind></label><div class="errormsg"></div>
										<%-- <form:input id="filGovernmentOrder" path="filePath"
											type="file" size="25" /> --%>
										<%-- <span class="msg" id="filGovernmentOrder_msg"><spring:message code="Msg.UPLOADGOVTORDER" htmlEscape="true"></spring:message></span> --%>
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
							</div>
							<div  id='changevillage'  style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: visible; display:block">
							  <div class="frmtxt">
								  <div class="frmhdtitle"  visibility: hidden;><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								   <c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${modifyVillageCmd.listVillageDetails}">
                                   
                                    
                                    <tr>
                                      <td width="14%" rowspan="4">&nbsp;</td>
                                      <td width="86%"><spring:message	code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message><span id ="required">*</span><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglishCh">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" id="OfficialAddress" cssClass="frmfield" onfocus="show_msg('OfficialAddress')" onblur="officialAddress()"/>							
					    	                <span class="msg" id="OfficialAddress_msg">please enter  the Village Name in English here</span>
										    <span class="error" id="OfficialAddress_error"></span>
					    	               </spring:bind>
                                          </label>
                                          <div class="errormsg"> <%-- <font color="red"><form:errors htmlEscape="true"  path="villageNameEnglishCh" /></font> --%> </div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocalCh">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
								           </spring:bind>	
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglishCh">							
							               <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
                                           </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocalCh">							
							  				<input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
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
									<td width="86%"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><br />
                                          <form:input path="orderCode" id="orderCode" type="hidden"
											class="frmfield" />
										<form:input path="orderNo" id="OrderNo" type="text" htmlEscape="true"
											class="frmfield"
											onblur="validateOrdeNo();" onfocus="show_msg('OrderNo');" />
										<span class="msg" id="OrderNo_msg"><spring:message code="Msg.ORDERNO" htmlEscape="true"></spring:message></span>
										<span class="error" id="OrderNo_error"></span>
										<div class="errormsg"> <form:errors htmlEscape="true"  path="orderNo" ></form:errors></div></td>
								</tr>
								<tr>
									<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2"  class="tblclear"><form:input
														path="orderDate" id="OrderDate" type="text"
														class="frmfield" style="width: 100px"
														onfocus="show_msg('OrderDate');" htmlEscape="true"
														onblur="validateOrdeDate();"
														onkeypress="validateNumeric();" /> <script
														language="JavaScript" type="text/javascript">
               
                    function exampleCallback_ISO10(date, month, year)
                    {
                            if (String(month).length == 1) 
                            {
                                    month = '0' + month;
                            }

                            if (String(date).length == 1) 
                            {
                                    date = '0' + date;
                            }    
                        document.getElementById('OrderDate').value = date + '-' + month + '-' +year;
                        document.getElementById('EffectiveDate').value = date + '-' + month + '-' +year;       
                    }
                    calendar10 = new dynCalendar('calendar10', 'exampleCallback_ISO10','<%=contextPath%>/images/');
														calendar10
																.setMonthCombo(true);
														calendar10
																.setYearCombo(true);
														calendar10
																.setYearComboRange(60);
													</script> <span class="msg" id="OrderDate_msg"><spring:message code="Msg.ORDERDATE" htmlEscape="true"></spring:message></span> <span class="error" id="OrderDate_error"></span>
												</td>
											</tr>
										</table>
										<div class="errormsg"></div>
									</td>

								</tr>
								<tr>
									<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><br />
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td colspan="2" class="tblclear"><form:input
														id="EffectiveDate" path="ordereffectiveDate" type="text"
														class="frmfield" style="width: 100px" htmlEscape="true"
														onfocus="show_msg('EffectiveDate');"
														onblur="validateEffecDate();"
														onkeypress="validateNumeric();" /> <script
														language="JavaScript" type="text/javascript">
               
                    function exampleCallback_ISO11(date, month, year)
                    {
                            if (String(month).length == 1) 
                            {
                                    month = '0' + month;
                            }

                            if (String(date).length == 1) 
                            {
                                    date = '0' + date;
                            }    
                        document.getElementById('EffectiveDate').value = date + '-' + month + '-' +year;                        
                    }
                    calendar11 = new dynCalendar('calendar11', 'exampleCallback_ISO11','<%=contextPath%>/images/');
                    calendar11.setMonthCombo(true);
                    calendar11.setYearCombo(true);
                   
													</script> <span class="msg" id="EffectiveDate_msg"><spring:message code="Msg.EFFECTIVEDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="EffectiveDate_error"></span></td>

											</tr>
										</table>
										<div class="errormsg"></div>
									</td>
								</tr>
								<tr>
									<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2" class="tblclear"><form:input
														id="GazPubDate" path="gazPubDate" type="text"
														class="frmfield" style="width: 100px" htmlEscape="true"
														onfocus="show_msg('GazPubDate');"
														onblur="validateGazPubDate();"
														onkeyup="validateNumeric();" /> <script
														language="JavaScript" type="text/javascript">
               
                    function exampleCallback_ISO12(date, month, year)
                    {
                            if (String(month).length == 1) 
                            {
                                    month = '0' + month;
                            }

                            if (String(date).length == 1) 
                            {
                                    date = '0' + date;
                            }                         
                        document.getElementById('GazPubDate').value = date + '-' + month + '-' +year;
                    }
                    calendar12 = new dynCalendar('calendar12', 'exampleCallback_ISO12','<%=contextPath%>/images/');
															calendar12
																	.setMonthCombo(true);
															calendar12
																	.setYearCombo(true);
														</script> <span class="msg" id="GazPubDate_msg"><spring:message code="Msg.GAZPUBDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="GazPubDate_error"></span></td>

											</tr>
										</table>
										<div class="errormsg"></div>
									</td>
								</tr>

								<tr>
									<td><spring:message code="Label.UGO" htmlEscape="true"></spring:message><br />
										<form:input id="filGovernmentOrder" path="filePath"	type="file" />
										<span class="msg" id="filGovernmentOrder_msg"><spring:message code="Msg.UPLOADGOVTORDER" htmlEscape="true"></spring:message></span> <span class="error"
													id="filGovernmentOrder_error"></span>
									</td>
								</tr>
								
								
							</table> 
							  </div>
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
						</form:form>
					</div></div>
</body>
</html>