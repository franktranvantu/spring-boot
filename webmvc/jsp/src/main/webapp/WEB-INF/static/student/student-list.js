$(function() {
  $('#student').DataTable();

  $('.dataTables_length').addClass('bs-select');

  $('.message').slideDown().delay(3000).slideUp();

  $('.delete-student-button').click(e => {
    e.preventDefault();
    const id = $(e.target).closest('a').data('id');
    const modal = $('#delete-student-modal');
    modal.data('id', id);
    modal.modal('show');
  });

  $('#confirm-delete-student').click(() => {
    const modal = $('#delete-student-modal');
    const id = modal.data('id');
    modal.modal('hide');
    const $form = $('<form action="/enrolment-management/student/delete-student" method="POST"></form>');
    $form.append(`<input type="hidden" name="id" value="${id}">`);
    $(document.body).append($form);
    $($form).submit();
  });
});