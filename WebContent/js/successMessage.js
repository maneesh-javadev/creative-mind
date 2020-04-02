$(document).ready(function() {

	var successMsg = document.getElementById("successMsg");
	var failurMsg = document.getElementById("failurMsg");
	if ((successMsg != null && successMsg.value != "") || (failurMsg != null && failurMsg.value != "")) {
		$('#overlay1').fadeIn('fast', function() {
			$('#box1').animate({
				'top' : '160px'
			}, 50);
		});
		$('#boxclose1').click(function() {

			$('#box1').animate({
				'top' : '-200px'
			}, 50, function() {
				$('#overlay1').fadeOut('fast');
			});

		});
	}

	// $(function() {

});

function showClientSideError() {

	$('#overlay1').fadeIn('fast', function() {
		$('#box').animate({
			'top' : '160px'
		}, 50);
	});

	$('#boxclose').click(function() {
		$('#box').animate({
			'top' : '-200px'
		}, 50, function() {
			$('#overlay1').fadeOut('fast');
		});

	});

}

function showNoChaneClientSideError() {

	$('#overlay1').fadeIn('fast', function() {
		$('#noChangeBox').animate({
			'top' : '160px'
		}, 50);
	});

	$('#noChangeboxclose').click(function() {
		$('#noChangeBox').animate({
			'top' : '-200px'
		}, 50, function() {
			$('#overlay1').fadeOut('fast');
		});

	});

}