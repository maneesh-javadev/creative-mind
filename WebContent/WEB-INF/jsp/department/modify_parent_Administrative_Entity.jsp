<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="js/departmentAdminUnit.js"></script>
<script type='text/javascript' src="js/notie/notie.js"></script>
<script type='text/javascript' src="js/notie/notie.min.js"></script>

<link rel="stylesheet" href="js/notie/notie.css">
	<link rel="stylesheet" href="js/notie/notie.min.css">
		<script type='text/javascript'
			src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

		<script type="text/javascript" language="javascript">
			dwr.engine.setActiveReverseAjax(true);
			$(document)
					.ready(
							function() {
								var childRecords = document
										.getElementById("childRecords").value;
								var parentAdminCode = '${deptAdminUnitForm.parentAdminCode}';
								var adminUnitLevCode = document
										.getElementById("administationUnit").value;
								paretnunitCode = '${deptAdminUnitForm.parentAdminCode}';
								$('#unitLevelCode').val(adminUnitLevCode);
								$('#parentUnit').val(parentAdminCode);
								if (childRecords > 0) {
									document.getElementById("unitLevelCode").disabled = true;
									document.getElementById("parentUnit").disabled = true;
								}
							});

			$(document).ready(function() {
				$("#parentUnit").val('0');
				$('#abc').dataTable({
					"sScrollY" : "100%",
					"sScrollX" : "100%",
					"bScrollCollapse" : true,
					"bPaginate" : true,
					"aoColumnDefs" : [ {
						"sWidth" : "10%",
						"aTargets" : [ -1 ]
					} ],
					"bJQueryUI" : true,
					"aaSorting" : [],
					"aoColumns" : [ null,

					],
					"sPaginationType" : "full_numbers"

				});

			});

			function alertboxbtnYES() {
				document.form1.method = "post";
				document.form1.action = "modifyParentEntity.htm?<csrf:token uri='modifyParentEntity.htm'/>";
				document.form1.submit();

			}

			function alertboxbtnNO() {

				$('#myModal').modal('hide');
				return false;

			}

			/*  if($('#parentUnit option:selected').val() != '0'){
			$('#fy').val($('#parentUnit option:selected').val());
			errorFlag=1;
			 }else{
			 	errorFlag=0;
			
			 notie.alert({ type: 2, text: 'Please select a Parent level Administrative Unit Entity First.', time: 4 });
			}
			
			 */
			function validate() {

				if ($('#parentUnit > option:selected').val() != ''
						&& parseInt($('#parentUnit > option:selected').val()) > 0) {
					errorFlag = 1;
					$('#myModal').modal('show');
					return true;
				} else {

					notie.alert({
						type : 3,
						text : 'Please Select the  Parent Entity ',
						time : 4
					});
					return false;
				}

			}
		</script>
		<script>
			/*  $( document ).ready(function() {
				$("#submit1").click(function(){
					  $('#myModal').modal('show');
			         // show Modal
			       
			    });
				
			}); */
		</script>
		<style>
