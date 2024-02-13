package com.example.erw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.erw.Luchador.ListadoActivity
import com.example.erw.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //depedencia
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //istancia con el farebase
        auth = FirebaseAuth.getInstance();

        binding.BInicio.setOnClickListener{
            val email = binding.ETEmail.text.toString()
            val password = binding.ETContrasenna.text.toString()
            if (email.isNullOrEmpty() || password.isNullOrEmpty()){
                Toast.makeText(this,"Por favor, complete todos los campos",Toast.LENGTH_LONG).show()
            }
            else {
                // Verificar si el usuario ya está autenticado
                if (auth.currentUser != null) {
                    startActivity(Intent(this, ListadoActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "No esta usted registrado", Toast.LENGTH_LONG).show()
                }
            }

        }
        binding.BRegistarse.setOnClickListener{
            startActivity(Intent(this,RegistroActivity::class.java))
        }
    }


}