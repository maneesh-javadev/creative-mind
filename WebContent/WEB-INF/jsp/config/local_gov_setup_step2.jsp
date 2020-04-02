<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/local_govt_setup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/lgd_css.css" rel="stylesheet" type="text/css" />
<script src="js/lgd_js.js"></script>
<script src="js/jquery.js"></script>
<script src="js/govtorder.js"></script>
<!-- <script src="js/jquery-ui-1.8.16.custom.min.js"></script> -->
<script src="js/local_govt_setup.js"></script>

<script type="text/javascript" language="javascript">

function callSaveDraft()
{
	if (doSubmit(false))
	{
		document.getElementById('lgsform').action="local_gov_setup_step3.htm?modify=${value}";
		document.forms['lgsform'].submit();
	}
}

function doSubmit(doSave)
{
	if (document.getElementById('hasTiers').value!='' && doSave)
		document.forms['lgsform'].submit();
	else
		{
			if (dropdown_pos<2)
			{
				alert('Please choose a Hierarchy');
				return false;
			}
			else
			{
				var hasErrors = false;
				if (document.getElementById('lgt1').selectedIndex<1)
					{
						alert("Please choose Local Government Type in Drop Down Number : 1");
						hasErrors = true;
					}
				
				for (var i=1; i<dropdown_pos;i++)
					{
						if (document.getElementById('select' + i)!=null)
							if (document.getElementById('select' + i).selectedIndex<1)
								{
									alert("Please choose Local Government Type in Drop Down Number : " + (i+1));
									hasErrors = true;
								}
					}
	
				if (hasErrors)
					return false;
				else
					if (doSave)
						document.forms['lgsform'].submit();
					else
						return true;
				
			}
		}
}
</script>
</head>
<body>
	<form:form action="local_gov_setup_step4.htm?modify=${value}" id="lgsform" name="lgsform" method="post" commandName="lGSetupForm" >
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="local_gov_setup_step4.htm"/>"/>
<table width="100%" border="1">

  <tr>
    <td colspan="3">
    <div class="frmhd">
        <table width="100%" class="tbl_no_brdr">
            <tr>
                <td><spring:message htmlEscape="true"  code="Label.LGTS"></spring:message></td>
                <%-- //these links are not working <td align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
            </tr>
        </table>
    </div>
    </td>
  </tr>
  <tr>
    <td colspan="3">		
    <div id="tab2">
			<table class="tbl_no_brdr" width="100%">
				<tr>
					<td valign="top" style="padding: 0px">
						<div class="clear"></div>
						<div id="frmcontent">

							 <div class="frmpnlbg">
								<div class="frmtxt">
											<table width="100%" class="tbl_no_brdr">
												<tr>
													<td><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message> : 	        
													<form:select id="lgt1" path="lBTList">
										            	<form:option value=""><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
														<form:options items="${localBodyTypeList}" itemLabel="localBodyTypeName" itemValue="temp" />
													</form:select>
<%-- 													<c:forEach var="lstNomen" varStatus="lstNomenRow" items="${lstNomen}">
													<form:hidden path="nomenEnglish" value="${lstNomen.nomenclatureEnglish}"/>
													<form:hidden path="nomenLocal" value="${lstNomen.nomenclatureLocal}"/>
													</c:forEach> --%>
													<span id="buttons">
														<input type="button" name="btn3" value="<spring:message code="Button.ADDLOWERLGTYPE"></spring:message>" onclick="changeTab3(dynamicdiv3)" />
														<input type="button" name="Reset" value="<spring:message code="Button.RESET"></spring:message>" onclick="resets()"/>
													</span>
														<div style="height: 15px; padding-top: 3px;"
															class="errormsg"></div>
													<div id="dynamicdiv3" class="frmpnlbg" style="visibility: hidden; display: none;"/>	 
													</td>

												</tr>


											</table>
											<div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
											<input type="hidden" name="hasTiers" id="hasTiers" value="<c:out value='${hasTiers}' escapeXml='true'></c:out>"/>
										</div>

							</div>
						</div></td>
				</tr>
			</table>

		</div></td>
  </tr>
  <tr>
    <td width="2%" align="left">&nbsp;</td>
    <td width="13%" align="left"><input type="button" value="<spring:message code="Button.ADDANOTHERTIER"></spring:message>" onclick="callSaveDraft()" /></td>
    <td width="85%" align="left"><input type="button" value="  Save " onclick='doSubmit(true)' />      <br/></td>
  </tr>
</table>
		
<!-- 	<input type="button" onclick="submit4()" name="Submit" id="Submit" value="Submit" align="right" style="visibility:hidden"/> -->
	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>

</body>
</html>
