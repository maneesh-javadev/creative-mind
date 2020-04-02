

app.controller('dashboardLBChildController', function($scope, $http, revenueDashboardService,$modalInstance,reportModel) { 
	$scope.LBDetailList=null;
	$scope.reportModel=reportModel;
	$scope.titleName=reportModel.titleName;
	$scope.pStateCode=reportModel.pStateCode!=null?reportModel.pStateCode:0;
	$scope.pageSizeLength=new Array(25,50,100,250,500);
	$scope.selectedLength='25';
	$scope.setSerialNumber=1;
	$scope.isloader=true;
	
	
	
	revenueDashboardService.getLBDetails($scope.pStateCode,reportModel.flag).then(function(response){
		 $scope.LBDetailList= response.data;
		 $scope.isloader=false;
		 $scope.showPaginationDiv = true;
	 },function(error){	 
		 alert(error);
	 });
	 
	 $scope.cancel = function () {
			$modalInstance.close();
		};
		
		
		$scope.setPageNumber= function(newPageNumber, oldPageNumber){
			//$scope.x=$scope.selectedLength.model;
			//var a=10;
			//console.log(a); 
			
			
			var paegSize=$( "#selectedPageSize" ).val();
			//alert(paegSize);
			$scope.selectedLength=paegSize;
			$scope.setSerialNumber = ((newPageNumber-1)*$scope.selectedLength)+1;
		}
});

