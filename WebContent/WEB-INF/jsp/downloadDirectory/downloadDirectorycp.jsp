<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@include file="../homebody/commanInclude.jsp"%>
<%@include file="downloadDirectoryJs.jsp"%>
<c:set var="downloadPDF" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_PDF.toString()%>"></c:set>
<c:set var="downloadXLS" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_XLS.toString()%>"></c:set>
<c:set var="downloadCSV" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_CSV.toString()%>"></c:set>
<c:set var="downloadODT" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_ODT.toString()%>"></c:set>
<c:set var="downloadHTM" value="<%=in.nic.pes.lgd.constant.ReportConstant.DOWNLOAD_DIRECTORY_HTM.toString()%>"></c:set>
<title><spring:message htmlEscape="true"  code="Label.DD"></spring:message></title>
</head>
<body>

<section class="content">
  <div class="row">
    <!-- main col -->
    <section class="col-lg-12">
		<div class="box">
	      <div class="box-header with-border">
	        <h3 class="box-title"><spring:message code="Label.DD" htmlEscape="true"></spring:message></h3>
	      </div><!-- /.box-header -->
<!-- Page Content starts -->
		<form:form action="downloadDirectory.do" method="post" id="downloadDirectoryForm" commandName="downloadDirectoryForm" class="form-horizontal">
		<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="downloadDirectory.do"/>" />
		
		<div class="box-header subheading">
			<h4 class="box-title"><spring:message code="Label.DDCRIT" htmlEscape="true"></spring:message>	</h4>
		</div><!-- /.box-header -->		
		
		<div class="form-group">
		   	  <label  class="col-sm-1 control-label" for="sel1"></label>
			  <div class="col-sm-6">
			  <label class="radio-inline">
			   		<input type="radio"  id="DFDOption" value="DFD" onclick="showHideOption();" name="DDOption"  /> 
			   		<spring:message htmlEscape="true" code="Label.DF"></spring:message>
			  </label>
			  <label class="radio-inline">
			  	<input type="radio"  id="DSWDOption" value="DSWD" onclick="showHideOption();" name="DDOption" />
				<spring:message code="Label.DSWD" htmlEscape="true" ></spring:message>
			  </label>
			  <label class="radio-inline">
			  	<input type="radio"  id="DMDOption" value="DMO" onclick="showHideOption();" name="DDOption" />
				<spring:message code="Label.DM" htmlEscape="true" ></spring:message>
			  </label>
			   <label class="radio-inline">
			  	
				<input type="radio"  id="UNSELECT" value="UNSELECT" checked="checked" style="Display:none" name="DDOption" />
				<form:hidden path="downloadOption" />
				<span class="errormsg" id="errrdownloadOption"></span>
				<form:errors htmlEscape="true" path="downloadOption" cssClass="error"/>
			  </label>
			  </div>
		</div>
