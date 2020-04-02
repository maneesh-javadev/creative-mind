<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib_includes.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%-- <%
	java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
%> --%>
<%!String contextPath;%>

<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/consolidatedReport.js'></script>
<script type="text/javascript" language="Javascript">

function manageState1(url,stateid,lbtype,level,localbodycode)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('lbtype', lbtype, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	dwr.util.setValue('localbodycode', localbodycode, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function goBack()
{
	var hierarchy = document.getElementById("hierarchylevel").value;
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>";
	document.getElementById('form1').submit();
}


function PrintDiv() {

	
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("divData");
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

function displayMap (districtPanchyatCode) {
	openGISModal(districtPanchyatCode, 2, "P", null, null);
}
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
	             <c:if test="${!empty consolidateLBList }">
					<a id="showprint" href="#" class="pull-right"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0"onclick="PrintDiv();" /></a></td>
				</c:if>
           </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportLBRPT" id='form1'>
				<input type="hidden" name="hierarchylevel" id="hierarchylevel" value="<c:out value='${hierarchylevel}' escapeXml='true'></c:out>"/>
								
				<div class="box-body" id="divData">
					<h3 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}">
							<spring:message code="Label.DISTRICTLVLTRADLBIES" htmlEscape="true"></spring:message>&nbsp;						
							<spring:message code="Label.OF" htmlEscape="true"></spring:message>&nbsp;<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
						</c:if> 
						<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
							<spring:message code="Label.DISTRICTLVLTRADLB"	htmlEscape="true"></spring:message>&nbsp;
							<spring:message	code="Label.OF" htmlEscape="true"></spring:message>&nbsp;<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
						</c:if>
					</h3>
				
				<div class="">*N.A.- NotApplicable</div><br />
				<div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="printble" >
				  <thead>
					<tr>
						<th><spring:message code="Label.SRNO" htmlEscape="true"></spring:message></th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.LOCALGOVTBODYNAME" htmlEscape="true"></spring:message></c:if> 
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.DISTRICTLVLTRADLB" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.LOCALBODYNAMEINLOCAL" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.DISTRICTLVLTRADLOCAL" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><spring:message code="Label.LOCALBODYTYPE" htmlEscape="true"></spring:message></th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.NO.INTERTRADLBRPT" htmlEscape="true"></spring:message></c:if> 
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.NO.INTERTRADLBRPT" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'R')}"><spring:message code="Label.NO.VILLAGETRADLBRPT" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.NO.VILLAGETRADLBRPT" htmlEscape="true"></spring:message></c:if>
						</th>
						<th width="6%" align="center">Map</th>
					</tr>
				 </thead>
					<tbody>
					<c:if test="${! empty consolidateLBList}">
						<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
							<tr class="tblRowB">
								<td width="3%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
								<td width="15%" align="left"><c:out value="${panchaytSetUp.localbodyNameEnglish}" escapeXml="true"></c:out></td>
								<td width="15%" align="left"><c:out value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out></td>
								<td width="15%" align="left"><c:out value="${panchaytSetUp.localbodyTypeName}" escapeXml="true"></c:out></td>
								<td width="15%" align="right">
									<c:choose>
										<c:when test="${panchaytSetUp.childCount == 0}"><c:out value="N.A."></c:out></c:when>
										<c:otherwise>
											<a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyIstLevel.do',${consolidateReportLBRPT.state_code},'${consolidateReportLBRPT.lbtype}','I',${panchaytSetUp.localBodyCode});"><c:out value="${panchaytSetUp.childCount}" escapeXml="true"></c:out></a>
							            </c:otherwise>
									</c:choose>
							   </td>
								
								<td width="15%" align="right">
									<c:choose>
										<c:when test="${panchaytSetUp.grandChildCount == 0}"><c:out value="N.A."></c:out></c:when>
										<c:when test="${panchaytSetUp.childCount == 0}"> 	
								 	 		 <a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyIstLevel.do',${consolidateReportLBRPT.state_code},'${consolidateReportLBRPT.lbtype}','V',${panchaytSetUp.localBodyCode});"><c:out value="${panchaytSetUp.grandChildCount}" escapeXml="true"></c:out></a>  
								 	 	</c:when>	
								 	 	<c:when test="${panchaytSetUp.childCount != 0}"><c:out value="${panchaytSetUp.grandChildCount}" escapeXml="true"></c:out></c:when> 
									</c:choose>
								</td>
								
								
								<td align="center" width="7%">
								<c:if test="${panchaytSetUp.childCount gt 0}">
								<a href="#" onclick="javascript:displayMap('${panchaytSetUp.localBodyCode}');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
									
								</c:if>
								</td>
							</tr>
						</c:forEach>
							<form:input path="stateId" type="hidden" name="stateId" id="stateId"  value="${panchaytSetUp.stateId}"/>
								<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
								<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
								<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" /> 
								<form:input path="captchaAnswer" type="hidden" value="0" /> 
								<form:input path="captchaAnswers" type="hidden" value="0" /> 
								<form:input path="lbLevel" type="hidden" value="0" id="lbLevel"/> 
					
							</c:if>
					
					<tr>
						<td width="4%"></td>
						<td width="46%" align="left" colspan="2"><spring:message htmlEscape="true" code="Label.TOTALS"></spring:message></td>
						<td width="20%"></td>
						<td width="23%" align="right"><c:out value="${consolidateReportLBRPT.ip_count}" escapeXml="true"></c:out></td>
						<td width="8%" align="right"><c:out value="${consolidateReportLBRPT.vp_count}" escapeXml="true"></c:out></td>
						<c:if test="${consolidateReportLBRPT.state_code == 16}">
							<td width="15%" align="right"><c:out value="${vcCount}" escapeXml="true"></c:out></td>
						</c:if>
					</tr>
				</tbody>
			</table>
			</div>

					<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%></label>
						</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
						<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%> </label>
						</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
					</div>
					<div id="footer"></div>
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
