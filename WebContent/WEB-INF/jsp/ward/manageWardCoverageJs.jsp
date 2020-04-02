
<script>
pubWardLen=isNaN(parseInt('${wardsize}'))?0:parseInt('${wardsize}');

//alert(pubWardLen);
$(document).ready(function() {

	
	$( "#actionSearchClose" ).click(function() {
		callActionUrl('home.htm','localGovtBodyForm');
	});
	
	if(isParseJson('${showTable}')){
	    //$('#viewData').show();
	    //$('#btnpanel').show();
	 //   $("#demand1 tr:odd").css("background-color", "#ffffff");
		//$("#demand1 tr:even").css("background-color", "#dedede");
		
		$("#demand2 tr:odd").css("background-color", "#ffffff");
		$("#demand2 tr:even").css("background-color", "#dedede");
	//	$("#trhead").css("background-color", "#");
		
		
	 }
	
	 
	$( "#actionFetchWardDetails" ).click(function() {
		//alert("hi");
		if(checkNotUrbanProcess()){
		var lbTypeHierarchylement = $( '#lbTypeHierarchy' );
		//alert("lbTypeHierarchylement"+$( lbTypeHierarchylement ).val());
			if( $_checkEmptyObject($( lbTypeHierarchylement ).val()) ){
				$(lbTypeHierarchylement).addClass("error");
				$( '#errorLbTypeHierarchy' ).text("<spring:message code='error.CHOOSEHIERARCHY' htmlEscape='true'/>");
				return false;
			}
		}
		var localBodyTypeElement = $( '#localBodyType' );
		var selectedlocalBodyType = $( localBodyTypeElement ).val();
		if( $_checkEmptyObject(selectedlocalBodyType) ){
			$(localBodyTypeElement).addClass("error");
			$( '#errorLocalBodyType' ).text("<spring:message code='error.select.LBTYPE' htmlEscape='true'/>");
			return false;
		}
		
			var element = $( '[name = localBodyLevelCodes]' );
			var localBodyElement = $( element )[$( element ).length - 1];
			if(!$_checkEmptyObject(localBodyElement) && !validateLBCode(localBodyElement)){
				return false;
			}
		
		callActionUrl('viewWardCoveragePRIAndTRI.htm','localGovtBodyForm');
			
	 });
});
	 

var callActionUrl = function (url,fromId) {
	
	$( 'form[id='+fromId+']' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id='+fromId+']' ).attr('method','post');
	$( 'form[id='+fromId+']' ).submit();
};


function publishWard() {
	 var pubWardList = [];
	 var checkAll = $('#publishAll').is(':checked');
	if (checkAll) {
	 var array_tableData = "";
	  var oTable = $('#demand').dataTable();
	  var rows = oTable.dataTable().fnGetNodes();
	  for (var i = 0; i < rows.length; i++) {
	   var datatableRowId = $(rows[i]).attr('id');
	   if (!$(rows[i]).find('#p_' + datatableRowId).is(':disabled')) {
	    var publishChecked = $(rows[i]).find('#p_' + datatableRowId).is(':checked');
	    if (publishChecked) {
	    	pubWardList.push("P_"+datatableRowId);
	    }
	   }


	  }

	  if (pubWardList.length < 1) {
	   customAlert("Kindly Select any Ward To publish");
	   return false;
	  } else {
	   $('#publishWardsList').val(pubWardList.toString());
	   $('#publishAllWard').val("Yes");
	   $('#manage').val("manage");
	   callActionUrl('publishWardCoverage.htm', 'wardCoverageForm');
	  }


	 } else {
	  var array_tableData = "";
	  var oTable = $('#demand').dataTable();
	  var rows = oTable.dataTable().fnGetNodes();
	  for (var i = 0; i < rows.length; i++) {
	   var datatableRowId = $(rows[i]).attr('id');
	   if (!$(rows[i]).find('#p_' + datatableRowId).is(':disabled')) {
	    var publishChecked = $(rows[i]).find('#p_' + datatableRowId).is(':checked');
	    if (publishChecked) {
	     pubWardList.push("P_"+datatableRowId);
	    }
	   }

	  }

	  if (pubWardList.length < 1) {
	   customAlert("Kindly Select any Ward To publish");
	   return false;
	  } else {
	   $('#publishWardsList').val(pubWardList.toString());
	   $('#manage').val("manage");
	   callActionUrl('publishWardCoverage.htm', 'wardCoverageForm');
	  }


	 }

	}

