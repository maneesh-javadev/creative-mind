<!DOCTYPE htmlUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/configmap.js"></script>
<title><spring:message htmlEscape="true"  code="Label.VIEWVILLAGE"></spring:message> >>>cons
</title>
<script type="text/javascript">

function validAll(val)
{
  //alert("validate");
	
  flag=true;
	
			var obj=document.getElementById('parliamentupload');	
			var obj1=document.getElementById('assemblyupload');	
			var patternu;
			patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
			
		//alert("mid"+val);
			if(val == true)
				{
				if(obj.checked)
				{
					document.getElementById('parliamentbaseurl').style.visibility = 'hidden';
					document.getElementById('parliamentbaseurl').style.display = 'none';
					 document.getElementById('parliamentbaseurl_error').style.visibility = 'hidden';	
					 document.getElementById('parliamentbaseurl_error').style.display = 'none';
					
				}
			else
				{
				//alert("me");
								 if(document.getElementById('parliamentbaseurlt').value=="")
					 {
					 //alert("inside");Base URL is Required
					  document.getElementById('parliamentbaseurl_error').innerHTML="Base URL is Required";
					 document.getElementById('parliamentbaseurl_error').style.visibility = 'visible';	
					 document.getElementById('parliamentbaseurl_error').style.display = 'block';
					
					 flag=false;
					   
					 }
				 else
					 {
					 if(!patternu.test(document.getElementById('parliamentbaseurlt').value))
					 {
						 document.getElementById('parliamentbaseurl_error').innerHTML="Base URL is not proper format";
						 document.getElementById('parliamentbaseurl_error').style.visibility = 'visible';	
						 document.getElementById('parliamentbaseurl_error').style.display = 'block';
						 flag=false;
					 }
					 else
						 {
						 document.getElementById('parliamentbaseurl_error').style.visibility = 'hidden';	
						 document.getElementById('parliamentbaseurl_error').style.display = 'none';
						 }
						
					 
					 }
				 
				}
				
				if(obj1.checked)
				{
					document.getElementById('assemblybaseurl').style.visibility = 'hidden';
					document.getElementById('assemblybaseurl').style.display = 'none';
					 document.getElementById('assemblybaseurl_error').style.visibility = 'hidden';	
					 document.getElementById('assemblybaseurl_error').style.display = 'none';
					
				}
			else
				{
				//alert("me");
				//alert(document.getElementById('baseUrl_' + pos).value);
				 if(document.getElementById('assemblybaseurlt').value=="")
					 {
					 //alert("inside");Base URL is Required
					  document.getElementById('assemblybaseurl_error').innerHTML="Base URL is Required";
					 document.getElementById('assemblybaseurl_error').style.visibility = 'visible';	
					 document.getElementById('assemblybaseurl_error').style.display = 'block';
					
					 flag=false;
					   
					 }
				 else
					 {
					 if(!patternu.test(document.getElementById('assemblybaseurlt').value))
					 {
						 document.getElementById('assemblybaseurl_error').innerHTML="Base URL is not proper format";
						 document.getElementById('assemblybaseurl_error').style.visibility = 'visible';	
						 document.getElementById('assemblybaseurl_error').style.display = 'block';
						 flag=false;
					 }
					 else
						 {
						 document.getElementById('assemblybaseurl_error').style.visibility = 'hidden';	
						 document.getElementById('assemblybaseurl_error').style.display = 'none';
						 }
						
					 
					 }
				 
				}
				
		}
	
	//alert(flag);
	return flag;
	
	
}

