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
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
        <form:form action="modifyConstituncy.htm" id="form1" method="Post"  commandName="configureMapForm">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyConstituncy.htm"/>" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true" code="Label.CONFIGUREMAP"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tr>
						  <th><spring:message htmlEscape="true" code="Label.Constituencyname"></spring:message></th>
						  <th ><spring:message htmlEscape="true" code="Label.uploadnseparate"></spring:message></th>
						  <th><spring:message htmlEscape="true" code="Label.BASEURL"></spring:message></th>
						</tr>
					<tbody>
                       <c:forEach var="listConfigurationMapConstituency" varStatus="administratorUnitRow" items="${configureMapForm.listConfigurationMapConstituency}">
							<tr>
                             <c:if test="${fn:containsIgnoreCase(listConfigurationMapConstituency.constituencyType,'P')}">
									<td>
										<spring:message htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCY"></spring:message>
									</td>
									<c:choose>
										<c:when test="${listConfigurationMapConstituency.ismapupload==true}">
											<td >
												<spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message>
											</td>
											<td></td>
										</c:when>
										<c:otherwise>
											<td>
												<spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message>
											</td>
											<td>
												<c:out value="${listConfigurationMapConstituency.baseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${fn:containsIgnoreCase(listConfigurationMapConstituency.constituencyType,'A')}">
									<td>
										<spring:message htmlEscape="true"  code="Label.ASSEMBLYCONSTITUENCY"></spring:message>
									</td>
									<c:choose>
										<c:when 	test="${listConfigurationMapConstituency.ismapupload==true}">
											<td>
												<spring:message htmlEscape="true" code="Label.UPLOADMAP"></spring:message>
											</td>
											<td></td>
										</c:when>
										<c:otherwise>
											<td >
												<spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message>
											</td>

											<td >
												<c:out value="${listConfigurationMapConstituency.baseUrl}" escapeXml="true"></c:out>
											</td>
										</c:otherwise>
									</c:choose>
								</c:if>
							</tr>
						</c:forEach>
                  </tbody>
               </table>
               </div>
                  <!-- /.box-body -->
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			             <button type="submit" class="btn btn-success" name="Submit"  name="Submit" 	 onclick="javascript:go('viewConfigMapConstituncy.htm?id=$(listConfigurationMapConstituency.stateCode)');" ><spring:message htmlEscape="true"  code="Button.Modify"></spring:message></button>
			             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
</body>
</html>
