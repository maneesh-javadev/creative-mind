<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextpthval%>/resources-localbody/css/jquery.dataTables.css">	
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.dataTables.js"></script>	
<!-- <style> 
p.wrapped {
    word-break: break-all;
}
</style> -->	
<script type="text/javascript" language="javascript" class="init">
	$(function () {
	    $(document).keydown(function (e) {
	    	return (e.which || e.keyCode) != 116;
	    });
	});
	$(document).ready(function() {
		$('#example').dataTable({
			"bJQueryUI": false,
		});
		$( "#fetchDraftDetails" ).click(function() {
			callActionUrl('getDraftedEntityDetails.htm');
		});
		$( "#gridDraftClose" ).click(function() {
			callActionUrl('home.htm');
		});
		
	   	processActionForDraftedEntity = function (paramURL, paramCode){
			$( '#paramLBCode' ).val(paramCode);
			callActionUrl( paramURL );
		};
	});
	var callActionUrl = function (url) {
		$( 'form[id=formCDraftedLB]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
		$( 'form[id=formCDraftedLB]' ).attr('method','post');
		$( 'form[id=formCDraftedLB]' ).submit();
	};
</script>
</head>
<body class="dt-example">
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Label.draftedlblist"></spring:message></h3>
			<ul class="item_list">
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form method="POST" id="formCDraftedLB" commandName="criteriaDraftedEntities">
				<form:hidden path="localBodyCreationType"/>
				<form:hidden path="entityTempId" id="paramLBCode"/>
				<%-- <form:hidden path="localBodyCode" id="paramLBCode"/> --%>
					<!-- Block for Search Criteria for Drafted Local Government Body. -->
					<div id="divCenterAligned" class="form_stylings">
						<div class="form_block">
							<div class="col_1">
								<h4><spring:message code='Label.Serachdrftedlocalbody' htmlEscape='true'/></h4>
								<ul class="form_body" >
									<li>
										<label class="inline">
											<spring:message code='LOCALGOVTBODYNAME' htmlEscape='true'/>
									    </label>
									    <form:input path="paramLocalBodyName" placeholder="Please Enter Local Body Name."/>
									</li>
									<li>
										<label class="inline">
											<spring:message code='Label.LOCALBODYTYPE' htmlEscape='true'/>
									    </label>
									    <form:select path="paramLocalBodyTypeCode">
									    	<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
									    	<c:forEach items="${localBodyTypeList}" var="objLBTypes">
												<form:option value="${objLBTypes.localBodyTypeCode}">
													<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
												</form:option>
											</c:forEach>
									    </form:select>
									</li>
									<li>
										<label class="inline">
											Select Process Name
									    </label>
									    <form:select path="paramActionProcessCode">
									    	<form:option value="-1">All</form:option>
									    	<c:forEach items="${draftedOperations}" var="objDraftedOperations">
												<form:option value="${objDraftedOperations.processId}">
													<c:out value="${objDraftedOperations.processName}" escapeXml="true"></c:out>
												</form:option>
											</c:forEach>
									    </form:select>
									</li>
									<li>
									    <label class="inline">&nbsp;</label>
										<input class="bttn" type="button" id="fetchDraftDetails" value="Fetch Drafted Local Bodies"/>
										<input class="bttn" type="button" id="gridDraftClose" value="Close"/>	
									</li>
								</ul>	
							</div>
						</div>
					</div>
					<br/>
					<!-- Block for Search Criteria for Drafted Local Government Body Ends. -->			
				
					<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h4>Local Government Body Details</h4>
							<ul class="form_body">
								<li>
									<table id="example" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												<td colspan="6">
													<span class="error" align="right"><strong>Note : Please Select View or Modify to delete Draft Changes.</strong></span>
												</td>
											</tr>
											<tr>
												<th><spring:message code='LOCALBODYNAMEENGLISH' htmlEscape='true'/></th>
												<th><spring:message code='LOCALBODYNAMEINLOCAL' htmlEscape='true'/></th>
												<th><spring:message code='Label.LOCALBODYTYPE' htmlEscape='true'/></th>
												<th>Process Name</th>
												<th><spring:message code='Label.VIEW' htmlEscape='true'/></th>
												<th><spring:message code='Label.MODIFY' htmlEscape='true'/></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="objDraftedLB" items="${draftedEntitiesList}">
												<tr>
													<td><p class="wrapped"><c:out value="${objDraftedLB.localBodyNameEnglish}" escapeXml="true"></c:out></p></td>
													<td><p class="wrapped"><c:out value="${objDraftedLB.localBodyNameLocal}" escapeXml="true"></c:out></p></td>
													<td><c:out value="${objDraftedLB.localBodyTypeName}" escapeXml="true"></c:out></td>
													<td><c:out value="${objDraftedLB.processName}" escapeXml="true"></c:out></td>
													<td align="center"><a id="callViewDrafted" href="#" onclick="processActionForDraftedEntity('${objDraftedLB.viewUrl}', '${objDraftedLB.entityTempId}')"><spring:message code='Label.VIEW' htmlEscape='true'/></a></td>
													<td align="center"><a id="callModifyDrafted" href="#" onclick="processActionForDraftedEntity('${objDraftedLB.modifyUrl}', '${objDraftedLB.entityTempId}')"><spring:message code='Label.MODIFY' htmlEscape='true'/></a></td>
												</tr> 
											</c:forEach>
											
										</tbody>
									</table>
								</li>
							</ul>
						</div>
					</div>
					<!-- Block for Drafted Local Government Body Details in Tabular Format Ends Here. -->
					<br/>
				</form:form>
			</div>
		</div>
		<!-- Page Content ends -->
	</div>
	<!-- Main Form Styling ends -->
	<c:if test="${not empty publishMessage}">
		<script>
			$(window).load(function () {
				customAlert('${publishMessage}');
			});
		</script>
	</c:if>
</body>
</html>