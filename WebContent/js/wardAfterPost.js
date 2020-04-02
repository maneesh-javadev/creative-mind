function loadValuesAfterPost() {
	hideShowDivforViewWardFP(lgd_LBTypeName, districtCodeP, lbTypeP, stateCodeP);
}

function hideShowDivforViewWardFP(id, districtCode, lbtype, stateCode) {
	var temp = id.split(":");
	var id1 = temp[0];
	var id2 = temp[1];
	var id3 = temp[2];
	var id4 = temp[3];
	var id5 = temp[4];
	// var stateCode = dwr.util.getValue('hdnStateCode');

	document.getElementById('flagCode').value = id1;
	// document.getElementById('cattype').value=category;
	document.getElementById('level').value = "";

	document.getElementById('divLgdSelIntermediateP').style.visibility = 'hidden';
	document.getElementById('divLgdSelIntermediateP').style.display = 'none';
	document.getElementById('divLgdVillageP').style.display = 'none';
	document.getElementById('divLgdVillageP').style.visibility = 'hidden';
	document.getElementById('divLgdVillagePanch').style.display = 'none';
	document.getElementById('divLgdVillagePanch').style.visibility = 'hidden';
	document.getElementById('tr_district1').style.display = 'none';
	document.getElementById('tr_district1').style.visibility = 'hidden';
	document.getElementById('tr_intermediate1').style.display = 'none';
	document.getElementById('tr_intermediate1').style.visibility = 'hidden';
	document.getElementById('tr_village').style.display = 'none';
	document.getElementById('tr_village').style.visibility = 'hidden';

	if (id2 == 'D') {

		// document.getElementById('divLgdSelIntermediateP').style.visibility =
		// 'none';
		// document.getElementById('divLgdSelIntermediateP').style.display =
		// 'hidden';
		document.getElementById('divLgdVillageP').style.display = 'none';
		// document.getElementById('divLgdVillageP').style.visibility =
		// 'hidden';
		document.getElementById('divLgdVillagePanch').style.display = 'none';
		// document.getElementById('divLgdVillagePanch').style.visibility =
		// 'hidden';
		document.getElementById('tr_district1').style.display = 'none';
		// document.getElementById('tr_district1').style.visibility = 'hidden';
		document.getElementById('tr_intermediate1').style.display = 'none';
		// document.getElementById('tr_intermediate1').style.visibility =
		// 'hidden';
		document.getElementById('tr_village').style.display = 'none';
		document.getElementById('tr_village').style.visibility = 'hidden';
		document.getElementById('tr_district1').style.visibility = 'visible';
		document.getElementById('tr_district1').style.display = 'block';

		document.getElementById("firstlevel").innerHTML = " " + id3;

		// document.getElementById("errSelectVIP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_dist').style.display = 'block';
		 * document.getElementById('tr_village_dist').style.visibility='visible';
		 */
		if (id5 == 0) {
			// getdisPanchayatListforDP(stateCode,id1);
			if (districtCode == 0) {
				getDistrictPanchayatListforDPNewWardP(id1, stateCode);
			}
			if (districtCode != 0) {
				getLandRegionWiseDis(id1, id2, districtCode, lbtype);
			}
			var idDist = id3;
			var fieldId = 'districtDP';
			var fieldId1 = 'districtDP';
			dwr.util.setValue(fieldId, idDist, {
				escapeHtml : false
			});
			dwr.util.setValue(fieldId1, idDist, {
				escapeHtml : false
			});

		}
	} else if (id2 == 'I') {
		document.getElementById('level').value = 'I';
		document.getElementById("secondlevel").innerHTML = " " + id3;

		document.getElementById('tr_intermediate1').style.visibility = 'visible';
		document.getElementById('tr_intermediate1').style.display = 'block';

		// document.getElementById("errSelectVDP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_inter').style.display = 'block';
		 * document.getElementById('tr_village_inter').style.visibility='visible';
		 */

		// dwr.util.removeAllOptions('ddlgdLBDistrictAtVillage');
		if (id5 == 0) {
			getdisInterPanchayatListforIPP(stateCode, id1);
		} else {
			getLocalBodyListbylblcCodeFP(stateCode, id5);
		}

		var idInter = id3;
		var fieldId = 'districtIP';
		var fieldId1 = 'districtIP';
		dwr.util.setValue(fieldId, idInter, {
			escapeHtml : false
		});
		dwr.util.setValue(fieldId1, idInter, {
			escapeHtml : false
		});
	} else if (id2 == 'V') {
		// alert("idv"+id);
		// alert(id4);
		document.getElementById("thirdlevel").innerHTML = " " + id3;
		document.getElementById('tr_village').style.visibility = 'visible';
		document.getElementById('tr_village').style.display = 'block';

		// document.getElementById("errSelectVVP").innerHTML="Select "+id3;
		/*
		 * document.getElementById('tr_village_pan').style.display = 'block';
		 * document.getElementById('tr_village_pan').style.visibility='visible';
		 */
		dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
		if (id5 == 0) {
			getdisVillagePanchayatListforVPOneLevelPost(stateCode, id1);
		} else {
			getLocalBodyListbylblcCodeFP(stateCode, id5);
		}

		var idVill = id3;
		var fieldId = 'districtVP';
		var fieldId1 = 'districtVP';
		dwr.util.setValue(fieldId, idVill, {
			escapeHtml : false
		});
		dwr.util.setValue(fieldId1, idVill, {
			escapeHtml : false
		});

	}
}

