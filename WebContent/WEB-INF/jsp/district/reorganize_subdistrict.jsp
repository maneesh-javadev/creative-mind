<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
 <script type="text/javascript" src="js/State.js"></script>

<script type="text/javascript" src="js/new_sub_district.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />

<title>Create Sub District</title>
<script type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);
	function selectFinal()
	{
		var selObj=document.getElementById('subDistrictListNew');
		var selObj2=document.getElementById('villageListNew');	
		
		

			var subDistrict="";
			for (var i = 0; i < selObj.options.length; i++) {
			     selObj.options[i].selected=true;
			     subDistrict +=selObj.options[i].value+",";
		 }
			
			for (var j = 0; j < selObj2.options.length; j++) {
				selObj2.options[j].selected=true;
			     subDistrict +=selObj2.options[j].value+",";
		 }
			
		}
</script>

</head>
<body onload="selectpreview()">
	
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td>
				<div id="frmcontent">
						<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                       <%--//this link is not working    <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
                        </tr>
                      </table>
						</div>
						<div class="clear">
						<div class="frmpnlbrdr">
							<form:form action="" method="POST" commandName="newsubdistrictform">
								<div class="frmpnlbg">
									<%-- <div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="App.SELECTDISTRICT"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td width="14%" rowspan="5">&nbsp;</td>

											</tr>

											<tr>
												<td><spring:message htmlEscape="true"  code="App.DISTRICT"></spring:message>
												<strong><span id ="required" class="errormsg">*</span></strong><br />
													<label> <form:select path="districtCode"
															class="frmtxtarea" id="ddDistrict" style="width: 150px"
															onchange="getSubDistrictList(this.value)">
															<form:option value="">Select District</form:option>
															<form:options items="${distList}"
																itemLabel="districtNameEnglish"
																itemValue="districtPK.districtCode" />
														</form:select> 
														</label> 
														
													<div class="errormsg"></div>
												</td>
											</tr>

										</table>
									</div> --%>
								</div>
								<div class="frmpnlbg">
									<div class="frmtxt"
										>
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Legend.GENERALDETAILOFNEWSUBDISTRICT"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="8">&nbsp;</td>
												<td width="86%"><label><spring:message htmlEscape="true" 
														code="Label.NAMEOFNEWSUBDISTRICTENGLISH"></spring:message></label>
														<strong><span id ="required" class="errormsg"></span></strong><br />
													<label>
                                                     <form:input path="subdistrictNameEnglish"  id="OfficialAddress" class="frmfield" onfocus="show_msg('OfficialAddress')" onblur="officialAddress()" />
													</label>
													<form:errors htmlEscape="true"  path="subdistrictNameEnglish" cssClass="errormsg"/>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message htmlEscape="true"  code="Label.NAMEOFNEWSUBDISTRICTLOCAL"></spring:message></label><br />
													<label> <form:input path="subdistrictNameLocal"  class="frmfield" />
												</label>
												<form:errors htmlEscape="true"  path="subdistrictNameLocal" cssClass="errormsg"/> <br />
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message htmlEscape="true"  code="Label.ALIASENGLISH"></spring:message></label><br />
													<label> <form:input
															path="aliasEnglish" class="frmfield"/> </label>
															<form:errors htmlEscape="true"  path="aliasEnglish" cssClass="errormsg"/>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message htmlEscape="true"  code="Label.ALIASLOCAL"></spring:message></label><br />
													<label> <form:input path="aliasLocal" class="frmfield" />
												</label>
												<form:errors htmlEscape="true"  path="aliasLocal" cssClass="errormsg"/>
													<div class="errormsg"></div></td>
											</tr>
											<tr>
												<td><label><spring:message htmlEscape="true"  code="Label.CENSUSCODE2011"></spring:message></label><br />
													<label> <form:input path="census2011Code" class="frmfield" /> </label>
													<form:errors htmlEscape="true"  path="census2011Code" cssClass="errormsg"/>
													<div class="errormsg"></div></td>
											</tr>
											<%-- <tr>
												<td><spring:message htmlEscape="true"  code="App.STATESPECIFICCODE"></spring:message><br />
													<label> <form:input path="sscode"  class="frmfield"/>
												</label>
												<form:errors htmlEscape="true"  path="sscode" cssClass="errormsg"/>
													<div class="errormsg"></div></td>
											</tr> --%>
											
										</table>
									</div>
								</div>
									<%@ include file="../common/gis_nodes.jspf" %>
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Legend.SUBDISTRICTHEADQUARTER"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="2">&nbsp;</td>
												<td width="86%" class="tblclear"/>
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td><label id="headquarterName"><spring:message htmlEscape="true" 
																	code="Label.SUBDISTRICTHEADQUARTERENGLISH"></spring:message>
																	</label>
																	&nbsp;<form:input type="text" class="frmfield" name="headquarterName" path="headquarterName"/>
															<form:errors htmlEscape="true"  path="headquarterName" cssClass="errormsg"/>
															&nbsp;<label id="headquarterNameLocal"><spring:message htmlEscape="true" 
																	code="Label.SUBDISTRICTHEADQUARTERLOCAL"></spring:message>
																	</label>
																	&nbsp;<form:input type="text" class="frmfield" name="headquarterNameLocal" path="headquarterNameLocal"/>
															<form:errors htmlEscape="true"  path="headquarterNameLocal" cssClass="errormsg"/></td>
															<td>&nbsp;</td>
															
														</tr>
											</tr>
										</table>
										</tr>
										</table>
									</div>
								</div>
								<div class="frmpnlbg">
			<%-- 	   <div class="frmtxt">
					   <div  class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message></div>
						   <table width="100%" class="tbl_no_brdr">
                              <tr>
                                <td width="12%" rowspan="2">&nbsp;</td>
                                <td width="88%">
                                <table width="100%" class="tbl_no_brdr">
                                 <tr>
                                    <td width="159"><spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message></td>
                                    <td width="155" align="right"><label id="lbllatitude"><spring:message htmlEscape="true"  code="Label.LATITUDE"></spring:message></label></td>
                                    <td width="75"><form:input path="lati" id="lbllatitude" type="text" class="frmfield"  /></td>
                                    <td width="150" align="right"><label id="lbllongitude"><spring:message htmlEscape="true"  code="Label.LONGITUDE"></spring:message></label></td>
                                    <td width="75"><form:input path="longi" type="text" class="frmfield"  /></td>
                                    <td >&nbsp;</td>
                                    <td width="150" align="right">
                                    <label>
                                               <input type="button" name="Submit3" value="<spring:message htmlEscape="true"  code="Button.ADDNODES"></spring:message>" onclick="addgisnodes()" />
                                    </label></td>
                                  </tr>
                                  </table>
                                  
                                  <table>
                                  <tr>
												<td width="1"></td>
												<td width="50" align="center"></td>
												<td width="50"></td>
												<td width="50" align="center"></td>
												<td width="50"></td>
												<td>&nbsp;</td>				
												<td><div id="addgisnodes"></div></td>
								 </tr>
                               </table>
                              
							   <div class="errormsg"></div>								
							 </td>
                           </tr>
                              <tr>
                                <td>
                                   <table width="468" class="tbl_no_brdr">                                                                                                 	                                
                                  <tr>
                                    <td width="31%"><label><span class="blktxt"><spring:message htmlEscape="true"  code="Label.MAPSUBDISTRICT"></spring:message> </span>
                                          <form:input id="" path="mapUrl"
											type="file" size="25" />
										
                                    </label></td>
                                  </tr> 
                               
                                   </table>
                                   <div class="errormsg"></div>
							   </td>
                            </tr>
                          </table>
						</div>	 --%>					
					</div>
							
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.CONTRIBUTINGLANDREGION"></spring:message>
										</div>
								<table width="100%" class="tbl_no_brdr">
								<tr>
								<td width="14%" rowspan="2">&nbsp;</td>
									<td width="86%" class="tblclear">
									<table width="664" 	class="tbl_no_brdr">

						        <tr>
							    <td><strong><spring:message htmlEscape="true"  code="Label.SUBDISTRICTS"></spring:message></strong></td>
							<td align="center">&nbsp;</td>
							<td><strong><spring:message htmlEscape="true" 
										code="Label.CONTRIBUTINGSUBDISTRICTLIST"></spring:message> </strong></td>
						</tr>
						<tr>
						
							<td width="86%" class="tblclear">
							<form:select name="select9"
									size="1" id="ddSubdistrict" path="subDistrictList"
									multiple="multiple" class="frmtxtarea"
									style="height: 80px; width: 233px">
									<form:options items="${listSD}" itemLabel="subdistrictNameEnglish"
									itemValue="subdistrictCode"/>
							</form:select>
							</td>
							
							<td align="center" style="padding-top: 3px">
							<table width="100%" class="tbl_no_brdr">
									<tr>
										<td align="center"><label> <input type="button"  class="btn"
												id="btnaddSubDistrictFull" name="Submit4"
												value="Select Full Sub Districts&gt;&gt;"
												onclick="addItem('subDistrictListNew','ddSubdistrict','FULL',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button"  class="btn"
												id="btnremoveOneSubDistrict" name="Submit4" value=" &lt; "
												onclick="removeItem('subDistrictListNew','ddSubdistrict',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button"  class="btn"
												id="btnremoveAllSubDistricts" name="Submit4" value="&lt;&lt;"
												onclick="removeAll('subDistrictListNew','ddSubdistrict',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button"  class="btn"
												id="btnaddSubDistrictPart" name="Submit4"
												value="Select Part Sub Districts&gt;&gt;"
												onclick="addItem('subDistrictListNew','ddSubdistrict','PART',true);" />
										</label></td>
									</tr>
								</table></td>
							<td width="246" valign="top">
							<form:select name="select4"
								id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" 
								class="frmtxtarea" style="height: 80px; width: 242px"  >
							</form:select></td>
						</tr>
						<tr>
							<td width="246"><div style="height: 15px; padding-top: 3px;"
									class="errormsg"></div>
							<td width="172"></td>
							<td width="246">
							<label> 
							<input type="button"  class="btn"
									id="partSubdistrict"
									 name="Submit7" onclick="getVillageList(subDistrictListNew.value,subDistrictListNew[subDistrictListNew.selectedIndex].text)"
									value="<spring:message htmlEscape="true"  code="Button.GETPARTSUBDISTRICT"></spring:message>" />
							</label>
							</td>
						</tr>
					</table>
					
					<div class="errormsg"></div>
					
				</td>
			</tr>
		</table>
		<table>
		<tr>
		<td width="19%" rowspan="2">&nbsp;</td>
			<td><strong><spring:message htmlEscape="true"  code="Label.VILLAGES"></spring:message>
			</strong></td>
			<td align="center">&nbsp;</td>
			<td><strong><spring:message htmlEscape="true" 
			code="Label.CONTRIBUTINGVILLAGELISTSD"></spring:message> </strong></td>
			</tr>
		<tr>
		<div id='cvillage' class="frmpnlbg">
					<tr><td width="14%" rowspan="2">&nbsp;</td>
							<td width="246" valign="top">
							<form:select name="select9"
									size="1" id="villageList" path="" htmlEscape="true"
									multiple="multiple" class="frmtxtarea"
									style="height: 80px; width: 233px">
							</form:select>
							</td>
							<td align="center" style="padding-top: 3px">
							<table width="100%" class="tbl_no_brdr">
									<tr>
										<td align="center"><label> <input type="button"
												id="btnaddVillageFull" name="Submit4" 
												value="Select Full Villages&gt;&gt;"
												onclick="addItem('villageListNew','villageList','FULL',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnremoveOneVillage" name="Submit4" value=" &lt; " 
												onclick="removeItem('villageListNew','villageList',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" 
												onclick="removeAll('villageListNew','villageList',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button" class="btn"
												id="btnaddVillagePart" name="Submit4" 
												value="Select Part Villages&gt;&gt;"
												onclick="addItem('villageListNew','villageList','PART',true)" />
										</label></td>
									</tr>
								</table></td>
							<td width="246" valign="top">
							<form:select name="select4"
								id="villageListNew" size="1" multiple="multiple" path="contributedVillages"
								class="frmtxtarea" style="height: 80px; width: 242px" >
								<form:options items="${listVill}" itemLabel="villageNameEnglish"
									itemValue="villageCode"/>
							</form:select></td>
						</tr>

						<%-- <tr>
							<td width="246"><div class="errormsg"></div>
							<td width="172"></td>
							<td width="246"><label> <input type="button" class="btn"
									id="tbnSurveyNumbers"
									 name="Submit7" onclick="getSurveyNobyVillage();"
									value="<spring:message htmlEscape="true"  code="App.GETPARTVILLAGESURVEYNUMBER"></spring:message>" />
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
								<td valign="top"><strong><spring:message htmlEscape="true" 
											code="App.SELECTEDVILLAGESURVEYNUMBER"></spring:message> </strong></td>
								<td width="180" align="center">&nbsp;</td>
								<td><strong><spring:message htmlEscape="true" 
											code="App.CONTRIBUTINGSURVEYNUMBER"></spring:message> </strong></td>
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
													value="<spring:message htmlEscape="true"  code="App.SELECTSURVEYNUMBER"></spring:message>"
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
		<%-- <div class="frmtxt"
			style="position: relative; background: inherit; padding-top: 20px;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="14%">&nbsp;</td>
					<td width="86%" style="padding: 0px">Do you want to create another Subdistrict?<br />
						<table width="104" height="21" border="0" cellpadding="0"
							cellspacing="0">
							 <tr>
								<td width="20" style="padding: 0px"><label> <input
										name="radiobutton" type="radio" id="Yes" onchange="reorganizeYes()" 
										onclick="reorganizeYes()"/> </label></td>
								<td width="37" style="padding: 0px"><spring:message htmlEscape="true" 
										code="App.YES"></spring:message></td>
								<td width="20" style="padding: 0px"><label> <input
										name="radiobutton" type="radio" id="No" value="true" onchange="reorganizeNo()" /> </label></td>
								<td width="27" style="padding: 0px"><spring:message htmlEscape="true" 
										code="App.NO"></spring:message></td>
							</tr> 
						</table>
						<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	  --%>
<div class="btnpnl">
			<table width="100%" class="tbl_no_brdr">
				<tr>
				<input type="hidden" name="coveredLandRegionByULB" id="coveredLandRegionByULB" />
					<td>
					<div >
								<input type="submit" onclick="javascript:selectall();" id="Proceed" style="visibility: hidden"
								value="<spring:message htmlEscape="true"  code="Button.PROCEED"></spring:message>"/> </label>
								
								<label> <input type="submit" onclick="javascript:selectFinal();" id="Submit" 
								value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="selectFinal();" /> </label> 
								<label>
								<input type="button" name="Submit6"
								value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
								onclick="javascript:go('add_district.htm');" /> </label>
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