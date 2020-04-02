<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>


<script src="js/Parliament.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>


<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);


function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "standardCodesPagination.htm?<csrf:token uri='standardCodesPagination.htm'/>";
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
	return true;
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

	function formsubmit()
	{
		//alert("save data");
		
		/* document.forms['form1'].action = "UpdateVillageDetails.htm?<csrf:token uri='UpdateVillageDetails.htm'/>";
		document.forms['form1'].submit(); */
		document.getElementById("Submit").disabled=true;
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "UpdateVillageDetails.do?<csrf:token uri='UpdateVillageDetails.do'/>";
		document.getElementById('form1').action = "UpdateSubdistrictDetails.do?<csrf:token uri='UpdateSubdistrictDetails.do'/>";
		document.getElementById('form1').submit();
		/* document.form1.action="/UpdateVillageDetails.do";
		document.form1.submit(); */
	}

function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
	

	
</script>
</head>

<body onload="">
	 <section class="content">
	   <div class="row">
	   <section class="col-lg-12">
	 
	 <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><c:out value="Update Other Standard Codes/Local Names(${entityName })" /></h3>
                </div>
                
           <form:form action="" method="POST" commandName="standardCodeForm" id="form1" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="standardCodesPagination.htm"/>" />
			<form:hidden path="category" />
			<input type="hidden" name="entityName" value="<c:out value='${entityName}' escapeXml='true'></c:out>"/>
			<input type="hidden" name="entityTypeFlag" value="<c:out value='${censuseCondition}' escapeXml='true'></c:out>"/>
			
			<c:if test="${! empty SEARCH_StandardCodes_RESULTS_KEY}">
			<div class="box-body">
			<div class="errormsg update_standard_code_error"><form:errors path="*" cssClass="error" /></div>
			
			
              <table class="table table-bordered table-hover">
    
             <tbody>
                 	<c:if test="${entityName=='state'}">
													<tr>

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message></td>
														 <%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>


													</tr>
												</c:if>
												<c:if test="${entityName=='district' || entityName=='districtDistUser'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.DISTRICTNAMEENG" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.DISTRICTNAMELOCAL" htmlEscape="true"></spring:message></td>
														<%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>


													</tr>
												</c:if>

												<c:if test="${entityName eq 'block' || entityName eq 'blockDistUser'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.BLOCKCODE" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.BLOCKNAMEINENGLISH" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.BLOCKNAMEINLOCAL" htmlEscape="true"></spring:message></td>



													</tr>
												</c:if>
												<c:if test="${entityName=='subdistrict' || entityName=='subdistrictDistUser'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></td>
														 <%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>


													</tr>
												</c:if>
												<c:if test="${entityName=='village' || entityName=='villageDistUser'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></td>
														 <%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%> 


													</tr>
												</c:if>

												<c:if test="${entityName=='locabody' }">
													<tr >
														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></td>
														<td width="16%" rowspan="2"><spring:message code="Label.LOCALBODYNAMEINLOCAL" htmlEscape="true"></spring:message></td>
													    <c:if test="${censuseCondition eq true}">
													    <td width="8%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> 
															</c:if>

													</tr>
												</c:if>
												
												<c:if test="${entityName eq 'adminLevel'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.AdminUnitCode" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Administrative Unit Level (In English)" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Administrative Unit Level (In Local)" htmlEscape="true"></spring:message></td>


													</tr>
													<tr></tr>
												</c:if>
												<c:if test="${entityName eq 'adminEntity'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.AdminEntityCode" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.AdminUnitEntityEng" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.AdminUnitEntityLoc" htmlEscape="true"></spring:message></td>


													</tr>
													<tr></tr>
												</c:if>
												<c:if test="${entityName eq 'dept'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.DEPTCODE" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.DEPTNAMEINENG" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.DEPTNAMEINLOCAL" htmlEscape="true"></spring:message></td>


													</tr>
													<tr></tr>
												</c:if>
												<c:if test="${entityName eq 'org'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.ORGCODE" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.ORGNAMEINENG" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.ORGNAMEINLOCAL" htmlEscape="true"></spring:message></td>


													</tr>
													<tr></tr>
												</c:if>
												
												<c:if test="${entityName eq 'orgunit'}">
													<tr >

														<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
														<td width="5%" rowspan="2"><spring:message code="Label.ORGUNITCODE" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.ORGUNITNAMEINENG" htmlEscape="true"></spring:message></td>
														<td width="30%" rowspan="2"><spring:message code="Label.ORGUNITNAMEINLOCAL" htmlEscape="true"></spring:message></td>


													</tr>
													<tr></tr>
												</c:if>
												<c:if test="${entityName ne 'adminLevel' and entityName ne 'adminEntity' and entityName ne 'org' and entityName ne 'dept' and entityName ne 'orgunit'}">
												<tr >
													<c:if test="${entityName!='state'}">
														<td width="6%" align="center"><spring:message code="Label.STATESPECIFICCODES" htmlEscape="true"></spring:message></td>
													</c:if>
												</tr>
												</c:if>
												<c:forEach var="standardCodes" varStatus="listStandardRow" items="${standardCodeForm.standardCodeDataDetails}">

													<tr >
														<td width="6%">${offsets*limits+(listStandardRow.index+1)}</td>
														
														<td align="left" >
														<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].entityCode">
														<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" readonly="readonly" id="<c:out value="${status.expression}"/>" />
														</spring:bind> <%--  <c:out value="${standardCodes.village_code}"></c:out> --%>
														</td>
														
														<td align="left">
														<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].entityNameEnglish">
														<input class="form-control"  name="<c:out value="${status.expression}"/>"  <c:if test="${entityName eq 'adminLevel' or entityName eq 'adminEntity' or entityName eq 'org'   or entityName eq 'dept' or entityName eq 'orgunit'}"> size="60%"</c:if>
														value="<c:out value="${status.value.trim()}" escapeXml="true"/>" readonly="readonly" id="<c:out value="${status.expression}"/>" />
														</spring:bind>
														</td>
														
														<td align="left" >
														<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].entityNameLocalch">
														<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" 
														<c:choose>
														<c:when test="${entityName eq 'adminLevel' or entityName eq 'adminEntity' or entityName eq 'org'  or entityName eq 'dept' or entityName eq 'orgunit'}"> size="60%" maxlength="100"
														</c:when>
														<c:otherwise>
														maxlength="50"
														</c:otherwise>
														</c:choose>
														onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"
														id="<c:out value="${status.expression}"/>" />

														</spring:bind> <spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].entityNameLocal">
														<input name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" type="hidden" id="<c:out value="${status.expression}"/>" />
														</spring:bind>
														<c:choose>
														<c:when test="${entityName eq 'adminLevel'}">
														</c:when>
														<c:otherwise>
														</c:otherwise>
														</c:choose>
														</td>



													<%-- <c:if test="${entityName ne 'adminLevel' and entityName ne 'adminEntity' and entityName ne 'org'  and entityName ne 'dept' and entityName ne 'orgunit'}">
														<c:if test="${!(entityName eq 'adminLevel' or entityName eq 'adminEntity' or entityName eq 'org' )}">
															 <td align="left"><spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].census2011Codech">
																	<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" onkeypress="return validateNumericKeys(event);" maxlength="6" id="<c:out value="${status.expression}"/>" />
																</spring:bind> <spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].census2011Code">
																	<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" type="hidden" id="<c:out value="${status.expression}"/>" />

																</spring:bind></td>
														</c:if>
														<!-- added by pooja on 10-06-2015 -->
														<c:if test="${entityName eq 'block' || entityName eq 'blockDistUser'}">
															<<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].census2011Codech">
																<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" type="hidden" id="<c:out value="${status.expression}"/>" />
															</spring:bind>

															<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].census2011Code">
																<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" type="hidden" id="<c:out value="${status.expression}"/>" />
															</spring:bind>
														</c:if>
													</c:if> --%>
													
													<c:if test="${censuseCondition}">
													<td align="left"><spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].census2011Codech">
																	<input   class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" onkeypress="return validateNumericKeys(event);" maxlength="6" placeholder="N.A." id="<c:out value="${status.expression}"/>" />
																</spring:bind> <spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].census2011Code">
																	<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" type="hidden" id="<c:out value="${status.expression}"/>" />
																</spring:bind></td>
													
													</c:if>
													
													
														<c:if test="${entityName ne'state' and entityName ne 'adminLevel' and  entityName ne 'adminEntity' and entityName ne 'org' and entityName ne 'dept' and entityName ne 'orgunit'}">
															<td align="left">
																<%-- <c:out value="${standardCodes.sscode}"></c:out> --%> <c:choose>
																	<c:when test="${entityName=='locabody' }">
																		<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].ssCodech">
																			<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" maxlength="7" onkeypress="return validateAlphanumericKeysStateSp(event);"
																				id="<c:out value="${status.expression}"/>" />
																		</spring:bind>
																	</c:when>
																	<c:otherwise>
																		<spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].ssCodech">
																			<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" maxlength="5" onkeypress="return validateAlphanumericKeysStateSp(event);"
																				id="<c:out value="${status.expression}"/>" />
																		</spring:bind>
																	</c:otherwise>
																</c:choose> <spring:bind path="standardCodeForm.standardCodeDataDetails[${listStandardRow.index}].ssCode">
																	<input class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value.trim()}" escapeXml="true"/>" type="hidden" id="<c:out value="${status.expression}"/>" />

																</spring:bind>


															</td>
														</c:if>
													</tr>

												</c:forEach>
                        </tbody>
                  </table>
               </div>
                  
        <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">                   
                <button type="button" name="save" id="Submit" class="btn btn-success " onclick="formsubmit();" >Save&Update </button>
                <button type="button" name="Cancel" class="btn btn-danger "  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" ><spring:message code="Button.CLOSE"></spring:message></button>
            </div>
            </div>
          </div>
             </c:if>
             
             
             <c:if test="${recordlength ==0}">
             <div class="box-body">
               <center><font size="3" color="red"> <c:out value="No Record found for selected searching criteria"/>
						</font></center>
             
             </div>
						
						
						<div class="box-footer">
									 <button type="button" name="Cancel" class="btn btn-danger pull-right "  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" ><spring:message code="Button.CLOSE"></spring:message></button>

					 	</div>
					</c:if>

				




			<input type="hidden" name="direction" id="direction" value="0" />
            </form:form>      
              
              </div>
               </section>
            </div>
         </section>
</body>
</html>

