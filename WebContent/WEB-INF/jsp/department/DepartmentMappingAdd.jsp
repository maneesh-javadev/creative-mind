<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%@taglib uri="/esapi" prefix="esapi"%>
<%@taglib uri="/csrf" prefix="csrf"%> --%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Department Mapping - Add</title>
<!-- <link href="resources/css/panchayat_main.css" rel="stylesheet" type="text/css" /> -->
<!-- <script type="text/javascript" language="javascript" src="resources/js/jquery-1.9.1.js"></script>   -->

<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/util.js'></script>		
 <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
 <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
 
<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/businessService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<script type="text/javascript" src="datepicker/jquery-1.6.2.js"	charset="utf-8"></script>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/trim-jquery.js"></script> -->
<link rel="stylesheet" href="datepicker/demos.css" />
<style type="text/css">
	
fieldset {
	display: block;
	-webkit-margin-start: 2px;
	-webkit-margin-end: 2px;
	-webkit-padding-before: 0.35em;
	-webkit-padding-start: 0.75em;
	-webkit-padding-end: 0.75em;
	-webkit-padding-after: 0.625em;
	border: 2px groove threedface;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	min-width: -webkit-min-content;
}
legend {
	display: block;
	-webkit-padding-start: 2px;
	-webkit-padding-end: 2px;
	border: none;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
}
/* ul.my_listing li {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	vertical-align: top;
	margin-right: 2px;
	font-size: 12px;
	line-height: 16px;
     width: 20%;
	/* word-wrap:break-word; */
} */


</style>
<script type="text/javascript">


