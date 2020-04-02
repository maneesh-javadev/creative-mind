<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script src="js/shiftvillage.js"></script>
<script src="js/shiftblock.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/shiftdistrict.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDWRDisturbedEntities.js'></script> --%>
<script type="text/javascript" language="javascript">
	
	function validation(){
		var error=0;
		var selObj=document.getElementById('ddTargetVillage');	
		var selObj1=document.getElementById('ddTargetBlock');	
		if(selObj.options.length==0){
			error=1;
			
		}
		if(selObj1.value=="" || selObj1.value=="Select"){
			error=1;
			
		}
		if (error==0){
			var selObj=document.getElementById('ddTargetVillage');	
			for (var i = 0; i < selObj.options.length; i++) {
			     selObj.options[i].selected=true;
			}
		}
	}
	
</script>
</head>

<body 
onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	
	
	<div id="frmcontent">

		<div class="frmhd">			
			<h3 class="subtitle"><label><spring:message htmlEscape="true"  code="Label.ASSIGNEDVILLAGES"></spring:message></label></h3>
					<ul class="listing">					 				        					 				        
					   <%--//these links are not working  <li>
					       	<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
					    </li> --%>					 				    
				    </ul>
		</div>
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="assignBlockVillageDisturbedState.htm" commandName="shiftvillageForm" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="assignBlockVillageDisturbedState.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
																					
										<div class="clear"></div>
											
											<div id="tab2">
												
												<div class="frmpnlbg">
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message>
														</div>
														
															<div >
															<ul class="blocklist">
																<li>
																	<spring:message htmlEscape="true" 
																		code="Label.TARGETBLOCK"></spring:message><span class="errormsg">*</span><br /> <form:select
																		path="blockNameEnglish" cssClass="combofield"
																		 id="ddTargetBlock" onblur="vlidateOnblur('ddTargetBlock','1','15','c');" onchange="getBlkMapUnVillageLists(0);">
																		<option value="0">
																		<spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message>
																		</option>
																		
																		<c:forEach items="${disturbedEntitiesService}" var="distListvar">
																			  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
																			    <form:option value="${distListvar.blockCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.blockNameEnglish}" escapeXml="true"></c:out> </form:option>
																			  </c:if>  
																			  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
																			     <form:option value="${distListvar.blockCode}" htmlEscape="true"><c:out value="${distListvar.blockNameEnglish}" escapeXml="true"></c:out></form:option>
																			  </c:if>
																		</c:forEach>
																		
																		<%-- <form:options items="${disturbedEntitiesService}" 
                 															itemValue="key" itemLabel="value"></form:options> --%>
																		</form:select> <span class="errormsg" id="ddTargetBlock_error"></span>
																
																</li>
																<li>
																	<br /> <label></label>
																</li>
															</ul>	
														

													</div>
												</div>
												
												<div class="frmpnlbg">
													<div class="frmtxt">
														<div class="frmhdtitle">
															<spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message>
														</div>
														
													<div>
														<ul class="blocklist">
															<li>
															<div class="ms_container">
																<div class="ms_selectable">
																	<label for="ddSourceVillage">
																	<spring:message htmlEscape="true" 
																		code="Label.SELECTVILLAGE"></spring:message></label>
																	<br /><br/><form:select
																		id="ddSourceVillage" path="villageName"
																		size="1" multiple="multiple" class="frmtxtarea" htmlEscape="true">
																		<%-- <form:options items="${villList}" item="villageNameEnglish"
																				itemValue="villageCode"/> --%>
																	</form:select>
																</div>
                                                                
                                                                <div class="ms_buttons"> 
                                                                		<br></br>
																	<label> <input type="button" class="bttn" value=" &gt;&gt;"  onclick="listbox_moveacross('ddSourceVillage', 'ddTargetVillage');" /></label>
																	<label> <input type="button" value=" &lt;&lt;" class="bttn"  onclick="listbox_moveacross('ddTargetVillage', 'ddSourceVillage')"  /></label>
																</div>

															<div class="ms_selection">
																<label for="ddTargetVillage"><spring:message htmlEscape="true" 
																		code="Label.VILLAGESTOINVALIDATE"></spring:message><span class="errormsg">*</span></label><br />	<br/> <form:select
																		path="villageNameEnglish" id="ddTargetVillage" htmlEscape="true"
																		size="1" multiple="multiple" class="frmtxtarea"
																		onblur="vlidateOnblur('ddTargetVillage','1','15','c');">
																	</form:select>
																		
																		</span>
															</div>
															<div class="clear"></div>
														</div>
														</li>
													</ul>
													</div>
															
														</div>
														
														
														<br />
													</div>
												</div>
												
												
												<br />
												
												<div class="btnpnl">
												
															
																	<label> <input type="button" name="Save"
																		class="bttn" id="btnSave" onclick="shiftVillagesubmit();"
																		value=<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> />
																	</label>
																	
																	<label> <input type="button" name="Cancel"
																		class="bttn"
																		value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
																		id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
																	</label>
																	
																</div> <br /> <br /> <br />
												
												</div>
										</div>							
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>