<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> `
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
<script type="text/javascript" language="javascript">
//jquery pagination  
 $(document).ready(function() {
	$('#modifyblockCorrectiontable').DataTable({
		
	});
} ); 
// jquery pagination  


function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
	if( isEmptyObject(entityCode) ){
		/* customAlert("No Record(s) found."); */
		$('#customAlertbody').text("No Record(s) found.");
		$('#customAlert').modal('show');
		return false;
	}
	$('#myIframe').attr('src',  cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");
	$('#dialogBXTitle').text('<spring:message htmlEscape="true" code="Label.MODIFYBLOCK"></spring:message>');
	$('#dialogBX').modal('show'); 
}; 

</script>
</head>
	<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<section class="content">
		
			<div class="row">
        		<section class="col-lg-12 ">
	            	<div class="box ">
           				<div class="box-header with-border">
		                	<h3 class="box-title"><spring:message htmlEscape="true" code="Label.VIEWBLOCK"></spring:message></h3>
		                </div>
		                <div class="box-body">
		                
			                <form:form action="viewblock.htm" id="form1" method="POST" commandName="blockView">
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewblock.htm"/>"/>
								<form:input type="hidden" path="dlc" />	
									<div class="box-header subheading">
										<h4><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></h4>
			                    	</div>
									<c:if test="${empty SEARCH_BLOCK_RESULTS_KEY}">
									
										<div class="form-group">
											<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
											<div class="col-sm-6">
													<form:select path="districtNameEnglish" class="form-control"  id="ddSourceDistrict" >
											         <form:option selected="selected" value="" label="--select--" />
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
											</div> 
										</div>
										
									</c:if>
									<c:if test="${! empty SEARCH_BLOCK_RESULTS_KEY}">			
																<table class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" id="modifyblockCorrectiontable">
																	<thead>
																	
																		<tr>
																			<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
																			<th rowspan="2"><spring:message htmlEscape="true" 	code="Label.BLOCKCODE"></spring:message></th>
																			<th rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message></th>
																			<th rowspan="2"><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINLOCAL"></spring:message></th>
																			<th colspan="4" style="text-align: center;"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
																		</tr>
																		<tr>
																			<th><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
																			<%-- <th><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></th> --%>
																			<th><spring:message htmlEscape="true" code="Label.CHANGE.EFFECTIVE.DATE"></spring:message></th>
																			<th><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
																			<th><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach var="blockWiseEntityDetails" varStatus="listBlockRow" items="${SEARCH_BLOCK_RESULTS_KEY}">
																			<tr>
																				<td ><c:out value="${offsets*limits+(listBlockRow.index+1)}" escapeXml="true"></c:out></td>
																				<td><c:out value="${blockWiseEntityDetails.blockCode}" escapeXml="true"></c:out></td>
																				<td><c:out value="${blockWiseEntityDetails.blockNameEnglish}" escapeXml="true"></c:out></td>
																				<td><c:out value="${blockWiseEntityDetails.blockNameLocal}" escapeXml="true"></c:out></td>
																				<td><a href="#" onclick="javascript:viewEntityDetailsInPopup('${blockWiseEntityDetails.blockCode}','viewBlockDetailInDialog.htm','blockId');"><i class="fa fa-eye" aria-hidden="true"></i></a></td>
																				<%-- <td><a href="#" onclick="javascript:manageBlock('viewBlockHistory.htm',${blockWiseEntityDetails.blockCode},'A');"><i class="fa fa-history" aria-hidden="true"></i></a></td> --%>
																				<td><a href="#" onclick="javascript:manageBlock('modifyBlockChangeEffectiveDate.htm',${blockWiseEntityDetails.blockCode});" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
						 													 	<td><a href="#" onclick="javascript:manageBlock('modifyBlockCrbyId.htm',${blockWiseEntityDetails.blockCode},'${blockWiseEntityDetails.operation_state}');" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
						 														<td><a href="#" onclick="javascript:manageBlock('modifyBlock.htm',${blockWiseEntityDetails.blockCode},'${blockWiseEntityDetails.operation_state}');"><i class="fa fa-pencil"  aria-hidden="true"></i></a></td>
						 													</tr>
																		</c:forEach>
																	</tbody>
														          </table>
													             
										</c:if>
									<c:if test="${fn:length(viewPage) > 0}"> 
										<c:if test="${empty SEARCH_BLOCK_RESULTS_KEY}">
													<spring:message htmlEscape="true"  code="error.NOBLOCKFOUND"></spring:message>
									   </c:if>
			     					</c:if>
									<input type="hidden" name="direction" id="direction" value="0" />
			     					<input type="hidden" name="pageType" value="B" />
			     					<form:input path="blockId" type="hidden" name="blockId" id="blockId"  />
							</form:form>
						
		                <script src="/LGD/JavaScriptServlet"></script>	
		                </div>
		                <c:if test="${empty SEARCH_BLOCK_RESULTS_KEY}">
		                	<div class="box-footer">
						                     <div class="col-sm-offset-2 col-sm-10">
						                       <div class="pull-right">
						                        <button type="button" name="Submit" class="btn btn-success" onclick="validate(1);"><i class="fa fa-floppy-o"></i> <spring:message code="Button.GETDATA" htmlEscape="true"></spring:message></button>
						                        <button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='viewblock.htm?<csrf:token uri='viewblock.htm'/>';"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
						                        <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
						                        </div>
						                     </div>   
                				</div>
                		</c:if>
		            </div>
		        </section>
		    </div>
		
		</section>
	</body>
</html>


<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >

<div class="modal-dialog" style="width:950px;height:700px">
		<div class="modal-content">
 				<div class="modal-header">
  				   <button type="button" class="close" data-dismiss="modal">&times;</button>
   			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
   			  	
 				</div>
 				<div class="modal-body" id="dialogBXbody">
       			<iframe id="myIframe" width="910" height="650"></iframe>
       			
    			 
			</div>
			
</div>
								</div>
							</div>
