function open_win() {

	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '',
			"dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");

}
function getData() {
	var errors = new Array();
	var error = false;
	errors[0] = !validatePc();
	errors[1] = !validatePcEnglishName();
	for ( var i = 0; i < 2; i++) {

		if (errors[i] == true) {

			error = true;
			break;
		}
	}

	if (error == true) {

		showClientSideError();

		return false;
	} else {
		document.forms['form1'].submit();

		return true;

	}
	return false;
}

function validatePc() {

	var ddDistrict = document.getElementById('ddDistrict');

	if (ddDistrict.selectedIndex == 0) {
		$("#ddDistrict_error").show();
		return false;
	}
	$("#ddDistrict_error").hide();
	return true;
}

function validatePcEnglishName() {

	var ddSubdistrict = document.getElementById('ddSubdistrict');

	if (ddSubdistrict.selectedIndex == 0) {
		$("#ddSubdistrict_error").show();
		return false;
	}
	$("#ddSubdistrict_error").hide();
	return true;
}
function getSubdistrictCode() {
	lgdDwrVillageService.getSubdistrict({
		async : false,
		callback : handleSubDistrictDraftSuccess,
	});
}

function getSubDistrictList(id) {

	remove_error2();
	lgdDwrSubDistrictService.getSubDistrictList(id, {
		async : false,
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});
}

function getVillageListCodes() {
	lgdDwrVillageServiceForDraftVillageList.getSelectedVillages({
		async : false,
		callback : handleVillageListDraftSuccess,
	});
}

function getSubdistrictInformation(value) {
	getVillageList(value);
	if (document.getElementById('villageListMain').size > 0) {
		document.getElementById('chkcvillage').checked = true;
		document.getElementById('chknothing').checked = false;
		toggledisplay("chkcvillage", "cvillage");
		getVillageListCodes();
	}
}

function handleSubDistrictDraftSuccess(data) {
	document.getElementById('ddSubdistrict').value = data;
	getSubdistrictInformation(data);
}

function handleVillageListDraftSuccess(data) {
	var list = "" + data;
	setTimeout("repopulateSelectedVillages('" + list + "')", 1000);

}

function callGetDraft() {
	document.getElementById('form1').action = "publishSaveAsDraftVillage.htm";
	document.forms['form1'].submit();
}

function getDraftData() {
	if (document.getElementById('ddDistrict').selectedIndex > 0) {
		getSubDistrictandULBList(document.getElementById('ddDistrict').value);
		getSubdistrictCode();

		var surveyNumbers = document.getElementById('surveyNumber').value.split(',');
		document.getElementById('surveyNumber').value = surveyNumbers[0];

		for ( var i = 1; i < surveyNumbers.length; i++)
			addsurveys();
		for ( var i = 1; i < surveyNumbers.length; i++)
			document.getElementsByName('surveyNumber').item(i).value = surveyNumbers[i];

		var gisLatitudes = document.getElementsByName('latitude').item(0).value.split(',');
		var gisLongitudes = document.getElementsByName('longitude').item(0).value.split(',');

		for ( var i = 1; i < gisLatitudes.length; i++)
			addgisnodes();

		for ( var i = 0; i < gisLatitudes.length; i++) {
			document.getElementsByName('latitude').item(i).value = gisLatitudes[i];
			document.getElementsByName('longitude').item(i).value = gisLongitudes[i];
		}

	}

}
function hideAll() {

	setButton(true);
	$("#ddDistrict_error").hide();
	$("#ddSubdistrict_error").hide();

}

function getVillageList(id) {
	if (id == 0)
		setButton(true);
	else
		setButton(false);
	lgdDwrVillageService.getVillageList(id, {
		asyn : false,
		callback : handleVillageSuccess,
		errorHandler : handleVillageError
	});

}

