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
		$('#sucessAlert').modal('show');	
		
		
		$("#modelYes").click(function() {
			callGISMapView("${villageCode}");
			
		 });
		
		
		$("#modelNo").click(function() {
			document.getElementById('form1').action = "home.htm";
			document.forms['form1'].submit();
			$('#sucessAlert').modal('hide');
		 });
		/* $( "#dialog:ui-dialog" ).dialog( "destroy" );
	
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
		}); */
	});
	
	
	var callGISMapView = function (  villageCode){
		lgdDwrVillageService.finalizeVillageGIS(parseInt(villageCode), {
			callback : function( result ){
				
				 if("FAILED" == result){
					customAlert(result);
					document.getElementById('form1').action = "home.htm";
					document.forms['form1'].submit();
				}else{
					$("#dialogBXTitle").text('GIS Map View of ${villageName} Village');
					
					 $('#myIframe').attr('src', result);
					/*  $("#myIframe").load(function(){
		            	alert("test"); 
				    }); */
					$('#dialogBX').modal('show'); 
				    $('#sucessAlert').modal('hide');
					/* $("#dialogBX").dialog({
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
					});	 */
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
<body>
<section class="content">
   <div class="row">
      <div class="container">
	     <section class="col-lg-12 prebox">
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
						<esapi:encodeForHTML>${msgid}.Do you want to finalize village of GIS platform?</esapi:encodeForHTML>
				        </div>
				        <div class="modal-footer">
				          <button type="button" class="btn btn-default"  id="modelYes"  data-dismiss="modal">YES</button>
				          <button type="button" class="btn btn-default"  id="modelNo"  data-dismiss="modal">NO</button>
				        </div>
				      </div>
				      
				    </div>
			  </div>
				
				<div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;">
											<div class="modal-content">
								  				<div class="modal-header">
								   				   <button type="button" class="close" data-dismiss="modal">&times;</button>
								    			  	<h4 class="modal-title" id="dialogBXTitle"></h4>
								    			  	
								  				</div>
								  				<div class="modal-body" id="dialogBXbody">
								        			<iframe id="myIframe" width="910" height="650"></iframe>
								        			
								     			 
												</div>
												
									</div>
								</div>
				</div>	


				</form>
			</section>
		</div>
	</div>
</section>

</body>
</html>
