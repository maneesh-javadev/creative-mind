<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script>
	var cPath = "<%=contextpthval%>";
	showDialog = function(){
		$("#dialog-message").dialog({
	    	  resizable: false,
	    	  title:message,
	    	  buttons: [
	    	            {id: "Ok", text: "Ok",click: function() {$(this).dialog("close");}},
                        {id: "Cancel", text: "Cancel",click: function() {$(this).dialog("close");}}
	    	   		   ]
		});
	};
	
	addSubtypesRow = function(tableId) {
		var tbl = document.getElementById("tblsubtype_"+tableId);
		var lastRow = tbl.rows.length;
		if (lastRow > 4) {
			alert("Can\'t add more than 5 rows");
			return;
		}
		var iteration = lastRow; 
		var row = tbl.insertRow(lastRow);
		// cell EnglishName
		var cellRight1 = row.insertCell(0);
		var el = document.createElement('input');
		el.type = 'text';
		el.name = 'subTypeNames_'+tableId;
		el.id = 'subtypeName' + iteration;
		cellRight1.appendChild(el);
		
		
		// cell Local Name
		var cellRight1 = row.insertCell(1);
		var el = document.createElement('input');
		el.type = 'text';
		el.name = 'subTypeLocalNames_'+tableId;
		el.id = 'subtypeName' + iteration;
		cellRight1.appendChild(el);
		
		// cell remove button
		var cellRemoveBtn = row.insertCell(2);
		var el = document.createElement('img');
		el.id = "remove" + iteration;
		el.name = "remove" + iteration;
		el.src = cPath+"/images/cancel.png";
		el.width="13";
		el.height="13";
		el.onclick = function () {
			 var rownum = this.parentNode.parentNode.rowIndex;
			 tbl.deleteRow(rownum);
		};
	    cellRemoveBtn.appendChild(el);
	};
	 
	checkbox_event = function (obj){
		var lbTypeId = obj.id;
		if(obj.checked){
			setDisabled(lbTypeId, false);	
		}else{
			 if(window.confirm("Are you sure, wan't to uncheck ?")){
				setDisabled(lbTypeId, true);
				removeRows(lbTypeId);	
			} else{
				obj.checked=true;
			}
		}
	};
	
	setDisabled = function(lbTypeId, bool){
		enableSubmit();
		document.getElementById('nomenEnglish_'+lbTypeId).disabled=bool;
		document.getElementById('nomenLocal_'+lbTypeId).disabled=bool;
		document.getElementById('sdubtype_'+lbTypeId).disabled=bool;
		document.getElementById('sdubtypeLocal_'+lbTypeId).disabled=bool;
		if(bool){
			document.getElementById('plus_'+lbTypeId).setAttribute('style', 'display:none');
			document.getElementById('nomenEnglish_'+lbTypeId).value="";
			document.getElementById('nomenLocal_'+lbTypeId).value="";
			document.getElementById('sdubtype_'+lbTypeId).value="";
			document.getElementById('sdubtypeLocal_'+lbTypeId).value="";
		}else{
			document.getElementById('plus_'+lbTypeId).setAttribute('style', 'display:inline');
		}
	};
	
	removeRows = function(tableId) {
	    retrievetable = document.getElementById("tblsubtype_"+tableId);
	    for (var i = retrievetable.rows.length-1 ; i > 0; i--) {
	           retrievetable.deleteRow(i);
	    }
	};
	
	
	
	submitDetails = function (process){
		var actionErr = document.getElementById('errorProcess'); actionErr.innerHTML="";
		var actionURL = "";
		if(process == "add"){
			actionURL = "localGovSetupRuralAdd.htm?<csrf:token uri='localGovSetupRuralAdd.html'/>";
		}else if(process == "modify"){
			actionURL = "localGovSetupRuralModify.htm?<csrf:token uri='localGovSetupRuralModify.html'/>";
		}else{
			actionErr.innerHTML="<strong>Processed action is invalid, Please use Define / Modify Rural Setup </strong>.";
			return false;
		}
			
				
			
		var isValidForm = validateForm();
		if(isValidForm == true){
	    	var elements = document.getElementsByName('check');
			var checkedvalues = "";
			for(var i=0; i<elements.length;i++) {
				var checkbox = elements[i];
				if(checkbox.checked){
					checkedvalues += checkbox.id + "|";
					checkedvalues += document.getElementById('lbType_'+checkbox.id).value + "|";
					checkedvalues += document.getElementById('nomenEnglish_'+checkbox.id).value + "|";
					checkedvalues += document.getElementById('nomenLocal_'+checkbox.id).value + "|";
					checkedvalues += document.getElementById('level_'+checkbox.id).value + "|";
					checkedvalues += getSubTypeDetails(checkbox.id);
					checkedvalues += "@@";
				};
			};
			document.getElementById('lbRuralChkedValues').value = checkedvalues.substring(0, checkedvalues.length-2);
			document.getElementById('save').disabled = true;
			document.getElementById('modify').disabled = true;
			document.forms['lGRuralSetupForm'].action = actionURL;
			document.forms['lGRuralSetupForm'].method = "post";
			document.forms['lGRuralSetupForm'].submit();
			return false;
	    };
	};
	
	getSubTypeDetails = function(lbTypeId){
		var subTypeDetails = "";
		var subTypeElements = document.getElementsByName('subTypeNames_'+lbTypeId);
		var subTypeLocalElements = document.getElementsByName('subTypeLocalNames_'+lbTypeId);
		for(var i=0; i<subTypeElements.length;i++) {
			var innSubType = subTypeElements[i];
			var innSubTypeLocal = subTypeLocalElements[i];
			if(innSubType.value != ""){
				subTypeDetails +=innSubType.value;
				subTypeDetails +="-";
				subTypeDetails +=innSubTypeLocal.value;
		    	subTypeDetails +=",";
			}
		}
		return subTypeDetails.substring(0, subTypeDetails.length-1);
	}; 
	
	
	
	validateForm = function() {
		var elements = document.getElementsByName('check');
		for(var i=0; i<elements.length;i++) {
			var checkbox = elements[i];
			if(checkbox.checked){
				var nameEnglish = document.getElementById('nomenEnglish_'+checkbox.id).value;
				if(trim(nameEnglish) == ""){
					document.getElementById('errorEnglish_'+checkbox.id).innerHTML="Please Enter Nomenclature (In English).";
					return false;
				}
				if(!validateLetterSpace(nameEnglish)){
					document.getElementById('errorEnglish_'+checkbox.id).innerHTML="Invalid Nomenclature (In English).";
					return false;
				}
				var nameLocal = document.getElementById('nomenLocal_'+checkbox.id).value;
				if(trim(nameLocal) == ""){
					document.getElementById('errorLocal_'+checkbox.id).innerHTML="Please Enter Nomenclature (In Local language).";
					return false;
				};
				if(!validateSpecialCharacters(nameLocal)){
					document.getElementById('errorLocal_'+checkbox.id).innerHTML="Invalid Nomenclature (In Local language).";
					return false;
				};
			};
		};
		return true;
	};
	
	clearMsgs = function(){
		var elements = document.getElementsByName('check');
		for(var i=0; i<elements.length;i++) {
			var checkbox = elements[i];
			document.getElementById('errorEnglish_'+checkbox.id).innerHTML="";
			document.getElementById('errorLocal_'+checkbox.id).innerHTML="";
		};	
	};
	
	enableSubmit = function(){
		document.getElementById('save').disabled = true;
		document.getElementById('modify').disabled = true;
		clearMsgs();
		var elements = document.getElementsByName('check');
		for(var i=0; i<elements.length;i++) {
			var checkbox = elements[i];
			if(checkbox.checked){
				document.getElementById('save').disabled = false;
				document.getElementById('modify').disabled = false;
				break;
			}
		};	
	};
