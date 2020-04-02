<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<script type="text/javascript" src="js/designation_hierarchy.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message htmlEscape="true"  code="Label.Title"></spring:message></title>

<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
	
	<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 


function dynamicRow(x)
{
	dynamicDiv.innerHTML += "<div id=div"
		+ x
		+ ">"
		+ "<td><input type='text' name='desgName'  maxlength='50' id='desgName"
		+ x
		+ "' class='frmfield' style='width:200px'/></td>&nbsp;&nbsp;"
		+ "<td><label id='lblreport"
		+ x
		+ "' style='color:red'/><input type='text' name='desgNameLocal'  maxlength='60' id='desgNameLocal"
		+ x
		+ "' class='frmfield' style='width:200px'/></td>&nbsp;"
		+ "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='isMultiple1' id='chkbx"
		+ x
		+ "'/></td>"
		+ "<td align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		+ "<input type='image' src='images/delete.png' onclick='isLBReportingExisting(desgName" + x + ".value," + x
		+ ");return false;'/></td></div>";
}

function ClearAll()
{
	/* var desNames="${desgName}";
	var desgNameLocal="${desgNameLocal}";
	var deNamearray;
	var desgNameLocalarray;
	//alert("names"+desNames);
	if(desNames.length>0)
		deNamearray=desNames.split(",");
	
	for(var x=2;x<deNamearray.length;x++)
		{
		dynamicRow(x);
		}
	
	for(var x=0;x<deNamearray.length;x++)
		{
		
		document.getElementById("desgName" + x).value = deNamearray[x];
		}
	
	if(desgNameLocal.length>0)
		desgNameLocalarray=desgNameLocal.split(",");
	

		
	
	for(var x=0;x<desgNameLocalarray.length;x++)
	{
	
	document.getElementById("desgNameLocal" + x).value = desgNameLocalarray[x];
	}
	
	i=x;
	 */
	
	
	
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
 
//Office dis
</script>
</head>

<body >
<div id="frmcontent">
		<div class="frmhd" >
		
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message htmlEscape="true"  code="Label.DHO"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a></td>
					<%-- //these links are not working <td width="50" align="right" valign="middle"><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></td>
					<td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP" ></spring:message></a> 
 --%>					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
	<div class="frmpnlbrdr">
	<form:form action="designation_hierarchy_panchayat.htm" method="post" commandName="designationForm" >
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="designation_hierarchy_panchayat.htm"/>"/>
	<input type="hidden" name="bindex" id="bindex"  /> 
	<input type="hidden" name="opeation" id="opeation" />
		<div id="cat">
		<div class="frmpnlbg">
						<div class="frmtxt">
						 <table width="100%" class="tbl_no_brdr">
                               <tr>
                               <td width="10%">&nbsp;</td>
                               <td width="60%">
                                 <font color="red"><form:errors path="*" cssClass="errorBox" ></form:errors></font><!-- </td>
                             <td width="40%"> --><!-- </td><td> --><div id="errormsg" class="errormsg"></div>
                               </td>
                               </tr>
                               </table>
						  <table width="100%" class="tbl_no_brdr">
                            <tr>
                              <td width="14%" rowspan="2">&nbsp;</td>
                              <td width="86%" style="padding:0px">
                              <table width="300" class="tbl_no_brdr">
                                <tr>
                                  <td  colspan="4">
                                  <Label><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message><span id ="required" class="errormsg">*</span></Label>
                                  <label id="lblType" class="mndt"></td>
                                  </tr><tr>
                                  <td colspan="3">
                                  <form:select name="lgTypeCode" id="lgtype" path="lgTypeCode"  onchange="getDesignation(false, this.value)">
											<form:option value=""> - - Select - - </form:option>
											<form:options items="${lgT}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode"/> 
								</form:select></td>
                                </tr>
                              </table>
                              </td>
                              </tr>
                              </table>
                              <div class="clear" style="height: 25px"></div>
                              <div class="frmtxt">
                              <div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.TD"></spring:message>
										</div>
						  <table width="100%" class="tbl_no_brdr">
                            <tr>
                              <td width="13%" rowspan="2">&nbsp;</td>
                              <td width="87%" style="padding:0px">
                              <table width="300" class="tbl_no_brdr">
                                 <tr>
                                  <td align="left" width="200"><Label><spring:message htmlEscape="true" code="Label.NE"></spring:message></Label><span id ="required" class="errormsg">*</span></td>
                                  <td align="left" width="200">&nbsp;</td>
                                  <td width="100"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label><span id ="required" class="errormsg">*</span></td>
                                </tr> 
                                
                                <tr>
                                  <td><label id="lbldesg0" class="mndt"></label> <form:input path="designationCode" name="designationCode" id="designationCode0" type="hidden" />
                                  <form:input path="desgName" name="desgName" id="desgName0" type="text"  onfocus="setPreName(0)" onblur="isLBReportingExistingM('',0)"  maxlength="50" class="frmfield" style="width:200px" />
                                  <!-- <div style="height:15px; padding-top:3px;" class="errormsg"></div> -->
                                  <td><form:errors path="desgName" cssClass="error"/></td>
                                  </td>
                                  <td><label id="lblreport0" class="mndt"></label><form:input path="desgNameLocal" name="desgNameLocal"  id="desgNameLocal0" type="text"  maxlength="100" class="frmfield" style="width:200px" />
<!--                               <div style="height:15px; padding-top:3px;" class="errormsg"></div>
 -->                            <td><form:errors path="desgNameLocal" cssClass="error"/></td>  
 								    </td>
 								<%-- 	<td><form:input path="designationCode" name="designationCode" id="designationCode0" type="hidden" /></td> --%>
                                </tr>
                                <tr>
                               <td></td>
                               <td></td>
                                </tr>
                              </table>
                              </td>
                              </tr>
                              </table>
                              </div>
                              <div class="clear" style="height: 25px"></div>
                              <div class="frmtxt">
                              <div class="frmhdtitle">
											<spring:message htmlEscape="true"  code="Label.DEGOTHER"></spring:message>
										</div>
						  <table width="100%" class="tbl_no_brdr">
                            <tr>
                              <td width="13%" rowspan="2">&nbsp;</td>
                              <td width="87%" style="padding:0px">
                              <table width="300" class="tbl_no_brdr">
                              </table>
                              <div style="height:15px; padding-top:3px;" class="errormsg"></div></td>
                            </tr>
                            <tr>
                              <td style="padding:0px"/>
                              <table width="650" class="tbl_no_brdr">
                              <tr>
                                  <td align="left" width="200"><Label><spring:message htmlEscape="true"  code="Label.NE"></spring:message></Label><span id ="required" class="errormsg">*</span></td>
                                  <td align="left" width="200"><Label><spring:message htmlEscape="true"  code="Label.NL"></spring:message></Label><span id ="required" class="errormsg">*</span></td>
                                  <td width="150" colspan="2"><Label><spring:message htmlEscape="true"  code="Label.ISMULTI"></spring:message></Label><br/></td>
                                </tr>
                                <tr>
                                  <td><form:input path="designationCode" name="designationCode" id="designationCode1" type="hidden" /> 
                                  <form:input path="desgName" name="desgName" id="desgName1" type="text"   onfocus="setPreName(1)" onblur="isLBReportingExistingM('',1)"  maxlength="50" class="frmfield" style="width:200px" />
                                  <form:errors path="desgName" cssClass="error"/>
                                  </td>
                                  <td><form:input path="desgNameLocal" name="desgNameLocal"  id="desgNameLocal1" type="text"  maxlength="100" class="frmfield" style="width:200px" />
                                  <form:errors path="desgNameLocal" cssClass="error"/>
                                  </td>
                                 <td align="center">
                                 <input type="checkbox" name="isMultiple1" id="chkbx1"/><br /><br /></td>
                                  <td width="100" align="center"><input type="button" name="Submit32" onclick="changeIt(dynamicDiv,'dynamic')"
											value="<spring:message htmlEscape="true" code="Button.ACHILD"></spring:message>" /><br /><br />
                                   </td>
                                 <%--   <td><form:input path="designationCode" name="designationCode" id="designationCode1" type="hidden" /></td> --%>
                                   </tr>
                                   
                                   <tr>
                                		
                                		<td colspan="4">
                                		<div id="dynamicDiv" style="width: 100%"></div>
                                		</td>                                
                                </tr>
                                </table>
                                </tr>
                              </table>
						</div><br/><br/>	
						<table width="100%">
						<tr>
                                  <td width="13%">&nbsp;</td>
                                  <td  width="87%" align="left">&nbsp;&nbsp;&nbsp;
                            
                              
		                              <input type="hidden" name="isLocalBody" value="P" />
		                              <input type="hidden" name="isMultiple" id="isMultiple"></input>
		                              <form:input  path="modifiedDesignation" type="hidden"  id="modifiedDesignation" name="modifiedDesignation"/>
		                              <input type="hidden" id="deletedDesignation" name="deletedDesignation"/>
		                              <form:input path="desigtype" type="hidden" id="desigtype" name="desigtype"/>
		                              <input type="submit" name="Submit" value="<spring:message htmlEscape="true"  code="Button.SAVE"></spring:message>" onclick="return checkBoxValueDesignation()" />
							  
	                            
	                             <c:choose>
							 	<c:when test="${fn:containsIgnoreCase(designationForm.desigtype,'P')}"><c:set var="clearURL" value="designation_hierarchy_official_panchayat.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(designationForm.desigtype,'T')}"><c:set var="clearURL" value="designation_hierarchy_official_traditional.htm"></c:set></c:when>
								<c:when test="${fn:containsIgnoreCase(designationForm.desigtype,'U')}"><c:set var="clearURL" value="designation_hierarchy_official_urban.htm"></c:set></c:when>
								 </c:choose>
	                          
	                            
	                             <input type="button" name="Clear" value="Clear" onclick="go('${clearURL}');"/>
	                            <input type="button" name="Submit33" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:window.location.href='home.htm'"/>
	                           
                      </td>
                                </tr>
                               <tr><td>&nbsp;</td><td>
                               
                                <div id="designationDiv" style="visibility:false">
               						 
               						 <table border="1" width="346" cellspacing="0" cellpadding="0" id="mtab">
										<thead>
											
										</thead>
										<tbody id="Designationrows"> </tbody>
										
									</table>
									</div></td></tr>
						</table>
                              </div>
						
           </div>
           
		</div>
	</form:form>
	</div>
	</div>
</body>
</html>
