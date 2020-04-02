<%@ page language="java" contentType="text/html; charset=ISO-8859-1;application/javascript;charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="otherServicesForm">
<head> 
<%!String contextPath;%>
<%
	contextPath = request.getContextPath(); 
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
 <%@include file="../common/taglib_includes.jsp"%> 

<title>Register For Web Services</title>
<style>
 {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

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
    background-color: #70d8d0 ;
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
.highlight
{
background-color:yellow;
}




#custom-search-input {
        margin:0;
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
        color:#D9230F;
    }
 
    .search-query:focus + button {
        z-index: 3;   
    }


</style>
<script type="text/javascript" language="javascript">

function changeBackground(row){
	$("#"+row).attr("style","background-color: #b3e6fe;");
}

function revertBackBackground(row){
		$("#"+row).removeAttr("style");
}

function putTextData(data,id){
	$("textarea#reportedTextData").val(data);
	$("#serialNumber").val(parseInt(id));
	$('#myModal').modal('show');
}


function setOffset(tab){
	var offsteVal=(10*tab)-10;
	$("#pageStart").val(offsteVal);
	createWsUser.method = "post";
	createWsUser.action = "wsUserRegistrationList.htm?<csrf:token uri='wsUserRegistrationList.htm'/>";
	createWsUser.submit();
}

</script>
</head>
<body ng-controller="consumeServiceController">
	
	<div id="frmcontent">
		<div class="frmpnlbrdr">
		<div class="box-header with-border">
        	<h3 class="box-title">List of Reported Issues</h3>
		</div>
		<form:form  method="POST" action="wsUserRegistrationList.htm" commandName="reportingIssue"  name="reportingIssue">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="wsUserRegistrationList.htm"/>" /> 
			<input type="hidden" name="pageStart" value="" id="pageStart"/> 
			<input type="hidden" name="fileName" value="" id="fileName"/> 
			<div style="background-color: white;margin: 30px 30px 30px 30px;padding: 10px 10px 10px 10px;border:1px solid #ccc;">
			<c:if test="${sucess}">
			    <div class="alert alert-success fade in">
			        <a href="#" class="close" data-dismiss="alert">&times;</a>
			        <strong>Success!</strong>
			       Replyed has been sent.
			    </div>
			</c:if>
			<div id="custom-search-input" style="width: 23%;margin-left: 77%;margin-bottom: 1%;">
                 <div class="input-group col-md-12">
                     <input type="text" class="  search-query form-control" placeholder="Search" />
                     <span class="input-group-btn">
                         <button class="btn btn-danger" type="button">
                             <span class=" glyphicon glyphicon-search"></span>
                         </button>
                     </span>
                 </div>
             </div>
		  	<c:set var="rowCount" value="0"></c:set>
		  	 <table style="width: 100%;" class="table table-striped">
		  		<thead style="background-color: #f1f1f1">
			  		<tr>
			  			<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 10px;width: 10px;" align="center"><b>S.No.</b></td>
			  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b>Issue Submited To </b></th>
			  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b>Issue Reported By</b></th>
			  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b></b>Reported Issue</th>
			  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b></b>Status</th>
			  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b></b>Reply</th>
			  		</tr>
		  		</thead>
		  		 <tbody>
		  		<c:forEach items="${reportingIssue}" var="obj" varStatus="count">
		  		<c:if test="${count.count eq 1}">
		  		<input type="hidden" name="pageStart" value="${obj.pageStart}" id="pageStart"/>
		  		<c:set var="rowCount" value="${obj.rowCount}"></c:set>
		  		</c:if>
		  			<tr class="feedbackListTr" id="abc${count.count }" onmouseover="changeBackground('abc${count.count}')" onmouseout="revertBackBackground('abc${count.count}')">
		  				<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 10px;width: 10px;" align="center">${count.count}</td>
		  				<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 30px;width: 10px;" align="center">${obj.nodalOfficer.nodalOfficerName}</td>
		  				<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 30px;width: 10px;" align="center">${obj.registration.name}</td>
		  				<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 30px;width: 10px;" align="center">${obj.reportedIssueText}
		  				<input type="hidden" ng-model="reportedtext" value="${obj.reportedIssueText}">
		  				
		  				</td>
		  				<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 30px;width: 10px; background-color: #ff2e63" align="center"><span class="badge">Waiting</span></td>
		  				<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 30px;width: 10px;" align="center"><button type="button" class="btn btn-info btn-sm" onclick="javascript:putTextData('${obj.reportedIssueText}','${obj.serialNumber}')">Reply Text</button></td>
		  			</tr>
		  		</c:forEach>
		  		</tbody> 
		  	</table> 
				  	<div>
					  	<div align="center">
					  		<button type="button" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';">Close</button>
						</div>
					  	<div align="right">
	  						<ul class="pagination">
							    <c:forEach begin="1" end="${rowCount}" varStatus="tabs">
							  		<li id="${tabs.count}_tab"><a href="#" onclick="setOffset(${tabs.count})">${tabs.count}</a></li>
							    </c:forEach>
							</ul>
						</div>
						
				  	</div>
				  </div>
			</form:form>
		</div>
	</div>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <form class="form-horizontal" method="POST" name="form" action="replyToReportedIssue.htm" commandName="reportingIssue">
      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="replyToReportedIssue.htm"/>" /> 
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
        	<div class="box-header with-border">
            	<h3 class="box-title">Report New Issue</h3>
            </div>
            
            	<div class="form-group">
                	<label for="entity" class="col-sm-4 control-label">Reported Issue Text</label>
					<div class="col-sm-6" >
						<textarea rows="4" cols="34" id="reportedTextData"></textarea>
					</div>
                </div>
                <div class="form-group">
                	<label for="entity" class="col-sm-4 control-label">Reply Issue Text</label>
					<div class="col-sm-6" ><textarea rows="4" cols="34" name="replyText"></textarea></div>
					<input type="hidden" name="serialNumber" id="serialNumber">
				</div>
        </div>
       <div class="modal-footer">
       	<button type="submit" class="btn btn-sucess">Submit</button>
         <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
       </div>
     </div>
     </form>
    </div>
  </div>
	
	
</body>
</html>