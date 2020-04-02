           
<%-- <%@include file="../common/taglib_includes.jsp"%> --%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"	prefix="csrf"%>



<section id="introMain">
   <div class="col-md-5 heading">
       <div id="typedStrings">
			<h1 class="hidden hidden-lg hidden-md hidden-sm hidden-xs">LGD</h1>
						<div class="string1Content" style="display: none;"> <p>Local</p></div>
                        <div class="string2Content" style="display: none;"><p>Government</p></div>
                        <div class="string3Content" style="display: none;"><p>Directory</p></div>
                        <div class="string4Content" style="display: none;">
                             <p>Complete directory of Land Regions/Revenue, Rural and Urban Local Governments</p>
                        </div>
                         <h1>
                         	<strong class="string1">Local</strong><span class="typed-cursor hidden"></span><span class="typed-cursor hidden"></span><br>
							<strong class="string2">Government</strong><span class="typed-cursor hidden"></span><span class="typed-cursor hidden"></span><br>
                            <strong class="string3">Directory</strong><span class="typed-cursor hidden"></span><span class="typed-cursor hidden"></span><br>
                            <em class="string4">Complete directory of Land Regions/Revenue, Rural and Urban Local Governments</em><span class="typed-cursor transparent"></span><span class="typed-cursor transparent"></span></h1>
                             <a href="#/#portfolioWrapper" class="btn btn-link solid hidden-sm hidden-md hidden-xs">
 						Overview
 						<i class="arrow-right"></i></a>
 		</div>
   </div>
    <div class="col-md-7">
   	<img src="${pageContext.request.contextPath}/resource/homebody-resource/images/lgdtreeview_animated-1500X878.gif" class="img-responsive"> 
    </div>
 </section>
   
   <c:set var="scount" value="0" />
   	<c:set var="dcount" value="0" />
    <c:set var="tcount" value="0" />
    <c:set var="bcount" value="0" />
    <c:set var="vcount" value="0" />
    <c:set var="lcount" value="0" />
   <c:if test="${!empty lgdEntitiesCountArr }">
   	<c:set var="scount" value="${lgdEntitiesCountArr[0]}" />
   	<c:set var="dcount" value="${lgdEntitiesCountArr[1]}" />
    <c:set var="tcount" value="${lgdEntitiesCountArr[2]}" />
    <c:set var="bcount" value="${lgdEntitiesCountArr[3]}" />
    <c:set var="vcount" value="${lgdEntitiesCountArr[4]}" />
    <c:set var="lcount" value="${lgdEntitiesCountArr[5]}" />
   </c:if>
   
<section class="scroll full-height black-bg fp-section fp-table" id="lgddashboard" style="height: 487px; background-image: url(&quot;images/background-activities.png&quot;);" data-anchor="dashboard"><div class="fp-tableCell" style="height:487px;">

               <div class="container-fluid">
                   <div class="row">
                       <div class="col-xs-12">
                           <h3 class="text-center sectionheading">Activities<br><br></h3>
                       </div>
                   </div>
                   <div>
                       <div class="row activities withbottommargin">


                           <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-state">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${scount}" /> </h3>
                                       <p>State</p>
                                   </div>
                                   <div class="icon">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/india-icon_75X75.png" style="float:right;top:20px;margin-top: 25px;">

                                   </div>
                                   <a href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>
                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-district">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${dcount}" /></h3>
                                       <p>District</p>
                                   </div>
                                   <div class="icon"><img src="${pageContext.request.contextPath}/resource/homebody-resource/images/state-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">


                                   </div>
                                   <a href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>

                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-subdistrict">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${tcount}" /></h3>
                                       <p>Sub-District</p>
                                   </div>
                                   <div class="icon">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/district-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive" width="75" height="75">

                                   </div>
                                   <a href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>


                       </div>
                       <div class="row activities">

                           <div class="col-lg-4 col-md-4  col-sm-6 col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-villagepanchayat">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${bcount}" /></h3>
                                       <p>
                                           <!--Village Panchayat-->Block</p>
                                   </div>
                                   <div class="icon">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/village-panchayat-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                   </div>
                                   <a href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>" target="_blank" class="small-box-footer">More Info <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                              
                           </div>
                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                                <!-- small box -->
                               <div class="small-box bg-village">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${vcount}" /></h3>
                                       <p>Village</p>
                                   </div>
                                   <div class="icon">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/village-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                   </div>
                                   <a href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>
                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-localbodies">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${lcount}" /></h3>
                                       <p>Local Bodies</p>
                                   </div>
                                   <div class="icon">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/local-bodies-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                   </div>
                                   <a href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>" target="_blank" title="National Panchayat Portal" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>

                       </div>
                   </div>


               </div>
           </div>
 </section>
           
   <!-- ///////////////////////////////////////////////Activites //////////////////////////////////////////////////////////////////////-->            
           
