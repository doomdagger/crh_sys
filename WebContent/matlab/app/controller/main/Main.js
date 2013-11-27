Ext.define("CrhSys.controller.main.Main",{
	extend : "Ext.app.Controller",
	requires : [],
	
	init : function(){
		var me = this;
		
		
		me.control({
			'#mainpanel #algorithmpanel #alformpanel #filecombobox' : {
				select : me.changeLabelButton
			},
			'#mainpanel #algorithmpanel #alformpanel #computebutton' : {
				click : me.computeLVM
			}/*
			'#mainpanel #algorithmpanel #alformpanel #labelbutton' : {
				click : me.changePic
			}
			*/
		});		
	},
	
	changeLabelButton : function(combo, records){
		var crhField = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #resultpanel #resultformpanel #crhNo')[0];
		var engineField = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #resultpanel #resultformpanel #engineNo')[0];
		var engineStatus = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #resultpanel #resultformpanel #engineStatus')[0];
		
		var label = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #alformpanel #labelbutton')[0];
		var value = combo.getRawValue();
		var tempOne = value.substring(0,4);
		var crhNo = tempOne+"-"+value.substring(4,8);
		tempOne = value.substring(9,11);
		if(tempOne.charAt(0)=='0'){
			tempOne = tempOne.charAt(1);
		}
		var engineNo = value.substring(8,9)+tempOne;
		var year = value.substring(11,15);
		var month = value.substring(15,17);
		var date = value.substring(17,19);
		label.setText("您选择了数据文件"+value+"，该数据文件记录了动车"+crhNo+"所属的电机"+engineNo+"在"+year+"年"+month+"月"+date+"日的振动信号数据。");
	
		crhField.setValue(crhNo);
		engineField.setValue(engineNo);
		engineStatus.setValue('');
	},
	
	computeLVM : function() {
		var me = this;
		
		var crhField = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #resultpanel #resultformpanel #crhNo')[0];
		var engineField = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #resultpanel #resultformpanel #engineNo')[0];
		//var form = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #alformpanel')[0];
		var engineStatus = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #resultpanel #resultformpanel #engineStatus')[0];
		var filecombobox = Ext.ComponentQuery.query('#mainpanel #algorithmpanel #alformpanel #filecombobox')[0];
		
		Ext.Msg.wait("Loading...");
		Ext.Ajax.request({
			url : 'LVMCompute',
			params : {
				"fileName" : filecombobox.getValue(),
				"crhNo" : crhField.getValue(),
				"engineNo" : engineField.getValue()
			},
			success: function(response,options){
				var data = Ext.decode(response.responseText);
				Ext.Msg.hide();
				var status = data.data[0].result;
				var one = status.charAt(0);
				var two = status.charAt(1);
				var three = status.charAt(2);
				
				if(one=='1'){
					engineStatus.setFieldStyle('color: green');
					engineStatus.setValue("这个电机运行良好。");
				}else if(two=='0'&&three=='1'){
					engineStatus.setFieldStyle('color: red');
					engineStatus.setValue("电机出现B故障。");
				}else if(two=='0'&&three=='5'){
					engineStatus.setFieldStyle('color: yellow');
					engineStatus.setValue("电机已有损耗，出现B故障趋势");
				}else if(two=='1'&&three=='0'){
					engineStatus.setFieldStyle('color: red');
					engineStatus.setValue("电机出现A故障。");
				}else if(two=='1'&&three=='1'){
					engineStatus.setFieldStyle('color: red');
					engineStatus.setValue("电机出现A,B故障。");
				}else if(two=='1'&&three=='5'){
					engineStatus.setFieldStyle('color: red');
					engineStatus.setValue("电机出现A故障。且电机已有出现B故障趋势。");
				}else if(two=='5'&&three=='0'){
					engineStatus.setFieldStyle('color: yellow');
					engineStatus.setValue("电机已有损耗，出现A故障趋势。");
				}else if(two=='5'&&three=='1'){
					engineStatus.setFieldStyle('color: red');
					engineStatus.setValue("电机出现B故障。且电机已有出现A故障趋势。");
				}else if(two=='5'&&three=='5'){
					engineStatus.setFieldStyle('color: yellow');
					engineStatus.setValue("电机已有损耗，出现A,B故障趋势。");
				}
				
				var uniqueFlag = data.data[1].key;
				console.log(uniqueFlag);
				me.changePic(uniqueFlag);
			}
		});
	},
	
	changePic : function(uniqueFlag){
		var picsarray = [];
		var picsdomarray = [];
		var index = 1;
		
		for(;index <= 14; index++){
			picsarray[index-1] = Ext.ComponentQuery.query("#mainpanel #picturepanel #pic"+index)[0];
			picsdomarray[index-1] = picsarray[index-1].getEl().down('#pic');
			picsdomarray[index-1].dom.src="../res/tmp/"+uniqueFlag+"/"+index+".jpg";
		}
		
		picsdomarray[0].fadeIn(1000,function(){
			picsdomarray[1].fadeIn(1000,function(){
				picsdomarray[2].fadeIn(1000,function(){
					picsdomarray[3].fadeIn(1000,function(){
						picsdomarray[4].fadeIn(1000,function(){
							picsdomarray[5].fadeIn(1000,function(){
								picsdomarray[6].fadeIn(1000,function(){
									picsdomarray[7].fadeIn(1000);
								});
							});
						});
					});
				});
			});
		});
	}
});
