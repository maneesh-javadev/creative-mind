<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%-- <%@ taglib uri="http://displaytag.sf.net" prefix="display"%> --%>
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
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
<script type="text/javascript">
 $(document).ready(function() {
	HideShowDisturblb();
	}); 

</script>	
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true"  code="Label.INVALIDATETLB"></spring:message> </h3>
					</div>
					<form:form action="invalidateLocalBodyforTRI.htm" id="invalidateLocalBodyforTRI" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" />
						<input type="hidden" name="flag1" id="flag1" value="0"  />
						<input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>"/>
						<input type="hidden" name="lbselectedtype" id="lbselectedtype" value="<c:out value='${lbselectedtype}' escapeXml='true'></c:out>"/>
						<input type="hidden" name="invalidatedlbcode" id="invalidatedlbcode" value="<c:out value='${invalidatedlbcode}' escapeXml='true'></c:out>"/>
						<div class="box-body">
			                <div id='divLgdLBType'>
				                <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LGT"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:select path="lgd_LBTypeName" id="ddLgdLBType" class="form-control">
											<%-- <form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option> --%>
											<c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
												<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}">
													<c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out>
												</form:option>
											</c:forEach>
										</form:select> 
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none"></div>
			                      	</div>
			                    </div>
			                    <div class="form-group">
			                    	<label class="col-sm-3 control-label"><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message><img src="images/red_flg.png" width="13" height="9" /></label>
			                      	<div class="col-sm-6">
			                        	<c:out value="${lbdisturbreason}" escapeXml="true"></c:out>
			                      	</div>
			                    </div>
			                    <div id="divLgdlistSubTypeCode" class="form-group" style="visibility: hidden; display: none;">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true"	code="Label.AVAILSUBTYPE"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:select path="localbodySubtype" class="form-control" id="ddlgdLBSubTypeCode"></form:select>
			                      	</div>
			                    </div>
			                    <div id="divLgdSelIntermediateP" class="form-group" style="visibility: hidden; display: none;">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.INTERPANCHYATNME"></spring:message></label>
			                      	<div class="col-sm-6">
			                        	<form:select path="lgd_LBDistrictAtInter" class="form-control" id="ddlgdLBDistAtInter" onchange="getlistofIntermediatePanchayat(this.value)">
											<form:option id="typeCode" value="${parentlbcode}"><c:out value="${parentlb}" escapeXml="true"></c:out></form:option>
										</form:select>
										<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
			                      	</div>
			                    </div>
			                    <div id="divLgdVillageP" style="visibility: hidden; display: none;">
				                    <div class="form-group">
				                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTPANCHYATNME"></spring:message></label>
				                      	<div class="col-sm-6">
				                        	<form:select path="lgd_LBDistrictAtVillage" class="form-control" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);">
												<form:option id="typeCode" value="${grandparentLBcode}"><c:out value="${grandparentLB}" escapeXml="true"></c:out></form:option>
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
				                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.INTERPANCHYATNME"></spring:message></label>
				                      	<div class="col-sm-6">
				                        	<form:select path="lgd_LBIntermediateAtVillage" class="form-control" id="ddlgdLBInterAtVillage" onchange="getlistofgp(this.value);">
												<form:option id="typeCode" value="${parentlbcode}"><c:out value="${parentlb}" escapeXml="true"></c:out></form:option>
												<form:option value="0">
													<spring:message htmlEscape="true"  code="divLgdLBType3"></spring:message>
												</form:option>
											</form:select>
											<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
											<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
											<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>
				                      	</div>
				                    </div>
			                    </div>
			                    <div class="form-group" id="Districtlocalbody" style="visibility: hidden; display: none;">
			                    	<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHYATNME"></spring:message></label>
			                      	<div class="col-sm-6">
			                      		<c:set var="flag1" value="1"/>
			                        	<form:select path="districtpanlbid" class="form-control" id="lblist1" onchange="removeData();setflag1()">
											ddSourceLocalBody id name changed 
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select> 
										<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="IPlocalbody" style="visibility: hidden; display: none;">
		                    		<c:if test="${Tier eq 3}">
										<label class="col-sm-3 control-label"><spring:message code="Label.INTERPANCHYATNME"></spring:message></label>
									</c:if> 
									<c:if test="${Tier eq 2}">
										<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGEPANCHYATNME"></spring:message></label>
									</c:if>
			                      	<div class="col-sm-6">
			                      		<form:select path="intermediatepanlbid" class="form-control" id="lblist2" onchange="removeData();setflag2();">
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
											</form:option>
										</form:select>	
										<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="gplocalbody" style="visibility: hidden; display: none;">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.VILLAGEPANCHYATNME"></spring:message></label>
			                      	<div class="col-sm-6" id="divLgdLBType3">
			                      		<c:set var="flag3" value="3"/>										
										<form:select path="grampanlbid" class="form-control" id="lblist3" onchange="removeData();setflag3();">
											<form:option value="0">
												<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
										</form:select>	
										<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="divLgdLBType4">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLBCOVERD"></spring:message></label>
			                      	<div class="col-sm-6">
			                      		<form:radiobutton id="show_dp" path="lboption" htmlEscape="true" onclick="getlblist();removeListItem();" value="true" name="rdoDistrictDelete"  />Yes &nbsp;&nbsp;
			                      		<form:radiobutton id="hide_dp" path="lboption" htmlEscape="true" onclick="hidelblist()" value="true" name="rdoDistrictDelete"  />No 
			                      		<div class="errormsg"></div>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="IPMerge1" style="visibility: hidden; display: none;">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> </label><span class="mandatory">*</span></label>
			                      	<div class="col-sm-6" id="divLgdLBType5">
			                      		<form:select path="contSubDistrict" class="form-control" id="mergelocalbody">
											<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
										</form:select>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="IPMerge2" style="visibility: hidden; display: none;">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> </label><span class="mandatory">*</span></label>
			                      	<div class="col-sm-6" id="divLgdLBType6">
			                      		<form:select path="contSubDistrict" class="form-control" id="mergelocalbody2">
											<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
										</form:select>
			                      	</div>
			                    </div>
			                    <div class="form-group" id="IPMerge3" style="visibility: hidden; display: none;">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> </label><span class="mandatory">*</span></label>
			                      	<div class="col-sm-6" id="divLgdLBType7">
			                      		<form:select path="contSubDistrict" class="form-control" id="mergelocalbody3">
											<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
										</form:select>
			                      	</div>
			                    </div>
			                    
			                    <div id="Districtlistlocalbodylist" style="visibility: hidden; display: none;">
									<div  id="divLgdLBTypelist">
										<div class="ms_container row" style="margin-left: 10px;">
								           <div class="ms_selectable col-sm-5 form-group">
									       		<label id="selectEntity"> </label>
									            <form:select name="select9" id="availablelb" path="lgd_LBDistrictAtVillage" multiple="multiple" class="form-control" onclick="checkcode(this.value);">
													<form:option value=""></form:option>
												</form:select>
												<div class="errormsg"><form:errors htmlEscape="true"  path="lgd_LBDistrictAtVillage" ></form:errors></div>
									        </div>
										    <div class="ms_buttons col-sm-2"><br>
										    	<input type="button" id="btnaddVillageFull" class="btn btn-primary btn-xs btn-block" name="Submit4" value= "<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>" onclick="addItem('choosenlb','availablelb','',false);" />
										    	<input type="button" id="btnremoveOneVillage" class="btn btn-primary btn-xs btn-block" name="Submit4" value=" &lt; "  onclick="removeItem('choosenlb','availablelb',false)" />
										    	<input type="button" id="btnremoveAllVillages" class="btn btn-primary btn-xs btn-block" name="Submit4" value="&lt;&lt;" onclick="removeAll('choosenlb','availablelb',false)" />
											</div>
											<div class="ms_selection col-sm-5">
												<label id="selectedEntity"></label> 
												<div class="form-group">
												   	<form:select name="select4" id="choosenlb" multiple="multiple" path="choosenlb" class="form-control" onclick="checkcode(this.value)"></form:select>		
											       	<input type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();'  class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.CHOOSEMOREDISTRICT"></spring:message>" />
											    </div>				
								            </div>
							            </div>
									</div>
								</div>	
			                    <div class="box-footer">
			                    	<div class="col-sm-offset-2 col-sm-10">
			                    		<div class="pull-right">
			                    			<input type="hidden" name="listformat" id="listformat" value="@" />
			                    			<input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn btn-success" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
			                    			<%-- <input type="button" name="Submit2" class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.CLEAR"> </spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStateTra.htm?<csrf:token uri="viewResolveEntitiesinDisturbedStateTra.htm"/>'" /> --%>
			                    			<input type="button" name="Submit9" class="btn btn-primary" value="<spring:message htmlEscape="true"  code="App.DRAFT"></spring:message>" onclick="javascript:go('saveAsDraftVillage.htm');" />
											<input type="button" name="Submit6" class="btn btn-danger" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											       
			                        	</div>
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

