<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>
<link rel="stylesheet" href="js/pdf/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="js/pdf/css/buttons.dataTables.min.css">

<script type="text/javascript" src="js/pdf/jquery-1.12.3.js"></script> 
 <script type="text/javascript" src="js/pdf/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="js/pdf/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/pdf/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/pdf/jszip.min.js"></script>
<script type="text/javascript" src="js/pdf/pdfmake.min.js"></script>
 <script type="text/javascript" src="js/pdf/vfs_fonts.js"></script> 
 <script type="text/javascript" src="js/pdf/buttons.html5.min.js"></script>
  <script type="text/javascript" src="js/pdf/buttons.print.min.js"></script>



<!-- <link rel="stylesheet" href="css/buttons.dataTables.min.css">
<link rel="stylesheet" href="css/jquery.dataTables.min.css"> -->
  <%-- <link href="<c:url value="css/bootstrap.min.css" />" rel="stylesheet"> --%>
<!--   <script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script> -->
<title><spring:message htmlEscape="true"  code="Label.VIEWDISTPCACMAPPING"></spring:message></title>
<script type="text/javascript">
$(document).ready(function() {
	$('#example').DataTable({
	    dom: 'Bfrtpi',
	    "pageLength": 100,
	    buttons: [
	              // 'colvis', // custom columns include JS https://cdn.datatables.net/buttons/1.0.1/js/buttons.colVis.min.js
	              // 'colvisRestore', // restore default columns include JS https://cdn.datatables.net/buttons/1.0.1/js/buttons.colVis.min.js
	             /*  'pdf', */ { // default PDF and customized PDF
	           extend: 'pdfHtml5',
	           title: 'Freeze/Unfreeze Pc/Ac mapping of District', // report header/title
	           orientation: 'landscape',
	           pageSize: 'A4',
	           pageMargins: [ 0, 0, 0, 0 ], // try #1 setting margins
	           margin: [ 0, 0, 0, 0 ], // try #2 setting margins
	           text: '<u>E</u>xport Page (PDF)',
	           key: { // press E for export PDF
	               key: 'e',
	               altKey: false
	           }
	           , content: [{ style: 'fullWidth' }]
	      		 , styles: { // style for printing PDF body
	      					fullWidth: { fontSize: 18, bold: true, alignment: 'right', margin: [0,0,0,0] }
	      		 },
	           download: 'download',
	           exportOptions: {
	               modifier: {
	           				 pageMargins: [ 0, 0, 0, 0 ], // try #3 setting margins
	               		 margin: [ 0, 0, 0, 0 ], // try #4 setting margins
	                   alignment: 'center'
	               }
	               , body : {margin: [ 0, 0, 0, 0 ], pageMargins: [ 0, 0, 0, 0 ]} // try #5 setting margins         
	               , columns: [0,1,2,3,4,5,6,7,8,9] //column id visible in PDF    
	            	 , columnGap: 1 // optional space between columns
	           }
	       }],
	});
	
	
} );
</script>
</head>
<body>
	
	    <table width="100%">
	    <tr>
	    <td>
	     <div id="frmcontent">
	    <div class="frmhd" style="width:100%">
		<!-- Main Heading starts -->
			<h3 class="subtitle"><spring:message code="Label.freezePcAcMapping" text="Freeze/Unfreeze Pc/Ac mapping of District "/></h3>
			<%-- <ul class="item_list">
				<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Button.HELP"></spring:message> </a></li>
			</ul> --%>
		</div>
		<div class="clear">	</div>	
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
				 <form:form  id="freezeDistrictPcAc" commandName="mappedLbWardDisttrict" enctype="multipart/form-data"> 	 
			<%-- 	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="freezeDistrict.htm"/> --%>
				  <form:hidden path="nodalOfficerPK.nodalUserId" value="${nodalOfficer.nodalOfficerPK.nodalUserId}"/>
				  <form:hidden path="nodalUserDistrict" value="${nodalOfficer.nodalUserDistrict}"/>
					<%-- <form:hidden path="lbLRType" value="L"/>  --%>
					<!-- General Details of District Started-->
					<!-- <h3>Freeze/Unfreeze Pc/Ac mapping of District</h3><br> -->
					
					<div class="form_block">
					<div class="frmpnlbg">
						  <table  width="100%" align="center">
						  <tr >
						    <td width="30%">Name Of Nodal Officer</td> <td width="70%">${nodalOfficer.nodalUserName} </td>
						  </tr>
						   <tr >
						    <td>E-Mail</td> <td> ${nodalOfficer.nodalUserEmail}</td>
						  </tr>
						   <tr>
						    <td>Mobile </td> <td>${nodalOfficer.nodalUserMobile} </td>
						  </tr>
						   <tr>
						    <td>Designation</td> <td>${nodalOfficer.nodalUserDesignation} </td>
						  </tr>
						  </table>
				   </div>
				   <div class="frmpnlbg">
						   <c:if test="${isFreeze eq false}">
					      <table  width="100%"  style="margin:20px 0px 0px 20px" align="center">
						  <tr>
						  <td width="28%">Upload Signed Copy</td>
						  <td width="72%">
                            <input type="file" id="pFile" name="attachFile" accept=".pdf" />
                          </td>
						  </tr>
						  <tr>
						   <td colspan="2"></td>
						  
						  </tr>
						   <tr>
						   <td></td>
						  <td align="left">
						  	<input type="submit" id="freezeBtn" class="bttn"  value="<spring:message htmlEscape="true" code="Freeze"/>"/>  
						  </td>
						  </tr>
						  </table>
						 <div id="unfreezeErr" style="color: red;"></div>	
						</c:if> 
						<c:if test="${isFreeze eq true}">
						<table  width="100%" style="margin:20px 0px 0px 20px" align="center">
						  <tr>
						  <td  width="28%">Uploaded Signed Copy</td>
						  <td>
                            <a href="showPcAcMappingPDF.htm?<csrf:token uri='"showPcAcMappingPDF.htm'/>">${pathView}</a>
                            </td>
						  </tr>
						  <tr>
						  <td  align="center">
						  <input type="submit" id="Unfreezebtn" style="margin:10px 0px 0px 100px" class="bttn" param="UnFreeze" value="<spring:message htmlEscape="true" code="UnFreeze"/> "/>
					
						   </td>
						  </tr>
						  </table>
					</c:if> 
					</div>
						     <h4>PC/AC Mapping Details</h4>
						     <table id="example" class="display" cellspacing="0">
						     <thead>
						                    <tr>
						                    <th ><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
						                 	<th ><spring:message htmlEscape="true"  code="Label.ASSEMBLY/PARLIAMENT/CONSTITUENCY"></spring:message></th>
						            
						                  <th ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></th>
						                   <th ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAME"></spring:message></th>
						                  <th ><spring:message htmlEscape="true"  code="Label.VILLAGECODE"></spring:message></th>
						                   <th  ><spring:message htmlEscape="true"  code="Label.VILLAGENAME"></spring:message></th>
						                  <th  ><spring:message htmlEscape="true"  code="Label.LOCALBODYCODE"></spring:message></th>
						                   <th ><spring:message htmlEscape="true"  code="Label.LBNAME"></spring:message></th>
						                  <th  ><spring:message htmlEscape="true"  code="Label.LBTYPE"></spring:message></th>
						                  
						                  <th style="width: 10%"><spring:message htmlEscape="true"  code="Label.WARDNAME"></spring:message>(<spring:message htmlEscape="true"  code="Label.WARDCODE"></spring:message>)</th>
						                 
						                   
						                    </tr>
						                    </thead>
						                     <tbody>
						                   <c:forEach var="mappedLbWard"  items="${mappedLbWardDisttrictWise}" varStatus="counts">
						                      <tr>
						                       <td>${ counts.count }</td>
						                       <td> ${mappedLbWard.assemblyConstituency}(${mappedLbWard.parliamentConstituency}) </td>

						                          <td>${mappedLbWard.subdistrictCode} </td>
						                           <td>${mappedLbWard.subdistrictNameEnglish} </td>
						                            <td>${mappedLbWard.villageCode} </td>
						                       <td>${mappedLbWard.villageNameEnglish} </td>
						                             <td>${mappedLbWard.localBodyCode} </td>
						                             <td>${mappedLbWard.localBodyNameEnglish} </td>
						                             <td>${mappedLbWard.localBodyTypeName} </td>
						                             <c:choose>
						                             <c:when test="${not empty mappedLbWard.wardCode}">
						                              <td>${mappedLbWard.wardNameEnglish} (${mappedLbWard.wardCode})</td>
						                              </c:when>
						                              <c:otherwise>
						                              <td></td>
						                              </c:otherwise>
						                              </c:choose> 
						                    </tr>
						                      </c:forEach>
						                      </tbody>
						     </table>
						
					</div>
					<br/>
			 </form:form>	
	</div>
	</div>
	    </td>
	    </tr>
	    
	    </table>
