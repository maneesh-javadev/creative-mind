<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>


<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
	<!-- <script src="js/convertRLBtoTLB.js"></script> -->

	<script src="js/mapParliament.js"></script>
	<script src="js/common.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/validation.js"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
	<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	<script src="datepicker/calender.js"></script> -->
    <script type="text/javascript" src="js/map_Ward.js"
	charset="utf-8"></script>
	<script src="js/new_ward.js"></script>
	<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
    
	$( document ).ready(function() {
		var rightpanel = document.getElementById("rpnls");
		
		rightpanel.style.overflow = "hidden";
		
		
	});
	</script>
	
	
	
<script src="<%=contextPath %>/jquery-1.7.2.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.core.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.widget.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.button.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.mouse.js"></script>	
<script src="<%=contextPath %>/ui/jquery.ui.draggable.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.position.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.dialog.js"></script>
<script src="<%=contextPath %>/ui/jquery.validate.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.accordion.js"></script>
<script src="<%=contextPath %>/external/jquery.bgiframe-2.1.2.js"></script>
<script src="<%=contextPath %>/ui/jquery.ui.resizable.js"></script>
<script src="<%=contextPath %>/ui/jquery.effects.core.js"></script>
<script src="<%=contextPath %>/ui/jquery.effects.blind.js"></script>
<script src="<%=contextPath %>/ui/jquery.effects.explode.js"></script>
<script type="text/javascript"  src="<%=contextPath %>/js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="<%= contextPath %>/js/jquery.tabs.pack.js"></script>
<script src="<%= contextPath %>/js/jquery-blink.js" language="javscript" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<link rel="stylesheet" href="<%=contextPath %>/css/demos.css"></link>
<link rel="stylesheet" href="<%=contextPath %>/themes/base/jquery.ui.all.css"></link>
	
	
<script>
	dwr.engine.setErrorHandler(function(message,exception){
		var form = document.getElementById("ajaxErrorFrom");			
		form.submit();
	});
	window.onerror = noError;
	$(document).ready(function() {
			$('.blink').blink();
	});
	function noError() {
		return true;
	}

	displayLoadingImage = function() {
		$.blockUI({ 
			theme: true,
			title: 'Loading...',
			message: $('#displayBox'), 
	        css: { 
	            top:  ($(window).height()) /2 + 'px', 
	            left: ($(window).width() - 100) /2 + 'px',
	            border: 'none', 
	            padding: 'none'                    
	          
	        } 
	    }); 
	};
	
	showLoadingImage = function() {
		$.blockUI({ 
			theme:true,
			title: 'Loading...',
			message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/images/ajax-loader-2.gif'/></div>"
	    }); 
	};

	hideLoadingImage = function(){
		$.unblockUI();
	};
	
	dwr.engine.setPreHook(showLoadingImage);
	dwr.engine.setPostHook(hideLoadingImage);
	
	
	customAlert = function(message){
		$("#cAlert").html(message);
		$("#cAlert").dialog({
			title: "Alert",
			resizable : false,
			height : 160,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	};
	</script>
</head>

<body >

<section class="content">
	<div class="row" id="frmcontent">
        <section class="col-lg-12 ">
	        <div class="box ">
        		<div class="box-header with-border">
               		<h3 class="box-title"><spring:message code="Label.MapZpconstituency" htmlEscape="true" text="Map ZP Constituency"></spring:message></h3>
               </div>
               
          <div class="box-body">
            	<div class="box-header subheading">
                        	<h4><spring:message code="Label.SelectConstituency" htmlEscape="true" text="Select Constituency "></spring:message></h4>
		       </div>
		        <form:form class="form-horizontal" action="mapZpConsituencyData.htm" method="POST" id="form1" commandName="localGovtBodyForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="mapZpConsituencyData.htm"/>" htmlEscape="true" />
				<input type="hidden" name="stateCode" id="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" htmlEscape="true" />
				<form:hidden path="listformat" value="" id="listformat" htmlEscape="true"/>
				<form:hidden path="contVillage" value="" id="contVillage" htmlEscape="true"/>
				<input type="hidden" name="mappedvillageList" id="mappedvillageList"	value="" htmlEscape="true" />		
				<input type="hidden" name="mappedgpList" id="mappedgpList"	value="" htmlEscape="true"/>	
				<form:hidden path="lgd_hiddenLbcList" value="" id="deletedlbList" htmlEscape="true"/>
				<form:hidden path="villagePanchayat" value="" id="deletedvillageList" htmlEscape="true"/>				
				<form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}" />

				<div id="cat">
				 <div class="form-group">
               		<label class="col-sm-3 control-label">
                  	  	<spring:message code="Label.ZPanchyat" text="Select Zilla  Panchyat"></spring:message><span class="mandatory">*</span>
				   </label>
					  <div class="col-sm-6">
						<c:if test="${twoTierStructure == true}">
						  <form:select htmlEscape="true" path="districtPanchayatName" class="form-control" id="ZilaPanchyatID" onchange="getZPWardsLists(this.value);getVillagePanchyatListforzp(this.value);">
							<form:option value="0" htmlEscape="true"><spring:message code="Label.ZPanchyat" text=" - Select - "></spring:message></form:option>
							<c:forEach items="${districtPanchayatList}" var="distListvar">
								<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
						   			 <form:option value="${distListvar.localBodyCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
								</c:if>  
						
								<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
									<form:option value="${distListvar.localBodyCode}" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
								</c:if>
							</c:forEach>
						  </form:select> 
					    </c:if>
						
						<c:if test="${twoTierStructure == false}">
							<form:select htmlEscape="true" path="districtPanchayatName" class="form-control" id="ZilaPanchyatID" onchange="getZPWardsLists(this.value);getZPIntermediatePanchyat(this.value);">
							<form:option value="0" htmlEscape="true"><spring:message code="Label.ZPanchyat" text=" - Select - "></spring:message></form:option>
								<c:forEach items="${districtPanchayatList}" var="distListvar">
									 <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
										<form:option value="${distListvar.localBodyCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
									</c:if>  
									
									<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
										 <form:option value="${distListvar.localBodyCode}" htmlEscape="true"><c:out value="${distListvar.localBodyNameEnglish}" escapeXml="true"></c:out></form:option>
									 </c:if>
								</c:forEach>
							</form:select> 
						</c:if><br /> <br /> 
						
						<div class="errormsg"></div> <span class="errormsg" id="ddDestDistrict_error"> </span>
					</div> 						
				</div>
																
			   <div class="form-group">
                 	<label class="col-sm-3 control-label"><spring:message code="Label.SELECTWARD" text="Select Ward Name " htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
                  	  <div class="col-sm-6">
                   		<c:if test="${twoTierStructure == true}">
							<form:select htmlEscape="true" path="ward_number" class="form-control" id="WardCode" onchange="fillMappedEntities1(this.value);checkExistingGramPanchayat()"></form:select>
						</c:if>
										
						<c:if test="${twoTierStructure == false}">
							<form:select htmlEscape="true" path="ward_number" class="form-control" id="WardCode" onchange="fillMappedEntities(this.value); checkExistingGramPanchayat();"></form:select>
						</c:if><br /> <br /> 
                  	</div>
              </div>
              
              
              <c:if test="${twoTierStructure == false}">				
              <div class="form-group">
                 <label class="col-sm-3 control-label"><spring:message code="Label.SELECTBLOCKPAN"
													text="Block Panchyat" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
                  	<div class="col-sm-6">
                  		<form:select htmlEscape="true" path="intermediatePanchayat" class="form-control" id="InterPanchyatID"  onchange="getVillagePanchyatListforzp(this.value);"> <br /> <br /> </form:select>
                   	</div>
              </div>
              </c:if>
              
              
              	
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message code="Label.AVAILABLEGRAMPANCHAYT" htmlEscape="true"></spring:message></label>
							<form:select htmlEscape="true" name="select9" id="GpListAvailable" path="grampanlbid" multiple="multiple" class="form-control"></form:select>
						</div>
						
						<div class="ms_buttons col-sm-2"><br>
							<button type="button" id="btnaddSubDistrictFull" name="Submit7" onclick="addItemforZP1('GpListSelected','GpListAvailable','FULL',true)" class="btn btn-primary btn-xs btn-block">Whole  <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" id="btnremoveOneSubDistrict" name="Submit8" onclick="removesingleItemforgp('GpListSelected','GpListAvailable',true);" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-left" aria-hidden="true"></i></button>
							<button type="button" id="btnremoveAllSubDistricts" name="Submit9" onclick="removeAllsgpward('GpListSelected','GpListAvailable',true)" class="btn btn-primary btn-xs btn-block"> Full <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
							<button type="button" id="btnaddSubDistrictPart" name="Submit10" onclick="addItemforZP('GpListSelected','GpListAvailable','PART',true);" class="btn btn-primary btn-xs btn-block"> Part <i class="fa fa-angle-right" aria-hidden="true"></i></button>
						</div>
						
						<div class="ms_selection col-sm-5">
							<label><spring:message code="Label.CONTRIGRAMPANCHAYT" htmlEscape="true"></spring:message></label><br/>
								<form:select htmlEscape="true" name="select4" id="GpListSelected" multiple="multiple" path="choosenlb" class="form-control" ></form:select>
									<div class="errormsg">
									<form:errors htmlEscape="true" path="choosenlb"></form:errors>
									</div>
								<input type="button" class="btn btn-info" id="partgpVillage" name="partgpVillage" value="<spring:message  code="Button.GETVILLAGEL"></spring:message>" onclick="getVillageListsforzpwardmapping()" />				
                        </div>
                    </div>
                    
               
				<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message></label><br/>
						<form:select htmlEscape="true" name="select9" id="VillageListAvailable" path="localGovtBodyVillageList" multiple="multiple" class="form-control" ></form:select>	
					</div>
					<div class="ms_buttons col-sm-2"><br /><br />
						<button type="button" id="btnaddSubDistrictFull" name="Submit7" onclick="addItemforZP('VillageListContributing','VillageListAvailable','FULL',true)" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
						<button type="button" id="btnremoveOneSubDistrict" name="Submit8" onclick="removesingleItemforgp('GpListSelected','GpListAvailable',true);" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-left" aria-hidden="true"></i></button>
					</div>
					<div class="ms_selection col-sm-5">
					  	<label><spring:message code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message></label><br/>
						<form:select htmlEscape="true" name="select4" id="VillageListContributing" multiple="multiple" path="contVillage" class="form-control"></form:select>
							
                    </div>
                  </div>
                
		
				
				<div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                       		<c:if test="${twoTierStructure == false}">
	                        <button type="button" class="btn btn-success" onclick="checkData();" name="Submit"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
							</c:if>
							<c:if test="${twoTierStructure == true}">
							<button type="button" class="btn btn-success" onclick="checkData1();" name="Submit"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
							</c:if>
							<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>	
                        
                       </div>
                    </div>   
              	</div>
		</div>				
     </form:form>
          
         <div id='cvillage'>
			<div class="frmtxt" style="visiblity: 'hidden'; display: none;">
				<div class="frmhdtitle">
					<spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message>
						<span class="mandatory">*</span><br />
				</div>
			</div>
		</div>
		<script src="/LGD/JavaScriptServlet"></script>
	  </div>
	</div>
  </section>
 </div>
</section>	
	
</body>
</html>