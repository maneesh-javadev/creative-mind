<!DOCTYPE html>

<html lang="en-US" class="dyn detail-main-page dir-ltr contents fp-enabled" dir="ltr" style="overflow: visible; height: initial;">
<%@ taglib	uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld"	prefix="csrf"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<%@include file="../common/taglib_includes.jsp"%>
<%-- <% String contextpthval = request.getContextPath(); %> --%>
<script src='<%=contextpthval %>/dwr/interface/lgdDwrInitialService.js'></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LGD - Local Government Directory, Government of India</title>
    <meta content="Local Government Directory, Government of India" name="description">
    <meta content="" name="keywords">
	
	<link href="<%=contextpthval %>/resource/homebody-resource/css/bootstrap.min.css" rel="stylesheet">
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  CSS -->
    <link href="<%=contextpthval %>/resource/homebody-resource/css/lgd.css" rel="stylesheet">
	
  


    <!-- Bootstrap Core JavaScript -->
  <!--   <script src="resource/homebody-resource/js/bootstrap.min.js"></script> -->

    <script>
        (function(d) {
            d.className = "dyn" + d.className.substring(6, d.className.length);
        })(document.documentElement);
    </script>
    <script>
        window.isRTL = false;
    </script>

    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="57x57" href="images/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="images/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="images/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="images/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="images/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="images/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="images/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192" href="images/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="images/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="images/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon/favicon-16x16.png">
    <link rel="manifest" href="favicon/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="images/favicon/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <!--FontAwesome CSS-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    
    <%-- <link href='<%=contextpthval %>/resource/dashboard-resource/bootstrap/css/font-face.css' rel='stylesheet' type='text/css'> --%>
    <!--JQuery-->
   <!--  <script src="resource/homebody-resource/js/jquery.min.js"></script> -->
    
	<style>
	.modal-open .modal {
	    overflow-x: auto !important;
	   
	}
	</style>
</head>
<body ng-app="app" block-ui="main" class="ng-scope block-ui block-ui-anim-fade fp-viewing-intro" aria-busy="false" style="overflow: visible; height: initial;">

	<!-- Back To Top  -->
    <div class="totop" ></div>
   
    <tiles:insertAttribute name="hpHeader" ignore="true"  />
    <tiles:insertAttribute name="hpcontentBody" ignore="true"  />
    <tiles:insertAttribute name="hpfooter" ignore="true"  />
    
    
    
    
    <!-- Supporting document Modal ends -->
	
	
              <script type="text/javascript" defer="">
            setTimeout(function() {
                var a = document.createElement("script");
                var b = document.getElementsByTagName("script")[0];
                //a.src = document.location.protocol + "//script.crazyegg.com/pages/scripts/0039/8964.js?" + Math.floor(new Date().getTime() / 3600000);
                a.defer = true;
                a.type = "text/javascript";
                b.parentNode.insertBefore(a, b)
            }, 1);
        </script>

        <!-- counter-->
        <script>
            $('.count').each(function() {
                $(this).prop('Counter', 0).animate({
                    Counter: $(this).text()
                }, {
                    duration: 4000,
                    easing: 'swing',
                    step: function(now) {
                        $(this).text(Math.ceil(now));
                    }
                });
            });
        </script>

    <!-- #####################################################################
   For Modal Login
   ##################################################################### -->
        <script>
            $(function() {

                var $formLogin = $('#login-form');
                var $formLost = $('#lost-form');
                var $divForms = $('#div-forms');
                var $modalAnimateTime = 300;
                var $msgAnimateTime = 150;
                var $msgShowTime = 2000;

                

                $('#login_lost_btn').click(function() {
                    modalAnimate($formLogin, $formLost);
                });
                $('#lost_login_btn').click(function() {
                    modalAnimate($formLost, $formLogin);
                });

                function modalAnimate($oldForm, $newForm) {
                    var $oldH = $oldForm.height();
                    var $newH = $newForm.height();
                    $divForms.css("height", $oldH);
                    $oldForm.fadeToggle($modalAnimateTime, function() {
                        $divForms.animate({
                            height: $newH
                        }, $modalAnimateTime, function() {
                            $newForm.fadeToggle($modalAnimateTime);
                        });
                    });
                }

                function msgFade($msgId, $msgText) {
                    $msgId.fadeOut($msgAnimateTime, function() {
                        $(this).text($msgText).fadeIn($msgAnimateTime);
                    });
                }

                function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
                    var $msgOld = $divTag.text();
                    msgFade($textTag, $msgText);
                    $divTag.addClass($divClass);
                    $iconTag.removeClass("glyphicon-chevron-right");
                    $iconTag.addClass($iconClass + " " + $divClass);
                    setTimeout(function() {
                        msgFade($textTag, $msgOld);
                        $divTag.removeClass($divClass);
                        $iconTag.addClass("glyphicon-chevron-right");
                        $iconTag.removeClass($iconClass + " " + $divClass);
                    }, $msgShowTime);
                }
            });
        </script>
		 <!--    For Top scrolling button              -->
    <script type="text/javascript">        
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