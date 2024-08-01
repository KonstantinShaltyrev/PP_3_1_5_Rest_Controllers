const ModalUpdate = document.getElementById('modal-update-user')
ModalUpdate.addEventListener('show.bs.modal', function (event) {
    const userData = event.relatedTarget

    document.querySelector('#form-control-update-id').value = userData.getAttribute('data-bs-userId');
    document.querySelector('#form-control-update-firstname').value = userData.getAttribute('data-bs-firstName');
    document.querySelector('#form-control-update-lastname').value = userData.getAttribute('data-bs-lastName');
    document.querySelector('#form-control-update-email').value = userData.getAttribute('data-bs-email');
    document.querySelector('#form-control-update-birthday').value = userData.getAttribute('data-bs-birthday');
    document.querySelector('#form-control-update-phone').value = userData.getAttribute('data-bs-phoneNumber');
    document.querySelector('#form-control-update-role-selection').value = userData.getAttribute('data-bs-roles');
})

const updateUserForm = document.getElementById('modal-update-form')
updateUserForm.addEventListener('submit', async function (event) {
    event.preventDefault();

    const id = document.querySelector('#form-control-update-id').value
    const firstName = document.querySelector('#form-control-update-firstname').value
    const lastName = document.querySelector('#form-control-update-lastname').value
    const email = document.querySelector('#form-control-update-email').value
    const phone = document.querySelector('#form-control-update-phone').value
    const password = document.querySelector('#form-control-update-password').value
    const birthday = document.querySelector('#form-control-update-birthday').value
    const role = document.querySelector('#form-control-update-role-selection').value

    const url = 'http://localhost:8080/admin/save_user';

    const response = await fetch(url, {
        method: "PUT",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
            "id": id,
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
        loadUsersTable(getUserList());
        document.getElementById('modal-update-form-button-close').click();
        document.querySelector('#admin-update-user-form-first-name-error').textContent = '';
        document.querySelector('#admin-update-user-form-last-name-error').textContent = '';
        document.querySelector('#admin-update-user-form-email-error').textContent = '';
        document.querySelector('#admin-update-user-form-birthday-error').textContent = '';
        document.querySelector('#admin-update-user-form-phone-error').textContent = '';
        document.querySelector('#admin-update-user-form-password-error').textContent = '';
        updateUserForm.reset();
    } else {
        document.querySelector('#admin-update-user-form-first-name-error').textContent = response.headers.get('firstName');
        document.querySelector('#admin-update-user-form-last-name-error').textContent = response.headers.get('lastName');
        document.querySelector('#admin-update-user-form-email-error').textContent = response.headers.get('email');
        document.querySelector('#admin-update-user-form-birthday-error').textContent = response.headers.get('birthday');
        document.querySelector('#admin-update-user-form-phone-error').textContent = response.headers.get('phoneNumber');
        document.querySelector('#admin-update-user-form-password-error').textContent = response.headers.get('password');
    }
})
