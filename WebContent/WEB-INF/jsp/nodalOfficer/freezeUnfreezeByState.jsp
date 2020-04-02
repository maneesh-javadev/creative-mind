 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <%@include file="../common/taglib_includes.jsp"%>
<link rel="stylesheet" href="<%=contextpthval%>/css/stateFreeze.css"></link>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script src="<%=contextpthval%>/resource/common-resource/jszip.min.js"></script>
<script	src="<%=contextpthval%>/resource/common-resource/kendo.all.min.js"></script>
<script>
	/* $(document).ready(function(){
	 $('#example').dataTable();
	 }); */

	 $(document).ready(function() {

			$("#title").hide();
			$("#sign").hide();
			$("#confirm").hide();
			/* $("#ulbexceptioncase").hide(); */
		});

	function ExportPdf() {

		$("#l1").hide();
		$("#l2").hide();
		$("#l3").hide();
		$("#title").show();
		$("#sign").show();
		$("#confirm").show();
		

		kendo.drawing.drawDOM("#printableArea", {
			orientation : 'landscape',
			// paperSize: "A4",
			margin : {
				top : "1cm",
				bottom : "1cm"
			},
			scale : 0.8,
			height : 500
		}).then(
				function(group) {
					kendo.drawing.pdf.saveAs(group,
							"Freez_Unfreez_Revenue_State_User_Form.pdf")
				});

		$("#title").hide();
		$("#sign").hide();
		$("#confirm").hide();
		$("#l1").show();
		$("#l2").show();
		$("#l3").show();
		;
		
	}

	function validate1(stateAction) {
		
		if(stateAction=='SU'){
			$("#status").val('U');
			submitFormFn('saveStateFreezebyOnlyAtState.htm');
		}else{
			var file = $('#uploadfile').prop('files');

			if (file.length > 0) {
				var ext = $('#uploadfile').val().split('.').pop().toLowerCase();
				if ($.inArray(ext, [ 'gif', 'png', 'jpg', 'jpeg', 'pdf', 'pjpeg' ]) == -1) {
					$("#fileUploadErr")
							.html(
									" Kindly Attach Approved Doc of gif,jpg,pdf,png,jpeg,pjpeg type ");
					return false;
				}

				$('#fileUploadErr').html(" ");
				action=stateAction
				 if(action=='SF'){
					$('#otpModule').modal('show');
					$("#status").val('F');
				}
				
			}

			else {
				$("#fileUploadErr").html(" Kindly upload file");
				return false;
			}
		}

		
	}

	function printDiv() {
		$("#l1").hide();
		$("#l2").hide();
		$("#l3").hide();
		$("#title").show();
		$("#sign").show();
		$("#confirm").show();
		
		

		var printContents = document.getElementById("printableArea").innerHTML;
		var originalContents = document.body.innerHTML;

		document.body.innerHTML = printContents;

		window.print();

		document.body.innerHTML = originalContents;

		$("#title").hide();
		$("#sign").hide();
		$("#confirm").hide();

		$("#l1").show();
		$("#l2").show();
		$("#l3").show();
		
		

	}

	
	
