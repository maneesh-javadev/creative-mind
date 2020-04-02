<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script src="js/invalidateDistrict.js"></script>
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

<script type="text/javascript" src="js/invalidateDistrict.js"
	charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function checkcode(obj)
{
	//alert("java"+obj);
	}

addItemList=function(selListId, coverageListId) {
	
	$('#'+selListId+'> option:selected').appendTo('#'+coverageListId); 
	};
	
</script>
</head>
<body onload="toggledisplay('districtdelete_yes','fromothersubdistrict')"
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
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true"  code="Label.INVALIDATEDISTRICT"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">	
			
				<%--//these links are not working <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a></li>
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>											
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</div>	 	
		
		<div class="clear"></div>

		<div class="frmpnlbrdr">
			
			<form:form action="enterInvalidateDistrictOrderDetails.htm" id="invalidateForm" method="POST" commandName="invalidateDistrict" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="enterInvalidateDistrictOrderDetails.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div>
								<div class="frmhdtitle">
									<spring:message htmlEscape="true"  code="Label.INVALIDATEDISTRICT"></spring:message>
								</div>
							
							
								<label><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
									<form:hidden path="operation" value="I"/>
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>
										<label> <form:select path="districtNameEnglish"  id="ddDistrict" cssClass="combofield"
												             onchange="getSubDistrictList(this.value);removeItemFromOtherDropdowns(this);" htmlEscape="true" onblur="vlidateOnblur('ddDistrict','1','15','c');">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
														<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
																</form:option>
															</c:if>
														</c:forEach>
											    </form:select> </label> <br />
											    <form:hidden path="operation" value="I"/>
										<div class="errormsg"><form:errors htmlEscape="true"  path="districtNameEnglish" ></form:errors></div>
										<span class="errormsg" id="districtNameEnglish">
									    </span>	
							</div>									
						</div>
					</div>
					<div class="frmpnlbg">
						<div class="frmtxt">
								<div>
										<label><spring:message htmlEscape="true" code="Label.HOWDISTRICTDELETED"></spring:message></label>
										
										<ul class="listing">
											<li><label> <form:radiobutton
															id="districtdelete_yes" path="rdoDistrictDelete"
															htmlEscape="true"
															onclick="toggledisplay('districtdelete_yes','fromothersubdistrict')"
															value="false" />
												</label>
												
												<spring:message
														htmlEscape="true" code="Label.YES"></spring:message>
												
												</li>
											<li>
													<label> <form:radiobutton
															id="districtdelete_no" path="rdoDistrictDelete"
															onclick="toggledisplay('districtdelete_no','cvillage')"
															value="true" />
												</label>
												
												<spring:message	htmlEscape="true" code="Label.NO"></spring:message>
											
											
											</li>
										
										</ul>
										
										
							<div class="errormsg"></div>

							</div>
						</div>
					</div>

					<div id='fromothersubdistrict' class="frmpnlbg" >
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.SELECTTARGETDISTRICT"></spring:message>
							</div>
									<div>
										<label><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
										<label> 
										<form:select path="targetDistrictYes"  id="ddDistrictMergeYes" cssClass="combofield" onblur="vlidateOnblur('ddDistrictMergeYes','1','15','c');">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
												<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out> 
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
																</form:option>
															</c:if>
														</c:forEach>
										</form:select>								
									    </label>
										</div>									
						</div>
					</div>
					<div id='cvillage' class="frmpnlbg"  style="visibility: hidden; display: none;" >
						<div class="frmtxt" >
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.SELECTTARDISTRICT"></spring:message>
							</div>													
									 <div>
										<ul class="blocklist">
											<li>
												<label><spring:message htmlEscape="true"  code="Label.SELECTTARGETDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
													<label> 
													<form:select path="targetDistrictNo" id="ddDistrictMergeNo" cssClass="combofield">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
														<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out> 																				
																</form:option>
															</c:if>
														</c:forEach>
												</form:select>
													
												    </label>
													<div class="errormsg"><form:errors htmlEscape="true"  path="targetDistrictNo" ></form:errors></div>
												
											</li>
											<li>
											
											<div class="ms_container">
												<div class="ms_selectable">
													<label><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message></label><br />							
													<form:select name="select9" size="1" htmlEscape="true" id="ddSubdistrict" path="subDistrictList" multiple="multiple" class="frmtxtarea" onclick="checkcode(this.value);">
														<form:option value="">
														</form:option>
													</form:select>
												</div>
												<div class="ms_buttons">																												
															<label> <input type="button" id="btnaddVillageFull" name="Submit4" class="bttn"  value="<spring:message htmlEscape="true"  code="Button.SELECTSUBDISTRICTS"></spring:message>" onclick="addItem('contributedSubDistricts','ddSubdistrict','',false);" /> </label>
															<label> <input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="addItemList('contributedSubDistricts','ddSubdistrict');" /> </label>
														  <label> <input type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" onclick="removeAll('contributedSubDistricts','ddSubdistrict',false)" /> </label>
												</div>
											<div class="ms_selection">
												<label><spring:message htmlEscape="true"  code="Label.SUBDISTRICTTOINVALIDATE"></spring:message></label><span class="errormsg">*</span><br />
												<form:select name="select4" htmlEscape="true" id="contributedSubDistricts" size="1" multiple="multiple" path="contributedSubDistricts" class="frmtxtarea"  onclick="checkcode(this.value)"></form:select>
												<div class="errormsg">
													<form:errors path="contributedVillages" htmlEscape="true"></form:errors>
												</div>
											
												
												<div class="errormsg"></div>
												<label> <input type="button" id="chooseMoreBtn" onclick='validateSelectAddMore();'  class="btn" value="<spring:message htmlEscape="true"  code="Button.CHOOSEMOREDISTRICT"></spring:message>" /> </label>
												
											</div>	
										</div>										

											</li>
										</ul>
										<div class="errormsg"></div>
										<div class="clear"></div>
										<table id="dynamicTbl" width="664"  class="tbl_with_brdr"  style="visibility: hidden;display:none;" >
											<tr class="tblRowTitle tblclear">
												   <th><spring:message code="Label.DISTRICTNAME" htmlEscape="true"></spring:message></th>
												   <th><spring:message code="Label.SUBDISTRICTNAME" htmlEscape="true"></spring:message></th>
											</tr>
										</table>										
									</div>												
						</div>
						
					</div>
										
										
					

					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%">&nbsp;</td>
								<td width="86%">
									<div>
										<label> 
										    <input type="hidden" name="listformat" id="listformat" value="@" />
										    <input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
										</label> 
										<%-- <label> 
										    <input type="button" name="Submit9" class="btn" value="<spring:message htmlEscape="true"  code="App.DRAFT"></spring:message>"
											       onclick="javascript:go('saveAsDraftVillage.htm');" /> </label> --%> <label>
											<input type="button" name="Submit6" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											       onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form:form>
			</div>
		</div>
</body>
</html>