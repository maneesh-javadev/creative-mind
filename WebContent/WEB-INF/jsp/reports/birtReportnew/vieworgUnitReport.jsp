<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
</head>
  
  <body>
	
<section class="content">
<!-- Main row -->
 <div class="row">
   <!-- main col -->
	 <div class="container">
	    <section class="col-lg-12 prebox">
     	   <div class="box">
            <birt:viewer id="birtViewer" reportDesign="${report_design}" pattern="frameset"  style="height:2200px;width:100%"   baseURL="${serverLoc}">
                        	<birt:param name="_ollc" value="${orgLvlCode}"></birt:param>
                        	<birt:param name="_orgName" value="${orgztn}"></birt:param>
                        	<birt:param name="_orgLvl" value="${orgLvl}"></birt:param>
                        	<birt:param name="_slc" value="${stateCode}"></birt:param>
                        	<birt:param name="_orgCode" value="${orgCode}"></birt:param>
               </birt:viewer> 
           		   <div class="box-footer">
		             <div class="col-sm-offset-2 col-sm-10">
		              <div class="pull-right">
						<button type="button"  id="close" name="close" onclick="javascript:go('welcome.do');" class="btn btn-danger" ><i class="fa fa-times-circle"></i> CLOSE</button>
					 </div>
				   </div>
			    </div>
              </div>
	        </section>
          </div>
       </div>
     </section>
   </body>
</html>