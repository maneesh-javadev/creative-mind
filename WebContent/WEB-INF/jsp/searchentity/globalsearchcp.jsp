<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%@include file="../homebody/commanInclude.jsp"%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script src="js/validation.js"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>


<script type="text/javascript">
$(document).ready(function() {
	$('#search_data').dataTable({
	});
});

/* $(document).ready(function() {
	$("#form1").validate({ 
    rules: { 
    	entityName:{
    	   required : true,
    	   minlength: 3,
    	   maxlength: 50,
    	   onlyLetterNumber: true
       },
       captchaAnswer:{
    	   required:true
       }
    }, 
    messages: { 
    	entityName: {
    		required: "<font color='red'><br><spring:message code='LGD.entity.required' text='Please enter search details and minimum character length should be 3'/></font>",
    		minlength: "<font color='red'><br><spring:message code='LGD.entity.min.length' text='Please enter atleast 3 characters'/></font>",
    		maxlength: "<font color='red'><br><spring:message code='LGD.entity.max.length' text='Can not enter more than 50 characters'/></font>",
    		onlyLetterNumber: "<font color='red'><br><spring:message code='LGD.only.letter.number' text='This field contains invalid characters.Please use A-Z or a-z or 0-9'/></font>"
       },
       captchaAnswer: {
    	   required: "<font color='red'><br><spring:message code='LGD.captcha.required' text='Please enter captcha characters'/></font>"
       }
    } 
  });
 });   */
//Maneesh
function validateForm() {
	var entityName = document.getElementById('entityNameA').value;
	var entityCode = document.getElementById('entitycode').value;
	var searchValueObj = document.getElementsByName('searchValueOption');
	var flag=0;
	for(var i=0;i<searchValueObj.length;i++){
		if(searchValueObj[i].checked==true)
			flag=1;
	}
	if(flag==0)
		{
	
		alert("Please select either Entity Name or Entity Code for searching");
		return false;
		
		}
	if(entityName == "" && entityCode=="" ) {
		for(var i=0;i<searchValueObj.length;i++){
			if(searchValueObj[i].checked==true)
				if(searchValueObj[i].value.trim()=="Entityname")
					{
					alert("Please enter search details Entity Name");
					}
				else if(searchValueObj[i].value.trim()=="Entitycode")
				{
					alert("Please enter search details Entity Code");
				}
		}
		
		return false;
	} 
	/* if(entityName == "") {
		alert("Please enter search details and minimum characters length should be 3");
		return false;
	}  
	*/
	if(entityName !=""){
	if(entityName.length < 3 || entityName.length > 50) {
		alert("Please enter search details and minimum characters length should be between 3 and 50");
		return false;
	}}
	 /* if(captchaAnswer=="") {
		alert("Please enter captcha characters");
		return false;
	} */
	return true;
}

function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
}


/**
 * Change this method for work all type of search by Maneesh Kumar
 
 */
function openwinByUrl(url, elementId,elemntVal) {
	var obj = window.showModalDialog(url+"?<csrf:token uri='"+url+"'/>&"+elementId+"="+elemntVal, '', "dialogWidth:900px; dialogHeight:550px; dialogLeft: 470; dialogTop: 200; center:yes; resizable: yes; status:no");
	
} 






var viewEntityDetailsInPopup = function (entityCode, cusurl, paramName)	{
	$('#myIframe').attr('src', cusurl + "?" + paramName + "=" + entityCode + "&<csrf:token uri='" + cusurl + "'/>");  
	$('#dialogBX').modal('show'); 
};

function viewById(cusurl,paramName,elemntVal) {
$('#myIframe').attr('src', cusurl + "?" + paramName + "=" + elemntVal + "&isState='N'&<csrf:token uri='" + cusurl + "'/>");  
$('#dialogBX').modal('show');
}

function searchStatebyId(url,elementId,elemntVal) {
	dwr.util.setValue('globalstateId', elemntVal, {	escapeHtml : false	});
	//openwinByUrl(url,elementId, elemntVal);
	viewEntityDetailsInPopup(elemntVal,url,elementId);
}

