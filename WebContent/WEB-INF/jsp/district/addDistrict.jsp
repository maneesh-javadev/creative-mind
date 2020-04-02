<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="resource/common-resource/run_prettify.min.js"></script>
<script src="resource/common-resource/bootstrap-dialog.min.js"></script>
<script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>
<script>
var stateCode = '${district.stateCode}';
var districtCount=parseInt("<c:out value='${districtCount}' />");
objData = [];
isDistDuplicate = false;
function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}",'',"dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
}



function checkDuplicate() {
	if(isDistDuplicate) {
		$("#alertboxTitle").text("Error Message");
		$("#alertboxbody").text("<spring:message code='Error.dialog-duplicate-district' htmlEscape='true'></spring:message>");
		$('#alertbox').modal('show');	
		/* $("#dialog-duplicate-district").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		}); */
		return true;
	}
	return false;
}




/* $(function() {
	$("#accordion").accordion({
		event : "mouseover"
	});
}); */

function addanother(val1) {
	//$("#dialog:ui-dialog").dialog("destroy");
	
	 BootstrapDialog.show({
         title: 'Add another district?',
         message: "Are you confirm to add anot her District?",
         buttons: [{
             label: 'Yes',
             action: function(dialog) {
            		document.getElementById('buttonClicked').value = val1;
    				formSubmitPreview();
    				 dialog.close();
             }
         }, {
             label: 'NO',
             action: function(dialog) {
            	 dialog.close();
             }
         }]
     });

	
	/* $("#dialog-confirm-addanother").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			"Yes" : function() {
				document.getElementById('buttonClicked').value = val1;
				formSubmitPreview();
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	}); */
}

function nextButton(val1) {		//alert('calling nextButton...value='+val1);

	 BootstrapDialog.show({
         title: 'Confirmation Dialog',
         message: 'Are you sure?',
         buttons: [{
             label: 'Yes',
             action: function(dialog) {
            		document.getElementById('buttonClicked').value = val1;
    				formSubmitPreview();
    				 dialog.close();
             }
         }, {
             label: 'NO',
             action: function(dialog) {
            	 dialog.close();
             }
         }]
     });

	
	/* $("#dialog:ui-dialog").dialog("destroy");
	
	$("#dialog-confirm-next").dialog({
		resizable : false,
		height : 140,
		modal : true,
		buttons : {
			"Yes" : function() {
				document.getElementById('buttonClicked').value = val1;
				formSubmitPreview();
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	}); */
}

function nextSessionButton(val1) {			//alert('calling nextSessionButton...value='+val1);
	document.getElementById('buttonClicked').value = val1;
	document.forms['form1'].submit();
}

tempVillList = "";

function closeDialog(value) { 	// merge button code in Merge subdistrict
	if (value == "Done") {
		var villLeftLen = $("#dialogVillageList").find("option").length;
		var villSelectedLen = $("#selectedDialogVillageList").find("option").length;
		var btncreate = document.getElementById("create");
		
		if(!((villLeftLen > 0 || villSelectedLen > 0 ) && btncreate.disabled)) {	
			
			BootstrapDialog.show({
		         title: 'Confirmation Dialog',
		         message: "Please confirm that your merging of villages into Sub-District is done.If 'Yes' then you will not be able to merge villages into Sub-District again",
		         buttons: [{
		             label: 'Yes',
		             action: function(dialog) {
		            	 $('#merge1').attr("disabled", true);
							$('#select1').attr("disabled", true);
							$('#ddSourceDistrict').attr("disabled", true);
							$('#ddDestDistrict').attr("disabled", true);
							$('#ddSubdistrictforsubdistrict').attr("disabled", true);
							$('#subDistrictListNew').attr("disabled", true);
							$('#villageList').attr("disabled", true);
							$('#villageListNew').attr("disabled", true);
							$('#dialogone').modal('hide');	
		    				 dialog.close();
		             }
		         }, {
		             label: 'NO',
		             action: function(dialog) {
		            	 dialog.close();
		             }
		         }]
		     });
			
			
			/* $("#dialog-confirm").dialog({
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					"Yes" : function() {
						$('#merge1').attr("disabled", true);
						$('#select1').attr("disabled", true);
						$('#ddSourceDistrict').attr("disabled", true);
						$('#ddDestDistrict').attr("disabled", true);
						$('#ddSubdistrictforsubdistrict').attr("disabled", true);
						$('#subDistrictListNew').attr("disabled", true);
						$('#villageList').attr("disabled", true);
						$('#villageListNew').attr("disabled", true);
						$(this).dialog("close");
						$("#dialogone").dialog("close");
					},
					No : function() {
						$(this).dialog("close");
					}
				}
			}); */
		} else {
			//showErrorDialog("dialog-error-on-done");
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("Invalid operation, No subdistrict found to merge");
			$('#alertbox').modal('show');	
		}
	} else if (value == "Clear") {			//alert('calling close of merge...');
		$("#dialogone").modal('hide');
		dwr.util.removeAllOptions('selectedDialogVillageList');
		document.getElementById("storedCombiValues").value = "";
	} else if (value == "Merge") {		//alert('calling merge of merge...');
		try {
			var radioButtonValue = "";
			var radioButtonText = "";
			var count = 0;
			var radioObj = document.getElementsByName("subdistRadio");
			for ( var i = 0; i < radioObj.length; i++) {
				if (radioObj[i].checked == true) {
					count++;
					break;
				}
			}
			if (count == 0) {
				$("#alertboxTitle").text("Error Message");
				$("#alertboxbody").text("<spring:message code='Error.dialog-error' htmlEscape='true'></spring:message>");
				$('#alertbox').modal('show');	
				/* $("#dialog-error").dialog({
					resizable : false,
					height : 140,
					modal : true,
					buttons : {
						Cancel : function() {
							$(this).dialog("close");
						}
					}
				}); */
				return false;
			}
			var selObj = document.getElementById('selectedDialogVillageList');
			for ( var i = 0; i < selObj.length; i++) {
				selObj.options[i].selected = true;
			}
			if (selObj.options.length == 0) {
				$("#alertboxTitle").text("Error Message");
				$("#alertboxbody").text("<spring:message code='Error.dialog-error1' htmlEscape='true'></spring:message>");
				$('#alertbox').modal('show');	
				/* $("#dialog-error1").dialog({
					resizable : false,
					height : 140,
					modal : true,
					buttons : {
						Cancel : function() {
							$(this).dialog("close");
						}
					}
				}); */
				return false;
			}
			for ( var i = 0; i < radioObj.length; i++) {
				if (radioObj[i].checked == true) {
					radioButtonValue = radioObj[i].value;
					radioButtonText = radioObj[i].value;
					count++;
					break;
				}
			}
			var objectVillageList = "";
			var objectTextVillageList = "";
			for ( var i = 0; i < selObj.options.length; i++) {
				if (i == 0) {
					objectVillageList = selObj.options[i].value;
					objectTextVillageList = selObj.options[i].text;
				} else {
					objectVillageList = objectVillageList + ":"+ selObj.options[i].value;
					objectTextVillageList = objectTextVillageList + ":"+ selObj.options[i].text;
				}
			}
			var temp = "";
			var temp1 = "";
			if (document.getElementById("storedCombiValues").value == "") {
				temp = radioButtonValue + "#" + objectVillageList;
				document.getElementById("storedCombiValues").value = temp;
			} else {
				temp = document.getElementById("storedCombiValues").value + ","+ radioButtonValue + "#" + objectVillageList;
				document.getElementById("storedCombiValues").value = temp;
			}
			//alert("temp");
			var radioButtonFinalValue = "";
			var fullSubdistrict = document.getElementById("subDistrictListNewFull");
			for ( var i = 0; i < fullSubdistrict.options.length; i++) {
				if (fullSubdistrict.options[i].value == radioButtonText) {
					radioButtonFinalValue = fullSubdistrict.options[i].text;
					break;
				}
			}

			if (radioButtonFinalValue == "") {
				var fullSubdistrict = document.getElementById("subDistrictListNew");
				for ( var i = 0; i < fullSubdistrict.options.length; i++) {
					if (fullSubdistrict.options[i].value == radioButtonText) {
						radioButtonFinalValue = fullSubdistrict.options[i].text;
						break;
					}
				}
			}

			if (document.getElementById("histroryMergeSubDistrictList").value == "null") {
				temp1 = radioButtonFinalValue + "#" + objectTextVillageList;
				document.getElementById("histroryMergeSubDistrictList").value = temp1;
			} else {
				temp1 = document.getElementById("histroryMergeSubDistrictList").value	+ ","+ radioButtonFinalValue+ "#"+ objectTextVillageList;
				document.getElementById("histroryMergeSubDistrictList").value = temp1;
			}

			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			//alert('tempVillList before-->'+tempVillList);
			
			var temp = document.getElementById("selectedDialogVillageList");		//alert('vill list length->'+temp.options.length);		// changed by sushil
			//var temp = document.getElementById("dialogVillageList");
			if(temp.options.length > 0) {
				for ( var i = 0; i < temp.options.length; i++) {	
					if (tempVillList == "") {	
						tempVillList = temp.options[i].value + ":"+ temp.options[i].text;
					} else {	
						tempVillList = tempVillList + "," + temp.options[i].value+ ":" + temp.options[i].text;
					}
				}
			}
			/* final selected box values as a string format */
			//alert('final tempVillList going to assign into mergeVillageListId -->'+tempVillList);
			/* Added by sushil on 06-05-2013 */
			if(document.getElementById('storedCombiValues').value == "" && document.getElementById('storedNewCombiValues').value == "") {
				document.getElementById("mergeVillageListId").value = "";
			}
			/* End */
			document.getElementById("mergeVillageListId").value = tempVillList;
			for ( var i = selObj.options.length - 1; i >= 0; i--) {
				if (selObj.options[i].selected == true) {
					selObj.remove(i, null);
				}
			}

			var tempSubDistList = "";
			for ( var i = 0; i < radioObj.length; i++) {
				if (radioObj[i].checked == false) {
					var temp = document.getElementById("mergeSubDistId").value;
					var arraytemp = temp.split(",");
					for ( var j = 0; j < arraytemp.length; j++) {
						var tempTwo = arraytemp[j].split(":");
						if (radioObj[i].value == tempTwo[0]) {
							if (tempSubDistList == "") {
								tempSubDistList = tempTwo[0] + ":" + tempTwo[1];
							} else {
								tempSubDistList = tempSubDistList + ","+ tempTwo[0] + ":" + tempTwo[1];
							}
						}
					}
				}
			}

			document.getElementById("mergeSubDistId").value = tempSubDistList;
			var tableObj = document.getElementById("subDistRadioId");
			for ( var i = 0; i < tableObj.rows.length; i++) {
				if (radioObj[i].checked == true) {
					tableObj.deleteRow(i);
				}
			}

			
			 BootstrapDialog.show({
		         title: 'Success Message',
		         message: "Successful! Merging of selected villages into Sub-District",
		         buttons: [{
		             label: 'Merge Another',
		             action: function(dialog) {
		            	 var srcDialogVillageList = document.getElementById("dialogVillageList");
							if(srcDialogVillageList.options.length == 0) {
								alert('Nothing found to merge.');		//alert('Final tempVillList after-->'+tempVillList);
							} else {
								dialog.close();						
							}
		    				 
		             }
		         }, {
		             label: 'Close',
		             action: function(dialog) {
		            	 dialog.close();
		             }
		         }]
		     });
			
			/* $("#dialog-merge-success").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					"Merge Another" : function() {
						var srcDialogVillageList = document.getElementById("dialogVillageList");
						if(srcDialogVillageList.options.length == 0) {
							alert('Nothing found to merge.');		//alert('Final tempVillList after-->'+tempVillList);
						} else {
							$(this).dialog("close");							
						}
					},
					Close : function() {
						$(this).dialog("close");
					}
				}
			}); */
			return false;
		} catch (error) {
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("<spring:message code='Error.dialog-merge-failure' htmlEscape='true'></spring:message>");
			$('#alertbox').modal('show');
			/* $("#dialog-merge-failure").dialog({
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
					}
				}
			}); */
		}
	}
}

