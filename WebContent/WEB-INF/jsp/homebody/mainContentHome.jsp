           

<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<% String contextpthval = request.getContextPath(); %>
<c:set var="lbCount" value="${statewiseEntitiesCount.no_of_zps+statewiseEntitiesCount.no_of_bps+statewiseEntitiesCount.no_of_vps+statewiseEntitiesCount.no_of_tlbs+statewiseEntitiesCount.no_of_ulbs}" />
<script type="text/javascript" src="<%=contextpthval %>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src='<%=contextpthval %>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval %>/dwr/util.js'> </script> 
<script type='text/javascript' src='<%=contextpthval %>/js/mainContentHome.js'> </script>
<style>
.pmlink
{
margin-bottom: 0px;
}

.header
{
font-size: 16px;
}
.ex1 {
  padding-bottom: 3px;
}
</style>

 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" style="width:950px;height:500px">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Quick Help</h4>
        </div>
        <div class="modal-body">
        
		<iframe id="myIframe" width="920" height="550"></iframe>
         
         
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
<%-- <link rel="stylesheet" href="<%=contextpthval %>/css/stateFreeze.css"></link> --%>
<%-- <div class="container">
  <span  style="margin-left: -62%;font: 42%;font-size: 149%;color: red;">Attention Please</span>
  <div class="panel panel-default">
   <div class="panel-body" style="background-color: #F3ECEE"><b>Important Note:</b> <br>In view of the DISHA week which begins from 25-Jun-2018, the district nodal officers are requested to update the locational data of their respective districts latest <br>by 18-Jun-2018.<a href="#" data-toggle="modal" data-target="#myModal">Click here for VC details, user manuals and workflows</a><br><b>For any issue Please contact on</b>
   
   <table>
									<tbody>
									<tr>
									<td>Email-ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-</td>
									<td>&nbsp;&nbsp;lgdirectory@gov.in</td>
									</tr>
									<tr>
									<td>Contact Number&nbsp;-</td><td>&nbsp;&nbsp;011-24360536</td>
									</tr>
									</tbody>
									</table> 
   
   </div>
  </div>
