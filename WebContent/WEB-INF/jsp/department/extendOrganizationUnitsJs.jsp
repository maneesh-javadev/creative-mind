<style>
.resizedTextbox {width: 350px}
</style>



<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<%@include file="../common/taglib_includes.jsp"%>
<c:set var="existDeptSlc" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.EXISTING_DEPT_SLC.toString()%>"></c:set>
<c:set var="entityTypeLandregion" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ENTITY_TYPE_LANDREGION.toString()%>"></c:set>
<c:set var="entityTypeBoth" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ENTITY_TYPE_BOTH.toString()%>"></c:set>
<c:set var="orgTypeCodeDept" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()%>"></c:set>
<c:set var="orgTypeCodeOrg" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_ORG.toString()%>"></c:set>
<c:set var="stateLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.STATE_CODE.toString()%>"></c:set>
<c:set var="districtLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.DISTRICT_CODE.toString()%>"></c:set>
<c:set var="subDistrictLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.SUBDISTRICT_CODE.toString()%>"></c:set>
<c:set var="villageLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.VILLAGE_CODE.toString()%>"></c:set>
<c:set var="blockLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.BLOCK_CODE.toString()%>"></c:set>
<%-- <c:set var="DPLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()%>"></c:set>
<c:set var="IPLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()%>"></c:set>
<c:set var="VPLevel" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.VILLAGE_PANCHAYAT_CODE.toString()%>"></c:set> --%>
<c:set var="slc" value="${adminOrgDeptForm.stateCode}"></c:set>

<script>

var _DP_LEVEL = '<%=in.nic.pes.lgd.constant.DepartmentConstent.DISTRICT_PANCHAYAT_LEVEL%>';
var _IP_LEVEL = '<%=in.nic.pes.lgd.constant.DepartmentConstent.INTERMEDIATE_PANCHAYAT_LEVEL%>';
var _VP_LEVEL = '<%=in.nic.pes.lgd.constant.DepartmentConstent.VILLAGE_PANCHAYAT_LEVEL%>';
var _U_LEVEL = '<%=in.nic.pes.lgd.constant.DepartmentConstent.URBAN_LEVEL%>';

var isCenter=false;
var selOlc;
var globalCoveageType;

var existingVillageString="";
var existingSubdistrictString="";
var existingBlockString="";
var _JS_STATE_CODE = "<c:out value='${adminOrgDeptForm.stateCode}' />";




showlbSpecifcDetails=function(paramAllOrSpecificLevel){
		if (paramAllOrSpecificLevel == "F") {
			if (accessLevel == _DP_LEVEL){
				$("#tblDPSpecific").hide();
			}else if (accessLevel == _IP_LEVEL){
				$("#tblCheckCoverageDPFull").hide();
				$("#tblDPFULL").hide();
				$("#DPChkpart").hide();
				$("#tblDPSelectBox").hide();
				$("#tblIPSpecific").hide();
				$("#orgEntityType").prop('checked', true);
				$("#DPChkFull").prop('disabled', true);
			
			}else if (accessLevel == _VP_LEVEL){
				$("#tblCheckCoverageDPFull").hide();
				$("#tblDPFULL").hide();
				$("#DPChkpart").hide();
				$("#tblDPSelectBox").hide();
				$("#tblIPSpecific").hide();
				$("#tblVPSpecific").hide();
				$("#DPChkpartVP").hide();
				$("#tblDPSelectBoxVP").hide();
				$("#tblIPSelectBoxVP").hide();
				
				
			
				}
				
			}else{
				
				if (accessLevel == _DP_LEVEL){
					$("#tblDPSpecific").show();
				}else if (accessLevel == _IP_LEVEL){
					$("#tblCheckCoverageDPFull").show();
					$("#tblDPFULL").show();
					$("#DPChkpart").show();
					$("#tblDPSelectBox").show();
					$("#tblIPSpecific").show();
					
					
				
				}else if (accessLevel == _VP_LEVEL){
					$("#tblCheckCoverageDPFull").hide();
					$("#tblDPFULL").hide();
					$("#DPChkpart").hide();
					$("#tblDPSelectBox").hide();
					$("#tblIPSpecific").hide();
					$("#tblVPSpecific").hide();
					$("#DPChkpartVP").hide();
					$("#tblDPSelectBoxVP").hide();
					$("#tblIPSelectBoxVP").hide();
					
					
				
					}
			}
		
	
	
};

