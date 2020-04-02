
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>

<script type="text/javascript" src="<%=contextpthval%>/dwr/interface/lgdDwrSectionService.js"></script>
<script>
$(document).ready(function() {
	 $("#btnFormActionUpdate").click(function() {
		 validateUpdateForm(); 
	  });
	 
	 /**
		 * The sectionNameEnglish keypress event allow only alphanumbric and some special character{} . 
		 */
		 $("INPUT[name^=sectionNameEnglishChange]").keypress(function(e){
			// get the key that was pressed
			
			$( '#errsectionNameEnglishChange').text(""); 	
			var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
			
			//alert(key);
				if(key == 13 && this.nodeName.toLowerCase() == "input")
				{
					return true;
				}
				else if(key == 13)
				{
					return false;
				}
				var allow = false;
				// allow Ctrl+A
				if((e.ctrlKey && key == 97 /* firefox */) || (e.ctrlKey && key == 65) /* opera */) return true;
				// allow Ctrl+X (cut)
				if((e.ctrlKey && key == 120 /* firefox */) || (e.ctrlKey && key == 88) /* opera */) return true;
				// allow Ctrl+C (copy)
				if((e.ctrlKey && key == 99 /* firefox */) || (e.ctrlKey && key == 67) /* opera */) return true;
				// allow Ctrl+Z (undo)
				if((e.ctrlKey && key == 122 /* firefox */) || (e.ctrlKey && key == 90) /* opera */) return true;
				// allow or deny Ctrl+V (paste), Shift+Ins
				if((e.ctrlKey && key == 118 /* firefox */) || (e.ctrlKey && key == 86) /* opera */
				|| (e.shiftKey && key == 45)) return false;
				
				if(this.value.length==0) //intial letter 
				{
					if(key>64 && key<91 /* A-Z */  || key>96 && key<123  /*  a-z */ ){
						
						return true;
					}else{
						$( '#errsectionNameEnglishChange').text("<spring:message code='error.first.letter.must.be.alphabet' htmlEscape='true'/>"); 
						return false;
					}
				}else{
					if(
							
							key==126 /* ~ */ ||
							key==96 /* ` */ ||
							key==33 /* ! */ ||
							key==64 /* @ */ ||
							key==35 /* # */ ||
							key==36 /* $ */ ||
							key==37 /* % */ ||
							key==94 /* ^ */ ||
							key==38 /* & */ ||
							key==42 /* * */ ||
							key==95 /* - */ ||
							key==43 /* + */ ||
							key==61 /* = */ ||
							key==123 /* { */ ||
							key==125 /* } */ ||
							key==91 /* [ */ ||
							key==93 /* ] */ ||
							key==124 /* | */ ||
							key==92 /* \ */ ||
							key==39 /* ' */ ||
							key==34 /* " */ ||
							key==58 /* : */ ||
							key==59 /* ; */ ||
							key==60 /* < */ ||
							key==62 /* > */ ||
							key==63 /* ? */
							
							
						){
						$( '#errsectionNameEnglishChange').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>"); 
						return false;
						
					}
					
					if(key==32)/* Space Key */
					{
						if(spaceCount>0){
							$( '#errsectionNameEnglishChange').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
							return false;
						}
						spaceCount++;
					}else {
						spaceCount=0;
					}
				}
			
		 });
		 
		 
		 /**
			 * The sectionNameEnglish keyup event allow first character must be alphbet and capital . 
			 */
		  $("INPUT[name^=sectionNameEnglishChange]").keyup(function(e){
			 var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
			 if(this.value.length==1) //intial letter 
			{
			 if(key>64 && key<91 ){
				this.value=this.value.toUpperCase();
				}
			}
		 }); 
		  
});


/* The {@code $_checkEmptyObject} used to check object / element  
* is empty or undefined.
*/
var $_checkEmptyObject = function(obj) {
	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
		return true;
	}
	return false;
};

