
<html>  
  
<head>  
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">  
  <title></title>  
  <style>  
    html,  
    body,  
    #map {  
      padding: 0;  
      margin: 0;  
     height: 90%;   
      
    } 
    
   
    
    .dijitTooltipContainer {
	    border: solid #cab525 2px !important;
	    background: #eaecf1 !important;
	    color: #131313;
	    font-size: small;
	}
     /* Center the loader */
.loading {
  position: absolute;
  top: 50%;
  left: 50%;
}
.loading-bar {
  display: inline-block;
  width: 4px;
  height: 18px;
  border-radius: 4px;
  animation: loading 1s ease-in-out infinite;
}
.loading-bar:nth-child(1) {
  background-color: #3498db;
  animation-delay: 0;
}
.loading-bar:nth-child(2) {
  background-color: #c0392b;
  animation-delay: 0.09s;
}
.loading-bar:nth-child(3) {
  background-color: #f1c40f;
  animation-delay: .18s;
}
.loading-bar:nth-child(4) {
  background-color: #27ae60;
  animation-delay: .27s;
}

@keyframes loading {
  0% {
    transform: scale(1);
  }
  20% {
    transform: scale(1, 2.2);
  }
  40% {
    transform: scale(1);
  }
}
  
    #tableGis {
    display: table;
    border-collapse: separate;
    margin-left: 10px;
    /* margin-bottom: 50px; */
    border-spacing: 2px;
    border-color: #333;
    background-color: #80808059;
    font-size: xx-small !important;
} 
ul.iconmenu {
    font-size: 1em;
    list-style: none;
    position: absolute;
    /* right: 2px; */
    top: 8px;
    margin: 0;
    margin-left: 10px;
    padding: 0;
    width: 3em;
    z-index: 20;
    background: #eee;
    border-top-left-radius: 5px;
    border-bottom-left-radius: 7px;
    box-shadow: 3px 1px 3px rgba(0,0,0,0.4);
}
ul.iconmenu li{
text-align: center;
cursor: pointer;
}

ul.iconmenu li a{
display: block;
text-decoration: none;
color: black;
padding: 3px 3px;
border-bottom: 1px solid black;

}
ul.iconmenu li a:hover, ul.iconmenu li a.selected{ /* style for selected icon */
background: #D2FFFF;
}

.entity_status {
  float: left;
  width: 8px;
   height: 8px;
  margin: 5px;
  border: 1px solid rgba(0, 0, 0, .2);
}

.unfeeze {
  background: #cc263c;
}

.freeze {
  background: #00cc00;
}

.pfreeze {
  background: #E5D65F;
}

table#showgis {
      font-size: xx-small;
   
}

 </style>  
  <link rel="stylesheet" href="https://js.arcgis.com/3.22/dijit/themes/claro/claro.css">
  <link rel="stylesheet" href="https://js.arcgis.com/3.20/esri/css/esri.css">
  <script src="https://js.arcgis.com/3.20/"></script>
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrStateService.js'></script>
  <script type="text/javascript" src='${pageContext.request.contextPath}/dwr/engine.js'> </script>
 <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>

  <script type="text/javascript" src="js/gisFreezeUnfreezeStatus.js"></script> 
<script>
var tokenval="${token}";
</script>
</head>  
  
<body>  
   
  <div class="loading" id="loader">
  <div class="loading-bar"></div>
  <div class="loading-bar"></div>
  <div class="loading-bar"></div>
  <div class="loading-bar"></div>
</div>
     <div style="display: none;padding-top: 15%; position: absolute; z-index: 4"  id="btnBack" >
                <ul id="myiconmenu" class="iconmenu">
                    <li id="zoomin" title="Back to State Map"><a>
                       <i class="fa fa-arrow-circle-left" aria-hidden="true"></i></a></li>
     </ul>
     </div>                     
  <div id="map" ></div>  
   <div id="legend" style="width: 210px; position: absolute; left: 20px; bottom: 60px; background-color: #F0F0F0; z-index: 4; display: block">
    	<div>
         <div id="title" class="title1" style="display: block;padding:2px"></div>
      </div> 
   		<div id="thematiclgnd" >
                    <table id="tableGis " >
                        <tr id="unfreeze">
                            <td  width="20" height="23" style="background-color: rgba(204, 38, 60, 0.9);"></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Unfreeze</label></td>
                        </tr>
                        <tr id="Freeze">
                            <td  width="20" height="23" style="background-color: rgba(0, 204, 0, 0.9);"></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Freeze</label></td>
                        </tr>
                        <tr id="PFreeze">
                            <td width="20" height="23" style="background-color: rgba(229, 214, 95, 0.9);"></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Partial Freeze</label></td>
                        </tr>
                       
                    </table>
      </div>
    </div>

</body>  
  
</html>  