<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<title><spring:message code="Label.INVAlVILL" htmlEscape="true"></spring:message>
</title>
<script type="text/javascript" src="js/invalidateVillage.js"
	charset="utf-8"></script>
	<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<!-- <script src="js/trim-jquery.js"></script> -->
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
		<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<!-- <link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" /> -->
<title>Create Vilage</title>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	
	var villageListContri="";
	function onSubmit()
	 {   
	    if(validateULbData())
	    	{
		selectall();
		<%-- added by pooja on 17-11-2015 --%>
		var villageListNewContri = $('#villageListNewContri').val();
		for(var i=0;i<villageListNewContri.length;i++)
			villageListContri = villageListContri + villageListNewContri[i] + ",";
		lgdDwrVillageService.getMaxPreVersionEffDateOfVillages(villageListContri.substring(0,villageListContri.length-1),{
 			async : false,
 			callback : function(data) {
 				$('#preVersionEffDate').val(data);
 			},
 			errorHandler : function() {
 				alert("Error");
 			}
 		});
		// added by Kirandeep 18/06/2014
		$('input[name=Submit]').prop('disabled',true);
		document.forms['invalidateForm'].submit();
	    	}
	 }
	
	 function validateULbData() {
			
			var ddDistrict = document.getElementById('ddDistrict').value;
			var ddSubdistrict = document.getElementById('ddSubdistrict').value;
			var villageList = document.getElementById('villageListNewContri').length;
			var ddSubdistrictMerge = document.getElementById('ddLgdLBType').value;
			var villageListMainMerge = document.getElementById('ULBListNewContri').length;
			var check = true;
      		if (ddDistrict == 0) {
				alert("Please Select District");
			
				check =false;
			}
			else if (ddSubdistrict == 0 || ddSubdistrict == "") {
				alert("Please Select Subdistrict");
			
				check =false;
			}
			else if (villageList == 0) {
				alert("Please Select Village To Invalidate");
				
				check =false;
			}
			else if (ddSubdistrictMerge == 0 || ddSubdistrictMerge == "") {
				alert("Please Select Local Body Type");
			
				check =false;
			}
			else if (villageListMainMerge == 0 ) {
				alert("Please Select Ulb To Merge");
			
				check =false;
			}
			return check;
	
		}
	 
		
	function selectall() {
		var selObj="";
		selObj = document.getElementById('villageListNewContri');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		selObj = document.getElementById('ULBListNewContri');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
	}
	$(document).ready(function() {
		
		
		var s = document.getElementById("flag2").value;
		if(s>0)
			getSubDistrictandULBList(s);
		}); 
	
	
	addItemList=function(copyId, pasteId) {
		$('#'+copyId+'> option:selected').appendTo('#'+pasteId); 
		};
		
		copyAllObjectFormOnetoAnother=function(copyId,pasteId){
			$('#'+copyId+' option').clone().appendTo('#'+pasteId);
			 $('#'+copyId).empty();
		};
