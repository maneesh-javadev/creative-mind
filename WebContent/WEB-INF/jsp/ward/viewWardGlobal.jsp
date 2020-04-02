<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
 
<script src="js/lgd_localbody.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" src="js/viewwards.js"></script>

<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />


<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService .js'></script>

<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script language="javascript">
function CallPrint()
{ 
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("divData"); 
	//document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0'); 
	//alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML); 
	Win4Print.document.close(); 
	Win4Print.focus(); 
	Win4Print.print(); 
	Win4Print.close(); 
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
}

function setBack()
{
	document.getElementById('divData').style.display = 'none';
	document.getElementById('divData').style.visibility = 'hidden';
	document.getElementById('viewentity').style.display = 'block';
	document.getElementById('viewentity').style.visibility = 'visible';
	document.getElementById('showhelp').style.display = 'block';
	document.getElementById('showhelp').style.visibility = 'visible';
	document.getElementById('showprint').style.display = 'none';
	document.getElementById('showprint').style.visibility = 'hidden';
	document.getElementById('showbutton').style.display = 'none';
	document.getElementById('showbutton').style.visibility = 'hidden';
}
function ClearAll()
{
	 var obj='<c:out value="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}" escapeXml="true"></c:out>';
	 //alert(obj);
	 if(obj=="true")
		 {
		 
		 	document.getElementById('viewentity').style.display = 'none';
			document.getElementById('viewentity').style.visibility = 'hidden';
		 	document.getElementById('showhelp').style.display = 'none';
			document.getElementById('showhelp').style.visibility = 'hidden';
			document.getElementById('showprint').style.display = 'block';
			document.getElementById('showprint').style.visibility = 'visible';
			document.getElementById('showbutton').style.display = 'block';
			document.getElementById('showbutton').style.visibility = 'visible';
		 }
	 else
		 {
			 document.getElementById('viewentity').style.display = 'block';
			document.getElementById('viewentity').style.visibility = 'visible';
		 	document.getElementById('showhelp').style.display = 'block';
			document.getElementById('showhelp').style.visibility = 'visible';
			document.getElementById('showprint').style.display = 'none';
			document.getElementById('showprint').style.visibility = 'hidden';
			document.getElementById('showbutton').style.display = 'none';
			document.getElementById('showbutton').style.visibility = 'hidden';
			
		 }
}


  if ( window.addEventListener ) { 
	     window.addEventListener( "load",ClearAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", ClearAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = ClearAll;
	  }
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body onload="document.getElementById('state').selectedIndex=0;">

	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.VIEWWARD" htmlEscape="true"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Title" border="0" /></a></li>
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message> </a></li> --%>
			</ul>
			<a id="showprint" style="visibility: hidden; display: none;" href="#">
				<img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
			</a>
		</div>
		
		<div class="frmpnlbrdr">
		
			<form:form action="viewWard.do" method="POST"
				commandName="localGovtBodyForm"
				enctype="multipart/form-data"
				id="viewWardForm">

				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="createWardforPRI.htm"/>" />

			

				<div id="cat">
					<div class="frmpnlbg" id="viewentity">
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message></div>
								
								<div class="search_criteria"> <!-- Seacrh criteria starts -->
									<div class="block">
										<ul class="blocklist">
											<li>
												<label for="state"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mndt">*</span></label>														
												<form:select path="stateNameEnglish"
															class="combofield" style="width: 175px" id="state"
															onchange="getLocalBodyList(this.value);">
															<form:option value="">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:options items="${stateList}"
																itemValue="statePK.stateCode"
																itemLabel="stateNameEnglish"></form:options>
												</form:select>
												<div id="errSelectState" class="errormsg"></div>													
											</li>
											<li>
												<input type="hidden" id="cattype" />
												<input type="hidden" id="level" />
												<input type="hidden" name="lblc" id="lblc" />
												<label for="ddLgdLBType"><spring:message	code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span class="mndt">*</span></label> 
													<form:select
													path="lgd_LBTypeName" id="ddLgdLBType" htmlEscape="true"
													onfocus="validateOnFocus('ddLgdLBType');helpMessage(this,'ddLgdLBTypeMsg');"
													onchange="hideShowDivforWard(this.value,'${districtCode}','${lbType}');"
													onkeydown="validateOnKeyPessDown(event,'ddLgdLBType','char')"
													onblur="vlidateOnblur('ddLgdLBType','1','15','c');hideHelp();"
													class="combofield" style="width: 175px">
													<form:option selected="selected" value=""	label="--select--" />
												</form:select>	
												<div id="errSelectLocalBodyType" class="errormsg"></div>
											</li>
											<li>											
												<div id="tr_district2" style="visibility: hidden; display: none;">													
													<label for="wardUrbanLocalBody" id="urbanlevel"><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></label>
													<form:select htmlEscape="true"
														path="lb_lgdPanchayatName" class="combofield"
														id="wardUrbanLocalBody" style="width: 175px"
														onfocus="validateOnFocus('wardUrbanLocalBody');helpMessage(this,'wardUrbanLocalBodyMsg');" 
														onchange="remove_error(4);getCovereSubDistUrbanExWardList(this.value)"
														onkeydown="validateOnKeyPessDown(event,'wardUrbanLocalBody','char')"
														onblur="vlidateOnblur('wardUrbanLocalBody','1','15','c');hideHelp();">
														<form:option value="0">
															<spring:message code="Label.SELECTLOCALBODY"></spring:message>
														</form:option>
													</form:select>
													<div id="errSelectUrban" class="errormsg" style="visibility: hidden; display: none;"></div>														
													<div id="wardUrbanLocalBody_error" class="errormsg"><spring:message code="error.blank.TEHSILP" htmlEscape="true"></spring:message></div>
													<div id="wardUrbanLocalBodyMsg" style="display:none"><spring:message code="error.blank.TEHSILP" htmlEscape="true"/></div>
													<div class="errormsg" id="wardUrbanLocalBody_error1"><form:errors path="lb_lgdPanchayatName" htmlEscape="true"/></div>  
													<div class="errormsg" id="wardUrbanLocalBody_error2" style="display: none" ></div>
												</div>
											</li>
											<li>
												<div id="tr_village_dist" style="visibility: hidden; display: none;">
													<label for="ddLgdLBDistListAtVillageCA" id="firstlevel"><spring:message code="Label.SELECT" htmlEscape="true"><span class="mndt">*</span></spring:message></label>
														<form:select
															path="lgd_LBDistListAtVillageCA" class="combofield" htmlEscape="true"
															style="width: 175px" id="ddLgdLBDistListAtVillageCA"
															onfocus="validateOnFocus('ddLgdLBDistListAtVillageCA');helpMessage(this,'ddLgdLBDistListAtVillageCAMsg');"
															onchange="remove_error(3);callList(this.value);"
															onkeydown="validateOnKeyPessDown(event,'ddLgdLBDistListAtVillageCA','char')"
															onblur="vlidateOnblur('ddLgdLBDistListAtVillageCA','1','15','c');hideHelp();">
															<form:option selected="selected" value=""
																label="--select--" />
		
															<form:options items="${districtPanchayatList}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select>
														<div id="errSelectVIP" class="errormsg" style="visibility: hidden; display: none;"></div>
													    <div id="ddLgdLBDistListAtVillageCA_error" class="errormsg"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
														<div id="ddLgdLBDistListAtVillageCAMsg" style="display:none"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"/></div>
														<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error1"><form:errors path="lgd_LBDistListAtVillageCA" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error2" style="display: none" ></div>
												</div>
											</li>
											<li>
												<div id="tr_village_inter" style="visibility: hidden; display: none;">
														<label for="ddLgdLBInterListAtVillageCA" id="secondlevel"><spring:message	code="Label.SELECT" htmlEscape="true"><span class="mndt">*</span></spring:message></label>
														<form:select
															path="lgd_LBInterListAtVillageCA" class="combofield"
															id="ddLgdLBInterListAtVillageCA" htmlEscape="true"
															style="width: 175px"
															onfocus="validateOnFocus('ddLgdLBInterListAtVillageCA');helpMessage(this,'ddLgdLBInterListAtVillageCAMsg');"
															onchange="remove_error(3);getVillagePanchWardbyIntercode(this.value);"
															onkeydown="validateOnKeyPessDown(event,'ddLgdLBInterListAtVillageCA','char')"
															onblur="vlidateOnblur('ddLgdLBInterListAtVillageCA','1','15','c');hideHelp();">

															<form:option selected="selected" value=""
																label="--select--" />
														</form:select>
														<div id="errSelectVDP" class="errormsg" style="visibility: hidden; display: none; "></div>
													 	<div id="ddLgdLBInterListAtVillageCA_error" class="errormsg"><spring:message code="error.blank.INTERMEDIATEP" htmlEscape="true"></spring:message></div>
														<div id="ddLgdLBInterListAtVillageCAMsg" style="display:none"><spring:message code="error.blank.INTERMEDIATEP" htmlEscape="true"/></div>
														<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error1"><form:errors path="lgd_LBInterListAtVillageCA" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error2" style="display: none" ></div>
													</div>
											</li>
											<li>
												<div id="tr_village_pan" style="visibility: hidden; display: none;">
														<label for="ddLgdLBVillageSourceAtVillageCA" id="thirdlevel"><spring:message	code="Label.SELECT" htmlEscape="true"><span class="mndt">*</span></spring:message></label>
														<form:select
															path="lgd_LBVillageSourceAtVillageCA"
															class="combofield" style="width: 175px" htmlEscape="true"
															id="ddLgdLBVillageSourceAtVillageCA"
															onchange="remove_error();getLGBforCoveredVillageListExWard(this.value);"
															onfocus="validateOnFocus('ddLgdLBVillageSourceAtVillageCA');helpMessage(this,'ddLgdLBVillageSourceAtVillageCAMsg');"																				
															onkeydown="validateOnKeyPessDown(event,'ddLgdLBVillageSourceAtVillageCA','char')"
															onblur="vlidateOnblur('ddLgdLBVillageSourceAtVillageCA','1','15','c');hideHelp();">
		
															<form:option selected="selected" value=""
																label="--select--" />
		
														</form:select>
														<div id="errSelectVVP" class="errormsg" style="visibility: hidden; display: none;"></div>																			
					 									<div id="ddLgdLBVillageSourceAtVillageCA_error" class="errormsg"><spring:message code="error.blank.VILLAGEP" htmlEscape="true"></spring:message></div>
														<div id="ddLgdLBVillageSourceAtVillageCAMsg" style="display:none"><spring:message code="error.blank.VILLAGEP" htmlEscape="true"/></div>
														<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error1"><form:errors path="lgd_LBVillageSourceAtVillageCA" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error2" style="display: none" ></div>													
											   </div>
											</li>											
										</ul>
									</div>
									<div class="block">
										<ul class="captcha_fields">
											<li>
												<img src="captchaImage" alt="Captcha Image" />
											</li>
											<li>
												<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
												<form:input path="captchaAnswer" name="captchaAnswer" id="captchaAnswer" />
												<c:if test="${captchaSuccess1 == false}">
													<div class="errormsg"><spring:message code="captcha.errorMessage" 
														htmlEscape="true" /></div>
												</c:if>
												<form:errors path="captchaAnswer" />
												<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
											</li>
											<li class="buttons">
												<input type="submit" name="get" id="btnGet" class="btn" value="<spring:message code='Button.GET' htmlEscape='true'></spring:message>" onclick="return validatePRIWardAll();" />												
												<input type="button" class="btn" name="Close" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"	id="btnCancel"  onclick="javascript:go('welcome.do');" />
											</li>
										</ul>
									</div>
									<div class="block"> <!-- This DIV contains hidden elements -->
										<input type="hidden" name="stateCode" value="<c:out value='${stateCode}'/>"/>
										<input type="hidden" name="districtCode" value="<c:out value='${districtCode}'/>"/>
										<input type="hidden" value="<c:out value='${lbType}'/>"/>
									</div>
									
									<div class="clear"></div>
									
								</div> 	<!-- Seacrh criteria ends -->			
							</div>
						</div>
								
					
							<c:if test="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}">		
								<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
				   			
								<h6 class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><c:out value="${message}" escapeXml="true"></c:out></h6>
								<table width="100%" class="tbl_no_brdr" align="center">
								<tr>
								<td>
										<table class="tbl_with_brdr" width="100%" align="center">
										<tr class="tblRowTitle tblclear">
											<td width="6%" ><spring:message
													htmlEscape="true" code="Label.SNO"></spring:message>
											</td>
											<td width="16%"><spring:message
													htmlEscape="true" code="Label.WARDNUMBER"></spring:message>
											</td>
											<td width="21%" ><spring:message
													htmlEscape="true" code="Label.WARDNAMEINENG"></spring:message>
											</td>
											
	
											<tr><td></td></tr>
	
										<c:forEach var="listWardDetail"
											varStatus="listWardRow"
											items="${SEARCH_SUBDISTRICT_RESULTS_KEY}">
											 <tr class="tblRowB"> 
										
												 <td width="6%"> <c:out value="${listWardRow.index+1}" escapeXml="true"></c:out></td> 
												<%-- <td width="5%" >${listWardRow.index+1}</td> --%>
												<td width="16%"><c:out value="${listWardDetail.wardNumber}" escapeXml="true"></c:out>
												</td>
												<td width="21%"><c:out
														value="${listWardDetail.wardNameEnglish}" escapeXml="true"></c:out>
												</td>
												
											</tr>
										</c:forEach>
	
									</tr>
										</table> 
									</td>
									</tr>
							</table>
							
						<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
								<li>
									<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
									<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
								</li>
								<li>
									<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
								    <label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>								
						 			<%=df.format(new java.util.Date())%>  </label>
								</li>
								<li>
									<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
								</li>
							</ul>
						
								<div id="footer" style="visibility: hidden; display: none;">
						 				<p id="footertext" style="text-align: center">
													Site is designed, hosted and maintained by <a
														target="_blank" href="http://www.nic.in/">National
														Informatics Centre</a><br> Contents on this website is
														owned,updated and managed by the <a target="_blank"
														href="http://www.panchayat.gov.in/" target="_blank">Ministry of
															Panchayati Raj</a>, Government of India <br> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
												</p>  
				     						 <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading..."  /></div>
						    
						    			</div> 
								</div>
							</div>
								
								</c:if>
								
								<div class="buttons center" id="showbutton">
									<input type="button" name="Back" class="btn" value="<spring:message htmlEscape="true" code="Button.BACK"></spring:message>" onclick="setBack();" />
									<input type="button" class="btn" name="Close" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel"  onclick="javascript:go('welcome.do');" />											
								</div>
							
								<c:if test="${wardsize==0}">								
									<p class="center errormsg"><c:out value="${listnull}" escapeXml="true"></c:out></p>								
								</c:if>
						</div>
						</form:form>
					</div>
				</div>
			
			<script src="/LGD/JavaScriptServlet"></script>
		


</body>
</html>