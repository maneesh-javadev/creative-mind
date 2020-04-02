<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<title><spring:message code="Title.CREATENEWSUBDISTRICT"></spring:message></title>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<script type="text/javascript" src="js/new_sub_district.js" charset="utf-8"></script>
<style>		
		label, input { display:inline; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 550px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
	</style>
<script>

function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

	//$(function() {
	function addanother(val1 , val2){		
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		$( "#dialog-confirm" ).dialog({
			resizable: false,
			height:140,
			modal: true,
			buttons: {
				"Yes": function() {
					validateForm(val1 , val2);
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			}
		});	

		/* $("subDistrictListNew").empty();
		$("villageList").empty(); */
		}
	//});
	</script>
	
	<script>
	$(function() {
		$( "#accordion" ).accordion({
			event: "mouseover"
		});
	});
	
	function updateTips( t ) {
		tips = $( ".validateTips" );		
		tips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			tips.removeClass( "ui-state-highlight", 8000 );
		}, 8000 );
	}

	function checkLength( o, n, min, max ) {
		if ( o.val().length > max || o.val().length < min ) {			
			o.addClass( "ui-state-error" );
			updateTips( "Length of " + n + " must be between " +
				min + " and " + max + "." );
			return false;
		} else {
			return true;
		}
	}

	function checkRegexp( o, regexp, n ) {		
		if ( !( regexp.test( o.val() ) ) ) {			
			o.addClass( "ui-state-error" );
			updateTips( n );
			return false;
		} else {
			return true;
		}
	}
	function openwin(subdistrictNameEnglish,subdistrictNameLocal,aliasEnglish,aliasLocal,census2011Code,sscode,count){		
	document.getElementById('subdisteng').value=subdistrictNameEnglish;
	document.getElementById('subdistloc').value=subdistrictNameLocal;
	document.getElementById('aleng').value=aliasEnglish;
	document.getElementById('alloc').value=aliasLocal;
	document.getElementById('cencd2011').value=census2011Code;
	document.getElementById('sscd').value=sscode;
	document.getElementById('villageNumber').value=count;
	
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
	var subdisteng = $( "#subdisteng" ),
	subdistloc = $( "#subdistloc" ),
	aleng = $( "#aleng" ),
	alloc = $( "#alloc" ),
	cencd2011 = $( "#cencd2011" ),
	sscd = $( "#sscd" ),
		allFields = $( [] ).add( subdisteng ).add( subdistloc ).add( aleng ).add( alloc ).add( cencd2011 ).add( sscd ),
		tips = $( ".validateTips" );
	
	$( "#create-user"+count )
	.button()
	.click(function() {		
		$( "#dialog-form" ).dialog( "open" );
	});
	
	
	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 500,
		width: 350,
		modal: true,
		buttons: {
			"Save": function() {
			 	var bValid = true;
				allFields.removeClass( "ui-state-error" );				
				bValid = bValid && checkLength( subdisteng, "subdisteng", 1, 50 );
				bValid = bValid && checkDuplicate2(subdisteng,document.getElementById('subdisteng').value,subdistrictNameEnglish);	
				/* if(document.getElementById('cencd2011').value!=null && document.getElementById('cencd2011').value!=""){
				bValid = bValid && checkRegexp( cencd2011,new RegExp("^[0-9]+$"), "Census2011 Code only allow 0-9" );	
				} */
				if(document.getElementById('sscd').value!=null && document.getElementById('sscd').value!=""){
				bValid = bValid && checkRegexp( sscd, new RegExp("^[a-zA-Z0-9]+$"), "State Specific Code only allow 0-9 and Alphabets");	
				}
				if ( bValid ) {		
 					document.getElementById('subdistengtemp').value=document.getElementById('subdisteng').value; 					
					document.getElementById('subdistloctemp').value=document.getElementById('subdistloc').value;
					document.getElementById('alengtemp').value=document.getElementById('aleng').value;
					document.getElementById('alloctemp').value=document.getElementById('alloc').value;
					document.getElementById('cencd2011temp').value=document.getElementById('cencd2011').value;
					document.getElementById('sscdtemp').value=document.getElementById('sscd').value;	
					
					document.forms['newsubdistrictform'].action ="updatesubdistrictdtls.htm?<csrf:token uri='updatesubdistrictdtls.htm'/>";
					document.forms['newsubdistrictform'].submit();					
					$( this ).dialog( "close" );
				} 				
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		 close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		} 
	});
	return false;
	}
 	$(document).ready(function() {
		var s = document.getElementById("flag2").value;  
		if(s>0)
		getSubDistrictList(s);
		}); 

	</script>
