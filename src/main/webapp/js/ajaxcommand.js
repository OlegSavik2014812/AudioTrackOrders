function executeTrackAdd(url) {
  $.ajax(
    {
      type: "POST",
      url: url,
      success: function () {
        $('#addModal').modal('show');
      },
      error: function (e) {
        console.log(e);
      }
    });
}