<!-- Download Option Body end-->
<!-- Download Option full Directory  Started-->
<div id="DFD" style="Display:none">
	<div class="form-group" >
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.DDENTITY" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
	  <div class="col-sm-4">
			 <form:select path="rptFileName" id="rptFileName" class="form-control">
         		<form:option value="-1">
					<spring:message code="Label.SEL" htmlEscape="true"></spring:message>
				</form:option>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.STATECAPS'></spring:message>">
						<option value="allStateofIndia" ><spring:message  htmlEscape="true" code='Label.ALLSTATE'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.DISTRICTCAPS'></spring:message>">
						<option value="allDistrictofIndia" ><spring:message  htmlEscape="true" code='Label.ALLDISTRICT'></spring:message></option>
						<option value="districtofSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLDISTRICTSTATE'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.SUBDISTRICTCAPS'></spring:message>">
						<option value="allSubDistrictofIndia" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICT'></spring:message></option>
						<option value="subDistrictofSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICTSTATE'></spring:message></option>
						<option value="subDistrictofSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICTDISTRICT'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.VILLAGECAPS'></spring:message>">
						<option value="allVillagesofIndia" ><spring:message  htmlEscape="true" code='Label.Village.India'></spring:message></option>
						<option value="villageofSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGESTATE'></spring:message></option>
						<option value="villageofSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGEDISTRICT'></spring:message></option>
						<option value="villageofSpecificSubdistrict@state#district#subdistrict" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGESUBDISTRICT'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.LOCALBODY'></spring:message>">
						<option value="priLbSpecificState@state" ><spring:message  htmlEscape="true" code='Label.PRISPECIFICSTATE'></spring:message></option>
						<option value="priLbSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.PRISPECIFICDISTRICT'></spring:message></option>
						<option value="ulbSpecificState@state" ><spring:message  htmlEscape="true" code='Label.ULBSPECIFICSTATE'></spring:message></option>
						<option value="ulbSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.ULBSPECIFICDISTRICT'></spring:message></option>
						<option value="tlbSpecificState@state" ><spring:message  htmlEscape="true" code='Label.TLBSPECIFICSTATE'></spring:message></option>
						<option value="tlbSpecificDistrict@state#district" ><spring:message  htmlEscape="true" code='Label.TLBSPECIFICDISTRICT'></spring:message></option>
						<option value="uLBWardforState@state" ><spring:message  htmlEscape="true" code='Label.ULBSTATESPECIFICWITHWARD' ></spring:message> </option>
						<option value="statewise_localbody_ward_coverage@state" ><spring:message  htmlEscape="true" code='ULBSTATESPECIFICWITHWARDWITHCOV' ></spring:message> </option> --%>
						
						
						<option value="priWards@state" ><spring:message  htmlEscape="true" code='Label.PRISTATEWARDS' ></spring:message> </option>
						<option value="priLocalBodyIndia" ><spring:message  htmlEscape="true" code='Label.PRIINDIA'></spring:message></option>
						<option value="allTraditionalLBofInida" ><spring:message  htmlEscape="true" code='Label.TLBINDIA'></spring:message></option>
						
						<option value="urbanLocalBodyIndia" ><spring:message  htmlEscape="true" code='Label.UrbanINDIA'></spring:message></option>
						<option value="statewise_ulbs_coverage" ><c:out value="Urban Localbodies with Coverage" /></option>
					</optgroup>
					
					
					<optgroup label="<spring:message  htmlEscape="true" code='Label.PARLIAMENTLVSASSEMBLYCONSTITUENCY'></spring:message>">
						<option value="parliamentConstituency@state#parliament" ><spring:message  htmlEscape="true" text="Parliament Wise Local Body Mapping"></spring:message></option>
						<option value="assemblyConstituency@state#parliament#assembly" ><spring:message  htmlEscape="true" text="Assembly Wise Local Body Mapping"></spring:message></option>
						<option value="parlimentConstituencyAndAssemblyConstituency@state" >State Wise Parliament Constituency and Assembly Constituency</option>
					</optgroup>
					
					<optgroup label="<spring:message  htmlEscape="true" code='Label.DDLBMAPPINGCENSUSLANDCODE'></spring:message>">
						<option value="LocalbodyMappingtoCensusLandregionCode@state" ><spring:message  htmlEscape="true" code='Label.DDLBMAPPINGCENSUSLANDCODE'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.BLOCK'></spring:message>">
						<option value="blockofspecificState@state" ><spring:message  htmlEscape="true" code='Label.ALLBLOCK'></spring:message></option>
						<option value="allBlockofIndia" ><spring:message  htmlEscape="true" code='Label.ALLBLOCKINDIA'></spring:message></option>
						<option value="allBlockofIndiawithCoverage"><c:out value="All Block of India with Covered Villages" /></option>
						<!-- [lgd 0012459]: Facility to download Subdistrict-villageBlock mapping -->
						<option value="allSubdistrictVillageBlockMapping" >All India Subdistrict-Village-Block Mapping</option>
						<option value="coveredVillagesAndGPBasedOnBlock@state#district#block" >Covered Villages And Gram Panchayats of a Block</option>
						<option value="subdistrictVillageBlockGpsMapping" >Subdistrict, Village,Block and Gps Mapping</option>
						
						
					</optgroup>
					<optgroup id="deptOrg" label="<spring:message  htmlEscape="true"  code='Department/Organization'></spring:message>">
						<option   value="departmentOrganisation"    >Department/Organization List</option>
						<option   value="orgUnitBasedOnOrgCode">Organization Units of a Department/Organization</option>
						<option   value="designationBasedOnOrgCode">Designations of a Department/Organization </option>
					</optgroup>
         	</form:select>
			 
	  </div>
	  <div class="col-sm-3">
			<span class="errormsg" id="errrptFileName"></span>
			 <form:errors htmlEscape="true" path="rptFileName" cssClass="error"/>
	</div>
 </div>


