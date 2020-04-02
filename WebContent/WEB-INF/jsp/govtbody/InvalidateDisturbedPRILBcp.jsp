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
<title>Invalidate PRI local Body <!-- dell --> <%-- <spring:message htmlEscape="true"  code="Label.INVALIDATEPRILOCALBODY"></spring:message> --%>
</title>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>


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

<script type="text/javascript" src="js/invalidatelocalbody.js"
	charset="utf-8"></script>

<script type="text/javascript" src="js/invalidatelBdisturbed.js"
	charset="utf-8"></script>	

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);
</script>

<script>

	
callActionUrl=function(){
	document.forms['invalidateLocalBodyforPRI'].action = "invalidateLocalBodyforPRI.htm?<csrf:token uri='"invalidateLocalBodyforPRI.htm'/>";
	document.forms['invalidateLocalBodyforPRI'].method = "POST";
    document.forms['invalidateLocalBodyforPRI'].submit();
};

</script>
</head>
<body onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="invalidateLocalBodyforPRI.htm" id="invalidateLocalBodyforPRI" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" htmlEscape="true" />
						<input type="hidden" name="flag1" id="flag1" value="0" htmlEscape="true"/>
						<input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>" htmlEscape="true"/>
						<input type="hidden" name="lbselectedtype" id="lbselectedtype" value="<c:out value='${lbselectedtype}' escapeXml='true'></c:out>" htmlEscape="true" />
						<input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>" htmlEscape="true"/>
						<input type="hidden" name="invalidatedlbcode" id="invalidatedlbcode" value="<c:out value='${invalidatedlbcode}' escapeXml='true'></c:out>" htmlEscape="true"/>
						<input type="hidden" name="unResolvedFlag"  id="unResolvedFlag" value="${localGovtBodyForm.unResolvedFlag}" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.INVALIDATEPLB"></spring:message></h3>
                                </div>
                                 
                        <div class="box-body">
                        <div id='divLgdLBType' class="frmpnlbg">
                        
                        <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LGT"></spring:message></label>
								<div class="col-sm-6" >
								 <form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShowDivs(this.value);" class="form-control"  htmlEscape="true">
										<!--tierSetupCode id Changed  -->
										<c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
											<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}" htmlEscape="true">
												<c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out>
											</form:option>
										</c:forEach>
									</form:select>&nbsp;&nbsp;
									<div class="errormsg" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true" /></div>
									<div class="errormsg" id="ddLgdLBType_error2" style="display: none"></div>
								  </div>
						</div>  
						
						<div class="form-group">
						  <label class="col-sm-3 control-label"><spring:message code="Label.DISTURBEDSTATE" htmlEscape="true" ></spring:message><img src="images/red_flg.png" width="13" height="9" /></label>
						  <div class="col-sm-6"><c:out value="${lbdisturbreason}" escapeXml="true"></c:out> </div>
						</div>
						
						<div id="divLgdlistSubTypeCode" class="form-group" style="visibility: hidden; display: none;">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AVAILSUBTYPE"></spring:message></label>
						<div class="col-sm-6"><form:select path="localbodySubtype" class="form-control" id="ddlgdLBSubTypeCode"  htmlEscape="true"></form:select></div>
						</div>
						
						
						<div id="divLgdSelIntermediateP" class="form-group" style="visibility: hidden; display: none;">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTPANCHYATNME"></spring:message></label>
							<div class="col-sm-6">
							 <form:select path="lgd_LBDistrictAtInter" class="form-control" id="ddlgdLBDistAtInter"  onchange="getlistofIntermediatePanchayat(this.value)" htmlEscape="true">
															<form:option id="typeCode" value="${parentlbcode}" htmlEscape="true"><c:out value="${parentlb}" escapeXml="true"></c:out></form:option>
														</form:select>
							   <div class="errormsg" id="ddlgdLBDistAtInter_error1">
															<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
														</div>
														<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>							
							</div>
						</div>
						
						<div id="divLgdVillageP" style="visibility: hidden; display: none;">
						
						<div  class="form-group" >
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTPANCHYATNME"></spring:message></label>
							<div class="col-sm-6">
							<form:select path="lgd_LBDistrictAtVillage" class="form-control" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" style="width: 200px" htmlEscape="true">
																<form:option id="typeCode" value="${grandparentLBcode}" htmlEscape="true"><c:out value="${grandparentLB}" escapeXml="true"></c:out></form:option>
													 </form:select>
													 
							<div class="errormsg" id="ddlgdLBDistrictAtVillage_error1">
														<form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBDistrictAtVillage_error2"style="display: none"></div>						 
							
							</div>
						</div>
						
						<div class="form-group">
						 <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.INTERPANCHYATNME"></spring:message></label>
						 <div class="col-sm-6">
						  <form:select path="lgd_LBIntermediateAtVillage" class="form-control" id="ddlgdLBInterAtVillage"  onchange="getlistofgp(this.value);" htmlEscape="true">
														<form:option id="typeCode" value="${parentlbcode}" htmlEscape="true"><c:out value="${parentlb}" escapeXml="true"></c:out></form:option>
													</form:select>
					       <div class="errormsg" id="ddlgdLBInterAtVillage_error1">
														<form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true" />
													</div>
													<div class="errormsg" id="ddlgdLBInterAtVillage_error2" style="display: none"></div>								
						    </div>
						 </div>
						
					</div>
						
						
			<div id="Districtlocalbody" style="visibility: hidden; display: none;">
					<div id="divLgdLBType1">
						<div class="box-header subheading">
								<c:if test="${Tiertype eq 2}">
                                    <spring:message htmlEscape="true"  code="Label.selectIPdelete"></spring:message>
                                  </c:if>
								<c:if test="${Tiertype eq 0}"> 
                                     <spring:message htmlEscape="true"  code="Label.selectDPdelete"></spring:message>
                                 </c:if>
                          </div>
                          
                          <div class="form-group">
                           <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.DISTRICTPANCHYATNME"></spring:message></label>
                            <div class="col-sm-6">
                              <form:select path="districtpanlbid" class="form-control" id="lblist1"  onchange="removeData();setflag1()" htmlEscape="true">
															ddSourceLocalBody id name changed 
									<form:option value="0" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
													code="Label.SELECTLOCALBODY"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options items="${districtPanchayatList}"
												itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
										</form:select>
                            
                              <div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
                            
                            </div>
                            
                          </div>
                      </div>
				</div>			
						
						
			<div id="IPlocalbody" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType2">
					<label class="col-sm-3 control-label"><c:if test="${Tier eq 3}">
											<label><spring:message code="Label.INTERPANCHYATNME"></spring:message>
											</label>
										</c:if> <c:if test="${Tier eq 2}">
											<label><spring:message code="Label.VILLAGEPANCHYATNME"></spring:message>
											</label>
										</c:if></label>
					<div class="col-sm-6">
					  <form:select path="intermediatepanlbid" class="form-control" id="lblist2"
											 onchange="removeData();setflag2();"  htmlEscape="true">
							</form:select> 
							<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
							<div class="errormsg" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
					
					</div>
			   </div>
		   </div>			
				
		<div id="gplocalbody" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType3">
						<label class="col-sm-3 control-label"><spring:message  htmlEscape="true" code="Label.VILLAGEPANCHYATNME"></spring:message></label>
                         <div class="col-sm-6">
                          <form:select path="grampanlbid" class="form-control"  id="lblist3"  onchange="removeData();setflag3();" htmlEscape="true">
											<form:option value="0" htmlEscape="true">
												  <esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
													code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
										</form:select> 
										
										<div class="errormsg" id="ddlgdLBDistAtInter_error1">
											<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
										</div>
										<div class="errormsg" id="ddlgdLBDistAtInter_error2"
											style="display: none"></div>
                         
                         </div>
					</div>
				</div>		
						
				
   <div class="form-group" id="divLgdLBType4">
       <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLBCOVERD"></spring:message></label>
       <div class="col-sm-6">
        <label class="radio-inline"><form:radiobutton id="show_dp" path="lboption" htmlEscape="true" onclick="getlblist();removeListItem();" value="true" name="rdoDistrictDelete" />Yes</label>
        <label class="radio-inline"><form:radiobutton id="hide_dp" path="lboption" htmlEscape="true"  onclick="hidelblist()" value="true" name="rdoDistrictDelete" />No</label>
       
       </div>
  </div>				
			
 
 
   <div id="IPMerge1" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType5">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDP"></spring:message><span class="errormsg">*</span></label>
						<div class="col-sm-6">
						 <form:select path="contSubDistrict" class="form-control" id="mergelocalbody" >
													<form:option value="0" htmlEscape="true">
														<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
															code="Label.DISTRICTPANCHAYAT"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>
													<form:options items="${districtPanchayatList}"
														itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
												</form:select>
						</div>
					</div>
				</div>			
			
			
			
    <div id="IPMerge2" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType6">
					<c:if test="${Tier eq 2}">
					   <label class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECTGP"></spring:message> <span class="errormsg">*</span> <label>
					</c:if>
					<c:if test="${Tier eq 3}">
					  <label class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECTIP"></spring:message>  <span class="errormsg">*</span><label>
					</c:if>
                         <div class="col-sm-6">
                           <form:select path="contSubDistrict" class="form-control" id="mergelocalbody2"  htmlEscape="true">
												<form:option value="0" htmlEscape="true">
													<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
														code="Label.DISTRICTPANCHAYAT"></spring:message></esapi:encodeForHTMLAttribute>
												</form:option>

							</form:select>
                         
                         </div>
					</div>
			</div>


				<div id="IPMerge3" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType6">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTGP"></spring:message><span class="errormsg">*</span> </label>
						<div class="col-sm-6">
						  <form:select path="contSubDistrict" class="form-control" id="mergelocalbody3" >
													<form:option value="0" htmlEscape="true">
														<esapi:encodeForHTMLAttribute><spring:message htmlEscape="true"
															code="Label.DISTRICTPANCHAYAT"></spring:message></esapi:encodeForHTMLAttribute>
													</form:option>

												</form:select>
						</div>
					</div>
				</div>				
			
			
			<div id="Districtlistlocalbodylist" style="visibility: hidden; display: none;">
					<div  id="divLgdLBTypelist">
					
			<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		             <label id="selectEntity"> </label>
		              <form:select htmlEscape="true" name="select9"  id="availablelb" path="lgd_LBDistrictAtVillage" multiple="multiple" class="form-control"  onclick="checkcode(this.value);">
								<form:option value="" htmlEscape="true"></form:option>
						</form:select>
						<div class="errormsg"><form:errors htmlEscape="true" path="lgd_LBDistrictAtVillage"></form:errors> </div>
		        </div>
					 <div class="ms_buttons col-sm-2"><br>
						<input  type="button" id="btnaddVillageFull" class="btn btn-primary btn-xs btn-block" name="Submit4" value="<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>" onclick="addItem('choosenlb','availablelb','',false);" />
						<input  type="button" id="btnremoveOneVillage" class="btn btn-primary btn-xs btn-block" name="Submit4" value=" &lt; " class="btn"  onclick="removeItem('choosenlb','availablelb',false)" />
						<input  type="button" id="btnremoveAllVillages" class="btn btn-primary btn-xs btn-block" name="Submit4" value="&lt;&lt;" class="btn"  onclick="removeAll('choosenlb','availablelb',false)" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"></label> 
			   <form:select htmlEscape="true"   name="select4" id="choosenlb"  multiple="multiple" path="choosenlb" class="form-control" onclick="checkcode(this.value)">
			   </form:select>		
		      
		       <input type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();' class="btn btn-primary" value="Choose more Local Bodies" />
		     </div>				
            </div>
         </div>
	</div>
</div>			
			
			<table id="dynamicTbl"  style="visibility: hidden; display: none;">
											<tr >
												<th><spring:message code="Label.LOCALBODYTYPENAME"
														htmlEscape="true"></spring:message></th>
												<th><spring:message
														code="Label.CHILDLOCALGOVTBODYDETAIL" htmlEscape="true"></spring:message>
												</th>
											</tr>
			</table>			
						
						
						 
                 </div> 
             
                      
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                    <input type="hidden" name="listformat" id="listformat" value="@" /> 
					<input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn btn-success" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
					<%-- <input	type="button" name="Submit2" class="btn btn-warning" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='viewResolveEntitiesinDisturbedStatePRI.htm?<csrf:token uri='viewResolveEntitiesinDisturbedStatePRI.htm'/>';" /> --%>
					<input type="button" name="Submit6" class="btn btn-danger" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
	</body>
</html>
<script>
			$(window).load(function () {
				HideShowDisturblb();
			}); 
</script>
