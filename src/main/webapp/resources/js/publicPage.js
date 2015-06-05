var oPublicTable;
var myTable;

$(document).ready(function () {
    publicPage.init();
});

var publicPage = {
    init: function () {
        myTable = $('#societies-announcement-table');
        publicPage.createTable();
    },
    createTable: function () {




        oPublicTable = myTable.dataTable({
            "ajax": {
                "url": '/ajax/getSocieties',
                "dataSrc": ""
            },
            "columns": [
                {'data': 'Id'},
                {'data': 'Name'}
            ],
            "columnDefs": [
                {
                    "targets": [ 0 ],
                    "visible": false,
                    "searchable": false
                }
            ],
            "fnDrawCallback": function () {
                myTable.find('tbody').find('tr').click(function () {
                    var data = oPublicTable.fnGetData(this);
                    console.log(data);

                    location.href = "/society/" + data.Id + "/announcements";

                });
            }
        });
    }
};
