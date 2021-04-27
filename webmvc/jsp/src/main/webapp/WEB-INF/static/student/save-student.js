$(function() {
  $('.message').slideDown().delay(3000).slideUp();

  $('#back').click(() => {
    window.location.href = '/enrolment-management/student';
  });

  const options = {
    format: 'dd/mm/yyyy',
    todayHighlight: true,
    autoclose: true
  }
  $('#dob').datepicker(options);
});