entityform.controller('mergeEntityController', function($scope, $modalInstance,mergeEntityObjects) {
	
	$scope.mergeNameFrom="";
	$scope.mergeNameTo="";
	$scope.mergeEntityObjects=[];
	$scope.mergeEntityObjects=mergeEntityObjects;
	$scope.mergeEntityList=[];
	$scope.mergeSubDataList=[];
	
	$scope.mergeEntityData=[];
	
	
	angular.forEach($scope.mergeEntityObjects, function(tp,i) {
		if(i===0){
			$scope.mergeEntityList=tp;
			$scope.mergeNameFrom=tp[0].parentAdminUnitEntityName;
		}
		else{
			$scope.mergeSubDataList=tp;
			$scope.mergeNameTo=tp[0].parentAdminUnitEntityName;
		}
	});
	console.log($scope.mergeSubDataList);
    $scope.submit = function () {
        console.log('Submiting user info.'); // kinda console logs this statement
        console.log(user); 
        $modalInstance.dismiss('cancel'); // dismiss(reason) - a method that can be used to dismiss a modal, passing a reason
    }
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel'); 
    };
    
    $scope.moveItem = function(items, from, to) {
		angular.forEach(items, function(item) {
		var idx = from.indexOf(item);
		from.splice(idx, 1);
		item.adminEntityNameEnglish = item.adminEntityNameEnglish.substring(0, item.adminEntityNameEnglish.length - 6);
		to.push(item);
	});
	};
    
    
    });