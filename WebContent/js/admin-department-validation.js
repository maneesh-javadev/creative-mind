var arrAccessLevesCenter = [ "D", "T", "V" ];
var arrAccessLevesState = [ "T", "B", "V" ];

notAllowedSpecialCharacters = function(value) {
	var _string_reg_ex = "[\#\%\~\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\\\\:\;\'\"\<\>\?\]";
	var regularExpression = new RegExp(_string_reg_ex);
	return !regularExpression.test(value);
};

notAllowedNumbers = function(value) {

	var _String_no = "[0-9]";
	var regularExpressionno = new RegExp(_String_no);
	return regularExpressionno.test(value);

};

clearFormErrors = function() {
	 $( "label[id^=err_]" ).each(function(){
			$( this ).html('');
	    });
	//$('label').html('');
};

validateForm = function() {
	clearFormErrors();
	var checkValid = true;
	var textVal = $("#deptNameEnglish").val();
	if (textVal == "") {
		if (isCenterUser) {
			if (isOrganizationFlow) {
				$('#err_deptName').html("Organization Name is Required");
			}else{
				$('#err_deptName').html("Ministry/Department Name is Required");	
			}
			
		}else{
			$('#err_deptName').html("Department Name is Required");
		}
		
		checkValid = false;
	}

	if (!notAllowedSpecialCharacters(textVal)) {
		$('#err_deptName').html("special characters not allowed.");
		checkValid = false;
	}

	if (notAllowedNumbers(textVal)) {
		$('#err_deptName').html("Numbers are not allowed.");
		checkValid = false;
	}

	/*
	 * if (test1()) { alert("test1 called"); $('#err_deptName').html("Department
	 * Name Already Exist"); } else {
	 * 
	 * alert("duplicate"); }
	 */

	if (isOrganizationFlow) {
		if (isCenterUser) {
			
			var ministryidvar = document.getElementById("ministryId");
			if (ministryidvar != null && typeof ministryidvar != 'undefined') {
				if (ministryidvar.options[ministryidvar.selectedIndex].value == "") {
					$('#err_ministry').html("List of Ministries is Required.");
					checkValid = false;
				}
			}
	

		}

		var deptOrgCodevar = document.getElementById("deptOrgCode");
		if (deptOrgCodevar != null && typeof deptOrgCodevar != 'undefined') {
			if ((deptOrgCodevar.options[deptOrgCodevar.selectedIndex].value == "")
					|| deptOrgCodevar.options[deptOrgCodevar.selectedIndex].value == "") {
				$('#err_dept').html("List of Department is Required.");
				checkValid = false;
			}
		}
		var orgTypevar = document.getElementById("orgType");
		if (orgTypevar != null && typeof orgTypevar != 'undefined') {
			if (orgTypevar.options[orgTypevar.selectedIndex].value == "") {
				$('#err_org').html("List of Orginazation is Required.");
				checkValid = false;
			}
		}
	} else {
		if (isCenterUser) {
			
			
			var ministryidvar = document.getElementById("ministryId");
			var isMinistry=$('#hMinistry');
			if( isMinistry!=null && typeof isMinistry != 'undefined' && isMinistry.val()=='false'){
				
				var entityLvl=$('input:radio[name=rMinistry]:checked').val();
				//alert(entityLvl);
				if (entityLvl==undefined) {
					$('#err_rMinistry').html("Please Select an Entity");
					checkValid = false;
				}	
				
			if (ministryidvar != null && typeof ministryidvar != 'undefined') {
				if (ministryidvar.options[ministryidvar.selectedIndex].value == "") {
					$('#err_ministry').html("List of Ministries is Required.");
					checkValid = false;
				}
			}
			}
			
		}

	}

	var chooseLevel = $('input[name=chooseLevelAllOrSpecific]:radio:checked').val();
	if (typeof chooseLevel != 'undefined') {

		if (chooseLevel == "S") {

			if (isCenterUser) {
				if ($.inArray(accessLevel, arrAccessLevesCenter) > -1) {
					if (accessLevel == _STATE_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked')) {
							$("#err_statechk").html("At Least One State Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _DISTRICT_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked') && $("#ChkPartialCoverage").is(':unchecked')) {
							$("#err_statechk").html("At Least One District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _SUBDISTRICT_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked') && $("#districtChkFull").is(':unchecked')
								&& $("#DistrictChkPart").is(':unchecked')) {
							$("#err_statechk").html("At Least One Sub-District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _BLOCK_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#DistrictChkPart").is(':unchecked')) {
							$("#err_statechk").html("At Least One Block Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _VILLAGE_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked') && $("#districtChkFull").is(':unchecked')
								&& $("#SubDistrictChkFull").is(':unchecked') && $("#SubDistrictChkPart").is(':unchecked')) {
							$("#err_statechk").html("At Least One Village Should be Selected");
							checkValid = false;
						}
					}
				}
			} else {
				if ($.inArray(accessLevel, arrAccessLevesState) > -1) {
					if (accessLevel == _DISTRICT_LEVEL) {
						if ($("#districtChkFull").is(':unchecked')) {
							$("#err_districtchk").html("At Least One District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _SUBDISTRICT_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#DistrictChkPart").is(':unchecked')) {
							$("#err_districtchk").html("At Least One Sub-District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _BLOCK_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#DistrictChkPart").is(':unchecked')) {
							$("#err_districtchk").html("At Least One Block Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _VILLAGE_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#SubDistrictChkFull").is(':unchecked')
								&& $("#SubDistrictChkPart").is(':unchecked')) {
							$("#err_districtchk").html("At Least One Village Should be Selected");
							checkValid = false;
						}
					}
					
					
				}
			}

			if (accessLevel == _ADMINISTRATIVE_LEVEL) {
				if (!isCenterUser) {
					var ddparent5 = document.getElementById('ddTargetAdminEnitiy');
					if (ddparent5.options.length == 0) {
						$("#err_Administrativeblk").html("Administrative must be filled");
						checkValid = false;
					}
				}
			}
			
			if (accessLevel == "U") {
				
				var uparent1 = document.getElementById('targetUList');
				if (uparent1.options.length == 0) {
					$("#err_ulevel").html(uname+" must be filled");
					checkValid = false;
				}
			
			}
			
			if (accessLevel == "X") {
				
					var dpparent1 = document.getElementById('targetDPList');
					if (dpparent1.options.length == 0) {
						$("#err_dplevel").html(dpname+" must be filled");
						checkValid = false;
					}
				
			}
			
			if (accessLevel == "Y") {
				
				
				if ($("#DPChkFull").is(':checked')) {
					var dpparent1 = document.getElementById('targetDPList');
					if (dpparent1.options.length == 0) {
						$("#err_dplevel").html(dpname+" must be filled");
						checkValid = false;
					}
				}
				if ($("#DPChkpart").is(':checked')) {
						var ipparent1 = document.getElementById('targetIPList');
					if (ipparent1.options.length == 0) {
						$("#err_iplevel").html(ipname+" must be filled");
						checkValid = false;
					}
				}
				
			
		}
			
			if (accessLevel == "Z") {
				
				if ($("#DPChkFull").is(':checked')) {
					var dpparent1 = document.getElementById('targetDPList');
					if (dpparent1.options.length == 0) {
						$("#err_dplevel").html(dpname+" must be filled");
						checkValid = false;
					}
				}
				if ($("#DPChkpart").is(':checked')) {
						var ipparent1 = document.getElementById('targetIPList');
					if (ipparent1.options.length == 0) {
						$("#err_iplevel").html(ipname+" must be filled");
						checkValid = false;
					}
				}
				if ($("#DPChkpartVP").is(':checked')) {
					var vpparent1 = document.getElementById('targetVpList');
				if (vpparent1.options.length == 0) {
					$("#err_vplevel").html(vpname+" must be filled");
					checkValid = false;
				}
			}
			
		}
			
			/*if ("XYZ".includes(accessLevel)) {
				if (!isCenterUser) {
					var ddparent5 = document.getElementById('ddTargetLocalBodyEnitiy');
					if (ddparent5.options.length == 0) {
						$("#err_LocalBodyblk").html("Local Body must be filled");
						checkValid = false;
					}
				}
			}*/
			if (accessLevel == _STATE_LEVEL) {
				if (isCenterUser) {
					var ddparent5 = document.getElementById('ddTargetState');
					if (ddparent5.options.length == 0) {
						$("#err_state").html("State must be filled");
						checkValid = false;
					}
				}
			}

			if (accessLevel == _DISTRICT_LEVEL) {
				if (isCenterUser) {
					if ($("#ChkFullCoverage").is(':checked')) {
						var ddparent5 = document.getElementById('ddTargetState');
						if (ddparent5.options.length == 0) {
							$("#err_state").html("state must be filled");
							checkValid = false;
						}
					}
					if ($("#ChkPartialCoverage").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
					}
				} else {
					var ddparent1 = document.getElementById('ddTargetDistrict');
					if (ddparent1.options.length == 0) {
						$("#err_district").html("District must be filled");
						checkValid = false;
					}
				}
			}

			if (accessLevel == _SUBDISTRICT_LEVEL) {
				if (isCenterUser) {
					if ($("#ChkFullCoverage").is(':checked')) {
						var ddparent5 = document.getElementById('ddTargetState');
						if (ddparent5.options.length == 0) {
							$("#err_state").html("state must be filled");
							checkValid = false;
						}
					}
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
					}
					if ($("#DistrictChkPart").is(':checked')) {
						var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
						if (ddparent2.options.length == 0) {
							$("#err_subDistrict").html("Sub-District must be filled");
							checkValid = false;
						}
					}
				} else {
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
						if ($("#DistrictChkPart").is(':checked')) {
							var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
							if (ddparent2.options.length == 0) {
								$("#err_subDistrict").html("Sub-District must be filled");
								checkValid = false;
							}
						}
					}
				}
			}
			if (accessLevel == _BLOCK_LEVEL) {
				if ($("#districtChkFull").is(':checked')) {
					var ddparent1 = document.getElementById('ddTargetDistrict');
					if (ddparent1.options.length == 0) {
						$("#err_district").html("District must be filled");
						checkValid = false;
					}
				}
				if ($("#DistrictChkPart").is(':checked')) {
					var ddparent3 = document.getElementById('ddTargetBlockSDLvl');
					if (ddparent3.options.length == 0) {
						$("#err_block").html("Block must be filled");
						checkValid = false;
					}
				}
			}

			if (accessLevel == _VILLAGE_LEVEL) {
				if (isCenterUser) {
					if ($("#ChkFullCoverage").is(':checked')) {
						var ddparent5 = document.getElementById('ddTargetState');
						if (ddparent5.options.length == 0) {
							$("#err_state").html("State must be filled");
							checkValid = false;
						}
					}
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkFull").is(':checked')) {
						var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
						if (ddparent2.options.length == 0) {
							$("#err_subDistrict").html("Sub-District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkPart").is(':checked')) {
						var ddparent4 = document.getElementById('ddTargetDistrictVillLvl');
						if (ddparent4.options.length == 0) {
							$("#err_village").html("Village must be filled");
							checkValid = false;
						}
					}
				} else {
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkFull").is(':checked')) {
						var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
						if (ddparent2.options.length == 0) {
							$("#err_subDistrict").html("Sub-District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkPart").is(':checked')) {
						var ddparent4 = document.getElementById('ddTargetDistrictVillLvl');
						if (ddparent4.options.length == 0) {
							$("#err_village").html("Village must be filled");
							checkValid = false;
						}
					}
				}
			}
		}

	}
	return checkValid;
};

