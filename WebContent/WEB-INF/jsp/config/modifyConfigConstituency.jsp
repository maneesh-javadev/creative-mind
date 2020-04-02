<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
<script type="text/javascript" language="javascript">
	function open_win() {

		var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '',
				"dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
		//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
	}
</script>
<script>
	function doSubmit() {
		document.getElementById('savebtn').disabled = true;
		document.forms['form1'].submit();
	}
</script>
</head>
<body onload="loadPage();">


	<div class="clear"></div>
	<div id="frmcontent">
		<div class="frmhd">

			<h3 class="subtitle">
				<spring:message htmlEscape="true" code="Label.CONFIGUREMAP"></spring:message>
			</h3>
			<ul id="showhelp" class="listing">
				<%-- //these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message
							htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message
							htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</div>



		<div class="frmpnlbrdr">
			<div class="frmpnlbg">
				<div class="frmtxt">
					<form:form action="modifyConstituncy.htm" id="form1" method="Post"
						commandName="configureMapForm">
						<input type="hidden" name="<csrf:token-name/>"
							value="<csrf:token-value uri="modifyConstituncy.htm"/>" />
						<div class="errormsg"></div>



						<div class="table col_3">
							<!-- Table starts -->
							<div class="trow thead">
								<div class="th col1">
									<spring:message htmlEscape="true" code="Label.Constituencyname"></spring:message>
								</div>
								<div class="th col1">
									<spring:message htmlEscape="true" code="Label.uploadnseparate"></spring:message>
								</div>
								<div class="th col1">
									<spring:message htmlEscape="true" code="Label.BASEURL"></spring:message>
								</div>
							</div>
						



						<c:forEach var="listConfigurationMapConstituency"
							varStatus="administratorUnitRow"
							items="${configureMapForm.listConfigurationMapConstituency}">


							<div class="trow red">

								<c:if
									test="${fn:containsIgnoreCase(listConfigurationMapConstituency.constituencyType,'P')}">
									<div class="td column col1">
										<spring:message htmlEscape="true"
											code="Label.PARLIAMENTCONSTITUENCY"></spring:message>
									</div>
									<c:choose>
										<c:when
											test="${listConfigurationMapConstituency.ismapupload==true}">
											<div class="td column col1">
												<spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message>
											</div>
											<div class="td column col1">-</div>
										</c:when>
										<c:otherwise>
											<div class="td column col1">
												<spring:message htmlEscape="true"
													code="Label.SEPERATEMAPSERVER"></spring:message>
											</div>
											<div class="td column col1">
												<c:out value="${listConfigurationMapConstituency.baseUrl}" escapeXml="true"></c:out>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if
									test="${fn:containsIgnoreCase(listConfigurationMapConstituency.constituencyType,'A')}">
									<div class="td column col1">
										<spring:message htmlEscape="true"
											code="Label.ASSEMBLYCONSTITUENCY"></spring:message>
									</div>
									<c:choose>
										<c:when
											test="${listConfigurationMapConstituency.ismapupload==true}">
											<div class="td column col1">
												<spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message>
											</div>
											<div class="td column col1">-</div>
										</c:when>
										<c:otherwise>
											<div class="td column col1">
												<spring:message htmlEscape="true"
													code="Label.SEPERATEMAPSERVER"></spring:message>
											</div>

											<div class="td column col1">
												<c:out value="${listConfigurationMapConstituency.baseUrl}" escapeXml="true"></c:out>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>



							</div>
						</c:forEach>


</div>


						<!-- 
						</table> -->


						<div class="btnpnl">
							<!-- <table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="2%" rowspan="2">&nbsp;</td>
									<td width="98%"> -->
							<ul class="listing">
								<li><label> <input type="submit" class="btn"
										name="Submit" class="btn" name="Submit"
										value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>"
										onclick="javascript:go('viewConfigMapConstituncy.htm?id=$(listConfigurationMapConstituency.stateCode)');" />
								</label> <label> <input type="button" class="btn"
										name="Submit623"
										value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
										onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								</label></li>
							</ul>

						</div>
					</form:form>
					<!-- </td>
								</tr>

							</table> -->
				</div>

				<script src="/LGD/JavaScriptServlet"></script>
				<%-- <script src="/LGD/JavaScriptServlet"></script> --%>
			</div>
		</div>
	</div>


</body>
</html>
