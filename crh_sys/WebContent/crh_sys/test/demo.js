Ext.define("CrhSys.view.history.HistoryForm",{
	extend : "Ext.form.Panel",
	alias : "widget.historyform",
	
	initComponent : function() {
		var me = this;
		
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	buildItems : function(){
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
				name : "crhNo",
				flex : 1
			},{
				xtype : "combobox",
				fieldLabel : "电机编号",
				margin : "8 10 5 5",
				name : "engineNo",
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


Ext.define("CrhSys.view.history.HistoryPanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.historypanel",
	title : "历史数据",
	
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		
		me.callParent();
	},
	
	buildDockedItems : function(){
		var formPanel = {
			xtype : "historyform"
		};
		
		
		var dockedPanel = Ext.create("Ext.panel.Panel",{
			dockedItems : [{
				xtype : "toolbar",
				items : [{
					xtype : "button",
					text : "查询",
					icon : "./images/zoom.png"
				},{
					xtype : "button",
					text : "重置",
					icon : "./images/delete16.png"
				}] 
			}],
			items : [formPanel]
		});
		
		return [dockedPanel];
	},
	
	buildItems : function() {
		
	}
});



Ext.define("CrhSys.view.realtimecurve.RealtimeCurvePanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.realtimecurvepanel",
	title : "实时曲线",
	
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		
		me.callParent();
	},
	
	buildDockedItems : function(){
		var me = this;
		
		
		var topForm = Ext.create("Ext.form.Panel",{
			items : [{
				xtype : 'fieldcontainer',
				fieldLabel : '',
				layout : {
					type : "hbox",
					align : "middle"
				},
				items : [{
					xtype : "textfield",
					fieldLabel : "动车编号",
					name : "crhNo",
					margin: "8 10 5 2",
					flex : 1
				},{
					xtype : "combobox",
					fieldLabel : "电机编号",
					name : "engineNo",
					margin : "8 10 5 2",
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
					{name : "property", boxLabel : "原边电流", inputValue : "ybdianliu"},
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


Ext.define("CrhSys.view.HomePanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.homepanel",
	title : "欢迎",
	html : "<h1>Home Page</h1>"
});

Ext.define("CrhSys.view.realtimedata.RealtimeDataPanel",{
	extend : "Ext.panel.Panel",
	alias : "widget.realtimedatapanel",
	title : "实时数据",
	initComponent : function(){
		var me = this;
		
		me.dockedItems = me.buildDockedItems();
		
		
		me.html = "<h1>Table will be here</h1>";
		
		me.callParent();
	},
	
	buildDockedItems : function(){
		//var me = this;
		
		var dockedForm = Ext.create("Ext.form.Panel",{
			layout : {
				type : "hbox",
				align : "middle"
			},
			
			items : [{
				xtype : 'textfield',
				fieldLabel : "动车编号",
				labelPad : 0,
				name : "crhNo",
				margins : '10 8 10 0',
				flex : 1
			},{
				xtype : 'textfield',
				fieldLabel : "电机总数",
				labelPad : 0,
				name : "engineNum",
				margins : '10 8 10 0',
				flex : 1
			},{
				xtype : 'tbfill',
				flex : 2
			}]
		});
		
		return [dockedForm];
		
	}
});
Ext.define("CrhSys.view.MainPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.mainpanel',
	
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
			xtype : 'homepanel'
		},{
			xtype : 'realtimedatapanel'
		},{
			xtype : 'realtimecurvepanel'
		},{
			xtype : 'historypanel'
		}];
	},
	
	buildDockedItems : function(){
		var me = this;
		
		var homeButton = Ext.create("Ext.button.Button",{
			text : "主页",
			icon : "./images/house.png"
		});
		
		homeButton.on('click',function(){
			me.getLayout().setActiveItem(0);
		});
		
		var realtimedataItem = Ext.create("Ext.menu.Item",{
			text : "实时数据"
		});
		
		realtimedataItem.on('click',function(){
			me.getLayout().setActiveItem(1);
		});
		
		var realtimecurveItem = Ext.create("Ext.menu.Item",{
			text : "实时曲线"
		});
		
		realtimecurveItem.on('click',function(){
			me.getLayout().setActiveItem(2);
		});
		
		var historydataItem = Ext.create("Ext.menu.Item",{
			text : "历史数据"
		});
		
		historydataItem.on('click',function(){
			me.getLayout().setActiveItem(3);
		});
		
		return [{
			xtype : "toolbar",
			docked : 'top',
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

Ext.define("CrhSys.view.Viewport",{
extend : "Ext.container.Viewport",
	layout : 'fit',
	initComponent : function(){
		var me = this;
		
		me.items = [{xtype : 'mainpanel'}];
		
		this.callParent();
	}
});



Ext.onReady(function(){
	Ext.create("CrhSys.view.Viewport",{
		renderTo : Ext.getBody()
	});
});