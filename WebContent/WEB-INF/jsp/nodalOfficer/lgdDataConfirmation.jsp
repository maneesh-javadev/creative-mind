<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="../common/taglib_includes.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=contextpthval%>/css/stateFreeze.css"></link>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script src="<%=contextpthval%>/resource/common-resource/jszip.min.js"></script>
<script	src="<%=contextpthval%>/resource/common-resource/kendo.all.min.js"></script>
<style>
.lgd_updation_message {
	margin-left: 74px;
}
</style>
<script>
	$(document).ready(function() {

		$("#title").hide();
		$("#sign").hide();
		$("#confirm").hide();
		$("#ulbexceptioncase").hide();
	});

	jQuery.validator.setDefaults({
		debug : true,
		success : "valid"
	});

	function validateNumber(e, id) {
		$("#err" + id).html("");
		var n = e.charCode;
		if ((n >= 48 && n <= 57) || (n == 0)) {

		} else {
			e.preventDefault();
			$("#" + id + "_type_error").fadeIn(1000, function() {
				$("#" + id + "_type_error").fadeOut(1000);
			});
		}
	}

	function genrateOTP(userId) {
		lgdDwrStateService
				.sendOTPForLGDDataConfirmation(
						userId,
						{
							callback : function(result) {

								if (isParseJson(result)) {
									$("#msgsendOTP")
											.html(
													"OTP has been sent successfully to your nodal officer's mobile number & email id");
									$("#userOTP").prop("disabled", false);

								} else {
									$("#msgsendOTP")
											.html(
													"Some problem send OTP please contact");
								}
							},
							errorHandler : function(errorString, exception) {
								alert(errorString + " dwr");
								alert(exception);
							},
							async : true
						});
	};

	function validate() {
		if (validate1()) {
			callActionUrl("saveLGDDataConfirmation.htm")
		}
		$("#userOTPErr").html("");
		var userOtp = $("#userOTP").val();
		if (userOtp.length < 6) {
			$("#userOTPErr").html("OTP is must be 6 length");

		} else {
			callActionUrl("saveLGDDataConfirmation.htm")

		}

	}

	callActionUrl = function(url) {
		
		$('form[id=lgdDataConfirmationFrom]').attr('action',
				url + '?<csrf:token uri="' + url + '"/>');
		$('form[id=lgdDataConfirmationFrom]').attr('method', 'post');
		$('form[id=lgdDataConfirmationFrom]').submit();

	};

	

	function ExportPdf() {

		$("#title").show();
		$("#sign").show();
		$("#confirm").show();
		$("#ulbexceptioncase").hide();
		$("#ulbexceptioncase").show();

		kendo.drawing.drawDOM("#printableArea", {
			orientation : 'landscape',
			// paperSize: "A4",
			margin : {
				top : "1cm",
				bottom : "1cm"
			},
			scale : 0.8,
			height : 700
		}).then(function(group) {
			kendo.drawing.pdf.saveAs(group, "Freez_Unfreez_Revenue_Form.pdf")
		});

		$("#title").hide();
		$("#sign").hide();
		$("#confirm").hide();
		$("#ulbexceptioncase").hide();
	}

	function printDiv() {
		$("#title").show();
		$("#sign").show();
		$("#confirm").show();
		$("#ulbexceptioncase").show();

		var printContents = document.getElementById("printableArea").innerHTML;
		var originalContents = document.body.innerHTML;

		document.body.innerHTML = printContents;

		window.print();

		document.body.innerHTML = originalContents;
		$("#title").hide();
		$("#sign").hide();
		$("#confirm").hide();
		$("#ulbexceptioncase").hide();
	}

	 function validate1() {

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

		}

		else {
			$("#fileUploadErr").html(" Kindly upload file");
			return false;
		}
	}  
	
	/* function validate1() {

		var file = $('#uploadfile').prop('files');
		$(".checkSize").css("border-color","#F0F0F0");
		var file_size = $('#uploadfile')[0].files[0].size;

		if (file.length > 0) {
			var ext = $('#uploadfile').val().split('.').pop().toLowerCase();
			if ($.inArray(ext, [ 'gif', 'png', 'jpg', 'jpeg', 'pdf', 'pjpeg' ]) == -1) {
				$("#fileUploadErr")
						.html(
								" Kindly Attach Approved Doc of gif,jpg,pdf,png,jpeg,pjpeg type ");
				return false;
			}
			else if(file_size>2097152) {
				$("#fileUploadErr").html("File size is greater than 2MB");
				$(".checkSize").css("border-color","#FF0000");
				return false;
			} 
			

			$('#fileUploadErr').html(" ");

		}

		else {
			$("#fileUploadErr").html(" Kindly upload file");
			return false;
		}*/		
	
	
