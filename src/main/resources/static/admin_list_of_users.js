async function getUserList() {
    const url = 'http://localhost:8080/rest/admin/user_list';
    let response = await fetch(url);
    if (response.ok) {
        return await response.json();
    } else {
        alert('Http connection error: ' + response.status);
    }
}

function loadUsersTable(userList) {
    const table = document.getElementById("admin_panel_user_list_body");

    table.innerHTML = "";

    console.log(userList)

    userList.then(userList => {
        for (let user of userList) {
            const roles = getRoles(user);
            let row = table.insertRow();

            row.innerHTML = `
                <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.birthday}</td>
                <td>${user.phoneNumber}</td>
                <td>${roles}</td>
                <td>
                    <button id="user_list_button_update" class="btn btn-info btn-sg" type="submit" data-bs-toggle="modal"
                        data-bs-userId=${user.id}
                        data-bs-firstName=${user.firstName}
                        data-bs-lastName=${user.lastName}
                        data-bs-email=${user.email}
                        data-bs-birthday=${user.birthday}
                        data-bs-phoneNumber=${user.phoneNumber}
                        data-bs-roles=${roles}
                        data-bs-target='#modal-update-user'>Edit
                    </button>
                </td>
                <td>
                    <button id="user_list_button_delete" class="btn btn-danger btn-sg" type="submit" data-bs-toggle="modal"
                        data-bs-userId=${user.id}
                        data-bs-firstName=${user.firstName}
                        data-bs-lastName=${user.lastName}
                        data-bs-email=${user.email}
                        data-bs-birthday=${user.birthday}
                        data-bs-phoneNumber=${user.phoneNumber}
                        data-bs-roles=${roles}
                        data-bs-target="#modal-delete-user">Delete
                    </button>
                </td>
                </tr>
            `
        }
    });
}