</div>  
  					                				
<!-- Download Option full Directory end-->
<!-- Download State wise Directory  Started-->
<div id="DSWD" style="Display:none">
	<div class="form-group" >
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<form:select path="entityCode" id="statewise" class="form-control">
			<form:option value="-1"><spring:message code="Label.SELECT" htmlEscape="true"/></form:option>
			<form:options items="${stateList}"	itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
		</form:select>
		
      </div>
      <div class="col-sm-3">
     	 <span class="errormsg" id="errstatewise"></span>
		<form:errors htmlEscape="true" path="entityCode" cssClass="error"/>
      </div>
 	</div>									
	<div class="form-group">
         <label class="col-sm-2 control-label" for="sel1"></label>
         <div class="col-sm-9">
             <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="districtofSpecificState"/>
	             <spring:message	htmlEscape="true" code="Label.ALLDISTRICTSTATE" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="subDistrictofSpecificState"/>
	             <spring:message	htmlEscape="true" code="Label.ALLSUBDISTRICTSTATE" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="villageofSpecificState"/>
	             <spring:message	htmlEscape="true" code="Label.ALLVILLAGESTATE" />
             </label>
         </div>
     </div>									
	<div class="form-group">
         <label class="col-sm-2 control-label" for="sel"></label>
         <div class="col-sm-9">
             <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="priLbSpecificState"/>
	             <spring:message	htmlEscape="true" code="Label.PRISPECIFICSTATE" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="ulbSpecificState"/>
	             <spring:message	htmlEscape="true" code="Label.ULBSPECIFICSTATE" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="tlbSpecificState"/>
	             <spring:message	htmlEscape="true" code="Label.TLBSPECIFICSTATE" />
             </label>
         </div>
        </div>
         <div class="form-group">
         <label class="col-sm-2 control-label" for="sel1"></label>
         <div class="col-sm-9">
             <label class="checkbox-inline col-sm-3" >
	             <form:checkbox path="multiRptFileNames" value="uLBWardforState"/>
	             <spring:message	htmlEscape="true" code="Label.ULBSTATESPECIFICWITHWARD" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="uLBWardforStateWithCov"/>
	             <spring:message	htmlEscape="true" code="Label.ULBSTATESPECIFICWITHWARDWITHCOV" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="priWards"/>
	             <spring:message	htmlEscape="true" code="Label.PRISTATEWARDS" />
             </label>
         </div>
         </div>
		 <div class="form-group">
         <label class="col-sm-2 control-label" for="sel1"></label>
         <div class="col-sm-9">
             <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="LocalbodyMappingtoCensusLandregionCode"/>
	             <spring:message	htmlEscape="true" code="Label.DDLBMAPPINGCENSUSLANDCODE" />
             </label>
              <label class="checkbox-inline col-sm-3">
	             <form:checkbox path="multiRptFileNames" value="blockofspecificState"/>
	             <spring:message	htmlEscape="true" code="Label.ALLBLOCK" />
             </label>
             
              <label class="checkbox-inline col-sm-3">
	               
			<form:checkbox path="multiRptFileNames" value="allBlockStateWithCoveredVillage1"/>
			<spring:message	htmlEscape="true" code="Label.ALLBlOCKSOFSTATEWITHCOVEREDVILLAGE" />
             </label>
             
             
         
														
             
             
             <span class="errormsg" id="errmultiRptFileNames"></span>
			<form:errors htmlEscape="true" path="multiRptFileNames" cssClass="error"/>
         </div>	
         </div>
  </div>										
