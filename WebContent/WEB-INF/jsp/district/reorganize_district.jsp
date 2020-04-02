<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Panchayat</title>
 <script src="js/shiftdistrict.js"></script> 
<script type="text/javascript" src="js/district.js" charset="utf-8"></script>
<script type="text/javascript" src="js/createDistrict.js" charset="utf-8"></script>
<link href="css/shiftdistrict.css" rel="stylesheet" type="text/css" />
<link href="css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubVillageService.js'></script>



<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" language="javascript">
dwr.engine.setActiveReverseAjax(true);

function validateform()



{
		var districtName=document.getElementById('districtNameInEn').value;
	
	if(districtName=="")
	{
	alert("Please Enter Name Of the Districit");
	document.getElementById('districtNameInEn').focus();
	return false;
	}
	return true;
}
function selectall()
{

	var selObj=document.getElementById('ddDestDistrict');	
	//var selObj2=document.getElementById('surveyListNew');
	//var selObj=document.getElementById('subDistrictListNew');	
	var subDistrict="";
		for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrict +=selObj.options[i].value+",";
	 }
		getSubDistricts(subDistrict);
	
}

function selectFinal()
{
	var selObj=document.getElementById('subDistrictListNew');
	var selObj1=document.getElementById('ddDestDistrict');
	//var selObj2=document.getElementById('surveyListNew');
	//var selObj=document.getElementById('subDistrictListNew');	
	var subDistrict="";
		for (i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
		     subDistrict +=selObj.options[i].value+",";
	 }
		for (j = 0; j < selObj1.options.length; j++) {
			selObj1.options[j].selected=true;
		     subDistrict +=selObj1.options[j].value+",";
	 }
	
	}
</script>
</head>

