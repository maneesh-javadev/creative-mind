<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../../common/taglib_includes.jsp"%>
<%@include file="ManageLBJavascript.jsp"%>


 <c:set var="URBAN_CONSTANT" value="<%=com.cmc.lgd.localbody.rules.LocalBodyConstant.LB_URBAN.toString()%>"></c:set>	
 <script type='text/javascript' src="js/notie/notie.js"></script>
<script type='text/javascript' src="js/notie/notie.min.js"></script>

<link rel="stylesheet" href="js/notie/notie.css">
<link rel="stylesheet" href="js/notie/notie.min.css">
 <script type="text/javascript">
		
 

function validateSubmit(){ 
	
		var filecount = document.getElementById('gazettePublicationUpload').value;
		var orderNo = document.getElementById('orderNo').value;
		
		var DateOrderDate = $('#formDateOrderDate').val();
		var EffectiveDate = $('#iParamEffectiveDate').val();
		var formDateGazPubDate = document.getElementById('formDateGazPubDate').value;
         var errorFlag =0;
       var  msg="";
		var formDateOrderDate = document.getElementById('formDateOrderDate').value;

		if (orderNo != '' && filecount != ''  && formDateOrderDate != '') {
			

			if(filecount.length>0){
				var file_size = $('#gazettePublicationUpload')[0].files[0].size;
				var ext = $('#gazettePublicationUpload').val().split('.').pop().toLowerCase();
				  if($.inArray(ext, ['gif','png','jpg','jpeg','pdf','pjpeg']) == -1) {
						notie.alert({ type: 2, text: 'Kindly Attach Approved Doc of gif,jpg,pdf,png,jpeg,pjpeg type', time: 4 });
						errorFlag = 0;
					  return false;
				  }
			}
			
			
			if(file_size>5242880) {
				notie.alert({ type: 2, text: 'Kindly Attach Approved Doc of size less than 5 Mb', time: 4 });

				errorFlag = 0;
				return false;
			}
			
			var EffectiveDateVal = new Date(EffectiveDate);
			var DateOrderDateVal = new Date(DateOrderDate);

			
			
			var effectiveCompareDate  = $("#iParamEffectiveDate").val(); //2013-09-5
			var orderCompareDate    = $("#formDateOrderDate").val(); //2013-09-10
			var dateCompareGazPubDate    = $("#formDateGazPubDate").val(); //2013-09-10


			if(Date.parse(effectiveCompareDate)<=Date.parse(orderCompareDate)){
				notie.alert({ type: 2, text: 'order date should be less than effective date ', time: 4 });
				errorFlag = 0;
				return false;
			}
			
			if(Date.parse(effectiveCompareDate)<=Date.parse(dateCompareGazPubDate)){
				notie.alert({ type: 2, text: 'Gaz. Pub Date  should be less than effective date ', time: 4 });
				errorFlag = 0;
				return false;
			}

			errorFlag = 1;

			
			
		} else {

			if (orderNo == "" || formDateOrderDate == "" || formDateGazPubDate == "" || filecount == "") {
			if (orderNo == "") {
				
				errorFlag = 0;
				notie.alert({ type: 2, text: 'please Enter Order No.', time: 4 });
/* 				$( '#OrderNo_error').text("<spring:message code="error.required.ORDERNUM" htmlEscape="true"/>"); 
 */
				return false;
			}
			
					if (filecount == "") {
				
				errorFlag = 0;
				notie.alert({ type: 2, text: 'please attach  file', time: 4 });
/* 				$( '#gazettePublicationUpload_error').text("Please upLoad File"); 
 */
				return false;
			}
			if (formDateOrderDate == "") {
				errorFlag = 0;
		     notie.alert({ type: 2, text: 'please Enter Order Pub Date', time: 4 });
/* 		    $( '#OrderDateBlank_error').text("<spring:message htmlEscape="true" code="error.required.ORDERDATE"/>"); 
 */
		
			return false;
			}
		
		}
		}
		/* var countries = [];
		$. each($(".rp_selected option:selected"), function(){
		countries. push($(this). val());
		}); */
		if (errorFlag == 1) {
			
			
			document.localBodyForm.method = "post";
			document.localBodyForm.action = "modifyLocalBody.htm?<csrf:token uri='modifyLocalBody.htm'/>";
			document.localBodyForm.submit();
		   
		}
		else
			{
		     notie.alert({ type: 2, text: 'please Enter Required Details Properly', time: 4 });
		     return false;
			}
	}
		
	</script>
