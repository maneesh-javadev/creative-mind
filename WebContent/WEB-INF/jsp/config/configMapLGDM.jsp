<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript">


function toggleURL(obj,val)
{
	var pos = obj.id.split('_')[1];
	if(val ==true)
	{
		document.getElementById('baseUrl_' + pos).style.visibility = 'hidden';
		document.getElementById('baseUrl_' + pos).style.display = 'none';
		document.getElementById('lbl_' + pos).style.visibility = 'hidden';
		document.getElementById('lbl_' + pos).style.display = 'none';
		document.getElementById('baseUrl_' + pos).value = "";
		document.getElementById('errorbaseUrl_'+pos).style.visibility = 'hidden';	
		 document.getElementById('errorbaseUrl_'+pos).style.display = 'none';
	}
	else
		{
			document.getElementById('baseUrl_' + pos).style.visibility = 'visible';
			document.getElementById('baseUrl_' + pos).style.display = 'block';
			document.getElementById('lbl_' + pos).style.visibility = 'visible';	
			document.getElementById('lbl_' + pos).style.display = 'block';
			errorbaseUrl_
		}
}

function toggleURLAll()
{
	var setupSize=document.getElementById('hdntierSetupSize').value;
	for(var i=1;i<=setupSize;i++)
		{
			var mapUploadID='mapUpload_'+i;
			var obj=document.getElementById(mapUploadID);			
			var pos = obj.id.split('_')[1];

			
					if(obj.checked)
					{
						document.getElementById('baseUrl_' + pos).style.visibility = 'hidden';	
						document.getElementById('baseUrl_' + pos).style.display = 'none';
						
						document.getElementById('lbl_' + pos).style.display = 'none';
						 document.getElementById('lbl_'+pos).style.visibility = 'hidden';	
						document.getElementById('baseUrl_' + pos).value = "";
						 document.getElementById('errorbaseUrl_'+pos).style.visibility = 'hidden';	
						 document.getElementById('errorbaseUrl_'+pos).style.display = 'none';
					}
				else
					{
					 document.getElementById('baseUrl_' + pos).style.visibility = 'visible';
					 document.getElementById('baseUrl_' + pos).style.display = 'block';
					 document.getElementById('lbl_' + pos).style.display = 'block';
					 document.getElementById('lbl_'+pos).style.visibility = 'visible';
					}
				
		}
}

function validAll(val)
{
  //alert("validate");
	flag=true;
	
	var setupSize=document.getElementById('hdntierSetupSize').value;
	
	for(var i=1;i<= setupSize;i++)
		{
			var mapUploadID='mapUpload_'+i;
		
			var obj=document.getElementById(mapUploadID);	
			var patternu;
			patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
			var pos = obj.id.split('_')[1];
		//alert("mid"+val);
			if(val == true)
				{
				if(obj.checked)
				{
					document.getElementById('baseUrl_' + pos).style.visibility = 'hidden';
					document.getElementById('baseUrl_'+pos).style.display = 'none';
					 document.getElementById('errorbaseUrl_'+pos).style.visibility = 'hidden';	
					 document.getElementById('errorbaseUrl_'+pos).style.display = 'none';
					
				}
			else
				{
				//alert("me");
				//alert(document.getElementById('baseUrl_' + pos).value);
				 if(document.getElementById('baseUrl_' + pos).value=="")
					 {
					 //alert("inside");Base URL is Required
					  document.getElementById('errorbaseUrl_'+pos).innerHTML="Base URL is Required";
					 document.getElementById('errorbaseUrl_'+pos).style.visibility = 'visible';	
					 document.getElementById('errorbaseUrl_'+pos).style.display = 'block';
					
					 flag=false;
					   
					 }
				 else
					 {
					 if(!patternu.test(document.getElementById('baseUrl_' + pos).value))
					 {
						 document.getElementById('errorbaseUrl_'+pos).innerHTML="Base URL is not proper format";
						 document.getElementById('errorbaseUrl_'+pos).style.visibility = 'visible';	
						 document.getElementById('errorbaseUrl_'+pos).style.display = 'block';
						 flag=false;
					 }
					 else
						 {
						 document.getElementById('errorbaseUrl_'+pos).style.visibility = 'hidden';	
						 document.getElementById('errorbaseUrl_'+pos).style.display = 'none';
						 }
						
					 
					 }
				 
				}
		}
	}
	//alert(flag);
	return flag;
	
	
}

if ( window.addEventListener ) { 
    window.addEventListener( "load", toggleURLAll, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", toggleURLAll );
 } else 
       if ( window.onLoad ) {
          window.onload = toggleURLAll;
 }

</script>
</head>
<body><!--  onload="toggleURLAll(true);"> -->
	<div class="clear"></div>
	<div class="frmhd">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></td>
				<td>&nbsp;</td>
				<%-- //this link is not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td> --%>
				<%-- //this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
							code="Label.HELP"></spring:message> --%>
				</a>
				</td>

			</tr>
		</table>
	</div>
	<div class="frmpnlbrdr">
		<form:form id="form3" name="formgovtorder" method="post"
			action="configMapLGDM.htm" commandName="configMapLGDMForm">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="configMapLGDM.htm"/>" />
			<div class="frmpnlbg">
				<div class="frmtxt" align="center">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message>
					</div>
				
					<table width="80%" class="tbl_no_brdr" >
						<thead>
							<tr class="tblRowTitleBold">

							</tr>
						</thead>
						<c:forEach var="getLocalGovtSetupList"
							varStatus="administratorUnitRow" items="${getLocalGovtSetupList}">
							<tr class="tblRowB" align="left">
								<td width="20%"><label><c:out value="${getLocalGovtSetupList.nomenclatureEnglish}" escapeXml="true"></c:out></label>
								</td>
								<td width="2%"><input type="radio"
									name="upload${administratorUnitRow.index+1}"
									id="mapUpload_${administratorUnitRow.index+1}" value="yes"
									checked="checked" onclick="toggleURL(this,true);" />
								</td>
								<td width="10%"><label><spring:message htmlEscape="true" 
											code="Label.UPLOADMAP"></spring:message>
								</label></td>
								<td width="2%"><input type="radio"
									name="upload${administratorUnitRow.index+1}" value="no"
									id="seperateServer_${administratorUnitRow.index+1}"
									onclick="toggleURL(this,false);" />
								</td>
								<td width="20%"><label><spring:message htmlEscape="true" 
											code="Label.SEPERATEMAPSERVER"></spring:message>
								</label>
								</td>
								<td width="25%" id="lbl_${administratorUnitRow.index+1}"><%-- <label><spring:message htmlEscape="true" 
											code="Label.ENTERBASEURL"></spring:message> --%> <form:input
											path="baseUrl" name="baseUrl" class="frmfield"
											id="baseUrl_${administratorUnitRow.index+1}" maxlength="99" />
										<form:errors path="baseUrl" cssClass="error" /> <span
										class="errormsg" id="baseUrl_error"></span>
								<!-- </label> -->
								
								</td>
								<td><div id="errorbaseUrl_${administratorUnitRow.index+1}" style="color: red;" ></div></td>
								<form:hidden path="tierSetupCode" value="${getLocalGovtSetupList.tierSetupCode}" htmlEscape="true" />
								<form:hidden path="tierSetupSize" value="${getLocalGovtSetupListSize}" id="hdntierSetupSize" htmlEscape="true"/>
							</tr>
							
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="btnpnl">
				<label> <input type="submit" class="btn" name="Submit" onclick="return validAll(true);"
					value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" /> </label> <label> <input
					type="button" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
			</div>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>

</body>
</html>