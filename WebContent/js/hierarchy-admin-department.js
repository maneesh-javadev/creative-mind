var selected = 0;
var id = 1;
var arr = [];
var arrLocalBody=['X','Y','Z'];
//var lrStateChilds = [ 2, 3, 4, 5 ];
//var lrDistrictChilds = [ 3, 4, 5 ];
//var lrSDorBlockChilds = [ 4 ];
var arrHaiVal=[1,2,3,4,5,6,7,8];
var haiRarId=0;
var parallelId=0;
var fristAcess=false;


removeAllOptions = function(elementId) {
	dwr.util.removeAllOptions(elementId);
};

callDWRMethod = function(adminId) {
	lgdAdminDepatmentDwr.getAdministrativeUnitLevel(parseInt(adminId), parseInt(_state_code), {
		callback : handleAdminUnitLevelsSuccess,
		errorHandler : handleAdminUnitLevelsError,
		async : true
	});
};

handleAdminUnitLevelsSuccess = function(categories) {
	$("#adminLevelNameLocal").children().remove("optgroup[id='adminUnits']");

	var optgroup = $('<optgroup>');
	optgroup.attr('id', 'adminUnits');
	optgroup.attr('label', 'Administrative Unit Levels');

	$.each(categories, function(i) {
		var option = $("<option></option>");
		option.val(categories[i].adminUnitCode);
		option.text(categories[i].adminLevelNameEng);
		optgroup.append(option);
	});
	$("#adminLevelNameLocal").append(optgroup);
};

handleAdminUnitLevelsError = function() {
	customAlert("An Error Has Occured");
};


getLocalBodyId=function(adminLevelValue){
	var localBodyId="u"+ adminLevelValue;
	var localBodylavel = document.getElementById(localBodyId);
	return localBodylavel;
};

setFormAccessLevel = function(adminLevelValue) {
	var pageAccess = "";
	//for local body
	if(adminLevelValue<0){  
		   var localBodylavel=getLocalBodyId(adminLevelValue);
		    if(adminLevelValue==-100){
		    	pageAccess = "C";
		    	haiRarId=8;
		    }else if (adminLevelValue == "1") {
				pageAccess = "1";
			
			} else if (localBodylavel.value=='D') {
				pageAccess = "X";
				haiRarId=2;
				
			} else if (localBodylavel.value=='I') {
				if(adminLevelValue<-3){
					pageAccess = "U";
				}else{
					pageAccess = "Y";
				}
				
				
				haiRarId=4;
			} else if (localBodylavel.value=='V') {
				pageAccess = "Z";
				haiRarId=6;
			}
	}
	//land Region
	else{
			
	 if (adminLevelValue == "1") {
				pageAccess = "S";
				haiRarId=1;
			} else if (adminLevelValue == "2") {
				pageAccess = "D";
				haiRarId=3;
			} else if (adminLevelValue == "3") {
				pageAccess = "T";
				haiRarId=5;
			} else if (adminLevelValue == "4") {
				pageAccess = "V";
				haiRarId=7;
				
			} else if (adminLevelValue == "5") {
				pageAccess = "B";
				haiRarId=5;
			} else {
				pageAccess = "A";
				haiRarId=0;
			 }
	}
	var accessMap =adminLevelValue + "|" + pageAccess;
	return accessMap;
};