</body>
</html>

<script type="text/javascript">
  
   $('#freezeBtn').click(function() {
	   var file = $('#pFile').prop('files');
	  if(file.length==0){
		  $("#unfreezeErr").html("Kindly upload a pdf file");
		   return false;
	  }
      
		   var filename = $('input[type=file]')[0].files[0].name.toLowerCase();
	     if(filename.indexOf("pdf") == -1){
	    	  $("#unfreezeErr").html("Kindly upload a signed copy in order to freeze PC/AC mapping");
		   return false;
	   }
	   else{ 
		document.forms['freezeDistrictPcAc'].method = "POST";
		document.forms['freezeDistrictPcAc'].action = "freezeDistrictPcAcMapping.htm?<csrf:token uri='"freezeDistrictPcAcMapping.htm'/>";
		document.forms['freezeDistrictPcAc'].submit(); 
	   }
   });
   
   </script>
   <script type="text/javascript">
   
   $('#Unfreezebtn').click(function() {
	   document.forms['freezeDistrictPcAc'].method = "POST";
		document.forms['freezeDistrictPcAc'].action = "unfreezeDistrictPcAcMapping.htm?<csrf:token uri='"unfreezeDistrictPcAcMapping.htm'/>";
		document.forms['freezeDistrictPcAc'].submit(); 
   });
      
</script>