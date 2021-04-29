Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: ['id', 'title', 'desc', 'releaseyear', 'language', 'director', 'rating', 'special']
});
var userStore = Ext.create('Ext.data.Store', {
    model: 'User',
    storeId: 'MovieGridStore',
    pageSize: 15,
    remoteFilter: true,
    remoteSort: true,
    proxy: {
        type: 'ajax',
        url: '/JavaTraining/getDataServletJDBC',
        reader: {
            type: 'json',
            transform: {
                fn: function (data) {
                    var finalData = JSON.parse(data);
                    return finalData;
                },
                scope: this
            },
            rootProperty: 'movies',
            totalProperty: 'total'
        },
        enablePaging: true,

    },
    autoLoad: true

});



Ext.define('Language', {
    extend: 'Ext.data.Model',

    fields: [
        //	'id',
        'name'
    ]
});


var userStore1 = Ext.create('Ext.data.Store', {
    model: 'Language',
    storeId: 'languageStore',
    proxy: {
        type: 'ajax',
        url: '/JavaTraining/getLanguage',
        reader: {
            type: 'json',
            transform: {
                fn: function (data) {
                    var finalData = JSON.parse(data);
                    return finalData;
                },
                scope: this
            },
            rootProperty: 'language',
            //            totalProperty: 'total'
        },

    },
    autoLoad: true

});


Ext.define('Rating', {
    extend: 'Ext.data.Model',

    fields: [
        //	'id',
        'rating'
    ]
});


var userStore2 = Ext.create('Ext.data.Store', {
    model: 'Rating',
    storeId: 'RatingStore',
    proxy: {
        type: 'ajax',
        url: '/JavaTraining/GetRating',
        reader: {
            type: 'json',
            transform: {
                fn: function (data) {
                    var finalData = JSON.parse(data);
                    return finalData;
                },
                scope: this
            },
            rootProperty: 'Rating',
            //            totalProperty: 'total'
        },

    },
    autoLoad: true

});


Ext.define('SP', {
    extend: 'Ext.data.Model',
    fields: ['special']
});
var SpecialFeature = Ext.create('Ext.data.Store', {
    model: 'SP',
    //    data: [
    //	{special: 'Trailers'},
    //	{special: 'Commentaries'},
    //	{special: 'Deleted Scenes'},
    //	{special: 'Behind the Scenes'}
    proxy: {
        type: 'ajax',
        url: '/JavaTraining/GetSpecial',
        reader: {
            type: 'json',
            transform: {
                fn: function (data) {
                    var finalData = JSON.parse(data);
                    return finalData;
                },
                scope: this
            },
            rootProperty: 'special',
            //            totalProperty: 'total'
        },

    },

    //    ]
});

