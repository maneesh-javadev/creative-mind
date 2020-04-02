// JavaScript Document
var errors = new Array();
function asset()
{
	
	$("#addasset").hide();
	$("#removeasset").show();
	$("#newasset").show();
	$("#AssetName").show();
	$("#AgencyName").show();
	$("#DescriptionOfAsset").show();
	$("#LandmarkOfasset").show();
	$("#LocationName").show();
}
function remove_asset()
{
	$("#addasset").show();
	$("#removeasset").hide();
	$("#newasset").hide();
	$("#AssetName").hide();
	$("#AgencyName").hide();
	$("#DescriptionOfAsset").hide();
	$("#LandmarkOfasset").hide();
	$("#LocationName").hide();

}
function next(t)
{
	var error = true;
	var show = t+1;
	var hide = t;
	var divToHide = "#tab"+hide;
	var divToShow = "#tab"+show;
	if(t==1)
		{
		errors[0] = description();
		errors[1] = officialAddress();
		errors[2] = officialAddress2();
		errors[3] = officialAddress3();
		errors[4] = officialPhone();
		errors[5] = pincode();
		errors[6] =  emailAddress();
		errors[7] = nearestBusStop();
		errors[8] = distanceofnearestBusStop();
		errors[9] = nearestRailwayStation();
		errors[10]= distanceofNearestRailwayStation();
		for(var i=0 ; i<10 ; i++)
		
			{
				if(errors[i]==false)
					{
						error = false;
					}
			}
			
		if(error == false)
			{
			alert("Please enter required fields ...!");
			}
		else
			{
			$(divToHide).hide( "slide",{ direction: "left"},500, function(){$(divToShow).show( "slide",{direction: "right"},500);});
			$(divToHide+"_header").removeClass("selectedTab");
			$(divToShow+"_header").addClass("selectedTab");
			}
		
		}
	
	else
		{
			$(divToHide).hide( "slide",{ direction: "left"},500, function(){$(divToShow).show( "slide",{direction: "right"},500);});
			$(divToHide+"_header").removeClass("selectedTab");
			$(divToShow+"_header").addClass("selectedTab");
		}
}
function previous(s)
{
	var hide = s;
	var show = s-1;
	var divToHide = "#tab"+hide;
	var divToShow = "#tab"+show;
	$(divToHide).hide( "slide",{ direction: "right"},500, function(){$(divToShow).show( "slide",{direction: "left"},500);});
	$(divToHide+"_header").removeClass("selectedTab");
	$(divToShow+"_header").addClass("selectedTab");
}


function show_image(t)
{
	$("#"+t).show();	
}
function hide_image(s)
{
	$("#"+s).hide();
}

function load_page()
{
	$("#tab1_header").addClass("selectedTab");
}




//to display help text 
function show_msg(Field_msg)
{
	var hint='#'+Field_msg+"_msg";
	var error='#'+Field_msg+"_error";
	$("#"+Field_msg).removeClass("error_fld");
	$("#"+Field_msg).removeClass("error_msg");
	$(hint).show();
	$(error).hide();
		
}



//OfficialAddress1 
function officialAddress()
{
	
	if(document.getElementById("OfficialAddress").value == "" )
	{
		
		document.getElementById("OfficialAddress_error").innerHTML="Name of new District (In English)";
		$("#OfficialAddress_error").show();
		$("#OfficialAddress_msg").hide();
		$("#OfficialAddress").addClass("error_fld");
		$("#OfficialAddress").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("OfficialAddress").value.length>60)
	{
		
		document.getElementById("OfficialAddress_error").innerHTML="Official address should  not exceed 60 characters.";
		$("#OfficialAddress_msg").hide();
		$("#OfficialAddress_error").show();
		$("#OfficialAddress").addClass("error_fld");
		$("#OfficialAddress").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#OfficialAddress_msg").hide();
		return true;
			
	}
}



//officialAddress2
function officialAddress2()
{

	if(document.getElementById("OfficialAddress2").value == "" )
	{
		document.getElementById("OfficialAddress2_error").innerHTML="Official address is required.";
		$("#OfficialAddress2_error").show();
		$("#OfficialAddress2_msg").hide();
		$("#OfficialAddress2").addClass("error_fld");
		$("#OfficialAddress2").addClass("error_msg");
		return false;
	}
	else if(document.getElementById("OfficialAddress2").value.length>60)
	{
		document.getElementById("OfficialAddress3_error").innerHTML="Official address should  not exceed than 60 characters.";
		$("#OfficialAddress2_msg").hide();
		$("#OfficialAddress2_error").show();
		$("#OfficialAddress2").addClass("error_fld");
		$("#OfficialAddress2").addClass("error_msg");
		return false;
	}
	
	
	
	else 
	{
		$("#OfficialAddress2_msg").hide();
			
			
	}
}