fillingSelectedCoverageData=function(orgCoverageEntityType,coverage)
{
	/* alert(orgCoverageEntityType);
	alert(accessLevel);
	alert(coverage); */
	
	globalCoveageType=coverage;
	if (coverage=="F"){
		$("#rdAllStateDis").prop('checked', true);
		$("#rdSpecificStateDis").prop('disabled', true);
		$("#btnSaveDept").prop('disabled', true);
		$("#tblAllSpecificDetails").hide();
		
		
	}
	else{
		
		
		
		if (accessLevel == _DP_LEVEL){
			showAllOrSpecificDetails(coverage);
		}else if (accessLevel == _IP_LEVEL){
			if(orgCoverageEntityType==-1){
				$("#DPChkFull").prop('checked', true);
				$("#DPChkFull").prop('disabled', true);
				$("#tblDPFULL").show();
				
			}else if(orgCoverageEntityType==-2){
				$("#DPChkpart").prop('checked', true);
				$("#DPChkpart").prop('disabled', true);
				$("#tblDPSelectBox").show();
				$("#tblIPSpecific").show();
				
			}
		}else if (accessLevel == _VP_LEVEL){
			
			
			
			if(orgCoverageEntityType==-1){
				$("#DPChkFull").prop('checked', true);
				$("#DPChkFull").prop('disabled', true);
				$("#tblDPFULL").show();
				
			}else if(orgCoverageEntityType==-2){
				$("#DPChkpart").prop('checked', true);
				$("#DPChkpart").prop('disabled', true);
				$("#tblDPSelectBox").show();
				$("#tblIPSpecific").show();
				
			}else if(orgCoverageEntityType==-3){
				$("#DPChkpart").prop('checked', true);
				$("#DPChkpart").prop('disabled', true);
				$("#tblDPSelectBoxVP").show();
				$("#tblIPSelectBox").show();
				$("#tblVPSpecific").show();
			}
		}
		else
		if (accessLevel == _DISTRICT_LEVEL){
			showAllOrSpecificDetails(coverage);
		}else if (accessLevel == _SUBDISTRICT_LEVEL){
			if(orgCoverageEntityType==2){
				$("#districtChkFull").prop('checked', true);
				$("#districtChkFull").prop('disabled', true);
				$("#tblDistrictSpecific").show();
				
			}else if(orgCoverageEntityType==3){
				$("#DistrictChkPart").prop('checked', true);
				$("#DistrictChkPart").prop('disabled', true);
				$("#tblDistrictSelectBox").show();
				$("#tblSubdistrictSpecific").show();
				
			}
		}else if (accessLevel == _VILLAGE_LEVEL){
			
			
			
			//alert(orgCoverageEntityType);
			
			if(orgCoverageEntityType==2){
				$("#districtChkFull").prop('checked', true);
				$("#districtChkFull").prop('disabled', true);
				$("#tblDistrictSpecific").show();
				
				
			}else if(orgCoverageEntityType==3){
				$("#SubDistrictChkFull").prop('checked', true);
				$("#SubDistrictChkFull").prop('disabled', true);
				$("#tblDistrictSelectBox").show();
				$("#tblSubdistrictSpecific").show();
				
			}else if(orgCoverageEntityType==4){
				$("#SubDistrictChkPart").prop('checked', true);
				$("#SubDistrictChkPart").prop('disabled', true);
				$("#tblDisttSubDisttVillLvlSelectBox").show();
				$("#tblVillageSpecific").show();
				
			}
		} else if (accessLevel == _BLOCK_LEVEL){
			if(orgCoverageEntityType==2){
				$("#districtChkFull").prop('checked', true);
				$("#districtChkFull").prop('disabled', true);
				$("#tblDistrictSpecific").show();
				
				
			}else if(orgCoverageEntityType==5){
				$("#DistrictChkPart").prop('checked', true);
				$("#DistrictChkPart").prop('disabled', true);
				$("#tblDistrictSelectBox").show(); 
				$("#tblBlockSpecific").show(); 
			}
		}
	
	}
	
};

showAllOrSpecificDetails=function(paramAllOrSpecificLevel){
	if (paramAllOrSpecificLevel == "F") {
		$("#tblAllSpecificDetails").hide();
		if (accessLevel == _SUBDISTRICT_LEVEL){
			$("#ddTargetDistrict").prop('disabled', true);
			$("#ddTargetDistrictSDLvl").prop('disabled', true);
		}
	}else{
		$("#tblAllSpecificDetails").show();
		if (accessLevel == _SUBDISTRICT_LEVEL){
			$("#ddTargetDistrict").prop('disabled', false);
			$("#ddTargetDistrictSDLvl").prop('disabled', false);
		}
	}	
};

