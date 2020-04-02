
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/State.js"></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type="text/javascript" language="javascript">

$(document).ready(function() {
	$("#form1").validate({
		rules : {
			districtNameInEn : {
				nameFormat : true
			},
			shortName : {
				shortName : true
			},
			census2011Code : {
				onlyNumberSp : true
			},
		},
		messages : {
			districtNameInEn : {
				 nameFormat : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>" 
									
			},
			shortName : {
				shortName : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>" 
							
			},
			census2011Code : {
				onlyNumberSp : "<font color='red'><br><spring:message code='error.valid.COMMONNUMERICREQUIRED'/></font>" 
						
				}	
		}
	});
});

function chkValid(obj) {
	var flag;
	if (obj.value != null) {
		flag=$("#form1").validate().form();
		if(flag)
			return true;
		else
			return false;
	}
}
function partList()
{
	var length=document.getElementById('ddDistrict').options.length;
	if(length==0)
		{
			alert("You can't take all the coverage of part entity");
			return 1;
		}
	else
		return 0;
}

function formsubmit() {
		var flag = 0;
		var tmp = 0;
		
		var selObj = document.getElementById('ddDestDistrict');
		var selObjArray=['ddDestDistrict','ddDestinctDistrict'];
		for(var i=0;i<selObjArray.length;i++)
			{
			var selObj = document.getElementById(selObjArray[i]);
				for (var j = 0; j < selObj.options.length; j++) {
					selObj.options[j].selected = true;
				}
			}
		
		//alert("@@"+document.getElementById('districtNameInEn').value.substr(0, 1).match(/[A-Za-z]/)+"@@") ;
		/* if(document.getElementById('districtNameInEn').value.substr(0, 1).match(/[A-Za-z]/)==null){
			alert("fgh");
			} */
			
		if (document.getElementById('stateOrUt').value == null || document.getElementById('stateOrUt').value == "") 
		{
			document.getElementById("stateOrUt_error").innerHTML = "Kindly select STATE or UT first";
			$("#stateOrUt_error").show();

			document.getElementById('stateOrUt').focus();
			flag = 1;
		} 
		
		else 
			if (document.getElementById('districtNameInEn').value == null || document.getElementById('districtNameInEn').value == "")
			{
			
				document.getElementById("districtNameInEn_error").innerHTML = "Name of new State (In English) required";
				$("#districtNameInEn_error").show();
	
				document.getElementById('districtNameInEn_error').focus();
				flag = 1;
			} 
			
			else if(document.getElementById('districtNameInEn').value.substr(0, 1).match(/[A-Za-z]/)==null){
				document.getElementById("districtNameInEn_error").innerHTML = "In Name of new State (In English) First Letter Must be Alphabet";
				$("#districtNameInEn_error").show();
				document.getElementById('districtNameInEn_error').focus();
				flag = 1;
			}
			else {
					if(document.getElementById('duplicate').value==1)
						{
							flag = 1;
							document.getElementById("districtNameInEn_error").innerHTML = "State is already exists";
							$("#districtNameInEn_error").show();
						}
					tmp = chkValid(document.getElementById('shortName'));
					if (!tmp) 
					{
						flag = 1;
						document.getElementById('shortName').focus();
					} 
					else
					{
						var census2011CodeConversion=$("#census2011Code").val();
						if(census2011CodeConversion==""){
							$("#census2011Code").val("00");
							tmp=true;
						} else if(census2011CodeConversion.length==1){
							$("#census2011Code").val("0"+$("#census2011Code").val());
							tmp=true;
						} else if(census2011CodeConversion.length>2){
							tmp=false;
						} 
						if (!tmp) {
							alert("Census 2011 code can not greater than 2 digits.");
							flag = 1;
							document.getElementById('census2011Code').focus();
						} else {
							tmp = chkValid(document.getElementById('census2011Code'));
							if (!tmp) {
								flag = 1;
								document.getElementById('census2011Code').focus();
							} 
							else
							{
		
								
	
									var selObj1 = document.getElementById('ddDestDistrict');
									var stateCode1 = "";
									for (i = 0; i < selObj1.options.length; i++) {
										selObj1.options[i].selected = true;
										stateCode1 += selObj1.options[i].value + ",";
									}
			
									//alert("selObj1"+selObj1);
									//alert("stateCode1"+stateCode1);
									var selObj = document.getElementById('ddDestinctDistrict');
									var stateCode = "";
									for (i = 0; i < selObj.options.length; i++) {
										selObj.options[i].selected = true;
										stateCode += selObj.options[i].value + ",";
									}
			
									if (document.getElementById('ddDestDistrict').value == null || document.getElementById('ddDestDistrict').value == "") {
										alert("Kindly Select Contributing State List" + document.getElementById('ddDestDistrict').value);
										flag = 1;
									} else if (document.getElementById('ddDestDistrict').options.length == 1
											&& (document.getElementById('ddDestDistrict').value.match('FULL')) == "FULL") {
										flag = 1;
										alert("You Can't Make the New  State by Shifting Only One Full State. Kindly Select One More Contributing State.  !");
									} else if (document.getElementById('ddDestinctDistrict').value == null
											|| document.getElementById('ddDestinctDistrict').value == "")
									{
	
										var list = stateCode1;
										if (list.match('PART')) {
											//alert(list);
											alert("Kindly Select At Least One Contributing District List As Part of State Selected");
											flag = 1;
										}
										
											
									}
									else if (document.getElementById('ddDestinctDistrict').value != null
											|| document.getElementById('ddDestinctDistrict').value != "")
									{
	
										
											flag=partList();
											
									}
							
	
						}
					}
				}
			}

		

		//alert("formsubmit"+document.getElementById('districtNameInEn').value);

		//alert("Enter flag condition");
		if (!flag) {
			//alert("kamlesh");
			document.forms['form1'].submit();
		}

	}

	var doRemove = true;


