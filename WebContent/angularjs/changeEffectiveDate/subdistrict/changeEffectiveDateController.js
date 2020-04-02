var app=angular.module("publicModule",['ngMaterial','ngMessages','ui.bootstrap']);

app.controller("changeEffectiveDateController",['$scope','$interval','$modal','changeEffectiveDateService',function($scope,$interval,$modal,changeEffectiveDateService){


	$scope.changeEntityList=[];
	 $scope.entityList=[];
	 $scope.SdCode=_JS_lb_CODE;
	 $scope.myDate = new Date();
	 $scope.myMaxDate=_JS_CURRENT_DATE;
	 loadinit();
	 
	
		 function loadinit(){
		 $scope.changeEntityList=[];
		 changeEffectiveDateService.getEntityDetail($scope.SdCode).then(function(response){
			 $scope.entityList= response.data.entityEffectiveList;
			 $scope.SdDetail= response.data.entityDetail;
			
			 for (var i = 0; i < $scope.entityList.length; i++) {
				 $scope.entityList[i].effectiveDate =new Date($scope.entityList[i].effectiveDate) ;
				 $scope.entityList[i].newEffectiveDate =new Date($scope.entityList[i].newEffectiveDate) ;
				 
			 }
		
		 },function(error){	 
			 alert(error);
		 });
		 
	 }
	
	 
	 
	 valdiateData=function(){
		 var retVal='T';
		 var flag=false;
		 if ($scope.changeEntityList.length>0){
			var  preDate=$scope.changeEntityList[0].newEffectiveDate;
			 for (var i = 0; i < $scope.changeEntityList.length; i++) {
				
				 if(preDate>$scope.changeEntityList[i].newEffectiveDate){
					 retVal='I';
				 }
				 preDate=$scope.changeEntityList[i].newEffectiveDate;
				 
			 }
			 
			
			
		 }else{
			 retVal='N'
		 }
		 
		 return retVal;
		 
	 }
	 
	 
	 $scope.saveEffectiveDate=function(){
		j=0;
		 for (var i = 0; i < $scope.entityList.length; i++) {
			 changeFlag=$scope.entityList[i].effectiveDate>$scope.entityList[i].newEffectiveDate || $scope.entityList[i].effectiveDate<$scope.entityList[i].newEffectiveDate?true:false;
			  if(changeFlag){
				  $scope.changeEntityList[j] =$scope.entityList[i];
				  j++;
			  }	
		 }
		if(valdiateData()=='T'){
			$scope.isDisabled=true;
			changeEffectiveDateService.saveEffectiveDate($scope.changeEntityList).then(function(response){
				$scope.isDisabled=false;
				
				if(response.data.responseCode==200){
					$scope.showMessage("Effective Date changed Successfully ");
					loadinit();
				}else if(response.data.responseCode==300){
					$scope.showError(response.data.reponseObject);
					 $scope.changeEntityList=[];
				}else{
					
					$scope.showError("There is some problem..");
					 $scope.changeEntityList=[];
				}
				
				
			},function(error){
				$scope.isDisabled=false;
				alert(error);
			});	
		}else if(valdiateData()=='N'){
			$scope.showError("There is no change in previous effective date and new effective date.")
			}else if(valdiateData()=='I'){
				$scope.showError("New effective date must be in assending order.");
			}else if(valdiateData()=='D'){
			$scope.isDisabled=true;
		}
		
		 
		}
	 
	 $scope.showError=function(msg){
		 $scope.errorbox=true;
			$scope.errormsg=msg;
				stop = $interval(function() {
					$scope.errorbox=false;
					$interval.cancel(stop);
		          }, 2000); 
		 
	 };
	 
	 $scope.showMessage=function(msg){
		 $scope.msgbox=true;
			$scope.message=msg;
				stop1 = $interval(function() {
					$scope.msgbox=false;
					$interval.cancel(stop1);
		          }, 2000); 
		 
	 };
	 
	$scope.showMinorVersion=function(villageName,villageCode,villageVersion){
		
		reportModel= {"entityNameEnglish":villageName,"entityCode":villageCode, "entityVersion" : villageVersion,"entityType":'T'};
		
		var modalInstance = $modal.open({
			templateUrl: 'angularjs/changeEffectiveDate/subdistrict/minorListofEntity.html', 
			controller: 'changeEffectiveDateChildController',
			windowClass: 'app-modal-window',
			backdrop: 'static',
			resolve: {	
				reportModel: function () {
                    return reportModel;
                },
               
			}
			});
			
	}

}])
.config(function ($mdDateLocaleProvider) {
    $mdDateLocaleProvider.formatDate = function (date) {
        return date ? moment(date).format('DD/MM/YYYY') : '';
        //return date ? moment(date).format('MM/DD/YYYY') : '';
        //return date ? moment(date).format('YYYY-MM-DD') : '';
    };

    $mdDateLocaleProvider.parseDate = function (dateString) {
        var m = moment(dateString, 'DD/MM/YYYY', true);
        //var m = moment(dateString, 'MM/DD/YYYY', true);
        //var m = moment(dateString, 'YYYY-MM-DD', true);
        return m.isValid() ? m.toDate() : new Date(NaN);
    };
});