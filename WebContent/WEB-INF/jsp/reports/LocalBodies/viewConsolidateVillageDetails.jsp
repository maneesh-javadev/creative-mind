<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%-- <%
	java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
%> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="Javascript">
function goBack()
{
	
	var hierarchy = document.getElementById("hierarchylevel").value;
	document.getElementById("statelevel").value="V";
	document.getElementById("lbLevel").value="3";
	document.getElementById('form1').method = "post";
	if(hierarchy==1)
	document.getElementById('form1').action = "rptConsolidateforPanbyLevel.do?<csrf:token uri='rptConsolidateforPanbyLevel.do'/>";
	else 
	document.getElementById('form1').action = "rptConsolidateforPanbyIstLevel.do?<csrf:token uri='rptConsolidateforPanbyIstLevel.do'/>";
	document.getElementById('form1').submit();
	
}


function goBackToIntermediate()
{
	var hierarchy = 3;
	document.getElementById("statelevel").value="I";
	document.getElementById("lbLevel").value="2";
	document.getElementById('form1').method = "post";
	if(hierarchy==1)
	document.getElementById('form1').action = "rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>";
	else if(hierarchy==3)
	document.getElementById('form1').action = "rptConsolidateforPanbyIstLevel.do?<csrf:token uri='rptConsolidateforPanbyIstLevel.do'/>";
	else if(hierarchy==4)
	document.getElementById('form1').action = "rptConsolidateforPanbyLevel.do?<csrf:token uri='rptConsolidateforPanbyLevel.do'/>";
	document.getElementById('form1').submit();
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

$(document).ready(function(){
	 
	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");
	 
	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");
	 
	});
	
</script>
</head>
<body>

<section class="content">
<!-- Main row -->
 <div class="row" id="frmcontent">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"></h3>
              <c:if test="${!empty consolidateLBList }"><a id="showprint" href="#" class="pull-right"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="PrintDiv();" /></a></c:if>
	       </div><!-- /.box-header -->
	       	<div class="clear"></div>
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportLBRPT" id='form1'>
				<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
				<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
				<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
				<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" />
				<form:input path="lbLevel" type="hidden" value="0" id="lbLevel"/>
				<%-- <form:input path="isVillageCouncilBack" type="hidden" name="isVillageCouncilBack" id="isVillageCouncilBack" /> --%> 
				<form:input path="parentCode" type="hidden"  />
				<form:input path="parentLevel" type="hidden"  />
				<input type="hidden" name="hierarchylevel" id="hierarchylevel" value="<c:out value='${hierarchylevel}' escapeXml='true'></c:out>" />	
				<form:input path="statetype" type="hidden" name="statetype" id="statetype" /> 	
				<form:input path="iPLbCode" type="hidden" name="iPLbCode" id="iPLbCode" /> 
				<form:input path="vPLbCode" type="hidden" name="vPLbCode" id="vPLbCode" /> 
				
				<div class="box-body" id="printable2">
				 <h3 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
				   <spring:message code="Label.LISTOFVILLAGESMAPPED" htmlEscape="true"></spring:message><c:out value="${parentLbDetail}" escapeXml="true"></c:out> 
			<!-- Temporarily Commented -->
			<%-- <c:if
					test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
					<label><spring:message code="Label.VILLAGEPANCHYATNME"
							htmlEscape="true"></spring:message> </label>&nbsp;<label><spring:message
							code="Label.OF" htmlEscape="true"></spring:message>&nbsp;${consolidateReportLBRPT.state_name_english}

					</label>
				</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
					<label><spring:message code="Label.VILLAGELVLTRADLB"
							htmlEscape="true"></spring:message> </label>&nbsp;<label><spring:message
							code="Label.OF" htmlEscape="true"></spring:message>&nbsp;${consolidateReportLBRPT.state_name_english}

					</label>
				</c:if> --%>
		</h3>
				
				<div class="">*N.A.- NotApplicable</div>
				<c:if test="${!empty consolidateLBList}">** Due to periodic elections, data is dynamic in nature and keep on changing</c:if>
				<input type="hidden" id="sessionId" value='<%=sessionId%>'></input><br />
				
				<c:if test="${!empty consolidateLBList}">*** Local Government Directory is now mapped to Census 2011 village codes</c:if>
				<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
				<br />
				 <div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr" >
				  <thead>
					<tr>
						<th><spring:message code="Label.SRNO" htmlEscape="true"></spring:message></th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message> </label></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.CENSUSFORVILLAGE" htmlEscape="true"></spring:message></c:if> 
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.CENSUSFORVILLAGE" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.VILLAGENAME" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.VILLAGENAME" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.COVERAGEDTYPE" htmlEscape="true"></spring:message> </label></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.COVERAGEDTYPE" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.MAINVILLAGE" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.MAINVILLAGE" htmlEscape="true"></spring:message></c:if>
						</th>
					</tr>
				 </thead>
					<tbody>
					<c:if test="${! empty consolidateLBList}">
						<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
							<tr>
								<td width="4%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
								<td width="12%" align="center"><c:out value="${panchaytSetUp[0]}" escapeXml="true"></c:out></td>
								<td width="20%" align="center"><c:out value="${panchaytSetUp[1]}" escapeXml="true"></c:out></td>
								<td width="22%" align="left"><c:out value="${panchaytSetUp[2]}" escapeXml="true"></c:out></td>
								<td width="22%" align="left">
									<c:if test="${fn:containsIgnoreCase(panchaytSetUp[3],'F')}"><label><spring:message code="Label.FULL" htmlEscape="true"></spring:message> </label></c:if>
									 <c:if test="${fn:containsIgnoreCase(panchaytSetUp[3],'P')}"><label> <spring:message code="Label.PART" htmlEscape="true"></spring:message> </label></c:if>
								</td>
								<td width="22%" align="center">
									<c:choose><c:when test="${panchaytSetUp[4] == 'true'}"><img src='<%=contextPath%>/images/right-icon5.gif' border="0" /></c:when>
										<c:otherwise><c:out value=""></c:out></c:otherwise>
									</c:choose>
								</td>
							</tr>
						  </c:forEach>
						<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
						<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
						<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
						<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" />
					</c:if>
						<tr></tr>
				</tbody>
			</table>
			</div>
			<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
				<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%></label>
				</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
				<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%> </label>
				</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
			</div>
			<div  id="footer"></div>
		</div>	
				
				
					<div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                      		<button type="button" name="Submit2" class="btn btn-info" onclick="goBack()"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i> <spring:message htmlEscape="true"  code="Button.BACK"></spring:message></button>
							<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					 </div>
				  </div>						
          	</form:form>
			</div>
		</section>
		</div>
	</div>
</section>
	
</body>
</html>
