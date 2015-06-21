$(document).ready(function () {
    societies.init();
});

var societies = {
    init: function () {
//        societies.showSocieties();
        societies.addClickEvents();
    },
    showSocieties: function () {

        $.ajax("/ajax/getSocieties", function (response) {
            console.log(response);

            var societiesList = response.societies;
            for(var i = 0; i < societiesList.length; i++) {
                societies.createSociety(societiesList[i], i);
            }

        });
    },
    createSociety: function (name, id) {
        var societyDiv = $('#sample-society').clone()
            .attr('id', 'society' + id)
            .show();
        var socHolder = $('.societies-holder');
        societyDiv.find('.top-side div').html('name');

        socHolder.appendChild(societyDiv);

    },
    addClickEvents: function () {
        $('.society-holder').each(function (index, element) {
            $(element).click(function () {
                var societyId = $(element).attr('id').split('-')[1];

                $.post('/societies/ajax/society/' + societyId, function (response) {
                    window.location = response.redirectUrl;
                });
            });
        });

        $('#add-society-button').click(function () {
            window.location = '/societies/add';
        });

    }
};
