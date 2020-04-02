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

<title><spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message>
</title>
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
 	
	function selectFinal()
	{
		
		var selObj2=document.getElementById('villageListNew');	
			var subDistrict="";
			for (var j = 0; j < selObj2.options.length; j++) {
				selObj2.options[j].selected=true;
			     subDistrict +=selObj2.options[j].value+",";
		 }
		}
	
</script>

</head>
<body>
	<div id="frmcontent">
						<div class="frmhd">
		<table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message></td>
                       <%--//these links are not working     <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a></td> --%>
                        </tr>
                      </table>
		</div>
					<div class="clear"></div>
						<div class="frmpnlbrdr">
							<form:form action="" method="POST" commandName="addVillage">
								<div class="frmpnlbg">
										<div class="frmhdtitle">
											<spring:message code="Label.GENERALDETAILOFNEWVILLAGE" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="8">&nbsp;</td>
												<td width="86%"><label><spring:message
														code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message></label><span id ="required" class="errormsg">*</span><br />
                                                     <form:input path="newVillageNameEnglish"  class="frmfield" htmlEscape="true"/>
													<span class="error" id="OfficialAddress_error"></span>
													</label>
													<div class="errormsg"><form:errors htmlEscape="true"  path="newVillageNameEnglish" ></form:errors></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message></label><br />
													<label> <form:input path="newVillageNameLocal" class="frmfield"  htmlEscape="true"/>
												</label> <br />
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label><br />
													<label> <form:input
															path="newVillageAliasEnglish"  class="frmfield" htmlEscape="true"/> </label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label><br />
													<label> <form:input path="newVillageAliasLocal"  class="frmfield" htmlEscape="true"/>
												</label>
													<div style="height: 15px; padding-top: 3px;"
														class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message></label><br />
													<label> <select name="select10" class="combofield" 
														style="width: 150px">
															<option>
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</option>
																<option value="R">Reserve Forest</option>
																<option value="U">Un-inhabitant</option>
																<option value="I">Inhabitant</option>
													</select> 
												</label> <br />
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label><br />
													<label> <form:input path="census2011Code" class="frmfield" htmlEscape="true"/> </label>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label><br />
													<label> <form:input path="stateSpecificCode" class="frmfield" htmlEscape="true"/>
												</label>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message code="Label.SURVEYNUMBER" htmlEscape="true"></spring:message></label>
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
								 <div class="frmpnlbg">
									<%-- <div class="frmtxt">
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
															<form:input path="urlpath" class="frmfield" />
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
									</div> --%>
								</div>
								<div id='cvillage' class="frmpnlbg">
									<div class="frmtxt"	>
										<div class="frmhdtitle">
											<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
										</div>
									<table>		
									<tr>
									<td width="19%">&nbsp;</td>
									<td width="81%" class="tblclear">
									
									<table width="664" class="tbl_no_brdr">

						<tr>
							<td><strong><spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message>
							</strong></td>
							<td align="center">&nbsp;</td>
							<td><strong><spring:message
										code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message> </strong></td>
						</tr>
						<tr>
							<td width="246" valign="top">
							<form:select name="select9"
									size="1" id="villageListMain" path="villageList"
									multiple="multiple" class="frmtxtarea" htmlEscape="true"
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
							</form:select></td>
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
    <%-- <div
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
	</div> --%>
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
			<table width="100%" class="tbl_no_brdr">
				<tr>
				<input type="hidden" name="coveredLandRegionByULB" id="coveredLandRegionByULB" />
					<td>
					<div >
								<input type="submit" onclick="javascript:selectall();" id="Proceed" style="visibility: hidden"
								value="<spring:message code="Button.PROCEED"></spring:message>"/> </label>
								
								<label> <input type="submit" onclick="javascript:selectFinal();" id="Submit"
								value="<spring:message code="Button.SAVE"></spring:message>"onclick="selectFinal();"/> </label> 
								<label>
								<input type="button" name="Submit6"
								value="<spring:message code="Button.CLOSE"></spring:message>"
								onclick="javascript:go('new_subdistrict.htm');" /> </label>
						</div>
						</td>
				</tr>
			</table>
		</div>
		</form:form>
		</div>
</div>
</body>
</html>