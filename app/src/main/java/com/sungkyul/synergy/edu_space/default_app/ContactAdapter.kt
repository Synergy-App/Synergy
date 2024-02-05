package com.sungkyul.synergy.edu_space.default_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

data class ContactData(
    val profile: Int,
    val name: String
)

class ContactAdapter(private val dataSet: ArrayList<ContactData>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profile = view.findViewById<ImageView>(R.id.profile)
        val name = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_contact, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.profile.setImageResource(dataSet[position].profile)
        viewHolder.name.text = dataSet[position].name
    }

    override fun getItemCount() = dataSet.size
}