validateUpdateForm=function(){
	
	  $( "span[id^=error]" ).each(function(){
			$( this ).text("");
	    });
	
	if(validateSelectionNameEng()){
		
		 var SectionNameEng=$("#sectionNameEnglishChange").val();
		 validateSectionNameUnique(SectionNameEng);
	}	
};

 validateSelectionNameEng=function(){
	
	var re = new RegExp("^[a-zA-Z0-9 \.\,\(\)\/-\]+$", "g");
	 var SectionNameChnage=$("#sectionNameEnglishChange").val();
	 
	 var SectionName='${sectionForm.sectionNameEnglish}';
	 //alert(SectionNameEng);
	 //alert(SectionNameEng.charAt(0).charCodeAt());
	 if($_checkEmptyObject(SectionNameChnage)){
		 $( '#errsectionNameEnglishChange').text("<spring:message code='Label.section.name.required' htmlEscape='true'/>");
		 return false;
	 }else{
		 if(!re.test(SectionNameChnage)){
			 $( '#errsectionNameEnglishChange').text("<spring:message code='Error.invalidchar' htmlEscape='true'/>");	 
			 return false;
		 } else{
			 var key=SectionNameChnage.charAt(0).charCodeAt();
			 //alert(key);
			 if(!(key>64 && key<91)){
				 $( '#errsectionNameEnglishChange').text("<spring:message code='error.first.letter.must.be.alphabet' htmlEscape='true'/>");	
				 return false;
			 
			 }else if(SectionName.trim()==SectionNameChnage.trim()){
				 $( '#errsectionNameEnglishChange').text("<spring:message code='error.must.change.section.name.of.previous' htmlEscape='true'/>");	
				 return false;
			 
			 }else{
				 for(i=0;i<SectionNameChnage.length;i++){
					 key=SectionNameChnage.charAt(i).charCodeAt();
					 if(key==32)
						{
							if(spaceCount>0){
								$( '#errsectionNameEnglishChange').text("<spring:message code='Error.invalidspace' htmlEscape='true'/>"); 
								return false;
							}
							spaceCount++;
						}else {
							spaceCount=0;
						}
				 }
			 }
			 
			 
		 }
		 
		/*  if(!validateSectionNameUnique(SectionNameEng)){
			
			 return false;
		 } */
	 }
	 return true;
}; 

  validateSectionNameUnique=function(SectionName){
	lgdDwrSectionService.sectionNameUniquewithParent(SectionName, parseInt('${sectionForm.parentCode}'),'${sectionForm.parentType}', {
		callback : function( result ) {
			
			//alert(Boolean(result));
			if( Boolean(result)){
				$( "#btnFormActionUpdate" ).prop( "disabled", true );
				callActionUrl('buildUpdateSection.htm');
			}else{
				 $( '#errsectionNameEnglishChange').text("<spring:message code='Error.section.name.unique' htmlEscape='true'/>"); 
			}
			},
		errorHandler : function(errorString, exception) { alert(errorString + " dwr"); alert(exception); },
		async : true
	});
return false;
}; 

callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=sectionForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=sectionForm]' ).attr('method','post');
	$( 'form[id=sectionForm]' ).submit();
};

</script>
<title><spring:message htmlEscape="true"  code="Label.UPDATESECTION"></spring:message></title>
</head>
<body>

<section class="content">
  <div class="row">
          <!-- main col -->
     <section class="col-lg-12">

        <div class="box">
		      <div class="box-header with-border">
		        <h3 class="box-title"><spring:message code="Label.UPDATESECTION" htmlEscape="true"></spring:message></h3>
		      </div><!-- /.box-header -->
		      

				<form:form action="buildUpdateSection.htm" method="post" id="sectionForm" commandName="sectionForm" class="form-horizontal">
				<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="buildUpdateSection"/>" />
					<form:hidden path="parentCode" />
					<form:hidden path="parentType" />
					<form:hidden path="sectionCode" />
					<form:hidden path="sectionVersion" />
					<!-- General Details of Section Started-->
					
					<div class="box-header subheading">
                  		<h4 class="box-title"><spring:message htmlEscape="true"  code="Label.GENERALDETAILNEWSECTION"></spring:message></h4>
               	    </div><!-- /.box-header -->
					
					<div class="form-group">
							  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.section.name.english" htmlEscape="true"></spring:message></label>
							  <div class="col-sm-6">
									<c:out value="${sectionForm.sectionNameEnglish}"></c:out>
									<form:hidden path="sectionNameEnglish" />
							  </div>
					</div>
					
					<div class="form-group">
							  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SECTIONNAMEENGLISH" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
							  <div class="col-sm-6">
									<form:input path="sectionNameEnglishChange" id="sectionNameEnglishChange" maxlength="200" htmlEscape="true" class="form-control" placeholder="Enter Section Name English" />
									<br/><span>Allowed characters are A-Z,a-z,0-9,/,-,Space,comma,dot,brackets()</span>
									<span class="errormessage" id="errsectionNameEnglishChange"></span>
									<form:errors htmlEscape="true" path="sectionNameEnglishChange" cssClass="error"/>
							  </div>
					</div>
					
					<br/>
					
					<!-- General Details of Section Ends Here-->
				
				
							
				 <div class="box-footer">
	                     <div class="col-sm-offset-2 col-sm-10">
	                       <div class="pull-right">
				
				<input class="btn btn-success" id="btnFormActionUpdate" type="button" value="<spring:message htmlEscape="true" code="Button.UPDATE"/>" />
				<input class="btn btn-danger" id="btnActionClose" type="button" value="<spring:message htmlEscape="true" code="Button.CLOSE"/>" onclick="callActionUrl('home.htm')"/>
			</form:form>	
		</div>
	</div>
	<!-- Page Content ends -->
	<!-- </div> -->
			
</div>
<!-- Main Form Stylings ends -->

</body>
</html>

