var shiftEntity = angular.module('shiftEntity', []);

shiftEntity.controller('shiftCntrl', function($scope,$compile,$http) {
	$scope.shiftEntityList=[];
	$scope.shiftedData=[];
	$scope.orderFlag=true;
	$scope.governmentOrder={};
	$scope.targetEntityCode=0;
	$scope.unitLevelCode=0;
	$scope.targetEntityList=[];
	$scope.files = [];
	$scope.parentLevelCode=0;
	$scope.adminUnitListFinal=[];
	$scope.errMessage = '';
	
	
	
	var maxfilesize = 5*1024 * 1024;
	$scope.fileValidation = function(){
		$scope.fileValid = '';
		var file = $('#uploadFile').prop('files');
		console.log(file);
		if(file.length==0){
			$scope.fileValid ='Kindly Attach Approved Doc';
  		   return false;
  	  }
  	  if(file[0].size==0){
  		$scope.fileValid ='Attach a nonzero size approved doc';
  		   return false;
  	  }
  	  if(file[0].size>maxfilesize){
  		$scope.fileValid ="File too large: " + file[0].size + ". Maximum size: " + maxfilesize;
  		   return false;
  	  } 
  	  var ext = $('#uploadFile').val().split('.').pop().toLowerCase();
  	  if($.inArray(ext, ['gif','png','jpg','jpeg','pdf','pjpeg']) == -1) {
  		$scope.fileValid = "Kindly Attach Approved Doc of gif,jpg,pdf,png,jpeg,pjpeg type ";
  		  return false;
  	  }
		return true;
	}
	
	
	
	
	$http.get('createUnitLevelList.htm?<csrf:token uri=createUnitLevelList.htm />').success(function(data) {
		var curData=angular.copy(data);
		  data.splice(0, 2);
		$scope.adminUnitList = data;
		curData.splice(0, 1);
		$scope.adminUnitListFinal=angular.copy(curData);
		
	    })
	    .error(function(error,status){
	    	alert(status);
	  });
	
	$scope.shiftEntity = function(entityobj){
		document.getElementById('dropdown').innerHTML="";
		$scope.shiftEntityList=[];
		$scope.targetEntityList=[];
		  var json = $.parseJSON(entityobj);
		  var sortOrder = json.hierarchyEntrry.sortOrder;
		  $scope.sortOrder = json.hierarchyEntrry.sortOrder;
		  $scope.unitLevelCode = json.adminUnitCode;
		  if(json.hierarchyEntrry.sordetOrr === 2){
			  $http.post('annFetchEntityList.htm?<csrf:token uri=annFetchEntityList.htm />',json.slc).then(
					  function(response){
						  $scope.shiftEntityList = response.data;
		   		          }, 
		   		      function(response){
		   		         console.log("failed");
		   		         });
		  }
		  document.getElementById('dropdown').innerHTML="";
	      angular.forEach($scope.adminUnitListFinal, function(tp,i) {
			  if(tp.hierarchyEntrry.sortOrder < sortOrder){
				  var nextTemp = null;
				  var temp1 = tp.adminLevelNameEng;
				  var adminUnitCode = tp.adminUnitCode;
				  if($scope.adminUnitListFinal.length-1>i){
						 nextTemp= $scope.adminUnitListFinal[i+1].adminLevelNameEng;
						 $scope.fieldLabel = $scope.adminUnitListFinal[i+1].adminLevelNameEng;
					 }
				  $scope[temp1+'List'] = [];
				  $scope[nextTemp+'List'] = [];
				  var drpdwnhtml =  '<div class=form-group><label class="col-sm-3 control-label">Select '+temp1+'<span class="mandatory">*</span></label><div class=col-sm-6 ><select class=form-control ng-model="'+temp1+'dropdown" ng-change="fetchEntityList('+temp1+'List,'+temp1+'dropdown,'+nextTemp+'List)"><option value="0">Please select</option><option ng-repeat="st in '+temp1+'List" value="{{st}}">{{st.adminEntityNameEnglish}}</option></select></div></div>';
				  var temp2 = $compile(drpdwnhtml)($scope);
				  angular.element(document.getElementById('dropdown')).append(temp2);
				  $scope[temp1+'dropdown']='0';
				  if(i===0){
					  $http.post('fetchEntityList.htm?<csrf:token uri=fetchEntityList.htm />',adminUnitCode).then(
						  function(response){
							  $scope[temp1+'List'] = response.data.adminUnitEntities;
			   		          }, 
			   		      function(response){
			   		         console.log("failed");
			   		         });
				  }
		  
			  }
			  });
			  };
	
	 $scope.fetchEntityList = function(currentList,adminUnit,temp1){
		 $scope.shiftEntityList=[];
		 $scope.targetEntityList=[];
		  var json = $.parseJSON(adminUnit);
		  var adminUnitObj = json.adminUnitEntityCode;
		  var j = temp1.length;
		  $scope.selectSourceEntity = adminUnitObj;
		  while (j--) {
			  temp1.pop();
			}
		  $http.post('annFetchEntityList.htm?<csrf:token uri=annFetchEntityList.htm />', adminUnitObj).then(
				  function(response){
					  
					  if(response.data !=null && response.data[0].adminUnitLevelCode === $scope.unitLevelCode ){
						  $scope.shiftEntityList = response.data;
						  $scope.targetEntityList=currentList;
					  }else{
					  var data=response.data;
			        	 angular.forEach(data, function(item) {
			        		 temp1.push(item);
			        		});
					  }
	   		          }, 
	   		      function(response){
	   		         console.log(response);
	   		         }
	   		      	 );
	  };
	
	
	$scope.moveItem = function(items, from, to) {
  		angular.forEach(items, function(item) {
		  var idx = from.indexOf(item);
		  from.splice(idx, 1);
		  to.push(item);
  		});
	};
	
	$scope.removeItem = function(items, from, to) {
  		angular.forEach(items, function(item) {
		  var idx = from.indexOf(item);
		  from.splice(idx, 1);
		  to.push(item);
  		});
	};
	
	$scope.generateGovOrder=function(){
		$scope.orderFlag=false;
	};
	
	
	$scope.holdSelect=function(selectTargetEntity){
		$scope.selectTargetEntity=selectTargetEntity.adminUnitEntityCode;
		$scope.parentLevelCode=selectTargetEntity.adminUnitLevelCode;
	};
	
	
	$scope.disabledFuture=function () {
		 $('[type="date"].min-date').prop('max', function(){
		        return new Date().toJSON().split('T')[0];
		    });
   };
	
   
   $scope.checkErr = function(){
   	$scope.errMessage = '';
       var orderDate=$scope.governmentOrder.orderDate;
       var effectiveDate=$scope.governmentOrder.effectiveDate;
       if(new Date(orderDate) > new Date(effectiveDate)){
         $scope.errMessage = 'EfectiveDate Date should be greate than or equal to order date';
         return false;
       }
       return true;
     };
   
	
	$scope.saveData=function(){
		if($scope.fileValidation() && $scope.checkErr()){
		var formData =new FormData();
		formData.append('file', $("#uploadFile").prop('files')[0]);
		formData.append('deptList',angular.toJson($scope.shiftedData));
		formData.append('govOrder',angular.toJson($scope.governmentOrder));
		formData.append('targetCode',angular.toJson($scope.selectTargetEntity));
		formData.append('sourceCode',angular.toJson($scope.selectSourceEntity));
		formData.append('shiftLevelCode',angular.toJson($scope.unitLevelCode));
		formData.append('parentLevelCode',angular.toJson($scope.parentLevelCode));
		
		$http.post('shiftEntityRecords.htm?<csrf:token uri=shiftEntityRecords.htm />',formData,{transformRequest:angular.identity,headers:{'Content-Type':undefined}})
		 .then(
		         function(response){
		        	alert(response.data.msgid);
		                }, 
		                function(response){
		                	console.log(response);
		                }
		          );
		}
	};
	
	
});


