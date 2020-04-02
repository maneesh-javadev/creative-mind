function setDirection(val) {
	document.getElementById('direction').value = val;
	document.forms['form1'].action = "stateWiseUnmappedVillagesReportPagination.do?<csrf:token uri='stateWiseUnmappedVillagesReportPagination.do'/>";
	document.forms['form1'].submit();
}

function validate_report() {
	$( '#errorcaptchaAnswer').text("");	
	var statename = document.getElementById("ddSourceState").value;
	var code = document.getElementById("captchaAnswer").value;
	flag = true;
	if (statename == null || statename == "") {

		document.getElementById('errSelectStateName').style.display = 'block';
		document.getElementById('errSelectStateName').style.visibility = 'visible';
		flag = false;
	} 
	else if(code==0 ||code ==null)
	{
		document.getElementById("errorCaptcha").innerHTML = "Please Enter CAPTCHA  in the textbox"; 
	    document.getElementById("errorCaptcha").focus();
	    flag = false;
		} 

	/*else if (document.getElementById('errorCaptcha').style.display == 'block'
			|| document.getElementById('errorCaptcha').style.visibility == 'visible') {
		document.getElementById('errorCaptcha').style.display = 'none';
		document.getElementById('errorCaptcha').style.visibility = 'hidden';
		flag = true;
	}*/
	

	return flag;

}

function go(url) {

	if (url.indexOf('home.htm') > -1 || url.indexOf(window.location.pathname.split('/')[window.location.pathname.split('/').length - 1]) == -1) {
		if (url.indexOf("?") > -1)
			window.location = url + "&OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
		else
			window.location = url + "?OWASP_CSRFTOKEN=" + getParameterByNameForCSRF();
	} else
		window.location = window.location.href;

}

function getParameterByNameForCSRF() {

	var name = "OWASP_CSRFTOKEN";
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.search);
	if (results == null)
		return "";
	else
		return decodeURIComponent(results[1].replace(/\+/g, " "));
}

function error_remove() {
	document.getElementById('errSelectStateName').style.display = 'none';
	document.getElementById('errSelectStateName').style.visibility = 'hidden';

}

function PrintDiv() {

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var headstr = "<html><head><title></title></head><body>";
	var footstr = "</body>";
	var newstr = document.getElementById('printable');
	var oldstr = document.body.innerHTML;

	document.body.innerHTML = headstr + (newstr.innerHTML) + footstr;
	window.print();
	document.body.innerHTML = oldstr;
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
	return false;

}

function CallPrint() {
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	/*document.getElementById('footertext').style.fontSize = '13px';*/
	var printContent = document.getElementById("printable");
	// document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
	// alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML);
	Win4Print.document.close();
	Win4Print.focus();
	Win4Print.print();
	Win4Print.close();
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
}

/*$(document).ready(function() {

	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");

	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");

});*/