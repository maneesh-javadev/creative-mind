app.service('changeEffectiveDateService', [ '$http', function($http) {
	
	this.getEntityDetail = function(StateCode) {
		getEntityEffectiveDate= {"entityCode":StateCode,  "entityType" : 'S' };
		return $http.post("getEffectiveDate.htm?<csrf:token uri=getEffectiveDate.htm/>",getEntityEffectiveDate);
	};
	
	this.saveEffectiveDate = function(entityList) {
		return $http.post("saveEffectiveDateState.htm?<csrf:token uri=saveEffectiveDateState.htm/>",entityList);
	};
	
	this.getEntityDetailbyVersion = function(getEntityEffectiveDate) {
		return $http.post("getEffectiveDatebyVersion.htm?<csrf:token uri=getEffectiveDatebyVersion.htm/>",getEntityEffectiveDate);
	};
		
}]);