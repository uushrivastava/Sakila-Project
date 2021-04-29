var panel1 = Ext.create('Ext.panel.Panel',{
		bodyPadding:5,
		layout:{
			type:'column',
		},
		border: false,
		items:[{
			xtype:'textfield',
			fieldLabel:'Name',
			name:'employeename',
			columnWidth: 0.5
			//margin:'0 0 0 150',
			},{
			fieldLabel:'Employee Id',
			xtype:'numberfield',
			hideTrigger:true,
			columnWidth: 0.5,
			name:'employeeid',
			//margin:'0 0 0 40',
			/*vtype:'email',*/
			}]
	});
	var panel2 = Ext.create('Ext.panel.Panel',{
		bodyPadding:5,
		border: false,
		layout:{
			type:'column',
		},
		items:[{
				fieldLabel:'Department',
				xtype:'combobox',
				reference:'department',
				typeAhead:true,
				margin:'0 0 0 40',
				forceSelection:true,
				store:Ext.data.StoreManager.lookup('analyticsStore'),
				displayField:'department',
			},
		]
	});
Ext.onReady(function(){
	
	Ext.create('Ext.data.Store',{
		storeId:'analyticsStore',
		fields:['name','rank'],
		data:[
			{name:'Piyush',rank:'Pod Lead',department:'Analytics'},
			{name:'Palash',rank:'Intern',department:'Analytics'},
			{name:'Dibyanshu',rank:'Intern',department:'DotoneAnalytics'},
			{name:'Utkarsh',rank:'Intern',department:'DotoneAnalytics'},
			{name:'Afshin',rank:'Intern',department:'Analytics'},	
		]

	});
	
	
	
	Ext.create('Ext.panel.Panel',{
		bodyPadding:5,
		/*width:300,*/
		title:'Employee List',
		defaultType:'textfield',
		layout:{
			type:'vbox',
		},
		items: [panel1,panel2],
		buttonAlign: 'center',
		buttons: [{ 
				text: 'Submit' 
			},{
				text:'Reset'
			}
		],
		renderTo:Ext.getBody()
	});
	
	
});
