<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib
	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"
	prefix="csrf"%>
<html xmlns="http://www.w3.org/1999/xhtml">


<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body>
	 <!--  Footer Starts -->
		<div id="footer">
			<div class="footer_links">
				<ul class="listing">
					<li><a	href="citizenFeedback.do?<csrf:token uri='"citizenFeedback.do'/>">
							<spring:message htmlEscape="true" code="Label.citizen.feedback"
								text="Feedback" /> </a>
					</li>
					<li><a href="contactUs.do?<csrf:token uri='"contactUs.do'/>">
							<spring:message htmlEscape="true" code="Label.citizen.contactus"
								text="Contact Us" /> </a>
					</li>
					<li><a href="siteMap.do?<csrf:token uri='"siteMap.do'/>">
							<spring:message htmlEscape="true" code="Label.citizen.sitemap"
								text="Site Map" /> </a>
					</li>
					<li><a href="copyRightPolicy.do?<csrf:token uri='"copyRightPolicy.do'/>">
							<spring:message htmlEscape="true" code=""
								text="Copyright Policy" /> </a>
					</li>
					<li><a href="privacyPolicy.do?<csrf:token uri='"privacyPolicy.do'/>">
							<spring:message htmlEscape="true" code=""
								text="Privacy Policy" /> </a>
					</li>
					<li><a href="termsconditions.do?<csrf:token uri='"termsconditions.do'/>">
							<spring:message htmlEscape="true" code=""
								text="Terms And Conditions" /> </a>
					</li>
				</ul>
			</div>
			<div class="copyrights">
				<div class="col_left">
					<ul class="listing">
						<li><img src="images/e_governance_logo.jpg" alt="National e-Governance Plan" width="161" height="38" />
						</li>
						<li><img src="images/panchayatilogo.jpg" alt="Panchayati Raj" width="93" height="38" />
						</li>
						<li><a target="_blank" href="http://india.gov.in/"
							title="http://india.gov.in, The National Portal of India : External website that opens in a new window">
								<img height="38" width="158" src="images/india_govlogo.jpg"
								alt="http://india.gov.in, The National Portal of India : External website that opens in a new window" />
						</a>
						</li>
					</ul>
				</div>
				<div class="col_ryt">
					<p>
						Site is designed, hosted and maintained by <a target="_blank" href="http://www.nic.in/"> National Informatics
						Centre</a> . Contents on this website are  owned,updated and<br> managed by the Panchayats and State Panchayati Raj Department as a part 
						of e-Panchayat MMP of Ministry of Panchayati Raj.<br>Site best viewed on Internet Explorer (IE)-8 &amp; above, Mozilla Firefox-11 &amp; above&nbsp; ${display_version}
					</p>
				</div>
				<div class="clear"></div>
			</div>

		</div>
		<!--  Footer ends -->
      
      
      <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
      <form id="ajaxErrorFrom" action="badRequest.htm">
      </form>
</body>
</html>