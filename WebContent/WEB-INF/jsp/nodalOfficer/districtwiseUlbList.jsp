<%@include file="../common/taglib_includes.jsp"%>
<table class="table table-bordered">
										<thead>
											<tr>
												<th><spring:message code="label.stateFreeze.serial.no"	htmlEscape="true"></spring:message></th>
												<th><label for="ulbcode" class=" control-label"
													text="Ulb Code"><spring:message
															code="Label.ULBCODE" /></label></th>
												<th><label for="ulbNameInEn" class="control-label"><spring:message
															code="Label.ULBNAMEE" text="ULB Name (In English)" /></label></th>
												<th><label for="ulbtypecode" class="control-label"><spring:message
															code="Label.ULBTYPECODE" text="ULB Type Code" /></label></th>
															<th><label for="ulbTypeNameInEn" class="control-label"><spring:message
															code="Label.ULBNAMEE" text="ULB Type Name" /></label></th>
															
												<th><label for="ulbcensus2011code"
													class="control-label"><spring:message
															code="Label.CENSUSCODE2011" text="Census 2011 Code" /></label></th>
												<th><label for="ulbnoofwards" class="control-label"><spring:message
															code="Label.ULBNOOFWARDS" text=" No.of Wards" /></label></th>

											</tr>

										</thead>
										<tbody>
											<c:forEach items="${ulbList}" varStatus="count"
												var="listofulb">
												<tr>
												<td><c:out value="${count.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${listofulb.entityCode}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.entityName}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.ulbTypeCode}"
															escapeXml="true" /></td>

													<td><c:out value="${listofulb.ulbTypeName}"
															escapeXml="true" /></td>


													<td><c:out value="${listofulb.census2011Code}"
															escapeXml="true" /></td>
													<td><c:out value="${listofulb.noOfWards}"
															escapeXml="true" /></td>
													

												</tr>


											</c:forEach>
										</tbody>
									</table>
									