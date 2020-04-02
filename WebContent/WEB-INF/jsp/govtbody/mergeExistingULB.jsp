<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script src="js/mergeUlb.js"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesByParentcode.dwr'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd"><spring:message	code="Label.MergeLB" htmlEscape="true"></spring:message></div>  
		<form:form action="mergeUrbanLb.htm" method="POST" commandName="localGovtFormData" id="viewWardForm">
			<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="mergeUrbanLb.htm"/>" />
			<input type="hidden" name="govfilePath" id="govfilePath" />
			<input type="hidden" name="fileDisplayType" id="fileDisplayType" />
			<input type="hidden" name="captchastatus" id="captchastatus" value="<c:out value='${flag1}' escapeXml='true'></c:out>" />
			<input type="hidden" name="listformat" id="listformat" value="" />
			<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
			<input type="hidden" name="operationCode" id="operationCode" value="2" />



			<div id='LgdLBType' class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
					</div>
					<ul class="blocklist">
					     <li>
					        	<label for="ddLgdLBType">
								<spring:message	htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
									</label><span class="errormsg">*</span><br />
									 <form:select
											path="lgd_LBTypeName" id="ddLgdLBType" multiple="multiple" htmlEscape="true"
											onchange="getLBSubTypeList(this.value,1)" class="combofield">
											<c:forEach var="localBodyTypelist" varStatus="var"
												items="${localBodyType}">
												<form:option id="typeCode" htmlEscape="true"
													value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><esapi:encodeForHTML>${localBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTML></form:option>
											</c:forEach>
								</form:select>
					     </li>
					
					</ul>
					<div class="clear"></div>

					<div id="AvailableULbs">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.EXISTINGULB" htmlEscape="true"></spring:message>
							</div>
							
<ul class="blocklist">
	<li>
		<div class="ms_container">
			<div class="ms_selectable">
				<label id="selectEntity"></label>
				      <label for="availablelb"><spring:message htmlEscape="true" code="Label.AVAILABLEURBANLB"></spring:message></label>
						<form:select
														name="select9" size="1" id="availablelb" htmlEscape="true"
														path="lgd_LBDistrictAtVillage" multiple="multiple"
														class="frmtxtarea" 
														onclick="checkcode(this.value);">
														<form:option value="" htmlEscape="true">
														</form:option>
													</form:select>
													<div class="errormsg">
														<form:errors htmlEscape="true"
															path="lgd_LBDistrictAtVillage"></form:errors>
													</div>					
				</div>
			
			
			<div class="ms_buttons">
				 <input type="button" id="btnaddVillageFull"  class="bttn" name="Submit4" value="<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>" onclick="addItemVillage('choosenlb','availablelb','',false);resetList();" />
				<input 	type="button" id="btnremoveOneVillage" name="Submit4" 	value=" &lt; " class="bttn" onclick="removeItem('choosenlb','availablelb',false)" />														
				<input 	type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn"  onclick="removeAll('choosenlb','availablelb',false)" />	
																
			</div>
			
			<div class="ms_selection">
				<label id="selectedEntity"></label>
				 <label> <spring:message htmlEscape="true" code="Label.CONTRIBUTEURBANLB"></spring:message></label><span class="errormsg">*</span>
					<form:select
														name="select4" id="choosenlb" size="1" multiple="multiple"
														path="choosenlb" class="frmtxtarea" htmlEscape="true"
														
														onclick="checkcode(this.value)">
														</form:select>									
															
												
			</div>
		</div>
	</li>
</ul>
							
							
							
          <div class="clear"></div>
						</div>
					</div>
					


					<div class="frmpnlbg" id="divmergeRLB">
						<div class="frmtxt">
							<div class="frmhdtitle">
							<spring:message	code="Label.USelectULBMerge" htmlEscape="true"></spring:message>
							 </div>  
							 
							 <ul class="blocklist">
							     <li>
							         <label for="ddUrbanLocalBodyType"><spring:message
											code="Label.SELURBANTYPEBODY" htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span><br />

										<form:select id="ddUrbanLocalBodyType" path="lgd_LBTypeName"
											size="1" class="combofield" style="width: 200px" htmlEscape="true"
											onchange="getLBSubTypeList(this.value,2)">
											<form:option value="0" htmlEscape="true">
												<spring:message code="Label.SELECTLOCALBODYTYPE"
													htmlEscape="true"></spring:message>
											</form:option>
											<form:options items="${localBodyType}" htmlEscape="true"
												itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
										</form:select>
							     </li>
							     <li>
							         <label for="ddUrbanLocalBody"><spring:message	code="Label.SELURBANLOCALBODY" htmlEscape="true"></label></spring:message><span
										class="errormsg">*</span><br /> <form:select
											id="ddUrbanLocalBody" path="localBodyNameEnglish" size="1"
											class="combofield" style="width: 200px" htmlEscape="true"
											onchange="askForUpgrade(this);">
											<form:option value="0" htmlEscape="true">
												<spring:message code="Label.SELURBANLOCALBODY"
													htmlEscape="true"></spring:message>
											</form:option>
										</form:select>
							     </li>
							    <li id="trForUpgrade1" style="display: none;">
							            <label for="upgradeType"><spring:message code="Label.ASKQUESTION"
											htmlEscape="true" text="Do you want to upgrade?"></spring:message></label><span
										class="errormsg">*</span><br /> <spring:message
											code="App.YES" htmlEscape="true" text="Yes" />&nbsp;&nbsp;<form:radiobutton
											id="upgradeType" path="lbtypeLevel" value="Y"
											onclick="selectForUpgrade(this)" />&nbsp;&nbsp;&nbsp;&nbsp;
										<spring:message code="App.NO" htmlEscape="true" text="No" />&nbsp;&nbsp;<form:radiobutton
											id="upgradeType" path="lbtypeLevel" value="N"
											onclick="selectForUpgrade(this)" />
							    </li>
							    <li id="trForUpgrade2" style="display: none;">
							       <label for="ddUrbanLocalBodyTypeForUpgrade"><spring:message
											code="Label.SELUPGRADEURBANLOCALBODY" htmlEscape="true"
											text="Select for upgrade"></spring:message></label><span
										class="errormsg">*</span> <br /> <form:select
											id="ddUrbanLocalBodyTypeForUpgrade" htmlEscape="true"
											path="localbodySubtype" size="1" class="combofield"
											style="width: 200px;" disabled="true">
											<form:option value="0" htmlEscape="true">
												<spring:message code="Label.SELECTLOCALBODYTYPE"
													htmlEscape="true"></spring:message>
											</form:option>
											<form:options items="${urbanlocalbodyType}"
												itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
										</form:select>
							    </li>
							    
							     
							 
							 </ul>
							
						</div>
					</div>

				</div>


                  <br/>
                    <br/>
                    <div class="btnpnl">
                             <input type="submit"  onclick="return getData();"	name="Submit" class="bttn"	value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
                           <input type="button" name="Submit6" class="bttn" 	value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"	onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                    </div>
				
			</div>

		</form:form>

	</div>

</body>
</html>