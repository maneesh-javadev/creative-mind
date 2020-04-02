<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>
<title>LGD - Privacy Policy</title>
</head>
<body>

<section class="content">
    	<!-- Main row -->
   <div class="row" id="frmcontent">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title">Privacy Policy</h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
									                 
		                    <form:form class="form-horizontal"  method="POST">
		<p>
			Thanks for visiting website of Local Government Directory, and reviewing our privacy policy.

we collect no personal information, like names or addresses, when you visit our website. If you choose to provide that information to us, it is only used to fulfill your request for information.

We do collect some technical information when you visit to make your visit seamless. The section below explains how we handle and collect technical information when you visit our website.
		
		</p>
			
		<h4>Information collected and stored automatically</h4>
		
			<p>
			When you browse, read pages, or download information on this website, we automatically gather and store certain technical information about your visit. This information never identifies who you are. The information we collect and store about your visit is listed below:
			</p>
			
			<p>
			The Internet domain of your service provider(e.g. mtnl.net.in) andIP address(an IP address is a number that is automatically assigned to your computer whenever you're surfing the web) from which you access our website.
			The type of browser (such as Firefox, Netscape, or Internet Explorer) and operating system(Windows, Linux) used to access our site.	The date and time you accessed our site.
			The pages/URLs you have visited and
			If you have reached this website from another website, the address of that referring website.
			</p>
			
			<p>
			This information is only used to help us make the site more useful for you. With this date, we learn about the number of visitors to our site and the types of technology our visitors use. We never track or record information about individuals and their visits.
			</p>
			
			<h4>Cookies</h4>
			<p>When you visit some websites, they may download small pieces of software on you computer/browsing device known as cookies. Some cookies collect personal information to recognise your computer in the future. We only use non-persistent cookies or "per-session cookies".
			<br/><br/>
			Per-session cookies serve technical purposes, like providing seamless navigation through this website. These cookies do not collect personal information on users and they are deleted as soon as you leave our website. The cookies do not permanently record data and they are not stored on your computer's hard drive. The cookies are stored in memory and only available during an active browser session. Again, once you close your browser, the cookie disappears.
			</p>
			
			<h4>If you send us personal information</h4>
			<p>
			We do not collect personal information for any purpose other than to respond to you(for example, to respond to your questions or provide subscriptions you have chosen). If you choose to provide us with personal information - like filling out a Contact Us form, with an e-mail address or postal address, and submitting it to us through the website - we use that infromation to respond to your message, and to help you get the information you've requested. We only share the information you give us with another Government agency if your question relates to that agency, or as otherwise required by law.
			<br/><br/>
			Our website never collects information or creates individual profiles for commercial marketing. While you must provide and e-mail address for a localised response to any incoming questions or comments to us, we recommend that you do NOT include any other personal information.
			</p>
			
			<h4>Site Security</h4>
			<p>
			For site security purposes and to ensure that this service remains available to all users, this Government computer system employs commercial software programs to monitor network traffic to identify unauthorised attempts to upload or change information, or otherwise cause damage.
			Except for authorised law enforcement investigations, no other attempts are made to identify individual users or their usage habits. Raw data logs are used for no other purposes and are scheduled for regular deletion.
			Unauthorised attempts to upload information or change information on this service are strictly prohibited and may be punishable under the Indian IT Act.
			</p>
                    				
            <div class="box-footer">
              <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                    <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
			   </div>
			 </div>
	      </div>		    
        </form:form>
	 </div>
   </div>
 </section>
 </div>
</section>

</body>
</html>