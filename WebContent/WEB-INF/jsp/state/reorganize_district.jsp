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
<title><spring:message code="Title.CREATENEWSUBDISTRICT" htmlEscape="true"></spring:message></title>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<!-- <script type="text/javascript" src="js/ createState.js"></script>
 --> <script type="text/javascript" src="js/common.js"></script>
 <script type="text/javascript" src="js/State.js"></script>

<script type="text/javascript" src="js/common.js"></script>

<script src="js/jquery.js"></script>
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

<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
dwr.engine.setActiveReverseAjax(true);<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	/* function selectFinal()
	{
		var selObj=document.getElementById('subDistrictListNew');
		var selObj1=document.getElementById('ddDestDistrict');
		var selObj2=document.getElementById('villageListNew');	
		var selObj3=document.getElementById('surveyListNew');
		
		

			var subDistrict="";
			for (var i = 0; i < selObj.options.length; i++) {
			     selObj.options[i].selected=true;
			     subDistrict +=selObj.options[i].value+",";
		 }
			for (var j = 0; j < selObj1.options.length; j++) {
				selObj1.options[j].selected=true;
			     subDistrict +=selObj1.options[j].value+",";
		 }
			for (var j = 0; j < selObj2.options.length; j++) {
				selObj2.options[j].selected=true;
			     subDistrict +=selObj2.options[j].value+",";
		 }
			for (var j = 0; j < selObj3.options.length; j++) {
				selObj3.options[j].selected=true;
			     subDistrict +=selObj3.options[j].value+",";
		 }
		} */
</script>

