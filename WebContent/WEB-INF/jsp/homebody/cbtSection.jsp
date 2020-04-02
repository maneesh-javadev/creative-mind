<div id="cbt-model" class="modal fade" role="dialog"  >
			    <div class="modal-dialog modal-lg">
			
					
		    <div class="modal-content">
			      	<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title"><c:out value="SUPPORTING DOCUMENTS" escapeXml="true"></c:out></h4>
			      </div>
			    <div class="modal-body ">
					<ul>
                                        
                         <li><a id="linkSupDocCbtonline" param="cbt" paramLink="CBT/LGD Combined CBT Ver 2.0.html" paramFName="CBT(Play Online)"><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> CBT (Play Online)</a></li>
                         <li><a id="linkSupDocCbtoffline" param="cbt" paramLink="cbt.htm?docType=cbtplaypath" paramFName='CBT(Play Offline)'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> CBT (Play Offline)</a></li>
                         <li><a id="linkSupDocPresentaion" param="presentation" paramLink="cbt.htm?docType=presentationfilepath" paramFName='Presentation'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> Presentation</a></li>
                         <li><a id="linkSupDocBrouchre" param="brochure" paramLink="cbt.htm?docType=Brochure" paramFName='Brochure'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> Brochure</a></li>
                         <li><a id="linkSupDocUsermanual" param="usermanual" paramLink="cbt.htm?docType=UserManualfilepath" paramFName='User Manual'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> User Manual</a></li>
                         <li><a id="linkSupDocUsermanual" param="usermanual.urban.development" paramLink="cbt.htm?docType=UserManualfilepathUrban" paramFName='User Manual for Urban Development Department'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> User Manual for Urban Development Department</a></li>
                         <li><a id="linkSupDocUsermanual" param="usermanual.revenue.development" paramLink="cbt.htm?docType=UserManualfilepathRevenue" paramFName='User Manual for Revenue Department'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> User Manual for Revenue Department</a></li>
                         <li><a id="linkSupDocUsermanual" param="usermanual.gis" paramLink="cbt.htm?docType=UserManualfilepathGIS" paramFName='User Manual of GIS Module'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> User Manual of GIS Module</a></li> 
                         <li><a id="linkSupDocUsermanual" param="usermanual.LDC" paramLink="cbt.htm?docType=UserManualfilepathLDC" paramFName='User Manual of Data Confirmation'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> User Manual of Data Confirmation Module</a></li> 
                         <li><a id="linkSupDocRegister" param="register" paramLink="cbt.htm?docType=DataCollectionRegisterfilepath" paramFName='Data Registers'><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --> Data Register</a></li>
                         <li><a href="#"  onclick="viewDishaHelpInPopup();hideCBT();"><!-- <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> --><c:out value="Quick Help"/></a></li>
                      
                     </ul>
				</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		   </div>
					
					
			
			    </div>
			</div>
			
			<script>
			function hideCBT(){
				$('#cbt-model').modal('hide');
			}
			
			</script>