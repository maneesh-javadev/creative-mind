<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/add_designation.js"></script>
<script type="text/javascript" src="js/add_reporting.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message htmlEscape="true" code="Label.Title"></spring:message></title>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

<script language="javascript" type="text/javascript">
function open_win() {
	window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
}

function removeRows() {
	retrievetable = document.getElementById("ReportTable");
	for ( var i = retrievetable.rows.length - 1; i > 1; i--) {
		retrievetable.deleteRow(i);
	}
};

function resetDetails(isLocatedLevel){
	removeRows();
	document.getElementById('ddDesignation0').value = "";
	dwr.util.removeAllOptions('RdesignationCode0');
	document.getElementById('RdesignationCode0').disabled = true;
	
	if(!isLocatedLevel){
		dwr.util.removeAllOptions('locatedAtLevel');
		var dataq = [ {name : "Select"} ];
		dwr.util.addOptions('locatedAtLevel', dataq, "name");	
	}
}

var listbox;
var popList;
var trec;
var updateData;
var selTierSetupCode;
var parntTierSetupCode;
var parentDesCode;
var delID;
var selId;

function getReportingDetails(id) {
	var org=document.getElementById('orgCode').value;
	selId = id;
	lgdDwrOrganizationService.getDesignationReportingOrganizDetail(id,org, {
		async : true,
		callback : getDesignationReportingOrganiz,
		errorHandler : getDesignationReportingOrganizError
	}); 
}

function getDesignationReportingOrganiz(data) {
	
	updateData = data;
	var org = document.getElementById('orgCode').value;
	lgdDwrOrganizationService.getDesignationReportingOrganiz(selId,org, {
		async : true,
		callback : handleDesigReportingSuccessOrg,
		errorHandler : handleDesigReportingErrorOrg
	}); 
}

function getDesignationReportingOrganizError(){
	alert("No Data Found !");
}

function handleDesigReportingSuccessOrg(data) {
	
	if(data == '' || data == 0)	{
		document.getElementById('RdesignationCode0').disabled = false;
	} else	{
		document.getElementById('RdesignationCode0').disabled = true;
	}	
	var valueText = 'designationCode';
	var nameText = 'designationName';
	var globaldata = data;
	popList = data;
	listbox = new Array(data.length);
	for ( var m = 0; m < data.length; m++) {
		listbox[m] = new Array(2);
	}
	var listData = new Array();
	
	var y = 0;
	var table, tr, td1, td2, inp1, inp2, select, div1;
	for ( var x = 0; x < globaldata.length; x++) {

		var labelDetails = globaldata[x];
		if (labelDetails.isTopDesignation) {
			listData[y] = labelDetails;
			y++;
			document.getElementById("ddDesignation0").value = labelDetails.designationName;
			document.getElementById("designationCode0").value = labelDetails.designationCode;
		} else {

			table = document.getElementById('ReportTable');
			tr = document.createElement('TR');
			td1 = document.createElement('TD');
			td2 = document.createElement('TD');
			td4 = document.createElement('TD');

			inp1 = document.createElement('INPUT');
			inp2 = document.createElement('INPUT');
			select = document.createElement("select");
			div1 = document.createElement('DIV');
			tr.setAttribute("id", "row" + x);

			inp1.setAttribute("id", "ddDesignationName" + x);
			inp1.setAttribute("size", "30");
			inp1.setAttribute("class", "frmfield");
			inp1.setAttribute("readonly", "readonly");
			inp1.setAttribute("value", labelDetails.designationName);
			inp2.setAttribute("Type", "hidden");
			inp2.setAttribute("path", "desig");
			inp2.setAttribute("id", "designationCode" + x);
			inp2.setAttribute("Value", labelDetails.designationCode);
			inp2.setAttribute("class", "frmfield");

			select.setAttribute("path", "report");
			select.setAttribute("id", "RdesignationCode" + x);
			select.setAttribute("style", "width:150px");
			select.setAttribute("onchange", "nextList(this.value," + x + ");clearError(" + x + ");");
			div1.setAttribute("id", "designationCode" + x + "_error");

			table.appendChild(tr);
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td4);
			td1.appendChild(inp1);
			td1.appendChild(inp2);
			td2.appendChild(div1);
			td2.appendChild(select);

			if (x != 1) {
				listData[y] = labelDetails;
				y++;
			}
		}
	}
	trec = x;
	
	if (x >= 1) {
		dwr.util.removeAllOptions('RdesignationCode1');
		var dataq = [ {name : "Select"} ];
		dwr.util.addOptions('RdesignationCode1', dataq, "name");
		var x = document.getElementById("RdesignationCode1");
		var optn = document.createElement("OPTION");
		optn.text = "None";
		optn.value = 0;
		x.options.add(optn);
		dwr.util.addOptions('RdesignationCode1', listData, valueText, nameText);

	}

	if (updateData != null) {
		var temp = updateData.designationName;
		if (temp != null) {
			var tempRepotTo = temp.split("~");
			topreportTo = tempRepotTo[0];
			parentDesCode = tempRepotTo[0];
			for ( var s = 1; s < (tempRepotTo.length - 1); s++) {
				document.getElementById("RdesignationCode" + s).value = tempRepotTo[s];
				nextList(tempRepotTo[s], s);
			}
		}
	}

	var olc = document.getElementById('orgCode').value;
	var locatedLevel = document.getElementById('locatedAtLevel').value;
	 lgdDwrOrganizationService.getOrgLocatedbyOrgCodeforTopReporting(olc, locatedLevel, {
		async : true,
		callback : handleDesigReportingSuccessToTop,
		errorHandler : handleTopDesigReportingErrorToTop
	}); 
}

