/*@Author - Sarvapriya Malhotra
            Note : PLEASE DO NOT CHANGE CODE WITHOUT ASKING THE AUTHOR FIRST 
            LOTS OF DYNAMIC THINGS ARE HAPPENING
*/
var i=50;
var max=0;
var dropdown_pos=1;
var sel_drop_value = new Array();
var sel_drop_value_pos = 1;
var allchk = 0;
var dynstart = 0;
var dynend = 0;
var dynfirst4 = 1;
var dynstart4 = 1;
var dynend4 = 1;
var tmplabel = "";
var isnew_officialtype = true;
var isnotcomplete = false;
var newsubtype=1;

function trim(str)
{
    if(!str || typeof str != 'string')
        return null;

    return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
}

function validator()
{
	var minselected=0;
	isnotcomplete = false;
	
	var nodeList = document.getElementsByName("check");

	
	for(var j=0;j<nodeList.length;j++)
		{
			if (allchk < parseInt(nodeList[j].id))
				allchk = parseInt(nodeList[j].id);
		}

	for (var j=0;j<nodeList.length;j++)
		{

			//To check if at least 1 is checked or not
			if (!nodeList[j].checked)
				minselected++;
			
			
			if(nodeList[j].checked && document.getElementById("txtNE" + nodeList[j].id).value=="")
				{
					document.getElementById("lblNE" + nodeList[j].id).innerText="*";
					isnotcomplete=true;
				}
			/*if(nodeList[j].checked && document.getElementById("txtNL" + nodeList[j].id).value=="")
			{
				document.getElementById("lblNL" + nodeList[j].id).innerText="*";
				isnotcomplete=true;
			}*/
			
			//To remove * if user has entered values
			if(nodeList[j].checked && document.getElementById("txtNE" + nodeList[j].id).value!="")
				document.getElementById("lblNE" + nodeList[j].id).innerText="";
			if(nodeList[j].checked && document.getElementById("txtNL" + nodeList[j].id).value!="")
				document.getElementById("lblNL" + nodeList[j].id).innerText="";
		}
	
	//To check if at least 1 is checked or not
	if (minselected==nodeList.length)
		isnotcomplete=true;
}

function validatepage4()
{
	for(var j=dynfirst4;j<=dynend4;j++)
		{
			if (document.getElementById('dtxtE' + j).value="")
				{
				document.getElementById('lblE' + j).innerText="*";
				}
			if (document.getElementById('dtxtL' + j).value="")
			{
			document.getElementById('lblE' + j).innerText="*";
			}
		}
	
}

function validatepage1()
{
	validator();
	if (!isnotcomplete)
		{
			document.getElementById('disable_next_button').disabled = true;
			document.forms['lgsform'].submit();
		}	
}

function fillofficials()
{
	if (isnew_officialtype==false)
		{
			for (var j=1;j<document.getElementById('officialType').length;j++)
			{
				document.getElementById('officialType').selectedIndex=j;
				removeSelectedValue('officialType');
				
			}
			
			for (var j=1;j<document.getElementById('officialType1').length;j++)
			{
				$("#officialType").append("<option value=" + document.getElementById('officialType1')[j].value + ">"+document.getElementById('officialType1')[j].text+"</option>");
			}
			isnew_officialtype=true;
		}
}

function onloadSelect()
{
	//@Author - Sarva - Added for modify case
	var nodeList = document.getElementsByName('nomenEnglish');

	for (var i=0;i<nodeList.length;i++)
		{
			if (nodeList[i].value!="")
				{
					document.getElementById(nodeList[i].id.substr(5)).checked=true;
					document.getElementById("txtNL" + nodeList[i].id.substr(5)).disabled=false;
					nodeList[i].disabled=false;
				}
		}
	
	
}
function fillbean()
{
	var tmpArray = new Array();
	for (var i=1;i<document.getElementById("lgt0").length;i++)
		{
			temp = document.getElementById("lgt0")[i].value.split("####");
			tmpArray[i]=temp[2];
			//document.getElementById("codes").value+=temp[2] + "%";
			document.getElementById("names").value+=temp[1] + "%";
		}
	tmpArray.sort();
	document.getElementById("codes").value+=tmpArray.join("%");
}

function removeSelectedValue(val)
{
  var elSel = document.getElementById(val);
  var i;
  for (i = elSel.length - 1; i>=1; i--) 
  {
    if (elSel.options[i].selected) 
    {
    	elSel.remove(i);
    }
  }	
}

