<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lable.badrequesterror</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/panchayat_main.css" rel="stylesheet" type="text/css" />
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-size: 15px;
}
</style>
</head>
<body>
	<div id="success" >
		<table width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="font: bold; font-size: 30px;">
					<h1>
						Ooops!!! <img src='images/error.jpg' height="40"
							style="vertical-align: middle;"></img>
					</h1>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><h2></h2></td>
			</tr>
			<tr>
				<td><h2>Bad Request Detected, may be you have not a Authorize User- <a href="<%=getServletContext().getInitParameter("URL")%>/welcome.do">Home</a></h2></td>
			</tr>
			<tr>
				<td>
					<h2>To continue, press Home or contact your administrator!!</h2></td>
			</tr>
		</table>
	</div>
</body>
</html>