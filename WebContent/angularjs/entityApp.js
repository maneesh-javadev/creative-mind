var entityform = angular.module('entityform', [ 'ui.grid','ui.bootstrap']);

entityform.controller('entityCtrl', function($scope, $http,$compile,$modal) {
	$scope.orderFlag=true;
	$scope.entity = {};
	$scope.governmentOrder = {};
	$scope.categoryList=[];
	var map = new Object();
	$scope.saveEntity = function(){
		$scope.entity.sortOrder = $scope.sortOrder;
		angular.forEach($scope.adminUnitList, function(item) {
			if(item.hierarchyEntrry.sortOrder < $scope.sortOrder){
				map[item.adminLevelNameEng] = $scope[item.adminLevelNameEng+'dropdown'];
			}
			else{
				map[item.adminLevelNameEng] = $scope['cont'+item.adminLevelNameEng+'List'];
			}
		});
		var formData =new FormData();
		formData.append('entity',angular.toJson($scope.entity));
		formData.append('govOrder',angular.toJson($scope.governmentOrder));
		formData.append('file',$('#attchmentFile').prop('files')[0]);
		formData.append('map',angular.toJson(map));
		 $http.post('createEntityForm.htm?<csrf:token uri=createEntityForm.htm />',formData,{transformRequest:angular.identity,headers:{'Content-Type':undefined}})
		 .then(
		         function(response){
		         // success callback
		        	console.log(response);
		                }, 
		                function(response){
		                	console.log(response);
		                }
		          );
		
	};
	
	$scope.getNextList = function(currTemp,upcomingTemp) {
		console.log(currTemp+upcomingTemp);
		 $scope.getList =currTemp.filter(function (temp) {
			    return (temp.operation_state == 'P');
			});
		 $http.post('getDepartmentListBsdPrnt.htm?<csrf:token uri=getDepartmentListBsdPrnt.htm />', $scope.getList)
		 .then(
		         function(response){
		         // success callback
		        	 var data=response.data;
		        	 angular.forEach(data, function(item) {
		        		 upcomingTemp.push(item);
		        		});
		        	 
		                }, 
		                function(response){
		         // failure callback
		                }
		          );
		  };
		
		  
		  $scope.getFullSubList = function(currTemp,upcomingTemp) {
			  $scope.getList =currTemp.filter(function (temp) {
			    return (temp.operation_state == 'F');
			  });
			   $http.post('getDepartmentListBsdPrnt.htm?<csrf:token uri=getDepartmentListBsdPrnt.htm />', $scope.getList)
			   .then(
			         function(response){
			        	 var data=response.data;
				         angular.forEach(data, function(item) {
				        	 upcomingTemp.push(item);
				        	 console.log("asdff"+upcomingTemp);
				          });
				     }, 
				     function(response){
	                 }
				     );
		 };
	$scope.moveItem = function(items, from, to) {
		angular.forEach(items, function(item) {
		var idx = from.indexOf(item);
		from.splice(idx, 1);
		item.adminEntityNameEnglish = item.adminEntityNameEnglish.substring(0, item.adminEntityNameEnglish.length - 6);
		to.push(item);
	});
    // clear selection
    $scope.available = "";
    $scope.selected = "";
	};
	$scope.moveItemWhole = function(items, from, to) {
		angular.forEach(items, function(item) {
		var idx = from.indexOf(item);
		from.splice(idx, 1);
		item.adminEntityNameEnglish = item.adminEntityNameEnglish+"(Full)";
		item.operation_state="F";
		to.push(item);
	});
    // clear selection
    $scope.available = "";
    $scope.selected = "";
  };
  	$scope.moveItemPart = function(items, from, to) {
  		angular.forEach(items, function(item) {
		  var idx = from.indexOf(item);
		  from.splice(idx, 1);
		  item.adminEntityNameEnglish = item.adminEntityNameEnglish+"(Part)";
		  item.operation_state="P";
		  to.push(item);
  		});
  		// clear selection
		$scope.available = "";
		$scope.selected = "";
	};
	$scope.moveAll = function(from, to) {
		angular.forEach(from, function(item) {
			item.name = item.adminEntityNameEnglish.substring(0, item.adminEntityNameEnglish.length - 6);
			to.push(item);
	    	});
	    from.length = 0;
	  };
		  
		    //Let's say you have element with id 'foo' in which you want to create a button
	  $http.get('createUnitLevelList.htm?<csrf:token uri=createUnitLevelList.htm />').success(function(data) {
		  data.splice(0, 1);
		$scope.adminUnitList = data;
		
	    })
	    .error(function(error,status){
	    	alert(status);
	  });

	  $scope.fetchEntityList = function(adminUnitObj,temp1){
		  $http.post('annFetchEntityList.htm?<csrf:token uri=annFetchEntityList.htm />', adminUnitObj).then(
				  function(response){
					  var data=response.data;
			        	 angular.forEach(data, function(item) {
			        		 temp1.push(item);
			        		});
	   		          }, 
	   		      function(response){
	   		         }
	   		      	 );
	  }
		   
	  $scope.createform = function(entityobj){
		  var json = $.parseJSON(entityobj);
		  $scope.categoryList=[];
		  var sortOrder = json.hierarchyEntrry.sortOrder;
		  $scope.sortOrder = json.hierarchyEntrry.sortOrder;
		  document.getElementById('entityForm').innerHTML="";
		  document.getElementById('dropdown').innerHTML="";
		  var count=0;
	      angular.forEach($scope.adminUnitList, function(tp,i) {
			  if(tp.hierarchyEntrry.sortOrder < sortOrder){
				  var nextTemp = null;
				  var temp1 = tp.adminLevelNameEng;
				  var adminUnitCode = tp.adminUnitCode;
				  if($scope.adminUnitList.length-1>i){
						 nextTemp= $scope.adminUnitList[i+1].adminLevelNameEng;
					 }
				  $scope[temp+'List'] = [];
				  $scope[nextTemp+'List'] = [];
				  var drpdwnhtml =  '<div class=form-group><label class="col-sm-3 control-label">Select '+temp1+'*</label><div class=col-sm-6 ><select class=form-control ng-model="'+temp1+'dropdown" ng-change="fetchEntityList('+temp1+'dropdown,'+nextTemp+'List)"> <option ng-repeat="st in '+temp1+'List" value="{{st.adminUnitEntityCode}}">{{st.adminEntityNameEnglish}}</option></select></div></div>';
				  var temp2 = $compile(drpdwnhtml)($scope);
				  angular.element(document.getElementById('dropdown')).append(temp2);
				  if(i===0){
					  $http.post('fetchEntityList.htm?<csrf:token uri=fetchEntityList.htm />',adminUnitCode).then(
							  function(response){
								  $scope.listdata = response.data.adminUnitEntities;
								  $scope[temp1+'List'] = $scope.listdata;
								  $scope.categoryList = response.data.adminUnitCategories;
				   		          }, 
				   		      function(response){
				   		         console.log("failed");
				   		         }
				   		      	 );
				  }
			 }
			 if(tp.hierarchyEntrry.sortOrder >= sortOrder){
				 var temp = tp.adminLevelNameEng;
				 var order = tp.hierarchyEntrry.sortOrder;
				 var upcomingTemp=[];
				 $scope[temp+'Flag'] = false;
				 $scope[temp+'List'] = [];
				 $scope['cont'+temp+'List'] = [];
				 $scope[upcomingTemp+'List'] = [];
				 if($scope.adminUnitList.length-1>i){
					 upcomingTemp= $scope.adminUnitList[i+1].adminLevelNameEng;
				 }
				 var btnhtml = '<div class="ms_container row" style="margin-left: 10px;"><div class="ms_selectable col-sm-5 form-group"><label>Contributing '+temp+': </label><select class=form-control name="contributingState" id="contributingState" ng-model="'+temp+'available" multiple="true" ng-options="state.adminEntityNameEnglish for state in '+temp+'List"></select></div><div class="ms_buttons col-sm-2"><br><br><button class="btn btn-primary btn-xs btn-block" ng-click="moveItemWhole('+temp+'available,'+temp+'List,cont'+temp+'List)" >Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button><button class="btn btn-primary btn-xs btn-block" ng-click="moveAll(cont'+temp+'List,'+temp+'List)" ><i class="fa fa-angle-double-left" aria-hidden="true"></i></button><button class="btn btn-primary btn-xs btn-block" ng-click="moveItemPart('+temp+'available,'+temp+'List,cont'+temp+'List)" >Part<i class="fa fa-angle-double-right" aria-hidden="true"></i></button></div><div class="ms_selection col-sm-5">'
					 			+'<div class="form-group"><label>Contributing '+temp+' List: </label><select name="contributing'+temp+'List"  class=form-control  ng-model="'+temp+'selected"  multiple="true" ng-options="contState.adminEntityNameEnglish for contState in cont'+temp+'List"></select>';
								 if(($scope.adminUnitList.length-1)>i){
					 				btnhtml+='<button type="button" class="btn btn-primary" ng-click="getNextList('+temp+'selected,'+upcomingTemp+'List)">GET List For Child</button>';
								 }
								 btnhtml+='<button type="button" class="btn btn-primary" ng-if="'+temp+'Flag" ng-click="getNewChildEntity(cont'+upcomingTemp+'List,'+order+')">Create New '+temp+'</button>'
				 				btnhtml+='</div></div></div>';
				 				if(tp.hierarchyEntrry.sortOrder != sortOrder && count===2){
				 					btnhtml+='<div class="ms_container row"><div class="ms_selection col-sm-5 col-sm-offset-7"><div class="form-group"><button type="button" class="btn btn-primary" ng-click="mergeEntity(cont'+$scope.adminUnitList[i-count].adminLevelNameEng+'List,cont'+temp+'List,cont'+$scope.adminUnitList[i-1].adminLevelNameEng+'List,'+i+')">Merge '+$scope.adminUnitList[i].adminLevelNameEng+' Into '+$scope.adminUnitList[i-1].adminLevelNameEng+'</button></div></div></div>';
				 					count=1;
				 				}		
				 				count++;	

				 var tempi = $compile(btnhtml)($scope);
				 	angular.element(document.getElementById('entityForm')).append(tempi);
				 	if(tp.hierarchyEntrry.sortOrder > sortOrder && i<$scope.adminUnitList.length-1){
						 $scope[temp+'Flag'] = true;
				 	}
				 	
				 	if(tp.hierarchyEntrry.sortOrder === 2){
				 		$http.post('fetchEntityList.htm?<csrf:token uri=fetchEntityList.htm />',json.adminUnitCode).then(
			                function(response){
			                	$scope[temp+'List'] = response.data.adminUnitEntities;
			                	$scope['cont'+temp+'List'] = [];
			                	$scope[upcomingTemp+'List'] = [];
			   		         }, 
			   		         function(response){
			   		        	 console.log("failed");
			   		         }
			   		    );
				   }
			  }
		});
	};
	
	$scope.getNewChildEntity = function (contSubList,order) {
		console.log(contSubList);
		console.log(order);
		var parent = null;
		var child = null;
		angular.forEach($scope.adminUnitList, function(tp,i) {
		if(tp.hierarchyEntrry.sortOrder == order){
			parent= $scope.adminUnitList[i].adminLevelNameEng;
			child= $scope.adminUnitList[i+1].adminLevelNameEng;
		 }
		});
		console.log(parent);
		console.log(child);
		
		$scope.availableList = angular.copy(contSubList);;
		var modalInstance = $modal.open({
            templateUrl: 'angularjs/createChildEntity.html', 
            controller: 'childEntityController',
            resolve: {
            	list: function () {
                    return $scope.availableList;
                },
                parent: function () {
                    return parent;
                },
                child: function () {
                    return child;
                }
            }
        });
		
		modalInstance.result.then(function (entity) {
		      console.log(entity);
		      map['new'+entity.parent+'|'+entity.newChild] = entity.selectedEntity;
		    }, function () {
		      $log.info('Modal dismissed at: ' + new Date());
		    });
		
    }; 
    
    $scope.mergeEntityObjects=[];
    $scope.mergeIntoList=[];
    $scope.mergeEntity = function (mergeFull,mergeFrom,mergeInto,index) {
    	$scope.mergeIntoList=[];
    	$scope.mergeIntoList=angular.copy(mergeInto);
    	$scope.mergeEntityObjects=[];
    	angular.forEach($scope.adminUnitList, function(tp,i) {
    		if(i == index){
    			mergeFrom[0].parentAdminUnitEntityName= $scope.adminUnitList[i].adminLevelNameEng;
    			mergeInto[0].parentAdminUnitEntityName= $scope.adminUnitList[i-1].adminLevelNameEng;
    		 }
    		});
    	$scope.getFullSubList(angular.copy(mergeFull),$scope.mergeIntoList);
    	$scope.mergeEntityObjects.push(angular.copy(mergeFrom));
    	$scope.mergeEntityObjects.push($scope.mergeIntoList);
		var modalInstance = $modal.open({
            templateUrl: 'angularjs/mergeEntity.html', 
            controller: 'mergeEntityController',
            resolve: {
            	mergeEntityObjects: function () {
                    return $scope.mergeEntityObjects;
                }
                }
            });
        };
        
        $scope.generateGovOrder = function(){
        	$scope.orderFlag=false;
        }
		 
});
