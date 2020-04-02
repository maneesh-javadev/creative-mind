app.service('changeEffectiveDateService', [ '$http', function($http) {
	
	this.getEntityDetail = function(DistrictCode) {
		getEntityEffectiveDate= {"entityCode":DistrictCode,  "entityType" : 'D' };
		return $http.post("getEffectiveDate.htm?<csrf:token uri=getEffectiveDate.htm/>",getEntityEffectiveDate);
	};
	
	this.saveEffectiveDate = function(entityList) {
		return $http.post("saveEffectiveDateDistrict.htm?<csrf:token uri=saveEffectiveDateDistrict.htm/>",entityList);
	};
	
	this.getEntityDetailbyVersion = function(getEntityEffectiveDate) {
		return $http.post("getEffectiveDatebyVersion.htm?<csrf:token uri=getEffectiveDatebyVersion.htm/>",getEntityEffectiveDate);
	};
		
}]);