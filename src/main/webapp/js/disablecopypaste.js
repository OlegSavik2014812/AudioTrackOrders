window.onload = function () {
  var myInput = document.getElementsByTagName('input')
  myInput.oncopy = function (e) {
    e.preventDefault();
  }
}
