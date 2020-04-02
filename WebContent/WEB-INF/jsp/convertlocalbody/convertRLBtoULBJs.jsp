<script>
$(document).ready(function() {
	
	$("#childbtn").click(function() {
		
		getCoveredVillagesList();
	});
});

/**
 * The {@code sortListBox} function used sort items of listbox ,
 * based on listbox id
 */
sortListBox=function(id){
	 var $r = $("#"+id+" option");
    $r.sort(function(a, b) {
        if (a.text < b.text) return -1;
        if (a.text == b.text) return 0;
        return 1;
    });
    $($r).remove();
    $("#"+id).append($($r));
    
    
};

/* The {@code removeAllOptions} used to clear drop down box based on their id. 
*/
removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
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

getCoveredVillagesList=function(){
	//alert("in");
	removeAllOptions('ddSourceCoveredAreaRLB');
	removeAllOptions('ddDestCoveredAreaRLB');
	 var lbCodes=[];
	var lbCodeArr=[];
	
	 $("#ddDestVillageRLBs option").each(function() { 
	if(($(this).val().indexOf("PART"))>-1){
			lbCodeArr=$(this).val().split("_");
			lbCodes.push(parseInt(lbCodeArr[0]));
		}
	});
	//alert("lbCodes"+lbCodes.toString()+"@len"+lbCodes.length);
	if(lbCodes.length>0){
		removeAllOptions('ddSourceCoveredAreaRLB');
		lgdDwrlocalBodyService.getCoveredVillagesofLocalbodies(lbCodes.toString(), {
						callback : function( result ) {
							var options = $("#ddSourceCoveredAreaRLB"); 
							
							jQuery.each(result, function(index, obj) {
								var option = $("<option />");
								var _value=obj.landRegionCode;
								var _text=obj.landRegionNameEnglish+"("+(obj.coverageType=='P'?"PART":"FULL")+")";
								
								if (obj.operation_state == 'F') {
									$(option).attr('disabled', 'disabled');
									var _text=obj.landRegionNameEnglish+"(being used as Drafted)";
								}
								
								option.val(_value).text(_text);
								options.append(option);
							});
						},
						errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
						async : true
					});
	}
};

validateMoveVillage=function(){
var flag=true;
var vpname=[];
var selvillage=[];
$( '#errorddSourceCoveredAreaRLB').text("");
$("#ddSourceCoveredAreaRLB option:selected").each(function() { 
	selvillage.push($(this).text())
});
$("#ddSourceCoveredAreaRLB option:selected").each(function() { 
	var _vill_name=$(this).text();
	var _temp=_vill_name.substring((_vill_name.indexOf("(")+1),_vill_name.indexOf(")"))
	var count=0;
	$("#ddSourceCoveredAreaRLB option").each(function() { 
		if(($(this).text().indexOf(_temp))>-1 && (selvillage.toString().indexOf($(this).text()))==-1){
			count++
		}

	});
	
	if(count<1){
		flag=false;
		if(vpname.toString().indexOf(_temp)==-1){
			vpname.push(_temp);
		}
		
		
	}
	
});
if(flag==false){
	$( '#errorddSourceCoveredAreaRLB').text("Can't select all villages of a partly selected Gram Panchayat("+vpname.toString()+")." ); 
}
return flag;
};


validateVillageCoverage=function(){
	$( '#errorddDestCoveredAreaRLB').text("");
	var _error_flag=true;
	var isPartFlag=false;
	$("#ddDestVillageRLBs option").each(function() { 
		if(($(this).val().indexOf("PART"))>-1){
			isPartFlag=true;
		}
	});
	
	if(isPartFlag){
		var isPartExistFlag;
		var isPartExist=new Array();
		var _part_text=[];
		var _notExist_part_name=[];
		var count;
		$("#ddDestVillageRLBs option").each(function() { 
			if(($(this).val().indexOf("PART"))>-1){
				_tmp=$(this).text();
				_part_text.push(_tmp.substring(0, _tmp.indexOf("(")));
			}
		});
				
		count=0;
		while(_part_text.length>0){
			 isPartExistFlag=false;
			 _isPart_name=_part_text.pop();
			$("#ddDestCoveredAreaRLB option").each(function() {
				_tmp=$(this).text();
				//alert(_temp);
			 if((_tmp.indexOf(_isPart_name))>-1 && isPartExistFlag==false){
				isPartExistFlag=true;
			 }	 
				
			});
			
			isPartExist[count]=isPartExistFlag;
			count++;
			 if(!isPartExistFlag){
				 _notExist_part_name.push(_isPart_name);
			}
			
		}
		
		for(i=0;i<isPartExist.length;i++){
			if(isPartExist[i]==false){
				$( '#errorddDestCoveredAreaRLB').text("Kindly Select Coverage from all Partly selected Gram Panchayats("+_notExist_part_name.toString()+")."); 
				_error_flag=false;
				$('#contibutingVillage').focus();	  
			}
		}
	}
	return _error_flag;
};

