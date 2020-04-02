<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  
  
  
  
  
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' language="javascript" src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' language="javascript" src='<%=contextPath%>/dwr/util.js'></script>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/lgdDwrVillageService.js"></script>
<script type="text/javascript" src="<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js"></script>
  
<title>Insert title here</title>
</head>

<script type="text/javascript">
var _JS_STATE_CODE = '${stateCode}';
var _JS_DISTRICT_CODE = '${districtId}';

var buildParentwisePanchayatOptions = function (elementNode , localBodyCode){
	dwrRestructuredLocalBodyService.getParentwiseLocalBody(parseInt(localBodyCode), null, null, {
		callback : function(result){
			var options = $("#" + elementNode);
			jQuery.each(result, function(index, obj) {
				var optionText = obj.localBodyNameEnglish;
				var option = $("<option />");
				if (obj.isDisturbed) {
					option.attr("disabled", true);
					optionText = optionText.concat(" <spring:message code='Label.disturblocalbody' htmlEscape='true'/>");
					
				}
				if (obj.isUsed) {
					option.attr("disabled", true);
					optionText = optionText.concat(" <spring:message code='Label.draftlocalbdy' htmlEscape='true'/>");
				}
				option.val(obj.localBodyCode).text(optionText);
				options.append(option);
			});
		},
		async : true
	});	
};

function populateHabitationList(parantCode) {
	lgdDwrVillageService.getHabitationList(parseInt(parantCode),_JS_STATE_CODE,callBackHabitationList);
}

function callBackHabitationList(data){
	 $("#subdistrict").DataTable().destroy();
		$("#subdistrict tbody tr").remove();
		var subdistrict=$("#subdistrict tbody");
		
		var len=data.length;
		for(i=0;i<len;i++){
			var cls;
			if(i%2==0){
				cls="odd";
			} else if(i%2==1){
				cls="even";
			}
			var tr=$("<tr class='"+cls+"' style='line-height:50px;'/> ");
			var td1=$("<td>"+data[i].habitationNameEnglish+"</td>");
			var td2=$("<td>"+data[i].habitationNameLocal+"</td>");
			var td3=$("<td align='center'/>");
			var img=$("<img id ='habitation"+i+"' src='images/edit.png' width='18' height='19' border='0' />");
			img.attr("onclick","getUnMappedVillageForGIS('"+data[i].habitationCode+"','"+data[i].habitationVersion+"')");
			td3.append(img);
			tr.append(td1).append(td2).append(td3);
			subdistrict.append(tr);
		}
		
		$('#subdistrict').DataTable({
			"bJQueryUI": false,
			"order": [[ 1, "asc" ]],
		});
		
		$("#subdistrict_wrapper").attr("style","width:100%");
}

$(document).ready(function() {
	$('#subdistrict').DataTable({
		"bJQueryUI": false,
		"order": [[ 1, "asc" ]]
	});
	$("#subdistrict_wrapper").attr("style","width:100%");
});

function getUnMappedVillageForGIS(habitationCode,habitationVersion){
	$("#habitationCode").val(habitationCode);
	$("#habitationVersion").val(habitationVersion);
	document.habitationForm.method="post";
	document.habitationForm.action="getHabitationDetails.htm?<csrf:token uri='getHabitationDetails.htm'/>";
	document.habitationForm.submit(); 
}

function getSubDistrictandULBList(id) {
	$('#ddSourceSubDistrict option[value != ""]').remove();
	$('#ddSourceVillage option[value != ""]').remove();
	if(isEmptyObject(id))return false;
	$( '#errorddSourceDistrict' ).text("");
	buildSubDistrict(id);
}

function getVillageList(id) {
	$('#ddSourceVillage option[value != ""]').remove();
	if(isEmptyObject(id))return false;
	$( '#errorddSourceSubDistrict' ).text("");
	buildVillage(id);
}


