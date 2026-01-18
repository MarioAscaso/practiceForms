const inputName = document.getElementById('registerName');
const inputSurname = document.getElementById('registerSurname');
const inputEmail = document.getElementById('registerEmail');
const inputPass1 = document.getElementById('registerPassword1');
const inputPass2 = document.getElementById('registerPassword2');
const btnRegister = document.querySelector('button');

// 2. FUNCIÓN DE REGISTRO CON BACKEND REAL
async function registerUser() {
    
    // a) Captura de datos
    const name = inputName.value;
    const surname = inputSurname.value;
    const email = inputEmail.value;
    const pass1 = inputPass1.value;
    const pass2 = inputPass2.value;

    // b) Validaciones básicas de Frontend
    if (name === '' || surname === '' || email === '' || pass1 === '') {
        alert('Por favor, rellena todos los campos.');
        return;
    }

    if (pass1 !== pass2) {
        alert('Las contraseñas no coinciden.');
        return;
    }

    // c) Preparar el objeto para enviar (DTO)
    // Fíjate que las claves (name, surname...) coinciden con tu UserRequest en Java
    const newUser = {
        name: name,
        surname: surname,
        email: email,
        password: pass1
    };

    // d) PETICIÓN AL BACKEND (Fetch)
    try {
        const response = await fetch('http://localhost:8081/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Decimos que enviamos JSON
            },
            body: JSON.stringify(newUser) // Convertimos el objeto JS a texto JSON
        });

        // e) Manejar la respuesta del servidor
        if (response.ok) {
            // Si el código es 201 (Created)
            const data = await response.json();
            console.log('Usuario registrado:', data);
            alert('¡Registro exitoso! Ahora puedes iniciar sesión.');
            
            // Redirigir al login
            window.location.href = 'login.html'; 
        } else {
            // Si hay error (ej: 400 Bad Request por email repetido)
            // Intentamos leer el mensaje de error si el back lo envía
            alert('Hubo un error al registrar el usuario.');
            console.error('Error en el registro:', response.status);
        }

    } catch (error) {
        // Error de red (ej: el servidor está apagado)
        console.error('Error de red:', error);
        alert('No se pudo conectar con el servidor.');
    }
}

// 3. EVENTO
btnRegister.addEventListener('click', registerUser);