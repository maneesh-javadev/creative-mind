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
<body><section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
             <form:form id="form3" name="formgovtorder" method="post"  action="configMapLGDM.htm" commandName="configMapLGDMForm" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="configMapLGDM.htm"/>" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
                    </div>
                     
                     <div class="box-header subheading">
                        <h4><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h4>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                    <tbody>
                       <c:forEach var="getLocalGovtSetupList" varStatus="administratorUnitRow" items="${getLocalGovtSetupList}">
					<tr>
						<td width="20%"><label><c:out value="${getLocalGovtSetupList.nomenclatureEnglish}" escapeXml="true"></c:out></label>
								</td>
					  <td>
						<label class="radio-inline">
						<input type="radio" name="upload${administratorUnitRow.index+1}" id="mapUpload_${administratorUnitRow.index+1}" value="yes"
									checked="checked" onclick="toggleURL(this,true);" />  <spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message>
						</label>
					</td>
					
					<td>
					<label class="radio-inline">
					<input type="radio" name="upload${administratorUnitRow.index+1}" value="no"  id="seperateServer_${administratorUnitRow.index+1}"
									onclick="toggleURL(this,false);" /><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message>
								</label>
					</td>
					
					<td width="25%" >
					<label id="lbl_${administratorUnitRow.index+1}">
					<form:input path="baseUrl" name="baseUrl" class="form-control" id="baseUrl_${administratorUnitRow.index+1}" maxlength="99" />
										<form:errors path="baseUrl" cssClass="error" /> <span class="errormsg" id="baseUrl_error"></span>
						</label>
					</td>
						<td><div id="errorbaseUrl_${administratorUnitRow.index+1}" style="color: red;" ></div>
								<form:hidden path="tierSetupCode" value="${getLocalGovtSetupList.tierSetupCode}" htmlEscape="true" />
								<form:hidden path="tierSetupSize" value="${getLocalGovtSetupListSize}" id="hdntierSetupSize" htmlEscape="true"/>
						</td>		
					    </tr>
							
						</c:forEach>
                  </tbody>
               </table>
              </div>
              
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			           <button type="submit" class="btn btn-success" name="Submit" onclick="return validAll(true);"  ><spring:message htmlEscape="true"  code="Button.SAVE"/> </button>
                       <button type="button" class="btn btn-danger" name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>