function searchDistrictbyId(url,elementId,elemntVal) {
	dwr.util.setValue('globaldistrictId', elemntVal, {	escapeHtml : false	});
	//openwinByUrl(url,elementId, elemntVal);
	viewEntityDetailsInPopup(elemntVal,url,elementId);
}

function searchSubDistrictbyId(url,elementId,elemntVal) {
	dwr.util.setValue('globalsubdistrictId', id, {	escapeHtml : false	});
	//openwinByUrl(url,elementId, elemntVal);
	viewEntityDetailsInPopup(elemntVal,url,elementId);
}

function searchVillagebyId(url,elementId,elemntVal) {
	dwr.util.setValue(elementId, elemntVal, {	escapeHtml : false	});
	//openwinByUrl(url,elementId, elemntVal);
	viewEntityDetailsInPopup(elemntVal,url,elementId);

}

/**
 * End Change this method for work all type of search by Maneesh Kumar
 
 */
function mulSelCheckBox(val) {	
	if (val) {
		document.getElementById('chkS').checked=true;
		document.getElementById('chkD').checked=true;
		document.getElementById('chkSD').checked=true;
		document.getElementById('chkV').checked=true;
		document.getElementById('chkR').checked=true;
		document.getElementById('chkU').checked=true;
		document.getElementById('chkO').checked=true;
		document.getElementById('chkOU').checked=true;
		document.getElementById('chkB').checked=true;
	} else {
		document.getElementById('chkS').checked=false;
		document.getElementById('chkD').checked=false;
		document.getElementById('chkSD').checked=false;
		document.getElementById('chkV').checked=false;
		document.getElementById('chkR').checked=false;
		document.getElementById('chkU').checked=false;
		document.getElementById('chkO').checked=false;
		document.getElementById('chkOU').checked=false;
		document.getElementById('chkB').checked=false;
	}
}


function validateSelectBox() {
	 n = $( "input[type=checkbox]:checked" ).length;
	
	if(n == 0){
		alert("Please select atleast one entity type");
		return false;
	}
	return true;
}
function getData() {
	document.getElementById('ems').innerHTML="";
	
	var bool = validateForm();
	
	if(bool) {
		if(validateSelectBox()){
			//document.getElementById('ems').innerHTML="<b>Please select atleast one entity type</b>";
			//return false;
		
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		/* New Captcha Code */
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		 if(capchaImgVal=="") {
		alert("Please enter captcha characters");
		return false;
		}
		//return false;
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>";
		//displayLoadingImage();     this method not working properly........commented on :02-06-2015 by Sangita for Search Functionality
		document.getElementById('form1').submit();
		
		return true;}
		else
			return false;
	}
	
}

	function chkSearchOnLoad() {
		$("#entityNameA_error").hide();

}
	$(document).ready(function() {
		 n = $( "input[type=radio]:checked" ).length;
		 if(n!=0)
			 {
			 var searchValueObj = document.getElementsByName('searchValueOption');
				var flag=0;
				for(var i=0;i<searchValueObj.length;i++){
					if(searchValueObj[i].checked==true)
						{
						if(searchValueObj[i].value=="Entityname")
						{
						document.getElementById('searchByEntityName').style.display = "block";
						document.getElementById('searchByEntityCode').style.display = "none";
						document.getElementById('entitycode').value="";
						}
					else if(searchValueObj[i].value=="Entitycode")
						{
						document.getElementById('searchByEntityCode').style.display = "block";
						document.getElementById('searchByEntityName').style.display = "none";
						document.getElementById('entityNameA').value="";
						}
						}
				}
			 }
	});
	
	function displaySearchBlock(obj)
	{
		var value=obj.value.trim();
		if(value=="Entityname")
			{
			document.getElementById('searchByEntityName').style.display = "block";
			document.getElementById('searchByEntityCode').style.display = "none";
			document.getElementById('entitycode').value="";
			}
		else if(value=="Entitycode")
			{
			document.getElementById('searchByEntityCode').style.display = "block";
			document.getElementById('searchByEntityName').style.display = "none";
			document.getElementById('entityNameA').value="";
			}
	}
	
	function validateAlphanumericWithSpace(event,a) {
		var key;
		
		key = event.keyCode;

		// alert(key);
		if (key == 0)
			key = event.which;

		if ((key >= 48) && (key <= 58) || (key >= 65) && (key <= 90) || (key >= 97) && (key <= 122) || (key == 32) || (key == 8) || (key == 9)
				|| (key == 46)) {
			event.returnValue = true;
			return true;
		} else {
			alert("Please use numeric,Alphabets and Space value only");
			a.value='';
			event.returnValue = false;
			return false;

		}
	}
