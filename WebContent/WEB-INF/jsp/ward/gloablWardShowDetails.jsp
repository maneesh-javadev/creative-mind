<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' src="<%=contextpthval%>/js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/TableTools.js" ></script>
<link href="<%=contextpthval%>/external/jqueryCustom/css/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<script src="<%=contextpthval%>/JavaScriptServlet"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#tblViewLBTWards').dataTable({
			"bJQueryUI": true,
			"aoColumns":[{"bSortable": false}, null, {"bSortable": false}, {"bSortable": false}],
			"sPaginationType": "full_numbers"
		});
	} );
	
	resetFrom = function(){
		displayLoadingImage();
		document.forms['lbTypeWiseWards'].method = "GET"; 
		document.forms['lbTypeWiseWards'].action = "lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
	backForm = function(){
		//displayLoadingImage();
		document.forms['lbTypeWiseWards'].flow.value = "WARD_DETAILS";
		document.forms['lbTypeWiseWards'].method = "POST"; 
		document.forms['lbTypeWiseWards'].action = "globalWardBack.do?<csrf:token uri='globalWardBack.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
</script>
</head>
<body>	
	<div class="frmhd">
		<div class="subtitle"><spring:message code="label.HeaderSummaryReportWard"/></div>
		<ul id="showhelp" class="listing">
			<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a></li>
			<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>	
			<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message></a></li>	 --%>				
		</ul>
		<a id="showprint" style="visibility: hidden; display: none;" href="#">
			<img src='<%=contextpthval%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" />
		</a>	
	</div>
	<div class="frmpnlbrdr">
		<form:form id="lbTypeWiseWards" name="lbTypeWiseWards" commandName="lbTypeWiseWards">
			<input type="hidden" id="flow" name="flow"/>
			<input type="hidden" id="state" name="state" value="<c:out value='${state}'/>"/>
			<input type="hidden" id="lbTypeCode" name="lbTypeCode" value="<c:out value="${lbTypeCode}" escapeXml="true"></c:out>"/>
			
			<div class="frmpnlbg" id='showbytext' >
					<div class="frmtxt" align="center" id ="resultLBType">
						<div class="frmhdtitle" >
							<spring:message code="Label.WARDDETAILS" htmlEscape="true"/>
						</div>
						<br/>
						<h1><label style="text-transform: uppercase;">Ward(s) of <c:out value="${lbTypeHierarchy}" escapeXml="true"></c:out></label></h1>
						<table id="tblViewLBTWards" width="100%" >
							<thead>
								<tr >
									<th align="center" width="5%"><b><spring:message code="Label.SNO"/></b></th>
									<th align="center" width="35%"><b><spring:message code="Label.WARDNAMEINENG"/></b></th>
									<th align="center" width="35%"><b><spring:message code="Label.WARDNAMEINLOCAL"/></b></th>
									<th align="center" width="25%"><b><spring:message code="Label.WARDNUMBER"/></b></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${lisWardDetails}" varStatus="sn">
									<tr style="height: 30px;">		
										<td align="center"><c:out value="${sn.count}" escapeXml="true"></c:out></td>
										<td align="left"><c:out value="${data[0]}" escapeXml="true"></c:out></td>
										<td align="left"><c:out value="${data[1]}" escapeXml="true"></c:out></td>
										<td align="center"><c:out value="${data[2]}" escapeXml="true"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table> 
						<table width="100%">
							<tr>
								<td align="center">
									<input type="button" name="close" class="btn" style="width: 100px;" onclick="backForm();" value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>" />
									<!-- added by sunita on 07-7-2015  -->
									<input type="button" name="close" class="btn" style="width: 100px;" onclick="javascript:window.location.href='welcome.do'" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" />
								</td>
							</tr>
						</table>
				</div>
			</div>	
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</body>
</html>