<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script
	src="<%=contextPath%>/css_js_files/msg_box/scripts/jquery.msgBox.js"
	type="text/javascript"></script>
	<script type='text/javascript' src="js/notie/notie.js"></script>
<script type='text/javascript' src="js/notie/notie.min.js"></script>

<link rel="stylesheet" href="js/notie/notie.css">
<link rel="stylesheet" href="js/notie/notie.min.css">
<link
	href="<%=contextPath%>/css_js_files/msg_box/styles/msgBoxLight.css"
	rel="stylesheet" type="text/css">
	<!--  <style type="text/css">
		.extab{
			margin: 0px 0px -29px 0px;		
		}
		*{
			box-sizing: content-box;
		}
		#mainnav_logout{
			padding: 1px 35px 1px 3px;
		}  -->
	</style>
	<script>
	
	$(document).ready(function() {
		 $('#myModal').modal('hide');
	});
	function alertboxbtnYES() {
		 var optionval =  $("#tId option:selected").text();
		    document.entityTransactionsNew.method = "post";
			document.entityTransactionsNew.action = "accessDepartmentScript.htm?<csrf:token uri='accessDepartmentScript.htm'/>&optionval="+optionval;
			document.entityTransactionsNew.submit();
 
	}

	function alertboxbtnNO() {

		$('#myModal').modal('hide');
		return false;

	}
	
     function showResult(){
    	 var errorFlag =0;
    	 
    	 if ($('#tId > option:selected').val() != ''
				&& parseInt($('#tId > option:selected').val()) > 0) {
    		 $('#myModal').modal('show');
			errorFlag = 1;
			/* $('#myModal').modal('show'); */
			return true;
		} else {

			notie.alert({ type : 3, text : 'Please Select the  Transaction',	time : 4	});
			return false;
		}
    	/* if(errorFlag == 1){
    		
    	
    	  
    	   document.entityTransactionsNew.method = "post";
			document.entityTransactionsNew.action = "accessDepartmentScript.htm?<csrf:token uri='accessDepartmentScript.htm'/>&optionval="+optionval;
			document.entityTransactionsNew.submit();
    	}
    	else{
    		return false;
    	} */
	}
	</script>
</head>
<body>
	<section class="content">
	<div class="row">
		<section class="col-lg-12">
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">Generate Department/Organization Script</h3>
			</div>

			<div class="box-body">
				<div class="form-group">
					<label class="col-sm-3 control-label"><b><spring:message
								code="Label.SELOrganization" text="Select Organization"
								htmlEscape="true"></spring:message></b></label>
					<div class="col-sm-6">
						<select id="org" name="org" class="form-control orgListDD">
							<option value="0"><spring:message code="Label.SELECT"
									text="-slect"></spring:message></option>
							<c:forEach var="obj" items="${organizationList}">
								<option value='<c:out value="${obj.olc}"/>'><c:out
										value="${obj.orgName}" /></option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
				</div>
				<div class="form-group">
				<br />
				<br />
				
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><b>Script
							Generation For</b></label>
					<div class="col-sm-6">
						<label class="radio-inline"><input type="radio" id="org" class="selOrg"
							name="organization" />Organization</label> <label class="radio-inline"><input
							type="radio" id="desig" class="selOrg" name="designation" />Designation</label> <label
							class="radio-inline"><input type="radio" id="ounit" class="selOrg"
							name="org_unit"/>OrgUnit</label>
					</div>
				</div>
			</div>
			<div class="box-footer">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="pull-right">
						<button type="button" class="btn btn-info insertScriptBtn">Generate Insert Script</button>
						<button type="button" class="btn btn-info updateScriptBtn">Generate Update Script</button>
					</div>
				</div>
			</div>

		</div>
		<div class="form-group">
		<br />
		
		</div>
		
		 <form:form
			class="form-horizontal" 
			name="entityTransactionsNew" method="post" id="entityTransactionsNew" 
			commandName="createDepartment">
			<input type="hidden" name="<csrf:token-name/>"	value="" />
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Generate Script For II Transaction</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label class="col-sm-3 control-label"><b><spring:message
									code="Label.SELOrganization" text="Select Transaction"
									htmlEscape="true"></spring:message></b></label>
						<div class="col-sm-6">
							<select id="tId" name="tId" class="form-control ">
								<option value="0"><spring:message code="Label.SELECT"
										text="-slect"></spring:message></option>
								<c:forEach var="obj" items="${entityTransactionsNews}">
									<option value='<c:out value="${obj.tId}"/>'><c:out
											value="${obj.description}" /></option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			  <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
      
				<div class="form-group">
					<button type="button" onclick="showResult();" class="btn btn btn-info"
						value="" >Generate Script </button>
				</div>
				  </div>
           </div>   
       </div> 
			</div>
		</form:form>
		
		</section>
		<div class="modal fade modal-admin" id="myModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">

						<h4 class="modal-title" id="alertboxTitle">Do you want to Generate or Update script.
							</h4>
					</div>

					<div class="modal-footer">
						<button type="button" onclick="alertboxbtnYES();"
							class="btn btn-success">YES</button>
						<button type="button" onclick="alertboxbtnNO();"
							class="btn btn-danger">NO</button>


					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>
