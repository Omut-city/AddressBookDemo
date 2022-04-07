
function initBody() {
    console.log('Page is loaded...');
}

async function checkEmail() {
    var email = document.getElementById('form_email').value;
    console.log("Check email: " + email);
    if (null == email || email == '') {
        let comment = "Email can't to be empty";
        document.getElementById('email_comment').textContent = comment;
    } else {
        let url = 'http://localhost:8888/api/exists/contact/email/' + email;
        let response = await fetch(url);
        let isEmailExists = await response.json();
        if (isEmailExists) {
            document.getElementById('email_comment').textContent = "Contact with e-mail '" + email + "' is exists already";
        } else {
            document.getElementById('email_comment').textContent = null;
        }
    }
    validateContactForm();
}

async function checkName() {
    var name = document.getElementById('form_name').value;
    console.log("Check name: " + name);
    if (null == name || name == '') {
        document.getElementById('name_comment').textContent = "Name can't to be empty";
    } else {
        document.getElementById('name_comment').textContent = null;
    }
    validateContactForm();
}

async function checkSurname() {
    var surname = document.getElementById('form_surname').value;
    console.log("Check surname: " + surname);
    if (null == surname || surname == '') {
        document.getElementById('surname_comment').textContent = "Surname can't to be empty";
    } else {
        document.getElementById('surname_comment').textContent = null;
    }
    validateContactForm();
}

async function checkGender() {
    validateContactForm();
}

async function checkPhone() {
    var phone = document.getElementById('form_phone').value;
    console.log("Check phone: " + phone);
    if (null == phone || phone == '') {
        document.getElementById('phone_comment').textContent = "Phone number can't to be empty";
    } else {
        document.getElementById('phone_comment').textContent = null;
    }
    validateContactForm();
}

async function checkSelectCategory() {
    var categorySelect = document.getElementById("categoryId");
    var categoryId = categorySelect.options[categorySelect.selectedIndex].value;
    var propertySelect = document.getElementById("propertyId");
    var propertyLength = propertySelect.options.length;

    for (i = 0; i < propertyLength; i++) {
        var currentProperty = propertySelect.options[i];
        var currentArray = currentProperty.value.split('_');
        var currentCategoryId = currentArray[0];

        if (categoryId == currentCategoryId) {
            currentProperty.removeAttribute("disabled");
        } else {
            currentProperty.setAttribute("disabled", "true");
        }
    }
    propertySelect.removeAttribute("disabled");
}

function validateContactForm() {
    var name = document.getElementById('form_name').value;
    var surname = document.getElementById('form_surname').value;
    var email = document.getElementById('form_email').value;
    var phone = document.getElementById('form_phone').value;
    var gender_male = document.getElementById('form_gender_male');
    var gender_female = document.getElementById('form_gender_female');

    if (valueIsEmpty(name)
        || valueIsEmpty(surname)
        || valueIsEmpty(email)
        || valueIsEmpty(phone)
        || (!gender_male.checked && !gender_female.checked)) {
        document.getElementById("form_submit").disabled = true;
    } else {
        document.getElementById("form_submit").disabled = false;
    }
}

function valueIsEmpty(value) {
    return null == value || value == '';
}

