var app=angular.module("publicModule",['ngMaterial','ngMessages','ui.bootstrap','angularUtils.directives.dirPagination']);

app.controller("revenueDashboardController",['$scope','$interval','$modal','revenueDashboardService',function($scope,$interval,$modal,revenueDashboardService){

	$scope.changeEntityList=[];
	$scope.isnodalOfficer=true;
	 $scope.entityList=[];
	 var d = new Date();
	 var m = d.getMonth();
	 var year = d.getFullYear()
	 if(m>3){
		 $scope.curfinyear=(year-2+"-"+year-1);
	 }else{
		 $scope.curfinyear=(year-1+"-"+year);
	 }
	 $scope.finYearModel =$scope.curfinyear;
	// alert(_isDashBoradCenterorState);
	 	if(_isDashBoradCenterorState){
	 		$scope.isnodalOfficer=false;
	 	}
	 loadinit(0);
	 
	
		 function loadinit(pStateCode){
			 $scope.isloader=true;
			 $scope.changeEntityList=[];
			 revenueDashboardService.getDashboardDetials(pStateCode).then(function(response){
				 $scope.entityList= response.data;
				 $scope.isloader=false;
			 },function(error){	 
				 alert(error);
				 $scope.isloader=false;
			 });
			 
			 revenueDashboardService.getStateList().then(function(response){
				 $scope.stateList= response.data;
			 },function(error){	 
				 alert(error);
			 });
		 }
		 
		 $scope.changeState=function(stateModel){
			if(stateModel==null){
				 stateModel=0; 
				 $scope.isnodalOfficer=false;
			 }
			 loadinit(stateModel);
			 if(stateModel>0){
				 $scope.isnodalOfficer=true;
			 }
		 }
		 
		 $scope.changeFinYear=function(finYearModel){
			 revenueDashboardService.getEntityDetailsFinYearWise(finYearModel).then(function(response){
				 $scope.entityList.getDashboardEntityDetails= response.data;
			 },function(error){	 
				 alert(error);
			 });
		 }
		 
		 $scope.showRevenueDetails=function(flag,titleName){
				
				reportModel= {"flag":flag,"titleName":titleName,"pStateCode":$scope.stateModel};
				
				var modalInstance = $modal.open({
					templateUrl: 'angularjs/dashboard-page/dashRevenueDetails.html', 
					controller: 'dashboardRevenueChildController',
					windowClass: 'app-modal-window',
					backdrop: 'static',
					scope: $scope ,
					resolve: {	
						reportModel: function () {
		                    return reportModel;
		                },
		               
					}
					});
					
			}
		 
		 
		 $scope.showLBDetails=function(flag,titleName){
				
				reportModel= {"flag":flag,"titleName":titleName,"pStateCode":$scope.stateModel};
				
				var modalInstance = $modal.open({
					templateUrl: 'angularjs/dashboard-page/dashLBDetails.html', 
					controller: 'dashboardLBChildController',
					windowClass: 'app-modal-window',
					backdrop: 'static',
					resolve: {	
						reportModel: function () {
		                    return reportModel;
		                },
		               
					}
					});
					
			}
		 
		 $scope.showEntityChangeDetails=function(flag,entityType){
			 	$scope.pStateCode=$scope.stateModel!=null?$scope.stateModel:0;
			 	$scope.pfinyear=$scope.finYearModel!=null?$scope.finYearModel:null;
			 	reportModel= {"pflag":flag,"pStateCode":$scope.pStateCode,"pfinyear":$scope.pfinyear,"pEntityType":entityType};
				
				var modalInstance = $modal.open({
					templateUrl: 'angularjs/dashboard-page/dashEntityChangeDetail.html', 
					controller: 'dashboardChangeEntityChildController',
					windowClass: 'app-modal-window',
					backdrop: 'static',
					resolve: {	
						reportModel: function () {
		                    return reportModel;
		                },
		               
					}
					});
					
			}
		 
		 
		 
		

}])


