<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/invalidateSubdistrict.js"
	charset="utf-8"></script>
	<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />


<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictGetInvalidateDraft.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
<script src="<%=contextpthval %>/jquery-1.7.2.js"></script>

<script src="<%=contextpthval %>/ui/jquery.ui.core.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.widget.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.button.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.mouse.js"></script>	
<script src="<%=contextpthval %>/ui/jquery.ui.draggable.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.position.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.dialog.js"></script>
<script src="<%=contextpthval %>/ui/jquery.validate.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.accordion.js"></script>
<script src="<%=contextpthval %>/external/jquery.bgiframe-2.1.2.js"></script>
<script src="<%=contextpthval %>/ui/jquery.ui.resizable.js"></script>
<script src="<%=contextpthval %>/ui/jquery.effects.core.js"></script>
<script src="<%=contextpthval %>/ui/jquery.effects.blind.js"></script>
<script src="<%=contextpthval %>/ui/jquery.effects.explode.js"></script>
<script type="text/javascript"  src="<%=contextpthval %>/js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="<%= contextpthval %>/js/jquery.tabs.pack.js"></script>
<script src="<%= contextpthval %>/js/jquery-blink.js" language="javscript" type="text/javascript"></script>
<link rel="stylesheet" href="<%=contextpthval %>/css/demos.css">
<link rel="stylesheet" href="<%=contextpthval %>/themes/base/jquery.ui.all.css">


<!-- <link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" /> -->
<title>Invalidate Subdistrict</title>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function getvillagesInDraftMode(subdistrictCode,name){
	lgdDwrSubDistrictService.getvillagesInDraftMode(subdistrictCode,{
		async: true,
		callback : handleVillageListDraft,
		arg:name,
	});
	
	
}

function handleVillageListDraft(data,name){
	if(data.length >0){
		var text= $("#"+name+" option:selected").text();
		customAlert(data.length+" villages of "+text+" Sub District are in Draft Mode.Kindly first take suitable action(publish the changes) on them in order to continue further");
		$('#ddSubdistrict').val(0);
	}
}


var popupWindow	=	null;
function centeredPopup(url,winName,scroll){
	windowHeight = (screen.height) ? (screen.height-200) : 0;
	windowWidth = (screen.width) ? (screen.width)/4 : 0;
	LeftPosition = (screen.width) ? (screen.width-windowWidth) : 0;
	TopPosition = (screen.height) ? (screen.height-windowHeight)/2 : 0;
	settings = 'height='+windowHeight+',width='+windowWidth+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=no';
	popupWindow = window.open(url,winName,settings);
	}

function doSaveAsDraft()
{
	document.forms['invalidateForm'].action = 'saveAsDraftInvalidateSubdistrict.htm';
	document.forms['invalidateForm'].submit();
}

function getSaveAsDraft()
{
	document.forms['invalidateForm'].action = 'publishSaveAsDraftInvalidateSubdistrict.htm';
	document.forms['invalidateForm'].submit();
}

function getData()
{
	if (document.getElementById('ddDistrict').selectedIndex>0)
		{
			getSubDistrictandULBList(document.getElementById('ddDistrict').value);
			populateSubdistricts();
		}
}

function populateSubdistricts()
{
	lgdDwrSubDistrictGetInvalidateDraft.getInvalidateSubdistricts({
		async: false,
		callback : handleSubDistrictsListSuccess,
	});
}

function handleSubDistrictsListSuccess(data)
{
	document.getElementById('ddSubdistrict').value = data.split('::')[0];
	getVillageList(document.getElementById('ddSubdistrict').value);
	
	if (data.indexOf('#')==-1)
		document.getElementById('ddSubdistrictMergeYes').value = data.split('::')[1];
	else
		{
			document.getElementById('subdistrictdelete_no').checked=true;
			toggledisplay('subdistrictdelete_no','cvillage');
			
			setTimeout("getInvalidatingSubdistrictsList('" + data + "')",2000);
		}
}

function getInvalidatingSubdistrictsList(value)
{
	var subdistrictsList = value.split('::')[1].split('%');
	
	for (var i = 0; i < subdistrictsList.length-1; i++)
	{
		if (i%2==0)
			document.getElementById('ddSubdistrictMergeNo').value = subdistrictsList[i];
		else
			{
				var villagesList = subdistrictsList[i].split('#');
				for (var j = 0; j < villagesList.length; j++)
					{
						document.getElementById('villageListMainContributing').value = villagesList[j];
						addItem('villageListNewContri','villageListMainContributing','',false);
					}
				setTimeout("doNothing()", 100);
				validateSelectAddMore();
				generateTempView();
				hasMoreItems("villageListMainContributing","chooseMoreBtn");
			}
	}
}

$(document).ready(function() {
	
	toggledisplay('subdistrictdelete_yes','fromothersubdistrict');
	
	$('#fromothersubdistrict').hide();
	$('#id1').hide();
	
	
});



