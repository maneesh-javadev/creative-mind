//<![CDATA[
CKEDITOR.config.height = '500px';
CKEDITOR.config.toolbar_Full = [
		{
			name : 'clipboard',
			items : [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ]
		},
		{
			name : 'editing',
			items : [ 'Replace', '-', 'SelectAll', '-', 'SpellChecker', 'Scayt' ]
		},
		{
			name : 'forms',
			items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ]
		},
		{
			name : 'styles',
			items : [ 'Styles', 'Format', 'Font', 'FontSize' ]
		},
		{
			name : 'colors',
			items : [ 'TextColor', 'BGColor' ]
		},
		'/',
		{
			name : 'basicstyles',
			items : [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ]
		},
		{
			name : 'paragraph',
			items : [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter',
					'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl' ]
		}, {
			name : 'links',
			items : [ 'Link', 'Unlink', 'Anchor' ]
		}, {
			name : 'insert',
			items : [ 'Image', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak' ]
		} ];

/**
 * This call can be placed at any point after the <textarea>, or inside a <head><script>
 * in a window.onload event handler. On paste function use to handle paste
 * event. Replace the <textarea id="editor"> with an CKEditor instance, using
 * default configurations.
 */
CKEDITOR.on('instanceReady', function(ev) {
	ev.editor.on('paste', function(ev) {
		ev.data.html = ev.data.html.replace(/<img( [^>]*)?>/gi, '');
	});
});
CKEDITOR.on('dialogDefinition', function(ev) {
	// Take the dialog name and its definition from the event data.
	var dialogName = ev.data.name;
	var dialogDefinition = ev.data.definition;

	// Check if the definition is from the dialog we're interested on (the
	// 'Image' dialog).
	if (dialogName == 'image') {
		// Remove the 'Link' and 'Advanced' tabs from the 'Image' dialog.
		dialogDefinition.removeContents('Link');
		dialogDefinition.removeContents('advanced');
	}
});

// ]]>
