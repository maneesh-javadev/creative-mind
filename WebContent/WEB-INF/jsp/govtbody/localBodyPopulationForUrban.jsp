<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ include file="lBFreezeJs.jsp"%>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrStateDistrictFreezeService.js'></script>

<script type="text/javascript" src="<%=contextPath%>/dwr/interface/lgdDwrVillageService.js"></script>

<script src="resource-tree/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource-tree/jquerysctipttop.css">

<style type="text/css">
 input[type='text']{
	text-align: right;
} 
</style>
<script type="text/javascript">
function getFreezeLocalbodybyState(){
	var districtCode=0;
	if('${districtUser}'){
		districtCode='${districtCode}';
	}
	var str = $("#entity").val();
	var res = str.split(":");
	var parantLocal=$("#interLevel").val();
	if(validateForm()){
		lgdDwrStateDistrictFreezeService.getFreezeLocalbodybyStatePopulation(parseInt(0),parseInt(res[1]),parseInt(res[2]),parseInt(0),callBackData);
	}else{
		$("#tableId").hide();
		$("#save").hide();
		checkForNotSelectedValues();
	}
}

function callBackData(data){
		if(data.length > 0){
		 	$("#subdistrict").DataTable().destroy();
			$("#subdistrict tbody tr").remove();
			var subdistrict=$("#subdistrict tbody");
			
			var len=data.length;
			var count = 1;
			for(i=0;i<len;i++){
				
				var cls;
				if(i%2==0){
					cls="odd";
				} else if(i%2==1){
					cls="even";
				}
				var popul =data[i].population;
				var population="";
				if(popul!=null){
					population=popul;
				}
				var tr=$("<tr class='"+cls+"' style='line-height:50px;'/> ");
				var td0=$("<td>"+count+"</td>");
				var td1=$("<td>"+data[i].localBodyCode+"</td>");
				var td2=$("<td>"+data[i].localBodyName+"</td>");
				var td3=$("<td style='width: 40%'>"+data[i].localBodyCoverage+"</td>");
				var populationCount = 0;
				if(data[i].population != null)
					populationCount = data[i].population;
				var td4=$("<td style='width: 10%'><input type='number' min='0' style='text-align: center' name='lbFreezeUnfreezes["+i+"].population' placeholder='No. Of Population' value="+populationCount+"><input type='hidden' name='lbFreezeUnfreezes["+i+"].lblc' value="+data[i].lblc+"></td>");
				tr.append(td0).append(td1).append(td2).append(td3).append(td4);
				subdistrict.append(tr);
				count++;
			}
			
			$('#subdistrict').dataTable( {
				"bJQueryUI": false,
				"order": [[ 2, "asc" ]],
				columnDefs: [ { orderable: false, targets: [4] } ]
			});
			
			$("#subdistrict_wrapper").attr("style","width:100%");
			$("#tableId").show();
			$("#save").show();
		}
}

function validateForm(){
	var localBodyType = $("#entity").val();
	if(localBodyType == 0){
		return false;
	}return true;
}

function checkForNotSelectedValues(){
	var localBodyType = $("#entity").val();
	if(localBodyType == 0){
		$('#errorentity').html("Please select local body type")
	} else{
		$('#errorentity').html("");
	}
}

$(document).ready(function() {
	$("#tableId").hide();
	$("#save").hide();
});

</script>



</head>
		<body>
			<section class="content">
				<div class="row">
					<section class="col-lg-12">
							<!-- <div class="header">
								<h3>Local Body Population For Urban</h3>
							</div> -->
						<div class="box">
							<form class="form-horizontal">
								<div class="box-header with-border">
									<h3 class="box-title"><spring:message text="Local Body Population For Urban" code="" htmlEscape="true"></spring:message></h3>
								</div><!-- /.box-header -->
								
								<div class="form-group">
									<label  class="col-sm-3 control-label" for="entity">
										<spring:message text="Select Local Body Type" code="Label.LBType" htmlEscape="true"></spring:message>
											<span class="mandatory">*</span>
									</label>
									<div class="col-sm-6">
								  		<select class="form-control" varStatus="index" id="entity" onchange="showHideBlock(this.value);">
								  			<option value="0"><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
											<c:forEach var="getLocalGovtSetupList" items="${getLocalGovtSetupList}" varStatus="index">
												<option value="${getLocalGovtSetupList.nomenclatureEnglish}:${getLocalGovtSetupList.localBodyTypeCode}:${stateCode}" selected="selected">
													<c:out value="${getLocalGovtSetupList.localBodyTypeName}" escapeXml="true"></c:out></option>
											</c:forEach>
										</select>
							  		</div>
							  		<div id="errorentity" style="color: red;"></div>
								</div>
								
								<%-- <div align="center">
									<button type="button" class="btn btn-success " id="createhabitation" onclick="getFreezeLocalbodybyState();">
										<i class="fa fa-floppy-o"></i> 
									<c:out value='Get Data' />
									</button>
								</div> --%>
								
								<div class="box-footer">
								   <div class="col-sm-offset-2 col-sm-10">
									  <div class="pull-right">
										<button type="button" class="btn btn-success " id="createhabitation" onclick="getFreezeLocalbodybyState();">
											<i class="fa fa-floppy-o"></i> 
										<c:out value='Get Data' />
										</button>
										<button style="width: 80px;" type="button" name="Clear" class="btn btn-warning" id="btnClear" onclick="clearFields();">
											<i class="fa fa-times-circle"></i> 
											<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>
										</button>
										<button style="width: 80px;" type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';">
											<i class="fa fa-times-circle"></i> 
											<spring:message code="Button.CLOSE" htmlEscape="true">
											</spring:message>
										</button>
									  </div>
									</div>
								</div>
							</form>
							
							<form:form  method="POST" action="localBodyPopulationForUrban.htm" commandName="freezeForm">
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="localBodyPopulationForUrban.htm"/>"/>
								<div id="tableId">
									<table id="subdistrict" class="data_grid" cellspacing="0" style="width: 95%; margin-left: 3%;">
										<thead>
											<tr>
												<th style="text-align: center;">S.No.</th>
												<th style="text-align: center;">Local Body Code</th>
												<th style="text-align: center;">Local Body Name(IN ENGLISH)</th>
												<th style="width: 70%;text-align: center;">Local Body Coverage</th>
												<th style="text-align: center;">Population</th>
											</tr>
										</thead>
										<tbody>
							
										</tbody>
									</table>
								</div>
								<div align="center" id="">
									<button type="submit" class="btn btn-success " id="save">
										<i class="fa fa-floppy-o"></i> 
									<c:out value='Save' />
									</button>
								</div>
							</form:form>
							
						</div>
					</section>
				</div>
			</section>
		</body>
</html>