</script>
</head>

<body >
	<form:form name="lGRuralSetupForm" method="post" commandName="lGRuralSetupForm" >
        <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="localGovSetupRural.htm"/>"/>
        <input type="hidden" id="lbRuralChkedValues" name="lbRuralChkedValues"/>
        <div id="tab1">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td valign="top" style="padding: 0px"> 
						<div id="frmcontent">
							<div class="frmhd">  <!-- this is riht page -->
																
							<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.LGSR"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				       <%--//these links are not working  
					 				        <li>
					 				            <a href="#" class="frmhelp"><label><spring:message htmlEscape="true" code="Button.HELP"></spring:message></label></a>
					 				        </li>
					 	 --%>
					 		    </ul>	
								
							</div>
						<div class="frmpnlbrdr">	
													<div class="frmpnlbg">
								<div class="frmtxt">
									<table width="100%" class="tbl_no_brdr">
										<tr align="center">
											<td style="padding: 0px">
												<table width="100%" class="tbl_with_brdr">
													<tr class="tblRowTitle tblclear" style="height: 45px">
														<td width="5%"></td>
														<td>
															<strong><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></strong>
														</td>
														<td align="center" width="23%">
															<strong><spring:message htmlEscape="true" code="Label.NIE"></spring:message>&nbsp;<font style="color:red;">*</font></strong>
														</td>
														<td align="center" width="23%">
															<strong><spring:message htmlEscape="true" code="Label.NILE"></spring:message>&nbsp;<font style="color:red;">*</font></strong>
														</td>
														<td align="center" width="15%">
															<strong>Sub Type&nbsp;</strong>
														</td>
													</tr>
													
													<c:if test="${!empty localBodyTypesRural}">
														<c:forEach var="localBodyTypesRural" varStatus="row" items="${localBodyTypesRural}">
															
																   
																
															<tr class="tblRowB" >
																
																<td align="center"> 
																	<input type="checkbox" name="check" id="${localBodyTypesRural[0]}" value="<c:out value='${localBodyTypesRural[0]}' escapeXml='true'></c:out>" onclick="checkbox_event(this);" 
																	<c:forEach var="preselected" items="${preSelectedLocalBodyTypes}">
																		<c:if test="${preselected[0] eq localBodyTypesRural[0]}">
																			checked="checked" disabled="disabled"
																		</c:if>
																	</c:forEach>	
																	/>
																</td>
																<td>
																	<c:out value="${localBodyTypesRural[1]}" escapeXml="true"/>
																	<input type="hidden" id="lbType_${localBodyTypesRural[0]}" value="<c:out value='${localBodyTypesRural[1]}' escapeXml='true'/>"/>
																</td>
																<td >
																	<br/>
																	<input id="nomenEnglish_${localBodyTypesRural[0]}" name="nomenEnglish" maxlength="50" size="40" onchange="enableSubmit();"
																	<c:set var="isdisabled1" value="true"/>
																	<c:forEach var="preselected" items="${preSelectedLocalBodyTypes}">
																		<c:if test="${preselected[0] eq localBodyTypesRural[0]}">
																			value="${preselected[1]}"
																			<c:set var="isdisabled1" value="false"/>
																		</c:if>
																	</c:forEach>
																	<c:if test="${isdisabled1}">disabled="disabled"</c:if>
																	/> 
																	<div id="errorEnglish_${localBodyTypesRural[0]}" class="errormsg"></div> 
																</td>
																<td>
																	<br/>
																	<input id="nomenLocal_${localBodyTypesRural[0]}" name="nomenLocal" maxlength="50" size="40" onchange="enableSubmit();"
																	<c:forEach var="preselected" items="${preSelectedLocalBodyTypes}">
																		<c:if test="${preselected[0] eq localBodyTypesRural[0]}">
																			value="${preselected[2]}"
																		</c:if>
																	</c:forEach>
																	<c:if test="${isdisabled1}">disabled="disabled"</c:if>
																	/> 
																	<div id="errorLocal_${localBodyTypesRural[0]}" class="errormsg"></div>
																</td>
																<td>
																	<br/>
																	<table   cellpadding="0" cellspacing="0" id="tblsubtype_${localBodyTypesRural[0]}">
																		<tr style="height:15px;">
																			<td >
																				<input id="sdubtype_${localBodyTypesRural[0]}" name="subTypeNames_${localBodyTypesRural[0]}" value="" maxlength="50" disabled="disabled"/>		
																			</td>
																			<td >
																				<input id="sdubtypeLocal_${localBodyTypesRural[0]}" name="subTypeLocalNames_${localBodyTypesRural[0]}" value="" maxlength="50" disabled="disabled"/>		
																			</td>
																			
																			<td>
																				<img id="plus_${localBodyTypesRural[0]}" src="<%=contextpthval%>/images/plus.png" alt="" height="13" width="13" 
																				style="display: none;" onclick="addSubtypesRow('${localBodyTypesRural[0]}')"/>		
																			</td>
																		</tr>
																	</table>
																	<div id="errorEnglish" class="errormsg"></div>
																</td>
																<td style="display: none;">
																	<input type="hidden" id="level_${localBodyTypesRural[0]}" name="level" value="<c:out value='${localBodyTypesRural[2]}' escapeXml='true'></c:out>"/> 
																</td>
															</tr>
															
															
															
														</c:forEach>
													</c:if>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<input type="button" id="save" class="bttn" value="Define New Rural Setup" disabled="disabled" onclick="return submitDetails('add');"/>
												<input type="button" id="modify" class="bttn" value="Modify Rural Setup" disabled="disabled" onclick="return submitDetails('modify');"/>
												<input type="button" id="cancel" class="bttn" value=" Back " onclick="javascript:window.location='home.htm'"/>
											</td>
										</tr>
										<tr>
											<td align="center">
												<div id="errorProcess" class="errormsg"></div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>	
					</div></td>
				</tr>
			</table>
		</div>
	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
<div id="dialog-message" title="Operational Message"><c:out value="${successmsg}" escapeXml="true"></c:out></div>
</body>
</html>
