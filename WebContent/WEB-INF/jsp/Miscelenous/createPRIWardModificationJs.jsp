<c:choose>
	<c:when test="${PANCHAYAT_TYPE eq 'P'}">
		<c:set var="formTitle" value="label.create.n.manage.pri.ward"></c:set>
	</c:when>
	<c:when test="${PANCHAYAT_TYPE eq 'T'}">
		<c:set var="formTitle" value="label.create.n.manage.tri.ward"></c:set>
	</c:when>
	<c:when test="${PANCHAYAT_TYPE eq 'U'}">
		<c:set var="formTitle" value="label.create.n.manage.urban.ward"></c:set>
	</c:when>
</c:choose>

<link href="<%=contextpthval%>/resources-localbody/css/localbody.css"	rel="stylesheet" type="text/css" ></link>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" ></link>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" ></link> 
<link href="<%=contextpthval%>/datatable/demo_table_jui.css"	rel="stylesheet" type="text/css" > </link>-->
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js' > </script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrWardService.js' > </script>


 <script type='text/javascript' src="js/govtorder.js" ></script>
 <script type='text/javascript' src="js/common.js" ></script> 
<script type='text/javascript' src="js/new_ward.js" ></script>
<script type='text/javascript' src="js/validationWard.js" ></script>
<script type='text/javascript' src="js/validation.js" ></script>
<!-- <script type='text/javascript' src="js/successMessage.js" ></script>
<script type='text/javascript' src="js/helpMessage.js" ></script> -->
<script type='text/javascript' src="js/wardAfterPost.js" > </script>

<script>

$(document).ready(function() {
	
	$( "#actionFetchWardDetails" ).click(function() {
		if(checkNotUrbanProcess()){
			var lbTypeHierarchylement = $( '#lbTypeHierarchy' );
			//alert("lbTypeHierarchylement"+$( lbTypeHierarchylement ).val());
				if( $_checkEmptyObject($( lbTypeHierarchylement ).val()) ){
					$(lbTypeHierarchylement).addClass("error");
					$( '#errorLbTypeHierarchy' ).text("<spring:message code='error.CHOOSEHIERARCHY' htmlEscape='true'/>");
					return false;
				}
		}
		var localBodyTypeElement = $( '#localBodyType' );
		var selectedlocalBodyType = $( localBodyTypeElement ).val();
		if( $_checkEmptyObject(selectedlocalBodyType) ){
			$(localBodyTypeElement).addClass("error");
			$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
			return false;
		}
		
			var element = $( '[name = localBodyLevelCodes]' );
			var localBodyElement = $( element )[$( element ).length - 1];
			if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
				return false;
			}
		
		callActionUrl('viewWardLocalNamePRIAndTRI.htm','localGovtBodyForm');
			
	 });
	
	$( "#actionSearchClose" ).click(function() {
		callActionUrl('home.htm','localGovtBodyForm');
	});
	 
	 if(isParseJson('${showTable}')){
		    $('#viewData').show();
		    $('#btnpanel').show();
		    $("#demand tr:odd").css("background-color", "#ffffff");
			$("#demand tr:even").css("background-color", "#dedede");
		//	$("#trhead").css("background-color", "#");
			
			
		 }
	 
/* 	$('#').dataTable({
		"sScrollY": "200px",
		"bScrollCollapse": true,
		"bPaginate": true,
		"aoColumnDefs": [
			{ "sWidth": "10%", "aTargets": [ -1 ] }
		],
		"bJQueryUI": true,
		"aaSorting": [],
		"aoColumns": [
						null,
						null,
						null,
						{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			{ "bSortable": false },
			  			],
		"sPaginationType": "full_numbers"
	}); */
	
});



