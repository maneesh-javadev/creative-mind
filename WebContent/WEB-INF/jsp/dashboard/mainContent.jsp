
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/fontawesome-free-5.12.1-web/js/all.js"></script>  --%>
<%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/dist/css/dashboard-page/material-dashboard.min.css"> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/dist/css/dashboard-page/style.css"> 
 <%-- fdf:${isUserType} --%>
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="publicModule">
<link rel="stylesheet" href="${pageContext.request.contextPath}/angularjs/angular-material.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/common-resource/Loader/loeader1.css">


<script>
var _isDashBoradCenterorState=isParseJson('${isDashBoradCenterorState.charAt(0) eq 'C'.charAt(0) }');
</script>




<div id="preloader" ng-show="isloader" >
  			<div id="loader"></div>
		</div>
<c:choose>
	<c:when test="${isUserType eq 'R'.charAt(0) }">
		<%@include file="dashboardRevenue.jsp"%>
	</c:when>
	<c:when test="${isUserType eq 'P'.charAt(0) }">
		<%@include file="dashboardPRI.jsp"%>
	</c:when>
	<c:when test="${isUserType eq 'U'.charAt(0) }">
		<%@include file="dashboardULB.jsp"%>
	</c:when>
	<c:when test="${isUserType eq 'T'.charAt(0) }">
		<%@include file="dashboardTLB.jsp"%>
	</c:when>
	<c:when test="${isUserType eq 'B'.charAt(0) }">
		<%@include file="dashboardBlock.jsp"%>
	</c:when>
	
	<c:otherwise>
	
	 <section class="content">

                <!-- Main row -->
                <div class="row">
                    <!-- main col -->
                    <section class="col-lg-12 ">
                            <div class="box ">
                                <div class="box-header with-border">

                                    <h3 class="box-title">Welcome to Local Government Directory</h3>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <p>The republic of India comprises the union government, 28 state governments, 7 union territories and about 2,45,000 local governments. Though there are many software applications developed and successfully implemented for catering the needs of local governments, there is no comprehensive web site or application which provides authenticated and up-to-date information on list of local governments. National Panchayat Directory (http://panchayatdirectory.gov.in) was an effort of Ministry of Panchayati Raj, developed and technically maintained by National Informatics Center(NIC, http://www.nic.in ), to maintain list of rural local governments that are called as Panchayati Raj Institutions.
									Local Government Directory will be used by the Central and state departments who are responsible for forming new states/UTs, new districts, new sub-districts, new villages and new local government bodies as well as changing their status , name and formation. "
 									</p>
 									 
                                <!-- /.box-body -->
                               
                                
                            </div>
                            <!-- /.box -->
								
                    </section>
                    <!-- /.main col -->

 </div>
 </section>
	
	</c:otherwise>
</c:choose>

   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-animate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-route.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-aria.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-messages.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/angular-material.min.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/dashboard-page/revenueDashboardController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/dashboard-page/revenueDashboardService.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/dashboard-page/dashboardRevenueChildController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/dashboard-page/dashboardLBChildController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/dashboard-page/dashboardChangeEntityChildController.js"></script>

<script  type="text/javascript" src="${pageContext.request.contextPath}/angularjs/ui-bootstrap-tpls-0.11.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/angularjs/pagination.js"></script>
</html>