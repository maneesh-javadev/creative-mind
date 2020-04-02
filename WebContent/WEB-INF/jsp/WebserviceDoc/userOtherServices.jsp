<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="otherServicesForm"> 
<head>

<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>

<%!String contextPath;%> 
<%
	contextPath = request.getContextPath(); 
%>

<link rel="stylesheet" href="https://rawgit.com/angular/bower-material/master/angular-material.min.css">

 <script src="https://cdn.jsdelivr.net/hammerjs/2.0.4/hammer.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-animate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-aria.min.js"></script>

  <!-- Use dev version of Angular Material -->
  <script src="https://rawgit.com/angular/bower-material/master/angular-material.min.js"></script>



<script type="text/javascript" src="<%=contextPath%>/angularjs/ui-bootstrap-tpls-0.12.0.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/angularjs/userOtherServices.js"></script> 

<script type="text/javascript" src="<%=contextPath%>/angularjs/reportingFormController.js"></script>
<script type="text/javascript" src="<%=contextPath%>/angularjs/textOrReplyController.js"></script>

 <script type="text/javascript" src="<%=contextPath%>/angularjs/ngIpAddress.min.js"></script>
 
 
 <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.css">
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>Insert title here</title>
<script type="text/javascript" language="javascript">

$(document).ready(function(){
    $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
        localStorage.setItem('activeTab', $(e.target).attr('href'));
    });
    var activeTab = localStorage.getItem('activeTab');
    if(activeTab){
        $('#myTab a[href="' + activeTab + '"]').tab('show');
    }
});
</script>

<style>
html {
	height: 100%;
	/*Image only BG fallback*/
	
	/*background = gradient + image pattern combo*/
}
body {
margin: 0;
}


 ul.topnav {
    list-style-type: none;
    padding: 41px;
    background-color: rgb(41, 144, 202);
   

    
} 

ul.topnav li {float: left;
margin-left: 2%;

 

}

ul.topnav li a {
    display: block;
    color: black;
    text-align: center;
    padding: 10px 16px;
    text-decoration: none;
    border: 0px solid #939838;
	font-family: montserrat;
	font-size: 18px;
}

/* ul.topnav li a:hover:not(.active) {background-color: #5cb85c;}
 */
ul.tab li a:focus, .active {
    background-color: #FFF;
}

.navbar-default .navbar-nav > .active{
    color: #000;
    background: #d65c14;
}
.tabcontent.active {
    display: block;
}
/* ul.topnav li a.active {background-color: #4CAF50;}
 */
ul.topnav li.right {float: right;}

@media screen and (max-width: 600px){
    ul.topnav li.right, 
    ul.topnav li {float: none;}
}



.asterisk::-webkit-input-placeholder {
    color:    #f00;
}
.asterisk:-moz-placeholder {
   color:    #f00;
   opacity:  1;
}
.asterisk::-moz-placeholder {
   color:    #f00;
   opacity:  1;
}
.asterisk:-ms-input-placeholder {
   color:    #f00;
}




