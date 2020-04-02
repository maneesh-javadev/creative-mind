<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script src="js/createDepartment.js"></script>
<script src="js/extendDepartment.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript">
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>

<script src="js/common.js"></script>
<script type="text/javascript" language="javascript">

dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<script type="text/javascript" language="javascript">
var isCenterUser = isParseJson('${isCenterUser}');
function formSubmit(){
	var obj1=false,obj2=false,obj3=false,obj4=false;
	if(document.getElementById('districtL'))
		obj1=document.getElementById('districtL').checked;
	else
		obj1=false;
	if(document.getElementById('subDistrictL'))
		obj2=document.getElementById('subDistrictL').checked;
	else
		obj2=false; 
	if(document.getElementById('blockL'))
		obj3=document.getElementById('blockL').checked;
	else
		obj3=false; 
	if(document.getElementById('villageL'))
		obj4=document.getElementById('villageL').checked;
	else
		obj4=false;
	if((obj1) || (obj2) || (obj3) || (obj4))
		{
				 var error=0;
				var itr=0;
				var districtL=document.getElementById('districtL');
				var subDistrictL=document.getElementById('subDistrictL');
				var villageL=document.getElementById('villageL');
				var deptName=document.getElementById('deptName').value;
				var flag=true;
					document.getElementById('errorshortDeptName').innerHTML="";
					document.getElementById('errordeptName').innerHTML="";
					document.getElementById('errordeptNameLocal').innerHTML="";
				if(itr==0 && deptName==""){
					
					document.getElementById('errordeptName').innerHTML="Kindly fill the Department name.";
					return false;
				}
				else
				{
					if(!alphabetValidate(deptName))
						{
						document.getElementById('errordeptName').innerHTML="Please use [A-Z],[a-z] and space in Department name.";
						flag=false;
						}
				}
				
				if(document.getElementById('deptNameLocal').value!="")
					{
					if(!alphabetValidate(document.getElementById('deptNameLocal').value))
						{
						document.getElementById('errordeptNameLocal').innerHTML="Please use [A-Z],[a-z] and space in Department name Local.";
						flag=false;
						}
					
					}
				
				if(document.getElementById('shortDeptName').value!="")
				{
				if(!alphabetValidate(document.getElementById('shortDeptName').value))
					{
					document.getElementById('errorshortDeptName').innerHTML="Please use [A-Z],[a-z] and space in Sort Department name.";
					flag=false;
					}
				
				}
				if(flag)
				document.getElementById("btnSave").disabled=true;
			
				return flag; 
	
		}
		else
			{
			alert("Please Select one of Location for Extend");
			return false;
			}
	
	
}
function isNext(id){
	
	document.getElementById('btnSave').value="Save";
	if (document.getElementById('districtL').checked||document.getElementById('subDistrictL').checked||document.getElementById('blockL').checked||document.getElementById('villageL').checked) {
		document.getElementById('btnSave').value="Next";
	}
}
function getLocalBodyList(localBodyTypeCode, stateCode) {
	lgdDwrlocalBodyService.getLocalBodyList(localBodyTypeCode, stateCode, {
			callback : handleLocalBodySuccess,
			errorHandler : handleLocalBodyError
		});
}
function handleLocalBodySuccess(data) {
	var fieldId = 'localBodyCode';
	var valueText = 'localBodyCode';
	var nameText = 'localBodyNameEnglish';
	
	dwr.util.removeAllOptions(fieldId);
	dwr.util.addOptions(fieldId, data, valueText, nameText);
}
function handleLocalBodyError() {
		alert("No data found!");
}
function formLoad(){
	
	
	document.getElementById('lbSpecific').style.display = 'none';
	document.getElementById('lbSpecific').style.visibility = 'hidden';
}
function isLBSpecific(chkRd,chkRd1,idname,idname2){
	
	
	var chkRd = document.getElementById(chkRd);
	var chkRd1 = document.getElementById(chkRd1);

	if (chkRd.checked) {
		document.getElementById(idname).style.display='none';
		document.getElementById(idname).style.visibility = 'hidden';

	} else if (chkRd1.checked) {
		
		document.getElementById(idname).style.display = 'block';
		document.getElementById(idname).style.visibility = 'visible';
		document.getElementById(idname2).style.display='none';
		document.getElementById(idname2).style.visibility = 'hidden'; 
		
	}
	

	
}
function isLB(chkRd,chkRd1,idname,idname2){
	var chkRd = document.getElementById(chkRd);
	var chkRd1 = document.getElementById(chkRd1);

	if (chkRd.checked) {
		document.getElementById(idname2).style.display='none';
		document.getElementById(idname2).style.visibility = 'hidden';

	} else if (chkRd1.checked) {
		
		document.getElementById(idname2).style.display = 'block';
		document.getElementById(idname2).style.visibility = 'visible';
	
		
	}
	
}


