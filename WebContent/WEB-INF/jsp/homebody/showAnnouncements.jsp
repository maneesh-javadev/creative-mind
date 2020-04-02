<link href="${pageContext.request.contextPath}/resource/homebody-resource/css/announcement.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <div class="table-responsive ">
<table>
<tr>
<td class="firstCol">
<i class="fa fa-bullhorn fa-2x modal-color" aria-hidden="true" ></i>
</td>
<td class="secondCol">
<div class="datehome">
<div class="date_mm">31 Jul</div>
<div class="date_dd">2019</div>
</div>
</td>
<td class="thirdCol">
<div >
<a href="#">
Existing list of Sub-districts of Tripura have been replaced with 23 Sub-divisions of Revenue Department</a>
<a href="#"></a></div>

</td>
<td class="fourthCol">
<a href="#" onclick="retrieveFile1('LGD_LETTER.pdf')"  >View File</a>
</td>
</tr>

<tr>
<td class="firstCol">
<i class="fa fa-bullhorn fa-2x modal-color" aria-hidden="true" ></i>
</td>
<td class="secondCol">
<div class="datehome">
<div class="date_mm">18 Oct</div>
<div class="date_dd">2019</div>
</div>
</td>
<td class="thirdCol">
<div >
<a href="#">
Change in the base url of the APIs in the Citizen section.Current base url will be operational till 30th November 2019</a>
<a href="#"></a></div>

</td>
<td class="fourthCol">

</td>
</tr>


</table>
</div>
<script>
var retrieveFile1 = function (filename) {
	$(window).attr("location","downloadAnnouncementFile.do?filename="+ filename+"&<csrf:token uri='downloadAnnouncementFile.do'/>");
}
</script>