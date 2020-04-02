<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>
<%@include file="ManageWardJs.jsp"%>
<head>
<c:choose>
<c:when test="${PANCHAYAT_TYPE eq 'P'}">
<c:set var="formTitle" value="label.publish.pri.ward.changes"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'T'}">
<c:set var="formTitle" value="label.publish.tri.ward.changes"></c:set>
</c:when>
<c:when test="${PANCHAYAT_TYPE eq 'U'}">
<c:set var="formTitle" value="label.publish.urban.ward.changes"></c:set>
</c:when>
</c:choose>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/new_ward.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/wardAfterPost.js"></script>
<link href="<%=contextpthval%>/resources-localbody/css/localbody.css" rel="stylesheet" type="text/css" />

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'>
</script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'>
</script>

<script type='text/javascript'>

</script>

<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrWardService.js'></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<!-- <script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script> 
<link rel="stylesheet" href="datepicker/demos.css" />-->

<script type="text/javascript" language="javascript"
	src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
<link href="<%=contextpthval%>/datatable/demo_table_jui.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">

dwr.engine.setActiveReverseAjax(true);
</script>

<%@include file="../common/dwr.jsp"%>
<%-- <c:if test="${message ne null }">
<script>
customAlert("${message}");
</script>
</c:if> --%>

