// JavaScript Document

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
	var show = t+1;
	var hide = t;
	var divToHide = "#tab"+hide;
	var divToShow = "#tab"+show;
	$(divToHide).hide( "slide",{ direction: "left"},500, function(){$(divToShow).show( "slide",{direction: "right"},500);});
	$(divToHide+"_header").removeClass("selectedTab");
	$(divToShow+"_header").addClass("selectedTab");

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


// JavaScript Document
//var errors = new Array();
//var i=0;
//function submit_form()
//{
//	validate_description();
////	
//	for(var i=0; i<errors.length; i++)
//	{
//		if(errors[i]=='y')
//		{
//			alert("Please fill the registration form properly..!") ;
//			return false;
//		}
//	}
//	if ($("#declaration")[0].checked == false)
//	{
//		 alert ( "Please check the agreement ");
//		return false;
//	}
//	else
//	{
//		 return confirm("Do you really want this submit this form ?")
//	}
//}



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




//userloginid



function userloginid()
{

	if(document.getElementById("userloginid").value == "" )
	{
		document.getElementById("userloginid_error").innerHTML="userloginid is required.";
		$("#userloginid_error").show();
		$("#userloginid_msg").hide();
		$("#userloginid").addClass("error_fld");
		$("#userloginid").addClass("error_msg");

	}
	else if(document.getElementById("userloginid").value.length>60)
	{
		document.getElementById("userloginid_error").innerHTML="Official address should  not exceed than 60 characters.";
		$("#userloginid_msg").hide();
		$("#userloginid_error").show();
		$("#userloginid").addClass("error_fld");
		$("#userloginid").addClass("error_msg");
		
	}
	else 
	{
		$("#userloginid_msg").hide();
			
			
	}
}



function firstnameId()
{
	 var searchVal = $("#firstname").val();
	 var searchReg = /^[a-zA-Z-]+$/;
	if(searchVal != "" )
	{
		
		if(!searchReg.test(searchVal))
			{
		document.getElementById("firstname_error").innerHTML=" only characters is allowed.";
		$("#firstname_msg").hide();
		$("#firstname_error").fadeIn(1000).show();
		$("#firstname").addClass("error_fld");
		$("#firstname").addClass("error_msg");
			}
		
	}
	
}


function lastnameId()
{
	 var searchVal = $("#lastname").val();
	 var searchReg = /^[a-zA-Z-]+$/;
	if(searchVal != "" )
	{
		
		if(!searchReg.test(searchVal))
			{
		document.getElementById("lastname_error").innerHTML=" only characters is allowed.";
		$("#lastname_msg").hide();
		$("#lastname_error").fadeIn(1000).show();
		$("#lastname").addClass("error_fld");
		$("#lastname").addClass("error_msg");
			}
		
	}
	
}



/*function mobilenumberId()
{
	 var searchVal = $("#mobilenumber").val();
	 var searchReg = /^[a-zA-Z-]+$/;
	if(searchVal != "" )
	{
		
		if(searchReg.test(searchVal))
			{
		document.getElementById("mobilenumber_error").innerHTML=" Only Number allowed for Mobile number";
		$("#mobilenumber_msg").hide();
		$("#mobilenumber_error").fadeIn(1000).show();
		$("#mobilenumber").addClass("error_fld");
		$("#mobilenumber").addClass("error_msg");
			}
		
	}
	
}
*/



function hideMessageOnKeyPress(id)
{
	
	
	if(document.getElementById(id).value!="")
		{
			$("#"+id+"_msg").fadeOut(1000);
		}
	
}

function hideError(id)
{
	$("#"+id+"_error").fadeOut(1000).hide();
	$("#"+id).removeClass("error_fld");
	$("#"+id).removeClass("error_msg");
}


