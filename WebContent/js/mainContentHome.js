
$(document).ready(function() {
	$("a[id^='dedicatedVideoLink']").bind({
		click : function() {
			$('#cbt-model').modal('hide')	;
						var link = $(this).attr( 'paramLink');
						var filename = $(this).attr('paramFName');
						var propKey = $(this).attr('param');
						lgdDwrInitialService.getSupportDownloadDoc(link,filename,propKey,{
	    					callback : function(data) {
	    					
	    						if(data!=null && data.length>0){
	        						docdetArr=data.split("#");
	        						
	        						$("#dynamicDocStructure").empty();
	        					 	var divDocTemplate = $("#dynamicDocStructure");
	        						 
	        						//------#Header Part started ----
	        						var modalHeadDiv = $("<DIV/>");
	        						modalHeadDiv.attr("class", "modal-header");
	        						
	        						var modalHeadButton = $("<BUTTON/>");
	        						modalHeadButton.attr("class", "close");
	        						modalHeadButton.attr("type", "button");
	        						modalHeadButton.attr("data-dismiss", "modal");
	        						modalHeadButton.html("&times;");
	        					
	        						var modalHeading = $("<H4/>");
	        						modalHeading.attr("class", "modal-title");
	        						modalHeading.html(docdetArr[0]);
	        						
	        						$(modalHeadDiv).append(modalHeadButton);
	        						$(modalHeadDiv).append(modalHeading);
	        						//------#Header Part end ----
	        						
	        						//------#body Part started ---
	        						var modalbodyDiv = $("<DIV/>");
	        						modalbodyDiv.attr("class", "modal-body");
	        						//-- last modified
	        						if(docdetArr[1]!=""){
	        							var p1=$("<P/>");
		        						            	        						
		        						/* var strong1=$("<STRONG/>");
		        						strong1.html("File Last Modified:");
		        						
		        						$(p1).append(strong1); */
		        						p1.html("<strong>File Last Modified:</strong>"+ " " + "24/04/2018");
		        						$(modalbodyDiv).append(p1);
	        						}
	        						//-- file description
	        						
									var p2=$("<P/>");
	        						
									/* var strong2=$("<STRONG/>");
	        						strong2.html("File Description:");
	        						
	        						$(p2).append(strong2); */
	        						p2.html("<strong>File Description:</strong>"+docdetArr[2]);
	        						$(modalbodyDiv).append(p2);
	        						//-- file type
	        						if(docdetArr[3]!=""){
									var p3=$("<P/>");
	        						
									/* var strong3=$("<STRONG/>");
	        						strong3.html("File Type:"); */
	        						
	        						
	        						/* 
	        						var i3=$("<I/>");
	        						i3.attr("class",docdetArr[4] );
	        						i3.attr("aria-hidden","true" ); */
	        						
	        						//$(p3).append(strong3);
	        						p3.html("<strong>File Type: </strong>"+docdetArr[3]+"<i class='"+docdetArr[4]+"' style='padding-left: 10px' aria-hidden='true'></i>");
	        						//$(p3).append(i3);
	        						$(modalbodyDiv).append(p3);
	        						}
	        						
	        						//-- play button
	        						if(docdetArr[3]=="" && docdetArr[1]=="")
	    							{
	        							var p4=$("<P/>");
	        							p4.attr("class","text-center");
	        							
	        							var a4=$("<A/>");
	        							a4.attr("href",docdetArr[5]);
	        							a4.attr("class","download btn btn-success");
	        							a4.attr("role","button");
	        							
	        							var i4=$("<I/>");
		        						i4.attr("class","fa fa-download" );
		        						i4.attr("aria-hidden","true" );
		        						
		        						$(a4).append(i4);
		        						a4.html("Play");
		        						$(p4).append(a4);
		        						$(modalbodyDiv).append(p4);
		        						
	    							}else{
	    								var p5=$("<P/>");
		        						
		        						/* var strong5=$("<STRONG/>");
		        						strong5.html("File size:");
		        						
		        						$(p5).append(strong5); */
		        						p5.html("<strong>File size:</strong>"+docdetArr[6]+" MB");
		        						
		        						
		        						var p4=$("<P/>");
	        							p4.attr("class","text-center");
	        							
	        							
	        							
//	             	        								http://localhost:8080/LGD/CBT/lgdLaunchVideo24thApril2018.mp4
	        								var a4=$("<iframe>");
	        								a4.attr("id","dedicatingVideo");
	        								a4.attr("src","<%=contextpthval %>/resource/lgdLaunchVideo/lgdLaunchVideo24thApril2018.mp4");
	        								a4.attr("width",450);
	        								a4.attr("height",280);
	        								a4.append("</iframe>");
	        							
	        							
	        							var i4=$("<I/>");
		        						i4.attr("class","fa fa-download" );
		        						i4.attr("aria-hidden","true" );
		        						
		        				
		        							$(a4).append(i4);
	    	        						a4.html("Play");
	    	        						$(p4).append(a4);
		        						
		        						
		        						$(modalbodyDiv).append(p5);
		        						$(modalbodyDiv).append(p4);
	    								
	    							}
	        						
	        						//------#body Part End ---
	        						//------#modal footer started ---
	        						var modalfootDiv = $("<DIV/>");
	        						modalfootDiv.attr("class", "modal-footer");
	        						
	        						var modalfootButton = $("<BUTTON/>");
	        						modalfootButton.attr("class", "btn btn-default");
	        						modalfootButton.attr("type", "button");
	        						modalfootButton.attr("data-dismiss", "modal");
	        						modalfootButton.attr("id", "modelCloseButtonId");
	        						modalfootButton.html("Close");
	        						
	        						$(modalfootDiv).append(modalfootButton);
	        						$(divDocTemplate).append(modalHeadDiv);
	        						$(divDocTemplate).append(modalbodyDiv);
	        						$(divDocTemplate).append(modalfootDiv);
	        					}	
	    					},
							errorHandler : function(
									errorString) {
								alert("EXCEPTION : "
										+ errorString);
							},
							async : true,

	                					});
	        							
	        							/* $("#myIframe").contents().find("body").html('');
	        							$('#myIframe').attr('src',"supportdownloaddoc.do?<csrf:token uri='supportdownloaddoc.do'/>&link="
														+ link+ "&filename="+ filename+ "&propkey="+ propKey); */
	        							$('#cbtdoc-model').modal('show')	
	        						}
	        						
	        		});
	         		
//	          	$("#modelCloseButtonId").on(click)
	         	



	$("a[id^='linkSupDoc']").bind({
		click : function() {
			$('#cbt-model').modal('hide')	;
						var link = $(this).attr( 'paramLink');
						var filename = $(this).attr('paramFName');
						var propKey = $(this).attr('param');
						lgdDwrInitialService.getSupportDownloadDoc(link,filename,propKey,{
	    					callback : function(data) {
	    					
	    						if(data!=null && data.length>0){
	        						docdetArr=data.split("#");
	        						
	        						$("#dynamicDocStructure").empty();
	        					 	var divDocTemplate = $("#dynamicDocStructure");
	        						 
	        						//------#Header Part started ----
	        						var modalHeadDiv = $("<DIV/>");
	        						modalHeadDiv.attr("class", "modal-header");
	        						
	        						var modalHeadButton = $("<BUTTON/>");
	        						modalHeadButton.attr("class", "close");
	        						modalHeadButton.attr("type", "button");
	        						modalHeadButton.attr("data-dismiss", "modal");
	        						modalHeadButton.html("&times;");
	        					
	        						var modalHeading = $("<H4/>");
	        						modalHeading.attr("class", "modal-title");
	        						modalHeading.html(docdetArr[0]);
	        						
	        						$(modalHeadDiv).append(modalHeadButton);
	        						$(modalHeadDiv).append(modalHeading);
	        						//------#Header Part end ----
	        						
	        						//------#body Part started ---
	        						var modalbodyDiv = $("<DIV/>");
	        						modalbodyDiv.attr("class", "modal-body");
	        						//-- last modified
	        						if(docdetArr[1]!=""){
	        							var p1=$("<P/>");
		        						            	        						
		        						/* var strong1=$("<STRONG/>");
		        						strong1.html("File Last Modified:");
		        						
		        						$(p1).append(strong1); */
		        						p1.html("<strong>File Last Modified:</strong>"+docdetArr[1]);
		        						$(modalbodyDiv).append(p1);
	        						}
	        						//-- file description
	        						
									var p2=$("<P/>");
	        						
									/* var strong2=$("<STRONG/>");
	        						strong2.html("File Description:");
	        						
	        						$(p2).append(strong2); */
	        						p2.html("<strong>File Description:</strong>"+docdetArr[2]);
	        						$(modalbodyDiv).append(p2);
	        						//-- file type
	        						if(docdetArr[3]!=""){
									var p3=$("<P/>");
	        						
									/* var strong3=$("<STRONG/>");
	        						strong3.html("File Type:"); */
	        						
	        						
	        						/* 
	        						var i3=$("<I/>");
	        						i3.attr("class",docdetArr[4] );
	        						i3.attr("aria-hidden","true" ); */
	        						
	        						//$(p3).append(strong3);
	        						p3.html("<strong>File Type: </strong>"+docdetArr[3]+"<i class='"+docdetArr[4]+"' style='padding-left: 10px' aria-hidden='true'></i>");
	        						//$(p3).append(i3);
	        						$(modalbodyDiv).append(p3);
	        						}
	        						
	        						//-- play button
	        						if(docdetArr[3]=="" && docdetArr[1]=="")
	    							{
	        							var p4=$("<P/>");
	        							p4.attr("class","text-center");
	        							
	        							var a4=$("<A/>");
	        							a4.attr("href",docdetArr[5]);
	        							a4.attr("class","download btn btn-success");
	        							a4.attr("role","button");
	        							
	        							var i4=$("<I/>");
		        						i4.attr("class","fa fa-download" );
		        						i4.attr("aria-hidden","true" );
		        						
		        						$(a4).append(i4);
		        						a4.html("Play");
		        						$(p4).append(a4);
		        						$(modalbodyDiv).append(p4);
		        						
	    							}else{
	    								var p5=$("<P/>");
		        						
		        						/* var strong5=$("<STRONG/>");
		        						strong5.html("File size:");
		        						
		        						$(p5).append(strong5); */
		        						p5.html("<strong>File size:</strong>"+docdetArr[6]+" MB");
		        						
		        						
		        						var p4=$("<P/>");
	        							p4.attr("class","text-center");
	        							
	        							
	        							
	        							var a4=$("<A/>");
	        							a4.attr("href",docdetArr[5]);
	        							a4.attr("class","download btn btn-success");
	        							a4.attr("role","button");
	        							
	        							var i4=$("<I/>");
		        						i4.attr("class","fa fa-download" );
		        						i4.attr("aria-hidden","true" );
		        						
		        						$(a4).append(i4);
		        						a4.html("Download File");
		        						$(p4).append(a4);
		        						
		        						$(modalbodyDiv).append(p5);
		        						$(modalbodyDiv).append(p4);
	    								
	    							}
	        						
	        						//------#body Part End ---
	        						//------#modal footer started ---
	        						var modalfootDiv = $("<DIV/>");
	        						modalfootDiv.attr("class", "modal-footer");
	        						
	        						var modalfootButton = $("<BUTTON/>");
	        						modalfootButton.attr("class", "btn btn-default");
	        						modalfootButton.attr("type", "button");
	        						modalfootButton.attr("data-dismiss", "modal");
	        						modalfootButton.html("Close");
	        						
	        						$(modalfootDiv).append(modalfootButton);
	        						$(divDocTemplate).append(modalHeadDiv);
	        						$(divDocTemplate).append(modalbodyDiv);
	        						$(divDocTemplate).append(modalfootDiv);
	        					}	
	    					},
							errorHandler : function(
									errorString) {
								alert("EXCEPTION : "
										+ errorString);
							},
							async : true,

						});
						
						/* $("#myIframe").contents().find("body").html('');
						$('#myIframe').attr('src',"supportdownloaddoc.do?<csrf:token uri='supportdownloaddoc.do'/>&link="
										+ link+ "&filename="+ filename+ "&propkey="+ propKey); */
						$('#cbtdoc-model').modal('show')	
					}
					
	});
});