function editForm(gridData, selectedRecord, flag) {
    if (flag === true) {
        var x = gridData.items[0].data.special;
        var t = x.split(',');
    }
    return new Ext.form.Panel({
        border: false,
        layout: 'vbox',
        url: '/JavaTraining/EditForm',
        width: '100%',
        margin: '20 0 0 0',
        height: '100%',
        items: [{
                xtype: 'textfield',
                name: 'title1',
                //		flex:1,
                width: 480,
                fieldLabel: 'Title',
                allowBlank: false,
                value: flag ? gridData.items[0].data.title : null,

            },
            {
                xtype: 'numberfield',
                name: 'releaseyear1',
                width: 480,
                fieldLabel: 'Release Year',
                allowBlank: false,
                minValue: 1970,
                maxValue: 2021,
                value: flag ? gridData.items[0].data.releaseyear : null
            },
            {
                xtype: 'combobox',
                fieldLabel: 'Special Feature',
                forceSelection: true,
                name: 'special',
                multiSelect: true,
                width: 480,
                store: SpecialFeature,
                valueField: 'special',
                displayField: 'special',
                //		queryMode: 'local',
                value: flag ? t : null,
                emptyText: 'Enter the special feature of movie',
            },
            {
                xtype: 'combobox',
                fieldLabel: 'Rating',
                //            id: 'rating1',
                //        flex: 1,
                forceSelection: true,
                name: 'Rating',
                width: 480,
                store: userStore2,
                valueField: 'rating',
                displayField: 'rating',
                value: flag ? gridData.items[0].data.rating : null,
                emptyText: 'Enter the rating of movie',
            }, {
                xtype: 'combobox',
                fieldLabel: '<b>Language</b>',
                //id: 'lang1',
                //        flex: 1,
                name: 'language',
                forceSelection: true,
                //margin: '25 0 0 0',
                store: userStore1,
                width: 480,
                valueField: 'name',
                displayField: 'name',
                allowBlank: false,
                value: flag ? gridData.items[0].data.language : null,
                //            queryMode: 'local',

                emptyText: 'Enter the language of movie',
            },
            {
                xtype: 'textfield',
                name: 'director1',
                width: 480,
                fieldLabel: 'Director',
                allowBlank: false,
                value: flag ? gridData.items[0].data.director : null
            },
            {
                xtype: 'textarea',
                name: 'desc1',
                width: 480,
                height: 100,
                fieldLabel: 'Description',
                allowBlank: false,
                value: flag ? gridData.items[0].data.desc : null
            }
        ],

        buttonAlign: 'center',
        buttons: [{
                text: 'Apply',
                formBind: true, //only enabled once the form is valid
                disabled: true,
                handler: function () {

                    if (flag === true) {
                        var form = this.up('form').getForm();
                        form.submit({
                            url: '/JavaTraining/EditForm',
                            method: 'post',
                            params: {
                                //                        id: form.ge
                                id: gridData.items[0].id,
                                desc: form.getFieldValues().desc1,
                                title: form.getFieldValues().title1,
                                releaseyear: form.getFieldValues().releaseyear1,
                                director: form.getFieldValues().director1,
                                language: form.getFieldValues().Language1,
                                rating: form.getFieldValues().Rating,
                                sp: form.getFieldValues().special.toString()
                            },
                            success: function (form) {
                                Ext.getCmp('MovieGrid').getStore().reload();
                                Ext.Msg.alert('Success', 'OK');
                                Ext.toast('Records have been Edited');
                            },
                            failure: function (form) {
                                Ext.Msg.alert('Failed', 'TRY Again');
                            }
                        })
                        //						userStore.getView().refresh();	
                        this.up('window').close();
                    }
                    if (flag === false) {
                        var form = this.up('form').getForm();
                        form.submit({
                            url: '/JavaTraining/AddForm',
                            method: 'post',
                            params: {
                                //                        id: form.ge


                                desc: form.getFieldValues().desc1,
                                title: form.getFieldValues().title1,
                                releaseyear: form.getFieldValues().releaseyear1,
                                director: form.getFieldValues().director1,
                                language: form.getFieldValues().Language1,
                                rating: form.getFieldValues().Rating,
                                sp: form.getFieldValues().special.toString()
                            },
                            success: function (form) {
                                Ext.getCmp('MovieGrid').getStore().reload();
                                Ext.Msg.alert('Success', 'OK');
                                Ext.toast('Record have been Added');
                            },
                            failure: function (form) {
                                Ext.Msg.alert('Failed', 'TRY Again');
                            }
                        })
                        //						userStore.getView().refresh();
                        this.up('window').close();
                    }
                }
            },
            {
                text: 'Cancel',
                handler: function () {
                    this.up('window').close();
                }
            }
        ]
    })
};


