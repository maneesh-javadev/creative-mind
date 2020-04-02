<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="registrationUserForm">
<head>

<%@ taglib
	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"
	prefix="csrf"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath(); 
%>
<script type="text/javascript"
	src="<%=contextPath%>/angularjs/angular.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/angularjs/registrationUserSevice.js"></script>

<%@include file="../common/taglib_includes.jsp"%>

<script type="text/javascript"
	src="<%=contextPath%>/angularjs/toastr.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource-tree/toastr.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

/*custom font*/
@import url(https://fonts.googleapis.com/css?family=Montserrat);

/*basic reset*/
* {
	margin: 0;
	padding: 0;
}

html {
	height: 100%;
	/*Image only BG fallback*/
	/*background = gradient + image pattern combo*/
	background: linear-gradient(rgba(196, 102, 0, 0.6),
		rgba(155, 89, 182, 0.6));
}

body {
	font-family: montserrat, arial, verdana;
}
/*form styles*/
#msform {
	width: 400px;
	margin: 50px auto;
	text-align: center;
	position: relative;
}

#msform fieldset {
	background: whitesmoke;
	border: 0 none;
	border-radius: 3px;
	box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.4);
	padding: 20px 30px;
	box-sizing: border-box;
	width: 119%;
	margin: 0 1%;
	/*stacking fieldsets above each other*/
	position: relative;
}
/*Hide all except first fieldset*/
#msform fieldset:not (:first-of-type ) {
	display: none;
}
/*inputs*/
#msform input, #msform textarea, #msform select {
	padding: 15px;
	border: 1px solid #ccc;
	border-radius: 3px;
	margin-bottom: 10px;
	width: 100%;
	box-sizing: border-box;
	font-family: montserrat;
	color: #2C3E50;
	font-size: 13px;
}
/*buttons*/
#msform .action-button {
	width: 100px;
	background: #27AE60;
	font-weight: bold;
	color: white;
	border: 0 none;
	border-radius: 12px;
	cursor: pointer;
	padding: 10px 5px;
	margin: 10px 5px;
}

#msform .action-button:hover, #msform .action-button:focus {
	box-shadow: 0 0 0 2px white, 0 0 0 3px #27AE60;
}
/*headings*/
.fs-title {
	font-size: 15px;
	text-transform: uppercase;
	color: #2C3E50;
	margin-bottom: 10px;
}

.fs-subtitle {
	font-weight: normal;
	font-size: 13px;
	color: #666;
	margin-bottom: 20px;
}
/*progressbar*/
#progressbar {
	margin-bottom: 30px;
	overflow: hidden;
	/*CSS counters to number the steps*/
	counter-reset: step;
}

#progressbar li {
	list-style-type: none;
	color: black;
	text-transform: uppercase;
	font-size: 15px;
	width: 33.33%;
	float: left;
	position: relative;
	margin-left: 16%;
}

#progressbar li:before {
	content: counter(step);
	counter-increment: step;
	width: 20px;
	line-height: 20px;
	display: block;
	font-size: 10px;
	color: #333;
	background: white;
	border-radius: 3px;
	margin: 0 auto 5px auto;
}
/*progressbar connectors*/
#progressbar li:after {
	content: '';
	width: 100%;
	height: 2px;
	background: white;
	position: absolute;
	left: -50%;
	top: 9px;
	z-index: -1; /*put it behind the numbers*/
}

#progressbar li:first-child:after {
	/*connector not needed before the first step*/
	content: none;
}
/*marking active/completed steps green*/
/*The number of the step and the connector before it = green*/
#progressbar li.active:before, #progressbar li.active:after {
	background: #27AE60;
	color: white;
}

.asterisk::-webkit-input-placeholder {
	color: #f00;
}

.asterisk:-moz-placeholder {
	color: #f00;
	opacity: 1;
}

.asterisk::-moz-placeholder {
	color: #f00;
	opacity: 1;
}

.asterisk:-ms-input-placeholder {
	color: #f00;
}

.mlink{

padding-left: 10px; 
}
.mpadding{

padding-top: 20px; 
}
</style>
</head>