function saveEnabledvalues(){
	
	var table1 = document.getElementById('demand');
	var lengthofEnable='${fn:length(wardList)}';
  	var size=lengthofEnable;
  	var array_tableData = ""; 
  	var array_newWardData="";
  	var array_DeleteWardData="";
  	var table = document.getElementById('demand');	 
  	var tablelength = table.rows.length;
  	var actualsize=tablelength-1;
  	var status=false;
  	var erroFlag=false;
  	  
  	if(tablelength > lengthofEnable){
  	    if(actualsize >lengthofEnable){
  	    	var totallength=tablelength-lengthofEnable;
    		 --totallength;
         	 var startid =++lengthofEnable;
  		     
         	 for(var k=0;k<totallength;k++){
  		        var wardName;
        	    	if(wardName==''){
        	    	$('#wardname'+startid).addClass("error");
        	    	status=true;
        	    	}
     	        var wardnumber  = 	$('#wardnumber'+startid).val();
     	        	if(wardnumber==''){
        	    	$('#wardnumber'+startid).addClass("error");
        	    	status=true;
        	    	}
     	        var wardnamelocal = $('#wardnamelocal'+startid).val();	
     	        var wardnamelocalOld = $('#wardnamelocalOld'+startid).val();	

     	        var wardVersion = $('#wardVersion'+startid).val();	
     	       var wardCode = $('#wardCode'+startid).val();	
              if(wardnamelocal != wardnamelocalOld)
            	  {
     	        wardName =  wardCode;
     	        wardName += "#" + wardnamelocal;
      	        wardName += "#" +wardVersion;

     	        wardName += ",";
            	  }
     	       array_newWardData+= wardName;
        	   startid++;
  		     }
  		        
  		     $('#newWardList').val(array_newWardData);
  	    	 
  	      }
  	    	  
  	 }
  	
	 for(var i=1;i<=size;i++){
		  
			    var wardEditInfo;
     	        var wardName =   $('#wardname'+i).val();
     	       if(wardName==''){
        	    	$('#wardname'+i).addClass("error");
        	    	status=true;
        	    }
     	        var wardnumber = $('#wardnumber'+i).val();
     	       if(wardnumber==''){
        	    	$('#wardnumber'+i).addClass("error");
        	    	status=true;
        	    }
     	        var wardnamelocal =   $('#wardnamelocal'+i).val();
     	       var wardVersion = $('#wardVersion'+i).val();	
    	        var wardnamelocalOld = $('#wardnamelocalOld'+i).val();	

    	       var wardCode = $('#wardCode'+i).val();	
    	  
               if(wardnamelocal != wardnamelocalOld)
            	   {

    	        wardEditInfo = wardCode;
    	        wardEditInfo += "#" + wardVersion;
     	        wardEditInfo += "#" + wardnamelocal;
     	        wardEditInfo += ",";
		 		array_tableData+= wardEditInfo;
            	   }	    	       
        }
  		 
	    $('#formNominations').val(array_tableData);
  	    for(var i=1;i<=size;i++) {
  			   if($('#delete'+i).is(":checked")){
  				    var wardEditInfo;
  	     	        var wardName =   $('#wardname'+i).val();
		     	       if(wardName==''){
		        	    	$('#wardname'+i).addClass("error");
		        	    	status=true;
		        	    }
		  	     	        var wardnumber = $('#wardnumber'+i).val();
		     	       if(wardnumber==''){
		        	    	$('#wardnumber'+i).addClass("error");
		        	    	status=true;
		        	    }
		     	        var wardnamelocal =   $('#wardnamelocal'+i).val();
		     	        var wardVersion = $('#wardVersion'+i).val();	
		     	       var wardCode = $('#wardCode'+i).val();	

		    	        wardEditInfo = wardCode;
		     	        wardEditInfo =  wardnumber;
		     	        wardEditInfo += "#" + wardVersion;
		     	        wardEditInfo += "#" + wardnamelocal;
		     	        wardEditInfo += ",";
		 		
     	       array_DeleteWardData+= wardEditInfo;
  			   }			    	       
  	       }
  	    
		  $('#deleteWardList').val(array_DeleteWardData);
  		  if(status){
  	    	   showDialogBox("Kindly add Required Fields");
  	    	   erroFlag=true;
  	       }else{
  	 		   var difference= tablelength-lengthofEnable;
  	 		   if(difference==1){
  	 			  if(array_tableData=""){
  	 				 showDialogBox("Kindly add or Modified any Field First");
  	 				 erroFlag=true;
  	 			  }
  	 		   }else{
  	 			  if(array_newWardData==""){
  	 				 showDialogBox("Kindly add or Modified any Field First");
  	 				 erroFlag=true;
  	 			  }
  	 		   }
  	       }
  	 		 
  	 		if(!erroFlag){
  	 		   var tablelength1 = table1.rows.length;
  	 		   var duplicateStaus=false,duplicateDraftStaus=false;
  	 		   var wardNames="";
  	 		   if(tablelength1>2){
  	 		  	  for(var k=1;k<tablelength1;k++){
  	 		  		  var wardnumber  = 	$('#wardnumber'+k).val();
  	 		  		  var wardName 	=  	$('#wardname'+k).val();
  	 		  		
  	 			           for(var l=(k+1);l<tablelength1;l++){
  	 			        	         var wardnumbercheck=$('#wardnumber'+l).val();
  	 			        	         var wardNamecheck 	=  	$('#wardname'+l).val();
  	 			        	        	 if (wardnumber.replace(/ /g,'').toUpperCase() == wardnumbercheck.replace(/ /g,'').toUpperCase()) {
  	 			        	        	 $('#wardnumber'+l).addClass("error");
  	 			        	        	 $('#wardnumber'+k).addClass("error");
  	 			        	        	 duplicateStaus=true;
  	 			        	         }   
  	 			        	         if (wardName.replace(/ /g,'').toUpperCase() == wardNamecheck.replace(/ /g,'').toUpperCase()) {
  	 			        	        	 $('#wardname'+l).addClass("error");
  	 			        	        	 $('#wardname'+k).addClass("error");
  	 			        	        	 duplicateStaus=true;
  	 			        	         }
  	 			        	         
  	 			           }
  	 			           
  	 			           if(!$("#checkbox"+k).length>0){
  	 			        	   var wardNamecheck 	=  	$('#wardname'+k).val();
  	 			        	   if(wardNamecheck.replace(/ /g,'').toUpperCase()!=""){
  	 			        		   		$('#wardname'+k).addClass("error");
  	 			        		     	wardNames=wardNames+fullTrim(wardNamecheck)+"@";
  	 			        		 }
  	 			        	   
  	 			           }
  	 		       }
  	 		   }
  	 		   
  	 		if(duplicateStaus){
	    		showDialogBox("kindly remove duplicate values");
  	 		}else{
  	 			lgdDwrWardService.checkWardDuplicateInDraft(parseInt('${lblc}'),wardNames, {
  	 				callback : function(result) {
  	 					//alert(result);
  	 					/* if(result){
  	 					showDialogBox("Ward Name already exist in draft");
  	 					}
  	 					else{ */
  	 						if(document.getElementById('UniqueWardNameError').innerHTML=="" && document.getElementById('UniqueWardCodeError').innerHTML==""){
  	 		  	 			 callActionUrl('updateLocalNameOfWard.htm','wardForm');
  	 		  	 		  	}
  	 				/* 	} */
  	 			},
  	 			async : false
  	 		});
  	 		
	}   	
	 
  }
}

