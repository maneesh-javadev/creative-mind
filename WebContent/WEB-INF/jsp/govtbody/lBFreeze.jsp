<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ include file="lBFreezeJs.jsp"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js'></script>


</head>
		<body>
		<div class="form_stylings">
			<div class="header">
				<h3><spring:message code="Label.LocalBodyFreeze/Unfreeze" /></h3>
				<ul class="item_list">
					<%--//this link is not working <li>
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
					</li> --%>
					<%--  //this link is not working <li>
						<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
					</li> --%>
				</ul>
			</div>
			<div class="page_content">
				<div class="form_container">
				<form:form  method="POST" action="localBodyFreezePost.htm">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="localBodyFreezePost.htm"/>"/>
					
					<input type="hidden" id="category" name="category" />
					<input type="hidden" id="lbTypeCode" name="lbTypeCode" value="0"/>
					<input type="hidden" id="parentLocalbodyCode" name="parentLocalbodyCode" value="0" />
					
					
					<div class="form_block">
						<div class="col_1">
							<ul class="form_body">
								
								
								<li>
										<label for="entity"><spring:message text="Select Local Body Type" code="Label.LBType" htmlEscape="true"></spring:message><font color="red">*</font></label>									
											
											<select name="entityName" id="entity" onchange="showHideBlock(this.value);">
											<option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
											<c:forEach var="getLocalGovtSetupList" items="${getLocalGovtSetupList}">
															<option value="${getLocalGovtSetupList.nomenclatureEnglish}"><c:out value="${getLocalGovtSetupList.localBodyTypeName}" escapeXml="true"></c:out></option>
											</c:forEach>
											</select>
															
									    <div id="errorentity"></div>
								    </li>
								    
								    <li id="DLevel" style="display:none">
								    <label for="distLevel"><spring:message code="Label.SELECT" htmlEscape="true"><span id="firstlevel"></span><span class="mndt">*</span></spring:message></label>
								    <select name="distLevel" id="distLevel" onchange="getIntegermediatePanchayatListbylblc(this.value)">
								   
								    </select>
								    <div id="errordistrict"></div>
									</li>		
									 <li id="ILevel" style="display:none">
								    <label for="interLevel"><spring:message code="Label.SELECT" htmlEscape="true"><span id="secondlevel"></span><span class="mndt">*</span></spring:message></label>
								    <select name="interLevel" id="interLevel" onchange="setVillagePanchayatCode(this.value)">
								 
								    </select>
								    <div id="errorinter"></div>
									</li>	
									
									
									
							</ul>
						</div>
					</div><br></br>
							<input type="submit" id="createhabitation" name="submmit" value="Get Data" onclick="return validateSubmitForm();"></input>
							<input type="button" id="close" name="close" value="Close" onclick="javascript:go('welcome.do');" ></input>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>