Ext.define('CrhSys.controller.realtimedata.RealtimeData',{
	extend      : 'Ext.app.Controller',
	models		: [
		"CrhSys.model.realtimedata.CrhNo"
	],
	stores		: [
		"CrhSys.store.realtimedata.CrhNos"
	],
	views		: [
		"CrhSys.view.realtimedata.RealtimeDataPanel"
	],

	refs	: [{
		ref			: 'dockedForm',
		selector	: '#realtimedatapanel #dockedForm'
	}],

	init   : function(){
		//var me = this;

		
	}
});