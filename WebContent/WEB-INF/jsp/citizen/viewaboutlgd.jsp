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
   <div class="row" id="frmcontent">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title"><spring:message code="Label.contact.us" htmlEscape="true" text="Contact Us"/></h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
									                 
		                    <form:form class="form-horizontal" name="form1" id="form1" method="POST">
			                	<label><h2>About Local Government Directory</h2></label>
									<p>
										 Primary objective of Local Government directory is to facilitate State Departments to update the directory with newly formed panchayats/local bodies,re-organization in panchayats, 
							            conversion from Rural to Urban area etc and provide the same info in public domain. <br/><br/>
							            Key Features of Local Government Directory:<br/>
									</p>
									
									<p>
									
										 Generation of unique code for each local government body - each local government body is assigned with a unique code.<br />
						            	 Maintenance of local government bodies and its mapping with constituting land region entities. For ex. gram panchayat mapping with villages.<br />
						            	 Mandatory upload of Govt. order for each modification in the directory - to ascertain the users that the data published in LGD is authentic.<br />
						            	 Maintenance of historical data - when modifications take place in LGD, the old values/data is archived. <br />
						            	 Provision to maintain state specific local government setup.<br />
						            	 Compliance with Census 2011 codes.<br />
						            	 Facility to integrate with state specific standard codes - if any state is following standard codes for state level software applications, the same code can be linked to LGD code.<br />
            
									</p>
                    				
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