</div> 
 
 
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" style="width: 90%">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Quick Help</h4>
        </div>
        <div class="modal-body">
        
        <ul class="nav nav-tabs">
        
		    <li><a href="" data-target="#home1" data-toggle="tab" class="nav-link small text-uppercase">Over View</a></li>
		    <li><a href=""   data-target="#userManual" data-toggle="tab" class="nav-link small text-uppercase" >User Manuals</a></li>
		    <li><a href=""   data-target="#workFlow" data-toggle="tab" class="nav-link small text-uppercase">User Workflows</a></li>
  		</ul>
        <br>
        <div id="tabsJustifiedContent" class="tab-content">
        
        
        <div id="weekLetter" class="tab-pane fade active in">
        <a target="_blank" href="userManualFiles.do?fileName=DISHAWeekLettterfromSecretaryRuralDevelopment">DISHA Week Letter from Secretary Rural Development</a><br>
        </div>
        
        <div id="home1" class="tab-pane fade">
        	<img src="<%=contextpthval %>/resource/homebody-resource/disha/flow.jpg" class="img-responsive">
        </div> 
        
        <div  id="vcDetails" class="tab-pane fade container">
		  <div id="myCarousel" class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		      <li data-target="#myCarousel" data-slide-to="1"></li>
		      <li data-target="#myCarousel" data-slide-to="2"></li>
		      <li data-target="#myCarousel" data-slide-to="3"></li>
		      <li data-target="#myCarousel" data-slide-to="4"></li>
		      <li data-target="#myCarousel" data-slide-to="5"></li>
		      <li data-target="#myCarousel" data-slide-to="6"></li>
		    </ol>
		
		    <!-- Wrapper for slides -->
		    <div class="carousel-inner">
		
		      <div class="item active">
		      <h4>VC-ID : 230676</h4> <h4>DATE : 4-06-2018</h4>
		      
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails4.jpg" >
		      </div>
		
		      <div class="item">
		      	<h4>VC-ID : 230677</h4> <h4>DATE : 5-06-2018</h4>
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails6.jpg" >
		      </div>
		    
		      <div class="item">
		      <h4>VC-ID : 230678</h4> <h4>DATE : 6-06-2018</h4>
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails3.jpg" >
		      </div>
		      
		      <div class="item">
		      <h4>VC-ID : 230679</h4> <h4>DATE : 7-06-2018</h4>
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails1.jpg" >
		      </div>
		      
		      <div class="item">
		      <h4>VC-ID : 230680</h4> <h4>DATE : 18-06-2018</h4>
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails7.jpg" >
		      </div>
		      
		      <div class="item">
		      <h4>VC-ID : 230682</h4> <h4>DATE : 11-06-2018</h4>
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails2.jpg">
		      </div>
		      
		      <div class="item">
		      <h4>VC-ID : 230683</h4> <h4>DATE : 12-06-2018</h4>
		        <img  src="<%=contextpthval %>/resource/homebody-resource/disha/vcDetails5.jpg">
		      </div>
		  
		    </div>
		
		    <!-- Left and right controls -->
		    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left" style="color: black"></span>
		    </a>
		    <a class="right carousel-control" href="#myCarousel" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right" style="color: black"></span>
		    </a>
		  </div>
	</div>
        
        
        
        
        
        <div id="userManual" class="tab-pane fade">
        <a target="_blank" href="userManualFiles.do?fileName=ManualforLGDDistrictBlockUser">Manual for LGD District Block User</a><br>
        <a target="_blank" href="userManualFiles.do?fileName=ManualforLGDDistrictRevenueUser">Manual for LGD District Revenue User</a><br>
        <a target="_blank" href="userManualFiles.do?fileName=ManualforLGDDistrictUrbanUser">Manual for LGD District Urban User</a><br>
        <a target="_blank" href="userManualFiles.do?fileName=ManualForLGDPRIUser">Manual For LGD PRI User</a><br>
        </div> 
        
        <div id="workFlow" class="tab-pane fade">
        
        <ul class="nav nav-tabs">
		    <li><a href="" data-target="#flowChartForDistrictBlockUser" data-toggle="tab" class="nav-link small text-uppercase">District Block User</a></li>
		    <li><a href="" data-target="#flowChartForDistrictRevenueuser" data-toggle="tab" class="nav-link small text-uppercase">District Revenue User</a></li>
		    <li><a href="" data-target="#flowChartForDistrictRuralUser" data-toggle="tab" class="nav-link small text-uppercase" >District Rural User</a></li>
		    <li><a href="" data-target="#flowChartForDistrictUrbanUser" data-toggle="tab" class="nav-link small text-uppercase">District Urban User</a></li>
  		</ul>
  		
  		
  		<div id="tabsJustifiedContent1" class="tab-content">
  		<div id="flowChartForDistrictBlockUser" class="tab-pane fade active in">
        	<img src="<%=contextpthval %>/resource/homebody-resource/disha/FlowchartforDistrictBlockUser.jpg" class="img-responsive">
        </div> 
        <div id="flowChartForDistrictRevenueuser" class="tab-pane fade">
        	<img src="<%=contextpthval %>/resource/homebody-resource/disha/FlowchartforDistrictRevenueUser.jpg" class="img-responsive">
        </div> 
        <div id="flowChartForDistrictRuralUser" class="tab-pane fade">
        	<img src="<%=contextpthval %>/resource/homebody-resource/disha/FlowchartforDistrictRuralUser.jpg" class="img-responsive">
        </div> 
        <div id="flowChartForDistrictUrbanUser" class="tab-pane fade">
        	<img src="<%=contextpthval %>/resource/homebody-resource/disha/FlowchartforDistrictUrbanUser.jpg" class="img-responsive">
        </div> 
  		</div>
  		
        </div> 
         </div>
         
         
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div> --%>
 
 
 
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
                             <a href="#" onclick="showoverview();" class="btn btn-link solid hidden-sm hidden-md hidden-xs">Overview<i class="arrow-right"></i></a>
 		</div>
   </div>
    <div class="col-md-7">
   	<img src="<%=contextpthval %>/resource/homebody-resource/images/lgdtreeview_animated-1500X878.gif" class="img-responsive"> 
   	
   	
    </div>
 </section>
   
      <section class="scroll full-height black-bg fp-section fp-table" id="lgddashboard" style="height: 487px; background-image: url(&quot;images/background-activities.png&quot;);" data-anchor="dashboard"><div class="fp-tableCell" style="height:487px;">

			
			
               <div class="container-fluid">
               <div class="row">
               <div class="col-xs-12 text-center">
               	<div class="pmlink">
				<a id="dedicatedVideoLink" param="lgdLaunchVideo" paramLink="CBT/LGD Combined CBT Ver 2.0.html?docType=dedicatingVideo" paramFName='PM Dedicating LGD' >
	  			LGD is dedicated to the nation by the Hon'ble Prime Minister of India
	  			</a>
			</div>
               </div>
               </div>
                   <div class="row">
                       <div class="col-xs-12">
                           <h3 class="text-center sectionheading">Entities<br><br></h3>
                       </div>
                   </div>
                 
                       <div class="row activities withbottommargin">


                           <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-state">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${statewiseEntitiesCount.no_of_states}"/> </h3>
                                       <p>States/UTs</p>
                                   </div>
                                   <div class="icon">
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/india-icon_75X75.png" style="float:right;top:20px;margin-top: 25px;">

                                   </div>
                                   <a href="globalviewstateforcitizen.do?<csrf:token uri='globalviewstateforcitizen.do'/>" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>
                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-district">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${statewiseEntitiesCount.no_of_districts}"/></h3>
                                       <p>Districts</p>
                                   </div>
                                   <div class="icon"><img src="<%=contextpthval %>/resource/homebody-resource/images/state-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">


                                   </div>
                                   <a href="globalviewdistrictforcitizen.do?<csrf:token uri='globalviewdistrictforcitizen.do'/>" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>

                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-subdistrict">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${statewiseEntitiesCount.no_of_subdistricts}"/></h3>
                                       <p>Sub-Districts</p>
                                   </div>
                                   <div class="icon">
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/district-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive" width="75" height="75">

                                   </div>
                                   <a href="globalviewsubdistrictforcitizen.do?<csrf:token uri='globalviewsubdistrictforcitizen.do'/>" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>


                       </div>
                       <div class="row activities">

                           <div class="col-lg-4 col-md-4  col-sm-6 col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-villagepanchayat">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${statewiseEntitiesCount.no_of_blocks}"/></h3>
                                       <p>
                                           <!--Village Panchayat-->Blocks</p>
                                   </div>
                                   <div class="icon">
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/village-panchayat-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                   </div>
                                   <a href="globalviewBlockforcitizen.do?<csrf:token uri='globalviewBlockforcitizen.do'/>" class="small-box-footer">More Info <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                              
                           </div>
                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                                <!-- small box -->
                               <div class="small-box bg-village">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${statewiseEntitiesCount.no_of_villages}"/></h3>
                                       <p>Villages</p>
                                   </div>
                                   <div class="icon">
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/village-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                   </div>
                                   <a href="globalviewvillageforcitizen.do?<csrf:token uri='viewvillageforcitizen.do'/>" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>
                           <div class="col-lg-4 col-md-4 col-sm-6  col-xs-12">
                               <!-- small box -->
                               <div class="small-box bg-localbodies">
                                   <div class="inner">
                                       <h3 class="count"><c:out value="${lbCount}"/></h3>
                                       <p>Local Bodies</p>
                                   </div>
                                   <div class="icon">
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/local-bodies-icon_75X75.png" style="float:right;margin-top: 25px;" class="img-responsive">

                                   </div>
                                   <a href="globalViewLocalBodyForCitizen.do?<csrf:token uri='globalViewLocalBodyForCitizen.do'/>" title="National Panchayat Portal" class="small-box-footer">More Info  <i class="fa fa-arrow-circle-right"></i></a>
                               </div>
                           </div>

                       </div>
                   
                   <div class="row">
                   	<div class="col-lg-1 hidden-sm hidden-xs">&nbsp;</div>
                   	<div class="col-lg-10 col-md-8 col-sm-12 col-xs-12"><br /><br />
                           <%@include file="../reports/statewiseEntitiesCount.jsp" %>
                           </div>
                   	<div class="col-lg-1 hidden-sm hidden-xs">&nbsp;</div>
                   </div>
				</div>
				 
           </div>
 </section>
           
   <!-- ///////////////////////////////////////////////Activites //////////////////////////////////////////////////////////////////////-->            
           
