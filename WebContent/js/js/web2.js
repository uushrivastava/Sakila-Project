Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: ['first', 'last', 'DOB', 'gender', 'email', 'phone']
});
var userStore = Ext.create('Ext.data.Store', {
    model: 'User',
    data: [{
            first: 'Lisa',
            last: 'kudrow',
            email: 'lisa@simpsons.com',
            phone: '555-111-1224',
            DOB: '1999-12-01',
            gender: 'Female'
        },
        {
            first: 'Bart',
            last: 'pit',
            email: 'bart@simpsons.com',
            phone: '555-222-1234',
            DOB: '1999-11-01',
            gender: 'Male'
        },
        {
            first: 'Homer',
            last: 'gomer',
            email: 'homer@simpsons.com',
            phone: '555-222-1244',
            DOB: '1999-10-01',
            gender: 'Male'
        },
        {
            first: 'Marge',
            last: 'peace',
            email: 'marge@simpsons.com',
            phone: '555-222-1254',
            DOB: '1999-09-01',
            gender: 'Female'
        },
        {
            first: 'Homer',
            last: 'gomer',
            email: 'homer@simpsons.com',
            phone: '555-222-1244',
            DOB: '1999-08-01',
            gender: 'Male'
        },
        {
            first: 'Marge',
            last: 'peace',
            email: 'marge@simpsons.com',
            phone: '555-222-1254',
            DOB: '1999-07-01',
            gender: 'Female'
        }
    ]
});

var date = Ext.create('Ext.form.field.Month', {
        format: 'F, Y',
        fieldLabel: 'Date',})
//        renderTo: Ext.getBody()

function editFormWindow(selectedRecord){
	Ext.create('Ext.window.Window',{
		title: 'Edit Window',
		id: 'editWindow',
		height: 300,
		width: 1000,
		items:[{
			xtype: 'form',
			id: 'formm',
			items: [{
            xtype: 'textfield',
            fieldLabel: 'First Name',
            name: 'first',
			value: selectedRecord.get('first')
            

        }, {

            xtype: 'textfield',
            fieldLabel: 'Last Name',
            name: 'last',
			value: selectedRecord.get('last')
        }, {
            xtype: 'textfield',
            fieldLabel: 'Mobile Number',
            name: 'phone',
			value: selectedRecord.get('phone')
        }, {
            xtype: 'datefield',
            name: 'DOB',
            fieldLabel: 'Date Of Birth',
			value: selectedRecord.get('DOB')
        }, {

            xtype: 'textfield',
            vtype: 'email',
            fieldLabel: 'Email Address',
            name: 'email',
			value: selectedRecord.get('email')
        }]
		}],
		buttonAlign: 'center',
   		buttons: [{
            text: '<b>OK </b>',
            handler: function() {
               	//this.up('form').getForm().reset();
				//console.log(userStore);
				var formdata = Ext.getCmp('formm').getValue()
				debugger;
				
				Ext.Msg.alert('Success','Data Updated')
            }
        }]
	}).show()
}

var paginationToolbar = new Ext.PagingToolbar({
    pageSize: 20,
    displayInfo: true,
    //        displayMsg: 'Invoices {0} - {1} of {2}',
    emptyMsg: "No Records to display",
    store: myStore,
    dock: 'top',
	items: [{
		xtype: 'button',
		text: 'Edit',
		handler: function(){
			var selectedRecord = Ext.getCmp('customerGridId').getSelectionModel().getSelection();
			//debugger;
			editFormWindow(selectedRecord[0]);
		}
		
	}]
});
var grid = Ext.create('Ext.grid.Panel', {

    store: userStore,
    //    width: 400,
    height: 340,
	id: 'pesonalGrid',
    plugins: [{
	ptype: 'gridfilters'
		}
	,{
	
        ptype: 'cellediting',
        clicksToEdit: 1
			}],
    title: 'Application Users',
		selModel: {
		checkOnly: false,
		injectCheckBox: 'first',
		model: 'SIMPLE' 
	},
	selType: 'checkboxmodel',
	dockedItems: [{
		//xtype: 'pagingtoolbar',
		store: userStore,
		dock: 'bottom',
			items: [{
		xtype: 'button',
		text: 'Edit',
		handler: function(){
			var selectedRecord = Ext.getCmp('pesonalGrid').getSelectionModel().getSelection();
			//debugger;
			editFormWindow(selectedRecord[0]);
		}
		
	}]
	}],
    columns: [{
            text: '<b>First Name</b>',
            //flex: 1,
            sortable: false,
            hideable: false,
            dataIndex: 'first',
			editor: 'textfield',
			filter:{
							type:'string',
						}

        },
        {

            text: '<b>Last Name</b>',
            flex: 1,
            sortable: false,
            hideable: false,
            dataIndex: 'last',
			editor: 'textfield',
			filter:{
							type:'string',
						}

        },
        {
            text: 'Email Address',
            flex: 1,
            dataIndex: 'email',
			editor: 'textfield',
			filter:{
							type:'string',
						}
        },
        {
            text: 'Phone Number',
            flex: 1,
            dataIndex: 'phone',
			editor: 'textfield',filter:{
							type:'string',
						}
        },
        {
            text: 'Date Of Birth',
            flex: 1,
            dataIndex: 'DOB',
			editor: 'textfield',
			filter:{
							type:'string',
						}
        },
        {
            text: 'Gender',
            flex: 1,
            dataIndex: 'gender',
			editor: 'textfield',
			filter:{
							type:'string',
						}
        }
    ]


});

