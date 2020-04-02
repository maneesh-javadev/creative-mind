<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<c:set var="orgTypeCodeDept" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()%>"></c:set>
<c:set var="orgTypeCodeOrg" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.ORGANIZTION_TYPE_CODE_ORG.toString()%>"></c:set>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
var isCenter=false;
var selOlc;

removeAllOptions=function(id){
	dwr.util.removeAllOptions(id);
};


getOrgORDeptList=function(orgTypeCode){
	removeAllOptions('level');
	removeAllOptions('OrgLocatedLevelCode');
	lgdAdminDepatmentDwr.getOrganizationbyCriteria("${stateCode}",orgTypeCode,{
		callback : function(result) {
			var dataq = [ {
				select : '<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>'
			} ];
			removeAllOptions('deptId');
			dwr.util.addOptions('deptId', dataq, "select");
			dwr.util.addOptions('deptId', result, 'olc', 'orgName');
			
		},
		async : true
	});
};

getOrgLocatedAtLevel=function(olc){
	removeAllOptions('level');
	removeAllOptions('OrgLocatedLevelCode');
	if(checkSelectValue(olc)){
		//changed BY Pranav on 15 Dec 2016 
		//to get OrgLocatedAtLevel list 
		//along with localbody list 
		
		selOlc=olc;
		lgdAdminDepatmentDwr.getExistDeptHierarchy(olc,parseInt("${stateCode}"),null, {
			callback : function(result){
				removeAllOptions('level')
				var options = $("#level");
				options.append($("<option />").val("").text("<spring:message htmlEscape='true' code='Label.SELECT'/>"));
				jQuery.each(result, function(index, obj) {
					var optionText = obj.childName;
					var option = $("<option />");
					option.val(obj.rno).text(optionText);
					options.append(option);
				});
				
				
				
			},
			async : true
		});	
	}
}; 


getOfficeNamebyLevel=function(level){
	removeAllOptions('OrgLocatedLevelCode');
	if(checkSelectValue(level)){
		//lgdAdminDepatmentDwr.getOfficeNamebyLevel(selOlc, level, {
			lgdAdminDepatmentDwr.getOfficeDetailbyParent(selOlc,null, level, {
			//lgdDwrOrganizationService.getOrganizationParent(selOlc, level, {
			callback : function(result) {
				var dataq = [ {
					select : '<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>'
				} ];
				dwr.util.addOptions('OrgLocatedLevelCode', dataq, "select");
				dwr.util.addOptions('OrgLocatedLevelCode', result, 'orgLocatedLevelCode', 'orgLevelSpecificName');
				//dwr.util.addOptions('OrgLocatedLevelCode', result, 'orgCode', 'orgName');
				
			},
			async : true
		});	
	}
};

checkSelectValue=function(value){
return(value!="select"); 	
};


submitForm=function(){
	var OrgLocatedLevelCode=$("#OrgLocatedLevelCode").val();
	if(OrgLocatedLevelCode!=null && OrgLocatedLevelCode!="Select"){
		 $('#btnGet').attr('disabled', 'disabled');
		$('#close').attr('disabled', 'disabled');
		document.forms['extendOrgUnitsForm'].method = "POST";
		document.forms['extendOrgUnitsForm'].submit();
	}else{
		
		$("#alertboxbody").html("Please select all mandatory fields.");
		$('#alertbox').modal('show');	
		
		
	
	}
};

</script>
</head>
<body>

<section class="content">
  <div class="row">
    <!-- main col -->
    <section class="col-lg-12">
		<div class="box">
	      <div class="box-header with-border">
	        <h3 class="box-title"><spring:message htmlEscape="true" code="Label.extendOrganisationUnits"></spring:message></h3>
	      </div><!-- /.box-header -->
	      
	      <form:form action="extendOrganizationUnits.htm" method="POST" id="extendOrgUnitsForm" name="extendOrgUnitsForm" class="form-horizontal">
		  <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="extendOrganizationUnits.htm"/>" />
					
			<div class="form-group">
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.selOrgType"	htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			  <div class="col-sm-6">
			  		<select id="orgTypeCode" name="orgTypeCode" onchange="getOrgORDeptList(this.value)" class="form-control" >
						<option value="0" htmlEscape="true"><spring:message code="Label.SELECT"></spring:message></option>
						<option value="${orgTypeCodeDept}" htmlEscape="true"><spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message></option>
						<option value="${orgTypeCodeOrg}" htmlEscape="true"><spring:message code="Label.SELECTORG" text="ORGANIZATION"></spring:message></option>
					</select>
			
			  </div>
			</div>
			<div class="form-group">
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.selDeptorORg" /><span class="mandatory">*</span></label>
			  <div class="col-sm-6">
			  		<select id="deptId" onchange="getOrgLocatedAtLevel(this.value)" class="form-control"  >
							<option value="select"><spring:message htmlEscape="true" code="Label.SELECT" /></option>
					</select>
			
			  </div>
			</div>	
			<div class="form-group">
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.selUnitLevels" /><span class="mandatory">*</span></label>
			  <div class="col-sm-6">
			  		<select id="level" onchange="getOfficeNamebyLevel(this.value)" class="form-control" ></select>
			
			  </div>
			</div>			
			<div class="form-group">
			  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.selOfficeName" /><span class="mandatory">*</span></label>
			  <div class="col-sm-6">
			  		<select id="OrgLocatedLevelCode" name="OrgLocatedLevelCode" class="form-control" ></select>
			  </div>
			</div>			
					
			<div class="box-footer">
    		 	<div class="col-sm-offset-2 col-sm-10">
       			<div class="pull-right">
       				<button type="button" class="btn btn-success" id="btnGet" onclick="submitForm();"><i class="fa fa-floppy-o"></i><spring:message htmlEscape="true"  code="Button.GETDATA"/></button>
       				<button type="button" class="btn btn-danger" id="close" onclick="javascript:go('home.htm');"><i class="fa fa-floppy-o"></i>Close</button>
       			</div>
     		</div>   
   			</div>
		</form:form>
			
			
		</div>
		</section>
	</div>
</section>

<div class="modal fade modal-admin" id="alertbox" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
			<div class="modal-content">
  				<div class="modal-header">
   				 
    			  	<h4 class="modal-title" id="alertboxTitle"><spring:message htmlEscape="true" code="Label.extendOrganisationUnits"></spring:message></h4>
  				</div>
  				<div class="modal-body" id="alertboxbody">
        
  				</div>
     			 <div class="modal-footer">
        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      
      			</div>
		</div>
	</div>
</div>


</body>
</html>