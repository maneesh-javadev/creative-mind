function HideShowDisturblb() {
	var id = document.getElementById('ddLgdLBType').value;
	/*
	 * var type = document.getElementById('Tier').value;
	 * 
	 * if (type == 2) twoTier(id); else if (type == 3) threeTie ); else
	 */
	threeTier(id);
	filldsiturbedLbData();

}
function filldsiturbedLbData() {
	var type = document.getElementById('lbselectedtype').value;
	if (type == 2)
		selectoptiontwo();
	else if (type == 1)
		selectoptionone();
	else if (type == 3)
		selectoptionthree();

}
function selectoptionone() {

	setflag1();
	var temp = document.getElementById('invalidatedlbcode').value;
	$("#lblist1").val(temp).attr('selected', true);
	document.getElementById('ddLgdLBType').disabled = true;
	document.getElementById('lblist1').disabled = true;

}

function selectoptiontwo() {
	document.getElementById('IPlocalbody').style.visibility = 'visible';
	document.getElementById('IPlocalbody').style.display = 'block';
	var id = document.getElementById('ddlgdLBDistAtInter').value;
	setflag2();
	getlistofIpDisturbedstate(id);

}

function selectoptionthree() {
	var id = document.getElementById('ddlgdLBInterAtVillage').value;
	setflag3();
	getlistofgpdisturbed(id);

}
// //

// dev

function getlistofIpDisturbedstate(id) {

	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleInterPDisturbed,
		errorHandler : handleInterPanchayatICAError
	});
}

function handleInterPDisturbed(data) {

	document.getElementById('IPlocalbody').style.visibility = 'visible';
	document.getElementById('IPlocalbody').style.display = 'block';
	var fieldId = 'lblist2';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('lblist2');
	var type = document.getElementById('Tier').value;
	if (type == 2)
		st.add(new Option('Select Village Panchayat', '0'));
	else if (type == 3)
		st.add(new Option('Select Intermediate Panchayat', '0'));

	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0) {
		var temp = document.getElementById('invalidatedlbcode').value;
		$("#lblist2").val(temp).attr('selected', true);
		document.getElementById('ddLgdLBType').disabled = true;
		document.getElementById('ddlgdLBDistAtInter').disabled = true;
		document.getElementById('lblist2').disabled = true;
	}
}
function getlistofgpdisturbed(id) {
	lgdDwrlocalBodyService.getchildlocalbodiesByParentcode(id, {
		callback : handleGPCASuccessdisturbed,
		errorHandler : handleInterPanchayatICAError
	});
}
function handleGPCASuccessdisturbed(data) {
	document.getElementById('gplocalbody').style.visibility = 'visible';
	document.getElementById('gplocalbody').style.display = 'block';
	var fieldId = 'lblist3';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById('lblist3');
	st.add(new Option('Select Gram Panchayat', '0'));

	dwr.util.addOptions(fieldId, data, valueText, nameText);
	if (data.length > 0) {
		var temp = document.getElementById('invalidatedlbcode').value;
		$("#lblist3").val(temp).attr('selected', true);
		document.getElementById('ddLgdLBType').disabled = true;
		document.getElementById('ddlgdLBDistrictAtVillage').disabled = true;
		document.getElementById('ddlgdLBInterAtVillage').disabled = true;
		document.getElementById('lblist3').disabled = true;
	}

}

// / UlB Scripts
function HideShowdisturblblist(id) {
	if (id != "" && id != "0") {
		var temp = id.split(":");
		var id1 = temp[0];
		var id2 = temp[1];
		document.getElementById('Districtlocalbody').style.visibility = 'visible';
		document.getElementById('Districtlocalbody').style.display = 'block';
		document.getElementById('ddLgdLBType').disabled = true;
		document.getElementById('lblist1').disabled = true;

		if (document.getElementById('ddlgdLBDistrictAtVillage').value != 0) {
			getWorkOnVillagePanchayatforDistrictP(document.getElementById('ddlgdLBDistrictAtVillage').value);
		}
		if (chkExist.checked) {
			getHideLocalBodyParentList(id, chkExist.checked);
		}
		if (chkLgdLBUnmapped.checked) {
			getHideUnmappedList(id, chkLgdLBUnmapped.checked);
		}
	}

}

function validateAndSubmitdisturblb() {

	document.forms['invalidateLocalBodyforURB'].submit();

}
