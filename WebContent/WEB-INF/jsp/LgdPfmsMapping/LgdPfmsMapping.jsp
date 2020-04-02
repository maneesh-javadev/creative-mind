<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String contextpthval = request.getContextPath();
%>
<script type="text/javascript"
	src="<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js"></script>
<script type="text/javascript" src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>

<html>
<head>
<script lang="javascript">
function lgdPfmsMapping(lgdPfmsId,userId)
{
	
	lgdDwrlocalBodyService.updateLgdPfmsMappingStatus( parseInt(lgdPfmsId),  parseInt(userId) ,{
  	 	callback: function( result ) {
  	 		
  	 		alert("Verified");
  	 	},
  	 	errorHandler : function(exception) { alert(exception); },
		async : true
		});
	
	}
	
	function getdistrictlist(districtCode)
	{
		lgdDwrlocalBodyService.fetchLgdPfmsMapping(parseInt(districtCode),{
			callback: function( result ){
				
				$('#tbodyId').empty();
				
				for(var i = 0;i < result.length ; i++)
					{
						var tds ='<tr>';
						tds +='<td>'+(i+1) +'</td>';
						tds +='<td>'+result[i].pfmsStateName+'</td>';
						tds +='<td>'+result[i].lgdStateName+'</td>';
						tds +='<td>'+result[i].lgdStateCode+'</td>';
						tds +='<td>'+result[i].pfmsDistrictName+'</td>';
						tds +='<td>'+result[i].lgdDistrictName+'</td>';
						tds +='<td>'+result[i].lgdDistrictCode+'</td>';
						tds +='<td>'+result[i].pfmsBlockName+'</td>';
						tds +='<td>'+result[i].lgdBlockName+'</td>';
						tds +='<td>'+result[i].lgdBlockCode+'</td>';
						tds +='<td>'+result[i].pfmsPanchayatName+'</td>';
						tds +='<td>'+result[i].lgdPanchayatName+'</td>';
						tds +='<td>'+result[i].lgdPanchayatCode+'</td>';
						tds +='<td>'+result[i].lgdPanchayatVersion+'</td>';
						
						if(result[i].verifiedStatus == true)
							{
							tds +='<td><i class="fa fa-check" aria-hidden="true" style="color: light blue;"> Verified</i></td>';
							}
						else{tds +='<td><div id="checkbox_'+(i+1)+'" style="display: block;"><input type="checkbox" class="form-control-check" name="lgdPfmsMapping['+i+'].verifiedStatus" onchange="lgdPfmsMapping('+result[i].lgdPfmsId+',${userid}),disable('+(i+1)+')" id="check_'+(i+1)+'"></div><div id="verified_'+(i+1)+'" style="display: none;"><i class="fa fa-check" aria-hidden="true" style="color: light blue;"> Verified</i></div></td>';}
						tds += '</tr>';
						$('#tableId tbody').append(tds);
					}
				
			},
			errorHandler : function(exception) { alert(exception); },
			async : true
		});
		
	}
	
	function disable(obj)
	{
		if(document.getElementById("check_"+obj).checked)
			{
			document.getElementById("check_"+obj).disabled = true;
			document.getElementById("verified_"+obj).style.display = "block";
			document.getElementById("checkbox_"+obj).style.display = "none";
			}
		else
			{
			document.getElementById("check_"+obj).disabled = false;
			document.getElementById("verified_"+obj).style.display = "none";
			document.getElementById("checkbox_"+obj).style.display = "block";
			}
	}
	
	


