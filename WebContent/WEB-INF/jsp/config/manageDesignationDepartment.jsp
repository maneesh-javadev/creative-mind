<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<style type="text/css">
.redborder {
	border: 1px solid red;
}
</style>
<script type="text/javascript">var cPath="<%=contextPath%>";
</script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDesignationDwr.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" language="javascript">
	var allLevelList = null;
	var existingDesignationList = null;

	function getLevels() {
		var olc = '${olc}';
		lgdDesignationDwr.getOrgLocatedLevelList(parseInt(olc), {
			async : true,
			callback : function(data) {
				allLevelList = data;
				lgdDesignationDwr.getExistingDesignationsForDepartment(
						parseInt(olc), {
							async : true,
							callback : getExistingDesignations,
							errorHandler : function() {
								alert("Error in getting Deignations");
							}//handleDesignationError
						});
			},
			errorHandler : function() {
				alert("Error in getting Organization Levels");
			}
		});
	}

	function getExistingDesignations(data) {
		existingDesignationList = data
		var desigCode = 0;
		var desigName = "";
		var desigNameLocal = "";
		var desigLevelList = "";
		var isMultiple = false;
		var isContractPermanet = false;
		var isTopDesig = false;
		var flag = 1;
		if (existingDesignationList.length == 0) {
			addNewRow();
		} else {
			for ( var i = 0; i < existingDesignationList.length; i++) {
				flag = 0;
				desigCode = existingDesignationList[i].lgdDesignationPK.designationCode;
				desigName = existingDesignationList[i].designationName;
				desigNameLocal = existingDesignationList[i].designationNameLocal;
				desigLevelList = existingDesignationList[i].designationLevelList;
				isMultiple = existingDesignationList[i].isMultiple;
				isContractPermanet = existingDesignationList[i].isContractPermanent;
				isTopDesig = existingDesignationList[i].isTopDesignation;
				if (flag == 0) {
					addTableRow(desigCode, desigName, desigNameLocal,
							allLevelList, desigLevelList, isMultiple,
							isContractPermanet, isTopDesig);
					flag = 1;
				}
			}
		}
	}

	function addTableRow(desigId, desigName, desigNameLocal, allLevelList,
			desigLevelList, isMulti, isContrctPrmt, isTopDesig) {

		var tbl = document.getElementById('designation');
		var lastRow = tbl.rows.length;
		var iteration = lastRow;
		var row = tbl.insertRow(lastRow);
		row.setAttribute('style', 'height:80px;');

		var cellRight1 = row.insertCell(0);
		var el = document.createElement('input');
		el.type = 'text';
		el.id = 'desgName' + iteration;
		el.name = 'designationName';
		el.maxLength = 50;
		if (desigName != null) {
			el.value = desigName;
		}
		el.setAttribute('style', 'width:200px;');
		cellRight1.appendChild(el);
		cellRight1.style.verticalAlign = "top";

		var cellRight2 = row.insertCell(1);
		var el = document.createElement('input');
		el.type = 'text';
		el.id = 'desgNameLocal' + iteration;
		el.name = 'designationNameLocal';
		el.maxLength = 60;
		if (desigNameLocal != null) {
			el.value = desigNameLocal;
		}
		el.setAttribute('style', 'width:200px;');
		cellRight2.appendChild(el);
		cellRight2.style.verticalAlign = "top";
		
		var cellRight2 = row.insertCell(2);
		var el = document.createElement('select');
		el.multiple = "multiple";
		for ( var i = 0; i < allLevelList.length; i++) {
			var value = allLevelList[i].orgLocatedLevelCode;
			var text = allLevelList[i].orgLevelSpecificName;
			if (desigLevelList != null) {
				if (value >= 0)
					addOption(el, text, value, desigLevelList);
			} else {
				addOptions(el, text, value);
			}
		}
		el.id = 'locatedLevelCode' + iteration;
		el.name = 'locatedLevelCode';

		el.setAttribute('style', 'width:300px;');
		cellRight2.appendChild(el);
		cellRight2.style.verticalAlign = "top";

		var cellRight2 = row.insertCell(3);
		var el = document.createElement('input');
		el.type = 'checkbox';
		el.id = 'isMultiple' + iteration;
		el.name = 'isMultiple';
		if (isMulti != null) {
			el.checked = isMulti;
		}
		cellRight2.appendChild(el);
		cellRight2.setAttribute('align', 'center');
		cellRight2.style.verticalAlign = "top";

		var cellRightcp = row.insertCell(4);
		var el = document.createElement('input');
		el.type = 'checkbox';
		el.id = 'isContrctPermanent' + iteration;
		el.name = 'isContrctPermanent';
		if (isContrctPrmt != null) {
			el.checked = isContrctPrmt;
		}
		cellRightcp.appendChild(el);
		cellRightcp.setAttribute('align', 'center');
		cellRightcp.style.verticalAlign = "top";

		var cellRight2 = row.insertCell(5);
		var el = document.createElement('input');
		el.type = 'hidden';
		el.id = 'desigCode' + iteration;
		el.name = 'desigCode';
		if (desigId != null) {
			el.value = desigId;
		}
		cellRight2.appendChild(el);
		cellRight2.style.verticalAlign = "top";

		var cellRightTop = row.insertCell(6);
		var el = document.createElement('input');
		el.type = 'hidden';
		el.id = 'isTopDesign' + iteration;
		el.name = 'isTopDesign';
		if (isTopDesig != null) {
			el.value = isTopDesig;
		}
		cellRightTop.appendChild(el);
		cellRightTop.style.verticalAlign = "top";

		// cell remove button
		var cellRemoveBtn = row.insertCell(7);
		var el = document.createElement('img');
		el.id = iteration;
		el.name = "remove" + iteration;
		el.src = cPath + "/images/cancel.png";
		el.width = "20";
		el.height = "20";
		el.onclick = function() {
			document.getElementById('errorCommon').innerHTML = "";
			document.getElementById('tdError').style.display = "none";
			var designationcode = document
					.getElementById('desigCode' + this.id).value;
			var rownum = this.parentNode.parentNode.rowIndex;
			if (designationcode == "") {
				tbl.deleteRow(rownum);
			} else {
				displayLoadingImage();
				lgdDesignationDwr
						.isDesignationBeingUsed(
								parseInt(designationcode),
								{
									async : true,
									callback : function(data) {
										if (data) {
											document.getElementById('tdError').style.display = "inline";
											document
													.getElementById('errorCommon').innerHTML = "You cannot Modify / Delete this designation. This is being used in reporting !";
										} else {
											if (confirm("Are you sure you want to remove this designation?")) {
												lgdDesignationDwr
														.updateIsActive(parseInt(designationcode));
												tbl.deleteRow(rownum);
											}
										}
										hideLoadingImage();
									}
								});
			}
		};
		cellRemoveBtn.appendChild(el);
		cellRemoveBtn.style.verticalAlign = "top";

		if (desigId != null) {
			lgdDesignationDwr
					.isDesignationBeingUsed(
							parseInt(desigId),
							{
								async : true,
								callback : function(data) {
									if (data) {
										document.getElementById('desgName'
												+ iteration).readOnly = true;
										document.getElementById('desgNameLocal'
												+ iteration).readOnly = true;
										document
												.getElementById('locatedLevelCode'
														+ iteration).disabled = true;
										document.getElementById('isMultiple'
												+ iteration).disabled = true;
										document
												.getElementById('isContrctPermanent'
														+ iteration).disabled = true;
										document.getElementById('desigCode'
												+ iteration).readOnly = true;
										document.getElementById('isTopDesign'
												+ iteration).readOnly = true;
									}
								}
							});
		}

	}

	function addOption(selectbox, text, value, desigLevelList) {
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		for ( var k = 0; k < desigLevelList.length; k++) {
			var obj = desigLevelList[k];
			if (value == obj.orgLocatedLevelCode && obj.isActive) {
				optn.selected = "selected";
			}
			selectbox.options.add(optn);
		}
	}

	function addOptions(selectbox, text, value) {
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}
	function addNewRow() {
		var olc = '${olc}';
		lgdDesignationDwr.getOrgLocatedLevelList(parseInt(olc),
				{
					async : true,
					callback : function(data) {
						var levelList = data;
						addTableRow(null, null, null, levelList, null, null,
								null, null);
					}
				});
	}

	function submitForm() {
		var levelIds = [];
		$($("[name=locatedLevelCode]")).each(function(index, obj) {
			levelIds.push({
				values : $(obj).val()
			});
		});
		levelIds = $.makeArray(levelIds);

		var desName = document.getElementsByName('designationName');
		var desNameLocals = document.getElementsByName('designationNameLocal');
		var desMultiples = document.getElementsByName('isMultiple');
		var desContrctPermas = document.getElementsByName('isContrctPermanent');
		var desCodes = document.getElementsByName('desigCode');
		var isTopDesigns = document.getElementsByName('isTopDesign');
		var len = desName.length;
		var designationDetails = "";
		for ( var i = 0; i < len; i++) {
			var designationName = desName[i].value;
			var desigNameLocal = desNameLocals[i].value;
			if (fullTrim(designationName) != "") {
				if (!validateAlphabetWithSpaceDotBrackets(designationName)) {
					alert('You have entered an invalid Character in Designation Name(In English) !');
					document.getElementById(desName[i].id).focus();
					return false;
				}
			} else {
				alert('Please Enter Designation Name(In English) !');
				document.getElementById(desName[i].id).focus();
				return false;
			}
			if (fullTrim(desNameLocals[i].value) != "") {
				if (!validateSpecialCharactersWithHyphen(desNameLocals[i].value)) {
					alert('You have entered an invalid Character in Designation Name(In Local) !');
					document.getElementById(desNameLocals[i].id).focus();
					return false;
				}
			} else {
				alert('Please Enter Designation Name(In Local)!');
				document.getElementById(desNameLocals[i].id).focus();
				return false;
			}
			for ( var j = 0; j < len; j++) {
				if (i != j) {
					if (fullTrim(designationName) == fullTrim(desName[j].value)) {
						alert('Designation Name already referenced, Please change Designation Name(In English) !');
						document.getElementById(desName[j].id).focus();
						return false;
					}
				}
			}
			for ( var j = 0; j < len; j++) {
				if (i != j) {
					if (fullTrim(desigNameLocal) == fullTrim(desNameLocals[j].value)) {
						alert('Designation Name already referenced, Please change Designation Name(In Local) !');
						document.getElementById(desNameLocals[j].id).focus();
						return false;
					}
				}
			}
			if (($.map(levelIds[i], function(value) {
				return value;
			})) == "") {
				alert('Please Select organization levels at which designation exist !');
				return false;
			}

			if (isTopDesigns[i].value == "true" && desMultiples[i].checked) {
				alert('Top Designation should not be held by multiple officials');
				document.getElementById(desName[i].id).focus();
				return false;
			}
			if (isTopDesigns[i].value == "true" && !desContrctPermas[i].checked) {
				alert('Top Designation  should be a regular post');
				document.getElementById(desName[i].id).focus();
				return false;
			}
			designationDetails += desCodes[i].value + "##";
			designationDetails += designationName + "##";
			designationDetails += desNameLocals[i].value + "##";
			designationDetails += $.map(levelIds[i], function(value) {
				return value;
			}) + "##";
			designationDetails += desMultiples[i].checked + "##";
			designationDetails += desContrctPermas[i].checked + "@@";
		}

		document.getElementById('designations').value = designationDetails
				.substring(0, designationDetails.length - 2);
		return true;
	}