//OfficialAddress3
function officialAddress3()
{ 
	
	if(document.getElementById("OfficialAddress3").value == "" )
	{
		document.getElementById("OfficialAddress3_error").innerHTML="Official address is required.";
		$("#OfficialAddress3_error").show();
		$("#OfficialAddress3_msg").hide();
		$("#OfficialAddress3").addClass("error_fld");
		$("#OfficialAddress3").addClass("error_msg");
		return false;
	}
	else if(document.getElementById("OfficialAddress3").value.length>60)
	{
		document.getElementById("OfficialAddress3_error").innerHTML="Official address should  not exceed than 60 characters.";
		$("#OfficialAddress3_msg").hide();
		$("#OfficialAddress3_error").show();
		$("#OfficialAddress3").addClass("error_fld");
		$("#OfficialAddress3").addClass("error_msg");
		return false;
	}
	
	
	else 
	{
		$("#OfficialAddress3_msg").hide();
		return true;
			
	}
}


//officialPhone number




function officialPhone()
{
	

	if(document.getElementById("OfficialPhone").value == "" )
	{
		document.getElementById("OfficialPhone_error").innerHTML="Official phone number is required.";
		$("#OfficialPhone_msg").hide();
		$("#OfficialPhone_error").show();
		$("#OfficialPhone").addClass("error_fld");
		$("#OfficialPhone").addClass("error_msg");
		return false;
	}
	else if(isNaN(document.getElementById("OfficialPhone").value))
	{
		document.getElementById("OfficialPhone_error").innerHTML="Only number is allowed.";
		$("#OfficialPhone_msg").hide();
		$("#OfficialPhone_error").show();
		$("#OfficialPhone").addClass("error_fld");
		$("#OfficialPhone").addClass("error_msg");
		return false;
			
			
	}
	else if(document.getElementById("OfficialPhone").value.length <8 || document.getElementById("OfficialPhone").value.length>15)
	{
		document.getElementById("OfficialPhone_error").innerHTML="Official phone number should be not less than 8 and not exceed than 15 digits.";
		$("#OfficialPhone_msg").hide();
		$("#OfficialPhone_error").show();
		$("#OfficialPhone").addClass("error_fld");
		$("#OfficialPhone").addClass("error_msg");
		return false;
		
	}
	
	else 
	{
		$("#OfficialPhone_msg").hide();
		
		return true;	
	}
}


//pin code
function pincode()
{
	
	if(document.getElementById("pinCode").value == "" )
	{
		document.getElementById("pinCode_error").innerHTML="Pincode  is required.";
		$("#pinCode_msg").hide();
		$("#pinCode_error").show();
		$("#pinCode").addClass("error_fld");
		$("#pinCode").addClass("error_msg");
		return false;
	}
	else if(isNaN(document.getElementById("pinCode").value))
	{
		document.getElementById("pinCode_error").innerHTML="Only number is allowed.";
		$("#pinCode_msg").hide();
		$("#pinCode_error").show();
		$("#pinCode").addClass("error_fld");
		$("#pinCode").addClass("error_msg");
		return false;
			
			
	}
	else if(document.getElementById("pinCode").value.length !=6)
	{
		document.getElementById("pinCode_error").innerHTML="Pincode must be 6 digits.";
		$("#pinCode_msg").hide();
		$("#pinCode_error").show();
		$("#pinCode").addClass("error_fld");
		$("#pinCode").addClass("error_msg");
		return false;
	}
	
	else 
	{
		$("#pinCode_msg").hide();
		return true;
			
	}
}



//Email Address

function emailAddress()
{
	var vemail = document.getElementById("EmailAddress").value;
		if(vemail!="")
		{
			
	       if(vemail.indexOf("@")>1)
	        {
			
				var p = vemail.indexOf("@")+2;
				if(vemail.indexOf(".", p) > 0)
				{
					var q=vemail.indexOf(".", p)+2;
					var r= vemail.length;
					if(q<r)
					{
						 $("#EmailAddress_error").hide();
						 $("#EmailAddress_msg").hide();
						 return true; 
											
					}
					else
					{
						   $("#EmailAddress_msg").hide();
						    document.getElementById("EmailAddress_error").innerHTML="Invalid email address.";
						    $("#EmailAddress_error").show();
							$("#EmailAddress").addClass("error_fld");
							$("#OfficialPhone").addClass("error_msg");
							return false;
					}
									
				}
				else
				{			$("#EmailAddress_msg").hide();
							document.getElementById("EmailAddress_error").innerHTML="Invalid email address.";
							$("#EmailAddress_error").show();
							$("#EmailAddress").addClass("error_fld");
							$("#OfficialPhone").addClass("error_msg");
							return false;
				}
			}
		else
			{				$("#EmailAddress_msg").hide();
							document.getElementById("EmailAddress_error").innerHTML="Invalid email address.";
							$("#EmailAddress_error").show();
							$("#EmailAddress").addClass("error_fld");
							$("#OfficialPhone").addClass("error_msg");
							return false;
			}
		}
		else
		{
			 $("#EmailAddress_msg").hide();
			 document.getElementById("EmailAddress_error").innerHTML="Email address is required.";
			 $("#EmailAddress_error").show();
			 $("#EmailAddress").addClass("error_fld");
			 $("#OfficialPhone").addClass("error_msg");
			 return false;
	}


}




