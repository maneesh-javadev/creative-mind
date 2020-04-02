entityform.controller('govermentOrderController', function($scope) { 
	
    $scope.submit = function () {
        $modalInstance.dismiss('cancel'); // dismiss(reason) - a method that can be used to dismiss a modal, passing a reason
    }
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel'); 
    };
    
    });