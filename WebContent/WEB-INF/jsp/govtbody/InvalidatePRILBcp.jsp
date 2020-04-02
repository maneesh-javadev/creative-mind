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
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<!-- <script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" /> -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script src="js/local_body.js"></script>
<script src="js/lgd_localbody.js"></script>
<script src="js/localbody_validation.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>



<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript" src="js/invalidatelocalbody.js" charset="utf-8"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);
</script>

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
           <form:form action="invalidateLocalBodyforPRI.htm" id="invalidateLocalBodyforPRI" commandName="localGovtBodyForm" method="POST" enctype="multipart/form-data" class="form-horizontal">
			      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLocalBodyforPRI"/>" />
				  <input type="hidden" name="flag1" id="flag1" value="0" />
				  <input type="hidden" name="Tier" id="Tier" value="<c:out value='${Tier}' escapeXml='true'></c:out>"/>
				  <input type="hidden" name="Tiertype" id="Tiertype" value="<c:out value='${Tiertype}' escapeXml='true'></c:out>"/>
				      
				               
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></h3>
                                </div>
                                
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								     <form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShowDivs(this.value);"  htmlEscape="true" Class="form-control">
										<form:option value="0" htmlEscape="true">
											<spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
										</form:option>
										<c:forEach var="localBodyTypelist" varStatus="var"
											items="${localBodyTypelist}">
											<form:option id="typeCode" htmlEscape="true" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
										</c:forEach>
								</form:select>
									<div class="mandatory" id="ddLgdLBType_error1"> <form:errors path="lgd_LBTypeName" htmlEscape="true" /> </div>
									<div class="mandatory" id="ddLgdLBType_error2" style="display: none"></div>
								 </div>
						   </div>   
                  
                  
                        <div id="divLgdlistSubTypeCode" class="form-group" style="visibility: hidden; display: none;">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AVAILSUBTYPE"></spring:message> </label></label> 
							 <div class="col-sm-6">
								 <form:select path="localbodySubtype" id="ddlgdLBSubTypeCode" htmlEscape="true" Class="form-control"> </form:select> 
							 </div>					
												
						</div>
						
						
						<div id="divLgdSelIntermediateP"  class="form-group" style="visibility: hidden; display: none;">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>  <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span class="mandatory">*</span>
							<div class="col-sm-6">
							 <form:select path="lgd_LBDistrictAtInter" class="form-control" id="ddlgdLBDistAtInter" htmlEscape="true" onchange="getlistofIntermediatePanchayat(this.value)">
								<form:option value="0" htmlEscape="true">
									<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
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
							</form:select>
								<div class="mandatory" id="ddlgdLBDistAtInter_error1"> <form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" /> </div>
								<div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none"></div>		
													
							</div>	
						</div>						
               
                 <div id="divLgdVillageP" style="visibility: hidden; display: none;">																					
												
					<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>  <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out><span class="mandatory"  >*</span></label>
					<div class="col-sm-6"> 
						<form:select path="lgd_LBDistrictAtVillage" class="form-control " htmlEscape="true" id="ddlgdLBDistrictAtVillage" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" >
								 <form:option value="0" htmlEscape="true">
										<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
								</form:option>
																
							<c:forEach items="${districtPanchayatList}" var="districtList">
								   <c:if test="${districtList.operation_state =='A'.charAt(0)}">
									  <form:option value="${districtList.localBodyCode}" htmlEscape="true" ><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>
								      </form:option>
							      </c:if>
								<c:if test="${districtList.operation_state =='F'.charAt(0)}">
									<form:option value="${districtList.localBodyCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.localBodyNameEnglish}" escapeXml="true"></c:out>																			
									</form:option>
								</c:if>
							 </c:forEach>
						</form:select> 
							<div class="mandatory" id="ddlgdLBDistrictAtVillage_error1"> <form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true" /> </div>
							<div class="mandatory" id="ddlgdLBDistrictAtVillage_error2" style="display: none"></div>																								
	                    </div>
			    </div>




				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message><c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out><span class="mandatory">*</span></label>
					<div class="col-sm-6">
						<form:select path="lgd_LBIntermediateAtVillage" class="form-control" id="ddlgdLBInterAtVillage"  onchange="getlistofgp(this.value);">
								<form:option value="0" htmlEscape="true">
										<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
								</form:option>
						</form:select>
															
					<div class="mandatory" id="ddlgdLBInterAtVillage_error1">
							<form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true" />
					</div>
					<div class="mandatory" id="ddlgdLBInterAtVillage_error2" style="display: none"></div>																										
		        </div>
						
		  </div>																								
	</div>
           
           
           	<div id="Districtlocalbody"  style="visibility: hidden; display: none;">
				  <div class="box-header subheading">
						<h4><c:if test="${Tiertype eq 2}">
                                  	<spring:message htmlEscape="true"  code="Label.selectIPdelete"></spring:message>
                                </c:if>
								<c:if test="${Tiertype eq 0}"> 
                          			 <spring:message htmlEscape="true"  code="Label.selectDPdelete"></spring:message>
                                 </c:if></h4>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out><span class="mandatory">*</span></label>
                     <div class="col-sm-6">
							<form:select path="districtpanlbid" class="form-control" htmlEscape="true" id="lblist1"  onchange="removeData();setflag1()">
															ddSourceLocalBody id name changed 
								<form:option value="0" htmlEscape="true"> <spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
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
							</form:select> 
							<div class="mandatory" id="ddlgdLBDistAtInter_error1"> <form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" /> </div>
							<div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
						</div>	
					</div>	
				</div>    
               
               
               
               
               <div id="IPlocalbody" style="visibility: hidden; display: none;">
					<div class="box-header subheading"><h4><c:if test="${Tier eq 3}">
 									<spring:message htmlEscape="true"  code="Label.selectIPdelete"></spring:message>
								</c:if>
								<c:if test="${Tier eq 2}">
									<spring:message htmlEscape="true"  code="Label.selectGPdelete"></spring:message>
								</c:if></h4>
					</div>
				<div class="form-group">	
					<label class="col-sm-3 control-label">
					<c:if test="${Tier eq 3}">
						<spring:message code="Label.SELINTERMPANCHYAT"></spring:message>
					</c:if> 
					<c:if test="${Tier eq 2}">
						<spring:message code="Label.SELECTGPLB"></spring:message>
					</c:if>
					<span class="mandatory">*</span></label> 
					
					<div class="col-sm-6">					
							<form:select path="intermediatepanlbid" class="form-control" id="lblist2"  onchange="removeData();setflag2();"> </form:select>
						 <div class="mandatory" id="ddlgdLBDistAtInter_error1">
							<form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" />
						</div>
						<div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
						
					  </div>	
					</div>
				</div>
               
              
              
              <div id="gplocalbody" style="visibility: hidden; display: none;">
					<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.selectGPdelete"></spring:message></h4>
					</div>
				<div class="form-group">	
					<label class="col-sm-3 control-label">
					<spring:message htmlEscape="true" code="Label.SELECTGPLB"></spring:message>
					<span class="mandatory">*</span></label> 
					
					<div class="col-sm-6">					
							<form:select path="grampanlbid" class="form-control" id="lblist3" htmlEscape="true" onchange="removeData();setflag3();">
								<form:option value="0">
									<spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
								</form:option>
							</form:select>
						<div class="mandatory" id="ddlgdLBDistAtInter_error1"> <form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" /> </div>
						<div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
						
					  </div>	
					</div>
				</div> 
               
             <div class="box-header subheading">
                               <h4><spring:message htmlEscape="true" code="Label.SELECTLBCOVERD"></spring:message></h4>
               </div>
             <div class="form-group" id="divLgdLBType4">
								<label class="col-sm-3 control-label"> </label>							
								<div  class="col-sm-6">
								
										<label class="radio-inline"> <form:radiobutton id="show_dp" path="lboption" htmlEscape="true" onclick="getlblist();removeListItem();" value="true" name="rdoDistrictDelete" /> 
										YES</label>

										<label class="radio-inline"> <form:radiobutton id="hide_dp" path="lboption" htmlEscape="true" onclick="hidelblist()" value="true" name="rdoDistrictDelete" />
										No</label>
								</div>							
						<div class="mandatory"></div>
				</div>
            
            
          <div id="IPMerge1" style="visibility: hidden; display: none;">
					<div class="form-group" id="divLgdLBType5">
					<label class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECTDP"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">								
						<form:select path="contSubDistrict" class="form-control" id="mergelocalbody" htmlEscape="true" >
								<form:option value="0" htmlEscape="true">
									<spring:message htmlEscape="true" code="Label.DISTRICTPANCHAYAT"></spring:message>
								</form:option>
								<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
								</form:select> 
						</div>
				</div>
		   </div>  
            
       <div id="IPMerge2" style="visibility: hidden; display: none;">
			<div class="form-group" id="divLgdLBType6">
					<c:if test="${Tier eq 2}">
						<label class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECTGP"></spring:message> <span class="mandatory">*</span></label>
					</c:if> 
					<c:if test="${Tier eq 3}">
						<label class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECTIP"></spring:message> <span class="mandatory">*</span></label>
					</c:if> 
					
				<div class="col-sm-6">
					<form:select path="contSubDistrict" Class="form-control" htmlEscape="true" id="mergelocalbody2" >
							<form:option value="0" htmlEscape="true">
									<spring:message htmlEscape="true" code="Label.DISTRICTPANCHAYAT"></spring:message>
							</form:option>
                     </form:select>
				</div>
			</div>
	    </div>
             
       	<div id="IPMerge3" style="visibility: hidden; display: none;">
				<div class="form-group" id="divLgdLBType6">
					  <label class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECTGP"></spring:message> <span class="mandatory">*</span></label>
						<div class="col-sm-6">
							<form:select path="contSubDistrict" class="form-control" id="mergelocalbody3" htmlEscape="true" >
								<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.DISTRICTPANCHAYAT"></spring:message>
								</form:option>
							</form:select> 
						</div>
				</div>
		</div>      
             
          <div id="Districtlistlocalbodylist" style="visibility: hidden; display: none;">
             <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label id="selectEntity"></label></label>
		             <form:select name="select9"  id="availablelb" htmlEscape="true" path="lgd_LBDistrictAtVillage" multiple="multiple" class="form-control" onclick="checkcode(this.value);">
								<form:option value="" htmlEscape="true"> </form:option>
					</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnaddVillageFull" name="Submit4" class="bttn" value="<spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>" onclick="addItem('choosenlb','availablelb','',false);"   >Select</button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="removeItem('choosenlb','availablelb',false)" ><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn"  onclick="removeAll('choosenlb','availablelb',false)"   ><i class="fa fa-angle-double-left" aria-hidden="true"></i>  <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><c:out value="Contributing Child Local Bodies"/><span class="mandatory">*</span></label> 
			   <form:select name="select4" id="choosenlb"  htmlEscape="true" multiple="multiple" path="choosenlb" class="form-control" onclick="checkcode(this.value)"></form:select>
				 <button type="button" id="chooseMoreBtn" onclick='validateSelectedAddMore();' class="btn btn-primary"  >Choose more Local Bodies</button>
		     </div>				
            </div>
         </div>		
         
         
         <table id="dynamicTbl" class="table table-bordered table-hover"  style="visibility: hidden; display: none;">
				<tr>
					<th><spring:message code="Label.LOCALBODYTYPENAME" htmlEscape="true"></spring:message></th>
					<th><spring:message code="Label.CHILDLOCALGOVTBODYDETAIL" htmlEscape="true"></spring:message></th>
				</tr>
		</table>
         </div>
             
             
					
	</div>
	
	  
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <input type="hidden" name="listformat" 	id="listformat" value="@" /> 
                   <button type="button" class="btn btn-success " name="Submit"  onclick="validateSelectAndSubmit(); "  value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" ><i class="fa fa-floppy-o"></i> Submit</button>
                   <button type="button" class="btn btn-danger " name="Cancel" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
       
     
  
             
    </form:form>  
     </div>
  </div>      
   </section>
<script src="/LGD/JavaScriptServlet"></script>

	
</body>
</html>