function submitLast()
{
	var tmpArr = new Array();
	for(var j=1;j<newsubtype;j++)
		{
			tmpArr[j-1]=document.getElementById('subtypehidden' + j).value;
			//now we add all text to hidden textbox
			document.getElementById('tab4txt').value += document.getElementById('subtypehidden' + j).value + "~";     // + document.getElementById('firsttextE'+ j).value + "%" + document.getElementById('firsttextL'+ j).value + ":";
			
			for(var k=dynfirst4;k<=dynend4;k++)
			{
				if (document.getElementById("dtxtE" + k)!=null)
				{
					//alert("Parent : " + document.getElementById('subtypehidden' + j).value);
					//alert("Child : " + document.getElementById("dtxtE" + k).parentNode.parentNode.childNodes.item(2).value);
					//alert(document.getElementById("dtxtE" + k).value);
					if (document.getElementById("dtxtE" + k).parentNode.parentNode.childNodes.item(1).value!=document.getElementById('subtypehidden' + j).value)
						{
							var docontinue=false;
							for (var l=0;l<tmpArr.length;l++)
								{
									if (document.getElementById("dtxtE" + k).parentNode.parentNode.childNodes.item(2).value!=tmpArr[l])
										{
											docontinue=true;
										}
								}
							if (docontinue)
								continue;
						}
					document.getElementById('tab4txt').value +=  document.getElementById("dtxtE" + k).value + "%" + document.getElementById("dtxtL" + k).value + ":";
				}
			}
			document.getElementById('tab4txt').value += "~";
		}
	document.forms['lgsform'].submit();
}

function persist4()
{
	//If User has not selected from dropdown
	if (document.getElementById('lgt2').selectedIndex!=0)
		{
			//First we save id then remove selected value from Dropdown
			var ddown = document.getElementById('lgt2')[document.getElementById('lgt2').selectedIndex].value;
						
			//Add Static fields to dynamicDiv4_Addons
			document.getElementById('dynamicDiv4_Addons').innerHTML += "<div id='newsubtype" + newsubtype + "'></div>"; 
			document.getElementById('newsubtype' + newsubtype).innerHTML += "<strong>" + document.getElementById('lgt2')[document.getElementById('lgt2').selectedIndex].text + "</strong><input type='hidden' id='subtypehidden" + newsubtype + "' value='" + ddown + "' />";
			document.getElementById('newsubtype' + newsubtype).innerHTML += '&nbsp;<input type="button"  value="Add Grade" onclick="changeTab4(\'newsubtype' + newsubtype+'\')" /><br/>';
			
			for (var j=dynstart4;j<=dynend4;j++)
				{
					//For case where add more button has not been clicked document.getElementById("dtxtE" + j) will be null 
					if (document.getElementById("dtxtE" + j)!=null)
						{
							document.getElementById('newsubtype' + newsubtype).innerHTML += "<div id=div" + j + "><label>" + document.getElementById("lbl_E").innerText + "</label><input id='dtxtE" + j + "' name= 'subTypeName' type='text' class='frmfield' style='width: 200px' value='" + document.getElementById('dtxtE' + j).value + "' /><label id='lblE" + j + "' ></label> &nbsp;&nbsp;<span style='height: 15px; padding-top: 3px;'class='errormsg'></span><label>" + document.getElementById("lbl_L").innerText + "</label><input id='dtxtL" + j + "' name='subTypeLocal' type='text' class='frmfield' style='width: 200px' value='" + document.getElementById('dtxtL' + j).value + "' /><label id='lblL" + j + "'></label><span style='height: 15px; padding-top: 3px;' class='errormsg'></span><label></label>&nbsp;<input type='button' value='Remove' onclick='div" + j + ".parentNode.removeChild(div" + j +")'/></div>";
						}
				}
			
			//Reset Original Page fields
			document.getElementById('dynamicDiv4_Addons').style.visibility='visible';
			document.getElementById('dynamicDiv4').innerHTML='';
			document.getElementById('dtxtE0').value="";
			document.getElementById('dtxtL0').value="";
			dynstart4 = dynend4+1;
			dynend4 = dynstart4;
			newsubtype++;
		}
}

