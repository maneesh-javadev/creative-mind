<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<script src="js/setParentOrgUnitCenter.js"></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type="text/javascript" language="javascript">	dwr.engine.setActiveReverseAjax(true);</script>


<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/js/bootstrap-dialog.min.js"></script>

<script>

function emptyElements() {
try{
	BootstrapDialog.show({
        title: 'Set Parent Org Unit ?',
        message: "All the details entered in the form would be cleared. Do you still wish to continue?",
        buttons: [{
            label: 'Yes',
            action: function(dialog) {
           	 $('#topLevelEntityType').attr('selectedIndex', 0);
				$('#orgCode').find('option:gt(0)').remove();
				$('#orgType').find('option:gt(0)').remove();
				$('#orgLevel').find('option:gt(0)').remove();
				$('#orgDeptType').find('option:gt(0)').remove();
				$('#orgLevelDept').find('option:gt(0)').remove();
				$("#parentLevelOrg").empty();
				$("#sourceOrgList").empty();
				$("#contributedOrgist").empty();
   				 dialog.close();
            }
        }, {
            label: 'NO',
            action: function(dialog) {
           	 dialog.close();
            }
        }]
    });
	
	
}catch(err) {
    alert(err.message);
}


}

function ValidateAndSubmitforEntity() {
		
try{	
	document.getElementById("topLevelType").innerHTML = "";
	document.getElementById("parentAdminOrg").innerHTML = "";
	document.getElementById("parentAdminOrgLevel").innerHTML = "";
	document.getElementById("parentOrganization").innerHTML = "";
	document.getElementById("childOrganization").innerHTML = "";
	var orgtypeIndex = document.getElementById('topLevelEntityType').selectedIndex;
	var orgindex = document.getElementById('orgCode').selectedIndex;
	var typeindex = document.getElementById('orgType').selectedIndex;
	var count = 0;
	var status = true;
	if (orgtypeIndex == 0) {
		document.getElementById("topLevelType").innerHTML = "<font color='red'>Please Select Organization Type </font>";
		return false;

	} else if (orgindex == 0) {
		document.getElementById("parentAdminOrg").innerHTML = "<font color='red'>Please Select a Ministry</font>";
		return false;

	} else if (typeindex == 0) {
		document.getElementById("parentAdminOrgLevel").innerHTML = "<font color='red'>Please Select a Department</font>";
		return false;

	}
	var selObj = document.getElementById("parentLevelOrg");
	for ( var i = 0; i < selObj.options.length; i++) {
		if (selObj.options[i].selected == true)
			count++;
	}
	if (count == 0) {

		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please select a parent organization unit to view its child organization units</font>";
		return false;

	} else if (count > 1) {
		document.getElementById("parentOrganization").innerHTML = "<font color='red'>Please Select Single Parent</font>";
		return false;

	}
	selObj = document.getElementById("contributedOrgist");
	if (selObj.options.length == 0) {
		document.getElementById("childOrganization").innerHTML = "<font color='red'>Please select child organization units to be mapped with parent organization unit</font>";
		return false;

	}
	// $("#childOrgCode").val()=
	var temp = '';
	var selObj = document.getElementById('contributedOrgist');
	for ( var i = 0; i < selObj.options.length; i++) {
		temp = selObj.options[i].value + "," + temp;
	}
	temp = temp.substring(0, temp.length - 1);
	$("#childOrgCode").val(temp);
	$("#parentOrgCode").val($("#parentLevelOrg").val());
	if (status) {
		
		 BootstrapDialog.show({
	         title: 'Set Parent Org Unit ?',
	         message: "The selected	mapping would be saved. Do you wish to continue?",
	         buttons: [{
	             label: 'Yes',
	             action: function(dialog) {
	            	 	document.forms['form1'].method = "post";
						document.forms['form1'].action = "setOrgParentChilds.htm?<csrf:token uri='setOrgParentChilds.htm'/>";
						document.forms['form1'].submit();
	    				 dialog.close();
	             }
	         }, {
	             label: 'NO',
	             action: function(dialog) {
	            	 dialog.close();
	             }
	         }]
	     });

	}
	
}catch(err) {
    alert(err.message);
}
}
</script>
</head>
<body>
<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                  <form:form action="setParentOrgUnitCenter.htm" method="POST" id="form1" commandName="orgUnitCenterForm" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="setParentOrgUnitCenter.htm"/>" />
				     	<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				        <input type="hidden" name="parentOrgCode" id='parentOrgCode' />
                        <input type="hidden" name="childOrgCode" id='childOrgCode' />
                    <div class="box">
                    <div class="box-header with-border">
                      <h3><spring:message code="Label.ParentOrgUnit" htmlEscape="true"  text="Set Parent Org Unit"></spring:message></h3>
                    </div>
                    <div class="box-header subheading"><h4><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4></div>
                        <div class="box-body">
                          <div class="form-group">
                            	<label  class="col-sm-3 control-label" ><spring:message code="Label.SELOrganization" text="Select Organization Type" 	htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
                            	<div id="topLevelType" style="color: red;"></div>									
								  <div class="col-sm-6">	
										<form:select htmlEscape="true" path="" class="form-control" id="topLevelEntityType" onchange="getTopLevelEntityByType(this.value)" >
												<form:option value="0" htmlEscape="true">
													<spring:message code="Label.SELECT" text="-slect"></spring:message>
												</form:option>
												<form:option value="2" htmlEscape="true">
													<spring:message code="Label.SELECTDEP" text="DEPARTMENT"></spring:message>
												</form:option>
												<form:option value="3" htmlEscape="true">
													<spring:message code="Label.SELECTORG" text="ORGANIZATION"></spring:message>
												</form:option>
											</form:select>
                                    </div>	
                          </div>
                          
                            <div style='display: none;' id='minDiv' >
                            <div class="form-group">
								  <label class="col-sm-3 control-label"><spring:message code="Label.SELOrganization" text="Select Ministry" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
											<div id="parentAdminOrg" style="color: red;"></div>
									 <div class="col-sm-6">
										<form:select htmlEscape="true" path="orgTypeCode" class="form-control" id="orgCode" onchange="getParentLevelEntity(this.value)" >
										  <form:option value="0" htmlEscape="true"> <spring:message code="Label.SELECT" text="-slect"></spring:message>
													</form:option>

									        </form:select> 
										
											<div class="errormsg"><form:errors htmlEscape="true" path="orgCode"></form:errors></div>
								    </div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message code="Label.SELOrgLevel" text="Select Department" htmlEscape="true"></spring:message> <span class="errormsg">*</span></label>
										
											<div id="parentAdminOrgLevel" style="color: red;"></div> 
											<div class="col-sm-6"> <form:select htmlEscape="true" path="orgTypeCode" class="form-control" onchange="getLevelofDepartment(this.value);" id="orgType" >
													<form:option value="0">
														<spring:message code="Label.SELECT" text="-slect -"></spring:message>
													</form:option>

												</form:select> 
										</div>
								</div>
								
						  </div>
						  
						  <div style='display: none;' id='deptDiv' class="form-group">
								 <label class="col-sm-3 control-label"><spring:message code="Label.SELOrgLevel" text="Select Level of Department" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
											<div id="parentAdminOrgLevel" style="color: red;"></div> 
								<div class="col-sm-6">
									<form:select htmlEscape="true" path="orgTypeCode" class="form-control" onchange="getparentOrganizations(this.value);" id="orgLevel">
											<form:option value="0"><spring:message code="Label.SELECT" text="-slect -"></spring:message></form:option>
                                  </form:select>  
							        </div>
							</div>
										
                        
                       <div style='display: none;' id='selOrgDiv'>
							<div class="form-group">
								<label class="col-sm-3 control-label"><spring:message  code="Label.SELOrgLevel" text="Select organisation" htmlEscape="true"></spring:message>
											 <span class="errormsg">*</span></label>
										<div id="parentAdminOrgLevel" style="color: red;"></div> 
									<div class="col-sm-6"> 
									<form:select htmlEscape="true" path="orgTypeCode" class="form-control" onchange="getLevelofOrganisation(this.value);" id="orgDeptType">
												<form:option value="0"><spring:message code="Label.SELECT" text="-slect -"></spring:message></form:option>
	                               </form:select> 
									 </div>
									 
							</div>
										
										
							<div class="form-group">
							  <label class="col-sm-3 control-label"><spring:message code="Label.SELOrgLevel" text="Select Level of Organisation" htmlEscape="true"></spring:message> <span class="errormsg">*</span></label>
											<div id="parentAdminOrgLevelofDept" style="color: red;"></div> 
								<div class="col-sm-6"> 
								<form:select htmlEscape="true" path="orgTypeCode" class="form-control" onchange="getparentOrganizations(this.value);" id="orgLevelDept" >
										<form:option value="0"><spring:message code="Label.SELECT" text="-slect -"></spring:message></form:option>
                               </form:select> 
								 </div>
							</div>

                         </div>
                         
           <div style='display: none;' id='commonDiv'>
                         
               <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message code="Label.SELECTPARENTLEVELORG" text="Select Organization Unit"></spring:message><span class="errormsg">*</span><div id="parentOrganization" style="color: red;"></div>  </label>
		              <div id='test' >
						<select id='parentLevelOrg' name="parentLevelOrg" class="form-control" size="7"   onchange="clearOrgsUnitsData();">
                            </select>
                            <div class="errormsg"></div>
							<span class="errormsg" id="parentOrganization_error"> </span>
												</div>
		        </div>
					 <div class="ms_buttons col-sm-2">
					 	<button name="button2" class="btn btn-primary btn-xs btn-block" type="button" style="width:300px; margin-top: 50%; margin-left: 80%" onclick="getChildOrganizations()" >Get all child level Organization Units</button>
					 </div>
			<div class="ms_selection col-sm-5">
						
            </div>
         </div>
         
            <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message code="Label.SELECTPARENTLEVELORG" text="Select Child Level Organization "></spring:message>
		               <spring:message htmlEscape="true" code="Label.AVAILABLECDHILDORG" text="Available Child level Organization Units"></spring:message></label>
		               <div id="childOrganization" style="color: red;"></div>
		               
		              <select multiple="multiple" id='sourceOrgList' name="sourceOrgList" 	class="form-control"> </select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button id="src2Target1" class="btn btn-primary btn-xs btn-block" type="button"  onclick="addOrgUnits('contributedOrgist','sourceOrgList');"	 > &gt;&gt;</button>
						<button id="target2Src2" class="btn btn-primary btn-xs btn-block" type="button"  onclick="removeOrgUnits('contributedOrgist', 'sourceOrgList');" > &lt;&lt;</button>
						<button id="target2Src2" class="btn btn-primary btn-xs btn-block"  type="button"  onclick="getSpecificChildOrganizations();" value="&lt;&lt;" >Shift suitable Child Org Units</button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true" code="Label.CONTRIBUTECDHILDORG" text="Selected Child level Organization Units"></spring:message></label> 
			   <select id='contributedOrgist' multiple="multiple" name="contributedOrgist" class="form-control"> </select>	
		     </div>				
            </div>
         </div>
                         
        </div>
      </div>
     <div class="box-footer">
		    <div class="col-sm-offset-2 col-sm-10">
		        <div class="pull-right">
					 <button type="button" class="btn btn-success" name="Submit" onclick="ValidateAndSubmitforEntity()" id="submit1" ><spring:message code="Button.SAVEMappin" text="Save Mapping"  htmlEscape="true"  ></spring:message></button>
				      <button type="button" class="btn btn-warning" id="Submit6" onclick="emptyElements()" ><spring:message code="Button.CLEAR"></spring:message></button>
					  <button type="button" class=" btn btn-danger"onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><spring:message code="Button.CLOSE"/></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
    
                    </form:form>
             
                    </section>
                </div>
            </section>

</body>
</html>