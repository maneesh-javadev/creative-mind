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

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/addBlock.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<!-- jquery pagination  -->
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
	<!-- jquery pagination  -->

<script type="text/javascript" language="javascript">



</script>
<script type="text/javascript" language="javascript">
//jquery pagination  
$(document).ready(function() {
	$('#modifyblockCorrectiontable').dataTable({
		"sScrollY": "200px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			],
		"sPaginationType": "full_numbers"
	});
} );
// jquery pagination  


function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
<title><spring:message htmlEscape="true"  code="Label.VIEWBLOCK"></spring:message></title>
</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<div id="frmcontent">
			<div class="frmhd">
				<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWBLOCK"></spring:message></h3>
				<ul class="listing">
					<li>
						<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
					</li>
					<%-- these links are not working<li>
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
					</li>
					<li>
						<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
					</li> --%>
				</ul>
			 </div>
			<div class="clear"></div>
			
			
			<div class="frmpnlbrdr">
				<form:form action="viewblock.htm" id="form1" method="POST" commandName="blockView">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewblock.htm"/>"/>
				<form:input type="hidden" path="dlc" />	
				<c:if test="${empty SEARCH_BLOCK_RESULTS_KEY}">		
					   
				   <div class="frmpnlbg" id='showbydropdown' >
					     <div class="frmtxt">
						     <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTCRITERIA"></spring:message></div>
								<div class ="block">
									<ul class="blocklist">
										<li>
											<label><spring:message  htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
									         <form:select path="districtNameEnglish" class="combofield" style="width: 150px" id="ddSourceDistrict" >
									         <form:option selected="selected" value="" label="--select--" />
									         <%-- <form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options> --%>
									         
									         			   <c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out> 																				
																</form:option>
															</c:if>
														</c:forEach>
									         
									         </form:select> <span class="error" id="ddSourceDistrict_error"></span>
									         <form:errors htmlEscape="true"  path="districtNameEnglish" class="errormsg"></form:errors>
										 </li>
										 <li>
										 	<input type="button" name="Submit" class="btn" onclick="validate(1);" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" /> </label><label>
											<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='viewblock.htm?<csrf:token uri='viewblock.htm'/>';"  /> </label><label>
											<input type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										 </li>
									</ul>								 
								</div> 
							</div>
						</div>
					</c:if>
					<c:if test="${! empty SEARCH_BLOCK_RESULTS_KEY}">			
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center">
										<table cellpadding="0" cellspacing="0" border="0" class="display" id="modifyblockCorrectiontable">
											<thead>
											
												<tr class="tblRowTitle tblclear">
													<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
													<th rowspan="2"><spring:message htmlEscape="true" 	code="Label.BLOCKCODE"></spring:message></th>
													<th rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message></th>
													<th rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINLOCAL"></spring:message></th>
													<th align="center" colspan="4"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
												</tr>
													<tr class="tblRowTitle tblclear">
	
													<th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></th>
												
													</tr>
											</thead>
											<tbody>
												<c:forEach var="blockWiseEntityDetails" varStatus="listBlockRow" items="${SEARCH_BLOCK_RESULTS_KEY}">
														<tr class="tblRowB">
															<td ><c:out value="${offsets*limits+(listBlockRow.index+1)}" escapeXml="true"></c:out></td>
															<td><c:out value="${blockWiseEntityDetails.blockCode}" escapeXml="true"></c:out></td>
															<td align="left"><c:out value="${blockWiseEntityDetails.blockNameEnglish}" escapeXml="true"></c:out></td>
															<td align="left"><c:out value="${blockWiseEntityDetails.blockNameLocal}" escapeXml="true"></c:out></td>
															<td align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageBlock('viewBlockDetail.htm',${blockWiseEntityDetails.blockCode},'A');" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/history.png" onclick="javascript:manageBlock('viewBlockHistory.htm',${blockWiseEntityDetails.blockCode},'A');" width="18" height="19" border="0" /></a></td>
	 													 	<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageBlock('modifyBlockCrbyId.htm',${blockWiseEntityDetails.blockCode},'${blockWiseEntityDetails.operation_state}');" width="18" height="19" border="0" /></a></td>
	 														<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageBlock('modifyBlock.htm',${blockWiseEntityDetails.blockCode},'${blockWiseEntityDetails.operation_state}');" width="18" height="19" border="0" /></a></td>
	 													</tr>
													</c:forEach>
											</tbody>
								          </table>
									       </td>
								         </tr>   
								        
								       <tr>
							               <td> <form:input path="blockId" type="hidden" name="blockId" id="blockId"  />
							               
							               	</td>
						               </tr>  
							</table>
						  </div>
					  </div>
					</c:if>
					<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_BLOCK_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
						<ul class="blocklist">
							<li>
								<spring:message htmlEscape="true"  code="error.NOBLOCKFOUND"></spring:message>
							</li>
						</ul>
						</div>
				     </div>
				   </c:if>
			     </c:if>
			     <input type="hidden" name="direction" id="direction" value="0" />
			     <input type="hidden" name="pageType" value="B" />
				</form:form>
				<script src="/LGD/JavaScriptServlet"></script>	
			</div>
		</div>
	</body>
</html>