// data contains the returned value
function handleVillageSuccess(data) {
	var fieldId = 'villageListMain';
	var valueText = 'villageCode';
	var nameText = 'villageNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}

function handleVillageError() {
	alert("No data found!");
}

function getDetails() {
	hideAllOption();
	document.getElementById("errorentity").innerHTML = "";
	var e = document.getElementById('entity').value;
	var lbtype = e.split(":");
	var val = lbtype[0];
	document.getElementById('DR').style.display = 'none';
	document.getElementById('TR').style.display = 'none';
	document.getElementById('VPAN').style.display = 'none';
	document.getElementById('IPAN').style.display = 'none';
	document.getElementById('ADMINLEVEL').style.display = 'none';
	document.getElementById('DEPT').style.display = 'none';
	document.getElementById('deptLI').style.display = 'none';
	document.getElementById('ORG').style.display = 'none';
	document.getElementById('ORGUNIT').style.display = 'none';
	
	if (e == "subdistrict" || e == "block") {
		document.getElementById('DR').style.visibility = 'visible';
		document.getElementById('DR').style.display = 'block';
	} else if (e == "village") {
		document.getElementById('TR').style.visibility = 'visible';
		document.getElementById('TR').style.display = 'block';

	} else if (val == "2") {
		document.getElementById('IPAN').style.visibility = 'visible';
		document.getElementById('IPAN').style.display = 'block';

	} else if (val == "3") {
		document.getElementById('VPAN').style.visibility = 'visible';
		document.getElementById('VPAN').style.display = 'block';

	}else if(val=="adminEntity"){
		document.getElementById('ADMINLEVEL').style.visibility = 'visible';
		document.getElementById('ADMINLEVEL').style.display = 'block';
		getAdminUnitLevel();
	
	}else if(val=="dept"){
	document.getElementById('DEPT').style.visibility = 'visible';
	document.getElementById('DEPT').style.display = 'block';
	
	}
	else if(val=="org"){
		document.getElementById('deptLI').style.visibility = 'visible';
		document.getElementById('deptLI').style.display = 'block';
		/*document.getElementById('ORG').style.visibility = 'visible';
		document.getElementById('ORG').style.display = 'block';*/
		fillDeptList();
		}
	else if(val=="orgunit"){
		
		document.getElementById('ORGUNIT').style.visibility = 'visible';
		document.getElementById('ORGUNIT').style.display = 'block';
		
		}
	

}

function getDetails_district() {
	document.getElementById("errorentity").innerHTML = "";
	var e = document.getElementById('entity').value;
	document.getElementById('DTR').style.display = 'none';
	document.getElementById('DISIPAN').style.display = 'none';
	document.getElementById('DISVPAN').style.display = 'none';
	var lbtype = e.split(":");
	var val = lbtype[0];

	if (e == "villageDistUser") {
		document.getElementById('DTR').style.visibility = 'visible';
		document.getElementById('DTR').style.display = 'block';

	} else if (val == "2") {
		document.getElementById('DISIPAN').style.visibility = 'visible';
		document.getElementById('DISIPAN').style.display = 'block';

	} else if (val == "3") {
		document.getElementById('DISVPAN').style.visibility = 'visible';
		document.getElementById('DISVPAN').style.display = 'block';

	}

}

function remove_error4() {
	document.getElementById("errorsubdistrict").innerHTML = "";
}

function validateAllforDistrict() {
	var flag = true;
	var e = document.getElementById('entity').value;
	// alert(":"+e+":");
	if (e == "" || e == "0") {
		document.getElementById("errorentity").innerHTML = "<font color='red'><br>Please Select a Standard Entity to Update its Standard Codes</font>";
		document.getElementById("entity").focus();
		flag = false;
	}

	else if (e == "villageDistUser") {
		if (document.getElementById('ddSubdistrict').value == "" || document.getElementById('ddSubdistrict').value == "0") {
			document.getElementById("errorsubdistrict").innerHTML = "<font color='red'><br>Select One of Subdistrict<spring:message code='error.select.DISTRICTFRMDROPDWN'/></font>";
			document.getElementById("ddSubdistrict").focus();
			flag = false;
		}
	}
	 else {
		lbtype = e.split(":");
		val = lbtype[0];
		if (val == 2) {
			if (document.getElementById('distleveldispanchayat').value == "" || document.getElementById('distleveldispanchayat').value == "0") {
				alert("Please Select District Panchayat");
				flag = false;
			}
		} else if (val == 3) {
			if (document.getElementById('distleveldispanchayatforvp').value == ""
					|| document.getElementById('distleveldispanchayatforvp').value == "0") {
				alert("Please Select District Panchayat");
				flag = false;
			} else if (document.getElementById('distlevelvilpanchayats').value == ""
					|| document.getElementById('distlevelvilpanchayats').value == "0") {
				alert("Please Select Intermediate Panchayat");
				flag = false;
			}
		}
	}
	
	return flag;
}

function validateAll() {
	var flag = true;
	var e = document.getElementById('entity').value;
	var lbtype = "";
	var val = "";
	if (e == "" || e == "0") {
		document.getElementById("errorentity").innerHTML = "<font color='red'><br>Please Select a Standard Entity to Update its Standard Codes</font>";
		document.getElementById("entity").focus();
		flag = false;
	}

	else if (e == "subdistrict" || e == "block") {
		if (document.getElementById('ddDistrict').value == "" || document.getElementById('ddDistrict').value == "0") {
			document.getElementById("errordistrict").innerHTML = "<font color='red'><br>Select One of District<spring:message code='error.select.DISTRICTFRMDROPDWN'/></font>";
			document.getElementById("ddDistrict").focus();
			flag = false;
		}
	}

	else if (e == "village") {
		if (document.getElementById('ddDistrict2').value == "" || document.getElementById('ddDistrict2').value == "0") {
			document.getElementById("errordistrict1").innerHTML = "<font color='red'><br>Select One of District<spring:message code='error.select.DISTRICTFRMDROPDWN'/></font>";
			document.getElementById("ddDistrict2").focus();
			flag = false;
		} else if (document.getElementById('ddSubdistrict').value == "" || document.getElementById('ddSubdistrict').value == "0") {
			document.getElementById("errorsubdistrict1").innerHTML = "<font color='red'><br>Select One of Subdistrict<spring:message code='error.select.DISTRICTFRMDROPDWN'/></font>";
			document.getElementById("ddSubdistrict").focus();
			flag = false;
		}
	}else if (e == "adminEntity") {
		flag=validateAdminEntity();
	
	
	}else if(e=="org" || e=="dept"){
		if(e=="org" ){
			flag=validateDeptList();
		}
		
		flag=validateOrganization(e);
		
	}else if (e == "orgunit") {
		flag=validateOrgUnits();
	
	
	}
	
	
	else {
		lbtype = e.split(":");
		val = lbtype[0];
		if (val == 2) {
			if (document.getElementById('dispanchayat').value == "" || document.getElementById('dispanchayat').value == "0") {
				alert("Please Select District Panchayat");
				flag = false;
			}
		} else if (val == 3) {
			if (document.getElementById('dispanchayatforvp').value == "" || document.getElementById('dispanchayatforvp').value == "0") {
				alert("Please Select District Panchayat");
				flag = false;
			} else if (document.getElementById('vilpanchayat').value == "" || document.getElementById('vilpanchayat').value == "0") {
				alert("Please Select Intermediate Panchayat");
				flag = false;
			}
		}
	}
	return flag;
}

function remove_error1() {
	document.getElementById("errordistrict").innerHTML = "";
}

function remove_error2() {
	document.getElementById("errordistrict2").innerHTML = "";
	document.getElementById("errorsubdistrict").innerHTML = "";
	/*added by pooja on 12-06-2015*/
	document.getElementById("errordistrict1").innerHTML = "";
	document.getElementById("errorsubdistrict1").innerHTML = "";
}

function getIntermediatePanchayatbyDcode(id) {
	var tier = document.getElementById('Tiertype').value;
	if (tier == 3)
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : IntermediatePanchayatSuccessforVP,
			errorHandler : handleIntermediatePanchayatError
		});
}

