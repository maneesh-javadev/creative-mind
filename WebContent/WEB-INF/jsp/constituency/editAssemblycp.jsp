<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>


<title>E-Panchayat</title>

<!-- <script type="text/javascript" src="js/district.js" charset="utf-8"></script>
 --><!-- <script type="text/javascript" src="js/createDistrict.js"></script> -->
<script src="js/Parliament.js"></script> 
<script src="js/govtorder.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>

<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);


function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = 'viewAssemblyCocnstituencyPagination.htm';
	document.forms['form1'].submit();
}
function validate()
{
			document.forms['form1'].submit();
		
}
/* function validateform()

{
		var districtName=document.getElementById('districtNameInEn').value;
	
	if(districtName=="")
	{
	alert("Please Enter Name Of the Districit");
	document.getElementById('districtNameInEn').focus();
	return false;
	}
	return true;
} */


function selectDistrict(id,name)
{     
	
		if (id.match('PART')=='PART'){
		
	var selObj=document.getElementById('ddDestDistrict');	
	
	//var selObj2=document.getElementById('surveyListNew');
	//var selObj=document.getElementById('subDistrictListNew');	
	/*var subDistrict="";
		for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrict +=selObj.options[i].value+",";
	 }*/
		lgdDwrStateService.getDistricts(id, {
		callback : handleDistrictSuccess,
		errorHandler : handleDistrictError
	});
	
		
	}else {
		dwr.util.removeAllOptions('villageList');
		alert("Kindly Select the Part State From the List");
	}
	}

function handleDistrictSuccess(data) {
	// Assigns data to result id
	
	var fieldId = 'ddDistrict';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	

	dwr.util.addOptions(fieldId, data,valueText,nameText);
	
}

function handleDistrictError() {
	// Show a popup message
	alert("No data found!");
}
	//DWR Dropdownlist Population


	//DWR Dropdownlist Population

	

function formSubmitAdd(){
	document.getElementById('addAnotherSD').value="true";
	selectFinal();
}
	

	
</script>
</head>

<body onload="">

<section class="content">
  <div class="row" id="frmcontent">
	 <section class="col-lg-12">
	   <div class="box">
		 <div class="box-header with-border">
			<h3 class="box-title"><spring:message htmlEscape="true"  code="Label.MODIFYASSEMBLY"></spring:message></h3>
		 </div>
		
	<form:form class="form-horizontal" action="" method="POST" commandName="AssemblyForm"  id="form1">
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewAssemblyCocnstituencyPagination.htm"/>"/>
				
	<div class="box-body">				       
		<div class="col-sm-12">
			<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.MODIFYASSEMBLY"></spring:message></h4></div>
		
			
 
			 	 <c:if test="${! empty SEARCH_Assembly_RESULTS_KEY}">
					<div class="dataTables_wrapper form-inline dt-bootstrap" id="divData">				  
						<div class="row">
							<div class="col-sm-12 ">
								<div class="table-responsive">
									<table class="table table-bordered table-striped dataTable no-footer" cellspacing="0" width="100%">
			        					<thead>
											<tr>
												<th rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th rowspan="2"><spring:message htmlEscape="true" 	code="Label.ASSEMBLYCONSTITUENCYNAMEINENG"></spring:message></th>
												<th rowspan="2"><spring:message htmlEscape="true" 	code="Label.ASSEMBLYCONSTITUENCYNAMEINLOCAL"></spring:message></th>
												<th rowspan="5"><spring:message htmlEscape="true"  code="Label.ECICODE"></spring:message></th>
											</tr>
											<tr>
												<th><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
												<th><spring:message htmlEscape="true"  code="Label.MODIFY"></spring:message></th>
											</tr>
										</thead>
										   <tbody>
											 <c:forEach var="Parliamentconstituencymodify" varStatus="listDistrictRow" items="${SEARCH_Assembly_RESULTS_KEY}">
												<tr>
													<td width="6%"><c:out value="${offsets*limits+(listDistrictRow.index+1)}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${Parliamentconstituencymodify.constituency_name_english}" escapeXml="true"></c:out></td>
                                                    <td align="left"><c:out value="${Parliamentconstituencymodify.constituency_name_local}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${Parliamentconstituencymodify.eci_code}" escapeXml="true"></c:out></td>		
													<td align="center"><a href="viewAssemblyConstituency.htm?id=${Parliamentconstituencymodify.constituency_code}&<csrf:token uri="viewAssemblyConstituency.htm"/>" width="18" height="19" border="0"><i class="fa fa-eye" aria-hidden="true"></i></a></td>
													<td align="center"><a href="modifyAssemblyConstituency.htm?id=${Parliamentconstituencymodify.constituency_code}&<csrf:token uri="modifyAssemblyConstituency.htm"/>" width="18" height="19" border="0"><i class="fa fa-pencil" aria-hidden="true"></i></a></td> 
									           </tr>
								 			 </c:forEach>	
										</tbody>
									</table>
									
									<div class="pull-right">
									<table>
									 <tr>
										<td width="150" align="right" class="pageno"><c:out value="${AssemblyCount}" escapeXml="true"></c:out></td>
										<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true"  code="Label.PREVIOUS"></spring:message></a></td>
										<td width="24" align="center" class="pageno">|</td>
										<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(1)"><spring:message htmlEscape="true"  code="Label.NEXT"></spring:message></a></td>
										<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
									</tr>
								  </table>
								 </div>
								</div>
							</div>
						</div>
			 		</div>
				</c:if>

				<c:if test="${fn:length(viewPage) > 0}"> 
					<c:if test="${empty SEARCH_Assembly_RESULTS_KEY}">
						<div class="form-group" id="divData">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="error.NOASSEMBLYFOUND"></spring:message></label>
								<div class="col-sm-6"></div>
						</div>
					</c:if>
				</c:if>
			  
			</div>
		  </div>
		</form:form>
	  </div>
	</section>
  </div>
</section>	
<input type="hidden" name="direction" id="direction" value="0" />
</body>
</html>

