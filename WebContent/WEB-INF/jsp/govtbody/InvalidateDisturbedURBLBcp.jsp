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
<title> <spring:message htmlEscape="true"  code="Label.INVALIDATEULB"></spring:message>
</title>
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	
</script>
<script src="js/local_body.js"></script>
<script src="js/lgd_localbody.js"></script>
<script src="js/localbody_validation.js"></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/invalidatelBdisturbed.js"
	charset="utf-8"></script>		
	
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
	<script>
$(document).ready(function() {
	var id = document.getElementById('ddLgdLBType').value;
	var lbTypeCode=$("#typeCode").val().split(":")[0];
	$("#lbTypeCode").val(lbTypeCode)
	HideShowdisturblblist(id);
	
	}); 

</script>
	
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true"  code="Label.INVALIDATEULB"></spring:message> </h3>
					</div>
					<form:form action="invalidateLocalBodyforURB.htm" id="invalidateLocalBodyforURB" commandName="localGovtBodyForm" method="POST"  class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforURB.htm"/>" />
						<input type="hidden" name="flag1" id="flag1" value="0"  />
						<input type="hidden" name="flag2" id="flag2" value="<c:out value='${scode}' escapeXml='true'></c:out>"/>
						<input type="hidden" name="invalidatedlbcode" id="invalidatedlbcode" value="<c:out value='${invalidatedlbcode}' escapeXml='true'></c:out>" />
						<input type="hidden" name="lbTypeCode"  id="lbTypeCode"  />
						<input type="hidden" name="unResolvedFlag"  id="unResolvedFlag" value="${localGovtBodyForm.unResolvedFlag}" />
						
						<div class="box-body">
			                <div id='divLgdLBType'>
				                <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LGT"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShow(this.value);" class="form-control">
											<c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
												<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
											</c:forEach>
										</form:select> 
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>	
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message><img src="images/red_flg.png" width="13" height="9" /></label>
			                      	<div class="col-sm-6">
			                        	<c:out value="${lbdisturbreason}" escapeXml="true"></c:out>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="divLgdlistSubTypeCode" style="visibility: hidden; display: none;">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AVAILSUBTYPE"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:select path="localbodySubtype" class="form-control" id="ddlgdLBSubTypeCode"></form:select>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="divLgdSelIntermediateP" style="visibility: hidden; display: none;">
			                    	<label class="col-sm-3 control-label">
			                    		<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
										<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out>' &nbsp;
										<span class="errormsg">*</span>
									</label>
			                      	<div class="col-sm-6">
			                        	<form:select path="lgd_LBDistrictAtInter" class="form-control" id="ddlgdLBDistAtInter" onchange="getlistofIntermediatePanchayat(this.value)">
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select>
										<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
			                      	</div>
			                    </div>
			                    <div id="divLgdVillageP" style="visibility: hidden; display: none;">
			                    	<div class="form-group">
				                    	<label class="col-sm-3 control-label">
				                    		<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;
				                    		<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out>'
				                    		<span class="errormsg">*</span>
										</label>
				                      	<div class="col-sm-6">
				                        	<form:select path="lgd_LBDistrictAtVillage" class="form-control" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" >
												<form:option value="0">
													<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
												</form:option>
												<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
											</form:select> &nbsp;
											<div id="ddlgdLBDistrictAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
											<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
											<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>
				                      	</div>
				                    </div>
				                    <div class="form-group">
				                    	<label class="col-sm-3 control-label">
				                    		<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;
				                    		<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out>
				                    		<span class="errormsg">*</span>
										</label>
				                      	<div class="col-sm-6">
				                        	<form:select path="lgd_LBIntermediateAtVillage" class="form-control" id="ddlgdLBInterAtVillage" onchange="getlistofgp(this.value);">
												<form:option value="0">
													<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
												</form:option>
											</form:select>
											<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
											<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
											<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>
				                      	</div>
				                    </div>
			                    </div>
			                </div>
			                <div class="form-group" id="Districtlocalbody" style="visibility: hidden; display: none;">
		                    	<label class="col-sm-3 control-label">
		                    		<spring:message htmlEscape="true"  code="Label.URBANLOCALBODY"></spring:message>
									<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out>'&nbsp;
								</label>
		                      	<div class="col-sm-6" id="divLgdLBType2">
		                        	<form:select path="districtpanlbid" class="form-control" id="lblist1" onchange="setflag1()">
										ddSourceLocalBody id name changed 
										<form:option id="typeCode"
                                           value="${invalidatedlbcode}"><c:out value="${invalidatedlb}" escapeXml="true"></c:out>
                                        </form:option>
									</form:select> 
									<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
		                      	</div>
		                    </div>
		                   
		                    <div id="Muncipiality" style="visibility: hidden; display: none;">
		                    	 <div class="box-header subheading">
					                  <h4>Select Municipality You want to Delete</h4>
					                </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label">
			                    		<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
										<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;
										<span class="errormsg">*</span>
									</label>
			                      	<div class="col-sm-6" id="divLgdLBType3">
			                        	<form:select path="intermediatepanlbid" class="form-control" id="lblist2" onchange="setflag2()">
											ddSourceLocalBody id name changed 
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											<form:options items="${districtPanchayatList2}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select> 
										&nbsp;
										<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
			                      	</div>
			                    </div>
		                    </div>
			                <div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
		                    			<input type="hidden" name="listformat" id="listformat" value="@" />
										<input type="button" onclick="validateAndSubmitdisturblb(); " name="Submit" class="btn btn-success" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
		                    			<%-- <input type="button" name="Submit2" class="btn btn-primary" value="<spring:message htmlEscape='true'  code='Button.CLEAR'></spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateULB.htm?<csrf:token uri="viewResolveEntitiesinDisturbedStateULB.htm"/>'" /> --%>
										<input type="button" name="Submit6" class="btn btn-danger" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
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

