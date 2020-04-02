<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
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

	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>				
			</c:if>				
				<c:if test="${!empty family_error}">			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>			
				</c:if>							 
				</div>
       		</div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<div class= "errorImg"></div>
							<div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div>
			                <div class="errorfont"><spring:message htmlEscape="true"  code="error.blank.commonAlert"></spring:message></div>
			                
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>

	<!-- onload="ClearAll();" -->
	<div class="clear"></div>
	<div class="frmhd">
	
			
			<h3 class="subtitle"><label><spring:message htmlEscape="true" code="Label.CONFIGUREMAP"></spring:message></label></h3>
										 <ul id="showhelp" class="listing">
					 				       <%-- //these links are not working <li>
					 				         <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>           
					 				        </li>
					 				   <li>
					 				       <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
										</li>
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
			
		</div>
	
	
	

	<div class="frmpnlbrdr">
		<form:form action="modifyLandMapUpdate.htm" id="form1" method="POST"
			commandName="modifyconfigMapCmd">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLandMapUpdate.htm"/>"/>
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message>
					</div>
					
						
						
							
							<c:forEach var="viewConfigMapLandRegion"
									varStatus="administratorUnitRow"
									items="${modifyconfigMapCmd.viewConfigMapLandRegion}">
									<table width="820" class="tbl_no_brdr">
										<tr>
										<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'S')}">
											<td width="80" valign="top">
													<label> <spring:message htmlEscape="true"  code="Label.STATE"></spring:message>
													</label>
											</td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="stateupload" 
														name="<c:out value="${status.expression}" />" value="true"
														<c:if test="${status.value == true}">checked</c:if>
														onclick="ClearRd('stateseperateserver','isbaseUrlstate','stateupload','isbaseUrlstate_error');" />
												</spring:bind>
											</td>
											<td valign="top" width="115"><label> <spring:message htmlEscape="true" 
														code="Label.UPLOADMAP"></spring:message>
											</label>
											</td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="stateseperateserver"
														name="<c:out value="${status.expression}" />" value="false"
														<c:if test="${status.value == false}">checked</c:if>
														onclick="ClearRd('stateseperateserver','isbaseUrlstate','stateupload','isbaseUrlstate_error');" />
												</spring:bind></td>
											<td width="160" valign="top"><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td valign="top" width="220"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
													<input type="text" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}"  escapeXml="true" />"
														maxlength="100"
														id="isbaseUrlstate" cssClass="frmfield"
													   
														/>
														<!-- onfocus="validateOnFocus('isbaseUrlstate');helpMessage(this,'isbaseUrlstateMsg');"
														onblur="vlidateOnblur('isbaseUrlstate','1','15','c');hideHelp();" -->
												
													
												</spring:bind> <spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].configurationMapLandregionCode">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind></td>
												<td width="200"><div class="errormsg" id="statebaseurl_error"></div></td>
												<td><form:errors path="isbaseUrl" cssClass="error" /></td>	
												
											</c:if>
										</tr>


										<tr>
										<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'D')}">
											<td width="80" valign="top">
													<label> <spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message>
													</label>
											</td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="districtupload"
														name="<c:out value="${status.expression}" />" value="true"
														<c:if test="${status.value == true}">checked</c:if>
														onclick="ClearRd('districtseperateserver','districtbaseulr','districtupload','districtbaseulr_error');" />
												</spring:bind></td>
											<td valign="top" width="115"><label> <spring:message htmlEscape="true" 
														code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="districtseperateserver"
														name="<c:out value="${status.expression}" escapeXml="true"/>" value="false"
														<c:if test="${status.value == false}">checked</c:if>
														onclick="ClearRd('districtseperateserver','districtbaseulr','districtupload','districtbaseulr_error');" />
												</spring:bind></td>
											<td width="160" valign="top"><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td valign="top" width="220"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
													<input type="text" id="districtbaseulr" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														maxlength="100"
														cssClass="frmfield" 
														/>
														<!-- onfocus="show_msg('districtbaseulr')"
														onblur="districtmap()" -->
													
												</spring:bind> <spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
											</td>
											<td  width="200"><div class="errormsg" id="districtbaseurl_error"></div></td>
											<td ><form:errors path="isbaseUrl" cssClass="error" /></td>	
											
											</c:if>
										</tr>

										<tr>
										<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'T')}">
											<td width="80" valign="top">
													<label> <spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message>
													</label>
											</td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="subdistrictupload"
														name="<c:out value="${status.expression}" />" value="true"
														<c:if test="${status.value == true}">checked</c:if>
														onclick="ClearRd('subdistrictseperateserver','subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');" />
												</spring:bind></td>
											<td valign="top" width="115"><label> <spring:message htmlEscape="true" 
														code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="subdistrictseperateserver"
														name="<c:out value="${status.expression}" />" value="false"
														<c:if test="${status.value == false}">checked</c:if>
														onclick="ClearRd('subdistrictseperateserver','subdistrictbaseurl','subdistrictupload','subdistrictbaseurl_error');" />
												</spring:bind></td>
											<td width="160" valign="top"><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td valign="top" width="220"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
													<input type="text" id="subdistrictbaseurl" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														maxlength="100"
														cssClass="frmfield"
														/>
														<!-- onfocus="show_msg('subdistrictbaseurl')"
														onblur="subdistrictmap()" --> 
													
												</spring:bind>
													
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
												<spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
											</td>
											<td  width="200"><div class="errormsg" id="subdistrictbaseurl_error"></div></td>
											<td><form:errors path="isbaseUrl" cssClass="error" /></td>
											
											</c:if>
										</tr>

										<tr>
										<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'V')}">
											<td width="80" valign="top">
													<label><spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message></label>
											</td>
											<td width="20" align="right" valign="top"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="villageupload"
														name="<c:out value="${status.expression}" />" value="true"
														<c:if test="${status.value == true}">checked</c:if>
														onclick="ClearRd('villageseperateserver','villagebaseurl','villageupload','villagebaseurl_error');" />
												</spring:bind></td>
											<td valign="top" width="115"><label> <spring:message htmlEscape="true" 
														code="Label.UPLOADMAP"></spring:message>
											</label></td>
											<td width="20" valign="top" align="right"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].ismapupload">
													<input type="radio" id="villageseperateserver"
														name="<c:out value="${status.expression}" />" value="false"
														<c:if test="${status.value == false}">checked</c:if>
														onclick="ClearRd('villageseperateserver','villagebaseurl','villageupload','villagebaseurl_error');" />
												</spring:bind></td>
											<td width="160" valign="top"><label> <spring:message htmlEscape="true" 
														code="Label.SEPERATEMAPSERVER"></spring:message>
											</label></td>
											<td valign="top" width="220"><spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].isbaseUrl">
													<input type="text" id="villagebaseurl" class="frmfield"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														maxlength="100"
														cssClass="frmfield"
														/>
													
												</spring:bind> <spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].Id">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind> <spring:bind
													path="modifyconfigMapCmd.viewConfigMapLandRegion[${administratorUnitRow.index}].landregiontype">
													<input type="hidden"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" />
												</spring:bind>
													
											</td>
												<td width="200"><div class="errormsg" id="villagebaseurl_error"></div></td>
											<td ><form:errors path="isbaseUrl" cssClass="error" /></td>
										
											</c:if>
										</tr>


									</table>
								</c:forEach>
								
						
				</div>
			</div>
			<div class="btnpnl">
				<label> <input type="submit" id="savebtn" class="btn"
					name="Submit"
					value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>"
					onclick="return validateAll();" /> </label> <label> <input
					type="button" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
			</div>
		</form:form>
		<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	</div>
</body>
</html>