function persist2()
{
	//First we save id then remove selected value from Dropdown
	tmplgt=document.getElementById('lgt3');
	var ddown = document.getElementById('lgt3')[document.getElementById('lgt3').selectedIndex].id.substr(1);
	
	//now we add all text to hidden textbox
	document.getElementById('hierarchy').value += ddown + "@" + document.getElementById('officialType').value + "~" + document.getElementById('desgName0').value;
	
	if (isnew_officialtype==true)
		{
			removeSelectedValue('officialType');
			isnew_officialtype=false;
		}
	else
		{
			removeSelectedValue('lgt3');
			fillofficials();
		}
	
	for (var j=dynstart;j<=dynend;j++)
		{
			//j>0 because desgName0 is the static textbox on jsp page
			if(document.getElementById("desgName" + j)!=null && j>0)
				{
					document.getElementById('hierarchy').value += "%" + document.getElementById("desgName" + j).value;
				}
		}
	document.getElementById('hierarchy').value += "~";
	dynstart=0;
	dynend=0;
	document.getElementById('desgName0').value="";
	document.getElementById('dynamicDiv').innerHTML='';

}

function populate()
{
	document.getElementById("childOrder").value+=document.getElementById("categoryType").value + "@@@@";
	document.getElementById("childOrder").value+=document.getElementById("lgt1")[document.getElementById("lgt1").selectedIndex].id + "@@@@";
	for(var j =1; j<dropdown_pos;j++)
		{
			//alert("Name : " + document.getElementById("select" + j)[document.getElementById("select" + j).selectedIndex].value + " ID : " + document.getElementById("select" + j)[document.getElementById("select" + j).selectedIndex].id);
			document.getElementById("childOrder").value+=document.getElementById("select" + j)[document.getElementById("select" + j).selectedIndex].id + "@@@@";
		}
	
	//Now we refresh page
	if (document.getElementById("categoryType").selectedIndex>0)
		removeSelectedValue('categoryType');
	
	for (var j=document.getElementById("lgt1").length;j>0;j--)
		{
			document.getElementById("lgt1").selectedIndex = j;
			removeSelectedValue('lgt1');
		}
	document.getElementById('dynamicdiv3').innerHTML = "";
	//Reset dropdown_pos for other drop down value chosen
	dropdown_pos=1;
	sel_drop_value_pos=1;
	
}


function ClearDropDown(ddl) 

{
 
  var len = document.getElementById(ddl).options.length;
 
    for (i=0; i<len; i++) {
    						document.getElementById(ddl).remove(0); //It is 0 (zero) intentionally 
    					}
 
}

function getcategory()
{
	ClearDropDown('lgt1');
	$("#lgt1").append("<option value=" + "---Select---"+">"+"---Select---"+"</option>");
	

	
	for (var i = 1; i<document.getElementById("lgt").length; i++)
	{
		temp = document.getElementById("lgt0")[i].value.split('####');
		$("#lgt1").append("<option id='" + temp[2] + "' value=" + temp[1]+">"+temp[1]+"</option>");
	}
	
	
	/*if (document.getElementById('categoryType').value=="Rural")
			{
				for (var i = 1; i<document.getElementById("lgt").length; i++)
					{
					temp = document.getElementById("lgt0")[i].value.split('####');
					//alert(temp[0]);
					if (temp[0]=="R")
							{
								$("#lgt1").append("<option id='" + temp[2] + "' value=" + temp[1]+">"+temp[1]+"</option>");
							}
					}
			}
	
	
	
	if (document.getElementById('categoryType').value=="Traditional")
	{
		for (var i = 1; i<document.getElementById("lgt").length; i++)
			{
			temp = document.getElementById("lgt0")[i].value.split('####');
			//alert(temp[0]);
			if (temp[0]=="I")
					{
						$("#lgt1").append("<option id='" + temp[2] + "' value=" + temp[1]+">"+temp[1]+"</option>");
					}
			}
	}*/
	
	//We Set Max = number of items in lgt1
	max=document.getElementById("lgt1").length;
}

function checkBoxValue()
{
	//IMPLEMENTED IN DESIGNATION HIRERARCHY PAGES --- RAM
	for (var z=50;z<i;z++){
		
		document.getElementById("isMultiple").value+=document.getElementById("chkbx" + z).checked + ",";
	}
	document.forms['designationForm'].submit();
}

