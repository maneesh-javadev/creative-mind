
<%@include file="../common/taglib_includes.jsp"%>

<html>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1"/>
<title>E-Panchayat</title>


<script src="js/shiftvillage.js"></script>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<script src="js/validation.js"></script>
<script src="js/common.js"></script>

<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>


<script type="text/javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
	
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>

<body onload="loadpage();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form commandName="shiftvillageblock" onsubmit="cursorwait();" action="draftShiftVillageBlock.htm" class="form-horizontal">
				        <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftVillageBlock.htm"/>"/>
				      
				                <input type="hidden" id="districtCode" name="districtCode" value="<c:out value='${districtCode}'/>"/>
								<form:hidden htmlEscape="true"  path="govtOrderConfig" value="${govtOrderConfig}"/> 
								<form:hidden path="operationCode" value="${shiftvillageblock.operationCode}"></form:hidden> 
								<form:hidden path="operation" htmlEscape="true"  value="${shiftvillageblock.operation}"></form:hidden> 
								<form:hidden path="districtSName" htmlEscape="true"  value="${shiftvillageblock.districtSName}" id="hdnDistrictSName"></form:hidden>
							    <form:hidden path="blockSName" value="${shiftvillageblock.blockSName}" id="hdnBlockSName"></form:hidden>
								<form:hidden path="blockDName" id="hdnBlockDName" htmlEscape="true"  value="${shiftvillageblock.blockDName}"></form:hidden>
								<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.SHIFTVILLAGEBLOCK" htmlEscape="true"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 class="box-title"><spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                        <c:if test="${districtCode == 0}">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message  htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message><span class="mandatory">*</span></label> 
								<div class="col-sm-6" >
								 <form:select path="districtNameEnglish"  id="ddSourceDistrict" Class="form-control" onchange="getSourceBlockListFinal(this.value);" onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
											<form:option value="" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTRICT" htmlEscape="true" ></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<c:forEach items="${distrinctlist}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option htmlEscape="true" value="${distListvar.districtCode}" disabled="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option htmlEscape="true" value="${distListvar.districtCode}"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
									</form:select> 
									 <div id="ddSourceDistrict_error" class="mandatory"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div> 
										<%-- <div id="ddSourceDistrict_msg" class="mandatory" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceDistrict_error2" style="display: none" ></div>			
								  </div>
						</div>   
				   </c:if>	
				   
				   
				      <div class="form-group">
								<label for="ddSourceBlock" class="col-sm-3 control-label"><spring:message code="Label.SOURCEBLOCK" htmlEscape="true" ></spring:message> <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								   <form:select htmlEscape="true" path="blockNameEnglish"  Class="form-control" id="ddSourceBlock" onchange="getVillageBlockList(this.value);"
											onfocus="validateOnFocus('ddSourceBlock');helpMessage(this,'ddSourceBlock_msg');" onblur="vlidateOnblur('ddSourceBlock','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceBlock')">
											<form:option value="" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message code="Label.SOURCEBLOCK" htmlEscape="true" ></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options htmlEscape="true" items="${blockSourceList}" itemValue="blockCode" itemLabel="blockNameEnglish"></form:options>
										</form:select>
										 <div id="ddSourceBlock_error" class="mandatory"><spring:message code="Error.SOURCEBLOCK" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddSourceBlock_msg" class="mandatory" style="display:none"><spring:message code="Error.SOURCEBLOCK" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddSourceBlock_error1"><form:errors path="blockNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddSourceBlock_error2" style="display: none" ></div>		
								  </div>
						</div>  	
                 </div> 
             
             
             
                       <div class="box-header subheading">
                               <h3 class="box-title"><spring:message code="Label.SELTARGET" htmlEscape="true"></spring:message></h3>
                          </div>
                    
                   <div class="box-body">
                    <c:if test="${districtCode == 0}">
						<div class="form-group">
								<label for="ddTargetDistrict" class="col-sm-3 control-label"><spring:message  htmlEscape="true"  code="Label.TARGETDISTRICT"></spring:message><span class="mandatory">*</span></label>
							 <div class="col-sm-6">		
									<form:select htmlEscape="true" path="districtNameEnglish"  id="ddTargetDistrict" Class="form-control" onchange="getTargetBlockList(this.value);"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');" onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
								      </form:select> 
										 <div id="ddTargetDistrict_error" class="mandatory"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div> 
										<%-- <div id="ddTargetDistrict_msg" class="mandatory" style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddTargetDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddTargetDistrict_error2" style="display: none" ></div>				
							</div>
						</div>
						</c:if>
						<div class="form-group">
								<label for="ddTargetBlock" class="col-sm-3 control-label"><spring:message code="Label.TARGETBLOCK" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							 <div class="col-sm-6">		
									 <form:select htmlEscape="true" path="blockNameEnglish"  Class="form-control" id="ddTargetBlock" onfocus="validateOnFocus('ddTargetBlock');helpMessage(this,'ddTargetBlock_msg');"
											onblur="vlidateOnblur('ddTargetBlock','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddTargetBlock')">
											<form:option value="" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message code="Label.TARGETBLOCK" htmlEscape="true" ></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											
										</form:select> 
									    <div id="ddTargetBlock_error" class="mandatory"><spring:message code="Error.TARGETBLOCK" htmlEscape="true"></spring:message></div>
										<%-- <div id="ddTargetBlock_msg" class="mandatory" style="display:none"><spring:message code="Error.TARGETBLOCK" htmlEscape="true"/></div> --%>
										<div class="mandatory" id="ddTargetBlock_error1"><form:errors path="blockNameEnglish" htmlEscape="true"/></div>  
										<div class="mandatory" id="ddTargetBlock_error2" style="display: none" ></div>				
							</div>
						</div>
						
						
						
                      </div>
                   
              <div class="box-header subheading">
              <h3 class="box-title"><spring:message code="Label.SHIFTVILLAGE" htmlEscape="true"></spring:message></h3> </div>
               <div class="box-body">
	           <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	              <label for="ddSourceVillage"><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message> </label>
	              <form:select  id="ddSourceVillage" path="villageNameEnglish"  multiple="multiple" class="form-control">
								</form:select> 
						<form:hidden path="villageName" id="hdnVillageName" htmlEscape="true" />
		        </div>
		 <div class="ms_buttons col-sm-2"><br><br>
			<button type="button" class="btn btn-primary btn-xs btn-block"  onClick="listbox_moveacross('ddSourceVillage', 'ddTargetVillage','V')"><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
			<button type="button" class="btn btn-primary btn-xs btn-block" onClick="listbox_moveacross('ddTargetVillage', 'ddSourceVillage','V')"><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
		 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			    <form:select htmlEscape="true" path="villageNameEnglish" id="ddTargetVillage"  multiple="multiple" class="form-control"
							onfocus="validateOnFocus('ddTargetVillage');helpMessage(this,'ddTargetVillage_msg');"
							onblur="vlidateOnblur('ddTargetVillage','1','15','p');hideHelp();" onkeyup="hideMessageOnKeyPress('ddTargetVillage')">
					</form:select> 
					 <div id="ddTargetVillage_error" class="mandatory"><spring:message code="Error.VILLAGE" htmlEscape="true"></spring:message></div> 
					<%-- <div id="ddTargetVillage_msg" class="mandatory" style="display:none"><spring:message code="Error.VILLAGE" htmlEscape="true"/></div> --%>
					<div class="mandatory" id="ddTargetVillage_error1"><form:errors path="villageNameEnglish" htmlEscape="true"/></div>  
					<div class="mandatory" id="ddTargetVillage_error2" style="display: none" ></div>			
		     </div>				
            </div>
         </div>
        </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="submit" class="btn btn-success " id="btnSave" name="Submit"  onclick="return validateBlockAll();getVillageName();"  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" ><i class="fa fa-floppy-o"></i> Submit</button>
                   <button type="button" class="btn btn-warning " name="Submit6"   onclick="javascript:location.href='shiftvillageblock.htm?<csrf:token uri='shiftvillageblock.htm'/>';"> Clear</button>
                   <button type="button" class="btn btn-danger " name="Cancel" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
	

</body>
</html>