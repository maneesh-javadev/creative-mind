<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/subscribe.js"></script>
<script language="javascript">


</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
 			

					<h3 class="subtitle"><spring:message htmlEscape="true"
							code="Label.SUBSCRIBEAPPLICATION"></spring:message>
							</h3>

					<ul class="listing">
					<li>
					<a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
							</li>
					        <%--  this link is not working<li>
					         <a href="#" class="frmhelp"><spring:message
								htmlEscape="true" code="Label.HELP"></spring:message> </a>
								</li> --%>
								</ul>		
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<%-- <form:form action="subscribeemailAction.htm" id="form1" method="POST" commandName="subscribeFrom"> --%>
			
			<%-- <form:form action="modifySubDistrictCrAction.htm?disturb=${disturb}" --%>
				<form:form action="subscriberApplicationAction.htm"
					method="POST" commandName="subscribeFrom"
					id="form1" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="subscriberApplicationAction.htm"/>" />
				<div id="cat">
				<div class="frmpnlbg">
					<div class="frmtxt">
					<table>
					<tr>
					<td><form:errors path="*" class="errormsg"></form:errors></td>
					</tr>
					</table>
					
						<table class="tbl_with_brdr" width="100%" id="table1">
							<tr>
								<td colspan="5" class="tblRowTitle tblclear"><label><strong><spring:message
												htmlEscape="true" code="Label.SUBSCRIBEAPPLICATION"></spring:message>
									</strong> </label></td>
							</tr>
							<tr class="tblRowTitle tblclear">
							  <td width="5%" rowspan="2"><spring:message htmlEscape="true"
										code="Label.SNO"></spring:message></td>
								
							  <td width="25%" rowspan="2"><spring:message htmlEscape="true"
					         code="Label.SUBSCRIBINGAPPLICATION"></spring:message></td>
							  <td width="25%" rowspan="2"><spring:message htmlEscape="true"
										code="Label.URL"></spring:message></td>
								<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
								</tr>
								<tr class="tblRowTitle tblclear">
											  <td width="5%" align="center"><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></td>
							  <td width="6%" align="center"><spring:message htmlEscape="true"  code="Label.DELETE"></spring:message></td>
							  <td>&nbsp;</td>
							   <td>&nbsp;</td>
							    <td>&nbsp;</td>
							     <td>&nbsp;</td>
								</tr>
							
								<%--  <td width="10%"><spring:message htmlEscape="true"
										code="Label.CREATEDBY"></spring:message></td>
										<td width="5%"><spring:message htmlEscape="true"
										code="Label.CREATEDON"></spring:message></td>
										<td width="10%"><spring:message htmlEscape="true"
										code="Label.LASTUPDATEBY"></spring:message></td>
										<td width="5%"><spring:message htmlEscape="true"
										code="Label.LASTUPDATED"></spring:message></td>  --%>
							
							
							<c:forEach var="subscribelist" varStatus="listSubscribeDetailsRow"
									items="${subscribeFrom.scbscribeDetails}">
							
						
						<c:set var="sno" value="${listSubscribeDetailsRow.index+1}" />	
							<tr class="tblRowB" id="row${listSubscribeDetailsRow.index}">
							
							  <td><label><c:out value="${sno}" escapeXml="true"></c:out></label>  </td>
								
								<td >
								 	
								      <div id="dt${listSubscribeDetailsRow.index}" style="visibility: hidden; display: none;">	
								      <spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].applicationName">
								      <input type="text"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='true' />"  size="30"   />
								     </spring:bind>
								    
								     </div>
								       
								      
								     <div id="dl${listSubscribeDetailsRow.index}" >
								      <c:out value="${subscribelist.applicationName}" escapeXml="true"></c:out>
							      </div>
								      
									<spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].applicationId">
								      	<input type="hidden"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='true' />"/>
							      </spring:bind>
								     <spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].flag">
								      	<input type="hidden"  id="<c:out value='${status.expression}' escapeXml="true"></c:out>"  name="<c:out value='${status.expression}'/>"  />
								     </spring:bind>								    
								     
								      
							  </td>
								<td>
								 	
								 	 <div id="ut${listSubscribeDetailsRow.index}" style="visibility: hidden; display: none;">	
								   <spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].url">
								      <input type="text"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='true' />"  size="30"  onblur="validate_textbox(${listSubscribeDetailsRow.index});"/>
								     </spring:bind>
								       <form:errors path="url"></form:errors>
								     </div>
								       
								      
								     <div id="ul${listSubscribeDetailsRow.index}" >
								      <c:out value="${subscribelist.url}" escapeXml="true"></c:out>
							      </div>
								 	
								 	<%-- <spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].url">
								      	<input type="text"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='false'  />" size="20" readonly="readonly" />
								     </spring:bind>
								      --%>
								     			 	<spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].userId">
								      	<input type="hidden"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='true' />"   />
								     </spring:bind>
						 
					
								 	<spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].createBy">
								      	<input type="hidden"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='true' />"  />
							      </spring:bind>
								     
								   
								<%-- 	<spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].createdon">
								      	<input type="hidden"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='false' />"  />
								     </spring:bind>
							 --%>
							
								 	<%-- <spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].lastupdated">
								      	<input type="hidden"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='false' />"  />
								     </spring:bind> --%>
							
								 	<%-- <spring:bind path="subscribeFrom.scbscribeDetails[${listSubscribeDetailsRow.index}].lastupdatedby">
								      	<input type="hidden"  id="<c:out value='${status.expression}'/>"  name="<c:out value='${status.expression}'/>" value="<c:out value='${status.value}' escapeXml='false' />"  />
								     </spring:bind> --%> 
								     
							  </td>
								
									 <td  align="center"><a href="#">
								  <img src="images/edit.png" onclick="manageOperation('U','${listSubscribeDetailsRow.index}');"  width="18" height="19" border="0" /></a></td>
									<td align="center"><a href="#">
									<img src="images/delete.png" onclick="manageOperation('D','${listSubscribeDetailsRow.index}');" width="18" height="19" border="0" /></a></td>
									
								
					
					<td width="36%" >
					<div id="error${listSubscribeDetailsRow.index}"  ></div>
					</td>
								
								<%-- <td width="15%"><c:out
										value="${subscribelist.userId}"></c:out></td>
								<td width="35%"><c:out
										value="${subscribelist.applicationName}"></c:out></td>
								<td width="35%"><c:out
										value="${subscribelist.url}"></c:out></td>
								<td width="35%"><c:out
										value="${subscribelist.createBy}"></c:out></td>
								<td width="35%"><c:out
										value="${subscribelist.createdon}"></c:out></td>
								<td width="35%"><c:out
										value="${subscribelist.lastupdated}"></c:out></td>
								<td width="35%"><c:out
										value="${subscribelist.lastupdatedby}"></c:out></td>	 --%>
										
							</tr>
							
							
							</c:forEach>
							
								
							
							
					</table>
						
						<table>
						<tr>
						<td><input type="button" value="Add New" name="newbtn" id="newbtn" onclick="insertRow();" />
						<input type="hidden" name="bindex" id="bindex"  /> 
						<input type="hidden" name="index" id="index" value="<c:out value='${size}' escapeXml='true'></c:out>"/></td>	
						</tr>
						</table>			
					</div>
					
				
										

								<div class="buttons center">
								<input type="hidden" name="validate_flag" id="validate_flag" value="true" />
								
									 <input type="button" value="Save & Update" name="Updatebtn" onclick="validate_submit();" />
								

								 
								 <input type="button" name="close" class="btn"
											value="<spring:message code='Button.CLOSE' htmlEscape='true'></spring:message>"
											id="btnCancel"
											   onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"  />
											   </div>
									
				</div>
				</div>
				

			</form:form>
		</div>
	</div>
	</body>
	