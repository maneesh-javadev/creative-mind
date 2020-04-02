<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	.redborder { border: 1px solid red; }
</style>
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/js/DefineGovSetupRural.js"></script>
<script type="text/javascript">var cPath="<%=contextpthval%>";</script>
</head>

<body >

<section class="content">
   <div class="row">
       <section class="col-lg-12">
           <div class="box">
              <form:form  name="lGRuralSetupHierarchyForm" method="post" commandName="lGRuralSetupHierarchyForm" class="form-horizontal">
			  <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="localGovSetupRural.htm"/>"/>
	          <input type="hidden" id="lbRuralHirarchyValues" name="lbRuralHirarchyValues"/>
	          <input type="hidden" id="ruralHirarchyLevels" name="ruralHirarchyLevels"/>
	          <c:set var="listNotEmpty" value="false"/>
	          <c:if test="${!empty definedLocalBodyTypesRural}">
	        	<c:set var="listNotEmpty" value="true"/>
	         </c:if>
	            <div id="tab1">
	              <div id="frmcontent">
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.LGSUP"></spring:message></h3>
                    </div>
                     
                    <table class="table table-bordered table-hover">
                    <div class="box-header subheading">
                        <h3 class="box-title"><spring:message htmlEscape="true" code="Label.selectedlbtmodification" ></spring:message></h3>
                    </div>
                        <tr>
                        	<th><spring:message htmlEscape="true" code="Label.SNO" ></spring:message></th>
							<th  ><spring:message htmlEscape="true"  code="Label.LOCALBODYTYPE"></spring:message></th>
							<th  ><spring:message htmlEscape="true" code="Label.NIE"></spring:message>&nbsp;<font style="color:red;">*</font></th>
							<th  ><spring:message htmlEscape="true" code="Label.NILE"></spring:message>&nbsp;<font style="color:red;">*</font></th>
							<th  >
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr><th colspan="2" align="center"></th></tr>
									<tr>
										<th><spring:message htmlEscape="true" code="Label.STNIE" ></spring:message></th>
										<th><spring:message htmlEscape="true" code="Label.STNIL" ></spring:message></th>
									</tr>
								</table>
							</th>		
						</tr>
					<tbody>
					<c:choose>
					  <c:when test="${listNotEmpty}">
						<c:forEach var="localBodyTypesRural" varStatus="row" items="${definedLocalBodyTypesRural}">
                          <tr> 
							<td align="center"><c:out value="${row.count}" escapeXml="true"/></td>
							<td><c:out value="${localBodyTypesRural[1]}" escapeXml="true"/></td>
							<td><c:out value="${localBodyTypesRural[2]}" escapeXml="true"/></td>
							<td><c:out value="${localBodyTypesRural[3]}" escapeXml="true"/></td>
							<td>
								<table  width="100%" cellpadding="0" cellspacing="0">
									<c:set var="subtype" value="${localBodyTypesRural[5]}"/>
									<c:set var="subtypebr" value="${fn:split(subtype, ',')}" />
									<c:forEach var="subtypeEngLocal" items="${subtypebr}">
										<c:set var="subtypeValues" value="${fn:split(subtypeEngLocal, '-')}"/>
										<tr>
											<td width="50%"><c:out value="${subtypeValues[0]}" escapeXml="true"/></td>
											<td ><c:out value="${subtypeValues[1]}" escapeXml="true"/></td>
										</tr>
									</c:forEach>	
								</table>
							</td>
							<td >
								<input type="hidden" id="level_${localBodyTypesRural[4]}" name="level" value="<c:out value='${localBodyTypesRural[4]}' escapeXml='true'></c:out>"/> 
							</td>
						</tr>
				</c:forEach>
				</c:when>
					<c:otherwise>
						<tr class="tblRowB" style="height: 30px">
							<td colspan="5"><spring:message htmlEscape="true" code="error.norecoredfound"></spring:message></td> 
						</tr>
					</c:otherwise>
				</c:choose>
             </table>
           		
           	 <c:if test="${listNotEmpty}">
           	 	<div class="box-header subheading">
                   <h3 class="box-title"><spring:message htmlEscape="true" code="Label.predefinedhierarchysetup" ></spring:message></h3>
                </div>
                <div id="errorHierarchy" class="errormsg"></div>
              		
              	<table class="table table-bordered table-hover">
                    <tr>	
						<th style="text-align: center;"><spring:message htmlEscape="true" code="Label.modifypredefinedhierarchyset" ></spring:message><font style="color:red;">*</font></th>
						<th style="text-align: center;"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY" ></spring:message><font style="color:red;">*</font></th>
						<th></th>
						<th style="text-align: center;"><spring:message htmlEscape="true" code="Label.hierarchy" ></spring:message></th>		
				    </tr>
					<tbody>
					   <tr>
						  <td>
							<c:if test="${!empty  hierarchySet and !empty predefHierarchy}">
								<c:forEach var="hirarchyLevel" items="${hierarchySet}" varStatus="hirarchyCount">
		                          <table id="tblHierarchy1">
		                           <tr> 
									<td><br ></br>
										<input type="hidden" id="hCount_${hirarchyLevel}" size="1" value="Hirarchy - '<c:out value="${hirarchyCount.count}" escapeXml="true"></c:out>' "/>
										<input type="radio" name="setupCode" id="setupCode_${hirarchyLevel}" value="<c:out value='${hirarchyLevel}' escapeXml='true'></c:out>"
										<c:if test="${hirarchyCount.count eq 1}">checked="checked"</c:if>/>
									</td>
									<c:forEach var="predefvalues" items="${predefHierarchy}" varStatus="count">
										<c:if test="${hirarchyLevel eq predefvalues[5]}">
											<td align="left"><br></br>
												<strong><c:out value=" ${predefvalues[8] }  | " escapeXml="true"></c:out></strong>
											</td>
										</c:if>
									</c:forEach>
									<td><br></br>
										<strong><c:out value=" Hirarchy - ${hirarchyCount.count } " escapeXml="true"></c:out></strong>
									</td>
								</tr>
							  </table>
						   </c:forEach>
						  </c:if>
						</td>
					<td><select id="lbTypeD" name="parent" style="width: 150px;">
							<option value="-1">Select D Level</option>
								<c:forEach var="lbTypesRuralD" items="${definedLocalBodyTypesRural}">
									<c:if test="${fn:contains(lbTypesRuralD[4], 'D')  }">
										<option value="${lbTypesRuralD[0]}#${lbTypesRuralD[4]}"><c:out value="${lbTypesRuralD[1]}" escapeXml="true"></c:out></option>
									</c:if>
								</c:forEach>
						</select><br/><br/>
						
						<select id="lbTypeI" name="parent" style="width: 150px;">
							<option value="-1">Select I Level</option>
								<c:forEach var="lbTypesRuralI" items="${definedLocalBodyTypesRural}">
									<c:if test="${fn:contains(lbTypesRuralI[4], 'I')  }">
										<option value="${lbTypesRuralI[0]}#${lbTypesRuralI[4]}"><c:out value="${lbTypesRuralI[1]}" escapeXml="true"></c:out></option>
									</c:if>
								</c:forEach>
						</select><br/><br/>
						
						<select id="lbTypeV" name="parent" style="width: 150px;">
							<option value="-1">Select V Level</option>
								<c:forEach var="lbTypesRuralV" items="${definedLocalBodyTypesRural}">
									<c:if test="${fn:contains(lbTypesRuralV[4], 'V')  }">
										<option value="${lbTypesRuralV[0]}#${lbTypesRuralV[4]}"><c:out value="${lbTypesRuralV[1]}" escapeXml="true"></c:out></option>
									</c:if>
								</c:forEach>
						</select>
					</td>
					<td><br></br><input type="button" id="build" value="build >>" style="width: 100px;" onclick="return buildHierarchy('tblHierarchy','modify');"/> <br/><br/></td>
					<td><table width="100%" id="tblHierarchy"  class="tbl_with_brdr"></table></td>
				  </tr>
             </table>
           </c:if>
         </div>
              		
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right" id="input_styl">                   
			            <c:if test="${listNotEmpty}">
							<input type="button" id="save" class="btn btn-success" value="Save Hierarchy" onclick="return submitHirarchyDetails();"/>
						</c:if>
							<input type="button" id="cancel" class="btn btn-warning" value="Cancel" onclick="javascript:window.location='localGovSetupRural.htm?<csrf:token uri='localGovSetupRural.htm'/>'"/></div>
                  </div>
              </div>
          </div>
        </form:form>
      </div>
    </section>
  </div>
</section>	

<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
</body>
</html>