<!-- Download State wise Directory  end-->
<!-- Download Modification only start-->
<div id="DMO" style="Display:none">
	<div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.FROMDATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
      	<div class="col-sm-4">
      	
      		<div class="input-group date datepicker" id="bfromDate">
      			<form:input path="fromDate" id="fromDate" readonly="true" class="form-control" />
      			<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      		</div>
			
			<form:errors htmlEscape="true" path="fromDate" cssClass="error"/>
			<span class="errormessage" id="errfromDate"></span>
			
      	</div>
    </div>
    
    <div class="form-group">
		<label class="col-sm-3 control-label"><spring:message code="Label.TODATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
      	<div class="col-sm-4">
      	
      		<div class="input-group date datepicker" id="btoDate">
      			<form:input path="toDate" id="toDate" readonly="true" class="form-control" />
      			<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
      		</div>
			
			<form:errors htmlEscape="true" path="toDate" cssClass="error"/>
			<span class="errormessage" id="errtoDate"></span>
			
      	</div>
    </div>
    
    
    <div class="form-group" >
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.DDENTITY" htmlEscape="true"></spring:message> <span class="mandatory">*</span></label>
	  <div class="col-sm-4">
			 <form:select path="rptFileNameMod" id="rptFileNameMod" class="form-control">
         		<form:option value="0">
					<spring:message code="Label.SEL" htmlEscape="true"></spring:message>
				</form:option>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.STATECAPS'></spring:message>">
						<option value="allStateofIndiabyDate" ><spring:message  htmlEscape="true" code='Label.ALLSTATE'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.DISTRICTCAPS'></spring:message>">
						<option value="alldistrictbyDate" ><spring:message  htmlEscape="true" code='Label.ALLDISTRICT'></spring:message></option> 
						<option value="districtofSpecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.ALLDISTRICTSTATE'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.SUBDISTRICTCAPS'></spring:message>">
						<option value="allSubDistrictofIndiabyDate" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICT'></spring:message></option> 
						<option value="subDistrictofSpecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICTSTATE'></spring:message></option>
						<option value="subDistrictofSpecificDistrictbyDate@state#district" ><spring:message  htmlEscape="true" code='Label.ALLSUBDISTRICTDISTRICT'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.VILLAGECAPS'></spring:message>">
						<option value="allVillagesofIndiabyDate" ><spring:message  htmlEscape="true" code='Label.Village.India'></spring:message></option> 
						<option value="villageofSpecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGESTATE'></spring:message></option>
						<option value="villageofSpecificDistrictbyDate@state#district" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGEDISTRICT'></spring:message></option>
						<option value="villageofSpecificSubdistrictbyDate@state#district#subdistrict" ><spring:message  htmlEscape="true" code='Label.ALLVILLAGESUBDISTRICT'></spring:message></option>
					</optgroup>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.LOCALBODY'></spring:message>">
						<option value="priLbSpecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.PRISPECIFICSTATE'></spring:message></option>
						<option value="priLbSpecificDistrictbyDate@state#district" ><spring:message  htmlEscape="true" code='Label.PRISPECIFICDISTRICT'></spring:message></option>
						<option value="ulbSpecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.ULBSPECIFICSTATE'></spring:message></option>
						<option value="ulbSpecificDistrictbyDate@state#district" ><spring:message  htmlEscape="true" code='Label.ULBSPECIFICDISTRICT'></spring:message></option>
						<option value="tlbSpecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.TLBSPECIFICSTATE'></spring:message></option>
						<option value="tlbSpecificDistrictbyDate@state#district" ><spring:message  htmlEscape="true" code='Label.TLBSPECIFICDISTRICT'></spring:message></option>
						 <option value="uLBWardforStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.ULBSTATESPECIFICWITHWARD' ></spring:message> </option>
						<option value="uLBWardforStateWithCovbyDate@state" ><spring:message  htmlEscape="true" code='ULBSTATESPECIFICWITHWARDWITHCOV' ></spring:message> </option>
						
						
						<option value="priWardsbyDate@state" ><spring:message  htmlEscape="true" code='Label.PRISTATEWARDS' ></spring:message> </option>
						<option value="priLocalBodyIndiabyDate" ><spring:message  htmlEscape="true" code='Label.PRIINDIA'></spring:message></option>
						<option value="allTraditionalLBofInidabyDate" ><spring:message  htmlEscape="true" code='Label.TLBINDIA'></spring:message></option>
						
						<option value="urbanLocalBodyIndiabyDate" ><spring:message  htmlEscape="true" code='Label.UrbanINDIA'></spring:message></option> 
					</optgroup>
					
					
					<%-- <optgroup label="<spring:message  htmlEscape="true" code='Label.PARLIAMENTLVSASSEMBLYCONSTITUENCY'></spring:message>">
						<option value="parliamentConstituency@state#parliament" ><spring:message  htmlEscape="true" code='Label.PCWISELOCALBODYMAP'></spring:message></option>
						<option value="assemblyConstituency@state#parliament#assembly" ><spring:message  htmlEscape="true" code='Label.ACWISELOCALBODYMAP'></spring:message></option> 
						
					</optgroup> --%>
					
					<%-- <optgroup label="<spring:message  htmlEscape="true" code='Label.DDLBMAPPINGCENSUSLANDCODE'></spring:message>">
						<option value="LocalbodyMappingtoCensusLandregionCode@state" ><spring:message  htmlEscape="true" code='Label.DDLBMAPPINGCENSUSLANDCODE'></spring:message></option>
					</optgroup> --%>
					<optgroup label="<spring:message  htmlEscape="true" code='Label.BLOCK'></spring:message>">
						<option value="blockofspecificStatebyDate@state" ><spring:message  htmlEscape="true" code='Label.ALLBLOCK'></spring:message></option>
						<option value="allBlockofIndiabyDate" ><spring:message  htmlEscape="true" code='Label.ALLBLOCKINDIA'></spring:message></option> 
					</optgroup>
         	</form:select>
			 
	  </div>
	  <div class="col-sm-3">
			<span class="errormsg" id="errrptFileNameMod"></span>
			 <form:errors htmlEscape="true" path="rptFileNameMod" cssClass="error"/>
	</div>
 </div>


