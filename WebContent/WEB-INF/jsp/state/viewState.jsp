<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'/>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'/>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'/>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$('#modifystateCorrectiontable').dataTable({
		
		"bScrollCollapse": true,
		"bPaginate": true,
		
		"bJQueryUI": true,
		
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
	});
} );

function manageState(url,id){
	
	dwr.util.setValue('stateId', id, {	escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

dwr.engine.setActiveReverseAjax(true);

</script>
<title><spring:message htmlEscape="true"  code="Label.VIEWSTATE"></spring:message></title>
</head>

<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message htmlEscape="true"  code="Label.VIEWSTATE"></spring:message>
			</h3>
			 <ul id="showhelp" class="listing">
			        <li>
			           <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>                     
			        </li>
			       <%--//these links are not working  <li>
			           <a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
			       </li> --%>
		       </ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="viewstate.htm" id="form1" method="POST" commandName="statebean">	
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewstate.htm"/>"/>
			    <div id="cat">	
			    	<c:if test="${! empty SEARCH_STATE_RESULTS_KEY}">	
					<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h4><spring:message htmlEscape="true"  code="Label.STATEDETAIL"></spring:message></h4>
							<ul class="form_body">
								<li>
									<table id="modifystateCorrectiontable" class="display" cellspacing="0" width="100%">
										<thead>
											<tr class="tblRowTitle tblclear">
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.STATECODE"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.STATENAMEINENGLISH"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.STATENAMEINLOCAL"></spring:message></th>
												<th colspan="2" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
								          	</tr>
										  	<tr class="tblRowTitle tblclear">
												<th align="center"><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></th>
												<th  align="center"><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="stateWiseEntityDetails" varStatus="listStateRow" items="${SEARCH_STATE_RESULTS_KEY}">
												<tr class="tblRowB">
													<td ><c:out value="${offsets*limits+(listStateRow.index+1)}" escapeXml="true"></c:out></td>
													<td><c:out value="${stateWiseEntityDetails.stateCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${stateWiseEntityDetails.stateNameEnglish}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${stateWiseEntityDetails.stateNameLocal}" escapeXml="true"></c:out></td>													
													<td  align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageState('modifyStateCrbyId.htm',${stateWiseEntityDetails.stateCode});" width="18" height="19" border="0" /></a></td>
													<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageState('modifyStatechangebyId.htm',${stateWiseEntityDetails.stateCode});" width="18" height="19" border="0" /></a></td>
																								 												
												</tr>
											</c:forEach>
										</tbody>
									</table>
										
									<form:hidden path="stateId" name="stateId" id="stateId"  />	
									
									
								</li>
							</ul>
						</div>
					</div>
					<!-- Block for Drafted Local Government Body Details in Tabular Format Ends Here. -->	
					</c:if>
					<c:if test="${fn:length(viewPage) > 0}"> 
				   		<c:if test="${empty SEARCH_STATE_RESULTS_KEY}">
							<div class="frmpnlbg" id="divData">
								<div class="frmtxt">
									<table width="100%" class="tbl_no_brdr">					
										<tr>
											<td colspan="4" align="center"><spring:message htmlEscape="true"  code="error.NOSTATEFOUND"></spring:message></td>
										</tr>					
									</table>
								</div>
				     		</div>
				   		</c:if>
			    	</c:if>
				     <input type="hidden" name="direction" id="direction" value="0" />
				     <input type="hidden" name="pageType" value="D" />
		     	</div>
		     </form:form>	
		     <script src="/LGD/JavaScriptServlet"></script>		
		 </div>			
	</div>
</body>
</html>

