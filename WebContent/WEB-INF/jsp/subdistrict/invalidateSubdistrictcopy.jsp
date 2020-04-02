<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
 <%@include file="../common/taglib_includes.jsp"%> 
<%!String contextPath;%>
<%contextPath = request.getContextPath();%>
<head>



<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>	
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictGetInvalidateDraft.js'>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/invalidateSubdistrict.js" charset="utf-8"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<!-- <script src="js/trim-jquery.js"></script> -->
 <script src="js/common.js"></script>




<!-- <link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" />
 -->
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/js/bootstrap-dialog.min.js"></script>

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
		alert(data.length+" villages of "+text+" Sub District are in Draft Mode.Kindly first take suitable action(publish the changes) on them in order to continue further");
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

function validateform() {

	var ddDistrict = document.getElementById('ddDistrict').value;
	var ddSubdistrict = document.getElementById('ddSubdistrict').value;
	var ddSubdistrictMergeYes = document.getElementById('ddSubdistrictMergeYes').value;

	if (ddDistrict == 0) {
		alert("Please Select District");
		document.getElementById('ddDistrict').focus();
		return false;
	}
	if (ddSubdistrict == 0 || ddSubdistrict == "") {
		alert("Please Select Subdistrict to invalidate");
		document.getElementById('ddSubdistrict').focus();
		return false;
	}
   
	var c = document.getElementById('hiddenid').value;

	if (c == 1) {

		var mylist = document.getElementById("ddSubdistrict");
		
		
		 BootstrapDialog.show({
	         title: 'Confirm Box',
	         message: "Do you want to invalidate all the Villages of " + mylist.options[mylist.selectedIndex].text + " SubDistrict",
	         buttons: [{
	             label: 'Yes',
	             action: function(dialog) {
	            	 BootstrapDialog.show({
	        	         title: 'Confirm Box',
	        	         message: "Are You Sure?",
	        	         buttons: [{
	        	             label: 'Confirm',
	        	             action: function(dialog) {
	        	            	 $('#flagSubDistrictInvalid').val("-1");
									if (document.getElementById('subdistrictdelete_no').checked) {
										if (document.getElementById('villageListMainContributing').length > 0) {
											alert('Please Select remaining villages');
											return false;
										} else if (document.getElementById('villageListNewContri').length > 0) {
											alert('Please Choose Villages to merge with Subdistrict ');
											return false;
										}

									}

									$('input[name=Submit]').prop('disabled', true);
									document.forms['invalidateForm'].submit();
	        	    				 dialog.close();
	        	             }
	        	         }, {
	        	             label: 'Cancel',
	        	             action: function(dialog) {
	        	            	 $('#id1').show();
									$('#flagSubDistrictInvalid').val("1");
									// var
									// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
									$("#subdistrictdelete_yes").prop('checked', true);

									toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

									$('#fromothersubdistrict').show();
									document.getElementById('hiddenid').value = 3;
									var invalidateSoursesubdist = $('#ddSubdistrict');
									getVillageList(invalidateSoursesubdist.val());
									removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
									dialog.close();
									return false;
									
	        	             }
	        	         }]
	        	     });
	             }
	         }, {
	             label: 'NO',
	             action: function(dialog) {
	            	 $('#id1').show();
						$('#flagSubDistrictInvalid').val("1");
						// var
						// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
						$("#subdistrictdelete_yes").prop('checked', true);

						toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

						$('#fromothersubdistrict').show();
						document.getElementById('hiddenid').value = 3;
						var invalidateSoursesubdist = $('#ddSubdistrict');
						getVillageList(invalidateSoursesubdist.val());
						removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
						 dialog.close();
						return false;
	            	
	             }
	         }]
	     }); 
		
		/*$("#dialog-confirm").html("Do you want to invalidate all the Villages of " + mylist.options[mylist.selectedIndex].text + " SubDistrict");
		$("#dialog-confirm").dialog({
			resizable : false,
			modal : true,
			title : "Confirm Box",
			height : 250,
			width : 400,
			buttons : {
				"Yes" : function() {
					$("#dialog-confirm").html("Are You Sure?");
					$("#dialog-confirm").dialog({
						resizable : false,
						modal : true,
						title : 'Confirm Box',
						height : 250,
						width : 400,
						buttons : {
							"Confirm" : function() {
								$('#flagSubDistrictInvalid').val("-1");
								if (document.getElementById('subdistrictdelete_no').checked) {
									if (document.getElementById('villageListMainContributing').length > 0) {
										alert('Please Select remaining villages');
										return false;
									} else if (document.getElementById('villageListNewContri').length > 0) {
										alert('Please Choose Villages to merge with Subdistrict ');
										return false;
									}

								}

								$('input[name=Submit]').prop('disabled', true);
								document.forms['invalidateForm'].submit();

							},
							"Cancel" : function() {
								$('#id1').show();
								$('#flagSubDistrictInvalid').val("1");
								// var
								// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
								$("#subdistrictdelete_yes").prop('checked', true);

								toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

								$('#fromothersubdistrict').show();
								document.getElementById('hiddenid').value = 3;
								var invalidateSoursesubdist = $('#ddSubdistrict');
								getVillageList(invalidateSoursesubdist.val());
								removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
								$(this).dialog('close');
								return false;

							}
						}
					});

				},
				"No" : function() {
					$('#id1').show();
					$('#flagSubDistrictInvalid').val("1");
					// var
					// checkboxvalue=document.getElementById('subdistrictdelete_yes').value
					$("#subdistrictdelete_yes").prop('checked', true);

					toggledisplay('subdistrictdelete_yes', 'fromothersubdistrict');

					$('#fromothersubdistrict').show();
					document.getElementById('hiddenid').value = 3;
					var invalidateSoursesubdist = $('#ddSubdistrict');
					getVillageList(invalidateSoursesubdist.val());
					removeItemFromOtherDropdowns(document.getElementById('ddSubdistrict'));
					$(this).dialog('close');
					return false;

				}
			}
		});*/

	}

	else {
		if (document.getElementById('hiddenid').value == 3) {
			if (document.getElementById('subdistrictdelete_yes').checked) {
				if (ddSubdistrictMergeYes == 0 || ddSubdistrictMergeYes == "") {
					alert("Please Sub-District To merge with");
					document.getElementById('ddSubdistrictMergeYes').focus();
					return false;
				}
			} else if (document.getElementById('subdistrictdelete_no').checked) {

				if (ddSubdistrictMergeNo == 0 || ddSubdistrictMergeNo == "") {

					if (!document.getElementById('chooseMoreBtn').disabled) {
						alert('Please Select the Villages to merge with target Sub-District');
						return false;
					}

				
				}
			}
		}
		return true;
	}

	// alert(document.getElementById('hiddenid').value);

	// return true;
}


