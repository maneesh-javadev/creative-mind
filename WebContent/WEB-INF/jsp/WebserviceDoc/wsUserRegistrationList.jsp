<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1;application/javascript;charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib
	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"
	prefix="csrf"%>
	<script type='text/javascript' src="js/notie/notie.js"></script>
<script type='text/javascript' src="js/notie/notie.min.js"></script>

<link rel="stylesheet" href="js/notie/notie.css">
<link rel="stylesheet" href="js/notie/notie.min.css">
<%-- <%@include file="../common/taglib_includes.jsp"%> --%>

<title>Register For Web Services</title>

<style>
{
box-sizing

border-box

}
body {
	font-family: "Lato", sans-serif;
}

/* Style the tab */
div.tab {
	float: left;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
	width: 20%;
	height: 900px;
}

/* Style the buttons inside the tab */
div.tab a {
	display: block;
	background-color: inherit;
	color: black;
	padding: 22px 16px;
	width: 100%;
	border: none;
	outline: none;
	text-align: left;
	cursor: pointer;
	transition: 0.3s;
	font-size: 17px;
	text-decoration: none;
}

/* Change background color of buttons on hover */
div.tab a:hover {
	background-color: #ebfaf9;
}

/* Create an active/current "tab button" class */
div.tab a.active {
	background-color: #70d8d0;
}

/* Style the tab content */
.tabcontent {
	float: right;
	padding: 0px 12px;
	border: 1px solid #ccc;
	width: 80%;
	border-left: none;
	height: 900px;
	background-color: #ebfaf9;
}

.highlight {
	background-color: yellow;
}

#custom-search-input {
	margin: 0;
	margin-top: 10px;
	padding: 0;
}

#custom-search-input .search-query {
	padding-right: 3px;
	padding-right: 4px \9;
	padding-left: 3px;
	padding-left: 4px \9;
	/* IE7-8 doesn't have border-radius, so don't indent the padding */
	margin-bottom: 0;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}

#custom-search-input button {
	border: 0;
	background: none;
	/** belows styles are working good */
	padding: 2px 5px;
	margin-top: 2px;
	position: relative;
	left: -28px;
	/* IE7-8 doesn't have border-radius, so don't indent the padding */
	margin-bottom: 0;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	color: #D9230F;
}

.search-query:focus+button {
	z-index: 3;
}

.table td {
	word-wrap: break-word !important;
}
</style>
<script type="text/javascript" language="javascript">
$(document).ready(function() {
	$('#wsTable').dataTable({
		"sScrollY": "500px",
		"sScrollX": "500px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
		
	});
	$('.dropdown-toggle').click(function(event){
	console.log('clicked ', $(this));
	$(this).dropdown('toggle');
	event.stopImmediatePropagation();
});
	
	var success ="${sucess}";
	var type ="${type}";
	
	if(success)
		{
		
		if(type == "A")
			{
			notie.alert({ type: 2, text: ' Request for consuming LGD Web Service has been Approved ', time: 4 });

			}
		if(type == "R")
		{
		notie.alert({ type: 2, text: ' Request for consuming LGD Web Service has been Rejected ', time: 4 });

		}
		
		}
});

 
function changeBackground(row){
	$("#"+row).attr("style","background-color: #b3e6fe;");
}

function revertBackBackground(row){
		$("#"+row).removeAttr("style");
}



function setOffset(tab){
	var offsteVal=(10*tab)-10;
	$("#pageStart").val(offsteVal);
	createWsUser.method = "post";
	createWsUser.action = "wsUserRegistrationList.htm?<csrf:token uri='wsUserRegistrationList.htm'/>";
	createWsUser.submit();
} 

function takeApprovel(id,request)
{
	var RequestType =request;
	var userId =id;
	
	if(RequestType =="A")
	{
		window.location.href = "approveUserRegitration.htm?<csrf:token uri='approveUserRegitration.htm'/>&userId="+userId+"&type="+RequestType;
	
	}
	else if(RequestType =="R")
		{
		window.location.href = "approveUserRegitration.htm?<csrf:token uri='approveUserRegitration.htm'/>&userId="+userId+"&type="+RequestType;

		}
	
	
}




