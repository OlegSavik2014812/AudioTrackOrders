function isUserNameValid() {
  var htmlElement = document.getElementById('username');
  var userName = htmlElement.value;
  var userNameRegExp = new RegExp('^[a-zA-Z][a-zA-Z0-9-_\.]{4,20}$');
  var isElementValid = userNameRegExp.test(userName);

  var elem = $('#username').siblings('.validation-message');

  if (!isElementValid && !!userName) {
    setDisabledCssClass(htmlElement);
    elem.addClass('visible');
  } else {
    elem.removeClass('visible');
    removeDisabledCssClass(htmlElement);
  }

  return isElementValid;
}

function isFirstAndLastNameValid(firstName, lastName) {
  var htmlLastNameElement = document.getElementById('inputLastName');
  var htmlFirstNameElement = document.getElementById('inputFirstName');
  var firstName = htmlFirstNameElement.value;
  var lastName = htmlLastNameElement.value;
  var allowedSymbolsRegexp = new RegExp('^[A-Z][a-z\\.]{1,20}$');

  var elem = $('#inputLastName').siblings('.validation-message');
  var elem1 = $('#inputFirstName').siblings('.validation-message');

  if (!allowedSymbolsRegexp.test(lastName) && !!lastName) {
    setDisabledCssClass(htmlLastNameElement);
    elem.addClass('visible');
  } else {
    removeDisabledCssClass(htmlLastNameElement);
    elem.removeClass('visible');
  }

  if (!allowedSymbolsRegexp.test(firstName) && !!firstName) {
    setDisabledCssClass(htmlFirstNameElement);
    elem1.addClass('visible');
  } else {
    removeDisabledCssClass(htmlFirstNameElement);
    elem1.removeClass('visible');
  }

  return allowedSymbolsRegexp.test(firstName) && allowedSymbolsRegexp.test(lastName);
}

function isPasswordValid() {
  var password1Element = document.getElementById('inputPassword1');
  var password2Element = document.getElementById('inputPassword2');
  var password1 = password1Element.value;
  var password2 = password2Element.value;
  var isPasswordValid = password1 === password2;

  var elem = $('#inputPassword1').siblings('.validation-message');

  if (!isPasswordValid) {
    elem.addClass('visible');
    setDisabledCssClass(password1Element);
    setDisabledCssClass(password2Element);

  } else {
    elem.removeClass('visible');
    removeDisabledCssClass(password1Element);
    removeDisabledCssClass(password2Element);
  }

  return isPasswordValid;
}

function setDisabledCssClass(htmlElement) {
  htmlElement.classList.add('disabled');
}

function removeDisabledCssClass(htmlElement) {
  htmlElement.classList.remove('disabled');
}

function isFormValid() {
  var isUserNameAllowed = isUserNameValid();
  var isFirstAndLastNameAllowed = isFirstAndLastNameValid();
  var isPasswordAllowed = isPasswordValid();

  if (isUserNameAllowed && isFirstAndLastNameAllowed && isPasswordAllowed) {
    document.getElementById('submitButton').removeAttribute('disabled');
  } else {
    document.getElementById('submitButton').setAttribute('disabled', 'disabled');
  }
}
