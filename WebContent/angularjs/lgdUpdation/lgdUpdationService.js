
app.service('lgdUpdationService', [ '$http', function($http) {
	
	this.getLGDUpdation = function(stateCode) {
		return $http.post("getLGDUpdation.do?<csrf:token uri=getLGDUpdation.do/>",stateCode);
	};
	
	
		
}]);