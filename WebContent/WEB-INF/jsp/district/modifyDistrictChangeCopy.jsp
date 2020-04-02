<%@include file="../common/taglib_includes.jsp"%> 

<html>
<head>

<title>
	<spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message>
</title>
<meta http-equiv="Content-Type" content="text/html;  charset=utf-8" />
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<!-- for Unique constrain  -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCommonService.js'></script>
<!-- for Unique constrain  -->

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script>
	
	function valdiateDistrictSubmit()
	{
		
		var error=false;
		var mandatory_change_error=false;
		var mandatory_error=false;
		districtNameInEnch=document.getElementById('districtname').value; 
		districtNameInEn=document.getElementById('districtnameold').value; 
		districtNameInLc=document.getElementById('txtNameLocal').value; 
		aliasNameInEn=document.getElementById('txtAliasEnglish').value; 
		aliasNameInLc=document.getElementById('txtAliasLocal').value; 
		
		if (!validateEntityEnglishNameBlank(districtNameInEnch, '#districtNameEngBlank_error'))
			{
			error = true;
			mandatory_error=true;
			}
		if (!validateDistrictEnglishNameData(districtNameInEnch, '#districtNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(districtNameInLc, '#districtNameLocData_error'))
			error = true;
		
		
		if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
			error = true;
		if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
			error = true;
		
		if(mandatory_error==true)
			showClientSideError();
		else
			{
			if((districtNameInEn==districtNameInEnch))
				mandatory_change_error=true;
			
			 if(mandatory_change_error==true)
				{
				
				error=true;
				
				$("#alertboxTitle").text("Error Message");
				$("#alertboxbody").text("<spring:message code='error.change.commonAlert' htmlEscape='true'></spring:message>");
				$('#alertbox').modal('show');
				}
			}
		if(error==true)
			return false;
		else
			return true;
		
		
	}
	
	
	function loadMe()
	{
		//alert("hi");
		previousEntityName=document.getElementById('districtnameold').value;
		var errorflag='<c:out value="${modifyDistrictCmd.errorflag}" escapeXml="true"></c:out>';
		//alert(errorflag);
		if(errorflag=="2")
			{
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("<spring:message code='error.blank.commonAlert' htmlEscape='true'></spring:message>");
			$('#alertbox').modal('show');
			
			}
		else if(errorflag=="1"){
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("<spring:message code='error.change.commonAlert' htmlEscape='true'></spring:message>");
			$('#alertbox').modal('show');
			
		}
		hideAll();
			
	}
	function hideAll()
	{
		
		
		$("#districtNameEngBlank_error").hide();
		$("#districtNameEngData_error").hide();
		$("#districtNameLocData_error").hide();
		$("#aliasNameEngData_error").hide();
		$("#aliasNameLocData_error").hide();
		$("#districtname_error").hide();
		$("#entityNameInEnExist_error").hide();
		
		//error.alredyExist.districtNameEnglish 
		
		
	}
	
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",loadMe, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", loadMe );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = loadMe;
	  }
</script>
</head>

