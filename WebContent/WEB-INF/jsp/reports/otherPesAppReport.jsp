<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<style>
@CHARSET "ISO-8859-1";

/* Default Presets */
div,h1, h2, h3, h4, h5, h6, p
 {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
 
footer_other{
	display: block;
}

ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}

em{
color:#333333;
font-style:italic;
}
.clear{
	clear:both;
	font-size:0;
	line-height:0;
	height:0;
}
input[type="checkbox"], input[type="radio"]{
	vertical-align:middle;
}
p{
line-height:20px;
margin:15px 0 0 0;
}
/* a{
color:#940027;
text-decoration:none;
cursor:pointer;
transition: all ease-in-out 0.3s;
} */
/*a:hover{
text-decoration:underline;
}*/


/* Heading Styles */
h1{
color:#555555;
font-size:18px;
line-height:20px;
margin-bottom:15px;
text-transform:uppercase;
}
h1 span, h2 span{
color:#9d0000;
}
h2{
color:#555555;
font-size:18px;
line-height:20px;
margin-bottom:15px;
padding-bottom:15px;
text-transform:uppercase;
background:url(../images/dbbl-border.png) left bottom repeat-x;
position:relative;
}
h3{
color:#fff;
font-size:18px;
line-height:20px;
margin-bottom:0;
text-transform:uppercase;
}
.footer_other h3{
margin-bottom:15px;}

/* Background Styles */
.white{
background:#fff;
}
.grey{
background:#f1f1f1;
}


.wrapper_other{

}
.container{
padding:0 15px;
margin:0 auto;
width:99%;
}

/* Footer Stylings */
.footer_other {
background:url(./images/footer-bg.jpg) #1a1a1a left top repeat;
padding:30px 0 0 0;
}
.footer_other .block{
width: 23.197%; /*245*/
padding-left:30px;
float:left;
}
.footer_other .block:first-child{
padding-left:0;
}
ul.linklist li{
background:url(./images/arrow-bullet.png) left 10px no-repeat; 
padding:3px 0 3px 6px;
}
ul.linklist li a{
font-size:13px;
color:#666666;
}
ul.linklist li a:hover{
color:#999;
}
.copyright{
height:50px;
line-height:50px;
text-align:center;
color:#756e5c;
font-size:12px;
background:none repeat scroll 0 0 #000000;
background:#171717\9;
margin:0px 0 0 0;
}
</style>

</head>
<html>
<%
	
int tlanguageCode = 1;
if (request.getAttribute("DefaultLanguageCode")  != null) {
		 String languageCodeStr="";
		languageCodeStr = request.getAttribute("DefaultLanguageCode").toString();
		if(languageCodeStr!=null && languageCodeStr.trim()!=""){
			tlanguageCode=Integer.parseInt(languageCodeStr);
		}
	}
	String tplantype = "";
	if (request.getAttribute("plantype") != null) {
		tplantype = (String) request.getAttribute("plantype");
	}
	String tplanYear = "";
	if (request.getAttribute("SelPlanYear") != null) {
		tplanYear = (String) request.getAttribute("SelPlanYear");
	}
	
	String ttypeName="";
	if (request.getAttribute("typeName") != null) {
		ttypeName = (String) request.getAttribute("typeName");
	}
	
	int tstateCode = 0;
	if (request.getAttribute("stateCode") != null) {
		tstateCode = Integer.parseInt(request.getAttribute("stateCode").toString());
	}
	String tlbType = "";
	if (request.getAttribute("lbType") != null) {
		tlbType = (String) request.getAttribute("lbType");
	}
	int tlbTypeCode = 0;
	if (request.getAttribute("lbTypeCode") != null) {
		tlbTypeCode = Integer.parseInt(request.getAttribute("lbTypeCode").toString());
	}
	int tlbCode = 0;
	if (request.getAttribute("lbCode") != null) {
		tlbCode = Integer.parseInt(request.getAttribute("lbCode")
				.toString());
	}
	String	tunitName ="";
	if (request.getAttribute("unitName") != null) {
		tunitName = request.getAttribute("unitName").toString();
	}
	
	String otherInfotitle="";
	if(request.getAttribute("otherInfotitle") != null) {
		otherInfotitle = request.getAttribute("otherInfotitle").toString();
	}
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script language = javascript>

</script>
</head>
<body>
<div class="wrapper_other"> <!-- Main container starts -->
	
		<div class="footer_other">  <!-- Footer starts here -->
		<div align="center"><h3>Other Information <%=otherInfotitle %> </h3></div>
			<div class="container">
			
			<div class="block">
					
					<ul class="linklist">
					<li><a href="http://164.100.128.118/nppWebService/getPanchayatSite.do?lgdCode=<%=tlbCode%>&isState=n" target="_blank">Visit Panchayat Website</a></li>
					<li><a href="http://reportingonline.gov.in/ReportDataForExt.do?ReportMethod=getPlanStatusFormExt&sparam=<%=tstateCode%>&planType=A&languageId=1&lbCode=<%=tlbCode%>" target="_blank">Action Plan Report</a></li>
					<li><a href="http://assetdirectory.gov.in/showAssetListReportForm.htm?stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=<%=tlanguageCode%>" target="_blank">List of Assets</a></li>
								
					</ul>
			</div>
			<div class="block">
					
					<ul class="linklist">
					<li><a href="http://areaprofiler.gov.in/aboutUsIntgeratedPES.do?stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=<%=tlanguageCode%>" target="_blank">General Profile of Panchayat</a></li>
					<li><a href="http://reportingonline.gov.in/execStatusOfActionPlanIPA.htm?stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=<%=tlanguageCode%>" target="_blank">Execution Status of ActionPlan</a></li>
					<li><a href="http://socialaudit.gov.in/MoMReportBRDIntegrated?stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=<%=tlanguageCode%>" target="_blank">Meeting Management</a></li>
								
					</ul>
				</div>
			<div class="block">
					
					<ul class="linklist">
					<li><a href="http://areaprofiler.gov.in/viewelectedmemberReport.do?stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=<%=tlanguageCode%>" target="_blank">Elected Member Details</a></li>
					<li><a href="https://accountingonline.gov.in/daybookReportPage.do?method=dayBookIntegrated&stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=1" target="_blank">Day Book Report</a></li>
					
					
					
					
					</ul>
				</div>
				<div class="block">
					
					<ul class="linklist">
					<li><a href="http://areaprofiler.gov.in/manageofficialreportIntegrated.do?stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=<%=tlanguageCode%>" target="_blank">Panchayat Official Details</a></li>
					<li><a href="https://accountingonline.gov.in/reportforext.do?method=SchemeWiseAnnualReportForExt&stateCode=<%=tstateCode%>&lbCode=<%=tlbCode%>&languageId=1" target="_blank">Annual Receipt and Payment</a></li>
					
					</ul>
				</div>
				<div class="clear"></div>
				
				</div>
			
			

		</div> <!-- Footer ends here -->
	<p class="copyright">&copy; Ministry of Panchayati Raj. All rights reserved.</p>
	</div> <!-- Main container ends -->


</body>
</html>