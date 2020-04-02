<%@include file="../common/taglib_includes.jsp"%> 

<html>


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>
	<spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCommonService.js'></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>

<link href="css/error.css" rel="stylesheet" type="text/css" />

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
 <link href="css/successMessage.css" rel="stylesheet" type="text/css" /> 



<link rel="stylesheet" href="datepicker/demos.css" />
<script>
function valdiateSubDistrictSubmit()
{
	
	var error=false;
	var mandatory_change_error=false;
	var mandatory_error=false;
	subDistrictNameInEnch=document.getElementById('subdistrictNameEnglishch').value; 
	subDistrictNameInEn=document.getElementById('subdistrictNameEnglish').value; 
	subDistrictNameInLc=document.getElementById('subdistrictNameInLc').value; 
	aliasNameInEn=document.getElementById('AliasNameEnglish').value; 
	aliasNameInLc=document.getElementById('AliasNameLocal').value; 
	
	if (!validateEntityEnglishNameBlank(subDistrictNameInEnch, '#subdistrictNameEngBlank_error'))
		{
		error = true;
		mandatory_error=true;
		}
	if (!validateSubdistrictNameEnglish(subDistrictNameInEnch, '#subdistrictNameEngData_error'))
		error = true;
	if (!validateEntityNameLocalData(subDistrictNameInLc, '#subdistrictNameLocData_error'))
		error = true;
	
	
	if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
		error = true;
	if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
		error = true;
	
	if(mandatory_error==true)
		showClientSideError();
	else
		{
		if((subDistrictNameInEn==subDistrictNameInEnch))
			mandatory_change_error=true;
		
		 if(mandatory_change_error==true)
			{
			
			error=true;
			showNoChaneClientSideError();
			}
		}
	
	
	if(error==true)
		return false;
	else{
		$('input:submit').attr("disabled", true);
		return true;
		
	}
	
	
}


function subdistrictName()
{
	if(document.getElementById("subdistrictname").value == "" )
	{
		document.getElementById("subdistrictname_error").innerHTML="Sub District Name in English is Required";
		$("#subdistrictname_error").show();
		$("#subdistrictname_msg").hide();
		$("#subdistrictname").addClass("error_fld");
		$("#subdistrictname").addClass("error_msg");
		return false;

	}
	else 
	{
		$("#subdistrictname_msg").hide();
		return true;
			
	}
}


function chkSubdistrictNameOnLoad() {
		$("#subdistrictname_error").hide();

}

function loadMe()
{
	previousEntityName=document.getElementById('subdistrictNameEnglish').value;
	hideError();
	var errorflag='<c:out value="${modifySubDistrictCmd.errorflag}" escapeXml="true"></c:out>';
	//alert(errorflag);
	if(errorflag=="2")
		{
		showClientSideError();
		}
	else if(errorflag=="1")
		showNoChaneClientSideError();
}