</script>
</head>
<body><!--  onload="getData();" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);"> -->
		<%
			String formname="ManageSchemeSpecificATRFormat";
			request.setAttribute("formId", formname);
		%>
		
<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.INVALIDATESUBDISTRICT" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		
		
		<form:form action="invalidateSubD.htm" id="invalidateForm" method="POST" commandName="invalidatesubdistrict" >
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateSubD.htm"/>"/>
			<input type ="hidden" name="flagSubDistrictInvalid" id="flagSubDistrictInvalid"> </input>
			
			<div id="dialog-confirm"></div>
		<div class="box-body">
			
			
			<div class="box-header subheading">
             	<h4 class="box-title">	<spring:message code="Label.INVALIDATESUBDISTRICT" htmlEscape="true"></spring:message></h4>
           </div><!-- /.box-header -->
			
		<div class="box-body dept_list_button">
			<div class="form-group">
				  <label  class="col-sm-3 control-label" for="ddDistrict"><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"/> <span class="mandatory">*</span></label>
				  <div class="col-sm-8">
						 <form:select path="districtNameEnglish"  id="ddDistrict" class="form-control"  onchange="getSubDistrictandULBList(this.value);hideBoxes();" htmlEscape="true"
									 onfocus="validateOnFocus('ddDistrict');"   onblur="vlidateOnblur('ddDistrict','1','15','c');">
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
						<div class="errormsg"><form:errors htmlEscape="true"  path="districtNameEnglish" ></form:errors></div>
						<span class="errormsg" id="ddDestDistrict_error"></span> 
				  </div>
			</div>
		</div>	
		<div class="box-body dept_list_button">
			<div class="form-group">
				  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.SOURCESUBDISTRICT"/> <span class="mandatory">*</span></label>
				  <div class="col-sm-8">
						<form:select path="subdistrictNameEnglish"   id="ddSubdistrict" class="form-control" htmlEscape="true" 
									onchange="getvillagesInDraftMode(this.value,'ddSubdistrict');hideBoxes();" onblur="vlidateOnblur('ddSubdistrict','1','15','c');">
						 </form:select> 
						<div class="errormsg"><form:errors htmlEscape="true"  path="subdistrictNameEnglish" ></form:errors></div>
				  </div>
			</div>
			</div>	
			
			
			
			<div id="id1" class="box-body dept_list_button" >
				<div class="form-group">
					<label  class="col-sm-6 control-label" for="sel1"><spring:message code="Label.HOWSUBDISTRICTDELETED" htmlEscape="true"></spring:message></label>
				  	<div class="col-sm-6">
				  	<label class="radio-inline">
				   		<form:radiobutton id="subdistrictdelete_yes" path="rdoSubdistrictDelete" htmlEscape="true" onclick="toggledisplay('subdistrictdelete_yes','fromothersubdistrict')" name="villagedelete" value="false" /> 
				   		<spring:message code="Label.YES" htmlEscape="true"></spring:message>
				  	</label>
				  	<label class="radio-inline">
				  		<form:radiobutton id="subdistrictdelete_no" path="rdoSubdistrictDelete" htmlEscape="true" onclick="toggledisplay('subdistrictdelete_no','cvillage')" name="villagedelete" value="true" /> 
						<spring:message code="Label.NO" htmlEscape="true"></spring:message>
				 	</label>
				 	<div  class="errormsg"></div>
				  </div>
				  
				</div>
			</div>
			
		<div id='fromothersubdistrict' class="box-body dept_list_button">
			<div class="box-header subheading">
             	<h4 class="box-title">	Select the Subdistrict to be merged with</h4>
           </div><!-- /.box-header -->
           
           
           <div class="form-group">
				  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.TARGETSUBDISTRICT"/> <span class="mandatory">*</span></label>
				  <div class="col-sm-6">
						<form:select path="targetSubdistrictYes"  id="ddSubdistrictMergeYes" class="form-control" htmlEscape="true"
							onchange="getVillageListMerge(this.value);" onblur="vlidateOnblur('ddSubdistrictMergeYes','1','15','c');">
						</form:select> 
						<div class="errormsg"><form:errors htmlEscape="true"  path="targetSubdistrictYes" ></form:errors></div>
						<input type="hidden" id="hiddenid" value="1"></input>
				  </div>
			</div>
		 </div>
		 
					
	      <div id='cvillage' style="visibility: hidden; display: none;" >
	   		 <div class="box-header subheading">
             		<h4 class="box-title">	<spring:message code="Label.TARGETSUBD" htmlEscape="true"></spring:message></h4>
           	</div><!-- /.box-header -->
						
					  <div class="form-group">
						  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.TARGETSUBDISTRICT"/> <span class="mandatory">*</span></label>
						  <div class="col-sm-6">
								<form:select path="targetSubdistrictNo"  id="ddSubdistrictMergeNo" class="form-control" htmlEscape="true"
									onchange="getVillageListMerge(this.value);" onblur="vlidateOnblur('ddSubdistrictMergeNo','1','15','c');">
								</form:select>
								<div class="errormsg"><form:errors htmlEscape="true"  path="targetSubdistrictNo" ></form:errors></div>
						  </div>
						</div>
						 
						
						<div class="ms_container row" style="margin-left: 10px;">
		                	<div class="ms_selectable col-sm-5 form-group">
		                    	<label><spring:message htmlEscape="true" code="Label.SELECTVILLAGESTOMERGEWITH"/></label>
		                    	<form:select name="select9"  id="villageListMainContributing" path="villList" multiple="multiple" class="form-control"></form:select>
		                    	<div class="errormsg"><form:errors htmlEscape="true"  path="villList" ></form:errors></div>
	                      	</div>
	                      	<div class="ms_buttons col-sm-2"><br/>
			                   <input type="button" id="btnaddVillageFull" name="Submit4" value="Select Villages&gt;&gt;" class="btn btn-primary btn-xs btn-block"  onclick="addItemPC('villageListNewContri','villageListMainContributing','',false);" />
								<input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="btn btn-primary btn-xs btn-block"  onclick="removeItem('villageListNewContri','villageListMainContributing',false)" />
								<input type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="btn btn-primary btn-xs btn-block" onclick="copyAllObjectFormOnetoAnother('villageListNewContri','villageListMainContributing');" />
									
	                      		
	                      	</div>
	                      	<div class="ms_selection col-sm-5">
	                      		<div class="form-group">
	                      			<label><spring:message htmlEscape="true" code="Label.VILLAGESTOINVALIDATE"/></label>
									<form:select name="select4" id="villageListNewContri"  multiple="multiple" path="contributedVillages" class="form-control"></form:select>
									 <div class="errormsg"><form:errors htmlEscape="true"  path="contributedVillages" ></form:errors></div>
									  <input type="button" class="btn btn-primary " id="chooseMoreBtn" onclick='validateSelectAddMore();generateTempView();hasMoreItems("villageListMainContributing","chooseMoreBtn")' name="Add Villages"  value="Add Villages" />
									
	                      		</div>
	                      	</div>
	                    </div>
						<div >
								<table id="dynamicTbl" width="664" class="data_grid" style="visibility: hidden">
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
	                       		<input type="button" onclick="validateSelectAndSubmit(); " name="Submit" class="btn btn-success" value="<spring:message code="Button.SAVE"></spring:message>" />
								<input type="button" name="Submit6" class="btn btn-danger" value="<spring:message code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
							</div>
						</div>
				  </div>
			
			  
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
			
			</form:form>
			
			
			</div>
			</section>
			</div>
			</section>
			<script>
				 <c:if test="${districtCode ne 0}">	
					getSubDistrictandULBList('${districtCode}');
				</c:if>					
			</script>	
             <script src="/LGD/JavaScriptServlet"></script> 
			
		
</body>
</html>

