/* End merge button function*/

/* function on click of create subdistrict */
function closeDialogtwo(value) {
	if (value == "Done") {
		
		 BootstrapDialog.show({
	         title: 'Confirmation Dialog',
	         message: "Please confirm that your creation of Sub-District part is done.If 'Yes' then you will not be able to create Sub-District again",
	         buttons: [{
	             label: 'Yes',
	             action: function(dialog) {
	             	$('#create').attr("disabled", true);
					$('#select').attr("disabled", true);
					$('#ddSourceDistrict').attr("disabled", true);
					$('#ddDestDistrict').attr("disabled", true);
					$('#ddSubdistrictforsubdistrict').attr("disabled", true);
					$('#subDistrictListNew').attr("disabled", true);
					$('#villageList').attr("disabled", true);
					$('#villageListNew').attr("disabled", true);
					$('#confirmbox').modal('hide');	
	    			 dialog.close();
	             }
	         }, {
	             label: 'NO',
	             action: function(dialog) {
	            	 dialog.close();
	             }
	         }]
	     });
		
		
		/* $("#dialog-confirm-create").dialog({
			resizable : false,
			height : 200,
			modal : true,
			buttons : {
				"Yes" : function() {
					$('#create').attr("disabled", true);
					$('#select').attr("disabled", true);
					$('#ddSourceDistrict').attr("disabled", true);
					$('#ddDestDistrict').attr("disabled", true);
					$('#ddSubdistrictforsubdistrict').attr("disabled", true);
					$('#subDistrictListNew').attr("disabled", true);
					$('#villageList').attr("disabled", true);
					$('#villageListNew').attr("disabled", true);
					$(this).dialog("close");
					$("#dialogtwo").dialog("close");
				},
				No : function() {
					$(this).dialog("close");
				}
			}
		}); */
	} else if (value == "Clear") {
		$("#dialogtwo").modal('hide');
		dwr.util.removeAllOptions('selectedDialogVillageListCreate');
		document.getElementById("storedNewCombiValues").value = "";
	} else if (value == "Create Sub District") {				//alert('called create sub district..');
		try {
			var radioButtonValue = "";
			var selObj = document.getElementById('selectedDialogVillageListCreate');
			if (selObj.options.length == 0) {
				/* $("#dialog-error3").dialog({
					resizable : false,
					height : 140,
					modal : true,
					buttons : {
						Cancel : function() {
							$(this).dialog("close");
						}
					}
				}); */
				$("#alertboxTitle").text("Error Message");
				$("#alertboxbody").text("<spring:message code='Error.dialog-error3' htmlEscape='true'></spring:message>");
				$('#alertbox').modal('show');	
				return false;
			}
			if (document.getElementById('subDistId').value == "") {
				/* $("#dialog-error2").dialog({
					resizable : false,
					height : 140,
					modal : true,
					buttons : {
						Cancel : function() {
							$(this).dialog("close");
						}
					}
				}); */
				$("#alertboxTitle").text("Error Message");
				$("#alertboxbody").text("<spring:message code='Error.dialog-error2' htmlEscape='true'></spring:message>");
				$('#alertbox').modal('show');	
				return false;
			}

			radioButtonValue = document.getElementById('subDistId').value;
			var objectVillageList = "";
			for ( var i = 0; i < selObj.options.length; i++) {
				if (i == 0) {
					objectVillageList = selObj.options[i].value;
				} else {
					objectVillageList = objectVillageList + ":"	+ selObj.options[i].value;
				}
			}
			var temp = "";
			if (document.getElementById("storedNewCombiValues").value == "") {
				temp = radioButtonValue + "#" + objectVillageList;
				document.getElementById("storedNewCombiValues").value = temp;
			} else {
				temp = document.getElementById("storedNewCombiValues").value+ "," + radioButtonValue + "#" + objectVillageList;
				document.getElementById("storedNewCombiValues").value = temp;
			}
			// --------------------------------

			var objectTextVillageList = "";
			for ( var i = 0; i < selObj.options.length; i++) {
				if (i == 0) {
					objectTextVillageList = selObj.options[i].text;
				} else {
					objectTextVillageList = objectTextVillageList + ":"+ selObj.options[i].text;
				}
			}
			var temp1 = "";
			if (document.getElementById("histroryNewSubDistrictList").value == "null") {
				temp1 = radioButtonValue + " (FULL)#" + objectTextVillageList;
				document.getElementById("histroryNewSubDistrictList").value = temp1;
			} else {
				temp1 = document.getElementById("histroryNewSubDistrictList").value+ ","+ radioButtonValue+ " (FULL)#"	+ objectTextVillageList;
				document.getElementById("histroryNewSubDistrictList").value = temp1;
			}

			// ------------------------------------------

			if (document.getElementById("histroryNewSubDistrict").value == "null") {
				document.getElementById("histroryNewSubDistrict").value = radioButtonValue+ " (FULL)";
			} else {
				document.getElementById("histroryNewSubDistrict").value = document.getElementById("histroryNewSubDistrict").value	+ "," + radioButtonValue + " (FULL)";
			}

			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
			}
			var tempVillList = "";
			// changed dialogVillageListCreate by sushil on 25-04-2013 
			var temp = document.getElementById("selectedDialogVillageListCreate");			//alert('selectedDialogVillageListCreate length-->'+temp.length);
			for ( var i = 0; i < temp.options.length; i++) {
				if (tempVillList == "") {
					tempVillList = temp.options[i].value + ":"+ temp.options[i].text;
				} else {
					tempVillList = tempVillList + "," + temp.options[i].value+ ":" + temp.options[i].text;
				}
			}
			//alert(' &&&&& tempVillList-->'+tempVillList);
			/* Added by sushil on 06-05-2013 */
			if(document.getElementById('storedCombiValues').value == "" && document.getElementById('storedNewCombiValues').value == "") {
				document.getElementById("mergeVillageListId").value = "";
			}
			/* End */
			document.getElementById("mergeVillageListId").value = tempVillList;
			for ( var i = selObj.options.length - 1; i >= 0; i--) {
				if (selObj.options[i].selected == true) {
					selObj.remove(i, null);
				}
			}

			document.getElementById('subDistId').value = "";

			/* $("#dialog-merge-success").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					"Create Another" : function() {
						$(this).dialog("close");
					},
					Close : function() {
						$(this).dialog("close");
						//$("#dialogtwo").dialog("close");
					}
				}
			}); */
			
			$("#alertboxTitle").text("Success Message");
			$("#alertboxbody").text("<spring:message code='Error.dialog-merge-success' htmlEscape='true'></spring:message>");
			$('#alertbox').modal('show');	
		} catch (error) {
			/* $("#dialog-merge-failure").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
					}
				}
			}); */
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("<spring:message code='Error.dialog-merge-failure' htmlEscape='true'></spring:message>");
			$('#alertbox').modal('show');

		}
	}
	return false;
}

