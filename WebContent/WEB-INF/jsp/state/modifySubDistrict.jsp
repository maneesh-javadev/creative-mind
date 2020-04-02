<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>

<head>
<title><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></title>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script>function show_msg(Field_msg)
{
	var hint='#'+Field_msg+"_msg";
	var error='#'+Field_msg+"_error";
	$("#"+Field_msg).removeClass("error_fld");
	$("#"+Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();
		
}

function officialAddress()
{

	if(document.getElementById("OfficialAddress").value == "" )
	{
		document.getElementById("OfficialAddress_error").innerHTML="Sub District Name in English is Required";
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
}</script>
</head>
 
<body onload='toggledisplay2("chkchvillage","changevillage")'>
 		<div id="frmcontent">
					<div class="frmhd">
					 
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message> </td>
                          <td>&nbsp;</td>
                       <%--//these links are not working     <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					
					<div class="frmpnlbrdr">
					
					<form:form action="reorganize_SubDistrict_Modify.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict" enctype="multipart/form-data">
							  <div class="frmpnlbg">
							 
                                <div class="frmtxt">
                                  <div class="frmhdtitle"><spring:message code="Label.MODIFYSUBDISTRICTOPTION" htmlEscape="true"></spring:message></div>
                                
                                  <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="86%"><br />
                                          <table width="259" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td width="20" height="25" class="tblclear"><label>
                                                <form:radiobutton path="correction" type="radio" id='chkcrvillage' value="true" htmlEscape="true"
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
							  <div id='correctionvillage' class="frmpnlbg"  style="visibility: hidden; display:block">
							  <div class="frmtxt">
								  <div class="frmhdtitle"><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
                                    <tr>
                                      <td width="14%" rowspan="14">&nbsp;</td>
                                      <td><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
					    	               <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
                                         </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
                                         </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.SUBDISTRICTALIASENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.SUBDISTRICTALIASLOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	               </spring:bind>
                                         </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                      <tr>
                                      <td><spring:message code="Label.HEADQUARTER" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              </spring:bind>
					    	               <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
										   <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
										  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
                                         
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>  
									                                
                                    <tr>
                                      <td width="86%"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].census2011Code">							
								            <input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">							
								            <input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />							
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                     <tr>
                                      <td><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message><br />
                                        <table class="tbl_no_brdr">
                                        <tr>
                                         <td class="tblclear" ><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message><br/> 
                                         <label>
                                          <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].mapLandregionCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
										  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
										  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
										  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_code">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
										  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_version">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										  </spring:bind>
                                          <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].lati">							
								            <input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	              </spring:bind>
                                         </label>    
                                            <div class="errormsg"></div></td>
                                             
                                            <td width="171" ><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message><br/>
                                            <label>
                                             <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].longi">							
								            <input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	                  </spring:bind>
                                           </label> 
                                           <div class="errormsg"></div></td>
                                         
                                         
                                        </tr>
										 <tr>
                                      <td><spring:message code="Label.SUBDISTRICTMAP" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].mapUrl">							
								            <input type="text" class="frmfield"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>  
										 
                                      </table>
									 	 
							<table width="100%" class="tbl_no_brdr" align="center">
								
								<tr>
									<td ><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><br />
									<label>
                                        <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNo">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind>
					    	                <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind></label><div class="errormsg"></div>
                                       </td>
								</tr>
								<tr>
									<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><br />
										<label>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDate">							
								        <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	             </spring:bind></label><div class="errormsg"></div>
									</td>

								</tr>
								<tr>
									<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><br />
										<label>
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDate">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind></label><div class="errormsg"></div>
									</td>
								</tr>
								<tr>
									<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><br />
										<label>
												<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].gazPubDate">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind></label><div class="errormsg"></div>
									</td>
								</tr>

								<tr>
									<td><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><br />
									<label>
									<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderPath">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />							
					    	              
					    	               </spring:bind></label><div class="errormsg"></div>
										</tr>
								<tr>
									<td>&nbsp;</td>

								</tr>
								
							</table>		
                                    
                                </c:forEach>
                                  </table>
							  </div>
							</div>
							<div   id='changevillage' class="frmpnlbg"   style="visibility: visible; display:block">
							  <div class="frmtxt">
								  <div class="frmhdtitle"  visibility: hidden;><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
                                    <tr>
                                      <td width="14%" rowspan="4">&nbsp;</td>
                                      <td width="86%"><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglishch">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"   id="OfficialAddress" cssClass="frmfield" onfocus="show_msg('OfficialAddress')" onblur="officialAddress()"  />							
					    	                 <span class="msg" id="OfficialAddress_msg">Please Enter  the Sub District Name in English here</span>
										    <span class="error" id="OfficialAddress_error"></span>							
					    	               </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocalch">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	               </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.SUBDISTRICTALIASENGLISH" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglishch">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	                </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><spring:message code="Label.SUBDISTRICTALIASLOCAL" htmlEscape="true"></spring:message><br />
                                          <label>
                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocalch">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	              
					    	               </spring:bind>
					    	               <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
											<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_code">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind>
										<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_version">							
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
									<td width="86%"><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
                                          <form:input path="orderCode" id="orderCode" type="hidden"
											class="frmfield"  />
										<form:input path="orderNo" id="OrderNo" type="text"
											class="frmfield" 
											onblur="validateOrdeNo();" onfocus="show_msg('OrderNo');" />
										<span class="msg" id="OrderNo_msg"><spring:message code="Msg.ORDERNO" htmlEscape="true"></spring:message></span><span class="error" id="OrderNo_error"></span>
										<div class="errormsg"> <form:errors htmlEscape="true"  path="orderNo" ></form:errors></div></td>
								</tr>
								<tr>
									<td><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2" ><form:input
														path="orderDate" id="OrderDate" type="text"
														class="frmfield"
														onfocus="show_msg('OrderDate');"
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
												<div class="errormsg"> <form:errors htmlEscape="true"  path="orderDate" ></form:errors></div>
												</td>
											</tr>
										</table>
										</td>

								</tr>
								<tr>
									<td><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td colspan="2" ><form:input
														id="EffectiveDate" path="ordereffectiveDate" type="text"
														class="frmfield" onfocus="show_msg('EffectiveDate');"
														onblur="validateEffecDate();" htmlEscape="true"
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
													id="EffectiveDate_error"></span>
													<div class="errormsg"> <form:errors htmlEscape="true"  path="ordereffectiveDate" ></form:errors></div>
													</td>

											</tr>
										</table>
										</td>
								</tr>
								<tr>
									<td><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td colspan="2"><form:input
														id="GazPubDate" path="gazPubDate" type="text"
														class="frmfield" 
														onfocus="show_msg('GazPubDate');" htmlEscape="true"
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
													id="GazPubDate_error"></span>
													<div class="errormsg"><form:errors htmlEscape="true"  path="gazPubDate" ></form:errors></div>
													</td>

											</tr>
										</table>
										
									</td>
								</tr>

								<tr>
									<td><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
										<form:input id="filGovernmentOrder" path="filePath"	class="frmfield" type="file" size="25" htmlEscape="true"
										onfocus="show_msg('filGovernmentOrder');" onblur="validateSFile();" /> 
										<span class="msg" id="filGovernmentOrder_msg">
										<spring:message code="Msg.UPLOADGOVTORDER" htmlEscape="true"></spring:message> </span>
										 <span class="error" id="filGovernmentOrder_error"></span>
									</td>
								</tr>
																
							</table>                                    
                                            
                                  
							  </div>
						  </div>
						    <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td><label>
                                    <input type="submit" class="btn" name="Submit" value="<spring:message code="Button.UPDATE"></spring:message>"/>
                                    </label>
                                     <!--  <label>
                                      <input type="reset" name="Reset" value="Save &amp; Publish" />
                                      </label> -->
                                      <label>
                                      <input type="button" class="btn" name="Submit3" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                                      </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
						
					</div>
				</div>		
		</form:form>

					
</body>
</html>