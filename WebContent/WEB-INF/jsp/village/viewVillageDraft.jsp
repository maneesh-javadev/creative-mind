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
			
			"sScrollY": "100%",
			"bScrollCollapse": false,
			"bPaginate": true,
			"aoColumnDefs": [
				{ "sWidth": "10%", "aTargets": [ -1 ] }
			],
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [
							null,
							null,
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

function manageVillage(url,id)
{
		dwr.util.setValue('villageId', id, {	escapeHtml : false	});
		document.getElementById('form1').method = "GET";
		document.getElementById('form1').action = url;
		document.getElementById('form1').submit();
}


function getSubDistrictListAuthenticated(id) {
	if (id!=0)
		lgdDwrSubDistrictService.getSubDistrictList(id, {
			callback : handleSubDistrictSuccessAuthenticated,
			errorHandler : handleSubDistrictErrorAuthenticated
		});
	
}

function handleSubDistrictSuccessAuthenticated(data) {
	// Assigns data to result id	
	var fieldId = 'ddSourceSubDistrict';
	var valueText = 'subdistrictCode';
	var nameText = 'subdistrictNameEnglish';
		
	dwr.util.removeAllOptions(fieldId);	
	var st = document.getElementById('ddSourceSubDistrict');
	dwr.util.removeAllOptions(fieldId);
	st.add(new Option('------------Select------------', '0'));	
	
	dwr.util.addOptions(fieldId, data,valueText,nameText);
	
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
	
	if (val==1)
		{
		if (document.getElementById('ddSourceDistrict').selectedIndex>=0 && document.getElementById('ddSourceSubDistrict').selectedIndex>0)			
			document.forms['form1'].submit();
	else
		alert('please select the value from dropdown');
		}
	else
		{
		if (document.getElementById('ddSourceSubDistrict').selectedIndex>0)			
			document.forms['form1'].submit();
	else
		alert('please select the value from dropdown');
		
		}
	
		
}

function populateDistricts()
{
  
	stateCode = document.getElementById('hdnStateCode').value;
	getDistrictbyhierarchy(stateCode);
}

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
$(function() {							
	$("#dialog").dialog({
		autoOpen : false,
		height : 450,
		width : 400,
		modal : true,
		title : 'Invalidate Village List',
		show : {
			effect : "blind",
			duration : 1000,
		},
		hide : {
			effect : "blind",
			duration : 1000
		},
		buttons : {
			close : function(){
				$( "#tblSample tbody" ).empty();
				$(this).dialog("close");
			}
		}
	});
});

function viewVillageData(villageCode,villageNameEnglish){
	try{
	 
	 $( "#dialog" ).dialog( "open" );
	 var villageNameEnglishArr = villageNameEnglish.split(",");
	  var inValidateVillageCode= villageCode.split(",");
	  for(var i=0;i<inValidateVillageCode.length;i++){
		  $( "#tblSample tbody" ).append( "<tr>" +
		          "<td>" + inValidateVillageCode[i]+ "</td>" +
		          "<td>" + villageNameEnglishArr[i] + "</td>" +
		        "</tr></br>" );  
	  }
	   
	}catch(e){
		alert(e);
	}
}
							
</script>	

<title><spring:message htmlEscape="true" code="Label.MANAGEVILLAGEDRAFT"></spring:message></title>


</head>
<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();loadRadio();">

<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				<div class="box-header with-border">
			
					<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MANAGEVILLAGEDRAFT"></spring:message></h3>
				</div>
					
				
				<form:form action="viewvillage.htm" id="form1" method="POST" commandName="villagebean">
				   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewvillage.htm"/>"/>
				   <input type="hidden" name="villageId" />
				  
				
				
	<div class="box-body">				       

 
 	 <c:if test="${! empty SEARCH_VILLAGE_RESULTS_KEY}">
		<div >				  
			<div class="row">
				<div class="col-sm-12 ">
					<div class="table-responsive">
					
					<table id="manageVillage" class="table table-bordered table-striped dataTable no-footer" cellspacing="0" width="100%">
        					<thead>
								<tr>
									<th  rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
									<th  rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></th>
									<th rowspan="2"><spring:message htmlEscape="true" code="Label.OPERATION"></spring:message></th>
									<th colspan="4" style="text-align: center;"><spring:message htmlEscape="true" code="Label.ACTION"></spring:message></th>
								</tr>
								<tr>
									<th ><spring:message htmlEscape="true" code="Label.EDIT"></spring:message></th>
		         										<th ><spring:message htmlEscape="true" code="Label.PUBLISH"></spring:message></th> 
		         										<th><spring:message htmlEscape="true" code="Label.DELETE"></spring:message></th> 
								</tr>
							</thead>
							<tbody>
									<c:forEach var="listVillageDetail" varStatus="listVillageRow" items="${SEARCH_VILLAGE_RESULTS_KEY}">
									<tr class="tblRowB" >
										<td><c:out value="${offsets*limits+(listVillageRow.index+1)}" escapeXml="true"></c:out></td>
													<%-- 	<td><c:out value="${listVillageDetail.villageCode}" escapeXml="true"></c:out></td> --%>
														<c:choose>
															<c:when test="${listVillageDetail.operationCode==14}">
																<td align="left" onclick="viewVillageData('${listVillageDetail.invalidateVillageList}','${listVillageDetail.invalidateVillageListNameEnglish}');">
																	<c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out>
																</td>
															</c:when>
															<c:otherwise>
																<td align="left"><c:out value="${listVillageDetail.villageNameEnglish}" escapeXml="true"></c:out></td>	
															</c:otherwise>
														</c:choose>
														
														<c:if test="${listVillageDetail.operationCode==10}">
															<td align="left">Create Village</td>
															<td  align="center"><a href="#"><img src="images/edit_icon.png" onclick="javascript:manageVillage('viewVillageDraftDetail.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/publish.png" onclick="javascript:manageVillage('publichModifyVillage.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
														</c:if>
														<c:if test="${listVillageDetail.operationCode==11}">
														<td align="left">Rename Village</td>
															<td  align="center"><a href="#"><img src="images/edit_icon.png" onclick="javascript:manageVillage('renameModifyVillage.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/publish.png" onclick="javascript:manageVillage('renameModifyVillagePublish.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
														</c:if>
														<c:if test="${listVillageDetail.operationCode==14}">
														<td align="left">Invalidate Village</td>
															<td  align="center"><a href="#"><img src="images/edit_icon.png" onclick="javascript:manageVillage('invalidateModifyVillage.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
															<td align="center"><a href="#"><img src="images/publish.png" onclick="javascript:manageVillage('invalidatedModifyVillagePublish.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
														</c:if>
														<td  align="center"><a href="#"><img src="images/delete.png" onclick="javascript:manageVillage('deleteDraftModifyVillage.htm',${listVillageDetail.villageCode});" width="18" height="19" border="0" /></a></td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					
					
						
						 <div class="box-footer">
						  <div class="col-sm-offset-2 col-sm-10"> 
						  <div class="pull-right">
							  <button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='manageDraftVillage.htm?<csrf:token uri='manageDraftVillage.htm'/>';"><spring:message htmlEscape="true" code="Button.BACK"></spring:message></button>
							  <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
						</div>
						</div>
					  </div>
					</div>
				</div>
			</div>
 		</div>
	</c:if>


			

		</div>
				
				<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:1050px;height:700px">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="1000" height="650"></iframe>
								        			
								     			 
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

