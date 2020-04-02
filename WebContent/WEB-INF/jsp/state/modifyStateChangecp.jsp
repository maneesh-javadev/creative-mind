<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/shiftdistrict.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="js/govtorder.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
<script src="js/validation.js"></script>
<script src="js/helpMessage.js"></script>

<%!String allowedNoOfAttachment = "5";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCommonService.js'></script>



<head>

<script>
	var stateradio;
	var changEntityFlag='true';
	function show_msg(Field_msg) {
		var hint = '#' + Field_msg + "_msg";
		var error = '#' + Field_msg + "_error";
		$("#" + Field_msg).removeClass("error_fld");
		$("#" + Field_msg).removeClass("error_msg");
		$(hint).show();
		$(error).hide();

	}
	function resetVillageModifyForm()
	{
		document.getElementById('statename').value="";
		document.getElementById('txtAliasLocal').value="";
		document.getElementById('txtAliasEnglish').value="";
		document.getElementById('txtNameLocal').value="";

	}
	function getGisNodes1() {
		if (document.getElementById('txtlatitude').value != '') {
			var gisList = document.getElementById('txtlatitude').value
					.split(':');
			//i=gisList.length;

			document.getElementById('txtlatitude').value = gisList[0]
					.split(',')[0];
			document.getElementById('txtLongitude').value = gisList[0]
					.split(',')[1];

			for ( var k = 1; k < gisList.length; k++) {
				addgisnodes1();
				document.getElementById('lati' + k).value = gisList[k]
						.split(',')[0];
				document.getElementById('longi' + k).value = gisList[k]
						.split(',')[1];
			}
		}
	}

	function districtName() {
		$("#districtname_error").hide();

	}
	
	
	function valdiateStateSubmit()
	{
		
		var error=false;
		var mandatory_change_error=false;
		var mandatory_error=false;
		var stateNameInEnch=document.getElementById('statename').value; 
		var stateNameInEng=document.getElementById('stateNameEng').value;
		var stateNameInLc=document.getElementById('txtNameLocal').value; 
		var stateradioch=document.getElementById('stateradio').checked; 
		var sortNameEng=document.getElementById('sortName').checked;
	/* 	utradio=document.getElementById('utradio'); 
		alert(stateradio.checked);
		alert(utradio.checked); */
		//if(stateradio.checked)
		/* aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
		aliasNameInLc=document.getElementById('txtAliasLocal').value;  */
		
		if (!validateEntityEnglishNameBlank(stateNameInEnch, '#stateNameEngBlank_error'))
	{
			error = true;
			mandatory_error=true;
	}		
		
		if (!validateEntityEnglishNameData(stateNameInEnch, '#stateNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(stateNameInLc, '#stateNameLocData_error'))
			error = true;
		
		if (!validateEntityEnglishNameData(sortNameEng, '#sortNameEngData_error'))
			error = true;
		/* if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
			error = true; */
			/* alert(stateradio);
			alert(stateradioch); */
			
			if(mandatory_error==true)
				{
				
				}
				//showClientSideError();
			else
				{
				if((stateNameInEnch==stateNameInEng) && (stateradioch==stateradio))
					mandatory_change_error=true;
					
				
			 if(mandatory_change_error==true)
					{
					alert("<spring:message code='error.change.commonAlert' htmlEscape='true'></spring:message>");
					error=true;
					//showNoChaneClientSideError();
					}
				
				}
			
		
		
		if(error==true)
			return false;
		else
			return true;
		
		
	}
	
	
	function hideError()
	{
		previousEntityName=document.getElementById('statename').value;
		$("#entityNameInEnExist_error").hide();
		stateradio=document.getElementById('stateradio').checked; 
		hideAll();
		var errorflag='${modifyStateCmd.errorflag}';
		//alert(errorflag);
		if(errorflag=="2")
			{
			//showClientSideError();
			}
		else if(errorflag=="1"){
			
		}
			//showNoChaneClientSideError();
		
	}
	function hideAll()
	{
		$("#stateNameEngBlank_error").hide();
		$("#stateNameEngData_error").hide();
		$("#stateNameLocData_error").hide();
		$("#sortNameEngData_error").hide();
		
		
	}
	
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideError, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideError );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideError;
	  }
</script>
</head>

