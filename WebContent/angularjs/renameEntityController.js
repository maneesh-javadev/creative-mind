renameEntity.controller('renameEntityController', function($scope,$http,$parse,$compile,$window,$modalInstance,entity,GovOrder,fieldLabel,draftFlag) {
	$scope.updateName='';
	$scope.entity = entity;
	$scope.GovOrder = GovOrder;
	$scope.fieldLabel = fieldLabel;
	$scope.fileValid = '';
	$scope.savedResult = '';
	$scope.failedResult = '';
	$scope.form = [];
	$scope.draftFlag = draftFlag;
	$scope.curDate='';
	$scope.errMessage = '';
	$scope.oldEntity = angular.copy(entity);
	  $scope.opts = {
			    dateFormat: 'dd/mm/yy',
			    changeMonth: true,
			    changeYear: true
			  };
	
	var maxfilesize = 5*1024 * 1024;
	$scope.files = [];
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
	
	$scope.disabledFuture=function () {
		 $('[type="date"].min-date').prop('max', function(){
		        return new Date().toJSON().split('T')[0];
		    });
    };
    
    
    $scope.checkErr = function(){
    	$scope.errMessage = '';
    	$scope.updateName='';
        var orderDate=$scope.GovOrder.orderDate;
        var effectiveDate=$scope.GovOrder.effectiveDate;
        if(new Date(orderDate) > new Date(effectiveDate)){
          $scope.errMessage = 'EfectiveDate Date should be greate than or equal to order date';
          return false;
        }
        
        if($scope.oldEntity.adminEntityNameEnglish === $scope.entity.adminEntityNameEnglish){
        	 $scope.updateName = 'Please change the Name(In English)';
             return false;
        }
        return true;
      };
	
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	    };
	
	$scope.renameEntity = function(publishFlag){
		console.log(publishFlag);
		if($scope.fileValidation() && $scope.checkErr()){
			var file = $('#uploadFile').prop('files');
		var formdata = new FormData();
			formdata.append('file', file[0]);
		$scope.entity.isPublishOrDraft = publishFlag;
		var entity = $scope.entity;
		console.log($scope.GovOrder);
		formdata.append('entity',angular.toJson($scope.entity,true));
		formdata.append('govOrder',angular.toJson($scope.GovOrder,true));
		 $http.post('renameEntityForm.htm?<csrf:token uri=renameEntityForm.htm />', formdata,
				 {
			 transformRequest: angular.identity,
			 headers: { 'Content-Type' : undefined}
			 }
				 )
		 .then(
		         function(response){
		         // success callback
		        	 console.log("success");
		        	$scope.serverErrors=response.data;
		        	/*angular.forEach(response.data,function(errorKey){*/
		        	if(response.data['code'] === '200'){
		        		console.log(response.data);
		        		if($scope.entity.isPublishOrDraft == 'D'){
		        			$scope.savedResult = "Data has been saved successfully in Draft!";
		        		}
		        		if($scope.entity.isPublishOrDraft == 'P' || $scope.entity.isPublishOrDraft == 'A'){
		        			$scope.savedResult = "Data has been Published successfully!";
		        		}
		        		
		        		}
		        	
		        	if(response.data['code'] === '999'){
		        		console.log(response.data);
		        		$scope.failedResult = "Rename Entity is failed!";
		        		}
		        	
		        	if(response.data['code'] === '900'){
		        	$scope.form['attachFile1'] = '';
    				for (var key in response.data) {
    					console.log(key + ':' + response.data[key]);
    					if(key === 'attachFile1')
    						$scope.fileValid = response.data[key];
    					$scope.form[key].$dirty=true;
    				};
		        	}
		                }, 
		                function(response){
		                	console.log("failed");
		                	console.log(response.data);
		         // failure callback
		                }
		          );
	}
	 
	}
	
	
	
	$scope.renameEntityP = function(publishFlag){
		var formdata = new FormData();
		$scope.entity.isPublishOrDraft = publishFlag;
		var entity = $scope.entity;
		console.log($scope.GovOrder);
		formdata.append('entity',angular.toJson($scope.entity,true));
		formdata.append('govOrder',angular.toJson($scope.GovOrder,true));
		 $http.post('renameEntityFormP.htm?<csrf:token uri=renameEntityFormP.htm />', formdata,
				 {
			 transformRequest: angular.identity,
			 headers: { 'Content-Type' : undefined}
			 }
				 )
		 .then(
		         function(response){
		         // success callback
		        	 console.log("success");
		        	$scope.serverErrors=response.data;
		        	/*angular.forEach(response.data,function(errorKey){*/
		        	if(response.data['code'] === '200'){
		        		console.log(response.data);
		        		$scope.savedResult = "Data has been saved successfully!";
		        		}
		        	
		        	if(response.data['code'] === '999'){
		        		console.log(response.data);
		        		$scope.failedResult = "Rename Entity is failed!";
		        		}
		        	
		        	if(response.data['code'] === '900'){
		        	$scope.form['attachFile1'] = '';
    				for (var key in response.data) {
    					console.log(key + ':' + response.data[key]);
    					if(key === 'attachFile1')
    						$scope.fileValid = response.data[key];
    					$scope.form[key].$dirty=true;
    				};
		        	}
		                }, 
		                function(response){
		                	console.log("failed");
		                	console.log(response.data);
		         // failure callback
		                }
		          );
	 
	}
	
    });