//LGD related JS

///////////////////////////////////





//Name in English 
function NameInEn()
{
	
	if(document.getElementById("districtNameInEn").value == "" )
	{
		
		document.getElementById("districtNameInEn_error")
		.innerHTML="Name of new District (In English) required";
		$("#districtNameInEn_error").show();
		$("#districtNameInEn_msg").hide();
		$("#districtNameInEn").addClass("error_fld");
		$("#districtNameInEn").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("districtNameInEn").value.length>60)
	{
		
		document.getElementById("districtNameInEn_error")
		.innerHTML="Name of new District (In English) should  not exceed 60 characters.";
		$("#districtNameInEn_msg").hide();
		$("#districtNameInEn_error").show();
		$("#districtNameInEn").addClass("error_fld");
		$("#districtNameInEn").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("districtNameInEn").value.length<=5)
	{
		
		document.getElementById("districtNameInEn_error")
		.innerHTML="Name of new District (In English) should  not less thsn 5 characters.";
		$("#districtNameInEn_msg").hide();
		$("#districtNameInEn_error").show();
		$("#districtNameInEn").addClass("error_fld");
		$("#districtNameInEn").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#districtNameInEn_msg").hide();
		return true;
			
	}
}


//Name in local language

function NameInLcl()
{
	
	if(document.getElementById("districtNameInLcl").value == "" )
	{
		
		document.getElementById("districtNameInLcl_error")
		.innerHTML="Name of new District (In English) required";
		$("#districtNameInLcl_error").show();
		$("#districtNameInLcl_msg").hide();
		$("#districtNameInLcl").addClass("error_fld");
		$("#districtNameInLcl").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("districtNameInEn").value.length>60)
	{
		
		document.getElementById("districtNameInLcl_error")
		.innerHTML="Name of new District (In English) should  not exceed 60 characters.";
		$("#districtNameInLcl_msg").hide();
		$("#districtNameInLcl_error").show();
		$("#districtNameInLcl").addClass("error_fld");
		$("#districtNameInLcl").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("districtNameInLcl").value.length<=5)
	{
		
		document.getElementById("districtNameInLcl_error")
		.innerHTML="Name of new District (In English) should  not less thsn 5 characters.";
		$("#districtNameInLcl_msg").hide();
		$("#districtNameInLcl_error").show();
		$("#districtNameInLcl").addClass("error_fld");
		$("#districtNameInLcl").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#districtNameInLcl_msg").hide();
		return true;
			
	}
}

//Alias of the district(In English)

function AliasInEn()
{
	
	
	if(document.getElementById("districtAliasInEn").value == "" )
	{
		
		document.getElementById("districtAliasInEn_error")
		.innerHTML="Name of new District (In English) required";
		$("#districtAliasInEn_error").show();
		$("#districtAliasInEn_msg").hide();
		$("#districtAliasInEn").addClass("error_fld");
		$("#districtAliasInEn").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("districtAliasInEn").value.length>60)
	{
		
		document.getElementById("districtAliasInEn_error")
		.innerHTML="Alias of the district should  not exceed 60 characters.";
		$("#districtAliasInEn_msg").hide();
		$("#districtAliasInEn_error").show();
		$("#districtAliasInEn").addClass("error_fld");
		$("#districtAliasInEn").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("districtAliasInEn").value.length<=5)
	{
		
		document.getElementById("districtAliasInEn_error")
		.innerHTML="Alias of the district should  not less thsn 5 characters.";
		$("#districtAliasInEn_msg").hide();
		$("#districtAliasInEn_error").show();
		$("#districtAliasInEn").addClass("error_fld");
		$("#districtAliasInEn").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#districtAliasInEn_msg").hide();
		return true;
			
	}
}

//Alias of the district (In Local language)