</div>
<!-- Download Modification only end-->
<!--  -->
<div class="form-group" style="Display:none" id="stateLI">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<select id="state" name="entityCodes" class="form-control">
			
		</select>
		<input type="hidden" name="stateName" id="stateName"/>
		<input type="hidden" name="districtName" id="districtName"/>
		<input type="hidden" name="blockName" id="blockName"/>
	 </div>
      <div class="col-sm-3">
     	 <span class="errormsg" id="errstatewise"></span>
		<form:errors htmlEscape="true" path="entityCode" cssClass="error"/>
      </div>
</div>

<div class="form-group" style="Display:none" id="districtLI">
  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
  <div class="col-sm-4">
	<select id="district" name="entityCodes" class="form-control"></select>
  </div>
    <div class="col-sm-3">
		<span class="errormsg" id="errdistrict"></span>
	</div>
 </div>
 
 <div class="form-group" style="Display:none" id="blockLI">
	  <label  class="col-sm-3 control-label" title="Select block" for="sel1"><spring:message code="Select block" text="Select block" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<select id="block" name="entityCodes" class="form-control">
		</select>
	 </div>
      <div class="col-sm-3">
			<span class="errormsg" id="errstate"></span>
	</div>
</div>
 
<div class="form-group" style="Display:none" id="subdistrictLI" >
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<select id="subdistrict" name="entityCodes" class="form-control"></select>
	  </div>
      <div class="col-sm-3">
			<span class="errormsg" id="errsubdistrict"></span>
	</div>
  </div>				                				
<div class="form-group" style="Display:none" id="parliamentLI">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<select id="parliament" name="entityCodes" class="form-control"></select>
	 </div>
      <div class="col-sm-3">
			<span class="errormsg" id="errparliament"></span>
	</div>
  </div>					                				
<div class="form-group" style="Display:none" id="assemblyLI">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTASSEMBLYCONSTITUENCY" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<select id="assembly" name="entityCodes" class="form-control"></select>
	</div>
       <div class="col-sm-3">
			<span class="errormsg" id="errassembly"></span>
	</div>
  </div>
  
  <!--  Added By Sushma Singh 15 oct 2019 -->
  
<div class="form-group"  style="Display:none"   id="allState">
  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.ALLSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
  <div class="col-sm-4">
	<select id="allStateList" name="entityCodes" class="form-control"></select>
  </div>
    <div class="col-sm-3">
		<span class="errormsg" id="errAllStateList"></span>
	</div>
 </div>
