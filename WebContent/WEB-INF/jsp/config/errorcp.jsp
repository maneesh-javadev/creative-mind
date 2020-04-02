<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../taglib_includes.jsp"%>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />

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
<body>
<%
String message=null;
if(request.getAttribute("message")!=null)
	message=request.getAttribute("message").toString();

%>
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
		   <%=message %>
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