Ext.define("CrhSys.model.realtimedata.RealtimeData",{
	extend : 'Ext.data.Model',
	idProperty : "id",
	fields:[
		{name:'id', type:'int'},
		{name:'engineNo', type:'string'},
		{name:'crhNo', type:'string'},
		{name:'ybdianya', type:'float'},
		{name:'ybdianliu', type:'float'},
		{name:'kzdianya', type:'float'},
		{name:'zjdianya', type:'float'},
		{name:'djdianliu', type:'float'},
		{name:'djpinlv', type:'float'},
		{name:'qyzhidongli', type:'float'},
		{name:'speed', type:'float'},
		{name:'jiasudu', type:'float'},
		{name:'temperature', type:'float'},
		{name:'zdxinhao', type:'float'},
		{name:'dzdianliu', type:'float'},
		{name:'dateTime', type:'date',dateFormat:'Y-m-d H:i:s'}
	]
});