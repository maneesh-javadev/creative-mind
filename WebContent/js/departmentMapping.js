
var lbTypeCategory = "";
var lbCode = "";
var totalfields = 0;
var totalFieldsFr = 0;
var stateId=0;
var userId=0;
var nxtId = 0;
var errordata;
var len=0;
$(document).ready(function() {
	$('#mappingType').change(function() {
		removeAll();
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
		
		 var divTemplate = $("#wardDiv");
		 var templateLIHierarchy = $("<div/>");
		 templateLIHierarchy.addClass("form-group");
		 
			    
		 // Added Label Elements
		 var templateLabelHierarchy = $("<label/>");
		     templateLabelHierarchy.addClass("col-sm-3 control-label");
			 templateLabelHierarchy.html("Select Wards");
			
			 
		   var templateDiv=$("<div/>"); 
		       templateDiv.addClass("col-sm-6");
		       
		 // Added Html Select Elements
		 var templateSelectHierarchy = $("<select/>");
		 templateSelectHierarchy.addClass("form-control");
			templateSelectHierarchy.attr("id", "wardList" );
			templateSelectHierarchy.attr("name", "wardList" );
			
			jQuery.each(data, function(index, obj) {
				var option = $("<option />");
				option.val(obj.wardCode).text(obj.wardNameEnglish);
				templateSelectHierarchy.append(option);
			});
			
		
			templateDiv.append(templateSelectHierarchy);
			templateLIHierarchy.append(templateLabelHierarchy);
			templateLIHierarchy.append(templateDiv);
			
			
		
		
			divTemplate.append(templateLIHierarchy);
	}
	
	$('#toLocalBodySetup').change(function() {
		removeAll();
		var msg="Data is not available";
		var selectVal = $(this).val();
		var stateID = parseInt(document.getElementById("stateId").value);
		if((stateID==25 || stateID==26 ||stateID==31 ||stateID==34) && selectVal==4)
			{
			alert(msg);
			}
		else
			{
		$("#toDepartmentLevelHierarchy").empty();
		 $("#fromDepartmentLevelHierarchy").empty();
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
				
				if(stateID==34)
				{
					/*lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(selectVal,stateID,{
						callback:showToDistrictPanchayat,async:false});*/
					var selectedText =  $('option:selected',$(this)).text();
					document.getElementById("to_selected_idx").value = 1;
					addDynamicToDropbox(1, "G", selectedText);
					$("#id_to_level_1").show();
					lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(parseInt(selectVal),stateID,{callback:showToDistrictPanchayat,async:false});
				}
				if(stateID==34 && selectVal==3)
				{
					lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(2,stateID,{callback:showToDistrictPanchayat,async:false});	
				}
				   else
					{
				lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(1,stateID,{callback:showToDistrictPanchayat,async:false});
					}
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
			}
	});
	
	
	$('#fromLocalBodySetup').change(function() {
		var validateFlag = validatePartABeforePartB();
		   if(validateFlag){		
			var selectVal = $(this).val();
			var stateID = parseInt(document.getElementById("stateId").value);
			var msg="Data is not available";
			if((stateID==25 || stateID==26 ||stateID==31 ||stateID==34) && (selectVal==4))
			{
			alert(msg);
			}
			else
				{
			 $("#fromDepartmentLevelHierarchy").empty();
			if(selectVal == '') {
				alert("Select Part B local body type");
			}else{
				var codes = ['2','3'];
				if($.inArray(selectVal, codes) > -1){
					var selectedIdx = $('option:selected',$(this)).index();
					document.getElementById("from_selected_idx").value = selectedIdx;
					
					addDynamicFromDropbox(selectedIdx, "G","");
					$("#id_from_level_1").show();
					if(stateID==34 && selectVal==2)
						{
						lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(selectVal,stateID,{callback:showFromDistrictPanchayat,async:false});
						}
					else if(stateID==34 && selectVal==3)
						{
						lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(2,stateID,{callback:showFromDistrictPanchayat,async:false});
						}
					  else
						{
					lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(1,stateID,{callback:showFromDistrictPanchayat,async:false});
					//LGDDataService.findDistrictPanchyatList(1,stateID,{callback:showFromDistrictPanchayat,async:false});
						}
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
					//lgdAdminDepatmentDwr.getUnmappedULBList(parseInt(selectVal),stateID,mappingType,"G",parseInt(toLevelTypeCode),{callback:showFromChildList,async:false});
					lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(parseInt(selectVal),stateID,{callback:showFromDistrictPanchayat,async:false});
				}
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
				$("#toDepartmentOrgUnitID").show();
				orgCode = selectVal.split("-"); 
				lgdAdminDepatmentDwr.getOrganizationAtLevelsForDeptLBMapping(parseInt(orgCode[0]),{callback:showToDeptLevel,async:false});

				//lgdAdminDepatmentDwr.getOrganizationAtLevels(parseInt(orgCode[0]),{callback:showToDeptLevel,async:false});
				
			}
		}
	});
	
	/* The {@code removeAllOptions} used to clear drop down box based on their id. 
	*/
	removeAllOptions=function(id){
		dwr.util.removeAllOptions(id);
	};
	
	
	
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
					$("#fromDepartmentOrgUnitID").show();
					
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
		//$("#toTableID").find("ul:gt(2)").remove();
		if(selectVal == '') {
			alert("Please Select");
		}else{
			var selectedIdx = $('option:selected',$(this)).index();
			document.getElementById("to_selected_idx").value = selectedIdx;
			lgdDwrOrganizationService.getOrganizationeUnitsByorglocatedlevelcode(parseInt(selectVal),{callback:showHierachyofDeptLevel,async:false});
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
		//$("#fromTableID").find("ul:gt(2)").remove();
		if(selectVal == '') {
			alert("Please Select");
		}else{
			var selectedIdx = $('option:selected',$(this)).index();
			document.getElementById("from_selected_idx").value = selectedIdx;
			lgdDwrOrganizationService.getOrganizationeUnitsByorglocatedlevelcode(parseInt(selectVal),{callback:showHierachyofFromDeptLevel,async:false});
		}
		
	}); 
	 
	  function showHierachyofFromDeptLevel(data){
		  
		  removeAllOptions('fromOrgUnits');
		  
		  var options = $("#fromOrgUnits"); 
			
		
		  
			jQuery.each(data, function(index, obj) {
				var option = $("<option />");
				option.val(obj.orgUnitCode).text(obj.orgUnitName);
				options.append(option);
			});
			
		
		// Added By Pranav on 01 Nov 16
/*		  $("#fromDepartmentLevelHierarchy").empty();
		var length=data.length;
		
		if(length>0){
			totalFieldsFr = length; 
			var listOfOrganization = data[0].list;
			for (var int = 0; int < length; int++) {
				
				var ullist="";
				var ullistend="";
				var category = "O";
				var ids = int+1;
				
				if (length>1)
				     var onChangeTxt = "getFromChildList1(this.value,'"+category+"',"+ids+")";	
				
				if(int==(length-1)){
					 var ullist='<div class="form-group" id= "ul_from_level_'+ids+'"><label  class="col-sm-3 control-label">'+data[int].levelName+'</label>'
				     +'<div class="col-sm-6" id= "liFR_level_'+ids+'"> <select class=form-control  id= "selectFR_level_'+ids+'" style="width:350px"    name="from_level_'+ids+'" onchange="'+onChangeTxt+'">';
				
				}
				else{
					 var ullist='<div class="form-group" id= "ul_from_level_'+ids+'"><label  class="col-sm-3 control-label">'+data[int].levelName+'</label>'
				     +'<div class="col-sm-6" id= "liFR_level_'+ids+'"> <select class=form-control  id= "selectFR_level_'+ids+'"   name="from_level_'+ids+'" onchange="'+onChangeTxt+'">';
					
					
				}   
					
				var optList='<option value = "0">--select--</option>';
				 for(var j = 0; j<listOfOrganization.length; j++){
					if(data[int].levelCode == listOfOrganization[j][2])
					optList+='<option value="'+listOfOrganization[j][0]+'">'+listOfOrganization[j][1]+'</option>';
					}
				
				ullistend=+'</select></div></div>';
				$('#fromDepartmentLevelHierarchy').append(''+ullist+''+optList+''+ullistend+'');
			}
		}*/
		
		
		    
	}
	  
	
	function showHierachyofDeptLevel(data){
		// Added By Pranav on 01 Nov 16
		removeAllOptions('toOrgUnits');
		  var options = $("#toOrgUnits"); 
			
		  var option = $("<option />");
			option.val(0).text("select");
			options.append(option);
		  
			jQuery.each(data, function(index, obj) {
				var option = $("<option />");
				option.val(obj.orgUnitCode).text(obj.orgUnitName);
				options.append(option);
			});
			
			
		
		/* $("#toDepartmentLevelHierarchy").empty();
		 
		var length=data.length;
		if(length>0){
			totalfields = length; 
			var listOfOrganization = data[0].list;
			for (var int = 0; int < length; int++) {
				
				var ullist="";
				var ullistend="";
				var category = "O";
				var ids = int+1;
				if(length>1)
				     var onChangeTxt = "getToChildList1(this.value,'"+category+"',"+ids+")";	
				else
					var onChangeTxt = "loadExistingMapping(this.value)";
				if(int==(length-1))
					{
					 var ullist='<div class="form-group" id= "ul_to_level_'+ids+'"><label  class="col-sm-3 control-label">'+data[int].levelName+'</label>'
				     +'<div class="col-sm-6" id= "li_level_'+ids+'"> <select class=form-control  id= "select_level_'+ids+'" style="width:350px"    name="to_level_'+ids+'" onchange="'+onChangeTxt+'">';
					}
				else{
					 var ullist='<div class="form-group" id= "ul_to_level_'+ids+'"><label  class="col-sm-3 control-label">'+data[int].levelName+'</label>'
				     +'<div class="col-sm-6" id= "li_level_'+ids+'"> <select class=form-control  id= "select_level_'+ids+'"    name="to_level_'+ids+'" onchange="'+onChangeTxt+'">';
				}
				
				var optList='<option value = "0">--select--</option>';
				 for(var j = 0; j<listOfOrganization.length; j++){
					if(data[int].levelCode == listOfOrganization[j][2])
					optList+='<option value="'+listOfOrganization[j][0]+'">'+listOfOrganization[j][1]+'</option>';
					}
				
				ullistend=+'</select></div></div>';
				$('#toDepartmentLevelHierarchy').append(''+ullist+''+optList+''+ullistend+'');
			}
		}
		*/
		
		    
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
 var ids2=0;
 function addDynamicToDropbox(level, category, labelTxt){
	 stateId=$("#stateId").val();
	 var sstateId=parseInt(stateId);
	 var ids = 0;
	 var vLabelText="Village Panchayat";
	 var iLabelText="Intermidiate Panchayat";
	 var selectVal = $('#toLocalBodySetup').val();
	$.each(new Array(level),function(cnt){
		 ids = parseInt(cnt) + 1;
		 
		var labelText = "";
	      if(labelTxt == ""){
			if(category == 'G'){
				 if((sstateId==11 || sstateId==14 || sstateId==25 || sstateId==26|| sstateId==30 || sstateId==31 || sstateId==34)&& ids==2 && level==2)
				 {
				  labelText = vLabelText;
				 }
				 else if((sstateId==34) && ids==1 && level==1)
				 {
				  labelText =iLabelText;
				 }
				 else
					 {
				     labelText = $("#toLocalBodySetup option[value="+ids+"]").text();
					 }
			}else if(category == 'O'){
				labelText = $("#toDepartmentLevel option").eq(ids).text() ;
			}else if(category == 'L'){
				labelText = $("#toLandRegion option").eq(ids).text() ;
			}
			 
			
		}else{
			
			    labelText = labelTxt ;
				 
		}
		
		 if(level==ids)	
			{
			 var onChangeTxt = "loadExistingMappinglocalbody(this.value);getToChildList(this.value,'"+category+"',"+ids+");";
			}
		 else
			 { 
			 var onChangeTxt = "getToChildList(this.value,'"+category+"',"+ids+")";
			 }
		      var ullist='<div class="form-group" id= "id_to_level_'+ids+'" style="display:none"><label  class="col-sm-3 control-label">'+labelText+'</label>'
			 +'<div class="col-sm-6" id= "li_level_'+ids+'"> <select class=form-control  id= "to_level_'+ids+'" style="width:350px"    name="to_level_'+ids+'" onchange="'+onChangeTxt+'">';
			 $("#toDepartmentLevelHierarchy").append(ullist);
		
	});

}

 
 /*$(document).on('change', '#to_level_'+ids2, function(){
		if(ids2>0)
	{
     alert('Does this work?');
    }
    });
 
 $('#to_level_'+ids2).on('change', function() {
	  alert( this.value );
	});*/
 

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
	stateId=$("#stateId").val();
	var sstateId=parseInt(stateId);
	 var ids = 0;
	 var vLabelText="Village Panchayat";
	 var iLabelText="Intermidiate Panchayat";
	$.each(new Array(level),function(cnt){
	    ids = parseInt(cnt) + 1;
		var multipleFlag = "";
	    var labelText = "";
		if(labelTxt == ""){
			if(category == 'G'){
				 if((sstateId==11 || sstateId==14 || sstateId==25 || sstateId==26|| sstateId==30 || sstateId==31 || sstateId==34)&& ids==2 && level==2)
				 {
				  labelText = vLabelText;
				 }
				 else if((sstateId==34) && ids==1 && level==1)
				 {
				  labelText =iLabelText;
				 }
				/* else if((sstateId==34) && ids==2 && level==2)
				 {
				  labelText =iLabelText;
				 }*/
				else
					{
				labelText = $("#fromLocalBodySetup option[value="+ids+"]").text() ;
					}
			}else if(category == 'O'){
				labelText =  $("#fromDepartmentLevel option").eq(ids).text() ;
			}else if(category == 'L'){
				labelText =  $("#fromLandRegion option").eq(ids).text() ;
			}
		}else{
			labelText = "<td>" + labelTxt + "</td>";
		}
	    if(ids == level){
			multipleFlag = 'multiple="multiple"';
		   var onChangeTxt = "getFromChildList(this.value,'"+category+"',"+ids+")";
			 var ullist='<div class="form-group" id= "id_from_level_'+ids+'" style="display:none"><label  class="col-sm-3 control-label">'+labelText+'</label>'
			 +'<div class="col-sm-6" id= "li_level_'+ids+'"> <select class="form-control" style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 200px; overflow: -moz-scrollbars-horizontal;"  id= "from_level_'+ids+'"    name="from_level_'+ids+'" '+multipleFlag+' onchange="'+onChangeTxt+'">';
			 $("#fromDepartmentLevelHierarchy").append(ullist);
		}else{
			var onChangeTxt = "getFromChildList(this.value,'"+category+"',"+ids+")";
			 var ullist='<div class="form-group" id= "id_from_level_'+ids+'" style="display:none"><label  class="col-sm-3 control-label">'+labelText+'</label>'
			 +'<div class="col-sm-6" id= "li_level_'+ids+'"> <select class="form-control"  id= "from_level_'+ids+'"     name="from_level_'+ids+'" '+multipleFlag+' onchange="'+onChangeTxt+'">';
			 $("#fromDepartmentLevelHierarchy").append(ullist);
			
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
						    
							removeDynamicToComboFields();
							createDynamicToComboFields();
							nxtId = ids+1;
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
			    
				removeDynamicFromComboFields();
				createDynamicFromComboFields();
				nxtId = ids+1;
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
							var ullist='<select class=form-control  style="width: 350px;" id= "select_level_'+j+'"  name="to_level_'+j+'" >';
						else
						var ullist='<select class=form-control  style="width: 350px;"  id= "select_level_'+j+'"  name="to_level_'+j+'" onchange="'+onChangeTxt+'">';
						ullistend=+'</select>';
						optList ='<option value="0"> --select-- </option>';
						$('#li_level_'+j+'').append(''+ullist+''+optList+''+ullistend+'');
						//$('#ul_to_level_'+j+'').append(''+ullist+''+optList+''+ullistend+'');
					}else{   
							if(j == totalfields)
								var ullist='<ul id= "ul_to_level_'+j+'"><li id= "li_level_'+j+'"><select class=form-control  style="width: 350px;" id= "select_level_'+j+'"  name="to_level_'+j+'" >';
							else
							var ullist='<ul id= "ul_to_level_'+j+'"><li id= "li_level_'+j+'"><select class=form-control  style="width: 350px;"  id= "select_level_'+j+'"  name="to_level_'+j+'" onchange="'+onChangeTxt+'">';
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
		optList="";
			var onChangeTxt = "getFromChildList1(this.value,'"+category+"',"+j+")";
			if(j == totalFieldsFr){
				var ullist='<div class="form-group" id= "ul_From_level_'+j+'">'
				+'<div class="col-sm-6" id= "liFR_level_'+j+'"> <select class="form-control"  style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 500px; overflow: -moz-scrollbars-horizontal;"  id= "selectFR_level_'+j+'" name="from_level_'+j+'"  multiple="multiple">';
			}
				
					
					/* var ullist='<ul id= "ul_From_level_'+j+'"><li id= "liFR_level_'+j+'"><select style="border: 1px solid grey; overflow-x: scroll; height: 134px; width: 500px; overflow: -moz-scrollbars-horizontal;" 
				id= "selectFR_level_'+j+'"  name="from_level_'+j+'" multiple="multiple" >'; */
			else{
				var ullist='<div class="form-group" id= "ul_From_level_'+j+'">'
				+'<div class="col-sm-6" id= "liFR_level_'+j+'"> <select class="form-control"  id= "selectFR_level_'+j+'" name="from_level_'+j+'"  ">';
				optList ='<option value="0"> --select-- </option>';	
			}
			ullistend=+'</select></div></div>';
			
			$('#fromDepartmentLevelHierarchy').append(''+ullist+''+optList+''+ullistend+'');
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
	
	/*if(lbTypeCategory == 'U'){
		$("#IsLbMapped").show();
		$('input[name=mappedCoverageType]:checked').val("F");
		lbCode = parseInt(val);
	}*/
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
		//removeDynamicFromDropbox(ids, $("#from_selected_idx").val());
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
				//lgdAdminDepatmentDwr.getUnmappedParentwiseLBList(parseInt(val),stateID,mappingType,category,parseInt(toLevelTypeCode),{callback:showFromChildList,async:false});	
				
				lgdDwrlocalBodyService.getLocalBodyListForSubDistBody(1,stateID,parseInt(val),{callback:showFromChildListSubDist,async:false});
			}else{
				//lgdAdminDepatmentDwr.getUnmappedULBList(parseInt(selectVal),stateID,mappingType,category,parseInt(toLevelTypeCode),{callback:showFromChildList,async:false});
				lgdDwrlocalBodyService.getLocalBodyListForSelectedBody(parseInt(selectVal),stateID,{callback:showFromDistrictPanchayat,async:false});

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
	
	$("#toDepartmentOrgUnitID").hide();
	$("#fromDepartmentOrgUnitID").hide();
	$("#showMappedData").hide();
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
			
			if($("#toOrgUnits").val() == ""){
				msg += "Select Part A Line department level \n";
			}
			
			/*document.getElementById("to_selected_idx").value=parseInt(totalfields);
			var toSelectedIdx =parseInt(totalfields);
			
			if($("#select_level_"+toSelectedIdx).val() == null || $("#select_level_"+toSelectedIdx).val() == "" || $("#select_level_"+toSelectedIdx).val() == "0"){
				msg += "Select Part A Line department at level \n";
			}*/
			if($("#fromDepartment").val() == ""){
				msg += "Select Part B Line department \n";
			}
			if($("#fromDepartmentLevel").val() == ""){
				msg += "Select Part B Line department level \n";b
			}
			
			if($("#fromOrgUnits").val() == ""){
				msg += "Select Part B Line department level \n";b
			}
					/* var fromSelectedIdx = document.getElementById("from_selected_idx").value;
					 if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
						msg += "Select Part B Line department at level \n";
					}  */
			/* document.getElementById("from_selected_idx").value=parseInt(totalFieldsFr);
			 var fromSelectedIdx =parseInt(totalFieldsFr);
			 if($("#selectFR_level_"+fromSelectedIdx).val() == null || $("#selectFR_level_"+fromSelectedIdx).val() == ""  || $("#selectFR_level_"+fromSelectedIdx).val() == "0"){
				msg += "Select Part B Line department at level \n";
			} */
			 
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
			 /*document.getElementById("from_selected_idx").value=parseInt(totalFieldsFr);
			 var fromSelectedIdx =parseInt(totalFieldsFr);
			 
			 if($("#selectFR_level_"+fromSelectedIdx).val() == null || $("#selectFR_level_"+fromSelectedIdx).val() == ""  || $("#selectFR_level_"+fromSelectedIdx).val() == "0")
			 {
				msg += "Select Part B Line department at level \n";
			} */
			
			if($("#fromOrgUnits").val() == ""){
				msg += "Select Part B Line department level \n";
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
			/*document.getElementById("to_selected_idx").value=parseInt(totalfields);
			var toSelectedIdx =parseInt(totalfields);
			if($("#select_level_"+toSelectedIdx).val() == null || $("#select_level_"+toSelectedIdx).val() == "" || $("#select_level_"+toSelectedIdx).val() == "0"){
				msg += "Select Part A Line department at level \n";
			}*/
			
			if($("#fromLocalBodySetup").val() == ""){
				msg += "Select Part B Local Body Type \n";  
			}
			/*var fromSelectedIdx = document.getElementById("from_selected_idx").value;
			if($("#from_level_"+fromSelectedIdx).val() == null || $("#from_level_"+fromSelectedIdx).val() == ""){
				msg += "Select Part B local body \n";
			}*/
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




function cancelForm(){
	answer = confirm("Are you sure you want to cancel the form?");
	if (answer !="0")
	{
		window.location="home.htm?<csrf:token uri='home.htm'/>";
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

function removeAll(){
	$("#toDepartmentLevelHierarchy").empty();
	$("#fromDepartmentLevelHierarchy").empty();
	$('#wardDiv').hide();
	$('#IsLbMapped').hide();
	
}

function loadExistingMappingDept(orgUnitCode)
{
orgLocatedCode=$("#toDepartmentLevel").val();
mappingType=$("#mappingType").val();
stateId=$("#stateId").val();
userId=$("#userId").val();
tbl=$('#showMappedTable');

lgdAdminDepatmentDwr.getExistingDeptMapping(orgUnitCode,orgLocatedCode,stateId,mappingType, {
callback : function(result) {
	     errordata=result;
	  if(result!=null)
		{
		$("#showMappedTable > tbody").empty();
		jQuery.each(result, function(index, obj) {
	    $("#showMappedData").show();
		var templateTR = $("<tr/>");
		var templateTD1 = $("<td/>");
		$( templateTD1 ).html(index+1);
		var templateTD2 = $("<td/>");
		$( templateTD2 ).html(obj.lineDept);
		var templateTD3 = $("<td/>");
		if(obj.deptLevel==null)
		{
		$( templateTD3 ).html("STATE");
		}
	else
		{
		$( templateTD3 ).html(obj.deptLevel);
		}
		
		
		var templateTD4 = $("<td/>");
		$( templateTD4 ).html(obj.childHierarchy);
		var templateBtn = $( "<input />" );
		templateBtn.attr("type", "button");
		templateBtn.attr("value", "Delete");
		$(templateBtn).click(function() {
		var rownum = this.parentNode.parentNode.rowIndex;
		
		lgdAdminDepatmentDwr.deleteExistingDeptMapping(obj.mappingFromId,obj.mappingId,userId,stateId, {
			callback : function(result) {
			if(result==true){
				
				deleteRow(rownum);
			}	
	
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
			});
		});
		var templateTD5 = $("<td/>");
		templateTD5.append(templateBtn);
		
		$( templateTR ).append( templateTD1 ).append( templateTD2 ).append( templateTD3 ).append( templateTD4 ).append( templateTD5 );
		$( tbl ).append( templateTR );
	
	});	
	
		}  
	
},
errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
async : true
});
}

function loadExistingMappinglocalbody(orgUnitCode)
{
orgLocatedCode=$("#toLocalBodySetup").val();
orgLocatedCode= parseInt(orgLocatedCode);
orgUnitCode=parseInt(orgUnitCode);
mappingType=$("#mappingType").val();
stateId=$("#stateId").val();
userId=$("#userId").val();
tbl=$('#showMappedTable');
lgdAdminDepatmentDwr.getExistingDeptMapping(orgUnitCode,orgLocatedCode,stateId,mappingType, {
callback : function(result) {
	errordata=result;
	if(result!=null)
		{
		//len=$('#showMappedTable >tbody >tr').length;
	    $("#showMappedTable > tbody").empty();
	    jQuery.each(result, function(index, obj) {
	    $("#showMappedData").show();
	    var templateTR = $("<tr/>");
		var templateTD1 = $("<td/>");
		$( templateTD1 ).html(index+1);
		var templateTD2 = $("<td/>");
		$( templateTD2 ).html(obj.lineDept);
		var templateTD3 = $("<td/>");
		if(obj.deptLevel==null)
		{
		$( templateTD3 ).html("STATE");
		}
	else
		{
		$( templateTD3 ).html(obj.deptLevel);
		}
		var templateTD4 = $("<td/>");
		$( templateTD4 ).html(obj.childHierarchy);
		var templateBtn = $( "<input />" );
		templateBtn.attr("type", "button");
		templateBtn.attr("value", "Delete");
		$(templateBtn).click(function() {
		var rownum = this.parentNode.parentNode.rowIndex;
		lgdAdminDepatmentDwr.deleteExistingDeptMapping(obj.mappingFromId,obj.mappingId,userId,stateId, {
			callback : function(result) {
			var len=result.length;
			if(result==true){
			deleteRow(rownum);
			
			}	
	      },
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
			});
		});
		var templateTD5 = $("<td/>");
		templateTD5.append(templateBtn);
		
		$( templateTR ).append( templateTD1 ).append( templateTD2 ).append( templateTD3 ).append( templateTD4 ).append( templateTD5 );
		$( tbl ).append( templateTR );
	
	});	
}  
	
},
errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
async : true
});

}


function showSaveError()
{
   var errorflag =true;
   var fromorgUnitCode=$("#fromOrgUnits").val();
   var fromUnitCode; 
   var len=errordata.length;
   var len2=fromorgUnitCode.length;
	
	var i,j;
	for (i = 0; i < len; i++) {
		for (j = 0; j <len2;  j++) {
			fromUnitCode=parseInt(fromorgUnitCode[j])
			
			if(errordata[i].toOrgUnitCode==fromUnitCode){
				 errorflag=false;
			}
		}
		}
    if(errorflag==false)
    {
		alert("This mapping is already exist");
		return false;
    }
	else
		{
		return true;
		}
}     

function deleteRow(i)
{
var x = document.getElementById('showMappedTable').rows.length;
var flag=false;
document.getElementById('showMappedTable').deleteRow(i)
flag=true;
if(flag==true)
	{
	 window.alert("Record Deleted Successfully..!!!");
	}
	if(x==2)
	{
	 $('#showMappedData').hide();
	}
}

function showSaveErrorlb()
{
 var i,j;
 var selectVal = $("#fromLocalBodySetup").val();
 var fromUnitCode; 
 var len=errordata.length;
 var len2;
 var errorflag =true;
 var fromorgUnitCode;
 stateId=$("#stateId").val();
  // var toID = parseInt(document.getElementById("from_level_id").value) + 1;
   
/*   var toID = parseInt(document.getElementById("from_level_id").value) + 1;
	if(document.getElementById("from_level_"+toID).multiple == false){*/
  
   if(selectVal<=3)
	   {
	   
	   if((stateId==11||stateId==14 ||stateId==15||stateId==17||stateId==25||stateId==26||stateId==30||stateId==31||stateId==34 )&& selectVal==3)
		   {
		   fromorgUnitCode=$("#from_level_"+2).val();
		   }
	   else if((stateId==11||stateId==14 ||stateId==15||stateId==17||stateId==25||stateId==26||stateId==30||stateId==31||stateId==34 )&& selectVal==2)
		   {
		   fromorgUnitCode=$("#from_level_"+1).val();
		   }
	   else{
         fromorgUnitCode=$("#from_level_"+selectVal).val();
	      }
      }
   else
	   {
	   fromorgUnitCode=$("#from_level_"+1).val();
	   }
      len2=fromorgUnitCode.length;
      for (i = 0; i < len; i++) {
		for (j = 0; j <len2;  j++) {
			fromUnitCode=parseInt(fromorgUnitCode[j])
			
			if(errordata[i].toOrgUnitCode==fromUnitCode){
				 errorflag=false;
			}
		}
		}
    if(errorflag==false)
    {
		alert("This mapping is already exist");
		return false;
    }
	else
		{
		return true;
		}
}     









