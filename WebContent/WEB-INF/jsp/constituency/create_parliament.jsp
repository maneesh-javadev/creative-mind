<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script type="text/javascript" language="javascript">

function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 -->
<script type="text/javascript" src="js/createDistrict.js"></script>


<script src="js/Parliament.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<script src="js/validation.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/call/plaincall/lgdDwrParlimentECIService.existEntityName.dwr'></script>	
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);

	/* function validateform()

	 {
	 var districtName=document.getElementById('districtNameInEn').value;
	
	 if(districtName=="")
	 {
	 alert("Please Enter Name Of the Districit");
	 document.getElementById('districtNameInEn').focus();
	 return false;
	 }
	 return true;
	 } */

	 function hideAll() {

			$("#ddStateParliamnetSource_error").hide();
			$("#districtNameInEn_error").hide();
			$("#districtNameInEns_error").hide();
			$("#districtNameInLoc_error").hide();
			$("#AssemblyList_error").hide();
			$("#AssemblyListData_error").hide();
			$("#districtNameInEnExist_error").hide();
			$("#ECICODEData_error").hide();
			$("#ECICODEExist_error").hide();
			$("#AssemblyListBlank_error").hide();
			$("#AssemblyListData_errorcov").hide();
			
			/* 	$("#ddassemblySource_error").hide();
			 */
		}

	function selectDistrict(id, name) {

		if (id.match('PART') == 'PART') {

			var selObj = document.getElementById('ddDestDistrict');

			//var selObj2=document.getElementById('surveyListNew');
			//var selObj=document.getElementById('subDistrictListNew');	
			/*var subDistrict="";
				for (i = 0; i < selObj.options.length; i++) {
				     selObj.options[i].selected=true;
				     subDistrict +=selObj.options[i].value+",";
			 }*/
			lgdDwrStateService.getDistricts(id, {
				callback : handleDistrictSuccess,
				errorHandler : handleDistrictError
			});

		} else {
			dwr.util.removeAllOptions('villageList');
			alert("Kindly Select the Part State From the List");
		}
	}

	function handleDistrictSuccess(data) {
		// Assigns data to result id

		var fieldId = 'ddDistrict';
		var valueText = 'districtCode';
		var nameText = 'districtNameEnglish';

		dwr.util.removeAllOptions(fieldId);

		dwr.util.addOptions(fieldId, data, valueText, nameText);

	}

	function handleDistrictError() {
		// Show a popup message
		alert("No data found!");
	}


	function formSubmitAdd() {
		document.getElementById('addAnotherSD').value = "true";
		selectFinal();
	}

	
	function clearList(fieldId)
	{
		dwr.util.removeAllOptions(fieldId);
	}
	
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideAll;
	  }
	
</script>
</head>

