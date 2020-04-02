<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" /> --%>
<!-- Menu  -->
<script src="js/styleswitch.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/ddaccordion_action.js"></script>
<script type="text/javascript" src="js/collapse_pnl.js"></script>
<script type="text/javascript" src="js/animatedcollapse.js"></script>
<script type="text/javascript" src="js/contentscroller.js"></script>
<script type="text/javascript" src="js/doctextsizer.js"></script>




<!-- common   -->
<script type="text/javascript" src="js/jquery.js"></script>



<!-- Validation   -->
<script src="js/validation.js" type="text/javascript"></script>
<script type="text/javascript" src="js/trim-jquery.js"></script>
<script src="js/util.js" type="text/javascript"></script>



<!-- datepicker   -->

<script src="js/calender-js/jquery.ui.core.js"></script>
<script src="js/calender-js/jquery.ui.widget.js"></script>
<script src="js/calender-js/jquery.ui.datepicker.js"></script>
<script src="js/calender-js/calender.js"></script>





<!-- css   -->
<link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css"/>
<link rel="stylesheet" href="css/profile_css.css" type="text/css"/>
<link href="css/calender.css" rel="stylesheet" type="text/css" />



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title><tiles:getAsString name="title" ignore="true" /></title>


</head>
<body>

<!-- To blur the background while processing dwr -->
<div class="faded_div process"></div>
<div  class="popup_block_div process">
		<img alt="" src="images/loadingAnimation.gif"/>								
</div>







<div id="maincontainer" class="resize">
	<div id="headerwrap">
		<div id="topnav">
<!-- 		Language -->
				<tiles:insertAttribute name="language" ignore="true"  />
		</div>
<div class="clear"></div>





<!-- Header -->
<tiles:insertAttribute name="header" ignore="true"  />

</div>



<div class="clear"></div>
<div id="content">
 	<div id="leftpnl">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top" bgcolor="#F2DA84" class="tblclear tblrtbrdr">
			 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblbgclr">
    	        <tr>
        	      <td id="span_1_0" class="tblclear coltab"><div class="collapse_menu"></div></td>        	  
            	  <td class="tblclear resize">
					<!-- 				  Menu -->
					<tiles:insertAttribute name="menu" ignore="true"  />
				  </td>
            	  <td valign="top" class="tblclear" id="span_2" style="width:10px; padding-top:10px; padding-left:5px">
            	  <img id="collapseImg" height="28" width="10" border="0" align="left" alt="" src="images/collapse.jpg" onclick="hideWrraper();" /></td>
            	</tr>
          	</table>
          </td>
          <td width="100%" valign="top" class="tblclear">
				<!--           content -->
          <tiles:insertAttribute name="body" ignore="true"  />
          </td>
        </tr>
      </table>
	</div>
</div>	
<div class="clear"></div>





<div id="footer"> 
<!-- 	Footer -->
	<tiles:insertAttribute name="footer" ignore="true"  /> 
	
</div>

</div>
</body>
</html>
