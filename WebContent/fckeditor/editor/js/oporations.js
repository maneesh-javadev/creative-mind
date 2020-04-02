function insertHtmlInFckEditor(){
	
	var EditorInstance = FCKeditorAPI.GetInstance("templateBodySrc"); //message is name of field to be validate
//	EditorInstance.EditorDocument.body.innerHTML+="<table border='1' widtha='100%' height='20%'><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>" +
//	"<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>";

	EditorInstance.InsertHtml("<table border='1' widtha='100%' height='20%'><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>" +
	"<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");
	
	return false;
}


function insertLblInFckEditor(){
//	alert("insertLblInFckEditor");
	var EditorInstance = FCKeditorAPI.GetInstance("templateBodySrc"); //message is name of field to be validate
	EditorInstance.InsertHtml("<hr style='width:100%;'/>");
//	alert(document.getElementById("selType").value);
//	EditorInstance.EditorDocument.body.innerHTML+="<hr style='width:100%;'/>";

	return false;
}

















