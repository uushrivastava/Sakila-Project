Ext.onReady(function () {

    var data, flag;
    Ext.define('Emp', {
        extend: 'Ext.data.Model',
        fields: ['Title', 'Description', 'Release_Year', 'Director_Name', 'Rating', 'Special_Feature', 'Language', ]
    });

    var langStore = new Ext.data.Store({
        storeId: 'langStore',
        fields: [
            'abbr',
            'lang'

        ],
        data: [
            [0, 'Hindi'],
            [1, 'English'],
            [2, 'Telugu'],
            [3, 'Chinese'],
            [4, 'Japanese'],
            [5, 'Bhojpuri'],
            [6, 'Spanish'],
            [7, 'French'],
            [8, 'Kannada'],
        ]
    });


    var appuserStore = Ext.create('Ext.data.Store', {

        model: 'Emp',
        //autoLoad: true,
        //pageSize: 4,


        data: [{
                Title: 'The Matrix',
                Description: 'Action/Sci-fi',
                Release_Year: '1999',
                Rating: 'PG',
                Special_Feature: 'Deleted Scenes',
                Language: 'English',
                Director_Name: 'Lana Wachowski,Lilly Wachowski'
            },
            {
                Title: 'Iron Man',
                Description: 'Action/Sci-fi',
                Release_Year: '2008',
                Rating: 'G',
                Special_Feature: 'Deleted Scenes',
                Language: 'English',
                Director_Name: 'Jon Favreau'
            },
            {
                Title: 'Kung Fu Panda',
                Description: 'Family/Comedy',
                Release_Year: '2008',
                Rating: 'G',
                Special_Feature: 'Deleted Scenes',
                Language: 'English',
                Director_Name: 'Mark Osborne, John Stevenson'
            },
            {
                Title: 'Shaolin Soccer',
                Description: 'Comedy/Action',
                Release_Year: '2001',
                Rating: 'G',
                Special_Feature: 'Deleted Scenes',
                Language: 'Chinese',
                Director_Name: 'Stephen Chow'
            },
            {
                Title: 'Mirage',
                Description: 'Drama',
                Release_Year: '2018',
                Rating: 'PG',
                Special_Feature: 'Deleted Scenes',
                Language: 'Spanish',
                Director_Name: 'Oriol Paulo'
            },
            {
                Title: 'Lost Bullet',
                Description: 'Action/Sci-fi',
                Release_Year: '2020',
                Rating: 'R',
                Special_Feature: 'Deleted Scenes',
                Language: 'French',
                Director_Name: 'Guillaume Pierret'
            },
            {
                Title: 'Ganga',
                Description: 'Drama/Action',
                Release_Year: '2006',
                Rating: 'NC-17',
                Special_Feature: 'Deleted Scenes',
                Language: 'Bhojpuri',
                Director_Name: 'Abhishek Chhadha'
            }
        ]
    });

    //store.load();

    var myForm2 = Ext.create('Ext.form.Panel', {
        border: false,
        title: 'Movie Advance Search',
        height: 280,
        layout: {
            type: 'vbox'
        },
        items: [{
            xtype: 'panel',
            layout: {
                type: 'hbox'
            },
            width: '100%',
            border: false,
            items: [{
                xtype: 'textfield',
                fieldLabel: 'Movie Name',
                name: 'Name',
                id: 'first2',
                //allowBlank: false,
                margin: '30 30 10 300',
                //width:'53%'
            }, {
                xtype: 'textfield',
                fieldLabel: 'Director Name',
                name: 'Director2',
                id: 'last',
                //allowBlank: false,
                margin: '30 300 30 50',
                //width:'53%'
            }]
        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox'
            },
            width: '100%',
            border: false,
            items: [{
                xtype: 'numberfield',
                fieldLabel: 'Release Year',
                name: 'Release_Year',
                id: 'DOB2',
                //allowBlank: false,
                margin: '30 30 10 300',
                minValue: 1970,
                maxValue: 2021,
                hideTrigger: true

                //width:'53%',
            }, {
                xtype: 'panel',
                layout: {
                    type: 'hbox'
                },
                width: '100%',
                border: false,
                items: [{
                    xtype: 'combobox',
                    name: 'Language',
                    id: 'Lang2',
                    margin: '30 300 30 50',
                    fieldLabel: 'Language',
                    displayField: 'lang',
                    anchor: '0',
                    width: '47%',
                    queryMode: 'local',
                    //store: Ext.data.StoreManager.lookup('langStore'),
                    store: Ext.getStore('langStore'),
                    //forceSelection: 'true'
                }]
            }]
        }],



        buttonAlign: 'center',
        buttons: [{

            text: 'Reset',
            //margin:'10 10 10 300',		
            handler: function () {
                Ext.getCmp('grid2').store.clearFilter();
                this.up('form').getForm().reset();
            },
            //margin:'10 10 10 100'
        }, {
            text: 'Search',
            //margin:'10 550 10 10',
            formBind: true, //only enabled once the form is valid        disabled: true,
            listeners: {
                click: function () {
                    var form = this.up('form').getForm();
                    var temp = form.getFieldValues();
                    var grid = Ext.getCmp('grid2');
                    //console.log(temp);
                    //console.log(grid);
                    if (temp.Name) {
                        grid.store.filter('Title', temp.Name)
                    }
                    if (temp.Release_Year) {
                        grid.store.filter('Release_Year', temp.Release_Year)
                    }
                    if (temp.Director2) {
                        grid.store.filter('Director_Name', temp.Director2)
                    }
                    if (temp.Language) {
                        grid.store.filter('Language', temp.Language)
                    }

                }
            }
        }],

    });


    var myForm = new Ext.form.Panel({
        border: false,
        height: 230,
        layout: {
            type: 'vbox'
        },
        items: [{
            xtype: 'panel',
            layout: {
                type: 'hbox'
            },
            width: '100%',
            border: false,
            items: [{
                xtype: 'textfield',
                fieldLabel: 'Title',
                name: 'Title',
                id: 'Title',
                //allowBlank: false,
                margin: '10 10 30 10',
                //width:'53%'
            }, {
                xtype: 'textfield',
                fieldLabel: 'Description',
                name: 'Description',
                id: 'Description',
                allowBlank: false,
                margin: '10 10 30 10',
                //width:'53%'
            }]
        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox'
            },
            width: '100%',
            border: false,
            items: [{
                xtype: 'numberfield',
                fieldLabel: 'Release Year',
                name: 'Release_Year',
                id: 'Release_Year',
                //allowBlank: false,
                margin: '10 10 30 10',
                minValue: 1970,
                maxValue: 2021,
                hideTrigger: true
                //width:'53%',
            }, {
                xtype: 'textfield',
                fieldLabel: 'Director',
                name: 'Director_Name',
                id: 'Director_Name',
                //allowBlank: false,
                margin: '10 10 30 10',
                //width:'53%'
            }]
        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox'
            },
            width: '100%',
            border: false,
            items: [{
                xtype: 'textfield',
                fieldLabel: 'Rating',
                name: 'Rating',
                id: 'Rating',
                //allowBlank: false,
                margin: '10 10 30 10',
            }, {
                xtype: 'textfield',
                fieldLabel: 'Special Feature',
                name: 'Special_Feature',
                id: 'Special_Feature',
                //allowBlank: false,
                margin: '10 10 30 10',
                //width:'53%'
            }]
        }, {
            xtype: 'panel',
            layout: {
                type: 'hbox'
            },
            width: '100%',
            border: false,
            items: [{
                xtype: 'combobox',
                name: 'Language',
                id: 'Language',
                margin: '10 10 30 10',
                fieldLabel: 'Language',
                displayField: 'lang',
                anchor: '0',
                width: '100%',
                queryMode: 'local',
                //store: Ext.data.StoreManager.lookup('langStore'),
                store: Ext.getStore('langStore'),
                //forceSelection: 'true'
            }]
        }],




        buttonAlign: 'center',
        buttons: [{

            text: 'Reset',
            //margin:'10 10 10 300',		
            handler: function () {
                //this.up('form').getForm().reset();

                if (flag === 1) {
                    Ext.getCmp('Title').setValue(data.items[0].data.Title);
                    Ext.getCmp('Description').setValue(data.items[0].data.Description);
                    Ext.getCmp('Release_Year').setValue(data.items[0].data.Release_Year);
                    Ext.getCmp('Director_Name').setValue(data.items[0].data.Director_Name);
                    Ext.getCmp('Rating').setValue(data.items[0].data.Rating);
                    Ext.getCmp('Special_Feature').setValue(data.items[0].data.Special_Feature);
                    Ext.getCmp('Language').setValue(data.items[0].data.Language);
                } else {
                    myForm.reset();
                }
            },
            //margin:'10 10 10 100'
        }, {
            text: 'Apply',
            //margin:'10 550 10 10',
            formBind: true, //only enabled once the form is valid        disabled: true,
            listeners: {
                click: function () {

                    var form = this.up('form').getForm();
                    var newform = form.getFieldValues();
                    if (flag === 1) {
                        var record = appuserStore.findRecord('Title', data.items[0].data.Title);
                        //console.log(newform);
                        record.set('Title', newform.Title);
                        record.set('Description', newform.Description);
                        record.set('Release_Year', newform.Release_Year);
                        record.set('Director_Name', newform.Director_Name);
                        record.set('Rating', newform.Rating);
                        record.set('Special_Feature', newform.Special_Feature);
                        record.set('Language', newform.Language);
                    } else {
                        appuserStore.add(newform);
                    }

                    this.up('window').close();
                    myForm.reset();
                }
            }
        }],

    });


    var grid2 = Ext.create('Ext.grid.Panel', {
        //renderTo: Ext.getBody(),
        title: 'Movie Grid',
        store: appuserStore,
        width: '100%',
        height: 330,
        id: 'grid2',

        selModel: {
            checkOnly: false,
            injectCheckbox: 'first',
            mode: 'SIMPLE'
        },
        selType: 'checkboxmodel',

        columns: [{
                text: 'Title',
                width: 200,
                sortable: false,
                hideable: false,
                dataIndex: 'Title',
                editor: 'textfield'
            }, {
                text: 'Description',
                width: 200,
                sortable: false,
                hideable: false,
                dataIndex: 'Description',
                editor: 'textfield'
            },
            {
                text: 'Release Year',
                width: 150,
                sortable: false,
                hideable: false,
                dataIndex: 'Release_Year',
                editor: 'datefield',
                hideTrigger: true
                //renderer: Ext.util.Format.dateRenderer('d-m-Y')
            },
            {
                text: 'Director Name',
                width: 200,
                dataIndex: 'Director_Name',
                hidden: false,
                editor: 'textfield'
            },
            {
                text: 'Rating',
                width: 130,
                dataIndex: 'Rating',
                hidden: false,
                editor: 'textfield'
            }, {
                text: 'Special Feature',
                width: 200,
                dataIndex: 'Special_Feature',
                hidden: false,
                editor: 'textfield'

            }, {
                text: 'Language',
                width: 150,
                dataIndex: 'Language',
                hidden: false,
                editor: 'textfield'
            }
        ],

        bbar: [{
            xtype: 'pagingtoolbar',
            store: appuserStore, // same store GridPanel is using
            dock: 'top',
            displayInfo: true,
            border: false,
        }, {
            xtype: 'button',
            text: 'Edit',
            disable: true,
            listeners: {
                click: function () {

                    flag = 1;
                    data = grid2.getSelectionModel().getSelected();
                    console.log(data);
                    //debugger;
                    if (data.items.length === 1) {
                        new Ext.window.Window({
                            title: 'Edit Movie',
                            closeAction: 'hide',
                            header: {
                                titleAlign: 'center'
                            },
                            height: 370,
                            width: 600,
                            layout: 'fit',
                            items: [myForm],
                            //autoShow: true

                        }).show();
                        Ext.getCmp('Title').setValue(data.items[0].data.Title);
                        Ext.getCmp('Description').setValue(data.items[0].data.Description);
                        Ext.getCmp('Release_Year').setValue(data.items[0].data.Release_Year);
                        Ext.getCmp('Director_Name').setValue(data.items[0].data.Director_Name);
                        Ext.getCmp('Rating').setValue(data.items[0].data.Rating);
                        Ext.getCmp('Special_Feature').setValue(data.items[0].data.Special_Feature);
                        Ext.getCmp('Language').setValue(data.items[0].data.Language);

                    } else {
                        //myForm.reset();
                        Ext.Msg.alert('Invalid Action', 'Please Select one row to edit');
                    }
                }

            }
        }, {
            xtype: 'button',
            text: 'Add',
            formBind: true, //only enabled once the form is valid        disabled: true,
            listeners: {
                click: function () {
                    flag = 0;
                    myForm.reset();
                    //if(!Ext.getCmp('myWin')){   
                    new Ext.window.Window({
                        title: 'Edit Movie',
                        closeAction: 'hide',
                        //id:'myWin',
                        header: {
                            titleAlign: 'center'
                        },
                        height: 370,
                        width: 600,
                        layout: 'fit',
                        items: [myForm],
                        //autoShow: true

                    }).show();
                    //}
                    //Ext.getCmp('myWin').show(); 
                }
            }

        }]

    });
    var mainPanel = Ext.create('Ext.panel.Panel', {
        //title:'Movie Grid',
        border: false,
        layout: {
            type: 'anchor'
        },
        items: [myForm2, grid2],

        renderTo: Ext.getBody()
    });

});