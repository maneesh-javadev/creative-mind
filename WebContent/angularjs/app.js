angular.module(
  'wowUIGrid', 
  [
  'ui.grid', 
  'ui.grid.cellNav', 
  'ui.grid.edit', 
  'ui.grid.resizeColumns', 
  'ui.grid.pinning', 
  'ui.grid.selection', 
  'ui.grid.moveColumns'
  ]
);

angular.module('wowUIGrid').controller('MainCtrl', MainCtrl);

MainCtrl.$inject = ['$scope', '$http', 'uiGridConstants'];



function MainCtrl($scope, $http, uiGridConstants) {
	
	$scope.adminUnitCode=0;
	$scope.sortOrder=0;
	$scope.subDisCode=0;
	$scope.parentAdminCode=0;
	$scope.typeChange="";
	
	$http.get('createUnitLevelList.htm?<csrf:token uri=unitLevelList.htm />').success(function(data) {
		$scope.adminUnitList = data;
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
	$scope.personalDetail = [
        {
            'adminEntityNameEnglish':'',
            'adminEntityNameLocal':'',
            'parentAdminUnitEntityCode':'',
            'adminUnitLevelCategoryCode':''
        },
        ];
  
  $scope.addNew = function(personalDetail){
         $scope.personalDetail.push({ 
        	   'adminEntityNameEnglish': '', 
        	   'adminEntityNameLocal': '',
        	   'parentAdminUnitEntityCode': $scope.districts[0].districtCode,
        	   'adminUnitLevelCategoryCode': ''
         }
         );
  };
  
  $scope.remove = function(){
      var newDataList=[];
      $scope.selectedAll = false;
      angular.forEach($scope.personalDetail, function(selected){
          if(!selected.selected){
              newDataList.push(selected);
          }
      }); 
      $scope.personalDetail = newDataList;
  };
  

  
  
  
  
  $scope.saveData = function(status) {
	  
	     
	     var adminUnitCode = $scope.adminUnitCode;
	     
	     $scope.personalDetail = $scope.personalDetail.filter(function (personalDetail) {
			    return (personalDetail.adminEntityNameEnglish != ' ');
			});
	     var adminUnitEntities = $scope.personalDetail;
	     $http.post('createUnitLevelList.htm?<csrf:token uri=createUnitLevelList.htm />', adminUnitEntities, {params: {"adminUnitCode":$scope.adminUnitCode,"status":status} })
	          .then(
	                function(response){
	         // success callback
	                	alert("New Entity has Been Created Sucessfully");
	                	$scope.adminUnitList = response.data.deptAdminUnits;
	                }, 
	                function(response){
	         // failure callback
	                	alert("Error while creating new Entity");
	                }
	          );
	  };
	  
	  
	  $scope.saveParentData = function(status) {
		  
		  $scope.parentChange = $scope.parentChange.filter(function (parentChange) {
			    return (parentChange.parentAdminUnitEntityCode != 0);
			});
		     var adminUnitEntities = $scope.parentChange;
		     var adminUnitCode = $scope.adminUnitCode;
		     
		     $http.post('createUnitLevelListVill.htm?<csrf:token uri=createUnitLevelListVill.htm />', adminUnitEntities, {params: {"adminUnitCode":$scope.adminUnitCode,"status":status,"typeChange":$scope.typeChange} })
		          .then(
		                function(response){
		         // success callback
		                	alert("Parent of Entity has Been updated Sucessfully");
		                	$scope.adminUnitList = response.data.deptAdminUnits;
		                }, 
		                function(response){
		         // failure callback
		                	alert("Error while updating Parent of Entity");
		                }
		          );
		  };
	  
  	$scope.getData = function(adminUnitCode,sortOrder,typeChange,parentAdminCode) {
  		$scope.personalDetail = [
  		                       {
  		                           'adminEntityNameEnglish':'',
  		                           'adminEntityNameLocal':'',
  		                           'parentAdminUnitEntityCode':'',
  		                           'adminUnitLevelCategoryCode':''
  		                       },
  		                       ];
	       $scope.adminUnitCode=adminUnitCode;
	  	   $scope.sortOrder=sortOrder;
	  	   $scope.typeChange=typeChange;
	  	   $scope.parentAdminCode=parentAdminCode;
	  	 
	  	   $http.post('unitLevelList.htm?<csrf:token uri=unitLevelList.htm/>',adminUnitCode,{params: {"sortOrder":$scope.sortOrder,"typeChange":$scope.typeChange,"parentAdminCode":$scope.parentAdminCode}}).then(function(rest) {
	  		   	 $scope.categorys=rest.data.categoryList;
	             $scope.districts=rest.data.departmentAdminFormsList;
	             $scope.adminUnitEntities=rest.data.adminUnitEntities;
	             $scope.subDistrictList=rest.data.subDistrictList;
	             
	             
	             if($scope.adminUnitCode === 2){
	            	 $scope.parentChange=rest.data.adminUnitEntities;
	            	 $scope.departmentAdminFormsList=rest.data.departmentAdminFormsList;
	             }
	             if($scope.adminUnitCode === 3){
	            	 $scope.parentChange=rest.data.subDistrictList;
	            	 $scope.departmentAdminFormsList=rest.data.departmentAdminFormsList;
	             }
	             if($scope.adminUnitCode === 4){
	            	 $scope.departmentAdminFormsList=rest.data.departmentAdminFormsList;
	             }
	             if($scope.districts.length ===1)
	             $scope.personalDetail[0].parentAdminUnitEntityCode=$scope.districts[0].districtCode;
	             
	       });
	}; 
		  
	$scope.getVillage = function(subDisCode) {
		   $scope.subDisCode=subDisCode;
		   $scope.parentChange="";
		   $http.post('getVillageList.htm?<csrf:token uri=getVillageList.htm/>',subDisCode,{params: {"adminUnitCode":$scope.adminUnitCode }}).then(function(rest) {
		         $scope.parentChange=rest.data;
		  });
	}; 	  
		  
		  
}