copyAllObjectFormOnetoAnother=function(copyId,pasteId){
	
	
	$('#'+copyId+' option').clone().appendTo('#'+pasteId);
	 $('#'+copyId).empty();
	 sortListBox(pasteId);
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

</script>
</head>
<body onload="getData();"


	
         

onkeypress="disableCtrlKeyCombination(event);"
		onkeydown="disableCtrlKeyCombination(event);">
		<%
			String formname="ManageSchemeSpecificATRFormat";
			request.setAttribute("formId", formname);
		%>
		<div class="overlay" id="overlay1" style="display: none;"></div>
		<div class="box" id="box1">
			<a class="boxclose" id="boxclose1"></a>
			<div>
				<c:if test="${!empty param.family_msg}">
					<table>
						<tr>
							<td rowspan="2"><center>
									<div class="success"></div>
								</center></td>

							<td><div class="helpMsgHeader" style="width: 275px;">
									<h4>Success Message</h4>
								</div></td>
						</tr>
						<tr>
							<td><div id="successMsg" class="successfont">
									<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
								</div>
							</td>
						</tr>
					</table>

				</c:if>

				<c:if test="${!empty family_error}">

					<table>
						<tr>
							<td rowspan="2"><div class="failur"></div></td>

							<td><center>
									<div class="helpMsgHeader" style="width: 275px;">
										<b>Failure Message</b>
									</div>
								</center></td>
						</tr>
						<tr>
							<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
							</td>
						</tr>
					</table>

				</c:if>

			</div>
		</div>

		<div class="box" id="box">
			<a class="boxclose" id="boxclose"></a>
			<div id="validate_error">
				<table>
					<tr>
						<td rowspan="2"><div class="errorImg"></div></td>
						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Error Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
						</td>
					</tr>
				</table>

			</div>
		</div>
	<div id="frmcontent">
		<div class="frmhd">
		
		
		
			<h3 class="subtitle"><label> <spring:message code="Label.INVALIDATESUBDISTRICT" htmlEscape="true"></spring:message></label></h3>
										 <ul id="showhelp" class="listing">
					 				       <%--//these links are not working  <li>
									 				       <a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
									 		</li> --%>
					 				      <%--  //this link is not working   
					 				        <li>
					 				        	<a href="viewManualHelps.do?formName=${formId}&<csrf:token uri='viewManualHelps.do'/>" class="frmhelp" onclick="centeredPopup(this.href,'myWindow','yes');return false">Help</a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
		
	
		</div>
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="invalidateSubD.htm" id="invalidateForm" method="POST" commandName="invalidatesubdistrict" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateSubD.htm"/>"/>
			<input type ="hidden" name="flagSubDistrictInvalid" id="flagSubDistrictInvalid"> </input>
			<div id="dialog-confirm"></div>
				<div id="cat">
				
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.INVALIDATESUBDISTRICT" htmlEscape="true"></spring:message>
							</div>
							<div>
							<ul class="blocklist">
									<li>
											<label><spring:message code="Label.SOURCEDISTRICT" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /><br />
										<label> <form:select path="districtNameEnglish"  id="ddDistrict" cssClass="combofield"
												             onchange="getSubDistrictandULBList(this.value);hideBoxes();" htmlEscape="true"
												             onfocus="validateOnFocus('ddDistrict');" 
												              onblur="vlidateOnblur('ddDistrict','1','15','c');">
												  <c:if test="${districtCode eq 0}">
												<form:option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></form:option>
												</c:if>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
												
														<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state eq 'A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state eq 'F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
																</form:option>
															</c:if>
														</c:forEach>
											    </form:select> </label> <br />
											    <form:hidden path="operation" value="I"/>
												<form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>	
										<div class="errormsg"><form:errors htmlEscape="true"  path="districtNameEnglish" ></form:errors></div>
										<span class="errormsg" id="ddDestDistrict_error">
									    </span>
									</li>
									<li>
											<label><spring:message code="Label.SOURCESUBDISTRICT" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /><br />
										<label> <form:select path="subdistrictNameEnglish"   id="ddSubdistrict" cssClass="combofield" htmlEscape="true"
												             onchange="getvillagesInDraftMode(this.value,'ddSubdistrict');hideBoxes();" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
											   </form:select> </label>
										<div class="errormsg"><form:errors htmlEscape="true"  path="subdistrictNameEnglish" ></form:errors></div>
										
									
												
									</li>
																
							</ul>
							
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div id="id1" class="frmpnlbg">
						<div class="frmtxt">
						<div>
					
						<label><spring:message code="Label.HOWSUBDISTRICTDELETED" htmlEscape="true"></spring:message></label>
						<ul class="listing">
								<li><label>
																	<form:radiobutton id="subdistrictdelete_yes" path="rdoSubdistrictDelete" htmlEscape="true"
																		onclick="toggledisplay('subdistrictdelete_yes','fromothersubdistrict')" name="villagedelete" value="false" /> 
															</label>
															<spring:message code="Label.YES" htmlEscape="true"></spring:message>
															</li>
								<li>
								<label>
																	<form:radiobutton id="subdistrictdelete_no" path="rdoSubdistrictDelete" htmlEscape="true"
																		onclick="toggledisplay('subdistrictdelete_no','cvillage')" name="villagedelete" value="true" /> 
															</label> <spring:message code="Label.NO" htmlEscape="true"></spring:message>
								</li>
								<li>
								<div  class="errormsg"></div></li>
						</ul>
								</div>
							<div class="clear"></div>
						
						</div>
					</div>

					<div id='fromothersubdistrict' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
							Select the Subdistrict to be merged with
							
							</div>
							<div >
							<ul class="blocklist">
										<li>
												<label><spring:message code="Label.TARGETSUBDISTRICT" htmlEscape="true"></spring:message></label><span class="errormsg">*</span><br /><br />
										<label> <form:select path="targetSubdistrictYes"  id="ddSubdistrictMergeYes" cssClass="combofield" htmlEscape="true"
												             onchange="getVillageListMerge(this.value);" onblur="vlidateOnblur('ddSubdistrictMergeYes','1','15','c');">
											</form:select> 
									    </label>
										<div class="errormsg"><form:errors htmlEscape="true"  path="targetSubdistrictYes" ></form:errors></div>
										</li>
										
										
										<li>
										<input type="hidden" id="hiddenid" value="1"></input>
										
										</li>
							</ul>
							</div>
							
						</div>
					</div>
					<div id='cvillage' class="frmpnlbg" style="visibility: hidden; display: none;" >
						<div class="frmtxt" >
							<div class="frmhdtitle">
								<spring:message code="Label.TARGETSUBD" htmlEscape="true"></spring:message>
							</div>
							
							<ul class="blocklist">
								<li>
									<label><spring:message code="Label.TARGETSUBDISTRICT" htmlEscape="true"></spring:message></label><span class="errormsg"></span><br />
													<label> <form:select path="targetSubdistrictNo"  id="ddSubdistrictMergeNo" cssClass="combofield" htmlEscape="true"
												             onchange="getVillageListMerge(this.value);" onblur="vlidateOnblur('ddSubdistrictMergeNo','1','15','c');">
											</form:select>  
												    </label>
													<div class="errormsg"><form:errors htmlEscape="true"  path="targetSubdistrictNo" ></form:errors></div>
								</li>
								
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
										<label><spring:message code="Label.SELECTVILLAGESTOMERGEWITH" htmlEscape="true"></spring:message> </label>
										<form:select name="select9" size="1" id="villageListMainContributing" path="villList" multiple="multiple" class="frmtxtarea"></form:select>
												<div class="errormsg"><form:errors htmlEscape="true"  path="villList" ></form:errors></div>
										</div>
										<div class="ms_buttons">
												<label> 
															    <input type="button" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;" class="bttn" 
																	   onclick="addItemPC('villageListNewContri','villageListMainContributing','',false);" />
															</label>
															
												<label> 
															    <input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" 
																	   onclick="removeItem('villageListNewContri','villageListMainContributing',false)" />
															</label>
												<label> <input
																	type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" 
																	onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing');" />
															</label>
										</div>
										<div class="ms_selection">
										<label><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message></label><span class="errormsg">*</span>
											 <form:select name="select4" id="villageListNewContri" size="1" multiple="multiple" path="contributedVillages" class="frmtxtarea"></form:select>
												  <div class="errormsg"><form:errors htmlEscape="true"  path="contributedVillages" ></form:errors></div>
												  
												  <label> 
										    <input type="button" id="chooseMoreBtn" onclick='validateSelectAddMore();generateTempView();hasMoreItems("villageListMainContributing","chooseMoreBtn")' name="Add Villages"  class="btn" value="Add Villages" /></label>
										
										</div>
									</div>
								
								</li>
								
								<li>
											
										
								</li>
							</ul>
							
							<div class="clear"></div>
							<div >
								<table id="dynamicTbl" width="664" class="tbl_with_brdr" style="visibility: hidden">
												    	<tr class="tblRowTitle tblclear">
												    		<th>Subdistrict Name</th>
												    		<th>Village Names</th>
												    	</tr>
										</table>
						</div>
						</div>
					</div>

					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%">&nbsp;</td>
								<td width="86%">
									<div>
										<label> 
										    <input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn" value="<spring:message code="Button.SAVE"></spring:message>" />
										</label> 
										 <label>
											<input type="button" name="Submit6" class="btn" value="<spring:message code="Button.CLOSE"></spring:message>"
											       onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
																						   <label>											       
									</div>
								</td>
							</tr>
						</table>
					</div>
					<script>
					 
							   <c:if test="${districtCode ne 0}">	
							   	getSubDistrictandULBList('${districtCode}');
						   		  </c:if>					
					 </script>	
				</div>
			</form:form>
             <script src="/LGD/JavaScriptServlet"></script> 
			</td>
			</tr>
			</table>
		</div>
		</div>
</body>
</html>

























