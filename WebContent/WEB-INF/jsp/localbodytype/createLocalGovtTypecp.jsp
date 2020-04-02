<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>E-Panchayat</title> <script src="js/govtorder.js"></script>



	<script src="js/createlocalgovttype.js" type="text/javascript"></script>


	<script type="text/javascript" language="Javascript">
	</script>
	<script language="javascript">
	if ( window.addEventListener ) { 
	    window.addEventListener( "load",hidediv, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", hidediv );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = hidediv;
	 }

	$( document ).ready(function() {
		$('#createlocalgovtForm input[type=text]').attr("autocomplete",'off');
	});
	
	</script>
</head>
<body onload="hidediv();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">

                <div class="row">
               <%--  <c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>
			
		   <c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if> --%>
			
<%--  <div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div> --%>
                    <section class="col-lg-12">
                    <form:form method="POST" commandName="createlocalgovtType" action="draftLocalgovtType.htm" id="createlocalgovtForm"  class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftLocalgovtType.htm"/>"/>
                       <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CREATELOCALGOVTTYPE" htmlEscape="true"></spring:message></h3>
                                </div>
                                
                                <div class="box-header subheading"><h4><spring:message code="Label.CREATELOCALGOVTTYPE" htmlEscape="true"></spring:message></h4></div>
                                 
                        <div class="box-body">
                        <form:hidden path="govtOrderConfig" htmlEscape="true" value="${govtOrderConfig}"/>
                            <div class="form-group">
								<label  class="col-sm-3 control-label"for="txtlocalBodyTypeName"><spring:message  code="Label.LOCALGOVTTYPENAME" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
								<div class="col-sm-6" >
								   <form:input htmlEscape="true" path="localBodyTypeName"  class="form-control" onkeypress="validateAlphanumericKeys();" id="txtlocalBodyTypeName"
						            onfocus="validateOnFocus('txtlocalBodyTypeName'); helpMessage(this,'txtlocalBodyTypeName_msg');"	onblur="vlidateOnblur('txtlocalBodyTypeName','1','20','m'); hideHelp();" />
											<div><form:errors path="localBodyTypeName" htmlEscape="true" class="errormsg"></form:errors> </div> 
											<div id="txtlocalBodyTypeName_msg" style="display: none"> <spring:message code="error.blank.localBodyTypeName" htmlEscape="true"></spring:message> </div> 
											<div class="errormsg" id="txtlocalBodyTypeName_error"> <spring:message code="error.blank.localBodyTypeName" htmlEscape="true"></spring:message> </div>
									<div><form:errors path="localBodyTypeName1" class="errormsg" htmlEscape="true"></form:errors> </div>
									<div class="errormsg"><c:out value="${msgid}" escapeXml="true"></c:out></div>
								</div>
						   </div>   
						
						
						
						
						    <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message  code="Label.CATLOCALGOVTTYPE" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
								<div class="col-sm-6" >
								   <label class="radio-inline"><form:radiobutton	path="categoryRadio" value="U" id="urbanId" htmlEscape="true" onclick="showdiv(this.value);"  /> <spring:message code="Label.ULG" htmlEscape="true"></spring:message></label>
								   <label class="radio-inline"><form:radiobutton	path="categoryRadio" value="R" id="ruralId" onclick="showdiv(this.value);" htmlEscape="true"/> <spring:message code="Label.RURALG" htmlEscape="true"></spring:message></label>
								  <div	class="errormsg" id="urbanId_error" > <spring:message code="error.blank.category" htmlEscape="true" ></spring:message> </div>
								</div>
						  </div>
						  <div id="divRCategory" >
						     <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message code="Label.RURALCATEGORY" htmlEscape="true"></spring:message> <span class="errormsg">*</span></label>
								<div class="col-sm-6" >
								    <form:select	path="ruralCategory" id="ddRuralCategory"   class="form-control" onfocus="validateOnFocus('ddRuralCategory');helpMessage(this,'ddRuralCategory_msg');"	onblur="vlidateOnblur('ddRuralCategory','1','15','c');hideHelp();" >
												<form:option value="S" > <spring:message code="Label.SELECT"  htmlEscape="true"></spring:message> </form:option>
												<form:option value="P"> <spring:message code="Label.PRI" htmlEscape="true"></spring:message> 	</form:option>
												<form:option value="T" > <spring:message code="Label.TRADITIONALBODY"  htmlEscape="true"></spring:message></form:option>
									</form:select> 
									<div><form:errors path="ruralCategory" htmlEscape="true" class="errormsg"></form:errors> </div> 
									<div id="ddRuralCategory_msg" style="display: none"> <spring:message code="error.blank.Rcategory" htmlEscape="true"></spring:message> </div> 
									<div class="errormsg" id="ddRuralCategory_error"> <spring:message 	code="error.blank.Rcategory" htmlEscape="true"></spring:message> </div>
								</div>
						   </div>  
						   
						   
						   <div class="form-group">
								<label  class="col-sm-3 control-label"><spring:message code="Label.LEVEL" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
								<div class="col-sm-6" >
								   <form:select  path="level" id="ddlevel" class="form-control" onfocus="validateOnFocus('ddlevel');helpMessage(this,'ddlevel_msg');" onblur="vlidateOnblur('ddlevel','1','15','c');hideHelp();">
										<form:option value="S"><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
										<form:option value="D"><spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message></form:option>
										<form:option value="I"><spring:message code="Label.INTERMEDIATE" htmlEscape="true"></spring:message></form:option>
										<form:option value="V"><spring:message code="Label.VILLAGE" htmlEscape="true"></spring:message></form:option>
									</form:select>
									 <div><form:errors path="level" class="errormsg" htmlEscape="true"></form:errors></div>
									  <div id="ddlevel_msg" style="display: none"> <spring:message code="error.blank.level" htmlEscape="true"></spring:message> </div> 
									  <div class="errormsg" id="ddlevel_error"> <spring:message  code="error.blank.level" htmlEscape="true"></spring:message> </div>
								</div>
						   </div>  
						
						
                 </div> 
             
            </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                   <button type="submit" class="btn btn-success" name="Submit" onclick="return validateAll();"  ><spring:message code="Button.SAVEPUB" htmlEscape="true"></spring:message></button>
				  <button type="button" class="btn btn-warning" name="Submit6"  onclick="javascript:location.href='localgovtType.htm?<csrf:token uri='localgovtType.htm'/>';" ><spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
                  <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> </button>
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