var callActionUrl = function (url,fromId) {
	
	$( 'form[id='+fromId+']' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id='+fromId+']' ).attr('method','post');
	$( 'form[id='+fromId+']' ).submit();
};

function addarow(value)
{	
	
	  var noofRow= $('#noofWards').val();
	  if(noofRow > 100){
		  alert("Kindly enter less then 100 rows");
		  return false;
	  }
	
	  if(noofRow!=''){
		  if(value==2){
			  noofRow=1;
		  }
		  
	  }else{
		  noofRow=1;
	  }
	  for(var i=0;i<noofRow;i++){
		  var table = document.getElementById('demand');	 
		     var rowCount = table.rows.length;
		     var row = table.insertRow(rowCount);  
		     row.id=rowCount;
		     /* if(rowCount%2==0){
		    	 $("#"+rowCount).css("background-color", "#dedede");
		     }else{
		    	 $("#"+rowCount).css("background-color", "#ffffff");
		     } */
		     //$("#demand tr:odd").css("background-color", "#ffffff");
				
		     
		     
		     var cell2 = row.insertCell(0);
		     cell2.innerHTML = rowCount;	      
		     
		     var cell4 = row.insertCell(1);
		     cell4.innerHTML = "";	
		     
		     var cell1 = row.insertCell(2);
		     var element1 = document.createElement("input");
		     element1.type = "text";
		     element1.name='wardnumber' + rowCount;
		     element1.id='wardnumber' + rowCount;
		    element1.setAttribute('maxlength','10');
		    // element1.size = 20; 
		     /* element1.className='frmfield'; */
		     /* element1.style.width="100px"; */
		   //  element1.setAttribute('maxlength','250');
		     element1.onblur =  function () {
		    	UniqueWardValidation(this.value,2,rowCount);
		    	 vlidateOnblur('wardnumber','1','15','m');
		 		  }; 
				 
		 	 element1.onkeypress=function(event){return validateNumberWardNumUrban(event);};
		 	 element1.onfocus=function(){
		 		 validateOnFocus('wardname');
		 	     	changeErrorClass(this.id);
		 	      };
		 	 cell1.appendChild(element1);	
		 	 
		 	 
		     var cell2 = row.insertCell(3);
		     var element2 = document.createElement("input");
		     element2.type = "text";
		     element2.name=' wardname' + rowCount;
		     element2.id='wardname' + rowCount;
		     element2.size = 20;   
		    /*  element2.className='frmfield'; */
		     element2.style.width="250px";
		     element2.setAttribute('maxlength','250');
		     element2.onkeypress=function(event){ return validateSpecialCharactersUpdateStandardCodeLocal(event);};
		 	 element1.onfocus=function(){
	 	     	
	 	  	    validateOnFocus('wardname');
	 	     	changeErrorClass(this.id);
	 	     	
		 	 };
		     element2.onblur = function () {
		    	UniqueWardValidation(this.value,1,rowCount);
		    	vlidateOnblur('wardname','1','15','c');
		 		  };
		     cell2.appendChild(element2);	     
		     
		     
		     var cell3 = row.insertCell(4);
		     var element3 = document.createElement("input");
		     element3.type = "text";
		     element3.name='wardnamelocal' + rowCount;
		     element3.id='wardnamelocal' + rowCount;
		     element3.size = 20;   
		     element3.style.width="250px"; 
		    /*  element3.className='frmfield'; */
		     element3.setAttribute('maxlength','250');
		     element3.onblur = function () {
		    	 validateSpecialCharactersWardNLocal(this.value);
		 		  };
		 	 element3.onfocus=function(){
		 		validateOnFocus('wardnamelocal');
		 		changeErrorClass(this.id);
		 	
		 	 	 };
		 	 cell3.appendChild(element3);
		 	
		 	// cell4.appendChild(element4);
		 	
		 	 
		 
	  }

}

