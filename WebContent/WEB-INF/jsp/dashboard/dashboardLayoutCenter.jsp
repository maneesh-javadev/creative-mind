<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../common/taglib_includes.jsp"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>LOCAL GOVERNMENT DIRECTORY| Dashboard</title>
        

	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/jquery-3.1.0.min.js"></script> 
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/common-resource/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/common-resource/bootstrap-theme.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/bootstrap.min.js"></script> 
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->

	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/common-resource/jquery-migrate-3.0.1.min.js"></script> 
     <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/plugins/datatables/dataTables.bootstrap.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/fonts/font-awesome.min.css">
    
    <!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/dist/css/dashboard.min.css">
    <!--  Theme Skins -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <!-- Back To Top  -->
    <div class="totop" ></div>
    
<tiles:insertAttribute name="dbtopHeader" ignore="true"  />
<!-- Content Wrapper. Contains page content -->
<div class="wrapper">
	
	 <tiles:insertAttribute name="dbmainHeader" ignore="true"  />
	<tiles:insertAttribute name="dbcontentBody" ignore="true"  />
	
	<%-- <div class="content-wrapper">
		
		<tiles:insertAttribute name="dbcontentBody" ignore="true"  />
	</div> --%>
</div>
<tiles:insertAttribute name="dbfooter" ignore="true"  />

 <!-- jQuery 2.1.4 -->
   
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    
    <!-- Bootstrap 3.3.7 -->
    <script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>

    <!-- Slimscroll -->
    <script src="resource/dashboard-resource/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="resource/dashboard-resource/plugins/fastclick/fastclick.min.js"></script>
    <!-- Dashboard App -->
    <script src="resource/dashboard-resource/dist/js/app.min.js"></script>

	<script src="resource/dashboard-resource/dist/js/plugin.js"></script>
<!-- 	
	 JS for CK Editor
 	<script src="resource/dashboard-resource/plugins/ckeditor/ckeditor.js"></script> -->
 	

    
 
    <!-- Select2 -->
    <script src="resource/dashboard-resource/plugins/select2/select2.full.min.js"></script>
	<!-- DatePicker -->
    <script src="resource/dashboard-resource/plugins/datepicker/bootstrap-datetimepicker.min.js"></script>
    <!-- JS For Other Application dropdown -->

	<script>
	// to open dropdown
	$(document).on('ready', function(){
      $(".button").on('click', function() { 
        $("div").removeClass("hide");
       }); 
    });
	$(document).ready(function(){
	  
       
	  var scroll = false;
	  var launcherMaxHeight = 396;
	  var launcherMinHeight = 296;
	  
	 
	  // Mousewheel event handler to detect whether user has scrolled over the container
	  $('.apps').bind('mousewheel', function(e){
			if(e.originalEvent.wheelDelta /120 > 0) {
			  // Scrolling up
			}
			else{
				// Scrolling down
				if(!scroll){
					$(".second-set").show();
					$('.apps').css({height: launcherMinHeight}).addClass('overflow');
					scroll =true; 
					$(this).scrollTop(e.originalEvent.wheelDelta);
				}
			}
		});
	  
	  // Scroll event to detect that scrollbar reached top of the container
	  $('.apps').scroll(function(){
		var pos=$(this).scrollTop();
		if(pos == 0){
			scroll =false;
			$('.apps').css({height: launcherMaxHeight}).removeClass('overflow');
			$(".second-set").hide();
		}
	  });
	  
	  // Click event handler to show more apps
	  $('.apps .more').click(function(){
		$(".second-set").removeClass("hide");
		$(".apps").animate({ scrollTop: $('.apps')[0].scrollHeight}).css({height: 296}).addClass('overflow');
	  });
	  
	  // Click event handler to toggle dropdown
	  $(".button").click(function(event){
		event.stopPropagation();
		$(".app-launcher").toggle();
	  });
	  
	  $(document).click(function() {    
		
		//Hide the launcher if visible
		$('.app-launcher').hide();
		});
		
		// Prevent hiding on click inside app launcher
		$('.app-launcher').click(function(event){
			event.stopPropagation();
		});
  
});

// Resize event handler to maintain the max-height of the app launcher
$(window).resize(function(){
		$('.apps').css({maxHeight: $(window).height() - $('.apps').offset().top});
});

// For Top scrolling button              -->
$(function () {
$(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
        $('.totop').fadeIn();
    } else {
        $('.totop').fadeOut();
    }
});

// scroll body to 0px on click
$('.totop').click(function () {
    $('body,html').animate({
        scrollTop: 0
    }, 1600);
    return false;
});
});

</script>
    
</body>