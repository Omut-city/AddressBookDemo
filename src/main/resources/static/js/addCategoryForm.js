
async function checkCategoryName() {
    var name = document.getElementById('form_name').value;
    console.log("Check name: " + name);
    if (null == name || name == '') {
        document.getElementById('name_comment').textContent = "Name can't to be empty";
    } else {
        let url = 'http://localhost:8888/api/exists/category/name/' + name;
        let response = await fetch(url);
        let isNameExists = await response.json();
        if (isNameExists) {
            document.getElementById('name_comment').textContent = "Category with name '" + name + "' is exists already";
        } else {
            document.getElementById('name_comment').textContent = null;
        }
    }
    validateCategoryForm();
}

function validateCategoryForm() {
    var name = document.getElementById('form_name').value;
    if (null == name || name == '') {
        document.getElementById("form_submit").disabled = true;
    } else {
        document.getElementById("form_submit").disabled = false;
    }
}
