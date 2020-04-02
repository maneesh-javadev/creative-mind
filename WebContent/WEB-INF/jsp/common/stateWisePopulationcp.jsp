<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/error.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function setStateCode(code){
	$("#stateCode").val(code);
}

function validateState(){
	var stateVal=$("#entity").val();
	if( stateVal==""){
		alert("Please Select State")
		return false;
	}
	return true;
}

</script>

</head>
	<body onload="setStateCode('${entityName}')">
	   <section class="content">
                <div class="row">
                    <section class="col-lg-12">
                  <form:form  method="POST" action="stateWisePopulation.do" commandName="viewDeptforadmin" class="form-horizontal">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="stateWisePopulation.do"/>"/>
                    <div class="box">
                    <div class="box-header with-border">
                      <h4>Statewise Localbody Population Details</h4>
                    </div>
                        <div class="box-body">
                          <div class="form-group">
                            	<label  class="col-sm-3 control-label" for="entity">State<font color="red">*</font></label>									
								  <div class="col-sm-6">	
										<select name="entityName" id="entity" onchange="setStateCode(this.value);" class="form-control">
											<option value=""><spring:message code="Label.SEL" htmlEscape="true"></spring:message></option>
											<c:forEach var="statelist" items="${statelist}" varStatus="index">
												<option value="${statelist.statePK.stateCode}" <c:if test="${statelist.statePK.stateCode eq entityName}">selected</c:if>><c:out value="${statelist.stateNameEnglish}" escapeXml="true"></c:out></option>
											</c:forEach>
										</select>
									   <div id="errorentity"></div>
                                    </div>	
                          </div>
                        
                        
                        </div>
                        <div class="box-footer"> 
                            <input type="hidden" id="stateCode" name="code" value="">
						   <button type="submit"  class="btn btn-success pull-right" value="Get Data" onclick="return validateState();"><i class="fa fa-floppy-o"></i> Get Data</button>
                        </div>
                    </div>
                    
                    </form:form>
             <c:if test="${dataExists}">
               <div class="box">
                <div class="box-body">
                     <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a"); %>
                        	<%-- <iframe width="100%" height="500" src="${serverLoc}?__report=${report_design}&stateName=${stateName}&stateCode=${entityName}&rpt_curr_dt_tm=<%=df.format(new java.util.Date())%>"></iframe> --%>
							<birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset" height="700" width="1500"  baseURL="${serverLoc}">
                        	<birt:param name="stateName" value="${stateName}"></birt:param>
                        	<birt:param name="stateCode" value="${entityName}"></birt:param>
                        	<birt:param name="rpt_curr_dt_tm" value="<%=df.format(new java.util.Date())%>"></birt:param>
                        		</birt:viewer>            
                </div>
		            <div class="box-footer">                    
			              <button type="button"  class="btn btn-danger pull-right" id="close" name="close" value="Close" onclick="javascript:location.href='welcome.do'"><i class="fa fa-times-circle"></i> Close</button>
			          </div>
		               
              </div>
              </c:if>
                    </section>
                </div>
            </section>
		
	</body>
</html>