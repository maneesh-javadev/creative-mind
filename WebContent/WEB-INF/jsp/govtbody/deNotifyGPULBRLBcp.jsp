<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="js/govtorder.js"></script>
<link href="<%=contextpthval%>/resources-localbody/css/googleapi_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);	
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#example,#example1").dataTable({
		 "scrollX": true
		
	});
	
	/*------------ for govt order erros hide----------------*/

	$("#OrderNo_error").hide();
	$("#OrderNo_error1").hide();
	$("#OrderNo_error2").hide(); 
	$("#OrderDate_error").hide();
	$("#EffectiveDate_error").hide();
	$("#templateList_error").hide();
	$("#category_error").hide();
	$("#OrderNo_msg").hide();
	$("#OrderDate_msg").hide();
	$("#EffectiveDate_msg").hide();
	$("#GazPubDate_msg").hide();
	$("#GazPubDate_error").hide();
	$("#error_ulb_gp").hide();
	$("#error_rlb_gp").hide();
	
});

function getValidatedForm() {
	
	if(checkFileDetails()){
		if(validateULBS() && validateRLBS() ) {
       	 if(customDialog()) {
       		 return true;
       	 }
        }	
	}
	
         
}

function validateULBS() {
	var array_tableData = ""; 
	if($('.cb1').is(':checked')) {
	var rows = $('#tblCoveragedLBDetails > tbody > tr');
  	 for(var i = 0; i < rows.length; i++) {
  		 var datatableRowId = $(rows[i]).attr( 'id' ); 
  		 var isChecked = $(rows[i]).find('#cb1' + datatableRowId).is( ':checked' );
  	         if( isChecked ) {
  		        	var dstring	= $("#cb1"+datatableRowId).val();
  		        	dstring	+= ",";        	 
  		        	array_tableData	+= dstring;
  		        	
  			 	}
         }
  	var index = array_tableData.lastIndexOf(",");
  	$("#ulbsList").val(array_tableData.substr(0,index));
  	 $("#error_ulb_gp").hide();
  	 return true;
	} else {
		$("#error_ulb_gp").html("Please select at least 1 ULB coverage to de-notify !");
		$("#error_ulb_gp").show();
		return true;
	}
	
}
function validateRLBS() {
	var array_tableData = ""; 
	if($('.cb2').is(':checked')) {
	var oTable = $( '#example1' ).dataTable();
  	var rows = oTable.dataTable().fnGetNodes();
  	 for(var i = 0; i < rows.length; i++) {
  		 var datatableRowId = $(rows[i]).attr( 'id' ); 
  		 var isChecked = $(rows[i]).find('#cb2' + datatableRowId).is( ':checked' );
  	         if( isChecked ) {
  		        	var dstring	= $("#cb2"+datatableRowId).val();
  		        	dstring	+= ",";        	 
  		        	array_tableData	+= dstring;        	
  			 	}
         }
  	var index = array_tableData.lastIndexOf(",");
  	$("#rlbsList").val(array_tableData.substr(0,index));
  	$("#error_rlb_gp").hide();
  	return true;
	} else {
		$("#error_rlb_gp").html("Please select at least 1 Rural localbody to de-notify !");
		$("#error_rlb_gp").show();
		return true;
	}
	
}