function fullTrim(stringToTrim) {
	return stringToTrim.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, '').replace(/\s+/g, ' ');
}


getExistingWardCheck=function(wardCode,id,type){
	lgdDwrlocalBodyService.getexistingwardinTempTable(parseInt(wardCode), {
			callback : function(result) {
				//alert(result);
				if(!result){
					showDialogBox("This Ward Already Exist in Unpublished Wards\n Kindly Take Appropriate Action.");
					 $('#checkbox'+id).prop('checked',false);
				}
				else{
					
					 if(type=='U'){
						if($('#checkbox'+id).is(":checked")){
							enableEditing(id);
							 $('#delete'+id).prop('checked',false);
							 $('#delete'+id).prop("disabled",true);
						}else{
							enableEditing(id);
							// $('#delete'+id).prop('checked',true);
							 $('#delete'+id).prop("disabled",false);
						}
						
					} else{
						if($('#delete'+id).is(":checked")){
							 $('#checkbox'+id).prop('checked',false);
							 $('#checkbox'+id).prop("disabled",true);
						}else{
							 $('#checkbox'+id).prop("disabled",false);
						}
						
					}
					
				}
		},
		async : false
	});
	
};


var deleteid='';
/* function getExistingWardCheck(lblc,id){
	 deleteid=id;
		lgdDwrlocalBodyService.getexistingwardinTempTable(lblc, {
			callback : handleLocalBodytempSuccess,
			errorHandler : handleLocalBodyErrortemp
		});
	 
}
 */

function handleLocalBodytempSuccess(data){
	alert(data);
	if(data){
		 
	 }
	 else{
		 alert("This Ward Already Exist in Unpublished Wards\n Kindly Take Appropriate Action.");
		 $('#delete'+deleteid).prop('checked',false);
		 $('#delete'+deleteid).prop("disabled",true);
		 //$('#delete'+id).prop('checked',prop);
		
		 $('#wardname'+deleteid).prop("disabled",true);
		 $('#wardnumber'+deleteid).prop("disabled",true);
		 $('#wardnamelocal'+deleteid).prop("disabled",true);
		 $('#check'+deleteid).prop("checked",false);
		 $('#wardnumber'+deleteid).css( "background", "none repeat scroll 0 0 lightgrey" );
		 $('#wardnamelocal'+deleteid).css( "background", "none repeat scroll 0 0 lightgrey" );
		 $('#wardname'+deleteid).css( "background", "none repeat scroll 0 0 lightgrey" ); 
		 $('#checkbox'+deleteid).prop("disabled",true);
		 $('#checkbox'+deleteid).prop("checked",false);
		 $('#delete'+deleteid).prop("disabled",true);
		 
	}
	 
}
function handleLocalBodyErrortemp(){
	 alert("Error occur ");
}

