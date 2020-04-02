<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="../taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type='text/javascript'	src='${pageContext.request.contextPath}/dwr/interface/dwrReportService.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/dwr/engine.js'> </script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'> </script>


<script src="<%=contextpthval%>/resource/common-resource/jszip.min.js"></script>
<script	src="<%=contextpthval%>/resource/common-resource/kendo.all.min.js"></script>
 
 <script type='text/javascript' src='${pageContext.request.contextPath}/resource/homebody-resource/js/jquery.dataTables.min.js'> </script>
<script type='text/javascript' src='${pageContext.request.contextPath}/resource/homebody-resource/js/dataTables.fixedHeader.min.js'> </script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/homebody-resource/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/homebody-resource/css/fixedHeader.dataTables.min.css">
<script>

$(document).ready(function() {
	
	$('#mainTable').DataTable( {
		"bPaginate": false,
		"bFilter": false,
	    "bInfo": false,
	    "scrollX":  false,
	    "scrollY":  false,
	    	fixedHeader: {
            header: true,
            footer: true
        }
	} );
	
	
});

var createTD=function(val,isAlign)
{
var td=$("<TD/>");
td.attr("align",isAlign);
$( td ).html(val);
return td;
};

function hideBttn(){
	$("#bttndiv").hide();
}

function showBttn(){
	$("#bttndiv").show();
}

function ExportPdf(){ 
	hideBttn();
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
	        kendo.drawing.pdf.saveAs(group, "Exported.pdf")
	    });
	
	showBttn();
	}

var createTH=function(val,isAlign)
{
	var td=$("<TD/>");
	td.attr("align",isAlign);
	$( td ).html("<b>"+val+"</b>");
	return td;
};








function PrintDiv() {
	hideBttn();
	var printContent = document.getElementById("containerPage");
	var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
	Win4Print.document.write(printContent.innerHTML);
	Win4Print.document.close();
	Win4Print.focus();
	Win4Print.print();
	Win4Print.close();
	showBttn();
	return false;
}

</script>