<body onload='onLoadSelectDisturbed(); districtName();'>	
<section class="content">
     <div class="row">
        <section class="col-lg-12">
             <div class="box">
              <div class="box-header with-border">
                 <h3 class="box-title">STATE </h3> </div>
    <div class="box-header subheading">
        <h3 class="box-title"><spring:message code="Label.CHANGE" htmlEscape="true"></spring:message></h3>
     </div>
         <c:if test="${!empty param.family_msg}">
           <div class="alert alert-success">
          <strong>Success!<c:out value="${param.family_msg}"></c:out></strong>
          </div>
	     </c:if>
				
		<c:if test="${!empty family_error}">
			<div class="alert alert-danger">
                  <strong>Failure Message! <c:out value="${family_error}"></c:out></strong>
                </div>
		</c:if>                       
      <div class="box-body">
       <form:form action="draftChangeState.htm" method="POST" commandName="modifyStateCmd" id="frmModifyDistrict" class="form-horizontal">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftChangeState.htm"/>"/>
		<c:forEach var="listStateDetails" varStatus="listStateDetailsRow" items="${modifyStateCmd.listStateDetails}">
			<div class="form-group">
			  <label for="statename" class="col-sm-3 control-label"><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			   <div class="col-sm-6">
			   <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglishch">
			     <input type="text" onkeypress="hideAll();" id="statename" maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" escapeXml="false"/>" id="statename"   	onfocus="validateOnFocus('statename');" 
								onblur="validateEntityNameExist(null,'S',this.value,'statename');vlidateOnblur('statename','1','15','c');"  />
			       </spring:bind>
			        <!-- <span class="mandatory" id="districtname_error">State Name (In English) is Required</span> -->
			      
			      <div id="entityNameInEnExist_error" class="errormsg">  
			        <spring:message  code="error.alredyExist.stateNameEnglish" htmlEscape="true"></spring:message>
                  </div>
                  <div class="errormsg" id="stateNameEngBlank_error">
						<spring:message htmlEscape="true" code="error.blank.stateNameInEnch"></spring:message>
				 </div>
				<div class="errormsg" id="stateNameEngData_error">
								<spring:message htmlEscape="true" code="Error.data.StateNameEng"></spring:message>
				</div>
				<form:errors path="stateNameEnglishch" class="errormsg" htmlEscape="true"></form:errors> 
				<div class="errormsg"></div>
			    
			   </div>
	       </div>	
	       				
	<div class="form-group">
			<label for="txtNameLocal" class="col-sm-3 control-label"><spring:message code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message> </label> 
		 <div class="col-sm-6">	
			<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameLocal">
				<input type="text" id="txtNameLocal" onkeypress="hideAll();" maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="false"/>"  />
			</spring:bind> 
			<div class="errormsg" id="stateNameLocData_error">
						<spring:message htmlEscape="true" code="Error.data.stateNameLoc"></spring:message>
			 </div>
			<form:errors path="stateNameLocal" class="mandatory" htmlEscape="true"></form:errors> 
		 </div>
	  </div>
		
		<div class="form-group">
            <label for="sortName" class="col-sm-3 control-label"><spring:message code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message> </label>
           <div class="col-sm-6">	
              <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].shortName" >
					 <input type="text" id="sortName" onkeypress="hideAll();" maxlength="2" class="form-control" name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" escapeXml="false"/>"  />	</spring:bind>
				<div class="errormsg" id="sortNameEngData_error">
						<spring:message htmlEscape="true" code="Error.data.StateSortNameEng"></spring:message>
				</div>				 
				<form:errors path="shortName" class="mandatory" htmlEscape="true"></form:errors> 
		  </div>
		</div>
	<div class="form-group">
		<div style="display:none;">
		        <form:errors path="warningFlag" class="mandatory" htmlEscape="true"></form:errors>
		 </div>
				<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateNameEnglish">
							<input type="hidden" id="stateNameEng" 	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
				</spring:bind> 
				<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
						<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
				</spring:bind>
											
				<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateCode">
						<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" /> 
				</spring:bind> 
				<%-- <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateVersion">
					<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
				</spring:bind>  --%>
				<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].minorVersion">
					<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
				</spring:bind>
		       <spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateOrUt">
					<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" />
				</spring:bind> 
	       </div>
					<div class="form-group">
							<label class="col-sm-3 control-label"><spring:message code="Label.STATUS" htmlEscape="true"></spring:message> <span class="mandatory">*</span>
							</label>
						<div class="col-sm-6">			
							<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateOrUtch">
							    <label class="radio-inline"><input type="radio" id="stateradio" name="<c:out value="${status.expression}" escapeXml="false"/>"    value="S" <c:if test="${status.value =='S'}">checked</c:if> /><spring:message code="Label.STATE" htmlEscape="true"></spring:message></label>
						   </spring:bind>
						     
								<spring:bind path="modifyStateCmd.listStateDetails[${listStateDetailsRow.index}].stateOrUtch">
								  <label class="radio-inline"> <input type="radio" id="utradio" name="<c:out value="${status.expression}" escapeXml="false"/>"  value="U" <c:if test="${status.value == 'U'}">checked</c:if>  /> <spring:message code="Label.UT" htmlEscape="true"></spring:message></label>
								</spring:bind>
								</div>
						 </div>
								
			</c:forEach>
				<div class="form-group"><form:input path="stateId" type="hidden" name="stateId" id="stateId"  />	
						<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" />
						<form:hidden path="operation" value="M" />
				</div> 
					
					
		<div class="box-footer">
                   <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
                            <button type="submit" name="Submit" id="Submit" class="btn btn-success "  value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" onclick="return valdiateStateSubmit();" ><i class="fa fa-floppy-o"></i> Submit</button>
                           <c:if test="${reqWarningFlag!=null}">
									<button type="button" name="Submit3"  class="btn btn-danger " value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>" onclick="javascript:location.href='modifyStatechangebyId.htm?<csrf:token uri='modifyStatechangebyId.htm'/>&stateId=${modifyStateCmd.stateId}';" ><i class="fa fa-times-circle"></i> Close</button>
                            </c:if>
                            <c:if test="${reqWarningFlag==null}">
                             <button type="button" name="Submit3" class="btn btn-danger " value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                           </c:if>
                        </div>
                     </div>   
                 </div>	
                 		
			  </form:form> 
             </div>
           </div>
         </section>
       </div>
      </section>
    
</body>
</html>