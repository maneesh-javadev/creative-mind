
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="blockVillagesMappingJs.jsp"%>
<%@include file="blockVillagesMappingJs1.jsp"%>

<title><spring:message htmlEscape="true"  code="Label.ASSIGNEDVILLAGES"></spring:message></title>
</head>
<body>

<section class="content">
	<div class="row">
        <section class="col-lg-12 ">
	        <div class="box ">
        		<div class="box-header with-border">
               		<h3 class="box-title"><spring:message htmlEscape="true" code="Label.ASSIGNEDVILLAGES"></spring:message></h3>
               </div>
               
          <div class="box-body">
            	<%-- <div class="box-header subheading">
                        	<h4><spring:message code="Label.SelectConstituency" htmlEscape="true" text="Select Constituency "></spring:message></h4>
		       </div> --%>
		        <form:form class="form-horizontal" action="saveBlockVillageMapping.htm" method="post" id="blockVillageForm" commandName="blockVillage">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="saveBlockVillageMapping.htm"/>" />

				<c:if test="${!isDistrict}">
				 <div class="form-group">
				 
               		<label class="col-sm-3 control-label">
                  	  	<spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span>
				   </label>
					  <div class="col-sm-6">
						<form:select path="" id="districtCode" class="form-control">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
								<c:forEach  var="obj" items="${districtList}">
									 <c:choose>
									 	<c:when test="${obj.operation_state == 'F'.charAt(0)}">
									 		 <form:option value="${obj.districtCode}" disabled="true">${obj.districtNameEnglish}</form:option>
									 	</c:when>
									 	<c:otherwise>
									 		<form:option value="${obj.districtCode}">${obj.districtNameEnglish}</form:option>
									 	</c:otherwise>	 
									 </c:choose>	
											  
								</c:forEach>
							</form:select>
						<span class="errormessage" id="errdistrictCode"></span>
					</div> 						
				</div>
			   </c:if>
																
			   <div class="form-group">
                 	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TARGETBLOCK"></spring:message><span class="mandatory">*</span></label>
                  	  <div class="col-sm-6">
                   		<form:select path="blockCode" id="ddTargetBlock" class="form-control">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECT"/></form:option>
								<c:forEach items="${blockList}" var="obj">
								  <c:if test="${obj.operation_state == 'F'.charAt(0)}">
								    <form:option value="${obj.blockCode}-${obj.blockVersion}" disabled="true" htmlEscape="true"><c:out value="${obj.blockNameEnglish}" escapeXml="true"></c:out> </form:option>
								  </c:if>  
								  <c:if test="${obj.operation_state == 'A'.charAt(0)}">
								     <form:option value="${obj.blockCode}-${obj.blockVersion}" htmlEscape="true"><c:out value="${obj.blockNameEnglish}" escapeXml="true"></c:out></form:option>
								  </c:if>
								</c:forEach>
						</form:select>
						<span class="errormessage" id="errddTargetBlock"></span>
					  <form:errors htmlEscape="true" path="blockCode" class="mandatory"/>
                   </div>
              </div>
              
          <c:choose>
          <c:when test="${!configVillagePartFullMap}">
            <div class="ms_container row" style="margin-left: 10px;">
				<div class="ms_selectable col-sm-5 form-group">
				<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
					<form:select path="villageMappedListDel" id="ddSourceVillage" multiple="multiple" class="form-control"/>
				</div>
				
				<div class="ms_buttons col-sm-2"><br></br>
					<button type="button" id="btnEventCoverage" onclick="moveElementnew('FORWARD')" class="btn btn-primary btn-xs btn-block">Forward  <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
					<button type="button" id="btnEventCoverage" onclick="moveElementnew('BACK')" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-left" aria-hidden="true"></i></button>
			      
			   </div>
				
				<div class="ms_selection col-sm-5">
					<label><spring:message htmlEscape="true" code="Label.VILLAGESTOINVALIDATE"/><span class="mandatory">*</span></label><br/>
						<form:select path="villageMappedListNew" id="ddTargetVillage" multiple="multiple" class="form-control"/>
						<span class="errormessage" id="errddTargetVillage"></span>
						<form:errors htmlEscape="true" path="villageMappedListNew" class="mandatory"/>	
						<span class="mandatory" id="errchangevillage"></span>
				</div>
				
				<br/>
				
			</div>
            
            <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-10">
                   <div class="pull-right">
                   	 <button type="button" id="btnFormActionProceestoSave" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.SAVE"/></button>
		 			<button type="button" id="btnActionClose" onclick="callActionUrl('home.htm')" class="btn btn-danger"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"/></button>	
                    
                   </div>
                </div>   
            </div>
          
          </c:when>
          <c:otherwise>
             <div class="ms_container row" style="margin-left: 10px;">
	          <div class="ms_selectable col-sm-5 form-group">
				<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"/></label>
				<form:select path="villageMappedListDel" id="ddSourceVillage" multiple="multiple"  class="form-control "/>
			</div>
			 <div class="ms_buttons col-sm-2"><br>
		 
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Back"  param="Back" onclick="moveElement('ddTargetVillage','ddSourceVillage','BACK');" >Back</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Reset" param="Reset" onclick="moveElement('ddTargetVillage','ddSourceVillage','RESET');" >Reset</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage"  level="SubdistrictLevel" paramLBLR="LB" value="Whole" param="Whole" onclick="moveElement('ddSourceVillage','ddTargetVillage','Full');">whole</button>
			<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnEventCoverage" level="SubdistrictLevel" paramLBLR="LB" value="Part"  param="Part" onclick="moveElement('ddSourceVillage','ddTargetVillage','PART');">Part</button>
		
		 </div>
			<div class="ms_selection col-sm-5">
			<label><spring:message htmlEscape="true" code="Label.VILLAGESTOINVALIDATE"/><span class="mandatory">*</span></label><br/>
			<form:select path="villageMappedListNew" id="ddTargetVillage" multiple="multiple" class="form-control"/>
			<span class="errormessage" id="errddTargetVillage"></span>
			<form:errors htmlEscape="true" path="villageMappedListNew" class="mandatory" />	
			<button 	type="button"  class="btn btn-primary"  value="<spring:message code="Get LocalBody list" text="Get LocalBody list " />" style="width:100%;" onclick="coverageDetail('ddTargetVillage');"><spring:message code="Button.GetVillageList" text="Get Localbody list" /></button>
			</div>
         </div>
         

         <div class="ms_container row" style="margin-left: 10px;"> 
	           <div class="ms_selectable col-sm-5 form-group">
	               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Available Localbody list"/></label>
	              <select id="localbodyCoverageVillageList"	 multiple="multiple" class="form-control">
						
				   </select>
		        </div>
		 <div class="ms_buttons col-sm-2"><br></br>
							<button type="button" id="btnEventCoverage" onclick="moveElementnew1('FORWARD')" class="btn btn-primary btn-xs btn-block"><i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" id="btnEventCoverage" onclick="moveElementnew1('BACK')" class="btn btn-primary btn-xs btn-block"> <i class="fa fa-angle-left" aria-hidden="true"></i></button>
		</div>
		<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Covered Localbody list"/><span class="mandate">*</span></label> 
			    <form:select id="lbCoverageVillageList" path="lbCoverageVillageList"	 multiple="multiple" class="form-control" />
				<span class="mandatory" id="errlbCoverageVillageList"></span>	
					
		     </div>				
         </div>
        
         <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       	 <button type="button" id="btnFormActionProceestoSave" class="btn btn-success"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.SAVE"/></button>
						 <button type="button" id="btnActionClose" onclick="callActionUrl('home.htm')" class="btn btn-danger"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"/></button>	
                        </div>
                       </div>
                    </div>
          
          
          </c:otherwise>
          
          </c:choose>    
          
      </form:form>
	</div>
  </section>
 </div>
</section>	

</body>
</html>

