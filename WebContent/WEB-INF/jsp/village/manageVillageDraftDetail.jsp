<%@include file="../common/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String allowedNoOfAttachment = "1";
	String uploadLocation = "C:\\files";
	String allowedFileType = "gif,jpg,pdf,png";
	Long allowedTotalFileSize = 5L;
	Long allowedIndividualFileSize = 1L;
	String requiredTitle = "no";
	String uniqueTitle = "no";%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/disturbedEntities.js"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<script src="js/validation.js"></script>
<script src="js/orderValidate.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCoveredLandRegionByULBService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraftVillageList.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrSurveyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrVillageServiceForDraft.js'></script>
	
<script type="text/javascript" language="javascript"></script>
<title><spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message></title>


<script src="js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="js/jquery.MultiFile.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="<%=contextpthval %>/jquery-1.8.3.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" />
<script type="text/javascript" src="js/modify.js" charset="utf-8"></script>
<script type="text/javascript">

	var mandatoryFlag;
	var renameListOnLoadVal;
	
	 function hideAll()
		{
			mandatoryFlag='${mandatoryFlag}';
		 	$("#EffectiveFutureDate_error").hide();
			$("#OrderFutureDate_error").hide();
		 	$("#EffectiveDateBlank_error").hide();
			$("#aliasNameEngData_error").hide();
			$("#aliasNameLocData_error").hide();
			//$("#txtCensus_error").hide();
			$("#OrderNoBlank_error").hide();
			$("#OrderNoDataValid_error").hide();
			$("#OrderDateBlank_error").hide();
			$("#OrderDateData_error").hide();
			$("#OrderDateValid_error").hide();
			$("#GuzpubDateValid_error").hide();
			$("#error_govorder").hide();
			$("#EffectiveDateData_error").hide();
			$("#sscode_error").hide();
			$("#GuzpubDateCompareOrderDate_error").hide();
			$("#GuzpubDateBlankOrderDate_error").hide();	
		}
	 function hideError(){
		 $("#errProSelectDist").hide();
	 }
	 var previousEntityName;
	 function LoadMe()
		{
			previousEntityName=document.getElementById('OfficialAddress').value;
			document.getElementById('villagenameInEngOld').value = previousEntityName;
		}
	 
	 if ( window.addEventListener ) { 
		    window.addEventListener( "load",LoadMe, false );
		 }
		 else 
		    if ( window.attachEvent ) { 
		       window.attachEvent( "onload", LoadMe );
		 } else 
		       if ( window.onLoad ) {
		          window.onload = LoadMe;
		 }
	 function setEffectiveDatetoOrderDate(orderDate)
	 {
		
		 //alert(orderDate);
		 if(orderDate!="" && orderDate!=undefined)
		 document.getElementById('EffectiveDate').value=orderDate;
	 }
	 function incrementCount()
		{
	    	document.getElementById('surveytable').style.visibility = "visible";
	    	document.getElementById("errBlockCode").innerHTML="";
	    	 var ptable = document.getElementById('ptablePerson');    	 
	    	 var numFlds = document.modifyVillageCmd.surveyNumber.length;
	    	 if(numFlds>9)
			 {
			 alert("Cannot Add More Survey No");
			 return false;
			 }
	    	  for (var x=0; x<numFlds; x++) {
	    	  for (var y=x+1; y<numFlds; y++) {
	    	      if (document.modifyVillageCmd.surveyNumber[x].value == document.modifyVillageCmd.surveyNumber[y].value) {
	    	    	  document.getElementById("errBlockCode").innerHTML = "<font color='red'><br><spring:message code="Error.Unique.SurveNo"/></font>"; 
	    	           document.modifyVillageCmd.surveyNumber[y].value="";    	        
	    	        return false;    	       	      
	    	      }    	       	    	  
	    	    }
	    	  }   	    	 
	    	  var lastElement = ptable.rows.length;
	    	  var index = lastElement;
	    	  var row = ptable.insertRow(lastElement);
	    	  var cellLeft = row.insertCell(0);
	    	  var textNode = document.createTextNode(index);
	    	  cellLeft.appendChild(textNode);
	    	  var cellText = row.insertCell(1);
	    	  var element = document.createElement('input');
	    	  element.type = 'text';
	    	  element.name = 'surveyNumber';
	    	  element.setAttribute("class", "frmfield");
	    	  element.id = 'person' + index;
	    	  element.size = 26;   
	    	  element.setAttribute('maxlength','10');
	    	  element.onblur = function () {
	    		  checkmultipleSno(element.id);
	    		  };
	    	  cellText.appendChild(element);
	    	  document.getElementById("psize").value=index;   
	    	  
		}
	 function fillVillage(num,villName){		
			
			if(document.getElementById("reNameFlagId"+num).checked ==true){
				var split = villName.substring(0,villName.length-6);			
				var VillageName = split.replace(/^\s+|\s+$/g,"");
				document.getElementById("reNamedVillId"+num).value = VillageName;
				document.getElementById("reNamedVillIdNew"+num).disabled = false;
				
			}
			else{
				if(document.getElementById("reNameFlagId"+num).checked == false){
					document.getElementById("reNamedVillId"+num).value = "";
					document.getElementById("reNamedVillIdNew"+num).disabled = true;
					document.getElementById("reNamedVillIdNew"+num).value = "";
				}
			}
			
			
		}
	 
	    function removeRow()
	    {
	      document.getElementById("errBlockCode").innerHTML="";
	      var ptable = document.getElementById('ptablePerson');
	      var lastElement = ptable.rows.length;
	      if (lastElement > 2) ptable.deleteRow(lastElement - 1);
	      if(document.getElementById("psize").value>1)
	    	{
	    	   document.getElementById("psize").value = document.getElementById("psize").value-1;
	    	}
	      if (document.getElementById("psize").value<2)
	    	  {
	    	  hideTable();
	    	  }
	    }
	    function chekcalphanumeric(inputtxt, num) {
	    	var status = true;
	    	if (inputtxt.match(/\S/)) {
	    		var obj = "";
	    		if (num == 3)
	    			obj = document.getElementById('sscode');
	    		else if (num == 4)
	    			obj = document.getElementById('surveyNumber');
	    		else if (num == 5)
	    			obj = document.getElementById('txtSscode');
	    		var retVal = false;
	    		var letterNumber = /^[0-9a-zA-Z]+$/;
	    		if (inputtxt.match(letterNumber))
	    			retVal = true;
	    		if (retVal == false) {

	    			if (num == 3 || num == 5)
	    				alert("State Specific Code  contains invalid characters Use AlphaNumerics !");
	    			else if (num == 4)
	    				alert("Survey No. contains invalid characters Use AlphaNumerics !");

	    			obj.value = "";
	    			status = false;
	    		}
	    	}
	    	return stautus;
	    }
	    function renameListMethod(val){		
	    	if(val == 1){		
				try{
			oList = document.modifyVillageCmd.elements["contributedVillages"];
				}catch(err){
					alert("err"+err);
				}
			if(oList.options.length > 0)
			{		
				
				var table = document.getElementById("renameList");
				table.style.display="inline";
				n  = table.rows.length;	
				
					
				for(var i = table.rows.length - 1; i > 0; i--)
					{			 
					table.deleteRow(i);
				  
				    }
			
			if(n>=1){
				var count =0;
			    
				for (var i=0;i<oList.options.length;i++)
				{ 
					var village = oList.options[i].value;
					var villageText =  oList.options[i].text;	
					if(village.indexOf("PART") != -1){
						   
				            var rowCount = table.rows.length;
				            var row = table.insertRow(rowCount);
				 
				            var cell1 = row.insertCell(0);
				            cell1.innerHTML = rowCount ;
				            
				            var cell2 = row.insertCell(1);
				            var element1 = document.createElement("input");
				            element1.type = "text";
				            element1.name = "villName"+count;
				            element1.id = "villName"+count;
				            element1.value = villageText;
				            element1.size = 30;
				            element1.setAttribute('readonly', 'readonly');
				            element1.setAttribute('class','frmfield');
				            cell2.appendChild(element1);
				            
				            var cell3 = row.insertCell(2);
				            var element2 = document.createElement("input");
				            element2.name = "reNameFlag"+count;
				            element2.id = "reNameFlagId"+count;
				            element2.type = "checkbox";					          
				            element2.setAttribute('onClick',"fillVillage("+count+",'"+villageText+"')"); //function(){fillVillage(rowCount,villageText);};
				            cell3.appendChild(element2);
				            cell3.setAttribute('align','center');
				            
				            var cell4 = row.insertCell(3);
				            var element3 = document.createElement("input");
				            element3.type = "text";
				            element3.name = "reNamedVillName"+count;
				            element3.id = "reNamedVillId"+count;
				            element3.setAttribute('class','frmfield');
				            element3.setAttribute('disabled', 'true');
				            cell4.appendChild(element3);
				            
				            var cell5 = row.insertCell(4);
				            var element4 = document.createElement("input");
				            element4.type = "text";
				            element4.name = "reNamedVillNameNew"+count;
				            element4.id = "reNamedVillIdNew"+count;
				            element4.setAttribute('class','frmfield');
				            element4.setAttribute('disabled', 'true');
				            cell5.appendChild(element4);
				            
				            var cell6 = row.insertCell(5);
				            var element5 = document.createElement("input");
				            element5.type = "hidden";
				            element5.name = "villId"+count;
				            element5.id = "villId"+count;
				            element5.value = village;
				            element5.setAttribute('class','frmfield');			          
				            cell5.appendChild(element5);
							count++;
							document.getElementById("count").value = count;
					}

				}
				
			  }			
			}	
			else{
				document.getElementById("rename").checked = false;
				alert("Please choose contributing Villages");			
			}
			
		}
			
			else{
				var table = document.getElementById("renameList");
				table.style.display="none";
			}
		}
	    
	    
	    
	    function renameListMethodOnLoad(val,renameList){		
	    	
	    	renameListOnLoadVal=renameList;
	    	if(val == 1){		
				try{
			oList = document.modifyVillageCmd.elements["contributedVillages"];
				}catch(err){
					alert(err);
				}
			if(oList.options.length > 0)
			{		
				var table = document.getElementById("renameList");
				table.style.display="inline";
				n  = table.rows.length;	
			for(var i = table.rows.length - 1; i > 0; i--)
			{			 
				table.deleteRow(i);
			}
			if(n>=1){
				var count =0;
				for (var i=0;i<oList.options.length;i++)
				{ 
			
					var village = oList.options[i].value;
					var villageText =  oList.options[i].text;	
					if(village.indexOf("PART") != -1){
							var renameFlag=false;
							var renameSplit =  renameList.split(",");
							var renameVilNameSplit ="";
							var reNameVilNameChanged="";
							for(var j=0;j<renameSplit.length;j++){
								renameVilNameSplit=renameSplit[j].split(":");
								for(var k=0;k<renameVilNameSplit.length;k++){
									if((village.substr(0, village.length - 4)) == renameVilNameSplit[0]){
										reNameVilNameChanged=renameVilNameSplit[1];
										renameFlag=true;
										break;
									}
								}
							}
							
						if(renameFlag==true){
							var rowCount = table.rows.length;
				            var row = table.insertRow(rowCount);
				 
				            var cell1 = row.insertCell(0);
				            cell1.innerHTML = rowCount ;
				            
				            var cell2 = row.insertCell(1);
				            var element1 = document.createElement("input");
				            element1.type = "text";
				            element1.name = "villName"+count;
				            element1.id = "villName"+count;
				            element1.value = villageText;
				            element1.size = 30;
				            element1.setAttribute('readonly', 'readonly');
				            element1.setAttribute('class','frmfield');
				            cell2.appendChild(element1);
				           
				            	var cell3 = row.insertCell(2);
					            var element2 = document.createElement("input");
					            element2.name = "reNameFlag"+count;
					            element2.id = "reNameFlagId"+count;
					            element2.type = "checkbox";	
					            element2.checked=true;
					            element2.setAttribute('onClick',"fillVillage("+count+",'"+villageText+"')"); //function(){fillVillage(rowCount,villageText);};
					            cell3.appendChild(element2);
					            cell3.setAttribute('align','center');
					            
					            var split = villageText.substring(0,villageText.length-6);			
								var VillageName = split.replace(/^\s+|\s+$/g,"");
								
					            var cell4 = row.insertCell(3);
					            var element3 = document.createElement("input");
					            element3.type = "text";
					            element3.name = "reNamedVillName"+count;
					            element3.id = "reNamedVillId"+count;
					            element3.value=VillageName;
					            element3.setAttribute('class','frmfield');
					            element3.setAttribute('disabled', 'true');
					            cell4.appendChild(element3);
					            
								var cell5 = row.insertCell(4);
					            var element4 = document.createElement("input");
					            element4.type = "text";
					            element4.name = "reNamedVillNameNew"+count;
					            element4.id = "reNamedVillIdNew"+count;
					            element4.value = reNameVilNameChanged;
					            element4.setAttribute('class','frmfield');
					            //element4.setAttribute('disabled', 'false');
					            cell5.appendChild(element4);
					            
					            var cell6 = row.insertCell(5);
					            var element5 = document.createElement("input");
					            element5.type = "hidden";
					            element5.name = "villId"+count;
					            element5.id = "villId"+count;
					            element5.value = village;
					            element5.setAttribute('class','frmfield');			          
					            cell5.appendChild(element5);
								count++;
								document.getElementById("count").value = count;
									
				            }else{
				            	
				            	    var rowCount = table.rows.length;
						            var row = table.insertRow(rowCount);
						 
						            var cell1 = row.insertCell(0);
						            cell1.innerHTML = rowCount ;
						            
						            var cell2 = row.insertCell(1);
						            var element1 = document.createElement("input");
						            element1.type = "text";
						            element1.name = "villName"+count;
						            element1.id = "villName"+count;
						            element1.value = villageText;
						            element1.size = 30;
						            element1.setAttribute('readonly', 'readonly');
						            element1.setAttribute('class','frmfield');
						            cell2.appendChild(element1);
						            
						            var cell3 = row.insertCell(2);
						            var element2 = document.createElement("input");
						            element2.name = "reNameFlag"+count;
						            element2.id = "reNameFlagId"+count;
						            element2.type = "checkbox";					          
						            element2.setAttribute('onClick',"fillVillage("+count+",'"+villageText+"')");
						            cell3.appendChild(element2);
						            cell3.setAttribute('align','center');
						            
						            var cell4 = row.insertCell(3);
						            var element3 = document.createElement("input");
						            element3.type = "text";
						            element3.name = "reNamedVillName"+count;
						            element3.id = "reNamedVillId"+count;
						            element3.setAttribute('class','frmfield');
						            element3.setAttribute('disabled', 'true');
						            cell4.appendChild(element3);
						            
						            var cell5 = row.insertCell(4);
						            var element4 = document.createElement("input");
						            element4.type = "text";
						            element4.name = "reNamedVillNameNew"+count;
						            element4.id = "reNamedVillIdNew"+count;
						            element4.setAttribute('class','frmfield');
						            element4.setAttribute('disabled', 'true');
						            cell5.appendChild(element4);
						            
						            var cell6 = row.insertCell(5);
						            var element5 = document.createElement("input");
						            element5.type = "hidden";
						            element5.name = "villId"+count;
						            element5.id = "villId"+count;
						            element5.value = village;
						            element5.setAttribute('class','frmfield');			          
						            cell5.appendChild(element5);
									count++;
									document.getElementById("count").value = count;
				            }
				            
					}

				}
				
			  }			
			}	
			else{
				document.getElementById("rename").checked = false;
				alert("Please choose contributing Villages");			
			}
			
		}
			
			else{
				var table = document.getElementById("renameList");
				table.style.display="none";
			}
		}
	    
	    
 function addItemVillageForDraft(list1, list2, val, doAddVal) {
	    	if (document.getElementById(list2).selectedIndex >= 0) {
	    		for ( var j = 0; j < document.getElementById(list2).options.length; j++) {
	    			if (document.getElementById(list2).options[j].selected == true) {
	    				if (doAddVal) {
	    					$('#' + list1).append(
	    							"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text + " ("
	    									+ val + ")</option>");
	    				} else {
	    					$('#' + list1).append(
	    							"<option value=" + document.getElementById(list2)[j].value + " >" + document.getElementById(list2)[j].text + "</option>");
	    				}

	    			}
	    		}
	    		removeSelectedValue(list2);
	    		
	    	}
	    	if(document.getElementById("rename").checked)
    			renameListMethodAfterLoad(1);
	    }
 function removevillageandsurveynoForDraft(list1, list2, doRemoveVal) {
	 var selObj = document.getElementById('villageListNew');
		for ( var i = 0; i < selObj.options.length; i++) {
			if (selObj.options[i].selected)
				if ((selObj.options[i].value).substr(selObj.options[i].value.length - 4) == "PART") {
					$("#surveyListMain").empty();
					$("#surveyListNew").empty();
					document.getElementById("survernoset").value = "0";
					break;
				}

		}
	
		if (document.getElementById(list1).selectedIndex >= 0) {
			for ( var i = 0; i <  document.getElementById(list1).options.length; i++) 
				if (document.getElementById(list1).options[i].selected == true)
				if (doRemoveVal)
					$('#' + list2).append(
							"<option value="
									+ document.getElementById(list1)[i].value.substr(0, document.getElementById(list1)[i].value.length - 4)
									+ " >"
									+ document.getElementById(list1)[i].text.substr(0, document
											.getElementById(list1)[i].text.length - 6) + "</option>");
				else
					$('#' + list2).append(
							"<option value=" + document.getElementById(list1)[i].text + " >"
									+ document.getElementById(list1)[i].text + "</option>");
				removeSelectedValue(list1);
		
	}
		if(document.getElementById("rename").checked)
			renameListMethodAfterLoad(1);
 }
 
 function removeAllSelectedVillagesForDraft(list1, list2, doRemoveVal) {
		$("#surveyListMain").empty();
		$("#surveyListNew").empty();
		document.getElementById("survernoset").value = "0";
		for ( var i = document.getElementById(list1).length - 1; i >= 0; i--) {
			document.getElementById(list1).selectedIndex = i;

			if (doRemoveVal)
				$('#' + list2).append(
						"<option value="
								+ document.getElementById(list1).value.substr(0, document.getElementById(list1).value.length - 4)
								+ " >"
								+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text.substr(0, document
										.getElementById(list1)[document.getElementById(list1).selectedIndex].text.length - 6) + "</option>");
			else
				$('#' + list2).append(
						"<option value=" + document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + " >"
								+ document.getElementById(list1)[document.getElementById(list1).selectedIndex].text + "</option>");
			removeSelectedValue(list1);
		}
		if(document.getElementById("rename").checked)
			renameListMethodAfterLoad(1);
	}
 function renameListMethodAfterLoad(val){		
	    	var renameList=renameListOnLoadVal;
	    	if(val == 1){		
				try{
			oList = document.modifyVillageCmd.elements["contributedVillages"];
				}catch(err){
					alert(err);
				}
			if(oList.options.length > 0)
			{		
				var table = document.getElementById("renameList");
				table.style.display="inline";
				n  = table.rows.length;	
				document.getElementById("rename").checked = true;
			for(var i = table.rows.length - 1; i > 0; i--)
			{			 
				table.deleteRow(i);
			}
			if(n>=1){
				var count =0;
				for (var i=0;i<oList.options.length;i++)
				{ 
					
					var village = oList.options[i].value;
					var villageText =  oList.options[i].text;	
					if(village.indexOf("PART") != -1){
							var renameFlag=false;
							if(typeof(renameList) != "undefined"){
								var renameSplit =  renameList.split(",");
								var renameVilNameSplit ="";
								var reNameVilNameChanged="";
								for(var j=0;j<renameSplit.length;j++){
									renameVilNameSplit=renameSplit[j].split(":");
									for(var k=0;k<renameVilNameSplit.length;k++){
										if((village.substr(0, village.length - 4)) == renameVilNameSplit[0]){
											reNameVilNameChanged=renameVilNameSplit[1];
											renameFlag=true;
											break;
										}
									}
								}
							}
						if(renameFlag==true){
							var rowCount = table.rows.length;
				            var row = table.insertRow(rowCount);
				 
				            var cell1 = row.insertCell(0);
				            cell1.innerHTML = rowCount ;
				            
				            var cell2 = row.insertCell(1);
				            var element1 = document.createElement("input");
				            element1.type = "text";
				            element1.name = "villName"+count;
				            element1.id = "villName"+count;
				            element1.value = villageText;
				            element1.size = 30;
				            element1.setAttribute('readonly', 'readonly');
				            element1.setAttribute('class','frmfield');
				            cell2.appendChild(element1);
				           
				            	var cell3 = row.insertCell(2);
					            var element2 = document.createElement("input");
					            element2.name = "reNameFlag"+count;
					            element2.id = "reNameFlagId"+count;
					            element2.type = "checkbox";	
					            element2.checked=true;
					            element2.setAttribute('onClick',"fillVillage("+count+",'"+villageText+"')"); //function(){fillVillage(rowCount,villageText);};
					            cell3.appendChild(element2);
					            cell3.setAttribute('align','center');
					            
					            var split = villageText.substring(0,villageText.length-6);			
								var VillageName = split.replace(/^\s+|\s+$/g,"");
								
					            var cell4 = row.insertCell(3);
					            var element3 = document.createElement("input");
					            element3.type = "text";
					            element3.name = "reNamedVillName"+count;
					            element3.id = "reNamedVillId"+count;
					            element3.value=VillageName;
					            element3.setAttribute('class','frmfield');
					            element3.setAttribute('disabled', 'true');
					            cell4.appendChild(element3);
					            
								var cell5 = row.insertCell(4);
					            var element4 = document.createElement("input");
					            element4.type = "text";
					            element4.name = "reNamedVillNameNew"+count;
					            element4.id = "reNamedVillIdNew"+count;
					            element4.value = reNameVilNameChanged;
					            element4.setAttribute('class','frmfield');
					            //element4.setAttribute('disabled', 'false');
					            cell5.appendChild(element4);
					            
					            var cell6 = row.insertCell(5);
					            var element5 = document.createElement("input");
					            element5.type = "hidden";
					            element5.name = "villId"+count;
					            element5.id = "villId"+count;
					            element5.value = village;
					            element5.setAttribute('class','frmfield');			          
					            cell5.appendChild(element5);
								count++;
								document.getElementById("count").value = count;
									
				            }else{
				            	
				            	    var rowCount = table.rows.length;
						            var row = table.insertRow(rowCount);
						 
						            var cell1 = row.insertCell(0);
						            cell1.innerHTML = rowCount ;
						            
						            var cell2 = row.insertCell(1);
						            var element1 = document.createElement("input");
						            element1.type = "text";
						            element1.name = "villName"+count;
						            element1.id = "villName"+count;
						            element1.value = villageText;
						            element1.size = 30;
						            element1.setAttribute('readonly', 'readonly');
						            element1.setAttribute('class','frmfield');
						            cell2.appendChild(element1);
						            
						            var cell3 = row.insertCell(2);
						            var element2 = document.createElement("input");
						            element2.name = "reNameFlag"+count;
						            element2.id = "reNameFlagId"+count;
						            element2.type = "checkbox";					          
						            element2.setAttribute('onClick',"fillVillage("+count+",'"+villageText+"')");
						            cell3.appendChild(element2);
						            cell3.setAttribute('align','center');
						            
						            var cell4 = row.insertCell(3);
						            var element3 = document.createElement("input");
						            element3.type = "text";
						            element3.name = "reNamedVillName"+count;
						            element3.id = "reNamedVillId"+count;
						            element3.setAttribute('class','frmfield');
						            element3.setAttribute('disabled', 'true');
						            cell4.appendChild(element3);
						            
						            var cell5 = row.insertCell(4);
						            var element4 = document.createElement("input");
						            element4.type = "text";
						            element4.name = "reNamedVillNameNew"+count;
						            element4.id = "reNamedVillIdNew"+count;
						            element4.setAttribute('class','frmfield');
						            element4.setAttribute('disabled', 'true');
						            cell5.appendChild(element4);
						            
						            var cell6 = row.insertCell(5);
						            var element5 = document.createElement("input");
						            element5.type = "hidden";
						            element5.name = "villId"+count;
						            element5.id = "villId"+count;
						            element5.value = village;
						            element5.setAttribute('class','frmfield');			          
						            cell5.appendChild(element5);
									count++;
									document.getElementById("count").value = count;
				            }
				            
					}

				}
				
			  }			
			}	
			else{
				document.getElementById("rename").checked = false;
				alert("Please choose contributing Villages");			
				var table = document.getElementById("renameList");
				table.style.display="none";
			}
			
		}
			
			else{
				var table = document.getElementById("renameList");
				table.style.display="none";
			}
		}
  function modifyVillageVal(vilname) {
		var disid = document.getElementById("ddDistrict").value;
		var subdisid = document.getElementById("ddSubdistrict").value;
		if (previousEntityName != vilname) {
			lgdDwrVillageService.VilageExist(subdisid, vilname, {
				callback : handleDistrictSuccess,
				errorHandler : handleDistrictError
			});
		}
	}

	function handleDistrictSuccess(data) {
		document.getElementById("errProSelectDist").innerHTML = "";
		/*Modified by Pooja on 21-07-2015 for display difft. error msg*/
		if (data=='P') {
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><br>Same Village Name already exist.</font>";
			document.getElementById("OfficialAddress").value = previousEntityName;
			document.getElementById("OfficialAddress").focus();
		}
		else if(data == 'S'){
			document.getElementById("errProSelectDist").innerHTML = "<font color='red'><br>Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.</font>";
			document.getElementById("OfficialAddress").value = previousEntityName;
			document.getElementById("OfficialAddress").focus();
		}
	}

	function handleDistrictError() {
		document.getElementById("OfficialAddress").value = "";
		document.getElementById("OfficialAddress").focus();
	}
 function checkAll(field) {
		var table = document.getElementById("renameList");
		n = table.rows.length;
		for ( var i = 0; i < n - 1; i++) {
			document.getElementById("reNameFlagId" + i).checked = true;
			var vilNameArray = document.modifyVillageCmd.villName;
			var villName = document.getElementById("villName" + i).value;
			//var split = villName.substring(0,villName.length-6);
			var VillageName = villName.replace(/^\s+|\s+$/g, "");
			document.getElementById("reNamedVillId" + i).value = VillageName;
			document.getElementById("reNamedVillIdNew" + i).disabled = false;
		}
	}
	function uncheckAll(field) {
		var table = document.getElementById("renameList");
		n = table.rows.length;
		for ( var i = 0; i < n - 1; i++) {
			document.getElementById("reNameFlagId" + i).checked = false;
			document.getElementById("reNamedVillId" + i).value = "";
			document.getElementById("reNamedVillIdNew" + i).disabled = true;
			document.getElementById("reNamedVillIdNew" + i).value = "";
		}

	}
	    dwr.engine.setActiveReverseAjax(true);    
	    function validateVillageDraft(){
	    	var mandatory_error=false;
			var fielattach = document.getElementById('attachFile1').value;
			
		    var filecount = document.getElementById('govtfilecount').value;
		    
		    var error=false;	
			var aliasNameInEn=document.getElementById('txtaliasEnglish').value; 
		    var aliasNameInLc=document.getElementById('txtAliaslocal').value; 
			/* var cecsus2011=document.getElementById('txtCensus').value;  */
			var orderDate= document.getElementById('OrderDate').value; 
		    var effectiveDate=document.getElementById('EffectiveDate').value; 
			var GuzpubDate=document.getElementById('GazPubDate').value; 
			var OrderNo=document.getElementById('OrderNo').value;
			var sscode=document.getElementById('txtSscode').value; 
			/* alert("file length:"+fielattach.length);
			alert("filecount:"+filecount);
		
			
			alert("mandatory:"+mandatoryFlag);
			alert("filecount:"+document.getElementById('govtfilecount').value );  */
			
			if(OrderNo!="" || orderDate!=""  || effectiveDate!="" || GuzpubDate!="" || filecount>0 || fielattach!=0)
				{
				if(OrderNo=="")
					{
					$("#OrderNoBlank_error").show();
					error=true;
					}
				if(orderDate=="")
					{
					$("#OrderDateBlank_error").show();
					error=true;
					}
				
				if(effectiveDate=="")
					{
					$("#EffectiveDateBlank_error").show();
					error=true;
					}
				
				if(filecount==0 && fielattach==0)
					{
					$("#error_govorder").show();
					error=true;
					}
					
				}
			
			if(mandatoryFlag==true)
				{
				if(OrderNo=="")
				{
				$("#OrderNoBlank_error").show();
				mandatory_error=true;
				error=true;
				}
				}
		
				if(OrderNo!="")
				{
				if(!validateOrderNoGeneral(OrderNo))
					{
					error=true;
					}
				}
			
			
				if(mandatoryFlag==true)
				{
					if(orderDate=="")
					{
					$("#OrderDateBlank_error").show();
					mandatory_error=true;
					error=true;
					}
				}
				
			 if(orderDate!="")
				{
				if(!validateDateDDMMYYY(orderDate))
					{
					$("#OrderDateValid_error").show();
					error=true;
					}
				}
			
			if(GuzpubDate!="")
				{
				if(!validateDateDDMMYYY(GuzpubDate))
					{
					$("#GuzpubDateValid_error").show();
					error=true;
					}
				}
			 
			
			
			
			if (!validateVillageNameEnglish(aliasNameInEn, '#aliasNameEngData_error'))
				error = true;
			if (!validateEntityNameLocalData(aliasNameInLc, '#aliasNameLocData_error'))
				error = true;
			
			
			/* if(!validateEntityNumbricData(cecsus2011,"#txtCensus_error"))
				error=true; */
			
		 if(!validateEntityAlphaNumbericData(sscode,"#sscode_error"))
				error=true; 
			if(mandatoryFlag==true)
				{
				if (!fielattach.length && filecount == 0) {
					$("#error_govorder").show();
					
					mandatory_error=true;
					error=true;
			    }
				}
			
			
			if(mandatory_error==true)
				showClientSideError();
			
			if(error==true)
				return false;
			else{
				document.getElementById("EffectiveDate").disabled = false;
				return true;	
			}
			
			
			
	    }
	 function validateVillage()
		{  
		 validateVillageDraft();
		 selectall();
		 hideAll();
			if (validateformModify()) {
				if (surverNoSelected()) {
					if (validateGovtOrderDetailsForVilModify()) {
						$("#OrderDate").removeAttr("disabled");
						$("#EffectiveDate").removeAttr("disabled");
						$("#GazPubDate").removeAttr("disabled");
						document.getElementById('frmModifyVillage').action = 'saveModifyDraftVillageOrderDetails.htm?<csrf:token uri="saveModifyDraftVillageOrderDetails.htm"/>';
						document.getElementById('buttonClicked').value = 'S';
						document.getElementById("BtnSA").disabled = true;
						document.getElementById("BtnSAP").disabled = true;
						document.getElementById('frmModifyVillage').submit();
					}

				}

			}
		}
	 
	 function validateVillagePublish()
		{  
		 validateVillageDraft();
		 selectall();
		 hideAll();
			if (validateformModify()) {
				if (surverNoSelected()) {
					if (validateGovtOrderDetailsForVilModify()) {
						$("#OrderDate").removeAttr("disabled");
						$("#EffectiveDate").removeAttr("disabled");
						$("#GazPubDate").removeAttr("disabled");
						document.getElementById('frmModifyVillage').action = 'saveModifyDraftVillageOrderDetails.htm?<csrf:token uri="saveModifyDraftVillageOrderDetails.htm"/>';
						document.getElementById('buttonClicked').value = 'P';
						document.getElementById("BtnSA").disabled = true;
						document.getElementById("BtnSAP").disabled = true;
						document.getElementById('frmModifyVillage').submit();
					}

				}

			}
		}
	 
	 function validateDatetoFuture(id,diverror)
	 {
		var entityDate=document.getElementById(id).value;
		 $(diverror).hide();
		 if(!compareDateforFutureDDMMYYY(entityDate)){ 
				$(diverror).show();
				document.getElementById(id).value="";
				
				
		 }
		 
	 }
	 
	 function validateEffectiveDatecompOrderDate(orderdateId,effectivedateID,diverror)
	 {
		 var orderDate=document.getElementById(orderdateId).value;
		 var effectiveDate=document.getElementById(effectivedateID).value;
		if(orderDate!="") 
			{
			$(diverror).hide();	
			if (compareDateYYMMDD(orderDate, effectiveDate, 'dd-mm-yyyy'))
		 {
					$(diverror).show();
					document.getElementById(effectivedateID).value=orderDate;
					
				}
			}
		 
		 
	 }
		
		
	 
	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideAll;
	  }
	 
	function getGisNodes() {
		if (document.getElementById('hiddenCoordinates').value != '')
			document.getElementById('txtlatitude').value = document
					.getElementById('hiddenCoordinates').value;

		if (document.getElementById('txtlatitude').value != '') {
			var gisList = document.getElementById('txtlatitude').value
					.split(':');
			//i=gisList.length;

			document.getElementById('txtlatitude').value = gisList[0]
					.split(',')[0];
			document.getElementById('txtLongitude').value = gisList[0]
					.split(',')[1];

			for ( var k = 1; k < gisList.length; k++) {
				addgisnodes1();
				document.getElementById('lati' + k).value = gisList[k]
						.split(',')[0];
				document.getElementById('longi' + k).value = gisList[k]
						.split(',')[1];
			}
		}
	}
	
	function deleteRow(count){
		if(count!=2)
			{
		var id = 'trId'+count;
		var row = document.getElementById(id);
	    row.parentNode.removeChild(row);
			}
		
	}
	
	function validateSscode(value){	
		if(isNaN(value)){
			alert("State Specific Code must be integer");
			return false;
		}
	}
	
	
	function toggledisplayedOnload(obj)
	{	
		<c:if test="${isExistGovt == 'Y'}">
			//document.getElementById('actionExistingGO').checked=true;
			document.getElementById('orderCode').value='${orderCode}';
			$("#divGazettePublicationUpload").hide(); 
		</c:if>
		<c:if test="${isExistGovt == 'N'}">
			//document.getElementById('actionNonExistingGO').checked=true;
			$("#divGazettePublicationUpload").show();
			document.getElementById('orderCode').value='';
		</c:if>
		var val='';
		if(obj=='V'){
			obj='chkcvillage';
			val='cvillage';
		}
		else if(obj=='U'){
			obj='chkculb';
			val='culb';
		}
		else{
			obj='chknothing';
		}
		document.getElementById(obj).checked=true;
		if (document.getElementById(obj).checked)
			{
					if (val=='cvillage')
					{
						document.getElementById('cvillage').style.visibility='visible';
						document.getElementById('csurvey').style.visibility='visible';
						document.getElementById('cvillage').style.display='block';
						document.getElementById('csurvey').style.display='block';
						document.getElementById('culb').style.display='none';
						document.getElementById('chknothing').checked=false;
						document.getElementById('chkculb').checked=false;
						getVillage('${subDistrictCode}','${draftVilCode}');
						loadIsPesaStatus();					
					}
				  else if (val=='culb')
					{
					    document.getElementById('culb').style.visibility='visible';
						document.getElementById('culb').style.display='block';
						document.getElementById('cvillage').style.display='none';
						document.getElementById('csurvey').style.display='none';
						document.getElementById('chkcvillage').checked=false;
						document.getElementById('chknothing').checked=false;
						getSubDistrictandULBListModify('${districtCode}');
						/* $("#ulbListNew").empty(); */
					}
				else
					{
					document.getElementById('cvillage').style.display='none';
					document.getElementById('csurvey').style.display='none';
					document.getElementById('culb').style.display='none';
					document.getElementById('chkcvillage').checked=false;
					document.getElementById('chkculb').checked=false;
					
					}			}
	
	
	}	
	function toggledisplayed(obj,val)
	{
		
		if (document.getElementById(obj).checked)
			{
					if (val=='cvillage')
					{
						
						document.getElementById('cvillage').style.visibility='visible';
						document.getElementById('csurvey').style.visibility='visible';
						document.getElementById('cvillage').style.display='block';
						document.getElementById('csurvey').style.display='block';
						document.getElementById('culb').style.display='none';
						document.getElementById('chknothing').checked=false;
						document.getElementById('chkculb').checked=false;
						document.getElementById('existVilOrUlbFlag').value='V';
						getVillage('${subDistrictCode}','${draftVilCode}');
					 	
						
						/* $("#surveyListMain").empty();
						$("#surveyListNew").empty();  */

						
					}
				  else if (val=='culb')
					{
					    document.getElementById('culb').style.visibility='visible';
						document.getElementById('culb').style.display='block';
						document.getElementById('cvillage').style.display='none';
						document.getElementById('csurvey').style.display='none';
						document.getElementById('chkcvillage').checked=false;
						document.getElementById('chknothing').checked=false;
						document.getElementById('existVilOrUlbFlag').value='U';
						getSubDistrictandULBListModify('${districtCode}');
						
						/* $("#ulbListNew").empty(); */
					}
				else
					{
					document.getElementById('cvillage').style.display='none';
					document.getElementById('csurvey').style.display='none';
					document.getElementById('culb').style.display='none';
					document.getElementById('chkcvillage').checked=false;
					document.getElementById('existVilOrUlbFlag').value='';
					document.getElementById('chkculb').checked=false;
					
					}			}
	
	
	}
	
	
	loadIsPesaStatus=function(){
		var isPesaFlag='${modifyVillageCmd.isPesaStatus}';
		if(isPesaFlag.trim()!=""){
			createDynamicIsPesaDiv(isPesaFlag);
		}
	};
	