.checkboxdemoSelectAll .demo-legend {
  color: #3F51B5; }

.checkboxdemoSelectAll .demo-fieldset {
  border-style: solid;
  border-width: 1px;
  height: 100%; }

.checkboxdemoSelectAll .demo-select-all-checkboxes {
  padding-left: 30px; }


</style>

</head>
<body  ng-controller="consumeServiceController">

<div align="left" style="margin-bottom: 20%">
<div id="menu">
<ul class="topnav" id="myTab">

  <li class="active"><a id="a1" data-toggle="tab" href="" class="tabcontent " ng-click="showRequestWeb()">Request consume web services</a></li>
  <li><a  href="#" data-toggle="tab" class="tabcontent " ng-click="showReportingIssue()">Issues Reporting</a></li>
   <li><a href="" data-toggle="tab" class="tabcontent "  ng-click="subscribeNotification()">Subscribe for Email/SMS Notification</a></li> 
  <li class=""   style="margin-left: 22%;"><b>Welcome</b><span style="color:#FFF">(${userName})</span> </li><br>
  
  <%-- <li class="right "><button type="button" name="Submit3" id="btn1" class="btn btn-sm glyphicon glyphicon-log-out btn btn-info btn-lg" onclick="javascript:location.href='userLoginForm.do?<csrf:token uri='welcome.do'/>';"><i class="fa "></i>&nbsp;Logout</button> --%>
  <li class="right "><a  onclick="javascript:location.href='userLoginForm.do?<csrf:token uri='welcome.do'/>';"><span class="glyphicon glyphicon-log-out" style="color:maroon;text-color:black;"></span> Logout<!-- <img src='images/WebserviceSupportImages/logout.png' width='18' height='19' border='0' align="middle" /> --></a>
  </li>

</ul>
</div>
</div>
<div class="row">
<div class="form-group">
<div class="col-sm-12">
		<section class="content" style="background-color: whitesmoke;margin-top: -20%;">
    		<div class="row">
        		<section class="col-lg-12 ">
        		<form class="form-horizontal" ng-if="requestWeb" name="myForm">
					<div class="box-body">
						<div class="box-header subheading">
		                	<h4 style="color: inherit;">Name of the software application in which these web services will be used </h4>
		                </div>
		                
		             <div class="alert alert-danger fade in" ng-if="errorFlag">
        				<a href="#" class="close" data-dismiss="alert">&times;</a>
        				<strong>Error!</strong>.Error While inserting data.
    				</div>   
		            <div class="alert alert-sucess fade in" ng-if="sucessFlag">
        				<a href="#" class="close" data-dismiss="alert">&times;</a>
        				<strong>Sucess!</strong>.Data has been saved sucessfully.
    				</div>     
		                
		               <div class="form-group">
		                	<label  class="col-sm-3 control-label">Application Name</label>
		                	
		                	<table class="table table-striped table-bordered" style="width: 50%">
		                	<thead class="thead-default">
	  							<tr>
	  								<th>
			  							<button type="button" ng-disabled="applicationNameList.length>4" class="btn btn-primary" ng-click="addNewApp()">Add New Row</button>
			  							<input  type="button" ng-disabled="applicationNameList.length==removeAppLength" class="btn btn-danger" ng-click="removeAppRow()" value="Remove">
			  						</th>
			  					</tr>
			  				</thead>
			  				<tbody>
						    <tr ng-repeat="applicationNames  in applicationNameList">
						    <td>
						    <input type="text" maxlength="100" class="form-control asterisk" ng-disabled="applicationNames.nonEdit" ng-model="applicationNames.applicationName" required = "true" placeholder="S/w application *" />
						    </td>
						    </tr>
						    </tbody>
		                	</table>
								
		               </div>
		               
		               
		               <div class="box-header subheading">
		                	<h4 style="color: inherit;">Enter IP Addresses from which you would like to consume web services</h4>
		                </div>
		                <div class="form-group">
		                	<label  class="col-sm-3 control-label">IP Addresses </label>
		                	
		                	<table class="table table-striped table-bordered" style="width: 50%">
		                	<thead class="thead-default">
	  							<tr>
	  								<th>
			  							<button type="button" ng-disabled="ipAddressList.length>4" class="btn btn-primary" ng-click="addNew()">Add New Row</button>
			  							<input  type="button" ng-disabled="ipAddressList.length==removeIpLength" class="btn btn-danger" ng-click="removeIpRow()" value="Remove">
			  						</th>
			  					</tr>
			  				</thead>
			  				<tbody>
						    <tr ng-repeat="ipAddresses  in ipAddressList">
						    <td>
						    <input type="text" class="form-control asterisk" id="ipAddresses" ng-model="ipAddresses.ipAddress" name="ipAddresses" ng-disabled="ipAddresses.nonIpEdit" ng-ip-address required = "true" placeholder="IP Address *" />
						    <span ng-show ="ipAddresses.ipAddress!='' || ipAddresses.ipAddress==undefind" style="color: red;font-size: small" ng-if="myForm.ipAddresses.$invalid">Invalid Ip Address</span>
						    </td>
						    </tr>
						    </tbody>
		                	</table>
								
		               </div>
		                	<div class="form-group">
		                		<label  class="col-sm-3 control-label">Click to consume web services from Mobile App</label>
									<div class="col-sm-3" >
										<input type="checkbox" ng-model="mobileAppCheck" id="mobileAppCheck" title="Please keep generated token in .so or .lib file."  />
									</div>
		               		</div>
		               <div class="box-header subheading">
		                	<h4 style="color: inherit;"> File Upload Request </h4>
		                </div>
		                <div class="form-group">
		               		<div class="ms_container row"  >
		               			<div class="col-sm-2"></div>
								<div class="col-sm-10 form-group">
									<label  class="col-sm-2 control-label">Attach File</label>
									<div class="col-sm-2" >
										<input type="file" accept="application/pdf" class="form-control" required name="uploadFile" id="uploadFile" placeholder="Upload file" onclick="{return validateEmptyFile()}"  />
									</div>
									
									<div class="col-sm-4">
										<a ng-href="uploadUserFile.do?<csrf:token uri='uploadUserFile.do'/>&fileName={{fileName}}"
										  target="_blank"   ng-show="fileExist">{{fileName}}</a>
										  <input  type="button"  class="btn btn-danger" ng-click="removeFileFlag()" value="Delete" ng-show="fileExist">
									</div>
									<div class="col-sm-2">
										
									</div>
								</div>
									
							</div>	
		                </div>
		               
		              <div class="box-footer" style="background-color: whitesmoke;">
                      	<div>
                       		<div class="pull-right">
                       			<button ng-disabled ="!myForm.$valid||btnDisabled" class="btn btn-success" ng-click="requestConsumeWebService()"> <span class="glyphicon glyphicon-user"> Submit</button>
                            	<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i>Close</button>
                        	</div>
                     	</div>   
                  	</div>
		               
			       </div>
			      </form>
			      
	        		<form class="form-horizontal" ng-if="reportingIssue">
	        			<button  style="margin-top: 2%;margin-left: 3%;" class="btn btn-success" ng-click="createNewReport()">Report an issue</button>
						<div style="background-color: white;margin: 30px 30px 30px 30px;padding: 10px 10px 10px 10px;border:1px solid #ccc;">
						
						<table style="width: 100%;" class="table table-striped">
					  		<thead style="background-color: #f1f1f1">
						  		<tr>
						  			<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 10px;width: 10px;" align="center"><b>S.No.</b></td>
						  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b>Issue Submitted To</b></th>
						  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b>Type of issues</b></th>
						  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 20px;"><b>Reported Issue</b></th>
						  			<th style="padding: 10px 10px 10px 50px;border: 1px solid #ccc;line-height: 25px;"><b>Status</b></th>
						  		</tr>
					  		</thead>
  							<tbody>
  								<tr ng-repeat="reportingIssue  in reportingIssueList"  ng-class="{'background-color: #f1f1f1': $index+1 == 1,'background-color: #f1f1f1': $index+1 == 2}">
	  								<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 20px;width: 10px;" align="center">{{$index+1}}</td>
	  								<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 20px;width: 10px;" align="center">{{reportingIssue.stateName}}</td>
	  								<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 20px;width: 10px;" align="center">
	  									{{reportingIssue.userType =='B' ? 'Block' : '' || reportingIssue.userType =='R' ? 'Revenue/Land region(District,Sub district,Village)':''||reportingIssue.userType =='P'?'Panchyat': '' || reportingIssue.userType =='U'?'Urban Local Body' : ''}}
	  								</td>
	  								<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 20px;width: 10px;" align="center">
	  								<button type="button" class="btn btn-info btn-sm" ng-click="showReportedText($index)">Text</button>
	  								</td>
	  								<td style="padding: 10px 10px 10px 10px;border: 1px solid #ccc;line-height: 20px;width: 10px;" align="center">&nbsp;</td>
								</tr>
							</tbody>
							</table>
						
						</div>
						
						
						</form>
						
						
						<form class="form-horizontal" name="subscribeForm" ng-if="subscribeNotificationFlag">
							<div style="background-color: white;margin: 46px 464px 160px 61px;padding: 49px 34px 32px 54px;border:37px solid #ccc;">
							
							 <fieldset>
	    						
	    						<div class="box-header with-border">
                        			<h3 class="box-title">Select the entity on which you would like to receive email notification:</h3>
                        		</div>
	    						
	    						<div class="form-group">
	          					 	<label for="entity" class="col-sm-4 control-label">Select State</label>
		          					 <div class="col-sm-6" >
		          					 <select ng-model="emailNotification.slc" class="form-control">
					  					<option value="0">All State</option>
					   						<option ng-selected="{{defaultvalue == state.stateCode}}" ng-repeat="state in statelist" value="{{state.stateCode}}" >{{state.stateNameEnglish}}</option>
					  				 </select>
					  				 </div>
	                            </div>
	                            
	                            <div class="form-group">
	          						<label for="entity" class="col-sm-4 control-label">Land-region / Revenue <span style="color: red">*</span></label>
		          						<div class="col-sm-6" >
		          					 		<div layout="row" layout-wrap flex>
	          									<div flex-xs flex="50">
							            			<md-checkbox aria-label="Select All" ng-checked="isChecked()" md-indeterminate="isIndeterminate()" ng-click="toggleAll()">
							              				<span ng-if="isChecked()">Un-</span>Select All
							              			</md-checkbox>
	          									</div>
						            			<div class="demo-select-all-checkboxes" flex="100" ng-repeat="item in items">
							              			<md-checkbox  ng-checked="exists(item, selected)"  ng-click="toggle(item, selected)">
							               				{{ item }}
							              			</md-checkbox>
						            			</div>
	          								</div>
					  				 	</div>
	                            </div>
	                            
	                            
	                            <div class="form-group">
	          						<label for="entity" class="col-sm-4 control-label">Select type of LocalBody<span style="color: red">*</span></label>
		          						<div class="col-sm-6" >
		          					 		<div layout="row" layout-wrap flex>
	          									<div flex-xs flex="50">
							            			<md-checkbox aria-label="Select All" ng-checked="isCheckedLocalBody()" md-indeterminate="isIndeterminateLocal()" ng-click="toggleAllLocal()">
							              				<span ng-if="isCheckedLocalBody()">Un-</span>Select All
							              			</md-checkbox>
	          									</div>
						            			<div class="demo-select-all-checkboxes" flex="100" ng-repeat="item in itemsLocalBody">
							              			<md-checkbox  ng-checked="exists(item, selectedLocalBody)"  ng-click="toggle(item, selectedLocalBody)">
							               				{{ item }}
							              			</md-checkbox>
						            			</div>
	          								</div>
					  				 </div>
	                            </div>
	                            <div class="form-group">
	                            <label for="entity" class="col-sm-4 control-label">Block</label>
		          					 <div class="col-sm-6 demo-select-all-checkboxes" >
				              			<md-checkbox ng-model="emailNotification.block">
				              			</md-checkbox>
					  				 </div>
	                            </div>
	                       			<button  ng-disabled="!(selected.length>0 || (selectedLocalBody.length>0))" class="btn btn-success" ng-click="saveNotificationDetails()"> <span class="glyphicon glyphicon-user"> Submit</button>
	                            	<button type="button" name="Submit3" id="btn1" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i>Close</button>
	  						</fieldset>
	                  		</div>
	                  		
	                       		
						
						</form>
			      
				</section>
			</div>
		</section>
		</div>
		</div>
		</div>
</body>
</html>