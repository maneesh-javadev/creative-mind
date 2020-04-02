<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>

<script src="js/blockwisevillagesandulb.js"></script>
<title>

</title>
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
	 var obj='<c:out value="${! empty blockWiseEntityList}" escapeXml="true"></c:out>';
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
</head>

<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li>
				<%-- //these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message> </a></li>		 --%>		
			</ul>
			<a href="#" id="showprint" style="visibility: hidden; display: none;">
				<img src='<%=contextPath%>/images/printer_icon.png' border="0"	onclick="CallPrint()" onmouseover="" alt="Print" />
			</a>
		</div>
			
		<div class="frmpnlbrdr">
			<form:form action="globalviewblockwiseVillageandUlbforcitizen.do" id="form1" name="form1" method="POST"
				commandName="blockbean">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='globalviewblockwiseVillageandUlbforcitizen.do'/>" />
				<form:input path="globalvillageId" type="hidden" name="globalvillageId" id="globalvillageId" />
				<form:input path="globallocalbodyId" type="hidden" name="globallocalbodyId" id="globallocalbodyId" />
				
			<div id="cat">
				<div class="frmpnlbg" id='viewentity'>
					<div class="frmtxt">
						<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
						
						<div class="search_criteria"> <!-- Seacrh criteria starts -->
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mndt">*</span></label>
										<form:select htmlEscape="true" path="stateNameEnglish"
										class="combofield" style="width: 175px" id="ddSourceState"
										onchange="getDistrictList(this.value);document.getElementById('ddSourceState').selectedIndex==0?doRefresh(true):false;">
										<form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
										</form:option>
										<form:options items="${stateSourceList}"
											itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select> 
										<div class="errormsg" id="ddSourceState_error" style=" visibility: hidden; display:none">Error.STATE<br/></div>
									</li>
									<li>
										<label for="ddSourceDistrict"><spring:message	htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mndt">*</span></label>
										<form:select htmlEscape="true"
										path="districtNameEnglish" class="combofield"
										style="width: 175px" id="ddSourceDistrict"
										onchange="getBlockList(this.value);document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
										</form:select>
										<div class="errormsg" id="ddSourceDistrict_error" style=" visibility: hidden; display:none">Error.PSDT<br/></div>
									</li>
									<li>
										<label for="ddSourceblock"><spring:message htmlEscape="true" code="Label.SELBLOCK"></spring:message><span class="mndt">*</span></label>
										<form:select htmlEscape="true"
										path="blockNameEnglish" class="combofield"
										style="width: 175px" id="ddSourceblock">
										</form:select>
										<div class="errormsg" id="ddSourceblock_error" style=" visibility: hidden; display:none">Error.selectblock</div>
									</li>									
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>
									<li>
										<label for="captchaAnswers"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
										<form:input path="captchaAnswers" name="captchaAnswers" class="frmfield" autocomplete="off" />
										<div class="errormsg">
											<c:if test="${ captchaSuccess1 == false }">
												<spring:message code="captcha.errorMessage" htmlEscape="true" />
											</c:if>												
										</div>
										<div class="errormsg" id="errorCaptchas" style="visibility: hidden; display: none;"><spring:message code='captcha.errorMessage'/></div>
									</li>
									<li class="buttons">
										<input type="submit" name="Submit" class="btn" onclick="return getParentData();"value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message> />								
										<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onclick="javascript:go('globalviewblockwiseVillageandUlbforcitizen.do');" />
										<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						
						</div> <!-- Seacrh criteria ends -->						
					</div>
				</div>
				
				
				  <c:if test="${! empty blockWiseEntityList}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr" align="center">
							<tr>
							<th align="center" width="80%"> <label><c:out value="${message}" escapeXml="true"></c:out></label> </th>
							</tr>
							</table>
							<table width="100%" class="tbl_no_brdr">
							
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="15%"><spring:message htmlEscape="true"	code="Label.ENTITYTYPE"></spring:message></td>
												<td width="15%" ><spring:message htmlEscape="true"	code="Label.ENTITYCODE"></spring:message></td>
												<td width="20%"><spring:message htmlEscape="true" code="Label.ENTITYNAMEENGLISH"></spring:message></td>
												<td width="20%"><spring:message htmlEscape="true" code="Label.ENTITYNAMEINLOCAL"></spring:message></td>
												<td width="10%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></td>
												<td width="10%" align="center"><spring:message htmlEscape="true" code="Label.VIEW.GOVERNMENTORDER"></spring:message></td>
											</tr>
											<c:forEach var="listBlockWiseEntityDetail" varStatus="listEntityRow" items="${blockWiseEntityList}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${(listEntityRow.index+1)}" escapeXml="true"></c:out></td>
													<td><c:out value="${listBlockWiseEntityDetail.entityType}" escapeXml="true"></c:out></td>
													<td><c:out value="${listBlockWiseEntityDetail.entityCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listBlockWiseEntityDetail.entityNameEnglish}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${listBlockWiseEntityDetail.entityNameLocal}" escapeXml="true"></c:out></td>
													<td align="center"><a href="#"><img alt="View Details" src="images/view.png" onclick="javascript:viewEntityDetails('${listBlockWiseEntityDetail.entityCode}','${listBlockWiseEntityDetail.entityType}');" width="18" height="19" border="0" /></a></td> 
													<td align="center"><a href="#"><img alt="View Details" src="images/gvt.order.png" onclick="javascript:ViewEntityGovermentOrder('${listBlockWiseEntityDetail.entityCode}','${listBlockWiseEntityDetail.entityType}');" width="18" height="19" border="0" /></a></td> 
												</tr>
										  </c:forEach>	
								  	<!-- hidden value to view village or ULB -->
														
															<!-- hidden value to view village or ULB -->							
								</table>
								
							  </td>
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
					 
					 <div class="buttons center" id="showbutton" style="visibility: hidden; display: none;">
								<input type="button" name="Back" class="btn" value=<spring:message htmlEscape="true" code="Button.BACK"></spring:message> onclick="setBack();" /> 
								<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />								
				     </div>
					 
					 <c:if test="${size==0}">
						<p class="center mndt"><spring:message htmlEscape="true" code="Error.blockwiselist" text="Block Wise Entity List not Found"></spring:message></p>
					  </c:if>
					  
					 
			</div>				
					
			</form:form>
		</div>
			
	</div>
		
		
			
</body>
</html>