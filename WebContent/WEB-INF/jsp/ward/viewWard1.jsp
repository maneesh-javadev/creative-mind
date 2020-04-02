 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*" %>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<script type="text/javascript" src="js/cancel.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/viewLocalbody.js"></script>
<script type="text/javascript" src="js/GovtLocalBody.js"></script>

<link href="css/error.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>

<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
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
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService .js'></script> 
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
	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService .js'></script>

<!--  <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" src="js/ministry.js"></script>

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);

 </script>	

<script type="text/javascript" language="javascript">

/* function doRefresh()
{
    alert("JAI SIYA RAM");
	document.getElementById('tierSetupCode').selectedIndex=0;
	document.getElementById('ddSourceLocalBody').selectedIndex=0;
} */


function doRefresh(removeAll)
{
	alert("JAI SIYA RAM");
	if (removeAll)
		{
			document.getElementById('tierSetupCode').selectedIndex=0;
			removeSelectedValue('ddSourceLocalBody');
		}

}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
   	elSel.remove(i);
}
</script>
</head>

<body onload="loadviewLBPage();" oncontextmenu="return false" 
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);" >
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.VIEWWARD"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" />
					</a>
					</td>
					
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Label.HELP"></spring:message> --%>
					</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="viewWardPRIAndTRI.htm" method="POST" id="form1" 
				name="form1" commandName="localGovtBodyForm">
				<input   type='hidden' name="hdnType" id="hdnType" value="<c:out value='${typeCode}'/>"/>
				<input   type='hidden' name="hdnIntermediatePanchayat" id="hdnIntermediatePanchayat" value="<c:out value='${hdnIntermediatePanchayat}'/>"/>
				<input   type='hidden' name="hdnDistrictPanchayat" id="hdnDistrictPanchayat" value="<c:out value='${hdnDistrictPanchayat}'/>"/>
				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>'/>
			
				<div id="cat">
					<div class="frmpnlbg">
						<!-- For Localbody list for different district, Intermediate, Village Panchayat  -->
						<div id='district' class="frmpnlbg">
							<div class="frmtxt">
								<div class="frmhdtitle">
									<spring:message code="Label.SEARCHCRIT"></spring:message>
								</div>
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td width="14%" rowspan="8">&nbsp;</td>
										<td width="86%" class="lblBold"><spring:message
												code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span	class="errormsg">*</span><br /> 
												<form:select path="lgd_LBTypeName" style="width: 150px" id="tierSetupCode" name="tierSetupCode" class="combofield" onchange="getdistrictPanchahyatList(this.value);document.getElementById('tierSetupCode').selectedIndex==0?doRefresh(true):false;">
												<form:option value="">
													<spring:message code="Label.SEL"></spring:message>
												</form:option>
												<c:forEach var="lgtLocalBodyType" varStatus="var" items="${lgtLocalBodyType}">
													<form:option id="typeCode"
														value="${lgtLocalBodyType.localBodyTypeCode}"><c:out value="${lgtLocalBodyType.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
												</c:forEach>
											</form:select> <span id="tierSetupCode_error" class="errormsg"><spring:message
													code="error.blank.viewlgTypeName"></spring:message>
										</span>
										</tr>
										
                                      	<!--    updated         -->		
							
									<tr>
								          <td class="lblBold" align="left"><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message><br />
									           <form:select path="localBodyCode" class="combofield" style="width: 150px" id="ddSourceLocalBody" onchange="document.getElementById('ddSourceLocalBody').selectedIndex==0?doRefresh(true):false;">
									           </form:select> <span class="error" id=""></span></td>
							      </tr>

											</td>
									
									
					
									
									
									<tr>
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

									<tr>
										<td height="50" colspan="4"><label> <input
												type="submit" name="Submit" class="btn" onclick=""
												value="<spring:message code="Button.GETDATA"></spring:message>"
												 /> </label><label> <label><input
													type="button" name="Submit2" class="btn"
													value="<spring:message code="Button.CLEAR"></spring:message>"
													onclick="doRefresh(true);" />
											</label> <label><input type="button" name="Submit3"
													class="btn"
													value="<spring:message code="Button.CLOSE"></spring:message>"
													onclick="javascript:go('home.htm');" />
											</label>
										</td>
									</tr>



								</table>
							</div>
						</div>
						<!-- End here Localbody  -->

					</div>
				</div>
			</form:form>
			<c:if test="${! empty wardList}">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="14%" align="center">
									<table class="tbl_with_brdr" width="98%" align="center">
										<tr class="tblRowTitle tblclear">
											<td rowspan="2"><spring:message code="Label.SNO"></spring:message>
											</td>
											<td width="20%" rowspan="2"><spring:message
													code="Label.WARDNAMEINENG"></spring:message>
											</td>
											<td colspan="21%" rowspan="2"><spring:message
													code="Label.WARDNAMEINLOCAL"></spring:message>
											</td>
											
											<td colspan="6" align="center"><spring:message
													code="Label.ACTION"></spring:message>
											</td>
										</tr>
										<tr class="tblRowTitle tblclear">

											<td width="6%" align="center"><spring:message
													code="Label.VIEW"></spring:message>
											</td>
											<td width="6%" align="center"><spring:message
													code="Label.DELETE"></spring:message>
											</td>
										</tr>


										
											<c:forEach var="wardList" varStatus="listLocalBodyRow" items="${wardList}">
												<tr class="tblRowB">
													<td width="6%"><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
													<td width="16%" align="left"><c:out
															value="${wardList.wardNameInEnglish}" escapeXml="true"></c:out>
													</td>
													<td colspan="21%" align="left"><c:out
															value="${wardList.wardNameInLocal}" escapeXml="true"></c:out>
													</td>
													<td align="center"><a href="viewWardDetails.htm?id=${wardList.wardCode}&<csrf:token uri='viewWardDetails.htm'/>"><img	src="images/view.png" width="18" height="19" border="0" /></a></td>
													 <td align="center"><a href="modifyWard.htm?id=${wardList.wardCode}"><img	src="images/edit.png" width="18" height="19" border="0" /></a></td>  
													<td align="center"><a href="invalidateWard.htm?wardCode=${wardList.wardCode}"><img src="images/delete.png" width="18" height="19" border="0" /></a></td>
												
												</tr>
											</c:forEach>
									
                                      <c:if test="${fn:length(viewPage) > 0}">
								<c:if test="${empty wardList}">
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true" code="Error.noresult"></spring:message></td>
									</tr>
								</c:if>
							</c:if>

									</table></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							<tr>
								<td height="30" align="right"><table width="301"
										class="tbl_no_brdr">
										<tr>
											<td width="99" align="right" class="pageno">(1 - 50 of
												464)</td>
											<td width="96" align="right" class="pre"><a href="#"><spring:message
														code="Label.PREVIOUS"></spring:message>
											</a>
											</td>
											<td width="24" align="center" class="pageno">|</td>
											<td width="51" align="right" class="nxt tblclear"><a
												href="#"><spring:message code="Label.NEXT"></spring:message>
											</a>
											</td>
											<td width="16" align="right" class="nxt tblclear">&nbsp;</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div>
				</div>
             	</c:if>
		</div>
	</div>
</body>
</html>

 