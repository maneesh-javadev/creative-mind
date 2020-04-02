<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<title><spring:message code="Label.INVAlVILL" htmlEscape="true"></spring:message>
</title>
<script type="text/javascript" src="js/invalidateVillage.js"
	charset="utf-8"></script>
	<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<!-- <script src="js/trim-jquery.js"></script> -->


<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
		<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<!-- <link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" /> -->
<title>Create Vilage</title>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	
	var villageListContri="";
	function onSubmit()
	 {   
	    if(validateULbData())
	    	{
		selectall();
		<%-- added by pooja on 17-11-2015 --%>
		var villageListNewContri = $('#villageListNewContri').val();
		for(var i=0;i<villageListNewContri.length;i++)
			villageListContri = villageListContri + villageListNewContri[i] + ",";
		lgdDwrVillageService.getMaxPreVersionEffDateOfVillages(villageListContri.substring(0,villageListContri.length-1),{
 			async : false,
 			callback : function(data) {
 				$('#preVersionEffDate').val(data);
 			},
 			errorHandler : function() {
 				alert("Error");
 			}
 		});
		// added by Kirandeep 18/06/2014
		$('input[name=Submit]').prop('disabled',true);
		document.forms['invalidateForm'].submit();
	    	}
	 }
	
	 function validateULbData() {
			
			var ddDistrict = document.getElementById('ddDistrict').value;
			var ddSubdistrict = document.getElementById('ddSubdistrict').value;
			var villageList = document.getElementById('villageListNewContri').length;
			var ddSubdistrictMerge = document.getElementById('ddLgdLBType').value;
			var villageListMainMerge = document.getElementById('ULBListNewContri').length;
			var check = true;
      		if (ddDistrict == 0) {
				alert("Please Select District");
			
				check =false;
			}
			else if (ddSubdistrict == 0 || ddSubdistrict == "") {
				alert("Please Select Subdistrict");
			
				check =false;
			}
			else if (villageList == 0) {
				alert("Please Select Village To Invalidate");
				
				check =false;
			}
			else if (ddSubdistrictMerge == 0 || ddSubdistrictMerge == "") {
				alert("Please Select Local Body Type");
			
				check =false;
			}
			else if (villageListMainMerge == 0 ) {
				alert("Please Select Ulb To Merge");
			
				check =false;
			}
			return check;
	
		}
	 
		
	function selectall() {
		var selObj="";
		selObj = document.getElementById('villageListNewContri');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
		selObj = document.getElementById('ULBListNewContri');
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
		}
	}
	$(document).ready(function() {
		
		
		var s = document.getElementById("flag2").value;
		if(s>0)
			getSubDistrictandULBList(s);
		}); 
	
	
	addItemList=function(copyId, pasteId) {
		$('#'+copyId+'> option:selected').appendTo('#'+pasteId); 
		};
		
		copyAllObjectFormOnetoAnother=function(copyId,pasteId){
			$('#'+copyId+' option').clone().appendTo('#'+pasteId);
			 $('#'+copyId).empty();
		};
