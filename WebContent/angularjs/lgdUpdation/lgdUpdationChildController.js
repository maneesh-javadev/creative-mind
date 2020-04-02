app.controller('lgdUpdationChildController', function($scope, $http, lgdUpdationService,$modalInstance,reportModel) { 

	$scope.reportModel=reportModel;
	 $scope.chartColors=['#008000', '#FF0000'];
	 $scope.labels = ["Freeze", "Unfreeze"];
	$scope.districtList=[];
	var rfcount=0;
	var ufcount=0;
	var pfcount=0;
	var tfcount=0;
	$scope.isRevenue=true;
	$scope.isULB=true;
	$scope.isPRI=true;
	$scope.isTRD=true;
	 lgdUpdationService.getLGDUpdation(reportModel.stateCode).then(function(response){
		
		 $scope.districtList= response.data
		 angular.forEach( $scope.districtList, function (obj, key) { 
            // $scope.names.push(value.name); 
			 if(obj.revenueStatus=='N.A.'){
				 $scope.isRevenue=false;
             }
			 
			 if(obj.ulbStatus=='N.A.'){
				 $scope.isULB=false;
             }
			 
			 if(obj.priStatus=='N.A.'){
				 $scope.isPRI=false;
             }
			 
			 if(obj.trdStatus=='N.A.'){
				 $scope.isTRD=false;
             }
			 
             if(obj.revenueStatus=='Freeze'){
            	 rfcount++;
             }
             if(obj.ulbStatus=='Freeze'){
            	 ufcount++;
             }
             if(obj.priStatus=='Freeze'){
            	 pfcount++;
             }
             if(obj.trdStatus=='Freeze'){
            	tfcount++;
             }
         }); 
		 l=$scope.districtList.length;
		 frp=rfcount*100/l;
		 srp=100-frp;
		 $scope.rdata = [frp.toFixed(2),srp.toFixed(2)];
		 
		 fup=ufcount*100/l;
		sup=100-fup;
		$scope.udata = [fup.toFixed(2),sup.toFixed(2)];
				  
		fpp=pfcount*100/l;
		spp=100-fpp;
		$scope.pdata = [fpp.toFixed(2),spp.toFixed(2)];
					  
		ftp=tfcount*100/l;
		stp=100-ftp;
		 $scope.tdata = [ftp.toFixed(2),stp.toFixed(2)];
		 
	 },function(error){	 
		 alert(error);
	 });
	 
	 $scope.cancel = function () {
			$modalInstance.close();
		};
	
});