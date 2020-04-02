
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../common/dwr.jsp"%>
<%@include file="mapParliamentJs.jsp"%>

<script type="text/javascript" language="javascript">
		
	$(document).ready(function() {
		var statecode = document.getElementById('hdnState').value;
		var temp ='';
		var val = '';
		var selObj ='';
		var  i='';
		if (statecode == 34)
		{
		selObj = document.getElementById('ddLgdLBType');
		for ( i = 0; i < selObj.options.length; i++) {
			
			val= selObj.options[i].value;
			temp = val.split(":");
			val = temp[0];
			if(val==2)
				{
				temp =selObj.options[i].value ;
				selObj.options[i].value = "1:D"+temp.substring(3,temp.length);
				}
			
				
		}
		}
		
		});	
	
	function open_win() {
		
		var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
		//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
	} 
	dwr.engine.setActiveReverseAjax(true);

	 if ( window.addEventListener ) { 
	     window.addEventListener( "load",hideAll, false );
	  }
	  else 
	     if ( window.attachEvent ) { 
	        window.attachEvent( "onload", hideAll );
	  } else 
	        if ( window.onLoad ) {
	           window.onload = hideAll;
	  }	
	</script>
</head>


<body oncontextmenu="return false" onkeypress="disableCtrlKeyCombination(event);" onkeydown="disableCtrlKeyCombination(event);">

