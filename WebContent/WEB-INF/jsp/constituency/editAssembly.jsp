<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>


<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 --><!-- <script type="text/javascript" src="js/createDistrict.js"></script> -->
  <script src="js/Parliament.js"></script>
 
<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>

<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);


function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = 'viewAssemblyCocnstituencyPagination.htm';
	document.forms['form1'].submit();
}
function validate()
{
			document.forms['form1'].submit();
		
}
/* function validateform()

{
		var districtName=document.getElementById('districtNameInEn').value;
	
	if(districtName=="")
	{
	alert("Please Enter Name Of the Districit");
	document.getElementById('districtNameInEn').focus();
	return false;
	}
	return true;
} */


function selectDistrict(id,name)
{     
	
		if (id.match('PART')=='PART'){
		
	var selObj=document.getElementById('ddDestDistrict');	
	
	//var selObj2=document.getElementById('surveyListNew');
	//var selObj=document.getElementById('subDistrictListNew');	
	/*var subDistrict="";
		for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrict +=selObj.options[i].value+",";
	 }*/
		lgdDwrStateService.getDistricts(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
		
	}else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part State From the List");
	}
	}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	
	var fieldId = 'ddDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	

	dwr.util.addOptions(fieldId, data,valueText,nameText);
	
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}
	//DWR Dropdownlist Population


	//DWR Dropdownlist Population

	

function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
	

	
</script>
</head>

<body onload="">
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.MODIFYASSEMBLY"></spring:message></h3>
			<ul class="listing">
				<li><a href="#" class="frmhelp">Help</a></li>
			</ul>
		</div>

		<form:form action="" method="POST" commandName="AssemblyForm"  id="form1" >
	    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewAssemblyCocnstituencyPagination.htm"/>"/>
		<table width="100%" class="tbl_no_brdr"></table>
			<div class="frmpnlbg">
		    	<div class="frmtxt" align="center">
			    	 <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.MODIFYASSEMBLY"></spring:message></div>
		        		  <table width="800" class="tbl_no_brdr"></table>
								 <c:if test="${! empty SEARCH_Assembly_RESULTS_KEY}">
									<div class="frmpnlbg" id="divData">
										<div class="frmtxt">
											<table width="100%" class="tbl_no_brdr">
												<tr>
												<td width="14%" align="center">
													<table class="tbl_with_brdr" width="98%" align="center">
														<tr class="tblRowTitle tblclear">
															<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
			<%-- 												<td width="5%" rowspan="2"><spring:message htmlEscape="true"  code="Label.PCCODE"></spring:message></td>
			 --%>												<td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.ASSEMBLYCONSTITUENCYNAMEINENG"></spring:message></td>
			                                                    <td width="16%" rowspan="2"><spring:message htmlEscape="true" 	code="Label.ASSEMBLYCONSTITUENCYNAMEINLOCAL"></spring:message></td>
															<td width="16%" rowspan="2"><spring:message htmlEscape="true"  code="Label.ECICODE"></spring:message></td>
			<%-- 												<td width="21%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
			 --%>											
														</tr>
											<tr class="tblRowTitle tblclear">
															<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
															<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></td>
															<%-- <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td> --%>
															<%-- <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.GOVTORDER"></spring:message></td>
															
															<td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MAP"></spring:message></td> --%>
											</tr>
											
											      
											     <c:forEach var="Parliamentconstituencymodify" varStatus="listDistrictRow" items="${SEARCH_Assembly_RESULTS_KEY}">
											     
															  <tr class="tblRowB">
																		<td width="6%"><c:out value="${offsets*limits+(listDistrictRow.index+1)}" escapeXml="true"></c:out></td>
																	
			<%-- 														<td align="left">  <c:out value="${Parliamentconstituencymodify.constituency_code}"></c:out></td>
			 --%>														<td align="left">  <c:out value="${Parliamentconstituencymodify.constituency_name_english}" escapeXml="true"></c:out></td>
			                                                            <td align="left">  <c:out value="${Parliamentconstituencymodify.constituency_name_local}" escapeXml="true"></c:out></td>
																	<td align="left"><c:out value="${Parliamentconstituencymodify.eci_code}" escapeXml="true"></c:out></td>		
																	
																<td align="center"><a href="viewAssemblyConstituency.htm?id=${Parliamentconstituencymodify.constituency_code}&<csrf:token uri="viewAssemblyConstituency.htm"/>"><img src="images/view.png" width="18" height="19" border="0" /></a></td>
																	<td align="center"> <a href="modifyAssemblyConstituency.htm?id=${Parliamentconstituencymodify.constituency_code}&<csrf:token uri="modifyAssemblyConstituency.htm"/>"><img src="images/edit.png" width="18" height="19" border="0" /></a></td> 
																<!-- <td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td>
																	<td align="center"><a href="#"><img src="images/map.png" width="18" height="19" /></a></td>	 -->
															 </tr>
											  </c:forEach>											
											</table>
										  </td>
									   </tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
				
										<tr>
											<td height="30" align="right">
													     <table width="301">
															<tr>
																<td width="150" align="right" class="pageno"><c:out value="${AssemblyCount}" escapeXml="true"></c:out></td>
																<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
																<td width="24" align="center" class="pageno">|</td>
																<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
																<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										  </div>
									    </div>
									 </c:if>
							
									<c:if test="${fn:length(viewPage) > 0}"> 
										<c:if test="${empty SEARCH_Assembly_RESULTS_KEY}">
											<div class="frmpnlbg" id="divData">
												<div class="frmtxt">
													<ul class="listing">
														<li>
															<spring:message htmlEscape="true"  code="error.NOASSEMBLYFOUND"></spring:message>
														</li>
													</ul>
												</div>
											</div>
										</c:if>
									</c:if>
								</div>
							</div>
						</form:form>
					</div>
				 <input type="hidden" name="direction" id="direction" value="0" />
			</body>
		</html>

