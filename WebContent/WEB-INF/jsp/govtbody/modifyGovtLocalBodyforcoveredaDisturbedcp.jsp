<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<title><spring:message code="Label.CREATENEWLOCALGOVTBODY" htmlEscape="true" ></spring:message>
	</title> <script src="js/shiftdistrict.js"></script>
	<script src="js/govtorder.js"></script>
	
	<script src="js/common.js"></script> 
	<script src="js/modify_LB.js"></script>
	<script src="js/local_body.js"></script>
	<script type="text/javascript" src="js/GovtLocalBody.js"></script>
	<script type="text/javascript" src="js/viewLocalbody.js"></script>
	<script src="js/lgd_localbody.js"></script>
	<script src="js/validation.js"></script>
	<script src="js/successMessage.js"></script>
	
	<script src="js/attachedFiles.js" type="text/javascript"
		language="javascript"></script>
	<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
	
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
	
	</script>
	
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>		
	</script>
	
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>

	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
	<script type='text/javascript'
		src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
	
	
	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);

		function selectallLocalBody() {

			var selObj = document.getElementById('ddDestLocalGovtBody');
			var villageCode = "";
			for (var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				villageCode += selObj.options[i].value + ",";

			}

			getCoveredLandRegion(villageCode);

		}

		function selectallSubDistLocalBody() {
			// alert("Alert in subdistrict");
			var selObj = document.getElementById('ddSubDistDestLocalGovtBody');
			var subDistrictCode = "";
			for (var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				subDistrictCode += selObj.options[i].value + ",";

			}
			getCoveredLandForSubDistRegion(subDistrictCode);
		}

		function selectallDistrictName() {

			var selObj = document.getElementById('ddDestDistLocalGovtBody');
			var districtList = "";
			for (var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				districtList += selObj.options[i].value + ",";

			}
			getCoveredLandRegionforDistrict(districtList);

		}
		
		function ondataLoadF()
		{
			var id=document.getElementById('selectBox').value;
			
			if (id != "0") 
			{
				var temp = id.split(":");
				var id1 = temp[0];
				var id2 = temp[1];
				var id3 = temp[2];
				
				if(id3 !='U')
				{
					switch (id2) 
					{
						case 'D':
							
							if(document.getElementById('availdivLgdLBDistCAreaUnmapped')) {
								document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'block';
							}
							if(document.getElementById('availdivLgdLBInterCAreaUnmapped')) {
								document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
							}
							if(document.getElementById('availdivLgdLBVillageCAreaUnmapped')) {
								document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
							}
							document.getElementById('availdivLgdUrban').style.display = 'none';
							$("#availdivLgdUrban").hide();
							$("#divLgdLBCoveredAreaHeadQuarter").show();
							$("#getHeadQuartersD").show();
							getHeadQuarterListFinalWithoutMappedLBonLoadDist('D'); 
							break;
						case 'I':
							document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'block';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							$("#availdivLgdUrban").hide();
							$("#divLgdLBCoveredAreaHeadQuarter").show();
							$("#getHeadQuartersT").show();
							getHeadQuarterListFinalWithoutMappedLBonLoadSubDist('T');
							
							break;
						case 'V':
							$("#availdivLgdLBVillageCAreaUnmapped").show();
							$("#availdivLgdLBInterCAreaUnmapped").hide();
							$("#availdivLgdLBDistCAreaUnmapped").hide();
							$("#availdivLgdUrban").hide();
							$("#divLgdLBCoveredAreaHeadQuarter").show();
							$("#getHeadQuartersV").show();
							//document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'block';
						/* 	document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
							document.getElementById('availdivLgdUrban').style.display = 'none';
							document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.display = 'block';
							document.getElementById('divLgdLBCoveredAreaHeadQuarter').style.visibility = 'visible';
							document.getElementById('getHeadQuartersV').style.display = 'block';
							document.getElementById('getHeadQuartersV').style.visibility = 'visible';*/
							getHeadQuarterListFinalWithoutMappedLBonLoad('V');  
							
							break;
					}
				}	
				else{
					
					$("#availdivLgdUrban").show();
					$("#availdivLgdLBInterCAreaUnmapped").hide();
					$("#availdivLgdLBDistCAreaUnmapped").hide();
					$("#availdivLgdLBVillageCAreaUnmapped").hide();
					
				/* 	document.getElementById('availdivLgdUrban').style.display = 'block';
					document.getElementById('availdivLgdLBInterCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBDistCAreaUnmapped').style.display = 'none';
					document.getElementById('availdivLgdLBVillageCAreaUnmapped').style.display = 'none'; */
				}	
			
			}
			
		}
		
	  	if ( window.addEventListener ) { 
		     window.addEventListener("load",ondataLoadF, true );
		}
		else 
		if ( window.attachEvent ) { 
		        window.attachEvent("onload", ondataLoadF);
		} else 
		if ( window.onLoad ) {
		     window.onload = ondataLoadF;
		}  
		
	</script>
	<%@include file="../common/dwr.jsp"%>
</head>

