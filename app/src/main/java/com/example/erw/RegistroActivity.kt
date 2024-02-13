package com.example.erw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.erw.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad y establecer como contenido principal
        val binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Configurar el OnClickListener para el botón de crear cuenta
        binding.BCrear.setOnClickListener {
            // Obtener los datos ingresados por el usuario
            val nombre = binding.ETNombre.text.toString()
            val email = binding.ETgmail.text.toString()
            val password = binding.ETpasword.text.toString()

            // Verificar si los campos están completos
            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Intentar crear una cuenta con el correo y la contraseña proporcionados
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registro exitoso
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                            // Redirigir a la actividad principal después de un registro exitoso
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()  // Cerrar la actividad actual para evitar que el usuario retroceda
                        } else {
                            // Fallo en el registro, muestra un mensaje de error
                            Toast.makeText(this, "Error en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Mostrar mensaje si algún campo está vacío
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
