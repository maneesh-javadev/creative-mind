


<html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>

<head>
<title><spring:message code="Label.Title"></spring:message></title>

</head>
<body>
<!-- <div id="headbg"><img src="images/headimg.png" width="774" height="145" /></div> -->
 <div id="contentin">
<h1>Welcome to Local Government Directory</h1>
 <p>The republic of India comprises the union government, 28 state governments, 7 union territories and about 2,45,000 local governments. Though there are many software applications developed and successfully implemented for catering the needs of local governments, there is no comprehensive web site or application which provides authenticated and up-to-date information on list of local governments. National Panchayat Directory (http://panchayatdirectory.gov.in) was an effort of Ministry of Panchayati Raj, developed and technically maintained by National Informatics Center(NIC, http://www.nic.in ), to maintain list of rural local governments that are called as Panchayati Raj Institutions.
Local Government Directory will be used by the Central and state departments who are responsible for forming new states/UTs, new districts, new sub-districts, new villages and new local government bodies as well as changing their status , name and formation. "
 </p>
  
 <br/>
 <a href="newDeptAdminEntity.htm?<csrf:token uri='"newDeptAdminEntity.htm'/>">Define Administrative Unit Entity</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="manage_Administrative_Entity.htm?<csrf:token uri='"manage_Administrative_Entity.htm'/>">Manage Administrative Unit Entity</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/>
<br/>
 <br/>

 
<a href="freezUnfreezUrb.htm?<csrf:token uri='"freezUnfreezUrb.htm'/>">Unfreeze ULB</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="freezeURBLB.htm?<csrf:token uri='"freezeURBLB.htm'/>">URBLB Freeze</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="manageDraftVillage.htm?<csrf:token uri='"manageDraftVillage.htm'/>">Manage Draft Village</a><br/>
<a href="showBPSyncWithBlock.htm?<csrf:token uri='"showBPSyncWithBlock.htm'/>">SYNC BP</a> &nbsp;&nbsp;

<br/>

<a href="createSection.htm?<csrf:token uri='"createSection.htm'/>">Create Section(Center) </a> &nbsp;&nbsp;
<a href="manageSection.htm?<csrf:token uri='"manageSection.htm'/>">Manage  Section(Center)</a> &nbsp;&nbsp;
<a href="setParentOrgUnitCenter.htm?<csrf:token uri='"setParentOrgUnitCenter.htm'/>">Set Parent OrgUnit(Center)</a> &nbsp;&nbsp;
<a href="unMappedPolygon.htm?<csrf:token uri='"unMappedPolygon.htm'/>">un-Mapped Polygon </a> &nbsp;&nbsp;
<a href="localBodyPopulation.htm?<csrf:token uri='"localBodyPopulation.htm'/>">Local Body Population </a> &nbsp;&nbsp;
<a href="localBodyPopulationForUrban.htm?<csrf:token uri='"localBodyPopulationForUrban.htm'/>">Local Body Population For Urban </a> &nbsp;&nbsp;



<br>
<br>
<a href="localBodyPopulationForTraditional.htm?<csrf:token uri='"localBodyPopulationForTraditional.htm'/>">Local Body Population for Traditional </a> &nbsp;&nbsp;
<a href="updateGISBoundaries.htm?<csrf:token uri='"updateGISBoundaries.htm'/>">UPDTAE GIS BOUNDARIES </a> &nbsp;&nbsp;

<a href="createDraftSubdistrict.htm?<csrf:token uri='"createDraftSubdistrict.htm'/>">Create Subdistrict with Draft </a> &nbsp;&nbsp;

<a href="manageSubdistrict.htm?<csrf:token uri='"manageSubdistrict.htm'/>">Manage Subdistrict with Draft </a> &nbsp;&nbsp;
<br/><br/>
<a href="habitationConfiguration.htm?<csrf:token uri='"habitationConfiguration.htm'/>">Habitation Configure</a> &nbsp;&nbsp;
<a href="createHabitation.htm?<csrf:token uri='createHabitation.htm'/>">&nbsp;<spring:message htmlEscape="true" code="Label.create.habitation" text="Create Habitation"></spring:message></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="modifyHabitation.htm?<csrf:token uri='modifyHabitation.htm'/>">&nbsp;Modify Habitation</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="stateWisePopulation.htm?<csrf:token uri='stateWisePopulation.htm'/>">&nbsp;State Wise Population report</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<br><br>
<a href="extendExistDept.htm?<csrf:token uri='extendExistDept.htm'/>">&nbsp;Build Tree</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="buildTree.htm?<csrf:token uri='buildTree.htm'/>">&nbsp;Build Tree</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="startRebuildTree.htm?<csrf:token uri='startRebuildTree.htm'/>&hierarchyIds=1,-1,-2&olc=730">&nbsp;Start Build Tree</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="extendOrganizationUnits.htm?<csrf:token uri='extendOrganizationUnits.htm'/>&orgTypeCode=2&OrgLocatedLevelCode=2460">&nbsp;Show Organization Setup DP</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<a href="extendOrganizationUnits.htm?<csrf:token uri='extendOrganizationUnits.htm'/>&orgTypeCode=2&OrgLocatedLevelCode=2461">&nbsp;Show Organization Setup IP</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<a href="extendOrganizationUnits.htm?<csrf:token uri='extendOrganizationUnits.htm'/>&orgTypeCode=2&OrgLocatedLevelCode=2465">&nbsp;Show Organization Setup GP</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<a href="extendOrganizationUnits.htm?<csrf:token uri='extendOrganizationUnits.htm'/>&orgTypeCode=2&OrgLocatedLevelCode=2463">&nbsp;Show Organization Setup Urban</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<a href="accessDepartmentScript.htm?<csrf:token uri='"accessDepartmentScript.htm'/>">accessDepartmentScript</a> &nbsp;&nbsp;

 </div>
 	<c:if test="${not empty publishMessage}">
		<script>
			$(window).load(function () {
				customAlert('${publishMessage}');
			});
		</script>
	</c:if>
</body>
</html>