var states = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data: [{
            "abbr": "MH",
            "name": "Maharashtra"
        },
        {
            "abbr": "OR",
            "name": "Orrisa"
        },
        {
            "abbr": "RJ",
            "name": "Rajasthan"
        },
        {
            "abbr": "MP",
            "name": "Madhya Pradesh"
        },
        {
            "abbr": "CG",
            "name": "Chhatissgarh"
        },
        {
            "abbr": "DL",
            "name": "Delhi"
        }
    ]
});

var state1 = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'States',
    store: states,
	name: 'state',
    queryMode: 'local',
    displayField: 'name',
    valueField: 'name',
});

var city = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data: [{
            "abbr": "bom",
            "name": "Mumbai"
        },
        {
            "abbr": "bbsr",
            "name": "Bhubaneswar"
        },
        {
            "abbr": "jr",
            "name": "Jaipur"
        },
        {
            "abbr": "bP",
            "name": "Bhopal"
        },
        {
            "abbr": "r",
            "name": "Raipur"
        },
        {
            "abbr": "DiL",
            "name": "New Delhi"
        }
    ]
});

var city1 = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'City',
    store: city,
	name: 'city',
    queryMode: 'local',
    displayField: 'name',
    valueField: 'name',
});
var contactForm = Ext.create('Ext.form.Panel', {

    items: [{

            xtype: 'textfield',
            fieldLabel: 'Address Line -1',
            name: 'add1',
            allowBlank: false
        }, {

            xtype: 'textfield',
            fieldLabel: 'Address Line -2',
            name: 'add2',
            allowBlank: false
        },
        city1,
        {

            xtype: 'textfield',
            fieldLabel: 'District',
            name: 'distt',
            allowBlank: false
        },
        state1,date,
        {

            xtype: 'textfield',
            fieldLabel: 'PinCode',
            name: 'pin',
            allowBlank: false
        }
    ],

    // Reset and Submit buttons
    buttonAlign: 'center',
    buttons: [{
            text: 'Reset <i class="fa fa-refresh" aria-hidden="true"></i>',
            handler: function() {
                this.up('form').getForm().reset();
            }
        }, {
            text: 'Submit <i class="fa fa-check-square" aria-hidden="true"></i>',
            formBind: true, //only enabled once the form is valid
            disabled: true,
            handler: function() {
                var form = this.up('form').getForm();
                if (form.isValid()) {
					var temp = form.getFieldValues();
					contactStore.add(temp);
                    Ext.Msg.alert('Success', '<b>OK</b>');
                }
                //                form.submit({
                //                    success: function(form, action) {
                //                        Ext.Msg.alert('Success', action.result.msg);
                //                    },
                //                    failure: function(form, action) {
                //                        Ext.Msg.alert('Failed', action.result.msg);
                //                    }
                //                });
                else {
                    Ext.Msg.alert('Failed', 'TRY AGAIN');
                }
            }
        }

    ]
});
var myForm = Ext.create('Ext.form.Panel', {
    scrollable: true,
    layout: {
        type: 'vbox'
        //	align: 'center'
    },
    items: [{
            xtype: 'textfield',
            fieldLabel: 'First Name',
            name: 'first',
            allowBlank: false,

        }, {

            xtype: 'textfield',
            fieldLabel: 'Last Name',
            name: 'last',
            allowBlank: false
        }, {
            xtype: 'textfield',
            fieldLabel: 'Mobile Number',
            name: 'phone',
            allowBlank: false
        }, {
            xtype: 'datefield',
            name: 'DOB',
            fieldLabel: 'Date Of Birth',
            allowBlank: false
        }, {

            xtype: 'textfield',
            vtype: 'email',
            fieldLabel: 'Email Address',
            name: 'email',
            allowBlank: false
        },
        {
            xtype: 'fieldcontainer',
            fieldLabel: 'Gender',
            defaultType: 'radiofield',
            defaults: {
                flex: 1
            },
            layout: 'hbox',
            items: [{
                boxLabel: 'Male',
                name: 'gender',
                inputValue: 'Male',
                id: 'radio1'
            }, {
                boxLabel: 'Female',
                name: 'gender',
                inputValue: 'Female',
                id: 'radio2'
            }, {
                boxLabel: 'Other',
                name: 'gender',
                inputValue: 'Other',
                id: 'radio3'
            }]
        },
        {

            xtype: 'textfield',
            fieldLabel: 'BloodGroup',
            name: 'BG',
            allowBlank: false

        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox',
                align: 'justify'
            },
            items: [{
                xtype: 'button',
                text: 'Reset <i class="fa fa-refresh" aria-hidden="true"></i>',
                handler: function() {
                    this.up('form').getForm().reset();
                }
            }, {
                xtype: 'button',
                text: 'Submit <i class="fa fa-check-square" aria-hidden="true"></i>',
                formBind: true, //only enabled once the form is valid
                disabled: true,
                handler: function() {
                    var form = this.up('form').getForm();
                    if (form.isValid()) {

                        var temp = form.getFieldValues();
                        temp.DOB = temp.DOB.getFullYear() + '-' + (temp.DOB.getMonth() + 1) + '-' + temp.DOB.getDate();
                        delete temp['BG']
                        userStore.add(temp);
                        Ext.Msg.alert('Success', 'OK');

//                                            form.submit({
                        //                          success: function(form, action) {
                        //                             Ext.Msg.alert('Success', 'OK');
                        //                          },
                        //                          failure: function(form, action) {
                        //                            Ext.Msg.alert('Failed', 'Try Again');
                        //                         }
                        //                      });
                    } else {
                        Ext.Msg.alert('Failed', 'Try Again');
                    }
                }
            }]
        }
    ],

});