</head>
<body onload="load_page()">
	
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td>
				<div id="frmcontent">
						<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message code="Label.CREATEDISTRICT" htmlEscape="true"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                      <%--//these links are not working      <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a></td> --%>
                        </tr>
                      </table>
						</div>
						<div class="clear">
						<div class="frmpnlbrdr">
							<form:form action="" method="POST" commandName="districtReorganize">
								<div class="frmpnlbg">
									<%-- <div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="App.SELECTDISTRICT" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">

											<tr>
												<td width="14%" rowspan="5">&nbsp;</td>

											</tr>

											<tr>
												<td><spring:message code="App.DISTRICT" htmlEscape="true"></spring:message>
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
											<spring:message code="Label.GENERALDETAILNEWDISTRICT" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
							<td width="14%" rowspan="8">&nbsp;</td>
							<td width="86%"><label><spring:message
									code="Label.DISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></label><span
								id="required"></span><br /> <form:input
									path="districtNameInEn" id="districtNameInEn" onkeypress="validateCharKeys(event)" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('districtNameInEn')"
									onblur="NameInEn()" /> <span class="msg" id="districtNameInEn_msg">Please enter the District name in english</span>
							<span class="error" id="districtNameInEn_error"></span><span><form:errors htmlEscape="true"  path="districtNameInEn" class="errormsg"></form:errors></span>	 
							</td>
						</tr>


						<tr>
							<td width="86%"><label><spring:message code="Label.DISTRICTNAMELOCAL" htmlEscape="true"></spring:message></label><br /> <form:input
									path="districtNameInLcl" id="districtNameInLcl" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('districtNameInLcl')"  onkeypress="validateCharKeys(event)"
									onblur="NameInLcl()" /> <!-- <span class="msg" id="districtNameInLcl_msg">Please enter  Name of new District (In English) here</span>
							<span class="error" id="districtNameInLcl_error"></span> -->
							</td>
						</tr>

						<tr>
							<td width="86%"><label><spring:message
									code="Label.DISTRICTALIASENGLISH" htmlEscape="true"></spring:message></label><br /> <form:input
									path="districtAliasInEn" id="districtAliasInEn"  onkeypress="validateCharKeys(event)"
									cssClass="frmfield" onfocus="show_msg('districtAliasInEn')" htmlEscape="true"
									onblur="AliasInEn()" /> <!-- <span class="msg" id="districtAliasInEn_msg">Please enter  Name of Alias of the district(In English) here</span>
							<span class="error" id="districtAliasInEn_error"></span> -->
							</td>
						</tr>


						<tr>
							<td width="86%"><label><spring:message
									code="Label.DISTRICTALIASLOCAL" htmlEscape="true"></spring:message></label><br /> <form:input
									path="districtAliasInLcl" id="districtAliasInLcl" onkeypress="validateCharKeys(event)"
									cssClass="frmfield" onfocus="show_msg('districtAliasInLcl')" htmlEscape="true"
									onblur="AliasInLcl()" /> <!-- <span class="msg" id="districtAliasInLcl_msg">Alias of the district (In Local language) here</span>
							<span class="error" id="districtAliasInLcl_error"></span> -->
							</td>
						</tr>

						

						<tr>
							<td width="86%"><label><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label><br /> <form:input path="census2011Code"  onkeypress="validateNumericKeys(event)"
									id="census2011Code" cssClass="frmfield" htmlEscape="true"
									onfocus="show_msg('census2011Code')" onblur="Cns2011Code()" />
								<!-- 	<span class="msg" id="census2011Code_msg">Please enter  Census2011 Code here</span>
							<span class="error" id="census2011Code_error"></span> -->
							</td>
						</tr>


						
											
										</table>
									</div>
								</div>
								<%@ include file="../common/gis_nodes.jspf" %>
								<div class="frmpnlbg">
								
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.HEADQUARTERSDISTRICT" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="2">&nbsp;</td>
												<td width="86%" class="tblclear"/>
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td><label id="headquarterName"><spring:message
																	code="Label.SUBDISTRICTHEADQUARTERENGLISH" htmlEscape="true"></spring:message>
																	</label>
																	&nbsp;<form:input type="text" class="frmfield" name="headquarterName" htmlEscape="true" path="headquarterName"/>
															<form:errors htmlEscape="true"  path="headquarterName" cssClass="errormsg"/>
															&nbsp;<label id="headquarterNameLocal"><spring:message
																	code="Label.SUBDISTRICTHEADQUARTERLOCAL" htmlEscape="true"></spring:message>
																	</label>
																	&nbsp;<form:input type="text" class="frmfield" name="headquarterNameLocal" htmlEscape="true" path="headquarterNameLocal"/>
															<form:errors htmlEscape="true"  path="headquarterNameLocal" cssClass="errormsg"/></td>
															<td>&nbsp;</td>
															
														</tr>
											</tr>
										</table>
										</tr>
										</table>
									</div>
								</div>
								<%-- <div class="frmpnlbg">
				   <div class="frmtxt">
					   <div  class="frmhdtitle"><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></div>
						   <table width="100%" class="tbl_no_brdr">
                              <tr>
                                <td width="12%" rowspan="2">&nbsp;</td>
                                <td width="88%">
                                <table width="100%" class="tbl_no_brdr">
                                 <tr>
                                    <td width="159"><spring:message code="Label.GISNODES" htmlEscape="true"></spring:message></td>
                                    <td width="155" align="right"><label id="lbllatitude"><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message></label></td>
                                    <td width="75"><form:input path="lati" id="lbllatitude" type="text" class="frmfield"  /></td>
                                    <td width="150" align="right"><label id="lbllongitude"><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message></label></td>
                                    <td width="75"><form:input path="longi" type="text" class="frmfield"  /></td>
                                    <td >&nbsp;</td>
                                    <td width="150" align="right">
                                    <label>
                                               <input type="button" name="Submit3" value="<spring:message code="Button.ADDNODES" htmlEscape="true"></spring:message>" onclick="addgisnodes()" />
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
                                    <td width="31%"><label><span class="blktxt"><spring:message code="Label.MAPSUBDISTRICT" htmlEscape="true"></spring:message> </span>
                                          <form:input id="" path="mapUrl"
											type="file" size="25" />
										
                                    </label></td>
                                  </tr> 
                               
                                   </table>
                                   <div class="errormsg"></div>
							   </td>
                            </tr>
                          </table>
						</div>						
					</div> --%>
							<%-- <div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.HEADQUARTERSDISTRICT" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="2">&nbsp;</td>
												<td width="86%" class="tblclear"/>
												<table width="100%" class="tbl_no_brdr">
														<tr>
															<td><label id="headquarterName"><spring:message
																	code="Label.SUBDISTRICTHEADQUARTERENGLISH" htmlEscape="true"></spring:message>
																	</label>
																	&nbsp;<form:input type="text" class="frmfield" name="headquarterName" path="headquarterName" onkeypress="validateCharKeys(event)"  />
															<form:errors htmlEscape="true"  path="headquarterName" cssClass="errormsg"/>
															&nbsp;<label id="headquarterNameLocal"><spring:message
																	code="Label.SUBDISTRICTHEADQUARTERLOCAL" htmlEscape="true"></spring:message>
																	</label>
																	&nbsp;<form:input type="text" class="frmfield" name="headquarterNameLocal" path="headquarterNameLocal" onkeypress="validateCharKeys(event)"/>
															<form:errors htmlEscape="true"  path="headquarterNameLocal" cssClass="errormsg"/></td>
															<td>&nbsp;</td>
															
														</tr>
											</tr>
										</table>
										</tr>
										</table>
									</div>
								</div> --%>
						  
					 
				
				
				<div class="clear"></div>
				<div class="frmpnlbg">
				<div id='district'>
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CONTRIBUTINGLAND" htmlEscape="true"></spring:message>
						</div>