<section class="content">
	<div class="row">
        <section class="col-lg-12 ">
	        <div class="box ">
        		<div class="box-header with-border">
               		<h3 class="box-title"><spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message></h3>
               </div>
               
            <div class="box-body">
            	<div class="box-header subheading">
                        	<h4><spring:message htmlEscape="true" code="Label.MOPTLG"></spring:message></h4>
		       </div>
		        <form:form class="form-horizontal" action="MapofParliament.htm" method="POST" id="form1" name="form1" commandName="localGovtBodyForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="MapofParliament.htm"/>" />
				<form:hidden path="lbFullMap" />
				<form:hidden path="lbPartMap" />
				<form:hidden path="villageMap" />
				<form:hidden path="wardMap" />
				<form:hidden path="deleteMap" />
				<form:hidden path="ccCodeMap" />
				<form:hidden path="districtCode" />
				<form:hidden path="subDistrictCode" />
				<form:hidden path="villageCode" />
				
				<input type="hidden" name="listformat" id="listformat" value="" />
				<input type="hidden" name="selListFormat" id="selListFormat" value="" />
				<input type='hidden' name="hdnType" id="hdnType" value="${typeCode}" />
				<input type='hidden' name="hdnIntermediatePanchayat" id="hdnIntermediatePanchayat" value="${hdnIntermediatePanchayat}" />
				<input type='hidden' name="hdnDistrictPanchayat" id="hdnDistrictPanchayat" value="${hdnDistrictPanchayat}" />
				<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
				<input type="hidden" name="flag1" id="flag1" value="0" />
				<input type="hidden" name="choosenlb" id="choosenlb" value="1" />
				<input type="hidden" name="ward_number" id="ward_number" value="1" />
				<input type="hidden" name="lbtiertype" id="lbtiertype" value="${Tier}" />
				<input type="hidden" name="partULb" id="partULb" value="0" />

				<div id="cat">
					<div id='district'>
                <div class="form-group">
               		<label class="col-sm-3 control-label">
                  	  	<spring:message htmlEscape="true" code="Label.SELECTPARLIAMENTCONSTITUENCY"></spring:message><span class="mandatory">*</span>
				   </label>
					  <div class="col-sm-6">
						<form:select path="contributedParliament" class="form-control" id="ddStateParliamnetSource" onchange="getAssemblyList(this);">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECTPC"></spring:message></form:option>
							<form:options items="${distList}" itemValue="id.pcCode" itemLabel="pcNameEnglish"></form:options>
						</form:select> <br />
						<span class="errormsg" id="errorddStateParliamnetSource"></span>
						<form:errors htmlEscape="true" path="contributedParliament" class="errormsg"></form:errors>
					</div> 						
				</div>

			   <div class="form-group">
                 	<label class="col-sm-3 control-label"><spring:message htmlEscape="true" code="Label.SELECTASSEMBLYCONSTITUENCY"></spring:message></label>
                  		<div class="col-sm-6">
                   		<form:select path="contributedAssembly" onchange="loadData(this.value)" class="form-control" id="ddassemblySource" >
							<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
						</form:select> <br />
						<span class="errormsg" id="errorddassemblySource">
                  	</div>
              </div>
              
              <div id="divFirstPanel">
              	<div class="box-header subheading">
                   <h4><spring:message htmlEscape="true" text="COVERED LAND REGION/LOCAL BODY" ></spring:message></h4>
		        </div>
		      <input type="hidden" class="btn" value="${stateCode}" id="hdnState" name="hdnState" />
              				
              <div class="form-group">
                 <label class="col-sm-3 control-label"><spring:message htmlEscape="true" text="Select Landregion Type/Localbody Type"></spring:message><span class="mandatory">*</span></label>
                  	<div class="col-sm-6">
                  		<form:select path="rurallbTypeName" id="ddLgdLBType" class="form-control" onchange="hideShowDivbyType(this.value)" onfocus="validateOnFocus('ddLgdLBType');">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECTLOCALBODYTYPE"></spring:message></form:option>
								<c:forEach var="localBodyTypelist" varStatus="var" items="${localbodyTypelist}">
									<form:option id="typeCode" value="${localBodyTypelist.localBodyTypeCode}:${localBodyTypelist.level}:${localBodyTypelist.category}:${localBodyTypelist.localBodyTypeName}">${localBodyTypelist.nomenclatureEnglish}</form:option>
								</c:forEach>
						</form:select>
						<span class="errormsg" id="errorddLgdLBType"></span>
						<form:errors htmlEscape="true" path="rurallbTypeName" class="errormsg"></form:errors>
                   	</div>
              </div>
              
              <div id="divSpecificStateforward">
              	
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
						<span class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error"></span>
						<span><form:errors htmlEscape="true" path="lgd_LBWardCArea" class="errormsg"></form:errors></span>
							<label id='lbl_header'><spring:message htmlEscape="true" code="Label.AVAILEPANCHAYATLIST"></spring:message></label>${localGovtBodyForm.districtPanchayatName}<br/>
								<form:select path="lgd_LBSubDistrictSourceLatDCAUmapped" class="form-control" id="ddLgdWardSubDistrictUListSource" multiple="true"></form:select><br /><br />
						</div>
						
						<div class="ms_buttons col-sm-2"><br>
							<button type="button" onclick="moveElementURBAN('FULL');" class="btn btn-primary btn-xs btn-block">Whole  <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" id="btnremoveOneULB" name="Submit4" onclick="moveElementURBAN('BACK')" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
							<button type="button" onclick="moveElementURBAN('PART');" class="btn btn-primary btn-xs btn-block"> Part <i class="fa fa-angle-right" aria-hidden="true"></i></button>
						</div>
						
						<div class="ms_selection col-sm-5">
							<span class="errormsg" id="errorddLgdWardSubDistrictUListDest"></span>
							<span><form:errors htmlEscape="true" path="lgd_LBSubDistrictDestLatDCA" class="errormsg"></form:errors> </span>
						  	<label id='lbl_header_contri'><spring:message htmlEscape="true" code="Label.CONTRIBUTPANCHAYATLIST"></spring:message></label><br/>
								<form:select name="select6" id="ddLgdWardSubDistrictUListDest" multiple="true" path="lgd_LBWardCArea" class="form-control"></form:select><br />
								<input type="button" class="btn btn-info" value="<spring:message htmlEscape="true"  code="Button.GETWARDLIST"/>" onclick="getWardList();" />				
                        </div>
                    </div>
                    
               
				<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message htmlEscape="true" code="Label.AVAILEWARDLIST"></spring:message></label><br/>
						<form:select path="lgd_LBSubDistrictSourceLatDCA" class="form-control" id="ddLgdLBwardSourceLatDCA" multiple="true"></form:select><br /> <br />
					</div>
					<div class="ms_buttons col-sm-2"><br /><br />
						<button type="button" onclick="moveElementWard('FULL');" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
						<button type="button" id="btnremoveOneULB"  name="Submit4" onclick="moveElementWard('BACK')" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
					</div>
					<div class="ms_selection col-sm-5">
					  	<label><spring:message htmlEscape="true" code="Label.CONTRIBUTWARDLIST"></spring:message></label><br/>
						<form:select name="select6" id="ddLgdLBVillageDestLatDCA" multiple="true" path="lgd_LBwardDestLatDCA" class="form-control"></form:select>
							<span class="errormsg" id="errorddLgdLBVillageDestLatDCA"></span><br />
							<label> 
								<input type="button" id="chooseMoreBtn1" onclick='addCoverdArea();' name="chooseMoreBtn1" class="btn btn-info" value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span class="mandatory">*</span>
							</label>
                    </div>
                  </div>
                </div>
				
				<div id="divRuralDistrictPanchayat">
				
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label>
<%-- 								<spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureDist} --%>
								<spring:message htmlEscape="true" text="Available Districts"></spring:message>
							</label><br/>
							<form:select path="lgd_LBDistPSourceList" class="form-control" id="ddLgdDispandist" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" multiple="true"></form:select><br /> <br />
								<span class="errormsg" id="ddLgdLBSubDistrictDestLatDCA_error"> </span>&nbsp;<span>
								<form:errors htmlEscape="true" path="lgd_LBSubDistrictDestLatDCA" class="errormsg"></form:errors> </span>
						</div>
						<div class="ms_buttons col-sm-2"><br /><br />
							<button type="button" onclick="moveElementDP('FULL');" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" id="btnremoveOneULB" name="Submit4" onclick="moveElementDP('BACK')" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
						</div>
						<div class="ms_selection col-sm-5">
							<span class="errormsg" id="ddLgdLBDistCAreaDestL_error"></span>
							<span><form:errors htmlEscape="true" path="lgd_LBDistCAreaforintermediate" class="errormsg"></form:errors></span>
							<label>
