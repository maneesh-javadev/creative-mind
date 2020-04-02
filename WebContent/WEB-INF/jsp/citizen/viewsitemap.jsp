<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
</head>
<body>
<section class="content">
    	<!-- Main row -->
   <div class="row">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title"><spring:message code="Label.sitemap" htmlEscape="true" text="Site map"/></h3>
            	  </div>
            	  
            	  <div class="box-header subheading">
            	  		<h3><a href="welcome.do?<csrf:token uri='"welcome.do'/>"><spring:message code="label.home" text="Home" htmlEscape="true" /></a></h3>
            	  </div>
		              <!-- /.box-header -->
		   <form:form class="form-horizontal" name="form1" id="form1" method="POST">  
			<div class="box-body" id="sitemap">
			<div class="col-lg-12">    
		    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingOne">
		                <h4 class="panel-title">
		                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="label.about.pes" text="About PES" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
		                <div class="panel-body">
		                     <a href="aboutPes.do?<csrf:token uri='aboutPes.do'/>"><spring:message code="label.about.pes" text="About PES" htmlEscape="true" /></a><br />
							 <a href="aboutLGD.do?<csrf:token uri='aboutLGD.do'/>"><spring:message code="label.about.lgd" text="About LGD" htmlEscape="true" /></a><br />
						</div>
		            </div>
		        </div>
		
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingTwo">
		                <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="label.citizen.section" text="Citizen Section" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
		                <div class="panel-body">
		                    <a target="_blank" href="globalViewLGDRegistration.do?<csrf:token uri='globalViewLGDRegistration.do'/>"><spring:message htmlEscape="true" code="Label.LINKTOLGDREG"/></a><br />
							<a href="downloadDirectory.do?<csrf:token uri='downloadDirectory.do'/>"><spring:message htmlEscape="true" code="Label.DD"/></a><br />
							<a href="globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>"><spring:message htmlEscape="true" code="Label.SEARCH"/></a><br />
						</div>
		            </div>
		        </div>
		
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingThree">
		                <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="label.supporting.document" text="Supporting Document" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		                <div class="panel-body">
			                <a href="<%=request.getContextPath()%>/media/CBT/LGD Combined CBT.html" target="_blank" rel="gb_page_center[700, 500]"/><spring:message code="label.cbt.play.online" text="CBT (Play Online)" htmlEscape="true" /> </a><br />
							<a href="downLoadCBT.do?<csrf:token uri='downLoadCBT.do'/>"><spring:message code="label.cbt.play.offline" text="CBT-(Play Offline)" htmlEscape="true" /></a><br />
							<a href="viewPresentation.do?<csrf:token uri='viewPresentation.do'/>"><spring:message code="label.presentation" text="Presentation" htmlEscape="true" /></a><br />
							<a href="viewBrochure.do?<csrf:token uri='viewBrochure.do'/>"><spring:message code="label.brochure" text="Brochure" htmlEscape="true" /></a><br />
							<a href="viewManual.do?<csrf:token uri='viewManual.do'/>"> <spring:message code="label.user.manual" text="User Manual" htmlEscape="true" /></a><br />
							<a href="dataRegister.do?<csrf:token uri='dataRegister.do'/>"> <spring:message code="label.data.register" text="Data Register" htmlEscape="true" /></a><br />				
					   </div>
		            </div>
		        </div>
		        
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingFour">
		                <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="label.useful.link" text="Useful Link" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
		                <div class="panel-body">
			                <a href="http://panchayatonline.gov.in/viewPESHome.htm"><spring:message htmlEscape="true" code="Label.panchayat.online" text="Panchayat Online"/></a><br />
							<a href="http://nic.in/"><spring:message htmlEscape="true" code="Label.nic" text="NIC"/></a><br />
						</div>
		            </div>
		        </div>
		        
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingFive">
		                <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="common.reports" text="Reports" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
		                <div class="panel-body">
			                <a href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWSTATE"/></a><br />
							<a href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWDIST"/></a><br />
							<a href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWSUBDIST"/></a><br />
							<a href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"/></a><br />
							<a href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWBLOCK"/></a><br />
							<a href="globalviewblockwiseVillageandUlbforcitizen.do?<csrf:token uri='globalviewblockwiseVillageandUlbforcitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWBLOCKWISEVILLAGESANDULB"/></a><br />
                   			<a href="viewWard.do?<csrf:token uri='viewWard.do'/>"><spring:message htmlEscape="true" code="Label.VIEWWARD"/></a><br />
							<a href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>"><spring:message htmlEscape="true" code="Label.VIEWLOCALBODY"/></a><br />
							<a href="rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>"><spring:message htmlEscape="true" code="Label.CONSOLIDATEDRPTOFLB"/></a><br />
							<a href="rptConsolidateforRuralLB.do?<csrf:token uri='rptConsolidateforRuralLB.do'/>"><spring:message htmlEscape="true" code="Label.ConsolidatedReportForRuralLB"/></a><br />
						</div>
		            </div>
		        </div>
		        
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingSix">
		                <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="label.training" text="Training" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
		                <div class="panel-body">
			                <a href="http://trainingonline.gov.in/rptConsolidateforTrainingExternal.htm?code=8543&&entity_type=O" target="_blank"><spring:message htmlEscape="true" code="Label.TRAININGSCHEDULE"/></a><br />
						</div>
		            </div>
		        </div>
		        
		        <div class="panel panel-default">
		            <div class="panel-heading" role="tab" id="headingSeven">
		                <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
		                        <i class="more-less glyphicon glyphicon-plus"></i> <spring:message code="label.helpful.setion" text="Helpful Section" htmlEscape="true" />
		                    </a>
		                </h4>
		            </div>
		            <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
		                <div class="panel-body">
			                <a href="downLoadCBT.do?<csrf:token uri='downLoadCBT.do'/>"><spring:message code="label.help.desk" text="Help Desk" htmlEscape="true" /></a><br />
							<a href="globalAddQuery.do?<csrf:token uri='globalAddQuery.do'/>"><spring:message htmlEscape="true" code="Label.post.your.query" text="Post your Query"/></a><br />
					   </div>
		            </div>
		        </div>
		    </div><!-- panel-group -->
          </div>
		</div>			    
	  </form:form>
	</div>
   </div>
  </section>
 </div>
</section>
</body>
</html>