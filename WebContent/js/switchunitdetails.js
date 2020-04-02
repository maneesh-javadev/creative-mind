var strSelect=[{optValue:'',optText:'--- Select ---'}];

var strCenterSelect=[{optValue:'0',optText:'--- Select ---'}];



//**********************Global Variable**************************

var entityType = "";
var parentId = 0;
var selectToAppend = "";
var currentSelect = "";

var levelNo = 0;
var selectedlevelis=0;
var single=1;
var currntNoSelect="";
var lastNoSelect="";

var dataStore="";

//*****************************************************************
//Handling state level or center level assigned unit 
function getLevelOfAssignedUnit() {
	// $("#assignedUnitDiv").hide();
	// $("#level0tr").hide();
	
	 $("#level1tr").fadeOut();
	 $("#level2tr").fadeOut();
	 $("#level3tr").fadeOut();
	 $("#level4tr").fadeOut();
	 $("#assignedUnittr").fadeOut();
	 $("#statetr").fadeOut();
	 
	 dwr.util.removeAllOptions('level1');
	 dwr.util.addOptions('level1',strSelect,'optValue','optText'); 
	 dwr.util.removeAllOptions('level2');
	 dwr.util.addOptions('level2',strSelect,'optValue','optText'); 
	 dwr.util.removeAllOptions('level3');
	 dwr.util.addOptions('level3',strSelect,'optValue','optText'); 
	 dwr.util.removeAllOptions('level4');
	 dwr.util.addOptions('level4',strSelect,'optValue','optText'); 
	 dwr.util.removeAllOptions('stateId');
	 dwr.util.addOptions('stateId',strSelect,'optValue','optText'); 
}




/*function getEntityTypeBasedOnUserId() {
	$("[id$=_error]").hide();
	getLevelOfAssignedUnit();
    var userId = $("#userId").val();
    var applicationId = $("#applicationId").val();
    
    pesUtility.getEntityTypePojoListbasedOnUserIdAppId(parseInt(userId), applicationId,{
        callback:function(data) {
                dataStore=data;
                dwr.util.removeAllOptions('level0');
                dwr.util.addOptions('level0',strSelect,'optValue','optText'); 
             if(data!=null) {
                 dwr.util.addOptions('level0', data,'entityFormLevelId','levelEntityName');
             } else {
                 alert("Record Not Found");
             }
             if(data!=null && data.length==1) {
                 $("#level0").val( data[0].entityFormLevelId );
             }
        }
       ,preHook:function() {$(".process").fadeIn(500); },
        postHook:function() { $(".process").fadeOut();},
        timeout:50000,
        async:false,
        errorHandler:function(message) { alert("Oops:1 " + message);} 
    });
        getAssignedUnitHierrachy(0); 
}*/





function getEntityTypeBasedOnUserId()
{
	getLevelOfAssignedUnit();
    var userId = $("#userId").val();
    var applicationId = $("#applicationId").val();
    var stateOldId= $("#stateOldId").val();
    var entityId= $("#entityId").val();
    
    
    
    pesUtility.getEntityTypePojoListbasedOnUserIdAppIdStateId(parseInt(userId), applicationId,parseInt(stateOldId),{
        callback:function(data) {
                dataStore=data;
                dwr.util.removeAllOptions('level0');
                dwr.util.addOptions('level0',strSelect,'optValue','optText'); 
             if(data!=null && data.length>0) {
                 dwr.util.addOptions('level0', data,'entityFormLevelId','levelEntityName');
             } else {
                 alert("Record Not Found");
             }
             if(data!=null && data.length==1) {
                 $("#level0").val( data[0].entityFormLevelId );
             }
        }
       ,preHook:function() {$(".process").fadeIn(500); },
        postHook:function() { $(".process").fadeOut();},
        timeout:50000,
        async:false,
        errorHandler:function(message) { alert("Oops:1 " + message);} 
    });
        getAssignedUnitHierrachy(0); 
}