<script>
	var opVal;
	var selectedOrg;
	$('document').ready(function() {
		$('.scriptAccessDiv').hide();
		$('.levelContainer').hide();
		$('.levellabel').hide();
		$('.scriptactionlabel').hide();
	});
	$('.orgListDD').on('change', function() {
		if ($(this).val() != '0') {
			$('.scriptAccessDiv').show();
			$('.levelContainer').show();
			$('.levellabel').show();
			$('.scriptactionlabel').show();
		} else {
			$('.scriptAccessDiv').hide();
			$('.levelContainer').hide();
			$('.levellabel').hide();
			$('.scriptactionlabel').hide();
		}
	});
	$(document).ready(function() {
		$('input[type=radio]').change(function() {
			$('input[type=radio]:checked').not(this).prop('checked', false);
		});
	});
	$('.insertScriptBtn , .updateScriptBtn').on('click', function() {
		var selectedOrgVal = $('#org option:selected').val();
		
		if ($('input[type=radio]:checked').length == 0) {
			alert("Please select a Level");
			return;
		} else {
			selectedOrg = $('input[type=radio]:checked').attr('name');
			opVal = $(this).html().substring(9, 15);
			lgdAdminDepatmentDwr.checkOrgUnit(selectedOrgVal, {
				callback : checkOrgUnitCallback,
			});
		}
	});
	function checkOrgUnitCallback(data) {
		var msg1 = "", msg2 = "", messageBoxVal = "";
		var levelSelectedName = $('input[type=radio]:checked').attr('name');
		var selectedOrgVal = $('#org option:selected').val();
		if (data.length > 0) {
			if (data[1] != null) {
				msg1 = data[0];
				msg2 = data[1];
			} else {
				msg1 = data[0];
			}
			if (data[0] == null && data[1] == null) {
				messageBoxVal = "Do you want to generate the " + opVal
						+ " script ?";
			} else {
				messageBoxVal = msg1 + "<br/>" + msg2 + "<br/><br/>"
						+ "Do you still want to generate the " + opVal
						+ " script ?";
			}
			$.msgBox({
				title : "Are You Sure",
				content : messageBoxVal,
				type : "confirm",
				buttons : [ {
					value : "Yes"
				}, {
					value : "Cancel"
				} ],
				success : function(result) {
					if (result == "Yes") {
						lgdAdminDepatmentDwr.invokeScriptCall(
								levelSelectedName, opVal, selectedOrgVal, {
									callback : invokedScriptResultCallback,
								});
					}
				}
			});
		}
		$('input[type=radio]:checked').prop('checked', false);
	}
	function invokedScriptResultCallback(data) {
		if (data) {
			$
					.msgBox({
						title : "Message Info",
						content : opVal
								+ " script generated successfully...You will get within one hour.",
						type : "info"
					});
		} else {
			$.msgBox({
				title : "Ooops",
				content : "Error occurred in the execution of " + opVal
						+ "script!!!",
				type : "error",
				buttons : [ {
					value : "Ok"
				} ]
			});
		}
	}
</script>
