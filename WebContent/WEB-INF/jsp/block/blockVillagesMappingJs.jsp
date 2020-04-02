
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type="text/javascript" language="javascript">

$(document).ready(function() {
	
	$("#districtCode").change(function(){
		removeError(this);
		getBlockList($(this).val());
	});
	
	$("#ddTargetBlock").change(function(){
		removeError(this);
		$( this ).removeClass("error");
		getBlkMapUnVillageLists(0);
	});
	
	$("#btnFormActionProceestoSave").click(function(){
		validateForm();
	});
});	
	
	var firstErrorElement=false;
	
	removeError=function(obj){
		$( obj ).removeClass("error");
		$( '#err' + $(obj ).attr('id') ).text("");
		
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

	validateBlockCode=function(){
		 var _error_flag=false;
		 var blockElement=$( '#ddTargetBlock');
			if($_checkEmptyObject($( blockElement ).val())){
				_error_flag=true;
				$( blockElement ).addClass("error");
				$( '#err' + $( blockElement ).attr('id') ).text("<spring:message code='Label.TARGETBLOCK' htmlEscape='true'/>");
				if(!firstErrorElement){
					$(blockElement).focus();
					firstErrorElement=true;
				}
				
			}
			return _error_flag;
	};
	
	validateMappedVillageList=function(){
		var _error_flag=false;
		 var villageMapElement=$( '#ddTargetVillage');
		if($(villageMapElement).children().length == 0){
			_error_flag=true;
			$( villageMapElement ).addClass("error");
			$( '#err' + $( villageMapElement ).attr('id') ).text("<spring:message code='error.select.VILLAGE' htmlEscape='true'/>");
			if(!firstErrorElement){
				$(villageMapElement).focus();
				firstErrorElement=true;
			}
		}
		
		if(!_error_flag){
			_error_flag=!(validateChangeVillageMapping());
		}
		return _error_flag;
	};

	
	validateChangeVillageMapping=function(){
		var _error_flag=false;
		$("#ddTargetVillage option").each(function() { 
			if(($(this).text().indexOf("MAPPED"))==-1 || ($(this).text().indexOf("Part"))==-1){
			_error_flag=true;
			}
		});
		
		$("#ddSourceVillage option").each(function() { 
			if(($(this).text().indexOf("MAPPED"))>-1 || ($(this).text().indexOf("Part"))>-1){
				_error_flag=true;
			}
		});
		
		if(!_error_flag){
			$("#diverrchangevillage").show(); 
			$( '#errchangevillage').text("There is no change found in the existing mapping");
		}
		return _error_flag;
	};
	
	validateForm=function(){
		var isDistrict= isParseJson('${isDistrict}');
		$("#diverrchangevillage").hide(); 
		$( '#errchangevillage').text("");
		
		  $( "span[id^=err]" ).each(function(){
				$( this ).text("");
		    });
		var error=true; 
		var errors=new Array();
		var id=0;
		if(isDistrict==null){
			errors[id++]= !validateDistrictCode();
		}
		errors[id++]= !validateBlockCode();
		errors[id++]= !validateMappedVillageList();
			for ( var i = 0; i < errors.length; i++) {
			if (errors[i] == false) {
				error = false;
				break;
			}

		}
		if(error){
			
			$("#ddTargetVillage option").each(function() {
				if(($(this).text().indexOf("MAPPED"))== -1 ){
					
					$(this).prop("selected",true);
				}	
				
				
				

				});

				$("#ddSourceVillage option").each(function() {

				if(($(this).text().indexOf("MAPPED")) > -1){
				$(this).prop("selected",true);
				}
				
				

				}); 
				
				

				$("#lbCoverageVillageList option").each(function() {
					$(this).prop("selected",true);
				}); 

/* selectedLocalbody = document.getElementById('lbCoverageVillageList');

if (selectedLocalbody != null) {
for ( var j = 0; j < selectedLocalbody.options.length; j++) {
selectedLocalbody.options[j].selected = true;
} 
} */




			//alert("in");
			 //$("#ddTargetVillage option").prop("selected",true);
			 $( "#btnFormActionProceestoSave" ).prop( "disabled", true );
		$( "#btnActionClose" ).prop( "disabled", true ); 
			callActionUrl('saveBlockVillageMapping.htm');
			
		} 
			
	};
	
	callActionUrl=function(url){
	 	
	   
	    $( 'form[id=blockVillageForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=blockVillageForm]' ).attr('method','post');
		$( 'form[id=blockVillageForm]' ).submit();
	};
	
	getBlockList=function(districtCode){
		removeAllOptions('ddTargetBlock');
		removeAllOptions('ddTargetVillage');
		removeAllOptions('ddSourceVillage');
		 if (!$_checkEmptyObject(districtCode)) {
			 lgdDwrBlockService.getBlockListbyDistrictCodeForLocalBody(parseInt(districtCode), {
					callback : function( result ) {
						var options = $("#ddTargetBlock"); 
						var option = $("<option/>");
						$(option).val("").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
						options.append(option);
						
						jQuery.each(result, function(index, obj) {
							var option = $("<option />");
							if (obj.operation_state == 'F') {
								$(option).attr('disabled', 'disabled');
							}
							option.val(obj.blockCode+'-'+obj.blockVersion).text(obj.blockNameEnglish);
							options.append(option);
						});
					},
					errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
					async : true
				});
		 }
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
	/* sortListBox=function(id){
		 var $r = $("#"+id+" option");
	    $r.sort(function(a, b) {
	        if (a.text < b.text) return -1;
	        if (a.text == b.text) return 0;
	        return 1;
	    });
	    $($r).remove();
	    $("#"+id).append($($r));
	    
	    
	}; */
	
	
	function getBlkMapUnVillageLists(id) {
		var s = document.getElementById('ddTargetBlock').value;
		blkArr=s.split("-");
		var blkCode=parseInt(blkArr[0]);
		if (id == 0){
			lgdDwrBlockService.getUnmapVillagesbyBlockCode(blkCode, 'U', {
				callback : handleUnmapVilSuccess,
				errorHandler : handleunmapVilError,
				async : true
			});
		}else{
			lgdDwrBlockService.getUnmapVillagesbyBlockCode(blkCode, 'M', {
				callback : handleMappedVilSuccess,
				errorHandler : handleunmapVilError,
				async : true

			});
		}
	}
	
	function handleUnmapVilSuccess(data) {
		var fieldId = 'ddSourceVillage';
		dwr.util.removeAllOptions(fieldId);
		var _show_text="";
		var options = $("#ddSourceVillage");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				_show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+"]";
				$(option).val(obj.villageCode+'-'+obj.villageVersion).text(_show_text);
				
				options.append(option);
			} else {if (obj.coverageType == '(PART)') {
				_show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+obj.coverageType;
				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				$(option).val(obj.villageCode+'-'+obj.villageVersion+'_'+"Part").text(_show_text);
			}else{
			_show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish;
			
/* 				_show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+"]"+obj.coverageType;
 */				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				$(option).val(obj.villageCode+'-'+obj.villageVersion).text(_show_text);
			}
			options.append(option);
			}
		});

		// dwr.util.addOptions(fieldId, data, valueText, nameText);
		getBlkMapUnVillageLists(1);

	}
	function handleMappedVilSuccess(data) {
		var fieldId = 'ddTargetVillage';
		dwr.util.removeAllOptions(fieldId);
		var options = $("#ddTargetVillage");
		var _show_text="";
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			
			if (obj.operation_state == 'F') {
				_show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+"](MAPPED)";
				$(option).attr('disabled', 'disabled');
				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				$(option).val(obj.villageCode+'-'+obj.villageVersion).text(_show_text);
				options.append(option);
			}
			else if (obj.operation_state == 'F') {
				_show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+"](MAPPED)";
				$(option).attr('disabled', 'disabled');
				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				$(option).val(obj.villageCode+'-'+obj.villageVersion).text(_show_text);
				options.append(option);
			}
			else {
				
				  if (obj.coverageType == '(PART)') {
					  _show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+"](Part)(MAPPED)";
				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				$(option).val(obj.villageCode+'-'+obj.villageVersion+'_'+"PART").text(_show_text);
				options.append(option);
				  }
				  else if (obj.coverageType == '(MAPPED)') {
					  _show_text=obj.villageNameEnglish+"("+obj.villageCode+")[Sub-Distirct:"+obj.subdistrictNameEnglish+"](MAPPED)";
				$(option).attr('title', "This village is mapped to "+obj.mappedVillageGramPanchayatName+" gram panchayat ");
				$(option).val(obj.villageCode+'-'+obj.villageVersion).text(_show_text);
				options.append(option);
				  }
				 
			}
		});

coverageDetail(fieldId );
		
	}
	
	function handleunmapVilError() {
		removeAllOptions('ddTargetVillage');
		removeAllOptions('ddSourceVillage');
}
	
	
	
	moveElementnew=function(action){
		var copyId=null;
		var pasteId=null;
		if(action=="FORWARD"){
			copyId='ddSourceVillage';
			pasteId='ddTargetVillage';
			 var villageMapElement=$( '#ddTargetVillage');
			removeError(villageMapElement);
		}else if(action=="BACK" ){
			copyId='ddTargetVillage';
			pasteId='ddSourceVillage'; 
		}
		else if(action=="Part"){
			copyId='ddSourceVillage';
			pasteId='ddTargetVillage';
			 var villageMapElement=$( '#ddTargetVillage');
			removeError(villageMapElement);
		}
		 
		 if(action=="BACK" || action=="FORWARD" || action== "Part" ){
			var options = $("#"+pasteId); 
			$('#'+copyId+' option:selected').each(function() { 
				var option = $("<option />");
				option.val($(this).val()).text($(this).text());
				options.append(option);
				$(this).remove();
				});
		}
		sortListBox(copyId);
		sortListBox(pasteId);
			
		
	}; 
	</script>
	