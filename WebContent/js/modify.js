var t=1;
var dynstart = 0;
var dynend = 0;


function onLoadHide()
{
	$("#subdistrictname_error").hide();

	}
function addgisnodes1()
{
	
	addTableRows(null,null);
}


var checkDecimalPlaces = function (e){
	var maxPlaces = 3, integer = e.target.value.split('.')[0], mantissa = e.target.value.split('.')[1];
    if (typeof mantissa === 'undefined') {
        mantissa = '';
    }
    if (mantissa.length > maxPlaces) {
        e.target.value = integer + '.' + mantissa.substring(0, maxPlaces);
    }
};  


registerMethod=function(id){
	$("#lati"+id).on('keyup', function(e){
			 	checkDecimalPlaces(e);
			 	 }).numeric({ decimal: ".", negative: false }, function() {
				this.value = ""; this.focus(); 
				//alert("hi");
			});  
	
		 $("#longi"+id).on('keyup', function(e){
			 	checkDecimalPlaces(e);
			 	 }).numeric({ decimal: ".", negative: false }, function() {
				this.value = ""; this.focus(); 
				//alert("hi");
			});  
		 
		
		

 };
 
 
 
 checkLatitudeRange=function(latitudeStr,id){
		if(latitudeStr.length>0){
			var latitude=parseFloat(latitudeStr);
			if(latitude>=6 && latitude<=38){
				$("#error_lati"+id).html("");
				return true;
			}else{
				$("#error_lati"+id).html("Please enter latitude in the range 6,38.");
				return false;
			}
		}else{
			$("#error_lati"+id).html("latitude value required");
			return false;
		}
	};


	checkLongitudeRange=function(longitudeStr,id){
		if(longitudeStr.length>0){
			var longitude=parseFloat(longitudeStr);
			if(longitude>=32 && longitude<=98){
				$("#error_longi"+id).html("");
				return true;
			}else{
				$("#error_longi"+id).html("Please enter longitude in the range 32,98.");
				return false;
			}
		}else{
			$("#error_longi"+id).html("longitude value required");
			return false;
		}
	};





function addTableRows(latitude, longitude) {

	var tbl = document.getElementById('gisNodesTable');
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	row.setAttribute('style', 'height:25px;');

	var cellRight1 = row.insertCell(0);
	var el = document.createElement('input');
	el.type = 'text';
	el.id = 'lati' + lastRow;
	el.name = 'lati';
	el.maxLength = 6;
	if (latitude != null) {
		el.value = latitude;
	}
	el.setAttribute('style', 'width:200px;');
	el.onblur = function() {
		checkLatitudeRange(this.value,lastRow);
	};
	cellRight1.appendChild(el);
	
	var div=document.createElement('div');
	var el = document.createElement('Label');
	el.id = 'error_lati' + lastRow;
	el.setAttribute('style', 'width:200px;');
	el.setAttribute('class', 'errormsg');
	div.appendChild(el);
	cellRight1.appendChild(div);

	var cellRight2 = row.insertCell(1);
	var el = document.createElement('input');
	el.type = 'text';
	el.id = 'longi' + lastRow;
	el.name = 'longi';
	el.maxLength = 6;
	if (longitude != null) {
		el.value = longitude;
	}
	el.setAttribute('style', 'width:200px;');
	el.onblur = function() {
		checkLongitudeRange(this.value,lastRow);
	};
	cellRight2.appendChild(el);
	
	var div=document.createElement('div');
	
	var el = document.createElement('Label');
	el.id = 'error_longi' + lastRow;
	el.setAttribute('style', 'width:200px;');
	el.setAttribute('class', 'errormsg');
	div.appendChild(el);
	cellRight2.appendChild(div);

	var cellRight3 = row.insertCell(2);
	var el = document.createElement('input');
	el.type = 'button';
	el.value = 'Remove';
	el.onclick = function() {
	var rownum = this.parentNode.parentNode.rowIndex;
	tbl.deleteRow(rownum);
	};
	cellRight3.appendChild(el);
	registerMethod(lastRow);
};


gisNodesMismatch=function(){
	 var status = true;
    var lonArr = [];
		$("[name=longi]").each(function(index, obj){
			if(! $_checkEmptyObject($(this).val())){
				lonArr.push($(this).val());
			}
		});	
		var latArr = [];
		$("[name=lati]").each(function(index, obj){
			if(! $_checkEmptyObject($(this).val())){
				latArr.push($(this).val());
			}	
		});	
		if(lonArr.length != latArr.length){
			status = false;
		}
    return status;
};

checkLatitudeLongitudeRange=function(){
	 var statusLati = true;
	 var statusLongi = true;

		$("[name=longi]").each(function(index, obj){
			if(! $_checkEmptyObject($(this).val())){
				statusLati=checkLongitudeRange($(this).val(),index);
			}
		});	

		$("[name=lati]").each(function(index, obj){
			if(! $_checkEmptyObject($(this).val())){
				statusLongi=checkLatitudeRange($(this).val(),index);
			}	
		});	
		
   return (statusLati && statusLongi);
};







/**
 * The {@code $_checkEmptyObject} used to check object / element  
 * is empty or undefined.
 */
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

buildLatitudeLongitude=function(cordinates){
	var cordinateArr = cordinates.split(',');
	$.each( cordinateArr, function( index, value ){
	   cordinate= value.split(':');
	   addTableRows(cordinate[0],cordinate[1]);
	});
	 
};



