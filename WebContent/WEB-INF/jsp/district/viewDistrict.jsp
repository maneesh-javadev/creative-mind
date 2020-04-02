<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'/>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrSubDistrictService.js'/>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'/>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>





<script type="text/javascript">
var table =null;
//jquery pagination  
$(document).ready(function() {
	
 table =$('#modifydistrictCorrectiontable').dataTable({
		
		 "pageLength": 10,
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
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
	
	 
	/* $('#modifydistrictCorrectiontable').dataTable({
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
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false }
			  			
			  			],
		"sPaginationType": "full_numbers"
	}); */
} );
// jquery pagination  

function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
function manageDistrict(url,id)
{
	dwr.util.setValue('districtId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
}


function manageDistrict1(url,id,operationState)
{
	dwr.util.setValue('districtId', id, {	escapeHtml : false	});
	
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
	document.forms['form1'].action = "viewDistrictPagination.htm?<csrf:token uri='viewDistrictPagination.htm'/>";
	document.forms['form1'].submit();
}

function go1(id)
{
	var url="viewdistrictbystatecode.htm?id="+id;
	window.location=url;
	}
dwr.engine.setActiveReverseAjax(true);
function getData()
{
	var chkcrdistrict=document.getElementById('chkcrdistrict');
	var chkchdistrict=document.getElementById('chkchdistrict');
	
	if(chkcrdistrict.checked)
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
	else if(chkchdistrict.checked)
	{
		document.getElementById('text1').value='';
		document.getElementById('text2').value='';
		document.forms['form1'].submit();
		chkchdistrict.checked=true;
		chkcrdistrict.checked=false;
	}
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

function loadPage()
{	
document.getElementById('chkcrdistrict').checked=false;
document.getElementById('chkchdistrict').checked=false;
if(document.getElementById('text1').value!=''|| document.getElementById('text2').value!='')
	{
	document.getElementById('chkcrdistrict').checked=true;
	document.getElementById('chkchdistrict').checked=false;
	}
else if(document.getElementById('ddSourceState').value!=0)
	{
	document.getElementById('chkcrdistrict').checked=false;
	document.getElementById('chkchdistrict').checked=true;
	}

	if (document.getElementById('tblrows')==null)
		{
			document.getElementById('chkcrdistrict').checked = true;
			toggledisplay3("chkcrdistrict","showbytext");
			doRefresh();
		}
	else
		document.getElementById('chkcrdistrict').checked = false;
		
	}
	
function doRefresh()
{
	document.getElementById('text1').value=document.getElementById('text2').value='';
	document.getElementById('ddSourceState').selectedIndex=0;
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
		if (document.getElementById('ddSourceState').selectedIndex>0)			
			document.forms['form1'].submit();
		else
			alert('please select the value from dropdown');
}

var callGISMapView = function (districtCode,districtNameEnglish,isShowOnlyBoundary){
	lgdDwrDistrictService.getDistrictForGIS(parseInt(districtCode),districtNameEnglish,isShowOnlyBoundary, {
		callback : function( result ){
			
			 if("mapNtFin" == result)
				alert("Map is not finalised !");
			else{ 
				 if("FAILED" == result){
					customAlert(result);
				}else{
					$("#dialogBX").dialog({
						title:' GIS Map View of '+districtNameEnglish+' District',
					    modal: true,
					    width:950,
					    height: 700,
					    resizable:false,
					    open: function(ev, ui){
					    	 showLoadingImage();
							
				             $('#myIframe').attr('src', result);
				             $("#myIframe").load(function(){
				            	 hideLoadingImage(); 
						    });
				    	}
					});	
			 }  
		}
		},
		errorHandler : function( errorString, exception){
				customAlert(exception);
		},
		async : true
	});		
};
</script>

<title><spring:message htmlEscape="true"  code="Label.VIEWDIST"></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
		<div id="frmcontent">
			
			<div class="frmhd">
						<h3 class="subtitle"><spring:message code="Label.VIEWDIST" htmlEscape="true"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				      <%--   //this link is not working  <li>
					 				        <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg"	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>                     
					 				        </li>
					 				       //this link is not working <li>    
					 				       		<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
					 				       </li>
					 				       <li>
					 				       		<a href="#" class="frmhelp">Help</a>
					 				       </li> --%>
					 					  </ul>
			
		</div>
			<div class="clear"></div>
			<div class="frmpnlbrdr">
				<form:form action="viewdistrict.htm" id="form1" method="POST" commandName="districtbean">	
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdistrict.htm"/>"/>
				 
					
					<c:if test="${! empty SEARCH_DISTRICT_RESULTS_KEY}">
					<!-- Block for Drafted Local Government Body Details in Tabular Format. -->
					<div class="form_block">
						<div class="col_1 overflow_horz">
							<h4><spring:message htmlEscape="true"  code="Label.DISTRICTDETAIL"></spring:message></h4>
							<ul class="form_body">
								<li>
									<table id="modifydistrictCorrectiontable" class="display" cellspacing="0" width="100%">
										<thead>
											<tr class="tblRowTitle tblclear">
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DISTRICTCODE"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINENGLISH"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINLOCAL"></spring:message></th>
												<th colspan="5" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
												
								          </tr>
											<tr class="tblRowTitle tblclear">

												<th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></th>
												<th>GIS Map View</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="districtWiseEntityDetails" varStatus="listDistrictRow" items="${SEARCH_DISTRICT_RESULTS_KEY}">
													<tr class="tblRowB">
														<td align="center" ><c:out value="${offsets*limits+(listDistrictRow.index+1)}" escapeXml="true"></c:out></td>
														<td align="center" ><c:out value="${districtWiseEntityDetails.districtCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtWiseEntityDetails.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtWiseEntityDetails.districtNameLocal}" escapeXml="true"></c:out></td>
														
														<td   align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageDistrict1('viewDistrictDetail.htm',${districtWiseEntityDetails.districtCode},'A');" width="18" height="19" border="0" /></a></td>
														<td  align="center"><a href="#"><img src="images/history.png" onclick="javascript:manageDistrict1('viewDistrictHistory.htm',${districtWiseEntityDetails.districtCode},'A');" width="18" height="19" border="0" /></a></td>
														<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDistrict1('modifyDistrictCrbyId.htm',${districtWiseEntityDetails.districtCode},'${districtWiseEntityDetails.operation_state}');" width="18" height="19" border="0" /></a></td>
														<td  align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageDistrict1('modifyDistrictchangebyId.htm',${districtWiseEntityDetails.districtCode},'${districtWiseEntityDetails.operation_state}');" width="18" height="19" border="0" /></a></td>
														<td align="center">
															<img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" onclick="callGISMapView('${districtWiseEntityDetails.districtCode}','${districtWiseEntityDetails.districtNameEnglish}','MDT')" />
														</td>
													</tr>
								 		 	</c:forEach>	
									</tbody>
									</table>
									<form:input path="districtId" type="hidden" name="districtId" id="districtId"  />	
									
								</li>
							</ul>
						</div>
					</div>
		
					</c:if>
					
							<c:if test="${fn:length(viewPage) > 0}"> 
							   <c:if test="${empty SEARCH_DISTRICT_RESULTS_KEY}">
								<div class="frmpnlbg" id="divData">
									<div class="frmtxt">
										<table width="100%" class="tbl_no_brdr">					
										<tr>
											<td colspan="4" align="center"><spring:message htmlEscape="true"  code="error.NODISTFOUND"></spring:message></td>
										</tr>					
									</table>
									</div>
							     </div>
							   </c:if>
						     </c:if>
			     <input type="hidden" name="direction" id="direction" value="0" />
			     <input type="hidden" name="pageType" value="D" />
			     <div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
				</div>
			     </form:form>	
			      <script src="/LGD/JavaScriptServlet"></script>		
			   </div>			
		</div>
		
<%-- <c:if test="${isDistrictUser}">
	<script>
	    var docform = document.getElementById("form1"); 
		docform.method = "post";
		docform.action = "viewdistrict.htm?<csrf:token uri='viewdistrict.htm'/>";
		docform.submit();
	</script>
</c:if>		 --%>
</body>
</html>