var lbTypeCategory = "";
var lbCode = "";
var totalfields = 0;
var totalFieldsFr = 0;
var nxtId = 0;
$(document).ready(function() {
	$('#mappingType').change(function() {
		if ($(this).val() == '') {
			alert("Select Mapping Type");
			$("#mappingDivID").hide();
		}else{
			toggleFromTo($(this).val());
			fixToMapping();
			$("#labelID").text($("#mappingType option:selected").text());
		}
	});
	$( "[name='mappedCoverageType']" ).change(function() {
		if($('input[name=mappedCoverageType]:checked').val() == "P"){
			$('#wardDiv').show();
		lgdDwrlocalBodyService.getWardListByLbCode(lbCode,{
				callback:showWardList,
				errorHandler : function() {
					alert("No record found.");
				},
				async:false
				});	
		}
		else{
			$('#wardDiv').empty();
			$('#wardDiv').hide();
		}
	});
	function showWardList(data){
		var div  = document.getElementById("wardDiv");
		var ul = document.createElement("ul");
		ul.setAttribute('class', "listing");
		var li1 = document.createElement("li");
		li1.innerHTML = "Select Wards";
		ul.appendChild(li1);
		var li2 = document.createElement("li");
		var cell = document.createElement("select");
		cell.style.width = "200px";
		cell.style.height = "150px";
		cell.setAttribute('id',"wardList");
		cell.setAttribute('name',"wardList");
		cell.multiple = "multiple";
		for ( var i = 0; i < data.length; i++) {
			var value = data[i].wardCode;
			var text = data[i].wardNameEnglish;
			var optn = document.createElement("OPTION");
			optn.text = text;
			optn.value = value;
			cell.options.add(optn);
			}
		li2.appendChild(cell);
		ul.appendChild(li2);
		div.appendChild(ul);
	}
	
	$('#toLocalBodySetup').change(function() {
		var selectVal = $(this).val();
		var stateID = parseInt(document.getElementById("stateId").value);
		$("#toTableID").find("ul:gt(2)").remove();
		$("#fromTableID").find("ul:gt(2)").remove();
		$("#IsLbMapped").hide();
		lbTypeCategory = $("#toLocalBodySetup option:selected").attr("paramCategory");
		if(selectVal == '') {
			alert("Select Part A local body type");
		}else{
			var codes = ['1','2','3'];
			if($.inArray(selectVal, codes) > -1){
				var selectedIdx = $('option:selected',$(this)).index();
				document.getElementById("to_selected_idx").value = selectedIdx;
				
				addDynamicToDropbox(selectedIdx, "G","");
				$("#id_to_level_1").show();
				
				lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(1,stateID,{
					callback:showToDistrictPanchayat,async:false});
				//LGDDataService.findDistrictPanchyatList(1,stateID,{callback:showToDistrictPanchayat,async:false});
			}else{
				
				var selectedText =  $('option:selected',$(this)).text();
				document.getElementById("to_selected_idx").value = 1;
				addDynamicToDropbox(1, "G", selectedText);
				$("#id_to_level_1").show();
				lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(parseInt(selectVal),stateID,{callback:showToDistrictPanchayat,async:false});
				//LGDDataService.findDistrictPanchyatList(parseInt(selectVal),stateID,{callback:showToDistrictPanchayat,async:false});
			}
			
			
		}
	});
	
	
	$('#fromLocalBodySetup').change(function() {
		var validateFlag = validatePartABeforePartB();
		if(validateFlag){		
			var selectVal = $(this).val();
			var stateID = parseInt(document.getElementById("stateId").value);
			$("#fromTableID").find("ul:gt(2)").remove();
			if(selectVal == '') {
				alert("Select Part B local body type");
			}else{
				var codes = ['2','3'];
				if($.inArray(selectVal, codes) > -1){
					var selectedIdx = $('option:selected',$(this)).index();
					document.getElementById("from_selected_idx").value = selectedIdx;
					
					addDynamicFromDropbox(selectedIdx, "G","");
					$("#id_from_level_1").show();
					
					lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(1,stateID,{callback:showFromDistrictPanchayat,async:false});
					//LGDDataService.findDistrictPanchyatList(1,stateID,{callback:showFromDistrictPanchayat,async:false});
				}else{
					var selectedText =  $('option:selected',$(this)).text();
					document.getElementById("from_selected_idx").value = 1;
					
					addDynamicFromDropbox(1, "G", selectedText);
					$("#id_from_level_1").show();
					
					var stateID = parseInt(document.getElementById("stateId").value);
					var mappingType = $("#mappingType").val();
					var selectVal = $("#fromLocalBodySetup").val();
					var toLevelTypeCode = 0;
					if((mappingType == 'OMO') || (mappingType == 'OMG') ){
						toLevelTypeCode = $("#toDepartmentLevel").val();
					}else if(mappingType == 'GMO'){
						toLevelTypeCode = $("#toLocalBodySetup").val();
					}
					document.getElementById("from_level_id").value = 0;
					
					lgdAdminDepatmentDwr.getUnmappedULBList(parseInt(selectVal),stateID,mappingType,"G",parseInt(toLevelTypeCode),{callback:showFromChildList,async:false});
				}
			}
		}else{
			$(this).val("");
		}
	});
	
	$('#toDepartment').change(function() {
		var selectVal = $(this).val();
		$("#toTableID").find("ul:gt(2)").remove();
		if(selectVal == '') {
			alert("Select Part A Line Department");
		}else{
			if(selectVal == $('#fromDepartment').val()){
				alert("Both Part A and Part B line department cannot be same");
				$('#toDepartment').val("");
			}else{
				$('#toDepartmentLevelID option[value!=""]').remove();
				$("#toDepartmentLevelID").show();
				orgCode = selectVal.split("-"); 
				lgdAdminDepatmentDwr.getOrganizationAtLevelsForDeptLBMapping(parseInt(orgCode[0]),{callback:showToDeptLevel,async:false});

				//lgdAdminDepatmentDwr.getOrganizationAtLevels(parseInt(orgCode[0]),{callback:showToDeptLevel,async:false});
				
			}
		}
	});
	
	$('#fromDepartment').change(function() {
		var validateFlag = validatePartABeforePartB();
		if(validateFlag){
			var selectVal = $(this).val();
			$("#fromTableID").find("ul:gt(2)").remove();
			if(selectVal == '') {
				alert("Select Part B Line Department");
			}else{
				if(selectVal == $('#toDepartment').val()){
					alert("Both Part A and Part B line department cannot be same");
					$('#fromDepartment').val("");
				}else{
					$('#fromDepartmentLevelID option[value!=""]').remove();
					$("#fromDepartmentLevelID").show();
					orgCode = selectVal.split("-"); 
					lgdAdminDepatmentDwr. getOrganizationAtLevels(parseInt(orgCode[0]),{callback:showFromDeptLevel,async:false});
					
				}
			}
		}else{
			$(this).val("");
		}
	});

	$('#toDepartmentLevel').change(function() {
		var selectVal = $(this).val();
		$("#toTableID").find("ul:gt(2)").remove();
		if(selectVal == '') {
			alert("Please Select");
		}else{
			var selectedIdx = $('option:selected',$(this)).index();
			document.getElementById("to_selected_idx").value = selectedIdx;
			lgdAdminDepatmentDwr.getUpperHierarchyLevel(parseInt(selectVal),{callback:showHierachyofDeptLevel,async:false});
		}
		//changed By Pranav 
								/* else{
									var selectedIdx = $('option:selected',$(this)).index();
									document.getElementById("to_selected_idx").value = selectedIdx;
									if(selectedIdx >= 1){	// changed by sachin (=)
										addDynamicToDropbox(selectedIdx, "O","");
										var val = $('#toDepartment').val().split("-");
											
										$("#id_to_level_1").show();
											
										var anOption = document.createElement("OPTION");
										document.getElementById("to_level_1").options.add(anOption);
										anOption.text = $('#toDepartment option:selected').text();
										anOption.value = val[1]; 
											
										document.getElementById("to_level_id").value = 1;
									
										getToChildList(val[1], "O", 1);
									}
								} */
	});
	
	  $('#fromDepartmentLevel').change(function() {
		var selectVal = $(this).val();
		$("#fromTableID").find("ul:gt(2)").remove();
		if(selectVal == '') {
			alert("Please Select");
		}else{
			var selectedIdx = $('option:selected',$(this)).index();
			document.getElementById("from_selected_idx").value = selectedIdx;
			lgdAdminDepatmentDwr.getUpperHierarchyLevel(parseInt(selectVal),{callback:showHierachyofFromDeptLevel,async:false});
		}
		
	}); 
	 
	  function showHierachyofFromDeptLevel(data){
		// Added By Pranav on 01 Nov 16
		
		var length=data.length;
		totalFieldsFr = length; 
		var listOfOrganization = data[0].list;
		for (var int = 0; int < length; int++) {
			
			var ullist="";
			var ullistend="";
			var category = "O";
			var ids = int+1;
			
			     var onChangeTxt = "getFromChildList1(this.value,'"+category+"',"+ids+")";	
			
			if(int==(length-1))
			     var ullist='<ul id= "ul_from_level_'+ids+'">'+data[int].levelName+'<li id= "liFR_level_'+ids+'"><select class=combofield  id= "selectFR_level_'+ids+'" style="width:350px"    name="from_level_'+ids+'" onchange="'+onChangeTxt+'">';
			else
				var ullist='<ul id= "ul_from_level_'+ids+'">'+data[int].levelName+'<li id= "liFR_level_'+ids+'"><select class=combofield   id= "selectFR_level_'+ids+'"  name="from_level_'+ids+'" onchange="'+onChangeTxt+'">';
			var optList='<option value = "0">--select--</option>';
			
			 for(var j = 0; j<listOfOrganization.length; j++){
				if(data[int].levelCode == listOfOrganization[j][2])
				optList+='<option value="'+listOfOrganization[j][0]+'">'+listOfOrganization[j][1]+'</option>';
				}
			
			ullistend=+'</select></li></ul>';
			$('#fromTableID ul').last().after(''+ullist+''+optList+''+ullistend+'');
		}
		
		    
	}
	  
	
	function showHierachyofDeptLevel(data){
		// Added By Pranav on 01 Nov 16
		
		var length=data.length;
		totalfields = length; 
		var listOfOrganization = data[0].list;
		for (var int = 0; int < length; int++) {
			
			var ullist="";
			var ullistend="";
			var category = "O";
			var ids = int+1;
			
			     var onChangeTxt = "getToChildList1(this.value,'"+category+"',"+ids+")";	
			
			if(int==(length-1))
			     var ullist='<ul id= "ul_to_level_'+ids+'">'+data[int].levelName+'<li id= "li_level_'+ids+'"><select class=combofield  id= "select_level_'+ids+'" style="width:350px"    name="to_level_'+ids+'" onchange="'+onChangeTxt+'">';
			else
				var ullist='<ul id= "ul_to_level_'+ids+'">'+data[int].levelName+'<li id= "li_level_'+ids+'"><select class=combofield   id= "select_level_'+ids+'"  name="to_level_'+ids+'" onchange="'+onChangeTxt+'">';
			var optList='<option value = "0">--select--</option>';
			
			 for(var j = 0; j<listOfOrganization.length; j++){
				if(data[int].levelCode == listOfOrganization[j][2])
				optList+='<option value="'+listOfOrganization[j][0]+'">'+listOfOrganization[j][1]+'</option>';
				}
			
			ullistend=+'</select></li></ul>';
			$('#toTableID ul').last().after(''+ullist+''+optList+''+ullistend+'');
		}
		
		    
	}
	
	
	
/* 	 $('#fromDepartmentLevel').change(function() {
		var validateFlag = validatePartABeforePartB();
		if(validateFlag){
			var selectVal = $(this).val();
			$("#fromTableID").find("ul:gt(2)").remove();
			if(selectVal == '') {
				alert("Please Select");
			}else{
				var selectedIdx = $('option:selected',$(this)).index();
				document.getElementById("from_selected_idx").value = selectedIdx;
				if(selectedIdx >= 1 ){  // changed by sachin (=)
					var val = $('#fromDepartment').val().split("-");
				    var flag = '';
				    if(selectedIdx == 1){
				    	flag = checkIfDeptMappingExists(selectVal, val[1]);
				    }else{
				    	flag = 'false';
				    }
				    if(flag == 'false' ){
					addDynamicFromDropbox(selectedIdx, "O", "");
					
					$("#id_from_level_1").show();
					
					var anOption = document.createElement("OPTION") ;
					document.getElementById("from_level_1").options.add(anOption) ;
					anOption.text = $('#fromDepartment option:selected').text();
					anOption.value = val[1]; 
					
					document.getElementById("from_level_id").value = 1;
					
					getFromChildList(val[1], "O", 1);
				    }else{
				    	alert("Mapping for this department already exists at this level.");
				    }
				}
			}
		}else{
			$(this).val("");
		}
	});  */
	
});

 function checkIfDeptMappingExists(fromLevelTypeCode, fromLevelCode){
	var stateID = parseInt(document.getElementById("stateId").value);
	var mappingType = $("#mappingType").val();
	var toLevelTypeCode = "";
	if(mappingType == 'OMO'){
		toLevelTypeCode = $("#toDepartmentLevel").val();
	}else if(mappingType == 'LMO'){
		toLevelTypeCode = $("#toLandRegion").val();
	}else{
		toLevelTypeCode = $("#toLocalBodySetup").val();
	}
	var fromLevelCategory = 'O';
	var flag = 'false';
	
	lgdAdminDepatmentDwr.checkIfDeptMappingExists(stateID, mappingType, parseInt(toLevelTypeCode), fromLevelCategory,
			parseInt(fromLevelTypeCode), parseInt(fromLevelCode),{callback:function checkMapping(data){
				if(data == true){
					flag = 'true';
				}
				}, async: false});
	return flag;
	
	
} 
 
 
function addDynamicToDropbox(level, category, labelTxt){
	$.each(new Array(level),function(cnt){
		var ids = parseInt(cnt) + 1;
		var labelText = "";
		if(labelTxt == ""){
			if(category == 'G'){
				labelText = "<li>" + $("#toLocalBodySetup option[value="+ids+"]").text() + "</li>";
			}else if(category == 'O'){
				labelText = "<li>" + $("#toDepartmentLevel option").eq(ids).text() + "</li>";
			}else if(category == 'L'){
				labelText = "<li>" + $("#toLandRegion option").eq(ids).text() + "</li>";
			}
		}else{
			labelText = "<li>" + labelTxt + "</li>";
		}
		
			var onChangeTxt = "getToChildList(this.value,'"+category+"',"+ids+")";
			$('#toTableID ul').last().after('<ul id="id_to_level_'+ids+'" style="display:none">'+labelText+'<li><select class="combofield" name="to_level_'+ids+'" id="to_level_'+ids+'" onchange="'+onChangeTxt+'" /></li></ul>');
		
		
	});
}

