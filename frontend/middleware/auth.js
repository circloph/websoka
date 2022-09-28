export default function () {
    if (!localStorage.getItem('token')) {
      window.location.href = 'login';
    }
  }
  