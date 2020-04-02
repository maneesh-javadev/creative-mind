
//on submit form then these are nessessary to include all combobox ids
function selectall()
{
	var selObj=document.getElementById('ddDestDistLocalGovtBody');	
	var selObj2=document.getElementById('ddSubDistDestLocalGovtBody');
	var selObj3=document.getElementById('ddDestLocalGovtBody');
	var selObj4=document.getElementById('ddSourceLocalGovtBodyMain');
	var selObj5=document.getElementById('ddSourceLocalGovtBodyUnmapped');
	var selObj6=document.getElementById('ddSourceLocalGovtBodyUnmapped');
	var selObj7=document.getElementById('localGovtBodyListMain');	
	var selObj8=document.getElementById('localBodyNameEnglishListUnMapped');	
	var selObj9=document.getElementById('ddCoveredVillageList');	
	var selObj10=document.getElementById('localGovtBodyVillageList');	
	var selObj11=document.getElementById('localDistGovtBodyListMain');	
	var selObj12=document.getElementById('localSubDistGovtBodyListMain');
	var selObj13=document.getElementById('ddSourceLocalBody');
	var selObj14=document.getElementById('ddSourceLocalGovtBody');
	
	for (var i = 0; i < selObj.options.length; i++) {
		     selObj.options[i].selected=true;
	 }
	for (l = 0; l < selObj2.options.length; l++) {
		     selObj2.options[l].selected=true;
	 }
	for (m = 0; m < selObj3.options.length; m++) {
	     selObj3.options[m].selected=true;
	}
	
	for (j = 0; j < selObj4.options.length; j++) {
	     selObj4.options[j].selected=true;
	}
	for (a = 0; a < selObj5.options.length; a++) {
	     selObj5.options[a].selected=true;
	}
	for (b = 0;b < selObj6.options.length; b++) {
	     selObj6.options[b].selected=true;
	}
	for (c = 0; c < selObj7.options.length; c++) {
	     selObj7.options[c].selected=true;
	}
	
	for (d = 0; d < selObj8.options.length; d++) {
	     selObj8.options[d].selected=true;
	}
	
	for (e = 0;e< selObj9.options.length;e++) {
		
	     selObj9.options[e].selected=true;
	}
	
	for (f = 0; f < selObj10.options.length; f++) {
	     selObj10.options[f].selected=true;
	}
	for (g = 0; g < selObj11.options.length; g++) {
	     selObj11.options[g].selected=true;
	}
	for (h = 0; h < selObj12.options.length; h++) {
	     selObj12.options[h].selected=true;
	}
	for (n = 0; n < selObj13.options.length; n++) {
	     selObj13.options[n].selected=true;
	}
	for (o = 0; o < selObj14.options.length; o++) {
	     selObj14.options[o].selected=true;
	}
	
}

function vaildateLocalBodyEnglishName()
{
	if(document.getElementById('localBodyNameInEn').value=="")
	{
		alert("Please enter the name of new Govt. Local Body in English");
		return false;
	}
	return true;
	}




