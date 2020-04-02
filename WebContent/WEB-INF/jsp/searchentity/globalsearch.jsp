<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" /> 
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

/* $(document).ready(function() {
	$("#form1").validate({ 
    rules: { 
    	entityName:{
    	   required : true,
    	   minlength: 3,
    	   maxlength: 50,
    	   onlyLetterNumber: true
       },
       captchaAnswer:{
    	   required:true
       }
    }, 
    messages: { 
    	entityName: {
    		required: "<font color='red'><br><spring:message code='LGD.entity.required' text='Please enter search details and minimum character length should be 3'/></font>",
    		minlength: "<font color='red'><br><spring:message code='LGD.entity.min.length' text='Please enter atleast 3 characters'/></font>",
    		maxlength: "<font color='red'><br><spring:message code='LGD.entity.max.length' text='Can not enter more than 50 characters'/></font>",
    		onlyLetterNumber: "<font color='red'><br><spring:message code='LGD.only.letter.number' text='This field contains invalid characters.Please use A-Z or a-z or 0-9'/></font>"
       },
       captchaAnswer: {
    	   required: "<font color='red'><br><spring:message code='LGD.captcha.required' text='Please enter captcha characters'/></font>"
       }
    } 
  });
 });   */

function validateForm() {
	var entityName = document.getElementById('entityNameA').value;
	var entityCode = document.getElementById('entitycode').value;
	var searchValueObj = document.getElementsByName('searchValueOption');
	var flag=0;
	for(var i=0;i<searchValueObj.length;i++){
		if(searchValueObj[i].checked==true)
			flag=1;
	}
	if(flag==0)
		{
	
		alert("Please select either Entity Name or Entity Code for searching");
		return false;
		
		}
	if(entityName == "" && entityCode=="" ) {
		for(var i=0;i<searchValueObj.length;i++){
			if(searchValueObj[i].checked==true)
				if(searchValueObj[i].value.trim()=="Entityname")
					{
					alert("Please enter search details Entity Name");
					}
				else if(searchValueObj[i].value.trim()=="Entitycode")
				{
					alert("Please enter search details Entity Code");
				}
		}
		
		return false;
	} 
	/* if(entityName == "") {
		alert("Please enter search details and minimum characters length should be 3");
		return false;
	}  
	*/
	if(entityName !=""){
	if(entityName.length < 3 || entityName.length > 50) {
		alert("Please enter search details and minimum characters length should be between 3 and 50");
		return false;
	}}
	 /* if(captchaAnswer=="") {
		alert("Please enter captcha characters");
		return false;
	} */
	return true;
}

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
}


/**
 * Change this method for work all type of search by Maneesh Kumar
 
 */
function openwinByUrl(url, elementId,elemntVal) {
	var obj = window.showModalDialog(url+"?<csrf:token uri='"+url+"'/>&"+elementId+"="+elemntVal, '', "dialogWidth:900px; dialogHeight:550px; dialogLeft: 470; dialogTop: 200; center:yes; resizable: yes; status:no");
} 

function viewById(url,elementId,elemntVal) {
	openwinByUrl(url, elementId,elemntVal);
}

function searchStatebyId(url,elementId,elemntVal) {
	dwr.util.setValue('globalstateId', elemntVal, {	escapeHtml : false	});
	openwinByUrl(url,elementId, elemntVal);
}

function searchDistrictbyId(url,elementId,elemntVal) {
	dwr.util.setValue('globaldistrictId', elemntVal, {	escapeHtml : false	});
	openwinByUrl(url,elementId, elemntVal);
}

function searchSubDistrictbyId(url,elementId,elemntVal) {
	dwr.util.setValue('globalsubdistrictId', id, {	escapeHtml : false	});
	openwinByUrl(url,elementId, elemntVal);
}

function searchVillagebyId(url,elementId,elemntVal) {
	dwr.util.setValue(elementId, elemntVal, {	escapeHtml : false	});
	openwinByUrl(url,elementId, elemntVal);

}

