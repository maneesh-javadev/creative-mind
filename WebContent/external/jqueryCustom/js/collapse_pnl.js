
	function hideWrraper()
	{
		var obj = document.getElementById("panel");
		var colImg = document.getElementById("collapseImg");
		var colSpanToShow = document.getElementById("span_1_0");
		
		if(obj.style.display != "none"){
			obj.style.display = 'none';
			colSpanToShow.style.display = 'block';
			colImg.src="images/collapse1.jpg"
		}
		else{
			obj.style.display = 'block';
			colSpanToShow.style.display = 'none';
			colImg.src="images/collapse.jpg";
			}
	}


	function setIframeHeight(iframeName) {

		  var iframeEl = document.getElementById? document.getElementById(iframeName): document.all? document.all[iframeName]: null;
		  if (iframeEl) {
		  iframeEl.style.height = "auto"; // helps resize (for some) if new doc shorter than previous
		  var h = alertSize();
		  var new_h = (h+60);
		  iframeEl.style.height = new_h + "px";
		  }
		}

		function alertSize() {
		  var myHeight = 0;
		  if( typeof( window.innerWidth ) == 'number' ) {
		    //Non-IE
		    myHeight = window.innerHeight;
		  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
		    //IE 6+ in 'standards compliant mode'
		    myHeight = document.documentElement.clientHeight;
		  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
		    //IE 4 compatible
		    myHeight = document.body.clientHeight;
		  }
		  //window.alert( 'Height = ' + myHeight );
		  return myHeight;
		}
		
		function changeHeight(iframe)
		{
			var hit = iframe.document.body.offsetHeight;
		var newHit = hit + "px";
		iframe.style.height = newHit;
		}

				

		function openAttachmentsFromReference(url){
			try{
			
			var path = "./OpenPopup?FILE_PATH="+url;
			window.location.href = path;
			}catch(err){}
			}		
	

	function openSignOutPage(url){
			try{
			window.location.href = url;
			}catch(err){}
			}

	function refereshNavigationPanel(){
		
		window.location.reload();
	}
	
// JavaScript Document