Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [ 'name', 'email', 'phone' ]
});
var userStore = Ext.create('Ext.data.Store', {
    model: 'User',
    data: [
        { name: 'Lisa', email: 'lisa@simpsons.com', phone: '555-111-1224' },
        { name: 'Bart', email: 'bart@simpsons.com', phone: '555-222-1234' },
        { name: 'Homer', email: 'homer@simpsons.com', phone: '555-222-1244' },
        { name: 'Marge', email: 'marge@simpsons.com', phone: '555-222-1254' }
    ]
});
var grid = Ext.create('Ext.grid.Panel', {
    renderTo: document.body,
    store: userStore,
    width: 400,
    height: 200,
    title: 'Application Users',
    columns: [
        {
            text: 'Name',
            width: 100,
            sortable: false,
            hideable: false,
            dataIndex: 'name'
        },
        {
            text: 'Email Address',
            width: 150,
            dataIndex: 'email'
        },
        {
            text: 'Phone Number',
           	flex: 1,
            dataIndex: 'phone'
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
    queryMode: 'local',
    displayField: 'name',
    valueField: 'abbr',
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
    queryMode: 'local',
    displayField: 'name',
    valueField: 'abbr',
});
var contactForm = Ext.create('Ext.form.Panel', {
    items: [{
            xtype: 'textfield',
            fieldLabel: 'Mobile Number',
            name: 'mob',
            allowBlank: false
        }, {

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
        state1,
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
        text: 'Reset',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: 'Submit',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        handler: function() {
            var form = this.up('form').getForm();
            if (form.isValid()) {
                form.submit({
                    success: function(form, action) {
                        Ext.Msg.alert('Success', action.result.msg);
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('Failed', action.result.msg);
                    }
                });
            }
        }
    }]
});
var myForm = Ext.create('Ext.form.Panel', {
    items: [{
        xtype: 'textfield',
        fieldLabel: 'First Name',
        name: 'first',
        allowBlank: false
    }, {

        xtype: 'textfield',
        fieldLabel: 'Last Name',
        name: 'last',
        allowBlank: false
    }, {
        xtype: 'datefield',
        name: 'DOB',
        fieldLabel: 'Date Of Birth',
        allowBlank: false
    }, {

        xtype: 'textfield',
        fieldLabel: 'Email Address',
        name: 'email',
        allowBlank: false
    }, {

        xtype: 'textfield',
        fieldLabel: 'BloodGroup',
        name: 'BG',
        allowBlank: false
    }],

    // Reset and Submit buttons
    buttonAlign: 'center',
    buttons: [{
        text: 'Reset',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }, {
        text: 'Submit',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        handler: function() {
            var form = this.up('form').getForm();
            if (form.isValid()) {
                form.submit({
                    success: function(form, action) {
                        Ext.Msg.alert('Success', action.result.msg);
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('Failed', action.result.msg);
                    }
                });
            }
        }
    }]
});
var leftPanel = Ext.create('Ext.TabPanel', {

    bodyPadding: 5,
    columnWidth: 0.5,
    fullscreen: true,
    tabBarPosition: 'bottom',

    defaults: {
        styleHtmlContent: true
    },

    items: [{

            title: 'Personal Details',
            iconCls: 'home',
            items: myForm

        },
        {
            title: 'Contact Details',
            iconCls: 'user',
            items: contactForm
        }
    ]
});


var rightPanel = Ext.create('Ext.TabPanel', {
    columnWidth: 0.5,
    title: 'Right Panel',
//    html: 'Hello World',
	items: [grid]
});
Ext.onReady(function() {
    Ext.create('Ext.panel.Panel', {
        renderTo: Ext.getBody(),
        id: 'myPanel',
        layout: 'column',
        //height: 200,
        bodyPadding: 10,
        title: '<b style="color:orange;textAlign:center">Utkarsh Shrivastava - 11306</b>',
		html: '<b>This is simple web page showing the use of TapPanel,panels,grid,textfield,datefield,combobox And buttons</b>',
        items: [leftPanel, rightPanel]
    })
});