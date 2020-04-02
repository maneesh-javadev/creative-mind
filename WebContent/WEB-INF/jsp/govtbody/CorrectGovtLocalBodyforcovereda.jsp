<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%!String contextPath;%>
<% 
	contextPath = request.getContextPath(); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message>
	</title> <script src="js/shiftdistrict.js"></script>
	<script src="js/govtorder.js"></script>
	<script src="js/validation.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/common.js"></script>
	<script src="js/modify_LB.js"></script>
	<script src="js/local_body.js"></script>
	<script type="text/javascript" src="js/GovtLocalBody.js"></script>
	<script type="text/javascript" src="js/viewLocalbody.js"></script>
	<script src="js/lgd_localbody.js"></script>
	
	<script src="js/jquery.MultiFile.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/attachedFiles.js" type="text/javascript"
		language="javascript"></script>
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
	<script src="js/lgd_localbody.js"></script>
	<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>	
	
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
	
	<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
	
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
   <script type="text/javascript"  src="<%=contextPath %>/js/jquery.blockUI.js"></script>
   <script type="text/javascript"  src="<%= contextPath %>/js/jquery.tabs.pack.js"></script>
   <script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
   <script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	
   <link href="css/successMessage.css" rel="stylesheet" type="text/css" />
 
	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
		/* 
	 	 * Conditional Check whether Logged in state is ULB oprate District wise.
	 	 * Set value for javascript file reference.
	 	 */
		var isDistrictLevel = "${isDistrictLevel}";
		
		function getHideLandregionWithTypesList()
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
							document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
							document.getElementById('divLgdLBCoveredArea').style.display = 'block';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							document.getElementById('getHeadQuartersD').style.visibility = 'visible';
							document.getElementById('getHeadQuartersD').style.display = 'block';
							
							//getHeadQuarterListFinalWithoutMappedLBonLoadDP('D');
							getHeadQuarterListFinalWithoutMappedLBDPYesNo('D');
							
							document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
							document.getElementById('divLgdLandRegionwithTypes').style.display = 'block';
							document.getElementById('getLandRegionWithTypesD').style.visibility = 'visible';
							document.getElementById('getLandRegionWithTypesD').style.display = 'block';
							getCoveredandRegionwithTypesListFinalDP('D');
							
							break;
						case 'I':
							document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
							document.getElementById('divLgdLBCoveredArea').style.display = 'block';
							document.getElementById('getHeadQuartersT').style.visibility = 'visible';
							document.getElementById('getHeadQuartersT').style.display = 'block';
							
							//getHeadQuarterListFinalWithoutMappedLBonLoadIP('T');
							getHeadQuarterListFinalWithoutMappedLBIPYesNo('T');
							
							document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
							document.getElementById('divLgdLandRegionwithTypes').style.display = 'block';
							document.getElementById('getLandRegionWithTypesT').style.visibility = 'visible';
							document.getElementById('getLandRegionWithTypesT').style.display = 'block';
							getCoveredandRegionwithTypesListFinalIP('T');
							
							break;
						case 'V':
							
							document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
							document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							
							document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
							document.getElementById('divLgdLBCoveredArea').style.display = 'block';
							document.getElementById('getHeadQuartersV').style.visibility = 'visible';
							document.getElementById('getHeadQuartersV').style.display = 'block';
							
							//getHeadQuarterListFinalWithoutMappedLBonLoad('V');
							
							getHeadQuarterListFinalWithoutMappedLBVPforYesNo('V');
							
							document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
							document.getElementById('divLgdLandRegionwithTypes').style.display = 'block';
							document.getElementById('getLandRegionWithTypesV').style.visibility = 'visible';
							document.getElementById('getLandRegionWithTypesV').style.display = 'block';
							getCoveredandRegionwithTypesListFinal('V');
							break;
					}
				}
				else
				{
					document.getElementById('availdivLgdUrban').style.display = 'block';
					document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
					
					document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
					document.getElementById('divLgdLandRegionwithTypes').style.display = 'block';
					document.getElementById('getLandRegionWithTypesU').style.visibility = 'visible';
					document.getElementById('getLandRegionWithTypesU').style.display = 'block';
					getCoveredandRegionwithTypesListFinalUP('T');
					
				}
			}
		}
		
		
		function getHideLandRegionDetails()
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
								document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
								document.getElementById('divLgdLBCoveredArea').style.display = 'block';
								document.getElementById('availdivLgdUrban').style.display = 'none';
								document.getElementById('getHeadQuartersD').style.visibility = 'visible';
								document.getElementById('getHeadQuartersD').style.display = 'block';
								
								//getHeadQuarterListFinalWithoutMappedLBonLoadDP('D');
								getHeadQuarterListFinalWithoutMappedLBDPYesNo('D');
								//document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
								document.getElementById('divLgdLandRegionwithTypes').style.display = 'none';
								//document.getElementById('getLandRegionWithTypesD').style.visibility = 'visible';
								document.getElementById('getLandRegionWithTypesD').style.display = 'none';
								//getCoveredandRegionwithTypesListFinal('D');
								break;
							case 'I':
								document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
								document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
								document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
								document.getElementById('availdivLgdUrban').style.display = 'none';
								document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
								document.getElementById('divLgdLBCoveredArea').style.display = 'block';
								document.getElementById('getHeadQuartersT').style.visibility = 'visible';
								document.getElementById('getHeadQuartersT').style.display = 'block';
								
								//getHeadQuarterListFinalWithoutMappedLBonLoadIP('T');
								getHeadQuarterListFinalWithoutMappedLBIPYesNo('T');
								
								//document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
								document.getElementById('divLgdLandRegionwithTypes').style.display = 'none';
								//document.getElementById('getLandRegionWithTypesT').style.visibility = 'visible';
								document.getElementById('getLandRegionWithTypesT').style.display = 'none';
								//getCoveredandRegionwithTypesListFinal('T');
								
								break;
							case 'V':
								
								document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
								document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
								document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
								document.getElementById('availdivLgdUrban').style.display = 'none';
								
								document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
								document.getElementById('divLgdLBCoveredArea').style.display = 'block';
								document.getElementById('getHeadQuartersV').style.visibility = 'visible';
								document.getElementById('getHeadQuartersV').style.display = 'block';
								
								//getHeadQuarterListFinalWithoutMappedLBonLoad('V');
								getHeadQuarterListFinalWithoutMappedLBVPforYesNo('V');
								
								//document.getElementById('divLgdLandRegionwithTypes').style.visibility = 'visible';
								document.getElementById('divLgdLandRegionwithTypes').style.display = 'none';
								//document.getElementById('getLandRegionWithTypesV').style.visibility = 'visible';
								document.getElementById('getLandRegionWithTypesV').style.display = 'none';
								//getCoveredandRegionwithTypesListFinal('V');
								break;
						}
					}
					else
					{
						document.getElementById('availdivLgdUrban').style.display = 'block';
						document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
						document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
						document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLandRegionwithTypes').style.display = 'none';
						document.getElementById('getLandRegionWithTypesU').style.display = 'none';
					}
		   	 }
		}
		
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
		
		function ondataLoadforCorrectCov()
		{
			$("#ddLgdUrbanLBDistUmappedDest_error").hide();
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
							document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
							document.getElementById('divLgdLBCoveredArea').style.display = 'block';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							document.getElementById('getHeadQuartersD').style.visibility = 'visible';
							document.getElementById('getHeadQuartersD').style.display = 'block';
							
							getHeadQuarterListFinalWithoutMappedLBonLoadDP('D');
							break;
						case 'I':
							document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
							document.getElementById('divLgdLBCoveredArea').style.display = 'block';
							document.getElementById('getHeadQuartersT').style.visibility = 'visible';
							document.getElementById('getHeadQuartersT').style.display = 'block';
							
							getHeadQuarterListFinalWithoutMappedLBonLoadIP('T');
							
							break;
						case 'V':
							document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
							document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							
							document.getElementById('divLgdLBCoveredArea').style.visibility = 'visible';
							document.getElementById('divLgdLBCoveredArea').style.display = 'block';
							document.getElementById('getHeadQuartersV').style.visibility = 'visible';
							document.getElementById('getHeadQuartersV').style.display = 'block';
							
							getHeadQuarterListFinalWithoutMappedLBonLoad('V');
							
							break;
					}
				}	
				else{
					document.getElementById('availdivLgdUrban').style.display = 'block';
					document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
				}
				
				if (id != "0")
				{
					if(id3 !='U')
					{	
						if (id2 !="") 
						{
							switch (id2)
							{
							case 'D':
								document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'block';
								document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
								document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
								break;
							case 'I':
								document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'block';
								document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
								document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
								break;
							case 'V':
								document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'block';
								document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
								document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
								break;
							default:
								document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
								document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
								document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
							}
						}	
					} 
					else 
					{
						document.getElementById('divLgdLBUrbanUnmapped').style.display = 'block';
						document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
						document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
					}

				} else {
					alert("Please First Select Type of Local Body");
					document.getElementById('divLgdLBUrbanUnmapped').style.display = 'none';
					document.getElementById('divLgdLBVillageCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('divLgdLBDistCAreaUnmapped').style.display = 'none';
				}
			
			}
		}
		
	 	if (window.addEventListener) { 
		     window.addEventListener("load",ondataLoadforCorrectCov, true );
		}
		else 
		if (window.attachEvent) { 
		        window.attachEvent("onload", ondataLoadforCorrectCov);
		} else 
		if (window.onLoad) {
		     window.onload = ondataLoadforCorrectCov;
		}  
		
	</script>