function getDistrictPanchayatListforDPNewWardP(id1, stateCode) {
	lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryNewWardF(id1, stateCode, {
		callback : function(data) {
			var fieldId = 'ddSourceLocalBody';
			var valueText = 'localBodyCode';
			var nameText = 'localBodyNameEnglish';

			dwr.util.removeAllOptions(fieldId);
			var st = document.getElementById('ddSourceLocalBody');
			st.add(new Option('Select', '0'));

			var options = $("#ddSourceLocalBody");
			$.each(data, function(key, obj) {
				var option = $("<option/>");
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				} else {
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				}
			});
			$('#ddSourceLocalBody').val(localBodyNameEnglishListP);
		},
		errorHandler : handleDistrictErrorListDP
	});

}

function getLandRegionWiseDis(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : function(data) {
			var fieldId = 'ddSourceLocalBody';
			var valueText = 'localBodyCode';
			var nameText = 'localBodyNameEnglish';

			dwr.util.removeAllOptions(fieldId);
			var st = document.getElementById('ddSourceLocalBody');
			st.add(new Option('Select', '0'));
			dwr.util.addOptions(fieldId, data, valueText, nameText);
			$('#ddSourceLocalBody').val(localBodyNameEnglishListP);
		},
		errorHandler : handleLandRegionWiseErrorDisWard
	});
}

function handleLandRegionWiseErrorDisWard() {
	alert("No data found!");
}

function getdisInterPanchayatListforIPP(id1, id2) {
	// alert("inter"+id1+":"+id2);
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : function(data) {
			var fieldId = 'ddLgdLBDistListAtVillageCA';
			var fieldId1 = 'ddLgdLBInterPSourceList';
			var valueText = 'localBodyCode';
			var nameText = 'localBodyNameEnglish';

			dwr.util.removeAllOptions(fieldId);
			dwr.util.removeAllOptions(fieldId1);
			var st = document.getElementById('ddLgdLBDistListAtVillageCA');
			st.add(new Option('Select', '0'));

			var options = $("#ddLgdLBDistListAtVillageCA");
			$.each(data, function(key, obj) {
				var option = $("<option/>");
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				} else {
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				}
			});

			var st = document.getElementById('ddLgdLBInterPSourceList');
			st.add(new Option('Select', '0'));

			var options = $("#ddLgdLBInterPSourceList");
			$.each(data, function(key, obj) {
				var option = $("<option/>");
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				} else {
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				}
			});
		},
		errorHandler : handledisInterPanchayatErrorforIP
	});
}

