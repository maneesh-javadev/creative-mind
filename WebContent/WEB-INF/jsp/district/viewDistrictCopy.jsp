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
//jquery pagination  
$(document).ready(function() {
	/* $('#modifydistrictCorrectiontable').dataTable({
	}); */
	
	$('#modifydistrictCorrectiontable').DataTable({
		"bJQueryUI" : false,
		 "aLengthMenu": [[10, 50, 100, 500], [10, 50, 100, 500]],
	        "iDisplayLength": 50,
	        "scrollX": true
	});
});

var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
	if( isEmptyObject(entityCode) ){
		customAlert("No Record(s) found.");
		return false;
	}
	$('#myIframe').attr('src',  cusurl + "?" + paramName + "=" + entityCode+ "&<csrf:token uri='" + cusurl + "'/>");
	$('#dialogBXTitle').text('<spring:message code="Label.MANAGEDISTRICT" htmlEscape="true"></spring:message>');
	$('#dialogBX').modal('show'); 
}
//jquery pagination  
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
					alert(result);
				}else{
					$("#dialogBXTitle").text(' GIS Map View of '+districtNameEnglish+' District');
					 $('#myIframe').attr('src', result);
					$('#dialogBX').modal('show');	
					/* $("#dialogBX").dialog({
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
					});	 */
					
					
			 }  
		}
		},
		errorHandler : function( errorString, exception){
			alert(exception);
		},
		async : true
	});		
};

customAlert 
</script>

<title><spring:message htmlEscape="true"  code="Label.VIEWDIST"></spring:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadPage();">
	 <section class="content">
		 <div class="row">
		 	<section class="col-lg-12 ">
	        	<div class="box ">
		        	<div class="box-header with-border">
		            	<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MANAGEDISTRICT"></spring:message></h3>
		            </div>
		            <div class="box-body">
	            		<div class="box-header subheading">
                        	<h4><spring:message htmlEscape="true" code="Label.DISTRICTDETAIL"></spring:message></h4>
		                </div>
						<div class="row">
					    	<div class="col-sm-12 ">
						  		<form:form action="viewdistrict.htm" id="form1" method="POST" commandName="districtbean">	
								<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewdistrict.htm"/>"/>
				 
					
							 <c:if test="${! empty SEARCH_DISTRICT_RESULTS_KEY}">
						  		<table id="modifydistrictCorrectiontable" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true" 	code="Label.DISTRICTCODE"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINENGLISH"></spring:message></th>
												<th  rowspan="2"><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEINLOCAL"></spring:message></th>
												<th colspan="5" align="center"><spring:message htmlEscape="true"  code="Label.ACTION"></spring:message></th>
												
								          </tr>
											<tr>

												<th align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></th>
												<%-- <th align="center"><spring:message htmlEscape="true"  code="Label.HISTORY"></spring:message></th> --%>
												<th align="center"><spring:message htmlEscape="true"  code="Label.CORRECTION"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.CHANGE"></spring:message></th>
												<th align="center"><spring:message htmlEscape="true"  code="Label.CHANGE.EFFECTIVE.DATE"></spring:message></th>
												
												<!-- <th>GIS Map View</th> -->
											</tr>
										</thead>
										<tbody>
											 <c:forEach var="districtWiseEntityDetails" varStatus="listDistrictRow" items="${SEARCH_DISTRICT_RESULTS_KEY}">
													<tr>
														<td align="center" ><c:out value="${offsets*limits+(listDistrictRow.index+1)}" escapeXml="true"></c:out></td>
														<td align="center" ><c:out value="${districtWiseEntityDetails.districtCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtWiseEntityDetails.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${districtWiseEntityDetails.districtNameLocal}" escapeXml="true"></c:out></td>
														
														<%-- <td width="8%"  align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('viewDistrictDetail.htm',${districtWiseEntityDetails.districtCode},'A');"><i class="fa fa-eye" aria-hidden="true"></i></a></td> 
														 --%>
														<td width="8%" align="center"><a href="#" onclick="javascript:viewEntityDetailsInPopup('${districtWiseEntityDetails.districtCode}', 'viewDistrictDetail.htm', 'id');"><i class="fa fa-eye" aria-hidden="true"></i></a>
													    </td> 
														
<%-- 														<td  align="center"><a href="#" onclick="javascript:manageDistrict1('viewDistrictHistory.htm',${districtWiseEntityDetails.districtCode},'A');"><i class="fa fa-history" aria-hidden="true"></i></a></td> --%>
														<td   align="center"><a href="#" onclick="javascript:manageDistrict1('modifyDistrictCrbyId.htm',${districtWiseEntityDetails.districtCode},'${districtWiseEntityDetails.operation_state}');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
														</td>
														<td  align="center"><a href="#" onclick="javascript:manageDistrict1('modifyDistrictchangebyId.htm',${districtWiseEntityDetails.districtCode},'${districtWiseEntityDetails.operation_state}');"><i class="fa fa-pencil"  aria-hidden="true"></i></a></td>
														
														
														<td  align="center"><a href="#" onclick="javascript:manageDistrict1('modifyDistrictChangeEffectiveDate.htm',${districtWiseEntityDetails.districtCode},'${districtWiseEntityDetails.operation_state}');"><i class="fa fa-pencil"  aria-hidden="true"></i></a></td>
														
														
														
														<%-- <td align="center">
														<a href="#" onclick="callGISMapView('${districtWiseEntityDetails.districtCode}','${districtWiseEntityDetails.districtNameEnglish}','MDT')">
														<i class="fa fa-map-marker" aria-hidden="true"></i></a>
															 <img id ="gisMapView" src="images/showMap.jpg" width="18" height="19" border="0" onclick="callGISMapView('${districtWiseEntityDetails.districtCode}','${districtWiseEntityDetails.districtNameEnglish}','MDT')" />
														
														 </td> --%>
													</tr>
								 		 	</c:forEach>	
									</tbody>
									</table>
									<form:input path="districtId" type="hidden" name="districtId" id="districtId"  />
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
			    
					<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
						<div class="modal-dialog" style="width:1050px;height:700px">
								<div class="modal-content">
					  				<div class="modal-header">
					   				 
					    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
					    			  	 <button type="button" class="close" data-dismiss="modal">&times;</button>
					  				</div>
					  				<div class="modal-body" id="dialogBXbody">
					        			<iframe id="myIframe" width="1010" height="690"></iframe>
					  				</div>
					     			 <div class="modal-footer">
					        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      
					      			</div>
							</div>
						</div>
					</div>
					
					
			     </form:form>	
			      <script src="/LGD/JavaScriptServlet"></script>
						  	</div>
					  </div>
				   </div>
					 
        		</div>
            </section>
		 </div>
	 
	 </section>	
		

</body>
</html>

