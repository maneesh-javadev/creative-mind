<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>
<script src="js/validation.js"></script>
<script src="js/shiftvillage.js"></script>
<script src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
	
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>


<body onload="loadpagevillSubdistrict();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form commandName="shiftvillageSubDistrict" onsubmit="cursorwait();" action="draftShiftVillageSD.htm" class="form-horizontal">
				       <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftVillageSD.htm"/>"/>
				      
				               <input type="hidden" name="districtCode" value="<c:out value='${districtCode}'/>"/>
							   <input type="hidden" name="stateCode" value="<c:out value='${stateCode}'/>"/>
							   <form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}"/>
							   <form:hidden path="operationCode" value="${shiftvillageSubDistrict.operationCode}" htmlEscape="true"></form:hidden>
							   <form:hidden path="operation" htmlEscape="true" value="${shiftvillageSubDistrict.operation}"></form:hidden> 
							   <form:hidden  path="stateSName" htmlEscape="true" value="${shiftvillageSubDistrict.stateSName}" id="hdnStateSName"></form:hidden>
							   <form:hidden path="districtDName" htmlEscape="true" value="${shiftvillageSubDistrict.districtDName}" id="hdnDistrictDName"></form:hidden>
							   <form:hidden path="districtSName" htmlEscape="true" value="${shiftvillageSubDistrict.districtSName}" id="hdnDistrictSName"></form:hidden> 
							   <form:hidden path="subdistrictSName" htmlEscape="true" value="${shiftvillageSubDistrict.subdistrictSName}" id="hdnSubdistrictSName"></form:hidden> 
							   <form:hidden path="subdistrictDName" htmlEscape="true" value="${shiftvillageSubDistrict.subdistrictDName}" id="hdnSubdistrictDName"></form:hidden>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Shift Village Between Subdistricts</h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 class="box-title"><spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                        	<c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
                        	<div class="form-group">
                        	    <label for="ddSourceState" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span></label>
									<div class="col-sm-6">
									 <form:select path="stateNameEnglish" class="form-control" Class="form-control" id="ddSourceState" onchange="getDistrictList(this.value);"
											onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');" onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceState')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
											</form:option>
											<form:options items="${stateSourceList}"
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish" htmlEscape="true"></form:options>
										</form:select> 
										
										<div id="ddSourceState_error" class="mandatory"><spring:message code="Error.STATE" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddSourceState_msg" style="display:none"><spring:message code="Error.STATE" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceState_error2" style="display: none" ></div>	
								 </div>			
								</div>
							</c:if> 
                        
                        
                        <c:if test="${districtCode == 0}">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SOURCEDISTRICT"></spring:message><span class="mandatory">*</span></label> 
								<div class="col-sm-6" >
								<form:select path="districtNameEnglish" class="form-control"  id="ddSourceDistrict" onchange="getTargetDistrictandSubdistrictList(this.value);"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');" onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')" htmlEscape="true">
										    <form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
											<c:forEach items="${districtSourceList}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${distListvar.districtCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${distListvar.districtCode}" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
										</form:select>
									
									    <div id="ddSourceDistrict_error" class="mandatory" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddSourceDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceDistrict_error2" style="display: none" ></div>		 
								  </div>
						</div>   
				   </c:if>	
				   
				   
				      <div class="form-group">
								<label  class="col-sm-3 control-label" for="ddSourceSubDistrict"><spring:message code="Label.SOURCESUBDISTRICT" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select path="subdistrictNameEnglish" class="form-control"  id="ddSourceSubDistrict" onchange="getVillageList(this.value)"
											onfocus="validateOnFocus('ddSourceSubDistrict');helpMessage(this,'ddSourceSubDistrict_msg');" onblur="vlidateOnblur('ddSourceSubDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceSubDistrict')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
												<c:forEach items="${subdistrictSourceList}" var="SubdistListvar">
												  <c:if test="${SubdistListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${SubdistListvar.subdistrictCode}" disabled="true"><c:out value="${SubdistListvar.subdistrictNameEnglish}" escapeXml="true"></c:out>'</form:option>
												  </c:if>  
												  <c:if test="${SubdistListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${SubdistListvar.subdistrictCode}"><c:out value="${SubdistListvar.subdistrictNameEnglish}" escapeXml="true"></c:out>'</form:option>
												  </c:if>
											</c:forEach>
											
										</form:select>
								
										 <div id="ddSourceSubDistrict_error" class="mandatory"><spring:message code="Error.SOURCESUBDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddSourceSubDistrict_msg" style="display:none"><spring:message code="Error.SOURCESUBDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceSubDistrict_error1"><form:errors path="subdistrictNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceSubDistrict_error2" style="display: none" ></div> 		
								  </div>
						</div>  	
                 </div> 
             
             
             
                       <div class="box-header subheading">
                               <h3 class="box-title"><spring:message code="Label.SELTARGET" htmlEscape="true"></spring:message></h3>
                          </div>
                   <div class="box-body">
                     <c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
						<div class="form-group">
						   <label for="ddTargetState" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mandatory">*</span> </label>
						   <div class="col-sm-6">
						   <form:select path="stateNameEnglish" class="form-control" Class="form-control" id="ddTargetState" onchange="getTargetDistrictList(this.value);"
												onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddTargetState_msg');" onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddSourceState')" htmlEscape="true">
										</form:select> 
												 <div id="ddTargetState_error" class="mandatory"><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
												<%-- <div id="ddTargetState_msg" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div> --%>
												<div class="mandatory" id="ddTargetState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
												<div class="mandatory" id="ddTargetState_error2" style="display: none" ></div>	 		
											</div>
										</div>
							</c:if>
                   
                   
                   
                   <c:if test="${districtCode == 0}">
                   
						<div class="form-group">
								<label for="ddTargetDistrict" class="col-sm-3 control-label"><spring:message code="Label.TARGETDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							 <div class="col-sm-6">		
									<form:select path="districtNameEnglish" class="form-control"  id="ddDestDistrict" onchange="getTargetSubDistrictList(this.value);"
												onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');" onblur="vlidateOnblur('ddDestDistrict','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddDestDistrict')" htmlEscape="true">
												<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
												</form:option>
										       <c:forEach items="${districtTargetList}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${distListvar.districtCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${distListvar.districtCode}" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
								  </form:select>
											
											 <div id="ddDestDistrict_error" class="mandatory"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div>
											<%-- <div id="ddDestDistrict_msg" style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div> --%>
											<div class="mandatory" id="ddDestDistrict_error1"><form:errors path="districtNameEnglishDest" htmlEscape="true"/></div>  
											<div class="mandatory" id="ddDestDistrict_error2" style="display: none" ></div>							
							</div>
						</div>
						</c:if>
						<div class="form-group">
								<label for="ddTargetBlock" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TARGETSUBDISTRICT"></spring:message><span class="mandatory">*</span></label>
							 <div class="col-sm-6">		
									  <form:select path="subdistrictNameEnglishTarget" class="form-control" id="ddTargetSubDistrict" onfocus="validateOnFocus('ddTargetSubDistrict');helpMessage(this,'ddTargetSubDistrict_msg');"
											onblur="vlidateOnblur('ddTargetSubDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddTargetSubDistrict')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
										</form:select>
										
										 <div id="ddTargetSubDistrict_error" class="mandatory"><spring:message code="Error.TARGETSUBDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddTargetSubDistrict_msg" style="display:none"><spring:message code="Error.TARGETSUBDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddTargetSubDistrict_error1"><form:errors path="subdistrictNameEnglishTarget" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddTargetSubDistrict_error2" style="display: none" ></div>		 	
							</div>
						</div>
						
						
						
                      </div>
                   
              <div class="box-header subheading">
              <h3 class="box-title"><spring:message code="Label.SHIFTVILLAGE" htmlEscape="true"></spring:message></h3> </div>
               <div class="box-body">
	           <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	             <label for="ddSourceVillage"><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message> </label>
	              <form:select	id="ddSourceVillage" path="villageNameEnglish" multiple="multiple" class="form-control" htmlEscape="true">
								</form:select> 
					<form:hidden path="villageName" id="hdnVillageName" htmlEscape="true"/>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="listbox_moveacross('ddSourceVillage', 'ddTargetVillage','V')"><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" onclick="listbox_moveacross('ddTargetVillage', 'ddSourceVillage','V')" ><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					 </div>
			<div class="ms_selection col-sm-5">
				 <div class="form-group">
				    <label for="ddTargetVillage"><spring:message code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
				    <form:select path="villageNameEnglish" id="ddTargetVillage" multiple="multiple" class="form-control" onfocus="validateOnFocus('ddTargetVillage');helpMessage(this,'ddTargetVillage_msg');"
															onblur="vlidateOnblur('ddTargetVillage','1','15','p');hideHelp();" onkeyup="hideMessageOnKeyPress('ddTargetVillage')" htmlEscape="true">
						</form:select>
														
						 <div id="ddTargetVillage_error" class="mandatory"><spring:message code="Error.VILLAGE" htmlEscape="true"></spring:message></div>
						<%-- <div id="ddTargetSubDistrict_msg" style="display:none"><spring:message code="Error.VILLAGE" htmlEscape="true"/></div> --%>
						<div class="mandatory" id="ddTargetVillage_error1"><form:errors path="villageNameEnglish" htmlEscape="true"/></div>  
						<div class="mandatory" id="ddTargetVillage_error2" style="display: none" ></div> 			
			     </div>				
            </div>
         </div>
        </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="submit" class="btn btn-success " id="btnSave" name="Submit"  onclick="return validateSDAll();getVillageName();"  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" ><i class="fa fa-floppy-o"></i> Submit</button>
                   <button type="button" class="btn btn-warning " name="Submit6" value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"  onclick="javascript:location.href='shiftvillageSubDistrict.htm?<csrf:token uri='shiftvillageSubDistrict.htm'/>';" > Clear</button>
                   <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>