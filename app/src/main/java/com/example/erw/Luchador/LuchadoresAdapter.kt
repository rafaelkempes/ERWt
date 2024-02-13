package com.example.erw.Luchador

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erw.BaseDatos.Luchador
import com.example.erw.R
import com.example.erw.databinding.LuchadorBinding

class LuchadoresAdapter(private val luchadorList: List<Luchador>) :
    RecyclerView.Adapter<LuchadoresAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.Nombre)
        val gimickTextView: TextView = itemView.findViewById(R.id.Gimick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.luchador, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val luchador = luchadorList[position]
        holder.nombreTextView.text = luchador.nombreArtistico
        holder.gimickTextView.text = luchador.gimmick
    }

    override fun getItemCount(): Int {
        return luchadorList.size
    }
}
