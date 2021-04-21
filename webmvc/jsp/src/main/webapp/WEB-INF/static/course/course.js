$(function() {
  $('#back').click(() => {
    window.location.href = '/enrolment-management/course';
  });

  $('.delete-course-link').click(e => {
    const courseId = $(e.target).data('id');
    $('#course-id-to-delete').val(courseId);
    $('#delete-course-modal').modal('show');
  });

  $('#delete-course-btn').click(() => {
    const courseId = $('#course-id-to-delete').val();
    $('#delete-course-modal').modal('hide');
    window.location.href = `/enrolment-management/course/delete-course/${courseId}`;
  });
});