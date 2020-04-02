<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script src="js/createDepartment.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	<script src="datepicker/calender.js"></script>
	<link rel="stylesheet" href="datepicker/demos.css" /> -->

<script src="js/common.js"></script>
	<script src="js/govtorder.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript">
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script src="js/common.js">dwr.engine.setActiveReverseAjax(true);</script>
<script type="text/javascript" language="javascript">

</script>

<script type="text/javascript">
function hideAll()
{
	
	
	$("#error_govorder").hide();
	$("#EffectiveFutureDate_error").hide();
	$("#OrderFutureDate_error").hide();
 	$("#EffectiveDateBlank_error").hide();
	$("#OrderNoBlank_error").hide();
	$("#OrderNoDataValid_error").hide();
	$("#OrderDateBlank_error").hide();
	$("#OrderDateData_error").hide();
	$("#OrderDateValid_error").hide();
	$("#GuzpubDateValid_error").hide();
	$("#EffectiveDateData_error").hide();
	$("#GuzpubDateBlankOrderDate_error").hide();
	$("#GuzpubDateCompareOrderDate_error").hide();
	
	
	
}

if ( window.addEventListener ) { 
    window.addEventListener( "load",hideAll, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", hideAll );
 } else 
       if ( window.onLoad ) {
          window.onload = hideAll;
 }
 
function validate_orgnization()
{
	hideAll();
	var OrderNo=document.getElementById('OrderNo').value;
	var orderDate=document.getElementById('OrderDate').value;
	var EffectiveDate=document.getElementById('EffectiveDate').value;
	var GazPubDate=document.getElementById('GazPubDate').value;
	var fielattach = document.getElementById('attachFile1').value;
	var error=false;
		if(OrderNo!="")
			{
			if(!validateOrderNo(OrderNo,"#OrderNoDataValid_error"))
				{
				error=true;
				}
			}
		else
			{
			$("#OrderNoBlank_error").show(); 
			error=true;
			}
		
		if(orderDate==null || orderDate=="")
			{
			$("#OrderDateBlank_error").show(); 
			
			error=true;
			}
			
		if(EffectiveDate==null || EffectiveDate=="")
			
			{
			$("#EffectiveDateBlank_error").show(); 
			error=true;
			}
		
		if(fielattach.length==0)
			{
			$("#error_govorder").show();
			error=true;
			}
		
		if(error==true)
			return false;
		else
			return true;

	
	}
	
function validateDatetoFuture(id,diverror)
{
	var entityDate=document.getElementById(id).value;
	 $(diverror).hide();
	 if(!compareDateforFutureDDMMYYY(entityDate)){ 
			$(diverror).show();
			document.getElementById(id).value="";
			
			
	 }
	 
}

function setEffectiveDatetoOrderDate(orderDate)
{
	
	 //alert(orderDate);
	 if(orderDate!="" && orderDate!=undefined)
	 document.getElementById('EffectiveDate').value=orderDate;
}


function validateEffectiveDatecompOrderDate(orderdateId,effectivedateID,diverror)
{
	 var orderDate=document.getElementById(orderdateId).value;
	 var effectiveDate=document.getElementById(effectivedateID).value;
	if(orderDate!="") 
		{
		$(diverror).hide();	
		if ((compareDateYYMMDD(effectiveDate,orderDate, 'dd-mm-yyyy'))) {
				$(diverror).show();
				document.getElementById(effectivedateID).value=orderDate;
				
			}
		}
	 
	 
}

</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>
	<div id="frmcontent">
		<div class="frmhd" >


			
					<h3 class="subtitle"> <spring:message code="Label.DELETEORGANIZATION" htmlEscape="true"></spring:message> 
					</h3>
					<ul class="listing">
					<li>
					<a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
							</li>
					<li><a href="#" class="frmhelp">Help</a>
					</li>
					</ul>
				
			
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="abolishDepartmentforState.htm" method="POST" commandName="viewDept">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="abolishDepartmentforState.htm"/>"/>
										<form:input type="hidden"  path="departmentId" value="${orgCode}"/>
			
			
			
			
				<div id="cat">
					<div class="frmtxt">
													
													<div class="search_criteria"  >
																										
													<c:if test="${! empty viewOrgState}">
														<ul class="blocklist">			
														<c:forEach var="viewOrgState" items="${viewOrgState}">	
														 <li>
																
																<spring:message htmlEscape="true"  code="Label.ORGCODE"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${viewOrgState.orgCode}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
														</li>
														 
														 <li>
																
																<spring:message htmlEscape="true"  code="Label.ORGNAME"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${viewOrgState.orgName}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
														</li>
														<li>
																
																<spring:message htmlEscape="true"  code="Label.SHORTORGNAME"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${viewOrgState.shortName}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
														</li>
														
														<li>
																
																<spring:message htmlEscape="true"  code="Label.ORGNTYPE"></spring:message><br />
																	<label class="lblPlain"> <c:out value="${viewOrgState.organizationType.orgType}" escapeXml="true"></c:out></label>						  
																	<div class="errormsg"></div>
														</li>
														
														
														
																																					
															
													</c:forEach>
													</ul>
													</c:if>
																									
													</div>
												</div>
					<!--Begining of Govt Order Details  -->

					<div >
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"  ></spring:message>
							</div>

							
								
									 <%--<form:hidden path="orderCode"
											id="hdnOrderCode" value="${viewOrgState.orderCode}" />
									</td> --%>
									<div class="search_criteria">
									<ul class="blocklist">
									<li>
									<label for="OrderNo"><spring:message
												code="Label.ORDERNO" htmlEscape="true"  ></spring:message> <span
										class="errormsg">*</span></label><br /> <form:input
											path="orderNo" id="OrderNo" type="text"
											style="width: 120px" class="frmfield"
											onkeypress="validateNumericAlphaKeys();" />
											<div class="errormsg" id="OrderNoBlank_error">
												<spring:message htmlEscape="true" code="error.required.ORDERNUM"></spring:message>
									       </div>
									        <div class="errormsg" id="OrderNoDataValid_error">
												<spring:message htmlEscape="true" code="error.valid.ORDERNO"></spring:message>
									       </div>
											
											 <span
										class="error" id="OrderNo_error"></span>&nbsp;&nbsp;&nbsp;<span>
												
												<form:errors htmlEscape="true"  path="orderNo" class="errormsg"></form:errors>
										</span>
									</li>	
								
								<li>
									<label for="OrderDate"><spring:message code="Label.ORDERDATE" htmlEscape="true"  ></spring:message>
									<span class="errormsg">*</span></label><br /> <form:input
											path="orderDate" id="OrderDate" type="text"
											class="frmfield" style="width: 120px"
											onchange="hideAll();validateDatetoFuture('OrderDate','#OrderFutureDate_error');setEffectiveDatetoOrderDate(this.value)"
											onkeypress="validateNumeric();"  htmlEscape="true"  /> 
											<div class="errormsg" id="OrderDateBlank_error">
												<spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message>
									       </div>
									 
									  <div class="errormsg" id="OrderFutureDate_error">
												<spring:message htmlEscape="true" code="error.INCORECT.ORDERDATE"></spring:message>
									       </div>
									  
									  <div class="errormsg" id="OrderDateValid_error">
												<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message>
									       </div>
									 
									 <div class="errormsg" id="OrderDateData_error">
												<spring:message htmlEscape="true" code="error.compare.ORDERDATE"></spring:message>
									       </div>
											<span class="error"
										id="OrderDate_error"></span>&nbsp;&nbsp;&nbsp;<span>
												<form:errors htmlEscape="true"  path="orderDate" class="errormsg"></form:errors>
										</span>

								</li>
								
								<li>
									<label for="EffectiveDate"><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"  ></spring:message> <span
										class="errormsg">*</span></label><br /> <form:input
											id="EffectiveDate" path="ordereffectiveDate" type="text"
											class="frmfield" style="width: 120px"
											onchange="hideAll();validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
											onkeypress="validateNumeric();"  htmlEscape="true"  />
											<div class="errormsg" id="EffectiveDateData_error">
												<spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message>
									       </div>
											
											<div class="errormsg" id="EffectiveDateBlank_error">
												<spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message>
									       </div>
											<div class="errormsg" id="EffectiveFutureDate_error">
												<spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message>
									       </div>
											 <span class="error"
										id="EffectiveDate_error"></span>&nbsp;&nbsp;&nbsp;<span>
												<form:errors htmlEscape="true"  path="ordereffectiveDate" class="errormsg"></form:errors>
										</span>
								</li>
								
								<li>
									<label for="GazPubDate"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"  ></spring:message>
									</label> <br /> <form:input id="GazPubDate" path="gazPubDate"
											type="text" class="frmfield" style="width: 120px"
											onchange="noOrderDataValid('GazPubDate','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('GazPubDate','#GuzpubDateCompareOrderDate_error','OrderDate')"
											onkeypress="validateNumeric();" /> 
											<div class="errormsg" id="GuzpubDateValid_error">
												<spring:message htmlEscape="true" code="error.valid.DATEFORMAT"></spring:message>
									       </div>
									      <div class="errormsg" id="GuzpubDateBlankOrderDate_error">
												<spring:message htmlEscape="true" code="error.required.ORDERDATE"></spring:message>
									       </div>
									       
									        <div class="errormsg" id="GuzpubDateCompareOrderDate_error">
												<spring:message htmlEscape="true" code="error.compare.GuzpubDate"></spring:message>
									       </div>
											<span class="error"
										id="GazPubDate_error"></span>


								</li>
								</ul>
								</div>
								<%--  <tr>

									<td><div id="titleId">
											<form:hidden id="showTitle" path="showTitle" name="showTitle"
												class="frmfield" />
										</div>
										<div id="hiddentitle"></div>
										<br />
										<div id="hideAttachmentUtilDiv">
											
											<br /> <br />
											<div id="alreadyAttachedFiles">
												<c:if test="${! empty Attached_File_Meta_Data_List}">
													<table width="100%" class="tbl_with_brdr" align="center"
														id="tid">
														<tr class="frmhd">
															<!-- 	<spring:message htmlEscape="true"  code="addAttachment.tableheading" /> -->
														</tr>
														<tr class="tblRowTitle tblclear">
															<td><spring:message htmlEscape="true"  code="addAttachment.filetitle" />
															</td>
															<td><spring:message htmlEscape="true"  code="addAttachment.filename" />
															</td>
															<td><spring:message htmlEscape="true"  code="addAttachment.filesize" />
															</td>
															<td><spring:message htmlEscape="true" 
																	code="addAttachment.filecontenttype" />
															</td>
															<td><spring:message htmlEscape="true" 
																	code="addAttachment.fileflagfordeletion" />
															</td>
														</tr>
														<c:forEach var="abc"
															items="${Attached_File_Meta_Data_List}">

															<tr id="row${abc.attachmentId}" class="tblRowB">

																<td><c:out value="${abc.fileTitle}" escapeXml="false"></c:out></td>
																<td><c:out value="${abc.fileName}" escapeXml="false"></c:out></td>
																<td><c:out value="${abc.fileSize}" escapeXml="false"></c:out></td>
																<td><c:out value="${abc.fileContentType}" escapeXml="false"></c:out></td>

																<td width="10%"><a href="#"><img
																		src="images/delete.png" name="deleteAttachment"
																		width="18" height="19" border="0"
																		onclick="{hideThisRow(${abc.attachmentId}),holdAttachmentID(${abc.attachmentId}),holdFilePath('${abc.fileTimestamp}'),holdFileSize(${abc.fileSize })}" />
																</a>
																	<div id="deleteID"></div>
																	<div id="deletePath"></div>
																	<div id="deleteFileSize"></div></td>
															</tr>

														</c:forEach>
													</table>
												</c:if>
									</td>
								</tr> --%>
								
									<%@ include file="../common/update_attachment.jspf"%>
								
							
							
						</div>
					</div>
					<!-- End of Govt Order Details  -->
					 <div class="btnpnl">
                              
                                  
                                    <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true"  code="Button.DELETE"></spring:message>" onclick="return validate_orgnization();" />
                                    
                                     
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" />
                                
					      </div>
							  
				</div>
			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>	
		</div>
	</div>
	

</body>
</html>