/*renameEntity.directive("fileInput",['$parse',function($parse){
    return{
        restrict:'A',
        link:function(scope,ele,attrs){
            ele.bind('change',function(){
            	console.log(scope,ele[0].files);
                $parse(attrs.fileInput).
                assign(scope,ele[0].files)
                scope.$apply()
            });
        }
    }
}]);*/

renameEntity.directive('myDatePicker', function () {
    return {
        restrict: 'A',
        require: '?ngModel',
        link: function (scope, element, attrs, ngModelController) {
  
            // Private variables
            var datepickerFormat = 'm/d/yyyy',
                momentFormat = 'M/D/YYYY',
                datepicker,
                elPicker;
  
            // Init date picker and get objects http://bootstrap-datepicker.readthedocs.org/en/release/index.html
            datepicker = element.datepicker({
                autoclose: true,
                keyboardNavigation: false,
                todayHighlight: true,
                format: datepickerFormat
            });
            elPicker = datepicker.data('datepicker').picker;
  
            // Adjust offset on show
            datepicker.on('show', function (evt) {
                elPicker.css('left', parseInt(elPicker.css('left')) + +attrs.offsetX);
                elPicker.css('top', parseInt(elPicker.css('top')) + +attrs.offsetY);
            });
  
            // Only watch and format if ng-model is present https://docs.angularjs.org/api/ng/type/ngModel.NgModelController
            if (ngModelController) {
                // So we can maintain time
                var lastModelValueMoment;
  
                ngModelController.$formatters.push(function (modelValue) {
                    //
                    // Date -> String
                    //
  
                    // Get view value (String) from model value (Date)
                    var viewValue,
                        m = moment(modelValue);
                    if (modelValue && m.isValid()) {
                        // Valid date obj in model
                        lastModelValueMoment = m.clone(); // Save date (so we can restore time later)
                        viewValue = m.format(momentFormat);
                    } else {
                        // Invalid date obj in model
                        lastModelValueMoment = undefined;
                        viewValue = undefined;
                    }
  
                    // Update picker
                    element.datepicker('update', viewValue);
  
                    // Update view
                    return viewValue;
                });
  
                ngModelController.$parsers.push(function (viewValue) {
                    //
                    // String -> Date
                    //
  
                    // Get model value (Date) from view value (String)
                    var modelValue,
                        m = moment(viewValue, momentFormat, true);
                    if (viewValue && m.isValid()) {
                        // Valid date string in view
                        if (lastModelValueMoment) { // Restore time
                           /* m.hour(lastModelValueMoment.hour());
                            m.minute(lastModelValueMoment.minute());
                            m.second(lastModelValueMoment.second());
                            m.millisecond(lastModelValueMoment.millisecond());*/
                        }
                        modelValue = m.toDate();
                    } else {
                        // Invalid date string in view
                        modelValue = undefined;
                    }
  
                    // Update model
                    return modelValue;
                });
  
                datepicker.on('changeDate', function (evt) {
                    // Only update if it's NOT an <input> (if it's an <input> the datepicker plugin trys to cast the val to a Date)
                    if (evt.target.tagName !== 'INPUT') {
                        ngModelController.$setViewValue(moment(evt.date).format(momentFormat)); // $seViewValue basically calls the $parser above so we need to pass a string date value in
                        ngModelController.$render();
                    }
                });
            }
  
        }
    };
});	 


renameEntity.directive('restrictInput', function() {
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function(scope, element, attr, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue) {
	        var options = scope.$eval(attr.restrictInput);
	        if (!options.regex && options.type) {
	          switch (options.type) {
	            case 'digitsOnly': options.regex = '^[0-9]*$'; break;
	            case 'lettersOnly': options.regex = '^[a-zA-Z]*$'; break;
	            case 'lowercaseLettersOnly': options.regex = '^[a-z]*$'; break;
	            case 'uppercaseLettersOnly': options.regex = '^[A-Z]*$'; break;
	            case 'lettersAndDigitsOnly': options.regex = '^[a-zA-Z0-9]*$'; break;
	            case 'validPhoneCharsOnly': options.regex = '^[0-9 ()/-]*$'; break;
	            default: options.regex = '';
	          }
	        }
	        var reg = new RegExp(options.regex);
	        if (reg.test(viewValue)) { //if valid view value, return it
	          return viewValue;
	        } else { //if not valid view value, use the model value (or empty string if that's also invalid)
	          var overrideValue = (reg.test(ctrl.$modelValue) ? ctrl.$modelValue : '');
	          element.val(overrideValue);
	          return overrideValue;
	        }
	      });
	    }
	  };
	});