<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Update Pesa Status</title>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	
</script>
<script src="js/lgd_localbody.js"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
<script type="text/javascript" src="js/invalidatelocalbody.js"
	charset="utf-8"></script>

<script> 
$(document).ready(function() {
	var type= '<c:out value="${flag}" escapeXml="true"></c:out>';
	if (type == 0) {
		document.getElementById('firstbox').style.visibility = 'visible';
		document.getElementById('firstbox').style.display = 'block';
		document.getElementById('secondbox').style.visibility = 'hidden';
		document.getElementById('secondbox').style.display = 'none';
		
	} else if (type == 1) {
		document.getElementById('secondbox').style.visibility = 'visible';
		document.getElementById('secondbox').style.display = 'block';
		document.getElementById('firstbox').style.visibility = 'hidden';
		document.getElementById('firstbox').style.display = 'none';
		document.getElementById('divLgdLBType').style.visibility = 'hidden';
		document.getElementById('divLgdLBType').style.display = 'none';
		earlyChecked = $('input:checkbox:checked').map(function() {
			return this.value;
		}).get();
		
	}
	else if (type == 3) {
		document.getElementById('firstbox').style.visibility = 'visible';
		document.getElementById('firstbox').style.display = 'block';
		document.getElementById('secondbox').style.visibility = 'hidden';
		document.getElementById('secondbox').style.display = 'none';
		
	}
	$("#tbl_with_brdr tr:even").css("background-color", "#ffffff");
	$("#tbl_with_brdr tr:odd").css("background-color", "#dedede");
	
} );
</script>
</head>
<body>

<section class="content">
	<div class="row" id="frmcontent">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message htmlEscape="true" text="Update Pesa Status" code="Label.UPDATEPESASTATUS"></spring:message></h3>
				</div>
					
				
				<form:form class="form-horizontal" action="getLBPesaRecords.htm" id="form1" commandName="localGovtBodyForm" method="POST">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="getLBPesaRecords.htm"/>" />
				<input type="hidden" name="choosenlb" id="choosenlb" value="" />
				<input type="hidden" id="listformat" name="listformat" value="" />

				<div class="box-body" id='divLgdLBType'>				       
					<div class="col-sm-12">
						<div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></h4></div>
							<div id="divLgdVillageP">
							 
						<div class="form-group">							
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTICTPANCHAYAT"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6">					           
						          <form:select path="lgd_LBDistrictAtVillage" class="form-control" id="districtpanchyatId" htmlEscape="true" onchange="clearlbPesaInputErrors();getWorkOnVillagePanchayatforDistrictP(this.value);"
									<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
										<c:forEach items="${districtPanchayatList}" var="distListvar">
											 <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												<form:option value="${distListvar.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${distListvar.localBodyNameEnglish}</esapi:encodeForHTMLAttribute></form:option>
											 </c:if>  
											 
											 <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												<form:option value="${distListvar.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${distListvar.localBodyNameEnglish}</esapi:encodeForHTMLAttribute></form:option>
											</c:if>
										</c:forEach>
							     </form:select>
							     <div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true" /></div>
								 <div id="districtpanchyatIdError" style="color: red;"></div>
							</div>
					    </div>
				
						<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELINTERMPANCHYAT"></spring:message><span class="mandatory">*</span></label>
							 <div class="col-sm-6">
								  <form:select onchange="clearlbPesaInputErrors()" htmlEscape="true" path="lgd_LBIntermediateAtVillage" class="form-control" id="ddlgdLBInterAtVillage">
									  <form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></form:option>
								  </form:select> &nbsp;
								  <div id="intermediatepanchyatIdError" style="color: red;"></div>
						    </div>
					     </div>
				      </div>
			       </div>
		        </div>
					
				 <div id="manageEntity">
					<c:if test="${! empty lbList}">
						<div class="dataTables_wrapper form-inline dt-bootstrap" id="divData">				  
							<div class="row">
								<div class="col-sm-12 ">
									<div class="table-responsive">
										<table class="table table-bordered table-striped dataTable no-footer" width="100%" align="center" id="tbl_with_brdr"  border="1">
				        					<thead>
												<tr>
													<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
													<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message></th>
													<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true" code="Label.LOCALBODYNAMEINENG"></spring:message></th>
													<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true" code="Label.NAMEINLOCALLANGUAGE"></spring:message></th>
													<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></th>
													<th rowspan="2" style="border:1px solid black;"><spring:message htmlEscape="true" text="Update Pesa Status" code="Label.UpdatePesaStatus"></spring:message></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="adminEntityDetail" varStatus="listAdminRow" items="${lbList}" >
													<tr>
														<td style="border:1px solid black;"><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
														<td style="border:1px solid black;"><c:out value="${adminEntityDetail.localBodyCode}" escapeXml="true"></c:out></td>
														<td align="center" style="border:1px solid black;"><c:out value="${adminEntityDetail.localBodyNameEnglish}" escapeXml="true"></c:out></td>
														<td align="center" style="border:1px solid black;"><c:out value="${adminEntityDetail.localBodyNameLocal}" escapeXml="true"></c:out></td>
														<td align="center" style="border:1px solid black;"><c:out value="${adminEntityDetail.stateSpecificCode}" escapeXml="true"></c:out></td>
														<td align="center" style="border:1px solid black;"><input class="messageCheckbox" type="checkbox" value="<c:out value="${adminEntityDetail.localBodyCode}" escapeXml="true"></c:out>"
															<c:if test="${adminEntityDetail.ispesa == true}">checked</c:if> />
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
				
									</div>
								</div>
							</div>
				 		</div>
					</c:if>
				</div>
				 <div class="box-footer">
					<div class="col-sm-offset-2 col-sm-10"> 
					  <div class="pull-right">
						<div id="firstbox">
							<button type="submit" class=" btn btn-primary" onclick="return checkRequiredInputsforPesa(); " name="Submit" class="btn" >Proceed</button> 
							<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   </div>
					   <div id="secondbox">
						   <button type="button" value="" onclick="updatePesaRecords();" class="btn btn-success">Save</button>
						   <button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					  </div>
					</div>
				</div>
			 </div>
			</form:form>
		  </div>
		</section>
	 </div>
   </section>
</body>
</html>