</script>
</head>
<body>

	<div id="frmcontent">
		<div class="frmpnlbrdr">
			<div class="box-header with-border">
				<h3 class="box-title">List of Registered Users that are requested for Web Service</h3>
			</div>
			<form:form method="POST" action="wsUserRegistrationList.htm"
				commandName="createWsUser" id="createWsUser" name="createWsUser">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="wsUserRegistrationList.htm"/>" />
				<input type="hidden" name="pageStart" value="" id="pageStart" />
				<input type="hidden" name="fileName" value="" id="fileName" />
			
				<div
					style="background-color: white; margin: 30px 30px 30px 30px; padding: 10px 10px 10px 10px; border: 1px solid #ccc;">
					<%-- <c:if test="${sucess}">
						<div class="alert alert-success fade in">
							<a href="#" class="close" data-dismiss="alert">&times;</a>
							<!-- <strong>Success!</strong> -->
							<c:if test="${type eq 'A'}">
			                 Request for consuming LGD Web Service has been <strong></strong>
							</c:if>
							<c:if test="${type ne 'A'}">
			         	   Request for consuming LGD Web Service has been <strong>Rejected</strong>
							</c:if>
						</div>
					</c:if> --%>
					<!-- <div id="custom-search-input" style="width: 23%;margin-left: 77%;margin-bottom: 1%;">
                 <div class="input-group col-md-12">
                     <input type="text" class="search-query form-control" placeholder="Search" />
                     <span class="input-group-btn">
                         <button class="btn btn-danger" type="button">
                             <span class=" glyphicon glyphicon-search"></span>
                         </button>
                     </span>
                 </div>
             </div> -->
					<c:set var="rowCount" value="0"></c:set>
					<div class="table-responsive ">
					<table style="width: 100%; table-layout: auto;" id="wsTable"
						class="table table-striped table-bordered ">
						<thead style="background-color: #9ec5ec">
							<tr>
								<td
									<b>S.No.</b></td>
								<th
									<b>User
										Name</b></th>
								<th>
									<b>Designation</b></th>

								<th>
									<b>IP
										Address</b></th>
								<th
									><b>Uploaded
										Files</b></th>
								<th
									><b>Status</b></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${registrationForms}" var="obj"
								varStatus="count">
								<%-- <c:if test="${count.count eq 1}">
		  		<input type="hidden" name="pageStart" value="${obj.pageStart}" id="pageStart"/>
		  		<c:set var="rowCount" value="${obj.rowCount}"></c:set>
		  		</c:if> --%>
								<tr class="feedbackListTr" id="abc${count.count }"
									onmouseover="changeBackground('abc${count.count}')"
									onmouseout="revertBackBackground('abc${count.count}')">
									<td
										>${count.count}</td>
									<td
										>${obj.name}</td>
									<td
										>${obj.designation}</td>

									<td
										><c:forEach items="${obj.ipAddressList}"
											var="ipAddress">
		  				${ipAddress.ipAddress},
		  				</c:forEach></td>
									<td
										><a
										href="uploadUserFile.htm?fileName=${obj.uploadFileName}&<csrf:token uri='uploadUserFile.htm'/>">
											<c:out value="${obj.fileName}" />
									</a></td>
									<td
										style="padding: 10px 10px 10px 10px; border: 1px solid #ccc; line-height: 30px; width: 10px; vertical-align: middle;"
										align="center"><c:set var="status"
											value="${obj.registrationStatus}"></c:set> <c:if
											test="${status == 'N'.charAt(0)}">
											<select class="form-control btn btn-primary " name=""
												onchange="takeApprovel(${obj.userRegistrationId},this.value);">
												<option value="-1">-- Action --</option>
												<option value="A">Approve</option>
												<option value="R">Reject</option>
											</select>

											<%-- <div class="dropdown">
				  				 <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"  aria-haspopup="true">Action
								    <span class="caret"></span>
								  </button>
								    <ul class="dropdown-menu">
								      <li>
									      <a  onclick="return takeApprovel()" href="approveUserRegitration.htm?userId=${obj.userRegistrationId}&type=A&<csrf:token uri='approveUserRegitration.htm'/>">
											Approve
										  </a>
									  </li>
								      <li>
								     	 <a  onclick="return takeApprovel()" href="approveUserRegitration.htm?userId=${obj.userRegistrationId}&type=R&<csrf:token uri='approveUserRegitration.htm'/>">
											Reject
										  </a>
								      </li>
								    </ul>
							 </div> --%>
										</c:if> <c:if test="${status == 'A'.charAt(0)}">
											<h4>
												<span class="label label-success">Approved</span>
											</h4>
										</c:if> <c:if test="${status == 'R'.charAt(0)}">
											<h4>
												<span class="label label-danger">Rejected</span>
											</h4>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<div>
						<div align="center">
							<button type="button" class="btn btn-danger"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';">Close</button>
						</div>
						<%-- <div align="right">
	  						<ul class="pagination">
							    <c:forEach begin="1" end="${rowCount}" varStatus="tabs">
							  		<li id="${tabs.count}_tab"><a href="#" onclick="setOffset(${tabs.count})">${tabs.count}</a></li>
							    </c:forEach>
							</ul>
						</div> --%>

					</div>
				</div>
			</form:form>
		</div>
	</div>
                     

</body>
</html>