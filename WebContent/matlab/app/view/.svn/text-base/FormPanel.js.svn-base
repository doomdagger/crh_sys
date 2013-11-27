Ext.define("CrhSys.view.FormPanel",{
	extend : "Ext.form.Panel",
	alias : "widget.alformpanel",
	requires : ["CrhSys.store.LVMFileNames"],
	
	layout : {
		type : "border"
	},
	
	initComponent : function() {
		var me = this;
		
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	buildItems : function(){
		var comboBoxStore = Ext.create("CrhSys.store.LVMFileNames");
		
		var combobox = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "hbox",
				align : 'middle'
			},
			items : [{
				xtype : "combobox",
				fieldLabel : "文件选择",
				margin :"8 10 5 5",
				//padding : "10",
				name : "fileName",
				store : comboBoxStore,
				valueField : 'fileName',
				displayField : 'fileName',
				queryMode : "local",
				forceSelection : true,
				itemId : 'filecombobox',
				flex : 5
			},{
				xtype : "button",
				text: "运算",
				itemId : 'computebutton',
				flex : 1
			},{
				xtype : "tbfill",
				flex : 2
			}],
			region : "center"
		});
		
		var label = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "fit",
			},
			items : [{
				xtype : "button",
				text : "file info will be displayed here",
				itemId : 'labelbutton',
			}],
			region : "south"
		});
		
		return [combobox, label];
	}
});