function removeDynamicToDropbox(id, selectedID){ 
	document.getElementById("to_level_id").value = id;
	while(parseInt(id) < parseInt(selectedID)){
		$("#id_to_level_"+parseInt(id+1)).find('option').remove();
		$("#id_to_level_"+parseInt(id+1)).hide();
		id++;
	}
}

function removeDynamicToComboFields(){ //added By Pranav
	var i = nxtId;
	while(i <= totalfields){
		 $('#select_level_'+i+'').remove();
		 i++;
	 }
}

 function removeDynamicFromComboFields(){ //added By Pranav
	var i = nxtId;
	while(i <= totalFieldsFr){
		 $('#selectFR_level_'+i+'').remove();
		 i++;
	 }
} 

function removeDynamicFromDropbox(id, selectedID){
	document.getElementById("from_level_id").value = id;
	while(parseInt(id) <= parseInt(selectedID)){
		$("#id_from_level_"+parseInt(id+1)).find('option').remove();
		$("#id_from_level_"+parseInt(id+1)).hide();
		id++;
	}
}

function addDynamicFromDropbox(level, category, labelTxt){
	$.each(new Array(level),function(cnt){
		var ids = parseInt(cnt) + 1;
		var multipleFlag = "";
	//	var div=$("<div/>");
		    //var stylflag = "";
		var labelText = "";
		if(labelTxt == ""){
			if(category == 'G'){
				labelText = "<li>" + $("#fromLocalBodySetup option[value="+ids+"]").text() + "</li>";
			}else if(category == 'O'){
				labelText = "<li>" + $("#fromDepartmentLevel option").eq(ids).text() + "</li>";
			}else if(category == 'L'){
				labelText = "<li>" + $("#fromLandRegion option").eq(ids).text() + "</li>";
			}
		}else{
			labelText = "<td>" + labelTxt + "</td>";
		}
			//var divstyle="";
			//var enddiv="";
			//var stylFlag = "";
		if(ids == level){
			multipleFlag = 'multiple="multiple"';
			//divstyle="<div style=border: 1px solid grey; overflow-x: scroll; height: 135px; width: 300px; overflow: -moz-scrollbars-horizontal; >"
			//enddiv="</div>";
			//stylFlag = 'size="9" style="width: 600px; background: none repeat scroll 0 0 #fbf9f0;"'
			var onChangeTxt = "getFromChildList(this.value,'"+category+"',"+ids+")";
			$('#fromTableID ul').last().after('<ul id="id_from_level_'+ids+'" style="display:none">'+labelText+'<li><select style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 500px; overflow: -moz-scrollbars-horizontal;"  name="from_level_'+ids+'" id="from_level_'+ids+'" '+multipleFlag+'  onchange="'+onChangeTxt+'"  /></li></ul>');
		
		}else{
			var onChangeTxt = "getFromChildList(this.value,'"+category+"',"+ids+")";
			$('#fromTableID ul').last().after('<ul id="id_from_level_'+ids+'" style="display:none">'+labelText+'<li><select class="combofield"  name="from_level_'+ids+'" id="from_level_'+ids+'" '+multipleFlag+'  onchange="'+onChangeTxt+'"  /></li></ul>');
		
		}
		});
}

