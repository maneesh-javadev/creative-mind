<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrAssemblyService.js'></script>
<script type='text/javascript'>
$(document).ready(function() {
	loadParliamentMaptoLocalBody();
});


var lbfullArr=[];
var lbpartArr=[];
var lbPartOrg=[];
var villageArr=[];
var villageNameArr=[];
var vpnameArr=[];
var dpnameArr=[];
var ipnameArr=[];
var districtNameArray = [];
var subDistrictNameArray = [];
var urbannameArr=[];
var wardnameArr=[];
var wardCodeArr=[];
var dptypeName;
var iptypeName;
var vptypename;

var deleteRowArr=[];
var savedConstituncyArr=[];

var districtCode="";
var subdistrictCode="";
var villageCode="";

hideShowDivbyType=function(lbtype){
	removeAllList();
	loadParliamentMaptoLocalBody();
	if(!validateConsituency()){
		if(!$_checkEmptyObject( lbtype)){
			var lbArr=lbtype.split(":");
			var lbTypeCode=parseInt(lbArr[0]);
			var lbLevel=lbArr[1];
			var lbType=lbArr[2];
			var lbTypeName=lbArr[3];
			
				switch (lbLevel) {
				case 'D':
							getDistrictPanchyatList(lbType,1,true,'ddLgdDispandist',false);
							document.getElementById('divRuralDistrictPanchayat').style.display = 'block';
							break;
				case 'I':
							if (lbType != "U") {
								getDistrictPanchyatList(lbType,1,false,'ddLgdLBDistListAtInterCA',false);
								document.getElementById('divRuralIntermediatePanchayat').style.display = 'block';
							} else {
								$( '#lbl_header').text("Available "+lbTypeName+" List ");	 
								$( '#lbl_header_contri').text("Contribute "+lbTypeName+" List ");	 
								
								
								getDistrictPanchyatList(lbType,lbTypeCode,true,'ddLgdWardSubDistrictUListSource',true);
								document.getElementById('divSpecificStateforward').style.display = 'block';
							}
							break;
				case 'V':
							forDropdown = false;
							getDistrictPanchyatList(lbType,1,false,'ddLgdLBDistListAtVillageCA',false);
							document.getElementById('divRuralVillagePanchayat').style.display = 'block';
							break;
							
				
					 
				}
			
			
			
		}
	}else{
		$("#ddLgdLBType option[value='']").attr("selected", "selected");
		
	}
	
};

var $_checkEmptyObject = function(obj) {
 	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
 		return true;
 	}
 	return false;
 };
 
 var validateConsituency=function(){
	 var _error_flag=false;
	 var Parliament=$("#ddStateParliamnetSource");
	  if($_checkEmptyObject($( Parliament ).val())){
			_error_flag=true;
			$( Parliament ).addClass("error");
			$( '#error' + $( Parliament ).attr('id') ).text("<spring:message code='Label.SELECTPARLIAMENTCONSTITUENCY' htmlEscape='true'/>");	 
		}
	  var Assembly=$("#ddassemblySource");
	  if($_checkEmptyObject($( Assembly ).val())){
			_error_flag=true;
			$( Assembly ).addClass("error");
			$( '#error' + $( Assembly ).attr('id') ).text("<spring:message code='Label.SELECTASSEMBLYCONSTITUENCY' htmlEscape='true'/>");	 
		}
	  return _error_flag;
 };
 
 getAssemblyList =function(pcCode) {
	 clearAllData();
	var pcCodeVal= $( pcCode ).val();
	 if(!$_checkEmptyObject(pcCodeVal)){
		 $( '#error' + $( pcCode ).attr('id') ).text("");
		 lgdDwrAssemblyService.getAssemblyconstuincy(pcCodeVal, {
				callback : function( result ) {
					dwr.util.removeAllOptions('ddassemblySource');
					var options = $("#ddassemblySource"); 
					var option = $("<option/>");
					$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
					options.append(option);
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						
						option.val(obj.acCode).text(obj.acNameEnglish);
						options.append(option);
					});
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
	}
	
};

getDistrictPanchyatList=function(lbType,lbTypeCode,isList,objId,isUrban,forDropdown){
	lgdDwrlocalBodyService.getlbListforConstituencyMap(lbType,lbTypeCode,parseInt('${stateCode}'),null,parseInt('${districtCode}'),lbfullArr.toString(),lbpartArr.toString(),isList, {
		callback : function( result ) {
			dwr.util.removeAllOptions(objId);
			var options = $("#"+objId); 
			if(!isList){
				var option = $("<option/>");
				$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
				options.append(option);
			}
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				} else if(obj.isPart == 'T'){
					if(isList){
						if(!isUrban){
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish+"(Already used as Part)");
						}else{
							$(option).val(obj.localBodyCode+"_PART").text(obj.localBodyNameEnglish+"(PART)");
						}
						
					}else{
						$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					}
					options.append(option);	
				}
				else if(obj.operation_state == 'A') {
					$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
					options.append(option);
				}
				
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
	
};
getInterPanchyatList=function(dpCode,isList,objId){
	var dpCodeVal= $( dpCode ).val();
	 if(!$_checkEmptyObject(dpCodeVal)){
		 $( '#error' + $( dpCode ).attr('id') ).text("");
		 lgdDwrlocalBodyService.getlbListforConstituencyMap('L',2,parseInt('${stateCode}'),dpCodeVal,parseInt('${districtCode}'),lbfullArr.toString(),lbpartArr.toString(),isList, {
		 		callback : function( result ) {
		 			
		 			dwr.util.removeAllOptions(objId);
					var options = $("#"+objId);  
					if(!isList){
						var option = $("<option/>");
						$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
						options.append(option);
					}
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
							options.append(option);
						} else if(obj.isPart == 'T'){
							if(isList){
								$(option).attr('disabled', 'disabled');
								$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish+"(Already used as Part)");
							}else{
								$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
							}
							options.append(option);	
						}
						else if(obj.operation_state == 'A') {
							$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
							options.append(option);
						}
						
						
					});
		 		},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
	}
};

getVillagePanchyatList=function(ipCode,isList,objId){
	var ipCodeVal= $( ipCode ).val();
	$('#movePartVillage').hide();
	 if(!$_checkEmptyObject(ipCodeVal)){
		 $( '#error' + $( ipCode ).attr('id') ).text("");
		 lgdDwrlocalBodyService.getlbListforConstituencyMap('L',3,parseInt('${stateCode}'),ipCodeVal,parseInt('${districtCode}'),lbfullArr.toString(),lbpartArr.toString(),isList, {
				callback : function( result ) {
					dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
					var options = $("#ddLgdLBVillageSourceAtVillageCA"); 
					if(!isList){
						var option = $("<option/>");
						$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
						options.append(option);
					}
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
							options.append(option);
						} else if(obj.isPart == 'T'){
							if(isList){
								//$(option).attr('disabled', 'disabled');
								$(option).val(obj.localBodyCode+"@PART").text(obj.localBodyNameEnglish+"(Already used as Part)");
							}else{
								$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
							}
							options.append(option);	
						}
						else if(obj.operation_state == 'A') {
							$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
							options.append(option);
						}
						
						
					});
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
	}
};


