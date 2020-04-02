<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->

  <script src="js/Chart/bootstrap.min.js"></script>
  <script src="js/Chart/angular.min.js"></script>
  <script src="js/Chart/Chart.min.js"></script>
  <script type="text/javascript" src='<%=contextpthval%>/dwr/interface/lgdDwrCommonService.js'></script>
  
  <script src="jquery/script.js" type="text/javascript"></script>
<script type="text/javascript">
var linkId="";
	function expand(e,stateCode,obj){
		e.preventDefault();
		if(obj.text=="[+]"){
			linkId=stateCode;
			obj.text="[-]";
			obj.style="color:red";
			lgdDwrCommonService.getDistrictDetailsByStateCode(stateCode,{
				callback: getDistrictDetailsByStateCodeCallBack,
			});
			
			
			$("#actionDiv").fadeIn(500);
			
		} else if(obj.text=="[-]"){
			obj.text="[+]";
			obj.style="color:blue";
		}
		
	}

	function getDistrictDetailsByStateCodeCallBack(data){
		var len=data.length;
		$('#workingTable tr').not(function(){ return !!$(this).has('th').length; }).remove();
		var tbl=$("#workingTable");
		var tbody=$("<tbody/>");
		for(var i=0;i<len;i++){
			var classname="";
			if(i%2==0){
				classname= "odd";
			} else {
				classname="even";
			}
			var tr=$("<tr class="+classname+" style='line-height:30px'/>");
			var td1=$("<td>"+(i+1)+"</td>");
			var td2=$("<td>"+data[i][1]+"</td>");
			var status="";
			var status1="";
			/* modified by Sunita on 25-11-2016 */
			if(data[i][2]!=null){
				status=data[i][2];
				if(status=='F'){
					status1 = "Freeze";
				}else if(status=='U'){
					status1 = "UnFreeze";
				}
			}
			var td3=$("<td>"+status1+"</td>");
			var file="";
			if(data[i][3]!=null){
				file=data[i][3];
				}
			
			 var td4=$("<TD/>");
			 var templateAnchor = $("<A/>");
			 templateAnchor.attr("href", "#" );
			 templateAnchor.attr("onclick","retrieveFile('"+file+"')"); 
			 var templateLabel = $("<label/>");
			 templateLabel.html(file);
			 templateAnchor.append(templateLabel);
			 td4.append(templateAnchor);
			
			//var td4=$("<td><a onclick='retrieveFile("+file+")' href='#'>"+file+"</a></td>");
			tr.append(td1).append(td2).append(td3).append(td4);
			tbody.append(tr);
		}
		tbl.append(tbody);
	}
	
	/* added by Sunita */
	var retrieveFile = function (fileName) {
	$(window).attr("location","downloadStateDistrictWiseReportFile.do?filename="+ fileName +"&<csrf:token uri='downloadStateDistrictWiseReportFile.do'/>");
	  								
	}

	
	function donutPieChart(percent,row){
	 var doughnutData = [{
	        value: percent,
	        color: "#46BFBD",
	        highlight: "#5AD3D1",
	      }, {
	        value: 100-percent,
	        color: "#F7464A",
	        highlight: "#FF5A5E",
	      }

	    ];
	 
	      var ctx = document.getElementById("chart-area"+row).getContext("2d");
	      window.myDoughnut = new Chart(ctx).Doughnut(doughnutData);
		  
	    
	}
	
	function closeAction(){
		$("#actionDiv").fadeOut(500);
		var obj=document.getElementById("link"+linkId);
		obj.text="[+]";
		obj.style="color:blue";
		
	}
	
	$(function() {
	    $("#nestActionDiv").draggable();
	});
</script>
 <style>
    
    #canvas-holder {
      height: 80px;
      width: 100px;
      position: relative;
    }
    
    .chart-title {
      position: absolute;
      right: 0;
      margin: 0 auto;
      top: 40%;
      transform: translateY(-50%);
      width: 100%;
      text-align: center;
    }
    
    #actionDiv {
	background-color: rgba(255, 255, 255, 0.5);
	width: 100%;
	height: 100%;
	position: fixed;
	top: 0%;
	left: 0%;
}

#nestActionDiv {
	background-color: rgba(255, 255, 255, 255);
	width: 800px;
	height: 500px;
	border-width: 2px;
	border-color: gray;
	border-style: solid;
	-webkit-border-radius: 50px;
	position: fixed;
	top: 30%;
	left: 15%;
}