</script>
<script language="javascript">
//---------------------------------- to select all checkboxes ---------------------------//
$(function(){
	$("#coveredall1").click(function() {
			$('.cb1').attr('checked',this.checked);
		});
		$(".cb1").click(function() {
			if($(".cb1").length==$(".cb1:checked").length) {
					$("#coveredall1").attr("checked","checked");
				} else {
							$("#coveredall1").removeAttr("checked");
					   }
		});
	
	$("#coveredall2").click(function() {
		$('.cb2').attr('checked',this.checked);
	});
	$(".cb2").click(function() {
		if($(".cb2").length==$(".cb2:checked").length) {
				$("#coveredall2").attr("checked","checked");
			} else {
						$("#coveredall2").removeAttr("checked");
				   }
	});	
	   
});
 function customDialog () {
	 
	 $("#dialog-confirm").modal('show')
/* 	 
	$("#dialog-confirm").html('<spring:message code="Error.dialog-confirm-denotify"  htmlEscape="true"></spring:message>');
	$("#dialog-confirm").dialog({
        resizable: true,
        modal: true,
        title: '<spring:message code="Msg.ConformDenotifyULBRLB"  htmlEscape="true"></spring:message>',
        height: 200,
        width: 400,
        buttons: {
            "Yes": function () {
            	document.denotifyULBRLB.submit();
                return true;
            },
                "No": function () {
                $(this).dialog('close');	               
            }
        }
    }); */
} 
 
 function modalYes(){
	document.denotifyULBRLB.submit();
     return true;
 }
 
 function modalNo(){
	 //document.denotifyULBRLB.submit();
      $("#dialog-confirm").modal('hide')
 }
 
 function validateOrdeNo() {

		if (document.getElementById('orderNo').value == "") {
			document.getElementById("errorderNo").innerHTML="Order No. is required.";
			return false;

		} else {
			// $("#OrderNo_msg1").hide();
		document.getElementById("errorderNo").innerHTML="";
			return true;
		}

	}
 
 
 function validateOrdeDate() {
	 	document.getElementById("errformDateOrderDate").innerHTML="";
		var orderdate = document.getElementById('formDateOrderDate').value;
		if (document.getElementById('formDateOrderDate').value == "") {
			document.getElementById("errformDateOrderDate").innerHTML = "Order Date is required.";
			
			return false;

		}

		else if (!validateDateDDMMYYYFn(orderdate)) {
			document.getElementById("errformDateOrderDate").innerHTML = "Enter valid date(dd-mm-yyyy) format";
			return false;
		} else if (!compareDateDDMMYYY(orderdate)) {
			document.getElementById("errformDateOrderDate").innerHTML = "Order Date should be equal or previous to Current Date ";
			return false;
		} else {
			document.getElementById("errformDateOrderDate").innerHTML="";
			return true;
		}

	}
 
 function validateEffecDate() {

		var effecdate = document.getElementById('formDateEffectiveDate').value;
		if (document.getElementById('formDateEffectiveDate').value == "") {
			document.getElementById("errformDateEffectiveDate").innerHTML = "Effective Date is required.";
			return false;

		}

		else if (!validateDateDDMMYYYFn(effecdate)) {
			document.getElementById("errformDateEffectiveDate").innerHTML = "Enter valid date(dd-mm-yyyy) format";
			return false;
		} 
		else {
			document.getElementById("errformDateEffectiveDate").innerHTML = "";

			return true;
		}
	}
 
 function validateGazPubDate() {

		var gazPubDate = document.getElementById('formDateGazPubDate').value;
		if (document.getElementById('formDateGazPubDate').value != "") {
			if (!validateDateDDMMYYYFn(gazPubDate)) {

				document.getElementById("errgazPubDate").innerHTML = "Enter valid date(dd-mm-yyyy) format";
				return false;

			}

			
			else {
				document.getElementById("errgazPubDate").innerHTML = "";
				return true;
			}

		}else{
			document.getElementById("errgazPubDate").innerHTML = "";
			return true;
		}

		

	}
 
 function validateSFile1() {
		var attachFile1 =  $("#gazettePublicationUpload").val();
		if (attachFile1 == "") {
			$( '#errgazettePublicationUpload').text("Please upload at atleast one file as Government Order");
			return false;

		} else {
			$( '#errgazettePublicationUpload').text("");
			return true;
		}

	}
 
 
 function compareDateDDMMYYY(orderdate) {
		var string_start = orderdate.split("-");
		var date_start = string_start[0];
		var month_start = string_start[1] - 1;
		var year_start = string_start[2];
		var ordeDate = new Date(year_start, month_start, date_start);

		if (days_between(ordeDate, new Date()) > 0) {
			return false;
		} else {
			return true;
		}

	}
 
 
 function validateDateDDMMYYYFn(DateOfBirth) {

		var Char1 = DateOfBirth.charAt(2);
		var Char2 = DateOfBirth.charAt(5);
		// alert(Char1); alert(Char2);

		var flag = false;

		if (Char1 == '-' && Char2 == '-') {
			// alert ('valid positions of non numeric characters.');
			flag = true;
		} else {
			// alert('invalid position of non numeric symbols');
			flag = false;
		}

		var day;
		var month;
		var year;

		day = DateOfBirth.substring(0, 2);
		month = DateOfBirth.substring(3, 5);
		year = DateOfBirth.substring(6, 10);

		// alert(day); alert(month);alert(year);
		if (validDay(day) && validMonth(month) && validYear(year) && (flag == true)) {
			// alert(' Valid Date')
			return true;
		} else {
			// alert('Invalid Date Format: Please enter dd-mm-yyyy for Date of
			// Birth!');
			return false;
		}

	}
 
