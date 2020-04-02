<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!-- <script type="text/javascript" src="js/cancel.js"></script>
<script src="js/configmap.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script> -->


	<title><spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
	</title> <script type="text/javascript">
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
/* function loadPage() {
			
			document.getElementById('blockbaseurl').disabled = true;
		
			ClearAll();
		} */
		
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
		

		 function doSubmit()
		 {
		 document.getElementById('savebtn').disabled=true;
		 document.forms['form1'].submit();
		 } 
	</script>
</head>
<body > 
<section class="content">
  <div class="row">
    <section class="col-lg-12">
      <div class="box">
	    <form:form action="modifyBlockUpdate.htm" id="form1" method="POST"  commandName="modifyconfigMapCmd" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockUpdate.htm"/>" />
	              <div class="box-header with-border">
	                 <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
	               </div>
	            <div class="box-body">
	           <table  class="table table-bordered table-hover">   
	           <c:forEach var="viewConfigMapLandRegion" varStatus="administratorUnitRow" items="${modifyconfigMapCmd.viewConfigMapLandRegion}">
				
				<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'B')}">
				<tr>
						<td ><label> <spring:message htmlEscape="true"  code="Label.BLOCK"></spring:message></label>
						</td>
						<td>
						 <label class="radio-inline">
						     <spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
							     <input type="radio" id="blockupload"  	name="<c:out value="${status.expression}" escapeXml="true"/>" value="true" <c:if test="${status.value == true}">checked</c:if>
														onclick="ClearRd('blockseperateserver','blockbaseurl','blockupload');" />
								</spring:bind><spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message></label>
						</td>
						
						<td>
						 <label class="radio-inline">
						  <spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
								<input type="radio" id="blockseperateserver"  name="<c:out value="${status.expression}" escapeXml="true"/>" value="false" <c:if test="${status.value == false}">checked</c:if>
														onclick="ClearRd('blockseperateserver','blockbaseurl','blockupload');" />
						  </spring:bind><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label>
						</td>
					
						<td>
							<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
								<input type="text" id="blockbaseurl" class="form-control" name="<c:out value='${status.expression}'/>"	value="<c:out value='${status.value}' escapeXml="true"/>"
										maxlength="100" onfocus="validateOnFocus('blockbaseurl');" onblur="vlidateOnblur('blockbaseurl','1','15','c');" />
								      
						  </spring:bind> 
						  <spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
							<input type="hidden"	name="<c:out value="${status.expression}"/>"  value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
						  </spring:bind>
						<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
						   <input type="hidden"		name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
						</spring:bind>
						<br /><form:errors path="isbaseUrl" cssClass="error" />
					</td>
				  <td><div class="mandatory" id="blockbaseurl_error" style="color: red;"> </div></td>
				
			  </tr>
	        </c:if>
	           <c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}">
				
							<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].configurationMapLandregionCode">
								<input type="hidden"	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
						   </spring:bind>
						  <spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
							<input type="hidden"	name="<c:out value="${status.expression}"/>"  value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
						  </spring:bind>
					 
		       </c:if>
			   </c:forEach>
	        </table>
	        </div>   					
	                  <!-- /.box-body -->
	              <div class="box-footer">   
		              <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">                 
		                  <button type="submit" id="savebtn" class="btn btn-success" 	name="Submit"  onclick="return validateblockAll();" ><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
		                  <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
		                 </div>
	              </div>
	              </div>
	              
	              </form:form>
         </div>
     </section>
  </div>
</section>
<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>