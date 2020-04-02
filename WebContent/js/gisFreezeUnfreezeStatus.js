
var map, dialog,sdata=null,ddata=null,entityType=null;
var startExtent = null;		
	

require([
"esri/map", "esri/layers/FeatureLayer", "esri/tasks/QueryTask", "esri/graphicsUtils","esri/layers/GraphicsLayer",
"esri/graphic","esri/symbols/Font", "esri/symbols/TextSymbol",
"esri/tasks/query","esri/InfoTemplate", "esri/symbols/SimpleMarkerSymbol", 
"esri/symbols/SimpleLineSymbol", "esri/symbols/SimpleFillSymbol",
"esri/renderers/UniqueValueRenderer","esri/lang", "esri/Color", "dojo/dom-style",
"dijit/TooltipDialog", "dijit/popup", "dojo/on",  "dojo/domReady!"

], function(
	Map, FeatureLayer,QueryTask,graphicsUtils,GraphicsLayer,Graphic,Font,TextSymbol, Query, InfoTemplate,
	SimpleMarkerSymbol,SimpleLineSymbol, SimpleFillSymbol,
	UniqueValueRenderer,esriLang, Color, domStyle,
	TooltipDialog, dijitPopup,on
  ) {
	
	startExtent = new esri.geometry.Extent(2664790.7619840866, 9783.939620515332, 15902461.068520531, 5263759.515828993, new esri.SpatialReference({
	wkid : 102100
	}));


  

    dialog = new TooltipDialog({
        id: "tooltipDialog",
        style: "position: absolute; width: 250px; font: normal normal normal 10pt Helvetica;z-index:100"
    });
    dialog.startup();

    highlightSymbol = new SimpleFillSymbol(
    SimpleFillSymbol.STYLE_SOLID,
    new SimpleLineSymbol(
    SimpleLineSymbol.STYLE_SOLID,
    new Color([255, 0, 0]), 3),
    new Color([125, 125, 125, 0.35]));

	var StateDispGraphicLayer = new GraphicsLayer();
	var StateDispLabelGraphicLayer = new GraphicsLayer();
	var DistrictDispGraphicLayer = new GraphicsLayer();
	var DistrictDispLabelGraphicLayer = new GraphicsLayer();

	map = new Map("map", {
		basemap: "streets",
		center: [78,21],
		extent : startExtent,
		slider: false,
		logo: false,  
	});
       
	map.on("load", function(){
		var a = map.layerIds;			        	
		var b = map.getLayer(a);
		map.removeLayer(b);
		map.graphics.enableMouseEvents();
		map.disableScrollWheelZoom();
		map.disableDoubleClickZoom();
		map.disablePan();
		map.disableKeyboardNavigation();
		map.infoWindow.resize(245, 125);
		addFeatureLayer();
	});        
       
		
	function addFeatureLayer() {
		
		map.removeAllLayers();
		
		
		if(sdata!=null){
			var query = new Query();
			query.where = "1=1";
			query.outFields = ["stname,State_LGD"];
			query.returnGeometry = true;
			var queryTask = new QueryTask("https://webgis.nic.in/publishing/rest/services/panchayat/admin17_lgd/MapServer/0?token="+tokenval);
			queryTask.execute(query,showResultsState,errCallback);
		}else{
			lgdDwrStateService.getEntityFreezeStatus({
				callback : function(result) {
					sdata=result;	
					$("#loader").show();
					
					var query = new Query();
					query.where = "1=1";
					query.outFields = ["stname,State_LGD"];
					query.returnGeometry = true;
					var queryTask = new QueryTask("https://webgis.nic.in/publishing/rest/services/panchayat/admin17_lgd/MapServer/0?token="+tokenval);
					queryTask.execute(query,showResultsState,errCallback);
					},
					errorHandler : function( errorString, exception){
					alert(exception);
					},
				async : true
			});
		}
		
		
        	
    }
		 
		 
	function showResultsState(featureSet) { 

		

		var i, featureSetLength = featureSet.features.length;
		var filledColorSymbol3 = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(
			esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color("#CC9999"), 1), new dojo.Color([ 255, 255, 204, 0.5 ]));
		var StategraphicArray = [];
		var StateFeatures = featureSet.features;
	

		

		for (i = 0; i < featureSetLength; i++) {
			// Get the current feature from the featureSet. Feature is a graphic
			var StFeature = StateFeatures[i];
			var Stgraphic = new Graphic(StFeature.geometry, filledColorSymbol3 , StFeature.attributes);
			StategraphicArray.push(Stgraphic);
			

			var slc=StFeature.attributes["State_LGD"];
			obj=searchObjFnState(slc);
			var freezeColor = new Color([ 0,	204	,0, 0.7]);  
			var PartialFreezeColor = new Color([229,214,95, 0.7]);  
			var UnfreezeColor = new Color([204	,38,	60, 0.9]);
			var setStatusColor=UnfreezeColor;
			
			if( obj.status=='F'){
				setStatusColor=freezeColor;
			}else if( obj.status=='P'){
				setStatusColor=PartialFreezeColor;
			}else if( obj.status=='U'){
				setStatusColor=UnfreezeColor;
			}
			
			StFeature.setSymbol( new SimpleFillSymbol().setColor(setStatusColor));
			var StateNameAttr = StFeature.attributes["stname"];
			var StlabelPoint = StFeature.geometry.getCentroid();
			var Stlabelfont = new Font("8px", Font.STYLE_NORMAL, Font.VARIANT_NORMAL, Font.WEIGHT_BOLDER);
			var StlabelTextSymbol = new TextSymbol(StateNameAttr, Stlabelfont, new Color([0, 0, 255 ]));
			var StlabelGraphic = new Graphic(StlabelPoint, StlabelTextSymbol);
			
			StateDispGraphicLayer.add(StFeature);
			StateDispLabelGraphicLayer.add(StlabelGraphic);
			
		}
		
		var StExtent = graphicsUtils.graphicsExtent(StategraphicArray);
			map.setExtent(StExtent.expand(1.5), 1.5);	
			map.addLayer(StateDispGraphicLayer);
			map.addLayer(StateDispLabelGraphicLayer);
			entityType='S';
			StateDispGraphicLayer.on("click", districtMapShow);
			StateDispGraphicLayer.on("mouse-over", showtooltip);
			//StateDispGraphicLayer.on("mouse-out", closeDialog);
			$("#loader").hide();
	}
			 
       
		
	function showtooltip(evt){
	flag=true;
		if(entityType=='S'){
			var slc=(evt.graphic['attributes'])['State_LGD'];
			obj=searchObjFnState(slc);
			
			var arrRow = obj.entitiesStatus.split('#');
			var msg="<b>Freeze/Unfreeze status of ${stname} State</b><hr>"
		
			msg=msg+"<table id='showgis'><tr id='staticRow'>" ;
			
			
			$.each( arrRow, function( index, value ) {
				if(value!=""){
					arrCol=value.split("@");
					msg=msg+"<td class='entity_status   ";
					if(arrCol[1]=='F'){
						status=" freeze'";	
					}else if(arrCol[1]=='P'){
						status=" pfreeze'";	
					}else{
						status=" unfeeze'";	
					}
				
					switch(arrCol[0]){
						case 'R':entity="Revenue";break;
						case 'E':entity="Consitutency";break;
						case 'B':entity="Block";break;
						case 'P':entity="Panchayat Localbody";break;
						case 'U':entity="Urban Localbody";break;
						case 'T':entity="Traditional Localbody";break;
					}
				
						msg=msg+status+"></td><td>&nbsp;&nbsp;&nbsp;&nbsp;<label>"+entity+"</td>";
						if(index>=2 && flag){
							flag=false;
							msg=msg+"</tr><tr id='dynamicRow'>";
						}
				}
			
			});
			msg=msg+"</tr></table>";
			}else if(entityType=='D') {
				 var dlc=(evt.graphic['attributes'])['Dist_LGD'];
				obj=searchObjFnDistrict(dlc);
				
				
				var arrRow = obj.entitiesStatus.split('#');
				var msg="<b>Freeze/Unfreeze status of ${dtname} District,${stname} State</b><hr>"
				
				msg=msg+"<table id='showgis'><tr id='staticRow'>" ;
				
				
				$.each( arrRow, function( index, value ) {
					if(value!=""){
						arrCol=value.split("@");
						msg=msg+"<td class='entity_status   ";
						if(arrCol[1]=='F'){
							status=" freeze'";	
						}else if(arrCol[1]=='P'){
							status=" pfreeze'";	
						}else{
							status=" unfeeze'";	
						}
					
						switch(arrCol[0]){
							case 'R':entity="Revenue";break;
							case 'E':entity="Consitutency";break;
							case 'B':entity="Block";break;
							case 'P':entity="Panchayat Localbody";break;
							case 'U':entity="Urban Localbody";break;
							case 'T':entity="Traditional Localbody";break;
						}
					
							msg=msg+status+"></td><td>&nbsp;&nbsp;&nbsp;&nbsp;<label>"+entity+"</td>";
							if(index>=2 && flag){
								flag=false;
								msg=msg+"</tr><tr id='dynamicRow'>";
							}
					}
				
				});
				
				
				
				
				
			}

	        var content = esriLang.substitute(evt.graphic.attributes, msg);
	        evt.graphic.symbol = highlightSymbol;

	        dialog.setContent(content);
	        
	       

	        domStyle.set(dialog.domNode, "opacity", 0.85);
	        dijitPopup.open({
	            popup: dialog,
	            x: evt.pageX,
	            y: evt.pageY
	        });
		
		
	}
		
	function closeDialog() {
	 // evt.graphic.symbol = symbol;
     map.graphics.clear();
        dijitPopup.close(dialog);     
	}
			
	function districtMapShow(evt){
		 map.graphics.clear();
	     dijitPopup.close(dialog);     
		$("#loader").show();
		map.removeAllLayers();
		DistrictDispGraphicLayer.clear();
		DistrictDispLabelGraphicLayer.clear();
		 var stateCode=(evt.graphic['attributes'])['State_LGD'];
		
		lgdDwrStateService.getEntityFreezeStatus(stateCode, {
			callback : function(result) {
				ddata=result;
				 var queryTask = new esri.tasks.QueryTask("https://webgis.nic.in/publishing/rest/services/panchayat/admin17_lgd/MapServer/1?token="+tokenval);
				 var query = new esri.tasks.Query();
					  
					query.returnGeometry = true;
					query.outFields = [ "*" ];
					query.where = "state_LGD="+stateCode;  
					query.outSpatialReference = {
						"wkid" : 4326
					};
					queryTask.execute(query,showResults,errCallback);
			},
			errorHandler : function( errorString, exception){
				alert(exception);
			},
			async : true
		});
		
	 } 
			
	function showResults(featureSet) { 
		$("#btnBack").show();
		
		

		var i, featureSetLength = featureSet.features.length;
	
		var filledColorSymbol3 = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(
			esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color("#CC9999"), 1), new dojo.Color([ 255, 255, 204, 0.5 ]));

		var DistrictgraphicArray = [];
		var DistrictFeatures = featureSet.features;
			
		for (i = 0; i < featureSetLength; i++) {
			// Get the current feature from the featureSet. Feature is a graphic
			var StFeature = DistrictFeatures[i];
			var Stgraphic = new Graphic(StFeature.geometry, filledColorSymbol3 , StFeature.attributes);
			DistrictgraphicArray.push(Stgraphic);

			var dlc=StFeature.attributes["Dist_LGD"];
			obj=searchObjFnDistrict(dlc);
			var freezeColor = new Color([ 0,	204	,0, 0.7]);  
			var PartialFreezeColor = new Color([229,214,95, 0.7]);  
			var UnfreezeColor = new Color([204	,38,	60, 0.9]);
			var setStatusColor=UnfreezeColor;
			
			if(obj!=null){					
				if( obj.status=='F'){
					setStatusColor=freezeColor;
				}else if( obj.status=='P'){
					setStatusColor=PartialFreezeColor;
				}else if( obj.status=='U'){
					setStatusColor=UnfreezeColor;
				}
			}else{
				setStatusColor=UnfreezeColor;

			}

			StFeature.setSymbol( new SimpleFillSymbol().setColor(setStatusColor));
			var StateNameAttr = StFeature.attributes["dtname"];
			var StlabelPoint = StFeature.geometry.getCentroid();
			var Stlabelfont = new Font("6px", Font.STYLE_NORMAL, Font.VARIANT_NORMAL, Font.WEIGHT_BOLDER);
			var StlabelTextSymbol = new TextSymbol(StateNameAttr, Stlabelfont, new Color([0, 0, 255 ]));
			var StlabelGraphic = new Graphic(StlabelPoint, StlabelTextSymbol);
			DistrictDispGraphicLayer.add(StFeature);
			DistrictDispLabelGraphicLayer.add(StlabelGraphic);
		}
	var StExtent = graphicsUtils.graphicsExtent(DistrictgraphicArray );
	map.setExtent(StExtent.expand(1.5), 1.5);		  
	map.addLayer(DistrictDispGraphicLayer);
	map.addLayer(DistrictDispLabelGraphicLayer);
	entityType='D';
	DistrictDispGraphicLayer.on("mouse-over", showtooltip);
	DistrictDispGraphicLayer.on("mouse-out", closeDialog);
	$("#loader").hide();
	}  
															
	 function errCallback(error) {  
		alert("error in");
		
	 }
			 
	on(dojo.byId('btnBack'), 'click', function(){  
		$("#btnBack").hide();  
		$("#loader").show();
		map.removeAllLayers();
		addFeatureLayer();
	});  
});
	  
var searchObjFnState=function(entityCode){
			 //alert(stateCode);
	 var dobj=null;
	 jQuery.each(sdata,function(index,obj) {
		 if(obj.entityCode==entityCode){
			 dobj=obj;
			 
		 }
	 });
	 return dobj;
} 
	  
var searchObjFnDistrict=function(entityCode){
	 //alert(stateCode);
var dobj=null;
jQuery.each(ddata,function(index,obj) {
if(obj.entityCode==entityCode){
	 dobj=obj;
	 
}
});
return dobj;
} 
	  
	
	  