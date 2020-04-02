<script type="text/javascript">
var level;
var parent;
var setFlag=false;
var prevLevel;
showHideBlock= function(lbdetails)
{
	$("#tableId").hide();
	$("#save").hide();
	if(lbdetails == ""){
		$('#errorentity').html("Please select local body type");
	}else{
		$('#errorentity').html("");
	}
	var detailArr =lbdetails.split(":");
	var lbTypeCode=	parseInt(detailArr[0]);
	level=	detailArr[1];
	var stateCode=parseInt(detailArr[2]);
	var category=detailArr[3];
	parent=parseInt(detailArr[4]);
	var lbtypeName=detailArr[5];
	 $("#DLevel").hide(); 
	 $("#ILevel").hide(); 
	 $("#lbTypeCode").val(lbTypeCode);
	 $("#parentLocalbodyCode").val(0);
	 $("#category").val(category);
	 
	 
	if ((level == 'D' && category == 'P') || (level == 'D' && category == 'T')) {
		$("#firstlevel").html(" " + lbtypeName);
		if(parent==0){
			getFirstLevelList(stateCode, lbTypeCode);
		}
	}else if((level == 'I' && category == 'P') || (level == 'I' && category == 'T')) {
		if (parent == 0){
			getdisInterPanchayatList(stateCode, lbTypeCode);
			}
		else{
			getLocalBodyListbylblcCode(stateCode, parent);
			}
	}else if ((level == 'V' && category == 'P') || (level == 'V' && category == 'T')) {
	
		if (parent == 0){
			getdisVillagePanchayatList(stateCode, lbTypeCode);
		}else{
			getLocalBodyListbylblcCode(stateCode, parent);
		}
			
	}
};

clearFields = function(){
	$('#entity').val(0);
	$('#distLevel').val(0);
	$('#interLevel').val(0);
	$("#tableId").hide();
	$("#save").hide();
}

getdisInterPanchayatList = function (statecode, lbTypeCode){
	
lgdDwrlocalBodyService.getPanchayatListbylblcCode(statecode, lbTypeCode, {
callback : function(result){
dwr.util.removeAllOptions('interLevel');
$('#interLevel').append($('<option>', {
    value: 0,
    text: 'Select'
}));
dwr.util.addOptions('interLevel', result, 'localBodyCode', 'localBodyNameEnglish');
},
async : true
});	
}; 

getLocalBodyListbylblcCode = function (statecode, lblc){
	lgdDwrDesignationService.getLocalbodyDetailbyCode(statecode, lblc, {
	callback : function(result){
	if(result!=null){
		var object	=result[0];
		var lblc = object.localBodyTypeCode;
		var plblc = object.parentTierSetupCode;
		var level = object.level;
		var name = object.localBodyTypeName;
		switch(level){
		case 'D':
			$("#firstlevel").html(" " + name);
			if (plblc == 0) {
				// alert("inside2");
				if(prevLevel!='I'){
					setFlag=true;
				}
				   $("#DLevel").show(); 
				getdisVillagePanchayatList(statecode, lblc);
			}	
			break;
		case 'I':
			$("#secondlevel").html(" " + name);
			$("#ILevel").show(); 
			if(plblc == 0) {
				getdisInterPanchayatList(statecode, lblc);
			}else{
				prevLevel=level;
				getLocalBodyListbylblcCode(statecode, plblc);
			}
			
			
		}
	}
	/* dwr.util.removeAllOptions('interLevel');	
	$('#interLevel').append($('<option>', {
	    value: 0,
	    text: 'Select'
	}));	
	
	dwr.util.addOptions('interLevel', result, 'localBodyCode', 'localBodyNameEnglish'); */
	},
	async : true
	});	
	}; 
	
	getdisVillagePanchayatList=function (statecode, lblc){
		dwr.util.removeAllOptions('distLevel');
		$('#distLevel').append($('<option>', {
		    value: 0,
		    text: 'Select'
		}));
		<c:if test="${districtUser ne true}">
		lgdDwrlocalBodyService.getPanchayatListbylblcCode(statecode, lblc, {
			callback : function(result){
			dwr.util.addOptions('distLevel', result, 'localBodyCode', 'localBodyNameEnglish');
				},
				async : true
				});	
		</c:if>
		<c:if test="${districtUser eq true}">
		lgdDwrlocalBodyService.getTopLocalbodyListforDistrictUser(statecode, lblc,'${districtCode}', {
			callback : function(result){
			dwr.util.addOptions('distLevel', result, 'localBodyCode', 'localBodyNameEnglish');
			},
				async : true
				});	
		</c:if>
		
	};
	

getIntegermediatePanchayatListbylblc=function(lblc){
	if(lblc != 0) $('#errordistrict').html("");
	
	$("#tableId").hide();
	$("#save").hide();
	
	if(level=='I' && setFlag ){
		$("#parentLocalbodyCode").val(lblc);
	}else{
		dwr.util.removeAllOptions('interLevel');
		$('#interLevel').append($('<option>', {
		    value: 0,
		    text: 'Select'
		}));
		<c:if test="${districtUser ne true}">
		lgdDwrlocalBodyService.getchildlocalbodiesWithoutCountByParentcode(lblc, {
			callback : function(result){
			dwr.util.addOptions('interLevel', result, 'localBodyCode', 'localBodyNameEnglish');
				},
				async : true
				});	
		</c:if>
			
		<c:if test="${districtUser eq true}">
		lgdDwrlocalBodyService.getchildlocalbodiesWithoutCountByParentcodeDistrict(lblc,'${districtCode}', {
			callback : function(result){
			dwr.util.addOptions('interLevel', result, 'localBodyCode', 'localBodyNameEnglish');
			},
				async : true
				});
		</c:if>
	}
	
	
};

setVillagePanchayatCode=function(lblc){
	if(lblc != 0) $('#errorinter').html("");
	
	$("#tableId").hide();
	$("#save").hide();
	$("#parentLocalbodyCode").val(lblc);
};

validateSubmitForm=function(){
	var errorflag=false;
	var errname="";
	var lbTypeCode=$("#lbTypeCode").val();
	var parentLocalbodyCode=$("#parentLocalbodyCode").val();
	if(lbTypeCode==0){
		errorflag=true;
		errname="Please select localbody Type";
	}
	else if(parent!=0 && parentLocalbodyCode==0)
	{
		errorflag=true;
		errname="Please select localbody";
	}
	
	if(errorflag){
		alert(errname);
		/* $("#cAlert").html(errname);
		$("#cAlert").dialog({
			title : '<spring:message htmlEscape="true" code="Label.LocalBodyFreeze/Unfreeze"></spring:message>',
			resizable : false,
			height : 200,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
					return false;
				}
			}
		}); */
		return false;
	}
	else{
		return true;
	}
};

callActionUrl=function(url){
    $( 'form[id=localBodyFreezePost]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=localBodyFreezePost]' ).attr('method','post');
	$( 'form[id=localBodyFreezePost]' ).submit();
};
</script>