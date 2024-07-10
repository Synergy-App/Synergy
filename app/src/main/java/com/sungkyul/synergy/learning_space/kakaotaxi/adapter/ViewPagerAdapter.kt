package com.sungkyul.synergy.learning_space.kakaotaxi.adapter

import android.view.LayoutInflater
import android.widget.ImageView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R

class ViewPagerAdapter(private val paymentList: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PagerViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = paymentList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(paymentList[position])
    }

    inner class PagerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.card_list_item, parent, false)) {

        private val card: ImageView = itemView.findViewById(R.id.imageView_card1)

        fun bind(imageResId: Int) {
            card.setImageResource(imageResId)
        }
    }
}