function showToDistrictPanchayat(data){	
	var anOption = document.createElement("OPTION") ;
	document.getElementById("to_level_1").options.add(anOption) ;
	anOption.text = "Select";
	anOption.value = ""; 
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("to_level_1").options.add(anOption) ;
		anOption.text = data[key].localBodyNameInEnglish;
		anOption.value = data[key].localBodyCode;  		
	}
	document.getElementById("to_level_id").value = 1;
}

function showFromDistrictPanchayat(data){	
	if(document.getElementById("from_level_1").multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_1").options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_1").options.add(anOption) ;
		anOption.text = data[key].localBodyNameInEnglish;
		anOption.value = data[key].localBodyCode;  		
	}
	document.getElementById("from_level_id").value = 1;
}


function getToChildList1(val, category, ids){
				   var stateID = parseInt(document.getElementById("stateId").value);
				   if(parseInt(ids+1) <= parseInt(document.getElementById("to_selected_idx").value)){
						    nxtId = ids+1;
							removeDynamicToComboFields();
							createDynamicToComboFields();
					
						    if(category == 'G'){
								lgdDwrlocalBodyService.getLocalBodyListForSubDistBody(1,stateID,parseInt(val),{callback:showToChildList,async:false});
							}else if(category == 'O'){
								lgdAdminDepatmentDwr.getSubordinateOrgUnits(parseInt(val),{callback:showChangedToOrgnChildList,async:false});		
							}else if(category == 'L'){ 
								if(ids == 2){
									businessService.getVillageList( parseInt(val), 0,{callback:showToVillageforLR,async:false});
								}else{
									LGDDataService.findSubDistrictList('D', parseInt(val),{callback:showToSubDistrictListforLR,async:false});
								}
							}  
	}

}

 function getFromChildList1(val, category, ids){
	   var stateID = parseInt(document.getElementById("stateId").value);
	   if(parseInt(ids+1) <= parseInt(document.getElementById("from_selected_idx").value)){
			    nxtId = ids+1;
				removeDynamicFromComboFields();
				createDynamicFromComboFields();
		
			    if(category == 'G'){
					lgdDwrlocalBodyService.getLocalBodyListForSubDistBody(1,stateID,parseInt(val),{callback:showToChildList,async:false});
				}else if(category == 'O'){
					lgdAdminDepatmentDwr.getSubordinateOrgUnits(parseInt(val),{callback:showChangedFromOrgnChildList,async:false});		
				}else if(category == 'L'){ 
					if(ids == 2){
						businessService.getVillageList( parseInt(val), 0,{callback:showToVillageforLR,async:false});
					}else{
						LGDDataService.findSubDistrictList('D', parseInt(val),{callback:showToSubDistrictListforLR,async:false});
					}
				}  
}

} 


function createDynamicToComboFields(){ 
	// Added By Pranav Tiwari 
	var j = nxtId;
	var category = "O";
	var ullist="";
	var optList="";
	var ullistend="";
	
	while(j <= totalfields){
			var onChangeTxt = "getToChildList1(this.value,'"+category+"',"+j+")";
			
			
					if ($('#ul_to_level_'+j+'').length && $('#li_level_'+j+'').length ){
						if(j == totalfields)
							var ullist='<select class=combofield  style="width: 350px;" id= "select_level_'+j+'"  name="to_level_'+j+'" >';
						else
						var ullist='<select class=combofield  style="width: 350px;"  id= "select_level_'+j+'"  name="to_level_'+j+'" onchange="'+onChangeTxt+'">';
						ullistend=+'</select>';
						optList ='<option value="0"> --select-- </option>';
						$('#li_level_'+j+'').append(''+ullist+''+optList+''+ullistend+'');
						//$('#ul_to_level_'+j+'').append(''+ullist+''+optList+''+ullistend+'');
					}else{   
							if(j == totalfields)
								var ullist='<ul id= "ul_to_level_'+j+'"><li id= "li_level_'+j+'"><select class=combofield  style="width: 350px;" id= "select_level_'+j+'"  name="to_level_'+j+'" >';
							else
							var ullist='<ul id= "ul_to_level_'+j+'"><li id= "li_level_'+j+'"><select class=combofield  style="width: 350px;"  id= "select_level_'+j+'"  name="to_level_'+j+'" onchange="'+onChangeTxt+'">';
							ullistend=+'</select></li></ul>';
							optList ='<option value="0"> --select-- </option>';
							$('#li_level_'+j+'').last().after(''+ullist+''+optList+''+ullistend+'');
					}
	j++;
	}
	
}

 function createDynamicFromComboFields(){ 
	// Added By Pranav Tiwari 
	var j = nxtId;
	var category = "O";
	var ullist="";
	var optList="";
	var ullistend="";
	
	while(j <= totalFieldsFr){
			var onChangeTxt = "getFromChildList1(this.value,'"+category+"',"+j+")";
			if(j == totalFieldsFr)
				var ullist='<ul id= "ul_From_level_'+j+'"><li id= "liFR_level_'+j+'"><select class=combofield style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 500px; overflow: -moz-scrollbars-horizontal;" id= "selectFR_level_'+j+'"  name="from_level_'+j+'"  multiple="multiple" >';
				/* var ullist='<ul id= "ul_From_level_'+j+'"><li id= "liFR_level_'+j+'"><select style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 500px; overflow: -moz-scrollbars-horizontal;" 
				id= "selectFR_level_'+j+'"  name="from_level_'+j+'" multiple="multiple" >'; */
			else{
				var ullist='<ul id= "ul_From_level_'+j+'"><li id= "liFR_level_'+j+'"><select class=combofield  style="width: 350px;"  id= "selectFR_level_'+j+'"  name="from_level_'+j+'" onchange="'+onChangeTxt+'">';
				optList ='<option value="0"> --select-- </option>';
			}
			ullistend=+'</select></li></ul>';
			
			$('#liFR_level_'+j+'').last().after(''+ullist+''+optList+''+ullistend+'');
	j++;
	}
	
} 
	
	
function showChangedToOrgnChildList(data){
		var x = document.getElementById('select_level_'+nxtId+'');
		for(var key in data){
			var option = document.createElement("option");
			option.text = data[key].orgUnitName;
			option.value = data[key].orgUnitCode;
			x.add(option);
		}
		
}
	
function showChangedFromOrgnChildList(data){
	var x = document.getElementById('selectFR_level_'+nxtId+'');
	for(var key in data){
		var option = document.createElement("option");
		option.text = data[key].orgUnitName;
		option.value = data[key].orgUnitCode;
		x.add(option);
	}
	
}
 