<%-- 								<spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureDist}  --%>
						  		<spring:message htmlEscape="true" text="Contributing Districts"></spring:message>
						  	</label><br/>
							<form:select id="ddLgdLBDistPList" multiple="true" value="D" path="lgd_LBDistPDestList" class="form-control"></form:select><br />
							<input type="button" id="chooseMoreBtn2" onclick='addCoverdArea();' name="chooseMoreBtn2" class="btn btn-info" value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span class="mandatory">*</span>
						</div>
					</div>

				</div>
				
				<div id="divRuralDistrictPanchayatforinter">
					<span class="errormsg" id="ddLgdLBDistPDestList_error"><spring:message htmlEscape="true" code="error.blank.DISTRICTP"></spring:message></span>
					<span><form:errors htmlEscape="true" path="lgd_LBDistPDestList" class="errormsg"></form:errors></span>
				
					<div class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<label><spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureDist}<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label><br/>
						<form:select path="lgd_LBDistPSourceList" class="form-control" id="ddLgdLBDistPSourceList" items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" multiple="true"></form:select>
					</div>
					<div class="ms_buttons col-sm-2"><br /><br />
						<button type="button" onclick="addItemPC('ddLgdLBDistPDestList','ddLgdLBDistPSourceList','FULL',true);" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
						<button type="button" id="btnremoveOneULB" name="Submit4" onclick="removeItem('ddLgdLBDistPDestList','ddLgdLBDistPSourceList',true)" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
						<button type="button" onclick="removeAll('ddLgdLBDistPDestList', 'ddLgdLBDistPSourceList', true)" class="btn btn-primary btn-xs btn-block">Reset </button>
						<button type="button" onclick="addItemPC('ddLgdLBDistPDestList','ddLgdLBDistPSourceList', 'PART',true);" class="btn btn-primary btn-xs btn-block"> Part <i class="fa fa-angle-right" aria-hidden="true"></i></button>
					</div>
					<div class="ms_selection col-sm-5">
						<label><spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureDist} 
					  	<spring:message htmlEscape="true" code="Label.LIST"></spring:message></label><br/>
						<form:select id="ddLgdLBDistPDestList" multiple="true" path="lgd_LBDistPDestList" class="form-control"></form:select><br />
						<span class="errormsg" id="ddLgdLBDistCAreaDestL_error"> </span>
						<span><form:errors htmlEscape="true" path="lgd_LBDistPDestList" class="errormsg"></form:errors></span>
					</div>
                  </div>
				</div>
				
				<div id="divRuralIntermediatePanchayat">
				  <div class="form-group">
                 	<label class="col-sm-3 control-label">
<%--                  		<spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureDist} --%>
                 		<spring:message htmlEscape="true" code="Select District"></spring:message>
                 	</label>
                  		<div class="col-sm-6">
	                   		<form:select path="lgd_LBDistListAtInterCA" onchange="getInterPanchyatList(this,true,'ddLgdLBInterPSourceList');" class="form-control" id="ddLgdLBDistListAtInterCA">
								<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
								<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
							</form:select>
							<form:errors htmlEscape="true" path="lgd_LBDistListAtInterCA" class="errormsg"></form:errors>
                  		</div>
                	</div>
	                <span class="errormsg" id="ddLgdLBInterPDestList_error"> </span>
					<span><form:errors htmlEscape="true" path="lgd_LBInterPDestList" class="errormsg"></form:errors> </span>
					
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label>
<%-- 								<spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureInter} --%>
								<spring:message htmlEscape="true" code="Available Subdistricts"></spring:message>
							</label><br/>
							<form:select path="lgd_LBInterPSourceList" class="form-control" id="ddLgdLBInterPSourceList" multiple="true"></form:select>
						</div>
						<div class="ms_buttons col-sm-2"><br /><br />
							<button type="button"onclick="moveElementIP('FULL');" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" id="btnremoveOneULB" name="Submit4" onclick="moveElementIP('BACK');" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
						</div>
						<div class="ms_selection col-sm-5">
							<label>