var checkedRadio;
function ClearRd(chkRd, idname, chkRd1,errorid) {		

	var chkRd = document.getElementById(chkRd);
	var chkRd1 = document.getElementById(chkRd1);

	if (chkRd.checked) {
		document.getElementById(idname).disabled = false;
		document.getElementById(idname).style.visibility = "visible";
		document.getElementById(idname).style.display = 'block';
		
		

	} else if (chkRd1.checked) {
		document.getElementById(idname).disabled = true;
		document.getElementById(idname).style.visibility = "hidden";
		document.getElementById(idname).style.display = 'none';
		document.getElementById(errorid).style.visibility = "hidden";
		document.getElementById(errorid).style.display = 'none';
		document.getElementById(idname+'t').value="";
		 
		
	}
}
	
		
	  if ( window.addEventListener ) { 
		     window.addEventListener( "load", ClearAll, false );
		  }
		  else 
		     if ( window.attachEvent ) { 
		        window.attachEvent( "onload", ClearAll );
		  } else 
		        if ( window.onLoad ) {
		           window.onload = ClearAll;
		  }
	
	function doSubmitConstituency() {
		
		document.getElementById('savebtn').disabled = true;
		document.forms['form1'].submit();
	}
	
	function ClearAll()
	{
		ClearRd('parliamentseperateserver','parliamentbaseurl','parliamentupload','parliamentbaseurl_error');
		ClearRd('assemblyseperateserver','assemblybaseurl','assemblyupload','assemblybaseurl_error');
		
	}
