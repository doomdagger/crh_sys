Ext.define("CrhSys.view.historydata.HistoryForm",{
	extend : "Ext.form.Panel",
	alias : "widget.historyform",
	requires : ["CrhSys.store.realtimedata.CrhNos","CrhSys.model.realtimedata.CrhNo",
	"CrhSys.store.realtimecurve.EngineNos"],
	initComponent : function() {
		var me = this;
		
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	buildItems : function(){
		var comboBoxStore = Ext.create("CrhSys.store.realtimedata.CrhNos");
		var comboBoxStore2 = Ext.create("CrhSys.store.realtimecurve.EngineNos");
		
		var combobox = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "hbox",
				align : 'middle'
			},
			items : [{
				xtype : "combobox",
				fieldLabel : "动车编号",
				margin :"8 10 5 5",
				name : "crhId",
				store : comboBoxStore,
				valueField : 'crhId',
				displayField : 'crhNo',
				queryMode : "local",
				forceSelection : true,
				itemId : 'crhComboBox',
				flex : 1
			},{
				xtype : "combobox",
				fieldLabel : "电机编号",
				margin : "8 10 5 5",
				name : "engineId",
				store : comboBoxStore2,
				valueField : 'id',
				displayField : 'engineNo',
				queryMode : 'local',
				forceSelection : true,
				itemId : 'engineComboBox',
				flex : 1
			},{
				xtype : "tbfill",
				flex : 2
			}]
		});
		
		var date = Ext.create("Ext.form.FieldContainer",{
			fieldLabel : "",
			layout : {
				type : "hbox",
				align : "middle"
			},
			items : [{
				xtype : "datefield",
				fieldLabel : "起始日期",
				margin : "4 10 0 5",
				name : "startDate",
				maxValue : new Date(),
				flex : 1
			},{
				xtype : "datefield",
				fieldLabel : "终止日期",
				margin : "4 10 4 5",
				name : "endDate",
				maxValue : new Date(),
				flex : 1
			},{
				xtype : "tbfill",
				flex : 2
			}]
		});
		
		return [combobox, date];
	}
});