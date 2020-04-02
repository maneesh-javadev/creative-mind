<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<%@include file="../common/taglib_includes.jsp"%>
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
	
	<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>

	<script src="js/govtorder.js"></script>
	<script src="js/common.js"></script>
	
	
	<script type="text/javascript" src="js/cancel.js"></script>
	
	<title><spring:message code="Label.MODIFYBLOCK"></spring:message>
	</title>
	<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
	<script>
	
function loadMe()
{
	hideAll();
	previousEntityName=document.getElementById('blockname').value;
	var errorflag='<c:out value="${modifyBlockCmd.errorflag}" escapeXml="true"></c:out>';
	//alert(errorflag);
	if(errorflag=="2")
		{
		showClientSideError();
		}
	else if(errorflag=="1")
		showNoChaneClientSideError();
	
}

function hideAll()
{
	$("#blockNameEngBlank_error").hide();
	$("#entityNameInEnExist_error").hide();
	$("#blockNameEngData_error").hide();
	$("#blockNameLcData_error").hide();
	$("#aliasNameEngData_error").hide();
	$("#aliasNameLocData_error").hide();

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

function validateBlockChange()
{
	
	var mandatory_change_error=false;
	var mandatory_error=false;
	var blockNameEngch=document.getElementById("blocknamech").value;
	var blockNameEng=document.getElementById("blockname").value;
	var blockNameLc=document.getElementById("txtNameLocal").value;
	var aliasNameInEn=document.getElementById("txtAliasEnglish").value;
	var aliasNameInLc=document.getElementById("txtAliasLocal").value;
	var error=false;
	if(blockNameEngch.length<=0)
		{
		$("#blockNameEngBlank_error").show();
		error=true;
		mandatory_error=true;
		}
	
	else if (!validateBlockNameEnglish(blockNameEngch, "#blockNameEngData_error"))
			error = true;
		
	

if (!validateEntityNameLocalData(blockNameLc, '#blockNameLcData_error'))
	error = true;
	
if (!validateEntityEnglishNameData(aliasNameInEn, '#aliasNameEngData_error'))
	error = true;
if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
	error = true;
	
if(mandatory_error==true)
	showClientSideError();
else
	{
	if((blockNameEng==blockNameEngch))
		mandatory_change_error=true;
	
	 if(mandatory_change_error==true)
		{
		
		error=true;
		//showNoChaneClientSideError();
		  $("#blockname_error").html("You have not done any changes in mandatory Field");
		
		}else{
			 $("#blockname_error").html("");
		}
	}

	
	if(error==true)
		{
		return false;
		}
	else
		{
		return true;
		}
	
}

</script>

</head>

	<body>
	
	<section class="content">
		<div class="row">
        	<section class="col-lg-12 ">
           		<div class="box ">
           			<div class="box-header with-border">
           				<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MODIFYBLOCK"></spring:message></h3>
           			</div>
	              	<div class="box-body">
						<div class="box-header subheading">
	                              <h4><spring:message htmlEscape="true" code="Label.CHANGE"></spring:message></h4>
	                    </div>
	                    <form:form action="modifyBlockAction.htm" method="POST" class="form-horizontal" commandName="modifyBlockCmd" id="frmModifyBlock">
							<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyBlockAction.htm"/>" />
							<c:forEach var="listBlockDetails" varStatus="listBlockDetailsRow"	items="${modifyBlockCmd.listBlockDetails}">
							
							<div class="form-group">
			                    	<label  class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWBLOCKENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
				                    <div class="col-sm-6">
										<spring:bind path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglishch">
											<input type="text" class="form-control" maxlength="50" onchange="hideAll();"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="true"/>" id="blocknamech"  onblur="validateEntityNameExist(${modifyBlockCmd.dlc},'B',this.value,'blocknamech');"  escapeXml="false" />
											<span  id="blockname_error" class="errormsg"></span>
										</spring:bind>
									</div>
									<div class="errormsg" id="blockNameEngBlank_error">
												<spring:message htmlEscape="true" code="Error.blank.BlockNameEng"></spring:message>
									       </div>
									        <div id="entityNameInEnExist_error" class="errormsg">
                                          <spring:message
                                                code="error.alredyExist.blockNameEng" htmlEscape="true"></spring:message>
                                          </div>
									        <div class="errormsg" id="blockNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.BlockNameEng"></spring:message>
									       </div>
												 <form:errors path="blockNameEnglishch" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
			                    </div>
							
							
								<div class="form-group">
			                    	<label  class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWBLOCKLOCAL" htmlEscape="true"></spring:message></label>
				                    <div class="col-sm-6">
					                    <spring:bind
													path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameLocalch">
													<input type="text" id="txtNameLocal"
														onkeypress="validateCharKeys(event)" maxlength="50"
														onchange="hideAll();"
														class="form-control"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														 escapeXml="false" />
											</spring:bind>
										<div class="errormsg" id="blockNameLcData_error">
											 <spring:message htmlEscape="true" code="Error.data.BlockNameLc"></spring:message>
									       </div>
												<form:errors path="blockNameLocalch" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										
									</div>
			                    </div>
							
							
								<div class="form-group">
			                    	<label  class="col-sm-3 control-label"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label>
				                    <div class="col-sm-6">
					                    <spring:bind
													path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasEnglishch">
													<input type="text" id="txtAliasEnglish"
														onkeypress="validateCharKeys(event)" maxlength="50"
														onchange="hideAll();"
														class="form-control"
														name="<c:out value="${status.expression}" />"
														value="<c:out value="${status.value}" escapeXml="true"/>"
														escapeXml="false" />

										</spring:bind>
										<div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.data.BlockAliasNameEng"></spring:message>
									       </div>
												<form:errors path="aliasEnglishch" cssClass="errormsg" htmlEscape="true"></form:errors>
											<div class="errormsg"></div>
										
									</div>
			                    </div>
			                    
			                    
			                   <div class="form-group">
			                    	<label  class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
				                    <div class="col-sm-6">
					                    <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].aliasLocalch">
												<input type="text" id="txtAliasLocal"
													onkeypress="validateCharKeys(event)" maxlength="50"
													onchange="hideAll();"
													class="form-control"
													name="<c:out value="${status.expression}" />"
													value="<c:out value="${status.value}" escapeXml="true" />"
													/>
											</spring:bind>
											
										<div class="errormsg" id="aliasNameLocData_error">
											<spring:message htmlEscape="true" code="Error.data.BlockAliasNameLocal"></spring:message>
								       </div>
											<form:errors path="aliasLocalch" cssClass="errormsg" htmlEscape="true"></form:errors> 
										 <div class="errormsg"></div> 
											<spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockNameEnglish">
												<input type="hidden" id="blockname"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind>
											<spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].blockVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].districtCode">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind> <spring:bind
												path="modifyBlockCmd.listBlockDetails[${listBlockDetailsRow.index}].districtVersion">
												<input type="hidden"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
											</spring:bind>
										
									</div>
			                    </div> 
			                    
							</c:forEach>
							<form:input path="blockId" type="hidden" name="blockId" id="blockId"  />
						
									<div class="box-footer">
	                    				 <div class="col-sm-offset-2 col-sm-10">
					                       	<div class="pull-right">
					                            <button type="submit" name="Submit" class="btn btn-success" onclick="return validateBlockChange();"><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
													
												<button type="button" name="Submit3" id="btnClear"  class="btn btn-warning"  onclick="javascript:location.href='modifyBlock.htm?<csrf:token uri='modifyBlock.htm'/>&blockId=${modifyBlockCmd.blockId}';"  ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>	
													
												<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
					                        </div>
	                     				</div>   
	                  				</div>
						
						
						</form:form>
		            </div>
			             
		         </div>
			</section>
		</div>
	</section>
		<script src="/LGD/JavaScriptServlet"></script>
	</body>
</html>