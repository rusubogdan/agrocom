$(document).ready(function () {
    home.init();
});

var home = {
    init : function () {
        $('#my-div').on({
            click: function () {
                home.test();
            }
        });
    },
    test: function () {
        $.get('/sendMessage', function (response) {
            console.log(response);
        });
    }
};
