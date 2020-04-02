<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/showTree.css">
<style>
.treebutton { font-family: Arial,Helvetica,sans-serif; font-size: 10px;
}
#leftbase {display:inline-block;width: 30%;vertical-align: top;}
#showtree {display:inline-block;width:50%;}
.hr_dept {
    margin-top: 9px;
    margin-bottom: 20px;
    border: 0;
        border-top-width: 0px;
        border-top-style: none;
        border-top-color: currentcolor;
    border-top: 1px solid #eee;
}
.dbtn_align{
margin-top: 41px;
}
</style>

<script>
var _continue_url = "countinueRebuildTree.htm?<csrf:token uri='countinueRebuildTree.htm'/>";
var _save_url = "saveBuildTree.htm?<csrf:token uri='saveBuildTree.htm'/>";	
var _JS_STATE_CODE = "<c:out value='${stateCode}' />";
var office_id;
var officeDetails="<c:out value='${officeListDetails}' />";
var isCenterUser="<c:out value='${isCenterUser}' />"
$(document).ready(function() {
	if(!isCenterUser){
		buildTree();
	}
	
	
	$("#deptNameEnglish").blur(function(){
		
		if(validtaeDeptNameEng()){
			validateDeptNameEngUnique($(this).val());
		}
		
	});
	
	});
	





buildTree=function(){
	var div = document.getElementById("base");
	var parentHierarchy="<c:out value='${adminOrgDeptForm.parentHierarchy}' />";
	var treeHierArr=parentHierarchy.split(",");
	var treeLen=treeHierArr.length;
	var isParent=(treeLen==2?true:false);
	var parent_id=1;
	var lindex=treeLen;
	var slindex=(treeLen-1);
	var passColor='N';
	//alert(treeLen);
		ulState=createBase(isParent);
		div.appendChild(ulState);
		jQuery.each(treeHierArr, function(index, item) {
			if(index!=0){
				var itemArr=item.split("#");
				if(index==slindex-1){
					passColor='G';
				}else if(index==lindex-1){
					passColor='R';
					
				}
				createChildNode(parent_id,itemArr[1],itemArr[0],passColor);
				parent_id=itemArr[1];
			}
			
		});
		//alert('${adminOrgDeptForm.parentHierarchy}');
};

createChildNode=function(parent_id,new_child,child_name,passColor){
	
	
	var liParent=document.getElementById(parent_id);      
	
	if(liParent!=null){
		/*  var ulNewEntity=document.getElementById("ul"+parent_id);
		 if(ulNewEntity==null){ */
			 ulNewEntity =  document.createElement("UL");      
			 ulNewEntity.setAttribute("id", "ul"+parent_id);
		/*  } */
		
		 
		 var liNewEntity =  document.createElement("LI");  
		 liNewEntity.setAttribute("id",new_child);
		 
		 var buttonNewEntity =document.createElement("INPUT");	
		 buttonNewEntity.setAttribute("type", "button");
		 buttonNewEntity.setAttribute("value",child_name);
		 if(passColor=='G'){
			 buttonNewEntity.setAttribute("style","background:rgba(0, 255, 0, 0.34);color: #000d19;padding: 4px;font-size: 10px;"); 
		 }else  if(passColor=='R'){
			 buttonNewEntity.setAttribute("style","background:rgba(255, 0, 0, 0.34);color: #000d19;padding: 4px;font-size: 10px;"); 
			 office_id=new_child;
			 uloffice=document.createElement("UL"); 
			 uloffice.setAttribute("id", "ul"+office_id);
			
			
		 }else{
			 buttonNewEntity.setAttribute("style","color: #000d19;padding: 4px;font-size: 10px;"); 
		 }
		 buttonNewEntity.setAttribute("class", "treebutton");		 
		 liNewEntity.appendChild(buttonNewEntity);
		 if(passColor=='R' && officeDetails.length>0 ){
		 liNewEntity.appendChild(uloffice);
		 }
		 ulNewEntity.appendChild(liNewEntity);
		 
		 liParent.appendChild(ulNewEntity);
		 
		 if(passColor=='R'  && officeDetails.length>0){
			 showOffice();
		 }
		 
	}
};

