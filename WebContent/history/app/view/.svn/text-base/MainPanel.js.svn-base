Ext.define("CrhSys.view.MainPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.mainpanel',
	requires : ["CrhSys.view.HomePanel",
	"CrhSys.view.realtimedata.RealtimeDataPanel",
	"CrhSys.view.realtimecurve.RealtimeCurvePanel",
	"CrhSys.view.historydata.HistoryPanel"],

	header : false,
	layout : 'fit',
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	
	buildItems : function(){
		return [{
			xtype : 'historypanel',
			itemId : 'historypanel'
		}];
	},
	
	buildDockedItems : function(){
		
		var homeButton = Ext.create("Ext.button.Button",{
			text : "主页",
			iconCls : "home-icon16",
			itemId : 'homebutton'
		});
	
		

		var historydataItem = Ext.create("Ext.button.Button",{
			text : "历史数据",
			itemId : "historydataItem",
			iconCls : 'save-icon16'
		});
		
		
		return [{
			xtype : "toolbar",
			docked : 'top',
			itemId : "maintoolbar",
			items : [ homeButton,
			          historydataItem,{
				xtype : "tbfill"
			},{
				xtype : "button",
				text : "2013-05-22 10:44:03"
			}]
		}];
	}
});