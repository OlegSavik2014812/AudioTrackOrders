function isCommentValid() {
  var htmlElement = document.getElementById('comment');
  var comment = htmlElement.value;
  var commentRegExp = new RegExp('.{0,254}');
  var isElementValid = commentRegExp.test(comment);
  var elem = $('#price').siblings('.validation-message');

  if (!isElementValid && !!comment) {
    elem.addClass('visible');
    setDisabledCssClass(htmlElement);
  } else {
    elem.removeClass('visible');
    removeDisabledCssClass(htmlElement);
  }
  return isElementValid;
}

function setDisabledCssClass(htmlElement) {
  htmlElement.classList.add('disabled');
}

function removeDisabledCssClass(htmlElement) {
  htmlElement.classList.remove('disabled');
}

function isFormCommentValid() {
  var isFeedbackAllowed = isCommentValid();

  if (isFeedbackAllowed) {
    document.getElementById('modal-button-submit').removeAttribute('disabled');
  } else {
    document.getElementById('modal-button-submit').setAttribute('disabled', 'disabled');
  }
}
