<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib_includes.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>

<%!String contextPath;%>

<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>

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
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/consolidatedReport.js'></script>
<script type="text/javascript" language="Javascript">
$(document).ready(function() {
    $('#example').DataTable( {
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
function manageState1(url,district_code,stateid)
{
	//alert("hi");
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('district_code', district_code, {	escapeHtml : false	});
	//dwr.util.setValue('lbtype', lbtype, { escapeHtml : false	});
	//dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	//dwr.util.setValue('localbodycode', localbodycode, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
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
// add method for Back functionality----(by Sangita---21-04-2015)
function goBack()
{
	//alert("inside the back method");
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptBacktoParentConsolidateLandRegion.do?<csrf:token uri='rptBacktoParentConsolidateLandRegion.do'/>";
	document.getElementById('form1').submit();
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
              <c:if test="${!empty consolidateLBList }"><a id="showprint" class="pull-right" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="PrintDiv();" /></a></c:if>
           </div><!-- /.box-header -->
           
                <!-- form start -->
                <form:form class="form-horizontal" commandName="consolidateReportLBRPT" id='form1'>
				<form:input path="stateId" type="hidden" name="stateId" id="stateId" />
				<form:input path="district_code" type="hidden" name="district_code" id="district_code"  /> 
				<form:input path="parentlevel" type="hidden" name="parentlevel" id="parentlevel" value="S"/> 
						
			<div class="box-body" id="printable2">
				
				 <h3 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
						<%-- <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}"> --%>
						<%--<c:if test="${fn:containsIgnoreCase(localBodyType.category,'r') or fn:containsIgnoreCase(localBodyType.category,'p') or fn:containsIgnoreCase(localBodyType.category,'t')}">--%>
					<spring:message code="label.consolidate.landregion.district" arguments="${consolidateReportLBRPT.state_name_english}" argumentSeparator=","></spring:message>
						<%-- <<%-- /c:if>  --%><%-- <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}"> --%>
						<%-- <label><spring:message code="Label.DISTRICTLVLTRADLB" htmlEscape="true"></spring:message>&nbsp;<label><spring:message code="Label.OF" htmlEscape="true"></spring:message>&nbsp;${consolidateReportlb.state_name_english}</label>  --%><%-- </c:if> --%>
		   		</h3>
		   		<label><div class="">*N.A.- Not Applicable</div></label><br />
				<div class="table-responsive ">
				<table class="table table-striped table-bordered dataTable" id="example" cellspacing="0" width="100%">         	
					
				<thead>
					<tr>
						<th width="5%"><label><spring:message code="Label.SRNO" htmlEscape="true"></spring:message> </label></th>
                           <th width="10%" align="center"><label><spring:message code="Label.DISTRICTCODE"	htmlEscape="true"></spring:message> </label></th>
						<th width="22%" align="center"><label><spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message> </label></th>
						<th width="22%" align="center"><label><spring:message code="Label.DISTRICTNAMEINLOCAL" htmlEscape="true"></spring:message> </label></th>
						<th width="10%" ><label><spring:message code="Label.CENSUS" htmlEscape="true"></spring:message> </label></th>
						<th width="10%" ><label><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message> </label></th>
						<th width="10%" align="center"><label><spring:message code="Label.NOOFSUBDISTRICT" htmlEscape="true"></spring:message> </label></th>
						<th width="10%" align="center"><label> <spring:message code="Label.NOOFVILLAGES" htmlEscape="true"></spring:message> </label></th>
					</tr>
				</thead>
 				
 				<tbody>
					<c:if test="${! empty consolidateLBList}">
						<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
							<tr class="tblRowB">
								<td><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
								<td align="center"><c:out value="${panchaytSetUp.district_code}" escapeXml="true"></c:out></td>
								<td align="left"><c:out value="${panchaytSetUp.district_name_english}" escapeXml="true"></c:out></td>
								<td align="left"><c:out value="${panchaytSetUp.district_name_local}" escapeXml="true"></c:out></td>
								<td align="center"><c:out value="${panchaytSetUp.census_2001_code}" escapeXml="true"></c:out></td>
								<td align="center"><c:out value="${panchaytSetUp.census_2011_code}" escapeXml="true"></c:out></td>
								<td align="center">
									<c:choose>
										<c:when test="${panchaytSetUp.noofsubdistricts == 0}"><c:out value="N.A."></c:out></c:when>
											<c:otherwise>
											<%-- 	<a href="rptConsolidateforPanbyIstLevel.do?selstate=${consolidateReportLBRPT.state_code}&type=${consolidateReportLBRPT.lbtype}&level=I&parentLBCode=${panchaytSetUp.localBodyCode}&<csrf:token uri='rptConsolidateforPanbyIstLevel.do'/>">
													<c:out value="${panchaytSetUp.childCount}"></c:out> </a>
											 --%>		
										 		<a href="#" onclick="javascript:manageState1('rptConsolidateforSubDistrictLevel.do',${panchaytSetUp.district_code},${consolidateReportLBRPT.stateId});"><c:out value="${panchaytSetUp.noofsubdistricts}" escapeXml="true"></c:out></a>
											</c:otherwise>
									</c:choose>
								</td>
								<td align="center">
									<c:choose><c:when test="${panchaytSetUp.noofvillages == 0}"><c:out value="N.A."></c:out></c:when>
											 <c:otherwise>
												<%-- <a href="rptConsolidateforPanbyIstLevel.do?selstate=${consolidateReportLBRPT.state_code}&type=${consolidateReportLBRPT.lbtype}&level=V&parentLBCode=${panchaytSetUp.localBodyCode}&<csrf:token uri='rptConsolidateforPanbyIstLevel.do'/>"> --%>
												<c:out value="${panchaytSetUp.noofvillages}" escapeXml="true"></c:out>
											</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
								
						<%-- <form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
						<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" />  --%>
 								
							
					</c:if>
						<tr>
							<td><spring:message htmlEscape="true" code="Label.TOTALS"></spring:message></td>
							<td align="left" >&nbsp;</td>
							<td align="left" >&nbsp;</td>
							<td align="left" >&nbsp;</td>
							<td align="left" >&nbsp;</td>
							<td align="left" >&nbsp;</td>
							<td align="center"><c:out value="${consolidateReportLBRPT.sd_count}" escapeXml="true"></c:out></td>
							<td align="center"><c:out value="${consolidateReportLBRPT.v_count}" escapeXml="true"></c:out></td>
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
