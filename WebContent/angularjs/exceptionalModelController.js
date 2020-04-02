reactivateLocalBody.controller('exceptionalModelController', function($scope,$http,$modalInstance,localBodyCode,exceptionMessage,currentInvalidatedObject) { 
	
	setObjects();
	
	
	function setObjects(){
		$scope.exceptionMessage = exceptionMessage;
		$scope.currentInvalidatedObject = currentInvalidatedObject;
	}
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel'); 
	};

});