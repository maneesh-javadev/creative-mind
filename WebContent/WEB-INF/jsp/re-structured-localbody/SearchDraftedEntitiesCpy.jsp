<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
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
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Label.draftedlblist"></spring:message></h3>
					</div>
					<form:form method="POST" id="formCDraftedLB" commandName="criteriaDraftedEntities" class="form-horizontal">
						<form:hidden path="localBodyCreationType"/>
						<form:hidden path="entityTempId" id="paramLBCode"/>
						<div class="box-body" id="divCenterAligned">
							<div class="box-header subheading">
			                  <h4><spring:message code='Label.Serachdrftedlocalbody' htmlEscape='true'/></h4>
			                </div>
			                <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message code='LOCALGOVTBODYNAME' htmlEscape='true'/></label>
		                      	<div class="col-sm-6">
		                        	<form:input path="paramLocalBodyName" placeholder="Please Enter Local Body Name." class="form-control"/>
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message code='Label.LOCALBODYTYPE' htmlEscape='true'/></label>
		                      	<div class="col-sm-6">
		                        	<form:select path="paramLocalBodyTypeCode" class="form-control">
								    	<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
								    	<c:forEach items="${localBodyTypeList}" var="objLBTypes">
											<form:option value="${objLBTypes.localBodyTypeCode}">
												<c:out value="${objLBTypes.name}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
								    </form:select>
		                      	</div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-3 control-label">Select Process Name</label>
		                      	<div class="col-sm-6">
		                        	<form:select path="paramActionProcessCode" class="form-control">
								    	<form:option value="-1">All</form:option>
								    	<c:forEach items="${draftedOperations}" var="objDraftedOperations">
											<form:option value="${objDraftedOperations.processId}">
												<c:out value="${objDraftedOperations.processName}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
								    </form:select>
		                      	</div>
		                    </div>
			            </div>
			            <div class="box-footer">
	                    	<div class="col-sm-offset-2 col-sm-10">
	                    		<div class="pull-right">
	                    			<input class="btn btn-primary" type="button" id="fetchDraftDetails" value="Fetch Drafted Local Bodies"/>
									<input class="btn btn-danger" type="button" id="gridDraftClose" value="Close"/>
	                        	</div>
	                    	 </div>   
                  		</div>
                  		<div class="box-body">
							<div class="box-header subheading">
			                  <h4>Local Government Body Details</h4>
			                </div>
			                <table id="example" class="table table-bordered table-striped dataTable " width="100%" cellspacing="0">
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
											<td align="center"><a id="callViewDrafted" href="#" onclick="processActionForDraftedEntity('${objDraftedLB.viewUrl}', '${objDraftedLB.entityTempId}')"><i class="fa fa-eye" aria-hidden="true"></i></a></td>
											<td align="center"><a id="callModifyDrafted" href="#" onclick="processActionForDraftedEntity('${objDraftedLB.modifyUrl}', '${objDraftedLB.entityTempId}')"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
										</tr> 
									</c:forEach>
									
								</tbody>
							</table>
			            </div>
			        </form:form>
			    </div>
			</section>
		</div>
	</section>
	<c:if test="${not empty publishMessage}">
		<script>
			$(window).load(function () {
				alert('${publishMessage}');
			});
		</script>
	</c:if>
</body>
</html>