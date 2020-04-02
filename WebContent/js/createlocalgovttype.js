function hidediv()
{		
	$("#txtlocalBodyTypeName_error").hide();
	$("#urbanId_error").hide();
	$("#ddRuralCategory_error").hide();
	$("#ddlevel_error").hide();

var ruralId=document.getElementById('ruralId');
if(ruralId.checked)
	{
	document.getElementById('divRCategory').style.display='block';
	}
else
	{
		document.getElementById('divRCategory').style.display='none';
	}


}

function showdiv(id)
{	
	if(id=='R')
		{
		document.getElementById('divRCategory').style.display='block';
		}
	else
		{
		document.getElementById('divRCategory').style.display='none';
		}
}


function loadModifyLBTypePage()
{
	
	$("#txtlocalBodyTypeName_error").hide();
	$("#urbanId_error").hide();
	$("#rdoTrad_error").hide();
	$("#ddlevel_error").hide();
}
function validateAllforModify()
{
	var msg=null;	 
	 
	
	
var errors = new Array();
	
	var error = false;
	   errors[0] = !vaildateLBTypeName();
	   errors[1] = !validateRadioButtons();
	   errors[2] = !validateRuralCategoryforModify();
	   errors[3] = !validateLevel();
	    
	    for(var i=0 ; i<=3 ; i++)
			
		{
			if(errors[i]==true)
				{	
				 
				error = true;
					break;
				}
		}

	   
		if(error == true)
			{
			showClientSideError();
		
			return false;
			}
		else
			{
			return true;
			}
		return false;
	if(msg!=null)
		{
		alert(msg);
		return false;
	}
	else{
		
		return true;
	}
	
return false;
}
function validateAll()
{ 	
	


	var errors = new Array();
	
	var error = false;
	   errors[0] = !vaildateLBTypeName();
	   errors[1] = !validateRadioButtons();
	   errors[2] = !validateRuralCategory();
	   errors[3] = !validateLevel();
	    
	    for(var i=0 ; i<=3 ; i++)
			
		{
			if(errors[i]==true)
				{	
				
				error = true;
					break;
				}
		}

	   
		if(error == true)
			{
			showClientSideError();
		
			return false;
			}
		else
			{
			return true;
			}
		return false;
}

function vaildateLBTypeName()
{
	if(document.getElementById('txtlocalBodyTypeName').value=="")
	{		
		$("#txtlocalBodyTypeName_error").show();
		return false;
	}
	
	$("#txtlocalBodyTypeName_error").hide();
	return true;
	}

function validateRadioButtons()
{ 
	if(document.getElementById('ruralId').checked==false && document.getElementById('urbanId').checked==false)
		{
		$("#urbanId_error").show();
		return false;
		}
	$("#urbanId_error").hide();
	return true;
	
}

function validateRuralCategory()
{ 
	var ruralId=document.getElementById('ruralId');
	if(ruralId.checked)
		{
	 
			var ddRuralCategory=document.getElementById('ddRuralCategory');
			if(ddRuralCategory.selectedIndex==0)
				{ 
				$("#ddRuralCategory_error").show();
				return false;
				}
			else if(ddRuralCategory.selectedIndex==-1)
			{ 
				$("#ddRuralCategory_error").show();
			return false;
			}
			$("#ddRuralCategory_error").hide();
			return true;
		}
	$("#ddRuralCategory_error").hide();
	return true;
}


function validateRuralCategoryforModify()
{ 
	var ruralId=document.getElementById('ruralId');
	if(ruralId.checked)
		{
		 
			if(document.getElementById('rdoPRI').checked==false && document.getElementById('rdoTrad').checked==false)
			{
			$("#rdoTrad_error").show();
			return false;
			}
		$("#rdoTrad_error").hide();
		return true;
		}
	else{
		$("#rdoTrad_error").hide();
		return true;
	}
	 
}

function validateLevel()
{ 
	var ruralId=document.getElementById('ruralId');
	 
	if(ruralId.checked)
		{
			var ddlevel=document.getElementById('ddlevel');
			 
			if(ddlevel.selectedIndex==0)
				{ 
					$("#ddlevel_error").show();
					return false;
				}
			else if(ddlevel.selectedIndex==-1)
			{ 
				$("#ddlevel_error").show();
				return false;
			}
			$("#ddlevel_error").hide();
			return true;
		}
	$("#ddlevel_error").hide();
	return true;
}





function resetForm()
{ 
	document.getElementById('txtlocalBodyTypeName').value="";
	document.getElementById('urbanId').checked=false;
	document.getElementById('ruralId').checked=false;
	document.getElementById('ddRuralCategory').selectedIndex=0;
	document.getElementById('ddlevel').selectedIndex=0;
	document.getElementById('divRCategory').style.display='none';

	}
function resetModifyForm()
{
	//document.getElementById('OfficialAddressType').value="";
	document.getElementById('urbanId').checked=false;
	document.getElementById('ruralId').checked=false;
	document.getElementById('rdoPRI').checked=false;
	document.getElementById('rdoTrad').checked=false;
	document.getElementById('ddlevel').selectedIndex=0;
	
}
function resetModifyCorrectionForm()
{
	document.getElementById('OfficialAddress').value="";
	}
	




function chekradio()
{
var group2=document.forms["createlocalgovtType"].urbanId;

if(group2[0].checked==false && group2[1].checked==false)
	{	
		$("#urbanId_error").fadeIn(500).show();
		
		$("#urbanId").addClass("error_fld");
		$("#urbanId").addClass("error_msg");
	
	return true;
	
	}
else 
	{
	
	$("#urbanId_error").hide();
	}

}