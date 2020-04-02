  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"	prefix="csrf"%>
   
   
   
   
    <section class="wrapper section-header-wrapper">
        <div class="container-fluid container-header">
            <div class="row">
                <div class="col-lg-1 col-xs-2 "><img src="${pageContext.request.contextPath}/resource/homebody-resource/images/logo.png" alt="emblem" title="Home" class="img-responsive header-logo"></div>
                <div class="col-lg-8 col-xs-10 col-sm-4 logo">
                    <p>Local Government Directory</p>
                </div>
                 <div class="col-lg-3 col-xs-12 col-sm-6 ">
                 <ul class="nav navbar-nav navbar-right mobile-menu">
                 			<c:if test="${isHomepage eq true}">
                 			<li><a href="#" onclick="gisStatewiseentitycount()"><i class="fa fa-globe" aria-hidden="true"><c:out value=" Dashboard"/></i></a></li>
                 			<li><a href="#" onclick="showDashboard()"><i class="fa fa-lock" aria-hidden="true"><c:out value="Freeze/Unfreeze Report"/></i></a></li>
							<li><a href="globalsearchentity.do?<csrf:token uri='globalsearchentity.do'/>"><i class="fa fa-search" aria-hidden="true"></i> Search</a></li>
                             
                             <!-- <li class="dropdown">
                                    <a href="#"><i class="fa fa-font" aria-expanded="false"></i></a>
                                    <a href="#/#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><span class="caret"></span></a>
                                    <ul class="dropdown-menu lang">
                                        <li><a href="#/font-increase">A+</a></li>
                                        <li><a href="#/fontreset">A</a></li>
                                        <li><a href="#/font-decrease">A-</a></li> 

                                    </ul>
                                </li> -->
                                </c:if>
                              
                                <c:choose>
	                                <c:when test="${isHomepage eq true}">
	                                <li >
	                                 <a href="#" data-toggle="modal" data-target="#login-modal" >
	                                 	<li>
	                                 		<a href="#" onclick="viewLoginPopup();" class="login-btn">
	                                     		<span >
	            										Login
	         									</span>
	                                 		</a>
	                                 	</li>
										<!-- 
										 * This Method is Use for External User 
										 * @param loginForm
										 * @author Maneesh Kumar
										 * @since 01-10-2019 -->
										<!-- <li class="left_padding">
	                                 	<a href="#" onclick="viewExternalLoginPopup();" class="login-btn">
	                                     		<span >
	            										Login as Ladakh User
	         									</span>
	                                 		</a>
	                                 	</li>
	                                  </a>
	                                </li> -->
	                                <!-- end External User  -->
									</c:when>
									<c:otherwise>
									<li >
	                                 
	                                 	<li>
	                                 		<a href="#" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" class="login-btn">
	                                     		<span >
	            										HOME
	         									</span>
	                                 		</a>
	                                 	</li>
	                                 
	                                </li>
									</c:otherwise>
								</c:choose>
								<c:if test="${isHomepage eq true}">
									<form  action="login.do">
										<input type="text" name="username" value="Centeradmin1" />
       									<input type="submit" value="Login" name=""  />  
									</form>
                                
                                </c:if> 
								
								

                        </ul>
                 </div>   
            </div>
        </div>
         <!-- BEGIN # MODAL LOGIN -->
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" align="center">
                        <img class="img-circle" id="img_logo" src="${pageContext.request.contextPath}/resource/homebody-resource/images/User.png">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
                    </div>

                    <!-- Begin # DIV Form -->
                    <div id="div-forms">

                        <!-- Begin # Login Form -->
						<form action="#" method="post"  id="login-form" class="form-horizontal">	
							<div class="modal-body" id="popupDialogBX">
								<div class="rahul">
									<iframe id="loginPopupFrame" ></iframe>
										<!-- <a class="lightbox-close" href="#"></a> -->
								</div>

                            </div>
							<div class="modal-footer">
                                
                                <div class="login-tip">
                                    <a id="login_lost_btn">Having trouble while Login? Check out these troubleshooting tips</a>
                                </div>
                            </div>
        					
						</form>
                        <!-- End # Login Form -->

                        <!-- Begin | tips -->
                         <form id="lost-form" style="display:none;">
                            <div class="modal-body">
                                <div class="col-sm-12">
                                    <p>Kindly follow below steps for resolving login related issues:</p>
                                    <ol>
                                        <li>Kindly clear your web browser’s history and cache.</li>
                                        <li>Close your web browser.</li>
                                        <li>Open your web browser and Enter URL of LGD website in the address bar and press enter.</li>
                                        <li>Click on Login button available on the Home of LGD website.</li>
                                        <li>A login popup window will appear.</li>
                                        <li>Enter Valid User Credentials.</li>
                                        <li>In case in the Login Popup window exception is coming or nothing is visible in it then follow below steps :
                                            <ul>
                                                <li>Enter Below URL in the address bar of your web browser and press enter :<a href="http://panchayatonline.gov.in" target="_blank">http://panchayatonline.gov.in</a></li>
                                                <li>Click on the Login button available on the home page.</li>
                                                <li>A login window will appear.</li>
                                                <li>Add security exception from there using add exception button.</li>
                                                <li>Now Close your web browser window.</li>
                                                <li>Open your web browser window and now access LGD website.</li>
                                            </ul>
                                        </li>
                                    </ol>
                                </div>
                                <div class="modal-footer">
                                    <div class="login-tip">
                                        <button id="lost_login_btn" type="button" class="btn btn-info">Log In</button>
                                    </div>
                                </div>
                                </div>
                        </form>
                        <!-- End | Tips -->


                        </div>
                        <!-- End # DIV Form -->

                    </div>
                </div>
            </div>
			</div>
            <!-- END # MODAL LOGIN -->

    </section>
    <!-- ///////////////////////////////////////////////Header Section //////////////////////////////////////////////////////////////////////-->
  <Script type="text/javascript">
            var viewLoginPopup = function() {
				$('#loginPopupFrame').attr('src', "login.htm?x=nonpopup");
				$('#login-modal').modal('show')	
        	};
         </Script>
  
  
  
  
  
  <%-- 
  
  
  
  
  
  <header class="fixed mainpage-nav">
            <nav class="navbar container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mainpageMenuCollapse" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                            <a class="navbar-brand" href="#">
                                <div class="logo">
                                    <img src="${pageContext.request.contextPath}/resource/homebody-resource/images/logo/emblem-dark_35X59.png" alt="emblem" class="img-responsive center-block">
                                </div>



                            </a>
                            <ul class="nav navbar-nav hidden-sm hidden-xs">
                                <li class="active "><a href="#" class="logotextu">Local Government Directory</a></li>
                            </ul>
                            <div class="navbar-center positioned">
                                <ul class="text-center list-block text-uppercase">
                                    <li class="sitetitle">
                                        Local Government Directory
                                    </li>
                                    <li>
                                        Government of India
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="navbar-collapse collapse" id="mainpageMenuCollapse" aria-expanded="false" style="height: 11px;">

                            <ul class="nav navbar-nav navbar-right">
							<li><a href="#" ><i class="fa fa-search" aria-hidden="true"></i> Search</a></li>
                                <li class="dropdown">
                                    <a href="#"><i class="fa fa-font" aria-expanded="false"></i></a>
                                    <a href="#/#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#/font-increase">A+</a></li>
                                        <li><a href="#/fontreset">A</a></li>
                                        <li><a href="#/font-decrease">A-</a></li>

                                    </ul>
                                </li>

								<c:if test="${isHomepage eq true}">

                                <li class="loginlink filled positioned">
                                    <a href="#" onclick="viewLoginPopup();">
                                        <span>
							               Login
							            </span>
                                    </a>
                                </li>
                                </c:if>

                            </ul>


                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <!-- BEGIN # MODAL LOGIN -->
          <!-- BEGIN # MODAL LOGIN -->
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" align="center">
                        <img class="img-circle" id="img_logo" src="${pageContext.request.contextPath}/resource/homebody-resource/images/User.png">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
                    </div>

                    <!-- Begin # DIV Form -->
                    <div id="div-forms">

                        <!-- Begin # Login Form -->
						<form action="#" method="post"  id="login-form" class="form-horizontal">	
							<div class="modal-body" id="popupDialogBX">
								<div class="rahul">
									<iframe id="loginPopupFrame" ></iframe>
										<!-- <a class="lightbox-close" href="#"></a> -->
								</div>

                            </div>
							<div class="modal-footer">
                                
                                <div class="login-tip">
                                    <a id="login_lost_btn">Having trouble while Login? Check out these troubleshooting tips</a>
                                </div>
                            </div>
        					
						</form>
                        <!-- End # Login Form -->

                        <!-- Begin | tips -->
                        <form id="lost-form" style="display:none;">
                            <div class="modal-body">
                                <div class="col-sm-12">
                                    <p>Kindly follow below steps for resolving login related issues:</p>
                                    <ol>
                                        <li>Kindly clear your web browser’s history and cache.</li>
                                        <li>Close your web browser.</li>
                                        <li>Open your web browser and Enter URL of LGD website in the address bar and press enter.</li>
                                        <li>Click on Login button available on the Home of LGD website.</li>
                                        <li>A login popup window will appear.</li>
                                        <li>Enter Valid User Credentials.</li>
                                        <li>In case in the Login Popup window exception is coming or nothing is visible in it then follow below steps :
                                            <ul>
                                                <li>Enter Below URL in the address bar of your web browser and press enter :<a href="http://panchayatonline.gov.in" target="_blank">http://panchayatonline.gov.in</a></li>
                                                <li>Click on the Login button available on the home page.</li>
                                                <li>A login window will appear.</li>
                                                <li>Add security exception from there using add exception button.</li>
                                                <li>Now Close your web browser window.</li>
                                                <li>Open your web browser window and now access LGD website.</li>
                                            </ul>
                                        </li>
                                    </ol>
                                </div>
                                <div class="modal-footer">
                                    <div class="login-tip">
                                        <button id="lost_login_btn" type="button" class="btn btn-info">Log In</button>
                                    </div>
                                </div>
                                </div>
                        </form>
                        <!-- End | Tips -->


                        </div>
                        <!-- End # DIV Form -->

                    </div>
                </div>
            </div>
			
            <!-- END # MODAL LOGIN -->
            <Script type="text/javascript">
            var viewLoginPopup = function() {
				$('#loginPopupFrame').attr('src', "login.htm?x=nonpopup");
				$('#login-modal').modal('show')	
        	};
            </Script> --%>