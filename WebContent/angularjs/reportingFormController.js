app.controller('reportingFormController',function($scope,$http,$modalInstance){  
	
	$scope.reportingIssue={};
	$scope.centerFlag=false;
	$scope.stateFlag=true;
	$scope.centerList=[];
	$scope.reportingIssueList={};
	
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$http.get('getStateList.do?<csrf:token uri=getStateList.do/>').success(function(data) {
		$scope.statelist = data;
	})
	.error(function(error,status){
		alert(status);
	});
	
	$scope.showStateList=function(){
		$scope.centerFlag=false;
		$scope.stateFlag=true;
	$http.get('getStateList.do?<csrf:token uri=getStateList.do/>').success(function(data) {
		$scope.statelist = data;
	})
	.error(function(error,status){
		alert(status);
	});
	};
	
	$scope.showCenterList=function(){
		$scope.centerFlag=true;
		$scope.stateFlag=false;
		$http.get('getCenterList.do?<csrf:token uri=getCenterList.do/>').success(function(data) {
			$scope.centerList = data;
		})
		.error(function(error,status){
			alert(status);
		});
	}
	
	
	 $http.get('listOfReportingIssue.do?<csrf:token uri=listOfReportingIssue.do />').success(function(data) {
		 $scope.issueTypes=data.issueType;
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
	
	
	$scope.saveData = function() {
	  
	     $http.post('createNewReportingIssue.do?<csrf:token uri=createNewReportingIssue.do />', $scope.reportingIssue)
	          .then(
	                function(response){
	                	alert("Data has been saved sucessfully");
	                	$scope.reportingIssueList=response.data.reportingIssues;
	                	$modalInstance.close($scope.reportingIssueList);
	                }, 
	                function(response){
	                	alert("Error while creating new Entity");
	                }
	          );
	  };
	
});




