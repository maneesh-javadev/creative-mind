
app.service('changeEffectiveDateService', [ '$http', function($http) {
	
	this.getEntityDetail = function(lbCode) {
		getEntityEffectiveDate= {"entityCode":lbCode,  "entityType" : 'G' };
		return $http.post("getEffectiveDate.htm?<csrf:token uri=getEffectiveDate.htm/>",getEntityEffectiveDate);
	};
	
	this.saveEffectiveDate = function(entityList) {
		return $http.post("saveEffectiveDateLB.htm?<csrf:token uri=saveEffectiveDateLB.htm/>",entityList);
	};
	
	this.getEntityDetailbyVersion = function(getEntityEffectiveDate) {
		return $http.post("getEffectiveDatebyVersion.htm?<csrf:token uri=getEffectiveDatebyVersion.htm/>",getEntityEffectiveDate);
	};
		
}]);