</script>
</head>
<body>
	<section class="content">
		<div class="row">
			<div class="col-sm-12 col-lg-12 col-md-12">
				<div class="box">
					<div class="header">
						<h3>
							<div align="center">LGD-PFMS Mapping</div>
						</h3>
					</div>
					<form:form method="post" name="lgdpfmsmapping"
						action="lgdPfmsMapping.html"
						modelAttribute="LGD_PFMS_MAPPING_FORM">
						<div class="body">
						
							<div id="districtDropDown">
								<div class="row">
									<c:if test="${regionName == 'state'}">
										<div class="form-group">
											<label for="district" class="col-sm-3" style="margin-left: 15px;" > Select District :-</label>
											<div class="col-sm-5">
												<select onchange="getdistrictlist(this.value)">
													<option value="0">----select----</option>
													<c:forEach items="${LIST_OF_DISTRICT}" var="DISTRICT">
														<option value="${DISTRICT.districtPK.districtCode}">${DISTRICT.districtNameEnglish}</option>
													</c:forEach>
												</select>
										
											</div>
											
										</div>
									</c:if>
								</div>
								</br>
							</div>
							<div class="box">
							<div class="table-responsive">
								<table class="table table-bordered" id="tableId">
									<thead>
										<tr>
											<th><div align="center">S.no</div></th>
											<th><div align="center">PFMS State Name</div></th>
											<th><div align="center">LGD State Name</div></th>
											<th><div align="center">LGD State Code</div></th>
											<th><div align="center">PFMS District Name</div></th>
											<th><div align="center">LGD District Name</div></th>
											<th><div align="center">LGD District Code</div></th>
											<th><div align="center">PFMS Block Name</div></th>
											<th><div align="center">LGD Block Name</div></th>
											<th><div align="center">LGD Block Code</div></th>
											<th><div align="center">PFMS Panchayat Name</div></th>
											<th><div align="center">LGD Panchayat Name</div></th>
											<th><div align="center">LGD Panchayat Code</div></th>
											<th><div align="center">LGD Panchayat Version</div></th>
											<th><div align="center">Verified Status</div></th>
										</tr>
									</thead>
									<tbody id="tbodyId">
										<c:forEach items="${LIST_OF_LGD_PFMS_MAPPING}"
											var="LGD_PFMS_MAPPING" varStatus="count">
											<%-- <input name="lgdPfmsMapping[${count.index}].verifiedBy" value="${userid}"/> --%>
											<tr>
												<td>${count.count}</td>
												<td>${LGD_PFMS_MAPPING.pfmsStateName}</td>
												<td>${LGD_PFMS_MAPPING.lgdStateName}</td>
												<td>${LGD_PFMS_MAPPING.lgdStateCode}</td>
												<td>${LGD_PFMS_MAPPING.pfmsDistrictName}</td>
												<td>${LGD_PFMS_MAPPING.lgdDistrictName}</td>
												<td>${LGD_PFMS_MAPPING.lgdDistrictCode}</td>
												<td>${LGD_PFMS_MAPPING.pfmsBlockName}</td>
												<td>${LGD_PFMS_MAPPING.lgdBlockName}</td>
												<td>${LGD_PFMS_MAPPING.lgdBlockCode}</td>
												<td>${LGD_PFMS_MAPPING.pfmsPanchayatName}</td>
												<td>${LGD_PFMS_MAPPING.lgdPanchayatName}</td>
												<td>${LGD_PFMS_MAPPING.lgdPanchayatCode}</td>
												<td>${LGD_PFMS_MAPPING.lgdPanchayatVersion}</td>
												<c:choose>
												<c:when test="${LGD_PFMS_MAPPING.verifiedStatus eq true}">
												<td><i class="fa fa-check" aria-hidden="true"
															style="color: light blue;"> Verified</i></td>
												</c:when>
												<c:otherwise>
												<td><div id="checkbox_${count.count}"
														style="display: block;">
														<form:checkbox
															path="lgdPfmsMapping[${count.index}].verifiedStatus"
															class="form-control-check"
															onchange="lgdPfmsMapping(${LGD_PFMS_MAPPING.lgdPfmsId},${userid}),disable(${count.count})"
															id="check_${count.count}" />
													</div>
													<div id="verified_${count.count}" style="display: none;">
														<i class="fa fa-check" aria-hidden="true"
															style="color: light blue;"> Verified</i>
													</div></td>
												</c:otherwise>
													</c:choose>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>