var createBase=function(isParent){
	
	 var ulState = document.createElement("UL");       
	 ulState.setAttribute("id", "ul0")
	
	 var liState = document.createElement("LI");   
	 liState.setAttribute("id", "1")
	 
	 
	 
	 var buttonstate =document.createElement("INPUT");
	 
	 buttonstate.setAttribute("class", "treebutton");
	 buttonstate.setAttribute("type", "button");
	 buttonstate.setAttribute("value","State");
	 if(isParent){
		 buttonstate.setAttribute("style","background:rgba(0, 255, 0, 0.34);color: #000d19;padding: 4px;font-size: 10px;"); 
	 }else{
		 buttonstate.setAttribute("style","color: #000d19;padding: 4px;font-size: 10px;"); 
	 }
	
	//#999
	
	liState.appendChild(buttonstate);
	
	ulState.appendChild(liState);
	isBase=true;
	return ulState;
	
};







reProcessForm = function(processId) {
	
	
	
	 var deptNameEnglish = $("#deptNameEnglish").val();
	var sameLevel = document.getElementById("sameLevel");
	var newLevel = document.getElementById("newLevel");
	
		lgdAdminDepatmentDwr.checkExistingDepartmentName(parseInt(_JS_STATE_CODE), deptNameEnglish, null, {
			async : true,
			callback : function(data) {
				if (data) {
					$('#errdeptNameEnglish').html("<spring:message htmlEscape="true" code="${extendEntity}"></spring:message> Name Already Exist");
					return false;
				} else {
					var url = null;
					if (processId == "NEXT") {
						$('#btnCreateDeptNext').attr('disabled', 'disabled');
						
						sameLevel.value = false;
						url = _continue_url;
					} else if (processId == "SAVE") {
						$('#btnSaveDept').attr('disabled', 'disabled');
						url = _save_url;
					} else if (processId == "AddDeptatSameLevel") {
						url = _continue_url;
						sameLevel.value = true;
					} else {
						customAlert("Invalid Procees Called.");
						return false;
					}

					$('#close').attr('disabled', 'disabled');
					$("select[multiple] option").prop("selected", "selected");
					document.forms['adminOrgDeptForm'].method = "POST";
					document.forms['adminOrgDeptForm'].action = url;
					document.forms['adminOrgDeptForm'].submit();
					return true;
				}
			}
		}); 
/* 	} else {

		var url = null;
		if (processId == "NEXT") {
			$('#btnCreateDeptNext').attr('disabled', 'disabled');
			sameLevel.value = false;
			url = _continue_url;
		} else if (processId == "SAVE") {
			$('#btnSaveDept').attr('disabled', 'disabled');
			url = _save_url;
		} else if (processId == "AddDeptatSameLevel") {
			url = _continue_url;
			sameLevel.value = true;
		} else {
			customAlert("Invalid Procees Called.");
			return false;
		}

		$('#close').attr('disabled', 'disabled');
		$("select[multiple] option").prop("selected", "selected");
		document.forms['adminOrgDeptForm'].method = "POST";
		document.forms['adminOrgDeptForm'].action = url;
		document.forms['adminOrgDeptForm'].submit();
		return true;

	} */

};


 showOffice=function(){
	
	var officeArr=officeDetails.split("#");
	
	var liParent=document.getElementById(office_id);  
	
	var ulNewEntity=document.getElementById("ul"+office_id);  
	
	
		jQuery.each(officeArr, function(index, item) {
			
			var liNewEntity =  document.createElement("LI");  
			 liNewEntity.setAttribute("id","child"+index);
				
			 var buttonNewEntity =document.createElement("BUTTON");	
			 buttonNewEntity.setAttribute("class","btn btn-primary btn-xs");
			 buttonNewEntity.setAttribute("type", "button");
			 buttonNewEntity.innerHTML =item;
			 buttonNewEntity.setAttribute("style","width: 70px;background:rgba(0, 0, 255, 0.34);color: #000d19;padding: 4px;font-size: 10px;"); 
			 
			 liNewEntity.appendChild(buttonNewEntity);
			 ulNewEntity.appendChild(liNewEntity);
			 liParent.appendChild(ulNewEntity);
			
		});
	
	

	

	
}; 



