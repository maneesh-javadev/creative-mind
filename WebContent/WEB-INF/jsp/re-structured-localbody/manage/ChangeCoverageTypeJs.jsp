<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
 <script type='text/javascript'>
 var _JS_STATE_CODE = '${stateCode}';
 var _JS_DISTRICT_CODE = '${districtId}';
 /**
  * The {@code ready} initialized once page started excuting 
  * and invoke all on load events.
  */ 
  
  var lblist=[];
  var localbodyMap=new Map();
  var changeLRTypeMap =new Map();
 $(document).ready(function() {	
	 
	 $( 'INPUT[id^=btnFormAction]' ).click(function() {
		 saveCoverageMethod();
	 });
	 
	 $('[id^=contributed][id$=_F]').mouseover(function() {
			if(!$(this).attr('checked')){
				var selectedLR=$( this );
				lRCode=parseInt($(this).attr('param'));
				lRType=$(this).attr('paramRegionType');
				
				 dwrRestructuredLocalBodyService.getLocalbodiesCoveredlrlc(lRCode,lRType, {
					 callback : function(result) {
						    localbodyMap.set(lRCode,result);
							
							var usedLbObj=localbodyMap.get(lRCode);				
							if(usedLbObj.length==1){
								
								$(selectedLR).attr('checked','checked');
								setChangeCoverageTypeLR(lRType,lRCode,"F"); 
								
							} else{
								
								 displayUsedLocalbodyTable(lRCode);
							} 
					},		
						errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
						async : true
					});	
				
				
				
			}
					
		});
	 
	 
	 
		 $('[id^=contributed][id$=_P]').click(function() {
			if(!$(this).attr('checked')){
				var selectedLR=$( this );
				lRCode=parseInt($(this).attr('param'));
				lRType=$(this).attr('paramRegionType');	
				$(selectedLR).attr('checked','checked');
				setChangeCoverageTypeLR(lRType,lRCode,"P"); 
			}
					
		});		 
 });
  
  
 var displayUsedLocalbodyTable=function(lRCode){
		
	 $( '#existingLocalBodyDetails > tbody' ).empty();	
	$( '#existingLocalBodyDetails' ).show();	
	jQuery.each(localbodyMap.get(lRCode), function(index, obj) {
		var templateTR = $("<tr/>");
		var templateTDHQId = $("<td/>");
		$( templateTDHQId ).html(obj[0]);			
		var templateTDHQName = $("<td/>");
		$( templateTDHQName ).html(obj[1]);
		$( templateTR ).append( templateTDHQId ).append( templateTDHQName );
		$( '#tbodyExistLB' ).append( templateTR );
	});
	 $('#displayExistingLocalBody').modal('show');	
	
}
 
 var setChangeCoverageTypeLR = function(entityType,entityCode,coverageType) {
 		if(changeLRTypeMap.has(entityCode)){
			changeLRTypeMap.delete(entityCode);
		}else{
			changeLRTypeMap.set(entityCode,entityCode+"#"+entityType+"#"+coverageType);					
		}	
 		
	};  
	
	
	var saveCoverageMethod=function(){
		if(changeLRTypeMap.size>0){
			var changeLRTypeArray=new Array();	
			
			changeLRTypeMap.forEach(function(value, key) {
				changeLRTypeArray.push(value);		
				}, changeLRTypeMap);  
			$( "[id=changeCoverageTypeLRList]" ).val(changeLRTypeArray);
		callActionUrl('saveChangeCoverageTypePublishedLocalBody.htm');
			
		}else{
			alert("Please change at least one coverage to Save");
		}
	}
	
	callActionUrl=function(url){
	 	
	   
	    $( 'form[id=localBodyForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=localBodyForm]' ).attr('method','post');
		$( 'form[id=localBodyForm]' ).submit();
};
	
	
 </script>
