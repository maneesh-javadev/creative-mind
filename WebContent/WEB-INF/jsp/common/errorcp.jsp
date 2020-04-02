<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="taglib_includes.jsp"%>

<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />

<style>
.eror {
	color: #FF0202;
	font-style: italic;
	font-size:20px;
}
</style>

<script>

$(window).on('load',function(){
    $('#customAlert').modal('show');
});


function modelClose(){
	
	document.getElementById('form1').action = "home.htm";
	document.forms['form1'].submit();
	
}

</script>
</head>
<body >
<form id="form1" action="">
<div class="modal fade" id="customAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Message</h4>
        </div>
        <div class="modal-body" id="customAlertbody">
           <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		   <esapi:encodeForHTML>${message}</esapi:encodeForHTML>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id="modelclose" onclick="modelClose();"  data-dismiss="modal">Ok</button>
        </div>
      </div>
      
    </div>
  </div>
</form>
</body>
</html>
