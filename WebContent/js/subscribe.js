function manageOperation(op, index) {
	/* alert(op+":"+index); */
	if (op == 'D' || op == 'G') {
		var con = confirm("Are You Sure delete the record!");
		if (con == true) {
			flagname = "scbscribeDetails[" + index + "].flag";
			/*
			 * alert(flagname); alert(index);
			 */
			document.getElementById('row' + index).style.visibility = 'hidden';
			document.getElementById('row' + index).style.display = 'none';
			document.getElementById(flagname).value = op;
			var del = document.getElementById('bindex').value;
			// var size=parseInt(document.getElementById('index').value)-1;
			del = del + index + ",";
			document.getElementById('bindex').value = del;
			// document.getElementById(flagname).value=op;
			/* alert(document.getElementById(flagname).value); */
		}
	} else {
		// alert(op);
		flilename = "scbscribeDetails[" + index;
		flagname = flilename + "].flag";
		appname = flilename + "].applicationName";
		urlname = flilename + "].url";
		/*
		 * alert(flagname); alert(appname);
		 */
		document.getElementById('dt' + index).style.visibility = 'visible';
		document.getElementById('dt' + index).style.display = 'block';
		document.getElementById('dl' + index).style.visibility = 'hidden';
		// document.getElementById('dl'+index).style.display = 'hidden';
		document.getElementById('ut' + index).style.visibility = 'visible';
		document.getElementById('ut' + index).style.display = 'block';
		document.getElementById('ul' + index).style.visibility = 'hidden';
		// document.getElementById('ul'+index).style.display = 'hidden';

		document.getElementById(flagname).value = op;
		// document.getElementById(flagname).value=op;
		/* alert(document.getElementById(flagname).value); */
		// scbscribeDetails[0].flag
	}

}

function insertRow(op) {
	var size = parseInt(document.getElementById('index').value);

	// var indexi=size;
	// var bindex=parseInt(document.getElementById('bindex').value);
	// alert(bindex);
	// alert("size:"+size);

	/*
	 * var tmptextapp=new Array(); var temptexturl=new Array();
	 */
	/*
	 * var k=0; for(var j=bindex;j<size;j++) {
	 * 
	 * alert(":"+document.getElementById("scbscribeDetails["+j+"].applicationName").value+":"+document.getElementById("scbscribeDetails["+j+"].url"));
	 * if(document.getElementById("scbscribeDetails["+j+"].applicationName")!=null &&
	 * document.getElementById("scbscribeDetails["+j+"].url")!=null) {
	 * 
	 * tmptextapp[k]=document.getElementById("scbscribeDetails["+j+"].applicationName").value;
	 * temptexturl[k]=document.getElementById("scbscribeDetails["+j+"].url").value;
	 * k++; } }
	 */

	var table = document.getElementById('table1');
	var tr = document.createElement('TR');
	var td1 = document.createElement('TD');
	var td2 = document.createElement('TD');
	var td3 = document.createElement('TD');
	var td4 = document.createElement('TD');
	var td5 = document.createElement('TD');
	var td6 = document.createElement('TD');
	var inp0 = document.createElement('INPUT');
	var inp1 = document.createElement('INPUT');
	var inp2 = document.createElement('INPUT');
	var inp3 = document.createElement('INPUT');
	var div1 = document.createElement('DIV');

	tr.setAttribute("class", "tblRowB");
	tr.setAttribute("id", "row" + size);
	// tr.class = "tblRowB";
	td4.setAttribute("align", "center");
	td5.setAttribute("align", "center");
	inp0.setAttribute("Name", "sno");
	inp0.setAttribute("value", size + 1);
	inp0.setAttribute("readonly", "readonly");

	inp1.setAttribute("Name", "scbscribeDetails[" + size + "].applicationName");
	inp1.setAttribute("id", "scbscribeDetails[" + size + "].applicationName");
	inp1.setAttribute("size", "35");
	inp1.setAttribute("class", "frmfield");

	inp2.setAttribute("Type", "hidden");
	inp2.setAttribute("Name", "scbscribeDetails[" + size + "].flag");
	inp2.setAttribute("id", "scbscribeDetails[" + size + "].flag");
	inp2.setAttribute("Value", "I");
	inp2.setAttribute("class", "frmfield");

	inp3.setAttribute("Name", "scbscribeDetails[" + size + "].url");
	inp3.setAttribute("id", "scbscribeDetails[" + size + "].url");
	inp3.setAttribute("size", "35");
	inp3.setAttribute("class", "frmfield");
	inp3.setAttribute("onblur", "validate_textbox(" + size + ");");

	div1.setAttribute("id", "error" + size);

	var editIcon = document.createElement('IMG');
	editIcon.setAttribute('src', 'images/edit.png');
	var deleteIcon = document.createElement('IMG');
	deleteIcon.setAttribute('src', 'images/delete.png');
	deleteIcon.setAttribute("onclick", "manageOperation('G'," + size + ")");
	table.appendChild(tr);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	tr.appendChild(td6);
	td1.appendChild(inp0);
	td2.appendChild(inp1);
	td2.appendChild(inp2);
	td3.appendChild(inp3);
	/* td4.appendChild(editIcon); */
	td5.appendChild(deleteIcon);
	td6.appendChild(div1);

	/*
	 * document.getElementById("insertNew").innerHTML += "<tr id='record"+size+"><td width='33%'><label>
	 * "+(size+1) + "</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='33%'>" + "<label><input
	 * type='text' name='scbscribeDetails["+size+"].applicationName'
	 * id='scbscribeDetails["+size+"].applicationName' size='35'
	 * class='frmfield'>" + "<input type='hidden'
	 * name='scbscribeDetails["+size+"].flag'
	 * id='scbscribeDetails["+size+"].flag' value='I' /></label>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</td><td width='34'>" + "<input
	 * type='text' name='scbscribeDetails["+size+"].url'
	 * id='scbscribeDetails["+size+"].url' size='34'
	 * onblur='validate_textbox("+size+");' class='frmfield'></td><td width='20%'><div
	 * id='error"+size+"'></div></td> </tr>";
	 */

	/*
	 * document.getElementById("insertNew").innerHTML += " <div
	 * id='record"+size+"'><tr><td width='33%'><label> "+(size+1) + "</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='33%'>" + "<label><input
	 * type='text' name='scbscribeDetails["+size+"].applicationName'
	 * id='scbscribeDetails["+size+"].applicationName' size='35'
	 * class='frmfield'>" + "<input type='hidden'
	 * name='scbscribeDetails["+size+"].flag'
	 * id='scbscribeDetails["+size+"].flag' value='I' /></label>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</td><td width='34'>" + "<input
	 * type='text' name='scbscribeDetails["+size+"].url'
	 * id='scbscribeDetails["+size+"].url' size='34'
	 * onblur='validate_textbox("+size+");' class='frmfield'></td><td width='20%'><div
	 * id='error"+size+"'></div></td> </tr></div>";
	 */
	/*
	 * document.getElementById("addgisnodes").innerHTML += "<div id=div" + t + "><td width='5'><input
	 * type='text' name='lati' id='lati" + t + "' class='frmfield'/></td><td ></td><td width='5'>&nbsp;&nbsp;<input
	 * type='text' name='longi' id='longi" + t + "' class='frmfield'></td><td><input
	 * type='button' value='Remove' onclick='div" + t +
	 * ".parentNode.removeChild(div" + t +")'/></td></div>";
	 */
	/*
	 * alert(size); this.manageOperation('I',size);
	 */
	/*
	 * k=0; for(var j=bindex;j<size;j++) {
	 * 
	 * 
	 * document.getElementById("scbscribeDetails["+j+"].applicationName").value=tmptextapp[k];
	 * document.getElementById("scbscribeDetails["+j+"].url").value=temptexturl[k];
	 * k++; }
	 */

	size = size + 1;
	// alert("up"+size);
	document.getElementById('index').value = size;
	size = parseInt(document.getElementById('index').value);
	// document.getElementById('bindex').value=false;
	// alert("last"+size);
}

