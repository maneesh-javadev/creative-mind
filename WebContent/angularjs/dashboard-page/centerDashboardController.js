var app=angular.module("publicModule",['ui.bootstrap','chart.js']);

app.controller("centerDashboardController",['$scope','$modal','revenueDashboardService',function($scope,$modal,revenueDashboardService){


	/* $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
	  $scope.data = [300, 500, 100];*/
	  
	 /* $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
	  $scope.series = ['Series A', 'Series B'];

	  $scope.data = [
	    [65, 59, 80, 81, 56, 55, 40],
	    [28, 48, 40, 19, 86, 27, 90]
	  ];*/
	$scope.labels=[];
	$scope.invalidateCensusVillage=[];
	$scope.invalidateVillage=[];
	 $scope.data =[];
	 $scope.series=[];
	 loadinit();
	 
	
		 function loadinit(){
			 $scope.changeEntityList=[];
			 revenueDashboardService.getAllEntityCountList().then(function(response){
				 $scope.entityList= response.data;
				 
				 angular.forEach($scope.entityList, function (obj, key) { 
					 if(obj.stateCode<=11 && obj.stateCode!=9){
						 $scope.labels.push(obj.stateNameEnglish.trim()); 
						 $scope.invalidateCensusVillage.push(obj.invalidateCensusVillage); 
						 $scope.invalidateVillage.push(obj.invalidateVillage); 	 
					 }
					 
		            }); 
				 
				 $scope.series.push('Invalidate Census Village');
				 $scope.series.push('Invalidate Village');
				 $scope.data.push($scope.invalidateCensusVillage);
				 $scope.data.push($scope.invalidateVillage);
			 },function(error){	 
				 alert(error);
			 });
		 }
		 
		 
		 
		 
		

}])


