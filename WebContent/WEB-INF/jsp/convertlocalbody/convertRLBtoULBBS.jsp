 <%@include file="../common/taglib_includes.jsp"%>
 <%@include file="convertRLBtoULBJs.jsp"%>
 <script src="js/common.js"></script>
 <script src="js/validation.js"></script>
 <script src="js/successMessage.js"></script>
 <script src="js/convertRLBtoULB.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" language="javascript">
var level = "<c:out value='${ddLevel}'/>";

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 

$(document).ready(function() {
	$("#convertForm").validate({ 
    rules: { 
    	localBodyNameInEn:{
    		nameFormatLocal:true
    	},
    	localBodyNameInLcl:{
    	   nameFormatLocal: true
       },
       localBodyliasInLcl:{
    	   nameFormatLocal: true
       }
    }, 
    messages: { 
    	localBodyNameInEn: {
    		required: "<font color='red'><Alias of the local body (In English)br><spring:message code='Error.URBANLBNEW' text='Please enter value'/></font>",
    		nameFormatLocal: "<font color='red'><br><spring:message code='error.enter.valid.format' text='Please enter value in correct format'/></font>"
       },
    	localBodyNameInLcl: {
    		nameFormatLocal: "<font color='red'><br><spring:message code='TRG.req.trg.type' text='Please enter in local language only'/></font>"
       },
       localBodyliasInLcl: {
    	   nameFormatLocal: "<font color='red'><br><spring:message code='TRG.req.trg.need' text='Please enter in local language only'/></font>"
       }
    } 
  });
 });  

