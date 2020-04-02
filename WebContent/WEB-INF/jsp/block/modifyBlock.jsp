<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
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


	
	<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	
	<script src="js/govtorder.js"></script>
	<script src="js/common.js"></script>
	
	<link href="css/error.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="js/cancel.js"></script>
	
	<title><spring:message code="Label.MODIFYBLOCK"></spring:message>
	</title>
	

	<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
	
	<script>
	
function loadMe()
{
	hideAll();
	previousEntityName=document.getElementById('blockname').value;
	var errorflag='<c:out value="${modifyBlockCmd.errorflag}" escapeXml="true"></c:out>';
	//alert(errorflag);
	if(errorflag=="2")
		{
		showClientSideError();
		}
	else if(errorflag=="1")
		showNoChaneClientSideError();
	
}

function hideAll()
{
	$("#blockNameEngBlank_error").hide();
	$("#entityNameInEnExist_error").hide();
	$("#blockNameEngData_error").hide();
	$("#blockNameLcData_error").hide();
	$("#aliasNameEngData_error").hide();
	$("#aliasNameLocData_error").hide();

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

function validateBlockChange()
{
	
	var mandatory_change_error=false;
	var mandatory_error=false;
	var blockNameEngch=document.getElementById("blocknamech").value;
	var blockNameEng=document.getElementById("blockname").value;
	var blockNameLc=document.getElementById("txtNameLocal").value;
	var aliasNameInEn=document.getElementById("txtAliasEnglish").value;
	var aliasNameInLc=document.getElementById("txtAliasLocal").value;
	var error=false;
	if(blockNameEngch.length<=0)
		{
		$("#blockNameEngBlank_error").show();
		error=true;
		mandatory_error=true;
		}
	
	else if (!validateBlockNameEnglish(blockNameEngch, "#blockNameEngData_error"))
			error = true;
		
	

if (!validateEntityNameLocalData(blockNameLc, '#blockNameLcData_error'))
	error = true;
	
if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
	error = true;
if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
	error = true;
	
if(mandatory_error==true)
	showClientSideError();
else
	{
	if((blockNameEng==blockNameEngch))
		mandatory_change_error=true;
	
	 if(mandatory_change_error==true)
		{
		
		error=true;
		showNoChaneClientSideError();
		}
	}

	
	if(error==true)
		{
		return false;
		}
	else
		{
		return true;
		}
	
}



</script>

</head>

	<body>
	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
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
			<h3 class="subtitle"><spring:message code="Label.MODIFYBLOCK"  htmlEscape="true"></spring:message></h3>
				<ul class="listing">
					<%--//these links are not working <li>
						<a href="#" class="frmhelp"><spring:message code="Button.HELP"  htmlEscape="true"></spring:message> </a>			
					</li> --%>
				</ul>
			</div>
			
			<div class="frmpnlbrdr">
				<form:form action="modifyBlockAction.htm" method="POST" commandName="modifyBlockCmd" id="frmModifyBlock" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockAction.htm"/>" />
					<div id='changevillage' class="frmpnlbg" style="visibility: visible; display: block">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
							</div>
							<c:forEach var="listBlockDetails" varStatus="listBlockDetailsRow"	items="${modifyBlockCmd.listBlockDetails}">
								<div class="block">
									<ul class="blocklist">
										<li>
											<label><spring:message
													code="Label.NAMEOFNEWBLOCKENGLISH" htmlEscape="true"></spring:message> </label><span
												class="errormsg">*</span><br /> <label> <spring:bind
													path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglishch">
													<input type="text" class="frmfield"
														 maxlength="50"
														onchange="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" id="blocknamech"
														cssClass="frmfield" 
														onblur="validateEntityNameExist(${modifyBlockCmd.dlc},'B',this.value,'blocknamech');" 
													   escapeXml="false" />

													<span class="error" id="blockname_error"></span>

												</spring:bind> </label>
												 <div class="errormsg" id="blockNameEngBlank_error">
												<spring:message htmlEscape="true" code="Error.blank.BlockNameEng"></spring:message>
									       </div>
									        <div id="entityNameInEnExist_error" class="errormsg">
                                          <spring:message
                                                code="error.alredyExist.blockNameEng" htmlEscape="true"></spring:message>
                                          </div>
									        <div class="errormsg" id="blockNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.BlockNameEng"></spring:message>
									       </div>
												 <form:errors path="blockNameEnglishch" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										</li>
										<li>
											<label><spring:message
													code="Label.NAMEOFNEWBLOCKLOCAL" htmlEscape="true"></spring:message> </label><br /> <label>
												<spring:bind
													path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameLocalch">
													<input type="text" id="txtNameLocal"
														onkeypress="validateCharKeys(event)" maxlength="50"
														onchange="hideAll();"
														class="frmfield"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														 escapeXml="false" />

												</spring:bind> </label>
												 <div class="errormsg" id="blockNameLcData_error">
											 <spring:message htmlEscape="true" code="Error.data.BlockNameLc"></spring:message>
									       </div>
												<form:errors path="blockNameLocalch" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										</li>
										<li>
											<label><spring:message
													code="Label.ALIASENGLISH" htmlEscape="true"></spring:message> </label><br /> <label>
												<spring:bind
													path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasEnglishch">
													<input type="text" id="txtAliasEnglish"
														onkeypress="validateCharKeys(event)" maxlength="50"
														onchange="hideAll();"
														class="frmfield"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														escapeXml="false" />

												</spring:bind> </label>
												 <div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.BlockAliasNameEng"></spring:message>
									       </div>
												<form:errors path="aliasEnglishch" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										</li>
										<li>
											<label><spring:message
												code="Label.ALIASLOCAL" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasLocalch">
												<input type="text" id="txtAliasLocal"
													onkeypress="validateCharKeys(event)" maxlength="50"
													onchange="hideAll();"
													class="frmfield"
													name="<c:out value="${status.expression}" />"
													value="<c:out value="${status.value}" escapeXml="true" />"
													/>

											</spring:bind>
											 <div class="errormsg" id="aliasNameLocData_error">
											<spring:message htmlEscape="true" code="Error.data.BlockAliasNameLocal"></spring:message>
								       </div>
											<form:errors path="aliasLocalch" cssClass="errormsg" htmlEscape="true"></form:errors> 
										 <div class="errormsg"></div> 
											<spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglish">
												<input type="hidden" id="blockname"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind>
											<spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].districtCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].districtVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> </label>
										</li>
									</ul>
								</div>
							</c:forEach>
							<form:input path="blockId" type="hidden" name="blockId" id="blockId"  />
						</div>
						<div class="btnpnl">
							<ul class="listing">
								<li>
									<label> <input type="submit" class="btn" onclick="return validateBlockChange();"
										name="Submit"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
									</label> <input 	type="button" name="Submit3" class="btn"
								 	value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>" id="btnClear" 
								 	onclick="javascript:location.href='modifyBlock.htm?<csrf:token uri='modifyBlock.htm'/>&blockId=${modifyBlockCmd.blockId}';"  />    
									<label> <input type="button" class="btn"
										name="Submit3"
										value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
								</li>
							</ul>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<script src="/LGD/JavaScriptServlet"></script>
	</body>
</html>