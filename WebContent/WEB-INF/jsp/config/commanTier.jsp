<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />

<title>ContributeVillage</title>
</head>
<body>
	<form:form action="contributeVillageModAction.htm" method="POST" commandName="addVillageNew">
		<div
			style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px">
			<div class="frmtxt"
				style="position: relative; background: inherit; padding-top: 20px;">
				<div
					style="position: absolute; z-index: 1; width: 90px; text-align: center; top: -11px; left: 12px"
					class="frmhdtitle"><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST" ></spring:message></div> 
				<div class="frmtxt"
				style="position: relative; background:inherit; padding-top: 20px;">
			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
					<tr>
								<td><strong><spring:message htmlEscape="true" 	code="Label.OPERATIONAL"></spring:message> </strong></td>						
						        <td><strong><spring:message htmlEscape="true" 	code="Label.NAMEOFOLDVILLAGEENGLISH"></spring:message></strong></td>								
								<td><strong><spring:message htmlEscape="true" 	code="Label.NAMEOFNEWVILLAGEENGLISH"></spring:message></strong></td>
								<td><strong><spring:message htmlEscape="true"  code="Label.NAMEOFOLDVILLAGELOCAL"></spring:message></strong></td> 
								<td><strong><spring:message htmlEscape="true"  code="Label.NAMEOFNEWVILLAGELOCAL"></spring:message></strong></td>
								<td><strong><spring:message htmlEscape="true"  code="Label.ALIASENGLISH"></spring:message></strong></td>
								<td><strong><spring:message htmlEscape="true"  code="Label.ALIASLOCAL"></spring:message></strong></td>
								<td><strong><spring:message htmlEscape="true"  code="Label.CENSUSCODE2011"></spring:message></strong></td>
								<td><strong><spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message></strong></td>
								
					</tr>
					
					
					
					<!-- Start New Method -->	
					<c:forEach var="listVillageDetails" varStatus="listVillageDetailsRow" items="${addVillageNew.listVillageDetails}">
							 
                              <tr>
                              <spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].localBodyTypeCode">                              
                                 <td width="2%">                                  
                                  <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />                                
                                </td>                                
                            </spring:bind>
                           
                            <spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].nomenclatureEnglish">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>							
					    	</td>
							</spring:bind>
							 <spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].nomenclatureEnglish">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>							
					    	</td>
							</spring:bind>
							<spring:bind path="addVillageNew.listVillageDetails[${listVillageDetailsRow.index}].localBodyTypeName">							
								<td><input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>"/>							
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
				
										<input type="submit" name="Submit3" value="Save" /> <span
										style="padding: 10px 0px 10px 0px"> <input
											type="button" value="<< .. Previous"
											onclick="showsubmit(false)" /><input type="button"
											name="Submit2" value="Proceed"
											onclick="javascript:go('ulbVillage.htm');" /> </span><span
										style="padding: 10px 0px 10px 0px"> <input
											type="button" name="Submit6" value="Close" /> </span>
								

			</div>

		</div>
	</form:form>

</body>
</html>