<title>Create Sub District</title>

<script>

function validateForm(val1 , val2){
	
			document.getElementById('buttonClicked').value=val2;
			var flag = checkDuplicate(val1);	
			if(flag==true){
				formSubmitAdd(val2);
			}	
	 }

function checkDuplicate(value){		
	var subDistrictName = '<%=session.getAttribute("subDistrictNames")%>';
	var arraysubDistrictName = subDistrictName.split(",");
	for(var i=0; i<arraysubDistrictName.length; i++) {
		var valueold = arraysubDistrictName[i].replace(/^\s+|\s+$/g, "");		
		if(value.toUpperCase()==valueold.toUpperCase()){			
				$( "#dialog" ).dialog({
					resizable: false,
					height:140,
					modal: true,
					buttons: {
						"Ok": function() {							
							$( this ).dialog( "close" );
						},
						
					}
				});	
		
			//alert("Sub district name can not be same as previously created Sub districts");
			return false;
		}		
	}
	
		return true;
	
}

function checkDuplicate2(o,value,oldValue){			
	var subDistrictName = '<%=session.getAttribute("subDistrictNames")%>';
	var arraysubDistrictName = subDistrictName.split(",");	
	for(var i=0; i<arraysubDistrictName.length; i++) {
		var valueoldArray = arraysubDistrictName[i].replace(/^\s+|\s+$/g, "");		
		if(value.toUpperCase()==valueoldArray.toUpperCase()){
			if(value.toUpperCase()==oldValue.toUpperCase()){
				
			}
			else{
				o.addClass( "ui-state-error" );
				updateTips( "Sub district name can not be same as previously created Sub districts" );
				return false;
			   }
			} 		
		}		
	
		return true;

	}
	
		
	


function nextButton(val){
	document.getElementById('buttonClicked').value=val;
	if(val == 'Next'){
	formSubmitAdd(val);
	}
	if(val =='NextNoValidation'){
	document.forms['newsubdistrictform'].submit();
	}
}

</script>

