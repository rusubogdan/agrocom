var editor; // use a global for the submit and return data rendering in the examples
var oTable;
var selectedMenuItem = 'generalInfo';

$(document).ready(function () {
    home.init();
});

function TableSettings (table, ajaxURL, columns, preBuildColumns, columnDefs) {
    this.table = table;
    this.ajaxURL = ajaxURL;
    this.columns = columns;
    this.preBuildColumns = preBuildColumns;
    this.columnDefs = columnDefs;
}

var home = {
    init: function () {
        $('#general').click(function () {
            selectedMenuItem = 'general';
            home.dataTable.showGeneralInfo();
        });

        $('#activities').click(function () {
            selectedMenuItem = 'activities';
            home.dataTable.createTable();
        });

        $('#employees').click(function () {
            selectedMenuItem = 'employees';
            home.dataTable.createTable();
        });

        $('#tenants').click(function () {
            selectedMenuItem = 'tenants';
            home.dataTable.createTable();
        });

        $('#infields').click(function () {
            selectedMenuItem = 'infields';
            home.dataTable.createTable();
        });

        $('#garages').click(function () {
            selectedMenuItem = 'garages';
            home.dataTable.createTable();
        });
    },
    dataTable: {
        createTable: function () {
            var settings = home.dataTable.getTableSettings();
            // this also creates the table object
            var myTable = home.dataTable.createHtmlForTable(settings.preBuildColumns);

            oTable = myTable.dataTable({
                "ajax": {
                    "url": settings.ajaxURL,
                    "dataSrc": ""
                },
                "columns": settings.columns,
                "columnDefs": settings.columnDefs,
                "fnDrawCallback": function() {
                    myTable.find('tbody').find('tr').click(function () {
                        var data = oTable.fnGetData(this);
                        console.log(data);

                        home.dataTable.showInfo(data);

                        var id = data;
                    });
                }
            })
        },
        createHtmlForTable: function (preBuildColumns) {
            var myTable = $('#example').clone();
            myTable.attr('id', selectedMenuItem + 'Table');
            var rowToAppend = myTable.find('tr');

            for (var i = 0; i < preBuildColumns.length; i++) {
                rowToAppend.append(preBuildColumns[i]);
            }

            myTable.show();

//            add to home
            $('.page-content.inset .row .col-md-12').empty().append(myTable);

            return myTable;

        },
        getTableSettings: function () {
            switch (selectedMenuItem) {
                case 'activities':
                    var tableName = 'activities';
                    var ajaxURL = '/home/ajax/getActivities';
                    var columns = [
                        {'data': 'Id'},
                        {'data': 'Worker'},
                        {'data': 'Infield'},
                        {'data': 'Date'},
                        {'data': 'Machinery'},
                        {'data': 'WorkType'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('Worker');
                    columnsList[index++] = column;
                    column = $('<td>').html('Infield');
                    columnsList[index++] = column;
                    column = $('<td>').html('Date');
                    columnsList[index++] = column;
                    column = $('<td>').html('Machinery');
                    columnsList[index++] = column;
                    column = $('<td>').html('WorkType');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "type": "date",
                            "targets": 3
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'employees':
                    var tableName = 'employees';
                    var ajaxURL = '/home/ajax/getEmployees';
                    var columns = [
                        {'data': 'Id'},
                        {'data': 'FullName'},
                        {'data': 'CNP'},
                        {'data': 'Email'},
                        {'data': 'Mobile'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('FullName');
                    columnsList[index++] = column;
                    column = $('<td>').html('CNP');
                    columnsList[index++] = column;
                    column = $('<td>').html('Email');
                    columnsList[index++] = column;
                    column = $('<td>').html('Mobile');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'tenants':
                    var tableName = 'tenants';
                    var ajaxURL = '/home/ajax/getTenants';
                    var columns = [
                        {'data': 'Id'},
                        {'data': 'FullName'},
                        {'data': 'CNP'},
                        {'data': 'Email'},
                        {'data': 'Mobile'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('FullName');
                    columnsList[index++] = column;
                    column = $('<td>').html('CNP');
                    columnsList[index++] = column;
                    column = $('<td>').html('Email');
                    columnsList[index++] = column;
                    column = $('<td>').html('Mobile');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'infields':
                    var tableName = 'infields';
                    var ajaxURL = '/home/ajax/getInfields';
                    var columns = [
                        {'data': 'Id'},
                        {'data': 'Code'},
                        {'data': 'County'},
                        {'data': 'Village'},
                        {'data': 'LastYear'},
                        {'data': 'TwoYearsAgo'},
                        {'data': 'ThreeYearsAgo'},
                        {'data': 'FourYearsAgo'},
                        {'data': 'FiveYearsAgo'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('Code');
                    columnsList[index++] = column;
                    column = $('<td>').html('County');
                    columnsList[index++] = column;
                    column = $('<td>').html('Village');
                    columnsList[index++] = column;
                    column = $('<td>').html('LastYear');
                    columnsList[index++] = column;
                    column = $('<td>').html('TwoYearsAgo');
                    columnsList[index++] = column;
                    column = $('<td>').html('ThreeYearsAgo');
                    columnsList[index++] = column;
                    column = $('<td>').html('FourYearsAgo');
                    columnsList[index++] = column;
                    column = $('<td>').html('FiveYearsAgo');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'garages':
                    var tableName = 'garages';
                    var ajaxURL = '/home/ajax/getGarages';
                    var columns = [
                        {'data': 'Id'},
                        {'data': 'Name'},
                        {'data': 'Capacity'},
                        {'data': 'Address'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('Name');
                    columnsList[index++] = column;
                    column = $('<td>').html('Capacity');
                    columnsList[index++] = column;
                    column = $('<td>').html('Address');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);

                default :
                    return [];
            }
        },
        showInfo: function (data) {
            var ajaxURL = "/home/ajax/" + selectedMenuItem + '/' + data.id;

            $.get(ajaxURL, function (response) {
                console.log(response);
            });

            $('.page-content.inset .row .col-md-12').empty().append('<div>').html('success ' + selectedMenuItem);
        },
        showGeneralInfo: function () {
            var genInfoHtml = $('#gen-info-sample').clone();
            genInfoHtml.attr('id', 'gen-info').show();



        }
    }
};
