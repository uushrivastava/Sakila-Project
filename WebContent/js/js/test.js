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
Ext.onReady(function() {
    Ext.create('Ext.TabPanel', {
        renderTo: Ext.getBody(),
        bodyPadding: 5,
//        width: 350,
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
                html: 'Contact Screen'
            }
        ]
    });
});