
<link rel="stylesheet"
	href="/LGD/resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.css">
<script
	src="resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/js/date.format.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/js/date.format.min.js"></script>
<script type="text/javascript"
	src="http://momentjs.com/downloads/moment.min.js"></script>

<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrParlimentService.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrAssemblyService.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrBlockService.js'></script>

<style>
.download_option {
	padding: 15px 0 0;
}

.download_option .row {
	padding: 0 0 15px 0;
	font-size: 0;
}

.download_option .row:first-child {
	padding-bottom: 0;
}

.download_option .row .col {
	width: 11%;
	padding-right: 20px;
	display: inline-block;
	*display: inline;
	*zoom: 1;
	vertical-align: top;
}

.captcha_option {
	padding: 15px 0 0;
}

.captcha_option .row {
	padding: 0 0 15px 0;
	font-size: 0;
}

.captcha_option .row:first-child {
	padding-bottom: 0;
}

.captcha_option .row .col {
	width: 17%;
	padding-right: 20px;
	display: inline-block;
	*display: inline;
	*zoom: 1;
	vertical-align: top;
}
</style>
<script>
var stateChoice="";
var hierarchyStr;
var _PARLIAMENT_CONSTITUENCY='<%=in.nic.pes.lgd.constant.ReportConstant.PARLIAMENT_CONSTITUENCY.toString()%>';
var _ASSEMBLY_CONSTITUENCY='<%=in.nic.pes.lgd.constant.ReportConstant.ASSEMBLY_CONSTITUENCY.toString()%>';
	$(document)
			.ready(
					function() {
						$("#stateCenter").hide();
						var date = new Date();
						var today = new Date(date.getFullYear(), date
								.getMonth(), date.getDate());
						$("#bfromDate").datetimepicker({
							format : 'dd/mm/yyyy',
							startView : 'month',
							endDate : today,
							autoclose : true,
							minView : 'month',
							pickerPosition : "bottom-left",

						}).on(
								'changeDate',
								function(selected) {

									$('#toDate').val(
											(selected.date).format('d/m/Y'));
									$('#btoDate').datetimepicker(
											'setStartDate',
											(selected.date).format('d/m/Y'));
								});

						$("#btoDate").datetimepicker({
							format : 'dd/mm/yyyy',
							startView : 'month',
							endDate : today,
							autoclose : true,
							minView : 'month',
							pickerPosition : "bottom-left",

						})

						var selOption = $('#rptFileName option:selected').val();
						if (selOption == "allSubdistrictVillageBlockMapping") {
							$("#downloadType1").prop("disabled", true);
							$("#downloadType2").prop("disabled", true);
							$("#downloadType3").prop("checked", true);
							$("#downloadType5").prop("disabled", true);
						}

						$("#rptFileName")
								.change(
										function() {
											removeError(this);
											hideAllOption();
											removeAllPopulateList();
											
											var selOption = $(
													'#rptFileName option:selected')
													.val();
											if(selOption=="subdistrictVillageBlockGpsMapping")
												{
												$("#allState").show();
												  removeAllOptions('state');
												AllStateListforOption();
												}
											else if (selOption == "allVillagesofIndia") {
												$("#downloadType1").prop(
														"disabled", true);
												$("#downloadType2").prop(
														"disabled", true);
												$("#downloadType3").prop(
														"checked", true);
											}
											/* [lgd 0012459]: Facility to download Subdistrict-villageBlock mapping start*/
											else if (selOption == "allSubdistrictVillageBlockMapping") {
												$("#downloadType1").prop(
														"disabled", true);
												$("#downloadType2").prop(
														"disabled", true);
												$("#downloadType3").prop(
														"checked", true);
												$("#downloadType5").prop(
														"disabled", true);
											}
											/* [lgd 0012459]: Facility to download Subdistrict-villageBlock mapping end*/
											else {
												$("#downloadType1").prop(
														"disabled", false);
												$("#downloadType2").prop(
														"disabled", false);
												$("#downloadType1").prop(
														"checked", true);
												fillStateList();
											}
											if (selOption.indexOf("@") != -1) {
												var hierarchySet = selOption
														.split("@")[1];
												hierarchyStr = hierarchySet;
												stateChoice = selOption
														.split("@")[0];
												//alert(stateChoice);
												$
														.each(
																hierarchySet
																		.split('#'),
																function(index,
																		val) {

																	$(
																			"#"
																					+ val
																					+ "LI")
																			.show();
																	var options = $("#"
																			+ val);
																	var option = $("<option/>");
																	$(option)
																			.val(
																					"-1")
																			.text(
																					"<spring:message code='Label.SEL' htmlEscape='true'/>");
																	options
																			.append(option);
																	//removeAllOptions(val);
																});

											}

										});

						$("#rptFileNameMod")
								.change(
										function() {
											removeError(this);
											hideAllOption();
											removeAllPopulateList();
											fillStateList();
											var selOption = $(
													'#rptFileNameMod option:selected')
													.val();
											if (selOption == "allVillagesofIndia") {
												$("#downloadType1").prop(
														"disabled", true);
												$("#downloadType2").prop(
														"disabled", true);
												$("#downloadType3").prop(
														"checked", true);
											} else {
												$("#downloadType1").prop(
														"disabled", false);
												$("#downloadType2").prop(
														"disabled", false);
												$("#downloadType1").prop(
														"checked", true);
											}
											if (selOption.indexOf("@") != -1) {
												var hierarchySet = selOption
														.split("@")[1];
												hierarchyStr = hierarchySet;
												stateChoice = selOption
														.split("@")[0];
												//alert(stateChoice);
												$
														.each(
																hierarchySet
																		.split('#'),
																function(index,
																		val) {

																	$(
																			"#"
																					+ val
																					+ "LI")
																			.show();
																	var options = $("#"
																			+ val);
																	var option = $("<option/>");
																	$(option)
																			.val(
																					"-1")
																			.text(
																					"<spring:message code='Label.SEL' htmlEscape='true'/>");
																	options
																			.append(option);
																	//removeAllOptions(val);
																});

											}

										});

						$("#state")
								.change(
										function() {
											removeError(this);
											// 		removeAllPopulateList();
											var stateCode = $(
													'#state option:selected')
													.val();
											//alert(stateCode);
											//alert(stateChoice);

											if (stateChoice == _PARLIAMENT_CONSTITUENCY
													|| stateChoice == _ASSEMBLY_CONSTITUENCY) {
												//alert("df");
												fillParliamnetList(stateCode);

											} else {
												if (hierarchyStr
														.indexOf("district") != -1) {
													if (!$("#subdistrictLI")
															.is(':hidden')) {
														removeAllOptions('subdistrict');
														var options = $("#subdistrict");
														var option = $("<option/>");
														$(option)
																.val("-1")
																.text(
																		"<spring:message code='Label.SEL' htmlEscape='true'/>");
														options.append(option);
													}
													fillDistrictList(stateCode);
												}

											}

										});

						$("#district")
								.change(
										function() {
											removeError(this);
											var districtCode = this.value;

											//alert(hierarchyStr.indexOf("subdistrict"));
											if (hierarchyStr
													.indexOf("subdistrict") != -1) {
												fillSubdistrictList(districtCode);
											}
											if (stateChoice == "coveredVillagesAndGPBasedOnBlock") {
												fillBlockList(districtCode);
											}
										});

						fillBlockList = function(districtCode) {
							removeAllOptions('block')
							lgdDwrBlockService
									.getBlockListbyDistrict(
											districtCode,
											{
												callback : function(result) {

													var options = $("#block");
													var option = $("<option/>");
													$(option)
															.val("-1")
															.text(
																	"<spring:message code='Label.SEL' htmlEscape='true'/>");
													options.append(option);
													$
															.each(
																	result,
																	function(
																			key,
																			obj) {
																		var option = $("<option/>");
																		(option)
																				.val(
																						obj.blockCode)
																				.text(
																						obj.blockNameEnglish);
																		options
																				.append(option);

																	});
												},
												async : true
											});
						}

						$("#subdistrict").change(function() {
							removeError(this);
						});

						$("#parliament").change(function() {
							removeError(this);
							if ($("#assembly").is(":visible")) {
								fillAssemblyList($("#parliament").val());
							}

						});

						$("#assembly").change(function() {
							removeError(this);
						});

						$("#statewise").change(function() {
							removeError(this);
						});

						$("#captchaAnswer").change(function() {
							removeError(this);
						});

						$('#btnGenreateReport')
								.click(
										function() {
											
											var errors = new Array();
											if ($("#UNSELECT").is(':checked')) {
												$("#errrdownloadOption")
														.text(
																"Please Select one of download option ");
											} else {
												if ($("#DFDOption").is(
														':checked')) {
													errors[0] = validateDownloadFullDirectory();
												} else if ($("#DSWDOption").is(
														':checked')) {
													errors[0] = validateStatewiseDownloadDirectory();

												} else if ($("#DMDOption").is(
														':checked')) {
													errors[0] = validateDownloadModificationDirectory();

												}

											}

											errors[1] = validateDownloadOption();
											errors[2] = validateCaptcha();

											showErrorFlag = false;
											for (i = 0; i < errors.length; i++) {
												if (!errors[i]) {
													showErrorFlag = true;
													break;
												}
											}
											if (!showErrorFlag) {

												$("#UNSELECT").prop("checked",
														true);
												$("#DFD").hide();
												$("#DSWD").hide();
												$("#stateName")
														.val(
																$(
																		"#state option:selected")
																		.text());
												$("#districtName")
														.val(
																$(
																		"#district option:selected")
																		.text());
												$("#blockName")
														.val(
																$(
																		"#block option:selected")
																		.text());
												document.forms['downloadDirectoryForm'].action = "downloadDirectory.do?<csrf:token uri='"downloadDirectory.do'/>";
												document.forms['downloadDirectoryForm'].method = "POST";
												 document.forms['downloadDirectoryForm']
														.submit(); 

											}

										});

						$("input[name='multiRptFileNames']").click(function() {
							$('#errmultiRptFileNames').text("");
						});

						$('#btnActionClear').click(function() {
							go('downloadDirectory.do');

						});

						$('#btnActionClose').click(function() {
							go('welcome.do');

						});

					});

	removeError = function(obj) {
		//alert($( obj ).attr('id'));
		$(obj).removeClass("error");
		$('#err' + $(obj).attr('id')).text("");
	};

	hideAllOption = function() {
		$("#stateLI").hide();
		$("#districtLI").hide();
		$("#subdistrictLI").hide();
		$("#parliamentLI").hide();
		$("#assemblyLI").hide();
		$("#blockLI").hide();
		$("#allState").hide();
	};

	fillStateList = function() {
		removeAllOptions('state');
		lgdDwrStateService.getStateSourceList({
			callback : function(result) {

				var options = $("#state");
				var option = $("<option/>");
				//$(option).val("-1").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				//options.append(option);
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					(option).val(obj.stateCode).text(obj.stateNameEnglish);
					options.append(option);

				});
			},
			async : true
		});
	};

	fillDistrictList = function(stateCode) {
		removeAllOptions('district')
		lgdDwrDistrictService
				.getDistrictList(
						stateCode,
						{
							callback : function(result) {

								if (!$("#blockLI").is(':hidden')) {
									removeAllOptions('block');

									var blockOptions = $("#block");
									var blockOption = $("<option/>");
									$(blockOption).val("-1").text(
											"----------Select----------");
									blockOptions.append(blockOption);
								}
								var options = $("#district");
								var option = $("<option/>");
								$(option)
										.val("-1")
										.text(
												"<spring:message code='Label.SEL' htmlEscape='true'/>");
								options.append(option);
								$.each(result, function(key, obj) {
									var option = $("<option/>");
									(option).val(obj.districtCode).text(
											obj.districtNameEnglish);
									options.append(option);

								});
							},
							async : true
						});
	};

	fillSubdistrictList = function(districtCode) {
		removeAllOptions('subdistrict')
		lgdDwrSubDistrictService
				.getSubDistrictList(
						districtCode,
						{
							callback : function(result) {

								var options = $("#subdistrict");
								var option = $("<option/>");
								$(option)
										.val("-1")
										.text(
												"<spring:message code='Label.SEL' htmlEscape='true'/>");
								options.append(option);
								$.each(result, function(key, obj) {
									var option = $("<option/>");
									(option).val(obj.subdistrictCode).text(
											obj.subdistrictNameEnglish);
									options.append(option);

								});
							},
							async : true
						});
	};

	fillParliamnetList = function(stateCode) {
		removeAllOptions('parliament')
		lgdDwrParlimentService
				.getParliamentConstituencyDetail(
						stateCode,
						{
							callback : function(result) {

								var options = $("#parliament");
								var option = $("<option/>");
								$(option)
										.val("-1")
										.text(
												"<spring:message code='Label.SEL' htmlEscape='true'/>");
								options.append(option);
								$.each(result, function(key, obj) {
									var option = $("<option/>");
									(option).val(obj.pcCode).text(
											obj.pcNameEnglish);
									options.append(option);

								});
							},
							async : true
						});
	};

	fillAssemblyList = function(pcCode) {
		removeAllOptions('assembly')
		lgdDwrAssemblyService
				.getAssemblyConstituencyListbyParliamenCodet(
						pcCode,
						{
							callback : function(result) {

								var options = $("#assembly");
								var option = $("<option/>");
								$(option)
										.val("-1")
										.text(
												"<spring:message code='Label.SEL' htmlEscape='true'/>");
								options.append(option);
								$.each(result, function(key, obj) {
									var option = $("<option/>");
									(option).val(obj.acCode).text(
											obj.acNameEnglish);
									options.append(option);

								});
							},
							async : true
						});
	};

	removeAllOptions = function(id) {
		dwr.util.removeAllOptions(id);
	};

	showHideOption = function() {
		hideAllOption();
		$("#errrdownloadOption").text("");
		if ($("#DFDOption").is(':checked')) {
			$("#captchaAnswer").val("");
			captchaReferesh();
			$("#" + $("#DSWDOption").val()).hide();
			$("#" + $("#DFDOption").val()).show();
			$("#" + $("#DMDOption").val()).hide();
			$("#downloadOption").val($("#DFDOption").val());
			$("#rptFileName").val($("#rptFileName option:first").val());
		} else if ($("#DSWDOption").is(':checked')) {
			$("#captchaAnswer").val("");
			captchaReferesh();
			$("#" + $("#DSWDOption").val()).show();
			$("#" + $("#DFDOption").val()).hide();
			$("#" + $("#DMDOption").val()).hide();
			$("#downloadOption").val($("#DSWDOption").val());
		} else if ($("#DMDOption").is(':checked')) {
			$("#captchaAnswer").val("");
			captchaReferesh();
			$("#" + $("#DMDOption").val()).show();
			$("#" + $("#DFDOption").val()).hide();
			$("#" + $("#DSWDOption").val()).hide();
			$("#downloadOption").val($("#DMDOption").val());
			$("#rptFileNameMod").val($("#rptFileNameMod option:first").val());
		}
	};

	validateDownloadFullDirectory = function() {
		var entity = $('#rptFileName');
		var selOption = entity.val();
		if (selOption != "-1") {
			if (selOption.indexOf("@") != -1) {
				var showHierarchy = selOption.split("@")[1];
				var errorflag = true;
				$.each(showHierarchy.split('#'), function(index, obj) {
					var element = $("#" + obj).val();
					//alert(element);
					if (element == "-1" || element == "" || element == null
							|| element == "Select") {
						$("#" + obj).addClass("error");
						$("#err" + obj).text(
								"<spring:message htmlEscape='true' code='Label.SELECT'/> "
										+ obj);
						errorflag = false;
					}
					//alert(obj);
				});
				return errorflag;
			}

		} else {
			entity.addClass("error");
			$('#errrptFileName')
					.text(
							" <spring:message code='error.select.ENTITYLEVEL' htmlEscape='true'/>");
			return false;
		}

		return true;
	};

	validateStatewiseDownloadDirectory = function() {

		var stateWs = $('#statewise');
		var stateflag = true;
		if (stateWs.val() == -1 || stateWs.val() == "" || stateWs.val() == null
				|| stateWs.val() == "Select") {
			stateWs.addClass("error");
			$('#errstatewise')
					.text(
							"<spring:message code='error.select.SELECTSTATENAME' htmlEscape='true'/>");
			stateflag = false;
		}

		var checkCount = 0;
		var selflag = true;
		$.each($("input[name='multiRptFileNames']:checked"), function() {
			checkCount++;
		});
		if (checkCount < 2) {
			$("#errmultiRptFileNames")
					.text(
							"<spring:message htmlEscape='true' code='Label.Select.Multi.Reports' /> ");
			selflag = false;
		}
		return (stateflag && selflag);
	};

	validateDownloadOption = function() {
		//alert(5452);
		var downloadOption = $('input[name=downloadType]');
		if (downloadOption.val == "" || downloadOption.val == null) {
			downloadOption.addClass("error");
			$('#err' + downloadOption.attr('id'))
					.text(
							"<spring:message code='error.select.download.type'  htmlEscape='true'/>");
			return false;
		}
		return true;
	};

	validateCaptcha = function() {

		var center = $("input:checked + label").text();
		;
		var organisationList = $("#organisationList option:selected").val();
		//alert(center);
		var stateWs = $("#stateList option:selected").val();
		
		var optgrpOptionVal=$('#rptFileName').find('optgroup option:selected').val();
		if (center == "Central Level") {

			if (organisationList !== undefined) {
				if (organisationList == 0 || organisationList == ""
						|| organisationList == null
						|| organisationList == "Select") {

					$('#errorganisation')
							.text("Select Department/Organization");
					return false;
				}
				else{
					$('#errorganisation').hide();
					return true;
				}

			}
		}
		if (center == "State Level") {
			if (stateWs == 0 || stateWs == "" || stateWs == null
					|| stateWs == "Select") {

				$('#errstateList')
						.text(
								"<spring:message code='error.select.SELECTSTATENAME' htmlEscape='true'/>");
				return false;
			}
			else{
				$('#errstateList').hide();
				return true;
			}

			if(optgrpOptionVal === "designationBasedOnOrgCode" || optgrpOptionVal === "orgUnitBasedOnOrgCode"){
				if (organisationList == 0 || organisationList == ""
					|| organisationList == null || organisationList == "Select") {

				$('#errorganisation').text("Select Department/Organization");
				return false;
			}
				else{
					$('#errorganisation').hide();
					return true;
				}
		}
			
		}

		var captchaAnswer = $('#captchaAnswer');
		if (captchaAnswer.val() == "" || captchaAnswer.val() == null) {
			captchaAnswer.addClass("error");
			$('#err' + captchaAnswer.attr('id'))
					.text(
							"<spring:message code='error.enter.captcha'  htmlEscape='true'/>");
			return false;
		}
		return true;
	};

	/*
	 * Load basic info
	 */
	function departOrg() {
		$("#stateCenter").show();
	}
	loadSelectedInformation = function() {
		$('#captchaAnswer').val("");
		var loadOtion = '${downloadDirectoryForm.downloadOption}';
		if (loadOtion == 'DFD') {
			$("#DFDOption").prop("checked", true);
			$("#" + $("#DSWDOption").val()).hide();
			$("#" + $("#DFDOption").val()).show();
			validateandSelectDownloadFullDirectory();
		} else if (loadOtion == 'DSWD') {
			$("#DSWDOption").prop("checked", true);
			//alert($("#DSWDOption").val());
			$("#" + $("#DSWDOption").val()).show();
			$("#" + $("#DFDOption").val()).hide();
			validateandSelectStateWiseDownloadDirectory();
		} else {
			$("#errrdownloadOption").text(
					"Please Select one of download option ");
		}
		//alert(loadOtion);
	};

	validateandSelectDownloadFullDirectory = function() {

		var loadEntity = '${downloadDirectoryForm.rptFileName}';
		//alert(loadEntity);
		$("#rptFileName option[value='" + loadEntity + "']").attr("selected",
				"selected");
		showHierarchyLoad(loadEntity);

	};

	validateandSelectStateWiseDownloadDirectory = function() {

		var loadMultiRptFileNames = '${downloadDirectoryForm.multiRptFileNames}';
		if (loadMultiRptFileNames != "") {
			//alert(loadMultiRptFileNames);
			if (loadMultiRptFileNames.indexOf(",") != -1) {
				$.each(loadMultiRptFileNames.split(','), function(index, val) {
					//alert(val);
					$("input:checkbox[value=" + val + "]")
							.attr("checked", true);
				});
			} else {
				//alert(loadMultiRptFileNames);
				$("input:checkbox[value=" + loadMultiRptFileNames + "]").attr(
						"checked", true);
			}
		} else {

		}

	};

	showHierarchyLoad = function(selOption) {
		if (selOption.indexOf("@") != -1) {
			var hierarchySet = selOption.split("@")[1];
			hierarchyStr = hierarchySet;
			var loadEntityCodes = '${downloadDirectoryForm.entityCodes}';
			//alert(loadEntityCodes);
			var entityCodesArr;
			if (loadEntityCodes.indexOf(",") != -1) {
				entityCodesArr = loadEntityCodes.split(",");
				setTimeout(
						function() {
							//alert(entityCodesArr);
							$
									.each(
											hierarchySet.split('#'),
											function(index, val) {

												$("#" + val + "LI").show();
												var loadVal = entityCodesArr[index];
												//alert("loadVal:"+loadVal);
												if (checkEmptyObject(loadVal)) {
													$("#" + val).addClass(
															"error");
													$("#err" + val).text(
															"<spring:message htmlEscape='true' code='Label.SELECT'/> "
																	+ val);
												} else {
													if (val == "state") {
														if (option == _PARLIAMENT_CONSTITUENCY
																|| option == _ASSEMBLY_CONSTITUENCY) {
															if (hierarchyStr
																	.indexOf("parliament") != -1) {
																fillParliamnetList(loadVal);
															}

														} else {
															if (hierarchyStr
																	.indexOf("district") != -1) {
																if (!$(
																		"#subdistrictLI")
																		.is(
																				':hidden')) {
																	removeAllOptions('subdistrict')
																}
																fillDistrictList(loadVal);
															}

														}

													} else if (val == "district") {
														if (hierarchyStr
																.indexOf("subdistrict") != -1) {
															fillSubdistrictList(loadVal);
														}

													}

													setTimeout(
															function() {
																$(
																		"#"
																				+ val
																				+ " option[value='"
																				+ loadVal
																				+ "']")
																		.attr(
																				"selected",
																				"selected");
															}, 200);

												}

											});
						}, 200);
			} else {
				$("#" + hierarchySet + "LI").show();
				if (loadEntityCodes == "-1") {
					$("#" + hierarchySet).addClass("error");
					$("#err" + hierarchySet).text(
							"<spring:message htmlEscape='true' code='Label.SELECT'/> "
									+ hierarchySet);
				} else {
					$(
							"#" + hierarchySet + " option[value='"
									+ loadEntityCodes + "']").attr("selected",
							"selected");
				}

			}

		}
	};

	/**
	 * The {@code $_checkEmptyObject} used to check object / element  
	 * is empty or undefined.
	 */
	var checkEmptyObject = function(obj) {
		if (obj == null || obj == "Select" || obj == "-1") {
			return true;
		}
		return false;
	};

	removeAllPopulateList = function() {
		$("#state option[value='-1']").attr("selected", "selected");
		dwr.util.removeAllOptions('district');
		dwr.util.removeAllOptions('block');
		dwr.util.removeAllOptions('subdistrict');
		dwr.util.removeAllOptions('parliament');
		dwr.util.removeAllOptions('assembly');
		dwr.util.removeAllOptions('state');
		dwr.util.removeAllOptions('allStateList');
	};

	captchaReferesh = function() {

		var timestamp = (new Date()).getTime();
		var newSrc = $("#captchaImageId").attr("src").split("?");
		//  $('#captchaImage').attr('src', '').attr('src', 'Captcha.jpg');
		newSrc = newSrc[0] + "?" + timestamp;
		$("#captchaImageId").attr("src", newSrc);
		$("#captchaImageId").slideDown("fast");
	};

	go = function(url) {

		if (url.indexOf('home.htm') > -1
				|| url
						.indexOf(window.location.pathname.split('/')[window.location.pathname
								.split('/').length - 1]) == -1) {
			if (url.indexOf("?") > -1)
				window.location = url + "&OWASP_CSRFTOKEN="
						+ getParameterByNameForCSRF();
			else
				window.location = url + "?OWASP_CSRFTOKEN="
						+ getParameterByNameForCSRF();
		} else
			window.location = window.location.href;

	};

	getParameterByNameForCSRF = function() {

		var name = "OWASP_CSRFTOKEN";
		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regexS = "[\\?&]" + name + "=([^&#]*)";
		var regex = new RegExp(regexS);
		var results = regex.exec(window.location.search);
		if (results == null)
			return "";
		else
			return decodeURIComponent(results[1].replace(/\+/g, " "));
	};

	open_win = function() {
		var obj = window
				.showModalDialog(
						"help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}",
						'',
						"dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	};

	validateDownloadModificationDirectory = function() {
		var fromDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		//alert("fromDate:"+fromDate+",toDate:"+toDate);
		var entity = $('#rptFileNameMod');
		var selOption = entity.val();
		if (selOption != "-1") {
			if (selOption.indexOf("@") != -1) {
				var showHierarchy = selOption.split("@")[1];
				var errorflag = true;
				$.each(showHierarchy.split('#'), function(index, obj) {
					var element = $("#" + obj).val();
					//alert(element);
					if (element == "-1" || element == "" || element == null
							|| element == "Select") {
						$("#" + obj).addClass("error");
						$("#err" + obj).text(
								"<spring:message htmlEscape='true' code='Label.SELECT'/> "
										+ obj);
						errorflag = false;
					}
					//alert(obj);
				});
				return errorflag;
			}

		} else {
			entity.addClass("error");
			$('#errrptFileNameMod')
					.text(
							" <spring:message code='error.select.ENTITYLEVEL' htmlEscape='true'/>");
			return false;
		}

		return true;
	};
	
	
	fillStateListforOption=function(){
		  removeAllOptions('stateList');
		  lgdDwrStateService.getStateSourceList( {
		  callback : function(result) {
		 
		  var options = $("#stateList");
		  var option = $("<option/>");
		  $(option).val("0").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
		  options.append(option);
		  $.each(result, function(key, obj) {
		  var option = $("<option/>");
		  (option).val(obj.stateCode).text(obj.stateNameEnglish);
		  options.append(option);
		 
		  });
		  },
		  async : true
		  });
		  };
		  
		  callOrgList=function(){
		  var stateCode =0;
			lgdDwrStateService.getOrganisationList(stateCode, {
				callback : function(result) {
					removeAllOptions('organisationList');
					var options = $("#organisationList");
					var option = $("<option/>");
					$(option).val("0").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.orgCode).text(obj.orgName);
						options.append(option);
						
					}); 
				
				},
				async : true
			});		
		  }
		  
		  
		  AllStateListforOption=function(){
			  removeAllOptions('allStateList');
			  removeAllOptions('state');
			  lgdDwrStateService.getStateSourceList( {
			  callback : function(result) {
			 
			  var options = $("#allStateList");
			  var option = $("<option/>");
			  $(option).val("0").text("<spring:message code='Label.AllSTATE' htmlEscape='true'/>");
			  options.append(option);
			  $.each(result, function(key, obj) {
			  var option = $("<option/>");
			  (option).val(obj.stateCode).text(obj.stateNameEnglish);
			  options.append(option);
			 
			  });
			  },
			  async : true
			  });
			  };
		  
		    
			/*   selectStateListforOption=function(){
			   var allselect;
			  $('#rptFileName').on('change',function(){
				  var optgrpOptionVal=opt.closest('optgroup option:selected').val();
				  if(optgrpOptionVal=="subdistrictVillageBlockGpsMapping")
					  {
					  AllStateListforOption();
					  
					  }
				  
				  
			  });
			  
			  }
			   */
			  
</script>
