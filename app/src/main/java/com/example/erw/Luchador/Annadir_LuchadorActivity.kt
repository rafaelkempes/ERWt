package com.example.erw.Luchador


import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.erw.MenuActivity
import com.example.erw.databinding.ActivityAnnadirLuchadorBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID


class Annadir_LuchadorActivity : MenuActivity() {

    lateinit var binding: ActivityAnnadirLuchadorBinding

    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private val IMAGE_PICK_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnadirLuchadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialización de Firebase Storage
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        // Configuración del click listener para el ImageButton
        binding.imageButtonFoto.setOnClickListener {
            // Crear un Intent para abrir la galería de imágenes
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            // Verificar si hay una aplicación para manejar el intent
            if (intent.resolveActivity(packageManager) != null) {
                // Iniciar la actividad de selección de imágenes
                startActivityForResult(intent, IMAGE_PICK_REQUEST_CODE)
            } else {
                // Si no hay una aplicación para manejar el intent, mostrar un mensaje o manejar según sea necesario
                Toast.makeText(this, "No hay aplicación para manejar la selección de imágenes", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuración del click listener para el botón de confirmar
        binding.buttonConfirmar.setOnClickListener {
            try {
                // Obtener datos de los campos de entrada
                val dni = binding.editTextDni.text.toString()
                val nombreArtistico = binding.editTextNombreArtistico.text.toString()
                val nombre = binding.editTextNombre.text.toString()
                val edad = binding.editTextEdad.text.toString()
                val licencia = binding.editTextLicencia.text.toString()
                val peso = binding.editTextPeso.text.toString()
                val altura = binding.editTextAltura.text.toString()
                val habilidades = binding.editTextHabilidades.text.toString()
                val entrada = binding.editTextEntrada.text.toString()
                val musica = binding.editTextMusica.text.toString()
                val gimick = binding.editTextGimmick.text.toString()
                val titulos = binding.editTextTitulos.text.toString()

                val activo = binding.checkBoxActivo.isChecked
                val censurado = binding.checkBoxCensurado.isChecked

                // Obtener la URL de la imagen desde el ImageButton
                val foto = ""

                // Validar campos obligatorios
                if (nombre.isEmpty() || nombreArtistico.isEmpty() || edad.isEmpty() || licencia.isEmpty() || peso.isEmpty() || altura.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Introduce los campos necesarios",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // Acceder a la instancia de Firebase Firestore
                    val database = FirebaseFirestore.getInstance()

                    // Guardar información del luchador en la base de datos
                    database.collection("Luchador").document(dni).set(
                        mapOf(
                            "Nombre" to nombre,
                            "NombreArtistico" to nombreArtistico,
                            "activo" to activo,
                            "censurado" to censurado,
                            "Edad" to edad.toInt(),
                            "Licencia" to licencia,
                            "peso" to peso.toDouble(),
                            "altura" to altura.toDouble(),
                            "habilidades" to habilidades,
                            "entrada" to entrada,
                            "musica" to musica,
                            "Gimmick" to gimick,
                            "titulos" to titulos,
                            "foto" to foto,
                        )
                    ).addOnSuccessListener {
                        // Mostrar mensaje de éxito
                        Toast.makeText(
                            this,
                            "Información del luchador guardada en la base de datos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener { e ->
                        // Mostrar mensaje de error en caso de fallo
                        Toast.makeText(
                            this,
                            "Error al guardar la información del luchador: ${e.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                // Manejar excepciones generales
                e.printStackTrace()
                Toast.makeText(this, "Error desconocido: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Método para manejar el resultado de la selección de imágenes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Verificar si el resultado corresponde a la solicitud de selección de imágenes
        if (requestCode == IMAGE_PICK_REQUEST_CODE && resultCode == RESULT_OK) {
            // La imagen ha sido seleccionada exitosamente
            val selectedImageUri = data?.data

            // Aquí puedes manejar la URI de la imagen, como mostrarla en una vista de imagen o cargarla en Firebase Storage
            // Ejemplo: binding.imageViewFoto.setImageURI(selectedImageUri)
        }
    }
}
