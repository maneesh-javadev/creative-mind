
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ include file="../common/taglib_includes.jsp"%>
<%@ include file="../common/dwr.jsp"%>
<%@ include file="BlockSyncJavascript.jsp"%>
<style type="text/css" lang="css">
.bluish { float:left; }
.bluish div { width:15px; float:left; margin-right:10px; background:#CEF6F5; height:15px; }
.blushBG {background:#CEF6F5;}
</style>
<title><spring:message htmlEscape="true"  code="Label.CREATENEWLOCALGOVTBODY"></spring:message></title>
</head>
<body>
	<!-- Main Form Stylings starts -->
	<div class="form_stylings">
			
		<!-- Main Heading starts -->
		<div class="header">
			<h3>Synchronize Block Panchayat with Block</h3>
		</div>
		<!-- Main Heading ends -->
			
		<!-- Page Content starts -->
		<div class="page_content">
			<div class="form_container">
				<form:form action="" method="post" id="blockPanchayatSyncBlock" commandName="blockPanchayatSyncBlock">
		
					<div class="form_block">
						<div class="col_1">
							<h4>Districts</h4>
							<ul class="form_body">
								<li>
									<label>
										<spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"/>
										<span class="mandate">*</span>
									</label>
									<select name="paramDistrictCode"  id="ddDistrict" class="combofield"
												 <c:if test="${districtId gt 0}">disabled="disabled"</c:if>>
										<option value=""><spring:message code="Label.SELECTDISTRICT"></spring:message></option>
										<c:forEach items="${districtList}" var="vDistricts">
											<option value="${vDistricts.districtCode}" <c:if test="${districtId eq vDistricts.districtCode}">selected=true</c:if>>
												<c:out value="${vDistricts.districtNameEnglish}"></c:out>
											</option>				
										</c:forEach>
									</select>
								</li>
							</ul>
							<h4><!-- Used header for blank head, please dont remove.  --></h4>
							<ul class="form_body">
									<li>
										<div id="divCreateBlockUI" style="display: none;">
											<div class="form_block">
												<div class="col_1">
													<h4>New Block Creation</h4>
													<ul class="form_body">
														<li>
															<label >
																New Block Name in English &nbsp;
												    		</label>
												    	</li>
												    	<li>
												    		<input type="text" id="paramBlockNameEnglish" readonly="readonly"/>
												    	</li>
													</ul>
													<br/><br/>
												</div>
											</div>
											<ul class="form_body" >
												<li>
												    <label class="inline">&nbsp;</label>
													<input class="bttn" type="button" id="btnCreateBlock" value="Create New Block" />
													<input class="bttn" type="button" id="btnClose" value="Close" />	
												</li>
											</ul>	
											<br/>
										</div>
										<div id="divShowData" style="display: none;">
										<table class="data_grid" width="100%" id="tblBlockPanchayats">
											<thead>
												<tr>
													<td colspan="3">
														<div class="bluish">
									          				<div><!-- Used div for color block --></div>
									          				<strong>Synchronized Block with Block Panchayat</strong>
									        			</div>
													</td>
												</tr>
												<tr>
													<th width="3%"></th>
													<th width="47%">Name of Block Panchayat</th>
													<th width="50%">Name of Block</th>
													<!-- <th width="5%">Action</th> -->
												</tr>
											</thead>
										</table>
										<div align="center">
											<input class="bttn" type="button" id="btnSynchronizeBP" value="Synchronize Block Panchayat with Block"/>
											<input class="bttn" type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>'"/>
										</div>
										</div>
									</li>
								</ul>
							</div>
					</div>
					<br/>
					
					
					<!-- <div class="form_stylings" id="helltest">
						<div class="form_block">
							<div class="col_1">
								<h4>Synchronize Block Panchayat with Block</h4>
								
							</div>
						</div>
					</div> -->
					
					
		
				</form:form>	
			</div>
		</div>
		<!-- Page Content ends -->
			
	</div>
	<!-- Main Form Stylings ends -->
</body>
</html>

