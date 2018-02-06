function isUserNameValid() {
  var htmlElement = document.getElementById('username');
  var userName = htmlElement.value;
  var userNameRegExp = new RegExp('^[a-zA-Z][a-zA-Z0-9-_\.]{4,20}$');
  var isElementValid = userNameRegExp.test(userName);

  if (!isElementValid && !!userName) {
    setDisabledCssClass(htmlElement);
  } else {
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

  if (!allowedSymbolsRegexp.test(lastName) && !!lastName) {
    setDisabledCssClass(htmlLastNameElement);
  } else {
    removeDisabledCssClass(htmlLastNameElement);
  }

  if (!allowedSymbolsRegexp.test(firstName) && !!firstName) {
    setDisabledCssClass(htmlFirstNameElement);
  } else {
    removeDisabledCssClass(htmlFirstNameElement);
  }

  return allowedSymbolsRegexp.test(firstName) && allowedSymbolsRegexp.test(lastName);
}

function isPasswordValid() {
  var password1Element = document.getElementById('inputPassword1');
  var password2Element = document.getElementById('inputPassword2');
  var password1 = password1Element.value;
  var password2 = password2Element.value;
  var isPasswordValid = password1 === password2;

  if (!isPasswordValid) {
    setDisabledCssClass(password1Element);
    setDisabledCssClass(password2Element);
  } else {
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