function hideError()
{
	
	
	$("#entityNameInEnExist_error").hide();
	$("#subdistrictNameEngBlank_error").hide();
	$("#subdistrictNameEngData_error").hide();
	$("#subdistrictname_error").hide();
	$("#subdistrictNameLocData_error").hide();
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
						<h3 class="subtitle"><spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message></h3> 
						<ul class="listing">
							<%--//these links are not working <li>
								<a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message></a>
							</li> --%>
						</ul>
					</div>
					
					<div class="frmpnlbrdr">
					<form:form action="draftModifySubDistrict.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='draftModifySubDistrict.htm'/>"/>
					    <div   id='changevillage' class="frmpnlbg" >
							  <div class="frmtxt">
								  <div class="frmhdtitle" ><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message> </div>
								  	<div class="block">
										<div  >
											<ul class="blocklist">
											  <c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
		                                    	<li>
			                                    	<label><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br />
	                                         		 <label>
	                                          		 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglishch">							
									            	 <input type="text" class="frmfield" maxlength="50" onkeypress="validateCharKeysWithDotSlash(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  
										             id="subdistrictNameEnglishch" class="frmfield"  onfocus="validateOnFocus('subdistrictname');"onblur="hideError();validateEntityNameExist(${districtCode},'T',this.value,'subdistrictNameEnglishch');"/>							
						    	                  	 </spring:bind>
	                                         		 </label>
	                                         		 <div id="entityNameInEnExist_error" class="errormsg">
			                                          <spring:message
			                                                code="Error.alredyExist.SubDistrictNameEng" htmlEscape="true"></spring:message>
			                                          </div>
			                                          
			                                          <div class="errormsg" id="subdistrictNameEngBlank_error">
															<spring:message htmlEscape="true" code="Error.blank.subdistrictNameInEn"></spring:message>
												       </div>
												       <div class="errormsg" id="subdistrictNameEngData_error">
															<spring:message htmlEscape="true" code="Error.data.SubDistrictNameEng"></spring:message>
												       </div>
			                                          <div class="errormsg" id="subdistrictname_error"><spring:message
			                                                code="Error.SUBDISTRICTENGLISH" htmlEscape="true"></spring:message></div>
			                                          <span><form:errors path="subdistrictNameEnglishch"
			                                                class="errormsg" htmlEscape="true"></form:errors></span>  
			                                          <div class="errormsg"></div>
		                                    	</li>
		                                    	<li>
		                                    		<label><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></label><br />
			                                          <label>
			                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocalch">							
											            <input type="text" class="frmfield" id="subdistrictNameInLc"  maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />							
								    	               </spring:bind>
			                                          </label>
			                                          <div class="errormsg" id="subdistrictNameLocData_error">
															<spring:message htmlEscape="true" code="Error.data.SubDistrictNameLocal"></spring:message>
												       </div>
			                                          <form:errors path="subdistrictNameLocalch" class="errormsg" htmlEscape="true"></form:errors> 
			                                          <div class="errormsg"></div>
		                                    	</li>
		                                    	<li>
		                                    		<label><spring:message code="Label.SUBDISTRICTALIASENGLISH" htmlEscape="true"></spring:message></label><br />
			                                          <label>
			                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglishch" htmlEscape="true">							
											            <input type="text" class="frmfield" id="AliasNameEnglish"  maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />							
								    	                </spring:bind>
			                                          </label>
			                                            <div class="errormsg" id="aliasNameEngData_error">
															<spring:message htmlEscape="true" code="Error.data.SubDistrictAliasNameEng"></spring:message>
												       </div>
			                                          <form:errors path="aliasEnglishch" class="errormsg" htmlEscape="true"></form:errors> 
			                                          <div class="errormsg"></div>
		                                    	</li>
		                                    	<li>
		                                    			<label><spring:message code="Label.SUBDISTRICTALIASLOCAL" htmlEscape="true"></spring:message></label><br />
			                                          <label>
			                                           <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocalch">							
											            <input type="text" id="AliasNameLocal" class="frmfield"  maxlength="50" onkeypress="validateCharKeys(event)" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />							
								    	              
								    	               </spring:bind>
								    	                <div class="errormsg" id="aliasNameLocData_error">
															<spring:message htmlEscape="true" code="Error.data.SubDistrictAliasNameLocal"></spring:message>
												       </div>
								    	               <form:errors path="aliasLocalch"
			                                                class="errormsg" htmlEscape="true"></form:errors> 
			                                          <div class="errormsg"></div>
								    	                 <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">							
										  				<input type="hidden" id="subdistrictNameEnglish" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
													</spring:bind>
								    	               <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">							
										  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
													</spring:bind>
														<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">							
										  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
													</spring:bind>
													<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_code">							
										  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
													</spring:bind>
													<spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].district_version">							
										  				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>
													</spring:bind>
			                                         </label>
		                                    	</li>
		                                    
		                                 	  </c:forEach>
		                                 	  <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>
			                                  <form:input path="subdistrictId" type="hidden" name="subdistrictId" id="subdistrictId"  htmlEscape="true"/>
											  <form:hidden path="operation" value="M"  htmlEscape="true"/>
		                                 	 </ul>
		                                 </div>
		                            </div>
		                        </div>
		                        <div class="btnpnl">
			                        <input type="submit" class="btn" name="Submit" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return valdiateSubDistrictSubmit();" />
			                        <input type="button" name="Submit3" class="btn" value=<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message> id="btnClear" onclick="javascript:location.href='modifySubdistrictchangebyId.htm?<csrf:token uri='modifySubdistrictchangebyId.htm'/>&subdistrictId=${modifySubDistrictCmd.subdistrictId}';" />
				                    <input type="button" class="btn" name="Submit3" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
		                        </div>
		                   </div>
                     </form:form>
                  </div>    
				</div>
		<script src="/LGD/JavaScriptServlet"></script>

					
</body>
</html>