Ext.define("CrhSys.view.MainPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.mainpanel',
	requires : ["CrhSys.view.realtimedata.RealtimeDataPanel",
	"CrhSys.view.realtimecurve.RealtimeCurvePanel"],

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
			xtype : 'realtimecurvepanel',
			itemId : 'realtimecurvepanel'
		},{
			xtype : 'realtimedatapanel',
			itemId : 'realtimedatapanel',
			controller : 'CrhSys.controller.realtimedata.RealtimeData'
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
			itemId : 'realtimedataItem',
			iconCls : 'invoices-icon16'
		});
		
		
		var realtimecurveItem = Ext.create("Ext.menu.Item",{
			text : "实时曲线",
			itemId : "realtimecurveItem",
			icon : './images/page_go.png'
		});
		
		
		return [{
			xtype : "toolbar",
			docked : 'top',
			itemId : "maintoolbar",
			items : [ homeButton,
			{
				xtype : "button",
				text : "在线监测",
				iconCls : 'categories-icon16',
				menu : [realtimedataItem,realtimecurveItem]
			},{
				xtype : "tbfill"
			},{
				itemId : "refreshButton",
				xtype : "button",
				icon : "./images/page_go.png",
				text : "--"
			},{
				xtype : "button",
				text : "2013-05-22 10:44:03"
			}]
		}];
	}
});