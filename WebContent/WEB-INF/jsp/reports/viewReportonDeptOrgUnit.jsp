<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/extendDepartment.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<!-- jquery pagination  -->
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ministry.js"></script>

<script type="text/javascript">
 $(document).ready(function() {
	  $(window).keydown(function(event){
	    if(event.keyCode == 13) {
	    	if(validateFormData()){
	    		return true;
	    	}else{
	    		 event.preventDefault();
	   	      return false;
	    	}
	    	
	     
	    }
	  });
	}); 



	function stateList() {
		lgdDwrStateService.getAllStates({
			callback : getstaetForCenterCallBack,
			errorHandler:handleDistrictErrorExtend
		});
	}
	function getLocalGovtBodyTypeCallBack(data) {
		dwr.util.removeAllOptions('lgType');
		dwr.util.addOptions('lgType', strSelect, 'optValue', 'optText');
		dwr.util.addOptions('lgType', data, 'lbTypeCode', 'lbTypeName');
	}
	function getstaetForCenterCallBack(data) {
		var fieldId = 'statelabel';
		var valueText = 'stateCode';
		var nameText = 'stateNameEnglish';
		dwr.util.removeAllOptions('statelabel');
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
	function showCentreORState() {
		var ulbRadioButton = $('input[name=radioUser]:checked').val();
		if (ulbRadioButton == 0) {
			$('#Statelabel').hide();
			$('#tdMinistry').show();
			$('#tdMinDepartment').show();
			$('#tdDepartment').hide();
		} else if (ulbRadioButton == 1) {
			$('#tdDepartment').show();
			$('#Statelabel').show();
			$('#tdMinistry').hide();
			$('#tdMinDepartment').hide();
		}
	}
	function handleDepartmentdetail(data) {
		var fieldId = 'category';
		var valueText = 'orgCode';
		var nameText = 'orgName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
	function getOrganizationMinistryList() {
		var stateCode = 0;
		lgdDwrOrganizationService.getOrganizationDetailbystateCode(stateCode, {
			callback : handleMinistrydetail,
			errorHandler : handleDistrictErrorExtend
		});
	}
	
	
 function getOrganizationList() {
		lgdDwrOrganizationService.getOrganizationDetails({
			callback : handleOrganizationdetail,
			errorHandler : handleDistrictErrorExtend
		});
	} 
	

	
	function handleMinistrydetail(data) {
		var fieldId = 'tdMinDepartmentt';
		var valueText = 'orgCode';
		var nameText = 'orgName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	} 
	function getAdminUnitDepartmentDetailsOrganise(slcCode) {
			lgdDwrOrganizationService.getOrganizationDetailbySlcCode(slcCode, {
				callback : handlerforDepartmentdetail1,
				errorHandler : handleDistrictErrorExtend
			});	
	}
	function handlerforDepartmentdetail1(data) {
		var fieldId = 'depatment';
		var valueText = 'orgCode';
		var nameText = 'orgName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
	function getAdminUnitDepartmentDetailsOrganizeationer(orgCode) {
			lgdDwrOrganizationService.getAdministrativeUnitLevelDeptByOrgCode(orgCode, {
				callback : handlerforDepartmentlevels,
				errorHandler : handleDistrictErrorExtend
			});	
	}
	function handlerforDepartmentlevels(data) {
		var fieldId = 'adminUnitLevelName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			select : "Select"
		} ];
		 var dataq = [ {
			select : "All"
		} ]; 
		dwr.util.addOptions(fieldId, dataq, "select");
		var options = $("#adminUnitLevelName");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			var pageAccess = "";
			if (obj.locatedAtLevel == "1") {
				pageAccess = "S";
			} else if (obj.locatedAtLevel == "2") {
				pageAccess = "D";
			} else if (obj.locatedAtLevel == "3") {
				pageAccess = "T";
			} else if (obj.locatedAtLevel == "4") {
				pageAccess = "V";
			} else if (obj.locatedAtLevel == "5") {
				pageAccess = "B";
			} else {
				pageAccess = "A";
			}
			var accessMap = obj.locatedAtLevel + "|" + pageAccess;
			$(option).val(obj.orglocatedlevelcode).text(obj.unitlevelnameenglish);
			options.append(option);
		});
	}
	/* function getDepartmentDetailss(id) {
		lgdDwrOrganizationService.getDepartmentDetails(id, {
			callback : handleMinDepartmentdetail,
			errorHandler : handleDistrictErrorExtend
		});
	}
	function handleMinDepartmentdetail(data) {
		var fieldId = 'tdMinDepartmentt';
		var valueText = 'orgCode';
		var nameText = 'orgName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	} */
	function clearMessage(id) {
		if(id="ministrycat") {
			$("#err_minlabel").hide();	
		}
		if(id="tdMinDepartmentt") {
			$("#err_MinDptlabel").hide();	
		}
		if(id="adminUnitLevelName") {
			$("#err_unitLvlClabel").hide();	
		}
		if(id="statelabel") {
			$("#err_statelabel").hide();	
		}
		if(id="depatment") {
			$("#err_deptlabel").hide();	
		}
		if(id="captchaAnswer") {
			$("#captchaErrormsg").hide();	
		}
		
	}
	function validateFormData() {
		var radioUser = $("input[name='radioUser']:checked").val();
		$(".errormsg").hide();
		if(radioUser == 0) {
			if($("#ministrycat").val()=="Select") {
				$("#err_minlabel").html("Please select ministry name !");
				$("#err_minlabel").show();
				return false;
			}
			if($("#tdMinDepartmentt").val()=="Select") {
				$("#err_MinDptlabel").html("Please select ministry department !");
				$("#err_MinDptlabel").show();
				return false;
			}
			if($("#adminUnitLevelName").val()=="Select") {
				$("#err_unitLvlClabel").html("Please select department level !");
				$("#err_unitLvlClabel").show();
				return false;
			}
			if($("#captchaAnswer").val()=="") {
				$("#captchaErrormsg").html("Please enter captcha code !");
				$("#captchaErrormsg").show();
				return false;
			}
			
			/* append to get organization start----------*/
			var org = $("#tdMinDepartmentt :selected").text();
			$("#orgztn").val(org);
			/* -----------------end------------------*/
			/* append to get organization level start-----*/
				var orgLvl = $("#adminUnitLevelName :selected").text();
				$("#orgLevel").val(orgLvl);
			/* -----------------end------------------*/
		
			document.OrgUnitForm.submit();
			return true;
		} 
		if(radioUser == 1) {
			$(".errormsg").hide();
			if($("#statelabel").val()=="Select") {
				$("#err_statelabel").html("Please select a state !");
				$("#err_statelabel").show();
				return false;
			}
			if($("#depatment").val()=="Select") {
				$("#err_deptlabel").html("Please select state department !");
				$("#err_deptlabel").show();
				return false;
			}
			if($("#adminUnitLevelName").val()=="Select") {
				$("#err_unitLvlClabel").html("Please select department level !");
				$("#err_unitLvlClabel").show();
				return false;
			}
			if($("#captchaAnswer").val()=="") {
				$("#captchaErrormsg").html("Please enter captcha code !");
				$("#captchaErrormsg").show();
				return false;
			}
			
			/* append to get organization start----------*/
			var org = $("#depatment :selected").text();
			$("#orgztn").val(org);
			/* -----------------end------------------*/
			/* append to get organization level start-----*/
				var orgLvl = $("#adminUnitLevelName :selected").text();
				$("#orgLevel").val(orgLvl);
			/* -----------------end------------------*/
		
			document.OrgUnitForm.submit();
			return true;
		} 
	}
	
	function handleOrganizationdetail(data) {
		var fieldId = 'tdMinDepartmentt';
		var valueText = 'orgCode';
		var nameText = 'orgName';
		dwr.util.removeAllOptions(fieldId);
		var dataq = [ {
			select : "Select"
		} ];
		dwr.util.addOptions(fieldId, dataq, "select");
		dwr.util.addOptions(fieldId, data, valueText, nameText);
	}