</script>
</head>
<body>
<section class="content">
	<div class="row">
		<section class="col-lg-12 ">
		<div class="box ">
			<div class="box-header with-border">

				<h3 class="box-title">
					<c:out value="FREEZE/UNFREEZE STATUS OF ${entityName} ONLY AT STATE LEVEL"> </c:out> 
				</h3>
			</div>

			<div class="box-body">
			
				<form:form id="lgdDataConfirmationFrom" action="saveStateFreezebyOnlyAtState.htm" method="POST" commandName="lgdDataConfirmation"
				 class="form-horizontal" enctype="multipart/form-data" >
				<form:hidden path="status"/>
                 
        
                  <input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="saveStateFreezebyOnlyAtState.htm"/>" />

					 <spring:hasBindErrors name="lgdDataConfirmation">
						<div
							style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;"
							align="center">
							<label id="errorCommon"> <c:forEach var="error"
									items="${errors.allErrors}">
									<spring:message message="${error}" />
									<br />
								</c:forEach>
							</label>
						</div>
					</spring:hasBindErrors> 
					<br /><br /><br />
					<div id="printableArea" class="prints">
						<div id="title">
							<div class="row">
								<div class="form-group" style="align: right;">

									<div class="col-sm-4"></div>
									<div class="col-sm-8">
										<div>
											<h2>
												<c:out value="Local Government Directory"></c:out>
											</h2>
										</div>

									</div>

								</div>
								<hr>
							</div>
						</div>

						<div class="row">
							<div class="form-group" style="align: right;">
								<div class="col-sm-5"></div>
								<div class="col-sm-7" id="confirm">
									<h3>
										<c:out value="Confirmation Page"></c:out>
									</h3>
								</div>

							</div>
						</div>
						<br />

						<c:choose>
						
						<c:when test="${fn:contains(userType, 'U')}">
							<table id="example1"
									class="table table-bordered table-striped dataTable no-footer"
									width="100%" cellspacing="0">

									<thead>
										<tr>
											<th colspan="8">
											<div class="lgd-table">
											<div class="specified">
											
											<div class="reddish" id="l1">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.DistrictFreezedByDistrictUser"
																	text="District Freezed by the District User"
																	htmlEscape="true"></spring:message></b>
														</div>
                                                 <div class="bluish " id="l3">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
														</div>
													</div>
												</div>

											</th>
										</tr>
									</thead>
									<thead>
										<tr>

											<th><spring:message code="Label.SNO"	htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message></th>
											<th><spring:message code="label.no.of.ulbs" htmlEscape="true"></spring:message></th>
											<th><spring:message code="label.no.of.wards"	htmlEscape="true"></spring:message></th>
										</tr>

									</thead>
									<tbody>
										<c:forEach var="obj" varStatus="row" items="${lgdDataConfirmation.districtFreezeEntityListULB}">
											<c:set var="statusColor" value="" /> 
											 <c:choose>
											<c:when test="${stateFrezzeStatus}">
											<c:set var="statusColor" value="background-color:#CEF6F5" />
											</c:when>
											<c:otherwise>
											<c:set var="statusColor" value="background-color:#fa8787" />
													
											</c:otherwise>
											</c:choose>
											
											<tr  style="${statusColor}">
												<td><c:out value="${row.index+1}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtCode}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtNameEnglish}"	escapeXml="true"></c:out>
												<td>
												<%-- <a href="#" onclick="javascript:viewEntityDetailsInPopup('${obj.districtCode}', 'freezeUnfreezeDistrictwiseULBList.htm', 'districtCode','${obj.districtNameEnglish}');">
												<c:out value="${obj.noOfUlb}" escapeXml="true"></c:out>
												   </a> --%>
												   <c:out value="${obj.noOfUlb}" escapeXml="true"></c:out>
												   <spring:bind path="lgdDataConfirmation.districtFreezeEntityListULB[${row.index}].noOfUlb" />
												</td>
												<td>
												<c:out value="${obj.noOfWards}" escapeXml="true"></c:out>
												<spring:bind path="lgdDataConfirmation.districtFreezeEntityListULB[${row.index}].noOfWards" />
												</td>


											</tr>
										</c:forEach>
									</tbody>

								</table>
						</c:when>
						<c:when test="${fn:contains(userType, 'R')}">

								<table id="example"
									class="table table-bordered table-striped dataTable no-footer"
									width="100%" cellspacing="0">

									<thead>
										<tr>
											<th colspan="8">
											<div class="lgd-table">
											<div class="specified">
											
											<div class="reddish" id="l2">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.DistrictUnfreezeByStateUser"
																	htmlEscape="true"></spring:message></b>
														</div>
                                                 <div class="bluish" id="l3">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
														</div>
													</div>
												</div>

											</th>
										</tr>
									</thead>

									<thead>
										<tr>

											<th><spring:message code="Label.SNO"
													htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTCODE"
													htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTNAMEINENGLISH"
													htmlEscape="true"></spring:message></th>
											<th><spring:message
													code="label.stateFreeze.noOfSubdistrict" htmlEscape="true"></spring:message></th>
											<th><spring:message code="label.stateFreeze.noOfVillage"
													htmlEscape="true"></spring:message></th>
										</tr>

									</thead>
									<tbody>
											<c:set var="statusColor" value="" /> 
											 <c:choose>
											<c:when test="${stateFrezzeStatus}">
											<c:set var="statusColor" value="background-color:#CEF6F5" />
											</c:when>
											<c:otherwise>
											<c:set var="statusColor" value="background-color:#fa8787" />
													
											</c:otherwise>
											</c:choose>
										<c:forEach var="obj" varStatus="row"
											items="${districtFreezeEntityList}">
											<tr style="${statusColor}">
												<td><c:out value="${row.index+1}" escapeXml="true"></c:out>
												</td>
												<td><c:out value="${obj.dlc}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtNameEnglish}" escapeXml="true"></c:out> </td>
												<td><c:out value="${obj.nooftlc}" escapeXml="true"></c:out>
													<spring:bind path="lgdDataConfirmation.districtFreezeEntityList[${row.index}].nooftlc" />
												</td>
												<td><c:out value="${obj.noofvlc}" escapeXml="true"></c:out>
												</td>
											</tr>
										</c:forEach>
									</tbody>

								</table>
							</c:when>
							
							<c:when test="${fn:contains(userType, 'P')}">
							<table id="example1"
									class="table table-bordered table-striped dataTable no-footer"
									width="100%" cellspacing="0">

									<thead>
										<tr>
											<th colspan="8">
											<div class="lgd-table">
											<div class="specified">
											
											<div class="reddish" id="l1">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.DistrictFreezedByDistrictUser"
																	text="District Freezed by the District User"
																	htmlEscape="true"></spring:message></b>
														</div>
                                                 <div class="bluish " id="l3">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
														</div>
													</div>
												</div>

											</th>
										</tr>
									</thead>
									<thead>
										<tr>

											<th><spring:message code="Label.SNO"	htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message></th>
										  <c:choose>
										     <c:when test="${fn:contains(stateSetupType, 'DIV')}">
										     <th><label for="noofdp" class="control-label"><spring:message
																	code="Label.NOOFDP" text="No. of DPs" /></label></th>
											<th><label for="noofbp" class="control-label"><spring:message
																	code="Label.NOOFBP" text="No. of BPs" /></label></th>
											<th><label for="noofgp" class="control-label"><spring:message
																	code="Label.NOOFGP" text="No. of GPs" /></label></th>
											
											</c:when>
											<c:when test="${fn:contains(stateSetupType, 'DV')}">
											<th><label for="noofdp" class="control-label"><spring:message
																	code="Label.NOOFDP" text="No. of DPs" /></label></th>
											<th><label for="noofgp" class="control-label"><spring:message
																	code="Label.NOOFGP" text="No. of GPs" /></label></th>
											</c:when>
											<c:when test="${fn:contains(stateSetupType, 'IV')}">
											<th><label for="noofbp" class="control-label"><spring:message
																	code="Label.NOOFBP" text="No. of BPs" /></label></th>
											<th><label for="noofgp" class="control-label"><spring:message
																	code="Label.NOOGP" text="No. of GPs" /></label></th>
											</c:when>
											</c:choose>
										</tr>

									</thead>
									<tbody>
										<c:forEach var="obj" varStatus="row" items="${lgdDataConfirmation.districtFreezeEntityListULB}">
											<c:set var="statusColor" value="" /> 
											 <c:choose>
											<c:when test="${stateFrezzeStatus}">
											<c:set var="statusColor" value="background-color:#CEF6F5" />
											</c:when>
											<c:otherwise>
											<c:set var="statusColor" value="background-color:#fa8787" />
													
											</c:otherwise>
											</c:choose>
											
											<tr  style="${statusColor}">
												<td><c:out value="${row.index+1}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtCode}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtNameEnglish}"	escapeXml="true"></c:out>
												<c:choose>
										          <c:when test="${fn:contains(stateSetupType, 'DIV')}">
										          <td><c:out value="${obj.noofDps}"
																	escapeXml="true" /></td>
												<td><c:out value="${obj.noOfBps}"
																	escapeXml="true" /></td>
															<td><c:out value="${obj.noOfGps}"
																	escapeXml="true" /></td>
														
                                                 </c:when>
                                                  <c:when test="${fn:contains(stateSetupType, 'DV')}">
                                                  <td><c:out value="${obj.noofDps}"
																	escapeXml="true" /></td>
                                                   <td><c:out value="${obj.noOfGps}"
																	escapeXml="true" /></td>
                                                  </c:when>
                                                  
                                                   <c:when test="${fn:contains(stateSetupType, 'IV')}">
                                                   <td><c:out value="${obj.noOfBps}"
																	escapeXml="true" /></td>
                                                    <td><c:out value="${obj.noOfGps}"
																	escapeXml="true" /></td>
                                                   </c:when>
                                                 </c:choose>
											</tr>
										</c:forEach>
									</tbody>

								</table>
						</c:when>
						
						<c:when test="${fn:contains(userType, 'T')}">
							<table id="example1"
									class="table table-bordered table-striped dataTable no-footer"
									width="100%" cellspacing="0">

									<thead>
										<tr>
											<th colspan="8">
											<div class="lgd-table">
											<div class="specified">
											
											<div class="reddish" id="l1">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.DistrictFreezedByDistrictUser"
																	text="District Freezed by the District User"
																	htmlEscape="true"></spring:message></b>
														</div>
                                                 <div class="bluish " id="l3">
															<div></div>
															<b class="boldCharacter"><spring:message
																	code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
														</div>
													</div>
												</div>

											</th>
										</tr>
									</thead>
									<thead>
										<tr>

											<th><spring:message code="Label.SNO"	htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></th>
											<th><spring:message code="Label.DISTRICTNAMEINENGLISH" htmlEscape="true"></spring:message></th>
											<th><spring:message code="label.no.of.tlbs" htmlEscape="true"></spring:message></th>
											
										</tr>

									</thead>
									<tbody>
										<c:forEach var="obj" varStatus="row" items="${lgdDataConfirmation.districtFreezeEntityListULB}">
											<c:set var="statusColor" value="" /> 
											 <c:choose>
											<c:when test="${stateFrezzeStatus}">
											<c:set var="statusColor" value="background-color:#CEF6F5" />
											</c:when>
											<c:otherwise>
											<c:set var="statusColor" value="background-color:#fa8787" />
													
											</c:otherwise>
											</c:choose>
											
											<tr  style="${statusColor}">
												<td><c:out value="${row.index+1}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtCode}" escapeXml="true"></c:out></td>
												<td><c:out value="${obj.districtNameEnglish}"	escapeXml="true"></c:out>
												<td>
												<c:out value="${obj.noOfUlb}" escapeXml="true"></c:out>
												<spring:bind path="lgdDataConfirmation.districtFreezeEntityListULB[${row.index}].noOfUlb" />
												</td>
											


											</tr>
										</c:forEach>
									</tbody>

								</table>
						</c:when>
						</c:choose>

						<br />
						<div class="row">
							<div class="form-group">
								<div class="col-sm-8"></div>
								<div class="col-sm-4" id="sign">

									<div>
										<label class="control-label"><c:out value="Signature"
												escapeXml="true"></c:out></label>
									</div>

									<p>
									<hr noshade width="30%" align="left" color="2px">
								</div>
							</div>

						</div>


						<div class="col-sm-12"></div>

					</div>
	                   <div class="form-group">
						<label class="col-sm-6 control-label"><c:out
								value="Step.1: Before Confirming the data updation, Download the Summary Sheet "
								escapeXml="true"></c:out></label>
						<div class="col-sm-5">
							<button type="button" class="btn btn-info" onclick="printDiv()"
								title=" Print the Summary Sheet" <c:if test="${stateFrezzeStatus}">Disabled='true'</c:if>  >Print</button>
							<button id="print" type="button" class="btn btn-info"
								title=" Download the Summary Sheet" onclick="ExportPdf()" <c:if test="${stateFrezzeStatus}">Disabled='true'</c:if> >Download</button>

						</div>
					</div>
			
			<br />

			<div class="form-group">
				<label class="col-sm-6 control-label"><c:out
						value=" Step.2: Upload Signed copy of Summary Sheet "
						escapeXml="true"></c:out></label>
				<div class="col-sm-5 ">
					<div style="width: 55%">

				<c:choose>
				<c:when test="${stateFrezzeStatus}">
				<form:input path="uploadFiles" type="file" title=" Upload Signed copy of Summary Sheet " disabled="true"  ></form:input>
				</c:when>
				<c:otherwise>
				<form:input id="uploadfile" path="uploadFiles" type="file" title=" Upload Signed copy of Summary Sheet " required="true"    ></form:input>
				</c:otherwise>
				</c:choose>
						
					</div>
					<div id="fileUploadErr" class="mandatory"></div>

				</div>
			</div>
			<br /> <br />


			<div class="box-footer">
				<div class="col-sm-offset-2 col-sm-10">



					<div class="pull-right">
						
						 <c:choose>
							<c:when test="${stateFrezzeStatus}">
								<button name="savestateUnFreeze" id="savestateUnFreeze"
									type="button" onclick="validate1('SU')" 
									"
									class="btn btn-success">State Unfreez</button>
							</c:when>
							<c:otherwise>

								<button name="savestateFreeze" id="savestateFreeze"
									type="button" onclick="validate1('SF')"
									class="btn btn-success"
									<%-- <c:if test="${stateFreezeBtn eq false}"> Disabled='true' </c:if> --%> > 
									State Freez</button>

							
							</c:otherwise>
						</c:choose> 

						<button class="btn btn-danger" id="btnActionClose" type="button"
							onclick="callActionUrl('home.htm')">
							<spring:message htmlEscape="true" code="Button.CLOSE" />
						</button>
					</div>
				</div>
			</div>


			<div class="modal fade" id="otpModule" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<b><c:out
									value="State wise Freeze/Unfreeze status of ${entityName}" /></b>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label class="col-sm-4 lgd_updation_message" for="sel1">
									<c:out
										value="To freeze State level you need to genrate OTP"
										escapeXml="true"></c:out>
									</p>
								</label>
								<div class="col
									-sm-4">
									<button name="sendOTP" type="button" class="btn btn-success"
										onclick="genrateOTP('${lgdDataConfirmation.userId}');"
										<c:if test="${isConfirmDataSame}">Disabled='true'</c:if>>
										Genrate OTP</button>
									<div id="msgsendOTP" style="color: green;"></div>
								</div>


							</div>
							<br />
							<div class="form-group">
								<label class="col-sm-4 control-label">Enter OTP <span
									class="mandatory">*</span></label>
								<div class="col-sm-4">
									<form:input path="userOTP" pattern="^[0-9\\s]+$"
										class="form-control" id="userOTP" maxlength="6"
										required="true" placeholder="Enter OTP"
										onkeypress="validateNumber(event,'userOTP')"
										autocomplete="off" />

									<div id="userOTPErr" style="color: red; font-size: x-small;"></div>

								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="btnConfirm">Confirm</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
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

   <%@include file="lgdDataConfirmationStateJs.jsp"%>

</body>
</html>