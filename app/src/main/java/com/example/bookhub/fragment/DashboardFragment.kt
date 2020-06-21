package com.example.bookhub.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R
import com.example.bookhub.adaptor.DashboardRecyclerAdaptor

class DashboardFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    val booklist = arrayListOf(
        "P.S. I Love You",
        "The Great Gatsby",
        "Anna Karenina",
        "Madame Bovary",
        "War and Peace",
        "Lolita",
        "Middlemarch",
        "The Adventures of Huckleberry Finn",
        "Moby-dick",
        "Lords of the Rings"

    )

    lateinit var recyclerAdapter: DashboardRecyclerAdaptor
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = view.findViewById(R.id.recycle)
        layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = DashboardRecyclerAdaptor(activity as Context, booklist)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
         recyclerView.addItemDecoration(
             DividerItemDecoration(
                 recyclerView.context,
                 (layoutManager as LinearLayoutManager).orientation

             )
         )
        return view
    }

}
