<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/datatable/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextPath%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
<script src="<%=contextPath%>/datatable/ZeroClipboard.js" charset="utf-8" type="text/javascript"></script>
<link href="<%=contextPath%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextPath%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<link href="<%=contextPath%>/datatable/TableTools.css" rel="stylesheet"  type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script src="js/common.js"></script>
<script type='text/javascript'>

function validation()
 {	
	var e = document.getElementById("ddSourceState");
	var strUser = e.options[e.selectedIndex].text;
	$('#abc').val(strUser.toString());
	
	var textVal = $("#ddSourceState").val();
	if (textVal == "") {
		
	$('#err_State_Name').html("State list is Required");
	return false;
	}
	
 }
 
 
 
function CallPrint()
{ 
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("print"); 
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
 
	
	 $(document).ready(function() {		
		$('#modifyAdminUnitLevel').dataTable({
			"bJQueryUI": true,
			"aoColumns": [null, null, null,
				  		  { "bSortable": false },
				  		  { "bSortable": false },
				  		  { "bSortable": false },
				  		  { "bSortable": false }
				  		 ],
				  		 
				  		"aButtons": [ "csv", "pdf","print"],
				  		"sSwfPath": "<%=contextPath%>/datatable/ZeroClipboard.swf",	  		
			"sPaginationType": "full_numbers"
		});
		var type= '<c:out value="${flag}" escapeXml="true"></c:out>';
		if (type == 1) {
			document.getElementById('selectTypeofLb').style.visibility = 'visible';
			document.getElementById('selectTypeofLb').style.display = 'block';
			document.getElementById('viewLbPesaDetail').style.visibility = 'hidden';
			document.getElementById('viewLbPesaDetail').style.display = 'none';
			document.getElementById('print').style.display = 'hidden';
			
			
		} 
		else if (type == 2) {
			document.getElementById('viewLbPesaDetail').style.visibility = 'visible';
			document.getElementById('viewLbPesaDetail').style.display = 'block';
			document.getElementById('selectTypeofLb').style.visibility = 'hidden';
			document.getElementById('selectTypeofLb').style.display = 'none';
			document.getElementById('showprint').style.display = 'block';
			document.getElementById('print').style.display = 'hidden';
		}
		
	} );
	 
	 
	 function test()
	 {
		 
		var sOptions = "dialogWidth:940px; dialogHeight:660px; dialogLeft: 300; dialogTop: 350; scroll:no; center:yes; resizable: no; status:no; edge:sunken;unadorned :yes";
		showModalDialog("PrintPesaReport.do",sOptions);
		 
	 }
		</script>

	</script>
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" text="List of PESA Panchayats" code="Label.ListofPESAPanchayats"></spring:message></h3>
			<a id="showprint" href="#" style="display: none;"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="test();" /></a>
		</div>
		
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
		<form:form method="post" commandName="localGovtBodyForm" id="localGovtBodyForm" onsubmit="" name="localGovtBodyForm">
					<div class="frmpnlbg" id='selectTypeofLb' align="" >
						<div class="frmtxt" align="">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
							</div>
							
							<div class="search_criteria" id='showbydropdown'>
							<form:input type="hidden" path="coordinates" id="abc" ></form:input>
								<div class="block">
									<ul class="blocklist">
										<li>
											<label for="ddSourceState"><spring:message	htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
											<form:select path="slc" class="combofield" style="width: 175px" id="ddSourceState"	onchange=""><form:option value="">
												<spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												<form:options items="${statesList}" itemValue="stateCode" itemLabel="stateNameEnglish"></form:options>
											</form:select><br/>
											<div id="err_State_Name" class="errormsg"></div>
										</li>
										<%-- <li>
											<label for="intermediatePanchyat"><spring:message	htmlEscape="true" code="Label.IntermediatePanchayat"></spring:message></label>
											<form:select path="parentLBCode" class="combofield" style="width: 175px" id="intermediatePanchyat"	onchange="intermediatepanch(this.value);">
											<form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
											</form:option>
											</form:select>
											<div id="errorintermediatepanchyat" class="errormsg"></div>
										</li> --%>
									</ul>
								</div>
								
								<div class="block">
									<ul class="captcha_fields">
										<li><img src="captchaImage" alt="Captcha Image" /></li>
										<li>
											<label><spring:message code="captcha.message" htmlEscape="true" /></label><br /> 
											<form:input path="captchaAnswer" name="captchaAnswer" id="captchaAnswer" onblur="clearMessage(this.value);" /> 
											<div id="errorCaptcha" class="errormsg"></div>
											<c:if test="${captchaSuccess == false}">
												<div class="errormsg"><spring:message code="captcha.errorMessage" htmlEscape="true" /></div>
											</c:if> 
											<div class="errormsg"><form:errors path="captchaAnswer" cssStyle="color:red" /></div>

											<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;">
												<spring:message code="captcha.errorMessage" />
											</div>
										</li>
										<li>
											<input type="submit" id="grampanchyatlist"	name="submmit" value="Get Data" onclick="return validation(this.value);"></input>
											<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');" ></input>
										</li>
									</ul>
								</div>
								
								<div class="clear"></div>
								
								</div>
							</div>
							
							
							<c:if test="${dataflag == 0}">

								<div class="errormsg" id="divData">
									<label><spring:message htmlEscape="true" code="error.no.rec.found.LBPesa" text="No PESA Panchayat Found"/>*</label>
								</div>

							</c:if>

						</div>	
							<!--   POST    -->

				<div id="viewLbPesaDetail"	style="visibility: hidden; display: none;">
					<c:if test="${! empty getLbPesa}">
						<div class="frmpnlbg" id="divData">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
								<spring:message htmlEscape="true" code="Label.PESAPanchayat"	text="List of PESA enabled Village Panchayats of " />
									<c:out value="${localGovtBodyForm.coordinates}" escapeXml="true"></c:out>
							</h6>							
							
								<table id="tblrows" width="100%" class="tbl_no_brdr">
									<tr>
										<td>
											<table cellpadding="0" cellspacing="0" border="0"
												class="display" id="modifyAdminUnitLevel">
												<thead>
													<tr class="tblRowTitle">
														<th align="center"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
														<th align="center"><spring:message code="Label.ZillaPanchayatname" text="Zilla Panchayat name"></spring:message></th>
														<th align="center"><spring:message code="Label.INTERMEDIATEPANCHAYATNAME" text="Intermediate Panchayat Name"></spring:message></th>
														<th align="center"><spring:message code="Label.LGDCodeofVillagePanchayat" text="LGD Code of Village Panchayat"></spring:message></th>
														<th align="center"><spring:message code="Label.NameofVillagePanchayat(In English)" text="Name of Village Panchayat (In English)"></spring:message></th>
														<th align="center"><spring:message code="Label.NameofVillagePanchayat(In Local language)" text="Name of Village Panchayat (In Local language)"></spring:message></th>
														<th align="center"><spring:message code="Label.PesaCoverageType" text="PESA Coverage Type"></spring:message></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${getLbPesa}">
														<tr class="tblRowB">
															<td align="center"><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.dpName}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.ipName}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.vpCode}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.vpNameEng}" escapeXml="true"></c:out></td>
															<td align="center"><c:out value="${adminEntityDetail.vpNameLoc}" escapeXml="true"></c:out></td>
															<td align="center">
																<c:choose>
																	<c:when test="${fn:contains(adminEntityDetail.coverageType,'P')}"><spring:message code="Label.PART" ></spring:message></c:when>
																	<c:otherwise><spring:message code="Label.FULL" ></spring:message></c:otherwise>
																</c:choose>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
							
							<ul class="blocklist center" style="text-align: center; list-style: none;"> <!-- Inline style only for Print purpose -->
										<li><label><spring:message code="Label.URL"
													htmlEscape="true"></spring:message> <%=request.getScheme() + "://"
									+ request.getServerName()
									+ request.getContextPath()%>
										</label></li>
										<li>
											<%
												java.text.DateFormat df = new java.text.SimpleDateFormat(
																		"dd/MM/yyyy hh:mm:ss a");
											%>
											<label><spring:message code="Label.REPORTPRINTED"
													htmlEscape="true"></spring:message> <%=df.format(new java.util.Date())%>
										</label></li>
										<li><label><spring:message
													code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message>
										</label></li>
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
									<div id="displayBox" style="text-align: center; display: none;">
										<img src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" alt="Loading..." />
									</div>

								</div> 
								
								<div class="buttons center">
									<input type="button" value="<spring:message htmlEscape="true"  code="Button.BACK"/>" onclick="javascript:location.href='listOfPesaPanchyat.do?<csrf:token uri='listOfPesaPanchyat.do'/>';" />
									<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');" ></input>
								</div>
						</div>
					</c:if>
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>