registerOnclickMethods = function() {
	$('#mvFrDistt').click(function() {
		if(accessLevel==_DISTRICT_LEVEL){
			addItemList('dddistrictAtStateLvl','ddTargetDistrict');	
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict");	
		}else if (accessLevel == _SUBDISTRICT_LEVEL){
			addItemList('dddistrictAtStateLvl','ddTargetDistrict');	
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict");
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','district');
			if( $('#ddTargetDistrictSDLvl').length )        
			{
				removeAllOptions('ddSubdistrictAtSubDistrictLvl');
				getCoverageSubDistrictList();
			}
		}else  if (accessLevel == _VILLAGE_LEVEL){
			addItemList('dddistrictAtStateLvl','ddTargetDistrict');
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict"); 
			
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','district');
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','dddistrictAtVillLvl');
			if( $('#ddTargetDistrictSDLvl').length )        
			{
				removeAllOptions('ddSubdistrictAtSubDistrictLvl');
				getCoverageSubDistrictList();
				
				
			}
			if( $('#ddTargetDistrictVillLvl').length )        
			{
				removeAllOptions('ddSubdistrictAtVillLvl');
				removeAllOptions('ddVillageAtVillLvl');
				getCoverageVillageList();
			}
			
		}
		else if(accessLevel == _BLOCK_LEVEL){
			addItemList('dddistrictAtStateLvl','ddTargetDistrict');	
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict");	
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','district');
			if( $('#ddTargetBlockSDLvl').length )        
			{
				removeAllOptions('ddBlockAtDistrictLvl');
				getCoverageBlockList();
			}
			
		}
	});
	
	$('#mvFrSubDist').click(function() {
		$("#err_subDistrict").html("");
		var subdistrictArray = [];
		var selSubdistrictArray = [];
		$('#ddSubdistrictAtSubDistrictLvl option:selected').each(function() { subdistrictArray.push($(this).val());});
		$('#ddTargetDistrictSDLvl option').each(function() { selSubdistrictArray.push($(this).val());});
		var subdistrictCodes=subdistrictArray.toString()+",0";
		subdistrictCodes=subdistrictCodes+selSubdistrictArray.toString();
		parentEntityCode=$("#district").val();
		lgdAdminDepatmentDwr.validateEntityListforFullCoverage(subdistrictCodes,'${subDistrictLevel}',parentEntityCode,{
			callback : function(result) {
			if(result==true){
				$("#err_subDistrict").html("You can't select all subdistirct of partially selected district ");
			}else{
				if (accessLevel == _SUBDISTRICT_LEVEL){
					addItemList('ddSubdistrictAtSubDistrictLvl','ddTargetDistrictSDLvl');
				}else  if (accessLevel == _VILLAGE_LEVEL){
					addItemList('ddSubdistrictAtSubDistrictLvl','ddTargetDistrictSDLvl');
					sortListBox("ddSubdistrictAtSubDistrictLvl");
					sortListBox("ddTargetDistrictSDLvl"); 
					if( $('#ddTargetDistrictVillLvl').length )        
					{
						$("#dddistrictAtVillLvl").val('');
						removeAllOptions('ddSubdistrictAtVillLvl');
						removeAllOptions('ddVillageAtVillLvl');
						getCoverageVillageList();
					}
				}	
			}
				
			},
			async : true
		});
	
	});
	
	$('#mvBackDistt').click(function() {
		if(accessLevel==_DISTRICT_LEVEL){
			addItemList('ddTargetDistrict','dddistrictAtStateLvl');
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict");	
		}else if (accessLevel == _SUBDISTRICT_LEVEL){
			addItemList('ddTargetDistrict','dddistrictAtStateLvl');
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict");
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','district');
		}else  if (accessLevel == _VILLAGE_LEVEL){
			addItemList('ddTargetDistrict','dddistrictAtStateLvl');	
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict"); 
			
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','district');
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','dddistrictAtVillLvl');
			
		}
		else if(accessLevel == _BLOCK_LEVEL){
			addItemList('ddTargetDistrict','dddistrictAtStateLvl');	
			sortListBox("dddistrictAtStateLvl");
			sortListBox("ddTargetDistrict");	
			copyAllObjectFormOnetoAnother('dddistrictAtStateLvl','district');
			
			
		}
	
	});
	
	$('#mvBackSubDist').click(function() {
		if (accessLevel == _SUBDISTRICT_LEVEL){
			addItemList('ddTargetDistrictSDLvl','ddSubdistrictAtSubDistrictLvl');
			sortListBox("ddSubdistrictAtSubDistrictLvl");
			sortListBox("ddTargetDistrictSDLvl"); 
		}else  if (accessLevel == _VILLAGE_LEVEL){
			addItemList('ddTargetDistrictSDLvl','ddSubdistrictAtSubDistrictLvl');
			sortListBox("ddTargetDistrictSDLvl");
			sortListBox("ddSubdistrictAtSubDistrictLvl"); 
		}
	
	});
	
	$('#mvFrVillage').click(function() {
		$("#err_village").html("");
		var villageArray = [];
		var selVillageArray = [];
		$('#ddVillageAtVillLvl option:selected').each(function() { villageArray.push($(this).val());});
		$('#ddTargetDistrictVillLvl option').each(function() { selVillageArray.push($(this).val());});
		var villageCodes=villageArray.toString()+",0";
		villageCodes=villageCodes+selVillageArray.toString();
		parentEntityCode=$("#ddSubdistrictAtVillLvl").val();
		lgdAdminDepatmentDwr.validateEntityListforFullCoverage(villageCodes,'${villageLevel}',parentEntityCode,{
			callback : function(result) {
			if(result==true){
				$("#err_village").html("You can't select all village of partially selected subdistrict ");
			}else{
				if (accessLevel == _VILLAGE_LEVEL){
					addItemList('ddVillageAtVillLvl','ddTargetDistrictVillLvl');
					sortListBox("ddVillageAtVillLvl");
					sortListBox("ddTargetDistrictVillLvl"); 
				}
			}
				
			},
			async : true
		});
		
		
	
	});
	
	$('#mvBackVillage').click(function() {
		 if (accessLevel == _VILLAGE_LEVEL){
			addItemList('ddTargetDistrictVillLvl','ddVillageAtVillLvl');
			sortListBox("ddVillageAtVillLvl");
			sortListBox("ddTargetDistrictVillLvl"); 
		}
	
	});
	
	$('#mvFrBlock').click(function() {
		$("#err_block").html("");
		var blockArray = [];
		var selBlockArray = [];
		$('#ddBlockAtDistrictLvl option:selected').each(function() { blockArray.push($(this).val());});
		$('#ddBlockAtDistrictLvl option:selected').each(function() { selBlockArray.push($(this).val());});
		var blockCodes=blockArray.toString()+",0";
		blockCodes=blockCodes+selBlockArray.toString();
		parentEntityCode=$("#district").val();
		lgdAdminDepatmentDwr.validateEntityListforFullCoverage(blockCodes,'${blockLevel}',parentEntityCode,{
			callback : function(result) {
			if(result==true){
				$("#err_block").html("You can't select all block of partially selected district ");
			}else{
				addItemList('ddBlockAtDistrictLvl','ddTargetBlockSDLvl');
				sortListBox("ddBlockAtDistrictLvl");
				sortListBox("ddTargetBlockSDLvl"); 
			}
				
			},
			async : true
		});
		
		
	});
	
	$('#mvBackBlock').click(function() {
		addItemList('ddTargetBlockSDLvl','ddBlockAtDistrictLvl');
		sortListBox("ddBlockAtDistrictLvl");
		sortListBox("ddTargetBlockSDLvl"); 
	});
	
	$('#mvFrAdminEntity').click(function() {
		addItemList('ddAdminEnitiy','ddTargetAdminEnitiy');
		sortListBox("ddAdminEnitiy");
		sortListBox("ddTargetAdminEnitiy"); 
	});
	
	$('#mvBackAdminEntity').click(function() {
		addItemList('ddTargetAdminEnitiy','ddAdminEnitiy');
		sortListBox("ddAdminEnitiy");
		sortListBox("ddTargetAdminEnitiy"); 
	});
	
	$('#mvFrDP').click(function() {
		if(accessLevel==_DP_LEVEL){
			addItemList('sourceDPList','targetDPList');	
			sortListBox("sourceDPList");
			sortListBox("targetDPList");	
		}else if (accessLevel == _IP_LEVEL){
			addItemList('sourceDPList','targetDPList');	
			sortListBox("sourceDPList");
			sortListBox("targetDPList");	
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBox');
			if( $('#targetIPList').length )        
			{
				removeAllOptions('sourceIPList');
				getlbCoverage();
			}
		}else  if (accessLevel == _VP_LEVEL){
			addItemList('sourceDPList','targetDPList');	
			sortListBox("sourceDPList");
			sortListBox("targetDPList");	
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBox');
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBoxvp');
			if( $('#targetIPList').length )        
			{
				removeAllOptions('sourceIPList');
				getlbCoverage();
			}
			if( $('#targetVPList').length )        
			{
				removeAllOptions('sourceIPList');
				removeAllOptions('sourceVPList');
				getlbCoverage();
			}
			
		}
		
	});
	
	$('#mvBackDP').click(function() {
		if(accessLevel==_DP_LEVEL){
			addItemList('targetDPList','sourceDPList');	
			sortListBox("sourceDPList");
			sortListBox("targetDPList");	
		}else if (accessLevel == _IP_LEVEL){
			addItemList('targetDPList','sourceDPList');	
			sortListBox("sourceDPList");
			sortListBox("targetDPList");	
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBox');
			removeAllOptions('sourceIPList');
			//removeAllOptions('targetIPList');
		}else  if (accessLevel == _VP_LEVEL){
			addItemList('targetDPList','sourceDPList');	
			sortListBox("sourceDPList");
			sortListBox("targetDPList");
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBox');
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBoxvp');
			removeAllOptions('sourceIPList');
			//removeAllOptions('targetIPList');
			removeAllOptions('sourceVPList');
			//removeAllOptions('targetVpList');
			
		}
		
	
	});
	
	
	$('#mvFrIP').click(function() {
		 if (accessLevel == _IP_LEVEL){
			addItemList('sourceIPList','targetIPList');	
			sortListBox("sourceIPList");
			sortListBox("targetIPList");	
			
			
		}else  if (accessLevel == _VP_LEVEL){
			addItemList('sourceIPList','targetIPList');	
			sortListBox("sourceIPList");
			sortListBox("targetIPList");		
			
			
			if( $('#targetIPList').length )        
			{
				removeAllOptions('sourceIPList');
				 $("#dpSelectBox option[value='']").attr("selected", "selected");
				getlbCoverage();
			}
			if( $('#targetVpList').length )        
			{
				removeAllOptions('sourceIPList');
				removeAllOptions('sourceVPList');
				getlbCoverage();
				
				 $("#dpSelectBoxvp option[value='']").attr("selected", "selected");
			}
			
			
			
			 
		}
		
	});
	
	$('#mvBackIP').click(function() {
		 if (accessLevel == _IP_LEVEL){
			addItemList('targetIPList','sourceIPList');	
			sortListBox("sourceIPList");
			sortListBox("targetIPList");	
			
			
		}else  if (accessLevel == _VP_LEVEL){
			addItemList('targetIPList','sourceIPList');	
			sortListBox("sourceIPList");
			sortListBox("targetIPList");		
			removeAllOptions('sourceVPList');
			//removeAllOptions('targetVpList');
			removeAllOptions('ipSelectBoxvp');
			copyAllObjectFormOnetoAnother('sourceDPList','dpSelectBoxvp');
			
		}
	});
		 
	 $('#mvFrU').click(function() {
		addItemList('sourceUList','targetUList');	
		sortListBox("sourceUList");
		sortListBox("targetUList");	
		
	});
	 
	 $('#mvBackU').click(function() {
			addItemList('targetUList','sourceUList');	
			sortListBox("sourceUList");
			sortListBox("targetUList");	
			
	 });
	 
	 $('#mvFrVP').click(function() {
			addItemList('sourceVPList','targetVpList');	
			sortListBox("sourceVPList");
			sortListBox("targetVpList");	
			
		});
		 
		 $('#mvBackVP').click(function() {
				addItemList('targetVpList','sourceVPList');	
				sortListBox("sourceVPList");
				sortListBox("targetVpList");	
				
		 });
	
	
	/* $('#mvFrLocalBodyEntity').click(function() {
		addItemList('ddLocalBodyEntity','ddTargetLocalBodyEntity');
		sortListBox("ddLocalBodyEntity");
		sortListBox("ddTargetLocalBodyEntity"); 
	});
	
	$('#mvBackLocalBodyEntity').click(function() {
		addItemList('ddTargetLocalBodyEntity','ddLocalBodyEntity');
		sortListBox("ddLocalBodyEntity");
		sortListBox("ddTargetLocalBodyEntity"); 
	}); */
	
	
};

removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};



addItemList=function(selListId, coverageListId) {
$('#'+selListId+'> option:selected').appendTo('#'+coverageListId); 
};

copyAllObjectFormOnetoAnother=function(copyId,pasteId){
	 $('#'+pasteId).empty();
	 $("#"+pasteId).append('<option value=0>Select</option>');
	$('#'+copyId+' option').clone().appendTo('#'+pasteId);
};



getCoverageSubDistrictList=function(){
	var  distArray = [];
	var subdistrictArray = [];
	$('#ddTargetDistrict option').each(function() { distArray.push($(this).val());});
	$('#ddTargetDistrictSDLvl option').each(function() { subdistrictArray.push($(this).val());});
	var distCodes=distArray.toString()+",0";
	var subdistrictCodes=subdistrictArray.toString()+",0";
	  lgdDwrSubDistrictService.getSubDistrictListbyCreteria(distCodes, subdistrictCodes,null, {
		callback : function(result) {
			if(result!=null){
			removeAllOptions('ddTargetDistrictSDLvl');
			var options = $("#ddTargetDistrictSDLvl");
			$.each(result, function(key, obj) {
				var option = $("<option/>");
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
					options.append(option);
				} else {
					if(existingSubdistrictString.indexOf(obj.subdistrictCode)>0){
						$(option).attr('disabled', 'disabled');
					}
					$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
					options.append(option);
				}
			});
		}
		},
		async : true
	});		 
};

