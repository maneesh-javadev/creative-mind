<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>
<title>LGD - Terms & Conditions</title>

</head>
<body>

<section class="content">
    	<!-- Main row -->
   <div class="row" id="frmcontent">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title">Terms and Conditions</h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
									                 
		                    <form:form class="form-horizontal" method="POST">
			                	<p>
									This website is designed, developed and maintained by National Informatics Centre, Government of India.
									<br/><br/>
									Though all efforts have been made to ensure the accuracy and currency of the content on this website, the same should not be
									constructed as a statement of law or used for any legal purposes. In case of any ambiguity or doubts, users are advised to
									 verify/check with the State Departments and/or other source(s), and to obtain appropriate professional advice.
									<br/><br/>
									Under no circumstances will this Department/Ministry be liable for any expense, loss or damage including, without limitation, 
									indirect or consequential loss or damage, or any expense, loss or damage whatsoever arising from use, or loss of use, of data, 
									arising out of or in connection with the use of this website.
									<br/><br/>
									 These terms and conditions shall be governed by and constructed in accordance with the Indian Laws. Any dispute arising under
									  the terms and conditions shall be subject to the jurisdiction of the courts of India.
									 <br/><br/>TThe information posted on this website could include hypertext links or pointers to information created and 
									 maintained by non-Government/private organisations. Ministry of Panchayati Raj is providing these links and pointers 
									 solely for your information and convenience. When you select a link to an outside website, you are leaving this website 
									 and are subject to the privacy and security policies of the owners/sponsors of the outside website.
									 <br/><br/>
									 Ministry of Panchayati Raj, does not guarantee the availability of such linked pages at all times.
									 <br/><br/>
									 Ministry of Panchayati Raj, can't authorise the use of copyrighted materials contained in linked websites. Users are 
									 advised to request such authorisation from the owner of the linked website.
									 <br/><br/>
									 Ministry of Panchayati Raj, does not guarantee that linked websites comply with Indian Government Websites Guidelines.
									 
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