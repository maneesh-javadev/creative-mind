// JavaScript Document
function next(t)
{
	var show = t+1;
	var hide = t;
	var divToHide = "#tab"+hide;
	var divToShow = "#tab"+show;
	//alert(document.getElementById("chkCategory5").value.split("####")[0]);
	/*if((document.getElementById("chkCategory5").value.split("####")[0])=="U"){
		divToShow="#tab3";
		document.getElementById('tab2_header').style.width='0%';
		document.getElementById('tab2_header').style.visibility='hidden';
		document.getElementById('tab1_header').style.width='49.5%';
		document.getElementById('tab3_header').style.width='49.5%';
	}*/
	
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
	/*if((document.getElementById("chkCategory5").value.split("####")[0])=="U"){
		divToShow="#tab1";
		document.getElementById('tab2_header').style.width='0%';
		document.getElementById('tab2_header').style.visibility='hidden';
		document.getElementById('tab1_header').style.width='49.5%';
		document.getElementById('tab3_header').style.width='49.5%';
	}*/
	$(divToHide).hide( "slide",{ direction: "right"},500, function(){$(divToShow).show( "slide",{direction: "left"},500);});
	$(divToHide+"_header").removeClass("selectedTab");
	$(divToShow+"_header").addClass("selectedTab");
}


