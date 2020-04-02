<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type='text/javascript'	src='${pageContext.request.contextPath}/dwr/interface/lgdDwrInitialService.js'></script>
 <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdDwrStateService.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/engine.js'> </script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>


<html lang="en">
<head>
  <meta charset="utf-8">
  

  <meta name="viewport" content="width=device-width, initial-scale=1">
  
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/common-resource/jquery-jvectormap-2.0.3.css">
	
  <script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/jquery-3.3.1.min.js"></script> 
<script>

</script>
  <style>
  .india-map{
      width: 100%;
    height: 100%;
    top: 0;
    position: absolute;
   }
   
   table#showgis {
      font-size: xx-small;
   	  color: white;
}
  </style>
</head>
<body>
   <div class="india-map"></div>
   <div id="legend" style="bottom: 20px;  z-index: 4; text-align: center;left: 280px;position: absolute;color:white;">	
						<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>:<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
						</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
		</div>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/jquery-jvectormap-2.0.3.min.js"></script> 
   <script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/jquery-jvectormap-india-en4.js"></script> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/gisStateWiseEntitiesCount.js"></script> 
 
</body>
</html>	
		
		

