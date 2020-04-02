<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/hierarchy-admin-department.js'></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery.treeview/css/jquery.treeview.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/restructureDeparmentLevel.css">
<%@include file="../common/taglib_includes.jsp"%>
<%@include file="../reports/consolidateReportTooltip.jsp"%>

<c:set var="landRegionSlc" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.LAND_REGION_SLC.toString()%>"></c:set>
<c:set var="blockEqCode" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.BLOCK_EQUL_CODE.toString()%>"></c:set>
<c:set var="blockCode" value="<%=in.nic.pes.lgd.constant.DepartmentConstent.BLOCK_CODE.toString()%>"></c:set>
<c:set var="deptOrgOperationalAt" value="Label.SELLEVELADDOFFTYPE"></c:set>
<c:set var="formTitle" value="Label.ExtendDeptSetup"></c:set>
<c:set var="selectCreteria" value="Label.SELDEPT"></c:set>
<c:set var="extendHierarchy" value="Label.ExtendDeptHierarchy"></c:set>
<c:set var="extendEntityButton" value="Button.ExtendDept"></c:set>
<c:set var="extendEntity" value="Label.Department"></c:set>



<c:if test="${isOrganizationFlow}">
		<c:set var="formTitle" value="Label.ExtendOrgSetup"></c:set>
		<c:set var="deptOrgNameEng" value="Label.ORGNAMEINENG"></c:set>
		<c:set var="deptOrgNameLocal" value="Label.ORGNAMEINLOCAL"></c:set>
		<c:set var="deptOrgNameShort" value="Label.SHORTORGNAME"></c:set>
		<c:set var="deptOrgOperationalAt" value="Label.ORGOPERATIONALAT"></c:set>
		<c:set var="selectCreteria" value="Label.SELORG"></c:set>
		<c:set var="extendHierarchy" value="Label.ExtendOrgHierarchy"></c:set>
		<c:set var="extendEntityButton" value="Button.ExtendOrg"></c:set>
		<c:set var="extendEntity" value="Label.Organization"></c:set>
</c:if>

<script type='text/javascript'>
	var isTop = true;
	var isHierarchy = true;
	var treeTop = 0;
	var treeTopId = 0;
	var newTop = false;
	var midNode = false;
	var errorFlag = false;
	var stateWiseAdminUnits = null;
	var existAdminUnit = "";
	var existAdminUnitObj = null;
	var resetFlag = true;
	var childAdminCode=0;
	var existOfficeList=[];
	var childLevelOne="0";
	var hirParentToChild="@";
	var newAdminUnitlist="";
	var arrParentOrgLocatedLevelCode=[];
	var isButton=true;
	var childData="";
	var isAddIteminHierarchy=false;
	var allChildInArray=[];