</script>
</head>
<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title"><spring:message code="Label.MERGEVIL" htmlEscape="true"></spring:message></h3>
				</div>
			<div class="box-body">
				<div class="box-header subheading"><spring:message code="Label.MERGEUNMAPVIL" htmlEscape="true"></spring:message></div>
			
			<form:form action="mergeandInvalidateVillageDetails.htm" class="form-horizontal" id="invalidateForm" method="POST" commandName="invalidatevillage" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="enterInvalidateVillageOrderDetails.htm"/>"/>
			<input type="hidden" name="stateCode"  id ="stateCode" value="<c:out value='${stateCode}'/>"/>
			<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}'/>"/>
            <input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}'/>"/>	
            <form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}"/>
			<input type="hidden" name="preVersionEffDate" id="preVersionEffDate" />
			
				
		<div class="form-group">
			<c:if test="${flag1 eq 1}">
				<label class="col-sm-3 control-label"><spring:message  code="Label.SELECTDISTRICT"  htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			</c:if>
			<c:if test="${flag1 eq 0}">
				<label class="col-sm-3 control-label" for="ddDistrict"><spring:message  code="Label.DISTRICTCAPS"></spring:message></label>
			</c:if>
			<div class="col-sm-6">
				<form:hidden htmlEscape="true" path="operation" value="I"/>
				<form:select htmlEscape="true" path="districtNameEnglish" class="form-control" id="ddDistrict" onchange="getSubDistrictandULBList(this.value);EmptlyVillageList();" onblur="vlidateOnblur('ddDistrict','1','15','c');">
					<c:if test="${flag1 eq 1}">
						<form:option value="0"  htmlEscape="true"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></form:option>
							<c:forEach items="${districtList}" var="districtList">
								<c:if test="${districtList.operation_state =='A'.charAt(0)}">
									<form:option value="${districtList.districtCode}"  htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out></form:option>
								</c:if>
								<c:if test="${districtList.operation_state =='F'.charAt(0)}">
									<form:option value="${districtList.districtCode}" disabled="true"  htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				</form:option>
								</c:if>
							</c:forEach>
					</c:if>
					<c:if test="${flag1 eq 0}">
						<form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode"  htmlEscape="true"/>
					</c:if>
				</form:select>
			 <div class="errormsg"></div><span class="errormsg" id="ddDestDistrict_error"></span>
		  </div>		
	  </div>
	  
	 <div class="form-group">
		<label class="col-sm-3 control-label" for="ddSubdistrict"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			<div class="col-sm-6">
				<form:select htmlEscape="true" path="subdistrictNameEnglish" class="form-control" id="ddSubdistrict"  onchange="getunmapViltoUlb();"> </form:select>
			</div>
	</div>
	
	<div class="box-header subheading"><h5>Please Select Unmapped Villages</h5></div>
	
	<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message></label>
							<form:select htmlEscape="true" name="select9" id="villageListMainContributing" path="villageList" multiple="multiple" class="form-control" ></form:select>
						</div>
						
						<div class="ms_buttons col-sm-2"><br></br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillageFull" name="Submit4"  onclick="addItem('villageListNewContri','villageListMainContributing','',false);" >Select Villages<i class="fa fa-angle-double-right" aria-hidden="true" ></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneVillage" name="Submit4" onclick="addItemList('villageListNewContri','villageListMainContributing')"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveAllVillages" name="Submit4" onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing')" ><i class="fa fa-angle-double-left" aria-hidden="true"></i>
						</div>
						
						<div class="ms_selection col-sm-5">
							 <div class="form-group">
								<label><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
								<form:select htmlEscape="true" name="select4" id="villageListNewContri" multiple="multiple" path="invalidateVillageList" class="form-control" ></form:select>
										<div class="errormsg"><form:errors htmlEscape="true" path="invalidateVillageList" ></form:errors></div>
							</div>				
						</div>
				</div>
		<div class="clear"></div>
		
		<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			<div class="col-sm-6">
				<form:select path="ulbList" id="ddLgdLBType" onchange="getULBListbyLBtype(this.value);" class="form-control">
					<form:option value="0"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></form:option>
						<c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
							<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
			    		</c:forEach>
				</form:select>
			</div>
		</div>
		
		
		<div class="ms_container row" style="margin-left: 10px;">
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ULBListMainContributing"><spring:message code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message> </label>
				<form:select htmlEscape="true" name="select9" id="ULBListMainContributing" path="coveredLandRegionByULB" multiple="multiple" class="form-control"></form:select>	</div>
			
			<div class="ms_buttons col-sm-2"><br></br>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillageFull" name="Submit4"  onclick="addItemSinglevalidation('ULBListNewContri','ULBListMainContributing','',false);" >Select ULB<i class="fa fa-angle-double-right" aria-hidden="true" ></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneVillage" name="Submit4" onclick="addItemList('ULBListNewContri','ULBListMainContributing')"><i class="fa fa-angle-left" aria-hidden="true"></i>
			</div>
			
			<div class="ms_selection col-sm-5">
				 <div class="form-group">
					<label><spring:message code="Label.SELECTSINULB" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
					<form:select htmlEscape="true" name="select4" id="ULBListNewContri" multiple="multiple" path="selectedCoveredLandRegionByULB" class="form-control"></form:select>
				 </div>				
			</div>
		</div>
		<div class="clear"></div>
		
			
			<div class="box-footer">
              <div class="col-sm-offset-2 col-sm-10">
                <div class="pull-right">
                  <button style="width: 80px;" type="button" onclick="onSubmit();" name="Submit" class="btn btn-success"><i class="fa fa-floppy-o"></i>  <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
				  <button style="width: 80px;" type="button" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" name="Submit6" class="btn btn-danger"><i class="fa fa-times-circle"></i>  <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
				</div>
              </div>   
           </div>
	       	<div id='cvillage'  >
				<div class="form-group" style="visiblity:'hidden';display:none;">
					<div class="box-header subheading"><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></div>						
				</div>
			</div>
</form:form>
<script src="/LGD/JavaScriptServlet"></script>
</div>
</div>
</section>
</div>
</section>
</body>
</html>