function getToChildList(val, category, ids){ 
	var stateID = parseInt(document.getElementById("stateId").value);
	
	if(lbTypeCategory == 'U'){
		$("#IsLbMapped").show();
		$('input[name=mappedCoverageType]:checked').val("F");
		lbCode = parseInt(val);
	}
	if(parseInt(ids+1) <= parseInt(document.getElementById("to_selected_idx").value)){
		removeDynamicToDropbox(ids, $("#to_selected_idx").val());
		$("#id_to_level_"+parseInt(ids+1)).show();
		if(category == 'G'){
			lgdDwrlocalBodyService.getLocalBodyListForSubDistBody(1,stateID,parseInt(val),{callback:showToChildList,async:false});
		}else if(category == 'O'){
			lgdAdminDepatmentDwr.getSubordinateOrgUnits(parseInt(val),{callback:showToOrgnChildList,async:false});		
		}else if(category == 'L'){ 
			if(ids == 2){
				businessService.getVillageList( parseInt(val), 0,{callback:showToVillageforLR,async:false});
			}else{
				LGDDataService.findSubDistrictList('D', parseInt(val),{callback:showToSubDistrictListforLR,async:false});
			}
		}
	}
}

function getFromChildList(val, category, ids){
	var stateID = parseInt(document.getElementById("stateId").value);
	if(parseInt(ids+1) <= parseInt(document.getElementById("from_selected_idx").value)){
		removeDynamicFromDropbox(ids, $("#from_selected_idx").val());
		$("#id_from_level_"+parseInt(ids+1)).show();
		
	}
	if(parseInt(ids+1) < parseInt(document.getElementById("from_selected_idx").value)){
		if(category == 'G'){ //'G' signifies Local Body
			lgdDwrlocalBodyService.getLocalBodyListForSubDistBody(1,stateID,parseInt(val),{callback:showFromChildListSubDist,async:false});
		}else if(category == 'O'){ //'O' signifies Line department
			lgdAdminDepatmentDwr.getSubordinateOrgUnits(parseInt(val),{callback:showFromOrgnChildList,async:false});	
		}
	}else if(parseInt(ids+1) == parseInt(document.getElementById("from_selected_idx").value)){
		var stateID = parseInt(document.getElementById("stateId").value);
		var mappingType = $("#mappingType").val();
		
			var selectVal = $("#fromLocalBodySetup").val();
		var codes = ['2','3'];
		var toLevelTypeCode = 0;
		if((mappingType == 'OMO') || (mappingType == 'OMG') || (mappingType == 'OML')){
			toLevelTypeCode = $("#toDepartmentLevel").val();
		}else if(mappingType == 'GMO'){
			toLevelTypeCode = $("#toLocalBodySetup").val();
		}else if(mappingType == 'LMO'){
			toLevelTypeCode = $("#toLandRegion").val();
		}
		
		if(category == 'G'){
			if($.inArray(selectVal, codes) > -1){
				lgdAdminDepatmentDwr.getUnmappedParentwiseLBList(parseInt(val),stateID,mappingType,category,parseInt(toLevelTypeCode),{callback:showFromChildList,async:false});	
			}else{
				lgdAdminDepatmentDwr.getUnmappedULBList(parseInt(selectVal),stateID,mappingType,category,parseInt(toLevelTypeCode),{callback:showFromChildList,async:false});
			}
		}else if(category == 'O'){
			lgdAdminDepatmentDwr.getUnmappedSubordinateOrgUnits(parseInt(val),stateID,mappingType,category,parseInt(toLevelTypeCode),{callback:showFromOrgnChildList,async:false});
		}
	}
}

// showChildList for local body (Part A)
function showToChildList(data){
	var toID = parseInt(document.getElementById("to_level_id").value) + 1;
		//var toID = parseInt(document.getElementById("to_level_id").value);
	var anOption = document.createElement("OPTION") ;
	document.getElementById("to_level_"+toID).options.add(anOption) ;
	anOption.text = "Select";
	anOption.value = ""; 
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("to_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].localBodyNameInEnglish;
		anOption.value = data[key].localBodyCode;  		
	}
	document.getElementById("to_level_id").value = toID;
}

function showFromChildList(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].localBodyNameEnglish;
		anOption.value = data[key].localBodyCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}

function showFromChildListSubDist(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].localBodyNameInEnglish;
		anOption.value = data[key].localBodyCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}

function showToDeptLevel(data){
	//Modified By Pranav On 24 oct 2016 to fetch data with office name
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("toDepartmentLevel").options.add(anOption) ;
		if(data[key].levelWiseOfficeName == null){
			anOption.text = data[key].value;
		}
		else if(data[key].value == null) {
			anOption.text = data[key].levelWiseOfficeName ;
			
		}else{
			anOption.text = data[key].value + " "+ data[key].levelWiseOfficeName ;

		}
		anOption.value = data[key].key;  		
	}
}

function showFromDeptLevel(data){ 
	//Modified by Pranav on 7 nov to fetch with office name
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("fromDepartmentLevel").options.add(anOption) ;
		if(data[key].levelWiseOfficeName == null){
			anOption.text = data[key].value;
		}
		else if(data[key].value == null) {
			anOption.text = data[key].levelWiseOfficeName ;
			
		}else{
			anOption.text = data[key].value + " "+ data[key].levelWiseOfficeName ;

		}
		anOption.value = data[key].key;  		
	}
}

function showToOrgnChildList(data){
	var toID = parseInt(document.getElementById("to_level_id").value) + 1;
	var anOption = document.createElement("OPTION") ;
	document.getElementById("to_level_"+toID).options.add(anOption) ;
	anOption.text = "Select";
	anOption.value = ""; 
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("to_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].orgUnitName;
		anOption.value = data[key].orgUnitCode;  		
	}
	document.getElementById("to_level_id").value = toID;
}

function showFromOrgnChildList(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].orgUnitName;
		anOption.value = data[key].orgUnitCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}

function showFromDistrict(data){	
	if(document.getElementById("from_level_1").multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_1").options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_1").options.add(anOption) ;
		anOption.text = data[key].districtNameEnglish;
		anOption.value = data[key].districtCode;  		
	}
	document.getElementById("from_level_id").value = 1;
}

function showToDistrict(data){	
	var anOption = document.createElement("OPTION") ;
	document.getElementById("to_level_1").options.add(anOption) ;
	anOption.text = "Select";
	anOption.value = ""; 
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("to_level_1").options.add(anOption) ;
		anOption.text = data[key].districtNameEnglish;
		anOption.value = data[key].districtCode;  		
	}
	document.getElementById("to_level_id").value = 1;
}

function showToSubDistrictListforLR(data){
	var toID = parseInt(document.getElementById("to_level_id").value) + 1;
	var anOption = document.createElement("OPTION") ;
	document.getElementById("to_level_"+toID).options.add(anOption) ;
	anOption.text = "Select";
	anOption.value = ""; 
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("to_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].subdistrictNameEnglish;
		anOption.value = data[key].subdistrictCode;  		
	}
	document.getElementById("to_level_id").value = toID;
}


