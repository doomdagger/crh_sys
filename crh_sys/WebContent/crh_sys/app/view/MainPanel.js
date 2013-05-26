Ext.define("CrhSys.view.MainPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.mainpanel',
	requires : ["CrhSys.view.HomePanel",
	"CrhSys.view.realtimedata.RealtimeDataPanel",
	"CrhSys.view.realtimecurve.RealtimeCurvePanel",
	"CrhSys.view.historydata.HistoryPanel"],

	header : false,
	layout : 'card',
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	
	buildItems : function(){
		return [{
			xtype : 'homepanel',
			itemId : 'homepanel'
		},{
			xtype : 'realtimedatapanel',
			itemId : 'realtimedatapanel',
			controller : 'CrhSys.controller.realtimedata.RealtimeData'
		},{
			xtype : 'realtimecurvepanel',
			itemId : 'realtimecurvepanel'
		},{
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
	
		var realtimedataItem = Ext.create("Ext.menu.Item",{
			text : "实时数据",
			itemId : 'realtimedataItem'
		});
		
		
		var realtimecurveItem = Ext.create("Ext.menu.Item",{
			text : "实时曲线",
			itemId : "realtimecurveItem"
		});
		

		var historydataItem = Ext.create("Ext.menu.Item",{
			text : "历史数据",
			itemId : "historydataItem"
		});
		
		
		return [{
			xtype : "toolbar",
			docked : 'top',
			itemId : "maintoolbar",
			items : [ homeButton,
			{
				xtype : "button",
				text : "在线监测",
				menu : [realtimedataItem,realtimecurveItem,historydataItem]
			},{
				xtype : "tbfill"
			},{
				xtype : "button",
				text : "2013-05-22 10:44:03"
			}]
		}];
	}
});