<section>
               <div id="skilled-slider" class="carousel slide" data-ride="carousel">
                <br /> <br />
                   <div class="carousel-inner" role="listbox">
                       <div class="item">
                           <div class="container">
                               <div class="row">
                                   <div class="col-lg-12 col-md-12">
                                  
                                       <div class="img-wrapper">
                                           <img src="<%=contextpthval %>/resource/homebody-resource/images/panchayatlogo.png" alt="Panchayati Raj" class="img-responsive">
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
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/usefulfor1.png" style="float:right" class="img-responsive">

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
                                       <img src="<%=contextpthval %>/resource/homebody-resource/images/keyfeatures.gif" style="float:right" class="img-responsive">

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
           
           <%-- <hr >
   <section class="lgdwebservice scroll fp-section fp-table active" id="webservice" style="height: 200px; background-image: url(&quot;images/background-activities.png&quot;);" data-anchor="documents-and-downloads"><div class="fp-tableCell" style="height:200px;">
   <div class="row">
   <div class="col-md-12 text-center">
   <a href="wsUserRegistration.do?<csrf:token uri='wsUserRegistration.do'/>" title="Register here to consume Web Services" target="_parent"><img src="<%=contextpthval %>/resource/homebody-resource/images/cloud-based-application.png" alt="Register here to consume Web Services" class="img-responsive center-block"></a>
   </div>
   </div></div>
   </section>        
       <hr /> --%>
           
           
 <section class="lgdfeatures scroll fp-section fp-table active" id="lgdfeatures" style="height: 487px; " data-anchor="documents-and-downloads"><div class="fp-tableCell" style="height:487px;">
               <div class="fp-tableCell" style="height: 755px;">
                   <div class="container-fluid">
                       <div class="row">
                       
                        <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block">
                                           <a href="#" class="yellowfont" onclick="viewAnnouncementInPopup()">
                                           <i class="fa fa-bullhorn fa-4x fa-blink " aria-hidden="true"></i></a>
                                          
                                            <h4>
                                                <a href="#" onclick="viewAnnouncementInPopup()">Announcements</a>
                                            </h4>
                                        </div>
                                    </div>
										<!-- <div class="service-components">
										<ul>
											<li> <a href="https://lgdirectory.gov.in/#/lgdfeatures/announcements">Get Important Announcements </a></li>
										</ul>
				
										<a href="#" 
										class="btn btn-link solid btn-small">Click here<i class="arrow-right sm"></i></a>
										
										</div> -->
                                   
                               </div>
                           </section>
                           
                             <section class="col-sm-3 square-section">
                               <div class="content-container ">
                                   <div class="translated-block">
                                       <div class="icon-block center-block">
                                          <a href="#" onclick="viewSoftwareUpdateInPopup()">
                                           <img src="<%=contextpthval %>/resource/homebody-resource/images/software-updates3.png" alt="Updates in Software"></a>
                                           <h4>
                                                <a href="#" onclick="viewSoftwareUpdateInPopup()">Software Updates</a>

                                           </h4>
                                       </div>
                                   </div>
                               </div>
                           </section>
                           
                            <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block">
                                           <img src="<%=contextpthval %>/resource/homebody-resource/images/citizen.png" alt="Citizen">
                                           <h4>
                                               <a href="#/lgdfeatures/citizen">Citizen</a><br>
                                           </h4>
                                          
                                       </div>
                                   </div>
                                   

                                   <div class="service-components">
                                        <div class="header pmlink">
                                            To Consume Web Services, Report Issue and E-mail/SMS Notification
                                           
                                        </div>
                                       
                                       <ul>
                                          <li style="color:red;"><a href="userRegistratinService.do?<csrf:token uri='userRegistratinService.do'/>">
	            										Register Here   >
	                                 		</a></li>
	                                 		
	                                 		<li style="color:red;"><a href="userLoginForm.do?<csrf:token uri='userLoginForm.do'/>">
	            										Login Here   >
 	                                 		</a></li>
 	                                 		
 	                                 		
                                           
                                       </ul>
                                    </div>
                                </div>
                            </section>
                       
                           
                          
                            <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block">

                                          <a href="#" onclick="showReports();" > 
                                           <img src="<%=contextpthval %>/resource/homebody-resource/images/reports.png" alt="Reports">
										   </a>

                                          

                                            <h4>
                                                <a href="#/lgdfeautes/reports" onclick="showReports();">REPORTS</a>
                                            </h4>
                                        </div>
                                    </div>
								</div>
								<div class="service-components">
       							</div>
							</section>
                           <%--  <section class="col-sm-3 square-section">
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
                            </section> --%>
                            
                            <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block pinkfont">
                                           <a href="#/supportingdocuments" onclick="showCBT()">
                                           <i class="fa fa-folder-open fa-4x" aria-hidden="true"></i></a>
                                          
                                            <h4>
                                                <a href="#/supportingdocuments" onclick="showCBT()">Supporting Documents </a>
                                            </h4>
                                        </div>
                                    </div>

                                   
                               </div>
                           </section>
                           
                             <section class="col-sm-3 square-section">
                                <div class="content-container">
                                    <div class="translated-block">
                                        <div class="icon-block darkpinkfont">
                                           <a href="downloadDirectory.do?<csrf:token uri='downloadDirectory.do'/>" class="icon-block darkpinkfont"> <i class="fa fa-download fa-4x" aria-hidden="true"></i></a>
                                            <h4>
                                                <a href="downloadDirectory.do?<csrf:token uri='downloadDirectory.do'/>">Download Directory</a>
                                            </h4>
                                        </div>
                                    </div>

                                  
                               </div>
                           </section>
                           
                           <section class="col-sm-3 square-section">
                               <div class="content-container ">
                                   <div class="translated-block">
                                       <div class="icon-block center-block">
                                           <img src="<%=contextpthval %>/resource/homebody-resource/images/training_details.png" alt="Training Details">
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
                            
                          
                        <!--    <section class="col-sm-3  square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block">
                                           <a href="#" onclick="showAnoride()" class="icon-block"><i class="fa fa-android fa-4x androidcolor" aria-hidden="true"></i></a>
                                           <h4>
                                               <a href="#" onclick="showAnoride()">LGD Android</a>
                                           </h4>
                                       </div>
                                   </div>
                                  
                               </div>
                           </section> -->
                           <section class="col-sm-3 square-section">
                               <div class="content-container">
                                   <div class="translated-block">
                                       <div class="icon-block bluefont">
                                           <a href="stateFreezeReport.do?<csrf:token uri='stateFreezeReport.do'/>" class="icon-block bluefont"> <i class="fa fa-bar-chart fa-4x" aria-hidden="true"></i></a>
                                           <h4>
                                             <a href="stateFreezeReport.do?<csrf:token uri='stateFreezeReport.do'/>">REPORT ON UPDATION STATUS</a>
                                           </h4>
                                       </div>
                                   </div>
                                   
                               </div>
                           </section>
                       </div>
                   </div>

               </div>
           </div>
		</section>
		  <section>
		  <div class="container-fluid">
		  <div class="row">
                <ul class="top-foot">
                    <li class="topfootLi"><a  href="citizenFeedback.do?<csrf:token uri='citizenFeedback.do'/>">Feedback </a> </li>
                    <li class="hidden-xs hidden-sm">|</li>
                    <li class="topfootLi"><a href="#" onclick="showFAQ();">FAQs </a>  </li>
                    <li class="hidden-xs hidden-sm">|</li>
                    <li class="topfootLi"><a  href="contactUs.do?<csrf:token uri='contactUs.do'/>">Contact Us </a>  </li>
                    <li class="hidden-xs hidden-sm">|</li>
                    <li class="topfootLi"><a  href="siteMap.do?<csrf:token uri='siteMap.do'/>">Site Map </a> </li>
                    <li class="hidden-xs hidden-sm">|</li>
                    <li class="topfootLi"><a  href="privacyPolicy.do?<csrf:token uri='privacyPolicy.do'/>">Privacy Policy   </a></li>
                    <li class="hidden-xs hidden-sm">|</li>
                    <li class="topfootLi"><a href="copyRightPolicy.do?<csrf:token uri='copyRightPolicy.do'/>">Copyright Policy </a>  </li>
                    <li class="hidden-xs hidden-sm">|</li>
                    <li class="topfootLi"><a  href="termsconditions.do?<csrf:token uri='termsconditions.do'/>">Terms And Conditions</a></li>
                </ul></div></div>
            </section>
		
		
		<div id="cbtdoc-model" class="modal fade" role="dialog" >
			    <div class="modal-dialog">
			
			        <!-- Modal content-->
			        <div class="modal-content">
			             <div id="dynamicDocStructure"></div>
			      </div> 
			
			    </div>
			</div>
            
            <!-- Added by Ashish on 30/8 for Overview -->
            
 		<div id="overview-model" class="modal fade" role="dialog"  >
			    <div class="modal-dialog modal-lg">
					<div class="modal-content">
				      	<div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4 class="modal-title"><c:out value="Overview" escapeXml="true"></c:out></h4>
				      </div>
				      <div class="modal-body pannel_box">
				       <p>
				       
				       Primary objective of Local Government directory is to facilitate State Departments to update the directory with newly formed panchayats/local bodies,re-organization in panchayats, conversion from Rural to Urban area etc and provide the same info in public domain. 
						Key Features of Local Government Directory: 
						Generation of unique code for each local government body - each local government body is assigned with a unique code.<br />
						Maintenance of local government bodies and its mapping with constituting land region entities. For ex. gram panchayat mapping with villages.<br />
						Mandatory upload of Govt. order for each modification in the directory - to ascertain the users that the data published in LGD is authentic.<br />
						Maintenance of historical data - when modifications take place in LGD, the old values/data is archived.<br />
						Provision to maintain state specific local government setup.Compliance with Census 2011 codes.<br />
						Facility to integrate with state specific standard codes - if any state is following standard codes for state level software applications, the same code can be linked to LGD code.
				       
				       </p>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
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
            
          <%@ include file="reportSection.jsp"%>
           <%@ include file="cbtSection.jsp"%>
          <div class="modal fade" id="dialogBXSummary" tabindex="-1" role="dialog" >
					<div class="modal-dialog" style="width:950px;height:580px;">
							<div class="modal-content">
				  				<div class="modal-header">
				   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
				    			  	<h4 class="modal-title" id="dialogBXTitle">Summary Statistics</h4>
				    			  	
				  				</div>
				  				<div class="modal-body" id="dialogBXbodySummary">
				        			<!-- <iframe id="myIframe" width="910" height="480"></iframe> -->
				        			<iframe id="myIframeSummary" width="910" height="600"></iframe>
				        		</div>
								
								
								
					</div>
				</div>
			</div>  
          <div class="modal fade" id="dashdialogBX" tabindex="-1" role="dialog" >
					<div class="modal-dialog" style="width:950px;height:640px;">
							<div class="modal-content">
				  				<div class="modal-header">
				  				
				   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
				    			  	<h4 class="modal-title" id="dialogBXTitle">Freeze/Unfreeze Status</h4>
				    			  	
				  				</div>
				  				<div class="modal-body" id="dialogBXbody">
				        			<!-- <iframe id="myIframe" width="910" height="480"></iframe> -->
				        			<iframe id="dashboardFrame" width="910" height="620"></iframe>
				        		</div>
								
								<!-- <div class="modal-footer">
        							
     							 </div> -->
								
					</div>
				</div>
			</div>  
            <div class="modal fade" id="dialogBXAnnouncement" tabindex="-1" role="dialog" >
					<div class="modal-dialog announcement-modal-dialog" >
							<div class="modal-content announcement-modal-content">
				  				<div class="modal-header announcement-modal-header">
				   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
				    			  	<h4 class="modal-title announcement-modal-title" id="announcement-dialogBXTitle"> </h4>
				    			  	
				  				</div>
				  				<div class="modal-body announcement-modal-body" id="dialogBXbody">
				  				<div id="AnnouncementsdialogBXbody" style="display: none;">
				  					 <%@ include file="showAnnouncements.jsp"%> 
				  				</div>
				        		<div id="SoftwareUpdatesdialogBXbody" style="display: none;">
				  					<%@ include file="showSoftwareUpdates.jsp"%>
				  				</div>
				        		</div>
								
								
								
					</div>
				</div>
			</div>  
           <!--  <script type="text/javascript">
					try{
						
						$.ajax({
							url: "${fullPath}/lgd_entity_count.json", 
							Accept:"application/json",//depends on your api
							dataType: 'json',  
							type:'GET',
							async:false,
							
							success: function(response){  
								jQuery.each(response,function(index,obj) {
									$( '#state_entity').text(obj.no_of_states);
									$( '#district_entity').text(obj.no_of_districts);
									$( '#subdistrict_entity').text(obj.no_of_subdistricts);
									$( '#block_entity').text(obj.no_of_blocks);
									$( '#village_entity').text(obj.no_of_villages);
									$( '#localbody_entity').text(obj.no_of_zps+obj.no_of_bps+obj.no_of_vps+obj.no_of_tlbs+obj.no_of_ulbs);
									//alert(obj.state_count);
								});
								
								
								},
							error: function(e){	alert('error')} 
						}); 	
						
					
					}catch(err) {
					    alert(err.message);
					}
		</script> -->
            
            
           