<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
	<%@include file="../common/taglib_includes.jsp"%> 
	<script src="<%=contextPath%>/css_js_files/bs_files/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css_js_files/bs_files/css/bootstrap.min.css">
	
	<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdAdminDepatmentDwr.js'></script>

	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 
  <style>
  #draggable { width: 150px; height: 150px; padding: 0.5em; }
	<style type="text/css">
		.extab{
			margin: 0px 0px -29px 0px;		
		}
		*{
			box-sizing: content-box;
		}
		#mainnav_logout{
			padding: 1px 35px 1px 3px;
		}
		.canvas{
			position:absolute;
			margin:-20px 0px 0px 550px;
			display: inline-block;
			width:100%
		}
	.nodeCon{
		margin: 0 0 0 -12px;
		padding: 3px 12px 1px 15px;
		text-align: center;
		position:relative;
	}
	.nodeText{
		border: 1px solid;
		font-size: 15px;
		font-weight: bold;
		padding:0px 20px 0px 20px;
		text-decoration:none !important;
		outline:none !important;
		-moz-border-radius: 15px;
		-webkit-border-radius: 15px;
		-moz-box-shadow: 5px 5px 15px #000;
		-webkit-box-shadow: 5px 5px 15px #000;
		box-shadow: 5px 5px 15px #000;
		background: #88b7d5 none repeat scroll 0 0;
    	border: 4px solid #c2e1f5;
    	display: inline-block;
    	margin:0px 625px 0px 0px;
	}
	.nodeImg{
		margin:0 0 0 77px;
		padding:0 0 0 0;
	}
	.add_text{
		margin:0 0 0 0;
		padding:5px 5px 9px 3px;
		text-align:center;
		height: 35px;
		width:85%;
		border:5px solid #c2e1f5 !important;
		text-transform:capitalize;
		outline:none !important;
	}
	.cancel{
		margin:60px -175px 12px 9px;
		/* padding: 50px 0 0 2px; */
		width:5%;
	}
	.confirmIMG{
		margin:-35px -35px 0 0;
		width:5%;
	}
	.hiddenNodeCon{
		margin:-20px 575px -24px -202px;;
		display:none;
		position:relative;
	}
	.frmpnlbrdr{
		height:600px;
	}
	.dowmImg{
		width:38px;
		height:80px;
	}
	.traingle_tool_tip {
	    background: #88b7d5 none repeat scroll 0 0;
	    border: 4px solid #c2e1f5;
	    display: none;
	    height: 20%;
	    left: -190px;
	    line-height: 1;
	    margin: -52px 0 0 585px;
	    padding: 3px 0 0 5px;
	    position: absolute;
	    text-decoration: none !important;
	    top: 27px;
	    width: 51%;
	    /*  */
	}
	.traingle_tool_tip::before {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #c2e1f5 transparent transparent #c2e1f5;
	    border-image: none;
	    border-style: solid;
	    border-width: 11px;
	    content: " ";
	    height: 0;
	    left: 25px;
	    position: absolute;
	    top: 25px;
	    width: 0;
	    /*  */
	}
	.addNodeInactive{
		display:none;
	}
	/* .add_text:hover a.traingle_tool_tip{
		display:block;
	}  */
	</style>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><h5>Manage Region Hierarchy</h5></td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<div class="row">
				<div class="form-group">
					<div class="col-sm-4"><div class="col-sm-12">
						<select id="org" class="ui-widget-content orgLGD" name="org" style="width:70%;height:30%;">
							<option value="0">Select</option>
							<option value="1">Division</option>
							<option value="2">Sub-Division</option>
							<option value="3">Block</option>
							<option value="4">Urban Block</option>
							<option value="5">Rural Block</option>
						</select>
					</div></div>
					<div class="col-sm-8"><div class="col-sm-12">
						<div class="canvas" id="canvas">
							<div class="nodeCon">
								<a href="javascript:void(0);" class="nodeText">State</a>
								<p class="nodeImg"><img src="<%=contextPath%>/images/down_image_icon/down_img_icon3.png"  class="dowmImg"/></p>
								<div class="hiddenNodeCon" data-child-attr="1c">
									<input type="text" name="inputNodeText" class="add_text" placeholder="Add Field">
									<img src="<%=contextPath%>/images/tick.png" class="confirmIMG" />
									<img src="<%=contextPath%>/images/cross.png" class="cancel"/>
									<a class="traingle_tool_tip">Please enter name of the <b>Label</b></a>
								</div>
							</div>
							<div class="nodeCon">
								<a href="javascript:void(0);" class="nodeText">District</a>
								<p class="nodeImg"><img src="<%=contextPath%>/images/down_image_icon/down_img_icon3.png" class="dowmImg"/></p>
								<div class="hiddenNodeCon" data-child-attr="2c">
									<input type="text" name="inputNodeText" class="add_text" placeholder="Add Field"/>
									<img src="<%=contextPath%>/images/tick.png" class="confirmIMG" />
									<img src="<%=contextPath%>/images/cross.png" class="cancel"/>
									<a class="traingle_tool_tip">Please enter name of the <b>Label</b></a>
								</div>
							</div>
							<div class="nodeCon">
								<a href="javascript:void(0);" class="nodeText">Sub-District</a>
								<p class="nodeImg"><img src="<%=contextPath%>/images/down_image_icon/down_img_icon3.png" class="dowmImg"/></p>
								<div class="hiddenNodeCon" data-child-attr="3c">
									<input type="text" name="inputNodeText" class="add_text" placeholder="Add Field"/>
									<img src="<%=contextPath%>/images/tick.png" class="confirmIMG" />
									<img src="<%=contextPath%>/images/cross.png" class="cancel"/>
									<a class="traingle_tool_tip">Please enter name of the <b>Label</b></a>
								</div>
							</div>
							<div class="nodeCon">
								<a href="javascript:void(0);" class="nodeText">Village</a>
							</div>
						</div>
					</div></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
	$('document').ready(function(){
		$('#org').hide();
		$('body').on('click','.nodeText',function(){
			var elm=$(this);
			if(!$(this).parent().children('.hiddenNodeCon').hasClass('addNodeInactive')){
				if($(this).parent().children().hasClass('hiddenNodeCon')){
					$(this).parent().find('.hiddenNodeCon').css("display","block");
					elm.parent().addClass('activeNode');
				}
			}
			$('#org').show();
			$('select#org').find('option[value="0"]').prop('selected', true);
			$('#canvas').find('div').each(function(){
			    if( !$(this).is(".activeNode")){
			    	$(this).children('.hiddenNodeCon').addClass('addNodeInactive');
			    }
			});
		});
		$('body').on('click','.cancel',function(){
			$(this).closest('.hiddenNodeCon').find('.traingle_tool_tip').css("display","none");
			$(this).closest('.nodeCon').find('.hiddenNodeCon').css("display","none");
			$('#org').hide();
			$('select#org').find('option[value="0"]').prop('selected', true);
			$('#canvas').find('div').each(function(){
			    if( !$(this).is(".activeNode")){
			    	$(this).children('.hiddenNodeCon').removeClass('addNodeInactive');
			    }
			});
			$(this).parent().children('.add_text').val('');
			$(this).parent().parent().removeClass('activeNode');
		});
		$('body').on('click','.confirmIMG',function(){
			if($(this).parent().find('.add_text').val().length == 0){
				$(this).closest('.hiddenNodeCon').find('.traingle_tool_tip').css("display","block");
			}else{
				var _newNodeElement="<div class='nodeCon'><a href='javascript:void(0);' class='nodeText'>"+$(this).parent().find('.add_text').val()+"</a>"+
									'<p class="nodeImg"><img src="<%=contextPath%>/images/down_image_icon/down_img_icon3.png"  class="dowmImg"/></p>'+
									"<div class='hiddenNodeCon'><input type='text' name='inputNodeText' class='add_text' placeholder='Add Field'/>"+
									'<img src="<%=contextPath%>/images/tick.png" class="confirmIMG" /><img src="<%=contextPath%>/images/cross.png" class="cancel"/>'+
									"<a class='traingle_tool_tip'>Please enter name of the <b>Label</b></a></div></div>";
				$(this).parent().hide();
				$(this).closest('.hiddenNodeCon').find('.traingle_tool_tip').css("display","none");
				$(this).parent().find('.add_text').val('');
				$(this).parent().closest('.nodeCon').after(_newNodeElement);
				$('#org').hide();
				$('#canvas').find('div').each(function(){
				    if( !$(this).is(".activeNode")){
				    	$(this).children('.hiddenNodeCon').removeClass('addNodeInactive');
				    }
				});
				$(this).parent().parent().removeClass('activeNode');
			}
		});
		$('body').on('focus','.add_text',function(){
			if($(this).parent().find('.traingle_tool_tip').is(':visible')){
				$(this).parent().find('.traingle_tool_tip').css("display","none");
			}
		});
		$('body').on('blur','.add_text',function(){
			var _val = $(this).val();
			var temp=_val.split(" ");
			var _txt="";
			for(var i=0;i<temp.length;i++){
				_txt+=temp[i].charAt(0).toUpperCase()+temp[i].slice(1).toLowerCase();
				if(i < temp.length-1)
					_txt+=" ";
			}
			$(this).val(_txt);
		});
	});	
	$('body').on('click','#org',function(){
		$('.activeNode').children('.hiddenNodeCon').find('.add_text').val($('#org option:selected').text());
	});
</script>
