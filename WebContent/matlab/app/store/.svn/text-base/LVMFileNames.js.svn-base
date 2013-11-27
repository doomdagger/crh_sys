Ext.define("CrhSys.store.LVMFileNames",{
	extend : "Ext.data.Store",
	requires : ["CrhSys.model.LVMFileName"],
	
	model : "CrhSys.model.LVMFileName",
	
	proxy : {
		type : "ajax",
		url : "LVMFileList",
		reader : {
			type : "json",
			root : "data"
		}
	},
	autoLoad : true
});