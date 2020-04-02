<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script> --%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css" rel="stylesheet" type="text/css">	
<script src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$( '[id^=btnFormAction]' ).click(function() {	
			var url = $(this).attr('param').concat('.htm');	
			$( 'form[id=formViewDraftedChangeParentLB]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=formViewDraftedChangeParentLB]' ).attr('method','post');
			$( 'form[id=formViewDraftedChangeParentLB]' ).submit();
		});
	});
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message code="Label.MODIFYGOVTLOCALBODY.CP" htmlEscape="true"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form id="formViewDraftedChangeParentLB" commandName="draftChangeParentLocalbodyTemp">
					<input type="hidden" name="tempLocalBodyCode" value="${draftChangeParentLocalbodyTemp.tempPkId}"/>
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
												<spring:message	code="Label.SELECTEDTYPEOFLOCALBODY" htmlEscape="true"></spring:message>
										    </label>
										    <label >
										    	<c:out value="${lbTypeName}" escapeXml="true"></c:out>
										    </label>
										</li>
										<li>
											<label>
												<spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"></spring:message>
											</label>
											<label >
												<c:out value="${parentLBName}" escapeXml="true"></c:out>
										    </label>
										</li>
										<li>
											<label>
												<spring:message code="Label.PARENTNEWLOCALBODY" htmlEscape="true"></spring:message>
												<c:out value="${localBodyTableObj.localBodyNameEnglish}"></c:out>
											</label>
											<label>
												<c:out value="${newParentLBName}" escapeXml="true"></c:out>
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
											<c:out value="${draftChangeParentLocalbodyTemp.orderNo}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</label>
										<label >
											<c:out value="${draftChangeParentLocalbodyTemp.orderDate}"></c:out>
									    </label>
									</li>
									<li>
										<label>
											<spring:message	code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> 
										</label>
										<label>
											<c:out value="${draftChangeParentLocalbodyTemp.effectiveDate}"></c:out>
									    </label>
									</li>
									
									<c:choose>
										<c:when test="${isGovernmentOrderUpload}">
											<li>
									   			<label>	
													<spring:message	code="Label.GAZPUBDATE" htmlEscape="true"></spring:message>									
												</label>
												<label>
													<c:out value="${draftChangeParentLocalbodyTemp.gazPubDate}"></c:out>
											    </label>
											</li>
											<li>
												<label>	
													<spring:message code='Label.UGO' htmlEscape='true'/>								
												</label>
												<label>
													<c:choose>
														<c:when test="${not empty draftChangeParentLocalbodyTemp.orderCode}">
															<a href="downloadLBGovernementOrder.htm?filename=${govtOrderDetails.fileTimestamp}&<csrf:token uri='downloadLBGovernementOrder.htm'/>"><c:out value="${govtOrderDetails.fileName}"/></a>
														</c:when>
														<c:otherwise>
															<c:set var="fileName" value=""/>
															<c:if test="${not empty draftChangeParentLocalbodyTemp.orderPath}">
																<c:set var="substrng" value="${fn:substring(draftChangeParentLocalbodyTemp.orderPath, fn:indexOf(draftChangeParentLocalbodyTemp.orderPath, '_'), fn:indexOf(draftChangeParentLocalbodyTemp.orderPath, '.'))}" />
																<c:set var="fileName" value="${fn:replace(draftChangeParentLocalbodyTemp.orderPath, substrng, '')}" />
															</c:if>
															<a href="downloadLBGovernementOrder.htm?filename=${draftChangeParentLocalbodyTemp.orderPath}&<csrf:token uri='downloadLBGovernementOrder.htm'/>">
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

					<input class="bttn" id="btnFormActionPublish" type="button" value="<spring:message htmlEscape="true"  code="Button.SP"/>" param="publishSingleDraftedChangeParentLB"/>
					<input class="bttn" id="btnFormActionDelete" type="button" value="Delete" param="deleteSingleDraftedChangeParentLB" />
					<input class="bttn" id="btnFormActionClose" type="button" value="Close" param="home"/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
</body>
</html>