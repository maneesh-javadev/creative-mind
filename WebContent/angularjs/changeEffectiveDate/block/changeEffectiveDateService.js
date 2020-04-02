
app.service('changeEffectiveDateService', [ '$http', function($http) {
	
	this.getEntityDetail = function(lbCode) {
		getEntityEffectiveDate= {"entityCode":lbCode,  "entityType" : 'B' };
		return $http.post("getEffectiveDate.htm?<csrf:token uri=getEffectiveDate.htm/>",getEntityEffectiveDate);
	};
	
	this.saveEffectiveDate = function(entityList) {
		return $http.post("saveEffectiveDateBlock.htm?<csrf:token uri=saveEffectiveDateBlock.htm/>",entityList);
	};
	
	this.getEntityDetailbyVersion = function(getEntityEffectiveDate) {
		return $http.post("getEffectiveDatebyVersion.htm?<csrf:token uri=getEffectiveDatebyVersion.htm/>",getEntityEffectiveDate);
	};
		
}]);
