function isCommentValid() {
  var htmlElement = document.getElementById('comment');
  var comment = htmlElement.value;
  var commentRegExp = new RegExp('.{0,254}');
  var isElementValid = commentRegExp.test(comment);

  if (!isElementValid && !!comment) {
    setDisabledCssClass(htmlElement);
  } else {
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
