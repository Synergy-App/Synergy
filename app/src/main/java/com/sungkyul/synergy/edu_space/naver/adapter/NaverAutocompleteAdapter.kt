package com.sungkyul.synergy.edu_space.naver.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.naver.activity.NaverSearchResultActivity

data class NaverAutocompleteData(
    val searchQuery: String
)

class NaverAutocompleteAdapter(private val dataSet: ArrayList<NaverAutocompleteData>, private val activity: AppCompatActivity): RecyclerView.Adapter<NaverAutocompleteAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchQuery: TextView = view.findViewById(R.id.search_query)

        init {
            view.rootView.setOnClickListener {
                val intent = Intent(activity, NaverSearchResultActivity::class.java)
                activity.startActivity(intent)
            }
        }
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

    /*fun changeItems(items: List<NaverAutocompleteData>) {
        dataSet.clear()
        dataSet.addAll(items)
        notifyDataSetChanged()
    }*/
}
