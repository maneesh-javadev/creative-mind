<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' src="<%=contextpthval%>/js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/TableTools.js" ></script>
<link href="<%=contextpthval%>/external/jqueryCustom/css/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<script src="<%=contextpthval%>/JavaScriptServlet"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#tblViewLBTWards').dataTable({
			"bJQueryUI": true,
			"aoColumns":[{"bSortable": false}, null, {"bSortable": false}, {"bSortable": false}],
			"sPaginationType": "full_numbers"
		});
	} );
	
	resetFrom = function(){
		displayLoadingImage();
		document.forms['lbTypeWiseWards'].method = "GET"; 
		document.forms['lbTypeWiseWards'].action = "lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
	backForm = function(){
		//displayLoadingImage();
		document.forms['lbTypeWiseWards'].flow.value = "WARD_DETAILS";
		document.forms['lbTypeWiseWards'].method = "POST"; 
		document.forms['lbTypeWiseWards'].action = "globalWardBack.do?<csrf:token uri='globalWardBack.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
</script>
</head>
<body>	

<section class="content">
<!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><spring:message code="label.HeaderSummaryReportWard"/></h3>
              	<!-- <div id="showhelp">
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a></li>
				</div> -->
					<a id="showprint" style="visibility: hidden; display: none;" href="#">
					<img src='<%=contextpthval%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" /></a>	
            </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" id="lbTypeWiseWards" name="lbTypeWiseWards" commandName="lbTypeWiseWards">
				<input type="hidden" id="flow" name="flow"/>
				<input type="hidden" id="state" name="state" value="<c:out value='${state}'/>"/>
				<input type="hidden" id="lbTypeCode" name="lbTypeCode" value="<c:out value="${lbTypeCode}" escapeXml="true"></c:out>"/>
					<div id="showbytext">
					 	<div class="box-header subheading" id='resultLBType'><spring:message code="Label.WARDDETAILS" htmlEscape="true"/></div>
					 	  <br/>
						  <h3><label style="text-transform: uppercase;">Ward(s) of <c:out value="${lbTypeHierarchy}" escapeXml="true"></c:out></label></h3>
							<div class="box-body">
							<table class="table table-striped table-bordered dataTable" width="100%" align="center" id="tblViewLBTWards" >
							  <thead>
								<tr>
									<th><spring:message code="Label.SNO"/></th>
									<th><spring:message code="Label.WARDNAMEINENG"/></th>
									<th><spring:message code="Label.WARDNAMEINLOCAL"/></th>
									<th><spring:message code="Label.WARDNUMBER"/></th>	
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${lisWardDetails}" varStatus="sn">
									<tr>		
										<td align="center"><c:out value="${sn.count}" escapeXml="true"></c:out></td>
										<td align="left"><c:out value="${data[0]}" escapeXml="true"></c:out></td>
										<td align="left"><c:out value="${data[1]}" escapeXml="true"></c:out></td>
										<td align="center"><c:out value="${data[2]}" escapeXml="true"></c:out></td>
									</tr>
								</c:forEach>
						   </tbody>
					    </table>
					    </div>			
	     </div>
		
		<div class="box-footer">
		 <div class="col-sm-offset-2 col-sm-10">
		      <div class="pull-right">
				<button type="button" id="backButton" name="close" onclick="backForm();" class="btn btn-info" > <spring:message htmlEscape="true"  code="Button.BACK"></spring:message></button>
				<button type="button"  name="close" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
			</div>
		 </div>
		 </div>	
    </form:form>
    <script src="/LGD/JavaScriptServlet"></script>
   </div>
</section>
</div>
</div>
</section>	

</body>
</html>