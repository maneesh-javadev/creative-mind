<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<script src="js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css"></link>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);	
</script>
<script type="text/javascript">
$(document).ready(function(){
	<c:choose>
	    <c:when test="${statecode eq 19}">
	    	$('#gtaTypes').show();
	    	$('#dPForVP').hide();
	    	$('#iPForDP').hide();
	    	$('#gtachildtr').hide();
	    	showgtaandlb("${statecode}");
	    </c:when>
	    <c:when test="${statecode eq 14}">
	    	$('#gtaTypes').hide();
	    	$('#iPForDP').hide();
	    	$('#gtachildtr').hide();
    	</c:when>
	    <c:otherwise>
	    	$('#gtaTypes').hide();
	    	$('#gtachildtr').hide();
	    </c:otherwise>
	</c:choose>
	<c:if test="${Tiertype eq 3}">
		$('#iPForDP').show();
	</c:if>
	$("#example").dataTable({
	});
	$('.dataTables_wrapper').css('min-height','210px');
	
	<c:if test='${ not empty getRLBs}'>
	/**
	 * The below code used to display dialog box 
	 * to RLB lists.
	 */ 
		$( "#divExistingRLB" ).dialog({
	    	title : "RLB(s) in active state",
	    	resizable: true,
	      	width:500,
	      	height:300,
	      	modal: true

	 });
	</c:if>
	<c:if test="${tableflag == 0 and empty deNotifiedVPList}">
		customAlert("Presently There is no Village Panchayat to de-notify !");
	</c:if>
	<c:if test="${tableflag == 0}">
		getIntermediatePanchayatbyDcode($("#dispanchayatforvp").val());
	</c:if>
});

function getIntermediatePanchayatbyDcode(id) {
	if($("#dispanchayatforvp").val()=="select") {
		$("#errordistrict2").html("Please Select a District Level Panchyat !");
		$("#errordistrict2").show();
		$("select#vilpanchayat").prop('selectedIndex', 0);
		$("#vilpanchayat").prop('disabled', 'disabled');
		return true;
	} else
		{
			$("#errordistrict2").hide();
			$("#vilpanchayat").removeAttr("disabled");
			var tier = $('#Tiertype').val();
			if (tier == 3)
				lgdDwrStateFreezeService.getchildlocalbodiesByParentcodeold(id, {
					callback : IntermediatePanchayatSuccessforVP,
					errorHandler : handleIntermediatePanchayatError
				});
			return true;
		}
}

