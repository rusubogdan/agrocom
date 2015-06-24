var editor; // use a global for the submit and return data rendering in the examples
var oTable;

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
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=general';
            } else if(!window.location.href.toString().includes("/home?selectedMenuItem=general")) {
                window.location = '/home?selectedMenuItem=general';
            } else {
                selectedMenuItem = 'general';
                home.dataTable.showGeneralInfo();
            }
        });

        $('#activities').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=activities';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=activities")) {
                window.location = '/home?selectedMenuItem=activities'
            } else {
                selectedMenuItem = 'activities';
                home.dataTable.createTable();
            }
        });

        $('#payments').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=payments';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=payments")) {
                window.location = '/home?selectedMenuItem=payments'
            } else {
                selectedMenuItem = 'payments';
                home.dataTable.createTable();
            }
        });

        $('#employees').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=employees';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=employees")) {
                window.location = '/home?selectedMenuItem=employees';
            } else {
                selectedMenuItem = 'employees';
                home.dataTable.createTable();
            }
        });

        $('#jobs').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=jobs';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=jobs")) {
                window.location = '/home?selectedMenuItem=jobs';
            } else {
                selectedMenuItem = 'jobs';
                home.dataTable.createTable();
            }
        });

        $('#tenants').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=tenants';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=tenants")) {
                window.location = '/home?selectedMenuItem=tenants';
            } else {
                selectedMenuItem = 'tenants';
                home.dataTable.createTable();
            }
        });

        $('#infields').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=infields';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=infields")) {
                window.location = '/home?selectedMenuItem=infields';
            } else {
                selectedMenuItem = 'infields';
                home.dataTable.createTable();
            }
        });

        $('#garages').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=garages';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=garages")) {
                window.location = '/home?selectedMenuItem=garages';
            } else {
                selectedMenuItem = 'garages';
                home.dataTable.createTable();
            }
        });

        $('#myActivities').click(function () {
            if (!window.location.href.toString().includes("/home")) {
                window.location = '/home?selectedMenuItem=garages';
            } else if (!window.location.href.toString().includes("/home?selectedMenuItem=garages")) {
                window.location = '/home?selectedMenuItem=garages';
            } else {
                selectedMenuItem = 'garages';
                home.dataTable.createTable();
            }
        });

        // manually select the menu item
        var $2 = $('#' + selectedMenuItem + '');
        if ($2 != undefined && $2.size() > 0)
            $2[0].click();

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

            var role = 1;

//            $.get('/home/ajax/getRole', function (response) {
//                role = response.role;
//            }, false);

            $.ajax({
                url: '/home/ajax/getRole',
                success: function (response) {
                    role = response.role;
                },
                async: false
            });

            var myTable = $('#example').clone();
            myTable.attr('id', selectedMenuItem + 'Table');
            var rowToAppend = myTable.find('tr');

            for (var i = 0; i < preBuildColumns.length; i++) {
                rowToAppend.append(preBuildColumns[i]);
            }

            myTable.show();

            // delete add button
            $('.active-button').each(function () {
                $(this).remove();
            });

            // add add button
            var addButton = $("#button-sample")
                .clone()
                .attr('id', 'add-button-' + selectedMenuItem)
                .addClass('active-button')
                .show()
                .click(function () {
                    // todo change this shit :|
                    if (selectedMenuItem != 'activities')
                        window.location = selectedMenuItem.substr(0, selectedMenuItem.length - 1) + '/add';
                    else
                        window.location = 'activity/add';
                });
