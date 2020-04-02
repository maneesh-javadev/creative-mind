<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/new_ward.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script> -->
<!-- <link rel="stylesheet" href="datepicker/demos.css" /> -->

<script type="text/javascript" language="javascript">


	function manageWard(url,id)
	{
		dwr.util.setValue('wardId', id, {	escapeHtml : false	});
		//document.getElementById('form1').method = "GET";
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
	}
	
	function manageDeleteWard(url,id)
	{
		var reset = confirm("Do you wish to delete the selected Ward?");
		if(reset==true)
		{	
			dwr.util.setValue('wardId', id, {	escapeHtml : false	});
			//document.getElementById('form1').method = "GET";
			document.getElementById('form1').action = url;
			document.getElementById('form1').submit();
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	function doRefresh(removeAll) {
		if (removeAll) {
			document.getElementById('tierSetupCode').selectedIndex = 0;
			removeSelectedValue('ddSourceLocalBody');
			removeSelectedValue('localGovtBodyListMain');
			removeSelectedValue('localGovtBodyListVillage');
		}

	}

	function removeSelectedValue(val) {
		var elSel = document.getElementById(val);
		var i;
		for (i = elSel.length - 1; i >= 0; i--)
			elSel.remove(i);
	}
	
	function doValidate()
	{
		if (document.getElementById('ddLgdLBType').selectedIndex==0)
			{
				alert("Please select a localbody type first");
				return false;
			}
		if (document.getElementById('ddLgdLBType').value.indexOf(":D:")!=-1)
			{
			localbdytyp=1;
			if (document.getElementById('ddSourceLocalBody').selectedIndex==0)
				{
					alert("Please select a District Level localbody first");
					return false;
				}
			}
		if (document.getElementById('ddLgdLBType').value.indexOf(":I:")!=-1)
			{
			localbdytyp=2;
			if (document.getElementById('ddLgdLBDistListAtInterCA').selectedIndex==0)
			{
				alert("Please select a District Level localbody first");
				return false;
			}
			else if (document.getElementById('ddLgdLBInterPSourceList').selectedIndex==0)
			{
				alert("Please select an Intermediate Level localbody first");
				return false;
			}
			}
		
		if (document.getElementById('ddLgdLBType').value.indexOf(":V:")!=-1)
			{
			localbdytyp=3;
			if (document.getElementById('ddLgdLBDistListAtVillageCA').selectedIndex==0)
			{
				alert("Please select a District Level localbody first");
				return false;
			}
			else if (document.getElementById('ddLgdLBInterListAtVillageCA').selectedIndex==0)
			{
				alert("Please select an Intermediate Level localbody first");
				return false;
			}
			else if (document.getElementById('ddLgdLBVillageSourceAtVillageCA').selectedIndex==0)
			{
				alert("Please select a Village Level localbody first");
				return false;
			}
			}
		
		document.forms['form1'].submit();
	}
	
	function checkParent(val)
	{
		if (document.getElementById('ddLgdLBType').value.indexOf(":D:")==-1)
			getLGBforCoveredDistListExWard(val);
	}
	
	function setDirection(val)
	{
		//alert("inside wrad");
		document.getElementById('direction').value=val;
		document.forms['form1'].action = "viewManagegeWardPagination.htm?<csrf:token uri='viewManagegeWardPagination.htm'/>";
		document.forms['form1'].submit();
	}
</script>
<%@include file="../common/dwr.jsp"%> 
</head>

<!-- <body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);"> -->
<body onload="loadWardFormtoretain();">	
	<%-- <div class="overlay" id="overlay1" style="display: none;"></div>
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
								<center><c:out value="${param.family_msg}"></c:out></center>
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
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
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
					<td><div class="errorfont"><spring:message code="error.blank.commonAlert"></spring:message></div>
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
	</div> --%>



	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.VIEWWARD"></spring:message></td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Label.HELP"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="viewWardPRIAndTRI.htm" method="POST" id="form1" name="form1" commandName="localGovtBodyForm">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="viewWardPRIAndTRI.htm"/>" />
				<input type='hidden' name=hdnType id="hdnType" value="<c:out value='${typeCode}'/>"/>
				<input type='hidden' name="hdnIntermediatePanchayat"
					id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}'/>"/>
				<input type='hidden' name=hdnDistrictPanchayat
					id="hdnDistrictPanchayat" value="<c:out value="${hdnDistrictPanchayat}'/>"/>
				<input type='hidden' id="hdnStateCode"
					value='<%=request.getAttribute("stateCode")%>' />
					<input type='hidden' name=lbTypeDetail id="lbTypeDetail" value="<c:out value='${lbTypeDetail}'/>"/>
				<%-- 	<form:input htmlEscape="true" path="lblc"
																		id="lblc" type="hidden" class="frmfield"
																		value="${localGovtBodyForm.localBodyNameEnglishList}" />
                  <form:input htmlEscape="true" path="ward_Name"
																		id="ward_Name" type="hidden" class="frmfield"
																		value="${localGovtBodyForm.ward_Name}" /> --%>
                  																		

				<div id="cat">
					<!-- For Localbody list for different district, Intermediate, Village Panchayat  -->
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td style="width: 100%">
								<div class="frmpnlbg">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.SEARCHCRIT"></spring:message>
										</div>

										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%" rowspan="8"><input type="hidden" id="hdnStateCode" name="stateCode" value="<c:out value='${stateCode}'/>"/>
													<input type="hidden" name="districtCode" value="<c:out value='${districtCode}'/>"/>
													<input type="hidden" name="lbType" value="<c:out value='${lbType}'/>"/>
													<input type="hidden" id="level" />
													<input type="hidden" name="flagCode" id="flagCode" />
													
												</td>
												<td width="86%"><label><spring:message
															code="Label.SELECTLOCALBODYTYPE">
												 </spring:message></label><span class="errormsg">*</span><br /> <form:select
														path="lgd_LBTypeName" id="ddLgdLBType" htmlEscape="true"
														onfocus="validateOnFocus('ddLgdLBType');"
														onchange="hideShowDivforViewWardF(this.value,'${districtCode}','${lbType}','${stateCode}');"
														onkeydown="validateOnKeyPessDown(event,'ddLgdLBType','char');"
														onblur="vlidateOnblur('ddLgdLBType','1','15','c');"
														class="combofield" style="width: 175px">
														<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>

														<c:forEach var="localBodyTypelist" varStatus="var"
															items="${localBodyTypelist}">
															<form:option id="typeCode"
																		value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}:${localBodyTypelist.tierSetupCode}:${localBodyTypelist.parentLocalBodyTypeCode}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
														
														</c:forEach>
													</form:select> &nbsp;&nbsp;  <%-- <span
													class="errormsg" id="ddLgdLBType_error"><spring:message
															code="error.choose.WARDLBTYPE"></spring:message> </span> --%>
															
													<div id="ddLgdLBType_error" class="error"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>		
												</td>			
											</tr>
											 <tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<div id="tr_district1" style="visibility: hidden; display: none;">
														<table>
															<tr>
														<%-- 	<c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}"> --%>
																<td><label> <spring:message
																			code="Label.SELECT" htmlEscape="true"></spring:message>
																</label><label id="districtDP"></label><span class="errormsg">*</span><br /> <form:select
																		path="localBodyNameEnglishList" class="combofield" htmlEscape="true"
																		id="ddSourceLocalBody" style="width: 175px"
																		onfocus="validateOnFocus('ddSourceLocalBody');"
																		onchange="checkParent(this.value)"
																		onkeydown="validateOnKeyPessDown(event,'ddSourceLocalBody','char');"
																		onblur="vlidateOnblur('ddSourceLocalBody','1','15','c');">

																		<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>

																		<form:options items="${districtPanchayatList}"
																			itemLabel="localBodyNameEnglish"
																			itemValue="localBodyCode" />
																	</form:select><br/> &nbsp;&nbsp;  <%-- <span
																	class="errormsg" id="ddSourceLocalBody_error"><spring:message
																			code="error.blank.DISTRICTP"></spring:message> </span> --%>
																	<div id="ddSourceLocalBody_error" class="error"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message></div>
																	<div class="errormsg" id="ddSourceLocalBody_error1"><form:errors path="localBodyNameEnglishList" htmlEscape="true"/></div>  
																	<div class="errormsg" id="ddSourceLocalBody_error2" style="display: none" ></div>			
																			
																</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															<%-- </c:if> --%>
															</tr>
															<tr>
																<td><form:input htmlEscape="true" path="lgd_lbCategory"
																		id="hiddenCheckBox" type="hidden" class="frmfield"
																		value="${localGovtBodyForm.lgd_lbCategory}"/>
																</td>
															</tr>
														</table>
													</div></td>

											</tr>
											<tr>
												<td>
													<div id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">

																<table>
																	<tr>
																	<%-- <c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}"> --%>
																		<td><label> <spring:message
																					code="Label.SELECT"></spring:message>
																		</label><label id="firstlevel"></label><span class="errormsg">*</span><br /> <form:select
																				path="lgd_LBDistListAtInterCA" style="width:175px" htmlEscape="true"
																				class="combofield" id="ddLgdLBDistListAtInterCA"
																				onfocus="validateOnFocus('ddLgdLBDistListAtInterCA');"
																				onchange="getInterPanchayatWardbyDcode(this.value);"
																				onkeydown="validateOnKeyPessDown(event,'ddLgdLBDistListAtInterCA','char')"
																				onblur="vlidateOnblur('ddLgdLBDistListAtInterCA','1','15','c');">
 
																				<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>

																				<form:options items="${districtPanchayatList}"
																					itemLabel="localBodyNameEnglish"
																					itemValue="localBodyCode" />
																			</form:select><br/>  &nbsp;&nbsp; 
																			<%-- <span class="errormsg"
																			id="ddLgdLBDistListAtInterCA_error"><spring:message
																					code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span> --%>
																			
																			<div id="ddLgdLBDistListAtInterCA_error" class="error"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message></div>
																			<div class="errormsg" id="ddLgdLBDistListAtInterCA_error1"><form:errors path="lgd_LBDistListAtInterCA" htmlEscape="true"/></div>  
																			<div class="errormsg" id="ddLgdLBDistListAtInterCA_error2" style="display: none" ></div>		
																					
																		</td>
																	<%-- </c:if>	 --%>
																	</tr>
																	<tr>
																		<td>&nbsp;</td>
																</tr>
																</table>
																</div>
																<div id="tr_intermediate1" style="visibility: hidden; display: none;">
																	<table>
																	<tr>
																<%-- 	<c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}"> --%>
																		<td><label> <spring:message
																					code="Label.SELECT"></spring:message>
																		</label><label id="districtIP"></label><span class="errormsg">*</span><br /> <form:select
																				path="lgd_LBInterPSourceList" class="combofield"
																				id="ddLgdLBInterPSourceList" style="width: 180px" htmlEscape="true"
																				onfocus="validateOnFocus('ddLgdLBInterPSourceList');helpMessage(this,'ddLgdLBInterPSourceListMsg');"
																				onchange="getLGBforCoveredSubDistListExWard(this.value);"
																				onkeydown="validateOnKeyPessDown(event,'ddLgdLBInterPSourceList','char')"
																				onblur="vlidateOnblur('ddLgdLBInterPSourceList','1','15','c');hideHelp();">
																			 
																				<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>
																			</form:select><br/> &nbsp;&nbsp; <%-- <span id="ddLgdLBInterPSourceList_error"
																			class="errormsg"><spring:message
																					code="error.blank.INTERMEDIATEP"></spring:message>
																		</span> <br />  <br />--%> <!-- <div id="ddLgdLBInterPSourceListMsg" style="display: none">please
																		select Intermediate Panchayat</div>
																			<div class="error" id="ddLgdLBInterPSourceList_error">Select
																				Intermediate Panchayat</div> --> 
																			<div id="ddLgdLBInterPSourceList_error" class="error"><spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message></div>
																			<div class="errormsg" id="ddLgdLBInterPSourceList_erro1r"><form:errors path="lgd_LBInterPSourceList" htmlEscape="true"/></div>  
																			<div class="errormsg" id="ddLgdLBInterPSourceList_error2" style="display: none" ></div>		
																			
																		</td>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																	<%-- </c:if> --%>
																	</tr>

																</table>
															</div></td>
											</tr>
											<tr>
												<td>
													<div id="divLgdVillageP" style="visibility: hidden; display: none;">

														<table>
															<%-- <c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureDist}"> --%>
															<tr>
																<td><label> <spring:message
																			code="Label.SELECT"></spring:message>
																</label><label id="secondlevel"></label><span class="errormsg">*</span><br /> <form:select
																				path="lgd_LBDistListAtVillageCA" class="combofield" htmlEscape="true"
																				style="width: 180px" id="ddLgdLBDistListAtVillageCA"
																				onfocus="validateOnFocus('ddLgdLBDistListAtVillageCA');helpMessage(this,'ddLgdLBDistListAtVillageCAMsg');"
																				onchange="getInterPanchayatbyDcodeAtVWardFinal(this.value);"
																				onkeydown="validateOnKeyPessDown(event,'ddLgdLBDistListAtVillageCA','char')"
																				onblur="vlidateOnblur('ddLgdLBDistListAtVillageCA','1','15','c');hideHelp();">

																		<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>

																		<form:options items="${districtPanchayatList}"
																			itemLabel="localBodyNameEnglish"
																			itemValue="localBodyCode" />
																	</form:select><br/> &nbsp;&nbsp; <%-- <span class="errormsg"
																	id="ddLgdLBDistListAtVillageCA_error"><spring:message
																			code="error.SOURCESELECTLOCALBODYPARENT"></spring:message>
																</span> --%>
																<div id="ddLgdLBDistListAtVillageCA_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
																<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error1"><form:errors path="lgd_LBDistListAtVillageCA" htmlEscape="true"/></div>  
																<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error2" style="display: none" ></div>		
																			
																
																</td>
															</tr>
															<%-- </c:if> --%>
															<tr>
																<td>&nbsp;</td>
															</tr>
														</table>
														</div>
														<div id="divLgdVillagePanch" style="visibility: hidden; display: none;">
														<table>	
															<tr>
															<%-- <c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureInter}"> --%>
																<td><label> <spring:message
																			code="Label.SELECT"></spring:message>
																</label><label id="thirdlevel"></label><span class="errormsg">*</span><br /> <form:select
																		path="lgd_LBInterListAtVillageCA" class="combofield"
																		id="ddLgdLBInterListAtVillageCA" style="width: 180px" htmlEscape="true"
																		onfocus="validateOnFocus('ddLgdLBInterListAtVillageCA');"
																		onchange="getVillagePanchWardbyIntercode(this.value);"
																		onkeydown="validateOnKeyPessDown(event,'ddLgdLBInterListAtVillageCA','char')"
																		onblur="vlidateOnblur('ddLgdLBInterListAtVillageCA','1','15','c');">

																		<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>
																		<%-- <form:options items="${intermediatePanchayatList}"
																					itemLabel="localBodyNameEnglish"
																					itemValue="localBodyCode" /> --%>	


																	</form:select><br/>  &nbsp;&nbsp;<%-- <span
																	class="errormsg" id="ddLgdLBInterListAtVillageCA_error"><spring:message
																			code="error.blank.INTERMEDIATEP"></spring:message> </span> --%>
																	<div id="ddLgdLBInterListAtVillageCA_error" class="error"><spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message></div>
																	<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error1"><form:errors path="lgd_LBInterListAtVillageCA" htmlEscape="true"/></div>  
																	<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error2" style="display: none" ></div>		
																			
																	</td>
															<%-- </c:if> --%>				
															</tr>
															
															<tr>
																<td>&nbsp;</td>
															</tr>
															</table>
															</div>
															<div id="tr_village" style="visibility: hidden; display: none;">
															<table>
															<tr>
															<%-- <c:if test="${! empty localGovtBodyForm.lgd_LBNomenclatureVillage}"> --%>
																<td><label><spring:message
																			code="Label.SELECT"></spring:message>
																</label><label id="districtVP"></label><span class="errormsg">*</span> <br /> <form:select
																		path="localBodyNameEnglishListSourceVillg"
																		class="combofield" style="width: 180px"
																		id="ddLgdLBVillageSourceAtVillageCA" htmlEscape="true"
																		onfocus="validateOnFocus('ddLgdLBVillageSourceAtVillageCA');"
																		onkeydown="validateOnKeyPessDown(event,'ddLgdLBVillageSourceAtVillageCA','char')"
																		onblur="vlidateOnblur('ddLgdLBVillageSourceAtVillageCA','1','15','c');">

																		<form:option value=""><spring:message htmlEscape="true"  code="Label.SEL"></spring:message></form:option>
																		<%-- <form:options items="${villagePanchayatList}"
																					itemLabel="localBodyNameEnglish"
																					itemValue="localBodyCode" /> 	
 --%>
																	</form:select><br/> &nbsp;&nbsp; 
																	<%-- <span
																	class="errormsg"
																	id="ddLgdLBVillageSourceAtVillageCA_error"><spring:message
																			code="error.blank.VILLAGEP"></spring:message> </span> --%>
																	<div id="ddLgdLBVillageSourceAtVillageCA_error" class="error"><spring:message code="Error.NoVPSelected" htmlEscape="true"></spring:message></div>
																	<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error1"><form:errors path="localBodyNameEnglishListSourceVillg" htmlEscape="true"/></div>  
																	<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error2" style="display: none" ></div>			
																			
																</td>
																<%-- </c:if> --%>
																</tr>
														</table>
													</div></td>
											</tr> 
											
											<tr>
	 <!-- correctly aligned no. of records label 22/05/2012 -->
	<%-- <td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td> --%>
						</tr>

						<tr>
							<td height="50" align="center">
							<label> <%-- <input type="button"
									name="Submit" class="btn"  onclick="doValidate();"									
									value="<spring:message code="Button.GETDATA"></spring:message>" />  --%>
									<input type="submit"
									name="Submit" class="btn" onclick="return validateViewWardPage();"  								
									value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>" />  
							</label> 
							<label> <input type="button" name="Submit2"
								class="btn"
								value="<spring:message code="Button.CLEAR"></spring:message>"
								onclick="javascript:location.href='viewwardforTraditional.htm?<csrf:token uri='viewwardforTraditional.htm'/>';" /></label>
				
 							  <label><input
										type="button" name="Submit3" class="btn"
										value="<spring:message code="Button.CLOSE"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
							</td>
						</tr>
										</table>
									</div>

								</div>
							</td>
						</tr>
						



					</table>
				</div>
				<%-- </form:form>
				<script src="/LGD/JavaScriptServlet"></script> --%>
		
		
		<!-- End here Localbody  -->
	
	
	
	<c:if test="${! empty wardList}">
		<div class="frmpnlbg">
			<div class="frmtxt">
				<table width="100%" class="tbl_no_brdr">
					<tr>
						<td width="90%" align="center">
							<table class="tbl_with_brdr" width="98%" align="center">
								<tr class="tblRowTitle tblclear">
									<td rowspan="2"><spring:message code="Label.SNO"></spring:message>
									</td>
									<td width="20%" rowspan="2"><spring:message
											code="Label.WARDNUMBER"></spring:message></td>
									<td width="20%" rowspan="2"><spring:message
											code="Label.WARDNAMEINENG"></spring:message></td>
									<td colspan="21%" rowspan="2"><spring:message
											code="Label.WARDNAMEINLOCAL"></spring:message></td>

									<td colspan="6" align="center"><spring:message
											code="Label.ACTION"></spring:message></td>
								</tr>
								<tr class="tblRowTitle tblclear">

									<td width="6%" align="center"><spring:message
											code="Label.VIEW"></spring:message></td>
									<td width="6%" align="center"><spring:message
											code="Label.MODIFY"></spring:message></td>
									<%-- <td width="6%" align="center"><spring:message
											code="Label.DELETE"></spring:message></td> --%>
								</tr>



								<c:forEach var="wardList" varStatus="listLocalBodyRow"
									items="${wardList}">
									<tr class="tblRowB">
										<td width="6%"><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
										<td width="16%" align="left"><c:out
												value="${wardList.wardNumber}" escapeXml="true"></c:out>
										</td>
										<td width="16%" align="left"><c:out
												value="${wardList.wardNameInEnglish}" escapeXml="true"></c:out>
										</td>
										<td colspan="21%" align="left"><c:out
												value="${wardList.wardNameInLocal}" escapeXml="true"></c:out>
										</td>
										<%-- <td align="center"><a href="viewWardDetails.htm?id=${wardList.wardCode}&<csrf:token uri='viewWardDetails.htm'/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td> --%>
										
										<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageWard('viewWardDetails.htm',${wardList.wardCode});" width="18" height="19" border="0" /></a></td>
										
										
										<!-- <td align="center"><a href="#"><img
												src="images/view.png" width="18" height="19" border="0" />
										</a>
										</td> -->
										<td width="8%" align="center"><a href="#"><img	src="images/edit.png" onclick="javascript:manageWard('modifyWard.htm',${wardList.wardCode});" width="18" height="19" border="0" /></a></td>
										
										<%--<td align="center"><a href="invalidateWard.htm?wardCode=${wardList.wardCode}&<csrf:token uri="invalidateWard.htm"/>"><img	src="images/delete.png" width="18" height="19" border="0" /> --%>
									  <%--   <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDeleteWard('invalidateWard.htm',${wardList.wardCode});" width="18" height="19" border="0" /></a></td> --%>
											
									</tr>
								</c:forEach>
								<form:input path="wardId" type="hidden" name="wardId" id="wardId"  />
								 <input type="hidden" name="localbdytyp" id="localbdytyp"/>
									
								<c:if test="${fn:length(viewPage) > 0}">
									<c:if test="${empty wardList}">
										<tr>
											<td colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
										</tr>
									</c:if>
								</c:if>

							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td align="right">
									     <table width="301">
											<tr>
												<td width="150" align="right" class="pageno"><c:out value="${wardCount}" escapeXml="true"></c:out></td>
												<c:if test="${counter > 25}">
														<c:if test="${offset > 0}">
												<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="right" class="pageno">|</td>
														</c:if>
												</c:if>
												
												
												
												<td width="51" align="right" class="nxt tblclear">
												<c:if test="${counter > 25}">
													<c:if test="${movenext==true}" >
												
												<a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
													</c:if>
												</c:if>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
												
											</tr>
										</table>
									</td>
									<td><input type="hidden" name="direction" id="direction" value="0" /></td>
					</tr>
					</table>
			</div>
		</div>
	</c:if>
	<c:if test="${wardsize==0}">
							<table width="80%"><tr><td>&nbsp;</td></tr>
							<tr align="center"> <td><c:out value="${listnull}" escapeXml="true"></c:out></td></tr>
							</table>
							</c:if>
	</form:form>
	<script src="/LGD/JavaScriptServlet"></script>
	
	 </div> 
	 </div> 
</body>
</html>