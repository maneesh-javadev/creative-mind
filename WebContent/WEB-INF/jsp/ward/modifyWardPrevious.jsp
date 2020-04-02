<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>
<title><spring:message htmlEscape="true" code="Label.MODIFYMINISTRY"></spring:message></title>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<!-- <link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
	<script type="text/javascript">
	function MinistryName()
		{
         	if(document.getElementById("ministryName").value == "" )
			{
				document.getElementById("ministryName_error").innerHTML="Ministry Name is Required";
				$("#ministryName_error").show();
				$("#ministryName_msg").hide();
				$("#ministryName").addClass("error_fld");
				$("#ministryName").addClass("error_msg");
				return false;

			}
			else 
			{
				$("#ministryName_msg").hide();
				return true;
					
			}
		}
	function MinistryShortName()
	{
      if(document.getElementById("ministryshortName").value == "" )
		{
			document.getElementById("ministryshortName_error").innerHTML="Ministry Short Name is Required";
			$("#ministryshortName_error").show();
			$("#ministryshortName_msg").hide();
			$("#ministryshortName").addClass("error_fld");
			$("#ministryshortName").addClass("error_msg");
			return false;

		}
		else 
		{
			$("#ministryName_msg").hide();
			return true;
				
		}
	}
	function validateMinistryCorrection()
	{
		var msg=null;	
				if (!MinistryName()) {
					if(msg!=null)
					{
						msg=msg+"Ministry Name is Required"+ '\n';	
					}
					else
						{
						msg="Ministry Name is Required"+ '\n';	
						}
				}
				if (!MinistryShortName()) {
					if(msg!=null)
					{
						msg=msg+"Ministry Short Name is Required "+ '\n';	
					}
					else
						{
						msg="Ministry Short Name is Required"+ '\n';	
						}
				}
											
				if(msg!=null)
					{
					alert(msg);
					return false;
				}
				else{
					
					return true;
				}
				
			return false;
	}
	

	</script>