</script>
</head>
<body>
<section class="content">
  <div class="row">
    <!-- main col -->
    <section class="col-lg-12">
		<div class="box">
	      <div class="box-header with-border">
	        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.SEARCH"></spring:message></h3>
	      </div><!-- /.box-header -->

		<form:form action="globalsearchentity.do" method="POST" commandName="globalsearchView" id="form1" name="searchForm" class="form-horizontal">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="globalsearchentity.do"/>"/>
		<form:input path="globalvillageId" type="hidden" name="globalvillageId" id="globalvillageId" />	
		<form:input path="globalvillageId" type="hidden" name="globalsubdistrictId" id="globalsubdistrictId" />	
		<form:input path="globalvillageId" type="hidden" name="globaldistrictId" id="globaldistrictId" />		
		<form:input path="globalvillageId" type="hidden" name="globallocalbodyId" id="globallocalbodyId" />																																										
		<div class="box-header subheading">
			<h4 class="box-title"><spring:message htmlEscape="true"  code="Label.SEARCHCRITERIA"></spring:message></h4>
		</div><!-- /.box-header -->		
		
		<input type="hidden" id="sessionId"	value='<%=sessionId%>'></input>
		
		<div class="form-group">
		   	  <label  class="col-sm-1 control-label" for="sel1"></label>
			  <div class="col-sm-6">
				  <label class="radio-inline"><form:radiobutton path="searchValueOption" value="Entityname" name="searchValueOption" onclick="displaySearchBlock(this)"/>
				   		Search by Entity Name
				  </label>
				  <label class="radio-inline"><form:radiobutton path="searchValueOption" value="Entitycode" name="searchValueOption" onclick="displaySearchBlock(this)"/>
						Search by Entity Code
				  </label>
			  </div>
		</div>
		
		<div class="form-group" id="searchByEntityName" style="display: none;">
		  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.ENTITYNAME"></spring:message><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
			<form:input type="text" id="entityNameA" class="form-control" path="entityName" minlength="3" maxlength="50" onkeydown="validateAlphanumericWithSpace(event,this)"/>
			<span class="errormsg" >(Please Enter atleast 3 characters of the Entity Name)</span>
	      </div>
	       <div class="col-sm-3"><form:errors htmlEscape="true" path="entityName"></form:errors></div>
	  	</div>
	  	
	  	<div class="form-group" id="searchByEntityCode" style="display: none;">
		  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.ENTITYCODE"></spring:message><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
				<form:input type="text" id="entitycode" class="form-control" path="entityCode" minlength="1" maxlength="50" onkeydown="validateOnKeyPessDown(event,'entitycode_1','number')" />
			<!-- <span class="errormsg" >(Please Enter 3 digits of the Entity Code)</span> -->
	      </div>
	       <div class="col-sm-3">
					<div class="errormsg" id="entitycode_1_type_error" style="display: none;">Only Numeric value allowed.</div>
		</div>
	  	</div>
              
         <div class="form-group">
            <label class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.SELECTALL"></spring:message></label>
            <div class="col-sm-8">
                <label class="checkbox-inline col-sm-2">
                	<form:checkbox id="selectall" name="checkbox" value="" path="" onclick="mulSelCheckBox(this.checked)"  />
				</label>
            </div>
        </div>
              
            <div class="form-group">
                  <label class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.LANDREGION"></spring:message></label>
                  <div class="col-sm-8">
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkS" path="stateChecked"  /> 
						<spring:message htmlEscape="true"  code="Label.STATE"></spring:message>
					  </label>
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkD" path="districtChecked"   /> 
						  <spring:message htmlEscape="true"  code="Label.DISTRICT"></spring:message>
					  </label>
					  <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkSD" path="subDistrictChecked"  /> 
						 <spring:message htmlEscape="true"  code="Label.SUBDISTRICT"></spring:message>
					  </label>
					  <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkV" path="villageChecked"  /> 
						 <spring:message htmlEscape="true"  code="Label.VILLAGE"></spring:message>
					  </label>
                  </div>
              </div>
              
              <div class="form-group">
                  <label class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true"  code="Label.LOCALGOVTBODY"></spring:message></label>
                  <div class="col-sm-8">
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkR" path="ruralChecked"  /> <%-- onclick="ruralCheckBox(this.checked)" --%>
						<spring:message htmlEscape="true"  code="Label.RURALG"></spring:message>
					  </label>
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkU" path="urbanChecked" /> 
					    <spring:message htmlEscape="true"  code="Label.ULG"></spring:message>
					  </label>
					  
                  </div>
              </div>
              
              <!-- from here i have to start (aashish barua) -->
              <div class="form-group">
                  <label class="col-sm-3 control-label" for="sel1">Block</label>
                  <div class="col-sm-8">
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkB" path="blockChecked" /> 
						Block
					  </label>
                  </div>
              </div>
              
              
              <div class="form-group">
                  <label class="col-sm-3 control-label" for="sel1">Organisation Level<%-- <spring:message htmlEscape="true"  code="Label.LOCALGOVTBODY"></spring:message> --%></label>
                  <div class="col-sm-8">
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkO" path="organistionChecked" /> <%-- onclick="ruralCheckBox(this.checked)" --%>
						Organisation<%-- <spring:message htmlEscape="true"  code="Label.RURALG"></spring:message> --%>
					  </label>
                      <label class="checkbox-inline col-sm-2">
                      	<form:checkbox name="checkbox" id="chkOU" path="orgUnitChecked" /> 
					    Org. Unit<%-- <spring:message htmlEscape="true"  code="Label.ULG"></spring:message> --%>
					  </label>
					  
                  </div>
              </div>
              <!-- code ends here -->
							
				<div class="box-header subheading">
					<h4 class="box-title"><spring:message code="Label.CAPTCHA" text="Captcha" htmlEscape="true"></spring:message>	</h4>
				</div><!-- /.box-header -->									
				<div class="form-group">
						  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message> <span class="mandatory">*</span></label>
						  <div class="col-sm-6">
						  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
						  </div>
				</div>
				<div class="form-group">
						  <label  class="col-sm-3 control-label" for="sel1">
						 </label>
						  <div class="col-sm-6">
						  	<form:input path="captchaAnswer"  autocomplete="off" maxlength="5" />
						  <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>							
						 <div id="errEmptyCaptcha" class="errormsg" style="display:none"></div>
							<div id="errorCaptcha" style="display:none" class="errormsg"></div>	
						  </div>
				</div>				
								
							
				<div class="block" style="width:21%;" >
					<%-- <form:errors cssClass="errormsg" path="selectedVal"></form:errors> --%>
					<div id="ems" class="errormsg"></div>
				</div>
				
				
				<div class="box-footer">
				     <div class="col-sm-offset-2 col-sm-10">
				       <div class="pull-right">
				        	
				       		<button type="button" class="btn btn-success" onclick="return getData();" ><i class="fa fa-floppy-o"></i><spring:message htmlEscape="true"  code="Button.GETDATA"> </spring:message></button>
							<button type="button" class="btn btn-warning" onclick="javascript:location.href='globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>';"> <spring:message htmlEscape="true"  code="Button.CLEAR"/></button>
							<button type="button" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"/></button>
				        </div>
				     </div>   
				  </div>
				
				<c:if test="${! initialStep}">		
					 <c:if test="${empty allSerachDeatail}">
						<script>
							alert("Matching records not found for selected search criteria, kindly change your search criteria");
						</script>
					</c:if>					
				</c:if>
				
				 <c:if test="${! empty allSerachDeatail}">
				 	<div class="box-header subheading">
						<h4 class="box-title"><spring:message htmlEscape="true"  code="Label.URBANSEARCHRESULT"></spring:message>	</h4>
					</div><!-- /.box-header -->		
					
				
							<table class="table table-striped table-bordered dataTable" id="search_data"  width="80%" >
								<tr >
									<td width="5%" ><spring:message htmlEscape="true"  code="S.No."></spring:message></td>
									<td width="21%" ><spring:message htmlEscape="true"  code="Label.ENTITYCODE"></spring:message></td>
									<td width="12%" ><spring:message htmlEscape="true"  code="Label.ENTITYTYPE"></spring:message></td>
									<td width="21%" ><spring:message htmlEscape="true"  code="Label.ENTITYNAME"></spring:message></td>
									<td width="48%"  colspan="2"><spring:message htmlEscape="true" 	code="Entity Hierarchy"></spring:message></td>
									<td colspan="6" align="center"><spring:message htmlEscape="true"  code="Label.VIEW"></spring:message></td>
							</tr>
						<c:forEach var="SerachDeatail" varStatus="SerachDeatailRow" items="${allSerachDeatail}">
										<tr>
											<td width="6%"><c:out value="${SerachDeatailRow.count}" escapeXml="true"></c:out></td>
											<td><c:out value="${SerachDeatail.entityCode}" escapeXml="true"></c:out></td> 
											<%-- <td><c:out value="Urban"></c:out></td> --%>
											<td align="left"><c:out value="${SerachDeatail.entityType}" escapeXml="true"></c:out></td>
											
											<td align="left"><c:out value="${SerachDeatail.entityName}" escapeXml="true"></c:out></td>
											<td align="left" colspan="2"><c:out value="${SerachDeatail.hierarchy}" escapeXml="true"></c:out></td>
											<%-- <td align="left"><c:out value="${searchLGB.stateNameEnglish}"></c:out></td> --%>
											 <td><c:choose>
									          <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Sub-District')}">
											<center><a href="#" onclick="javascript:searchSubDistrictbyId('globalviewSubDistrictDetail.do','globalsubdistrictId',${SerachDeatail.entityCode});" width="18" height="19" border="0"><i class="fa fa-eye" aria-hidden="true"></i></center>
									         </c:when>
									          <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'District')}">
											 <center><a href="#" onclick="javascript:searchDistrictbyId('globalviewDistrictDetail.do','globaldistrictId',${SerachDeatail.entityCode});" width="18" height="19" border="0" ><i class="fa fa-eye" aria-hidden="true"></i></center>
									         </c:when>
									           <c:when test="${ SerachDeatail.entityType=='Village'}">
											 <center><a href="#" onclick="javascript:searchVillagebyId('globalviewVillageDetail.do','globalvillageId',${SerachDeatail.entityCode});" width="18" height="19" border="0" ><i class="fa fa-eye" aria-hidden="true"></i></center>
									         </c:when>
									           <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'State')}">
											 <center><a href="#" onclick="javascript:searchStatebyId('globalviewStateDetail.do','globalstateId',${SerachDeatail.entityCode});" width="18" height="19" border="0" ><i class="fa fa-eye" aria-hidden="true"></i></center>
									         </c:when>
									         <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Block')}">
											 <center>&nbsp;</center>
									         </c:when>
									           <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Ministry')}">
											 <center>&nbsp;</center>
									         </c:when>
									           <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Organization')}">
											 <center>&nbsp;</center>
									         </c:when>
									           <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Department')}">
											 <center>&nbsp;</center>
									         </c:when>
									           <c:when test="${fn:containsIgnoreCase(SerachDeatail.entityType,'Org Unit')}">
											 <center>&nbsp;</center>
									         </c:when>
									           
									           <c:otherwise>
											 	<center><a href="#" onclick="viewById('viewEntityDetail.do','code',${SerachDeatail.entityCode});"  width="18" height="19" border="0"><i class="fa fa-eye" aria-hidden="true"></i></center>
									         </c:otherwise>
									         </c:choose>
									         </td>
										</tr>
									</c:forEach>
                     				</table>
							
							<c:if test="${nodata == null}">
							 <c:if test="${closeFlag == true}"> 
								<div class="box-footer">
							     <div class="col-sm-offset-2 col-sm-10">
							       <div class="pull-right">
							        	<button type="button" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"/></button>
							        </div>
							     </div>   
							  </div>
							</c:if>	 
						</c:if>		
						
				</c:if>		
					
					
					
				</form:form>
				<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="910" height="650"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
							</div>
				
			 <script src="/LGD/JavaScriptServlet"></script>												
			</div>
		</section>
		</div>
		</section>
		
		

</body>
</html>