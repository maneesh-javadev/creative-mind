<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
<script type="text/javascript" language="javascript">
var stateCode; 
function setDirection(val)
{	
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "globalviewVillagePagination.do?<csrf:token uri='globalviewVillagePagination.do'/>";
	document.forms['form1'].submit();
}

function go1(id)
{
	var url="viewvillagebysubdistrictCode.htm?id="+id;
	window.location=url;
	}
dwr.engine.setActiveReverseAjax(true);

function getData()
{
	var chkcrvillage=document.getElementById('chkcrvillage');
	var chkchvillage=document.getElementById('chkchvillage');
	
	if(chkcrvillage.checked)
		{		
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		{
		document.forms['form1'].submit();	
		}
	else
		{
		alert('Please enter search criteria!');
		}
		}
	else if(chkchvillage.checked)
		{
			document.getElementById('text1').value='';
			document.getElementById('text2').value='';
			document.forms['form1'].submit();
			chkchvillage.checked=true;
			chkcrvillage.checked=false;
		}

}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function loadPage()
{	
document.getElementById('chkcrvillage').checked=false;
document.getElementById('chkchvillage').checked=false;
if(document.getElementById('text1').value!=''|| document.getElementById('text2').value!='')
	{
	document.getElementById('chkcrvillage').checked=true;
	document.getElementById('chkchvillage').checked=false;
	}
else if(document.getElementById('ddSourceDistrict').value!=0)
	{
	document.getElementById('chkcrvillage').checked=false;
	document.getElementById('chkchvillage').checked=true;
	}
stateCode = document.getElementById('ddSourceState').value;
	}
	
function doRefresh(removeAll)
{
	document.getElementById('text1').value=document.getElementById('text2').value='';
	if (removeAll)
		{
			document.getElementById('ddSourceState').selectedIndex=0;
			removeSelectedValue('ddSourceDistrict');
		}
	removeSelectedValue('ddSourceSubDistrict');	
}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}

function validate(val)
{
	if (val==1)
		if (document.getElementById('ddSourceDistrict').selectedIndex>0 && document.getElementById('ddSourceSubDistrict').selectedIndex>0)			
			document.forms['form1'].submit();
		else
			alert('please select the value from dropdown');
}

function populateDistricts()
{
	stateCode = document.getElementById('hdnStateCode').value;
	getDistrictList(stateCode);
}

</script>


<title><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></title>

