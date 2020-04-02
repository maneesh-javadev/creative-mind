<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/Parliament.js"></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>

<link rel="stylesheet" href="datepicker/demos.css" />
 -->
</head>
<body onload="">
	<div id="frmcontent">


		<div class="frmhd">

			<h3 class="subtitle">
				<label><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">				
				<%--//these links are not working <li>
						<a href="#" class="frmhelp"><spring:message
								code="Label.HELP" htmlEscape="true"></spring:message> </a>
				</li>
 --%>
			</ul>
		</div>

		
		
		<form:form action="AddGazettePublicationDateshow.htm" id="form1"
			commandName="governmentOrder">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="AddGazettePublicationDateshow.htm"/>" />
			<div class="clear"></div>

			<div class="frmpnlbrdr">
				<div class="frmpnlbg">

					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.ADDGAZETTEPUBLICATIONDATEHistroty" htmlEscape="true"></spring:message>
						</div>
					
					<table width="100%" class="tbl_with_brdr">
						<tr>
							<td width="14%" align="center">
								<table>
									<tr class="tblRowTitle tblclear">
										<td width="6%" ></td>
										  <td width="16%" rowspan="2"><spring:message code="Label.ORDERCODE" htmlEscape="true"></spring:message></td>
										<td width="16%" ><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message>
										</td>
										<td width="16%" ><spring:message
												code="Label.ENTITYTYPE" htmlEscape="true"></spring:message>
										</td>
										<td width="16%" ><spring:message
												code="Label.ENTITYNAME" htmlEscape="true"></spring:message>
										</td>
										<td width="21%" ><spring:message
												code="Label.ORDERDATE" htmlEscape="true"></spring:message>
										</td>
										<td width="21%" ><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
										</td>
										<td width="21%" rowspan="2"><spring:message
												code="Label.DESCRIPTION" htmlEscape="true"></spring:message>
										</td>
										<td width="21%" rowspan="2"><spring:message
												code="Label.GAZETTESTATUS" htmlEscape="true"></spring:message>
										</td>

									</tr>




									<tr>
										<td><br />
										</td>
									</tr> <c:if test="${empty gazettePublication}">
										<tr>
											<td colspan="1" align="left"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
										</tr>
									</c:if>
									<c:if test="${! empty gazettePublication}">
										<c:forEach var="gazetteDateDetail" varStatus="listDistrictRow"
											items="${gazettePublication}">
											<tr class="tblRowB">
												<td width="6%"><form:checkbox path="gazettePublicationCheckBox" value="${gazetteDateDetail.orderCode}" id="CheckBoxSelect" />
												</td>
												
												<td align="left">  <c:out value="${gazetteDateDetail.orderCode}" escapeXml="true"></c:out></td>
												<td align="left"><c:out
														value="${gazetteDateDetail.orderNo}" escapeXml="true"></c:out>
												</td>
												<td align="left"><c:out
														value="${gazetteDateDetail.entityType}" escapeXml="true"></c:out>
												</td>
												<td align="left"><c:out
														value="${gazetteDateDetail.entityNameEnglish}" escapeXml="true"></c:out>
												</td>
												<td align="left"><c:out
														value="${gazetteDateDetail.orderDate}" escapeXml="true"></c:out>
												</td>
												<td align="left"><c:out
														value="${gazetteDateDetail.effectiveDate}" escapeXml="true"></c:out>
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											

										</c:forEach>
										<form:errors path="gazettePublicationCheckBox" class="errormsg"></form:errors>
									</c:if>
								</table>
							</td>
						</tr>

						</table>
					</div>
					<div class="btnpnl">
						<ul class="blocklist tbl_no_brdr">
							<li>
								<label for="GazettePublicationdate"><spring:message
											code="Label.EGPDATE" htmlEscape="true"></spring:message>
								</label><span class="errormsg">*</span><br /> <form:input
										path="gazPubDate" id="GazettePublicationdate" type="text"
										class="frmfield" style="width: 140px"
										onkeypress="validateforLATIandLONGI(event)" htmlEscape="true"
										cssClass="frmfield"
										 /> <form:errors path="gazPubDate2"
										cssClass="errormsg"></form:errors> <span id="GazPubDate_msg"
									style="display: none"> </span>
								
								

							</li>
					<li>
						<div >
							<div class="btnpnl tbl_no_brdr">
									<label> <input
										type="button" class="btn" id="Submit"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
										onclick="selectgazetteDate();" /> 
									</label>
									<label> <input
											type="button" name="Submit2" class="btn"
											value=<spring:message code="Button.CLEAR">
			                          </spring:message>
											onclick="javascript:location.href='AddGazettePublicationDate.htm?<csrf:token uri="AddGazettePublicationDate.htm"/>'" />
									</label>
									<label> <input type="button" name="Cancel"
											class="btn"
											value='<spring:message code="Button.CANCEL" htmlEscape="true"></spring:message>'
											id="btnCancel"
											onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />
									</label>
								</div>
							</div> <br /> <br /> <br />
						</li>
					</ul>
					<div class="clear"></div>
					</div>
					</div>
				</div>
				<%-- </form> --%>
		
	</form:form>
	</div>
</body>
</html>

