<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>

<title><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message></title>

<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>

<script src="js/jquery-1.7.js" type="text/javascript"
	language="javascript"></script>
<script src="js/jquery.MultiFile.js" type="text/javascript"
	language="javascript"></script>
<script src="js/attachedFiles.js" type="text/javascript"
	language="javascript"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->


<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>


<script type="text/javascript">
	function loadPage() {
			var mypath = window.location.href;
		
			
			var mySplitResult = mypath.split("&");

			if (mySplitResult[1] != "") {
				var type = mySplitResult[1].replace("type=", "");

				
			} 

		}
	function villageName()
	{
		if(document.getElementById("villagename").value == "" )
		{
			document.getElementById("villagename_error").innerHTML="Village Name in English is Required";
			$("#villagename_error").show();
			$("#villagename_msg").hide();
			$("#villagename").addClass("error_fld");
			$("#villagename").addClass("error_msg");
			return false;

		}
		else 
		{
			$("#subdistrictname_msg").hide();
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
                          <td><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                         <%--//these links are not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					<div class="clear"></div>
					<div class="frmpnlbrdr">
						<form:form action="modifyVillageAction.htm?disturb=${disturb}" method="POST" commandName="modifyVillageCmd" id="frmModifyVillage" enctype="multipart/form-data">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyVillageAction.htm"/>"/>
							  <div class="frmpnlbg">
                                <div class="frmtxt">
                                  <div class="frmhdtitle"><label><spring:message code="Label.MODIFYVILLAGEOPTION" htmlEscape="true"></spring:message></label></div>
                                    <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="86%"><label><spring:message code="Label.MODIFYVILLAGEOPTION" htmlEscape="true"></spring:message></label><br />
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
								  <div class="frmhdtitle"><spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${modifyVillageCmd.listVillageDetails}">
                                    <tr>
                                      <td width="14%" rowspan="14">&nbsp;</td>
                                      <td><label><spring:message	code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">							
								            <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly="readonly" />							
					    	              </spring:bind>   
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly="readonly"/>
								           </spring:bind>	
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">							
							               <input type="text" id="txtaliasEnglish" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly/>	
                                           </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">							
							  				<input type="text" id="txtAliaslocal"  class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly="readonly"/>
										</spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    
                                    <tr>
                                      <label><td width="86%"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].census2011Code">							
											<input type="text" id="txtCensus" onkeypress="validateNumericAlphaKeys();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
										 </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].sscode">							
								           <input type="text" id="txtSscode" maxlength="5" onkeypress="validateNumericKeys(event)"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
								          </spring:bind>
                                        </label>
                                      <div class="errormsg"></div></td>
                                    </tr>
                                     <tr>
                                      <td><label><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></label><br />
                                        <table  width="83%" class="tbl_no_brdr">
                                        <tr>
                                         <td width="13%" id="lbllatitude" ><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message></td>
                                          
                                          <td>
                                         <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].lati">							
								            <input type="text" id="txtlatitude"  name="lati" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	              </spring:bind>
					    	              
                                         </label> </td>  
                                         <td width="14%" id="lbllongitude" ><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message></td>
                                           <td>
                                            <label>
                                             <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].longi">							
								            <input type="text" id="txtLongitude" name="longi" value="<c:out value="${status.value}" escapeXml="true"></c:out>"  />							
					    	                  </spring:bind>
                                           </label><input type="button" name="Submit3" value="<spring:message code="Button.ADDNODES" htmlEscape="true"></spring:message>" onclick="addgisnodes1()" /> </td>
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
                                           <tr> <td colspan="4"><div id="addgisnodes"></div></td></tr>
								
										<%--  <tr>
                                      <td><spring:message code="Label.SUBDISTRICTMAP" htmlEscape="true"></spring:message><br />
                                          <label>
                                          <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].mapUrl">							
								            <input type="text" class="frmfield"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" onfocus="this.blur()" readonly />							
					    	             </spring:bind>
                                          </label>
                                      <div class="errormsg"></div></td>
                                    </tr>   --%>
										 
                                      </table></td></tr>
                                   <%--  <tr>
                                      <td><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
                                      <table class="tbl_no_brdr">
                                      	<tr>
                                      		<td class="tblclear"><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message><br />
															<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].longitude">							
								            <input type="text" id="txtLongitude"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>	
								          </spring:bind><div class="errormsg"></div></td>
                                      		<td class="tblclear"><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message><br />
															 <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].latitude">							
								           <input type="text" id="txtLatitude"   name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>	
								          </spring:bind>
								          <div class="errormsg"></div> </td>
								          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].mapCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
										</spring:bind>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
										</spring:bind>
										</tr>
                                      	
                                      	<tr><td><spring:message code="Label.MAPVILLAGE" htmlEscape="true"></spring:message><br />
										  <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].imagePath">							
								           <input type="text" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" onfocus="this.blur()" readonly/>	
								          </spring:bind>
								           <div class="errormsg"></div></td>
                                      	</tr>
                                      </table>
                                                     
									  
                                    </tr> --%>
                                     <tr>
                                    <td class="tblclear">
                                    <table width="100%" class="tbl_no_brdr" align="center">								
								<tr>
								    
									<td width="80" ><label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
									<label>
                                        <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">							
								            <input type="text" maxlength="20"  onkeypress="validateNumericAlphaKeys();" id="OrderNo1" onblur="validateOrdeNo1();" onfocus="show_msg('OrderNo1');"
								            name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	              
					    	               </spring:bind><span class="msg" id="OrderNo_msg1"><spring:message code="Msg.ORDERNO" htmlEscape="true"></spring:message></span><span class="error" id="OrderNo_error1"></span>
										   <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">							
							  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
										</spring:bind></label><div class="errormsg"></div>
                                       </td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
										<label>
										<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">							
								        <input type="text"  id="OrderDate1" style="width: 100px"
														onfocus="show_msg('OrderDate1');" onchange="setEffectiveDate1(this.value);"
														onblur="validateOrdeDate1();"
														onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	             </spring:bind></label> <span class="msg" id="OrderDate_msg1"><spring:message code="Msg.ORDERDATE" htmlEscape="true"></spring:message></span> <span class="error" id="OrderDate_error1"></span>
												 <div class="errormsg"/>
									</td>

								</tr>
								<tr>
									<td><label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
										<label>
										  <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">	
										<%--   <c:if test="${listStateDetails.ordereffectiveDatecr >}">	 --%>					
								            <input type="text" id="EffectiveDate1"  style="width: 100px"
														onfocus="show_msg('EffectiveDate1');"
														onblur="validateEffecDate1();"
														onkeypress="validateNumeric();" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
					    	              
					    	               </spring:bind></label><span class="msg" id="EffectiveDate_msg1"><spring:message code="Msg.EFFECTIVEDATE" htmlEscape="true"></spring:message></span> <span class="error"
													id="EffectiveDate_error1"></span>
													<div class="errormsg"></div>
									</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
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
									<td><label><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
									<form:input id="filGovernmentOrder1" path="filePathcr"	class="frmfield" type="file" size="25"
										onfocus="show_msg('filGovernmentOrder1');" onblur="validateSFile();" /> 
										<span class="msg" id="filGovernmentOrder_msg">
										<spring:message code="Msg.UPLOADGOVTORDER" htmlEscape="true"></spring:message> </span>
										 <span class="error" id="filGovernmentOrder_error1"></span>
									</td>
								</tr>
								<%--  <tr>
									<td><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message><br />
									<label>
									<spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderPathcr">		
									   <input type="text" id="path" class="frmfield"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />"  />							
					    	               </spring:bind></label><div class="errormsg"></div>
								
									</td>
								</tr>	 --%>					
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
                                    <input type="submit" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateAllGov1();"    />
                                    </label>
                                   <!--  <input type="button" name="Submit3" class="btn" value=<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>
																	id="btnClear" onclick="resetVillageFormCr();" /> -->
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:go('home.htm');"/> </label>
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
                                      <td width="86%"><label><spring:message	code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglishCh">							
								            <input type="text" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" id="villagename" cssClass="frmfield" onfocus="show_msg('villagename')" onblur="villageName()"/>							
					    	               <!--  <span class="msg" id="OfficialAddress_msg">please enter  the Village Name in English here</span> -->
										    <span class="error" id="villagename_error"></span>
										  </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                          <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocalCh">							
								           <input type="text" id="txtNameLocal" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
								           </spring:bind>	
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                         <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglishCh">							
							               <input type="text" id="txtAliasEnglish" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>	
                                           </spring:bind>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                    <tr>
                                      <td><label><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label><br />
                                          <label>
                                           <spring:bind path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocalCh">							
							  				<input type="text" id="txtAliasLocal" onkeypress="validateCharKeys(event)" class="frmfield" maxlength="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
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
                                          <!-- <div class="errormsg"></div> --></td>
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
													<spring:message code='<c:out value="${validationError}" escapeXml="true"></c:out>'/>
												</c:if>
										</div></td>
									</tr>
										</table></td>
								</tr>

							<tr>
									<td width="14%" rowspan="20">&nbsp;</td>
									<td width="86%"><label><spring:message code="Label.ORDERNO" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input path="orderNo"
											id="OrderNo" type="text" class="frmfield"
											onblur="hideErrorMsges();" style="width: 140px"
											onkeypress="validateNumericAlphaKeys();" /> <span
										class="errormsg" id="OrderNo_error"></span>
										<form:errors path="orderNo" cssClass="errormsg" ></form:errors>
										</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									 <td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input path="orderDate"
											id="OrderDate" type="text" class="frmfield"
											style="width: 140px" onblur="hideErrorMsges();"
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="OrderDate_error"></span>
										<form:errors path="orderDate" cssClass="errormsg" ></form:errors>
									</td> 

								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" path="ordereffectiveDate" type="text"
											class="frmfield" style="width: 140px"
											onblur="hideErrorMsges();" onkeypress="validateNumeric();" /><span
										class="errormsg" id="EffectiveDate_error"></span>
										<form:errors path="effectiveDate" cssClass="errormsg" ></form:errors>
										</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br /> <form:input id="GazPubDate"
											path="gazPubDate" type="text" class="frmfield"
											style="width: 140px" onblur="hideErrorMsges();"
											onkeypress="validateNumeric();" /> <span class="errormsg"
										id="GazPubDate_error"></span> <form:errors path="gazPubDate"
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
												<label><spring:message code="Label.UPLOADGOVTORDER" htmlEscape="true"></spring:message></label>
												<span class="errormsg">*</span> <br /> <input type='hidden'
													name='attachmentNumber'
													value='"<%=allowedNoOfAttachment%>"' /> <input type="file"
													name="attachedFile" id="loadfile" class="multi frmfield"
													onblur="hideErrorMsges();"
													maxlength="<%=allowedNoOfAttachment%>" style="width: 280px"
													accept="<%=allowedFileType%>"
													onclick="{return holdTitle()}" /> <span class="errormsg"
													id="loadfile_error"></span>
												<form:errors path="attachedFile" cssClass="errormsg"></form:errors>
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
                                    <input type="submit" name="Submit" class="btn"  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return validateVillage();" />
                                    </label>
                                   <!--  <input type="button" name="Submit3" class="btn" value=<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>
																	id="btnClear" onclick="resetVillageModifyForm();" /> -->
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
					       </div>
						</form:form>
						<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
					</div></div>
</body>
</html>