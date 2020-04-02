   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
    <header class="main-header">
            <!-- Logo -->
            <!-- <a href="#" class="logo">
                mini logo for sidebar mini 50x50 pixels
                <span class="logo-mini"><b>Menu</b></span>
                logo for regular state and mobile devices
                <span class="logo-lg"><b>Menu</b></span>
            </a> -->
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar-switchunit navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
               <!--  <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a> -->
				<%-- <a href="home.htm?<csrf:token uri='home.htm'/>"  class="home_bttn">
                    <i class="fa fa-home icon-3x" aria-hidden="true"></i>&nbsp; Home
                </a> --%>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
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
                                        <%-- <a href="href='switchunit.htm?<csrf:token uri='switchunit.htm'/>'" class="btn btn-default btn-flat">Switch Unit</a> --%>

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
            
    
        </header>