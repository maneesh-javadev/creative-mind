<html>

<%@page language="java"	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<title>${Url} </title>
<%-- <%@include file="css_javascript.jsp"%> --%>
<%-- <%@include file="include/include.jsp"%> --%>
<%@include file="taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<script type="text/javascript" src="<%=contextPath%>/media/help/swfobject.js"></script>
<script type="text/javascript">

    var flashvars = {
    		autostart:"false",
    		thumb:"<%=contextPath%>/media/help/${Foldermapping}/FirstFrame.png",
    		thumbscale:"45",
    		color:"0x000000,0x000000"
    };  
    var params = {
        menu: "false",
        scale: "showall",
        wMode: "opaque",
        allowfullscreen: "true",
        allowscriptaccess: "always",
        quality: "best",
        bgcolor: "#1a1a1a"
        
    };
    swfobject.embedSWF("<%=contextPath%>/media/help/${Foldermapping}/${Filename}", "swf", "400", "400", "9.0.0",
                       "<%=contextPath%>/media/help/expressInstall.swf", flashvars, params);

    $(function() {
        var swf = $("#swf");
        $("body").append(
            $("<div onclick=\"window.open('http://www.google.com');\"></div>").
                css({
                    position: 'absolute',
                    top: swf.offset().top,
                    left: swf.offset().left,
                    width: swf.attr('width'),
                    height: swf.attr('height'),
                    zIndex: 1
                })
        );
    });
</script>
</head>
<body>
<div id="swf"></div>
</body>
</html>
