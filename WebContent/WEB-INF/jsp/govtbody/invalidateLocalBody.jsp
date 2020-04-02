<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message>
	</title> <script src="js/shiftdistrict.js"></script>
	 <script src="js/govtorder.js"></script>
	<script src="js/common.js"></script>
	<script src="js/local_body.js"></script>
	<script type="text/javascript" src="js/GovtLocalBody.js"></script>
	
<link href="css/error.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
		<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
		
		

	<script src="js/dynCalendar.js" type="text/javascript"></script>
	<script src="js/browserSniffer.js" type="text/javascript"></script>
	<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);


function selectallLocalBody()
{

	var selObj=document.getElementById('ddDestLocalGovtBody');	
    var villageCode="";
	for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     villageCode+=selObj.options[i].value+",";
		     
	}
	//alert("villageCode-----"+villageCode);
	
	getCoveredLandRegion(villageCode);
	
}



function selectallSubDistLocalBody()
{
	// alert("Alert in subdistrict");
	var selObj=document.getElementById('ddSubDistDestLocalGovtBody');	
    var subDistrictCode="";
	for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrictCode+=selObj.options[i].value+",";
		     
	}
	getCoveredLandForSubDistRegion(subDistrictCode);	
}

function selectallDistrictName()
{

	var selObj=document.getElementById('ddDestDistLocalGovtBody');	
    var districtList="";
	for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     districtList +=selObj.options[i].value+",";
		     
	}
	getCoveredLandRegionforDistrict(districtList);
	
}



function callSaveDraft()
{
  // it will we used at a time of webservices
}











	
</script>

</head>

<body onload="load_page(); load_pageCheck();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<div id="frmcontent">
	<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.INVALIDATELB"></spring:message>
					</td>
					<%-- //these links are not working<td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Button.HELP"></spring:message></a> --%>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="invalidateLBPost.htm" commandName="localGovtBodyForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateLBPost.htm"/>"/>
			<div class="frmpnlbg">
							  <div class="frmtxt">								  
								  <table width="100%" class="tbl_no_brdr">
                                    
                                    <tr>
                                      <td class="tblclear">
									  	<div id="tab_panel">
											<ul>
											<li><a href="#" class="current" onclick="tabdisplay(id);" id="tab1_header"><spring:message htmlEscape="true"  code="Label.GOVTORDERDETAILS"></spring:message></a></li>
												<li><a href="#" onclick="tabdisplay(id);" id="tab2_header"><spring:message htmlEscape="true"  code="Label.INVALIDATELB"> </spring:message></a></li>
												
											</ul>
											<div class="clear"></div>
										</div>
										<div class="clear"></div>
										<div class="greybrdr">
									<!-- div tab 1 -->
										  
										  <div class="clear"></div>
			<div id="tab2" >
		        
				<div class="frmpnlbg">
					<div class="frmtxt">				   
					 
						  <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.INVALIDATELB"></spring:message></div>
						    <table width="100%" class="tbl_no_brdr">
						      <tr>
                                <td width="14%" rowspan="8">&nbsp;</td>
                                <td width="86%">
                                <spring:message htmlEscape="true"  code="Label.LBSOURCE"></spring:message><span id ="required" class="errormsg"></span><span class="errormsg">*</span><br />
                                <form:select path="lgTypeName">
                                <form:option value="0">Please Select</form:option>
                                <form:options items="${listlbt}"
									itemLabel="localbodyName"
									itemValue="localbodyTypeCode" />
								</form:select>
						    	<span class="msg" id="localBodyNameInEn"><spring:message htmlEscape="true"  code="Msg.LOCALBODYNAMEEN"></spring:message> </span>
						    	</td>
						    	
                             </tr>
            </table>
		 </div>
	</div>
					  