checkIsPesaStatus=function(){
		
		var contributeVillageLits = $('#villageListNew option');
		if($(contributeVillageLits).length == 0){
			$("#divispesaFlag").empty();
			 $("#isPesaStatus").val("");
		}else{
			var _village_code_list="";
			$(contributeVillageLits).each(function(index){
				// alert( $(this).val());
				 _village_str= $(this).val();
				 _village_code=_village_str.substring(0,( _village_str.length-4));
				 _village_code_list=_village_code_list+_village_code+"@";
				
			 });
			_village_code_list=_village_code_list.substring(0,(_village_code_list.length-1));
			lgdDwrVillageService.getISPesaFlag(_village_code_list,{
				callback : function( result ) {
					if(result!=null){
						createDynamicIsPesaDiv(result);
					}
				},
				errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
				async : true
			});
			
		}
		
		};
		
		var enableIsPesaButton=false;
		
		createDynamicIsPesaDiv=function(ispesaFlag){
			
			enableIsPesaButton=false;
			if(ispesaFlag.trim()=="M"){
				enableIsPesaButton=true;
			}
			
			$("#divispesaFlag").empty();
			var divTemplate = $("#divispesaFlag");
			 
			

			
			 var divfrmtxt = $("<div/>");
			 //divfrmtxt.attr("id", "isPesaStructure" );
			 divfrmtxt.addClass('frmtxt');
			 
			 var divTitle=$("<div/>");
			 divTitle.addClass('frmhdtitle');
			 divTitle.html("<spring:message htmlEscape='true' code='Label.ispesa.flag'/> ");
			
			 var divContainer=$("<div/>");
			 
			 var UL=$("<UL/>")
			 UL.addClass('listing');
			 
			 var LIONE=$("<LI/>");
			 
			 
			 var labelYes = $("<label/>");
			 labelYes.html("<spring:message htmlEscape='true' code='Label.YES'/> ");
			 
			
			 var templateYesInput=$("<input/>");
			 templateYesInput.attr("id", "isPesaFlagYes" );
			 templateYesInput.attr("name", "isPesaFlag" );
			 templateYesInput.attr("type", "radio" );
			 templateYesInput.attr("value", "F" );
			 if(enableIsPesaButton){
				 $(templateYesInput).click(function() {
					 $("#isPesaStatus").val("F");
					});
			 }else
				 {
				 if(ispesaFlag.trim()=="F"){
					 $(templateYesInput).prop('checked', true);
					 $("#isPesaStatus").val("F");
				 }
				 $(templateYesInput).prop('disabled', true);
				 }
			 
			 
			 LIONE.append(labelYes);
			 LIONE.append(templateYesInput);
			 
			 var LITWO=$("<LI/>");
			 
			 
			 var labelNo = $("<label/>");
			 labelNo.html("<spring:message htmlEscape='true' code='Label.NO'/> ");
			 
			
			 var templateNoInput=$("<input/>");
			 templateNoInput.attr("id", "isPesaFlagNo" );
			 templateNoInput.attr("name", "isPesaFlag" );
			 templateNoInput.attr("type", "radio" );
			 templateNoInput.attr("value", "N" );
			 if(enableIsPesaButton){
				 $(templateNoInput).click(function() {
					 $("#isPesaStatus").val("N");
					});
			 }else
				 {
				 if(ispesaFlag.trim()=="N"){
					 $(templateNoInput).prop('checked', true);
					 $("#isPesaStatus").val("N");
				 }
				 $(templateNoInput).prop('disabled', true);
				 }
			 
			 var templateUnselectInput=$("<input/>");
			 templateUnselectInput.attr("id", "unselect" );
			 templateUnselectInput.attr("name", "isPesaFlag" );
			 templateUnselectInput.attr("type", "radio" );
			 templateUnselectInput.attr("value", "unselect" );
			 templateUnselectInput.attr("style", "Display:none" );
			 if(ispesaFlag.trim()=="M"){
			 $(templateUnselectInput).prop('checked', true);
			 $("#isPesaStatus").val("");
			 }
			 
			 LITWO.append(labelNo);
			 LITWO.append(templateNoInput);
			 LITWO.append(templateUnselectInput);
			 
			 var LITHREE=$("<LI/>");
			 
			 templateSpanError=$("<span/>")
			 templateSpanError.attr("id", "errorIsPesaFlag");
			 templateSpanError.addClass('errormessage');
			 
			 LITHREE.append(templateSpanError);
			 
			 UL.append(LIONE);
			 UL.append(LITWO);
			 UL.append(LITHREE);
			 
			 divContainer.append(UL);
			 
			 divfrmtxt.append(divTitle);
			 divfrmtxt.append(divContainer);
			 
			 divTemplate.append(divfrmtxt);
			 
		};

		
		validateIsPesaFlag=function(){
			$( '#errorIsPesaFlag').text("");
			if ($("#chkcvillage").is(':checked')) {
				var contributeVillageLits = $('#villageListNew option');
					if($(contributeVillageLits).length == 0){
						alert("Please choose contributing Villages ");
						return false;
					}else if(enableIsPesaButton==true){
							   var isPesaFlag=$("#isPesaStatus").val();
		         	  				if(isPesaFlag.trim()==""){
		         		  					$( '#errorIsPesaFlag').text("<spring:message code='label.choose.isPesa.flag' htmlEscape='true'/>");
		         		 					return false;
		         	   				  }
			
					}
			
		   }
			return true;
			
		};
	
