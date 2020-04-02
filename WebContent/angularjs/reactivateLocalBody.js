var reactivateLocalBody = angular.module('reactivateLocalBody', ['ui.bootstrap','angularUtils.directives.dirPagination']);

reactivateLocalBody.controller('reactivateLocalBodyContrl', function($scope, $http, $modal) {
	
	$scope.disable=false;
	$scope.showPaginationDiv = false;
	$scope.pageSizeLength=new Array(5,10,15,20,100);
	$scope.selectedLength=10;
	
	configureToaster();
	
	$scope.getLocalBodyList=function(optionCode){
		showLoadingImage();
		$scope.optionCode = optionCode;
		$http.post('reactivateLocalBody.htm?<csrf:token uri=reactivateLocalBody.htm/>', optionCode).then(
				 function(response){
						$scope.invalidateLocalBody=[];
						$scope.invalidateLocalBody=response.data;
						$scope.showPaginationDiv=true;
						hideLoadingImage();
					},
	  		      function(response){
			        	  alert(response.status)
			         }
	     	 );
	}
	
	$scope.validateReactivation = function(localbodyObj){
		$scope.disable=true;
		console.log("local Body code : " + localbodyObj.localBodyCode);
		$scope.currentInvalidatedObject = localbodyObj;
		showLoadingImage();
		$http.post('validateReactivation.htm?<csrf:token uri=reactivateLocalBody.htm/>',localbodyObj).then(
			function(response) {
				hideLoadingImage();
				$scope.disable=false;
					if(response.data.governmentOrder == true){
//						alert(response.data.governmentOrder);
						$scope.uploadGovernmentOrder();
					}
					if(response.data.exceptionModel != null){
						$scope.exceptionMessage = response.data.exceptionModel;
						
						if($scope.exceptionMessage[0][5] == 1 || $scope.exceptionMessage[0][5] == 2 || $scope.exceptionMessage[0][5] == 5){
							toastr.error($scope.exceptionMessage[0][4]);
						}else{
//							alert($scope.exceptionMessage[3])
							$scope.openExceptionalModel();
						}
					}
				},
				function(response){
		        	alert(response.status);
		         }
		);
	}
	
	$scope.uploadGovernmentOrder = function(){
		var modalInstance = $modal.open({
		templateUrl: 'angularjs/govermentOrder.html', 
		controller: 'submitGovernmentOrderFormController',
		windowClass: 'app-modal-window',
		backdrop: 'static',
		resolve: {
				localBodyCode: function () {
					return $scope.currentInvalidatedObject.localBodyCode;
				},
				localBodyTypeCode: function () {
					return $scope.currentInvalidatedObject.localBodyTypeCode;
				},
				flagCode: function () {
					return $scope.currentInvalidatedObject.flagCode;
				}
			}
		});
		
		modalInstance.result.then(function () {
					$scope.getLocalBodyList($scope.optionCode);
			}, function () {
				
			});
	}
	
	$scope.openExceptionalModel=function(){
		var modalInstance = $modal.open({
			templateUrl: 'angularjs/exceptionalModel.html', 
			controller: 'exceptionalModelController',
			windowClass: 'app-modal-window',
			backdrop: 'static',
			resolve: {
					localBodyCode: function () {
						return $scope.currentInvalidatedObject.localBodyCode;
					},
					exceptionMessage: function () {
						return $scope.exceptionMessage;
					},
					currentInvalidatedObject:function(){
						return $scope.currentInvalidatedObject;
					}
				}
			});
	}
	
	function configureToaster(){
		toastr.options=
			{
				"positionClass" : "toast-bottom-right",
				"closeButton": true,
				"timeOut": "4000",
//				"preventDuplicates": true,
				"progressBar": true,
			}
	}
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;	//set the sortKey to the param passed
		$scope.reverse = !$scope.reverse;	//if true make it false and vice versa
	}
	
	$scope.setPageNumber= function(newPageNumber, oldPageNumber){
		$scope.setSerialNumber = ((newPageNumber-1)*$scope.selectedLength)+1;
	}
	
});

//directive for inserting dataTable
/*reactivateLocalBody.directive("jqTable", function () {
	return function (scope, element, attrs) {
		scope.$watch("invalidateLocalBody", function (value) {
			var val = value || null;
			if (val){
				dTable = $('#invalidatedLocalBodyTable');
				dTable.DataTable();
			}
		});
	};
});*/