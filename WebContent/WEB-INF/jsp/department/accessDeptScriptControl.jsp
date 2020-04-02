<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
	<%@include file="../common/taglib_includes.jsp"%>
	<script src="<%=contextPath%>/css_js_files/bs_files/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css_js_files/bs_files/css/bootstrap.min.css">
	<script src="<%=contextPath%>/css_js_files/bs_select/js/bootstrap-select.min.js"></script>
	<script src="<%=contextPath%>/css_js_files/bs_select/js/jquery.search.min.js"></script>
	<link rel="stylesheet" href="<%=contextPath%>/css_js_files/bs_select/css/bootstrap-select.min.css" />
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
	<script src="<%=contextPath%>/css_js_files/msg_box/scripts/jquery.msgBox.js" type="text/javascript"></script>
	<link href="<%=contextPath%>/css_js_files/msg_box/styles/msgBoxLight.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.extab{
			margin: 0px 0px -29px 0px;		
		}
		*{
			box-sizing: content-box;
		}
		#mainnav_logout{
			padding: 1px 35px 1px 3px;
		}
	</style>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><h5>Generate Department/Organization Script</h5></td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr" style="height:380px;">
			<div class="row">
				<div class="form-group">
					<div class="col-sm-4"><div class="col-sm-12">
						<b><spring:message code="Label.SELOrganization" text="Select Organization" htmlEscape="true"></spring:message></b>
					</div></div>
					<div class="col-sm-3"><div class="col-sm-12 levellabel">
						<label class="control-label">Script Generation For</label>
					</div></div>
					<div class="col-sm-5"><div class="col-sm-12 scriptactionlabel">
						<label class="control-label">Action</label>
					</div></div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-sm-4"><div class="col-sm-12">
						<select id="org" name="org" class="selectpicker  show-tick orgListDD" data-show-subtext="true" data-live-search="true"  style="width:70%;height:30%;">
							<option value="0"><spring:message code="Label.SELECT" text="-slect"></spring:message></option>
								<c:forEach var="obj" items="${organizationList}">
									<option value='<c:out value="${obj.olc}"/>'><c:out value="${obj.orgName}"/></option>
								</c:forEach>
						</select>
					</div></div>
					<div class="col-sm-3"><div class="col-sm-12 levelContainer">
						<div class="input-group" style="width:50%;">
							<span class="input-group-addon">
								<input type="radio" id="org" name="organization">
							</span>
							<input type="text" class="form-control" name="Organization" value="Organization" readonly="readonly">
						</div><br/>
						<div class="input-group" style="width:50%;">
							<span class="input-group-addon">
								<input type="radio" id="desig" name="designation">
							</span>
							<input type="text" class="form-control" name="Designation" value="Designation" readonly="readonly">
						</div><br/>
						<div class="input-group" style="width:50%;">
							<span class="input-group-addon">
								<input type="radio" id="ounit" name="org_unit">
							</span>
							<input type="text" class="form-control" name="OrgUnit" value="OrgUnit" readonly="readonly">
						</div><br/><br/><br/>
					</div></div>
					<div class="col-sm-5"><div class="col-sm-12 scriptAccessDiv">
						<button type="button" class="btn btn-info insertScriptBtn">Generate Insert Script</button>
						<button type="button" class="btn btn-info updateScriptBtn">Generate Update Script</button>
					</div></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
	var opVal;
	$('document').ready(function(){
		$('.scriptAccessDiv').hide();
		$('.levelContainer').hide();
		$('.levellabel').hide();
		$('.scriptactionlabel').hide();
	});
	$('.orgListDD').on('change',function(){
		if($(this).val() != '0'){
			$('.scriptAccessDiv').show();
			$('.levelContainer').show();
			$('.levellabel').show();
			$('.scriptactionlabel').show();
		}else{
			$('.scriptAccessDiv').hide();
			$('.levelContainer').hide();
			$('.levellabel').hide();
			$('.scriptactionlabel').hide();
		}
	});
	$(document).ready(function(){
		$('input[type=radio]').change(function(){
			$('input[type=radio]:checked').not(this).prop('checked',false);
		});
	});
	$('.insertScriptBtn , .updateScriptBtn').on('click',function(){
		var selectedOrgVal=$('#org option:selected').val();
		if($('input[type=radio]:checked').length == 0){
			alert("Please select a Level");
			return;
		}else{
			opVal=$(this).html().substring(9,15);
			lgdAdminDepatmentDwr.checkOrgUnit(selectedOrgVal, {
				callback : checkOrgUnitCallback,
			});
		}
	});
	function checkOrgUnitCallback(data) {
		var msg1="",msg2="",messageBoxVal="";
		var levelSelectedName=$('input[type=radio]:checked').attr('name');
		var selectedOrgVal=$('#org option:selected').val();
		if(data.length > 0){
			if(data[1] != null){
				msg1=data[0];
				msg2=data[1];
			}else{
				msg1=data[0];
			}
			if(data[0] == '' && data[1] == ''){
				messageBoxVal="Do you want to generate the "+opVal+" script ?";
			}else{
				messageBoxVal=msg1+"<br/>"+msg2+"<br/><br/>"+"Do you still want to generate the "+opVal+" script ?";
			}
			$.msgBox({
				title: "Are You Sure",
				content: messageBoxVal,
				type: "confirm",
				buttons: [{ value: "Yes" }, { value: "Cancel"}],
				success: function (result) {
					if (result == "Yes") {
						lgdAdminDepatmentDwr.invokeScriptCall(levelSelectedName,opVal,selectedOrgVal, {
							callback : invokedScriptResultCallback,
						});
					}
				}
			});
		}
		$('input[type=radio]:checked').prop('checked',false);
	}
	function invokedScriptResultCallback(data){
		if(data){
			$.msgBox({
				title:"Message Info",
				content:opVal+" script generated successfully...You will get within one hour.",
				type:"info"
			});
		}else{
			$.msgBox({
				title: "Ooops",
				content: "Error occurred in the execution of "+opVal +"script!!!",
				type: "error",
				buttons: [{ value: "Ok" }]
			});
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</script>
