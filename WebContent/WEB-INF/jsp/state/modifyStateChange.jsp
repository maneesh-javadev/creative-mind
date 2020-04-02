<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<link href="css/shiftdistrict.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>

<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!-- for Unique constrain  -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCommonService.js'></script>
<!-- for Unique constrain  -->
<script src="js/jquery-1.7.js" type="text/javascript"
	language="javascript"></script>
<script src="js/jquery.MultiFile.js" type="text/javascript"
	language="javascript"></script>
<script src="js/attachedFiles.js" type="text/javascript"
	language="javascript"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<head>
<title><spring:message code="Label.MODIFYSTATE" htmlEscape="true"></spring:message>
</title>
<script src="js/shiftdistrict.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<link rel="stylesheet" href="datepicker/demos.css" />
<script>
	var stateradio;
	var changEntityFlag='true';
	function show_msg(Field_msg) {
		var hint = '#' + Field_msg + "_msg";
		var error = '#' + Field_msg + "_error";
		$("#" + Field_msg).removeClass("error_fld");
		$("#" + Field_msg).removeClass("error_msg");
		$(hint).show();
		$(error).hide();

	}
	function resetVillageModifyForm()
	{
		document.getElementById('statename').value="";
		document.getElementById('txtAliasLocal').value="";
		document.getElementById('txtAliasEnglish').value="";
		document.getElementById('txtNameLocal').value="";

	}
	function getGisNodes1() {
		if (document.getElementById('txtlatitude').value != '') {
			var gisList = document.getElementById('txtlatitude').value
					.split(':');
			//i=gisList.length;

			document.getElementById('txtlatitude').value = gisList[0]
					.split(',')[0];
			document.getElementById('txtLongitude').value = gisList[0]
					.split(',')[1];

			for ( var k = 1; k < gisList.length; k++) {
				addgisnodes1();
				document.getElementById('lati' + k).value = gisList[k]
						.split(',')[0];
				document.getElementById('longi' + k).value = gisList[k]
						.split(',')[1];
			}
		}
	}

	function districtName() {
		$("#districtname_error").hide();

	}
	
	
	function valdiateStateSubmit()
	{
		
		var error=false;
		var mandatory_change_error=false;
		var mandatory_error=false;
		var stateNameInEnch=document.getElementById('statename').value; 
		var stateNameInEng=document.getElementById('stateNameEng').value;
		var stateNameInLc=document.getElementById('txtNameLocal').value; 
		var stateradioch=document.getElementById('stateradio').checked; 
		var sortNameEng=document.getElementById('sortName').checked;
	/* 	utradio=document.getElementById('utradio'); 
		alert(stateradio.checked);
		alert(utradio.checked); */
		//if(stateradio.checked)
		/* aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
		aliasNameInLc=document.getElementById('txtAliasLocal').value;  */

		if (!validateEntityEnglishNameBlank(stateNameInEnch, '#stateNameEngBlank_error'))
	{
			error = true;
			mandatory_error=true;
	}		
	
		if (!validateEntityEnglishNameData(stateNameInEnch, '#stateNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(stateNameInLc, '#stateNameLocData_error'))
			error = true;
		
		if (!validateEntityEnglishNameData(sortNameEng, '#sortNameEngData_error'))
			error = true;
		/* if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
			error = true; */
			/* alert(stateradio);
			alert(stateradioch); */
			
			if(mandatory_error==true)
				showClientSideError();
			else
				{
				if((stateNameInEnch==stateNameInEng) && (stateradioch==stateradio))
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
	
	
	function hideError()
	{
		previousEntityName=document.getElementById('statename').value;
		$("#entityNameInEnExist_error").hide();
		stateradio=document.getElementById('stateradio').checked; 
		hideAll();
		var errorflag='${modifyStateCmd.errorflag}';
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
		$("#stateNameEngBlank_error").hide();
		$("#stateNameEngData_error").hide();
		$("#stateNameLocData_error").hide();
		$("#sortNameEngData_error").hide();
		
		
	}
	
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideError, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideError );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideError;
	  }
</script>
</head>

<body
	onload='onLoadSelectDisturbed(); districtName();'>
	
	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
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
				
					<h3 class="subtitle"><spring:message code="Label.MODIFYSTATE" htmlEscape="true"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				       <%--//these links are not working  <li>
					 				           <a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message>
					</a>                     
					 				        </li> --%>
					 				        
					 			        </ul>
				
				
			
		</div>

		<div class="frmpnlbrdr">
			<form:form action="draftChangeState.htm"
				method="POST" commandName="modifyStateCmd" id="frmModifyDistrict">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftChangeState.htm"/>"/>
				<div id='changevillage' class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CHANGE" htmlEscape="true"></spring:message>
						</div>
						<div>
								<ul class="blocklist">
						<c:forEach var="listStateDetails"
								varStatus="listStateDetailsRow"
								items="${modifyStateCmd.listStateDetails}">
								
								<li>	
								
									<label for="statename"><spring:message
												code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br /> <label> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglishch">
												<input type="text" onkeypress="hideAll();"
													id="statename" maxlength="50" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false"/>" id="statename"
													cssClass="frmfield" 
													onfocus="validateOnFocus('statename');" 
										           onblur="validateEntityNameExist(null,'S',this.value,'statename');vlidateOnblur('statename','1','15','c');"  />
												<span class="error" id="districtname_error">State Name (In English) is Required</span>
											</spring:bind> </label>
											<div id="entityNameInEnExist_error" class="errormsg">
                                          <spring:message
                                                code="error.alredyExist.stateNameEnglish" htmlEscape="true"></spring:message>
                                          </div>
											<div class="errormsg" id="stateNameEngBlank_error">
												<spring:message htmlEscape="true" code="error.blank.stateNameInEnch"></spring:message>
									       </div>
									       <div class="errormsg" id="stateNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.StateNameEng"></spring:message>
									       </div>
											<form:errors path="stateNameEnglishch" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
								</li>
								
								
								<li>
										<label for="txtNameLocal"><spring:message
												code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message>
									</label><br /> <label> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">
												<input type="text" id="txtNameLocal"
													onkeypress="hideAll();" maxlength="50"
													class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false"/>"  />
											</spring:bind> </label>
											<div class="errormsg" id="stateNameLocData_error">
												<spring:message htmlEscape="true" code="Error.data.stateNameLoc"></spring:message>
									       </div>
											<form:errors path="stateNameLocal" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
								
								</li>
								
								<li>
										<label for="sortName"><spring:message
												code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message>
									</label><br/> <label> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].shortName" >
												<input type="text" id="sortName"
													onkeypress="hideAll();" maxlength="2"
													class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false"/>"  />
											</spring:bind> </label>
											 <div class="errormsg" id="sortNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.StateSortNameEng"></spring:message>
									       </div>
											
											<form:errors path="shortName" class="errormsg" htmlEscape="true"></form:errors> 
										<div class="errormsg"></div>
										
								</li>
								
								
								<li>
											<label>
									<div style="display:none;">
									<form:errors path="warningFlag" class="errormsg" htmlEscape="true"></form:errors> 
									</div>
								
										
										<spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">
												<input type="hidden"
														id="stateNameEng"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> 
										<spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> <spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> 
											<spring:bind
												path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateOrUt">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}"/>" />
											</spring:bind> 
											</label> 
								
								</li>
								
								<li>
								
												<label><spring:message
												code="Label.STATUS" htmlEscape="true"></spring:message>
												<span class="errormsg">*</span><br/>
												</label>
									
									<spring:bind
													path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateOrUtch">
													<input type="radio" id="stateradio" 
														name="<c:out value="${status.expression}" escapeXml="false"/>" 
														  value="S"
														<c:if test="${status.value =='S'}">checked</c:if>
														/>
												</spring:bind><!-- </td>
											<td > --><label for="stateradio"><spring:message code="Label.STATE" htmlEscape="true"></spring:message>
											</label><!-- </td>
											<td  > --><spring:bind
													path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateOrUtch">
													<input type="radio" id="utradio" 
														name="<c:out value="${status.expression}" escapeXml="false"/>" 
													    value="U"
														<c:if test="${status.value == 'U'}">checked</c:if>
													 />
												</spring:bind>
												<label for="utradio"> <spring:message code="Label.UT" htmlEscape="true"></spring:message>
											</label>
								</li>
								
						</c:forEach>
						
						
						<li><form:input path="stateId" type="hidden" name="stateId" id="stateId"  />	
								<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
								<form:hidden path="operation" value="M" />
						</li>
						
						
						</ul>
						
						
						</div>
						
					<%-- 	<table width="100%" class="tbl_no_brdr">
							<c:forEach var="listStateDetails"
								varStatus="listStateDetailsRow"
								items="${modifyStateCmd.listStateDetails}">
								<tr>
									<td width="14%" rowspan="6">&nbsp;</td>
									<td width="86%"></td>
								</tr>


								<tr>
									<td>
									</td>
								</tr>
								
								<tr>
									<td>
									</td>
								</tr>
								<tr>
									
									<td><!-- <div class="errormsg"></div> -->
									</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
								
									
									
									
									
									<td >
									</td>
								</tr>
							</c:forEach>
							<tr>
								
								<td width="14%" rowspan="5">&nbsp;</td>

							</tr>
						</table> --%>

					</div>
					<div class="btnpnl">
						 <input type="submit" class="btn"
										name="Submit"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
										onclick="return valdiateStateSubmit();" /> </label> 
										 <input type="button" 	name="Submit3" class="btn"
									value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
									id="btnClear" 
										onclick="javascript:location.href='modifyStatechangebyId.htm?<csrf:token uri='modifyStatechangebyId.htm'/>&stateId=${modifyStateCmd.stateId}';"  />        <label>
										<input type="button" class="btn" name="Submit3"
										value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>