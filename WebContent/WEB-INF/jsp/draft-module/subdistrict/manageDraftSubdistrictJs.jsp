<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/dwrDraftSubdistrictService.js'></script>
<style>
table.data_child{

font-size:12px;
color:#555;
}

table.data_child tr th, table.data_child tr td{
border-left:none;
border-bottom:none;
border-top:none;
border-right:none;
padding:8px;
text-align:left;
transition:all ease-in-out 0.2s;
}

td.details-control {
    background: url('images/expend.png') no-repeat center center;
    background-size: 18px 19px;
    cursor: pointer;
}
tr.shown td.details-control {
    background: url('images/collapse.png') no-repeat center center;
    background-size: 18px 19px;
}
</style>
<script>
$(document).ready(function() {
	var  deleteFlag= isParseJson( '${draftSubdistrictForm.deleteFlag}' );
		if(deleteFlag){
			$("#customAlertbody").text("Subdistrict in Draft delete successfully");
			$("#customAlert").modal('show');
			$("#yes").hide();
			
			/* $("#cAlert").dialog({
				title : 'Draft Subdistrict',
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			}); */
		}
	
	var table = $('#example').DataTable( {
	} );

	$("#example").on('click', 'td.details-control', function() {
	  	var tr = $(this).closest('tr');
	    var row = table.row( tr );
	    if ( row.child.isShown() ) {
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
           idStr=$(tr).attr('id');
     	  	id=idStr.split("_")[1]
          	fetchMultipleSubdistrict(id,row);
     	   tr.addClass('shown');
        }
        	
        
	  
	 
	  
      
	});  
});

    var fetchMultipleSubdistrict= function  (id,row) {
    	dwrDraftSubdistrictService.getDraftSubdistrictListbyGroupId(parseInt(id), {
			callback : function( result ) {
					
				var tableTemp = $("<TABLE/>");
				tableTemp.addClass('data_child');
				tableTemp.attr("width", "100%" );
				tableTemp.attr("id", "childtable" );
				// var tableDef="<table width='100%' class='data_child'  >";
				jQuery.each(result, function(index, obj) {
					
					 var trTemp = $("<TR/>");
						
					 var tdTemp=$("<TD/>");
					 tdTemp.attr("width", "4%" );
					 
					 var tdTemp1=$("<TD/>");
					 tdTemp1.attr("width", "40%" );
					 var templateLabel = $("<label/>");
					 templateLabel.html(obj.subdistrictNameEnglish);
					 tdTemp1.append(templateLabel);
					 
					 var tdTemp2=$("<TD/>");
					 tdTemp2.attr("width", "20%" );
					 
					 var tdTemp3=$("<TD/>");
					 tdTemp3.attr("width", "7%" );
					 
					 var tdTemp4=$("<TD/>");
					 tdTemp4.attr("width", "7%" );
					 tdTemp4.attr("align", "center" );
					 
					 var templateAnchor = $("<A/>");
					 templateAnchor.attr("href", "#" );
					 templateAnchor.attr("onclick", "processLinkActions('"+obj.draftCode+"','"+obj.subdistrictNameEnglish+"','editDraftSubdistrict.htm');" );
					
					 var imgTemp=$('<IMG />'); 
					 imgTemp.attr("src","images/edit_icon.png");
					 imgTemp.attr("width","18");
					 imgTemp.attr("height","19");
					 imgTemp.attr("border","0");
					 templateAnchor.append(imgTemp);
					 tdTemp4.append(templateAnchor);
					 
					 var tdTemp5=$("<TD/>");
					 tdTemp5.attr("width", "7%" );
					 
					 var tdTemp6=$("<TD/>");
					 tdTemp6.attr("width", "7" );
					 
					 var tdTemp7=$("<TD/>");
					 tdTemp7.attr("width", "7%" );
					 
					 trTemp.append(tdTemp);
					 trTemp.append(tdTemp1);
					 trTemp.append(tdTemp2);
					 trTemp.append(tdTemp3);
					 trTemp.append(tdTemp4);
					 trTemp.append(tdTemp5);
					 trTemp.append(tdTemp6);
					 trTemp.append(tdTemp7);
					 
					 tableTemp.append(trTemp);
				}); 
				
				var tab1=tableTemp[0];
				// alert(tab1.outerHTML);
				row.child(tab1.outerHTML).show();
				
			},
			errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
			async : true
		}); 
    	
 };




callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=draftSubdistrictForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=draftSubdistrictForm]' ).attr('method','post');
	$( 'form[id=draftSubdistrictForm]' ).submit();
};

var processLinkActions = function (paramCode,paramName,url){
	//alert("inbox");
	//deltabPos=$("#reactiveTable").position();
	//alert(deltabPos.left())
	$('#paramCode').val(paramCode);
	
	if(url=="deleteSection.htm"){
		 $("#customAlertbody").text('Are you sure to delete '+paramName+' Draft subdistrict');
		 $("#customAlert").modal('show');
		 
		 $('#yes').click(function() {
			   callActionUrl(url);
			   $("#customAlert").modal('hide');
			   
			});
		 
		/* 	$("#dialog-confirm").dialog({
		        resizable: false,
		        modal: true,
		        title: "Section Delete confirm",
		        height: 150,
		        width: 300,
		        buttons: {
		            "Yes": function () {
		                $(this).dialog('close');
		                callActionUrl(url);
		                
		            },
		                "No": function () {
		                $(this).dialog('close');
		                
		                
		            }
		        },
		        position: "center"
		    }); */
	}else{
		callActionUrl(url);
	}
	
	
};

</script>