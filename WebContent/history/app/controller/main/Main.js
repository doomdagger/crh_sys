Ext.define("CrhSys.controller.main.Main",{
	extend : "Ext.app.Controller",
	requires : ['CrhSys.store.realtimedata.RealtimeDatas',
				"CrhSys.model.realtimecurve.EngineNo",
				'CrhSys.model.realtimedata.CartData',
				'CrhSys.store.realtimedata.CartDatas'],
	
	
	init : function(){
		var me = this;
		
		//var mainlayout = Ext.ComponentQuery.query('#mainpanel')[0].getLayout();
		
		me.control({
			
			'#mainpanel #historypanel #historyform #crhComboBox' : {
				select : me.changeHistoryDataCombo
			},
			'#mainpanel #historypanel #dockedtoolbar button[action=search]' : {
				click : me.searchHistoryData
			},
			'#mainpanel #historypanel #dockedtoolbar button[action=reset]' : {
				click : me.resetForm
			}
		});
		
	},
	
	
	
	searchHistoryData : function(){
		var form = Ext.ComponentQuery.query('#mainpanel #historypanel #historyform')[0];
		var grid = Ext.ComponentQuery.query('#mainpanel #historypanel #historyGrid')[0];
		grid.getStore().removeAll();
		Ext.Msg.wait("Loading...");
		Ext.Ajax.request({
			url : 'historyQuery',
			params : form.getForm().getValues(),
			success: function(response,options){
				var data = Ext.decode(response.responseText);
				grid.getStore().loadData(data.data);
				Ext.Msg.hide();
			}
		});
	},
	
	resetForm : function(){
		var form = Ext.ComponentQuery.query('#mainpanel #historypanel #historyform')[0];
		form.getForm().reset();
	},
	
	changeHistoryDataCombo : function(combo, records){
		var otherCombo = Ext.ComponentQuery.query('#mainpanel #historypanel #historyform #engineComboBox')[0];
		otherCombo.clearValue();
		var store = otherCombo.getStore();
		store.removeAll();
		Ext.Ajax.request({
			url : "engineList",
			params : {
				"crhId" : combo.getValue()
			},
			timeout : 20000,
			success : function(response){
				var text = response.responseText;
				var data = Ext.decode(text);
				//console.log('about to load data combo box data');
				store.loadData(data.data);
			},
			failure : function(){
				Ext.Msg.alert("Failure","Unable to load data from remote server!");
			}
		});
	}
});
