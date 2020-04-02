<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/updateStandardCode.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/validation.js"></script>
<link href="css/error.css" rel="stylesheet" type="	text/css" />

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraftVillageList.js'></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraft.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>


<c:set var="type" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString()%>"></c:set>
<script type="text/javascript" language="javascript">
var stateChoice;
var _state_code=parseInt('${stateCode}');
var _type='${type}';
var _entity_type='${entityType}';
var _entity_code='${entityCode}';
var _org_type=0;
//alert("@@"+'${stateCode}');
	dwr.engine.setActiveReverseAjax(true);
</script>
<script type="text/javascript" src="js/updateStandardCode.js"></script>
</head>


<body onload="hideAll();">
	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center>
									<c:out value="${param.family_msg}" escapeXml="true"></c:out>
								</center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><Div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>



	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>

	<div class="clear"></div>
	<div class="frmhd">


		<h3 class="subtitle">
			<spring:message code="Label.UOTHERSTANDARDCODESCH" htmlEscape="true" text="Update Other Standard Codes/Local Names(Landregion)"></spring:message> 
		</h3>
		<%-- <ul class="listing">
			<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
			<li><a href="#" class="frmhelp">Help</a></li>
		</ul> --%>
	</div>





	<form:form action="ModifyStandardCodes.htm" method="POST" commandName="Standard_Update" id="form1">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="ModifyStandardCodes.htm"/>" />

		<input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>"/>

		<div class="frmpnlbrdr">



			<div class="frmpnlbg">
				<div class="frmtxt">



					<div class="frmhdtitle">
						<%-- <spring:message code="Label.UOTHERSTANDARDCODES" htmlEscape="true"></spring:message> --%>
					</div>




					<c:if test="${empty distuser}">


						<div class="search_criteria">
							<ul class="blocklist">
								<li><label for="entity"><spring:message code="Label.ENTITYSTANDARD" htmlEscape="true"></spring:message><font color="red">*</font></label> 
								<form:select path="entityName" htmlEscape="true"  class="combofield" id="entity" onchange="getDetails();">



										<form:option value="" htmlEscape="true" >
											<esapi:encodeForHTMLAttribute><spring:message code="Label.SEL" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
										</form:option>
										
										
											<option value="state">
												<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code='Label.State'></spring:message></esapi:encodeForHTMLAttribute>
											</option>

										<option value="district">
												<spring:message htmlEscape="true" code='Label.DISTRICT'></spring:message>
											</option>
											
											<option value="subdistrict">
												<spring:message htmlEscape="true" code='Label.SUBDISTRICT'></spring:message>
											</option>
											<option value="village">
												<spring:message htmlEscape="true" code='Label.VILLAGE'></spring:message>
											</option>

									

									</form:select>
									<div id="errorentity"></div></li>
								
								<br/>
								
								<div id="ORGUNIT" style="Display:none" >
											<li>
											<label><spring:message code="Label.SELOrganization" text="Select Organization Type" htmlEscape="true"></spring:message><font color="red">*</font></label>
											<label><form:select path="" class="combofield" id="topLevelEntityType"	onchange="getTopLevelEntityByType(this.value);">
											<form:option value="" htmlEscape="true"><spring:message code="Label.SELECT" text="-slect"></spring:message></form:option>
											<form:option value="2" htmlEscape="true"><spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message></form:option>
											<form:option value="3" htmlEscape="true"><spring:message code="Label.SELECTORG" text="ORGANIZATION"></spring:message></form:option>
											</form:select>
											</label>
											<span class="errormessage" id="errtopLevelEntityType"></span>
											</li>
											
											<li>
											<label><spring:message code="Label.SELOrganization" text="Select Organization" htmlEscape="true"></spring:message><font color="red">*</font></label>
											<form:select htmlEscape="true" path="orgCode" class="combofield"	id="orgCode"	onchange="getParentLevelEntity(this.value)">
											</form:select>
											<span class="errormessage" id="errorgCode"></span>
											</li>
											
											<li>
											<label><spring:message code="Label.SELOrgLevel" text="Select Level  of Organization" htmlEscape="true"></spring:message><font color="red">*</font></label>
											<form:select htmlEscape="true" path="" class="combofield"	id="orgTypeCode"	onchange="getparentOrganizations(this.value);">
											</form:select>
											<span class="errormessage" id="errorgTypeCode"></span>
											</li>
						
										
									
								</div>
								
								
								
									
									
								<li style="Display:none" id="deptLI">
               					<label>
               						<spring:message code="Label.SELDEPT" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="deptList" name="deptList" class="combofield"  onchange="removedeptListError();divShowOrgBlock();">
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								
								</select>
								<span class="errormessage" id="errdeptList"></span>
								</li>
								
								
								
								
								<li id="ORG" style="Display: none">
									<div >
									<label for=category> <spring:message code="Label.ORGLEVEL" htmlEscape="true"></spring:message> <font color="red">*</font></label>
									<form:select htmlEscape="true" path="" class="combofield" id="categoryOption" onchange="getCategoryWisesShowData(this.value,'org')" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');">
									 						<form:option selected="selected" value="" label="--select--" />
													         <form:option value="S" label="State Level Organization" />
													         <form:option value="D@district" label="District Level Organization"  />
													         <form:option value="T@district#subdistrict" label="SubDistrict Level Organization" />
													         <form:option value="B@district#block" label="Block Level Organization" />
													         <form:option value="V@district#subdistrict#village" label="Village Level Organization" />
													         <form:option value="A@adminUnitLevel#parentUnitEntity" label="Administrative Unit Organization" />
									</form:select>
									<span class="errormessage" id="errcategoryOption"></span>
									<form:hidden  path="category" />
									
									</div>
									
								</li>
								
								<li id="DEPT" style="Display: none">
									<div >
									<label for=category> <spring:message code="label.departmentLevel" htmlEscape="true"></spring:message> <font color="red">*</font></label>
									<form:select htmlEscape="true" path="" class="combofield" id="categoryOptionDept" onchange="getCategoryWisesShowData(this.value,'dept')" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');">
									 						<form:option selected="selected" value="" label="--select--" />
													         <form:option value="S" label="State Line Department" />
													         <form:option value="D@district" label="District Line Department"  />
													         <form:option value="T@district#subdistrict" label="SubDistrict Line Department" />
													         <form:option value="B@district#block" label="Block Line Department" />
													         <form:option value="V@district#subdistrict#village" label="Village Line Department" />
													         <form:option value="A@adminUnitLevel#parentUnitEntity" label="Administrative Unit Department" />
									</form:select>
									<span class="errormessage" id="errcategoryOptionDept"></span>
									
									
									</div>
									
								</li>
								
								<li style="Display:none" id="adminUnitLevelLI">
               					<label>
               						<spring:message code="Label.ADMINUNITLEVEL" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="adminUnitLevel" name="entityCodes" class="combofield" onchange="fillParentUnitEntity(this.value)">
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								
								</select>
								<span class="errormessage" id="erradminUnitLevel"></span>
								</li>
								
								<li style="Display:none" id="parentUnitEntityLI">
               					<label>
               						<spring:message code="sel.parent.admin.unit" text="Select Parent Administrative Unit" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="parentUnitEntity" name="entityCodes" class="combofield" >
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								
								</select>
								<span class="errormessage" id="errparentUnitEntity"></span>
								</li>
								
								<%-- <li style="Display:none" id="stateLI">
               					<label>
               						<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="state" name="entityCodes">
									<option value="-1"><spring:message htmlEscape="true" code="Label.SEL"/></option>
									<c:forEach var="obj" items="${stateList}">
									<option  value="${obj.statePK.stateCode}">
										<c:out value="${obj.stateNameEnglish}" escapeXml="true"></c:out>
									</option>
									</c:forEach>
								</select>
								<span class="errormessage" id="errstate"></span>
							</li> --%>
							
							<li style="Display:none" id="districtLI">
               					<label>
               						<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="district" name="entityCodes" class="combofield" onchange="changeDistrict(this.value)">
								<option value=""><spring:message htmlEscape="true" code="Label.SEL"/></option>
								<c:forEach var="obj" items="${districtList}">
									<option  value="${obj.districtPK.districtCode}">
										<c:out value="${obj.districtNameEnglish}" escapeXml="true"></c:out>
									</option>
								</c:forEach>
								</select>
								<span class="errormessage" id="errdistrict"></span>
							</li>
							
							<li style="Display:none" id="subdistrictLI">
               					<label>
               						<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="subdistrict" name="entityCodes" class="combofield" onchange="fillVillageList(this.value)">
									
								</select>
								<span class="errormessage" id="errsubdistrict"></span>
							</li>
							
							<li style="Display:none" id="villageLI">
               					<label>
               						<spring:message code="Label.SELECTVILLAGE" htmlEscape="true" ></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="village" name="entityCodes" class="combofield" onchange="removeVilageError();">
									
								</select>
								<span class="errormessage" id="errvillage" ></span>
							</li>
							<li style="Display:none" id="blockLI">
               					<label>
               						<spring:message code="Label.SELBLOCK" htmlEscape="true"></spring:message>
               						<span class="mandate">*</span>
               					</label>
								<select id="block" name="entityCodes" class="combofield" onchange="removeBlockError();">
									
								</select>
								<span class="errormessage" id="errblock"></span>
							</li>
								
									<li>
									<div id="ADMINLEVEL" style="Display: none">


										<label for="adminUnitLevel"> <spring:message code="Label.SELDeptAdministrationUNIT" htmlEscape="true"></spring:message> <font color="red">*</font></label>
										<form:select htmlEscape="true" path="adminCode" class="combofield" id="adminUnitLevelforEntity" onchange="getParentAdminUnitLevel(this.value)" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');"/>
										<span class="errormessage" id="erradminUnitLevelforEntity"></span>
										
										<label for="parentAdminUnitLevel"> <spring:message code="sel.parent.admin.unit"  text="Select Parent Administrative Unit" htmlEscape="true"></spring:message> <font color="red">*</font></label>
										<form:select htmlEscape="true" path="parentAdminCode" class="combofield" id="parentAdminUnitLevel" onchange="removeError()" onfocus="validateOnFocus('adminUnitLevel');" onblur="vlidateOnblur('adminUnitLevel','1','15','c');">
											<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
											<form:options items="${parentAdminEntity}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
										</form:select>
										<span class="errormessage" id="errparentAdminUnitLevel"></span>

									</div>
								</li>
								
								
								<li>
									<div id="DR" style="Display: none">


										<label for="ddDistrict"> <spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message> <font color="red">*</font></label>
										<form:select htmlEscape="true" path="districtCode" class="combofield" id="ddDistrict" style="width: 150px" onchange="remove_error1()" onfocus="validateOnFocus('ddDistrict');" onblur="vlidateOnblur('ddDistrict','1','15','c');">
											<form:option value="" htmlEscape="true" >Select District</form:option>
											<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" htmlEscape="true" />
										</form:select>

										<div id="errordistrict"></div>


									</div>
								</li>

								<li>

									<div id="IPAN" style="Display: none">

										<label for="dispanchayat"> <spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message><font color="red">*</font>
										</label>

										<form:select path="districtPanchyat" class="combofield" id="dispanchayat" style="width: 150px" onchange="remove_error1()" onfocus="validateOnFocus('ddDistrict');" onblur="vlidateOnblur('ddDistrict','1','15','c');" htmlEscape="true">
											<form:option value="" htmlEscape="true">
											<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
										</form:select>

										<div id="errordistrict"></div>

									</div>
								</li>
								<li>

									<div id="VPAN" style="Display: none">

										<ul class="blocklist">
											<li><label for="dispanchayatforvp"><spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message><font color="red">*</font></label> <form:select path="disPanchyat" class="combofield" id="dispanchayatforvp"
													style="width: 150px" onchange="getIntermediatePanchayatbyDcode(this.value);">
													<form:option value="" htmlEscape="true">
														<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>
													<form:options htmlEscape="true" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
												</form:select>

												<div id="errordistrict2"></div></li>
											<li><c:if test="${Tiertype eq 3}">


													<label for="vilpanchayat"><spring:message code="Label.INTERPANCHYATNME" htmlEscape="true"></spring:message> <font color="red">*</font></label>


													<form:select htmlEscape="true" path="intermediatePanchyat" class="combofield" id="vilpanchayat" style="width: 150px" onchange="remove_error2();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
													</form:select>


													<div id="errorsubdistrict"></div>

												</c:if></li>
										</ul>
									</div>
								</li>
								<li>
									<div id="TR" style="Display: none">

										<ul class="blocklist">

											<li><label for="ddDistrict2"><spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message><font color="red">*</font></label> <form:select htmlEscape="true" path="districtCode" class="combofield" id="ddDistrict2" style="width: 150px"
													onchange="getSubDistrictList(this.value);" onfocus="validateOnFocus('ddDistrict2');" onblur="vlidateOnblur('ddDistrict2','1','15','c');">
													<form:option value="" htmlEscape="true">Select District</form:option>
													<form:options htmlEscape="true" items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" />
												</form:select>


												<!-- Modified by pooja on 12-06-2015 -->
												<div id="errordistrict1"></div></li>
											<li><label for="ddSubdistrict"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><font color="red">*</font> </label> <form:select htmlEscape="true" path="subdistrictCodes" class="combofield" id="ddSubdistrict"
													style="width: 150px" onchange="remove_error2();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
												</form:select>



												<!-- Modified by pooja on 12-06-2015 -->
												<div id="errorsubdistrict1"></div></li>
										</ul>

									</div>
								</li>
							</ul>
						</div>

						<div class="btnpnl">


							<input type="submit" name="Submit" id="Submit" onclick="return validateAll();" value="<spring:message code="Button.GETDATA"></spring:message>" />
							<!-- <input type="button" name="Submit" class="btn"
																	onclick="getData();"
																	value=<spring:message code="Button.GETDATA"></spring:message> /> -->
							<input type="button" name="Submit2" class="btn" value="<spring:message code="Button.CLEAR"></spring:message>" onclick="javascript:go('standard_Code.htm');" /> <input type="button" name="Cancel" class="btn"
								value="<spring:message code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />

							<spring:bind path="stateCode">

								<input type="hidden" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
							</spring:bind>
						</div>
					</c:if>


					<c:if test="${!empty distuser}">

						<div class="search_criteria">
							<ul class="blocklist">
								<li><label for="entity"><spring:message code="Label.ENTITYSTANDARD" htmlEscape="true"></spring:message></label> <form:select path="entityName" class="combofield" id="entity" onchange="getDetails_district();">



										<form:option value="" htmlEscape="true">
										<esapi:encodeForHTMLAttribute><spring:message code="Label.SEL" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
										</form:option>

										<optgroup label="<spring:message  htmlEscape="true" code='Label.LANDREGIONENTITY'></spring:message>">
											<option value="districtDistUser">
												<spring:message htmlEscape="true" code='Label.DISTRICT'></spring:message>
											</option>
											<option value="blockDistUser">
												<spring:message htmlEscape="true" code='Label.BLOCK'></spring:message>
											</option>
											<option value="subdistrictDistUser">
												<spring:message htmlEscape="true" code='Label.SUBDISTRICT'></spring:message>
											</option>
											<option value="villageDistUser">
												<spring:message htmlEscape="true" code='Label.VILLAGE'></spring:message>
											</option>

										</optgroup>

										<form:options htmlEscape="true"  items="${getLocalGovtSetupList}" itemLabel="localBodyTypeName" itemValue="nomenclatureEnglish" />
										
										

									</form:select>

									<div id="errorentity"></div></li>

								<li>
									<div id="DTR" style="Display: none">

										<label for="ddSubdistrict"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message> <span id="required" class="errormsg">*</span> </label>

										<form:select htmlEscape="true"  path="subdistrictCodes" class="combofield" id="ddSubdistrict" style="width: 150px" onchange="remove_error4();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
											<form:option value="">Select Subdistrict</form:option>
											<form:options items="${subdistrictList}" itemLabel="subdistrictNameEnglish" itemValue="subdistrictPK.subdistrictCode" />
										</form:select>

										<div id="errorsubdistrict"></div>

									</div>
								</li>
								<li>

									<div id="DISIPAN" style="Display: none">

										<label for="distleveldispanchayat"> <spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message> <font color="red">*</font>
										</label>

										<form:select htmlEscape="true"  path="districtPanchyat" class="combofield" id="distleveldispanchayat" style="width: 150px" onchange="remove_error1()" onfocus="validateOnFocus('ddDistrict');" onblur="vlidateOnblur('ddDistrict','1','15','c');">
											<form:option value="" htmlEscape="true">
											<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options htmlEscape="true"  items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select>

										<div id="errordistrict"></div>

									</div>
								</li>
								<li>
									<div id="DISVPAN" style="Display: none">
										<ul class="blocklist">
											<li><label for="distleveldispanchayatforvp"><spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message><font color="red">*</font></label> <form:select htmlEscape="true" path="disPanchyat" class="combofield"
													id="distleveldispanchayatforvp" style="width: 150px" onchange="getIPcodeforDistrictUser(this.value);">
													<form:option value="" htmlEscape="true">
													<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTICTPANCHAYAT" htmlEscape="true"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>
													<form:options htmlEscape="true" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
												</form:select>

												<div id="errordistrict2"></div></li>
											<li><c:if test="${Tiertype eq 3}">

													<label for="distlevelvilpanchayats"><spring:message code="Label.INTERPANCHYATNME" htmlEscape="true"></spring:message> <font color="red">*</font> </label>

													<form:select htmlEscape="true" path="intermediatePanchyat" class="combofield" id="distlevelvilpanchayats" style="width: 150px" onchange="remove_error2();" onfocus="validateOnFocus('ddSubdistrict');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
													</form:select>

													<div id="errorsubdistrict"></div>

												</c:if></li>
										</ul>
									</div>
								</li>
							</ul>
						</div>

						<div class="btnpnl">
							<input type="submit" name="Submit" id="Submit" onclick="return validateAllforDistrict();" value="<spring:message code="Button.GETDATA"></spring:message>" />
							<!-- <input type="button" name="Submit" class="btn"
																	onclick="getData();"
																	value=<spring:message code="Button.GETDATA"></spring:message> /> -->
							<input type="button" name="Submit2" class="btn" value="<spring:message code="Button.CLEAR"></spring:message>" onclick="javascript:go('standard_Code.htm');" /> <input type="button" name="Cancel" class="btn"
								value="<spring:message code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
						</div>

					</c:if>

				</div>
			</div>
		</div>
		<div class="clear"></div>

	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>

</body>
</html>