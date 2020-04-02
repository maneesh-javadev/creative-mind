<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<script src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/js/bootstrap-dialog.min.js"></script>
<head>
	
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
	<script>
	 $(function() {		
		$('#sucessAlert').modal('show');	
	});	 

	 function redirectWelcome(){
		 javascript:location.href='welcome.do';
	 }
	</script>
</head>


<body  >

<form id="form1" action="">
<div class="modal fade" id="sucessAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Message</h4>
        </div>
        <div class="modal-body">
           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		   <esapi:encodeForHTML>${msgid}.</esapi:encodeForHTML>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id="modelclose" onclick="redirectWelcome();modelClose();"  data-dismiss="modal">Ok</button>
        </div>
      </div>
      
    </div>
  </div>

</form>
</body>
</html>
