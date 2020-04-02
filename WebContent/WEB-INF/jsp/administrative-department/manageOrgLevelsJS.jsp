<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrOrganizationService.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDesignationDwr.js'></script>
<script type="text/javascript" src="<%=contextpthval%>/resources-localbody/scripts/jquery.fileDownload.js"></script>
<%-- <script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextpthval%>/ckeditor/ckeditor_components.js"></script>
 --%>
<script src="js/govtorder.js"></script>
<style>
	/* This is the style for the trigger icon. The margin-bottom value causes the icon to shift down to center it. */
	.ui-datepicker-trigger {
		margin-left:1px;
		margin-top: 8px;
		margin-bottom: -3px;
	}
	.textHead{color: #3B5998;}
</style>
<script type='text/javascript'>
	var name="";
	var oDate = "";
	$(document).ready(function() {
		
		var stateCode = "${stateCode}";
		
		$("#orgType").change(function() {
			$('#olc').html('');
			$('#olc').append('<option value="">Select</option>');
			$('#orgLocatedLevelCode').html('');
			$('#orgLocatedLevelCode').append('<option value="">Select</option>');
			var olc = $("#olc");
			lgdDwrOrganizationService.getOrgbyTypeCodeAtlevel($(this).val(),1,stateCode, {
			async : true,
			callback : function(data) {
				var option = $("<option />");
				jQuery.each(data, function(index, obj) {
					option = $("<option />");
					option.val(obj.orgCode).text(obj.orgName);
					olc.append(option);
				});
				},
				errorHandler : function() {
					alert("No Data found!");
				}
			});
		});
		
		$("#olc").change(function() {
			$('#orgLocatedLevelCode').html('');
			$('#orgLocatedLevelCode').append('<option value="">Select</option>');
			$('#orgLocatedLevelCode').val("").attr("selected","selected");
			$("#orgName").val($('#olc option:selected').text());
			var orgLocatedLevelCode = $("#orgLocatedLevelCode");
			lgdAdminDepatmentDwr.getOrgLocatedLevelListExceptTop($(this).val(), {
				async : true,
				callback : function(data) {
					var option = $("<option />");
					jQuery.each(data, function(index, obj) {
						option = $("<option />");
						option.val(obj.orgLocatedLevelCode).text(obj.orgLevelSpecificName + " (" + obj.locatedLevel + " LEVEL)");
						orgLocatedLevelCode.append(option);
					});
				},
				errorHandler : function() {
					alert("No Data found!");
				}
			});
		});	
		
		$("#orgLocatedLevelCode").change(function() {
			lgdDesignationDwr.getOrgSpecificName($('#olc').val(),$(this).val(), {
				async : true,
				callback : function(data) {
					name = data;
				},
				errorHandler : function() {
				}
			});
		});
		
		<%--  $("#OrderDate").datepicker({
				dateFormat: 'dd-mm-yy',
				showOn: 'both',
				maxDate: '0',
				onSelect: function(selected) {
					oDate = selected;
					$('#EffectiveDate').val(selected);
					$('#EffectiveDate').datepicker("option","minDate", selected);
					$("#gazPubDatecr").datepicker("option","minDate", selected);
				},
				buttonImage: "<%=contextpthval%>/images/calender.gif",
				buttonImageOnly: true,
			});
		
		 $("#EffectiveDate").datepicker({
				dateFormat: 'dd-mm-yy',
				showOn: 'both',
				maxDate: '0',
				minDate: oDate,
				onSelect: function(selected) {
					$('#OrderDate').datepicker("option","maxDate", selected);
				},
				buttonImage: "<%=contextpthval%>/images/calender.gif",
				buttonImageOnly: true,
			});
		 
		 $("#gazPubDatecr").datepicker({
				dateFormat: 'dd-mm-yy',
				showOn: 'both',
				onSelect: function(selected) {
					var parsedDate = $.datepicker.parseDate( 'dd-mm-yy', selected );
					$('#OrderDate').datepicker("option","maxDate", selected);
					$('#EffectiveDate').datepicker("option","maxDate", selected);	
					if( parsedDate > new Date() ){
						$('#OrderDate, #EffectiveDate').datepicker("option","maxDate", '0');
					}
				},
				buttonImage: "<%=contextpthval%>/images/calender.gif",
				buttonImageOnly: true,
			}); --%>
		
		$('#btnFormActionSave').click(function() {
			$('.label').html('');
			var orgType = $('#orgType').val();
			var olc = $('#olc').val();
			var orgLocatedLevelCode = $('#orgLocatedLevelCode').val();
			var orgLevelSpecificName = $('#orgLevelSpecificName').val();
			var orderNo = $('#orderNo').val();
			var orderDate = $('#OrderDate').val();
			var effectiveDate = $('#EffectiveDate').val();
			if(orgType == "") {
				$('#err_orgType').html("Please select an Organization Type");
				return false;
			}else if(olc == "") {
				$('#err_olc').html("Please select an Organization");
				return false;
			}else if(orgLocatedLevelCode == "") {
				$('#err_orgLocatedLevelCode').html("Please select Organization Level that you want to be modified");
				return false;
			}else if(orgLevelSpecificName == "") {
				$('#err_orgLevelSpecificName').html("Please enter the new name of the Organization Level in English");
				return false;
			}else if(name == orgLevelSpecificName) {
				$('#err_orgLevelSpecificName').html("Please Rename the new name of the Organization Level in English");
				return false;
			}else{
				lgdDwrOrganizationService.checkExistOrgLevelSpecificName(parseInt(stateCode),orgLevelSpecificName,{
					async : true,
					callback : function(data) {
						if (data) {
							$('#err_orgLevelSpecificName').html("entered new Organization level specific Name Already Exist");
							return false;
						} else {
							var nameReg = /^[a-zA-Z][a-zA-Z.,()-\s\/]+$/;
							if (!nameReg.test(orgLevelSpecificName)){
							$('#err_orgLevelSpecificName').html("Please Enter Valid New Organization level specific Name");
							return false;
							}
						}
						if(orderNo != null) {
							var pattern = "[~#%&\!\@\$\^\*\_\+\`\=\{\}\\[\\]\|\:\;\'\"\<\>\?\,\]";
							var regularExpression = new RegExp(pattern);
							if (regularExpression.test(orderNo)){
								$('#err_orderNo').html("Please Enter Valid Order No.");
								return false;
								}
						}
						 if((orderNo == "") && (orderDate != "" || effectiveDate != "")) {
							alert("Govt. Order Details can't be stored with out order no.Plz enter order No.");
							$('#orderNo').focus();
							return false;
						} 
						if(orderNo != "" && orderDate == "") {
							var today = new Date();
						    var dd = today.getDate();
						    var mm = today.getMonth()+1; //January is 0!
						    var yyyy = today.getFullYear();
						    if(dd<10){
						        dd='0'+dd;
						    } 
						    if(mm<10){
						        mm='0'+mm;
						    } 
						    var today = dd+'-'+mm+'-'+yyyy;
							$('#OrderDate').val(today);
						}
						if(orderNo != "" && effectiveDate == "") {
							var today = new Date();
						    var dd = today.getDate();
						    var mm = today.getMonth()+1; //January is 0!
						    var yyyy = today.getFullYear();
						    if(dd<10){
						        dd='0'+dd;
						    } 
						    if(mm<10){
						        mm='0'+mm;
						    } 
						    var today = dd+'-'+mm+'-'+yyyy;
							$('#EffectiveDate').val(today);
						}
						if($('#OrderDate').val() != "" && $('#EffectiveDate').val() != "") {
							if($('#OrderDate').val() > $('#EffectiveDate').val()) {
								$('#err_orderDate').html("Order Date should be less than equal to Effective Date.");
								$('#err_effectiveDate').html("Effective Date should be greater than or equal to order date.");
								return false;
							}
						}
							document.forms['manageOrgLevels'].method = "POST";
							document.forms['manageOrgLevels'].action = "manageOrgLevels.htm?<csrf:token uri='"manageOrgLevels.htm'/>";
							document.forms['manageOrgLevels'].submit();
					},
				errorHandler:function() { alert("Error"); }
				});
			}
			
		});
		 
		$('#btnFormActionClear').click(function() {
			 $('#orgType').val("").attr("selected","selected");
			 $('#olc').val("").attr("selected","selected");
			 $('#orgLocatedLevelCode').val("").attr("selected","selected");
			 $('#orgLevelSpecificName').val('');
			 $('#orderNo').val('');
			 $('#OrderDate').val('');
			 $('#EffectiveDate').val('');
			 $('#gazPubDatecr').val('');
			 $('#attachFile').val('');
		 });
	});
</script>