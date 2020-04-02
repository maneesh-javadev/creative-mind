<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		$( '#actionFetchDetails' ).click(function() {
	 		validationForm();
	 		$("#entitesForMsg").val($("#ddSourceState option:selected").text());
	 		
		});
	});
	
	function validationForm()
	{
	var capchaImgVal = document.getElementById('captchaAnswer').value;
	/* Empty Captcha Check */
	if(capchaImgVal == null || capchaImgVal == ""){
		document.getElementById("errSelectCaptcha").innerHTML = "<font color='red'><br><b><spring:message code="Error.EmptyCaptcha"/></b></font>"; 
	    return false;
	}
	}
</script>
</head>
<body class="dt-example">
	<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                     <form:form action="viewGlobalStateWiseLocalBodyReport.do" method="POST" id="reportingStatewiseGISMappedLBEntity" commandName="reportingStatewiseGISMappedLBEntity" class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="viewGlobalStateWiseLocalBodyReport.do"/>" />
					<form:hidden path="entitesForMessage" id="entitesForMsg"/>
						
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Statewise GIS Mapped LocalBody" htmlEscape="true"></spring:message></h3>
                                </div>
                                
	                    <div id="dialogBX" style="display: none;">
							<iframe id="myIframe" width="910" height="650"></iframe>
						</div> 
                        
                        <c:choose>
					<c:when test="${showSearchResult}">
					 <div class="box-body">
		                    <div class="form-group">
		  <label  class="col-sm-4 control-label" for="sel1"></label>
		  <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
		                   <div class="form-group">
		   <label for="captchaAnswer" class="col-sm-4 control-label"><spring:message code="captcha.message"	htmlEscape="true" /><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px" class="form-control" maxlength="5"/>
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" cssStyle="color:red"/>
		                     
								<c:if test="${not empty captchaFaliedMessage and not captchaFaliedMessage}">
									<br/><label><!-- Used Label, please dont remove. --></label>
									<span class="mandatory"><strong><spring:message code="captcha.errorMessage"/></strong></span>
								</c:if>
		                       </div>
	                    	</div>
	                    	
	                    	
	                    	
	                    	
	                    	
					  </div>
					
					
			<div class="box-footer">
		           <div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
		                    <button type="submit" class="btn btn-success" id="actionFetchDetails" ><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
							<button type="button" class=" btn btn-danger" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>	
		                 </div>
		           </div>   
       			</div>
									
					</c:when>
				    <c:otherwise>
					   <div class="box-body" >
					     <div class="table-responsive ">
							<table id="example" class="table table-bordered table-hover" >
								<thead>
									<tr>
										<th ><spring:message code="SNO"/></th>
										<th ><spring:message code="STATE NAME"/></th>
										<th ><spring:message code="TOTAL LOCAL BODY"/></th>
										<th ><spring:message code="MAPPED LOCAL BODY"/></th>
										<th ><spring:message code="MAPPED PERCENT"/></th>
														
									 </tr>
									</thead>
								<tbody><c:set var = "sumLocalBody" value = "${0}"/>
										<c:set var = "sumTotalMappedLB" value = "${0}"/>
									<c:forEach var="listStateDetails" varStatus="rowstatus" items="${SEARCH_LOCALBODY_KEY}">
										<tr >
											<td width="6%"><c:out value="${rowstatus.count}"/></td>
											<td><c:out value="${listStateDetails.stateName}"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.totalLocalBody}"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.mappedLocalBody}"></c:out></td>
											<td align="left"><c:out value="${listStateDetails.mappedPercent}"></c:out></td>
											<c:set var = "sumLocalBody" value = "${sumLocalBody+listStateDetails.totalLocalBody}"/>
											<c:set var = "sumTotalMappedLB" value = "${sumTotalMappedLB+listStateDetails.mappedLocalBody}"/>
										</tr>
									</c:forEach>
								</tbody>
								<tr>
								 <th ></th>
								 <th ></th>
								 <th ><spring:message code="Grand Total : ${sumLocalBody}"/></th>
								 <th ><spring:message code="Grand Total: ${sumTotalMappedLB}"/></th>
								 <th ></th>
							   </tr>
							</table>
							</div>
									</div>
									
									
					<div class="form-group" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						     
						<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
						</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						 
				  </div>
					     
					     
						
					   <div class="box-footer" id="showbutton">
                      	<div class="col-sm-offset-2 col-sm-10">
		                 <div class="pull-right">
		                 	 <button type="button" class="btn btn-info" onclick="javascript:location.href='globalViewStatewiseLocalBody.do?<csrf:token uri='globalViewStatewiseLocalBody.do'/>'" ><spring:message code="Button.BACK"/></button>
						  	<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do'" ><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message></button>
		                 </div>
          				 </div>   
       				</div>
					</c:otherwise>
					</c:choose>
    </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
</body>
</html>