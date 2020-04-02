


 <div id="page-wrapper" ng-controller = "revenueDashboardController as ctrl">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Tradtional Local Bodies Dashboard</h4> </div>
                   
                    <!-- /.col-lg-12 -->
                </div>

			<div class="col-lg-12 col-md-12">
              <div class="card">
                <div class="card-header card-header-warning">
                  <h4 class="card-title">Nodal Officer Details</h4>
                  
                </div>
                <div class="card-body tbl_pos">
					<label class="cname inline col-sm-4"><i class="fa fa-user"></i><m1 class="p-left"> Name</m1>  <m1 class="cval">{{entityList.getDashboardNodalOfficer.noName}}</m1> </label>
					 <label class="cname inline col-sm-4"><i class="fa fa-phone"></i> <m1 class="p-left">Mobile No.</m1><m1 class="cval">{{entityList.getDashboardNodalOfficer.noMobile}}</m1>  </label>
					  <label class="cname inline col-sm-4"><i class="fa fa-envelope"></i><m1 class="p-left">Email</m1><m1 class="cval">{{entityList.getDashboardNodalOfficer.noEmail}}</m1> </label>
                </div>
              </div>
            </div>
			
			
			<div class="col-lg-12 col-md-12">
              <div class="card">
                <div class="card-header card-header-warning">
                  <h4 class="card-title">LGD Discrpancy</h4>
                  
                </div>
				<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 number_pos_2x">
					<div class="row">
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="card card-stats">
								<div class="card-header card-header-warning card-header-icon">
									<div class="card-icon pn-blue-color">
										<p class="card-category">Village not mapped with Local Bodies</p>
									</div>
								</div>
								<div class="card-body number_pos">
									<center><m1 class="number_show">{{entityList.getDashboardRevenueDetails.unmappedVillageinLbt}}</m1></center>
								</div>
								<div class="card-footer">
									<div class="stats">
										<a href="#" ng-click="showRevenueDetails('UVLT','Village not mapped with Local Bodies')">
											<i class="fa fa-arrow-circle-right fa-2x" style="color:#0b8bcc;"></i>
											<m1 class="p-left-anchor">Get More info...</m1>
							
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="card card-stats">
								<div class="card-header card-header-warning card-header-icon">
									<div class="card-icon pn-orange-color">
										<p class="card-category">Local Bodies in Disturbed State</p>
									</div>
								</div>
								<div class="card-body number_pos">
									<center><m1 class="number_show">{{entityList.getDashboardRevenueDetails.distrubStateT}}</m1></center>
								</div>
								<div class="card-footer">
									<div class="stats">
										<a href="#" ng-click="showLBDetails('EDST','Local Bodies in Disturbed State')">
											<i class="fa fa-arrow-circle-right fa-2x" style="color:#0b8bcc;"></i>
											<m1 class="p-left-anchor">Get More info...</m1>
							
										</a>
									</div>
								</div>
							</div>
						</div>
            
					</div>
				</div>
				
				
              </div>
            </div>
			
			
			<div class="col-lg-12 col-md-12">
              <div class="card">
                <div class="card-header card-header-warning">
                  <h4 class="card-title">LGD Reports</h4>
                  
                </div>
				<div class="row tbl_pos"  ng-show="isnodalOfficer">
					<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
					
						<label class="col-sm-3 row_data inline "><m1 class="m-left_2x"><i class="fa fa-calendar"></i><m2 class="p-left">Last Updation Date</m2></m1></label>
						<label class="col-sm-2 row_data">{{entityList.getDashboardNodalOfficer.updatedOn| date :  "dd/MM/yyyy" }}</label>
						<!--<label class="col-sm-2 row_data"><i class="far fa-calendar-alt"></i></label>-->
					</div>
				</div>
				<div class="row"  ng-show="isnodalOfficer">
					<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
						<!-- fa fa-lock -->
						<label class="col-sm-3 row_data inline"><m1 class="m-left_2x"><i class="fa fa-unlock-alt"></i><m2 class="p-left">Status of Data Freeze</m2></m1></label>
						<label class="col-sm-2 row_data">{{entityList.getDashboardNodalOfficer.noStatus}}</label>
						<!--<label class="col-sm-2 row_data"><i class="fas fa-lock-open"></i></label>-->
					</div>
				</div>
				
				<div class="table-responsive tbl_pos m-left_2x">
										<table class="table">
											<thead>
												<tr>
													
													<td width="25%">Entity</td>
													<td width="15%">Created</td>
													<td width="15%">Modified</td>
													<td width="15%">Shifted</td>
													<td width="30%">
														Financial Year
														<select ng-model="finYearModel" ng-change="changeFinYear(finYearModel)">
															<option value="2017-2018">2017-2018</option>
															<option value="2018-2019">2018-2019</option>
															<option value="2019-2020">2019-2020</option>
														</select>
													</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="obj in entityList.getDashboardEntityDetails " >
													<td>{{obj.entityName}}</td>
													<td class="txt-oflo" align=""><a href="#">{{obj.noofEntityCreated}}</a></td>
													<td><a href="#">{{obj.noofEntityModified}}</a></td>
													<td class="txt-oflo"><a href="#">{{obj.noofEntityShift}}</a></td>
													<td></td>
													
												</tr>
												
												
											</tbody>
										</table>
									</div>
									
									
					<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-lg-3 col-md-6 col-sm-6">
							<div class="card card-stats">
								<div class="card-header card-header-warning card-header-icon">
									<div class="card-icon pn-green-color">
										<p class="card-category">Invalidate Local Bodies</p>
									</div>
								</div>
								<div class="card-body number_pos">
									<center><m1 class="number_show">{{entityList.getDashboardRevenueDetails.invalidateLbT}}</m1></center>
								</div>
								<div class="card-footer">
									<div class="stats">
										<a href="#" ng-click="showLBDetails('IILB','Invalidate Local Bodies')">
											<i class="fa fa-arrow-circle-right fa-2x" style="color:#0b8bcc;"></i>
											<m1 class="p-left-anchor">Get More info...</m1>
							
										</a>
									</div>
								</div>
							</div>
						</div>
						
						
            
					</div>
				</div>				
				
              </div>
            </div>
            
				
				
				
				
				
				
				
				
				
				
			</div>
</div>