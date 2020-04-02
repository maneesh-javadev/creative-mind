function getTargetState(id)
{		

	lgdDwrStateService.getStateTargetList(id, {
		callback : handleStateSuccess,
		errorHandler : handleStateError
	});
		
}

// data contains the returned value
function handleStateSuccess(data) {
	// Assigns data to result id	
	
	var fieldId = 'ddDestState';
	var valueText = 'statePK.stateCode';
	var nameText = 'stateNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	
	dwr.util.addOptions(fieldId, data,valueText,nameText);
}

function handleStateError() {
	// Show a popup message
	alert("No data found!");
}

function getDistrictList(id){
	
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
}



function getSubDistrictList(id){
	alert(id);
	lgdDwrDistrictService.getDistrictList(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
}


function handleDistrictSuccess(data) {
	// Assigns data to result id	
	
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	
	dwr.util.addOptions(fieldId, data,valueText,nameText);
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}

//////////////////////////////////////////////////////////
	
//For sub district


function getSubDistrictList(id){
	
	lgdDwrDistrictService.getSubDistrictList(id, {
		callback : handleSubDistrictSuccess,
		errorHandler : handleSubDistrictError
	});
	
}


function handleSubDistrictSuccess(data) {
	// Assigns data to result id	
	
	var fieldId = 'ddSourceDistrict';
	var valueText = 'districtPK.districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	
	dwr.util.addOptions(fieldId, data,valueText,nameText);
}

function handleSubDistrictError() {
	// Show a popup message
	alert("No data found!");
}


////////////////////////////////////////
function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if(-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if(direction == 'up')
		increment = -1;
	else
		increment = 1;

	if((selIndex + increment) < 0 ||
		(selIndex + increment) > (listbox.options.length-1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
}
// For whole
/*function addItem(list1,list2,val,doAddVal)
{
	if(document.getElementById(list2).selectedIndex>=0)
		{
			if (doAddVal)
				$('#' + list1).append("<option value=" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].value +  val +  ">" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText + " (" +  val + ")</option>");
			else
				$('#' + list1).append("<option value=" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].value + " >" + document.getElementById(list2)[document.getElementById(list2).selectedIndex].innerText  + "</option>");
			removeSelectedValue(list2);	
		}
}*/
function listbox_moveacross(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for(var count=0; count < src.options.length; count++) {

		if(src.options[count].selected == true) {
				var option = src.options[count];
				check=true;
				var newOption = document.createElement("option");
				//newOption.value = option.value+"(Full)";
				//newOption.text = option.text+"(Full)";
				newOption.value = option.value;
				newOption.text = option.text;
				newOption.selected = true;
				try {
						 dest.add(newOption, null); //Standard
						 src.remove(count, null);
				 }catch(error) {
						 dest.add(newOption); // IE only
						 src.remove(count);
				 }
				count--;

		}
	
		
		

	}	
	
	if(check!=true)
	{
	alert("Please select valid district to shift");
	
	}
	check=false;
	//show_hide();

}





////////////////////////////////

//dwr.engine.setActiveReverseAjax(true);
  
//State and district list
  function getList(id)
   {	
	//alert("id "+id);	
	getDistrictList(id);
	}




function getVillageList(id)
{	
	
		getDistrictList(id);
}
		
// For part selection
function part(sourceID, destID) {
	var src = document.getElementById(sourceID);
	var dest = document.getElementById(destID);
	var check;
	for(var count=0; count < src.options.length; count++) {

		if(src.options[count].selected == true) {
				var option = src.options[count];
				check=true;
				var newOption = document.createElement("option");
	//newOption.value = option.value+"(Full)";
	//newOption.text = option.text+"(Full)";
	newOption.value = option.value;
	newOption.text = option.text;
	newOption.selected = true;
	try {
			 dest.add(newOption, null); //Standard
			 src.remove(count, null);
	 }catch(error) {
			 dest.add(newOption); // IE only
					 src.remove(count);
			 }
			count--;

	}

	
	

}





if(check!=true)
{
alert("Please select valid district to shift");

}
check=false;
//show_hide();

}
///vanisha
function addgisnodes()
{
	document.getElementById("addgisnodes").innerHTML += "<div width='100%'>" + document.getElementById('lbllatitude').innerText + "<input type='text' name='latitude'>" + document.getElementById('lbllongitude').innerText + "<input type='text' name='longitude'><input type='button' value='Remove' onclick='this.parentNode.parentNode.removeChild(this.parentNode)'></div>";
}
	

		
		