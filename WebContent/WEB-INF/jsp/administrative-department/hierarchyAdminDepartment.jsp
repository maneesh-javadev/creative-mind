<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/common.js"></script>

<link rel="stylesheet" href="<%=contextpthval%>/jquery.treeview/css/jquery.treeview.css" />
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jquery.cookie.js" ></script>
<script type="text/javascript" src="<%=contextpthval%>/jquery.treeview/jquery.treeview.js"></script>
<script type="text/javascript" src='<%=contextpthval%>/js/hierarchy-admin-department.js'></script>
<%@include file="../common/dwr.jsp"%>
<script type="text/javascript" language="javascript">
    var isCenterUser=isParseJson('${isCenterUser}');
    //alert(isCenterUser);
	var _state_code = '<c:out value="${stateCode}" escapeXml="true"></c:out>';
	var _process_url = "continueCreateAdminDepartmentProcess.htm?<csrf:token uri='continueCreateAdminDepartmentProcess.htm'/>";
	dwr.engine.setActiveReverseAjax(true);
	$(document).ready(function(){
		$("#browser").treeview();
	});
</script>
</head>
<body>
    <c:choose>
		<c:when test="${isOrganizationFlow}" >
			<c:set var="formTitle" value="Label.CREATEORG"></c:set>
		</c:when>
		<c:when test="${isCenterUser}">
			<c:set var="formTitle" value="label.create.minstordept"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="formTitle" value="Label.CREATEDEPT"></c:set>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${isOrganizationFlow}">
		
	</c:if>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="${formTitle}"/></h3>
			 <ul id="showhelp" class="listing">
		      		<!--//this link is not working <li>
		      			<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>
		      		</li> -->
		      		<%-- <li>
		      		//this link is not working
		      			<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true" code="Label.CBT"></spring:message></a>  
 		      		</li> --%>
		        
			       <%--//these links are not working  <li>
			        	<a href="#" class="frmhelp"><spring:message htmlEscape="true" 	code="Button.HELP"></spring:message> </a>
			        </li> --%>
			  </ul>
		</div>
		<div class="clear"></div>
		   <div class="frmpnlbrdr">
			   <form:form id="adminOrgDeptForm" commandName="adminOrgDeptForm">
			   		<input type="hidden" id="hierarchySequence" name="hierarchySequence"/>
			   		<form:hidden path="topNode" value="true"/>
			   		<div class="frmpnlbg" style="background: #FFFFFF;">
						<div class="frmtxt">
							<div class="frmhdtitle"><label><spring:message htmlEscape="true" code="Label.BuildHierarchy"/></label></div>
							<div>
								<ul class="blocklist">
									<li>
										<div class="ms_container">
												<div class="ms_selectable">
														<label><spring:message htmlEscape="true" code="Label.UnitLevels"/></label> </br/>
												<select id="adminLevelNameLocal" name="adminLevelNameLocal" class="combofield" style="width: 200px;">
									        		<option value="0">Select</option>
									        		<optgroup id="landRegionUnits" label="Land Regions Unit Levels">
									        			<c:forEach var="lrUnitLevel" items="${landRegionUnitLevels}">
									        				<option value="${lrUnitLevel.adminUnitCode}"><c:out value="${lrUnitLevel.adminLevelNameEng}" escapeXml="true"></c:out></option>
									        			</c:forEach>
									        		</optgroup>
									        		<c:if test="${not isCenterUser}">
									        		<optgroup id=" localbBodyType" label=" Local Body Types">
									        			<c:forEach var="localBody" items="${localBodyTypes}">
									        				<option value="-${localBody.localBodyTypeCode}"><c:out value="${localBody.name}" escapeXml="true"></c:out></option>
									        			
									        			</c:forEach>
									        		</c:if>
									        		</optgroup>
									        		<optgroup id="adminUnits" label="Administrative Unit Levels">
									        			<c:forEach var="adminUnitLevel" items="${adminUnitLevels}">
									        				<option value="${adminUnitLevel.adminUnitCode}"><c:out value="${adminUnitLevel.adminLevelNameEng}" escapeXml="true"></c:out></option>
									        			</c:forEach>
									        		</optgroup>
									        		
									         	</select>
									         	<c:forEach var="localBodys" items="${localBodyTypes}">
									        			
									        				<input type="hidden" id="u-${localBodys.localBodyTypeCode}" value="${localBodys.lbLevel}" ></input>
									        			</c:forEach>
									        			 
									         	<input  type="checkbox" id="chk" title="Check to add as Parent" checked="checked" disabled="disabled" style="display: none;"/>
									         	<br></br>
									         	  	<input type="button" id="btnBuildHrchy"  onclick="buildHierarchy();" value="Add Hierarchy"/>	
		
												</div>
												<div class="ms_buttons">
												</div>
												<div class="ms_selection">
														<ul id="browser" class="filetree"></ul>
															
												</div>																		
										</div>
									</li>
								</ul>
					
									<div class="clear"></div>
							</div>		
								<br/>
							</div>
					</div>
					<div class="btnpnl">
					<input type="button" id="btnCreateDept" value="Click to Proceed" onclick="return submitForm();"/>
								<input type="button" id="clear" value="Clear" onclick="javascript:go('startDepartmentCreatetion.htm');"/>
								<input type="button" id="close" value="Close" onclick="javascript:go('home.htm');"/> 
					</div>
			  </form:form>
			             	<c:choose>
									<c:when test="${isCenterUser}">
								                   <script>
													  createHirarchy('Center',0);
												   </script>
									</c:when>
									<c:otherwise>
							                    <script>
										             createHirarchy('State',1);
									            </script>
									</c:otherwise>
						</c:choose>	
			  <script src="/LGD/JavaScriptServlet"></script>
		  </div>
		</div>
</body>
</html>