</style>
</head>
<body>
	<section class="content">
	<div class="row">
		<section class="col-lg-12 ">
		<div class="box ">
			<div class="box-header with-border">
				<h3 class="box-title">
					<spring:message code="Label.ChangeParentAdminstrativeEntity"
						htmlEscape="true" text="Change Parent Adminstrative Entity of "></spring:message>${deptAdminUnitForm.adminLevelNameEnglish }</h3>

			</div>
			<div class="box-body">
				<form:form action="modifyParentEntity.htm"
					onsubmit="return validateSubmit();" name="form1"
					class="form-horizontal" method="POST" id="form1"
					commandName="adminUnitView">
					<input type="hidden" name="<csrf:token-name/>" value="" />
					<input type="hidden" name="stateCode" id="stateCode"
						value="<c:out value='${stateCode}'  escapeXml='true'></c:out>" />
					<input type="hidden" id="districtCode"
						value="<c:out value='${districtCode}' escapeXml='true'></c:out>"></input>
					<form:hidden path="adminUnitEntityCode"
						value="${deptAdminUnitForm.adminUnitCode}" />
					<form:hidden path="createdby"
						value="${deptAdminUnitForm.createdby}" />
					<form:hidden htmlEscape="true" path="parentCategory"
						value="${deptAdminUnitForm.parentCategory}" />
					<input type="hidden" id="parentAdminCode"
						value="<c:out value='${deptAdminUnitForm.parentAdminCode}' escapeXml='true'></c:out>" />
					<input type="hidden" id="parentCode" value="1" />
					<input type="hidden" id="administationUnit"
						name="adminUnitLevelCode"
						value="<c:out value='${deptAdminUnitForm.adminUnitLevelCode}' escapeXml='true'></c:out>" />
					<input type="hidden" id="childRecords"
						value="<c:out value='${deptAdminUnitForm.childRecords}' escapeXml='true'></c:out>" />
					<input type="hidden" id="adminUnitName"
						value="<c:out value='${deptAdminUnitForm.adminLevelNameEnglish }' escapeXml='true'></c:out>" />
					<input type="hidden" id="adminUnitNameLocal"
						value="<c:out value='${deptAdminUnitForm.adminLevelNameLocal }' escapeXml='true'></c:out>" />
					<input type="hidden" id="adminUnitCodehidden"
						value="<c:out value='${deptAdminUnitForm.administrationUnit }' escapeXml='true'></c:out>" />
					<input type="hidden" name="oldParentAdminUnitEnti tyCode"
						value="<c:out value='${deptAdminUnitForm.parentAdminCode }' escapeXml='true'></c:out>" />



					<div class="box-header subheading">
						<h4>
							<spring:message htmlEscape="true" code="Label.GENDETAILENTITY"
								text="General Details of Admin Unit Entity"></spring:message>
						</h4>
					</div>

					<%-- <div class="form-group">
								<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELDeptAdministrationUNIT" text="Type of Administrative unit Level"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">
									<form:select htmlEscape="true" path="adminUnitLevelCode" onchange="resetAdminParentIndex();getUnitLevelEntity();" class="form-control" id="unitLevelCode">
										<form:option value="0">
											<spring:message code="Label.SELECT" text="-slect"></spring:message>
										</form:option>
										<form:options items="${unitLevels}" itemLabel="adminLevelNameEng" itemValue="adminUnitCode" />
									</form:select> 
									<div class="errormsg">
										<form:errors htmlEscape="true" path="adminUnitLevelCode"></form:errors>
									</div>
									<div id="parentAdminUnitLevel" style="color: red;"></div>
								</div>
				             </div> --%>


					<div class="form-group" id="statePopup" style="display: none;">
						<label class="col-sm-3 control-label"><spring:message
								htmlEscape="true" code="Label.SELECTSTATE"
								text="Type of Administrative unit Level"></spring:message><span
							class="mandatory">*</span></label>
						<div class="col-sm-6">
							<select class="form-control"
								onchange="getUnitParentLvlEntity(this.value);" id="stateId">
								<option value="0">
									<spring:message code="Label.SELECT"></spring:message>
								</option>
								<c:forEach var="stateList" items="${stateList}">
									<option value="${stateList.stateCode}">
										<c:out value='${stateList.stateNameEnglish}' escapeXml='true'></c:out>
									</option>
								</c:forEach>
							</select>
							<div class="errormsg" id="err_state" style="color: red;"></div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								htmlEscape="true" code="Label.Existing Parent "
								text="Existing Parent "></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							<input type="text" class="form-control" readonly="true"
								id="prntAdminUnitEntityCode" value="${ParentAdminLevelName}" />
							<%-- <form:select htmlEscape="true" path="parentAdminUnitEntityCode"  class="form-control" id="parentUnit" onchange="checkmodifyEntityName();">
										<form:option value="0">
											<spring:message code="Label.SELECT" text="-slect"></spring:message>
										</form:option>
										<form:options disabled="true" items="${unitEntities}" itemLabel="adminLevelNameEng" itemValue="parentAdminCode" />
									</form:select>
									<div id="parentAdminUnitforEntity" style="color: red;"></div> --%>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								htmlEscape="true" code="Label.NewParent" text="New parent"></spring:message><span
							class="mandatory">*</span></label>
						<div class="col-sm-6">
							<form:select htmlEscape="true" path="parentAdminUnitEntityCode"
								class="form-control validate[required]" id="parentUnit"
								onchange="checkmodifyEntityName();validate();">
								<form:option value="0">
									<spring:message code="Label.SELECT" text="-slect"></spring:message>
								</form:option>
								<form:options items="${unitEntities}"
									itemLabel="adminLevelNameEng" itemValue="parentAdminCode" />
							</form:select>
							<div id="parentAdminUnitforEntity" style="color: red;"></div>
						</div>
					</div>


					<div class="form-group">
						<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message
								htmlEscape="true" code="Label.AdminUnitEntityEng"
								text="Administrative Unit Entity Name(In English)"></spring:message><span
							class="mandatory">*</span></label>
						<div class="col-sm-6">
							<form:input path="adminEntityNameEnglish" readonly="true"
								id="adminLevelNameEng"
								onblur="chekModifyNameValidatonsforEntity(this.value);"
								name="adminLevelNameEng" htmlEscape="true" class="form-control"
								value="${deptAdminUnitForm.adminLevelNameEnglish }"
								maxlength="100" />
						</div>
						<div class="errormsg">
							<form:errors htmlEscape="true" path="adminEntityNameEnglish"></form:errors>
						</div>
						<div id="UniqueDeptAdminError" style="color: red;"></div>
					</div>

					<div class="form-group">
						<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message
								htmlEscape="true" code="Label.AdminUnitEntityLoc"
								text="Administrative Unit Entity Name (In Local)"></spring:message></label>
						<div class="col-sm-6">
							<form:input path="adminEntityNameLocal" readonly="true"
								id="adminLevelNameLocal" name="adminLevelNameLocal"
								htmlEscape="true" class="form-control"
								value="${deptAdminUnitForm.adminLevelNameLocal }"
								maxlength="100" />
						</div>
						<div class="errormsg">
							<form:errors htmlEscape="true" path="adminEntityNameLocal"></form:errors>
						</div>

					</div>
					<div class="form-group" style="display: none">
						<a href="#" style="text-decoration: underline; color: navy;"
							onclick="showReports();">View Affected Organization Units</a>
					</div>
					<div id="dialog-confirm" title="Administrative Units ?"
						style="display: none">
						<p>
							<span class="ui-icon ui-icon-alert"
								style="float: left; margin: 0 7px 20px 0;"></span> Are you
							confirmed to Modify Admin Entity ?
						</p>
					</div>



					<div class="form-group">
						<br /> <br /> <br />

					</div>
					<c:if test="${ShowHide}">
						<div class="box-header  subheading">
							<h3 class="box-title">
								<spring:message
									text="List of Org Units created on the selected Admin Unit." />
							</h3>

						</div>
						<%-- <c:if test="${deptOrgList}"> --%>
						<div class="frmpnlbg" id="divData">
							<div class="table-responsive">
								<table class="table table-striped table-bordered  "
									cellspacing="0" id="abc">
									<thead>
										<tr>
											<th rowspan="2" align="right"><spring:message htmlEscape="true"
													code="Org Code"></spring:message></th>
											<th rowspan="2"><spring:message code="org name"
													text="Org Name (In English)"></spring:message></th>
											<th rowspan="2" align="right"><spring:message htmlEscape="true"
													code="Org Unit Code"></spring:message></th>
											<th rowspan="2"><spring:message code="org name"
													text="Org Unit Name (In English)"></spring:message></th>
										</tr>
									</thead>
									<tbody>
										<c:set var="allAdminCode" />
										<c:forEach var="adminEntityDetail" varStatus="listAdminRow"
											items="${deptOrgList}">
											<tr>
												<td align="right"><c:out
														value="${adminEntityDetail.orgCode}"></c:out></td>
												<td align="left"><c:out
														value="${adminEntityDetail.orgName}"></c:out></td>

                                               <td align="right"><c:out
														value="${adminEntityDetail.orgUnitCode}"></c:out></td>
												<td align="left"><c:out
														value="${adminEntityDetail.orgUnitName}"></c:out></td>


											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>

						</div>
					</c:if>
					<div class="form-group">
						<br /> <br />
					</div>
					<div class="box-footer">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="pull-right">
								<button type="button" id="submit1" name="Submit"
									class="btn btn-success open" id="btnSaveDept"
									onclick="validate();">
									<i class="fa fa-floppy-o"></i>
									<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>
								</button>
								<button type="button" id="Submit6" class="btn btn-warning"
									onclick="emptymodifyEntityElements()">
									<i class="fa fa-times-circle"></i>
									<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>
								</button>
								<button type="button" name="Submit6" class="btn btn-danger"
									onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';">
									<i class="fa fa-times-circle"></i>
									<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>
								</button>
							</div>
						</div>
					</div>
					<%-- </c:if>  --%>
				</form:form>

			</div>
		</div>
		</section>
		<div class="modal fade modal-admin" id="myModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">

						<h4 class="modal-title" id="alertboxTitle">Do You Want to
							Change the Parent Entity .</h4>
					</div>

					<div class="modal-footer">
						<button type="button" onclick="alertboxbtnYES();"
							class="btn btn-success">YES</button>
						<button type="button" onclick="alertboxbtnNO();"
							class="btn btn-danger">NO</button>


					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
<script>
	
</script>

</script>