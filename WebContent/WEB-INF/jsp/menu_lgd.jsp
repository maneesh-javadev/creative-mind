<%@include file="taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	<table width="100%" class="tblbgclr tbl_no_brdr">
		<tr>
			<td id="span_1_0" class="tblclear coltab">
			<div class="collapse_menu"></div>
			</td>
			<td class="tblclear resize">
				<div id="panel" class="pnlsize">
					<div class="arrowlistmenu">
						<h3 class="menuheader expandable"><spring:message code="Menu.STATE"></spring:message></h3>
						<ul class="categoryitems">
						    
							<li><a href="add_state.htm"><spring:message code="Label.CREATESTATE"></spring:message></a></li>
							<li><a href="viewstate.htm"><spring:message code="Label.MANAGESTATE"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Menu.DISTRICT"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="add_district.htm"><spring:message code="Label.CREATEDISTRICT"></spring:message></a></li>
							<li><a href="viewdistrict.htm"><spring:message code="Label.MANAGEDISTRICT"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Menu.SUBDISTRICT"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="new_subdistrict.htm"><spring:message code="Title.CREATENEWSUBDISTRICT"></spring:message></a></li>
							<li><a href="viewsubdistrict.htm"><spring:message code="Label.MANAGESUBDISTRICT"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Menu.VILLAGE"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="createVillage.htm"><spring:message code="Label.CREATENEWVILLAGE"></spring:message></a></li>
							<li><a href="viewvillage.htm"><spring:message code="Label.MANAGEVILLAGE"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Menu.SHIFT"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="shiftdistrict.htm"><spring:message code="Label.SHIFTDISTRICT"></spring:message></a></li>
							<li><a href="shiftSubDistrict.htm"><spring:message code="Label.SHIFTSUBDISTRICT"></spring:message></a></li>
							<li><a href="shiftblock.htm"><spring:message code="Label.SHIFTBLOCK"></spring:message></a></li>
						
							<li><a href="shiftvillageblock.htm"><spring:message code="Label.SHIFTVILLAGEBLOCK"></spring:message></a></li>
							<li><a href="shiftvillageSubDistrict.htm"><spring:message code="Label.SHIFTVILLAGESUBDISTRICT"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Menu.INVALIDATE"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="invalidatevillage.htm"><spring:message code="Label.INVAlVILL"></spring:message></a></li>
							<li><a href="invalidatesubdistrict.htm"><spring:message code="Label.INVALIDATESUBDISTRICT"></spring:message></a></li>
							<li><a href="invalidatedistrict.htm"><spring:message code="Label.INVALIDATEDISTRICT"></spring:message></a></li>
							<li><a href="invalidateblock.htm"><spring:message code="Label.INVALIDATEBLOCK"></spring:message></a></li>
							<li><a href="invalidateLocalBodyUrban.htm"><spring:message code="Label.INVALIDATEULB"></spring:message></a></li>
							<li><a href="invalidateLocalBodyPanchayat.htm"><spring:message code="Label.INVALIDATEPLB"></spring:message></a></li>
							<li><a href="invalidateLocalBodyTraditional.htm"><spring:message code="Label.INVALIDATETLB"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.LGBODY"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="createLocalBodyforPRI.htm"><spring:message code="Label.CREATEPRILOCALBODY"></spring:message></a></li>	
							<li><a href="createLocalBodyforTraditional.htm"><spring:message code="Label.CREATETRADILOCALBODY"></spring:message></a></li>	
							<li><a href="createLocalBodyforUrban.htm"><spring:message code="Label.CREATEURBANLOCALBODY"></spring:message></a></li>	
							<li><a href="viewLocalBodyforPRI.htm"><spring:message code="Label.MODIFYPRILOCALBODY"></spring:message></a></li>	
							<li><a href="viewLocalBodyforTraditional.htm"><spring:message code="Label.MODIFYTRADILOCALBODY"></spring:message></a></li>	
							<li><a href="viewLocalBodyforUrban.htm"><spring:message code="Label.MODIFYURBANLOCALBODY"></spring:message></a></li>							
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.CONVERTLB"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="convertRLBtoULB.htm"><spring:message code="Label.CRU"></spring:message></a></li>
							<li><a href="convertTLBtoULB.htm"><spring:message code="Label.CTU"></spring:message></a></li>
							<li><a href="convertRLBtoTLB.htm"><spring:message code="Label.CRT"></spring:message></a></li>
							
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.LGT"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="localgovtType.htm"><spring:message code="Label.CREATELOCALGOVTTYPE"></spring:message> </a></li>
							<li><a href="viewlocalgovttype.htm"><spring:message code="Label.VLGT"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.WARD"></spring:message></h3>
						<ul class="categoryitems">
						    <li><a href="createWardforPRI.htm"><spring:message code="Label.CREATEWARDPRI"></spring:message></a></li>	
							<li><a href="createWardforTraditional.htm"><spring:message code="Label.CREATEWARDTRADITIONAL"></spring:message></a></li>	
							<li><a href="createWardforUrban.htm"><spring:message code="Label.CREATEWARDURBAN"></spring:message></a></li>
							<li><a href="viewwardforPRI.htm"><spring:message code="Label.MANAGEPRIWARD"></spring:message></a></li>	
							<li><a href="viewwardforTraditional.htm"><spring:message code="Label.MANAGETRADITIONALWARD"></spring:message></a></li>	
							<li><a href="viewwardforUrban.htm"><spring:message code="Label.MANAGEURBANWARD"></spring:message></a></li>
						</ul>
						
						
						<h3 class="menuheader expandable"><spring:message code="Label.BLOCK"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="new_block.htm"><spring:message code="Label.CREATEBLOCK"></spring:message></a></li>
							<li><a href="viewblock.htm"><spring:message code="Label.MANAGEBLOCK"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.MINISTRY"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="createMinistry.htm"><spring:message code="Label.CREATEMINISTRY"></spring:message></a></li>
							<li><a href="viewministry.htm"><spring:message code="Label.MANAGEMINISTRY"></spring:message></a></li>
					   </ul>

						<h3 class="menuheader expandable"><spring:message code="Label.DEPARTMENT"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="createDepartment.htm"><spring:message code="Label.CREATEDEPT"></spring:message></a></li>
							<li><a href="viewdepartment.htm"><spring:message code="Label.MANAGEDEPARTMENT"></spring:message></a></li>
						</ul>
						
						
						<h3 class="menuheader expandable"><spring:message code="Label.ORGTYPEMENU"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="addOrganizationType.htm"><spring:message code="Label.ORGTYPE"></spring:message></a></li>
							<li><a href="modifyOrganizationType.htm"><spring:message code="Label.MODIFYORGTYPE"></spring:message></a></li>
							<li><a href="deleteOrganizationType.htm"><spring:message code="Label.DELETEORGTYPE"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.ORG"></spring:message></h3>
						<ul class="categoryitems">
							
							<li><a href="createOrganization.htm"><spring:message code="Label.CREATEORG"></spring:message></a></li>
							<li><a href="vieworganization.htm"><spring:message code="Label.MANAGEORG"></spring:message></a></li>
							<li><a href="add_designation.htm"><spring:message code="Label.CREATEDESIGNATION"></spring:message></a></li>
							<li><a href="add_reporting_structure.htm"><spring:message code="Label.ADDREPORTINGSTRUCTURE"></spring:message></a></li>
						</ul>

						<h3 class="menuheader expandable"><spring:message code="Label.CONSTITUENCY"></spring:message></h3>
						<ul class="categoryitems">
						
						
						<li><a href="parliament_Constituency.htm"><spring:message code="Label.CPC"></spring:message></a></li>
						<li><a href="assembly_Constituency.htm"><spring:message code="Label.AC"></spring:message></a></li>
						<li><a href="map_constitutionBody.htm"><spring:message code="Label.PC"></spring:message></a></li>
						<li><a href="Modifyparliament_Constituency.htm"><spring:message code="Label.MPC"></spring:message></a></li>
						<li><a href="ModifyAssembly_Constituency.htm"><spring:message code="ALabel.MODIFYASSEMBLY"></spring:message></a></li>
								
							<li><a href="home.htm"><spring:message code="Label.MPC"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.MAC"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.MAPCONSTITUENCY"></spring:message></a></li>
						</ul>

						<h3 class="menuheader expandable"><spring:message code="Label.CONFIGURESYSTEM"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="local_gov_setupUrban.htm"><spring:message code="Label.DEFINELGOVSETUPURBAN"></spring:message></a></li>
							<li><a href="local_gov_setupPanchayat.htm"><spring:message code="Label.DEFINELGOVSETUPPANCHAYAT"></spring:message></a></li>
							<li><a href="local_gov_setupTraditional.htm"><spring:message code="Label.DEFINELGOVSETUPTRADITIONAL"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.CONFIGUREMAP"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.CONFIGUREGOVORDER"></spring:message></a></li>
							<li><a href="nomenclature_sub_district.htm"><spring:message code="Label.NOMENCLATUREOFSUBDISTRICT"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.MSYSTEM"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.CONFIGUREDESIGNATION"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="home.htm"><spring:message code="Label.DSGE"></spring:message> </a></li>
							<li><a href="home.htm"><spring:message code="Label.DSGO"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.CREATERH"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.MANAGEDESIGNATION"></spring:message></a></li>
						</ul>
						<h3 class="menuheader expandable"><spring:message code="Label.CGDG"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="centralAdmin.htm"><spring:message code="Label.CADMIN"></spring:message></a></li>
							<li><a href="lrmForm.htm"><spring:message code="Label.LRM"></spring:message></a></li>
							<li><a href="lgdmForm.htm"><spring:message code="Label.LGDM"></spring:message></a></li>
							<li><a href="constituencyMgr.htm"><spring:message code="Label.CM"></spring:message></a></li>
							<li><a href="administrativeUnitMgr.htm"><spring:message code="Label.AUM"></spring:message></a></li>
						</ul>
						<h3 class="menuheader expandable"><spring:message code="Label.CONFIGUREMAP"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="config_landregion.htm"><spring:message code="Label.LRM"></spring:message></a></li>
							<li><a href="config_constituency.htm"><spring:message code="Label.CM"></spring:message></a></li>
							<li><a href="config_block.htm"><spring:message code="Label.BM"></spring:message></a></li>
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.GOVTEMPLATE"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="createGovtTemplate.htm?mode=createmode"><spring:message code="Label.CREATEGOVTORDERTEMPLATE"></spring:message></a></li>
							<li><a href="viewListOfTemplates.htm"><spring:message code="Label.LISTOFTEMPLATES"></spring:message></a></li>
							<li><a href="generateGovtTemplate.htm"><spring:message code="Label.GENDRAFTTEMPL"></spring:message></a></li>
						</ul>
						<h3 class="menuheader expandable"><spring:message code="Label.UNRESOLVEENTITY"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="viewResolveEntitiesinDisturbedState.htm"><spring:message code="Label.VIEWRESOLVEDISTRUBEDSTATEWARNFLAG"></spring:message></a></li>
							<li><a href="assignBlockVillageDisturbedState.htm"><spring:message code="Label.ASSIGNEDBLOCK"></spring:message></a></li>
							<li><a href="AddGazettePublicationDate.htm"><spring:message code="Label.GAZETTEPUBLICATIONDATE"></spring:message></a></li>
							
							
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.MISCELENOUSENTITY"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="standard_Code.htm"><spring:message code="Label.UDISTRICTMISINFOR"></spring:message></a></li>
<%-- 							<li><a href="assignBlockVillageDisturbedState.htm"><spring:message code="App.ASSBLOCKTOVILLINDISTSTATE"></spring:message></a></li>
 --%>							
							
						</ul>
						
						<h3 class="menuheader expandable"><spring:message code="Label.UNPUBITEM"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="viewUnpublishedItems.htm"><spring:message code="Label.VIEWUNBPUBLISHEDITEMS"></spring:message></a></li>
						</ul>
						<h3 class="menuheader expandable"><spring:message code="Label.REPORTS"></spring:message></h3>
						<ul class="categoryitems">
							<li><a href="home.htm"><spring:message code="Label.ConsolidatedReport"></spring:message></a></li>
							<li><a href="home.htm"><spring:message code="Label.CREPORTSPANCHAYAT"></spring:message></a></li>
						</ul>
					</div>
					
					<div class="clear"></div>
					<div id="newsupdate">
						<div class="pnlhd">News Update</div>
						<div class="clear"></div>
						<div class="scroltxt">
							<marquee direction="up" scrollamount="2" height="125px">
							It is a long established fact that a reader will be distracted by the readable content of a page when...
							<img src="images/activflag.png" width="17" height="12" border="0" /><br /><br />
							It is a long established fact that a reader will be distracted by the readable content of a page when...
							<img src="images/normal_flag.png" width="17" height="12" border="0" />
							</marquee>
							<!-- <div class="more">
								<a href="#"></a>
							</div> -->
						</div>
					</div>
				</div>
			</td>
			<td valign="top" class="tblclear" id="span_2"><div class="extab">
					<img id="collapseImg" height="28" width="10" border="0"
						align="left" alt="" src="images/collapse.jpg"
						onclick="hideWrraper();" />
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