</script>
 <section class="content">

      <!-- Main row -->
      <div class="row">
          <!-- main col -->
          <section class="col-lg-12">

          <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.CONVERTRLBTOULB" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		      
		          
		        <form:form commandName="convertRLBtoULB" action="draftConversionRLBtoULB.htm" method="POST" enctype="multipart/form-data" name="convertForm" id="convertForm"  class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftConversionRLBtoULB.htm"/>" />
					<input type="hidden" id="stateid" name="stateid" value="${stateCode}" />
					<input type="hidden" id="coverage" name="coverage" value="" />
					<input type="hidden" id="lbFull" name="lbFull" value="N" />
					<form:hidden path="hdnState" value="${stateCode}" id="hdnState" htmlEscape="true"/>
					<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>
					<form:hidden path="mergeUlbClick" htmlEscape="true" value="${convertRLBtoULB.mergeUlbClick}" id="mergeUlbClick" />
					<form:hidden path="districtLocalBodyCode" htmlEscape="true" value="${convertRLBtoULB.districtLocalBodyCode}" id="hdnDistrictCode" />
					<form:hidden path="interLocalBodyCode" htmlEscape="true" value="${convertRLBtoULB.interLocalBodyCode}" id="hdnInterLBCode" />
					<form:hidden path="declareUlbClick" htmlEscape="true" value="${convertRLBtoULB.declareUlbClick}" id="declareUlbClick" />
					<form:hidden path="operationCode" htmlEscape="true" value="${convertRLBtoULB.operationCode}"></form:hidden>
					<form:hidden path="lbType" value="${convertRLBtoULB.lbType}" htmlEscape="true"></form:hidden>
					
					<div id="divFirstPanel" >
					  <div class="frmpnlbg" id="divDIV">
						 <div class="box-body">
						 
						 <c:if test="${ddLevel.contains('D')}">
						   <div class="form-group">
								  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELECTDISTICTPANCHAYAT"></spring:message><span class="mandatory">*</span></label>
								  <div class="col-sm-6">
								 <form:select class="form-control"  style="width: 100%;" id="ddDistrictPanchayat" path="districtLocalBodies"
									  	         onchange="getIntermediatePanchayatbyDcode(this.value);" onfocus="validateOnFocus('ddDistrictPanchayat');helpMessage(this,'ddDistrictPanchayat_msg');"
											     onblur="hideHelp();vlidateOnblur('ddDistrictPanchayat','1','15','c');"	onkeyup="hideMessageOnKeyPress('ddDistrictPanchayat')">
						                	
						                	<form:option value="0">
												<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
											</form:option>
											<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
												<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
													<form:option value="${districtPanchayatList.localBodyCode}" >${districtPanchayatList.localBodyNameEnglish}
													</form:option>
												</c:if>
												<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
													<form:option value="${districtPanchayatList.localBodyCode}" disabled="true">${districtPanchayatList.localBodyNameEnglish}																				
													</form:option>
												</c:if>
										</c:forEach>
	                				</form:select>
	                				
	                				<span>
										<form:errors path="districtLocalBodies" class="mandatory" htmlEscape="true"></form:errors>
									</span>
								
									<span id="ddDistrictPanchayat_error" class="mandatory" style="display: none;"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message>	</span>
									
								  </div>
								  
							</div>
							</c:if>
						  <br/>	
						  <c:if test="${ddLevel.contains('DI') || ddLevel.contains('IV')}">
						 	<div class="form-group">
	                     		<label for="ddIntermediateLb" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELINTERMPANCHYAT"></spring:message><span class="mandatory">*</span></label>
	                      		<div class="col-sm-6">
	                       			<form:select class="form-control"  data-placeholder="Select a State" style="width: 100%;" id="ddIntermediateLb" path="intermediateLocalBodies"
												onchange="getVillageLBforRLBULB(this.value)" onfocus="validateOnFocus('ddIntermediateLb');helpMessage(this,'ddIntermediateLb_msg');"
												onblur="hideHelp();vlidateOnblur('ddIntermediateLb','1','15','c');"	onkeyup="hideMessageOnKeyPress('ddIntermediateLb')">
						                	
						                	<form:option value="0">
												<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
											</form:option>
											<form:options items="${interPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
	                				</form:select>
	                				<span><form:errors htmlEscape="true" path="intermediateLocalBodies" class="mandatory"></form:errors>	</span> 
	                				<span id="ddIntermediateLb_error" class="mandatory" style="display: none;"><spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message></span>
									
	                      		</div>                
	                  		</div>
	                  		</c:if>
	                  		  <br/>	
			   				
	                           <div class="box-body">
									<div class="ms_container row">
										<div class="ms_selectable col-sm-5 form-group">
											<label for="ddSourceVillageRLBS"><spring:message htmlEscape="true" code="Label.AVAILVILLAGEPANCHAYATLIST"></spring:message></label>
											<form:select
																				id="ddSourceVillageRLBS" items="${villageLBList}"
																				itemLabel="localBodyNameEnglish"
																				itemValue="localBodyCode"
																				path="villagelocalbodyNameSource" 
																				multiple="multiple"  class="form-control">
											</form:select>
										
										
										</div>
										<div class="ms_buttons col-sm-2"><br>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElementVP('FULL')">Whole</button>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElementVP('BACK')">Back &lt;</button>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElementVP('RESET')">Reset &lt;&lt;</button>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElementVP('PART')">Part &gt;&gt;</button>
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label for="ddDestVillageRLBs"><spring:message htmlEscape="true"	code="Label.SELECTEDRLBS"></spring:message><span class="mandatory">*</span></label> 
												<form:select class="form-control" path="villagelocalbodyNameDest"  id="ddDestVillageRLBs"
															 multiple="multiple" onfocus="validateOnFocus('ddDestVillageRLBs');"
															 onblur="vlidateOnblur('ddDestVillageRLBs','1','15','p');" onkeyup="hideMessageOnKeyPress('ddDestVillageRLBs')">
												</form:select>
												<span> <form:errors htmlEscape="true" path="villagelocalbodyNameDest" class="mandatory"></form:errors></span>
												<span id="ddDestVillageRLBs_error" class="mandatory" style="display: none;"><spring:message code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message></span> 
												<span id="ddDestVillageRLBs_PART_error" class="mandatory" style="display: none;"> <spring:message htmlEscape="true" code="error.select.VILLAGEPANCHAYT"></spring:message></span>
											</div>
											
											<div>
												<button type="button" class="btn btn-primary btn-xs btn-block" id="childbtn"><spring:message code="Button.GETCOVEREDAREAOFLOCALBODY" htmlEscape="true"/></button>
											</div>			
										</div>
										
									</div>
	                              </div>
	                           	  <br/>
	                           	  
	                           	   <div class="box-body">
									<div class="ms_container row">
										<div class="ms_selectable col-sm-5 form-group">
											<label for="ddSourceCoveredAreaRLB"><spring:message htmlEscape="true" code="Label.AVAILBALECOVEREDAREA"></spring:message></label>
											<form:select id="ddSourceCoveredAreaRLB" path="localBodyLandRegionAreaSource"	multiple="multiple"  class="form-control">
											</form:select>
											<div id="error_village_coverage" class="mandatory"></div>
											<span id="errorddSourceCoveredAreaRLB" class="errormsg" style="display: none;">Can't select all villages of a partly selected Gram Panchayat(BAMHANI).</span>
											<span id="ddSourceCoveredAreaRLB_error" class="errormsg" style="display: none;">Please Select at least one Village Panchayat to convert into Urban Areas</span>
										</div>
										<div class="ms_buttons col-sm-2"><br></br>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('FORWARD')" >FULL</button>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('PART')" >PART</button>
										<button type="button" class="btn btn-primary btn-xs btn-block" onclick="moveElement('BACK')"> BACK</button>
										
										</div>
										<div class="ms_selection col-sm-5">
											<div class="form-group">
												<label for="ddDestCoveredAreaRLB"><spring:message htmlEscape="true"	code="Label.SELECTCOVEREDAREA"></spring:message><span class="mandatory">*</span></label> 
												<form:select class="form-control" path="localBodyLandRegionAreaDest"  id="ddDestCoveredAreaRLB"
															 multiple="multiple" onfocus="validateOnFocus('ddDestCoveredAreaRLB');helpMessage(this,'ddDestCoveredAreaRLB_msg');"
															 onblur="vlidateOnblur('ddDestCoveredAreaRLB','1','15','p');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDestCoveredAreaRLB')">
												</form:select>
												<span class="mandatory" id="ddDestCoveredAreaRLB_error"> <form:errors path="localBodyLandRegionAreaDest" class="mandatory" htmlEscape="true"></form:errors></span>
												<span style="display: none;" id="ddDestCoveredAreaRLB_msg"><spring:message code="Error.TARGETCOVEREDAREA" htmlEscape="true"></spring:message> </span>
												<span id="errorddDestCoveredAreaRLB" class="mandatory"></span>
											</div>				
										</div>
										
									</div>
	                              </div>
	                           	  <br/>		
						 </div>
					 
					 <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="submit" class="btn btn-success " onclick="return validateForm('R');"><i class="fa fa-floppy-o"></i> <spring:message code="Button.NEXTSTEP" htmlEscape="true"></spring:message></button>
                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                        </div>
                     </div>   
                   </div>
                   </div>
                   
                   </div>
                   
                   	<div id="divSecondPanel" style="display: none" >
                   	
                   	<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    
               	    <div class="form-group">
					  <label  class="col-sm-2 control-label" for="sel1"></label>
					  <div class="col-sm-6">
					  <div >
						  <label class="radio-inline">	
						  <form:radiobutton   path="mergeRLBtoULB" 	 id="rdmergeRLBtoULB" value="M"	onclick="radioClick(this.value);" /> 
						   <spring:message code="Label.MERGERLBTOULB" htmlEscape="true"></spring:message>
						 </label>
						 
					  </div>
					   <div >
						  <label class="radio-inline">	
						 <form:radiobutton	  path="declarenewULB"  id="rddeclarenewULB" onclick="radioClick(this.value);" value="N" htmlEscape="true"/>
						 <spring:message code="Label.DECLARENEWULB" htmlEscape="true"></spring:message>
						 </label>
						 
					  </div>
						<span id="rdmergeRLBtoULB_error" class="mandatory"></span>
						<form:errors path="mergeRLBtoULB" class="mandatory" htmlEscape="true"></form:errors>
						<form:errors path="declarenewULB" class="mandatory" htmlEscape="true"></form:errors>
					  </div>
					</div>
						
					<div class="frmpnlbg" id="divmergeRLB">	
						<div class="box-header subheading">
		       				 <h3 class="box-title"><spring:message code="Label.EXISTINGULB" htmlEscape="true"></spring:message></h3>
		     			 </div>
		     			 
		     			 	<div id="dialog-confirm" title="Do you want to upgrade?" style="display: none">
							<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Are you confirm to upgrade?</p>
						</div>
						
						<div class="form-group">
								  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELURBANTYPEBODY"></spring:message><span class="mandatory">*</span></label>
								  <div class="col-sm-6">
									<form:select class="form-control"  style="width: 100%;" id="ddUrbanLocalBodyType" path="urbanLgTypeName" onchange="getUrbanLocalBodies(this.value);" >
						                	
						               <form:option value="0"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></form:option>
										<form:options items="${urbanlbType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
	                				</form:select>
	                				
		                			    <span id="ddUrbanLocalBodyType_error" class="mandatory" style="display: none"><spring:message code="Error.URBANLBTYPE" htmlEscape="true"></spring:message></span>
										<form:errors path="urbanLgTypeName" class="mandatory" htmlEscape="true"></form:errors>	
								  </div>
								  
						</div>
						
						<div class="form-group">
								  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELURBANLOCALBODY"></spring:message><span class="mandatory">*</span></label>
								  <div class="col-sm-6">
								  	<form:select class="form-control"  style="width: 100%;" id="ddUrbanLocalBody" path="urbanlocalBodyNameEnglish" onchange="askForUpgrade(this);" >
							         	<form:option value="0">
									 		<spring:message code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message>
									 	</form:option>
		                			</form:select>
		                			
		                			<span id="ddUrbanLocalBody_error" class="mandatory" style="display: none"> <spring:message	code="Error.URBANLB" htmlEscape="true"></spring:message> </span>
		                			<form:errors path="urbanlocalBodyNameEnglish" class="mandatory" htmlEscape="true"></form:errors>
								 </div>
								  
						</div>
						
					  <div class="form-group" id="trForUpgrade1" style="display:none;">
						  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELUPGRADEURBANLOCALBODY" htmlEscape="true" text="Select for upgrade"></spring:message><span class="mandatory">*</span></label>
						  <div class="col-sm-6">
						  <label class="radio-inline"><form:radiobutton id="upgradeType" path="upgradeType" value="Y" onclick="selectForUpgrade(this)"/><spring:message code="App.YES" htmlEscape="true" text="Yes"/></label>
						  <label class="radio-inline"><form:radiobutton id="upgradeType" path="upgradeType" value ="N" onclick="selectForUpgrade(this)"/><spring:message code="App.NO" htmlEscape="true" text="No"/></label>
						  <span id="upgradeType_error" class="mandatory" style="display:none;"><spring:message code="Label.SELECTRURALBODY" htmlEscape="true"></spring:message></span>
						  </div>
					  </div>
					  
					  <div class="form-group" id="trForUpgrade2" style="display:none;">
								  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELURBANLOCALBODY"></spring:message><span class="mandatory">*</span></label>
								  <div class="col-sm-6">
								  	<form:select class="form-control"  style="width: 100%;" id="ddUrbanLocalBodyTypeForUpgrade" path="urbanLgTypeName" disabled="true" >
							         	<form:option value="0"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></form:option>
										<form:options items="${urbanlbType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
		                			</form:select>
		                			
		                			<span id="ddUrbanLocalBodyTypeForUpgrade_error" class="mandatory" style="display:none;"><spring:message code="Error.URBANLBTYPE" htmlEscape="true"></spring:message></span>
									<form:errors path="urbanLgTypeName" class="mandatory" htmlEscape="true"></form:errors>
								 </div>
								  
						</div>
                   	
					</div>
						
					<div id="divdeclareNewULB" class="frmpnlbg" style="display: none;">
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message code="Label.NEWULB" htmlEscape="true"></spring:message></h4>
               	    </div><!-- /.box-header -->
               	    
               	    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
                      <div class="col-sm-6">
                       <form:input path="localBodyNameInEn" htmlEscape="true" id="txtlocalBodyNameInEn"  class="form-control" onkeypress="ULbTypeDefualtSet();" />
						<div id="UniqueULBrror" class="mandatory"></div>
						<span class="mandatory" id="txtlocalBodyNameInEn_error" style="display: none;">
							<spring:message htmlEscape="true" code="Error.URBANLBNEW"></spring:message>
						</span>
						<span>
							<form:errors path="localBodyNameInEn" class="mandatory" htmlEscape="true"></form:errors>
						</span>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"></spring:message></label>
                      <div class="col-sm-6">
                       <form:input 	path="localBodyNameInLcl" id="txtlocalBodyNameInLcl" htmlEscape="true" 	size="40" class="form-control" /><span>
						<form:errors path="localBodyNameInLcl" class="mandatory"/></span>
                      </div>
                    </div>
                    
                     <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"></spring:message></label>
                      <div class="col-sm-6">
                       <form:input path="localBodyliasInEn" htmlEscape="true" id="txtlocalBodyNmeInAlias" size="40" class="form-control" onkeypress="validateAlphanumericKeys();" /> 
                       <span><form:errors path="localBodyliasInEn" class="mandatory" htmlEscape="true"></form:errors> </span>
										
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"></spring:message></label>
                      <div class="col-sm-6">
                      <form:input 	path="localBodyliasInLcl" id="txtlocalBodyliasInLcl" size="40" class="form-control" htmlEscape="true" />
					   <span><form:errors path="localBodyliasInLcl" class="mandatory"></form:errors></span>
													
                      </div>
                    </div>
                    
                    
                    <div class="form-group">
					  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.SELURBANTYPEBODY"></spring:message><span class="mandatory">*</span></label>
					  <div class="col-sm-6">
					 <form:select id="ddUrbanLocalBodyTypeNew" path="urbanLgTypeNameNew" onchange="ConvertULBnameVal(this.value);" class="form-control">
						<form:option value="0">
							<spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message>
						</form:option>
						<form:options items="${urbanlbType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" />
					</form:select> 
						
						<span id="ddUrbanLocalBodyTypeNew_error" class="mandatory"><spring:message code="Error.URBANLBTYPENEW" htmlEscape="true"></spring:message> </span>
						 <form:errors path="urbanLgTypeNameNew" class="mandatory" htmlEscape="true"></form:errors>	
					  </div>
								  
					</div>
                    
               	    
					</div>
					
					 <div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       		<button type="button" class="btn btn-success " onclick="onPreviousClick();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.PREVIOUS" htmlEscape="true"></spring:message></button>
                            <button type="submit" class="btn btn-success " onclick="return validateSecondULBAll();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
                             <button type="button" class="btn btn-danger " name="Cancel"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                     </div>   
                   </div>
					
                   	</div>
		            <br/>  
		      </form:form>
		      
          </div> 
         </section>
         <script src="/LGD/JavaScriptServlet"></script>
      </div> 
 </section>  