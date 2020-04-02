<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>


<head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message>
	</title> <script src="js/shiftdistrict.js"></script>
	<script src="js/govtorder.js"></script>
	
	<script src="js/common.js"></script> 
	<script src="js/modify_LB.js"></script>
	<script src="js/local_body.js"></script>
	<script src="js/orderValidate.js"></script>
	
	<script src="js/localbody_validation.js"></script>
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
		/* 
	 	 * Conditional Check whether Logged in state is ULB oprate District wise.
	 	 * Set value for javascript file reference.
	 	 */
		var isDistrictLevel = "${isDistrictLevel}";

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
<body onload='clearOrdernoErrors();'>
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

			
					<h3 class="subtitle"><spring:message
								code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true">
							</spring:message></h3>
					<ul class="listing">
					<%--//this link is not working <li>
					<a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"  ></spring:message> </a>
								</li> --%>
								</ul>
			
		</div>

		<div class="frmpnlbrdr">
			<form:form action="modifyLocalBodyCoveredarea.htm" method="POST" commandName="localGovtBodyForm" enctype="multipart/form-data" onsubmit="displayLoadingImage()">
				   <input type="hidden" name="gtaInterPanch" id="gtaInterPanch" value="<c:out value='${gtaIntermediatePanchatayt}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox" id="selectBox" value="<c:out value='${selectBox}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox1" id="subDistrictVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishList}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox2" id="villageVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishListSource}' escapeXml='true'></c:out>"></input>
				   <input type='hidden' id="localBCode" value="<c:out value='${localBCode}' escapeXml='true'></c:out>"/>
				   <input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
				   <input type='hidden'	id="hdnStateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				   <input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
				   <form:hidden path="lgd_LBTypeNamehidden" id="lgdLBTypeNamehidden" value="${selectBox}" />
					<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="modifyLocalBodyCoveredarea.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"  ></spring:message>
								</label>
							</div>
						
									
								
									<form:hidden path="localBodyCode" id="hdnLbCode"
											value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> <form:hidden
											path="lbType" id="hdnLbTypeCode"
											value="${localGovtBodyForm.lbType}" htmlEscape="true" />
											<form:hidden path="operationCode" id="operationCode"
											value="${localGovtBodyForm.operationCode}" htmlEscape="true" />
											<form:hidden path="lgd_LBlevelChk" id="lgd_LBlevelChk"
											value="${localGovtBodyForm.lgd_LBlevelChk}" htmlEscape="true" />
								<div class="search_criteria">
								<div class="localbodylabels">
								<ul class="blocklist">
								<c:forEach var="localBodyDetails"
									varStatus="localBodyUrbanNameListRow"
									items="${localGovtBodyForm.localBodyDetails}">

									<li>
										
										<spring:bind
											path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
											<c:if test="${status.value}">
											<label> <spring:message
													code="Label.DISTURBEDSTATE" htmlEscape="true"  ></spring:message>
										</label>
												 <label
													class="lblPlain"> <img src="images/red_flg.png"
														width="13" height="9" />
												</label>
											</c:if>
										</spring:bind>
									</li>

									<li>

										<label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label>
									</li>

									<li>
										<label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label>
									</li>
									<li>
										<label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label>
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label>
									</li>
									<li>
										<label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label>
									</li>

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
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
											
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
										
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									
								</c:forEach>


							</ul>
							</div>
							</div>
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
							
						 	
										
										<div id="availdivLgdLBDistCAreaUnmapped" class="frmpnlbg" style="display: none">
										
											
												<%-- <span class="errormsg"
													id="availddLgdLBDistCAreaDestLUmapped_error"><spring:message htmlEscape="true" 
															code="error.DESTDISTLOCALBODY"></spring:message> </span>&nbsp;<span><form:errors htmlEscape="true" 
															path="lgd_LBDistCAreaDestListUmapped" class="errormsg"></form:errors>
												</span> --%>
												
												
															
													<ul class="blocklist">
													<li>			
													<div class="ms_container">
													<div class="ms_selectable">
														<label for="availddLgdLBDistCAreaSourceLUmapped"><spring:message htmlEscape="true" 
																	code="Label.AVAILABLEFORCURRENTDIS"></spring:message> </label><br />
																												
															  <form:select
																path="availlgd_LBDistCAreaSourceListUmapped" cssClass="frmtxtarea"
																id="availddLgdLBDistCAreaSourceLUmapped"
																items="${districtnamelist}"
																itemLabel="land_region_name_english"
																itemValue="combineLR"
																multiple="true" class="frmtxtarea">
	
															</form:select>
													</div>
													
													<div class="ms_selectable">		
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
															 <form:select
																path="availlgd_LBDistCAreaSourceListUmappedHidden" cssClass="frmtxtarea"
																id="availddLgdLBDistCAreaSourceLUmappedHidden"
																items="${districtnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																multiple="true" class="frmtxtarea">
															</form:select>
															
														</div>
														</div>
														<div class="clear"></div>
														</div>														
														 
														 </li>													
														
														<li><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" class="bttn"
														onclick="removeItemforCovArea('availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistCAreaSourceLUmapped','availddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatDCAUmapped',true)" />
														</li>
														
														<li>
														<div class="ms_container">
														<div class="ms_selectable">	
															<label for="availddLgdLBSubDistrictSourceLatDCAUmapped"><spring:message htmlEscape="true" 
																	code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
															 <form:select
																path="availlgd_LBSubDistrictSourceLatDCAUmapped" class="frmtxtarea"
																id="availddLgdLBSubDistrictSourceLatDCAUmapped"
																items="${subdisticnamelist}"
																itemLabel="land_region_name_english"
																itemValue="combineLR"
																multiple="true">
	
															</form:select></div></div>
															<div class="clear"></div>
														</li>
																										
														<li><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" 
														onclick="removeItemforcoveredsubdisticDP('availddLgdLBSubDistrictSourceLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</li>
														
														
														
														<li>
														 <div class="ms_container">
															<div class="ms_selectable">	
																<label for="availddLgdLBVillageSourceLatDCAUmapped"><spring:message htmlEscape="true" 
																	code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
																<form:select
																	path="availlgd_LBVillageSourceLatDCAUmapped" class="frmtxtarea"
																	id="availddLgdLBVillageSourceLatDCAUmapped"
																	items="${villagenamelist}"
																	itemLabel="land_region_name_english"
																	itemValue="combineLR"
																	multiple="true">
		
																</form:select>
																</div><div class="clear"></div></div>
														</li>
																						
														<li><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" 
														onclick="removeItemforcoveredvillage('availddLgdLBVillageSourceLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</li>
													
												
												
													<li>
													
													<span class="errormsg"
														id="availddLgdLBSubDistrictDestLatDCAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCAUmapped"
																class="errormsg"></form:errors> </span>
												</li>
												
												<li>
													
													<span class="errormsg"
														id="availddLgdLBVillageDestLatDCAUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCAUmapped" class="errormsg"></form:errors>
													</span>
												</li>
												</ul>

										</div>
									
										<div id="availdivLgdLBInterCAreaUnmapped" class="frmpnlbg" style="display: none">
										
											       <ul class="blocklist">
											       
													<li><span class="errormsg"
														id="availddLgdLBInterCAreaDestLUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestListUmapped" class="errormsg"></form:errors>
													</span>
												</li>


												<li>
												<div class="ms_container">
													<div class="ms_selectable">	
													<label for="availddLgdLBInterCAreaSourceLUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
														 <form:select
															path="availlgd_LBInterCAreaSourceListUmapped" class="frmtxtarea"
															id="availddLgdLBInterCAreaSourceLUmapped"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															multiple="true">

														</form:select>
														</div>
														<div class="ms_selectable">	
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
															 <form:select
																path="availlgd_LBInterCAreaSourceListUmappedHidden" class="frmtxtarea"
																id="availddLgdLBInterCAreaSourceLUmappedHidden"
																items="${subdisticnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																multiple="true">
															</form:select>
														</div>
														</div>
														<div class="clear"></div>
														</div>
														<br /> <br />
														</li>
														
														<li><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" class="bttn"
														onclick="removeItemforcoveredsubdistic('availddLgdLBInterCAreaSourceLUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatICAUmapped',true)" /><br />
														</li>

														<li>
														<div class="ms_container">
														<div class="ms_selectable">	
														<label for="availddLgdLBVillageSourceLatICAUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														
														<form:select
															path="availlgd_LBVillageSourceLatICAUmapped" class="frmtxtarea"
															id="availddLgdLBVillageSourceLatICAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															multiple="true">

														</form:select></div>
														<div class="clear"></div>
														</div>
														</li>
														
														<li><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" 
														onclick="removeItemforcoveredvillage('availddLgdLBVillageSourceLatICAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														
														</li>

												<li>
													
													<span class="errormsg"
														id="availddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span>
												</li>

												
											</ul>

										</div>
								
									
									
										<div id="availdivLgdUrban" class="frmpnlbg" style="display: none">
										
											<ul class="blocklist">
											<li>
													<span class="errormsg"
														id="availddLgdLBInterCAreaDestLUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestListUmapped" class="errormsg"></form:errors>
													</span>
												</li>


												
													<li>
													<div class="ms_container">
															<div class="ms_selectable">	
														<label for="availddLgdLBInterCAreaSourceLUmappedUrban">
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILABLEFORCURRENTDIS" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTSUB"></spring:message></c:otherwise>
															</c:choose>
															 
														</label><br />
														<form:select
															path="availlgd_LBInterCAreaSourceListUmappedUrban" class="frmtxtarea"
															id="availddLgdLBInterCAreaSourceLUmappedUrban"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															
															itemValue="land_region_code"
															multiple="true">

														</form:select>
														</div>
														<div class="ms_selectable">	
															<div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
																 <form:select
																	path="availlgd_LBInterCAreaSourceListUmappedUrbanHidden" class="frmtxtarea"
																	id="availddLgdLBInterCAreaSourceLUmappedUrbanHidden"
																	items="${subdisticnamelistHidden}"
																	itemLabel="land_region_name_english" 
																	itemValue="combineLR"
																	multiple="true">
																</form:select>
															</div></div>
														<div class="clear"></div></div>
														</li>
														
														<li>
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" 
														onclick="removeItemforcoveredsubdisticUrbanFin('availddLgdLBInterCAreaSourceLUmappedUrban','ddLgdUrbanLBDistUmappedSource',true)" />
														</li>
														
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

												

												<li>
													
													<span class="errormsg"
														id="availddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span>
												</li>

												
											</ul>

										</div>
								
									
									
									 	<div id="availdivLgdLBVillageCAreaUnmapped" class="frmpnlbg" style="display: none">
									 	
											<ul class="blocklist">

												<li>
													
													<span class="errormsg"
														id="availddLgdLBVillageCAreaDestLUnmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestLUnmapped" class="errormsg"></form:errors>
													</span>
												</li>
												<li>
												<div class="ms_container">
															<div class="ms_selectable">	
													 <label for="availddLgdLBVillageCAreaSourceLUnmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="availlgd_LBVillageCAreaSourceLUnmapped" class="frmtxtarea"
															id="availddLgdLBVillageCAreaSourceLUnmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															multiple="true">

														</form:select></div><div class="clear"></div>
														
														<div class="ms_selectable">	
														<div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
															 <form:select
																path="availlgd_LBVillageCAreaSourceLUnmappedHidden" class="frmtxtarea"
																id="availddLgdLBVillageCAreaSourceLUnmappedHidden"
																items="${villagenamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																multiple="true">
															</form:select>
														</div>
														</div></div>
														</li>
														
														<li>
												       <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" class="bttn"
														onclick="removeItemforcoveredvillageFin('availddLgdLBVillageCAreaSourceLUnmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
														</li>


											</ul>

										</div>
									
								
						</div>
				    </div>
							
							
					<div id='divLgdLBCoveredArea' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.NEWCOVEREDAREAOFLCLBDY"></spring:message>
							</div>
							
								
												<div><span class="errormsg"
													id="chkLgdLBExistChk_error"> <spring:message htmlEscape="true" 
															code="error.EXISTINGLOCALBODY"></spring:message>
												</span>
												</div>
												
								
							
								  
																				
											<ul class="listing">
											<li>
												<label ><form:checkbox id="chkLgdLBExistChk"
														onclick="getHideLocalBodyParentListModify(document.getElementById('selectBox').value, this.checked,'${localGovtBodyForm.localBodyCode}');"
														value="true" path="lgd_LBExistCheck"></form:checkbox> <spring:message htmlEscape="true" 
															code="Label.SELEXISTINGCOVEREDAREA"></spring:message><span><form:errors htmlEscape="true" 
																path="lgd_LBExistCheck" class="errormsg"></form:errors>
													</span> </label>
													</li>
													<li>
													<label><form:checkbox value="true"
														onclick="getHideUnmappedListModifyforcoveredarea(document.getElementById('selectBox').value,this.checked);"
														id='chkLgdLBUnmapped' path="lgd_LBUnmappedCheck" />
														<spring:message htmlEscape="true" 
															code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"></spring:message><span><form:errors htmlEscape="true" 
																path="lgd_LBUnmappedCheck" class="errormsg"></form:errors>
													</span>
												</label>
												</li>

											</ul>

										
									
							

								
								
						
									
									
									<div id='divLgdLBDistCArea' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
										
											<ul class="blocklist">
											  <li>
									             

												
													<div id="ddLgdLBDistPDestList_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList_error1"><form:errors path="lgd_LBDistPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none" ></div> 
													<div id="ddLgdLBDistPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList1_error2" style="display: none" ></div> 
													
													</li>
												<li>
												 <div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBDistPSourceList"><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span><br /> 
														
														<form:select
															path="lgd_LBDistPSourceList" class="frmtxtarea"
															id="ddLgdLBDistPSourceList"
															multiple="true">

															<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
															
															   <c:if test="${districtPanchayatList.operation_state == 'F'.charAt(0)}">
															    <form:option value="${districtPanchayatList.localBodyCode}" disabled="true"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  </c:if>  
															   <c:if test="${districtPanchayatList.operation_state == 'A'.charAt(0)}">
															    <form:option value="${districtPanchayatList.localBodyCode}"><c:out value="${districtPanchayatList.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  </c:if>
															</c:forEach>
														</form:select>
														
														
													 <br /> <br />
													</div>	
														
														
													<div class="ms_buttons btnmargin" >
													
													<input type="button"
														value="<spring:message  code="Button.WHOLE"/>"  class="bttn" 
														onclick="addItemforLBChangeCovFULL('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
														 
														<input type="button" id="btnremoveOneULB" name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemLevel1('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" />
														
														<input type="button" value=" Reset &lt;&lt;" class="bttn" 
														onclick="removeAllLevel1('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" />
														
														<input type="button" value="Part &gt;&gt;" class="bttn" 
														onclick="addItemforLBChangeCov('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
													</div>

													<div class="ms_selection">
													
													<label for="ddLgdLBDistPDestList"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> 
														
														<form:select
															id="ddLgdLBDistPDestList" size="1" multiple="true"
															path="lgd_LBDistPDestList" class="frmtxtarea">

														</form:select> 
														<input type="button" value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>" class="bttn"  onclick="selectLocalBodyListAtDCAforModifyCoverageF();" /> 
														
														</div>
														 <div class="clear"></div>
												        </div>
												        </li>
												        
												


												<li>
													<%-- <span class="errormsg"
														id="ddLgdLBDistCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.DISTRICTCAatDCA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBDistCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBDistCAreaDestL_error" class="error"><spring:message code="error.blank.DistrictCreateWard" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistCAreaDestL_error1"><form:errors path="lgd_LBDistCAreaDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistCAreaDestL_error2" style="display: none" ></div> 
													
												</li>
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBDistCAreaSourceL"><spring:message htmlEscape="true" 
																code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBDistCAreaSourceList"
															class="frmtxtarea" id="ddLgdLBDistCAreaSourceL"
															multiple="true">

														</form:select><br /> <br />
												</div>		
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforVillageLocalBodyDP('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistPDestListhiddenforHeadQuarter','FULL',true,'D');" />
														 
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"  class="bttn" 
														onclick="removeItemModify('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														 class="bttn"
														onclick="removeAllModify('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn"
														onclick="addItemforVillageLocalBodyDP('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistPDestListhiddenforHeadQuarter','PART',true,'D');" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBDistCAreaDestL"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label><span
														class="errormsg">*</span><br />
														<form:select name="select6" id="ddLgdLBDistCAreaDestL"
															size="1" multiple="true" path="lgd_LBDistCAreaDestList"
															class="frmtxtarea" >
														</form:select><br />
														<input type="button" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>" class="bttn" onclick="selectSubdistrictAtDCACovChng();" />
													</div>
													 <div class="clear"></div>
											        </div>
											        </li>
																								
													
												
												<%-- Data Is not found For SubDistrict so Hide tha Below Pan --%>
												<li>
													
													
													<%-- <span class="errormsg"
														id="ddLgdLBSubDistrictDestLatDCA_error"> <spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatDCA" />
													</span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCA" class="errormsg"></form:errors>
													</span>--%>
													<div id="ddLgdLBSubDistrictDestLatDCA_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatDCA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error1"><form:errors path="lgd_LBSubDistrictDestLatDCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error2" style="display: none" ></div> 
																										
													
												</li>
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBSubDistrictSourceLatDCA"><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBSubDistrictSourceLatDCA"
															class="frmtxtarea" id="ddLgdLBSubDistrictSourceLatDCA"
															multiple="true">

														</form:select><br /> <br />
												</div>		
												<div class="ms_buttons btnmargin" >
											    <input type="button"
												value=" Whole &gt;&gt;" class="bttn" 
												onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA','FULL',true,'T');" />
												 
												<input type="button" id="btnremoveOneULB"
												name="Submit4" value="Back &lt;"  class="bttn"
												onclick="removeItemLevel3('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA',true)" />
												<input type="button" value=" Reset &lt;&lt;"
												class="bttn"
												onclick="removeAllLevel3('ddLgdLBSubDistrictDestLatDCA', 'ddLgdLBSubDistrictSourceLatDCA', true)" />
												<input type="button" value="Part &gt;&gt;"
												class="bttn"
												onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA', 'PART',true,'T');" />
											    </div>

													
													<div class="ms_selection">
													<label for="ddLgdLBSubDistrictDestLatDCA"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBSubDistrictDestLatDCA" size="1"
															multiple="true" path="lgd_LBSubDistrictDestLatDCA"
															class="frmtxtarea" >
														</form:select>
														 <input type="button" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>" class="bttn" onclick="selectVillageAtDCACovChng();" />
														
														
													</div>
													<div class="clear"></div>
											        </div>
											        </li>
																					    
												
												<li>
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatDCA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatDCA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCA" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageDestLatDCA_error" class="error"><spring:message code="error.blank.VILLAGECAatDCA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error1"><form:errors path="lgd_LBVillageDestLatDCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error2" style="display: none" ></div> 
													
												</li>
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBVillageSourceLatDCA"><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatDCA"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatDCA"
															multiple="true">

														</form:select>
												</div>	

													<div class="ms_buttons btnmargin" >
													    <input type="button"
														value=" Whole &gt;&gt;" class="bttn"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA','FULL',true,'V');" />
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn"
														onclick="removeItemLB('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn"
														onclick="removeAll('ddLgdLBVillageDestLatDCA', 'ddLgdLBVillageSourceLatDCA', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA', 'PART',true,'V');" />
													</div>

													 <div class="ms_selection">
													<label for="ddLgdLBVillageDestLatDCA"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBVillageDestLatDCA"
															size="1" multiple="true" path="lgd_LBVillageDestLatDCA"
															class="frmtxtarea" >
														</form:select><br />
														</div>
														<div class="clear"></div>
														</div>
														</li>
												
												</ul>
											
										<!-- 	<tr>
													<td colspan="3" width="100%">
														<div id="getHeadQuartersD"></div>
													</td>
											</tr> -->
											

										</div>
									 </div>
									
								
								
								<div class="frmpnlbghidden" style="visibility: hidden; display: none;">   
								 
								<!--  	<div class="frmpnlbghidden">
								 --> <form:select
											id="ddLgdLBDistPDestListhidden" size="1" multiple="true"
											path="lgd_LBDistPDestListhidden" class="frmtxtarea">
											
									</form:select>
									
									<form:select
											id="ddLgdLBDistPDestListhiddenforHeadQuarter" size="1" multiple="true"
											path="lgd_LBDistPDestListhiddenforHeadQuarter" class="frmtxtarea">
											
									</form:select>
									
								 </div>   
								

							
									
										<div id='divLgdLBInterCArea' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
											  <ul class="blocklist">
											  <li>
													
													<div id="ddLgdLBInterPDestList_error" class="error"><spring:message code="error.blank.INTERPANCHAYT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBInterPDestList_msg" style="display:none"><spring:message code="error.blank.INTERPANCHAYT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBInterPDestList_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList_error2" style="display: none" ></div> 
													<div id="ddLgdLBInterPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterPDestList1_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList1_error2" style="display: none"></div>
													
												
												</li>
												<li>
												 <div class="ms_container">
												 <div class="ms_selectable">
													<label for="ddLgdLBInterPSourceList"><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out>' &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span><br />
														 <form:select
															path="lgd_LBInterPSourceList" class="frmtxtarea"
															id="ddLgdLBInterPSourceList"
															multiple="true">

														</form:select>
												</div>		
													<div class="ms_buttons btnmargin" >
													   <input type="button"
														value="<spring:message
																code="Button.WHOLE"/>"  class="bttn" 
														onclick="addItemforLBCHCovFULL('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" />
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn"
														onclick="removeItemLevel9('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAll9('ddLgdLBInterPDestList', 'ddLgdLBInterPSourceList', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBCHCov('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBInterPDestList"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															name="select6" id="ddLgdLBInterPDestList" size="1"
															multiple="true" path="lgd_LBInterPDestList"
															class="frmtxtarea">
														</form:select>
															<input type="button" value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>" class="bttn"  onclick="selectLocalBodyListAtICAforChCoverage();" />
														</div> 
														 <div class="clear"></div>
														</div>
														</li>
												


												<li>
													
													
													<%-- <span class="errormsg"
														id="ddLgdLBInterCAreaDestL_error"><spring:message htmlEscape="true" 
																code="error.blank.SUBDISTRICTCAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterCAreaDestList" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBInterCAreaDestL_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterCAreaDestL_error1"><form:errors path="lgd_LBInterCAreaDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestL_error2" style="display: none" ></div>
													
													
													
												</li>


												<li>
												 <div class="ms_container">
												 <div class="ms_selectable">
													<label for="ddLgdLBInterCAreaSourceL"><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBInterCAreaSourceList"
															class="frmtxtarea" id="ddLgdLBInterCAreaSourceL"
															multiple="true">

														</form:select><br /> <br />
													</div>	
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforsubChangeCov('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','availddLgdLBInterCAreaSourceLUmapped','ddLgdInterSubDestListhidden','FULL',true,'T');" />
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemLevel10IP('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAllLevel10IP('ddLgdLBInterCAreaDestL', 'ddLgdLBInterCAreaSourceL', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforsubChangeCov('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','availddLgdLBInterCAreaSourceLUmapped','ddLgdInterSubDestListhidden','PART',true,'T');" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBInterCAreaDestL"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label><span class="errormsg">*</span> <br /> 
													<form:select name="select6"
															id="ddLgdLBInterCAreaDestL" size="1" multiple="true"
															path="lgd_LBInterCAreaDestList" class="frmtxtarea">
														</form:select>
														<input type="button" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>" class="bttn"  onclick="selectVillageICACovChng();" />
														</div>
														<div class="clear"></div>
													</div>
													</li>
												

												<li>
													
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICA_error"><spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatICA" /> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICA" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageDestLatICA_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error1"><form:errors path="lgd_LBVillageDestLatICA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error2" style="display: none" ></div>
													
													</li>

												<li>
												    <div class="ms_container">
												    <div class="ms_selectable">
													<label for="ddLgdLBVillageSourceLatICA"><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatICA"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatICA"
															multiple="true">

														</form:select><br /> <br />
													</div>	
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value=" Whole &gt;&gt;" class="bttn" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA','FULL',true,'V');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemLB('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAll('ddLgdLBVillageDestLatICA', 'ddLgdLBVillageSourceLatICA', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA', 'PART',true,'V');" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBVillageDestLatICA"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6" id="ddLgdLBVillageDestLatICA"
															size="1" multiple="true" path="lgd_LBVillageDestLatICA"
															class="frmtxtarea">
														</form:select>
													</div>	
													<div class="clear"></div>
													</div>
													</li>
												
												
											
											</ul>

										</div>
										</div>
									
								
								
								<div class="frmpnlbgInterhidden" style="visibility: hidden; display: none;"> 
								<!-- 	<div class="frmpnlbgInterhidden">  -->
								 	<form:select
											id="ddLgdInterSubDestListhidden" size="1" multiple="true"
											path="lgd_LBInterSubDestListhidden" class="frmtxtarea">

									</form:select>
								 </div> 
								
								
								
										<div id='divLgdLBVillageCArea' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
											  <ul class="blocklist">
											  <li>
													<div id="ddLgdLBVillageDestAtVillageCA_error" class="error"><spring:message code="error.blank.VILLAGEPANCHAYT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBVillageDestAtVillageCA_msg" style="display:none"><spring:message code="error.blank.VILLAGEPANCHAYT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none" ></div>
													<div id="ddLgdLBVillageDestAtVillageCA1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error2" style="display: none"></div> 
													
											  </li>
												
												<li>
												 <div class="ms_container">
												    <div class="ms_selectable">
													<label for="ddLgdLBVillageSourceAtVillageCA"><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
													</label><span class="errormsg">*</span><br /> 
													
													<form:select
															path="lgd_LBVillageSourceAtVillageCA" class="frmtxtarea"
															id="ddLgdLBVillageSourceAtVillageCA"
															multiple="true">

														</form:select><br /> <br />
														</div>

													
													
													<%-- <input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px" 
														onclick="addItemforLBChangeCovFULL('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
														<br /> --%>
												<div class="ms_buttons btnmargin" >
													
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLB('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" />
														 
														
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemLevel8('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAll8('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforChangeCoverage('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA', 'PART',true);" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBVillageDestAtVillageCA"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
													</label><span class="errormsg">*</span> <br /> 
													<form:select
															name="select6" id="ddLgdLBVillageDestAtVillageCA"
															size="1" multiple="true"
															path="lgd_LBVillageDestAtVillageCA" class="frmtxtarea">
														</form:select>
														 <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														class="bttn"  onclick="selectLocalBodyListAtVCAforModifyCoverageF();" />
	
														</div>
													 <div class="clear"></div>
													</div>
													</li>


												<li>
													
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageCAreaDestL_error"> <spring:message htmlEscape="true" 
																code="error.blank.VILLAGECAatICA" />
													</span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestL" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddLgdLBVillageCAreaDestL_error" class="error"><spring:message code="error.PSV" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error1"><form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error2" style="display: none" ></div>
													
													
												</li>
												<li>
													<div class="ms_container">
												    <div class="ms_selectable">
													<label for="ddLgdLBVillageCAreaSourceL"><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageCAreaSourceL"
															class="frmtxtarea" id="ddLgdLBVillageCAreaSourceL"
															multiple="true">

														</form:select><br /> <br />
														</div>
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforvillageMappedChangedCoverage('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','availddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" />
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemvillageModify('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAllVillageModify('ddLgdLBVillageCAreaDestL', 'ddLgdLBVillageCAreaSourceL', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforvillageMappedChangedCoverage('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','availddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" />
													</div>

												    <div class="ms_selection">
													<label for="ddLgdLBVillageCAreaDestL"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label><span class="errormsg">*</span><br />
														<form:select name="select6" id="ddLgdLBVillageCAreaDestL"
															size="1" multiple="true" path="lgd_LBVillageCAreaDestL"
															class="frmtxtarea">
														</form:select><br />
												</div>
												 <div class="clear"></div>
												 </div>
												</li>
												
											  </ul>

										</div>
										</div>
									
								
								
								
								
								
										<div id='divLgdLBUrban' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
											</div>
											
													 <ul class="blocklist">
											        <li>
													<div id="ddLgdUrbanLBDistExistDest_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
													<div id="ddLgdUrbanLBDistExistDest_msg" style="display:none"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error2" style="display: none"></div> 
													<div id="ddLgdUrbanLBDistExistDest1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
												<%--<div id="ddLgdUrbanLBDistExistDest_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div> --%>
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error2" style="display: none"></div> 
													
													
												</li>
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdUrbanLBDistExistSource"><spring:message
															code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><br /> <form:select
															path="lgd_UrbanLBDistExistSource" class="frmtxtarea"
															id="ddLgdUrbanLBDistExistSource"
															multiple="true">

														</form:select><br /> <br />
														</div>
														
														
														
														<%-- <input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 70px"
														onclick="addItem('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource','FULL',true);" />
														<br />  --%>
														<div class="ms_buttons btnmargin" >
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemLevel9('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAllItemLevel9Urban('ddLgdUrbanLBDistExistDest', 'ddLgdUrbanLBDistExistSource', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforUrbanChangeCoverage('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource', 'PART',true);" />
													   </div>
													<div class="ms_selection">			
													<label for="ddLgdUrbanLBDistExistDest"><spring:message
															code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message>
															&nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															name="select6" id="ddLgdUrbanLBDistExistDest" size="1"
															multiple="true" path="lgd_UrbanLBDistExistDest"
															class="frmtxtarea">
														</form:select>
														 <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														class="bttn"  onclick="selectLocalBodyListforUrbanforModF();" />
														
														</div>
												       <div class="clear"></div>
												        </div>
												        </li>
												 


												<li>
													
													<div id="ddLgdUrbanLBSubdistrictListDest_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdUrbanLBSubdistrictListDest_error1"><form:errors path="lgd_UrbanLBSubdistrictListDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdUrbanLBSubdistrictListDest_error2" style="display: none" ></div>
													
													
												</li>


												<li>
												<div class="ms_container">
												<div class="ms_selectable">
														<label for="ddLgdUrbanLBSubdistrictListSource">
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message></c:otherwise>
															</c:choose>
														</label><br />
														<form:select path="lgd_UrbanLBSubdistrictListSource"
															class="frmtxtarea" id="ddLgdUrbanLBSubdistrictListSource"
															multiple="true">
														</form:select><br /> 
													</div>
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforsub('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource','FULL',true,'T');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemLevel10('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAll10('ddLgdUrbanLBSubdistrictListDest', 'ddLgdUrbanLBSubdistrictListSource', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforsub('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource', 'PART',true,'T');" />
													</div>

													<div class="ms_selection">			
														<label for="ddLgdUrbanLBSubdistrictListDest">
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.CONTRIBUTDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message code="Label.CONTRIBUTSUBDISTRICTLIST" htmlEscape="true"></spring:message></c:otherwise>
															</c:choose>
														</label>
														<span class="errormsg">*</span><br />
														<form:select name="select6"
															id="ddLgdUrbanLBSubdistrictListDest" size="1" multiple="true"
															path="lgd_UrbanLBSubdistrictListDest" class="frmtxtarea">
														</form:select><br />
														</div>
												        <div class="clear"></div>
												        </div>
												        </li>

											
											</ul>

										</div>
										</div>
								
									
								
										<div id='divLgdLBDistCAreaUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											
												<%-- <span class="errormsg"
													id="ddLgdLBDistCAreaDestLUmapped_error"><spring:message htmlEscape="true" 
															code="error.DESTDISTLOCALBODY"></spring:message> </span>&nbsp;<span><form:errors htmlEscape="true" 
															path="lgd_LBDistCAreaDestListUmapped" class="errormsg"></form:errors>
												</span> --%>
												<%-- <div id="ddLgdLBDistCAreaDestLUmapped_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
												<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error1"><form:errors path="lgd_LBDistCAreaDestListUmapped" htmlEscape="true"/></div>  
												<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error2" style="display: none" ></div> --%>
												
												<ul class="blocklist">
												<li>
												
												<div id="ddLgdLBDistCAreaDestLUmapped_error" class="error"><spring:message code="error.PSDT" htmlEscape="true"></spring:message></div> 
												<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error1"><form:errors path="lgd_LBDistCAreaDestListUmapped" htmlEscape="true"/></div>  
												<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>
												</li>
												<li>
                                                 <div class="ms_container">
												  <div class="ms_selectable">
													<label for="ddLgdLBDistCAreaSourceLUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBDistCAreaSourceListUmapped"
															class="frmtxtarea" id="ddLgdLBDistCAreaSourceLUmapped"
															multiple="true">
															<form:options items="${UnmappedData}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="landRegionCode" />
														</form:select><br /> <br />
														
													</div>			
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforDistforCovChange('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','FULL',true,'D');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemDistrict('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAllDistrict('ddLgdLBDistCAreaDestLUmapped', 'ddLgdLBDistCAreaSourceLUmapped', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforDistforCovChange('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped', 'PART',true,'D');" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBDistCAreaDestLUmapped"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label><span class="errormsg">*</span> <br />
														<form:select name="select6"
															id="ddLgdLBDistCAreaDestLUmapped" size="1"
															multiple="true" path="lgd_LBDistCAreaDestListUmapped"
															class="frmtxtarea" >
														</form:select><br /> 
														 <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>"
														class="bttn" 
														onclick="refreshOptions('ddLgdLBSubDistrictSourceLatDCAUmapped');getUnmappedLBSDPListatUmappedFinal('T','${localGovtBodyForm.lbType}','${levelcheck}');" />
														
														</div>
														<div class="clear"></div>
														</div>
												</li>

												
													<%-- <span class="errormsg"
														id="ddLgdLBSubDistrictDestLatDCAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBSubDistrictDestLatDCAUmapped"
																class="errormsg"></form:errors> </span> --%>
													<%-- <div id="ddLgdLBSubDistrictDestLatDCAUmapped_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error1"><form:errors path="lgd_LBSubDistrictDestLatDCAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>	 --%>		
																
													
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBSubDistrictSourceLatDCAUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped"
															class="frmtxtarea"
															id="ddLgdLBSubDistrictSourceLatDCAUmapped"
															multiple="true">

														</form:select><br /> <br />
													</div>	
														<div class="ms_buttons btnmargin" >
													    <input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','FULL',true,'T');" />
														<input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAllSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped', 'ddLgdLBSubDistrictSourceLatDCAUmapped', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped', 'PART',true,'T');" />
													</div>
													<div class="ms_selection">	
													<label for="ddLgdLBSubDistrictDestLatDCAUmapped"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBSubDistrictDestLatDCAUmapped" size="1"
															multiple="true" path="lgd_LBSubDistrictDestLatDCAUmapped"
															class="frmtxtarea">
														</form:select><br /> 
														 <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														class="bttn" 
														onclick="refreshOptions('ddLgdLBVillageSourceLatDCAUmapped');getUnmappedLBVillPListatUmappedFinal('V','${localGovtBodyForm.lbType}','${levelcheck}');" />
														</div>
													<div class="clear"></div>
													</div>
												</li>
												
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatDCAUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCAUmapped" class="errormsg"></form:errors>
													</span> --%>
													<%-- <div id="ddLgdLBVillageDestLatDCAUmapped_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatDCAUmapped_error1"><form:errors path="lgd_LBVillageDestLatDCAUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatDCAUmapped_error2" style="display: none" ></div>		 --%>
													
													
												<li>
													<div class="ms_container">
												    <div class="ms_selectable">
													<label for="ddLgdLBVillageSourceLatDCAUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatDCAUmapped"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatDCAUmapped"
															multiple="true">

														</form:select><br /> <br />
														</div>
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped','FULL',true,'V');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemVillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAll('ddLgdLBVillageDestLatDCAUmapped', 'ddLgdLBVillageSourceLatDCAUmapped', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped', 'PART',true,'V');" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBVillageDestLatDCAUmapped"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageDestLatDCAUmapped" size="1"
															multiple="multiple" path="lgd_LBVillageDestLatDCAUmapped"
															class="frmtxtarea">
														</form:select><br />
													</div>	
													<div class="clear"></div>
												</div>

											</li>
											</ul>
											</div>
										</div>
									
								
										<div id='divLgdLBInterCAreaUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<ul class="blocklist">
											<li>
													
													<div id="ddLgdLBInterCAreaDestLUmapped_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error1"><form:errors path="lgd_LBInterCAreaDestListUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error2" style="display: none" ></div>
													
													
												</li>

												<li>
													<div class="ms_container">
												    <div class="ms_selectable">
													<label for="ddLgdLBInterCAreaSourceLUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBInterCAreaSourceListUmapped"
															class="frmtxtarea" id="ddLgdLBInterCAreaSourceLUmapped"
															multiple="true">
															<form:options items="${UnmappedData}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="landRegionCode" />
														</form:select><br /> <br />
													</div>	

													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" class="bttn" 
														onclick="addItemforLBforsubUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','FULL',true,'T');" />
														
														
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" class="bttn" 
														onclick="removeItemSubdistrictUnmapped('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														class="bttn" 
														onclick="removeAllSubdistrictUnmapped('ddLgdLBInterCAreaDestLUmapped', 'ddLgdLBInterCAreaSourceLUmapped', true)" />
														<input type="button" value="Part &gt;&gt;"
														class="bttn" 
														onclick="addItemforLBforsubUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped', 'PART',true,'T');" />
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBInterCAreaDestLUmapped"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <span class="errormsg">*</span><br /> <form:select name="select6"
															id="ddLgdLBInterCAreaDestLUmapped" size="1"
															multiple="true" path="lgd_LBInterCAreaDestListUmapped"
															class="frmtxtarea">
														</form:select><br /> 
														 <input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														class="bttn" 
														onclick="refreshOptions('ddLgdLBVillageSourceLatICAUmapped');selectSubDistrictAtICAUmapped('V','${localGovtBodyForm.lbType}','${levelcheck}');" />
														
														</div>
														<div class="clear"></div>
														</div>
												</li>

												
													<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span> --%>
													
													
												

												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBVillageSourceLatICAUmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatICAUmapped"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatICAUmapped"
															multiple="true">

														</form:select><br /> <br />
												</div>		
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>"  class="bttn" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped','FULL',true,'V');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"  class="bttn" 
														onclick="removeItemVillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														 class="bttn" 
														onclick="removeAll('ddLgdLBVillageDestLatICAUmapped', 'ddLgdLBVillageSourceLatICAUmapped', true)" />
														<input type="button" value="Part &gt;&gt;"
														 class="bttn" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped', 'PART',true,'V');" />
													</div>
													<div class="ms_selection">	
													<label for="ddLgdLBVillageDestLatICAUmapped"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageDestLatICAUmapped" size="1"
															multiple="true" path="lgd_LBVillageDestLatICAUmapped"
															class="frmtxtarea">
														</form:select>
													</div>	
												<div class="clear"></div>
												</div>
											</li>
											</ul>
											</div>
										</div>
									
									
									
										<div id='divLgdLBVillageCAreaUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true" code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<ul class="blocklist">
											<li>
													
													<%-- <span class="errormsg"
														id="ddLgdLBVillageCAreaDestLUnmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageCAreaDestLUnmapped" class="errormsg"></form:errors>
													</span> --%>
													
													<div id="ddLgdLBVillageCAreaDestLUnmapped_error" class="error"><spring:message code="error.PSCV" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error1"><form:errors path="lgd_LBVillageCAreaDestLUnmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error2" style="display: none" ></div>
													
													</li>
												
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label for="ddLgdLBVillageCAreaSourceLUnmapped"><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageCAreaSourceLUnmapped"
															class="frmtxtarea"
															id="ddLgdLBVillageCAreaSourceLUnmapped"
															multiple="true">
															
															  <c:forEach items="${UnmappedData}" var="UnmappedData">
																  <c:if test="${UnmappedData.operation_state == 'F'.charAt(0)}">
																    <form:option value="${UnmappedData.landRegionCode}" disabled="true"><c:out value="${UnmappedData.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																  </c:if>  
																   <c:if test="${UnmappedData.operation_state == 'A'.charAt(0)}">
																    <form:option value="${UnmappedData.landRegionCode}"><c:out value="${UnmappedData.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																  </c:if>
														      </c:forEach>

														</form:select><br /> <br />
													</div>	
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>"  class="bttn" 
														onclick="addItemforLBforvillageMappedUnMappedCovChange('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"  class="bttn" 
														onclick="removeItemVillUnmapped('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														 class="bttn" 
														onclick="removeAllVillUnmappedF('ddLgdLBVillageCAreaDestLUnmapped', 'ddLgdLBVillageCAreaSourceLUnmapped', true)" />
														<input type="button" value="Part &gt;&gt;"
														 class="bttn" 
														onclick="addItemforLBforvillageMappedUnMappedCovChange('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" />
														
													</div>
													<div class="ms_selection">
													<label for="ddLgdLBVillageCAreaDestLUnmapped"><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label><span class="errormsg">*</span> <br />
														<form:select name="select6"
															id="ddLgdLBVillageCAreaDestLUnmapped" size="1"
															multiple="true" path="lgd_LBVillageCAreaDestLUnmapped"
															class="frmtxtarea">
														</form:select><br />
														</div>
														<div class="clear"></div>
														</div>
												</li>


											</ul>
											</div>
										</div>
									
										<div id='divLgdLBUrbanUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message>
											</div>
											<ul class="blocklist">
											<li>
											
													<div id="ddLgdUrbanLBDistUmappedDest_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdUrbanLBDistUmappedDest_error1"><form:errors path="lgd_UrbanLBDistUmappedDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdUrbanLBDistUmappedDest_error2" style="display: none" ></div>	
												
												</li>
									
												<li>
												<div class="ms_container">
												<div class="ms_selectable">
														<label for="ddLgdUrbanLBDistUmappedSource">
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </c:otherwise>
															</c:choose>
														</label><br />
														<form:select path="lgd_UrbanLBDistUmappedSource"
															class="frmtxtarea" id="ddLgdUrbanLBDistUmappedSource"
															multiple="true">
														</form:select><br /> <br />
													</div>
													<div class="ms_buttons btnmargin" >
													<input type="button"
														value="<spring:message
																code="Button.WHOLE"/>"  class="bttn" 
														onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource','FULL',true,'T');" />
														 <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"  class="bttn" 
														onclick="removeItemSubdistrictUnmapped('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														 class="bttn" 
														onclick="removeAll11('ddLgdUrbanLBDistUmappedDest', 'ddLgdUrbanLBDistUmappedSource', true)" />
														<input type="button" value="Part &gt;&gt;"
														 class="bttn" 
														onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource', 'PART',true,'T');" />
													</div>

													<div class="ms_selection">
														<label for="ddLgdUrbanLBDistUmappedDest">
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.CONTRIBUTDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message> </c:otherwise>
															</c:choose>
														</label>
														<span class="errormsg">*</span><br /> 
														<form:select name="select6"
															id="ddLgdUrbanLBDistUmappedDest" size="1"
															multiple="true" path="lgd_UrbanLBDistUmappedDest"
															class="frmtxtarea">
														</form:select><br />
														</div>
														
													<div class="clear"></div>	
													</div>
												</li>

												<li>
													
													<span class="errormsg"
														id="ddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span>
												</li>

											</ul>
											</div>
										</div>
									
						</div>
						<!--Begining of Govt Order Details  -->

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>

							<ul class="blocklist">
							<li>
							
								
							<form:hidden path="orderCode"
											id="hdnOrderCode" value="${localGovtBodyForm.orderCode}"/>
							</li>		
									<li><label for="OrderNo"><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> 
										<form:input
											path="lgd_LBorderNo" id="OrderNo" type="text" maxLength="60"
											class="frmfield"											
											 onkeypress="return validateaGovtOrderNOforModify(event);" 
											onfocus="validateOnFocus('OrderNo');helpMessage(this,'OrderNo_error');"
									         onblur="hideHelp();vlidateOrderNo('OrderNo','1','60');"
											onkeyup="hideMessageOnKeyPress('OrderNo')" />
											
										
										
												
											<div id="OrderNo_error" class="errormsg"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
									<div id="OrderNo_msg" class="errormsg"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
										
											
											
											<div class="errormsg" id="OrderNo_error1"><form:errors path="lgd_LBorderNo" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
										</li>
								
								<li>
									<label for="OrderDate"><spring:message code="Label.ORDERDATE" htmlEscape="true"  ></spring:message>
									</label><span class="errormsg">*</span><br /> 
									<form:input
											path="lgd_LBorderDate" id="OrderDate" type="text"
											class="frmfield" 
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" htmlEscape="true"/> 
											
											<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
											<div id="OrderDate_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderDate_error1"><form:errors path="lgd_LBorderDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderDate_error2" style="display: none" ></div>
										
									</li>
								
								<li>
									<label for="EffectiveDate"><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> 
										<form:input
											id="EffectiveDate" path="lgd_LBeffectiveDate" type="text"
											class="frmfield" 
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
											
											<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="lgd_LBeffectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
											
										
								</li>
								
								<li>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								
									<label for="GazPubDate"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>
									</label> <br /> <form:input id="GazPubDate" path="gazPubDate"
											type="text" class="frmfield"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
									
											<div id="GazPubDate_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none" ></div>		
												
										
							
								
								</c:if>
								</li>
								<li>
									<form:hidden path="govtOrderConfig"
											value="${govtOrderConfig}" id="hdngovtOrderConfig" />
									
								</li>
								<li>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								
									<%@ include file="../common/update_attachment.jspf"%>
								
								</c:if>
								</li>
								<li>
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
									
<!-- 										<td width="14%" rowspan="2">&nbsp;</td> -->
										<label for="templateList"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
										class="errormsg">*</span><br /> 
										<form:select
												path="templateList" id="templateList" 
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
												
									
								</c:if>
								</li>
								</ul>
							
						</div>
					</div>
					
					
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
											<form:hidden path="lbType"
												value="${localGovtBodyForm.lbType}"/>
											<input type="submit" name="Submit"
												value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>"
												onclick="return validateModifyCoverageLB();" />
											
											<!--  -->
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'P')}">
												 <input type="button" class="btn"
													name="Submit6"
													value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='modifyGovtLocalBodyMainforcoverageareaClear.htm?<csrf:token uri="modifyGovtLocalBodyMainforcoverageareaClear.htm"/>'" />
												
											</c:if>
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'T')}">
												 <input type="button" class="btn"
													name="Submit6"
													value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='modifyGovtLocalBodyMainforcoverageareaClear.htm?<csrf:token uri="modifyGovtLocalBodyMainforcoverageareaClear.htm"/>'" />
												
											</c:if>
											<input type="button" class="btn"
												name="Submit6"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											
										</div>
								
						
					</div>
							</div>
						</div>

				</div>
		
			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

	<!-- </div> -->
</body>
</html>