<!-- <body onload=ondataLoad('${selectBox}')> -->
<body>
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="redirectToDisturbedPage.htm" method="POST" commandName="localGovtBodyForm" enctype="multipart/form-data" onsubmit="displayLoadingImage()" class="form-horizontal">
						   <input type="hidden" name="selectBox" id="selectBox" value="<c:out value='${selectBox}' escapeXml='true'></c:out>"></input>
						   <input type="hidden" name="selectBox1" id="subDistrictVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishList}' escapeXml='true'></c:out>"></input>
						   <input type="hidden" name="selectBox2" id="villageVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishListSource}' escapeXml='true'></c:out>"></input>
						   <input type='hidden' id="localBCode" value="<c:out value='${localBCode}' escapeXml='true'></c:out>"/>
						   <input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
						   <input type='hidden'	id="hdnStateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
						   <input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
						   <form:hidden path="lgd_LBTypeNamehidden" id="lgdLBTypeNamehidden" value="${selectBox}" />
							<input type="hidden" name="<csrf:token-name/>"
							value="<csrf:token-value uri="redirectToDisturbedPage.htm"/>" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.MODIFYGOVTLOCALBODY" htmlEscape="true"> </spring:message></h3>
                                </div>
                                
                               
                        <div class="box-body">
                          
                          <div>Reason for Local Body to be in Disturbed State<h4><font color="red"><c:out value="${messageDisturbed}" escapeXml="true"></c:out></font></h4></div>
                          
                          <div class="box-header subheading">
                                    <h4><spring:message code="Label.LOCALBODYDETAILS" htmlEscape="true"></spring:message></h4>
                                </div>
                        
                        
                        <table width="100%" class="table table-bordered table-hover">
								<tr>
									<td>&nbsp;</td>
									<td>
										<form:hidden path="localBodyCode" id="hdnLbCode" value="${localGovtBodyForm.localBodyCode}" htmlEscape="true" /> 
										<form:hidden path="lbType" id="hdnLbTypeCode" value="${localGovtBodyForm.lbType}" htmlEscape="true" />
										<form:hidden path="operationCode" id="operationCode" value="${localGovtBodyForm.operationCode}" htmlEscape="true" />
										<form:hidden path="lgd_LBlevelChk" id="lgd_LBlevelChk" value="${localGovtBodyForm.lgd_LBlevelChk}" htmlEscape="true" />
									</td>
								</tr>
								<c:forEach var="localBodyDetails" varStatus="localBodyUrbanNameListRow" items="${localGovtBodyForm.localBodyDetails}">

									<tr>
										<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].isdisturbed">
											<c:if test="${status.value}">
											<td class="lblBold" width="40%"><label> <spring:message
													code="Label.DISTURBEDSTATE" htmlEscape="true"  ></spring:message>
										</label></td>
												<td> <img src="images/red_flg.png" width="13" height="9" /> </td>
											</c:if>
										</spring:bind>
									</tr>

									<tr>
										<td  width="40%"><label> <spring:message
													code="Label.NAMEOFLOCALBODY" htmlEscape="true"  ></spring:message>
										</label></td><td width="40%"> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
													<c:out value="${status.value}" escapeXml="true"/>

												</spring:bind>
										</label></td>
									</tr>

									<tr>
										<td ><label> <spring:message
													code="Label.NAMEINLOCALLANGUAGE" htmlEscape="true" ></spring:message>
										</label></td><td> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
													<c:out value="${status.value}" escapeXml="true" />

												</spring:bind>
										</label></td>
									</tr>
									<tr>
										<td><label><spring:message
													code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"  ></spring:message> </label></td><td>
											<label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
													<c:out value="${status.value}" escapeXml="true" />
												</spring:bind>
										</label></td>
									</tr>
									<tr>
										<td ><label> <spring:message
													code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"  ></spring:message>
										</label></td><td> <label class="lblPlain"> <spring:bind
													path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
													<c:out value="${status.value}" escapeXml="true"/>
												</spring:bind>
										</label></td>
									</tr>

									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].level"  htmlEscape="true" >
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />

									</spring:bind>
									
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
											
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
										
									<spring:bind
										path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal"  htmlEscape="true">
										<input type="hidden" class="frmfield"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" escapeXml="true" />" />
									</spring:bind>
									
								</c:forEach>


							</table>
                        <!-- Coverage Area of Local Body -->
                        <div id='divLgdLBCoveredArea' > 
                        
                          <div class="box-header subheading">
								<h4><spring:message code="Label.MODIFYCOVERAGE" htmlEscape="true"></spring:message>
								</h4>
								<form:hidden path="lgd_hiddenLbcList" id="hdnListLbCode" />
							</div>
                        <div id='currentCoverDiv' >
                        
								<div id="availdivLgdLBDistCAreaUnmapped"  style="display: none">
								 
								 <div class="form-group">
								  <label class="col-sm-3 control-label">	<spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTDIS"></spring:message> </label>
								   <div class="col-sm-6">
								    <form:select path="availlgd_LBDistCAreaSourceListUmapped" class="form-control" id="availddLgdLBDistCAreaSourceLUmapped"
															items="${districtnamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR"
															 multiple="true">

									</form:select>
								   
								   </div>
								 
								 </div>
								 
								 <div class="form-group">
								  <label class="col-sm-3 control-label"></label>
								   <div class="col-sm-6">
								   <div  style="visibility: hidden; display: none;">   
															 <form:select
																path="availlgd_LBDistCAreaSourceListUmappedHidden" class="form-control"
																id="availddLgdLBDistCAreaSourceLUmappedHidden"
																items="${districtnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																style="height: 110px; width: 230px" multiple="true">
															</form:select>
														</div> 
									<input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforCovArea('availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistCAreaSourceLUmapped','availddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatDCAUmapped',true)" />					
														
								   
								   </div>
								 
								 </div>
								 
								 
								 <div class="form-group">
								  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTSUB"></spring:message></label>
								  <div class="col-sm-6">
								   <form:select path="availlgd_LBSubDistrictSourceLatDCAUmapped" class="form-control"
															id="availddLgdLBSubDistrictSourceLatDCAUmapped"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"  multiple="true">
														</form:select>
														
										<input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforcoveredsubdisticDP('availddLgdLBSubDistrictSourceLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
										</div>				
								 </div>
								 
								 <div class="form-group">
								  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTVILL"></spring:message></label>
								  <div class="col-sm-6">
								   <form:select path="availlgd_LBVillageSourceLatDCAUmapped" class="form-control"
															id="availddLgdLBVillageSourceLatDCAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"  multiple="true">
														</form:select>
														
														<span class="errormsg" id="availddLgdLBSubDistrictDestLatDCAUmapped_error"> </span>
													<span><form:errors htmlEscape="true"  path="lgd_LBSubDistrictDestLatDCAUmapped" class="errormsg"></form:errors> </span>
														
										<input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforcoveredvillage('availddLgdLBVillageSourceLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
										</div>				
								 </div>
										

				 		  </div>
                      
                      </div>  
                        </div>
                    <div id="availdivLgdLBInterCAreaUnmapped" style="display: none">
                      <div class="form-group">
                       <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTSUB"></spring:message></label>
                       <div class="col-sm-6">
                        <form:select path="availlgd_LBInterCAreaSourceListUmapped" class="form-control" id="availddLgdLBInterCAreaSourceLUmapped"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR" multiple="true">
														</form:select>
                       </div>
                      
                      </div>
                      
                      
                      <div class="form-group" >
                       <label class="col-sm-3 control-label"></label>
	                       <div class="col-sm-6" style="visibility: hidden; display: none;" >  
																 <form:select
																	path="availlgd_LBInterCAreaSourceListUmappedHidden" class="frmtxtarea"
																	id="availddLgdLBInterCAreaSourceLUmappedHidden"
																	items="${subdisticnamelistHidden}"
																	itemLabel="land_region_name_english" 
																	itemValue="combineLR"
																	style="height: 110px; width: 230px" multiple="true">
																</form:select>
							
                      <input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforcoveredsubdistic('availddLgdLBInterCAreaSourceLUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','availddLgdLBVillageSourceLatICAUmapped',true)" /><br />
                         </div>
                      </div>
                      
                      
                      <div class="form-group">
                       <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTVILL"></spring:message></label>
                       <div class="col-sm-6">
                        <form:select path="availlgd_LBVillageSourceLatICAUmapped" class="form-control"
															id="availddLgdLBVillageSourceLatICAUmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code" multiple="true">
														</form:select>
							<span class="errormsg" id="availddLgdLBVillageDestLatICAUmapped_error"> </span>
                            <span><form:errors htmlEscape="true"  path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors> </span>
                            						
							<input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforcoveredvillage('availddLgdLBVillageSourceLatICAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />							
														
                         </div>
                      
                      
                      </div>
										
				</div>    
                        
                        
               <div id="availdivLgdUrban" style="display: none">
									<div class="form-group">
									  <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTSUB"></spring:message> </label>
									  <div class="col-sm-6">
									   <form:select path="availlgd_LBInterCAreaSourceListUmappedUrban" class="form-control"
															id="availddLgdLBInterCAreaSourceLUmappedUrban"
															items="${subdisticnamelist}"
															itemLabel="land_region_name_english"
															itemValue="land_region_code"
															 multiple="true">

										 </form:select>
										 <span class="errormsg" id="availddLgdLBVillageDestLatICAUmapped_error"> </span>&nbsp;<span><form:errors htmlEscape="true" 
																path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors>
													</span>
									  <input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforcoveredsubdisticUrbanFin('availddLgdLBInterCAreaSourceLUmappedUrban','ddLgdUrbanLBDistUmappedSource',true)" />
									  </div>
									</div>	
									
							     <div class="frmpnlbghidden" style="visibility: hidden; display: none;">  
									 <form:select path="availlgd_LBInterCAreaSourceListUmappedUrbanHidden" class="frmtxtarea"
																id="availddLgdLBInterCAreaSourceLUmappedUrbanHidden"
																items="${subdisticnamelistHidden}"
																itemLabel="land_region_name_english" 
																itemValue="combineLR"
																 multiple="true">
															</form:select>
									</div> 			
														 
					</div>         
                     
                     
                     <div id="availdivLgdLBVillageCAreaUnmapped" style="display: none">
						<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.AVAILABLEFORCURRENTVILL"></spring:message></label>
							<div class="col-sm-6">
							 <form:select path="availlgd_LBVillageCAreaSourceLUnmapped" class="form-control" id="availddLgdLBVillageCAreaSourceLUnmapped"
															items="${villagenamelist}"
															itemLabel="land_region_name_english"
															itemValue="combineLR" multiple="true">
							</form:select>
							<div class="frmpnlbghidden" style="visibility: hidden; display: none;">   
								<form:select path="availlgd_LBVillageCAreaSourceLUnmappedHidden" class="frmtxtarea" id="availddLgdLBVillageCAreaSourceLUnmappedHidden"
											 items="${villagenamelistHidden}" itemLabel="land_region_name_english" 
																itemValue="combineLR"  multiple="true">
															</form:select>
								</div> 
								<input type="button" id="btnremoveOneULB" name="Submit4" value="Remove" class="btn btn-primary"
														onclick="removeItemforcoveredvillageFin('availddLgdLBVillageCAreaSourceLUnmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" /><br />
								
							</div>	 	
									 	
						</div>

				</div>   
                        
                  <div id='divLgdLBCoveredArea' >
					
							<div class="box-header subheading">
								<h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
							</div>
							
							<div class="form-group">
							 <label class="col-sm-1 control-label"></label>
							  <div class="col-sm-10">
							    <label class="radio-inline">
							    <form:checkbox id="chkLgdLBExistChk" onclick="getHideLocalBodyParentListModify(document.getElementById('selectBox').value, this.checked,'${localGovtBodyForm.localBodyCode}');"
														value="true" path="lgd_LBExistCheck"></form:checkbox> <label><spring:message htmlEscape="true" 
															code="Label.SELEXISTINGCOVEREDAREA"></spring:message><span><form:errors htmlEscape="true" 
																path="lgd_LBExistCheck" class="errormsg"></form:errors>
													</span> </label></label>
													
							    <label class="radio-inline"><form:checkbox value="true"
														onclick="getHideUnmappedListModifyforcoveredarea(document.getElementById('selectBox').value,this.checked);"
														id='chkLgdLBUnmapped' path="lgd_LBUnmappedCheck" /><label>
														<spring:message htmlEscape="true" 
															code="Label.SELCONTRIBUTEUNMAPPEDPARTIALLY"></spring:message><span><form:errors htmlEscape="true" 
																path="lgd_LBUnmappedCheck" class="errormsg"></form:errors>
													</span>
												</label></label>
							  
							  </div>
							</div>
						</div>	
			<div id='divLgdLBDistCArea' class="" style="display: none">
					
							<div class="box-header subheading">
									<h4><spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message></h4>
					            </div>
										
													<div id="ddLgdLBDistPDestList_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList_error1"><form:errors path="lgd_LBDistPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList_error2" style="display: none" ></div> 
													<div id="ddLgdLBDistPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBDistPDestList1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBDistPDestList1_error2" style="display: none" ></div> 
											
											
											
		<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock">
											  	<spring:message htmlEscape="true"  code="Label.AVAILABLE"></spring:message>
												<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> 
												<spring:message htmlEscape="true"  code="Label.LIST"></spring:message><span class="errormsg">*</span>
											 </label>
		              <form:select path="lgd_LBDistPSourceList" class="form-control"
															id="ddLgdLBDistPSourceList"
															items="${districtPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode"
															multiple="true">
														</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>" onclick="addItemforLBChangeCovFULL('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" />
						<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemLevel1('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAllLevel1('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemforLBChangeCov('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureDist}" escapeXml="true"></c:out> &nbsp;<spring:message htmlEscape="true" 
																code="Label.LIST"></spring:message> </label><span
														class="errormsg">*</span>
										</label> 
			   <form:select  id="ddLgdLBDistPDestList"  multiple="true" path="lgd_LBDistPDestList" class="form-control" >

														</form:select> 
						<div id="ddLgdLBDistCAreaDestL_error" class="error"><spring:message code="error.blank.DISTRICTCAatDCA" htmlEscape="true"></spring:message></div>
						<div class="errormsg" id="ddLgdLBDistCAreaDestL_error1"><form:errors path="lgd_LBDistCAreaDestList" htmlEscape="true"/></div>  
						<div class="errormsg" id="ddLgdLBDistCAreaDestL_error2" style="display: none" ></div> 								
				
				<input type="button"  value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														class="btn btn-primary" onclick="selectLocalBodyListAtDCAforModifyCoverageF();" /> 
		     </div>				
            </div>
         </div>
				
				
											
	 <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILDISTRICTLIST"></spring:message></label>
		              <form:select path="lgd_LBDistCAreaSourceList"   class="form-control" id="ddLgdLBDistCAreaSourceL"  multiple="true">
                         </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						    <input type="button"  class="btn btn-primary btn-xs btn-block" value="<spring:message	code="Button.WHOLE"/>" onclick="addItemforVillageLocalBodyDP('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistPDestListhiddenforHeadQuarter','FULL',true,'D');" />
							<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;"  onclick="removeItemModify('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL',true)" />
							<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAllModify('ddLgdLBDistCAreaDestL', 'ddLgdLBDistCAreaSourceL', true)" />
							<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforVillageLocalBodyDP('ddLgdLBDistCAreaDestL','ddLgdLBDistCAreaSourceL','availddLgdLBDistCAreaSourceLUmapped','ddLgdLBDistPDestListhiddenforHeadQuarter','PART',true,'D');" />
												
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"></spring:message></label> 
			   <form:select name="select6" id="ddLgdLBDistCAreaDestL"  multiple="true" path="lgd_LBDistCAreaDestList" class="form-control" >
					</form:select>
						
			<div id="ddLgdLBSubDistrictDestLatDCA_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatDCA" htmlEscape="true"></spring:message></div>
			<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error1"><form:errors path="lgd_LBSubDistrictDestLatDCA" htmlEscape="true"/></div>  
			<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error2" style="display: none" ></div>
													 												
			<input type="button" class="btn btn-primary" value="<spring:message htmlEscape="true" code="Button.GETSUBDISTRICTL"/>"  onclick="selectSubdistrictAtDCACovChng();" />
		    
		     </div>				
            </div>
         </div>								
											
											
			
		    <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label>
		             <form:select path="lgd_LBSubDistrictSourceLatDCA" class="form-control" id="ddLgdLBSubDistrictSourceLatDCA" multiple="true">
                      </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button"  class="btn btn-primary btn-xs btn-block" value=" Whole &gt;&gt;" onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA','FULL',true,'T');" />
						<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemLevel3('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAllLevel3('ddLgdLBSubDistrictDestLatDCA', 'ddLgdLBSubDistrictSourceLatDCA', true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCA','ddLgdLBSubDistrictSourceLatDCA', 'PART',true,'T');" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message></label> 
			   <form:select name="select6"   id="ddLgdLBSubDistrictDestLatDCA"  multiple="true" path="lgd_LBSubDistrictDestLatDCA" class="form-control" >
				</form:select>
						
					<div id="ddLgdLBVillageDestLatDCA_error" class="error"><spring:message code="error.blank.VILLAGECAatDCA" htmlEscape="true"></spring:message></div>
					<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error1"><form:errors path="lgd_LBVillageDestLatDCA" htmlEscape="true"/></div>  
					<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error2" style="display: none" ></div> 	
															
					 <input type="button" class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>"
														style="width: 200px" onclick="selectVillageAtDCACovChng();" />	
		     </div>				
            </div>
         </div>		
         
         
         
            <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true"  code="Label.AVAILVILLAGELIST"></spring:message> </label>
		             <form:select path="lgd_LBVillageSourceLatDCA" class="form-control" id="ddLgdLBVillageSourceLatDCA" multiple="true">
                      </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						    <input type="button"  class="btn btn-primary btn-xs btn-block"  value=" Whole &gt;&gt;"  onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA','FULL',true,'V');" />
							<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"  name="Submit4" value="Back &lt;" onclick="removeItemLB('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA',true)" />
							<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll('ddLgdLBVillageDestLatDCA', 'ddLgdLBVillageSourceLatDCA', true)" />
							<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCA','ddLgdLBVillageSourceLatDCA', 'PART',true,'V');" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"></spring:message></label> 
			  <form:select name="select6" id="ddLgdLBVillageDestLatDCA"   multiple="true" path="lgd_LBVillageDestLatDCA"
															class="form-control" >
														</form:select>
						
					<div id="ddLgdLBVillageDestLatDCA_error" class="error"><spring:message code="error.blank.VILLAGECAatDCA" htmlEscape="true"></spring:message></div>
					<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error1"><form:errors path="lgd_LBVillageDestLatDCA" htmlEscape="true"/></div>  
					<div class="errormsg" id="ddLgdLBVillageDestLatDCA_error2" style="display: none" ></div> 	
															
					 	
		     </div>				
            </div>
         </div>	
         
         
      </div>
         
             <div class="" style="visibility: hidden; display: none;">
					 <form:select  id="ddLgdLBDistPDestListhidden" multiple="true" path="lgd_LBDistPDestListhidden" class="form-control">

									</form:select>
									
									<form:select id="ddLgdLBDistPDestListhiddenforHeadQuarter"  multiple="true"
											path="lgd_LBDistPDestListhiddenforHeadQuarter" class="frmtxtarea"
											style="height: 110px; width: 230px">
											
									</form:select>
					</div>						
											
											
											
			<div id='divLgdLBInterCArea'  style="display: none">
				
				<div class="box-header subheading">
						<h4><spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message></h4>
				</div>
				                                    <div id="ddLgdLBInterPDestList_error" class="error"><spring:message code="error.blank.INTERPANCHAYT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBInterPDestList_msg" style="display:none"><spring:message code="error.blank.INTERPANCHAYT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBInterPDestList_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList_error2" style="display: none" ></div> 
													<div id="ddLgdLBInterPDestList1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBInterPDestList1_error1"><form:errors path="lgd_LBInterPDestList" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBInterPDestList1_error2" style="display: none"></div>
													
													
													
		<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message>
						<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> &nbsp;
						<spring:message htmlEscape="true" code="Label.LIST"></spring:message> <span class="errormsg">*</span> </label>
		              <form:select  path="lgd_LBInterPSourceList" class="form-control" id="ddLgdLBInterPSourceList" multiple="true">
                     </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button"  class="btn btn-primary btn-xs btn-block"value="<spring:message  code="Button.WHOLE"/>" onclick="addItemforLBCHCovFULL('ddLgdLBInterPDestList','ddLgdLBInterPSourceList','FULL',true);" />
							<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"  name="Submit4" value="Back &lt;"  onclick="removeItemLevel9('ddLgdLBInterPDestList','ddLgdLBInterPSourceList',true)" />
							<input type="button" class="btn btn-primary btn-xs btn-block"  value=" Reset &lt;&lt;"   onclick="removeAll9('ddLgdLBInterPDestList', 'ddLgdLBInterPSourceList', true)" />
							<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"   onclick="addItemforLBCHCov('ddLgdLBInterPDestList','ddLgdLBInterPSourceList', 'PART',true);" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTE"></spring:message>
										<c:out value="${localGovtBodyForm.lgd_LBNomenclatureInter}" escapeXml="true"></c:out> 
										<spring:message htmlEscape="true"  code="Label.LIST"></spring:message> <spanclass="errormsg">*</span></label> 
			    <form:select   name="select6" id="ddLgdLBInterPDestList"  multiple="true" path="lgd_LBInterPDestList" class="form-control" >
				</form:select>
					<div id="ddLgdLBInterCAreaDestL_error" class="error"><spring:message code="error.blank.SUBDISTRICTCAatICA" htmlEscape="true"></spring:message></div>
					<div class="errormsg" id="ddLgdLBInterCAreaDestL_error1"><form:errors path="lgd_LBInterCAreaDestList" htmlEscape="true"/></div>  
					<div class="errormsg" id="ddLgdLBInterCAreaDestL_error2" style="display: none" ></div>
																					
						<input type="button"  value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														class="btn btn-primary" onclick="selectLocalBodyListAtICAforChCoverage();" />		
		     </div>				
            </div>
         </div>
				
				
		<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label>
		              <form:select path="lgd_LBInterCAreaSourceList" class="form-control" id="ddLgdLBInterCAreaSourceL"
															 multiple="true">

														</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>"  onclick="addItemforLBforsubChangeCov('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','availddLgdLBInterCAreaSourceLUmapped','ddLgdInterSubDestListhidden','FULL',true,'T');" />
						 <input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemLevel10('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll10('ddLgdLBInterCAreaDestL', 'ddLgdLBInterCAreaSourceL', true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block"  value="Part &gt;&gt;" onclick="addItemforLBforsubChangeCov('ddLgdLBInterCAreaDestL','ddLgdLBInterCAreaSourceL','availddLgdLBInterCAreaSourceLUmapped','ddLgdInterSubDestListhidden','PART',true,'T');" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message></label> 
			   <form:select name="select6"   id="ddLgdLBInterCAreaDestL"  multiple="true" path="lgd_LBInterCAreaDestList" class="form-control">
														</form:select>					
						<div id="ddLgdLBVillageDestLatICA_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error1"><form:errors path="lgd_LBVillageDestLatICA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestLatICA_error2" style="display: none" ></div>
					    
					    
					    <input type="button" class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>" onclick="selectVillageICACovChng();" />		
		     </div>				
            </div>
         </div>	
				
				
		<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true"  code="Label.AVAILVILLAGELIST"></spring:message></label>
		             <form:select path="lgd_LBVillageSourceLatICA" class="form-control" id="ddLgdLBVillageSourceLatICA"
															 multiple="true">

														</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Whole &gt;&gt;"  onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA','FULL',true,'V');" />
						 <input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"  name="Submit4" value="Back &lt;" onclick="removeItemLB('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll('ddLgdLBVillageDestLatICA', 'ddLgdLBVillageSourceLatICA', true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICA','ddLgdLBVillageSourceLatICA', 'PART',true,'V');" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"></spring:message></label> 
			  <form:select name="select6" id="ddLgdLBVillageDestLatICA"  multiple="true" path="lgd_LBVillageDestLatICA"
															class="form-control">
														</form:select>				
		     </div>				
            </div>
         </div>			
         
				
		<div class="frmpnlbgInterhidden" style="visibility: hidden; display: none;">
			<form:select id="ddLgdInterSubDestListhidden" size="1" multiple="true"
											path="lgd_LBInterSubDestListhidden" class="frmtxtarea"
											style="height: 110px; width: 230px">

									</form:select>
	    </div>
							
							
							
		</div>						
					
		<div id='divLgdLBVillageCArea'  style="display: none">
			
			<div class="box-header subheading">
			   <spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
			</div>
			
			                                        <div id="ddLgdLBVillageDestAtVillageCA_error" class="error"><spring:message code="error.blank.VILLAGEPANCHAYT" htmlEscape="true"></spring:message></div>
													<div id="ddLgdLBVillageDestAtVillageCA_msg" style="display:none"><spring:message code="error.blank.VILLAGEPANCHAYT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error2" style="display: none" ></div>
													<div id="ddLgdLBVillageDestAtVillageCA1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error1"><form:errors path="lgd_LBVillageDestAtVillageCA" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddLgdLBVillageDestAtVillageCA1_error2" style="display: none"></div> 
													
	 <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message>
															&nbsp;<c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message> <span class="errormsg">*</span></label>
		              <form:select    path="lgd_LBVillageSourceAtVillageCA" class="form-control" id="ddLgdLBVillageSourceAtVillageCA"  multiple="true">
                          </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"   name="Submit4" value="Back &lt;" onclick="removeItemLevel8('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll8('ddLgdLBVillageDestAtVillageCA', 'ddLgdLBVillageSourceAtVillageCA', true)" /><br />
						<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforLBforChangeCoverage('ddLgdLBVillageDestAtVillageCA','ddLgdLBVillageSourceAtVillageCA', 'PART',true);" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message> <c:out value="${localGovtBodyForm.lgd_LBNomenclatureVillage}" escapeXml="true"></c:out>
															&nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message><span class="mandatory">*</span></label> 
			    <form:select   name="select6" id="ddLgdLBVillageDestAtVillageCA" multiple="true" path="lgd_LBVillageDestAtVillageCA" class="form-control" >
					</form:select>
															
						<div id="ddLgdLBVillageCAreaDestL_error" class="error"><spring:message code="error.blank.VILLAGECAatICA" htmlEscape="true"></spring:message></div>
						<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error1"><form:errors path="lgd_LBVillageCAreaDestL" htmlEscape="true"/></div>  
						<div class="errormsg" id="ddLgdLBVillageCAreaDestL_error2" style="display: none" ></div>
					    
					<input type="button"   class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>"
														style="width: 200px" onclick="selectLocalBodyListAtVCAforModifyCoverageF();" />    
					    	
		     </div>				
            </div>
         </div>								
		
		
		 <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"></spring:message></label>
		              <form:select path="lgd_LBVillageCAreaSourceL"    class="form-control" id="ddLgdLBVillageCAreaSourceL" multiple="true">

														</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button" class="btn btn-primary btn-xs btn-block"  value="<spring:message	code="Button.WHOLE"/>" onclick="addItemforLBforvillageMappedChangedCoverage('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','availddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" />
						<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"  name="Submit4" value="Back &lt;" onclick="removeItemvillageModify('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAll8('ddLgdLBVillageCAreaDestL', 'ddLgdLBVillageCAreaSourceL', true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block"  value="Part &gt;&gt;"    onclick="addItemforLBforvillageMappedChangedCoverage('ddLgdLBVillageCAreaDestL','ddLgdLBVillageCAreaSourceL','availddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" />
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> 
			   <form:select name="select6" id="ddLgdLBVillageCAreaDestL" multiple="true" path="lgd_LBVillageCAreaDestL"
															class="form-control" >
														</form:select>
															
						
		     </div>				
            </div>
         </div>
		
</div>
	<div id='divLgdLBUrban'  style="display: none">
	
		<div class="box-header subheading">
				<spring:message htmlEscape="true"  code="Label.EXISTINGLANDREGION"></spring:message>
		</div>
										
		         <div id="ddLgdUrbanLBDistExistDest_error" class="error"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"></spring:message></div>
				<div id="ddLgdUrbanLBDistExistDest_msg" style="display:none"><spring:message code="error.blank.DistrictPanchayt" htmlEscape="true"/></div>
				<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
				<div class="errormsg" id="ddLgdUrbanLBDistExistDest_error2" style="display: none"></div> 
				<div id="ddLgdUrbanLBDistExistDest1_error" class="error"><spring:message code="error.DESTDISTLOCALBODY" htmlEscape="true"></spring:message></div>
				<div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error1"><form:errors path="lgd_UrbanLBDistExistDest" htmlEscape="true"/></div>  
			     <div class="errormsg" id="ddLgdUrbanLBDistExistDest1_error2" style="display: none"></div> 
													
	 <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message code="Label.AVAILABLEURBANLB" htmlEscape="true"></spring:message> &nbsp;<spring:message htmlEscape="true"  code="Label.LIST"></spring:message> </label>
		              <form:select path="lgd_UrbanLBDistExistSource" class="form-control" id="ddLgdUrbanLBDistExistSource"  multiple="true">
                      </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;"  onclick="removeItemLevel9('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource',true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAll9('ddLgdUrbanLBDistExistDest', 'ddLgdUrbanLBDistExistSource', true)" />
						<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforUrbanChangeCoverage('ddLgdUrbanLBDistExistDest','ddLgdUrbanLBDistExistSource', 'PART',true);" />
					 </div>
<div class="ms_selection col-sm-5">
	 <div class="form-group">
		<label for="ddDestBlock"><spring:message code="Label.CONTRIBUTEURBANLB" htmlEscape="true"></spring:message><spring:message htmlEscape="true" code="Label.LIST"></spring:message> <spanclass="errormsg">*</span></label> 
			   <form:select  name="select6" id="ddLgdUrbanLBDistExistDest"  multiple="true" path="lgd_UrbanLBDistExistDest" class="form-control">
					</form:select>
					
					 <input type="button" class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.GETCOVEREDAREAOFLOCALBODY"/>" onclick="selectLocalBodyListforUrbanforModF();" />
															
						<div id="ddDestBlock_error" class="mandatory"><spring:message code="Error.BLOCK" htmlEscape="true"></spring:message></div>
						<%-- <div id="ddDestBlock_msg" class="mandatory" style="display:none"><spring:message code="Error.BLOCK" htmlEscape="true"/></div> --%>
						<div class="mandatory" id="ddDestBlock_error1"></div>  
					    <div class="mandatory" id="ddDestBlock_error2" style="display: none" ></div>		
		     </div>				
            </div>
         </div>								
										
					
					
 <div class="ms_container row" style="margin-left: 10px;">
	  <div class="ms_selectable col-sm-5 form-group">
		 <label for="ddSourceBlock"><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label>
		  <form:select path="lgd_UrbanLBSubdistrictListSource"  class="form-control" id="ddLgdUrbanLBSubdistrictListSource" multiple="true">
         </form:select>
		        </div> 
	<div class="ms_buttons col-sm-2"><br>
		<input type="button" class="btn btn-primary btn-xs btn-block"  value="<spring:message code="Button.WHOLE"/>" onclick="addItemforLBforsub('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource','FULL',true,'T');" />
		<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"   name="Submit4" value="Back &lt;"  onclick="removeItemLevel10('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource',true)" />
		<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAll10('ddLgdUrbanLBSubdistrictListDest', 'ddLgdUrbanLBSubdistrictListSource', true)" />
		<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"   onclick="addItemforLBforsub('ddLgdUrbanLBSubdistrictListDest','ddLgdUrbanLBSubdistrictListSource', 'PART',true,'T');" />
	</div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true"  code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message></label> 
			    <form:select name="select6"  id="ddLgdUrbanLBSubdistrictListDest" multiple="true" path="lgd_UrbanLBSubdistrictListDest" class="form-control" >
				</form:select>	
		     </div>				
            </div>
         </div>			
					
					
					
	</div>								
										
										
<div id='divLgdLBDistCAreaUnmapped'  style="display: none">
		<div>
			<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message></h4> </div>
												
		<div id="ddLgdLBDistCAreaDestLUmapped_error" class="error"><spring:message code="error.PSDT" htmlEscape="true"></spring:message></div> 
		<div class="errormsg" id="ddLgdLBDistCAreaDestLUmapped_error1"><form:errors path="lgd_LBDistCAreaDestListUmapped" htmlEscape="true"/></div>  
		<div class="errormsg" id="ddLgdLBSubDistrictDestLatDCAUmapped_error2" style="display: none" ></div>
												
<div class="ms_container row" style="margin-left: 10px;">
	 <div class="ms_selectable col-sm-5 form-group">
		<label><spring:message htmlEscape="true"  code="Label.AVAILDISTRICTLIST"></spring:message> </label>
			<form:select path="lgd_LBDistCAreaSourceListUmapped"  class="form-control" id="ddLgdLBDistCAreaSourceLUmapped"  multiple="true">
				<form:options items="${UnmappedData}"  itemLabel="localBodyNameEnglish" itemValue="landRegionCode" />
				</form:select>
														
	</div>									
														

<div class="ms_buttons col-sm-2">
	<input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>" onclick="addItemforLBforDistforCovChange('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped','FULL',true,'D');" />
	<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;"  onclick="removeItemDistrict('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped',true)" />
	<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAllDistrict('ddLgdLBDistCAreaDestLUmapped', 'ddLgdLBDistCAreaSourceLUmapped', true)" />
		<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"   onclick="addItemforLBforDistforCovChange('ddLgdLBDistCAreaDestLUmapped','ddLgdLBDistCAreaSourceLUmapped', 'PART',true,'D');" />
</div>

<div class="ms_selection col-sm-5">
<div class="form-group"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTDISTRICTLIST"></spring:message> <span class="mandatory">*</span></label> 
	<form:select name="select6"  id="ddLgdLBDistCAreaDestLUmapped"  multiple="true" path="lgd_LBDistCAreaDestListUmapped" class="form-control">
	</form:select>
  <input type="button"  value="<spring:message htmlEscape="true"  code="Button.GETSUBDISTRICTL"/>"  class="btn btn-primary"
														onclick="refreshOptions('ddLgdLBSubDistrictSourceLatDCAUmapped');getUnmappedLBSDPListatUmappedFinal('T','${localGovtBodyForm.lbType}','${levelcheck}');" />
	</div>
 </div>
</div>

												
												
												
												
			<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	           <label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label>
				<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped"  class="form-control" id="ddLgdLBSubDistrictSourceLatDCAUmapped"  multiple="true">
                      </form:select>
			</div>
			 <div class="ms_buttons col-sm-2">
				 <input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message  code="Button.WHOLE"/>"  onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped','FULL',true,'T');" />
				<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"  name="Submit4" value="Back &lt;" onclick="removeItemSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped',true)" />
				<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAllSubdistrict('ddLgdLBSubDistrictDestLatDCAUmapped', 'ddLgdLBSubDistrictSourceLatDCAUmapped', true)" />
				<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforLBforsub('ddLgdLBSubDistrictDestLatDCAUmapped','ddLgdLBSubDistrictSourceLatDCAUmapped', 'PART',true,'T');" />
			</div>

			<div class="ms_selection col-sm-5">
			 <div class="form-group"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message> </label> 
			  <form:select name="select6"  id="ddLgdLBSubDistrictDestLatDCAUmapped"  multiple="true" path="lgd_LBSubDistrictDestLatDCAUmapped"
															class="form-control">
			   </form:select>
			    <input type="button"  class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>" style="width: 200px"
														onclick="refreshOptions('ddLgdLBVillageSourceLatDCAUmapped');getUnmappedLBVillPListatUmappedFinal('V','${localGovtBodyForm.lbType}','${levelcheck}');" />
			</div>
		</div>
	</div>
												
												
												
												
												
	<div class="ms_container row" style="margin-left: 10px;">
	   <div class="ms_selectable col-sm-5 form-group"><label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"></spring:message> </label>
		<form:select path="lgd_LBVillageSourceLatDCAUmapped" class="form-control" id="ddLgdLBVillageSourceLatDCAUmapped"  multiple="true">
           </form:select>
		</div>

		<div class="ms_buttons col-sm-2">
			<input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>"  onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped','FULL',true,'V');" />
			 <input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;"  onclick="removeItemVillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped',true)" />
			 <input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll('ddLgdLBVillageDestLatDCAUmapped', 'ddLgdLBVillageSourceLatDCAUmapped', true)" />
			 <input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemforLBforvillage('ddLgdLBVillageDestLatDCAUmapped','ddLgdLBVillageSourceLatDCAUmapped', 'PART',true,'V');" />
		 </div>

	<div class="ms_selection col-sm-5">
			 <div class="form-group"><label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> 
			<form:select name="select6" id="ddLgdLBVillageDestLatDCAUmapped"  multiple="multiple" path="lgd_LBVillageDestLatDCAUmapped" class="form-control">
			 </form:select> 							
	  </div>
	  </div>
	  </div>
	        													
	</div>
</div>
									
									
									
		<div id='divLgdLBInterCAreaUnmapped'  style="display: none">
										
		<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message></h4></div>
			  <div id="ddLgdLBInterCAreaDestLUmapped_error" class="error"><spring:message code="error.PSSDT" htmlEscape="true"></spring:message></div>
			   <div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error1"><form:errors path="lgd_LBInterCAreaDestListUmapped" htmlEscape="true"/></div>  
			   <div class="errormsg" id="ddLgdLBInterCAreaDestLUmapped_error2" style="display: none" ></div>	
													
													
	<div class="ms_container row" style="margin-left: 10px;">
	     <div class="ms_selectable col-sm-5 form-group">
	       <label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label>
			<form:select path="lgd_LBInterCAreaSourceListUmapped"  class="form-control" id="ddLgdLBInterCAreaSourceLUmapped"  multiple="true">
		     <form:options items="${UnmappedData}"  itemLabel="localBodyNameEnglish" itemValue="landRegionCode" />
			</form:select>
		</div>									
														
														
		<div class="ms_buttons col-sm-2">
			<input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>" onclick="addItemforLBforsubUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped','FULL',true,'T');" />
			<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;"  onclick="removeItemSubdistrictUnmapped('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped',true)" />
			<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAll11('ddLgdLBInterCAreaDestLUmapped', 'ddLgdLBInterCAreaSourceLUmapped', true)" />
			<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemforLBforsubUnMappedIP('ddLgdLBInterCAreaDestLUmapped','ddLgdLBInterCAreaSourceLUmapped', 'PART',true,'T');" />
		</div>

	<div class="ms_selection col-sm-5">
			<div class="form-group"><label><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message><span class="errormsg">*</span> </label> 
			<form:select name="select6"  id="ddLgdLBInterCAreaDestLUmapped"  multiple="true" path="lgd_LBInterCAreaDestListUmapped" class="form-control">
														</form:select>
         <input type="button"  class="btn btn-primary" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>" style="width: 200px"
														onclick="refreshOptions('ddLgdLBVillageSourceLatICAUmapped');selectSubDistrictAtICAUmapped('V','${localGovtBodyForm.lbType}','${levelcheck}');" />
		</div>
	</div>
</div>

<div class="ms_container row" style="margin-left: 10px;">
	  <div class="ms_selectable col-sm-5 form-group">
	  <label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"></spring:message> </label>
		<form:select path="lgd_LBVillageSourceLatICAUmapped" class="form-control" id="ddLgdLBVillageSourceLatICAUmapped" multiple="true">
            </form:select>
	</div>

	<div class="ms_buttons col-sm-2">
		<input type="button"  class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>"  onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped','FULL',true,'V');" />
		<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;"  onclick="removeItemVillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped',true)" />
		<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll('ddLgdLBVillageDestLatICAUmapped', 'ddLgdLBVillageSourceLatICAUmapped', true)" />
		<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;"  onclick="addItemforLBforvillage('ddLgdLBVillageDestLatICAUmapped','ddLgdLBVillageSourceLatICAUmapped', 'PART',true,'V');" />
	 </div>

	<div class="ms_selection col-sm-5">
			 <div class="form-group"><label><spring:message htmlEscape="true"  	code="Label.CONTRIBUTVILLAGELIST"></spring:message> </label> 
				<form:select name="select6"  id="ddLgdLBVillageDestLatICAUmapped"  multiple="true" path="lgd_LBVillageDestLatICAUmapped" class="form-control">
				</form:select>
       </div>
      </div>
   </div>
</div>							
													
	
                      
			
<div id='divLgdLBVillageCAreaUnmapped'  style="display: none">
	
	<div class="box-header subheading"><h4><spring:message htmlEscape="true" code="Label.UNMAPPPEDPARTILLY"></spring:message></h4></div>
											
													
	<div id="ddLgdLBVillageCAreaDestLUnmapped_error" class="error"><spring:message code="error.PSCV" htmlEscape="true"></spring:message></div>
	<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error1"><form:errors path="lgd_LBVillageCAreaDestLUnmapped" htmlEscape="true"/></div>  
	<div class="errormsg" id="ddLgdLBVillageCAreaDestLUnmapped_error2" style="display: none" ></div>
													
			<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
	           <label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"></spring:message> </label>
	           
	           
				<form:select path="lgd_LBVillageCAreaSourceLUnmapped"  class="form-control" id="ddLgdLBVillageCAreaSourceLUnmapped" multiple="true">
					<form:options items="${UnmappedData}" itemLabel="localBodyNameEnglish" itemValue="landRegionCode" />
                </form:select>
			  </div>									

			<div class="ms_buttons col-sm-2">
				<input type="button"  class="btn btn-primary btn-xs btn-block" value="<spring:message code="Button.WHOLE"/>" onclick="addItemforLBforvillageMappedUnMappedCovChange('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','FULL',true,'V');" />
				<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB"  name="Submit4" value="Back &lt;"  onclick="removeItemVillUnmapped('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped',true)" />
				<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;"  onclick="removeAll('ddLgdLBVillageCAreaDestLUnmapped', 'ddLgdLBVillageCAreaSourceLUnmapped', true)" />
				<input type="button" class="btn btn-primary btn-xs btn-block"  value="Part &gt;&gt;"   onclick="addItemforLBforvillageMappedUnMappedCovChange('ddLgdLBVillageCAreaDestLUnmapped','ddLgdLBVillageCAreaSourceLUnmapped','PART',true,'V');" />
														
		   </div>

		<div class="ms_selection col-sm-5">
			 <div class="form-group"><label><spring:message htmlEscape="true"  code="Label.CONTRIBUTVILLAGELIST"></spring:message> <span class="errormsg">*</span> </label>
			<form:select name="select6"  id="ddLgdLBVillageCAreaDestLUnmapped"  multiple="true" path="lgd_LBVillageCAreaDestLUnmapped"
															class="form-control">
			</form:select><br />
														
		</div>
	</div>
 </div>
</div>
									
		<div id='divLgdLBUrbanUnmapped'  style="display: none">
		
			<div class="box-header subheading">
			<h4><spring:message htmlEscape="true"  code="Label.UNMAPPPEDPARTILLY"></spring:message></h4>
			</div>
											
											
			<div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">	
					<label><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message> </label>
					<form:select path="lgd_UrbanLBDistUmappedSource"  class="form-control" id="ddLgdUrbanLBDistUmappedSource"  multiple="true">
                          </form:select>
							</div>							

				<div class="ms_buttons col-sm-2">
					<input type="button" class="btn btn-primary btn-xs btn-block" value="<spring:message  code="Button.WHOLE"/>"  onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource','FULL',true,'T');" />
					<input type="button" class="btn btn-primary btn-xs btn-block" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemSubdistrictUnmapped('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource',true)" />
					<input type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll11('ddLgdUrbanLBDistUmappedDest', 'ddLgdUrbanLBDistUmappedSource', true)" />
					<input type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemforLBforsub('ddLgdUrbanLBDistUmappedDest','ddLgdUrbanLBDistUmappedSource', 'PART',true,'T');" />
				</div>

		<div class="ms_selection col-sm-5">
			 <div class="form-group"><label><spring:message htmlEscape="true"  code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message> </label> 
			  <form:select name="select6"  id="ddLgdUrbanLBDistUmappedDest"  multiple="true" path="lgd_UrbanLBDistUmappedDest"
															class="form-control">
														</form:select>
				<span class="errormsg"  id="ddLgdLBVillageDestLatICAUmapped_error"> </span>
				<span><form:errors htmlEscape="true" path="lgd_LBVillageDestLatICAUmapped" class="errormsg"></form:errors> </span>
		 </div>
	  </div>
	
	</div>
													
	</div>
									


						
					
					<div id='divLgdLBCoveredAreaHeadQuarter'  style="display: none">
							
								<div class="box-header subheading">
									<h4><spring:message htmlEscape="true" code="Label.HeaquarterFrame"></spring:message></h4>
								</div>
							
								<table  width="50%">
									<tr>
									 	<td colspan="3" width="100%">
				    						<div id="getHeadQuartersD"></div>
							    			<div id="getHeadQuartersT"></div> 
						    				<div id="getHeadQuartersV"></div>
						   				</td>
									</tr>	
								</table>
							
					</div>
					
					
					<!-- End of Govt Order Details  -->
	<div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
						<form:hidden path="lbType" value="${localGovtBodyForm.lbType}"/>
						<input type="submit" name="Submit" class="btn btn-primary"  value="<spring:message htmlEscape="true" code="Button.NEXT"></spring:message>" onclick="return validateModifyCoverageLBDisturbed();" />
						<%-- <c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'P')}">
							 <input type="button" class="btn btn-warning"  name="Submit6"  value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='modifyGovtLocalBodyMainforcoverageareaClear.htm?<csrf:token uri="modifyGovtLocalBodyMainforcoverageareaClear.htm"/>'" />
					     </c:if>
						<c:if test="${fn:containsIgnoreCase(localGovtBodyForm.lbType,'T')}">
							<input type="button" class="btn btn-warning"	name="Submit6"  value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>"
													onclick="javascript:location.href='modifyGovtLocalBodyMainforcoverageareaClear.htm?<csrf:token uri="modifyGovtLocalBodyMainforcoverageareaClear.htm"/>'" />
						</c:if> --%>
						<input type="button" class="btn btn-danger"  name="Submit6"  value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
						
						
						
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