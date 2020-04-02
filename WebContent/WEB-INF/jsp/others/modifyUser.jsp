<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="taglib_includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
/*------------------POPUPS------------------------*/

#fade {
	display: none;
	background: #000; 
	position: fixed; 
	left: 0; top: 0; 
	z-index: 10;
	width: 100%; height: 100%;
	opacity: .80;
	z-index: 1;
}

.popup_block{
	display: none;
	background: #fff;
	padding: 20px; 	
	border: 20px solid #ddd;
	float: left;
	font-size: 1.2em;
	position: absolute;
	top: 50%; left: 50%;
	z-index: 5;
	-webkit-box-shadow: 0px 0px 20px #000;
	-moz-box-shadow: 0px 0px 20px #000;
	box-shadow: 0px 0px 20px #000;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}

.popup p {
	padding: 5px 10px;
	margin: 5px 0;
}
/*--Making IE6 Understand Fixed Positioning--*/
*html #fade {
	position: fixed;
}
*html .popup_block {
	position: absolute;
}
</style>



<script type="text/javascript">
// ###############################  popUp  ############################### 
function popUp() {
	
		var popID = "popup"; 
		var popWidth = 900; 
		$('#'+popID).fadeIn().css({ 'width': Number( popWidth )});
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;
		$('#' + popID).css({ 'margin-top' : -popMargTop,'margin-left' : -popMargLeft
		});
		
		$('body').append('<div id="fade"></div>'); 
		$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); 
		
		return false;
	}
function closePopUp()
	{
	  	$('#fade , .popup_block').fadeOut(function() {
			$('#fade').remove(); }); // fade them both out
	  	return false;
	}
	
window.onload=function onLoad()
{
	
	var ststus =true; //document.getElementById("status").value;	
	//alert(ststus);
	if(ststus == "false")
		{
		popUp();
		}
}
</script>



</head>
<body>



<!-- req reject Pop Up window  -->
<form:form action="updateuser.htm" method="POST"  name="updateuser" onsubmit="" >
<div class="overlay" id="overlay1" style="display:none;"></div>
    <div class="box" id="box1">
            <a class="boxclose" id="boxclose1"></a>
			<div >
			<c:if test="${!empty param.message}">
				<table>
								<tr><td rowspan="2"><center><Div class= "success"></div></center></td>
								
								<td ><div  class="helpMsgHeader" style="width:275px;"><h4>Success Message</h4></div></td></tr>
								<tr><td><div id="successMsg" class= "successfont" ><center><c:out value="${param.message}" escapeXml="true"></c:out></center></div>
					          </td></tr></table>
				
			</c:if>
			</div>
			</div>



<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='updateuser.htm'/>"/>

<input type="hidden" name = "status" id="status" value="<c:out value='${USER_ACTIVATION_STATUS}'  escapeXml='true'></c:out>"/>

<div id="popup" class="popup_block">
			<div id="popupError" style="display: ; text-align: center;" class="errormsg"></div>
						<div class="frmpnlbg" >
                             <div class="frmtxt">
                            <div class="frmhdtitle"></div>
                             <table width="100%" class="tbl_with_brdr"> 
                               <tr valign="top" class="tblRowA">
									<td><label>Name :</label></td>
										 <td><input type="text" name="name"  id="remarks" class="frmfield" /> </td>
							   </tr>	
							
							  <tr valign="top" class="tblRowA">
										<td><label>Email :</label></td>
										 <td><input type="text" name="email"  id="remarks" class="frmfield" /> </td>
							</tr>	
							
					   </table>
					   </div>
				   </div>
						 
						<div class="btnpnl" align="center">
				                      <input type="submit" onclick="" value=<spring:message code="Button.Save"/> />
                					 <input type="button" onclick="closePopUp()" value=<spring:message code="Button.Close"/> />
						</div>
					</div>
		
<!-- end Pop Up window  -->

</form:form>
</body>
</html>