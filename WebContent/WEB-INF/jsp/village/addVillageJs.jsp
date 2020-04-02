 <c:set var="createVillageGISFlag" value="<%=in.nic.pes.lgd.constant.CommanConstant.CREATE_VILLAGE_GIS_PREVIEW.toString()%>"></c:set> 
<script type="text/javascript" language="javascript">

showGISPreview=function(){
	
	var subdistCode = $( '#ddSubdistrict' ).val();
	var villageName = $( '#OfficialAddress' ).val();
	if(validateGISPreview(subdistCode,villageName)){
		var vpCodeArr = [];
		$('#villageListNew option').each(function() { 
			/* if(($(this).val().indexOf("PART"))>-1){ */
				villCode=$(this).val();
				vpCodeArr.push(villCode.substring(0, villCode.length - 3));              
			/* } */
		});
		callGISMapView(subdistCode,villageName,vpCodeArr.toString(),"${createVillageGISFlag}");
	}
};

var callGISMapView = function ( subdistrictCode, villageName,covrage,isShowOnlyBoundary){
	lgdDwrVillageService.getMappedVillageForGIS(parseInt(subdistrictCode),null,villageName,covrage,isShowOnlyBoundary, {
		callback : function( result ){
			//alert(result);
			 if("FAILED" == result){
				customAlert(result);
			}else{
				$("#dialogBXTitle").text(' GIS Map View of '+villageName+' Village');
				
				 $('#myIframe').attr('src', result);
				/*  $("#myIframe").load(function(){
	            	alert("test"); 
			    }); */
				$('#dialogBX').modal('show'); 
				/* $("#dialogBX").dialog({
					title:' GIS Map View of '+villageName+' Village',
				    modal: true,
				    width:950,
				    height: 700,
				    resizable:false,
				    open: function(ev, ui){
				    	 showLoadingImage();
						
			             $('#myIframe').attr('src', result);
			             $("#myIframe").load(function(){
			            	 hideLoadingImage(); 
					    });
			    	}
				});	 */
			} 
		},
		errorHandler : function( errorString, exception){
				customAlert(exception);
		},
		async : true
	});		
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

validateGISPreview=function(subdistCode,villageName){
	var flag=true;
	var districtCode=$("#ddDistrict").val();
	if($_checkEmptyObject(districtCode) || districtCode=="0"){
		$( '#errddDistrict').text("<spring:message code='error.PSDT' htmlEscape='true'/>");
		if(flag){
			$( "#ddDistrict" ).focus();
		}
		flag=false;
	}

	
	if($_checkEmptyObject(subdistCode) || subdistCode=="0"){
		$( '#errddSubdistrict').text("<spring:message code='error.PSSDT' htmlEscape='true'/>"); 
		if(flag){
			$( "#ddSubdistrict" ).focus();
		}
		flag=false;
	}
	
	if(villageName.length<1){
		$( '#errOfficialAddress').text("<spring:message code='error.blank.villageNameInEn' htmlEscape='true'/>");
		if(flag){
			$( "#OfficialAddress" ).focus();
		}
		
		flag=false;
	}
	
	if(flag){
		if (!$("#chkcvillage").is(':checked')) {
			if(flag){
				$( "#chkcvillage" ).focus();
			}
			
			flag=false;
			customAlert("<spring:message code='error.preview.gis' text='Preview GIS Option only for existing village' htmlEscape='true'/>");
			
		}else{
			
			var len=$("#villageListNew option").length;
			if(len<1){
				$( '#errvillageListNew').text("<spring:message code='Label.SELECT' htmlEscape='true'/>"+" "+ "<spring:message code='Label.CONTRIBUTINGVILLAGELIST' htmlEscape='true'/>");
				if(flag){
					$( "#villageListNew" ).focus();
				}
				flag=false;
			}
		}
		
	}
	
	return flag;
};

clear_message=function(){
	$( '#errOfficialAddress').text(""); 
}


</script>