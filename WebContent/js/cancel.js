var allchk = 0;
var msg="";

function togglehide(val)
{ 
	if (val)
	{
		document.getElementById('trdyn1').style.visibility='visible';
		document.getElementById('trdyn2').style.visibility='visible';
		 
	}
	else
	{
		document.getElementById('trdyn1').style.visibility='hidden';
		document.getElementById('trdyn2').style.visibility='hidden';
		 
	}
}



/*function message(msg){
	alert(msg);
}*/

function addControls(url)
{
	
	
}
function newContact()
{
	window.location = "saveContact.do";
}

function deleteContact(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
	
	
	var checkedRadio;
	function ClearRd(chkRd) {
		if (checkedRadio == chkRd) {
			
			chkRd.checked = false;
			checkedRadio = null;
			document.getElementById("txtOrderNo").style.display = 'block';
		} else {
			checkedRadio = chkRd;
			document.getElementById("txtOrderNo").style.display = 'none';
		}
	}


}