var buildSubDistrict = function(districtCode){
	lgdDwrSubDistrictService.getSubDistrictList(parseInt(districtCode), {
		callback : function( result ) {
			var options = $("#ddSourceSubDistrict"); 
			options.append("<option>Select Sub District</option>>");
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Bauwa"); alert(exception); },
		async : true
	});
};

var buildVillage = function(subdistrictCode){
	lgdDwrVillageService.getVillageList(parseInt(subdistrictCode), {
		callback : function( result ) {
			var options = $("#ddSourceVillage"); 
			options.append("<option>Select Village</option>>");
			jQuery.each(result, function(index, obj) {
				var option = $("<option />");
				option.val(obj.villageCode).text(obj.villageNameEnglish);
				options.append(option);
			});
		},
		errorHandler : function(errorString, exception) { alert(errorString + " Bauwa"); alert(exception); },
		async : true
	});
};
</script>


<body>
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form method="POST" commandName="habitation" name="habitationForm"  id="habitationId" class="form-horizontal">
				<div id="dialogBX" style="display: none;">
					<iframe id="myIframe" width="910" height="650"></iframe>
				</div>
				
				<form:hidden path="habitationCode" id="habitationCode"/>
				
				<form:hidden path="habitationVersion" id="habitationVersion"/>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Modify Habitation</h3>
                                </div>
                                
                   <div class="box-body">
                     
                     	<c:if test="${parantType eq 'V'}">
						<div class="form-group" id="districtli">
									<label class="col-sm-3 control-label"><spring:message  text="Select District"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
							<div class="col-sm-6">
							<form:select htmlEscape="true" path="" id="ddSourceDistrict" Class="form-control" onchange="getSubDistrictandULBList(this.value);" >
									<form:option value="0" htmlEscape="true"><esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SEL"></spring:message></esapi:encodeForHTMLAttribute></form:option>
									<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" htmlEscape="true"/>
							</form:select>
							<span class="errormessage" id="errorddSourceDistrict"></span>
							</div>
						</div>
									
						<div class="form-group" id="subdistrictli">
									<label class="col-sm-3 control-label"><spring:message text="Select Sub District" htmlEscape="true"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
							<div class="col-sm-6">
							<form:select htmlEscape="true" path=""  id="ddSourceSubDistrict" Class="form-control" onchange="getVillageList(this.value);">
							<form:option value="0" htmlEscape="true"><esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SEL"></spring:message></esapi:encodeForHTMLAttribute></form:option>
							</form:select>
							<span class="errormessage" id="errorddSourceSubDistrict"></span>
							</div>
						</div>
									
						<div class="form-group" id="villagelist">
									<label class="col-sm-3 control-label"><spring:message text="Select Village" htmlEscape="true"></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
							<div class="col-sm-6">
							<form:select htmlEscape="true" path=""  id="ddSourceVillage" Class="form-control" onchange="populateHabitationList(this.value)" >
							<form:option value="0" htmlEscape="true"><esapi:encodeForHTMLAttribute><spring:message htmlEscape="true" code="Label.SEL"></spring:message></esapi:encodeForHTMLAttribute></form:option>
							</form:select>
							<span class="errormessage" id="errorddSourceVillage"></span>
							<form:errors htmlEscape="true" path="parentCode" cssClass="errormsg"/>
						</div>
						</div>
						</c:if>
						
						
					<c:if test="${parantType eq 'G' }">
						<div class="form-group" id="divLgdSelIntermediateP">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true" text="Select District Panchayat" code="Label.VP" ></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
							<div class="col-sm-6">
							<form:select path="" id="ddLgdLBVillageSourceAtVillageCA" class="form-control" onchange="buildParentwisePanchayatOptions('ddlgdLBDistAtInter',this.value);" htmlEscape="true">
								<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
						 		<c:forEach items="${zilaPanchyats}" var="objLBTypes">
									<form:option  value="${objLBTypes.localBodyCode}">
										<c:out value="${objLBTypes.localBodyNameEnglish}" escapeXml="true"></c:out>
									</form:option>
								</c:forEach> 
						   </form:select>
							<div id="err_dpgp" class="errormsg errorr label"></div>
							</div>
						</div>
						
						<div class="form-group" id="divLgdVillageP">
									<label class="col-sm-3 control-label">
									<spring:message htmlEscape="true" text="Select Intermediate Panchyat" ></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
							<div class="col-sm-6">
							<form:select path="" Class="form-control" id="ddlgdLBDistAtInter" onchange="buildParentwisePanchayatOptions('ddlgdLBDistAtVillage',this.value)" htmlEscape="true">
							 <form:option value="0" htmlEscape="true">
								<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></esapi:encodeForHTMLAttribute>
							</form:option> 
							<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
							</form:select>
							<div id="err_ipgp" class="errormsg errorr label"></div>
							</div>
						</div>
						<div class="form-group" id="Villageli">
									<label class="col-sm-3 control-label">
									<spring:message htmlEscape="true" text="Select Gram Panchayat" ></spring:message><strong><span id="required" class="errormsg">*</span> </strong></label>
							<div class="col-sm-6">
							<form:select path="" Class="form-control" id="ddlgdLBDistAtVillage" onchange="populateHabitationList(this.value)" htmlEscape="true">
							 <form:option value="0" htmlEscape="true">
								<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></esapi:encodeForHTMLAttribute>
							</form:option> 
							<%-- <form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> --%>
							</form:select>
							<div id="err_vpgp" class="errormsg errorr label"></div>
							<form:errors htmlEscape="true" path="parentCode" cssClass="errormsg"/>
							</div>
						</div>
					</c:if>
						
			<table id="subdistrict" style="width: 100%" class="table table-bordered table-hover" cellspacing="0">
					<thead>
						<tr>
							<th align="left" style="width: 40%">Habitation Name English</th>
							<th align="left">Habitation Name Local</th>
							<th align="left">View</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
                      	
											
                      </div>
                   
          
     

       
       
       
       
       
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>