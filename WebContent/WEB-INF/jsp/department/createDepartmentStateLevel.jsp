<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script src="js/createDepartment.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />


<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript">
</script>
<script src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script src="js/common.js">dwr.engine.setActiveReverseAjax(true);</script>
<script type="text/javascript" language="javascript">

function onLoadSelect(){
	document.getElementById('txtAddAnother').value="";
	var mypath = window.location.href;
	var mySplitResult = mypath.split("=")[1].split(",");
	if (mySplitResult[1] != null) {
		document.getElementById('btnSave').value="Next";
	} 
}
function addAnotherSubmit(){
	document.getElementById('txtAddAnother').value="true";
	var isError=validation();
	if(isError==0){
		document.forms['createDepartment'].submit();
	}
} 
/* function formSubmit(){
	var isError=validation();
	if(isError==0){
		document.forms['createDepartment'].submit();
	}
} */
 function validation(){
	var error=0;
	var itr=0;
	var selObj=document.getElementById('ddTargetState');	
	
	if(itr==0 && document.getElementById('deptName').value==""){
		error=1;
		itr=1;
		//alert("Kindly fill the state level unit name.");
	}
	if(itr==0 && document.getElementById('rdSpecificState').checked){
		for (var i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		}
		if(document.getElementById('ddTargetState').value==""){
			error=1;
			itr=1;
			//alert("Kindly select state from the list.");
		}
	}
	return error;
} 
function formSubmite(){
var errors = new Array();
	
	var error = false;  
	var selObj=document.getElementById('ddTargetState');
	    errors[0] = vlidateOnblur('deptName','1','15','c');

	   
	  if(errors[0]==true){
		  error = true;
	  }
	
		if(error == true)
			{
			showClientSideError();
			return false;
			}
		else
			{
			
			document.forms['createDepartment'].submit();
		 return true; 
			}
	
	
	
}
</script>
</head>


<body onload="onLoadSelect()"
     onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">
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
							<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
						</div>
					</td>
				</tr>
			</table>

		</c:if>

		<c:if test="${!empty family_error}">

			<table>
				<tr>
					<td rowspan="2"><div class="failur"></div></td>

					<td><center>
							<div class="helpMsgHeader" style="width: 275px;">
								<b>Failure Message</b>
							</div>
						</center></td>
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
				<td rowspan="2"><div class="errorImg"></div></td>
				<td><div class="helpMsgHeader" style="width: 275px;">
						<h4>Error Message</h4>
					</div></td>
			</tr>
			<tr>
				<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
				</td>
			</tr>
		</table>

	</div>
</div>
	<div id="frmcontent">
		<div class="frmhd" >


			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.CREATEDEPTSTATELEVEL"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<td width="50" align="right"><a href="#" class="frmhelp">Help</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="createDepartmentStateLevel.htm?level=${level}" method="POST" commandName="createDepartment">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createDepartmentStateLevel.htm"/>"/>
			<form:hidden path="orgLevel" />
				<div id="cat">
					<div class="frmpnlbg">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="100%">

									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.GENERALDETAILS"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.DEPTNAMES"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="deptName" id="deptName" style="width: 200px" class="frmfield" onblur="vlidateOnblur('deptName','1','15','m');"></form:input>
												</td>
												<td width="56%"><form:errors htmlEscape="true"  path="deptName"
														cssClass="errormsg"></form:errors></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true"  code="Label.NL"></spring:message>
													<br /> <form:input path="deptNameLocal" style="width: 200px"
														class="frmfield"></form:input>
												</td>
												<td width="56%"></td>
											</tr>
											<tr>
												<td width="14%"></td>
												<td width="30%"><spring:message htmlEscape="true" 
														code="Label.SHORTNAMEENGLISH"></spring:message> <br /> <form:input
														path="shortDeptName" style="width: 200px" class="frmfield"></form:input>
												</td>
												<td width="56%"></td>
											</tr>
											
										</table>
									</div></td>
							</tr>
							<tr>
								<td width="100%">
								</br>
									<div class="frmtxt" id="divStatelvl">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.STATELEVEL"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%"></td>
												<td width="36%" class="tblclear">
												<form:radiobutton
														path="levelradio" id="rdAllState" value="F" htmlEscape="true"
														onclick="divSpecificState();" /> <spring:message htmlEscape="true" 
														code="Label.ALLSTATE"></spring:message> 
														&nbsp;&nbsp;&nbsp;&nbsp;
														<form:radiobutton
														path="levelradio" value="S" checked="true" onclick="divSpecificState();"
														id="rdSpecificState" /> <spring:message htmlEscape="true" 
														code="Label.SPECIFICSTATE"></spring:message></td>
												<td width="50%"></td>
											</tr>
											<tr>
												<td>
											</tr>
											<tr>
												<td width="100%" colspan="3">
													<div id="divSpecificState">
														<table width="850" class="tbl_no_brdr">

															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td width="275" rowspan="3" align="center">
																<spring:message htmlEscape="true" 
																		code="Label.STATELIST"></spring:message><br /> <form:select
																		path="" multiple="true" htmlEscape="true"
																		style="height:100px; width: 233px" class="frmtxtarea" id="ddSourceState">
																		<form:options items="${stateSourceList}" 
																			itemValue="statePK.stateCode"
																			itemLabel="stateNameEnglish"></form:options>
																	</form:select></td>

																<td width="100" align="center"><input type="button"
																	class="btn" value=" &gt;&gt;" style="width: 40px"
																	onclick="listbox_moveacross('ddSourceState', 'ddTargetState')" />
																</td>

																<td width="275" rowspan="3" align="center"><spring:message htmlEscape="true" 
																		code="Label.STATELISTSELECTED"></spring:message><br /> <form:select
																		path="stateName" multiple="true" id="ddTargetState"
																		style="height:100px; width: 233px" class="frmtxtarea"/>
																</td>
															</tr>

															<tr>
																<td align="center"><input type="button"
																	value=" &lt;&lt;" class="btn" style="width: 40px"
																	onclick="listbox_moveacross('ddTargetState', 'ddSourceState')" />
																</td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
							<input type="hidden" name="addAnother" id="txtAddAnother"/>
								<td width="16%" rowspan="2">&nbsp;</td>
								<td width="84%" align="left"><label> <input
										type="button" name="Save" class="button" id="btnSave" onclick="return formSubmite();"
										value=<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> /> </label>
<!-- 										<input type="button" value="Add Another State Level Setup" class="button" onclick="addAnotherSubmit();"/> -->
										<label>
										<input type="button" class="button" name="Cancel"
										value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
					<br /> <br /> <br />
					</td>

					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					</table>
				</div>
				</div>
			</form:form>
		</div>
	</div>
	</td>

</body>
</html>