//By sumit kumar sharma 
var pathname;
$(document).ready(function() {
    pathname = window.location.pathname;
});
function onClose(s) {
	//alert(s);
	var position =pathname.indexOf("/", 2);
	window.location.replace(".."+pathname.substring(0,position)+"/"+s+".htm");
}


function onDelete()
{
	
	return confirm("Are you sure you want to delete the record .! ");
}


function onClear() {
	
	$(".frmfield").each(function() {
		$(this).val("");
		$(this).removeClass("error_fld");
		$(".error").hide();
		$(".helpBox").hide();
		});
	
		$(".combofield").each(function() {
		$(this).val("");
		$(this).removeClass("error_fld");
		$(".error").hide();
		$(".helpBox").hide();
		});
	
}

function onOk()
{
	$("#faded_bg").fadeOut(500);
	$("#popup").fadeOut(500);
	$("#success").fadeOut(500);
	$("#failure").fadeOut(500);
}

$(document).ready(function(){
	
	

	

		
function onLoadPopUp(status)
{
	alert("statuss  "+status);
	if(status=="s")
		{
			$("#success").fadeIn(1000);
			$("#faded_bg").fadeIn(1000);
			$("#popup").fadeIn(1000);
				
		}
	else
		{
		alert("status f "+status);
		$("#failure").fadeIn(1000);
		$("#faded_bg").fadeIn(1000);
		$("#popup").fadeIn(1000);
		
				
	

	
}}});

