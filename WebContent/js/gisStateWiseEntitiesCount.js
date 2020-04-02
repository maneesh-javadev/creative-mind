






 $(document).ready(function() {
	 
	lgdDwrInitialService.getDataFromJsonFile( {
 		callback : function(result) {
					sdata=result;
					
					var palette = ['#d6cbd3','#d5e1df','#eca1a6','#e3eaa7','#bdcebe','#b5e7a0','#e6e2d3','#92a8d1','#deeaee','#f7cac9','#eea29a',' #f7786b','#c94c4c',
						'#d5f4e6','#ffef96','#80ced6',' #fefbd8','#b2b2b2','#618685','#f4e1d2'],
				      generateColors = function(){
				        var colors = {},
				            key;

				        for (key in map.regions) {
				        	console.log(key);
				          colors[key] = palette[Math.floor(Math.random()*palette.length)];
				          //colors[key] = new Color([Math.floor(Math.random() * 255)	,Math.floor(Math.random() * 255),	Math.floor(Math.random() * 255), 0.4]);
							
				        }
				        return colors;
				      },
				      map;
					
					/*var templateParent = $('.india-map');
		            if (templateParent.length === 0) {
		                console.log('No element found to insert map');
		                return;
		            }*/
		            
		            map = new jvm.Map({
		                map: 'india_en4',
		                container: $('.india-map'),
		                zoomButtons : false,
		                showTooltip: true,
		                backgroundColor: '#336699',
		                series: {
		                  regions: [{
		                    attribute: 'fill'
		                  }]
		                },
		                onRegionTipShow: function (e, el, code) {   el.html(el.html()+searchObjFnState(code)); }
		              });
		            map.series.regions[0].setValues(generateColors());
		            
		            /*var map=templateParent.vectorMap({
		                map: 'india_en4',
		                series:{
		                    regions: [{
		                        attribute: 'fill'
		                      }]
		                    },
		                backgroundColor: '#336699',
		                color: '#7097F0',
		                hoverOpacity: 0.7,
		                selectedColor: '#1de9b6',
		                enableZoom: true,
		                showTooltip: true,
		                normalizeFunction: 'polynomial',
		                onRegionTipShow: function (e, el, code) {   el.html(el.html()+searchObjFnState(code)); }
		            });*/
		            window['map']=map;
		            console.log(map);
				
				
				},
				errorHandler : function( errorString, exception){
				alert(exception);
				},
			async : true
		});	
	 
            
  
  });


 
 var searchObjFnState=function(entityCode){
	 //alert(stateCode);
var msg="";
jQuery.each(sdata,function(index,obj) {
 if(obj.state_code==entityCode){
	
		msg=msg+"<table id='showgis'>" ;
		msg=msg+"<tr id='staticRow'><td>No. of Districts:<label></td><td align='right'>"+obj.no_of_districts+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of District Panchayat:<label></td><td  align='right'>"+obj.no_of_zps+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Sub-districts:<label></td><td  align='right'>"+obj.no_of_subdistricts+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Blocks:<label></td><td  align='right'>"+obj.no_of_blocks+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Intermediate Panchayat:<label></td><td  align='right'>"+obj.no_of_bps+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Villages:<label></td><td  align='right'>"+obj.no_of_villages+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Village Panchayat:<label></td><td  align='right'>"+obj.no_of_vps+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Traditional Bodies:<label></td><td  align='right'>"+obj.no_of_tlbs+"</label></td></tr>";
		msg=msg+"<tr id='staticRow'><td>No. of Urban Bodies:<label></td><td  align='right'>"+obj.no_of_ulbs+"</label></td></tr>";
	 
 }
});
return msg;
} 

	  