</script>

</head>
 <body>

	
	<section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				
		<div class="box-body">
	   <div id="frmcontent">
	
		<div class="frmhd">
			<h4 class="subtitle">
				<label><spring:message code="Label.MANAGEVILLAGEDRAFT" htmlEscape="true"></spring:message></label>
			</h4>
			<ul id="showhelp" class="listing">				

			</ul>
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="viewVillageDraftDetail.htm"
				method="POST" commandName="modifyVillageCmd" id="frmModifyVillage" name="modifyVillageCmd"
				enctype="multipart/form-data">
				<input type="hidden" name="flag1" id="flag1" value="${flag1}" />
				<input type="hidden" name="flag2" id="flag2" value="${flag2}" />
				<input type="hidden" name="survernoset" id="survernoset" value="0" />
				<input type="hidden" name="contributedSurvey" id="contributedSurvey" value="" />
				<input type="hidden" name="subDistrictCode" id="subDistrictCode" value="${subDistrictCode}" />
				<input type="hidden" name="districtCode" id="districtCode" value="${districtCode}" />
				<input type="hidden" name="draftVilCode" id="draftVilCode" value="${draftVilCode}"/>
				<input type="hidden" name="existVilOrUlbFlag" id="existVilOrUlbFlag" value="${existVilOrUlbFlag}"/>
				<input type="hidden" name="isExistGovt" id="isExistGovt" value="${isExistGovt}"/>
				<input type="hidden" name="villagenameInEngOld" id="villagenameInEngOld"/>	
				<input type="hidden" name="selectedUlbCode" id="selectedUlbCode" value="${selectedUlbCode}"/>
				<form:hidden path="isPesaStatus" value="N"/>
				
				<input type="hidden" name="count" value="" id="count"></input>
				<c:if test="${isExistGovt == 'N'}">
				<input type="hidden" name="checkNewOrExist" id="checkNewOrExist" />
				</c:if>
			
			
			
			    <div class="box-header subheading">
					<h4>
						<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
					</h4>
				</div>
			   <div class="box-body">
			    <div style="width: 100%">
			    
			    <input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}"></input>
					<input type="hidden" id="mapfilecount" name="mapfilecount" value="${mapfilecount}"></input>
					<input type="hidden" name="warningFlag" value="${pageWarningEntiesFlag}"/>   
					<input type="hidden" name="type" value="${type}"/>
			    
			    
				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
				
				
				<select class="form-control" htmlEscape="true" name="districtNameEnglish" id="ddDistrict" onchange="getSubDistrictandULBList(this.value);" onclick="clearalllist();"
								onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');" onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDistrict')">
								
								  <c:choose>
								  	<c:when test="${flag1 eq 0}">
									<option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></option>
										<c:forEach items="${districtList}" var="districtList">
										   <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'A'.charAt(0))}">
											  <option value="${districtList.districtCode}">${districtList.districtNameEnglish}</option>
										   </c:if>
										   
										   <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">
												<option value="${districtList.districtCode}" disabled="true">${districtList.districtNameEnglish}</option>
											</c:if>
										 </c:forEach>
									 </c:when>
									<c:otherwise>
										<c:forEach items="${districtList}" var="districtList">
											<c:if test="${districtId eq districtList.districtCode}">
												<option value="${districtList.districtCode}" <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">disabled="disabled"</c:if>>${districtList.districtNameEnglish}</option>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select>
							<span class="errormessage" id="errddDistrict"></span>
								<div id="ddDistrict_msg" class="mandatory" style="display: none">
									<spring:message code="Error.SOURCEDISTRICT" htmlEscape="true" />
								</div>
				              </div>
				              </div>
				              
				              <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						  <div class="col-sm-6">
							<form:select htmlEscape="true" path="subdistrictNameEnglish" class="form-control" id="ddSubdistrict" onchange="getVillageList(this.value);" onclick="clearalllist();"
								onfocus="validateOnFocus('ddSubdistrict');helpMessage(this,'ddSubdistrict_msg');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSubdistrict')">
								<c:if test="${not empty subDistrictList }">
									<form:option value="0">
										<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
									</form:option>
								</c:if>
							</form:select>
								<span class="errormessage" id="errddSubdistrict"></span>
								<div class="errormsg">
									<form:errors htmlEscape="true" path="subdistrictNameEnglish"></form:errors>
								</div>
								<div id="ddSubdistrict_msg" class="mandatory" style="display: none">
									<spring:message code="error.PSSDT" htmlEscape="true" />
								</div>
						</div>
					</div> 
			    </div>
			    
			    <div class="box-header subheading">
						<h4>
							<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
						</h4>
					</div>
			
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
							   <form:input htmlEscape="true" path="newVillageNameEnglish" id="OfficialAddress" class="form-control" onfocus="clear_message();show_msg('OfficialAddress');validateOnFocus('OfficialAddress');helpMessage(this,'OfficialAddress_msg');"
								onblur="officialAddressVil();vlidateOnblur('OfficialAddress','1','15','c');hideHelp();" onkeypress="return validateAlphanumericUpdateKeysWard(event);" maxlength="150"  />
								<span class="errormessage" id="errOfficialAddress"></span>
									<div id="errProSelectDist" style="color: red;"></div> <span
									class="errormsg" id="OfficialAddress_error"></span> <label></label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameEnglish"></form:errors>
									</div>
									<div id="OfficialAddress_msg" style="display: none">
										Please Enter Village Name
										<spring:message code="error.PSV" htmlEscape="true"/>
									</div>
							</div>
					</div>
			     	
				
				<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input htmlEscape="true" id="villageNameLocal" path="newVillageNameLocal" class="form-control" maxlength="50"
								onfocus="show_msg('villageNameLocal');helpMessage(this,'villageNameLocal_msg');" onblur="hideHelp();validateSpecialCharactersforVillageValues(this.value,1);" />
								<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameLocal"></form:errors>
									</div>
									<div id="villageNameLocal_msg" style="display: none">
										Please Enter Village Name Local
										<spring:message code="error.PSV" htmlEscape="true"/>
									</div>
						</div>
					</div>
				
				
				   <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input path="newVillageAliasLocal" class="form-control" maxlength="50" onblur="validateSpecialCharactersforVillageValues(this.value,2);" />
							<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasLocal"></form:errors>
									</div>
						</div>
					</div>
				
				<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input path="newVillageAliasLocal" class="form-control" maxlength="50" onblur="validateSpecialCharactersforVillageValues(this.value,2);" />
							<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasLocal"></form:errors>
									</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:select path="villageType" cssClass="form-control">
								<option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></option>
								<option value="R">Forest Village</option>
								<option value="U">Un-inhabitant</option>
								<option value="I">Inhabitant</option>
							</form:select>
						</div>
					</div>
				
				  <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label>
					   	  <div class="col-sm-6">
							<form:input path="sscode" class="form-control" id="sscode" name="sscode" maxlength="5" onblur="chekcalphanumeric(this.value,3);" />
						  </div>
					</div>
				
				
				
				<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SURVEYNUMBER" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<input type="hidden" name="psize" id="psize" />
							<table id="ptablePerson" align="left">
								<tr></tr>
								<tr>
									<td> 1 &nbsp;</td>
									<td>
										<form:input htmlEscape="true" type="text" class="form-control" path="surveyNumber" id="surveyNumber"
										name="surveyNumber" onblur="chekcalphanumeric(this.value,4);" maxlength="10" />
										<td><input type="button" name="Submit32" class="btn btn-info" value="Add Survey Number" onclick="incrementCount()" /></td>
									</td>
								</tr>

							</table>
							<div id=errBlockCode style="color: red;"></div>
							<table id="surveytable" style="visibility: hidden;">
								<tr>
									<td><input type="button" class="btn btn-warning" value="Remove" onclick="removeRow();" /></td>
								</tr>
							</table>

						</div>
					</div>
					
					<div class="box-header subheading">
					<h4>
						<spring:message code="Label.HOWVILLAGEFORMED" htmlEscape="true"><span class="mandatory">*</span></spring:message>
					</h4>
				</div>
				<div class="form-group">
						<label class="col-sm-2 control-label" for="sel1"></label>
						<div class="col-sm-6">
							<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
								
								<div class="col">
									<label>
									<input type="radio"  htmlEscape="true" value="true" id='chkcvillage' name="createFromExistingVillages" onclick='toggledisplayed("chkcvillage","cvillage")' /> 
									<spring:message code="Label.EXISTINGVILLAGE" htmlEscape="true"></spring:message></label>
								</div>
								<div class="col">
									<label>
									<input type="radio" htmlEscape="true" value="true" id='chknothing' name="createFromNewLand" onclick='toggledisplayed("chknothing","chknothing")'/>
									<spring:message code="Label.NEWLAND" htmlEscape="true"></spring:message></label>
								</div>
								
								<div class="col" style = "display:none">
									<label>
									<input type="radio" htmlEscape="true" value="true" id='chkculb' name="createFromCoverageOfUrbanLocalBody" onclick='toggledisplayed("chkculb","culb")' />
									<spring:message code="Label.ULB" htmlEscape="true"></spring:message></label>
								</div>
							</div>
						</div>
					</div>
					
					<div id='cvillage' class="" style="visibility: hidden; display: none;">
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
					</h4>
				</div>

					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label for="ddSourceBlock">
								<strong><spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message> </strong>
							</label> 
							<select class="form-control" name="select9" id="villageListMain" path="villageList" multiple="multiple"></select> 
						</div>
						<div class="ms_buttons col-sm-2">
							<br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillageFull" name="Submit4" onclick="addItemVillage('villageListNew','villageListMain','FULL',true);checkIsPesaStatus();" >Select Full Villages <i class="fa fa-angle-double-right" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneVillage" name="Submit4" onclick="removevillageandsurveynoForDraft('villageListNew','villageListMain',true);checkIsPesaStatus();"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveAllVillages" name="Submit4" onclick="removeAllSelectedVillagesForDraft('villageListNew','villageListMain',true)checkIsPesaStatus();"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillagePart" name="Submit4" onclick="addItemVillageForDraft('villageListNew','villageListMain','PART',true);checkIsPesaStatus();">Select Part Villages <i class="fa fa-angle-right" aria-hidden="true"></i>
						</div>
						<div class="ms_selection col-sm-5">
							<label for="ddSourceBlock"><spring:message code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message></label>
							<form:select name="_blockNameEnglish" id="villageListNew" multiple="multiple" path="contributedVillages" class="form-control"/>
							<span class="errormessage" id="errvillageListNew"></span>
							<div class="errormsg">
								<form:errors path="contributedVillages"></form:errors>
							</div>
							<button type="button" class="btn btn-primary" id="tbnSurveyNumbers" name="Submit7" onclick="getSurveyNobyVillage();"><spring:message code="Label.GETPARTVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></button>
							<div class="errormsg"></div>
							<br>
						</div>
					</div>
				
					
				<div id='csurvey' style="visibility: hidden;">				
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label for="ddSourceBlock"><spring:message code="Label.SELECTEDVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></label>
							<select class="form-control" name="select5" id="surveyListMain" multiple="multiple" path="surveyList"></select>
						</div>
						
						<div class="ms_buttons col-sm-2"><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit42" onclick="fillselectedSurveryNo('surveyListNew','surveyListMain',document.getElementById('surveyListMain').value,false)"><spring:message code="Label.SELECTSURVEYNUMBER" htmlEscape="true"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true" ></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneSurvey" name="Submit4" onclick="removeItem('surveyListNew','surveyListMain',false)"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit42" id="btnremoveAllSurveys" onclick="removeAll('surveyListNew','surveyListMain',false)"  ><i class="fa fa-angle-double-left" aria-hidden="true"></i>
						</div>
						
						<div class="ms_selection col-sm-5">
							 <div class="form-group">
								<label for="ddDestBlock"><spring:message code="Label.CONTRIBUTINGSURVEYNUMBER" htmlEscape="true"></spring:message></label> 
								<select  class="form-control" name="select5" id="surveyListNew" multiple="multiple" path="contributedSurvey"></select>
								<input type="hidden" name="_blockNameEnglish" value="1">
							</div>				
						</div>
				</div>
				</div>
					<div class="box-header subheading">
						<h4>
							<spring:message code="Label.RENAME.CONTRIBUTING.VILLAGE" htmlEscape="true"></spring:message>
						</h4>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="sel1"></label>
						<div class="col-sm-6">
							<div class="radio">
								<label><input type="radio" name="rename" id="rename" value="1" onclick="renameListMethod(this.value);"><spring:message code="Label.YES" htmlEscape="true"></spring:message></label>
							</div>

							<div class="radio">
								<label><input type="radio" name="rename" id="rename" value="0" onclick="renameListMethod(this.value);" checked="checked"><spring:message code="Label.NO" htmlEscape="true"></spring:message></label>
							</div>
						</div>
					</div>
					<table id="renameList" class="table table-bordered table-hover" style="display: none">
						<tr>
							<td width="5%" style="text-align: center;"><strong><spring:message code="Label.SNO" htmlEscape="true"></spring:message></strong></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.CONTRIBUTINGVILLAGE" htmlEscape="true"></spring:message></strong></td>
							<td width="20%" style="text-align: center;"><input type="button" name="CheckAll" value="Check All" onclick="checkAll(document.addVillageNew.reNameFlag)"> <input type="button" name="UnCheckAll" value="Uncheck All" onclick="uncheckAll(document.addVillageNew.reNameFlag)"></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.CURRENTVILLAGENAME" htmlEscape="true"></spring:message></strong></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.RENAMEVILLAGE" htmlEscape="true"></spring:message></strong></td>
						</tr>
					</table>
					<div style="display: none;">
						<input class="btn btn-info" id="btnPreviewGIS" type="button" onclick="showGISPreview();" value="<spring:message code='Button.PREVIEWGIS'   htmlEscape='true'/>" />
					</div>
						
					<div id="divispesaFlag"></div>
			</div>
		</div>	
				
				<div id='culb' style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: hidden; display: none">
			<div class="box-header subheading">
				<h4><spring:message code="Label.SELECTEDDISTRICTULB" htmlEscape="true"></spring:message> </h4>
			</div>
				
		<div class="ms_container row" style="margin-left: 10px;">
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceBlock"><strong><spring:message code="Label.ULBCOVEREDLANDREGION" htmlEscape="true"></spring:message></strong></label> 
				<select class="form-control" name="select6" path="coveredLandRegionByULB" id="ulbListMain" multiple="multiple"></select> 
			</div>
			
			<div class="ms_buttons col-sm-2"><br>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit4222"   onclick="addItem('ulbListNew','ulbListMain','FULL',true)"> <spring:message code="Label.FULLULB" htmlEscape="true"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneULB" name="Submit4" onclick="removeItem('ulbListNew','ulbListMain',true)" ><i class="fa fa-angle-left" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit22223"   onclick="removeAll('ulbListNew','ulbListMain',true)"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit22222"   onclick="addItem('ulbListNew','ulbListMain','PART',true)"><spring:message code="Label.PARTULB" htmlEscape="true"></spring:message><i class="fa fa-angle-right" aria-hidden="true"></i>
			</div>

			<div class="ms_selection col-sm-5"><br>
						<form:select name="select6" path="selectedCoveredLandRegionByULB" id="ulbListNew" multiple="multiple" class="form-control"/>
						<div class="errormsg"><form:errors path="selectedCoveredLandRegionByULB"></form:errors></div>
			</div>
		</div>
	</div>
	
	<div class="box-header subheading">
					<h4>
						<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
					</h4>
				</div>
				
				
				<%@ include file="../govtbody/ExistingGovernmentOrderVillagecp.jsp"%>  
	
				
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
						<form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="form-control" maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" />
							<div id="OrderNo_error" class="error"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
							<div id="OrderNo_msg" class="mandatory"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderNo_error1"><form:errors path="orderNo" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
					</div>
			</div>   			
										
									
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
					  <div class="input-group date datepicker" id="bOrderDate">
						  <form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control" onchange="setEffectiveDate(this.value);"
							onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" /> 
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
							<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
							<div id="OrderDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.ORDERDATE" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderDate_error2" style="display: none"></div>
					</div>
			</div> 
			
			<div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
				   <div class="input-group date datepicker" id="bEffectiveDate">
					<form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control" onkeypress="validateNumeric();"
						onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');" onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
						onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				  </div>
					<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
					<div id="EffectiveDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
					<div class="errormsg" id="EffectiveDateData_error"><spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message></div>
					<div class="errormsg" id="EffectiveDateBlank_error"><spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message></div>
					<div class="errormsg" id="EffectiveFutureDate_error"><spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message></div>
				</div>
		</div>	
		
		<div class="form-group">
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
				<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label>
					<div class="col-sm-6">
					   <div class="input-group date datepicker" id="bGazPubDate">
						<form:input id="GazPubDate" path="gazPubDate" type="text" class="form-control" readonly="true" />
						  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
						   <span class="errormsg" id="GazPubDate_error"></span>
						  <form:errors path="gazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors>
						  <span id="GazPubDate_msg" class="mandatory" style="display: none">Please Enter Gazette Publication Date Like 12-04-2012</span>
						<div id="GazPubDate_error" class="error"></div>
						<div id="GazPubDate_msg" style="display: none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error2" style="display: none"></div>
					</div>
				</c:if>
		</div>	
		
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
							<div id="divGazettePublicationUpload">
							<%@ include file="../common/attachmentgovtcp.jspf"%>
							</div>
					</c:if>
					
				<div class="form-group">
					<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							<form:select path="templateList" id="templateList" style="width:280px" class="form-control" onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
								onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');" onkeyup="hideMessageOnKeyPress('templateList')">
								<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
								<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
							</form:select> <span class="errormsg" id="templateList_error"></span>
								<span><form:errors cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span>
								<span style="display: none;" class="mandatory" id="templateList_msg">Please Select Government order Template</span>
								<div id="templateList_error" class="error"></div>
								<div id="templateList_msg" style="display: none"><spring:message code="error.blank.template" htmlEscape="true" /></div>
								<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true" /></div>
								<div class="errormsg" id="templateList_error2" style="display: none"></div>
						</div>
					</c:if>
				</div>
				
				
				<form:hidden htmlEscape="true" path="buttonClicked" value="" />
		<div class="box-footer">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="pull-right">
				<div id="drafthide">
				 <button type="button"  class="btn btn-success" id="BtnSA" onclick="return selectValidateAndSubmit('S');" name="Submit"  ><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
				 </div> 
					 <button type="button" class="btn btn-primary" id="BtnSAP" onclick="return selectValidateAndSubmit('P');"name="Submit"><spring:message code="Label.PUBLISH" htmlEscape="true" text="Publish" ></spring:message></button> 
					 <button type="button" class="btn btn-danger " name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
				</div>
			</div>
		</div>
					
				 </div> 
				<%-- <div class="box-header subheading">
					<h4>
						<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
					</h4>
				</div>
				
				<div class="box-body">
				<div style="width: 100%">
				<form:hidden htmlEscape="true" path="buttonClicked" value="" />	
					
					<input type="hidden" id="govtfilecount" name="govtfilecount" value="${govtfilecount}"></input>
					<input type="hidden" id="mapfilecount" name="mapfilecount" value="${mapfilecount}"></input>
					<input type="hidden" name="warningFlag" value="${pageWarningEntiesFlag}"/>   
					<input type="hidden" name="type" value="${type}"/>
				
				<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
				
				
				<select class="form-control" htmlEscape="true" name="districtNameEnglish" id="ddDistrict" onchange="getSubDistrictandULBList(this.value);" onclick="clearalllist();"
								onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');" onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDistrict')">
								
								  <c:choose>
								  	<c:when test="${flag1 eq 0}">
									<option value="0"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message></option>
										<c:forEach items="${districtList}" var="districtList">
										   <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'A'.charAt(0))}">
											  <option value="${districtList.districtCode}">${districtList.districtNameEnglish}</option>
										   </c:if>
										   
										   <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">
												<option value="${districtList.districtCode}" disabled="true">${districtList.districtNameEnglish}</option>
											</c:if>
										 </c:forEach>
									 </c:when>
									<c:otherwise>
										<c:forEach items="${districtList}" var="districtList">
											<c:if test="${districtId eq districtList.districtCode}">
												<option value="${districtList.districtCode}" <c:if test="${fn:containsIgnoreCase(districtList.operation_state, 'F'.charAt(0))}">disabled="disabled"</c:if>>${districtList.districtNameEnglish}</option>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select>
							<span class="errormessage" id="errddDistrict"></span>
								<div id="ddDistrict_msg" class="mandatory" style="display: none">
									<spring:message code="Error.SOURCEDISTRICT" htmlEscape="true" />
								</div>
				              </div>
				              </div>
				             </br>
				            <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
						  <div class="col-sm-6">
							<form:select htmlEscape="true" path="subdistrictNameEnglish" class="form-control" id="ddSubdistrict" onchange="getVillageList(this.value);" onclick="clearalllist();"
								onfocus="validateOnFocus('ddSubdistrict');helpMessage(this,'ddSubdistrict_msg');" onblur="vlidateOnblur('ddSubdistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSubdistrict')">
								<c:if test="${not empty subDistrictList }">
									<form:option value="0">
										<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
									</form:option>
								</c:if>
							</form:select>
								<span class="errormessage" id="errddSubdistrict"></span>
								<div class="errormsg">
									<form:errors htmlEscape="true" path="subdistrictNameEnglish"></form:errors>
								</div>
								<div id="ddSubdistrict_msg" class="mandatory" style="display: none">
									<spring:message code="error.PSSDT" htmlEscape="true" />
								</div>
						</div>
					</div>  
				              
				</div>
				
				
				<div class="box-header subheading">
						<h4>
							<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
						</h4>
					</div>
				
				<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWVILLAGEENGLISH" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
							<div class="col-sm-6">
							   <form:input htmlEscape="true" path="newVillageNameEnglish" id="OfficialAddress" class="form-control" onfocus="clear_message();show_msg('OfficialAddress');validateOnFocus('OfficialAddress');helpMessage(this,'OfficialAddress_msg');"
								onblur="officialAddressVil();vlidateOnblur('OfficialAddress','1','15','c');hideHelp();" onkeypress="return validateAlphanumericUpdateKeysWard(event);" maxlength="150"  />
								<span class="errormessage" id="errOfficialAddress"></span>
									<div id="errProSelectDist" style="color: red;"></div> <span
									class="errormsg" id="OfficialAddress_error"></span> <label></label>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameEnglish"></form:errors>
									</div>
									<div id="OfficialAddress_msg" style="display: none">
										Please Enter Village Name
										<spring:message code="error.PSV" htmlEscape="true"/>
									</div>
							</div>
					</div>
				
				<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.NAMEOFNEWVILLAGELOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input htmlEscape="true" id="villageNameLocal" path="newVillageNameLocal" class="form-control" maxlength="50"
								onfocus="show_msg('villageNameLocal');helpMessage(this,'villageNameLocal_msg');" onblur="hideHelp();validateSpecialCharactersforVillageValues(this.value,1);" />
								<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageNameLocal"></form:errors>
									</div>
									<div id="villageNameLocal_msg" style="display: none">
										Please Enter Village Name Local
										<spring:message code="error.PSV" htmlEscape="true"/>
									</div>
						</div>
					</div>
				
				   <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.ALIASENGLISH" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input htmlEscape="true" path="newVillageAliasEnglish" class="form-control" maxlength="50" />
								<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasEnglish"></form:errors>
									</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:input path="newVillageAliasLocal" class="form-control" maxlength="50" onblur="validateSpecialCharactersforVillageValues(this.value,2);" />
							<div class="errormsg">
										<form:errors htmlEscape="true" path="newVillageAliasLocal"></form:errors>
									</div>
						</div>
					</div>
					
				
				<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<form:select path="villageType" cssClass="form-control">
								<option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></option>
								<option value="R">Forest Village</option>
								<option value="U">Un-inhabitant</option>
								<option value="I">Inhabitant</option>
							</form:select>
						</div>
					</div>
				
				    <div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message></label>
					   	  <div class="col-sm-6">
							<form:input path="sscode" class="form-control" id="sscode" name="sscode" maxlength="5" onblur="chekcalphanumeric(this.value,3);" />
						  </div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message code="Label.SURVEYNUMBER" htmlEscape="true"></spring:message></label>
						  <div class="col-sm-6">
							<input type="hidden" name="psize" id="psize" />
							<table id="ptablePerson" align="left">
								<tr></tr>
								<tr>
									<td> 1 &nbsp;</td>
									<td>
										<form:input htmlEscape="true" type="text" class="form-control" path="surveyNumber" id="surveyNumber"
										name="surveyNumber" onblur="chekcalphanumeric(this.value,4);" maxlength="10" />
										<td><input type="button" name="Submit32" class="btn btn-info" value="Add Survey Number" onclick="incrementCount()" /></td>
									</td>
								</tr>

							</table>
							<div id=errBlockCode style="color: red;"></div>
							<table id="surveytable">
								<tr>
									<td><input type="button" class="btn btn-warning" value="Remove" onclick="removeRow();" /></td>
								</tr>
							</table>

						</div>
					</div>
				
				
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.HOWVILLAGEFORMED" htmlEscape="true"><span class="mandatory">*</span></spring:message>
					</h4>
				</div>
				
				
				<div class="form-group">
						<label class="col-sm-2 control-label" for="sel1"></label>
						<div class="col-sm-6">
							<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
								
								<div class="col">
									<label>
									<input type="radio"  htmlEscape="true" value="true" id='chkcvillage' name="createFromExistingVillages" onclick='toggledisplayed("chkcvillage","cvillage")' /> 
									<spring:message code="Label.EXISTINGVILLAGE" htmlEscape="true"></spring:message></label>
								</div>
								<div class="col">
									<label>
									<input type="radio" htmlEscape="true" value="true" id='chknothing' name="createFromNewLand" onclick='toggledisplayed("chknothing","chknothing")'/>
									<spring:message code="Label.NEWLAND" htmlEscape="true"></spring:message></label>
								</div>
								
								<div class="col" style = "display:none">
									<label>
									<input type="radio" htmlEscape="true" value="true" id='chkculb' name="createFromCoverageOfUrbanLocalBody" onclick='toggledisplayed("chkculb","culb")' />
									<spring:message code="Label.ULB" htmlEscape="true"></spring:message></label>
								</div>
							</div>
						</div>
					</div>
				
				
				
				
				<div id='cvillage' class="" style="visibility: hidden; display: none;">
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
					</h4>
				</div>

					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label for="ddSourceBlock">
								<strong><spring:message code="Label.VILLAGES" htmlEscape="true"></spring:message> </strong>
							</label> 
							<select class="form-control" name="select9" id="villageListMain" path="villageList" multiple="multiple"></select> 
						</div>
						<div class="ms_buttons col-sm-2">
							<br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillageFull" name="Submit4" onclick="addItemVillage('villageListNew','villageListMain','FULL',true);checkIsPesaStatus();" >Select Full Villages <i class="fa fa-angle-double-right" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneVillage" name="Submit4" onclick="removevillageandsurveynoForDraft('villageListNew','villageListMain',true);checkIsPesaStatus();"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveAllVillages" name="Submit4" onclick="removeAllSelectedVillagesForDraft('villageListNew','villageListMain',true)checkIsPesaStatus();"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnaddVillagePart" name="Submit4" onclick="addItemVillageForDraft('villageListNew','villageListMain','PART',true);checkIsPesaStatus();">Select Part Villages <i class="fa fa-angle-right" aria-hidden="true"></i>
						</div>
						<div class="ms_selection col-sm-5">
							<label for="ddSourceBlock"><spring:message code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message></label>
							<form:select name="_blockNameEnglish" id="villageListNew" multiple="multiple" path="contributedVillages" class="form-control"/>
							<span class="errormessage" id="errvillageListNew"></span>
							<div class="errormsg">
								<form:errors path="contributedVillages"></form:errors>
							</div>
							<button type="button" class="btn btn-primary" id="tbnSurveyNumbers" name="Submit7" onclick="getSurveyNobyVillage();"><spring:message code="Label.GETPARTVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></button>
							<div class="errormsg"></div>
							<br>
						</div>
					</div>
				
					
				<div id='csurvey' style="visibility: hidden;">				
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label for="ddSourceBlock"><spring:message code="Label.SELECTEDVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></label>
							<select class="form-control" name="select5" id="surveyListMain" multiple="multiple" path="surveyList"></select>
						</div>
						
						<div class="ms_buttons col-sm-2"><br>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit42" onclick="fillselectedSurveryNo('surveyListNew','surveyListMain',document.getElementById('surveyListMain').value,false)"><spring:message code="Label.SELECTSURVEYNUMBER" htmlEscape="true"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true" ></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneSurvey" name="Submit4" onclick="removeItem('surveyListNew','surveyListMain',false)"><i class="fa fa-angle-left" aria-hidden="true"></i>
							<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit42" id="btnremoveAllSurveys" onclick="removeAll('surveyListNew','surveyListMain',false)"  ><i class="fa fa-angle-double-left" aria-hidden="true"></i>
						</div>
						
						<div class="ms_selection col-sm-5">
							 <div class="form-group">
								<label for="ddDestBlock"><spring:message code="Label.CONTRIBUTINGSURVEYNUMBER" htmlEscape="true"></spring:message></label> 
								<select  class="form-control" name="select5" id="surveyListNew" multiple="multiple" path="contributedSurvey"></select>
								<input type="hidden" name="_blockNameEnglish" value="1">
							</div>				
						</div>
				</div>
				<div class="box-header subheading">
						<h4>
							<spring:message code="Label.RENAME.CONTRIBUTING.VILLAGE" htmlEscape="true"></spring:message>
						</h4>
					</div>


					<div class="form-group">
						<label class="col-sm-2 control-label" for="sel1"></label>
						<div class="col-sm-6">
							<div class="radio">
								<label><input type="radio" name="rename" id="rename" value="1" onclick="renameListMethod(this.value);"><spring:message code="Label.YES" htmlEscape="true"></spring:message></label>
							</div>

							<div class="radio">
								<label><input type="radio" name="rename" id="rename" value="0" onclick="renameListMethod(this.value);" checked="checked"><spring:message code="Label.NO" htmlEscape="true"></spring:message></label>
							</div>
						</div>
					</div>
					<table id="renameList" class="table table-bordered table-hover" style="display: none">
						<tr>
							<td width="5%" style="text-align: center;"><strong><spring:message code="Label.SNO" htmlEscape="true"></spring:message></strong></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.CONTRIBUTINGVILLAGE" htmlEscape="true"></spring:message></strong></td>
							<td width="20%" style="text-align: center;"><input type="button" name="CheckAll" value="Check All" onclick="checkAll(document.addVillageNew.reNameFlag)"> <input type="button" name="UnCheckAll" value="Uncheck All" onclick="uncheckAll(document.addVillageNew.reNameFlag)"></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.CURRENTVILLAGENAME" htmlEscape="true"></spring:message></strong></td>
							<td width="25%" style="text-align: center;"><strong><spring:message code="Label.RENAMEVILLAGE" htmlEscape="true"></spring:message></strong></td>
						</tr>
					</table>
					<div style="display: none;">
						<input class="btn btn-info" id="btnPreviewGIS" type="button" onclick="showGISPreview();" value="<spring:message code='Button.PREVIEWGIS'   htmlEscape='true'/>" />
					</div>
						
					<div id="divispesaFlag"></div>
			</div>
		</div>	
				
				<div id='culb' style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: hidden; display: none">
			<div class="box-header subheading">
				<h4><spring:message code="Label.SELECTEDDISTRICTULB" htmlEscape="true"></spring:message> </h4>
			</div>
				
		<div class="ms_container row" style="margin-left: 10px;">
			<div class="ms_selectable col-sm-5 form-group">
				<label for="ddSourceBlock"><strong><spring:message code="Label.ULBCOVEREDLANDREGION" htmlEscape="true"></spring:message></strong></label> 
				<select class="form-control" name="select6" path="coveredLandRegionByULB" id="ulbListMain" multiple="multiple"></select> 
			</div>
			
			<div class="ms_buttons col-sm-2"><br>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit4222"   onclick="addItem('ulbListNew','ulbListMain','FULL',true)"> <spring:message code="Label.FULLULB" htmlEscape="true"></spring:message><i class="fa fa-angle-double-right" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" id="btnremoveOneULB" name="Submit4" onclick="removeItem('ulbListNew','ulbListMain',true)" ><i class="fa fa-angle-left" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit22223"   onclick="removeAll('ulbListNew','ulbListMain',true)"><i class="fa fa-angle-double-left" aria-hidden="true"></i>
				<button type="button" class="btn btn-primary btn-xs btn-block" aria-hidden="true" name="Submit22222"   onclick="addItem('ulbListNew','ulbListMain','PART',true)"><spring:message code="Label.PARTULB" htmlEscape="true"></spring:message><i class="fa fa-angle-right" aria-hidden="true"></i>
			</div>

			<div class="ms_selection col-sm-5"><br>
						<form:select name="select6" path="selectedCoveredLandRegionByULB" id="ulbListNew" multiple="multiple" class="form-control"/>
						<div class="errormsg"><form:errors path="selectedCoveredLandRegionByULB"></form:errors></div>
			</div>
		</div>
	</div>
				
				<div class="box-header subheading">
					<h4>
						<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
					</h4>
				</div>
				
				
				<%@ include file="../govtbody/ExistingGovernmentOrderVillagecp.jsp"%>  
	
				
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.ORDERNO"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
						<form:input path="orderNo" htmlEscape="true" id="OrderNo" type="text" class="form-control" maxLength="60" onfocus="helpMessage(this,'OrderNo_error');" onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();" onkeypress="return validateaGovtOrderNO(event);" />
							<div id="OrderNo_error" class="error"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
							<div id="OrderNo_msg" class="mandatory"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderNo_error1"><form:errors path="orderNo" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderNo_error2" style="display: none"></div>
					</div>
			</div>   			
										
									
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
					<div class="col-sm-6">
					  <div class="input-group date datepicker" id="bOrderDate">
						  <form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="form-control" onchange="setEffectiveDate(this.value);"
							onkeypress="validateNumeric();" onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" /> 
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
							<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
							<div id="OrderDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.ORDERDATE" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true" /></div>
							<div class="errormsg" id="OrderDate_error2" style="display: none"></div>
					</div>
			</div> 
			
			<div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
				<div class="col-sm-6">
				   <div class="input-group date datepicker" id="bEffectiveDate">
					<form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="form-control" onkeypress="validateNumeric();"
						onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');" onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
						onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" /> 
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				  </div>
					<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
					<div id="EffectiveDate_msg" class="mandatory" style="display: none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true" /></div>
					<div class="errormsg" id="EffectiveDate_error2" style="display: none"></div>
					<div class="errormsg" id="EffectiveDateData_error"><spring:message htmlEscape="true" code="error.compare.EFFECTIVEDATE"></spring:message></div>
					<div class="errormsg" id="EffectiveDateBlank_error"><spring:message htmlEscape="true" code="ordereffectiveDate.required"></spring:message></div>
					<div class="errormsg" id="EffectiveFutureDate_error"><spring:message htmlEscape="true" code="error.valid.EFFECTIVEFUTUREDATE"></spring:message></div>
				</div>
		</div>	
		
		<div class="form-group">
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
				<label class="col-sm-3 control-label"><spring:message code="Label.GAZPUBDATE" htmlEscape="true"></spring:message></label>
					<div class="col-sm-6">
					   <div class="input-group date datepicker" id="bGazPubDate">
						<form:input id="GazPubDate" path="gazPubDate" type="text" class="form-control" readonly="true" />
						  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
						   <span class="errormsg" id="GazPubDate_error"></span>
						  <form:errors path="gazPubDate" cssClass="errormsg" htmlEscape="true"></form:errors>
						  <span id="GazPubDate_msg" class="mandatory" style="display: none">Please Enter Gazette Publication Date Like 12-04-2012</span>
						<div id="GazPubDate_error" class="error"></div>
						<div id="GazPubDate_msg" style="display: none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true" /></div>
						<div class="errormsg" id="GazPubDate_error2" style="display: none"></div>
					</div>
				</c:if>
		</div>	
		
			<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
							<div id="divGazettePublicationUpload">
							<%@ include file="../common/attachmentgovtcp.jspf"%>
							</div>
					</c:if>
					
				<div class="form-group">
					<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
					  <label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message><span class="mandatory">*</span></label>
						<div class="col-sm-6">
							<form:select path="templateList" id="templateList" style="width:280px" class="form-control" onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
								onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');" onkeyup="hideMessageOnKeyPress('templateList')">
								<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECT"></spring:message></form:option>
								<form:options items="${templateList}" itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
							</form:select> <span class="errormsg" id="templateList_error"></span>
								<span><form:errors cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span>
								<span style="display: none;" class="mandatory" id="templateList_msg">Please Select Government order Template</span>
								<div id="templateList_error" class="error"></div>
								<div id="templateList_msg" style="display: none"><spring:message code="error.blank.template" htmlEscape="true" /></div>
								<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true" /></div>
								<div class="errormsg" id="templateList_error2" style="display: none"></div>
						</div>
					</c:if>
				</div>	
				
				<form:hidden htmlEscape="true" path="buttonClicked" value="" />
		<div class="box-footer">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="pull-right">
				<div id="drafthide">
				 <button type="button"  class="btn btn-success" id="BtnSA" onclick="return selectValidateAndSubmit('S');" name="Submit"  ><i class="fa fa-floppy-o"></i> <spring:message code="Button.SAVE" htmlEscape="true"></spring:message></button>
				 </div> 
					 <button type="button" class="btn btn-primary" id="BtnSAP" onclick="return selectValidateAndSubmit('P');"name="Submit"><spring:message code="Label.PUBLISH" htmlEscape="true" text="Publish" ></spring:message></button> 
					 <button type="button" class="btn btn-danger " name="Submit6" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
				</div>
			</div>
		</div>
		 --%>
		
		
		
	<!---------------------old jsp start  ----------------------------->
		
		
		<%--  <body  onload="clearOrdernoErrors();return toggledisplayedOnload('${existVilOrUlbFlag}');renameListMethod(1);" >
 --%>	

 
 
 <%-- 	<%
			String formname="ManageSchemeSpecificATRFormat";
			request.setAttribute("formId", formname);
		%>
	 --%>
         <%--   <div class="overlay" id="overlay1" style="display:none;"></div>
                <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.family_msg}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.family_msg}"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
				
				<c:if test="${!empty family_error}">
			
				<table>
				<tr><td rowspan="2"><Div class= "failur"></div></td>
				
				<td><center><div class="helpMsgHeader" style="width:275px;"><b>Failure Message</b></div></center></td></tr>
				<tr><td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
	          </td></tr></table>
			
				</c:if>
							 
			</div>
       </div> --%>   
       
       
       
      <%--  <section class="content">
	<div class="row" id="frmcontent">
	   <section class="col-lg-12">
		  <div class="box">
			 <div class="box-header with-border">
				<h3 class="box-title"><spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message></h3>
			 </div> -
       
      <div class="box" id="box">
            <a class="boxclose" id="boxclose"></a>
            <div id="validate_error" >
							<table><tr><td rowspan="2"><div class= "errorImg"></div></td>
							<td><div  class="helpMsgHeader" style="width:275px;"><h4>Error Message</h4></div></td></tr>
			                <tr><td><div class="errorfont"><spring:message code="error.blank.commonAlert" htmlEscape="true"></spring:message></div>
			                </td></tr> </table>
                         
			</div>			
        	</div>	

  <div id="helpDiv" class="helpMessage" >
 <div class="helpheader"><h4>Help Message</h4></div>
