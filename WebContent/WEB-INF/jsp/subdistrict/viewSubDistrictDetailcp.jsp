<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">


 	</script>


</head>
<body>
<section class="content">
       <div class="row">
            <section class="col-lg-12">
                    <div class="box">
                
                <form:form action="modifySubDistrictAction.htm" method="POST" commandName="modifySubDistrictCmd" id="frmModifySubDistrict">
										<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="modifySubDistrictAction.htm"/>"/>
										<input type="hidden" name="warningEntiesFlag" id="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}'/>"/>
				  						<input type="hidden" name="type" id="type" value="<c:out value='${type}'/>"/>
							     		<font size="3"><esapi:encodeForHTML>${successMsg}</esapi:encodeForHTML></font>
							     		<div class="box-body">
							     		<div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.SUBDISTRICTDETAIL"></spring:message></h4></div>
							     		<c:forEach var="listSubdistrictDetails" varStatus="listSubdistrictDetailsRow" items="${modifySubDistrictCmd.listSubdistrictDetails}">
							              <table class="table table-bordered table-hover">
							    
												    <tbody>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></td>
												        <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictCode">&nbsp;
																					<c:out value="${status.value}" escapeXml="true" />
																				</spring:bind></td>
												      </tr>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message></td>
												        <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictVersion">&nbsp;													
																				 <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind></td>
												      </tr>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTMINORVERSION"></spring:message></td>
												        <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].minorVersion">&nbsp;													
																				 <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind></td>
												      </tr>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
												        <td> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameEnglish">&nbsp;																				
																				  <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind> </td>
												      </tr>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
												        <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].subdistrictNameLocal">&nbsp;
											                                     <c:out value="${status.value}"  escapeXml="true"/>
																			</spring:bind></td>
												      </tr>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTALIASENGLISH"></spring:message></td>
												        <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasEnglish">&nbsp;
																				  <c:out value="${status.value}" escapeXml="true"/>
																			</spring:bind></td>
												      </tr>
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.SUBDISTRICTALIASLOCAL"></spring:message></td>
												        <td> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].aliasLocal">&nbsp;
																				       <c:out value="${status.value}" escapeXml="true" />
																			</spring:bind></td>
												      </tr>
												      
												       <tr>
												        <td><spring:message htmlEscape="true"  code="Label.HEADQUARTERS"></spring:message></td>
												        <td>  <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].headquarterName">&nbsp;							
								                                                             <c:out value="${status.value}" escapeXml="true"/>							
					    	                                                      </spring:bind></td>
												      </tr>
												      
												       <tr>
												        <td><spring:message htmlEscape="true"  code="Label.STATESPECIFICCODE"></spring:message></td>
												        <td> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].sscode">&nbsp;
																				    <c:out value="${status.value}" escapeXml="true"/>
																			</spring:bind> </td>
												      </tr>
												      
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.ORDERNO"></spring:message></td>
												        <td> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderNocr">&nbsp;
																					<c:out value="${status.value}" escapeXml="true"/>
																			   </spring:bind> </td>
												      </tr>
												      
												      <tr>
												        <td><spring:message htmlEscape="true"  code="Label.ORDERDATE"></spring:message></td>
												        <td> <spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].orderDatecr">&nbsp;
																														<c:out value="${status.value}" escapeXml="true"/>
																						   </spring:bind> </td>
												      </tr>
												      
												      
												      <tr>
												      <td><spring:message htmlEscape="true"  code="Label.EFFECTIVEDATE"></spring:message></td>
												      <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].ordereffectiveDatecr">&nbsp;
																														<c:out value="${status.value}" escapeXml="true"/>
																						   </spring:bind></td>
												      
												      
												      </tr>
												      <tr>
												       <td><spring:message htmlEscape="true"  code="Label.GAZPUBDATE"></spring:message></td>
												       <td><spring:bind path="modifySubDistrictCmd.listSubdistrictDetails[${listSubdistrictDetailsRow.index}].gazPubDatecr">&nbsp;
																														<c:out value="${status.value}" escapeXml="true"/>
																						   </spring:bind> </td>
													   <c:set var="string1" value="${modifySubDistrictCmd.listSubdistrictDetails[listSubdistrictDetailsRow.index].cordinate}"/> 
												      
												      </tr>
												    </tbody>
												  </table>

				                 </c:forEach>
				                 <c:if test="${! empty subDistrictHistory}">	
				                  <div class="box-header subheading"><h4><spring:message htmlEscape="true"  code="Label.SUBDISTRICTHISTORY"></spring:message></h4></div>
				                  <table  class="table table-bordered table-hover">
										     <tr>
													<td width="6%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message></td>
													<td width="16%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></td>
													<td width="20%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message></td>
													<td width="20%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTMINORVERSION"></spring:message></td>
													<td width="20%" rowspan="2"><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
													
												</tr>
													<tr>
												
												</tr>			
											<c:forEach var="subDistrictHistoryDetail" varStatus="listSubDistrictRow" items="${subDistrictHistory}">
												<tr>
													<td width="6%"><c:out value="${listSubDistrictRow.index+1}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictCode}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictVersion}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${subDistrictHistoryDetail.minorVersion}" escapeXml="true"></c:out></td>
													<td align="left"><c:out value="${subDistrictHistoryDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
													
												</tr>
											</c:forEach>
										
									</table>
								  </c:if>
				                 
				                 
				                    <div>
                        				
								</div>
				                   
				                  </div>
				                <%--   <div class="box-footer">
	                <div class="col-sm-offset-2 col-sm-10">
		             <div class="pull-right">
					    <button type="button" name="Submit2" class="btn btn-warning"  onclick="javascript:location.href='viewdistrict.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i><spring:message htmlEscape="true" code="Button.BACK"></spring:message></button>
			            <button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i>  <spring:message htmlEscape="true" code="Button.CLOSE"></spring:message></button>
					</div>
					</div>
					</div>  --%>
				                  
				                  
				   </form:form>
              
       </div>
    </section>
</body>
</html>