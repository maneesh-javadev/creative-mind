<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();

%>
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/manageVillage.js" charset="utf-8"></script>
<script>
$(document).ready(function() {

	 $('#dialogBX').draggable({
		    handle: ".modal-header"
		});
	 
	 $('#manageVillage').dataTable({
		});
});

var _JS_DISTRICT_CODE =0;
if(!isEmptyObject(_JS_DISTRICT_CODE)){
	var _JS_DISTRICT_CODE = parseInt('${villagebean.districtCode}');
}

var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
	if( isEmptyObject(entityCode) ){
		$('#customAlertbody').text("No Record(s) found.");
		$('#customAlert').modal('show');
		return false;
	}
	$('#myIframe').attr('src',  cusurl + "?" + paramName + "=" + entityCode+ "&<csrf:token uri='" + cusurl + "'/>");
	$('#dialogBXTitle').text('<spring:message code="Label.MANAGEVILLAGE" htmlEscape="true"></spring:message>');
	$('#dialogBX').modal('show'); 
}; 


function manageVillage1(url,id,optState,operationState)
{
	if(optState=='P' && operationState=='A'){
		dwr.util.setValue('villageId', id, {	escapeHtml : false	});
		document.getElementById('form1').method = "GET";
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
	}else if(optState=='S' || operationState=='F'){
		draftModeAlert();
		return false;
	}
}

</script>

<title><spring:message htmlEscape="true" code="Label.MANAGEVILLAGE"></spring:message></title>


</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();loadRadio();" >
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message code="Label.MANAGEVILLAGE" htmlEscape="true"></spring:message></h3>
				</div>
					
				
				<form:form class="form-horizontal" action="viewvillage.htm" method="POST" commandName="villagebean" id="form1">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewvillage.htm"/>"/>
				  
				
				
	<div class="box-body">				       
		<c:if test="${!villagebean.isDataDiv}">
		<div class="col-sm-12">
		<div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.SELPARENT"></spring:message></h4></div>
		
		 
		
		
			<div class="form-group">							
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">					           
		          <form:select path="districtCode" class="form-control" id="ddSourceDistrict">
							 <c:if test="${villagebean.districtCode eq 0}">
				          		 <form:option value="" htmlEscape="true"><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
							</c:if>
					          <c:forEach items="${villagebean.districtList}" var="districtList">
								<c:if test="${districtList.operation_state =='A'.charAt(0)}">
									<form:option value="${districtList.districtCode}" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
									</form:option>
								</c:if>
								<c:if test="${districtList.operation_state =='F'.charAt(0)}">
									<form:option value="${districtList.districtCode}" disabled="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																			
									</form:option>
								</c:if>
							</c:forEach>	
				  </form:select>
				</div>
           </div>
           
           
           <div class="form-group">							
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">					           
		          <form:select path="subdistrictCode" class="form-control" id="ddSourceSubDistrict">
						</form:select>
				</div>
           </div>        
                    
						
			



	
	
  <div class="box-footer">
	  <div class="col-sm-offset-2 col-sm-10"> 
	  <div class="pull-right">
		 <button type="submit" class="btn btn-primary" id="actionFetchDetails"> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
	 
		  <button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='viewvillage.htm?<csrf:token uri='viewvillage.htm'/>';"><spring:message htmlEscape="true" code="Button.CLEAR"></spring:message></button>
		  <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
	</div>
	</div>
  </div>
  
</div>
</c:if>
 
 	<c:if test="${villagebean.isDataDiv}">
		<div class="dataTables_wrapper form-inline dt-bootstrap">				  
			<div class="row">
				<div class="col-sm-12 ">
					<div class="table-responsive">
						<table id="manageVillage" class="table table-bordered table-striped dataTable no-footer" cellspacing="0" width="100%">
        					<thead>
								<tr>
									<th  rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
									<th rowspan="2"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></th>
									<th  rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></th>
									<th colspan="5" style="text-align: center;"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
								</tr>
								<tr>
									<th><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
									<th><spring:message htmlEscape="true" code="Label.CHANGE.EFFECTIVE.DATE"></spring:message></th>
									<th><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
									<th><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></th>
									<th><spring:message htmlEscape="true" code="LABEL.CHANGEVILLAGESTATUS"></spring:message></th>
									
									<!-- <th>GIS Map View</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="listVillageDetail" varStatus="listVillageRow" items="${villageList}">
									<tr class="tblRowB" >
										<td><c:out value="${offsets*limits+(listVillageRow.index+1)}" escapeXml="true"></c:out></td>
										<td><c:out value="${listVillageDetail.villageCode}" escapeXml="true"></c:out></td>
										<c:if test="${listVillageDetail.operation_state =='A'.charAt(0)}" >	
											<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
										</c:if>
										<c:if test="${listVillageDetail.operation_state =='F'.charAt(0)}" >	
											<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out>(Village in draft mode)</td>
										</c:if>
										<td align="center">
										
										<a href="#" onclick="javascript:viewEntityDetailsInPopup('${listVillageDetail.villageCode}', 'viewVillageDetail.htm', 'id');" >
										<i class="fa fa-eye" aria-hidden="true"></i></a>
										</td>
										<td align="center"><a href="#" onclick="javascript:manageVillage1('modifyVillageChangeEffectiveDate.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}','${listVillageDetail.operation_state}');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
										<td align="center"><a href="#" onclick="javascript:manageVillage1('modifyVillageCrbyId.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}','${listVillageDetail.operation_state}');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
										<td align="center"><a href="#" onclick="javascript:manageVillage1('modifyVillagechangebyId.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}','${listVillageDetail.operation_state}');"><i class="fa fa-pencil"  aria-hidden="true"></i></a></td>
										<td align="center"><a href="#" onclick="javascript:manageVillage1('modifyVillageStatus.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}','${listVillageDetail.operation_state}');"><i class="fa fa-pencil"  aria-hidden="true"></i></a></td>
										
										<%-- <td align="center">
											<c:if test="${listVillageDetail.operation_state =='A'.charAt(0)}" >	
												<a href="#" onclick="callGISMapView('${subDistrictCode}','${listVillageDetail.villageCode}','${listVillageDetail.villageNameEnglish}','MV')"><i class="fa fa-map-marker" aria-hidden="true"></i></a>
												
											</c:if>
										</td> --%>
									</tr>
								</c:forEach>
								<c:if test="">
								<tr>
								<td colspan="8" rowspan="2">
								<spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message>
								</td>
								</tr>
								</c:if>
								
							</tbody>
						</table>
						<form:input path="villageId" type="hidden" name="villageId" id="villageId"  />
						 <div class="box-footer">
						  <div class="col-sm-offset-2 col-sm-10"> 
						  <div class="pull-right">
							  <button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='viewvillage.htm?<csrf:token uri='viewvillage.htm'/>';"><spring:message htmlEscape="true" code="Button.BACK"></spring:message></button>
							  <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
						</div>
						</div>
					  </div>
					</div>
				</div>
			</div>
 		</div>
	</c:if>


			

		</div>
				
				<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:1050px;height:700px">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="1000" height="650"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
							</div>
				
				
				</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		   </div>
		</section>
	</div>
</section>
	
<script type='text/javascript'>
		$(window).load(function () {
			
					setTimeout(function(){
						$("#ddSourceSubDistrict option[value='<c:out value="${villagebean.districtCode}"/>']").attr("selected", "selected");
					}, 200);
				
			
		});
	</script>		
		
</body>
</html>