</head>
<body>

	

     	<div class="box stateWiseEntitiesCountJSP" id="containerPage">
            <div class="box-header with-border">
           <h3 class="box-title">
           <c:out value="State Wise Administrative Units Information" escapeXml="true"/>   
          <div id="bttndiv" style="margin-left:850px;float:right;">
           <a style="padding-left:10px " href="#" onclick="ExportPdf();"><i class="fa fa-file-pdf-o " aria-hidden="true"></i></a>
          <!--  <a style="padding-left:10px " href="#" onclick="ExportPdf();"><i class="fa fa-file-excel-o fa-2x" aria-hidden="true"></i></a> -->
           <a style="padding-left:10px " href="#" onclick="PrintDiv();"> <i class="fa fa-print " aria-hidden="true"></i></a>
         
          </div>
           </h3>
            </div><!-- /.box-header -->
           
               <div class="box-body" >
							<div id="loader" style="display:none;"></div>
							<div class="table-responsive" id="mainDevx">
				                   <table class="table table-striped table-bordered dataTable" id="mainDevx" >
										<thead>
											<tr>
												<th ><c:out value="S.No." escapeXml="true"/></th>
												<th > <c:out value="State/UT Name" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of Districts" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of District Panchayat" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of Sub-districts" escapeXml="true"/> </th>
												<th align="right"><c:out value="No. of Blocks" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of Intermediate Panchayat" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of Villages" escapeXml="true"/></th> 
												<th align="right"><c:out value="No. of Village Panchayat" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of Traditional Bodies" escapeXml="true"/></th>
												<th align="right"><c:out value="No. of Urban Bodies" escapeXml="true"/></th>
												
											</tr>
										</thead>
										<c:set var="total_no_of_districts" value="0" />
										<c:set var="total_no_of_zps" value="0" />
										<c:set var="total_no_of_subdistricts" value="0" />
										<c:set var="total_no_of_blocks" value="0" />
										<c:set var="total_no_of_bps" value="0" />
										<c:set var="total_no_of_villages" value="0" />
										<c:set var="total_no_of_vpsn" value="0" />
										<c:set var="total_no_of_tlbs" value="0" />
										<c:set var="total_no_of_ulbs" value="0" />
										<tbody>
										<c:forEach var="obj" varStatus="listrow" items="${statewiseEntitiesCountList}">
										
										<tr>
										<td><c:out value="${listrow.index+1}"/></td>
										<td><c:out value="${obj.state_name_english}"/></td>
										<td align="right"><a href="javascript:void(0);" onclick="showHideDiv('S',${obj.state_code},'L','${obj.state_name_english}','X')"><c:out value="${obj.no_of_districts}"/></a></td>
										<td align="right">
											<c:choose>
												<c:when test="${obj.no_of_zps ne 0}">
													<a href="javascript:void(0);" onclick="showHideDiv('S',${obj.state_code},'P','${obj.state_name_english}','D')">
														<c:out value="${obj.no_of_zps}"/>
													</a>
												</c:when>
												<c:otherwise>
													N.A
												</c:otherwise>
											</c:choose>
										</td>
										<td align="right"><c:out value="${obj.no_of_subdistricts}"/></td>
										<td align="right"><c:out value="${obj.no_of_blocks}"/></td> 
										<td align="right">
										
											<c:choose>
												 <c:when test="${obj.no_of_zps eq 0 and obj.no_of_bps ne 0}">
													<a href="javascript:void(0);" onclick="showHideDiv('S',${obj.state_code},'P','${obj.state_name_english}','I')" >
														<c:out value="${obj.no_of_bps}"/>
													</a>
												</c:when>
												<c:when test="${obj.no_of_bps ne 0}">
													<c:out value="${obj.no_of_bps}"/>
												</c:when>
												 <c:otherwise>
													N.A
												</c:otherwise> 
											</c:choose>
										</td>
										
									<td align="right"><c:out value="${obj.no_of_villages}"/></td> 
										<td align="right">
											<c:choose>
													<c:when test="${obj.no_of_vps ne 0}">
														<c:out value="${obj.no_of_vps}"/>
													</c:when>
													<c:otherwise>
														N.A
													</c:otherwise>
												</c:choose>
										</td>
										<td align="right">
											<c:choose>
													<c:when test="${obj.no_of_tlbs ne 0}">
														<c:out value="${obj.no_of_tlbs}"/>
													</c:when>
													<c:otherwise>
														N.A
													</c:otherwise>
												</c:choose>
										</td>
										<td align="right">
											<c:choose>
													<c:when test="${obj.no_of_ulbs ne 0}">
														<a href="javascript:void(0);" onclick="showHideDiv('S',${obj.state_code},'U','${obj.state_name_english}','X')">
															<c:out value="${obj.no_of_ulbs}"/>
														</a>
													</c:when>
													<c:otherwise>
														N.A
													</c:otherwise>
												</c:choose>
										
										</td>
										</tr>
										
										<c:set var="total_no_of_districts" value="${total_no_of_districts+obj.no_of_districts}" />
										<c:set var="total_no_of_zps" value="${total_no_of_zps+obj.no_of_zps}" />
										<c:set var="total_no_of_subdistricts" value="${total_no_of_subdistricts+obj.no_of_subdistricts}" />
										<c:set var="total_no_of_blocks" value="${total_no_of_blocks+obj.no_of_blocks}" />
										<c:set var="total_no_of_bps" value="${total_no_of_bps+obj.no_of_bps}" />
										<c:set var="total_no_of_villages" value="${total_no_of_villages+obj.no_of_villages}" />
										<c:set var="total_no_of_vpsn" value="${total_no_of_vpsn+obj.no_of_vps}" />

										<c:set var="total_no_of_tlbs" value="${total_no_of_tlbs+obj.no_of_tlbs}" />
										<c:set var="total_no_of_ulbs" value="${total_no_of_ulbs+obj.no_of_ulbs}" />
										</c:forEach>
										</tbody >
										 <tfoot>
										<tr>
										<th></th>
										<th><c:out value="Total"/></th>
										<td align="right"><b><c:out value="${total_no_of_districts}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_zps}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_subdistricts}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_blocks}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_bps}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_villages}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_vpsn}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_tlbs}"/></b></td>
										<td align="right"><b><c:out value="${total_no_of_ulbs}"/></b></td>
										</tr> 
										</tfoot>
										
										
									</table>
									</div>
									<div id="dynamicDIV">
									
									</div>
									<div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
										<label>${' '}<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
										</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>${' '}<%=df.format(new java.util.Date())%>  and Data is updated & managed by State Departments</label>
										</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
									</div>
								
				            </div>
		                  
          
            
           
         
            
           
            
            
            
            </div>
          
<%@include file="statewiseEntitiesCountJs.jsp" %>
</body>

</html>