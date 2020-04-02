reactivateLocalBody.controller('submitGovernmentOrderFormController', function($scope,$http,$modalInstance,localBodyCode,localBodyTypeCode,flagCode) { 
	
	
	$scope.maxDate = new Date();
	$scope.governmentOrder = {};
	
	calculateDate();
	setValues();
	
	$scope.reinitialize = true;
	
	function calculateDate(){
		var dtToday = new Date();

		var month = dtToday.getMonth() + 1;
		var day = dtToday.getDate();
		var year = dtToday.getFullYear();
		if(month < 10)
		month = '0' + month.toString();
		if(day < 10)
		day = '0' + day.toString();
		
		$scope.maxDate = year + '-' + month + '-' + day;
	}
	
	function setValues(){
		$scope.governmentOrder.localBodyCode = localBodyCode;
		$scope.governmentOrder.localBodyTypeCode = localBodyTypeCode;
		$scope.governmentOrder.flagCode = flagCode;
	}
	
	$scope.saveEntity = function(){
		showLoadingImage();
		var file = $('#uploadFile').prop('files');
		if(file[0]==null){
			toastr.error("Please Upload Government Order");
		}
		var formdata = new FormData();
		formdata.append('file', file[0]);
		formdata.append('governmentOrder',angular.toJson($scope.governmentOrder,true));
		 $http.post('submitGovernmentOrder.htm?<csrf:token uri=submitGovernmentOrder.htm/>', formdata, {
  		   headers:{'Content-Type': undefined},
  	 		transformRequest: angular.identity
  		}).then(
  				function(response){
  					toastr.success("Localbody is reactivated successfully");
  					hideLoadingImage();
  					$modalInstance.close();
  					}, 
  				function(response){
//  						toastr.success("Something went wrong!!");
  					});
	}
	
	$scope.cancel = function () {
		hideLoadingImage();
		$modalInstance.dismiss('cancel');
	};

});