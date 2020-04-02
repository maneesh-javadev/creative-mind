<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<script src="js/jquery-1.7.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/jquery.MultiFile.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/attachedFiles.js" type="text/javascript"
		language="javascript"></script>
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
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
	
	<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
	<script src="datepicker/jquery-1.6.2.js"></script>
	<script src="datepicker/jquery.ui.core.js"></script>
	<script src="datepicker/jquery.ui.widget.js"></script>
	<script src="datepicker/jquery.ui.datepicker.js"></script>
	<script src="datepicker/calender.js"></script>
	<link rel="stylesheet" href="datepicker/demos.css" /> -->
	<!-- <link href="css/successMessage.css" rel="stylesheet" type="text/css" /> -->
	
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
<div class="overlay" id="overlay1" style="display: none"></div>



<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYGOVTLOCALBODY"></spring:message></h3>
					</div>
					<form:form action="redirectToGovtOrderPage.htm" method="POST" commandName="localGovtBodyForm" enctype="multipart/form-data" onsubmit="displayLoadingImage()" class="form-horizontal">
						<input type="hidden" name="selectBox" id="selectBox" value="<c:out value='${selectBox}' escapeXml='true'></c:out>"></input>
					    <input type="hidden" name="selectBox1" id="subDistrictVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishList}' escapeXml='true'></c:out>" ></input>
					    <input type="hidden" name="selectBox2" id="villageVal" value="<c:out value='${localGovtBodyForm.localBodyNameEnglishListSource}' escapeXml='true'></c:out>"></input>
					    <input type='hidden' id="localBCode" value="<c:out value='${localBCode}' escapeXml='true'></c:out>"/>
					    <input type="hidden" name="levelcheck" id="levelcheck" value="<c:out value='${levelcheck}' escapeXml='true'></c:out>"></input>
					    <input type='hidden'	id="hdnStateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
					    <input type="hidden" name="districtCode" value="<c:out value='${districtCode}' escapeXml='true'></c:out>"/>
					    <form:hidden path="lgd_LBTypeNamehidden" id="lgdLBTypeNamehidden" value="${selectBox}"/>
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="redirectToGovtOrderPage.htm"/>" />
						<form:hidden path="lgd_hiddenLbcList" id="hdnListLbCode" />
						<div class="box-body" id='divLgdLBCoveredArea'>
							<div class="box-header subheading">
			                  <h4><spring:message htmlEscape="true"  code="Label.COVEREDAREAOFNEWLCLBDY"></spring:message></h4>
			                </div>
			                <div class="form-group">
		                    	<label class="col-sm-3 control-label">
		                    		<form:checkbox id="chkChangeNameDisturbed" onclick="getHideModNameDisturbed(document.getElementById('selectBox').value, this.checked);"
									value="true" path="lgd_LBModNameCheckDisturb"></form:checkbox>
								</label>
								<div class="col-sm-6">
		                        	<label>
										<spring:message htmlEscape="true" code="Label.MODIFYLOCALBODYNAME"></spring:message>
										<span>
											<form:errors htmlEscape="true" path="lgd_LBExistCheck" class="errormsg"></form:errors>
										</span> 
									</label>
		                      	</div>
		                    </div>
		                    <c:if test="${categorycheck !='U'}">
								<c:if test="${levelcheck !='D'}">
				                    <div class="form-group">
				                    	<label class="col-sm-3 control-label">
				                    		<form:checkbox value="true" onclick="getHideModParentDisturbed(document.getElementById('selectBox').value,this.checked);"
											id='chkParentTypeDisturbed' path="lgd_LBModParentCheckDisturb" />
										</label>
										<div class="col-sm-6">
				                        	<label>
				                        		<spring:message htmlEscape="true" code="label.MODIFYPARENT"></spring:message>
				                        		<span>
				                        			<form:errors htmlEscape="true" path="lgd_LBUnmappedCheck" class="errormsg"></form:errors>
												</span>
											</label>
				                      	</div>
				                    </div>
				                   </c:if>
				                  </c:if>
				                  
				                  <div id="modify_name" style="visibility: visible; display: none;">
				                  		<div class="box-header subheading">
						                  <h4><spring:message htmlEscape="true"  code="Label.MODIFYLOCALBODYNAME"></spring:message></h4>
						                </div>
						                <c:forEach var="localBodyDetails" varStatus="localBodyUrbanNameListRow" items="${localGovtBodyForm.localBodyDetails}">
						                	<div class="form-group">
						                    	<label class="col-sm-3 control-label">
						                    		<spring:message code="Label.NEWNAMEOFLOCALBODYENGLISH" htmlEscape="true" ></spring:message><span class="mandatory">*</span>
												</label>
												<div class="col-sm-6">
						                        	<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameEnglish">
														<input type="text" class="form-control" maxlength="200" name="<c:out value="${status.expression}"/>"
														value="<c:out  value="${status.value}" escapeXml="true"/>" id="localBodyNameInEn" onblur="validateLocalNameInEngModify()"
														onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"/>
													
														<div id="localBodyNameInEn_error" class="errormsg"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div> 
														<div class="errormsg" id="localBodyNameInEn_error1"><form:errors path="lgd_LBNameInEn" htmlEscape="true"/></div>  
														<div class="errormsg" id="localBodyNameInEn_error2"><form:errors path="lgd_LBNameInEnh" htmlEscape="true"/></div>
														<div class="errormsg" id="localBodyNameInEn_error3" style="display: none"></div>
													</spring:bind>
													<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyCode">
														<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" escapeXml="true" />"
														onfocus="this.blur()"/>
													</spring:bind> 
													<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyVersion">
														<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"
														onfocus="this.blur()"/>
													</spring:bind>
						                      	</div>
						                    </div>
						                    <div class="form-group">
						                    	<label class="col-sm-3 control-label">
						                    		<spring:message	code="Label.NEWNAMEOFLOCALLANGUAGE" htmlEscape="true"  ></spring:message>
												</label>
												<div class="col-sm-6">
						                        	<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyNameLocal">
														<input type="text" class="form-control" maxlength="100" name="<c:out value="${status.expression}" />"
															value="<c:out value="${status.value}" escapeXml="true"/>" id="localBodyNameInLocal" onblur="validateSpecialCharactersLBName(this.value);" />
													</spring:bind>
													<form:errors htmlEscape="true" path="localBodyNameLocal" class="errormsg"></form:errors>
													<div class="errormsg">
														<div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
														<div class="errormsg" id="localBodyNameInLocal_error1"><form:errors path="localBodyNameLocal" htmlEscape="true"/></div>  
														<div class="errormsg" id="localBodyNameInLocal_error2" style="display: none" ></div>
													</div>
						                      	</div>
						                    </div>
						                    <div class="form-group">
						                    	<label class="col-sm-3 control-label">
						                    		<spring:message code="Label.LOCALBODYALIASENGLISH" htmlEscape="true" ></spring:message>
												</label>
												<div class="col-sm-6">
						                        	<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].aliasNameEnglish">
														<input type="text" class="form-control" maxlength="50" name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />" onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"/>
													</spring:bind>
													<span><form:errors htmlEscape="true"  path="aliasNameEnglish" class="errormsg"></form:errors> </span>
													<div class="errormsg"></div>
													<div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="localBodyNameInEn_error1"><form:errors path="aliasNameEnglish" htmlEscape="true"/></div>  
													<div class="errormsg" id="localBodyNameInEn_error2" style="display: none" ></div>
						                      	</div>
						                    </div>
						                    <div class="form-group">
						                    	<label class="col-sm-3 control-label">
						                    		<spring:message code="Label.LOCALBODYALIASLOCAL" htmlEscape="true" ></spring:message>
												</label>
												<div class="col-sm-6">
						                        	<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].alisNameLocal">
														<input type="text" class="form-control" maxlength="50" name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true" />" id="localBodyNameInAliasLocal" onblur="validateSpecialCharactersLBNameAliasLocal(this.value);"/>
													</spring:bind>
													<span><form:errors htmlEscape="true"  path="alisNameLocal" class="errormsg"></form:errors> </span>
													<div class="errormsg"></div>
													<div id="localBodyNameInEn_error" class="error"><spring:message code="error.blank.localBodyNameInEn" htmlEscape="true"></spring:message></div>
													<div class="errormsg" id="localBodyNameInAliasLocal_error1"><form:errors path="alisNameLocal" htmlEscape="true"/></div>  
													<div class="errormsg" id="localBodyNameInAliasLocal_error2" style="display: none" ></div>
						                      	</div>
						                    </div>
						                    <input name="lgd_LBlevelChk" id="hiddenCheckBox" type="hidden" value="<c:out value='${localGovtBodyForm.lgd_LBlevelChk}' escapeXml='true'></c:out>"/>
										</c:forEach>
				                  </div>
				                  <div id="modify_parent_list" style="visibility: visible; display: none;">
				                  	<div class="box-header subheading">
					                  <h4><spring:message htmlEscape="true"  code="label.MODIFYPARENT"></spring:message></h4>
					                </div>
					                <c:forEach var="localBodyDetails" varStatus="localBodyUrbanNameListRow" items="${localGovtBodyForm.localBodyDetails}">
					                	<div class="form-group">
					                    	<label class="col-sm-3 control-label">
					                    		<spring:message code="Label.SELECTEDTYPEOFLOCALBODY" htmlEscape="true" ></spring:message>
											</label>
											<div class="col-sm-6">
					                        	<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].localBodyTypeName">
													<input type="text" maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true" />" readonly />
												</spring:bind>
												<div class="errormsg"></div>
					                      	</div>
					                    </div>
					                    <div class="form-group">
					                    	<label class="col-sm-3 control-label">
					                    		<spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true" ></spring:message>
											</label>
											<div class="col-sm-6">
					                        	<spring:bind path="localGovtBodyForm.localBodyDetails[${localBodyUrbanNameListRow.index}].parentLocalBodyNameEnglish">
													<input type="text" maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="this.blur()" readonly />
												</spring:bind>
												<div class="errormsg"></div>
					                      	</div>
					                    </div>
					                </c:forEach>
					                <div class="form-group">
				                    	<label class="col-sm-3 control-label">
				                    		<spring:message code="Label.PARENTNEWLOCALBODY" htmlEscape="true" ></spring:message>
				                    		<c:out value="${localGovtBodyForm.localBodyDetails[0].localBodyNameEnglish}" escapeXml="true"></c:out>
				                    		<span class="errormsg">*</span>
										</label>
										<div class="col-sm-6">
				                        	<form:select path="localBodyNameEnglishList" id="parentLocalBody" class="form-control" htmlEscape="true"
														onfocus="validateOnFocus('parentLocalBody');helpMessage(this,'parentLocalBody_msg');" onblur="vlidateOnblur('parentLocalBody','1','15','c');hideHelp();"
														onkeyup="hideMessageOnKeyPress('parentLocalBody')">
														<form:option value="0">
															<spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message>
														</form:option>
														<form:options items="${lgd_LGdistrictPanchayatList}"
															itemLabel="localBodyNameEnglish"
															itemValue="localBodyCode" />
													</form:select> 
												
												<div id="parentLocalBody_error" class="error"><spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"></spring:message></div>
												<div id="parentLocalBody_msg" style="display:none"><spring:message code="Label.PARENTOFLOCALBODY" htmlEscape="true"/></div>
												<div class="errormsg" id="parentLocalBody_error1"><form:errors path="localBodyNameEnglishList" htmlEscape="true"/></div>  
												<div class="errormsg" id="parentLocalBody_error2" style="display: none"></div>
				                      	</div>
				                    </div>
				                  </div>
				                  <div class="box-footer">
			                    	<div class="col-sm-offset-2 col-sm-10">
			                    		<div class="pull-right">
			                           		<form:hidden path="lbType" value="${localGovtBodyForm.lbType}"/>
											<input type="submit" class="btn btn-primary" name="Submit" value="<spring:message htmlEscape="true" code="Button.NEXT"></spring:message>" onclick="return validateforDisturbedState('${categorycheck}','${levelcheck}');" />
											<input type="button" class="btn btn-danger" name="Submit6" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
			                        	</div>
			                    	 </div>   
		                  		</div>
				                  
				                  
		                    
		                    
		                </div>
		               </form:form>
		               <script src="/LGD/JavaScriptServlet"></script>
		              </div>
		             </section>
		            </div>
		           </section>






</body>
</html>