<div id="helpMsgText" class="helptext"></div> 
</div> --%>
				
				
				<%-- <div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
						</div>
						
						
						
					<div  >
						<ul class="listing" style="width: 100%">
							<form:hidden htmlEscape="true" path="govtOrderConfig"
								value="${govtOrderConfig}" />
							<form:hidden htmlEscape="true" path="operation" value="C" />
													
							<c:if test = "${flag1 eq 0}"> 
									
								<li style="width: 48%">
								
									<label><spring:message
											code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
									</label><span id="required" class="errormsg">*</span><br /> <form:select 
										htmlEscape="true" path="districtNameEnglish"
										class="combofield" id="ddDistrict" style="width: 150px"
										onchange="getSubDistrictandULBList(this.value);" onclick="clearalllist();" onfocus="validateOnFocus('ddDistrict');helpMessage(this,'ddDistrict_msg');"
										 onblur="vlidateOnblur('ddDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDistrict')"  disabled="true" >
										<form:options items="${districtList}"  class="form-control"
											itemLabel="districtNameEnglish" 
											itemValue="districtPK.districtCode" />
									</form:select> <br />
									<div id="ddDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
								</li>
									
								<li style="width: 48%">
									<label><spring:message
											code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
									</label><span id="required" class="errormsg">*</span><br /> <label>
								
								 	<form:select 
										htmlEscape="true" path="subdistrictNameEnglish"
										class="combofield" id="ddSubdistrict" style="width: 150px"
										onchange="helpMessage(this,'ddDistrict_msg');"
										 onblur="hideHelp();" onkeyup="hideMessageOnKeyPress('ddSubdistrict')"  disabled="true" >
										<form:options items="${subDistrictList}" itemLabel="subdistrictNameEnglish" 
											itemValue="subdistrictPK.subdistrictCode" />
									</form:select></label> </br>
									<div class="errormsg">
										<form:errors htmlEscape="true" path="subdistrictNameEnglish"></form:errors>
									</div>
										<div id="ddSubdistrict_msg" style="display:none"><spring:message code="error.PSSDT" htmlEscape="true"/></div>
								</li>						
									</c:if>																
						</ul>
					</div>
					
					</div>
				</div> --%>
				
				
				
				
				
				
				
				
				
				
				
				
				
				<%--  <div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CORRECTION" htmlEscape="true"></spring:message>
						</div>
						
					<div  >
						<ul class="" style="width: 100%">
							<c:forEach var="listVillageDetails"
								varStatus="listVillageDetailsRow"
								items="${modifyVillageCmd.listVillageDetails}">
								
