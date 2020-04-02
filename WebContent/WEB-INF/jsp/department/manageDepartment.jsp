<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>		
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>	
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
	<!-- jquery pagination  -->
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<!-- jquery pagination  -->
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>

<script type="text/javascript">



//jquery pagination
$(document).ready(function() {
	$('#modifystateDepartment').dataTable({
		"sScrollY": "300px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%","sHeight":"20%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
	});
} );


$(document).ready(function() {
	$('#modifyDeparmentLowLevel').dataTable({
		"sScrollY": "300px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%","sHeight":"20%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
	});
} );
// jquery pagination  

function manageDepartmentState(url,id)
{
	//alert(id);
	dwr.util.setValue('departmentId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}


dwr.engine.setActiveReverseAjax(true);



function loadPage()
{	
	hideAll();
	document.getElementById('td_adminunit').style.display='none';
	document.getElementById('td_district').style.display='none';
	document.getElementById('td_subdistrict').style.display='none';
	document.getElementById('td_block').style.display='none';
	document.getElementById('td_village').style.display='none';
	document.getElementById('lbSpecific').style.display='none';
	document.getElementById('trAdminUnitLevel').style.display='none';
	/* document.getElementById('localBody').style.display='none'; */
document.getElementById('chkcentre').checked=false;
document.getElementById('chkstate').checked=false;
document.getElementById('stateLine').checked=false;
if(document.getElementById('ddMinistry').value!='')
	{
	document.getElementById('chkcentre').checked=true;
	document.getElementById('chkstate').checked=false;
	}
else if(document.getElementById('stateLine').value!='')
	{
	document.getElementById('chkcentre').checked=false;
	document.getElementById('chkstate').checked=false;
	}
}

function hideAll()
{
	
	$("#error_category").hide();
	$("#error_td_district").hide();
 	$("#error_td_subdistrict").hide();
	$("#error_td_village").hide();
	$("#error_td_block").hide();
	$("#error_td_adminunit").hide();
	
	
	
	
}


 if ( window.addEventListener ) { 
     window.addEventListener( "load",loadPage, false );
  }
  else 
     if ( window.attachEvent ) { 
        window.attachEvent( "onload", loadPage );
  } else 
        if ( window.onLoad ) {
           window.onload = loadPage;
  }



</script>
</head>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
                <tr>
                   <td><spring:message htmlEscape="true"  code="Label.VIEWDEPT"></spring:message></td>
                   <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
                 <%--//this link is not working   <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
                </tr>
             </table>
		    </div>
		<div class="clear"></div>
	<div class="frmpnlbrdr">
	
	<form:form action="viewdepartmentbyministrycodeforstate.htm" id="form1" method="POST" commandName="viewDeptforstate">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdepartmentbyministrycodeforstate.htm"/>"/>
	    <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
	 	<input type="hidden" id="specificLevel" name=SpecificLevel"  value="<c:out value='${category}' escapeXml='true'></c:out>" />
	  
	     
	     	<div id="cat">	
				    	
					
		<div class="frmpnlbg" id='showbystatelinelevel' >
				<div class="frmtxt" align="center">
						<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
							<table width="400" class="tbl_no_brdr">
								<tr>	
										<td align="left" class="lblBold"><label><spring:message htmlEscape="true"  code="Label.SELSEARCHENTITY"></spring:message></label></br>
											<form:select path="category" style="width: 150px" class="combofield" id="category" onchange="hideAll();getandSetDistrictListDPforAdminUnitLevel(this.value);" htmlEscape="true">
											      	<form:option value="" label="-----------Select----------" htmlEscape="true" />
												  	<form:option value="S" label="State Line Department" htmlEscape="true"/>
												  	<form:option value="D" label="District Line Department"  htmlEscape="true" />
												  	<form:option value="T" label="SubDistrict Line Department" htmlEscape="true"/>
													<form:option value="B" label="Block Line Department" htmlEscape="true" />
													<form:option value="V" label="Village Line Department" htmlEscape="true" />
													<form:option value="A" label="Administrative Unit Department" htmlEscape="true" />
											</form:select>
											<div id="error_category" class="errormsg">	<spring:message htmlEscape="true" 
															code="errorMessage.notselectedoption"></spring:message> </div>
										</td>
								  </tr>
								<tr>
										<td align="left" class="lblBold" id="td_district"><label><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></label><br />
													<form:select path="districtCode" class="combofield" style="width:150px" htmlEscape="true"
														id="ddSourceDistrict" onchange="hideAll();getandSetSubDistrictList(this.value);getandSetBlockList(this.value);">
														<form:options itemValue="districtCode" itemLabel="districtNameEnglish" htmlEscape="true"></form:options>
													</form:select>
													<div id="error_td_district" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTDISTRICTNAME"></spring:message> </div>
													<%-- <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
															code="Error.SOURCEDISTRICT"></spring:message> </span> --%>
 										</td>
								 </tr>
								<tr>
								        <td class="lblBold" align="left" id="td_subdistrict"><label><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message></label><br />
												<form:select path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" onchange="hideAll();getandSetVillageListDP(this.value);" htmlEscape="true">
												<form:options itemValue="subdistrictCode" itemLabel="subdistrictNameEnglish" htmlEscape="true"></form:options>
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												<div id="error_td_subdistrict" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTSUBDISTRICTNAME"></spring:message> </div>
										</td>
											
							      </tr>
							    <tr>
								              <td class="lblBold" align="left" id="td_village"><label><spring:message htmlEscape="true"  code="Label.SELECTVILLAGE"></spring:message></label><br />
												<form:select path="villageNameEnglish" class="combofield" style="width: 150px" onchange="hideAll();" id="ddvillage" htmlEscape="true">
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												   <div id="error_td_village" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTVILLAGENAME" text="please select one of village name"></spring:message> </div> 
											 </td>
											
							       </tr>
							     <tr>
								        <td class="lblBold" align="left" id="td_block"><label><spring:message htmlEscape="true"   code="Label.SELBLOCK"></spring:message></label><br />
												<form:select path="blockNameEnglish" class="combofield" style="width: 150px" id="ddblock" onchange="hideAll();" >
												</form:select> <!-- <span class="error" id="ddSourceSubDistrict_error"></span> -->
												 <div id="error_td_block" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTBLOCKNAME" text=" please select one of block name"></spring:message> </div>
										</td>
							      </tr>
							      <tr id="trAdminUnitLevel" style="display: none">
							     			<td>
							     					<label><spring:message htmlEscape="true"  code="" text="Select Admin Unit Level"></spring:message></label><span class="errormsg">*</span><br />
							     					<form:select path="" id="adminUnitLevel" class="combofield" style="width:150px" htmlEscape="true" onchange="getAdminUnitListByLevelCode(this.value);">
							     					
							     					
							     					</form:select>
							     				
							     			
							     			</td> 
							     </tr>
							      
							      
							        <tr>
										<td align="left" class="lblBold" id="td_adminunit"><label><spring:message htmlEscape="true"  code="Label.SELECTADMINUNITDEPT" text="Select Admin Unit"></spring:message></label><span class="errormsg">*</span><br />
													<form:select path="adminUnitCode" class="combofield" style="width:150px" htmlEscape="true"
														id="ddSourceAdminUnit" onchange="hideAll();">
														<form:options itemValue="adminUnitEntityCode" itemLabel="adminEntityNameEnglish" htmlEscape="true"></form:options>
													</form:select>
													<div id="error_td_adminunit" class="errormsg">	<spring:message htmlEscape="true" 
															code="error.select.SELECTADMINUNITDEPTNAME" text="please select one of admin unit name"></spring:message> </div>
													<%-- <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
															code="Error.SOURCEDISTRICT"></spring:message> </span> --%>
 										</td>
								 </tr>
								 <tr>
									<td>&nbsp;</td>
								 </tr>
								 <tr>
									<td>&nbsp;</td>
								 </tr>
								 <tr>
									<td>&nbsp;</td>
								 </tr>
								 <tr>
									<td height="50" colspan="4" align="center"><label>
											<input type="submit" name="Submit" class="btn" onclick="return validate_modifyDepartmnet();"  value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" /> </label><label>
											<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='viewdepartmentforstate.htm?<csrf:token uri='viewdepartmentforstate.htm'/>';" /> </label><label>
											<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('home.htm');" /> </label>
									</td>
								</tr>
							</table>
					</div>
		 </div>
		
<!---------------	 State level show details       ---------------------------------------------------------------------------------------------------------------------------> 			   	
 				 <c:if test="${! empty listDept}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" align="center">
											<table width="100%" height="5%" cellpadding="0" cellspacing="0" border="0" class="display" id="modifystateDepartment">
													<thead>
															<tr class="tblRowTitle tblclear">
																<th width="5%" rowspan="2" align="center"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																<th width="16%" rowspan="2" align="center"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																<th width="21%" rowspan="2" align="left"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																<th width="18%" rowspan="2" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
															</tr>
											 				<%-- <tr class="tblRowTitle tblclear">
																
																<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
																 <th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th> 
															
															</tr>	 --%>	
													</thead>
													<tbody>
															<c:forEach var="listDept" varStatus="listDepartmentRow" items="${listDept}">
																	<tr class="tblRowB">
																		<td width="6%" align="center"><c:out value="${listDepartmentRow.index+1}"  escapeXml="true"></c:out></td>
																		<td align="center"><c:out value="${listDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDept.orgName}" escapeXml="true"></c:out></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<%-- <td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartmentState.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td>
																	    <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDept.orgCode});" width="18" height="19" border="0" /></a></td> 	 --%>
																	</tr>
															</c:forEach>
													</tbody>
												</table>
											</td>
									</tr>
									<tr>
										<td><form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true" />
										<form:input path="entityCode" type="hidden" htmlEscape="true" />
										
										</td>
									</tr>
								
							</table>
						 </div>
					   </div>
				   	</c:if>
				   	
