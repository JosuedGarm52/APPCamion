package com.example.camionapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.camionapi.R
import com.example.camionapi.models.camion.CamionItem

class CamionAdapter(private val xyz: (CamionItem) -> Unit) : ListAdapter<CamionItem, CamionAdapter.ViewHolder>(CamionComparator()){
    class ViewHolder(Camion_item: View, val xyz: (CamionItem) -> Unit) : RecyclerView.ViewHolder(Camion_item) {
        val tvID = Camion_item.findViewById<TextView>(R.id.tvID)
        val tvMatricula = Camion_item.findViewById<TextView>(R.id.tvMatricula)
        val tvColor = Camion_item.findViewById<TextView>(R.id.tvColor)
        val tvYear = Camion_item.findViewById<TextView>(R.id.tvYear)
        val tvConductor = Camion_item.findViewById<TextView>(R.id.tvConductor)
        val tvMarca = Camion_item.findViewById<TextView>(R.id.tvMarca)
        val tvModelo = Camion_item.findViewById<TextView>(R.id.tvModelo)
        val tvDimension = Camion_item.findViewById<TextView>(R.id.tvDimension)
        val tvTipo = Camion_item.findViewById<TextView>(R.id.tvTipo)

        fun bind(Camion: CamionItem){
            tvID.text = "Camion N°: "+Camion.ID.toString()
            tvMatricula.text = "Matricula: "+ Camion.matricula
            tvColor.text = "Color: " + Camion.color
            tvYear.text = "Años: "+ Camion.yearOperative.toString()
            tvConductor.text = "Conductor: " + Camion.conductor
            tvMarca.text  = "Marca:" + Camion.marca
            tvModelo.text = "Modelo: " + Camion.modelo
            tvDimension.text = "Dimensiones: " + Camion.dimension
            tvTipo.text  = "Tipo: " + Camion.tipo

            itemView.setOnClickListener{
                xyz(Camion)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val Camion_item = LayoutInflater.from(parent.context).inflate(R.layout.camion_item,parent,false)
        return ViewHolder(Camion_item,xyz)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val camion = getItem(position)
        holder.bind(camion)
    }
    class CamionComparator : DiffUtil.ItemCallback<CamionItem>() {
        override fun areItemsTheSame(oldItem: CamionItem, newItem: CamionItem): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CamionItem, newItem: CamionItem): Boolean {
            return oldItem.ID == newItem.ID
        }
    }
}