<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%!int pageNumber = 1;%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!--Expend and Collaps Section of Form-->
<script type="text/javascript" src="js/animatedcollapse.js"></script>
</head>
<link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet"
	type="text/css" />
<body>
	<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
              <form:form action="modifyLbSetup.htm" method="GET" commandName="lGSetupForm"> 
	            <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLbSetup.htm"/>"/>
	            
	           <c:if test="${localBodySubtypeList.size()>0}">
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.VST"></spring:message></h3>
                    </div>
                    
                  <c:if test="${localBodySubtypeList.size()>0}">
                  <table class="table table-bordered table-hover">
						<tr>
							<td><spring:message htmlEscape="true"  code="Label.STNE"></spring:message></td>
							<td><spring:message htmlEscape="true"  code="Label.STNL"></spring:message></td>
						</tr>

                   <c:forEach var="current" varStatus="currentRow" items="${localBodySubtypeList}">
						<tr>
							<td ><c:out value="${current.subtypeNameEng}" escapeXml="true"></c:out></td>
	          				<td ><c:out value="${current.subtypeNameLocal}" escapeXml="true"></c:out></td>
					   </tr>
			    </c:forEach>
			    
               </table>
              </c:if>
             </c:if>
             
             
       <c:if test="${subType.size()>0}">
			
			 <div class="box-header with-border">
                        <h3 class="box-title">Sub Type</h3>
                 </div>
				<table class="table table-bordered table-hover">
					<tr >
						<td><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPENAME"></spring:message></td>
						<td><spring:message htmlEscape="true"  code="Label.STNIE"></spring:message></td>
						<td><spring:message htmlEscape="true"  code="Label.STNIL"></spring:message></td>
					</tr>
		   		<c:forEach var="subType" varStatus="listVillageDetailsRow" items="${addVillageNew.subType}">
		               <tr>
						<spring:bind path="addVillageNew.subType[${listVillageDetailsRow.index}].localBodyTypeName">
		                    <td><option name="${status.expression}" />
								<c:out value="${status.value}" escapeXml="true"></c:out></option>
						    </td>
						</spring:bind>
						
		                <spring:bind path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameEng">
						<td><option name="${status.expression}" /> <c:out value="${status.value}" escapeXml="true"></c:out>
							</option>
						</td>
						</spring:bind>
						
						<spring:bind path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameLocal">
						<td><option name="${status.expression}" /> <c:out value="${status.value}" escapeXml="true"></c:out>
							</option></td>
						</spring:bind>
						
		                </tr>
				
					</c:forEach>
				</table>
						
				<div class="box-footer">                    
                    <a href="#">Previous</a>
                    <a href="#">Next</a>
              </div>	
	  </c:if>
	
	
	
	
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			           <button type="button" name="Submit3" class="btn" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'" id="btnClose" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button> 
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
 <script src="/LGD/JavaScriptServlet"></script>
</body>
</html>