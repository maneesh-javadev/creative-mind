<%@include file="../common/taglib_includes.jsp"%>
<c:set var="deptOrgOperationalAt" value="Label.SELLEVELADDOFFTYPE"></c:set>
<c:set var="formTitle" value="Label.ExtendDeptSetup"></c:set>
<c:set var="selectCreteria" value="Label.SELDEPT"></c:set>
<c:set var="extendHierarchy" value="Label.ExtendDeptHierarchy"></c:set>
<c:set var="extendEntityButton" value="Button.ExtendDept"></c:set>
<c:set var="extendEntity" value="Label.Department"></c:set>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/showTree.css">


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

<c:choose>
	<c:when  test="${!empty deptList }">
		<script type='text/javascript'>
		var getDeptSetup = function() {
			 $( "span[id^=err]" ).each(function(){
					$( this ).text("");
			    });
			var dept = $("#deptId").val();

			 if(isEmptyObject(dept)){
				 $( '#errdeptId').text("<spring:message htmlEscape="true" code="${selectCreteria}"></spring:message>");
			 }else {
					var form = document.getElementById('deptform');
					$("#btnGet").attr("disabled", "disabled");
					form.submit();
				}
			
		};
		
		callActionUrl=function(url){
			   
		    $( 'form[id=deptform]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
			$( 'form[id=deptform]' ).attr('method','post');
			$( 'form[id=deptform]' ).submit();
		};

		</script>
	</c:when>
	<c:otherwise>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery.treeview/css/jquery.treeview.css" />




<%-- <%@include file="../reports/consolidateReportTooltip.jsp"%> --%>



<script type='text/javascript'>



$(document).ready(function() {
	
	/*  $('[data-toggle="tooltip"]').tooltip();  */
	
	$( '#errchildUnitLevel').text("");
	$("#btnExtendHierarchy").click(function() {
		var select_child_val= $("#childUnitLevel").val();
		var select_child_text=$('#childUnitLevel option:selected').text();
		 if(!isEmptyObject(select_child_val)){
			 createChildNode(cur_parent_id,select_child_val,select_child_text,false,null);
			 $('#exampleModal').modal('hide');
		 }else{
			 
			 $( '#errchildUnitLevel').text("Select any Level for extends");
		 }
		 
	  });
	
	$("#btnExtendDept").click(function() {
		extendDepartmentHierarchy();
	  });
	
	$("#btnClose").click(function() {
		callActionUrl('home.htm');
	  });
});

    var cur_parent_id;
	var isBase=false;
	var dataElement = new Array();
	var selElements= new Array();
	dataElement.push("-1,0");
	

	
	extendDepartmentHierarchy=function(){
	$('input:checkbox:checked').map(function() {
				chkval=$(this).attr('id')
				selElements.push(chkval.substring(3,chkval.length));
			   
			});
			if(selElements.length>0){
				$("#hierarchyIds").val(selElements.join('@'));
				callActionUrl('startRebuildTree.htm');
			}else{
				$("#alertboxbody").text("Please select a level in order to extend department hierarchy");
				$('#alertbox').modal('show');	
		}
	};
	
	callActionUrl=function(url){
	   
	    $( 'form[id=adminOrgDeptForm]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
		$( 'form[id=adminOrgDeptForm]' ).attr('method','post');
		$( 'form[id=adminOrgDeptForm]' ).submit();
	};

	
	
	buildTree=function(){
		var div = document.getElementById("base");
		if(!isBase){
			ulCenter=createBase();
			div.appendChild(ulCenter);
		}	
	};
	
	var createBase=function(){
		
		 var ulCenter = document.createElement("UL");       
		 ulCenter.setAttribute("id", "ul-1")
		
		 var liCenter = document.createElement("LI");   
		 liCenter.setAttribute("id", "0")
		 
		 
		 
		 var buttonCenter =document.createElement("BUTTON");	
		 buttonCenter.setAttribute("class","btn btn-primary btn-xs");
		 buttonCenter.setAttribute("type", "button");
		 buttonCenter.setAttribute("onclick", "showchild('0')");
		 buttonCenter.innerHTML ='Center';
		 buttonCenter.setAttribute('data-toggle', 'tooltip');
		 buttonCenter.setAttribute("title", "Click to extend hierarchy at Center Level ");
		 
		
		
		liCenter.appendChild(buttonCenter);
		
		ulCenter.appendChild(liCenter);
		isBase=true;
		return ulCenter;
		
	};
	
	
	createChildNode=function(parent_id,new_child,child_name,isExist,parentCode){
		var liParent=document.getElementById(parent_id);      
		
		if(liParent!=null){
			 var ulNewEntity=document.getElementById("ul"+parent_id);
			 if(ulNewEntity==null){
				 ulNewEntity =  document.createElement("UL");      
				 ulNewEntity.setAttribute("id", "ul"+parent_id);
			 }
			
			 
			 var liNewEntity =  document.createElement("LI");  
			 var id=(parent_id+","+new_child);
			 liNewEntity.setAttribute("id",id);
			 
			 var divGroup =  document.createElement("DIV");  
			 divGroup.setAttribute("class","grp");
			 
			 var checkboxEntity =document.createElement("INPUT");
			 if(!isExist){
				 checkboxEntity.setAttribute("disabled", "true");
				 checkboxEntity.setAttribute("checked", "true");
			 }
			 checkboxEntity.setAttribute("id","chk"+id);
			 checkboxEntity.setAttribute("type", "checkbox");
			 checkboxEntity.setAttribute('data-toggle', 'tooltip');
			 checkboxEntity.setAttribute("title", "Select to add another office at "+child_name+" Level ");
			 //checkboxEntity.setAttribute("onclick", "showchild('"+id+"')");
			 //checkboxEntity.innerHTML =child_name;
			 
			 var buttonNewEntity =document.createElement("BUTTON");	
			 buttonNewEntity.setAttribute("class","btn btn-primary btn-xs");
			 buttonNewEntity.setAttribute("type", "button");
			 buttonNewEntity.setAttribute("onclick", "showchild('"+id+"')");
			 buttonNewEntity.setAttribute('data-toggle', 'tooltip');
			 buttonNewEntity.setAttribute("title", "Click to extend hierarchy at "+child_name+" Level ");
			
			 buttonNewEntity.innerHTML =child_name;
			 
			 divGroup.appendChild(checkboxEntity);
			 divGroup.appendChild(buttonNewEntity);
			 
			 if(isExist){
			  var el = document.createElement('img');
				el.src = "${pageContext.request.contextPath}/images/treeRightbutton.jpg";
				el.setAttribute('style', 'width:10px;height:22px');
				el.setAttribute('data-toggle', 'tooltip');
				el.setAttribute('title', "Click to view existing Office Name(s) at "+child_name+"  Level ");
				/* el.setAttribute('data-placement', 'right'); */
			
				el.onclick = function() {
					//alert("use click");
					getLevelwiseOfiice(parentCode,child_name,new_child);
				}; 
				
				divGroup.appendChild(el);
			 }
				
			
			 
			 
			 liNewEntity.appendChild(divGroup);
			 ulNewEntity.appendChild(liNewEntity);
			 
			 liParent.appendChild(ulNewEntity);
			 dataElement.push(id);
			 
		}
	};
	
	
	removeAllOptions = function(elementId) {
		dwr.util.removeAllOptions(elementId);
	};
	
	showchild=function(parent_id){
		
		cur_parent_id=parent_id;
		var parentArr=parent_id.split(",");
		var nebnodes=new Array();
		
		jQuery.each(parentArr, function(index, dirParent) {
			nebnodes.push(dirParent);
		});
		//alert(parentArr[(parentArr.length-1)]);
		adminCode=parentArr[(parentArr.length-1)];
		//createChildNode('1','2','District');
		//parentArr.pop();
		 //var dataElement=["0,1","1,2","1,3","1,4","1,3,4","1,2,12","1,2,3","1,2,5","1,2,3,4"];
		var searchElement=parent_id;
		
		
		
		jQuery.each(dataElement, function(index, item) {
		//alert("value:"+item+"index:"+item.indexOf(searchElement)+"search:"+searchElement);
		var itemArr=item.split(",");
		var searchArr=searchElement.split(",");
		if(item.indexOf(searchElement)==0 && itemArr.length==(searchArr.length+1)){
			nebnodes.push(item.substring((searchElement.length+1),item.length));
		}
		});
		var existAdminCodes=null;
		if(nebnodes.length>0){
			existAdminCodes=nebnodes.toString();
		}
		
		 lgdAdminDepatmentDwr.getChildofParentAdminCode(parseInt('${buildTree.slc}'),parseInt(adminCode), existAdminCodes, {
			callback : function(result) {
				removeAllOptions( $("#childUnitLevel"));
				$('#childUnitLevel optgroup[label="Land Regions Unit Levels"] option').remove()
				$('#childUnitLevel optgroup[label="Local Body"] option').remove()
				$('#childUnitLevel optgroup[label="Administrative Unit Levels"] option').remove()
				
				//var optgroup = $('<optgroup>');	
				var optgroupLR =  $("#landRegionUnits");   
				var optgroupAD =   $("#adminUnits");         
				var optgroupLB =  $("#localBody");  
			
				jQuery.each(result, function(index, obj) {
					var option = $("<option />");
					option.val(obj.adminUnitCode).text(obj.adminLevelNameEng);
					if(obj.entityType=="LR"){
						optgroupLR.append(option);
					}else if(obj.entityType=="LB"){
						optgroupLB.append(option);
					}
					else if(obj.entityType=="AD"){
						optgroupAD.append(option);
					}
					
					
				});
				
				$('#exampleModal').modal('show')

			

			},
			async : true
		}); 
		
	}; 
	
	
	var createParentLevel=function(parentLevelName){
		
		 var ulParent = document.createElement("UL");       
		 ulParent.setAttribute("id", "ulo0")
		 
		
		
		 var liParent = document.createElement("LI");   
		 liParent.setAttribute("id", "liParentOffice")
		 
		 var buttonparent =document.createElement("BUTTON");	
		 buttonparent.setAttribute("class","btn btn-primary btn-xs");
		 buttonparent.setAttribute("type", "button");
		 buttonparent.innerHTML =parentLevelName;
		 
		 ulNewEntity =  document.createElement("UL");      
		 ulNewEntity.setAttribute("id", "ulParentOffice");
		
		liParent.appendChild(buttonparent);
		liParent.appendChild(ulNewEntity);
		ulParent.appendChild(liParent);
		
		
		
		return ulParent;
		
	};
	
	
	createChildOfficeNode=function(child_name,child_id){
		
			var liParent=document.getElementById("liParentOffice");  
			
			var ulNewEntity=document.getElementById("ulParentOffice");  
			
		
			var liNewEntity =  document.createElement("LI");  
			 liNewEntity.setAttribute("id",child_id);
			 
			 var buttonNewEntity =document.createElement("BUTTON");	
			 buttonNewEntity.setAttribute("class","btn btn-primary btn-xs");
			 buttonNewEntity.setAttribute("type", "button");
			 buttonNewEntity.innerHTML =child_name;
			 
			 liNewEntity.appendChild(buttonNewEntity);
			 ulNewEntity.appendChild(liNewEntity);
			 liParent.appendChild(ulNewEntity);
			
		
	};
	
	
	getLevelwiseOfiice=function(parentCode,parentLevelName,locatedAtLevel){
		$( '#offietitle').text("Existing Office Name(s) "+parentLevelName+" Level "); 
		
		var div = document.getElementById("officeTree");
		$("#officeTree").empty();
		var olc=$("#olc").val();
		 var ulParent=createParentLevel(parentLevelName);
		 div.appendChild(ulParent);

		 lgdAdminDepatmentDwr.fetchOfficeDetailbyParent(olc,parentCode,locatedAtLevel, {
			callback : function(result) {
				jQuery.each(result, function(index, obj) {	
					createChildOfficeNode(obj.orgLevelSpecificName,index)
				
				});
				
				$('#showOffice').modal('show')
				
			},
			async : true
		}); 
	};
	
	
	</script>
	</c:otherwise>

</c:choose>
	

