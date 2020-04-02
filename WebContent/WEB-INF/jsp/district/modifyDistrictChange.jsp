<%@include file="../common/taglib_includes.jsp"%> 

<html>
<head>

<title>
	<spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<!-- for Unique constrain  -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCommonService.js'></script>
<!-- for Unique constrain  -->

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="datepicker/demos.css" />
<script>
	
	function valdiateDistrictSubmit()
	{
		
		var error=false;
		var mandatory_change_error=false;
		var mandatory_error=false;
		districtNameInEnch=document.getElementById('districtname').value; 
		districtNameInEn=document.getElementById('districtnameold').value; 
		districtNameInLc=document.getElementById('txtNameLocal').value; 
		aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
		aliasNameInLc=document.getElementById('txtAliasLocal').value; 
		
		if (!validateEntityEnglishNameBlank(districtNameInEnch, '#districtNameEngBlank_error'))
			{
			error = true;
			mandatory_error=true;
			}
		if (!validateDistrictEnglishNameData(districtNameInEnch, '#districtNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(districtNameInLc, '#districtNameLocData_error'))
			error = true;
		
		
		if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
			error = true;
		
		if(mandatory_error==true)
			showClientSideError();
		else
			{
			if((districtNameInEn==districtNameInEnch))
				mandatory_change_error=true;
			
			 if(mandatory_change_error==true)
				{
				
				error=true;
				showNoChaneClientSideError();
				}
			}
		
		
		if(error==true)
			return false;
		else
			return true;
		
		
	}
	
	
	function loadMe()
	{
		//alert("hi");
		previousEntityName=document.getElementById('districtnameold').value;
		var errorflag='<c:out value="${modifyDistrictCmd.errorflag}" escapeXml="true"></c:out>';
		//alert(errorflag);
		if(errorflag=="2")
			{
			showClientSideError();
			}
		else if(errorflag=="1")
			showNoChaneClientSideError();
		hideAll();
	}
	function hideAll()
	{
		
		
		$("#districtNameEngBlank_error").hide();
		$("#districtNameEngData_error").hide();
		$("#districtNameLocData_error").hide();
		$("#aliasNameEngData_error").hide();
		$("#aliasNameLocData_error").hide();
		$("#districtname_error").hide();
		$("#entityNameInEnExist_error").hide();
		
		//error.alredyExist.districtNameEnglish 
		
		
	}
	
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",loadMe, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", loadMe );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = loadMe;
	  }
	

	
	/* function hideError()
	{
		
		previousEntityName=document.getElementById('districtnameold').value;
		$("#entityNameInEnExist_error").hide();
		
	} */

/* 
	if ( window.addEventListener ) { 
	    window.addEventListener( "load",hideError, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", hideError );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = hideError;
	 } */
</script>
</head>

