<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<title>E-Panchayat</title>

	<script src="js/convertRLBtoTLB.js"></script>
	<script src="js/govtorder.js"></script>

	<script src="js/Parliament.js"></script>
	<script src="js/common.js"></script>
	<script src="js/lgd_localbody.js"></script>

	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
	</script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService .js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<!-- <script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	<script src="datepicker/calender.js"></script> 
	<link rel="stylesheet" href="datepicker/demos.css" /> -->


	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
		
	</script>
	
	<script type="text/javascript" language="javascript">
function doRefresh(removeAll)
{
	if (removeAll)
		{
			document.getElementById('tierSetupCode').selectedIndex=0;
			removeSelectedValue('ddSourceLocalBody');
		}

}
</script>
<script type="text/javascript" language="javascript">
function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}
</script>
</head>

<body onload="loadParliamentMaptoLocalBody()" oncontextmenu="return false" 
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);" >
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.MOPTLG"></spring:message>  
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" />
					</a>
					</td>
					<%-- //this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
								code="Label.HELP"></spring:message> --%>
					</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="MapofParliament.htm" method="POST" id="form1" onsubmit="cursorwait();" name="form1" commandName="localGovtBodyForm">
				<input type='hidden' name="hdnType" id="hdnType" value="<c:out value='${typeCode}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="hdnIntermediatePanchayat" id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' name="hdnDistrictPanchayat" id="hdnDistrictPanchayat" value="<c:out value='${hdnDistrictPanchayat}' escapeXml='true'></c:out>"/>
				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
				<div id="cat">
				
				<div class="clear"></div>
				<div class="frmpnlbg">
				<div id='district'>
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true"  code="Label.MOPTLG"></spring:message>
						</div>
				<table width="100%" class="tbl_no_brdr">
  				<tr>
    			<td width="14%" rowspan="2">&nbsp;</td>
   			 <td width="86%" colspan="3"><spring:message htmlEscape="true" code="Label.SELECTPARLIAMENTCONSTITUENCY"></spring:message><br /> 
       		 <form:select path="contributedParliament" class="combofield" style="width: 150px" id="ddStateParliamnetSource" onchange="getAssemblyListForAssembly(this.value);">
          <form:option value="">
            <spring:message htmlEscape="true" code="Label.SELECTPC"></spring:message>
          </form:option>
          <form:options items="${stateSourceParliament}" itemValue="id.pcCode" itemLabel="pcNameEnglish"></form:options>
      </form:select></td>
  </tr>
   <tr>
    <td colspan="3"><table width="730">
          
     
    </table></td>
  </tr>
</table>	

<%-- <table width="100%" class="tbl_no_brdr">
  <tr>
    <td width="14%" rowspan="2">&nbsp;</td>
    <td width="86%" colspan="3">Select Assembly Constituency<br />
        <form:select
										path="contributedParliament" class="combofield" style="width: 150px"
										id="ddStateParliamnetSource" onchange="getList(this.value);">
          <form:option value="">
            <spring:message htmlEscape="true"  code="Label.SELECTSTATE"></spring:message>
          </form:option>
         <form:options items="${stateSourceParliament}"
         itemValue="id.acCode" itemLabel="acNameEnglish"></form:options>
      </form:select></td>
  </tr>
   <tr>
    <td colspan="3"><table width="730">
          
      </tr>
    </table></td>
  </tr>
