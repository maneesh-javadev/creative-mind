app.service('changeEffectiveDateService', [ '$http', function($http) {
	
	this.getEntityDetail = function(SdCode) {
		getEntityEffectiveDate= {"entityCode":SdCode,  "entityType" : 'T' };
		return $http.post("getEffectiveDate.htm?<csrf:token uri=getEffectiveDate.htm/>",getEntityEffectiveDate);
	};
	
	this.saveEffectiveDate = function(entityList) {
		return $http.post("saveEffectiveDateSubdistrict.htm?<csrf:token uri=saveEffectiveDateSubdistrict.htm/>",entityList);
	};
	
	this.getEntityDetailbyVersion = function(getEntityEffectiveDate) {
		return $http.post("getEffectiveDatebyVersion.htm?<csrf:token uri=getEffectiveDatebyVersion.htm/>",getEntityEffectiveDate);
	};
		
}]);