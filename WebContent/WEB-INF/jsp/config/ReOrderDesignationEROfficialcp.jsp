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
	<style type="text/css">
		.redborder { border: 1px solid red; }
	</style>
	<script type="text/javascript">var cPath="<%=contextPath%>";</script>
	<script type="text/javascript" src="js/common.js"></script>
	<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDesignationDwr.js'></script>
	<script type='text/javascript' src='<%=contextPath%>/js/reorder_designationer_official.js'> </script>
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
                <form:form action="reorder_designation_entry.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation" class="form-horizontal">
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
													<label id="errorCommon"><form:errors path="*" cssClass="errorBox" ></form:errors></<label>
												</div>
		                            		</div>
                            
                            </label>
                            <div class="col-sm-6">
                           <form:select id="tierSetupCode" path="tierSetupCode"  onchange="return getDesignationDetails(this.value,'${lgdDesignation.designationType}');" class="form-control">
												<form:option value="-1"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
												<form:options items="${tiersetup}" itemLabel="nomenclatureEnglish" itemValue="tierSetupCode"/> 
											</form:select>
											<div id="errorTierSetup" style="height:15px; padding-top:3px;" class="errormsg"></div>
                            </div>
                          
                          
                          </div>
                          
                          <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.TD"></spring:message></h4></div>
                          
                          <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label><span id ="required" class="errormsg">*</span></label>
                            <div class="col-sm-6">
                               <label id="desgNameTop"></label>
                            </div>
                          </div>
                          
                           <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label><span id ="required" class="errormsg">*</span></label>
                            <div class="col-sm-6">
                               <label id="desgNameLocalTop"> </label>
                            </div>
                          </div>
                          
                          <div class="form-group">
                            <input type="checkbox" id="chkbx1" disabled="disabled" style="display: none;"/>
                              <form:hidden path="designationCode" id="desgIdTop"/>
							  <input type="hidden" id="isTop" name="isTopDesignation" value="true"/>
                          </div>
                          
                        
                        
                        <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.DEGOTHER"></spring:message></h4></div>
                        
                        
                          <table  class="table table-bordered table-hover" id="designationOthers">
									<tr>
										<td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label></td>
			                            <td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label></td>
			                           	<td align="center" width="15%"><Label><spring:message htmlEscape="true"  code="Label.ISMULTI"></spring:message></Label></td>
			                           	<td align="center" width="20%"><Label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></Label></td>
			                           	<td class="center" align="center" colspan="2" ><b><spring:message code="label.training.process.scheduler.rearrange" text="Rearrangement"/></b></td>
			                           	<td>&nbsp;</td>
			                           </tr>
			                        	
			                    </table>
                        
                                                   
                        </div>
                  <div class="box-footer">
		            <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
						 <button id="submit" type="submit" name="Submit" class="btn btn-success" onclick="return getOtherDesignations();" disabled="disabled"><spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
						<c:if test="${lgdDesignation.designationType eq 'E'}">
							<c:choose>
					 			<c:when test="${fn:containsIgnoreCase(lbType,'P')}"><c:set var="clearURL" value="reorder_designation_hierarchy_elected_panchayat.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'T')}"><c:set var="clearURL" value="reorder_designation_hierarchy_elected_traditional.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'U')}"><c:set var="clearURL" value="reorder_designation_hierarchy_elected_urban.htm"></c:set></c:when>
							</c:choose>
						</c:if>
						<c:if test="${lgdDesignation.designationType eq 'O'}">
					 		<c:choose>
						 		<c:when test="${fn:containsIgnoreCase(lbType,'P')}"><c:set var="clearURL" value="reorder_designation_hierarchy_official_panchayat.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'T')}"><c:set var="clearURL" value="reorder_designation_hierarchy_official_traditional.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(lbType,'U')}"><c:set var="clearURL" value="reorder_designation_hierarchy_official_urban.htm"></c:set></c:when>
							</c:choose>
					 	</c:if>
						
                        <button type="button" class="btn btn-warning" name="Clear"  onclick="go('${clearURL}');">Clear</button>
                        <button type="button" class="btn btn-danger" name="Submit33"  onclick="javascript:window.location.href='home.htm'"><spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
                        </div>
                      </div>
                   </div>
                        
                    </div>
                    
                    </form:form>
             
                    </section>
                </div>
            </section>
	
</body>
</html>
