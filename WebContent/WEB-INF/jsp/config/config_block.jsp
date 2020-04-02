<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<script type="text/javascript" src="js/cancel.js" ></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript">

var checkedRadio;
function ClearRd(chkRds, idname, chkRdu) {

	var chkRd = document.getElementById(chkRds);
	var chkRd1 = document.getElementById(chkRdu);

	if (chkRd.checked) {
		document.getElementById(idname).disabled = false;
		document.getElementById(idname).style.display = 'block';
		document.getElementById(idname).style.visibility = 'visible';
		//		document.getElementById('blockbaseurl_error').style.display='block';
		

	} else if (chkRd1.checked) {
		document.getElementById(idname).disabled = true;
		document.getElementById(idname).style.display = 'none';
		document.getElementById(idname).style.visibility = 'hidden';
		 document.getElementById('blockbaseurl_error').style.visibility = 'hidden';	
		 document.getElementById('blockbaseurl_error').style.display = 'none';

	}
}


function validateblockAll()
{
	var obj=document.getElementById('blockupload');	
	
	var patternu;
	patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	
//alert("mid"+val);
	var flag=true;
		if(obj.checked)
		{
			document.getElementById('blockbaseurl').style.visibility = 'hidden';
			document.getElementById('blockbaseurl').style.display = 'none';
			 document.getElementById('blockbaseurl_error').style.visibility = 'hidden';	
			 document.getElementById('blockbaseurl_error').style.display = 'none';
			
		}
	else
		{
		//alert("me");
		//alert(document.getElementById('baseUrl_' + pos).value);
		 if(document.getElementById('blockbaseurl').value=="")
			 {
			 //alert("inside");Base URL is Required
			  document.getElementById('blockbaseurl_error').innerHTML="Base URL is Required";
			 document.getElementById('blockbaseurl_error').style.visibility = 'visible';	
			 document.getElementById('blockbaseurl_error').style.display = 'block';
			
			 flag=false;
			   
			 }
		 else
			 {
			
			 if(!patternu.test(document.getElementById('blockbaseurl').value))
			 {
				 document.getElementById('blockbaseurl_error').innerHTML="Base URL is not proper format";
				 document.getElementById('blockbaseurl_error').style.visibility = 'visible';	
				 document.getElementById('blockbaseurl_error').style.display = 'block';
				 flag=false;
			 }
			 else
				 {
				 document.getElementById('blockbaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('blockbaseurl_error').style.display = 'none';
				 }
			 }
		}
		
		
	
	return flag;	
	}

		function ClearAll() {

			
			ClearRd('blockseperateserver','blockbaseurl','blockupload');

		}
		
		
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

	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><div class="success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4><spring:message htmlEscape="true" code="Label.successmsg" ></spring:message></h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message htmlEscape="true"  code="error.blank.commonAlert"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>
<div class="clear"></div>
				<div id="frmcontent">
				<div class="frmpnlbrdr">
<table width="100%"  class="tbl_no_brdr">
		<tr>
			<td valign="top" class="tblclear">
				
					<div class="frmhd">
						<table width="100%"  class="tbl_no_brdr">
							<tr>
								<td><label> <spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></label> 
								</td>
								 <td>&nbsp;</td>
                              <%--  these links are not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
                                <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
							</tr>
						</table>
					</div>
					
						<form:form action="blockType.htm" id="form1" method="POST" commandName="config">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="blockType.htm"/>"/>
						<div class="frmpnlbg">
							<div class="frmtxt">
									<table width="100%"  class="tbl_no_brdr">
										<%-- <tr>
											<td colspan="8">
											 <label> <spring:message htmlEscape="true"  code="Label.TYPEOFLANDREGION"></spring:message></label> 
											</td>
										</tr> --%>
										<tr>
										<td width="3%" rowspan="4">&nbsp;</td></tr>
										<tr>
											<td width="188">
											<label> <spring:message htmlEscape="true"  code="Label.BLOCK"></spring:message></label> 
											<form:hidden path="landregionTypeBlock" id="blocklandregiontype" value='B' />
											</td>
										 <td width="2%" align="right"><label> 
											<form:radiobutton path="ismapuploadBlock" id="blockupload" value="false"  checked="checked"
														onclick="ClearRd('blockseperateserver','blockbaseurl','blockupload');" />
											</label></td>
											<td width="94" class="tblclear">
											<label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label> 
											</td> 

											<td width="24" align="right"><label>
											 <form:radiobutton path="ismapuploadBlock" id="blockseperateserver" value="true"
														onclick="ClearRd('blockseperateserver','blockbaseurl','blockupload');" />
											</label></td>
											<td width="159" class="tblclear"><label> <spring:message htmlEscape="true" 
													code="Label.SEPERATEMAPSERVER"></spring:message></label> </td>
											
											<td width="35%" >
											 <label style="visibility:hidden;">
											 <spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message>
											   <form:input path="baseUrlBlock" id="blockbaseurl" class="frmfield"  maxlength="100"
											   />
											 </label> 
											 <div class="errormsg" id="blockbaseurl_error"></div>
											  
											 </td>
											
											<td><form:errors path="baseUrlBlock" cssClass="error"/>  </td>
											
										</tr>
									</table>
									<div  class="errormsg"></div>
								</div>
								</div>
						
							<div class="btnpnl">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="2%" rowspan="2">&nbsp;</td>
										<td width="98%">
												<label>
												 <input type="submit" id="savebtn" onclick="return validateblockAll();" class="btn" name="Submit22" value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" /> </label>
												  <label>
												 </label> 
												  <label> 
												  <input type="button" class="btn" name="Submit623" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>

										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
						</form:form>
					
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>