Ext.define('contact', {
    extend: 'Ext.data.Model',
    fields: ['add1', 'add2', 'city', 'distt', 'state', 'pin']
});
var contactStore = Ext.create('Ext.data.Store', {
    model: 'contact',
    data: [{
            add1: 'ABC',
            add2: 'XYZ',
            city: 'Delhi',
            distt: 'Delhi',
            state: 'Delhi',
            pin: '100010'
        }
    ]
});

var contactGrid  = Ext.create('Ext.grid.Panel', {

    store: contactStore,
    //    width: 400,
    height: 340,
    title: 'Contact Details',
    columns: [{
            text: 'Address Line -1',
            flex: 1,
            sortable: false,
            hideable: false,
            dataIndex: 'add1'
        },
        {

            text: 'Address Line -2',
            flex: 1,
            sortable: false,
            hideable: false,
            dataIndex: 'add2'

        },
        {
            text: 'City',
            flex: 1,
            dataIndex: 'city'
        },
        {
            text: 'District',
            flex: 1,
            dataIndex: 'distt'
        },
        {
            text: 'State',
            flex: 1,
            dataIndex: 'state'
        },
        {
            text: 'Pincode',
            flex: 1,
            dataIndex: 'pin'
        }
    ]
});

var leftPanel = Ext.create('Ext.TabPanel', {

    bodyPadding: 5,
    //    columnWidth: 0.5,
    flex: 1,
    fullscreen: true,

    //    defaults: {
    //        styleHtmlContent: true
    //    },

    items: [{

            title: 'Personal Details',

            items: myForm

        },
        {
            title: 'Contact Details',

            items: contactForm
        }
    ]
});