//            addButton.before(myTable);

            // add to home
            $('.page-content.inset .row .col-md-12').empty().append(myTable);
            if (role == 1) {
                $('.page-content.inset .row .col-md-12').append(addButton);
            }

            return myTable;

        },
        getTableSettings: function () {
            switch (selectedMenuItem) {
                case 'activities':
                    var tableName = 'activities';
                    var ajaxURL = '/home/ajax/getActivities';
                    var columns = [
                        {'data': 'workHistoryId'},
                        {'data': 'fullName'},
                        {'data': 'locationCode'},
                        {'data': 'machineryName'},
                        {'data': 'workType'},
                        {'data': 'description'},
                        {'data': 'duration'},
                        {'data': 'status'},
                        {'data': 'date'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('Worker');
                    columnsList[index++] = column;
                    column = $('<td>').html('Location  ');
                    columnsList[index++] = column;
                    column = $('<td>').html('Machinery');
                    columnsList[index++] = column;
                    column = $('<td>').html('Work type  ');
                    columnsList[index++] = column;
                    column = $('<td>').html('Description');
                    columnsList[index++] = column;
                    column = $('<td>').html('Duration (hours)  ');
                    columnsList[index++] = column;
                    column = $('<td>').html('Status');
                    columnsList[index++] = column;
                    column = $('<td>').html('Date');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": 8,
                            "data": "date",
                            "render": function (data, type, full, meta) {
                                if(type == "display"){
                                    var date = new Date(data);
                                    var options = {year: "numeric", month: "numeric", day: "numeric"};

                                    return date.toLocaleDateString('ro-RO', options);
                                }

                                return data;
                            }
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'jobs':
                    var tableName = 'jobs';
                    var ajaxURL = '/home/ajax/getJobs';
                    var columns = [
                        {'data': 'workHistoryId'},
                        {'data': 'fullName'},
                        {'data': 'locationCode'},
                        {'data': 'machineryName'},
                        {'data': 'workType'},
                        {'data': 'description'},
                        {'data': 'duration'},
                        {'data': 'status'},
                        {'data': 'date'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('Worker');
                    columnsList[index++] = column;
                    column = $('<td>').html('Location  ');
                    columnsList[index++] = column;
                    column = $('<td>').html('Machinery');
                    columnsList[index++] = column;
                    column = $('<td>').html('Work type  ');
                    columnsList[index++] = column;
                    column = $('<td>').html('Description');
                    columnsList[index++] = column;
                    column = $('<td>').html('Duration (hours)  ');
                    columnsList[index++] = column;
                    column = $('<td>').html('Status');
                    columnsList[index++] = column;
                    column = $('<td>').html('Date');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0 ],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": 8,
                            "data": "date",
                            "render": function (data, type, full, meta) {
                                if(type == "display"){
                                    var date = new Date(data);
                                    var options = {year: "numeric", month: "numeric", day: "numeric"};

                                    return date.toLocaleDateString('ro-RO', options);
                                }

                                return data;
                            }
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'employees':
                    var tableName = 'employees';
                    var ajaxURL = '/home/ajax/getEmployees';
                    var columns = [
                        {'data': 'userId'},
                        {'data': 'firstName'},
                        {'data': 'lastName'},
                        {'data': 'pin'},
                        {'data': 'email'},
                        {'data': 'phone'},
                        {'data': 'mobile'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('id');
                    columnsList[index++] = column;
                    column = $('<td>').html('First Name');
                    columnsList[index++] = column;
                    column = $('<td>').html('Last Name');
                    columnsList[index++] = column;
                    column = $('<td>').html('PIN');
                    columnsList[index++] = column;
                    column = $('<td>').html('Email');
                    columnsList[index++] = column;
                    column = $('<td>').html('Phone');
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
                case 'payments':
                    var tableName = 'payments';
                    var ajaxURL = '/home/ajax/getPayments';
                    var columns = [
                        {'data': 'paymentId'},
                        {'data': 'societyId'},
                        {'data': 'tenantName'},
                        {'data': 'paymentType'},
                        {'data': 'paymentValue'},
                        {'data': 'date'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('paymentId');
                    columnsList[index++] = column;
                    column = $('<td>').html('societyId');
                    columnsList[index++] = column;
                    column = $('<td>').html('Name');
                    columnsList[index++] = column;
                    column = $('<td>').html('Type');
                    columnsList[index++] = column;
                    column = $('<td>').html('Value');
                    columnsList[index++] = column;
                    column = $('<td>').html('Date');
                    columnsList[index++] = column;
                    var columnDefs = [
                        {
                            "targets": [ 0, 1 ],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": 5,
                            "data": "date",
                            "render": function (data, type, full, meta) {
                                if(type == "display"){
                                    var date = new Date(data);
                                    var options = {year: "numeric", month: "numeric", day: "numeric"};

                                    return date.toLocaleDateString('ro-RO', options);
                                }

                                return data;
                            }
                        }
                    ];

                    return new TableSettings(tableName, ajaxURL, columns, columnsList, columnDefs);
                case 'tenants':
                    var tableName = 'tenants';
                    var ajaxURL = '/home/ajax/getTenants';
                    var columns = [
                        {'data': 'userId'},
                        {'data': 'firstName'},
                        {'data': 'lastName'},
                        {'data': 'pin'},
                        {'data': 'email'},
                        {'data': 'phone'},
                        {'data': 'mobile'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('id');
                    columnsList[index++] = column;
                    column = $('<td>').html('First Name');
                    columnsList[index++] = column;
                    column = $('<td>').html('Last Name');
                    columnsList[index++] = column;
                    column = $('<td>').html('PIN');
                    columnsList[index++] = column;
                    column = $('<td>').html('Email');
                    columnsList[index++] = column;
                    column = $('<td>').html('Phone');
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
                        {'data': 'infieldId'},
                        {'data': 'locationCode'},
                        {'data': 'areaHa'},
                        {'data': 'landLordFullName'},
                        {'data': 'county'},
                        {'data': 'village'},
                        {'data': 'lastYear'},
                        {'data': 'twoYearsAgo'},
                        {'data': 'threeYearsAgo'},
                        {'data': 'fourYearsAgo'},
                        {'data': 'fiveYearsAgo'}
                    ];
                    var columnsList = [];
                    var index = 0;
                    var column = $('<td>').html('Id');
                    columnsList[index++] = column;
                    column = $('<td>').html('Location Code');
                    columnsList[index++] = column;
                    column = $('<td>').html('Area Ha');
                    columnsList[index++] = column;
                    column = $('<td>').html('Landlord name');
                    columnsList[index++] = column;
                    column = $('<td>').html('County');
                    columnsList[index++] = column;
                    column = $('<td>').html('Village');
                    columnsList[index++] = column;
                    column = $('<td>').html('LastYear');
                    columnsList[index++] = column;
                    column = $('<td>').html('2 Years Ago');
                    columnsList[index++] = column;
                    column = $('<td>').html('3 Years Ago');
                    columnsList[index++] = column;
                    column = $('<td>').html('4 Years Ago');
                    columnsList[index++] = column;
                    column = $('<td>').html('5 Years Ago');
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
            if (selectedMenuItem != 'activities')
                window.location = '/' + selectedMenuItem.substr(0, selectedMenuItem.length - 1)
                    + '/view/' + data[getFirstFieldName(data)];
            else
                window.location = '/' + 'activity' + '/view/' + data[getFirstFieldName(data)];
        },
        showGeneralInfo: function () {
            var genInfoHtml = $('#gen-info-sample').clone();
            genInfoHtml.attr('id', 'gen-info').show();

            $.ajax({
                url: '/home/ajax/getGeneralInfo',
                success: function (response) {
                    genInfoHtml.find('#name').html(response.societyName);
                    genInfoHtml.find('#owner').html(response.owner);
                    genInfoHtml.find('#address').html(response.address);
                    genInfoHtml.find('#employeesNumber span').html(response.employeesNumber);
                    genInfoHtml.find('#garagesCapacity span').html(response.garagesCapacity);
                    genInfoHtml.find('#totalArea span').html(response.totalArea);
                },
                async: false
            });

            $('.row .col-md-12 .container').append(genInfoHtml);

        }
    }
};

function getFirstFieldName(obj) {
    for(var a in obj)
        return a;
}