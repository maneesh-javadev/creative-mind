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
						<h3 class="box-title"><spring:message code="Label.sitemap" htmlEscape="true" text="Sitemap"/></h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
									                 
		                    <form:form class="form-horizontal" name="form1" id="form1" method="POST">
			                	<label><h2>About PES</h2></label>
									<p>
										The Ministry of Panchayati Raj (MoPR) has undertaken e-Panchayat Mission Mode Project(e-Panchayat MMP) 
										with a view to introduce and strengthen e-Governance in Panchayati Raj Institutions (PRIs) 
										across the country and build associated capacities of the PRIs for effective adoption of 
										the e-Governance initiative. Under this project, Panchayat Enterprise Suite (PES) has been 
										conceptualised which comprises 11 Core Common applications. At present, Panchayat Enterprise 
										Suite has been deployed/operational with 10 Core Common Applications and GIS layer module is under 
										conceptualisation. The operational modules includes LGD(Local Government Directory), 
										Area Profiler(Socio-economic & general details), PlanPlus(to strengthen Decentralised & 
										Participatory Planning), PriaSoft(Panchayat Accounting), ActionSoft(Works/scehme implementation 
										Monitoring System), NAD(National Asset Directory), Service Plus(To facilitate Service Delivery), 
										Social Audit, Training and National Panchayat Portal(Dynamic Website of Panchayats.
							          </br/><br/>
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

