<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%	contextPath = request.getContextPath(); %>
<%@include file="../common/taglib_includes.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/govtorder.js"></script>
<script src="js/new_ward.js"></script>
<script src="js/lgd_localbody.js"></script>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/jquery.js"></script> -->
<script src="js/validation.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" language="Javascript"></script>

<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>

<script src="js/common.js"></script>
<!-- <link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" /> -->
<script src="js/browserSniffer.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript"
	src="<%=contextpthval%>/datatable/jquery.dataTables.js"></script>
<link href="<%=contextpthval%>/datatable/demo_table_jui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">





$(document).ready(function() {
	
	 if(isParseJson('${showTable}')){
	    //$('#viewData').show();
	    //$('#btnpanel').show();
	 //   $("#demand1 tr:odd").css("background-color", "#ffffff");
		//$("#demand1 tr:even").css("background-color", "#dedede");
		
		$("#demand2 tr:odd").css("background-color", "#ffffff");
		$("#demand2 tr:even").css("background-color", "#dedede");
	//	$("#trhead").css("background-color", "#");
		
		
	 }
	 $('#demand1').dataTable({
			"sScrollY": "200px",
			"iDisplayLength": 50,
			"bScrollCollapse": true,
			"bPaginate": true,
			
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [
							null,
							null,
							null,
							null,								
				  			],
			"sPaginationType": "full_numbers"
		});
	/*  $('#demand').dataTable({
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


	 $('#demand').dataTable({
			"sScrollY": "200px",
			"iDisplayLength": 50,
			"bScrollCollapse": true,
			"bPaginate": true,
			
			"bJQueryUI": true,
			"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0,1,2,3,4,5] } ],
			"aaSorting": [],
			"aoColumns": [
							null,
							null,
							null,
							null,
							null,
							null,
				  			],
			"sPaginationType": "full_numbers"
		});
		
		$('#demand_wrapper').css("min-height", "32px");
		$('#demand1_wrapper').css("min-height", "32px");
		$('#demand2_wrapper').css("min-height", "32px");
		
		
		

	 $('#demand2').dataTable({
			"sScrollY": "200px",
			
			"bScrollCollapse": true,
			"bPaginate": true,
			
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [								
							null,
							null,
							null,
							null,
							null,
				  			],
			"sPaginationType": "full_numbers"
		});

 } );
 
 function  validateromannumber(value){
	    var strInput = value;
	    var matchArr = strInput.match(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/g);
	    console.log(matchArr);
	    if(matchArr) {
	        // test successful
	     alert(true);
	    } else {
	        // failure
	        alert(false);
	    }
 }

/* function validateromannumber(value)
{
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

*/
$(window).load(function(){
	
	var id = dwr.util.getValue('localBodyType');
	var stateCode = dwr.util.getValue('stateCode');

	var temp = id.split(":");

	var id1 = temp[0];
	var id2 = temp[1];
	// document.getElementById('divCoveredArea').style.display = 'block';
	switch (id2) {
	case 'I':
		$('#tr_district1').show();
		// added by kirandeep on 01/09/2014
		/* document.getElementById('tr_district1').style.visibility = 'visible'; */
		/* document.getElementById('tr_district1').style.display = 'block'; */

		/*
		 * document.getElementById('wardUrbanLocalBody').style.display =
		 * 'block';
		 */

		lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, id1, {
			callback : function(data){
				var fieldId = 'wardUrbanLocalBody';
				var valueText = 'localBodyCode';
				var nameText = 'localBodyNameEnglish';
				// villageCode,villageNameEnglish
				dwr.util.removeAllOptions(fieldId);

				var st = document.getElementById('wardUrbanLocalBody');
				st.add(new Option('Select Local Body', '0'));

				var options = $("#wardUrbanLocalBody");
				$.each(data, function(key, obj) {
					var option = $("<option/>");
					if (obj.operation_state == 'F') {
						if('${localGovtBodyForm.lb_lgdPanchayatName}' == obj.localBodyCode ){
							$(option).attr('selected','selected');
						}
						$(option).attr('disabled', 'disabled');
						$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
						options.append(option);
						
					} else {
						if('${localGovtBodyForm.lb_lgdPanchayatName}' == obj.localBodyCode ){
							$(option).attr('selected','selected');
						}
						$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
						options.append(option);
					}
				});
				//alert('${localGovtBodyForm.lb_lgdPanchayatName}');
	
				
			},
			errorHandler : getPanchayatListbyStateandlbTypeCodeError,
			async:true
		});

		break;

	default:
		document.getElementById('tr_district1').style.display = 'none';

	}
});


function getList()
{  
	
	var status=validateUrbanWardAll();
	
				if(status){
	         		$('#manage').val("manage");	         
					$('#localGovtBodyForm').attr('action',  "viewUrbanWard.htm?<csrf:token uri='viewUrbanWard.htm'/>").submit();
				}
	 
}


/* function deleteward(id){
	 $("#"+id).remove();
	  var i = 0;
		$('#demand tr').each(function() {
			if (i > 0) {
				$(this).find("td:first").text(i);
			}
			i++;
		}); */
//	alert(id);
	//$('#demand tr:last').remove();
	
/* }
 */
 function hideviewData(){
	 
	  if($('#viewData').is(':visible')){
		 $("#demand tr").remove(); 
	  }
	   $('#viewData').hide(); 
	   $('#btnpanel').hide();
}

 function enableEditing(id){
	if($('#checkbox'+id).is(":checked")){
		 $('#wardname'+id).prop("disabled",false);
		 $('#wardnumber'+id).prop("disabled",false);
		 $('#wardnamelocal'+id).prop("disabled",false);
		 $('#check'+id).prop("checked",true);
		 $('#wardnumber'+id).css( "background", "none repeat scroll 0 0 #fff" );
		 $('#wardnamelocal'+id).css( "background", "none repeat scroll 0 0 #fff" );
		 $('#wardname'+id).css( "background", "none repeat scroll 0 0 #fff" );
		// $('#checkbox'+id).prop("disabled",true);
		 $('#delete'+id).prop("disabled",true);
	}else{
		
		 $('#wardname'+id).prop("disabled",true);
		 $('#wardnumber'+id).prop("disabled",true);
		 $('#wardnamelocal'+id).prop("disabled",true);
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

function getListOfWard(id){
		lgdDwrlocalBodyService.getlocalGovtBodyWardListforpaginationonCreate(id1, {
			callback : handleWardListSuccess,
			errorHandler : handleWardListerror
		});
}
function handleWardListSuccess(data){


}

function changeErrorClass(id){
	 $('#'+id).removeClass( "error_fld" );
	 $('#'+id).addClass( "frmfield" );
}


function saveEnabledvalues(){
	
   var table1 = document.getElementById('demand');	 
   var tablelength1 = table1.rows.length;
   var duplicateStaus=false;
   if(tablelength1>2){
  	  for(var k=1;k<=tablelength1;k++){
  		  var wardnumber    = 	$('#wardnumber'+k).val();
  		  var wardName  	=  	$('#wardname'+k).val();
	           for(var l=(k+1);l<=tablelength1;l++){
	        	         var wardnumbercheck=  $('#wardnumber'+l).val();
	        	         var wardNamecheck 	=  	$('#wardname'+l).val();
	        	         if(wardnumber==wardnumbercheck){
	        	        	 $('#wardnumber'+l).addClass("error_fld");
	        	        	 $('#wardnumber'+k).addClass("error_fld");
	        	        	 duplicateStaus=true;
	        	         }   
	        	         if(wardName==wardNamecheck){
	        	        	 $('#wardname'+l).addClass("error_fld");
	        	        	 $('#wardname'+k).addClass("error_fld");
	        	        	 duplicateStaus=true;
	        	         }
	           }
       }
   }
 
   if(duplicateStaus){
  	 alert("kindly remove duplicate values");
  	 return false;
   } 
   
	 var lengthofEnable='${fn:length(wardList)}';
	 
	 var size=lengthofEnable;
		 var array_tableData = ""; 
		 var array_newWardData="";
		 var array_DeleteWardData="";
		 
		 var table = document.getElementById('demand');	 
	     var tablelength = table.rows.length;
	   
	     var actualsize=tablelength-1;
	     var status=false;
	   
	     if(tablelength > lengthofEnable){
	    	 
	    	 if(actualsize>lengthofEnable){
	    		
	    		 var totallength=tablelength-lengthofEnable;
		    	  --totallength;
		    
		          var startid=++lengthofEnable;
		          for(var k=0;k<totallength;k++){
		              
		        	    var wardName 	=  	$('#wardname'+startid).val();
		        	    if(wardName==''){
		        	    	$('#wardname'+startid).addClass("error_fld");
		        	    	status=true;
		        	    }
		     	        var wardnumber  = 	$('#wardnumber'+startid).val();
		     	        if(wardnumber==''){
		        	    	$('#wardnumber'+startid).addClass("error_fld");
		        	    	status=true;
		        	    }
		     	        var wardnamelocal = $('#wardnamelocal'+startid).val();	
		     	        wardName += "|" + wardnumber;
		     	        wardName += "|" + wardnamelocal;
		     	        wardName += "@@";
				 		
		     	       array_newWardData+= wardName;
		        	   startid++;
		          }
		        
		         $('#newWardList').val(array_newWardData);
	    	 
	    	 }
	    	  
	     }
	
		 for(var i=1;i<=size;i++)
	        {
			   if($('#check'+i).is(":checked")){
				    var wardEditInfo=$('#check'+i).val();
	     	        var wardName =   $('#wardname'+i).val();
	     	       if(wardName==''){
	        	    	$('#wardname'+i).addClass("error_fld");
	        	    	status=true;
	        	    }
	     	        var wardnumber = $('#wardnumber'+i).val();
	     	       if(wardnumber==''){
	        	    	$('#wardnumber'+i).addClass("error_fld");
	        	    	status=true;
	        	    }
	     	        var wardnamelocal =   $('#wardnamelocal'+i).val();
	     	        wardEditInfo += "|" + wardName;
	     	        wardEditInfo += "|" + wardnumber;
	     	        wardEditInfo += "|" + wardnamelocal;
	     	        wardEditInfo += "@@";
			 		
			 		array_tableData+= wardEditInfo;
			   }			    	       
	        }

	       $('#formNominations').val(array_tableData);
	       
		 for(var i=1;i<=size;i++)
	        {
			   if($('#delete'+i).is(":checked")){
				    var wardEditInfo=$('#deletev'+i).val();
	     	        var wardName =   $('#wardname'+i).val();
	     	       if(wardName==''){
	        	    	$('#wardname'+i).addClass("error_fld");
	        	    	status=true;
	        	    }
	     	        var wardnumber = $('#wardnumber'+i).val();
	     	       if(wardnumber==''){
	        	    	$('#wardnumber'+i).addClass("error_fld");
	        	    	status=true;
	        	    }
	     	        var wardnamelocal =   $('#wardnamelocal'+i).val();
	     	        wardEditInfo += "|" + wardName;
	     	        wardEditInfo += "|" + wardnumber;
	     	        wardEditInfo += "|" + wardnamelocal;
	     	        wardEditInfo += "@@";
			 		
	     	       array_DeleteWardData+= wardEditInfo;
			   }			    	       
	        }

		
		 
         $('#deleteWardList').val(array_DeleteWardData);
		 
	     
	       if(status){
	    	   alert("Kindly add Required Fields");
	 		   return false;
	 	   }else{
	 		   var difference= tablelength-lengthofEnable;
	 		   if(difference==1){
	 			  if(array_tableData=""){
		 			   alert("Kindly add or Modified any Field First");
		 			   return false;
		 		   }
	 		   }else{
	 			  if(array_newWardData==""){
		 			   alert("Kindly add or Modified any Field First");
		 			   return false;
		 		   }
	 		   }
	 		 
	 		  if(document.getElementById('UniqueWardNameError').innerHTML=="" && document.getElementById('UniqueWardCodeError').innerHTML==""){
	 			  $('#localGovtBodyForm').attr('action',  "createWardforUrbanPost.htm?<csrf:token uri='createWardforUrbanPost.htm'/>").submit();
	 		  }  else{
	 			 
	 			 return false;
	 		  }
	 		
	 	   }
}



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
		     if(rowCount%2==0){
		    	 $("#"+rowCount).css("background-color", "#dedede");
		     }else{
		    	 $("#"+rowCount).css("background-color", "#ffffff");
		     }
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
		     element1.className='frmfield';
		     element1.style.width="200px";
		     //element1.setAttribute('maxlength','250');
		     element1.onblur =  function () {
		    	 UniqueWardValidationforUrban(this.value,2,rowCount);
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
		     element2.className='frmfield';
		     element2.style.width="200px";
		     element2.setAttribute('maxlength','250');
		     element2.onkeypress=function(event){ return validateSpecialCharactersUpdateStandardCodeLocal(event);};
		 	 element1.onfocus=function(){
	 	     	
	 	  	    validateOnFocus('wardname');
	 	     	changeErrorClass(this.id);
	 	     	
		 	 };
		     element2.onblur = function () {
		    	 UniqueWardValidationforUrban(this.value,1,rowCount);
		    	vlidateOnblur('wardname','1','15','c');
		 		  };
		     cell2.appendChild(element2);	     
		     
		     
		     var cell3 = row.insertCell(4);
		     var element3 = document.createElement("input");
		     element3.type = "text";
		     element3.name='wardnamelocal' + rowCount;
		     element3.id='wardnamelocal' + rowCount;
		     element3.size = 20;   
		     element3.style.width="200px"; 
		     element3.className='frmfield';
		     element3.setAttribute('maxlength','250');
		     element3.onblur = function () {
		    	 validateSpecialCharactersWardNLocal(this.value);
		 		  };
		 	 element3.onfocus=function(){
		 		validateOnFocus('wardnamelocal');
		 		changeErrorClass(this.id);
		 	
		 	 	 };
		 	 cell3.appendChild(element3);
		 
	  }

}

function validateNumberWardNumUrban(event) {
	var key;
	var chkey = -1;
	key = event.keyCode;
	if (key == 0) {
		chkey = key;
		key = event.which;
	}
	
	//alert(String.fromCharCode(key));
	var status=false;
   
	if ((key == 47) || (key == 48) || (key == 49) || (key == 50) || (key == 51) || (key == 52) || (key == 53) || (key == 54) || (key == 55)
			|| (key == 56) || (key == 57) || (key == 8) || (key == 46 && chkey == -1)) {
			
			
			status=true;
		
	}
	var checkroman=inputIsValid(String.fromCharCode(key));
	if(checkroman || status)
	{
	return true;
	}else{
	alert("Please use Roman,numerics or / ");
	event.returnValue = false;
		/* $('#textbox').val(''); */
		return false;
	}
		

}


function inputIsValid(value) {
	value=value.toUpperCase();

              var regex = /^M*(?:D?C{0,3}|C[MD])(?:L?X{0,3}|X[CL])(?:V?I{0,3}|I[XV])$/;
	  		  var roman =regex.test(value);
			//  var number = /\/[0-9]+$/.test(value);
			//  alert(number);
			  if(roman){
			//   alert("ok");
			   return true;
			    }
				 else{
				// alert("wrong input");
				 return false;
				 }

 }
 
 
 function publishward(code,isdelete,isupdate,isnew,isRestore){
	 $('#tempWardCode').val(code);
	 $('#manage').val("manage");
	 
	 if(isdelete=='true'){
		
		 $('#IsDelete').val("Yes");
		 $('#localGovtBodyForm').attr('action',  "publishWardUrban.htm?<csrf:token uri='publishWard.htm'/>").submit();
		 
	 }else if(isupdate=='true'){
		
		 $('#isUpdate').val("Yes");
			$('#localGovtBodyForm').attr('action',  "publishWardUrban.htm?<csrf:token uri='publishWard.htm'/>").submit();
		 
	 } else if(isnew=='true'){
		 $('#isNew').val("Yes");
		$('#localGovtBodyForm').attr('action',  "publishWardUrban.htm?<csrf:token uri='publishWard.htm'/>").submit();
	 }else if(isRestore){
		 $('#IsRestore').val("Yes");
		 $('#localGovtBodyForm').attr('action',  "publishWardUrban.htm?<csrf:token uri='publishWard.htm'/>").submit();
		
	 }
	 
 }
 
 function deletetempdata(id){
	 
	 $('#manage').val("manage");
	 $('#tempWardCode').val(id);	 
	 $('#istempDelete').val("Yes");	 
	 $('#localGovtBodyForm').attr('action',  "publishWardUrban.htm?<csrf:token uri='publishWard.htm'/>").submit();
	
 }
 
 function handleDeleteData(data){
	 $('#tr'+deleteid).remove();
	  var i = 0;
		$('#demand tr').each(function() {
			if (i > 0) {
				$(this).find("td:first").text(i);
			}
			i++;
		});
	   
 }
 
 function handledeleteListerror(){
	 alert("Delete Error Occur");
 }
 
 
 
 function fetchWardValues(){
	 var checkAll=$('#publishAll').is( ':checked' );
	 if( checkAll ){
		 
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#p' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#p' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }
	 		 /* if(!$(rows[i]).find('#u' + datatableRowId).is( ':disabled' )){
	 			var unfreezeChecked = $(rows[i]).find('#u' + datatableRowId).is(":checked");
	 		    if(unfreezeChecked) {
	 		       		var dstring   = datatableRowId;
	 					dstring  += "_" + "U";
	 					dstring  += "_" + reason + ":";
	 				    array_tableData	+= dstring;        	 
	 		           }
	 		 } */
	                
	      }
		 
		 
	 	 $('#publishWardsList').val(array_tableData);
		 $('#publishAllWard').val("Yes");
		 $('#manage').val("manage");
		 $('#localGovtBodyForm').attr('action',  "publishWardUrbanAll.htm?<csrf:token uri='publishWardUrbanAll.htm'/>").submit();
	 }else{
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#p' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#p' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }
	 		 /* if(!$(rows[i]).find('#u' + datatableRowId).is( ':disabled' )){
	 			var unfreezeChecked = $(rows[i]).find('#u' + datatableRowId).is(":checked");
	 		    if(unfreezeChecked) {
	 		       		var dstring   = datatableRowId;
	 					dstring  += "_" + "U";
	 					dstring  += "_" + reason + ":";
	 				    array_tableData	+= dstring;        	 
	 		           }
	 		 } */
	                
	        }
	 	
	 	if(array_tableData==""){
			 alert("Kindly Select any Ward To publish");
			 return false;
		 }
	 	$('#publishWardsList').val(array_tableData);
	 	$('#manage').val("manage");
	 	$('#localGovtBodyForm').attr('action',  "publishWardUrbanAll.htm?<csrf:token uri='publishWardUrbanAll.htm'/>").submit();
	 }
 	
 }
 
 function selectAll(){
	 var checkAll	   = $('#publishAll').is( ':checked' );
	 
	 if( checkAll ){
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		$(rows[i]).find('#p' + datatableRowId).prop('checked', true);
	 		$(rows[i]).find('#d' + datatableRowId).prop('checked', false);
	 		$('#deleteAll').prop('checked', false);
	 	 }		 
	 }else{
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 $(rows[i]).find('#p' + datatableRowId).prop('checked', false);
	 	 }		 
	 }
	
	 
 }
 
 function deleteAllWards() {
	 var checkAllDelete= $('#deleteAll').is( ':checked' );
	 if( checkAllDelete ){
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {	 		 
	 		var datatableRowId = $(rows[i]).attr( 'id' );
	 		$(rows[i]).find('#d' + datatableRowId).prop('checked', true);
	 		$(rows[i]).find('#p' + datatableRowId).prop('checked', false);
	 		$('#publishAll').prop('checked', false); 
	 	 }	
	 }else{
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		$(rows[i]).find('#d' + datatableRowId).prop('checked', false);
	 		
	 	 }		
		 
	 }
 }
 
 function deleteWardValues() {
	 var checkAll=$('#deleteAll').is( ':checked' );
	 if( checkAll ){
		 
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#d' + datatableRowId).is( ':disabled' )){
	 			 var deleteChecked = $(rows[i]).find('#d' + datatableRowId).is( ':checked' );
	 	         if( deleteChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }        
	      }
		 
		 
	 	 $('#publishWardsList').val(array_tableData);
		 $('#deleteAllWardTemp').val("Yes");
		 $('#manage').val("manage");
		 $('#localGovtBodyForm').attr('action',  "publishWardUrbanAll.htm?<csrf:token uri='publishWardUrbanAll.htm'/>").submit();
	 }else{
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#d' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#d' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }	                
	        }
	 	
	 	if(array_tableData==""){
			 alert("Kindly Select any Ward To Delete");
			 return false;
		 }
	 	 $('#publishWardsList').val(array_tableData);
	 	 $('#manage').val("manage");
	 	 $('#deleteAllWardTemp').val("Yes");	 	
	 	 $('#localGovtBodyForm').attr('action',  "publishWardUrbanAll.htm?<csrf:token uri='publishWardUrbanAll.htm'/>").submit();
	 }
	 
 }
 
 /* Added by Pooja on 18-08-2015 */
	function selectWard(id) {
		var check = $('#p' + id).is(':checked');
		if (check) {
			$('#d' + id).prop('checked', false);
			$('#deleteAll').prop('checked', false);
		} else {
			$('#publishAll').prop('checked', false);
		}
	}

	function deleteWard(id) {
		var check = $('#d' + id).is(':checked');
		if (check) {
			$('#p' + id).prop('checked', false);
			$('#publishAll').prop('checked', false);
		} else {
			$('#deleteAll').prop('checked', false);
		}
	}
 