function changeIt(divs)
//IMPLEMENTED IN DESIGNATION HIRERARCHY PAGES --- RAM
{
	var tmptexts=new Array();
	if (dynstart==0)
	{
		dynstart=i;
	}
	dynend=i;
	//alert("dynstart : " + dynstart + "and dynend : " + dynend);
	for(var j=dynstart;j<dynend;j++)
		{
			if(document.getElementById("desgName" + j)!=null && document.getElementById("desgNameLocal" + j)!=null)
				tmptexts[j]=document.getElementById("desgName" + j).value;
				tmptexts[j]=document.getElementById("desgNameLocal" + j).value;
		}
	divs.innerHTML += "<div id=div" + i + "><td><input type='checkbox' name='isMultiple1' id='chkbx" + i + "'/></td>&nbsp;&nbsp;" +
						"<td><input type='text' name='desgName' id='desgName" + i + "' class='frmfield' style='width:220px'/></td>&nbsp;&nbsp;&nbsp;"+
						"<td><input type='text' name='desgNameLocal' id='desgNameLocal" + i + "' class='frmfield' style='width:220px'/></td>&nbsp;"+
						"<td><input type='button' value='delete' onclick='div" + i + ".parentNode.removeChild(div" + i +")'/></td></div>";
	for(var j=dynstart;j<dynend;j++)
	{
		if(document.getElementById("desgName" + j)!=null && document.getElementById("desgNameLocal" + j)!=null)
			document.getElementById("desgName" + j).value=tmptexts[j];
			document.getElementById("desgNameLocal" + j).value=tmptexts[j];
	}
	i++;
}

function setval(sel,pos)
{
	sel_drop_value[pos]=sel.value;

}


function changeTab3(divs)
{
	if (document.getElementById('lgt1').selectedIndex>0 && dropdown_pos==1)
	{
		//When Add Child is pressed on the default select
		//Add Child is Add Lower LG Type now

			if (document.getElementById('lgt1').value.split(':')[1]=="D")
				{
					document.getElementById('dynamicdiv3').style.visibility='visible';
					document.getElementById('dynamicdiv3').style.display = 'block';
					
					divs.innerHTML += "Local Government Type : <select path='lBTList' name='lBTList' onchange='setval(select" + dropdown_pos + "," + dropdown_pos + ")' id='select" + dropdown_pos + "'><option value=''>- - Select - -</option></select>";
					divs.innerHTML += "<span id='buttons1'><input type='button' name='btn3' value='Add Lower LG Type' onclick='changeTab3(dynamicdiv3)' /><input type='button' name='Reset' value='Reset' onclick='resets()'/></span>";
					document.getElementById("buttons").style.display="none";
					
					for (var j=1;j<document.getElementById('lgt1').length;j++)
						{
							if (document.getElementById('lgt1')[j].value.split(':')[1]!="D")
								{
									document.getElementById('select' + dropdown_pos).add(new Option(document.getElementById("lgt1")[j].innerHTML,document.getElementById("lgt1")[j].value));
								}
						}
					//document.getElementById('lgt1').disabled=true;
					dropdown_pos++;
				}
			else if (document.getElementById('lgt1').value.split(':')[1]=="I")
				{
					document.getElementById('dynamicdiv3').style.visibility='visible';
					document.getElementById('dynamicdiv3').style.display = 'block';
					divs.innerHTML += "Local Government Type : <select path='lBTList' name='lBTList' onchange='setval(select" + dropdown_pos + "," + dropdown_pos + ")' id='select" + dropdown_pos + "'><option value=''>- - Select - -</option></select>";
					divs.innerHTML += "<span id='buttons1'><input type='button' name='btn3' value='Add Lower LG Type' onclick='changeTab3(dynamicdiv3)' /><input type='button' name='Reset' value='Reset' onclick='resets()'/></span><br />";
					document.getElementById("buttons").style.display="none";
					
					for (var j=1;j<document.getElementById('lgt1').length;j++)
						{
							if (document.getElementById('lgt1')[j].value.split(':')[1]!="D" && document.getElementById('lgt1')[j].value.split(':')[1]!="I")
								{
								document.getElementById('select' + dropdown_pos).add(new Option(document.getElementById("lgt1")[j].innerHTML,document.getElementById("lgt1")[j].value));
								}
						}
					//document.getElementById('lgt1').disabled=true;
					dropdown_pos++;
				}
	}
	else if (dropdown_pos>1)
	{
		var currentIndex = document.getElementById('select' + (dropdown_pos-1)).selectedIndex;
		if (document.getElementById('select' + (dropdown_pos-1)).value.split(':')[1]=="I")
		{
			divs.innerHTML += "<br />Local Government Type : <select path='lBTList' name='lBTList' onchange='setval(select" + dropdown_pos + "," + dropdown_pos + ")' id='select" + dropdown_pos + "'><option value=''>- - Select - -</option></select>";
			divs.innerHTML += "<span id='buttons2'><input type='button' name='btn3' value='Add Lower LG Type' onclick='changeTab3(dynamicdiv3)' /><input type='button' name='Reset' value='Reset' onclick='resets()'/></span>";
			document.getElementById("buttons").style.display="none";
			
			for (var j=1;j<document.getElementById('select' + (dropdown_pos-1)).length;j++)
				{
					if (document.getElementById('select' + (dropdown_pos-1))[j].value.split(':')[1]!="I")
						{
						document.getElementById('select' + dropdown_pos).add(new Option(document.getElementById('select' + (dropdown_pos-1))[j].innerHTML,document.getElementById('select' + (dropdown_pos-1))[j].value));
						}
				}
			document.getElementById('select' + (dropdown_pos-1)).selectedIndex=currentIndex;
			//document.getElementById('select' + (dropdown_pos-1)).disabled=true;
			dropdown_pos++;
		}
	}
}

