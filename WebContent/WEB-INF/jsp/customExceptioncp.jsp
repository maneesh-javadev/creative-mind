<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-size: 15px;
}
#leftpnl{
display:none;
}
</style>
</head>
<body>
 <section class="content">

                <!-- Main row -->
                <div class="row">
                    <!-- main col -->
                    <section class="col-lg-12">

                        <div class="box">
       <div id="success" >
       <div class="box-body">
		<table >
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="font: bold; font-size: 40px;">
					<h3>
						Ooops!!! <img src='images/error.jpg' height="40"
							style="vertical-align: middle;"></img>
					</h3>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				
				<td><h4>Error Code:<%=request.getAttribute("errorLogId") %></h4></td>
			
			</tr>
			<tr>
				<td><div align="left" class="error"><h2><c:out value="${message}" escapeXml="false"></c:out></h2></div></td>
			</tr>
			<tr>
				<td>
					<h4>Something went wrong while displaying this webpage. To continue, press Home button above or contact your administrator!!</h4></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</div>
	</div>
 </div>


</section>
       </div>
       </section>
              
	
</body>
</html>
