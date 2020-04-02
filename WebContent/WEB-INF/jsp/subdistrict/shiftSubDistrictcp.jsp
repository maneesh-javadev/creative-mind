<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>E-Panchayat</title>
<script src="js/shiftsubdistrict.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/validation.js"></script>
<script src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
<script src="js/govtorder.js"></script>	
</head>
<body onload="subdistrictloadpage();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form commandName="shiftSubDistrict" method="POST" onsubmit="cursorwait();" action="draftShiftSubDistrict.htm" class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftSubDistrict.htm"/>"/>
				      <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/> 
					  <form:hidden path="operationCode" value="${shiftSubDistrict.operationCode}" htmlEscape="true"></form:hidden>
					  <form:hidden path="stateSName" value="${shiftSubDistrict.stateSName}" id="hdnStateSName" htmlEscape="true"></form:hidden>
					  <form:hidden  path="stateDName" value="${shiftSubDistrict.stateDName}" id="hdnStateDName" htmlEscape="true"></form:hidden> 
					  <form:hidden path="districtSName" value="${shiftSubDistrict.districtSName}" id="hdnDistrictSName" htmlEscape="true"></form:hidden> 
					  <form:hidden path="districtDName" value="${shiftSubDistrict.districtDName}" id="hdnDistrictDName" htmlEscape="true"></form:hidden>
					  <input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />	
                            <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.SHIFTSUBDISTRICT" htmlEscape="true"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 class="box-title"><spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message></h4>
                                </div>
                 <div class="box-body">
                     <c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
						<div class="form-group">
						   <label for="ddSourceState" class="col-sm-3 control-label"><spring:message code="Label.SOURCESTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span> </label>
						  <div class="col-sm-6">
						      <form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="getList(this.value);" onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
									onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceState')"  htmlEscape="true">
									<form:option value="0"  htmlEscape="true">
										<spring:message code="Label.SOURCESTATE" htmlEscape="true"></spring:message>
									</form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"  htmlEscape="true"></form:options>
									</form:select> 
									<div id="ddSourceState_error" class="mandatory" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
									<%-- <div id="ddSourceState_msg" class="mandatory" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div> --%>
									<div class="mandatory" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
									<div class="mandatory" id="ddSourceState_error2" style="display: none" ></div>		
								</div>		
						</div>
						</c:if>
						
                        <c:if test="${fn:containsIgnoreCase(isCenterState,'S')}">
							<div class="form-group"  style="display: none;" > 
							<label for="ddSourceState" class="col-sm-3 control-label"><spring:message code="Label.SOURCESTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span>  </label>
							<div class="col-sm-6">
							   <form:select path="stateNameEnglish" class="form-control" id="ddSourceState" onchange="getList(this.value);" onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
										onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceState')"  htmlEscape="true">
							       <form:option value="0"  htmlEscape="true">
									<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
								</form:option>
								<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"  htmlEscape="true"></form:options>
							</form:select> 
									<div id="ddSourceState_error" class="mandatory" ><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
									<%-- <div id="ddSourceState_msg" class="mandatory" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div> --%>
									<div class="mandatory" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
									<div class="mandatory" id="ddSourceState_error2" style="display: none" ></div>		
											
								</div>
						 </div>		
						 </c:if> 
						 
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SOURCEDISTRICT"></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								 <form:select   path="districtNameEnglish" class="form-control" id="ddSourceDistrict" onchange="getSubDistrictList(this.value)"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');" onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')"  htmlEscape="true">
											<form:option value="0"  htmlEscape="true">
												<spring:message code="Label.SOURCEDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
											<c:forEach items="${distrinctlist}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${distListvar.districtCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${distListvar.districtCode}" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
										</form:select>
										<div id="ddSourceDistrict_error" class="mandatory" ><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddSourceDistrict_msg" class="mandatory" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceDistrict_error2" style="display: none" ></div>	
								  </div>
						</div>   
                 </div> 
              
                       <div class="box-header subheading">
                               <h3 class="box-title"><spring:message code="Label.SELTARGET" htmlEscape="true"></spring:message></h3>
                          </div>
                   <div class="box-body">
					<c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
						<div class="form-group">
							<label for="ddDestState" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TARGETSTATE"></spring:message> 
							<span class="mandatory">*</span> </label>
							<div class="col-sm-6">
						      <form:select 	path="stateNameEnglishTarget" class="form-control" id="ddDestState" onchange="getDestDistrictList(this.value)" onfocus="validateOnFocus('ddDestState');helpMessage(this,'ddDestState_msg');"
											onblur="vlidateOnblur('ddDestState','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDestState')" htmlEscape="true">
							   </form:select>
										<div id="ddDestState_error" class="mandatory"  ><spring:message code="Error.TARGETSTATE" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddDestState_msg" class="mandatory" style="display:none"><spring:message code="Error.TARGETSTATE" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddDestState_error1"><form:errors path="stateNameEnglishTarget" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddDestState_error2" style="display: none" ></div>	
							 </div>			
							</div>				
						</c:if>
						
						<c:if test="${fn:containsIgnoreCase(isCenterState,'S')}">	
						<div class="form-group" style="display: none;"><label for="ddDestState" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TARGETSTATE"></spring:message> <span class="mandatory">*</span> </label>
					      <div class="col-sm-6">				
								<form:select path="stateNameEnglishTarget" class="form-control" id="ddDestState" onchange="getDestDistrictList(this.value)"
											onfocus="validateOnFocus('ddDestState');helpMessage(this,'ddDestState_msg');" onblur="vlidateOnblur('ddDestState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDestState')" htmlEscape="true">
										</form:select> 
										<div id="ddDestState_error" class="mandatory" ><spring:message code="Error.TARGETSTATE" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddDestState_msg" class="mandatory"  style="display:none"><spring:message code="Error.TARGETSTATE" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddDestState_error1"><form:errors path="stateNameEnglishTarget" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddDestState_error2" style="display: none" ></div>	
							</div>			
						  </div>
						</c:if>
						
						<div class="form-group">
								<label for="ddDestDistrict" class="col-sm-3 control-label"><spring:message code="Label.TARGETDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span> </label>
							 <div class="col-sm-6">		
									<form:select  path="districtNameEnglish" Class="form-control" id="ddDestDistrict"  onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');"
											onblur="vlidateOnblur('ddDestDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDestDistrict')" htmlEscape="true">
									 </form:select>
										<div id="ddDestDistrict_error" class="mandatory" ><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddDestDistrict_msg" class="mandatory" style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddDestDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddDestDistrict_error2" style="display: none" ></div>	
							</div>
						</div>
                      </div>
                    <!-- </div>
          <div class="box"> -->
              <div class="box-header subheading">
              <h3 class="box-title"><spring:message code="Label.SHIFTSUBDISTRICT" htmlEscape="true"></spring:message></h3> </div>
               <div class="box-body">
	           <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message code="Label.AVAILSUBDISTRICTLIST" htmlEscape="true"></spring:message> </label>
	               <form:select id="ddSourceSubDistrict" items="${subdistrictSourceList}" path="subdistrictNameEnglish"  multiple="multiple" class="form-control"
												itemLabel="subdistrictNameEnglish" itemValue="subdistrictCode" htmlEscape="true">
							</form:select> 
							<form:hidden path="subDistrictName" id="hdnSubdistrictName" htmlEscape="true"/>
		        </div>
		 <div class="ms_buttons col-sm-2"><br><br>
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="listbox_moveacross('ddSourceSubDistrict', 'ddDestSubDistrict','T')"><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
			<button type="button" class="btn btn-primary btn-xs btn-block" onclick="listbox_moveacross('ddDestSubDistrict', 'ddSourceSubDistrict','T')"><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRIBUTSUBDISTRICTLIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			     <form:select	path="subdistrictNameEnglish" id="ddDestSubDistrict" items="${subdistrictDestList}"  multiple="multiple" class="form-control"
								onfocus="validateOnFocus('ddDestSubDistrict');helpMessage(this,'ddDestSubDistrict_msg');" onblur="vlidateOnblur('ddDestSubDistrict','1','15','p');hideHelp();"
							onkeyup="hideMessageOnKeyPress('ddDestSubDistrict')" htmlEscape="true"> </form:select>
			        <div id="ddDestSubDistrict_error" class="mandatory" ><spring:message code="Error.SUBDISTRICT" htmlEscape="true"></spring:message></div>
					<%-- <div id="ddDestSubDistrict_msg" class="mandatory" style="display:none"><spring:message code="Error.SUBDISTRICT" htmlEscape="true"/></div> --%>
					<div class="mandatory" id="ddDestSubDistrict_error1"><form:errors path="subdistrictNameEnglish" htmlEscape="true"/></div>  
					<div class="mandatory" id="ddDestSubDistrict_error2" style="display: none" ></div>	
		     </div>				
            </div>
         </div>
        </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <input type="submit" name="Submit" id="btnSave"  class="btn btn-success" onclick="getSubdistrictNameEnglish();return validateAll();" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
                   <button type="button" class="btn btn-warning " onclick="javascript:location.href='shiftSubDistrict.htm?<csrf:token uri='shiftSubDistrict.htm'/>';" > Clear</button>
                   <button type="button" class="btn btn-danger " name="Cancel value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
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