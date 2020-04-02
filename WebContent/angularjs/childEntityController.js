entityform.controller('childEntityController', function($scope,$compile, $modalInstance,list,parent,child) {
	
	$scope.childEntity = {};
	$scope.newChildEntity = [];
	$scope.parent = parent;
	$scope.child = child;
	$scope.availableList = list;
	console.log($scope.availableList);
	$scope.selectedList = [];
	$scope.childEntity.parent = parent;
	 
	 
	 
	 
	
	$scope.moveItem = function(items, from, to) {
		angular.forEach(items, function(item) {
		var idx = from.indexOf(item);
		from.splice(idx, 1);
		/*item.adminEntityNameEnglish = item.adminEntityNameEnglish.substring(0, item.adminEntityNameEnglish.length - 6);*/
		to.push(item);
	});
    // clear selection
    $scope.available = "";
    $scope.selected = "";
	};
	
	$scope.moveAll = function(from, to) {
		angular.forEach(from, function(item) {
			/*item.name = item.adminEntityNameEnglish.substring(0, item.adminEntityNameEnglish.length - 6);*/
			to.push(item);
	    	});
	    from.length = 0;
	  };
	
    $scope.saveChildEntity = function () {
        $scope.childEntity.selectedEntity = $scope.selectedList;
        $modalInstance.close($scope.childEntity); // dismiss(reason) - a method that can be used to dismiss a modal, passing a reason
    }
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel'); 
    };
    });
	 