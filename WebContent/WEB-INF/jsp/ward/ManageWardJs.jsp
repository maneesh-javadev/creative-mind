
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
	 $('#demand1').dataTable({
		 "sScrollY": "200px",
			"iDisplayLength": 50,
			"bScrollCollapse": true,
			"bPaginate": true,
			
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [
							null,
							null,
							null,
							null,								
				  			],
			"sPaginationType": "full_numbers"
		});
	

	 $('#demand').dataTable({
			"sScrollY": "200px",
			"iDisplayLength": 50,
			"bScrollCollapse": true,
			"bPaginate": true,
			
			"bJQueryUI": true,
			"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0,1,2,3,4,5] } ],
			"aaSorting": [],
			"aoColumns": [
							null,
							null,
							null,
							null,
							null,
							null,
				  			],
			"sPaginationType": "full_numbers"
		});
		$('#demand_wrapper').css("min-height", "32px");
		$('#demand1_wrapper').css("min-height", "32px");
		$('#demand2_wrapper').css("min-height", "32px");
		

	 $('#demand2').dataTable({
		 "sScrollY": "200px",
			
			"bScrollCollapse": true,
			"bPaginate": true,
			
			"bJQueryUI": true,
			"aaSorting": [],
			"aoColumns": [								
							null,
							null,
							null,
							null,
							null,
				  			],
			"sPaginationType": "full_numbers"
		});
	 
	 
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
		
		callActionUrl('viewWardPRIAndTRI.htm','localGovtBodyForm');
			
	 });
});
	 

var callActionUrl = function (url,fromId) {
	
	$( 'form[id='+fromId+']' ).attr('action', url +'?<csrf:token uri="'+ url +'"/>');
	$( 'form[id='+fromId+']' ).attr('method','post');
	$( 'form[id='+fromId+']' ).submit();
};


function fetchWardValues(){
	 var checkAll=$('#publishAll').is( ':checked' );
	 if( checkAll ){
		 
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#p' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#p' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }
	 		 /* if(!$(rows[i]).find('#u' + datatableRowId).is( ':disabled' )){
	 			var unfreezeChecked = $(rows[i]).find('#u' + datatableRowId).is(":checked");
	 		    if(unfreezeChecked) {
	 		       		var dstring   = datatableRowId;
	 					dstring  += "_" + "U";
	 					dstring  += "_" + reason + ":";
	 				    array_tableData	+= dstring;        	 
	 		           }
	 		 } */
	                
	      }
		 
		 
	 	 $('#publishWardsList').val(array_tableData);
		 $('#publishAllWard').val("Yes");
		 $('#manage').val("manage");
			callActionUrl('publishWardAll.htm','publishWardForm');
	 }else{
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#p' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#p' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }
	 		 /* if(!$(rows[i]).find('#u' + datatableRowId).is( ':disabled' )){
	 			var unfreezeChecked = $(rows[i]).find('#u' + datatableRowId).is(":checked");
	 		    if(unfreezeChecked) {
	 		       		var dstring   = datatableRowId;
	 					dstring  += "_" + "U";
	 					dstring  += "_" + reason + ":";
	 				    array_tableData	+= dstring;        	 
	 		           }
	 		 } */
	                
	        }
	 	
	 	if(array_tableData==""){
			 customAlert("Kindly Select any Ward To publish");
			 return false;
		 }
	 	
	 	$('#publishWardsList').val(array_tableData);
	 	$('#manage').val("manage");
		callActionUrl('publishWardAll.htm','publishWardForm');
	 }
	
}


