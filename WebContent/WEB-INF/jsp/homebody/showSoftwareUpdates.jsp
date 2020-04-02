<link href="${pageContext.request.contextPath}/resource/homebody-resource/css/announcement.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="table-responsive ">
<table>

<tr>
<td class="firstColSA">
<i class="fa fa-desktop fa-2x modal-color" aria-hidden="true"></i>
</td>
<td class="secondColSA">
<div class="datehome">
<div class="date_mm">31 July</div>
<div class="date_dd">2019</div>
</div>
</td>
<td class="thirdColSA">
<div>
<ol>
<li>You can track each and every modification in Ward.</li>
<li>LGD is providing the facility to restore the invalidated Wards.</li>
<li>You can update the Ward Name in Local Language.</li>
<li> Coverage of the ward has been handled in the same way of Local Body with versions. </li>
</ol>

</div>
</td>

</tr>

</table>
</div>
<script>
var retrieveFile1 = function (filename) {
	$(window).attr("location","downloadAnnouncementFile.do?filename="+ filename+"&<csrf:token uri='downloadAnnouncementFile.do'/>");
}
</script>