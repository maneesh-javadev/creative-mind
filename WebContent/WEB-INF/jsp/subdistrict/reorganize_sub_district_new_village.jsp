<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<script type="text/javascript" src="js/cancel.js"></script>

<title><spring:message code="App.CREATENEWVILLAGE" htmlEscape="true"></spring:message>
</title>
 <script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/validation.js"></script>
<script src="js/validation.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
 
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>


<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>

<!-- <link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" /> -->
<title>Create Vilage</title>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	function selectvillage() {
		//selectDistrict();
		var errors = new Array();
		var error = false;
		errors[0] = !validVillage();
		
		
		for ( var i = 0; i < 1; i++)
		{
			
			if (errors[i] == true) {
				
				error = true;
				break;
			}
		}

		if (error == true) {

			showClientSideError();

			return false;
		} else {
			document.forms['form1'].submit();

			return true;
			
		}
		return false;
	}


	function validVillage() {
		var villagename = document.getElementById('villagename');

		if (villagename.value == "")  {
			$("#villagename_error").show();
			return false;
		}
		$("#villagename_error").hide();
		return true;
	}
	function selectall()
	{
        var i;
		var selObj=document.getElementById('villageListMain');	
		var selObj2=document.getElementById('villageListNew');
		for (i = 0; i < selObj.options.length; i++) {
			     selObj.options[i].selected=true;
		 }
		for (i = 0; i < selObj2.options.length; i++) {
			     selObj2.options[i].selected=true;
		 }
	}
	
	function callSaveDraft()
	{
		document.getElementById('form1').action="saveAsDraftVillage.htm";
		document.forms['form1'].submit();
	}
	function hideAll() {
	$("#villagename_error").hide();
	}	
</script>

