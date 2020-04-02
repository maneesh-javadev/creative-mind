<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>
 <link rel="stylesheet" href="js/pdf/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="js/pdf/css/buttons.dataTables.min.css"> 
<link rel="stylesheet" href="js/pdf/css/customcss/consoldateLendregion.css"> 

 
<script type="text/javascript" src="js/pdf/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/pdf/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/pdf/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/pdf/jszip.min.js"></script>
<script type="text/javascript" src="js/pdf/pdfmake.min.js"></script>
<script type="text/javascript" src="js/pdf/vfs_fonts.js"></script> 
<script type="text/javascript" src="js/pdf/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/pdf/buttons.print.min.js"></script>  
  
<script type="text/javascript" src="js/common.js"></script>


 
<%!String sessionId;%>
<%sessionId = request.getSession().getId();%>




<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
$(document).ready(function() {
    $('#example').DataTable( {
    	
    	"bJQueryUI": true,
		"aoColumns":[null,null,null,null,null,{"bSortable": false}, null,null],
		"sPaginationType": "full_numbers",
    	searching: false,
   		paging: false,
    	ordering: false,
        info:     false,
    	dom: 'Bfrtip',
        buttons: [
             'csv', 'excel', 'pdf'
        ]
    } );
} );

function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 


function manageState1(url,stateid)
{
	$("#stateId").val(stateid);
	//dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	//dwr.util.setValue('statetype', type, { escapeHtml : false	});
	//dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function manageState2(url,stateid,type)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	//dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState3(url,stateid,type)
{
	
	 dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	}); 
	//dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
/* function manageState4(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 } */