</head>
<body class="dt-example">
	<section class="content">
		<div class="row">
       		<section class="col-lg-12 ">
           		<div class="box">
   					<div class="box-header with-border">
                		<h3 class="box-title">CORRECT GOVT ORDER OF LOCALBODY</h3>
	                </div>
	                <div class="box-body">
	                
	                <form:form  class="form-horizontal" name="localBodyForm" onsubmit="return validateSubmit();" method="post" id="localBodyForm" commandName="localBodyForm" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"	value="" />
 						<form:hidden path="processAction"/>
						<form:hidden path="localBodyCreationType"/>
						<form:hidden path="localBodyCode" id="paramLBCode"/>
						<form:hidden path="localBodyTypeCode"/>
						<form:hidden path="parentLocalBodyCode"/>
						<form:hidden path="parentLocalBodyCode"/>
						<input type="hidden" name="orderCode"  value ="${orderCode}"/>
			            <input type="hidden" name="effectiveDate" value ="${getEffective}"/>
 						<input type="hidden" name="attachmentId" value ="${attachmentId}"/>
 						 <input type="hidden" name="localBodyNameEnglish" value ="${localBodyForm.localBodyNameEnglish}"/>
 						 <input type="hidden" name="entityWiseId" value ="${govtWiseId}"/>
 						  <input type="hidden" name="localBodyVersion" value ="${localBodyVersion}"/>
 						 <input type="hidden" name="minorVersion" value ="${minorVersion}"/>
 						 
 						
 					
						
					
						<div class="box-header subheading">
							<h4><spring:message htmlEscape="true" code="Label.GENERALLOCALGOVTBODYDETAILS"></spring:message></h4>
                    	</div> 
                    	
                    	<table  class="table table-bordered table-hover" cellspacing="0" width="100%">
		                	    <tbody>					    
									<c:if test="${localBodyTableObj.isdisturbed}">
									<tr>
										<td><spring:message htmlEscape="true"  code="Label.DISTURBEDSTATE"></spring:message></td>
										<td><img src="<%=contextpthval%>/images/red_flg.png" width="13" height="9" /></td>
									</tr>
									
									</c:if>
									<div class="form-group">
                 			<label for="districtAliasInEn" class="col-sm-3 control-label">
                 			<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMEENGLISH"></spring:message>
                 			</label>
								  <div class="col-sm-6"><input value="${localBodyForm.localBodyNameEnglish}" class="form-control" readonly="readonly"  escapeXml="true"/>
								</div>
							</div>
									<div class="form-group">
                 			<label for="districtAliasInEn" class="col-sm-3 control-label">
                 			<spring:message htmlEscape="true"  code="Label.LOCALBODYNAMELOCAL"></spring:message></label>
								  <div class="col-sm-6"><input value="${localBodyForm.localBodyNameLocal}" class="form-control" readonly="readonly"  escapeXml="true"/>
										
										</div>
									</div>
								
			    					</tr>
				   				</tbody>	
		                	</table>
                    	
                    	
                    		<div class="modal fade" id="myModal1">
	  							<div class="modal-dialog" role="document">
	    							<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">Local Body</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										</div>
									      <div class="modal-body">
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>S.No.</th>
														<th><spring:message code='LOCALBODYNAMEENGLISH' htmlEscape='true'/></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="pName" items="${previousNames}" varStatus="counter">
														<tr>	
															<td><c:out value="${counter.count}" escapeXml="true"/></td>
															<td><c:out value="${pName}" escapeXml="true"/></td>
														</tr>	
													</c:forEach>
												</tbody>
											</table>
									      </div>
									      <div class="modal-footer">
									        <div class="col-sm-offset-2 col-sm-10">
								            	<div class="pull-right">
												<button class="btn btn-dengar" type="button" data-dismiss="modal" id="goClose">Close</button>	
								            	</div>
								            </div>
									      </div>
						   			 </div>
						  		</div>
							</div>
                    	
                    	
                    	
                    	 <%@include file="../ExistingGovernmentOrderCpy1.jsp"%>
                    	
                    	 <c:if test="${not empty getPreviousAttachedFiles}">
		    
		      <table class="table table-striped table-bordered" cellspacing="0" width="100%">
						 <thead>
					         <th><spring:message htmlEscape="true" code="File Name"></spring:message></th>
					         <th><spring:message htmlEscape="true" code="File Size"></spring:message></th>
					         <th><spring:message htmlEscape="true" code="File Contant Type"></spring:message></th>
					        
						</thead>
					<tbody>					    
				         <c:forEach items="${getPreviousAttachedFiles}" var="villageHistory" varStatus="listVillageRow" >
							<tr>
								 <td>${villageHistory.fileName}</td>
								<td>${sizeOfFile} kb</td>
								<td>${villageHistory.fileContentType}</td>														
								
						   </tr>
						</c:forEach>
					</tbody>
				</table>
		    
		    
	                    </c:if>
                    	
                    	
                    	<div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
			<input class="btn btn-success" id="submit" type="submit"   value="<spring:message htmlEscape="true" code="Button.UPDATE"/>" param="UPDATE"/>
									
					 <input class="btn btn-danger" type="button"  value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
				
	                        </div>
	                    </div>   
                  </div>     
                    	
					</form:form>
	                
	                
	                </div>
	             </div>
	          </section>
	      </div>
	 </section>
</body>
</html>