buildHierarchy = function() {
	var adminLevelValue = $("#adminLevelNameLocal").val();
	if (adminLevelValue == "0") {
		customAlertFn("Please select a Administrative Unit Level.");
		return false;
	}
	var accessMap = setFormAccessLevel(adminLevelValue);

	if ($.inArray(accessMap, arr) > -1) {
		customAlertFn("Administrative Unit already exist in hierarchy.");
		return false;
	}

	/*if (validateLRParent(adminLevelValue)) {
		customAlert("Can't Add Parent Level under a Child Level.");
		return false;
	}*/

	/*if (adminLevelValue<0) {
		var isvalid=validateLocalBody(adminLevelValue);
		if(isvalid){
		customAlert("Can't Add Parent Level under a Child Level in Local Body");
		return false;
		}
	}*/
	if (validateHairarchay()) {
		
		return false;
	}
	/*
	 * Code Start for - Sub-district can't be added under Block and Block can't
	 * be added under Sub-district.
	 */

	/*if (accessMap.lastIndexOf('T') > -1) {
		var sub = setFormAccessLevel(5);
		if ($.inArray(sub, arr) > -1) {
			customAlert("Sub-district can't be added under Block.");
			return false;
		}
	} else if (accessMap.lastIndexOf('B') > -1) {
		var block = setFormAccessLevel(3);
		if ($.inArray(block, arr) > -1) {
			customAlert("Block can't be added under Sub-district.");
			return false;
		}
	}*/

	/* Code ends */
	var levelName = $("#adminLevelNameLocal option:selected").text();
	createHirarchy(levelName,adminLevelValue,accessMap);
};
createHirarchy=function(name,adminLevelValue,accessMap){
/*	if (adminLevelValue==0 && selected == 0) {

		var main = document.getElementById('browser');
		var parLi = document.createElement("li");
		parLi.id = id;
		
		parLi.setAttribute("value", adminLevelValue);

		var parSpan = document.createElement("span");
		parSpan.setAttribute("class", "folder");

		var checkbox = document.createElement("input");
		checkbox.type = "checkbox";
		checkbox.id = id;
		checkbox.setAttribute("onchange", "performCheck(" + id + ")");
		checkbox.setAttribute("style", "display : none;");

		var node = document.createTextNode(name);
		parSpan.appendChild(checkbox);
		parSpan.appendChild(node);
		parLi.appendChild(parSpan);
		main.appendChild(parLi);
		id++;
		selected = 0;

	} else*/ if (document.getElementById("chk").checked && selected == 0) {

		var main = document.getElementById('browser');
		var parLi = document.createElement("li");
		parLi.id = id;
		
		parLi.setAttribute("value", adminLevelValue);

		var parSpan = document.createElement("span");
		parSpan.setAttribute("class", "folder");

		var checkbox = document.createElement("input");
		checkbox.type = "checkbox";
		checkbox.id = id;
		checkbox.setAttribute("onchange", "performCheck(" + id + ")");
		checkbox.setAttribute("style", "display : none;");

		var node = document.createTextNode(name);
		parSpan.appendChild(checkbox);
		parSpan.appendChild(node);
		parLi.appendChild(parSpan);
		main.appendChild(parLi);
		id++;
		selected = 0;
		if(fristAcess)
		pushDataInArrayAndBindAdmin(accessMap,adminLevelValue)
		else
			fristAcess=true;
		
	} else if (document.getElementById("chk").checked && selected > 0) {

		var parent = document.getElementById(selected);
		var chUl = document.createElement("ul");
		var chLi = document.createElement("li");

		chLi.id = id;
		chLi.setAttribute("value", adminLevelValue);

		var checkbox = document.createElement("input");
		checkbox.type = "checkbox";
		checkbox.id = id;
		checkbox.setAttribute("onchange", "performCheck(" + id + ")");
		checkbox.setAttribute("style", "display : none;");

		var chSpan = document.createElement("span");
		chSpan.setAttribute("class", "folder");

		var chNode = document.createTextNode(name);
		chSpan.appendChild(checkbox);
		chSpan.appendChild(chNode);
		chLi.appendChild(chSpan);
		chUl.appendChild(chLi);
		parent.appendChild(chUl);

		id++;
		selected = 0;
		pushDataInArrayAndBindAdmin(accessMap,adminLevelValue)
	} else {

		customAlert("not allowed");
	}
	$('input[type=checkbox]').attr('checked', false);
	$('#chk').attr('checked', true);
	$("#browser").treeview();
	
	
};
function pushDataInArrayAndBindAdmin(accessMap,adminLevelValue){
	arr.push(accessMap);
	if (adminLevelValue<0){
		  var idex=accessMap.indexOf("|");
		  if(accessMap.substring(idex+1,idex+2)=="X")
			  callDWRMethod(-1);
		  else if(accessMap.substring(idex+1,idex+2)=="Y")
			  callDWRMethod(-2);
		  else if(accessMap.substring(idex+1,idex+2)=="Z")
			  callDWRMethod(-3);
		  else
			  callDWRMethod(adminLevelValue);
	}
	 
	else	
	callDWRMethod(adminLevelValue);
}
performCheck = function(id) {
	selected = id;
};

submitForm = function() {
	if (arr.length == 0) {
		customAlertFn("Please Select a Level")
		
	} else {
		
		return processForm();
	}
};

processForm = function() {
	$('#btnBuildHrchy').attr('disabled', 'disabled');
	$('#btnCreateDept').attr('disabled', 'disabled');
	$('#clear').attr('disabled', 'disabled');
	$('#close').attr('disabled', 'disabled');

	document.getElementById("hierarchySequence").value = arr;
	document.forms['adminOrgDeptForm'].method = "POST";
	document.forms['adminOrgDeptForm'].action = _process_url;
	document.forms['adminOrgDeptForm'].submit();
	return true;
};

/*validateLRParent = function(adminLevelValue) {
	var isParentExist = false;
	
	switch (Math.abs(adminLevelValue)) {
	case 1:
		// Check state level child
		isParentExist = checkParent(lrStateChilds);
		break;
	case 2:
		// Check district level child
		isParentExist = checkParent(lrDistrictChilds);
		break;
	case 3:
		// Check sub-district level child
		isParentExist = checkParent(lrSDorBlockChilds);
		break;
	case 5:
		// Check block level child
		isParentExist = checkParent(lrSDorBlockChilds);
		break;
	}
	return isParentExist;
};
*/
/*checkParent = function(childArray) {
	var isFound = false;
	for ( var i = 0; i < childArray.length; i++) {
		var childId = childArray[i];
		var childAccessLevel = setFormAccessLevel(childId);
		if ($.inArray(childAccessLevel, arr) > -1) {
			isFound = true;
			break;
		}
	}
	return isFound;
};*/
/*validateLocalBody = function(adminLevelValue) {
	
	   var localBodylavel=getLocalBodyId(adminLevelValue);
	   for(var i=arrLocalBody.length;i>=0;i--){
		   if(arrLocalBody[i]=="-1")
			   return true;
		   if(arrLocalBody[i]==localBodylavel.value){
			   arrLocalBody[i]="-1";
			   break;
		   }
	   }
		return false;
};*/

validateHairarchay = function() {
	   for(var i=arrHaiVal.length-1;i>=0;i--){
		    if(arrHaiVal[i]==-1 && haiRarId>0){
		    	if(parallelId==haiRarId)
		    		customAlertFn("You can't Add multiple Offices at same Level."); 
		    	else
		    		customAlertFn("You can't  select a Parent Level under a Child Level."); 
		    	 return true;
		    }
		    
		    if(haiRarId==8 && arrHaiVal.includes(-1) ){
		    	customAlertFn("You can't  select a Parent Level under a Child Level."); 
		    	 return true;
		    }
			  
		   if(arrHaiVal[i]==haiRarId){
			   arrHaiVal[i]=-1;
			   parallelId=haiRarId
			   break;
		   }
	   }
		return false;
};

customAlertFn=function(msg){
	$("#alertboxbody").html(msg);
	$('#alertbox').modal('show');	
}


