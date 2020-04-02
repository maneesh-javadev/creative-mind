<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><spring:message code="e.panchayati" htmlEscape="true"/></title>
<link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css"/>

<script src="js/globalHelpMessage.js" type="text/javascript"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="maincontainer" class="resize">
	<div id="headerwrap">
		<tiles:insertAttribute name="header" ignore="true"  />
	</div>
	<div class="clear"></div>
	<div id="content">
		<div id="leftpnl">
			<table width="100%" >
				<tr>
					<td valign="top" class="tblclear ">
						<table width="100%" class="tblbgclr">
							
						</table>
						</td>
								<td width="100%" valign="top" class="tblclear">
								<div id="rightpnl"><tiles:insertAttribute name="body" ignore="true" /></div>
						</td>
				</tr>
			</table>
		</div>
			
	</div>
	<div class="clear"></div>
	<div id="footer"><tiles:insertAttribute name="footer" ignore="true"  /></div>
</div>
</body>
</html>