<!-- 									<td width="14%" rowspan="14">&nbsp;</td> -->
									<ul class="listing" style="width: 100%"> 
									<li style="width: 40%">
										<label><spring:message
												code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind 
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameEnglish">
												<input type="text" class="frmfield" id="OfficialAddress"
													 onblur="officialAddressVil();modifyVillageVal(this.value);vlidateOnblur('OfficialAddress','1','15','c');hideHelp();"
													onkeypress="return validateAlphanumericUpdateKeysWard(event);"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													 />
											</spring:bind> </label>
										<div id="errProSelectDist" style="color: red;"></div> <span
										class="errormsg" id="OfficialAddress_error"></span> <label></label>
										<div class="errormsg">
											<form:errors htmlEscape="true" path="newVillageNameEnglish"></form:errors>
										</div>
										<div id="OfficialAddress_msg" style="display: none">
											Please Enter Village Name
											
										</div>
										
										</li>
									<li style="width: 6%">
										<c:if test="${pageWarningEntiesFlag==true}">
										<label><spring:message
												code="Label.WARNINGFLAGSTATUS" htmlEscape="true"></spring:message> </label><br/>
												<img src="images/ylw_flg.png" width="13" height="9" />
										</c:if>
									</li>
									<li style="width: 45%">
										
										<label><spring:message
													code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message> </label><br /> <label>
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageNameLocal">
													<input type="text" class="frmfield" onkeypress="return validateAlphanumericUpdateKeysWard(event);"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />"
														 />
												</spring:bind> </label>
											<div class="errormsg"><form:errors htmlEscape="true" path="newVillageNameLocal"></form:errors></div>
									</li>
								</ul>
								<ul class="listing" style="width: 100%">
								<li style="width: 48%">
									<label><spring:message
												code="Label.ALIASENGLISH" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasEnglish">
												<input type="text" id="txtaliasEnglish" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													  />
											</spring:bind> </label>
											<div class="errormsg" id="aliasNameEngData_error">
												<spring:message htmlEscape="true" code="Error.VillageAliasNameEngData"></spring:message>
									       </div>
										<div class="errormsg"><form:errors htmlEscape="true" path="newVillageAliasEnglish"></form:errors></div>
								</li>
								<li style="width: 45%">
									<label><spring:message code="Label.ALIASLOCAL" htmlEscape="true"></spring:message>
									</label><br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].aliasLocal">
												<input type="text" id="txtAliaslocal" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />"
													/>
											</spring:bind> </label>
											 <div class="errormsg" id="aliasNameLocData_error">
												<spring:message htmlEscape="true" code="Error.VillageAliasNameLocalData"></spring:message>
									       </div>
										<div class="errormsg"><form:errors htmlEscape="true" path="newVillageAliasLocal"></form:errors></div>
								</li>

								
								<li style="width: 48%">
									<label><spring:message
												code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].sscode">
												<input type="text" id="txtSscode" maxlength="5"
													class="frmfield" onblur="chekcalphanumeric(this.value,5);"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false"/>" />
											</spring:bind> </label>
											<div class="errormsg" id="sscode_error">
												<spring:message htmlEscape="true" code="Error.SSCode"></spring:message>
									       </div>
											<form:errors path="sscode" class="errormsg" htmlEscape="true"></form:errors> 
											<form:errors htmlEscape="true" path="stateSpecificCode"></form:errors>
										<div class="errormsg"></div>
								</li>
								<li style="width: 100%">
								<label><spring:message code="Label.VILLAGESTATUS" htmlEscape="true"></spring:message>
									</label><br /> <label> 
									
										<c:if test="${fn:containsIgnoreCase(modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].villageStatus,'R')}">
											<form:select path="villageStatus" cssClass="combofield" cssStyle="width: 150px">								
													<option value="R">Reserve Forest</option>
													<option value="U">Un-inhabitant</option>
													<option value="I">Inhabitant</option>
											</form:select>
										</c:if>
										<c:if test="${fn:containsIgnoreCase(modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].villageStatus,'U')}">
											<form:select path="villageStatus" cssClass="combofield" cssStyle="width: 150px">								
													<option value="U">Un-inhabitant</option>
													<option value="R">Reserve Forest</option>
													<option value="I">Inhabitant</option>
											</form:select>
										</c:if>
										
										<c:if test="${fn:containsIgnoreCase(modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].villageStatus,'I')}">
											<form:select path="villageStatus" cssClass="combofield" cssStyle="width: 150px">								
													<option value="I">Inhabitant</option>
													<option value="R">Reserve Forest</option>
													<option value="U">Un-inhabitant</option>
											</form:select>
										</c:if>
										
									</label> <br />
									<div class="errormsg"></div>

							</li>
							<li>
								<label><spring:message code="Label.SURVEYNUMBER" htmlEscape="true"></spring:message>
								</label> <br /> 
								<input type="hidden" name="psize" id="psize"/>	
																		
								<table id="ptablePerson" align="center"><tr>
									</tr>
									<tr>
									<td>1</td>
									<td><form:input htmlEscape="true" type="text"
																			class="frmfield"  style="width: 157px" path="surveyNumber"
																			id="surveyNumber" name="surveyNumber" onblur="(this.value,4);"  maxlength="10" /> <input
																			type="button" name="Submit32" value="Add Survey Number"
																			onclick="incrementCount()" /></td>
									</tr>
									
								</table>
									<div id="errBlockCode" style="color: red;"></div>
								<table align="left" id="surveytable">
									 <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Remove" style="width: 150px" onclick="removeRow();" align="left"/></td></tr>
								</table>														
																							
									<div class="errormsg"></div>
									<div id="addsurveys"></div>
							</li>
							</ul>
					 </ul> 
					   </div>
					</div>
				</div> --%>  



			<%-- 	<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.HOWVILLAGEFORMED" htmlEscape="true">
								<span class="errormsg">*</span>
							</spring:message>
						</div>				
															
								<div  >
									<ul class="blocklist">
										<li>
											<div>
											<ul class="listing" style="width: 100%">
											<li>
												<label><spring:message
														code="Label.EXISTINGVILLAGE" htmlEscape="true"></spring:message>
												</label>
											
												<label> <form:input
														htmlEscape="true" name="checkbox" type="radio"
														value="true" id='chkcvillage'
														path="createFromExistingVillages"
														onclick="toggledisplayed('chkcvillage','cvillage')" />
												 </label>
											</li>
											</ul>
											</div>
										</li>
										<li>
											<ul class="listing">
												<li>
													<label><spring:message
														code="Label.NEWLAND" htmlEscape="true"></spring:message>
													</label>
												
													<form:input htmlEscape="true"
														name="checkbox" type="radio" value="true"
														id='chknothing' path="createFromNewLand"
														onclick='toggledisplayed("chknothing","chknothing")' />
												</li>
											</ul>
										</li>
										<li>
											<ul class="listing">
												<li><label><spring:message
														code="Label.ULB" htmlEscape="true"></spring:message>
													</label>
													<label> <form:input
														htmlEscape="true" name="checkbox" type="radio"
														value="true" id='chkculb'
														path="createFromCoverageOfUrbanLocalBody"
														onclick='toggledisplayed("chkculb","culb")' /> </label>
												</li>
											</ul>
										</li>
										<li>
											<div class="errormsg">
													<form:errors path="createFromExistingVillages"></form:errors>
											</div>
											
										</li>
									</ul>
								</div>
									
									<div class="errormsg"></div>
					</div>
				</div>
				
				
				<div id='cvillage' class="frmpnlbg"
					style="visibility: hidden; display: none;">
					<div class="frmtxt">
										
						<div class="frmhdtitle">
							<spring:message code="Label.CONTRIBUTINGLANDREGION"
								htmlEscape="true"></spring:message>
						</div>
					
					<div class="clear"></div>
					<div  >
						<div class="ms_container">
							<div class="ms_selectable">
								<strong><spring:message code="Label.VILLAGES"
										htmlEscape="true"></spring:message></strong>
										<br/>
								<form:select name="select9" size="1" id="villageListMain"
									path="villageList" multiple="multiple" class="frmtxtarea"
									style="height: 150px; width: 242px"></form:select>
							</div>
							<div class="ms_buttons">
								<label> <input type="button" class="bttn"
									id="btnaddVillageFull" style="width: 125px" name="Submit4"
									value="Select Full Villages&gt;&gt;"
									onclick="addItemVillage('villageListNew','villageListMain','FULL',true);" />
								</label> <label> <input type="button" style="width: 80px" class="bttn"
									id="btnremoveOneVillage" name="Submit4" value=" &lt; "
									onclick="removevillageandsurveynoForDraft('villageListNew','villageListMain',true)" />
								</label> <label> <input type="button" style="width: 80px" class="bttn"
									id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;"
									onclick="removeAllSelectedVillagesForDraft('villageListNew','villageListMain',true)" />
								</label> <label> <input type="button" style="width: 125px" class="bttn"
									id="btnaddVillagePart" name="Submit4"
									value="Select Part Villages&gt;&gt;"
									onclick="addItemVillageForDraft('villageListNew','villageListMain','PART',true)" />
								</label>

							</div>
							<div class="ms_selection">
								<label><spring:message
										code="Label.CONTRIBUTINGVILLAGELIST" htmlEscape="true"></spring:message></label>
								<form:select name="select4" id="villageListNew" size="1"
									multiple="multiple" path="contributedVillages"
									class="frmtxtarea" style="height: 150px; width: 242px">
								</form:select>
								<div class="errormsg">
									<form:errors path="contributedVillages"></form:errors>
								</div>
								<label> <input type="button"
										class="bttn" id="tbnSurveyNumbers" name="Submit7"
										onclick="getSurveyNobyVillage();"
										value="<spring:message code="Label.GETPARTVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message>" />
								</label>
							</div>
						</div>
						<div class="clear"></div>
					</div>
						<div>							
								
								<div class="errormsg"></div>						
						</div>
		
						<div id='csurvey' style="visibility: hidden;">
						
						
						<div  >
							<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message code="Label.SELECTEDVILLAGESURVEYNUMBER" htmlEscape="true"></spring:message></label>
											<form:select name="select5" id="surveyListMain" size="1" multiple="multiple" path="surveyList" class="frmtxtarea" style="height: 150px; width: 242px"></form:select>
										</div>
										<div class="ms_buttons">
							
											<label> <input type="button" style="width: 150px" name="Submit42" class="bttn"
														value="<spring:message code="Label.SELECTSURVEYNUMBER" htmlEscape="true"></spring:message>&gt;&gt;"
														onclick="fillselectedSurveryNo('surveyListNew','surveyListMain',document.getElementById('surveyListMain').value,false)" />
											</label>

											<label> <input type="button" style="width: 80px" class="bttn" id="btnremoveOneSurvey"
														name="Submit4" value=" &lt; "
														onclick="removeItem('surveyListNew','surveyListMain',false)" />
											</label>
												
											<label> <input type="button" style="width: 80px" class="bttn" name="Submit42"
														id="btnremoveAllSurveys" value="&lt;&lt;"
														onclick="removeAll('surveyListNew','surveyListMain',false)" />
											</label>

										</div>
										<div class="ms_selection">
												<label><spring:message code="Label.CONTRIBUTINGSURVEYNUMBER" htmlEscape="true"></spring:message></label>
												<form:select name="select5" id="surveyListNew" size="1" multiple="multiple" path="contributedSurvey" class="frmtxtarea" style="height: 150px; width: 242px"></form:select>
										</div>	
										<div class="clear"></div>																	
							</div>
						</div>																								
										<br></br>
									
										<div id="renameDiv" class="frmtxt">
										<div class="frmhdtitle">
											<spring:message code="Label.RENAME.CONTRIBUTING.VILLAGE"
												htmlEscape="true"></spring:message>
										</div>
										
									<div>	
										<ul class="blocklist">
											
											<li>
												<div>
												<ul class="listing">
													<li>
														<label><spring:message
															code="Label.YES"
															htmlEscape="true"></spring:message>
														</label>
													
														<input type="radio" name="rename" id="rename" value="1" onclick="renameListMethod(this.value);"></input>												
													</li>											
												</ul>
												</div>
											</li>
											<li>
												<ul class="listing">
												<li>
													<label><spring:message code="Label.NO" htmlEscape="true"></spring:message></label>												
											
													<input type="radio" name="rename" id="rename" value="0" onclick="renameListMethod(this.value);" checked="checked"></input>												
												</li>											
												</ul>
											</li>
										</ul>
									</div>
									<div  >
									 <table id="renameList" style="display: none">
									  <tr>
									  <td width="5%"><strong><spring:message code="Label.SNO" htmlEscape="true"></spring:message></strong></td>  
									  <td width="40%"><strong><spring:message code="Label.CONTRIBUTINGVILLAGE" htmlEscape="true"></spring:message></strong></td> 
									  <td width="40%" align="center">
									  <input type="button" name="CheckAll" value="Check All"	onclick="checkAll(document.modifyVillageCmd.reNameFlag)"></input>
										<input type="button" name="UnCheckAll" value="Uncheck All"
										onclick="uncheckAll(document.modifyVillageCmd.reNameFlag)"></input></td>   
									  <td width="40%"><strong><spring:message code="Label.CURRENTVILLAGENAME" htmlEscape="true"></spring:message></strong></td>  
									  <td width="40%"><strong><spring:message code="Label.RENAMEVILLAGE" htmlEscape="true"></spring:message></strong></td>
									  </tr>
									</table>
								</div>	
									</div>
									
									<br/>
								<div id="divispesaFlag" >
								</div>
										<div class="errormsg"></div>
								<div class="clear"></div>
						</div>
						
					</div>
				</div>

				<div id='culb'
					style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px; visibility: hidden; display: none">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.SELECTEDDISTRICTULB"
								htmlEscape="true"></spring:message>
						</div>
						
												
							<div  >								
								<div class="ms_container">
										<div class="ms_selectable">
											<label><spring:message code="Label.ULBCOVEREDLANDREGION" htmlEscape="true"></spring:message></label>
											<form:select name="select6" path="coveredLandRegionByULB" id="ulbListMain" size="1" class="frmtxtarea" multiple="multiple" style="height: 150px; width: 242px"></form:select>
										</div>
										<div class="ms_buttons">
													<label> <input class="bttn" type="button" name="Submit4222"
																onclick="addItem('ulbListNew','ulbListMain','FULL',true)"
																value="<spring:message code="Label.FULLULB" htmlEscape="true"></spring:message>&gt;&gt;" />
													</label>
														
													<label> <input class="bttn" type="button" id="btnremoveOneULB" name="Submit4"
																value=" &lt; "
																onclick="removeItem('ulbListNew','ulbListMain',true)" />
													</label>
													
													<label> <input class="bttn" type="button" name="Submit22223" value="&lt;&lt;"
																style="width: 58px"
																onclick="removeAll('ulbListNew','ulbListMain',true)" />
													</label>
													<label> <input class="bttn" type="button" name="Submit22222"
																onclick="addItem('ulbListNew','ulbListMain','PART',true)"
																value="<spring:message code="Label.PARTULB" htmlEscape="true"></spring:message>&gt;&gt;" />
													</label>
														
										</div>
										<div class="ms_selection">
											<form:select name="select6" path="selectedCoveredLandRegionByULB" id="ulbListNew" multiple="multiple" class="frmtxtarea" style="height: 150px; width: 242px"></form:select>
												<div class="errormsg">
													<form:errors path="selectedCoveredLandRegionByULB"></form:errors>
												</div>
										</div>																		
								</div>																
								<div class="errormsg"></div>
								<div class="clear"></div>
						</div>
					</div>
				</div>
				
				
				
				
				
				
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
						</div>
						<%@ include file="../govtbody/ExistingGovernmentOrderVillage.jsp"%>
							
					<div>							
						<ul class="blocklist">
							<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
								<li>
								
										<label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
												<input type="text" id="OrderNo" class="frmfield"
													readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${fn:trim(status.value)}" escapeXml="false" />"
													style="width: 128px" />

											</spring:bind></label> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">
											<input type="hidden" id="orderCode"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind> <form:errors path="orderNocr" cssClass="errormsg"
											htmlEscape="true" />
										<div class="errormsg"></div>
									
								</li>
								<li>
										<label><spring:message code="Label.ORDERDATE"
												htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
												<input type="text" id="OrderDate" readonly="readonly"
													style="width: 128px" class="frmfield"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />
											</spring:bind>

								</li>
								<li>
										<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
												  <c:if test="${listStateDetails.ordereffectiveDatecr >}">	
												<input type="text" style="width: 128px" class="frmfield"
													id="EffectiveDate" readonly="readonly"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />

											</spring:bind>
								</li>


								<li>
									
								</li>

							</c:if>

							<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<li>										
										<label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderNocr">
												<input type="text" maxlength="60" class="frmfield"
													onkeypress="return validateaGovtOrderNOforModify(event);"
													id="OrderNo" onfocus="validateOnFocus('OrderNo');"
													onblur="vlidateOrderNo('OrderNo','1','60');"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${fn:trim(status.value)}" escapeXml="false" />"
													style="width: 128px" />

											</spring:bind></label>
										<div id="OrderNo_error" class="errormsg">
											<spring:message code="error.required.ORDERNUM"
												htmlEscape="true"></spring:message>
										</div>
										<div id="OrderNo_msg" class="errormsg">
											<spring:message code="error.required.ORDERINPUTTYPE"
												text="Please Enter AlphaNumerics Space . / - ( ) Only"
												htmlEscape="true" />
										</div> <span class="errormsg" id="OrderNo_error"></span> <spring:bind
											path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderCodecr">
											<input type="hidden"
												name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" escapeXml="false" />" />
										</spring:bind> <form:errors path="orderNocr" cssClass="errormsg"
											htmlEscape="true" />
										<div class="errormsg"></div>
								</li>
								<li>
										<label><spring:message code="Label.ORDERDATE"
												htmlEscape="true"></spring:message> </label> <c:if
											test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <c:if test="${mandatoryFlag==true}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
													<input type="text" id="OrderDate" style="width: 128px"
														class="frmfield"
														onblur="vlidateOnblur('OrderDate','1','15','c');"
														onfocus="hideAll();setOrderDateforCorrection('OrderDate','#OrderDateData_error');"
														onchange="vaidatetOrderDateforCorrectionEntity(this.value,'#OrderDateData_error');"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />
												</spring:bind>
											</c:if> <c:if test="${mandatoryFlag==false}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].orderDatecr">
													<input type="text" id="OrderDate" style="width: 128px"
														class="frmfield" onfocus="hideAll();"
														onblur="vlidateOnblur('OrderDate','1','15','c');"
														onchange="validateDatetoFuture('OrderDate','#OrderFutureDate_error');setEffectiveDatetoOrderDate(this.value)"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />
												</spring:bind>
											</c:if>
									</label><span class="errormsg" id="OrderDate_error"></span>
									<form:errors path="orderDatecr" cssClass="errormsg"
											htmlEscape="true" />
										<div class="errormsg" id="OrderDateBlank_error">
											<spring:message htmlEscape="true"
												code="error.required.ORDERDATE"></spring:message>
										</div>

										<div class="errormsg" id="OrderFutureDate_error">
											<spring:message htmlEscape="true"
												code="error.INCORECT.ORDERDATE"></spring:message>
										</div>

										<div class="errormsg" id="OrderDateValid_error">
											<spring:message htmlEscape="true"
												code="error.valid.DATEFORMAT"></spring:message>
										</div>

										<div class="errormsg" id="OrderDateData_error">
											<spring:message htmlEscape="true"
												code="error.compare.ORDERDATE"></spring:message>
										</div>
										<div class="errormsg"></div>

								</li>
								<li>
										<label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message>
										</label> <c:if test="${mandatoryFlag==true}">
											<span class="errormsg">*</span>
										</c:if> <br /> <label> <c:if test="${mandatoryFlag==true}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
													  <c:if test="${listStateDetails.ordereffectiveDatecr >}">	
													<input type="text" id="EffectiveDate" style="width: 128px"
														class="frmfield" readonly="readonly"
														onfocus="validateOnFocus('EffectiveDate');"
														onblur="vlidateOnblur('EffectiveDate','1','15','c');"
														onkeypress="validateNumeric();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />

												</spring:bind>
											</c:if> <c:if test="${mandatoryFlag==false}">
												<spring:bind
													path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].ordereffectiveDatecr">
													  <c:if test="${listStateDetails.ordereffectiveDatecr >}">	
													<input type="text" id="EffectiveDate" style="width: 128px"
														class="frmfield"
														onfocus="validateOnFocus('EffectiveDate');"
														onblur="vlidateOnblur('EffectiveDate','1','15','c');"
														onchange="validateDatetoFuture('EffectiveDate','#EffectiveFutureDate_error');validateEffectiveDatecompOrderDate('OrderDate','EffectiveDate','#EffectiveDateData_error');"
														onkeypress="hideAll();"
														name="<c:out value="${status.expression}"/>"
														value="<c:out value="${status.value}" escapeXml="false" />" />

												</spring:bind>
											</c:if>
										
								
											<div class="errormsg" id="EffectiveDateData_error">
												<spring:message htmlEscape="true"
													code="error.compare.EFFECTIVEDATE"></spring:message>
											</div>

											<div class="errormsg" id="EffectiveDateBlank_error">
												<spring:message htmlEscape="true"
													code="ordereffectiveDate.required"></spring:message>
											</div>
											<div class="errormsg" id="EffectiveFutureDate_error">
												<spring:message htmlEscape="true"
													code="error.valid.EFFECTIVEFUTUREDATE"></spring:message>
											</div>
											<div class="errormsg"></div>
									</label><span class="errormsg" id="EffectiveDate_error"></span> <form:errors
											path="ordereffectiveDatecr" cssClass="errormsg" />
										<div class="errormsg"></div>
							
								</li>
								<li>
										<label><spring:message code="Label.GAZPUBDATE"
												htmlEscape="true"></spring:message> </label><br /> <label>
											<spring:bind
												path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].gazPubDatecr">
												<input type="text" id="GazPubDate" style="width: 128px"
													class="frmfield" onkeyup="validateNumeric();"
													onchange="noOrderDataValid('GazPubDate','#GuzpubDateBlankOrderDate_error','OrderDate');compareGuzpubDatetoOrderDate('GazPubDate','#GuzpubDateCompareOrderDate_error','OrderDate')"
													onkeypress="hideAll();"
													name="<c:out value="${status.expression}"/>"
													value="<c:out value="${status.value}" escapeXml="false" />" />

											</spring:bind>
										</label>
										<div class="errormsg" id="GuzpubDateValid_error">
											<spring:message htmlEscape="true"
												code="error.valid.DATEFORMAT"></spring:message>
										</div>
										<div class="errormsg" id="GuzpubDateBlankOrderDate_error">
											<spring:message htmlEscape="true"
												code="error.required.ORDERDATE"></spring:message>
										</div>

										<div class="errormsg" id="GuzpubDateCompareOrderDate_error">
											<spring:message htmlEscape="true"
												code="error.compare.GuzpubDate"></spring:message>
										</div> <form:errors path="gazPubDatecr" cssClass="errormsg" />
										<div class="errormsg"></div>
										<div id="GazPubDate_error" class="error">
											<spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message>
										</div>
										<div id="GazPubDate_msg" style="display: none">
											<spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true" />
										</div>
										<div class="errormsg" id="GazPubDate_error1">
											<form:errors path="gazPubDate" htmlEscape="true" />
										</div>
										<div class="errormsg" id="GazPubDate_error2"
											style="display: none"></div>
								</li>

								<li id="divGazettePublicationUpload">									
									<div>
										<%@ include file="../common/updateattach.jspf"%>
									</div>																			
								</li>
							</c:if>
						</ul>												
						</div>
					</div>


				</div>
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.GISNODES" htmlEscape="true"></spring:message>
						</div>
						
						
						
					<div  >
					
					<ul class="listing">
									<li>
											<label id="lbllatitude"><spring:message code="Label.LATITUDE" htmlEscape="true"></spring:message> </label>
									</li>

									<li  >
											<label id="lbllongitude"><spring:message code="Label.LONGITUDE" htmlEscape="true"></spring:message> </label>
									</li>
									<li  >
											<label> <input type="button" name="Submit3" value='<spring:message code="Button.ADDNODES" htmlEscape="true"></spring:message>' onclick="addgisnodes1()" />
										</label>
									</li>
								</ul>
					
					
						<ul id="latlontableId" class="blocklist">						

							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].mapCode">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>
							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageCode">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>
							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].villageVersion">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>
							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictCode">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>
							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].subdistrictVersion">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>
							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].cordinate">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>

							<spring:bind
								path="modifyVillageCmd.listVillageDetails[${listVillageDetailsRow.index}].warningflag">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" escapeXml="false" />" />
							</spring:bind>


							<c:choose>
								<c:when
									test="${modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].cordinate!=null}">
									<c:set var="count" value="1"></c:set>
								</c:when>

								<c:otherwise>
									<c:set var="count" value="0"></c:set>




								</c:otherwise>
							</c:choose>

							<c:if test="${count>0}">

								<c:set var="str1"
									value="${modifyVillageCmd.listVillageDetails[listVillageDetailsRow.index].cordinate}" />
								<c:set var="i" value="2"></c:set>

								<c:forEach var="str2" items="${fn:split(str1, ',')}">
									<li id="trId${i}">
									  <ul class="listing">
										<c:forEach var="num" items="${fn:split(str2, ':')}">
											
												<c:if test="${count%2==1}">
													<li style="padding-left: 9px;"><label> <input
														type="text" id="lati${count}" name="lati"
														onkeypress="return validateCordination(event,'lati${count}','lati');"
														class="frmfield" value="${num}" />
													</label>
													<div>
														<form:errors path="lati" cssClass="errormsg"
															htmlEscape="true" />
													</div>
													</li>
	
												</c:if>


												<c:if test="${count%2==0}">
													<li>
														<label> <input type="text"
														id="longi${count}" name="longi"
														onkeypress="return validateCordinationLatandLongi(event,'longi${count}','longi');"
														class="frmfield" value="${num}" />
														</label>
														<div>
														<form:errors path="longi" cssClass="errormsg"
															htmlEscape="true" />
														</div>
													</li>
												</c:if>

												<c:set var="count" value="${count+1}"></c:set>
											
										</c:forEach>
										<li>
											 <input type="button"
											onclick="deleteRow('${i}');" value="Remove" />

										</li>
									</ul>
										
								</li>

									<c:set var="i" value="${i+1}"></c:set>

								</c:forEach>
							</c:if>

							<li>
								<div id="addgisnodes"></div>
							</li>

							<c:if test="${mapConfig == 'true'}">
								<li class="clear">
									<%@ include file="../common/modifyGis_nodes.jspf"%>
								</li>
							</c:if>
						</ul>
					</div>
						

					</div>
				</div>


				</c:forEach>
                 <div class="btnpnl">
						<label> <input type="button" id="BtnSA" onclick="return validateVillage();" name="Submit" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" /></label>
						<label> <input type="button" id="BtnSAP" onclick="return validateVillagePublish();" name="Submit" value="<spring:message code="Label.PUBLISH" htmlEscape="true"></spring:message>" /></label>									
				</div --%>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	
				
            
			</form:form>
			
			
			
			
			
			<%-- <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
					<div class="modal-dialog" style="width:950px;">
							<div class="modal-content">
				  				<div class="modal-header">
				   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
				    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
				    			  	
				  				</div>
				  				<div class="modal-body" id="dialogBXbody">
				        			<iframe id="myIframe" width="910" height="650"></iframe>
				        		</div>
							   </div>
							  </div>
							  </div>
			
			<div class="modal fade" id="sucessAlert" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Message</h4>
	        </div>
	        <div class="modal-body">
	           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
			   <esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
	        </div>
	        </div>
	        
	        
	        
	       </div>
		  <script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		 </div> --%>
	    </div> 
	   </div>
	  </div>
    </section>
   </div>
  </section> 
	
</body>
</html>
