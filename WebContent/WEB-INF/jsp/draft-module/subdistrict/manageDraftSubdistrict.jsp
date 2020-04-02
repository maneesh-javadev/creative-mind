
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<%@include file="manageDraftSubdistrictJs.jsp"%>

<title><spring:message htmlEscape="true"  code="label.view.and.manage.draft.subdistrict"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="label.view.and.manage.draft.subdistrict"></spring:message></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form  method="post" id="draftSubdistrictForm" commandName="draftSubdistrictForm" >
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildSelection.htm"/>" />
				<form:hidden path="paramCode" />
					<div class="form_block">
										<div class="col_1 overflow_horz">
											
											<ul class="form_body">
												<li>
													<table id="example"  width="100%" class="display">
														<thead>
															
																
																<th width="5%"> <spring:message code="Label.SNO" htmlEscape="true"></spring:message></th>
																<th width="40%"><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></th>
																<th width="20%"><spring:message code="Label.OPERATION" htmlEscape="true"></spring:message></th>
																<th width="7%"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
																<th width="7%"><spring:message htmlEscape="true" code="Label.EDIT"></spring:message></th>
																<th width="7%"><spring:message htmlEscape="true" code="Label.EDGO"></spring:message></th>
		         												<th width="7%"><spring:message htmlEscape="true" code="Label.PUBLISH"></spring:message></th> 
		         												<th width="7%"><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th> 
																
															
														</thead>
														<tbody>
															
															<c:forEach var="objects" items="${subdistrictDraftList}" varStatus="counter">
																<tr id="parent_${objects.groupId}">
																	
																	<td align="center"><c:out value="${counter.count}" escapeXml="true"></c:out></td>
																	<c:choose>
																	<c:when test="${objects.opeartionFlag eq 'C'}">
																		<td><c:out value="${objects.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
																		<td style="word-break:break-all">
																		<spring:message code="Label.CREATENEWSUBDIS" htmlEscape="true"></spring:message>
																		</td>
																		<td align="center">
																				<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','viewDraftSubdistrict.htm');">
																						<img src="images/view.png" width="18" height="19" border="0"/>
																							
																				</a>
																			</td>
																		
																		<c:choose>
																		<c:when test="${objects.multipleFlag eq false}">
																			<td align="center" >
																				<a href="#" onclick="processLinkActions('${objects.draftCodeText}','${objects.subdistrictNameEnglish}','editDraftSubdistrict.htm');">
																					<img src="images/edit_icon.png" width="18" height="19" border="0"/>
																				</a>
																			</td>
																		</c:when>
																		<c:otherwise>
																		<td align="center" class="details-control">
																		<%-- <a href="#" onclick="toggleSubComponent('${objects.groupId}');">
																			<img src="images/expend.png" width="18" height="19" border="0"/>
																			
																		</a> --%>
																		</td>
																		
																		</c:otherwise>
																		</c:choose>
																		
																		<td align="center" >
																			<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','editDraftGovermentOrder.htm');">
																				<img src="images/edit_icon.png" width="18" height="19" border="0"/>
																				
																			</a>
																		</td>
																		<td align="center" >
																			<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','publishDraftSubdistrict.htm');">
																				<img src="images/publish.png" width="18" height="19" border="0"/>
																			</a>
																		</td>
																		
																		
																		
																		<td align="center" >
																			<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','deleteDraftSubdistrict.htm');">
																				<img src="images/delete.png" width="18" height="19" border="0"/>
																			</a>
																		</td>
																		
																	
																		
																	</c:when>
																	<c:otherwise>
																		<td ><c:out value="${objects.changeSubdistrictNameEnglish}" escapeXml="true"></c:out></td>
																		<td style="word-break:break-all">
																		<spring:message code="Label.MANAGESUBDISTRICT" htmlEscape="true"></spring:message>
																		</td>
																		<td align="center">
																			<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','viewChangeDraftSubdistrict.htm');">
																			<img src="images/view.png" width="18" height="19" border="0"/>
																			</a>
																		</td>
																		<td align="center" >
																				<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','editChangeDraftSubdistrict.htm');">
																					<img src="images/edit_icon.png" width="18" height="19" border="0"/>
																				</a>
																			
																		</td>
																		<td></td>
																		<td align="center" >
																			<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','publishChangeDraftSubdistrict.htm');">
																				<img src="images/publish.png" width="18" height="19" border="0"/>
																			</a>
																		</td>
																		<td align="center" >
																			<a href="#" onclick="processLinkActions('${objects.groupId}','${objects.subdistrictNameEnglish}','deleteChangeDraftSubdistrict.htm');">
																				<img src="images/delete.png" width="18" height="19" border="0"/>
																			</a>
																		</td>
																		<td></td>
																	</c:otherwise>
																	</c:choose>
																	</tr>
																	
																	
															</c:forEach>
														</tbody>
													</table>
													
												</li>
											</ul>
										</div>
									</div>
			 </form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

