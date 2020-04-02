<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="publicModule">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<title><spring:message code="Label.report.on.lgd.updation" htmlEscape="true"></spring:message></title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/angularjs/angular-material.min.css">
   <script src="${pageContext.request.contextPath}/angularjs/material/angular.min.js"></script>
  <script src="${pageContext.request.contextPath}/angularjs/material/angular-animate.min.js"></script>
  <script src="${pageContext.request.contextPath}/angularjs/material/angular-route.min.js"></script>
  <script src="${pageContext.request.contextPath}/angularjs/material/angular-aria.min.js"></script>
  <script src="${pageContext.request.contextPath}/angularjs/material/angular-messages.min.js"></script>
  <script src="${pageContext.request.contextPath}/angularjs/material/moment.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/angular-material.min.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/lgdUpdation/lgdUpdationController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/lgdUpdation/lgdUpdationChildController.js"></script>
<script src = "${pageContext.request.contextPath}/angularjs/lgdUpdation/lgdUpdationService.js"></script>
<script src="${pageContext.request.contextPath}/angularjs/lgdUpdation/dist/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/angularjs/lgdUpdation/dist/angular-chart.min.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/angularjs/ui-bootstrap-tpls-0.11.0.js"></script>
 <script src="${pageContext.request.contextPath}/angularjs/material/jszip.min.js"></script>
 <script src="${pageContext.request.contextPath}/angularjs/material/kendo.all.min.js"></script>
 <script>
 function ExportPdf(){ 
		$('.hidefield').hide();
		kendo.drawing
		    .drawDOM("#containerPage", 
		    { 
		    	orientation: 'landscape',
		    	//paperSize: "A4",
		        margin: { top: "1cm", bottom: "1cm" },
		        scale: 0.8,
		        height: 500
		    })
		        .then(function(group){
		        kendo.drawing.pdf.saveAs(group, "LGD_UPDATION.pdf")
		    });
		
		$('.hidefield').show();
		}
 
 function printDiv() {
	 $('.hidefield').hide();
	 var docHead = document.head.outerHTML;
	  var printContents = document.getElementById("containerPage").outerHTML;
	  var winAttr = "location=yes, statusbar=no, menubar=no, titlebar=no, toolbar=no,dependent=no, width=865, height=600, resizable=yes, screenX=200, screenY=200, personalbar=no, scrollbars=yes";

	  var newWin = window.open("", "_blank", winAttr);
	  var writeDoc = newWin.document;
	  writeDoc.open();
	  writeDoc.write('<!doctype html><html>' + docHead + '<body onLoad="window.print()">' + printContents + '</body></html>');
	  writeDoc.close();
	  newWin.focus();
	  
	/* 	$('.hidefield').hide();

		var printContents = document.getElementById("containerPage").innerHTML;
		var originalContents = document.body.innerHTML;
		document.body.innerHTML = printContents;
		
		window.print();
		document.body.innerHTML = originalContents; */
		$('.hidefield').show();
	}
 </script>

