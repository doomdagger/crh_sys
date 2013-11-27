Ext.define("CrhSys.view.ResultFormPanel",{
	extend : "Ext.form.Panel",
	alias : "widget.resultformpanel",
	
	
	initComponent : function() {
		var me = this;
		
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	buildItems : function(){
		
		
		var crhNo = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "hbox",
				align : 'middle'
			},
			items : [{
		        xtype: 'displayfield',
		        margin :"2 2 2 20",
		        fieldLabel: '动车编号',
		        name: 'crhNo',
		        itemId : "crhNo",
		        value: ' ',
		        flex : 3
		    },{
				xtype : "tbfill",
				flex : 2
			}]
		});
		
		var engineNo = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "hbox",
				align : 'middle'
			},
			items : [{
		        xtype: 'displayfield',
		        margin :"2 2 2 20",
		        fieldLabel: '电机编号',
		        name: 'engineNo',
		        itemId : "engineNo",
		        value: ' ',
		        flex : 3
		    },{
				xtype : "tbfill",
				flex : 2
			}]
		});
		
		var engineStatus = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "hbox",
				align : 'middle'
			},
			items : [{
		        xtype: 'displayfield',
		        margin :"2 2 2 20",
		        fieldLabel: '电机状态',
		        name: 'engineStatus',
		        itemId : "engineStatus",
		        value: ' ',
		        flex : 3
		    },{
				xtype : "tbfill",
				flex : 1
			}]
		});
		
		
		return [crhNo, engineNo, engineStatus];
	}
});