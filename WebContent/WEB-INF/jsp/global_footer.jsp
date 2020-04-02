<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"	prefix="csrf"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body>

	  <table width="100%" class="tbl_no_brdr">
      <!-- Cititzen Section Bar show -->
				<tr>
					<td colspan="4" class="header" valign="middle" align="center">
						
						<table width="80%" align="center">
						<tr>
						<td class="citizen_menu" align="center">
							<form id="globalFooterForm" name="globalFooterForm" method="get" action="#">
								<a href="citizenFeedback.do?<csrf:token uri='citizenFeedback.do'/>">
								   					<spring:message htmlEscape="true" code="Label.citizen.feedback"  text="Feedback"/>
								  </a>
								  <a href="contactUs.do?<csrf:token uri='"contactUs.do'/>">
								   					<spring:message htmlEscape="true" code="Label.citizen.contactus"  text="Contact Us"/>
								  </a>
								  <a href="siteMap.do?<csrf:token uri='"siteMap.do'/>">
								   					<spring:message htmlEscape="true" code="Label.citizen.sitemap"  text="Site Map"/>
								  </a>
							</form>
						</td>
						<td class="citizen_menu_tile"></td>
						</tr>
						</table>
										
						   			
						
							
						
					</td>
				</tr>
				<!-- End of Cititzen Section Bar show -->
				
        <tr>
          <td colspan="4" class="fotbrdr" height="4"></td>
        </tr>
        <tr>
          <td width="161" class="btmlogospace"><img src="images/e_governance_logo.jpg" title="LGD V7.17.04.2012" width="161" height="38" /></td>
          <td width="93" class="btmlogospace"><img src="images/panchayatilogo.jpg" width="93" height="38" /></td>
          <td class="btmlogospace">
						<a target="_blank" href="http://india.gov.in/" title="http://india.gov.in, The National Portal of India : External website that opens in a new window">
							<img height="38" width="158" src="images/india_govlogo.jpg" alt="http://india.gov.in, The National Portal of India : External website that opens in a new window"/>
						</a>
		 </td>	
          <td align="right" class="btmlogospace">
          Content on this website is updated and managed by the Ministry of Panchayati Raj. <br />
          In case of any problem please send a mail at <a href="">lgdirectory at googlegroups dot com</a> &nbsp; <br />
		  Site hosted and maintained by National Informatics Centre (NIC).
		   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		  </td> 
          
        </tr>
      </table>
      <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
      <form id="ajaxErrorFrom" action="badRequest.htm">
      </form>
 
</body>
</html>