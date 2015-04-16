var login = {
    init: function() {
        $('#toggle-login').on({
            click: function () {
                 $('#register-form').hide();
                 $('#login-form').show();
            }
        });
        $('#toggle-register').on({
            click: function () {
                 $('#login-form').hide();
                 $('#register-form').show();
            }
        });
    }
};
