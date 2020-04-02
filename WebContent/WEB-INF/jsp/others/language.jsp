<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page pageEncoding="utf-8" import="java.util.Properties"%>
<%@ include file="../others/taglib_includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<script type="text/javascript">
function call2(){
	
	
	
	document.form2.submit();
	
	
}
function call1(){
	
	
	
	document.form1.submit();
	
	
}
</script>
</head>
<body>





	 <table width="100%" class="tbl_no_brdr">
       <tr>
         <td width="85" height="25" class="tblclear"><spring:message htmlEscape="true"  code="Label.Choose"/>:</td>
          <td width="156" class="tblclear" >
          
          
          <form id="form1" name="form1" method="get" >
            <label>
            <select name="theme" class="combofield" onchange="call1()" style="width:120px;">
              <option value="default" ><spring:message htmlEscape="true"  code="Label.Select"/></option>
              <option value="default">Default Theme</option>
              <option value="mustard">Mustard Theme</option>
              <option value="peach">Peach Theme</option>
             <option value="green">Green Theme</option>
             <option value="blue">Blue Theme</option>
            </select>
            </label>
            </form>          
   
         </td>
         
          
          
            <td style="padding:0px"><c:if test="${ not empty sessionScope.login_key_id }"><a href="switchunit.htm">Switch Unit</a></c:if></td>
              <td width="20" class="tblclear"><img src="images/help.jpg" width="16" height="14" /></td>
           <%--//this link is not working    <td width="50" class="help tblclear"><a href="#"><spring:message htmlEscape="true"  code="Label.Help"/></a></td> --%>
              <td width="14" class="tblclear" style="width:20px; padding-top:2px"><a href="#" class="texttoggler" rel="smallview" title="small size"><img src="images/btnMinus.jpg" width="16" height="14" border="0" /></a></td>
              <td width="14" class="tblclear" style="width:20px; padding-top:2px"><a href="#" class="texttoggler" rel="normalview" title="normal size"><img src="images/btnDefault.jpg" width="16" height="14" border="0" /></a> </td>
              <td width="28" class="tblclear" style="width:20px; padding-top:2px"><a href="#" class="texttoggler" rel="largeview" title="large size"><img src="images/btnPlus.jpg" width="16" height="14" border="0" /></a></td>
          
          		<script type="text/javascript">
					documenttextsizer.setup("texttoggler");			  
			 	</script>
          
          
          
           <td width="100" align="right" style="padding:0px"><spring:message htmlEscape="true"  code="Label.Language"/> </td>
          <td width="108" align="right" style="padding:0px">
          
          
          <form id="form2" name="form2" method="get" action="#">
            <input type="hidden" name="id" value="<%=request.getParameter("id") %>" />
             <select id="sel" name="lang" class="combofield"  style="width:120px;" onchange="call2()">
               	<option value="" ><spring:message htmlEscape="true"  code="Label.Language"/></option>
              	<option value="en"><spring:message htmlEscape="true"  code="Label.English"/></option>
              	<option value="hi"><spring:message htmlEscape="true"  code="Label.Hindi"/></option>
              
              </select>
              
              
              
           </form>
          </td>
        </tr>
      </table>
</body>
</html>