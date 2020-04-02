<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="organizationUnitJavascript.jsp"%>
<%@include file="../common/dwr.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3></h3>
			<ul class="item_list">
				<%-- //these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
 --%>			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form method="POST" id="organisationunit" commandName="adminOrgDeptForm" action="organizationUnit.htm" >	
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="organizationUnit.htm"/>" />	
				<form:hidden path="updatedSubOfficeList"/>	
				<input type="hidden" name="selectdOrgName" id="selectdOrgName"	/>		
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4>Add/Manage Sub Offices</h4>
								<ul class="form_body" >
									<c:choose>
										<c:when test="${stateCode ne 0}">
										<li>
											<spring:message htmlEscape="true"  code="Label.STATENAME"/> &nbsp;:&nbsp;
											<span style="font-weight: bold;"><c:out value="${stateName}" escapeXml="true"></c:out></span>							
										</li>
										</c:when>
										<c:otherwise>
											<li>
												<spring:message htmlEscape="true"  code="Label.MINISTRIES"/>
												<span class="mandate">*</span><br/>
												<form:select path="ministryId" id="ministryId" class="combofield" >
													<form:option value="">																
														<spring:message code="Label.SELECT" htmlEscape="true"/>
													</form:option>
													<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
												</form:select>	
												<br/>	
												<span class="error" id="ministrycode"></span>																					
												<form:errors  path="ministryId" htmlEscape="true"  cssClass="errormsg"/>
												<br/>
											</li>
										</c:otherwise>
									</c:choose>
									<li>
										<spring:message htmlEscape="true"  code="Label.SELORG"/>/Department
										<span class="mandate">*</span><br/>
										<form:select path="deptOrgCode" id="orgCombo" class="combofield">
											<form:option value="">																
												<spring:message code="Label.SELECT" htmlEscape="true"/>
											</form:option>
											<form:options items="${orgList}" itemLabel="orgName" itemValue="orgCode" />
										</form:select>											
										<span class="error" id="orgCode"></span>							
										<form:errors  path="deptOrgCode" htmlEscape="true"  cssClass="errormsg"/>												
										<br/>
									</li>
									<li>
										 <input class="bttn" type="button" id="getdata" value="<spring:message htmlEscape="true" code="Button.GETDATA"/>" />	
									</li>
									<br/>								
									<!-- Block for add organization detail  Started -->
									<div id="addSubOffyc" style="display: none;">
											<h4><spring:message code='Label.SubOffices' htmlEscape='true'/></h4>
											<input type="text" name="noOfRow" id="noOfRow" style="width:90px;margin-right: 25px;" maxlength="3"/>
											<input type="button"  id="btnAddOfficeDetail" value="Add Rows"/>
											<span class="error" id="noOfRowErr"></span>											
									        <div class="specified">
							        			<div class="greenish">
							          				<div></div>
							           				  <b class="boldCharacter"><spring:message text="Saved Sub Offices"></spring:message></b>
							        			</div>      			
											</div>
											<table class="data_grid" style="width: 70%;">
												<thead>
													<tr>
														<th width="60%"><spring:message code='Label.SubOfficeName' htmlEscape='true' text="Add Sub Offices"/></th>														
														<th width="7%">Action</th>	
													</tr>
												</thead>
												<tbody id="tbodyOfficeDetail" >
													<c:choose>
														<c:when test="${admionFormList ne null}">
													
															<c:forEach items="${admionFormList }" var="adminFormList" varStatus="index">
																<c:choose>
																	<c:when test="${adminFormList.olc ne null }">
																		 <tr class=row>
																		 	<td><input type="text" name="departmentName" id="${adminFormList.olc}"  maxlength="198" value="<c:out value='${adminFormList.departmentNameEnglish}'/>" style="width: 700px;"/>
																		 		<c:forEach var="errMap" items="${errMsgMap}">
																		 			<c:set var="adminListElementid">${adminFormList.olc }</c:set>
																		 			<c:set var="mapid">${errMap.key}</c:set>
																		 			<c:if test="${adminListElementid eq mapid }">
																		 				<span class="errormessage">${errMap.value}</span>
																		 			</c:if>
																				</c:forEach>
																		 	</td>
																		 	<td></td>
																		 </tr>
																	</c:when>
																	<c:otherwise>
																	<tr class=rows>
																		<td><form:input path="departmentNameEnglish" id="officeName" maxlength="198" style="width:700px;" value="${adminFormList.departmentNameEnglish}" htmlEscape="true"/>
																		<br/>
																		<c:forEach var="errMap" items="${errMsgMap}">
																		 <c:set var="adminElementname">${adminFormList.departmentNameEnglish }</c:set>
																		 <c:set var="mapid">${errMap.key}</c:set>
																 			<c:if test="${mapid eq adminElementname }">
																 				<span class="errormessage"><c:out value="${errMap.value}" escapeXml="true"></c:out></span>
																 			</c:if>
																		</c:forEach>
																		</td>														
																		<td width='7%' style="text-align: center;"><a href='#' id="btnRemoveOfficeDetail_${index.count }"><img src='images/delete.png' width='18' height='19' border='0' align="middle" /></a></td>													
																	</tr>	
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</c:when>
													</c:choose>
												</tbody>
											</table>
									<br/>
									<!-- Block for add organization detail Ends Here -->
								</div>	
								</ul>	
							</div>
							
			
						</div>
					</div>
					<br/>
					<div id="buttonFormAction" style="display: none;">
					 <input class="bttn" id="btnFormActionSave"  type="submit" value="<spring:message code='Button.SAVE' htmlEscape='true'/>" param="SAVE"/>
					 <input class="bttn" id="btnFormActionClr" type="button" value="<spring:message htmlEscape="true" code="Button.CLEAR"/>"  param="CLEAR"/>
					 <input class="bttn" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>	
					</div>
					
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>