<%-- 								<spring:message htmlEscape="true" code="Label.CONTRIBUTE"></spring:message>${localGovtBodyForm.lgd_LBNomenclatureInter} --%>
								<spring:message htmlEscape="true" text="Contributing Subdistricts"></spring:message>
							</label><br/>
							<form:select name="select6" id="ddLgdLBInterPDestList" multiple="true" path="lgd_LBInterPDestList" value="I" class="form-control"></form:select>
							<input type="button" id="chooseMoreBtn3" onclick='addCoverdArea();' name="chooseMoreBtn3" class="btn btn-info" value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span class="mandatory">*</span>
						</div>
	                  </div>
				
				</div>
				
				
				<div id="divRuralVillagePanchayat">
					
				<div id="selectDistrictForVillage" class="form-group">
                 	<label class="col-sm-3 control-label"> 
                 		<spring:message htmlEscape="true" text="Select District"></spring:message>
                 	</label>
					<div class="col-sm-6">
						<form:select path="lgd_LBDistListAtVillageCA" class="form-control" id="ddLgdLBDistListAtVillageCA" onchange="getInterPanchyatList(this,false,'ddLgdLBInterListAtVillageCA');">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message></form:option>
							<form:options items="${districtPanchayatList}" itemLabel="localBodyNameEnglish" itemValue="localBodyCode" />
						</form:select>
						<form:errors htmlEscape="true"	path="lgd_LBDistListAtVillageCA" class="errormsg"></form:errors><br/>
						<span class="errormsg" id="errorddLgdLBDistListAtVillageCA"></span>
					</div>
				</div>

<%--                 <c:if test="${Tier eq 3}"> --%>
				<div id="selectSubDistrictForVillage" class="form-group">
					<label class="col-sm-3 control-label"> 
						<spring:message htmlEscape="true" text="Select Subdistrict"></spring:message>
					</label>
						<div class="col-sm-6">
						<form:select path="lgd_LBInterListAtVillageCA" class="form-control" id="ddLgdLBInterListAtVillageCA" onchange="getVillagePanchyatList(this,true,'ddLgdLBVillageSourceAtVillageCA');">
							<form:option value=""><spring:message htmlEscape="true" code="Label.SELECTLOCALBODY"></spring:message>
							</form:option>
						</form:select> 
						<form:errors htmlEscape="true" path="lgd_LBInterListAtVillageCA" class="errormsg"></form:errors><br/>
						<span class="errormsg" id="errorddLgdLBInterListAtVillageCA"></span>
					</div>
				</div>
<%-- 				</c:if> --%>
				
				<div id="availableVillageListDiv" class="ms_container row" style="margin-left: 10px;">
					<div class="ms_selectable col-sm-5 form-group">
						<span class="errormsg" id="ddLgdLBVillageDestAtVillageCA_error"> </span>
						<span><form:errors htmlEscape="true" path="lgd_LBVillageDestAtVillageCA" class="errormsg"></form:errors></span>
							<label>
<%-- 								<spring:message htmlEscape="true" code="Label.AVAILABLE"></spring:message> --%>
								<spring:message htmlEscape="true" text="Available Villages"></spring:message>
							</label><br/>
							<form:select path="lgd_LBVillageSourceAtVillageCA" class="form-control" id="ddLgdLBVillageSourceAtVillageCA" multiple="true"></form:select> <br /> <br />
					</div>
					<div class="ms_buttons col-sm-2"><br /><br />
						<button type="button" id="moveFullVillage" onclick="moveElementVP('FULL');" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
						<button type="button" id="btnremoveOneULB" name="Submit4" onclick="moveElementVP('BACK');" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
<!-- 						<button type="button" id="movePartVillage" onclick="moveElementVP('PART');" class="btn btn-primary btn-xs btn-block">PART <i class="fa fa-angle-right" aria-hidden="true"></i> </button> -->
					</div>
					<div class="ms_selection col-sm-5">
						<span class="errormsg"	id="ddLgdLBSubDistrictDestLatDCA_error"> </span>&nbsp;<span>