</script>
<style>
.download {
	position: relative;
	overflow: hidden;
}

.inputt {
	position: absolute;
	font-size: 50px;
	opacity: 0;
	right: 0;
	top: 0;
}

#sign {
	width: 100%;
	border: 0px;
	border-bottom: 1px solid #000;
}
</style>
</head>
<body>
<body>
	<c:choose>
		<c:when test="${stateStatus eq 'F'.charAt(0) }">
			<c:set var="freezemsg" value="Freeze by State User" />
			<c:set var="statusColor" value="background-color:#CEF6F5" />
			<c:set var="statusName" value="Freeze" />
			<c:set var="stateStatusName" value="Freeze" />
		</c:when>
		<c:when test="${stateStatus eq 'U'.charAt(0)}">
			<c:set var="stateStatusName" value="Unfreeze" />
			<c:choose>
				<c:when test="${status eq 'F'.charAt(0) }">

					<c:set var="statusColor" value="background-color:#fa8787" />
					<c:set var="statusName" value="Freeze" />
					<c:set var="freezemsg" value="Data freezed by District user" />

				</c:when>
				<c:when test="${status eq 'U'.charAt(0)}">

					<c:set var="statusName" value="Unfreeze" />
					<%-- 	<c:set var="statusColor" value="background-color:#59ff90" />--%>
				</c:when>
			</c:choose>

		</c:when>
	</c:choose>


	<section class="content">
	<div class="row">
		<section class="col-lg-12 ">
		<div class="box ">
			<div class="box-header with-border" style="${statusColor}">

				<h3 class="box-title">
					<c:out value="Freeze /Unfreeze ${entityName} "></c:out>
				</h3>
			</div>

			<div class="box-body">


				<form:form id="lgdDataConfirmationFrom"
					action="saveLGDDataConfirmation.htm" method="POST"
					commandName="lgdDataConfirmation" class="form-horizontal"
					enctype="multipart/form-data"  onsubmit=" return validate1()" >
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="saveLGDDataConfirmation.htm"/>" />
					<div class="box-body">

						<div class="form-group">

							<div class="lgd-table ">
								<div class="specified">
									<div class="row">

										<div class="reddish col-sm-4" style="width: 30%;">
											<div></div>
											<b class="boldCharacter"><spring:message
													code="label.DistrictFreezedByDistrictUser"
													text="District Freezed by the District User"
													htmlEscape="true"></spring:message></b>
										</div>

										<div class="greenish col-sm-4" style="width: 30%;">
											<div></div>
											<b class="boldCharacter"><spring:message
													code="label.DistrictUnfreezeByStateUser" htmlEscape="true"></spring:message></b>
										</div>

										<div class="bluish col-sm-4">
											<div></div>
											<b class="boldCharacter"><spring:message
													code="label.StateFreezedByStateUser" htmlEscape="true"></spring:message></b>
										</div>

									</div>
								</div>
							</div>


						</div>


						<!--   Begin of print div -->

						<div id="printableArea" class="prints">


							<br />
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
								<c:when test="${fn:contains(userTypeStr, 'R')}">
									<table class="table table-bordered">
										<thead>
											<tr>

												<th><label for="districtCode" class=" control-label"><spring:message
															code="Label.DISTRICTCODE" /></label></th>
												<th><label for="districtNameInEn" class="control-label"><spring:message
															code="Label.DISTRICTNAME" /></label></th>
												<th><label for="districtNameInEn"
													class=" control-label"><spring:message
															code="Label.DISTRICTSTATUS" /></label></th>
												<th><label for="districtNameInEn" class="control-label">
														<spring:message code="Label.STATESTATUS"
															text="State Status" />
												</label></th>
											</tr>

										</thead>
										<tbody>
											<tr>
												<td><c:out value="${dlc}" escapeXml="true" /></td>
												<td><c:out value="${districtNameEnglish}"
														escapeXml="true" /></td>
												<td><c:out value="${statusName}" escapeXml="true" /></td>
												<td><c:out value="${stateStatusName}" escapeXml="true" />
												</td>
											</tr>

										</tbody>
									</table>
								</c:when>
							</c:choose>

							<br /> <br />

							<!--  Add on 21 Agust regarding  Urban local body -->

							<c:choose>
								<c:when test="${fn:contains(userTypeStr, 'R')}">
									<table class="table table-bordered">
										<thead>
											<tr>

												<th><label for="subdistrictCode" class=" control-label"
													text="Sub District Code"><spring:message
															code="Label.SUBDISTRICTCODE" /></label></th>
												<th><label for="subdistrictNameInEn"
													class="control-label"><spring:message
															code="Label.SUBDISTRICTNAMEGLISH"
															text="Sub District Name (In English)" /></label></th>
												<th><label for="subdistrictNameInL"
													class="control-label"><spring:message
															code="Label.SUBDISTRICTNAMELOCAL"
															text="Sub District Name (In Local Language)" /></label></th>

												<th><label for="subdistrictcensus2011code"
													class="control-label"><spring:message
															code="Label.CENSUSCODE2011"
															text="Census 2011 Code" /></label></th>
												<th><label for="subdistrictnoofvillage"
													class="control-label"><spring:message
															code="Label.SUBDISTRICTNOOFVILLAGE"
															text=" No. Of Villages" /></label></th>

											</tr>

										</thead>
										<tbody>
											<c:forEach items="${subdstrictList}" varStatus="count"
												var="subDistList">
												<tr>

													<td><c:out value="${subDistList.entityCode}"
															escapeXml="true" /></td>

													<td><c:out value="${subDistList.entityNameEnglish}"
															escapeXml="true" /></td>

													<td><c:out value="${subDistList.census2001Code}"
															escapeXml="true" /></td>

													<td><c:out value="${subDistList.census2011Code}"
															escapeXml="true" /></td>


													<td><c:out value="${subDistList.noOfVlc}"
															escapeXml="true" /></td>

												</tr>


											</c:forEach>
										</tbody>
									</table>
								</c:when>


								<c:when test="${fn:contains(userTypeStr, 'U')}">

									<table class="table table-bordered">
										<thead>
											<tr>

												<th><label for="districtCode" class=" control-label"><spring:message
															code="Label.DISTRICTCODE" /></label></th>
												<th><label for="districtNameInEn" class="control-label"><spring:message
															code="Label.DISTRICTNAME" /></label></th>
												<th><label for="districtNameInEn"
													class=" control-label"><spring:message
															code="Label.DISTRICTSTATUS" /></label></th>
												<th><label for="districtNameInEn" class="control-label">
														<spring:message code="Label.STATESTATUS"
															text="State Status" />
												</label></th>
											</tr>

										</thead>
										<tbody>
											<c:forEach var="obj" items="${districtListStatus}">
												<tr>
													<td><c:out value="${obj.districtCode}"
															escapeXml="true" /></td>
													<td><c:out value="${obj.districtNameEnglish}"
															escapeXml="true" /></td>
													<td><c:choose>
															<c:when test="${obj.districtStatus eq 'F'}">
																<c:out value="Freeze" escapeXml="true" />
															</c:when>
															<c:otherwise>
																<c:out value="Unfreeze" escapeXml="true" />
															</c:otherwise>
														</c:choose></td>
													<td><c:choose>
															<c:when test="${obj.stateStatus eq 'F'}">
																<c:out value="Freeze" escapeXml="true" />
															</c:when>
															<c:otherwise>
																<c:out value="Unfreeze" escapeXml="true" />
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<table class="table table-bordered">
										<thead>
											<tr>

												<th><label for="ulbcode" class=" control-label"
													text="Ulb Code"><spring:message
															code="Label.ULBCODE" /></label></th>
												<th><label for="ulbNameInEn" class="control-label"><spring:message
															code="Label.ULBNAMEE" text="ULB Name (In English)" /></label></th>
												<th><label for="ulbtypecode" class="control-label"><spring:message
															code="Label.ULBTYPECODE" text="ULB Type Code" /></label></th>
												<th><label for="ulbTypeNameInEn" class="control-label"><spring:message
															code="Label.ULBTYPENAME" text="ULB Type Name" /></label></th>

												<th><label for="ulbcensus2011code"
													class="control-label"><spring:message
															code="Label.ULBCENSUS2011CODE" text="Census 2011 Code" /></label></th>
												<th><label for="ulbnoofwards" class="control-label"><spring:message
															code="Label.ULBNOOFWARDS" text=" No.of Wards" /></label></th>

											</tr>

										</thead>
										<tbody>
											<c:forEach items="${ulbList}" varStatus="count"
												var="listofulb">
												<tr>

													<td><c:out value="${listofulb.entityCode}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.entityName}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.ulbTypeCode}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.ulbTypeName}"
															escapeXml="true" /></td>


													<td><c:out value="${listofulb.census2011Code}"
															escapeXml="true" /></td>
													<td><c:out value="${listofulb.noOfWards}"
															escapeXml="true" /></td>


												</tr>


											</c:forEach>
										</tbody>
									</table>



								</c:when>


								<c:when test="${fn:contains(userTypeStr, 'P')}">

									<c:choose>
										<c:when test="${fn:contains(stateSetupType, 'DIV')}">

											<table class="table table-bordered">
												<thead>
													<tr>

														<th><label for="districtcode" class=" control-label"
															text="District Panchayat Code"><spring:message
																	code="Label.DISTRICTPANCHAYATCODE" /></label></th>
														<th><label for="districNameInEn"
															class="control-label"><spring:message
																	code="Label.DISTRICTPANCHAYATNAMEE"
																	text="District Panchayat Name (In English)" /></label></th>

														<th><label for="noofbp" class="control-label"><spring:message
																	code="Label.NOOFBP" text="No. of BPs" /></label></th>
														<th><label for="noofgp" class="control-label"><spring:message
																	code="Label.NOOFGP" text="No. of GPs" /></label></th>

													</tr>

												</thead>
												<tbody>
													<c:forEach items="${priList}" varStatus="count"
														var="listofpri">
														<tr>

															<td><c:out value="${listofpri.entityCode}"
																	escapeXml="true" /></td>

															<td><c:out value="${listofpri.entityNameEnglish}"
																escapeXml="true" /></td>
                          

															<td><c:out value="${listofpri.noOfTlc}"
																	escapeXml="true" /></td>
															<td><c:out value="${listofpri.noOfVlc}"
																	escapeXml="true" /></td>


														</tr>


													</c:forEach>
												</tbody>
											</table>
										</c:when>


										<c:when test="${fn:contains(stateSetupType, 'DV')}">

											<table class="table table-bordered">
												<thead>
													<tr>

														<th><label for="districtpanchayatcode"
															class=" control-label" text="District  Panchayat Code"><spring:message
																	code="Label.DISTPANCHAYATCODE" /></label></th>
														<th><label for="ulbNameInEn" class="control-label"><spring:message
																	code="Label.DISTRICTPANCHAYATNAMEE"
																	text="District Panchayat Name (In English)" /></label></th>
														<th><label for="noofgp" class="control-label"><spring:message
																	code="Label.NOOFGP" text="No. of GPs" /></label></th>

													</tr>

												</thead>
												<tbody>
													<c:forEach items="${priList}" varStatus="count"
														var="listofpri">
														<tr>

															<td><c:out value="${listofpri.entityCode}"
																	escapeXml="true" /></td>

															<td><c:out value="${listofpri.entityNameEnglish}"
																	escapeXml="true" /></td>

															<td><c:out value="${listofpri.noOfVlc}"
																	escapeXml="true" /></td>

														</tr>


													</c:forEach>
												</tbody>
											</table>
										</c:when>

										<c:when test="${fn:contains(stateSetupType, 'IV')}">

											<table class="table table-bordered">
												<thead>
													<tr>

														<th><label for="blockpanchayatcode"
															class=" control-label" text="Block  Panchayat Code"><spring:message
																	code="Label.BLOCKPANCHAYATCODE" /></label></th>
														<th><label for="blockPanchayatNameInEn"
															class="control-label"><spring:message
																	code="Label.BLOCKPANCHAYTNAMEE"
																	text="Block Panchayat Name (In English)" /></label></th>
														<th><label for="noofgp" class="control-label"><spring:message
																	code="Label.NOOFGP" text="No. of GPs" /></label></th>

													</tr>

												</thead>
												<tbody>
													<c:forEach items="${priList}" varStatus="count"
														var="listofpri">
														<tr>

															<td><c:out value="${listofpri.entityCode}"
																	escapeXml="true" /></td>

															<td><c:out value="${listofpri.entityNameEnglish}"
																	escapeXml="true" /></td>

															<td><c:out value="${listofpri.noOfVlc}"
																	escapeXml="true" /></td>

														</tr>


													</c:forEach>
												</tbody>
											</table>
										</c:when>

									</c:choose>

								</c:when>
								<c:when test="${fn:contains(userTypeStr, 'T')}">

									<table class="table table-bordered">
										<thead>
											<tr>

												<th><label for="districtCode" class=" control-label"><spring:message
															code="Label.DISTRICTCODE" /></label></th>
												<th><label for="districtNameInEn" class="control-label"><spring:message
															code="Label.DISTRICTNAME" /></label></th>
												<th><label for="districtNameInEn"
													class=" control-label"><spring:message
															code="Label.DISTRICTSTATUS" /></label></th>
												<th><label for="districtNameInEn" class="control-label">
														<spring:message code="Label.STATESTATUS"
															text="State Status" />
												</label></th>
											</tr>

										</thead>
										<tbody>
											<c:forEach var="obj" items="${districtListStatusTlb}">
												<tr>
													<td><c:out value="${obj.districtCode}"
															escapeXml="true" /></td>
													<td><c:out value="${obj.districtNameEnglish}"
															escapeXml="true" /></td>
													<td><c:choose>
															<c:when test="${obj.districtStatus eq 'F'}">
																<c:out value="Freeze" escapeXml="true" />
															</c:when>
															<c:otherwise>
																<c:out value="Unfreeze" escapeXml="true" />
															</c:otherwise>
														</c:choose></td>
													<td><c:choose>
															<c:when test="${obj.stateStatus eq 'F'}">
																<c:out value="Freeze" escapeXml="true" />
															</c:when>
															<c:otherwise>
																<c:out value="Unfreeze" escapeXml="true" />
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<table class="table table-bordered">
										<thead>
											<tr>

												<th><label for="tlbcode" class=" control-label"
													text="Tlb Code"><spring:message
															code="Label.TLBCODE" /></label></th>
												<th><label for="ulbNameInEn" class="control-label"><spring:message
															code="Label.TLBNAMEE" text="TLB Name (In English)" /></label></th>
															<th><label for="TlbTypeNameInEn" class="control-label"><spring:message
															code="Label.TLBTYPENAME" text="TLB Type Name" /></label></th>
												
											</tr>

										</thead>
										<tbody>
											<c:forEach items="${tlbList}" varStatus="count"
												var="listofulb">
												<tr>

													<td><c:out value="${listofulb.entityCode}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.entityName}"
															escapeXml="true" /></td>
															<td><c:out value="${listofulb.ulbTypeName}"
															escapeXml="true" /></td>

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
							<br />
							<c:choose>
								<c:when test="${fn:contains(userTypeStr, 'U')}">
									<div class="form-group">
										<div class="col-sm-8">
											<div id="ulbexceptioncase">
												<p>
												<b>Note:</b>
												</p>
												<p>1.ULBs not mapped to land-region/Revenue entities</p>
												<p>2.ULBs formed after year 2011/without census 2011
													code</p>
												<p>3.ULBs with no wards</p>
												<p>4.ULBs mapped to multiple districts</p>

											</div>
										</div>

										<div class="col-sm-4"></div>

									</div>
								</c:when>
							</c:choose>
						</div>

						<!-- 	end of print div -->
						<c:choose>
							<c:when test="${status eq 'F'.charAt(0)}">
								<div class="form-group">
									<label class="col-sm-6 control-label"><c:out
											value="Step.1: Before Confirming the data updation, Download the Summary Sheet "
											escapeXml="true"></c:out></label>
									<div class="col-sm-5">
										<button type="button" class="btn btn-info" disabled="true"
											title=" Print the Summary Sheet">Print</button>
										<button id="print" type="button" class="btn btn-info"
											title=" Download the Summary Sheet" disabled="true">Download</button>

									</div>
								</div>

								<br />

								<div class="form-group">
									<label class="col-sm-6 control-label"><c:out
											value=" Step.2: Upload Signed copy of Summary Sheet "
											escapeXml="true"></c:out></label>
									<div class="col-sm-5 ">
										<div style="width: 55%">


											<form:input id="uploadfile" path="uploadFiles" type="file"
												title=" Upload Signed copy of Summary Sheet "
												disabled="true" required="true" />
										</div>
										<div id="fileUploadErr" class="mandatory"></div>

									</div>
								</div>

								<br />
								<br />


								<div class="form-group">
									<label class="col-sm-6 control-label"><c:out
											value=" Step.3: The data available/entered in LGD is up-to-date and correct. "
											escapeXml="true"></c:out></label>
									<div class="col-sm-5">
										<button name="sendOTP" type="button" class="btn btn-success"
											title=" Before generating OTP Please Upload Signed copy of Summary Sheet "
											disabled="true"
											<c:if test="${status eq 'F'.charAt(0)}"></c:if>>
											Generate OTP</button>

										<!--demo button add by Sushma Singh-->
										<!-- 									<button name="enableotp" type="button" id="enableotp" class="btn btn-success" disabled="disabled" title=" Upload file"> Generate Otp</button>
 -->


										<div id="msgsendOTP" style="color: green;"></div>
									</div>
								</div>

							</c:when>
							<c:otherwise>
								<div class="form-group">
									<label class="col-sm-6 control-label"><c:out
											value="Step.1: Before Confirming the data updation, Download the Summary Sheet "
											escapeXml="true"></c:out></label>
									<div class="col-sm-5">
										<button type="button" class="btn btn-info"
											onclick="printDiv()" title=" Print the Summary Sheet">Print</button>
										<button id="print" type="button" class="btn btn-info"
											title=" Download the Summary Sheet" onclick="ExportPdf()">Download</button>

									</div>
								</div>

								<br />

								<div class="form-group">
									<label class="col-sm-6 control-label"><c:out
											value=" Step.2: Upload Signed copy of Summary Sheet "
											escapeXml="true"></c:out></label>
									<div class="col-sm-5 ">
										<div style="width: 55%">


											<form:input id="uploadfile" path="uploadFiles" type="file"
												title=" Upload Signed copy of Summary Sheet "
												onchange="validate1()" required="true" class="checkSize" />
										</div>
										<div id="fileUploadErr" class="mandatory"></div>

									</div>
								</div>

								<br />
								<br />


								<div class="form-group">
									<label class="col-sm-6 control-label"><c:out
											value=" Step.3: The data available/entered in LGD is up-to-date and correct. "
											escapeXml="true"></c:out></label>
									<div class="col-sm-5">
										<button name="sendOTP" type="button" class="btn btn-success"
											title=" Before generating OTP Please Upload Signed copy of Summary Sheet "
											onclick="genrateOTP('${lgdDataConfirmation.userId}');"
											<c:if test="${status eq 'F'.charAt(0)}"></c:if>>
											Generate OTP</button>

										<!--demo button add by Sushma Singh-->
										<!-- 									<button name="enableotp" type="button" id="enableotp" class="btn btn-success" disabled="disabled" title=" Upload file"> Generate Otp</button>
 -->


										<div id="msgsendOTP" style="color: green;"></div>
									</div>
								</div>


							</c:otherwise>
						</c:choose>




						<div class="form-group">
							<label class="col-sm-6 control-label"><c:out
									value="Enter OTP" escapeXml="true"></c:out><span
								class="mandatory">*</span></label>
							<div class="col-sm-5">
								<form:input path="userOTP" pattern="^[0-9\\s]+$"
									class="form-control" id="userOTP" maxlength="6" required="true"
									disabled="true" placeholder="Enter OTP"
									onkeypress="validateNumber(event,'userOTP')" autocomplete="off"
									style="width:25%" />
								<form:errors htmlEscape="true" path="userOTP"
									cssClass="errormsg" />
								<div id="userOTPErr" style="color: red; font-size: x-small;"></div>

								<!-- Demo Input type add by sushma singh  -->

								<!-- 								<input id="demoinput" pattern="^[0-9\\s]+$" placeholder="Enter OTP"  style="width:25%" disabled="disabled" />
 -->
							</div>
						</div>

						<c:if test="${status eq 'F'.charAt(0)}">
							<div
								style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;"
								align="center">
								<label id="errorCommon"><c:out value="${freezemsg}" /></<label>
							</div>

						</c:if>

						<br /> <br />
						<div class="box-footer">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="pull-right">


                                 

									<button name="saveConfirmLGDData" type="button"
										class="btn btn-success" onclick="validate();"
										<c:if test="${status eq 'F'.charAt(0)}">Disabled='true'</c:if>>
										Freeze</button>

									<button class="btn btn-danger" id="btnActionClose"
										type="button" onclick="callActionUrl('home.htm')">
										<spring:message htmlEscape="true" code="Button.CLOSE" />
									</button>
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

	<script>
		$("#userOTP").val("");
	</script>
	<%--  <c:if test="${isConfirmDataSame}"><script>alert("No updatation in data");</script></c:if> --%>
	<c:if test="${ not empty draftedOrFreezedVillages}">
		<div id="divExistingFreezedVillageDeatils" class="form_stylings"
			style="display: none;">
			<div class="form_block">
				<div class="col_1">
					<h4>${freezePRI.districtNameEnglish}districtcan'tbefreeze
						because below Local Bodies are in ${headingMessage}.In order to
						Freeze this district first take action on below Local Bodies.</h4>
					<ul class="form_body">
						<li>
							<table id="tblGornmentOrderDetails" class="data_grid"
								width="100%">
								<thead>
									<tr>
										<th>S.No.</th>
										<th><spring:message code="App.LOCALGOVTBODYCODE" /></th>
										<th><spring:message code="Label.NAMEOFLOCALBODY" />
											(with its hierarchical details)</th>
										<th><spring:message code="Label.STATUS" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="varDraftedOrFreezedEntity"
										items="${draftedOrFreezedVillages}" varStatus="counter">
										<tr>
											<td><c:out value="${counter.count}" escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lblc}"
													escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lbName}"
													escapeXml="true"></c:out></td>
											<td><c:out value="${varDraftedOrFreezedEntity.lbStatus}"
													escapeXml="true"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</li>
					</ul>
				</div>
			</div>
			<br />
		</div>
	</c:if>

	</div>
	</section>
	</div>
	</section>
	<!-- Main Form Stylings ends -->
	<div class="modal fade" id="freezelbAlert" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<c:out value="Drafted or Freezed Entity Details."></c:out>
					</h4>
				</div>
				<div class="modal-body" id="customAlertbody">
					<div class="form_block">
						<div class="col_1">
							<h4>
								<c:out
									value="${freezePRI.districtNameEnglish} district can't be freeze because below Local Bodies are in ${headingMessage}.In order to Freeze this district first take action on below Local Bodies."></c:out>
							</h4>
							<ul class="form_body">
								<li>
									<table id="tblGornmentOrderDetails" class="data_grid"
										width="100%">
										<thead>
											<tr>
												<th>S.No.</th>
												<th><spring:message code="App.LOCALGOVTBODYCODE" /></th>
												<th><spring:message code="Label.NAMEOFLOCALBODY" />
													(with its hierarchical details)</th>
												<th><spring:message code="Label.STATUS" /></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="varDraftedOrFreezedEntity"
												items="${draftedOrFreezedVillages}" varStatus="counter">
												<tr>
													<td><c:out value="${counter.count}" escapeXml="true"></c:out></td>
													<td><c:out value="${varDraftedOrFreezedEntity.lblc}"
															escapeXml="true"></c:out></td>
													<td><c:out value="${varDraftedOrFreezedEntity.lbName}"
															escapeXml="true"></c:out></td>
													<td><c:out
															value="${varDraftedOrFreezedEntity.lbStatus}"
															escapeXml="true"></c:out></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- <script type='text/javascript'>
		$(window)
				.load(
						function() {
							<c:if test="${ not empty draftedOrFreezedVillages}">

							$("#freezelbAlert").modal('show');
							</c:if>
							<c:if test="${freezeDistrict.stateStatus eq 'Freeze'}">
							customAlert("PRI LocalBody has been  already  Freezed by State User. You can't UnFreezed.");
							</c:if>
						});
	</script> -->

</body>
</html>