<style>
.label-pad{padding-left: 9px;}
.indi-pad{padding-left: 20px;}
.bg-unfreeze { background-color:#ff000087;}
.bg-freeze { background-color: #00B500;}
.bg-partial-freeze { background-color: #FFED00;}
.bg-not-config{ background-color: #bbbbbb;}
.bg-config-state { background-color: #2cc0c5;}
#preloader {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    margin-top: 90px;
}
#loader {
    display: block;
    position: relative;
    left: 50%;
    top: 50%;
    width: 150px;
    height: 150px;
    margin: -75px 0 0 -75px;
    border-radius: 50%;
    border: 3px solid transparent;
    border-top-color: #9370DB;
    -webkit-animation: spin 2s linear infinite;
    animation: spin 2s linear infinite;
}
#loader:before {
    content: "";
    position: absolute;
    top: 5px;
    left: 5px;
    right: 5px;
    bottom: 5px;
    border-radius: 50%;
    border: 3px solid transparent;
    border-top-color: #BA55D3;
    -webkit-animation: spin 3s linear infinite;
    animation: spin 3s linear infinite;
}
#loader:after {
    content: "";
    position: absolute;
    top: 15px;
    left: 15px;
    right: 15px;
    bottom: 15px;
    border-radius: 50%;
    border: 3px solid transparent;
    border-top-color: #FF00FF;
    -webkit-animation: spin 1.5s linear infinite;
    animation: spin 1.5s linear infinite;
}
@-webkit-keyframes spin {
    0%   {
        -webkit-transform: rotate(0deg);
        -ms-transform: rotate(0deg);
        transform: rotate(0deg);
    }
    100% {
        -webkit-transform: rotate(360deg);
        -ms-transform: rotate(360deg);
        transform: rotate(360deg);
    }
}
@keyframes spin {
    0%   {
        -webkit-transform: rotate(0deg);
        -ms-transform: rotate(0deg);
        transform: rotate(0deg);
    }
    100% {
        -webkit-transform: rotate(360deg);
        -ms-transform: rotate(360deg);
        transform: rotate(360deg);
    }
}
</style>
</head>
<body class = "datepickerdemo" ng-controller = "lgdUpdationController as ctrl"
         layout = "column" ng-cloak>
         
  <section class="content">
	<div class="row">
		<section class="col-lg-12">
			<div class="box">
				
				
		<div class="box-body" id="containerPage">
		 <form class="form-horizontal" id="lgdUpdationForm">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifyVillageChangeEffectiveDate.htm"/>"/>
		 <input type="hidden" name="stateCode" value="{{villageDetail.parentCode}}" /> 
		<div class="box-header subheading">
		
		
		<div class="form-group" >
			<div class="col-sm-9">
				<div ng-show="isStateDiv">
			    <h4><spring:message code="Label.report.on.lgd.updation" text="Report on LGD Updation" htmlEscape="true"></spring:message></h4>
			    </div>
			    <div ng-show="isDistrictDiv">
			    <h4><spring:message code="Label.report.on.lgd.updation" text="Report on LGD Updation" htmlEscape="true"></spring:message>({{reportModel.stateName}} State)</h4>
			    </div>
			</div>
			<div class="col-sm-3" id="confirm">
				 <div id="bttndiv" style="float:right;">
		           <a style="padding-left:10px " href="#" onclick="ExportPdf();"><i class="fa fa-2x fa-file-pdf-o hidefield" aria-hidden="true"></i></a>
		           <a style="padding-left:10px " href="#" onclick="printDiv();"> <i class="fa fa-2x fa-print hidefield" aria-hidden="true"></i></a>
		         
		          </div>
		 </div>
			</div>

		</div>
		
			
		 
	
				
<p><i class="fa fa-hand-o-right hidefield" aria-hidden="true"></i><m class="indi-pad">Land Regions includes Districts,Subdistricts and Villages.</label></p>
<p><i class="fa fa-hand-o-right hidefield" aria-hidden="true"></i><m class="indi-pad">Panchayati Raj Institutions includes District Panchayats,Block Panchayats and Village Panchayats as per the State PRI setup.</m></p>
<p><i class="fa fa-hand-o-right hidefield" aria-hidden="true"></i><m class="indi-pad">Traditional Local Bodies includes Autonomous District Council,Village Council as per the State TLB setup.</m></p>
<p><i class="fa fa-hand-o-right hidefield" aria-hidden="true"></i><m class="indi-pad">Urban Local Bodies includes Muncipal Corporations,Munciplaities as per the State ULB setup.</m></p>

<br/>
<div ng-show="isStateDiv">
<div id="thematiclgnd" >
                    <table id="tableGis " >
                        <tr>
                            <td  width="40" height="23" class="bg-unfreeze"></td>
                            <td><label class="label-pad">Unfreeze</label></td>
                        	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td  width="40" height="23" class="bg-freeze"></td>
                            <td><label class="label-pad">Freeze</label></td>
                      		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td width="40" height="23" class="bg-partial-freeze"></td>
                            <td><label class="label-pad">Partial Freeze</label></td>
                        </tr>
                       
                    </table>
      </div>
	<br/>	
	
			      
		<div class="table-responsive">
		
				<table class="table table-bordered table-hover" width="80%">
    			 <thead>
    			 <tr>
	    			   <th width="5%" class="bg-primary"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
					   <th width="20%" class="bg-primary"><spring:message htmlEscape="true"	code="Label.STATENAME"></spring:message></th>
					    <th  width="5%" class="bg-primary hidefield"><spring:message htmlEscape="true"	code="label.view" text="View District Status"></spring:message></th>
					   <th  width="13%" class="bg-primary"><spring:message htmlEscape="true" code="Label.landregion.status" text="Status of Land Regions"></spring:message></th>
					   <th width="13%" class="bg-primary"><spring:message htmlEscape="true" code="Label.urban.status" text="Status of Urban Local Bodies"></spring:message></th>
					   <th width="13%" class="bg-primary"><spring:message htmlEscape="true" code="Label.panchayat.status" text="Status of Panchayati Raj Institutions"></spring:message></th>
					   <th width="13%" class="bg-primary"><spring:message htmlEscape="true" code="Label.traditional.status" text="Status of Traditional Local Bodies"></spring:message></th>
					   <th width="5%" class="bg-primary hidefield"><spring:message htmlEscape="true"	code="label.gview" text="Graphical View"></spring:message></th>
    			 </tr>
			</thead>
			 <tbody>
			
			 <tr ng-repeat="obj in entityList | orderBy : 'obj.entityName'" >
			 <td>{{$index+1}}</td>
			 <td >{{obj.entityName}}</td>
			  <td class="hidefield"><a href="#" ng-click="showDistrictReport(obj.entityName,obj.entityCode)"><i class="fa fa-television" aria-hidden="true"></i></a> </td>
			 <td class="{{obj.revenueColor}}">{{obj.revenueStatus}}</td>
			  <td class="{{obj.ulbColor}}">{{obj.ulbStatus}}</td>
			   <td class="{{obj.priColor}}">{{obj.priStatus}}</td>
			    <td class="{{obj.trdColor}}">{{obj.trdStatus}}</td>
			    <td class="hidefield"><a href="#" ng-click="showChart(obj.entityName,obj.entityCode)"><i class="fa fa-pie-chart" aria-hidden="true"></i></a> </td>
			 </tr>
			 
			
			 
			 </tbody>
			 
		</table>
		
			
		</div>
		</div>
		
		<div ng-show="isDistrictDiv">	
		<div id="thematiclgnd" >
                    <table id="tableGis " >
                        <tr>
                            <td  width="40" height="23" class="bg-unfreeze"></td>
                            <td><label class="label-pad">Unfreeze</label></td>
                        	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td  width="40" height="23" class="bg-freeze"></td>
                            <td><label class="label-pad">Freeze</label></td>
                      		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      		 <td  width="40" height="23" class="bg-not-config"></td>
                            <td><label class="label-pad">State Not Configure</label></td>
                      		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      		 <td  width="40" height="23" class="bg-config-state"></td>
                            <td><label class="label-pad">Configure at State level only</label></td>
                      		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            
                        </tr>
                       
                    </table>
      </div>
	<br/>	
			      
		<div class="table-responsive">
		<table class="table table-bordered table-hover" width="80%">
    			 <thead>
    			 <tr>
	    			   <th width="5%" class="bg-primary"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></th>
					   <th width="20%" class="bg-primary"><spring:message htmlEscape="true"	code="Label.DISTRICTNAME"></spring:message></th>
					  
					   <th  width="15%" class="bg-primary"><spring:message htmlEscape="true" code="Label.landregion.status" text="Status of Land Regions"></spring:message></th>
					   <th width="15%" class="bg-primary"><spring:message htmlEscape="true" code="Label.urban.status" text="Status of Urban Local Bodies"></spring:message></th>
					   <th width="15%" class="bg-primary"><spring:message htmlEscape="true" code="Label.panchayat.status" text="Status of Panchayati Raj Institutions"></spring:message></th>
					   <th width="15%" class="bg-primary"><spring:message htmlEscape="true" code="Label.traditional.status" text="Status of Traditional Local Bodies"></spring:message></th>
    			 </tr>
			</thead>
			 <tbody>
			
			 <tr ng-repeat="obj in districtList | orderBy : 'obj.entityName'" >
			 <td>{{$index+1}}</td>
			 <td >{{obj.entityName}}</td>
			
			 <td class="{{obj.revenueColor}}">{{obj.revenueStatus}}</td>
			  <td class="{{obj.ulbColor}}">{{obj.ulbStatus}}</td>
			   <td class="{{obj.priColor}}">{{obj.priStatus}}</td>
			    <td class="{{obj.trdColor}}">{{obj.trdStatus}}</td>
			 </tr>
			 
			
			 
			 </tbody>
			 
		</table>
	
		</div>
		</div>
		
		<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>&nbsp;<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
							</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>&nbsp;<%=df.format(new java.util.Date())%></label>
							</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</div>
		
		<!--Big blue-->
		<div id="preloader" ng-show="isloader" >
  			<div id="loader"></div>
		</div>
		
		<div class="box-footer">
	   <div class="col-sm-offset-2 col-sm-10">
		  <div class="pull-right">		
		<button style="width: 80px;" type="button" class="btn btn-info hidefield" ng-click=stateReport() ng-show="isDistrictDiv"><i class="fa fa-arrow-left" aria-hidden="true"></i>  <spring:message code="Button.BACK" htmlEscape="true"></spring:message></button>
		<button type="button" name="Submit3" id="btn1" class="btn btn-danger hidefield" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					   
	</div></div></div>
		
				
		  </form>
		  <script src="/LGD/JavaScriptServlet"></script>
		 </div>
       </div>
    </section>
   </div>
  </section>       
 </body>
 </html>