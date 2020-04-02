<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="../../common/dwr.jsp"%>
<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/dwrRestructuredLocalBodyService.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type="text/javascript">
var strSelect = [ {
	optValue : '',
	optText : '<spring:message text='Select'/>'
} ];

$(document).ready(function() {
$('#subdistrict').DataTable({
	"bJQueryUI": false,
	"order": [[ 1, "asc" ]],
}); 

$("#subdistrict_wrapper").attr("style","width:100%");
});

function getUnMappedPolygonSubDistrict(obj){
	var districtId=obj.value;
	if (districtId != "") {
		lgdDwrVillageService.getUnMappedPolygonSubDistrictByDistrict(districtId,getUnMappedPolygonSubDistrictCallBack);
		}
	}
	
	function getUnMappedPolygonSubDistrictCallBack(data){
		 $("#subdistrict").DataTable().destroy();
		$("#subdistrict tbody tr").remove();
		var subdistrict=$("#subdistrict tbody");
		
		var len=data.length;
		for(i=0;i<len;i++){
			var cls;
			if(i%2==0){
				cls="odd";
			} else if(i%2==1){
				cls="even";
			}
			var tr=$("<tr class='"+cls+"' style='line-height:50px;'/> ");
			var td1=$("<td>"+data[i].subdistrictNameEnglish+"</td>");
			var td2=$("<td align='center'/>");
			var img=$("<img id ='gisMapView"+i+"' src='images/showMap.jpg' width='18' height='19' border='0' />");
			img.attr("onclick","getUnMappedVillageForGIS('"+data[i].subdistrictCode+"','"+data[i].subdistrictNameEnglish+"')");
			td2.append(img);
			tr.append(td1).append(td2);
			subdistrict.append(tr);
		}
		
		$('#subdistrict').DataTable({
			"bJQueryUI": false,
			"order": [[ 1, "asc" ]],
		});
		
		$("#subdistrict_wrapper").attr("style","width:100%");
	}
	
	function getUnMappedVillageForGIS(subdistrictCode,subdistrictNameEnglish){
		var isShowOnlyBoundary="BV";
		if(subdistrictCode!=""){
		lgdDwrVillageService.getUnMappedVillageForGIS(parseInt(subdistrictCode),isShowOnlyBoundary, {
			callback : function( result ){
				 if("FAILED" == result){
					customAlert(result);
				}else{
					$("#dialogBX").dialog({
						title:' GIS Map View of '+subdistrictNameEnglish+' Sub-District',
					    modal: true,
					    width:950,
					    height: 700,
					    resizable:false,
					    open: function(ev, ui){
					    	 showLoadingImage();
							
				             $('#myIframe').attr('src', result);
				             $("#myIframe").load(function(){
				            	 hideLoadingImage(); 
						    });
				    	}
					});	
				} 
			},
			errorHandler : function( errorString, exception){
					customAlert(exception);
			},
			async : true
		});
		}
	}
</script>
</head>
<body>
	<div class="form_stylings">
		<div class="header">
			<h3><spring:message htmlEscape="true" code="Update GIS Boundaries" /></h3>
		</div>
		<div class="clear"></div>
		<div class="page_content">
		<div class="form_container">
			<form:form method="POST" commandName=""  id="formCDraftedLB">
				<div id="dialogBX" style="display: none;">
					<iframe id="myIframe" width="910" height="650"></iframe>
				</div>
				<div>
				<table style="width: 35%">
					<tr style="line-height: 50px;">
						<td><spring:message text='Select District'/>&nbsp;<font style="color: red">*</font></td>
						<td align="center">
							<select onchange="getUnMappedPolygonSubDistrict(this)" style="width: 150px;">
								<option value="">Select</option>
								<c:forEach items="${unMappedPolygonDistrict }" var="dist">
									<option value="${dist.districtCode }">${dist.districtNameEnglish }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				</div>
				<div>
				<table id="subdistrict" style="width: 100%" class="display" cellspacing="0">
					<thead>
						<tr>
							<th align="left" style="width: 40%">Sub-District Name</th>
							<th align="left">Map</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				</div>									
			</form:form>
		</div>
		

</div>
</div>
<c:if test="${not empty publishMessage}">
		<script>
			$(window).load(function () {
				customAlert('${publishMessage}');
			});
		</script>
	</c:if>
</body>
</html>