<body>
	<div class="overlay" id="overlay1" style="display: none;"></div>
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
						<td><div id="failurMsg" class="errorfont">
								<c:out value="${family_error}" escapeXml="true"></c:out>
							</div></td>
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
					<td><div class="errorfont">
							<spring:message htmlEscape="true" code="error.blank.commonAlert"></spring:message>
						</div></td>
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
		<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.CNPL"></spring:message></h3>
			<ul class="listing">
				<%--//this link is not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				</li> --%>
				<li>
					<a href="#" class="frmhelp">Help</a>
				</li>
			</ul>
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="addParliament.htm" method="POST"	commandName="parliamentConstituency" id="form1"	enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="addParliament.htm"/>" />
			
				<form:hidden path="statecode" htmlEscape="true"/>
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.CNPL"></spring:message>
						</div>
						<div >
							<ul class="blocklist">
								<li>
									<label for=""><spring:message htmlEscape="true" code="Label.NAMEPARLIAMENTCONSTEN"></spring:message> </label> <span id="required" class="errormsg"></span><span class="errormsg">*</span><br /></label>
									<form:input path="newParliamentNameEnglish"
											id="districtNameInEn"
											onkeypress="hideAll();"
											onfocus="validateOnFocus('districtNameInEn');helpMessage(this,'districtNameInEn_msg');"
											htmlEscape="true" cssClass="frmfield" style="width: 180px"
											onblur="hideAll();validateEntityNameExistforParliamnet('${stateCode}',this.value,'P');vlidateOnblur('districtNameInEn','1','25','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('districtNameInEn');" maxlength="50"/>
										
										 <div class="errormsg" id="districtNameInEn_error">
											<spring:message htmlEscape="true" code="Error.PARLIAMENTENGISHNAME"></spring:message>
									       </div>
									       <div class="errormsg" id="districtNameInEns_error">
											<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCONENGLISHData"></spring:message>
									       </div>
									        <div class="errormsg" id="districtNameInEnExist_error">
											<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCONENGLISHExist"></spring:message>
									       </div> 
									
									<form:errors htmlEscape="true" path="newParliamentNameEnglish"
											class="errormsg"></form:errors> </span>	<span style="display: none;" id="districtNameInEn_msg">Please Enter Name of Parliament Constituency in English
									</span> 
								</li>
								<li><div id="errordistrictNameInEn" class="errormsg"></div></li>
								
								<li>
									<label><spring:message htmlEscape="true" code="Label.NAMEPARLIAMENTCONSTLOCAL"></spring:message> </label><span
										id="required"></span><br /> <form:input
											path="newParliamentNameLocal" id="districtNameInLcl"
											onkeypress="hideAll();"
											onfocus="validateOnFocus('districtNameInLcl');helpMessage(this,'districtNameInLcl_msg');"
											cssClass="frmfield" htmlEscape="true" style="width: 180px"
											onblur="vlidateOnblur('districtNameInLcl','1','25','o');hideHelp();"  
											onkeyup="hideMessageOnKeyPress('districtNameInLcl');" maxlength="50"/>
										<div class="errormsg" id="districtNameInLoc_error">
											<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCONLOCALData"></spring:message>
									       </div>
										<form:errors htmlEscape="true" path="newParliamentNameLocal"
											class="errormsg"></form:errors> <span style="display: none;" id="districtNameInLcl_msg">Please Enter Name of Parliament Constituency in Local
									</span> 
								</li>
								<li><div id="errordistrictNameInLcl" class="errormsg"></div>  &nbsp;</li>
								<li>
									<label><spring:message htmlEscape="true" code="Label.ECICODE"></spring:message> </label><span
										id="required"></span><br /> <form:input
											path="ParliamentECICODE" maxlength="10" style="width: 180px"
											 id="census2011Code"
											htmlEscape="true" cssClass="frmfield"
											onkeypress="hideAll();"
											onfocus="validateOnFocus('census2011Code');helpMessage(this,'census2011Code_msg');"
										    onblur="vlidateOnblur('census2011Code','1','25','o');hideHelp();validateNumericdigit('${stateCode}','P')" onkeyup="hideMessageOnKeyPress('census2011Code');"/>
  										<div class="errormsg" id="ECICODEData_error">
											<spring:message htmlEscape="true" code="Error.ECICODEData"></spring:message>
									       </div> 
												  <div class="errormsg" id="ECICODEExist_error">
											<spring:message htmlEscape="true" code="Error.ECICODEExist"></spring:message>
									       </div>
										<form:errors htmlEscape="true" path="ParliamentECICODE"
											class="errormsg"></form:errors>  	<span style="display: none;" id="census2011Code_msg">Please enter ECI Code here</span>
										<span class="error" id="census2011Code_error"></span>  <div id="errorcensus2011Code" class="errormsg"></div>
								</li>
							</ul>
						</div>
						<%@ include file="../common/gis_nodes_constituency.jspf"%>
						
						<div class="frmpnlbg">
							<div id='district'>
								<div class="frmtxt">
									<div class="frmhdtitle">
										<spring:message htmlEscape="true" code="Label.CONTRIPARLIAMENTCONSTITUENCY"></spring:message>
									</div>
															
									<ul class="blocklist">
									
										<li>
											<span class="errormsg" id="ddLgdLBDistCAreaDestL_error"> </span>&nbsp;<span><form:errors htmlEscape="true" path="contributedParliament" class="errormsg"></form:errors> </span>
										</li>
										<li>
											<div class="ms_container">
												<div class="ms_selectable">
													<label>
														<spring:message htmlEscape="true" code="Label.CONTRIPARLIAMENTCONSTITUENCY"></spring:message>
													</label>
													
													<form:select path="contributedParliament" class="frmtxtarea" id="ddStateParliamnetSource" htmlEscape="true" multiple="true">
														<form:options items="${distList}" itemValue="id.pcCode"	itemLabel="pcNameEnglish" htmlEscape="true"></form:options>
													</form:select>
												</div>
												
												
												<div class="ms_buttons">
													<label> 
														<input name="button3" class="bttn" type="button" onclick="addItemPC('ddStateParliamnetDest','ddStateParliamnetSource','FULL',true);" value=" Full PC &gt;&gt;" />
													</label>
													
													<label> 
														<input name="button22" class="bttn" type="button" onclick="clearList('ddDistrictParliamnetSource');removeItem('ddStateParliamnetDest', 'ddStateParliamnetSource', true);checkAll('ddStateParliamnetDest');" value=" Back &lt;&lt;" />
													</label>
													
													<label> 
														<input name="button22" class="bttn" type="button" onclick="addItemPC('ddStateParliamnetDest','ddStateParliamnetSource', 'PART',true);" value="Part PC &gt;&gt;" />
													</label>
												</div>
												
												<div class="ms_selection">
													<label>
														<spring:message htmlEscape="true" code="Label.CONTRIPARLIAMENTCONSTITUENCY"></spring:message>
													</label>
													<form:select name="select4"
																	id="ddStateParliamnetDest" size="1" multiple="multiple"
																	path="contributedParliament" class="frmtxtarea" htmlEscape="true">
																</form:select>
																		<div class="errormsg" id="AssemblyList_error">
															<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCListBlank"></spring:message>
									      					 </div>
									      					 <div class="errormsg" id="AssemblyListData_error">
															<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCONLISTDATAPart"></spring:message>
									      					 </div>
									      					 
									      					  <div class="errormsg" id="AssemblyListData_errorcov">
															<spring:message htmlEscape="true" code="Error.PARLIAMENTENTCONLISTDATAcovered"></spring:message>
									      					 </div>
									      					<br></br>
									      					 <input name="button2" class="btn" type="button" style="width: 120px" onclick="hideAll();getAssemblyRecordList();" value="Get Assembly List" />
															<br></br>
												</div> 
												
												
											</div>
										</li>
									
										
										<li>
											<div class="ms_container">
												<div class="ms_selectable">
													<label>
														<spring:message htmlEscape="true" code="Label.AVAILABLEASSEMBLYL"></spring:message>

													</label>
													
													<form:select path="contributedAssembly" class="frmtxtarea" id="ddDistrictParliamnetSource" multiple="true" htmlEscape="true">
													</form:select>
											
												</div>
												
												
												<div class="ms_buttons">
													<label> 
														<input name="button3" class="bttn" type="button" onclick="addItemPC('ddDistrictParliamnetDest','ddDistrictParliamnetSource','FULL',true);" value=" Full AC &gt;&gt;" />
													</label>
													
													<label> 
														<input name="button22" class="bttn" type="button" onclick="removeItem('ddDistrictParliamnetDest', 'ddDistrictParliamnetSource', true)" value=" Back &lt;&lt;" />
													</label>
												</div>
												
												<div class="ms_selection">
													<label>
														<spring:message htmlEscape="true" code="Label.CONTRIBUTINGASSEMBLYLIST"></spring:message>

													</label>
													<form:select name="select4" id="ddDistrictParliamnetDest" size="1" multiple="multiple" path="contributedAssembly" class="frmtxtarea" htmlEscape="true" >

													</form:select>
													<div class="errormsg" id="AssemblyListBlank_error">
													<spring:message htmlEscape="true" code="Error.ASSEMBLYCONLISTBLANK"></spring:message>
							      					 </div>
							      					<form:errors htmlEscape="true" path="contributedAssembly" class="errormsg"></form:errors>												</div> 
											
												
											</div>
										</li>
										
										
										
										
									</ul>
									<div class="clear"></div>
									
								</div>
							</div>
						</div>
						
						<div class="btnpnl">
							<ul class="listing">
								<li>
									
									<input type="submit" class="btn" id="Submit" value='<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>' onclick="return selectAssemblyList();" /> 
									<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"  onclick="javascript:location.href='parliament_Constituency.htm?<csrf:token uri="parliament_Constituency.htm"/>'" />

									<input type="button" name="Cancel" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
									
								</li>
							</ul>
						</div>							
					</div>
				</div>
			</form:form>
		</div>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
	 

</body>
</html>