populateSubdistrictList=function(districtCode,id,selOption){
	//alert(districtCode);
	
	if(districtCode!=""){
		 if(accessLevel==_BLOCK_LEVEL){
			 id="ddBlockAtDistrictLvl";
			 removeAllOptions(id);
			 var blockArray=[];
			 $('#ddTargetBlockSDLvl option').each(function() { blockArray.push($(this).val());});
			 blockCodes=blockArray.toString()+",0";
			 lgdDwrBlockService.getBlockListbyCreteria(null, blockCodes,districtCode, {
					callback : function(result) {
						if(result!=null){
						
						if(selOption){
							$("#"+id).append('<option value=0>Select</option>');
						}
						var options = $("#"+id);
						$.each(result, function(key, obj) {
							var option = $("<option/>");
							if (obj.operation_state == 'F') {
								$(option).attr('disabled', 'disabled');
								$(option).val(obj.blockCode).text(obj.blockNameEnglish);
								options.append(option);
							} else {
								$(option).val(obj.blockCode).text(obj.blockNameEnglish);
								options.append(option);
							}
						});
					}
					},
					async : true
				});	
			 
		 }else{
			removeAllOptions(id); 
			var subdistrictArray = [];
			$('#ddTargetDistrictSDLvl option').each(function() { subdistrictArray.push($(this).val());});
			var subdistrictCodes=subdistrictArray.toString()+",0";
			 lgdDwrSubDistrictService.getSubDistrictListbyCreteria(null, subdistrictCodes,districtCode, {
					callback : function(result) {
						if(result!=null){
						
						if(selOption){
							$("#"+id).append('<option value=0>Select</option>');
						}
						var options = $("#"+id);
						$.each(result, function(key, obj) {
							var option = $("<option/>");
							if (obj.operation_state == 'F') {
								$(option).attr('disabled', 'disabled');
								$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
								options.append(option);
							} else {
								$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
								options.append(option);
							}
						});
					}
					},
					async : true
				});	
		}
	}
};

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