function IntermediatePanchayatSuccessforVP(data) {
	var fieldId = 'vilpanchayat';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('vilpanchayat');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleIntermediatePanchayatError() {
	alert("No data found!");
}
function getIPcodeforDistrictUser(id) {
	var tier = document.getElementById('Tiertype').value;
	if (tier == 3)
		lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
			callback : IPcodeforDistrictUser,
			errorHandler : handleIntermediatePanchayatError
		});
}

function IPcodeforDistrictUser(data) {
	var fieldId = 'distlevelvilpanchayats';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('distlevelvilpanchayats');
	st.add(new Option('Select Intermediate Panchayat', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

getAdminUnitLevel=function(){
	removeAllOptions('parentAdminUnitLevel');
	if(!$_checkEmptyObject(_state_code)){
	
		lgdDwrOrganizationService.getDeptAdminUnits(_state_code, _type, {
			callback : function( result ) {
					var options = $("#adminUnitLevelforEntity"); 
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						
						option.val(obj.adminUnitCode).text(obj.adminLevelNameEng);
						options.append(option);
					});
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
	}

	
};


getParentAdminUnitLevel=function(adminLevelCode){
	removeAllOptions('parentAdminUnitLevel');
	if(!$_checkEmptyObject(adminLevelCode)){
		$( "#erradminUnitLevelforEntity" ).text("");
		
		if(_entity_type=="D"){
			lgdDwrOrganizationService.getUnitLevelNamesForLocalBodyDistrictUser(adminLevelCode, _entity_code, {
				callback : function( result ) {
					var options = $("#parentAdminUnitLevel"); 
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.parentAdminCode).text(
									obj.adminLevelNameEng + " (" + obj.parentAdminCode + ")");
							options.append(option);
						} else {
							$(option).val(obj.parentAdminCode).text(
									obj.adminLevelNameEng + " (" + obj.parentAdminCode + ")");
							options.append(option);
						}
					});
					
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true	
						
			});
		}
		else{
			lgdDwrOrganizationService.getUnitLevelNamesForLocalBody(adminLevelCode, _entity_code, {
				callback : function( result ) {
						var options = $("#parentAdminUnitLevel"); 
						var option = $("<option/>");
						$(option).val("").text("Select");
						options.append(option);
						$.each(result, function(key, obj) {
							var option = $("<option/>");
							if (obj.operation_state == 'F') {
								$(option).attr('disabled', 'disabled');
								$(option).val(obj.parentAdminCode).text(
										obj.adminLevelNameEng + " (" + obj.parentAdminCode + ")");
								options.append(option);
							} else {
								$(option).val(obj.parentAdminCode).text(
										obj.adminLevelNameEng + " (" + obj.parentAdminCode + ")");
								options.append(option);
							}
						});
						
					},
					errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
					async : true
				});	
		}
		
	}
	
			
};

/* The {@code $_checkEmptyObject} used to check object / element  
* is empty or undefined.
*/
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

/* The {@code removeAllOptions} used to clear drop down box based on their id. 
*/
removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};