function deleteWardValues() {
	 var delWardList = [];
	 var checkAll=$('#deleteAll').is( ':checked' );
	 if( checkAll ){
		 
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#d_' + datatableRowId).is( ':disabled' )){
	 			 var deleteChecked = $(rows[i]).find('#d_' + datatableRowId).is( ':checked' );
	 	         if( deleteChecked ){
	 	        	delWardList.push("D_"+datatableRowId);  	
	 			 	}
	 		 }        
	      }
		 
	 	if(delWardList.length < 1){
			 customAlert("Kindly Select any Ward To Delete");
			 return false;
		 }else{
			 $('#deleteWardsList').val(delWardList.toString());
			 $('#manage').val("manage");
			  callActionUrl('publishWardCoverage.htm','wardCoverageForm');
		 }
		 
	 
	 }else{
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#d_' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#d_' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 	        		delWardList.push("D_"+datatableRowId);  	  	
	 			 	}
	 		 }	                
	        }
	 	
	 	 if(delWardList.length < 1){
			 customAlert("Kindly Select any Ward To Delete");
			 return false;
		 }else{
			 $('#deleteWardsList').val(delWardList.toString());
		 	 $('#manage').val("manage");
		 	 $('#deleteAllWardTemp').val("Yes");	 
		 	 
		 	callActionUrl('publishWardCoverage.htm','wardCoverageForm');
		 }
	 	
	 	
	 }
	 
}

function selectAll(){
	 var checkAll	   = $('#publishAll').is( ':checked' );
	 
	 if( checkAll ){
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		$(rows[i]).find('#p_' + datatableRowId).prop('checked', true);
	 		$(rows[i]).find('#d_' + datatableRowId).prop('checked', false);
	 		$('#deleteAll').prop('checked', false);
	 	 }		 
	 }else{
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 $(rows[i]).find('#p_' + datatableRowId).prop('checked', false);
	 	 }		 
	 }
	
	 
}

function deleteAllWards() {
	 var checkAllDelete= $('#deleteAll').is( ':checked' );
	 if( checkAllDelete ){
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {	 		 
	 		var datatableRowId = $(rows[i]).attr( 'id' );
	 		$(rows[i]).find('#d_' + datatableRowId).prop('checked', true);
	 		$(rows[i]).find('#p_' + datatableRowId).prop('checked', false);
	 		$('#publishAll').prop('checked', false); 
	 	 }	
	 }else{
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		$(rows[i]).find('#d_' + datatableRowId).prop('checked', false);
	 		
	 	 }		
		 
	 }
}

var callActionUrl = function (url,fromId) {
	
	$( 'form[id='+fromId+']' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id='+fromId+']' ).attr('method','post');
	$( 'form[id='+fromId+']' ).submit();
};

var viewEntityDetailsInPopup = function (entityCode,entityVersion, cusurl )	{
	/* if( isEmptyObject(entityCode) ){
		$('#customAlertbody').text("No Record(s) found.");
		$('#customAlert').modal('show');
		return false;
	} */
	$('#myIframe').attr('src',  cusurl + "?ward_code="+ entityCode+"&ward_version="+ entityVersion+ "&<csrf:token uri='" + cusurl + "'/>");
	$('#dialogBX').modal('show'); 
}; 

</script>

  <div class="modal fade" id="dialogBX" tabindex="-1" role="dialog" >
									<div class="modal-dialog" style="width:950px;height:700px">
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