getVillageList=function(){
	
	var vpCodeArr = [];
	$('#ddLgdLBVillageDestAtVillageCA option').each(function() { 
		if(($(this).val().indexOf("PART"))>-1){
			vpCodeArr.push($(this).val().split("@")[0]+$(this).val().split("@")[1]);
		}
	});
	if(vpCodeArr.length>0){
		var vpCodes=vpCodeArr.toString();
		//alert(vpCodes);
		lgdDwrlocalBodyService.getVillageListforConstituencyMap(vpCodes, {
			callback : function( result ) {
				dwr.util.removeAllOptions('ddLgdLBVillageSourceLatDCAforvillage');
				var options = $("#ddLgdLBVillageSourceLatDCAforvillage"); 
				var _value=null;
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					_value=obj.villageCode+"@"+obj.villageNameEnglish+"@"+obj.gpCode+"@"+obj.gpName+"@"+obj.ipName+"@"+obj.dpName;
					_text=obj.villageNameEnglish+"("+obj.gpName+")";
					if(obj.operation_state == 'A') {
						$(option).val(_value).text(_text);
						options.append(option);
					}else{
						$(option).attr('disabled', 'disabled');
						$(option).val(_value).text(_text);
						options.append(option);
					}
					
					
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	}
		
};


getWardList=function(){
	
	var urbanCodeArr = [];
	$('#ddLgdWardSubDistrictUListDest option').each(function() { 
		if(($(this).val().indexOf("PART"))>-1){
			if(($(this).val().indexOf("PART@"))>-1){
				urbanCodeArr.push($(this).val().split("@")[0].split("_")[0]+$(this).val().split("@")[1]);
			}else{
				urbanCodeArr.push($(this).val().split("@")[0]+$(this).val().split("@")[1]);
			}
			
		}
	});
	if(urbanCodeArr.length>0){
		var urbanCodes=urbanCodeArr.toString();
		//alert(vpCodes);
		lgdDwrlocalBodyService.getWardListforConstituencyMap(urbanCodes,wardCodeArr.toString(), {
			callback : function( result ) {
				dwr.util.removeAllOptions('ddLgdLBwardSourceLatDCA');
				var options = $("#ddLgdLBwardSourceLatDCA"); 
				var _value=null;
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					_value=obj.wardCode;
					_text=obj.wardNameEnglish+"("+obj.localBodyNameEnglish+")";
						$(option).val(_value).text(_text);
						options.append(option);
					
					
					
				});
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	}
		
};


moveElementIP=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL"){
		copyId='ddLgdLBInterPSourceList';
		pasteId='ddLgdLBInterPDestList';
	}else if(action=="BACK"){
		copyId='ddLgdLBInterPDestList';
		pasteId='ddLgdLBInterPSourceList'; 
		
	}
	
	if(action=="FULL" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("PART"))>-1)){
				var option = $("<option />");
				//_value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)";
				option.val($(this).val()).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			$("#cAlert").html(_part_list+" localbodies already part contributing");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>',
				resizable : false,
				height : 150,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
		
	}else if(action=="BACK" ){
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
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};

/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElementVP=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL" ||action=="PART" ){
		copyId='ddLgdLBVillageSourceAtVillageCA';
		pasteId='ddLgdLBVillageDestAtVillageCA';
	}else if(action=="BACK"){
		copyId='ddLgdLBVillageDestAtVillageCA';
		pasteId='ddLgdLBVillageSourceAtVillageCA'; 
		dwr.util.removeAllOptions('ddLgdLBVillageSourceLatDCAforvillage');
		dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCAforvillage');
	}
	
	 if(action=="PART" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId); 
		$('#'+copyId+' option:selected').each(function() { 
			var option = $("<option />");
			_value=$(this).val()+"@PART";
			_text=$(this).text()+"(PART)";
			if(($(this).val().indexOf("PART"))>-1){
				_value=$(this).val()+"@PART";
				_text=$(this).text();
				
			}
			option.val(_value).text(_text);
			options.append(option);
			
			$(this).remove();
			
			});
	}else if(action=="FULL" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("PART"))>-1)){
				var option = $("<option />");
				_value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)";
				option.val(_value).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			$("#cAlert").html(_part_list+"  localbodies already part contributing");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>',
				resizable : false,
				height : 150,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
		
	}else if(action=="BACK" ){
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
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};


moveElementVillage=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL"){
		copyId='ddLgdLBVillageSourceLatDCAforvillage';
		pasteId='ddLgdLBVillageDestLatDCAforvillage';
	}else if(action=="BACK"){
		copyId='ddLgdLBVillageDestLatDCAforvillage';
		pasteId='ddLgdLBVillageSourceLatDCAforvillage'; 
		
	}
	
	if(action=="FULL" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("PART"))>-1)){
				var option = $("<option />");
				_value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)";
				option.val(_value).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			$("#cAlert").html(_part_list+" subdistricts already part contributing");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>',
				resizable : false,
				height : 150,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
		
	}else if(action=="BACK" ){
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
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};



/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElementURBAN=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL" ||action=="PART" ){
		copyId='ddLgdWardSubDistrictUListSource';
		pasteId='ddLgdWardSubDistrictUListDest';
	}else if(action=="BACK"){
		copyId='ddLgdWardSubDistrictUListDest';
		pasteId='ddLgdWardSubDistrictUListSource'; 
		dwr.util.removeAllOptions('ddLgdLBwardSourceLatDCA');
		dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCA');
	}
	
	 if(action=="PART" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId); 
		$('#'+copyId+' option:selected').each(function() { 
			var option = $("<option />");
			_value=$(this).val()+"@PART";
			_text=$(this).text()+"(PART)";
			if(($(this).val().indexOf("PART"))>-1){
				_value=$(this).val()+"@PART";
				_text=$(this).text()+"(PART)";
				
			}
			option.val(_value).text(_text);
			options.append(option);
			
			$(this).remove();
			
			});
	}else if(action=="FULL" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("PART"))>-1)){
				var option = $("<option />");
				_value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)";
				option.val(_value).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			$("#cAlert").html(_part_list+" localbodies already part contributing");
			$("#cAlert").dialog({
				title :'<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>',
				resizable : false,
				height : 150,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
		
	}else if(action=="BACK" ){
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
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};

moveElementWard=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL"){
		copyId='ddLgdLBwardSourceLatDCA';
		pasteId='ddLgdLBVillageDestLatDCA';
	}else if(action=="BACK"){
		copyId='ddLgdLBVillageDestLatDCA';
		pasteId='ddLgdLBwardSourceLatDCA'; 
		
	}
	
	if(action=="FULL" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("PART"))>-1)){
				var option = $("<option />");
				_value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)";
				option.val(_value).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			$("#cAlert").html(_part_list+"  localbodies already part contributing");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>',
				resizable : false,
				height : 150,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
		
	}else if(action=="BACK" ){
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
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};

