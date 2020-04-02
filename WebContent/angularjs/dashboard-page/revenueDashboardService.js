
app.service('revenueDashboardService', [ '$http', function($http) {
	
	this.getDashboardDetials = function(pStateCode) {
		return $http.post("getDashboardDetials.htm?<csrf:token uri=getDashboardDetials.htm/>",pStateCode);
	};
	
	this.getEntityDetailsFinYearWise = function(finyear) {
		return $http.post("getEntityDetailsFinYearWise.htm?<csrf:token uri=getEntityDetailsFinYearWise.htm/>",finyear);
	};
	
	this.getRevenueDetails = function(pStateCode,flag) {
		getDashboardLBDetails= {"stateCode":pStateCode,"flag":flag};
		return $http.post("getVillageDetails.htm?<csrf:token uri=getVillageDetails.htm/>",getDashboardLBDetails);
	};
	
	
	this.getLBDetails = function(pStateCode,flag) {
		getDashboardLBDetails= {"stateCode":pStateCode,"flag":flag};
		return $http.post("getLBDetails.htm?<csrf:token uri=getLBDetails.htm/>",getDashboardLBDetails);
	};
	
	this.getAllEntityCountList = function() {
		return $http.post("getAllEntityCountList.htm?<csrf:token uri=getAllEntityCountList.htm/>");
	};
	
	this.getStateList = function() {
		return $http.post("getStateListDashboard.htm?<csrf:token uri=getStateListDashboard.htm/>");
	};
	
	this.dashboardChangeEntityDetail = function(pStateCode,flag,finYear) {
		getDashboardChangeEntityDetail= {"stateCode":pStateCode,"flag":flag,"finYear":finYear};
		return $http.post("getEntityChangeDetail.htm?<csrf:token uri=getEntityChangeDetail.htm/>",getDashboardChangeEntityDetail);
	};
	
		
}]);