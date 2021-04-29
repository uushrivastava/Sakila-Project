    Ext.define('Ext.ux.OnlyYearPicker', {
        xtype: 'onlyyearpicker',
        extend: 'Ext.picker.Month',

        afterRender: function () {
            this.callParent();
            this.el.setStyle({
                width: '106px',
            })
        },

        renderTpl: [
            '<div id="{id}-bodyEl" data-ref="bodyEl" class="{baseCls}-body">',
              '<div id="{id}-monthEl" data-ref="monthEl" class="{baseCls}-months" style="display: none;">',
                  '<tpl for="months">',
                      '<div class="{parent.baseCls}-item {parent.baseCls}-month">',
                          '<a style="{parent.monthStyle}" role="button" hidefocus="on" class="{parent.baseCls}-item-inner">{.}</a>',
                      '</div>',
                  '</tpl>',
              '</div>',
              '<div id="{id}-yearEl" data-ref="yearEl" class="{baseCls}-years">',
                  '<div class="{baseCls}-yearnav">',
                      '<div class="{baseCls}-yearnav-button-ct">',
                          '<a id="{id}-prevEl" data-ref="prevEl" class="{baseCls}-yearnav-button {baseCls}-yearnav-prev" hidefocus="on" role="button"></a>',
                      '</div>',
                      '<div class="{baseCls}-yearnav-button-ct">',
                          '<a id="{id}-nextEl" data-ref="nextEl" class="{baseCls}-yearnav-button {baseCls}-yearnav-next" hidefocus="on" role="button"></a>',
                      '</div>',
                  '</div>',
                  '<tpl for="years">',
                      '<div class="{parent.baseCls}-item {parent.baseCls}-year">',
                          '<a hidefocus="on" class="{parent.baseCls}-item-inner" role="button">{.}</a>',
                      '</div>',
                  '</tpl>',
              '</div>',
              '<div class="' + Ext.baseCSSPrefix + 'clear"></div>',
              '<tpl if="showButtons">',
                  '<div class="{baseCls}-buttons">{%',
                      'var me=values.$comp, okBtn=me.okBtn, cancelBtn=me.cancelBtn;',
                      'okBtn.ownerLayout = cancelBtn.ownerLayout = me.componentLayout;',
                      'okBtn.ownerCt = cancelBtn.ownerCt = me;',
                      'Ext.DomHelper.generateMarkup(okBtn.getRenderTree(), out);',
                      'Ext.DomHelper.generateMarkup(cancelBtn.getRenderTree(), out);',
                  '%}</div>',
              '</tpl>',
            '</div>'
        ]
    });
Ext.define('Ext.form.field.Month', {
    extend: 'Ext.form.field.Date',
    alias: 'widget.monthfield',
    requires: ['Ext.picker.Month'],
    alternateClassName: ['Ext.form.MonthField', 'Ext.form.Month'],
    selectMonth: null,
    createPicker: function() {
        var me = this,
            format = Ext.String.format;
        return Ext.create('Ext.picker.Month', {
            pickerField: me,
            ownerCt: me.ownerCt,
            //renderTo: document.body,
            floating: true,
            hidden: true,
            focusOnShow: true,
            minDate: me.minValue,
            maxDate: me.maxValue,
            disabledDatesRE: me.disabledDatesRE,
            disabledDatesText: me.disabledDatesText,
            disabledDays: me.disabledDays,
            disabledDaysText: me.disabledDaysText,
            format: me.format,
            showToday: me.showToday,
            startDay: me.startDay,
            minText: format(me.minText, me.formatDate(me.minValue)),
            maxText: format(me.maxText, me.formatDate(me.maxValue)),
            listeners: {
                select: {
                    scope: me,
                    fn: me.onSelect
                },
                monthdblclick: {
                    scope: me,
                    fn: me.onOKClick
                },
                yeardblclick: {
                    scope: me,
                    fn: me.onOKClick
                },
                OkClick: {
                    scope: me,
                    fn: me.onOKClick
                },
                CancelClick: {
                    scope: me,
                    fn: me.onCancelClick
                }
            },
            keyNavConfig: {
                esc: function() {
                    me.collapse();
                }
            }
        });
    },
    onCancelClick: function() {
        var me = this;
        me.selectMonth = null;
        me.collapse();
    },
    onOKClick: function() {
        var me = this;
        if (me.selectMonth) {
            me.setValue(me.selectMonth);
            me.fireEvent('select', me, me.selectMonth);
        }
        me.collapse();
    },
    onSelect: function(m, d) {
        var me = this;
        me.selectMonth = new Date((d[0] + 1) + '/1/' + d[1]);
    }
});

//
//var date = Ext.create('Ext.form.field.Month', {
//        format: 'F, Y',
//        fieldLabel: 'Date',
//        renderTo: Ext.getBody()
//    });