<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

 <script type="text/javascript" src="js/cancel.js" ></script>


<script src="js/common.js"></script>
<script src="js/validation.js"></script>

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


<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
		      </div><!-- /.box-header -->
		      
		      

				<form:form action="blockType.htm" id="form1" method="POST" commandName="config">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="blockType.htm"/>"/>
						
						 <div class="box-body">
						
						
									<table width="100%"   class="tbl_no_brdr">
										<tr>
											<td colspan="8">
											 <label> <spring:message htmlEscape="true"  code="Label.TYPEOFLANDREGION"></spring:message></label> 
											</td>
										</tr>
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
											   &nbsp;<form:input path="baseUrlBlock" id="blockbaseurl" class="form-control"  maxlength="100" style="margin-left:10px;"
											   />
											 </label> 
											 <div class="errormsg" id="blockbaseurl_error"></div>
											  
											 </td>
											
											<td><form:errors path="baseUrlBlock" cssClass="error"/>  </td>
											
										</tr>
									</table> 
								</div>
								
								 <div class="box-footer">  
					                <div class="col-sm-offset-2 col-sm-10">
						                 <div class="pull-right"> 
						                	 <input type="submit" class="btn btn-success" id="savebtn" onclick="return validateblockAll();" class="btn" name="Submit22" value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" />                  
								             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
					                      </div>
					                  </div>
					              </div>
						
							
						</form:form>
					
	</div>
	</section>
	</div>
	</section>
</body>
</html>
