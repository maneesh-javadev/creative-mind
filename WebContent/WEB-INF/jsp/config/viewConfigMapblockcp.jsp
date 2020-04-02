<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <script type="text/javascript" src="js/cancel.js" ></script>
     <script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<title><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></title>
</head>
<body>
<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
               <form:form action="modifyLandBlock.htm" method="Post" commandName="configureMapForm">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLandBlock.htm"/>"/>
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
                    </div>
                    <div class="box-body">
                    <table class="table table-bordered table-hover">
    
                  
     				   <tr class="active">
							<th >Entity Name</th>
							<th >Upload Or From Seperate Server</th>
							<th >Base Url</th>
						</tr>
					<tbody>
                          <c:forEach var="viewConfigMapLandRegion" varStatus="administratorUnitRow" items="${configureMapForm.viewConfigMapLandRegion}">
							  <tr >    
								  	<c:if test="${fn:containsIgnoreCase(viewConfigMapLandRegion.landregiontype,'B')}">
									     <td><label> <spring:message htmlEscape="true"  code="Label.BLOCK"></spring:message></label> </td>
										     <c:choose>
										     <c:when test="${viewConfigMapLandRegion.ismapupload==true}">
									    <td><label> <spring:message htmlEscape="true"  code="Label.UPLOADMAP"></spring:message></label> </td>
									    <td align="center">-</td>
										  </c:when>
										    <c:otherwise>
									     <td> <label> <spring:message htmlEscape="true"  code="Label.SEPERATEMAPSERVER"></spring:message></label> </td>
									     <td><c:out value="${viewConfigMapLandRegion.isbaseUrl}" escapeXml="true"></c:out></td>
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
			              <button type="submit" class="btn btn-success" name="Submit"  ><spring:message htmlEscape="true"  code="Button.Modify"></spring:message></button>
			             <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
  </div>
</section>
</body>
</html>