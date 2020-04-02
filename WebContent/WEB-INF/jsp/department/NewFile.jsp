<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<title> Form UI</title>
<link rel="stylesheet" type="text/css" href="css/sample.css" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css" />

<!-- Add jQuery library -->
	<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
	
	<!-- Add fancyBox main JS and CSS files -->
	<script type="text/javascript" src="js/jquery.fancybox.js?v=2.1.5"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css?v=2.1.5" media="screen" />
	<script type="text/javascript">
		$(document).ready(function() {
			//$(".").attr("disabled","disabled");
			var mainWrapper = $('.form_block');

			mainWrapper.find('input, textarea, button, select').each(function () {
			    $(this).attr('disabled', true);
			});
			
			var mainWrapper1 = $('#coverageArea');

			mainWrapper1.find('input, textarea, button, select').each(function () {
			    $(this).attr('disabled', false);
			});
			
			
			/*
			 *  Simple image gallery. Uses default settings
			 */

			$('.fancybox').fancybox();

			/*
			 *  Different effects
			 */

			// Change title type, overlay closing speed
			$(".fancybox-effects-a").fancybox({
				helpers: {
					title : {
						type : 'outside'
					},
					overlay : {
						speedOut : 0
					}
				}
			});

			
			

		});
	</script>
	