validateFormdeails=function(processId){
	  $( "span[id^=err]" ).each(function(){
			$( this ).text("");
	    });
	var error=true; 
	var errors=new Array();
	errors[0]= validtaeDeptNameEng();
	errors[1]= validateCoverage();
	
	
	
	for ( var i = 0; i < errors.length; i++) {
		if (errors[i] == false) {
			error = false;
			break;
		}

	}
	if(error){
		
		reProcessForm(processId);
		
	}
	   
	
};


validateDeptNameEngUnique=function(deptNameEnglish){
	 
	if (!$_checkEmptyObject(deptNameEnglish)) {
		lgdAdminDepatmentDwr.checkExistingDepartmentName(parseInt(_JS_STATE_CODE), deptNameEnglish, null, {
			async : true,
			callback : function(data) {
				if (data) {
					$('#errdeptNameEnglish').html("<spring:message htmlEscape="true" code="${extendEntity}"></spring:message> Name Already Exist");
					return false;
				} else {
					$("#errdeptNameEnglish").val("");
					return true;
				}
			}
		});
	}else{
		$("#deptNameEnglish").val("");
	}
	
};

validtaeDeptNameEng=function(){
	var _error_flag=true;
	var re = new RegExp("^[a-zA-Z ]+$", "g");
	 var deptNameEnglish=$("#deptNameEnglish").val();

	 if($_checkEmptyObject(deptNameEnglish)){
		 $( '#errdeptNameEnglish').text("<spring:message code='dept.name.eng.require' htmlEscape='true'/>");
		 _error_flag=false;
	 }else{
		 if(!re.test(deptNameEnglish)){
			 $( '#errdeptNameEnglish').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>");	 
			 _error_flag=false;
		 }
	 }
	 return _error_flag;
};

validateCoverage=function(){
	$("#coverageError").hide();
	var _error_flag=true;
	if ($("#rdSpecificStateDis").is(':checked')) {
		var covLen=0;
		
		if(accessLevel=='S'){
			covLen=$('#ddTargetState').children().length;
		}
		if(accessLevel=='D'){
			if(parseInt(_JS_STATE_CODE)==0){
				covLen1=$('#ddTargetState').children().length;
				covLen=$('#ddTargetDistrict').children().length;
				covLen=covLen+covLen1;
				
			}else{
				covLen=$('#ddTargetDistrict').children().length;
			}
			
			
		}else if(accessLevel=='A'){
			covLen=$('#ddTargetAdminEnitiy').children().length;
		}else if(accessLevel=='U'){
			covLen=$('#targetUList').children().length;
		}
		else if(accessLevel=='X'){
			covLen=$('#targetDPList').children().length;
		}else if(accessLevel=='Y'){
			covLen=$('#targetDPList').children().length;
			
			if(covLen==0){
				covLen=$('#targetIPList').children().length;
			}
		}else if(accessLevel=='Z'){
			covLen=$('#targetDPList').children().length;
			
			if(covLen==0){
				covLen=$('#targetIPList').children().length;
			}
			if(covLen==0){
				covLen=$('#targetVpList').children().length;
			}
		}
		else if(accessLevel=='T'){
				covLen=$('#ddTargetDistrict').children().length;
				
				if(covLen==0){
					covLen=$('#ddTargetDistrictSDLvl').children().length;
				}
		}else if(accessLevel=='B'){
			covLen=$('#ddTargetDistrict').children().length;
			
			if(covLen==0){
				covLen=$('#ddTargetBlockSDLvl').children().length;
			}
		}else if(accessLevel=='V'){
			covLen=$('#ddTargetDistrict').children().length;
			
			if(covLen==0){
				covLen=$('#ddTargetDistrictSDLvl').children().length;
			}
			
			if(covLen==0){
				covLen=$('#ddTargetDistrictVillLvl').children().length;
			}
			
		}
		else if(accessLevel=='B'){
			covLen=$('#ddTargetDistrict').children().length;
			
			if(covLen==0){
				covLen=$('#ddTargetBlockSDLvl').children().length;
			}
		}
		
		if(covLen == 0){
			$("#coverageError").show();
			 $( '#errCoverage').text("<spring:message code='select.coverage' htmlEscape='true'/>");	 
			 _error_flag=false;
		}
	}
	
	
	 return _error_flag;
};

callActionUrl=function(url){
	   
    $( 'form[id=adminOrgDeptForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=adminOrgDeptForm]' ).attr('method','post');
	$( 'form[id=adminOrgDeptForm]' ).submit();
};
</script>