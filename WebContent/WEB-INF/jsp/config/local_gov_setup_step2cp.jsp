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
	<section class="content">
              <div class="row">
                   <section class="col-lg-12">
                       <div class="box">
              <form:form action="local_gov_setup_step4.htm?modify=${value}" id="lgsform" name="lgsform" method="post" commandName="lGSetupForm" >
	                   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="local_gov_setup_step4.htm"/>"/>
                    <div class="box-header with-border">
                        <h3 class="box-title"><spring:message htmlEscape="true"  code="Label.LGTS"></spring:message></h3>
                    </div>
                     
                    <table class="table table-bordered table-hover">
                        
					<tbody>
					
					
                        <tr>
						<td><spring:message htmlEscape="true"  code="Label.LGTYPE"></spring:message> :</td> 	        
						<td><form:select id="lgt1" path="lBTList">
										            	<form:option value=""><spring:message htmlEscape="true"  code="Label.SELECTDISTRICT"></spring:message></form:option>
														<form:options items="${localBodyTypeList}" itemLabel="localBodyTypeName" itemValue="temp" />
													</form:select>
                         </td>
							<td><span id="buttons">
														<button type="button" name="btn3"  onclick="changeTab3(dynamicdiv3)" ><spring:message code="Button.ADDLOWERLGTYPE"></spring:message></button>
														<button type="button" class="btn btn-danger" name="Reset" onclick="resets()"><spring:message code="Button.RESET"></spring:message></button>
													</span>
							</td>
							<td><div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
								<div id="dynamicdiv3" class="frmpnlbg" style="visibility: hidden; display: none;"/>	 </td>
													

					</tr>
				
                  </tbody>
                 <div style="height: 15px; padding-top: 3px;" class="errormsg"></div>
				  <input type="hidden" name="hasTiers" id="hasTiers" value="<c:out value='${hasTiers}' escapeXml='true'></c:out>"/>
               </table>
              
              
              <div class="box-footer">  
                <div class="col-sm-offset-2 col-sm-10">
	                 <div class="pull-right">                   
			           <button type="button"  class="btn btn-info" onclick="callSaveDraft()" ><spring:message code="Button.ADDANOTHERTIER"></spring:message></button>
			           <button type="button" class="btn btn-success" value=" Save " onclick='doSubmit(true)' >Save</button>
                      </div>
                  </div>
              </div>
              </form:form>
         </div>
     </section>
<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
</body>
</html>
