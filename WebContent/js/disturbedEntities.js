/*@Author - Ram Krishna Chauhan */
function onLoadSelectDisturbed(){
		var mypath = window.location.href;
		if(mypath.match('disturb=true')=='disturb=true'){
			document.getElementById("chkcrvillage").checked=true;
			document.getElementById("chkchvillage").disabled = true;
			document.getElementById('correctionvillage').style.visibility='visible';
			document.getElementById('correctionvillage').style.display='block';
			document.getElementById('changevillage').style.visibility='hidden';
			document.getElementById('changevillage').style.display='none';
		}
	}
