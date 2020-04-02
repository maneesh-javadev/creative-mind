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
<title> <spring:message htmlEscape="true"  code="Label.INVALIDATETLB"></spring:message> 
</title>
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
    <script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
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
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>	
	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>


<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script>
	
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
	<script>
	var callActionUrl = function () {
		var url='invalidateLocalBodyforPRI.htm';
		$( 'form[id=invalidateLocalBodyforPRI]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
		$( 'form[id=invalidateLocalBodyforPRI]' ).attr('method','post');
		$( 'form[id=invalidateLocalBodyforPRI]' ).submit();
	};
	</script>
	
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	
	<section class="content">

                <div class="row">
          <section class="col-lg-12">
           <form:form action="invalidateLocalBodyforTRI.htm" id="invalidateLocalBodyforTRI" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data" class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" />
			<input type="hidden" name="flag1" id="flag1" value="0"  />
			<input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>"/>
				      
				               
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.INVALIDATETLB"></spring:message></h3>
                                </div>
                                
                        <div class="box-body">
                        
                     <div id='divLgdLBType' >
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></h4>
							</div>
							
							
						<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message> <span class="errormsg">*</span></label>
							<div class="col-sm-6">
							 <form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShowDivs(this.value);" class="form-control" >
									<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
									</form:option>
                                    <c:forEach var="localBodyTypelist" varStatus="var"  items="${localBodyTypelist}">
										<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out>  </form:option>
											</c:forEach>
										</form:select>&nbsp;&nbsp; 
										<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddLgdLBType_error2" style="display: none" ></div>		
							
							   </div>
							</div>
								
							<div id="divLgdlistSubTypeCode" class="form-group" style="visibility: hidden; display: none;">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILSUBTYPE"></spring:message></label>
									<div class="col-sm-6">
									 <form:select path="localbodySubtype" class="form-control" id="ddlgdLBSubTypeCode" >
														</form:select>  
									</div>
							 </div>
								
								
								
								
							<div id="divLgdSelIntermediateP" class="form-control" style="visibility: hidden; display: none;">
								<label class="col-sm-3 control-label"> <spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
															 <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out><span class="errormsg">*</span></label> 
									
									<div class="col-sm-6">
									 <form:select path="lgd_LBDistrictAtInter" class="form-control" id="ddlgdLBDistAtInter"  onchange="getlistofIntermediatePanchayat(this.value)">
											<form:option value="0"> <spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message> </form:option>
											    <c:forEach items="${districtPanchayatList}" var="districtList">
																	<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																		<form:option value="${districtList.localBodyCode}" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
																		</form:option>
																	</c:if>
																	<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																		<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																		</form:option>
																	</c:if>
																</c:forEach>
														</form:select> &nbsp;
														<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
									
									
									</div>
							 </div>
								
								
							<div id="divLgdVillageP" style="visibility: hidden; display: none;">
									<div class="form-group">
									<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> 
									  <span   class="errormsg" >*</span></label>
									  <div class="col-sm-6">
									  <form:select path="lgd_LBDistrictAtVillage" class="form-control" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" >
															<form:option value="0">
																<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															
																<c:forEach items="${districtPanchayatList}" var="districtList">
																	<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																		<form:option value="${districtList.localBodyCode}" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
																		</form:option>
																	</c:if>
																	<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																		<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																			
																		</form:option>
																	</c:if>
																</c:forEach>
														</form:select> &nbsp;
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
														<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>
									  
									  
									  </div>
									</div>
												
									<div class="form-group">
									  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp; <c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out><span class="errormsg">*</span></label>
										<div class="col-sm-6">
										 <form:select path="lgd_LBIntermediateAtVillage" class="form-control" id="ddlgdLBInterAtVillage"  onchange="getlistofgp(this.value);">
												   <form:option value="0"> <spring:message htmlEscape="true"  code="divLgdLBType3"></spring:message> </form:option>
															</form:select> &nbsp;
															<div class="errormsg" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
															<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>	
										
										
										 </div>												
									</div>					
							</div>
							
			
			</div>
			
								
			<div id="Districtlocalbody" style="visibility: hidden; display: none;">
			  <div  id="divLgdLBType1">
				<div class="box-header subheading">
					 <h4><spring:message htmlEscape="true"  code="Label.SELECTLBD"></spring:message> </h4>
				</div>
					<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message><span class="errormsg">*</span></label>				
					<div class="col-sm-6">
					 <form:select path="districtpanlbid" class="form-control" id="lblist1"  onchange="removeData();setflag1()">
															
						<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></form:option>
							<c:forEach items="${districtPanchayatList}" var="districtList">
								<c:if test="${districtList.operation_state =='A'.charAt(0)}">
										<form:option value="${districtList.localBodyCode}" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
										</form:option>
								</c:if>
								<c:if test="${districtList.operation_state =='F'.charAt(0)}">
									<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																			
																		</form:option>
																	</c:if>
																</c:forEach>
														</form:select>
														
									<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
					
					</div>	
				</div>
			</div>
		</div> 	
			
		<div id="IPlocalbody" style="visibility: hidden; display: none;">
			<div id="divLgdLBType2">
			  <div class="box-header subheading">
					<h4><spring:message htmlEscape="true"  code="Label.SELECTLBD"></spring:message> </h4>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message><span class="errormsg">*</span></label>
					<div class="col-sm-6">
					     <form:select path="intermediatepanlbid" class="combofield" id="lblist2" style="width: 200px" onchange="removeData();setflag2();">
							</form:select>	
							<div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
							<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
					
					
					</div>		
							
				</div>
			</div>
	</div> 						
		<div id="gplocalbody" style="visibility: hidden; display: none;">
			<div id="divLgdLBType3">
				
				<div class="box-header subheading">
				   <spring:message htmlEscape="true"  code="Label.SELECTLBD"></spring:message> 
				</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"> <spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message> <span class="errormsg">*</span></label>
				<div class="col-sm-6">
					 <form:select  path="grampanlbid" class="form-control" id="lblist3"  onchange="removeData();setflag3();">
						<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></form:option>
						<c:forEach items="${singleTierGpList}" var="obj">
								<c:if test="${!obj.isDrafted}">
									<form:option value="${obj.localBodyCode}" htmlEscape="true"><c:out value=" ${obj.localBodyNameEnglish}" escapeXml="true"></c:out>
									</form:option>
								</c:if>
								<c:if test="${obj.isDrafted}">
									<form:option value="${obj.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${obj.localBodyNameEnglish}" escapeXml="true"></c:out>																			
									</form:option>
								</c:if>
						</c:forEach>
					</form:select>	
				    <div class="errormsg" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
				    <div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
				</div>
		   </div>
	    </div>
	</div> 					
			
			
	<div  id="divLgdLBType4">			
		<div class="form-group">
			<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTLBCOVERD"></spring:message></label>
			<div class="col-sm-6">
			 <label class="radio-inline"><form:radiobutton id="show_dp" path="lboption" htmlEscape="true" onclick="getlblist();removeListItem();" value="true" name="rdoDistrictDelete"  />																		
										YES</label>
				<label class="radio-inline"><form:radiobutton id="hide_dp" path="lboption" htmlEscape="true" onclick="hidelblist()" value="true" name="rdoDistrictDelete"  /> 
																No</label>
			
			</div>
	   </div>
	</div>								
		
		 <div id="IPMerge1" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType5">
						<label class="col-sm-3 control-label"> <spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> <span class="errormsg">*</span></label>
							<div class="col-sm-6"> 
													<form:select path="contSubDistrict" class="form-control" id="mergelocalbody" >
													<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
													
																<c:forEach items="${districtPanchayatList}" var="districtList">
																	<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																		<form:option value="${districtList.localBodyCode}" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
																		</form:option>
																	</c:if>
																	<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																		<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																				
																		</form:option>
																	</c:if>
																</c:forEach>
													</form:select>
												
									</label>
							</div>
							</div>
					</div> 			
							
		  <div id="IPMerge2" style="visibility: hidden; display: none;">
					   <div class="form-group" id="divLgdLBType6">
					
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message> <span class="errormsg">*</span></label>
						<div class="col-sm-6"> <form:select path="contSubDistrict" class="form-control" id="mergelocalbody2" htmlEscape="true" >
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
												
										</form:select>
												
					   </div>
					
						
					</div>
			  </div> 						
		
			<div id="IPMerge3" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType7">
					  <label class="col-sm-3 control-label"> <spring:message htmlEscape="true"  code="Label.SELECTMERGE"></spring:message><span class="errormsg">*</span></label>
					<div class="col-sm-6"> 
						<form:select path="contSubDistrict" class="form-control" id="mergelocalbody3" htmlEscape="true" >
								<form:option value="0"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHAYAT"></spring:message></form:option>
					    </form:select>
												
					</div>
							
					
					</div>
			   </div> 					
						
   <div id="Districtlistlocalbodylist" style="visibility: hidden; display: none;">
					<div  id="divLgdLBTypelist">
				
				<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		              <label id="selectEntity"></label>
		             <form:select name="select9" id="availablelb" htmlEscape="true" path="lgd_LBDistrictAtVillage" multiple="multiple" class="form-control"  onclick="checkcode(this.value);">
						<form:option value=""></form:option>
					  </form:select>
																			<div class="errormsg"><form:errors htmlEscape="true"  path="lgd_LBDistrictAtVillage" ></form:errors></div>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						 <button type="button" id="btnaddVillageFull" class="btn btn-primary btn-xs btn-block" name="Submit4" onclick="addItem('choosenlb','availablelb','',false);" ><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message></button>
				         <button type="button"  class="btn btn-primary btn-xs btn-block"  id="btnremoveOneVillage" name="Submit4" class="btn"  onclick="removeItem('choosenlb','availablelb',false)" >&lt;</button>
				        <button type="button"  class="btn btn-primary btn-xs btn-block"  id="btnremoveAllVillages" name="Submit4" class="btn"  onclick="removeAll('choosenlb','availablelb',false)" >&lt;&lt;</button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label id="selectedEntity"></label>
			    <form:select name="select4" id="choosenlb"  htmlEscape="true" multiple="multiple" path="choosenlb" class="form-control" onclick="checkcode(this.value)"></form:select>
				 <button type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();'  class="btn btn-primary" >Choose more Local Bodies</button>
		     </div>				
            </div>
         </div>	
         <table id="dynamicTbl" width="664"  class="table table-bordered table-hover"  style="visibility: hidden;display:none;" >
												    	<tr class="tblRowTitle tblclear">
												    		<th><spring:message code="Label.LOCALBODYTYPENAME" htmlEscape="true"></spring:message></th>
												    		<th><spring:message code="Label.CHILDLOCALGOVTBODYDETAIL" htmlEscape="true"></spring:message></th>
												    	</tr>
												    </table>
								
						
					</div>
			</div>







					</div>	 
                  
             
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                 	<input type="hidden" name="listformat" id="listformat" value="@" />
				<button type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn btn-success"  ><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
				<button type="button" name="Submit2" class="btn btn-warning"	onclick="javascript:location.href='invalidatetriocalbody.htm?<csrf:token uri="invalidatetriocalbody.htm"/>'" ><spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
				<button type="button" name="Submit6" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button> 
                 </div>
           </div>   
       </div> 
       
     </div>   
     </div>
             
    </form:form>      
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>

