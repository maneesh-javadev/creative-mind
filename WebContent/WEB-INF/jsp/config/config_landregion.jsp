<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript">
function go(url)
{
	window.location = url;
}

var checkedRadio;
	function ClearRd(chkRd, idname, chkRd1,errorid) {		

		var chkRd = document.getElementById(chkRd);
		var chkRd1 = document.getElementById(chkRd1);

		if (chkRd.checked) {
			document.getElementById(idname).disabled = false;
			document.getElementById(idname).style.visibility = "visible";
			document.getElementById(idname).style.display = 'block';
		

		} else if (chkRd1.checked) {
			document.getElementById(idname).disabled = true;
			document.getElementById(idname).style.visibility = "hidden";
			document.getElementById(idname).style.display = 'block';
			document.getElementById(errorid).style.visibility = "hidden";
			document.getElementById(errorid).style.display = 'block';
			
		}
	}
	
	function validateAll()
	{	
		
		var obj=document.getElementById('stateupload');	
		var obj1=document.getElementById('districtupload');	
		var obj2=document.getElementById('subdistrictupload');	
		var obj3=document.getElementById('villageupload');	
		
		var patternu;
		patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
		var flag=true;
		
		//-----------------state validation -------------------------------------
		if(obj.checked)
		{
			document.getElementById('statebaseurl').style.visibility = 'hidden';
			document.getElementById('statebaseurl').style.display = 'none';
			 document.getElementById('statebaseurl_error').style.visibility = 'hidden';	
			 document.getElementById('statebaseurl_error').style.display = 'none';
			
		}
	else
		{
		//alert("me");
		//alert(document.getElementById('baseUrl_' + pos).value);
		 if(document.getElementById('baseUrlState').value=="")
			 {
			 //alert("inside");Base URL is Required
			  document.getElementById('statebaseurl_error').innerHTML="Base URL is Required";
			 document.getElementById('statebaseurl_error').style.visibility = 'visible';	
			 document.getElementById('statebaseurl_error').style.display = 'block';
			
			 flag=false;
			   
			 }
		 else
			 {
			
			 if(!patternu.test(document.getElementById('baseUrlState').value))
			 {
				 document.getElementById('statebaseurl_error').innerHTML="Base URL is not proper format";
				 document.getElementById('statebaseurl_error').style.visibility = 'visible';	
				 document.getElementById('statebaseurl_error').style.display = 'block';
				 flag=false;
			 }
			 else
				 {
				 document.getElementById('statebaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('statebaseurl_error').style.display = 'none';
				 }
			 }
		}
		
		//-----------------district validation -------------------------------------
		if(obj1.checked)
		{
			document.getElementById('districtbaseulr').style.visibility = 'hidden';
			document.getElementById('districtbaseulr').style.display = 'none';
			 document.getElementById('districtbaseurl_error').style.visibility = 'hidden';	
			 document.getElementById('districtbaseurl_error').style.display = 'none';
			
		}
	else
		{
		//alert("me");
		//alert(document.getElementById('baseUrl_' + pos).value);
		 if(document.getElementById('baseUrlDistrict').value=="")
			 {
			 //alert("inside");Base URL is Required
			  document.getElementById('districtbaseurl_error').innerHTML="Base URL is Required";
			 document.getElementById('districtbaseurl_error').style.visibility = 'visible';	
			 document.getElementById('districtbaseurl_error').style.display = 'block';
			
			 flag=false;
			   
			 }
		 else
			 {
			
			 if(!patternu.test(document.getElementById('baseUrlDistrict').value))
			 {
				 document.getElementById('districtbaseurl_error').innerHTML="Base URL is not proper format";
				 document.getElementById('districtbaseurl_error').style.visibility = 'visible';	
				 document.getElementById('districtbaseurl_error').style.display = 'block';
				 flag=false;
			 }
			 else
				 {
				 document.getElementById('districtbaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('districtbaseurl_error').style.display = 'none';
				 }
			 }
		}
		
		//-----------------sub district validation -------------------------------------
		if(obj2.checked)
		{
			document.getElementById('subdistrictbaseurl').style.visibility = 'hidden';
			document.getElementById('subdistrictbaseurl').style.display = 'none';
			 document.getElementById('subdistrictbaseurl_error').style.visibility = 'hidden';	
			 document.getElementById('subdistrictbaseurl_error').style.display = 'none';
			
		}
	else
		{
		//alert("me");
		//alert(document.getElementById('baseUrl_' + pos).value);
		 if(document.getElementById('baseUrlSubDist').value=="")
			 {
			 //alert("inside");Base URL is Required
			  document.getElementById('subdistrictbaseurl_error').innerHTML="Base URL is Required";
			 document.getElementById('subdistrictbaseurl_error').style.visibility = 'visible';	
			 document.getElementById('subdistrictbaseurl_error').style.display = 'block';
			
			 flag=false;
			   
			 }
		 else
			 {
			
			 if(!patternu.test(document.getElementById('baseUrlSubDist').value))
			 {
				 document.getElementById('subdistrictbaseurl_error').innerHTML="Base URL is not proper format";
				 document.getElementById('subdistrictbaseurl_error').style.visibility = 'visible';	
				 document.getElementById('subdistrictbaseurl_error').style.display = 'block';
				 flag=false;
			 }
			 else
				 {
				 document.getElementById('subdistrictbaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('subdistrictbaseurl_error').style.display = 'none';
				 }
			 }
		}
		
		//-----------------village validation -------------------------------------
		
		if(obj3.checked)
		{
			document.getElementById('villagebaseurl').style.visibility = 'hidden';
			document.getElementById('villagebaseurl').style.display = 'none';
			 document.getElementById('villagebaseurl_error').style.visibility = 'hidden';	
			 document.getElementById('villagebaseurl_error').style.display = 'none';
			
		}
	else
		{
		//alert("me");
		//alert(document.getElementById('baseUrl_' + pos).value);
		 if(document.getElementById('baseUrlVillage').value=="")
			 {
			 //alert("inside");Base URL is Required
			  document.getElementById('villagebaseurl_error').innerHTML="Base URL is Required";
			 document.getElementById('villagebaseurl_error').style.visibility = 'visible';	
			 document.getElementById('villagebaseurl_error').style.display = 'block';
			
			 flag=false;
			   
			 }
		 else
			 {
			
			 if(!patternu.test(document.getElementById('baseUrlVillage').value))
			 {
				 document.getElementById('villagebaseurl_error').innerHTML="Base URL is not proper format";
				 document.getElementById('villagebaseurl_error').style.visibility = 'visible';	
				 document.getElementById('villagebaseurl_error').style.display = 'block';
				 flag=false;
			 }
			 else
				 {
				 document.getElementById('villagebaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('villagebaseurl_error').style.display = 'none';
				 }
			 }
		}
		
		
		return flag;
	}
	
	function doSubmit()
	{
		document.getElementById('savebtn').disabled=true;
		document.forms['form1'].submit();
	}
	
	function ClearAll() {
		
		ClearRd('stateseperateserver', 'statebaseurl', 'stateupload','statebaseurl_error');
		ClearRd('districtseperateserver', 'districtbaseulr','districtupload','districtbaseurl_error');
		ClearRd('subdistrictseperateserver', 'subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');
		ClearRd('villageseperateserver', 'baseUrlVillage', 'villageupload','villagebaseurl_error');
	}
	
	/* function doLoad() {
	     alert( "The load event is executing" );
	  } */
	  if ( window.addEventListener ) { 
	     window.addEventListener( "load", ClearAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", ClearAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = ClearAll;
	  }
</script>


<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body >

	
				
				<div class="clear"></div>
				<div id="frmcontent">
					<div class="frmhd">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td><label><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></label>
								</td>
								 <td>&nbsp;</td>
                              <%--  //these links are not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
                                <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
								
							</tr>
						</table>
					</div>
					<div class="frmpnlbrdr">
						<form:form action="configland.htm" id="form1" method="POST" commandName="config">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="configland.htm"/>"/>
						<div class="frmpnlbg">
						  		<div class="frmtxt">
						  		<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></div>
									<table width="100%" class="tbl_no_brdr">
										<%-- <tr>
											<td colspan="8">
											 <label><spring:message htmlEscape="true"  code="App.TYPEOFLANDREGION"></spring:message> </label>
											</td>
										</tr> --%>
										<tr>
											<td width="3%" rowspan="4">&nbsp;</td>
											<td width="17%">
											   <label><spring:message htmlEscape="true"  code="Label.STATE"></spring:message> </label>
											</td>
											<td width="2%" align="right">
											<label> 
											<form:radiobutton path="ismapuploadState" id="stateupload" value="true" htmlEscape="false"
														onclick="ClearRd('stateseperateserver','statebaseurl','stateupload','statebaseurl_error');"  checked="checked" />
											</label></td>
											<td width="8%" class="tblclear">
											 <label><spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message> </label>
											</td>
											<td width="3%" align="right">
											  <label> 
											  <form:radiobutton path="ismapuploadState" name="upload" id="stateseperateserver" value="true" htmlEscape="true"
														onclick="ClearRd('stateseperateserver','statebaseurl','stateupload','statebaseurl_error');"  />
											  </label>
											</td>
											<td width="15%" class="tblclear">
											  <label><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message> </label>
											</td>
											
											 <td width="23%">
											 <label id="statebaseurl" style="visibility:hidden;"> 
											   <spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message>  
											  <form:input path="baseUrlState" class="frmfield" maxlength="100"/> 
											 </label>
											
											</td> 
											<td> <div class="errormsg" id="statebaseurl_error"></div><form:errors path="baseUrlState" cssClass="error"  id="baseUrlStateErr"  /></td>
											
										</tr>
										<tr>
										
											<td> <label><spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message> </label>
											<form:hidden path="landregionTypeDistrict" id="districtlandregiontype" value='D' htmlEscape="true"/>
											</td>
											<td align="right"><label>
											 <form:radiobutton 	path="ismapuploadDistrict" id="districtupload" value="false" checked="checked"
														onclick="ClearRd('districtseperateserver','districtbaseulr','districtupload','districtbaseurl_error');" htmlEscape="true"/>
											</label></td>
											<td style="padding: 0px"><spring:message htmlEscape="true" 
													code="Label.UPLOADMAP"></spring:message>
											</td>
											<td align="right"><label> 
											<form:radiobutton path="ismapuploadDistrict" id="districtseperateserver"
														value="true"
														onclick="ClearRd('districtseperateserver','districtbaseulr','districtupload','districtbaseurl_error');" htmlEscape="true"/>
											</label></td>
											<td class="tblclear">
											 <label><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message> </label>
											</td>
											<!-- <td width="0%" align="right" >
											</td> -->
											 <td>
											<label id="districtbaseulr" style="visibility:hidden">
											 <spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message> 
											<form:input path="baseUrlDistrict" class="frmfield"	 maxlength="100"/> 
											</label> 
											
											</td> 
											<td><div class="errormsg" id="districtbaseurl_error"></div><form:errors path="baseUrlDistrict" cssClass="error" /></td>
											
										</tr>
										<tr>
										
											<td> <label><spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message> </label>
											<form:hidden path="landregionTypeSubDist" id="subdistrictlandregiontype" value='T' />
											</td>
											<td align="right"><label> 
											<form:radiobutton path="ismapuploadSubDist" id="subdistrictupload" value="false" checked="checked"
														onclick="ClearRd('subdistrictseperateserver','subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');" htmlEscape="true"/>
											</label></td>
											<td class="tblclear">
											 <label><spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message> </label>
											</td>
											<td align="right">
											<label> 
											<form:radiobutton path="ismapuploadSubDist" id="subdistrictseperateserver"
														value="true"
														onclick="ClearRd('subdistrictseperateserver','subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');" htmlEscape="true"/>
											</label></td>
											<td class="tblclear">
											 <label><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message> </label>
											</td>
											<!-- <td width="0%" align="right" >
											</td> -->
											 <td><label id="subdistrictbaseurl" style="visibility:hidden">
											<spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message>  
											<form:input path="baseUrlSubDist" class="frmfield" maxlength="100"/> 
											</label>
											
											</td> 
											<td><div class="errormsg" id="subdistrictbaseurl_error"></div><form:errors path="baseUrlSubDist" cssClass="error" /></td>
											
										</tr>
										<tr>
											<td> <label><spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message> </label>
											<form:hidden path="landregionTypeVillage" id="villagelandtype" value='V' htmlEscape="true"/>
											</td>
											<td align="right"><label> 
											<form:radiobutton path="ismapuploadVillage" id="villageupload" value="false" checked="checked"
														onclick="ClearRd('villageseperateserver','villagebaseurl','villageupload','villagebaseurl_error');" htmlEscape="true"/>
											</label></td>
											<td class="tblclear">
											 <label><spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message> </label>
											</td>
											<td align="right"><label>
											 <form:radiobutton path="ismapuploadVillage" id="villageseperateserver"
														value="true" onclick="ClearRd('villageseperateserver','villagebaseurl','villageupload','villagebaseurl_error');" htmlEscape="true"/>
											</label></td>
											<td class="tblclear">
											 <label><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message> </label>
											</td>
											<!-- <td align="right">
											</td> -->
										 <td>
											<label id="villagebaseurl" style="visibility:hidden">
											<spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message> 
											<form:input path="baseUrlVillage" class="frmfield" maxlength="100"/>
											 </label>
											
											</td> 
											<td> <div class="errormsg" id="villagebaseurl_error"></div><form:errors path="baseUrlVillage" cssClass="error" /></td>																								
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
											  <input type="submit" id="savebtn"  onclick="return validateAll();" class="btn" name="Submit22" value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" /> 
											</label>
											 <label></label> 
											 <label>
											   <input type="button" class="btn" name="Submit623" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/>
											  </label>

											</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
							
						</form:form>
							</div>
		
	<div class="clear"></div>
	<div class="clear"></div>
</div>
</body>
</html>

