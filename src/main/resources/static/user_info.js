const currentUser = getCurrentUser();

async function getCurrentUser() {
    const url = 'http://localhost:8080/rest/user/current_user';
    const response = await fetch(url);
    if (response.ok) {
        return await response.json();
    } else {
        alert('Http connection error: ' + response.status);
    }
}


function getRoles(obj) {
    let result = '';
    for (let role of obj.roles) {
        result = result.concat(role.replace("ROLE_", "") + ' ');
    }
    return result;
}

function fillCurrentUserHeader() {
    currentUser.then(p => document.querySelector('#header_current_user_email').textContent = p.email);
    currentUser.then(p => document.querySelector('#header_current_user_roles').textContent = getRoles(p));
}

function fillCurrentUserInfoPage() {
    currentUser.then(p => document.querySelector('#user_info_id').textContent = p.id);
    currentUser.then(p => document.querySelector('#user_info_firstName').textContent = p.firstName);
    currentUser.then(p => document.querySelector('#user_info_lastName').textContent = p.lastName);
    currentUser.then(p => document.querySelector('#user_info_email').textContent = p.email);
    currentUser.then(p => document.querySelector('#user_info_birthday').textContent = p.birthday);
    currentUser.then(p => document.querySelector('#user_info_phoneNumber').textContent = p.phoneNumber);
    currentUser.then(p => document.querySelector('#user_info_roles').textContent = getRoles(p));
}