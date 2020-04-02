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
<div id="frmcontent">
		<div class="frmhd" >
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td>
					<c:choose>
						<c:when test="${lgdDesignation.designationType eq 'E'}">
							<spring:message htmlEscape="true"  code="Label.DHER"></spring:message>
						</c:when>
						<c:when test="${lgdDesignation.designationType eq 'O'}">
							<spring:message htmlEscape="true"  code="Label.DHO"></spring:message>
						</c:when>
					</c:choose>
					</td>
					<td align="right">
						<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg">
							<img src="images/plus.jpg" border="0" /> 
						</a>
					</td>
					<%--//these links are not working <td width="50" align="right" valign="middle">
						<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
					</td>
					<td width="50" align="right">
						<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a>
					</td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
	<div class="frmpnlbrdr">
	<form:form action="reorder_designation_entry.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="designation_entry.htm"/>"/>
		<form:hidden path="designationType" />
		<form:hidden path="otherDesignations" id="otherDesignations" />
		<form:hidden path="flowName"/>
		<input type="hidden" name="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
		<div id="cat">
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></div>
					<table width="100%">
						<tr>
							<td width="15%">&nbsp;</td>
							<td>
								<table width="100%" class="tbl_no_brdr">
									<tr valign="top">
										<td width="25%">
			                               	<Label><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message><span id ="required" class="errormsg">*</span></Label>
			                               	<label id="lblType" style="color:red"></label>
			                            </td>
			                            <td rowspan="5">
		                            		<div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
		                            			<div style="height: 40px; border: 1px solid red; background-color:#FFEBE8; padding-top: 10px;" align="center">
													<label id="errorCommon"><form:errors path="*" cssClass="errorBox" ></form:errors></<label>
												</div>
		                            		</div>
										</td>
			                        </tr>
			                        <tr>
			                        	<td>
			                        		<form:select id="tierSetupCode" path="tierSetupCode"  onchange="return getDesignationDetails(this.value,'${lgdDesignation.designationType}');" cssStyle="width:200px;">
												<form:option value="-1"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
												<form:options items="${tiersetup}" itemLabel="nomenclatureEnglish" itemValue="tierSetupCode"/> 
											</form:select>
											<div id="errorTierSetup" style="height:15px; padding-top:3px;" class="errormsg"></div>
										</td>
									</tr>
			                    </table>				
							</td>
						</tr>
					</table>
				</div>
		   </div>
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.TD"></spring:message></div>
					<table width="100%">
						<tr>
							<td width="15%">&nbsp;</td>
							<td>
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label><span id ="required" class="errormsg">*</span></td>
			                            <td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label><span id ="required" class="errormsg">*</span></td>
			                           	<td width="15%">&nbsp;</td>
			                           	<td>&nbsp;</td>
			                           </tr>
			                        <tr>
			                        	<td><label id="desgNameTop"></label></td>
										<td><label id="desgNameLocalTop"> </label>
										</td>
										<td align="center">
											<input type="checkbox" id="chkbx1" disabled="disabled" style="display: none;"/>
										</td>
										<td style="display: none;">
											<form:hidden path="designationCode" id="desgIdTop"/>
											<input type="hidden" id="isTop" name="isTopDesignation" value="true"/>
										</td>
			                        </tr>
			                    </table>			
							</td>
						</tr>
					</table>		
				</div>
		   </div>
		   <div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DEGOTHER"></spring:message></div>
					<table width="100%">
						
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td width="15%">&nbsp;</td>
							<td>
								<table width="100%" class="tbl_no_brdr" id="designationOthers">
									<tr>
										<td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label></td>
			                            <td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label></td>
			                           	<td align="center" width="15%"><Label><spring:message htmlEscape="true"  code="Label.ISMULTI"></spring:message></Label></td>
			                           	<td align="center" width="20%"><Label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></Label></td>
			                           	<td class="center" align="center" colspan="2" ><b><spring:message code="label.training.process.scheduler.rearrange" text="Rearrangement"/></b></td>
			                           	<td>&nbsp;</td>
			                           </tr>
			                        	
			                    </table>			
							</td>
						</tr>
					</table>	
				</div>
		   </div>
		   <table width="100%">
		   		<tr>
		   			<td align="center">
		   				<input id="submit" type="submit" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return getOtherDesignations();" disabled="disabled"/>
						<c:if test="${lgdDesignation.designationType eq 'null'}">
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
						
                        <input type="button" name="Clear" value="Clear" onclick="go('${clearURL}');"/>
                        <input type="button" name="Submit33" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'"/>
		   			</td>
		   		</tr>
		   </table>
        </div>
	</form:form>
	</div>
	</div>
	
</body>
</html>
