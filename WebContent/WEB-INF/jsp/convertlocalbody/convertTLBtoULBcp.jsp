<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*,in.nic.pes.lgd.bean.GetLocalGovtSetup"%>
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<title>Convert RLB to ULB</title>
<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<script src="js/validation.js"></script>

<!-- <script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<link href="css/error.css" rel="stylesheet" type="text/css" /> -->

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type="text/javascript" language="javascript">
var level = "<c:out value='${ddLevel}'/>";
var isSingleTier=	isParseJson( '${isSingleTier}' )
function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 

$(document).ready(function() {
	$("#convertForm").validate({ 
    rules: { 
    	localBodyNameInEn:{
    		onlyLetterNumberWithDot:true
    	},
    	localBodyNameInLcl:{
    	   nameFormatLocal: true
       },
       localBodyliasInLcl:{
    	   nameFormatLocal: true
       }
    }, 
    messages: { 
    	localBodyNameInEn: {
    		onlyLetterNumberWithDot: "<font color='red'><br><spring:message code='error.enter.valid.format' text='Please enter value in correct format'/></font>"
       },
    	localBodyNameInLcl: {
    		nameFormatLocal: "<font color='red'><br><spring:message code='TRG.req.trg.type' text='Please enter in local language only'/></font>"
       },
       localBodyliasInLcl: {
    	   nameFormatLocal: "<font color='red'><br><spring:message code='TRG.req.trg.need' text='Please enter in local language only'/></font>"
       }
    } 
  });
 });  


