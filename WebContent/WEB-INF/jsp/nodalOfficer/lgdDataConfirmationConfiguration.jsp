<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="../common/taglib_includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>

 
   
   var url="saveStateConfiguration.htm";
   var isState=false;
   var errormsg1=null;
   var errormsg2=null;
   $(document).ready(function () {
  	isState= document.getElementById('r1').checked;
  	 $("#btnConfirm").click(function() {
  		 callActionUrl(url);
  	   });
  	 
  });

    
   
   function validateForm() {
   var errormsg1=document.getElementById('r1').checked;
   var errormsg2=document.getElementById('r2').checked;
   var saveflag='${saveflag}';
  
 if(errormsg1==false && errormsg2==false)
	  {
	   $('#err_category').html("Please select any level");
		return false;
	   }
   
 else if(saveflag=="N")
  	 {
     callActionUrl(url); 
  	 }
     
   else if(saveflag=="S")
	 {
	  if(errormsg1)
		 {
		  $('#configModule').modal('show');
		 }
	  else
		  {
		  $('#confirmModule').modal('show'); 
		  }
	 }
   	
   else if(saveflag=="B")
	   {
	   if(errormsg2)
		 {
		   $('#configModule').modal('show');
		 }
	  else
		  {
		  $('#confirmModule').modal('show'); 
		  }
	   
	   }
   }
  
   
   
   
    callActionUrl=function(url){
  	    
  	    $( 'form[id=stateConfigurationFrom]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
  		$( 'form[id=stateConfigurationFrom]' ).attr('method','post');
  		$( 'form[id=stateConfigurationFrom]' ).submit();
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
					<c:out value="Configuration of LGD Data Confirmation ${entityName}"></c:out>
				</h3>
			</div>

			<div class="box-body">

				<form:form id="stateConfigurationFrom" name="freezeUnfreezeStateConfigEntity"
					action="saveStateConfiguration.htm" method="POST"
					commandName="freezeUnfreezeStateConfigEntity"
					class="form-horizontal">   

					<input type="hidden" name="<csrf:token-name/>"
						value="<csrf:token-value uri="saveStateConfiguration.htm"/>" />
						<form:hidden path="id"/>
						<form:hidden path="createdBy"/>
						<form:hidden path="createdOn"/>	
						
						
					 <div class="form-group" style="margin-left: 10%">
						<div class="col-sm-12">
						<div class="col-sm-4" >
						<label for="label.configuration" class="control-label"  class="custom-control-input">
						<spring:message code="Label.Config" text="Configuration  of LGD data will be done at" /></label>
						</div>
						<div div class="col-sm-5" >
							<label class="radio-inline" for="customRadioInline1"> 
							<form:radiobutton path="finalizeAtStateLevel" value="true" id="r1"   />
							
							<!-- <input type="radio"
									id="state" name="state" value="state" onfocus="" onblur="" /> -->
								 Only State level
							</label> <label class="radio-inline" for="customRadioInline2" > 
							<form:radiobutton path="finalizeAtStateLevel" value="false" id="r2" /> District and State level
							</label>
							
							</div>
							<div div class="col-sm-3" >
							<div id="err_category" class="mandatory"></div>
							</div>
							</div>
		   </div>
           <div class="pull-right">
          <c:choose>
          
		 <c:when test="${not empty freezeUnfreezeStateConfigEntity.id}">
	       <button type="button" class="btn btn-info" data-dismiss="modal"
				id="btnUpdate" onclick="validateForm();" >Update</button>
			</c:when>
           <c:otherwise>
            <button type="button" class="btn btn-success" data-dismiss="modal"
				id="btnSave" onclick="validateForm();">Save</button>
		 </c:otherwise>
		</c:choose> 
		
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
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<b><c:out
									value="Configuration of LGD Data Confirmation ${entityName}" /></b>
						</div>
						<div class="modal-body">
							<p>Your previous change of LGD Data Confirmation will be loss! Do you still want to change your configuration?</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick id="btnConfirm">Confirm</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="modal fade" id="configModule" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<b><c:out
									value="Configuration of LGD Data Confirmation ${entityName}" /></b>
						</div>
						<div class="modal-body">
							<p>You have not done any changes in mandatory field.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

</body>
</html>