<table width="100%" class="tbl_no_brdr">
  <tr>
    <td width="14%" rowspan="2">&nbsp;</td>
    <td width="86%" colspan="3"></td>
  </tr>
  
  <tr>
    <td colspan="3"><table width="730">
      <tr>
        <td height="25" colspan="3">&nbsp;</td>
        </tr>
      <tr>
        <td><strong>
          <spring:message code="Label.DISTRICTLIST" htmlEscape="true"></spring:message>
        </strong></td>
        <td>&nbsp;</td>
        <td><strong>
          <spring:message code="Label.CONTRIBUTEDISTRICTLIST" htmlEscape="true"></spring:message>
        </strong></td>
      </tr>
      <tr>
        <td><form:select path="districtsourcecode" class="frmtxtarea" id="ddSourceDistrict" style="height: 120px; width: 233px" multiple="true" >
        <form:options items="${listDist}" itemLabel="districtNameEnglish"
									itemValue="districtCode"/>
        </form:select></td>
        <td><table width="100%">
            <tr>
              <td align="center"><input name="button3" type="button" style="width: 80px" onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);getSubDistrictList('ddDestDistrict');"  value="<spring:message
																code="Button.WHOLE"/>" /></td>
            </tr>
            <tr>
              <td align="center"><input name="button22" type="button" style="width: 70px" onclick="removeAll('ddDestDistrict', 'ddSourceDistrict', true)" value=" Back &lt;&lt;" /></td>
            </tr>
            <tr>
              <td align="center"><input name="button22" type="button" style="width: 70px" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);getSubDistrictList('ddDestDistrict');" value="Part &gt;&gt;" /></td>
            </tr>
        </table></td>
        <td><form:select name="select4"
													id="ddDestDistrict" size="1" multiple="multiple"
													path="districtNameEnglish" class="frmtxtarea"
													style="height: 120px; width: 242px"> 
												 </form:select></td>
      </tr>
      <tr>
        <td>&nbsp;</td>             
        <td>&nbsp;</td>
        <td><input name="button2" class="btn"  type="button" style="width: 100px"  onclick="selectsubdieverything(ddDestDistrict.value,ddDestDistrict[ddDestDistrict.selectedIndex].text)"
        value="Get Sub-district List" /></td>
      </tr>
    </table></td>
  </tr>
