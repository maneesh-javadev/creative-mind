<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>	
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<link href="HTML_panchayat - 2.2/css/panchayat_main.css"	rel="stylesheet" type="text/css" />
<script type='text/javascript'	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
	<script>
	
	$(function() {		
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
		$( "#dialog-message" ).dialog({
			modal: true,
			buttons: {
				
				Yes : function() {
					
					callGISMapView("${villageCode}");
					$( this).dialog( "close" );
					},
					No : function() {
						document.getElementById('form1').action = "home.htm";
						document.forms['form1'].submit();
						$( this).dialog( "close" );
					}
				
			}
		});
	});
	
	
	var callGISMapView = function (  villageCode){
		lgdDwrVillageService.finalizeVillageGIS(parseInt(villageCode), {
			callback : function( result ){
				
				 if("FAILED" == result){
					customAlert(result);
					document.getElementById('form1').action = "home.htm";
					document.forms['form1'].submit();
				}else{
					$("#dialogBX").dialog({
						title:' GIS Map View of ${villageName} Village',
					    modal: true,
					    width:950,
					    height: 700,
					    resizable:false,
					    open: function(ev, ui){
					    	 showLoadingImage();
							
				             $('#myIframe').attr('src', result);
				             $("#myIframe").load(function(){
				            	 hideLoadingImage(); 
						    });
				    	},
				    	close: function() {
				    		document.getElementById('form1').action = "home.htm";
							document.forms['form1'].submit();
							$( this).dialog( "close" );
						} 
					});	
				} 
			},
			errorHandler : function( errorString, exception){
					customAlert(exception);
			},
			async : true
		});		
	};
	</script>
</head>


<body  >
<form id="form1" action="">
<div id="dialogBX" style="display: none;">
						<iframe id="myIframe" width="910" height="650"></iframe>
			</div>
<div class="demo">

<div id="dialog-message" title="Message">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		<esapi:encodeForHTML>${msgid}.Do you want to finalize village of GIS platform?</esapi:encodeForHTML>
	</p>
	<p>
		<!-- Currently using <b>36% of your storage space</b>. -->
	</p>
</div>
</div><!-- End demo -->

<table width="100%" border="3" >
  <tr>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td>&nbsp;</td>
  </tr>
    <tr >
    <td>&nbsp;</td>
  </tr>
  <tr>
   <%--  <td><div align="center" style="font-weight:bold;font-size:18px">${msgid}</div></td> --%>
  </tr>
</table>
</form>
</body>
</html>