<%-- 						<form:errors htmlEscape="true" path="lgd_LBInterPDestListforvillage" class="errormsg"></form:errors> </span> --%>
						<label>Contributing Villages <c:out value=" ${localGovtBodyForm.lgd_LBNomenclatureVillage}"></c:out></label><br/>
						<form:select name="select6" id="ddLgdLBVillageDestAtVillageCA" multiple="true" path="lgd_LBInterPDestListforvillage" class="form-control"></form:select>
						<span class="errormsg"	id="errorddLgdLBVillageDestAtVillageCA"><br />
<%-- 						<input type="button" value="<spring:message htmlEscape="true"  code="Button.GETVILLAGEL"/>" class="btn btn-info" onclick="getVillageList();" /> --%>
						<div class="ms_selection col-sm-5">
<%-- 							<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message></label><br/> --%>
<%-- 							<form:select name="select6" id="ddLgdLBVillageDestLatDCAforvillage" multiple="true" path="lgd_LBSubDistrictDestLatDCAforvillage" class="form-control"></form:select><br />  --%>
							<input type="button" id="chooseMoreBtn4" onclick='addCoverdArea();' name="chooseMoreBtn4" class="btn btn-info" value="Add 
								<spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" />
<!-- 							<span class="errormsg">*</span> -->
						</div>
					</div>
                  </div>
				
				<%-- <div id="availableVillageListDiv">
					<div class="ms_container row" style="margin-left: 10px;">
						<div class="ms_selectable col-sm-5 form-group">
							<label><spring:message htmlEscape="true" code="Label.AVAILVILLAGELIST"></spring:message></label><br/>
							<form:select path="lgd_LBSubDistrictSourceLatDCA" class="form-control" id="ddLgdLBVillageSourceLatDCAforvillage" multiple="true"></form:select><br /> <br />
						</div>
						<div class="ms_buttons col-sm-2"><br /><br />
							<button type="button" onclick="moveElementVillage('FULL');" class="btn btn-primary btn-xs btn-block">Whole <i class="fa fa-angle-double-right" aria-hidden="true"></i></button>
							<button type="button" id="btnremoveOneULB" name="Submit4" onclick="moveElementVillage('BACK');" class="btn btn-primary btn-xs btn-block"> Back <i class="fa fa-angle-double-left" aria-hidden="true"></i></button>
						</div>
						<div class="ms_selection col-sm-5">
							<label><spring:message htmlEscape="true" code="Label.CONTRIBUTVILLAGELIST"></spring:message></label><br/>
							<form:select name="select6" id="ddLgdLBVillageDestLatDCAforvillage" multiple="true" path="lgd_LBSubDistrictDestLatDCAforvillage" class="form-control"></form:select><br /> 
								<input type="button" id="chooseMoreBtn4" onclick='addCoverdArea();' name="chooseMoreBtn4" class="btn btn-info" value="Add <spring:message htmlEscape="true"  code="Label.COVEREDAREA"></spring:message>" /><span class="errormsg">*</span>
						</div>
					</div>
				</div> --%>
				
			</div>
			
			
				<table id="dynamicTbl" path="newAddLocalBody" width="100%" class="table table-bordered table-hover">
				  <thead>
					<tr>
						<th width="5%">S.No.</th>
						<th width="30%">Entity Name</th>
						<th width="5%">Coverage Type</th>
						<th width="40%">Entity Hierarchy Details</th>
						<th width="10%">Delete</th>
					</tr>
				</thead>
			  </table>
				
				</div>
				<div class="box-footer">
                     <div class="col-sm-offset-2 col-sm-10">
                       <div class="pull-right">
	                        <button type="button" id="Submit" name="Submit" class="btn btn-success" onclick="callActionUrl();"><i class="fa fa-floppy-o"></i> <spring:message htmlEscape="true"  code="Button.SAVE"></spring:message></button>
							<button type="button" name="Submit2" class="btn btn-warning" onclick="javascript:location.href='map_constitutionBody.htm?<csrf:token uri="map_constitutionBody.htm"/>'"> <spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message></button>
							<button type="button" name="Submit6" class="btn btn-danger" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"><i class="fa fa-times-circle"></i> <spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message></button>	
                        
                       </div>
                    </div>   
              	</div>
			</div>
		</div>				
          </form:form>
          <script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
		</div>
	</div>
</section>
</div>
</section>	


</body>
</html>

