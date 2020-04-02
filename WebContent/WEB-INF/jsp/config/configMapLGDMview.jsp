<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
</title>
</head>
<body>
	<div class="clear"></div>
	 <div class="frmhd">
		 <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<%-- this link is not working<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li> --%>
 			<%-- //this link is not working	<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
	  </div>
	<div class="frmpnlbrdr">
		<div class="frmpnlbg">
			<div class="frmtxt">
				<form:form action="configmapLGDMview.htm" method="Post"
					commandName="configMapLGDMForm">
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="configmapLGDMview.htm"/>" />


					<div class="frmhdtitle">
						<label> <spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message>
						</label>
					</div>
					
					 <div class="table col_5"> <!-- Table starts -->				
					<div class="trow thead">
						<div class="th col1"><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME" ></spring:message></div>
						<div class="th col2"><spring:message htmlEscape="true" code="Label.uploadnseparate" /></div>
						<div class="th col3"><spring:message htmlEscape="true" code="Label.BASEURL" ></spring:message></div>
						<!-- <div class="th col4"></div>
						<div class="th col5"></div>	 -->					
					</div>	
					<c:forEach var="viewmaplstdetail" varStatus="administratorUnitRow"
							items="${viewmaplstdetail}">
							 <div class="trow red">
								<div class="td column col1">
									<c:out value="${viewmaplstdetail.nomenclature_english}" escapeXml="true"></c:out>
								</div>

								<c:choose>
									<c:when test="${viewmaplstdetail.ismapupload==true}">
										<div class="td column col2">
											<spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
										</div>
										<div class="td column col3">-</div>
									</c:when>
									<c:otherwise>
										<div class="td column col4">
											<spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message>
										</div>
										<div class="td column col5">
											<c:out value="${viewmaplstdetail.base_url}" escapeXml="true"></c:out>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>

					</div>
					<!-- </table> -->
					<form:hidden path="category" value="${configMapLGDMForm.category}" htmlEscape="true"/>
					<div class="errormsg"></div>

					<div class="btnpnl">
						<label> <input type="submit" class="btn" name="Submit"
							value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>" />
						</label> <label> <input type="button" class="btn" name="Submit6"
							value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
							onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
					</div>
				</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
			</div>
		</div>
	</div>
</body>
</html>