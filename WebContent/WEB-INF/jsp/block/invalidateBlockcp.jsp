<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="js/invalidateBlock.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>	
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type="text/javascript" language="javascript">
function loadPage()
{
	toggledisplay('blockdelete_yes','fromothersubdistrict');
	}

if ( window.addEventListener ) { 
    window.addEventListener( "load",loadPage, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", loadPage );
 } else 
       if ( window.onLoad ) {
          window.onload = loadPage;
 }



$(document).ready(function() {
   var s = document.getElementById("flag2").value;  
   if(s>0)
   getBlockList(s);
});
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
</head>
<body >
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
            <form:form action="invalidateblockPost.htm" id="invalidateForm" method="POST" commandName="invalidateBlock"  class="form-horizontal">
			<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>" />
			<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}' escapeXml='true'></c:out>" />
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateblockPost.htm"/>"/>
				      
				               
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.INVALIDATEBLOCK"></spring:message></h3>
                                </div>
                                
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								     <form:select path="districtCode"  id="ddDistrict" Class="form-control" htmlEscape="true"  onchange="getBlockList(this.value);">
								     <c:if test="${flag1 eq 0}">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
									</c:if>
												<c:forEach items="${districtList}" var="districtList">
														<c:if test="${districtList.operation_state =='A'.charAt(0)}">
															<form:option value="${districtList.districtCode}" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
															</form:option>
															</c:if>
														<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																		
																</form:option>
															</c:if>
														</c:forEach>
											    </form:select> 
											    <div class="mandatory"><form:errors htmlEscape="true"  path="districtCode" ></form:errors></div>
										      <span class="mandatory" id="ddSrcDistrict_error">  </span>
								  </div>
						   </div>   
                  
                  
                  <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SOURCEBLOCK"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								    <form:select path="blockList" id="ddSrcBlock" Class="form-control" onchange="getBlockVillages(this.value);getselectedblockUlbData(this.value)" htmlEscape="true">
											    </form:select>
								       <div class="mandatory"><form:errors htmlEscape="true"  path="blockList" ></form:errors></div>
										<span class="mandatory" id="ddSrcBlock_error"> </span>										
								  </div>
						   </div>   
                  
             
                       <div class="box-header subheading">
                               <h4><spring:message htmlEscape="true" code="Label.HOWBLOCKDELETED"></spring:message></h4>
                          </div>
						<div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-6">
									<label class="radio-inline"> <form:radiobutton id="blockdelete_yes" path="rdoBlockDelete" onclick="toggledisplay('blockdelete_yes','fromothersubdistrict')" value="false" htmlEscape="true" /><spring:message htmlEscape="true" code="Label.YES"></spring:message></label>
									<label class="radio-inline"><form:radiobutton id="blockdelete_no" path="rdoBlockDelete" onclick="toggledisplay('blockdelete_no','cvillage')" value="true" htmlEscape="true" /><spring:message	htmlEscape="true" code="Label.NO"></spring:message></label>
								</div>
						</div>
					
					
			 	
				
				
				<div id='fromothersubdistrict'>	
				<div class="box-header subheading">
                  <h4><spring:message htmlEscape="true" code="Label.SELECTTARGETBLOCK"></spring:message></h4> </div>			
				<div class="form-group">
				  <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.TARGETDISTRICT"></spring:message><span class="mandatory">*</span></label>
				  <div class="col-sm-6">
				   <form:select path="targetDistrictCodeYes"  id="ddTargetDistrictYes" Class="form-control" onchange="getTargetBlockListYes(this.value);" htmlEscape="true">
								<c:if test="${flag1 eq 0}">
												<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></form:option>
									</c:if>
								
										
										
										<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																		
																</form:option>
															</c:if>
										</c:forEach>
				</form:select>
				   <div class="mandatory"> <form:errors htmlEscape="true" path="targetDistrictCodeYes"></form:errors> </div> 
				   <span class="mandatory" id="ddDestDistrictYes_error"> </span>
				  </div>
				</div>		
               
               
               	<div class="form-group">
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTTARGETBLOCK"></spring:message><span class="mandatory">*</span></label>
					  <div class="col-sm-6">
						   <form:select path="blockYes" id="ddTargetBlockListYes" Class="form-control" htmlEscape="true">
								</form:select>
						   <div class="mandatory"> <form:errors htmlEscape="true" path="blockYes"></form:errors> </div> 
							<span class="mandatory" id="ddDestBlockYes_error"> </span>
					  </div>
				</div>	
				
				
				
				</div>
				
               
               <div id='cvillage'>
						
							 <div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.TARGTBLOCK"></spring:message></h4>
							</div>													
									
			    <div class="form-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.TARGETDISTRICT"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6"> 
					  <form:select path="targetDistrictCodeNo"  id="ddTargetDistrictNo" Class="form-control" htmlEscape="true" onchange="getTargetBlockListNo(this.value);">
						<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
							<c:forEach items="${districtList}" var="districtList">
							<c:if test="${districtList.operation_state =='A'.charAt(0)}">
							  <form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
							 </form:option>
							 </c:if>
							<c:if test="${districtList.operation_state =='F'.charAt(0)}">
								<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																			
								</form:option>
							</c:if>
						</c:forEach>
					 </form:select> 
						
					<div class="mandatory"><form:errors htmlEscape="true"  path="targetDistrictCodeNo" ></form:errors></div>
					<span class="mandatory" id="ddDestDistrictNo_error">
				 </div>
				</div>
				
				
				
				<div class="form-group">  
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECTTARGETBLOCK"></spring:message><span id="required" class="mandatory">*</span></label>
					<div class="col-sm-6"> 
						   <form:select path="blockNo"  id="ddTargetBlockListNo" Class="form-control" htmlEscape="true"> </form:select>
							<div class="mandatory"><form:errors htmlEscape="true"  path="blockNo" ></form:errors></div>
							<span class="mandatory" id="ddDestBlockNo_error"> </span>
												
					</div>
				</div>	
									
			<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true"  code="Label.VOB"></spring:message> </label>
		              <form:select name="select9"  id="ddVillage" path="villageList" multiple="multiple" class="form-control"  htmlEscape="true"></form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnaddVillageFull"  name="Submit4" class="bttn" value="<spring:message htmlEscape="true" code="Button.SELECTVILLAGES"></spring:message>" onclick="addItem('contributedVillages','ddVillage','',false);"  ><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="removeBockListItem('contributedVillages','ddVillage',false)" ><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" onclick="removeBockListItem('contributedVillages','ddVillage',false)"  ><i class="fa fa-angle-double-left" aria-hidden="true"></i>  <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRIBUTBLOCKLIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			    <form:select name="select4" id="contributedVillages" htmlEscape="true"  multiple="multiple" path="contributedVillages" class="form-control"></form:select>
												<div class="mandatory">
													<form:errors path="contributedVillages" htmlEscape="true"></form:errors>
												</div>
		     </div>				
            </div>
         </div>		
         
         
         <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true"  code="Label.ULBOB" text="ULB of Block"></spring:message> </label>
		             <form:select name="select9"  id="avaBlockUlb" path="UlbLists" multiple="multiple" class="form-control" htmlEscape="true"></form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnaddVillageFull" name="Submit4" class="bttn" value="<spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message>" onclick="addItem('contributedBlockUlb','avaBlockUlb','',false);"  ><i class="fa fa-angle-double-right" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="removeBockListItem('contributedBlockUlb','avaBlockUlb',false)"  ><i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" onclick="removeBockListItem('contributedBlockUlb','avaBlockUlb',false)"  ><i class="fa fa-angle-double-left" aria-hidden="true"></i>  <i class="fa fa-angle-double-left" aria-hidden="true"></i> </button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.ULBOBSEL" text="Selected ULB of Block"></spring:message><span class="mandatory">*</span></label> 
			    <form:select name="select4" id="contributedBlockUlb"  htmlEscape="true" multiple="multiple" path="contributedblockUlb" class="form-control"></form:select>
						<div class="mandatory"> <form:errors path="contributedVillages"></form:errors></div>
					  <button type="button" id="chooseMoreBtn" onclick='validateSelectAddMore();'  class="btn btn-primary" value="" ><spring:message htmlEscape="true"  code="Button.CHOOSEMOREBLOCK"></spring:message></button>
		     </div>				
            </div>
         </div>						
		
	<div class="form-group">								
		<div class="col-sm-12">								
			  <table id="dynamicTbl" class="table table-bordered table-hover" style="visibility: hidden;display:none;" >
				<tr>
					<th>Block Name</th>
					 <th>Merging Entities</th>
				</tr>
			 </table>	
		  </div>	
		 </div>										
    </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                 <input type="hidden" name="listformat" id="listformat" /> 
				 <input type="hidden" name="ulbListFormat" id="ulbListFormat" /> 
				  <input type="button" onclick="javascript:validateSelectAndSubmit();" name="Submit" class="btn btn-success" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
				 <input type="button" name="Submit6" class="btn btn-danger" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                 
                 </div>
           </div>   
       </div> 
     </div>   
     </div>     
    </form:form>      
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
	<c:if test="${flag1 eq 1}">
		<script>
		var districtCode=parseInt('${flag2}');
		getBlockList(districtCode);
		getTargetBlockListYes(districtCode);
		</script>
</c:if>
</body>
</html>