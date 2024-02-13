package com.example.erw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.erw.Luchador.Annadir_LuchadorActivity
import com.example.erw.Luchador.EliminarLuchadorActivity
import com.example.erw.Luchador.ListadoActivity
import com.example.erw.Luchador.ModificarLuchadorActivity

open class MenuActivity : AppCompatActivity() {
    companion object {
        var actividadActual = 0;
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        //relacionamos la clase con el layout del menú que hennos creado
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        //desactivar la opción de la actividad en la que estamos
        for (i in 0 until  menu.size()){
            if (i == actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i).isEnabled = true
        }

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.lista_luchador ->
            {
                val intent = Intent(this, ListadoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                actividadActual = 0;
                startActivity(intent)
                true
            }

            R.id.annadir_luchador -> {
                val intent = Intent(this, Annadir_LuchadorActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                actividadActual = 1;
                startActivity(intent)
                true
            }
            R.id.EliminarLuchador ->{
                val intent = Intent(this, EliminarLuchadorActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                actividadActual = 2;
                startActivity(intent)
                true
            }
            R.id.ModificarLuchador->{
                val intent = Intent(this, ModificarLuchadorActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                actividadActual = 3;
                startActivity(intent)
                true
            }
            R.id.cerrar_sesion->{
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                actividadActual = 4;
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}