<body ng-controller="registrationUserCtrl">
	
	<div class="form-group mpadding">
	<div class="col-sm-12">
	<div class="col-sm-4"></div>
	<div class="col-sm-5"></div>
	<div class="col-sm-3">
	                    <i class="fa fa-hand-o-right"   aria-hidden="true"></i><m2 class="mlink">User Manual:How to consume Web Service</m2><m2 class="mlink"><a href="downloadDoc.do?fileType=U"><i class="fa fa-download" aria-hidden="true"></i></a></m2>
	</div>
	</div>
	</div>
	<div class="form-group">
	<div class="col-sm-12">
	<div class="col-sm-4"></div>
	<div class="col-sm-5"></div>
	<div class="col-sm-3">
	                    	 <i class="fa fa-hand-o-right"  aria-hidden="true"></i><m2 class="mlink">List of the Web Services offered by LGD</m2><m2 class="mlink"><a href="downloadDoc.do?fileType=W"><i class="fa fa-download" aria-hidden="true"></i></a></m2>
	</div>
	</div>
	</div>

	<form id="msform" name="form">
	
		
		
		<ul id="progressbar">
			<li class="active">Account Setup</li>
			<li>Authentication</li>
			<!-- <li>Personal Details</li> -->
		</ul>
		<div class="form-group">
			<span>Already registered <a style="color: red;"
				href="userLoginForm.do?<csrf:token uri='userLoginForm.do'/>"> <i
					class="" aria-hidden="true"></i><b>Sign-in</b>
			</a>
			</span> <br> <br>

		</div>
		<fieldset ng-if="account">




			<h2 class="fs-title">Registration Form</h2>
			<h2 class="fs-title" style="text-align: right;">
				<span style="color: red; font-size: small">All fields are
					mandatory!</span>
			</h2>
			<!-- <span style="color: red;font-size: small" ng-show="form.name.$error.required" >Name is required!</span> -->
			<input type="text" maxlength="100"
				restrict-input="{type: 'lettersOnly'}"
				ng-model="userRegistration.name" autocomplete="off"
				placeholder="Name *" class="asterisk" name="name" required />
			<!-- <span style="color: red;font-size: small" ng-show="form.email.$error.required">Email_Id is required!</span> -->
			<input type="email" ng-pattern="/^[a-z]+[a-z0-9._-]+@[a-z]+\.[a-z.]{2,5}$/" maxlength="100"
				ng-model="userRegistration.email" autocomplete="off"
				placeholder="Email Id *" name="email" id="email" class="asterisk" required />
			<span style="color:Red; font-size: small" ng-show="form.email.$dirty&&form.email.$error.pattern">Email_Id is Invalid!</span>	
			
			<!-- <span style="color: red;font-size: small" ng-show="form.mobile.$error.required">Mobile Number is required!</span> -->
			<input type="text" restrict-input="{type: 'validPhoneCharsOnly'}"
				autocomplete="off" ng-model="userRegistration.mobile"
				class="asterisk" maxlength="10" minlength="10"
				placeholder="Mobile No. *" name="mobile" required /> <span
				style="color: red; font-size: small"
				ng-show="form.mobile.$error.minlength">mobile number is
				Invalid!</span> <label class="col-sm-12 control-label" for="sel1">Department/Organization</label><br />
			<label class="radio-inline col-sm-5"><input type="radio"
				ng-click="showCenterList()" ng-model="optradio" name="optradio">Center</label>
			<label class="radio-inline col-sm-5"><input type="radio"
				ng-click="showStateList()" name="optradio">State</label> <select
				ng-model="userRegistration.orgUnitCode" ng-if="centerFlag"
				ng-required="userRegistration.deptName==''">
				<option value="">Select Department</option>
				<option ng-repeat="canter in centerlist"
					value="{{canter.orgUnitCode}}">{{canter.orgUnitName}}</option>
			</select> <select ng-model="userRegistration.stateCode" ng-if="stateFlag"
				ng-required="userRegistration.stateDepartment==''"
				ng-change="getStateWiseOrgList(userRegistration.stateCode)">
				<option value="">Select State</option>
				<option ng-repeat="state in statelist" value="{{state.stateCode}}">{{state.stateNameEnglish}}</option>
			</select> <select ng-model="userRegistration.stateOrgUnitCode"
				ng-if="stateOrgL" ng-required="userRegistration.stateDepartment==''">
				<option value="">Select Organization</option>
				<option ng-repeat="organization in organizationList"
					value="{{organization.orgUnitCode}}">{{organization.orgUnitName}}</option>
			</select>

			<p>OR</p>
			<input type="text" ng-if="centerFlag" maxlength="100"
				autocomplete="off" ng-required="userRegistration.orgUnitCode==''"
				ng-model="userRegistration.deptName"
				ng-class="{'asterisk':userRegistration.orgUnitCode==''}"
				ng-disabled="!userRegistration.orgUnitCode==' '"
				placeholder="Enter department name *" /> <input type="text"
				ng-if="stateFlag" maxlength="100" autocomplete="off"
				ng-model="userRegistration.stateDepartment"
				ng-required="userRegistration.stateCode==''"
				ng-class="{'asterisk':userRegistration.stateCode==''}"
				ng-disabled="!userRegistration.stateCode==' '"
				placeholder="Enter department name *" />

			<button ng-if="otpSend" ng-click="generateOTP()"
				ng-disabled="!form.$valid" name="next" class="btn btn-success">Generate
				OTP</button>
			<input ng-if="otp" type="text" restrict-input="{type: 'digitsOnly'}"
				autocomplete="off" maxlength="6"
				ng-model="userRegistration.generatedOTP"  name="OTP"
				placeholder="Enter OTP" />

			<div class="alert alert-danger fade in" ng-if="errorMsg">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Error!</strong>
				{{msg}}.
			</div>


			<button ng-if="otp" ng-disabled="!form.$valid" ng-click="saveData()"
				class="btn btn-success">Submit</button>
			<button type="button" name="Submit3" id="btn1" class="btn btn-danger"
				onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';">
				Close <i class="fa fa-times-circle"></i>
			</button>
			<!-- <div ng-if="otp">
			<span></span><button  ng-onclick ="reGenerateOTP()" > Resend OTP</button></span>
			
			</div> -->
			<br>
			
			<%--  <a style="color: red;margin-left: 40%;" href="userLoginForm.do?<csrf:token uri='userLoginForm.do'/>">
	            										<i class="fa fa-sign-in" aria-hidden="true"></i>Already registered , Sign-in
 	                                 		</a> --%>
		</fieldset>
	</form>
</body>
</html>