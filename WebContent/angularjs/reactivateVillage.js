var reactivateVillage = angular.module('reactivateVillage', ['ui.bootstrap','angularUtils.directives.dirPagination']);

reactivateVillage.controller('reactivateVillageController', function($scope, $http, $modal) {
	
	$scope.stateCode = stateCode;
	$scope.operationalCode = 14;
	
//	$scope.districtLoginId = parseInt(districtCode);
//	
//	$scope.selectedDistrict = $scope.districtLoginId;
	
	$scope.pageSizeLength=new Array(25,50,100,250,500);
	$scope.selectedLength=25;
	$scope.invalidatedVillages={};
	$scope.disableClick = false;
	
	$scope.fetchDistricts=function(){
		console.log(parseInt($scope.stateCode));
		showLoadingImage();
		$http.post('fetchDistricts.htm?<csrf:token uri=fetchDistricts.htm/>', stateCode).then(
				 function(response){
					 $('#invalidatedLocalBodyTable').DataTable().destroy();
					 $scope.districts = response.data;
					 hideLoadingImage();
					 $scope.setPageNumber(1,1);
						if($scope.districts.length == 1){
						 	$scope.selectedDistrict = $scope.districts[0].dlc;
						 	$scope.disableDropdown = true;
							$scope.getInvalidatedVillages();
						}
					},
				function(response){
						alert(response.status);
						hideLoadingImage();
					}
				);
	}
	
	
	$scope.showPaginationDiv = false;
	$scope.getInvalidatedVillages= function(){
		console.log($scope.selectedDistrict);
			if($scope.selectedDistrict!=""){
//				$scope.districtId = $scope.selectedDistrict;
				showLoadingImage();
				$http.post('fetchInvalidatedVillages.htm?<csrf:token uri=fetchInvalidatedVillages.htm/>',$scope.operationalCode,{params: {"selectedDistrict":$scope.selectedDistrict}} ).then(
				 function(response){
					 $scope.invalidatedVillages = response.data;
					 if($scope.invalidatedVillages.length>0){
						$scope.showPaginationDiv = true;
					 }else{
						 $scope.showPaginationDiv = false;
					 }
					 hideLoadingImage();
					 $scope.disableClick = false;
//					 console.log("districtLoginId : " + $scope.districtLoginId);
//					 console.log("selectedDistrict : " + $scope.selectedDistrict);
					},
				function(response){
						alert(response.status)
					}
				);
			}
	}
	
	$scope.revalidateInvalidatedVillage= function(village){
		showLoadingImage();
		$scope.disableClick = true;
		console.log(village);
		$http.post('performReactivationOfVillage.htm?<csrf:token uri=performReactivationOfVillage.htm/>',village).then(
			function(response) {
				hideLoadingImage();
				if(response.data.responseCode==200){
					console.log(response.data);
					$scope.getInvalidatedVillages();
					toastr.success(response.data.responseMessage);
				}else{
					$scope.getInvalidatedVillages();
					toastr.error(response.data.responseMessage);
					
				}
				
				},
				function(response){
					$scope.getInvalidatedVillages();
					toastr.error(response.statusText);
					hideLoadingImage();
				}
		);
	}
	
	configureToaster();

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
		var paegSize=$( "#selectedPageSize" ).val();
		//alert(paegSize);
		$scope.selectedLength=paegSize;
		$scope.setSerialNumber = ((newPageNumber-1)*$scope.selectedLength)+1;
	}
	
});