$.fx.speeds._default = 1000;
$("#dialogone").modal('show');
/* $(function() {
	$("#dialogone").dialog({
		autoOpen : false,
		show : "blind",
		hide : "explode",
		width : 750,
		height : 400
	}).bind('dialogclose', function(event) {
		 dwr.util.removeAllOptions('selectedDialogVillageList');
	 });
});
 */
/* $(function() {
	$("#dialogtwo").dialog({
		autoOpen : false,
		show : "blind",
		hide : "explode",
		width : 750,
		height : 350
	}).bind('dialogclose', function(event) {
		 dwr.util.removeAllOptions('selectedDialogVillageListCreate');
	 });
}); */

$(function() {
	$("#create")
			.button()
			.click(
					function() {				//alert('called create...');
						var selObj2 = document.getElementById('villageListNew');
					/* 	if (selObj2.options.length == 0) {
							$("#dialog-confirm-nodata-village").dialog({
								resizable : false,
								height : 140,
								modal : true,
								buttons : {
									Ok : function() {
										$(this).dialog("close");
									}
								}
							});
							return false;
						} */
						alert("Are You Confirm for creating Subdistrict");

						
						$('#dialogtwo').modal('show');
						var tmpSelobj = document.getElementById("dialogVillageListCreate");
						for ( var k = 0; k < tmpSelobj.options.length; k++) {
							tmpSelobj.options[k].selected = true;
						}
						for ( var k = tmpSelobj.options.length - 1; k >= 0; k--) {
							tmpSelobj.remove(k);
						}

						if (document.getElementById("storedCombiValues").value == "") {		//alert('inside if');
							for ( var i = 0; i < selObj2.options.length; i++) {
								selObj2.options[i].selected = true;
							}
							var backupVillageList = "";
							for ( var i = 0; i < selObj2.options.length; i++) {
								$("#dialogVillageListCreate").append('<option value="'+ selObj2.options[i].value+ '">'	+ selObj2.options[i].text+ '</option>');
								if (i == 0) {
									backupVillageList = selObj2.options[i].value+ ":" + selObj2.options[i].text;
								} else {
									backupVillageList = backupVillageList + ","+ selObj2.options[i].value + ":"+ selObj2.options[i].text;
								}
							}
							//alert("backupVillageList assigned into mergeVillageListId-->"+backupVillageList);
							//document.getElementById('mergeVillageListId').value = backupVillageList;
							var backupSubDistList = "";
							var selObj3 = document.getElementById('subDistrictListNew');
							for ( var j = 0; j < selObj3.options.length; j++) {
								// $("#subDistRadioIdCreate").append('<tr><td>'+selObj3.options[j].text+'</td><td><input
								// type="radio" name="subdistRadioName"
								// id="subdistRadioId"
								// value="'+selObj3.options[j].value+'"/></td></tr>');
								if (j == 0) {
									backupSubDistList = selObj3.options[j].value	+ ":" + selObj3.options[j].text;
								} else {
									backupSubDistList = backupSubDistList + ","+ selObj3.options[j].value + ":"+ selObj3.options[j].text;
								}
							}
							document.getElementById('mergeSubDistId').value = backupSubDistList;
						} else {			//alert('inside else dialogVillageList-->'+document	.getElementById("dialogVillageList").length);	alert('inside else mergeVillageListId-->'+document	.getElementById("mergeVillageListId").value);
							// ---------------------------------------------------------
							var temp = document	.getElementById("dialogVillageList");		// changed by sushil mergeVillageListId
							//var arraytemp = temp.split(",");
							for (var j = 0; j < temp.length; j++) {
								//var tempTwo = arraytemp[j].split(":");
								$("#dialogVillageListCreate").append('<option value="'+temp.options[j].value + '">'+temp.options[j].text+ '</option>');
							}
						}
						return false;
					}).next().button({
				text : false,
				icons : {
					primary : "ui-icon-triangle-1-s"
				}
			})
			.click(function() {
				return false;
				// alert("Could display a menu to select an action");
			}).parent().buttonset();
});

