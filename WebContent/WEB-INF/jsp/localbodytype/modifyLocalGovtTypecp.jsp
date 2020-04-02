<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="js/cancel.js"></script>

<title><spring:message code="Label.MODIFYLGTYPE"
		htmlEscape="true"></spring:message>
</title>
<head>
<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/dynCalendar.js" type="text/javascript"></script>
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/dynCalendar.css" rel="stylesheet" type="text/css" />


<script src="js/createlocalgovttype.js" type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>

<script type="text/javascript">
	
function setValue(category)
{
	alert("hi");
	var category=document.getElementById('category').value;
	var level=document.getElementById('listGovtTypeDetail[0].level').value;
	alert(category);
	alert(level);
/* if(category=="P")
	{ */
	document.getElementById('ddRuralCategory').value=category;
	document.getElementById('ddlevel').value=level;
	
/* 	} */
	
}
function loadPage() {
		var mypath = window.location.href;
		var mySplitResult = mypath.split("&");
		if (mySplitResult[1] != "") {
			var type = mySplitResult[1].replace("type=", "");
		}
	}

	function disableLevel() {
		if (document.getElementById('rdoPRI').checked) {
			document.getElementById('ddlevel').disabled = true;
		}
		if (document.getElementById('rdoTrad').checked) {
			document.getElementById('ddlevel').disabled = false;
			document.getElementById('rdoPRI').disabled = true;
		}

	}
	function hidediv() {
		var ruralId = document.getElementById('ruralId');
		if (ruralId.checked) {
			document.getElementById('divRCategory').style.display = 'block';
		} else {
			document.getElementById('divRCategory').style.display = 'none';
		}
	}

	function showdiv(id) {

		if (id == 'R') {
			document.getElementById('divRCategory').style.display = 'block';
		} else {
			document.getElementById('divRCategory').style.display = 'none';
		}
	} 
	function onSave() {
		var errors = new Array();

		var error = false;
		errors[0] = !officialAddress();

		if (errors[0] == true) {
			error = true;
		}

		if (error == true) {

			showClientSideError();

			return false;
		} else {
			return true;
		}

		return false;
	}

	function getLevel() {
		if (document.getElementById('txtLevel').value == "D")
			document.getElementById('ddlevel').selectedIndex = 1;
		else if (document.getElementById('txtLevel').value == "I")
			document.getElementById('ddlevel').selectedIndex = 2;
		else if (document.getElementById('txtLevel').value == "V")
			document.getElementById('ddlevel').selectedIndex = 3;

		if (document.getElementById('urbanId').checked)
			showdiv('U');
	}
	function officialAddress() {

		if (document.getElementById("OfficialAddress").value == "") {
			document.getElementById("OfficialAddress_error").innerHTML = "Local Government Type Name is Required";
			$("#OfficialAddress_error").show();
			$("#OfficialAddress_msg").hide();
			$("#OfficialAddress").addClass("error_fld");
			$("#OfficialAddress").addClass("error_msg");
			return false;

		} else {
			$("#OfficialAddress_msg").hide();
			return true;

		}
	}
</script>

