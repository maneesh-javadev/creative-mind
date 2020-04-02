
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<%@ include file="../common/taglib_includes.jsp"%>
<%@ include file="../common/dwr.jsp"%>
<%@ include file="BlockSyncJavascript.jsp"%>
<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message></title>


<style type="text/css" lang="css">
.bluish { float:left; }
.bluish div { width:15px; float:left; margin-right:10px; background:#CEF6F5; height:15px; }
.blushBG {background:#CEF6F5;}
</style>

</head>
<body>
	<section class="content">
		<div class="row">
	             <section class="col-lg-12">
	              	<div class="box ">
	           			<div class="box-header with-border">
	
	               			<h3 class="box-title">Synchronize Block Panchayat with Block</h3>
	           			</div>
		                <div class="box-body">
							<div class="box-header subheading">
		                              <h3 class="box-title">Districts</h3>
		                    </div>
		                   <form:form action="" class="form-horizontal" method="post" id="blockPanchayatSyncBlock" commandName="blockPanchayatSyncBlock">
		                   
			                   	<div class="form-group" >
	                		   		<label  class="col-sm-3 control-label"><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"/>
											<span class="mandatory">*</span> </label>
			                    	<div class="col-sm-6">
										<select name="paramDistrictCode"  id="ddDistrict" class="form-control"
										    <c:if test="${districtId gt 0}">disabled="disabled"</c:if>>
											<option value=""><spring:message code="Label.SELECTDISTRICT"></spring:message></option>
											<c:forEach items="${districtList}" var="vDistricts">
												<option value="${vDistricts.districtCode}" <c:if test="${districtId eq vDistricts.districtCode}">selected=true</c:if>>
													<c:out value="${vDistricts.districtNameEnglish}"></c:out>
												</option>				
											</c:forEach>
										</select>		 
	                    			</div>
		                        </div>
	                   			<div id="divShowData" style="display: none;">
		                   			<div class="box-header subheading">
			                              <div class="bluish">
				          				  	<div><!-- Used div for color block --></div>
					          				<strong>Synchronized Block with Block Panchayat</strong>
					        			</div>
			                    	</div>
			                    	<div class="row">
						    			<div class="col-sm-12 ">
											<table class="table table-striped table-bordered" cellspacing="0" id="tblBlockPanchayats">
												<thead>
													<tr>
														<th></th>
														<th>Name of Block Panchayat</th>
														<th>Name of Block</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="box-footer">
                     					<div class="col-sm-offset-2 col-sm-10">
				                        	<div class="pull-right">
												<button type="button" id="btnSynchronizeBP" class="btn btn-primary"><i class="fa fa-floppy-o"></i> Synchronize Block Panchayat with Block</button>	
					                       		<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
					                        </div>
					                     </div>   
	                  				</div>
										
								</div>
							</form:form>
						</div>
					</div>
		                    
				</div>
			</div>
		</section>
	</div>
</section>
</body>
</html>