function checkFileDetails() {
	
	var errors = new Array();
	var error = false;
	errors[0] = !validateOrdeNo();
	errors[1] = !validateOrdeDate();
	errors[2] = !validateEffecDate();
	errors[3] = !validateGazPubDate();
	errors[4] = !validateSFile1();
	for ( var i = 0; i < 5; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	if (error == true) {
		return false;
	} else {
		return true;
	}
	return false;
}	
</script>
</head>	
		<body>
		<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form action="denotifyULBRLBVillage.htm" method="post" commandName="denotifyGP" id="form1" enctype="multipart/form-data" name="denotifyULBRLB" class="form-horizontal">
						<form:hidden id="ulbsList" htmlEscape="true" path="ulbsListData"/>
						<form:hidden id="rlbsList" htmlEscape="true" path="rlbsListData"/>
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="denotifyULBRLBVillage.htm"/>" />
						<form:hidden path="transactionid" value="${tranId}" htmlEscape="true"/>
						<form:hidden htmlEscape="true" path="govtOrderConfig" value="${govtOrderConfig}" />
						<form:hidden htmlEscape="true" path="operation" value="C" />
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.DenotifyGPsTitle"/></h3>
                                </div>
                                
                        <div class="box-body">
                   <div class="box-header subheading">
                    <h4>Details of ULB in which the selected GP got merged :</h4>
                   </div>  
                   <div id="error_ulb_gp" class="errormsg"></div>
												<table id="example" class="table table-bordered table-striped dataTable no-footer" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><spring:message code="Label.Serialno" text="Serial No." htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.ULBCode" text="ULB Code" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.URBANLBNAME" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.URBANLBNAMELOCAL" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.ULBCOVERAGE" htmlEscape="true"></spring:message></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="deNotifiedVPList" varStatus="listDepartmentRow" items="${getVPULBList }">
															<tr>
																<td >
																	 <c:out value="${listDepartmentRow.index+1}" escapeXml="true"></c:out>
																</td>
																<td>
																	<c:out value="${deNotifiedVPList.localBodyCode}" escapeXml="true"></c:out>
																</td>
																<td >
																	<c:out value="${deNotifiedVPList.lBNameEnglish}" escapeXml="true"></c:out>
																</td>
																 <td > 
																	<c:out value="${deNotifiedVPList.lBNameLocal}" escapeXml="true"></c:out>
																 </td> 
																 <td > 
																	<c:if test="${ not empty getCoveragedData}">
																	<div id="divCoveragedLBDeatils" >
																		
																				
																							<table id="tblCoveragedLBDetails" class="table table-bordered table-hover" width="50%">
																								<thead>
																									<tr>
																										<th width="5%">S.No.</th>
																										<th><spring:message code="App.TYPEOFLANDREGION" text="LRTYPE"/></th>
																										<th><spring:message code="Label.COVERAGETYPE" /></th>
																										<th><spring:message code="Label.COVERAGEDETAIL"/></th>
																										<th><input type="checkbox" name="covered" id="coveredall1" class="linkdis masterTooltip" title="Select All"/></th>
																									</tr>
																								</thead>
																								<tbody>
																									<c:forEach var="varCoveragedEntity" items="${getCoveragedData}" varStatus="counter">
																										<tr id="${counter.index+1}">
																											<td><c:out value="${counter.index+1}" escapeXml="true"></c:out></td>
																											<td><c:out value="${varCoveragedEntity[1]}" escapeXml="true"></c:out></td>
																											<td><c:out value="${varCoveragedEntity[2]}" escapeXml="true"></c:out></td>
																											<td><c:out value="${varCoveragedEntity[3]}" escapeXml="true"></c:out></td>
																											<td><input type="checkbox" class="cb1" value="<c:out value='${varCoveragedEntity[0]}' escapeXml='true'></c:out>" name="covered" id="cb1${counter.index+1}"/></td>
																										</tr>
																									</c:forEach>
																								</tbody>
																							</table>
																						
																			
																		</div>
																	</c:if>
																 </td>																				
															</tr>										
														</c:forEach>																				
												</tbody>
												
											</table>									 
                 </div> 
                 
                 
                 
                 
             
                      
                   <div class="box-body">
					<div class="box-header subheading"><h4>Invalidated Village Panchayats and their coverage details :</h4></div>
					<div id="error_rlb_gp" class="errormsg"></div>
												<table id="example1" class="table table-bordered table-striped dataTable no-footer" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><spring:message code="Label.Serialno" text="Serial No." htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.RLBCode" text="RLB Code" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.RLBANLBNAME" text="RLB Name(English)" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.RLBANLBNAMELOCAL" text="RLB Name(Local)" htmlEscape="true"></spring:message></th>
															<th><spring:message code="Label.coveredvillage" htmlEscape="true"></spring:message></th>
															<th><input type="checkbox" name="covered" id="coveredall2" class="linkdis masterTooltip" title="Select All"/></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="deNotifiedVPList" varStatus="counter" items="${getVpRLBList }">
															<tr id="${counter.index+1}">
																<td class="sorting" tabindex="0" aria-controls="example">
																	 <c:out value="${counter.index+1}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.localBodyCode}" escapeXml="true"></c:out>
																</td>
																<td class="sorting" tabindex="0" aria-controls="example">
																	<c:out value="${deNotifiedVPList.lBNameEnglish}" escapeXml="true"></c:out>
																</td>
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:out value="${deNotifiedVPList.lBNameLocal}" escapeXml="true"></c:out>
																 </td> 
																 <td class="sorting" tabindex="0" aria-controls="example"> 
																	<c:out value="${deNotifiedVPList.coveraged}" escapeXml="true"></c:out>
																 </td>		
																 <td><input type="checkbox" class="cb2" value="<c:out value='${deNotifiedVPList.localBodyCode}' escapeXml='true'></c:out>" name="covered" id="cb2${counter.index+1}"/></td>																		
															</tr>									
														</c:forEach>																				
												</tbody>
												
											</table>				
                      </div>
                   
           <div class="box-header subheading">
           
           
            <h4> <spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message></h4>
             </div>
									 <%@ include file="../govtbody/ExistingGovernmentOrderVillageToDenotifycp.jsp"%> 
									<br /> <br />
									<%-- <%@ include file="../govtbody/GovtOrderUploadFormcp.jsp"%> 	 --%>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                       <input type="button" class="btn btn-success" id="createDenotify" name="submmit" value="De-Notify" onclick="getValidatedForm();"></input>
						<input type="button" class="btn btn-danger" id="close" name="close" value="Close" onclick="javascript:go('home.htm');"/>
						
                 </div>
           </div>   
       </div> 
    <!-- ---model start here    -->
    <div class="modal fade" id="dialog-confirm" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><spring:message code="Label.DenotifyGPsTitle"  htmlEscape="true"></spring:message></h4>
        </div>
        <div class="modal-body">
              <p>  <spring:message code="Error.dialog-confirm-denotify"  htmlEscape="true"></spring:message></p>  
              
				
	
	 </div>
       
        <div class="modal-footer">
          <input class="btn btn-primary" type="button" id="aaa" onclick="modalYes();"  value="Yes" />
        	<input class="btn btn-danger" type="button" id="goClose" onclick="modalNo();" value="Close" />	
        </div>
      </div>
      
    </div>
</div>
       
       
       
       
       
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
	</body>
</html>