<body onload="load_page()">
	<form:form action="reorganize_district.htm" method="POST" commandName="district">

		<div style="margin:20px 20px 0px 20px; background:#F7F7F7; padding:10px">
					  <div class="frmtxt" style="position:relative; background:inherit; padding-top:20px;">
						  <div style="position:absolute; z-index:1; width:180px; text-align:center; top:-11px;
						   left:12px" class="frmhdtitle"><spring:message htmlEscape="true"  code="App.GENERALDETAILNEWDISTRICT"></spring:message>		   
						   </div>
						    <table width="100%" border="0" cellpadding="0" cellspacing="0">
						    
						   
						   
						      <tr>
                                <td width="14%" rowspan="8">&nbsp;</td>
                                <td width="86%"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEENGLISH"></spring:message><span id ="required">*</span><br />
                               <form:input path="districtNameInEn"  id="districtNameInEn" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('districtNameInEn')" onblur="districtNameInEn()" />
							<span class="msg" id="districtNameInEn_msg"><spring:message htmlEscape="true"  code="Error.Newnamedistricteng"></spring:message></span> 
							<span class="error" id="districtNameInEn_error"></span> 
							</td>
                               </tr>
                               
                               
                                 <tr>
                                <td width="86%"><spring:message htmlEscape="true"  code="App.DISTRICTNAMELOCAL"></spring:message><br />
                                   <form:input path="districtNameInLcl"  id="districtNameInLcl" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('districtNameInLcl')" onblur="NameInLcl()" />
							<!-- <span class="msg" id="districtNameInLcl_msg">Please enter  Name of new District (In English) here</span>
							<span class="error" id="districtNameInLcl_error"></span> -->
                                    </td>
                              </tr>
                              
                               <tr>
                                <td width="86%"><spring:message htmlEscape="true"  code="App.DISTRICTALIASENGLISH"></spring:message><br />
                                   <form:input path="districtAliasInEn"  id="districtAliasInEn" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('districtAliasInEn')" onblur="AliasInEn()" />
							<!-- <span class="msg" id="districtAliasInEn_msg">Please enter  Name of Alias of the district(In English) here</span>
							<span class="error" id="districtAliasInEn_error"></span> -->
                                    </td>
                              </tr>
                            
                            
                              <tr>
                                <td width="86%"><spring:message htmlEscape="true"  code="App.DISTRICTALIASLOCAL"></spring:message><br />
                                   <form:input path="districtAliasInLcl"  id="districtAliasInLcl" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('districtAliasInLcl')" onblur="AliasInLcl()" />
							<!-- <span class="msg" id="districtAliasInLcl_msg">Alias of the district (In Local language) here</span>
							<span class="error" id="districtAliasInLcl_error"></span> -->
                                    </td>
                              </tr>
                          
                           <tr>
                                <td width="86%"><spring:message htmlEscape="true"  code="App.HEADQUARTERS"></spring:message><br />
                                   <form:input path="districtHeadquarters"  id="districtHeadquarters" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('districtHeadquarters')" onblur="headquarters()" />
						<!-- 	<span class="msg" id="districtHeadquarters_msg">Please enter Headquarters here</span>
							<span class="error" id="districtHeadquarters_error"></span> -->
                                    </td>
                              </tr>
                             
                              <tr>
                                <td width="86%"><spring:message htmlEscape="true"  code="App.CENSUSCODE2011"></spring:message><br />
                                   <form:input path="census2011Code"  id="census2011Code" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('census2011Code')" onblur="Cns2011Code()" />
						<!-- 	<span class="msg" id="census2011Code_msg">Please enter  Census2011 Code here</span>
							<span class="error" id="census2011Code_error"></span> -->
                                    </td>
                              </tr> 
                              
                              
                                <tr>
                                <td width="86%"><spring:message htmlEscape="true"  code="App.STATESPECIFICCODE"></spring:message><br />
                                   <form:input path="stateSpecificCode"  id="stateSpecificCode" cssClass="frmfield" htmlEscape="true"
							  onfocus="show_msg('stateSpecificCode')" onblur="specificCode()" />
							<!-- <span class="msg" id="stateSpecificCode_msg">Please enter  State Specific Code here</span>
							<span class="error" id="stateSpecificCode_error"></span> -->
                                    </td>
                              </tr> 
                     
                  
                            </table>
					  </div></div>
					  
					  
					  
					  
					  			  
					  	<div style="margin:20px 20px 0px 20px; background:#F7F7F7; padding:10px">
					  <div class="frmtxt" style="position:relative; background:inherit; padding-top:20px;">
						  <div style="position:absolute; z-index:1; width:180px; text-align:center; top:-11px;
						   left:12px" class="frmhdtitle"><spring:message htmlEscape="true"  code="App.HEADQUARTERSDISTRICT"></spring:message></div>
						    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                              
                              <tr>
                                <td width="12%" rowspan="2">&nbsp;</td>
                                <td width="88%" style="padding:0px"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                              
                                 
                                </table>
								<div style="height:15px; padding-top:3px;" class="errormsg"></div>								</td>
                              </tr>
                              <tr>
                                <td><table width="468" border="0" cellpadding="0" cellspacing="0" style="padding:0px">
                                 
                                </table>
                                <div style="height:15px; padding-top:3px;" class="errormsg"></div>
								
								</td>
                              </tr>
                              <tr>
															
															<td width="75" align="right"><label id="headquarterName"><spring:message htmlEscape="true" 
																	code="App.SUBDISTRICTHEADQUARTERENGLISH"></spring:message></label></td>
															<td width="75"><input type="text" name="headquarterName" /></td>
															<td width="75" align="right"><label id="headquarterNameLocal"><spring:message htmlEscape="true" 
																	code="App.SUBDISTRICTHEADQUARTERLOCAL"></spring:message></label></td>
															<td width="75"><input type="text" name="headquarterNameLocal" /></td>
															<td>&nbsp;</td>
															
														</tr>
                            </table>
						</div>						
					</div>	
		
								
					  
					  	<div style="margin:20px 20px 0px 20px; background:#F7F7F7; padding:10px">
						<div class="frmtxt" style="position:relative; background:inherit; padding-top:20px;">
						<div style="position:absolute; z-index:1; width:64px; text-align:center; top:-11px; left:12px" class="frmhdtitle"><spring:message htmlEscape="true"  code="App.GISNODES"></spring:message></div>
						    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                              
                              <tr>
                                <td width="12%" rowspan="2">&nbsp;</td>
                                <td width="88%" style="padding:0px"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <%-- <tr>
															<td width="200"><spring:message htmlEscape="true"  code="App.GISNODES"></spring:message>
																 </td>
															<td width="75" align="right"><label id="lbllatitude"><spring:message htmlEscape="true" 
																	code="App.LATITUDE"></spring:message></label></td>
															<td width="75"><input type="text" name="latitude" /></td>
															<td width="75" align="right"><label id="lbllongitude"><spring:message htmlEscape="true" 
																	code="App.LONGITUDE"></spring:message></label></td>
															<td width="75"><input type="text" name="longitude" /></td>
															<td>&nbsp;</td>
															<td width="150" align="right"><label> <input
																	type="button" name="Submit32" value="Add More Nodes" onclick="addgisnodes()" />
															</label>
															</td>
														</tr> --%>
                                  <tr>
                                    <td width="223"><spring:message htmlEscape="true"  code="App.GISNODES"></spring:message></td>
                                    <td width="52" align="right"><label id="lbllatitude"><spring:message htmlEscape="true"  code="App.LATITUDE"></spring:message></label></td>
                                    <td width="75"><input name="latitude" id="lbllatitude" type="text" class="frmfield" style="width:50px" /></td>
                                    <td width="75" align="right"><label id="lbllongitude"><spring:message htmlEscape="true"  code="App.LONGITUDE"></spring:message></label></td>
                                    <td width="75"><input name="longitude" type="text" class="frmfield" style="width:50px" /></td>
                                    <td width="412">&nbsp;</td>
                                    <td width="150" align="right">
                                      <label>
                                        <input type="button" name="Submit3" value="<spring:message htmlEscape="true"  code="App.ADDNODES"></spring:message>" onclick="addgisnodes()" />
                                    </label></td>
                                  </tr>
                                </table>
								<div style="height:15px; padding-top:3px;" class="errormsg"></div>								</td>
                              </tr>
                              <tr>
                                <td><table width="468" border="0" cellpadding="0" cellspacing="0" style="padding:0px">
                                  <tr>
                                    <td width="31%"><label><span class="blktxt"><spring:message htmlEscape="true"  code="App.DISTRICTMAP"></spring:message> </span>
                                          
                                    </label></td>
                                    <td width="54%"><input name="textfield123" type="text" class="frmfield" style="width:250px" /></td>
                                    <td width="15%" align="right"><label>
                                      <input type="submit" name="Submit33" value="<spring:message htmlEscape="true"  code="App.UPDATE"></spring:message>" />
                                    </label></td>
                                  </tr>
                                </table>
                                <div style="height:15px; padding-top:3px;" class="errormsg"></div>
								
								</td>
                              </tr>
                              
                            </table>
						</div>						
					</div>	
					
								<div 
									style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px;">
									<div class="frmtxt"
										style="position: relative; background: inherit; padding-top: 20px;">
										<div
											style="position: absolute; z-index: 1; width: 164px; text-align: center; top: -11px; left: 12px"
											class="frmhdtitle">
											<spring:message htmlEscape="true"  code="App.CONTRIBUTINGLANDREGION"></spring:message>
										</div>
										
		<table>
		<tr>
			<td><strong><spring:message htmlEscape="true"  code="App.RSELECTEDVILLAGES"></spring:message>
			</strong></td>
			<td align="center">&nbsp;</td>
			<td><strong><spring:message htmlEscape="true" 
			code="App.REORGANIZEVILLAGES"></spring:message> </strong></td>
			</tr>
		<tr>
		<div id='cvillage'
			style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: hidden">
					<tr>
							<td width="246" valign="top">
							<form:select name="select9"
									size="1" id="villageList" path=""
									multiple="multiple" class="frmsfield"
									style="height: 80px; width: 233px">
							</form:select>
							</td>
							<td align="center" style="padding-top: 3px">
							<table
									width="100%" border="0" cellspacing="0" cellpadding="0">
									
									<tr>
										<td align="center"><label> <input type="button"
										id="btnremoveAllVillages" name="Submit4" value="  &gt;"
												onclick="removeItem('villageListNew','villageList',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button"
												id="btnremoveOneVillage" name="Submit4" value=" &lt; "
												onclick="removeAll('villageListNew','villageList',true)" />
										</label></td>
									</tr>
									<tr>
										<td align="center"><label> <input type="button"
												id="btnaddVillagePart" name="Submit4"
												value="&lt;&lt;"
												onclick="addItem('villageListNew','villageList','PART',true)" />
										</label></td>
									</tr>
								</table></td>
							<td width="246" valign="top">
							<form:select name="select4"
								id="villageListNew" size="1" multiple="multiple" path="contributedVillages"
								class="frmsfield" style="height: 80px; width: 242px" >
							</form:select></td>
						</tr>
						</div>
						</tr>
						</table>
	</div>
		</div>
	<div
		style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px">
		<div class="frmtxt"
			style="position: relative; background: inherit; padding-top: 20px;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td width="14%">&nbsp;</td>
					<td width="86%"><div style="padding: 10px 0px 10px 0px">
							<label> <input type="button" name="Submit" 
								value="<spring:message htmlEscape="true"  code="App.SAVE"></spring:message>"  
								onclick="formSubmit();"/></label> <label>
								<input type="button" name="Submit"
								value="<spring:message htmlEscape="true"  code="App.PROCEED"></spring:message>"
								onclick="javascript:go('contributeVillage.htm');" /> </label> <label>
								<input type="button" name="Submit6"
								value="<spring:message htmlEscape="true"  code="App.CANCEL"></spring:message>"
								onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
	</form:form>
	
	</td>
	</tr>
	</table>

</body>
</html>