/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElement=function(action){
	 $( '#error_village_coverage').text(""); 
	var copyId=null;
	var pasteId=null;
	if(action=="FORWARD"||action=="PART"  ){
		copyId='ddSourceCoveredAreaRLB';
		pasteId='ddDestCoveredAreaRLB';
	}else if(action=="BACK" ){
		copyId='ddDestCoveredAreaRLB';
		pasteId='ddSourceCoveredAreaRLB'; 
		
	}
	
	 if(action=="BACK" ){
		 var options = $("#"+pasteId); 
			$('#'+copyId+' option:selected').each(function() { 
				var option = $("<option />");
				_tmp=$(this).val();
				_value=_tmp.substring(0, _tmp.lastIndexOf("@"));
				_text=$(this).text();
				if($(this).val().indexOf("@")==$(this).val().lastIndexOf("@")){
					_tmp=$(this).text();
					_text=_tmp.substring(0, _tmp.lastIndexOf("("));
					
				}
				option.val(_value).text(_text);
				options.append(option);
				$(this).remove();
				});
	}else if(action=="FORWARD" ){
		var partFlag=false;
		if(validateMoveVillage()){
			
			var options = $("#"+pasteId); 
			$('#'+copyId+' option:selected').each(function() { 
				var option = $("<option />");
				/* _value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)"; */
				if(($(this).text().indexOf("FULL"))>-1){
					_value=$(this).val()+"@F";
					_text=$(this).text()+"(FULL)";
					option.val(_value).text(_text);
					options.append(option);
					$(this).remove();
				}else{
					partFlag=true;
				}
			});
			
		}else{
			$( '#error_village_coverage').text("you can't select all coverage for partly localbody"); 
		}
		if(partFlag==true){
			$( '#error_village_coverage').text("you can't take part coverage as full"); 
		}
		
	}else if(action=="PART" ){
		var options = $("#"+pasteId); 
		$('#'+copyId+' option:selected').each(function() { 
			var option = $("<option />");
			_value=$(this).val()+"@P";
			_text=$(this).text()+"(PART)";
			if(($(this).val().indexOf("PART"))>-1){
				_value=$(this).val()+"@P";
				_text=$(this).text();
				
			}
			option.val(_value).text(_text);
			options.append(option);
			
			$(this).remove();
			
			});
	}
		
	
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};

moveElementVP=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL" || action=="PART"){
		copyId='ddSourceVillageRLBS';
		pasteId='ddDestVillageRLBs';
	}else if(action=="BACK" ||action=="RESET" ){
		copyId='ddDestVillageRLBs';
		pasteId='ddSourceVillageRLBS'; 
		
		removeAllOptions('ddSourceCoveredAreaRLB');
		removeAllOptions('ddDestCoveredAreaRLB');
	}
	
	if(action=="FULL" || action=="PART"){
		var allLen = $('#'+copyId+' option').length;
		var selLen = $('#'+copyId+' option:selected').length;
		
		var fullFlag=false;
		if(allLen==selLen){
			fullFlag=true;
			$('#'+pasteId+' option').each(function() {
				if($(this).val().indexOf("_PART")>-1){
					fullFlag=false;
				}
				
				});
		
		 }
		if(fullFlag){
			alert("you can't take all Garam Panchyat Full")
		}else{
			var options = $("#"+pasteId);
			$('#'+copyId+' option:selected').each(function() { 
				var option = $("<option />");
				option.val($(this).val()+"_"+action).text($(this).text()+"("+action+")");
				options.append(option);
				$(this).remove();
				});
		}
		
			
		
		
	}else if(action=="BACK" ){
	var options = $("#"+pasteId); 
	$('#'+copyId+' option:selected').each(function() { 
		var option = $("<option />");
		_tmp_text=$(this).text();
		_text=_tmp_text.substring(0, _tmp_text.lastIndexOf("("));
		_temp_val=$(this).val().split("_");
		option.val(_temp_val[0]).text(_text);
		options.append(option);
		$(this).remove();
		});
	}else if(action=="RESET" ){
		var options = $("#"+pasteId); 
		$('#'+copyId+' option').each(function() { 
			var option = $("<option />");
			_tmp_text=$(this).text();
			_text=_tmp_text.substring(0, _tmp_text.lastIndexOf("("));
			_temp_val=$(this).val().split("_");
			option.val(_temp_val[0]).text(_text);
			options.append(option);
			$(this).remove();
		});
}
sortListBox(copyId);
sortListBox(pasteId);
};


function validateForm(ctx) {
	var stateCode = document.getElementById("stateid").value;
	if (stateCode == 34 && fulLB == 'Y') {
		clickOnProceed();
		return true;
	}
	selectVillageRLBs();
	var errors = new Array();
	var error = false;
	var obj = document.getElementById('divDIV');
	if (obj.style.display == 'block' || obj.style.display == '') {
		errors[0] = !validatedistrictLB();
	}
	if (obj.style.display == 'block' || obj.style.display == '') {
		errors[1] = !validateintermediateLB();
		errors[2] = !validatevillageLB();
		errors[3] = !validatevillageLBforFULLandPART();
		errors[4] = !validatevillageDestCoveredAreas();
		errors[5] = !validateVillageCoverage();
	}

	for ( var i = 0; i <errors.length; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}

	if (error == true) {
		//showClientSideError();
		return false;
	} else {
		var vals = document.getElementById('ddDestVillageRLBs');
		if (vals != null) {
			var valArray = new Array();
			for ( var i = 0; i < vals.length; i++) {
				val = (vals[i].value).replace("_FULL", "");
				valArray[i] = parseInt(val);
			}
			lgdDwrlocalBodyService.isVillageExist(valArray, ctx, {
				callback : serverCallBack
			});
		}
	}
	return false;
}

</script>