<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<head>
<style type="text/css">
.redborder {
	border: 1px solid red;
}
</style>
<script type="text/javascript">var cPath="<%=contextpthval%>";</script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDesignationDwr.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type="text/javascript" language="javascript">

	var designationList = null;
	function getDesignations() {
		var olc = '${olc}';
		var orgLocatedLevelCode = '${orgLocatedLevelCode}';
		lgdDesignationDwr.getDepartmentDesignationsForReorder(
				parseInt(olc), parseInt(orgLocatedLevelCode),{
					async : true,
					callback : getExistingDesignations,
					errorHandler : function(){alert("Error in getting Deignations");}//handleDesignationError
				});
	}
	function getExistingDesignations(data) {
		designationList = data
		var desigCode = 0;
		var desigName = "";
		var desigNameLocal = "";
		var isMultiple = false;
		var isContractPermanet = false;
		if (designationList.length == 0) {
			alert("No Designation Details found for selected Organization");
			document.getElementById('submit').disabled = true;
			} else {
			for ( var i = 0; i < designationList.length; i++) {
				desigCode = designationList[i].designationCode;
				desigName = designationList[i].designationName;
				desigNameLocal = designationList[i].designationNameLocal;
				isMultiple = designationList[i].isMultiple;
				isContractPermanet = designationList[i].isContractPermanent;
				addTableRow(desigCode,desigName,desigNameLocal,isMultiple,isContractPermanet);	
			}
		}
	}
	
	function addTableRow(desigId, desigName, desigNameLocal, isMulti,
			isContrctPrmt) {

		var tbl = document.getElementById('designation');
		var lastRow = tbl.rows.length;
		var iteration = lastRow;
		var row = tbl.insertRow(lastRow);
		row.setAttribute('style', 'height:75px;');

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

		var cellRight2 = row.insertCell(2);
		var el = document.createElement('input');
		el.type = 'checkbox';
		el.id = 'isMultiple' + iteration;
		el.name = 'isMultiple';
		if (isMulti != null) {
			el.checked = isMulti;
		}
		cellRight2.appendChild(el);
		cellRight2.setAttribute('align', 'center');

		var cellRightcp = row.insertCell(3);
		var el = document.createElement('input');
		el.type = 'checkbox';
		el.id = 'isContrctPermanent' + iteration;
		el.name = 'isContrctPermanent';
		if (isContrctPrmt != null) {
			el.checked = isContrctPrmt;
		}
		cellRightcp.appendChild(el);
		cellRightcp.setAttribute('align', 'center');

		var cellRight2 = row.insertCell(4);
		var el = document.createElement('input');
		el.type = 'hidden';
		el.id = 'desigCode' + iteration;
		el.name = 'desigCode';
		if (desigId != null) {
			el.value = desigId;
		}
		cellRight2.appendChild(el);

		 
		var cellRightasc = row.insertCell(5);
				
		var el = document.createElement('img');
		el.src = cPath + "/images/sort_asc.png";
		el.onclick = function() {
			var row = $(this).parents("tr:first");
			row.insertBefore(row.prev());
			setPriorityImg();
		};
		cellRightasc.appendChild(el);

		var cellRightdesc = row.insertCell(6);
		var el = document.createElement('img');
		el.src = cPath + "/images/sort_desc.png";
		el.onclick = function() {
			var row = $(this).parents("tr:first");
			row.insertAfter(row.next());
			setPriorityImg();
		};
		cellRightdesc.appendChild(el);
		setPriorityImg();
		
		document.getElementById('desgName'+ iteration).readOnly = true;
		document.getElementById('desgNameLocal'+ iteration).readOnly = true;
		document.getElementById('isMultiple'+ iteration).disabled = true;
		document.getElementById('isContrctPermanent'+ iteration).disabled = true;
		document.getElementById('desigCode'+ iteration).readOnly = true;
	}
	function setPriorityImg() {
		var retrievetable = document.getElementById('designation');
		var w = 1;
		for ( var i = 1; i <= retrievetable.rows.length - 1; i++) {

			if (retrievetable.rows[i].cells[5].childNodes[1] == null) {
				w = 0;
			} else {
				w = 1;
			}
			if (i == 1) {

				retrievetable.rows[i].cells[5].childNodes[w].style.display = "none";
			} else {
				retrievetable.rows[i].cells[5].childNodes[w].style.display = "inline";
			}

			if (i == retrievetable.rows.length - 1) {
				retrievetable.rows[i].cells[6].childNodes[w].style.display = "none";
			} else {
				retrievetable.rows[i].cells[6].childNodes[w].style.display = "inline";
			}

		}
	}
	function submitForm()
	{
		var desMultiples = document.getElementsByName('isMultiple');
		var desContrctPermas = document.getElementsByName('isContrctPermanent');
		var desCodes = document.getElementsByName('desigCode');
		var len = desCodes.length;
		var designationDetails = "";
		if((desMultiples[0].checked) || (!desContrctPermas[0].checked) )
		{
		alert('The selected designation cannot be a top designation as it is either shared by multiple officials or is not a regular post');
		return false;
		}
		
		for ( var i = 0; i < len; i++) {
			
			designationDetails += desCodes[i].value + "##";
			
		}

		document.getElementById('designations').value = designationDetails
				.substring(0, designationDetails.length - 2);
		return true;
	}
</script>
</head>

<body onload="getDesignations()">
	<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
            <form:form action="save_designation_department_reorder.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="save_designation_department_reorder.htm"/>" />
				<form:hidden path="designationType" />
				<form:hidden path="olc" id="olc" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true" code="Label.ReorderOrgDesig"></spring:message></h3>
                    </div>
                     <div class="box-header subheading">
								<h4><spring:message htmlEscape="true" code="Label.desigReorder" text="Reorder Designations for Organization Located Level "/><c:out value= "${orgSpecificName}" escapeXml="true"></c:out></h4>
							</div>
                     
                    <div class="box-body">
                   <div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
								<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
									<label id="errorCommon"><form:errors path="*" cssClass="errorBox"></form:errors></<label>
								</div>
		           </div>
                    
                    <table class="table table-bordered table-hover" id="designation">
                    <tbody>
                          
                     <tr>
						<td ><Label><spring:message htmlEscape="true" code="Label.DESIGNATIONNAMEENGLISH"></spring:message></Label></td>
						<td ><Label><spring:message htmlEscape="true" code="Label.DESIGNATIONNAMELOCAL"></spring:message></Label></td>
					    <td ><Label><spring:message htmlEscape="true" code="Label.ISMULTI"></spring:message></Label></td>
						<td ><Label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></Label></td>
						<td  colspan="3" ><b><spring:message code="label.rearrange" text="Rearrangement"/></b></td>
					</tr>
                  </tbody>
               </table>
              </div>
              
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			          <input type="hidden" value="" name="designations" id="designations" /> 
			          <input type="hidden" value="<c:out value='${orgLocatedLevelCode}' escapeXml='true'></c:out>" name="orgLocatedLevelCode" id="orgLocatedLevelCode" />
			          <button id="submit" type="submit" name="submit" class="btn btn-success" onclick="return submitForm()"><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message> </button>
                       <button type="button" class="btn btn-danger" name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>