const createNewUserForm = document.getElementById('admin-new-user-form')
createNewUserForm.addEventListener('submit', async function (event) {
    event.preventDefault();

    const firstName = document.querySelector('#form-control-first-name').value
    const lastName = document.querySelector('#form-control-last-name').value
    const email = document.querySelector('#form-control-email').value
    const phone = document.querySelector('#form-control-phone').value
    const password = document.querySelector('#form-control-password').value
    const birthday = document.querySelector('#form-control-birthday').value
    const role = document.querySelector('#form-control-role-selection').value

    const url = 'http://localhost:8080/rest/create_user';

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
                "password": password,
                "firstName": firstName,
                "lastName": lastName,
                "birthday": birthday,
                "email": email,
                "phoneNumber": phone,
                "roles": [role]
        })
    });

    if (response.ok) {
        document.getElementById('nav-user-list-tab').click();
        loadUsersTable(getUserList());
        document.querySelector('#admin-new-user-form-first-name-error').textContent = '';
        document.querySelector('#admin-new-user-form-last-name-error').textContent = '';
        document.querySelector('#admin-new-user-form-email-error').textContent = '';
        document.querySelector('#admin-new-user-form-birthday-error').textContent = '';
        document.querySelector('#admin-new-user-form-phone-error').textContent = '';
        document.querySelector('#admin-new-user-form-password-error').textContent = '';
        createNewUserForm.reset();
    } else {
        document.querySelector('#admin-new-user-form-first-name-error').textContent = response.headers.get('firstName');
        document.querySelector('#admin-new-user-form-last-name-error').textContent = response.headers.get('lastName');
        document.querySelector('#admin-new-user-form-email-error').textContent = response.headers.get('email');
        document.querySelector('#admin-new-user-form-birthday-error').textContent = response.headers.get('birthday');
        document.querySelector('#admin-new-user-form-phone-error').textContent = response.headers.get('phoneNumber');
        document.querySelector('#admin-new-user-form-password-error').textContent = response.headers.get('password');
    }
})