<section>
               <div id="skilled-slider" class="carousel slide" data-ride="carousel">
                   <div class="carousel-inner" role="listbox">
                       <div class="item">
                           <div class="container">
                               <div class="row">
                                   <div class="col-lg-12 col-md-12">
                                       <div class="img-wrapper">
                                           <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/panchayatlogo.png" alt="Panchayati Raj" class="img-responsive">
                                       </div>
                                       <h3><span>About</span>
                                           <p><strong>Local Government Directory</strong></p>
                                       </h3>
                                       <p class="text-justify introtext">Introduction Local Government Directory (LGD) is one of the applications developed as part of <span>Panchayat Enterprise Suite (PES)</span> under <span>e-Panchayat Mission Mode project (MMP)</span> (<a href="http://epanchayat.gov.in" alt="E-panchayat">http://epanchayat.gov.in</a>). LGD aims to keep all information about the structure of Local Governments and revenue entities online. Main objective of LGD is to maintain up-to-date list of revenue entities (districts/subdistricts/villages), Local Government Bodies (Panchayats, Municipalities and traditional bodies) and their wards, organizational structure of Central, State and Local Governments, reporting hierarchy within the government organizations and parliamentary and assembly constituencies and their relationship with one another. </p>
                                   </div>
                               </div>
                           </div>
                       </div>


                       <div class="item active">
                           <div class="container">
                               <div class="row">
                                   <div class="col-lg-4 col-md-6">
                                       <!-- <div class="img-wrapper">
                              <span style="color:#eb6e04"> <i class="fa fa-university  fa-3x" aria-hidden="true"></i></span>
                           </div>-->
                                       <h3>
                                           <strong>Useful for:</strong>
                                       </h3>
                                       <ul class="liststyle">
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Panchayati Raj Departments</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Urban Development Departments</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Revenue Departments</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Election Commissions</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> Registered Software Applications</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> Citizens</li>
                                       </ul>

                                   </div>
                                   <div class="col-lg-8 col-md-6">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/usefulfor1.png" style="float:right" class="img-responsive">

                                   </div>
                               </div>
                           </div>
                       </div>

                       <div class="item">
                           <div class="container">
                               <div class="row">
                                   <div class="col-lg-4 col-md-6">
                                       <!--<div class="img-wrapper">
                              <img src="images/skilled.png" alt="skilled">

                           </div>-->
                                       <h3>
                                           <strong>
                                   Key Features:
                               </strong>
                                       </h3>
                                       <ul class="liststyle">
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> Unique Code to Land/Region</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> Enable Interoperability</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> Notification generated online</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> SMS/Email notifications</li>
                                           <li><i class="fa fa-check-circle" aria-hidden="true"></i> GIS based boundary</li>
                                       </ul>
                                   </div>
                                   <div class="col-lg-8 col-md-6">
                                       <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/keyfeatures.gif" style="float:right" class="img-responsive">

                                   </div>
                               </div>
                           </div>
                       </div>

                       <!-- Indicators -->
                       <div class="text-center">
                           <ol class="carousel-indicators">
                               <li data-target="#skilled-slider" data-slide-to="0" class=""></li>
                               <li data-target="#skilled-slider" data-slide-to="1" class="active"></li>
                               <li data-target="#skilled-slider" data-slide-to="2" class=""></li>
                           </ol>
                       </div>
                   </div>

               </div>

           </div></section>
           
           <hr >
   <section class="lgdwebservice scroll fp-section fp-table active" id="webservice" style="height: 200px; background-image: url(&quot;images/background-activities.png&quot;);" data-anchor="documents-and-downloads"><div class="fp-tableCell" style="height:200px;">
   <div class="row">
   <div class="col-md-12 text-center">
   <a href="#" title="Register here to consume Web Services" target="_parent"><img src="cloud-based-application.png" alt="Register here to consume Web Services" class="img-responsive center-block"></a>
   </div>
   </div></div>
   </section>        
       <hr />
           
 <section class="lgdfeatures scroll fp-section fp-table active" id="lgdfeatures" style="height: 487px; background-image: url(&quot;images/background-activities.png&quot;);" data-anchor="documents-and-downloads"><div class="fp-tableCell" style="height:487px;">
               <div class="fp-tableCell" style="height: 755px;">
                   <div class="container-fluid">
                       <div class="row">
                           <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block pinkfont">
                                           <i class="fa fa-folder-open fa-4x" aria-hidden="true"></i>
                                           <!--<img src="images/development-icon.png" alt="Supporting Documents">-->
                                            <h4>
                                                <a href="#/supportingdocuments">Supporting Documents </a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                        
                                            <li><a id="linkSupDocCbtonline" param="cbt" paramLink="CBT/LGD Combined CBT Ver 2.0.html" paramFName="CBT(Play Online)"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i>CBT (Play Online)</a></li>
                                            <li><a id="linkSupDocCbtoffline" param="cbt" paramLink="cbt.htm?docType=cbtplaypath" paramFName='CBT(Play Offline)'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> CBT (Play Offline)</a></li>
                                            <li><a id="linkSupDocPresentaion" param="presentation" paramLink="cbt.htm?docType=presentationfilepath" paramFName='Presentation'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Presentation</a></li>
                                            <li><a id="linkSupDocBrouchre" param="brochure" paramLink="cbt.htm?docType=Brochure" paramFName='Brochure'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Brochure</a></li>
                                            <li><a id="linkSupDocUsermanual" param="usermanual" paramLink="cbt.htm?docType=UserManualfilepath" paramFName='User Manual'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> User Manual</a></li>
                                            <li><a id="linkSupDocRegister" param="register" paramLink="cbt.htm?docType=DataCollectionRegisterfilepath" paramFName='Data Registers'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Data Register</a></li>
                                         <!--    <li><a href="#" data-toggle="modal" data-target="#Supporting-document-CBTOffline"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> CBT (Play Offline)</a></li>
                                            <li><a href="#" data-toggle="modal" data-target="#Supporting-document-CBTOnline"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> CBT (Play Online)</a></li>
                                            <li><a href="#" data-toggle="modal" data-target="#Supporting-document-Presentation"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Presentation</a></li>
                                            <li><a href="#" data-toggle="modal" data-target="#Supporting-document-Brochure"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Brochure</a></li>
                                            <li><a href="#" data-toggle="modal" data-target="#Supporting-document-Usermanual"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> User Manual</a></li>
                                            <li><a href="#" data-toggle="modal" data-target="#Supporting-document-DataRegister"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Data Register</a></li> -->
                                        </ul>

                                        <!-- <a href="#" class="btn btn-link solid btn-small">More Info...<i class="arrow-right sm"></i></a>-->

                                   </div>
                               </div>
                           </section>
                           <section class="col-sm-3 square-section">
                               <div class="content-container ">
                                   <div class="translated-block">
                                       <div class="icon-block center-block">
                                           <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/training_details.png" alt="Training Details">
                                           <h4>
                                               <a href="http://trainingonline.gov.in/rptConsolidateforTrainingExternal.htm?code=8543&&entity_type=O" target="_blank">Training Details</a>
                                           </h4>
                                       </div>
                                   </div>

                                   <div class="service-components">
                                       <!-- <ul>
                                           <li> <a href="#/lgdfeatures/tranining-details"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Training Details</a></li>
                                       </ul> -->
                                       <!-- <a href="#" class="btn btn-link solid btn-small">
							  More Info
							  <i class="arrow-right sm"></i>
							</a>-->

                                   </div>
                               </div>
                           </section>
                           <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block">
                                           <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/citizen.png" alt="Citizen">
                                           <h4>
                                               <a href="#/lgdfeatures/citizen">Citizen</a>
                                           </h4>
                                       </div>
                                   </div>

                                   <div class="service-components">
                                       <ul>
                                           <li><a href="#/lgdfeatures/register-here-for-e-mail-sms-notifications"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Register here for E-mail/SMS Notifications </a></li>
                                       </ul>

                                       <!-- <a href="#" class="btn btn-link solid btn-small">More Info<i class="arrow-right sm"></i></a>-->

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block greenfont">

                                          <a href="#" onclick="showReports();" > 
                                           <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/reports.png" alt="Reports">
										   </a>

                                          

                                            <h4>
                                                <a href="#/lgdfeautes/reports" onclick="showReports();">REPORTS</a>
                                            </h4>
                                        </div>
                                    </div>



                            </section>
								</div>
								<div class="service-components">
       							</div>
							</section>
								</div>
								<div class="service-components">
       							</div>
							</section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block yellowfont">
                                            <a href="#" onclick="showFAQ();" class="icon-block yellowfont"> <i class="fa fa-question-circle fa-4x" aria-hidden="true"></i></a>
                                            <h4>

                                                <a href="#/services/faq" onclick="showFAQ();">FAQs</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                            <li> <a href="#/lgdfeatures/faqs">If you have any Queries to be answered by the PES Technical Team</a></li>
                                        </ul>

                                        <a href="globalAddQuery.do?<csrf:token uri='globalAddQuery.do'/>" class="btn btn-link solid btn-small">Click here<i class="arrow-right sm"></i></a>

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block darkpinkfont">
                                            <i class="fa fa-download fa-4x" aria-hidden="true"></i>
                                            <h4>
                                                <a href="#/lgdfeatures/directory">Directory</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <!-- <ul>
										<li><a href="#">#</a></li>
                               </ul>-->

                                       <a href="#" class="btn btn-link solid btn-small">Click to download... <i class="arrow-right sm"></i></a>

                                   </div>
                               </div>
                           </section>
                           <section class="col-sm-3  square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block">
                                           <i class="fa fa-android fa-4x androidcolor" aria-hidden="true"></i>
                                           <h4>
                                               <a href="downloadFile.do?<csrf:token uri='downloadFile.do'/>">LGD Android</a>
                                           </h4>
                                       </div>
                                   </div>
                                   <div class="service-components">
                                       <!-- <ul>
										<li><a href="#">#</a></li>
                               </ul>-->
                                       <a href="#" class="btn btn-link solid btn-small">Click to download<i class="arrow-right sm"></i></a>

                                   </div>
                               </div>
                           </section>
                           <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block bluefont">
                                           <i class="fa fa-bar-chart fa-4x" aria-hidden="true"></i>
                                           <h4>
                                               <a href="#/lgdfeatures/report-on-updation-status">REPORT ON UPDATION STATUS</a>
                                           </h4>
                                       </div>
                                   </div>
                                   <div class="service-components">
                                       <!-- <ul>
										<li><a href="#">#</a></li>
                               </ul>-->
                                       <a href="#" class="btn btn-link solid btn-small">Click to view...<i class="arrow-right sm"></i></a>

                                   </div>
                               </div>
                           </section>
                       </div>
                   </div>

               </div>
           </div>
		</section>
		  <section>
                <ul class="top-foot">
                    <li><a class="hyperlink" href="citizenFeedback.do?<csrf:token uri='citizenFeedback.do'/>">Feedback </a> </li>
                    <li>|</li>
                    <li><a class="hyperlink" href="contactUs.do?<csrf:token uri='contactUs.do'/>">Contact Us </a>  </li>
                    <li>|</li>
                    <li><a class="hyperlink" href="siteMap.do?<csrf:token uri='siteMap.do'/>">Site Map </a> </li>
                    <li>|</li>
                    <li><a class="hyperlink" href="privacyPolicy.do?<csrf:token uri='privacyPolicy.do'/>">Privacy Policy   </a></li>
                    <li>|</li>
                    <li><a class="hyperlink" href="copyRightPolicy.do?<csrf:token uri='copyRightPolicy.do'/>">Copyright Policy </a>  </li>
                    <li>|</li>
                    <li><a class="hyperlink" href="termsconditions.do?<csrf:token uri='termsconditions.do'/>">Terms And Conditions</a></li>
                </ul>
            </section>
		
		
		<div id="cbtdoc-model" class="modal fade" role="dialog" >
			    <div class="modal-dialog">
			
			        <!-- Modal content-->
			        <div class="modal-content">
			             <div id="dynamicDocStructure"></div>
			      </div> 
			
			    </div>
			</div>
            
 		<div id="faq-model" class="modal fade" role="dialog"  >
			    <div class="modal-dialog modal-lg">
			
					
					<div class="modal-content">
				      	<div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4 class="modal-title"><c:out value="FAQ" escapeXml="true"></c:out></h4>
				      </div>
				      <div class="modal-body pannel_box">
				        <div id="dynamicFAQStructure"></div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
				    </div>
					
					
			
			    </div>
			</div>
            
            <div id="reports-model" class="modal fade" role="dialog"  >
			    <div class="modal-dialog modal-lg">
			
					
		    <div class="modal-content">
			      	<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title"><c:out value="Reports" escapeXml="true"></c:out></h4>
			      </div>
			    <div class="modal-body ">
				<ol>
                <li> <a href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWSTATE"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWSUBDIST"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWBLOCK"></spring:message></a></li>
                <li> <a class="hyperlink" href="globalviewblockwiseVillageandUlbforcitizen.do?<csrf:token uri='globalviewblockwiseVillageandUlbforcitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message></a></li>
          		<li> <a class="hyperlink" href="viewWard.do?<csrf:token uri='viewWard.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWWARD"></spring:message></a></li>
                <li> <a class="hyperlink" class="hyperlink" href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTOFLB"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateforRuralLB.do?<csrf:token uri='rptConsolidateforRuralLB.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.ConsolidatedReportForRuralLB"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateforLandregions.do?<csrf:token uri='rptConsolidateforLandregions.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTONLRENTITIES"></spring:message></a></li>
                <li> <a class="hyperlink" href="rptConsolidateVillageGramPanchayat.do?<csrf:token uri='rptConsolidateVillageGramPanchayat.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.ConsolidatedReportDistrictWise"></spring:message></a></li>
           		<li> <a class="hyperlink" href="rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.RPTONDISTRICTWISEBLOCKANDMAPGRAMPANCH" text="Report on District wise Blocks and Mapped Gram Panchayats"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="rptStatePanchayats.do?<csrf:token uri='rptStatePanchayats.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.StatePanchayatReportSetup"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="stateWiseUnmappedVillagesReport.do?<csrf:token uri='stateWiseUnmappedVillagesReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.StateWiseUnmappedVillages"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="rptMappedGPNWardforPCAC.do?<csrf:token uri='rptMappedGPNWardforPCAC.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></a></li>
           		<li> <a class="hyperlink" href="stateWiseGPtoVillageMappingReport.do?<csrf:token uri='stateWiseGPtoVillageMappingReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.stateWiseGPtoVillageMappingStatus"></spring:message></a></li>
           		<li> <a class="hyperlink" href="stateWiseGramPanchayats.do?<csrf:token uri='stateWiseGramPanchayats.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.StateWiseGPCencusVillageMapping"></spring:message></a></li>
           		<li> <a class="hyperlink" href="unmapLBWardforPCAC.do?<csrf:token uri='unmapLBWardforPCAC.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message code="Label.unmappedLBWARDPCAC" /></a></li>
           		<li> <a class="hyperlink" href="districtWiseLBReport.do?<csrf:token uri='districtWiseLBReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message code="Label.distwiselbreport" /></a></li>
           		<li> <a class="hyperlink" href="lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message code="label.HeaderSummaryReportWard" /></a></li>
           		<li> <a class="hyperlink" href="rptViewUnmappedLocalBodies.do?<csrf:token uri='rptViewUnmappedLocalBodies.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.viewUnmappedlocalbodies"></spring:message></a></li>
           		<li> <a class="hyperlink" href="rptDistrictWiseInvalidatedVillage.do?<csrf:token uri='rptDistrictWiseInvalidatedVillage.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.rptdistrictWiseInvalidatedVillage"></spring:message></a></li>
           		<li> <a class="hyperlink" href="dpwardConstituencyWiseVP.do?<csrf:token uri='dpwardConstituencyWiseVP.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.DPwardConstituencyWiseVP"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="districtWiseDetailReport.do?<csrf:token uri='districtWiseDetailReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.DistrictWiseDetailReport" text="District wise Detailed Report"></spring:message></a></li>
           		<li> <a class="hyperlink" href="listOfPesaPanchyat.do?<csrf:token uri='listOfPesaPanchyat.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.ListofPESAPanchayat" text="List of PESA Panchayat"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="habitation.do?<csrf:token uri='habitation.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.habitation" text="Habitations Report"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="departOrganizationUnit.do?<csrf:token uri='departOrganizationUnit.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.ROOUNIT" text="Report on Organization Unit"></spring:message></a></li>
           	    <li> <a class="hyperlink" href="nomenClatureSubdistrictReport.do?<csrf:token uri='nomenClatureSubdistrictReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.NomenclatureofSub-DistrictReport" text="Report on Nomenclature of Sub-District"></spring:message></a></li>
           		<li> <a class="hyperlink" href="departDesigOrgLevel.do?<csrf:token uri='departDesigOrgLevel.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.DepartDesigOrgLevelReport" text="Report on Designation details"></spring:message></a></li>
           		<li> <a class="hyperlink" href="wardToHalkaMappingReport.do?<csrf:token uri='wardToHalkaMappingReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.WardHalkaMappingReport" text="Ward to Halka Mapping Report (Jharkhand State)"></spring:message></a></li>
           		<li> <a class="hyperlink" href="halkaToVillageMappingReport.do?<csrf:token uri='halkaToVillageMappingReport.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.halkaToVillageMappingReport" text="Halka to Village Mapping Report (Jharkhand State)"></spring:message></a></li>
           		<li> <a class="hyperlink" href="globalViewStatewiseLocalBody.do?<csrf:token uri='globalViewStatewiseLocalBody.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.globalViewStatewiseLocalBody" text="StateWise GIS Mapped Local Body"></spring:message></a></li>
           		<li> <a class="hyperlink" href="globalViewLocalBodyMappedToDistricts.do?<csrf:token uri='globalViewLocalBodyMappedToDistricts.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.viewmappedlocalbodiestodistricts" text="Local Bodies Mapped to more than One District"></spring:message></a></li>
           		<li> <a class="hyperlink" href="stateWisefreezedDistrict.do?<csrf:token uri='stateWisefreezedDistrict.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.stateWisefreezedDistrict" text="View State Wise Freezed Status"></spring:message></a></li>
           		<li> <a class="hyperlink" href="stateWisePopulation.do?<csrf:token uri='stateWisePopulation.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> State Wise Population</a></li>
           		<li> <a class="hyperlink" href="nofnStates.do?<csrf:token uri='nofnStates.do'/>"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> NOFN Panchayat List</a></li>
           	    
                       </ol>
				</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		   </div>
					
					
			
			    </div>
			</div>
            
             <script>
             
           //Accordian
          	function toggleIcon(e) {
              $(e.target)
                  .prev('.panel-heading')
                  .find(".more-less")
                  .toggleClass('glyphicon-plus glyphicon-minus');
          }
             
            function callToggle(){
            	 $('.panel-group').on('hidden.bs.collapse', toggleIcon);
                 $('.panel-group').on('shown.bs.collapse', toggleIcon);
            }
			 
            
             
       
            function showReports(){
            	$('#reports-model').modal('show')
            }
            
            function showFAQ() {
        		
        				lgdDwrInitialService.getAvailableFAQs(1,{
        					callback : function(data) {
        						
        						
        						
        						 $("#dynamicFAQStructure").empty();
        					 	var divFAQTemplate = $("#dynamicFAQStructure");
        						 
        						 
        						 var pannelGroupDiv = $("<DIV/>");
        						 pannelGroupDiv.attr("class", "panel-group");
        						 pannelGroupDiv.attr("role", "tablist");
        						 pannelGroupDiv.attr("aria-multiselectable", "true");
        						 
        						 
        						 jQuery.each(data,function(index,obj) {
        						 
        						
	        						var pannelContentDiv = $("<DIV/>");
	        						pannelContentDiv.attr("class", "panel panel-default");
	        						
	        						
	        						var questionDiv = $("<DIV/>");
	        						questionDiv.attr("id", ("heading"+index));
	        						questionDiv.attr("class", "panel-heading");
	        						questionDiv.attr("role", "tab");
	        						
	        						var questionHead = $("<H4/>");
	        						questionHead.attr("class", "panel-title");
	        						
	        						var questionAnchor = $("<A/>");
	        						questionAnchor.attr("role", "tab");
	        						questionAnchor.attr("data-toggle", "collapse");
	        						questionAnchor.attr("data-parent", "#accordion");
	        						questionAnchor.attr("href", ("#collapse"+index));
	        						questionAnchor.attr("aria-expanded", "true");
	        						questionAnchor.attr("aria-controls", ("collapse"+index));
	        						
	        						var questionI = $("<I/>");
	        						questionI.attr("class", "more-less glyphicon glyphicon-plus");
	        						
	        						var qstn = obj.faqQuestionText.replace("<p>","").replace("</p>","");
	        						
	    							
	        						
	        						
	        						
	        						
	    							/* $(questionI).append(qstnLbl); */
	    							//$(questionAnchor).append(questionI);
	    							questionAnchor.html(qstn+"<i class='more-less glyphicon glyphicon-plus'></i> ");
	    							$(questionHead).append(questionAnchor);
	    							$(questionDiv).append(questionHead);
	    							
	    							var ansDiv = $("<DIV/>");
	    							ansDiv.attr("id", ("collapse"+index));
	    							ansDiv.attr("class", "panel-collapse collapse");
	    							ansDiv.attr("role", "tabpanel");
	    							ansDiv.attr("aria-labelledby", ("heading"+index));
	        						
	    							var ansPannel = $("<DIV/>");
	    							ansPannel.attr("class", "panel-body");
	    							
	    							ansPannel.html(obj.faqAnswerText);
	    							
	    							
	    							$(ansDiv).append(ansPannel);
	    							
	    							$(pannelContentDiv).append(questionDiv);
	    							$(pannelContentDiv).append(ansDiv);
	    							
	    							$(pannelGroupDiv).append(pannelContentDiv);
	    							
        						 });
        						 
        						
        						 
        						
        						 $(divFAQTemplate).append(pannelGroupDiv);
        						 
        						 callToggle();					
        						
        						
        						
        						},
        						errorHandler : function(
        								errorString) {
        							alert("EXCEPTION : "
        									+ errorString);
        						},
        						async : true,

        					});
        				
        				$('#faq-model').modal('show')	
        	}


        	$("a[id^='linkSupDoc']").bind({
        			click : function() {
        							var link = $(this).attr( 'paramLink');
        							var filename = $(this).attr('paramFName');
        							var propKey = $(this).attr('param');
        							lgdDwrInitialService.getSupportDownloadDoc(link,filename,propKey,{
        	        					callback : function(data) {
        	        					
        	        						if(data!=null && data.length>0){
            	        						docdetArr=data.split("#");
            	        						
            	        						$("#dynamicDocStructure").empty();
            	        					 	var divDocTemplate = $("#dynamicDocStructure");
            	        						 
            	        						//------#Header Part started ----
            	        						var modalHeadDiv = $("<DIV/>");
            	        						modalHeadDiv.attr("class", "modal-header");
            	        						
            	        						var modalHeadButton = $("<BUTTON/>");
            	        						modalHeadButton.attr("class", "close");
            	        						modalHeadButton.attr("type", "button");
            	        						modalHeadButton.attr("data-dismiss", "modal");
            	        						modalHeadButton.html("&times;");
            	        					
            	        						var modalHeading = $("<H4/>");
            	        						modalHeading.attr("class", "modal-title");
            	        						modalHeading.html(docdetArr[0]);
            	        						
            	        						$(modalHeadDiv).append(modalHeadButton);
            	        						$(modalHeadDiv).append(modalHeading);
            	        						//------#Header Part end ----
            	        						
            	        						//------#body Part started ---
            	        						var modalbodyDiv = $("<DIV/>");
            	        						modalbodyDiv.attr("class", "modal-body");
            	        						//-- last modified
            	        						if(docdetArr[1]!=""){
            	        							var p1=$("<P/>");
                	        						            	        						
                	        						/* var strong1=$("<STRONG/>");
                	        						strong1.html("File Last Modified:");
                	        						
                	        						$(p1).append(strong1); */
                	        						p1.html("<strong>File Last Modified:</strong>"+docdetArr[1]);
                	        						$(modalbodyDiv).append(p1);
            	        						}
            	        						//-- file description
            	        						
    											var p2=$("<P/>");
            	        						
    											/* var strong2=$("<STRONG/>");
            	        						strong2.html("File Description:");
            	        						
            	        						$(p2).append(strong2); */
            	        						p2.html("<strong>File Description:</strong>"+docdetArr[2]);
            	        						$(modalbodyDiv).append(p2);
            	        						//-- file type
            	        						if(docdetArr[3]!=""){
    											var p3=$("<P/>");
            	        						
    											/* var strong3=$("<STRONG/>");
            	        						strong3.html("File Type:"); */
            	        						
            	        						
            	        						/* 
            	        						var i3=$("<I/>");
            	        						i3.attr("class",docdetArr[4] );
            	        						i3.attr("aria-hidden","true" ); */
            	        						
            	        						//$(p3).append(strong3);
            	        						p3.html("<strong>File Type: </strong>"+docdetArr[3]+"<i class='"+docdetArr[4]+"' aria-hidden='true'></i>");
            	        						//$(p3).append(i3);
            	        						$(modalbodyDiv).append(p3);
            	        						}
            	        						
            	        						//-- play button
            	        						if(docdetArr[3]=="" && docdetArr[1]=="")
           	        							{
            	        							var p4=$("<P/>");
            	        							p4.attr("class","text-center");
            	        							
            	        							var a4=$("<A/>");
            	        							a4.attr("href",docdetArr[5]);
            	        							a4.attr("class","download btn btn-success");
            	        							a4.attr("role","button");
            	        							
            	        							var i4=$("<I/>");
                	        						i4.attr("class","fa fa-download" );
                	        						i4.attr("aria-hidden","true" );
                	        						
                	        						$(a4).append(i4);
                	        						a4.html("Play");
                	        						$(p4).append(a4);
                	        						$(modalbodyDiv).append(p4);
                	        						
           	        							}else{
           	        								var p5=$("<P/>");
                	        						
                	        						/* var strong5=$("<STRONG/>");
                	        						strong5.html("File size:");
                	        						
                	        						$(p5).append(strong5); */
                	        						p5.html("<strong>File size:</strong>"+docdetArr[6]+" MB");
                	        						
                	        						
                	        						var p4=$("<P/>");
            	        							p4.attr("class","text-center");
            	        							
            	        							
            	        							
            	        							var a4=$("<A/>");
            	        							a4.attr("href",docdetArr[5]);
            	        							a4.attr("class","download btn btn-success");
            	        							a4.attr("role","button");
            	        							
            	        							var i4=$("<I/>");
                	        						i4.attr("class","fa fa-download" );
                	        						i4.attr("aria-hidden","true" );
                	        						
                	        						$(a4).append(i4);
                	        						a4.html("Download File");
                	        						$(p4).append(a4);
                	        						
                	        						$(modalbodyDiv).append(p5);
                	        						$(modalbodyDiv).append(p4);
           	        								
           	        							}
            	        						
            	        						//------#body Part End ---
            	        						//------#modal footer started ---
            	        						var modalfootDiv = $("<DIV/>");
            	        						modalfootDiv.attr("class", "modal-footer");
            	        						
            	        						var modalfootButton = $("<BUTTON/>");
            	        						modalfootButton.attr("class", "btn btn-default");
            	        						modalfootButton.attr("type", "button");
            	        						modalfootButton.attr("data-dismiss", "modal");
            	        						modalfootButton.html("Close");
            	        						
            	        						$(modalfootDiv).append(modalfootButton);
            	        						$(divDocTemplate).append(modalHeadDiv);
            	        						$(divDocTemplate).append(modalbodyDiv);
            	        						$(divDocTemplate).append(modalfootDiv);
            	        					}	
        	        					},
                						errorHandler : function(
                								errorString) {
                							alert("EXCEPTION : "
                									+ errorString);
                						},
                						async : true,

                					});
        							
        							/* $("#myIframe").contents().find("body").html('');
        							$('#myIframe').attr('src',"supportdownloaddoc.do?<csrf:token uri='supportdownloaddoc.do'/>&link="
													+ link+ "&filename="+ filename+ "&propkey="+ propKey); */
        							$('#cbtdoc-model').modal('show')	
        						}
        						
        		});
        	
        	

            </script>
            
            
            
            
            
            
            
            
            
            
            
            
            
            <%-- <script src="resource/homebody-resource/js/vendor.js"></script>
            <section id="introMain" class="scroll full-height black-bg loaded fp-section fp-table active mses-slide-active">
                <div class="fp-tableCell" style="height: 755px;">
                    <div class="fullpage-backgrounds visible-lg visible-md visible-sm visible-xs">
                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/lgdtreeview_animated-1500X878.gif" class="img-responsive mainlgdtreeviewimg"> </div>

                    <div>
                        <div class="container-fluid" id="home">

                            <div class="row flex-center">
                                <div class="col-xs-12 col-md-11 col-lg-4 col-md-offset-1 visible-lg visible-md visible-sm visible-xs">
                                    <div class="heading">
                                        <div id="typedStrings">

                                            <h1 class="hidden hidden-lg hidden-md hidden-sm hidden-xs">
                                                LGD</h1>

                                            <div class="string1Content" style="display: none;">
                                                <p>Local</p>
                                            </div>
                                            <div class="string2Content" style="display: none;">
                                                <p>Government</p>
                                            </div>
                                            <div class="string3Content" style="display: none;">
                                                <p>Directory</p>
                                            </div>
                                            <div class="string4Content" style="display: none;">
                                                <p>Complete directory of Land Regions/Revenue, Rural and Urban Local Governments</p>
                                            </div>
                                            <h1>
                                                <strong class="string1">Local</strong><span class="typed-cursor hidden"></span><br>

                                                <strong class="string2">Government</strong><span class="typed-cursor hidden"></span><br>
                                                <strong class="string3">Directory</strong><span class="typed-cursor hidden"></span><br>
                                                <em class="string4">Complete directory of Land Regions/Revenue, Rural and Urban Local Governments</em><span class="typed-cursor transparent"></span></h1>
                                            <a href="#/#portfolioWrapper" class="btn btn-link solid hidden-sm hidden-md hidden-xs">
  Overview
  <i class="arrow-right"></i></a> </div>
                                    </div>
                                </div>

                                <div class="col-md-7 slider-wrapper hidden-md hidden-sm hidden-xs">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="scroll full-height black-bg fp-section fp-table" id="lgddashboard" style="height: 755px; background-image:url(images/background-activities.png)">

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="text-center sectionheading">Activities<br /><br /></h3>
                        </div>
                    </div>
                    <div>
                        <div class="row activities withbottommargin">


                            <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                                <!-- small box -->
                                <div class="small-box bg-state">
                                    <div class="inner">
                                        <h3 class="count">36</h3>
                                        <p>State</p>
                                    </div>
                                    <div class="icon">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/india-icon_75X75.png" style="float:right;top:20px;margin-top: 25px;">

                                    </div>
                                    <a href="#" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                                <!-- small box -->
                                <div class="small-box bg-district">
                                    <div class="inner">
                                        <h3 class="count">144</h3>
                                        <p>District</p>
                                    </div>
                                    <div class="icon"><img src="${pageContext.request.contextPath}/resource/homebody-resource/images/state-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">


                                    </div>
                                    <a href="#" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                                <!-- small box -->
                                <div class="small-box bg-subdistrict">
                                    <div class="inner">
                                        <h3 class="count">244</h3>
                                        <p>Sub-District</p>
                                    </div>
                                    <div class="icon">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/district-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive" width="75" height="75">

                                    </div>
                                    <a href="#" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>


                        </div>
                        <div class="row activities">

                            <div class="col-lg-4 col-md-4  col-sm-6 col-xs-12">
                                <!-- small box -->
                                <div class="small-box bg-village">
                                    <div class="inner">
                                        <h3 class="count">344</h3>
                                        <p>Village</p>
                                    </div>
                                    <div class="icon">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/village-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                    </div>
                                    <a href="#" target="_blank" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                                <!-- small box -->
                                <div class="small-box bg-villagepanchayat">
                                    <div class="inner">
                                        <h3 class="count">644</h3>
                                        <p>
                                            <!--Village Panchayat-->Local Government</p>
                                    </div>
                                    <div class="icon">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/village-panchayat-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                    </div>
                                    <a href="#" target="_blank" class="small-box-footer">More Info <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                                <!-- small box -->
                                <div class="small-box bg-localbodies">
                                    <div class="inner">
                                        <h3 class="count">424</h3>
                                        <p>Local Bodies</p>
                                    </div>
                                    <div class="icon">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/local-bodies-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                    </div>
                                    <a href="#" target="_blank" title="National Panchayat Portal" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>

                        </div>
                    </div>


                </div>
            </section>


            <section id="companyinformation" class="scroll white-bg fp-section fp-table" style="height: 755px;  background-color: #f3f1f2;background-image:url(images/background-activities.png)">
                <div id="skilled-slider" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner" role="listbox">
                        <div class="item  active">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                        <div class="img-wrapper">
                                            <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/panchayatlogo.png" alt="Panchayati Raj">
                                        </div>
                                        <h3><span>About</span>
                                            <p><strong>Local Government Directory</strong></p>
                                        </h3>
                                        <p class="text-justify">Introduction Local Government Directory (LGD) is one of the applications developed as part of <span>Panchayat Enterprise Suite (PES)</span> under <span>e-Panchayat Mission Mode project (MMP)</span> (<a href="http://epanchayat.gov.in" alt="E-panchayat">http://epanchayat.gov.in</a>). LGD aims to keep all information about the structure of Local Governments and revenue entities online. Main objective of LGD is to maintain up-to-date list of revenue entities (districts/subdistricts/villages), Local Government Bodies (Panchayats, Municipalities and traditional bodies) and their wards, organizational structure of Central, State and Local Governments, reporting hierarchy within the government organizations and parliamentary and assembly constituencies and their relationship with one another. </p>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-4 col-md-6">
                                        <!-- <div class="img-wrapper">
                               <span style="color:#eb6e04"> <i class="fa fa-university  fa-3x" aria-hidden="true"></i></span>
                            </div>-->
                                        <h3>
                                            <strong>Useful for:</strong>
                                        </h3>
                                        <ul style="list-style:none;color:#fff;font-size: 17px;    line-height: 28px;">
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Panchayati Raj Departments</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Urban Development Departments</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Revenue Departments</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> State Election Commissions</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> Registered Software Applications</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> Citizens</li>
                                        </ul>

                                    </div>
                                    <div class="col-lg-8 col-md-6">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/usefulfor1.png" style="float:right">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-4 col-md-6">
                                        <!--<div class="img-wrapper">
                               <img src="images/skilled.png" alt="skilled">

                            </div>-->
                                        <h3>
                                            <strong>
                                    Key Features:
                                </strong>
                                        </h3>
                                        <ul style="list-style:none;color:#fff;font-size: 17px;    line-height: 28px;">
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> Unique Code to Land/Region</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> Enable Interoperability</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> Notification generated online</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> SMS/Email notifications</li>
                                            <li><i class="fa fa-check-circle" aria-hidden="true"></i> GIS based boundary</li>
                                        </ul>
                                    </div>
                                    <div class="col-lg-8 col-md-6">
                                        <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/keyfeatures.gif" style="float:right">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Indicators -->
                        <div class="text-center">
                            <ol class="carousel-indicators">
                                <li data-target="#skilled-slider" data-slide-to="0" class=""></li>
                                <li data-target="#skilled-slider" data-slide-to="1" class="active"></li>
                                <li data-target="#skilled-slider" data-slide-to="2" class=""></li>
                            </ol>
                        </div>
                    </div>

                </div>

            </section>

            <section class="lgdfeatures scroll fp-section fp-table" id="lgdfeatures" style="height: 755px; background-image:url(images/background-activities.png)">
                <div class="fp-tableCell" style="height: 755px;">
                    <div class="container-fluid">
                        <div class="row">
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block pinkfont">
                                            <i class="fa fa-folder-open fa-4x" aria-hidden="true"></i>
                                            <!--<img src="images/development-icon.png" alt="Supporting Documents">-->
                                            <h4>
                                                <a href="#/supportingdocuments">
Supporting Documents                                        </a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                            <li><a id="linkSupDocCbtonline" param="cbt" paramLink="CBT/LGD Combined CBT Ver 2.0.html" paramFName="CBT(Play Online)"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> CBT (Play Offline)</a></li>
                                            <li><a id="linkSupDocCbtoffline" param="cbt" paramLink="cbt.htm?docType=cbtplaypath" paramFName='CBT(Play Offline)'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> CBT (Play Online)</a></li>
                                            <li><a id="linkSupDocPresentaion" param="presentation" paramLink="cbt.htm?docType=presentationfilepath" paramFName='Presentation'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Presentation</a></li>
                                            <li><a id="linkSupDocBrouchre" param="brochure" paramLink="cbt.htm?docType=Brochure" paramFName='Brochure'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Brochure</a></li>
                                            <li><a id="linkSupDocUsermanual" param="usermanual" paramLink="cbt.htm?docType=UserManualfilepath" paramFName='User Manual'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> User Manual</a></li>
                                            <li><a id="linkSupDocRegister" param="register" paramLink="cbt.htm?docType=DataCollectionRegisterfilepath" paramFName='Data Registers'><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Data Register</a></li>
                                        </ul>

                                        <!-- <a href="#" class="btn btn-link solid btn-small">More Info...<i class="arrow-right sm"></i></a>-->

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container ">
                                    <div class="translated-block">
                                        <div class="icon-block center-block">
                                            <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/training_details.png" alt="Training Details">
                                            <h4>
                                                <a href="#/trainingdetails">TRAINING</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                            <li> <a href="#/lgdfeatures/tranining-details"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Training Details</a></li>
                                        </ul>
                                        <!-- <a href="#" class="btn btn-link solid btn-small">
								  More Info
								  <i class="arrow-right sm"></i>
								</a>-->

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block">
                                            <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/citizen.png" alt="Citizen">
                                            <h4>
                                                <a href="#/lgdfeatures/citizen">Citizen</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                            <li><a href="#/lgdfeatures/register-here-for-e-mail-sms-notifications"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Register here for E-mail/SMS Notifications </a></li>
                                        </ul>

                                        <!-- <a href="#" class="btn btn-link solid btn-small">More Info<i class="arrow-right sm"></i></a>-->

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block greenfont">
                                            <i class="fa fa-wpforms fa-4x" aria-hidden="true"></i>
                                            <h4>
                                                <a href="#/lgdfeautes/reports">REPORTS</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                            <li> <a href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> <spring:message htmlEscape="true" code="Label.VIEWSTATE"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWDIST"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWSUBDIST"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWBLOCK"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="globalviewblockwiseVillageandUlbforcitizen.do?<csrf:token uri='globalviewblockwiseVillageandUlbforcitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"></spring:message></a></li>
                                       		<li> <a class="hyperlink" href="viewWard.do?<csrf:token uri='viewWard.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWWARD"></spring:message></a></li>
                                            <li> <a class="hyperlink" class="hyperlink" href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTOFLB"></spring:message></a></li>
                                            <li> <a class="hyperlink" href="rptConsolidateforRuralLB.do?<csrf:token uri='rptConsolidateforRuralLB.do'/>">
                                            	<i class="fa fa-arrow-circle-right" aria-hidden="true"></i><spring:message htmlEscape="true" code="Label.ConsolidatedReportForRuralLB"></spring:message></a></li>
                                        </ul>

                                        <a href="#" class="btn btn-link solid btn-small">More Reports<i class="arrow-right sm"></i></a>

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block yellowfont">
                                            <i class="fa fa-question-circle fa-4x" aria-hidden="true"></i>
                                            <h4>
                                                <a href="#/services/faq" onclick="showFAQ();">
												FAQs</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <ul>
                                            <li> <a href="#/lgdfeatures/faqs">If you have any Queries to be answered by the PES Technical Team</a></li>
                                        </ul>

                                        <a href="#" class="btn btn-link solid btn-small">Click here<i class="arrow-right sm"></i></a>

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block darkpinkfont">
                                            <i class="fa fa-download fa-4x" aria-hidden="true"></i>
                                            <h4>
                                                <a href="#/lgdfeatures/directory">Directory</a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="service-components">
                                        <!-- <ul>
											<li><a href="#">#</a></li>
                                </ul>-->

                                        <a href="#" class="btn btn-link solid btn-small">Click to download... <i class="arrow-right sm"></i></a>

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3  square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block">
                                            <i class="fa fa-android fa-4x androidcolor" aria-hidden="true"></i>
                                            <h4>
                                                <a href="#/lgd-android-app-link">LGD Android</a>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="service-components">
                                        <!-- <ul>
											<li><a href="#">#</a></li>
                                </ul>-->
                                        <a href="#" class="btn btn-link solid btn-small">Click to download<i class="arrow-right sm"></i></a>

                                    </div>
                                </div>
                            </section>
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block bluefont">
                                            <i class="fa fa-bar-chart fa-4x" aria-hidden="true"></i>
                                            <h4>
                                                <a href="#/lgdfeatures/report-on-updation-status">REPORT ON UPDATION STATUS</a>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="service-components">
                                        <!-- <ul>
											<li><a href="#">#</a></li>
                                </ul>-->
                                        <a href="#" class="btn btn-link solid btn-small">Click to view...<i class="arrow-right sm"></i></a>

                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>

                </div>
            </section>
            
            <div id="cbtdoc-model" class="modal fade" role="dialog" >
			    <div class="modal-dialog">
			
			        <!-- Modal content-->
			        <div class="modal-content">
			            <iframe id="myIframe" width="600" height="460"></iframe>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
			            </div>
			        </div>
			
			    </div>
			</div>
			
			 <div id="faq-model" class="modal fade" role="dialog"  >
			    <div class="modal-dialog">
			
					
			        <!-- Modal content-->
			        <div class="modal-content">
			        
			        	<div class="modal-content">
							<h4 class="modal-title"><c:out value="FAQ" escapeXml="true"></c:out></h4>
  						</div>
  						
  					   <div id="dynamicFAQStructure"></div>
			    			
				    		
							
					        	
			            
			            <div class="modal-footer">
			                <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
			            </div>
			        </div>
			
			    </div>
			</div>
			<style>
				.faq-section input{
    position: absolute;
    z-index: 8;
    cursor: pointer;
    opacity: 0;         
    display: none\9; /* IE8 and below */
    margin: 0;          
    width: 87%;
    height: 100px;
}

/*Show only the clipped intro */
.faq-section label+p{
    display: block; 
    color: #999;
    font-size: 1em;
    font-family:inherit;
    transition: all .15s ease-out;
    margin-left:5px;
    height:60px;
    /* Clipping text */
    text-overflow: ellipsis;
    /* white-space: nowrap; */
    overflow: hidden;                       
}

/*If the checkbox is checked, show all paragraphs*/
.faq-section input[type=checkbox]:checked~p{
    display: block;
    color: #444;
    font-size: 1em;
    /* restore clipping defaults */
    text-overflow: clip; 
    /* white-space: normal; */
    overflow: visible;  
}

/*Style the label*/
.faq-section label{
    font-size: 1.2em;
    background: #eee;
    display: block;
    position: relative;
    height: 30px;
    padding: 7px 10px;
    font-weight: bold;
    border: 1px solid #ddd;
    border-left: 0px solid #888;
    text-shadow: 0 1px 0 rgba(255,255,255,.5);
    transition: all .15s ease-out;          
}

/*Remove text selection when toggle-ing*/
.faq-section label::selection{
    background: none;
}

.faq-section label:hover{
    background: #f5f5f5;
}

/*If the checkbox is checked, style the label accordingly*/
.faq-section input[type=checkbox]:checked~label{
    border-color: #ff7f50;          
    background: #f5deb4;
    background-image: linear-gradient(to bottom, #fff, #f5deb4);
    box-shadow: 0 0 1px rgba(0,0,0,.4);             
}

/*Label's arrow - default state */
.faq-section label::before{
    content: '';
    position: absolute;
    right: 4px;
    top: 50%;
    margin-top: -6px;
    border: 6px solid transparent;
    border-left-color: inherit; 
}

/*Update the right arrow*/
.faq-section input[type=checkbox]:checked~label::before{
    border: 6px solid transparent;
    border-top-color: inherit;
    margin-top: -3px;
    right: 10px;    
}

			</style>
           
            <script>
            
            function showFAQ() {
        		
        				lgdDwrInitialService.getAvailableFAQs(1,{
        					callback : function(data) {
        						
        						
        						
        						 $("#dynamicFAQStructure").empty();
        					 	var divFAQTemplate = $("#dynamicFAQStructure");
        						 
        						 
        						 var pannelGroupDiv = $("<DIV/>");
        						 pannelGroupDiv.attr("class", "panel panel-default");
        						 pannelGroupDiv.attr("role", "tablist");
        						 pannelGroupDiv.attr("aria-multiselectable", "true");
        						 
        						 
        						 jQuery.each(data,function(index,obj) {
        						 
        						
	        						var pannelContentDiv = $("<DIV/>");
	        						pannelContentDiv.attr("class", "panel panel-default");
	        						
	        						
	        						var questionDiv = $("<DIV/>");
	        						questionDiv.attr("id", ("heading"+index));
	        						questionDiv.attr("class", "panel-heading");
	        						questionDiv.attr("role", "tab");
	        						
	        						var questionHead = $("<H4/>");
	        						questionHead.attr("class", "panel-title");
	        						
	        						var questionAnchor = $("<A/>");
	        						questionAnchor.attr("role", "tab");
	        						questionAnchor.attr("data-toggle", "collapse");
	        						questionAnchor.attr("data-parent", "#accordion");
	        						questionAnchor.attr("href", ("#collapse"+index));
	        						questionAnchor.attr("aria-expanded", "true");
	        						questionAnchor.attr("aria-controls", ("collapse"+index));
	        						
	        						var questionI = $("<I/>");
	        						questionI.attr("class", "more-less glyphicon glyphicon-plus");
	        						
	        						var qstn = obj.faqQuestionText.replace("<p>","").replace("</p>","");
	        						var qstnLbl = $("<label />");
	        						qstnLbl.html(qstn);
	    							
	    							$(questionI).append(qstnLbl);
	    							$(questionAnchor).append(questionI);
	    							$(questionHead).append(questionAnchor);
	    							$(questionDiv).append(questionHead);
	    							
	    							var ansDiv = $("<DIV/>");
	    							ansDiv.attr("id", ("collapse"+index));
	    							ansDiv.attr("class", "panel-collapse collapse");
	    							ansDiv.attr("role", "tabpanel");
	    							ansDiv.attr("aria-labelledby", ("heading"+index));
	        						
	    							var ansPannel = $("<DIV/>");
	    							ansPannel.attr("class", "panel-body");
	    							
	    							var ansLbl = $("<label />");
	    							ansLbl.html(obj.faqAnswerText);
	    							
	    							$(ansPannel).append(ansLbl);
	    							$(ansDiv).append(ansPannel);
	    							
	    							$(pannelContentDiv).append(questionDiv);
	    							$(pannelContentDiv).append(ansDiv);
	    							
	    							$(pannelGroupDiv).append(pannelContentDiv);
	    							
        						 });
        						 
        						
        						 
        						
        						 $(divFAQTemplate).append(pannelGroupDiv);
        						 
        												
        						
        						
        						
        						},
        						errorHandler : function(
        								errorString) {
        							alert("EXCEPTION : "
        									+ errorString);
        						},
        						async : true,

        					});
        				
        				$('#faq-model').modal('show')	
        	}


        	$("a[id^='linkSupDoc']").bind({
        			click : function() {
        							var link = $(this).attr( 'paramLink');
        							var filename = $(this).attr('paramFName');
        							var propKey = $(this).attr('param');
        							$("#myIframe").contents().find("body").html('');
        							$('#myIframe').attr('src',"supportdownloaddoc.do?<csrf:token uri='supportdownloaddoc.do'/>&link="
													+ link+ "&filename="+ filename+ "&propkey="+ propKey);
        							$('#cbtdoc-model').modal('show')	
        						}
        						
        		});
        	
            </script> --%>