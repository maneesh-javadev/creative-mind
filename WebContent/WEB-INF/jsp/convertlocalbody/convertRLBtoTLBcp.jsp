<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Convert RLB to TLB</title>
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/convertRLBtoTLB.js"></script>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/lgd_localbody.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
</head>

<body onload="loadConvertRLBTLBPage();" onsubmit="cursorwait();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

	<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                    <form:form commandName="convertRLBtoTLBForm" action="draftConversionRLBtoTLB.htm" method="POST" enctype="multipart/form-data" class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftConversionRLBtoTLB.htm"/>" />
				      
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.CONVERSIONRLBTLB"></spring:message></h3>
                                </div>
                                 
                      <div id="divFirstPanel">    
                         <div class="box-header subheading">
                                    <h4 class="box-title"><spring:message htmlEscape="true" code="Label.CONVERTRLBToTLB"></spring:message></h4>
                                </div>      
                        <div class="box-body">
                             
	                        <input type="hidden" class="btn" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" id="hdnState" name="hdnState" />
	                            <div class="form-group">
									<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
									<div class="col-sm-6" >
											<form:select path="rurallbTypeName" id="ddLgdLBType" class="form-control"  htmlEscape="true" onchange="getHideShowRuralToTLBLBList(this.value)">
												<form:option value="0"  htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></form:option>
						                         <c:forEach var="localBodyTypelist" varStatus="var" items="${localbodyTypelist}">
														<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}"  htmlEscape="true"><esapi:encodeForHTMLAttribute>${localBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute></form:option>
												</c:forEach>
											</form:select>
													<div id="ddLgdLBType_error" class="mandatory"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
													<div class="mandatory" id="ddLgdLBType_error1"><form:errors path="rurallbTypeName" htmlEscape="true" /></div>
													<div class="mandatory" id="ddLgdLBType_error2" style="display: none"></div>
								  </div>
							  </div>   
	                   
                
              <div id="divRuralIntermediatePanchayat" style="visibility: visible; display: none;">
                                
                                    <div class="form-group">
										<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label>
										<div class="col-sm-6" >
											<form:select path="lgd_LBDistListAtInterCA" onchange="getInterPanchayatbyDcodeAtICA(this.value);" class="form-control" id="ddLgdLBDistListAtInterCA" htmlEscape="true">
													<form:option value="0" htmlEscape="true">
															<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
													</form:option>
													<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
									         </form:select>

												<div id="ddLgdLBDistListAtInterCA_error" class="mandatory"> <spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message> </div>
												<div class="mandatory" id="ddLgdLBDistListAtInterCA_error1"><form:errors path="lgd_LBDistListAtInterCA" htmlEscape="true" /></div>
												<div class="mandatory" id="ddLgdLBDistListAtInterCA_error2" style="display: none"></div>
												<div class="mandatory" id="ddLgdLBInterPDestList_error1"> <form:errors path="lgd_LBInterPDestList" htmlEscape="true" /> </div>
											    <div class="mandatory" id="ddLgdLBInterPDestList_error2" style="display: none"></div>
								    	</div>
							 	  </div>  
                                
                                
                       <div class="ms_container row" style="margin-left: 10px;">
	             			<div class="ms_selectable col-sm-5 form-group">
		               			<label><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message> </label>
		              			 <form:select path="lgd_LBDistPSourceList" class="form-control" id="ddLgdLBDistPSourceList" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode"  multiple="true" htmlEscape="true">
                                        	</form:select>
		        			</div>
							 <div class="ms_buttons col-sm-2"><br>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" ><spring:message code="Button.WHOLE"></spring:message></button>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)">Back <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)"><spring:message code="Button.RESET"></spring:message> </button>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItemforLB('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);">Part<i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
							 </div>
							<div class="ms_selection col-sm-5">
								 <div class="form-group">
								    <label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label> 
								    <form:select id="ddLgdLBDistPDestList"  multiple="true" path="lgd_LBDistPDestList" class="form-control" htmlEscape="true">
									  </form:select>
									  
										<div class="mandatory" id="ddLgdLBDistCAreaDestL_error1"> <form:errors path="lgd_LBDistCAreaDestList" htmlEscape="true" /> </div> 
										<div class="mandatory" id="ddLgdLBDistCAreaDestL_error2" style="display: none"></div>							
										
										<button type="button" class="btn btn-primary"  onclick="selectIntermediateLocalBodyListAtDCA();" ><spring:message htmlEscape="true" code="Button.GETDISTRICTL"/></button>	
							     </div>				
				            </div>
         			</div> 
                                
                            
                            
                   <div class="ms_container row" style="margin-left: 10px;">
	             			<div class="ms_selectable col-sm-5 form-group">
		               			<label><spring:message htmlEscape="true" code="Label.SELINTERMPANCHYAT"></spring:message>  </label>
		              			  <form:select path="lgd_LBInterPSourceList" class="form-control" id="ddLgdLBInterPSourceList"  multiple="true" htmlEscape="true">
                                    </form:select>
		        			</div>
							 <div class="ms_buttons col-sm-2"><br>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItemforLB('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" ><spring:message code="Button.WHOLE"/></button>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeItem('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)">Back <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
								<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBInterPSourceList', true)"><spring:message code="Button.RESET"></spring:message> </button>
							     <button type="button" class ="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforLB('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" >Part<i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							 </div>
							<div class="ms_selection col-sm-5">
								 <div class="form-group">
								    <label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInter}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label> 
								      <form:select name="select6" id="ddLgdLBInterPDestList"  multiple="true" path="lgd_LBInterPDestList" class="form-control" htmlEscape="true">
														</form:select>
									<button type="button"  class="btn btn-primary" onclick="selectVillageLocalBodyListAtICA();" ><spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/></button>			
							     </div>				
				            </div>
         			</div>     
               </div>
             
             
             
               <div id="divRuralIntermediatePanchayat" style="visibility: visible; display: none;">
          
                                    <div class="form-group">
										<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label>
										<div class="col-sm-6" >
									<form:select path="lgd_LBDistListAtInterCA" onchange="getInterPanchayatbyDcodeAtICA(this.value);" class="form-control" id="ddLgdLBDistListAtInterCA" htmlEscape="true">
											<form:option value="0" htmlEscape="true">
													<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
											</form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
									   </form:select>

												<div id="ddLgdLBDistListAtInterCA_error" class="mandatory"> <spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message> </div>
										        <div class="mandatory" id="ddLgdLBDistListAtInterCA_error1"><form:errors path="lgd_LBDistListAtInterCA" htmlEscape="true" /></div>
										        <div class="mandatory" id="ddLgdLBDistListAtInterCA_error2" style="display: none"></div>
												<div class="mandatory" id="ddLgdLBInterPDestList_error1"> <form:errors path="lgd_LBInterPDestList" htmlEscape="true" /> </div>
												<div class="mandatory" id="ddLgdLBInterPDestList_error2" style="display: none"></div>
								    	</div>
							 	  </div>
							 	  
							 	  
			                   <div class="ms_container row" style="margin-left: 10px;">
				             			<div class="ms_selectable col-sm-5 form-group">
					               			<label><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInter}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label>
					              			 <form:select path="lgd_LBDistCAreaSourceList" class="form-control" id="ddIntermediateLbSourceL"  multiple="true" htmlEscape="true">
			                                     </form:select>
					        			</div>
										 <div class="ms_buttons col-sm-2"><br>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItemforVillageLocalBody('ddIntermediateLbDesteL','ddIntermediateLbSourceL','FULL',true);" ><spring:message code="Button.WHOLE"/></button>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeItem('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)">Back <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAll('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)"><spring:message code="Button.RESET"></spring:message> </button>
										 </div>
										<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label><spring:message htmlEscape="true" code="Label.SELECTEDINTERRLBS"></spring:message></label> 
											      <form:select name="select6" id="ddIntermediateLbDesteL"  multiple="true" path="lgd_LBDistCAreaDestList"  class="form-control" htmlEscape="true">
																	</form:select>
										     </div>				
							            </div>
			         			</div>
          
          
                        <div class="ms_container row" style="margin-left: 10px;">
				             			<div class="ms_selectable col-sm-5 form-group">
					               			<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGEPANCHAYATLIST"></spring:message></label>
					              			 <form:select path="lgd_LBInterCAreaSourceList" class="form-control" id="ddLgdLBVillageInterCAreaSourceL"  multiple="true" htmlEscape="true">
                                              </form:select>
					        			</div>
										 <div class="ms_buttons col-sm-2"><br>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItemforLB('ddLgdLBVillageInterCAreaDestL','ddLgdLBVillageInterCAreaSourceL','FULL',true);" ><spring:message code="Button.WHOLE"/></button>
											<button type="button" class="btn btn-primary btn-xs btn-block"   onclick="removeItem('ddLgdLBVillageInterCAreaDestL','ddLgdLBVillageInterCAreaSourceL',true)">Back <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAll('ddLgdLBVillageInterCAreaDestL', 'ddLgdLBVillageInterCAreaSourceL', true)"><spring:message code="Button.RESET"></spring:message> </button>
										 </div>
										<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGEPANCHAYATLIST"></spring:message></label> 
											      <form:select name="select6" id="ddLgdLBVillageInterCAreaDestL"  multiple="true"  path="lgd_LBInterCAreaDestList" class="form-control" htmlEscape="true">
													</form:select>
										     </div>				
							            </div>
			         			</div>
			         			
                     </div>
             
             
             
             <div id="divRuralVillagePanchayat" style="visibility: hidden; display: none;"> 
             
                
                                    <div class="form-group">
										<label  class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECT"></spring:message> &nbsp;<esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDist}</esapi:encodeForHTML>&nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message><span
															class="mandatory">*</span></label>
										<div class="col-sm-6" >
									<form:select path="lgd_LBDistListAtVillageCA" class="form-control" id="ddLgdLBDistListAtVillageCA" htmlEscape="true" onchange="getInterPanchayatbyDcodeAtVCA(this.value);">
															<form:option value="0" htmlEscape="true">
																<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
															</form:option>
															<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
																		<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
																			<form:option value="${districtPanchayatList.localBodyCode}" htmlEscape="true" ><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
																			</form:option>
																		</c:if>
																		<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
																			<form:option value="${districtPanchayatList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
																			</form:option>
																		</c:if>
															</c:forEach>
										</form:select>
													<div id="ddLgdLBDistListAtVillageCA_error" class="mandatory"> <spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message> </div>
													<div class="mandatory" id="ddLgdLBDistListAtVillageCA_error1"> <form:errors path="lgd_LBDistListAtVillageCA" htmlEscape="true" /> </div>
													<div class="mandatory" id="ddLgdLBDistListAtVillageCA_error2" style="display: none"></div>
								    	</div>
							 	  </div>
							 	  
							 	  
							 	  <div class="form-group">
										<label  class="col-sm-3 control-label"> <spring:message htmlEscape="true" code="Label.SELECT"></spring:message> &nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInter}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message><span
															class="mandatory">*</span></label>
										<div class="col-sm-6" >
									<form:select path="lgd_LBInterListAtVillageCA" class="form-control" id="ddLgdLBInterListAtVillageCA" htmlEscape="true" onchange="getVillagePanchbyIntercodeAtVCA(this.value);">
	                                    <form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
                                     </form:select>
													<div id="ddLgdLBVillageDestAtVillageCA_error" class="mandatory"><spring:message code="Error.NoVPSelected" htmlEscape="true"></spring:message></div>
                                                     <div class="mandatory" id="ddLgdLBVillageDestAtVillageCA_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true" /></div>
                                                     <div class="mandatory" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none"></div>
								    	</div>
							 	  </div>
							 	  
			                   <div class="ms_container row" style="margin-left: 10px;">
				             			<div class="ms_selectable col-sm-5 form-group">
					               			<label><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> &nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureVillage}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label>
					              			 <form:select path="lgd_LBVillageSourceAtVillageCA" class="form-control" id="ddLgdLBVillageSourceAtVillageCA"  multiple="true">
                                               </form:select>
					        			</div>
										 <div class="ms_buttons col-sm-2"><br>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItemVillageFinalS('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA','FULL',true);" ><spring:message code="Button.WHOLE"/></button>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeItem('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true);" >Back <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
											<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAll('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true);"><spring:message code="Button.RESET"></spring:message> </button>
										 </div>
										<div class="ms_selection col-sm-5">
											 <div class="form-group">
											    <label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> &nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureVillage}</esapi:encodeForHTML> &nbsp;<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label> 
											      <form:select name="select6" id="ddLgdLBVillageDestAtVillageCA"  multiple="true" path="lgd_LBVillageDestAtVillageCA" class="form-control" htmlEscape="true">
												  </form:select>
												  <div class="mandatory" id="ddLgdLBVillageCAreaDestL_error_error1"> <form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true" /> </div>
												  <div class="mandatory" id="ddLgdLBVillageCAreaDestL_error_error2" style="display: none"></div>
										     </div>				
							            </div>
			         			</div>
                         </div>
             
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-info " name="Submit" id="btnNext" onclick="return validateFirstAllRLBtoTLB();"  ><i class="fa fa-floppy-o"></i> <spring:message code="Button.NEXTSTEP" htmlEscape="true"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
        
     </div>   
   </div>   
      
   <div id="divSecondPanel" style="display: none">        
	      <div class="box-body">
	        <div class="box-header subheading">
                              <h4><spring:message htmlEscape="true" code="Label.SELTRIBALLOCALBODY"></spring:message></h4>
            </div>
            
            <div class="form-group">
            <label class="col-sm-1"></label>
	            <div class="col-sm-10">
			           <label class="radio-inline"> <form:radiobutton path="mergeRLBtoTLB" id="rdmergeRLBtoTLB" value="M" onclick="radioClick(this.value,document.getElementById('ddLgdLBType').value);" />  <spring:message htmlEscape="true" code="Label.MERGERLBTOTLB"></spring:message></label> 
						<label class="radio-inline"><form:radiobutton path="declarenewTLB" id="rddeclarenewTLB" onclick="radioClick(this.value,document.getElementById('ddLgdLBType').value);" value="N" /> <spring:message htmlEscape="true" code="Label.DECLARENEWTLB"></spring:message></label>
	                  
	                  <span id="rdmergeRLBtoTLB_error" class="mandatory"></span> 
	                 <form:errors path="mergeRLBtoTLB" class="mandatory" htmlEscape="true"></form:errors>
	                 <form:errors path="declarenewTLB" class="mandatory" htmlEscape="true"></form:errors>
	           </div>
            </div>
            
         <div id="divmergeTLB">
             <div class="box-header subheading">
                              <h4 ><spring:message htmlEscape="true" code="Label.EXISTINGTLB"></spring:message></h4>
            </div>
            
         <div class="form-group" >
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TRIBALLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
					 <form:select id="ddTraditionalLBType" path="traditionalLbTypeName"  class="form-control"  onchange="gethideShowDivforExistTLB(this.value);" htmlEscape="true">
								<form:option value="0" htmlEscape="true"> <spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>
								</form:option>
							<c:forEach var="traditionalocalBodyTypelist" varStatus="var" items="${traditionalocalBodyTypelist}">
									<form:option id="typeCode" value="${traditionalocalBodyTypelist.localBodyTypeCode}:${traditionalocalBodyTypelist.level}" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${traditionalocalBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute> </form:option>
							</c:forEach>
		           </form:select> 
						<div id="ddTraditionalLBType_error" class="mandatory"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
						<div class="mandatory" id="ddTraditionalLBType_error1"><form:errors path="traditionalLbTypeName" htmlEscape="true" />	</div>
						<div class="mandatory" id="ddTraditionalLBType_error2" style="display: none"></div>
		    </div>
	    </div>
            
           <div id="divDistrictPforExist" class="form-group" style="visibility: hidden; display: none;">
           
              <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>  <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> </label><span class="mandatory">*</span></label>
				<div class="col-sm-6">
					<form:select  path="lgd_LBDistrictforExist" class="form-control" id="ddlgdLBDistrictPforExist" items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true">
							<form:option value="0" htmlEscape="true">
								<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
							</form:option>
					 </form:select> 
					<div id="ddlgdLBDistrictPforExist_error" class="mandatory"> <spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message> </div>
					<div class="mandatory" id="ddlgdLBDistrictPforExist_error1"> <form:errors path="lgd_LBDistrictforExist" htmlEscape="true" /> </div>
					<div class="mandatory" id="ddlgdLBDistrictPforExist_error2" style="display: none"></div>
		      </div>
           </div>  
            
            <div id="divIntermediatePforExist" style="visibility: hidden; display: none;"> 
												
					<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML><span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<form:select path="lgd_LBDistrictPatInterforExist" class="form-control" id="ddlgdLBDistrictAtInterforExist" onchange="getInterPanchayatAtInterbyDcodeforExist(this.value);" htmlEscape="true">
									<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
										<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
											<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
												<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute> 
											</form:option>
											</c:if>
											<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
												<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute> 																				
											</form:option>
											</c:if>
										</c:forEach>
								</form:select> 
					
								<div id="ddlgdLBDistrictAtInterforExist_error" class="mandatory"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
								<div class="mandatory" id="ddlgdLBDistrictAtInterforExist_error1"><form:errors path="lgd_LBDistrictPatInterforExist" htmlEscape="true" /></div>
								<div class="mandatory" id="ddlgdLBDistrictAtInterforExist_error2" style="display: none"></div>
							</div>
				    </div>
				    
					<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> <span class="mandatory">*</span></label>
						<div class="col-sm-6">
							 <form:select	path="lgd_LBIntermediatePanchaytforExist" class="form-control" htmlEscape="true" id="ddlgdLBIntermediatePanchayatforExist" >
								<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></form:option>
							</form:select> 
							<div class="mandatory" id="ddlgdLBIntermediatePanchayatforExist_error1"> <form:errors path="lgd_LBIntermediatePanchaytforExist" htmlEscape="true" /> </div>
							<div class="mandatory"  id="ddlgdLBIntermediatePanchayatforExist_error2" style="display: none"></div>
					  </div> 	
					</div>
			
			</div>
            
            <div id="divVillagePforExist" style="visibility: hidden; display: none;">
			
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML><span class="mandatory">*</span></label> 
				<div class="col-sm-6">
					<form:select path="lgd_LBDistrictAtVillageforExist" class="form-control" id="ddlgdLBDistrictAtVillageforExist" onchange="getInterPanchayatAtVillagebyDcodeforExist(this.value);" htmlEscape="true">
						<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message> </form:option>
						<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
							<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
								<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"> <esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
								</form:option>
							</c:if>
							<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
								<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
								</form:option>
							</c:if>
						</c:forEach>
					</form:select> 
					<div id="ddlgdLBDistrictAtVillageforExist_error" class="mandatory"><spring:message code="error.SELECTHDCOUNCIL" htmlEscape="true"></spring:message></div>
					<div class="mandatory" id="ddlgdLBDistrictAtVillageforExist_error1"><form:errors path="lgd_LBDistrictAtVillageforExist" htmlEscape="true" /></div>
					<div class="mandatory" id="ddlgdLBDistrictAtVillageforExist_error2" style="display: none"></div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message><esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> <span class="mandatory">*</span></label> 
						<div class="col-sm-6">
							<form:select path="lgd_LBIntermediateAtVillageforExist" class="form-control" id="ddlgdLBInterAtVillageforExist"  onchange="getVillagePanchayatAtVillagebyDcodeforExist(this.value);" htmlEscape="true">
								<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
							</form:select> 
								<div id="ddlgdLBInterAtVillageforExist_error" class="mandatory"><spring:message code="error.SELECTBACOUNCIL" htmlEscape="true"></spring:message></div>
								<div class="mandatory" id="ddlgdLBInterAtVillageforExist_error1"><form:errors path="lgd_LBIntermediateAtVillageforExist" htmlEscape="true" /></div>
								<div class="mandatory" id="ddlgdLBInterAtVillageforExist_error2" style="display: none"></div>
			            </div>
			</div>
																
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureVillageTrade}</esapi:encodeForHTML><span class="mandatory">*</span></label>
				 <div class="col-sm-6">
						 <form:select	path="lgd_LBVillagePanchaytforExist" class="form-control" id="ddlgdLBVillagePanchayatforExist" htmlEscape="true">
						<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
						</form:select>
						<div id="ddlgdLBVillagePanchayatforExist_error" class="mandatory"><spring:message code="error.SELECTVCOUNCIL" htmlEscape="true"></spring:message></div>
						<div class="mandatory" id="ddlgdLBVillagePanchayatforExist_error1"><form:errors path="lgd_LBVillagePanchaytforExist" htmlEscape="true" /></div>
						<div class="mandatory" id="ddlgdLBVillagePanchayatforExist_error2" style="display: none"></div>
				</div>
			</div>
															
			</div>
            
            </div>
            <div id="divdeclareNewTLB" class="frmpnlbg">
               <div class="box-header subheading">
						<h4><spring:message htmlEscape="true" code="Label.DECNEWTLB"></spring:message></h4>
				</div>
            
             <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYNAMEENGLISH"></spring:message><span id="required" class="mandatory"></span></label>
						<div class="col-sm-6">
							<form:input path="localBodyNameInEn" id="txtlocalBodyNameInEn"  class="form-control" onkeypress="validateAlphanumericKeys();" />
							<div id="txtlocalBodyNameInEn_error" class="mandatory"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
							<div class="mandatory" id="txtlocalBodyNameInEn_error1"><form:errors path="localBodyNameInEn" htmlEscape="true" /></div>
							<div class="mandatory" id="txtlocalBodyNameInEn_error2" style="display: none"></div>
						</div>
			 </div>
			
            <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYNAMELOCAL"></spring:message></label> 
						<div class="col-sm-6">
                          <form:input path="localBodyNameInLcl" id="txtlocalBodyNameInLcl"  class="form-control" onkeypress="validateAlphanumericKeys();" />
                        </div>
              </div>
            
           <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASENGLISH"></spring:message></label>
              <div class="col-sm-6">
                 <form:input path="localBodyAliasInEn" id="txtlocalBodyNmeInAlias"  class="form-control" onkeypress="validateAlphanumericKeys();" />
			   </div>
			</div>
			
			<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.LOCALBODYALIASLOCAL"></spring:message></label>
			  <div class="col-sm-6">
			    <form:input path="localBodyAliasInLcl" id="txtlocalBodyliasInLcl"  class="form-control" onkeypress="validateAlphanumericKeys();" />
			   </div>
			</div>
            
           <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TRIBALLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
			 <div class="col-sm-6">
				 <form:select id="ddTribalLocalBodyTypeNew" path="traditionalLgTypeNameNew" class="form-control" onchange="hideShowDivforNewTLBFinal(this.value);" htmlEscape="true">
					<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message>	</form:option>
		               <c:forEach var="traditionalocalBodyTypelist" varStatus="var" items="${traditionalocalBodyTypelist}">
						<form:option id="typeCode" value="${traditionalocalBodyTypelist.localBodyTypeCode}:${traditionalocalBodyTypelist.level}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${traditionalocalBodyTypelist.nomenclatureEnglish}</esapi:encodeForHTMLAttribute></form:option>
					     </c:forEach>
	           </form:select> 
				<div id="ddTribalLocalBodyTypeNew_error" class="mandatory"><spring:message code="error.blank.ruralLBTypeName" htmlEscape="true"></spring:message></div>
				<div class="mandatory" id="ddTribalLocalBodyTypeNew_error1"><form:errors path="traditionalLgTypeNameNew" htmlEscape="true" /></div>
				<div class="mandatory" id="ddTribalLocalBodyTypeNew_error2" style="display: none"></div>
		     </div>
		   </div>
            
            
            <div id="divDistrictPforNew"  class="form-group" style="visibility: hidden; display: none;">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML><span class="mandatory">*</span></label>
				<div class="col-sm-6" >
					 <form:select  path="lgd_LBDistrictforExistNew" class="form-control" id="ddlgdLBDistrictPforExistNew"  items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true">
					<form:option value="0" htmlEscape="true">	<spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
				    </form:option>
					<form:options items="${districtTraditionalList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
					</form:select> &nbsp;<span><form:errors path="lgd_LBDistrictforExistNew" class="mandatory"></form:errors> </span> &nbsp;&nbsp;<span class="mandatory" id="ddlgdLBDistrictPforExist_error"> </span>
				</div>
			</div>
            
       <div id="divLgdSelIntermediatePforNew" style="visibility: hidden; display: none;"> 
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML> <span class="mandatory">*</span></label>
				  <div class="col-sm-6"	>
					 <form:select path="lgd_LBDistrictAtInterforNew" class="form-control" id="ddlgdLBDistAtInterforNew" onchange="getInterPanchayatAtInterbyDcodeforExist(this.value);" htmlEscape="true">
						  <form:option value="0" htmlEscape="true">
							 <spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
							 </form:option>
							<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
								<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
								<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
								</form:option>
								</c:if>
								<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
								<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
								</form:option>
								</c:if>
							</c:forEach>
						</form:select>
						<div class="mandatory" id="ddlgdLBDistAtInterforNew_error1"> <form:errors path="lgd_LBDistrictAtInterforNew" htmlEscape="true" />
						</div>
						<div class="mandatory" id="ddlgdLBDistAtInterforNew_error2" style="display: none"></div>
					</div> 	
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> <span class="mandatory">*</span></label>
					<div class="col-sm-6">	 
						 <form:select path="lgd_LBIntermediatePanchaytforExist" class="form-control" id="ddlgdLBIntermediatePanchayatforNew" htmlEscape="true">
						   <form:option value="0" htmlEscape="true"> <spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>  </form:option>
						   </form:select>
							<div class="mandatory" id="ddlgdLBIntermediatePanchayatforExist_error1"><form:errors path="lgd_LBIntermediatePanchaytforExist" htmlEscape="true" /></div>
							<div class="mandatory" id="ddlgdLBIntermediatePanchayatforExist_error2" style="display: none"></div>
					</div>
				</div>
				
				
        </div>
            
           <div id="divLgdVillagePforNew" style="visibility: hidden; display: none;"> 
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureDistTrade}</esapi:encodeForHTML><span class="mandatory">*</span></label>
				<div class="col-sm-6">
					<form:select	path="lgd_LBDistrictAtVillageforNew" class="form-control" id="ddlgdLBDistrictAtVillageforNew" onchange="getIntermediatePanchayatbyDcodeforNew(this.value);" htmlEscape="true">
						<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
						<c:forEach items="${districtTraditionalList}" var="districtTraditionalList">
							<c:if test="${districtTraditionalList.operation_state =='A'.charAt(0)}">
							<form:option value="${districtTraditionalList.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
							</form:option>
							</c:if>
							<c:if test="${districtTraditionalList.operation_state =='F'.charAt(0)}">
							<form:option value="${districtTraditionalList.localBodyCode}" disabled="true" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtTraditionalList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																				
							</form:option>
							</c:if>
						</c:forEach>
					</form:select> 
					<div id="ddlgdLBDistrictAtVillageforNew_error" class="mandatory"><spring:message code="error.SELECTHDCOUNCIL" htmlEscape="true"></spring:message></div>
					<div class="mandatory" id="ddlgdLBDistrictAtVillageforNew_error1"><form:errors path="lgd_LBDistrictAtVillageforNew" htmlEscape="true" /></div>
					<div class="mandatory" id="ddlgdLBDistrictAtVillageforNew_error2" style="display: none"></div>
				</div>
			</div>
			
             <div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message>&nbsp;&nbsp; <esapi:encodeForHTML>${convertRLBtoTLBForm.lgd_LBNomenclatureInterTrade}</esapi:encodeForHTML> <span class="mandatory">*</span></label>
	              <div class="col-sm-6">
		              <form:select	path="lgd_LBIntermediateAtVillageforNew" class="form-control" id="ddlgdLBInterAtVillageforNew" htmlEscape="true">
							<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></form:option>
						</form:select>
						<div id="ddlgdLBInterAtVillageforNew_error" class="mandatory"><spring:message code="error.SELECTBACOUNCIL" htmlEscape="true"></spring:message></div>
						<div class="mandatory" id="ddlgdLBInterAtVillageforNew_error1"><form:errors path="lgd_LBIntermediateAtVillageforNew" htmlEscape="true" /></div>
						<div class="mandatory" id="ddlgdLBInterAtVillageforNew_error2" style="display: none"></div>
					</div>
			  </div>
		 </div> 
            
       </div>
            
  
	  
	</div>  
	
	<div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                 <div id="divPreviousButtons" >
                        <button  type="button" value="" onclick="onPreviousClick();" class="btn btn-info" ><spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message></button>
                   </div>
                   <div class="btnpnl" id="divSaveButtons">
		                   <button type="button" value="" onclick="onPreviousClick();" class="btn btn-info" ><spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message></button>
		                   <button type="button" name="Submit"  class="btn btn-success" onclick="return validateFinalAllRLBtoTLB();" value="" ><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
		                   <button type="button" class="btn btn-danger" name="Submit6" value="" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                 </div>
                 </div>
           </div>   
       </div>
        
    </div>      
     </div>
          
            
 </form:form>
    
   </section>
</div>
</section>

</body>
</html>