</script>
<!-- <script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script> -->
</head>
<%@include file="../common/dwr.jsp"%>
<body onload="">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>
 
	<div id="frmcontent">
	
		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message code="Label.viewWard" text="Manage Ward" htmlEscape="true"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">	
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /> </a></li>
			<%--//these links are not working 	<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>		 --%>	
				<li><a href="#" class="frmhelp">Help</a></li>
			</ul>
		</div>
		
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<%-- <form:form action="createWardforPRI.htm" method="POST"
				commandName="localGovtBodyForm" enctype="multipart/form-data"> --%>

			<form:form action="createWardforUrbanPost.htm" method="POST"
				commandName="localGovtBodyForm" enctype="multipart/form-data" id="localGovtBodyForm">

				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createWardforUrbanPost.htm"/>" />
				<input type="hidden" id="formNominations" name="formNominations" />
				<input type="hidden" id="newWardList" name="newWardList" />
				<input type="hidden" name="flagCode" id="flagCode" value="" />
				<form:hidden path="listofwards" value="" id="listofwards"></form:hidden>
				<input type="hidden" id="localbodyCode" name="localbodyCode" />
				<input type="hidden" id="deleteWardList" name="deleteWardList" />
				<input type="hidden" id="manage" name="manage" />
				<input type="hidden" id="tempWardCode" name="tempWardCode" />
				<input type="hidden" id="isNew" name="isNew" />
				<input type="hidden" id="isUpdate" name="isUpdate" />
				<input type="hidden" id="IsDelete" name="IsDelete" />
				<input type="hidden" id="IsRestore" name="IsRestore" />
				<input type="hidden" id="istempDelete" name="istempDelete" />
				<input type="hidden" id="publishWardsList"	name="publishWardsList" />	
				<input type="hidden" id="publishAllWard"	name="publishAllWard" />
				<input type="hidden" id="deleteAllWardTemp"	name="deleteAllWardTemp" />	
																
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
													
							<ul class="blocklist">
								<li>									
										<div class="frmpnlbg">
											<div class="frmtxt">
												<div class="frmhdtitle">
													<spring:message code="Label.LOCALBODY" htmlEscape="true"></spring:message>
												</div>
											<!-- 	<table width="100%" class="tbl_no_brdr"> -->
											
												<ul class="blocklist">
													<li>
														<ul class="listing">
															<li>
																<input type="hidden" name="stateCode"
																value="<c:out value='${stateCode}'/>"/>
																<input type="hidden"
																name="districtCode" value="<c:out value='${districtCode}'/>"/>
															</li>
															<li>
																<b><spring:message
																	code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message> </b> <span
																	class="errormsg">*</span> <br /> <form:select htmlEscape="true"
																path	="lgd_LBTypeName" id="localBodyType"
																onfocus="validateOnFocus('localBodyType');helpMessage(this,'localBodyTypeMsg');" 
																onchange="hideviewData();hideShowDivforUrbanWard(this.value);getPanchayatListbyStateandlbTypeCode(this.value);"
																onkeydown="validateOnKeyPessDown(event,'localBodyType','char')"
																onblur="vlidateOnblur('localBodyType','1','15','c');hideHelp();"
																class="combofield" style="width: 175px">
																<form:option value="0">
																	<spring:message code="Label.SELECT" htmlEscape="true"> </spring:message>
																</form:option>

																<c:forEach var="localBodyTypelist" varStatus="var"
																	items="${localBodyTypelist}">
																	<form:option
																		value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
																</c:forEach>
															</form:select>&nbsp;&nbsp;																											
														
															<div id="localBodyType_error" class="error"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"></spring:message></div>
															<div id="localBodyTypeMsg" style="display:none"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"/></div>
															<div class="errormsg" id="localBodyType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
															<div class="errormsg" id="localBodyType_error2" style="display: none" ></div>
														
															</li>
														</ul>
													</li>												

													<li id="tr_district1" style="display: none;">
															<b><spring:message
																	code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message> </b><span
																class="errormsg">*</span> <br /> <form:select htmlEscape="true"
																path="lb_lgdPanchayatName" class="combofield"
																id="wardUrbanLocalBody" style="width: 160px"
																onfocus="validateOnFocus('wardUrbanLocalBody');helpMessage(this,'wardUrbanLocalBodyMsg');" 
																onchange="hideviewData();"
																onkeydown="validateOnKeyPessDown(event,'wardUrbanLocalBody','char')"
																onblur="vlidateOnblur('wardUrbanLocalBody','1','15','c');hideHelp();">
																<form:option value="0">
																	<spring:message code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
															</form:select>&nbsp;&nbsp;
														
														<div id="wardUrbanLocalBody_error" class="error"><spring:message code="error.blank.TEHSILPa" htmlEscape="true" text="Please select local body"></spring:message></div>
														<div id="wardUrbanLocalBodyMsg" style="display:none"><spring:message code="error.blank.TEHSILPa" text="Please select local body" htmlEscape="true"/></div>
														<div class="errormsg" id="wardUrbanLocalBody_error1"><form:errors path="lb_lgdPanchayatName" htmlEscape="true"/></div>  
														<div class="errormsg" id="wardUrbanLocalBody_error2" style="display: none" ></div>

													</li>
												</ul>
												<div class="">
													<input type="button" id="testingurbanbutton" onclick="getList()" value="Get Data" />
												</div>	
											
																						
												<!-- </table> -->
										</div>
									</div>									
								</li>	
							<!-- 	modified by pooja on 09-07-2015 to remove coverage ward part -->							
								<%-- <li style="display:none">									
										<div class="frmpnlbg">
											<div class="frmtxt" id="divStatelvl">
												<div class="frmhdtitle">
													<spring:message code="Label.COVEREDAREAOFWARD" htmlEscape="true"></spring:message>
												</div>												
												<div id="divSpecificState">		
													<div class="margin_left_10per">											
														<ul class="blocklist">
																	<li>																																				
																																				
																		<div id="ddLgdWardSubDistrictUListDest_error" class="error"><spring:message code="error.blank.SubdistrictCreateWard" htmlEscape="true"></spring:message></div>																		
																 		<div class="errormsg" id="ddLgdWardSubDistrictUListDest_error1"><form:errors path="lgd_LBSubDistrictList" htmlEscape="true"/></div>  
																 		<div class="errormsg" id="ddLgdWardSubDistrictUListDest_error2" style="display: none" ></div>
																																				
																	</li>
																	<li>
																		<ul class="listing">
																		  <li>
																			<b><spring:message htmlEscape="true"
																					code="Label.AVAILSUBDISTRICTLIST"></spring:message>&nbsp;
																				</b><br /> <form:select htmlEscape="true"
																				path="lgd_LBSubDistrictSourceLatDCAUmapped"
																				class="frmtxtarea"
																				id="ddLgdWardSubDistrictUListSource"
																				style="height: 110px; width: 233px" multiple="true">

																			</form:select><br /> <br />
																		  </li>

																		 <li>
																			<input
																			type="button" value=" Whole &gt;&gt;"
																			style="width: 80px"
																			onclick="addItemforWard('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource','FULL',true,'T');" />
																			<br /> <input type="button" id="btnremoveOneULB"
																			name="Submit4" value="Back &lt;"
																			onclick="removeItemVillListSubListWardULB('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource',true)" /><br />
																			<input type="button" value="Part &gt;&gt;"
																			style="width: 70px"
																			onclick="addItemforSubDistrictWardPart('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource', 'PART',true);" />
																		 </li>

																		 <li>
																			<b><spring:message htmlEscape="true"
																					code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message><span class="errormsg">*</span><span><form:errors
																					path="lgd_LBSubDistrictList" class="errormsg"></form:errors>
																			</span>
																			</b> <br />
																			 <form:select name="select6" htmlEscape="true"
																				id="ddLgdWardSubDistrictUListDest" size="1"
																				multiple="multiple" path="lgd_LBSubDistrictList"
																				class="frmtxtarea"
																				style="height: 110px; width: 242px">
																			</form:select><br /> 																		
																		 </li>
																	  </ul> 
																	</li>
																</ul>	
															</div>												
													</div>
														
											</div>
										</div>									
								</li> --%>
							</ul>						
						</div>
						<!-- start of manage urban ward code by Kirandeep on 04/09/2014 -->
						
	<br></br>
	<br></br>
