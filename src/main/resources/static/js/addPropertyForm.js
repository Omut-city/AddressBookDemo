
async function checkPropertyName() {
    var categoryId = document.getElementById('category_id').value;
    var categoryName = document.getElementById('category_name').textContent;
    var name = document.getElementById('form_name').value;

    if (null == name || name == '') {
        document.getElementById('name_comment').textContent = "Name can't to be empty";
    } else {
        let url = 'http://localhost:8888/api/exists/property/' + categoryId + '/' + name;
        let response = await fetch(url);
        let isExists = await response.json();
        if (isExists) {
            document.getElementById('name_comment').textContent = "Property with name '" + name + "' is exists for category: " + categoryName;
        } else {
            document.getElementById('name_comment').textContent = null;
        }
    }
    validatePropertyForm();
}

function validatePropertyForm() {
    var name = document.getElementById('form_name').value;
    if (null == name || name == '') {
        document.getElementById("form_submit").disabled = true;
    } else {
        document.getElementById("form_submit").disabled = false;
    }
}