if ( window.addEventListener ) { 
	     window.addEventListener( "load",formLoad, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", formLoad );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = formLoad;
	  }
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd" >
			<h3 class="subtitle">
				<spring:message htmlEscape="true"  code="Label.EXTENDDEPT"></spring:message>
			</h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a>
				</li>
				<%--//this link is not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				</li> --%>
				<li> 
					<a href="#" class="frmhelp">Help</a>
				</li>
			</ul>
		</div>
		<!-- added by kirandeep on 18/06/2014 -->
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="extendDepartmentPost.htm" method="POST" commandName="adminOrgDeptForm" name="form1" id="form1">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="extendDepartmentPost.htm"/>"/>
				<div id="cat">
					
						<div class="frmpnlbg">
						
						<%-- <c:if test="${!empty orgList}"> --%>
						<div id="search">
						
						<!-- <table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="100%"> -->
							<ul class = "blocklist">
								<li>
								<br/>
								<div class="frmtxt" align="left" >
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"
									code="Label.SEARCHCRITERIA"></spring:message>
							</div>
								
								<ul class = "blocklist">
										<c:if test="${isCenterUser}">
											<li>
													<label for="ministryId"><spring:message htmlEscape="true"  code="Label.MINISTRIES"/></label>
													<span class="errormsg">*</span><br />
													<form:select path="ministryId" id="ministryId" class="combofield"  style="width: 200px" onchange="getDepartmentListByMinistry(this.value)">
														<form:option value="">																
															<spring:message code="Label.SELECT" htmlEscape="true"/>
														</form:option>
														<form:options items="${ministryList}" itemLabel="orgName" itemValue="orgCode" />
													</form:select>	
													<br/>
													<label id="err_ministry" class="errormsg"></label>
													<form:errors  path="ministryId" htmlEscape="true"  cssClass="errormsg"/>
													<br/>
											</li>
										</c:if>
										
											<!-- <tr>
											<td width="2%">&nbsp;&nbsp;</td>
											<td  width="10%">&nbsp;</td>
											</tr> -->
											<li>
												<label for = "orgName"><spring:message htmlEscape="true"  code="Label.SELDEPT"></spring:message></label> 
													<span class="errormsg">*</span><br />
												 
													 		<form:select path="orgName" class="combofield" style="width: 225px" id="orgName" onchange="getAdminUnitDepartmentDetails(this.value);" >
															<form:option value="">
																	<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															
															             <form:options items="${orgList}"
																						itemValue="orgCode"
																						itemLabel="orgName"></form:options>
														 </form:select> 
											 </li>
											 <li>
											 <label for = "adminUnitLevelName"><spring:message htmlEscape="true"  code="Label.UnitLevelDEPT" text ="Select Unit Level"></spring:message></label>
											 	<span class="errormsg">*</span><br />
												 <form:select path="adminUnitLevelName" style="width: 225px" id="adminUnitLevelName"
															class="combofield">
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															</form:option>
												</form:select>
											</li>
											<li>
												<div class="">
												<label><input type="button" id="GetButton" name="Submit"  class="button" style="width: 50px" onclick="getData();"  value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" /></label>
												</div>
										    
											</li>
										</ul>
								</div>
								</li>
								</ul>
								</div>
							<%-- 	</c:if> --%>
								
								
								
				</div>
				</div>
				
			</form:form>
		</div>
	</div>

</body>
</html>