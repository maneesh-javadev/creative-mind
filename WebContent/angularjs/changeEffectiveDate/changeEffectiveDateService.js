
app.service('changeEffectiveDateService', [ '$http', function($http) {
	
	this.getEntityDetail = function(villageCode) {
		getEntityEffectiveDate= {"entityCode":villageCode,  "entityType" : 'V' };
		return $http.post("getEffectiveDate.htm?<csrf:token uri=getEffectiveDate.htm/>",getEntityEffectiveDate);
	};
	
	this.saveEffectiveDate = function(entityList) {
		return $http.post("saveEffectiveDate.htm?<csrf:token uri=saveEffectiveDate.htm/>",entityList);
	};
	
	this.getEntityDetailbyVersion = function(getEntityEffectiveDate) {
		return $http.post("getEffectiveDatebyVersion.htm?<csrf:token uri=getEffectiveDatebyVersion.htm/>",getEntityEffectiveDate);
	};
		
}]);