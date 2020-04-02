<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="createPRIWardJs.jsp"%>
</head>
<body>
	<div class="form_stylings">
		<div class="header">
			<h3><spring:message htmlEscape="true" code="${formTitle}" /></h3>
			<ul class="item_list">
			<%--//these links are not working 	<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="page_content">
		<div class="form_container">
			<form:form action="createWard.htm" method="POST" commandName="wardForm"  id="localGovtBodyForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveandUpdateWard.htm"/>" />
				<form:hidden path="status" value="S" />
				<form:hidden path="PanchayatType"  />
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
		
			<c:if test="${showTable eq true}">
			<div class="form_container">
			<form:form  commandName="wardForm"  id="wardForm" name="wardForm">
			<input type="hidden" id="formNominations"	name="formNominations" />
			<input type="hidden" id="newWardList" name="newWardList" />
			<input type="hidden" id="deleteWardList" name="deleteWardList" />
			<form:hidden path="localBodyCode" value="${lbCode}" />
			 
			
					<div id="UniqueWardNameError" style="color: red;"></div>
					<div id="wardnameMsg" style="display:none"><spring:message code="error.blank.WARDNAME" htmlEscape="true"/></div>
				    <%-- <div class="errormsg" id="wardname_error1"><form:errors path="ward_Name" htmlEscape="true"/></div>  
					<div class="errormsg" id="wardname_error2" style="display: none" ></div> --%>
					<div id="UniqueWardCodeError" style="color: red;"></div>
					<div id="wardnumberMsg" style="display:none"><spring:message code="error.blank.WARDNUMBER" htmlEscape="true"/></div>
					<div class="errormsg" id="wardnumber_error1"><form:errors path="ward_number" htmlEscape="true"/></div>  
					<div class="errormsg" id="wardnumber_error2" style="display: none" ></div>	
					<div class="errormsg" id="wardnamdlocal_error1"><form:errors path="ward_NameLocal" htmlEscape="true"/></div>	
					<div id="wardnamdlocalMsg" style="display:none"><spring:message code="error.blank.WARDNAMELOCAL" htmlEscape="true"/></div>
					<div class="errormsg" id="wardnamdlocal_error2" style="display: none" ></div>	
				
				
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message code="Label.listofWards" text="List of Wards"></spring:message></h4>
								<ul class="form_body" >
					<li>
					<spring:message code="Label.NoofWards" text="Enter No. of  New Wards to be Added"></spring:message>
					<input type="text" id="noofWards" maxlength="3" onkeypress="return validateNumericKeys(event);" style="width: 100px;" ></input><input type="button" onclick="addarow('1')" value="Add Rows" />		
				</li>									 		
					
				<li>		 
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="demand">
					<thead>
						<tr class="tblRowTitle tblclear" id="trhead">
							<th align="left">Serial No.</th>
							<th><spring:message    code="Label.enableeDiting" htmlEscape="true" text="Enable a Ward For Editing"></spring:message></th>
							<th align="left"><spring:message	code="Label.WARDNUMBER" htmlEscape="true"></spring:message></th>
							<th align="left"><spring:message	code="Label.WARDNAMEEnglish" htmlEscape="true" text="Ward Name(In English)"></spring:message> </th>
							<th align="left"><spring:message    code="Label.WARDNAMEINLOCAL" htmlEscape="true"></spring:message></th>
						
							<th align="left"><spring:message    code="Label.Delete" htmlEscape="true" text="Delete Ward"></spring:message></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<input type="hidden" name="wardName" value="" />
					<input type="hidden" name="listWardDetails" value="${wardList}"/>
					<c:forEach var="adminEntityDetail" varStatus="listAdminRow"	items="${wardList}">
							<tr>
								<td> ${offsets*limits+(listAdminRow.index+1)}</td>
								
								<td align="center"><input type="checkbox" text="Update" onchange="" value="Update" id="checkbox${offsets*limits+(listAdminRow.index+1)}" onclick="getExistingWardCheck('${adminEntityDetail.wardCode}','${offsets*limits+(listAdminRow.index+1)}','U'); enableEditing('${offsets*limits+(listAdminRow.index+1)}')"></input>
								    <input type="checkbox" text=check value="${adminEntityDetail.wardCode}" id="check${offsets*limits+(listAdminRow.index+1)}" style="display: none" ></input>
								</td>
								<td>  <input type="text" id="wardnumber${offsets*limits+(listAdminRow.index+1)}" name="wardnumber${offsets*limits+(listAdminRow.index+1)}"  size="10" maxlength="10" value="${adminEntityDetail.wardNumber}" disabled="true"
																		style="width: 100px;"
																		onfocus="validateOnFocus('wardnumber');changeErrorClass(this.id);"
																		onblur="UniqueWardValidation(this.value,2,'${offsets*limits+(listAdminRow.index+1)}');vlidateOnblur('wardnumber','1','15','c');"
																		onkeypress="return validateNumberWardNumUrban(event);"></input>
									  <input type="hidden" id="wardnumberOld${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNumber}" />						
								
								</td>
								<td> <input type="text" id="wardname${offsets*limits+(listAdminRow.index+1)}" name ="wardName${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNameInEnglish}" disabled="true"
																		style="width: 250px;" maxlength="250" 
																		onfocus="validateOnFocus('wardname');changeErrorClass(this.id);"
																		onblur="UniqueWardValidation(this.value,1,'${offsets*limits+(listAdminRow.index+1)}');vlidateOnblur('wardname','1','15','m');"
																		onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"
																		htmlEscape="true"></input>
									<input type="hidden" id="wardnameOld${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNameInEnglish}" />							
				
								</td>	
								
								<td> <input type="text"	id="wardnamelocal${offsets*limits+(listAdminRow.index+1)}" name="wardnamelocal${offsets*limits+(listAdminRow.index+1)}" disabled="true" value="${adminEntityDetail.wardNameInLocal}"
																		style="width: 250px;"
																		onfocus="validateOnFocus('wardnamelocal');changeErrorClass(this.id);"
																		 maxlength="250"
																		 onblur="validateSpecialCharactersWardNLocal(this.value);"></input>
									<input type="hidden" id="wardnamelocalOld${offsets*limits+(listAdminRow.index+1)}" value="${adminEntityDetail.wardNameInLocal}" />					
								
								
								</td>
								<td align="center"><input type="checkbox" text="Delete" onchange="getExistingWardCheck('${adminEntityDetail.wardCode}','${offsets*limits+(listAdminRow.index+1)}','D'); deletecheckbox('${offsets*limits+(listAdminRow.index+1)}')" value="delete" id="delete${offsets*limits+(listAdminRow.index+1)}" onclick=""></input>
								    <input type="checkbox" text="delete" value="${adminEntityDetail.wardCode}" id="deletev${offsets*limits+(listAdminRow.index+1)}" style="display: none" ></input>
								</td>
								
								<td></td>
								<td></td>
								<td>
								
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</li>	
				
				<li>
								<label class="inline">&nbsp;</label>
								<label>
								<input class="bttn" type="button" id="btnSave" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return saveEnabledvalues(); " />
								<input type="button" onclick="addarow('2')" value="Add Another Row" class="bttn" />
								<%-- <input type="button" class="bttn" name="Submit6" value="<spring:message code="Button.CLEAR"></spring:message>" onclick="ClearForm();" />	  --%>	
								<input type="button" class="bttn" name="Cancel" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label>	
								
				</li>	
				</ul>
				</div>
				</div>
				</div>
				
				
				<div class="btnpnl" id="btnpanel" >
						<ul>
							
						</ul>
					</div>
				
			
		
	</form:form>
	</div>
	</c:if>
	<script src="/LGD/JavaScriptServlet"></script>
</div>
</div>
</body>
</html>