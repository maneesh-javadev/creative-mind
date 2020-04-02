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
	<section class="content">
       <div class="row">
          <section class="col-lg-12">
             <div class="box">
               <form:form action="configmapLGDMview.htm" method="Post"  commandName="configMapLGDMForm">
			   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="configmapLGDMview.htm"/>" />
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tr>
						  <th><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME" ></spring:message></th>
						  <th><spring:message htmlEscape="true" code="Label.uploadnseparate" /></th>
						  <th><spring:message htmlEscape="true" code="Label.BASEURL" ></spring:message></th>
						</tr>
					<tbody>
                       <c:forEach var="viewmaplstdetail" varStatus="administratorUnitRow" items="${viewmaplstdetail}">
							 <tr>
								<td>
									<c:out value="${viewmaplstdetail.nomenclature_english}" escapeXml="true"></c:out>
								</td>

								<c:choose>
									<c:when test="${viewmaplstdetail.ismapupload==true}">
										<td>
											<spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message>
										</td>
										<td></td>
									</c:when>
									<c:otherwise>
										<td>
											<spring:message htmlEscape="true" code="Label.SEPERATEMAPSERVER"></spring:message>
										</td>
										<td>
											<c:out value="${viewmaplstdetail.base_url}" escapeXml="true"></c:out>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
                  </tbody>
               </table>
               </div>
               <form:hidden path="category" value="${configMapLGDMForm.category}" htmlEscape="true"/>
			   <div class="errormsg"></div>
                  <!-- /.box-body -->
            
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			            <input type="submit" class="btn btn-success" name="Submit" value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>" />
			             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              
              </form:form>
              <script src="/LGD/JavaScriptServlet"></script>
         </div>
     </section>
  </div>
</section>
</body>
</html>