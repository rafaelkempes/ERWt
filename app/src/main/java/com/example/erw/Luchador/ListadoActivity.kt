package com.example.erw.Luchador

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erw.BaseDatos.Luchador

import com.example.erw.MenuActivity
import com.example.erw.R
import com.example.erw.databinding.ActivityListadoBinding
import com.google.firebase.FirebaseApp

import com.google.firebase.firestore.FirebaseFirestore


class ListadoActivity : MenuActivity() {

    private lateinit var binding: ActivityListadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad y establecer como contenido principal
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Llama al método para obtener y mostrar los datos desde Firebase
        mostrarDatosDesdeFirebase()
    }

    private fun mostrarDatosDesdeFirebase() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Luchador")

        // Realiza una consulta a Firestore para obtener datos de luchadores
        collectionRef.get().addOnSuccessListener { result ->
            val luchadorList = mutableListOf<Luchador>()

            // Itera sobre los documentos obtenidos y crea objetos Luchador
            for (document in result) {
                val nombreArtistico = document.getString("NombreArtistico") ?: ""
                val gimmick = document.getString("Gimmick") ?: ""
                val foto = document.getString("foto") ?: ""
                val activo = document.getBoolean("activo")?:""
                val censurado = document.getBoolean("censurado")?:""
                val altura = document.getLong("altura")?:""
                val peso = document.getDouble("peso")?:""

                // Crea un objeto Luchador y asigna los valores obtenidos
                val luchador = Luchador(
                    dni = "",
                    nombre = "",
                    nombreArtistico = nombreArtistico,
                    activo = activo as Boolean,
                    censurado = censurado as Boolean,
                    edad = 0,
                    licencia = "",
                    peso = peso as Double,
                    altura = altura as Double,
                    habilidades = "",
                    entrada = "",
                    musica = "",
                    gimmick = gimmick,
                    titulos = "",
                    foto = foto
                )

                luchadorList.add(luchador)
            }

            // Configura el adaptador y layout manager, y asigna a la RecyclerView
            configurarRecyclerView(luchadorList)
        }.addOnFailureListener { e ->
            // Maneja posibles errores en la consulta a Firestore
            Toast.makeText(this, "Error al obtener datos: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurarRecyclerView(luchadorList: MutableList<Luchador>) {
        try {
            // Configura el adaptador con la lista de luchadores
            val adapter = LuchadoresAdapter(luchadorList)

            // Configura el layout manager para la RecyclerView
            binding.recycler.layoutManager = LinearLayoutManager(this)

            // Asigna el adaptador a la RecyclerView
            binding.recycler.adapter = adapter
        } catch (e: Exception) {
            // Maneja posibles errores durante la configuración de la RecyclerView
            e.printStackTrace()
        }
    }
}