</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);"
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
								</center></td>

							<td><div class="helpMsgHeader" style="width: 275px;">
									<h4>Success Message</h4>
								</div></td>
						</tr>
						<tr>
							<td><div id="successMsg" class="successfont">
									<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
								</div>
							</td>
						</tr>
					</table>

				</c:if>

				<c:if test="${!empty family_error}">

					<table>
						<tr>
							<td rowspan="2"><div class="failur"></div></td>

							<td><center>
									<div class="helpMsgHeader" style="width: 275px;">
										<b>Failure Message</b>
									</div>
								</center></td>
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
						<td rowspan="2"><div class="errorImg"></div></td>
						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Error Message</h4>
							</div></td>
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
				<label><spring:message code="Label.MERGEVIL" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">
				<!--  //this link is not working <li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a>
				</li>

				<li><a href="#" class="frmhelp">Help</a></li> -->
			</ul>
		</div>

	
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="mergeandInvalidateVillageDetails.htm" id="invalidateForm" method="POST" commandName="invalidatevillage" >
			
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="enterInvalidateVillageOrderDetails.htm"/>"/>
			<input type="hidden" name="stateCode"  id ="stateCode" value="<c:out value='${stateCode}'/>"/>
			<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}'/>"/>
            <input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}'/>"/>	
            <form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}"/>
			<input type="hidden" name="preVersionEffDate" id="preVersionEffDate" />	
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.MERGEUNMAPVIL" htmlEscape="true"></spring:message>
							</div>
							
						<div>
							<!-- <table width="100%" class="tbl_no_brdr"> -->
							<ul class="blocklist" >								
								<li>									
										<c:if test="${flag1 eq 1}">
											<label for="ddDistrict"><spring:message  code="Label.SELECTDISTRICT"  htmlEscape="true"></spring:message><span class="errormsg">*</span></label><br /><br />
										</c:if>
										<c:if test="${flag1 eq 0}">
											<label for="ddDistrict"><spring:message  code="Label.DISTRICTCAPS"></spring:message></label><br /><br /></label>
										</c:if>
										<form:hidden htmlEscape="true" path="operation" value="I"/>
										<form:select htmlEscape="true" path="districtNameEnglish" class="combofield" id="ddDistrict" 
												             onchange="getSubDistrictandULBList(this.value);EmptlyVillageList();" onblur="vlidateOnblur('ddDistrict','1','15','c');">
												             <c:if test="${flag1 eq 1}">
												<form:option value="0"  htmlEscape="true"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></form:option>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
														<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}"  htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true"  htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
																</form:option>
															</c:if>
														</c:forEach>
												
												</c:if>
												<c:if test="${flag1 eq 0}">
											
												<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode"  htmlEscape="true"/>
												</c:if>
											    </form:select> </label> <br />
										
										<div class="errormsg">
										</div>
										<span class="errormsg" id="ddDestDistrict_error">
									    </span>
										
								</li>

								<li>
									<label for="ddSubdistrict"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /><br />
										<label> <form:select htmlEscape="true" path="subdistrictNameEnglish" class="combofield" id="ddSubdistrict" 
										 onchange="getunmapViltoUlb();">
											   </form:select> </label>
										
								
								</li>
								<li>
									
										<div class="errormsg"></div>							
										<label>Please Select Unmapped Villages</label>																	
									
								</li>
								</ul>
								<ul class="blocklist">
								
								<li>																	
									
										
										
										
						
									<div class="ms_container">
										<div class="ms_selectable">
												<label for="villageListMainContributing"><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message> </label></br>
												
												<form:select htmlEscape="true" name="select9" size="1" id="villageListMainContributing" path="villageList" multiple="multiple" class="frmtxtarea" ></form:select>
												
										</div>
										<div class="ms_buttons">
																										
											<label> 
											    <input type="button"  class="bttn" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;"
													   onclick="addItem('villageListNewContri','villageListMainContributing','',false);" />
											</label>														
																								
											<label> 
											    <input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" 
													   onclick="addItemList('villageListNewContri','villageListMainContributing')" />
											</label>														  
										
											<label> <input
													type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" 
													onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing')" />
											</label>														 
										
										</div>
										<div class="ms_selection">
										
										<strong><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message></strong><span class="errormsg">*</span><br />
										 <form:select htmlEscape="true" name="select4" id="villageListNewContri" size="1" multiple="multiple" path="invalidateVillageList" class="frmtxtarea" ></form:select>
										<div class="errormsg"><form:errors htmlEscape="true" path="invalidateVillageList" ></form:errors></div>
										
										</div>
										
									
									</div>
																										
									</li>		
								
							</ul>	
							<div class="clear"></div>
							
							<ul class="blocklist">
									<li>
											 
										
										<label for="ddLgdLBType"><spring:message
											code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span
											class="errormsg">*</span> </label><br /> <form:select
											path="ulbList" id="ddLgdLBType"
											onchange="getULBListbyLBtype(this.value);" class="combofield">
											<!--tierSetupCode id Changed  -->
											<form:option value="0">
												<spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
											</form:option>

											<c:forEach var="localBodyTypelist" varStatus="var"
												items="${localBodyTypelist}">
												<form:option id="typeCode"
													value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
			    					
											</c:forEach>
										</form:select>											
 									
								
									
									</li>
							
							
								<li>
										<div class="ms_container">
										<div class="ms_selectable">
												<label for="ULBListMainContributing"><spring:message code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message> </label></br>
												<form:select htmlEscape="true" name="select9" size="1" id="ULBListMainContributing" path="coveredLandRegionByULB" multiple="multiple" class="frmtxtarea"></form:select>
										</div>
										<div class="ms_buttons">
												<label> 
												    <input type="button"  id="btnaddVillageFull" class="bttn" name="Submit4" value="Select ULB&gt;&gt;" onclick="addItemSinglevalidation('ULBListNewContri','ULBListMainContributing','',false);" />
												</label>
												 <label> 
												    <input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="addItemList('ULBListNewContri','ULBListMainContributing')" />
												</label>
													
										</div>
										<div class="ms_selection">
												<strong><spring:message code="Label.SELECTSINULB" htmlEscape="true"></spring:message></strong><span class="errormsg">*</span><br />
												 <form:select htmlEscape="true" name="select4" id="ULBListNewContri" size="1" multiple="multiple" path="selectedCoveredLandRegionByULB" class="frmtxtarea"></form:select>
										</div>
										
									
									</div>
								
								</li>
							
							</ul>
							
							<div class="clear"></div>
						</div>																
							
						</div>
					</div>
		
					<div class="btnpnl">
										<label> 
										    <input type="button" onclick="onSubmit();" name="Submit" class="btn" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
										</label> 
											<input type="button" name="Submit6" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
											       onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
								
					</div>
							
		
						</div>
					</div>

					<div id='cvillage'  >
						<div class="frmtxt" style="visiblity:'hidden';display:none;">
							<div class="frmhdtitle">
								<spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message><span class="errormsg">*</span><br />
							</div>						
						</div>
									
				</div>
			</form:form>
			 <script src="/LGD/JavaScriptServlet"></script>
			
		</div>
		
</body>
</html>