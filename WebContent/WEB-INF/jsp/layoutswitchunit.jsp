<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" ignore="true" /></title>
<link href="css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/doctextsizer.js"></script>

<!-- <link href="resources-localbody/css/localbody.css" rel="stylesheet" type="text/css" /> -->
<!-- 
<link href="css/panchayat_green.css" rel="alternate stylesheet" type="text/css" media="screen" title="green-theme" />
<link href="css/panchayat_peach.css" rel="alternate stylesheet" type="text/css" media="screen" title="peach-theme" />
<link href="css/panchayat_blue.css" rel="alternate stylesheet" type="text/css" media="screen" title="blue-theme" />
<link href="css/panchayat_new_theme.css" rel="alternate stylesheet" type="text/css" media="screen" title="new-theme" /> -->
<script src="js/styleswitch.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/ddaccordion_action.js"></script>
<script type="text/javascript" src="js/collapse_pnl.js"></script>
<script type="text/javascript" src="js/animatedcollapse.js"></script>
<script type="text/javascript" src="js/contentscroller.js"></script>
</head>


<body>
<div id="maincontainer" class="resize">

<div id="headerwrap"><tiles:insertAttribute name="header" ignore="true"  /></div>
<div class="clear"></div>
<div id="content">
<%-- <div id="leftpnl" class="inner_left_col"><tiles:insertAttribute name="menu" ignore="true"  /></div> --%>
<div id="rpnls" class="inner_right_col"><tiles:insertAttribute name="body" ignore="true"  /></div>
 
</div>
<div class="clear"></div>
<div id="footer"><tiles:insertAttribute name="footer" ignore="true"  /></div>
</div>
</body>
</html>