function handleDesigReportingErrorOrg() {
	alert("No data found!");
}

function handleDesigReportingSuccessToTop(data) {
	var org = document.getElementById('orgCode').value;
	if(data != 0) {
		lgdDwrOrganizationService.getTopDesignationReporting(data, org, {
			async:true,
			callback : handleDesigReportingSuccessToTopforTop,
			errorHandler : handleTopDesigReportingErrorToTopforTop
		});
	}else {
		removeDesReportingTop();
	}	
}

function handleTopDesigReportingErrorToTop() {
	alert("No data found!");
}

function handleDesigReportingSuccessToTopforTop(data) {
	var valueText='designationCode';
	var nameText='designationName';
	var fieldId1 = 'RdesignationCode0';
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.addOptions(fieldId1,data,valueText,nameText);
	document.getElementById('RdesignationCode0').disabled = false;
}

function handleTopDesigReportingErrorToTopforTop() {
	alert("No data found!");
}

function removeDesReportingTop() {
	var fieldId1 = 'RdesignationCode0';
	dwr.util.removeAllOptions(fieldId1);
	document.getElementById('RdesignationCode0').disabled = true;
}

function nextList(code, id) {
	var listData = new Array();
	var find = false;
	var matcharray = new Array();
	var listArray = new Array();
	var m = 0;
	var p = 0;
	if (trec > id + 1) {

		listbox[id - 1][0] = code;
		listbox[id - 1][1] = document.getElementById("designationCode" + id).value;
		var ddcode = document.getElementById("designationCode" + parseInt(id + 1)).value;
		matcharray.push(ddcode);
		for ( var d = 0; d < matcharray.length; d++) {
			for ( var z = 0; z < popList.length; z++) {
				var labelDetails = popList[z];
				for ( var k = 0; k < (id + p); k++) {
					if (listbox[k][0] == undefined) {
						p++;
					}
					if (listbox[k][0] == matcharray[d]) {
						if (labelDetails.designationCode == listbox[k][1]) {
							matcharray.push(labelDetails.designationCode);
							listArray.push(labelDetails.designationCode);
							break;
						}
					}
				}
			}
		}
		for ( var z = 0; z < popList.length; z++) {
			var labelDetails = popList[z];
			find = false;
			for ( var u = 0; u < listArray.length; u++) {
				if (labelDetails.designationCode == listArray[u]) {
					find = true;
				}
			}
			if (find == false && labelDetails.designationCode != ddcode) {
				listData[m] = labelDetails;
				m++;
			}
		}

		for ( var t = parseInt(id + 1); t < popList.length; t++) {
			dwr.util.removeAllOptions(('RdesignationCode' + t));
		}

		var dataq = [ {name : "Select"} ];
		dwr.util.addOptions(('RdesignationCode' + parseInt(id + 1)), dataq, "name");
		var x = document.getElementById(('RdesignationCode' + parseInt(id + 1)));
		var optn = document.createElement("OPTION");
		optn.text = "None";
		optn.value = 0;
		x.options.add(optn);
		dwr.util.addOptions(('RdesignationCode' + parseInt(id + 1)), listData, valueText, nameText);
	}
}





function populateOrg() {
	
	document.getElementById("designation").value = "";
	document.getElementById("reportTo").value = "";
	for ( var h = 1; h < trec; h++) {
		document.getElementById("designationCode" + h + "_error").innerHTML = "";
	}
	var error = false;
	if (document.getElementById('RdesignationCode0').disabled == false && document.getElementById('RdesignationCode0').length>0) {
		if(document.getElementById('ddDesignation0').value == "" ||  (document.getElementById('RdesignationCode0').value == "")) {
			alert("Report To cannot be blank");
			error = true;
		}
	}
	
	if (document.getElementById('orgType').selectedIndex == 0) {
		alert("Please select Organization Type");
		error=true;
	}
	if (document.getElementById('orgCode').selectedIndex == 0) {
		alert("Please select Organization");
		error=true;
	}
	if (document.getElementById('locatedAtLevel').selectedIndex == 0) {
		alert("Please select Organization Level");
		error=true;
	}
	
	for ( var h = 1; h < trec; h++) {
		if (document.getElementById("RdesignationCode" + h).value == "" || document.getElementById("RdesignationCode" + h).value == "Select") {
			document.getElementById("designationCode" + h + "_error").innerHTML = "<font color='red'><br>Please Select Designation </font>";
			error = true;
		}
	}

	if (error) {
		return false;
	} else {
		for ( var k = 0; k < trec; k++) {
			document.getElementById("designation").value += document.getElementById("designationCode" + k).value + ",";
			document.getElementById("reportTo").value += document.getElementById("RdesignationCode" + k).value + ",";
		}
		return true;
	} 
}