<!--  -->
<!-- by rajeev  -->

                 <div class="form-group"  id="stateCenter">
	 <br />
	 
	                <div class="form-group">
						<label class="col-sm-3 control-label" style="padding-top: 4px;
						">Select Level of Department</label>
						 <div class="col-sm-4">
						 <input type="radio" name="state" class="selectState" id="radioState"/>
				            <label><spring:message code="Label.STATELEVEL"></spring:message></label>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="radio" name="state" class="selectState" checked="checked" id="radioCenter"   value="0"  />
							<label ><spring:message code="Label.CENTRELEVEL" text="Center"></spring:message></label> <br/>
							
					 	</div>
					</div>
	 
	<div id="stateList1">
	  <label  class="col-sm-3 control-label" for="sel1"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
	  <div class="col-sm-4">
		<select id="stateList" name="entityCodes" class="form-control selectState" >
			
		</select>
		<input type="hidden" name="stateName" id="stateName"/>
		<input type="hidden" name="districtName" id="districtName"/>
		<input type="hidden" name="blockName" id="blockName"/>
	 </div>
	 </div>
     <div class="col-sm-3">
     	 <span class="errormsg" id="errstateList"></span>
		<form:errors htmlEscape="true" path="entityCode" cssClass="error"/>
      </div>
  </div>	
<div class="form-group" id="organisationLt" >
  <label  class="col-sm-3 control-label"  for="sel1"><spring:message code="Select Organization/Departments " htmlEscape="true"></spring:message><span class="mandatory">*</span></label>
  <div class="col-sm-4">
	<select id="organisationList" name="entityCodes" class="form-control"></select>
	
  </div>
    <div class="col-sm-3">
		<span class="errormsg" id="errorganisation"></span>
	</div>
 </div>

<div class="box-header subheading">
	<h4 class="box-title"><spring:message code="Label.DDOPTION" htmlEscape="true"></spring:message>	</h4>
</div><!-- /.box-header -->
<!-- Download Type Started-->
<div class="form-group">
	<label  class="col-sm-1 control-label" for="sel1"></label>
	<div class="col-sm-6">
		  <label class="radio-inline">
		   		<form:radiobutton path="downloadType" value="${downloadPDF}" checked="checked" />
		   		<spring:message code="Label.PDF" htmlEscape="true"/>
		  </label>
		  <label class="radio-inline">
		   		<form:radiobutton path="downloadType" value="${downloadHTM}" checked="checked" />
		   		<spring:message code="Label.HTM" htmlEscape="true"/>
		  </label>
		  <label class="radio-inline">
		   		<form:radiobutton path="downloadType" value="${downloadXLS}" checked="checked" />
		   		<spring:message code="Label.XLS" htmlEscape="true"/>
		  </label>
		  <label class="radio-inline">
		   		<form:radiobutton path="downloadType" value="${downloadCSV}" checked="checked" />
		   		<spring:message code="Label.CSV" htmlEscape="true"/>
		  </label>
		  <label class="radio-inline">
		   		<form:radiobutton path="downloadType" value="${downloadODT}" checked="checked" />
		   		<spring:message code="Label.ODT" htmlEscape="true"/>
		  </label>
	</div>
</div>
<!-- Download Type end-->
<div class="box-header subheading">
	<h4 class="box-title"><spring:message code="Label.CAPTCHA" text="Captcha" htmlEscape="true"></spring:message>	</h4>
</div><!-- /.box-header -->									
<div class="form-group">
		  <label  class="col-sm-3 control-label" for="sel1"><spring:message htmlEscape="true" code="Label.ENTERTEXTASSHOWNINTHISIMAGE" ></spring:message><span class="mandatory">*</span></label>
		  <div class="col-sm-6">
		  	<img src="captchaImage" id="captchaImageId" alt="Captcha Image" border="0"/>
		  </div>
</div>
<div class="form-group">
		  <label  class="col-sm-3 control-label" for="sel1">
		 </label>
		  <div class="col-sm-6">
		  	<form:input path="captchaAnswer" autocomplete="off"  style="width:250px" />
		  	 <a href="#" onclick="captchaReferesh();" ><i class="fa fa-retweet fa-2x" aria-hidden="true"></i></a>
		  <form:errors path="captchaAnswer" cssStyle="color:red"/>
			<c:if test="${serverFlag ne true}">
			<span class="errormsg" id="errcaptchaAnswer"></span>
			</c:if>
		  </div>
