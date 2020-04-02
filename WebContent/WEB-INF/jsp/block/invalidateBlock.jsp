<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="js/invalidateBlock.js"
	charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>	
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type="text/javascript" language="javascript">
function loadPage()
{
	toggledisplay('blockdelete_yes','fromothersubdistrict');
	}

if ( window.addEventListener ) { 
    window.addEventListener( "load",loadPage, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", loadPage );
 } else 
       if ( window.onLoad ) {
          window.onload = loadPage;
 }



$(document).ready(function() {
   var s = document.getElementById("flag2").value;  
   if(s>0)
   getBlockList(s);
});
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
</script>
</head>
<body >
	<div id="frmcontent">


		<div class="frmhd">
			<h3 class="subtitle">
				<label><spring:message htmlEscape="true"  code="Label.INVALIDATEBLOCK"></spring:message></label>
			</h3>
			<ul id="showhelp" class="listing">	
			
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /></a></li>
				<%-- these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>											
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</div>				
				
		<div class="clear"></div>

		<div class="frmpnlbrdr">

			<form:form action="invalidateblockPost.htm" id="invalidateForm" method="POST" commandName="invalidateBlock" >
			<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>" />
			<input type="hidden" name="flag2" id="flag2" value="<c:out value='${flag2}' escapeXml='true'></c:out>" />
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="invalidateblockPost.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.INVALIDATEBLOCK"></spring:message>
							</div>
						<div>																	
							<ul class="blocklist">
								<li>									
										
										<strong><spring:message htmlEscape="true"  code="Label.SOURCEDISTRICT"></spring:message></strong>&nbsp;&nbsp;<span id="required" class="errormsg">*</span><br />
										<label> <form:select path="districtCode"  id="ddDistrict" cssClass="combofield" htmlEscape="true"
												             onchange="getBlockList(this.value);">
												<form:option value="0"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
												<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
														<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																		
																</form:option>
															</c:if>
														</c:forEach>
											    </form:select> </label> <br />
										<div class="errormsg"><form:errors htmlEscape="true"  path="districtCode" ></form:errors></div>
										<span class="errormsg" id="ddSrcDistrict_error">
									    </span>
									    
									   						    
										
								</li>
								<li>
										<strong><spring:message htmlEscape="true"  code="Label.SOURCEBLOCK"></spring:message></strong>&nbsp;&nbsp;<span id="required" class="errormsg">*</span><br />
										<label> <form:select path="blockList" id="ddSrcBlock" cssClass="combofield" onchange="getBlockVillages(this.value);getselectedblockUlbData(this.value)" htmlEscape="true">
											    </form:select> </label> <br />
										<div class="errormsg"><form:errors htmlEscape="true"  path="blockList" ></form:errors></div>
										<span class="errormsg" id="ddSrcBlock_error">
									    </span>										
								</li>
							</ul>
						   </div>
						</div>
					</div>

					<div class="frmpnlbg">
						<div class="frmtxt">
						   <div>	
							<spring:message htmlEscape="true" code="Label.HOWBLOCKDELETED"></spring:message>
								<ul class="listing">
									<li>
										<label> <form:radiobutton id="blockdelete_yes" path="rdoBlockDelete" onclick="toggledisplay('blockdelete_yes','fromothersubdistrict')" value="false" htmlEscape="true" /> </label>
								
										<spring:message htmlEscape="true" code="Label.YES"></spring:message>
									</li>

									<li>
										<label> <form:radiobutton id="blockdelete_no" path="rdoBlockDelete" onclick="toggledisplay('blockdelete_no','cvillage')" value="true" htmlEscape="true" />
										</label>
									
										<spring:message htmlEscape="true" code="Label.NO"></spring:message>
									</li>
								</ul>
							</div>
							<div class="errormsg"></div>
							
						</div>
					</div>

					<div id='fromothersubdistrict' class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message htmlEscape="true" code="Label.SELECTTARGETBLOCK"></spring:message>
							</div>
						 <div>	
							<ul class="blocklist">								
								<li>
									<strong><spring:message htmlEscape="true"
												code="Label.TARGETDISTRICT"></spring:message></strong>&nbsp;&nbsp;<span
										id="required" class="errormsg">*</span><br /> <label>
											<form:select path="targetDistrictCodeYes" 
												id="ddTargetDistrictYes" cssClass="combofield"
												onchange="getTargetBlockListYes(this.value);"
												htmlEscape="true">
												<form:option value="0">
													<spring:message htmlEscape="true"
														code="Label.SELECTDISTRICT"></spring:message>
												</form:option>
												<%-- <form:options items="${districtList}"
													itemLabel="districtNameEnglish"
													itemValue="districtPK.districtCode" /> --%>
													<c:forEach items="${districtList}" var="districtList">
															<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																<form:option value="${districtList.districtCode}" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																</form:option>
															</c:if>
															<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																		
																</form:option>
															</c:if>
														</c:forEach>
											</form:select>
									</label> <br />
										<div class="errormsg">
											<form:errors htmlEscape="true" path="targetDistrictCodeYes"></form:errors>
										</div> <span class="errormsg" id="ddDestDistrictYes_error"> </span>
									
								</li>
								<li>
									<strong><spring:message htmlEscape="true"
												code="Label.SELECTTARGETBLOCK"></spring:message></strong>&nbsp;&nbsp;<span
										id="required" class="errormsg">*</span><br /> <label>
											<form:select path="blockYes"
												id="ddTargetBlockListYes" cssClass="combofield"
												htmlEscape="true">
											</form:select>
									</label> <br />
										<div class="errormsg">
											<form:errors htmlEscape="true" path="blockYes"></form:errors>
										</div> <span class="errormsg" id="ddDestBlockYes_error"> </span>
								</li>
							</ul>
						</div>
							<div class="clear"></div>
						</div>
					</div>
					
 					<div id='cvillage' class="frmpnlbg" >
						<div class="frmtxt" >
							<div class="frmhdtitle">
								<spring:message htmlEscape="true"  code="Label.TARGTBLOCK"></spring:message>
							</div>
							
									 <div>	
										<ul class="blocklist">
											<li>
												<strong><spring:message htmlEscape="true"  code="Label.TARGETDISTRICT"></spring:message></strong>&nbsp;&nbsp;<span id="required" class="errormsg">*</span><br />
												<label> <form:select path="targetDistrictCodeNo"  id="ddTargetDistrictNo" cssClass="combofield" htmlEscape="true"
														             onchange="getTargetBlockListNo(this.value);">
														<form:option value="0" htmlEscape="true"><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
														<%-- <form:options items="${districtList}" itemLabel="districtNameEnglish" itemValue="districtPK.districtCode" /> --%>
															<c:forEach items="${districtList}" var="districtList">
																<c:if test="${districtList.operation_state =='A'.charAt(0)}">
																	<form:option value="${districtList.districtCode}" htmlEscape="true" ><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>
																	</form:option>
																</c:if>
																<c:if test="${districtList.operation_state =='F'.charAt(0)}">
																	<form:option value="${districtList.districtCode}" disabled="true" htmlEscape="true"><c:out value="${districtList.districtNameEnglish}" escapeXml="true"></c:out>																			
																	</form:option>
																</c:if>
															</c:forEach>
													    </form:select> </label> <br />
												<div class="errormsg"><form:errors htmlEscape="true"  path="targetDistrictCodeNo" ></form:errors></div>
												<span class="errormsg" id="ddDestDistrictNo_error">
											    </span>
												
										</li>
										<li>
											
											<strong><spring:message htmlEscape="true"  code="Label.SELECTTARGETBLOCK"></spring:message></strong>&nbsp;&nbsp;<span id="required" class="errormsg">*</span><br />
											<label> <form:select path="blockNo"  id="ddTargetBlockListNo" cssClass="combofield" htmlEscape="true">
												    </form:select> </label> <br />
											<div class="errormsg"><form:errors htmlEscape="true"  path="blockNo" ></form:errors></div>
											<span class="errormsg" id="ddDestBlockNo_error">
										    </span>
											
										</li>
										
										<li id="villageCode">
											<div class="ms_container">
												<div class="ms_selectable">
													<strong><spring:message htmlEscape="true"  code="Label.VOB"></spring:message> </strong><br />							
													<form:select name="select9" size="1" id="ddVillage" path="villageList" multiple="multiple" class="frmtxtarea"  htmlEscape="true"></form:select>
												</div>
												<div class="ms_buttons">

												<label> <input type="button" id="btnaddVillageFull"  name="Submit4" class="bttn" value="<spring:message htmlEscape="true" code="Button.SELECTVILLAGES"></spring:message>" onclick="addItem('contributedVillages','ddVillage','',false);" /> </label>
												<label> <input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="removeBockListItem('contributedVillages','ddVillage',false)" /> </label>
												<label> <input type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" onclick="removeBockListItem('contributedVillages','ddVillage',false)" /> </label>
											</div>
											<div class="ms_selection">
												<strong><spring:message htmlEscape="true"  code="Label.VOBSEL"></spring:message></strong><br />
												<form:select name="select4" id="contributedVillages" htmlEscape="true" size="1" multiple="multiple" path="contributedVillages" class="frmtxtarea"></form:select>
												<div class="errormsg">
													<form:errors path="contributedVillages" htmlEscape="true"></form:errors>
												</div>
																						
											</div>
										</div>																				
										</li>

										<li>
										
											<div class="ms_container">
												<%-- <div class="ms_selectable">
													<strong><spring:message htmlEscape="true"  code="Label.ULBOB" text="ULB of Block"></spring:message> </strong><br />							
													<form:select name="select9" size="1" id="avaBlockUlb" path="UlbLists" multiple="multiple" class="frmtxtarea" htmlEscape="true"></form:select>
												</div>
												<div class="ms_buttons">														
														<label> <input type="button" id="btnaddVillageFull" name="Submit4" class="bttn" value="<spring:message htmlEscape="true"  code="Label.SELBLOCK"></spring:message>" onclick="addItem('contributedBlockUlb','avaBlockUlb','',false);" /> </label>
														 <label><input type="button" id="btnremoveOneVillage" name="Submit4" value=" &lt; " class="bttn" onclick="removeBockListItem('contributedBlockUlb','avaBlockUlb',false)" /> </label>
														 <label> <input type="button" id="btnremoveAllVillages" name="Submit4" value="&lt;&lt;" class="bttn" onclick="removeBockListItem('contributedBlockUlb','avaBlockUlb',false)" /> </label>																													 
											</div>
											<div class="ms_selection">
												<strong><spring:message htmlEscape="true"  code="Label.ULBOBSEL" text="Selected ULB of Block"></spring:message></strong><br />
												<form:select name="select4" id="contributedBlockUlb" size="1" htmlEscape="true" multiple="multiple" path="contributedblockUlb" class="frmtxtarea"></form:select>
												<div class="errormsg">
													<form:errors path="contributedVillages"></form:errors>
												</div>
											
												<div class="errormsg"></div> --%>
												<label> <input type="button" id="chooseMoreBtn" onclick='validateSelectAddMore();'  class="btn" value="<spring:message htmlEscape="true"  code="Button.CHOOSEMOREBLOCK"></spring:message>" /> </label>
											</div>
										</div>																				
						
										</li>
																		
							<li>	

							</li>
						</ul>
					
					<div class="errormsg"></div>
					<div class="clear"></div>
						<table id="dynamicTbl" width="664" class="tbl_with_brdr" style="visibility: hidden; display: none;">
							<tr class="tblRowTitle tblclear">
								<th>Block Name</th>
								<th>Merging Entities</th>
							</tr>
						</table>
					</div>							
					</div>
					</div>

					<div class="btnpnl">
						<label> <input type="hidden" name="listformat"
							id="listformat" /> <input type="hidden" name="ulbListFormat"
							id="ulbListFormat" /> <input type="button"
							onclick="javascript:validateSelectAndSubmit();" name="Submit"
							class="btn"
							value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" />
						</label>
						
						<label> <input type="button" name="Submit6" class="btn"
							value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
							onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
						</label>

						
					</div>
				</div>
			</form:form>			
		
		</div>
		</div>
</body>
</html>