/**
 * End Change this method for work all type of search by Maneesh Kumar
 
 */
function mulSelCheckBox(val) {	
	if (val) {
		document.getElementById('chkS').checked=true;
		document.getElementById('chkD').checked=true;
		document.getElementById('chkSD').checked=true;
		document.getElementById('chkV').checked=true;
		document.getElementById('chkR').checked=true;
		document.getElementById('chkU').checked=true;
	} else {
		document.getElementById('chkS').checked=false;
		document.getElementById('chkD').checked=false;
		document.getElementById('chkSD').checked=false;
		document.getElementById('chkV').checked=false;
		document.getElementById('chkR').checked=false;
		document.getElementById('chkU').checked=false;
	}
}


function validateSelectBox() {
	 n = $( "input[type=checkbox]:checked" ).length;
	
	if(n == 0){
		alert("Please select atleast one entity type");
		return false;
	}
	return true;
}
function getData() {
	document.getElementById('ems').innerHTML="";
	
	var bool = validateForm();
	
	if(bool) {
		if(validateSelectBox()){
			//document.getElementById('ems').innerHTML="<b>Please select atleast one entity type</b>";
			//return false;
		
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		/* New Captcha Code */
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		 if(capchaImgVal=="") {
		alert("Please enter captcha characters");
		return false;
		}
		//return false;
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>";
		//displayLoadingImage();     this method not working properly........commented on :02-06-2015 by Sangita for Search Functionality
		document.getElementById('form1').submit();
		
		return true;}
		else
			return false;
	}
	
}

	function chkSearchOnLoad() {
		$("#entityNameA_error").hide();

}
	$(document).ready(function() {
		 n = $( "input[type=radio]:checked" ).length;
		 if(n!=0)
			 {
			 var searchValueObj = document.getElementsByName('searchValueOption');
				var flag=0;
				for(var i=0;i<searchValueObj.length;i++){
					if(searchValueObj[i].checked==true)
						{
						if(searchValueObj[i].value=="Entityname")
						{
						document.getElementById('searchByEntityName').style.display = "block";
						document.getElementById('searchByEntityCode').style.display = "none";
						document.getElementById('entitycode').value="";
						}
					else if(searchValueObj[i].value=="Entitycode")
						{
						document.getElementById('searchByEntityCode').style.display = "block";
						document.getElementById('searchByEntityName').style.display = "none";
						document.getElementById('entityNameA').value="";
						}
						}
				}
			 }
	});
	
	function displaySearchBlock(obj)
	{
		var value=obj.value.trim();
		if(value=="Entityname")
			{
			document.getElementById('searchByEntityName').style.display = "block";
			document.getElementById('searchByEntityCode').style.display = "none";
			document.getElementById('entitycode').value="";
			}
		else if(value=="Entitycode")
			{
			document.getElementById('searchByEntityCode').style.display = "block";
			document.getElementById('searchByEntityName').style.display = "none";
			document.getElementById('entityNameA').value="";
			}
	}
	
	function validateAlphanumericWithSpace(event,a) {
		var key;
		
		key = event.keyCode;

		// alert(key);
		if (key == 0)
			key = event.which;

		if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 8) || (key == 9)
				|| (key == 46)) {
			event.returnValue = true;
			return true;
		} else {
			alert("Please use numeric,Alphabets and Space value only");
			a.value='';
			event.returnValue = false;
			return false;

		}
	}
</script>
</head>
<body>

	<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div>
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message htmlEscape="true"  code="error.blank.commonAlert"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div>