/*

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

	}
	else if(document.getElementById("OfficialAddress2").value.length>60)
	{
		document.getElementById("OfficialAddress3_error").innerHTML="Official address should  not exceed than 60 characters.";
		$("#OfficialAddress2_msg").hide();
		$("#OfficialAddress2_error").show();
		$("#OfficialAddress2").addClass("error_fld");
		$("#OfficialAddress2").addClass("error_msg");
		
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

	}
	else if(document.getElementById("OfficialAddress3").value.length>60)
	{
		document.getElementById("OfficialAddress3_error").innerHTML="Official address should  not exceed than 60 characters.";
		$("#OfficialAddress3_msg").hide();
		$("#OfficialAddress3_error").show();
		$("#OfficialAddress3").addClass("error_fld");
		$("#OfficialAddress3").addClass("error_msg");
		
	}
	
	
	else 
	{
		$("#OfficialAddress3_msg").hide();
			
			
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

	}
	else if(isNaN(document.getElementById("OfficialPhone").value))
	{
		document.getElementById("OfficialPhone_error").innerHTML="Only number is allowed.";
		$("#OfficialPhone_msg").hide();
		$("#OfficialPhone_error").show();
		$("#OfficialPhone").addClass("error_fld");
		$("#OfficialPhone").addClass("error_msg");

			
			
	}
	else if(document.getElementById("OfficialPhone").value.length <8 || document.getElementById("OfficialPhone").value.length>15)
	{
		document.getElementById("OfficialPhone_error").innerHTML="Official phone number should be not less than 8 and not exceed than 15 digits.";
		$("#OfficialPhone_msg").hide();
		$("#OfficialPhone_error").show();
		$("#OfficialPhone").addClass("error_fld");
		$("#OfficialPhone").addClass("error_msg");

		
	}
	
	else 
	{
		$("#OfficialPhone_msg").hide();
		
			
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

	}
	else if(isNaN(document.getElementById("pinCode").value))
	{
		document.getElementById("pinCode_error").innerHTML="Only number is allowed.";
		$("#pinCode_msg").hide();
		$("#pinCode_error").show();
		$("#pinCode").addClass("error_fld");
		$("#pinCode").addClass("error_msg");
		
			
			
	}
	else if(document.getElementById("pinCode").value.length !=6)
	{
		document.getElementById("pinCode_error").innerHTML="Pincode must be 6 digits.";
		$("#pinCode_msg").hide();
		$("#pinCode_error").show();
		$("#pinCode").addClass("error_fld");
		$("#pinCode").addClass("error_msg");
		
	}
	
	else 
	{
		$("#pinCode_msg").hide();
		
			
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
						     
											
					}
					else
					{
						   $("#EmailAddress_msg").hide();
						    document.getElementById("EmailAddress_error").innerHTML="Invalid email address.";
						    $("#EmailAddress_error").show();
							$("#EmailAddress").addClass("error_fld");
							$("#OfficialPhone").addClass("error_msg");
					}
									
				}
				else
				{			$("#EmailAddress_msg").hide();
							document.getElementById("EmailAddress_error").innerHTML="Invalid email address.";
							$("#EmailAddress_error").show();
							$("#EmailAddress").addClass("error_fld");
							$("#OfficialPhone").addClass("error_msg");
				}
			}
		else
			{				$("#EmailAddress_msg").hide();
							document.getElementById("EmailAddress_error").innerHTML="Invalid email address.";
							$("#EmailAddress_error").show();
							$("#EmailAddress").addClass("error_fld");
							$("#OfficialPhone").addClass("error_msg");
			}
		}
		else
		{
			 $("#EmailAddress_msg").hide();
			 document.getElementById("EmailAddress_error").innerHTML="Email address is required.";
			 $("#EmailAddress_error").show();
			 $("#EmailAddress").addClass("error_fld");
			 $("#OfficialPhone").addClass("error_msg");
	}


}
//Nearest bus stop
function nearestBusStop()
{  
	
	if(document.getElementById("NearestBusStop").value == "" )
	{
		document.getElementById("NearestBusStop_error").innerHTML="Nearest bus stop   is required.";
		$("#NearestBusStop_error").show();
		$("#NearestBusStop_msg").hide();
		$("#NearestBusStop").addClass("error_fld");
		$("#NearestBusStop").addClass("error_msg");

	}
	else 
	{
		
		$("#NearestBusStop_msg").hide();
			
	}
}

//Distance of nearest bus stop

function distanceofnearestBusStop()
{
	if(document.getElementById("DistanceofNearestBusStop").value == "" )
	{
		document.getElementById("DistanceofNearestBusStop_error").innerHTML="Please enter the distance of the bus stop in (KM).";
		$("#DistanceofNearestBusStop_msg").hide();
		$("#DistanceofNearestBusStop_error").show();
		$("#DistanceofNearestBusStop").addClass("error_fld");
		$("#DistanceofNearestBusStop").addClass("error_msg");

	}
	else if(isNaN(document.getElementById("DistanceofNearestBusStop").value))
	{
		document.getElementById("DistanceofNearestBusStop_error").innerHTML="Only number is allowed.";
		$("#DistanceofNearestBusStop_msg").hide();
		$("#DistanceofNearestBusStop_error").show();
		$("#DistanceofNearestBusStop").addClass("error_fld");
		$("#DistanceofNearestBusStop").addClass("error_msg");
		
			
	}
	
	else 
	{
		$("#DistanceofNearestBusStop_msg").hide();
		
			
	}
	
}


//Nearest Railway station

function nearestRailwayStation()
{

	if(document.getElementById("NearestRailwayStation").value == "" )
	{
		document.getElementById("NearestRailwayStation_error").innerHTML="Railway station name is required.";
		$("#NearestRailwayStation_msg").hide();
		$("#NearestRailwayStation_error").show();
		$("#NearestRailwayStation").addClass("error_fld");
		$("#NearestRailwayStation").addClass("error_msg");

	}
	
	
	
	else 
	{
		
		$("#NearestRailwayStation_msg").hide();
	}
}

//distance of nearest railway station
function distanceofNearestRailwayStation()
{
	if(document.getElementById("DistanceofNearestRailwayStation").value == "" )
	{
		document.getElementById("DistanceofNearestRailwayStation_error").innerHTML="Enter the distance from Railway station  is required.";
		$("#DistanceofNearestRailwayStation_msg").hide();
		$("#DistanceofNearestRailwayStation_error").show();
		$("#DistanceofNearestRailwayStation").addClass("error_fld");
		$("#DistanceofNearestRailwayStation").addClass("error_msg");

	}
	
	else if(isNaN(document.getElementById("DistanceofNearestRailwayStation").value))
	{
		document.getElementById("DistanceofNearestRailwayStation_error").innerHTML="Only number is allowed.";
		$("#DistanceofNearestRailwayStation_msg").hide();
		$("#DistanceofNearestRailwayStation_error").show();
		$("#DistanceofNearestRailwayStation").addClass("error_fld");
		$("#DistanceofNearestRailwayStation").addClass("error_msg");
		
			
	}
	else 
	{
		
		$("#DistanceofNearestRailwayStation_msg").hide();
	}
}

//Nearest Airport
function nearestAirportToTouristPlace()
{

	if(document.getElementById("nearestAirportToTouristPlace").value == 0 )
	{
		document.getElementById("nearestAirportToTouristPlace_error").innerHTML=" Airport name  is required  ";
		$("#nearestAirportToTouristPlace_msg").hide();
		$("#nearestAirportToTouristPlace_error").show();
		$("#nearestAirportToTouristPlace").addClass("error_fld");
		$("#nearestAirportToTouristPlace").addClass("error_msg");

	}
	else 
	{
		$("#nearestAirportToTouristPlace_msg").hide();
			
	}
}
//Distance of nearest airport

function distanceofnearestAirport()
{

	if(document.getElementById("distanceofNearestAirport").value == 0 )
	{
		document.getElementById("distanceofNearestAirport_error").innerHTML=" Distance of Airport in (KM)  is required  ";
		$("#distanceofNearestAirport_msg").hide();
		$("#distanceofNearestAirport_error").show();
		$("#distanceofNearestAirport").addClass("error_fld");
		$("#distanceofNearestAirport").addClass("error_msg");

	}
	
	
	else if(isNaN(document.getElementById("distanceofNearestAirport").value))
	{
		document.getElementById("distanceofNearestAirport_error").innerHTML="Only digits are  allowed.";
		$("#distanceofNearestAirport_msg").hide();
		$("#distanceofNearestAirport_error").show();
		$("#distanceofNearestAirport").addClass("error_fld");
		$("#distanceofNearestAirport").addClass("error_msg");
		
			
	}
	else 
	{
		$("#distanceofNearestAirport_msg").hide();
			
	}
}

//Name of the tourist place


function nameoftheTouristPlace()
{

	if(document.getElementById("NameoftheTouristPlace").value == "" )
	{
		document.getElementById("NameoftheTouristPlace_error").innerHTML=" Name of the tourist place is required ";
		$("#NameoftheTouristPlace_error").show();
		$("#NameoftheTouristPlace_msg").hide();
		$("#NameoftheTouristPlace").addClass("error_fld");
		$("#NameoftheTouristPlace").addClass("error_msg");

	}
	else 
	{
		$("#NameoftheTouristPlace_msg").hide();
			
	}
}

//Nearest bus stop to tourist place

function nearestbusstoptotouristPlace()
{

	if(document.getElementById("nearestBusStopToTouristPlace").value == "" )
	{
		document.getElementById("nearestBusStopToTouristPlace_error").innerHTML=" Name of the bus stop to   tourist place is required ";
		$("#nearestBusStopToTouristPlace_error").show();
		$("#nearestBusStopToTouristPlace_msg").hide();
		$("#nearestBusStopToTouristPlace").addClass("error_fld");
		$("#nearestBusStopToTouristPlace").addClass("error_msg");

	}
	else 
	{
		$("#nearestBusStopToTouristPlace_msg").hide();
			
	}
}

//Distance of the nearest bus stop to tourist place
function distanceofnearestBusstoptoTouristplace()
{

	if(document.getElementById("DistanceofNearestBusStoptotouristplace").value == "" )
	{
		document.getElementById("DistanceofNearestBusStoptotouristplace_error").innerHTML=" Distance of the  bus stop to   tourist place is required ";
		$("#DistanceofNearestBusStoptotouristplace_error").show();
		$("#DistanceofNearestBusStoptotouristplace_msg").hide();
		$("#DistanceofNearestBusStoptotouristplace").addClass("error_fld");
		$("#DistanceofNearestBusStoptotouristplace").addClass("error_msg");

	}
	
	else if(isNaN(document.getElementById("DistanceofNearestBusStoptotouristplace").value))
	{
		document.getElementById("DistanceofNearestBusStoptotouristplace_error").innerHTML="Only digits are  allowed.";
		$("#DistanceofNearestBusStoptotouristplace_msg").hide();
		$("#DistanceofNearestBusStoptotouristplace_error").show();
		$("#DistanceofNearestBusStoptotouristplace").addClass("error_fld");
		$("#DistanceofNearestBusStoptotouristplace").addClass("error_msg");
		
			
	}
	
	
	else 
	{
		$("#DistanceofNearestBusStoptotouristplace_msg").hide();
			
	}
}
//Nearest railway station to tourist place
function nearestrailwaystationtoTouristPlace()
{

	if(document.getElementById("nearestRailwayStationToTouristPlace").value == "" )
	{
		document.getElementById("nearestRailwayStationToTouristPlace_error").innerHTML=" Name of the railway station  is required ";
		$("#nearestRailwayStationToTouristPlace_error").show();
		$("#nearestRailwayStationToTouristPlace_msg").hide();
		$("#nearestRailwayStationToTouristPlace").addClass("error_fld");
		$("#nearestRailwayStationToTouristPlace").addClass("error_msg");

	}
	
	else 
	{
		$("#nearestRailwayStationToTouristPlace_msg").hide();
			
	}
}
//Distance of the railway station
function distanceNearestrailwaystationtoTouristPlace()
{

	if(document.getElementById("distanceNearestRailwayStationToTouristPlace").value ==0)
	{
		document.getElementById("distanceNearestRailwayStationToTouristPlace_error").innerHTML=" Distance  of the railway station  is required ";
		$("#distanceNearestRailwayStationToTouristPlace_error").show();
		$("#distanceNearestRailwayStationToTouristPlace_msg").hide();
		$("#distanceNearestRailwayStationToTouristPlace").addClass("error_fld");
		$("#distanceNearestRailwayStationToTouristPlace").addClass("error_msg");

	}
	
	else if(isNaN(document.getElementById("distanceNearestRailwayStationToTouristPlace").value))
	{
		document.getElementById("distanceNearestRailwayStationToTouristPlace_error").innerHTML="Only digits are  allowed.";
		$("#distanceNearestRailwayStationToTouristPlace_msg").hide();
		$("#distanceNearestRailwayStationToTouristPlace_error").show();
		$("#distanceNearestRailwayStationToTouristPlace").addClass("error_fld");
		$("#distanceNearestRailwayStationToTouristPlace").addClass("error_msg");
		
			
	}
	else 
	{
		$("#distanceNearestRailwayStationToTouristPlace_msg").hide();
			
	}
}
//Nearest  airport



function nearestAirport()
{

	if(document.getElementById("nearestAirportToTouristPlace").value == "")
	{
		document.getElementById("nearestAirportToTouristPlace_error").innerHTML=" Airport name is required";
		$("#nearestAirportToTouristPlace_error").show();
		$("#nearestAirportToTouristPlace_msg").hide();
		$("#nearestAirportToTouristPlace").addClass("error_fld");
		$("#nearestAirportToTouristPlace").addClass("error_msg");

	}
	else 
	{
		$("#nearestAirportToTouristPlace_msg").hide();
			
	}
}


function distancenearestairporttoTouristPlace()
{

	if(document.getElementById("distanceofNearestTouristplaceAirport").value == "")
	{
		document.getElementById("distanceofNearestTouristplaceAirport_error").innerHTML=" Distance  of the railway station  is required ";
		$("#distanceofNearestTouristplaceAirport_error").show();
		$("#distanceofNearestTouristplaceAirport_msg").hide();
		$("#distanceofNearestTouristplaceAirport").addClass("error_fld");
		$("#distanceofNearestTouristplaceAirport").addClass("error_msg");

	}
	
	else if(isNaN(document.getElementById("distanceofNearestTouristplaceAirport").value))
	{
		document.getElementById("distanceofNearestTouristplaceAirport_error").innerHTML="Only digits are  allowed.";
		$("#distanceofNearestTouristplaceAirport_msg").hide();
		$("#distanceofNearestTouristplaceAirport_error").show();
		$("#distanceofNearestTouristplaceAirport").addClass("error_fld");
		$("#distanceofNearestTouristplaceAirport").addClass("error_msg");
		
			
	}
	else 
	{
		$("#distanceofNearestTouristplaceAirport_msg").hide();
			
	}
}*/