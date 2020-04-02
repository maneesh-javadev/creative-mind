<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title> <spring:message htmlEscape="true"  code="Label.INVALIDATEULB"></spring:message>
</title>
<script src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script src="js/local_body.js"></script>
<script src="js/lgd_localbody.js"></script>
<script src="js/localbody_validation.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript" src="js/invalidatetrilocalbody.js"
	charset="utf-8"></script>
	
	<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'>
	dwr.engine.setActiveReverseAjax(true);</script>	
	<script>
	var lbType='${localGovtBodyForm.lbType}';
	
	getEffectiveDate=function(lbcode){
		if(lbType="U" && lbcode>0){
			lgdDwrlocalBodyService.getExistingLBEffectiveDate(lbcode, {
				callback : function(result) {
					js_previous_ED=result;
					$("#existGO").show();
					var momentDate = moment(js_previous_ED, 'DD-MM-YYYY').toDate();
					
					var iParamEffectiveDate=showDateFormat(momentDate);	 
					$("#iParamEffectiveDate").val(iParamEffectiveDate);
					$("#divParamEffectiveDate").text(iParamEffectiveDate);
					setDefaultDates(js_previous_ED);
					
				},
				async : true
			});		 
		}
		
		
	};
	
	showDateFormat=function(momentDate){
		var dd = momentDate.getDate();

		var mm = momentDate.getMonth()+1; 
		var yyyy = momentDate.getFullYear();
		if(dd<10) 
		{
		    dd='0'+dd;
		} 

		if(mm<10) 
		{
		    mm='0'+mm;
		} 
		return (dd+'-'+mm+'-'+yyyy);
	};
	
	var callActionUrl = function (url) {
		$( 'form[id=invalidateLocalBodyforURB]' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
		$( 'form[id=invalidateLocalBodyforURB]' ).attr('method','post');
		$( 'form[id=invalidateLocalBodyforURB]' ).submit();
	};
	</script>
</head>
<body  onkeypress="disableCtrlKeyCombination(event);"  onkeydown="disableCtrlKeyCombination(event);">
	
	<section class="content">
     <div class="row">
        <section class="col-lg-12">
            <form:form action="invalidateURBFinal.htm" id="invalidateLocalBodyforURB"	commandName="localGovtBodyForm" method="POST"	enctype="multipart/form-data" class="form-horizontal">
			      <input type="hidden" name="<csrf:token-name/>"		value="<csrf:token-value uri="invalidateURBFinal.htm"/>" />
				   <input type="hidden" name="flag1" id="flag1" value="0"  />
				   <input type="hidden" name="flag2" id="flag2" value="<c:out value='${scode}' escapeXml='true'></c:out>"/>
				    <form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>
				               
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message></h3>
                                </div>
                                
                        <div class="box-body">
                            <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
								     <form:select path="lgd_LBTypeName" id="ddLgdLBType" onchange="HideShow(this.value);" Class="form-control">
											<form:option value="0" htmlEscape="true">
											<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message>
											</form:option>
						                      <c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
											<form:option id="typeCode" htmlEscape="true" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.nomenclatureEnglish}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
											</c:forEach>
									</form:select>
									<div class="mandatory" id="ddLgdLBType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
									<div class="mandatory" id="ddLgdLBType_error2" style="display: none" ></div>		
								  </div>
						   </div>   
                  
                  
                        <div id="divLgdlistSubTypeCode" class="form-group" style="visibility: hidden; display: none;">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILSUBTYPE"></spring:message></label> 
							 <div class="col-sm-6">
								 <form:select	path="localbodySubtype" Class="form-control" id="ddlgdLBSubTypeCode" htmlEscape="true">
									</form:select>  
							 </div>					
												
						</div>
						
						
						<div id="divLgdSelIntermediateP"  class="from-group" style="visibility: hidden; display: none;">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>
									<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out></label><span class="mandatory">*</span>
							<div class="col-sm-6">
							 <form:select path="lgd_LBDistrictAtInter"  id="ddlgdLBDistAtInter" Class="form-control" onchange="getlistofIntermediatePanchayat(this.value)">
										<form:option value="0" htmlEscape="true">
														<spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message>
										</form:option>
											<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true" />
										</form:select> 
								<div id="ddlgdLBDistAtInter_error" class="error"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
								<div class="mandatory" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true"/></div>  
								<div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none" ></div>
													
							</div>	
						</div>						
               
                 <div id="divLgdVillageP" style="visibility: hidden; display: none;">																					
												
					<div class="from-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message><c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out><span class="mandatory"  >*</span></label>
						<div class="col-sm-6"> 
						<form:select	path="lgd_LBDistrictAtVillage" class="form-control" id="ddlgdLBDistrictAtVillage" htmlEscape="true" onchange="getWorkOnVillagePanchayatforDistrictP(this.value);" >
								<form:option value="0" htmlEscape="true"> <spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message> </form:option>
								<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" htmlEscape="true"/>
						</form:select> 
						<div id="ddlgdLBDistrictAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
						<div class="mandatory" id="ddlgdLBDistrictAtVillage_error1"><form:errors path="lgd_LBDistrictAtVillage" htmlEscape="true"/></div>  
						<div class="mandatory" id="ddlgdLBDistrictAtVillage_error2" style="display: none" ></div>																								
                           </div>
					</div>

				<div class="from-group">
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message><c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out><span class="mandatory">*</span></label>
					<div class="col-sm-6">
						<form:select path="lgd_LBIntermediateAtVillage"  htmlEscape="true" id="ddlgdLBInterAtVillage" Class="form-control"
																onchange="getlistofgp(this.value);">
								<form:option value="0" htmlEscape="true">
										<spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message>
								</form:option>
							</form:select> 
															
					<div id="ddlgdLBInterAtVillage_error" class="error"><spring:message code="error.SOURCESELECTVILLAGEPARENT" htmlEscape="true"></spring:message></div>
					<div class="mandatory" id="ddlgdLBInterAtVillage_error1"><form:errors path="lgd_LBIntermediateAtVillage" htmlEscape="true"/></div>  
					<div class="mandatory" id="ddlgdLBInterAtVillage_error2" style="display: none" ></div>																										
		        </div>
						
		  </div>																								
	</div>
           
           
           	<div id="Districtlocalbody"  style="visibility: hidden; display: none;">
				  <div class="box-header subheading">
						<h4><spring:message htmlEscape="true" code="Label.SELECTLBD"></spring:message></h4>
					</div>
					<div class="from-group">
						<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out><span class="mandatory">*</span></label>
                     <div class="col-sm-6">
							<form:select path="districtpanlbid" id="lblist1" Class="form-control" onchange="getEffectiveDate(this.value);setflag1();">
								 								
							</form:select>
							<div class="mandatory" id="ddlgdLBDistAtInter_error1"> <form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" /> </div>
								<div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
						</div>	
					</div>	
				</div>    
               
               
               
               
               <div id="Muncipiality" style="visibility: hidden; display: none;">
					<div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.SelectMunicipa"></spring:message></h4></div>
				<div class="form-group">	
					<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message> <c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out><span class="mandatory">*</span></label> 
					<div class="col-sm-6">					
							<form:select path="intermediatepanlbid" class="form-control" id="lblist2"  onchange="setflag2()">
															ddSourceLocalBody id name changed 
								<form:option value="0" htmlEscape="true"> <spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message> </form:option>
								<form:options items="${districtPanchayatList2}" htmlEscape="true" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" /> 
						  </form:select> 
						 <div id="ddlgdLBDistAtInter_error" class="error"> <spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message> </div>
						 <div class="mandatory" id="ddlgdLBDistAtInter_error1"><form:errors path="lgd_LBDistrictAtInter" htmlEscape="true" /></div>
						 <div class="mandatory" id="ddlgdLBDistAtInter_error2" style="display: none"></div>
					  </div>	
					</div>
				</div>
               <br/> <br/> <br/>
               
               	<div style="display: none;padding-left: 30px;" id="existGO"><%@include file="InvalidateURBLBGovermentOrder.jsp"%>	</div>
             
             
             
             
     </div>                     
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                <input type="hidden" name="listformat"	id="listformat" value="@" /> 
                   <button type="button" class="btn btn-success " name="Submit" onclick="validateAndSubmit(); "  value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" ><i class="fa fa-floppy-o"></i> Submit</button>
                   <button type="button" name="Submit2" class="btn btn-warning"	 onclick="javascript:location.href='invalidateturbanlocalbody.htm?<csrf:token uri="invalidateturbanlocalbody.htm"/>'" ><spring:message htmlEscape="true"  code="Button.CLEAR"> </spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
       
     
     </div>
             
    </form:form>      
   </section>
<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>