<!-- Main content starts here -->
		<div id="frmcontent" >
			<div class="frmhd">
				<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.SEARCH"></spring:message></h3>
				<ul class="listing">
					<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li> --%>
				</ul>			
		    </div>
		    
		    <div class="frmpnlbrdr">
				<form:form action="globalsearchentity.do" method="POST" commandName="globalsearchView" id="form1">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="globalsearchentity.do"/>"/>
			  <div id="cat">		
					<div class="frmpnlbg" style="margin: 10px 10% 0px 8%;"  >
						<div class="frmtxt" >
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SEARCHCRITERIA"></spring:message></div>
							
							<input type="hidden" id="sessionId"	value='<%=sessionId%>'></input>
							
							
							
							<div class="row"  style="float: left;margin-right: 5%;">
							<form:radiobutton path="searchValueOption" value="Entityname" name="searchValueOption" onclick="displaySearchBlock(this)"/>Search byEntity Name
							<form:radiobutton path="searchValueOption" value="Entitycode" name="searchValueOption" onclick="displaySearchBlock(this)"/>Search by Entity Code
							
							<br/><br/>
							<div id="searchByEntityName" style="display: none;">
								<span class="errormsg" >(Please Enter atleast 3 characters of the Entity Name)</span>
								<br>
								<label for="entityNameA"><spring:message htmlEscape="true"  code="Label.ENTITYNAME"></spring:message><span class="errormsg">*</span></label>
								
								<form:input type="text" id="entityNameA" class="frmfield" path="entityName" maxlength="50"
								 onkeydown="validateAlphanumericWithSpace(event,this)"/>
								<%-- <div class="errormsg" id="entityNameA_1_type_error" style="display: none;"><spring:message code="Special characters are not allowed."/></div> --%>
								</br>
								
								<div class="errormsg">
									<form:errors htmlEscape="true" path="entityName"></form:errors>
								</div> 
								</div>
								<div id="searchByEntityCode" style="display: none;">
								<label for="entitycode"><spring:message htmlEscape="true"  code="Label.ENTITYCODE"></spring:message><span class="errormsg">*</span></label>
								<form:input type="text" id="entitycode" class="frmfield" path="entityCode" maxlength="50"
								 onkeydown="validateOnKeyPessDown(event,'entitycode_1','number')" />
								 <div class="errormsg" id="entitycode_1_type_error" style="display: none;"><spring:message code="Only Numeric value allowed."/></div>
								 </div>
								<%@ include	file="../common/captcha_integration.jspf"%>
								
							</div>
						
							<div class="search_options" style="width: 90%;" >
								<div class="block" ><label for="selectall"><form:checkbox id="selectall" name="checkbox" value="" path="" onclick="mulSelCheckBox(this.checked)" />
								<spring:message htmlEscape="true"  code="Label.SELECTALL"></spring:message></label>
								</div>
								<div class="block"  >
									<label><spring:message htmlEscape="true"  code="Label.LANDREGION"></spring:message></label>
									<ul class="blocklist">
										<li><label for="chkS"><form:checkbox name="checkbox" id="chkS" path="stateChecked" /> 
												   <spring:message htmlEscape="true"  code="Label.STATE"></spring:message>
											</label>
										</li>
										<li><label for="chkD"><form:checkbox name="checkbox" id="chkD" path="districtChecked" /> 
												   <spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message>
										    </label>
										</li>
										<li><label for="chkSD"><form:checkbox name="checkbox" id="chkSD" path="subDistrictChecked" /> 
											       <spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message>
										  	</label>
										</li>
										<li><label for="chkV"><form:checkbox name="checkbox" id="chkV" path="villageChecked" /> 
												   <spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message>
											</label>
										</li>
									</ul>
								</div>
								<div class="block">
									<label><spring:message htmlEscape="true"  code="Label.LOCALGOVTBODY"></spring:message></label>
									<ul class="blocklist">
										<li><label for="chkR"><form:checkbox name="checkbox" id="chkR" path="ruralChecked" /> <%-- onclick="ruralCheckBox(this.checked)" --%>
											       <spring:message htmlEscape="true"  code="Label.RURALG"></spring:message>
											 </label>
										</li>
										<li><label for="chkU"><form:checkbox name="checkbox" id="chkU" path="urbanChecked" /> 
												   <spring:message htmlEscape="true"  code="Label.ULG"></spring:message>
											</label>
										</li>
									</ul>
								</div>
								<div class="block" style="width:21%;" >
									<form:errors cssClass="errormsg" path="selectedVal"></form:errors>
									<div id="ems" class="errormsg"></div>
								</div>
								
								<div class="clear"></div>
							</div>
							<div class="bttns" align="center">
					   			<input type="button" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" onclick="return getData();" /> 
					   			<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>';"/>
					   			<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"/>
					  		</div>
							
						  </div>
						  
					   </div>
					   					   
					</div>
					
					
					<!-- State -->
					
		<%-- 			<c:if test="${!empty searchStateL }">
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.STATESEARCHRESULT"></spring:message></div> 
							<table name="Rural Local Body Detail" width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" ><spring:message htmlEscape="true" 	code="Label.STATECODE"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.STATENAMEINENGLISH"></spring:message></td>
												<td width="14%" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchState" varStatus="listStateRow" items="${searchStateL}">
													
													 <tr class="tblRowB">
														<td width="6%"><c:out value="${listStateRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchState.stateCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.stateNameEnglish}" escapeXml="true"></c:out></td>
 														<td><center><a href="#"><img src="images/view.png" onclick="javascript:searchStatebyId('globalviewStateDetail.do','globalstateId',${searchState.stateCode});" width="18" height="19" border="0" /></a></center></td>
													</tr> 
											
											</c:forEach>
											
											<form:input path="globalstateId" type="hidden" name="globalstateId" id="globalstateId" />
																						
                                           </table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					</c:if> --%>

					
					<!-- District-->
					
		<%-- 			<c:if test="${! empty searchDistrict and searchDistrict.size()>0 }">
					 <div class="frmpnlbg">
						<div class="frmtxt">
						  <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DISTRICTSEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" ><spring:message htmlEscape="true" 	code="Label.DISTRICTCODE"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.STATENAME"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchState" varStatus="listDistirctRow" items="${searchDistrict}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listDistirctRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchState.districtCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.stateNameEnglish}" escapeXml="true"></c:out></td>
														<td><center><a href="#"><img src="images/view.png" onclick="javascript:searchDistrictbyId('globalviewDistrictDetail.do','globaldistrictId',${searchState.districtCode});" width="18" height="19" border="0" /></a></center></td>
													
													</tr>
												</c:forEach>
												<form:input path="globaldistrictId" type="hidden" id="globaldistrictId" />																				
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>	
					</c:if> --%>
					
					<!--  Sub District-->
					<%-- 
					<c:if test="${! empty searchSubdistrict}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTSEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="16%" ><spring:message htmlEscape="true" 	code="Label.SUBDISTRICTCODE"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.DISTRICTNAME"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.STATENAME"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchState" varStatus="listSubdistirctRow" items="${searchSubdistrict}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listSubdistirctRow.index+1}" escapeXml="true"></c:out></td>
														
														<td><c:out value="${searchState.subdistrictCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.stateNameEnglish}" escapeXml="true"></c:out></td>
														<td><center><a href="#"><img src="images/view.png" onclick="javascript:searchSubDistrictbyId('globalviewSubDistrictDetail.do','globalsubdistrictId',${searchState.subdistrictCode});" width="18" height="19" border="0" /></a></center></td>
													</tr>
												</c:forEach>
												<form:input path="globalsubdistrictId" type="hidden" name="globalsubdistrictId" id="globalsubdistrictId" />	
																																		
										</table>
									</td>
								</tr>
							
							</table>
						</div>
					</div>
					</c:if> --%>
					
					<!-- Village -->
					
				<%-- 	<c:if test="${! empty searchVillage}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.VILLAGESEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="15%" ><spring:message htmlEscape="true" 	code="Label.VILLAGECODE"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAME"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.DISTRICTNAME"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.STATENAME"></spring:message></td>
												<td align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchState" varStatus="listVillageRow" items="${searchVillage}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listVillageRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchState.villageCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.villageNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchState.stateNameEnglish}" escapeXml="true"></c:out></td>
 														<td><center><a href="#"><img src="images/view.png" onclick="javascript:searchVillagebyId('globalviewVillageDetail.do','globalvillageId',${searchState.villageCode});" width="18" height="19" border="0" /></a></center></td>
													</tr>
												</c:forEach>		
												<form:input path="globalvillageId" type="hidden" name="globalvillageId" id="globalvillageId" />																					
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					</c:if> --%>
					
	            <!--  Local Government Body-->	
									
			<%-- 	<c:if test="${! empty searchRural}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						  <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.RURALSEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="8%" ><spring:message htmlEscape="true"  code="Label.LOCALBODYCODE"></spring:message></td>
												<td width="12%" ><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></td>
												<td width="18%" ><spring:message htmlEscape="true"  code="Label.LGBNAME"></spring:message></td>
												<td width="48%"  colspan="2"><spring:message htmlEscape="true" 	code="Label.Hierarchy"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchLGB" varStatus="listLGBRow" items="${searchRural}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listLGBRow.index+1}" escapeXml="true"></c:out></td>														
														<td><c:out value="${searchLGB.localBodyCode}" escapeXml="true"></c:out></td>	
														<c:if test="${fn:containsIgnoreCase(searchLGB.category,'P')}">
															<td align="left"><c:out value="${searchLGB.localBodyTypeName}" escapeXml="true"></c:out></td>
														</c:if>
														<c:if test="${fn:containsIgnoreCase(searchLGB.category,'T')}">
															<td align="left"><c:out value="Traditional"></c:out></td>
														</c:if>
														<td align="left"><c:out value="${searchLGB.localBodyNameEnglish}" escapeXml="true"></c:out></td>
														 <td align="left" colspan="2"><c:out value="${searchLGB.parentLocalBodyNameEnglish}" escapeXml="true"></c:out></td> 
														<td align="center"><a href="#"><img src="images/view.png" onclick="viewById('globalviewLocalBodyDetail.do','globallocalbodyId',${searchLGB.localBodyCode});" width="18" height="19" border="0" /></a></td>
						 																				
													</tr>
												</c:forEach>																						
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					</c:if>	
					 --%>	
				<c:if test="${! empty allSerachDeatail}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						  <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.URBANSEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.ENTITYCODE"></spring:message></td>
												<td width="12%" ><spring:message htmlEscape="true"  code="Label.ENTITYTYPE"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.ENTITYNAME"></spring:message></td>
												<td width="48%"  colspan="2"><spring:message htmlEscape="true" 	code="Label.ENTITYHIERARCHY"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="SerachDeatail" varStatus="SerachDeatailRow" items="${allSerachDeatail}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${SerachDeatailRow.count}" escapeXml="true"></c:out></td>
														<td><c:out value="${SerachDeatail.entityCode}" escapeXml="true"></c:out></td> 
														<%-- <td><c:out value="Urban"></c:out></td> --%>
														<td align="left"><c:out value="${SerachDeatail.entityType}" escapeXml="true"></c:out></td>
														
														<td align="left"><c:out value="${SerachDeatail.entityName}" escapeXml="true"></c:out></td>
														<td align="left" colspan="2"><c:out value="${SerachDeatail.hierarchy}" escapeXml="true"></c:out></td>
														<%-- <td align="left"><c:out value="${searchLGB.stateNameEnglish}"></c:out></td> --%>
														<%--  <td align="center"><a href="#"><img src="images/view.png" onclick="viewById('globalviewLocalBodyDetail.do','globallocalbodyId', ${SerachDeatail.entityCode});" width="18" height="19" border="0" /></a></td> --%>
														<c:choose>
												          <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Sub-District')}">
														 <td><center><a href="#"><img src="images/view.png"  onclick="javascript:searchSubDistrictbyId('globalviewSubDistrictDetail.do','globalsubdistrictId',${SerachDeatail.entityCode});" width="18" height="19" border="0" /></a></center></td>
												         </c:when>
												          <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'District')}">
														 <td><center><a href="#"><img src="images/view.png" onclick="javascript:searchDistrictbyId('globalviewDistrictDetail.do','globaldistrictId',${SerachDeatail.entityCode});" width="18" height="19" border="0" /></a></center></td>
												         </c:when>
												           <c:when test="${ SerachDeatail.entityType=='Village'}">
														 <td><center><a href="#"><img src="images/view.png" onclick="javascript:searchVillagebyId('globalviewVillageDetail.do','globalvillageId',${SerachDeatail.entityCode});" width="18" height="19" border="0" /></a></center></td>
												         </c:when>
												           <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'State')}">
														 <td><center><a href="#"><img src="images/view.png" onclick="javascript:searchStatebyId('globalviewStateDetail.do','globalstateId',${SerachDeatail.entityCode});" width="18" height="19" border="0" /></a></center></td>
												         </c:when>
												           
												           <c:otherwise>
														 <td><center><a href="#"><img src="images/view.png" onclick="viewById('globalviewLocalBodyDetail.do','globallocalbodyId',${SerachDeatail.entityCode});"  width="18" height="19" border="0" /></a></center></td>
												         </c:otherwise>
												         </c:choose>
													</tr>
												</c:forEach>
                        				</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					</c:if>		
					
					
											<!-- Election Constituency -->
					
				<%-- 	<c:if test="${! empty searchAC}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.ASSCONSTITUENCSEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="25%" ><spring:message htmlEscape="true" 	code="Label.ASSEMBLYCONSTITUENCYCODE"></spring:message></td>
												<td width="26%" ><spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCYNAME"></spring:message></td>
												<td width="25%" ><spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCYNAME"></spring:message></td>
												<td width="35%" ><spring:message htmlEscape="true"  code="Label.STATENAME"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchEC" varStatus="listACRow" items="${searchAC}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listACRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchEC.acCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchEC.acNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchEC.pcNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchEC.stateNameEnglish}" escapeXml="true"></c:out></td>
														<td align="center"><a href="#"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
													</tr>
									</c:forEach>
											
											
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					</c:if>						
						
						
						<c:if test="${! empty searchPC}">
					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.PARCONSTITUENCYSEARCHRESULT"></spring:message></div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="100%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCYCODE"></spring:message></td>
												<td width="30%" ><spring:message htmlEscape="true"  code="Label.PARLIAMENTCONSTITUENCYNAME"></spring:message></td>
												<td width="21%" ><spring:message htmlEscape="true"  code="Label.STATENAME"></spring:message></td>
												<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
								</tr>
									<c:forEach var="searchEC" varStatus="listECRow" items="${searchPC}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${listECRow.index+1}" escapeXml="true"></c:out></td>
														<td><c:out value="${searchEC.pcCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${searchEC.pcNameEnglish}" escapeXml="true"></c:out></td>													
														<td align="left"><c:out value="${searchEC.stateNameEnglish}" escapeXml="true"></c:out></td>														
														<td align="center"><a href="#"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
																
													</tr>
									</c:forEach>																						
										</table>
									</td>
								</tr>
						<tr>
									</tr>
							</table>
						</div>
					</div>
					</c:if>	 --%>
				</form:form>
				<c:if test="${nodata != null}">
					<script>
						alert("Matching records not found for selected search criteria, kindly change your search criteria");
					</script>
				</c:if>	
				<c:if test="${nodata == null}">
				<c:if test="${closeFlag == true}"> 
				<div class="buttons" align="center">				
					<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />
				</div>
				</c:if>	
				</c:if>	
			 <script src="/LGD/JavaScriptServlet"></script>												
			</div>
		</div>

</body>
</html>