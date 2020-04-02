<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="js/configmap.js"></script>
<script type="text/javascript">
	function go(url) {
		window.location = url;
	}
	function toggleURL(obj, val) {
		var pos = obj.id.split('_')[1];
		if (val == true) {

			document.getElementById('baseUrl_' + pos).style.visibility = 'hidden';
			document.getElementById('baseUrl_' + pos).style.display = 'none';
			//document.getElementById('lbl_' + pos).style.visibility = 'hidden';
			document.getElementById('errorbaseUrl_' + pos).style.display = 'none';
			document.getElementById('errorbaseUrl_' + pos).style.visibility = 'hidden';
			document.getElementById('baseUrl_' + pos).value = "";

		} else {

			document.getElementById('baseUrl_' + pos).style.visibility = 'visible';
			document.getElementById('baseUrl_' + pos).style.display = 'block';
			//document.getElementById('lbl_' + pos).style.visibility = 'hidden';
			document.getElementById('errorbaseUrl_' + pos).style.display = 'none';
			document.getElementById('errorbaseUrl_' + pos).style.visibility = 'hidden';

			document.getElementById('lbl_' + pos).style.visibility = 'visible';
		}
	}

	function toggleURLAll(val) {

		var setupSize = '<c:out value="${tierSetupSize}"/>'; //document.getElementById('hdntierSetupSize').value;

		for ( var i = 1; i <= setupSize; i++) {
			var mapUploadID = 'mapUpload_' + i;

			var obj = document.getElementById(mapUploadID);

			var pos = obj.id.split('_')[1];

			if (val == true) {
				if (obj.checked) {
					document.getElementById('baseUrl_' + pos).style.visibility = 'hidden';
					document.getElementById('baseUrl_' + pos).style.display = 'none';

					document.getElementById('errorbaseUrl_' + pos).style.display = 'none';
					document.getElementById('errorbaseUrl_' + pos).style.visibility = 'hidden';
					document.getElementById('baseUrl_' + pos).value = "";
				} else {
					document.getElementById('baseUrl_' + pos).style.visibility = 'visible';
					document.getElementById('baseUrl_' + pos).style.display = 'block';
					document.getElementById('errorbaseUrl_' + pos).style.visibility = 'hidden';
				}
			}
		}
	}

	function validAll(val) {
		//alert("validate");
		flag = true;

		var setupSize = document.getElementById('hdntierSetupSize').value;

		for ( var i = 1; i <= setupSize; i++) {
			var mapUploadID = 'mapUpload_' + i;

			var obj = document.getElementById(mapUploadID);
			var patternu;
			patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
			var pos = obj.id.split('_')[1];
			//alert("mid"+val);
			if (val == true) {
				if (obj.checked) {
					document.getElementById('baseUrl_' + pos).style.visibility = 'hidden';
					document.getElementById('baseUrl_' + pos).style.display = 'none';
					document.getElementById('errorbaseUrl_' + pos).style.visibility = 'hidden';
					document.getElementById('errorbaseUrl_' + pos).style.display = 'none';

				} else {
					//alert("me");
					//alert(document.getElementById('baseUrl_' + pos).value);
					if (document.getElementById('baseUrl_' + pos).value == "") {
						//alert("inside");Base URL is Required
						document.getElementById('errorbaseUrl_' + pos).innerHTML = "Base URL is Required";
						document.getElementById('errorbaseUrl_' + pos).style.visibility = 'visible';
						document.getElementById('errorbaseUrl_' + pos).style.display = 'block';

						flag = false;

					} else {
						if (!patternu.test(document.getElementById('baseUrl_' + pos).value)) {
							document.getElementById('errorbaseUrl_' + pos).innerHTML = "Base URL is not proper format";
							document.getElementById('errorbaseUrl_' + pos).style.visibility = 'visible';
							document.getElementById('errorbaseUrl_' + pos).style.display = 'block';
							flag = false;
						} else {
							document.getElementById('errorbaseUrl_' + pos).style.visibility = 'hidden';
							document.getElementById('errorbaseUrl_' + pos).style.display = 'none';
						}

					}

				}
			}
		}
		//alert(flag);
		return flag;

	}

	if (window.addEventListener) {
		window.addEventListener("load", toggleURLAll(true), false);
	} else if (window.attachEvent) {
		window.attachEvent("onload", toggleURLAll(true));
	} else if (window.onLoad) {
		window.onload = toggleURLAll(true);
	}
