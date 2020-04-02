<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<% 
	contextPath = request.getContextPath(); 
%>

<html>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.modal-body {
    max-height: calc(100vh - 200px);
    overflow-y: auto;
}


</style>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>	
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDWRDisturbedEntities.js'></script>

<script type="text/javascript" language="javascript">
var urlInvalidateLB="";
var urlChangeCovArea="";
var w ="";
var checkInval = new Boolean(false);
var checkChangeCov = new Boolean(false);


if (window.addEventListener) {
    // DOM2 standard
    window.addEventListener("load", onLoadSelect, false);
}
else if (window.attachEvent) {
    // Microsoft's precursor to it, IE8 and earlier
    window.attachEvent("onload", onLoadSelect);
}
else {
    // Some pre-1999 browser
    window.onload = onLoadSelect;
}

function Unset(id)
{
	var temp=id.split(",");
	document.getElementById("entityCode").value=temp[0];
	document.getElementById("entityVersion").value=temp[1];
	var confirmation=confirm("The flag will be unset, please ensure that the required changes are done.");
	if(confirmation==true){
		document.forms['GetEntitiesWithWanrning'].submit();
	}
}

function onLoadSelect()
{
	
	if('${warningEntiesFlag}' == 'W'){
		document.getElementById("wardisFlagW").checked = true;
		document.getElementById("warningId").style.display = "inline";
		document.getElementById("disturbId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEWARNING"/>";
		
	}
	if('${warningEntiesFlag}' == "D"){
		document.getElementById("wardisFlagD").checked = true;
		document.getElementById("disturbId").style.display = "inline";
		document.getElementById("warningId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEDISTURB"/>";
	
	}
	
	var mypath = window.location.href;
	document.getElementById(mypath.split("=")[1]).disabled = false;
	document.getElementById(mypath.split("=")[1]).style.backgroundColor="green";
	setTimeout("document.getElementById(mypath.split('=')[1]).style.backgroundColor=''", 500);
	setTimeout("onLoadSelect()",1500);
}

function WarDisFunction(val)
{
	if(val == "W"){		
		document.getElementById("warningId").style.display = "block";
		document.getElementById("disturbId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEWARNING"/>";
		document.getElementById("warningEntiesFlag").value = val;
	}
	if(val == "D"){
		document.getElementById("disturbId").style.display = "block";
		document.getElementById("warningId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEDISTURB"/>";
		document.getElementById("warningEntiesFlag").value = val;
	}
	
}

function getMessage(actionInvalidateLB,actionChangeCoveredArea,localbodycode)
{
	lgdDWRDisturbedEntities.getMessagebyLBCode(localbodycode, {
		callback : handledgetMessageSuccess,
		errorHandler : handledGetMessageError
	});
	
	urlInvalidateLB=actionInvalidateLB;
	urlChangeCovArea=actionChangeCoveredArea;
}

function handledgetMessageSuccess(data){
	
	var message="";
	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		message = labelDetails.getLocalbodyDisturbRegionfn;
	}
	callAction(message);
}


function handledGetMessageError() {
	alert("No data found!");
}
//it will be handeled later wise (commented by indu)
function callAction(message)
{	
	$("#dialogMessage").text(message);
	$("#partINV").val(urlInvalidateLB);
	$("#partCOV").val(urlChangeCovArea);
	$('#dialogBX').modal('show'); 
	
	/* w = window.open('', '',
		'width=600,height=600,resizeable,position=center,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes',
		align = "center");
	
	var innerHTMLText = '';
    
	innerHTMLText += "<body><table width='500' class='table table-bordered table-hover'><tr ><td width='300'><h2> Reason for Local Body to be in Disturbed State </h2></td></tr><tr class='tblRowTitle tblclear'><td width='300'>" + message + "</td></tr></table>";
	innerHTMLText += "<table width='500' class='table table-bordered table-hover'><tr ><td width='300'><h2>Action to be taken for Disturbed Flag "
		+ "</h2></td></tr>";
		
	innerHTMLText += "<tr ><td><input type='radio' name='partR' id='partINV' onclick='window.opener.invalidR(partINV)' value=" + urlInvalidateLB
		 + "/>Invalidate Local Body" + "</td></tr><tr class='tblRowB'><td><input type='radio' name='partR' id='partCOV' onclick='window.opener.changeCov(partCOV)' value=" + urlChangeCovArea 
		 + "/>Modify Local Govt Body</td></tr></table>"
		 + "<tr><td><button type='button' class='btn btn-info' name='Submit16' value='Get Value' onclick='window.opener.doSomeAction()' >Get Value</button></td></tr></body></html>";	
	
	w.document.write(innerHTMLText);
	w.document.close();	 */
}

function invalidR(partINV)
{
	checkInval=partINV.checked;
	urlInvalidateLB=partINV.value;
}

function changeCov(partCOV)
{
	checkChangeCov=partCOV.checked;
	urlChangeCovArea=partCOV.value;
}


function doSomeAction()
{
	if(checkInval==true)
 	{
 		
		 	$( 'form[id=GetEntitiesWithWanrning]' ).attr('action', urlInvalidateLB + '?<csrf:token uri="' + urlInvalidateLB + '"/>');
			$( 'form[id=GetEntitiesWithWanrning]' ).attr('method','post');
			$( 'form[id=GetEntitiesWithWanrning]' ).submit();
			/* document.forms["GetEntitiesWithWanrning"].action = urlInvalidateLB;
			document.forms["GetEntitiesWithWanrning"].submit(); */
		w.close();
 	}	
 	else if(checkChangeCov==true)
 	{
 		
 		$( 'form[id=GetEntitiesWithWanrning]' ).attr('action', urlChangeCovArea + '?<csrf:token uri="' + urlChangeCovArea + '"/>');
		$( 'form[id=GetEntitiesWithWanrning]' ).attr('method','post');
		$( 'form[id=GetEntitiesWithWanrning]' ).submit();
 		/* document.forms["GetEntitiesWithWanrning"].action = urlChangeCovArea;
		document.forms["GetEntitiesWithWanrning"].submit(); */
		w.close();
 	}	
}

dwr.engine.setActiveReverseAjax(true);
</script>
</head>
<body>
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <div class="box">
                    <form:form  action="viewResolveEntitiesinDisturbedState.htm?resolved=${resolved}" method="POST" commandName="GetEntitiesWithWanrning" id="GetEntitiesWithWanrning">
	                    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewResolveEntitiesinDisturbedState.htm"/>"  htmlEscape="true"/>
                    
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.RESOLVEDISTURBSTATE"/></h3>
                                </div>
                                
                        <div class="box-body">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"></label>
								<div class="col-sm-6" >
								<label class="radio-inline">   <input type="radio" name="wardisFlag" id="wardisFlagW" value="W" onclick="WarDisFunction(this.value);" htmlEscape="true"/>Warning State</label>
								<label class="radio-inline">
									<c:if test="${type == 'L'}">
					                       <input type="radio" name="wardisFlag" id="wardisFlagD" value="D" disabled="disabled" onclick="WarDisFunction(this.value);" htmlEscape="true"/>Disturb State
					 	
					               </c:if>
								   <c:if test="${type != 'L'}">
									 <input type="radio" name="wardisFlag" id="wardisFlagD" value="D" onclick="WarDisFunction(this.value);" htmlEscape="true"/>Disturb State
								   </c:if>
							   </label>
								</div>
						</div> 
						
						
					<input type="hidden" name="warningEntiesFlag" id="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}' escapeXml='true'></c:out>" htmlEscape="true"/>
				   <input type="hidden" name="type" id="type" value="<c:out value='${type}' escapeXml='true'></c:out>" htmlEscape="true"/> 
				   
				    <div id="warningId" style="display:none;" >
							<table   class="table table-bordered table-hover"  width="100%" >
								<tr >
									<td align="center"><spring:message htmlEscape="true"  code="Label.SNO"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.FLAG"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.ENTITYTYPE"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.ENTITYNAME"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.ACTION"/></td>
								</tr>
							    <c:choose>
	                               	<c:when test="${!empty warningEnties}">
	                               		<c:forEach var="disturbedEnties" varStatus="disturbedEntiesRow" items="${warningEnties}">
										<c:choose>
											<c:when test="${disturbedEnties.entityCode == id}">
												<tr  >
													<td align="center" width="10%"><c:out value="${disturbedEntiesRow.count}" escapeXml="true"></c:out></td>
													<td align="center" width="20%"><img src="images/ylw_flg.png" width="13" height="9" /></td>
													<td align="center" width="30%"><c:out value="${disturbedEnties.entityType}" escapeXml="true"></c:out></td>
													<td width="30%"><c:out value="${disturbedEnties.entityNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center" width="20%">
														<a href="<c:out value='${disturbedEnties.entityNameLocal}'></c:out>&<csrf:token uri="${disturbedEnties.entityNameLocal}"/>"><i class="fa fa-pencil" aria-hidden="true"></i></a>
													</td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr >
													<td align="center" width="10%"><c:out value="${disturbedEntiesRow.count}" escapeXml="true"></c:out></td>
													<td align="center" width="20%"><img src="images/ylw_flg.png" width="13" height="9" /></td>
													<td align="center" width="30%"><c:out value="${disturbedEnties.entityType}" escapeXml="true"></c:out></td>
													<td width="30%"><c:out value="${disturbedEnties.entityNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center" width="20%">
														<a href="<c:out value='${disturbedEnties.entityNameLocal}'></c:out>&<csrf:token uri="${disturbedEnties.entityNameLocal}"/>"><i class="fa fa-pencil" aria-hidden="true"></i></a>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
	                               	</c:when>
	                               	<c:otherwise>
	                               		<tr >
											<td colspan="7" align="center" height="30"><b><spring:message htmlEscape="true" code="Error.dialog-confirm-nodata"></spring:message></b></td>
										</tr>
	                               	</c:otherwise>
                               </c:choose>
                               <input type="hidden" name="entityCode" id="entityCode" htmlEscape="true" />
							   <input type="hidden" name="entityVersion" id="entityVersion" htmlEscape="true"/>
							</table>
							</div>
							
			   <div id="disturbId" style="display:none;" >
							<table   class="table table-bordered table-hover" >
								<tr >
									<td align="center"><spring:message htmlEscape="true" code="Label.SNO"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.FLAG"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.NAMELOCALGOVTTYPE"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.ACTION"/></td>
								</tr>
								<c:choose>
	                               	<c:when test="${!empty disturbedEnties}">
										<c:forEach var="disturbedEnties" varStatus="disturbedEntiesRow" items="${disturbedEnties}">
											<tr >
												<td width="16%" align="center"><c:out value="${disturbedEntiesRow.count}" escapeXml="true"></c:out></td>
												<td align="center" width="16%"><img src="images/red_flg.png" width="13" height="9" /></td>
												<td align="center" width="25%"><c:out value="${disturbedEnties.localbodycode}" escapeXml="true"></c:out></td>
												<td align="center" width="25%"><c:out value="${disturbedEnties.localbodytypename}" escapeXml="true"></c:out></td>
												<td><c:out value="${disturbedEnties.localbodynameenglish}" escapeXml="true"></c:out></td>
												<td width="20%" align="center">
													<a href="#" style="text-decoration:none;" onclick="getMessage('${disturbedEnties.actionInvalidateLB}','${disturbedEnties.actionChangeCoveredArea}','${disturbedEnties.localbodycode}');">action</a>
												</td>
											</tr>
											 <c:set var="countb" value="${countb+1}"></c:set>
										</c:forEach>
									</c:when>
	                               	<c:otherwise>
										<tr>
											<td colspan="6" align="center" height="30"><b><spring:message htmlEscape="true" code="Error.dialog-confirm-nodata"></spring:message></b></td>
										</tr>
									</c:otherwise>
								</c:choose>		
								<input type="hidden" name="entityCode" id="entityCode" htmlEscape="true"/>
								<input type="hidden" name="entityVersion" id="entityVersion" htmlEscape="true"/>
							</table>
						
						</div>
 				 
                 </div> 
             
                       
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>
       </form:form> 
       
       <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
		<div class="modal-dialog" style="width:600px;">
				<div class="modal-content">
	  				<div class="modal-header">
	   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
	    			  	<h4 class="modal-title" ><c:out value="Reason for Local Body to be in Disturbed State"/></h4>
	    			  	
	  				</div>
	  				<div class="modal-body" id="dialogBXbody">
	        			
		  					<label  class="col-sm-12" id="dialogMessage"></label>	
	        				
	     			 		<label  class="col-sm-12 control-label" for="sel1"></label>
	     			 		<label  class="col-sm-12" >Action to be taken for Disturbed Flag </label>	
  							<label  class="col-sm-1 control-label" for="sel1"></label>
								  
								  <label class="radio-inline">
								   		<input type='radio' name='partR' id='partINV' onclick='invalidR(partINV)' value=""/>
								   		Invalidate Local Body
								  </label>
								  <label class="radio-inline">
								  	<input type='radio' name='partR' id='partCOV' onclick='changeCov(partCOV)' value="" />
									Modify Local Govt Body
								  </label>
								  
								 
						
					</div>
					<div class="modal-footer">
						<button type='button' class='btn btn-info' name='Submit16' value='Get Value' onclick='doSomeAction()' >Get Value</button>
					    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      
					 </div>
					
		</div>
	</div>
	</div>
	
	
     </div>   
             
          
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>

</body>
</html>