</div>
<div class="box-footer">
     <div class="col-sm-offset-2 col-sm-10">
       <div class="pull-right">
       		<button type="button" class="btn btn-success" id="btnGenreateReport"><i class="fa fa-floppy-o"></i><spring:message htmlEscape="true"  code="Label.GENERATEREPORT"/></button>
			<button type="button" class="btn btn-info"  id="btnActionClear"><spring:message htmlEscape="true"  code="Button.CLEAR"/></button>
       		<button type="button"  class="btn btn-danger" id="btnActionClose"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true" code="Button.CLOSE"/></button>
        </div>
     </div>   
  </div>					
				
				</form:form>
			</div>
		</section>
	</div>
		<!-- Page Content end -->
</section>
<!-- Main Form Stylings end -->
<c:if test="${serverFlag eq true}">
<script>
loadSelectedInformation();
</script>
</c:if>
</body>
</html>


<script>
	$('document').ready(function(){
		
		$("#stateList1").hide();
		$("#organisationLt").hide();
		var stateCode = $("#stateList option:selected").val();
		$('#rptFileName').on('change',function(){
			  removeAllOptions('stateList');
			  removeAllOptions('organisationList');
			var elm=$(this);
			var opt=$(':selected',elm);
			var optgrpOptionVal=opt.closest('optgroup option:selected').val();
			/* if(opt.closest('optgroup').attr('id') == "deptOrg" ){
				$("#stateCenter").show();
			} */
			  
			if(optgrpOptionVal == "designationBasedOnOrgCode" ){
				$("#stateCenter").show();
				$("#organisationLt").show();
				 var center =$("input:checked + label").text();
		 		 
				 if(center == "Central Level"){
					 callOrgList();
			 			}
			}
			else if(optgrpOptionVal == "departmentOrganisation"){
				$("#stateCenter").show();
				
				$("#organisationLt").hide();
				
			}
			else if(optgrpOptionVal =="orgUnitBasedOnOrgCode"){
				$("#stateCenter").show();
				$("#organisationLt").show();
				var center =$("input:checked + label").text();
				 if(center == "Central Level"){
					 callOrgList();
			 			}
			}
			else{
				$("#stateCenter").hide();
				$("#organisationLt").hide();
			}
			/* if(stateCode >=0){
				$("#organisationLt").show();
			}else{
				$("#organisationLt").hide();
			} */
		});
		
	});
		$('#radioState').on('click',function(){
			$("#stateList1").show();
			fillStateListforOption();
			
		});
		$('#radioCenter').on('click',function(){
			$("#stateList1").hide();
		});
		
		
</script>
<script>
$('document').ready(function(){
	
 	$('.selectState').on('change',function(){
 		 var center =$("input:checked + label").text();
 		var optgrpOptionVal=$('#rptFileName').find('optgroup option:selected').val();
 		if(optgrpOptionVal == "departmentOrganisation"){
 			if(center == "Central Level"){
 	 			
 	 			$('#stateList').get(0).selectedIndex = 0;
 			}
 		}
 		
 		else if(optgrpOptionVal != "departmentOrganisation"){
 		var stateCode;
        
 		 
 		 //alert(center);
 		 if(center == "Central Level"){
 			
 			$('#stateList').get(0).selectedIndex = 0;
 			$('#organisationList').get(0).selectedIndex = 0;
 			stateCode =0;
 		 }
 		 if(center == "State Level"){
 			$('#organisationList').get(0).selectedIndex = 0;
 			 stateCode = $("#stateList option:selected").val();
 			 if(stateCode== 0){
 				stateCode =-1;
 				removeAllOptions('organisationList');
 			 }
  		 }
 		 if(center == "State Level" && stateCode >0 || center == "Central Level"){
 			removeAllOptions('organisationList');
 		lgdDwrStateService.getOrganisationList(stateCode, {
 			
 				callback : function(result) {
 					removeAllOptions('organisationList');
 					var options = $("#organisationList");
 					var option = $("<option/>");
 					$(option).val("0").text("<spring:message code='Label.SEL' htmlEscape='true'/>");
 					options.append(option);
 					$.each(result, function(key, obj) {
 						var option = $("<option/>");
 						(option).val(obj.orgCode).text(obj.orgName);
 						options.append(option);
 						
 					}); 
 				
 				},
 				async : true
 			});		
 		 }
 		}
 		
 	});
	});
	
	
	


</script>
