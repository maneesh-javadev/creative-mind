<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	$(document).ready(function(){
		alert("alert in nonscript");
		$(location).attr('href', 'welcome.do');
	});
</script>
</head>
<body>
	<p>
	JavaScript must be enabled in order for you to use Local Government Directory. However, it seems JavaScript is either disabled or not supported by your browser.<br/> 
	To use standard view, enable JavaScript by changing your browser options, then <a href="welcome.do">try again</a>.
	</p>
</body>
</html>