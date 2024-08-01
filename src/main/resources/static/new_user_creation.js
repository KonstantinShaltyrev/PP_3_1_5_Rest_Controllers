    const signUpForm = document.getElementById('signup-form-create-button')
    signUpForm.addEventListener('click', async function (event) {
        event.preventDefault();

    console.log('pressed submit button');
    alert('alert')

        const firstName = document.querySelector('#signup-form-first-name').value
        const lastName = document.querySelector('#signup-form-last-name').value
        const email = document.querySelector('#signup-form-email').value
        const phone = document.querySelector('#signup-form-phone').value
        const password = document.querySelector('#signup-form-password').value
        const birthday = document.querySelector('#signup-form-birthday').value
        const role = 'ROLE_USER'

        const url = 'http://localhost:8080/admin/save_user';

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
            document.getElementById('signup-form-signin-button').click();
        } else {
            document.querySelector('#signup-form-first-name-error').textContent = response.headers.get('firstName');
            document.querySelector('#signup-form-last-name-error').textContent = response.headers.get('lastName');
            document.querySelector('#signup-form-email-error').textContent = response.headers.get('email');
            document.querySelector('#signup-form-birthday-error').textContent = response.headers.get('birthday');
            document.querySelector('#signup-form-phone-error').textContent = response.headers.get('phoneNumber');
            document.querySelector('#signup-form-password-error').textContent = response.headers.get('password');
        }
    })