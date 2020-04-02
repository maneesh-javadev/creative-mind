document
		.write("<script type='text/javascript' src='js/oporations.js'></script>");

// Our method which is called during initialization of the toolbar.
function addlbl() {
}

// Disable button toggling.
addlbl.prototype.GetState = function() {
	return FCK_TRISTATE_OFF;
}

// Our method which is called on button click.
addlbl.prototype.Execute = function() {
	insertHtmlInFckEditor();
}

// Register the command.
FCKCommands.RegisterCommand('addlbl', new addlbl());

// Add the button.
var item = new FCKToolbarButton('addlbl', 'Insert Html');
// item.IconPath = FCKPlugins.Items['addlbl'].Path + 'addlbl.gif';
item.IconPath = 'images/' + 'addlbl.gif';
FCKToolbarItems.RegisterItem('addlbl', item);