<%-- 				<div class="frmpnlbg">
				   <div class="frmtxt">
					   <div  class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message></div>
						   <table width="100%" class="tbl_no_brdr">
                              <tr>
                                <td width="12%" rowspan="2">&nbsp;</td>
                                <td width="88%">
                                <table width="100%" class="tbl_no_brdr">
                                 <tr>
                                    <td width="159"><spring:message htmlEscape="true"  code="Label.GISNODES"></spring:message></td>
                                    <td width="155" align="right"><label id="lbllatitude"><spring:message htmlEscape="true"  code="Label.LATITUDE"></spring:message></label></td>
                                    <td width="75"><form:input path="latitude" id="lbllatitude" type="text" class="frmfield"  /></td>
                                    <td width="150" align="right"><label id="lbllongitude"><spring:message htmlEscape="true"  code="Label.LONGITUDE"></spring:message></label></td>
                                    <td width="75"><form:input path="longitude" type="text" class="frmfield"  /></td>
                                    <td >&nbsp;</td>
                                    <td width="150" align="right">
                                    <label>
                                               <input type="button" name="Submit3" value="<spring:message htmlEscape="true"  code="Button.ADDNODES"></spring:message>" onclick="addgisnodes()" />
                                    </label></td>
                                  </tr>
                                  </table>
                                  
                                  <table>
                                  <tr>
												<td width="1"></td>
												<td width="50" align="center"></td>
												<td width="50"></td>
												<td width="50" align="center"></td>
												<td width="45"></td>
												<td><div id="addgisnodes"></div></td>
								 </tr>
                               </table>
                              
							   <div class="errormsg"></div>								
							 </td>
                           </tr>
                              <tr>
                                <td>
                                
                                <table width="468" class="tbl_no_brdr">
                                <tr>
																<td><spring:message htmlEscape="true"  code="Label.MAPOFLOCALBODY"></spring:message><br />
																	<form:input id="newLocalBodyMap" path="filePathMapUpLoad"
																		class="frmfield" type="file" size="25"/> 
																		<span><form:errors htmlEscape="true"  path="filePathMapUpLoad" class="errormsg"></form:errors></span>
																</td>
															</tr>
                                
                                                                                                                                 	                                
                                  
                               
                                   </table>
                                
                                   <div class="errormsg"></div>
							   </td>
                            </tr>
                          </table>
						</div>		
											
					</div> --%>
			
<%-- 		 <div id='district' class="frmpnlbg">
			<div class="frmtxt" >
			   <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.SELECTEDTYPELOCALBODY"></spring:message></div>
					<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%" rowspan="8">&nbsp;</td>
								<td width="86%"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODYTYPE"></spring:message><span class="errormsg">*</span><br /> 
									<form:select path="lgTypeName" id="tierSetupCode" name="tierSetupCode" class="frmsfield" style="width: 175px" onchange="refreshdropdowns(); hideShowDistIV(this.value); setandgetUnmappedLocalBodyList(this.value); ">
									<form:option value=""><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></form:option>
                                    <c:forEach var="lgtLocalBodyType" varStatus="var" items="${lgtLocalBodyType}">                                 
                                 <form:option id="typeCode" value="${lgtLocalBodyType.localBodyTypeCode}:${lgtLocalBodyType.level}">${lgtLocalBodyType.nomenclatureEnglish}</form:option>
                                 </c:forEach>
                                  </form:select>&nbsp;<span><form:errors htmlEscape="true"  path="lgTypeName" class="errormsg"></form:errors></span>
                                  &nbsp;&nbsp;<span class="errormsg" id="tierSetupCode_error">
                                  <spring:message htmlEscape="true"  code="error.SOURCESELECTLOCALBODYTYPE"></spring:message></span>
                                  <br/><br/>
                                 
                                  
                                  <div id="tr_district" style="visibility: hidden; display:none;">
                                  <table>
                                  <tr>
                                  		<td><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message> ${localGovtBodyForm.districtPanchayatName}&nbsp;<spring:message htmlEscape="true"  code="Label.OFNEWLOCALBODY"></spring:message><br/><br/>
								   <form:select path="localBodyNameEnglishList" class="frmsfield" id="ddSourceLocalBody" style="width: 175px" onchange="getandsetLocalBodyList(this.value);setandGetSubDistList1(this.value);"> 														   
									   <form:option value="-1"><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></form:option>
											<form:options items="${localGovtBody}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />											
								  </form:select> &nbsp;<span><form:errors htmlEscape="true"  path="localBodyNameEnglishList" class="errormsg"></form:errors></span>
								  &nbsp;&nbsp;<span class="errormsg" id="ddSourceLocalBody_error">
								  <spring:message htmlEscape="true"  code="error.SOURCESELECTLOCALBODYPARENT"></spring:message></span>								
								  <br/><br/>
									</td>
								  <td>&nbsp;</td>
								  <td>&nbsp;</td>	
                                  </tr>
                                  </table>
                                  </div>
                               <div id="tr_intermediate" style="visibility: hidden; display:none;">
                                  <table>
                                  <tr>
										<td><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp;${localGovtBodyForm.intermediatePanchayat}<br/>
													   <form:select path="localBodyNameEnglishListSource" class="frmsfield" id="localGovtBodyListMain" style="width: 175px" onchange="setandgetLocalBodyVillageList(this.value)"> 														   
																<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></form:option>
																<form:options items="${localBodyforSubDistList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
													   </form:select>
													   &nbsp;<span><form:errors htmlEscape="true"  path="localBodyNameEnglishListSource" class="errormsg"></form:errors></span>
													   &nbsp;&nbsp;<span class="errormsg" id="localGovtBodyListMain_error">	
													   <spring:message htmlEscape="true"  code="error.SOURCESELECTVILLAGEPARENT"></spring:message></span>
													   <br/><br/>
									   </td>
											 <td>&nbsp;</td>
											 <td>&nbsp;</td>					
								</tr>
                              </table>
                           </div>
							</td>
						  </tr>
						  
					 <tr>
						<td><br /> <label><spring:message htmlEscape="true"  code="Label.PESAACT"></spring:message>&nbsp;&nbsp;&nbsp;
									   <form:radiobutton path="pesaAct" value="Yes" />
									   <spring:message htmlEscape="true"  code="Label.PESAACTYES"></spring:message>&nbsp;&nbsp;
	                                       <form:radiobutton path="pesaAct" value="No" checked="true"/><spring:message htmlEscape="true"  code="Label.PESAACTNO"/>
									</label><br/></td>
				 </tr>
								  
							  		
	   </table>
				     </div>
				 </div> --%>
					
