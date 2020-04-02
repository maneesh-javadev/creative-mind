<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/add_designation.js"></script>
<script src="js/organization.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>

<script type="text/javascript" language="Javascript">
dwr.engine.setActiveReverseAjax(true);

	function getOrganisationListCount(id) {
		lgdDwrOrganizationService.getRecordsforOrganization(id, {
			callback : handleOrganisationListCountSuccess,
			errorHandler : handleOrganisationListCountError
		});
	}
	function OrgTypeName()
	{		
      if(document.getElementById("txtorgTypeName").value == "" )
		{
			document.getElementById("txtorgTypeName_error").innerHTML="Organization Type Name is Required";
			$("#txtorgTypeName_error").show();
			$("#txtorgTypeName_msg").hide();
			$("#txtorgTypeName").addClass("error_fld");
			$("#txtorgTypeName").addClass("error_msg");
			return false;

		}
		else 
		{
			$("#orgTypeName_msg").hide();
			return true;
				
		}
	}
	
    function handleOrganisationListCountSuccess(data) 
    {
    	if(data>0)
			{ 
			document.getElementById('btnSave').style.visibility = 'hidden';
			   // document.getElementById('btnSave').style.display='none';
			alert("Cannot Delete organization type as there	exists an organization of the selected type");
				
			}
		else
			{ 
			alert("Are U Sure You want to delete the selected Organization type");
			
			  var orgname=dwr.util.getText('ddorgtype');
			  document.getElementById('btnSave').style.visibility = 'visible';
			 // dwr.util.setValue('btnSave',orgname);
			} 
			
		
	}
	var isSubmit =false;
    /* function confirmDelete(){
    	alert("sure");
    	var agree=CONFIRM("Are you sure you want to delete this Organization Type?");
    	if (agree)
    	     return true ;
    	else
    	     return false ;
    	} */
    	function deleteConfirm(msg)
    	  {
    	   var confDel = msg;
    	   if(confDel ==null)
    	   confDel= confirm("Would you like to perform this delete action?");
    	else
    	  confDel= confirm(msg);
    	  
    	if (confDel== true)
    	  doSubmit('Delete');
    	  }
    
    	function doSubmit(formName,errorText)
    	{
    		if (isSubmit)
    			document.getElementById(formName).submit();
    		else
    			alert(errorText);
    	}

	function handleOrganisationListCountError() {
		// Show a popup message
		alert("No data found!");
	}
	function resetOrgTypeForm()
	{		
		document.getElementById('txtorgTypeName').value="";
	}
	
	</script>
</head>


<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadOrgTypePage();">


	<div id="frmcontent">
		<div class="frmhd"
			style="background: #E4E4E4; padding: 3px; ">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.DELETEORGTYPE"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<%--//this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
								code="Button.HELP"></spring:message> --%>
					</a></td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form   action="deleteOrganizationType.htm" method="POST" commandName="orgTypeForm">
				<div id="cat">
					<div class="frmpnlbg">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="100%">

									<div class="frmtxt">
										<div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.GENERALDETAILS"></spring:message>
										</div>
										<table width="100%" class="tbl_no_brdr">
										
										
							<tr>
								<td width="14%">&nbsp;</td>
								<td class="lblBold" align="left"><spring:message htmlEscape="true"  code="Label.OLDORGTYPENAME"></spring:message><br />
									           <form:select path="orgType" style="width: 150px" class="combofield" id="ddorgtype" onchange="getOrganisationListCount(this.value);">	
									           <form:option selected="selected" value="" label="--select--"></form:option>								           
									           <form:options items="${organizationTypeList}" itemLabel="orgType" itemValue="orgTypeCode"  />
									           </form:select> </td>
							   </tr>
							   				<tr id="tr_orgName"style="display: none">
												<td width="14%"></td>
												<td width="26%" ><spring:message htmlEscape="true"  code="Label.ORGTYPENAME"></spring:message>
													<span class="errormsg">*</span> <br /> <form:input
														id="txtorgTypeName" path="orgTypeName" maxlength="50"
														 class="frmfield" onkeypress="validateAlphanumericKeys();"  onblur="OrgTypeName()"/>							
					    	                 <span class="error" id="txtorgTypeName_error"></span>
														  <form:input path="orgTypeCode" type="hidden" class="frmfield" /></td>
												<td width="60%"><%-- <form:errors path="orgTypeName"
														class="errormsg"></form:errors> <span class="errormsg" id="txtorgTypeName_error">
														<spring:message htmlEscape="true"  code="error.blank.orgTypeName"></spring:message>
														</span>--%></td> 
											</tr> 
										</table>
									</div></td>
							</tr>
						</table>
					</div>
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="16%" rowspan="2">&nbsp;</td>
								<td width="84%" align="left">
								<label> 
								<input type="submit" name="Save" id="btnSave" value=<spring:message htmlEscape="true"  code="Button.DELETE"></spring:message> onClick="deleteConfirm();" /> </label>
									<label><input type="button" class="btn" name="Cancel" value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
					<br /> <br /> <br />
					</td>

					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					</table>
				</div>
		</div>
		</form:form>
	</div>
	</div>
</body>
</html>
