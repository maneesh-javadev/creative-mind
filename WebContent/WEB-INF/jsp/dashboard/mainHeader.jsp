   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
    <header class="main-header">
            <!-- Logo -->
           
            
            <a href="#" class="logo">
             <c:if test="${isDashBoradCenterorState.charAt(0) ne 'C'.charAt(0) }">
                <!-- mini logo for sidebar mini 50x50 pixels -->
                <span class="logo-mini"><b>Menu</b></span>
                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>Menu</b></span>
                </c:if>
            </a>
            
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                 <c:if test="${isDashBoradCenterorState.charAt(0) ne 'C'.charAt(0) }">
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>
				<a href="home.htm?<csrf:token uri='home.htm'/>"  class="home_bttn">
                    <i class="fa fa-home icon-3x" aria-hidden="true"></i>&nbsp; Home
                </a>
                
                <a href="getReportingIsses.htm?<csrf:token uri='getReportingIsses.htm'/>" class="home_bttn">Reported Issue</a><br>
                </c:if>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                    <c:if test="${isDashBoradCenterorState.charAt(0) ne 'C'.charAt(0) }">
                    <li class="button"><a href="#" class="btn " data-toggle="tooltip" data-placement="bottom" title="Other Applications"><i class="fa  fa-th" aria-hidden="true"></i></a></li>
                    </c:if>
						<!-- <li><a href="#" class="btn ">A-</a></li>
						<li><a href="#" class="btn ">A</a></li>
						<li><a href="#" class="btn ">A+</a></li> -->
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/User_160x160.png" class="user-image" alt="User Image">
                                <span class="hidden-xs"><c:out value="${login_key_id}" escapeXml="true"></c:out></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                 <li class="user-header">
                                    <img src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/User_160x160.png" class="img-circle" alt="User Image">
                                    <p><small><c:if test="${!empty login_key_id}"> ${USERS_SELECTION.assignedUnitHierarchy}</c:if></small></p>
                                </li>

                                <!-- Menu Footer-->
                                
                                 <li class="user-footer">
                                    <div class="pull-left">

                                        <!-- Font size  -->
                                        <a href="<%=getServletContext().getInitParameter("URL")%>/switchunit.htm?<csrf:token uri='switchunit.htm'/>"  class="btn btn-default btn-flat">Switch Unit</a>

                                    </div>
                                    <div class="pull-right">
                                        <a href="<%=getServletContext().getInitParameter("URL")%>/logout.htm?<csrf:token uri='logout.htm'/>" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>
               
            </nav>
             <c:if test="${isDashBoradCenterorState.charAt(0) ne 'C'.charAt(0) }">
             <div class="launcher">			
				<div class="app-launcher hide">
				<div class="apps">
				<ul class="first-set">			  			
								<li><a href="http://lgdirectory.gov.in/demo/switchunit.htm" target="_black">
								<img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/LGD.png"  onclick="changeText()">
								<div>LGD </div></a>
								</li>
								<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/areaprofiler.png" oncilck="location.href=&quot;#&quot;/">
								<div>AreaProfiler </div></li>
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/planplus.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>PlanPlus </div></li>
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/priasoft.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>PriaSoft </div></li>
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/actionsoft.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>ActionSoft </div></li>
				  			
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/NAD.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>NAD </div></li>
					</ul>
					<a href="#" class="more">More</a>
					<ul class="second-set hide">
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/servicedelivery.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>ServiceDelivery </div></li>
				    
				  			
				  				<%-- <li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/socialaudit.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>SocialAudit </div></li> --%>
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/Training.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>Training </div></li>
				  			
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/NPP.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>NPP </div></li>
				  			
				  				<li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/PESb.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>PES </div></li>
				  			
				  				<%-- <li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/auditonline.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>AuditOnline </div></li> --%>
				  			
				  				<%-- <li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/scheme.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>SCHEME </div></li> --%>
				  			
				  				<%-- <li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/meeting.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>MeetingManagement </div></li> --%>
				  			
				  				<%-- <li><img width="100" height="70" src="${pageContext.request.contextPath}/resource/dashboard-resource/dist/img/FFC.png" oncilck="location.href=&quot;#&quot;/">	
							
								<div>FFC </div></li> --%>
				  			
				  	 </ul>
					
				</div>
			</div>
		</div>
		</c:if>
        </header>