<div id="viewData">
		<c:if test="${showTable}">
		<div style="width: 100%;">
		<c:if test="${!empty wardList}">
		
		<div class="frmtxt">
					<div class="frmhdtitle">
							Published Wards
 
					</div>
			<table class="display" id="demand1">
			<thead>
				<tr class="tblRowTitle tblclear" id="trhead">
					<th align="left" >Serial No.</th>
					<th align="left">Ward Number</th>
					<th align="left">Ward Name In English</th>
					<th align="left">Ward Name Local</th>
					
				</tr>
			</thead>
			<tbody>
			     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${wardList}">
														
														
					<tr  id="trid${offsets*limits+(listAdminRow.index+1)}"  >
						<td> <c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
						<td><c:out value="${adminEntityDetail.wardNumber}" escapeXml="true"></c:out></td>
						<td><c:out value="${adminEntityDetail.wardNameInEnglish}" escapeXml="true"></c:out>	</td>
						<td><c:out value="${adminEntityDetail.wardNameInLocal}" escapeXml="true"></c:out></td>
					</tr>
															
					
				</c:forEach>
			</tbody>
			</table>
			  <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${wardList}">
				<c:if test="${!empty listWardDetailstemp}">
															  <c:forEach var="adminEntityDetail1" varStatus="listAdminRow1"
													               	items="${listWardDetailstemp}">
																	<c:if test="${adminEntityDetail.wardCode eq adminEntityDetail1.wardCode}">
																				<c:if test="${adminEntityDetail1.isupdate}">
																					<script>
																					
																					 $('#trid'+'${offsets*limits+(listAdminRow.index+1)}').css("background-color", "#FAFAD2");
																					 
																					</script>
																					
																				
																				</c:if>
																	    		<c:if test="${adminEntityDetail1.isdelete}">
																					<script>
																					
																					 $('#trid'+'${offsets*limits+(listAdminRow.index+1)}').css("background-color", "#D2691E");
																					 
																					</script>
																					
																				
																				</c:if>
																	    
																	 
																	</c:if>
														     </c:forEach>
																
							</c:if>
			
			</c:forEach>
		</div>
		</c:if>
			
		</div>
		<br></br>
		<br></br>
		
		<div>
		<c:if test="${!empty listWardDetailstemp}">
		<table>
			<tr>
			<td bgcolor="" height="5%" width="2%">&nbsp;</td>
			<td bgcolor="" height="5%" width="2%">&nbsp;</td>
				<td bgcolor="#70DB70" height="5%" width="2%">&nbsp;</td>
															<td width="2%">New Ward</td>
															<td bgcolor="#FFFF94" height="5%" width="2%">&nbsp;</td>
															<td width="2%">Update</td>
															<td bgcolor="#FF9999" height="5%" width="2%">&nbsp;</td>
															<td width="2%">Delete</td>
			
			</tr>
		</table>
		
		
		<div class="frmtxt">
						<div class="frmhdtitle">
								Un Published Changes

						</div>

			
							<table  class="display" id="demand">
							<thead>
								<tr class="tblRowTitle tblclear" id="trhead">
									<th width="3%" align="left">Serial No.</th>
									<th width="3%" align="left">Ward Number</th>
									<th width="15%" align="left">ward Name In English</th>
									<th width="15%" align="left">Ward Name Local</th>
								    <th width="32%" align="left">Publish Changes <input type="checkbox" id="publishAll" onchange="selectAll();"/>Publish All Changes</th>
								    <th width="32%" align="left">Delete Changes <input type="checkbox" id="deleteAll"  onchange="deleteAllWards()"/> Delete  All Changes</th>
								</tr>
							</thead>
							<tbody>
							     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
																		items="${listWardDetailstemp}">
									<tr id="${adminEntityDetail.temp_ward_code}" <c:if test="${adminEntityDetail.isnew}" > style="background-color:#70DB70" </c:if> <c:if test="${adminEntityDetail.isupdate}" > style="background-color: 	#FFFF94" </c:if><c:if test="${adminEntityDetail.isdelete}" > style="background-color: 	#FF9999" </c:if>><!--   </tr> --> 
										<td><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
										<td><c:out value="${adminEntityDetail.wardNumber}" escapeXml="true"></c:out></td>
										<td><c:out value="${adminEntityDetail.wardNameEnglish}" escapeXml="true"></c:out></td>
										<td><c:out value="${adminEntityDetail.wardNameLocal}" escapeXml="true"></c:out></td>
									
									    <td> <input type="checkbox" id="p${adminEntityDetail.temp_ward_code}" value="<c:out value='${adminEntityDetail.temp_ward_code}'/>"  onchange="selectWard(this.value)"/> <%-- <input type="button" name="button" value="Publish" onclick="publishward('${adminEntityDetail.temp_ward_code}','${adminEntityDetail.isdelete}','${adminEntityDetail.isupdate}','${adminEntityDetail.isnew}',false)"/> --%> </td>
									    <td> <input type="checkbox" id="d${adminEntityDetail.temp_ward_code}" value="<c:out value='${adminEntityDetail.temp_ward_code}'/>" onchange="deleteWard(this.value)"/> <%-- <input type="button" name="button" value="Delete" onclick="deletetempdata('${adminEntityDetail.temp_ward_code}','${offsets*limits+(listAdminRow.index+1)}')"/> --%></td>
									</tr>
								</c:forEach>
							
							</tbody>
							</table>
			
							<table>
								<tr>
									<td><input type="button" name="PublishWards" value="Publish Wards Changes" onclick="fetchWardValues();"></input></td>
									<td><input type="button" name="PublishWards" value="Delete Wards Changes" onclick="deleteWardValues();"></input></td>
								</tr>
							</table>
		</div>
		</c:if> 
		
			<br></br>
		<br></br>
		
		
		
			<div class="frmtxt">
						<div class="frmhdtitle">
							         Deleted Wards List
						</div>
			
			<table class="display" id="demand2">
			<thead>
				<tr class="tblRowTitle tblclear" id="trhead">
					<th align="left" >Serial No.</th>
					<th align="left">Ward Number</th>
					<th align="left" >Ward Name In English</th>
					<th align="left">Ward Name Local</th>
					<th align="left">Restore Ward</th>
					
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty listWardDetailsdetails}">
			     <c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${listWardDetailsdetails}">
					<tr>
						<td><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
						<td><c:out value="${adminEntityDetail.wardNumber}" escapeXml="true"></c:out></td>
						<td><c:out value="${adminEntityDetail.wardNameEnglish}" escapeXml="true"></c:out></td>
						<td><c:out value="${adminEntityDetail.wardNameLocal}" escapeXml="true"></c:out></td>
						<td>  <input type="button" id="${offsets*limits+(listAdminRow.index+1)}" value="Restore ward" onclick="publishward('${adminEntityDetail.wardCode}',false,false,false,true)" />                                </td>
					</tr>
				</c:forEach>
				</c:if>
			
			</tbody>
			</table>
			</div>
		
		
		<br></br>
		<br></br>
		
			</div>
			
			</div>
		</c:if>
		<!-- second box for viewinglocalbody start -->
		
</div>
<c:if test="${! empty msgid}">
		<script>
		  alert('${msgid}');
		</script>
</c:if>		
						

					
						
						
					</div>
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

</body>
</html>