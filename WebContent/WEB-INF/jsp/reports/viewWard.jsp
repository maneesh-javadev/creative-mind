
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../homebody/commanInclude.jsp"%>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
 
<script src="js/lgd_localbody.js"></script>
<script src="js/validationWard.js"></script>
<script src="js/validation.js"></script>
<script type="text/javascript" src="js/viewwards.js"></script>



<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrLocalGovtBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService .js'></script>


<script language="javascript">
function CallPrint()
{ 
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("divData"); 
	//document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0'); 
	//alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML); 
	Win4Print.document.close(); 
	Win4Print.focus(); 
	Win4Print.print(); 
	Win4Print.close(); 
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
}

function setBack()
{
	document.getElementById('divData').style.display = 'none';
	document.getElementById('divData').style.visibility = 'hidden';
	document.getElementById('viewentity').style.display = 'block';
	document.getElementById('viewentity').style.visibility = 'visible';
	document.getElementById('showhelp').style.display = 'block';
	document.getElementById('showhelp').style.visibility = 'visible';
	document.getElementById('showprint').style.display = 'none';
	document.getElementById('showprint').style.visibility = 'hidden';
	document.getElementById('showbutton').style.display = 'none';
	document.getElementById('showbutton').style.visibility = 'hidden';
}
function ClearAll()
{
	 var obj='<c:out value="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}" escapeXml="true"></c:out>';
	 //alert(obj);
	 if(obj=="true")
		 {
		 
		 	document.getElementById('viewentity').style.display = 'none';
			document.getElementById('viewentity').style.visibility = 'hidden';
		 	document.getElementById('showhelp').style.display = 'none';
			document.getElementById('showhelp').style.visibility = 'hidden';
			document.getElementById('showprint').style.display = 'block';
			document.getElementById('showprint').style.visibility = 'visible';
			document.getElementById('showbutton').style.display = 'block';
			document.getElementById('showbutton').style.visibility = 'visible';
		 }
	 else
		 {
			 document.getElementById('viewentity').style.display = 'block';
			document.getElementById('viewentity').style.visibility = 'visible';
		 	document.getElementById('showhelp').style.display = 'block';
			document.getElementById('showhelp').style.visibility = 'visible';
			document.getElementById('showprint').style.display = 'none';
			document.getElementById('showprint').style.visibility = 'hidden';
			document.getElementById('showbutton').style.display = 'none';
			document.getElementById('showbutton').style.visibility = 'hidden';
			
		 }
}


  if ( window.addEventListener ) { 
	     window.addEventListener( "load",ClearAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", ClearAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = ClearAll;
	  }
</script>
<%@include file="../common/dwr.jsp"%>
</head>
<body onload="document.getElementById('state').selectedIndex=0;">
			
<section class="content">

 <!-- Main row -->
 <div class="row">
     <!-- main col -->
	<div class="container">
	<section class="col-lg-12 prebox">
     	<div class="box">
            <div class="box-header with-border" id="frmcontent">
              <h3 class="box-title"><spring:message code="Label.VIEWWARD" htmlEscape="true"></spring:message></h3>
            	<c:if test="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}">		
            		<a id="showprint" href="#" class="pull-right"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" /></a>
            	</c:if>
            </div><!-- /.box-header -->
                
                <!-- form start -->
                <form:form action="viewWard.do" class="form-horizontal" method="POST" id="viewWardForm" commandName="localGovtBodyForm" enctype="multipart/form-data">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createWardforPRI.htm"/>" />
					
					<div id="viewentity">
					<div class="box-body" >
						<div class="box-header subheading"><h4><spring:message code="Label.SELECTLOCALBODY" htmlEscape="true"></spring:message></h4></div>
						
		                  
		                    <div class="form-group">
			                    <label class="col-sm-4 control-label"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			                    <div class="col-sm-6">
			                     	<form:select path="stateNameEnglish" class="form-control" id="state" onchange="getLocalBodyList(this.value);">
										<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
										<form:options items="${stateList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select>
									<div id="errSelectState" class="errormsg"></div>
		                 		</div>
		             		</div>
		                    
		                    
		                    <div class="form-group ">
			                    <input type="hidden" id="cattype" /> 
			                    <input type="hidden" id="level" />
								<input type="hidden" name="lblc" id="lblc" />
			                      <label class="col-sm-4 control-label"><spring:message	code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
			                      <div class="col-sm-6">
			                      	<form:select path="lgd_LBTypeName" id="ddLgdLBType" htmlEscape="true" onfocus="validateOnFocus('ddLgdLBType');helpMessage(this,'ddLgdLBTypeMsg');"
										 onchange="hideShowDivforWard(this.value,'${districtCode}','${lbType}');" onkeydown="validateOnKeyPessDown(event,'ddLgdLBType','char')" onblur="vlidateOnblur('ddLgdLBType','1','15','c');hideHelp();"
										 class="form-control">
										<form:option selected="selected" value=""	label="--select--" />
									</form:select>	
										<div id="errSelectLocalBodyType" class="errormsg"></div><br>
			                     </div>
		                    </div>
	                          
	                       
	                       <div id="tr_district2" style="visibility: hidden; display: none;" class="form-group">
		                      <label class="col-sm-4 control-label"><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></label>
		                      <div class="col-sm-6">
		                      	<form:select htmlEscape="true" path="lb_lgdPanchayatName" class="form-control" id="wardUrbanLocalBody" onfocus="validateOnFocus('wardUrbanLocalBody');helpMessage(this,'wardUrbanLocalBodyMsg');" 
									onchange="remove_error(4);getCovereSubDistUrbanExWardList(this.value)" onkeydown="validateOnKeyPessDown(event,'wardUrbanLocalBody','char')" onblur="vlidateOnblur('wardUrbanLocalBody','1','15','c');hideHelp();">
									<form:option value="0"><spring:message code="Label.SELECTLOCALBODY"></spring:message></form:option>
								</form:select>
								<div id="errSelectUrban" class="errormsg" style="visibility: hidden; display: none;"></div>														
								<div id="wardUrbanLocalBody_error" class="errormsg"><spring:message code="error.blank.TEHSILP" htmlEscape="true"></spring:message></div>
								<div id="wardUrbanLocalBodyMsg" style="display:none"><spring:message code="error.blank.TEHSILP" htmlEscape="true"/></div>
								<div class="errormsg" id="wardUrbanLocalBody_error1"><form:errors path="lb_lgdPanchayatName" htmlEscape="true"/></div>  
								<div class="errormsg" id="wardUrbanLocalBody_error2" style="display: none" ></div><br>
		                     </div>
		                  </div>
		                  	                       
	                       <div class="form-group" id="tr_village_dist" style="visibility: hidden; display: none;">
		                     <label id="firstlevel" class="col-sm-4 control-label"><spring:message code="Label.SELECT" htmlEscape="true"><span class="mandatory">*</span></spring:message></label>
		                       <div class="col-sm-6">
		                        <form:select path="lgd_LBDistListAtVillageCA" class="form-control" htmlEscape="true" id="ddLgdLBDistListAtVillageCA" onfocus="validateOnFocus('ddLgdLBDistListAtVillageCA');helpMessage(this,'ddLgdLBDistListAtVillageCAMsg');"
									onchange="remove_error(3);callList(this.value);" onkeydown="validateOnKeyPessDown(event,'ddLgdLBDistListAtVillageCA','char')" onblur="vlidateOnblur('ddLgdLBDistListAtVillageCA','1','15','c');hideHelp();">
									<form:option selected="selected" value="" label="--select--" />
									<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
								</form:select>
									<div id="errSelectVIP" class="errormsg" style="visibility: hidden; display: none;"></div>
								    <div id="ddLgdLBDistListAtVillageCA_error" class="errormsg"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"></spring:message></div>
									<div id="ddLgdLBDistListAtVillageCAMsg" style="display:none"><spring:message code="error.SOURCESELECTLOCALBODYPARENT" htmlEscape="true"/></div>
									<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error1"><form:errors path="lgd_LBDistListAtVillageCA" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddLgdLBDistListAtVillageCA_error2" style="display: none" ></div><br>
		                       </div>
		                   </div>
		                    
		                   <div class="form-group" id="tr_village_inter" style="visibility: hidden; display: none;">
		                     <label id="secondlevel" class="col-sm-4 control-label"><spring:message	code="Label.SELECT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                      	 <form:select path="lgd_LBInterListAtVillageCA" class="form-control" id="ddLgdLBInterListAtVillageCA" htmlEscape="true"
									onfocus="validateOnFocus('ddLgdLBInterListAtVillageCA');helpMessage(this,'ddLgdLBInterListAtVillageCAMsg');" onchange="remove_error(3);getVillagePanchWardbyIntercode(this.value);"
									onkeydown="validateOnKeyPessDown(event,'ddLgdLBInterListAtVillageCA','char')" onblur="vlidateOnblur('ddLgdLBInterListAtVillageCA','1','15','c');hideHelp();">
									<form:option selected="selected" value="" label="--select--" />
								</form:select>
									<div id="errSelectVDP" class="errormsg" style="visibility: hidden; display: none; "></div>
								 	<div id="ddLgdLBInterListAtVillageCA_error" class="errormsg"><spring:message code="error.blank.INTERMEDIATEP" htmlEscape="true"></spring:message></div>
									<div id="ddLgdLBInterListAtVillageCAMsg" style="display:none"><spring:message code="error.blank.INTERMEDIATEP" htmlEscape="true"/></div>
									<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error1"><form:errors path="lgd_LBInterListAtVillageCA" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddLgdLBInterListAtVillageCA_error2" style="display: none" ></div>
		                       </div>
	                    	</div>
	                    	
	                    	<div class="form-group" id="tr_village_pan" style="visibility: hidden; display: none;">
		                     <label id="thirdlevel" class="col-sm-4 control-label"><spring:message	code="Label.SELECT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                       <div class="col-sm-6">
		                      	 <form:select path="lgd_LBVillageSourceAtVillageCA" class="form-control" htmlEscape="true" id="ddLgdLBVillageSourceAtVillageCA" onchange="remove_error();getLGBforCoveredVillageListExWard(this.value);"
									onfocus="validateOnFocus('ddLgdLBVillageSourceAtVillageCA');helpMessage(this,'ddLgdLBVillageSourceAtVillageCAMsg');" onkeydown="validateOnKeyPessDown(event,'ddLgdLBVillageSourceAtVillageCA','char')"
									onblur="vlidateOnblur('ddLgdLBVillageSourceAtVillageCA','1','15','c');hideHelp();">
									<form:option selected="selected" value="" label="--select--" />
								</form:select>
									<div id="errSelectVVP" class="errormsg" style="visibility: hidden; display: none;"></div>																			
 									<div id="ddLgdLBVillageSourceAtVillageCA_error" class="errormsg"><spring:message code="error.blank.VILLAGEP" htmlEscape="true"></spring:message></div>
									<div id="ddLgdLBVillageSourceAtVillageCAMsg" style="display:none"><spring:message code="error.blank.VILLAGEP" htmlEscape="true"/></div>
									<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error1"><form:errors path="lgd_LBVillageSourceAtVillageCA" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddLgdLBVillageSourceAtVillageCA_error2" style="display: none" ></div>
		                       </div>
	                    	</div>
	                    	
	                    	
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
		  	<form:input path="captchaAnswer" id="captchaAnswer" autocomplete="off"  style="width:250px" maxlength="5"/>
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" cssStyle="color:red"/>
		                     
								<c:if test="${captchaSuccess1 == false}">
									<div class="errormsg"><spring:message code="captcha.errorMessage" htmlEscape="true" /></div>
								  
								  <div id="errEmptyCaptcha" class="errormsg" ></div>
				<div id="errorCaptcha" class="errormsg"></div>	
				
								  </c:if>
		                       </div>
	                    	</div>
	                    	
	                    	
	                    	
	                    	
	                    	
					  </div>
	                    	
	                    	
	                    	<div class="block"> <!-- This DIV contains hidden elements -->
								<input type="hidden" name="stateCode" value="<c:out value='${stateCode}'/>"/>
								<input type="hidden" name="districtCode" value="<c:out value='${districtCode}'/>"/>
								<input type="hidden" value="<c:out value='${lbType}'/>"/>
							</div>
							<div class="clear"></div>
	                    	
					  </div>
					  
						<div class="box-footer">
		                     <div class="col-sm-offset-2 col-sm-10">
		                       <div class="pull-right">
		                      		
		                           <%--  <input type="submit" name="get" id="btnGet" class="btn btn-success" value="<spring:message code='Button.GETDATA' htmlEscape='true'></spring:message>" onclick="return validatePRIWardAll();" />												
									<input type="button" class="btn btn-danger" name="Close" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"	id="btnCancel"  onclick="javascript:go('welcome.do');" /> --%>
									<button type="submit" class="btn btn-success " id="actionFetchDetails"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true" code="Button.GETDATA"/></button>
		                            <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
		                        </div>
		                     </div>   
		                  </div><!-- /.box-footer -->
		              </div>    
		                 
		                  <c:if test="${! empty SEARCH_SUBDISTRICT_RESULTS_KEY}">	
		                  	<div class="box-body" id="divData">
							  <h6 style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><c:out value="${message}" escapeXml="true"></c:out></h6>
				                  <div class="table-responsive ">
				                   <table class="table table-striped table-bordered dataTable" id="example" >
										<thead>
											<tr>
												
												<th><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
												<th><spring:message htmlEscape="true" code="Label.WARDNUMBER"></spring:message></th>
												<th><spring:message htmlEscape="true" code="Label.WARDNAMEINENG"></spring:message></th>
												
											</tr>
										</thead>
										
										<tbody>
										 	<c:forEach var="listWardDetail" varStatus="listWardRow" items="${SEARCH_SUBDISTRICT_RESULTS_KEY}">
															<tr>
																<td width="5%"><c:out value="${listWardRow.index+1}" escapeXml="true"></c:out></td>
																<td><c:out value="${listWardDetail.wardNumber}" escapeXml="true"></c:out></td>
																<td align="left"><c:out value="${listWardDetail.wardNameEnglish}" escapeXml="true"></c:out></td>
																
														    </tr>
														</c:forEach>
													</tbody>
											</table>
				            </div>
				       <div style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message><%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
							</br><%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%><label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message><%=df.format(new java.util.Date())%></label>
							</br><label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</div>
					<div id="footer"></div>
				       
				       
				            
				      <div class="box-footer">
				        <div class="col-sm-offset-2 col-sm-10">
                           <div class="pull-right">
                           		<input type="button" name="Back" class="btn btn-primary" value="<spring:message htmlEscape="true" code="Button.BACK"></spring:message>" onclick="javascript:go('viewWard.do');" />
                           		<button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button>
                           		
								<%-- <input type="button" class="btn btn-danger" name="Close" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel"  onclick="javascript:go('welcome.do');" /> --%>
				             <%--   <button type="button" class="btn btn-primary" onclick="setBack();"><spring:message code="Button.BACK"/></button>
				               <button type="button" class="btn btn-danger " onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> <spring:message code="Button.CLOSE"/></button> --%>
				            </div>
				         </div>
				     </div>
				     </div>
		           </c:if>
		       </form:form>
			</div>
		</section>
		</div>
	</div>
</section>		
			
<script src="/LGD/JavaScriptServlet">
$(document).ready(function(){
	$('#btnGet').
	
})</script>
</body>
</html>






