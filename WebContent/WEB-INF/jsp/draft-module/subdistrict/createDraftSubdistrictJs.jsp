<%@include file="commanDraftSubdistrictJs.jsp"%>


<script>
/* var subdistrictListPart='${draftSubdistrictForm.listofSubdistrictPart}'!=''?'${draftSubdistrictForm.listofSubdistrictPart}':null;
var subdistrictListFull='${draftSubdistrictForm.listofSubdistrictFull}'!=''?'${draftSubdistrictForm.listofSubdistrictFull}':null;
var draftsubDistrictNameEng='${draftSubdistrictForm.listofSubdistrictNameEng}'!=''?'${draftSubdistrictForm.listofSubdistrictNameEng}':null; */
//var firstErrorElement=false;

//var isPartFlag;
$(document).ready(function() {
	
	$("#subdistrictNameEnglish").blur(function(){
		validateSubdistrictNameEngUnique($(this).val());
	});
	
	$("#districtCode").change(function() {
		$( this ).removeClass("error");
		$( '#err' + $( this ).attr('id') ).text("");
		changeDistrictClearAll();
		 if (!$_checkEmptyObject($(this).val())) {
			 $("#selectDistrictCode").val($(this).val());
		 }
		 getDraftSubdistrictList($(this).val(),subdistrictListPart,subdistrictListFull); 
	 });
	
	
	
	$("#btnFormActionAddAnoter").click(function() {
		validateFormdeails("AddAnother");
	 });
	
	$("#btnFormActionProceestoSave").click(function() {
		validateFormdeails("ProcessGovOrder");
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

function getSubdistrictList(obj){
	var jsObj=$("#"+obj.id);
	jsObj.removeClass("error");
	$( '#err' + jsObj.attr('id') ).text("");
	changeDistrictClearAll();
	 if (!$_checkEmptyObject(jsObj.val())) {
		 $("#selectDistrictCode").val(jsObj.val());
	 }
	 getDraftSubdistrictList(jsObj.val(),subdistrictListPart,subdistrictListFull); 
}

getDraftSubdistrictList=function(districtCode,draftSudistrictsPart,draftSubdistrictsFull){
	removeAllOptions('subdistrictList')
	 if (!$_checkEmptyObject(districtCode)) {
		 dwrDraftSubdistrictService.getDraftSubdistrictList(parseInt(districtCode),draftSudistrictsPart,draftSubdistrictsFull, {
				callback : function( result ) {
					var options = $("#subdistrictList"); 
					
					jQuery.each(result, function(index, obj) {
						var option = $("<option />");
						var _value=obj.subdistrictCode;
						var _text=obj.subdistrictNameEnglish;
						if (obj.operation_state == 'F') {
							$(option).attr('disabled', 'disabled');
							var _text=obj.subdistrictNameEnglish+"(Subdistrict is being used as Drafted)";
						}else if(obj.isPart== 'T'){
							_value=obj.subdistrictCode+"@PART";
							_text=obj.subdistrictNameEnglish+"(PART)";
							
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





loadElementandShowAddAnother=function(){
	var districtCodeElement = $( '#districtCode' );
	$("#districtCode option[value='${draftSubdistrictForm.selectDistrictCode}']").attr("selected", "selected");
	$( districtCodeElement).prop( "disabled", true );
	 getDraftSubdistrictList(parseInt('${draftSubdistrictForm.selectDistrictCode}'),subdistrictListPart,subdistrictListFull); 
};


changeDistrictClearAll=function(){
	$("#subdistrictNameEnglish").val("");
	removeAllOptions('subdistrictList');
	removeAllOptions('contibutingSubdistrict');
	removeAllOptions('villageList');
	removeAllOptions('contibutingVillage');
	
};

validateFormdeails=function(formAction){
	  $( "span[id^=err]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	errors[0]= !validateDistrictCode();
	errors[1]= validateSubdistrictNameEng();
	errors[2]= validateSubdistrictCoverage();
	//errors[3]= validateVillageCoverage(); 
	errors[3]=validateCordinate();
	
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	if(error){
		
		
		
		 $("#contibutingSubdistrict option").prop("selected",true);
		 $("#contibutingVillage option").prop("selected",true);
		 $("#formAction").val(formAction);
		 $( "#btnFormActionAddAnoter" ).prop( "disabled", true );
		 $( "#btnFormActionProceestoSave" ).prop( "disabled", true );
		 $( "#btnActionClose" ).prop( "disabled", true ); 
		callActionUrl('startDraftSubdistrictCreation.htm');
		
	} 
	   
			
};

loadElementandShowError=function(){
	var districtCode='${draftSubdistrictForm.selectDistrictCode}'!=''?'${draftSubdistrictForm.selectDistrictCode}':null;
	var districtElement = $( '#districtCode' );
	if(districtCode!=null){
		
		$("#districtCode option[value='${draftSubdistrictForm.selectDistrictCode}']").attr("selected", "selected");
		$( districtElement).prop( "disabled", true );
		 getDraftSubdistrictList(parseInt('${draftSubdistrictForm.selectDistrictCode}'),subdistrictListPart,subdistrictListFull); 
	}else
		{
		$( districtElement ).addClass("error");
		$(districtElement).focus();
		}
	
};

function saveData(){
	callActionUrl('startDraftSubdistrictCreation.htm');
}

</script>

<!--#stared page return from server with error then call loadElementandShowError  method -->
<c:if test="${serverAdd eq true}">
			<script>
			$(window).load(function () {
				loadElementandShowAddAnother();
			}); 
			</script>
</c:if>
<!--#end page return from server with error then call loadElementandShowError  method -->
<!--#stared page return from server with error then call loadElementandShowError  method -->
<c:if test="${serverError eq true}">
			<script>
			$(window).load(function () {
				loadElementandShowError();
			}); 
			</script>
</c:if>
<!--#end page return from server with error then call loadElementandShowError  method -->