<body>
	
	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>
        	
        	  <div class="box" id="noChangeBox">
            <a class="boxclose" id="noChangeboxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.change.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>	

	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message></h3>
			<ul class="listing">
				<%--//these links are not working <li>
					<a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a>
				</li> --%>
			</ul>
			
		</div>

		<div class="frmpnlbrdr">
			<form:form action="draftModifyDistrict.htm" method="POST" commandName="modifyDistrictCmd" id="frmModifyDistrict">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='draftModifyDistrict.htm'/>" />
			 
 				<div id='changevillage' class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
						</div>
						<div class="block">
						<div  >
							<ul class="blocklist">
								<c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${modifyDistrictCmd.listDistrictDetails}">
									<li>
										<label>
											<spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message>
										</label><span class="errormsg">*</span><br /> 
										<label>
											<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglishch">
												<input type="text" onkeypress="hideAll();" maxlength="50" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" id="districtname" cssClass="frmfield"onfocus="validateOnFocus('districtname');helpMessage(this,'districtname_msg');" onblur="validateEntityNameExist(${stateCode},'D',this.value,'districtname');vlidateOnblur('districtname','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('districtname')" />		
										     	<div class="error" id="districtname_error"><spring:message htmlEscape="true" code="error.required.DISTRICTNAMEEN"></spring:message></div> 
											</spring:bind>
										</label>
											 <div id="entityNameInEnExist_error" class="errormsg">
                                          <spring:message
                                                code="error.alredyExist.districtNameEnglish" htmlEscape="true"></spring:message>
                                          </div>
											<div class="errormsg" id="districtNameEngBlank_error">
												<spring:message htmlEscape="true" code="error.blank.districtNameInEn"></spring:message>
									       </div>
									       <div class="errormsg" id="districtNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.DistrictNameEng"></spring:message>
									       </div>
											<form:errors path="districtNameEnglishch" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
										<div id="districtname_msg" style="display:none"><spring:message htmlEscape="true" code="Label.EnterDistrictName"></spring:message><%-- <spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/> --%></div>
									</li>
									<li>
										<label><spring:message code="Label.DISTRICTNAMEINLOCAL" htmlEscape="true"></spring:message>
										</label><br/> <label>
										<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocalch">
											<input type="text" id="txtNameLocal" onkeypress="hideAll();"  maxlength="50" class="frmfield" name="<c:out value="${status.expression}"/>"	value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="validateOnFocus('txtNameLocal');helpMessage(this,'txtNameLocal_msg');" onblur="vlidateOnblur('txtNameLocal','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('txtNameLocal')" />
										</spring:bind> </label>
										<div class="errormsg" id="districtNameLocData_error">
											<spring:message htmlEscape="true" code="Error.data.DistrictNameLocal"></spring:message>
								        </div>
										<form:errors path="districtNameLocalch" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
										<div id="txtNameLocal_msg" style="display:none"><spring:message htmlEscape="true" code="Label.EnterDistrictNameLocal"></spring:message><%-- <spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/> --%></div>
									</li>
									<li>
										<label><spring:message code="Label.DISTRICTALIASENGLISH" htmlEscape="true"></spring:message>
										</label><br /> <label> <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish" >
												<input type="text" id="txtAliasEnglish" onkeypress="hideAll();" maxlength="50" class="frmfield" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />
										</spring:bind> </label>
										<div class="errormsg" id="aliasNameEngData_error">
											<spring:message htmlEscape="true" code="Error.data.DistrictAliasNameEng"></spring:message>
									     </div>
										<form:errors path="aliasEnglish" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
									</li>
									<li>
										<label>
											<spring:message code="Label.DISTRICTALIASLOCAL" htmlEscape="true"></spring:message>
										</label><br /> 
										<label> 
										<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocalch">
											<input type="text" id="txtAliasLocal"onkeypress="hideAll();" maxlength="50"class="frmfield" name="<c:out value="${status.expression}"/>"value="<c:out value="${status.value}"  escapeXml="true"/>" />
										</spring:bind> 
											 <div class="errormsg" id="aliasNameLocData_error">
												<spring:message htmlEscape="true" code="Error.data.DistrictAliasNameLocal"></spring:message>
									       </div>
											<form:errors path="aliasLocalch" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
										<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
												<input type="hidden" id="districtnameold"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind>
										<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].stateCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].stateVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> </label> <!-- <div class="errormsg"></div> -->
									</li>
								</c:forEach>
								<li>
									<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>
									<form:input path="districtId" type="hidden" name="districtId" id="districtId"  htmlEscape="true" />	
									<form:hidden path="operation" value="M" htmlEscape="true"/>
								</li>
							</ul>
						 </div>
						</div>
						
					</div>
					<div class="btnpnl">
						<div class="listing">
							 <input type="submit" class="btn" name="Submit" 
							 		value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" 
							  		onclick="return valdiateDistrictSubmit();" />
							
							<input 	type="button" name="Submit3" class="btn"
								 	value=<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message> id="btnClear" 
								 	onclick="javascript:location.href='modifyDistrictchangebyId.htm?<csrf:token uri='modifyDistrictchangebyId.htm'/>&districtId=${modifyDistrictCmd.districtId }';"  />       
								
							<input 	type="button" class="btn" name="Submit3"
									value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
									onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> 
						</div>
					</div>
				</div>
			</form:form>
		</div>	
	</div>

</body>
</html>