validateAdminEntity=function(){
	$( "span[id^=err]" ).each(function(){
		$( this ).text("");
    });
	var flag=true; 
	var adminUnitLevel=$("#adminUnitLevelforEntity");
	 if($_checkEmptyObject($(adminUnitLevel).val())){
		 $( '#err' + $(adminUnitLevel).attr('id') ).text("Select Administrative unit Level");
		 flag=false;
	 }
	 var parentAdminUnitLevel=$("#parentAdminUnitLevel");
	 if($_checkEmptyObject($(parentAdminUnitLevel).val())){
		 $( '#err' + $(parentAdminUnitLevel).attr('id') ).text("Select Parent Administrative Unit Level");
		 flag=false;
	 }
	 
	 return flag;
	 
};

removeError=function(){
	$( "#errparentAdminUnitLevel" ).text("");
};


getCategoryWisesShowData=function(category,e){
	
	
	if(e=="dept"){
		$( '#errcategoryOptionDept' ).text("");
	}else{
		$( '#errcategoryOption' ).text("");
	}
	hideAllOption();
	if(!$_checkEmptyObject(category)){
		removeAllPopulateList();
		var selOption=category;
		if(category=="S"){
			$("#category").val(selOption);
		}
		else if(selOption.indexOf("@")!=-1){
			var hierarchySet=selOption.split("@")[1];
			hierarchyStr=hierarchySet;
			stateChoice=selOption.split("@")[0];
			$("#category").val(stateChoice);
			//alert(stateChoice);
			$.each(hierarchySet.split('#'), function(index,val) {
				
				$("#"+val+"LI").show(); 
				var options = $("#"+val);
				
				//alert(val);
				if(val=="district"){
					$("#"+val+" option[value='']").attr("selected", "selected");
				}
				if(!(val=="district" || category=="A")){
					var option = $("<option/>");
					$(option).val("-1").text("SELECT");
					options.append(option);
				}
				
				
				//removeAllOptions(val);
				});
			
			if(stateChoice=="A"){
				fillAdminUnitLevel();
			}
			
		}
	}
	
};

