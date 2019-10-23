var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var startTime = "";
var endTime = "";
var dateFormat = "YYYY-MM-DD HH:MM:SS";

var fileManage = {
    query: function () {
        $(grid_selector).jqGrid('clearGridData');  //清空表格
        $(grid_selector).jqGrid('setGridParam', {  // 重新加载数据
            postData: {
                appName: $("#appName").val(),
                startTime: startTime,
                endTime: endTime,
//                state: $("#state").val()
            },   //  newdata 是符合格式要求的需要重新加载的数据
        }).trigger("reloadGrid");
    },

    reset: function () {
        $(grid_selector).jqGrid('clearGridData');  //清空表格
        $("#timeRange").val("请选择日期");
        $("#appName").val("")
        startTime = "",
            endTime = "",
            $(grid_selector).jqGrid('setGridParam', {  // 重新加载数据
                postData: {
                    appName: "",
                    startTime: "",
                    endTime: "",
                },
            }).trigger("reloadGrid");
    },

    getSelect: function () {
        var options = "";
        $.ajax({
            url: baseUrl + "/groupManage/getGroups",
            type: "POST",
            async: false,
            data: {},
            success: function (response) {
                var data = $.parseJSON(response);
                if (data.code == 200) {
                    if (data.data != null && data.data.length > 0) {
                        for (var i = 0; i < data.data.length; i++) {
                            var tmp = data.data[i];
                            if (i != data.data.length - 1) {
                                options += tmp.name + ":" + tmp.name + ";";
                            } else {
                                options += tmp.name + ":" + tmp.name;
                            }

                        }

                    } else {
                        options += "无:无";
                    }
                }
            }
        });
        return options;
    },
    initGroup: function () {
        var self = this;
        $.ajax({
            url: baseUrl + "/groupManage/getGroups",
            type: "POST",
            data: {},
            success: function (response) {
                var data = $.parseJSON(response);
                if (data.code == 200) {
                    if (data.data != null && data.data.length > 0) {
                        $("#state").empty();
                        $("#state").append("<option value=''>全部</option>");
                        for (var i = 0; i < data.data.length; i++) {
                            var tmp = data.data[i];
                            $("#state").append("<option value='" + tmp.name + "'>" + tmp.name + "</option>");
                        }
                    }
                }
                if (data.status == "error") {
                    alert(data.msg);
                }
            },
            error: function () {
                alert("请求失败！");
            }
        });
    },
    enterEvent: function () {
        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                $(grid_selector).jqGrid('clearGridData');  //清空表格
                $(grid_selector).jqGrid('setGridParam', {  // 重新加载数据
                    postData: {
                        appName: $("#appName").val(),
                        startTime: startTime,
                        endTime: endTime,
                    },
                }).trigger("reloadGrid");
            }
        });
    },
    initGridData: function () {
        var self = this;
        jQuery(grid_selector).jqGrid({
            url: baseUrl + "/apps",
            datatype: "json",
            mtype: "get",
            postData: {},
            jsonReader: {
                root: "data.list",
                page: 10,
                total: "data.pages",
                records: "data.total"
            },
            colNames: ['id', '应用ID', '应用名称', '应用类型', '当前版本号', '区域', '当前状态', '发布日期', '操作', ''],
            colModel: [
                {name: 'id', index: 'id', width: 60, sorttype: "int", editable: true, edittype: 'text', hidden: true},
                {
                    name: 'appId',
                    index: 'appId',
                    width: 100,
                    editable: true,
                    edittype: 'text',
                    editoptions: {size: "20", maxlength: "100"}
                }, {
                    name: 'appName',
                    index: 'appName',
                    width: 100,
                    editable: true,
                    edittype: "text",
                    editoptions: {size: "20", maxlength: "100"}
                }, {
                    name: 'appType',
                    index: 'appType',
                    width: 50,
                    editable: false
                }, {
                    name: 'version',
                    index: 'version',
                    width: 50,
                    editable: true,
                    edittype: "text",
                    editoptions: {size: "20", maxlength: "100"}
                }, {
                    name: 'region',
                    index: 'region',
                    width: 100,
                    editable: false,
                    hidden: true
                }, {
                    name: 'stateName',
                    index: 'stateName',
                    width: 50,
                    editable: true,
                    edittype: "select",
                    editoptions: {style: "width:158.38px;height:37px", value: selectState()},
                    //formatter: fmatterFuuc

                }, {
                    name: 'releaseDate',
                    index: 'releaseDate',
                    width: 80,
                    sortable: false,
                    editable: false,
                    formatter: function (cellvalue, options, rowObject) {
                        var date = new Date();
                        date.setTime(cellvalue);
                        return date.Format("yyyy-MM-dd HH:mm:ss");
                    }
                }, {
                    name: '', index: '', width: 80, fixed: true, sortable: false, resize: false,
                    formatter: 'actions',
                    formatoptions: {
                        keys: true,
                        delbutton: false,//disable delete button
                        delOptions: {recreateForm: true, beforeShowForm: beforeDeleteCallback},
                        editformbutton: true,
                        editOptions: {
                            recreateForm: true,
                            closeAfterEdit: true,
                            beforeShowForm: beforeEditCallback,
                            beforeSubmit: beforeSumbitCallback,
                            afterSubmit: afterSubmitCallback
                        }
                    }
                }, {
                    name: 'fileToUpload',
                    index: 'fileToUpload',
                    hidden: true,
                    editoptions: {enctype: "multipart/form-data"},
                    edittype: 'file',
                    // formatter: this.showPicture,
                    width: 150,
                    align: "left",
                    editable: true
                }
            ],
            viewrecords: true,
            height: 'auto',
            width: 'auto',
            // height: 500,
            rowNum: 10,
            rowList: [10, 20, 50],
            pager: pager_selector,
            altRows: false,
            multiselect: false,
            multiboxonly: true,
            rownumbers: true,
            editurl: baseUrl + "/apps",//nothing is saved
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    $(grid_selector).jqGrid('setLabel', 'rn', '序号', {'text-align': 'left'}, '');
                    styleCheckbox(table);
                    updateActionIcons(table);
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });
        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
        //navButtons
        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
            { 	//navbar options
                edit: false,
                editicon: 'ace-icon fa fa-pencil blue',
                add: true,
                addicon: 'ace-icon fa fa-plus-circle purple',
                del: false,
                delicon: 'ace-icon fa fa-trash-o red',
                search: false,
                searchicon: 'ace-icon fa fa-search orange',
                refresh: true,
                refreshicon: 'ace-icon fa fa-refresh green',
                view: true,
                viewicon: 'ace-icon fa fa-search-plus grey',
            },
            {
                //edit record form
                closeAfterEdit: true,
                recreateForm: true,
                viewPagerButtons: true,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);

                    $("#editmodgrid-table").css("top", "20%");
                    $("#editmodgrid-table").css("left", "40%");
                },
                onInitializeForm: function (formid) {
                    var addUrl = baseUrl + "/apps";
                    console.log($(grid_selector).jqGrid('getGridParam', 'editurl'));
                    $(grid_selector).jqGrid('setGridParam', {editurl: addUrl});
                    $("#tr_fileToUpload").css("display", "");
                    console.log(formid);
//                     alert(postdata);
//                     $(formid).attr('method', 'POST');
                    // $(formid).attr('action', '/abc');
//                     $(formid).attr('enctype', 'multipart/form-data');
                },
                afterSubmit: function (response, postdata) {
                    var res = $.parseJSON(response.responseText);
                    if (res.code != 200) {
                        // self.uploadFile(postdata);
                        alert("提交失败，请重新提交！");
                        return [false];
                        // return [false, res.msg];
                    } else {
                        alert("图片上传成功！");
                        self.uploadFile(postdata);
                        return [true];
                    }
                }
            },
            {
                //new record form
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: true,
                beforeShowForm: function (e) {
                    $("#editmodgrid-table").css("top", "20%");
                    $("#editmodgrid-table").css("left", "40%");
                    e.find("#appId").attr('readOnly', false);
                    // $(grid_selector).setGridParam().hideCol("fileToUpload").trigger("reloadGrid");
                    // $(grid_selector).setGridParam().showCol("fileToUpload").trigger("reloadGrid");
//                    var form = $(e[0]);
//                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
//                        .wrapInner('<div class="widget-header" />')
//                    style_edit_form(form);
                },
                onInitializeForm: function (formid) {
                    var addUrl = baseUrl + "/apps";
                    // console.log($(grid_selector).jqGrid('getGridParam', 'editurl'));
                    $(grid_selector).jqGrid('setGridParam', {editurl: addUrl});
                    $("#tr_fileToUpload").css("display", "");
                    // console.log(formid);
                    // $(formid).attr('method', 'POST');
                    // $(formid).attr('action', '/abc');
                    // $(formid).attr('enctype', 'multipart/form-data');
                },
                beforeSubmit: function beforeSumbit() {
                    var fileTypes = [".jpeg", ".png",".jpg", ".gif"];
                    var filePath = $('#fileToUpload').val();
                    if (filePath) {
                        var isNext = false;
                        var fileEnd = filePath.substring(filePath.indexOf("."));
                        for (var i = 0; i < fileTypes.length; i++) {
                            if (fileTypes[i] === fileEnd) {
                                isNext = true;
                                break;
                            }
                        }
                        if (!isNext) {
                            alert(" 只接受图片类型！");
                            return false;
                        }
                        return [true];
                    } else {
                        alert("请选择图片！")
                        return false;
                    }
                },

                afterSubmit: function (response, postdata) {
                    var res = $.parseJSON(response.responseText);
                    if (res.code != 200) {
                        // self.uploadFile(postdata);
                        alert("提交失败，请重新提交！");
                        return [false];
                        // return [false, res.msg];
                    } else {
                        //alert("图片上传成功！");
                        self.uploadFile(postdata);
                        return [true];
                    }
                }
            },
            {
                //delete record form
                recreateForm: true,
                onInitializeForm: function (formid) {
                    var delUrl = baseUrl + "/fileManage/deleteFile";
                    $(grid_selector).jqGrid('setGridParam', {editurl: delUrl});
                },
                beforeShowForm: function (e) {
                    var delUrl = baseUrl + "/fileManage/deleteFile";
                    $(grid_selector).jqGrid('setGridParam', {editurl: delUrl});
                    var form = $(e[0]);
                    if (form.data('styled')) return false;
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_delete_form(form);
                    form.data('styled', true);
                },
                onClick: function (e) {
                    alert(1);
                }
            },
            {
                //search form
                recreateForm: true,
                afterShowSearch: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    style_search_form(form);
                },
                afterRedraw: function () {
                    style_search_filters($(this));
                },
                multipleSearch: true,
            },
            {
                //view record form
                recreateForm: true,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                }
            }
        )

        function beforeDeleteCallback(e) {
            var form = $(e[0]);
            if (form.data('styled')) return false;
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_delete_form(form);
            form.data('styled', true);
            var delUrl = baseUrl + "/fileManage/deleteFile";
            $(grid_selector).jqGrid('setGridParam', {editurl: delUrl});
        }

        function selectState() {
            var stateListResult = "";
            $.ajax({
                type: "get",
                async: false,
                url: baseUrl + "/apps/selectState",
                success: function (result) {
                    var result = $.parseJSON(result);
                    var stateList = result.data;
                    for (i = 0; i < stateList.length; i++) {
                        stateListResult += stateList[i].dictId + ":" + stateList[i].name + ";";
                    }
                }
            });
            if (stateListResult.length > 0) {
                stateListResult=stateListResult.substring(0,stateListResult.length-1);
            }
            return stateListResult;
        }

        function beforeEditCallback(e) {
            $("#editmodgrid-table").css("top", "20%");
            $("#editmodgrid-table").css("left", "40%");
            e.find('#appId').attr('readOnly', true);
            e.find('#appName').attr('readOnly', true);
            e.find('#version').attr('readOnly', true);
            e.find('#stateName').attr('disabled', true);
            var form = $(e[0]);
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_edit_form(form);
            var addUrl = baseUrl + "/apps";
            $(grid_selector).jqGrid('setGridParam', {editurl: addUrl});
            $("#tr_fileToUpload").css("display", "");
        }

        function afterSubmitCallback(response, postdata) {
            var res = $.parseJSON(response.responseText);
            if (res.code != 200) {
                // self.uploadFile(postdata);
                alert("提交失败，请重新提交！");
                return [false];
                // return [false, res.msg];
            } else {
                //alert("图片上传成功！");
                self.uploadFile(postdata);
                return [true];
            }
        }

        function beforeSumbitCallback() {
            var fileTypes = [".jpeg", ".png",".jpg",];
            var filePath = $('#fileToUpload').val();
            if (filePath) {
                var isNext = false;
                var fileEnd = filePath.substring(filePath.indexOf("."));
                for (var i = 0; i < fileTypes.length; i++) {
                    if (fileTypes[i] === fileEnd) {
                        isNext = true;
                        break;
                    }
                }
                if (!isNext) {
                    alert("只接受图片类型");
                    return false;
                }
                return [true];
            } else {
                alert("请选择图片！")
                return false;
            }
        }

        // function onClickSubmitCallback(params, postdata) {
        //     alert("上传成功！");
        //     self.uploadFile(postdata);
        // }

        function fmatterFuuc(cellvalue, options, rowObject) {
            if (cellvalue == 1) {
                return "应用创建";
            }
            if (cellvalue == 2) {
                return "提交审核";
            }
            if (cellvalue == 3) {
                return "审核通过";
            }
            if (cellvalue == 4) {
                return "申请灰度发布";
            }
            if (cellvalue == 5) {
                return "灰度发布";
            }
            if (cellvalue == 6) {
                return "申请上线";
            }
            if (cellvalue == 7) {
                return "上线";
            }
            if (cellvalue == 8) {
                return "申请下线";
            }
            if (cellvalue == 9) {
                return "下线";
            }
        }
    },
    uploadFile: function (postdata) {
        var formData = new FormData();
        formData.append("fileToUpload", document.getElementById("fileToUpload").files[0]);
        formData.append("appName", postdata.appName);
        formData.append("appId", postdata.appId);
//        formData.append("state",postdata.state);
//        formData.append("description",postdata.description);
        $.ajax({
            url: baseUrl + "/apps/uploadFile",
            type: "PUT",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#queryBtn").click();
                var data = $.parseJSON(data);
                //   console.log(">>>" + data);
                if (data.code != 200) {
                    alert(data.msg);
                } else {
                    alert("上传成功！");
                }
            },
            error: function () {
                alert("上传失败！");
            }
        });
    },
    showPicture: function (cellvalue, options, rowObject) {
        // console.log("cellvalue:"+cellvalue);
        // return "<img src='" + cellvalue + "' height='50' width='50' />";
    },
    initGirdAutoWidth: function () {
        var parent_column = $(grid_selector).closest('[class*="col-"]');
        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', parent_column.width());
        })
        //resize on sidebar collapse/expand
        $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
            if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
                //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                setTimeout(function () {
                    $(grid_selector).jqGrid('setGridWidth', parent_column.width());
                }, 20);
            }
        })
    },
    initDatePicker: function () {
        $("#timeRange").val("请选择日期");
        $('input[name=date-range-picker]').daterangepicker({
            'applyClass': 'btn-sm btn-success',
            'cancelClass': 'btn-sm btn-default',
            showDropdowns: true,//当设置值为true的时候，允许年份和月份通过下拉框的形式选择 默认false
            timePicker24Hour: true,//设置小时为24小时制 默认false
            timePicker: true,//可选中时分 默认false
            autoUpdateInput: false,//1.当设置为false的时候,不给与默认值(当前时间)2.选择时间时,失去鼠标焦点,不会给与默认值 默认true
            locale: {
                format: dateFormat,
                separator: " 至 ",
                applyLabel: '确定',
                cancelLabel: '取消',
                customRangeLabel: "自定义"
            },
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '近7天': [moment().subtract(7, 'days'), moment()],
                '这个月': [moment().startOf('month'), moment().endOf('month')],
                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        }).on('cancel.daterangepicker', function (ev, picker) {
            $("#timeRange").val("请选择日期");
            startTime = '';
            endTime = '';
        }).on('apply.daterangepicker', function (ev, picker) {
            startTime = picker.startDate.format(dateFormat);
            endTime = picker.endDate.format(dateFormat);
            $("#timeRange").val(picker.startDate.format(dateFormat) + " 至 " + picker.endDate.format(dateFormat));
        });
    },
    init: function () {
        $(document.body).css({
            "overflow-x": "hidden"
        });
//        this.initGroup();     //查询所有组
        this.initDatePicker();  //日期函数
        this.initGirdAutoWidth(); //匹配宽度
        this.initGridData();
        this.enterEvent();
    }

}