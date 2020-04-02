<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 

<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>

<title><spring:message  code="Label.CREATENEWBLOCK" htmlEscape="true"></spring:message>
</title>
<script type="text/javascript" src="js/new_block.js" charset="utf-8"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script src="js/helpMessage.js"></script>
<script src="js/common.js"></script>

<script type='text/javascript' src="js/notie/notie.js"></script>
<script type='text/javascript' src="js/notie/notie.min.js"></script>

<link rel="stylesheet" href="js/notie/notie.css">
<link rel="stylesheet" href="js/notie/notie.min.css">
 
<title><spring:message  code="Label.CREATENEWBLOCK" htmlEscape="true"></spring:message></title>
<script type="text/javascript" language="javascript">
function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
}
 $(document).ready(function() {
		$('#blockFormCreation [type=text]').attr("autocomplete",'off');
	}); 
</script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
	
	function checkUnique(blockNameVal,vid){ 
		var districtCode=$( '#ddDistrict').val();
		var statecode=$("#stateCode").val();
		 
		if(districtCode!="" && blockNameVal!="" && blockNameVal!=" " && statecode!="")
		{
		lgdDwrBlockService.newBlockNameIsUnique(blockNameVal,parseInt(districtCode),parseInt(statecode), {
			callback : function( uniqueFlag ) {
				if(uniqueFlag=='D'){
					notie.alert({ type: 2, text: 'Please Enter Unique Block Name!', time: 4 });
					$( '#UniqueBlockError').text("<spring:message code='Error.Block.name.unique.draft' htmlEscape='true'/>"); 
					$("#"+vid).val("");
					$("#"+vid).focus();
				}else if(uniqueFlag=='S'){
					
					notie.alert({ type: 2, text: 'Same Block are available in this state!', time: 4 });
					
					$( '#UniqueBlockError').text("<spring:message code='Error.Block.name.unique.publish' htmlEscape='true'/>"); 
					//$("#"+vid).focus();
				}
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		});
	 }
	}
	
 
	
</script>
</head>
<body onload="hideGISComponentOnLoad();" oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<section class="content">
	
		<div class="row">
		
		<input type="hidden" name="" id="stateCode" value="${stateCode}" />
        	<section class="col-lg-12 ">
	              	<div class="box ">
           				<div class="box-header with-border">
		                	<h3 class="box-title"><spring:message htmlEscape="true" code="Label.CREATENEWBLOCK "></spring:message></h3>
		                </div>
		                <div class="box-body">
		                    
		                    <form:form action="new_block.htm" id="newblockform" class="form-horizontal" method="POST" commandName="newblockform" enctype="multipart/form-data" >
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="new_block.htm"/>" />
								<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
								<%-- <div class="box-header subheading">							
									<c:if test="${flag1 eq 1}">
									<h4><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></h4>
									</c:if>
									<c:if test="${flag1 eq 0}">
									<h4><spring:message htmlEscape="true" code="Label.DISTRICTCAPS"></spring:message></h4>
									</c:if>
		                    	</div> --%>
		                    	<div class="form-group">
		                    	  <label class="col-sm-3 control-label">
		                    	  	<c:if test="${flag1 eq 1}"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></c:if>
									<c:if test="${flag1 eq 0}"><spring:message htmlEscape="true" code="Label.DISTRICTCAPS"></spring:message><span class="mandatory">*</span></c:if>
								</label>
									<div class="col-sm-6">
										<form:select path="districtCode" class="form-control" id="ddDistrict" onclick="clearalllist();" onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');"
											 onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();"	onkeyup="hideMessageOnKeyPress('ddDistrict')" >
											<c:if test="${flag1 eq 1}">
												<form:option value="0">
													<spring:message  code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
												</form:option>
																
																	
												<c:forEach items="${distList}" var="districtList">
													<c:if test="${districtList.operation_state =='A'.charAt(0)}">
														<form:option value="${districtList.districtCode}" >${districtList.districtNameEnglish}
														</form:option>
													</c:if>
													<c:if test="${districtList.operation_state =='F'.charAt(0)}">
														<form:option value="${districtList.districtCode}" disabled="true">${districtList.districtNameEnglish}																				
														</form:option>
													</c:if>
												</c:forEach>
											</c:if>
											<c:if test="${flag1 eq 0}">
												<form:options items="${distList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" />
											</c:if>
										</form:select>
									</div> 
									<div id="ddDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
								</div>
								
								<div class="box-header subheading">
									<h4><spring:message htmlEscape="true" code="Label.GENERALDETAILOFNEWBLOCK"></spring:message></h4>
		                    	</div>
								
								<div class="form-group">
                     					<label for="blockNameEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.BLOCKNAMEEN"></spring:message><span class="mandatory">*</span></label>
                      				<div class="col-sm-6">
	                      				<%-- <form:input path="blockNameEnglish" id="OfficialAddress" class="form-control" htmlEscape="true"
									    onblur="blocknameVal(this.value);chekcalphanumeric(this.value,1);officialAddress()"
										onkeyup="hideMessageOnKeyPress('ddDistrict')"  maxlength="50"/> --%>
										
										<form:input path="blockNameEnglish" id="OfficialAddress" class="form-control" onblur="checkUnique(this.value, this.id);" htmlEscape="true" maxlength="50"/>
										<span class="mandatory">Allowed characters are A-Z,a-z and Space</span>
										<div id="UniqueBlockError" style="color: red;"></div>
										<form:errors path="blockNameEnglish" cssClass="errormsg"/>
										<div id="OfficialAddress_msg" style="display:none"><spring:message code="Error.SOURCEBLOCK" htmlEscape="true"/></div>
                      				</div>
                  				</div>
	                  				
                  				<div class="form-group">
                     					<label for="blockNameLocal" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.BLOCKNAMELOCL"></spring:message></label>
                      				<div class="col-sm-6">
                      				<form:input path="blockNameLocal" htmlEscape="true" class="form-control" onblur="validatelocalCharforBLKValues(this.value,1);"
										onkeyup="hideMessageOnKeyPress('blockNameLocal')" maxlength="50"/> 
										<form:errors path="blockNameLocal" cssClass="errormsg"/>
										<div class="errormsg"></div>
										<div id="blockNameLocal_msg" style="display:none"> <spring:message code="Error.SOURCEBLOCK" htmlEscape="true"/> </div>
	                      				</div>
                  				</div>
	                  				
                  				<div class="form-group">
                     					<label for="aliasEnglish" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ALIASENGLISH"></spring:message></label>
                      				<div class="col-sm-6">
                      				
                      				<form:input  htmlEscape="true"  path="aliasEnglish" onblur="chekcalphanumeric(this.value,2);"
										class="form-control" maxlength="50"/> 
										<form:errors path="aliasEnglish" cssClass="errormsg"/>
								<div class="errormsg"></div>
                      				
                  					</div>
                  				</div>
                  				<div class="form-group">
                     					<label for="aliasLocal" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ALIASLOCAL"></spring:message></label>
                      				<div class="col-sm-6">
                      					<form:input  htmlEscape="true"  path="aliasLocal" onblur="validatelocalCharforBLKValues(this.value,2);" class="form-control" maxlength="50"/>
										<form:errors path="aliasLocal" cssClass="errormsg"/>
										<div class="errormsg"></div>
                      				</div>
                  				</div>
	                  				
                  				<div class="form-group">
                     					<label for="ssCode" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.STATESPECIFICCODE"></spring:message></label>
                      				<div class="col-sm-6">
	                      				<form:input  htmlEscape="true"  path="ssCode" class="form-control" maxlength="5" onblur="chekcalphanumeric(this.value,3);"  />
							
								<div class="errormsg"></div>
                      				</div>
                  				</div>
                  				
                  				
                  				<div class="box-header subheading">
									<h4><spring:message htmlEscape="true" code="Label.HEADQUARTER"></spring:message></h4>
		                    	</div>
								
								<div class="form-group">
			                    	<%@ include file="../common/headquarterCopy.jspf"%>
			                    </div>
			                    
			                    
			                   <%--  <div class="box-header subheading" >
									<h4><spring:message htmlEscape="true" code="Label.GISNODES"></spring:message></h4>
		                    	</div>
			                    <div class="form-group">
			                    	<%@ include file="../common/gis_nodesCopy.jspf"%>
			                    </div> --%>
								
								
								<div class="box-header subheading">
									<h4><spring:message htmlEscape="true" code="Label.CONTRIBUTINGLANDREGION"></spring:message></h4>
		                    	</div>
								<div class="form-group">
								  <div class="col-sm-12">
									  <input type="checkbox" value="true" onclick="getunmapvillageList('ddDistrict');" name="lgd_LBExistCheck" id="Villagelistchek"/>
									  <label for="sel1"><spring:message  code="Label.UnmappedRegion" htmlEscape="true"></spring:message> </label>
								  </div>
								</div>
									<div class="ms_container row" style="margin-left: 10px;">
											<div class="ms_selectable col-sm-5 form-group">
											<label><spring:message htmlEscape="true" code="Label.SELECTVILLAGE"></spring:message></label><br/>
													<form:select name="select9" id="vilunmapbblocks" path="blockList" multiple="multiple" class="form-control"></form:select>
											</div>
											<div class="ms_buttons col-sm-2"><br>
												<button type="button" id="btnaddSubDistrictFull" name="Submit1" onclick="addSelectedItems('VilListNew','vilunmapbblocks',true)" class="btn btn-primary btn-xs btn-block"><spring:message code="Label.SELECTVILLAGE"/>  <i class="fa fa-angle-double-right" aria-hidden="true"></i>
												</button>
												<button type="button" id="btnremoveOneSubDistrict" name="Submit2" onclick="removesingleItem('VilListNew','vilunmapbblocks',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
												</button>
												<button type="button" id="btnremoveAllSubDistricts" name="Submit3" onclick="removeAlls('VilListNew','vilunmapbblocks',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
												</button>
											</div>
											<div class="ms_selection col-sm-5">
											  	<label><spring:message htmlEscape="true" code="Label.VILLAGESTOINVALIDATE"></spring:message></label><br/>
												<form:select name="select1" id="VilListNew"  multiple="multiple" path="VillagesList" class="form-control"></form:select>
		                                	</div>
				                    </div>
				                    
				                 
				                <div class="form-group" style="display:none">
									  <div class="col-sm-12">
										  <input type="checkbox" value="true" onclick="getunmapULBList('ddDistrict');" name="Ulblistchek" id="Ulblistchek"/>
										  <label for="sel1">Select from ULB's</label>
									  </div>
								</div>
								<div class="ms_container row" style="margin-left: 10px; display:none">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.SelectULB"></spring:message></label><br/>
										<form:select name="select9" id="ulbunmapbblocks" path="blockList" multiple="multiple" class="form-control"></form:select>
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="btnaddSubDistrictFull" name="Submit4" onclick="addSelectedItems('ULBListNew','ulbunmapbblocks',true)" class="btn btn-primary btn-xs btn-block">Select ULB <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveOneSubDistrict" name="Submit5" onclick="removesingleItem('ULBListNew','ulbunmapbblocks',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveAllSubDistricts" name="Submit6" onclick="removeAlls('ULBListNew','ulbunmapbblocks',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
									</div>
									<div class="ms_selection col-sm-5">
									  	<label><spring:message htmlEscape="true" code="Label.SelectedULB"></spring:message></label><br/>
										<form:select name="select1" id="ULBListNew"  multiple="multiple" path="UlbLists" class="form-control"></form:select>
                                	 </div>
				                  </div>
								
								
								
								<div class="form-group">
									  <div class="col-sm-12">
										  <input type="checkbox" value="true" onclick="getBlockList();" name="lgd_LBExistCheck" id="Blocklistchek"/>
										  <input type="hidden" value="on" name="Blocklistchek" /> 
										  <label for="sel1">Select from Existing Blocks</label>
									  </div>
								</div>
								<div class="ms_container row" style="margin-left: 10px;">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.SELBLOCK"></spring:message></label><br/>
										<form:select name="select9" id="ddBlock" path="blockList" multiple="multiple" class="form-control"></form:select>
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="btnaddSubDistrictFull" name="Submit7" onclick="addItems('blockListNew','ddBlock','FULL',true)" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.SELECTFULLBLOCK"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveOneSubDistrict" name="Submit8" onclick="removesingleItem('blockListNew','ddBlock',true);clearblocksulbbvil()" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveAllSubDistricts" name="Submit9" onclick="removeAlls('blockListNew','ddBlock',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnaddSubDistrictPart" name="Submit10" onclick="addItems('blockListNew','ddBlock','PART',true);" class="btn btn-primary btn-xs btn-block"><spring:message code="Button.SELECTPARTBLOCK"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> 
										</button>
									</div>
									<div class="ms_selection col-sm-5">
									  	<label><spring:message htmlEscape="true" code="Label.BLOCKLISTSELECTED"></spring:message></label><br/>
										<form:select name="select3" id="blockListNew"  multiple="multiple" path="contributedBlocks" class="form-control">
										</form:select>
                                	 </div>
                                	 
                                	 <div>
                            				<button style="margin-left: 190px;margin-top: 10px;" type="button" id="partSubdistrict" name="Submit11" onclick="getVillageLists()" class="btn btn-primary btn-sm "><i class="fa fa-floppy-o"> </i> <spring:message  code="Button.ULBVillage"></spring:message></button>
                       				
                       				
                       				</div>
				                  </div>
				                  
				                  <div id='cvillage' style="display:none">
				                  <div class="ms_container row" style="margin-left: 10px;">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.SelectBlockUlb"></spring:message></label><br/>
										<form:select name="select9" id="ulbListsofblock" path="" multiple="multiple" class="form-control"></form:select>
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="btnaddVillageFull" name="Submit12" onclick="addItems('blockulblist','ulbListsofblock','FULL',true)" class="btn btn-primary btn-xs btn-block"><spring:message code="Label.SelectULB"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveOneVillage" name="Submit13" onclick="removesingleItem('blockulblist','ulbListsofblock',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveAllVillages" name="Submit14" onclick="removeAlls('blockulblist','ulbListsofblock',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
									</div>
									<div class="ms_selection col-sm-5">
									  	<label><spring:message htmlEscape="true" code="Label.SelectedBlockUlb"></spring:message></label><br/>
										<form:select name="select4" id="blockulblist"  multiple="multiple" path="contributedblockUlb" class="form-control">
										</form:select>
                                	 </div>
				                  </div>
				                  </div>
				                  
				                  
				                  <div class="ms_container row" style="margin-left: 10px;">
									<div class="ms_selectable col-sm-5 form-group">
										<label><spring:message htmlEscape="true" code="Label.SELECTVILLAGE"></spring:message></label><br/>
										<form:select name="select9" id="villageList" path="" multiple="multiple" class="form-control"></form:select>
									</div>
									<div class="ms_buttons col-sm-2"><br />
										<button type="button" id="btnaddVillageFull" name="Submit15" onclick="checkthenAdd()" class="btn btn-primary btn-xs btn-block"><spring:message code="Label.SELECTVILLAGE"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveOneVillage" name="Submit16" onclick="removesingleItem('villageListNew','villageList',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-left" aria-hidden="true"></i>
										</button>
										<button type="button" id="btnremoveAllVillages" name="Submit17" onclick="removeAlls('villageListNew','villageList',true)" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
										</button>
									</div>
									<div class="ms_selection col-sm-5">
									  	<label><spring:message htmlEscape="true" code="Label.VOBSEL"></spring:message></label><br/>
										<form:select name="select4" id="villageListNew"  multiple="multiple" path="contributedVillages" class="form-control">
										</form:select>
                                	 </div>
				                  </div>
								
								
								
								<div class="box-footer">
				                     <div class="col-sm-offset-2 col-sm-10">
				                       <div class="pull-right">
				                       <input type="hidden" name="addAnotherBlock" id="addAnotherBlock"/>
										<form:hidden path="operation" value="C"/>
										<form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>
				                            <button type="button" id="Save" name="Submit" class="btn btn-success" onclick="formSubmitSave();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
											<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:go('cancelBlock.htm');"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
											<button type="button" id="Proceed" style="visibility: hidden" class="btn btn-danger" onclick="formSubmit();"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.PROCEED"></spring:message></button>	
				                        
				                        </div>
				                     </div>   
                  				</div>
								
								
		                    </form:form>
		                    <script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
						</div>
					</div>
			</section>
		</div>
	</section>
			

		
</body>
</html>