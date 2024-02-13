package com.example.erw.Luchador
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.erw.BaseDatos.Luchador
import com.example.erw.databinding.ActivityModificarLuchadorBinding
import com.google.firebase.firestore.FirebaseFirestore

class ModificarLuchadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModificarLuchadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad y establecer como contenido principal
        binding = ActivityModificarLuchadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Otros códigos de inicialización aquí...

        // Configurar el OnClickListener para el botón de buscar
        binding.buttonBuscar.setOnClickListener {
            val dni = binding.editTextDni.text.toString()

            if (dni.isNotEmpty()) {
                // Llama al método para mostrar datos desde Firebase
                mostrarDatosDesdeFirebase(dni)
            } else {
                Toast.makeText(this, "Introduce el DNI del luchador a buscar", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Método para obtener y mostrar datos del luchador desde Firebase
    private fun mostrarDatosDesdeFirebase(dni: String) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Luchador")

        // Realiza una consulta a Firestore para obtener datos de luchadores
        collectionRef.get().addOnSuccessListener { result ->
            val luchadorList = mutableListOf<Luchador>()

            // Itera sobre los documentos obtenidos y crea objetos Luchador
            for (document in result) {
                // Obtener valores de Firestore y asignar a variables locales
                val nombre = document.getString("Nombre") ?: ""
                val nombreArtistico = document.getString("NombreArtistico") ?: ""
                val activo = document.getBoolean("activo") ?: false
                val censurado = document.getBoolean("censurado") ?: false
                val edad = document.getLong("Edad")?.toInt() ?: 0
                val licencia = document.getString("Licencia") ?: ""
                val peso = document.getDouble("peso") ?: 0.0
                val altura = document.getDouble("altura") ?: 0.0
                val habilidades = document.getString("habilidades") ?: ""
                val entrada = document.getString("entrada") ?: ""
                val musica = document.getString("musica") ?: ""
                val gimmick = document.getString("Gimmick") ?: ""
                val titulos = document.getString("titulos") ?: ""
                val foto = document.getString("foto") ?: ""

                // Crea un objeto Luchador y asigna los valores obtenidos
                val luchador = Luchador(
                    dni = "", // Asegúrate de obtener el DNI del documento si es necesario
                    nombre = nombre,
                    nombreArtistico = nombreArtistico,
                    activo = activo,
                    censurado = censurado,
                    edad = edad,
                    licencia = licencia,
                    peso = peso,
                    altura = altura,
                    habilidades = habilidades,
                    entrada = entrada,
                    musica = musica,
                    gimmick = gimmick,
                    titulos = titulos,
                    foto = foto
                )

                luchadorList.add(luchador)
            }

            // Llama a llenarCampos con el primer luchador de la lista (si existe)
            if (luchadorList.isNotEmpty()) {
                llenarCampos(luchadorList[0])
            }
        }
    }

    // Método para llenar los campos de la interfaz con los datos del luchador
    private fun llenarCampos(luchador: Luchador) {
        binding.apply {
            if (luchador != null) {
                editTextNombre.setText(luchador.nombre)
                editTextNombreArtistico.setText(luchador.nombreArtistico)
                editTextEdad.setText(luchador.edad?.toString())
                editTextLicencia.setText(luchador.licencia)
                editTextPeso.setText(luchador.peso?.toString())
                editTextAltura.setText(luchador.altura?.toString())
                editTextHabilidades.setText(luchador.habilidades)
                editTextEntrada.setText(luchador.entrada)
                editTextMusica.setText(luchador.musica)
                editTextGimmick.setText(luchador.gimmick)
                editTextTitulos.setText(luchador.titulos)
                checkBoxActivo.isChecked = luchador.activo
                checkBoxCensurado.isChecked = luchador.censurado
            }
        }
    }

    // Método para habilitar la edición de campos
    private fun habilitarEdicionCampos() {
        binding.apply {
            editTextNombre.isEnabled = true
            editTextNombreArtistico.isEnabled = true
            editTextEdad.isEnabled = true
            editTextLicencia.isEnabled = true
            editTextPeso.isEnabled = true
            editTextAltura.isEnabled = true
            editTextHabilidades.isEnabled = true
            editTextEntrada.isEnabled = true
            editTextMusica.isEnabled = true
            editTextGimmick.isEnabled = true
            editTextTitulos.isEnabled = true
            checkBoxActivo.isEnabled = true
            checkBoxCensurado.isEnabled = true
        }
    }
}