restoreWard=function(restorWardCode,restorWardVersion,userId,element){
	
	lgdDwrWardService.restoreDeleteWard(restorWardCode,restorWardVersion,userId,{
		callback : function(result) {
			
		if(result=="C" || result=="N")
		{
			restoredWardTable = $('#demand2').dataTable();
		    publishedWardTable = $('#demand1').dataTable();
			
		    var row = restoredWardTable.fnGetData(element.index());
		    pubWardLen=pubWardLen+1;
		    row[0]=pubWardLen;
		    
		    publishedWardTable.fnAddData(row);
		    
		    
		    restoredWardTable.fnDeleteRow(element.index(),null,true);
		    if(result=="C"){
		    	 customAlert("ward restored successfully");
		    }if( result=="N"){
		    	customAlert("ward restored but ward coverage is currently unavilable.kindly map the ward again");
		    }
		    
		    
		   
		}	
		else
			{
			customAlert("ward restore not successfully");
			}
			
		},
		async : false
	});
	
	
	//alert("restorWardCode:"+restorWardCode);
	//alert(element.index());
	
    
	/* jQuery('#'+delWardID).remove();
	DataTable = $('#demand2').dataTable;
	DataTable._fnAjaxUpdate();
    DataTable.fnDraw();
    //DataTable.fnReloadAjax();
    DataTableFun(); */
};


function selectWard(id) {
	var check = $('#p' + id).is(':checked');
	if (check) {
		$('#d' + id).prop('checked', false);
		$('#deleteAll').prop('checked', false);
	} else {
		$('#publishAll').prop('checked', false);
	}
}

function deleteWard(id) {
	var check = $('#d' + id).is(':checked');
	if (check) {
		$('#p' + id).prop('checked', false);
		$('#publishAll').prop('checked', false);
	} else {
		$('#deleteAll').prop('checked', false);
	}
}


function deleteAllWards() {
	 var checkAllDelete= $('#deleteAll').is( ':checked' );
	 if( checkAllDelete ){
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {	 		 
	 		var datatableRowId = $(rows[i]).attr( 'id' );
	 		$(rows[i]).find('#d' + datatableRowId).prop('checked', true);
	 		$(rows[i]).find('#p' + datatableRowId).prop('checked', false);
	 		$('#publishAll').prop('checked', false); 
	 	 }	
	 }else{
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		$(rows[i]).find('#d' + datatableRowId).prop('checked', false);
	 		
	 	 }		
		 
	 }
}

function deleteWardValues() {
	 var checkAll=$('#deleteAll').is( ':checked' );
	 if( checkAll ){
		 
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#d' + datatableRowId).is( ':disabled' )){
	 			 var deleteChecked = $(rows[i]).find('#d' + datatableRowId).is( ':checked' );
	 	         if( deleteChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }        
	      }
		 
		 
	 	 $('#deleteWardsList').val(array_tableData);
		 $('#deleteAllWardTemp').val("Yes");
		 $('#manage').val("manage");
			callActionUrl('publishWardAll.htm','publishWardForm');
	 }else{
		 var array_tableData = "";
	 	 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 if(!$(rows[i]).find('#d' + datatableRowId).is( ':disabled' )){
	 			 var publishChecked = $(rows[i]).find('#d' + datatableRowId).is( ':checked' );
	 	         if( publishChecked ){
	 		        	var dstring	= datatableRowId+","; 		        	       	 
	 		        	array_tableData	+= dstring;        	
	 			 	}
	 		 }	                
	        }
	 	
	 	 if(array_tableData==""){
			 customAlert("Kindly Select any Ward To Delete");
			 return false;
		 }
	 	 $('#deleteWardsList').val(array_tableData);
	 	 $('#manage').val("manage");
	 	 $('#deleteAllWardTemp').val("Yes");	 
	 	 
	 	callActionUrl('publishWardAll.htm','publishWardForm');
	 	
	 }
	 
}

function selectAll(){
	 var checkAll	   = $('#publishAll').is( ':checked' );
	 
	 if( checkAll ){
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		$(rows[i]).find('#p' + datatableRowId).prop('checked', true);
	 		$(rows[i]).find('#d' + datatableRowId).prop('checked', false);
	 		$('#deleteAll').prop('checked', false);
	 	 }		 
	 }else{
		 var oTable = $( '#demand' ).dataTable();
	 	 var rows = oTable.dataTable().fnGetNodes();
	 	 for(var i = 0; i < rows.length; i++) {
	 		 var datatableRowId = $(rows[i]).attr( 'id' ); 
	 		 $(rows[i]).find('#p' + datatableRowId).prop('checked', false);
	 	 }		 
	 }
	
	 
}
</script>