</head>

<%-- <body onload="loadModifyNamePage();ondataLoadforCorrectCov('${selectBox}');">  --%>
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
			<h3 class="subtitle">
				<spring:message code="Label.CORRECTGOVTLOCALBODY" htmlEscape="true"></spring:message> 
			</h3>
			<ul class="listing">
				<%--//this link is not working <li>
					<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"  ></spring:message> </a>
				</li> --%>
			</ul>
		</div>

		<div class="frmpnlbrdr">
			<form:form action="CorrectLocalBodyCoveredarea.htm" method="POST"
				commandName="localGovtBodyForm" enctype="multipart/form-data" onsubmit="displayLoadingImage()">
				   <input type="hidden" name="selectBox" id="selectBox" value="<c:out value='${selectBox}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="lbtypecode" id="lbtypecode" value="<c:out value='${lbtypecode}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox1" id="subDistrictVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishList}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="selectBox2" id="villageVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishListSource}' escapeXml='true'></c:out>"></input>
				   <input type="hidden" name="localBodyVersion" id="localBodyVersion" value="<c:out value='${localGovtBodyForm.localBodyVersion}' escapeXml='true'></c:out>"></input>
				   <input type='hidden'	id="hdnStateCode" value="<c:out value='${stateCode}' escapeXml="true"></c:out>"/>
				   <form:hidden path="lgd_LBTypeNamehidden" id="lgdLBTypeNamehidden" value="${selectBox}" />
					<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="CorrectLocalBodyCoveredarea.htm"/>" />
					
				   <input type='hidden'	id="landregionlength" value="<c:out value='${landregionLength}' escapeXml='true'></c:out>"/>
				   <form:input path="landregiondetails" type="hidden" id="landregiondetails" name="landregiondetails"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"  ></spring:message>
								</label>
							</div>
							<!-- <!-- <table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="30%">&nbsp;</td>
									<td width="70%"> --> <form:hidden path="localBodyCode" id="hdnLbCode"
											value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" />
											<form:hidden path="lbType" id="hdnLbTypeCode" value="${localGovtBodyForm.lbType}" htmlEscape="true" />
								<!-- </tr> -->
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
											<label> <spring:message code="Label.CHECKEDDISTURBED" htmlEscape="true"  ></spring:message>
										</label>
												 <label
													class="lblPlain"> <img src="images/red_flg.png"	width="13" height="9" />
												</label>
											</c:if>
										</spring:bind>
									</li>

									<li>

										<!-- <td class="lblBold"> --><label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label>
										<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label></li>

									<li>
										<!-- <td class="lblBold"> --><label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label>
										 <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label>
										</li>
									<li>
										<!-- <td class="lblBold"> --><label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label>
													
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label>
										</li>
									<li>
										<!-- <td class="lblBold"> --><label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label>
										 <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label>
										</li>
									
									<!-- Added by sushil on 21-12-2012 -->
									<li>
										<!-- <td class="lblBold"> --><label><spring:message code="Label.localbodytype" htmlEscape="true" text="Local Body Type" ></spring:message></label>
										<label class="lblPlain">
											<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyTypeName">
												<c:out value="${status.value}" escapeXml="true"/>
											</spring:bind>
											</label>
										</li>
									<!-- End -->
									
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].level"  htmlEscape="true" >
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
					<div id='divLgdLBCoveredArea' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message htmlEscape="true" code="Label.CORRECTLOCALBODY"></spring:message></label>
							</div>
								
								<div id='currentCoverDiv' class="frmpnlbg">
								<div class="frmtxt">
									<div class="frmhdtitle">
										<label><spring:message code="Label.CURRENTCOVERAREA" htmlEscape="true"></spring:message>
										</label>
									</div>	
							
						 			<ul class = "blocklist">
						 				
										<li>
										<div id='availdivLgdLBDistCAreaUnmapped' class="frmpnlbg">
											<!-- <table class="tbl_no_brdr">

												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td></td> -->
											<ul class = "blocklist">
												<li>
												<div class="ms_container">
													<div class="ms_selectable">	
														<label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTDIS"></spring:message> </label><br />
																								
																  <form:select
																	path="availlgd_LBDistCAreaSourceListUmapped" class="frmtxtarea"
																	id="availddLgdLBDistCAreaSourceLUmapped"
																	items="${districtnamelist}"
																	itemLabel="land_region_name_english"
																	itemValue="combineLR"
																	multiple="true">
		
																</form:select>
															</div>
															<div class="clear"></div>
														</div>
														 <br /> <br /></li>														
														
													
														
															<li>
																<div class="ms_container">
																	<div class="ms_selectable">	
																		<label><spring:message htmlEscape="true" 
																			code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
																	 <form:select
																		path="availlgd_LBSubDistrictSourceLatDCAUmapped" class="frmtxtarea"
																		id="availddLgdLBSubDistrictSourceLatDCAUmapped"
																		items="${subdisticnamelist}"
																		itemLabel="land_region_name_english"
																		itemValue="land_region_code"
																		multiple="true">
			
																	</form:select><br /> <br />
															      </div>
															      <div class="clear"></div>
															 </div>
														 </li>
																										
														
														<li>
														<div class="ms_container">
															<div class="ms_selectable">	
																<label><spring:message htmlEscape="true" 
																		code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
																<form:select
																	path="availlgd_LBVillageSourceLatDCAUmapped" class="frmtxtarea"
																	id="availddLgdLBVillageSourceLatDCAUmapped"
																	items="${villagenamelist}"
																	itemLabel="land_region_name_english"
																	itemValue="land_region_code"
																	multiple="true">
		
																</form:select>
															</div>
															<div class="clear"></div>
														</div>
														<br /> <br /></li>
												</ul>

										</div>
									</li>

								<li>
									
										<div id='availdivLgdLBInterCAreaUnmapped' class="frmpnlbg">
											<ul class = "blocklist">
												<li>
												<div class="ms_container">
															<div class="ms_selectable">	
													<label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label><br />
														 <form:select
															path="availlgd_LBInterCAreaSourceListUmapped" class="frmtxtarea"
															id="availddLgdLBInterCAreaSourceLUmapped"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															multiple="true">

														</form:select></div><div class="clear"></div></div></li>
														
														<!-- <td width="100" align="center"><input type="button" id="btnremoveOneULB"
														name="Submit4" value="Remove" style="width: 70px"
														onclick="removeItemforcoveredsubdistic('availddLgdLBInterCAreaSourceLUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped',true)" /><br />
														</td> -->
														<li>
														<div class="ms_container">
															<div class="ms_selectable">	
															<label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="availlgd_LBVillageSourceLatICAUmapped" class="frmtxtarea"
															id="availddLgdLBVillageSourceLatICAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															multiple="true">

														</form:select><br /></div><div class="clear"></div></div></li>
											


											</ul>

										</div>
									</li>
								
								<li>
										<div id='availdivLgdUrban' class="frmpnlbg">
											<ul class = "blocklist">
												<li>
												   <div class="ms_container">
													 <div class="ms_selectable">
														<label>
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILABLEFORCURRENTDIS" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message htmlEscape="true" code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </c:otherwise>
															</c:choose>
														</label>
														<br />
														<form:select
															path="availlgd_LBInterCAreaSourceListUmappedUrban" class="frmtxtarea"
															id="availddLgdLBInterCAreaSourceLUmappedUrban"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															multiple="true">
														</form:select>
														</div><div class="clear"></div></div>
													</li>
												
											</ul>
										</div>
									</li>
								
								<li>
										<div id='availdivLgdLBVillageCAreaUnmapped' class="frmpnlbg">
											<ul class = "blocklist">
												<li>
												<div class="ms_container">
												 <div class="ms_selectable">
													 <label><spring:message htmlEscape="true" 
																code="Label.AVAILABLEFORCURRENTVILL"></spring:message> </label><br />
														<form:select
															path="availlgd_LBVillageCAreaSourceLUnmapped" class="frmtxtarea"
															id="availddLgdLBVillageCAreaSourceLUnmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															multiple="true">

														</form:select></div><div class="clear"></div></div></li>
														<li>
															<div id='divhiddenavaillgd_LBMappedLocalbody' class="frmpnlbg" style="visibility: hidden; style="display: none">
														
																<form:select path="hiddenavaillgd_LBMappedLocalbody" class="frmtxtarea"
																	id="hiddenavaillgd_LBMappedLocalbody"
																	multiple="true" hidden="hidden">

																</form:select>
															</div>	
														</li>

											</ul>

										</div> <c:if test="${empty isDistrictLevel}"></c:if>
									</li>
									<!-- 	<table class="tbl_no_brdr" width="100%"> -->
								
								<c:if test="${fn:containsIgnoreCase(levelcheck,'D')}">
									<c:if test="${! empty districtnamelist}"> 
									<!-- <tr>
										<td> -->
										<li>
										<br /> <label>Do you want to change the current coverage type?&nbsp;&nbsp;&nbsp; 
											<form:radiobutton path="lgd_ChCovType" value="Yes" 
												onclick="getHideLandregionWithTypesList();"/> </label> 
												<spring:message htmlEscape="true" code="Label.PESAACTYES"></spring:message>&nbsp;&nbsp;
											<form:radiobutton path="lgd_ChCovType" value="No" onclick="getHideLandRegionDetails();" checked="true" /> <spring:message htmlEscape="true" 
												code="Label.PESAACTNO" /> <br />
										</li>
									</c:if>	
								</c:if>	
								
								<c:if test="${fn:containsIgnoreCase(levelcheck,'I')}">
									<c:if test="${! empty subdisticnamelist}"> 
									<li>
										<br /> <label><spring:message htmlEscape="true" code="Label.dochangecurrenttype"></spring:message>&nbsp;&nbsp;&nbsp; 
											<form:radiobutton path="lgd_ChCovType" value="Yes" 
												onclick="getHideLandregionWithTypesList();"/> </label> 
												<spring:message htmlEscape="true" code="Label.PESAACTYES"></spring:message>&nbsp;&nbsp;
											<form:radiobutton path="lgd_ChCovType" value="No" onclick="getHideLandRegionDetails();" checked="true" /> <spring:message htmlEscape="true" 
												code="Label.PESAACTNO" /> <br />
										</li>
									</c:if>	
								</c:if>	
								
								<c:if test="${fn:containsIgnoreCase(levelcheck,'V')}">
									<c:if test="${! empty villagenamelist}"> 
									<li>
										<br /> <label><spring:message htmlEscape="true" code="Label.dochangecurrenttype"></spring:message>&nbsp;&nbsp;&nbsp; 
											<form:radiobutton path="lgd_ChCovType" value="Yes" 
												onclick="getHideLandregionWithTypesList();"/> </label> 
												<spring:message htmlEscape="true" code="Label.PESAACTYES"></spring:message>&nbsp;&nbsp;
											<form:radiobutton path="lgd_ChCovType" value="No" onclick="getHideLandRegionDetails();" checked="true" /> <spring:message htmlEscape="true" 
												code="Label.PESAACTNO" /> <br />
										</li>
									</c:if>	
								</c:if>	
								
								<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'U')}">
									<c:if test="${empty isDistrictLevel}">
										<c:if test="${! empty subdisticnamelist}"> 
											<li>
												<br /> <label><spring:message htmlEscape="true" code="Label.dochangecurrenttype"></spring:message>&nbsp;&nbsp;&nbsp; 
													<form:radiobutton path="lgd_ChCovType" value="Yes" 
														onclick="getHideLandregionWithTypesList();"/> </label> 
														<spring:message htmlEscape="true" code="Label.PESAACTYES"></spring:message>&nbsp;&nbsp;
													<form:radiobutton path="lgd_ChCovType" value="No" onclick="getHideLandRegionDetails();" checked="true" /> <spring:message htmlEscape="true" 
														code="Label.PESAACTNO" /> <br />
												</li>
										</c:if>	
									</c:if>
								</c:if>	
								
							<!-- 	</table> -->
								
								<!-- <table class="tbl_no_brdr" width="100%"> -->
								<li>
										<div id='divLgdLandRegionwithTypes' class="frmpnlbg" style="visibility: hidden; style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
											<%-- <spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message> --%>
											Select Land Region Type
											</div>
											<ul class = "blocklist">
												<li>
									 					<div id="getLandRegionWithTypesD"></div>
									 					<div id="getLandRegionWithTypesT"></div>
						    							<div id="getLandRegionWithTypesV"></div>
						    							<div id="getLandRegionWithTypesU"></div>
						    						</li>	
											</ul>
										</div>
										</div>
									</li>		
								<!-- </table>  -->
								
							 	<li>
										<div id='divLgdLBDistCAreaUnmapped' class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message>
											</div>
											<ul class = "blocklist">
												
												<li>
														<div id="ddLgdLBDistCAreaDestLUmapped_error" class="error"><spring:message code="msg.mapLB" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error1"><form:errors path="lgd_LBDistCAreaDestListUmapped" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error2" style="display: none"></div>
												</li>
												<li>
													<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" 
																	code="Label.AVAILDISTRICTLIST"></spring:message> </label><br />
															<form:select path="lgd_LBDistCAreaSourceListUmapped"
																class="frmtxtarea" id="ddLgdLBDistCAreaSourceLUmapped"
																multiple="true">
																<c:forEach items="${UnmappedData}" var="UnmappedDatalist">
																  <c:if test="${UnmappedDatalist.operation_state == 'F'.charAt(0)}">
																    <form:option value="${UnmappedDatalist.landRegionCode}" disabled="true"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																  </c:if>  
																  <c:if test="${UnmappedDatalist.operation_state == 'A'.charAt(0)}">
																     <form:option value="${UnmappedDatalist.landRegionCode}"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																  </c:if>
																</c:forEach>
															</form:select><br /> <br />
													</div>

													<div class="ms_buttons">
													<label><input type="button" class = "bttn"
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforDistMapCov('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','availddLgdLBDistCAreaSourceLUmapped','FULL',true,'D');" />
														</label>
														<label> <input type="button" class = "bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" 
														onclick="removeItemCorrectCovforDP('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','availddLgdLBDistCAreaSourceLUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
														onclick="removeItemCorrectCovforDPReset('ddLgdLBDistCAreaDestLUmapped', 'ddLgdLBDistCAreaSourceLUmapped','availddLgdLBDistCAreaSourceLUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value="Part &gt;&gt;"
														onclick="addItemforLBforDistMapCov('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','availddLgdLBDistCAreaSourceLUmapped','PART',true,'D');" /></label>
													</div>

													<div class="ms_selection"><label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTDISTRICTLIST"></spring:message> </label><span
														class="errormsg">*</span><br /> 
														<form:select name="select6"
															id="ddLgdLBDistCAreaDestLUmapped" size="1"
															multiple="true" path="lgd_LBDistCAreaDestListUmapped"
															class="frmtxtarea">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <input type="button" class = "bttn"
														value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>"
														onclick="getUnmappedLBSDPListatUmappedFinal('T','${localGovtBodyForm.lbType}','${levelcheck}');" /></div>
													</div>
												</li>
												<li><div class="clear"></div></li>
												<li>
													<div class="ms_container">
													<div class="ms_selectable">
														<label><spring:message htmlEscape="true" 
																	code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
															<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped"
																class="frmtxtarea"
																id="ddLgdLBSubDistrictSourceLatDCAUmapped"
																multiple="true">
	
															</form:select><br /> <br />
													</div>

													<div class="ms_buttons">
													<label><input type="button" class = "bttn"
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','FULL',true,'T');" />
														 </label>
														<label><input type="button" class = "bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" 
														onclick="removeItemSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
														onclick="removeAllSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped', 'ddLgdLBSubDistrictSourceLatDCAUmapped', true)" /></label>
														<label><input type="button" class = "bttn" value="Part &gt;&gt;"
														onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped', 'PART',true,'T');" /></label>
													</div>

													<div class="ms_selection">
													<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBSubDistrictDestLatDCAUmapped" size="1"
															multiple="true" path="lgd_LBSubDistrictDestLatDCAUmapped"
															class="frmtxtarea">
														</form:select><br /> &nbsp;&nbsp;&nbsp; <label><input type="button" class = "bttn"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														onclick="refreshOptions('ddLgdLBVillageSourceLatDCAUmapped');getUnmappedLBVillPListatUmappedFinal('V','${localGovtBodyForm.lbType}','${levelcheck}');" /></label>
													</div>
												</div>
												</li>
												<li><div class="clear"></div></li>
												<li>
													<span class="errormsg"
														id="ddLgdLBVillageDestLatDCAUmapped_error"></span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatDCAUmapped" class="errormsg"></form:errors>
													</span></li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
																<label><spring:message htmlEscape="true" 
																			code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
																	<form:select path="lgd_LBVillageSourceLatDCAUmapped"
																		class="frmtxtarea" id="ddLgdLBVillageSourceLatDCAUmapped"
																		multiple="true">
			
																	</form:select><br /> <br /></div>

													<div class="ms_buttons">
													<label><input type="button" class = "bttn"
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped','FULL',true,'V');" /></label>
														 
														<label><input type="button" class = "bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;"
														onclick="removeItemVillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
														onclick="removeAll('ddLgdLBVillageDestLatDCAUmapped', 'ddLgdLBVillageSourceLatDCAUmapped', true)" /></label>
														<label><input type="button" class = "bttn" value="Part &gt;&gt;"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped', 'PART',true,'V');" /></label>
													</div>

													<div class="ms_selection">
													<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageDestLatDCAUmapped" size="1"
															multiple="multiple" path="lgd_LBVillageDestLatDCAUmapped"
															class="frmtxtarea">
														</form:select><br />
														</div>
												</div>
												</li>
												<li><div class="clear"></div></li>
											</ul>
											</div>
										</div>
									</li>

								<li>
										<div id='divLgdLBInterCAreaUnmapped' class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message>
											</div>
											<ul class = "blocklist">
												<li>
													<div id="ddLgdLBInterCAreaDestLUmapped_error" class="error"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBInterCAreaDestLUmapped_msg" style="display:none"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error1"><form:errors path="lgd_LBInterCAreaDestListUmapped" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error2" style="display: none"></div> 
													
													</li>


												<li>
													<div class="ms_container">
													<div class="ms_selectable">
													<label><spring:message htmlEscape="true" 
																code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label><br />
														<form:select path="lgd_LBInterCAreaSourceListUmapped"
															class="frmtxtarea" id="ddLgdLBInterCAreaSourceLUmapped"
															multiple="true">
																<c:forEach items="${UnmappedData}" var="UnmappedDatalist">
																	   <c:if test="${UnmappedDatalist.operation_state == 'F'.charAt(0)}">
																	        <form:option value="${UnmappedDatalist.landRegionCode}" disabled="true"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																	  </c:if>  
																	   <c:if test="${UnmappedDatalist.operation_state == 'A'.charAt(0)}">
																	        <form:option value="${UnmappedDatalist.landRegionCode}"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
																	  </c:if>
																</c:forEach>
															</form:select><br /> <br /></div>

													<div class="ms_buttons">
													<label><input type="button" class = "bttn"
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforsubIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','availddLgdLBInterCAreaSourceLUmapped','FULL',true,'T');" /></label>
														 
														<label><input type="button" class = "bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" 
														onclick="removeItemCorrectCovforIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','availddLgdLBInterCAreaSourceLUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
														onclick="removeItemCorrectCovforIPReset('ddLgdLBInterCAreaDestLUmapped', 'ddLgdLBInterCAreaSourceLUmapped','availddLgdLBInterCAreaSourceLUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value="Part &gt;&gt;"
														onclick="addItemforLBforsubIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','availddLgdLBInterCAreaSourceLUmapped','PART',true,'T');" /></label>
													</div>

													<div class="ms_selection">
													<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message>
													</label> <br /> <form:select name="select6"
															id="ddLgdLBInterCAreaDestLUmapped" size="1"
															multiple="true" path="lgd_LBInterCAreaDestListUmapped"
															class="frmtxtarea">
														</form:select><br /> &nbsp;&nbsp;&nbsp;<label><input type="button"
														value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														onclick="refreshOptions('ddLgdLBVillageSourceLatICAUmapped');selectSubDistrictAtICAUmapped('V','${localGovtBodyForm.lbType}','${levelcheck}');" /></label>
													</div>
												</div>
											</li>
											<li><div class="clear"></div></li>
												<%-- <span class="errormsg"
														id="ddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span> --%>
												
												
											<li>
												<div class="ms_container">
												<div class="ms_selectable">
													<label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageSourceLatICAUmapped"
															class="frmtxtarea" id="ddLgdLBVillageSourceLatICAUmapped"
															multiple="true">

														</form:select><br /> <br /></div>

													<div class="ms_buttons">
													<label><input type="button" class = "bttn"
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped','FULL',true,'V');" />
														 </label>
														<label><input type="button" class = "bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" 
														onclick="removeItemVillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped',true)" /></label>
														<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
														onclick="removeAll('ddLgdLBVillageDestLatICAUmapped', 'ddLgdLBVillageSourceLatICAUmapped', true)" /></label>
														<label><input type="button" class = "bttn" value="Part &gt;&gt;"
														onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped', 'PART',true,'V');" /></label>
													</div>

													<div class="ms_selection">
													<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageDestLatICAUmapped" size="1"
															multiple="true" path="lgd_LBVillageDestLatICAUmapped"
															class="frmtxtarea">
														</form:select></div>
												</div>
												</li>
												<li><div class="clear"></div></li>
												
											</ul>
											</div>
										</div>
									</li>
								
								<li>
										<div id='divLgdLBVillageCAreaUnmapped' class="frmpnlbg">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message>
											</div>
											<ul class = "blocklist">
												<li>
													
														<div id="ddLgdLBVillageCAreaDestLUnmapped_error" class="error"><spring:message code="msg.mapLB" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error1"><form:errors path="lgd_LBVillageCAreaDestLUnmapped" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error2" style="display: none"></div>  
													</li>
													
												<li>
													<div class="ms_container">
													<div class="ms_selectable">
													<label><spring:message htmlEscape="true" 
																code="Label.AVAILVILLAGELIST"></spring:message> </label><br />
														<form:select path="lgd_LBVillageCAreaSourceLUnmapped"
															class="frmtxtarea"
															id="ddLgdLBVillageCAreaSourceLUnmapped"
															multiple="true">
															<c:forEach items="${UnmappedData}" var="UnmappedDatalist">
															  <c:if test="${UnmappedDatalist.operation_state == 'F'.charAt(0)}">
																    <form:option value="${UnmappedDatalist.landRegionCode}" disabled="true"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  </c:if>  
															  <c:if test="${UnmappedDatalist.operation_state == 'A'.charAt(0)}">
															    	<form:option value="${UnmappedDatalist.landRegionCode}"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  </c:if>
															</c:forEach>

														</form:select><br /> <br />
														</div>
														
													<div class="ms_buttons">
													<label><input type="button" class = "bttn" 
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforvillageMapped('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','availddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" /></label>
														 
														<label><input type="button" class = "bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" 
														onclick="removeItemCorrectLBforVP('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','availddLgdLBVillageCAreaSourceLUnmapped',true)" /></label>
														<label><input type="button" class = "bttn" value=" Reset &lt;&lt;"
														onclick="removeItemCorrectLBforVPReset('ddLgdLBVillageCAreaDestLUnmapped', 'ddLgdLBVillageCAreaSourceLUnmapped','availddLgdLBVillageCAreaSourceLUnmapped',true)" /></label>
														<label><input type="button" class = "bttn" value="Part &gt;&gt;"
														onclick="addItemforLBforvillageMapped('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','availddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" /></label>
													</div>

													<div class="ms_selection">
													<label><spring:message htmlEscape="true" 
																code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> <br />
														<form:select name="select6"
															id="ddLgdLBVillageCAreaDestLUnmapped" size="1"
															multiple="true" path="lgd_LBVillageCAreaDestLUnmapped"
															class="frmtxtarea">
														</form:select><br />
														</div>
												</div>
												</li>
											<li><div class="clear"></div></li>
											</ul>
											</div>
										</div>
									</li>
								
									<li>
										<div id='divLgdLBUrbanUnmapped' class="frmpnlbg" style="display: none">
										<div class="frmtxt">
											<div class="frmhdtitle">
												<spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLYLANDREGIONS"></spring:message>
											</div>
											<ul class = "blocklist">
												<li>
													
														<div id="ddLgdUrbanLBDistUmappedDest_error" class="error"><spring:message code="msg.mapLB" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="ddLgdUrbanLBDistUmappedDest_error1"><form:errors path="lgd_UrbanLBDistUmappedDest" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddLgdUrbanLBDistUmappedDest_error2" style="display: none"></div>  
													</li>
												<li>
													<div class="ms_container">
														<div class="ms_selectable">
															<label>
																<c:choose>
																	<c:when test="${isDistrictLevel}"><spring:message code="Label.AVAILDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
																	<c:otherwise><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </c:otherwise>
																</c:choose>
															</label>		
															<br />
														<form:select path="lgd_UrbanLBDistUmappedSource"
															class="frmtxtarea" id="ddLgdUrbanLBDistUmappedSource"
															multiple="true">
															<%-- <form:options items="${UnmappedData}"
																	itemLabel="localBodyNameEnglish"
																	itemValue="landRegionCode" /> --%>
																	
															<c:forEach items="${UnmappedData}" var="UnmappedDatalist">
															  <c:if test="${UnmappedDatalist.operation_state == 'F'.charAt(0)}">
																    <form:option value="${UnmappedDatalist.landRegionCode}" disabled="true"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  </c:if>  
															  <c:if test="${UnmappedDatalist.operation_state == 'A'.charAt(0)}">
															    	<form:option value="${UnmappedDatalist.landRegionCode}"><c:out value="${UnmappedDatalist.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
															  </c:if>
															</c:forEach>
																	
														</form:select><br/><br/>
													</div>
														
													<div class="ms_buttons">
												<input type="button" class="bttn"
														value="<spring:message
																code="Button.WHOLE"/>" 
														onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource','FULL',true,'T');" />

														
														<input type="button"   class="bttn" id="btnremoveOneULB"
														name="Submit4" value="Back &lt;" 
														onclick="removeItemSubdistrictUnmapped('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource',true)" />
														<input type="button" value=" Reset &lt;&lt;"
														 class="bttn"
														onclick="removeAll11('ddLgdUrbanLBDistUmappedDest', 'ddLgdUrbanLBDistUmappedSource', true)" />
														<input type="button" value="Part &gt;&gt;"
													 class="bttn"
														onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource', 'PART',true,'T');" />

													</div>

													<div class="ms_selection">
														<label>
															<c:choose>
																<c:when test="${isDistrictLevel}"><spring:message code="Label.CONTRIBUTDISTRICTLIST" htmlEscape="true"></spring:message></c:when>
																<c:otherwise><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message> </c:otherwise>
															</c:choose>
														</label>
														<br/> 
														<form:select name="select6"
															id="ddLgdUrbanLBDistUmappedDest" size="1"
															multiple="true" path="lgd_UrbanLBDistUmappedDest"
															class="frmtxtarea">
														</form:select><br /> &nbsp;&nbsp;&nbsp; 
														</div>

												</div>
												</li>
												<li><div class="clear"></div></li>

											</ul>
											</div>
										</div>
									</li>
								
								

							</ul>

						</div> 
						
						<div id='divLgdLBCoveredArea' class="frmpnlbg" style="visibility: hidden; style="display: none">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message>
								</div>
							
								<ul class = "blocklist">
									<li>
						    				<div id="getHeadQuartersD"></div>
						    				<div id="getHeadQuartersT"></div>
						    				<div id="getHeadQuartersV"></div>
						   				</li>
					   				</ul>
							</div>
						</div>
						
						<div class="btnpnl">
							
									<div class="btnpnl">
											<form:hidden path="lbType"
												value="${localGovtBodyForm.lbType}"/>
											<label> <input type="submit" name="Submit"
												value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>"
												onclick="return validateCorrectCoverageLB();" />
											</label>
											<!--  -->
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'P')}">
												<label> <input type="button" class="btn"
													name="Submit6"
													value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='correctGovtLocalBodycoverageareaClear.htm?<csrf:token uri="correctGovtLocalBodycoverageareaClear.htm"/>'" /> 
												</label>
											</c:if>
											<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'T')}">
												<label> <input type="button" class="btn"
													name="Submit6"
													value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='correctGovtLocalBodycoverageareaClear.htm?<csrf:token uri="correctGovtLocalBodycoverageareaClear.htm"/>'" />
												</label>
											</c:if>
											<label> <input type="button" class="btn"
												name="Submit6"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
										</div>
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