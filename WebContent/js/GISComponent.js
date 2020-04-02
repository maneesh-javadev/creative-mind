

var map, dialog,sdata=null,ddata=null,entityType=null;
var startExtent = null;		
var isVillageFlag=false;	

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
    var VillageHighlightGraphicLayer=new GraphicsLayer();
	map = new Map("map", {
		basemap: "streets",
		center: [78,21],
		extent : startExtent,
		slider: true,
		logo: false, 
		zoom: 5,
	});
       
	map.on("load", function(){
		var a = map.layerIds;			        	
		var b = map.getLayer(a);
		map.removeLayer(b);
		map.graphics.enableMouseEvents();
		map.disableScrollWheelZoom();
		map.disableDoubleClickZoom();
		//map.disablePan();
		map.disableKeyboardNavigation();
		map.infoWindow.resize(245, 125);
		isVillageFlag=false;
		getReportDataToMap();
	}); 
	
	function getReportDataToMap() {
		var levelCode = $('#lvCode').val();
		var localGovBodyType = $('#localGovBodyType').val();
		var vpFlag = $('#vpFlag').val();
		var stateCode = $('#stateCode').val();
	    
		

		var parentStr = $('#parentObject').val();
		var paramId=$('#inParam').val();
		var subDistCensus2011Code=$('#subDistCensus2011Code').val();
		if(subDistCensus2011Code!=null && subDistCensus2011Code!=""){
			isVillageFlag=true;
		}	
			//displayLoadingImage();
			
			districtMapShow(baseURL,paramName,paramId,token);
			

		
	}
		
		
		function districtMapShow(baseURL,paramName, param,token){
			 map.graphics.clear();
		          
			//$("#loader").show();
			map.removeAllLayers();
			DistrictDispGraphicLayer.clear();
			DistrictDispLabelGraphicLayer.clear();
			VillageHighlightGraphicLayer.clear();
			var whereClause =paramName + "="+param;
			if(paramName=="sdtcode11" || paramName=="gp_code"){
				if(isVillageFlag){
				whereClause =paramName + "='"+$('#subDistCensus2011Code').val()+"'";

				}else{

			 	whereClause =paramName + "='"+param+"'";
				}
			}
			var querystring = baseURL+"?token="+token;
			
			///var querystring ="https://mapservice.gov.in/gismapservice/rest/services/BharatMapService/GramPanchayat_Boundary/MapServer/0?token=1jPI1erK8ncivdA-qtrjTVI3VZ1S6wkQyisuPupxMlg1Cys8j4PHpIcyrbaAIgUrqNq-XzG240Pj-b5hOzO5DQ..";
			//whereClause ="gp_code='195776'";
			
			
			var queryTask = new esri.tasks.QueryTask(querystring);
			var query = new esri.tasks.Query();
						  
						query.returnGeometry = true;
						query.outFields = [ "*" ];
						query.where = whereClause;  
						query.outSpatialReference = {
							"wkid" : 4326
						};
						queryTask.execute(query,showResults,errCallback);
				
			
		 } 
				
		function showResults(featureSet) { 
			var i, featureSetLength = featureSet.features.length;
			if(featureSetLength==0){
				alert("entity not avilable in GIS Record");
			}else{
				var filledColorSymbol3 = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color("#CC9999"), 1), new dojo.Color([ 255, 255, 204, 0.5 ]));

					var DistrictgraphicArray = [];
					var DistrictFeatures = featureSet.features;
						
					for (i = 0; i < featureSetLength; i++) {
						// Get the current feature from the featureSet. Feature is a graphic
						var StFeature = DistrictFeatures[i];
						var Stgraphic = new Graphic(StFeature.geometry, filledColorSymbol3 , StFeature.attributes);
						DistrictgraphicArray.push(Stgraphic);
						var freezeColor = new Color([ 233,	242	,253, 0.7]);  
						StFeature.setSymbol( new SimpleFillSymbol().setColor(freezeColor));
						var StateNameAttr = StFeature.attributes[attributeName];
						var StateCodeAttr = StFeature.attributes[attributeCodeName];
						var StlabelPoint = StFeature.geometry.getCentroid();
						var Stlabelfont = new Font("8px", Font.STYLE_NORMAL, Font.VARIANT_NORMAL, Font.WEIGHT_BOLDER);
						var StlabelTextSymbol = new TextSymbol((StateNameAttr+"("+StateCodeAttr+")"), Stlabelfont, new Color([0, 0, 255 ]));
						var StlabelGraphic = new Graphic(StlabelPoint, StlabelTextSymbol);
					if(isVillageFlag){
						 var highlightSymbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
					                  new Color([255, 0, 0]), 3), new Color([125, 125, 125, 0.35]));
						villageCode= StFeature.attributes['VILCODE11'];
						selVillageCode=$('#inParam').val();
						if(selVillageCode==villageCode){
							var highlightGraphic = new Graphic(StFeature.geometry,highlightSymbol);
							VillageHighlightGraphicLayer.add(highlightGraphic);
						isVillageFlag=false;
						}
						
						
					}
						
					

						
						DistrictDispGraphicLayer.add(StFeature);
						DistrictDispLabelGraphicLayer.add(StlabelGraphic);
					}
					if(isVillageFlag){
						alert("entity not avilable in GIS Record");
					}else{
						var StExtent = graphicsUtils.graphicsExtent(DistrictgraphicArray );
						map.setExtent(StExtent, true);		  
						map.addLayer(DistrictDispGraphicLayer);
						map.addLayer(DistrictDispLabelGraphicLayer);
						map.addLayer(VillageHighlightGraphicLayer);
					}
				
			}
	
		}  
																
		 function errCallback(error) {  
			console.log("error in:"+error);
			
		 }
       
		
	
});
 
	
	