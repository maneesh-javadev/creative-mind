<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
	
	<script type="text/javascript">var cPath="<%=contextPath%>";</script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDesignationDwr.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/js/designation_er_official.js'> </script>
	<script language="javascript">
	$(document).ready(function(){
		$(".up,.down").click(function(){ 
	        var row = $(this).parents("tr:first"); 
	        if ($(this).is(".up")) { 
	            row.insertBefore(row.prev()); 
	        } else { 
	            row.insertAfter(row.next()); 
	        } 
	        setPriorityImg();
	    }); 
		 
	}); 
	
	if ( window.addEventListener ) { 
	     window.addEventListener( "load",onLoadPage, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", onLoadPage );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = onLoadPage;
	  }
	
	function onLoadPage()
	{
		setPriorityImg();
	}
	</script>
</head>

<body >
<section class="content">
                <div class="row">
                    <section class="col-lg-12">
                 <form:form action="designation_entry.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="designation_entry.htm"/>"/>
						<form:hidden path="designationType" />
						<form:hidden path="otherDesignations" id="otherDesignations" />
						<form:hidden path="flowName"/>
						<input type="hidden" name="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
                    <div class="box">
                    <div class="box-header with-border">
                      <h4><c:choose>
								<c:when test="${lgdDesignation.designationType eq 'E'}">
									<spring:message htmlEscape="true"  code="Label.DHER"></spring:message>
								</c:when>
								<c:when test="${lgdDesignation.designationType eq 'O'}">
									<spring:message htmlEscape="true"  code="Label.DHO"></spring:message>
								</c:when>
							</c:choose></h4>
                    </div>
                        <div class="box-body">
                          <div class="box-header subheading"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message><span id ="required" class="errormsg">*</span>
                                <div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
			                            	<div style="height: 40px; border: 1px solid red; background-color:#FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><form:errors path="*" cssClass="errorBox" ></form:errors></label>
											</div>
		                            	</div>
                            
                            </label>
                            <div class="col-sm-6">
                            <form:select id="tierSetupCode" path="tierSetupCode" htmlEscape="true" onchange="return getDesignationDetails(this.value,'${lgdDesignation.designationType}');" class="form-control">
											<form:option value="-1" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
											<form:options items="${tiersetup}" itemLabel="nomenclatureEnglish" itemValue="tierSetupCode" htmlEscape="true"/> 
										</form:select>
										<div id="errorTierSetup" style="height:15px; padding-top:3px;" class="errormsg"></div>
                            </div>
                          
                          
                          </div>
                          
                          <div class="box-header subheading"><spring:message htmlEscape="true"  code="Label.TD"></spring:message></div>
                          
                          <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.NE"></spring:message></label><span id ="required" class="errormsg">*</span></label>
                            <div class="col-sm-6">
                             <form:input path="designationName" id="desgNameTop"  class="form-control" maxlength="50" onkeypress="removeBorderColor('desgNameTop');"/>
			                    <div id="errDesgNameTop" style="height:15px; padding-top:3px;" class="errormsg"></div>
                            </div>
                          
                          </div>
                          
                          
                          <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.NL"></spring:message></label><span id ="required" class="errormsg">*</span></label>
                            <div class="col-sm-6">
                            <form:input path="designationNameLocal" id="desgNameLocalTop" class="form-control" maxlength="60" onkeypress="removeBorderColor('desgNameLocalTop');"/>
			                    <div id="errDesgNameLocalTop" style="height:15px; padding-top:3px;" class="errormsg"></div>
                            
                            </div>
                          
                          </div>
                        
                        
                         <div class="form-group">
	                         <input type="checkbox" id="chkbx1" disabled="disabled" style="display: none;"/>
	                         <form:hidden path="lgdDesignationPK.designationCode" id="desgIdTop"/>
						     <input type="hidden" id="isTop" name="isTopDesignation" value="true"/>
	                         
                         </div>
                        
                        <div class="box-header subheading"><spring:message htmlEscape="true"  code="Label.DEGOTHER"></spring:message></div>
                         <div><span style="color: red;  ">NOTE : If the designation is being used by other applications, you cannot modify / delete that designation.</span></div>
                        
                            <table class="table table-bordered table-hover" id="designationOthers">
                            <thead>
                              <tr>
										<td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label></td>
			                            <td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label></td>
			                           	<td align="center" width="15%"><Label><spring:message htmlEscape="true"  code="Label.ISMULTI"></spring:message></Label></td>
			                           	<td align="center" width="20%"><Label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></Label></td>
			                           	<td align="center" width="20%">&nbsp;</td>
			                           	<td class="center" align="center" colspan="2" ><b><spring:message htmlEscape="true" code="label.training.process.scheduler.rearrange" text="Rearrangement"/></b></td>
			                           	<td>&nbsp;</td>
			                  </tr>
                            
                            
                            </thead>
                             <tbody>
								      <tr >
			                        	<td >
			                               	<input type="text" id="desgNameOthers" name="designationNameOthers" style="width:200px;" maxlength="50" onkeypress="removeBorderColor(this.id);"/>
										</td>
										<td >
			                               	<input type="text" id="desgNameLocalOthers" name="designationNameLocalOthers" style="width:200px;" maxlength="60" onkeypress="removeBorderColor(this.id);"/>
										</td>
										<td align="center">
											<input type="checkbox" id="isMultipleOthers" name="isMultipleOthers"/>
										</td>
										<td align="center">
											<input type="checkbox" id="isContrctPermanentOthers" name="isContrctPermanentOthers"/>
										</td>
										<td><img src="<%=contextPath%>/images/add.png" border="0" height="22" width="20" onclick="addTableRows(null, null, null, null, null);"/> </td>
										
										<td >
											<img src="<%=contextPath%>/images/sort_asc.png" class="up" />&nbsp;
										</td>
					    				<td >
					    					<img src="<%=contextPath%>/images/sort_desc.png" class="down" />
					    				</td>
					    				<td style="display: none;">
											<input type="hidden" id="desgIdOthers" name="designationCodeOthers"/>
											<input type="hidden" id="isTop" name="isTopDesignationOthers" value="false"/>
											
										</td>
									</tr>
								    </tbody>
								  </table>
                       
                        </div>
                  <div class="box-footer">
		            <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
						 <button id="submit" type="submit" name="Submit" class="btn btn-success" onclick="return getOtherDesignations();" disabled="disabled"><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
						<c:if test="${lgdDesignation.designationType eq 'E'}">
							<c:choose>
					 			<c:when test="${fn:containsIgnoreCase(lbType,'P')}"><c:set var="clearURL" value="designation_hierarchy_elected_panchayat.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'T')}"><c:set var="clearURL" value="designation_hierarchy_elected_traditional.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'U')}"><c:set var="clearURL" value="designation_hierarchy_elected_urban.htm"></c:set></c:when>
							</c:choose>
						</c:if>
						<c:if test="${lgdDesignation.designationType eq 'O'}">
					 		<c:choose>
						 		<c:when test="${fn:containsIgnoreCase(lbType,'P')}"><c:set var="clearURL" value="designation_hierarchy_official_panchayat.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'T')}"><c:set var="clearURL" value="designation_hierarchy_official_traditional.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'U')}"><c:set var="clearURL" value="designation_hierarchy_official_urban.htm"></c:set></c:when>
							</c:choose>
					 	</c:if>
						
                        <button type="button"  class="btn btn-warning" name="Clear" value="Clear" onclick="go('${clearURL}');">Clear</button>
                        <button type="button" name="Submit33" class="btn btn-danger" onclick="javascript:window.location.href='home.htm'"><spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
                    
                    </form:form>
             
                    </section>
                </div>
            </section>
	<c:if test="${isError}">
		<script>
			var otherdesignations = '<c:out value="${lgdDesignation.otherDesignations}" escapeXml="true"></c:out>';
			var rowvalues = otherdesignations.split("@@");
			if (rowvalues.length > 0) {
				var firstrowothers = true;
				for ( var i = 0; i < rowvalues.length; i++) {
					var columnvalues = rowvalues[i].split("##");
					var desName = columnvalues[1];
					var desNameLocal = columnvalues[2];
					var multiple = columnvalues[3];
					var contractPerma = columnvalues[4];
					var desCode = columnvalues[0];
					if (firstrowothers) {
						document.getElementById('desgNameOthers').value = desName;
						document.getElementById('desgNameLocalOthers').value = desNameLocal;
						document.getElementById('isMultipleOthers').checked = multiple;
						document.getElementById('isContrctPermanentOthers').checked = contractPerma;
						document.getElementById('desgIdOthers').value = desCode;
						firstrowothers = false;
					} else {
						addTableRows(desName, desNameLocal, isParseJson(multiple), isParseJson(contractPerma), desCode);
					}
				}
			}
		</script>
	</c:if>
</body>
</html>