</script>
<script src="js/convertRLBtoULB.js"></script>
</head>
<body onsubmit="cursorwait();" oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);" >
<section class="content">

                <div class="row">
                    <section class="col-lg-12">
                    <form:form commandName="convertTLBtoULB" action="draftConversionTLBtoULB.htm" method="POST" enctype="multipart/form-data" name="convertForm" id="convertForm" class="form-horizontal">
				      <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftConversionTLBtoULB.htm"/>" />
				      <input type="hidden" id="stateid" name="stateid" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" />
				     
				      
                    <div class="box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><spring:message code="Label.CONVERTTLBTOULB" htmlEscape="true"></spring:message></h3>
                                </div>
                     <div id="divFirstPanel" >
                     	<div id="divDIV">  
                                  <div class="box-header subheading">
                                    <h4 ><spring:message code="Label.CONVERSIONTLB" htmlEscape="true"></spring:message></h4>
                                </div>
                            	<form:hidden path="hdnState" value="${stateCode}" id="hdnState" htmlEscape="true"/>
					  			<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/>
					 			 <form:hidden path="mergeUlbClick" htmlEscape="true" value="${convertTLBtoULB.mergeUlbClick}" id="mergeUlbClick" />
					   			<form:hidden path="districtLocalBodyCode" htmlEscape="true" value="${convertTLBtoULB.districtLocalBodyCode}" id="hdnDistrictCode" />
					   			<form:hidden path="interLocalBodyCode" htmlEscape="true" value="${convertTLBtoULB.interLocalBodyCode}" id="hdnInterLBCode" />
					  			<form:hidden path="declareUlbClick" htmlEscape="true" value="${convertTLBtoULB.declareUlbClick}" id="declareUlbClick" />
					    	   	<form:hidden path="operationCode" htmlEscape="true" value="${convertTLBtoULB.operationCode}" ></form:hidden>
								<form:hidden path="lbType" value="${convertTLBtoULB.lbType}" htmlEscape="true"></form:hidden>         
                        <div class="box-body">
                        <c:if test="${ddLevel.contains('D')}">
                            <div class="form-group">
								<label for="ddSourceDistrict" class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTDISTICTPANCHAYAT"></spring:message><span class="mandatory">*</span></label>
								<div class="col-sm-6" >
							   <form:select  id="ddDistrictPanchayat" path="districtLocalBodies" class="form-control" htmlEscape="true" onchange="getIntermediatePanchayatbyDcode(this.value);"
											  onfocus="validateOnFocus('ddDistrictPanchayat');helpMessage(this,'ddDistrictPanchayat_msg');" onblur="vlidateOnblur('ddDistrictPanchayat','1','15','c');hideHelp();"
											   onkeyup="hideMessageOnKeyPress('ddDistrictPanchayat')">
										<form:option value="0" htmlEscape="true"><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
										<c:forEach items="${districtPanchayatList}" var="districtPanchayatList">
												<c:if test="${districtPanchayatList.operation_state =='A'.charAt(0)}">
													<form:option value="${districtPanchayatList.localBodyCode}" htmlEscape="true"><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>
												    </form:option>
												</c:if>
												<c:if test="${districtPanchayatList.operation_state =='F'.charAt(0)}">
													<form:option value="${districtPanchayatList.localBodyCode}" htmlEscape="true" disabled="true"><esapi:encodeForHTMLAttribute>${districtPanchayatList.localBodyNameEnglish}</esapi:encodeForHTMLAttribute>																			
													</form:option>
												</c:if>
										</c:forEach>
							      </form:select>
										<span> <form:errors path="districtLocalBodies" class="errormsg" htmlEscape="true"></form:errors> </span>
										<span id="ddDistrictPanchayat_error" class="errormsg"><spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message>	</span>
										<span id="ddDistrictPanchayat_msg" style="display: none;"> <spring:message code="Error.DISTRICTLOCALBODY" htmlEscape="true"></spring:message> </span>
								  </div>
						  </div>  
						</c:if> 
                
                
                <c:if test="${ddLevel.contains('DI') || ddLevel.contains('IV')}">
					<div class="form-group">
					<label class="col-sm-3 control-label"><spring:message code="Label.SELINTERMPANCHYAT" htmlEscape="true"></spring:message> <span class="errormsg">*</span></label>
					<div class="col-sm-6"> 
						 <form:select id="ddIntermediateLb" path="intermediateLocalBodies" class="form-control" htmlEscape="true" onchange="getVillageLocalBodies(this.value)"
									 onfocus="validateOnFocus('ddIntermediateLb');helpMessage(this,'ddIntermediateLb_msg');" onblur="vlidateOnblur('ddIntermediateLb','1','15','c');hideHelp();"
									onkeyup="hideMessageOnKeyPress('ddIntermediateLb')">						
									<form:option value="0" htmlEscape="true">  <spring:message code="Label.SELECT" htmlEscape="true"></spring:message> </form:option>
									<form:options items="${interPanchayatList}" htmlEscape="true"  itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
			             </form:select>
			                   <span><form:errors htmlEscape="true" 	path="intermediateLocalBodies" class="errormsg"></form:errors> </span>
			                    <span id="ddIntermediateLb_error" class="errormsg"> <spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message> </span>
			                     <span id="ddIntermediateLb_msg" style="display: none;"> 	<spring:message code="Error.INTERMEDIATELOCALBODY" htmlEscape="true"></spring:message> </span>
					   </div>
					 </div>
			    </c:if>
                
              <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		              <label><spring:message htmlEscape="true"	code="Label.AVAILVILLAGEPANCHAYATLIST"></spring:message> </label> 
		              <form:select id="ddSourceVillageRLBS" items="${villageLBList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" path="villagelocalbodyNameSource" 
 							 multiple="multiple" class="form-control" htmlEscape="true">
						</form:select>
		        </div>
					 <div class="ms_buttons col-sm-2"><br>
						<button type="button"  class="btn btn-primary btn-xs btn-block" onclick="addItemULB('ddDestVillageRLBs','ddSourceVillageRLBS','FULL',true);" ><spring:message code="Button.WHOLE"/></button>
						<button type="button" class="btn btn-primary btn-xs btn-block"  id="btnback" name="Submit4"  value="" onclick="removeItem('ddDestVillageRLBs', 'ddSourceVillageRLBS', true)" >Back </button>
						<button type="button" class="btn btn-primary btn-xs btn-block" value=" Reset &lt;&lt;" onclick="removeAll('ddDestVillageRLBs', 'ddSourceVillageRLBS', true)" >Reset</button>
						<button type="button" class="btn btn-primary btn-xs btn-block" value="Part &gt;&gt;" onclick="addItemULB('ddDestVillageRLBs','ddSourceVillageRLBS', 'PART',true);" >Part <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label for="ddDestBlock"><spring:message htmlEscape="true" code="Label.SELECTEDRLBS"></spring:message><span class="mandatory">*</span></label> 
			    <form:select path="villagelocalbodyNameDest" id="ddDestVillageRLBs"  multiple="multiple" class="form-control" htmlEscape="true"  onfocus="validateOnFocus('ddDestVillageRLBs');helpMessage(this,'ddDestVillageRLBs_msg');"
		                   onblur="vlidateOnblur('ddDestVillageRLBs','1','15','p');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDestVillageRLBs')">
                    </form:select>
															
						<span> <form:errors htmlEscape="true"	path="villagelocalbodyNameDest" class="errormsg"></form:errors></span> 
						<span id="ddDestVillageRLBs_error" class="errormsg"><spring:message code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message></span> 
						<span id="ddDestVillageRLBs_PART_error" class="errormsg"> <spring:message htmlEscape="true" code="error.select.VILLAGEPANCHAYT"></spring:message> </span>
						<span id="ddDestVillageRLBs_msg"  style="display: none;"> <spring:message code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message> </span>	
						
						<button type="button" id="childbtn"   class="btn btn-primary" onclick="selectVillageLocalBodyforrlbtoulb();"><spring:message code="Button.GETCOVEREDAREAOFLOCALBODY" htmlEscape="true"/></button>	
		     </div>				
            </div>
         </div>
         
         
         
         
         <div class="ms_container row" style="margin-left: 10px;">
	           <div class="ms_selectable col-sm-5 form-group">
		              <label><spring:message htmlEscape="true" 	code="Label.AVAILBALECOVEREDAREA"></spring:message> </label> </label> 
		              <form:select id="ddSourceCoveredAreaRLB" path="localBodyLandRegionAreaSource"  multiple="multiple" class="form-control" htmlEscape="true">
					    </form:select>
						 <span class="errormsg" id="ddSourceCoveredAreaRLB_error"><spring:message code="Error.TARGETVILLAGELB" htmlEscape="true"></spring:message> </span> 
						 <form:errors path="localBodyLandRegionAreaSource" class="errormsg" htmlEscape="true"></form:errors>
		        </div>
					 <div class="ms_buttons col-sm-2"><br><br>
						<button type="button" class="btn btn-primary btn-xs btn-block" id="btnAdd" value=" &gt;&gt;" onclick="listbox_moveacross('ddSourceCoveredAreaRLB', 'ddDestCoveredAreaRLB', this)" ><i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
						<button type="button" class="btn btn-primary btn-xs btn-block" value=" &lt;&lt;" onclick="listbox_moveacross('ddDestCoveredAreaRLB', 'ddSourceCoveredAreaRLB')" ><i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
					 </div>
			<div class="ms_selection col-sm-5">
			 <div class="form-group">
			    <label><spring:message htmlEscape="true"  code="Label.SELECTCOVEREDAREA"></spring:message> </label>
			    <form:select path="localBodyLandRegionAreaDest" id="ddDestCoveredAreaRLB"  multiple="multiple" class="form-control" htmlEscape="true" onfocus="validateOnFocus('ddDestCoveredAreaRLB');helpMessage(this,'ddDestCoveredAreaRLB_msg');" 
				                        onblur="vlidateOnblur('ddDestCoveredAreaRLB','1','15','p');hideHelp();" onkeyup="hideMessageOnKeyPress('ddDestCoveredAreaRLB')">
					</form:select>
					<span class="errormsg" id="ddDestCoveredAreaRLB_error"><spring:message htmlEscape="true" code="Error.TARGETCOVEREDAREA"></spring:message> </span> 
					<form:errors path="localBodyLandRegionAreaDest" class="errormsg" htmlEscape="true"></form:errors>
                    <span style="display: none;" id="ddDestCoveredAreaRLB_msg"><spring:message code="Error.TARGETCOVEREDAREA" htmlEscape="true"></spring:message> </span>	
						
		     </div>				
            </div>
         </div>
         
      </div> 
             
                       
     
    <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                  <button type="button" class="btn btn-info" name="Submit" id="btnNext" onclick="return validateFirstAll('T');" ><spring:message code="Button.NEXTSTEP" htmlEscape="true"></spring:message></button>
                   <button type="button" class="btn btn-danger " name="Cancel"  id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> Close</button>
                 </div>
           </div>   
       </div>

    </div>
    </div>
  <!-- ---------------------------------------------------------This section is for click on proceed button event -------------------------------------------------------------->    
       <div id="divSecondPanel" style="display: none">
           <div class="box-header subheading">
                      <h4 ><spring:message code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message></h4>
           </div>
         <div class="form-group">
         	<label class="col-sm-3 control-label"></label>
	         <div class="col-sm-6">
				 <label class="radio-inline">
				 <form:radiobutton path="mergeRLBtoULB" htmlEscape="true" id="rdmergeRLBtoULB" value="M" onclick="radioClick(this.value);" /> <spring:message code="Label.MERGERLBTOULB" htmlEscape="true"></spring:message> </label> 
				<label class="radio-inline"> <form:radiobutton path="declarenewULB" id="rddeclarenewULB" onclick="radioClick(this.value);" value="N" htmlEscape="true"/> &nbsp; <spring:message  code="Label.DECLARENEWULB" htmlEscape="true"></spring:message></label>
			     <span id="rdmergeRLBtoULB_error" class="errormsg"></span>
				 <form:errors path="mergeRLBtoULB" class="errormsg" htmlEscape="true"></form:errors>
				 <form:errors path="declarenewULB" class="errormsg" htmlEscape="true"></form:errors>
			</div>								
	     </div>
       
       
       <div  id="divmergeRLB">
			<div class="box-header subheading"><h4><spring:message code="Label.EXISTINGULB" htmlEscape="true"></spring:message></h4></div>
								
		<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message	code="Label.SELURBANTYPEBODY" htmlEscape="true"></spring:message><span  class="errormsg">*</span></label> 
				<div class="col-sm-6">
					<form:select id="ddUrbanLocalBodyType" path="urbanLgTypeName"  class="form-control" onchange="getUrbanLocalBodies(this.value)" htmlEscape="true">
						<form:option value="0" htmlEscape="true"> <spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message> </form:option>
						<form:options items="${urbanlocalbodyType}"  itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
					</form:select> 
					<span id="ddUrbanLocalBodyType_error" class="errormsg"><spring:message code="Error.URBANLBTYPE" htmlEscape="true"></spring:message> </span>
					 <form:errors	path="urbanLgTypeName" class="errormsg" htmlEscape="true"></form:errors>
				 </div>
		</div>
		
		<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message	code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
	      <div class="col-sm-6">		
			<form:select id="ddUrbanLocalBody" path="urbanlocalBodyNameEnglish"  class="form-control" htmlEscape="true" onchange="askForUpgrade(this);">
				<form:option value="0" htmlEscape="true"><spring:message code="Label.SELURBANLOCALBODY" htmlEscape="true"></spring:message></form:option>
			</form:select>
			<span id="ddUrbanLocalBody_error" class="errormsg"> <spring:message code="Error.URBANLB" htmlEscape="true"></spring:message> </span> 
			<form:errors path="urbanlocalBodyNameEnglish" class="errormsg" htmlEscape="true"></form:errors>
			</div>									
		</div>
		
		<div class="form-group" id="trForUpgrade1" style="display:none;" >
			<label class="col-sm-3 control-label"><spring:message code="Label.ASKQUESTION" htmlEscape="true" text="Do you want to upgrade?"></spring:message><span class="errormsg">*</span></label>
			<div class="col-sm-6">
				<label class="radio-inline"><form:radiobutton id="upgradeType" path="upgradeType" value="Y" onclick="selectForUpgrade(this)"></form:radiobutton>YES</label>
				<label class="radio-inline"><form:radiobutton id="upgradeType" path="upgradeType" value ="N" onclick="selectForUpgrade(this)"></form:radiobutton>NO</label>
				<span id="upgradeType_error" class="errormsg"><spring:message code="Label.SELECTRURALBODY" htmlEscape="true"></spring:message></span>
			</div>									
		</div>
		
		<div class="form-group"  id="trForUpgrade2" style="display:none;">
			<label class="col-sm-3 control-label"><spring:message code="Label.SELUPGRADEURBANLOCALBODY" htmlEscape="true" text="Select for upgrade"></spring:message><span class="errormsg">*</span></label>
			<div class="col-sm-6">
				<form:select id="ddUrbanLocalBodyTypeForUpgrade" path="urbanLgTypeName"  class="form-control" disabled="true" htmlEscape="true">
				<form:option value="0" htmlEscape="true"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></form:option>
				<form:options items="${urbanlocalbodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
				</form:select>
				<span id="ddUrbanLocalBodyTypeForUpgrade_error" class="errormsg"><spring:message code="Error.URBANLBTYPE" htmlEscape="true"></spring:message></span>
				<form:errors path="urbanLgTypeName" class="errormsg" htmlEscape="true"></form:errors>
		   </div>	
		</div>
		
	</div>
       
       
       
       <div id="divdeclareNewULB" >
        <div class="box-header subheading"><h4><spring:message code="Label.NEWULB" htmlEscape="true"></spring:message></h4></div>
								<!-- For  next button -->
		
		<div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"></spring:message><span id="required" class="errormsg"></span><span class="errormsg">*</span></label>
			<div class="col-sm-6">
				<form:input path="localBodyNameInEn" htmlEscape="true" id="txtlocalBodyNameInEn"  class="form-control" onkeypress="ULbTypeDefualtSet();" />
				<div id="UniqueULBrror" class="errormsg"></div>
				<span class="errormsg" id="txtlocalBodyNameInEn_error"><spring:message htmlEscape="true" code="Error.URBANLBNEW"></spring:message></span>
				<span><form:errors path="localBodyNameInEn" class="errormsg" htmlEscape="true"></form:errors></span>
			</div>
		</div>
											
		<div class="form-group">
			<label class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"></spring:message></label>
			<div class="col-sm-6">
				<form:input path="localBodyNameInLcl" id="txtlocalBodyNameInLcl" htmlEscape="true" 	size="40" class="form-control" /><span>
				<form:errors path="localBodyNameInLcl" class="errormsg"/></span>
			</div>
		</div>
	
       <div class="form-group">
			<label class="col-sm-3 control-label"><spring:message	code="Label.LOCALBODYALIASENGLISH" htmlEscape="true"></spring:message></label>
			<div class="col-sm-6">
				<form:input path="localBodyliasInEn" htmlEscape="true" id="txtlocalBodyNmeInAlias" class="form-control" onkeypress="validateAlphanumericKeys();" /> <span><form:errors
				path="localBodyliasInEn" class="errormsg" htmlEscape="true"></form:errors> </span>
		  </div>
	  </div>
	
	<div class="form-group">
	   	<label class="col-sm-3 control-label"><spring:message code="Label.LOCALBODYALIASLOCAL" htmlEscape="true"></spring:message><</label>
	    <div class="col-sm-6">
		    <form:input 	path="localBodyliasInLcl" id="txtlocalBodyliasInLcl"  class="form-control" htmlEscape="true" />
		    <span><form:errors path="localBodyliasInLcl" class="errormsg"></form:errors></span>
		</div>									
	</div>
											
	<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.SELURBANTYPEBODY" htmlEscape="true"></spring:message><span class="errormsg">*</span></label>
		 <div class="col-sm-6">
			   <form:select id="ddUrbanLocalBodyTypeNew" path="urbanLgTypeNameNew" htmlEscape="true" size="1" class="combofield" onchange="ConvertULBnameVal(this.value);">
				<form:option value="0" htmlEscape="true"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></form:option>
				<form:options items="${urbanlocalbodyType}" itemLabel="localBodyTypeName" itemValue="localBodyTypeCode" htmlEscape="true"/>
				</form:select> 
				<span id="ddUrbanLocalBodyTypeNew_error" class="errormsg"><spring:message code="Error.URBANLBTYPENEW" htmlEscape="true"></spring:message> </span>
				 <form:errors	path="urbanLgTypeNameNew" class="errormsg" htmlEscape="true"></form:errors>
			</div>									
	  </div>
  </div>	
						
       
      <div class="box-footer">
           <div class="col-sm-offset-2 col-sm-10">
                 <div class="pull-right">
                 <div id="divPreviousButtons" >
                        <button  type="button" class="btn btn-primary" value="" onclick="onPreviousClick();" class="btn" ><spring:message code="Button.PREVIOUS" htmlEscape="true"></spring:message></button>
                   </div>
                   <div class="btnpnl" id="divSaveButtons">
		                   <button type="button"  onclick="onPreviousClick();" class="btn btn-info" ><spring:message code="Button.PREVIOUS" htmlEscape="true"></spring:message> </button>
					       <button type="submit" class="btn btn-success" name="Submit" onclick="return validateSecondULBAll();"  ><spring:message code="Button.SAVE"></spring:message></button>
		                   <button type="button" class="btn btn-danger" name="Submit6" value="" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" ><spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>
                 </div>
                 </div>
           </div>   
       </div> 
 </div>
   <!--  --------   --------------------- -->
       
     </div>   
             
    </form:form>  
    <script>
			loadConvertULBPage();
			</script>
			<c:if test="${isError == true}">
				<script>
					hasError();
				</script>
			</c:if>    
   </section>
   </div>
   </section>
<script src="/LGD/JavaScriptServlet"></script>

</body>

</html>