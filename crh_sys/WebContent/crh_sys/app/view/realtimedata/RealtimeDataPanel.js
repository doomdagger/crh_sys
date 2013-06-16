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
				scale : 'large',
				text : '',
				flex : 1
			},
			items : [{
				itemId : 'cartIcon1',
				iconCls : ''
			},{
				itemId : 'cartIcon2',
				iconCls : ''
			},{
				itemId : 'cartIcon3',
				iconCls : ''
			},{
				itemId : 'cartIcon4',
				iconCls : ''
			},{
				itemId : 'cartIcon5',
				iconCls : ''
			},{
				itemId : 'cartIcon6',
				iconCls : ''
			},{
				itemId : 'cartIcon7',
				iconCls : ''
			},{
				itemId : 'cartIcon8',
				iconCls : ''
			},{
				itemId : 'cartIcon9',
				iconCls : ''
			},{
				itemId : 'cartIcon10',
				iconCls : ''
			},{
				itemId : 'cartIcon11',
				iconCls : ''
			},{
				itemId : 'cartIcon12',
				iconCls : ''
			},{
				itemId : 'cartIcon13',
				iconCls : ''
			},{
				itemId : 'cartIcon14',
				iconCls : ''
			},{
				itemId : 'cartIcon15',
				iconCls : ''
			},{
				itemId : 'cartIcon16',
				iconCls : ''
			},{
				itemId : 'cartIcon17',
				iconCls : ''
			},{
				itemId : 'cartIcon18',
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