function validate_textbox(index) {
	// alert("hi"+index);
	var patterna, patternu;
	var validateflag;

	patterna = /^[A-Za-z,.-]+$/;

	patternu = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;

	var name = "scbscribeDetails[" + index + "].applicationName";
	var url = "scbscribeDetails[" + index + "].url";
	// alert(document.getElementById(name).value+":"+document.getElementById(url).value);
	// alert(name+":"+url);
	if ((document.getElementById(name).value) != "" && (document.getElementById(url).value) != "") {

		if (patterna.test(document.getElementById(name).value)) {
			// alert('Valid Name.');
			if (patternu.test(document.getElementById(url).value)) {
				document.getElementById("error" + index).innerHTML = "";
				return true;
			}

			else {
				document.getElementById("error" + index).innerHTML = "<font color='red'><br>Please enter URL in this format 'http://abc.com' </font>";
				return false;
			}
		} else {
			// alert('Invalid Name.'+index);

			document.getElementById("error" + index).innerHTML = "<font color='red'><br>Please use [A-Z],[a-z], space, [.,-] in the application name</font>";
			return false;

			// document.getElementById("scbscribeDetails["+index+"].applicationName").focus();
		}

	} else {

		document.getElementById("error" + index).innerHTML = "<font color='red'><br>Application Name and URL must be enter</font>";
		return false;
	}

	document.getElementById("validate_flag").value = validateflag;
}

function validate_submit() {

	// alert(document.getElementById('bindex').value);
	var del = document.getElementById('bindex').value;
	// var j=new Array();
	var j = del.split(",");
	var index = document.getElementById('index').value;
	// alert("index"+index);
	var delflag = true;
	var newi;
	for ( var i = 0; i < index; i++) {
		delflag = true;
		for ( var k = 0; k < j.length; k++) {
			newi = parseInt(j[k]);
			if (newi == i) {
				delflag = false;

			}

		}

		if (delflag) {
			flag = validate_textbox(i);
		} else {
			flag = validate_textbox(i);
		}
		// alert(flag);
		/*
		 * if(!flag) {
		 * 
		 * //var document.getElementById("row"+i); var
		 * tr=document.getElementById("row"+i);
		 * tr.setAttribute("bgcolor","RED"); exit; }
		 */

	}

	if (flag) {
		// alert("jj");
		document.forms['form1'].submit();
	}

}
