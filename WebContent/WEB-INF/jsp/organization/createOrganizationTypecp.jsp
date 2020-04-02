<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<script src="js/organization.js"></script>


<script type="text/javascript" language="Javascript">
	
</script>
<script language="javascript">
	if ( window.addEventListener ) { 
	    window.addEventListener( "load",loadOrgTypePage, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", loadOrgTypePage );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = loadOrgTypePage;
	 }

	$( document ).ready(function() {
		$('#createOrganizationTypeForm input[type=text]').attr("autocomplete",'off');
	});
	
	
	
	</script>
</head>


<body onload="loadOrgTypePage();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" onload="loadOrgTypePage();">

	<section class="content">
	<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div>
						</td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>
	     </c:if>

                <div class="row">
                    <section class="col-lg-12">
                    <form:form method="POST" id="createOrganizationTypeForm" commandName="createOrganizationType" action="saveOrganizationType.htm" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="saveOrganizationType.htm"/>"/>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CREATEORGTYPE" htmlEscape="true"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message code="Label.GENERALDETAILS" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message 	code="Label.ORGTYPENAME" htmlEscape="true"></spring:message>  <span class="mandatory">*</span></label>
								<div class="col-sm-6" >
									<form:input htmlEscape="true" id="txtorgTypeName" path="orgTypeName"  class="form-control" onkeypress="validateAlphanumericKeys();"
											onfocus="validateOnFocus('txtorgTypeName');helpMessage(this,'txtorgTypeName_msg');" onblur="vlidateOnblur('txtorgTypeName','1','35','m');hideHelp();"></form:input>

										<div id="txtorgTypeName_msg" style="display: none"><spring:message code="error.blank.orgTypeName" htmlEscape="true"></spring:message> </div> 
										<div class="errormsg" id="txtorgTypeName_error"><spring:message code="error.blank.orgTypeName" htmlEscape="true"></spring:message> </div> 
										<div><form:errors path="orgTypeName" class="errormsg" htmlEscape="true"></form:errors> </div>
										<br/> <div><form:errors path="orgTypeName1" class="errormsg" htmlEscape="true"></form:errors> </div>
								  </div>
						</div>   
                 </div> 
             
                     
              
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="submit" name="Save" id="btnSave" class="btn btn-success" onclick="return vaildateOrgTypeAll();"  ><i class="fa fa-floppy-o"></i> Submit</button>
                   <button type="button" name="Submit3" class="btn btn-warning" id="btnClear" onclick="resetOrgTypeForm();" ><spring:message code="Button.CLEAR" htmlEscape="true"></spring:message> </button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>