</script>

</head>

<body onload="onloadState()">
<div id="frmcontent">
	<div class="frmhd">
			
					<h3 class="subtitle"><spring:message code="Label.CREATESTATE" htmlEscape="true"></spring:message>
					</h3>
					<ul class="listing">
					<li>
					<a href="#" class="frmhelp">Help</a>
					</li>
					</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form action="addNewState.htm" method="POST" commandName="state" id="form1" name="form1" enctype="multipart/form-data">  <!-- //addnewstate final annotation -->
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="addNewState.htm"/>"/>
			<div class="frmpnlbg">
				<div class="frmtxt">								  
				<!--  <table width="100%" class="tbl_no_brdr"> -->
                                    
                               
					<div class="frmhdtitle">
					<spring:message code="Label.GENERALDETAILNEWSTATE" htmlEscape="true"></spring:message>
					</div>
						<input type="hidden" name="duplicate"	id="duplicate" />				
					<div id="dialog-duplicate-state" title="Error Message" style="display: none">
					<p>
						<span class="ui-icon ui-icon-alert"
							style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-duplicate-state" htmlEscape="true"></spring:message>
					</p>
				</div>
				
				
					
					
						<div class="search_criteria">
						 <ul class="blocklist">
						 <li>
							
							<label for="stateOrUt"><spring:message
											code="Label.SELECT" htmlEscape="true"></spring:message>
											<spring:message
											code="Label.STATEORUT" htmlEscape="true"></spring:message>
											<span id="required" class="errormsg"></span>
											<span class="errormsg">*</span>
							</label><br />
											
								<form:select htmlEscape="true" path="stateOrUt"
										class="combofield" id="stateOrUt" onchange="hideerrorstateOrUt();">
										<form:option value="N" htmlEscape="true">
														<spring:message code="Label.SEL" htmlEscape="true"></spring:message>
														
											</form:option>
											<form:option value="S" htmlEscape="true">
														<spring:message code="Label.STATE" htmlEscape="true"></spring:message>
														
											</form:option>
											<form:option value="U" htmlEscape="true">
														<spring:message code="Label.UT" htmlEscape="true"></spring:message>
														
											</form:option>						
														
									</form:select> 
									
									<div id="stateOrUt_error" class="errormsg" style="display:none" ></div><br /> 
									<form:errors path="stateOrUt" class="errormsg" htmlEscape="true"></form:errors>  	
						
					
						
							
							<form:hidden path="operation" value="C" htmlEscape="true"/>
							<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true" />
							</li>
							
							<li>
							<label for="districtNameInEn"><spring:message code="Label.STATENAMEINENGLISH">
											</spring:message> <span class="errormsg">*</span></label><br />
						   
							<form:input path="districtNameInEn" id="districtNameInEn" maxLength="50" onkeyup="return chkValid(this);"
							 cssClass="frmfield" onfocus="show_msg('districtNameInEn')" htmlEscape="true"
							 onblur="findDuplicate(this);NameInEn()" /> 
							<form:errors path="districtNameInEn" class="errormsg" htmlEscape="true"></form:errors>  	
							<div class="errormsg" id="districtNameInEn_error"></div>
							 </li>
							
						     <li> 
							
							<label for="shortName"><spring:message
									code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message></label>
								<br /> <form:input
									path="shortName" id="shortName" maxLength="2" htmlEscape="true"
									cssClass="frmfield" onfocus="show_msg('shortName')" onkeyup="return chkValid(this);"
									/>
										<form:errors path="shortName" class="errormsg" htmlEscape="true"></form:errors> 
									 <!-- <span class="msg" id="districtAliasInLcl_msg">Alias of the district (In Local language) here</span>-->
							<!-- <div class="errormsg" id="districtAliasInLcl_error"></div> -->
							  </li>
							

						      <li> 
							<label for="census2011Code"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></label><span
								id="required"></span><br /> <form:input path="census2011Code" onkeyup="return chkValid(this);" htmlEscape="true"
									id="census2011Code" maxLength="6" cssClass="frmfield"
									onfocus="show_msg('census2011Code')" onblur="Cns2011Code()" />
									<form:errors path="census2011Code" class="errormsg" htmlEscape="true"></form:errors> 
								<!-- <div class="errormsg" id="census2011Code_error"></div> -->
							
								</li>
								</ul>
								 
								</div>
						


					
					
					
					<!-- -----table ends -->
					
					
					
				</div>
				
				</div>
				 <div class="clear"></div>
						<%@ include file="../common/headquarter.jspf" %>
					<%--	<%@ include file="../common/gis_nodes.jspf" %>--%>
				
				<div class="clear"></div> 
				<div class="frmpnlbg">
				<div id='district'>
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message>
						</div>

 
  
         <ul class="blocklist">
		  <li>
              <div class="ms_container">
											
				<div class="ms_selectable">
		        <strong>
		          <spring:message code="Label.STATELIST" htmlEscape="true"></spring:message>
		        </strong>
		         <form:select path="stateNameEnglishMain" class="frmsfield" id="ddSourceDistrict" style="height: 120px; width: 233px"
												   multiple="true" items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish" htmlEscape="true">										
				 </form:select>
                 
              </div>
      
        
               <div class="ms_buttons btnmargin" >
              
              <input name="button1" type="button" class="bttn" style="width: 70px" onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);" value="<spring:message
					 code="Button.WHOLE"/>" />
				
              <input name="button2" type="button" class="bttn" style="width: 70px" onclick="removeOneItem('ddDestDistrict', 'ddSourceDistrict', true)" value="&lt;" />
           
              <input name="button3" type="button" class="bttn" style="width: 70px" onclick="removeAllState('ddDestDistrict', 'ddSourceDistrict', true,'ddDistrict','ddDestinctDistrict')" value=" &lt;&lt;" />
            
              <input name="button4" type="button" class="bttn" style="width: 70px" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);" value="Part &gt;&gt;" />
            
            </div>
       
       
          <div class="ms_selection">
          <strong>
          <spring:message code="Label.CONTRISTATELIST" htmlEscape="true"></spring:message>
          </strong>
      	  <form:errors path="STATENAMEENGLISH" class="errormsg" htmlEscape="true"></form:errors>  
          <form:select name="select4"
													id="ddDestDistrict" size="1" multiple="multiple"
													path="STATENAMEENGLISH" class="frmtxtarea"
													style="height: 120px; width: 300px" htmlEscape="true">
													<form:options htmlEscape="true" items="${listState}" itemLabel="stateNameEnglish"
									itemValue="stateCode"/>  
													
													 </form:select>
		</div>											    
													
      <div class="btnleftpad">
      
        <input name="button5" class="bttn"  type="button" style="width: 150px" onclick="selectEverything();"
           
         value="Get District List" />
         </div>
         <div class="clear"></div>
        </div>
        </li>
      
      
       <li>
        <div class="ms_container">
											
		<div class="ms_selectable">
        <strong>
          <spring:message code="Label.DISTRICTLIST" htmlEscape="true"></spring:message>
        </strong>
        <form:select htmlEscape="true" path="STATENAMEENGLISH" class="frmtxtarea" id="ddDistrict" style="height: 120px; width: 233px" multiple="true" itemLabel="districtCode" itemValue="STATENAMEENGLISH"></form:select>
       </div>
        
        
       	<div class="ms_buttons btnmargin" >
              <input name="button6" type="button" class="bttn" style="width: 70px" onclick="addItem('ddDestinctDistrict','ddDistrict','FULL',true);" value="<spring:message
																code="Button.WHOLE" htmlEscape="true"/>" />
				
              <input name="button7" type="button" class="bttn" style="width: 70px" onclick="removeOneItem('ddDestinctDistrict', 'ddDistrict', true)" value="&lt;" />
              
              <input name="button8" type="button" class="bttn" style="width: 70px" onclick="removeAllDist('ddDestinctDistrict', 'ddDistrict', true)" value="&lt;&lt;" />
              
          </div>    
           <!--  <tr>
              
              
            <td align="center"><input name="button9" type="button" style="width: 70px" onclick="addItem('ddDestinctDistrict','ddDistrict', 'PART',true);" value="Part &gt;&gt;" /></td>
              
              
            </tr> -->
       
         
         <div class="ms_selection">
        <strong>
          <spring:message code="Label.CONTRIBUTEDISTRICTLIST" htmlEscape="true"></spring:message>
        </strong>
        <form:select name="select4"
		        id="ddDestinctDistrict" size="1" multiple="multiple" htmlEscape="true"
				path="DistrictNameEnglish" class="frmtxtarea"
				style="height: 120px; width: 300px"> 
				<form:options items="${listDist}" itemLabel="districtNameEnglish" itemValue="districtCode" htmlEscape="true"/>  
				</form:select>
				
				 <form:errors path="DistrictNameEnglish" class="errormsg" htmlEscape="true"></form:errors>  		
		
		</div>
		<div class="clear"></div>
		</div>
		</li>
		</ul>
     <!--  <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><input name="button10" class="btn"  type="button" style="width: 150px" 
        
         onclick="selectsubdieverything();"
        
         value="Get Sub-district List" /></td>
      </tr> -->
      
      
     
      <%-- 
      <tr>
										<td valign="top"><strong><spring:message
													code="App.SUBDISTRICTLIST" htmlEscape="true"></spring:message> </strong></td>
													 <td>&nbsp;</td>
        <td><strong>
          <spring:message code="App.CONTRIBUTESUBDISTRICTLIST" htmlEscape="true"></spring:message>
        </strong></td>
										<td width="180" align="center">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="246" valign="top"><form:select name="select9"
												size="1" id="ddSubdistrictforsubdistrict" htmlEscape="true"
												path="subDistrictList" multiple="multiple" class="frmtxtarea"
												style="height: 120px; width: 242px">
											</form:select>
										</td>
										<td align="center" style="padding-top: 3px"><table
												width="100%" class="tbl_no_brdr">
												<tr>
													<td align="center"><label> <input
															type="button"  class="btn"  id="btnaddSubDistrictFull" name="Submit4" 
															value="<spring:message
																code="Button.WHOLE"/>"
															onclick="addItem('subDistrictListNew','ddSubdistrictforsubdistrict','FULL',true)" />
													</label>
													</td>
													</td>
												</tr>