hideAllOption=function(){
	//$("#stateLI").hide(); 
	$("#districtLI").hide(); 
	$("#subdistrictLI").hide(); 
	$("#villageLI").hide(); 
	$("#blockLI").hide(); 
	$("#adminUnitLevelLI").hide(); 
	$("#parentUnitEntityLI").hide(); 
	removeAllOptions('parentUnitEntity');
	
	
};

removeAllPopulateList=function(){
		$("#district option[value='-1']").attr("selected", "selected");
		
		dwr.util.removeAllOptions('subdistrict');
		//dwr.util.removeAllOptions('state');
		dwr.util.removeAllOptions('block');
		dwr.util.removeAllOptions('village');
		
	};
	
	
	
	changeDistrict=function(districtCode){
		$( '#errdistrict' ).text("");
		
		if(!$_checkEmptyObject(districtCode)){
			removeAllPopulateList();
			if(stateChoice=="B"){
				fillBlockList(districtCode);
			}else {
				fillSubdistrictList(districtCode);
			}
		}
	};
	
	fillSubdistrictList=function(districtCode){
		removeAllOptions('subdistrict')
		lgdDwrSubDistrictService.getSubDistrictList(districtCode, {
				callback : function(result) {
					
					var options = $("#subdistrict");
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
						options.append(option);
						
					});
				},
				async : true
			});		 
	};
	
	
	fillBlockList=function(districtCode){
		removeAllOptions('block')
		lgdDwrBlockService.getBlockList(districtCode, {
				callback : function(result) {
					
					var options = $("#block");
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.blockCode).text(obj.blockNameEnglish);
						options.append(option);
						
					});
				},
				async : true
			});		 
	};
	
	fillVillageList=function(subdistrictCode){
		$( '#errsubdistrict' ).text("");
		removeAllOptions('village')
		lgdDwrVillageService.getVillageListbySubDistrictCodeCov(subdistrictCode, {
				callback : function(result) {
					
					var options = $("#village");
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.villageCode).text(obj.villageNameEnglish);
						options.append(option);
						
					});
				},
				async : true
			});		 
	};
	
	
	fillAdminUnitLevel=function(){
		removeAllOptions('adminUnitLevel')
		lgdAdminDepatmentDwr.getAdminUnitLevelList(_state_code, {
				callback : function(result) {
					
					var options = $("#adminUnitLevel");
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.adminUnitCode).text(obj.adminLevelNameEng);
						options.append(option);
						
					});
				},
				async : true
			});		 
	};
	
	fillParentUnitEntity=function(adminUnitLevelCode){
		removeAllOptions('parentUnitEntity')
		if(!$_checkEmptyObject(adminUnitLevelCode)){
			lgdAdminDepatmentDwr.getAdminUnitListbyStateCodeandLevel(_state_code,adminUnitLevelCode, {
				callback : function(result) {
					
					var options = $("#parentUnitEntity");
					var option = $("<option/>");
					$(option).val("").text("Select");
					options.append(option);
					$.each(result, function(key, obj) {
						var option = $("<option/>");
						(option).val(obj.adminUnitEntityCode).text(obj.adminEntityNameEnglish);
						options.append(option);
						
					});
				},
				async : true
			});		 
		}
		
		
	};
	
	
	validateOrganization=function(e)
	{
		var entity=$('#categoryOption');
		if(e=="dept"){
			 entity=$('#categoryOptionDept');
		}
		
		
		var selOption=entity.val();
		if(selOption!=""){
			if(selOption.indexOf("@")!=-1){
				var showHierarchy=selOption.split("@")[1];
			   var errorflag=true;
				$.each(showHierarchy.split('#'), function(index,obj) {
					var element=$("#"+obj).val();
					//alert(element);
					 if(element=="-1" || element=="" || element==null || element=="Select"){
						$("#"+obj).addClass("error");
						$("#err"+obj).text("Select " + obj);
						errorflag=false; 
					} 
					//alert(obj);
					});
				return errorflag;
			}
			
		}else{
			entity.addClass("error");
			$( '#errcategoryOption' ).text("Select Organization Level ");
			if(e=="dept"){
				$( '#errcategoryOptionDept' ).text("Select Department Level ");
			}
			return false;
		}
	};
	
	removeVilageError=function(){
		$( '#errvillage' ).text("");
	};

	removeBlockError=function(){
		$( '#errblock' ).text("");
	};
	
	fillDeptList=function(){
		removeAllOptions('deptList')
		lgdAdminDepatmentDwr.getDepartmentList(_state_code,2, {
			callback : function(result) {
				
				var options = $("#deptList");
				var option = $("<option/>");
				$(option).val("").text("Select");
				options.append(option);
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					(option).val(obj.orgCode).text(obj.orgName);
					options.append(option);
					
				});
			},
			async : true
		});		 
	};
	
	validateDeptList=function(){
		
		var deptList=$("#deptList");
		 if($_checkEmptyObject($(deptList).val())){
			 $( '#err' + $(deptList).attr('id') ).text("Select Department");
			 return false;
		 }
		 return true;

	}
	
	removedeptListError=function(){
		$( '#errdeptList' ).text("");
	};
	
	getTopLevelEntityByType=function(val){
		_org_type=val;
		removeAllOptions('orgCode');
		removeAllOptions('orgTypeCode');
		$( '#errtopLevelEntityType' ).text("");
		lgdAdminDepatmentDwr.getOrganizationbyCriteria(_state_code, val, {
			callback : function(result) {
				
				var options = $("#orgCode");
				var option = $("<option/>");
				$(option).val("").text("Select");
				options.append(option);
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					(option).val(obj.olc).text(obj.orgName);
					options.append(option);
					
				});
			},
			async : true
		});		 
	};
	
	getParentLevelEntity=function(unitCode){
		$( '#errorgCode' ).text("");
		removeAllOptions('orgTypeCode');
		lgdDwrOrganizationService.getOrgLocatedbyOrgCodemodify(unitCode, {
			callback : function(result) {
				
				if(_org_type==2){
				_org_type_name="Department";	
				}else{
					_org_type_name="Organization";
				}
				var options = $("#orgTypeCode");
				var option = $("<option/>");
				$(option).val("").text("Select");
				options.append(option);
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					switch(obj.locatedAtLevel){
					case 1:
						(option).val(1).text("State Level "+_org_type_name);
						break;
					case 2:
						(option).val(2).text("District Level "+_org_type_name);
						break;
					case 3:
						(option).val(3).text("SubDistrict Level "+_org_type_name);
						break;
					case 4:
						(option).val(4).text("Village Level "+_org_type_name);
						break;
					case 5:
						(option).val(5).text("Block Level "+_org_type_name);
						break;
					default:
						(option).val(obj.locatedAtLevel).text(obj.orgLevelSpecificName+" Level "+_org_type_name);
						break;
						
					}
					
					
					options.append(option);
					
				});
			},
			async : true
		});		 
	};
	
	getparentOrganizations=function(){
		$( '#errorgTypeCode' ).text("");
	};
	
	validateOrgUnits=function(){
		var _error_flag=true;
		var errorMsgArray=["Select Organization Type","Select Organization","Select Level  of Organization"];
		var orgOrgUnitListIdArray=["topLevelEntityType","orgCode","orgTypeCode"]
		 for(var i=0;i<orgOrgUnitListIdArray.length;i++){
				var orgElement=$( '#'+orgOrgUnitListIdArray[i] );
				if($_checkEmptyObject($( orgElement ).val())){
					_error_flag=false;
					$( orgElement ).addClass("error");
					$( '#err' + $( orgElement ).attr('id') ).text(errorMsgArray[i]);
				}
			}
		return _error_flag;
	};
	
	divShowOrgBlock=function(){
		document.getElementById('ORG').style.visibility = 'visible';
		document.getElementById('ORG').style.display = 'block';
		hideAllOption();
		categoryOption
		$("#categoryOption option[value='']").attr("selected", "selected");
	};
