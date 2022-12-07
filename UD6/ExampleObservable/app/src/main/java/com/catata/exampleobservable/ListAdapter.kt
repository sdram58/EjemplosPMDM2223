package com.catata.exampleobservable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var itemList = emptyList<String>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val tvNumber : TextView = itemView.findViewById(R.id.tvNumber)
        fun bind(item:String){
            tvNumber.text = item
        }

    }
}