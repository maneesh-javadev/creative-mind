<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<html>
<head>

<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>

</head>
<body>

	<form:form action="ulbVillage.htm" method="POST"
		commandName="addVillageNew">

		<div
			style="margin: 20px 20px 0px 20px; background: #F7F7F7; padding: 10px">
			<div
				style="position: absolute; z-index: 1; width: 90px; text-align: center; top: -11px; left: 12px"
				class="frmhdtitle">ULB List</div>
			<table width="570" border="0" cellspacing="0" cellpadding="0">
				<tr>
				 <td>ULB of Selected<br /> <label> <select
						name="select4" class="frmsfield" style="width: 150px">
							<option>Select</option>
					</select> </label>
					
				</td>
				</tr>
				<tr>
					<td colspan="3">Covered land region by the ULB</td>
				</tr>
				<tr>
					<td width="235" rowspan="3"><select name="select6" size="1"
						multiple="multiple" class="frmtxtarea"
						style="height: 80px; width: 233px">
					</select>
					</td>
					<td width="100" align="center"><label> <input
							type="submit" class="btn" name="Submit4" value="Full&gt;&gt;"
							style="width: 60px" /> </label>
					</td>
					<td width="235" rowspan="3"><select name="select6" size="1"
						multiple="multiple" class="frmtxtarea"
						style="height: 80px; width: 233px">
					</select>
					</td>
				</tr>
				</table>
           <table width="570" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center"><label> <input type="submit" class="btn"
							name="Submit7" value="Part&gt;&gt;" style="width: 60px" /> </label>
					</td>
				</tr>
				<tr>
					<td align="center"><label> <input type="submit" class="btn"
							name="Submit8" value="&lt;&lt;" style="width: 60px" /> </label>
					</td>
				</tr>
			<br />
			<tr>
				<td><label></label>
					 <div class="btnpnl">
                              <label>
                              <input type="submit" class="btn" name="Submit" value="Save" />
							  </label>
							  <label>
                              <input type="submit" class="btn" name="Submit5" value="Save and Publish" />
                              </label>
                              <label>
                              <input type="button" class="btn" name="Submit2" value="Preview" />
                              </label>
                              <label>
                              <input type="button" class="btn" name="Submit6" value="Cancel" />
                              </label>
                      </div>
					</div>
				</td>
			</tr>
		</div>
</table>
	</form:form>
</body>
</html>