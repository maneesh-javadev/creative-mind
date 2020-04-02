<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="js/invalidateSubdistrict.js" charset="utf-8"></script>
<script src="js/validation.js"></script>

<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>	 </script> --%>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictGetInvalidateDraft.js'>

dwr.engine.setActiveReverseAjax(true);</script>	
<title>Invalidate Subdistrict</title>

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

function getvillagesInDraftMode(subdistrictCode,name){
	lgdDwrSubDistrictService.getvillagesInDraftMode(subdistrictCode,{
		async: true,
		callback : handleVillageListDraft,
		arg:name,
	});
	
	
}

function handleVillageListDraft(data,name){
	if(data.length >0){
		var text= $("#"+name+" option:selected").text();
		customAlert(data.length+" villages of "+text+" Sub District are in Draft Mode.Kindly first take suitable action(publish the changes) on them in order to continue further");
		$('#ddSubdistrict').val(0);
	}
}


var popupWindow	=	null;
function centeredPopup(url,winName,scroll){
	windowHeight = (screen.height) ? (screen.height-200) : 0;
	windowWidth = (screen.width) ? (screen.width)/4 : 0;
	LeftPosition = (screen.width) ? (screen.width-windowWidth) : 0;
	TopPosition = (screen.height) ? (screen.height-windowHeight)/2 : 0;
	settings = 'height='+windowHeight+',width='+windowWidth+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=no';
	popupWindow = window.open(url,winName,settings);
	}

function doSaveAsDraft()
{
	document.forms['invalidateForm'].action = 'saveAsDraftInvalidateSubdistrict.htm';
	document.forms['invalidateForm'].submit();
}

function getSaveAsDraft()
{
	document.forms['invalidateForm'].action = 'publishSaveAsDraftInvalidateSubdistrict.htm';
	document.forms['invalidateForm'].submit();
}

function getData()
{
	if (document.getElementById('ddDistrict').selectedIndex>0)
		{
			getSubDistrictandULBList(document.getElementById('ddDistrict').value);
			populateSubdistricts();
		}
}

function populateSubdistricts()
{
	lgdDwrSubDistrictGetInvalidateDraft.getInvalidateSubdistricts({
		async: false,
		callback : handleSubDistrictsListSuccess,
	});
}

function handleSubDistrictsListSuccess(data)
{
	document.getElementById('ddSubdistrict').value = data.split('::')[0];
	getVillageList(document.getElementById('ddSubdistrict').value);
	
	if (data.indexOf('#')==-1)
		document.getElementById('ddSubdistrictMergeYes').value = data.split('::')[1];
	else
		{
			document.getElementById('subdistrictdelete_no').checked=true;
			toggledisplay('subdistrictdelete_no','cvillage');
			
			setTimeout("getInvalidatingSubdistrictsList('" + data + "')",2000);
		}
}

function getInvalidatingSubdistrictsList(value)
{
	var subdistrictsList = value.split('::')[1].split('%');
	
	for (var i = 0; i < subdistrictsList.length-1; i++)
	{
		if (i%2==0)
			document.getElementById('ddSubdistrictMergeNo').value = subdistrictsList[i];
		else
			{
				var villagesList = subdistrictsList[i].split('#');
				for (var j = 0; j < villagesList.length; j++)
					{
						document.getElementById('villageListMainContributing').value = villagesList[j];
						addItem('villageListNewContri','villageListMainContributing','',false);
					}
				setTimeout("doNothing()", 100);
				validateSelectAddMore();
				generateTempView();
				hasMoreItems("villageListMainContributing","chooseMoreBtn");
			}
	}
}

$(document).ready(function() {
	
	toggledisplay('subdistrictdelete_yes','fromothersubdistrict');
	
	$('#fromothersubdistrict').hide();
	$('#id1').hide();
	
	
});



copyAllObjectFormOnetoAnother=function(copyId,pasteId){
	
	
	$('#'+copyId+' option').clone().appendTo('#'+pasteId);
	 $('#'+copyId).empty();
	 sortListBox(pasteId);
};

sortListBox=function(id){
	 var $r = $("#"+id+" option");
    $r.sort(function(a, b) {
        if (a.text < b.text) return -1;
        if (a.text == b.text) return 0;
        return 1;
    });
    $($r).remove();
    $("#"+id).append($($r));
    
};

