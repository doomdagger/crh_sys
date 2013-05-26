Ext.define("CrhSys.view.realtimedata.RealtimeDataPanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.realtimedatapanel",
	requires : ["CrhSys.store.realtimedata.CrhNos","CrhSys.model.realtimedata.CrhNo",
				'CrhSys.store.realtimedata.RealtimeDatas',"CrhSys.model.realtimedata.RealtimeData"],
	title : "实时数据",
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		
		me.items = me.buildItems();
		
		me.html = "<h1>Table will be here</h1>";
		
		me.callParent();
	},
	
	buildItems : function(){
		
		return [];
	},
	
	buildDockedItems : function(){
		//var me = this;
		
		var comboBoxStore = Ext.create("CrhSys.store.realtimedata.CrhNos");
		
		var dockedForm = Ext.create("Ext.form.Panel",{
			layout : {
				type : "hbox",
				align : "middle"
			},
			itemId : 'dockedForm',
			items : [{
				xtype : 'combobox',
				fieldLabel : "动车编号",
				labelPad : 0,
				name : "crhNo",
				margins : '10 8 10 0',
				store : comboBoxStore,
				valueField : 'crhId',
				displayField : 'crhNo',
				queryMode : "local",
				forceSelection : true,
				itemId : 'crhComboBox',
				flex : 1
			},{
				xtype : 'textfield',
				fieldLabel : "电机总数",
				labelPad : 0,
				name : "engineNum",
				margins : '10 8 10 0',
				itemId : 'engineTextField',
				flex : 1
			},{
				xtype : 'tbfill',
				flex : 2
			}]
		});
		
		return [dockedForm];
		
	}
});