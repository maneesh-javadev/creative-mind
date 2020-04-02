<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ include file="lBFreezeJs.jsp"%>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateFreezeService.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDesignationService.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
</head>
<body>
	<section class="content">
		<div class="row">
			<section class="col-lg-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message code="Label.LocalBodyFreeze/Unfreeze" /></h3>
					</div>
					<form:form  method="POST" action="localBodyFreezePost.htm" id="localBodyFreezePost" class="form-horizontal">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="localBodyFreezePost.htm"/>"/>
						<input type="hidden" id="category" name="category" />
						<input type="hidden" name="formCategory" value="T"/>
						<input type="hidden" id="lbTypeCode" name="lbTypeCode" value="0"/>
						<input type="hidden" id="parentLocalbodyCode" name="parentLocalbodyCode" value="0" />
						<div class="box-body">
			                <div class="form-group">
		                    	<label class="col-sm-3 control-label"><spring:message text="Select Local Body Type" code="Label.LBType" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<select name="entityName" id="entity" onchange="showHideBlock(this.value);" class="form-control">
										<option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
										<c:forEach var="getLocalGovtSetupList" items="${getLocalGovtSetupList}">
											<option value="${getLocalGovtSetupList.nomenclatureEnglish}"><c:out value="${getLocalGovtSetupList.localBodyTypeName}" escapeXml="true"></c:out></option>
										</c:forEach>
									</select>
									<div id="errorentity"></div>
		                      	</div>
		                    </div>
		                    <div class="form-group" id="DLevel" style="display:none">
		                    	<label class="col-sm-3 control-label"><spring:message code="Label.SELECT" htmlEscape="true"/><span id="firstlevel"></span><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<select name="distLevel" id="distLevel" onchange="getIntegermediatePanchayatListbylblc(this.value)" class="form-control"></select>
									<div id="errordistrict"></div>
		                      	</div>
		                    </div>
		                    <div class="form-group" id="ILevel" style="display:none">
		                    	<label class="col-sm-3 control-label"><spring:message code="Label.SELECT" htmlEscape="true"/><span id="secondlevel"></span><span class="mandatory">*</span></label>
		                      	<div class="col-sm-6">
		                        	<select name="interLevel" id="interLevel" onchange="setVillagePanchayatCode(this.value)" class="form-control"></select>
								    <div id="errorinter"></div>
		                      	</div>
		                    </div>
		                    <div class="box-footer">
		                    	<div class="col-sm-offset-2 col-sm-10">
		                    		<div class="pull-right">
		                    			<input type="submit" id="createhabitation" name="submmit" class="btn btn-primary" value="Get Data" onclick="return validateSubmitForm();"></input>
										<input type="button" id="close" name="close" value="Close" class="btn btn-danger" onclick="callActionUrl('home.htm')" ></input>
		                        	</div>
		                    	 </div>   
                  			</div>
		                </div>
		            </form:form>
		        </div>
		    </section>
		</div>
	</section>
</body>
</html>