function IntermediatePanchayatSuccessforVP(data) {
	var fieldId = 'vilpanchayat';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('vilpanchayat');
	st.add(new Option('Please Select Intermediate Level Panchayat !', 'select'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleIntermediatePanchayatError() {
	alert("No data found !");
}

function showgtaandlb(value) {
	lgdDwrlocalBodyService.getStateTopHierarchy(value, {
		callback : getStateTopHierarchySuccess,
		errorHandler : handlegetStateTopHierarchyError
	});

}

function getStateTopHierarchySuccess(data) {
	var fieldId = 'gtadp';
	var valueText = 'localBodyTypeCode';
	var nameText = 'localBodyName';
	dwr.util.removeAllOptions(fieldId);
	var dataq = [ {
		id : "0",
		select : "Select"
	} ];
	dwr.util.addOptions(fieldId, dataq, "id", "select");
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handlegetStateTopHierarchyError() {
	// Show a popup message
	alert("No data found !");
}

function getVPDPORCB(id) {
	var selName = $("#"+id+" :selected").text();
	var selVal = $("#"+id).val();
	var statevalue = $("#stateCode").val();
	if(selName.trim() == "select") {
		$("#errParentType").html("Please Select Parent Type !");
		return true;
	}
	if(selName.trim() == "District Panchayat" && selVal=="1") {
		$('#dPForVP').show();
    	$('#iPForDP').show();
    	$('#gtachildtr').hide();
	}
	if(selName.trim() == "Constitutional Body" && selVal=="20") {
		$('#dPForVP').hide();
    	$('#iPForDP').hide();
    	
		lgdDwrlocalBodyService.getPanchayatListbylblcCode(statevalue, selVal, {
			callback : getParentLbSuccess,
			errorHandler : handledisPanchayatError
		});

	}
	
}
function getParentLbSuccess(data) {
	$('#gtachildtr').show();
	var fieldId = 'gtachild';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handledisPanchayatError() {
	// Show a popup message
	alert("No data found !");
}

function validateForm() {
	
	if($("#stateCode").val() == "19")
		{
			if($("#gtadp").val()=="select" || $('#gtadp').val()=="") {
				$("#errParentType").html("Please Select Parent Type !");
				return true;
			}
			if($.trim($("#gtadp").val())=="1") {
				if($("#dispanchayatforvp").val()=="select" || $('#dispanchayatforvp').val()=="") {
					$("#errordistrict2").html("Please Select District Level Panchayat !");
					return true;
				}
				if ($('#vilpanchayat').val()=="select" || $('#vilpanchayat').val()=="") {
					$("#errorsubdistrict").html("Please Select Intermediate Level Panchayat !");
					return true;
				}
				return true;
			}
			if($.trim($("#gtadp").val())=="20") {
				if ($('#gtachild').val()=="select") {
					$("#errSelectGta").html("Please Select GTA !");
					return true;
				}
				return true;
			}
			
		}
	if($("#stateCode").val() == "14") 
		{
			if($("#dispanchayatforvp").val()=="select" || $('#dispanchayatforvp').val()=="") {
				$("#errordistrict2").html("Please Select District Level Panchayat !");
				return true;
			}
			return true;
		} else {
			if($("#dispanchayatforvp").val()=="select" || $('#dispanchayatforvp').val()=="") {
				$("#errordistrict2").html("Please Select District Level Panchayat !");
				return true;
			}
			if ($('#vilpanchayat').val()=="select" || $('#vilpanchayat').val()=="") {
				$("#errorsubdistrict").html("Please Select Intermediate Level Panchayat !");
				return true;
			}
			return true;
		}
}
function removeErrors(id) {
	if(id == "dispanchayatforvp") {
		$("#errordistrict2").hide();
		return true;
	}
	if(id == "vilpanchayat") {
		$("#errorsubdistrict").hide();
		return true;
	}
	if(id == "gtadp") {
		$("#errParentType").hide();
		return true;
	}
	if(id == "gtachild") {
		$("#errSelectGta").hide();
		return true;
	}
	return true;
}
function getULBRLB(entityCode,tranId) {
	$("#localbodyCode").val(entityCode);
	$("#localbodyCode").html(entityCode);
	$("#tranId").val(tranId);
	$("#tranId").text(tranId);
	document.getElementById('form1').action = 'deNotifyGPVillage.htm';
	document.getElementById('form1').submit();
}
</script>
</head>	
		<body>
		<div class="form_stylings">
			<div class="header">
				<h3><spring:message code="Label.DenotifyGPsTitle"/></h3>
				<ul class="item_list">
					<%--//this link is not working <li>
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message> </a>
					</li> --%>
					<%--//this link is not working <li>
						<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return true;">Help</a>
					</li> --%>
				</ul>
			</div>
			<form:form commandName="denotifyGP" method="POST" action="deNotifyGramPanchtPost.htm"  id="form1">
			<div class="page_content">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="deNotifyGramPanchtPost.htm"/>"/>
					<input type="hidden" name="Tiertype" path="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>" htmlEscape="true"/>
					<form:input type="hidden" name="lblc" id="localbodyCode" path="lblc"/>
					<form:input type="hidden" name="transactionid" id="tranId" path="transactionid"/>
					<input type="hidden" name="stateCode" path="stateCode" id="stateCode" value="<c:out value='${statecode}' escapeXml='true'></c:out>"  htmlEscape="true"/>
					<div class="form_block">
						<div class="col_1">
							<ul class="form_body">
								 <li>
									<div id="VPAN">
										 <ul class="blocklist">
											 	<li id="gtaTypes"> <!-- gta and district panchyat list -->
													<label for="gtadp"><spring:message code="Label.SELPARENT" text="Parent Type" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
													<form:select path="parentList" id="gtadp" htmlEscape="true" onblur="removeErrors(this.id);" onchange="getVPDPORCB(this.id);"> 
														<form:option value="select" htmlEscape="true"><spring:message code="" text="Please Select Parent Type" htmlEscape="true"></spring:message></form:option>
													</form:select>
													<div id="errParentType" class="errormsg"></div>
												</li>
												<li id="dPForVP">
													<label for="dispanchayatforvp"><spring:message code="Label.DISTRICTLVLPANCHYATNME" text="District Level Panchayat" htmlEscape="true"></spring:message><font color="red">*</font></label><br>
													 <form:select path="disPanchyat" id="dispanchayatforvp" onchange="getIntermediatePanchayatbyDcode(this.value);" onblur="removeErrors(this.id);" htmlEscape="true">
													    <form:option value="select"><spring:message code="Label.SELECTDISTICTLVLPANCHAYAT" text="Please Select District Level Panchyat" htmlEscape="true"></spring:message></form:option>
														<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
													 </form:select> 
													<div id="errordistrict2" class="errormsg label"></div>
												</li>
												<li id="iPForDP">
														<label for="vilpanchayat"><spring:message code="Label.INTERPANCHYATNME"  text="Intermediate Level Panchayat" htmlEscape="true"></spring:message> <font color="red">*</font></label><br>
															<form:select path="intermediatePanchyat" id="vilpanchayat" onchange="removeErrors(this.id);" htmlEscape="true">
															</form:select> 
														<div id="errorsubdistrict" class="errormsg label"></div>
											 	</li>
											 	<li id="gtachildtr"> <!-- gta panchyat list	  -->
													<label for="gtachild"><spring:message code="Label.SELECTLOCALBODY" text="Select Gta" htmlEscape="true"></spring:message><span class="mndt">*</span></label>
													<form:select path="gtaList" id="gtachild" htmlEscape="true" onblur="removeErrors(this.id);">
														<form:option value="select" htmlEscape="true"><spring:message code="" text="Please Select GTA" htmlEscape="true"></spring:message></form:option>
													</form:select>
													<div id="errSelectGta" class="errormsg"></div>	
												</li>
										 </ul>
									</div>
								</li>
							</ul>
						</div>
					</div><br></br>
							<input type="submit" id="createDenotify" name="submmit" value="Get Data" onclick="return validateForm();"></input>
							<input type="button" id="close" name="close" value="Close" onclick="javascript:go('home.htm');" ></input>
				</div>
				<c:if test="${tableflag == 0 and not empty deNotifiedVPList}">
				<div class="errormsg label"></div>
				<div class="form_container">
					<div class="form_block">
							<div class="col_1 overflow_horz">
								<ul class="form_body">
											<li>
												<h3>Invalidated Village Level Panchayat(s)</h3></br>
												<h3>Click on any of the following Village Panchayat(s) to de-notify :</h3></br>
												<table id="example" class="display" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><spring:message code="Lable.Serialno" text="Serial No." htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.VILLAGEPCODE" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.VILLAGEPANCHAYATNAME" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.VILLAGEPNAMELOCAL" htmlEscape="true"></spring:message></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="deNotifiedVPList" varStatus="listDepartmentRow" items="${deNotifiedVPList}">
															<tr>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out> 
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.localBodyCode}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<a href="#" onclick="javascript:getULBRLB(${deNotifiedVPList.localBodyCode},${deNotifiedVPList.transactionid});"><c:out value="${deNotifiedVPList.lBNameEnglish}" escapeXml="true"></c:out></a>
																</td>
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:out value="${deNotifiedVPList.lBNameLocal}" escapeXml="true"></c:out>
																 </td> 																				
															</tr>										
														</c:forEach>																				
												</tbody>
												
											</table>									
										</li>
									</ul>
							</div>
						</div>
				</div>
				</c:if>
			</div>
			</form:form>
		</div>
		<!-- RLBs which are in active state -->
				<c:if test="${ not empty getRLBs}">
				<div id="divExistingRLB" class="form_stylings" style="display: none;">
					<div class="form_block">
							<div class="col_1">
								<h4>${message}</h4>
								<ul class="form_body">
									<li>
										<table id="tblGornmentOrderDetails" class="data_grid" width="100%">
											<thead>
												<tr>
													<th>S.No.</th>
													<th><spring:message code="" text="Rural Local Body"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="varRLBList" items="${getRLBs}" varStatus="counter">
													<tr>
														<td><c:out value="${counter.count}" escapeXml="true"></c:out></td>
														<td><c:out value="${varRLBList}" escapeXml="true"></c:out></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</li>
								</ul>	
							</div>
						</div>
						<br/>
					</div>
				</c:if>
	</body>
</html>