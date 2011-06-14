// Validate given name a-z, A-Z.
function validateName () {
	var regexp = /^[a-zA-Z]{1,}$/;
    var element = document.getElementById("firstname");
	var ok = regexp.exec(element.value);
	if (!ok) {
        element.style.color = "red";
        return false;
	} else { 
        element.style.color = "black";
        return true;
	}
}

// Validate lastname a-z, A-Z
function validateLastname () {
	var regexp = /^[a-zA-Z]{1,}$/;
    var element = document.getElementById("lastname");
	var ok = regexp.exec(element.value);
	if (!ok) {
        element.style.color = "red";
        return false;
	} else { 
        element.style.color = "black";
        return true;
	}
}

// Validate email (Regexp found here: http://fightingforalostcause.net/misc/2006/compare-email-regex.php)
function validateEmail () {
	var regexp = /^([\w\!\#$\%\&\'\*\+\-\/\=\?\^\`{\|\}\~]+\.)*[\w\!\#$\%\&\'\*\+\-\/\=\?\^\`{\|\}\~]+@((((([a-z0-9]{1}[a-z0-9\-]{0,62}[a-z0-9]{1})|[a-z])\.)+[a-z]{2,6})|(\d{1,3}\.){3}\d{1,3}(\:\d{1,5})?)$/i;
    var element = document.getElementById("email");
	var ok = regexp.exec(element.value);
	if (!ok) {
        element.style.color = "red";
        return false;
	} else { 
        element.style.color = "black";
        return true;
	}
}

// Validate Password (min 6 chars)
function validatePassword () {
    var element = document.getElementById("password");
	var ok = (element.value.length > 1);
	if (!ok) {
        element.style.color = "red";
        return false;
	} else { 
        element.style.color = "black";
        return true;
	}
}

// Check if the passwords are the same
function validatePasswordconfirm () {
    var element = document.getElementById("password");
    var element2 = document.getElementById("passwordconfirm");
	var ok = element.value == element2.value;
	if (!ok) {
        element.style.color = "red";
        return false;
	} else { 
        element.style.color = "black";
        return true;
	}
}

// Validate the whole register form. 
function validateForm() {
    nameok = validateName();
    lastnameok = validateLastname();
    emailok = validateEmail();
    passwordok = validatePassword();
    passwordconfirmok = validatePasswordconfirm();

	if (nameok && lastnameok && emailok && passwordok && passwordconfirmok)
		return true;
	else {
		alert ("Please fill in the form correctly.");
		return false;
	}
}
