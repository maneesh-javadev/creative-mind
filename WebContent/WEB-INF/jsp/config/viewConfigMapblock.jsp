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
<div class="clear"></div>
				<div class="frmhd">
					<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message></h3>
					<ul id="showhelp" class="listing">
						<%-- //these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
 						<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
					</ul>
				</div>
				    						  				   
                <div class="frmpnlbrdr">
                <div class="frmpnlbg">
				<div class="frmtxt" >
                <form:form action="modifyLandBlock.htm" method="Post" commandName="configureMapForm">
				 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLandBlock.htm"/>"/>
				<div class="frmhdtitle"><label> <spring:message htmlEscape="true"  code="Label.CONFIGUREMAP"></spring:message> </label> </div>
						   
						   
						    <table  width="80%" class="tbl_with_brdr"  align="center">
						    <tr ><td class="lblBold">Entity Name</td><td class="lblBold">Upload Or From Seperate Server</td><td class="lblBold">Base Url</td></tr>
						      <c:forEach var="viewConfigMapLandRegion" varStatus="administratorUnitRow" items="${configureMapForm.viewConfigMapLandRegion}">
							  <tr class="tblRowB">    
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
                            </table>
                            
						<div class="errormsg"></div>						
						<div class="btnpnl">
						<ul class="listing">
                             <li>
                             <label>
                              <input type="submit" class="btn" name="Submit" value="<spring:message htmlEscape="true"  code="Button.Modify"></spring:message>" />
							  </label>
							  <label>
                              <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                              </label>
                              </li> 
                              </ul>                              
                      	</div>
						</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
				</div>
				</div>
				</div>
</body>
</html>