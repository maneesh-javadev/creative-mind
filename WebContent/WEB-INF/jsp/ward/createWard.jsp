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


<script type="text/javascript" language="javascript">

$(document).ready(function() {
		// added by kirandeep on 01/09/2014
	 if(isParseJson('${showTable}')){
	    $('#viewData').show();
	    $('#btnpanel').show();
	    $("#demand tr:odd").css("background-color", "#ffffff");
		$("#demand tr:even").css("background-color", "#dedede");
	 }

$('#').dataTable({
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
});
} );
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 

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
				var code='<c:out value="${localGovtBodyForm.lb_lgdPanchayatName}" escapeXml="true"></c:out>';
				$.each(data, function(key, obj) {
					var option = $("<option/>");
					if (obj.operation_state == 'F') {
						if(code == obj.localBodyCode ){
							$(option).attr('selected','selected');
						}
						$(option).attr('disabled', 'disabled');
						$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
						options.append(option);
						
					} else {
						if(code == obj.localBodyCode){
							$(option).attr('selected','selected');
						}
						$(option).val(obj.localBodyCode).text(obj.localBodyNameEnglish);
						options.append(option);
					}
				});
				$('#wardUrbanLocalBody').val(code);
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
  	  for(var k=1;k<tablelength1;k++){
  		  var wardnumber    = 	$('#wardnumber'+k).val();
  		  wardnumber=wardnumber.trim();
  		  var wardName  	=  	$('#wardname'+k).val();
	           for(var l=(k+1);l<tablelength1;l++){
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
   
	 var lengthofEnable='<c:out value="${fn:length(wardList)}" escapeXml="true"></c:out>';
	 
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
 
 function deletecheckbox(id){
	 if($('#delete'+id).is(":checked")){
		
		 $('#checkbox'+id).prop("disabled",true);
	 }else{
		 $('#checkbox'+id).prop("disabled",false);
	 }
	
 }
 
 var deleteid='';
 function getExistingWardCheck(lblc,id){
	 deleteid=id;
		lgdDwrlocalBodyService.getexistingwardinTempTable(lblc, {
			callback : handleLocalBodytempSuccess,
			errorHandler : handleLocalBodyErrortemp
		});
	 
 }
 
 
 function handleLocalBodytempSuccess(data){
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
 
 

function checkWardNumberExist(wardNumber,lblc,fieldId){
	 alert(wardNumber);
	 lgdDwrlocalBodyService.getWardNumberExist(wardNumber,lblc, {
			callback : hadlewardNumberExistsuccess,
			arg: fieldId,
			errorHandler : handleLocalBodyErrortemp
		});
 }
 function hadlewardNumberExistsuccess(data,filedId){
	 
	 alert(data);
	 alert('WardNumber'+fieldId);
	 
 }
</script>
<!-- <script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script> -->
</head>
<%@include file="../common/dwr.jsp"%>
<body >

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
				<label><spring:message code="Label.CREATEWARD" htmlEscape="true"></spring:message></label>
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

				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="createWardforUrbanPost.htm"/>" />
															<input type="hidden" id="formNominations"	name="formNominations" />
															<input type="hidden" id="newWardList" name="newWardList" />
															<input type="hidden" name="flagCode" id="flagCode" value=""/>
															<form:hidden path="listofwards" value="" id="listofwards"></form:hidden>
															<input type="hidden" id="localbodyCode" name="localbodyCode"/>
                                        										<input type="hidden" id="deleteWardList" name="deleteWardList" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message code="Label.LOCALBODY" htmlEscape="true"></spring:message></div>
							
							<ul class="blocklist">	
														<li>																																						
															<input type="hidden" name="stateCode" value="<c:out value='${stateCode}'/>"/>
															<input type="hidden" name="districtCode" value="<c:out value='${districtCode}'/>"/>
														</li>													
														<li>															
																<b><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message> </b> <span class="errormsg">*</span> <br />
															 	<form:select htmlEscape="true" path="lgd_LBTypeName" id="localBodyType" onfocus="validateOnFocus('localBodyType');helpMessage(this,'localBodyTypeMsg');" onchange="hideShowDivforUrbanWard(this.value);getPanchayatListbyStateandlbTypeCode(this.value);" onkeydown="validateOnKeyPessDown(event,'localBodyType','char')" onblur="vlidateOnblur('localBodyType','1','15','c');hideHelp();" class="combofield" style="width: 175px">
																<form:option value="0" htmlEscape="true">
																	<spring:message code="Label.SELECT" htmlEscape="true"> </spring:message>
																</form:option>

																<c:forEach var="localBodyTypelist" varStatus="var" items="${localBodyTypelist}">
																	<form:option value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}" htmlEscape="true"><c:out value="${localBodyTypelist.nomenclatureEnglish}" escapeXml="true"></c:out></form:option>
																</c:forEach>
																</form:select>&nbsp;&nbsp;
	
																<div id="localBodyType_error" class="error"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"></spring:message></div>
																<div id="localBodyTypeMsg" style="display:none"><spring:message code="error.choose.WARDLBTYPE" htmlEscape="true"/></div>
																<div class="errormsg" id="localBodyType_error1"><form:errors path="lgd_LBTypeName" htmlEscape="true"/></div>  
																<div class="errormsg" id="localBodyType_error2" style="display: none" ></div>
														
														</li>																																						
														<li id="tr_district1"  style="display: none;">	
															<b><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message> </b><span class="errormsg">*</span> <br />
															 <form:select htmlEscape="true" path="lb_lgdPanchayatName" class="combofield" id="wardUrbanLocalBody" style="width: 160px" onfocus="validateOnFocus('wardUrbanLocalBody');helpMessage(this,'wardUrbanLocalBodyMsg');" onchange="getCovereSubDistUrbanExWardList(this.value)" onkeydown="validateOnKeyPessDown(event,'wardUrbanLocalBody','char')" onblur="vlidateOnblur('wardUrbanLocalBody','1','15','c');hideHelp();">
																<form:option value="0" htmlEscape="true">
																	<spring:message code="Label.SELECTLOCALBODY"></spring:message>
																</form:option>
															</form:select>&nbsp;&nbsp;
																																											
														<div id="wardUrbanLocalBody_error" class="error"><spring:message code="error.blank.TEHSILPa" htmlEscape="true" text="Please select local body"></spring:message></div>
														<div id="wardUrbanLocalBodyMsg" style="display:none"><spring:message code="error.blank.TEHSILPa" text="Please select local body" htmlEscape="true"/></div>
														<div class="errormsg" id="wardUrbanLocalBody_error1"><form:errors path="lb_lgdPanchayatName" htmlEscape="true"/></div>  
														<div class="errormsg" id="wardUrbanLocalBody_error2" style="display: none" ></div>																												
													</li>
													
													<li>																											
														    <input type="button" id="testingurbanbutton" onclick="getList()" value="Get Data" />														
													</li>
													<!-- comment by pooja on 08-07-2015 to remove coverage ward part -->
													<%-- <li style="display:none">									
										<div class="frmpnlbg">
											<div class="frmtxt" id="divStatelvl">
												<div class="frmhdtitle">
													<spring:message code="Label.COVEREDAREAOFWARD" htmlEscape="true"></spring:message>
												</div>
											
															<div id="divSpecificState">
																
																<ul class="blocklist">
																	<li>																																			
																		<div id="ddLgdWardSubDistrictUListDest_error" class="error"><spring:message code="error.blank.SubdistrictCreateWard" htmlEscape="true"></spring:message></div>																			
																 		<div class="errormsg" id="ddLgdWardSubDistrictUListDest_error1"><form:errors path="lgd_LBSubDistrictList" htmlEscape="true"/></div>  
																 		<div class="errormsg" id="ddLgdWardSubDistrictUListDest_error2" style="display: none" ></div>																																																						
																	</li>
																	<li>
																	<ul class="listing">
																		<li>
																			<b><spring:message htmlEscape="true" code="Label.AVAILSUBDISTRICTLIST"></spring:message>&nbsp;
																			</b><br /> <form:select htmlEscape="true" path="lgd_LBSubDistrictSourceLatDCAUmapped" class="frmtxtarea" id="ddLgdWardSubDistrictUListSource" style="height: 110px; width: 233px" multiple="true"> </form:select><br /> <br />
																		</li>

																		<li>
																			<input type="button" value=" Whole &gt;&gt;" style="width: 80px" onclick="addItemforWard('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource','FULL',true,'T');" />
																			<br /> <input type="button" id="btnremoveOneULB" name="Submit4" value="Back &lt;" onclick="removeItemVillListSubListWardULB('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource',true)" /><br />
																			<input type="button" value="Part &gt;&gt;" style="width: 70px" onclick="addItemforSubDistrictWardPart('ddLgdWardSubDistrictUListDest','ddLgdWardSubDistrictUListSource', 'PART',true);" />
																		</li>

																		<li>
																			<b><spring:message htmlEscape="true" code="Label.CONTRIBUTSUBDISTRICTLIST"></spring:message><span class="errormsg">*</span>
																		
																			</b> <br />
																			 <form:select name="select6" htmlEscape="true" id="ddLgdWardSubDistrictUListDest" size="1" multiple="multiple" path="lgd_LBSubDistrictList" class="frmtxtarea" style="height: 110px; width: 242px"> </form:select><br /> 																																		
																		</li>
																	</ul>
																	</li>
																</ul>
																
															</div>
														
											</div>
										</div>									
								</li> --%>
											</ul>
					</div>						
		<div >
		<div id="UniqueWardNameError" style="color: red;"></div>
							<div id="wardnameMsg" style="display:none"><spring:message code="error.blank.WARDNAME" htmlEscape="true"/></div>						 
							<div id="UniqueWardCodeError" style="color: red;"></div>
							<div id="wardnumberMsg" style="display:none"><spring:message code="error.blank.WARDNUMBER" htmlEscape="true"/></div>
							<div class="errormsg" id="wardnumber_error1"><form:errors path="ward_number" htmlEscape="true"/></div>  
							<div class="errormsg" id="wardnumber_error2" style="display: none" ></div>	
							<div class="errormsg" id="wardnamdlocal_error1"><form:errors path="ward_NameLocal" htmlEscape="true"/></div>	
							<div id="wardnamdlocalMsg" style="display:none"><spring:message code="error.blank.WARDNAMELOCAL" htmlEscape="true"/></div>
							<div class="errormsg" id="wardnamdlocal_error2" style="display: none" ></div>	
		<div class="frmpnlbg">
		<div id="viewData"  class="frmtxt" style='display: none'>
			<div class="frmhdtitle">
				<spring:message code="Label.listofWards" text="List of Wards"></spring:message>
			</div>										 		
			<spring:message code="Label.NoofWards" text="Enter No. of  New Wards to be Added"></spring:message>
		
		<input type="text" id="noofWards" maxlength="3" onkeypress="return validateNumericKeys(event);" class="frmfield" style="width:200px"></input><input type="button" onclick="addarow('1')" value="Add Rows" />	<br /><br /><br />	
					 
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="demand">
			<thead>
				<tr class="tblRowTitle tblclear">
					<th align="left">Serial No.</th>
					<th><spring:message    code="Label.enableeDiting" htmlEscape="true" text="Enable a Ward For Editing"></spring:message></th>
					<th align="left"><spring:message	code="Label.WARDNUMBER" htmlEscape="true"></spring:message></th>
					<th align="left"><spring:message	code="Label.WARDNAMEEnglish" htmlEscape="true" text="Ward Name(In English)"></spring:message> </th>
					<th align="left"><spring:message    code="Label.WARDNAMEINLOCAL" htmlEscape="true"></spring:message></th>
				
					<th align="left"><spring:message    code="Label.Delete" htmlEscape="true" text="Delete Ward"></spring:message></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<input type="hidden" name="wardName" value="" />
			<input type="hidden" name="listWardDetails" value="<c:out value='${wardList}' escapeXml='true'></c:out>"/>
			<c:forEach var="adminEntityDetail" varStatus="listAdminRow"
														items="${wardList}">
					<tr>
						<td><c:out value="${offsets*limits+(listAdminRow.index+1)}" escapeXml="true"></c:out></td>
						
						<td align="center"><input type="checkbox" text="Update" onchange="" value="Update" id="checkbox${offsets*limits+(listAdminRow.index+1)}" onclick="getExistingWardCheck('${adminEntityDetail.wardCode}','${offsets*limits+(listAdminRow.index+1)}'); enableEditing('${offsets*limits+(listAdminRow.index+1)}')"></input>
						    <input type="checkbox" text=check value="<c:out value='${adminEntityDetail.wardCode}'/>" id="check${offsets*limits+(listAdminRow.index+1)}" style="display: none" ></input>
						</td>
						<td>  <input type="text" id="wardnumber${offsets*limits+(listAdminRow.index+1)}" name="wardnumber${offsets*limits+(listAdminRow.index+1)}" style="width: 200px;background: none repeat scroll 0 0 lightgrey;" size="10" maxlength="10" value="<c:out value='${adminEntityDetail.wardNumber}'/>" disabled="true"
																class="frmfield" 
																onfocus="validateOnFocus('wardnumber');changeErrorClass(this.id);"
																onblur="UniqueWardValidation(this.value,2,'${offsets*limits+(listAdminRow.index+1)}');vlidateOnblur('wardnumber','1','15','c');"
																onkeypress="return validateNumberWardNumUrban(event);"></input>
							  <input type="hidden" id="wardnumberOld${offsets*limits+(listAdminRow.index+1)}" value="<c:out value="${adminEntityDetail.wardNumber}" escapeXml="true"></c:out>" />
							  						
						
						</td>
						
						<td> <input type="text" id="wardname${offsets*limits+(listAdminRow.index+1)}" name ="wardName${offsets*limits+(listAdminRow.index+1)}" style="width: 200px;   background: none repeat scroll 0 0 lightgrey;" value="<c:out value="${adminEntityDetail.wardNameInEnglish}" escapeXml="true"></c:out>" disabled="true"
																class="frmfield" maxlength="250" 
																onfocus="validateOnFocus('wardname');changeErrorClass(this.id);"
																onblur="UniqueWardValidation(this.value,1,'${offsets*limits+(listAdminRow.index+1)}');vlidateOnblur('wardname','1','15','m');"
																onkeypress="return validateSpecialCharactersUpdateStandardCodeLocal(event);"
																htmlEscape="true"></input>
							<input type="hidden" id="wardnameOld${offsets*limits+(listAdminRow.index+1)}" value="<c:out value="${adminEntityDetail.wardNameInEnglish}" escapeXml="true"></c:out>" />							

						</td>	
						
						<td> <input type="text"	id="wardnamelocal${offsets*limits+(listAdminRow.index+1)}" name="wardnamelocal${offsets*limits+(listAdminRow.index+1)}" disabled="true" value="<c:out value="${adminEntityDetail.wardNameInLocal}" escapeXml="true"></c:out>"
																onfocus="validateOnFocus('wardnamelocal');changeErrorClass(this.id);"
																style="width: 200px;background: none repeat scroll 0 0 lightgrey;" maxlength="250"
																class="frmfield" onblur="validateSpecialCharactersWardNLocal(this.value);"></input>
							<input type="hidden" id="wardnamelocalOld${offsets*limits+(listAdminRow.index+1)}" value="<c:out value="${adminEntityDetail.wardNameInLocal}" escapeXml="true"></c:out>" />					
						
						
						</td>
						<td align="center"><input type="checkbox" text="Delete" onchange="getExistingWardCheck('${adminEntityDetail.wardCode}','${offsets*limits+(listAdminRow.index+1)}'); deletecheckbox('${offsets*limits+(listAdminRow.index+1)}')" value="delete" id="delete${offsets*limits+(listAdminRow.index+1)}" onclick=""></input>
						    <input type="checkbox" text=delete value="<c:out value='${adminEntityDetail.wardCode}'/>" id="deletev${offsets*limits+(listAdminRow.index+1)}" style="display: none" ></input>
						</td>
						
						<td></td>
						<td></td>
						<td>
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="btnpnl" id="btnpanel" style="display:none">
				<label> <input type="button" name="Save" id="btnSave" class="btn" value=<spring:message code="Button.SAVE" htmlEscape="true"></spring:message> onclick="return saveEnabledvalues(); " /></label>												
				<label><input type="button" onclick="addarow('2')" value="Add Another Row" /></label>
				<label><input type="button" class="btn" name="Submit6" value="<spring:message code="Button.CLEAR"></spring:message>" onclick="getList();" /></label>		 	
				<label> <input type="button" class="btn" name="Cancel" value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message> id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>										
		</div>		
	</div>			
</div>
					
						
						
					</div>
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

</body>
</html>