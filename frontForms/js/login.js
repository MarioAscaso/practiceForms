// 1. SELECCIÓN DE ELEMENTOS
// Usamos los IDs que definiste en login.html
const inputEmail = document.getElementById('loginEmail');
const inputPassword = document.getElementById('loginPassword');
const btnLogin = document.querySelector('button');

// 2. FUNCIÓN DE LOGIN
async function loginUser() {
    
    const email = inputEmail.value;
    const password = inputPassword.value;

    // Validación básica: campos vacíos
    if (email === '' || password === '') {
        alert('Por favor, introduce tu correo y contraseña.');
        return;
    }

    // Objeto a enviar (DTO LoginRequest)
    const credentials = {
        email: email,
        password: password
    };

    try {
        // Petición al Backend
        const response = await fetch('http://localhost:8081/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        });

        if (response.ok) {
            // LOGIN EXITOSO (Código 200)
            const user = await response.json();
            
            console.log('Usuario logueado:', user);
            
            // GUADAR SESIÓN (IMPORTANTE)
            // Guardamos el usuario en el navegador para saber quién es en las otras páginas
            localStorage.setItem('user', JSON.stringify(user));

            alert('¡Bienvenido de nuevo, ' + user.name + '!');
            
            // REDIRECCIÓN
            // Si es admin iría a adminPage, pero de momento todos van a userPage
            window.location.href = 'userPage.html';

        } else {
            // LOGIN FALLIDO (401, 404, 500...)
            // Puede ser "Usuario no encontrado" o "Contraseña incorrecta"
            // Intentamos leer el mensaje de error del backend si existe
            try {
                const errorData = await response.json();
                alert('Error: ' + (errorData.message || 'Credenciales incorrectas'));
            } catch (e) {
                // Si el backend no devuelve JSON en el error
                alert('Usuario o contraseña incorrectos.');
            }
        }

    } catch (error) {
        console.error('Error de red:', error);
        alert('No se pudo conectar con el servidor.');
    }
}

// 3. EVENTO
btnLogin.addEventListener('click', loginUser);