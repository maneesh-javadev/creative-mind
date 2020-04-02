<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0"> 
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="organizationUnitJavascript.jsp"%>
<%@include file="../common/dwr.jsp"%>
</head>
<body>
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
            <div class="box">       
            <div class="box-body">
		            	<form:form method="POST" id="organisationunit" class="form-horizontal" commandName="adminOrgDeptForm" action="organizationUnit.htm" >	
							<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="organizationUnit.htm"/>" />	
							<form:hidden path="updatedSubOfficeList"/>	
							<input type="hidden" name="selectdOrgName" id="selectdOrgName"	/>
							
							<c:choose>
								<c:when test="${stateCode ne 0}">
									<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.STATENAME"></spring:message></label>
										<div class="col-sm-6">
											<c:out value="${stateName}" escapeXml="true"></c:out>
										</div>
						             </div>
								</c:when>
								
								<c:otherwise>
									<div class="form-group">
										<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.MINISTRIES"></spring:message><span class="mandatory">*</span></label>
										<div class="col-sm-6">
											<form:select path="ministryId" id="ministryId" class="form-control" >
												<form:option value="">																
													<spring:message code="Label.SELECT" htmlEscape="true"/>
												</form:option>
												<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
											</form:select>
										</div>
										<span class="error" id="ministrycode"></span>																					
										<form:errors  path="ministryId" htmlEscape="true"  cssClass="errormsg"/>
				             		</div>
								</c:otherwise>
							</c:choose>
							
							<div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELORG"></spring:message>/Department<span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select path="deptOrgCode" id="orgCombo" class="form-control">
										<form:option value="">																
											<spring:message code="Label.SELECT" htmlEscape="true"/>
										</form:option>
										<form:options items="${orgList}" itemLabel="orgName" itemValue="orgCode" />
									</form:select>											
								</div>
								<span class="error" id="orgCode"></span>							
								<form:errors  path="deptOrgCode" htmlEscape="true"  cssClass="errormsg"/>
		             		</div>
		             		
		             		
                                  <div class="col-sm-offset-2 col-sm-10">
						                 <div class="pull-right">
						                   <button type="button" class="btn btn-success " id="getdata" name="Submit" class="btn btn-success" ><i class="fa fa-floppy-o"></i> <spring:message code="Button.GETDATA"  htmlEscape="true"></spring:message></button>
						                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
						                 </div>
						           </div>   
						    
						       
		             		
							
							<div id="addSubOffyc" style="display: none;">
								
								<div class="box-header subheading">
                             		<h4><spring:message htmlEscape="true" code="Label.SubOffices"></spring:message></h4>
		                   		</div>
								<div class="form-group">
									<div class="col-sm-4">
										<input type="text" name="noOfRow" placeholder="Enter Number of rows" id="noOfRow" class="form-control" maxlength="3"/>
									</div>
									<div class="col-sm-6">
										<button type="button" id="btnAddOfficeDetail" class="btn btn-primary"><i class="fa fa-floppy-o"></i>Add Rows</button>
										<span class="error" id="noOfRowErr"></span>	
									</div>
							
								</div>
							
							 <div class="specified">
			        			<div class="greenish">
			          				<div></div>
			           				  <b class="boldCharacter"><spring:message text="Saved Sub Offices"></spring:message></b>
			        			</div>      			
							</div>
							
							<table class="table table-bordered table-hover" cellspacing="0" style="width: 70%;">
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
																		 <tr>
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
																	<tr>
																		<td><form:input path="departmentNameEnglish" id="officeName" maxlength="198" class="form-control" value="${adminFormList.departmentNameEnglish}" htmlEscape="true"/>
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
							</div>
							
							
				<div id="buttonFormAction" style="display: none;">
					   <div class="box-footer">
		                   <div class="col-sm-offset-2 col-sm-10">
								<div class="pull-right">
								 <button class="btn btn-success" id="btnFormActionSave"  type="submit"param="SAVE"><i class="fa fa-floppy-o"></i> <spring:message code='Button.SAVE' htmlEscape='true'/></button>
								 <button class="btn btn-warning" id="btnFormActionClr" type="button"   param="CLEAR"><spring:message htmlEscape="true" code="Button.CLEAR"/></button>
								 <button class="btn btn-danger" type="button" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"> <i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"/>	</button>
						       </div>
					       </div>
				      </div>
			    </div>
							
					</form:form>
						</div>
		          
             
   </div>
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>