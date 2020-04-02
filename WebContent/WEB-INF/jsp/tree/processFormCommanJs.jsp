<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/dwrRestructuredLocalBodyService.js"></script>

<script>


clearList=function(level){
	 if(level=="DPCHK"){
		 $("#DPChkpart").prop('checked', false);
		 $("#DPChkpartVP").prop('checked', false);
		 $('#targetDPList> option').appendTo('#sourceDPList');
			sortListBox('sourceDPList');
			removeAllOptions('targetDPList');
			 showHideDiv('');
		
	} else if(level=="IPCHK"){
		 $("#dpSelectBox option[value='']").attr("selected", "selected");
		 removeAllOptions('sourceIPList');
		 removeAllOptions('targetIPList');
		 $("#DPChkpartVP").prop('checked', false);
		 showHideDiv('');
		
	}else if(level=="VPCHK") {
		removeAllOptions('sourceVPList');
		 removeAllOptions('targetVpList');
		 removeAllOptions('ipSelectBoxvp');
		 $("#dpSelectBoxvp option[value='']").attr("selected", "selected");
		 showHideDiv('');
	}
	
};

showHideDiv=function(level){
	if ($("#DPChkFull").is(':checked')) {
		$("#tblDPFULL").show();
	}else{
		$("#tblDPFULL").hide();
		clearList(level);
	}
	if ($("#DPChkpart").is(':checked')) {
		populateDistrictList();
		$("#tblDPSelectBox").show();
		$("#tblIPSpecific").show();
	}else{
		$("#tblDPSelectBox").hide();
		$("#tblIPSpecific").hide();
		clearList(level);
	}
	
	if ($("#DPChkpartVP").is(':checked')) {
		$("#tblDPSelectBoxVP").show();
		$("#tblIPSelectBoxVP").show();
		$("#tblVPSpecific").show();
	}else{
		$("#tblDPSelectBoxVP").hide();
		$("#tblIPSelectBoxVP").hide();
		$("#tblVPSpecific").hide();
		clearList(level);
	}
	
	
	
	 
};


getDistrictPanchayatList=function(existdpCodes){
	lgdAdminDepatmentDwr.getDistrictPanchayatList(1,parseInt(_JS_STATE_CODE),existdpCodes, {
		callback : function(result){
			removeAllOptions('dpSelectBox')
			var options = $("#dpSelectBox");
			options.append($("<option />").val("").text("<spring:message htmlEscape='true' code='Label.SELECT'/>"));
			jQuery.each(result, function(index, obj) {
				var optionText = obj.localBodyNameEnglish;
				var option = $("<option />");
				option.val(obj.localBodyCode).text(optionText);
				options.append(option);
			});
			
			if(accessLevel=='Z' ){
				
					removeAllOptions('dpSelectBoxvp');
					copysourceListtoTargetList('dpSelectBox','dpSelectBoxvp');
				
			}
			
		},
		async : true
	});	
};
getLBChild=function(lbCode,ipId,isElement){
	_options_list = [];
	 _options_list.push(-99);
	if(ipId!=null){
		
		 $('#targetIPList option').each(function() { 
			  _options_list.push($(this).val());
		  });
		 
	}
	if(!isEmptyObject(lbCode)){
		lgdAdminDepatmentDwr.getParentwiseLocalBody(parseInt(lbCode),_options_list.toString(), {
			callback : function(result){
				removeAllOptions(ipId)
				var options = $("#"+ipId);
				if(isElement=="selectBox"){
					options.append($("<option />").val("").text("<spring:message htmlEscape='true' code='Label.SELECT'/>"));
					
				}
				jQuery.each(result, function(index, obj) {
					var optionText = obj.localBodyNameEnglish;
					var option = $("<option />");
					option.val(obj.localBodyCode).text(optionText);
					options.append(option);
				});
			},
			async : true
		});	
	}
}



populateDistrictList=function(){
	_options_list = [];
	 $('#targetDPList option').each(function() { 
		  _options_list.push($(this).val());
	  });
	 getDistrictPanchayatList(_options_list.toString());
};

/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElement=function(copyId,pasteId,isAction){
	
	$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
	sortListBox(copyId);
	sortListBox(pasteId);
	if(isAction=="IPChangePartialCoverage"){
		populateDistrictList();
		 removeAllOptions('sourceIPList');
		 removeAllOptions('targetIPList');
		 showHideDiv();
		 
	}
	
	if(accessLevel=='Z' ){
		
		
		if (copyId=='sourceIPList' || copyId=='targetIPList'){
			 removeAllOptions('sourceVPList');
			 removeAllOptions('targetVpList');
			 removeAllOptions('ipSelectBoxvp');
			 $("#DPChkpartVP").prop('checked', false);
			 $("#dpSelectBoxvp option[value='']").attr("selected", "selected");
			 showHideDiv();
			
		}
		
	}
	
};

/* The {@code removeAllOptions} used to clear drop down box based on their id. 
 */
 removeAllOptions=function(id){
 	dwr.util.removeAllOptions(id);
 };
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

copysourceListtoTargetList=function(copyId,pasteId){
	$('#'+copyId+' option').clone().appendTo('#'+pasteId);
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


</script>