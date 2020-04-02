

app.controller('dashboardChangeEntityChildController', function($scope, $http, revenueDashboardService,$modalInstance,reportModel) { 
	$scope.LBDetailList=null;
	$scope.reportModel=reportModel;
	$scope.pageSizeLength=new Array(25,50,100,250,500);
	$scope.selectedLength='25';
	$scope.setSerialNumber=1;
	$scope.isloader=true;
	
	$scope.opType=reportModel.pEntityType.trim()+reportModel.pflag.trim();
	
	switch(reportModel.pflag){
	case 'C':$scope.changeEntity="created";break;
	case 'M':$scope.changeEntity="modified";break;
	case 'S':$scope.changeEntity="shified";break;
	}
	
	
	switch(reportModel.pEntityType){
	case 'D':$scope.hEntityName='District Name(In English)';
			 $scope.hEntityCode='District Code';
			 $scope.hEntityVersion='District Version';
			 $scope.titleName='List of District '+$scope.changeEntity+' '+reportModel.pfinyear+' financial year';
			 break;
	case 'T':$scope.hEntityName='Subdistrict Name(In English)';
			 $scope.hEntityCode='Subdistrict Code';
			 $scope.hEntityVersion='Subdistrict Version';
			 $scope.titleName='List of Subdistricts '+$scope.changeEntity+' '+reportModel.pfinyear+' financial year';
			 break;		
	case 'V':$scope.hEntityName='Village Name(In English)';
			 $scope.hEntityCode='Village Code';
			 $scope.hEntityVersion='Village Version';
			 $scope.titleName='List of Villages '+$scope.changeEntity+' '+reportModel.pfinyear+' financial year';
			 break;
	 
	case 'P':
	case 'U':
	case 'T':	
			 
			 $scope.hEntityName='Localbody Name(In English)';
			 $scope.hEntityCode='Localbody Code';
			 $scope.hEntityVersion='Localbody Version';
			 $scope.titleName='List of Localbodies '+$scope.changeEntity+' '+reportModel.pfinyear+' financial year';
			 break;
	 
	case 'B':$scope.hEntityName='Block Name(In English)';
			 $scope.hEntityCode='Block Code';
			 $scope.hEntityVersion='Block Version';
			 $scope.titleName='List of Block '+$scope.changeEntity+' '+reportModel.pfinyear+' financial year';
			 break;
	 
	}
	
	revenueDashboardService.dashboardChangeEntityDetail(reportModel.pStateCode,$scope.opType,reportModel.pfinyear).then(function(response){
		 $scope.entityChangeList= response.data;
		 $scope.isloader=false;
	 },function(error){	 
		 alert(error);
		 $scope.isloader=false;
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