function showToVillageforLR(data){
	var toID = parseInt(document.getElementById("to_level_id").value) + 1;
	var anOption = document.createElement("OPTION") ;
	document.getElementById("to_level_"+toID).options.add(anOption) ;
	anOption.text = "Select";
	anOption.value = ""; 
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("to_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].villageNameEnglish;
		anOption.value = data[key].villageCode;  		
	}
	document.getElementById("to_level_id").value = toID;
}

function showFromChildListforLR(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].districtNameEnglish;
		anOption.value = data[key].districtCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}

function showFromSubDistrictListforLR(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].subdistrictNameEnglish;
		anOption.value = data[key].subdistrictCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}


function showFromVillageListforLR(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].villageNameEnglish;
		anOption.value = data[key].villageCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}


function showFromChildListforLR(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].districtNameEnglish;
		anOption.value = data[key].districtCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}

function showFromSubDistrictListforLR(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].subdistrictNameEnglish;
		anOption.value = data[key].subdistrictCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}


function showFromVillageListforLR(data){
	var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = "Select";
		anOption.value = ""; 
	}
	for(var key in data){
		var anOption = document.createElement("OPTION") ;
		document.getElementById("from_level_"+toID).options.add(anOption) ;
		anOption.text = data[key].villageNameEnglish;
		anOption.value = data[key].villageCode;  		
	}
	document.getElementById("from_level_id").value = toID;
}
function toggleFromTo(val){
	hideAll();
	$("#mappingDivID").show();
	$("#displaybtn").show();
	if(val == 'OMO'){
		$("#toDepartment").show();
		$("#fromDepartment").show();
		$("#toLabelID").text('<spring:message code="label.lineDep" />');
		$("#fromLabelID").text('<spring:message code="label.lineDep" />');
		document.getElementById("to_category").value = "O";
		document.getElementById("from_category").value = "O";
	}else if(val == 'GMO'){
		$("#toLocalBodySetup").show();
		$("#fromDepartment").show();
		$("#toLabelID").text('<spring:message code="common.localBody" />');
		$("#fromLabelID").text('<spring:message code="label.lineDep" />');
		document.getElementById("to_category").value = "G";
		document.getElementById("from_category").value = "O";
	}else if(val == 'OMG'){
		$("#toDepartment").show();
		$("#fromLocalBodySetup").show();
		$("#toLabelID").text('<spring:message code="label.lineDep" />');
		$("#fromLabelID").text('<spring:message code="common.localBody" />');
		document.getElementById("to_category").value = "O";
		document.getElementById("from_category").value = "G";
	}
	else if(val == 'OML'){
		$("#toDepartment").show();
		$("#fromLandRegion").show();		
		$("#toLabelID").text('<spring:message code="label.lineDep" />');
		$("#fromLabelID").text('<spring:message code="common.landRegion" />');
		document.getElementById("to_category").value = "O";
		document.getElementById("from_category").value = "L";
	}else if(val == 'LMO'){
		$("#toLandRegion").show();
		$("#fromDepartment").show();		
		$("#toLabelID").text('<spring:message code="common.landRegion" />');
		$("#fromLabelID").text('<spring:message code="label.lineDep" />');
		document.getElementById("to_category").value = "L";
		document.getElementById("from_category").value = "O";
	}
}

function hideAll(){
	$("#mappingDivID").hide();
	
	$("#toLocalBodySetup").val("");
	$("#toDepartment").val("");
	$("#fromLocalBodySetup").val("");
	$("#fromDepartment").val("");
	$("#fromLandRegion").val("");
	$("#toLandRegion").val("");
	
	$("#toLocalBodySetup").hide();
	$("#toDepartment").hide();
	$("#fromLocalBodySetup").hide();
	$("#fromDepartment").hide();
	$("#fromLandRegion").hide();
	$("#toLandRegion").hide();
	
	$("#toDepartmentLevelID").hide();
	$("#fromDepartmentLevelID").hide();
	
	$("#toTableID").find("ul:gt(2)").remove();
	$("#fromTableID").find("ul:gt(2)").remove();
	
	document.getElementById("to_level_id").value = 0;
	document.getElementById("to_selected_idx").value = 0;
	document.getElementById("to_category").value = "";
	
	document.getElementById("from_level_id").value = 0;
	document.getElementById("from_selected_idx").value = 0;
	document.getElementById("from_category").value = "";
	
	$('input[name=mappedCoverageType]:checked').val("");
}

