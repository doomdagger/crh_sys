Ext.define("CrhSys.view.Viewport",{
	extend : "Ext.container.Viewport",
	requires : ["CrhSys.view.MainPanel"],
	layout : 'fit',
	initComponent : function(){
		var me = this;
		
		me.items = [{xtype : 'mainpanel', itemId : 'mainpanel'}];
		
		this.callParent();
	}
});
