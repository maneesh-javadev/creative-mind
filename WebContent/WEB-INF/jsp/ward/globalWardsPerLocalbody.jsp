<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<% String contextpthval = request.getContextPath(); %>
<script type='text/javascript' src="<%=contextpthval%>/js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<link rel="stylesheet" href="<%=contextpthval %>/themes/base/jquery.ui.all.css"></link>
<link href="<%=contextpthval%>/external/jqueryCustom/css/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/TableTools.js" ></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery.dataTables.min.js"/>
<script src="<%=contextpthval%>/JavaScriptServlet"></script>
<script type="text/javascript"  charset="utf-8">
	$(document).ready(function() {
		$('#tblViewLBTWards').dataTable({
			"bProcessing": true,
			"bServerSide": true,
            "sAjaxSource": "fetchDataTableValues.do?<csrf:token uri='fetchDataTableValues.do'/>",
	        "sPaginationType": "full_numbers",
	        "bJQueryUI": true,
	        "fnServerParams": function ( aoData ) {
	            aoData.push( { "name": "state", "value": $('#state').val()},
	            			 { "name": "lbTypeCode", "value": $('#lbTypeCode').val()}
	                       );
	        },
            "fnRowCallback": function(nRow, aData, iDisplayIndex ) {
            	var lbCode = aData.localBodyCode;
            	var lblc = aData.lblc;
            	var tdcount = 4;
            	$('td:eq(0)', nRow).html("<span>"+aData.rowNum+"</span>").attr('align', 'center');
            	$('td:eq(1)', nRow).html("<a href='#' onclick=\"fetchWardsDetails('"+lbCode+"', '"+lblc+"')\">"+lbCode+"</a>").attr('align', 'left');
            	$('td:eq(2)', nRow).html("<a href='#' onclick=\"fetchWardsDetails('"+lbCode+"', '"+lblc+"')\">"+aData.localBodyNameEnglish+"</a>").attr('align', 'center');
            	$('td:eq(3)', nRow).html("<a href='#' onclick=\"fetchWardsDetails('"+lbCode+"','"+lblc+"')\">"+aData.localBodyNameLocal+"</a>").attr('align', 'center');
            	<c:if test="${showParent}">
            	$('td:eq(4)', nRow).html("<a href='#' onclick=\"fetchWardsDetails('"+lbCode+"','"+lblc+"')\">"+aData.parentName+"</a>" ).attr('align', 'center');
            	tdcount=5;
            	</c:if>
            	<c:if test="${showGrandParent}">
            	$('td:eq(5)', nRow).html( "<a href='#' onclick=\"fetchWardsDetails('"+lbCode+"','"+lblc+"')\">"+aData.grandparentName+"</a>" ).attr('align', 'center');	
            	tdcount = 6;
            	</c:if>
            	if(aData.wardCounts > 0){
            		$('td:eq('+tdcount+')', nRow).html( "<a href='#' onclick=\"fetchWardsDetails('"+lbCode+"','"+lblc+"')\">"+aData.wardCounts+"</a>" ).attr('align', 'center');	
            	}else{
            		$('td:eq('+tdcount+')', nRow).html( "<span>"+aData.wardCounts+"</span>").attr('align', 'center');
            	}
            	return nRow;
			},
			"aoColumns": [{ "mDataProp": "rowNum", "bSortable": false},
			              { "mDataProp": "localBodyCode", "bSortable": false},
			              { "mDataProp": "localBodyNameEnglish", "bSortable": false},
	                      { "mDataProp": "localBodyNameLocal", "bSortable": false},
	                      <c:if test="${showParent}">{ "mDataProp": "parentName", "bSortable": false},</c:if>
	                      <c:if test="${showGrandParent}">{ "mDataProp": "grandparentName", "bSortable": false},</c:if>
	                      { "mDataProp": "wardCounts", "bSortable": false}],
            "fnInitComplete": function(oSettings, json) {
            	$("#back").removeAttr('disabled');
            	$("#close").removeAttr('disabled');
            }
		});
	} );
	
	resetFrom = function(){
		document.forms['lbTypeWiseWards'].method = "GET"; 
		document.forms['lbTypeWiseWards'].action = "lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
	backForm = function(){
		document.forms['lbTypeWiseWards'].flow.value = "LB_WARD_DETAILS";
		document.forms['lbTypeWiseWards'].method = "POST"; 
		document.forms['lbTypeWiseWards'].action = "globalWardBack.do?<csrf:token uri='globalWardBack.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
	fetchWardsDetails = function(localBodyCode, lblcCode){
		document.forms['lbTypeWiseWards'].localBodyCode.value = localBodyCode;
		document.forms['lbTypeWiseWards'].lblcCode.value = lblcCode;
		document.forms['lbTypeWiseWards'].method = "post";
		document.forms['lbTypeWiseWards'].action = "showWardDetails.do?<csrf:token uri='showWardDetails.do'/>";
		document.forms['lbTypeWiseWards'].submit();
		return true; 
 	 };
</script>
</head>
<body>	
  <div id="frmcontent">
	<div class="frmhd">
		<h3 class="subtitle"><spring:message code="label.HeaderSummaryReportWard"/></h3>
		<ul id="showhelp" class="listing">
			<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a></li>
			<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>	
			<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message></a></li>		 --%>			
		</ul>
		<a id="showprint" style="visibility: hidden; display: none;" href="#">
			<img src='<%=contextpthval%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
		</a>	
		
	</div>
	
	<div class="frmpnlbrdr">
		<form:form id="lbTypeWiseWards" name="lbTypeWiseWards" commandName="lbTypeWiseWards">
			<input type="hidden" id="flow" name="flow"/>
			<input type="hidden" id="state" name="state" value="<c:out value="${state}" escapeXml="true"></c:out>"/>
			<input type="hidden" id="lbTypeCode" name="lbTypeCode" value="<c:out value="${lbTypeCode}" escapeXml="true"></c:out>"/>
			<input type="hidden" id="lblcCode" name="lblcCode"/>
			<input type="hidden" id="localBodyCode" name="localBodyCode"/>
			<div class="frmpnlbg" id='showbytext' >
					<div class="frmtxt" align="center" id ="resultLBType">
						<div class="frmhdtitle" >
							<spring:message code="label.LocalBodyWards" htmlEscape="true"/>
						</div>
						<table id="tblViewLBTWards" width="100%" >
							<thead>
								<tr style="height: 35px;">
									<th align="center" width="5%"><b><spring:message code="Label.SNO"/></b></th>
									<th width="10%" align="left"><b><spring:message code="Label.LGDCODE" text="LGD Code"/></b></th>
									<th align="center" width="20%"><b><spring:message code="Label.LOCALBODYNAMEINENG"/></b></th>
									<th align="center" width="20%"><b><spring:message code="Label.LOCALBODYNAMEINLOCAL"/></b></th>
									<c:if test="${showParent}">
									<th align="center" width="20%">
										<b>
											<c:choose>
												<c:when test="${!showGrandParent}"><spring:message code="Label.DLParent" text="District Level Parent Name"/></c:when>
												<c:otherwise><spring:message code="Label.ILParent" text="Intermediate Level Parent Name"/></c:otherwise>
											</c:choose>
										</b>
									</th>
									</c:if>
									<c:if test="${showGrandParent}">
									<th align="center" width="20%">
										<b>
											<spring:message code="Label.DLParent" text="District Level Parent"/>
										</b>
									</th>
									</c:if>
									<th align="center" width="15%"><b><spring:message code="label.TotalWards"/></b></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table> 
						
						<table width="100%">
							<tr>
								<td align="center">
									<input type="button" id="back" name="back" class="btn" style="width: 100px;" onclick="backForm();" value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>" disabled="disabled"/>
									<input type="button" id="close" name="close" class="btn" style="width: 100px;" onclick="javascript:window.location.href='welcome.do'" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" disabled="disabled" />
								</td>
							</tr>
						</table>
				</div>
			</div>	
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
 </div>
</body>
</html>