<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	
	
<script type='text/javascript'>
function validate() 
{ 
	
	var inputext= document.getElementById("user").value;

	 if(inputext=="")
		{ 
			alert('Please enter userName and password');
			return false; 
		} 
	return true; 
	} 
</script>
</head>
<body>


<%-- <center style="color:red;">
<c:if test="${!empty login}">
${login}
</c:if>
</center>  --%>

<center style="color:red;">
<c:if test="${!empty param.session_expired}">
${param.session_expired}
</c:if>
</center>




<div id="homepagebg">
			<div id="new_homelogin">
				<form:form method="post" action="login.htm" modelAttribute="loginForm" onsubmit="validate()">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="login.htm"/>"/>
				  <table width="100%" class="tbl_no_brdr">
                    <tr>
                      <td>User Name</td>
                      <td><label>
                       <form:input path="username" id="user" class="frmfield" style="width:150px"/>
                      
                      </label></td>
                    </tr>
                    <tr>
                      <td height="5" colspan="2"></td>
                    </tr>
                    <tr>
                      <td>Password</td>
                      <td><label>
                       <form:password path="password"  id="pass"   class="frmfield" style="width:150px" />
                      </label></td>
                    </tr>
                    <tr>
                      <td height="5" colspan="2"></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td><label>
                        <input name="Submit" type="submit" class="new_btn" value="Submit" />
                      </label></td>
                    </tr>
                    <tr>
                      <td width="31%">&nbsp;</td>
                      <td width="69%" height="25" class="new_wtlink"><a href="#">Forgot Password</a></td>
                    </tr>
                  </table>
				</form:form>
			</div>
		</div>
  
	<div class="clear"></div>
  <div id="contentin">
    <table width="100%" class="tbl_no_brdr">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td width="240" valign="top">
			<div class="new_pnlstyl">
				<div class="new_pnlbghd">
					<div class="new_pnlctzn">Citizen Section</div>
				</div>
				<div class="clear"></div>
				<div class="new_listmenu">
				<ul>
					<li><a href="">Check your entitlement</a></li>
					<li><a href="viewlistofpanchayatheadbywoman.htm">Report</a></li>
					<li><a href="">Find Details of given service</a></li>
					<li><a href="">Track application status</a></li>
					<li><a href="">Check Grievance Policy of state</a></li>
					<li><a href="">View Public service delivery act of State</a></li>
				</ul>
				</div>
				<div class="clear"></div>
				<!-- <div class="new_more"><a href="#">more...</a></div> -->
		  </div>
		  <div class="clear"></div>
		  <div class="new_pnlstyl">
				<div class="new_pnlbghd">
					<div class="new_pnlsprtdoc">Supporting Documentation</div>
				</div>
				<div class="clear"></div>
				<div class="new_supdoc">
				<ul>
					<li>CBT</li>
					<li>Presentations</li>
					<li>Brochure</li>
				</ul>
			</div>
		  </div>
	  </td>
        <td valign="top" class="new_tblcntent">
			<div class="new_pnlstyl">
				<div class="new_pnlbghd">
					<div class="new_pnlabout">About Area Profiler</div>
				</div>
				<div class="clear"></div>
				<div class="new_pnlparatxt">Area Profiler is one of the modules of Panchayat Enterprise Suite (PES) being prepared as a part of ePanchayat Mission Mode Project (http://ePanchayat.gov.in). Area Profiler envisages facilitating the Local Governments Bodies to manage their socio-economic information, demographical information, public infrastructure and amenities, election and elected representatives and other officials working in Local Governments effectively with proper records to facilitate tracking process subsequently.<br/><br/>
											It will act as a centralized database and the information would be available to other e-PRI applications for effective use.
											<br/><br/>
										
				<div class="new_pnlbghd">
				<div class="new_pnlabout">The Area Profiler Application</div>
				</div>
											
												Facilitates to Local Govt. Bodies to Record and maintain socio-economic, Public Infrastructure, Tourist Places and Neighbouring local bodies details.  
												<br/>
												Facilitate to Maintain Family Register for each Village Panchayat and helps to track the Demographic Condition based on many important Parameters.
												<br/>
												Facilitate to maintain State-wide General Election and By Election Process.
												<br/>
												Facilitate to record and maintain the BSLVD Datasets Proposed by MOSPI.
				</div>		
				
				<div class="clear"></div>
				<!-- <div class="new_more"><a href="#">read more ...</a></div> -->
		  </div>
		  <div class="new_pnlstyl">
				<div class="new_pnlbghd">
					<div class="new_pnlfaq">FAQ</div>
				</div>
				<div class="clear"></div>
				<div class="new_listfaq">
				<ul>
					<li><a href="">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.</a></li>
					<li><a href=""> The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.</a></li>
					<li><a href=""> Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)</a></li>
				    <li><a href=""> There are many variations of passages of Lorem Ipsum available, but the   majority have suffered alteration in some form, by injected humour, or   randomised words which don't look even slightly believable. If you are   going to use a passage of Lorem Ipsum, you need to be sure there isn't   anything embarrassing hidden in the middle of text.</a></li>
				</ul>
			</div>
				<div class="clear"></div>
				<!-- <div class="new_more"><a href="#">view all...</a></div> -->
		  </div>
		</td>
        <td width="240" valign="top"><div class="new_pnlstyl">
				<div class="new_pnlbghd">
					<div class="new_pnlannonce">Announcement</div>
				</div>
				<div class="clear"></div>
				<div class="new_pnlcommbg">
					<div class="new_pnlcmnhd">New Services</div>
				</div>
				<div class="clear"></div>
				<div class="new_listmenu">
				<ul>
					<li><a href="">Birth Certificate</a></li>
					<li><a href="">Death Certificate</a></li>
					<li><a href="">Marriage Certificate</a></li>
					<li><a href="">SC/ST Certificate</a></li>
				</ul>
				</div>
				<div class="clear"></div>
				<div class="new_more"><a href="#">view all...</a></div>
				<div class="clear"></div>
				<div class="new_pnlcommbg">
					<div class="new_pnlcmnhd">Change in AreaProfiler Rules</div>
				</div>
				<div class="new_pnlparatxt">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</div>
				<div class="clear"></div>
				<div class="new_more"><a href="#">view all...</a></div>
				<div class="clear"></div>
				<div class="new_pnlcommbg">
					<div class="new_pnlcmnhd">Orders / Circulars</div>
				</div>
				<div class="new_pnlparatxt">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</div>
				<div class="clear"></div>
				<div class="new_more"><a href="#">view all...</a></div>
		  </div></td>
      </tr>
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
    </table>
  </div>		
	<div class="clear"></div>



</body>

</html>
