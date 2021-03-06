Ext.define("CrhSys.controller.main.Main",{
	extend : "Ext.app.Controller",
	requires : ['CrhSys.store.realtimedata.RealtimeDatas',
				"CrhSys.model.realtimecurve.EngineNo",
				'CrhSys.model.realtimedata.CartData',
				'CrhSys.store.realtimedata.CartDatas'],
	
	mainDataStore : Ext.create('CrhSys.store.realtimedata.RealtimeDatas'),

	valueArray : [0,0,0,0,0,0,0,0,0,0,0,0],
	cartsArray : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
	
	crhId : -1,
	updateInterval : 24,
	
	init : function(){
		var me = this;
		
		//var mainlayout = Ext.ComponentQuery.query('#mainpanel')[0].getLayout();
		
		me.control({
			'#mainpanel #maintoolbar #realtimedataItem' : {
				click : me.showRealtimeData
			},
			'#mainpanel #maintoolbar #realtimecurveItem' : {
				click : me.showRealtimeCurve
			},
			'#mainpanel #maintoolbar #refreshButton' : {
				click : me.foreSendRequestData
			},
			'#mainpanel #realtimedatapanel #dockedForm #crhComboBox' : {
				select : me.dataComboSelect
			},
			'#mainpanel #realtimecurvepanel #dockedForm #crhComboBox' : {
				select : me.curveComboSelect
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=ybdianliu]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=ybdianya]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=kzdianya]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=zjdianya]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=dzdianliu]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=zdxinhao]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=djdianliu]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=djpinlv]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=speed]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=jiasudu]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=temperature]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm checkbox[inputValue=qyzhidongli]' : {
				change : me.controlChartPanel
			},
			'#mainpanel #realtimecurvepanel #dockedForm #engineComboBox' : {
				select : me.updateChartsStoreByCombo
			},
			'#mainpanel #realtimecurvepanel' : {
				afterrender : me.animateCharts
			}
		});
		
		me.mainDataStore.on('datachanged',function(){
			me.changeRealtimeDataText();
			me.changeRealtimeCurveCombo();
			me.updateChartsStore(0);
			me.updateCartsStore();
		});
		
		setInterval(function(){
			if(me.crhId!=-1){
				var refreshButton = Ext.ComponentQuery.query("#mainpanel #maintoolbar #refreshButton")[0];
				refreshButton.setText(me.updateInterval+"");
				if(me.updateInterval==0){
					me.sendDataRequest(me.crhId);
					//console.log("crhId = "+ me.crhId +", count time" + me.updateInterval);					
					me.updateInterval=24;
				}else{
					me.updateInterval--;
					//console.log("--" + "count time" + me.updateInterval);					
				}
			}else{
				//console.log("crhId = -1, next time");
			}
		},1000);
		
	},
	
	foreSendRequestData : function(){
		//var me = this;
		
	},
	
	updateCartsStore : function(){
		var me = this;
		var temp1;
		var temp2;
		var i = 0;
		var models = [];
		for(i = 0; i < 13; i++){
			models[i] = Ext.create("CrhSys.model.realtimedata.CartData");
		}
		models[0].set('name','原边电流');
		models[1].set('name','原边电压');
		models[2].set('name','控制电压');
		models[3].set('name','中间电压');
		models[4].set('name','电机电流');
		models[5].set('name','电机频率');
		models[6].set('name','动子电流');
		models[7].set('name','振动信号');
		models[8].set('name','牵引/制动力');
		models[9].set('name','速度');
		models[10].set('name','加速度');
		models[11].set('name','温度');
		models[12].set('name',"电机状态");
		
		var flag = -1;
		
		var icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon1")[0];
		//icon.setText('<div style="background:url(./images/head.gif); width:110px;height:96px;line-height:110px;">Test TEXT</div>');
		icon.getEl().dom.src='./images/head3.gif';
		//icon.setIcon('./images/head.gif');
		
		i = 1;
		Ext.each(me.cartsArray, function(item,index){
			if(item==1){
				temp1 = me.mainDataStore.getAt(i-1);
				temp2 = me.mainDataStore.getAt(i);
				models[0].set('cart'+(index+1),temp1.get('ybdianliu')+'/'+temp2.get('ybdianliu'));
				models[1].set('cart'+(index+1),temp1.get('ybdianya')+'/'+temp2.get('ybdianya'));
				models[2].set('cart'+(index+1),temp1.get('kzdianya')+'/'+temp2.get('kzdianya'));
				models[3].set('cart'+(index+1),temp1.get('zjdianya')+'/'+temp2.get('zjdianya'));
				models[4].set('cart'+(index+1),temp1.get('djdianliu')+'/'+temp2.get('djdianliu'));
				models[5].set('cart'+(index+1),temp1.get('djpinlv')+'/'+temp2.get('djpinlv'));
				models[6].set('cart'+(index+1),temp1.get('dzdianliu')+'/'+temp2.get('dzdianliu'));
				models[7].set('cart'+(index+1),temp1.get('zdxinhao')+'/'+temp2.get('zdxinhao'));
				models[8].set('cart'+(index+1),temp1.get('qyzhidongli')+'/'+temp2.get('qyzhidongli'));
				models[9].set('cart'+(index+1),temp1.get('speed')+'/'+temp2.get('speed'));
				models[10].set('cart'+(index+1),temp1.get('jiasudu')+'/'+temp2.get('jiasudu'));
				models[11].set('cart'+(index+1),temp1.get('temperature')+'/'+temp2.get('temperature'));
				var statusOne;
				var statusTwo;
				if(temp1.get('status')==0){
					statusOne = '<font color="green">正常</font>';
				}else if(temp1.get('status')==1){
					statusOne = '<font color="yellow">趋势</font>';
				}else{
					statusOne = '<font color="red">损坏</font>';
				}
				if(temp2.get('status')==0){
					statusTwo = '<font color="green">正常</font>';
				}else if(temp2.get('status')==1){
					statusTwo = '<font color="yellow">趋势</font>';
				}else{
					statusTwo = '<font color="red">损坏</font>';
				}
				models[12].set('cart'+(index+1),statusOne+'/'+statusTwo);
				icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon"+(index+2))[0];
				//icon.setText('<div style="background:url(./images/m2.gif); width:110px;height:96px;line-height:110px;repeat;">Test TEXT</div>');
				icon.getEl().dom.src='./images/m3.gif';
				//icon.setIcon('./images/m.gif');
				i+=2;
			}else if(item==0||item==-1){
				models[0].set('cart'+(index+1),'');
				models[1].set('cart'+(index+1),'');
				models[2].set('cart'+(index+1),'');
				models[3].set('cart'+(index+1),'');
				models[4].set('cart'+(index+1),'');
				models[5].set('cart'+(index+1),'');
				models[6].set('cart'+(index+1),'');
				models[7].set('cart'+(index+1),'');
				models[8].set('cart'+(index+1),'');
				models[9].set('cart'+(index+1),'');
				models[10].set('cart'+(index+1),'');
				models[11].set('cart'+(index+1),'');
				models[12].set('cart'+(index+1),'');
				if(item==0){
					icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon"+(index+2))[0];
					//icon.setText('<div style="background:url(./images/t2.gif);width:110px;height:96px;line-height:110px;">Test TEXT</div>');
					icon.getEl().dom.src='./images/t3.gif';
					//icon.setIcon('./images/t.gif');
				}else{
					if(flag==-1){
						flag = index;
						icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon"+(index+2))[0];
						//icon.setText('<div style="background:url(./images/tail2.gif); margin:0; padding:0; width:110px;height:96px;line-height:110px;">Test TEXT</div>');
						icon.getEl().dom.src='./images/tail3.gif';
						//icon.setIcon('./images/tail.gif');
					}else{
						icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon"+(index+2))[0];
						//icon.setText('<div style="background:url(./images/t.gif); width:96px;height:96px;line-height:96px;">Test TEXT</div>');
						//icon.setIcon('');
						icon.getEl().dom.src='';
					}
				}
			}
		});
		
		if(me.cartsArray[15]!=-1){
			icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon18")[0];
			//icon.setText('<div style="background:url(./images/tail2.gif); margin:0; padding:0;width:110px;height:96px;line-height:110px;">Test TEXT</div>');
			icon.getEl().dom.src='./images/tail3.gif';
			//icon.setIcon('./images/tail.gif');
		}else{
			icon = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #cartIcon18")[0];
			//icon.setIcon('');
			icon.getEl().dom.src='';
		}
		
		var grid = Ext.ComponentQuery.query('#mainpanel #realtimedatapanel #cartGrid')[0];
		var myStore = grid.getStore();
		myStore.removeAll();
		for(i = 0; i < 13; i++){
			myStore.add(models[i]);
		}
		
		
	},
	
	updateChartsStore : function(index){
		this.valueArray[0] = this.mainDataStore.getAt(index).get('ybdianya');
     	this.valueArray[1] = this.mainDataStore.getAt(index).get('ybdianliu');
     	this.valueArray[2] = this.mainDataStore.getAt(index).get('kzdianya');
     	this.valueArray[3] = this.mainDataStore.getAt(index).get('zjdianya');
     	this.valueArray[4] = this.mainDataStore.getAt(index).get('djdianliu');
     	this.valueArray[5] = this.mainDataStore.getAt(index).get('djpinlv');
     	this.valueArray[6] = this.mainDataStore.getAt(index).get('qyzhidongli');
     	this.valueArray[7] = this.mainDataStore.getAt(index).get('speed');
     	this.valueArray[8] = this.mainDataStore.getAt(index).get('jiasudu');
     	this.valueArray[9] = this.mainDataStore.getAt(index).get('temperature');
     	this.valueArray[10] = this.mainDataStore.getAt(index).get('zdxinhao');
     	this.valueArray[11] = this.mainDataStore.getAt(index).get('dzdianliu');	
	},
	
	updateChartsStoreByCombo : function(combo, records){
		var value = combo.getValue();
		this.valueArray[0] = this.mainDataStore.getAt(value).get('ybdianya');
     	this.valueArray[1] = this.mainDataStore.getAt(value).get('ybdianliu');
     	this.valueArray[2] = this.mainDataStore.getAt(value).get('kzdianya');
     	this.valueArray[3] = this.mainDataStore.getAt(value).get('zjdianya');
     	this.valueArray[4] = this.mainDataStore.getAt(value).get('djdianliu');
     	this.valueArray[5] = this.mainDataStore.getAt(value).get('djpinlv');
     	this.valueArray[6] = this.mainDataStore.getAt(value).get('qyzhidongli');
     	this.valueArray[7] = this.mainDataStore.getAt(value).get('speed');
     	this.valueArray[8] = this.mainDataStore.getAt(value).get('jiasudu');
     	this.valueArray[9] = this.mainDataStore.getAt(value).get('temperature');
     	this.valueArray[10] = this.mainDataStore.getAt(value).get('zdxinhao');
     	this.valueArray[11] = this.mainDataStore.getAt(value).get('dzdianliu');	
	},
	
	animateCharts : function(){
		var me = this;
		
		var checkboxes = Ext.ComponentQuery.query('#mainpanel #realtimecurvepanel #dockedForm checkbox');
		Ext.each(checkboxes,function(item,index){
			item.setValue(true);
		});
		
		var timeAxisArray = [], chartArray = [];
		chartArray[0] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #ybdianyaChart")[0];
		chartArray[1] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #ybdianliuChart")[0];
		chartArray[2] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #kzdianyaChart")[0];
		chartArray[3] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #zjdianyaChart")[0];
		chartArray[4] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #djdianliuChart")[0];
		chartArray[5] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #djpinlvChart")[0];
		chartArray[6] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #qyzhidongliChart")[0];
		chartArray[7] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #speedChart")[0];
		chartArray[8] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #jiasuduChart")[0];
		chartArray[9] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #temperatureChart")[0];
		chartArray[10] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #zdxinhaoChart")[0];
		chartArray[11] = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #dzdianliuChart")[0];

    	timeAxisArray[0] = chartArray[0].axes.get(1);
    	timeAxisArray[1] = chartArray[1].axes.get(1);
    	timeAxisArray[2] = chartArray[2].axes.get(1);
    	timeAxisArray[3] = chartArray[3].axes.get(1);
    	timeAxisArray[4] = chartArray[4].axes.get(1);
    	timeAxisArray[5] = chartArray[5].axes.get(1);
   		timeAxisArray[6] = chartArray[6].axes.get(1);
    	timeAxisArray[7] = chartArray[7].axes.get(1);
    	timeAxisArray[8] = chartArray[8].axes.get(1);
    	timeAxisArray[9] = chartArray[9].axes.get(1);
    	timeAxisArray[10] = chartArray[10].axes.get(1);
    	timeAxisArray[11] = chartArray[11].axes.get(1);
    	
    	var generateYbdianyaData = (function() {
        	var data = [], i = 0,
            	date = new Date(2011, 1, 1);
        	return function() {
            	if(data.length>10){
            		data = data.slice(data.length-10, data.length);
            	}
            	data.push({
                	date: Ext.Date.add(date, Ext.Date.DAY, i++),
                	ybdianya: me.valueArray[0]
            	});
            	return data;
        	};
    	})();


		var generateYbdianliuData = (function() {
        	var data = [], i = 0,
            	date = new Date(2011, 1, 1);
        	return function() {
            	if(data.length>10){
            		data = data.slice(data.length-10,data.length);
            	}
            	data.push({
             		date : Ext.Date.add(date, Ext.Date.DAY, i++),
               	 	ybdianliu: me.valueArray[1]
            	});
            	return data;
       		};
    	})();
    
    var generateKzdianyaData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                kzdianya: me.valueArray[2]
            });
            return data;
        };
    })();
    
    var generateZjdianyaData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                zjdianya: me.valueArray[3]
            });
            return data;
        };
    })();
    
    var generateDjdianliuData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                djdianliu: me.valueArray[4]
            });
            return data;
        };
    })();
    
    var generateDjpinlvData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                djpinlv: me.valueArray[5]
            });
            return data;
        };
    })();
    
    var generateQyzhidongliData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                qyzhidongli: me.valueArray[6]
            });
            return data;
        };
    })();
    
    var generateSpeedData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                speed: me.valueArray[7]
            });
            return data;
        };
    })();
    
    var generateJiasuduData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                jiasudu: me.valueArray[8]
            });
            return data;
        };
    })();
    
    var generateTemperatureData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                temperature: me.valueArray[9]
            });
            return data;
        };
    })();
    
    var generateZdxinhaoData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                zdxinhao: me.valueArray[10]
            });
            return data;
        };
    })();
    
    var generateDzdianliuData = (function() {
        var data = [], i = 0,
            date = new Date(2011, 1, 1);
        return function() {
            if(data.length>10){
            	data = data.slice(data.length-10,data.length);
            }
            data.push({
             date : Ext.Date.add(date, Ext.Date.DAY, i++),
                dzdianliu: me.valueArray[11]
            });
            return data;
        };
    })();
    
    
    	setInterval(function() {
        var gs = generateYbdianyaData();
        var gs2 = generateYbdianliuData();
        var gs3 = generateKzdianyaData();
        var gs4 = generateZjdianyaData();
        var gs5 = generateDjdianliuData();
        var gs6 = generateDjpinlvData();
        var gs7 = generateQyzhidongliData();
        var gs8 = generateSpeedData();
        var gs9 = generateJiasuduData();
        var gs10 = generateTemperatureData();
		var gs11 = generateZdxinhaoData();
		var gs12 = generateDzdianliuData();

        var toDate = timeAxisArray[0].toDate,
            lastDate = gs[gs.length - 1].date,
            markerIndex = chartArray[0].markerIndex || 0;
        if (toDate < lastDate) {
            markerIndex = 1;
            timeAxisArray[0].toDate = lastDate;
            timeAxisArray[0].fromDate = Ext.Date.add(Ext.Date.clone(timeAxisArray[0].fromDate), Ext.Date.DAY, 1);
            chartArray[0].markerIndex = markerIndex;
            timeAxisArray[1].toDate = timeAxisArray[0].toDate;
            timeAxisArray[1].fromDate = timeAxisArray[0].fromDate;
            chartArray[1].markerIndex = markerIndex;
            
            timeAxisArray[2].toDate = timeAxisArray[0].toDate;
            timeAxisArray[2].fromDate = timeAxisArray[0].fromDate;
            chartArray[2].markerIndex = markerIndex;
            
            timeAxisArray[3].toDate = timeAxisArray[0].toDate;
            timeAxisArray[3].fromDate = timeAxisArray[0].fromDate;
            chartArray[3].markerIndex = markerIndex;
            
            timeAxisArray[4].toDate = timeAxisArray[0].toDate;
            timeAxisArray[4].fromDate = timeAxisArray[0].fromDate;
            chartArray[4].markerIndex = markerIndex;
            
            timeAxisArray[5].toDate = timeAxisArray[0].toDate;
            timeAxisArray[5].fromDate = timeAxisArray[0].fromDate;
            chartArray[5].markerIndex = markerIndex;
            
            timeAxisArray[6].toDate = timeAxisArray[0].toDate;
            timeAxisArray[6].fromDate = timeAxisArray[0].fromDate;
            chartArray[6].markerIndex = markerIndex;
            
            timeAxisArray[7].toDate = timeAxisArray[0].toDate;
            timeAxisArray[7].fromDate = timeAxisArray[0].fromDate;
            chartArray[7].markerIndex = markerIndex;
            
            timeAxisArray[8].toDate = timeAxisArray[0].toDate;
            timeAxisArray[8].fromDate = timeAxisArray[0].fromDate;
            chartArray[8].markerIndex = markerIndex;
            
            timeAxisArray[9].toDate = timeAxisArray[0].toDate;
            timeAxisArray[9].fromDate = timeAxisArray[0].fromDate;
            chartArray[9].markerIndex = markerIndex;
            
            timeAxisArray[10].toDate = timeAxisArray[0].toDate;
            timeAxisArray[10].fromDate = timeAxisArray[0].fromDate;
            chartArray[10].markerIndex = markerIndex;
            
            timeAxisArray[11].toDate = timeAxisArray[0].toDate;
            timeAxisArray[11].fromDate = timeAxisArray[0].fromDate;
            chartArray[11].markerIndex = markerIndex;
        }
        
        chartArray[0].store.loadData(gs);
        chartArray[1].store.loadData(gs2);
        chartArray[2].store.loadData(gs3);
        chartArray[3].store.loadData(gs4);
        chartArray[4].store.loadData(gs5);
        chartArray[5].store.loadData(gs6);
        chartArray[6].store.loadData(gs7);
        chartArray[7].store.loadData(gs8);
        chartArray[8].store.loadData(gs9);
        chartArray[9].store.loadData(gs10);
        chartArray[10].store.loadData(gs11);
        chartArray[11].store.loadData(gs12);
        
        //console.log(chartArray[0].store.count());
        
    }, 2000);
    
    
	},
	
	controlChartPanel : function(checkbox, newValue){
		//var historyPanel = Ext.ComponentQuery.query('#mainpanel #realtimecurvepanel #chartsPanel')[0];
		var chartPanel = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #"+checkbox.inputValue+"Panel")[0];
		if(newValue){
			chartPanel.show();
		}else{
			chartPanel.hide();
		}
	},
	
	changeRealtimeDataText : function(){
		var text = Ext.ComponentQuery.query("#mainpanel #realtimedatapanel #dockedForm #engineTextField")[0];
		text.setValue(this.mainDataStore.count());
	},
	
	changeRealtimeCurveCombo : function(){
		var combo = Ext.ComponentQuery.query("#mainpanel #realtimecurvepanel #dockedForm #engineComboBox")[0];
		combo.clearValue();
		var store = combo.getStore();
		store.removeAll();
		this.mainDataStore.each(function(item,index){
			var temp = Ext.create("CrhSys.model.realtimecurve.EngineNo",{
				id : index,
				engineNo : item.get("engineNo")
			});
			store.add(temp);
		});
		combo.select(0);
	},
	showRealtimeData : function(){
		var mainlayout = Ext.ComponentQuery.query('#mainpanel')[0].getLayout();
		mainlayout.setActiveItem(1);
	},
	showRealtimeCurve : function(){
		var mainlayout = Ext.ComponentQuery.query('#mainpanel')[0].getLayout();
		mainlayout.setActiveItem(0);
	},
	dataComboSelect : function(combo, records){
		var me = this;
		var otherCombo = Ext.ComponentQuery.query('#mainpanel #realtimecurvepanel #dockedForm #crhComboBox')[0];
		otherCombo.setValue(combo.getValue());
		me.crhId = {
			"crhId" : combo.getValue()
		};
		this.sendDataRequest(me.crhId);
	},
	curveComboSelect : function(combo, records){
		var me = this;
		var otherCombo = Ext.ComponentQuery.query('#mainpanel #realtimedatapanel #dockedForm #crhComboBox')[0];
		otherCombo.setValue(combo.getValue());
		me.crhId = {
			"crhId" : combo.getValue()
		};
		this.sendDataRequest(me.crhId);
	},
	
	sendDataRequest : function(crhId){
		var me = this;
		Ext.Msg.wait('Loading data from remote server...wait');
		Ext.Ajax.request({
			url : "realtimedata",
			params : crhId,
			timeout : 20000,
			success : function(response){
				Ext.Msg.hide();
				var text = response.responseText;
				var data = Ext.decode(text);
				me.cartsArray = data.modelType;
				me.mainDataStore.loadData(data.data);
				me.updateInterval = 24;
				//console.log(me.mainDataStore.count() + ' is loaded');
			},
			failure : function(){
				Ext.Msg.alert("Failure","Unable to load data from remote server!");
			}
		});
	}
});