function getLocalBodyListbylblcCodeFP(stateCode, lblc) {
	lgdDwrlocalBodyService.getLocalbodyDetailbyCode(stateCode, lblc, {
		callback : function(data) {
			var stateCode = dwr.util.getValue('hdnStateCode');
			// var category=document.getElementById('cattype').value;
			var lblc = data[0].localBodyTypeCode;
			// alert(statecode);
			var plblc = data[0].parentTierSetupCode;
			var level = data[0].level;
			var name = data[0].localBodyTypeName;
			var districtCode = dwr.util.getValue('districtCode');
			var lbtype = dwr.util.getValue('lbType');
			var lgdLBType = dwr.util.getValue('ddLgdLBType');
			var temp = lgdLBType.split(":");
			var id1 = temp[0];
			var id2 = temp[1];

			if (level == 'D') {
				document.getElementById("firstlevel").innerHTML = " " + name;
				// document.getElementById("errSelectVIP").innerHTML="Select
				// "+name;
				if (plblc == 0) {
					document.getElementById('divLgdSelIntermediateP').style.display = 'block';
					document.getElementById('divLgdSelIntermediateP').style.visibility = 'visible';
					if (districtCode == 0) {
						lgdDwrlocalBodyService.getPanchayatListbylblcCode(stateCode, lblc, {
							callback : function(data) {
								var fieldId = 'ddLgdLBDistListAtInterCA';
								var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
								var valueText = 'localBodyCode';
								var nameText = 'localBodyNameEnglish';

								dwr.util.removeAllOptions(fieldId);
								dwr.util.removeAllOptions(fieldId1);
								var st1 = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
								var st = document.getElementById('ddLgdLBDistListAtInterCA');
								st.add(new Option('Select', '0'));
								st1.add(new Option('Select', '0'));

								var options = $("#ddLgdLBDistListAtInterCA");
								$.each(data, function(key, obj) {
									var option = $("<option/>");
									if (obj.operation_state == 'F') {
										$(option).attr('disabled', 'disabled');
										$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
										options.append(option);
									} else {
										$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
										options.append(option);
									}
								});

								var options = $("#ddLgdLBVillageSourceAtVillageCA");
								$.each(data, function(key, obj) {
									var option = $("<option/>");
									if (obj.operation_state == 'F') {
										$(option).attr('disabled', 'disabled');
										$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
										options.append(option);
									} else {
										$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
										options.append(option);
									}
								});
								// alert(lgd_LBDistListAtInterCA);
								$('#ddLgdLBDistListAtInterCA').val(lgd_LBDistListAtInterCAP);
								getInterPanchayatWardbyDcodeP($('#ddLgdLBDistListAtInterCA').val());

							},
							errorHandler : handledisVillagePanchayatErrorforVP
						});
						// getdisVillagePanchayatListforVPP(stateCode, lblc);
					}
					if (districtCode != 0) {
						getLandRegionWiseDisFinalP(id1, id2, districtCode, lbtype);
					}

				}
			}

			else if (level == 'I') {
				document.getElementById("secondlevel").innerHTML = " " + name;
				// document.getElementById("errSelectVDP").innerHTML="Select
				// "+name;
				document.getElementById('level').value = 'I';
				if (plblc == 0) {
					document.getElementById('divLgdVillageP').style.display = 'block';
					document.getElementById('divLgdVillageP').style.visibility = 'visible';
					dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
					getdisInterPanchayatListforIPP(stateCode, lblc);
				} else {
					document.getElementById('divLgdVillageP').style.display = 'block';
					document.getElementById('divLgdVillageP').style.visibility = 'visible';
					dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
					getLocalBodyListbylblcCodeFP(stateCode, plblc);
				}
			}
		},
		errorHandler : handleLocalbodylblcCodeErrorF
	});
}