</script>
</head>
<body onload="showCentreORState() ;getOrganizationMinistryList() ; ">
	
<section class="content">
<!-- Main row -->
 <div class="row"  id="frmcontent">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message htmlEscape="true"  code="" text="Organization Department Report"></spring:message></h3>
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" commandName="viewDeptforadmin" action="viewOrganizationUnitReport.do" method="POST" name="OrgUnitForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewOrganizationUnitReport.do"/>"/>
                <input type='hidden' id="orgLevel" name="orgLevel" path="orgLevel"/>
                <input type='hidden' id="orgztn" name="orgztn" path="orgztn"/>
				<div id="cat">
				  <div class="box-header subheading" id='showbystatelinelevel'><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
						
					<div class="form-group">
						<label class="col-sm-4 control-label"></label>
						 <div class="col-sm-6">
							<form:radiobutton path="radioUser" id="radioUser" name="radioUser" value="1"  checked="checked" onclick="stateList();showCentreORState();" onfocus="" onblur="hideHelp('helpDiv')"/>
				            <label><spring:message code="Label.STATELEVEL"></spring:message></label>
							<form:radiobutton path="radioUser" id="radioUser" name="radioUser" value="0" checked="checked" onclick="showCentreORState();getOrganizationMinistryList();" onfocus="" onblur="hideHelp('helpDiv')"/>
							<label><spring:message code="Label.CENTRELEVEL" text="Center"></spring:message></label> <br/>
					 	</div>
					</div>
					
					<div class="form-group" id="Statelabel">
						<label class="col-sm-4 control-label"><spring:message code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
						 <div class="col-sm-6">
							 <form:select name="statelabel" path="code" class="form-control" id="statelabel" onchange="getAdminUnitDepartmentDetailsOrganise(this.value);clearMessage(this.id);">
								<option value="0"><spring:message code="common.select" text="Select"/></option>
							 </form:select>
							 <div id="err_statelabel"class="mandatory"></div>
					 	</div>
					</div>
					
					<%-- <div class="form-group" id="tdMinistry">
						<label class="col-sm-4 control-label"><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message><span class="mandatory">*</span></label>
						 <div class="col-sm-6">
							<form:select path="ministryCode" class="form-control" id="ministrycat" onchange="getDepartmentDetailss(this.value);clearMessage(this.id);">
			     				<form:option value="0">
			     					<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
			     				</form:option>
						    	<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
		     			    </form:select>
							<div id="err_minlabel" class="mandatory"></div>
						</div>
					</div> --%>
				
				
				
				
					<div class="form-group" id="tdMinDepartment">
						<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELORG"></spring:message><span class="mandatory">*</span></label>
						 <div class="col-sm-6">
							<form:select path="orgUnitCode" id="tdMinDepartmentt" class="form-control"   onchange="getAdminUnitDepartmentDetailsOrganizeationer(this.value);clearMessage(this.id);">
								<form:option value="0">
									<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
								</form:option>
							<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
								
							</form:select>
							<div id="err_MinDptlabel" class="mandatory"></div>
						</div>
					</div>
					
					<div class="form-group" id="tdDepartment">
						<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELORG"></spring:message><span class="mandatory">*</span></label>
						 <div class="col-sm-6">
							<form:select path="orgCode" class="form-control" id="depatment" onchange="getAdminUnitDepartmentDetailsOrganizeationer(this.value);clearMessage(this.id);">
								<form:option value="">
									<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
								</form:option>
								<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
							</form:select>
							<div id="err_deptlabel" class="mandatory"></div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message htmlEscape="true" code="Label.SELORGLEVEL"></spring:message><span class="mandatory">*</span></label>
						 <div class="col-sm-6">
							<form:select path="unitLvlDept" class="form-control" id="adminUnitLevelName" onchange="getOrganizationParent(this.value);clearMessage(this.id);">
								<form:option value="0">
									<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
								</form:option>
							</form:select>
							<div id="err_unitLvlClabel" class="mandatory"></div>
						</div>
					</div>
					
				    <div class="form-group">
				 	  <label for="captchaAnswer" class="col-sm-4 control-label"></label>
				     	<div class="col-sm-6">
				           <img src="captchaImage" alt="Captcha Image" id="captchaImageId"/>
				       </div>
				   </div>
				                    
				   <div class="form-group">
					 <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message" htmlEscape="true" /><span class="mandatory">*</span></label>
					    <div class="col-sm-6">
					        <form:input id="captchaAnswer" type="text" maxlength="5" class="form-control" path="captchaAnswer" onblur="clearMessage(this.id);" name="captchaAnswer"/>								
						   <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
						   <div id="captchaErrormsg" class="errormsg"><form:errors path="captchaAnswer"/></div>
					 </div>
				   </div> 
				
			<div class="box-footer">
             <div class="col-sm-offset-2 col-sm-10">
              <div class="pull-right">
				<button type="submit" name="Submit" onclick="return validateFormData();" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Get Data "></spring:message></button>
				<button type="button"  name="Submit3" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
			 </div>
			</div>
	      </div>
          </div>
         </form:form>
		</div>
	  </section>
    </div>
  </div>
</section>
				
</body>
</html>