getCoverageVillageList=function(){
	var  distArray = [];
	var subdistrictArray = [];
	var villageArray=[];
	$('#ddTargetDistrict option').each(function() { distArray.push($(this).val());});
	$('#ddTargetDistrictSDLvl option').each(function() { subdistrictArray.push($(this).val());});
	$('#ddTargetDistrictVillLvl option').each(function() { villageArray.push($(this).val());});
	var distCodes=distArray.toString()+",0";
	var subdistrictCodes=subdistrictArray.toString()+",0";
	var villageCodes=villageArray.toString()+",0";
	lgdDwrVillageService.getVillageListbyCreteria(distCodes, subdistrictCodes,villageCodes,null, {
		callback : function(result) {
			if(result!=null){
			removeAllOptions('ddTargetDistrictVillLvl');
			var options = $("#ddTargetDistrictVillLvl");
			$.each(result, function(key, obj) {
				
				var option = $("<option/>");
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.villageCode).text(obj.villageNameEnglish);
					options.append(option);
				} else {
					if(existingVillageString.indexOf(obj.villageCode)>0){
						$(option).attr('disabled', 'disabled');
					}
					//$(option).attr('disabled', 'disabled');
					$(option).val(obj.villageCode).text(obj.villageNameEnglish);
					options.append(option);
				}
			});
		}
		},
		async : true
	});		 
};

populateVillageList=function(subDistrictCode,id){
	var villageArray = [];
	$('#ddTargetDistrictVillLvl option').each(function() { villageArray.push($(this).val());});
	var villageCodes=villageArray.toString()+",0";
	lgdDwrVillageService.getVillageListbyCreteria(null,null, villageCodes,subDistrictCode, {
			callback : function(result) {
				if(result!=null){
				removeAllOptions(id);
				var options = $("#"+id);
				$.each(result, function(key, obj) {
					var option = $("<option/>");
					if (obj.operation_state == 'F') {
						$(option).attr('disabled', 'disabled');
						$(option).val(obj.villageCode).text(obj.villageNameEnglish);
						options.append(option);
					} else {
						$(option).val(obj.villageCode).text(obj.villageNameEnglish);
						options.append(option);
					}
				});
			}
			},
			async : true
		});		 
};

checkSubDistFullCovrage = function() {
	if ($("#SubDistrictChkFull").is(':checked')) {
		$("#tblDistrictSelectBox").show();
		$("#tblSubdistrictSpecific").show();
		$("#ddTargetDistrictSDLvl").prop('disabled', false);
	}
	if (!($("#SubDistrictChkFull").is(':checked'))) {
		$("#tblDistrictSelectBox").hide();
		$("#tblSubdistrictSpecific").hide();
		$("#ddTargetDistrictSDLvl").prop('disabled', true);
		
	}

};


checkSubDistPartialCovrage = function() {
	if ($("#SubDistrictChkPart").is(':checked')) {
		$("#tblDisttSubDisttVillLvlSelectBox").show();
		$("#tblVillageSpecific").show();
		$("#ddTargetDistrictVillLvl").prop('disabled', false);
	}
	if (!($("#SubDistrictChkPart").is(':checked'))) {

		$("#tblDisttSubDisttVillLvlSelectBox").hide();
		$("#tblVillageSpecific").hide();
		$("#ddTargetDistrictVillLvl").prop('disabled', true);
		

	}
};

