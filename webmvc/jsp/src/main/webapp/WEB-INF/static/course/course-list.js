$(function() {
  $('.message').slideDown().delay(3000).slideUp();

  $('.delete-course-button').click(e => {
    e.preventDefault();
    const id = $(e.target).closest('a').data('id');
    const modal = $('#delete-course-modal');
    modal.data('id', id);
    modal.modal('show');
  });

  $('#confirm-delete-course').click(() => {
    const modal = $('#delete-course-modal');
    const id = modal.data('id');
    modal.modal('hide');
    const $form = $('<form action="/enrolment-management/course/delete-course" method="POST"></form>');
    $form.append(`<input type="hidden" name="id" value="${id}">`);
    $(document.body).append($form);
    $($form).submit();
  });
});