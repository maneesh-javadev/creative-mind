function toggledisplay3(obj,val)
{
	if (document.getElementById('divData')!=null)
		document.getElementById('divData').style.visibility='hidden';
	if (document.getElementById(obj).checked)
		{
			
			if (val=='showbytext')
				{
					document.getElementById('showbytext').style.visibility='visible';
					document.getElementById('showbytext').style.display='block';
					document.getElementById('showbydropdown').style.visibility='hidden';
					document.getElementById('showbydropdown').style.display='none';
					
									
				}
			else if (val=='showbydropdown')
			{
				document.getElementById('showbydropdown').style.visibility='visible';
				document.getElementById('showbydropdown').style.display='block';
				document.getElementById('showbytext').style.visibility='hidden';
				document.getElementById('showbytext').style.display='none';
			}
			
		}

}

function validate(val)
{
	//alert("inside block js");
	
	if (val==1)
		if (document.getElementById('ddSourceDistrict').selectedIndex>=0 )			
			document.forms['form1'].submit();
		else
			alert('please select the value from dropdown');
}

function manageBlock(url,id)
{
	//alert("inside url"+url+"inside id"+id);
	dwr.util.setValue('blockId', id, {	escapeHtml : false	});
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit(); 
} 

function setDirection(val)
{
	document.getElementById('direction').value=val;
	document.forms['form1'].action = "viewBlockPagination.htm?<csrf:token-value uri='viewBlockPagination.htm'/>";
	document.forms['form1'].submit();
}

function go1(id)
{
	var url="viewblockbystatecode.htm?id="+id;
	window.location=url;
}

function getData()
{
	document.getElementById('text1').value = trim(document.getElementById('text1').value);
	document.getElementById('text2').value = trim(document.getElementById('text2').value);
	
	if (document.getElementById('text1').value!='' || document.getElementById('text2').value!='')
		document.forms['form1'].submit();
	else
		alert('Please enter search text!');
}