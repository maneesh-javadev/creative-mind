
        function scrollToTop() {
            jQuery('html, body').animate({
                scrollTop: 0
            }, 'slow');
        }
  

  $(function () {
    //Initialize Select2 Elements
    $(".select2").select2();

    

  });
	//Accordian
	function toggleIcon(e) {
    $(e.target)
        .prev('.panel-heading')
        .find(".more-less")
        .toggleClass('glyphicon-plus glyphicon-minus');
}
$('.panel-group').on('hidden.bs.collapse', toggleIcon);
$('.panel-group').on('shown.bs.collapse', toggleIcon);
/*//date picker
$('.date').datetimepicker({
	format : 'dd-mm-yyyy',
	startView : 'month',
	minView : 'month',
	autoclose : true,
	pickerPosition : "bottom-left"
});
*/

	
	//DataTables
 
    
     /* $(function () {
        $("#example1").DataTable();
        $('#example2').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      });*/
    

	// JS For Other Application dropdown 
	
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

 // JS for CK Editor 

 /* $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace('editor1')
    })*/
    
    
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