<!---------------	End of  State level show details       ---------------------------------------------------------------------------------------------------------------------------> 						
<!---------------	 District, Sub-district,Block and village level show details       ---------------------------------------------------------------------------------------------------------------------------> 		    
			     <c:if test="${! empty listDistDept}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
											<table width="100%" height="10%" cellpadding="0" cellspacing="0" border="0" class="display" id="modifyDeparmentLowLevel">
												<thead>
														<tr class="tblRowTitle tblclear">
																		<th width="5%" rowspan="2" align="center"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																		<th width="16%" rowspan="2" align="center"><spring:message htmlEscape="true" 	code="Label.DEPTCODE"></spring:message></th>
																		<th width="21%" rowspan="2" align="left"><spring:message htmlEscape="true"  code="Label.DEPTNAMEINENG"></spring:message></th>
																		<%-- <th colspan="1" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th> --%>
																		<th width="12%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
														</tr>
														<%-- <tr class="tblRowTitle tblclear">
							
																		<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																		<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
																		<th width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></th>
														</tr>	 --%>
												</thead>
												<tbody>
														<c:forEach var="listDistDept" varStatus="listDepartmentRow" items="${listDistDept}">
																<tr class="tblRowB">
																		<td width="6%" align="center"><c:out value="${listDepartmentRow.index+1}"  escapeXml="true"></c:out></td>
																		<td align="center"><c:out value="${listDistDept.orgCode}" escapeXml="true"></c:out></td>
																		<td align="left"><c:out value="${listDistDept.orgName}" escapeXml="true"></c:out></td>
																		<td  width="8%" align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDepartmentState('viewDepartment.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>
																		<%-- <td  width="8%" align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDepartmentState('modifyDepartmentState.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td> --%>
																		<%-- <td  width="8%" align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageDepartmentState('Deletedepartmentforstate.htm',${listDistDept.orgCode});" width="18" height="19" border="0" /></a></td>	 --%>
															
																</tr>
														</c:forEach>
												</tbody>
										</table>
										
									</td>
								</tr>
								<tr>
									<td><form:input path="departmentId" type="hidden" name="departmentId" id="departmentId" htmlEscape="true"/>
									<form:input path="entityCode" type="hidden" htmlEscape="true" />
										
									 </td>
								</tr>
								
							</table>
					 	</div>
	  			 	</div>
   				</c:if>
<!---------------End of	 District, Sub-district,Block and village level show details       ---------------------------------------------------------------------------------------------------------------------------> 
				
				
			    
				</form:form>
				<script src="/LGD/JavaScriptServlet"></script>		
			</div>
		</div>
		
</body>
</html>