//	var currentListData="";
//	var ParentLinkCode=1;
//	var linkCode=0;
	
	/* var parentList=[];
	var ExistLvlFlag=false; */
	/* var getDepartmentLevel = function (elementNode,olc){
	 var isCenterFlag = 'F';
	 lgdDwrOrganizationService.getOrg_located_at_levelsByOrgCode(parseInt(olc), isCenterFlag,{
	 callback : function(result){
	
	 dwr.util.addOptions(elementNode, result, 'adminunitlevelcode', 'unitlevelnameenglish');
	
	 },
	 async : true
	 });	
	 }; 
	 */

	var getDeptSetup = function() {
		var dept = $("#deptId").val();
		if(dept==null || dept==""){
			$("#cAlert").html("Please <spring:message htmlEscape="true" code="${selectCreteria}"></spring:message>");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="${formTitle}"></spring:message>',
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}
			else {
				var form = document.getElementById('deptform');
				$("#btnGet").attr("disabled", "disabled");
				form.submit();
			}
		
	};

	checkMidNode = function(parentAdminCode) {
		var oneFlag = false;
		var thirdFlag = false;
		var midnewNodeOne = false;
		var midnewNodeTwo = false;
		for ( var i in existAdminUnitObj) {
			if (i != undefined) {
				if (stateWiseAdminUnits[i] == 1) {
					oneFlag = true;
					if (stateWiseAdminUnits[i] == existAdminUnitObj[i]) {
						midnewNodeOne = true;
					}
				}
				if (stateWiseAdminUnits[i] == 3) {
					thirdFlag = true;
					if (stateWiseAdminUnits[i] == existAdminUnitObj[i]) {
						midnewNodeTwo = true;
					}
				}
			}

		}
		//alert(parentAdminCode);
		if (/*existAdminUnit.indexOf(parentAdminCode + ":") != -1 && existAdminUnit.indexOf((parseInt(parentAdminCode) + 2) + ":") != -1 && */oneFlag && thirdFlag && parentAdminCode == 2) {
			if (midnewNodeOne && midnewNodeTwo && document.getElementById(("ul" + parentAdminCode)) != null) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}

	};

	addIteminHierarchy = function(parentId) {
		
		//alert(childObj.value);
		if (childData == null || childData == "") {
			$("#cAlert").html("Please select Child Unit Level");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="${formTitle}"></spring:message>',
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
			
		} else {
			var rowValue = childData.split("@");
			var adminUnitCodes = document.getElementById('adminUnitCodes');
			var slc = parseInt(rowValue[2]);
		    adminUnitCodes.value =newAdminUnitlist+","+ rowValue[0]; 
			//var parent = ParentLinkCode;
			if (parent == -1) {
				parent = rowValue[3];
				if (true) {
					newTop = true;
					resetChildListbyadminUnitCode(rowValue[0]);
				} else {
					errorFlag = true;
				}
			}
			if (errorFlag == false) {
		/* 		addCheckBoxofNewLevel(rowValue[0], rowValue[1]); */
				midNode = checkMidNode(rowValue[3]);

				var optgroup = null;
				if (slc == 0)
					optgroup = document.getElementById('parentLRUnitLevel');
				else
					optgroup = document.getElementById('parentAdUnitLevel');

				var opt = document.createElement('OPTION');
				opt.textContent = rowValue[1];
				opt.value = rowValue[0];
				optgroup.appendChild(opt);
				//pushitemintoList(parent,rowValue[0]);
				isAddIteminHierarchy=true;
				reBuildHierarchy(rowValue[0], rowValue[1], rowValue[2], (parentId+","+rowValue[0]),rowValue[4],null);
				
				removeSelectedValueinList();
				//code = parseInt(rowValue[0]);
				//existAdminUnitObj[code] = parseInt(parent); //  .push({code:parent});
				//getChildofExistingParent(parent, rowValue[3]);
			} else {
				$("#cAlert").html("you can add only one parent");
				$("#cAlert").dialog({
					title : '<spring:message htmlEscape="true" code="${formTitle}"></spring:message>',
					resizable : false,
					height : 200,
					modal : true,
					buttons : {
						Ok : function() {
							$(this).dialog("close");
							return false;
						}
					}
				});
				errorFlag = false;
			}

		}
	};

	
	
	
	
	
	reBuildHierarchy = function(adminLevelId, name, slc, parentAdminCode,localBodyLevel,orgLocatedLevelCode) {
		if(parentAdminCode=="1")
			hirParentToChild+=parentAdminCode+","+adminLevelId+"@";
		else	
			hirParentToChild+=parentAdminCode+"@";
		var adminLevelValue="";
		 if(parentAdminCode!="1"){
			 adminLevelValue=parentAdminCode;
			 
		var arr=parentAdminCode.split(",");
		parentAdminCode="";
		for(var i=0;i<arr.length-1;i++){
			parentAdminCode+=arr[i]+",";
		}
		parentAdminCode=parentAdminCode.slice(0,parentAdminCode.length-1);
		 }
		else{
			parentAdminCode="0";
			adminLevelValue="1"
		}
		 
		 
		 
		 
		childAdminCode=stateWiseAdminUnits[adminLevelValue];
		existAdminUnit = existAdminUnit + adminLevelValue + ":" + parentAdminCode + ",";
		
		var isChildNoteExist=checkIsChildAlreadyExist(adminLevelValue);
		
		if(isChildNoteExist==false){
		
		if (newTop) {
			addNewTopinList(adminLevelValue, name, parentAdminCode);

		} else if (midNode) {
			addMiddleNodeinList(adminLevelValue, name, parentAdminCode);
		} else if (isTop) {
			var div = document.getElementById("base");
			var ul = document.createElement("ul");
			ul.setAttribute('id', "ul" + parentAdminCode);
			var li = document.createElement("li");

			var btn=createNodeNameAsButton(name,adminLevelValue,adminLevelId,localBodyLevel);

			li.appendChild(btn);
			li.setAttribute("id", adminLevelValue);
			ul.appendChild(li);
			div.appendChild(ul);
			isTop = false;
			treeTop = parentAdminCode;
			treeTopId = adminLevelValue;
		} else {
			tempul = document.getElementById("ul" + parentAdminCode);
			if (tempul != null) {

				var lichild = document.createElement("li");
				var btn=createNodeNameAsButton(name,adminLevelValue,adminLevelId,localBodyLevel,orgLocatedLevelCode);

				lichild.appendChild(btn);
				lichild.setAttribute("id", adminLevelValue);
				tempul.appendChild(lichild);
				allChildInArray.push(adminLevelValue);
			} else {

				var linew = document.getElementById(parentAdminCode);
				if (linew == null && parentAdminCode == '${blockEqCode}') {
					linew = document.getElementById('${blockCode}');
				}
				var ulnew = document.createElement("ul");
				ulnew.setAttribute("id", "ul" + parentAdminCode);
				linew.appendChild(ulnew);
				var lichild = document.createElement("li");

				var btn=createNodeNameAsButton(name,adminLevelValue,adminLevelId,localBodyLevel,orgLocatedLevelCode);
				allChildInArray.push(adminLevelValue);
				lichild.appendChild(btn);
				lichild.setAttribute("id", adminLevelValue);
			
				ulnew.appendChild(lichild);
			}
		
		
		}
		
		createGraph(adminLevelValue,name,parentAdminCode,adminLevelId);
	
		
		var hierarchySequenceObj = document.getElementById('hierarchySequence');
		
		var lastData=hirParentToChild.split("@");
		lastData=lastData[lastData.length-2];
		var splitsLastData=lastData.split(",");
		splitsLastData[splitsLastData.length-1]
		var hirParentToChildWitoutLast= hirParentToChild.slice(0,hirParentToChild.length-1)
		var parentChild=hirParentToChildWitoutLast.split(",");
		
		hierarchySequenceObj.value=hierarchySequenceObj.value+splitsLastData[splitsLastData.length-1]+":"+splitsLastData[splitsLastData.length-2]+"@";
		
		}
	};
	
	checkIsChildAlreadyExist=function(adminLevelValue){
		if(adminLevelValue=="1")
			return false;
		var hirarchySplits=hirParentToChild.split("@");
		var isNodeDataNum=false;
		var nodeDataNum=adminLevelValue.split(",").map(Number);
		for(var i=2;i<hirarchySplits.length-2;i++){
			var hirarchySplitsNum=hirarchySplits[i].split(",").map(Number);
			    	if(hirarchySplitsNum.length==nodeDataNum.length && hirarchySplitsNum.toString()==nodeDataNum.toString()){
			    		isNodeDataNum=true;
			    		break;
			    }
		}
		if(isNodeDataNum)
			return isNodeDataNum;
		else
			return false;
	};
	
	
	
	getChildofExistingParent = function(adminLevelId,nodeData,localBodyLevel) {
		
		var parentChilds="";
		
		var hirarchySplits=hirParentToChild.split("@");
		var nodeDataNum=nodeData.split(",").map(Number);
		for(var i=2;i<hirarchySplits.length;i++){
			var isNodeDataNum=true;
			var hirarchySplitsNum=hirarchySplits[i].split(",").map(Number);
			    for(var j=0;j<nodeDataNum.length;j++){
			    	if(hirarchySplitsNum.length!=nodeDataNum.length+1){
			    		isNodeDataNum=false;
			    		break;
			    	}
			    	else if(hirarchySplitsNum.indexOf(nodeDataNum[j])<=-1){
			    		 isNodeDataNum=false;
			    		 break;
			    	}
			    		
			    }
		if(isNodeDataNum){
			if(parentChilds=="")
			parentChilds+=nodeData;
			   
			parentChilds+=hirarchySplits[i].substring(nodeData.length,hirarchySplits[i].length);
		}
		}
		//for last level of childs
		if(parentChilds=="")
		parentChilds=nodeData;
		 var existCode = "";
		//alert(parentAdminLvlCode);
		//alert(parentAdminLvlCode);
		//alert(AdminCode);
		//alert(linkCode);
		
		//ParentLinkCode=linkCode;
		var adminUnitCodes = document.getElementById("adminUnitCodes");
		adminUnitCodes.value="";

		var res = parentChilds.split(",");
		newAdminUnitlist=res.slice(0,res.indexOf(adminLevelId)+1).toString();
		
		
		
		for (var i=0; i<res.length; i++)
		{
			if(res!="")
			adminUnitCodes.value =adminUnitCodes.value+ res[i]+"@";
		}
		childAdminCode=0;
		//currentListData=AdminCode;
		
	/* document.getElementById('adminLevelNameLocal').value=parentAdminLvlCode; */
		/* if(localBodyLevel=="D")
			adminLevelId=2;
		else if(localBodyLevel=="I")
			adminLevelId=3;
		else if(localBodyLevel=="V")
			adminLevelId=4;
		else
			adminLevelId=adminLevelId;	 */
	
		lgdAdminDepatmentDwr.getChildofExistingParentLevelbyOrganization(parseInt(adminLevelId), '${restructureDeptLevelForm.olc}', '${landRegionSlc}', '${restructureDeptLevelForm.slc}', adminUnitCodes.value, localBodyLevel.toString(), 1, {
			callback : function(result) {
				$("#actionDiv").fadeIn(500);
				$("#workingTable tbody table").remove();
				var trLand=$("<table><tr><td class='headertd' >Land Regions Unit Levels</td></tr></table>");
				var trAdmin=$("<table><tr><td class='headertd' >Administrative Unit Levels</td></tr></table>");
				var trLocalBody=$("<table><tr><td class='headertd'>Local Body</td></tr></table>");
				
				var workingtable=$("#workingTable tbody");
				var adminLevelNameLocalNew = document.getElementById("adminLevelNameLocalNew");
				removeAllOptions(adminLevelNameLocalNew);
				//var optgroup = $('<optgroup>');	
				var optgroupLR = document.getElementById('landRegionUnits');
				var optgroupAD = document.getElementById('adminUnits');
				var optgroupLB = document.getElementById('localBody');
				$.each(result, function(i) {
					var child="";
					// child= " "+result[i].adminUnitCode+"@"+result[i].adminLevelNameEng+"@"+result[i].slc+"@"+result[i].parentAdminCode+"";
					var tr=$("<tr></tr>");
					var td=$("<td></td>")
					var a=$("<a>"+result[i].adminLevelNameEng+"</a>");
					a.attr("href","#");
					a.attr("class","anchortag");
					a.attr("onclick","ChangeColor(this),setChildData('"+result[i].adminUnitCode+"@"+result[i].adminLevelNameEng+"@"+result[i].slc+"@"+result[i].parentAdminCode+"@"+result[i].localBodyLevel+"')");
					td.append(a);
					tr.append(td);
					
					
					var opt = document.createElement('OPTION');
					opt.textContent = result[i].adminLevelNameEng;
					opt.value = result[i].adminUnitCode + "@" + result[i].adminLevelNameEng + "@" + result[i].slc + "@" + result[i].parentAdminCode;
					if(result[i].adminUnitCode<0){
						optgroupLB.appendChild(opt);
						trLocalBody.append(tr);
					}
					else if (parseInt(result[i].slc) == 0){
						optgroupLR.appendChild(opt);
						trLand.append(tr);
					}
						
					else{
						optgroupAD.appendChild(opt);
						trAdmin.append(tr);
					}

				});
				workingtable.append(trLand);
				workingtable.append(trLocalBody);
				workingtable.append(trAdmin);
				
				$("#btnBuildHrchy").parent('td').parent('tr').remove();
					var trButton=$("<tr></tr>");
					var tdButton=$(" <td style='height:75px' ></td>");
					var button= createButton(nodeData);
					    tdButton.append(button);
					    trButton.append(tdButton);
					    workingtable.append(trButton);
					    isButton=false;	
				
				

			},
			async : true
		}); 
	};
	 
     function ChangeColor(obj) {
       
        $("#workingTable a").parent().find('a').removeAttr("style");
        obj.style.backgroundColor = "#bfcbd6";
     }

	function setChildData(adminUnitCode){
    	childData=adminUnitCode;
    	
	};
	function createButton(parentId){
		var btn = document.createElement('BUTTON');
		btn.onclick=function(){
			addIteminHierarchy(parentId);
			closeAction();
			return false;
		};
		btn.style.fontSize = "15px";
		btn.innerHTML ="Extend Hierarchy";
		btn.setAttribute("id","btnBuildHrchy");
		return btn;
		//btn.setAttribute("code","${extendHierarchy}");
		//aTag.setAttribute("onmouseover","tooltip.pop(this, '#demo2_tip')");
		//btn.setAttribute("class",("tooltip"+adminLevelValue));
	}
	
	
	removeAllOptions = function(elementId) {
		dwr.util.removeAllOptions(elementId);
	};

	addListItem = function(id, text) {
		var ul = document.getElementById("list");
		var li = document.createElement("li");

		li.appendChild(document.createTextNode(text));
		li.setAttribute("id", id);

		ul.appendChild(li);

	};

	addListItemchild = function(id, text, parent) {

		var linew = document.getElementById(parent);
		var ulnew = document.createElement("ul");
		ulnew.setAttribute("id", "ul" + id);
		linew.appendChild(ulnew);
		var lichild = document.createElement("li");

		lichild.appendChild(document.createTextNode(text));
		lichild.setAttribute("id", id);
		ulnew.appendChild(lichild);

	};

	addListItemchildexist = function(id, text, parent) {

		var ul = document.getElementById("ul" + parent);
		var lichild = document.createElement("li");

		lichild.appendChild(document.createTextNode(text));
		lichild.setAttribute("id", id);
		ul.appendChild(lichild);
	};

	addNewTopinList = function(id, text, parent) {

		var div = document.getElementById("base");
		var childul = null;
		do {
			if (treeTop == 5 && id == 2) {
				treeTop = 2;
			}
			childul = document.getElementById("ul" + treeTop);
			treeTop = treeTop - 1;
		} while (childul == null)
		if (parent - 1 == treeTop) {

			childul.setAttribute("id", "ul" + (parseInt(treeTopId) - 1));
		}
		var ul = document.createElement("ul");
		ul.setAttribute("id", "ul" + parent);
		var li = document.createElement("li");

		var aTag=createNodeName(name,id);

		li.appendChild(aTag);
		li.setAttribute("id", id);
		li.appendChild(childul);
		ul.appendChild(li);
		div.appendChild(ul);
		treeTop = parent;
		newTop = false;
		resetFlag = false;
		/* if(resetFlag){
			resetStructure();	
		} */
	};

	addMiddleNodeinList = function(id, text, parent) {

		/* parentId=parent-1;
		childId=parseInt(parent)+1; */

		/* alert(existAdminUnitObj[parentId]);
		alert(stateWiseAdminUnits[parentId]);
		alert(existAdminUnitObj[childId]);
		alert(stateWiseAdminUnits[childId]); */
		if (resetFlag) {
			resetStructure();
		}
		tempul = document.getElementById("ul" + parent);
		if (tempul != null) {

			var lichild = document.createElement("li");
			var aTag=createNodeName(name,id);

			lichild.appendChild(aTag);
			lichild.setAttribute("id", id);
			tempul.appendChild(lichild);
		} else {

			var liparent = document.getElementById(parent);
			var ulchild = document.getElementById("ul" + (stateWiseAdminUnits[id] + 1));
			var ul = document.createElement("ul");
			ul.setAttribute("id", "ul" + parent);
			var li = document.createElement("li");

			var aTag=createNodeName(name,id);

			li.appendChild(aTag);
			li.appendChild(ulchild);
			ul.appendChild(li);
			liparent.appendChild(ul);

		}
		midNode = false;
	};

	resetChildListbyadminUnitCode = function(adminUnitCode) {
		var isCenter = false;
		lgdAdminDepatmentDwr.getLandRegionUnitLevelbyOrganization(isCenter, '${restructureDeptLevelForm.olc}', '${landRegionSlc}', adminUnitCode, {
			callback : function(result) {
				var optgroupLR = document.getElementById('landRegionUnits');
				var optgroupAD = document.getElementById('adminUnits');
				$.each(result, function(i) {
					var opt = document.createElement('OPTION');
					opt.textContent = result[i].adminLevelNameEng;
					opt.value = result[i].adminUnitCode + "@" + result[i].adminLevelNameEng + "@" + result[i].slc + "@" + result[i].parentAdminCode;
					if (parseInt(result[i].slc) == 0)
						optgroupLR.appendChild(opt);
					else
						optgroupAD.appendChild(opt);
				});
			},
			async : true
		});
	};

	removeSelectedValueinList = function() {
		var $select = $('#adminLevelNameLocalNew');
		$('option:selected', $select).remove();

	};

	addCheckBoxofNewLevel = function(adminUnitCode, name) {
		var div = document.getElementById("hierarchyCheckBox");
		var checkbox = document.createElement("input");
		var text = document.createTextNode(name);
		checkbox.setAttribute('checked', "checked");
		checkbox.setAttribute('disabled', "disabled");
		checkbox.type = "checkbox";
		checkbox.name = "updateHierarchy";
		checkbox.value = adminUnitCode;
		div.appendChild(checkbox);
		div.appendChild(text);
	};

	validateFormadminOrgDeptForm = function() {
		var checkbox = document.getElementsByName("updateHierarchy");
		var checkedFlag=false;
		for ( var i = 0; i < checkbox.length; i++) {
			checkbox[i].removeAttribute('disabled');
			if(checkbox[i].checked){
				checkedFlag=true;
				}
		}
		if (checkedFlag){
			$('#btnCreateDept').attr('disabled', true);
			submitFormPostMethod('startRestructureDepartmentCreatetion');
		}
		else{
			$("#cAlert").html("Please Select at least one level for Extending the <spring:message htmlEscape="true" code="${extendEntity}"></spring:message>");
			$("#cAlert").dialog({
				title : '<spring:message htmlEscape="true" code="${formTitle}"></spring:message>',
				resizable : false,
				height : 200,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
						return false;
					}
				}
			});
		}

	};

	submitFormPostMethod = function(methodName) {
		document.forms['adminOrgDeptForm'].method = "POST";
		document.forms['adminOrgDeptForm'].action = methodName + ".htm?<csrf:token uri='"+methodName+".htm'/>";
		document.forms['adminOrgDeptForm'].submit();
		return true;
	};

	reProcessForm = function(processId) {
		if ($('input.updateHierarchy').prop('checked'))
		{
			alert("check");
		}
		var deptNameEnglish = $("#deptNameEnglish").val();
		var sameLevel = document.getElementById("sameLevel");
		var isNewLevel = "${adminOrgDeptForm.newLevel}";
		if (isNewLevel == 'true') {
			lgdAdminDepatmentDwr.checkExistingDepartmentName(parseInt(stateLevelId), deptNameEnglish, null, {
				async : true,
				callback : function(data) {
					if (data) {
						$('#err_deptName').html("<spring:message htmlEscape="true" code="${extendEntity}"></spring:message> Name Already Exist");
						return false;
					} else {
						var url = null;
						if (processId == "NEXT") {
							$('#btnCreateDeptNext').attr('disabled', 'disabled');
							sameLevel.value = false;
							url = _continue_url;
						} else if (processId == "SAVE") {
							$('#btnSaveDept').attr('disabled', 'disabled');
							url = _save_url;
						} else if (processId == "AddDeptatSameLevel") {
							url = _continue_url;
							sameLevel.value = true;
						} else {
							customAlert("Invalid Procees Called.");
							return false;
						}

						$('#close').attr('disabled', 'disabled');
						$("select[multiple] option").prop("selected", "selected");
						document.forms['adminOrgDeptForm'].method = "POST";
						document.forms['adminOrgDeptForm'].action = url;
						document.forms['adminOrgDeptForm'].submit();
						return true;
					}
				}
			});
		} else {

			var url = null;
			if (processId == "NEXT") {
				$('#btnCreateDeptNext').attr('disabled', 'disabled');
				sameLevel.value = false;
				url = _continue_url;
			} else if (processId == "SAVE") {
				$('#btnSaveDept').attr('disabled', 'disabled');
				url = _save_url;
			} else if (processId == "AddDeptatSameLevel") {
				url = _continue_url;
				sameLevel.value = true;
			} else {
				customAlert("Invalid Procees Called.");
				return false;
			}

			$('#close').attr('disabled', 'disabled');
			$("select[multiple] option").prop("selected", "selected");
			document.forms['adminOrgDeptForm'].method = "POST";
			document.forms['adminOrgDeptForm'].action = url;
			document.forms['adminOrgDeptForm'].submit();
			return true;

		}

	};

	createStateWiseAdminUnits = function(AdminUnitString) {
		//alert(AdminUnitString);
		stateWiseAdminUnits = JSON.parse(AdminUnitString);
		//alert(stateWiseAdminUnits[2]);
	};

	/* pushitemintoList=function(key,value){
		parentList[key]=value;
	}; */
	
	createExistAdminUnit = function(existAdminUnitString) {
		//existAdminUnit=existAdminUnitString;
		existAdminUnitObj = JSON.parse(existAdminUnitString);
	};

	resetStructure = function() {
		for ( var i in existAdminUnitObj) {
			if (i != undefined) {
				if (existAdminUnitObj[i] != stateWiseAdminUnits[i] && (i == 1 || i == 2 || i == 3 || i == 4 || i == 5)) {
					ulObj = document.getElementById("ul" + existAdminUnitObj[i]);
					ulObj.setAttribute("id", "ul" + stateWiseAdminUnits[i]);
				}
			}
		}
		resetFlag = false;
	};
	
	createNodeNameAsButton=function (name,adminLevelValue,adminLevelId,localBodyLevel,orgLocatedLevelCode){
		var btn = document.createElement('BUTTON');
		btn.onclick=function(){
			getChildofExistingParent(adminLevelId,adminLevelValue,localBodyLevel);
			return false;
		};
		/* btn.style.fontSize = "10px"; */
		btn.innerHTML = name;
	//	btn.setAttribute("class","myButton");
		//aTag.setAttribute("onmouseover","tooltip.pop(this, '#demo2_tip')");
		btn.setAttribute("class",("tooltip"+adminLevelValue)+" myButton");
		
		btn.onmouseover = function() {
					
			$('#demo2_tip').empty();
			 $("#demo2_tip").append(name+" Level <ol>");
			
			 $.each(existOfficeList, function(key,obj) {
				 if(obj.hierarchy==adminLevelValue) {
				 $("#demo2_tip").append("<li>"+obj.adminLevelNameEng+"</li>"); 
				 }
			 });
			 
			 $("#demo2_tip").append("</ol>");
			
			 
			 $("." +("tooltip"+adminLevelValue) ).popModal({
					html : $("#demo2_tip" ),
					placement : 'rightTop',
				});
			
			
		};  
		return btn;
	};
	
	
	runCommand=function(){
		alert("hello")
	};
	
	
	
	
	
	
	/*  $(document).ready(function() {
	var olc='${restructureDeptLevelForm.olc}';
	if(olc!=null && olc!=""){
		lgdAdminDepatmentDwr.getOfficeNamebyLevel(parseInt(olc), null,{
			 callback : function(result){
				existOfficeList=result;
			
			 },
			 async : true
			 });	
	}
	
	}); */
	
	function closeAction(){
		$("#actionDiv").fadeOut(500);
		
		
	}
	
	$(function() {
	    $("#nestActionDiv").draggable();
	});
	
	createGraph=function(adminLevelValue,name,parentAdminCode,adminUnitCode){
		 if (midNode) {
			addMiddleNodeinList(adminLevelValue, name, parentAdminCode);
			//FristNode
		} else if (isHierarchy) {
			  var tb=  document.getElementById("hierarchyData");
			  var ul=document.createElement("ul");
			     ul.setAttribute("class","treeMenuParent");
			 var li = document.createElement("li");
			 li.setAttribute("class","treeMenuChild");
			 var aTag=createNodeName(name,adminUnitCode);
			 li.appendChild(aTag);
			 ul.append(li);
			 tb.append(ul);
			 li.setAttribute('id',"li"+adminLevelValue);
				isHierarchy = false;
		} else {
			
			  /*  if(arrParentOrgLocatedLevelCode.indexOf(parentAdminCode)>=0){
			
				  
				  var exitLi=  document.getElementById("li"+parentAdminCode);
				  var ul=  document.createElement("ul");
				  ul.setAttribute("style","width:100%");
			    var tr=document.createElement("ul");     
			     tr.setAttribute('id',"ul"+adminLevelValue);
			    var td = document.createElement("li");
			  var aTag=createNodeName(name,adminLevelValue);
			  td.appendChild(aTag);
			  tr.append(td);
			  ul.append(tr);
			  exitLi.append(ul);
			 td.setAttribute('id',"li"+adminLevelValue); 
			 
				/* lichild.setAttribute("id", adminLevelValue);
				tempul.appendChild(lichild); */
			
			
			
			
			/* tempul = document.getElementById("ul" + parentAdminCode);
			if (tempul != null) {

				
				var lichild = document.createElement("li");
				var btn=createNodeName(name,adminLevelValue);

				lichild.appendChild(btn);
				lichild.setAttribute("id", adminLevelValue);
				tempul.appendChild(lichild);  */
		//	}  else {
			
			

				     var exitLi=  document.getElementById("li"+parentAdminCode);
				       var ul=document.createElement("ul");
				       ul.setAttribute("class","treeMenuParent");
				       exitLi.append(ul);
				    /*  var tr=  exitLi.parentElement; */
					 var li = document.createElement("li");
					 li.setAttribute("class","treeMenuChild");
					 var aTag=createNodeName(name,adminUnitCode);
					 li.append(aTag);
					 ul.append(li);
					 li.setAttribute('id',"li"+adminLevelValue);
					 
				
			}
              arrParentOrgLocatedLevelCode.push(parentAdminCode);
		//}
	};
	createNodeName=function (name,adminUnitCode){
		var aTag = document.createElement('a');
		aTag.setAttribute('href', "#");
		aTag.innerHTML = name;
		aTag.setAttribute("class","anchorChild");
		if(adminUnitCode!=1){
			var checkbox = $('<input type="checkbox"></input>');
			checkbox.prependTo(aTag);
				 checkbox.attr("name","updateHierarchy");
				 checkbox.attr("value",adminUnitCode);
				 if(isAddIteminHierarchy){
				 checkbox.attr('checked', true).attr("disabled", true);
				 }
		}
		
			 
		return aTag;
	};
	addDataInHierarchyList=function(adminUnitCode,name,hierarchy){
		var obj = {};
		obj["adminUnitCode"]=adminUnitCode;
		obj["name"]=name;
		obj["hierarchy"]=hierarchy;
		existOfficeList.push(obj);
	};
	</script>
	

