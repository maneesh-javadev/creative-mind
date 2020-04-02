/* Our method which is called during initialization of the toolbar.*/document
		.write("<script type='text/javascript' src='js/oporations.js'></script>");
function addmsg() {
}/* Disable button toggling. */
addmsg.prototype.GetState = function() {
	return FCK_TRISTATE_OFF;
}/* Our method which is called on button click. */
addmsg.prototype.Execute = function() {
	insertLblInFckEditor();
}/* Register the command. */
FCKCommands.RegisterCommand('addmsg', new addmsg());/* Add the button. */
var item = new FCKToolbarButton('addmsg', 'Add Msg');/*
														 * item.IconPath =
														 * FCKPlugins.Items['addmsg'].Path +
														 * 'addmsg.gif';
														 */
item.IconPath = 'images/' + 'addmsg.gif';
FCKToolbarItems.RegisterItem('addmsg', item);
