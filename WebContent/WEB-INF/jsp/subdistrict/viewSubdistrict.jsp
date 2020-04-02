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


<!-- jquery pagination  -->



<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.min.js"></script>
<script src="<%=contextpthval%>/datatable/TableTools.js" charset="utf-8" type="text/javascript"></script>
	<link href="<%=contextpthval%>/datatable/jquery-ui-1.8.7.custom.css" rel="stylesheet"  type="text/css" />
	<link href="<%=contextpthval%>/datatable/demo_table_jui.css" rel="stylesheet"  type="text/css" />
	<!-- jquery pagination  -->


<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
<script type="text/javascript">

//jquery pagination  
$(document).ready(function() {
	$('#modifysubdistrictCorrectiontable').dataTable({
		"sScrollY": "200px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			],
		"sPaginationType": "full_numbers"
	});
} );
// jquery pagination  


function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
function manageSubDistrict(url,id)
{
	dwr.util.setValue('subdistrictId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}

function manageSubDistrict1(url,id,operationState)
{
	dwr.util.setValue('subdistrictId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	if(operationState=='A'){
	 	
			document.getElementById('form1').action = url;
			document.getElementById('form1').submit();
	 	
		displayLoadingImage();
 	}else{
 		draftModeAlert();
 	}
	
	

}


function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewSubDistrictPagination.htm?<csrf:token uri='viewSubDistrictPagination.htm'/>";
	document.forms['form1'].submit();
}

function go1(id)
{
	var url="viewsubdistrictbydistrictCode.htm?id="+id;
	window.location=url;
	}
dwr.engine.setActiveReverseAjax(true);

function getData()
{
	var chkcrsubdistrict=document.getElementById('chkcrsubdistrict');
	var chkchsubdistrict=document.getElementById('chkchsubdistrict');
	if(chkcrsubdistrict.checked)
	{	
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		{
		document.forms['form1'].submit();
		}
	else
		{
		alert('Please enter search text!');
		}
    }

	else if(chkchsubdistrict.checked)
	{
		document.getElementById('text1').value='';
		document.getElementById('text2').value='';
		document.forms['form1'].submit();
		chkchsubdistrict.checked=true;
		chkcrsubdistrict.checked=false;
	}
}	
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function loadPage()
{	
document.getElementById('chkcrsubdistrict').checked=false;
document.getElementById('chkchsubdistrict').checked=false;
if(document.getElementById('text1').value!=''|| document.getElementById('text2').value!='')
	{
	document.getElementById('chkcrsubdistrict').checked=true;
	document.getElementById('chkchsubdistrict').checked=false;
	}
else if(document.getElementById('ddSourceDistrict').value!=0)
	{
	document.getElementById('chkcrsubdistrict').checked=false;
	document.getElementById('chkchsubdistrict').checked=true;
	}

	}
	
function doRefresh(removeAll)
{
	document.getElementById('text1').value=document.getElementById('text2').value='';
	if (removeAll)
		{
			document.getElementById('ddSourceDistrict').selectedIndex=0;
		}
	
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
		if (document.getElementById('ddSourceDistrict').selectedIndex>0)			
			document.forms['form1'].submit();
		else
			alert('please select the value from dropdown');
}
</script>

<title><spring:message htmlEscape="true"  code="Label.VIEWSUBDIST"></spring:message></title>
</head>

<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
		<div id="frmcontent">
			<div class="frmhd">
				<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWSUBDIST"></spring:message></h3>
				<ul class="listing">
					<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li> --%>
				</ul>
			</div>
			<div class="clear"></div>
			
			<div class="frmpnlbrdr">
				<form:form action="viewsubdistrict.htm" id="form1" method="POST" commandName="subdistrictbean">	
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewsubdistrict.htm"/>"/>
				<c:if test="${viewPage==true && empty SEARCH_SUBDISTRICT_RESULTS_KEY }">		
					<div class="frmpnlbg" id='showbydropdown' >
					   <div class="frmtxt">
							<div class="block">
							  <div>
									<ul class="blocklist">
										<li>
											<label><spring:message  htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
								            <form:select path="districtNameEnglish" class="combofield" style="width: 150px" id="ddSourceDistrict" >
								            <form:option selected="selected" value="" label="--select--"></form:option>
								            
								            	<c:forEach items="${districtList}" var="distListvar">
													  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													    <form:option value="${distListvar.districtCode}" disabled="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out> </form:option>
													  </c:if>  
													  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													     <form:option value="${distListvar.districtCode}"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
													  </c:if>
												</c:forEach>
								            <%-- <form:options items="${districtList}" itemValue="districtCode" itemLabel="districtNameEnglish"></form:options> --%>
								            </form:select> <span class="error" id="ddSourceDistrict_error"></span>
								            <form:errors htmlEscape="true"  path="districtNameEnglish" class="errormsg"></form:errors>
										</li>
										<li>
											<label>
											<input type="button" name="Submit" class="btn" onclick="validate(1);" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> /> </label><label>
											<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="javascript:location.href='viewsubdistrict.htm?<csrf:token uri='viewsubdistrict.htm'/>';" /> </label><label>
											<input type="button" name="Submit3" class="btn"	value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}">		
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td width="14%" align="center" >
										<table cellpadding="0" cellspacing="0" border="0" class="display" id="modifysubdistrictCorrectiontable">
											<thead>
												<tr class="tblRowTitle tblclear">
													<td rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
													<td rowspan="2"><spring:message htmlEscape="true" 	code="Label.SUBDISTRICTCODE"></spring:message></td>
													<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
													<td  rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
													<td  colspan="4" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></td>
												</tr>
												<tr class="tblRowTitle tblclear">
													<th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true" code="Label.HISTORY"></spring:message></th>
													<th align="center"><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></th>
													<th  align="center"><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></th>
												</tr>
											</thead>
									<tbody>				
										<c:forEach var="subdistrictWiseEntityDetails" varStatus="listSubdistrictRow" items="${SEARCH_SUBDISTRICT_RESULTS_KEY}">
													<tr class="tblRowB">
														<td ><c:out value="${offsets*limits+(listSubdistrictRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${subdistrictWiseEntityDetails.subdistrictCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${subdistrictWiseEntityDetails.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${subdistrictWiseEntityDetails.subdistrictNameLocal}" escapeXml="true"></c:out></td>
														
														<td align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageSubDistrict1('viewSubDistrictDetail.htm',${subdistrictWiseEntityDetails.subdistrictCode},'A');" width="18" height="19" border="0" /></a></td>
														<td align="center"><a href="#"><img src="images/history.png" onclick="javascript:manageSubDistrict1('viewSubDistrictHistory.htm',${subdistrictWiseEntityDetails.subdistrictCode},'A');" width="18" height="19" border="0" /></a></td>
													    <td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageSubDistrict1('modifySubdistrictCrbyId.htm',${subdistrictWiseEntityDetails.subdistrictCode},'${subdistrictWiseEntityDetails.operation_state}');" width="18" height="19" border="0" /></a></td>
												  		<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageSubDistrict1('modifySubdistrictchangebyId.htm',${subdistrictWiseEntityDetails.subdistrictCode},'${subdistrictWiseEntityDetails.operation_state}');" width="18" height="19" border="0" /></a></td>
	
													</tr>
									   </c:forEach>	
								   </tbody>			
								 </table>
								
							  </td>
							</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
						
						
								</table>
							</div>
						</div>
					</c:if>
					<c:if test="${noRecord==true}">		
					
					<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td colspan="4" align="center"><spring:message htmlEscape="true"  code="error.NOSUBDISTFOUND"></spring:message>
										</td>
									</tr>
								</table>
							</div>
						</div>
					 </c:if>  
					<form:input path="subdistrictId" type="hidden" name="subdistrictId" id="subdistrictId"  />	
			     	<input type="hidden" name="pageType" value="T" />		 
			  </form:form>
					 <script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>

