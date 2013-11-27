Ext.define("CrhSys.store.realtimedata.CrhNos",{
	extend : "Ext.data.Store",
	requires : ["CrhSys.model.realtimedata.CrhNo"],
	alias : "store.crhnos",
	
	model : "CrhSys.model.realtimedata.CrhNo",
	
	proxy : {
		type : "ajax",
		url : "crhList",
		reader : {
			type : "json",
			root : "data"
		}
	},
	autoLoad : true
});