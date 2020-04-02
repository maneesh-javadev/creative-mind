<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="../common/taglib_includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>

   $(document).ready(function () {
	  
   
	  
	   $('#confirmModule').modal('hide'); 
  	 
  });


   
   function validateForm() {
  
		  $('#confirmModule').modal('show'); 
   }
   
   function btnConfirm(){
	   
	   var coverageType = $("input[name='partFull']:checked").val();
		   
		   		 
	   configBlockVillageMapping.method = "post";
		   configBlockVillageMapping.action = "saveConfigureBlockVillagePartiallyMap.htm?<csrf:token uri='saveConfigureBlockVillagePartiallyMap.htm'/>&coverageType="+coverageType+"";
		  configBlockVillageMapping.submit();

   }
  
   
   
    
   
 </script>

</head>
<body>

	<section class="content">
	<div class="row">
		<section class="col-lg-12 ">
		<div class="box ">
			<div class="box-header with-border">

				<h3 class="box-title">
					<c:out value="Configuration of Block Village(Partially/Fully)"></c:out>
				</h3>
			</div>

			<div class="box-body">

				<form:form id="stateConfigurationFrom" name="configBlockVillageMapping"
					action="saveStateConfiguration.htm" method="POST"
					commandName="configBlockVillageMapping"
					class="form-horizontal">   

					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="saveStateConfiguration.htm"/>" />
						
						
					 <div class="form-group" style="margin-left: 10%">
						<div class="col-sm-12">
						<div class="col-sm-4" >
						<label for="label.configuration" class="control-label"  class="custom-control-input">
						<spring:message code="Label.Config" text="Configuration of Block Village(Partially/Fully)" /></label>
						</div>
						<div div class="col-sm-5" id="coverage" >
							<label class="radio-inline" for="customRadioInline1"> 
							<input type="radio" checked="checked" name="partFull" value="true" id="r1"   />
							
							<!-- <input type="radio"
									id="state" name="state" value="state" onfocus="" onblur="" /> -->
								 Partially
							</label> <label class="radio-inline" for="customRadioInline2" > 
							<input type="radio" name="partFull" value="false" id="r2" /> Fully
							</label>
							
							</div>
							<div div class="col-sm-3" >
							<div id="err_category" class="mandatory"></div>
							</div>
							</div>
		   </div>
           <div class="pull-right">
          
            <button type="button" class="btn btn-success" data-dismiss="modal"
				id="btnSave" onclick="validateForm();">Save</button>
		
		
		<button class="btn btn-danger" id="btnActionClose" type="button" >close</button>
		
		</div>
		</form:form>
	</div>
	</div>
	</section>
	</div>
	</section>


			<div class="modal fade" id="confirmModule" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<%-- <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<b><c:out
									value="Configuration of LGD Data Confirmation ${entityName}" /></b>
						</div> --%>
						<div class="modal-body">
							<p>Your previous change of Configuration of Block Village will be loss! Do you still want to change your configuration?</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal"  onclick="btnConfirm();">Confirm</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

</body>
</html>