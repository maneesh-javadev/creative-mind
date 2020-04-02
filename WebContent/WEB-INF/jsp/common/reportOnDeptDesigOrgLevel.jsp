<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
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
	function handleMinistrydetail(data) {
		var fieldId = 'ministrycat';
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
	function getDepartmentDetailss(id) {
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
	}
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
</script>

</head>

			<body onload="showCentreORState();getOrganizationMinistryList();">
				<div id="frmcontent">
				<div class="frmhd">
					<h3 class="subtitle"></h3>
										 <ul id="showhelp" class="listing">
					 				        <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
											</li>
					 				       <%-- this link is not working <li>
					 				         <a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
					 				        </li> --%>
					 			        </ul>
			
		   	 </div>
					<div class="clear"></div>
	                                       <div class="frmpnlbrdr">
	
             	         <form:form commandName="viewDeptforadmin" action="viewDepartmentDesignationReport.do" method="POST" name="OrgUnitForm" class="form-horizontal">
		                            <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewDepartmentDesignationReport.do"/>"/>
	                                <input type='hidden' id="orgLevel" name="orgLevel" path="orgLevel"/>
	                                 <input type='hidden' id="orgztn" name="orgztn" path="orgztn"/>
						   	<div id="cat">	
								<div class="frmpnlbg" id='showbystatelinelevel' >
									<div class="frmtxt">
							   			   <div class="frmhdtitle"></div>
										   <div class="block">
											    <ul class="blocklist">
										        	<li>
											            <form:radiobutton path="radioUser" id="radioUser" name="radioUser" value="1"  checked="checked" class="frmfield" onclick="stateList();showCentreORState();" onfocus="" onblur="hideHelp('helpDiv')"/>
											            <label><spring:message code="Label.STATELEVEL"></spring:message></label>
														<form:radiobutton path="radioUser" id="radioUser" name="radioUser" value="0" checked="checked" class="frmfield" onclick="showCentreORState();getOrganizationMinistryList();" onfocus="" onblur="hideHelp('helpDiv')"/>
														<label><spring:message code="Label.CENTRELEVEL" text="Center"></spring:message></label> <br/>
												     </li>
										        </ul>
								                <ul class="blocklist">	
												    <li id="Statelabel">
												       <label for="statelabel"><spring:message code="Label.SELECTSTATE"></spring:message> <font color="#FF0000">*</font></label>
												       <br/>
												         <esapi:encodeForHTMLAttribute>
														 <form:select name="statelabel" path="code" id="statelabel" onchange="getAdminUnitDepartmentDetailsOrganise(this.value);clearMessage(this.id);" class="combofield">
																<option value="0"><spring:message code="common.select" text="Select"/></option>
														 </form:select>
														 </esapi:encodeForHTMLAttribute>
														<div id="err_statelabel"class="errormsg label"></div>
													</li>
												<li id="tdMinistry">
							     					<label for="tdMinistry" ><spring:message htmlEscape="true"  code="Label.SELMIN"></spring:message></label><span class="errormsg">*</span><br />
								     				<esapi:encodeForHTMLAttribute>
								     				<form:select path="ministryCode" class="combofield" id="ministrycat" onchange="getDepartmentDetailss(this.value);clearMessage(this.id);">
										     				<form:option value="0">
										     					<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
										     				</form:option>
													    	<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
								     			    </form:select>
								     			    </esapi:encodeForHTMLAttribute>
								     			    <div id="err_minlabel" class="errormsg label"></div>
							     			   </li>
												<li id="tdMinDepartment">
												<label for="tdMinDepartment2"><spring:message htmlEscape="true" code="Label.SELORG"></spring:message></label>
												<span class="errormsg">*</span><br />
												<esapi:encodeForHTMLAttribute> 
												<form:select path="orgUnitCode" id="tdMinDepartmentt" onchange="getAdminUnitDepartmentDetailsOrganizeationer(this.value);clearMessage(this.id);" class="combofield">
														<form:option value="0">
															<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
														</form:option>
												</form:select>
												</esapi:encodeForHTMLAttribute>
												<div id="err_MinDptlabel" class="errormsg label"></div>
												</li>
													<li id="tdDepartment">
														<label><spring:message htmlEscape="true" code="Label.SELORG"></spring:message></label>
														<span class="errormsg">*</span><br />
														<esapi:encodeForHTMLAttribute> 
														<form:select path="orgCode" class="combofield" id="depatment" onchange="getAdminUnitDepartmentDetailsOrganizeationer(this.value);clearMessage(this.id);">
															<form:option value="">
																<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
															</form:option>
															<form:options items="${orgList}" itemValue="orgCode" itemLabel="orgName"></form:options>
														</form:select>
														</esapi:encodeForHTMLAttribute>
														<div id="err_deptlabel" class="errormsg label"></div>
													</li>
													<li>
													<label for="adminUnitLevelName">
														<spring:message htmlEscape="true" code="Label.SELORGLEVEL"></spring:message></label>
														<span class="errormsg">*</span><br /> 
														<esapi:encodeForHTMLAttribute>
														<form:select path="unitLvlDept" id="adminUnitLevelName" onchange="getOrganizationParent(this.value);clearMessage(this.id);" class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true" code="Label.SELECT"></spring:message>
															</form:option>
														</form:select>
														</esapi:encodeForHTMLAttribute>
														<div id="err_unitLvlClabel" class="errormsg label"></div>
													</li>
													</ul>     
							 		     	</div>
											<div class="block">
												<ul class="captcha_fields">	
												<div class="form-group">
													<label class="col-sm-3 control-label"></label>
													  <div class="col-sm-6">
															<img alt="Captcha Image" src="captchaImage"></br>
															<label>Enter CAPTCHA image code as shown above</label><span class="errormsg">*</span></br>
															<form:input id="captchaAnswer" type="text" path="captchaAnswer" onblur="clearMessage(this.id);" name="captchaAnswer"/>
														    <div id="captchaErrormsg" class="errormsg"><form:errors path="captchaAnswer"/></div>
													   </div>
													</div> 
													 					
											   </ul>
										    </div>
											<div>
												<input type="button" name="Submit" class="bttn" onclick="validateFormData();"  value=<spring:message htmlEscape="true"  code="Get Data "></spring:message>/> 
										    </div>
									
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</body>
</html>