<%-- 		<div id='district' class="frmpnlbg">
		   <div class="frmtxt" >
			  <div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></div>
					<table class="tbl_no_brdr">
							<tr>
       							<td width="14%" rowspan="6">&nbsp;</td>
								<td width="86%">
							</td>
     					</tr>
			<tr>
			       <td></td>
			</tr>
							   <tr><br/></tr>
			
			 
			
			 
			 
		<tr> 
				<td>
				    <table class="tbl_no_brdr">
				    		
							<tr><td>&nbsp;&nbsp;<span class="errormsg" id="chkculb_error">
													   <spring:message htmlEscape="true"  code="error.EXISTINGLOCALBODY"></spring:message></span></td><td></td></tr>
							<tr>
							   <td><strong><form:checkbox  name="checkbox" value="true" id='chkculb' path="createFromCoverageOfUrbanLocalGovtBody" onclick="getHideLocalBodyParentList(document.getElementById('tierSetupCode').value, this.checked)" />	</strong>
											<strong></strong><spring:message htmlEscape="true"  code="Label.SELEXISTINGCOVEREDAREA"></spring:message>
											
											<br/><br/>
							   
									<!-- For District Label Local Govt Body  -->
									
									
				        <div id="tr_district_List" style="visibility: hidden; display:none;">
									<table>
									 <tr>
						        <td><strong>
						          ${localGovtBodyForm.districtPanchayatName} &nbsp;&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
						        </strong></td>
						        <td>&nbsp;&nbsp;</td>
						        <td>&nbsp;&nbsp;&nbsp;<strong><spring:message htmlEscape="true"  code="Label.CONTRIBUTELCLBDYLIST"></spring:message></strong></td>
						      </tr>
									<tr>
   								
								   <td width="235" rowspan="4">
									   <form:select path="localGovtBodyNameEnglishCovered" class="frmsfield" id="ddSourceLocalGovtBody" style="height: 100px; width: 233px"
												   multiple="true" items="${localGovtBodyDist}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode">
												   <form:options items="${localGovtBodyDist}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode"/>												   										
									  </form:select><br/><br/>
								  </td>
								  
								  <td width="100" align="center"><input type="button"
												value=" Whole &gt;&gt;" style="width: 80px"
												onclick="addItem('ddDestDistLocalGovtBody','ddSourceLocalGovtBody','FULL',true);" />
                                 </td>
                                 
                                 <td  rowspan="4">&nbsp;&nbsp;<span class="errormsg" id="ddDestDistLocalGovtBody_error"><spring:message htmlEscape="true"  code="error.DESTDISTLOCALBODY"></spring:message></span>&nbsp;<span><form:errors htmlEscape="true"  path="localGovtBodyNameEnglishDestDist" class="errormsg"></form:errors></span>
									 <form:select name="select6" id="ddDestDistLocalGovtBody" size="1"
										  multiple="multiple" 
										 path="localGovtBodyNameEnglishDestDist" 
										 class="frmsfield" style="height: 100px; width: 242px" >
									 <form:options items="${localGovtBodyNameList}" itemLabel="localBodyNameEnglish" itemValue="aliasEnglish"/>
									 </form:select><br/>
											 &nbsp;&nbsp;&nbsp; <input type="button" value="<spring:message htmlEscape="true"  code="Label.GETLANDREGIONDIST"/>" style="width: 200px" onclick="selectallDistrictName();"/>											
											</td>
									</tr>
										<tr>
											<td align="center"><label> <input type="button"	id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItem('ddDestDistLocalGovtBody','ddSourceLocalGovtBody',true)" /> </label>
											</td>
										</tr>
									
									<tr > 
									       <td align="center"><input type="button" value=" Reset &lt;&lt;" style="width: 70px" onClick="removeAll('ddDestDistLocalGovtBody', 'ddSourceLocalGovtBody', true)" /></td>
								   </tr>
								   
								   <tr >
										   <td align="center"><input type="button" value="Part &gt;&gt;" style="width: 70px" onClick="addItem('ddDestDistLocalGovtBody','ddSourceLocalGovtBody', 'PART',true);"/></td>
			
								  </tr>
								  
							      </table>
									
					</div>		<!--End here District Label Local Govt Body  -->
					<!--Start here SubDistrict Label Local Govt Body  localGovtBodyNameSubDistList-->
					<div id="tr_intermediate_List" style="visibility: hidden; display:none;">
									<table>
									
												<tr >
													<td><strong><spring:message htmlEscape="true"  code="Label.SELECT">&nbsp;&nbsp; ${localGovtBodyForm.districtPanchayatName}</spring:message></strong><br/>
													    <form:select path="localBodyNameEnglishListForSubDist" class="frmsfield" id="localDistGovtBodyListMain" onchange="getLocalSubDistBodyList(this.value);"> 														   
															 <form:option value=""><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></form:option>
																<form:options items="${localGovtBody}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
														</form:select><br/><br/></td>
																	  <td>&nbsp;</td><td>&nbsp;</td>					
																	
											 </tr>
												<tr>
						        <td><strong>
						          <spring:message htmlEscape="true"  code="Label.SELECT">&nbsp;&nbsp; ${localGovtBodyForm.intermediatePanchayat}</spring:message>
						        </strong></td>
						        <td>&nbsp;</td>
						        <td>&nbsp;<strong><spring:message htmlEscape="true"  code="Label.CONTRIBUTELCLBDYLIST"></spring:message></strong></td>
						      </tr>		
						                   <tr >
													<td width="235" rowspan="4">
														<form:select path="localBodyNameEnglishSubDistListSource" 
															class="frmsfield" 
															id="localSubDistGovtBodyListMain" 
															size="1" 
															multiple="multiple" 
															style="height: 100px; width: 242px">
															<form:options items="${localBodyforSubDist}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode"/>	
														</form:select>
															<br/><br/>														
													</td>
													
													<td width="100" align="center">
														<input type="button" value=" Whole &gt;&gt;" style="width: 80px" onclick="addItem('ddSubDistDestLocalGovtBody','localSubDistGovtBodyListMain','FULL',true);" /></td>
															<td width="235" rowspan="4">&nbsp;<span><form:errors htmlEscape="true"  path="localGovtBodyNameEnglishSubDist" class="errormsg"></form:errors></span>
															   	<form:select name="select7" id="ddSubDistDestLocalGovtBody" size="1" multiple="multiple" path="localGovtBodyNameEnglishSubDist" class="frmsfield" style="height: 100px; width: 242px" >
															   	 <form:options items="${localGovtBodyNameSubDistList}" itemLabel="localBodyNameEnglish" itemValue="aliasEnglish"/>
															   	</form:select><br/>
																	&nbsp;<input type="button" value="<spring:message htmlEscape="true"  code="Label.GETLANDREGION"/>" style="width: 200px" onclick="selectallSubDistLocalBody();"/>											
														   </td>
											</tr>
											<tr >
													<td align="center"><input type="button" value=" Back &lt;" style="width: 70px" onClick="removeItem('ddSubDistDestLocalGovtBody', 'localSubDistGovtBodyListMain', true)" /></td>
											</tr>
											<tr >
													<td align="center"><input type="button" value=" Reset &lt;&lt;" style="width: 70px" onClick="removeAll('ddSubDistDestLocalGovtBody', 'localSubDistGovtBodyListMain', true)" /></td>
											</tr>
											
											<tr > 
												    <td align="center"><input type="button" value="Part &gt;&gt;" style="width: 70px" onClick="addItem('ddSubDistDestLocalGovtBody','localSubDistGovtBodyListMain', 'PART',true);"/></td>
						                    </tr>
																	
											<tr><td></td>
													<td>&nbsp;</td>	<td align="center">&nbsp;</td>
						                   </tr>	
																	
										  <tr >
													<td><br/><br/></td><td>&nbsp;</td><td>&nbsp;</td>					
										 </tr>	
										
										<!--End here SubDistrict Label Local Govt Body  -->
									</table>
					</div>
					<div  id="tr_village" style="visibility: hidden; display:none;">
									<table>
									
									<!-- Get Village Label Body -->
											<tr>
												<td><strong><spring:message htmlEscape="true"  code="Label.SELECT">&nbsp;&nbsp; ${localGovtBodyForm.districtPanchayatName}</spring:message></strong><br/>
												<form:select path="localBodyDistListforVillage" 
															class="frmsfield" id="ddSourceLocalBodySubDistrict" 
															   onchange="getLocalBodyListAtVillageLvl(this.value);"> 														   
															   <form:option value=""><spring:message htmlEscape="true"  code="Label.SELECTLOCALBODY"></spring:message></form:option>
																<form:options items="${localGovtBody}"
																itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode" />
														</form:select>&nbsp;<span><form:errors htmlEscape="true"  path="localBodyDistListforVillage" class="errormsg"></form:errors></span>
														<br/><br/></td>
														<td>&nbsp;</td>
											  <td>&nbsp;</td>					
											
											</tr>
											
											<tr >
												<td><strong><spring:message htmlEscape="true"  code="Label.SELECT"></spring:message>&nbsp;&nbsp; ${localGovtBodyForm.intermediatePanchayat}</strong><br/>
												<form:select path="localBodyNameEnglishVillageList" 
															class="frmsfield" id="localGovtBodyListSubDistrict" 
															   onchange="getLocalBodyVillageList(this.value);"> 														   
															   <form:option value=""><spring:message htmlEscape="true"  code="Label.SELECTINTERMEDIATELOCBODY"></spring:message></form:option>
															   <form:options items="${localBodyforSubDistList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
															</form:select>&nbsp;<span><form:errors htmlEscape="true"  path="localBodyNameEnglishVillageList" class="errormsg"></form:errors></span>
															<br/><br/></td>
														<td>&nbsp;</td>
											  <td>&nbsp;</td>					
											
											</tr>
									<tr>
								        <td><strong>
								          ${localGovtBodyForm.villagePanchayat}&nbsp;&nbsp; <spring:message htmlEscape="true"  code="Label.LIST"></spring:message>
								        </strong></td>
								        <td>&nbsp;</td>
								        <td>&nbsp;&nbsp;<strong><spring:message htmlEscape="true"  code="Label.CONTRIBUTELCLBDYLIST"></spring:message></strong></td>
								      </tr>	
											<tr>
											<td rowspan="4">
											<form:select name="select9"
												size="1" id="localGovtBodyVillageList" path="localBodyNameEnglishVillageListSource"
												multiple="multiple" class="frmsfield"
												style="height: 100px; width: 233px">
												<form:options items="${localBodyforVillageList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
												</form:select>
										<br/><br/>														
											 </td>
										
											<td  align="center" width="100"><input type="button"
												value=" Whole &gt;&gt;" style="width: 80px"
												onclick="addItem('ddDestLocalGovtBody','localGovtBodyVillageList','FULL',true);" />

											</td>
											<td rowspan="4">&nbsp;<span><form:errors htmlEscape="true"  path="localGovtBodyNameEnglishDestVillage" class="errormsg"></form:errors></span>
											<form:select name="select8"
								id="ddDestLocalGovtBody" size="1" multiple="multiple" path="localGovtBodyNameEnglishDestVillage" 
								class="frmsfield" style="height: 100px; width: 233px">
								<form:options items="${localGovtBodyNameVillageDestList}" itemLabel="localBodyNameEnglish" itemValue="aliasEnglish"/>
								
							</form:select><br/>
												&nbsp;<input type="button"
												value="<spring:message htmlEscape="true"  code="Label.GETLANDREGION"/>" style="width: 200px" onclick="selectallLocalBody();"/>											
										</td>
										</tr>
										<tr>
											<td align="center"><input type="button"
												value=" Back &lt;" style="width: 70px"
												onClick="removeItem('ddDestLocalGovtBody', 'localGovtBodyVillageList', true)" />
											</td>

										</tr>
										<tr>
											<td align="center"><input type="button"
												value=" Reset &lt;&lt;" style="width: 70px"
												onClick="removeAll('ddDestLocalGovtBody', 'localGovtBodyVillageList', true)" />
											</td>

										</tr>
											<tr>
											<td align="center"><input type="button"
												value="Part &gt;&gt;" style="width: 70px" 
												onClick="addItem('ddDestLocalGovtBody','localGovtBodyVillageList', 'PART',true);"/>
											</td>

										</tr>											
																					
										<!-- End here Of Village Label body  -->	
									</table>
									
			</div>
			<!-- Get Covered Area -->			
			
			<div  id="tr_coveredArea" style="visibility: hidden; display:none;">
									<table>	
									<tr>
										<td><strong><spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message></strong><br/></td><td></td><td></td>
									</tr>											
											<tr>
											<td rowspan="4">
										<form:select name="select8"
								id="ddCoveredVillageList" size="1" multiple="multiple" path="localBodyLandRegionArea" 
								class="frmsfield" style="height: 100px; width: 242px" itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode">
								<form:options items="${localBodyLandRegionAreaList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode"/>
							</form:select><br/><br/>										
											 </td>
											<td  align="center" width="100"><input type="button"
												value=" Whole &gt;&gt;" style="width: 80px"
												onclick="addItem('ddSourceLocalGovtBodyMain','ddCoveredVillageList','FULL',true);" />

											</td>
											<td rowspan="4">&nbsp;<span><form:errors htmlEscape="true"  path="localBodyNameEnglishDestFinalPart" class="errormsg"></form:errors></span>
								<form:select name="select5"
										id="ddSourceLocalGovtBodyMain" size="1" multiple="multiple" path="localBodyNameEnglishDestFinalPart" 
										class="frmsfield" style="height: 100px; width: 242px" itemLabel="localBodyNameEnglish"
																itemValue="localBodyCode">
									<form:options items="${localBodyNameEnglishDestFinalPartList}" itemLabel="localBodyNameEnglish" itemValue="aliasEnglish"/>
							</form:select>									
										</td>
										</tr>
										<tr>
											<td align="center"><input type="button"
												value=" Back &lt;" style="width: 70px"
												onClick="removeItem('ddSourceLocalGovtBodyMain', 'ddCoveredVillageList', true)" />
											</td>

										</tr>
										<tr>
											<td align="center"><input type="button"
												value=" Reset &lt;&lt;" style="width: 70px"
												onClick="removeAll('ddSourceLocalGovtBodyMain', 'ddCoveredVillageList', true)" />
											</td>

										</tr>
											<tr>
											<td align="center"><input type="button"
												value="Part &gt;&gt;" style="width: 70px" 
												onClick="addItem('ddSourceLocalGovtBodyMain','ddCoveredVillageList', 'PART',true);"/>
											</td>

										</tr>											
																					
											
									</table>
									
			</div>
			<!-- End of covered area -->
			
								
					</td> 
										
				</tr>
											
											
			</table>
			<table>
			<tr>
			<!-- Start Unmapped area -->
			
						
										<td><form:checkbox  name="checkbox" value="true" id='unmappedLocalBody' path="unmappedLocalBody" 
											onclick="getHideUnmappedList(this.checked)" />																							
															<spring:message htmlEscape="true" 
																	code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"></spring:message>																																		
																	<br/>
									
												
			<div  id="tr_UnmappedArea" style="visibility: hidden; display:none;">
									<table>	
									<tr>
								        <td><br/><br/><strong>
								          <spring:message htmlEscape="true"  code="Label.UNMAPPEDLIST"></spring:message>
								        </strong></td>
								        <td>&nbsp;</td>
								        <td><strong></strong>								        
								        </td>
								     </tr>																				
									<tr>
											<td rowspan="4">
										<form:select path="localBodyNameEnglishListUnMapped" id="localBodyNameEnglishListUnMapped"
															class="frmsfield" style="height: 100px; width: 242px;"
															  multiple="true"  
																itemLabel="localBodyNameEnglish"
																itemValue="landRegionCode">										
												<form:options items="${localBodyUnmappedAreaList}" itemLabel="aliasEnglish" itemValue="aliasLocal"/>			
										</form:select><br/><br/>										
									 </td>
									<td  align="center" width="110"><input type="button"
												value=" Whole &gt;&gt;" style="width: 80px"
												onclick="addItem('ddSourceLocalGovtBodyUnmapped','localBodyNameEnglishListUnMapped','FULL',true);" />

									</td>
									<td rowspan="4">&nbsp;<span><form:errors htmlEscape="true"  path="localBodyNameEnglishDestUnmapped" class="errormsg"></form:errors></span>
										<form:select name="select5"
											id="ddSourceLocalGovtBodyUnmapped" size="1" multiple="multiple" path="localBodyNameEnglishDestUnmapped" 
											class="frmsfield" style="height: 100px; width: 242px" itemLabel="localBodyNameEnglish" itemValue="localBodyCode">
											<form:options items="${localUnmappedGovtBodyList}" itemLabel="aliasEnglish" itemValue="aliasLocal"/>											
										</form:select>									
								  </td>
								</tr>
								<tr>
											<td align="center"><input type="button"
												value=" Back &lt;" style="width: 70px"
												onClick="removeItem('ddSourceLocalGovtBodyUnmapped', 'localBodyNameEnglishListUnMapped', true)" />
											</td>

								</tr>
								<tr>
											<td align="center"><input type="button"
												value=" Reset &lt;&lt;" style="width: 70px"
												onClick="removeAll('ddSourceLocalGovtBodyUnmapped', 'localBodyNameEnglishListUnMapped', true)" />
											</td>

								</tr>
								<tr>
											<td align="center"><input type="button"
												value="Part &gt;&gt;" style="width: 70px" 
												onClick="addItem('ddSourceLocalGovtBodyUnmapped','localBodyNameEnglishListUnMapped', 'PART',true);"/>
											</td>

								</tr>											
																					
											
							</table>
											
							</div>
						<!--  End of Unampped Area -->
			</td>
			</tr>
			
			
			</table>
		<div class="errormsg"></div>
		</td>
		</tr>		
	</table>
	</div>
</div> --%>
<div class="btnpnl">
			<table width="100%" class="tbl_no_brdr">

				<tr>
					<td width="14%">&nbsp;</td>
					<td width="86%"><div class="btnpnl">
						<input type="button" value="<spring:message htmlEscape="true"  code="Button.PREVIOUS"></spring:message>"
													onclick="previous(2)" class="btn" />
							<label> <input type="button" onclick="javascript:validateAll();" name="Submit"
								value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" /> </label> 
								<label>
								<input type="button" class="btn"  name="Submit9"
								value="<spring:message htmlEscape="true"  code="Button.DRAFT"></spring:message>"
								onclick="callSaveDraft()" /> </label>
								 <label>
								<input type="button" class="btn"  name="Submit6"
								value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
						</div></td>
				</tr>
			</table>
		</div>
		
		
				</div>
			</div>
										</div>									  
									  </td>
                                    </tr>
                                  </table>
							  </div>
							</div>
			</form:form>		
		</div>
</div>
	
</body>
</html>