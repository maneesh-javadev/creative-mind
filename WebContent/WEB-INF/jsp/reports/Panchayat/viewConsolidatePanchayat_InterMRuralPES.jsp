<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib_includes.jsp"%>
<!-- Toooltip Code by Maneesh 13June2014 -->
<%@include file="../consolidateReportTooltip.jsp"%>
<!-- Toooltip Code by Maneesh 13June2014 -->
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/consolidatedReport.js'></script>
<script type="text/javascript" language="Javascript">

function manageState1(url,stateid,lbtype,level,localbodycode)
{
	dwr.util.setValue('statetype', 'P', {	escapeHtml : false	});
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('lbtype', lbtype, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	dwr.util.setValue('localbodycode', localbodycode, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url+"?<csrf:token uri='"+url+"'/>";
	document.getElementById('form1').submit();
 }

function goBack()
{
	//alert("inside the back method");
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptBacktoParentConsolidateReportPES.do?<csrf:token uri='rptBacktoParentConsolidateReportPES.do'/>";
	document.getElementById('form1').submit();
}

$(document).ready(function(){
	 
	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");
	 
	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");
	 
	});
	

         
function PrintDiv() {

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printdoc");
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

function displayMap (intermediatePanCode, stateCode) {
	openGISModal(intermediatePanCode, null, "P", "VP", stateCode);
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
              <c:if test="${!empty consolidateLBList }"><a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="PrintDiv();" /></a></c:if>
		 	</div><!-- /.box-header -->
			
			<div class="clear"></div>
               
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportLBRPT" id='form1'>
				<form:input path="statetype" type="hidden" name="statetype" id="statetype"  />
				<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
				<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
				<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
				<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" /> 
				<form:input path="parentCode" type="hidden"  />
				<form:input path="parentLevel" type="hidden"  />
								
				<div class="box-body" id="printdoc">
					<h2 id="statusmessage" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">									
						  	<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
							<c:if test="${!empty consolidateReportLBRPT.financialYear and consolidateReportLBRPT.financialYear ne ''}">
								<c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
							</c:if>
					   </c:if> 
					
					<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
						<spring:message code="Label.INTERLVLTRADLB" htmlEscape="true"></spring:message> >&nbsp; 
						<spring:message code="Label.OF" htmlEscape="true"></spring:message>&nbsp; 
						<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
						<c:if test="${!empty consolidateReportLBRPT.financialYear and consolidateReportLBRPT.financialYear ne ''}">
							<c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
						</c:if>
					</c:if>
					as available in other PES applications
	   			</h2>
				<div class="">*N.A.- Not Applicable</div>
				<c:if test="${!empty consolidateLBList}">** Due to periodic elections, data is dynamic in nature and keep on changing</c:if>
				<input type="hidden" id="sessionId" value='<%=sessionId%>'></input><br />
				<c:if test="${!empty consolidateLBList}">*** Local Government Directory is now mapped to Census 2011 village codes</c:if>
				<input type="hidden" id="sessionId" value='<%=sessionId%>'></input><br />
				<c:if test="${!empty consolidateLBList}">**** Click on the Localbody Name to view other related information</c:if>
				<input type="hidden" id="sessionId" value='<%=sessionId%>'></input><br />
				
				
				<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tbl_with_brdr">
				  <thead>
					<tr>
						<th><spring:message code="Label.SRNO" htmlEscape="true"></spring:message></th>
						<th style="text-align: right"><spring:message code="Label.LGDCODE" htmlEscape="true"></spring:message></th>
						<th style="text-align: right"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}"><spring:message code="Label.INTERMLBRPT" htmlEscape="true"></spring:message></c:if> 
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.INTERLVLTRADLB" htmlEscape="true"></spring:message></c:if>
						</th>
						<th><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}"><spring:message code="Label.INTERMLBRPTLOCAL" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.INTERLVLTRADLBLOCAL" htmlEscape="true"></spring:message></c:if>
						</th>
						<th style="text-align: right"><c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}"><spring:message code="Label.NO.VILLAGEPANRPT" htmlEscape="true"></spring:message></c:if>
							<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"><spring:message code="Label.NO.VILLAGETRADLBRPT" htmlEscape="true"></spring:message></c:if>
						</th>
						<!-- <th width="10%" align="center">Map</th> -->
					</tr>
				 </thead>
					<tbody>
						<c:if test="${! empty consolidateLBList}">
						<form:input path="financialYear" type="hidden"  />
							<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"	items="${consolidateLBList}">
							<c:set var="index" value="${panchaytSetUpRow.index +1}" />
								<c:set var="lbCode" value="${panchaytSetUp.localBodyCode}" />
								<c:set var="unitName" value="${panchaytSetUp.localbodyNameEnglish}" />
								<tr class="tblRowB">
									<td width="4%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
									<td width="15%" align="right"><c:out value="${panchaytSetUp.localBodyCode}" escapeXml="true"></c:out></td>
									<td width="15%" align="right"><c:out value="${panchaytSetUp.sscode}" escapeXml="true"></c:out></td>
									<td width="15%" align="left">
										<!-- Toooltip Code by Maneesh 13June2014 -->
										<a class="popModal_ex${index}" onclick="setTooltip('${lbCode}','popModal_ex${index}','${lbCode}','${unitName}','${typeName}','${stateCode}','<%=languageCode%>' )">
											<b> <label title="Click the Localbody Name to view Other Information of  ${unitName} ${typeName}"><c:out value="${unitName}" escapeXml="true"></c:out></label></b>
										</a>
											
										<div style="display:none">
											<div id="${lbCode}"></div>
										</div>
										<!-- Toooltip Code by Maneesh 13June2014 -->
										
									</td>
									<td width="15%" align="left"><c:out value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out></td>
									<td width="15%" align="right">
									  <c:choose><c:when test="${panchaytSetUp.childCount == 0}"><c:out value="N.A."></c:out></c:when>
										<c:otherwise>
											<%-- <a
												href="rptConsolidateforPanbyIstLevel.do?selstate=${consolidateReportLBRPT.state_code}&type=${consolidateReportLBRPT.lbtype}&level=V&parentLBCode=${panchaytSetUp.localBodyCode}&<csrf:token uri='rptConsolidateforPanbyIstLevel.do'/>">
												<c:out value="${panchaytSetUp.childCount}"></c:out> </a> --%>
												<%-- <a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyIstLevelRural.do',${consolidateReportLBRPT.state_code},'${consolidateReportLBRPT.lbtype}','V',${panchaytSetUp.localBodyCode});"><c:out value="${panchaytSetUp.childCount}"></c:out> </a> --%>
												<a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyLevelRuralPES.do',${consolidateReportLBRPT.state_code},'${consolidateReportLBRPT.lbtype}','V',${panchaytSetUp.localBodyCode});"><c:out value="${panchaytSetUp.childCount}" escapeXml="true"></c:out></a>	
										</c:otherwise>
									  </c:choose>
									</td>
									<%-- <td align="center" width="10%">
										<c:if test="${panchaytSetUp.childCount gt 0}">
											<a href="#" onclick="javascript:displayMap('${panchaytSetUp.localBodyCode}', '${consolidateReportLBRPT.state_code}');"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
											<img alt="Show Map" src="images/showMap.jpg" onclick="javascript:displayMap('${panchaytSetUp.localBodyCode}', '${consolidateReportLBRPT.state_code}');" width="18" height="19" border="0" />
										</c:if>
									</td> --%>
								</tr>
							</c:forEach>
						</c:if>
					<tr>
						<td></td>
							<td></td>
							<td width="4%"></td>
							<td width="46%" align="left" colspan="2"><spring:message htmlEscape="true" code="Label.TOTALS"></spring:message></td>
							<td width="23%" align="right"><c:out value="${consolidateReportLBRPT.vp_count}" escapeXml="true"></c:out></td>
							<!-- <td></td> -->
					</tr>
				</tbody>
			</table>
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
	
<c:if test="${consolidateReportLBRPT.otherInformationFlag eq true}">
	<%@include file="../../reports/otherPesAppReport.jsp"%>
</c:if>
</body>
</html>
