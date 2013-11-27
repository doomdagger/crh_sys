Ext.define("CrhSys.view.MainPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.mainpanel',
	requires : ["CrhSys.view.AlgorithmPanel","CrhSys.view.PicturePanel"],

	header : false,
	layout : {
		type : "vbox",
		align : "stretch"
	},
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	
	buildItems : function(){
		return [{
			xtype : "algorithmpanel",
			itemId : "algorithmpanel",
			flex : 1
			//region : "north"
		},{
			xtype : 'picturepanel',
			itemId : 'picturepanel',
			autoScroll : true,
			flex : 5
			//region : "center"
		}];
	},
	
	buildDockedItems : function(){
		
		var homeButton = Ext.create("Ext.button.Button",{
			text : "主页",
			iconCls : "home-icon16",
			itemId : 'homebutton'
		});
	
		
		
		return [{
			xtype : "toolbar",
			docked : 'top',
			itemId : "maintoolbar",
			items : [ homeButton,
			{
				xtype : "tbfill"
			},{
				xtype : "button",
				text : "2013-05-22 10:44:03"
			}]
		}];
	}
});