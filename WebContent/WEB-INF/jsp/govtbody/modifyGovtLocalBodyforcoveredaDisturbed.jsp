<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message>
	</title> <script src="js/shiftdistrict.js"></script>
	<script src="js/govtorder.js"></script>
	
	<script src="js/common.js"></script> 
	<script src="js/modify_LB.js"></script>
	<script src="js/local_body.js"></script>
	<script type="text/javascript" src="js/GovtLocalBody.js"></script>
	<script type="text/javascript" src="js/viewLocalbody.js"></script>
	<script src="js/lgd_localbody.js"></script>
	<script src="js/validation.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/jquery-1.7.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/jquery.MultiFile.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/attachedFiles.js" type="text/javascript"
		language="javascript"></script>
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	
	</script>
	
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>		
	</script>
	
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	
	<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<!-- <script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	<script src="datepicker/calender.js"></script>
	<link rel="stylesheet" href="datepicker/demos.css" /> -->
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);

		function selectallLocalBody() {

			var selObj = document.getElementById('ddDestLocalGovtBody');
			var villageCode = "";
			for (var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				villageCode += selObj.options[i].value + ",";

			}

			getCoveredLandRegion(villageCode);

		}

		function selectallSubDistLocalBody() {
			// alert("Alert in subdistrict");
			var selObj = document.getElementById('ddSubDistDestLocalGovtBody');
			var subDistrictCode = "";
			for (var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				subDistrictCode += selObj.options[i].value + ",";

			}
			getCoveredLandForSubDistRegion(subDistrictCode);
		}

		function selectallDistrictName() {

			var selObj = document.getElementById('ddDestDistLocalGovtBody');
			var districtList = "";
			for (var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				districtList += selObj.options[i].value + ",";

			}
			getCoveredLandRegionforDistrict(districtList);

		}
		
		function ondataLoadF()
		{
			var id=document.getElementById('selectBox').value;
			
			if (id != "0") 
			{
				var temp = id.split(":");
				var id1 = temp[0];
				var id2 = temp[1];
				var id3 = temp[2];
				
				if(id3 !='U')
				{
					switch (id2) 
					{
						case 'D':
							
							if(document.getElementById('availdivLgdLBDistCAreaUnmapped')) {
								document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'block';
							}
							if(document.getElementById('availdivLgdLBInterCAreaUnmapped')) {
								document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
							}
							if(document.getElementById('availdivLgdLBVillageCAreaUnmapped')) {
								document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
							}
							document.getElementById('availdivLgdUrban').style.display = 'none';
							$("#availdivLgdUrban").hide();
							$("#divLgdLBCoveredAreaHeadQuarter").show();
							$("#getHeadQuartersD").show();
							getHeadQuarterListFinalWithoutMappedLBonLoadDist('D'); 
							break;
						case 'I':
							document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							$("#availdivLgdUrban").hide();
							$("#divLgdLBCoveredAreaHeadQuarter").show();
							$("#getHeadQuartersT").show();
							getHeadQuarterListFinalWithoutMappedLBonLoadSubDist('T');
							
							break;
						case 'V':
							$("#availdivLgdLBVillageCAreaUnmapped").show();
							$("#availdivLgdLBInterCAreaUnmapped").hide();
							$("#availdivLgdLBDistCAreaUnmapped").hide();
							$("#availdivLgdUrban").hide();
							$("#divLgdLBCoveredAreaHeadQuarter").show();
							$("#getHeadQuartersV").show();
							//document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
						/* 	document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
							document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
							document.getElementById('getHeadQuartersV').style.display = 'block';
							document.getElementById('getHeadQuartersV').style.visibility = 'visible';*/
							getHeadQuarterListFinalWithoutMappedLBonLoad('V');  
							
							break;
					}
				}	
				else{
					
					$("#availdivLgdUrban").show();
					$("#availdivLgdLBInterCAreaUnmapped").hide();
					$("#availdivLgdLBDistCAreaUnmapped").hide();
					$("#availdivLgdLBVillageCAreaUnmapped").hide();
					
				/* 	document.getElementById('availdivLgdUrban').style.display = 'block';
					document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none'; */
				}	
			
			}
			
		}
		
	  	if ( window.addEventListener ) { 
		     window.addEventListener("load",ondataLoadF, true );
		}
		else 
		if ( window.attachEvent ) { 
		        window.attachEvent("onload", ondataLoadF);
		} else 
		if ( window.onLoad ) {
		     window.onload = ondataLoadF;
		}  
		
	</script>
	<%@include file="../common/dwr.jsp"%>
</head>

