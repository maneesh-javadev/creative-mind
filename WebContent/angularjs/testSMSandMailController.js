var publicModule = angular.module("publicModule", []);
publicModule.controller("testSMSMailController", [ '$scope', "testSMSMailControllerService",
		function($scope, testSMSMailControllerService) {
	
	
	
	$scope.testService=function(){
		$scope.isMessage = false;
		
		var email="",ph="";
		if($scope.txtmail!=null && $scope.txtmail!=undefined){
			email=$scope.txtmail;
		}
		if($scope.contact!=null && $scope.contact!=undefined){
			ph=$scope.contact;
		}
		
		if(!(email!="" || ph!="")){
			$scope.isMessage = true;
			$("#msgBoard").addClass('red-text');
			$("#msgBoard").html("Please enter at least one valid option Email or Mobile No.");
			
			$("#msgBoard").show().delay(2000).fadeOut();
				
		}else{
			testSMSMailControllerService.testSMSandMailServiceCall(email,ph).then(function(response){
				if(response.data.responseCode!=null &&  response.data.responseCode!=undefined){
					ecode= parseInt(response.data.responseCode);
					msg=response.data.responseMessage
					if(ecode==200){
						$("#msgBoard").addClass('blue-text');
					}else{
					  $("#msgBoard").addClass('red-text');
					}
					
					$("#msgBoard").html(msg);
						$scope.isMessage = true;
						$("#msgBoard").show().delay(2000).fadeOut();
						
					
				}
				
			});
		}
		
		
	};
	
}]);