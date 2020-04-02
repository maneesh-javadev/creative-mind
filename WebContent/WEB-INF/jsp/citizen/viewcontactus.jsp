<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="in.nic.pes.lgd.utils.ApplicationConstant"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
</head>
<body>
<section class="content">
    	<!-- Main row -->
   <div class="row">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title"><spring:message code="Label.contact.us" htmlEscape="true" text="Contact Us"/></h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
									                 
		                    <form:form class="form-horizontal" name="form1" id="form1" method="POST">
			                	<label><h2>Web Information Manager</h2></label>
									<p>
										Ministry of Panchayati Raj<br/>
										Government of India<br/>
										11th Floor, J.P. Building,<br/>
										Kasturba Gandhi Marg, Connaught Place, <br/>
										New Delhi -  110001 <br/>
									   <!--  Email-ID  -  lgdirectory@gov.in <br/>
										Call      -  011-24360536 <br/>
										<br/>
										 <a target="_blank" href="nodalOfficerList.do">
											Nodal Officer List
										</a>
										<br/> -->
									</p>
									
									 <table>
									<tbody>
									<tr>
									<td>Email-ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-</td>
									<td>&nbsp;&nbsp;lgdirectory@gov.in</td>
									</tr>
									<tr>
									<td>Contact Number&nbsp;-</td><td>&nbsp;&nbsp;011-24360536</td>
									</tr>
									</tbody>
									</table> 
									
									
									 <table>
									<tbody>
									<tr>
									<td>&nbsp;</td>
									</tr>
									<tr>
									<td><a target="_blank" href="nodalOfficerList.do">
											Nodal Officer List
										</a></td>
									</tr>
									</tbody>
									</table> 
									
									
					                 <div class="box-footer">
					                     <div class="col-sm-offset-2 col-sm-10">
					                       <div class="pull-right">
					                      		<button type="button" name="Submit3" class="btn btn-danger" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
										   </div>
										 </div>
								    </div>		    
							     </form:form>
							</div>
  					 </div>
  				</section>
		 	</div>
		</section>
	</body>
</html>

