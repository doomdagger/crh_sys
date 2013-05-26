Ext.define('CrhSys.store.realtimedata.RealtimeDatas', {
    extend : 'Ext.data.Store',
    requires : ["CrhSys.model.realtimedata.RealtimeData"],
	alias : 'store.realtimedatas',
	model	: "CrhSys.model.realtimedata.RealtimeData",

	proxy	: {
		type	: 'ajax',
		url	: 'serverside/realtimeDataList.json',
		reader	: {
			type	: 'json',
			root	: 'data'
		}
	},
});