moveElementDP=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL"){
		copyId='ddLgdDispandist';
		pasteId='ddLgdLBDistPList';
	}else if(action=="BACK"){
		copyId='ddLgdLBDistPList';
		pasteId='ddLgdDispandist'; 
		
	}
	
	if(action=="FULL" ){
		//$('#'+copyId+'> option:selected').appendTo('#'+pasteId);
		var options = $("#"+pasteId);
		var partToFullFlag=false;
		var _part_list="";
		$('#'+copyId+' option:selected').each(function() { 
			
			if(!(parseInt($(this).val().indexOf("PART"))>-1)){
				var option = $("<option />");
				//_value=$(this).val()+"@FULL";
				_text=$(this).text()+"(FULL)";
				option.val($(this).val()).text(_text);
				options.append(option);
				$(this).remove();
			}else{
				partToFullFlag=true;
				_temp=$(this).text();
				_part_list=_part_list+_temp.substring(0,_temp.indexOf("("))+",";
			}
			
			
			});
		if(partToFullFlag){
			_part_list=_part_list.substring(0,_part_list.length-1);
			$("#cAlert").html(_part_list+"  localbodies already part contributing");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message>',
				resizable : false,
				height : 150,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
		
	}else if(action=="BACK" ){
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
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};

var getHierarchyDetails=function(sel_lbTypeCode){
	var hd=[];
	if(sel_lbTypeCode<=3){
		$('#ddLgdLBType option').each(function() {
			if(!$_checkEmptyObject( $(this).val()) ){
				var lbTypeCode=$(this).val().split(":")[0];
				if(sel_lbTypeCode>=lbTypeCode){
					hd.push($(this).text());
				}
				
			}
		});
	}else{
		
	}
	
	return (hd.toString());
};

addCoverdArea=function(){
	
	var lbtype=$("#ddLgdLBType").val();
	if(!validateConsituency()){
		if(!$_checkEmptyObject( lbtype)){
			var lbArr=lbtype.split(":");
			var lbTypeCode=parseInt(lbArr[0]);
			var lbLevel=lbArr[1];
			var lbType=lbArr[2];
			var lbTypeName=lbArr[3];
			$('#dynamicTbl tr').not(function(){ return !!$(this).has('th').length; }).remove();
			var hdArr=getHierarchyDetails(lbTypeCode);
			
			switch (lbLevel) {
			case 'D': 
						
						dptypeName=lbTypeName;
						if(validateList('ddLgdLBDistPList',lbLevel)){
							$("#ddLgdLBDistPList option").each(function() { 
								lbfullArr.push($(this).val()+"_D");
								dpnameArr.push($(this).text()+"_"+$(this).val()+"_"+lbTypeCode+"@"+hdArr.toString());
								});
						}
						removeElement(lbLevel,lbType);
						updateCoverage();
						break;
			case 'I':
						var dpObj=$("#ddLgdLBDistListAtInterCA option:selected");
						hd=hdArr.split(",")[1]+", "+$(dpObj).text()+" "+hdArr.split(",")[0];
						
				         iptypeName=lbTypeName;
						if (lbType != "U") {
							if(validateList('ddLgdLBInterPDestList',lbLevel)){
								$("#ddLgdLBInterPDestList option").each(function() { 
									lbfullArr.push($(this).val()+"_T");
									ipnameArr.push($(this).text()+"_"+$(this).val()+"_"+lbTypeCode+"@"+hd)
									});
								
								lbpartArr.push($(dpObj).val());
								
							}
							removeElement(lbLevel,lbType);
							updateCoverage();
						} else {
							
							if(validateList('ddLgdWardSubDistrictUListDest',lbLevel) && validateUrbanCoverage()){
								$("#ddLgdWardSubDistrictUListDest option").each(function() { 
									if(($(this).val().indexOf("PART"))>-1){
										if($(this).val().indexOf("PART")==$(this).val().lastIndexOf("PART")){
											lbpartArr.push($(this).val().split("@")[0]);
											lbPartOrg.push($(this).val().split("@")[0]);
											urbannameArr.push($(this).text()+"_"+$(this).val().split("@")[0]+"_"+lbTypeCode+"@"+lbTypeName)
										}
										
										
									}else{
										lbfullArr.push($(this).val().split("@")[0]);
										urbannameArr.push($(this).text()+"_"+$(this).val().split("@")[0]+"_"+lbTypeCode+"@"+lbTypeName)
									}
									});
								
								
								$("#ddLgdLBVillageDestLatDCA option").each(function() { 
									
									var lbname;		
									wardCodeArr.push($(this).val().split("@")[0]);
									name=$(this).text().substring(0, $(this).text().indexOf("(FULL)"));
									if(name.lastIndexOf("(")>-1){
										lbname=name.substring((name.lastIndexOf("(")+1),(name.length-1));
										name=name.substring(0, name.lastIndexOf("("));
										
									}
									hd="Ward of "+lbname+" "+lbTypeName;
									wardnameArr.push(name+"(FULL)"+"_"+$(this).val().split("@")[0]+"@"+hd);
									});
								
								
							}
							removeElement(lbLevel,lbType);
							updateCoverage();
						}
						
					
						break;
			case 'V':
				var dpObj=$("#ddLgdLBDistListAtVillageCA option:selected");
				var ipObj=$("#ddLgdLBInterListAtVillageCA option:selected");
				hd=hdArr.split(",")[2]+", "+$(ipObj).text()+" "+hdArr.split(",")[1]+", "+$(dpObj).text()+" "+hdArr.split(",")[0];
					vptypeName=lbTypeName;
					if(validateDP() && validateIP() && validateList('ddLgdLBVillageDestAtVillageCA',lbLevel)){
					$("#ddLgdLBVillageDestAtVillageCA option").each(function() { 
						
						if(($(this).val().indexOf("PART"))>-1){
							if($(this).val().indexOf("PART")==$(this).val().lastIndexOf("PART")){
								lbpartArr.push($(this).val().split("@")[0]);
								lbPartOrg.push($(this).val().split("@")[0]);
								vpnameArr.push($(this).text()+"_"+$(this).val().split("@")[0]+"_"+lbTypeCode+"@"+hd)
							}
							
							
						}else{
							lbfullArr.push($(this).val().split("@")[0]+"_V");
							vpnameArr.push($(this).text()+"_"+$(this).val().split("@")[0]+"_"+lbTypeCode+"@"+hd)
						}
					});
					
					$("#ddLgdLBVillageDestLatDCAforvillage option").each(function() { 
					hd="Villages,"+$(this).val().split("@")[3]+" "+hdArr.split(",")[2]+", "+$(ipObj).text()+" "+hdArr.split(",")[1]+", "+$(dpObj).text()+" "+hdArr.split(",")[0];
							
					villageArr.push($(this).val().split("@")[0]);
					villageNameArr.push($(this).text()+"_"+$(this).val().split("@")[0]);
					});
					
					
					lbpartArr.push($(dpObj).val());
					lbpartArr.push($(ipObj).val());
					
				}
					
					removeElement(lbLevel,lbType);
					updateCoverage();
					break;
				
			}
			
			
			
			
		}
	}else{
		$("#ddLgdLBType option[value='']").attr("selected", "selected");
		
	}
};


updateCoverage=function(){
	//var divTemplate = $("#dynamicTbl");
	var tbody=$("#dynamicTbl");
	var no=1,lvalno=1,rno=0;
	if(dpnameArr.length>0){
		
		$.each( dpnameArr, function( key, value ) {
			rno=parseInt(no)+key;
			var trdp= $("<TR/>");
			trdp.attr("id",rno);
			 //trdp.addClass('tblRowB');
			 var hd=value.split("@")[1];
			 var lbArr=value.split("@")[0].split("_");
			 var name=value.substring(0, lbArr[0].indexOf("("));
			 var coverage="PART";
			 if(lbArr[0].indexOf("FULL")>-1){
				 coverage="FULL";
			 }
			 var tdTempCol1=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelSno = $("<label/>");
			 templateLabelSno.html(parseInt(no)+key);
			 tdTempCol1.append(templateLabelSno);
			 
			/*  var tdTempCol2=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			
				 var templateLabellbType = $("<label/>");
				 if(key==0){
				 templateLabellbType.html(dptypeName);
				  }else{
				 templateLabellbType.html(""); 
				  }
				 tdTempCol2.append(templateLabellbType); */
	
			 
			 var tdTempCol3=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelname = $("<label/>");
			 templateLabelname.html(name);
			 tdTempCol3.append(templateLabelname);
			 
			
			 var tdTempCol4=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelcoverage = $("<label/>");
			 templateLabelcoverage.html(coverage);
			 tdTempCol4.append(templateLabelcoverage);
			 
			 var tdTempCol5=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelhd = $("<label/>");
			 templateLabelhd.html(hd);
			 tdTempCol5.append(templateLabelhd);
			 
			/*  var btn = $('<input/>');
			 btn.attr("type","button");
			 btn.attr("className", "btn");
			 btn.attr("value" ,"Delete");
			 var aggh=parseInt(no)+parseInt(key);
			 btn.attr("onclick","deleteRowData("+aggh+")"); 
			  var tdTempCol6=$("<TD/>");
			 tdTempCol6.append(btn); */
			 var tdTempCol6=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","deleteRowData("+rno+","+lbArr[1]+","+lbArr[2]+")"); 
			
			 var imgTemp=$('<IMG />'); 
			 imgTemp.attr("src","images/delete.png");
			 imgTemp.attr("width","18");
			 imgTemp.attr("height","19");
			 imgTemp.attr("border","0");
			 templateAnchor.append(imgTemp);
			 tdTempCol6.append(templateAnchor); 
			 
			 trdp.append(tdTempCol1);
			 /* trdp.append(tdTempCol2); */
			 trdp.append(tdTempCol3);
			 trdp.append(tdTempCol4);
			 trdp.append(tdTempCol5);
			 trdp.append(tdTempCol6);
			 tbody.append(trdp);
			 
			 lvalno=key+no+1;
		});
	}
	no=lvalno;
	if(ipnameArr.length>0){
		
		$.each( ipnameArr, function( key, value ) {
			rno=parseInt(no)+key;
			var trip= $("<TR/>");
			
			 trip.attr("id",rno);
			  var hd=value.split("@")[1];
			  
			  var lbArr=value.split("@")[0].split("_");
				 var name=value.substring(0, lbArr[0].indexOf("("));
				 var coverage="PART";
				 if(lbArr[0].indexOf("FULL")>-1){
					 coverage="FULL";
			 }
			 
			 var tdTempCol1=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelSno = $("<label/>");
			 templateLabelSno.html(parseInt(no)+key);
			 tdTempCol1.append(templateLabelSno);
			 
			/*  var tdTempCol2=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			
				 var templateLabellbType = $("<label/>");
				if(key==0){
				 templateLabellbType.html(iptypeName);
				 }else{
				 templateLabellbType.html(""); 
						 
				 }
				 tdTempCol2.append(templateLabellbType); */
	
				 var tdTempCol3=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelname = $("<label/>");
				 templateLabelname.html(name);
				 tdTempCol3.append(templateLabelname);
				 
				
				 var tdTempCol4=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelcoverage = $("<label/>");
				 templateLabelcoverage.html(coverage);
				 tdTempCol4.append(templateLabelcoverage);
				 
				 var tdTempCol5=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelhd = $("<label/>");
				 templateLabelhd.html(hd);
				 tdTempCol5.append(templateLabelhd); 
				 
				 /* var btn = $('<input/>');
				 btn.attr("type","button");
				 btn.attr("className", "btn");
				 btn.attr("value" ,"Delete");
				 var aggh=parseInt(no)+parseInt(key);
				 btn.attr("onclick","deleteRowData("+aggh+")"); 
				  var tdTempCol6=$("<TD/>");
				 tdTempCol6.append(btn);
			 */
			 
			
			 
				var tdTempCol6=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","deleteRowData("+rno+","+lbArr[1]+","+lbArr[2]+")"); 
			
			 var imgTemp=$('<IMG />'); 
			 imgTemp.attr("src","images/delete.png");
			 imgTemp.attr("width","18");
			 imgTemp.attr("height","19");
			 imgTemp.attr("border","0");
			 templateAnchor.append(imgTemp);
			 tdTempCol6.append(templateAnchor); 
			 
			 trip.append(tdTempCol1);
			/*  trip.append(tdTempCol2); */
			 trip.append(tdTempCol3);
			 trip.append(tdTempCol4);
			 trip.append(tdTempCol5);
			 trip.append(tdTempCol6);
			tbody.append(trip);
			 
			lvalno=key+no+1;
		});
	}
	
	no=lvalno;
if(vpnameArr.length>0){
		
		$.each( vpnameArr, function( key, value ) {
			rno=parseInt(no)+key;
			var trvp= $("<TR/>");
			trvp.attr("id",rno);
			 //trvp.addClass('tblRowB');
			   var hd=value.split("@")[1];
			   var lbArr=value.split("@")[0].split("_");
				 var name=value.substring(0, lbArr[0].indexOf("("));
				 var coverage="PART";
				 if(lbArr[0].indexOf("FULL")>-1){
					 coverage="FULL";
				 }
			 
			 var tdTempCol1=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelSno = $("<label/>");
			 templateLabelSno.html(parseInt(no)+key);
			 tdTempCol1.append(templateLabelSno);
			 
			/*  var tdTempCol2=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			
				 var templateLabellbType = $("<label/>");
				 if(key==0){
				 templateLabellbType.html(vptypeName);
				}else{
				 templateLabellbType.html(""); 
						 
				 }
				 tdTempCol2.append(templateLabellbType); */
	
				 var tdTempCol3=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelname = $("<label/>");
				 templateLabelname.html(name);
				 tdTempCol3.append(templateLabelname);
				 
				
				 var tdTempCol4=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelcoverage = $("<label/>");
				 templateLabelcoverage.html(coverage);
				 tdTempCol4.append(templateLabelcoverage);
				 
				 var tdTempCol5=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelhd = $("<label/>");
				 templateLabelhd.html(hd);
				 tdTempCol5.append(templateLabelhd); 
				 
			/* 	 var btn = $('<input/>');
				 btn.attr("type","button");
				 btn.attr("className", "btn");
				 btn.attr("value" ,"Delete");
				 var aggh=parseInt(no)+parseInt(key);
				 btn.attr("onclick","deleteRowData("+aggh+")"); 
				  var tdTempCol6=$("<TD/>");
				 tdTempCol6.append(btn); */
			
			 
			 var tdTempCol6=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","deleteRowData("+rno+","+lbArr[1]+","+lbArr[2]+")"); 
			
			 var imgTemp=$('<IMG />'); 
			 imgTemp.attr("src","images/delete.png");
			 imgTemp.attr("width","18");
			 imgTemp.attr("height","19");
			 imgTemp.attr("border","0");
			 templateAnchor.append(imgTemp);
			 tdTempCol6.append(templateAnchor); 
			 
			 trvp.append(tdTempCol1);
			/*  trvp.append(tdTempCol2); */
			 trvp.append(tdTempCol3);
			 trvp.append(tdTempCol4);
			 trvp.append(tdTempCol5);
			 trvp.append(tdTempCol6);
			tbody.append(trvp);
			 
			 lvalno=key+no+1;
		});
	}
	
no=lvalno;
if(districtNameArray.length>0){
		
		$.each( districtNameArray, function( key, value ) {
			rno=parseInt(no)+key;
			var trv= $("<TR/>");
			trv.attr("id",rno);
			  var villArr=value.split("_");
			
			   var name=villArr[0];
			   var villageCode=villArr[1];
			   hd="District";
			   coverage="FULL";
				
			 var tdTempCol1=$("<TD/>");
			 var templateLabelSno = $("<label/>");
			 templateLabelSno.html(parseInt(no)+key);
			 tdTempCol1.append(templateLabelSno);
	
				 var tdTempCol3=$("<TD/>");
				 var templateLabelname = $("<label/>");
				 templateLabelname.html(name);
				 tdTempCol3.append(templateLabelname);
				 
				
				 var tdTempCol4=$("<TD/>");
				 var templateLabelcoverage = $("<label/>");
				 templateLabelcoverage.html(coverage);
				 tdTempCol4.append(templateLabelcoverage);
				 
				 var tdTempCol5=$("<TD/>");
				 var templateLabelhd = $("<label/>");
				 templateLabelhd.html(hd);
				 tdTempCol5.append(templateLabelhd); 
			 
			  var tdTempCol6=$("<TD/>");
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","deleteRowData("+rno+","+villageCode+",'D')"); 
			
			 var imgTemp=$('<IMG />'); 
			 imgTemp.attr("src","images/delete.png");
			 imgTemp.attr("width","18");
			 imgTemp.attr("height","19");
			 imgTemp.attr("border","0");
			 templateAnchor.append(imgTemp);
			 tdTempCol6.append(templateAnchor); 
			 
			 trv.append(tdTempCol1);
			 trv.append(tdTempCol3);
			 trv.append(tdTempCol4);
			 trv.append(tdTempCol5);
			 trv.append(tdTempCol6);
			tbody.append(trv);
			 
			 lvalno=key+no+1;
		});
	}

no=lvalno;
if(subDistrictNameArray.length>0){
		
		$.each( subDistrictNameArray, function( key, value ) {
			rno=parseInt(no)+key;
			var trv= $("<TR/>");
			trv.attr("id",rno);
			  var villArr=value.split("_");
			
			   var name=villArr[0];
			   var villageCode=villArr[1];
			   hd="SubDistrict";
			   coverage="FULL";
				
			 var tdTempCol1=$("<TD/>");
			 var templateLabelSno = $("<label/>");
			 templateLabelSno.html(parseInt(no)+key);
			 tdTempCol1.append(templateLabelSno);
	
				 var tdTempCol3=$("<TD/>");
				 var templateLabelname = $("<label/>");
				 templateLabelname.html(name);
				 tdTempCol3.append(templateLabelname);
				 
				
				 var tdTempCol4=$("<TD/>");
				 var templateLabelcoverage = $("<label/>");
				 templateLabelcoverage.html(coverage);
				 tdTempCol4.append(templateLabelcoverage);
				 
				 var tdTempCol5=$("<TD/>");
				 var templateLabelhd = $("<label/>");
				 templateLabelhd.html(hd);
				 tdTempCol5.append(templateLabelhd); 
			 
			  var tdTempCol6=$("<TD/>");
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","deleteRowData("+rno+","+villageCode+",'T')"); 
			
			 var imgTemp=$('<IMG />'); 
			 imgTemp.attr("src","images/delete.png");
			 imgTemp.attr("width","18");
			 imgTemp.attr("height","19");
			 imgTemp.attr("border","0");
			 templateAnchor.append(imgTemp);
			 tdTempCol6.append(templateAnchor); 
			 
			 trv.append(tdTempCol1);
			 trv.append(tdTempCol3);
			 trv.append(tdTempCol4);
			 trv.append(tdTempCol5);
			 trv.append(tdTempCol6);
			tbody.append(trv);
			 
			 lvalno=key+no+1;
		});
	}

no=lvalno;
if(villageNameArr.length>0){
		
		$.each( villageNameArr, function( key, value ) {
			rno=parseInt(no)+key;
			var trv= $("<TR/>");
			trv.attr("id",rno);
			 //trvp.addClass('tblRowB');
			  var villArr=value.split("_");
			
			   var name=value.split("_")[0];
			   var villageCode=value.split("_")[1];
			   hd="Village";
			   coverage="FULL";
				
			 var tdTempCol1=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelSno = $("<label/>");
			 templateLabelSno.html(parseInt(no)+key);
			 tdTempCol1.append(templateLabelSno);
			 
			/*  var tdTempCol2=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			
				 var templateLabellbType = $("<label/>");
				 if(key==0){
				 templateLabellbType.html("Village");
				}else{
				 templateLabellbType.html(""); 
						 
				 }
				 tdTempCol2.append(templateLabellbType); */
	
				 var tdTempCol3=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelname = $("<label/>");
				 templateLabelname.html(name);
				 tdTempCol3.append(templateLabelname);
				 
				
				 var tdTempCol4=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelcoverage = $("<label/>");
				 templateLabelcoverage.html(coverage);
				 tdTempCol4.append(templateLabelcoverage);
				 
				 var tdTempCol5=$("<TD/>");
				 //tdTemp1.attr("width", "40%" );
				 var templateLabelhd = $("<label/>");
				 templateLabelhd.html(hd);
				 tdTempCol5.append(templateLabelhd); 
				 
			/* 	 var btn = $('<input/>');
				 btn.attr("type","button");
				 btn.attr("className", "btn");
				 btn.attr("value" ,"Delete");
				 var aggh=parseInt(no)+parseInt(key);
				 btn.attr("onclick","deleteRowData("+aggh+")"); 
				  var tdTempCol6=$("<TD/>");
				 tdTempCol6.append(btn);
			 */
			 
			  var tdTempCol6=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","deleteRowData("+rno+","+villageCode+",'V')"); 
			
			 var imgTemp=$('<IMG />'); 
			 imgTemp.attr("src","images/delete.png");
			 imgTemp.attr("width","18");
			 imgTemp.attr("height","19");
			 imgTemp.attr("border","0");
			 templateAnchor.append(imgTemp);
			 tdTempCol6.append(templateAnchor); 
			 
			 trv.append(tdTempCol1);
			/*  trv.append(tdTempCol2); */
			 trv.append(tdTempCol3);
			 trv.append(tdTempCol4);
			 trv.append(tdTempCol5);
			 trv.append(tdTempCol6);
			tbody.append(trv);
			 
			 lvalno=key+no+1;
		});
	}
	
no=lvalno;
if(urbannameArr.length>0){
	
	$.each( urbannameArr, function( key, value ) {
		rno=parseInt(no)+key;
		var trvp= $("<TR/>");
		trvp.attr("id",rno);
		 //trvp.addClass('tblRowB');
		   var hd=value.split("@")[1];
		   var lbArr=value.split("@")[0].split("_");
			 var name=value.substring(0, lbArr[0].indexOf("("));
			 var code = lbArr[1];
			 var entityType = lbArr[2]
			 var coverage="PART";
			 if(lbArr[0].indexOf("FULL")>-1){
				 coverage="FULL";
			 }
		 
		 var tdTempCol1=$("<TD/>");
		 //tdTemp1.attr("width", "40%" );
		 var templateLabelSno = $("<label/>");
		 templateLabelSno.html(parseInt(no)+key);
		 tdTempCol1.append(templateLabelSno);
		 
		/*  var tdTempCol2=$("<TD/>");
		 //tdTemp1.attr("width", "40%" );
		
			 var templateLabellbType = $("<label/>");
			 if(key==0){
			 templateLabellbType.html(vptypeName);
			}else{
			 templateLabellbType.html(""); 
					 
			 }
			 tdTempCol2.append(templateLabellbType); */

			 var tdTempCol3=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelname = $("<label/>");
			 templateLabelname.html(name);
			 tdTempCol3.append(templateLabelname);
			 
			
			 var tdTempCol4=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelcoverage = $("<label/>");
			 templateLabelcoverage.html(coverage);
			 tdTempCol4.append(templateLabelcoverage);
			 
			 var tdTempCol5=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelhd = $("<label/>");
			 templateLabelhd.html(hd);
			 tdTempCol5.append(templateLabelhd); 
			 
		/* 	 var btn = $('<input/>');
			 btn.attr("type","button");
			 btn.attr("className", "btn");
			 btn.attr("value" ,"Delete");
			 var aggh=parseInt(no)+parseInt(key);
			 btn.attr("onclick","deleteRowData("+aggh+")"); 
			  var tdTempCol6=$("<TD/>");
			 tdTempCol6.append(btn); */
		
		 
		 var tdTempCol6=$("<TD/>");
		 //tdTemp1.attr("width", "40%" );
		 var templateAnchor = $("<A/>");
		 templateAnchor.attr("href", "#" );//"deleteRowData("+rno+","+code+",'4')"
		 templateAnchor.attr("onclick","deleteRowData("+rno+","+code+","+entityType+")"); 
		
		 var imgTemp=$('<IMG />'); 
		 imgTemp.attr("src","images/delete.png");
		 imgTemp.attr("width","18");
		 imgTemp.attr("height","19");
		 imgTemp.attr("border","0");
		 templateAnchor.append(imgTemp);
		 tdTempCol6.append(templateAnchor); 
		 
		 trvp.append(tdTempCol1);
		/*  trvp.append(tdTempCol2); */
		 trvp.append(tdTempCol3);
		 trvp.append(tdTempCol4);
		 trvp.append(tdTempCol5);
		 trvp.append(tdTempCol6);
		tbody.append(trvp);
		 
		 lvalno=key+no+1;
	});
}

no=lvalno;
if(wardnameArr.length>0){
	
	$.each( wardnameArr, function( key, value ) {
		rno=parseInt(no)+key;
		var trvp= $("<TR/>");
		trvp.attr("id",rno);
		 //trvp.addClass('tblRowB');
		   var hd=value.split("@")[1];
		   var lbArr=value.split("@")[0].split("_");
			 var name=value.substring(0, lbArr[0].indexOf("(FULL)"));
			 var coverage="PART";
			 if(lbArr[0].indexOf("FULL")>-1){
				 coverage="FULL";
			 }
		 
		 var tdTempCol1=$("<TD/>");
		 //tdTemp1.attr("width", "40%" );
		 var templateLabelSno = $("<label/>");
		 templateLabelSno.html(parseInt(no)+key);
		 tdTempCol1.append(templateLabelSno);
		 
		/*  var tdTempCol2=$("<TD/>");
		 //tdTemp1.attr("width", "40%" );
		
			 var templateLabellbType = $("<label/>");
			 if(key==0){
			 templateLabellbType.html(vptypeName);
			}else{
			 templateLabellbType.html(""); 
					 
			 }
			 tdTempCol2.append(templateLabellbType); */

			 var tdTempCol3=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelname = $("<label/>");
			 templateLabelname.html(name);
			 tdTempCol3.append(templateLabelname);
			 
			
			 var tdTempCol4=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelcoverage = $("<label/>");
			 templateLabelcoverage.html(coverage);
			 tdTempCol4.append(templateLabelcoverage);
			 
			 var tdTempCol5=$("<TD/>");
			 //tdTemp1.attr("width", "40%" );
			 var templateLabelhd = $("<label/>");
			 templateLabelhd.html(hd);
			 tdTempCol5.append(templateLabelhd); 
			 
		/* 	 var btn = $('<input/>');
			 btn.attr("type","button");
			 btn.attr("className", "btn");
			 btn.attr("value" ,"Delete");
			 var aggh=parseInt(no)+parseInt(key);
			 btn.attr("onclick","deleteRowData("+aggh+")"); 
			  var tdTempCol6=$("<TD/>");
			 tdTempCol6.append(btn); */
		
		 
		 var tdTempCol6=$("<TD/>");
		 //tdTemp1.attr("width", "40%" );
		 var templateAnchor = $("<A/>");
		 templateAnchor.attr("href", "#" );
		 templateAnchor.attr("onclick","deleteRowData("+rno+","+lbArr[1]+",'W')"); 
		 
		 var imgTemp=$('<IMG />'); 
		 imgTemp.attr("src","images/delete.png");
		 imgTemp.attr("width","18");
		 imgTemp.attr("height","19");
		 imgTemp.attr("border","0");
		 templateAnchor.append(imgTemp);
		 tdTempCol6.append(templateAnchor); 
		 
		 trvp.append(tdTempCol1);
		/*  trvp.append(tdTempCol2); */
		 trvp.append(tdTempCol3);
		 trvp.append(tdTempCol4);
		 trvp.append(tdTempCol5);
		 trvp.append(tdTempCol6);
		tbody.append(trvp);
		 
		 lvalno=key+no+1;
	});
}
	
no=lvalno;
//divTemplate.append(tbody);
	
};

validateDP=function(){
var dp=$("#ddLgdLBDistListAtVillageCA");
	  if($_checkEmptyObject($( dp ).val())){
			$( dp ).addClass("error");
			$( '#error' + $( dp ).attr('id') ).text("<spring:message code='Label.SELECT' htmlEscape='true'/>"+" ${localGovtBodyForm.lgd_LBNomenclatureDist}");	 
			return false;
	}
  return true;
};


validateIP=function(){
	var Ip=$("#ddLgdLBInterListAtVillageCA");
		  if($_checkEmptyObject($( Ip ).val())){
				$( Ip ).addClass("error");
				$( '#errorddLgdLBInterListAtVillageCA').text("<spring:message code='Label.SELECT' htmlEscape='true'/>"+" ${localGovtBodyForm.lgd_LBNomenclatureInter}");	 
				return false;
		}
	  return true;
	};
	
	validateList=function(id,type){
		
		var len=$("#"+id+" option").length;
		if(len<1){
			switch(type){
			case 'D':
					message="<spring:message code='Label.SELECT' htmlEscape='true'/>"+" Contributing "+" ${localGovtBodyForm.lgd_LBNomenclatureDist}";
					break;
			case 'V':
					message="<spring:message code='Label.SELECT' htmlEscape='true'/>"+" Contributing "+" ${localGovtBodyForm.lgd_LBNomenclatureVillage}";
					break;
					
			case 'I':
				message="<spring:message code='Label.SELECT' htmlEscape='true'/>"+" Contributing "+" <spring:message code='Label.CONTRIBUTPANCHAYATLIST' htmlEscape='true'/>";
				break;
			}
			
			
			$("#"+id).addClass("error");
			$( '#error' + $("#"+id ).attr('id') ).text(message);	 
			return false;
		}
		return true;
		
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
removeErrorAssembly=function(){
	 $( '#errorddassemblySource').text("");
};

function loadParliamentMaptoLocalBodyOnCoverageAdd() {
	document.getElementById('divSpecificStateforward').style.display = 'none';
// 	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	document.getElementById('divRuralDistrictPanchayatforinter').style.display = 'none';
	document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
	document.getElementById('divRuralVillagePanchayat').style.display = 'none';
}

function loadParliamentMaptoLocalBody() {
	document.getElementById('divSpecificStateforward').style.display = 'none';
	document.getElementById('divRuralDistrictPanchayat').style.display = 'none';
	document.getElementById('divRuralDistrictPanchayatforinter').style.display = 'none';
	document.getElementById('divRuralIntermediatePanchayat').style.display = 'none';
	document.getElementById('divRuralVillagePanchayat').style.display = 'none';
}

removeElement=function(type,lbType){
	$("#ddLgdLBType option[value='']").attr("selected", "selected");
	switch (type) {
	case 'D':
				 dwr.util.removeAllOptions('ddLgdDispandist');
				dwr.util.removeAllOptions('ddLgdLBDistPList');
				loadParliamentMaptoLocalBodyOnCoverageAdd();
				break;
	case 'I':
				 if (lbType != "U") {
					 dwr.util.removeAllOptions('ddLgdLBDistListAtInterCA');
						dwr.util.removeAllOptions('ddLgdLBInterPSourceList');
						dwr.util.removeAllOptions('ddLgdLBInterPDestList');
						loadParliamentMaptoLocalBodyOnCoverageAdd();
				} else { 
					
					dwr.util.removeAllOptions('ddLgdWardSubDistrictUListSource');
					dwr.util.removeAllOptions('ddLgdWardSubDistrictUListDest');
					dwr.util.removeAllOptions('ddLgdLBwardSourceLatDCA');
					dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCA');
					
					loadParliamentMaptoLocalBodyOnCoverageAdd();
				 } 
				break;
	case 'V':
		
		dwr.util.removeAllOptions('ddLgdLBDistListAtVillageCA');
		dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
		dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
		dwr.util.removeAllOptions('ddLgdLBVillageDestAtVillageCA');
		dwr.util.removeAllOptions('ddLgdLBVillageSourceLatDCAforvillage');
		dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCAforvillage');
		loadParliamentMaptoLocalBodyOnCoverageAdd();
		break;
	}
};

callActionUrl=function(){
	if(lbfullArr.length>0 || (lbPartOrg.length>0 && villageArr.length>0) || (lbPartOrg.length>0 && wardCodeArr.length>0) || deleteRowArr.length>0){
		if(validateUrbanChild()){
			//alert("in");
			$("#lbFullMap").val(lbfullArr.toString());
			
			var lbPartOrgNew = [];
			
			for (var i = 0; i < lbPartOrg.length; i++) {
				lbPartOrgNew.push(lbPartOrg[i]);
			}
			
			for (var i = 0; i < lbPartOrgNew.length; i++) {
				if(lbPartOrgNew[i].indexOf("_l") > 0){
					lbPartOrg.splice(i,1);
				}
			}
			
			$("#lbPartMap").val(lbPartOrg.toString());
			$("#villageMap").val(villageArr.toString());
			$("#deleteMap").val(deleteRowArr.toString());
			$("#wardMap").val(wardCodeArr.toString());
			$( 'form[id=form1]' ).submit(); 
		}else{
			alert("Contributing Urban localbody part not exist contributing ward");
		}
		
	}
	else{
		alert("Please Select Coverage");
	}
	
	
};



deleteRowData =function(rno,code,typeCode){
	
	deleteRowArr.push(code+"_"+typeCode);
	var row = document.getElementById(rno);
    row.parentNode.removeChild(row);
    if(typeCode=="V"){
    	
    	removeA(villageArr,code);
    	removeA(villageNameArr,searchName(villageNameArr,code));
    	removeA(subDistrictNameArray,searchName(subDistrictNameArray,code));
    	removeA(districtNameArray,searchName(districtNameArray,code));
    }else if(typeCode=="W"){
    	removeA(wardCodeArr,code);
    	removeA(wardnameArr,searchName(wardnameArr,code));
    }else{
    	
    	removeA(lbfullArr,code);
    	removeA(lbpartArr,code);
    	removeA(lbPartOrg,code);
    	removeA(dpnameArr,searchName(dpnameArr,code));
    	removeA(ipnameArr,searchName(ipnameArr,code));
    	removeA(vpnameArr,searchName(vpnameArr,code));
    	removeA(urbannameArr,searchName(urbannameArr,code));
    	if(typeCode=="3"){
    		removeElement('V','P');
    	}else if(typeCode=="2"){
    		removeElement('I','P');
    	}else if(typeCode=="1"){
    		removeElement('D','P');
    	}
    	
    }
	/* 
	alert(a);
	  var localBodyId=savedConstituncyArr[a-1].split("@")[0];
	  alert(localBodyId);
	  var ccID=savedConstituncyArr[a-1].split("@")[1];
	  alert(ccID);
	  lgdDwrAssemblyService.deleteConstituencyCoverageDetail(localBodyId,ccID, {
			callback : function( result ) {
				 if(result==true){
					alert("Row Deleted Successfully");
						
				 }
			}
				
	  
	  }); */
};

function removeA(arr,value) {
	
	var index = arr.indexOf(value.toString());
	if (index >= 0) {
	  arr.splice( index, 1 );
	}
}

var searchName=function(arr,search_value)
{
	var ret_val="-1";
	if(arr.length>0){
		
		$.each( arr, function( key, value ) {
			if(value.indexOf(search_value)>-1){
				ret_val= value;
			}
		});
  }
	return ret_val;
};



getButton=function(id){
	 var btn = $('<input/>');
	 btn.attr("type","button");
	 btn.attr("className", "btn");
	 btn.attr("value" ,"Delete");
	// var aggh=parseInt(no)+parseInt(key);
	 btn.attr("onclick","deleteRowData("+id+")");
	
	  return btn;
};


loadData=function(acCode){
	
	$( '#errorddassemblySource').text("");
	clearAllData();
	lgdDwrlocalBodyService.getConstituencyMapDetails(acCode, {
		callback : function( result ) {
			jQuery.each(result, function(index, obj) {
				if(obj.dpName.length>0){
					dpnameArr=obj.dpName.split("#");
				}
				
				if(obj.districtName.length>0){
					districtNameArray=obj.districtName.split(",");
				}
				
				if(obj.subdistrictName.length>0){
					subDistrictNameArray=obj.subdistrictName.split(",");
				}
					
				if(obj.ipName.length>0){
					ipnameArr=obj.ipName.split("#");
				}
				
				if(obj.vpName.length>0){
					vpnameArr=obj.vpName.split("#");
				}
				
				if(obj.villageName.length>0){
					villageNameArr=obj.villageName.split(",");
				}
				
				if(obj.lbCodeFull.length>0){
					lbfullArr=obj.lbCodeFull.split(",");
				}
				
				if(obj.lbCodePart.length>0){
					lbPartOrg=obj.lbCodePart.split(",");
					for (var i = 0; i < lbPartOrg.length; i++) {
						lbPartOrg[i] = lbPartOrg[i]+"_l";
					}
					lbpartArr=obj.lbCodePart.split(",");
				}
				
				if(obj.villageCode.length>0){
					villageArr=obj.villageCode.split(",");
				}
				
				if(obj.ulbName.length>0){
					urbannameArr=obj.ulbName.split(",");
				}
				
				if(obj.wardName != null){
					if(obj.wardName.length>0){
						wardnameArr=obj.wardName.split(",");
					}
				}
				
				if(obj.wardCode.length>0){
					wardCodeArr=obj.wardCode.split(",");
				}
				
				
				$("#ccCodeMap").val(obj.ccCode);
				/* dpnameArr.push(obj.dpName);
				ipnameArr.push(obj.ipName);
				vpnameArr.push(obj.vpName);
				villageNameArr.push(obj.villageName);
				lbfullArr.push(obj.lbCodeFull);
				lbPartOrg.push(obj.lbCodePart);
				villageArr.push(obj.villageCode); */
				
				updateCoverage();
				
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
};


clearAllData=function(){
	

	 lbfullArr=[];
	 lbpartArr=[];
	 lbPartOrg=[];
	 villageArr=[];
	 villageNameArr=[];
	 subDistrictNameArray=[];
	 districtNameArray = [];
	 vpnameArr=[];
	 dpnameArr=[];
	 ipnameArr=[];
	 urbannameArr=[];
	 wardnameArr=[];
	 wardCodeArr=[];
	 
	 dwr.util.removeAllOptions('ddLgdDispandist');
	 dwr.util.removeAllOptions('ddLgdLBDistPList');
	 
	 
	 dwr.util.removeAllOptions('ddLgdLBDistListAtInterCA');
	 dwr.util.removeAllOptions('ddLgdLBInterPSourceList');
	 dwr.util.removeAllOptions('ddLgdLBInterPDestList');
		
	 
	dwr.util.removeAllOptions('ddLgdWardSubDistrictUListSource');
	dwr.util.removeAllOptions('ddLgdWardSubDistrictUListDest');
	dwr.util.removeAllOptions('ddLgdLBwardSourceLatDCA');
	dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCA');
		
 	dwr.util.removeAllOptions('ddLgdLBDistListAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBVillageDestAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBVillageSourceLatDCAforvillage');
	dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCAforvillage');
		
	loadParliamentMaptoLocalBody();
	$("#ddLgdLBType option[value='']").attr("selected", "selected");
	$('#dynamicTbl tr').not(function(){ return !!$(this).has('th').length; }).remove(); 
};


validateUrbanCoverage=function(){
	$( '#errorddLgdLBVillageDestLatDCA').text(""); 
	var _error_flag=true;
	
		var isPartExistFlag;
		var isPartExist=new Array();
		var _part_text=[];
		var _notExist_part_name=[];
		var count;
		$("#ddLgdWardSubDistrictUListDest option").each(function() { 
			if(($(this).val().indexOf("PART"))>-1){
				_tmp=$(this).text();
				_part_text.push(_tmp.substring(0, _tmp.indexOf("(")));
			}
		});
				
		count=0;
		while(_part_text.length>0){
			 isPartExistFlag=false;
			 _isPart_name=_part_text.pop();
			$("#ddLgdLBVillageDestLatDCA option").each(function() {
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
				$( '#errorddLgdLBVillageDestLatDCA').text("Kindly select coverage from all contributing local bodies."); 
				_error_flag=false;
				$('#errorddLgdLBVillageDestLatDCA').focus();	  
			}
		}
return _error_flag;
};





deleteRowDataForUrban =function(rno,lbname,lbCode,covType){
	
	
	var row = document.getElementById(rno);
    row.parentNode.removeChild(row);
    
   var delArr=[];
    	if(covType=="PART"){
    		removeA(lbfullArr,lbCode);
        	removeA(lbpartArr,lbCode);
        	removeA(lbPartOrg,lbCode)
    		removeA(urbannameArr,searchName(urbannameArr,lbCode));
    		
    		if(wardnameArr.length>0){
    		delArr = wardnameArr.slice();;
    			$.each( delArr, function( key, value ) {
    				wardName=value;
    				if(wardName.indexOf(lbname)>-1){
    					removeA(wardCodeArr,wardName.split("@")[0].split("_")[1]);
    					removeA(wardnameArr,wardName);
    				}
    			
    			});
    		}
    		$('#dynamicTbl tr').not(function(){ return !!$(this).has('th').length; }).remove(); 
    		updateCoverage();
    	}
    	else{
    		removeA(lbfullArr,lbCode);
    		removeA(urbannameArr,searchName(urbannameArr,lbCode));
    	}
    
    	
    	
    
    /* if(typeCode=="V"){
    	
    	removeA(villageArr,code);
    	removeA(villageNameArr,searchName(villageNameArr,code));
    }else if(typeCode=="W"){
    	removeA(wardCodeArr,code);
    	removeA(wardnameArr,searchName(wardnameArr,code));
    }else{
    	
    	removeA(lbfullArr,code);
    	removeA(lbpartArr,code);
    	removeA(lbPartOrg,code);
    	removeA(dpnameArr,searchName(dpnameArr,code));
    	removeA(ipnameArr,searchName(ipnameArr,code));
    	removeA(vpnameArr,searchName(vpnameArr,code));
    	removeA(urbannameArr,searchName(urbannameArr,code));
    	if(typeCode=="3"){
    		removeElement('V','P');
    	}else if(typeCode=="2"){
    		removeElement('I','P');
    	}else if(typeCode=="1"){
    		removeElement('D','P');
    	}
    	
    } */
	
};


removeAllList=function(){
	 dwr.util.removeAllOptions('ddLgdDispandist');
	 dwr.util.removeAllOptions('ddLgdLBDistPList');
	 
	 
	 dwr.util.removeAllOptions('ddLgdLBDistListAtInterCA');
	 dwr.util.removeAllOptions('ddLgdLBInterPSourceList');
	 dwr.util.removeAllOptions('ddLgdLBInterPDestList');
		
	 
	dwr.util.removeAllOptions('ddLgdWardSubDistrictUListSource');
	dwr.util.removeAllOptions('ddLgdWardSubDistrictUListDest');
	dwr.util.removeAllOptions('ddLgdLBwardSourceLatDCA');
	dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCA');
		
 	dwr.util.removeAllOptions('ddLgdLBDistListAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBInterListAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBVillageSourceAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBVillageDestAtVillageCA');
	dwr.util.removeAllOptions('ddLgdLBVillageSourceLatDCAforvillage');
	dwr.util.removeAllOptions('ddLgdLBVillageDestLatDCAforvillage');
};

validateUrbanChild=function(){
	var flag=true;
	if(urbannameArr.length>0){
		no=0;
		var isPartExist=new Array();
		$.each( urbannameArr, function( key, value ) {
			name=value.split("@")[0].split("_")[0]
			name=name.substring(0,name.indexOf("("));
			if(value.indexOf("PART")>-1){
				isPartExist[no]=false;
				$.each( wardnameArr, function( key1, value1 ) {
					if(value1.indexOf(name)>-1){
						isPartExist[no]=true;
						
					}
				});
			}
			
			no++;
			
		});
			
		for ( var i = 0; i < isPartExist.length; i++) {
			if (isPartExist[i] == false) {
				flag = false;
				break;
			}

		}	
		
	}
	return flag;
}; 
</script>