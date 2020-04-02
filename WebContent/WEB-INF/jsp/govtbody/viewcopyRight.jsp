<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../common/taglib_includes.jsp"%>
<title>LGD - Copyright Policy</title>
</head>
<body>

	
<section class="content">
    	<!-- Main row -->
   <div class="row"  id="frmcontent">
        <!-- main col -->
            <section class="col-lg-12 ">
	           <div class="box ">
            	  <div class="box-header with-border">
						<h3 class="box-title">Copyright Policy</h3>
            	  </div>
		              <!-- /.box-header -->
		                <div class="box-body">
									                 
		                    <form:form class="form-horizontal" method="POST">
			                	<p>
									Material featured on this site may be reproduced free of charge in any format or media without requiring specific permission.
									 This is subject to the material being reproduced accurately and not being used in a derogatory manner or in a misleading context. 
									 Where the material is being published or issued to others, the source must be prominently acknowledged. However, the permission to 
									 reproduce this material doesn't extend to any material on this site, which is explicitly identified as being the copyright of a
									 third party. Authorisation to reproduce such material must be obtained from the copyright holders concerned.
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