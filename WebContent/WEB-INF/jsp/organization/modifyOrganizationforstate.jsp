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
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript">
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script src="js/common.js">dwr.engine.setActiveReverseAjax(true);</script>
<script type="text/javascript" language="javascript">
function formSubmit(){
	var error=0;
	var itr=0;
	var stateL=document.getElementById('stateL');
	var districtL=document.getElementById('districtL');
	var subDistrictL=document.getElementById('subDistrictL');
	var villageL=document.getElementById('villageL');

	if(document.getElementById('ministryName').value==0 || document.getElementById('ministryName').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Ministry from the list.");
	}
	if(itr==0 && (document.getElementById('deptOrgCode').value==0 || document.getElementById('deptOrgCode').value==""||document.getElementById('deptOrgCode').value=="Select")){
		error=1;
		itr=1;
		alert("Kindly select the Department from the list.");
	}
	if(itr==0 && document.getElementById('orgType').value==0 || document.getElementById('orgType').value==""){
		error=1;
		itr=1;
		alert("Kindly select the Organization Type from the list.");
	}
	if(itr==0 && document.getElementById('orgTypeName').value==""){
		error=1;
		itr=1;
		alert("Kindly fill the Organization name.");
	}
	/* if(itr==0 && !stateL.checked && !districtL.checked && !subDistrictL.checked && !villageL.checked){
		error=1;
		itr=1;
		alert("Kindly select either of the locations.");
	} */
	if(itr==0 && districtL.checked && !subDistrictL.checked && !villageL.checked){
		if(!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
	}
	
	if(subDistrictL.checked && !villageL.checked){
		if(!stateL.checked && !districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State and District level as well.");
		}
		if(itr==0 &&!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		if(itr==0 &&!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
		itr=0;
	}
	if(villageL.checked){
		if(!stateL.checked && !districtL.checked && !subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State, District and Sub District level as well.");
		}
		if(itr==0 && !stateL.checked && !districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State and District level as well.");
		}
		if(itr==0 &&!subDistrictL.checked){
			error=1;
			itr=1;
			alert("Kindly select the Sub District level as well.");
		}
		if(itr==0 &&!districtL.checked){
			error=1;
			itr=1;
			alert("Kindly select the District level as well.");
		}
		if(itr==0 &&!stateL.checked){
			error=1;
			itr=1;
			alert("Kindly select the State level as well.");
		}
	}
	if (error==0){
		document.forms['createOrganization'].submit();
	}
}
/* function formSubmite(){
	
	 var errors = new Array();
		var error = false;
			
		    errors[0] = vlidateOnblur('ministryName','1','15','c');
	       if(errors[0]==true){
			  error = true;
		  }
	 
		  errors[1] = vlidateOnblur('deptOrgCode','1','15','c');
		  if(errors[1]==true){
			  error = true;
		  }
		  errors[2] = vlidateOnblur('orgType','1','15','c');
		  if(errors[2]==true){
			  error = true;
		  }
		 
		  errors[3] = vlidateOnblur('deptName','1','15','c');
		  if(errors[3]==true){
			  error = true;
		  }
		 
		 
			if(error == true)
				{
				
				showClientSideError();
			
				return false;
				}
			else
				{
		
				document.forms['createOrganization'].submit();
			   return true;    
			
											
				
	}		
} */
	
	
	
function isNext(id){
	document.getElementById('btnSave').value="Save";
	if (document.getElementById(id).checked) {
		document.getElementById('btnSave').value="Next";
	} 
}
</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);"
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
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${family_msg}" escapeXml="true"></c:out></center>
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
	<div id="frmcontent">
		<div class="frmhd" >


			
					<%-- <spring:message code="Label.CREATEORG" htmlEscape="true"></spring:message> --%>
					<h3 class="subtitle">ModifyOrganizationStateLevel</h3>
					
					<ul class="listing">
					<li>
					<a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
							</li>
					<li><a href="#" class="frmhelp">Help</a></li>
					</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="modifyOrganizationStatepost.htm" method="POST" commandName="modifyOrganizationstate" onsubmit="Submit.disabled = true; return true;"  >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyOrganizationStatepost.htm"/>"/>
			<form:input type="hidden" path="orgCode" name="orgCode"/>
			
			
				<div id="cat">
					<div class="frmpnlbg">
						
							<%-- <tr>
								<td width="100%">
									<div class="frmtxt">
									<div class="frmhdtitle">
											<spring:message code="Label.SELMIN" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td width="14%">
													<td width="30%"><spring:message code="Label.MINISTRIES" htmlEscape="true"></spring:message>
														<span class="errormsg">*</span> <br /> <form:select
															path="ministryName" style="width: 200px" id="ministryName"
															class="combofield" onchange="getDepartmentList1(this.value)" onblur="vlidateOnblur('ministryName','1','15','c');">
															<form:option value="0">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:options items="${ministryList}" itemLabel="orgName"
																itemValue="orgCode" />
														</form:select>
												</td>
													<td width="56%"><form:errors htmlEscape="true"  path="ministryName"
															cssClass="errormsg"></form:errors></td>
											</tr>
											<tr><td>&nbsp;</td></tr>
											<tr>
												<td width="14%">
													<td width="30%"><spring:message code="Label.DEPTLIST" htmlEscape="true"></spring:message>
														<span class="errormsg">*</span> <br /> <form:select
															path="deptOrgCode" style="width: 200px" id="deptOrgCode"
															class="combofield" onblur="vlidateOnblur('deptOrgCode','1','15','c');">
															<form:option value="0">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
														</form:select>
												</td>
													<td width="56%"><form:errors htmlEscape="true"  path="ministryName"
															cssClass="errormsg"></form:errors></td>
											</tr> 
											<tr><td>&nbsp;</td></tr>
											<tr>
												<td width="14%">
													<td width="30%"><spring:message code="Label.ORGTYPELIST" htmlEscape="true"></spring:message>
														<span class="errormsg">*</span> <br /> <form:select
															path="orgType" style="width: 200px" id="orgType"
															class="combofield" onblur="vlidateOnblur('orgType','1','15','c');">
															<form:option value="0">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:options items="${orgType}" itemLabel="orgType"
																itemValue="orgTypeCode" />
														</form:select>
												</td>
													<td width="56%"><form:errors htmlEscape="true"  path="ministryName"
															cssClass="errormsg"></form:errors></td>
											</tr> 
											
											
										</table>
									</div>
									</div>
								</td>

							</tr> --%>
							
								
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message>
										</div>
										
												<div class="search_criteria">
												<ul class="blocklist">
												<li>
												<spring:message code="Label.ORGNAMEINENG" htmlEscape="true"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														path="orgName" id="orgName" maxlength="200" style="width: 200px" class="frmfield" onblur="('orgName','1','15','c');" htmlEscape="true"></form:input>
														<form:input path="orgNamech" type="hidden" />
												
												
												<form:errors htmlEscape="true"  path="orgName"
														cssClass="errormsg"></form:errors>
													</li>	
														
												
											
											<!-- <tr>
												<td width="14%"></td>

												<td width="30%">
												 -->
												 <li>
												 <div class="horizonlabel">
												 <ul class="horizonlisting">
													<li>
													 <label>
														<spring:message code="Label.ORGNAMEINLOCAL" htmlEscape="true"></spring:message>
													</label>
													</li>
												</ul>
												</div>	
													<div  >
													 <form:input path="orgNameLocal" style="width: 200px" maxlength="80"
														class="frmfield" htmlEscape="true"></form:input>
											<!-- 	</td>
												<td width="56%"> -->
												
												<form:errors htmlEscape="true"  path="orgNameLocal"
														cssClass="errormsg"></form:errors>
														</div>
													</li>	
														
													<!-- 	</td> -->
											
										<!-- 	</tr> -->
											
											<!-- <tr>
												<td width="14%"></td>
												<td width="30%"> -->
												
												<li>
												<spring:message
														code="Label.SHORTORGNAME" htmlEscape="true"></spring:message> <br /> <form:input
														path="shortName" style="width: 200px" class="frmfield" maxlength="10" htmlEscape="true"></form:input>
												
												<!-- </td>
												<td width="56%"> -->
												
												<form:errors htmlEscape="true"  path="shortName"
														cssClass="errormsg"></form:errors>
														
														</li>
														</ul>
														</div>
												
														
											<!-- 			</td>
											</tr>
											
										</table> -->
										
									</div>
									 
						
							<%-- <tr>
								<td width="100%">
								</br>
									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="App.SPECIFYLOCATION" htmlEscape="true"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
											<tr>
												<td><spring:message
														code="Label.SPECIFYLOCORG" htmlEscape="true"></spring:message> </br></br></td>
											</tr>
													<tr> 
														<td><form:checkbox  name="checkbox" value="S" path="specificLevel" id="stateL" onclick="isNext(this.id)"/>
															<spring:message code="Label.STATELEVEL" htmlEscape="true"></spring:message>
														&nbsp;&nbsp;
														<form:checkbox  name="checkbox" value="D" path="specificLevel" id="districtL"/>
															<spring:message code="Label.DISTRICTLVL" htmlEscape="true"></spring:message>
														&nbsp;&nbsp;
														<form:checkbox  name="checkbox" value="T" path="specificLevel" id="subDistrictL"/>
															<spring:message code="Label.SUBDISTRICTLVL" htmlEscape="true"></spring:message>
														&nbsp;&nbsp;
														<form:checkbox  name="checkbox" value="V" path="specificLevel" id="villageL"/>
															<spring:message code="Label.VILLAGELVL" htmlEscape="true"></spring:message>
														</td>
													</tr>
										</table>
									</div></td>
							</tr> --%>
						

					</div>
					 <div class="btnpnl">
                              
                                  
                                
                                    <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return onCsave();"    />
                                    
                                    
                                  <input
									type="button" name="Submit3" class="btn"
									value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>"
									id="btnClear" onclick="onClear();" />
                                      
                                    
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /></label>
                                  
                                
                              
					      </div>
							  
				</div>
			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>	
		</div>
	</div>
	

</body>
</html>