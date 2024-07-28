const ModalDelete = document.getElementById('modal-delete-user')
ModalDelete.addEventListener('show.bs.modal', function (event) {

  const userData = event.relatedTarget

  document.querySelector('#form-control-delete-id').value = userData.getAttribute('data-bs-userId');
  document.querySelector('#form-control-delete-firstname').value = userData.getAttribute('data-bs-firstName');
  document.querySelector('#form-control-delete-lastname').value = userData.getAttribute('data-bs-lastName');
  document.querySelector('#form-control-delete-email').value = userData.getAttribute('data-bs-email');
  document.querySelector('#form-control-delete-birthday').value = userData.getAttribute('data-bs-birthday');
  document.querySelector('#form-control-delete-phone').value = userData.getAttribute('data-bs-phoneNumber');
  document.querySelector('#form-control-delete-role-selection').value = userData.getAttribute('data-bs-roles');
})


const pressDeleteButton = document.getElementById('modal-delete-form-button-delete')
pressDeleteButton.addEventListener('click', function (event) {

  event.preventDefault();
  const id = document.querySelector('#form-control-delete-id').value
  const url = 'http://localhost:8080/rest/delete_user/' + id;

  fetch(url, {
    method: "DELETE",
  }).then(() => loadUsersTable(getUserList()))

  document.getElementById('modal-delete-form-button-close').click();
})

