<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>E-Panchayat</title>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/State.js"></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'	src='<%=contextpthval%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'></script>
<script type="text/javascript" language="javascript">

$(document).ready(function() {
	$('#form1 input[type=text]').attr("autocomplete",'off');
	$("#form1").validate({
		rules : {
			districtNameInEn : {
				nameFormat : true
			},
			shortName : {
				shortName : true
			},
			census2011Code : {
				onlyNumberSp : true
			},
		},
		messages : {
			districtNameInEn : {
				 nameFormat : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>" 
									
			},
			shortName : {
				shortName : "<font color='red'><br><spring:message code='error.valid.COMMONALPHABETREQUIRED'/></font>" 
							
			},
			census2011Code : {
				onlyNumberSp : "<font color='red'><br><spring:message code='error.valid.COMMONNUMERICREQUIRED'/></font>" 
						
				}	
		}
	});
});

function chkValid(obj) {
	var flag;
	if (obj.value != null) {
		flag=$("#form1").validate().form();
		if(flag)
			return true;
		else
			return false;
	}
}
function partList()
{
	var length=document.getElementById('ddDistrict').options.length;
	if(length==0)
		{
			alert("You can't take all the coverage of part entity");
			return 1;
		}
	else
		return 0;
}

function formsubmit() {
		var flag = 0;
		var tmp = 0;
		
		var selObj = document.getElementById('ddDestDistrict');
		var selObjArray=['ddDestDistrict','ddDestinctDistrict'];
		for(var i=0;i<selObjArray.length;i++)
			{
			var selObj = document.getElementById(selObjArray[i]);
				for (var j = 0; j < selObj.options.length; j++) {
					selObj.options[j].selected = true;
				}
			}
		
		//alert("@@"+document.getElementById('districtNameInEn').value.substr(0, 1).match(/[A-Za-z]/)+"@@") ;
		/* if(document.getElementById('districtNameInEn').value.substr(0, 1).match(/[A-Za-z]/)==null){
			alert("fgh");
			} */
			
		if (document.getElementById('stateOrUt').value == null || document.getElementById('stateOrUt').value == "") 
		{
			document.getElementById("stateOrUt_error").innerHTML = "Kindly select STATE or UT first";
			$("#stateOrUt_error").show();

			document.getElementById('stateOrUt').focus();
			flag = 1;
		} 
		
		else 
			if (document.getElementById('districtNameInEn').value == null || document.getElementById('districtNameInEn').value == "")
			{
			
				document.getElementById("districtNameInEn_error").innerHTML = "Name of new State (In English) required";
				$("#districtNameInEn_error").show();
	
				document.getElementById('districtNameInEn_error').focus();
				flag = 1;
			} 
			
			else if(document.getElementById('districtNameInEn').value.substr(0, 1).match(/[A-Za-z]/)==null){
				document.getElementById("districtNameInEn_error").innerHTML = "In Name of new State (In English) First Letter Must be Alphabet";
				$("#districtNameInEn_error").show();
				document.getElementById('districtNameInEn_error').focus();
				flag = 1;
			}
			else {
					if(document.getElementById('duplicate').value==1)
						{
							flag = 1;
							document.getElementById("districtNameInEn_error").innerHTML = "State is already exists";
							$("#districtNameInEn_error").show();
						}
					tmp = chkValid(document.getElementById('shortName'));
					if (!tmp) 
					{
						flag = 1;
						document.getElementById('shortName').focus();
					} 
					else
					{
						tmp = chkValid(document.getElementById('census2011Code'));
						if (!tmp) {
							flag = 1;
							document.getElementById('census2011Code').focus();
						} 
						else
						{
	
							

								var selObj1 = document.getElementById('ddDestDistrict');
								var stateCode1 = "";
								for (i = 0; i < selObj1.options.length; i++) {
									selObj1.options[i].selected = true;
									stateCode1 += selObj1.options[i].value + ",";
								}
		
								//alert("selObj1"+selObj1);
								//alert("stateCode1"+stateCode1);
								var selObj = document.getElementById('ddDestinctDistrict');
								var stateCode = "";
								for (i = 0; i < selObj.options.length; i++) {
									selObj.options[i].selected = true;
									stateCode += selObj.options[i].value + ",";
								}
		
								if (document.getElementById('ddDestDistrict').value == null || document.getElementById('ddDestDistrict').value == "") {
									alert("Kindly Select Contributing State List" + document.getElementById('ddDestDistrict').value);
									flag = 1;
								} else if (document.getElementById('ddDestDistrict').options.length == 1
										&& (document.getElementById('ddDestDistrict').value.match('FULL')) == "FULL") {
									flag = 1;
									alert("You Can't Make the New  State by Shifting Only One Full State. Kindly Select One More Contributing State.  !");
								} else if (document.getElementById('ddDestinctDistrict').value == null
										|| document.getElementById('ddDestinctDistrict').value == "")
								{

									var list = stateCode1;
									if (list.match('PART')) {
										//alert(list);
										alert("Kindly Select At Least One Contributing District List As Part of State Selected");
										flag = 1;
									}
									
										
								}
								else if (document.getElementById('ddDestinctDistrict').value != null
										|| document.getElementById('ddDestinctDistrict').value != "")
								{

									
										flag=partList();
										
								}
						

					}
				}
			}

		

		//alert("formsubmit"+document.getElementById('districtNameInEn').value);

		//alert("Enter flag condition");
		if (!flag) {
			//alert("kamlesh");
			document.forms['form1'].submit();
		}

	}

	var doRemove = true;


