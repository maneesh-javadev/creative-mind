var app=angular.module("publicModule",['ngMaterial','ngMessages','ui.bootstrap','chart.js']);

app.controller("lgdUpdationController",['$scope','$modal','lgdUpdationService',function($scope,$modal,lgdUpdationService){


	$scope.districtList=[];
	 $scope.entityList=[];
	$scope.color="bg-info"
	
	 loadinit();
	 
	
		 function loadinit(){
			 $scope.isloader=true;
			 $scope.isDistrictDiv=false;
			 $scope.isStateDiv=true;
		 $scope.changeEntityList=[];
		 lgdUpdationService.getLGDUpdation(0).then(function(response){
			 $scope.isloader=false;
			 $scope.entityList= response.data
			
		
		 },function(error){	 
			 alert(error);
		 });
		 
	 }
		 
		 
		 $scope.showDistrictReport=function(stateName,stateCode){
			 $scope.isloader=true;
				reportModel= {"stateName":stateName,"stateCode":stateCode};
				$scope.reportModel=reportModel;
				 
				 lgdUpdationService.getLGDUpdation(reportModel.stateCode).then(function(response){
					 $scope.isloader=false;
					 $scope.districtList= response.data
					 $scope.isDistrictDiv=true;
					 $scope.isStateDiv=false;
				 },function(error){	 
					 alert(error);
				 });
					
			}
	
	 
		 $scope.stateReport=function(stateName,stateCode){
				
			 $scope.isDistrictDiv=false;
			 $scope.isStateDiv=true;
					
			}
		 
		 
		 $scope.showChart=function(stateName,stateCode){
			 
				reportModel= {"stateName":stateName,"stateCode":stateCode};
				
				var modalInstance = $modal.open({
					templateUrl: 'angularjs/lgdUpdation/districtFreezeUnfeeze.html', 
					controller: 'lgdUpdationChildController',
					windowClass: 'app-modal-window',
					backdrop: 'static',
					resolve: {	
						reportModel: function () {
		                    return reportModel;
		                },
		               
					}
					});
				
				
					
			}
	

}]);