</script>
</head>

<body onload="getLevels()">
	<div id="frmcontent">


		<div class="frmhd">

			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.CREATEDESIGNATIONSTATE"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">
<!-- 				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li>
 -->				<%-- //these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message> </a></li>
 --%>
			</ul>


		</div>



		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="save_designation_department.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="save_designation_department.htm"/>" />
				<form:hidden path="designationType" />
				<form:hidden path="olc" id="olc" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<%-- <spring:message htmlEscape="true" code="Label.OSL" /> --%>
							</div>
							<div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
								<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
									<label id="errorCommon"><form:errors path="*" cssClass="errorBox"></form:errors></<label>
								</div>
							</div>
							<table width="100%">
								<tr>
									<td><span style="color: red;">NOTE : If the designation is being used by other applications, you cannot modify / delete that designation.</span></td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>
										<table width="100%" class="tbl_no_brdr" id="designation">
											<tr>
												<td align="left" width="25%" height="60px"><Label><spring:message htmlEscape="true" code="Label.DESIGNATIONNAMEENGLISH"></spring:message></Label></td>
												<td align="left" width="30%" height="60px"><Label><spring:message htmlEscape="true" code="Label.DESIGNATIONNAMELOCAL"></spring:message></Label></td>
												<td align="left" width="15%" height="60px"><Label><spring:message htmlEscape="true" code="Label.ORGLEVEL"></spring:message></Label></td>
												<td align="center" width="20%" height="60px"><Label><spring:message htmlEscape="true" code="Label.ISMULTI"></spring:message></Label></td>
												<td align="center" width="20%" height="60px"><Label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></Label></td>
												<td colspan="3" height="60px"><img src="<%=contextPath%>/images/add.png" border="0" height="22" width="20" onclick="addNewRow();" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
		</div>
	</div>
	<div class="btnpnl">
		<ul class="listing">
			<li>
			<input type="hidden" value="" name="designations" id="designations" /> 
			<input id="submit" type="submit" name="submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return submitForm()" /> 
			<input type="button" name="Submit33" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'" />
			</li>
		</ul>
	<div>
	</div>
	</form:form>
	</div>
	</div>
</body>
</html>