validateFormExtended = function() {
	clearFormErrors();
	var checkValid = true;
	var textVal = $("#deptNameEnglish").val();
	if (textVal == "") {
		$('#err_deptName').html("Department Name is Required");
		checkValid = false;
	}

	if (!notAllowedSpecialCharacters(textVal)) {
		$('#err_deptName').html("special characters not allowed.");
		checkValid = false;
	}

	if (notAllowedNumbers(textVal)) {
		$('#err_deptName').html("Numbers are not allowed.");
		checkValid = false;
	}

	/*
	 * if (test1()) { alert("test1 called"); $('#err_deptName').html("Department
	 * Name Already Exist"); } else {
	 * 
	 * alert("duplicate"); }
	 */

	if (isOrganizationFlow) {
		if (isCenterUser) {
			var ministryidvar = document.getElementById("ministryId");
			if (ministryidvar != null && typeof ministryidvar != 'undefined') {
				if (ministryidvar.options[ministryidvar.selectedIndex].value == "") {
					$('#err_ministry').html("List of Ministries is Required.");
					checkValid = false;
				}
			}

		}

		var deptOrgCodevar = document.getElementById("deptOrgCode");
		if (deptOrgCodevar != null && typeof deptOrgCodevar != 'undefined') {
			if ((deptOrgCodevar.options[deptOrgCodevar.selectedIndex].value == "")
					|| deptOrgCodevar.options[deptOrgCodevar.selectedIndex].value == "") {
				$('#err_dept').html("List of Department is Required.");
				checkValid = false;
			}
		}
		var orgTypevar = document.getElementById("orgType");
		if (orgTypevar != null && typeof orgTypevar != 'undefined') {
			if (orgTypevar.options[orgTypevar.selectedIndex].value == "") {
				$('#err_org').html("List of Orginazation is Required.");
				checkValid = false;
			}
		}
	} else {
		if (isCenterUser) {
			var ministryidvar = document.getElementById("ministryId");
			if (ministryidvar != null && typeof ministryidvar != 'undefined') {
				if (ministryidvar.options[ministryidvar.selectedIndex].value == "") {
					$('#err_ministry').html("List of Ministries is Required.");
					checkValid = false;
				}
			}

		}

	}

	var chooseLevel = $('input[name=chooseLevelAllOrSpecific]:radio:checked').val();
	if (typeof chooseLevel != 'undefined') {

		if (chooseLevel == "S") {

			if (isCenterUser) {
				if ($.inArray(accessLevel, arrAccessLevesCenter) > -1) {
					if (accessLevel == _STATE_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked')) {
							$("#err_statechk").html("At Least One State Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _DISTRICT_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked') && $("#ChkPartialCoverage").is(':unchecked')) {
							$("#err_statechk").html("At Least One District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _SUBDISTRICT_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked') && $("#districtChkFull").is(':unchecked')
								&& $("#DistrictChkPart").is(':unchecked')) {
							$("#err_statechk").html("At Least One Sub-District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _BLOCK_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#DistrictChkPart").is(':unchecked')) {
							$("#err_statechk").html("At Least One Block Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _VILLAGE_LEVEL) {
						if ($("#ChkFullCoverage").is(':unchecked') && $("#districtChkFull").is(':unchecked')
								&& $("#SubDistrictChkFull").is(':unchecked') && $("#SubDistrictChkPart").is(':unchecked')) {
							$("#err_statechk").html("At Least One Village Should be Selected");
							checkValid = false;
						}
					}
				}
			} else {
				if ($.inArray(accessLevel, arrAccessLevesState) > -1) {
					if (accessLevel == _DISTRICT_LEVEL) {
						if ($("#districtChkFull").is(':unchecked')) {
							$("#err_districtchk").html("At Least One District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _SUBDISTRICT_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#DistrictChkPart").is(':unchecked')) {
							$("#err_districtchk").html("At Least One Sub-District Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _BLOCK_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#DistrictChkPart").is(':unchecked')) {
							$("#err_districtchk").html("At Least One Block Should be Selected");
							checkValid = false;
						}
					}
					if (accessLevel == _VILLAGE_LEVEL) {
						if ($("#districtChkFull").is(':unchecked') && $("#SubDistrictChkFull").is(':unchecked')
								&& $("#SubDistrictChkPart").is(':unchecked')) {
							$("#err_districtchk").html("At Least One Village Should be Selected");
							checkValid = false;
						}
					}
				}
			}
			if (accessLevel == _ADMINISTRATIVE_LEVEL) {
				if (!isCenterUser) {
					var ddparent5 = document.getElementById('ddTargetAdminEnitiy');
					if (ddparent5.options.length == 0) {
						$("#err_Administrativeblk").html("Administrative must be filled");
						checkValid = false;
					}
				}
			}
			if ("XYZ".includes(accessLevel)) {
				if (!isCenterUser) {
					var ddparent5 = document.getElementById('ddTargetLocalBodyEnitiy');
					if (ddparent5.options.length == 0) {
						$("#err_LocalBodyblk").html("Local Body must be filled");
						checkValid = false;
					}
				}
			}
			if (accessLevel == _STATE_LEVEL) {
				if (isCenterUser) {
					var ddparent5 = document.getElementById('ddTargetState');

					if (ddparent5.options.length == 0) {
						$("#err_state").html("State must be filled");
						checkValid = false;
					}
				}
			}

			if (accessLevel == _DISTRICT_LEVEL) {
				if (isCenterUser) {
					if ($("#ChkFullCoverage").is(':checked')) {
						var ddparent5 = document.getElementById('ddTargetState');
						if (ddparent5.options.length == 0) {
							$("#err_state").html("State must be filled");
							checkValid = false;
						} else if (ddparent5.value <= 0) {
							$("#err_state").html("Kindly select any State");
							checkValid = false;
						}
					}
					if ($("#ChkPartialCoverage").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
						if (ddparent1.value <= 0) {
							$("#err_district").html("Kindly select any District");
							checkValid = false;
						}
					}
				} else {
					var ddparent1 = document.getElementById('ddTargetDistrict');
					if (ddparent1.options.length == 0) {
						$("#err_district").html("District must be filled");
						checkValid = false;
					}
					if (ddparent1.value <= 0) {
						$("#err_district").html("Kindly select any District");
						checkValid = false;
					}
				}
			}

			if (accessLevel == _SUBDISTRICT_LEVEL) {
				if (isCenterUser) {
					if ($("#ChkFullCoverage").is(':checked')) {
						var ddparent5 = document.getElementById('ddTargetState');
						if (ddparent5.options.length == 0) {
							$("#err_state").html("state must be filled");
							checkValid = false;
						}
						if (ddparent5.value <= 0) {
							$("#err_state").html("Kindly select any State");
							checkValid = false;
						}
					}
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
						if (ddparent1.value <= 0) {
							$("#err_district").html("Kindly select any District");
							checkValid = false;
						}
					}
					if ($("#DistrictChkPart").is(':checked')) {
						var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
						if (ddparent2.options.length == 0) {
							$("#err_subDistrict").html("Sub-District must be filled");
							checkValid = false;
						}
						if (ddparent2.value <= 0) {
							$("#err_subDistrict").html("Kindly select any Sub-District");
							checkValid = false;
						}
					}
				} else {
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
						if (ddparent1.value <= 0) {
							$("#err_district").html("Kindly select any District");
							checkValid = false;
						}
						if ($("#DistrictChkPart").is(':checked')) {
							var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
							if (ddparent2.options.length == 0) {
								$("#err_subDistrict").html("Sub-District must be filled");
								checkValid = false;
							}
							if (ddparent2.value <= 0) {
								$("#err_subDistrict").html("Kindly select any Sub-District");
								checkValid = false;
							}
						}
					}
				}
			}
			if (accessLevel == _BLOCK_LEVEL) {
				if ($("#districtChkFull").is(':checked')) {
					var ddparent1 = document.getElementById('ddTargetDistrict');
					if (ddparent1.options.length == 0) {
						$("#err_district").html("District must be filled");
						checkValid = false;
					}
				}
				if ($("#DistrictChkPart").is(':checked')) {
					var ddparent3 = document.getElementById('ddTargetBlockSDLvl');
					if (ddparent3.options.length == 0) {
						$("#err_block").html("Block must be filled");
						checkValid = false;
					}
				}
			}

			if (accessLevel == _VILLAGE_LEVEL) {
				if (isCenterUser) {
					if ($("#ChkFullCoverage").is(':checked')) {
						var ddparent5 = document.getElementById('ddTargetState');
						if (ddparent5.options.length == 0) {
							$("#err_state").html("State must be filled");
							checkValid = false;
						}
					}
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkFull").is(':checked')) {
						var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
						if (ddparent2.options.length == 0) {
							$("#err_subDistrict").html("Sub-District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkPart").is(':checked')) {
						var ddparent4 = document.getElementById('ddTargetDistrictVillLvl');
						if (ddparent4.options.length == 0) {
							$("#err_village").html("Village must be filled");
							checkValid = false;
						}
					}
				} else {
					if ($("#districtChkFull").is(':checked')) {
						var ddparent1 = document.getElementById('ddTargetDistrict');
						if (ddparent1.options.length == 0) {
							$("#err_district").html("District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkFull").is(':checked')) {
						var ddparent2 = document.getElementById('ddTargetDistrictSDLvl');
						if (ddparent2.options.length == 0) {
							$("#err_subDistrict").html("Sub-District must be filled");
							checkValid = false;
						}
					}
					if ($("#SubDistrictChkPart").is(':checked')) {
						var ddparent4 = document.getElementById('ddTargetDistrictVillLvl');
						if (ddparent4.options.length == 0) {
							$("#err_village").html("Village must be filled");
							checkValid = false;
						}
					}
				}
			}
		}

	}
	return checkValid;
};

function getCheckBoxValues(name){
	chkentity=false;
    $('[name='+name+']').each( function (){
        if($(this).prop('checked') == true){
        	chkentity=true;
        }
    });
    return chkentity;
}