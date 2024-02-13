package com.example.erw.Luchador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.erw.MenuActivity
import com.example.erw.R
import com.example.erw.databinding.ActivityEliminarLuchadorBinding
import com.google.firebase.firestore.FirebaseFirestore

class EliminarLuchadorActivity : MenuActivity() {

    private lateinit var binding: ActivityEliminarLuchadorBinding
    private val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad y establecer como contenido principal
        binding = ActivityEliminarLuchadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del OnClickListener para el botón de eliminar
        binding.Eliminar.setOnClickListener {
            // Obtener el DNI del luchador a eliminar desde el campo de texto
            val dni = binding.editTextdni.text.toString()

            if (dni.isNotEmpty()) {
                // Mostrar un cuadro de diálogo de confirmación antes de la eliminación
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmar Eliminación")
                builder.setMessage("¿Estás seguro de que deseas eliminar este luchador?")
                builder.setPositiveButton("Eliminar") { _, _ ->
                    // Llamada a la función para eliminar al luchador
                    eliminarLuchador(dni)
                }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                // Mostrar un mensaje si el campo de DNI está vacío
                Toast.makeText(this, "Introduce el DNI del luchador a eliminar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para eliminar al luchador de la base de datos
    private fun eliminarLuchador(dni: String) {
        // Eliminar el luchador de la base de datos
        database.collection("Luchador").document(dni)
            .delete()
            .addOnSuccessListener {
                // Mostrar mensaje de éxito y cerrar la actividad después de eliminar exitosamente
                Toast.makeText(this, "Luchador eliminado exitosamente", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                // Mostrar mensaje de error en caso de fallo durante la eliminación
                Toast.makeText(this, "Error al eliminar el luchador: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}