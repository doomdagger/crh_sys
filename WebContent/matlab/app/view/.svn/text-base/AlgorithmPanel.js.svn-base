Ext.define("CrhSys.view.AlgorithmPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.algorithmpanel',
	requires : ["CrhSys.view.FormPanel","CrhSys.view.ResultFormPanel"],

	//title : "Algorithm",
	//header : true,
	layout : {
		type : "hbox",
		align : "stretch"
	},
	initComponent : function(){
		var me = this;
		
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	
	buildItems : function(){
		return [{
			xtype : "panel",
			title : "Algorithm",
			layout : "fit",
			items : [{
				xtype : "alformpanel",
				itemId : "alformpanel",
				autoScroll : true
			}],
			flex : 4
		},{
			xtype : "panel",
			title : "Result",
			layout : "fit",
			items : [{
				xtype : "resultformpanel",
				itemId : "resultformpanel",
				autoScroll : true
			}],
			itemId : "resultpanel",
			flex : 3
		}];
	}
});