<body>
	<section class="content">
	<div class="row">
        	<!-- main col -->
              <section class="col-lg-12 ">
	              	<div class="box ">
	              	
	              		<div class="box-header with-border">
               				<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYDISTRICT"></spring:message></h3>
               			</div>
		              	<div class="box-body">
								<div class="box-header subheading">
			                              <h4><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></h4>
			                    </div>
			                    
			                    <form:form action="draftModifyDistrict.htm" class="form-horizontal" method="POST" commandName="modifyDistrictCmd" id="frmModifyDistrict">
			                    <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='draftModifyDistrict.htm'/>" />
			                    <c:forEach var="listDistrictDetails" varStatus="listDistrictDetailsRow" items="${modifyDistrictCmd.listDistrictDetails}">
			                    
			                    <div class="form-group">
                      					<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message><span class="mandatory">*</span></label>
	                      				<div class="col-sm-6">
		                      				<spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglishch">
												<input type="text" onkeypress="hideAll();" maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>" id="districtname" onfocus="validateOnFocus('districtname');helpMessage(this,'districtname_msg');" onblur="validateEntityNameExist(${stateCode},'D',this.value,'districtname');vlidateOnblur('districtname','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('districtname')" />		
										     	<div class="error" id="districtname_error"><spring:message htmlEscape="true" code="error.required.DISTRICTNAMEEN"></spring:message></div> 
											</spring:bind>
											<div id="entityNameInEnExist_error" class="errormsg">
                                          <spring:message
                                                code="error.alredyExist.districtNameEnglish" htmlEscape="true"></spring:message>
                                          </div>
											<div class="mandatory" id="districtNameEngBlank_error">
												<spring:message htmlEscape="true" code="error.blank.districtNameInEn"></spring:message>
									       </div>
									       <div class="mandatory" id="districtNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.DistrictNameEng"></spring:message>
									       </div>
											<form:errors path="districtNameEnglishch" class="mandatory" htmlEscape="true"></form:errors> 
										<div class="mandatory"></div>
										<div id="districtname_msg" style="display:none"><spring:message htmlEscape="true" code="Label.EnterDistrictName"></spring:message><%-- <spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/> --%></div>
	                      				</div>
                   				</div>
			                    
			                    
			                    <div class="form-group">
			                    	<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTNAMEINLOCAL" htmlEscape="true"></spring:message></label>
				                    <div class="col-sm-6">
					                    <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameLocalch">
											<input type="text" id="txtNameLocal" onkeypress="hideAll();"  maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>"	value="<c:out value="${status.value}" escapeXml="true"/>" onfocus="validateOnFocus('txtNameLocal');helpMessage(this,'txtNameLocal_msg');" onblur="vlidateOnblur('txtNameLocal','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('txtNameLocal')" />
										</spring:bind>
										
										
										<div class="mandatory" id="districtNameLocData_error">
											<spring:message htmlEscape="true" code="Error.data.DistrictNameLocal"></spring:message>
								        </div>
										<form:errors path="districtNameLocalch" class="mandatory" htmlEscape="true"></form:errors> 
										<div class="mandatory"></div>
										<div id="txtNameLocal_msg" style="display:none"><spring:message htmlEscape="true" code="Label.EnterDistrictNameLocal"></spring:message><%-- <spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/> --%></div>
									</div>
			                    </div>
			                    
			                    
			                    <div class="form-group">
			                    	<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTALIASENGLISH" htmlEscape="true"></spring:message></label>
				                    <div class="col-sm-6">
					                    <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasEnglish" >
												<input type="text" id="txtAliasEnglish" onkeypress="hideAll();" maxlength="50" class="form-control" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" escapeXml="true"/>"  />
										</spring:bind>
										<div class="mandatory" id="aliasNameEngData_error">
											<spring:message htmlEscape="true" code="Error.data.DistrictAliasNameEng"></spring:message>
									     </div>
										<form:errors path="aliasEnglish" class="mandatory" htmlEscape="true"></form:errors> 
										<div class="mandatory"></div>
										
									</div>
			                    </div>
			                    
			                    
			                    <div class="form-group">
			                    	<label for="districtNameInEn" class="col-sm-3 control-label"><spring:message code="Label.DISTRICTALIASLOCAL" htmlEscape="true"></spring:message></label>
				                    <div class="col-sm-6">
					                    <spring:bind path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].aliasLocalch">
											<input type="text" id="txtAliasLocal"onkeypress="hideAll();" maxlength="50"class="form-control" name="<c:out value="${status.expression}"/>"value="<c:out value="${status.value}"  escapeXml="true"/>" />
										</spring:bind> 
										<div class="mandatory" id="aliasNameLocData_error">
												<spring:message htmlEscape="true" code="Error.data.DistrictAliasNameLocal"></spring:message>
									       </div>
											<form:errors path="aliasLocalch" class="mandatory" htmlEscape="true"></form:errors> 
										<div class="mandatory"></div>
										<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtNameEnglish">
												<input type="hidden" id="districtnameold"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind>
										<spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].districtCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].stateCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyDistrictCmd.listDistrictDetails[${listDistrictDetailsRow.index}].stateVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> </label>
										
									</div>
			                    </div>
			                    
			                    </c:forEach>
			                    <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>
								<form:input path="districtId" type="hidden" name="districtId" id="districtId"  htmlEscape="true" />	
								<form:hidden path="operation" value="M" htmlEscape="true"/>
			                    
			                    
			                    <div class="box-footer">
                    				 <div class="col-sm-offset-2 col-sm-10">
				                       <div class="pull-right">
				                            <button type="submit" name="Submit" class="btn btn-success" onclick="return valdiateDistrictSubmit();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
												
											<%-- <button type="button" name="Submit3" class="btn btn-warning"  onclick="javascript:location.href='modifyDistrictchangebyId.htm?<csrf:token uri='modifyDistrictchangebyId.htm'/>&districtId=${modifyDistrictCmd.districtId }';"  ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>	 --%>
												
											<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
				                        </div>
                     				</div>   
                  				</div>
                  				
                  				 <div class="modal fade" id="alertbox" tabindex="-1" role="dialog" >
								<div class="modal-dialog" >
								<div class="modal-content">
					  				<div class="modal-header">
					   				 
					    			  	<h4 class="modal-title" id="alertboxTitle">Change District</h4>
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
	              	</div>
	              	
             </section>
	
	</div>
	
	
	</section>
	

</body>
</html>