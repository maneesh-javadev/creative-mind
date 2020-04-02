/*
 *
 * FCKeditor - The text editor 
 *@author : Ajay Kumar on 8th November 2011
 */

var FCKeditorAPI;

function InitializeAPI() {
	var oParentWindow = window.parent;

	if (!(FCKeditorAPI = oParentWindow.FCKeditorAPI)) {
		// Make the FCKeditorAPI object available in the parent window. Use
		// eval so this core runs in the parent's scope and so it will still be
		// available if the editor instance is removed ("Can't execute code
		// from a freed script" error).
		// Note: we check the existence of oEditor.GetParentForm because some
		// external
		// code (like JSON) can extend the Object prototype and we get then
		// extra oEditor
		// objects that aren't really FCKeditor instances.

		var sScript = 'window.FCKeditorAPI = {'
				+ 'Version : "2.6.5",'
				+ 'VersionBuild : "23959",'
				+ 'Instances : window.FCKeditorAPI && window.FCKeditorAPI.Instances || {},'
				+

				'GetInstance : function( name )'
				+ '{'
				+ 'return this.Instances[ name ];'
				+ '},'
				+

				'_FormSubmit : function()'
				+ '{'
				+ 'for ( var name in FCKeditorAPI.Instances )'
				+ '{'
				+ 'var oEditor = FCKeditorAPI.Instances[ name ] ;'
				+ 'if ( oEditor.GetParentForm && oEditor.GetParentForm() == this )'
				+ 'oEditor.UpdateLinkedField() ;'
				+ '}'
				+ 'this._FCKOriginalSubmit() ;'
				+ '},'
				+

				'_FunctionQueue	: window.FCKeditorAPI && window.FCKeditorAPI._FunctionQueue || {'
				+ 'Functions : new Array(),' + 'IsRunning : false,' +

				'Add : function( f )' + '{' + 'this.Functions.push( f );'
				+ 'if ( !this.IsRunning )' + 'this.StartNext();' + '},' +

				'StartNext : function()' + '{'
				+ 'var aQueue = this.Functions ;' + 'if ( aQueue.length > 0 )'
				+ '{' + 'this.IsRunning = true;' + 'aQueue[0].call();' + '}'
				+ 'else ' + 'this.IsRunning = false;' + '},' +

				'Remove : function( f )' + '{' + 'var aQueue = this.Functions;'
				+ 'var i = 0, fFunc;' + 'while( (fFunc = aQueue[ i ]) )' + '{'
				+ 'if ( fFunc == f )' + 'aQueue.splice( i,1 );' + 'i++ ;' + '}'
				+ 'this.StartNext();' + '}' + '}' + '}';

		// In IE, the "eval" function is not always available (it works with
		// the JavaScript samples, but not with the ASP ones, for example).
		// So, let's use the execScript instead.
		if (oParentWindow.execScript)
			oParentWindow.execScript(sScript, 'JavaScript');
		else {
			if (FCKBrowserInfo.IsGecko10) {
				// FF 1.0.4 gives an error with the request bellow. The
				// following seams to work well.
				eval.call(oParentWindow, sScript);
			} else if (FCKBrowserInfo.IsAIR) {
				FCKAdobeAIR.FCKeditorAPI_Evaluate(oParentWindow, sScript);
			} else if (FCKBrowserInfo.IsSafari) {
				// oParentWindow.eval in Safari executes in the calling window
				// environment, instead
				// of the parent one. The following should
				// make it work.
				var oParentDocument = oParentWindow.document;
				var eScript = oParentDocument.createElement('script');
				eScript.appendChild(oParentDocument.createTextNode(sScript));
				oParentDocument.documentElement.appendChild(eScript);
			} else
				oParentWindow.eval(sScript);
		}

		FCKeditorAPI = oParentWindow.FCKeditorAPI;

		// The __Instances properly has been changed to the public Instances,
		// but we should still have the "deprecated" version of it.
		FCKeditorAPI.__Instances = FCKeditorAPI.Instances;
	}

	// Add the current instance to the FCKeditorAPI's instances collection.
	FCKeditorAPI.Instances[FCK.Name] = FCK;
}

// Attach to the form onsubmit event and to the form.submit().
function _AttachFormSubmitToAPI() {
	// Get the linked field form.
	var oForm = FCK.GetParentForm();

	if (oForm) {
		// Attach to the onsubmit event.
		FCKTools.AddEventListener(oForm, 'submit', FCK.UpdateLinkedField);

		// IE sees oForm.submit function as an 'object'.
		if (!oForm._FCKOriginalSubmit
				&& (typeof (oForm.submit) == 'function' || (!oForm.submit.tagName && !oForm.submit.length))) {
			// Save the original submit.
			oForm._FCKOriginalSubmit = oForm.submit;

			// Create our replacement for the submit.
			oForm.submit = FCKeditorAPI._FormSubmit;
		}
	}
}

function FCKeditorAPI_Cleanup() {
	if (window.FCKConfig && FCKConfig.MsWebBrowserControlCompat
			&& !window.FCKUnloadFlag)
		return;
	delete FCKeditorAPI.Instances[FCK.Name];
}
function FCKeditorAPI_ConfirmCleanup() {
	if (window.FCKConfig && FCKConfig.MsWebBrowserControlCompat)
		window.FCKUnloadFlag = true;
}
FCKTools.AddEventListener(window, 'unload', FCKeditorAPI_Cleanup);
FCKTools.AddEventListener(window, 'beforeunload', FCKeditorAPI_ConfirmCleanup);

var FCKeditorGlblObj = FCKeditorAPI;
//-----FCK Editor Validatio(Start)---@ajay Kumar
function ValidateFCKEditor(editorName){
	var EditorInstance = FCKeditorAPI.GetInstance(editorName);
	var EditorContent = EditorInstance.EditorDocument.body.innerHTML;
	if ((EditorContent.length == 11) || (EditorContent == "")) {
		return false;
	} else {
		return true;
	}
}
//-----FCK Editor Validatio(End)---@ajay Kumar

