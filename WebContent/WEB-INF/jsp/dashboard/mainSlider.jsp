 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf"%>
 <!-- Left side column. contains the logo and sidebar -->
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->


                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">

                    
                    	<c:forEach items="${menuList.menuProfile}" var="itm">
                    	
							<c:choose>
								<c:when test="${itm.parent eq 0}">
								
										<li class=" treeview">
										<a href="#">
										<c:set var="micon" />
										<c:choose>
										<c:when test="${itm.resourceId eq 'State'}">
										<c:set var="micon"  value="fa fa-building"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'District'}">
										<c:set var="micon"  value="fa fa-building"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Sub District'}">
										<c:set var="micon"  value="fa fa-building"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Village'}">
										<c:set var="micon"  value="fa fa-building"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Shift'}">
										<c:set var="micon"  value="fa fa-exchange"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Local Body'}">
										<c:set var="micon"  value="fa fa-map-o"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Convert Local Body'}">
										<c:set var="micon"  value="fa fa-exchange"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Ward'}">
										<c:set var="micon"  value="fa fa-clone"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Block'}">
										<c:set var="micon"  value="fa fa-cube"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Govt Order Template'}">
										<c:set var="micon"  value="fa fa-file-code-o"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Configure System'}">
										<c:set var="micon"  value="fa fa-cog"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Constituency'}">
										<c:set var="micon"  value="fa fa-university"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Un-Resolved Entity'}">
										<c:set var="micon"  value="fa fa-share-square-o"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Miscellaneous'}">
										<c:set var="micon"  value="fa fa-cube"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'LGD Transactions'}">
										<c:set var="micon"  value="fa fa-external-link"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'PESA'}">
										<c:set var="micon"  value="fa fa-laptop"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Freeze/Unfreeze'}">
										<c:set var="micon"  value="fa fa-laptop"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'View and Manage Draft Entities'}">
										<c:set var="micon"  value="fa fa-laptop"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Section'}">
										<c:set var="micon"  value="fa fa-laptop"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Department'}">
										<c:set var="micon"  value="fa fa-address-book"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Organization'}">
										<c:set var="micon"  value="fa fa-address-book-o"/>
										</c:when>
										<c:when test="${itm.resourceId eq 'Reporting Hierarchy'}">
										<c:set var="micon"  value="fa fa-sitemap"/>
										</c:when>
										
										
										<c:otherwise>
										<c:set var="micon"  value="fa fa-chevron-circle-down"/>
										</c:otherwise>
										</c:choose>
                            				<i class="${micon}" aria-hidden="true"></i> <span><c:out value="${itm.resourceId}" /></span> <i class="fa fa-angle-left pull-right"></i>
														
                        				</a>
											<c:forEach items="${menuList.menuProfile}" var="itm2" varStatus="status">
												<c:choose>

													<c:when test="${(itm2.formName  eq null and itm.groupId eq itm2.groupId)and itm2.parent gt 0 and itm.appid eq itm2.appid}">
														<li><a href="<%=getServletContext().getInitParameter("URL")%>/${itm2.formName}?<csrf:token uri='${itm2.formName}'/>"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i><c:out value="${itm2.resourceId}" /></a></li>
														
													</c:when>
													<c:when test="${itm2.formName  ne null and itm.groupId eq itm2.groupId and itm.appid eq itm2.appid}">
													 <ul class="treeview-menu">
														<c:choose>
														<c:when test="${ itm2.disabled }">
														<li><a class="linkdis masterTooltip" title="This Menu has been disabled"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i><c:out value="${itm2.resourceId}" /></a></li>
														
														
														</c:when>
														<c:otherwise>
														<li><a href="<%=getServletContext().getInitParameter("URL")%>/${itm2.formName}?<csrf:token uri='${itm2.formName}'/>"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i><c:out value="${itm2.resourceId}" /></a></li>
														</c:otherwise>
														</c:choose>
														</ul>
													
													</c:when>

												</c:choose>

											</c:forEach>
										</li>
                    </c:when>
                    </c:choose>
                    </c:forEach>
                    
                                         

                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