function validateForm(){
	var msg = "";
	if(document.getElementById("mappingType").value == ""){
		msg += "Select Mapping Type \n";
	}else{
		var val = $("#mappingType").val();
		if(val == 'OMO'){
			if($("#toDepartment").val() == ""){
				msg += "Select Part A Line department \n";
			}
			if($("#toDepartmentLevel").val() == ""){
				msg += "Select Part A Line department level \n";
			}
			
			document.getElementById("to_selected_idx").value=parseInt(totalfields);
			var toSelectedIdx =parseInt(totalfields);
			
			if($("#select_level_"+toSelectedIdx).val() == null || $("#select_level_"+toSelectedIdx).val() == "" || $("#select_level_"+toSelectedIdx).val() == "0"){
				msg += "Select Part A Line department at level \n";
			}
			if($("#fromDepartment").val() == ""){
				msg += "Select Part B Line department \n";
			}
			if($("#fromDepartmentLevel").val() == ""){
				msg += "Select Part B Line department level \n";b
			}
					/* var fromSelectedIdx = document.getElementById("from_selected_idx").value;
					 if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
						msg += "Select Part B Line department at level \n";
					}  */
			 document.getElementById("from_selected_idx").value=parseInt(totalFieldsFr);
			 var fromSelectedIdx =parseInt(totalFieldsFr);
			 if($("#selectFR_level_"+fromSelectedIdx).val() == null || $("#selectFR_level_"+fromSelectedIdx).val() == ""  || $("#selectFR_level_"+fromSelectedIdx).val() == "0"){
				msg += "Select Part B Line department at level \n";
			} 
			 
			if($("#toDepartment").val() >0 && $("#fromDepartment").val() > 0 && $("#toDepartment").val() == $("#fromDepartment").val()){
				msg += "Part A and Part B Line department cannot be same \n";
			}
		}else if(val == 'GMO'){
			if($("#toLocalBodySetup").val() == ""){
				msg += "Select Part A Local Body Type \n";  
			}
			var toSelectedIdx = document.getElementById("to_selected_idx").value;
			if($("#to_level_"+toSelectedIdx).val() == null || $("#to_level_"+toSelectedIdx).val() == ""){
				msg += "Select Part A local body \n";
			}
			
			if($("#fromDepartment").val() == ""){
				msg += "Select Part B Line department \n";
			}
			if($("#fromDepartmentLevel").val() == ""){
				msg += "Select Part B Line department level \n";
			}
					/* var fromSelectedIdx = document.getElementById("from_selected_idx").value;
					if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
						msg += "Select Part B Line department at level \n";
					} */
			 document.getElementById("from_selected_idx").value=parseInt(totalFieldsFr);
			 var fromSelectedIdx =parseInt(totalFieldsFr);
			 if($("#selectFR_level_"+fromSelectedIdx).val() == null || $("#selectFR_level_"+fromSelectedIdx).val() == ""  || $("#selectFR_level_"+fromSelectedIdx).val() == "0"){
				msg += "Select Part B Line department at level \n";
			} 
			if(lbTypeCategory=='U'){
				if($('input[name=mappedCoverageType]:checked').val() == "P"){
					if($("#wardList").val() == null){
					msg += "Select Part A Ward \n";  
				}
			}
			}
		}else if(val == 'LMO'){
			if($("#toLandRegion").val() == ""){
				msg += "Select Part A Land Region \n";  
			}
			var toSelectedIdx = document.getElementById("to_selected_idx").value;
			if($("#to_level_"+toSelectedIdx).val() == null || $("#to_level_"+toSelectedIdx).val() == ""){
				msg += "Select Part A land Region \n";
			}
			if($("#fromDepartment").val() == ""){
				msg += "Select Part B Line department \n";
			}
			if($("#fromDepartmentLevel").val() == ""){
				msg += "Select Part B Line department level \n";
			}
			//var fromSelectedIdx = document.getElementById("from_selected_idx").value;
			document.getElementById("from_selected_idx").value=parseInt(totalfields);
			var fromSelectedIdx =parseInt(totalfields);
			if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
				msg += "Select Part B Line department at level \n";
			}
		}else if(val == 'OMG'){
			if($("#toDepartment").val() == ""){
				msg += "Select Part A Line department \n";
			}
			if($("#toDepartmentLevel").val() == ""){
				msg += "Select Part A Line department level \n";
			}
			//var toSelectedIdx = document.getElementById("to_selected_idx").value;
			document.getElementById("to_selected_idx").value=parseInt(totalfields);
			var toSelectedIdx =parseInt(totalfields);
			if($("#select_level_"+toSelectedIdx).val() == null || $("#select_level_"+toSelectedIdx).val() == "" || $("#select_level_"+toSelectedIdx).val() == "0"){
				msg += "Select Part A Line department at level \n";
			}
			
			if($("#fromLocalBodySetup").val() == ""){
				msg += "Select Part B Local Body Type \n";  
			}
			var fromSelectedIdx = document.getElementById("from_selected_idx").value;
			if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
				msg += "Select Part B local body \n";
			}
		}else if(val == 'OML'){
			if($("#toDepartment").val() == ""){
				msg += "Select Part A Line department \n";
			}
			if($("#toDepartmentLevel").val() == ""){
				msg += "Select Part A Line department level \n";
			}
			//var toSelectedIdx = document.getElementById("to_selected_idx").value;
			document.getElementById("to_selected_idx").value=parseInt(totalfields);
			var toSelectedIdx =parseInt(totalfields);
			if($("#to_level_"+toSelectedIdx).val() == null || $("#to_level_"+toSelectedIdx).val() == ""){
				msg += "Select Part A Line department at level \n";
			}
			if($("#fromLandRegion").val() == ""){
				msg += "Select Part B Land Region \n";  
			}
			var fromSelectedIdx = document.getElementById("from_selected_idx").value;
			if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
				msg += "Select Part B local body \n";
			}
		}
		
	}
	
	if(msg != ""){
		alert(msg);
		return false;
	}else{
		return true;	
		
	}
	
}

function saveData(){
	
	if(validateForm()){
		$("#Save").attr("disabled",true);
		document.deptMappingForm.action = "addDeptMappingForm.htm?<csrf:token uri='addDeptMappingForm.htm'/>";
		document.deptMappingForm.submit();
	}
}


function cancelForm(){
	answer = confirm("Are you sure you want to cancel the form?");
	if (answer !="0")
	{
		window.location="home.htm?<csrf:token uri='home.htm'/>";
	}
}

function showAlert(){   
	if("${message}"!=null && "${message}" != ''){
		var alertMessage = "<spring:message code="${message}"></spring:message> ";
		alert(alertMessage); 
		if("${message}" == 'msg.noPrivilege'){
			window.location="welcome.htm?<csrf:token uri='welcome.htm'/>";
		}
	}
}

function fixToMapping(){
	if("${isStateAdmin}" == "false" && "${isDO}" == "false"){
		var ids = "${to_selected_idx}";
		var locationID = "${locationID}";
		$("#to_selected_idx").val("${to_selected_idx}");
		
		$('#toLocalBodySetup').append($('<option>', {
		    value: '${toLocalBodySetup}',
		    text: ''
		}));
		
		$('#toDepartment').append($('<option>', {
		    value: '${toDepartment}',
		    text: ''
		}));
		
		$('#toDepartmentLevel').append($('<option>', {
		    value: '${toDepartmentLevel}',
		    text: ''
		}));
		
		
		$("#toLocalBodySetup").val("${toLocalBodySetup}");
		$("#toDepartment").val("${toDepartment}");
		$("#toDepartmentLevel").val("${toDepartmentLevel}");
		
		$('#toTableID ul').last().after('<ul id="id_to_level_'+ids+'" style="display:none"><li><input type = "hidden" name="to_level_'+ids+'" id="to_level_'+ids+'" value='+locationID+' /></li></ul>');
		
		$("#toLocalBodySetup").hide();
		$("#toDepartment").hide();
		$("#toDepartmentLevel").hide();
		$("#toLabelID").text('${locationName}');
		
	}
}

function validatePartABeforePartB(){
	var msg = "";
	var val = $("#mappingType").val();
	
	if(val == 'OMO'){
		if($("#toDepartment").val() == ""){
			msg += "Select Part A Line department \n";
		}
		if($("#toDepartmentLevel").val() == ""){
			msg += "Select Part A Line department level \n";
		}			
	}else if(val == 'GMO'){
		if($("#toLocalBodySetup").val() == ""){
			msg += "Select Part A Local Body Type \n";  
		}		
	}else if(val == 'OMG'){
		if($("#toDepartment").val() == ""){
			msg += "Select Part A Line department \n";
		}
		if($("#toDepartmentLevel").val() == ""){
			msg += "Select Part A Line department level \n";
		}			
	}else if(val == 'OML'){
		if($("#toDepartment").val() == ""){
			msg += "Select Part A Line department \n";
		}
		if($("#toDepartmentLevel").val() == ""){
			msg += "Select Part A Line department level \n";
		}			
	}else if(val == 'LMO'){
		if($("#toLandRegion").val() == ""){
			msg += "Select Part A Land Region \n";
		}			
	}
	if(msg != ""){
		alert(msg);
		return false;
	}else{
		return true;	
		
	}
}
</script>

