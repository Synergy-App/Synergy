package com.sungkyul.synergy.edu_space.naver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

data class NaverAutocompleteData(
    val searchQuery: String
)

class NaverAutocompleteAdapter(private val dataSet: ArrayList<NaverAutocompleteData>): RecyclerView.Adapter<NaverAutocompleteAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchQuery: TextView = view.findViewById(R.id.search_query)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_naver_autocomplete, viewGroup, false)
        
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.searchQuery.text = dataSet[position].searchQuery
    }

    override fun getItemCount() = dataSet.size
}
