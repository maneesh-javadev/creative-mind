<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script> --%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$( '[id^=btnFormAction]' ).click(function() {	
			var url = $(this).attr('param').concat('.htm');	
			$( 'form[id=formViewDraftedRenameLB]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=formViewDraftedRenameLB]' ).attr('method','post');
			$( 'form[id=formViewDraftedRenameLB]' ).submit();
		});
	});
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.CORRECTGOVTLOCALBODY.RENAME" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form id="formViewDraftedRenameLB" commandName="draftRenameLocalbodyTemp">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="publishRenameLocalBody.htm"/>" />
					<input type="hidden" name="tempLocalBodyCode" value="${draftRenameLocalbodyTemp.id}"/>
					<input type="hidden" name="tempLocalBodyCreationType" value="${draftBCreationType}"/>
					
					
					<!-- Block for Show General Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<c:if test="${localBodyTableObj.isdisturbed}">
										<li>
											<label class="inline">
												<spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message>
										    </label>
										    <label >
										    		<img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" />
										    </label>
										</li>
									</c:if>	
									<li>
										<label class="inline">
											<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
									    </label>
									    <label >
									    	<c:out value="${localBodyTableObj.localBodyNameEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyTableObj.localBodyNameLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message>
										</label>
										<label>
											<c:out value="${localBodyTableObj.localBodyAliasEnglish}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message>
										</label>
										<label >
											<c:out value="${localBodyTableObj.localBodyAliasLocal}" escapeXml="true"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message htmlEscape="true"  code="Label.list.previous.names"></spring:message>
										</label>
										<label >
											<a href="#" id="showPreviousNames" onclick="callPreviousNamesView('${localBodyForm.localBodyCode}')"> 
												<spring:message htmlEscape="true"  code="Label.view.previous.names"></spring:message>
											</a> 
											<div id="divPreviousNames" style="display: none;">
												<table class="data_grid" width="100%">
													<thead>
														<tr>
															<th>S.No.</th>
															<th><spring:message code='LOCALBODYNAMEENGLISH' htmlEscape='true'/></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="pName" items="${previousNames}" varStatus="counter">
															<tr>	
																<td><c:out value="${counter.count}" escapeXml="true"/></td>
																<td><c:out value="${pName}" escapeXml="true"/></td>
															</tr>	
														</c:forEach>
													</tbody>
												</table>		
											</div>
									    </label>
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Show General Details of Drafted Local Government Body Ends. -->		
				
				
					<!-- General Details of Local Body Started-->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4>Modified Local Body Details</h4>
								<ul class="form_body" >
										
										<li>
											<label class="inline">
												Modified Name of new Local body (In English)
										    </label>
										    <label >
										    	<c:out value="${draftRenameLocalbodyTemp.localBodyNameEnglish}" escapeXml="true"></c:out>
										    </label>
										</li>
										<li>
											<label>
												Modified Name of new Local body (In Local language)
											</label>
											<label >
												<c:out value="${draftRenameLocalbodyTemp.localBodyNameLocal}" escapeXml="true"></c:out>
										    </label>
										</li>
										<li>
											<label>
												Modified Alias of the local body (In English)
											</label>
											<label>
												<c:out value="${draftRenameLocalbodyTemp.localBodyAliasEnglish}" escapeXml="true"></c:out>
										    </label>
										</li>
										<li>
											<label>
												Modified Alias of the local body (In Local language)
											</label>
											<label >
												<c:out value="${draftRenameLocalbodyTemp.localBodyAliasLocal}" escapeXml="true"></c:out>
										    </label>
										</li>
									</ul>
							</div>
						</div>
					</div>
					<br/>
					<!-- General Details of Local Body Ends Here-->
					<!-- Block for Government Order Details of Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></h4>
								<ul class="form_body" >
									<li>
										<label class="inline">
											<spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message>
									    </label>
									    <label >
											<c:out value="${draftRenameLocalbodyTemp.orderNo}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${draftRenameLocalbodyTemp.orderDate}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										</label>
										<label>
											<c:out value="${draftRenameLocalbodyTemp.effectiveDate}"></c:out>
									    </label>
									</li>
									
									<c:choose>
										<c:when test="${isGovernmentOrderUpload}">
											<li>
									   			<label>	
													<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
												</label>
												<label>
													<c:out value="${draftRenameLocalbodyTemp.gazPubDate}"></c:out>
											    </label>
											</li>
											<li>
												<label>	
													<spring:message code='Label.UGO' htmlEscape='true'/>								
												</label>
												<label>
													<c:choose>
														<c:when test="${not empty draftRenameLocalbodyTemp.orderCode}">
															<a href="downloadLBGovernementOrder.htm?filename=${govtOrderDetails.fileTimestamp}&<csrf:token uri='downloadLBGovernementOrder.htm'/>"><c:out value="${govtOrderDetails.fileName}"/></a>
														</c:when>
														<c:otherwise>
															<c:set var="fileName" value=""/>
															<c:if test="${not empty draftRenameLocalbodyTemp.orderPath}">
																<c:set var="substrng" value="${fn:substring(draftRenameLocalbodyTemp.orderPath, fn:indexOf(draftRenameLocalbodyTemp.orderPath, '_'), fn:indexOf(draftRenameLocalbodyTemp.orderPath, '.'))}" />
																<c:set var="fileName" value="${fn:replace(draftRenameLocalbodyTemp.orderPath, substrng, '')}" />
															</c:if>
															<a href="downloadLBGovernementOrder.htm?filename=${draftRenameLocalbodyTemp.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
																<c:out value="${fileName}"/>
															</a>
														</c:otherwise>
													</c:choose>
												</label>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<div id="divCKEditor" >
													<form:textarea path="editorTemplateDetails" id="editorTemplateDetails" cssClass="ckeditor" disabled="true"/>
												</div>
									   		</li>
										</c:otherwise>
									</c:choose>
									
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Government Order Details of Drafted Local Government Body Ends. -->						

					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true"  code="Button.SP"/>" param="publishSingleDraftedRenameLocalBody"/>
					<input class="bttn" id="btnFormActionDelete" type="button" value="Delete" param="deleteSingleDraftedRenameLocalBody" />
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>