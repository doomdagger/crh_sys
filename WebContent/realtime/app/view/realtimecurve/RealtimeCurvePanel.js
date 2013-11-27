Ext.define("CrhSys.view.realtimecurve.RealtimeCurvePanel",{
	extend : "Ext.panel.Panel",
	requires : ['CrhSys.store.realtimedata.RealtimeDatas',"CrhSys.model.realtimedata.RealtimeData",
		"CrhSys.model.realtimecurve.EngineNo","CrhSys.store.realtimecurve.EngineNos",
		"CrhSys.view.realtimecurve.RealtimeCurveChartsPanel"],
	alias : "widget.realtimecurvepanel",
	title : "实时曲线",
	
	layout : 'fit',
	items : [{
		xtype : 'chartspanel',
		itemId : 'chartsPanel',
		autoScroll : true
	}],
	
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		
		
		me.callParent();
		
	},
	
	
	buildDockedItems : function(){
		//var me = this;
		
		var comboBoxStore = Ext.create("CrhSys.store.realtimedata.CrhNos");
		var comboBoxStore2 = Ext.create("CrhSys.store.realtimecurve.EngineNos");
		var topForm = Ext.create("Ext.form.Panel",{
			itemId : "dockedForm",
			items : [{
				xtype : 'fieldcontainer',
				fieldLabel : '',
				layout : {
					type : "hbox",
					align : "middle"
				},
				items : [{
					xtype : "combobox",
					fieldLabel : "动车编号",
					name : "crhNo",
					margin: "8 10 5 2",
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
					name : "engineNo",
					margin : "8 10 5 2",
					store : comboBoxStore2,
					valueField : 'id',
					displayField : "engineNo",
					forceSelection : true,
					itemId : 'engineComboBox',
					queryMode : 'local',
					flex : 1
				},{
					xtype : 'tbfill',
					flex : 2
				}]
			},{
				xtype : "checkboxgroup",
				fieldLabel : "",
				columns : 6,
				items :[
					{xtype : 'checkbox', value : 'true', name : "property", boxLabel : "原边电流", inputValue : "ybdianliu"},
					{name : "property", boxLabel : "原边电压", inputValue : "ybdianya"},
					{name : "property", boxLabel : "控制电压", inputValue : "kzdianya"},
					{name : "property", boxLabel : "中间电压", inputValue : "zjdianya"},
					{name : "property", boxLabel : "电机电流", inputValue : "djdianliu"},
					{name : "property", boxLabel : "电机频率", inputValue : "djpinlv"},
					{name : "property", boxLabel : "动子电流", inputValue : "dzdianliu"},
					{name : "property", boxLabel : "振动信号", inputValue : "zdxinhao"},
					{name : "property", boxLabel : "牵引/制动力", inputValue : "qyzhidongli"},
					{name : "property", boxLabel : "速度", inputValue : "speed"},
					{name : "property", boxLabel : "加速度", inputValue : "jiasudu"},
					{name : "property", boxLabel : "温度", inputValue : "temperature"}				]
			}]
		});
		
		return [topForm];
	},
	
	buildItems : function(){
		
	}
});