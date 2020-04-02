<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*" %> 
<%-- <%
	String contextPath = request.getContextPath();
	java.util.Calendar cal = Calendar.getInstance();
	java.util.Date dt = new Date();
	cal.setTime(dt);
	int currentMonth =  cal.get(Calendar.MONTH);
	int currentDate =  cal.get(Calendar.DATE);
	int currentYear =  cal.get(Calendar.YEAR);

%> --%>
<head>
	<title>LGD Transactions</title>
	<style>
	/* css for timepicker */
	.ui-timepicker-div .ui-widget-header{ margin-bottom: 8px; }
	.ui-timepicker-div dl{ text-align: left; }
	.ui-timepicker-div dl dt{ height: 25px; }
	.ui-timepicker-div dl dd{ margin: -25px 0 10px 65px; }
	.ui-timepicker-div td { font-size: 90%; }
	/* This is the style for the trigger icon. The margin-bottom value causes the icon to shift down to center it. */
	.ui-datepicker-trigger {margin-left:1px;margin-top: 8px;margin-bottom: -3px;}
	.textHead{color: #3B5998;}
	</style>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery-ui-1.8.7.custom.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf-8" 		 src="<%=contextpthval%>/external/jqueryCustom/js/TableTools.js" ></script>
	<link href="<%=contextpthval%>/external/jqueryCustom/css/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/external/jqueryCustom/css/demo_table_jui.css" rel="stylesheet"  type="text/css" />
	<script type="text/javascript">
 	
	 	setScheduleDates = function(){
	 		document.getElementById('dataBlank').innerHTML="";
	 		document.getElementById('Submit').disabled=true;
	 		document.searchForm.scheduleDates.value = "";
	 		var tblTask = document.getElementById('tblViewLGDTransactions');
	 		if(validateSchedules(tblTask)){
	 			
	 			var scheduleData = fetchScheduleValues(tblTask);
	 			if(scheduleData == null || scheduleData == ""){
	 				document.getElementById('dataBlank').innerHTML="NO RECORDS FOR UPDATE !";
	 				return false;
	 			}
	 			
	 			document.searchForm.scheduleDates.value = scheduleData.substring(0,scheduleData.lastIndexOf("@@")); 
	 			document.searchForm.method = "post";
	 	     	document.searchForm.action = "viewLGDTrans.htm?<csrf:token uri='viewLGDTrans.htm'/>";
	 	     	document.searchForm.submit();
	 			return true;
	 		}
	 		return false; 
	 	 };
	 	 
	 	 validateSchedules = function(table){
	 		return true; 
	 	 };
	 	 
	 	
	 	 fetchScheduleValues = function (retrievetable){
	 		var scheduleDetails = "";
	 		var tableRowLength = retrievetable.rows.length - 1;
	 	 	for (var i = 1;i <= tableRowLength;i++) {
	 	 		var schDateObj = retrievetable.rows[i].cells[5].childNodes[0];
	 	 		
	 	 		if(!schDateObj.disabled && schDateObj.value != ""){
	 	 			scheduleDetails += schDateObj.value + "|";
	 	 			var tid = retrievetable.rows[i].cells[6].childNodes[0].value;
	 	 			scheduleDetails += tid + "@@";
	 	 		}
	 	 	}
	 	 	return scheduleDetails;
	 	};
	 	
	 	checkEnable = function (){
	 		document.getElementById('Submit').disabled=true;
	 		var retrievetable = document.getElementById('tblViewLGDTransactions');
	 		var tableRowLength = retrievetable.rows.length - 1;
	 	 	for (var i = 1;i <= tableRowLength;i++) {
	 	 		var schDate = retrievetable.rows[i].cells[5].childNodes[0].value;
	 	 		if(schDate != ""){
	 	 			document.getElementById('Submit').disabled=false;
	 	 			break;
	 	 		}
	 	 	}
	 	};
 	</script>	
</head>
<body>
	<div id="frmcontent">



		<div class="frmhd">

			<h3 class="subtitle"><spring:message htmlEscape="true" code="" text=" LGD Transactions"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]"
					data-openimage="images/minus.jpg"
					data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
						border="0" /></a></li>

				<%--//this link is not working  <li><a href="#" class="frmhelp"><spring:message
							htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>

			</ul>


		</div>

		
			   <div class="frmpnlbrdr">
				   <form:form id="searchForm" method="POST" commandName="searchForm" name="searchForm">
				   		<input type="hidden" id="scheduleDates" name="scheduleDates"/>
				   		<div id="dataBlank" class="errormsg"></div>
						
						<table id="tblViewLGDTransactions" width="100%" >
							<thead>
								<tr >
									<th align="center" width="5%"><b>S.No.</b></th>
									<th align="center" width="7%"><b>Transaction Id</b></th>
									<th align="center" width="13%"><b>Effective Date</b></th>
									<th align="center" width="55%"><b>Description</b></th>
									<th align="center" width="8%"><b>Status</b></th>
									<th align="center" width="12%"><b>Schedule Date</b></th>
									<th style="display: none"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${lgdTransList}" varStatus="sn">
									<tr>		
										<td align="center"><c:out value="${sn.count}" escapeXml="true"></c:out></td>
										<td align="center"><c:out value="${data.tId}" escapeXml="true"></c:out></td>
										<td align="center"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${data.effectiveDate}"/></td>
										<td><c:out value="${data.description}" escapeXml="true"></c:out></td>
										<td align="center">
											<c:choose>
												<c:when test="${data.statusFlg eq 'P'}">Pending</c:when>
												<c:when test="${data.statusFlg eq 'S'}">Schedule</c:when>
											</c:choose>
										</td>
										<td align="center"><input type="text" id="scheduleDate_${sn.count}" name="scheduleDate_${sn.count}" readonly="readonly" size="10"  <c:if test="${data.scheduledDate != null}">value="<fmt:formatDate pattern="dd/MM/yyyy" value="${data.scheduledDate}"/>" disabled="disabled"</c:if>/></td>
										<td align="center" style="display: none;"><input type="hidden" id="rpId_${sn.count}" name="rpId_${sn.count}" size="1" value="<c:out value='${data.tId}' escapeXml='true'></c:out>"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table> 
											
						<c:if test="${fn:length(lgdTransList) > 0}">
							<div  class="btnpnl">
										<input type="button" id="Submit" value="Schedule Transaction Dates" onclick="return setScheduleDates();"/>
								</div>
	                    </c:if>
							
				  </form:form>	
				  <script src="/LGD/JavaScriptServlet"></script>
			  </div>
		</div>

<script type="text/javascript">
$(function() {
	var len = '<c:out value="${fn:length(lgdTransList)}" escapeXml="true"></c:out>';
	for(var i=1; i<=len; i++){
		$("#scheduleDate_"+i).datepicker({
			minDate: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
			dateFormat: 'dd/mm/yy',
			showOn: "button",
			buttonImage: "<%=contextpthval%>/images/calender.gif",
			buttonImageOnly: true
		});
		if(document.getElementById("scheduleDate_"+i).disabled){
			$("#scheduleDate_"+i).datepicker( 'disable' );	
		};
	}
});
$(document).ready(function() {
	
	$('#tblViewLGDTransactions').dataTable({
		"bJQueryUI": true,
		"aoColumns":[null, null, null, {"bSortable": false}, null, {"bSortable": false},{"bSortable": false}],
		"sPaginationType": "full_numbers"
	});
} );
</script>
</body>
</html>