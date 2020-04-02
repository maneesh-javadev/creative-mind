<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%@include file="../common/taglib_includes.jsp"%>


<link href="css/error.css" rel="stylesheet" type="text/css" />
<script>
	
	function saveHabitationConf() {
		//alert("in");
		document.form1.method = "post";
		document.form1.action = "habitationConfiguration.htm?<csrf:token uri='habitationConfiguration.htm'/>";
		document.form1.submit();
	}
	
	
</script>
<script>

	
	    
	 $(document).ready(function()  {
		 var IsDisabled = isParseJson('${IsDisabled}');
				 if(IsDisabled){
			    $('#id1').attr('disabled', 'disabled');
			    $('#id2').attr('disabled', 'disabled');
			   $('#id3').attr('disabled', 'disabled');
		
		 }

	       }); 
		
	
</script>

</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<b><h1>Configure Habitations</h1></b>
			
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
		
		
		
			<form:form autocomplete="${autoCompleteFlag}" name="form1" id="form1"
				commandName="habitationconfiguration">
				<form:hidden path="id"/>
		</div>
	</div>


	
	<div class="frmpnlbg">
		<div class="frmtxt" style="${style}">
			<div id="mainBody" class="block">
				<div class="frmhdtitle">
					<h2>Habitations in the state exists under*</h2>
				</div>
				<ul class="blocklist">
					<c:set var="formTitle" value="Button.SAVE" ></c:set>
					<c:if test="${!Isinsert}">
						<c:set var="formTitle" value="Label.EDIT" ></c:set>

					</c:if>
					<form:radiobutton path="parentType" value="G" name="disableme" id="id1" />
					Gram Panchayat&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<form:radiobutton path="parentType" value="V" name="disableme" id="id2" />
					Village
					<br> <br><br><br> <input type="button" id="id3" onclick="saveHabitationConf()"  value="<spring:message htmlEscape='true'  code='${formTitle}'/>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					

								<a href="home.htm?<csrf:token uri='home.htm'/>"><input type="button" value="close"></a>
							
							</form:form>
								<c:if test="${msg !=null }"><script>alert("${msg}")</script></c:if>
								<br/><br/>
								<c:if test="${IsDisabled}">
											<div style="height: 40px; border: 1px solid red; background-color: #FFEBE8; padding-top: 10px;" align="center">
												<label id="errorCommon"><c:out value="*habitation data for '${statNameEng}' state already entered.You can't change parent type for habitation now"  escapeXml="true"></c:out></<label>
											</div>
							</c:if>
							
			</div>

		</div>
</body>
</html>