</script>
</head>
<body onload="getData();" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
		<%
			String formname="ManageSchemeSpecificATRFormat";
			request.setAttribute("formId", formname);
		%>
		
<section class="content">
  <div class="row">
  <section class="col-lg-12">
  	 <div class="box">
  	 		<div class="box-header with-border">
                   <h3 class="box-title"><spring:message code="Label.INVALIDATESUBDISTRICT" htmlEscape="true"></spring:message></h3>
             </div>
             
       <form:form action="invalidateSubD.htm" id="invalidateForm" method="POST" commandName="invalidatesubdistrict"  class="form-horizontal">
	 			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateSubD.htm"/>"/>
     			<input type ="hidden" name="flagSubDistrictInvalid" id="flagSubDistrictInvalid"> </input>
     			<div id="dialog-confirm"></div>
			
     <div class="box-body">
     
         <div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.SOURCEDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			<div class="col-sm-6">
	             <form:select path="districtNameEnglish"  id="ddDistrict" Class="form-control"  onchange="getSubDistrictandULBList(this.value);hideBoxes();" htmlEscape="true"
										 onfocus="validateOnFocus('ddDistrict');"  onblur="vlidateOnblur('ddDistrict','1','15','c');">
						    <c:if test="${districtCode eq 0}">
								<form:option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></form:option>
						    </c:if>
													
					<c:forEach items="${districtList}" var="districtList">
						         <c:if test="${districtList.operation_state eq 'A'.charAt(0)}">
								    <form:option value="${districtList.districtCode}" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
									</form:option>
								 </c:if>
							<c:if test="${districtList.operation_state eq 'F'.charAt(0)}">
								<form:option value="${districtList.districtCode}" disabled="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																				
								</form:option>
							</c:if>
					</c:forEach>
			        </form:select> 
					<form:hidden path="operation" value="I"/>
					<form:hidden path="govtOrderConfig" value="${govtOrderConfig}"/>	
					<div class="mandatory"><form:errors htmlEscape="true"  path="districtNameEnglish" ></form:errors></div>
					<span class="mandatory" id="ddDestDistrict_error"> </span>
				</div>
	  	 </div> 
	  	 
	  	 
	  	 
	  	 <div class="form-group">
			 <label class="col-sm-3 control-label"><spring:message code="Label.SOURCESUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			 <div class="col-sm-6"> 
				 <form:select path="subdistrictNameEnglish"   id="ddSubdistrict" Class="form-control" htmlEscape="true" onchange="getvillagesInDraftMode(this.value,'ddSubdistrict');hideBoxes();" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
				 </form:select>
				<div class="mandatory"><form:errors htmlEscape="true"  path="subdistrictNameEnglish" ></form:errors></div>
			</div>							
									
	    </div>      
          
           <div  id="id1">
	           <div class="box-header subheading">
	               <h4 ><spring:message code="Label.HOWSUBDISTRICTDELETED" htmlEscape="true"></spring:message></h3>
	           </div>       
            
            
               <div class="form-group">
	            <label class="col-sm-3"></label>
		            <div class="col-sm-6">
			             <label class="radio-inline"><form:radiobutton id="subdistrictdelete_yes" path="rdoSubdistrictDelete" htmlEscape="true" onclick="toggledisplay('subdistrictdelete_yes','fromothersubdistrict')" name="villagedelete" value="false" /><spring:message code="Label.YES" htmlEscape="true"></spring:message> </label>
			             <label class="radio-inline"><form:radiobutton id="subdistrictdelete_no" path="rdoSubdistrictDelete" htmlEscape="true" onclick="toggledisplay('subdistrictdelete_no','cvillage')" name="villagedelete" value="true" />  <spring:message code="Label.NO" htmlEscape="true"></spring:message></label>
		            </div>
				</div>
            </div>
            
            
            <div id='fromothersubdistrict'>
		             <div class="box-header with-border">
		               <h4 >Select the Subdistrict to be merged with</h3>
		             </div>  
	            
	            
	            <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.TARGETSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6"> 
							<form:select path="targetSubdistrictYes"  id="ddSubdistrictMergeYes" Class="form-control" htmlEscape="true" onchange="getVillageListMerge(this.value);" onblur="vlidateOnblur('ddSubdistrictMergeYes','1','15','c');">
							</form:select> 
						</div>
						<div class="mandatory"><form:errors htmlEscape="true"  path="targetSubdistrictYes" ></form:errors></div>
						<input type="hidden" id="hiddenid" value="1"></input> 
				</div>
			</div>
			
      <div id="cvillage"  style="visibility: hidden; display: none;"  >
      
      	<div class="box-header subheading">
      		 <h4><spring:message code="Label.TARGETSUBD" htmlEscape="true"></spring:message></h4>
   	    </div><!-- /.box-header -->
		
	
		<div class="from-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.TARGETSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory"></span></label>
			<div class="col-sm-6"> 
				<form:select path="targetSubdistrictNo"  id="ddSubdistrictMergeNo" cssClass="combofield" htmlEscape="true" 
				onchange="getVillageListMerge(this.value);" onblur="vlidateOnblur('ddSubdistrictMergeNo','1','15','c');" ></form:select>  
				<div class="errormsg"><form:errors htmlEscape="true"  path="targetSubdistrictNo" ></form:errors></div>
			</div>
		</div> 
		
		
		
		
	
		<div class="ms_container row" >
			<div class="ms_selectable col-sm-4 form-group">
				<label> <spring:message code="Label.SELECTVILLAGESTOMERGEWITH" htmlEscape="true"></spring:message></label>
				 <form:select name="select9" id="villageListMainContributing" path="villList" multiple="multiple" class="form-control"></form:select>
		         <div class="mandatory"><form:errors htmlEscape="true"  path="villList" ></form:errors></div>	
			</div>
			<div class="ms_buttons col-sm-2 dbtn_align">
				<input type="button" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;" class="btn btn-primary btn-xs btn-block" onclick="addItemPC('villageListNewContri','villageListMainContributing','',false);" />
				<input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="btn btn-primary btn-xs btn-block"  onclick="removeItem('villageListNewContri','villageListMainContributing',false)" />
				<input type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn btn-primary btn-xs btn-block"  onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing');" />
				
			</div>
			<div class="ms_selection col-sm-4">
				
					<label for="ddDestBlock"><spring:message code="Label.VILLAGESTOINVALIDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
					 <form:select name="select4" id="villageListNewContri"  multiple="multiple" path="contributedVillages" class="form-control"></form:select>
												  <div class="mandatory"><form:errors htmlEscape="true"  path="contributedVillages" ></form:errors></div>
				<button type="button" id="chooseMoreBtn" onclick='validateSelectAddMore();generateTempView();hasMoreItems("villageListMainContributing","chooseMoreBtn")' name="Add Villages"  class="btn" value="Add Villages" ></button>
				
			</div>
		</div>	
	
		
		
		
			
    <div class="form-group" >
		<table id="dynamicTbl" width="664" class="tbl_with_brdr" style="visibility: hidden">
			<tr class="tblRowTitle tblclear">
				<th>Subdistrict Name</th>
				<th>Village Names</th>
			</tr>
	     </table>
   </div>
   </div>
					
	</div>
   
     
     <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                 <input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn btn-succes" value="<spring:message code="Button.SAVE"></spring:message>" />
                 <input type="button" name="Submit6" class="btn btn-danger" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
                  
                 </div>
           </div>   
       </div> 
     </form:form>
     </div>
     </section>
     </div>
    </section>
                 
     <script>
         <c:if test="${districtCode ne 0}">	 getSubDistrictandULBList('${districtCode}');
		  </c:if>					
	</script>	
	<script src="/LGD/JavaScriptServlet"></script>             
   
    
    
    <div class="modal fade" id="alertbox" tabindex="-1" role="dialog" >
		<div class="modal-dialog" >
				<div class="modal-content">
	  				<div class="modal-header">
	   				 
	    			  	<h4 class="modal-title" id="alertboxTitle"></h4>
	  				</div>
	  				<div class="modal-body" id="alertboxbody">
	        
	  				</div>
	     			 <div class="modal-footer">
	        		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      
	      			</div>
			</div>
		</div>
	</div> 
	     
	
</body>
</html>

