</head>
<body onload='toggledisplay2("chkchvillage","changevillage");'>
<div id="frmcontent">
					<div class="frmhd">
					  <table width="100%" class="tbl_no_brdr">
                        <tr>
                          <td><spring:message htmlEscape="true" code="Label.MODIFYWARD"></spring:message>&nbsp;</td>
                          <td>&nbsp;</td>
                       <%--//these links are not working    <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message></a></td> --%>
                        </tr>
                      </table>
					</div>
					<div class="clear"></div>
					<div class="frmpnlbrdr">
						<form:form action="modifyWardAction.htm" method="POST" commandName="modifyWardCmd" id="frmModifyVillage" enctype="multipart/form-data">
							  <div class="frmpnlbg">
                                <div class="frmtxt">
                                  <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.MODIFYWARDOPTION"></spring:message></div>
                                    <table width="100%" class="tbl_no_brdr">                                    
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="86%"><spring:message htmlEscape="true" code="Label.MODIFYWARDOPTION"></spring:message><br />
                                          <table width="259" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td width="20" height="25" class="tblclear"><label>
                                               <form:radiobutton htmlEscape="true" path="correction" type="radio" id='chkcrvillage' value="true" 
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage")'/>
                                                 </label></td>
                                              <td width="83" class="tblclear"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></td>
                                              <td width="20" class="tblclear"><label>
                                                <form:radiobutton htmlEscape="true" path="correction"  type="radio"  id='chkchvillage' value="false"
                                                 onclick='toggledisplay2("chkchvillage","changevillage")' />
                                              </label></td>
                                              <td width="136" class="tblclear"><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></td>
                                            </tr>
                                          </table>
                                      <div class="errormsg"> </div></td>
                                    </tr>
                                  </table>
                                </div>
						      </div>
							  <div   id='correctionvillage' class="frmpnlbg" style=" visibility: hidden; display:none">
							  <div class="frmtxt" >
								  <div class="frmhdtitle"  ><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								  <tr>
								   <td width="14%" rowspan="2">&nbsp;</td>
								  <td width="86%"><spring:message htmlEscape="true" code="Label.OLDWARDNAME"></spring:message><span class="errormsg">*</span> <br /> 
										<form:input htmlEscape="true" path="ward_Namecr" class="frmfield" id="ministryName" cssClass="frmfield" onfocus="show_msg('ministryName')" onblur="MinistryName()"/>							
					    	                 <span class="error" id="ministryName_error"></span></td>
										</tr>
									
									<tr>
									<td width="86%"><spring:message htmlEscape="true" code="Label.WARDNAMELOCAL"></spring:message><span class="errormsg">*</span> <br /> 
										<form:input htmlEscape="true" path="ward_NameLocal" class="frmfield" id="ministryshortName" cssClass="frmfield" onfocus="show_msg('ministryshortName')" onblur="MinistryShortName()"/>							
					    	                 <span class="error" id="ministryshortName_error"></span>
										<form:input htmlEscape="true" path="ward_Number" class="frmfield" type="hidden"/>
										<form:input htmlEscape="true" path="ward_version" class="frmfield" type="hidden"/>
									</td>
									</tr>
									
								  </table>
								  </div>
                           <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn" value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>" onclick="return validateMinistryCorrection();"  />
                                    </label>
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:go('home.htm');"/> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
							  
							  
							</div>
							<div  id='changevillage'  class="frmpnlbg" style="visibility: visible; display:block">
							  <div class="frmtxt">
								  <div class="frmhdtitle" style="visibility:hidden;"><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message> </div>
								  <table width="100%" class="tbl_no_brdr">
								   <%--  <c:forEach var="listMinistry" varStatus="listMinistryDetailsRow" items="${modifyMinistryCmd.listMinistry}"> --%>
                                   
                                    
                                    <tr>
                                      <td width="14%" rowspan="4">&nbsp;</td>
                                      <td width="86%"><spring:message htmlEscape="true"	code="Label.OLDWARDNAME"></spring:message><span class="errormsg">*</span><br />
                                          <label>
                                          <form:input htmlEscape="true" path="ward_Name" class="frmfield" id="ministryNamecr" cssClass="frmfield" onfocus="show_msg('ministryNamecr')" onblur="MinistryNamecr()"/>							
					    	                 <span class="error" id="ministryNamecr_error"></span>
                                          </label>
                                          <div class="errormsg"></div></td>
                                    </tr>
                                     
                                  </table>
                                       <table width="100%" class="tbl_no_brdr">
														<tr>
															<td width="14%" rowspan="9">&nbsp;</td>
															<td width="86%"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="errormsg">*</span><br />
                                                             <form:input htmlEscape="true" path="orderCode" id="orderCode" type="hidden" class="frmfield" />
																<form:input htmlEscape="true" path="orderNo" id="OrderNo"
																	type="text" class="frmfield" onblur="validateOrdeNo();"
																	onfocus="show_msg('OrderNo');"
																	onkeypress="validateNumericAlphaKeys();" /><span
																class="error" id="OrderNo_error"></span><div class="errormsg"> <form:errors htmlEscape="true" path="orderNo" ></form:errors></div></td>
														</tr>
														<tr>
															<td><spring:message htmlEscape="true" code="Label.ORDERDATE"></spring:message><span class="errormsg">*</span><br />
																<table width="100%" class="tbl_no_brdr">

																	<tr>
																		<td colspan="2" class="tblclear"><form:input htmlEscape="true"
																				path="orderDate" id="OrderDate" type="text"
																				class="frmfield" style="width: 100px" onchange="setEffectiveDate(this.value);"
																				onfocus="show_msg('OrderDate');"
																				onblur="validateOrdeDate();"
																				onkeypress="validateNumeric();" /> <span
																				class="error" id="OrderDate_error"></span><div class="errormsg">  <form:errors htmlEscape="true" path="orderDate" ></form:errors></div></td>
																	</tr>
																</table></td>

														</tr>
														<tr>
															<td><spring:message htmlEscape="true" code="Label.EFFECTIVEDATE"></spring:message><span class="errormsg">*</span><br />
																<table width="100%" class="tbl_no_brdr">
																	<tr>
																		<td colspan="2" class="tblclear"><form:input htmlEscape="true"
																				id="EffectiveDate" path="ordereffectiveDate"
																				type="text" class="frmfield" style="width: 100px"
																				onfocus="show_msg('EffectiveDate');"
																				onblur="validateEffecDate();"
																				onkeypress="validateNumeric();" /> <span
																			class="error" id="EffectiveDate_error"></span><div class="errormsg"> <form:errors htmlEscape="true" path="ordereffectiveDate" ></form:errors></div></td>

																	</tr>
																</table></td>
														</tr>
														<tr>
															<td><spring:message htmlEscape="true" code="Label.GAZPUBDATE"></spring:message><span class="errormsg">*</span><br />
																<table width="100%	" class="tbl_no_brdr">

																	<tr>
																		<td colspan="2" class="tblclear"><form:input htmlEscape="true"
																				id="GazPubDate" path="gazPubDate" type="text"
																				class="frmfield" style="width: 100px"
																				onfocus="show_msg('GazPubDate');" onchange="setFocus();"
																				onblur="validateGazPubDate();"
																				onkeypress="validateNumeric();" /> <span
																			class="error" id="GazPubDate_error"></span><div class="errormsg"> <form:errors htmlEscape="true" path="gazPubDate" ></form:errors></div></td>

																	</tr>
																</table></td>
														</tr>

														<tr>
															<td><spring:message htmlEscape="true" code="Label.UPLOADGOVTORDER"></spring:message><span class="errormsg">*</span><br />
																<form:input htmlEscape="true" id="filGovernmentOrder"
																	path="filePath" class="frmfield" type="file"
																	size="25" onfocus="show_msg('filGovernmentOrder');"
																	onblur="validateSFile();" /> <span
																class="error" id="filGovernmentOrder_error"></span></td>
														</tr>
														<tr>
															<td>&nbsp;</td>

														</tr>
														
													</table> 
							  </div>
						 
						  <div class="btnpnl">
                              <table width="100%" class="tbl_no_brdr">
                                <tr>
                                  <td ><label>
                                    <input type="submit" name="Submit" class="btn"  value="<spring:message htmlEscape="true" code="Button.SAVE"></spring:message>" onclick="return validateMinistryChange();" />
                                    </label>
                                    <label>
                                     <input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
                                  </td>
                                </tr>
                              </table>
					      </div>
					       </div>
						</form:form>
					</div></div>
</body>
</html>