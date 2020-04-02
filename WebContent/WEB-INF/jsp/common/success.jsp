<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
	
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
	<script>
	$(function() {		
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
		$( "#dialog-message" ).dialog({
			modal: true,
			buttons: {
				Ok: function() {
					document.getElementById('form1').action = "home.htm";
					document.forms['form1'].submit();
					$( this).dialog( "close" );
				}
			}
		});
	});
	</script>
</head>


<body  >
<form id="form1" action="">
<div class="demo">

<div id="dialog-message" title="Message">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		<esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
	</p>
	<p>
		<!-- Currently using <b>36% of your storage space</b>. -->
	</p>
</div>
</div><!-- End demo -->

<table width="100%" border="3" >
  <tr>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td>&nbsp;</td>
  </tr>
    <tr >
    <td>&nbsp;</td>
  </tr>
  <tr>
   <%--  <td><div align="center" style="font-weight:bold;font-size:18px">${msgid}</div></td> --%>
  </tr>
</table>
</form>
</body>
</html>