function resets()
{
	document.getElementById('dynamicdiv3').innerHTML = "";
	document.getElementById('lgt1').disabled=false;
	document.getElementById("buttons").style.visibility="visible";
	if (document.getElementById("buttons1")!=null)
		document.getElementById("buttons1").style.visibility="hidden";
	if (document.getElementById("buttons2")!=null)
		document.getElementById("buttons2").style.visibility="hidden";
	dropdown_pos=1;
}

function changeTab4(divsID)
{
	var divs=document.getElementById(divsID);
	var tmptxtE = new Array();
	var tmptxtL = new Array();
	if (dynstart4==1)
	{
		dynfirst4=i;
		dynstart4=i;
	}
	dynend4=i;
	for(var j=dynfirst4;j<dynend4;j++)
	{
		if(document.getElementById("dtxtE" + j)!=null)
			{
				tmptxtE[j]=document.getElementById("dtxtE" + j).value;
				tmptxtL[j]=document.getElementById("dtxtL" + j).value;
			}
	}
	 divs.innerHTML += "<div id=div" + i + "><label>" + document.getElementById("lbl_E").innerHTML + "</label><input id='dtxtE" + i + "' name='subTypeName' type='text' class='frmfield' style='width: 200px' maxlength='50' /><label id='lblE"+ i +"' /> &nbsp;<span style='height: 15px; padding-top: 3px;'class='errormsg'></span><label>" + document.getElementById("lbl_L").innerHTML + "</label><input id='dtxtL" + i + "' name='subTypeLocal' type='text' class='frmfield' style='width: 200px' maxlength='50' /><label id='lblL"+ j +"' /><span style='height: 15px; padding-top: 3px;' class='errormsg'></span><label></label>&nbsp;<img src='images/delete.png' onclick='div"+ i + ".parentNode.removeChild(div"	+ i + ")' /></div>";
	
	for(var j=dynfirst4;j<dynend4;j++)
	{
		if(document.getElementById("dtxtE" + j)!=null)
			{
				document.getElementById("dtxtE" + j).value=tmptxtE[j];
				document.getElementById("dtxtL" + j).value=tmptxtL[j];
			}
	}
	i++;

}

