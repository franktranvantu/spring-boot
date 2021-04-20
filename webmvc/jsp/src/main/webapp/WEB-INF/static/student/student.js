$(function() {
  $('#back').click(() => {
    window.location.href = '/student-management';
  });

  $('.delete-student-link').click(e => {
    const studentId = $(e.target).data('id');
    $('#student-id-to-delete').val(studentId);
    $('#delete-student-modal').modal('show');
  });

  $('#delete-student-btn').click(() => {
    const studentId = $('#student-id-to-delete').val();
    $('#delete-student-modal').modal('hide');
    window.location.href = `/student-management/delete-student/${studentId}`;
  });
});