toggle2 = function() {
	if ($("#districtChkFull").is(':checked')) {
		$("#tblDistrictSpecific").show();
		$("#ddTargetDistrict").prop('disabled', false);
	}
	if (!($("#districtChkFull").is(':checked'))) {
		$("#tblDistrictSpecific").hide();
		$("#ddTargetDistrict").prop('disabled', true);
		
	}
};

toggle3 = function() {
	if ($("#DistrictChkPart").is(':checked')) {
		$("#tblDistrictSelectBox").show();
		
		$("#ddTargetDistrictSDLvl").prop('disabled', false);
		if(accessLevel==_BLOCK_LEVEL){
			$("#tblBlockSpecific").show(); 
		}else if(accessLevel=_SUBDISTRICT_LEVEL){
			$("#tblSubdistrictSpecific").show();
		}
	}else if (!($("#DistrictChkPart").is(':checked'))) {
		$("#tblDistrictSelectBox").hide();
		$("#ddTargetDistrictSDLvl").prop('disabled', true);
		if(accessLevel==_BLOCK_LEVEL){
			$("#tblBlockSpecific").hide(); 
		}else if(accessLevel=_SUBDISTRICT_LEVEL){
			$("#tblSubdistrictSpecific").hide();
		}
	}
};

hideAllOption=function(){
	if (accessLevel == _VILLAGE_LEVEL){
		$("#tblDistrictSpecific").hide(); 
		$("#tblDistrictSelectBox").hide();
		$("#tblSubdistrictSpecific").hide();
		$("#tblDisttSubDisttVillLvlSelectBox").hide();
		$("#tblVillageSpecific").hide();
	}else if (accessLevel == _SUBDISTRICT_LEVEL){
		$("#tblDistrictSpecific").hide(); 
		$("#tblDistrictSelectBox").hide();
		$("#tblSubdistrictSpecific").hide();
	}else if(accessLevel==_BLOCK_LEVEL){
		
		$("#tblDistrictSpecific").hide(); 
		$("#tblDistrictSelectBox").hide(); 
		$("#tblBlockSpecific").hide(); 
	}
	
};

getCoverageBlockList=function(){
	
	var  distArray = []; 
	var blockArray=[];
	$('#ddTargetDistrict option').each(function() { distArray.push($(this).val());});
	$('#ddTargetBlockSDLvl option').each(function() { blockArray.push($(this).val());});
	var blockCodes=blockArray.toString()+",0";
	var distCodes=distArray.toString()+",0";
	lgdDwrBlockService.getBlockListbyCreteria(distCodes, blockCodes,null, {
		callback : function(result) {
			if(result!=null){
			removeAllOptions("ddTargetBlockSDLvl");
			var options = $("#ddTargetBlockSDLvl");
			$.each(result, function(key, obj) {
				var option = $("<option/>");
				if (obj.operation_state == 'F') {
					$(option).attr('disabled', 'disabled');
					$(option).val(obj.blockCode).text(obj.blockNameEnglish);
					options.append(option);
				} else {
					if(existingBlockString.indexOf(obj.blockCode)>0){
						$(option).attr('disabled', 'disabled');
					}
					$(option).val(obj.blockCode).text(obj.blockNameEnglish);
					options.append(option);
				}
			});
		}
		},
		async : true
	});	
	
};

