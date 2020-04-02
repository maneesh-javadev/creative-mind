app.controller('changeEffectiveDateChildController', function($scope, $http, changeEffectiveDateService,$modalInstance,reportModel) { 

	$scope.reportModel=reportModel;
	
	 changeEffectiveDateService.getEntityDetailbyVersion(reportModel).then(function(response){
		 $scope.minorVersionEntityList= response.data;
		 
	
	 },function(error){	 
		 alert(error);
	 });
	 
	 $scope.cancel = function () {
			$modalInstance.close();
		};
	
});