</head>

	<body onload="hideAll();">
	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>

	<div class="clear"></div>
	<div class="frmhd">
		<table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="App.CREATENEWVILLAGE" htmlEscape="true"></spring:message></td>
                          <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="App.HELP" htmlEscape="true"></spring:message></a></td>
                        </tr>
                      </table>
		</div>
					<div class="clear"></div>
						<div class="frmpnlbrdr">
							<form:form action="reorganize_subdistrict_new_village.htm" method="POST" commandName="addVillageNew" id="form1">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="reorganize_subdistrict_new_village.htm"/>"/>
							
								<div class="frmpnlbg">
									<div class="frmtxt" >
										<div class="frmhdtitle">
											<spring:message code="App.GENERALDETAILOFNEWVILLAGE" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="8">&nbsp;</td>
												<td width="86%"><spring:message
														code="App.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message><span id ="required" class="errormsg">*</span><br />
                                                   
                                                   
                                                   
                                                   <form:input
									path="newVillageNameEnglish" id="villagename"  onkeypress="validateCharKeys(event)"
									onfocus="validateOnFocus('villagename');" 
				                    onblur="vlidateOnblur('villagename','1','15','c');" />
								 
								 <span class="errormsg" id="villagename_error"><spring:message
												code="Error.VILLAGENAMEENGISH" htmlEscape="true"></spring:message></span><form:errors htmlEscape="true" 
										path="newVillageNameEnglish" class="errormsg"></form:errors>
							
                                                   
                                                   
                                                    
													
													<div class="errormsg"><form:errors htmlEscape="true"  path="newVillageNameEnglish" ></form:errors></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message><br />
													<label> <form:input path="newVillageNameLocal" class="frmfield"  />
												</label> <br />
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.ALIASENGLISH" htmlEscape="true"></spring:message><br />
													<label> <form:input
															path="newVillageAliasEnglish"  class="frmfield"/> </label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.ALIASLOCAL" htmlEscape="true"></spring:message><br />
													<label> <form:input path="newVillageAliasLocal"  class="frmfield"/>
												</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.VILLAGESTATUS" htmlEscape="true"></spring:message><br />
													<label> <select name="select10" class="combofield"
														style="width: 150px">
															<option>
																<spring:message code="App.SELECT" htmlEscape="true"></spring:message>
															</option>
																<option value="R">Reserve Forest</option>
																<option value="U">Un-inhabitant</option>
																<option value="I">Inhabitant</option>
													</select> 
												</label> <br />
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.CENSUSCODE2011" htmlEscape="true"></spring:message><br />
													<label> <form:input path="census2011Code" class="frmfield" /> </label>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.STATESPECIFICCODE" htmlEscape="true"></spring:message><br />
													<label> <form:input path="stateSpecificCode" class="frmfield" />
												</label>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><spring:message code="App.SURVEYNUMBER" htmlEscape="true"></spring:message>
													<br /> <input type="text" class="frmfield" name="survey_number" /><label>
														<input type="button" name="Submit32"
														value="Add Survey Number" onclick="addsurveys()" /> </label>
													<div class="errormsg"></div>
														<div id="addsurveys"></div>
														</td>
											</tr>
										</table>
									</div>
								</div>
								<%@ include file="../common/gis_nodes.jspf" %>
								 <%-- <div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="App.GISNODES" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="2">&nbsp;</td>
												<td width="86%" class="tblclear">
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td width="150"><spring:message code="App.GISNODES" htmlEscape="true"></spring:message>
															<td width="190" align="right">	 
															<label id="lbllatitude"><spring:message
																	code="App.LATITUDE" htmlEscape="true"></spring:message></label></td>
															<td width="75"><form:input type="text" class="frmfield" path="latitude" /></td>
															<td width="180" align="right"><label id="lbllongitude"><spring:message
																	code="App.LONGITUDE" htmlEscape="true"></spring:message></label></td>
															<td width="75"><form:input type="text" class="frmfield" path="longitude" /></td>
															<td>&nbsp;</td>
															<td ><label> <input
																	type="button" name="Submit32" value="Add More Nodes" onclick="addgisnodes()" />
															</label>
															</td>
															<tr>
															<td>
																<div id="addgisnodes"></div>
															</td>
															<tr><td><div class="errormsg"></div></td></tr>
														</tr>
															<tr>
															<td align="right"><label>
															<span class="blktxt"><spring:message
																			code="Label.MAPSUBDISTRICT" htmlEscape="true"></spring:message> 
																			</span> </label>
															</td>
															<td>
															<form:input path="mapPath" class="frmfield" />
															<label> 
															</td>
															<td>
															<input type="button" name="Submit332" value="Upload" /> </label>
															</td>
															</tr>
														</tr>
													</table>
													<div class="errormsg"></div></td>
											</tr>
										</table>
									</div>
								</div> --%>
								<div id='cvillage' class="frmpnlbg">
									<div class="frmtxt"	>
										<div class="frmhdtitle">
											<spring:message code="App.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
										</div>
									<table>		
									<tr>
									<td width="19%">&nbsp;</td>
									<td width="81%" class="tblclear">
									
									<table width="664" class="tbl_no_brdr">

						<tr>
							<td><strong><spring:message code="App.VILLAGES" htmlEscape="true"></spring:message>
							</strong></td>
							<td align="center">&nbsp;</td>
							<td><strong><spring:message
										code="App.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message> </strong></td>
						</tr>
						<tr>
							<td width="246" valign="top">
							<form:select name="select9"
									size="1" id="villageListMain" path="villageList"
									multiple="multiple" class="frmtxtarea"
									style="height: 80px; width: 242px">
									<form:options items="${listVill}" itemLabel="villageNameEnglish"
									itemValue="villageCode"/>
							</form:select>
							</td>
							<td align="center" style="padding-top: 3px">
							<table width="100%" class="tbl_no_brdr">
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnaddVillageFull" name="Submit4"
												value="Select Full Villages&gt;&gt;"
												onclick="addItem('villageListNew','villageListMain','FULL',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnremoveOneVillage" name="Submit4" value=" &lt; "
												onclick="removeItem('villageListNew','villageListMain',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;"
												onclick="removeAll('villageListNew','villageListMain',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnaddVillagePart" name="Submit4"
												value="Select Part Villages&gt;&gt;"
												onclick="addItem('villageListNew','villageListMain','PART',true)" />
										</label></td>
									</tr>
								</table></td>
							<td width="246" valign="top">
							<form:select name="select4"
								id="villageListNew" size="1" multiple="multiple" path="contributedVillages" 
								class="frmtxtarea" style="height: 80px; width: 242px" >
							</form:select>
							
							
							<span class="errormsg" id="ddassemblySource_error"></span><form:errors htmlEscape="true" 
										path="contributedVillages" class="errormsg"></form:errors>	
										
							</td>
						</tr>

						<%-- <tr>
							<td width="246"><div class="errormsg"></div>
							<td width="172"></td>
							<td width="246"><label> <input type="button" class="btn"
									id="tbnSurveyNumbers"
									 name="Submit7" onclick="getSurveyNobyVillage();"
									value="<spring:message code="App.GETPARTVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message>" />
							</label></td>
						</tr> --%>
					</table>
					<div class="errormsg"></div>
				</td>
			</tr>
		</table>
	<%-- <div id='csurvey' style="visibility: hidden;">
				<table width="100%" class="tbl_no_brdr">

				<tr>
					<td width="14%">&nbsp;</td>
					<td width="86%" class="tblclear">
					<table width="686" class="tbl_no_brdr">
							<tr>
								<td valign="top"><strong><spring:message
											code="App.SELECTEDVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message> </strong></td>
								<td width="180" align="center">&nbsp;</td>
								<td><strong><spring:message
											code="App.CONTRIBUTINGSURVEYNUMBER" htmlEscape="true"></spring:message> </strong></td>
							</tr>
							<tr>
								<td width="246" valign="top"><form:select name="select5"
									id="surveyListMain" size="1" multiple="multiple" path="surveyList"
									class="frmtxtarea" style="height: 80px; width: 242px">
										<option>survey number3(village2)</option>
										<option>survey number4(village2)</option>
										<option>survey number5(village2)</option>
								</form:select></td>
								<td align="center" style="padding-top: 3px">
								<table width="100%" class="tbl_no_brdr">
										<tr>
											<td align="center"><label> <input type="button"
													name="Submit42" class="btn"
													value="<spring:message code="App.SELECTSURVEYNUMBER" htmlEscape="true"></spring:message>"
													onclick="addItem('surveyListNew','surveyListMain',document.getElementById('surveyListMain').value,false)" />
											</label></td>
										</tr>
										<tr>
											<td align="center"><label> <input type="button" class="btn"
													id="btnremoveOneSurvey" name="Submit4" value=" &lt; "
													onclick="removeItem('surveyListNew','surveyListMain',false)" />
											</label></td>
										</tr>
										<tr>
											<td align="center"><label> <input type="button" class="btn"
													name="Submit42" id="btnremoveAllSurveys" value="&lt;&lt;"
													onclick="removeAll('surveyListNew','surveyListMain',false)" />
											</label></td>
										</tr>

									</table></td>
								<td width="260" valign="top">
								<form:select name="select5"
									id="surveyListNew" size="1" multiple="multiple" path="contributedSurvey"
									class="frmtxtarea" style="height: 80px; width: 242px">
								</form:select></td>
							</tr>


						</table>
						<div class="errormsg"></div></td>
				</tr>
			</table>
		</div> --%>
	</div>
    </div>
    <div
		style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px">
		<div class="frmtxt"
			style="position: relative; background: inherit; padding-top: 20px;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="14%">&nbsp;</td>
					<td width="86%" style="padding: 0px">Do you want to create another village?<br />
						<table width="104" height="21" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="20" style="padding: 0px"><label> <input
										name="radiobutton" type="radio" id="Yes" onchange="reorganizeYes()" 
										onclick="reorganizeYes()"/> </label></td>
								<td width="37" style="padding: 0px"><spring:message
										code="App.YES" htmlEscape="true"></spring:message></td>
								<td width="20" style="padding: 0px"><label> <input
										name="radiobutton" type="radio" id="No" value="true" onchange="reorganizeNo()" /> </label></td>
								<td width="27" style="padding: 0px"><spring:message
										code="App.NO" htmlEscape="true"></spring:message></td>
							</tr>
						</table>
						<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	  <%-- <div class="frmpnlbg">
		<div class="frmtxt">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td width="14%">&nbsp;</td>
					<td width="86%" class="tblclear"><spring:message
							code="App.VILLAGERETAINSAMENAME" htmlEscape="true"></spring:message><br />
						<table width="104" height="21"  class="tbl_no_brdr">
							<tr>
								<td width="20" style="padding: 0px"><label> <input
										name="radiobutton" type="radio" value="radiobutton" /> </label></td>
								<td width="37" style="padding: 0px"><spring:message
										code="App.YES" htmlEscape="true"></spring:message></td>
								<td width="20" style="padding: 0px"><label> <input
										name="radiobutton" type="radio" value="radiobutton" /> </label></td>
								<td width="27" style="padding: 0px"><spring:message
										code="App.NO" htmlEscape="true"></spring:message></td>
							</tr>
						</table>
						<div class="errormsg"></div>
					</td>
				</tr>
			</table>
		</div>
	</div> --%>
<!-- </div> -->
<div class="btnpnl">
<input type="hidden" name="coveredLandRegionByULB" id="coveredLandRegionByULB" />
			<table width="100%" class="tbl_no_brdr">
				<tr>
				
					<td>
					<div >
					<label>
								<input type="submit" onclick="javascript:selectall();" id="Proceed" style="visibility: hidden"
								value="<spring:message code="App.PROCEED"></spring:message>"/> </label>
								<label>
								<input type="button" name="Submit6"
								value="<spring:message code="App.CANCEL"></spring:message>"
								onclick="javascript:location.href='new_subdistrict.htm?<csrf:token uri='new_subdistrict.htm'/>'" /> </label>
								
								<label>
								<input type="button" class="btn" id="Submit" style="visibility: hidden"
								value="<spring:message code="Button.SAVE"></spring:message>"
								onclick="selectall();selectvillage();"/> 
								</label>
								
								
							
								
								<%-- label> <input type="button" onclick="javascript:selectall();" id="Submit" style="visibility: hidden"
								value="<spring:message code="App.SAVE"></spring:message>"onclick="selectvillage();"/>  </label>  --%>
						</div>
						</td>
				</tr>
			</table>
		</div>
		</form:form>
		</div>

</body>
</html>