</script>

</head>

<body onload="onloadState()">
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                   <form:form action="addNewState.htm" method="POST" commandName="state" id="form1" name="form1" enctype="multipart/form-data" class="form-horizontal">
				     <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="govtOrderCommon.htm"/>"/>
				      
				        <input type="hidden" name="duplicate"	id="duplicate" />				
					    <div id="dialog-duplicate-state" title="Error Message" style="display: none">
					    <p>
						<span class="ui-icon ui-icon-alert"
							style="float: left; margin: 0 7px 20px 0;"></span>
						<spring:message code="Error.dialog-duplicate-state" htmlEscape="true"></spring:message>
					  </p>
					  </div>
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CREATESTATE" htmlEscape="true"></spring:message></h3>
                                </div>
                                 <div class="box-header subheading">
                                    <h4 ><spring:message code="Label.GENERALDETAILNEWSTATE" htmlEscape="true"></spring:message></h4>
                                </div>
                        <div class="box-body">
                       
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message  code="Label.SELECT" htmlEscape="true"></spring:message>
											<spring:message code="Label.STATEORUT" htmlEscape="true"></spring:message>
											<span id="required" class="errormsg"></span>
											<span class="errormsg">*</span></label>
								<div class="col-sm-6" >
								 <form:select htmlEscape="true" path="stateOrUt" class="form-control" id="stateOrUt" onchange="hideerrorstateOrUt();">
										<form:option value="N" htmlEscape="true"> <spring:message code="Label.SEL" htmlEscape="true"></spring:message> </form:option>
										<form:option value="S" htmlEscape="true"> <spring:message code="Label.STATE" htmlEscape="true"></spring:message> </form:option>
										<form:option value="U" htmlEscape="true"> <spring:message code="Label.UT" htmlEscape="true"></spring:message> </form:option>						
														
									</form:select> 
									<div id="stateOrUt_error" class="errormsg" style="display:none" ></div><br /> 
									<form:errors path="stateOrUt" class="errormsg" htmlEscape="true"></form:errors>  
								  </div>
								  
						   <form:hidden path="operation" value="C" htmlEscape="true"/>
							<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true" />
						</div>  
						
						
						 <div class="form-group">
								<label class="col-sm-3 control-label" for="districtNameInEn"><spring:message code="Label.STATENAMEINENGLISH"> </spring:message> <span class="errormsg">*</span></label>
											
								<div class="col-sm-6" >
								  <form:input path="districtNameInEn" id="districtNameInEn" maxLength="50" onkeyup="return chkValid(this);"  class="form-control" onfocus="show_msg('districtNameInEn')" htmlEscape="true"
									 onblur="findDuplicate(this);NameInEn()" /> 
									<form:errors path="districtNameInEn" class="errormsg" htmlEscape="true"></form:errors>  	
									<div class="errormsg" id="districtNameInEn_error"></div>  
								  </div>
								  
								  
						</div> 
						
						 <div class="form-group">
								<label class="col-sm-3 control-label" for="shortName"><spring:message code="Label.SHORTNAMEENGLISH" htmlEscape="true"></spring:message> <span class="errormsg">*</span>
								</label>		
								<div class="col-sm-6" >
								  <form:input path="shortName" id="shortName" maxLength="2" htmlEscape="true"  class="form-control" onfocus="show_msg('shortName')" onkeyup="return chkValid(this);" />
										<form:errors path="shortName" class="errormsg" htmlEscape="true"></form:errors> 
								  </div>
								  
						</div>
						
							<div class="form-group">
								<label class="col-sm-3 control-label" for="census2011Code"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message> <span class="errormsg">*</span>
								</label>
								<div class="col-sm-6" >
								   <form:input path="census2011Code" onkeyup="return chkValid(this);" htmlEscape="true" id="census2011Code" maxLength="6" class="form-control" onfocus="show_msg('census2011Code')" onblur="Cns2011Code()" />
									<form:errors path="census2011Code" class="errormsg" htmlEscape="true"></form:errors> 
								  </div>
								  
						</div>
						
						
					
                 </div> 
              <div class="form-group" style="margin-left: 10px;">
                   <%@ include file="../common/headquarterCopy.jspf" %>   
               </div>
                   
              
               <div class="box-body">
	              <div class="box-header subheading">
                    <h3 class="box-title"><spring:message code="Label.CONTRIBUTINGLANDREGION" htmlEscape="true"></spring:message></h3> </div>
               
	           <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message code="Label.STATELIST" htmlEscape="true"></spring:message> </label>
		               <form:select path="stateNameEnglishMain" class="form-control" id="ddSourceDistrict" 
												   multiple="true" items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish" htmlEscape="true">										
				 </form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br>
						<button name="button1" type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItem('ddDestDistrict','ddSourceDistrict','FULL',true);"  ><spring:message  code="Button.WHOLE"/></button>
              			<button name="button2" type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeOneItem('ddDestDistrict', 'ddSourceDistrict', true)"  >&lt;</button>
              			<button name="button3" type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAllState('ddDestDistrict', 'ddSourceDistrict', true,'ddDistrict','ddDestinctDistrict')"  >&lt;&lt;</button>
              			<button name="button4" type="button" class="btn btn-primary btn-xs btn-block" onclick="addItem('ddDestDistrict','ddSourceDistrict', 'PART',true);"  >Part &gt;&gt;</button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRISTATELIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			        <form:select name="select4"  id="ddDestDistrict"  multiple="multiple"  path="STATENAMEENGLISH" class="form-control"  htmlEscape="true">
				        <form:options htmlEscape="true" items="${listState}" itemLabel="stateNameEnglish" 	itemValue="stateCode"/>  
		            </form:select>
					    
					    <form:errors path="STATENAMEENGLISH" class="errormsg" htmlEscape="true"></form:errors>  	
					    <button name="button5" class="btn btn-primary"  type="button"  onclick="selectEverything();"  >Get District List</button>
		     </div>				
            </div>
         </div>
         
         
         
         
         <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		               <label for="ddSourceBlock"><spring:message code="Label.DISTRICTLIST" htmlEscape="true"></spring:message> </label>
		               <form:select htmlEscape="true" path="STATENAMEENGLISH" class="form-control" id="ddDistrict"  multiple="true" itemLabel="districtCode" itemValue="STATENAMEENGLISH"></form:select>
				
		        </div>
					 <div class="ms_buttons col-sm-2"><br>
						 <button name="button6" type="button" class="btn btn-primary btn-xs btn-block"  onclick="addItem('ddDestinctDistrict','ddDistrict','FULL',true);"  ><spring:message code="Button.WHOLE" htmlEscape="true"/></button>
                         <button name="button7" type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeOneItem('ddDestinctDistrict', 'ddDistrict', true)"  >&lt;</button>
                         <button name="button8" type="button" class="btn btn-primary btn-xs btn-block"  onclick="removeAllDist('ddDestinctDistrict', 'ddDistrict', true)" >&lt;&lt;</button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message code="Label.CONTRIBUTEDISTRICTLIST" htmlEscape="true"></spring:message><span class="mandatory">*</span></label> 
			        <form:select name="select4"  id="ddDestinctDistrict" multiple="multiple" htmlEscape="true" path="DistrictNameEnglish" class="form-control" > 
				         <form:options items="${listDist}" itemLabel="districtNameEnglish" itemValue="districtCode" htmlEscape="true"/>  
				    </form:select>
					    
					     <form:errors path="DistrictNameEnglish" class="errormsg" htmlEscape="true"></form:errors>  	
		     </div>				
            </div>
         </div>
         
         
         <div id='reorgOption'   style="visibility: hidden; ">
		   <div class="form-group">
		   <label class="col-sm-1"></label>
			  <label class="radio-inline"> 
			  <form:radiobutton path="newSubdistrict"  id="newDistrict" value="createDistrict" type="radio" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
				 <spring:message code="Label.CREATEDISTRICT" htmlEscape="true"></spring:message> </label>
				 
				 <label class="radio-inline">
				 <form:radiobutton path="newSubdistrict" type="radio" name="modifyDistrict" value="modifyDistrict" id="isModifyDistrict" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
					<spring:message code="Label.MODIFYDISTRICT" htmlEscape="true"></spring:message>
					</label>		
										
		      </div>
									
			<div class="form-group">	
			  <label class="radio-inline"> <form:radiobutton path="newSubdistrict"  id="newVillage" value="create" type="radio" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
								<spring:message code="Label.CREATENEWSUBDIS" htmlEscape="true"></spring:message>
				</label>
				
				 <label class="radio-inline">
				     <form:radiobutton path="newSubdistrict" name="modifySubDistrict"	type="radio" value="modify" id="isModifySubdistrict" onchange="reorgOption()" htmlEscape="true"></form:radiobutton>
					<spring:message code="Label.MODIFYSUBDISTRICT" htmlEscape="true"></spring:message>
				</label>	
			</div>	
			
			<div class="form-group">	
			  <label class="radio-inline">
                  <form:radiobutton path="newSubdistrict" id="newSubdistrict" value="createvill" type="radio" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
								<spring:message code="Label.CREATENEWVILLAGE" htmlEscape="true"></spring:message>
				</label>
				
				 <label class="radio-inline">
				   	<form:radiobutton path="newSubdistrict"  name="modifyVillage"  type="radio"  value="modifyvill" id="isModifyVillage" onchange="reorgOption()" htmlEscape="true"></form:radiobutton> 
						<spring:message code="Label.MODIFYVILLAGE" htmlEscape="true"></spring:message>
				</label>	
			</div>	
			
		</div>
		       <input type="hidden" name="reorganized" id="reorganized"/>
				<input type="hidden" name="modifyVillage" id="modifyVillage"/>
				<input type="hidden" name="modifySubDistrict" id="modifySubDistrict"/>
				<input type="hidden" name="modifyDistrict" id="modifyDistrict"/>
				<input type="hidden" name="addAnotherSD" id="addAnotherSD"/>
		
        </div>
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button  type="button" class="btn btn-info" id="Reorganize" style="visibility: hidden"   onclick="checkforModifyforstate();"><spring:message code="Button.REORGANIZE"></spring:message></button> 
				  <button type="button" class="btn btn-success" id="Submit" name="Submit"   	onclick="formsubmit();"><spring:message code="Button.SAVE"></spring:message> </button>
				   <button type="button" class="btn btn-danger" name="Submit6"  onclick="javascript:go('cancelST.htm');" > <spring:message code="Button.CLOSE" htmlEscape="true"></spring:message> </button>
				   <button type="button" class="btn btn-info" id="Proceed" style="visibility: hidden" value="<spring:message code="Button.PROCEED" htmlEscape="true"></spring:message>"  onclick="selectFinal();"> </button>
                 </div>
           </div>   
       </div> 
     </div>   
             
    </form:form>      
   </section>
   </div>
   </section>
	<script src="/LGD/JavaScriptServlet"></script>
</body>
</html>
				
				