/* On click on Merge outer button */
$(function() {
	$("#merge1")	.button().click(
					function() {				
						var selObj2 = document.getElementById('villageListNew');
						var tmpnm = document.getElementById('districtNameInEn');
						if (tmpnm.value.length == 0) {
							$("#alertboxTitle").text("Error Message");
							$("#alertboxbody").text("<spring:message code='Error.dialog-error' htmlEscape='true'></spring:message>");
							$('#alertbox').modal('show');	
							/* $("#dialog-confirm-noname-created-district").dialog({
								resizable : false,
								height : 140,
								modal : true,
								buttons : {
									Ok : function() {
										$(this).dialog("close");
									}
								}
							}); */
							return false;
						} else {
							if (selObj2.options.length == 0) {
								$("#alertboxTitle").text("Error Message");
								$("#alertboxbody").text("<spring:message code='Error.dialog-confirm-nodata-village' htmlEscape='true'></spring:message>");
								$('#alertbox').modal('show');
								/* $("#dialog-confirm-nodata-village").dialog({
									resizable : false,
									height : 140,
									modal : true,
									buttons : {
										Ok : function() {
											$(this).dialog("close");
										}
									}
								}); */
								return false;
							}
						}
						var boolsubdisttfull = false;
						var subdisttCheck = document.getElementById('subDistrictListNew');
						for ( var sub = 0; sub < subdisttCheck.options.length; sub++) {
							if ((subdisttCheck.options[sub].value).match('FULL') == 'FULL') {			
								boolsubdisttfull = true;
								break;
							}
						}
						if(!boolsubdisttfull){
							$("#alertboxTitle").text("Message Dialog");
							$("#alertboxbody").text("Please select at least one full sub-district for merge villages.");
							$('#alertbox').modal('show');	
							/* $("#dialog-confirm-nodata-subdistt").dialog({
								resizable : false,
								height : 140,
								modal : true,
								buttons : {
									Ok : function() {
										$(this).dialog("close");
									}
								}
							}); */
							return false;
						}
						
						// code end for checking blank name in created new district by kamlesh
						$("#dialogone").modal('show');
						if (document.getElementById("storedNewCombiValues").value == "") {		//alert('inside if..');
							for ( var i = 0; i < selObj2.options.length; i++) {
								selObj2.options[i].selected = true;
							}
							var backupVillageList = "";
							var tmpSelobj = document.getElementById("dialogVillageList");
							for ( var k = 0; k < tmpSelobj.options.length; k++) {
								tmpSelobj.options[k].selected = true;
							}
							for ( var k = tmpSelobj.options.length - 1; k >= 0; k--) {
								tmpSelobj.remove(k);
							}
							
							if(selObj2.options.length > tmpSelobj.options.length) {	
								for ( var i = 0; i < selObj2.options.length; i++) {
									//alert('adding in to dialogVillageList-->'+selObj2.options[i].text);
									$("#dialogVillageList").append('<option value="'+ selObj2.options[i].value+ '">' + selObj2.options[i].text+ '</option>');
									if (i == 0) {
										backupVillageList = selObj2.options[i].value + ":" + selObj2.options[i].text;
									} else {
										backupVillageList = backupVillageList + "," + selObj2.options[i].value + ":" + selObj2.options[i].text;
									}
								}
							}
							//alert('%%%%%%%%%%%%%% final dialogVillageList-->'+document.getElementById("dialogVillageList").length);
							
							//if (document.getElementById('mergeVillageListId').value == "null")
								//alert('hi backupVillageList-->'+backupVillageList);
								//document.getElementById('mergeVillageListId').value = backupVillageList; // stored all contributing village to mergevillage
							// added by sushil on 30-04-2013
							resetValues(false);
							// End added by sushil on 30-04-2013
								
							var selObj3 = document.getElementById('subDistrictListNew');
							var backupSubDistList = "";
							var tmpSelobjRadio = document.getElementById("subDistRadioId");
						
							$(tmpSelobjRadio).empty();
							for ( var j = 0; j < selObj3.options.length; j++) {
								if ((selObj3.options[j].value).match('FULL') == 'FULL') {			
									//alert('creating radio from subDistrictListNew-->'+selObj3.options[j].text);
									$("#subDistRadioId").append('<tr><td>'+ selObj3.options[j].text	+ '</td><td><input type="radio" name="subdistRadio" id="subdistRadio" value="'+ selObj3.options[j].value	+ '"/></td></tr>');
									if (backupSubDistList == "") {
										backupSubDistList = selObj3.options[j].value + ":" + selObj3.options[j].text;
									} else {
										backupSubDistList = backupSubDistList + "," + selObj3.options[j].value + ":" + selObj3.options[j].text;
									}
								}
							}
							// --------------------------------------------------------
							var selObjFull = document.getElementById('subDistrictListNewFull'); // new one by kamlesh to show sub district list in merge
							for ( var j = 0; j < selObjFull.options.length; j++) {			//alert('creating radio from subDistrictListNewFull-->'+selObjFull.options[j].text);
								if(selObjFull.options[j].disabled)						
									$("#subDistRadioId").append('<tr><td>'+ selObjFull.options[j].text+ '</td><td><input type="radio" name="subdistRadio" id="subdistRadio" value="'	+ selObjFull.options[j].value+ '" disabled="'+selObjFull.options[j].disabled+'" /></td></tr>');
								else
									$("#subDistRadioId").append('<tr><td>'+ selObjFull.options[j].text+ '</td><td><input type="radio" name="subdistRadio" id="subdistRadio" value="'	+ selObjFull.options[j].value+ '"  /></td></tr>');
								if (backupSubDistList == "") {
									backupSubDistList = selObjFull.options[j].value + ":" + selObjFull.options[j].text;
								} else {
									backupSubDistList = backupSubDistList + "," + selObjFull.options[j].value + ":" + selObjFull.options[j].text;
								}
							}
							document.getElementById('mergeSubDistId').value = backupSubDistList;
						} else {
							//alert('inside else..');
							// Modified by sushil on 29-04-2013----------------------------------
							var temp = document.getElementById("mergeVillageListId").value;			//alert('inside else mergeVillageListId-->'+temp);
							var villFromCreateSubDistruct = document.getElementById("dialogVillageListCreate");		
							//var arraytemp = temp.split(",");
							dwr.util.removeAllOptions("dialogVillageList");
							if(villFromCreateSubDistruct != null) {
								var len = villFromCreateSubDistruct.options.length;
								for (var j = 0; j < len; j++) {
									$("#dialogVillageList").append('<option value="' + villFromCreateSubDistruct.options[j].value + '">' + villFromCreateSubDistruct.options[j].text+ '</option>');
								}
							}
							/* for ( var j = 0; j < arraytemp.length; j++) {
								var tempTwo = arraytemp[j].split(":");
								$("#dialogVillageList").append('<option value="' + tempTwo[0] + '">' + tempTwo[1] + '</option>');
							} */
							
							// added by sushil on 29-04-2013
							//resetValues(false);
							// End added by sushil on 29-04-2013
							var selObj3 = document.getElementById('subDistrictListNew');
							var backupSubDistList = "";
							$("#subDistRadioId").empty();
							for ( var j = 0; j < selObj3.options.length; j++) {
								if ((selObj3.options[j].value).match('FULL') == 'FULL') {		//alert('creating radio-->'+selObj3.options[j].text);
									$("#subDistRadioId").append('<tr><td>'+ selObj3.options[j].text+'</td><td><input type="radio" name="subdistRadio" id="subdistRadio" value="'+ selObj3.options[j].value	+ '"/></td></tr>');
									if (j == 0) {
										backupSubDistList = selObj3.options[j].value + ":" + selObj3.options[j].text;
									} else {
										backupSubDistList = backupSubDistList + "," + selObj3.options[j].value + ":" + selObj3.options[j].text;
									}
								}
							}
							document.getElementById('mergeSubDistId').value = backupSubDistList;
							//alert('inside else mergeSubDistId-->'+backupSubDistList);
						}
						//alert('at last mergeVillageListId-->'+document.getElementById('mergeVillageListId').value);
						return false;
					}).next().button({
				text : false,
				icons : {
					primary : "ui-icon-triangle-1-s"
				}
			}).click(function() {
				return false;
			}).parent().buttonset();
	
	
	$("#btnaddSubDistrictPart").click(function() {
		//if(checkthenAdd('ddSubdistrictforsubdistrict')){
			addItem('subDistrictListNew','ddSubdistrictforsubdistrict','PART',true);
		/*}else{
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("Can't select all subdistrict(s) of part district as full.");
			$('#alertbox').modal('show');	
			//showErrorDialog("dialog-error-subdistt-select-full");
		}*/
	});
	/* added by Vinay Yadav on 10-09-2013 */
	$("#btnaddSubDistrictFull").click(function() {
		if(checkthenAdd('ddSubdistrictforsubdistrict')){ 
			addItem('subDistrictListNew','ddSubdistrictforsubdistrict','FULL',true);
		 }else{
			//showErrorDialog("dialog-error-subdistt-select-full");
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("Can't select all subdistrict(s) of part district as full.");
			$('#alertbox').modal('show');	
		} 
	});
	
	
	
	$("#btnaddVillageFull").click(function() {
		if(checkthenAdd('villageList')){
			addItem('villageListNew','villageList','FULL',true)
		}else{
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("Can't select all village(s) of part sub-district as full.");
			$('#alertbox').modal('show');	
			//showErrorDialog("dialog-error-vill-select-full");
		}
	});
	/* added by Vinay Yadav ends here */
	
	$("#btnaddSubDistrictFullMarkPesa").click(function() {
		if(checkthenAddMarkPesa('ddSubdistrictforsubdistrict')){
			addItem('subDistrictListNew','ddSubdistrictforsubdistrict','FULL',true);
		}else{
			//showErrorDialog("dialog-error-subdistt-select-full");
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("Can't select all subdistrict(s) of part district as full.");
			$('#alertbox').modal('show');
		}
	});
	
	$("#btnaddVillageFullMarkPesa").click(function() {
		if(checkthenAddMarkPesa('villageList')){
			addItem('villageListNew','villageList','FULL',true)
		}else{
			//showErrorDialog("dialog-error-vill-select-full");
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("Can't select all village(s) of part sub-district as full.");
			$('#alertbox').modal('show');	
		}
	});
});