function show_msgEng(Field_msg)
{
	var hint='#'+Field_msg+"_msg";
	var error='#'+Field_msg+"_error";
	$("#"+Field_msg).removeClass("error_fld");
	$("#"+Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();
		
}

function validateLocalNameInEng(){
	
	
	if(document.getElementById('localBodyNameInEn').value=="")
	{			
		document.getElementById("localBodyNameInEn_error").innerHTML="Local Body Name (in English) is required.";
		$("#localBodyNameInEn_error").show();
		$("#localBodyNameInEn_msg").hide();
		$("#localBodyNameInEn").addClass("error_fld");
		$("#localBodyNameInEn" +"").addClass("error_msg");
		return false;

	}
}

// Validate form values



function validateAll() {
	var msg=null;
	selectall();

	
	
	
	if(!validateLocalBodyType()) {
		
		if(msg!=null)
		{
			msg=msg+"Please select type of Panchayat"+ '\n';
		}
	else
		{ 				
			msg="Please select type of Panchayat"+ '\n';				
		}
	}
	
	if(!validateLocalBodyParent())
		{
		if(msg!=null)
		{
			msg=msg+"Please select a parent local body"+ '\n';
		}
		else
		{ 				
			msg="Please select a parent local body"+ '\n';				
		}
		
		}
	
	
	if (!validateLocalBodyParentVillage()) {
		
		if(msg!=null)
		{
			msg=msg+"Please select a village panchayat's parent local body"+'\n';
		}
		else
			{
				msg="Please select a village panchayat's parent local body"+'\n';
			}
	}
	
	
		if(!validateExistingLocalBody())
			{
			if(msg!=null)
			{
				msg=msg+"Please select atleast Existing local body or Unmapped/Partially localbody"+ '\n';
			}
		else
			{ 				
				msg="Please select atleast Existing local body or Unmapped/Partially localbody"+ '\n';	
			}
			
			}
		
		if(!validateLocalBodyDistrict())
			{
			
			if(msg!=null)
			{
				msg=msg+"Please select minimum two FULL or one PART or one FULL and one PART"+ '\n';
			}
		else
			{ 				
				msg="Please select minimum two FULL or one PART or one FULL and one PART"+ '\n';				
			}
			}
		
	if(msg!=null)
		{
		alert(msg);
		return false;
	}
	else{
		document.forms['localGovtBodyForm'].submit();
		return true;
		
	}
	
return false;
}

// Local Body Type
function validateLocalBodyType()
{
	
	var ddsource= document.getElementById('tierSetupCode');	

	// alert("ddsource.selectedIndex--*****--"+ddsource.selectedIndex);
	if(ddsource.selectedIndex == 0)
		{		
		$("#tierSetupCode_error").show();
		return false;
		}
	$("#tierSetupCode_error").hide();
	return true;
}
// End here Local Body Type Here
// For District local body destination validation
function validateLocalBodyDistrict()
{
	
	var ddsource1= document.getElementById('tierSetupCode');
	var ddsource = ddsource1.options[ddsource1.selectedIndex].value; 
	var temp = ddsource.split(":");
	// alert("Temp---2-"+temp[0]);
	var id1=temp[0];
	var id2=temp[1];
	var ddDestDistLocalGovtBody= document.getElementById('ddDestDistLocalGovtBody');
	//alert("ddDestDistLocalGovtBody.selectedIndex----"+ddDestDistLocalGovtBody.selectedIndex);
	if(id2=="D")
		{	
		if(ddDestDistLocalGovtBody.selectedIndex >1)
			{
		$("#ddDestDistLocalGovtBody_error").show();
		return false;
		}
	}
	$("#ddDestDistLocalGovtBody_error").hide();
	return true;
}
// End here Local body District Panchayat

//Validation for Existing localBody
function validateExistingLocalBody()
{
	var culb= document.getElementById('chkculb');
	var unmappedLocalBody= document.getElementById('unmappedLocalBody');
	//alert("unmappedLocalBody"+unmappedLocalBody.checked);
	var ddsource= document.getElementById('tierSetupCode');	
	// alert("unmappedLocalBody------///---"+unmappedLocalBody.checked);
	if(ddsource.selectedIndex > 0){
	if (culb.checked==false && unmappedLocalBody.checked==false)
		{
		$("#chkculb_error").show();
		return false;		
		}
	}
	$("#chkculb_error").hide();	
	return true;
}
// End here Existing LocalBody
// Intermediate Body Start here
function validateLocalBodyParent()
{ //ddSourceLocalBody_error
	var ddsource1= document.getElementById('tierSetupCode');
	var ddsource = ddsource1.options[ddsource1.selectedIndex].value; 
	
	var temp = ddsource.split(":");
	// alert("Temp---2-"+temp[0]);
	var id1=temp[0];
	var id2=temp[1];
	//alert("ddsource--P--"+ddsource);
	var ddSourceParentSr1=document.getElementById('ddSourceLocalBody');
	var ddSourceParentSr = ddSourceParentSr1.options[ddSourceParentSr1.selectedIndex].value; 
	//alert("ddSourceParentSr--1-"+ddSourceParentSr);
	
	if(id2=="I")
		{
		if(ddSourceParentSr ==-1)
		{		
			$("#ddSourceLocalBody_error").show();
			return false;
		}
		
		}
	
	$("#ddSourceLocalBody_error").hide();
	return true;
		
}

//End here Intermediate

// For Village Panchayat's Paraent validation

function validateLocalBodyParentVillage()
{ 
	
	var ddsource1= document.getElementById('tierSetupCode');
	var ddsource = ddsource1.options[ddsource1.selectedIndex].value; 
	
	var temp = ddsource.split(":");
	// alert("Temp---2-"+temp[0]);
	var id1=temp[0];
	var id2=temp[1];
	// alert("ddsource--P--"+ddsource);
	var ddSourceParentSr1=document.getElementById('ddSourceLocalBody');
	var ddSourceParentSr = ddSourceParentSr1.options[ddSourceParentSr1.selectedIndex].value; 
	// alert("ddSourceParentSr--1-"+ddSourceParentSr);
	

	var localGovtBodyListMain1=document.getElementById('localGovtBodyListMain');
	var localGovtBodyListMain = localGovtBodyListMain1.options[localGovtBodyListMain1.selectedIndex].value; 
	if(localGovtBodyListMain==undefined)
		{
		
		localGovtBodyListMain=0;
		}
		
	
	if(id2=="V")
		{
		// alert("in V but check intermediate");			
			if(!validateInterMediatePanchayat(id2, ddSourceParentSr))
				{
			$("#localGovtBodyListMain_error").show();
			return false;
				}else if(localGovtBodyListMain==0){
				//	alert("Village Panchayat-****************-11-"+ddSourceParentSr1.selectedIndex);
					$("#localGovtBodyListMain_error").show();
					return false;
					
				}
		
		}

	
	$("#localGovtBodyListMain_error").hide();
	return true;
		
}

function validateInterMediatePanchayat(ddsource,ddSourceParentSr)
{

	// alert("ddSourceParentSr--3-----"+ddSourceParentSr);
	
	if(ddsource=="V" && ddSourceParentSr ==-1)
		{
		$("#ddSourceLocalBody_error").show();
		return false;
		}
	$("#ddSourceLocalBody_error").hide();
	return true;	
	
}
// End here Village Panchayat Parent validation






function load_page()
{
	
	$("#tierSetupCode_error").hide();
	$("#ddSourceLocalBody_error").hide();
	$("#localGovtBodyListMain_error").hide();
	$("#localBodyNameInEn_error").hide();
	$("#chkculb_error").hide();
	$("#ddDestDistLocalGovtBody_error").hide();
	$("#unmappedLocalBody_error").hide();
}


function refreshdropdowns()
{
	removeSelectedValues('ddCoveredVillageList');
	removeSelectedValues('ddSourceLocalGovtBodyMain');
}

function removeSelectedValues(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=0; i--) 
    	elSel.remove(i);
}