</head>
<body onload="showAlert()">
<div class="overlay" id="overlay1" style="display: none;"></div>
	<form:form name="deptMappingForm" method="POST" action="addDeptMappingForm.htm">
		<div class="content">
			<div id="frmcontent">
				<div id="helpDiv" class="helpMessage">
					<div class="helpheader">
						<h4>Help Message</h4>
					</div>
					<div id="helpMsgText" class="helptext"></div>
				</div>
				<div class="frmhd">
					<h3 class="subtitle">
						<label><spring:message code="menu.deptLBMapping" htmlEscape="true"></spring:message></label>
					</h3>
					
				</div>
				<input type="hidden" id="stateId" value="<%=request.getAttribute("stateCode")%>" /> <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addDeptMappingForm.htm"/>" />
				<div class="clear"></div>
				<div class="frmpnlbrdr">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<label><spring:message code="menu.deptLBMapping" htmlEscape="true"></spring:message></label>
							</div>
							<div>
								<ul class="listing" style="width: 100%;">
									<li style="margin-left: 300px"><label> <spring:message code="menu.deptMapping" htmlEscape="true"></spring:message>:
									</label></li>
									<li><select id="mappingType" name="mappingType" class="combofeild">

											<option value="">
												<spring:message code="Label.SELECT" />
											</option>
											<c:forEach var="mappingTypeList" items="${mappingTypeList}">
												<option value="${mappingTypeList.key}">
													<spring:message code='${mappingTypeList.value}'  htmlEscape="true" />
												</option>
											</c:forEach>
									</select><br /></li>
								</ul>
								<div class="clear"></div>
								<ul>
									<li style="width: 100%">
										<div id="mappingDivID" style="display: none">

											<fieldset>
												<legend>
													<label id="labelID"></label>
												</legend>
												<div class="clear"></div>
												<ul class="listing">
													<li style="width: 48%">
														<fieldset>
															<legend>Part A</legend>
															<div id="toTableID">
																<ul  class="listing">
																	<li><input type="hidden" id="to_level_id" name="to_level_id" value="" /> <input type="hidden" id="to_selected_idx" name="to_selected_idx" value="" /> <input type="hidden" id="to_category" name="to_category" value="" /></li>
																</ul>
																
																<ul class="listing">
																	<li><label id="toLabelID"></label></li>
																	<li><select class="combofield" id="toLocalBodySetup" name="toLocalBodySetup" style="display: none">
																			<option value="">
																				<spring:message code="Label.SELECT" htmlEscape="true" />
																			</option>
																			<c:forEach var="lbSetup" items="${localGovtSetup}">
																				<option value="${lbSetup.localBodyTypeCode}" paramCategory="${lbSetup.category}">
																					<c:out value="${lbSetup.localBodyTypeName}" escapeXml="true"></c:out>
																				</option>
																			</c:forEach>
																	</select> <select class="combofield" id="toDepartment" name="toDepartment" style="display: none">
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																			<c:forEach var="orgnList" items="${organizationList}">
																				<option value="${orgnList.orgCode}-${orgnList.orgUnitCode}">
																					<c:out value="${orgnList.orgUnitName}" escapeXml="true"></c:out>
																				</option>
																			</c:forEach>
																	</select></li>
																</ul>
																<ul class="listing" id="toDepartmentLevelID" style="display: none">
																	<li><spring:message code="label.departmentLevel" /></li>
																	<li><select class="combofield" id="toDepartmentLevel" name="toDepartmentLevel">
																			<option value="">
																				<spring:message code="Label.SELECT" htmlEscape="true" />
																			</option>
																	</select></li>
																</ul>
																
															</div>
															<div id="IsLbMapped" style="display: none">
																<ul class="listing">
																	<li><spring:message code="label.isLBMapped" text="Is Local Body Mapped" /></li>
																	<li>
																	<input type="radio" name="mappedCoverageType" id="mappedCoverageTypeF" value="F" checked="checked" /><spring:message code="label.Full" text="FULL" />
																	<input type="radio" name="mappedCoverageType" id="mappedCoverageTypeP" value="P" /><spring:message code="label.Part" text="PART" />
																	</li>
																</ul>
															</div>
															<div id="wardDiv" style="display: none">
															</div>
														</fieldset>
													</li>

													<li style="width: 48%">
														<fieldset>
															<legend>Part B</legend>
															<div id="fromTableID">
																<ul class="listing">
																	<li><input type="hidden" id="from_level_id" name="from_level_id" value="" /> <input type="hidden" id="from_selected_idx" name="from_selected_idx" value="" /> <input type="hidden" id="from_category" name="from_category" value="" />
																	</li>
																</ul>
																<ul class="listing">
																	<li><label id="fromLabelID"></label></li>

																	<li><select class="combofield" id="fromLocalBodySetup" name="fromLocalBodySetup" style="display: none">
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																			<c:forEach var="lbSetup" items="${localGovtSetup}">
																				<option value="${lbSetup.localBodyTypeCode}">
																					<c:out value="${lbSetup.localBodyTypeName}" escapeXml="true"></c:out>
																				</option>
																			</c:forEach>
																	</select> <select class="combofield" id="fromDepartment" name="fromDepartment" style="display: none">
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																			<c:forEach var="orgnList" items="${organizationList}">
																				<option value="${orgnList.orgCode}-${orgnList.orgUnitCode}">
																					<c:out value="${orgnList.orgUnitName}" escapeXml="true"></c:out>
																				</option>
																			</c:forEach>
																	</select></li>
																</ul>
																<ul id="fromDepartmentLevelID" style="display: none">
																	<li><spring:message code="label.departmentLevel" /></li>
																	<li><select class="combofield" id="fromDepartmentLevel" name="fromDepartmentLevel">
																			<option value="" htmlEscape="true">
																				<spring:message code="Label.SELECT" />
																			</option>
																	</select></li>
																</ul>
															</div>
														</fieldset>
													</li>
												</ul>
											</fieldset>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div class="btnpnl" id= "displaybtn" style="display: none">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" colspan="2"><input type="button" class="bttn" name="Save" id="Save" value="<spring:message code="Button.SAVE" />" onclick="saveData()" />
						</tr>
					</table>
				</div>

			</div>
		</div>
	</form:form>
</body>
</html>