/* End of merge button function */

	function selectsubdieverythingfdfd(disabledFlag) {
		if(!isParseJson(disabledFlag)){
			dwr.util.removeAllOptions("ddSubdistrictforsubdistrict");
			dwr.util.removeAllOptions("subDistrictListNew");
			dwr.util.removeAllOptions("villageList");
			dwr.util.removeAllOptions("villageListNew");
			dwr.util.removeAllOptions("subDistrictListNewFull");

			var selObj = document.getElementById('ddDestDistrict');
			var idValuePart = "";
			var idValueFull = "";
			for ( var i = 0; i < selObj.options.length; i++) {
				selObj.options[i].selected = true;
				if ((selObj.options[i].value).match('PART') == 'PART') {
					if (idValuePart == "") {
						idValuePart = selObj.options[i].value;
					} else {
						idValuePart = idValuePart + "," + selObj.options[i].value;
					}
				}
				if ((selObj.options[i].value).match('FULL') == 'FULL') {
					if (idValueFull == "") {
						idValueFull = selObj.options[i].value;
					} else {
						idValueFull = idValueFull + "," + selObj.options[i].value;
					}
				}
				/* Added by sushil */
				if (idValuePart != "") {
					lgdDwrDistrictService.getSubdistrictListbyDistrictInDistrictForLocalBody(idValuePart, {
						callback : handleSubDistrict,
						errorHandler : handleSubDistError
					});
				}
				if (idValueFull != "") {
					lgdDwrDistrictService.getSubdistrictListbyDistrictInDistrictForLocalBody(idValueFull, {
						callback : handleSubDistrictFull,
						errorHandler : handleSubDistErrorFull
					});
				}
			}
			if (idValuePart == "") {
				$("#alertboxTitle").text("Message Dialog");
				$("#alertboxbody").text("<spring:message code='Error.dialog-district-button' htmlEscape='true'></spring:message>");
				$('#alertbox').modal('show');	
				/* $("#dialog-district-button").dialog({
					resizable : false,
					height : 140,
					modal : true,
					buttons : {
						Ok : function() {
							$(this).dialog("close");
						}
					}
				}); */
				return false;
			}
		}else
			{
			$("#cAlert").html("You Can't Get Subdistrict List from this level");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="Label.CREATEDISTRICT"></spring:message>',
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
			}

	}

	function handleSubDistrict(data) {
		dwr.util.removeAllOptions('ddSubdistrictforsubdistrict');
		var objData = [];
		
		var options = $("#ddSubdistrictforsubdistrict");
	
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] ');
				options.append(option);
			} else {
				$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish + ' [ ' + obj.districtNameEnglish + ' ] ');
				options.append(option);
			}
		});
		
		
		
	/* 	if (data.length > 0) {
			for ( var i = 0; i < data.length; i++) {
				objData[i] = {
					name : data[i].subdistrictNameEnglish + ' [ ' + data[i].districtNameEnglish + ' ] ',
					code : data[i].subdistrictCode
				};
			}
			dwr.util.addOptions('ddSubdistrictforsubdistrict', objData, 'code', 'name');
		} */
	}

	function handleSubDistError() {
		$("#dialog-confirm-nodata").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	}

	function handleSubDistrictFull(data) {
		var fieldId = 'subDistrictListNewFull';
		dwr.util.removeAllOptions(fieldId);
		
		
		var options = $("#subDistrictListNewFull");
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if (obj.operation_state == 'F') {
				$(option).attr('disabled', 'disabled');
				$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
				options.append(option);
			} else {
				$(option).val(obj.subdistrictCode).text(obj.subdistrictNameEnglish);
				options.append(option);
			}
		});
		
		
		//dwr.util.addOptions(fieldId, data, valueText, nameText);

	}

	function handleSubDistErrorFull() {

		$("#dialog-confirm-nodata").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});

	}

	function addItem(list1, list2, val, doAddVal) {  //1 part

		if (document.getElementById(list2).selectedIndex >= 0) {
			for ( var j = 0; j < document.getElementById(list2).options.length; j++)
				if (document.getElementById(list2).options[j].selected == true)
					if (doAddVal) {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value + val + ">" + document.getElementById(list2)[j].text
										+ " (" + val + ")</option>");
						
						
					} else {
						$('#' + list1).append(
								"<option value=" + document.getElementById(list2)[j].value-6 + " >" + document.getElementById(list2)[j].text
										+ "</option>");
					}
			
			
			
			removeSelectedValue(list2);
		}
	}

	function selectFinal() {
		var selObj = document.getElementById('subDistrictListNew');
		var selObj1 = document.getElementById('ddDestDistrict');
		var selObj2 = document.getElementById('villageListNew');
		var selObj3 = document.getElementById('surveyListNew');

		var subDistrict = "";
		for ( var i = 0; i < selObj.options.length; i++) {
			selObj.options[i].selected = true;
			subDistrict += selObj.options[i].value + ",";
		}
		for ( var j = 0; j < selObj1.options.length; j++) {
			selObj1.options[j].selected = true;
			subDistrict += selObj1.options[j].value + ",";
		}
		for ( var j = 0; j < selObj2.options.length; j++) {
			selObj2.options[j].selected = true;
			subDistrict += selObj2.options[j].value + ",";
		}
		for ( var j = 0; j < selObj3.options.length; j++) {
			selObj3.options[j].selected = true;
			subDistrict += selObj3.options[j].value + ",";
		}
		hideGISComponentOnLoad();
		/*
		 * for (var k = 0; k < selObj4.options.length; k++) {
		 * selObj4.options[k].selected=true; subDistrict
		 * +=selObj4.options[j].value+","; }
		 */
	}

	$(document)
			.ready(
					function() {
						$("#form1")
								.validate(
										{
											rules : {
												districtNameInEn : {
													required : true,
													nameFormat : true
												},
												districtNameInLcl : {
													nameFormatLocal : true
												},
												districtAliasInEn : {
													nameFormat : true
												},
												districtAliasInLcl : {
													nameFormatLocal : true
												},
												shortName : {
													shortName : true
												},
												census2011Code : {
													onlyNumberSp : true
												},
											},
											messages : {
												districtNameInEn : {
													required : "<font color='red'><br><spring:message code='Error.dialog-confirm-dist-name'/></font>",
													nameFormat : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>"

												},
												districtNameInLcl : {
													nameFormatLocal : "<font color='red'><br><spring:message code='error.LocalName' text='Please enter in local language only'/></font>"

												},
												districtAliasInEn : {
													nameFormat : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>"

												},
												districtAliasInLcl : {
													nameFormatLocal : "<font color='red'><br><spring:message code='error.LocalName' text='Please enter in local language only'/></font>"

												},
												shortName : {
													shortName : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>"

												},
												census2011Code : {
													onlyNumberSp : "<font color='red'><br><spring:message code='error.valid.COMMONNUMERICREQUIRED'/></font>"

												}
											}
										});
					});

	function chkValid(obj) {
		var flag;
		if (obj.value != null) {
			flag = $("#form1").validate().form();
			if (flag)
				return true;
			else
				return false;
		}
	}

	// added by sushil on 30-04-2013
	function resetValues(optionOnly) {
		if(optionOnly) {
			dwr.util.removeAllOptions("villageList");
			dwr.util.removeAllOptions("villageListNew");
		} else {
			var tbl = document.getElementById("subDistRadioId"); 
			var lastRow = tbl.rows.length;	
			if (lastRow > 1) { //alert('deleting radio..');
			  	tbl.deleteRow(lastRow - 1);
			}
		}
	}
	
	function showErrorDialog(errorTitle) {
		$("#"+errorTitle).dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Cancel : function() {
					$(this).dialog("close");
				}
			}
		});
	} 
	
	function checkSelectedVillage() {
		var selObj = document.getElementById('selectedDialogVillageList');
		for ( var i = 0; i < selObj.length; i++) {
			selObj.options[i].selected = true;
		}
		if (selObj.options.length == 0) {
			$("#alertboxTitle").text("Error Message");
			$("#alertboxbody").text("<spring:message code='Error.dialog-error1' htmlEscape='true'></spring:message>");
			$('#alertbox').modal('show');	
			//showErrorDialog("dialog-error1");
			return false;
		} else {
			return true;
		}
	}
	
	
	// added by Vinay Yadav on 10-09-2013
	function checkthenAdd(inputId) {
		
		if(districtCount>0){
			return true;
		}else{
			var selObj = document.getElementById(inputId);
			var temp = "";
			var avacount = 0;
			var selcount = 0;
			var val = "";
			var check = true;
			for ( var i = 0; i < selObj.options.length; i++) {
				avacount = 0;
				selcount = 0;
				if (selObj.options[i].selected) {
					temp = selObj.options[i].text;
					var pos = temp.indexOf("[");
					temp = temp.substr(pos, temp.length);
					for ( var j = 0; j < selObj.options.length; j++) {
						val = selObj.options[j].text;
						if (val.indexOf(temp) > -1)
							avacount++;
					}
					for ( var k = 0; k < selObj.options.length; k++) {
						if (selObj.options[k].selected) {
							val = selObj.options[k].text;
							if (val.indexOf(temp) > -1)
								selcount++;
						}
					}
					if (selcount == avacount) {
						check = false;
						break;
					}
				}
			}
			return check;
		}
		
		
	}
	
	
	
	function markPesaSubmit(val1) {		
		formSubmitPreviewPESA();
	}
	
	
