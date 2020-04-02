<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<script language="javascript">
function goBack()
{
	//alert("inside the back method");
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>";
	document.getElementById('form1').submit();
}
function PrintDiv() {

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printble");
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
  <form:form commandName="consolidateReportLBRPT" id='form1'>
  <form:input path="captchaAnswer" type="hidden" value="0" /> 
	 								<form:input path="captchaAnswers" type="hidden" value="0" /> 
	<div id="frmcontent">
			<div class="frmhd">
				<table width="100%" class="tbl_no_brdr">
									
					<tr>
										
						<td align="right">				
					<c:if test="${!empty consolidateLBList}">
							
					         <a href="#"> <img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="PrintDiv();" /></a>
							
				     </c:if>
					</td>
			                           
				</tr>
				</table>
				</div>
					
	<div class="clear"></div>
	
	<div class="frmpnlbrdr">
	
		<div id="printble">
		
		<table class="tbl_no_brdr" width="100%">
			<tr>
				<td width="100%">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="center" style="font-size: 24px;"><label><spring:message
											code="Label.ULB" htmlEscape="true"></spring:message>&nbsp;<label><spring:message
												code="Label.OF" htmlEscape="true"></spring:message>&nbsp;<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
	
									</label>
								</td>
	
							</tr>
						</table>
					</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="left" width="100%"><label>*N.A.- Not
						Applicable</label></td>
			</tr>
			<tr>
				<td align="center" width="100%">

					<table class="tbl_with_brdr" width="80%" align="center">
						<tr class="tblRowTitle tblclear">
							<td width="6%"><label><spring:message
										code="Label.SRNO" htmlEscape="true"></spring:message> </label></td>
							<td width="10%" align="left">
								<label>
									<spring:message code="Label.LGDCODE" text="LGD Code" htmlEscape="true"></spring:message>
								</label>
							</td>
							<td width="24%" align="center"><label><spring:message
										code="Label.URBANLBNAME" htmlEscape="true"></spring:message> </label>
							</td>
							<td width="24%" align="center"><label><spring:message
										code="Label.URBANLBNAMELOCAL" htmlEscape="true"></spring:message>
							</label></td>
							<td width="24%" align="center"></label> <spring:message
									code="Label.URBANLBTYPENAME" htmlEscape="true"></spring:message>
								</label>
							</td>

						</tr>



						<c:if test="${! empty consolidateLBList}">
							<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"
								items="${consolidateLBList}">
								<tr class="tblRowB">
									<td width="6%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
									<td width="10%" align="left"><c:out value="${panchaytSetUp.localBodyCode}" escapeXml="true"></c:out></td>
									<td width="24%" align="left"><c:out
											value="${panchaytSetUp.localbodyNameEnglish}" escapeXml="true"></c:out>
									</td>
									<td width="24%" align="left"><c:out
											value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out>
									</td>
									<td width="24%" align="left"><c:out
											value="${panchaytSetUp.localbodyTypeName}" escapeXml="true"></c:out>
									</td>

								</tr>
							</c:forEach>
						</c:if>
						<tr class="tblRowTitle tblclear">

						</tr>
					</table>
				</td>
			</tr>
			</table>
			<table class="tbl_no_brdr" width="100%">
			
			<tr>
					<td align="center"><label><spring:message
								code="Label.URL" htmlEscape="true"></spring:message>
				<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label></td>
				</tr>
										<tr>
										<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
									

					<td align="center"><label><spring:message
								code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>&nbsp;
								
						 <%=df.format(new java.util.Date())%>  </label></td>
				</tr>
				<tr>
					<td align="center"><label><spring:message
								code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message>
					</label></td>
				</tr>
				
				<tr>
				
				<td>
				 <div id="footer" style="visibility: hidden; display: none;">
						 
									            <table width="100%" class="tbl_no_brdr">
											        <tr>
											          <td colspan="4" class="fotbrdr" height="4"></td>
											        </tr>
											        <tr>
											          <td width="161" class="btmlogospace"><img src="images/e_governance_logo.jpg" title="LGD V7.17.04.2012" width="161" height="38" /></td>
											          <td width="93" class="btmlogospace"><img src="images/panchayatilogo.jpg" width="93" height="38" /></td>
											          <td align="right" class="btmlogospace">
											          Content on this website is updated and managed by the Ministry of Panchayati Raj. <br />
											          In case of any problem please send a mail at <a href="">lgdirectory at googlegroups dot com</a> &nbsp; <br />
													  Site hosted and maintained by National Informatics Centre (NIC).
													   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
													  </td> 
											          
											        </tr>
				     						 </table>
				     						 <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
						    
						    			</div> 
				</td>
				</tr>

			</table>
			</div>
			<table class="tbl_no_brdr" width="100%">
			<tr>
				<td align="center">
				<input type="button" name="Submit2"
							class="btn"
							value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>"
							 onclick="goBack()" /> 
							 
						<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
				</td>
			</tr>
			
		   </table>
		   </div>
		</div>
	</form:form>
</body>
</html>

					

