<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State" %>

<%!String contextPath;%>
<%!int pageNumber = 1;%>
<%
	contextPath = request.getContextPath();
	String jsPath = contextPath + "/js";
%>
<head>
<!--Expend and Collaps Section of Form-->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/animatedcollapse.js"></script> 
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

	function submitForm() {
		document.getElementById('form3').action = "modifyLbSetup.htm?category=${category}";
		document.getElementById('btnModify').disabled = true;
		document.forms["form3"].submit();
	}
	
	function manageSetup(url,id)
	{
		
		dwr.util.setValue('tierSetupID', id, {	escapeHtml : false	});
		document.getElementById('form3').action = url;
		document.getElementById('form3').submit(); 
	}

	function correctionForm() {
		document.getElementById('form3').action = "modifyLbSetup.htm?category=${category}";
		document.getElementById('btnCorrection').disabled = true;
		document.getElementById('btnModify').disabled = true;
		document.getElementById('btnCancel').disabled = true;
		document.getElementById('isCorrection').value=true;
		document.forms["form3"].submit();
	}
	
</script>
</head>
<%-- <link href="<%=contextPath%>/css/displayTag.css" rel="stylesheet"
	type="text/css" /> --%>
<body>
	<div id="frmcontent">
		<form:form action="modifyLbSetup.htm?category=${category}" method="POST" id="form3" commandName="lGSetupForm">
			<input type="hidden" id="isCorrection" name="isCorrection"/>
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyLbSetup.htm"/>"/>
			 <div class="frmhd">
				   
				    
				    
				    <h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></h3>
			<ul id="showhelp" class="listing">
				<%-- //this link is not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
 				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
			
				   </div>
			<div class="clear"></div>
				<div class="frmpnlbrdr">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<c:if test="${getLocalGovtSetupList.size()>0}">
							<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.LBTS"></spring:message></div>
							
							
							<div class="table col_3"> <!-- Table starts -->				
									<div class="trow thead">
										<div class="th col1"><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></div>
										<div class="th col2"><spring:message htmlEscape="true"  code="Label.NIE"/></div>
										<div class="th col3"><spring:message htmlEscape="true"  code="Label.NILE"></spring:message></div>		
										<div class="th col2"><spring:message htmlEscape="true"  code="Label.PARENTLOCALBODYTYPE"></spring:message></div>
										<div class="th col3"><spring:message htmlEscape="true"  code="Label.VST"></spring:message></div>										
									</div>	
							
							<!-- <table width="100%" class="tbl_no_brdr">
								<tr>
									<td> -->
							<%-- <table width="100%" class="tbl_with_brdr">
								<tr class="tblRowTitle tblclear">
									<td><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></td>
									<td><spring:message htmlEscape="true"  code="Label.NIE"></spring:message></td>
									<td><spring:message htmlEscape="true"  code="Label.NILE"></spring:message></td>
									<td><spring:message htmlEscape="true"  code="Label.PARENTLOCALBODYTYPE"></spring:message></td>  
									<td><spring:message htmlEscape="true"  code="Label.VST"></spring:message></td>
								</tr> --%>
								<c:forEach var="current" varStatus="currentRow" items="${getLocalGovtSetupList}">
								
								
								 <div class="trow red"> 
									 <div class="td column col1"><c:out value="${current.localBodyTypeName}" escapeXml="true"></c:out></div>
	          						 <div class="td column col2"><c:out value="${current.nomenclatureEnglish}" escapeXml="true"></c:out></div> 							
	          						 <div class="td column col3"><c:out value="${current.nomenclatureLocal}" escapeXml="true"></c:out></div>
	          					     <div class="td column col4"><c:out value="${current.parentLocalBodyTypeName}" escapeXml="true"></c:out></div>
	          						<%-- <td><a href="viewSubtype.htm?st=<c:out value='${current.tierSetupCode}' /> "><img border="0" width="18" height="19" src="images/view.png"></img></a></td>	 --%>
	          				<%-- 	<td><a href="viewSubtype.htm?st=<c:out value="${current.tierSetupCode}" />&<csrf:token uri="viewSubtype.htm"/>"><img border="0" width="18" height="19" src="images/view.png"></img></a></td>	
							 --%>	
							    <%-- 	<td><a href="viewSubtype.htm?st=${current.tierSetupCode}"><img border="0" width="18" height="19" src="images/view.png"></img></a></td>	
								 --%>	
								  <div class="td column col5"><a href="#"><img src="images/view.png" onclick="javascript:manageSetup('viewSubtype.htm',${current.tierSetupCode});" width="18" height="19" border="0" /></a></div>
									
								</div>
								</c:forEach>
								 <form:input path="tierSetupID" type="hidden" name="tierSetupID" id="tierSetupID"  />	
							</div>
							<!-- </table> -->
							<!-- </tr></td>
							
							</table> -->
							</c:if>
						</div>
					</div>
					<!----TEST---->
					<c:if test="${subType.size()>0}">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">Sub Type</div>
							<div class="table col_3"> 
								<div class="trow thead">
									 <div class="th col1">Local Body Type Name</div>
									 <div class="th col2">Sub Type Name (In English)</div>
									 <div class="th col3">Sub Type Name (In Local)</div>
								</div>
								<c:forEach var="subType" varStatus="listVillageDetailsRow" items="${addVillageNew.subType}">

									<div class="trow red"> 
										<spring:bind
											path="addVillageNew.subType[${listVillageDetailsRow.index}].localBodyTypeName">

											<div class="td column col1">
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></div>
										</spring:bind>

										<spring:bind
											path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameEng">
											<div class="td column col1">
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></div>
										</spring:bind>
										<spring:bind
											path="addVillageNew.subType[${listVillageDetailsRow.index}].subtypeNameLocal">
											<div class="td column col1">
												<option name="${status.expression}" />
												<c:out value="${status.value}" escapeXml="true"></c:out>
												</option></div>
										</spring:bind>
                                  	</div>
								</c:forEach>
							</div>
								<!-- <td align="right"> -->
							<div class="btnpnl">
								<ul class="listing">
				                <li>
									<!--  <td align="right" class="pageno">(1 - 50 of 464)</td>  -->
									<a href="#">Previous</a>
									<div class="td column col1"></div>
									<!-- <td width="2" align="center" class="pageno">|</td> -->
									<!-- <td width="50" align="right" class="nxt" style="padding: 0px"> -->
									<div class="td column col1"><a
										href="#">Next</a></div>
									<!-- <td width="8" align="right" class="nxt" style="padding: 0px">&nbsp;</td> -->
								</li>
								</ul>
							</div>
								<!-- </td>
							</tr>
							</table> -->
							
							
						<%-- <ul class="listing">
				      <li>
                              <label>
                              <input type="button" id="btnSave" onclick="submitForm()" class="btn" name="Submit" value="<spring:message htmlEscape="true"  code="Button.UPDATE"/>" />
							  </label>
							 <label>
                              <input type="button" class="btn" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                              </label> </li>
                      </ul>	 --%>
							
							
							
							
							
						</div>
					</div>
					</c:if>					
				<div class="btnpnl">
							<input type="button" id="btnCorrection" onclick="correctionForm()" value="Correction" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div style="display: none;"><input type="button" id="btnModify" onclick="submitForm()" value="Modify" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
							<input type="button" id="btnCancel" value="Close" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/>
						</div>
				</div>				
		</form:form>
	</div>
 <script src="/LGD/JavaScriptServlet"></script>
 
 <c:if test="${isCorrectionDone}">
	 <div id="dialog-message" title="Operational Message"> <esapi:encodeForHTML>${successmsg}</esapi:encodeForHTML></div>
	 <script>
		$(function() {		
			$("#dialog:ui-dialog").dialog("destroy");
			$("#dialog-message" ).dialog({
				modal: true,
				buttons: {
					Ok: function() {$( this).dialog("close");}
				}
			});
		});
	 </script>
  </c:if>
</body>
</html>