<tr>
												              <td align="center"><input name="button11" type="button" style="width: 70px" onclick="removeOneItem('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" value="&lt;" /></td>
												
												
													
												</tr>
												<tr>
												              <td align="center"><input name="button12" type="button" style="width: 70px" onclick="removeAll('subDistrictListNew', 'ddSubdistrictforsubdistrict', true)" value="&lt;&lt;" /></td>
												
												
													
												</tr>
												
											</table></td>
										<td width="260" valign="top"><form:select name="select4"
												id="subDistrictListNew" size="1" multiple="multiple" htmlEscape="true"
												path="contributedSubDistricts" class="frmtxtarea"
												style="height: 120px; width: 242px">
                                   <form:options items="${listSD}" itemLabel="subdistrictNameEnglish"
									itemValue="subdistrictCode"/> 
											</form:select></td>
									</tr> --%>
									
									
									
									
									
									
									
						
						<%-- <tr>
							<td colspan="2"><br/><br/><label><spring:message
							code="Label.REORGANIZESUBDISTRICT" htmlEscape="true"></spring:message></label></td>
						</tr>--%>
						
							
								<%-- <td width="20" class="tblclear"><label> <input
										name="radiobutton" type="radio" id="Yes" onclick="reorganizingYes()" /> </label></td>
								<td width="37" class="tblclear"><spring:message
										code="Label.YRES" htmlEscape="true"></spring:message></td>
								<td width="20" class="tblclear"><label> <input
										name="radiobutton" type="radio" id="No" value="true" onclick="reorganizingNo()" checked="true" /> </label></td>
								<td width="27" class="tblclear"><spring:message
										code="Label.NO" htmlEscape="true"></spring:message></td> --%>
							
						
							
							<div id='reorgOption'  class="tbl_no_brdr" style="visibility: hidden; ">
							
							<ul class="blocklist">
							<li>
							<label> <form:radiobutton path="newSubdistrict"
									 id="newDistrict" value="createDistrict" type="radio" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
									
								<spring:message code="Label.CREATEDISTRICT" htmlEscape="true"></spring:message>
								</label>		
										
								</li>
								<li>
								<label><form:radiobutton path="newSubdistrict"
										type="radio" name="modifyDistrict" value="modifyDistrict" id="isModifyDistrict" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
									
								<spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message>
									</label>	
									</li>	
									<li>	
								
								<label> <form:radiobutton path="newSubdistrict"
										 id="newVillage" value="create" type="radio" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
									
								<spring:message code="Label.CREATENEWSUBDIS" htmlEscape="true"></spring:message>
									</label>	
									</li>	
									<li>
										<label> <form:radiobutton path="newSubdistrict"
									name="modifySubDistrict"	type="radio" value="modify" id="isModifySubdistrict" onchange="reorgOption()" htmlEscape="true"></form:radiobutton>
									
								
								<spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message>
								</label>
										</li>
										
								   <li>
								
								<label> <form:radiobutton path="newSubdistrict"
										id="newSubdistrict" value="createvill" type="radio" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
								
								<spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message>
									</label>	
									</li>	
								<li>
								<label><form:radiobutton path="newSubdistrict"  name="modifyVillage"
										type="radio"  value="modifyvill" id="isModifyVillage" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
								
								<spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message>
									</label>	
							    </li>
							    </ul>
							    </div>
							
						
						
				<div>
				<input type="hidden" name="reorganized" id="reorganized"/>
				<input type="hidden" name="modifyVillage" id="modifyVillage"/>
				<input type="hidden" name="modifySubDistrict" id="modifySubDistrict"/>
				<input type="hidden" name="modifyDistrict" id="modifyDistrict"/>
				<input type="hidden" name="addAnotherSD" id="addAnotherSD"/>
				</div>
			
			
				
				</div>
				
				</div>
											
				<div class="clear"></div>
				 <div class="btnpnl">
								
				<input type="button" class="button" id="Reorganize" style="visibility: hidden"
				value="<spring:message code="Button.REORGANIZE"></spring:message>"
				onclick="checkforModifyforstate();"/> 
				
				<%-- <label> <input type="button" id="Submit" style="${visibility}"
				value="<spring:message code="Button.ADDANOTHER" htmlEscape="true"></spring:message>"  
				onclick="addanother(this.value);"/>
			<!-- 	validateForm(document.getElementById('OfficialAddress').value,this.value) -->
				</label>	 --%>	
				 <input type="button" class="btn" id="Submit" name="Submit"
				value="<spring:message code="Button.SAVE"></spring:message>"  
				onclick="formsubmit();"/> <!-- added by kirandeep on 18/06/2014 -->
				
				
				<input type="button" class="btn" name="Submit6"
				value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
				onclick="javascript:go('cancelST.htm');" />  
				
				<input type="button" class="btn" id="Proceed" style="visibility: hidden"
				value="<spring:message code="Button.PROCEED" htmlEscape="true"></spring:message>"
				onclick="selectFinal();"/> 
				
						</div>
						
						</div>
							</form:form>	                       
	
	<!-- </td>
	</tr>
	</table> -->
	</div>
</div>
</body>
</html>
				
				