saveOrganizationUnitForm=function(){
	
	if ($("#rdSpecificStateDis").is(':checked')) {	
		if(accessLevel==_ADMINISTRATIVE_LEVEL ){
		$("#ddTargetAdminEnitiy option").prop("selected",true);
		}else if(accessLevel==_DISTRICT_LEVEL ){
			$("#ddTargetDistrict option").prop("selected",true);
		}else if(accessLevel==_SUBDISTRICT_LEVEL ){
					if( $('#ddTargetDistrict').length ){
						$("#ddTargetDistrict option").prop("selected",true);
					}
					if( $('#ddTargetDistrictSDLvl').length ){
						$("#ddTargetDistrictSDLvl option").prop("selected",true);
					}
					
		}else if(accessLevel==_BLOCK_LEVEL ){
			if( $('#ddTargetDistrict').length ){
				$("#ddTargetDistrict option").prop("selected",true);
			}
			if( $('#ddTargetBlockSDLvl').length ){
				$("#ddTargetBlockSDLvl option").prop("selected",true);
			}
			
		
		}else if(accessLevel==_VILLAGE_LEVEL ){
			if( $('#ddTargetDistrict').length ){
				$("#ddTargetDistrict option").prop("selected",true);
			}
			if( $('#ddTargetDistrictSDLvl').length ){
				$("#ddTargetDistrictSDLvl option").prop("selected",true);
			}
			if( $('#ddTargetDistrictVillLvl').length ){
				$("#ddTargetDistrictVillLvl option").prop("selected",true);
			}
		}
		
		else if(accessLevel==_DP_LEVEL ){
			$("#targetDPList option").prop("selected",true);
		}else if(accessLevel==_IP_LEVEL ){
					if( $('#targetDPList').length ){
						$("#targetDPList option").prop("selected",true);
					}
					if( $('#targetIPList').length ){
						$("#targetIPList option").prop("selected",true);
					}
					
		}else if(accessLevel==_VP_LEVEL ){
			if( $('#targetDPList').length ){
				$("#targetDPList option").prop("selected",true);
			}
			if( $('#targetIPList').length ){
				$("#targetIPList option").prop("selected",true);
			}
			if( $('#targetVpList').length ){
				$("#targetVpList option").prop("selected",true);
			}
		}
	
		else if(accessLevel==_U_LEVEL ){
		$("#targetUList option").prop("selected",true);
			
		}
	
	
	
	}
	
	if (globalCoveageType == "F") {
		
		 $("#model-confirm").modal('show');
		 $("#title").text('<spring:message htmlEscape="true" code="Label.extendOrganisationUnits"></spring:message>');
		 $("#customAlertbody").text("You Can't Change Full to part Department Coverage");
		
		
		/* $("#cAlert").html("You Can't Change Full to part Department Coverage");
		$("#cAlert").dialog({
			title : '<spring:message htmlEscape="true" code="Label.extendOrganisationUnits"></spring:message>',
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
	}else{
		document.forms['extendOrgUnitsForm'].submit();
	}
	
	
	return true;
};

createExistingEntityList = function(AdminUnitString,Type) {
	if(Type=='V'){
		existingVillageString=AdminUnitString;
	}else if(Type=='T'){
		existingSubdistrictString=AdminUnitString;
	}else if(Type=='B'){
		existingBlockString=AdminUnitString;
	}
	
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
};

getlbCoverage=function(){
	var ipMap =new Map();
	var vpMap =new Map();
	var dpArray = []; 
	var ipArray=[];
	var vpArray = []; 
	var id='targetIPList';
	$('#targetDPList option').each(function() { dpArray.push($(this).val());});
	$('#targetIPList option').each(function() { 
		ipArray.push($(this).val());
		   if ($(this).prop("disabled") == true) {
			   ipMap.set($(this).val(),$(this).val());	
		   }
	});
	lgdAdminDepatmentDwr.getlbListbyCreteria(dpArray.toString(),ipArray.toString(),null,_IP_LEVEL, {
		callback : function(result){
			removeAllOptions('targetIPList')
			var options = $("#targetIPList");
			
			jQuery.each(result, function(index, obj) {
				var optionText = obj.localBodyNameEnglish;
				var option = $("<option />");
				
				if(ipMap.has(obj.localBodyCode)){
					$(option).attr('disabled', 'disabled');
				}
				option.val(obj.localBodyCode).text(optionText);
				options.append(option);
			});
		},
		async : true
	});	
	
	if(accessLevel == _VP_LEVEL){
	$('#targetVpList option').each(function() { 
		vpArray.push($(this).val());
		if ($(this).prop("disabled") == true) {
			   vpMap.set($(this).val(),$(this).val());	
		   }
	});
	lgdAdminDepatmentDwr.getlbListbyCreteria(dpArray.toString(),ipArray.toString(),vpArray.toString(),_VP_LEVEL, {
		callback : function(result){
			removeAllOptions('targetVpList')
			var options = $("#targetVpList");
			
			jQuery.each(result, function(index, obj) {
				var optionText = obj.localBodyNameEnglish;
				var option = $("<option />");
				if(vpMap.has(obj.localBodyCode)){
					$(option).attr('disabled', 'disabled');
				}
				option.val(obj.localBodyCode).text(optionText);
			
				options.append(option);
			});
		},
		async : true
	});	
	}
	
	
	
	
};



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
		
		$("#tblIPSelectBox").show();
		$("#tblVPSpecific").show();
	}else{
		$("#tblDPSelectBoxVP").hide();
		$("#tblIPSelectBox").hide();
		$("#tblVPSpecific").hide();
		clearList(level);
	}
	
	
	
	 
};

populateDistrictList=function(){
	_options_list = [];
	 $('#targetDPList option').each(function() { 
		  _options_list.push($(this).val());
	  });
	 getDistrictPanchayatList(_options_list.toString());
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

copysourceListtoTargetList=function(copyId,pasteId){
	$('#'+copyId+' option').clone().appendTo('#'+pasteId);
};


</script>