</head>
<body>
		
	<div id="overlay">
		<div class="confirm popup" id="confirmbox">
			<a class="close" href="javascript:;" onclick="getElementById('confirmbox').style.display='none';">x</a>
			<h5>Confirm</h5>
			<div>
				<p>Woah, are you absolutely sure you want to do that?</p>
				<p>"$Revision: $".substring(11).replace('$','')</p>
				<div class="buttons">
					<input class="bttn blue" type="submit" value="Yes">
					<input class="bttn" type="submit" value="No">
				</div>
			</div>
		</div>
	</div>

	<div class="sample">
	
		<!-- Main Form Stylings starts -->
		<div class="form_stylings">
			
			<!-- Main Heading starts -->
			<div class="header">
				<h3>Main Page title will come here</h3>
				<ul class="item_list">
					<li>CBT</li>
					<li>Help</li>
				</ul>
			</div>
			<!-- Main Heading ends -->
			
			<!-- Page Content starts -->
			<div class="page_content">
				<div class="form_container">
				
					
				  <div class="form_block">
												<div class="col_1">
												<h4></h4>
													<ul class="form_body">
														<li>
															<label>Field Name:<span class="mandate">*</span></label>
															<input class="error" type="text" value="" />
															<span class="errormsg">Field cannot be left blank</span>
														</li>
														<li>
															<label>Field Name:<span class="mandate">*</span></label>
															<input type="text" value="" />								
														</li>
														<li>
															<label>Field Name:<span class="mandate">*</span></label>
															<textarea rows="3"></textarea>								
														</li>
														<li>
															<label>Field Name:<span class="mandate">*</span></label>
															<select>
																<option>Value1</option>
															</select>								
														</li>
														<li>
															<label>Field Name:<span class="mandate">*</span></label>
															<textarea cols="3"></textarea>							
														</li>								
														<li>
															<!-- To display Elements in a  line use '.inline' class on elements -->
															<label class="inline"><input name="radio" type="Radio" value="" />Radio</label>
															<label class="inline"><input name="radio" type="Radio" value="" />Radio</label>																	
														</li>
														<li>
															<label class="inline"><input type="checkbox" value="" />Checkbox</label>
														</li>
										
												  </ul>
												</div>
					</div>
					<br>
					<br>
					
					 <div class="form_block">
												<div class="col_1">
												<h4>Form Title will come Here</h4>
													<ul class="form_body">
														<li>
														<div class="ms_container">
															<div class="ms_selectable">
																<select id="ddLgdLBInterPSourceList" name="lgd_LBInterPSourceList" multiple="multiple">
																	<option value="1111">MORNI</option>
																	<option value="1112">PINJORE</option>
																	<option value="1113">RAIPUR RANI</option>
																</select>
																		</div>
															<div class="ms_buttons">
																<input class="bttn" type="button" value="Whole" />
																<input class="bttn" type="button" value="Back" />
																<input class="bttn" type="button" value="Reset" />
																<input class="bttn" type="button" value="Part" />
															</div>
															<div class="ms_selection">
																<select id="ddLgdLBInterPSourceList" name="lgd_LBInterPSourceList" multiple="multiple">												
																</select>
															</div>
															<div class="clear"></div>
															</div>
													   </li>
												    </ul>
													</div>
					</div>
					<br>
					<br>
					
					
					<div class="form_block">
										<div class="col_1">
										<h4>GRID</h4>
											<ul class="form_body">
												<li>														
													<table class="data_grid" width="100%">
														<thead>
															<tr>
																<th>Heading1</th>
																<th>Heading2</th>
																<th>Heading3</th>
																<th>Heading4</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>Value1</td>
																<td>Value2</td>
																<td>Value3</td>
																<td>Value4</td>
															</tr>
															<tr>
																<td>Value1</td>
																<td>Value2</td>
																<td>Value3</td>
																<td>Value4</td>
															</tr>
															<tr>
																<td>Value1</td>
																<td>Value2</td>
																<td>Value3</td>
																<td>Value4</td>
															</tr>
															<tr>
																<td>Value1</td>
																<td>Value2</td>
																<td>Value3</td>
																<td>Value4</td>
															</tr>
														</tbody>
													</table>
													<div class="paginationlist">
														<ul class="pagination">
														  <li><a href="#" class="first">First</a></li>
														  <li><a href="#" class="previous">Previous</a></li>
														  <li><a href="#">1</a></li>
														  <li><a href="#">2</a></li>
														  <li><a href="#" class="current">3</a></li>
														  <li><a href="#">4</a></li>
														  <li><a href="#">5</a></li>
														  <li><a href="#" class="next">Next</a></li>
														  <li><a href="#" class="last">Last</a></li>
														</ul>
													</div>
											   </li>
										    </ul>
										</div>
					 </div>
					 <br>
					 <br>
					
					 
					 <div class="form_block">
									<div class="col_1">
									<h4></h4>
										<ul class="form_body">
											<li>
											 <!-- Alert and Confirm Box -->
												<a class="fancybox" href="#inline1">ALERT BOX</a>
												<a href="javascript:;" onclick="getElementById('overlay').style.display='block';">CONFIRM BOX</a>
												<div id="inline1" style="width:400px;display: none;">
													<div class="alert popup" id="alertbox">
														<h5>Alert Box</h5>
														<div>
															<p>Woah, are you absolutely sure you want to do that?</p>
															<div class="buttons">
																<input class="bttn blue" type="submit" value="Submit">
																<input class="bttn" type="submit" value="Cancel">
															</div>
														</div>
													</div>
												</div>
					 
										   </li>
									    </ul>
									</div>
					</div>
					<br>
					<br>
					
					
					 <!-- links will come here -->
					<div class="form_block">
									<div class="col_1">
										<h4>GIS NODES</h4>
										<ul class="form_body">
											<li>
										<div class="long_latitude">
											<div class="row">
												<div class="col"><label>Longitude</label></div>
												<div class="col"><label>Latitude</label></div>
											</div>
											<div class="row"> <!-- Max of 4 divs can be used inside this row div -->
												<div class="col"><input type="text" value="" /></div>
												<div class="col"><input type="text" value="" /></div>
												<div class="col"><input class="bttn blue addmore" type="submit" value="Add more" /></div>
											</div>
											<div class="row"> 
												<div class="col"><input type="text" value="" /></div>
												<div class="col"><input type="text" value="" /></div>
												<div class="col"><input class="bttn blue addmore" type="submit" value="Add more" />
													<input class="bttn remove" type="submit" value="Remove" />
												</div>
											</div>
										</div>
											</li>
										</ul>
									</div>
					</div>
					<br>
					<br>
					
					
					<div class="form_block" id="coverageArea">
									<div class="col_1">
									<h4>Coverage Area</h4>
										<ul class="form_body">
											<li>
											<input type="text" id="coveragearea"/>
										   </li>
									    </ul>
									</div>
					</div>
					<br>
					<br>
					
								
					<div class="form_block">
									<div class="col_1">
									<h4> </h4>
										<ul class="form_body">
											<li>
												<input class="bttn blue" type="submit" value="Submit" />
												<input class="bttn" type="submit" value="Cancel" />
										   </li>
									    </ul>
									</div>
					</div>
								
						
					
						
				</div>
			</div>
			<!-- Page Content ends -->
			
		</div>
		<!-- Main Form Stylings ends -->
		
	</div>
	
	
									
</body>