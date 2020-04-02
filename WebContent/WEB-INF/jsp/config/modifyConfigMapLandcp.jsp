<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<!-- <script type="text/javascript" src="js/cancel.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script> -->
<script src="js/configmap.js"></script>
	<script type="text/javascript">
		function go(url) {
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
				document.getElementById('isbaseUrlstate').style.visibility = 'hidden';
				document.getElementById('isbaseUrlstate').style.display = 'none';
				 document.getElementById('statebaseurl_error').style.visibility = 'hidden';	
				 document.getElementById('statebaseurl_error').style.display = 'none';
				
			}
		else
			{
			//alert("me");
			//alert(document.getElementById('baseUrl_' + pos).value);
			 if(document.getElementById('isbaseUrlstate').value=="")
				 {
				 //alert("inside");Base URL is Required
				  document.getElementById('statebaseurl_error').innerHTML="Base URL is Required";
				 document.getElementById('statebaseurl_error').style.visibility = 'visible';	
				 document.getElementById('statebaseurl_error').style.display = 'block';
				
				 flag=false;
				   
				 }
			 else
				 {
				
				 if(!patternu.test(document.getElementById('isbaseUrlstate').value))
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
			 if(document.getElementById('districtbaseulr').value=="")
				 {
				 //alert("inside");Base URL is Required
				  document.getElementById('districtbaseurl_error').innerHTML="Base URL is Required";
				 document.getElementById('districtbaseurl_error').style.visibility = 'visible';	
				 document.getElementById('districtbaseurl_error').style.display = 'block';
				
				 flag=false;
				   
				 }
			 else
				 {
				
				 if(!patternu.test(document.getElementById('districtbaseulr').value))
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
			 if(document.getElementById('subdistrictbaseurl').value=="")
				 {
				 //alert("inside");Base URL is Required
				  document.getElementById('subdistrictbaseurl_error').innerHTML="Base URL is Required";
				 document.getElementById('subdistrictbaseurl_error').style.visibility = 'visible';	
				 document.getElementById('subdistrictbaseurl_error').style.display = 'block';
				
				 flag=false;
				   
				 }
			 else
				 {
				
				 if(!patternu.test(document.getElementById('subdistrictbaseurl').value))
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
			 if(document.getElementById('villagebaseurl').value=="")
				 {
				 //alert("inside");Base URL is Required
				  document.getElementById('villagebaseurl_error').innerHTML="Base URL is Required";
				 document.getElementById('villagebaseurl_error').style.visibility = 'visible';	
				 document.getElementById('villagebaseurl_error').style.display = 'block';
				
				 flag=false;
				   
				 }
			 else
				 {
				
				 if(!patternu.test(document.getElementById('villagebaseurl').value))
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
		
		function loadPage() {
			
			document.getElementById('isbaseUrlstate').disabled = true;
			document.getElementById('districtbaseulr').disabled = true;
			document.getElementById('subdistrictbaseurl').disabled = true;
			document.getElementById('villagebaseurl').disabled = true;
			document.getElementById('stateupload').checked = true;
			document.getElementById('districtupload').checked = true;
			document.getElementById('subdistrictupload').checked = true;
			document.getElementById('villageupload').checked = true;
			ClearAll();
		}
		function ClearAll() {
			
			ClearRd('stateseperateserver', 'isbaseUrlstate', 'stateupload','statebaseurl_error');
			ClearRd('districtseperateserver', 'districtbaseulr','districtupload','districtbaseurl_error');
			ClearRd('subdistrictseperateserver', 'subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');
			ClearRd('villageseperateserver', 'villagebaseurl', 'villageupload','villagebaseurl_error');
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
</head>
<body > <!--  onload="ClearAll();" > -->
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
     <form:form action="modifyLandMapUpdate.htm" id="form1" method="POST"  commandName="modifyconfigMapCmd" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLandMapUpdate.htm"/>"/>
              <div class="box-header with-border">
                 <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
               </div>
         <div class="box-body">
           <table  class="table table-bordered table-hover">   
            <c:forEach var="viewConfigMapLandRegion"  varStatus="administratorUnitRow" items="${modifyconfigMapCmd.viewConfigMapLandRegion}">
			
			<tr>
				<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}">
				<td><label> <spring:message htmlEscape="true"  code="Label.STATE"></spring:message></label></td>
				<td >
				<label class="radio-inline">
					<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
					      <input type="radio" id="stateupload" name="<c:out value="${status.expression}" />" value="true" <c:if test="${status.value == true}">checked</c:if>
																onclick="ClearRd('stateseperateserver','isbaseUrlstate','stateupload','isbaseUrlstate_error');" />
						</spring:bind><spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message></label>
				</td>
				<td>
				<label class="radio-inline">
					<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
					<input type="radio" id="stateseperateserver" name="<c:out value="${status.expression}" />" value="false" <c:if test="${status.value == false}">checked</c:if>
																onclick="ClearRd('stateseperateserver','isbaseUrlstate','stateupload','isbaseUrlstate_error');" />
					</spring:bind><spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message></label>
				</td>
			
				<td ><spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
					<input type="text" class="form-control" 	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"  escapeXml="true" />"
					   maxlength="100"  id="isbaseUrlstate" class="form-control" />
						</spring:bind> 
						<spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].configurationMapLandregionCode">
						<input type="hidden"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
							</spring:bind>
						<spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
					<input type="hidden"   name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
					</spring:bind>
					
				</td>				
				<td><div class="mandatory" id="statebaseurl_error"><form:errors path="isbaseUrl" cssClass="error" /></td>	
				</c:if>
			</tr>


			<tr>
				<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'D')}">
						<td >
							<label> <spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message></label>
						</td>
						<td >
						<label class="radio-inline">
							<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
						       <input type="radio" id="districtupload" name="<c:out value="${status.expression}" />" value="true"  <c:if test="${status.value == true}">checked</c:if>
										onclick="ClearRd('districtseperateserver','districtbaseulr','districtupload','districtbaseulr_error');" />
							</spring:bind><spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label>
						</td>
						
						<td >
						<label class="radio-inline">
							<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
									<input type="radio" id="districtseperateserver" name="<c:out value="${status.expression}" escapeXml="true"/>" value="false"
										<c:if test="${status.value == false}">checked</c:if> onclick="ClearRd('districtseperateserver','districtbaseulr','districtupload','districtbaseulr_error');" />
							</spring:bind><spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message></label>
						</td>
						
						<td valign="top" width="220">
							<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
								<input type="text" id="districtbaseulr" class="form-control" 	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" maxlength="100" cssClass="form-control"  />
							</spring:bind> 
							<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
								<input type="hidden"	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
							</spring:bind>
							<spring:bind   path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
						    	<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
							</spring:bind>
						</td>
					    <td ><div class="mandatory" id="districtbaseurl_error"></div><form:errors path="isbaseUrl" cssClass="error" /></td>	
													
					</c:if>
			</tr>

			<tr>
				<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'T')}">
					<td>
					    <label> <spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message></label>
					</td>
					<td>
					  <label class="radio-inline">
						<spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
						<input type="radio" id="subdistrictupload" name="<c:out value="${status.expression}" />" value="true" <c:if test="${status.value == true}">checked</c:if>
									onclick="ClearRd('subdistrictseperateserver','subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');" />
						</spring:bind><spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message></label>
					</td>
				    
					<td >
					<label class="radio-inline">
						<spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
							<input type="radio" id="subdistrictseperateserver" name="<c:out value="${status.expression}" />" value="false" <c:if test="${status.value == false}">checked</c:if>
									onclick="ClearRd('subdistrictseperateserver','subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');" />
						 </spring:bind><spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label>
					</td>
					
					<td valign="top" width="220"><spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
						<input type="text" id="subdistrictbaseurl" class="form-control" name="<c:out value="${status.expression}"/>"  value="<c:out value="${status.value}" escapeXml="true"/>" maxlength="100" class=""/>
						</spring:bind>
						<spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
						<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
						</spring:bind>
						<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
						<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
						</spring:bind>
					</td>
					<td  width="200"><div class="mandatory" id="subdistrictbaseurl_error"></div><form:errors path="isbaseUrl" cssClass="error" /></td>
				</c:if>
			</tr>

			<tr>
				<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'V')}">
					<td ><label><spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message></label></td>
					<td>
					 <label class="radio-inline">
						<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
						<input type="radio" id="villageupload" name="<c:out value="${status.expression}" />" value="true" <c:if test="${status.value == true}">checked</c:if> onclick="ClearRd('villageseperateserver','villagebaseurl','villageupload','villagebaseurl_error');" />
						</spring:bind><spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label>
					</td>
				
				    <td>
				    <label class="radio-inline">
					    <spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
						<input type="radio" id="villageseperateserver" name="<c:out value="${status.expression}" />" value="false" <c:if test="${status.value == false}">checked</c:if>
							onclick="ClearRd('villageseperateserver','villagebaseurl','villageupload','villagebaseurl_error');" />
						</spring:bind><spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message></label>
					</td>
					
					<td >
						<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
						<input type="text" id="villagebaseurl" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" maxlength="100" class="form-control" />
						</spring:bind> 
						<spring:bind	path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
						<input type="hidden" name="<c:out value="${status.expression}"/>" 	value="<c:out value="${status.value}" escapeXml="true"/>" />
						</spring:bind> 
						<spring:bind path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
						<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" />
						</spring:bind>
															
					</td>
					<td width="200"><div class="mandatory" id="villagebaseurl_error"></div></td>
					<td ><form:errors path="isbaseUrl" cssClass="error" /></td>
				</c:if>
			</tr>
          
		   </c:forEach>
        </table>
        </div>					
                  <!-- /.box-body -->
              <div class="box-footer">   
	              <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                 
	                  <button type="submit" id="savebtn" class="btn btn-success" 	name="Submit"  onclick="return validateAll();" ><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
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