</table> --%>
				</div>
				</div></div>
	
											
						<!-- End here Localbody  -->

					</div>
				
					
				
							<div id="cat">
					<div class="frmpnlbg" id="divFirstPanel">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.CONTRIBUTELOCAL"></spring:message>
							</div>
							<input type="hidden" class="btn" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"
								id="hdnState" name="hdnState" />
							<table width="100%" class="tbl_no_brdr">
								<tr>

									<td width="14%"></td>
									<td width="86%"><spring:message htmlEscape="true" 
											code="Label.SELECTLOCALBODYTYPE"></spring:message><span
										class="errormsg">*</span><br /> <form:select
											path="rurallbTypeName" id="ddLgdLBType" class="combofield"
											style="width: 175px" htmlEscape="true"
											onchange="getHideShowRuralLBList(this.value)">
											<!--tierSetupCode id Changed  -->
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option>

											<c:forEach var="localBodyTypelist" varStatus="var"
												items="${localbodyTypelist}">
												<form:option id="typeCode"
													value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
											</c:forEach>
										</form:select>&nbsp;&nbsp; <span> <form:errors htmlEscape="true"  path="rurallbTypeName"
												class="errormsg"></form:errors> </span> &nbsp;&nbsp; <span
										class="errormsg" id="ddLgdLBType_error"><spring:message htmlEscape="true" 
												code="error.blank.ruralLBTypeName"></spring:message> </span></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td width="14%"></td>
									<td width="86%">
										<div id="divRuralDistrictPanchayat">
											<table class="tbl_no_brdr">

												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="ddLgdLBDistPDestList_error"><spring:message htmlEscape="true" 
																code="error.blank.DISTRICTP"></spring:message></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBDistPDestList" class="errormsg"></form:errors>
													</span></td>
												</tr>
												<tr>
													<td width="235"><b><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b><br /> <form:select
															path="lgd_LBDistPSourceList" class="frmtxtarea"
															id="ddLgdLBDistPSourceList" htmlEscape="true"
															items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode"
															style="height: 110px; width: 233px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message code="Label.LIST"/>" style="width: 80px"
														onclick="addItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"
														onclick="removeItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
													</td>

													<td><b><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b> <br /><form:select
															id="ddLgdLBDistPDestList" size="1" multiple="true"
															path="lgd_LBDistPDestList" class="frmtxtarea"
															style="height: 110px; width: 242px">
														</form:select><br /> &nbsp;&nbsp;&nbsp;
													</td>
												</tr>

											</table>
										</div></td>
								</tr>
								<tr>
									<td width="14%"></td>
									<td width="86%">
										<div id="divRuralIntermediatePanchayat">
											<table class="tbl_no_brdr">
												<tr>
													<td><b> <spring:message htmlEscape="true"  code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b><br /> <form:select
															path="lgd_LBDistListAtInterCA" style="width:175px"
															onchange="getInterPanchayatbyDcodeAtICA(this.value);" htmlEscape="true"
															class="combofield" id="ddLgdLBDistListAtInterCA">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select>&nbsp;&nbsp;<form:errors htmlEscape="true"  path="lgd_LBDistListAtInterCA"
															class="errormsg"></form:errors>  &nbsp;&nbsp; <span
														class="errormsg" id="ddLgdLBDistListAtInterCA_error"><spring:message htmlEscape="true" 
																code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="ddLgdLBInterPDestList_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBInterPDestList" class="errormsg"></form:errors>
													</span></td>
												</tr>
												<tr>
													<td width="235"><b><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b><br /><form:select
															path="lgd_LBInterPSourceList" class="frmtxtarea" htmlEscape="true"
															id="ddLgdLBInterPSourceList"
															style="height: 110px; width: 233px" multiple="true">

														</form:select><br /> <br /></td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 80px"
														onclick="addItem('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"
														onclick="removeItem('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBInterPSourceList', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItem('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" />
													</td>

													<td><b><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b> <br /> <form:select
															name="select6" id="ddLgdLBInterPDestList" size="1"
															multiple="true" path="lgd_LBInterPDestList"
															class="frmtxtarea" style="height: 110px; width: 242px">
														</form:select><br />&nbsp;&nbsp;&nbsp;
													</td>
												</tr>

											</table>
										</div></td>
								</tr>
								<tr>
									<td width="14%"></td>
									<td width="86%">
										<div id="divRuralVillagePanchayat">
											<table class="tbl_no_brdr">
												<tr>
													<td><b> <spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b><br /> <form:select
															path="lgd_LBDistListAtVillageCA" class="combofield" htmlEscape="true"
															style="width: 180px" id="ddLgdLBDistListAtVillageCA"
															onchange="getInterPanchayatbyDcodeAtVCA(this.value);">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select>&nbsp;&nbsp;<form:errors htmlEscape="true"  path="lgd_LBDistListAtVillageCA"
															class="errormsg"></form:errors>&nbsp;&nbsp; <span
														class="errormsg" id="ddLgdLBDistListAtVillageCA_error"><spring:message htmlEscape="true" 
																code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td><b> <spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </b><br /> <form:select
															path="lgd_LBInterListAtVillageCA" class="combofield" htmlEscape="true"
															id="ddLgdLBInterListAtVillageCA" style="width: 180px"
															onchange="getVillagePanchbyIntercodeAtVCA(this.value);">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>

														</form:select>
														&nbsp;&nbsp;<form:errors htmlEscape="true" path="lgd_LBInterListAtVillageCA"
															class="errormsg"></form:errors> &nbsp;&nbsp; <span
														class="errormsg" id="ddLgdLBInterListAtVillageCA_error"><spring:message htmlEscape="true" 
																code="error.blank.INTERMEDIATEP"></spring:message></span>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td><span class="errormsg"
														id="ddLgdLBVillageDestAtVillageCA_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestAtVillageCA" class="errormsg"></form:errors>
													</span>
													</td>
												</tr>
												<tr>
													<td width="235"><b><spring:message htmlEscape="true" 
																code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
													</b><br /> <form:select path="lgd_LBVillageSourceAtVillageCA" htmlEscape="true"
															class="frmsfield" id="ddLgdLBVillageSourceAtVillageCA"
															style="height: 110px; width: 233px" multiple="true">

														</form:select><br /> <br />
													</td>

													<td width="100" align="center"><input type="button"
														value="<spring:message
																code="Button.WHOLE"/>" style="width: 80px"
														onclick="addItem('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" />
														<br /> <input type="button" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"
														onclick="removeItem('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" /><br />
														<input type="button" value=" Reset &lt;&lt;"
														style="width: 70px"
														onclick="removeAll('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" /><br />
														<input type="button" value="Part &gt;&gt;"
														style="width: 70px"
														onclick="addItem('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA', 'PART',true);" />
													</td>

													<td><b><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
													</b> <br /> <form:select name="select6"
															id="ddLgdLBVillageDestAtVillageCA" size="1"
															multiple="true" path="lgd_LBVillageDestAtVillageCA"
															class="frmtxtarea" style="height: 110px; width: 242px">
														</form:select><br /> &nbsp;&nbsp;&nbsp; 
													</td>
												</tr>



											</table>
										</div></td>
								</tr>
							</table>
						</div>
						<div class="btnpnl">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td align="center"><div class="btnpnl">

											<label> <input type="button" name="Submit"
												id="btnNext" onclick="return validateFirstAll();"
												value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>" />
											</label> <input type="button" class="btn" name="Submit6"
												value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
										</div></td>
										
								</tr>
				
							</table>

						</div>
				
					</div>
					
			</div>		
</form:form>				
	</div>


	</div>
	
</body>

</html>

 