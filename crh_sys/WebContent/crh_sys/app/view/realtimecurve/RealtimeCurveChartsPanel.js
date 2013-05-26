Ext.define("CrhSys.view.realtimecurve.RealtimeCurveChartsPanel",{
	extend : "Ext.panel.Panel",
	alias : 'widget.chartspanel',
	
	layout : {
		type : 'table',
		columns : 4
	},
	
	defaults : {
		layout : 'fit',
		height : 260,
		width : 330,
		xtype : 'panel'
	},
	
	initComponent : function(){
		var me = this;
		
		me.items = me.buildItems();
		
		me.callParent();
	},
	
	buildItems : function(){
		var ybdianyaStore = Ext.create('Ext.data.Store', {
        	fields: [{name : 'date', type : 'date'}, 'ybdianya'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		ybdianya : 100
        	}]
    	});
    	var ybdianliuStore = Ext.create('Ext.data.Store', {
        	fields: ['date','ybdianliu'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		ybdianliu : 100
        	}]
    	});
    	var kzdianyaStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'kzdianya'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		kzdianya : 100
        	}]
    	});
    	var zjdianyaStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'zjdianya'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		zjdianya : 100
        	}]
    	});
    	var djdianliuStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'djdianliu'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		djdianliu : 100
        	}]
    	});
    	var djpinlvStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'djpinlv'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		djpinlv : 100
        	}]
   		});
    	var qyzhidongliStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'qyzhidongli'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		qyzhidongli : 100
        	}]
    	});
    	var speedStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'speed'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		speed : 100
        	}]
    	});
    	var jiasuduStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'jiasudu'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		jiasudu : 100
        	}]
    	});
    	var temperatureStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'temperature'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		temperature : 100
        	}]
    	});
   		var zdxinhaoStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'zdxinhao'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		zdxinhao : 100
        	}]
    	});
    	var dzdianliuStore = Ext.create('Ext.data.Store', {
        	fields: ['date', 'dzdianliu'],
        	data: [{
        		date : new Date(2011, 1, 1),
        		dzdianliu : 100
        	}]
    	});
    
    
    	var ybdianyaChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'ybdianyaChart',
			store : ybdianyaStore,
    		shadow: false,
			animate: true,
   			
			
			
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['ybdianya'],
					title : '原边电压',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'ybdianya',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					type : 'cross',
     					radius: 5,
     					size: 5,
     					'fill': '#f21'
     				}
  				}
  			]
 		});
 		
 		var ybdianliuChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'ybdianliuChart',
			store : ybdianliuStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['ybdianliu'],
					title : '原边电流',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'ybdianliu',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f77'
     				}
  				}
  			]
 		});
 		
 		var zjdianyaChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'zjdianyaChart',
			store : zjdianyaStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['zjdianya'],
					title : '中间电压',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'zjdianya',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f23'
     				}
  				}
  			]
 		});
 		
 		var kzdianyaChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'kzdianyaChart',
			store : kzdianyaStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['kzdianya'],
					title : '控制电压',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'kzdianya',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var djdianliuChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'djdianliuChart',
			store : djdianliuStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['djdianliu'],
					title : '电机电流',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'djdianliu',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var djpinlvChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'djpinlvChart',
			store : djpinlvStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['djpinlv'],
					title : '电机频率',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'djpinlv',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var dzdianliuChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'dzdianliuChart',
			store : dzdianliuStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['dzdianliu'],
					title : '动子电流',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'dzdianliu',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var zdxinhaoChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'zdxinhaoChart',
			store : zdxinhaoStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['zdxinhao'],
					title : '振动信号',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'zdxinhao',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var qyzhidongliChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'qyzhidongliChart',
			store : qyzhidongliStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['qyzhidongli'],
					title : '牵引/制动力',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'qyzhidongli',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var speedChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'speedChart',
			store : speedStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['speed'],
					title : '速度',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'speed',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var jiasuduChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'jiasuduChart',
			store : jiasuduStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['jiasudu'],
					title : '加速度',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'jiasudu',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
 		
 		var temperatureChart = Ext.create('Ext.chart.Chart',{
    		style: 'background:#fff',
  			itemId: 'temperatureChart',
			store : temperatureStore,
    		shadow: false,
			animate: true,
   
  			axes : [
  				{
  					type: 'Numeric',
     				minimum: 0,
    				position: 'left',
     				fields : ['temperature'],
					title : '温度',
     				grid: {
     					odd: {
     						fill: '#dedede',
    						stroke: '#ddd',
     						'stroke-width': 0.5
     					}
     				}	
    			},
    			{
   					type: 'Time',
    				position: 'bottom',
    				fields: 'date',
     				title: false,
     				dateFormat: 's',
    				groupBy: 'year,month,day',
  					aggregateOp: 'sum',
    				constrain: true,
     				fromDate: new Date(2011, 1, 1),
     				toDate: new Date(2011, 1, 7),
   					grid: true
    			}
  			],
  			series : [
  				{
  					type: 'line',
     				smooth: true,
     				axis: ['left', 'bottom'],
     				xField: 'date',
     				yField : 'temperature',
     				label: {
        				display: 'none',
        				field: 'visits',
        				renderer: function(v) { return v >> 0; },
        				'text-anchor': 'middle'
     				},
     				markerConfig: {
     					radius: 5,
     					size: 5,
     					'fill': '#f00'
     				}
  				}
  			]
 		});
    	
 		
 		return [{
 			itemId : 'ybdianyaPanel',
 			items : [ybdianyaChart]
 		},{
 			itemId : 'ybdianliuPanel',
 			items : [ybdianliuChart]
 		},{
 			itemId : 'zjdianyaPanel',
 			items : [zjdianyaChart]
 		},{
 			itemId : 'kzdianyaPanel',
 			items : [kzdianyaChart]
 		},{
 			itemId : 'djdianliuPanel',
 			items : [djdianliuChart]
 		},{
 			itemId : 'djpinlvPanel',
 			items : [djpinlvChart]
 		},{
			itemId : 'qyzhidongliPanel',
			items : [qyzhidongliChart]
 		},{
 			itemId : 'dzdianliuPanel',
 			items : [dzdianliuChart]
 		},{
 			itemId : 'zdxinhaoPanel',
 			items : [zdxinhaoChart]
 		},{
 			itemId : 'speedPanel',
 			items : [speedChart]
 		},{
 			itemId : 'jiasuduPanel',
 			items : [jiasuduChart]
 		},{
 			itemId : 'temperaturePanel',
 			items : [temperatureChart]
 		}];
	}
});