var paginationToolbar = Ext.create('Ext.toolbar.Paging', {

    pageSize: 20,
    displayInfo: true,
    inputItemWidth: 60,
    //        displayMsg: 'Invoices {0} - {1} of {2}',
    emptyMsg: "No Records to display",
    store: userStore,
    dock: 'top',
    items: [{
            xtype: 'button',
            id: 'resetbuton',
            text: '<i class="fa fa-bolt" aria-hidden="true" style="color:#5fa2dd; font-size : 1rem"></i>',
            handler : function(){
                userStore.sorters.clear();
                userStore.clearFilter();
                grid.getSelectionModel().deselectAll();
                userStore.load();
            }
        },
        {
            xtype: 'button',
            disable: true,
            id: 'editButton',
            text: '<i class="fa fa-pencil-square-o" aria-hidden="true" style="color:#5fa2dd; font-size : 1rem"></i> Edit',
            listeners: {
                click: function () {
                    flag = true;
                    gridData = grid.getSelectionModel().getSelected();
                    var selectedRecord = grid.getSelectionModel().getSelection();


                    if (gridData.items.length === 1) {
                        var x = new Ext.window.Window({
                            title: 'Edit Movie',
                            closeAction: 'hide',
                            header: {
                                titleAlign: 'center'
                            },
                            height: 500,
                            width: 490,
                            layout: 'fit',
                            items: [editForm(gridData, selectedRecord, flag)],

                        }).show();
                    } else {

                        Ext.Msg.alert('Invalid Action', 'Please Select one row to edit');
                    }
                }
            }
        },
        {
            xtype: 'button',
            disable: true,
            id: 'addButton',
            text: '<i class="fa fa-plus-circle" aria-hidden="true" style="color:#5fa2dd; font-size : 1rem"></i> Add',
            listeners: {
                click: function () {
                    flag = false;
                    gridData = grid.getSelectionModel().getSelected();
                    var selectedRecord = grid.getSelectionModel().getSelection();
                    new Ext.window.Window({
                        title: 'Add Movie',
                        closeAction: 'hide',
                        //id:'myWin',
                        header: {
                            titleAlign: 'center'
                        },
                        height: 500,
                        width: 490,
                        layout: 'fit',
                        items: [editForm(gridData, selectedRecord, flag)],
                        //autoShow: true

                    }).show();
                }
            }
        },
        {
            xtype: 'button',
            id: 'deleteButton',
            // disabled: true,
            text: '<i class="fa fa-trash" aria-hidden="true" style="color:#5fa2dd; font-size : 1rem"></i> Delete',
            listeners: {
                click: function () {
                    gridData = grid.getSelectionModel().getSelected();
                    var item = [];

                    if (gridData.items.length >= 1) {

                        for (var i = 0; i < gridData.length; i++) {
                            item.push(gridData.items[i].id)
                        }
				debugger;
                        var s = item.toString();
                        var ajax = Ext.Ajax.request({
                            url: '/JavaTraining/DeleteForm',
                            method: 'post',
                            params: {
                                items: s
                            },
                            success: function (form) {
                                Ext.getCmp('MovieGrid').getStore().reload();
                                Ext.toast('Records Deleted');
                            },
                            failure: function (form) {
                                Ext.Msg.alert('Failed', 'TRY Again');
                            }
                        })
                        //					userStore.getView().refresh();

                    } else {
                        Ext.Msg.alert('Invalid Action', 'Please Select atleast one row to delete');
                    }
                }
            }
        }, {
            xtype: 'button',
            //            disabled: true,
            id: 'softDelete',
            text: '<i class="fa fa-trash" aria-hidden="true" style="color:#5fa2dd; font-size : 1rem"></i> Soft Delete',
            listeners: {
                click: function () {
                    gridData = grid.getSelectionModel().getSelected();
                    var item = [];

                    if (gridData.items.length >= 1) {
                        for (var i = 0; i < gridData.length; i++) {
                            item.push(gridData.items[i].id)
                        }
			debugger;
                        var s = item.toString();
                        var ajax = Ext.Ajax.request({
                            url: '/JavaTraining/SoftDeleteForm',
                            method: 'post',
                            params: {
                                items: s
                            },
                            success: function (form) {
                                Ext.getCmp('MovieGrid').getStore().reload();
                                //Ext.Msg.alert('Success', 'OK');
                                Ext.toast('Records Deleted');
                            },
                            failure: function (form) {
                                Ext.Msg.alert('Failed', 'TRY Again');
                            }
                        })
                        //					userStore.getView().refresh();

                    } else {
                        Ext.Msg.alert('Invalid Action', 'Please Select atleast one row to delete');
                    }
                }
            }
        }
    ],
    //listeners:{
    //	click: function(){
    //		var selectedLength= grid.getSelectionModel().getSelection().length;
    //			if(selectedLength === 1){
    //				paginationToolbar.getComponent('editButton').setDisabled(false);
    //				paginationToolbar.getComponent('deleteButton').setDisabled(false);
    //				paginationToolbar.getComponent('softDelete').setDisabled(false);
    //			}
    //			else if(selectedLength >= 1){
    //				paginationToolbar.getComponent('editButton').setDisabled(true);
    //				paginationToolbar.getComponent('deleteButton').setDisabled(false);
    //				paginationToolbar.getComponent('softDelete').setDisabled(false);
    //			}
    //			else {
    //				paginationToolbar.getComponent('editButton').setDisabled(true);
    //				paginationToolbar.getComponent('deleteButton').setDisabled(true);
    //				paginationToolbar.getComponent('softDelete').setDisabled(true);
    //			}
    //	}
    //}
})

