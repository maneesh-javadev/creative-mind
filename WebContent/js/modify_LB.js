var peopleCache = { };
var viewed = -1;


function fillTable(id) {
	lgdDwrlocalBodyService.getLBCoveredAreaList(id,function(LocalBodyCoveredArea) {
	    // Delete all the rows except for the "pattern" row
	    dwr.util.removeAllRows("peoplebody", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    // Create a new set cloned from the pattern row
	    var person, id;
	    LocalBodyCoveredArea.sort(function(p1, p2) { return p1.name.localeCompare(p2.name); });
	    for (var i = 0; i < LocalBodyCoveredArea.length; i++) {
	      person = LocalBodyCoveredArea[i];
	      id = person.landRegionCode;
	      dwr.util.cloneNode("pattern", { idSuffix:id });
 	      dwr.util.setValue("tableName" + id, person.landRegionNameEnglish);
	      if(person.coverageType == 'F')
	      dwr.util.setValue("tableAge" + id,'FULL');
	      if(person.coverageType == 'P')
		      dwr.util.setValue("tableAge" + id,'PART');
	
	      dwr.util.byId("pattern" + id).style.display = ""; // officially we should use table-row, but IE prefers "" for some reason
	      peopleCache[id] = person;
	    }
	  });
	}

function deleteClicked(eleid) {
	
	  var person = peopleCache[eleid.substring(6)];
	  if (confirm("Are you sure you want to delete " + person.landRegionNameEnglish.trim() + "?")) {
	    dwr.engine.beginBatch();	  
	    id="pattern"+person.landRegionCode;	 	
	    dwr.util.byId(id).style.display = "none";	 
	    dwr.engine.endBatch();
	  }
	}

function clearPerson() {
	  viewed = -1;
	  dwr.util.setValues({ id:-1, name:null, address:null, salary:null });
	}

var no_array = new Array();

function submitLink(lbCode, sel) {
	var chckebox = sel;
	var id = lbCode.substring(6);
	var remove = -1;
	if (chckebox.checked == false) {
		for ( var i = 0; i < no_array.length; i++) {
			 
			if (no_array[i] == id) {
				remove = i;
				break;
			}
		}
		if (remove > -1) {
			no_array.splice(remove, 1);
			 
		}
	} else {
		
			no_array.push(id);
		}
	// To Remove Duplicates ID
	for ( var i = 1; i < no_array.length; i++ ) {
        if ( no_array[i] === no_array[ i - 1 ] ) {
        	no_array.splice( i--, 1 );
        }
    }
	 
	   dwr.util.setValue('hdnListLbCode',no_array.valueOf());
 	  
	 
	}

	//var select_id = document.getElementById("SearchCompanies:company_id");
	//var select_idnxt = document
		//	.getElementById("SearchCompanies:company_idnxt");

	//select_id.value = no_array;
	//select_idnxt.value = no_array;
 

 