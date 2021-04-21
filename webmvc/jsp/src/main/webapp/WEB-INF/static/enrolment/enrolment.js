$(function() {
    $('#back').click(() => {
        window.location.href = '/enrolment-management';
    });

    $('.delete-enrolment-link').click(e => {
        const enrolmentId = $(e.target).data('id');
        $('#enrolment-id-to-delete').val(enrolmentId);
        $('#delete-enrolment-modal').modal('show');
    });

    $('#delete-enrolment-btn').click(() => {
        const enrolmentId = $('#enrolment-id-to-delete').val();
        $('#delete-enrolment-modal').modal('hide');
        window.location.href = `/enrolment-management/delete-enrolment/${enrolmentId}`;
    });
});