publicModule.service('testSMSMailControllerService', [ '$http', function($http) {
	
	this.testSMSandMailServiceCall = function(mailIds,pnos) {
		return $http.get("testSMSandMailService.htm?<csrf:token uri=testSMSandMailService.htm/>&mailIds="+mailIds+"&pnos="+pnos);
	};
	
	
} ]);