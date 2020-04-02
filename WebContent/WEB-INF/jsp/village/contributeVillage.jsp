<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<title>ContributeVillage</title>
</head>
<body>
	<form:form action="contributeVillageModAction.htm" method="POST" commandName="addVillageNew">
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="contributeVillageModAction.htm"/>"/>
		<div
			style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px">
			<div class="frmtxt"
				style="position: relative; background: inherit; padding-top: 20px;">
				<div
					style="position: absolute; z-index: 1; width: 90px; text-align: center; top: -11px; left: 12px"
					class="frmhdtitle"><spring:message code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message></div>

				<div class="frmtxt"
				style="position: relative; background:inherit; padding-top: 20px;">
			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
					<tr>
								<td><strong><spring:message	code="Label.OPERATIONAL" htmlEscape="true"></spring:message> </strong></td>						
						        <td><strong><spring:message	code="Label.NAMEOFOLDVILLAGEENGLISH" htmlEscape="true"></spring:message></strong></td>								
								<td><strong><spring:message	code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message></strong></td>
								<td><strong><spring:message code="Label.NAMEOFOLDVILLAGELOCAL" htmlEscape="true"></spring:message></strong></td> 
								<td><strong><spring:message code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message></strong></td>
								<td><strong><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></strong></td>
								<td><strong><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></strong></td>
								<td><strong><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></strong></td>
								<td><strong><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></strong></td>
								
					</tr>
					
					
					
					<!-- Start New Method -->	
					<c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${addVillageNew.listVillageDetails}">
							 
                              <tr>
                              <spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].villageCode">                              
                                 <td width="2%">                                  
                                  <input type="checkbox" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" />                                
                                </td>                                
                            </spring:bind>
                           
                            <spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							 <spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].census2011Code">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].sscode">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"/>							
					    	</td>
							</spring:bind>                            
                            
                           </tr>                                                            
                        </c:forEach>	
					<!--End here  -->
			
					<tr>
								
					</tr>
				
				</table>
			</div>
				
				<label></label>
				
										<input type="submit" class="btn" name="Submit3" value="Save" /> <span
										class="errormsg"> <input type="button" value="<<.. Previous" onclick="javascript:go('createVillage.htm');" />
									<%-- 	<input type="button" value="<< .. Previous" onclick="showsubmit(false)" /> --%>
									<!-- 	<input type="button" class="btn" name="Submit2" value="Proceed"	onclick="javascript:go('ulbVillage.htm');" /> --> </span><span
										class="errormsg"> <input
											type="button" name="Submit6" value="Cancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </span>
								

			</div>

		</div>
	</form:form>

</body>
</html>