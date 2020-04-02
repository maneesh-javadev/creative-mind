
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>


<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
	<!-- <script src="js/convertRLBtoTLB.js"></script> -->

	<script src="js/mapParliament.js"></script>
	<script src="js/common.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/validation.js"></script>
	<link href="css/error.css" rel="stylesheet" type="text/css" />
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	</script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
	<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	<script src="datepicker/calender.js"></script> -->
    <script type="text/javascript" src="js/map_Ward.js"
	charset="utf-8"></script>
	<script src="js/new_ward.js"></script>
	<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
    
	$( document ).ready(function() {
		var rightpanel = document.getElementById("rpnls");
		
		rightpanel.style.overflow = "hidden";
		
		
	});
	</script>
	
	
	
<script src="<%=contextPath %>/jquery-1.7.2.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.core.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.widget.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.button.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.mouse.js"></script>	
<script src="<%=contextPath %>/ui/jquery.ui.draggable.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.position.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.dialog.js"></script>
<script src="<%=contextPath %>/ui/jquery.validate.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.accordion.js"></script>
<script src="<%=contextPath %>/external/jquery.bgiframe-2.1.2.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.resizable.js"></script>
<script src="<%=contextPath %>/ui/jquery.effects.core.js"></script>
<script src="<%=contextPath %>/ui/jquery.effects.blind.js"></script>
<script src="<%=contextPath %>/ui/jquery.effects.explode.js"></script>
<script type="text/javascript"  src="<%=contextPath %>/js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="<%= contextPath %>/js/jquery.tabs.pack.js"></script>
<script src="<%= contextPath %>/js/jquery-blink.js" language="javscript" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<link rel="stylesheet" href="<%=contextPath %>/css/demos.css"></link>
<link rel="stylesheet" href="<%=contextPath %>/themes/base/jquery.ui.all.css"></link>
	
	
<script>
	dwr.engine.setErrorHandler(function(message,exception){
		var form = document.getElementById("ajaxErrorFrom");			
		form.submit();
	});
	window.onerror = noError;
	$(document).ready(function() {
			$('.blink').blink();
	});
	function noError() {
		return true;
	}

	displayLoadingImage = function() {
		$.blockUI({ 
			theme: true,
			title: 'Loading...',
			message: $('#displayBox'), 
	        css: { 
	            top:  ($(window).height()) /2 + 'px', 
	            left: ($(window).width() - 100) /2 + 'px',
	            border: 'none', 
	            padding: 'none'                    
	          
	        } 
	    }); 
	};
	
	showLoadingImage = function() {
		$.blockUI({ 
			theme:true,
			title: 'Loading...',
			message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/images/ajax-loader-2.gif'/></div>"
	    }); 
	};

	hideLoadingImage = function(){
		$.unblockUI();
	};
	
	dwr.engine.setPreHook(showLoadingImage);
	dwr.engine.setPostHook(hideLoadingImage);
	
	
	customAlert = function(message){
		$("#cAlert").html(message);
		$("#cAlert").dialog({
			title: "Alert",
			resizable : false,
			height : 160,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	};
	</script>
</head>

<body >

	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.MapZpconstituency" htmlEscape="true" text="Map ZP Constituency"></spring:message></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
				</li>
				<li>
					<a href="#" class="frmhelp">Help</a>
				</li>
			</ul>
		</div>
		<div class="clear"></div>



		<div class="frmpnlbrdr">
			<form:form   action="mapZpConsituencyData.htm" method="POST" id="form1" commandName="localGovtBodyForm" >
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="mapZpConsituencyData.htm"/>" htmlEscape="true" />
				<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" htmlEscape="true" />
				<form:hidden path="listformat" value="" id="listformat" htmlEscape="true"/>
				<form:hidden path="contVillage" value="" id="contVillage" htmlEscape="true"/>
				<input type="hidden" name="mappedvillageList" id="mappedvillageList"	value="" htmlEscape="true" />		
				<input type="hidden" name="mappedgpList" id="mappedgpList"	value="" htmlEscape="true"/>	
				<form:hidden path="lgd_hiddenLbcList" value="" id="deletedlbList" htmlEscape="true"/>
				<form:hidden path="villagePanchayat" value="" id="deletedvillageList" htmlEscape="true"/>				
				<form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SelectConstituency" htmlEscape="true" text="Select Constituency "></spring:message>
							</div>
							<div class="block"  >
								<ul class="blocklist">
									<li>
											<label><spring:message code="Label.ZPanchyat" text="Select Zilla  Panchyat"></spring:message></label><span class="errormsg">*</span><br />
											
											<c:if test="${twoTierStructure == true}">
											<form:select htmlEscape="true" path="districtPanchayatName"
													class="combofield" id="ZilaPanchyatID" style="width: 150px"
												onchange="getZPWardsLists(this.value);getVillagePanchyatListforzp(this.value);">
	
												<form:option value="0" htmlEscape="true">
													  <spring:message code="Label.ZPanchyat"
														text=" - Select - "></spring:message>
												</form:option>
												
												<c:forEach items="${districtPanchayatList}" var="distListvar">
													  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													    <form:option value="${distListvar.localBodyCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
													  </c:if>  
													  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													     <form:option value="${distListvar.localBodyCode}" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
													  </c:if>
												</c:forEach>
										
												
												
												<%-- <form:options items="${districtPanchayatList}"
													itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
	
	
												</form:select> 
											</c:if>
											
											<c:if test="${twoTierStructure == false}">
											<form:select htmlEscape="true" path="districtPanchayatName"
													class="combofield" id="ZilaPanchyatID" style="width: 150px"
													onchange="getZPWardsLists(this.value);getZPIntermediatePanchyat(this.value);">
		
													<form:option value="0" htmlEscape="true">
														<spring:message code="Label.ZPanchyat"
															text=" - Select - "></spring:message>
													</form:option>
													
													<c:forEach items="${districtPanchayatList}" var="distListvar">
													  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													    <form:option value="${distListvar.localBodyCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
													  </c:if>  
													  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													     <form:option value="${distListvar.localBodyCode}" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
													  </c:if>
													</c:forEach>
													
													
												<%-- 	<form:options items="${districtPanchayatList}"
														itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
		
		
												</form:select> 
											</c:if>
											</label> <br /> <br /> 
	
											<div class="errormsg"></div> <span class="errormsg"
											id="ddDestDistrict_error"> </span>
									</li>
									<li>
										<label><spring:message code="Label.SELECTWARD"
												text="Select Ward Name " htmlEscape="true"></spring:message></label><span
										class="errormsg">*</span>
										<br /> <label> 
										<c:if test="${twoTierStructure == true}">
										<form:select htmlEscape="true"
													path="ward_number" class="combofield" id="WardCode" onchange="fillMappedEntities1(this.value);checkExistingGramPanchayat()"
													style="width: 150px" >
												</form:select>
										</c:if>
										
										<c:if test="${twoTierStructure == false}">
										<form:select htmlEscape="true"
													path="ward_number" class="combofield" id="WardCode" onchange="fillMappedEntities(this.value); checkExistingGramPanchayat();"
													style="width: 150px" >
												</form:select>
										</c:if>
										
										<br /> <br /> 
										</label>
									</li>
									<li>
										<c:if test="${twoTierStructure == false}">
										<label><spring:message code="Label.SELECTBLOCKPAN"
													text="Block Panchyat" htmlEscape="true"></spring:message></label><span
											class="errormsg">*</span>
										<br /> <label> 
										
										
										<form:select htmlEscape="true"
													path="intermediatePanchayat" class="combofield" id="InterPanchyatID"
													style="width: 150px" onchange="getVillagePanchyatListforzp(this.value);"> <br /> <br /> 
												</form:select>
										</label>
										</c:if>
									</li>
								</ul>
							</div>
								<ul class="blocklist"  >
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<spring:message code="Label.AVAILABLEGRAMPANCHAYT" htmlEscape="true"></spring:message>
												</label>
												<form:select htmlEscape="true" name="select9" size="1" id="GpListAvailable" path="grampanlbid" multiple="multiple" class="frmtxtarea"></form:select>
											</div>
											
											
											<div class="ms_buttons">
												<label> 
													<input type="button" id="btnaddSubDistrictFull" name="Submit7"	class="bttn"  value=" Whole &gt;&gt;" onclick="addItemforZP1('GpListSelected','GpListAvailable','FULL',true)" />
												</label>
												
												<label> 
													<input type="button" class="bttn" id="btnremoveOneSubDistrict" name="Submit8" value=" &lt; " onclick="removesingleItemforgp('GpListSelected','GpListAvailable',true);" />
												</label>
												
												<label> 
													<input type="button" class="bttn" id="btnremoveAllSubDistricts" name="Submit9" value="&lt;&lt;"	onclick="removeAllsgpward('GpListSelected','GpListAvailable',true)" />
												</label>
												
												<label>
													<input type="button" class="bttn" id="btnaddSubDistrictPart" name="Submit10"  value=" Part &gt;&gt;" onclick="addItemforZP('GpListSelected','GpListAvailable','PART',true);" />
												</label>
											</div>
											
											<div class="ms_selection">
												<label>
													<spring:message code="Label.CONTRIGRAMPANCHAYT" htmlEscape="true"></spring:message><span class="errormsg">*</span>
												</label>
												<form:select htmlEscape="true" name="select4" id="GpListSelected" size="1" multiple="multiple" path="choosenlb" class="frmtxtarea" ></form:select>
												<div class="errormsg">
												<form:errors htmlEscape="true" path="choosenlb"></form:errors>
												</div>
												<label>
													<input type="button" id="partgpVillage" name="partgpVillage" onclick="getVillageListsforzpwardmapping()" class="bttn" value= "<spring:message  code="Button.GETVILLAGEL"></spring:message>"  />
												</label>
											</div>
										</div>
									</li>
								</ul>
								
								
								
								<ul class="blocklist"  >
									<li>
										<div class="ms_container">
											<div class="ms_selectable">
												<label>
													<spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message>
												</label>
												<form:select htmlEscape="true" name="select9" size="1" id="VillageListAvailable" path="localGovtBodyVillageList" multiple="multiple" class="frmtxtarea" ></form:select>	
											</div>
											<div class="ms_buttons">
												<label> 
													 <input type="button" id="btnaddSubDistrictFull" name="Submit7" class="bttn" value=" Whole &gt;&gt;" onclick="addItemforZP('VillageListContributing','VillageListAvailable','FULL',true)" />
												</label>
												
												<label> 
													<input type="button" class="bttn" id="btnremoveOneSubDistrict" name="Submit8" value=" &lt; " onclick="removesingleItemforgp('GpListSelected','GpListAvailable',true);" />
												</label>
											</div>
											
											<div class="ms_selection">
												<label>
													<spring:message code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message>
												</label>
												<form:select htmlEscape="true" name="select4" id="VillageListContributing" size="1" multiple="multiple" path="contVillage" class="frmtxtarea"></form:select>
											</div>
										</div>
									</li>
									<div class="errormsg"></div>
								</ul>
							<div class="clear"></div>
						</div>
					</div>
					<div class="btnpnl">
						<ul class="listing">
							<li>
								<c:if test="${twoTierStructure == false}">
									<label> 	<input type="button" onclick="checkData();"
										name="Submit" class="bttn"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
									</label>
								</c:if>
								<c:if test="${twoTierStructure == true}">
								  <label> 	<input type="button" onclick="checkData1();"
									name="Submit" class="bttn"
									value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
								  </label>
								</c:if>
								<label>
								 <input type="button" name="Submit6" class="bttn"
									value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
									onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label>
								</li>
							</ul>						
						</div>
					</div>
			</form:form>
			<div id='cvillage'>
					  <div class="frmtxt" style="visiblity: 'hidden'; display: none;">
						<div class="frmhdtitle">
							<spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message>
							<span class="errormsg">*</span><br />
						</div>
					</div>
				</div>
	<script src="/LGD/JavaScriptServlet"></script>
</div>
</body>
</html>