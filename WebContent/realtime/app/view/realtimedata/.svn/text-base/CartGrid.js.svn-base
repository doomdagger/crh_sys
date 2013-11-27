Ext.define("CrhSys.view.realtimedata.CartGrid",{
	extend      : 'Ext.grid.Panel',
	requires : ["CrhSys.store.realtimedata.CartDatas"],
	alias       : 'widget.cartgrid',
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
                text:'参数名称',
                xtype:'templatecolumn',
                flex:1,
                dataIndex:'name',
                tpl : '<b>{name}</b>'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart1'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart2'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart3'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart4'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart5'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart6'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart7'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart8'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart9'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart10'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart11'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart12'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart13'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart14'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart15'
            },
            {
                text:'',

                flex : 1,
                dataIndex:'cart16'
            },
            {
                xtype:'rownumberer',
                flex : 1
            },
        ];
	},

	buildStore	: function(){
		return Ext.create('CrhSys.store.realtimedata.CartDatas',{
		});
	}
});