function toggle(idname1,idname2,pos,total){
	
	if (total<pos)
		allchk = pos;
	else if (allchk==0)
		allchk = total;
	
	for (var i=1;i<=allchk;i++)
	{
		if (pos==i && document.getElementById(idname1 + i)!=null)
			{
			    document.getElementById(idname1 + i).disabled = !document.getElementById(idname1 + i).disabled;
				document.getElementById(idname2 + i).disabled = !document.getElementById(idname2 + i).disabled;
				document.getElementById(idname1 + i).value="";
				document.getElementById(idname2 + i).value=""; 
				
				if(document.getElementById(i).checked)
					{
						//Change class of textboxes so that user knows these fields are now enabled
						document.getElementById('txtNE' + i).className = "frmfield";
						document.getElementById('txtNL' + i).className = "frmfield";
						
						var isThere = false;
						for(var j=0;j<document.getElementById("lgt0").length;j++)
							{
								if (document.getElementById("lgt0")[j].value==document.getElementById("chkCategory" + i).value.split(' ').join(''))
									isThere = true;
							}
						if (isThere==false)
							{
								var tmp = document.getElementById("chkCategory" + i).value.split('####');
								
								$("#lgt0").append("<option value="+document.getElementById("chkCategory" + i).value.split(' ').join('')+">"+document.getElementById("chkCategory" + i).value.split(' ').join('')+"</option>");
								$("#lgt").append("<option value="+document.getElementById("chkCategory" + i).value.substr(5,document.getElementById("chkCategory" + i).value.length-10)+">"+document.getElementById("chkCategory" + i).value.substr(5,document.getElementById("chkCategory" + i).value.length-10)+"</option>");
								$("#lgt2").append("<option id=" + tmp[2] + " value="+document.getElementById("chk" + i).value+">"+document.getElementById("chk" + i).value+"</option>");
								$("#lgt3").append("<option id=x" + tmp[2] + " value="+document.getElementById("chk" + i).value+">"+document.getElementById("chk" + i).value+"</option>");
							}
					}
				else
					{
						//Change class of textboxes so that user knows these fields r now enabled
						document.getElementById('txtNE' + i).className = "disabled";
						document.getElementById('txtNL' + i).className = "disabled";
						
						for(var j=0;j<document.getElementById("lgt0").length;j++)
						{
							if (document.getElementById("lgt0")[j].value==document.getElementById("chkCategory" + i).value.split(' ').join(''))
								{
									document.getElementById("lgt0").selectedIndex = j;
									document.getElementById("lgt").selectedIndex = j;
									document.getElementById("lgt2").selectedIndex = j;
									document.getElementById("lgt3").selectedIndex = j;
									removeSelectedValue('lgt0');
									removeSelectedValue('lgt');
									removeSelectedValue('lgt2');
									removeSelectedValue('lgt3');
								}
						}
					}
			}		
		else
			{
				if (document.getElementById(idname1 + i)!=null)
					{
						if (document.getElementById(idname1 + i).checked=false)
						{
							document.getElementById(idname1 + i).disabled = true;
						}
						if (document.getElementById(idname2 + i).checked=false)
						{
							document.getElementById(idname2 + i).disabled = true;
						}
					}
			}
	}
}

function showsubmit(val)
{
	if (val)
		{
			document.getElementById("Submit").style.visibility="visible";
			refreshpage3();
			next(2);
			getSubTypes();
		}
	else
		{
			document.getElementById("Submit").style.visibility="hidden";
			previous(3);
		}
}

function refreshpage3()
{
	for (var j=1;j<document.getElementById('lgt2').length;j++)
		{
			document.getElementById('lgt2').selectedIndex=j;
			persist4();
		}
	for (var j=document.getElementById('lgt2').length;j>1;j--)
	{
		removeSelectedValue('lgt2');
	}
	dwr.util.removeAllOptions('lgt2');
	
}

if ( window.addEventListener ) { 
    window.addEventListener( "load", refreshpage3, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", refreshpage3 );
 } else 
       if ( window.onLoad ) {
          window.onload = refreshpage3;
 }


function addControls(url)
{
	
	
}
function newContact()
{
	window.location = "saveContact.do";
}

function deleteContact(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
	
	
	var checkedRadio;
	function ClearRd(chkRd) {
		if (checkedRadio == chkRd) {
			
			chkRd.checked = false;
			checkedRadio = null;
			document.getElementById("txtOrderNo").style.display = 'block';
		} else {
			checkedRadio = chkRd;
			document.getElementById("txtOrderNo").style.display = 'none';
		}
	}


}



function getSubTypes() {
	tempCD = document.getElementById("subTypcd").value.split(",");
	tempE = document.getElementById("subTypE").value.split(",");
	tempL = document.getElementById("subTypL").value.split(",");
	var z = 1;
	for ( var k = 1; k < newsubtype; k++) {
		for ( var i = 0; i < tempE.length - 1; i++) {
			if (document.getElementById('subtypehidden' + k).value == tempCD[i]) {
				changeTab4('newsubtype' + k);
				document.getElementById("dtxtE" + z).value = tempE[i];
				document.getElementById("dtxtL" + z).value = tempL[i];
				z++;
			}
		}
	}
}