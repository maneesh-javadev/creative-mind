<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/dwrDraftSubdistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/dwrDraftVillageService.js'></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.numeric.js"></script> 

<script>
var subdistrictListPart='${draftSubdistrictForm.listofAllSubdistrictPart}'!=''?'${draftSubdistrictForm.listofAllSubdistrictPart}':null;
var subdistrictListFull='${draftSubdistrictForm.listofAllSubdistrictFull}'!=''?'${draftSubdistrictForm.listofAllSubdistrictFull}':null;
var draftVillagesFull='${draftSubdistrictForm.listofAllVillageFull}'!=''?'${draftSubdistrictForm.listofAllVillageFull}':null;
var draftsubDistrictNameEng='${draftSubdistrictForm.listofSubdistrictNameEng}'!=''?'${draftSubdistrictForm.listofSubdistrictNameEng}':null;
var firstErrorElement=false;
var spaceCount=0;
var isPartFlag;

var isMulti= isParseJson("${serverAdd}");

$(document).ready(function() {
	
	
	
	$("#fetchVillageList").click(function() {
		var subdistrictCodes = "-1@FULL#";
		$('#contibutingSubdistrict option').each(function() { 
			if(($(this).val().indexOf("PART"))>-1){
				subdistrictCodes=subdistrictCodes+$(this).val()+"#";
			}
		});
		if((subdistrictCodes.indexOf("PART"))>-1){
			getDraftVillageList(subdistrictCodes,draftVillagesFull); 
		}
	 });
	
	$("#btnAddLatLong").click(function() {
		buildLatitudeLongitude('', '');
	});
	
	$("#longitude, #latitude").on('keyup', function(e){
		checkDecimalPlaces(e)
	}).numeric({ decimal: ".", negative: false }, function() {
		this.value = ""; this.focus(); 
	});
	
		
	/**
	 * The subdistrict Name (In English) keypress event allow only alphanumbric and some special character{} . 
	 */
	 $("#subdistrictNameEnglish").keypress(function(e){
		// get the key that was pressed
		
		$( '#errsubdistrictNameEnglish').text(""); 	
		var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
		
		//alert(key);
			if(key == 13 && this.nodeName.toLowerCase() == "input")
			{
				return true;
			}
			else if(key == 13)
			{
				return false;
			}
			var allow = false;
			// allow Ctrl+A
			if((e.ctrlKey && key == 97 /* firefox */) || (e.ctrlKey && key == 65) /* opera */) return true;
			// allow Ctrl+X (cut)
			if((e.ctrlKey && key == 120 /* firefox */) || (e.ctrlKey && key == 88) /* opera */) return true;
			// allow Ctrl+C (copy)
			if((e.ctrlKey && key == 99 /* firefox */) || (e.ctrlKey && key == 67) /* opera */) return true;
			// allow Ctrl+Z (undo)
			if((e.ctrlKey && key == 122 /* firefox */) || (e.ctrlKey && key == 90) /* opera */) return true;
			// allow or deny Ctrl+V (paste), Shift+Ins
			if((e.ctrlKey && key == 118 /* firefox */) || (e.ctrlKey && key == 86) /* opera */
			|| (e.shiftKey && key == 45)) return false;
			
			
				if(
						
						key==126 /* ~ */ ||
						key==96 /* ` */ ||
						key==33 /* ! */ ||
						key==64 /* @ */ ||
						key==35 /* # */ ||
						key==36 /* $ */ ||
						key==37 /* % */ ||
						key==94 /* ^ */ ||
						key==38 /* & */ ||
						key==42 /* * */ ||
						key==95 /* - */ ||
						key==43 /* + */ ||
						key==61 /* = */ ||
						key==123 /* { */ ||
						key==125 /* } */ ||
						key==91 /* [ */ ||
						key==93 /* ] */ ||
						key==124 /* | */ ||
						key==92 /* \ */ ||
						key==39 /* ' */ ||
						key==34 /* " */ ||
						key==58 /* : */ ||
						key==59 /* ; */ ||
						key==60 /* < */ ||
						key==62 /* > */ ||
						key==63 /* ? */ ||
						key==40 /* ( */	||
						key==41 /* ) */	||		
						key==44 /* , */	||
						key==46 /* . */	||	
						key==45 /* - */	||
						key==47 /*/ */	||
						(key>47 && key<58)  /*  0-9 */
						
					){
					$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>"); 
					return false;
					
				}
				
				if(key==32)/* Space Key */
				{
					if(spaceCount>0){
						$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
						return false;
					}
					spaceCount++;
				}else {
					spaceCount=0;
				}
			
		
	 });
	 
	 /**
		 * The subdistrict Name (In English) keypress event allow only alphanumbric and some special character{} . 
		 */
		 $("#census2011Code").keypress(function(e){
			// get the key that was pressed
			$( '#errcensus2011Code').text(""); 	
			var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
			
			//alert(key);
				if(key == 13 && this.nodeName.toLowerCase() == "input")
				{
					return true;
				}
				else if(key == 13)
				{
					return false;
				}
				var allow = false;
				// allow Ctrl+A
				if((e.ctrlKey && key == 97 /* firefox */) || (e.ctrlKey && key == 65) /* opera */) return true;
				// allow Ctrl+X (cut)
				if((e.ctrlKey && key == 120 /* firefox */) || (e.ctrlKey && key == 88) /* opera */) return true;
				// allow Ctrl+C (copy)
				if((e.ctrlKey && key == 99 /* firefox */) || (e.ctrlKey && key == 67) /* opera */) return true;
				// allow Ctrl+Z (undo)
				if((e.ctrlKey && key == 122 /* firefox */) || (e.ctrlKey && key == 90) /* opera */) return true;
				// allow or deny Ctrl+V (paste), Shift+Ins
				if((e.ctrlKey && key == 118 /* firefox */) || (e.ctrlKey && key == 86) /* opera */
				|| (e.shiftKey && key == 45)) return false;
				
				//alert(key);
				if(key==8){
					return true;
				}
				 if(!( key>47 && key<58)  /*  0-9 */){
						$( '#errcensus2011Code').text("<spring:message code='Error.allow.numberic' htmlEscape='true'/>"); 
						return false;
						
				}
					
					
				
			
		 });
		 
		 /**
			 * The subdistrict Name (In English) keypress event allow only alphanumbric and some special character{} . 
			 */
			 $("#aliasEnglish").keypress(function(e){
				// get the key that was pressed
				
				$( '#erraliasEnglish').text(""); 	
				var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
				
				//alert(key);
					if(key == 13 && this.nodeName.toLowerCase() == "input")
					{
						return true;
					}
					else if(key == 13)
					{
						return false;
					}
					var allow = false;
					// allow Ctrl+A
					if((e.ctrlKey && key == 97 /* firefox */) || (e.ctrlKey && key == 65) /* opera */) return true;
					// allow Ctrl+X (cut)
					if((e.ctrlKey && key == 120 /* firefox */) || (e.ctrlKey && key == 88) /* opera */) return true;
					// allow Ctrl+C (copy)
					if((e.ctrlKey && key == 99 /* firefox */) || (e.ctrlKey && key == 67) /* opera */) return true;
					// allow Ctrl+Z (undo)
					if((e.ctrlKey && key == 122 /* firefox */) || (e.ctrlKey && key == 90) /* opera */) return true;
					// allow or deny Ctrl+V (paste), Shift+Ins
					if((e.ctrlKey && key == 118 /* firefox */) || (e.ctrlKey && key == 86) /* opera */
					|| (e.shiftKey && key == 45)) return false;
					
					
						if(
								
								key==126 /* ~ */ ||
								key==96 /* ` */ ||
								key==33 /* ! */ ||
								key==64 /* @ */ ||
								key==35 /* # */ ||
								key==36 /* $ */ ||
								key==37 /* % */ ||
								key==94 /* ^ */ ||
								key==38 /* & */ ||
								key==42 /* * */ ||
								key==95 /* - */ ||
								key==43 /* + */ ||
								key==61 /* = */ ||
								key==123 /* { */ ||
								key==125 /* } */ ||
								key==91 /* [ */ ||
								key==93 /* ] */ ||
								key==124 /* | */ ||
								key==92 /* \ */ ||
								key==39 /* ' */ ||
								key==34 /* " */ ||
								key==58 /* : */ ||
								key==59 /* ; */ ||
								key==60 /* < */ ||
								key==62 /* > */ ||
								key==63 /* ? */ ||
								key==40 /* ( */	||
								key==41 /* ) */	||		
								key==44 /* , */	||
								key==46 /* . */	||	
								key==45 /* - */	||
								key==47 /*/ */	||
								(key>47 && key<58)  /*  0-9 */
								
							){
							$( '#erraliasEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>"); 
							return false;
							
						}
						
						if(key==32)/* Space Key */
						{
							if(spaceCount>0){
								$( '#erraliasEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
								return false;
							}
							spaceCount++;
						}else {
							spaceCount=0;
						}
					
				
			 });
		 
	 /**
		 * The sectionNameEnglish keyup event allow first character must be alphbet and capital . 
		 */
	  /* $("#subdistrictNameEnglish").keyup(function(e){
		 var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
		 if(this.value.length==1) //intial letter 
		{
		 if(key>64 && key<91 ){
			this.value=this.value.toUpperCase();
			}
		}
	 });  */
	 
});


getDraftVillageList=function(subdistrictCodes,draftVillagesFull){
	removeAllOptions('villageList');
	removeAllOptions('contibutingVillage')
	 if (!$_checkEmptyObject(subdistrictCodes)) {
		 dwrDraftVillageService.getDraftVillageList(subdistrictCodes,draftVillagesFull, {
				callback : function( result ) {
					var options = $("#villageList"); 
					
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						var _value=obj.villageCode;
						var _text=obj.villageNameEnglish;
						
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							var _text=obj.villageNameEnglish+"(Village is being used as Drafted)";
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


/**
 * The {@code moveElement} function used move items from one listbox to another,
 * based on copyid,pasteId and action(parameter)
 */
moveElement=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL" ||action=="PART" ){
		copyId='subdistrictList';
		pasteId='contibutingSubdistrict';
	}else if(action=="BACK" ||action=="RESET" ){
		copyId='contibutingSubdistrict';
		pasteId='subdistrictList'; 
		removeAllOptions('villageList');
		removeAllOptions('contibutingVillage');
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
			$("#cAlert").html(_part_list+" subdistricts already part contributing");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Title.CREATENEWSUBDISTRICT"></spring:message>',
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
	}else if(action=="RESET" ){
		var options = $("#"+pasteId); 
		$('#'+copyId+' option').each(function() { 
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
		copyId='villageList';
		pasteId='contibutingVillage';
	}else if(action=="BACK" ||action=="RESET" ){
		copyId='contibutingVillage';
		pasteId='villageList'; 
		
	}
	
	if(action=="FULL" ){
		if(moveValidatationVillageCoverage()){
			var options = $("#"+pasteId);
			$('#'+copyId+' option:selected').each(function() { 
				var option = $("<option />");
				option.val($(this).val()).text($(this).text()+"(FULL)");
				options.append(option);
				$(this).remove();
				});
		}
		
	}else if(action=="BACK" ){
	var options = $("#"+pasteId); 
	$('#'+copyId+' option:selected').each(function() { 
		var option = $("<option />");
		_tmp=$(this).text();
		_text=_tmp.substring(0, _tmp.lastIndexOf("("));;
		option.val($(this).val()).text(_text);
		options.append(option);
		$(this).remove();
		});
	}else if(action=="RESET" ){
		var options = $("#"+pasteId); 
		$('#'+copyId+' option').each(function() { 
		var option = $("<option />");
		_tmp=$(this).text();
		_text=_tmp.substring(0, _tmp.lastIndexOf("("));;
		option.val($(this).val()).text(_text);
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
moveElement=function(action){
	var copyId=null;
	var pasteId=null;
	if(action=="FULL" ||action=="PART" ){
		copyId='subdistrictList';
		pasteId='contibutingSubdistrict';
	}else if(action=="BACK" ||action=="RESET" ){
		copyId='contibutingSubdistrict';
		pasteId='subdistrictList'; 
		removeAllOptions('villageList');
		removeAllOptions('contibutingVillage')
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
	
			//alert($(this).val());
			if(!(($(this).val().indexOf("PART"))>-1)){
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
				title : '<spring:message htmlEscape="true" code="Title.CREATENEWSUBDISTRICT"></spring:message>',
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
				_text=_tmp.substring(0, _tmp.lastIndexOf("("));;
				
			}
			option.val(_value).text(_text);
			options.append(option);
			$(this).remove();
			});
	}else if(action=="RESET" ){
		var options = $("#"+pasteId); 
		$('#'+copyId+' option').each(function() { 
			var option = $("<option />");
			_tmp=$(this).val();
			_value=_tmp.substring(0, _tmp.lastIndexOf("@"));
			_text=$(this).text();
			if($(this).val().indexOf("@")==$(this).val().lastIndexOf("@")){
				_tmp=$(this).text();
				_text=_tmp.substring(0, _tmp.lastIndexOf("("));;
				
			}
			option.val(_value).text(_text);
			options.append(option);
			$(this).remove();
			});
	}
	sortListBox(copyId);
	sortListBox(pasteId);
		
	
};


var counter = 1;
var buildLatitudeLongitude = function(longval, latval ) {
	var template = '<div class="row">' 
				 + '<div class="col"><input type="text" name="longitude" id="longitude" maxlength="6" value="' + longval + '"/></div>'
			     + '<div class="col"><input type="text" name="latitude" id="latitude" maxlength="6" value="' + latval + '"/></div>'
			     + '<div class="col"><input type="button" class="bttn remove" id="removeLatLong_' + counter + '" value="Remove"/></div>'
			     + '</div>';
	$('#divLatitudeLongitude').before(template);
	$('#removeLatLong_' + counter).click(function() {
		$(this).closest('.row').remove();
	});
	$("#longitude, #latitude").on('keyup', function(e){
		checkDecimalPlaces(e)
	}).numeric({ decimal: ".", negative: false }, function() {
		this.value = ""; this.focus(); 
	});
	counter++;
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



validateDistrictCode=function(){
	 var _error_flag=false;
	 var districtElement=$( '#districtCode');
		if($_checkEmptyObject($( districtElement ).val())){
			_error_flag=true;
			$( districtElement ).addClass("error");
			$( '#err' + $( districtElement ).attr('id') ).text("<spring:message code='Label.SELECTDISTRICT' htmlEscape='true'/>");
			if(!firstErrorElement){
				$(districtElement).focus();
				firstErrorElement=true;
			}
			
		}
		return _error_flag;
};


validateSubdistrictNameEng=function(){
	 var _error_flag=true;
	var re = new RegExp("^[a-zA-Z ]+$", "g");
	 var subdistrictNameEnglish=$("#subdistrictNameEnglish").val();
	 //alert(SectionNameEng);
	 //alert(SectionNameEng.charAt(0).charCodeAt());
	 if($_checkEmptyObject(subdistrictNameEnglish)){
		 $( '#errsubdistrictNameEnglish').text("<spring:message code='Error.SUBDISTRICTENGLISH' htmlEscape='true'/>");
		 _error_flag=false;
	 }else{
		 if(!re.test(subdistrictNameEnglish)){
			 $( '#errsubdistrictNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>");	 
			 _error_flag=false;
		 } else{
		
				 for(i=0;i<subdistrictNameEnglish.length;i++){
					 key=subdistrictNameEnglish.charAt(i).charCodeAt();
					 if(key==32)/* Space Key */
						{
							if(spaceCount>0){
								$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
								 _error_flag=false;
							}
							spaceCount++;
						}else {
							spaceCount=0;
						}
				 }
			
			 
			 
		 }
	 }
	 
	 if(! _error_flag){
		 var subdistrictNameEnglishElement=$("#subdistrictNameEnglish");
			$( subdistrictNameEnglishElement ).addClass("error");
			if(!firstErrorElement){
				$(subdistrictNameEnglishElement).focus();
				firstErrorElement=true;
			}
			
	 }
	
	 return _error_flag;
};

validateSubdistrictNameEngUnique=function(subDistrictNameEng){
	
	if (!$_checkEmptyObject(subDistrictNameEng) && !validateDistrictCode()) {
		var subdistrictNameEnglishElement=$("#subdistrictNameEnglish");
		
		if(draftsubDistrictNameEng!=null && (draftsubDistrictNameEng.indexOf(subDistrictNameEng))>-1){
			$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.subdistrict.name.unique.previous' htmlEscape='true'/>"); 
			$(subdistrictNameEnglishElement).focus();
		}else{
			var districtCode=$( '#selectDistrictCode').val();
			dwrDraftSubdistrictService.subdistrictNameIsUnique(subDistrictNameEng,districtCode, {
					callback : function( uniqueFlag ) {
						if(uniqueFlag=='D'){
							$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.subdistrict.name.unique.draft' htmlEscape='true'/>"); 
							$(subdistrictNameEnglishElement).focus();
						}else if(uniqueFlag=='P'){
							$( '#errsubdistrictNameEnglish').text("<spring:message code='Error.subdistrict.name.unique.publish' htmlEscape='true'/>"); 
							$(subdistrictNameEnglishElement).focus();
						}
					},
					errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
					async : true
				});
		}
	}else{
		$("#subdistrictNameEnglish").val("");
	}
	
};

validateSubdistrictCoverage=function(){
	$( '#errcontibutingSubdistrict').text("");
	var _error_flag=true;
	var coverageFullCount=0;
	isPartFlag=false;
	var contibutingSubdistrict=$("#contibutingSubdistrict option");
	$(contibutingSubdistrict).each(function() { 
		if(($(this).val().indexOf("PART"))>-1){
			isPartFlag=true;
		}
		else if(($(this).val().indexOf("FULL"))>-1){
			coverageFullCount++;
		}
	});
	
	if(!(isPartFlag || coverageFullCount>=2)){
		$( '#errcontibutingSubdistrict').text("<spring:message code='Error.subdistrict.coverage.validatation' htmlEscape='true'/>"); 
		$(contibutingSubdistrict).focus();
		_error_flag=false;
	}
	return _error_flag;
};

validateVillageCoverage=function(){
	$( '#errcontibutingVillage').text("");
	var _error_flag=true;
	if(isPartFlag){
		var isPartExistFlag;
		var isPartExist=new Array();
		var _part_text=[];
		var _notExist_part_name=[];
		var count;
		$("#contibutingSubdistrict option").each(function() { 
			if(($(this).val().indexOf("PART"))>-1){
				_tmp=$(this).text();
				_part_text.push(_tmp.substring(0, _tmp.indexOf("(")));
			}
		});
				
		count=0;
		while(_part_text.length>0){
			 isPartExistFlag=false;
			 _isPart_name=_part_text.pop();
			$("#contibutingVillage option").each(function() {
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
				$( '#errcontibutingVillage').text("Contributing Subdistrict part("+_notExist_part_name.toString()+") not exist contributing village "); 
				_error_flag=false;
				$('#contibutingVillage').focus();	  
			}
		}
	}
	return _error_flag;
};

moveValidatationVillageCoverage=function(){
	if(!isMulti){
		var errors=new Array();
		var error=true;
		
		
		
		$( '#errvillageList').text("");
			var _remain_flag=false;
			var _remain_subdistrict=[];
			var counter=0;
			$("#contibutingSubdistrict option").each(function() { 
				if(($(this).val().indexOf("PART"))>-1){
					_tmp=$(this).text();
					_tmp=_tmp.substring(0, _tmp.indexOf("("));
					 _remain_flag=false;
					 errors[counter]=false;
					
					$("#villageList option").each(function() {
						
						_selected_flag=$(this).is(':selected');
						//alert(_temp);
					 if(_selected_flag==false && _remain_flag==false){
						 _village_text=$(this).text();
						 if((_village_text.indexOf(_tmp))>-1){
							 _remain_flag=true;
							
							 errors[counter]=true;
						 }
							
						
					 }	 
					 
						
					});
					if(_remain_flag==false){
						 _remain_subdistrict.push(_tmp);
					 }
					counter++;
					
				}
			});
					
			for ( var i = 0; i < errors.length; i++) {
				if (errors[i] == false) {
					error = false;
					break;
				}

			}
					
					if(error==false){
						$( '#errvillageList').text("You can not take Subdistrict part("+_remain_subdistrict.toString()+") all villages in contributing List"); 
					}
					return error;
	}else{
		return true;
	}
	
	
				
		
};




callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=draftSubdistrictForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=draftSubdistrictForm]' ).attr('method','post');
	$( 'form[id=draftSubdistrictForm]' ).submit();
};

validateCordinate=function(){
	var status = true;
	var _lon_error_flag=false;
	var _lat_error_flag=false;
    var lonArr = [];
    
    var errcordinate="";
	$("[name=longitude]").each(function(index, obj){
		if(! $_checkEmptyObject($(this).val())){
			var lon=parseInt($(this).val());
			lonArr.push(lon);
			if(lon<32 || lon>98 ){
				_lon_error_flag=true;
				
			}
		}
	});	
	var latArr = [];
	$("[name=latitude]").each(function(index, obj){
		if(! $_checkEmptyObject($(this).val())){
			var lat=parseInt($(this).val());
			latArr.push(lat);
			if(lat<6 || lat>38 ){
				_lat_error_flag=true;
				
			}
		}	
	});	
	if(lonArr.length != latArr.length){
		status = false;
		errcordinate="<spring:message code='Error.lengthmismatch' htmlEscape='true'/>";
	}else{
		if(_lon_error_flag==true){
			errcordinate="Please enter <spring:message code='Label.longituderange' htmlEscape='true'/>";
				status = false;
		} 
		
		if(_lat_error_flag==true){
			if(_lon_error_flag==true){
				errcordinate=errcordinate+" and ";
			}
			status = false;
			errcordinate=errcordinate+" Please enter <spring:message code='Label.latituderange' htmlEscape='true'/>";
		}
	}
	$( '#errcordinate').text(errcordinate); 
	return status;
	
};


var latitudelongitudeOnload = function(){
	var longitudeData = '${draftSubdistrictForm.longitude}';
	var latitudeData = '${draftSubdistrictForm.latitude}';
	
		var coordinates = '${draftSubdistrictForm.coordinates}';
		var array = coordinates.split(",");
		var arraysize = $(array).size() - 1;
		$(array).each(function(i){
			var childArray = array[i].split(":");
			longitudeData = longitudeData.concat(childArray[0]);
			latitudeData = latitudeData.concat(childArray[1]);
			if(i != arraysize){
				longitudeData = longitudeData.concat(",");
				latitudeData = latitudeData.concat(",");
			}
		});
	
	
	if(!$_checkEmptyObject(longitudeData) && !$_checkEmptyObject(latitudeData)){
		var longitudeArray = longitudeData.split(",");
		var latitudeArray = latitudeData.split(",");
		$("#longitude").val(longitudeArray[0]);
		$("#latitude").val(latitudeArray[0]);
		$(longitudeArray).each(function(index){
			if(index > 0){
				buildLatitudeLongitude(longitudeArray[index], latitudeArray[index]);
			}
		});	 
	}
};
</script>


