package com.example.bookhub.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R
import org.w3c.dom.Text
import java.util.ArrayList

class DashboardRecyclerAdaptor(val context : Context, val itemList :ArrayList<String>): RecyclerView.Adapter<DashboardRecyclerAdaptor.DashboardViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardRecyclerAdaptor.DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_item,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(
        holder: DashboardRecyclerAdaptor.DashboardViewHolder,
        position: Int
    ) {
        val text =  itemList[position]
        holder.textView.text = text
    }
    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.rowItem)

    }
}