function validateMarkPesaLandRegion(){
	var error = 0;
	var tmp = 0;
	var tmpsubd = 0;
	
	
	if (document.getElementById('ddDestDistrict').options.length == 0) {
		error = 1;
		$("#dialog-error5").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	} else {
		for ( var i = 0; i < document.getElementById('ddDestDistrict').options.length; i++) {
			if(document.getElementById('ddDestDistrict').options[i].selected){
				document.getElementById('ddDestDistrict').options[i].selected = true;
				if (document.getElementById('ddDestDistrict').options[i].value.match('FULL') == "FULL")
					tmp = 0;  //full
				else {
					tmp = 1;   //pesa
					break;
				}
			}else{
				tmp = 2;   //N
			}
		}
		if (document.getElementById('ddDestDistrict').options.length != 0 && tmp == 0) {

			document.getElementById('statusDist').value = 1;
			/* error = 0; */
		}else if(document.getElementById('ddDestDistrict').options.length != 0 && tmp == 2){
			error = 1;
			$("#dialog-error5").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
					}
				}
			});
		} else {   //district pesa
			if (tmp == 1) {  //pesa
				if (document.getElementById('subDistrictListNew').options.length == 0) {  //selected subdistrict blank
					error = 1;
					
					$("#dialog-error6").dialog({
						resizable : false,
						height : 140,
						modal : true,
						buttons : {
							Ok : function() {
								$(this).dialog("close");
							}
						}
					});
				}else {
					for ( var i = 0; i < document.getElementById('subDistrictListNew').options.length; i++) { //selected subdistrict not blank
						if(document.getElementById('subDistrictListNew').options[i].selected){
						 document.getElementById('subDistrictListNew').options[i].selected = true;
						if (document.getElementById('subDistrictListNew').options[i].value.match('FULL') == "FULL")
							tmpsubd = 0;  //selected sub district Full
						else {
							tmpsubd = 1;  // selected sub district pesa
							break;
						}
						}else{
							tmpsubd = 2;  // N
						}
					}
					
				   if (document.getElementById('subDistrictListNew').options.length != 0 && tmpsubd == 0) {  // dist pesa selected sub district Full
							document.getElementById('statusDist').value = 1;
							error = 0;
						}
				   else if(document.getElementById('subDistrictListNew').options.length != 0 && tmpsubd == 2){
							error=1;
							$("#dialog-error6").dialog({
								resizable : false,
								height : 140,
								modal : true,
								buttons : {
									Ok : function() {
										$(this).dialog("close");
									}
								}
							});
					} else {  /* added by anju on 25/03/2015 */
						if (tmpsubd == 1) {
							
							if(document.getElementById('ddSubdistrictforsubdistrict').options.length == 0){
								
								error=1;
								$("#dialog-error10").dialog({
									resizable : false,
									height : 140,
									modal : true,
									buttons : {
										Ok : function() {
											$(this).dialog("close");
										}
									}
								});
								
							} 	/* ended by anju */
							
							else if (document.getElementById('villageListNew').options.length == 0) {
								error = 1;
								
								document.getElementById('statusDist').value = 1;

								$("#dialog-error8").dialog({
									resizable : false,
									height : 140,
									modal : true,
									buttons : {
										Ok : function() {
											$(this).dialog("close");
										}
									}
								});
							}  
							/* Added by Anju start */
							else if (document.getElementById('villageListNew').options.length != 0) {
								
								if (document.getElementById('villageList').options.length == 0)
								{   alert('we cant do this');	
								error = 1;
								
								document.getElementById('statusDist').value = 1;

								$("#dialog-error11").dialog({
									resizable : false,
									height : 140,
									modal : true,
									buttons : {
										Ok : function() {
											$(this).dialog("close");
										}
									}
								});
								}
								}  
							/* ended by Anju end */
							else {
								if (document.getElementById('villageListNew').options.length > 0) {
									
									var len1 = document.getElementById('villageListNew').length;
									var len2 = 0;
									if (document.getElementById('storedCombiValues').value == ""
											&& document.getElementById('storedNewCombiValues').value != "") {
										var obj = document.getElementById('storedNewCombiValues').value.split(",");
										if (obj.length > 0) {
											for ( var i = 0; i < obj.length; i++) {
												if (obj[i].indexOf(":") >= 0) {
													var substr = obj[i].split(":");
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + obj.length;
												}
											}
										}
									} else if (document.getElementById('storedNewCombiValues').value == ""
											&& document.getElementById('storedCombiValues').value != "") {
										var obj = document.getElementById('storedCombiValues').value.split(",");
										if (obj.length > 0) {
											for ( var i = 0; i < obj.length; i++) {
												if (obj[i].indexOf(":") >= 0) {
													var substr = obj[i].split(":");
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + obj.length;
												}
											}
										}
									} else {
										var m = document.getElementById('storedNewCombiValues').value.split(",");
										var n = document.getElementById('storedCombiValues').value.split(",");
										
										if (m.length > 0) {
											for ( var i = 0; i < m.length; i++) {
												if (m[i].indexOf(":") >= 0) {
													var substr = m[i].split(":");
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + m.length;
												}
											}
										}

										if (n.length > 0) {
											for ( var i = 0; i < n.length; i++) {
												if (n[i].indexOf(":") >= 0) {
													var substr = n[i].split(":");
													len2 = len2 + substr.length;
												} else {
													len2 = len2 + n.length;
												}
											}
										}
									}
								}
							}
						}
					}

				}
			}
		}  //dist pesa end

	}

	/*
	 */
	if (error == 0) {
		return true;
	}
	if (error == 1) {
		return false;
	}

}
	function formSubmitPreviewPESA() {
		
		
		var selObjDist = document.getElementById('ddDestDistrict');
		for ( var i = 0; i < selObjDist.options.length; i++) {
			if(!selObjDist.options[i].disabled){
				selObjDist.options[i].selected = true;
			}
		}
		var selObjSubDist = document.getElementById('subDistrictListNew');
		for ( var i = 0; i < selObjSubDist.options.length; i++) {
			if(!selObjSubDist.options[i].disabled){
				selObjSubDist.options[i].selected = true;
			}
		}
		var selObjvillageList = document.getElementById('villageListNew');
		for ( var i = 0; i < selObjvillageList.options.length; i++) {
			if(!selObjvillageList.options[i].disabled){
				selObjvillageList.options[i].selected = true;
			}
		}
		if (validateMarkPesaLandRegion()) {
				document.forms['form1'].submit();
		}
		
	}
	
