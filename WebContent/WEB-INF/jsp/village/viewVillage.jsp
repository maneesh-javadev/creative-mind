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

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
<script src="js/shiftdistrict.js"></script>
<script type="text/javascript" charset="utf-8">

//jquery pagination  
	$(document).ready(function() {
		$('#modifyVillageCorrectiontable').dataTable({
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

function manageVillage(url,id,optState)
{
	
		dwr.util.setValue('villageId', id, {	escapeHtml : false	});
		document.getElementById('form1').method = "GET";
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
}

function manageVillage1(url,id,optState,operationState)
{
	if(optState=='P' && operationState=='A'){
		dwr.util.setValue('villageId', id, {	escapeHtml : false	});
		document.getElementById('form1').method = "GET";
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
	}else if(optState=='S' || operationState=='F'){
		draftModeAlert();
		return false;
	}
}


function getSubDistrictListAuthenticated(id) {
	if (id!=0)
		lgdDwrSubDistrictService.getSubDistrictListForLocalbody(id, {
			callback : handleSubDistrictSuccessAuthenticated,
			errorHandler : handleSubDistrictErrorAuthenticated
		});
	
}

function handleSubDistrictSuccessAuthenticated(data) {
	// Assigns data to result id	
	var fieldId = 'ddSourceSubDistrict';
	dwr.util.removeAllOptions(fieldId);	
	var st = document.getElementById('ddSourceSubDistrict');
	st.add(new Option('------------Select------------', '0'));	
	
	var options = $("#ddSourceSubDistrict");
	$.each(data, function(key, obj) {
		var option = $("<option/>");
		if (obj.operation_state == 'F') {
			$(option).attr('disabled', 'disabled');
		}
		$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
		options.append(option);
	});
	
}

function handleSubDistrictErrorAuthenticated() {
	// Show a popup message
	window.open ('infringement.htm','_self',false);
}

function putValue(val){
	  document.getElementById('filterOption').value = val;
}

var stateCode; 
function setDirection(val)
{	
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewVillagePagination.htm?<csrf:token uri='viewVillagePagination.htm'/>";
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
	
	var dlc='${districtCode}';
	
	if (val==1){
		if (document.getElementById('ddSourceDistrict').selectedIndex <= 0 && dlc==0 ){
			alert('Please Select District');
		}else if(document.getElementById('ddSourceSubDistrict').selectedIndex <= 0){
			alert('Please Select SubDistrict');
		}else{
			document.forms['form1'].submit();
		}	
}
}

function populateDistricts()
{
  
	stateCode = document.getElementById('hdnStateCode').value;
	getDistrictbyhierarchy(stateCode);
}

var callGISMapView = function ( subdistrictCode,villageCode, villageName,isShowOnlyBoundary){
	lgdDwrVillageService.getMappedVillageForGIS(parseInt(subdistrictCode),parseInt(villageCode),villageName,null,isShowOnlyBoundary, {
		callback : function( result ){
			//alert(result);
			 if("FAILED" == result){
				customAlert(result);
			}else{
				$("#dialogBX").dialog({
					title:' GIS Map View of '+villageName+' Village',
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
		},
		errorHandler : function( errorString, exception){
				customAlert(exception);
		},
		async : true
	});		
};

</script>
 <script>		
function loadRadio(){			
	              
                       if('${filterValue}' == '1'){
							     document.getElementById("chkchvillage").checked=true;		
								}    
				  
							      if('${filterValue}' == '0'){
							        document.getElementById("chkcrvillage").checked=true;	
							      }
							 
							}
</script>	

<title><spring:message htmlEscape="true" code="Label.MANAGEVILLAGE"></spring:message></title>


</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();loadRadio();">

		<div id="frmcontent">
		
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true" code="Label.MANAGEVILLAGE"></spring:message> </label>
			</h3>
			<ul id="showhelp" class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></li>												
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li> --%>
			</ul>
		</div>							 
			
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="viewvillage.htm" id="form1" method="POST" commandName="villagebean">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewvillage.htm"/>"/>
				<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
			</div>   
			<div id="cat">				       
				<div class="clear"></div>
				<c:if test="${ empty SEARCH_VILLAGE_RESULTS_KEY}">
				<div class="frmpnlbg" id='changevillage' >
					<div class="frmtxt">
						 <div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SELPARENT"></spring:message></div>
						  <input type="hidden" name="sty1" id="sty1" value="1"/>
						  	
						<div class="margin_left_10per">
							<ul class="blocklist">
							<c:if test="${empty subDistrictList}">
								<li>
										<label><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
									           
									          <form:select htmlEscape="true" path="districtNameEnglish" class="combofield" style="width: 150px" id="ddSourceDistrict" onchange="getSubDistrictListAuthenticated(this.value);document.getElementById('ddSourceDistrict').selectedIndex==0?doRefresh(false):false;">
									             <c:if test="${districtCode eq 0}">
									           <form:option value="" htmlEscape="true">
													<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
												</form:option>
												</c:if>
									           <%-- <form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options> --%>
									              <c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																			
																</form:option>
															</c:if>
												</c:forEach>	  
									             </form:select> <span class="error" id="ddSourceDistrict_error" htmlEscape="true"></span>
									           <form:errors path="districtNameEnglish" class="errormsg" htmlEscape="true"></form:errors>
								</li>
								</c:if>
								<li>
									<label><spring:message htmlEscape="true" code="Label.SELECTSUBDISTRICT"></spring:message></label><span class="errormsg">*</span><br />
									<form:select htmlEscape="true" path="subdistrictNameEnglish" class="combofield" style="width: 150px" id="ddSourceSubDistrict" >
									 <form:option value="" htmlEscape="true">
													<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
												</form:option>
									<form:options items="${subDistrictList}" itemValue="subdistrictPK.subdistrictCode" itemLabel="subdistrictNameEnglish"  htmlEscape="true"></form:options>
									</form:select> <span class="error" id="ddSourceSubDistrict_error"  htmlEscape="true"></span>
									<form:errors path="subdistrictNameEnglish" class="errormsg"  htmlEscape="true"></form:errors>
								</li>
								<li>
										<c:if test="${empty subDistrictList}"><label>
									
											<input style="width: 65px;" type="button" name="Submit" class="btn" onclick="validate(1);" value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>"  /> </label>
											</c:if>
											<c:if test="${empty districtList}">
											<label><input style="width: 65px;" type="button" name="Submit" class="btn" onclick="validate(2);" value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>"  /> </label>
											
											</c:if>
											<label>
																					
											<input style="width: 65px;" type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true" code="Button.CLEAR"></spring:message>" onclick="javascript:location.href='viewvillage.htm?<csrf:token uri='viewvillage.htm'/>';" /> </label><label>
											<input style="width: 65px;" type="button" name="Submit3" class="btn"	value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
								</li>
							</ul>
						</div>
					</div>
				  </div>
				  </c:if>
			
			      <c:if test="${! empty SEARCH_VILLAGE_RESULTS_KEY}">
					<div class="frmpnlbg" id="divData">
						<div class="frmtxt">
							<table width="100%" class="tbl_no_brdr"  >
							
								<tr>
									<td width="14%" align="center">
										<table  cellpadding="0" cellspacing="0" border="0" class="display" id="modifyVillageCorrectiontable">
											<thead>
													<tr class="tblRowTitle tblclear">
														<td  rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
														<td rowspan="2"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
														<td  rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
														<%-- <td rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message></td> --%>
														<td colspan="5" align="center"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></td>
														
													</tr>
													<tr class="tblRowTitle tblclear">
		
														<th><spring:message htmlEscape="true" code="Label.VIEW"></spring:message></th>
														<th><spring:message htmlEscape="true" code="Label.HISTORY"></spring:message></th>
														<th><spring:message htmlEscape="true" code="Label.CORRECTION"></spring:message></th>
														<th><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></th>
														<th>GIS Map View</th>
													</tr>
												</thead>
									
									<tbody>						
									<c:forEach var="listVillageDetail" varStatus="listVillageRow" items="${SEARCH_VILLAGE_RESULTS_KEY}">
									              									               
									               <tr class="tblRowB" >
														<td><c:out value="${offsets*limits+(listVillageRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${listVillageDetail.villageCode}" escapeXml="true"></c:out></td>
													 <c:if test="${listVillageDetail.operation_state =='A'.charAt(0)}" >	
														<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
														</c:if>
													<c:if test="${listVillageDetail.operation_state =='F'.charAt(0)}" >	
														<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out>(Village in draft mode)</td>
													</c:if>
													<td  align="center"><a href="#"><img src="images/view.png" onclick="javascript:manageVillage('viewVillageDetail.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}');" width="18" height="19" border="0" /></a></td>
														<td align="center"><a href="#"><img src="images/history.png" onclick="javascript:manageVillage('viewVillageHistory.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}');" width="18" height="19" border="0" /></a></td>
														<td   align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageVillage1('modifyVillageCrbyId.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}','${listVillageDetail.operation_state}');" width="18" height="19" border="0" /></a></td>
														<td align="center"><a href="#"><img src="images/edit.png" onclick="javascript:manageVillage1('modifyVillagechangebyId.htm',${listVillageDetail.villageCode},'${listVillageDetail.opeartion_state}','${listVillageDetail.operation_state}');" width="18" height="19" border="0" /></a></td>
 		 												<td align="center">
 		 												<c:if test="${listVillageDetail.operation_state =='A'.charAt(0)}" >	
														<img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" onclick="callGISMapView('${subDistrictCode}','${listVillageDetail.villageCode}','${listVillageDetail.villageNameEnglish}','MV')" />
														</c:if>
														</td>
													</tr>
													
								  </c:forEach>	
								  </tbody>
								  
								  										
								</table>
								  <form:input path="villageId" type="hidden" name="villageId" id="villageId"  />	
							  </td>
						   </tr>
						<tr>
							<td>&nbsp;</td>
						</tr>						
					</table>
				</div>
			 </div>
		 </c:if>

				<c:if test="${fn:length(viewPage) > 0}">
					<c:if test="${empty SEARCH_VILLAGE_RESULTS_KEY}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
										<spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message>	
							</div>
						</div>
					</c:if>
				</c:if>
			   
			     <input type="hidden" name="pageType" value="V" />
						     						   
							    <script>		
							   <c:if test="${filterValue == 1}">
							      populateDistricts();
							      var s = document.getElementById("ddSourceDistrict");
							    
							    	    for ( var i = 0; i < s.options.length; i++ ) {							    	    	
							    	        if ( s.options[i].value == '<c:out value="${districtId}" escapeXml="true"></c:out>' ) {
							    	            s.options[i].selected = true;
							    	           
							    	        }
							    	}							    	    
							     getSubDistrictListAuthenticated('${districtId}');
							   
							     var t = document.getElementById("ddSourceSubDistrict");
							    
							     for ( var j = 0; j < t.options.length; j++ ) {							    	
						    	        if ( t.options[j].value == '<c:out value="${subDistrictId}" escapeXml="true"></c:out>' ) {						    	        	
						    	            t.options[j].selected = true;
						    	           
						    	        }
						    	}								   
							   </c:if> 
							   <c:if test="${districtCode ne 0}">	
						   		  getSubDistrictListAuthenticated('${districtCode}');
						   		  </c:if>					
							  </script>	
					</div>		
				  </form:form>	
				  	 <script src="/LGD/JavaScriptServlet"></script>
			  </div>
		</div>
</body>
</html>