function enableEditing(id){
	if($('#checkbox'+id).is(":checked")){
		/*  $('#wardname'+id).prop("disabled",false);
		 $('#wardnumber'+id).prop("disabled",false);
		 */ $('#wardnamelocal'+id).prop("disabled",false);
		 $('#check'+id).prop("checked",true);
/* 		 $('#wardnumber'+id).css( "background", "none repeat scroll 0 0 #fff" );
 */		 $('#wardnamelocal'+id).css( "background", "none repeat scroll 0 0 #fff" );
/* 		 $('#wardname'+id).css( "background", "none repeat scroll 0 0 #fff" );
 */		// $('#checkbox'+id).prop("disabled",true);
		 $('#delete'+id).prop("disabled",true);
	}else{
		
		/*  $('#wardname'+id).prop("disabled",true);
		 $('#wardnumber'+id).prop("disabled",true);
		 */ $('#wardnamelocal'+id).prop("disabled",true);
		 $('#check'+id).prop("checked",false);
		 $('#wardnumber'+id).css( "background", "none repeat scroll 0 0 lightgrey" );
		 $('#wardnamelocal'+id).css( "background", "none repeat scroll 0 0 lightgrey" );
		 $('#wardname'+id).css( "background", "none repeat scroll 0 0 lightgrey" );
		// $('#checkbox'+id).prop("disabled",false);
		 $('#delete'+id).prop("disabled",false);
		 $('#wardnumber'+id).val($('#wardnumberOld'+id).val());
		 $('#wardnamelocal'+id).val( $('#wardnamelocalOld'+id).val());
		 $('#wardname'+id).val( $('#wardnameOld'+id).val());
		
	}
	
	 
 }
 
 showDialogBox=function(message){
	 
	 $("#alertboxbody").text(message);
	$('#alertbox').modal('show');	
	
 };
 
 
ClearForm=function(){
	callActionUrl('viewWardLocalNamePRIAndTRI.htm','wardForm'); 
 };
</script>

<c:if test="${showTable eq true and PANCHAYAT_TYPE ne 'U'}">
	<script>
$(window).load(function () {
	$('#localBodyType option[value != ""]').remove();
	//alert('${wardForm.paramLocalBodyTypeCode}');
	var _lb_type_value = $('#lbTypeHierarchy option:selected').val();
	if (!$_checkEmptyObject(_lb_type_value)) {
		var optVaues = _lb_type_value.split(',');
		var setupCode = optVaues[0]; // local body setup code
		var setupVersion = optVaues[1]; // local body setup version
		dwrRestructuredLocalBodyService.buildLocalBodyHierarchy(parseInt(setupCode), parseInt(setupVersion), {
			callback : function(result) {
				populateLBType(result);
				$("#localBodyType option[value='${wardForm.paramLocalBodyTypeCode}']").attr("selected", "selected");
				registerCallDynamicHierarchy($('#localBodyType').get(0));
				setTimeout(function(){
					var localbodyLevelCodes =  '${wardForm.localBodyLevelCodes}'.split(",");
					var localBodyLevelElement = $("SELECT[name='localBodyLevelCodes']");
					var elementCount = $(localBodyLevelElement).size()-1;
					$(localBodyLevelElement).each(function(index){
					//alert("index:"+index+" elementCount:"+elementCount+"localbodyLevelCodes:"+localbodyLevelCodes);
						$("#" + $(this).attr('id') +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected");
						if(index < elementCount ){
							configureLocalBodyTypeHierarchy(this);	
						}
						if(index == elementCount ){
							var elementId = $(this).attr('id');
							setTimeout(function(){
								 $("#" + elementId +" option[value='" + localbodyLevelCodes[index] + "']").attr("selected", "selected"); 
							},200);
						}
					});
				}, 200);
			},
			errorHandler : errorLbTypeProcess,
			async : true
		});
	}
}); 
</script>
</c:if>
