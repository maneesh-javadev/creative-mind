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
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
               <form:form action="typeOfConstituncy.htm" id="form1" method="POST" commandName="config">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="typeOfConstituncy.htm"/>"/>
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tr>
						  <th><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message></th>
						  <th colspan="3" text-align="center"><spring:message htmlEscape="true"  code="Label.OPTIONS"></spring:message></th>
						</tr>
					<tbody>
                            <tr>
							 
								 <td >
								   <label> <spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCY"></spring:message></label> 
								    <form:hidden path="constituencyTypeParliament" id="parliamentconstituencytype" value='P' />
								</td>
								<td ><label><form:radiobutton path="ismapuploadParliament" id="pcuploadmap" value="true" checked="checked"
															onclick="ClearRd('pcseperatemapserver','pcbaseUrl','pcuploadmap','errorbaseUrlpc');" />
								
								<spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label> </td>
							
								<td ><label> 
								<form:radiobutton path="ismapuploadParliament" id="pcseperatemapserver" value="false"
														onclick="ClearRd('pcseperatemapserver','pcbaseUrl','pcuploadmap','errorbaseUrlpc');" />
									
									<spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label></td>
												
								<td >
								<label id="pcbaseUrl" style="visibility:hidden;">
									<spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message>  
												<form:input path="baseUrlParliament"  id="baseUrlParliament" class="form-control" maxlength="100"/> </label></td>
												<td><div id="errorbaseUrlpc" style="color: red;" ></div></td>
						</tr>  
										
						<tr>
							
							<td><label> <spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCY"></spring:message></label> 
								<form:hidden path="constituencyTypeAssembly" id="assemblyconstituencytype" value='A' />
							</td>
							<td ><label> 
							<form:radiobutton path="ismapuploadAssembly" id="acuploadserver" value="true" checked="checked"
														onclick="ClearRd('acseperateserver','acbaseurl','acuploadserver','errorbaseUrlac');" />
								<spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label></td>
							<td ><label> 
							<form:radiobutton path="ismapuploadAssembly" id="acseperateserver" value="false"
														onclick="ClearRd('acseperateserver','acbaseurl','acuploadserver','errorbaseUrlac');" />
								 <spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label> </td>
							
							<td ><label id="acbaseurl" style="visibility:hidden;">
								<spring:message htmlEscape="true"  code="Label.ENTERBASEURL"></spring:message> 
								<form:input path="baseUrlAssembly" id="baseUrlAssembly" class="form-control"  maxlength="100"/></label></td>
								<td><div id="errorbaseUrlac" style="color: red;" ></div> </td>
						</tr>                                                      
                  </tbody>
               </table>
               </div>
                  <!-- /.box-body -->
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			            <button type="submit" id="savebtn"  onclick="return validAll(true);" class="btn btn-success" name="Submit22" ><spring:message htmlEscape="true" code="Button.SAVE"/> </button>
			             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
</body>
</html>
