Ext.define("CrhSys.view.historydata.HistoryGrid",{
	extend      : 'Ext.grid.Panel',
	requires : ['CrhSys.store.realtimedata.RealtimeDatas'],
	alias       : 'widget.historygrid',
	selType:'cellmodel',
	
	initComponent   : function(){
		var me = this;

		me.store = me.buildStore();
		me.columns = me.buildColumns();

		me.callParent();
	},

	buildColumns	: function(){
		return [
            {
                xtype:'rownumberer'
            },
            {
                text:'电机编号',
                xtype:'templatecolumn',
                flex:1,
                dataIndex:'engineNo',
                tpl : '<b>{engineNo}</b>'
            },
            {
                text:'动车编号',
                xtype:'templatecolumn',
                flex : 2,
                dataIndex:'crhNo',
                format : 'Y-m-d H:m:s',
				tpl:'<b>{engineNo}</b> </br>{dateTime}'	
            },
            {
                text:'原边电压',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'ybdianya'
            },
            {
                text:'原边电流',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'ybdianliu'
            },
            {
                text:'中间电压',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'zjdianya'
            },
            {
                text:'控制电压',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'kzdianya'
            },
            {
                text:'电机电流',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'djdianliu'
            },
            {
                text:'电机频率',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'djpinlv'
            },
            {
                text:'牵引/制动力',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'qyzhidongli'
            },
            {
                text:'动子电流',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'dzdianliu'
            },
            {
                text:'振动信号',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'zdxinhao'
            },
            {
                text:'速度',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'speed'
            },
            {
                text:'加速度',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'jiasudu'
            },
            {
                text:'温度',
                xtype:'numbercolumn',
                flex : 1,
                dataIndex:'temperature'
            }
        ];
	},

	buildStore	: function(){
		return Ext.create('CrhSys.store.realtimedata.RealtimeDatas',{
			pageSize:10
		});
	}
});