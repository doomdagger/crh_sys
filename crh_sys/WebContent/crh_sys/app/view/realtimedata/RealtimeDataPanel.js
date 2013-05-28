Ext.define("CrhSys.view.realtimedata.RealtimeDataPanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.realtimedatapanel",
	requires : ["CrhSys.store.realtimedata.CrhNos","CrhSys.model.realtimedata.CrhNo",
				'CrhSys.store.realtimedata.RealtimeDatas',"CrhSys.model.realtimedata.RealtimeData",
				'CrhSys.view.realtimedata.CartGrid'],
	title : "实时数据",
	layout : "fit",
	
	items : [{
		xtype : 'panel',
		dockedItems : [{
			xtype : "toolbar",
			layout : {
				type : 'hbox',
				align : 'middle'
			},
			defaults : {
				xtype : 'button',
				scale : 'medium',
				text : 'Cart Pic',
				flex : 1
			},
			items : [{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			},{
				iconCls : ''
			}]
		}],
		layout : 'fit',
		items : [{
			xtype : 'cartgrid',
			itemId : 'cartGrid'
		}],
	}],
	
	
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		
		
		//me.html = "<iframe src='../app/realtimedata.html' width='100%' height='100%'></iframe>";
		
		me.callParent();
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