<c:if test="${showTable eq true and PANCHAYAT_TYPE ne 'U'}">
<script>
$(window).load(function () {
	$('#localBodyType option[value != ""]').remove();
	//alert('${wardForm.paramLocalBodyTypeCode}');
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
			callback : function(result) {
				populateLBType(result);
				$("#localBodyType option[value='${wardForm.paramLocalBodyTypeCode}']").attr("selected", "selected");
				registerCallDynamicHierarchy($('#localBodyType').get(0));
				setTimeout(function(){
					var localbodyLevelCodes =  '${wardForm.localBodyLevelCodes}'.split(",");
					var localBodyLevelElement = $("SELECT[name='localBodyLevelCodes']");
					var elementCount = $(localBodyLevelElement).size()-1;
					$(localBodyLevelElement).each(function(index){
					//alert("index:"+index+" elementCount:"+elementCount+"localbodyLevelCodes:"+localbodyLevelCodes);
						$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
						if(index < elementCount ){
							configureLocalBodyTypeHierarchy(this);	
						}
						if(index == elementCount ){
							var elementId = $(this).attr('id');
							setTimeout(function(){
								 $("#" + elementId +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected"); 
							},200);
						}
					});
				}, 200);
			},
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
}); 
</script>
</c:if>

</head>
<!-- <body onload="onloadShowDivforWard()"> -->
<body>


	<div class="form_stylings">
		<div class="header">
			<h3><spring:message htmlEscape="true" code="${formTitle}" /></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		
		<div class="clear"></div>
		<div class="page_content">
		<div class="form_container">
			<form:form action="createWard.htm" method="POST" commandName="wardForm"  id="localGovtBodyForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveandUpdateWard.htm"/>" />
				<form:hidden path="status" value="P" />
				<form:hidden path="PanchayatType" />
				
				
				<%@include file="../common/showLBHierarchyHeader.jsp"%> 
				<li>
									    <label class="inline">&nbsp;</label>
									    <label>
										<input class="bttn" type="button" id="actionFetchWardDetails" value="Fetch Wards" />
										<input class="bttn" type="button" id="actionSearchClose" value="Close" />	
										</label>
				</li>
				<%@include file="../common/showLBHierarchyFooter.jsp"%> 
			</form:form>
		</div>
		
			<c:if test="${showTable eq true}" >
			<form:form  method="POST" commandName="wardForm"  id="publishWardForm">
			<form:hidden path="publishWardsList" />
			<form:hidden path="deleteWardsList" />
			<form:hidden path="localBodyCode"  value="${lbCode}"/>
			<form:hidden path="PanchayatType" />
			<form:hidden path="lbTypeHierarchy" />
				<form:hidden path="paramLocalBodyTypeCode" />
				<form:hidden path="localBodyLevelCodes" />
			<div class="form_container">
				
						<div class="form_block">
							<div class="col_1">
								<h4>Published Wards</h4>
								<ul class="form_body" >
													 		
					
				<li>		 
					<table class="display" id="demand1">
							<thead>
								<tr class="tblRowTitle tblclear" id="trhead">
									<th align="left" >Serial No.</th>
									<th align="left">Ward Number</th>
									<th align="left">Ward Name In English</th>
									<th align="left">Ward Name Local</th>
									
								</tr>
							</thead>
							<tbody>
							     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
																		items="${wardList}">
																		
																		
									<tr  id="trid${offsets*limits+(listAdminRow.index+1)}"  >
										<td> ${offsets*limits+(listAdminRow.index+1)}</td>
										<td> ${adminEntityDetail.wardNumber}</td>
										<td> ${adminEntityDetail.wardNameInEnglish}	</td>
										<td> ${adminEntityDetail.wardNameInLocal}</td>
									</tr>
																			
									
								</c:forEach>
							</tbody>
						</table>
						
						<c:forEach var="wardList" varStatus="listAdminRow" items="${wardList}">
							<c:if test="${!empty listWardDetailstemp}">
									<c:forEach var="tempWardList" varStatus="listAdminRow1" items="${listWardDetailstemp}">
											<c:if test="${wardList.wardCode eq tempWardList.wardCode}">
													<c:if test="${tempWardList.isupdate}">
														 <script>
															$('#trid'+'${offsets*limits+(listAdminRow.index+1)}').css("background-color", "#FFFF94");
														</script>
																					
																				
													</c:if>
													<c:if test="${tempWardList.isdelete}">
														<script>
														$('#trid'+'${offsets*limits+(listAdminRow.index+1)}').css("background-color", "#FF9999");
														</script>
													</c:if>
																	    
																	 
											</c:if>
									 </c:forEach>
								</c:if>
						</c:forEach>
				</li>	
				
					
				</ul>
				</div>
				</div>
				
				</div>
				<table>
				<tr>
				<td bgcolor="" height="5%" width="2%">&nbsp;</td>
				<td bgcolor="" height="5%" width="2%">&nbsp;</td>
				<td bgcolor="#70DB70" height="5%" width="2%">&nbsp;</td>
															<td width="2%">New Ward</td>
															<td bgcolor="#FFFF94" height="5%" width="2%">&nbsp;</td>
															<td width="2%">Update</td>
															<td bgcolor="#FF9999" height="5%" width="2%">&nbsp;</td>
															<td width="2%">Delete</td>
			
				</tr>
				</table>
				<div class="form_container">
				
				<div class="form_block">
					<div class="col_1">
						<h4>Un Published Changes</h4>
						<ul class="form_body" >
													 		
					
				<li>		 
					<table  class="display" id="demand">
							<thead>
								<tr class="tblRowTitle tblclear" id="trhead">
									<th width="3%" align="left">Serial No.</th>
									<th width="3%" align="left">Ward Number</th>
									<th width="15%" align="left">Ward Name In English</th>
									<th width="15%" align="left">Ward Name Local</th>
								    <th width="32%" align="left">Publish Changes <input type="checkbox" id="publishAll" onchange="selectAll();"/>Publish All Changes </th>
								    <th width="32%" align="left">Delete Changes <input type="checkbox" id="deleteAll"  onchange="deleteAllWards()"/> Delete  All Changes </th>
								</tr>
							</thead>
							<tbody>
							     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
																		items="${listWardDetailstemp}">
									<tr id="${adminEntityDetail.temp_ward_code}" <c:if test="${adminEntityDetail.isnew}" > style="background-color:#70DB70" </c:if> <c:if test="${adminEntityDetail.isupdate}" > style="background-color: 	#FFFF94" </c:if><c:if test="${adminEntityDetail.isdelete}" > style="background-color: 	#FF9999" </c:if>><!-- </tr> --> 
										<td> ${offsets*limits+(listAdminRow.index+1)}</td>
										<td> ${adminEntityDetail.wardNumber}</td>
										<td> ${adminEntityDetail.wardNameEnglish}	</td>
										<td> ${adminEntityDetail.wardNameLocal}</td>
									
									    <td> <input type="checkbox" id="p${adminEntityDetail.temp_ward_code}" value="${adminEntityDetail.temp_ward_code}" onchange="selectWard(this.value)"/> <%-- <input type="button" name="button" value="Publish" onclick="publishward('${adminEntityDetail.temp_ward_code}','${adminEntityDetail.isdelete}','${adminEntityDetail.isupdate}','${adminEntityDetail.isnew}',false)"/> --%> </td>
									    <td> <input type="checkbox" id="d${adminEntityDetail.temp_ward_code}" value="${adminEntityDetail.temp_ward_code}" onchange="deleteWard(this.value)"/> <%-- <input type="button" name="button" value="Delete" onclick="deletetempdata('${adminEntityDetail.temp_ward_code}','${offsets*limits+(listAdminRow.index+1)}')"/> --%></td>
									</tr>
								</c:forEach>
							
							</tbody>
					</table>
					</li>	
					<li>
									    <label class="inline">&nbsp;</label>
									    <label>
										<input type="button" class="bttn" name="PublishWards" value="Publish Wards Changes" onclick="fetchWardValues();"/>
										<input type="button" class="bttn"  name="PublishWards" value="Delete Wards Changes" onclick="deleteWardValues();"/>
										</label>
					</li>
								
							
				
				
					
				</ul>
				</div>
				</div>
				
				</div>
				<div class="form_container">
				
				<div class="form_block">
					<div class="col_1">
						<h4>Deleted Wards List</h4>
						<ul class="form_body" >
													 		
					
				<li>		 
					<table class="display" id="demand2">
								<thead>
									<tr class="tblRowTitle tblclear" id="trhead">
										<th align="left" >Serial No.</th>
										<th align="left">Ward Number</th>
										<th align="left" >Ward Name In English</th>
										<th align="left">Ward Name Local</th>
										<th align="left">Restore Ward</th>
										
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty listWardDetailsdetails}">
								     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
																			items="${listWardDetailsdetails}">
										<tr>
											<td> ${offsets*limits+(listAdminRow.index+1)}</td>
											<td> ${adminEntityDetail.wardNumber}</td>
											<td> ${adminEntityDetail.wardNameEnglish}</td>
											<td> ${adminEntityDetail.wardNameLocal}</td>
											<td>  <input type="button" id="${offsets*limits+(listAdminRow.index+1)}" value="Restore ward" onclick="restoreWard('${adminEntityDetail.wardCode}','${adminEntityDetail.wardVersion}',$(this).parent().parent())" />                                </td>
										</tr>
									</c:forEach>
									</c:if>
								
								</tbody>
								</table>		
				</li>	
				
					
				</ul>
				</div>
				</div>
				
				
				</div>
				
			
		
	
	</form:form>
	</c:if>
	
	
	<script src="/LGD/JavaScriptServlet">
	
	</script>
</div>
</div>
</body>
</html>