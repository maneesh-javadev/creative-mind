<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@include file="../common/taglib_includes.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js" ></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript">
var checkedRadio;
function ClearRd(chkRd, idname, chkRd1,errorid) {		

	var chkRd = document.getElementById(chkRd);
	var chkRd1 = document.getElementById(chkRd1);

	if (chkRd.checked) {
		document.getElementById(idname).disabled = false;
		document.getElementById(idname).style.visibility = "visible";
		document.getElementById(idname).style.display = 'block';
		document.getElementById(idname).value = "";

	} else if (chkRd1.checked) {
		document.getElementById(idname).disabled = true;
		document.getElementById(idname).style.visibility = "hidden";
		document.getElementById(idname).style.display = 'none';
		document.getElementById(errorid).style.visibility = "hidden";
		document.getElementById(errorid).style.display = 'none';
		
		 
		document.getElementById(idname).value = "";
	}
}

	function loadPage()
	{	
		/*  alert("dsfsd");  */
		ClearRd('pcseperatemapserver','pcbaseUrl','pcuploadmap');
		ClearRd('acseperateserver','acbaseurl','acuploadserver');
	
	}
	/* function doSubmit()
	{
		document.getElementById('savebtn').disabled=true;
		document.forms['form1'].submit();
	} */
	
	
	function validAll(val)
	{
	  //alert("validate");
		flag=true;
		
	
		
		
				
			
				var obj=document.getElementById('pcuploadmap');	
				var obj1=document.getElementById('acuploadserver');	
				var patternu;
				patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
				
			//alert("mid"+val);
				if(val == true)
					{
					if(obj.checked)
					{
						document.getElementById('baseUrlParliament').style.visibility = 'hidden';
						document.getElementById('baseUrlParliament').style.display = 'none';
						 document.getElementById('errorbaseUrlpc').style.visibility = 'hidden';	
						 document.getElementById('errorbaseUrlpc').style.display = 'none';
						
					}
				else
					{
					//alert("me");
					//alert(document.getElementById('baseUrl_' + pos).value);
					 if(document.getElementById('baseUrlParliament').value=="")
						 {
						 //alert("inside");Base URL is Required
						  document.getElementById('errorbaseUrlpc').innerHTML="Base URL is Required";
						 document.getElementById('errorbaseUrlpc').style.visibility = 'visible';	
						 document.getElementById('errorbaseUrlpc').style.display = 'block';
						
						 flag=false;
						   
						 }
					 else
						 {
						 if(!patternu.test(document.getElementById('baseUrlParliament').value))
						 {
							 document.getElementById('errorbaseUrlpc').innerHTML="Base URL is not proper format";
							 document.getElementById('errorbaseUrlpc').style.visibility = 'visible';	
							 document.getElementById('errorbaseUrlpc').style.display = 'block';
							 flag=false;
						 }
						 else
							 {
							 document.getElementById('errorbaseUrlpc').style.visibility = 'hidden';	
							 document.getElementById('errorbaseUrlpc').style.display = 'none';
							 }
							
						 
						 }
					 
					}
					
					if(obj1.checked)
					{
						document.getElementById('baseUrlAssembly').style.visibility = 'hidden';
						document.getElementById('baseUrlAssembly').style.display = 'none';
						 document.getElementById('errorbaseUrlac').style.visibility = 'hidden';	
						 document.getElementById('errorbaseUrlac').style.display = 'none';
						
					}
				else
					{
					//alert("me");
					//alert(document.getElementById('baseUrl_' + pos).value);
					 if(document.getElementById('baseUrlAssembly').value=="")
						 {
						 //alert("inside");Base URL is Required
						  document.getElementById('errorbaseUrlac').innerHTML="Base URL is Required";
						 document.getElementById('errorbaseUrlac').style.visibility = 'visible';	
						 document.getElementById('errorbaseUrlac').style.display = 'block';
						
						 flag=false;
						   
						 }
					 else
						 {
						 if(!patternu.test(document.getElementById('baseUrlAssembly').value))
						 {
							 document.getElementById('errorbaseUrlac').innerHTML="Base URL is not proper format";
							 document.getElementById('errorbaseUrlac').style.visibility = 'visible';	
							 document.getElementById('errorbaseUrlac').style.display = 'block';
							 flag=false;
						 }
						 else
							 {
							 document.getElementById('errorbaseUrlac').style.visibility = 'hidden';	
							 document.getElementById('errorbaseUrlac').style.display = 'none';
							 }
							
						 
						 }
					 
					}
					
			}
		
		//alert(flag);
		return flag;
		
		
	}

	
	if ( window.addEventListener ) { 
	    window.addEventListener( "load", loadPage, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", loadPage );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = loadPage;
	 }
</script>

<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body ><!--  onload="lo adPage();"> -->

	<table width="100%"  class="tbl_no_brdr">
		<tr>
			<td valign="top" class="tblclear">				
				<div class="clear"></div>
				<div id="frmcontent">
					<div class="frmhd">
					
					        <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
							<ul id="showhelp" class="listing">
								<%-- these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				 				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
							</ul>
						<%-- <table width="100%" class="tbl_no_brdr">
							<tr>
								<td><label> <spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></label> 
								</td>
								 <td>&nbsp;</td>
                               <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
                                <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td>
							</tr>
						</table> --%>
					</div>
					<div class="frmpnlbrdr">
							
							<form:form action="typeOfConstituncy.htm" id="form1" method="POST" commandName="config">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="typeOfConstituncy.htm"/>"/>
							<div class="frmpnlbg">
							<div class="frmtxt">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></div>
									<table width="100%" class="tbl_no_brdr">
										<%-- <tr>
											<td colspan="8">
											 <label> <spring:message htmlEscape="true"  code="Label.TYPECONSTITUENCY"></spring:message></label> 
											</td>
										</tr> --%>
										<tr>
										    <td width="3%" rowspan="4">&nbsp;</td>
											<td width="188">
											<label> <spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCY"></spring:message></label> 
											<form:hidden path="constituencyTypeParliament" id="parliamentconstituencytype" value='P' />
											</td>
											<td width="24" align="right"><label>
											 <form:radiobutton path="ismapuploadParliament" id="pcuploadmap" value="true" checked="checked"
														onclick="ClearRd('pcseperatemapserver','pcbaseUrl','pcuploadmap','errorbaseUrlpc');" />
											</label></td>
											<td width="94" class="tblclear">
											<label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label> 
											</td>
											<td width="3%" align="right"><label> 
											<form:radiobutton path="ismapuploadParliament" id="pcseperatemapserver" value="false"
														onclick="ClearRd('pcseperatemapserver','pcbaseUrl','pcuploadmap','errorbaseUrlpc');" />
											</label></td>
											<td width="159" class="tblclear">
											<label> <spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label> </td>
											
											
											
											<td width="25%">
											<label id="pcbaseUrl" style="visibility:hidden;">
											<spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message>  
											<form:input path="baseUrlParliament"  id="baseUrlParliament" class="frmfield" maxlength="100"/> </label></td>
											<td><div id="errorbaseUrlpc" style="color: red;" ></div></td>
											<%-- <td><form:errors path="baseUrlParliament" cssClass="error" id="baseUrlParliamentErr" /></td> --%>
											
											
										</tr>
										<tr>
											<td><label> <spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCY"></spring:message></label> 
											<form:hidden path="constituencyTypeAssembly" id="assemblyconstituencytype" value='A' /></td>
												<td align="right"><label> 
												<form:radiobutton path="ismapuploadAssembly" id="acuploadserver" value="true" checked="checked"
														onclick="ClearRd('acseperateserver','acbaseurl','acuploadserver','errorbaseUrlac');" />
											</label></td>
											<td class="tblclear">
											<label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label> 
											</td>

										 <td align="right"><label> 
											<form:radiobutton path="ismapuploadAssembly" id="acseperateserver" value="false"
														onclick="ClearRd('acseperateserver','acbaseurl','acuploadserver','errorbaseUrlac');" />
											</label></td>
											<td class="tblclear"> 
											<label> <spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label> 
											</td>
											 
											<td  width="25%"><label id="acbaseurl" style="visibility:hidden;">
											<spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message> 
													<form:input path="baseUrlAssembly" id="baseUrlAssembly" class="frmfield"  maxlength="100"/></label></td>
												<td><div id="errorbaseUrlac" style="color: red;" ></div> </td>
											<%-- <td><form:errors path="baseUrlAssembly" cssClass="error" id="baseUrlAssemblyErr"/></td> --%>
										
										</tr>
									</table>
									<div class="errormsg"></div>
								</div>
								</div>
								<div class="btnpnl">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="2%" rowspan="2">&nbsp;</td>
										<td width="98%">
												<label> 
												 	<input type="submit" id="savebtn"  onclick="return validAll(true);" class="btn" name="Submit22" value="<spring:message htmlEscape="true" code="Button.SAVE"/>"/> 
												</label>
												<label> 
												   	<input type="button" class="btn" name="Submit623" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
												</label>
										</td>
									</tr>
							    </table>
							</div>
						</form:form>
					 <script src="/LGD/JavaScriptServlet"></script>
					</div>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
