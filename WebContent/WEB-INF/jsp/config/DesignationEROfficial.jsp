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
<div id="frmcontent">
		<div class="frmhd" >
		<h3 class="subtitle">
			<c:choose>
				<c:when test="${lgdDesignation.designationType eq 'E'}">
					<spring:message htmlEscape="true"  code="Label.DHER"></spring:message>
				</c:when>
				<c:when test="${lgdDesignation.designationType eq 'O'}">
					<spring:message htmlEscape="true"  code="Label.DHO"></spring:message>
				</c:when>
			</c:choose>
		</h3>
		<ul class="listing">
			<li>
				<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg">
				<img src="images/plus.jpg" border="0" /> 
				</a>			
			</li>
			<%-- //these links are not working <li>
				<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
			</li>
			<li>
				<a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a>
			</li> --%>
		</ul>
	</div>
	<div class="clear"></div>
	
	<div class="frmpnlbrdr">
		<form:form action="designation_entry.htm" method="post" commandName="lgdDesignation" name="lgdDesignation" id="lgdDesignation">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="designation_entry.htm"/>"/>
			<form:hidden path="designationType" />
			<form:hidden path="otherDesignations" id="otherDesignations" />
			<form:hidden path="flowName"/>
			<input type="hidden" name="lbType" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/>
			<div id="cat">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></div>
							<div>
								<ul class="blocklist">
									<li>
										<label><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message><span id ="required" class="errormsg">*</span></Label>
			                           	<label id="lblType" class="mndt"></label></br>
			                            <div id="tdError" style="display: <c:choose><c:when test="${isError}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>">
			                            	<div style="height: 40px; border: 1px solid red; background-color:#FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><form:errors path="*" cssClass="errorBox" ></form:errors></label>
											</div>
		                            	</div>
										<form:select id="tierSetupCode" path="tierSetupCode" htmlEscape="true" onchange="return getDesignationDetails(this.value,'${lgdDesignation.designationType}');" cssStyle="width:200px;">
											<form:option value="-1" htmlEscape="true"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
											<form:options items="${tiersetup}" itemLabel="nomenclatureEnglish" itemValue="tierSetupCode" htmlEscape="true"/> 
										</form:select>
										<div id="errorTierSetup" style="height:15px; padding-top:3px;" class="errormsg"></div>
									</li>
								</ul>
							</div>
						</div>
					</div>
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.TD"></spring:message></div>
					<div>
						<ul class="listing">
							<li>
								<label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></label><span id ="required" class="errormsg">*</span></br>
								<form:input path="designationName" id="desgNameTop" cssStyle="width:200px;" maxlength="50" onkeypress="removeBorderColor('desgNameTop');"/>
			                    <div id="errDesgNameTop" style="height:15px; padding-top:3px;" class="errormsg"></div>
							</li>
							
							<li>
								<label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></label><span id ="required" class="errormsg">*</span></br>
								<form:input path="designationNameLocal" id="desgNameLocalTop" cssStyle="width:200px;" maxlength="60" onkeypress="removeBorderColor('desgNameLocalTop');"/>
			                    <div id="errDesgNameLocalTop" style="height:15px; padding-top:3px;" class="errormsg"></div>
							</li>
							
							<li>
								<input type="checkbox" id="chkbx1" disabled="disabled" style="display: none;"/>
							</li>
							
							<li>
								<form:hidden path="lgdDesignationPK.designationCode" id="desgIdTop"/>
								<input type="hidden" id="isTop" name="isTopDesignation" value="true"/>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle"><spring:message htmlEscape="true"  code="Label.DEGOTHER"></spring:message></div>
					<table width="100%">
						<tr>
							<td >
								<span style="color: red;  ">NOTE : If the designation is being used by other applications, you cannot modify / delete that designation.</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>
								<table width="100%" class="tbl_no_brdr" id="designationOthers">
									<tr>
										<td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label></td>
			                            <td align="left" width="25%"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label></td>
			                           	<td align="center" width="15%"><Label><spring:message htmlEscape="true"  code="Label.ISMULTI"></spring:message></Label></td>
			                           	<td align="center" width="20%"><Label><spring:message htmlEscape="true" code="Label.ISCONTRACTPERMENENT"></spring:message></Label></td>
			                           	<td align="center" width="20%">&nbsp;</td>
			                           	<td class="center" align="center" colspan="2" ><b><spring:message htmlEscape="true" code="label.training.process.scheduler.rearrange" text="Rearrangement"/></b></td>
			                           	<td>&nbsp;</td>
			                           </tr>
			                        <tr style="height: 25px;">
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
			                    </table>			
							</td>
						</tr>
					</table>	
				</div>
		   </div>
		   <div class="btnpnl">
		   		<ul class="listing">
		   			<li>
		   				<input id="submit" type="submit" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return getOtherDesignations();" disabled="disabled"/>
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
						
                        <input type="button" name="Clear" value="Clear" onclick="go('${clearURL}');"/>
                        <input type="button" name="Submit33" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'"/>
		   			</li>
		   		</ul>
		   </div>	
		</div>
	  </form:form>
   </div>
</div>
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
