    <div id="reports-model" class="modal fade" role="dialog"  >
			    <div class="modal-dialog modal-lg">
			
					
		    <div class="modal-content">
			      	<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title"><c:out value="Reports" escapeXml="true"></c:out></h4>
			      </div>
			    <div class="modal-body ">
				<ol>
                <li> <a class="hyperlink" href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWSTATE"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWSUBDIST"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWBLOCK"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewblockwiseVillageandUlbforcitizen.do?<csrf:token uri='globalviewblockwiseVillageandUlbforcitizen.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message></a></li>
          		<li> <a class="hyperlink" href="viewWard.do?<csrf:token uri='viewWard.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWWARD"></spring:message></a></li>
                <li> <a class="hyperlink" class="hyperlink" href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>"> <spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>"> <spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTOFLB"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateforRuralLB.do?<csrf:token uri='rptConsolidateforRuralLB.do'/>"> <spring:message htmlEscape="true" code="Label.ConsolidatedReportForRuralLB"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateforLandregions.do?<csrf:token uri='rptConsolidateforLandregions.do'/>"> <spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTONLRENTITIES"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateVillageGramPanchayat.do?<csrf:token uri='rptConsolidateVillageGramPanchayat.do'/>"> <spring:message htmlEscape="true" code="Label.ConsolidatedReportDistrictWise"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>"> <spring:message htmlEscape="true" code="form.rptConsolidateBlockGramPanchayat" text="Report on District wise Blocks and Mapped Gram Panchayats"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="rptStatePanchayats.do?<csrf:token uri='rptStatePanchayats.do'/>"> <spring:message htmlEscape="true" code="Label.StatePanchayatReportSetup"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="stateWiseUnmappedVillagesReport.do?<csrf:token uri='stateWiseUnmappedVillagesReport.do'/>"> <spring:message htmlEscape="true" code="Label.StateWiseUnmappedVillages"></spring:message> (Villages not mapped to any village panchayats/equivalent local body)</a></li>
                <li> <a class="hyperlink" href="rptMappedGPNWardforPCAC.do?<csrf:token uri='rptMappedGPNWardforPCAC.do'/>"> <spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></a></li>
                <li><b>State wise Village Mapping Status</b>
                <ol type="a">
           			<li> <a class="hyperlink" href="stateWiseGPtoVillageMappingReport.do?<csrf:token uri='stateWiseGPtoVillageMappingReport.do'/>"> <spring:message htmlEscape="true" code="Label.stateWiseGPtoVillageMappingStatus"></spring:message></a></li>
           			<li> <a class="hyperlink" href="stateWiseGPtoVillageMappingReportB.do?<csrf:token uri='stateWiseGPtoVillageMappingReportB.do'/>"> <spring:message htmlEscape="true" text="State wise Village to GP Mapping Status"></spring:message> </a></li>
           		</ol>
           		</li>
           		<li> <a class="hyperlink" href="stateWiseGramPanchayats.do?<csrf:token uri='stateWiseGramPanchayats.do'/>"> <spring:message htmlEscape="true" code="Label.StateWiseGPCencusVillageMapping"></spring:message></a></li>
           		<li> <a class="hyperlink" href="unmapLBWardforPCAC.do?<csrf:token uri='unmapLBWardforPCAC.do'/>"> <spring:message code="Label.unmappedLBWARDPCAC" /></a></li>
           		<li> <a class="hyperlink" href="districtWiseLBReport.do?<csrf:token uri='districtWiseLBReport.do'/>"> <spring:message code="Label.distwiselbreport" /></a></li>
           		<li> <a class="hyperlink" href="lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>"> <spring:message code="label.HeaderSummaryReportWard" /></a></li>
           		<li> <a class="hyperlink" href="rptViewUnmappedLocalBodies.do?<csrf:token uri='rptViewUnmappedLocalBodies.do'/>"> <spring:message htmlEscape="true" code="Label.viewUnmappedlocalbodies"></spring:message></a></li>
           		<li> <a class="hyperlink" href="rptDistrictWiseInvalidatedVillage.do?<csrf:token uri='rptDistrictWiseInvalidatedVillage.do'/>"> <spring:message htmlEscape="true" code="Label.rptdistrictWiseInvalidatedVillage"></spring:message></a></li>
           		<li> <a class="hyperlink" href="dpwardConstituencyWiseVP.do?<csrf:token uri='dpwardConstituencyWiseVP.do'/>"> <spring:message htmlEscape="true" code="Label.DPwardConstituencyWiseVP"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="districtWiseDetailReport.do?<csrf:token uri='districtWiseDetailReport.do'/>"> <spring:message htmlEscape="true" code="Label.DistrictWiseDetailReport" text="District wise Detailed Report"></spring:message></a></li>
           		<li> <a class="hyperlink" href="listOfPesaPanchyat.do?<csrf:token uri='listOfPesaPanchyat.do'/>"> <spring:message htmlEscape="true" code="Label.ListofPESAPanchayat" text="List of PESA Panchayat"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="habitation.do?<csrf:token uri='habitation.do'/>"> <spring:message htmlEscape="true" code="Label.habitation" text="Habitations Report"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="departOrganizationUnit.do?<csrf:token uri='departOrganizationUnit.do'/>"> <spring:message htmlEscape="true" code="Label.ROOUNIT" text="Report on Organization Unit"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="nomenClatureSubdistrictReport.do?<csrf:token uri='nomenClatureSubdistrictReport.do'/>"> <spring:message htmlEscape="true" code="Label.NomenclatureofSub-DistrictReport" text="Report on Nomenclature of Sub-District"></spring:message></a></li>
           		<li> <a class="hyperlink" href="departDesigOrgLevel.do?<csrf:token uri='departDesigOrgLevel.do'/>"> <spring:message htmlEscape="true" code="Label.DepartDesigOrgLevelReport" text="Report on Designation details"></spring:message></a></li>
           		<li> <a class="hyperlink" href="wardToHalkaMappingReport.do?<csrf:token uri='wardToHalkaMappingReport.do'/>"> <spring:message htmlEscape="true" code="Label.WardHalkaMappingReport" text="Ward to Halka Mapping Report (Jharkhand State)"></spring:message></a></li>
           		<li> <a class="hyperlink" href="halkaToVillageMappingReport.do?<csrf:token uri='halkaToVillageMappingReport.do'/>"> <spring:message htmlEscape="true" code="Label.halkaToVillageMappingReport" text="Halka to Village Mapping Report (Jharkhand State)"></spring:message></a></li>
           		<li> <a class="hyperlink" href="globalViewStatewiseLocalBody.do?<csrf:token uri='globalViewStatewiseLocalBody.do'/>"> <spring:message htmlEscape="true" code="Label.globalViewStatewiseLocalBody" text="StateWise GIS Mapped Local Body"></spring:message></a></li>
           		<li> <a class="hyperlink" href="globalViewLocalBodyMappedToDistricts.do?<csrf:token uri='globalViewLocalBodyMappedToDistricts.do'/>"> <spring:message htmlEscape="true" code="Label.viewmappedlocalbodiestodistricts" text="Local Bodies Mapped to more than One District"></spring:message></a></li>
           		<li> <a class="hyperlink" href="stateWisefreezedDistrict.do?<csrf:token uri='stateWisefreezedDistrict.do'/>"> <spring:message htmlEscape="true" code="Label.stateWisefreezedDistrict" text="View State Wise Freezed Status"></spring:message></a></li>
           		<li> <a class="hyperlink" href="stateWisePopulation.do?<csrf:token uri='stateWisePopulation.do'/>"> State Wise Population</a></li>
           		<li> <a class="hyperlink" href="nofnStates.do?<csrf:token uri='nofnStates.do'/>"> NOFN Panchayat List</a></li>
				<li><a class="hyperlink" href="changedEntity.do?<csrf:token uri='changedEntity.do'/>">List of modification done in LGD</a></li> 
                <li><a class="hyperlink" href="gisMapVerificationStatus.do?<csrf:token uri='gisMapVerificationStatus.do'/>">GIS Map Verification Status</a></li>
                <li><a class="hyperlink" href="registredUserIpReport.do?<csrf:token uri='registredUserIpReport.do'/>">Report on consumers of web services</a></li>
              <%--   <li><a class="hyperlink" href="getStatewiseEntitiesCount.do?<csrf:token uri='getStatewiseEntitiesCount.do'/>">Consolidated Report of LGD</a></li> --%>
				<li><a class="hyperlink" href="getStateWiseUrbanLB.do?<csrf:token uri='getStateWiseUrbanLB.do'/>">Report on State Wise number of Urban Localbodies and Wards</a></li>
                <li><a class="hyperlink" href="getStatewiseLastupdated.do?<csrf:token uri='getStatewiseLastupdated.do'/>">Report on State wise Last Updation Date</a></li>
                <li><a class="hyperlink" href="getStatewiseLGDDataConfirmation.do?<csrf:token uri='getStatewiseLGDDataConfirmation.do'/>">Report on State wise LGD Data Confirmation</a></li>
                <li><a class="hyperlink" href="getStatewiseTotalGPNPartlyMappedGP.do?<csrf:token uri='getStatewiseTotalGPNPartlyMappedGP.do'/>">Number of village Panchayats/Equivalent local bodies which are mapped with part of villages</a></li>
                <li><a class="hyperlink" href="getStatewiseVillageMappedCount.do?<csrf:token uri='getStatewiseVillageMappedCount.do'/>">State Wise Village Mapping Status</a></li>
                 <li><a class="hyperlink" href="stateWiseUnmappedVillages.do?<csrf:token uri='stateWiseUnmappedVillages.do'/>">State Wise Unmapped Vilages List</a></li>
                 <li> <a class="hyperlink" href="rptConsolidateforRuralLBPES.do?<csrf:token uri='rptConsolidateforRuralLBPES.do'/>"> <spring:message htmlEscape="true" code="Label.ConsolidatedReportForRuralLB"></spring:message> as available in other PES applications</a></li>
                 <li> <a class="hyperlink" href="localBodyInvalidation.do?<csrf:token uri='localBodyInvalidation.do'/>"> <spring:message htmlEscape="true" code="Report on Invalidated Local Bodies"></spring:message> </a></li>
                 <li> <a class="hyperlink" href="exceptionReportBasedonYear.do?<csrf:token uri='exceptionReportBasedonYear.do'/>"> <spring:message htmlEscape="true" code="Villages which are partially mapped with more than one village level rural local bodies"></spring:message> </a></li>
                 <li> <a class="hyperlink" href="getLocalBodyInDisturbedState.do?<csrf:token uri='getLocalBodyInDisturbedState.do'/>"> <spring:message htmlEscape="true" code="Report on Local Bodies in Disturb State"></spring:message> </a></li>
                 <li><a class="hyperlink" href="rptVillageConvertedRuralToUrban.do?<csrf:token uri='rptVillageConvertedRuralToUrban.do'/>"><spring:message code="Label.VillageConvertedRuralToUrban" /></a></li>
                 </ol>
                 
                 
                 <div class="box-header subheading">
                  		<h4 class="box-title">
                  		<c:out value="Exceptional Reports" />
                  	</h4>
               	    </div><!-- /.box-header -->
               	    
               	    
               	     <ol type="A">
               	     <li> <a class="hyperlink" id="exceptionalReport1" param="ER_InvalidateCensusVillage" paramLink="Invalidated Census villages"> <c:out value="Invalidated Census villages"/> </a></li>
               	     <li> <a class="hyperlink" id="exceptionalReport2" param="ER_GPMaptoVillMulBlock" paramLink="GPs mapped to villages of multiple blocks"> <c:out value="GPs mapped to villages of multiple blocks"/> </a></li>
               	     <li> <a class="hyperlink" id="exceptionalReport3" param="ER_GPMaptoVillMulDist" paramLink="GPs mapped to villages of multiple districts"> <c:out value="GPs mapped to villages of multiple districts"/> </a></li>
               	  	 <li> <a class="hyperlink" id="exceptionalReport4" param="ER_VillUnmapBlockExclVillageMapULB" paramLink="Villages which are not mapped to any Block by excluding villages mapped ULBs"> <c:out value="Villages which are not mapped to any Block by excluding villages mapped ULBs"/> </a></li>
               	     <li> <a class="hyperlink" id="exceptionalReport5" param="ER_BLockMapVillMulDist" paramLink="Blocks mapped to villages of multiple districts"> <c:out value="Blocks mapped to villages of multiple districts"/> </a></li>
               	     <li> <a class="hyperlink" id="exceptionalReport6" param="ER_InhabVillUnmapLocalbody" paramLink="Inhabitant villages not mapped to any local body"> <c:out value="Inhabitant villages not mapped to any local body"/> </a></li>
               	    <li> <a class="hyperlink" href="exceptionReportForFullyMappedCover.do?<csrf:token uri='exceptionReportForFullyMappedCover.do'/>"> <spring:message htmlEscape="true" code="List of land region entity FULLY mapped under more than one local bodies"></spring:message> </a></li>
               	    <li> <a class="hyperlink" href="exceptionalReportOnStateSelection.do?<csrf:token uri='exceptionalReportOnStateSelection.do'/>"> <spring:message htmlEscape="true" code="Villages which are mapped to block but not mapped to Rural local bodies and vice versa"></spring:message> </a></li>
               	    <li> <a class="hyperlink" href="exceptionalReportOnUrbanLbNoWard.do?<csrf:token uri='exceptionalReportOnUrbanLbNoWard.do'/>"> <spring:message htmlEscape="true" code="List of All urban  local bodies with no Wards."></spring:message> </a></li>
               	    
               	     </ol>
               	    
               	    
				</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		   </div>
					
					
			
			    </div>
			</div>
			
			<script>
			$("a[id^='exceptionalReport']").bind({
				click : function() {
				var fileName = $(this).attr('param');
				var reportName=$(this).attr('paramLink');
				//alert(fileName);
				$(window).attr("location","exceptionalReports.do?fileName="+ fileName +"&reportName="+reportName+"&<csrf:token uri='exceptionalReports.do'/>");
				}
			});
			
			</script>