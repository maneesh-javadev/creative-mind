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

<script type="text/javascript"
	src="<%=contextPath%>/angularjs/toastr.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource-tree/toastr.css">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/css/bootstrap-dialog.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/js/bootstrap-dialog.min.js"></script>
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
	margin-bottom: 35px;
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
	padding: 9px 5px;
	margin: 15px 7px;
}

#msform .action-button:hover, #msform .action-button:focus {
	box-shadow: 0 0 0 2px white, 0 0 0 3px #27AE60;
}
/*headings*/
.fs-title {
	font-size: 15px;
	text-transform: uppercase;
	color: #2C3E50;
	margin-bottom: 35px;
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

.input-container {
	display: -ms-flexbox; /* IE10 */
	display: flex;
	width: 100%;
	margin-bottom: 15px;
}

.icon {
	padding: 10px;
	background: dodgerblue !important;
	color: white;
	min-width: 50px;
	text-align: center;
	height: 44px !important;
}

.input-field {
	width: 100%;
	padding: 10px;
	outline: none;
	padding: 12px;
}

.inner-addon {
	position: relative;
}

/* style icon */
.inner-addon .glyphicon {
	position: absolute;
	padding: 10px;
	pointer-events: none;
}

/* align icon */
.left-addon .glyphicon {
	left: 0px;
}

.right-addon .glyphicon {
	right: 0px;
}

/* add padding  */
.left-addon input {
	padding-left: 30px;
}

.right-addon input {
	padding-right: 30px;
}
</style>
</head>
<body ng-controller="registrationUserCtrl">
	<form id="msform" name="form">
		<fieldset ng-if="account">
			<h2 class="fs-title">
				<label>Login</label>
			</h2>
			<div class="alert alert-danger fade in" ng-if="errorMsg">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Error!</strong>
				{{msg}}.
			</div>

			<div class="row">
				<div class="form-group">
					<div class="col-sm-12">
						<div class="input-container">


							<input type="{{inputType}}" name="mobile" style="padding: 12px;"
								ng-model="userLogin.mobile" class="input-field"
								maxlength="{{maxLength}}" ng-change="checkForMaxLength()"
								placeholder="Email Address or Phone number" name="name" required />

						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-sm-12">
						<div class="input-container">

							<input type="password" restrict-input="{type: 'digitsOnly'}"
								style="padding: 12px;" class="input-field" maxlength="6"
								ng-model="userLogin.generatedOTP" name="OTP" placeholder="OTP" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<!-- <button  type="button" ng-click="loginForm()" ng-disabled ="!form.$valid" class="next action-button">Login <i class="fa fa-sign-in" aria-hidden="true"></i></button> -->
				<button ng-disabled="!form.mobile.$valid" style="margin-left: -1%;"
					ng-click="reGenerateOTP()" class="btn btn-success">
					Generate OTP <i class="fa fa-sign-in" aria-hidden="true"></i>
				</button>

				<button ng-disabled="!form.$valid" ng-click="loginForm()"
					style="margin-left: 7%;" class="btn btn-primary">
					Login <i class="fa fa-sign-in" aria-hidden="true"></i>
				</button>
				<button type="button" name="Submit3" id="btn1"
					style="margin-left: 7%;" class="btn btn-danger"
					onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';">
					Close <i class="fa fa-times-circle"></i>
				</button>
			</div>
		</fieldset>
	</form>
</body>
</html>