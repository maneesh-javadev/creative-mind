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
<title><spring:message htmlEscape="true"  code="Title.CREATENEWSUBDISTRICT"></spring:message></title>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/createDistrict.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	
</script>

</head>
<body onload="onloaddistrict()">
	<div id="content">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td valign="top">
				<div id="frmcontent">
						<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message htmlEscape="true"  code="Label.REVIEWDISTRICT"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                          <td width="50" align="right"><a href="#" class="frmhelp">Help</a></td>
                        </tr>
                      </table>
						</div>
						<div class="clear">
							<form:form action="preview_districtnew.htm" method="POST" commandName="newDistrictPreview">
										 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="preview_districtnew.htm"/>"/>
								
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWDISTRICT"></spring:message>
					</div>
					<table width="100%" class="tbl_no_brdr">
						<tr>
						
							
							<td width="14%" rowspan="8">&nbsp;<form:hidden path="govtOrderConfig" /></td>
							<td width="86%"><spring:message htmlEscape="true" 
									code="Label.DISTRICTNAMEENGLISH"></spring:message><span
								id="required"></span><br /> <form:input
									path="districtNameInEn" id="districtNameInEn" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('districtNameInEn')"
									onblur="NameInEn()" /> <span class="msg" id="districtNameInEn_msg"></span>
							<span class="error" id="districtNameInEn_error"></span><span><form:errors htmlEscape="true"  path="districtNameInEn" class="errormsg"></form:errors></span>	 
							</td>
						</tr>


						<tr>
							<td width="86%"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMELOCAL"></spring:message><br /> <form:input
									path="districtNameInLcl" id="districtNameInLcl" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('districtNameInLcl')"
									onblur="NameInLcl()" /> <!-- <span class="msg" id="districtNameInLcl_msg">Please enter  Name of new District (In English) here</span>
							<span class="error" id="districtNameInLcl_error"></span> -->
							</td>
						</tr>

						<tr>
							<td width="86%"><spring:message htmlEscape="true" 
									code="Label.DISTRICTALIASENGLISH"></spring:message><br /> <form:input
									path="districtAliasInEn" id="districtAliasInEn" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('districtAliasInEn')"
									onblur="AliasInEn()" /> <!-- <span class="msg" id="districtAliasInEn_msg">Please enter  Name of Alias of the district(In English) here</span>
							<span class="error" id="districtAliasInEn_error"></span> -->
							</td>
						</tr>


						<tr>
							<td width="86%"><spring:message htmlEscape="true" 
									code="Label.DISTRICTALIASLOCAL"></spring:message><br /> <form:input
									path="districtAliasInLcl" id="districtAliasInLcl" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('districtAliasInLcl')"
									onblur="AliasInLcl()" /> <!-- <span class="msg" id="districtAliasInLcl_msg">Alias of the district (In Local language) here</span>
							<span class="error" id="districtAliasInLcl_error"></span> -->
							</td>
						</tr>

						

						<tr>
							<td width="86%"><spring:message htmlEscape="true"  code="Label.CENSUSCODE2011"></spring:message><br /> <form:input path="census2011Code"
									id="census2011Code" cssClass="frmfield" htmlEscape="true"
									onfocus="show_msg('census2011Code')" onblur="Cns2011Code()" />
								<!-- 	<span class="msg" id="census2011Code_msg">Please enter  Census2011 Code here</span>
							<span class="error" id="census2011Code_error"></span> -->
							</td>
						</tr>


					

					</table>
				</div></div>
				<div class="clear"></div>
				<%@ include file="../common/gis_nodes.jspf" %>
				<%-- <div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message>
					</div>
					<table width="100%" class="tbl_no_brdr">

						<tr>
							<td width="12%" rowspan="2">&nbsp;</td>
							<td width="88%" style="padding: 0px"><table width="100%"
									class="tbl_no_brdr">
									
									<tr>
										<td width="150"><spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message>
										</td>
										<td width="150" align="right"><label id="lbllatitude"><spring:message htmlEscape="true" 
													code="Label.LATITUDE"></spring:message> </label></td>
										<td width="75"><input name="latitude"  id="lbllatitude"
											type="text" class="frmfield"  /></td>
										<td width="150" align="right"><label id="lbllongitude"><spring:message htmlEscape="true" 
													code="Label.LONGITUDE"></spring:message> </label></td>
										<td width="75"><input name="longitude"  type="text"
											class="frmfield"  /></td>
										<td >&nbsp;</td>
										<td width="150" align="right"><label> <input
												type="button" name="Submit3"
												value="<spring:message htmlEscape="true"  code="Button.ADDNODES"></spring:message>"
												onclick="addgisnodes()" /> </label></td>
									</tr>
								</table>
								<div  class="errormsg"></div>
							</td>
						</tr>
						<tr>
							<td><table width="468" class="tbl_no_brdr" class="tblclear">
									 <tr>
                                    <td width="31%"><label><span class="blktxt"><spring:message htmlEscape="true"  code="Label.MAPOFLOCALBODY"></spring:message> </span>
                                          <form:input id="newLocalBodyMap" path="filePathMapUpLoad"
											type="file" size="25" />
										
                                    </label></td>
                                  </tr> 
								</table>
								<div  class="errormsg"></div>

							</td>
						</tr>
					</table>

				</div></div>
				 --%>
					<%@ include file="../common/headquarter.jspf" %>
					
					<%-- <div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.HEADQUARTERSDISTRICT"></spring:message>
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
								</div> --%>
								
								<div class="frmpnlbg">
									<div class="frmtxt"
										style="position: relative; background: inherit; padding-top: 20px;">
										<div
											style="position: absolute; z-index: 1; width: 164px; text-align: center; top: -11px; left: 12px"
											class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.CONTRIBUTINGLANDREGION"></spring:message>
										</div>
										
									<table>
				
			
									<tr>
									<td width="86%" style="padding: 0px"><table width="664"
											border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td><strong><spring:message htmlEscape="true"  code="Label.CONTRIBUTINGDISTRICTLIST"></spring:message>
							</strong></td>
							<td align="center">&nbsp;</td>
							<td><strong><spring:message htmlEscape="true" 
										code="Label.CONTRIBUTINGSUBDISTRICTLIST"></spring:message> </strong></td>
										<td align="center">&nbsp;</td>
							<td><strong><spring:message htmlEscape="true" 
										code="Label.CONTRIBUTINGVILLAGELISTSD"></spring:message> </strong></td>
				
						</tr>
						<tr>
						
							<td width="86%" style="padding: 0px">
							<form:select name="select4"
								id="ddDestDistrict" size="1" multiple="multiple" path="districtNameEnglish" htmlEscape="true"
								class="frmsfield" style="height: 80px; width: 242px"  >
								<form:options items="${listdist}" itemLabel="districtNameEnglish"
									itemValue="aliasEnglish"/>
							</form:select>
							</td>
							<td align="center" style="padding-top: 3px">
							</td>
							
							<td width="86%" style="padding: 0px">
							<form:select name="select4"
								id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" htmlEscape="true"
								class="frmsfield" style="height: 80px; width: 242px"  >
								<form:options items="${listSD}" itemLabel="subdistrictNameEnglish"
									itemValue="aliasEnglish"/>
							</form:select>
							</td>
							<td align="center" style="padding-top: 3px">
							</td>
							
							<td width="246" valign="top">
							<form:select name="select4"
								id="villageListNew" size="1" multiple="multiple" path="contributedVillages" 
								class="frmsfield" style="height: 80px; width: 242px" >
								<form:options items="${listVill}" itemLabel="villageNameEnglish"
									itemValue="villageCode"/>
							</form:select></td>
						</tr>
						
					</table>
					
					<div style="height: 15px; padding-top: 3px;"></div>
					
				</td>
			</tr>
		</table>
	</div>
	
	
	<label>
								
	
		</div>
	<div >
		<div class="frmtxt">
			<table width="100%" class="tbl_no_brdr">
				<tr>
				<input type="hidden" name="reorganized" id="reorganized"/>
				<input type="hidden" name="modifyVillage" id="modifyVillage"/>
				<input type="hidden" name="addAnotherSD" id="addAnotherSD"/>
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td ><div style="padding: 10px 0px 10px 0px">
								<label>
								 
								</label>
								<label> <input type="submit" id="Submit"  
								value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="checkforModify();"/>
								</label>
								<label> <input type="button" name="Cancel"
													class="btn"
													value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
													id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" /> </label>
								 
						</div></td>
				</tr>
			</table>
		</div>
	</div>
	</form:form>
	
	</td>
	</tr>
	</table>

</body>
</html>