/* For moving single selected option */
function removeOnedistrictOptionForMarkPesa(list1, list2, doRemoveVal) {
	    /*added by kirandeep for modified use case of is pesa landregion */
	    var distOrsubdistCodes="";
		if (document.getElementById(list1).selectedIndex >= 0) {
			for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
				if(!document.getElementById(list1).options[j].disabled){
					if (document.getElementById(list1).options[j].selected == true) {
						if (doRemoveVal) {
					var string=document.getElementById(list1)[j].text;
					var text=document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length-6);
					var dlccode = document.getElementById(list1)[j].value.substr(0,document.getElementById(list1)[j].value.length-4);					
					var s = document.getElementById(list1)[j].value;					
					if(s.indexOf("PART") > -1){
						distOrsubdistCodes =dlccode + ',' + distOrsubdistCodes ;
					}
				
				
	/*  Changed by Anju Gupta  on 20/03/2015	*/
	
							$('#' + list2).append(
									"<option value="
											+ document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 4)
											+ " >"
											+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
											+ "</option>");

						} else {
							$('#' + list2).append(
									"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text
											+ "</option>");
						}
					}
				}
			}

			//added by Anju Gupta on 25/03/2015

			if (list1 == 'ddDestDistrict') {
				//added by kirandeep for modified use case of is pesa landregion
				if(distOrsubdistCodes.length > 0) {
					var length= distOrsubdistCodes.length;
					var distOrsubdistCodes = distOrsubdistCodes.substring(0, length-1);
					
					//alert(distOrsubdistCodes);
					lgdDwrDistrictService.getHeirarchyByParentCodes(distOrsubdistCodes,'D','N',{
						callback : handledistrictBackSuccess,
						errorHandler : handleSubDistErrorFull
						
					});
				}
				
				
				removeSelectedValueForMarkPesaOneByOne(list1, text);

			}

			else if (list1 == 'subDistrictListNew') {
				{
				//added by kirandeep for modified use case of is pesa landregion
				   if(distOrsubdistCodes.length > 0) {
					   var length= distOrsubdistCodes.length;
						var distOrsubdistCodes = distOrsubdistCodes.substring(0, length-1);
						
						//alert(distOrsubdistCodes);
						lgdDwrDistrictService.getHeirarchyByParentCodes(distOrsubdistCodes,'T','N',{
							callback : handleSubdistrictBackSuccess,
							errorHandler : handleSubDistErrorFull
							
						});
				   }
					
					var text1 = text.substr(0, text.indexOf(' '));
					removeSelectedValueForMarkPesaOneByOneforsubdistrict(list1, text1);
				}

			} else if (list1 == 'villageListNew') {
				removeSelectedValueForMarkPesaOneByOne(list1, text);

			}
			//end by Anju Gupta

		}
	}

	/* For moving all option */
	function removeAllListForMarkPesa(list1, list2, doRemoveVal) {
		selectAllForMarkPesa(list1);
		if (document.getElementById(list1).selectedIndex >= 0) {
			for ( var j = 0; j < document.getElementById(list1).options.length; j++) {
				if (!document.getElementById(list1).options[j].disabled) {
					if (document.getElementById(list1).options[j].selected == true) {

						if (doRemoveVal) {
							$('#' + list2).append(
									"<option value="
											+ document.getElementById(list1)[j].value.substr(0, document.getElementById(list1)[j].value.length - 4)
											+ " >"
											+ document.getElementById(list1)[j].text.substr(0, document.getElementById(list1)[j].text.length - 6)
											+ "</option>");
						} else {
							$('#' + list2).append(
									"<option value=" + document.getElementById(list1)[j].text + " >" + document.getElementById(list1)[j].text
											+ "</option>");
						}
					}
				}
			}
			removeSelectedValueForMarkPesaAll(list1);
		}
	}

	function removeSelectedValueForMarkPesaOneByOne(val, text) {
		var elSel = document.getElementById(val);

		for ( var i = elSel.length - 1; i >= 0; i--) {
			if (elSel.options[i].selected) {
				if (!elSel.options[i].disabled)
					elSel.remove(i);
			}
		}
		if (val == 'ddDestDistrict') {

			for ( var j = 0; j < document.getElementById('subDistrictListNew').options.length; j++) {
				var text2 = document.getElementById('subDistrictListNew')[j].text.trim();

				if (text2.search(text.trim()) != -1) {

					var elSei = document.getElementById('subDistrictListNew')[j];
					elSei.disabled = true;

				}

			}
			var elSei = document.getElementById('subDistrictListNew');
			for ( var i = elSei.length - 1; i >= 0; i--) {

				if (elSei.options[i].disabled)
					elSei.remove(i);

			}

		}
	}
	function removeSelectedValueForMarkPesaOneByOneforsubdistrict(val, text) {

		var elSel = document.getElementById(val);

		for ( var i = elSel.length - 1; i >= 0; i--) {
			if (elSel.options[i].selected) {
				if (!elSel.options[i].disabled)
					elSel.remove(i);
			}
		}

		if (val == 'subDistrictListNew') {

			for ( var j = 0; j < document.getElementById('villageListNew').options.length; j++) {
				var text2 = document.getElementById('villageListNew')[j].text.trim();

				if (text2.search(text.trim()) != -1) {

					var elSei = document.getElementById('villageListNew')[j];
					elSei.disabled = true;

				}

			}
			var elSei = document.getElementById('villageListNew');
			for ( var i = elSei.length - 1; i >= 0; i--) {

				if (elSei.options[i].disabled)
					elSei.remove(i);

			}

		}

	}

	/*
	 * added by Anju on 26/03/2015 
	 *
	 */
	function removeSelectedValueForMarkPesaAll(val) {
		var elSel = document.getElementById(val);
		for ( var i = elSel.length - 1; i >= 0; i--) {
			if (elSel.options[i].selected) {
				if (!elSel.options[i].disabled)
					elSel.remove(i);
			}
		}
		if (val == 'ddDestDistrict') {
			dwr.util.removeAllOptions('ddSubdistrictforsubdistrict');
			dwr.util.removeAllOptions('subDistrictListNew');
			dwr.util.removeAllOptions('villageList');
			dwr.util.removeAllOptions('villageListNew');
		}
		if (val == 'subDistrictListNew') {
			dwr.util.removeAllOptions('villageList');
			dwr.util.removeAllOptions('villageListNew');
		}

	}

	/* End Anju Gupta*/
	function selectAllForMarkPesa(obj) {
		var selObj = document.getElementById(obj);
		for ( var i = 0; i < selObj.options.length; i++) {
			if (!selObj.options[i].disabled)
				selObj.options[i].selected = true;
		}
	}

	function checkthenAddMarkPesa(inputId) {
		var selObj = document.getElementById(inputId);
		var temp = "";
		var temp1 = "";
		var avacount = 0;
		var selcount = 0;
		var val = "";
		var valother = "";
		var check = true;
		for ( var i = 0; i < selObj.options.length; i++) {
			avacount = 0;
			selcount = 0;
			if (selObj.options[i].selected) {
				temp = selObj.options[i].text;
				var pos = temp.indexOf("[");
				temp = temp.substr(pos, temp.length);
				var selObj1 = document.getElementById("subDistrictListNew");
				for ( var j = 0; j < selObj.options.length; j++) {
					val = selObj.options[j].text;
					if (val.indexOf(temp) > -1)
						avacount++;
				}
				for ( var m = 0; m < selObj1.options.length; m++) {
					temp1 = selObj1.options[m].text;
					var pos1 = temp1.indexOf("[");
					temp1 = temp1.substr(pos1, temp1.length);
					valother = selObj1.options[m].text;
					if (valother.indexOf(temp) > -1 && temp1.indexOf(temp) > -1 && valother.indexOf("(PART)") > -1) {
						avacount = 0;
						break;
					}
				}
				for ( var k = 0; k < selObj.options.length; k++) {
					if (selObj.options[k].selected) {
						val = selObj.options[k].text;
						if (val.indexOf(temp) > -1)
							selcount++;
					}
				}
				if (selcount == avacount) {
					check = false;
					break;
				}
			}
		}
		return check;
	}
	
	/* added by Vinay Yadav ends here */
	
	//added by kirandeep for modified use case of is pesa landregion Starts
	function handleSubdistrictBackSuccess(data) {
				
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if(obj.type == 'V'){
				if (obj.ispesa == 'F') {					
					$("#villageListNew option[value='"+obj.specificCode+'FULL'+"']").remove();
					//$(option).attr('disabled', 'disabled');
					//$(option).val(obj.districtCode).text(obj.districtNameEnglish);
					//options.append(option);
					
					
				} else if(obj.ispesa =='P') {
					$("#villageListNew option[value='"+obj.specificCode+'PART'+"']").remove();
					//$(option).val(obj.districtCode).text(obj.districtNameEnglish);
					//options.append(option);
				}else{
					$("#villageList option[value='"+obj.specificCode+"']").remove();
				}
			}			
		});
		
	}
	
	function handledistrictBackSuccess(data) {
		
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if(obj.type == 'T'){
				if (obj.ispesa == 'F') {					
					$("#subDistrictListNew option[value='"+obj.specificCode+'FULL'+"']").remove();
					$("#ddSubdistrictforsubdistrict option[value='"+obj.specificCode+'FULL'+"']").remove();
					$("#ddSubdistrictforsubdistrict option[value='"+obj.specificCode+"']").remove();
					
					
				} else if(obj.ispesa =='P') {
					$("#subDistrictListNew option[value='"+obj.specificCode+'PART'+"']").remove();
					$("#ddSubdistrictforsubdistrict option[value='"+obj.specificCode+'PART'+"']").remove();
					$("#ddSubdistrictforsubdistrict option[value='"+obj.specificCode+"']").remove();					
					
				}else{
					$("#ddSubdistrictforsubdistrict option[value='"+obj.specificCode+"']").remove();
					$("#subDistrictListNew option[value='"+obj.specificCode+"']").remove();
					$("#subDistrictListNew option[value='"+obj.specificCode+'FULL'+"']").remove();
					$("#subDistrictListNew option[value='"+obj.specificCode+'PART'+"']").remove();
					
				}
			}
			
			if(obj.type == 'V'){
				if (obj.ispesa == 'F') {					
					$("#villageListNew option[value='"+obj.specificCode+'FULL'+"']").remove();
					$("#villageList option[value='"+obj.specificCode+'FULL'+"']").remove();
					$("#villageList option[value='"+obj.specificCode+"']").remove();
					//$(option).attr('disabled', 'disabled');
					//$(option).val(obj.districtCode).text(obj.districtNameEnglish);
					//options.append(option);
					
					
				} else if(obj.ispesa =='P') {
					$("#villageListNew option[value='"+obj.specificCode+'PART'+"']").remove();
					$("#villageList option[value='"+obj.specificCode+'PART'+"']").remove();
					$("#villageList option[value='"+obj.specificCode+"']").remove();
					
				}else{
					$("#villageList option[value='"+obj.specificCode+"']").remove();
					$("#villageListNew option[value='"+obj.specificCode+"']").remove();
					$("#villageListNew option[value='"+obj.specificCode+'FULL'+"']").remove();
					$("#villageListNew option[value='"+obj.specificCode+'PART'+"']").remove();
				}
			}
			
		});
		
	}
	
	
	function handledistrictOnLoadBackSuccess(data) {
		var options1 = $("#ddSubdistrictforsubdistrict");
		var options2 = $("#villageList");
		var options3 = $("#subDistrictListNew");
		var options4 = $("#villageListNew");
		
		
		$.each(data, function(key, obj) {
			var option = $("<option/>");
			if(obj.type == 'T'){
				if (obj.ispesa == 'N') {					
					//$("#subDistrictListNew option[value='"+obj.specificCode+'FULL'+"']").remove();
					$(option).val(obj.specificCode).text(obj.parentName);
					options1.append(option);
					
				}
				if (obj.ispesa == 'F') {					
					$(option).val(obj.specificCode+'FULL').text(obj.parentName+'(FULL)');
					options3.append(option);				
					
				}
				if (obj.ispesa == 'P') {					
					$(option).val(obj.specificCode+'PART').text(obj.parentName+'(PART)');
					options3.append(option);
				
				}
			}
			
			if(obj.type == 'V'){
				if (obj.ispesa == 'N') {					
					$(option).val(obj.specificCode).text(obj.parentName);
					options2.append(option);			
					
				} 				
				if (obj.ispesa == 'F') {					
					$(option).val(obj.specificCode+'FULL').text(obj.parentName+'(FULL)');
					options4.append(option);
										
				}
				if (obj.ispesa == 'P') {
					$(option).val(obj.specificCode+'PART').text(obj.parentName+'(PART)');
					options4.append(option);
					
				}
				
			}
			
		});
			
	}
	
	//added by kirandeep for modified use case of is pesa landregion ends
</script>