var rightPanel = Ext.create('Ext.TabPanel', {
    //    columnWidth: 0.5,
    flex: 1,
    title: 'Right Panel',
    header: {
        style: {
            background: 'black'
        }
    },
    layout: 'fit',
    //    html: 'Hello World',
    items: [grid,contactGrid]
});
Ext.define('Demo', {
    extend: 'Ext.data.Model',
    fields: [{
            name: 'ID',
            type: 'int'
        },
        {
            name: 'NAME',
            type: 'string'
        },
        {
            name: 'NAME2',
            type: 'string'
        },
        {
            name: 'VAL1',
            type: 'int'
        },
        {
            name: 'vAL2',
            type: 'float'
        },
        {
            name: 'VAL3',
            type: 'float'
        },
        {
            name: 'VAL4',
            type: 'float'
        },
        {
            name: 'TEST',
            type: 'string'
        },
        {
            name: 'DEPt',
            type: 'string'
        },
        {
            name: 'Ratio',
            type: 'float'
        }

    ]
});


var myStore = Ext.create('Ext.data.Store', {
    model: 'Demo',
    storeId: 'customerStoreId',
	pageSize: 20,
    proxy: {
        type: 'ajax',
        url: './js/js/data.json',
        reader: {
            type: 'json'
//            rootProperty: 'users'
//			totalProperty: 1000
        },
		enablePaging: true,
		
    },
    autoLoad: true
});

//myStore.load({
//	params: {
//		start: 0,
//		limit: 20
//	}
//})


var maingrid = Ext.create('Ext.grid.Panel', {
    store: Ext.data.StoreManager.lookup(
        'customerStoreId'), // or use Ext.getStore('customerStoreId')
    id: 'customerGridId',
    height: 380,
    //    scrollable: true,
    title: 'User Data',
	selModel: {
		checkOnly: false,
		injectCheckBox: 'first',
		model: 'SIMPLE' 
	},
	selType: 'checkboxmodel',
    columns: [{
            text: 'ID',
            width: 100,
            sortable: false,
            hideable: false,
            dataIndex: 'ID'
        },
        {
            text: 'NAME',
            dataIndex: 'NAME',
            flex: 1,
			editor: 'textfield'
        },
        {
            text: 'NAME2',
            dataIndex: 'NAME2',
            flex: 1
        },
        {
            text: 'VAL1',
            dataIndex: 'VAL1',
            flex: 1
        },
        {
            text: 'vAL2',
            dataIndex: 'vAL2',
            flex: 1
        },
        {
            text: 'VAL3',
            dataIndex: 'VAL3',
            flex: 1
        },
        {
            text: 'VAL4',
            dataIndex: 'VAL4',
            flex: 1
        },
        {
            text: 'TEST',
            dataIndex: 'TEST',
            flex: 1
        },
        {
            text: 'DEPt',
            dataIndex: 'DEPt',
            flex: 1
        },
        {
            text: 'Ratio',
            dataIndex: 'Ratio',
            flex: 1
        }
    ],
    dockedItems: [paginationToolbar]
});
var toppanel = Ext.create('Ext.panel.Panel', {
    id: 'top',
    bodyPadding: 10,
    layout: {
        type: 'hbox'
    },
    html: '<b>This is simple web page showing the use of TapPanel,panels,grid,textfield,datefield,combobox And buttons</b>',
    items: [leftPanel, rightPanel]
});
var bottompanel = Ext.create('Ext.panel.Panel', {
    id: 'bottom',
    bodyPadding: 10,
    items: [maingrid]

});
Ext.onReady(function() {
    Ext.create('Ext.panel.Panel', {
        renderTo: Ext.getBody(),
        id: 'myPanel',
        //        layout: 'column',
        //height: 200,
        bodyPadding: 10,
        title: '<b style="color:orange;textAlign:center">Utkarsh Shrivastava - 11306</b>',
        header: {
            items: [{
                xtype: 'button',
                style: {
                    color: 'white',
                    background: 'orange'
                },
                text: 'About Us <i class="fa fa-info-circle" aria-hidden="true"></i>',
                handler: function() {
                    Ext.create('Ext.window.Window', {
                        title: 'About Us',
                        height: 300,
                        width: 500,
                        layout: 'fit',
                        items: {
                            xtype: 'panel',
                            border: false,
                            html: '<b> However, creating an About US page that accurately describes your company can be easier said than done. You can probably think of hundreds of things you want people to know, from your history to your successes to your values, but cramming all of that information on one page is overwhelming for you and your visitors.</b>'



                        }
                    }).show();
                }
            }]
        },
        items: [toppanel, bottompanel]
    })
});