function clearAllLevel(levelHierarchy){
		$("[id$=_error]").hide();
	 	if(levelHierarchy==0) {
		  $("#level1tr").fadeOut();
			 $("#level2tr").fadeOut();
			 $("#level3tr").fadeOut();
			 $("#level4tr").fadeOut();
			 $("#assignedUnittr").fadeOut();
			 $("#statetr").fadeOut();
			
			 dwr.util.removeAllOptions('level1');
			 dwr.util.addOptions('level1',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('level2');
			 dwr.util.addOptions('level2',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('level3');
			 dwr.util.addOptions('level3',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('level4');
			 dwr.util.addOptions('level4',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('assignedUnit');
			 dwr.util.addOptions('assignedUnit',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('stateId');
			 dwr.util.addOptions('stateId',strSelect,'optValue','optText');
			 
			 
	  }
	
	 if(levelHierarchy==1){
		  
			/* $("#level2tr").fadeOut();
			 $("#level3tr").fadeOut();
			 $("#level4tr").fadeOut();
			 $("#assignedUnittr").fadeOut();*/
			 $("#statetr").fadeOut();
			 dwr.util.removeAllOptions('level2');
			 dwr.util.addOptions('level2',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('level3');
			 dwr.util.addOptions('level3',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('level4');
			 dwr.util.addOptions('level4',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('assignedUnit');
			 dwr.util.addOptions('assignedUnit',strSelect,'optValue','optText'); 
			 dwr.util.removeAllOptions('stateId');
			 dwr.util.addOptions('stateId',strSelect,'optValue','optText'); 
	  }
	 
	 
	 if(levelHierarchy==2){
		/*  
		 $("#level3tr").fadeOut();
		 $("#level4tr").fadeOut();
		 $("#assignedUnittr").fadeOut();*/
		 $("#statetr").fadeOut();
		
		 dwr.util.removeAllOptions('level3');
		 dwr.util.addOptions('level3',strSelect,'optValue','optText'); 
		 dwr.util.removeAllOptions('level4');
		 dwr.util.addOptions('level4',strSelect,'optValue','optText'); 
		 dwr.util.removeAllOptions('assignedUnit');
		 dwr.util.addOptions('assignedUnit',strSelect,'optValue','optText'); 
		 dwr.util.removeAllOptions('stateId');
		 dwr.util.addOptions('stateId',strSelect,'optValue','optText'); 
  }
	 
	 if(levelHierarchy==3){
		  
		/* $("#level4tr").fadeOut();
		 $("#assignedUnittr").fadeOut();*/
		 $("#statetr").fadeOut();
		
		
		 dwr.util.removeAllOptions('level4');
		 dwr.util.addOptions('level4',strSelect,'optValue','optText'); 
		 dwr.util.removeAllOptions('assignedUnit');
		 dwr.util.addOptions('assignedUnit',strSelect,'optValue','optText'); 
		 dwr.util.removeAllOptions('stateId');
		 dwr.util.addOptions('stateId',strSelect,'optValue','optText'); 
  }
	 var temVal=$("#level"+levelHierarchy).val();
	 if(temVal==""){
		  return false;
	  }
	 getAssignedUnitHierrachy(levelHierarchy);
	
}

function getAssignedUnitHierrachy(levelHierarchy) {
	   
		  if(levelHierarchy==0) {
			  getLevelOfAssignedUnit();
			  var temVal=$("#level0").val();
			  if(temVal==""){
				  //getLevelOfAssignedUnit() ;
				  return false;
			  }
			  
			  
			 
			  
			
			 
			  
		  var entityLevelStr = $("#level0").val();
		  
		  var entityLevelStr1=entityLevelStr.split("_");
		    var entityShortType=entityLevelStr1[0];
		    var lgdLevelRefId=entityLevelStr1[1];
		    var lgdEntityRefId=entityLevelStr1[2];
		    
		    pesUtility.getEntityLevelWithLgdRefPojoBasedOn(entityShortType,parseInt(lgdEntityRefId),parseInt(lgdLevelRefId),{
		        callback:function(data) {
		            if(data!=null) {
		                 $("#entityId").val(data.entityId);
		                 $("#levelId").val(data.levelId);
		            }
		        }
		        ,preHook:function() {$(".process").fadeIn(500); },
		        postHook:function() { $(".process").fadeOut();},
		        timeout:50000,
		        async:false,
		        errorHandler:function(message) { alert("Oops: " + message);} 
		           
		    });
		    //Department
		    var entityIdTempStr="";
		    var levelIdTempStr="";
		    if(entityShortType=='O') {//Organization
		          entityIdTempStr=$("#entityId").val();
		          levelIdTempStr=$("#levelId").val();
		          if(entityIdTempStr==2 || entityIdTempStr==7){
			             if(lgdLevelRefId==0) {//Center
			                 lastNoSelect=1;
			            }
			             if(lgdLevelRefId==1) {//State
			                 lastNoSelect=1;
			            }
			             if(lgdLevelRefId==2) {//Ditrict
			                 lastNoSelect=2;
			                // $("label[for="+"'level1"+"'"+"]").text("District");
			                 $("#spanlevel1").text("District");
					         $("#level1"+"tr").fadeIn(1000);
			            }
			             if(lgdLevelRefId==3) {//SubDistrict
			                 lastNoSelect=3;
			                 //$("label[for="+"'level1"+"'"+"]").text("District");
			                 $("#spanlevel1").text("District");
					         $("#level1"+"tr").fadeIn(1000);
					         //$("label[for="+"'level2"+"'"+"]").text("Block");
					         $("#spanlevel2").text("Block");
					         $("#level2"+"tr").fadeIn(1000);
			            }
			             if(lgdLevelRefId==4) {//Vilage
			                 lastNoSelect=4;
			                 //$("label[for="+"'level1"+"'"+"]").text("District");
			                 $("#spanlevel1").text("District");
					         $("#level1"+"tr").fadeIn(1000);
					         //$("label[for="+"'level2"+"'"+"]").text("Block");
					         $("#spanlevel2").text("Block");
					         $("#level2"+"tr").fadeIn(1000);
					        // $("label[for="+"'level3"+"'"+"]").text("Village");
					         $("#spanlevel3").text("Village");
					         $("#level3"+"tr").fadeIn(1000);
			            }
			         }
		    } else if(entityShortType=='G') {//Panchayat Traditional ULB
		         entityIdTempStr=$("#entityId").val();
		         levelIdTempStr=$("#levelId").val();
		         var tierSetUpStr=$("#tierSetUp").val();
		         var tierSetUpLength=new Array;
		         tierSetUpLength= tierSetUpStr.split(",");
		        
		         
		         var traditionalBodiesTierSetUpStr=$("#tradionalTierSetUp").val(); 
		         var traditionalBodiesTierSetUpLength=traditionalBodiesTierSetUpStr.split(",");
		         
		        if(entityIdTempStr==1){//Panchayat
		            if(levelIdTempStr==3) {
		                if(tierSetUpLength.length==3){
		                    lastNoSelect=3;
		                } else if(tierSetUpLength.length==2) {
		                    lastNoSelect=2;
		                }
		            } else if(levelIdTempStr==2){
		            	if(tierSetUpLength.indexOf("1")>-1){
		                    lastNoSelect=2;
		                } else {
		                    lastNoSelect=1;
		                }
		            } else if(levelIdTempStr==1){
		            	lastNoSelect=1;
		            }
		            //TierSetUpShow
		            var stateCode = $("#stateOldId").val();
		           // var tirSetupStr=null;
		            pesUtility.getLocalBodyTierSetUp(stateCode,"P",{
		                callback:function(data) {
		                    for(var i=0;i<(lastNoSelect-1);i++){
		                       /* $("label[for="+"'level"+(i+1)+"'"+"]").text(data[i].nomenclatureEnglish);*/
		                    	 $("#spanlevel"+(i+1)).text(data[i].nomenclatureEnglish);
		                        $("#level"+(i+1)+"tr").fadeIn(1000);
		                    }
		                    $("#assignedUnittr").fadeIn(500);
		                }
		                   ,preHook:function() {$(".process").fadeIn(500); },
		                    postHook:function() { $(".process").fadeOut();},
		                    timeout:50000,
		                    async:false,
		                    errorHandler:function(message) { alert("Oops: " + message);} 
		                });
		            
		        }
		        if(entityIdTempStr==3){//ULB
		            lastNoSelect=2;
		            /*$("label[for="+"'level1"+"'"+"]").text("District");*/
		            $("#spanlevel"+(i+1)).text("District");
		            $("#assignedUnittr").fadeIn(500);
		        }
		        
		        if(entityIdTempStr==4){ //Traditional Bodies
		        	var lvlNo=0;
		            if(lgdLevelRefId==11) {
		            	var positionNo=traditionalBodiesTierSetUpLength.indexOf("13");
		            	if(positionNo>-1){
		            		lvlNo=lvlNo+1;
		            	}
		            	positionNo=traditionalBodiesTierSetUpLength.indexOf("14");
		            	if(positionNo>-1){
		            		lvlNo=lvlNo+1;
		            	}
		            	positionNo=traditionalBodiesTierSetUpLength.indexOf("11");
		            	if(positionNo>-1){
		            		lvlNo=lvlNo+1;
		            	}
		            	lastNoSelect=lvlNo;
		               
		            } else if(lgdLevelRefId==14){
		            	var positionNo=traditionalBodiesTierSetUpLength.indexOf("13");
		            	if(positionNo>-1){
		            		lvlNo=lvlNo+1;
		            	}
		            	positionNo=traditionalBodiesTierSetUpLength.indexOf("14");
		            	if(positionNo>-1){
		            		lvlNo=lvlNo+1;
		            	}
		            	lastNoSelect=lvlNo;
		            } else if(lgdLevelRefId==13){
		            	 lastNoSelect=1;
		            } else if(lgdLevelRefId==12){
		            	 lastNoSelect=1;
		            }
		            //Traditional TierSetUp Show
		            var stateCode = $("#stateOldId").val();
		            pesUtility.getLocalBodyTraditionalTierSetUp(stateCode,"T",{
		                callback:function(data) {
		                    for(var i=0;i<(lastNoSelect-1);i++){
		                       /* $("label[for="+"'level"+(i+1)+"'"+"]").text(data[i].nomenclatureEnglish);*/
		                    	$("#spanlevel"+(i+1)).text(data[i].nomenclatureEnglish);
		                        $("#level"+(i+1)+"tr").fadeIn(1000);
		                    }
		                    $("#assignedUnittr").fadeIn(500);
		                }
		                   ,preHook:function() {$(".process").fadeIn(500); },
		                    postHook:function() { $(".process").fadeOut();},
		                    timeout:50000,
		                    async:false,
		                    errorHandler:function(message) { alert("Oops: " + message);} 
		                });
		            
		        
		      
		        }
		    }
	  }
		
	var levelNo = levelHierarchy; 
	selectedFinancialYear=$("#year").val();
	var stateCode=null;
	if(levelHierarchy==0) { 
	 stateCode = $("#stateOldId").val();
	} else { 
		 stateCode = $("#stateId").val();
		if(stateCode==null || stateCode=="") {
			
			 var entityLevelStr = $("#level0").val();
			 var entityLevelStrArr=entityLevelStr.split("_");
			 var entityShortType=entityLevelStrArr[0];
			 var lgdLevelRefId=entityLevelStrArr[1];
			 var lgdEntityRefId=entityLevelStrArr[2];
			 if(entityShortType=='G' && lgdEntityRefId !=3) {
				 var financialYear=$("#year").val( );
				 var levelLocalValue = $("#level1").val();
				 pesUtility.getLbDetailsBasedOnlbcAndFinancialYear( levelLocalValue, financialYear,{
					 callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
				 	 preHook:function() {$(".process").fadeIn(5000); },
					 postHook:function() { $(".process").fadeOut();},
					 async:false,
					 timeout:50000
				 });
			 } else if ((entityShortType=='O' && lgdLevelRefId>0) || (entityShortType=='G' && lgdEntityRefId ==3)){
				var stateCode = $("#stateOldId").val();
				var stateVersion = $("#stateVersion").val();
				pesUtility.getStateNameWithStateCodeVersion( stateCode, stateVersion,{
					callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
				 	 preHook:function() {$(".process").fadeIn(5000); },
					 postHook:function() { $(".process").fadeOut();},
					 async:false,
					 timeout:50000
				
				});
			 }
		}
		 stateCode = $("#stateId").val();
	}
	if(levelNo==0) {
				entityType = document.getElementById("level0").value;
				currentSelect = "level"+levelNo;
				currntNoSelect=levelNo;
				parentId = 0;
	} else {
				currentSelect = "level"+levelNo;
				currntNoSelect=levelNo;
				parentId = document.getElementById(currentSelect).value; 
	}
	var val1= document.getElementById("level1").value; 
	var val2 = document.getElementById("level2").value; 
	var val3 = document.getElementById("level3").value; 
	// null in the argument for session
	
	if(entityType != "" ) {
		pesUtility.getAssignedUnitHierarchy(entityType, parseInt(parentId),null,levelNo,selectedFinancialYear,val1,val2,val3,parseInt(stateCode), {
		 	 callback: getAssignedDataFromServerCallBack,
		 	 preHook:function() {$(".process").fadeIn(500); },
			 postHook:function() { $(".process").fadeOut();},
			 async:false,
			 timeout:50000
		 });
	}
}



function getAssignedDataFromServerCallBack(data) {

	var nextSelect=parseInt(currntNoSelect)+1;
	currntNoSelect=nextSelect;
	if(nextSelect==lastNoSelect) {
		dwr.util.removeAllOptions('assignedUnit');
		dwr.util.addOptions('assignedUnit',strSelect,'optValue','optText'); 
		$("#assignedUnittr").fadeIn(500);
		if(data!=null) {
			dwr.util.addOptions('assignedUnit', data,'assignedEntityUnitCode','assignedEntityUnitName');
		} else {
			alert("No Data Found");
		}
		var entityLevelStr = $("#level0").val();
		 var entityLevelStrArr=entityLevelStr.split("_");
		 var entityShortType=entityLevelStrArr[0];
		 var lgdLevelRefId=entityLevelStrArr[1];
		 var lgdEntityRefId=entityLevelStrArr[2];
		if(data!=null && data.length==1 ){
			
			$("#assignedUnit").val( data[0].assignedEntityUnitCode );
			var assignedLocal=null;
			if(entityShortType== 'G' && lgdEntityRefId != 3 ){//For RLB &&ULB
				if(lastNoSelect==1) {
					assignedLocal=$("#assignedUnit").val();
					getStateNameOrLbDetails(assignedLocal);
				} else {
					$("#statetr").fadeIn(500);
					//getStateNameOrLbDetails(assignedLocal);
				}
			}  else { //For Organization
				getStateNameOrLbDetails(assignedLocal);
			}
		}
		
	} else {
		var nextNodeSelect="level"+nextSelect;
		dwr.util.removeAllOptions(nextNodeSelect);
		dwr.util.addOptions(nextNodeSelect,strSelect,'optValue','optText'); 
		$("#level1tr").fadeIn(500);
		if(data!=null) {
			dwr.util.addOptions(nextNodeSelect, data,'assignedEntityUnitCode','assignedEntityUnitName');
		} else {
			alert("No Data Found");
		}
		
		if(data!=null && data.length==1){
			
			$("#"+nextNodeSelect).val( data[0].assignedEntityUnitCode );
			 var entityLevelStr = $("#level0").val();
			 var entityLevelStrArr=entityLevelStr.split("_");
			 var entityShortType=entityLevelStrArr[0];
			 var lgdEntityRefId=entityLevelStrArr[2];
			/* if((entityShortType== 'G' || lgdEntityRefId !=3) && nextSelect==1) {
				 var lbc=$("#level1").val( );
				 var financialYear=$("#year").val( );
				 pesUtility.getLbDetailsBasedOnlbcAndFinancialYear( lbc, financialYear,{
					 callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
				 	 preHook:function() {$(".process").fadeIn(500); },
					 postHook:function() { $(".process").fadeOut();},
					 async:false,
					 timeout:50000
					 
				 });
			 }*/
			 
			 if((entityShortType== 'G' && lgdEntityRefId !=3)) { //RLB (Panchayat +Traditional )
                 if(nextSelect == 1) { 
                     var lbc=$("#level1").val( );
                     var financialYear=$("#year").val( );
                     pesUtility.getLbDetailsBasedOnlbcAndFinancialYear( lbc, financialYear,{
                         callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
                          preHook:function() {$(".process").fadeIn(500); },
                         postHook:function() { $(".process").fadeOut();},
                         async:false,
                         timeout:50000
                     }); 
                 }
             } else { // For Organization + ULB
                     var stateCode = $("#stateOldId").val();
                    var stateVersion = $("#stateVersion").val();
                    pesUtility.getStateNameWithStateCodeVersion( stateCode, stateVersion,{
                        callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
                          preHook:function() {$(".process").fadeIn(500); },
                         postHook:function() { $(".process").fadeOut();},
                         async:false,
                         timeout:50000
                    });
             }
		}
		
		if(data!=null && data.length==1 ){
			 $("#"+nextNodeSelect).val( data[0].assignedEntityUnitCode );
			 getAssignedUnitHierrachy(currntNoSelect); 
		}
	}
	
}


function getStateNameOrLbDetails(lbc){
	/*$("#statetr").fadeIn(500); */
	
	
	 var entityLevelStr = $("#level0").val();
	 var entityLevelStrArr=entityLevelStr.split("_");
	 var entityShortType=entityLevelStrArr[0];
	 var lgdLevelRefId=entityLevelStrArr[1];
	 var lgdEntityRefId=entityLevelStrArr[2];
	 
	 if(entityShortType=='O' && lgdLevelRefId==0) {
		 $("#statetr").fadeOut(); 
		 dwr.util.addOptions('stateId',strCenterSelect,'optValue','optText'); 
		 $("#stateId").val(0 );
	 }
	 if(entityShortType=='G') {
			$("#statetr").fadeIn(500);
	 } else if(entityShortType=='O' && lgdLevelRefId==0) {
			 $("#statetr").fadeOut(); 
	} else if(entityShortType=='O' && lgdLevelRefId>0) {
			 $("#statetr").fadeIn(500);
	}

	 
	 var stateCode = $("#stateId").val();
     if(stateCode !=null && stateCode != "") {
         return ;
     }
	
	 if(entityShortType=='G' && lgdEntityRefId !=3) {
		 $("#statetr").fadeIn(500); 
		 
		 if(lbc == ""){
			 return false;
		 }
		 
		 
		 var financialYear=$("#year").val( );
		 pesUtility.getLbDetailsBasedOnlbcAndFinancialYear( lbc, financialYear,{
			 callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
		 	 preHook:function() {$(".process").fadeIn(500); },
			 postHook:function() { $(".process").fadeOut();},
			 async:false,
			 timeout:50000
		 });
	 } else if ((entityShortType=='O' && lgdLevelRefId>0) || (entityShortType=='G' && lgdEntityRefId ==3) ){ 
		
		var stateCode = $("#stateOldId").val();
		var stateVersion = $("#stateVersion").val();
		pesUtility.getStateNameWithStateCodeVersion( stateCode, stateVersion,{
			callback: getLBDetailsOrStateNameVersionDataFromServerCallBack,
		 	 preHook:function() {$(".process").fadeIn(500); },
			 postHook:function() { $(".process").fadeOut();},
			 async:false,
			 timeout:50000
		
		});
	 }
	 
	 
	 
}



function getLBDetailsOrStateNameVersionDataFromServerCallBack(data) {
	dwr.util.removeAllOptions("stateId");
	dwr.util.addOptions("stateId",strSelect,'optValue','optText');
	if(data!=null && data.length>0) {
		dwr.util.addOptions("stateId", data,'stateCode','stateName');
		if(data.length >= 1) {
			 $("#stateId").val( data[0].stateCode );
		}
	} else {
		alert("No State Found");
	}
}



function validateswithunitForm() {

	var errors = new Array();
	var error = false;
	errors[0] = vlidateOnblur('year','1','100','m');
	errors[1] = vlidateOnblur('level0','1','100','m');
	errors[2] = vlidateOnblur('assignedUnit','1','100','m');
	

	var i=2;
	
	
	
	/***********************************/
	
	if(lastNoSelect == 2) {
		errors[(i+1)] = vlidateOnblur('level1','1','100','m');
		
	}
	if(lastNoSelect == 3) {
		errors[(++i)] = vlidateOnblur('level1','1','100','m');
		errors[(++i)] = vlidateOnblur('level2','1','100','m');
		
	}
	if(lastNoSelect == 4) {
		errors[(++i)] = vlidateOnblur('level1','1','100','m');
		errors[(++i)] = vlidateOnblur('level2','1','100','m');
		errors[(++i)] = vlidateOnblur('level3','1','100','m');
		
	}
	
	var staeCodeType = $("#isCenterOrState").val();
	if(staeCodeType == "S") {
		errors[(++i)]=vlidateOnblur('stateId','1','100','m');;
	}
	
	/**********************************/
	for ( var i = 0; i <= errors.length; i++) {
		if (errors[i] == true) {
			error = true;
			break;
		}
	}
	
	if (error == true) {
		
//		alert("Please fill the profile form properly ...!");
		showClientSideError();
		return false;
	} else {
		return true;
	}
}




function showClientSideError(){
	
$('#overlay1').fadeIn('fast',function(){
    $('#box').animate({'top':'160px'},50);
});

$('#boxclose').click(function(){
$('#box').animate({'top':'-200px'},50,function(){
    $('#overlay1').fadeOut('fast');
});

}); 

}


/*******************************************************************************************/

function validateOnFocus(id)
{
	if(document.getElementById("NoLabels")!=null){
		document.getElementById("NoLabels").style.display='none';
	}
			$("#"+id+"_msg").fadeIn(500);
			$("#"+id).removeClass("error_fld");
			$("#"+id+"_error").hide();
			$("#"+id+"_mandatory_error").hide();
			$("#"+id+"_error1").hide();
			$("#"+id+"_error2").show();
			$("#"+id+"_type_error").hide();			
			$("#"+id+"_reason_error").hide();

			$('#'+id).bind("cut copy paste",function(e) {
			      e.preventDefault();
			  });	
}


function vlidateOnblur(id,minLength,maxLength,type) 
{
	
//	if(id==BulkRoleCheck){
//		if(id.checked==false)
//			return false;
//	}
	//alert("hi vlidateEmailOnblur"+id);
	$("#"+id+"_msg").hide();
	//$("#"+id).val(trimString($("#"+id).val()));
	var tempValue = $("#"+id).val();


	
	if(type == 'm')
	{
		
		if(tempValue==null||tempValue=="-1"||tempValue=="null" || $.trim(tempValue).length == 0)
		{
			$("#"+id+"_error").fadeIn(500);
			return true;
			
		}else if(tempValue.length < minLength || tempValue.length > maxLength){
	
			//$("#"+id).addClass("error_fld");
			//$("#"+id+"_error2").hide();
			$("#"+id+"_error").hide();
			//$("#"+id+"_error1").hide();
			//$("#"+id+"_maxlength_error").fadeIn(500);
			return true;
		}
		else
		{
			$("#"+id+"_error").hide();
			//$("#"+id+"_maxlength_error").hide();
			return false;
		}
	}
	else if(type == 'o')
	{
		if(tempValue.length!=0)
		{
			if(tempValue.length < minLength || tempValue.length > maxLength)
			{
				$("#"+id).addClass("error_fld");
				$("#"+id+"_maxlength_error").fadeIn(500);
				$("#"+id+"_error2").hide();
				return true;
			}
			else
			{
				$("#"+id+"_maxlength_error").hide();
				return false;
			}
		}
		else
			{
			$("#"+id+"_maxlength_error").hide();
			$("#"+id+"_error2").hide();
				return false;
			}
	}

}

/***************************************************************************************************************/


/****************************Not In Use below********************************/


/*****************************Organization******************************************/
/*function getOrganizationListFun(userId,applicationId,entityIdTempStr,levelIdTempStr,stateCode)
{
	pesUtility.getDepartmentListToShow(parseInt(userId),applicationId,entityIdTempStr,levelIdTempStr,stateCode,{
		
		callback:function(data) {
			if(data!=null) {
				dwr.util.removeAllOptions('level1');
				dwr.util.addOptions('level1',strSelect,'optValue','optText'); 
				dwr.util.addOptions('level1', data,'orgUnitCode','orgName');
				
			}
			if(data!=null && data.length==1){
				$("#level1").val( data[0].orgUnitCode );
			}
		}
	    ,preHook:function() {$(".process").fadeIn(500); },
		postHook:function() { $(".process").fadeOut();},
		timeout:50000,
		async:false,
		errorHandler:function(message) { alert("Oops: " + message);} 
		
	});

}

function getLbDetails(lbc){
	$("#statetr").fadeIn(500);
    var financialYear=$("#year").val( );
    dwr.util.removeAllOptions("stateId");
	dwr.util.addOptions("stateId",strSelect,'optValue','optText');
	
	pesUtility.getLbDetailsBasedOnlbcAndFinancialYear( lbc, financialYear,{
		callback:function(data) {
			if(data!=null) {
				dwr.util.addOptions("stateId", data,'stateCode','stateName');
			}
			if(data!=null && data.length==1 ) {
				 $("#statetr").fadeIn(500);
				 $("#stateId").val( data[0].stateCode );
			}
           
        }
       ,preHook:function() {$(".process").fadeIn(500); },
        postHook:function() { $(".process").fadeOut();},
        timeout:50000,
        async:false,
        errorHandler:function(message) { alert("Oops: " + message);} 
	});
	
}



function getStateName(stateCode,stateVersion) {
	$("#statetr").fadeIn(500);
    dwr.util.removeAllOptions("stateId");
	dwr.util.addOptions("stateId",strSelect,'optValue','optText');
	pesUtility.getStateNameWithStateCodeVersion( stateCode, stateVersion,{
		callback:function(data) {
			if(data!=null) {
				dwr.util.addOptions("stateId", data,'stateCode','stateName');
			}
			if(data!=null && data.length==1 ) {
				 $("#statetr").fadeIn(500);
				 $("#stateId").val( data[0].stateCode );
			}
           
        }
       ,preHook:function() {$(".process").fadeIn(500); },
        postHook:function() { $(".process").fadeOut();},
        timeout:50000,
        async:false,
        errorHandler:function(message) { alert("Oops: " + message);} 
	});
	
}*/





