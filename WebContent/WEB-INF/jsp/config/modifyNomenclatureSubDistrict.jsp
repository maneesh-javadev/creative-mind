<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js" ></script>
<title><spring:message htmlEscape="true"  code="Label.Title"></spring:message>
</title>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/nomenclatureSubdistrict.js" charset="utf-8"></script>
<script type="text/javascript">
function getInitailData()
{
	document.getElementById('blockExist').checked?togglehide(1):null;
}
</script>
</head >
<body onload="getInitailData()">
	<div id="maincontainer">
			<div >
					 <div class="clear"></div>
						<div id="frmcontent">
							<div class="frmhd">		
							<h3 class="subtitle"><label><spring:message htmlEscape="true" code="Label.NOMENCLATUREOFSUBDISTRICT"></spring:message></label></h3>
								<ul id="showhelp" class="listing">
					 		<%-- //these links are not working	<li>
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
								<form:form action="modifyNomenclatureSd.htm" id="formmodifynomen" method="POST" commandName="nomenclatureSubDistForm">
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyNomenclatureSd.htm"/>"/>
								<div class="frmpnlbg">
								<div class="frmtxt">
								
								
								
								<c:forEach var="nomen" varStatus="nomenRow" items="${nomenclatureSubdistrictList}">
											<table width="100%" class="tbl_no_brdr">
												<tr>
												<td><spring:message htmlEscape="true"  code="Label.NOMENCLATUREOFSUBDISTRICTENGLISH"></spring:message></td><tr>
												<tr><td align="left">
												<form:input path="nameinEnglish"  value="${nomen.nomenclatureEnglish}" id="NomenInEnglish" cssClass="frmfield" onfocus="show_msg('NomenInEnglish')"  
												onblur="chekcalphanumeric(this.value);"  maxlength="50"/>
												<form:errors path="nameinEnglish" cssClass="errormsg"></form:errors>
													<!-- <span class="msg" id="NomenInEnglish_msg">*Please enter the nomenclature in english</span>onblur="nomenInEnglish()" -->
													<span class="errormsg" id="NomenInEnglish_error"></span>
												<div class="errormsg"></div>
												
												
												</td></tr>
												<tr><td><spring:message htmlEscape="true"  code="Label.NOMENCLATUREOFSUBDISTRICTLOCAL"></spring:message></td></tr>
												<tr><td align="left"><form:input path="nameinlocalLanguage" value="${nomen.nomenclatureLocal}" class="frmfield" maxlength="50" onblur="validatelocalCharachterforsubdistrict(this.value,1);"/>
												</td></tr>
												<tr><td>&nbsp;</td></tr>
												<tr><td>Is Block Exist</td>
												<tr><td align="left"><form:radiobutton path="blockExist" id="blockExist"
															value="true" label="Yes" onclick="togglehide(1)" />
												<form:radiobutton path="blockExist"	value="false" label="No" onclick="togglehide(0)" /></td>
												</tr>

												
												<tr id="trdyn1" style="visibility : hidden">
													<td><spring:message htmlEscape="true"  code="Label.ISBLOCKNAMESAME"></spring:message></td> </tr>
												<tr id="trdyn2" style="visibility : hidden">
												<td align="left">
												<form:radiobutton path="nameofTheBlockisSameforTheState" value="true"  label="Yes"  />
												<form:radiobutton path="nameofTheBlockisSameforTheState" value="false"	label="No"  />
												</td>
												
												</tr>												
												</table>
									</c:forEach>
											
											
											
											
									<div class="errormsg"></div>
									</div>
									</div>
									<div class="btnpnl">
									<ul class="listing">
										<li>
										<label> 
										<input type="button" id="btnSave"  name="Button" class="btn" onclick="validateAndSubmit()"
											value="<spring:message htmlEscape="true"  code="Button.UPDATE"/>" /> </label>
										<label> <input type="button" class="btn" name="cancel"
											onclick="javascript:go('nomenclature_sub_district.htm');"
											value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" />
										</label>
										</li>
										</ul>
									</div>
								</form:form>
								 <script src="/LGD/JavaScriptServlet"></script>
							</div>
						</div>
					</div>															
				</div>
			</body>
		</html>