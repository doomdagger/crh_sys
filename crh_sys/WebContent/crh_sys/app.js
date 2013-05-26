Ext.Loader.setConfig({
    enabled:true
});
Ext.application({
	name				: 'CrhSys',
	appFolder			: 'app',
	controllers			: [
		'CrhSys.controller.main.Main'
	],
	autoCreateViewport	: true
});