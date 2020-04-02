<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-size: 15px;
}
#leftpnl{
display:none;
}
</style>
</head>
<body>
	<div id="success" style="height: 550px;">
		<table width="100%" >
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
				
				<td><h2>Error Code:<%=request.getAttribute("errorLogId") %></h2></td>
			
			</tr>
			<tr>
				<td><div align="left" class="error"><h2><c:out value="${message}" escapeXml="false"></c:out></h2></div></td>
			</tr>
			<tr>
				<td>
					<h2>Something went wrong while displaying this webpage. To continue, press Home button above or contact your administrator!!</h2></td>
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
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
</body>
</html>
