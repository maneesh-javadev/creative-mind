<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>E-Panchayat</title> <script src="js/shiftblock.js"></script>

	<script src="js/validation.js"></script>
	<script src="js/common.js"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>	 </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	 </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
		function open_win() {
			
			var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
			//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
		} 
		
		function validateNumeric() {
			var key = event.keyCode;

			if ((key >= 48) && (key <= 58) || (key == 45)) {

			} else {
				event.returnValue = false;
			}
		}
	</script>
</head>


<body onLoad="blockloadpage();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" class="form-horizontal">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form commandName="shiftblock" method="POST" onsubmit="cursorwait();" action="draftShiftBlock.htm" class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftBlock.htm"/>"/>
				      
				                <input type="hidden" id="ddSourceState" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>					
				                <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/> 
							    <form:hidden path="operationCode" value="${shiftblock.operationCode}" htmlEscape="true"/>
								<form:hidden path="districtSName" value="${shiftblock.districtSName}" id="hdnDistrictSName" htmlEscape="true"/>
								<form:hidden path="districtDName" value="${shiftblock.districtDName}" id="hdnDistrictDName" htmlEscape="true"/>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.SHIFTBLOCK" htmlEscape="true"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 class="box-title"><spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message code="Label.SOURCEDISTRICT" htmlEscape="true"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								 <form:select path="districtNameEnglish" Class="form-control" id="ddSourceDistrict" onchange="getTargetDictrictandBlockListFinal(this.value)"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');" onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')" htmlEscape="true">
											<form:option value="" htmlEscape="true"> <spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message> 	</form:option>
											<c:forEach items="${districtList}" var="distListvar">
												<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													<form:option value="${distListvar.districtCode}"
														disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												</c:if>
												<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													<form:option value="${distListvar.districtCode}" htmlEscape="true">	<c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												</c:if>
											</c:forEach>
										</form:select>
										<div id="ddSourceDistrict_error" class="mandatory"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddSourceDistrict_msg" class="mandatory" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceDistrict_error2" style="display: none" ></div>
								  </div>
						</div>   
                 </div> 
             
                       <div class="box-header subheading">
                               <h4 class="box-title"><spring:message code="Label.SELTARGET" htmlEscape="true"></spring:message></h4>
                          </div>
                   <div class="box-body">
						<div class="form-group">
								<label for="ddDestDistrict" class="col-sm-3 control-label"><spring:message code="Label.TARGETDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span> </label>
							 <div class="col-sm-6">		
									<form:select	path="districtNameEnglish"  id="ddDestDistrict" Class="form-control" onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');"
											onblur="vlidateOnblur('ddDestDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDestDistrict')" htmlEscape="true">
						              </form:select>
										<div id="ddDestDistrict_error" class="mandatory"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddDestDistrict_msg" class="mandatory"  style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddDestDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddDestDistrict_error2" style="display: none" ></div>	
							</div>
						</div>
                      </div>
                   
              <div class="box-header subheading">
              <h4 class="box-title"><spring:message code="Label.SHIFTBLOCK" htmlEscape="true"></spring:message></h4> </div>
               <div class="box-body">
	           <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message code="Label.AVAILBLOCKLIST" htmlEscape="true"></spring:message> </label>
		              <form:select id="ddSourceBlock" items="${blockSourceList}" path="blockNameEnglish"  multiple="multiple" itemLabel="blockNameEnglish" itemValue="blockCode" class="form-control" htmlEscape="true">
	                                    </form:select> 
	                      <form:hidden path="blockName" id="hdnBlockName" htmlEscape="true"/>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  onclick="listbox_moveacross('ddSourceBlock', 'ddDestBlock','B')"><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" onclick="listbox_moveacross('ddDestBlock', 'ddSourceBlock','B')"><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRIBUTBLOCKLIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			    <form:select items="${blockDestList}" path="blockNameEnglish" id="ddDestBlock"  multiple="multiple"  class="form-control" onblur="vlidateOnblur('ddDestBlock','1','15','p');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddDestBlock')" htmlEscape="true">
						</form:select>
															
						<div id="ddDestBlock_error" class="mandatory"><spring:message code="Error.BLOCK" htmlEscape="true"></spring:message></div>
						<%-- <div id="ddDestBlock_msg" class="mandatory" style="display:none"><spring:message code="Error.BLOCK" htmlEscape="true"/></div> --%>
						<div class="mandatory" id="ddDestBlock_error1"><form:errors path="blockNameEnglish" htmlEscape="true"/></div>  
					    <div class="mandatory" id="ddDestBlock_error2" style="display: none" ></div>		
		     </div>				
            </div>
         </div>
        </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="submit" class="btn btn-success " name="Submit"  onclick="getBlockName();return validateAll()"  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" ><i class="fa fa-floppy-o"></i> Submit</button>
                   <button type="button" class="btn btn-warning " name="Submit6" onclick="javascript:location.href='shiftblock.htm?<csrf:token uri='shiftblock.htm'/>';"> Clear</button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
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