<%@include file="../common/dwr.jsp"%>
</head>
<body onload="onloadSelect()">
	
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td>				
				<div id="frmcontent">
					<div class="frmhd">
					<h3 class="subtitle"><spring:message code="Title.CREATENEWSUBDISTRICT" htmlEscape="true"></spring:message>&nbsp;&nbsp;${subDistrictTitle}</h3>
						<ul class="listing">
							<%--//these links are not working <li>
								<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
							</li>
							<li>
								<a href="#" class="frmhelp"><spring:message code="App.HELP" htmlEscape="true"></spring:message></a>
							</li> --%>
						</ul>
					</div>
						<div class="clear">	</div>	
						<div class="frmpnlbrdr">
							<form:form action="new_subdistrict.htm" method="POST" commandName="newsubdistrictform" enctype="multipart/form-data">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="new_subdistrict.htm"/>"/>							  
							    
								<div id="subDistrictdetailId" class="frmpnlbg" style="${display}">
									<div class="frmtxt">
										<div class="frmhdtitle">
											<label><spring:message code="Label.SUBDISTRICTDETAIL" htmlEscape="true"></spring:message></label>
										</div>
											<c:set value="1" var="count"></c:set>
											<c:set value="WHITE" var="oddeven"></c:set>
										    <div id="accordion" width="100%">										    
										      <c:forEach var="subDistrictInfoList" items="${subDistrictInfoList}">
													<h3><label><a href="#">${subDistrictInfoList.subdistrictNameEnglish }</a></label></h3>
													<div>
													<table width="100%" class="tblRowTitle tblclear">
										     		<tr>
														<td align="center" width="5%"><label><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></label></td>
														<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAME"></spring:message></label></td>
														<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.CONTVILLAGES"></spring:message></label></td>		
														<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.CONTSUBDISTRICT"></spring:message></label></td>
														<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.SDSTATUS"></spring:message></label></td>				
														<td align="center" width="10%"><label><spring:message htmlEscape="true"  code="Label.SDSTATUS"></spring:message></label></td>	
													</tr>	
													</table>
 													<table width="100%" class="tbl_no_brdr">
 													<tr>
 														<td  align="center" width="5%">${count}</td>	
 														<td  align="center" width="10%"><label>${subDistrictInfoList.subdistrictNameEnglish }</label></td>
 													<td align="center" width="10%"> 													
 													<ul style="text-align: center;">
 													<c:forEach var="villageList" items="${subDistrictInfoList.contributeVillages}">													
														<li>${villageList}</li>														
													</c:forEach>
													</ul>
													</td>
													<td align="center" width="10%">
														
													<ul>
													<c:forEach var="contributesubDistrict" items="${subDistrictInfoList.contributesubDistrict}">
														<li>${contributesubDistrict}</li>														
													</c:forEach>
													</ul>
													</td>
													<td align="center" width="10%"><button id="create-user${count}" onclick="return openwin('${subDistrictInfoList.subdistrictNameEnglish }','${subDistrictInfoList.subdistrictNameLocal }','${subDistrictInfoList.aliasEnglish }','${subDistrictInfoList.aliasLocal }','${subDistrictInfoList.census2011Code }','${subDistrictInfoList.sscode }','${subDistrictInfoList.villageNumber}');">Modify</button></td>
													<td align="center" width="10%"><font color="#FF3232"><label ><span class="blink"><spring:message code="Label.PENDING" htmlEscape="true"></span></spring:message></label></font></td>
													</tr>
													</table>
													</div>	
													<c:set value="${count+1}" var="count"></c:set>												
												</c:forEach>	
											</div>
									
									</div>
								
								</div>	
		 <div id="topDiv" style="${topdiv}">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<c:if test="${flag1 eq 1}">
								<label><spring:message  code="Label.SELECTDISTRICT"></spring:message></label>
							</c:if>
							<c:if test="${flag1 eq 0}">
								<label><spring:message  code="Label.DISTRICTCAPS"></spring:message></label>
							</c:if>
						</div>
						<div class="block"  >
							<ul class="blocklist">
								<li>
									<c:if test="${flag1 eq 1}">
									<label for="ddDistrict"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></label>
									<strong><span id ="required" class="errormsg">*</span></strong><br /></c:if>
									<label> <form:select path="districtCode"
											class="combofield" id="ddDistrict" style="width: 150px"
											onchange="getSubDistrictList(this.value)" disabled="${disabled}">
											<c:if test="${flag1 eq 1}">
											
											<form:option value="0">Select District</form:option>
											
												<c:forEach items="${distList}" var="distListvar">
													  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													    <form:option value="${distListvar.districtCode}" disabled="true">${distListvar.districtNameEnglish}</form:option>
													  </c:if>  
													  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													     <form:option value="${distListvar.districtCode}">${distListvar.districtNameEnglish}</form:option>
													  </c:if>
												</c:forEach>
											
											
											<%-- <form:options items="${distList}"
												itemLabel="districtNameEnglish"
												itemValue="districtCode" /> --%>
												
												</c:if>
											<c:if test="${flag1 eq 0}">
											
												<c:forEach items="${distList}" var="distListvar">
													  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													    <form:option value="${distListvar.districtCode}" disabled="true" selected="selected" >${distListvar.districtNameEnglish}</form:option>
													  </c:if>  
													  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													     <form:option value="${distListvar.districtCode}" selected="selected" >${distListvar.districtNameEnglish}</form:option>
													  </c:if>
													  <c:if test="${distListvar.operation_state == null}">
													     <form:option value="${distListvar.districtCode}" selected="selected" >${distListvar.districtNameEnglish}</form:option>
													  </c:if>
												</c:forEach>
											
										
								<%-- 		<form:options items="${distList}"
											itemLabel="districtNameEnglish"
											itemValue="districtCode" selected="selected" /> --%>
											</c:if>
												
										</form:select> 
										</label> 
									<form:hidden path="operation" value="C" htmlEscape="true"/>
									<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>	
									<div class="errormsg"></div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			
									
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Legend.GENERALDETAILOFNEWSUBDISTRICT" htmlEscape="true"></spring:message>
							</div>
							<div class="block"  >
								<ul class="blocklist">
									<li>
										<label for="OfficialAddress"><spring:message code="Label.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message></label>
										<strong><span id ="required" class="errormsg">*</span></strong><br />
										<label>
                                        <form:input path="subdistrictNameEnglish"  id="OfficialAddress" class="frmfield" onfocus="show_msg('OfficialAddress')" onblur="uniquesubdistrictNameValidation(this.value);chekcalphanumeric(this.value,1);"  htmlEscape="true" maxlength="50"/>
                                        <div id="UniquesubDistrictError" style="color: red;"></div>
										</label>
										<form:errors path="subdistrictNameEnglish" cssClass="errormsg" htmlEscape="true"/>
										<div class="errormsg"></div>
									</li>
									<li>
										<label for="subdistrictNameLocal"><spring:message code="Label.NAMEOFNEWSUBDISTRICTLOCAL" htmlEscape="true"></spring:message></label><br />
										<label> <form:input path="subdistrictNameLocal" id="subdistrictNameLocal" class="frmfield" htmlEscape="true" maxlength="50" onblur="validatelocalCharachterforsubdistrict(this.value,1);"/>
										</label>
										<form:errors path="subdistrictNameLocal" cssClass="errormsg" htmlEscape="true"/> <br />
										<div class="errormsg"></div>
									</li>
									<li>
										<label for="aliasEnglish"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label><br />
										<label> <form:input path="aliasEnglish" id="aliasEnglish" class="frmfield" htmlEscape="true" maxlength="50" onblur="chekcalphanumeric(this.value,2);"/> </label>
										<form:errors path="aliasEnglish" cssClass="errormsg" htmlEscape="true"/>
										<div class="errormsg"></div>
									</li>
									<li>
										<label for="aliasLocal"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label><br />
										<label> <form:input path="aliasLocal" id="aliasLocal" class="frmfield" htmlEscape="true" maxlength="50" onblur="validatelocalCharachterforsubdistrict(this.value,2);"/>
										</label>
										<form:errors path="aliasLocal" cssClass="errormsg" htmlEscape="true"/>
										<div class="errormsg"></div>
									</li>
									<%-- <li>
										<label for="census2011Code"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label><br />
										<label> <form:input path="census2011Code" id="census2011Code" class="frmfield" htmlEscape="true"  onblur="numericvaluesTest(this.value,1)" maxlength="7"/> </label>
										<form:errors path="census2011Code" cssClass="errormsg" htmlEscape="true"/>
										<div class="errormsg"></div>
									</li> --%>
									<li>
										<label for="sscode"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label><br />
										<label> <form:input path="sscode" id="sscode" class="frmfield" htmlEscape="true" onblur="chekcalphanumeric(this.value,3);"  maxlength="5" />
										</label>
										<form:errors path="sscode" cssClass="errormsg" htmlEscape="true"/>
										<div class="errormsg"></div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<%@ include file="../common/headquarter.jspf" %>
					<%@ include file="../common/gis_nodes.jspf" %>
					
					<div class="frmpnlbg" >
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
							</div>
							<ul class="blocklist">
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
											<label>
												<spring:message code="Label.SUBDISTRICTS" htmlEscape="true"></spring:message>
											</label><br>
											<form:select name="select9"
													size="1" id="ddSubdistrict" path="subDistrictList"
													multiple="multiple" class="frmtxtarea">      <%--   disabled="${disabled}"> --%>
													<form:options items="${listSubDistrict}" itemLabel="subdistrictNameEnglish"
													itemValue="aliasEnglish"/>
											</form:select>
										</div>
										
										
										<div class="ms_buttons">
											<input type="button"  class="bttn" id="btnaddSubDistrictFull" name="Submit4" value="Select Full Sub Districts&gt;&gt;" onclick="CheckthenaddItem('subDistrictListNew','ddSubdistrict','FULL',true)" />
											<input type="button" class="bttn" id="btnremoveOneSubDistrict" name="Submit4" value=" &lt; " onclick="removepartVilagelist();removeItem('subDistrictListNew','ddSubdistrict',true);" />
											<input type="button"  class="bttn" id="btnremoveAllSubDistricts" name="Submit4" value="&lt;&lt;" onclick="removeAllListItems('subDistrictListNew','ddSubdistrict',true)" />
											<input type="button"  class="bttn" id="btnaddSubDistrictPart" name="Submit4" value="Select Part Sub Districts&gt;&gt;" onclick="addItem('subDistrictListNew','ddSubdistrict','PART',true);" />
										</div>
										
										<div class="ms_selection">
											<label>
												<spring:message code="Label.CONTRIBUTINGSUBDISTRICTLIST" htmlEscape="true"></spring:message>
											</label>
											<form:select name="select4" id="subDistrictListNew" size="1" multiple="multiple" path="contributedSubDistricts" class="frmtxtarea"> <%-- disabled="${disabled}" --%>
											<form:options items="${listSubDistrict1}" itemLabel="subdistrictNameEnglish" itemValue="aliasEnglish"/>  <%-- items="${listSubDistrict1}" --%>
											</form:select><br></br>
											<label> 
												<input type="button"  class="bttn" id="partSubdistrict" name="Submit7" onclick="getVillageList()" value="<spring:message code="Button.GETPARTSUBDISTRICT" htmlEscape="true"></spring:message>" />
											</label>
										</div>
										<div style="height: 15px; padding-top: 3px;"class="errormsg"></div>	
										<div class="errormsg"></div>
									</div>
								</li>
							</ul>
							
							<ul class="blocklist">
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
											<label>
												<spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message>
											</label><br>
											<form:select name="select9"
													size="1" id="villageList" path=""
													multiple="multiple" class="frmtxtarea">
													<form:options items="${listVillageList}" itemLabel="villageNameEnglish" 
													itemValue="aliasEnglish"/> <%-- items="${listVillageList}" --%>
											</form:select>
											</div>
										
										
										<div class="ms_buttons">
											<label> 
												<input type="button" class="bttn" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;" onclick="addItem('villageListNew','villageList','FULL',true)" />
											</label>
											
											<label> 
												<input type="button" class="bttn" id="btnremoveOneVillage" name="Submit4" value=" &lt; " onclick="removeItem('villageListNew','villageList',true)" />
											</label>
											
											<label> 
												<input type="button" class="bttn" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" onclick="removeAll('villageListNew','villageList',true)" />
											</label>
										</div>
										
										<div class="ms_selection">
											<label>
												<spring:message code="Label.CONTRIBUTINGVILLAGELISTSD" htmlEscape="true"></spring:message>
											</label>
											<form:select name="select4"
												id="villageListNew" size="1" multiple="multiple" path="contributedVillages"
												class="frmtxtarea">
												<form:options items="${listcontVillageList}" itemLabel="villageNameEnglish"
												itemValue="aliasEnglish"/>
											</form:select>	
										</div>
										<div class="errormsg"></div>
									</div>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
					</div>			
				</div>	
				
		<div class="block">
				<input type="hidden" name="subdistengtemp" id="subdistengtemp" value=""/>
				<input type="hidden" name="subdistloctemp" id="subdistloctemp"/>
				<input type="hidden" name="alengtemp" id="alengtemp"/>
				<input type="hidden" name="alloctemp" id="alloctemp" value=""/>
				<input type="hidden" name="cencd2011temp" id="cencd2011temp" value=""/>	
				<input type="hidden" name="sscdtemp" id="sscdtemp" value=""/>	
				<input type="hidden" name="villageNumber" id="villageNumber" value=""/>	
				
				
				<input type="hidden" name="buttonClicked" id="buttonClicked" value=""/>
				<input type="hidden" name="reorganized" id="reorganized"/>
				<input type="hidden" name="modifyVillage" id="modifyVillage"/>
				<input type="hidden" name="addAnotherSD" id="addAnotherSD" value=""/>
				<input type="hidden" name="villageNameList" id="villageNameList" value=""/>		
				
				<input type="hidden" name="subDistrictListForSession" id="subDistrictListForSession" value=""/>	
				<input type="hidden" name="contsubDistrictListForSession" id="contsubDistrictListForSession" value=""/>	
				<input type="hidden" name="villagesListForSession" id="villagesListForSession" value=""/>	
				<input type="hidden" name="contvillagesListForSession" id="contvillagesListForSession"  />
				<input type="hidden" name="previousAddedVillageCodes" id="previousAddedVillageCodes" value="${previousAddedVillageCodes}" />
				<input type="hidden" name="partAddedSubdistrict" id="partAddedSubdistrict" value="${partAddedSubdistrict}" />					
				<input type="hidden" name="hdn" id="hdn" />
				
				<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
                <input type="hidden" name="flag2" id="flag2" value="${flag2}" />							
				
				<div class="btnpnl">
		           <div id="dialog-confirm" title="Add another sub district?" style="display: none">
						<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Are you confirm to add another sub district?</p>
					</div>
					<div id="dialog" title="Alert Message" style="display: none">
						<p>Sub district name can not be same as previously created Sub districts.</p>
					</div>
					<div id="dialogcscode" title="Alert Message" style="display: none">
						<p>Census2011 Code must be numeric value.</p>
					</div>
					<div id="dialogsscode" title="Alert Message" style="display: none">
						<p>State Specific Code must be numeric value.</p>
					</div>
					<label> <input type="button" id="Submit" style="${visibility}"
					value="<spring:message code="Button.ADDANOTHER" htmlEscape="true"></spring:message>"  
					onclick="addanother(document.getElementById('OfficialAddress').value,this.value);"/>
				<!-- 	validateForm(document.getElementById('OfficialAddress').value,this.value) -->
					</label>
					<label> <input type="button" class="btn" id="Submit2" style="${visibility}"
					value="<spring:message code="Button.NEXT" htmlEscape="true"></spring:message>"  
					onclick="nextButton(this.value);"/>
					</label>
					<%-- <label> <input type="button" class="btn" id="Submit" style="${visibilityNext}"
					value="<spring:message code="Button.NEXT" htmlEscape="true"></spring:message>"  
					onclick="nextButton('NextNoValidation');"/>
					</label>
					 --%>
					<label> <input
						type="button" name="Submit2" class="btn"
						value=<spring:message htmlEscape="true"  code="Button.CLEAR">
			         </spring:message>
						onclick="javascript:location.href='new_subdistrict.htm?<csrf:token uri="new_subdistrict.htm"/>'" />
					</label>
					<label>
					<input type="button" class="button" name="Submit6"
					value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
					onclick="javascript:go('cancelSD.htm');" /> 
					</label>
					<label>
					<input type="button" class="button" id="Reorganize" style="visibility: hidden" 
					value="<spring:message code="Button.REORGANIZE" htmlEscape="true"></spring:message>"
					onclick="formSubmit();"/> 
					</label> 
					<label>
					<input type="button" class="button" id="Proceed" style="visibility: hidden"
					value="<spring:message code="Button.PROCEED" htmlEscape="true"></spring:message>"
					onclick="formSubmit();"/> 
					</label>
				</div>
		<div class="frmpnlbg" style = "visibility : hidden; display: block;">
			<div class="frmtxt">
				<label><spring:message code="Label.REORGANIZESUBDISTRICT" htmlEscape="true"></spring:message></label><br />
					<div  >	
						<ul class="listing">
							<li>
								<label> <input name="radiobutton" type="radio" id="Yes" onchange="reorganizeYes()" onclick="reorganizeYes()"/> </label>
								<spring:message code="App.YES" htmlEscape="true"></spring:message>
							</li>
							<li>
								<label> <input name="radiobutton" type="radio" id="No" value="true" onchange="reorganizeNo()" /> </label>
								<spring:message code="App.NO" htmlEscape="true"></spring:message>
							</li>
						</ul>
					</div>
					<div id='reorgOption'style="visibility: hidden; " >	
						<ul class="listing">
							<li>
								<label> <input name="newVillage" id="newVillage" value="true" type="radio" onchange="reorgOption()" /> </label>
								<spring:message code="App.CREATENEWVILLAGE" htmlEscape="true"></spring:message>
							</li>
							<li>
								<label> <input type="radio" name="newVillage" value="false" id="isModifyVillage" onchange="reorgOption()"/> </label>
								<spring:message code="App.MODIFYVILLAGE" htmlEscape="true"></spring:message>
							</li>
						</ul>
					</div>
					<div  class="errormsg"></div>
				</div>
			</div>				
		</div>						
	</div>
	 <div id="dialog-form" title="Update Sub district" style="display: none;">
		<p class="validateTips">All form fields are required.</p>

	
		<fieldset>
			<label for="name"><spring:message code="Label.NAMEOFNEWSUBDISTRICTENGLISH" htmlEscape="true"></spring:message></label>
			<input type="text" name="subdisteng" id="subdisteng"  class="text ui-widget-content ui-corner-all" />
			
			<label for="email"><spring:message code="Label.NAMEOFNEWSUBDISTRICTLOCAL" htmlEscape="true"></spring:message></label>
			<input type="text" name="subdistloc" id="subdistloc" class="text ui-widget-content ui-corner-all" />
			
			<label for="name"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label>
			<input type="text" name="aleng" id="aleng"  class="text ui-widget-content ui-corner-all" />
			
			<label for="name"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
			<input type="text" name="alloc" id="alloc"  class="text ui-widget-content ui-corner-all" />
			
			<label for="name"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label>
			<input type="text" name="cencd2011" id="cencd2011"  class="text ui-widget-content ui-corner-all" />
			
			<label for="name"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label>
			<input type="text" name="sscd" id="sscd"  class="text ui-widget-content ui-corner-all" />		
			
		</fieldset>
		
	   </div>	
		</form:form>
	<script src="/LGD/JavaScriptServlet"></script>	
	</div>
	
	</td>
	</tr>
	</table>

</body>
</html>