$( document ).ready(function() {
	var rightpanel = document.getElementById("rpnls");
	
	rightpanel.style.overflow = "hidden";
	
	
});



</script>

</head>

<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><label><spring:message code="Label.ADDREPORTINGSTRUCTURE" htmlEscape="true"></spring:message></label></h3>
			 <ul id="showhelp" class="listing">
			       <%--//these links are not working  <li>
			         <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>           
			        </li>
			        
			        <li>
			        	<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
			        </li>			 --%>		 				     
		        </ul>					
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form id="designationReportingFormOrg" name="designationReportingForm" action="add_reporting_structure.htm" method="post" commandName="designationReportingForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="add_reporting_structure.htm"/>" />
				<div class="frmpnlbg">
					<div class="frmtxt">
					<div class="frmhdtitle">Designation Reporting</div>
						<div   class="block">
							<ul class="blocklist" >
								<li>
									<input type="hidden" id="hdnStateCode" value='<%=request.getAttribute("slcCode")%>' />
									<ul>
										<li>
											<label><spring:message htmlEscape="true" code="Label.ORGTYPELIST"></spring:message>&nbsp;<span class="errormsg">*</span> </label><br /> 
												<form:select path="orgTypeCode" id="orgType" htmlEscape="true" class="combofield"
												onchange="displayLoadingImage();resetDetails(false);getOrgbyTypeAtLevel(this.value,1,'${hdnStateCode}');hideLoadingImage();">
												<form:option value="0">
													<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
												</form:option>
												<form:options items="${orgType}" itemLabel="orgType" itemValue="orgTypeCode" />
											</form:select> <br /> <br />
										</li>
									</ul>
									<ul>
									<li>
									<label><spring:message htmlEscape="true" code="Label.ORGLIST"></spring:message>&nbsp;<span class="errormsg">*</span></label><br /> 
														<form:select id="orgCode" path="orgCode" class="combofield" htmlEscape="true"
																	 onchange="displayLoadingImage(); 
																	 		   resetDetails(false); 
																	 		   getOrgLocatedbyOrgCode(this.value); 
																	 		   getOrgbyTypeForReporting(orgType.value,this.value); 
																	 		   hideLoadingImage();">
														<form:option value="0">
															<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
														</form:option>
													</form:select><br/><br/>									
									</li>
									</ul>
									<ul>
									<li>
									<label>Select Level&nbsp;<span class="errormsg">*</span></label><br /> 
														<form:select path="locatedAtLevel" id="locatedAtLevel" class="combofield" htmlEscape="true"
															onchange="displayLoadingImage(); resetDetails(true); getReportingDetails(this.value); hideLoadingImage();">
															<form:option value="0">
																<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
															</form:option>
														</form:select>
													<div class="errormsg"></div>
									</li>
									</ul>
									</li>
									</ul>
									
									
									<ul id="ReportTable">
									 	<li class="listing">
									 	  <label><spring:message htmlEscape="true" code="Label.TD"></spring:message></label><br />
									 	
										 	<input id="ddDesignation0" type="text" disabled="disabled" size="30" class="frmfield" />
						                    <form:input path="desig" id="designationCode0" type="hidden" />
				                            <div  class="errormsg"></div>
				                            </li>
				                            <li class="listing">
									 	     <label><spring:message htmlEscape="true" code="Label.REPORTTO"></spring:message><span class="errormsg">*</span></label> </br>
									 	  	<form:select path="report" class="combofield" id="RdesignationCode0" disabled="true"></form:select> 
			                                <div class="errormsg" id="designationCode0_error"></div>
									 	</li>
									 	<li>
									 		<div class="errormsg"></div>
									 	</li>
									</ul>
								   <div id="dynamicDiv" ></div>
		                        			<input type="hidden" name="reportTo" id="reportTo" value=""/>
			                              	<input type="hidden" id="txLBReporting" value="" />
			                              	<input type="hidden" name="designation" id="designation" value="" />
			                     			<input type="hidden" name="designation" id="designation" value="" />
		                         			<input type="hidden" name="reportTo" id="reportTo" value=""/>
				                     <div class="errormsg"></div>          
			              		</div>
							</div>
						</div>
					<div class="btnpnl">
					
					 		<input type="submit" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return populateOrg();"/>
           					<input type="button"  value="Clear" onclick="javascript:location.href='add_reporting_structure_state.htm?<csrf:token uri="add_reporting_structure_state.htm"/>'"/>
           					<input type="button" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
					
					</div>
					
					
					
					
					
						
			</form:form>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>