var grid = Ext.create('Ext.grid.Panel', {

    store: userStore,
    id: 'MovieGrid',
    height: 650,
    scrollable: true,
    dockedItems: [paginationToolbar],
    plugins: [{
        ptype: 'gridfilters'
    }, {

        ptype: 'cellediting',
        clicksToEdit: 2
    }],
    selType: 'checkboxmodel',
    title: '<b>Movie Grid</b>',

    columns: [{
        text: '<b>Title</b>',
        flex: 1,
        dataIndex: 'title',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }, {
        text: '<b>Description</b>',
        flex: 1,
        dataIndex: 'desc',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }, {
        text: '<b>Release Year</b>',
        flex: 1,
        dataIndex: 'releaseyear',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }, {
        text: '<b>Language</b>',
        flex: 1,
        dataIndex: 'language',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }, {
        text: '<b>Director</b>',
        flex: 1,
        dataIndex: 'director',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }, {
        text: '<b>Rating</b>',
        flex: 1,
        dataIndex: 'rating',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }, {
        text: '<b>Special Feature</b>',
        flex: 1,
        dataIndex: 'special',
        //        editor: 'textfield',
        filter: {
            type: 'string',
        }
    }],
    // listeners: {
    //     selectionchange: function () {
    //         var selectedLength = grid.getSelectionModel().getSelection().length;
    //         debugger;
    //         if (selectedLength === 1) {
    //             paginationToolbar.getComponent('editButton').setDisabled(false);
    //             paginationToolbar.getComponent('deleteButton').setDisabled(false);
    //             paginationToolbar.getComponent('softDelete').setDisabled(false);
    //         } else if (selectedLength >= 1) {
    //             paginationToolbar.getComponent('editButton').setDisabled(true);
    //             paginationToolbar.getComponent('deleteButton').setDisabled(false);
    //             paginationToolbar.getComponent('softDelete').setDisabled(false);
    //         } else {
    //             paginationToolbar.getComponent('editButton').setDisabled(true);
    //             paginationToolbar.getComponent('deleteButton').setDisabled(true);
    //             paginationToolbar.getComponent('softDelete').setDisabled(true);
    //         }
    //     }
    // }
});




var row1 = Ext.create('Ext.form.FieldContainer', {
    layout: {
        type: 'vbox',
        align: 'middle',
        pack: 'center'
    },
    bodyPadding: 80,
    border: false,
    flex: 1,
    items: [{
        xtype: 'textfield',
        fieldLabel: '<b>Movie Name</b>',
        name: 'title',
        flex: 1,
        bodyPadding: 30,
        id: 'mov1',

        emptyText: 'Enter the name of the movie',

    }, {
        xtype: 'numberfield',
        fieldLabel: '<b>Release Year</b>',
        name: 'releaseyear',
        id: 'date1',
        bodyPadding: 30,
        margin: '25 0 0 0',
        minValue: 1970,
        maxValue: 2021,
        keyNavEnabled: false,
        mouseWheelEnabled: false,
        flex: 1,

        emptyText: 'Enter the release year',
    }]
});

var row2 = Ext.create('Ext.form.FieldContainer', {
    preventHeader: true,
    id: 'row2',
    flex: 1,
    bodyPadding: 80,
    border: false,
    layout: {
        type: 'vbox',
        align: 'middle',
        pack: 'center'
    },
    items: [{
            xtype: 'textfield',
            flex: 1,
            fieldLabel: '<b>Director Name</b>',
            name: 'director',
            id: 'direct',

            emptyText: 'Enter the name of the director',
        },
        {
            xtype: 'combobox',
            fieldLabel: '<b>Language</b>',
            id: 'lang1',
            flex: 1,
            forceSelection: true,
            name: 'language',
            margin: '25 0 0 0',
            store: userStore1,
            valueField: 'name',
            displayField: 'name',
            //            queryMode: 'local',

            emptyText: 'Enter the language of movie',
        },
    ],
});


var toppanel = Ext.create('Ext.form.Panel', {
    id: 'formPanel',
    border: false,
    bodyPadding: 30,
    title: '<b style="textAlign:center">Movie Advance Search</b>',
    layout: {
        type: 'hbox',
        align: 'middle',
        pack: 'center'
    },
    items: [row1, row2],
    buttonAlign: 'center',
    buttons: [{
            text: 'Search <i class="fa fa-search" aria-hidden="true"></i>',
            formBind: true,
            disabled: true,
            handler: function () {

                Ext.toast('Filter Applied Successfully!')
                var form = this.up('form').getForm();
                var temp = form.getFieldValues();
                //temp.releaseyear = temp.releaseyear.getFullYear()
                var grid = Ext.getCmp('MovieGrid');
                grid.store.clearFilter();
                if (temp.title) {
                    grid.store.filter('title', temp.title)
                }
                if (temp.releaseyear) {
                    grid.store.filter('releaseyear', temp.releaseyear)
                }
                if (temp.director) {
                    grid.store.filter('director', temp.director)
                }
                if (temp.language) {
                    grid.store.filter('language', temp.language)
                }
            }
        }, {
            text: 'Reset <i class="fa fa-refresh" aria-hidden="true"></i>',
            handler: function () {
                this.up('form').getForm().reset();
                Ext.getCmp('MovieGrid').store.clearFilter();
                Ext.toast('Filter Reset Successfully!')
            }
        }

    ]
});

var bottompanel = Ext.create('Ext.panel.Panel', {
    border: false,
    width: '100%',
    items: [grid]

});


Ext.onReady(function () {
    Ext.create('Ext.panel.Panel', {
        renderTo: Ext.getBody(),
        id: 'myPanel',
        border: false,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        items: [toppanel, bottompanel]
    })
});