function getdisVillagePanchayatListforVPP(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVPP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function handledisVillagePanchayatSuccessforVPP(data) {

	var fieldId = 'ddLgdLBDistListAtInterCA';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st1 = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	var st = document.getElementById('ddLgdLBDistListAtInterCA');
	st.add(new Option('Select', '0'));
	st1.add(new Option('Select', '0'));

	var options = $("#ddLgdLBDistListAtInterCA");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});

	var options = $("#ddLgdLBVillageSourceAtVillageCA");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
	/*modified by pooja on 14-07-2015*/
	$('#ddLgdLBDistListAtInterCA').val(lgd_LBDistListAtInterCAP);
	getInterPanchayatWardbyDcodeP($('#ddLgdLBDistListAtInterCA').val());

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function getLandRegionWiseDisFinalP(id1, id2, districtCode, lbtype) {
	lgdDwrlocalBodyService.getLandRegionWise(id1, id2, districtCode, lbtype, {
		callback : handledisVillagePanchayatSuccessforVPP,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

/*
 * function handledisVillagePanchayatSuccessforVPP(data) {
 * 
 * var fieldId = 'ddLgdLBDistListAtInterCA'; var fieldId1 =
 * 'ddLgdLBVillageSourceAtVillageCA'; var valueText = 'localBodyCode'; var
 * nameText = 'localBodyNameEnglish';
 * 
 * dwr.util.removeAllOptions(fieldId); dwr.util.removeAllOptions(fieldId1); var
 * st1 = document.getElementById('ddLgdLBVillageSourceAtVillageCA'); var st =
 * document.getElementById('ddLgdLBDistListAtInterCA'); st.add(new
 * Option('Select', '0')); st1.add(new Option('Select', '0'));
 * 
 * var options = $("#ddLgdLBDistListAtInterCA"); $.each(data, function(key, obj) {
 * var option = $("<option/>"); if (obj.operation_state == 'F') {
 * $(option).attr('disabled', 'disabled');
 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
 * options.append(option); } else {
 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
 * options.append(option); } });
 * 
 * var options = $("#ddLgdLBVillageSourceAtVillageCA"); $.each(data,
 * function(key, obj) { var option = $("<option/>"); if (obj.operation_state ==
 * 'F') { $(option).attr('disabled', 'disabled');
 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
 * options.append(option); } else {
 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
 * options.append(option); } }); // dwr.util.addOptions(fieldId, data,
 * valueText, nameText); // dwr.util.addOptions(fieldId1, data, valueText,
 * nameText); }
 */

function getInterPanchayatWardbyDcodeP(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPanchayatWardSuccessP,
		errorHandler : handleInterPanchayatWardError
	});
}

function handleInterPanchayatWardSuccessP(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterPSourceList';
	var fieldId1 = 'ddLgdLBDistListAtVillageCA';
	var fieldId2 = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	dwr.util.removeAllOptions(fieldId2);

	var st = document.getElementById('ddLgdLBInterPSourceList');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	var st = document.getElementById('ddLgdLBDistListAtVillageCA');
	st.add(new Option('Select', '0'));
	var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select', '0'));

	var options = $("#ddLgdLBInterPSourceList");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});

	if (document.getElementById('divLgdVillageP').style.display == 'block') {
		var options = $("#ddLgdLBDistListAtVillageCA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
				options.append(option);
			}
		});
		$('#ddLgdLBDistListAtVillageCA').val(lgd_LBDistListAtVillageCAP);

	} else {
		var options = $("#ddLgdLBVillageSourceAtVillageCA");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
				options.append(option);
			}
		});
		$("#ddLgdLBVillageSourceAtVillageCA").val(localBodyNameEnglishListSourceVillgP);
	}

	$('#ddLgdLBInterPSourceList').val(lgd_LBInterPSourceListP);
	$('#ddLgdLBDistListAtVillageCA').val(lgd_LBDistListAtVillageCAP);
	if ($('#ddLgdLBDistListAtVillageCA').val() != '' && $('#ddLgdLBDistListAtVillageCA').val() != 0) {
		getInterPanchayatbyDcodeAtVWardFinalP($('#ddLgdLBDistListAtVillageCA').val());
	}

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
	// dwr.util.addOptions(fieldId2, data, valueText, nameText);
}

function getInterPanchayatbyDcodeAtVWardFinalP(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPWardVCASuccessP,
		errorHandler : handleInterPWardVCAError
	});
}

function handleInterPWardVCASuccessP(data) {
	// Assigns data to result id

	var fieldId = 'ddLgdLBInterListAtVillageCA';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st = document.getElementById('ddLgdLBInterListAtVillageCA');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	var st = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	st.add(new Option('Select', '0'));

	var options = $("#ddLgdLBInterListAtVillageCA");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});

	var options = $("#ddLgdLBVillageSourceAtVillageCA");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
	$('#ddLgdLBVillageSourceAtVillageCA').val(localBodyNameEnglishListSourceVillgP);

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}

function getdisVillagePanchayatListforVPOneLevelPost(id1, id2) {
	lgdDwrlocalBodyService.getPanchayatListbylblcCode(id1, id2, {
		callback : handledisVillagePanchayatSuccessforVPOneLevelPost,
		errorHandler : handledisVillagePanchayatErrorforVP
	});
}

function handledisVillagePanchayatSuccessforVPOneLevelPost(data) {

	var fieldId = 'ddLgdLBDistListAtInterCA';
	var fieldId1 = 'ddLgdLBVillageSourceAtVillageCA';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	dwr.util.removeAllOptions(fieldId1);
	var st1 = document.getElementById('ddLgdLBVillageSourceAtVillageCA');
	var st = document.getElementById('ddLgdLBDistListAtInterCA');
	st.add(new Option('Select', '0'));
	st1.add(new Option('Select', '0'));

	var options = $("#ddLgdLBVillageSourceAtVillageCA");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		} else {
			$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
			options.append(option);
		}
	});
	$("#ddLgdLBVillageSourceAtVillageCA").val(localBodyNameEnglishListSourceVillgP);
	/*
	 * var options = $("#ddLgdLBVillageSourceAtVillageCA"); $.each(data,
	 * function(key, obj) { var option = $("<option/>"); if
	 * (obj.operation_state == 'F') { $(option).attr('disabled', 'disabled');
	 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
	 * options.append(option); } else {
	 * $(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
	 * options.append(option); } });
	 */

	// dwr.util.addOptions(fieldId, data, valueText, nameText);
	// dwr.util.addOptions(fieldId1, data, valueText, nameText);
}