</script>
</head>
<body>
	<!-- onload="toggleURLAll(true);"> -->
	<!-- onload="ClearAll();" -->
	<div class="clear"></div>
	<div class="frmhd">
		<h3 class="subtitle">
			<spring:message htmlEscape="true" code="Label.CONFIGUREMAPLGDM"></spring:message>
		</h3>
	<%-- //this link is not working <ul id="showhelp" class="listing">
			<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP">TestUrban</spring:message></a></li>
		</ul> --%>

	</div>
	<div class="frmpnlbrdr">
		<form:form action="configMapLGDMmodify.htm" id="form1" method="POST"
			commandName="configMapLGDMForm">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="configMapLGDMmodify.htm"/>" />
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true" code="Label.CONFIGUREMAPLGDM"></spring:message>
					</div>
					<!-- <table width="80%" class="tbl_no_brdr"> -->

					<div class="table col_4">
						<!-- Table starts -->
						<c:forEach var="lstdetail" varStatus="administratorUnitRow"
							items="${configMapLGDMForm.lstdetail}">
							<div class="trow red">
								<div class="td column col1">
									<c:out value="${lstdetail.nomenclature_english}" escapeXml="true"></c:out>
								</div>
								<div class="td column col2">
									<spring:bind
										path="configMapLGDMForm.lstdetail[${administratorUnitRow.index}].ismapupload">
										<input type="radio" 
											name="<c:out value="${status.expression}"/>"
											id="mapUpload_${administratorUnitRow.index+1}" value="true"
											<c:if test="${status.value == true}">checked</c:if>
											onclick="toggleURL(this,true);" />
									</spring:bind>
								
									<spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message>
								</div>
								<div class="td column col3">
									<spring:bind
										path="configMapLGDMForm.lstdetail[${administratorUnitRow.index}].ismapupload">
										<input type="radio"
											id="seperateServer_${administratorUnitRow.index+1}"
											name="<c:out value="${status.expression}" />"
											value="false"
											<c:if test="${status.value == false}">checked</c:if>
											onclick="toggleURL(this,false);" />
									</spring:bind>
								
									<label id="lbl_${administratorUnitRow.index+1}"> <spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message>
									</label>
								</div>
								<div class="td column col4">
									<spring:bind
										path="configMapLGDMForm.lstdetail[${administratorUnitRow.index}].base_url">
										<input type="text" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true"></c:out>"
											id="baseUrl_${administratorUnitRow.index+1}" maxlength="100"
											class="frmfield" />
									</spring:bind>
									<form:hidden path="tierSetupSize" value="${tierSetupSize}" id="hdntierSetupSize" />
									<form:hidden path="tierSetupCode" value="${lstdetail.tier_setup_code}" htmlEscape="true"/>
									<form:hidden path="category" value="${configMapLGDMForm.category}" htmlEscape="true"/>
								</div>
								<!-- <div class="td column col5"> -->
									<div id="errorbaseUrl_${administratorUnitRow.index+1}" style="color: red;"></div>
								<!-- </div> -->
							</div>

						</c:forEach>
					</div>
					<div class="errormsg">
						<form:errors path="base_url" cssClass="error" />
					</div>
				</div>
			</div>
			<div class="btnpnl">
				<label> <input type="submit" id="savebtn" class="btn"
					onclick="return validAll(true);" name="Submit"
					value="<spring:message htmlEscape="true"  code="Button.UPDATE"></spring:message>" />
				</label> <label> <input type="button" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
				</label>
			</div>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
		<script>
			toggleURLAll(true);
		</script>
	</div>
</body>
</html>