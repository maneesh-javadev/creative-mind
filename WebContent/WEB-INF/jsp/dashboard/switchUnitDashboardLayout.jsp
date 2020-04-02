<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>LOCAL GOVERNMENT DIRECTORY| Dashboard</title>
    
    
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/dashboard-resource/bootstrap/css/bootstrap.min.css">
     
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
<tiles:insertAttribute name="dbmainHeader" ignore="true"  />
		<tiles:insertAttribute name="dbcontentHeader" ignore="true"  />
		<tiles:insertAttribute name="dbcontentBody" ignore="true"  />

<%-- <div class="wrapper">
	<tiles:insertAttribute name="dbmainHeader" ignore="true"  />
	<tiles:insertAttribute name="dbmainSlider" ignore="true"  />
	
	<div class="content-wrapper">
		<tiles:insertAttribute name="dbcontentHeader" ignore="true"  />
		<tiles:insertAttribute name="dbcontentBody" ignore="true"  />
	</div>
</div> --%>
<tiles:insertAttribute name="dbfooter" ignore="true"  />

 <!-- jQuery 2.1.4 -->
   
   
    <!-- Bootstrap 3.3.7 -->
    <script src="resource/dashboard-resource/bootstrap/js/bootstrap.min.js"></script>

    <!-- Slimscroll -->
    
	

 	
 	
 
    <!-- Select2 -->
   


</script>
    
</body>