Ext.define("CrhSys.view.historydata.HistoryPanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.historypanel",
	title : "历史数据",
	layout : 'fit',
	requires : ["CrhSys.view.historydata.HistoryForm",
				"CrhSys.view.historydata.HistoryGrid"],
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
				
		me.callParent();
	},
	
	items : [{
		xtype : "historygrid",
		itemId : 'historyGrid'
	}],
	
	buildDockedItems : function(){
		var formPanel = {
			xtype : "historyform",
			itemId : 'historyform'
		};
		
		
		var dockedPanel = Ext.create("Ext.panel.Panel",{
			dockedItems : [{
				xtype : "toolbar",
				itemId : 'dockedtoolbar',
				items : [{
					xtype : "button",
					text : "查询",
					scale : 'medium',
					icon : "./images/zoom.png",
					action : 'search'
				},{
					xtype : "button",
					text : "重置",
					scale : 'medium',
					icon : "./images/delete16.png",
					action : 'reset'
				}] 
			}],
			items : [formPanel]
		});
		
		return [dockedPanel];
	},
	
	buildItems : function() {
		
	}
});