function AliasInLcl()
{
	
	if(document.getElementById("districtAliasInLcl").value == "" )
	{
		
		document.getElementById("districtNameInLcl_error")
		.innerHTML="Name of new District (In English) required";
		$("#districtAliasInLcl_error").show();
		$("#districtAliasInLcl_msg").hide();
		$("#districtAliasInLcl").addClass("error_fld");
		$("#districtAliasInLcl").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("districtAliasInLcl").value.length>60)
	{
		
		document.getElementById("districtNameInLcl_error")
		.innerHTML="Alias of the district (In Local language) should  not exceed 60 characters.";
		$("#districtAliasInLcl_msg").hide();
		$("#districtAliasInLcl_error").show();
		$("#districtAliasInLcl").addClass("error_fld");
		$("#districtAliasInLcl").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("districtAliasInLcl").value.length<=5)
	{
		
		document.getElementById("districtAliasInLcl_error")
		.innerHTML="Alias of the district (In Local language) should  not less thsn 5 characters.";
		$("#districtAliasInLcl_msg").hide();
		$("#districtAliasInLcl_error").show();
		$("#districtAliasInLcl").addClass("error_fld");
		$("#districtAliasInLcl").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#districtAliasInLcl_msg").hide();
		return true;
			
	}
}

//Headquarters

function headquarters()
{
	
	if(document.getElementById("districtHeadquarters").value == "" )
	{
		
		document.getElementById("districtHeadquarters_error")
		.innerHTML="Name of new District (In English) required";
		$("#districtHeadquarters_error").show();
		$("#districtHeadquarters_msg").hide();
		$("#districtHeadquarters").addClass("error_fld");
		$("#districtHeadquarters").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("districtHeadquarters").value.length>60)
	{
		
		document.getElementById("districtHeadquarters_error")
		.innerHTML="Name of new District (In English) should  not exceed 60 characters.";
		$("#districtHeadquarters_msg").hide();
		$("#districtHeadquarters_error").show();
		$("#districtHeadquarters").addClass("error_fld");
		$("#districtHeadquarters").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("districtHeadquarters").value.length<=5)
	{
		
		document.getElementById("districtNameInLcl_error")
		.innerHTML="Name of new District (In English) should  not less thsn 5 characters.";
		$("#districtHeadquarters_msg").hide();
		$("#districtHeadquarters_error").show();
		$("#districtHeadquarters").addClass("error_fld");
		$("#districtHeadquarters").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#districtHeadquarters_msg").hide();
		return true;
			
	}
}

//Census2011 Code

function Cns2011Code()
{
	
	if(document.getElementById("census2011Code").value == "" )
	{
		
		document.getElementById("census2011Code_error")
		.innerHTML="Name of new District (In English) required";
		$("#census2011Code_error").show();
		$("#census2011Code_msg").hide();
		$("#census2011Code").addClass("error_fld");
		$("#census2011Code").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("census2011Code").value.length>60)
	{
		
		document.getElementById("census2011Code_error")
		.innerHTML="Census2011 Code should  not exceed 60 characters.";
		$("#census2011Code_msg").hide();
		$("#census2011Code_error").show();
		$("#census2011Code").addClass("error_fld");
		$("#census2011Code").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("census2011Code").value.length<=1)
	{
		
		document.getElementById("census2011Code_error")
		.innerHTML="Census2011 Code should  not less thsn 1 characters.";
		$("#census2011Code_msg").hide();
		$("#census2011Code_error").show();
		$("#census2011Code").addClass("error_fld");
		$("#census2011Code").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#census2011Code_msg").hide();
		return true;
			
	}
}

//State Specific Code

function specificCode()
{
	
	if(document.getElementById("stateSpecificCode").value == "" )
	{
		
		document.getElementById("stateSpecificCode_error")
		.innerHTML="Name of new District (In English) required";
		$("#stateSpecificCode_error").show();
		$("#stateSpecificCode_msg").hide();
		$("#stateSpecificCode").addClass("error_fld");
		$("#stateSpecificCode").addClass("error_msg");
		return false;

	}
	else if(document.getElementById("stateSpecificCode").value.length>60)
	{
		
		document.getElementById("stateSpecificCode_error")
		.innerHTML="State Specific Code should  not exceed 60 characters.";
		$("#stateSpecificCode_msg").hide();
		$("#stateSpecificCode_error").show();
		$("#stateSpecificCode").addClass("error_fld");
		$("#stateSpecificCode").addClass("error_msg");
		return false;
		
	}
	
	else if(document.getElementById("stateSpecificCode").value.length<=5)
	{
		
		document.getElementById("stateSpecificCode_error")
		.innerHTML="State Specific Code should  not less thsn 5 characters.";
		$("#stateSpecificCode_msg").hide();
		$("#stateSpecificCode_error").show();
		$("#stateSpecificCode").addClass("error_fld");
		$("#stateSpecificCode").addClass("error_msg");
		return false;
		
	}
	else 
	{		
		$("#stateSpecificCode_msg").hide();
		return true;
			
	}
}