<!-- <body onload=ondataLoad('${selectBox}')> -->
<body>
<div class="overlay" id="overlay1" style="display: none"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>


	<div id="frmcontent">
		<div class="frmhd">

			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><label><spring:message
								code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true">
							</spring:message></label></td>
					<td>&nbsp;</td>
					<td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"  ></spring:message> </a></td>
				</tr>
			</table>
		</div>

		<div class="frmpnlbrdr">
			<form:form action="redirectToDisturbedPage.htm" method="POST" commandName="localGovtBodyForm" enctype="multipart/form-data" onsubmit="displayLoadingImage()">
				   <input type="hidden" name="selectBox" id="selectBox" value="<c:out value='${selectBox}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox1" id="subDistrictVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishList}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox2" id="villageVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishListSource}' escapeXml='true'></c:out>"></input>
				   <input type='hidden' id="localBCode" value="<c:out value='${localBCode}' escapeXml='true'></c:out>"/>
				   <input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
				   <input type='hidden'	id="hdnStateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
				   <input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
				   <form:hidden path="lgd_LBTypeNamehidden" id="lgdLBTypeNamehidden" value="${selectBox}" />
					<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="redirectToDisturbedPage.htm"/>" />
				<div id="cat">
				
				<div class="frmMessagetxt">
						
						<table width="100%" class="tbl_no_brdr">
						<tr>
						<td><h1>Reason for Local Body to be in Disturbed State</h1>
						</td>
						</tr>
						<td><h4><font color="red"><c:out value="${messageDisturbed}" escapeXml="true"></c:out></font></h4>
						
						<%-- <input type="text" name="messageDisturbed" value="${messageDisturbed}" /> --%>
						</td>
						</table>
						
						</div>
						
				
					<div class="frmpnlbg">
						
						
						<div class="frmtxt">
						
							<div class="frmhdtitle">
								<label><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"></spring:message>
								</label>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td>&nbsp;</td>
									<td><form:hidden path="localBodyCode" id="hdnLbCode"
											value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> <form:hidden
											path="lbType" id="hdnLbTypeCode"
											value="${localGovtBodyForm.lbType}" htmlEscape="true" />
											<form:hidden path="operationCode" id="operationCode"
											value="${localGovtBodyForm.operationCode}" htmlEscape="true" />
											<form:hidden path="lgd_LBlevelChk" id="lgd_LBlevelChk"
											value="${localGovtBodyForm.lgd_LBlevelChk}" htmlEscape="true" />
									</td>
								</tr>
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">

									<tr>
										
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
											<c:if test="${status.value}">
											<td class="lblBold" width="40%"><label> <spring:message
													code="Label.DISTURBEDSTATE" htmlEscape="true"  ></spring:message>
										</label></td>
												<td> <label
													class="lblPlain"> <img src="images/red_flg.png"
														width="13" height="9" />
												</label></td>
											</c:if>
										</spring:bind>
									</tr>

									<tr>

										<td class="lblBold" width="40%"><label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label></td><td width="40%"> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label></td>
									</tr>

									<tr>
										<td class="lblBold"><label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label></td><td> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label></td>
									</tr>
									<tr>
										<td class="lblBold"><label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label></td><td>
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label></td>
									</tr>
									<tr>
										<td class="lblBold"><label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label></td><td> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label></td>
									</tr>

									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].level"  htmlEscape="true" >
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />

									</spring:bind>
									
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
											
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
										
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									
								</c:forEach>


							</table>
						</div>
					</div>

			
					<!-- Coverage Area of Local Body -->

					<div id='divLgdLBCoveredArea' class="frmpnlbg"> 
					
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.MODIFYCOVERAGE" htmlEscape="true"></spring:message>
								</label>
							</div>							

							<form:hidden path="lgd_hiddenLbcList" id="hdnListLbCode" />
							
					<div id='currentCoverDiv' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.CURRENTCOVERAREA" htmlEscape="true"></spring:message>
								</label>
							</div>	
							
						 	<table class="tbl_no_brdr" width="100%">
												<tr>
									<td  align="center">
										
										<div id="availdivLgdLBDistCAreaUnmapped" class="frmpnlbg" style="display: none">
										
											<table class="tbl_no_brdr">

												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>
												<%-- <span class="errormsg"
													id="availddLgdLBDistCAreaDestLUmapped_error"><spring:message htmlEscape="true" 
															code="error.DESTDISTLOCALBODY"></spring:message> </span>&nbsp;<span><form:errors htmlEscape="true" 
															path="lgd_LBDistCAreaDestListUmapped" class="errormsg"></form:errors>
												</span> --%>
												
												</td>
												</tr>
												
												<tr>
															

													
													<td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTDIS"></spring:message> </label><br />
																											
														  <form:select
															path="availlgd_LBDistCAreaSourceListUmapped" class="frmtxtarea"
															id="availddLgdLBDistCAreaSourceLUmapped"
															items="${districtnamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															style="height: 110px; width: 230px" multiple="true">

														</form:select>
														
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">   
															 <form:select
																path="availlgd_LBDistCAreaSourceListUmappedHidden" class="frmtxtarea"
																id="availddLgdLBDistCAreaSourceLUmappedHidden"
																items="${districtnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																style="height: 110px; width: 230px" multiple="true">
															</form:select>
														</div> 
														
														 <br /> <br /></td>														
														
														<tr><td><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforCovArea('availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistCAreaSourceLUmapped','availddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatDCAUmapped',true)" /></td></tr>
															<td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
														 <form:select
															path="availlgd_LBSubDistrictSourceLatDCAUmapped" class="frmtxtarea"
															id="availddLgdLBSubDistrictSourceLatDCAUmapped"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>
																										
														<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredsubdisticDP('availddLgdLBSubDistrictSourceLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</td></tr>
														<td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="availlgd_LBVillageSourceLatDCAUmapped" class="frmtxtarea"
															id="availddLgdLBVillageSourceLatDCAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>
																						
														<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredvillage('availddLgdLBVillageSourceLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</td></tr>
													
												</tr>
												
													<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBSubDistrictDestLatDCAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCAUmapped"
																class="errormsg"></form:errors> </span></td>
												</tr>
												
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBVillageDestLatDCAUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCAUmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>
												</table>

										</div>
									</td>
								</tr>

								<tr>
									
									<td width="86%" align="center">
										<div id="availdivLgdLBInterCAreaUnmapped" class="frmpnlbg" style="display: none">
										
											<table class="tbl_no_brdr">
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBInterCAreaDestLUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestListUmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>


												<tr>
													<td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
														 <form:select
															path="availlgd_LBInterCAreaSourceListUmapped" class="frmtxtarea"
															id="availddLgdLBInterCAreaSourceLUmapped"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															style="height: 110px; width: 230px" multiple="true">

														</form:select>
														
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
															 <form:select
																path="availlgd_LBInterCAreaSourceListUmappedHidden" class="frmtxtarea"
																id="availddLgdLBInterCAreaSourceLUmappedHidden"
																items="${subdisticnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																style="height: 110px; width: 230px" multiple="true">
															</form:select>
														</div> 
														
														<br /> <br /></td>
														
														<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredsubdistic('availddLgdLBInterCAreaSourceLUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatICAUmapped',true)" /><br />
														</td></tr>
														<td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="availlgd_LBVillageSourceLatICAUmapped" class="frmtxtarea"
															id="availddLgdLBVillageSourceLatICAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>
														
														<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredvillage('availddLgdLBVillageSourceLatICAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</td></tr>

														</tr>

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>

												<tr>
																								
												</tr>
											</table>

										</div>
									</td>
								</tr>
								
								<tr>
									
									<td width="86%" align="center">
										<div id="availdivLgdUrban" class="frmpnlbg" style="display: none">
										
											<table class="tbl_no_brdr">
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBInterCAreaDestLUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestListUmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>


												<tr>
													<td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
														 <form:select
															path="availlgd_LBInterCAreaSourceListUmappedUrban" class="frmtxtarea"
															id="availddLgdLBInterCAreaSourceLUmappedUrban"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															
															itemValue="land_region_code"
															style="height: 110px; width: 230px" multiple="true">

														</form:select>
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
															 <form:select
																path="availlgd_LBInterCAreaSourceListUmappedUrbanHidden" class="frmtxtarea"
																id="availddLgdLBInterCAreaSourceLUmappedUrbanHidden"
																items="${subdisticnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																style="height: 110px; width: 230px" multiple="true">
															</form:select>
														</div> 
														
														<br /> <br /></td>
														
														<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredsubdisticUrbanFin('availddLgdLBInterCAreaSourceLUmappedUrban','ddLgdUrbanLBDistUmappedSource',true)" />
														</td></tr>
														
														<%-- <td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="lgd_LBVillageSourceLatICAUmapped" class="frmtxtarea"
															id="availddLgdLBVillageSourceLatICAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>
														
														<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredvillage('availddLgdLBInterCAreaSourceLUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</td></tr> --%>

												</tr>

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>

												<tr>
																								
												</tr>
											</table>

										</div>
									</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									
									 <td width="86%" align="center">
									 	<div id="availdivLgdLBVillageCAreaUnmapped" class="frmpnlbg" style="display: none">
									 	
											<table class="tbl_no_brdr">

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="availddLgdLBVillageCAreaDestLUnmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestLUnmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>
												<tr>
													 <td width="250" ><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="availlgd_LBVillageCAreaSourceLUnmapped" class="frmtxtarea"
															id="availddLgdLBVillageCAreaSourceLUnmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															style="height: 110px; width: 230px" multiple="true">

														</form:select>
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">   
															 <form:select
																path="availlgd_LBVillageCAreaSourceLUnmappedHidden" class="frmtxtarea"
																id="availddLgdLBVillageCAreaSourceLUnmappedHidden"
																items="${villagenamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																style="height: 110px; width: 230px" multiple="true">
															</form:select>
														</div> 
														
														<br /> <br /></td>
														

												<tr><td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredvillageFin('availddLgdLBVillageCAreaSourceLUnmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</td></tr></tr>


											</table>

										</div>
									</td>
								</tr>

											</table>
							
								
						</div>
				    </div>
							
							
					<div id='divLgdLBCoveredArea' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message>
							</div>
							<table class="tbl_no_brdr" width="100%">
								<tr>
												<td>&nbsp;&nbsp;<span class="errormsg"
													id="chkLgdLBExistChk_error"> <spring:message htmlEscape="true" 
															code="error.EXISTINGLOCALBODY"></spring:message>
												</span>
												</td>
												
								</tr>
								<tr>
								   
								   <td width="100%">
										<table class="tbl_no_brdr">											
											<tr>
												<td width="40%"><form:checkbox id="chkLgdLBExistChk"
														onclick="getHideLocalBodyParentListModify(document.getElementById('selectBox').value, this.checked,'${localGovtBodyForm.localBodyCode}');"
														value="true" path="lgd_LBExistCheck"></form:checkbox> <label><spring:message htmlEscape="true" 
															code="Label.SELEXISTINGCOVEREDAREA"></spring:message><span><form:errors htmlEscape="true" 
																path="lgd_LBExistCheck" class="errormsg"></form:errors>
													</span> </label></td>
													
													<td width="50%"><form:checkbox value="true"
														onclick="getHideUnmappedListModifyforcoveredarea(document.getElementById('selectBox').value,this.checked);"
														id='chkLgdLBUnmapped' path="lgd_LBUnmappedCheck" /><label>
														<spring:message htmlEscape="true" 
															code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"></spring:message><span><form:errors htmlEscape="true" 
																path="lgd_LBUnmappedCheck" class="errormsg"></form:errors>
													</span>
												</label></td>

											</tr>

										</table>
									</td>	
									
							

								</tr>
								
								<tr>
									<td width="100%" colspan="2">
									<div id='divLgdLBDistCArea' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
										
											<table class="tbl_no_brdr">

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<div id="ddLgdLBDistPDestList_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList_error1"><form:errors path="lgd_LBDistPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none" ></div> 
													<div id="ddLgdLBDistPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList1_error2" style="display: none" ></div> 
													
													</td>
												</tr>
												
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBDistPSourceList" class="frmtxtarea"
															id="ddLgdLBDistPSourceList"
															items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode"
															style="height: 110px; width: 230px" multiple="true">

														</form:select> <br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px" 
														onclick="addItemforLBChangeCovFULL('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel1('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAllLevel1('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBChangeCov('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															id="ddLgdLBDistPDestList" size="1" multiple="true"
															path="lgd_LBDistPDestList" class="frmtxtarea"
															style="height: 110px; width: 230px">

														</form:select> <br /> &nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														style="width: 200px" onclick="selectLocalBodyListAtDCAforModifyCoverageF();" /> 
													</td>
												</tr>


												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBDistCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.DISTRICTCAatDCA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBDistCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBDistCAreaDestL_error" class="error"><spring:message code="error.blank.DISTRICTCAatDCA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistCAreaDestL_error1"><form:errors path="lgd_LBDistCAreaDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistCAreaDestL_error2" style="display: none" ></div> 
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBDistCAreaSourceList"
															class="frmtxtarea" id="ddLgdLBDistCAreaSourceL"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforVillageLocalBodyDP('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistPDestListhiddenforHeadQuarter','FULL',true,'D');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemModify('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAllModify('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforVillageLocalBodyDP('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistPDestListhiddenforHeadQuarter','PART',true,'D');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBDistCAreaDestL"
															size="1" multiple="true" path="lgd_LBDistCAreaDestList"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br />
													
														&nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>"
														style="width: 200px" onclick="selectSubdistrictAtDCACovChng();" />
													</td>
												</tr>
												<%-- Data Is not found For SubDistrict so Hide tha Below Pan --%>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<%-- <span class="errormsg"
														id="ddLgdLBSubDistrictDestLatDCA_error"> <spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatDCA" />
													</span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCA" class="errormsg"></form:errors>
													</span>--%>
													<div id="ddLgdLBSubDistrictDestLatDCA_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatDCA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error1"><form:errors path="lgd_LBSubDistrictDestLatDCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error2" style="display: none" ></div> 
																										
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBSubDistrictSourceLatDCA"
															class="frmtxtarea" id="ddLgdLBSubDistrictSourceLatDCA"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value=" Whole &gt;&gt;" style="width: 70px"
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA','FULL',true,'T');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel3('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAllLevel3('ddLgdLBSubDistrictDestLatDCA', 'ddLgdLBSubDistrictSourceLatDCA', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA', 'PART',true,'T');" />
													</td>

													<td><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBSubDistrictDestLatDCA" size="1"
															multiple="true" path="lgd_LBSubDistrictDestLatDCA"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														style="width: 200px" onclick="selectVillageAtDCACovChng();" />
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatDCA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatDCA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCA" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageDestLatDCA_error" class="error"><spring:message code="error.blank.VILLAGECAatDCA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error1"><form:errors path="lgd_LBVillageDestLatDCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error2" style="display: none" ></div> 
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatDCA"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatDCA"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value=" Whole &gt;&gt;" style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA','FULL',true,'V');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLB('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBVillageDestLatDCA', 'ddLgdLBVillageSourceLatDCA', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA', 'PART',true,'V');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBVillageDestLatDCA"
															size="1" multiple="true" path="lgd_LBVillageDestLatDCA"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /></td>
												</tr>
												
											
										<!-- 	<tr>
													<td colspan="3" width="100%">
														<div id="getHeadQuartersD"></div>
													</td>
											</tr> -->
											</table>

										</div>
									 </div>
									</td>
								</tr>
								
								<tr>
								<td>
								 <div class="frmpnlbghidden" style="visibility: hidden; display: none;">
								 
								<!--  	<div class="frmpnlbghidden">
								 --> <form:select
											id="ddLgdLBDistPDestListhidden" size="1" multiple="true"
											path="lgd_LBDistPDestListhidden" class="frmtxtarea"
											style="height: 110px; width: 230px">

									</form:select>
									
									<form:select
											id="ddLgdLBDistPDestListhiddenforHeadQuarter" size="1" multiple="true"
											path="lgd_LBDistPDestListhiddenforHeadQuarter" class="frmtxtarea"
											style="height: 110px; width: 230px">
											
									</form:select>
								</div>
								</td>
								
								</tr>

								<tr>
									<td width="100%" colspan="2">
										<div id='divLgdLBInterCArea' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
											<table class="tbl_no_brdr">
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<div id="ddLgdLBInterPDestList_error" class="error"><spring:message code="error.blank.INTERPANCHAYT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBInterPDestList_msg" style="display:none"><spring:message code="error.blank.INTERPANCHAYT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBInterPDestList_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList_error2" style="display: none" ></div> 
													<div id="ddLgdLBInterPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterPDestList1_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList1_error2" style="display: none"></div>
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span><br /> <form:select
															path="lgd_LBInterPSourceList" class="frmtxtarea"
															id="ddLgdLBInterPSourceList"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBCHCovFULL('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel9('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll9('ddLgdLBInterPDestList', 'ddLgdLBInterPSourceList', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBCHCov('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															name="select6" id="ddLgdLBInterPDestList" size="1"
															multiple="true" path="lgd_LBInterPDestList"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br />&nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														style="width: 200px" onclick="selectLocalBodyListAtICAforChCoverage();" />
													</td>
												</tr>


												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<%-- <span class="errormsg"
														id="ddLgdLBInterCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBInterCAreaDestL_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatICA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterCAreaDestL_error1"><form:errors path="lgd_LBInterCAreaDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestL_error2" style="display: none" ></div>
													
													
													</td>
												</tr>


												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBInterCAreaSourceList"
															class="frmtxtarea" id="ddLgdLBInterCAreaSourceL"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforsubChangeCov('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','availddLgdLBInterCAreaSourceLUmapped','ddLgdInterSubDestListhidden','FULL',true,'T');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel10('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll10('ddLgdLBInterCAreaDestL', 'ddLgdLBInterCAreaSourceL', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforsubChangeCov('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','availddLgdLBInterCAreaSourceLUmapped','ddLgdInterSubDestListhidden','PART',true,'T');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBInterCAreaDestL" size="1" multiple="true"
															path="lgd_LBInterCAreaDestList" class="frmtxtarea"
															style="height: 110px; width: 230px">
														</form:select><br />
														&nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														style="width: 200px" onclick="selectVillageICACovChng();" />
													</td>
												</tr>

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICA" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageDestLatICA_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error1"><form:errors path="lgd_LBVillageDestLatICA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error2" style="display: none" ></div>
													
													</td>
												</tr>

												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatICA"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatICA"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value=" Whole &gt;&gt;" style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA','FULL',true,'V');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLB('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBVillageDestLatICA', 'ddLgdLBVillageSourceLatICA', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA', 'PART',true,'V');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBVillageDestLatICA"
															size="1" multiple="true" path="lgd_LBVillageDestLatICA"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select></td>
												</tr>
												
											<tr>
													<td colspan="3" width="100%">
														<!-- <div id="getHeadQuartersT"></div> -->
													</td>
												</tr>
											</table>

										</div>
										</div>
									</td>
								</tr>
								
								<tr>
								<td>
								<div class="frmpnlbgInterhidden" style="visibility: hidden; display: none;">
								<!-- 	<div class="frmpnlbgInterhidden">  -->
								 	<form:select
											id="ddLgdInterSubDestListhidden" size="1" multiple="true"
											path="lgd_LBInterSubDestListhidden" class="frmtxtarea"
											style="height: 110px; width: 230px">

									</form:select>
								</div>
								</td>
								
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td width="100%" colspan="2">
										<div id='divLgdLBVillageCArea' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
											<table class="tbl_no_brdr">
												
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<div id="ddLgdLBVillageDestAtVillageCA_error" class="error"><spring:message code="error.blank.VILLAGEPANCHAYT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBVillageDestAtVillageCA_msg" style="display:none"><spring:message code="error.blank.VILLAGEPANCHAYT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none" ></div>
													<div id="ddLgdLBVillageDestAtVillageCA1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error2" style="display: none"></div> 
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
													</label><span class="errormsg">*</span><br /> <form:select
															path="lgd_LBVillageSourceAtVillageCA" class="frmtxtarea"
															id="ddLgdLBVillageSourceAtVillageCA"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center">
													
												<%-- 	<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLB('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" />
														<br /> --%> 
														
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel8('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll8('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforChangeCoverage('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA', 'PART',true);" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
													</label><span class="errormsg">*</span> <br /> <form:select
															name="select6" id="ddLgdLBVillageDestAtVillageCA"
															size="1" multiple="true"
															path="lgd_LBVillageDestAtVillageCA" class="frmtxtarea"
															style="height: 110px; width: 230px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														style="width: 200px" onclick="selectLocalBodyListAtVCAforModifyCoverageF();" />
													</td>
												</tr>


												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageCAreaDestL_error"> <spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatICA" />
													</span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestL" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageCAreaDestL_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error1"><form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error2" style="display: none" ></div>
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageCAreaSourceL"
															class="frmtxtarea" id="ddLgdLBVillageCAreaSourceL"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforvillageMappedChangedCoverage('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','availddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemvillageModify('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll8('ddLgdLBVillageCAreaDestL', 'ddLgdLBVillageCAreaSourceL', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforvillageMappedChangedCoverage('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','availddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBVillageCAreaDestL"
															size="1" multiple="true" path="lgd_LBVillageCAreaDestL"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /></td>
												</tr>
									
											</table>

										</div>
										</div>
									</td>
								</tr>
								
								
								
								
								<tr>
									<td width="100%" colspan="2">
										<div id='divLgdLBUrban' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
											<table class="tbl_no_brdr">
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<div id="ddLgdUrbanLBDistExistDest_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
													<div id="ddLgdUrbanLBDistExistDest_msg" style="display:none"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error2" style="display: none"></div> 
													<div id="ddLgdUrbanLBDistExistDest1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
												<%--<div id="ddLgdUrbanLBDistExistDest_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div> --%>
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error2" style="display: none"></div> 
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message
															code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><br /> <form:select
															path="lgd_UrbanLBDistExistSource" class="frmtxtarea"
															id="ddLgdUrbanLBDistExistSource"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>
														
														<td width="100" align="center">
														
														<%-- <input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItem('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource','FULL',true);" />
														<br />  --%>
														
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel9('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll9('ddLgdUrbanLBDistExistDest', 'ddLgdUrbanLBDistExistSource', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforUrbanChangeCoverage('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource', 'PART',true);" />
													</td>

													<td width="250"><label><spring:message
															code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message>
															&nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															name="select6" id="ddLgdUrbanLBDistExistDest" size="1"
															multiple="true" path="lgd_UrbanLBDistExistDest"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br />&nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														style="width: 200px" onclick="selectLocalBodyListforUrbanforModF();" />
													</td>
													
												</tr>


												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBInterCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													
													</td>
												</tr>


												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_UrbanLBSubdistrictListSource"
															class="frmtxtarea" id="ddLgdUrbanLBSubdistrictListSource"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>
														
														
													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforsub('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource','FULL',true,'T');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemLevel10('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll10('ddLgdUrbanLBSubdistrictListDest', 'ddLgdUrbanLBSubdistrictListSource', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforsub('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource', 'PART',true,'T');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdUrbanLBSubdistrictListDest" size="1" multiple="true"
															path="lgd_UrbanLBSubdistrictListDest" class="frmtxtarea"
															style="height: 110px; width: 230px">
														</form:select><br />
														&nbsp;&nbsp;&nbsp; 
													</td>
												</tr>

											
											</table>

										</div>
										</div>
									</td>
								</tr>
								
								<tr>
									
									<td width="100%">
										<div id='divLgdLBDistCAreaUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<table class="tbl_no_brdr">

												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>
												
												<div id="ddLgdLBDistCAreaDestLUmapped_error" class="error"><spring:message code="error.PSDT" htmlEscape="true"></spring:message></div> 
												<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error1"><form:errors path="lgd_LBDistCAreaDestListUmapped" htmlEscape="true"/></div>  
												<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>
												
												</td>
												</tr>
												<tr>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBDistCAreaSourceListUmapped"
															class="frmtxtarea" id="ddLgdLBDistCAreaSourceLUmapped"
															style="height: 110px; width: 230px" multiple="true">
															<form:options items="${UnmappedData}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="landRegionCode" />
														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforDistforCovChange('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','FULL',true,'D');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemDistrict('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAllDistrict('ddLgdLBDistCAreaDestLUmapped', 'ddLgdLBDistCAreaSourceLUmapped', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforDistforCovChange('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped', 'PART',true,'D');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label><span class="errormsg">*</span> <br />
														<form:select name="select6"
															id="ddLgdLBDistCAreaDestLUmapped" size="1"
															multiple="true" path="lgd_LBDistCAreaDestListUmapped"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>"
														style="width: 200px"
														onclick="refreshOptions('ddLgdLBSubDistrictSourceLatDCAUmapped');getUnmappedLBSDPListatUmappedFinal('T','${localGovtBodyForm.lbType}','${levelcheck}');" /></td>
												</tr>

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBSubDistrictDestLatDCAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCAUmapped"
																class="errormsg"></form:errors> </span> --%>
													<%-- <div id="ddLgdLBSubDistrictDestLatDCAUmapped_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error1"><form:errors path="lgd_LBSubDistrictDestLatDCAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>	 --%>		
																
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped"
															class="frmtxtarea"
															id="ddLgdLBSubDistrictSourceLatDCAUmapped"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','FULL',true,'T');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAllSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped', 'ddLgdLBSubDistrictSourceLatDCAUmapped', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped', 'PART',true,'T');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBSubDistrictDestLatDCAUmapped" size="1"
															multiple="true" path="lgd_LBSubDistrictDestLatDCAUmapped"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														style="width: 200px"
														onclick="refreshOptions('ddLgdLBVillageSourceLatDCAUmapped');getUnmappedLBVillPListatUmappedFinal('V','${localGovtBodyForm.lbType}','${levelcheck}');" />
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatDCAUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCAUmapped" class="errormsg"></form:errors>
													</span> --%>
													<%-- <div id="ddLgdLBVillageDestLatDCAUmapped_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatDCAUmapped_error1"><form:errors path="lgd_LBVillageDestLatDCAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatDCAUmapped_error2" style="display: none" ></div>		 --%>
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatDCAUmapped"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatDCAUmapped"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped','FULL',true,'V');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemVillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBVillageDestLatDCAUmapped', 'ddLgdLBVillageSourceLatDCAUmapped', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped', 'PART',true,'V');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageDestLatDCAUmapped" size="1"
															multiple="multiple" path="lgd_LBVillageDestLatDCAUmapped"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /></td>
												</tr>

											</table>
											</div>
										</div>
									</td>
								</tr>

								<tr>
									
									<td width="100%">
										<div id='divLgdLBInterCAreaUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<table class="tbl_no_brdr">
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													
													<td>
													
													<div id="ddLgdLBInterCAreaDestLUmapped_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error1"><form:errors path="lgd_LBInterCAreaDestListUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error2" style="display: none" ></div>	
													
													</td>
												</tr>


												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBInterCAreaSourceListUmapped"
															class="frmtxtarea" id="ddLgdLBInterCAreaSourceLUmapped"
															style="height: 110px; width: 230px" multiple="true">
															<form:options items="${UnmappedData}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="landRegionCode" />
														</form:select><br /> <br /></td>

													<td width="100" align="center">
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforsubUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','FULL',true,'T');" />
														<br />
														
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemSubdistrictUnmapped('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll11('ddLgdLBInterCAreaDestLUmapped', 'ddLgdLBInterCAreaSourceLUmapped', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforsubUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped', 'PART',true,'T');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label><span class="errormsg">*</span> <br /> <form:select name="select6"
															id="ddLgdLBInterCAreaDestLUmapped" size="1"
															multiple="true" path="lgd_LBInterCAreaDestListUmapped"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														style="width: 200px"
														onclick="refreshOptions('ddLgdLBVillageSourceLatICAUmapped');selectSubDistrictAtICAUmapped('V','${localGovtBodyForm.lbType}','${levelcheck}');" /></td>
												</tr>

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span> --%>
													
													
													
													</td>
												</tr>

												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatICAUmapped"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatICAUmapped"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped','FULL',true,'V');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemVillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBVillageDestLatICAUmapped', 'ddLgdLBVillageSourceLatICAUmapped', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped', 'PART',true,'V');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageDestLatICAUmapped" size="1"
															multiple="true" path="lgd_LBVillageDestLatICAUmapped"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select></td>
												</tr>
											</table>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									
									<td width="100%">
										<div id='divLgdLBVillageCAreaUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true" code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<table class="tbl_no_brdr">

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													
													<div id="ddLgdLBVillageCAreaDestLUnmapped_error" class="error"><spring:message code="error.PSCV" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error1"><form:errors path="lgd_LBVillageCAreaDestLUnmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error2" style="display: none" ></div>
													
													</td>
												</tr>
												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageCAreaSourceLUnmapped"
															class="frmtxtarea"
															id="ddLgdLBVillageCAreaSourceLUnmapped"
															style="height: 110px; width: 230px" multiple="true">
															
															<form:options items="${UnmappedData}"
																itemLabel="localBodyNameEnglish"
																itemValue="landRegionCode" />

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforvillageMappedUnMappedCovChange('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemVillUnmapped('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBVillageCAreaDestLUnmapped', 'ddLgdLBVillageCAreaSourceLUnmapped', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforvillageMappedUnMappedCovChange('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" />
														
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label><span class="errormsg">*</span> <br />
														<form:select name="select6"
															id="ddLgdLBVillageCAreaDestLUnmapped" size="1"
															multiple="true" path="lgd_LBVillageCAreaDestLUnmapped"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /></td>
												</tr>


											</table>
											</div>
										</div>
									</td>
								</tr>
								
								
								
								
								<tr>
									
									<td width="100%">
										<div id='divLgdLBUrbanUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<table class="tbl_no_brdr">
												<tr>
													
													<td>
													<%-- <span class="errormsg"
														id="ddLgdLBInterCAreaDestLUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestListUmapped" class="errormsg"></form:errors>
													</span> --%>
													</td>
												</tr>


												<tr>
													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_UrbanLBDistUmappedSource"
															class="frmtxtarea" id="ddLgdUrbanLBDistUmappedSource"
															style="height: 110px; width: 230px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource','FULL',true,'T');" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" style="width: 70px"
														onclick="removeItemSubdistrictUnmapped('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll11('ddLgdUrbanLBDistUmappedDest', 'ddLgdUrbanLBDistUmappedSource', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource', 'PART',true,'T');" />
													</td>

													<td width="250"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdUrbanLBDistUmappedDest" size="1"
															multiple="true" path="lgd_UrbanLBDistUmappedDest"
															class="frmtxtarea" style="height: 110px; width: 230px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; </td>
												</tr>

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="ddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span></td>
												</tr>

											</table>
											</div>
										</div>
									</td>
								</tr>
								
								

							</table>

						</div>
						<!--Begining of Govt Order Details  -->

					<%-- <div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>

							<table width="100%" class="tbl_no_brdr">
							<tr>
							<td><form:hidden path="govtOrderConfig"
											value="${localGovtBodyForm.govtOrderConfig}" id="hdngovtOrderConfig" />
									</td>
									</tr>
								<tr>
									<td width="14%" rowspan="14"><form:hidden path="orderCode"
											id="hdnOrderCode" value="${localGovtBodyForm.orderCode}" />
									</td>
									<td width="86%"><label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input
											path="lgd_LBorderNo" id="OrderNo" type="text" maxLength="20"
											style="width: 120px" class="frmfield"
											onkeypress="return validateAlphanumericforOrder(event);" 
											onfocus="validateOnFocus('OrderNo');helpMessage(this,'OrderNo_msg');"
											onblur="vlidateOnblur('OrderNo','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderNo')" />
											
											<div id="OrderNo_error" class="error"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
											<div id="OrderNo_msg" style="display:none"><spring:message code="error.required.ORDERNUM" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderNo_error1"><form:errors path="lgd_LBorderNo" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
										</td>
																		
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"  ></spring:message>
									</label><span class="errormsg">*</span><br /> <form:input
											path="lgd_LBorderDate" id="OrderDate" type="text"
											class="frmfield" style="width: 120px"
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" htmlEscape="true"/> 
											
											<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
											<div id="OrderDate_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderDate_error1"><form:errors path="lgd_LBorderDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderDate_error2" style="display: none" ></div>
										
									</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input
											id="EffectiveDate" path="lgd_LBeffectiveDate" type="text"
											class="frmfield" style="width: 120px"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
											
											<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="lgd_LBeffectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
											
										</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<tr>
									<td><label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
									</label> <br /> <form:input id="GazPubDate" path="gazPubDate"
											type="text" class="frmfield" style="width: 120px"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
									
											<div id="GazPubDate_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none" ></div>		
												
										</td>
								</tr>
								
								</c:if>
								<tr>
									<td><form:hidden path="govtOrderConfig"
											value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									</td>
								</tr>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<tr>
									<td class="tblclear"><%@ include file="../common/update_attachment.jspf"%></td>
								</tr>
								</c:if>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
									<tr>
<!-- 										<td width="14%" rowspan="2">&nbsp;</td> -->
										<td width="86%"><label><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
												path="templateList" id="templateList" style="width:280px"
												class="combofield" maxlength="20" 
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0"><spring:message htmlEscape="true" code="Error.templateselect" ></spring:message></form:option>
												<form:options items="${templateList}"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> 
											
												<div id="templateList_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
												<div id="templateList_msg" style="display:none"><spring:message code="error.blank.template" htmlEscape="true"/></div>
												<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true"/></div>  
												<div class="errormsg" id="templateList_error2" style="display: none" ></div>
												
												</td>
									</tr>
									
									<tr>
										<td>&nbsp;</td>
									</tr>
								</c:if>
							</table>
						</div>
					</div> --%>
					
					
					<div id='divLgdLBCoveredAreaHeadQuarter' class="frmpnlbg" style="display: none">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message>
								</div>
							
								<table class="tbl_no_brdr" width="50%">
									<tr>
									 	<td colspan="3" width="100%">
				    						<div id="getHeadQuartersD"></div>
							    			<div id="getHeadQuartersT"></div> 
						    				<div id="getHeadQuartersV"></div>
						   				</td>
									</tr>	
								</table>
							</div>
					</div>
					
					
					<!-- End of Govt Order Details  -->
						<div class="btnpnl">
							<table width="100%" class="tbl_no_brdr">
								<tr>

									<td align="center"><div class="btnpnl">
											<form:hidden path="lbType"
												value="${localGovtBodyForm.lbType}"/>
											<label> <%-- <input type="button" name="Submit"
												value="<spring:message htmlEscape="true" code="Button.NEXT"></spring:message>"
												onclick="javascript:location.href='redirectToDisturbedPage.htm?<csrf:token uri="redirectToDisturbedPage.htm"/>'" /> --%>
												
												<input type="submit" name="Submit"
												value="<spring:message htmlEscape="true" code="Button.NEXT"></spring:message>"
												onclick="return validateModifyCoverageLBDisturbed();" />
												</label>
												
												
											</label>
											<!--  -->
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'P')}">
												<label> <input type="button" class="btn"
													name="Submit6"
													value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='modifyGovtLocalBodyMainforcoverageareaClear.htm?<csrf:token uri="modifyGovtLocalBodyMainforcoverageareaClear.htm"/>'" />
												</label>
											</c:if>
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'T')}">
												<label> <input type="button" class="btn"
													name="Submit6"
													value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='modifyGovtLocalBodyMainforcoverageareaClear.htm?<csrf:token uri="modifyGovtLocalBodyMainforcoverageareaClear.htm"/>'" />
												</label>
											</c:if>
											<label> <input type="button" class="btn"
												name="Submit6"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
										</div></td>
								</tr>
							</table>
						</div>
					</div>
							</div>
						</div>

				</div>
		
			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

	</div>
</body>
</html>