</table>					</div>
				</div></div>
				<div class="clear"></div>
				<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						Sub District
						<%-- <spring:message code="App.SELECTEDDISTRICTULB" htmlEscape="true"></spring:message> --%>
					</div>

					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td width="14%" rowspan="4">&nbsp;</td>
							<td width="86%" class="tblclear">
							<table width="686"  class="tbl_no_brdr">
									<tr>
										<td valign="top"><strong><spring:message
													code="Label.SUBDISTRICTLIST" htmlEscape="true"></spring:message> </strong></td>
										<td width="180" align="center">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="246" valign="top"><form:select name="select9"
												size="1" id="ddSubdistrictforsubdistrict" htmlEscape="true"
												path="subDistrictList" multiple="multiple" class="frmtxtarea"
												style="height: 80px; width: 233px">
											</form:select>
										</td>
										<td align="center" style="padding-top: 3px"><table
												width="100%" class="tbl_no_brdr">
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnaddSubDistrictFull" name="Submit4"
															value="Select Full Sub Districtss&gt;&gt;" 
															onclick="addItem('subDistrictListNew','ddSubdistrictforsubdistrict','FULL',true)" />
													</label>
													</td>
													</td>
												</tr>

												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  name="Submit22" value="&lt;&lt;"
															style="width: 58px"
															onclick="removeAll('subDistrictListNew','ddSubdistrictforsubdistrict',true)" />
													</label></td>
												</tr>
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnaddSubDistrictPart" name="Submit4"
															value="Select Part Villages&gt;&gt;" 
															onclick="addItem('subDistrictListNew','ddSubdistrictforsubdistrict','PART',true);javascript:selectall();" />
													</label></td>
												</tr>
											</table></td>
										<td width="260" valign="top">
										
										<form:select name="select4"
								id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" 
								class="frmtxtarea" style="height: 80px; width: 242px"  >
								<form:options items="${listSD}" itemLabel="subdistrictNameEnglish"
									itemValue="subdistrictCode"/> 
							</form:select>
										
										
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="center"><input type="button"  class="btn" 
											value="Get Village List" style="width: 100px"
											onclick="selectVillageList(subDistrictListNew.value,subDistrictListNew[subDistrictListNew.selectedIndex].text)"
											 />
										</td>

									</tr>

								</table>

								<div  class="errormsg"></div></td>
						</tr>
					</table>
				</div></div>
				<div class="clear"></div>
				<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message code="Label.VILLAGE" htmlEscape="true"></spring:message>
					</div>
					<table width="100%" class="tbl_no_brdr">

						<tr>
							<td width="14%" rowspan="4">&nbsp;</td>
							<td width="86%" class="tblclear">
								<table width="686" class="tbl_no_brdr">
									<tr>
										<td valign="top"><strong><spring:message
													code="Label.VILLAGELIST" htmlEscape="true"></spring:message> </strong></td>
										<td width="180" align="center">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="246" valign="top"><form:select name="select9"
												size="1" id="villageList" path="" multiple="multiple"
												class="frmtxtarea" style="height: 80px; width: 233px">
											</form:select>
										</td>
										<td align="center" style="padding-top: 3px">
											<table width="100%" class="tbl_no_brdr">
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnaddVillageFull" name="Submit4"
															value="Select Full Villages&gt;&gt;" 
															onclick="addItem('villageListNew','villageList','FULL',true)" />
													</label></td>
												</tr>
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnremoveOneVillage" name="Submit4"
															value=" &lt; "
															onclick="removeItem('villageListNew','villageList',true)" />
													</label></td>
												</tr>
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnremoveAllVillages" name="Submit4"
															value="&lt;&lt;"
															onclick="removeAll('villageListNew','villageList',true)" />
													</label></td>
												</tr>
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnaddVillagePart" name="Submit4"
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
							</form:select>
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
	  --%>
<div class="btnpnl">
			<table width="100%" class="tbl_no_brdr">
				<tr>
				<input type="hidden" name="coveredLandRegionByULB" id="coveredLandRegionByULB" />
					<td>
					<div >
								<input type="submit" onclick="javascript:selectall();" id="Proceed" style="visibility: hidden"
								value="<spring:message code="Button.PROCEED" htmlEscape="true"></spring:message>"/> </label>
								
								<label> <input type="submit" onclick="javascript:selectFinal();" id="Submit" 
								value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="selectFinal();" /> </label> 
								<label>
								<input type="button" name="Submit6"
								value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
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