function getData() 
{
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		/* Old Captcha code */
		/*var capchaImgVal = document.getElementById('jcaptcha_response').value;
		 lgdDwrCaptchaService
				.validateCaptchaCode(
						'form1',
						sessionId,
						capchaImgVal,
						{
							callback : function(data) {
								var result = data;
								if ("success" == result) {
									document.getElementById('form1').method = "post";
									document.getElementById('form1').action = "rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>";
									document.getElementById('form1').submit();
									return true;
								} else {
									captchaResetImage('theimage',
											'jcaptcha_response');
									errorCaptcha.innerHTML = "<b>" + result
											+ "</b>";
									return false;
								}
							}
						}); */
	/* New Captcha Code */
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		displayLoadingImage();
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "rptConsolidateforLandregions.do?<csrf:token uri='rptConsolidateforLandregions.do'/>";
		document.getElementById('form1').submit();
		return true;
}
 
 function PrintDiv() {

	 document.getElementById('footer').style.display = 'block';
		document.getElementById('footer').style.visibility = 'visible';		
		var printContent = document.getElementById("printable2");
		// document.getElementById('btn1').style.visibility = 'hidden';
		var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
		// alert("contect"+printContent.innerHTML);
		Win4Print.document.write(printContent.innerHTML);
		Win4Print.document.close();
		Win4Print.focus();
		Win4Print.print();
		Win4Print.close();
		document.getElementById('footer').style.display = 'none';
		document.getElementById('footer').style.visibility = 'hidden';
		
		return false;
	}
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message code="Label.CONSOLIDATEDRPTONLRENTITIES" htmlEscape="true"></spring:message>
			</h3>
			
			<c:if test="${empty consolidateLBList}">
				<ul class="listing">
					<!-- <li><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" alt="Toggle" />
					</a>
					</li> -->
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message
								htmlEscape="true" code="Label.CBT"></spring:message>
					</a>
					</li>
					<li><a href="#" class="frmhelp"><spring:message
								htmlEscape="true" code="Label.HELP"></spring:message>
					</a>
					</li> --%>
				</ul>
			</c:if>
			
			<c:if test="${!empty consolidateLBList}">
				<a id="showprint" href="#"><img
					src='<%=contextpthval%>/images/printer_icon.png' border="0"
					onclick="PrintDiv();" alt="Print" />
				</a>
			</c:if>
			
		</div>
		
		<div class="clear"></div>

		<div class="frmpnlbrdr">
			<div class="frmpnlbg">
				<form:form commandName="consolidateReportlandregion" id='form1' action="rptConsolidateforLandregions.do">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptConsolidateforLandregions.do"/>" />
					<form:hidden path="stateId"/>
										
					<c:if test="${empty consolidateLBList}">
						<div id="cat">
							<ul class="captcha_fields consolidate_report">
								<li><%@ include file="../common/captcha_integration.jspf"%></li>
								<li>
									<input type="button" value="<spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message>" onclick="getData();" />
									<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
								</li>
							</ul>
						</div>
					</c:if>
											
											
					<div id="printable2">
					<c:if test="${!empty consolidateLBList}">
						<ul class="blocklist">
							<li><label>*N.A.- Not Applicable</label></li>
						</ul>
					</c:if>
					<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
					<table class="tbl_no_brdr" width="100%">
						<tr>
							<td align="center" width="100%">
							<c:if test="${!empty consolidateLBList}">
										<br />
								<table  id="example" class="display nowrap" cellspacing="0" width="100%">
									<thead>
										<tr >
											<th><label><spring:message code="Label.SRNO" htmlEscape="true"></spring:message> </label></th>
											<th ><label><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></label></th>
											<th><label><spring:message code="Label.STATENAME" htmlEscape="true"></spring:message></label></th>
											<th><label><spring:message code="Label.CENSUS" htmlEscape="true"></spring:message> </label></th>
											<th><label><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label></th>
											<th><spring:message code="Label.NOOFDISTRICT" htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.NOOFSUBDISTRICT" htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.NOOFVILLAGES" htmlEscape="true"></spring:message></th>
										</tr>
									</thead>
									<tbody>
									<c:if test="${not empty consolidateLBList}">
										<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
											<tr >
												<td><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
												<td align="center"><c:out value="${panchaytSetUp.state_code}" escapeXml="true"></c:out></td>
												<td align="left"><c:out value="${panchaytSetUp.state_name_english}" escapeXml="true"></c:out></td>
												<td align="center"><c:out value="${panchaytSetUp.census_2001_code}" escapeXml="true"></c:out></td>
												<td align="center"><c:out value="${panchaytSetUp.census_2011_code}" escapeXml="true"></c:out></td>
												<td align="center"><c:choose>
													<c:when test="${panchaytSetUp.d_count == 0}">
														<c:out value="N.A."></c:out>
													</c:when>
													<c:otherwise>
														<a href="#" onclick="javascript:manageState1('rptConsolidateforDistrictLevel.do',${panchaytSetUp.state_code});">
															<u><c:out value="${panchaytSetUp.d_count}" escapeXml="true"></c:out></u>
														</a>
														

													</c:otherwise>
												</c:choose>
												</td>
												<td align="center">
													<c:choose>
														<c:when test="${panchaytSetUp.sd_count == 0}">
															<c:out value="N.A."></c:out>
														</c:when>
														<c:otherwise>
															<c:out value="${panchaytSetUp.sd_count}" escapeXml="true"></c:out>
														</c:otherwise>
													</c:choose>
												</td>
												<td align="center">
													<c:choose>
														<c:when test="${panchaytSetUp.v_count == 0}">
															<c:out value="N.A."></c:out>
														</c:when>
														<c:otherwise>
														<c:out value="${panchaytSetUp.v_count}" escapeXml="true"></c:out>
														</c:otherwise>
													</c:choose>
												</td>

													</tr>
											</c:forEach>
										</c:if>
										<tr >
											<td><spring:message htmlEscape="true" code="Label.TOTALS"></spring:message></td>
											<td align="left" >&nbsp;</td>
											<td align="left" >&nbsp;</td>
											<td align="left" >&nbsp;</td>
											<td align="left" >&nbsp;</td>
											<td align="center"><c:out value="${consolidateReportlandregion.totalDCount}" escapeXml="true"></c:out></td>
											<td align="center"><c:out value="${consolidateReportlandregion.totalsdCount}" escapeXml="true"></c:out></td>
											<td align="center"><c:out value="${consolidateReportlandregion.totalVCount}" escapeXml="true"></c:out></td>
										</tr> 
									</tbody>
							</table>
							</c:if>
							</div>
							</td>
							</tr>
						</table>
						
						<c:if test="${! empty consolidateLBList}">
							<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
								<li>
									<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
									<%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%>
									</label>
								</li>
								<li>
									<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
									<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
									<%=df.format(new java.util.Date())%> </label>
								</li>
								<li>
									<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
								</li>
							</ul>
						</c:if>
						
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
					</div> <!-- Printable Div ends here -->

					<c:if test="${! empty consolidateLBList}">
						<div class="buttons center">
							<input type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
									onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />	
						</div>
					</c:if>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
