<%!String ctx;%>
<%
	ctx = request.getContextPath();
%>

<script type='text/javascript' src='<%=ctx%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=ctx%>/dwr/util.js'></script>
<script type="text/javascript" language="javascript" src="<%=ctx%>/js/jsutil.js"></script>
<%-- <script type='text/javascript' language="javascript" src='<%=ctx%>/dwr/engine.js'></script>
<script type='text/javascript' language="javascript" src='<%=ctx%>/dwr/util.js'></script> --%>


<script type="text/javascript" language="javascript">

if (!navigator.onLine) {
document.body.innerHTML = 'Loading...';
window.location = 'logOutAction.html';
}

displayLoadingImage = function() {
	$.blockUI({ 
		theme:true,
		/* title: 'Loading...', */
		message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resource/dashboard-resource/dist/img/loader-lgd.gif'/></div>"
    }); 
	   
	};
	
	showLoadingImage = function() {
		 $.blockUI({ 
			theme:true,
			/* title: 'Loading...', */
			message: "<div style='text-align: center'><img  src='<%= request.getContextPath() %>/resource/dashboard-resource/dist/img/loader-lgd.gif'/></div>"
	    }); 
	};
	
	hideLoadingImage = function(){
		 $.unblockUI(); 
		
	};

dwr.engine.setPreHook(showLoadingImage);

dwr.engine.setPostHook(hideLoadingImage);

</script>