</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
		<div id="frmcontent">
			 <div class="frmhd">
				    <table width="100%" class="tbl_no_brdr">
					      <tr>
						             <td><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						            <%--//these links are not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></td> --%>
					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewvillage.htm" id="form1" method="POST" commandName="villagebean">
				   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewvillage.htm"/>"/>
				       <div id="cat">				       
				       		 <div class="frmpnlbg">
						      <div class="frmtxt" align="center">
							     <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWVILL"></spring:message></div>
						          <table width="800" class="tbl_no_brdr">
						          <div>
						             <td width="86%">
                                          <table width="900" height="21" class="tbl_no_brdr">
                                            <tr>
                                              <td class="tblclear" align="center"><input type='hidden'
										id="hdnStateCode"
										value='<%=request.getAttribute("stateCode")%>' /> <label>
                                               <form:radiobutton htmlEscape="true" path="correctionRadio" id='chkcrvillage' 
                                                onclick='toggledisplay2("chkcrvillage","correctionvillage");doRefresh(true);'/>
                                                 </label></td>
                                              <td width="120" class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SEARCHBYNAME"></spring:message></label></td>
                                              <td>&nbsp;&nbsp;</td>
                                              <td class="tblclear"><label>
                                                <form:radiobutton htmlEscape="true" path="correctionRadio" id='chkchvillage' 
                                                 onclick='toggledisplay2("chkchvillage","changevillage");doRefresh(true);populateDistricts();' />
                                              </label></td>
                                              <td width="128" class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SEARCHBYHIERARCHY"></spring:message></label></td>
	 <!-- correctly aligned no. of records label 22/05/2012 -->
	<td colspan="5" class="lblBold" align="right" >
		<label>
			<spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message>
		<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
			<form:option value="5" label="5" />
			<form:option value="10" label="10" />
			<form:option value="25" label="25" selected="selected" />
			<form:option value="50" label="50" />
			<form:option value="100" label="100" />
		</form:select>
	   </label>
	</td>
                                            </tr>
                                          </table>
                                      </td>				          						          
                                 </table>
                             </div>
						</div>
					</div>
					      <div class="frmpnlbg" id='correctionvillage' style=" visibility: hidden; display:none">
						      <div class="frmtxt" align="center">
							     <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message></div>
						          <table width="800" class="tbl_no_brdr">
								    <tr>
									   <td width="120" class="lblBold"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
									   <td><label><form:input type="text" class="frmfield" id="text1" onkeypress="validateNumericKeys(event)"  path="code"/> </label>
									   <form:errors htmlEscape="true" path="code" class="errormsg"></form:errors></td>
									   <td width="230" align="right" class="lblBold" ><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
									   <td align="right"><label><form:input type="text" class="frmfield" id="text2" onkeypress="validateCharKeys(event)" path="villageNameEnglish"/> </label>
									   <form:errors htmlEscape="true" path="villageNameEnglish" class="errormsg"></form:errors></td>
								  </tr>
								 <tr>
									<td>&nbsp;</td>
								</tr>
								
								
								<tr>
									<td height="50" colspan="4" align="center"><label>
											<input type="submit" name="Submit" class="btn"  value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onclick="doRefresh(true);populateDistricts();" /> </label><label>
											<input type="button" name="Submit3" class="btn"	value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:location.href='login.do?<csrf:token uri='login.do'/>';" /> </label></td>

								</tr>
							</table>
						</div>
					</div>
				
					<div class="frmpnlbg" id='changevillage' style=" visibility: hidden; display:none">
						 <div class="frmtxt" align="center">
						 <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
						<table width="580" class="tbl_no_brdr">
							<tr>
								<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
									           <form:select htmlEscape="true" path="districtNameEnglish" class="combofield" style="width: 150px" id="ddSourceDistrict" onchange="getSubDistrictList(this.value);document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
									           </form:select> <span class="error" id="ddSourceDistrict_error"></span>
									           <form:errors path="districtNameEnglish" class="errormsg"></form:errors></td>
							</tr>

							<tr>
								<td class="lblBold" align="left"><label><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
									<form:select htmlEscape="true" path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" >
									</form:select> <span class="error" id="ddSourceSubDistrict_error"></span>
									<form:errors path="subdistrictNameEnglish" class="errormsg"></form:errors></td>
							</tr>
							<tr>
									<td height="50" colspan="4" align="left"><label>
											<input type="button" name="Submit" class="btn"  onclick="validate(1);" value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>  /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message> onClick="doRefresh(true);populateDistricts();" /> </label><label>
											<input type="button" name="Submit3" class="btn"	value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label></td>

							</tr>
							<tr style="visibility: hidden;">
								<td width="14%" rowspan="9">&nbsp;</td>
								<td width="86%" class="lblBold" align="left"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><br />
									<form:select htmlEscape="true" path="stateNameEnglish" class="combofield" style="width: 150px" id="ddSourceState" onchange="getDistrictList(this.value);document.getElementById('ddSourceState').selectedIndex==0?doRefresh(true):false;">
									            <form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
									            <form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select> <span class="error" id="ddSourceState_error"></span>
							</tr>
					  </table>
					</div>
				  </div>

			
			      <c:if test="${! empty SEARCH_VILLAGE_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
							
								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="5%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												<td width="21%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message></td>
												<td colspan="4" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
												
								</tr>
								<tr class="tblRowTitle tblclear">

												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></td>
												<%-- <td width="6%" align="center"><spring:message htmlEscape="true" code="Label.GOVTORDER"></spring:message></td> --%>
												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.HISTORY"></spring:message></td>
												<%-- <td width="6%" align="center"><spring:message htmlEscape="true" code="Label.MAP"></spring:message></td> --%>
												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></td>
												<td width="6%" align="center"><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></td>
											
								</tr>
															
									<c:forEach var="listVillageDetail" varStatus="listVillageRow" items="${SEARCH_VILLAGE_RESULTS_KEY}">
													<tr class="tblRowB">
														<td width="6%"><c:out value="${offsets*limits+(listVillageRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${listVillageDetail.villageCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listVillageDetail.villageNameLocal}" escapeXml="true"></c:out></td>
														<%-- <td align="center"><a href="viewVillageDetail.htm?id=${listVillageDetail.villageCode}"><img	src="images/view.png" width="18" height="19" border="0" /></a></td> --%>
														<td align="center"><a href="globalviewVillageDetail.htm?id=${listVillageDetail.villageCode}&<csrf:token uri='globalviewVillageDetail.htm'/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
														<!-- <td align="center"><a href="#"><img src="images/gvt.order.png" width="18" height="19" /></a></td> -->
														<td align="center"><a href="viewVillageHistory.htm?id=${listVillageDetail.villageCode}&<csrf:token uri='viewVillageHistory.htm'/>"><img	src="images/history.png" width="18" height="19" border="0" /></a></td>
														<!-- <td align="center"><a href="#"><img src="images/map.png" width="18" height="19" /></a></td> -->
														<td align="center"><a href="modifyVillageCrbyId.htm?id=${listVillageDetail.villageCode}&<csrf:token uri="modifyVillageCrbyId.htm"/>"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td> 
														<td align="center"><a href="modifyVillagechangebyId.htm?id=${listVillageDetail.villageCode}&<csrf:token uri='modifyVillagechangebyId.htm'/>"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td> 
														
													</tr>
								  </c:forEach>											
								</table>
							  </td>
						   </tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td height="30" align="right">
									     <table width="301">
											<tr>
												<td width="150" align="right" class="pageno"><c:out escapeXml="false" value="${villageCount}"></c:out></td>
												<td width="96" align="right" class="pre"><a href="#" onclick="setDirection(-1)"><spring:message htmlEscape="true" code="Label.PREVIOUS"></spring:message></a></td>
												<td width="24" align="center" class="pageno">|</td>
												<td width="51" align="right" class="nxt tblclear"><a href="#" onclick="setDirection(+1)"><spring:message htmlEscape="true" code="Label.NEXT"></spring:message></a></td>
												<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						  </div>
					    </div>
					 </c:if>

				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty SEARCH_VILLAGE_RESULTS_KEY}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:if>
				</c:if>
			     <input type="hidden" name="direction" id="direction" value="0" />
			     <input type="hidden" name="pageType" value="V" />
				  </form:form>	
				  	 <script src="/LGD/JavaScriptServlet"></script>
			  </div>
		</div>
</body>
</html>