</head>
<body onload="getLevel();loadModifyLBTypePage();"  onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="draftModifyLocalgovtType.htm" method="POST" commandName="modifyLocalGovtTypeCmd" id="frmModifyVillage" enctype="multipart/form-data">
		              <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftModifyLocalgovtType.htm"/>" />
		             
                                
   <div id="frmcontent" onload="load_page();hidediv();">                             
     <div class="box">
      <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYLGTYPE"></spring:message></h3>
                                </div>
        <div class="box-header subheading">
                          <h4><spring:message htmlEscape="true" code="Label.VIEWLOCALGOVTBODYDET"></spring:message></h4>
                        </div>
       <div class="box-body">
        <c:if test="${modifyLocalGovtTypeCmd.correction==true}">
          <div class="box-header subheading">
					<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
			</div>
        
           <c:forEach var="listdetail" varStatus="listLocalGovtTypeRow" items="${modifyLocalGovtTypeCmd.listdetail}">
             <form:hidden path="govtOrderConfig" htmlEscape="true" value="${govtOrderConfig}"/>
             <div class="form-group">
               <label class="col-sm-3 control-label" for = "txtlocalBodyTypeName"> <spring:message code="Label.NewnameLBT" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
               <div class="col-sm-6">
                 	<spring:bind path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].localBodyTypeName">
									<input type="text" class="form-control"  name="<c:out value="${status.expression}"></c:out>" value="<c:out value="${status.value}" escapeXml="true"/>"
														id="txtlocalBodyTypeName"  onfocus="show_msg('txtlocalBodyTypeName')" onblur="validateLbType()" />
													<div class="error" id="txtlocalBodyTypeName_error"></div>
												</spring:bind> 
								<spring:bind path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].localBodyTypeCode">
									<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
					</spring:bind> 
               </div>
             </div>
             
              <div class="form-group">
               <label class="col-sm-3 control-label" for = "ruralId"> <spring:message code="Label.CATLOCALGOVTTYPE" htmlEscape="true"></spring:message></label>
               <div class="col-sm-6">
                 	<label class="radio-inline">						
						<spring:bind path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].category">
								<input type="radio"	name="<c:out value="${status.expression}"/>" value="R" 	<c:if test="${status.value == 'R'}">checked</c:if> id="ruralId" onclick="showdiv(this.value);" />
						</spring:bind>
			 			<spring:message code="Label.RURALG" htmlEscape="true"></spring:message>
			 	    </label>
			 	    
			 	    
			   <label class="radio-inline">
					 <spring:bind path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].category">
				        <input type="radio"	name="<c:out value="${status.expression}"/>" value="U" <c:if test="${status.value == 'U'}">checked</c:if> id="urbanId" onclick="showdiv(this.value);" />
					</spring:bind> 
					<spring:message code="Label.ULG" htmlEscape="true"></spring:message> 
					<div id="urbanId_error" class="errormsg"><spring:message code="Label.SELECTRURALBODY" htmlEscape="true"></spring:message></div>
			  </label>
               </div>
             </div>
             
             <div id="divRCategory">
              <div class="form-group">
               <label class="col-sm-3 control-label" for = "ruralId"> </label>
               <div class="col-sm-6">
                 	<label class="radio-inline">	
                     <spring:bind 	path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].ispartixgovt">
																<input type="radio" name="<c:out value="${status.expression}"/>" value="true" id="rdoPRI" onclick="disableLevel();"
																	<c:if test="${status.value == true}">checked</c:if> />
															</spring:bind><spring:message code="Label.PRI" htmlEscape="true"></spring:message>
             
             
             
                </label>
                <label class="radio-inline">
	                 <spring:bind 	path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].ispartixgovt">
							<input type="radio" name="<c:out value="${status.expression}"/>" value="false" id="rdoTrad" onclick="disableLevel();"
										<c:if test="${status.value == false}">checked</c:if> />
					</spring:bind><spring:message code="Label.TRADITIONALBODY" htmlEscape="true"></spring:message>
					<div id="rdoTrad_error" class="errormsg"><spring:message code="Label.SELECTRURALBODY" htmlEscape="true"></spring:message></div>
                </label>
                
                </div>
                </div>
                
                <div class="form-group">
                 <label class="col-sm-3 control-label" for = "ddlevel"><spring:message code="Label.LEVEL" htmlEscape="true"></spring:message> <span class="errormsg">*</span></label> 
                 <div class="col-sm-6">
                    <form:select  path="level" id="ddlevel" class="form-control">
									<form:option value="S"><spring:message code="Label.SELECT" htmlEscape="true"></spring:message>	</form:option>
									<form:option value="D"><spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message></form:option>
									<form:option value="I"><spring:message code="Label.INTERMEDIATE" htmlEscape="true"></spring:message></form:option>
									<form:option value="V"><spring:message code="Label.VILLAGE" htmlEscape="true"></spring:message></form:option>
							</form:select>
							<div class="errormsg" id="ddlevel_error"> <spring:message code="error.blank.level" htmlEscape="true"></spring:message> </div>
							<form:errors path="level" class="errormsg"  htmlEscape="true"></form:errors>
					<spring:bind path="modifyLocalGovtTypeCmd.listdetail[${listLocalGovtTypeRow.index}].level">
							<input type="hidden" id="txtLevel" maxlength="1"	class="form-control" name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
                      </spring:bind> 
                 </div>
                
                
                </div>
             </div>
           
         <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                	<button type="submit" name="Submit" class="btn btn-success"  	onclick="return validateAllforModify();" > <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button> 
					<button type="button" name="Submit3" class="btn btn-warning" id="btnClear" onclick="resetModifyForm();" > <spring:message code="Button.CLEAR" htmlEscape="true"></spring:message></button>
					<button type="button" name="Submit3" class="btn btn-danger"  onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
                 </div>
           </div>   
       </div> 
           
           
           
           </c:forEach>
        
        </c:if>
        
        
         <c:if test="${modifyLocalGovtTypeCmd.correction==false}">
					  <font color="red"> 
					 	<ul class = "listing">
						 	<li>
								<div  align="center"><b>Cannot change the type of a local government body as the type is included in a local government setup of following states-</b> </div>
						 		<div  align="center"><b><c:out value="${stateList}" escapeXml="true"></c:out></b></div>
					 		</li>
				 		</ul>
					</font>
					 
					 
      <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
               <div class="pull-right">
				<button type="button" name="Submit3" class="btn btn-danger" value="" onclick="javascript:go('home.htm');" ><spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button> 
			</div>
		</div>
		</div>	
					  </c:if> 
       </div>
      
         
   
     </div>  
     
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script></body>
		</div>
	</div>
</body>
</html>