function showDashboard(){
            	 $('#dashboardFrame').attr('src',  "gisFreezeUnfreezeStatusReport.do?<csrf:token uri='gisFreezeUnfreezeStatusReport.do'/>");
            	 $('#dashdialogBX').modal('show'); 
             }
             
 function gisStatewiseentitycount(){
	 $('#myIframeSummary').attr('src',  "getGISStatewiseEntitiesCount.do?<csrf:token uri='getGISStatewiseEntitiesCount.do'/>");
	 /*  $("#myIframe").load(function(){
		  hideLoadingImage(); 
   });  */
	$('#dialogBXSummary').modal('show'); 
 }
           //Accordian
  	function toggleIcon(e) {
      $(e.target)
          .prev('.panel-heading')
          .find(".more-less")
          .toggleClass('glyphicon-plus glyphicon-minus');
  }
             
function callToggle(){
	 $('.panel-group').on('hidden.bs.collapse', toggleIcon);
     $('.panel-group').on('shown.bs.collapse', toggleIcon);
}
			 
 
function showReports(){
	$('#reports-model').modal('show');
}
            
function showPMIno(){
	$('#pmino-model').modal('show');
}

function showCBT(){
	$('#cbt-model').modal('show');
}
            
function showFAQ() {
	
lgdDwrInitialService.getAvailableFAQs(1,{
	callback : function(data) {
		
	 $("#dynamicFAQStructure").empty();
	 	var divFAQTemplate = $("#dynamicFAQStructure");
		 
		 
		 var pannelGroupDiv = $("<DIV/>");
		 pannelGroupDiv.attr("class", "panel-group");
		 pannelGroupDiv.attr("role", "tablist");
		 pannelGroupDiv.attr("aria-multiselectable", "true");
		 
		 
		 jQuery.each(data,function(index,obj) {
		 
		
			var pannelContentDiv = $("<DIV/>");
			pannelContentDiv.attr("class", "panel panel-default");
			
			
			var questionDiv = $("<DIV/>");
			questionDiv.attr("id", ("heading"+index));
			questionDiv.attr("class", "panel-heading");
			questionDiv.attr("role", "tab");
			
			var questionHead = $("<H4/>");
			questionHead.attr("class", "panel-title");
			
			var questionAnchor = $("<A/>");
			questionAnchor.attr("role", "tab");
			questionAnchor.attr("data-toggle", "collapse");
			questionAnchor.attr("data-parent", "#accordion");
			questionAnchor.attr("href", ("#collapse"+index));
			questionAnchor.attr("aria-expanded", "true");
			questionAnchor.attr("aria-controls", ("collapse"+index));
			
			var questionI = $("<I/>");
			questionI.attr("class", "more-less glyphicon glyphicon-plus");
			
			var qstn = obj.faqQuestionText.replace("<p>","").replace("</p>","");
			
			
			
			
			
			
			/* $(questionI).append(qstnLbl); */
			//$(questionAnchor).append(questionI);
			questionAnchor.html(qstn+"<i class='more-less glyphicon glyphicon-plus'></i> ");
			$(questionHead).append(questionAnchor);
			$(questionDiv).append(questionHead);
			
			var ansDiv = $("<DIV/>");
			ansDiv.attr("id", ("collapse"+index));
			ansDiv.attr("class", "panel-collapse collapse");
			ansDiv.attr("role", "tabpanel");
			ansDiv.attr("aria-labelledby", ("heading"+index));
			
			var ansPannel = $("<DIV/>");
			ansPannel.attr("class", "panel-body");
			
			ansPannel.html(obj.faqAnswerText);
			
			
			$(ansDiv).append(ansPannel);
			
			$(pannelContentDiv).append(questionDiv);
			$(pannelContentDiv).append(ansDiv);
			
			$(pannelGroupDiv).append(pannelContentDiv);
			
		 });
		 
		
		 
		
		 $(divFAQTemplate).append(pannelGroupDiv);
		 
		 callToggle();					
		
		
		
		},
		errorHandler : function(
				errorString) {
			alert("EXCEPTION : "
					+ errorString);
		},
		async : true,

	});

$('#faq-model').modal('show')	
}

          
		
				
function showoverview() {
    		
lgdDwrInitialService.getAvailableFAQs(1,{
	callback : function(data) {
		
		$("#dynamicFAQStructure").empty();
	 	var divFAQTemplate = $("#dynamicFAQStructure");
		 
		 
		 var pannelGroupDiv = $("<DIV/>");
		 pannelGroupDiv.attr("class", "panel-group");
		 pannelGroupDiv.attr("role", "tablist");
		 pannelGroupDiv.attr("aria-multiselectable", "true");
		 
		 
		 jQuery.each(data,function(index,obj) {
		 
		
			var pannelContentDiv = $("<DIV/>");
			pannelContentDiv.attr("class", "panel panel-default");
			
			
			var questionDiv = $("<DIV/>");
			questionDiv.attr("id", ("heading"+index));
			questionDiv.attr("class", "panel-heading");
			questionDiv.attr("role", "tab");
			
			var questionHead = $("<H4/>");
			questionHead.attr("class", "panel-title");
			
			var questionAnchor = $("<A/>");
			questionAnchor.attr("role", "tab");
			questionAnchor.attr("data-toggle", "collapse");
			questionAnchor.attr("data-parent", "#accordion");
			questionAnchor.attr("href", ("#collapse"+index));
			questionAnchor.attr("aria-expanded", "true");
			questionAnchor.attr("aria-controls", ("collapse"+index));
			
			var questionI = $("<I/>");
			questionI.attr("class", "more-less glyphicon glyphicon-plus");
			
			var qstn = obj.faqQuestionText.replace("<p>","").replace("</p>","");
			
			
			
			
			
			
			/* $(questionI).append(qstnLbl); */
			//$(questionAnchor).append(questionI);
			questionAnchor.html(qstn+"<i class='more-less glyphicon glyphicon-plus'></i> ");
			$(questionHead).append(questionAnchor);
			$(questionDiv).append(questionHead);
			
			var ansDiv = $("<DIV/>");
			ansDiv.attr("id", ("collapse"+index));
			ansDiv.attr("class", "panel-collapse collapse");
			ansDiv.attr("role", "tabpanel");
			ansDiv.attr("aria-labelledby", ("heading"+index));
			
			var ansPannel = $("<DIV/>");
			ansPannel.attr("class", "panel-body");
			
			ansPannel.html(obj.faqAnswerText);
			
			
			$(ansDiv).append(ansPannel);
			
			$(pannelContentDiv).append(questionDiv);
			$(pannelContentDiv).append(ansDiv);
			
			$(pannelGroupDiv).append(pannelContentDiv);
			
		 });
		 
		
		 
		
		 $(divFAQTemplate).append(pannelGroupDiv);
		 
		 callToggle();					
		
		
		
		},
		errorHandler : function(
				errorString) {
			alert("EXCEPTION : "
					+ errorString);
		},
		async : true,

	});

 $('#overview-model').modal('show')	
}
            



        	
function showAnoride() {
	var link ="cbt.htm?docType=AndroidLGD";
	var filename = "LGD Android";
	var propKey = "android";
	lgdDwrInitialService.getSupportDownloadDoc(link,filename,propKey,{
		callback : function(data) {
		
			if(data!=null && data.length>0){
				docdetArr=data.split("#");
				
				$("#dynamicDocStructure").empty();
			 	var divDocTemplate = $("#dynamicDocStructure");
				 
				//------#Header Part started ----
				var modalHeadDiv = $("<DIV/>");
				modalHeadDiv.attr("class", "modal-header");
				
				var modalHeadButton = $("<BUTTON/>");
				modalHeadButton.attr("class", "close");
				modalHeadButton.attr("type", "button");
				modalHeadButton.attr("data-dismiss", "modal");
				modalHeadButton.html("&times;");
			
				var modalHeading = $("<H4/>");
				modalHeading.attr("class", "modal-title");
				modalHeading.html(docdetArr[0]);
				
				$(modalHeadDiv).append(modalHeadButton);
				$(modalHeadDiv).append(modalHeading);
				//------#Header Part end ----
				
				//------#body Part started ---
				var modalbodyDiv = $("<DIV/>");
				modalbodyDiv.attr("class", "modal-body");
				//-- last modified
				if(docdetArr[1]!=""){
					var p1=$("<P/>");
					            	        						
					/* var strong1=$("<STRONG/>");
					strong1.html("File Last Modified:");
					
					$(p1).append(strong1); */
					p1.html("<strong>File Last Modified:</strong>"+docdetArr[1]);
					$(modalbodyDiv).append(p1);
				}
				//-- file description
				
				var p2=$("<P/>");
				
				/* var strong2=$("<STRONG/>");
				strong2.html("File Description:");
				
				$(p2).append(strong2); */
				p2.html("<strong>File Description:</strong>"+docdetArr[2]);
				$(modalbodyDiv).append(p2);
				//-- file type
				if(docdetArr[3]!=""){
				var p3=$("<P/>");
				
				/* var strong3=$("<STRONG/>");
				strong3.html("File Type:"); */
				
				
				/* 
				var i3=$("<I/>");
				i3.attr("class",docdetArr[4] );
				i3.attr("aria-hidden","true" ); */
				
				//$(p3).append(strong3);
				p3.html("<strong>File Type: </strong>"+docdetArr[3]+"<i class='"+docdetArr[4]+"' style='padding-left: 10px' aria-hidden='true'></i>");
				//$(p3).append(i3);
				$(modalbodyDiv).append(p3);
				}
				
				//-- play button
				if(docdetArr[3]=="" && docdetArr[1]=="")
				{
					var p4=$("<P/>");
					p4.attr("class","text-center");
					
					var a4=$("<A/>");
					a4.attr("href",docdetArr[5]);
					a4.attr("class","download btn btn-success");
					a4.attr("role","button");
					
					var i4=$("<I/>");
					i4.attr("class","fa fa-download" );
					i4.attr("aria-hidden","true" );
					
					$(a4).append(i4);
					a4.html("Play");
					$(p4).append(a4);
					$(modalbodyDiv).append(p4);
					
				}else{
					var p5=$("<P/>");
					
					/* var strong5=$("<STRONG/>");
					strong5.html("File size:");
					
					$(p5).append(strong5); */
					p5.html("<strong>File size:</strong>"+docdetArr[6]+" MB");
					
					
					var p4=$("<P/>");
					p4.attr("class","text-center");
					
					
					
					var a4=$("<A/>");
					a4.attr("href",docdetArr[5]);
					a4.attr("class","download btn btn-success");
					a4.attr("role","button");
					
					var i4=$("<I/>");
					i4.attr("class","fa fa-download" );
					i4.attr("aria-hidden","true" );
					
					$(a4).append(i4);
					a4.html("Download File");
					$(p4).append(a4);
					
					$(modalbodyDiv).append(p5);
					$(modalbodyDiv).append(p4);
					
				}
				
				//------#body Part End ---
				//------#modal footer started ---
				var modalfootDiv = $("<DIV/>");
				modalfootDiv.attr("class", "modal-footer");
				
				var modalfootButton = $("<BUTTON/>");
				modalfootButton.attr("class", "btn btn-default");
				modalfootButton.attr("type", "button");
				modalfootButton.attr("data-dismiss", "modal");
				modalfootButton.html("Close");
				
				$(modalfootDiv).append(modalfootButton);
				$(divDocTemplate).append(modalHeadDiv);
				$(divDocTemplate).append(modalbodyDiv);
				$(divDocTemplate).append(modalfootDiv);
			}	
		},
		errorHandler : function(
				errorString) {
			alert("EXCEPTION : "
					+ errorString);
		},
		async : true,

	});
	
	
	$('#cbtdoc-model').modal('show')
};
    			
var viewDishaHelpInPopup = function ()	{
	
	$('#myIframe').attr('src',  "showDishaHelp.htm?<csrf:token uri='showDishaHelp.htm'/>");
	$('#myModal').modal('show'); 
};

var viewAnnouncementInPopup = function ()	{
	$("#announcement-dialogBXTitle").text("Announcement");
	$("#SoftwareUpdatesdialogBXbody").hide();
	$("#AnnouncementsdialogBXbody").show();
	$('#dialogBXAnnouncement').modal('show'); 
};

var viewSoftwareUpdateInPopup = function ()	{
	$("#announcement-dialogBXTitle").text("Software Updates");
	$("#SoftwareUpdatesdialogBXbody").show();
	$("#AnnouncementsdialogBXbody").hide();
	$('#dialogBXAnnouncement').modal('show'); 
};

/**
 * This Method is Use for External User 
 * @param loginForm
 * @author Maneesh Kumar
 * @since 01-10-2019
 * @return
 */
var viewExternalLoginPopup = function() {
	$('#loginPopupFrame').attr('src', "getExternalLogin.do?x=nonpopup");
	$('#login-modal').modal('show')	
};

/**
 * end External User
 */


		
