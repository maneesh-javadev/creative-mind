<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="in.nic.pes.common.menu.pojo.UserSelection"%>
<%@page import="java.util.Properties,java.util.List" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
UserSelection userSelection=(UserSelection) session.getAttribute("USERS_SELECTION");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>

<style type="text/css">
.linkdis {    
    color: #999;
    cursor: hand;
    text-decoration: none;
}
.tooltip {
	display:none;
	position:absolute;
	border:1px solid #333;
	background-color:#161616;
	border-radius:5px;
	padding:10px;
	color:#fff;
	font-size:12px Arial;	
}
.tooltip:before{
	content:"";
	width: 0;
	height: 0; 
	border-top: 8px solid transparent; 
	border-right: 8px solid #161616; 
	border-bottom: 8px solid transparent;
	left:-6px;
	top:50%;
	margin-top:-9px;
	position:absolute;
}
</style>

<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
$(document).ready(function() {
    // Tooltip only Text
    $('.masterTooltip').hover(function(){
            // Hover over code
            var title = $(this).attr('title');
            $(this).data('tipText', title).removeAttr('title');
            $('<p class="tooltip"></p>')
            .text(title)
            .appendTo('body')
            .fadeIn('slow');
    }, function() {
            // Hover out code
            $(this).attr('title', $(this).data('tipText'));
            $('.tooltip').remove();
    }).mousemove(function(e) {
            var mousex = e.pageX + 20; //Get X coordinates
            var mousey = e.pageY + 10; //Get Y coordinates
            $('.tooltip')
            .css({ top: mousey, left: mousex })
    });
});
</script>
</head>
<body>
	<div id="span_1_0" class="tblclear coltab">
		<div class="collapse_menu"></div>
	</div>
	<div class="extab">
		<img id="collapseImg" align="left" alt="" src="images/collapse.jpg" onclick="hideWrraper();" />
	</div>

	<div id="panel" class="pnlsize">
		<div class="arrowlistmenu">
			<c:forEach items="${menuList.menuProfile}" var="itm">
				<c:choose>
					<c:when test="${itm.parent eq 0}">
						<h3 class="menuheader expandable">${itm.resourceId}</h3>
						<ul class="categoryitems">
							<c:forEach items="${menuList.menuProfile}" var="itm2" varStatus="status">
								<c:choose>

									<c:when test="${(itm2.formName  eq null and itm.groupId eq itm2.groupId)and itm2.parent gt 0 and itm.appid eq itm2.appid}">
										<li class="myclass">${itm2.resourceId}</li>
									</c:when>
									<c:when test="${itm2.formName  ne null and itm.groupId eq itm2.groupId and itm.appid eq itm2.appid}">
										<li><c:choose>
												<c:when test="${ itm2.disabled }">
													<a class="linkdis masterTooltip" title="This Menu has been disabled">${itm2.resourceId}</a>
												</c:when>
												<c:otherwise>
													<a href="<%=getServletContext().getInitParameter("URL")%>/${itm2.formName}?<csrf:token uri='${itm2.formName}'/>"><c:out value="${itm2.resourceId}"></c:out></a>
												</c:otherwise>
											</c:choose></li>
									</c:when>

								</c:choose>

							</c:forEach>

						</ul>
					</c:when>

				</c:choose>

			</c:forEach>
		</div>
		<div class="clear"></div>
		<!-- <div id="newsupdate"> -->
		<!-- <div class="pnlhd">News Update</div> -->
		<!-- <div class="clear"></div> -->
		<!-- <div class="scroltxt"> -->
		<!-- <marquee direction="up" scrollamount="2" height="125px"> -->
		<!-- It is a long established fact that a reader will be distracted by the readable content of a page when... -->
		<!-- <img src="images/activflag.png" width="17" height="12" border="0" /><br /><br /> -->
		<!-- It is a long established fact that a reader will be distracted by the readable content of a page when... -->
		<!-- <img src="images/normal_flag.png" width="17" height="12" border="0" /> -->
		<!-- </marquee> -->

		<!-- </div> -->
		<!-- </div> -->

	</div>

</body>
</html>