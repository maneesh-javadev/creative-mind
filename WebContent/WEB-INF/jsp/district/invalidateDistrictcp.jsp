<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script src="js/invalidateDistrict.js"></script>
<script src="js/validation.js"></script>
<script src="js/common.js"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>

<script type="text/javascript" src="js/invalidateDistrict.js" charset="utf-8"></script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function checkcode(obj)
{
	//alert("java"+obj);
	}

addItemList=function(selListId, coverageListId) {
	
	$('#'+selListId+'> option:selected').appendTo('#'+coverageListId); 
	};
	
</script>
</head>
<body onload="toggledisplay('districtdelete_yes','fromothersubdistrict')"  onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
            <form:form action="enterInvalidateDistrictOrderDetails.htm" id="invalidateForm" method="POST" commandName="invalidateDistrict"  class="form-horizontal">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="enterInvalidateDistrictOrderDetails.htm"/>"/>
				      
				               
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.INVALIDATEDISTRICT"></spring:message></h3>
                                </div>
                                
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								     <form:hidden path="operation" value="I"/>
								     <form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>
								      <form:select path="districtNameEnglish"  id="ddDistrict" Class="form-control" onchange="getSubDistrictList(this.value);removeItemFromOtherDropdowns(this);" htmlEscape="true" onblur="vlidateOnblur('ddDistrict','1','15','c');">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
												<c:forEach items="${districtList}" var="districtList">
													<c:if test="${districtList.operation_state =='A'.charAt(0)}">
														<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>
														</form:option>
													</c:if>
													<c:if test="${districtList.operation_state =='F'.charAt(0)}">
														<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
														 </form:option>
													</c:if>
												</c:forEach>
											    </form:select> 
											    <form:hidden path="operation" value="I"/>
										<div class="mandatory"><form:errors htmlEscape="true"  path="districtNameEnglish" ></form:errors></div>
										<span class="mandatory" id="districtNameEnglish">  </span>	
								  </div>
						   </div>   
                  
             
                       <div class="box-header subheading">
                               <h4><spring:message htmlEscape="true" code="Label.HOWDISTRICTDELETED"></spring:message></h4>
                          </div>
						<div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-6">
									<label class="radio-inline"> <form:radiobutton id="districtdelete_yes" path="rdoDistrictDelete" htmlEscape="true" onclick="toggledisplay('districtdelete_yes','fromothersubdistrict')" value="false" /><spring:message htmlEscape="true" code="Label.YES"></spring:message></label>
									<label class="radio-inline"><form:radiobutton id="districtdelete_no" path="rdoDistrictDelete" onclick="toggledisplay('districtdelete_no','cvillage')" value="true" /><spring:message	htmlEscape="true" code="Label.NO"></spring:message></label>
								</div>
						</div>
					
				
		<div id='fromothersubdistrict'  >			
			 <div class="box-header subheading">
              <h4><spring:message htmlEscape="true"  code="Label.SELECTTARGETDISTRICT"></spring:message></h4> </div>			
						
				<div class="form-group">
				  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message><span class="mandatory">*</span></label>
				  <div class="col-sm-6">
				   <form:select path="targetDistrictYes"  id="ddDistrictMergeYes" Class="form-control" onblur="vlidateOnblur('ddDistrictMergeYes','1','15','c');">
					<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
						<c:forEach items="${districtList}" var="districtList">
								<c:if test="${districtList.operation_state =='A'.charAt(0)}">
									<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out> 
									</form:option>
								</c:if>
								<c:if test="${districtList.operation_state =='F'.charAt(0)}">
										<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
								         </form:option>
								</c:if>
					   </c:forEach>
				 </form:select>
				  
				  </div>
				</div>		
             </div>  
               
               <div id='cvillage'   style="visibility: hidden; display: none;" >
						
							 <div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.SELECTTARDISTRICT"></spring:message></h4>
							</div>													
									
			    <div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTTARGETDISTRICT"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6"> 
						<form:select path="targetDistrictNo" id="ddDistrictMergeNo" Class="form-control">
							<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
							<c:forEach items="${districtList}" var="districtList">
								<c:if test="${districtList.operation_state =='A'.charAt(0)}">
									<form:option value="${districtList.districtCode}" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out></form:option>
								</c:if>
								<c:if test="${districtList.operation_state =='F'.charAt(0)}">
				                	<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value=" ${districtList.districtNameEnglish}" escapeXml="true"></c:out> 																				
									</form:option>
								</c:if>
							</c:forEach>
						</form:select>
						
						<div class="mandatory"><form:errors htmlEscape="true"  path="targetDistrictNo" ></form:errors></div>
					</div>
				</div>
										
			<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true"  code="Label.SELECTSUBDISTRICT"></spring:message> </label>
		              <form:select name="select9"  htmlEscape="true" id="ddSubdistrict" path="subDistrictList" multiple="multiple" class="form-control" onclick="checkcode(this.value);">
														<form:option value="">
														</form:option>
						</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnaddVillageFull"   value="" onclick="addItem('contributedSubDistricts','ddSubdistrict','',false);" ><spring:message htmlEscape="true"  code="Button.SELECTSUBDISTRICTS"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneVillage"  value=" &lt; "  onclick="addItemList('contributedSubDistricts','ddSubdistrict');"><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;"  onclick="removeAll('contributedSubDistricts','ddSubdistrict',false)" ><i class="fa fa-angle-double-left" aria-hidden="true"></i>  <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRIBUTBLOCKLIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			    <form:select name="select4" htmlEscape="true" id="contributedSubDistricts" multiple="multiple" path="contributedSubDistricts" class="form-control"  onclick="checkcode(this.value)"></form:select>
				<div class="mandatory"> <form:errors path="contributedVillages" htmlEscape="true"></form:errors> </div>	
				
				<button type="button" id="chooseMoreBtn" onclick='validateSelectAddMore();'  class="btn btn-primary"  ><spring:message htmlEscape="true"  code="Button.CHOOSEMOREDISTRICT"></spring:message></button>
		     </div>				
            </div>
         </div>							
		
	<div class="form-group">								
		<div class="col-sm-12">								
			  <table id="dynamicTbl" class="table table-bordered table-hover" style="visibility: hidden;display:none;" >
				<tr>
					<th><spring:message code="Label.DISTRICTNAME" htmlEscape="true"></spring:message></th>
					 <th><spring:message code="Label.SUBDISTRICTNAME" htmlEscape="true"></spring:message></th>
				</tr>
			 </table>	
		  </div>	
		 </div>										
    </div>
     
    <div class="box-footer">
       <div class="col-sm-offset-2 col-sm-10">
             <div class="pull-right">
             <input type="hidden" name="listformat" id="listformat" value="@" />
             <input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn btn-success" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
             <input type="button" name="Submit6" class="btn btn-danger" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
              
             </div>
       </div>   
      </div> 
       
     </div>   
     </div>
             
    </form:form>      
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
	
</body>
</html>