</script>
</head>
<body > <!-- onload="onloadme();"> -->
	
		<form:form action="viewConfigMapConstituncy.htm" id="form1"
									method="POST" commandName="configureMapForm">
									<input type="hidden" name="<csrf:token-name/>"
										value="<csrf:token-value uri="viewConfigMapConstituncy.htm"/>" />
		<!-- <table width="100%" class="tbl_no_brdr">
		
				<tr>
					<td width="100%" valign="top" class="tblclear"> -->
						<div id="frmcontent">
							<div class="frmhd">
								
								 <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<%-- //these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li> 
 				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
								
								
								
								
							</div>
							<div class="frmpnlbrdr">
							<div class="frmpnlbg">
										<div class="frmtxt">
								
									
											<div class="frmhdtitle">
												<label> <spring:message htmlEscape="true"  code="Label.TYPECONSTITUENCY"></spring:message>
												</label>
											</div>
											<div> <form:errors path="baseUrl" cssClass="errormsg"/>
												
														</div>
											<div class="table col_3"> 
												<c:forEach var="listConfigurationMapConstituency"
													varStatus="listConstituencyDetailsRow"
													items="${configureMapForm.listConfigurationMapConstituency}">
													<c:if test="${fn:containsIgnoreCase(listConfigurationMapConstituency.constituencyType,'P')}">
													 <div class="trow red"> 
														<%-- <c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}"> --%>
														
															<div class="td column col1">	<label> <spring:message htmlEscape="true" 
																		code="Label.PARLIAMENTCONSTITUENCY"></spring:message>
																</label>
														     </div>
														<div class="td column col1"><spring:bind path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].ismapupload">
																<input type="radio" id="parliamentupload"
																	name="<c:out value="${status.expression}"/>"
																	value="true"
																	<c:if test="${status.value == true}">checked</c:if>
																	onclick="ClearRd('parliamentseperateserver','parliamentbaseurl','parliamentupload','parliamentbaseurl_error');" htmlEscape="true" />
																	
														
														</spring:bind>
														
														<label> <spring:message htmlEscape="true" 
																	code="Label.UPLOADMAP"></spring:message>
														</label>
														</div>
														<div class="td column col1">
														<spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].ismapupload">
																<input type="radio" id="parliamentseperateserver"
																	name="<c:out value="${status.expression}"/>"
																	value="false"
																	<c:if test="${status.value == false}">checked</c:if>
																	onclick="ClearRd('parliamentseperateserver','parliamentbaseurl','parliamentupload','parliamentbaseurl_error');" htmlEscape="true" />
															</spring:bind><label> <spring:message htmlEscape="true" 
																	code="Label.SEPERATEMAPSERVER"></spring:message>
														</label></div>
														<div class="td column col1">
														<label id="parliamentbaseurl" style="visibility:hidden;">
														<%-- <spring:message htmlEscape="true" 
																	code="Label.SEPERATEMAPSERVER"></spring:message> --%>
														
														<spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].baseUrl">
																<input type="text" id="parliamentbaseurlt"
																	class="frmfield"
																	maxlength="100"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"></c:out>"  htmlEscape="true"/>
																
																
															</spring:bind> 
															</label>
															
															
															
															<spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].constituencyType">
																<input type="hidden" class="frmfield"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true"/>
															</spring:bind> <spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].configurationMapConstituencyCode">
																<input type="hidden" class="frmfield"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
															</spring:bind></div>
															<div class="td column col1"><div class="errormsg" id="parliamentbaseurl_error"></div>
															</div>
														
													</div>
													</c:if>
													<c:if test="${fn:containsIgnoreCase(listConfigurationMapConstituency.constituencyType,'A')}">
													<div class="td column col1">
															<%-- <c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}"> --%>
														
																<label> <spring:message htmlEscape="true" 
																		code="Label.ASSEMBLYCONSTITUENCY"></spring:message>
																</label>
														</div>
														<div class="td column col1"><spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].ismapupload">
																<input type="radio" id="assemblyupload"
																	name="<c:out value="${status.expression}"/>"
																	value="true"
																	<c:if test="${status.value== true}"> checked</c:if>
																	onclick="ClearRd('assemblyseperateserver','assemblybaseurl','assemblyupload','assemblybaseurl_error');" htmlEscape="true"/></spring:bind>
														<label> <spring:message htmlEscape="true" 
																	code="Label.UPLOADMAP"></spring:message>
														</label></div>
														<div class="td column col1"><spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].ismapupload">
																<input type="radio" id="assemblyseperateserver"
																	name="<c:out value="${status.expression}"/>"
																	value="false"
																	<c:if test="${status.value == false}">checked</c:if>
																	onclick="ClearRd('assemblyseperateserver','assemblybaseurl','assemblyupload','assemblybaseurl_error');" htmlEscape="true" />
															</spring:bind><label> <spring:message htmlEscape="true" 
																	code="Label.SEPERATEMAPSERVER"></spring:message>
														</label></div>
														<div class="td column col1">
														<label id="assemblybaseurl" style="visibility:hidden;">
														<%-- <spring:message htmlEscape="true" 
																	code="Label.SEPERATEMAPSERVER"></spring:message> --%>
														<spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].baseUrl">
																<input type="text" id="assemblybaseurlt" class="frmfield"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"></c:out>"   htmlEscape="true" />
																	
														</spring:bind>
													
															</label> 
															
															<spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].constituencyType">
																<input type="hidden" class="frmfield"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
															</spring:bind> <spring:bind
																path="configureMapForm.listConfigurationMapConstituency[${listConstituencyDetailsRow.index}].configurationMapConstituencyCode">
																<input type="hidden" class="frmfield"
																	name="<c:out value="${status.expression}"/>"
																	value="<c:out value="${status.value}" escapeXml="true"></c:out>" htmlEscape="true" />
															</spring:bind> </div>
														<div class="td column col1"><div class="errormsg" id="assemblybaseurl_error"></div></div>
														
													<!-- </tr> -->
													</c:if>
												</c:forEach>
											
											</div>
										</div>

										
											<!--<table width="100%" class="tbl_no_brdr">
												 <tr>
													<td> -->
												<div class="btnpnl">
				      <ul class="listing">
				      <li>	
													<label> <input type="submit" id="savebtn"
															onclick="return validAll(true);"
															value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"
														 /> </label> 
														 <label>
															<input type="button" class="btn" name="Submit623"
															value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
															onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										</li></ul>					
															
															
												
												
												
												<!--</td> </tr>
											</table> -->
										</div>
									</div>
							</div>
						</div>
					<!-- </td>
				</tr>
			</table> -->
		
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	

</body>
</html>