#close {
	width: 30px;
	height: 30px;
}

#close:hover {
	width: 35px;
	height: 35px;
}
  
  </style>
</head>
<body>
<h1 style="font-size: medium; font-weight: bold;color: gray;">State Wise Parliament/Assembly Constituency to local body mapping status</h1><br>
	<!-- Main Form Styling starts -->
	<div class="form_stylings">
		<!-- Main Heading starts -->
		<%-- <div class="header">
			<h3><spring:message htmlEscape="true" code="Label.LbMapped"></spring:message></h3>
		</div> --%>
		
		<div class="page_content">
			<div class="form_container" style="height:500px;overflow-x:auto; ">
			
				<table style="width: 100%">
					<thead>
						<tr>
							<th align="left" style="width: 10%"><label style="font-weight: bold;color: gray;">Expand to view district wise status</label></th>
							<th align="left" style="width: 25%"><label style="font-weight: bold;color: gray;">State Name</label></th>
							<th align="left" style="width: 10%"><label style="font-weight: bold;color: gray;">Total No. Of Districts</label></th>
							<th align="left" style="width: 10%"><label style="font-weight: bold;color: gray;">No. Of Districts completed mapping</label></th>
							<th align="left" style="width: 10%"><label style="font-weight: bold;color: gray;">Percentage(%) of Districts completed mapping</label></th>
							<th align="left" style="width: 15%"><label style="font-weight: bold;color: gray;"></label></th>
						</tr>
						</thead>
						<tbody>
						<c:set var="i" value="1"></c:set>
						<c:set var="classVar" value=""/>
						<c:forEach items="${freezedDistrictlist }" var="list" varStatus="count">
							<c:if test="${i%2 eq 1 }">
								<c:set var="classVar" value="odd"></c:set>
							</c:if>
							<c:if test="${i%2 eq 0 }">
								<c:set var="classVar" value="even"></c:set>
							</c:if>
							<tr class="${classVar }" style="height: 30px">
								<td><b><a href="#" id="link${list[0] }" onclick="expand(event,${list[0] },this)" style="color: blue;">[+]</a></b></td>
								<td>${list[1] }</td>
								<td align="center">${list[2] }</td>
								<td align="center">${list[3] }</td>
								<td align="center">
								<script type="text/javascript">
								var list3=parseFloat('${list[3]}').toFixed(2);
								var list2=parseFloat('${list[2]}').toFixed(2)
								var prcnt=list3/list2;
								</script>
								<fmt:parseNumber var="l3" value="${list[3]}" type="number"></fmt:parseNumber>
								<fmt:parseNumber var="l2" value="${list[2]}" type="number"></fmt:parseNumber>
								
									<fmt:formatNumber var="percentage" value="${(l3/l2)*100}" maxFractionDigits="2" minFractionDigits="0"></fmt:formatNumber>
									${percentage}%
								</td>
								<td align="center">
									
									<div id="canvas-holder">
										<label class="chart-title">${percentage}%</label>
										<canvas id="chart-area${count.count }" height="60" width="60" />
									</div>
									<script type="text/javascript">donutPieChart('${percentage}','${count.count }');</script>
								</td>
							</tr>
							<c:set var="i" value="${i+1 }"></c:set>
						</c:forEach>
					</tbody>
				</table>
				<div id="actionDiv" style="display: none;">
							<div id="nestActionDiv">
								<img src='<%=contextpthval%>/images/close.png' alt="close" title="Close" id="close" align="right" style="margin-right: -10px;margin-top: -5px;"
									onclick="closeAction()" /><br></br>

								<div style="padding-left: 20px; padding-top: 10px;"
									align="center">
									
									<div style="width: 600px; height: 400px; overflow-x: auto;">
										<table id="workingTable" style="width: 100%">
											<thead>
												<tr>
													<th align="left">S.no.</th>
													<th align="left">District Name</th>
													<th align="left">Status</th>
													<th align="left">Uploaded File</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<div style="position: absolute; bottom: 0; right: 40px;">
									<input type="button" value="Close" onclick="closeAction()" />
								</div>
							</div>
						</div>
			</div>
		</div>
	</div>
 	       <div class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<li>
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
							<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						</li>
						<li>
							<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
						    <label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>								
				 			<%=df.format(new java.util.Date())%>  </label>
						</li>
						<li>
							<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</li>
					</div>	
</body>
</html>
