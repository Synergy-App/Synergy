package com.sungkyul.synergy.training_space.kakao.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.learning_space.kakaotalk.activity.KakaoProfileDetailActivity
import com.sungkyul.synergy.learning_space.kakaotalk.data.profileItem
import com.sungkyul.synergy.training_space.kakao.PracticeKakao2Activity

/** 카카오톡 프로필 어뎁터와 뷰홀더 */
class Profile2Adapter(private val context: Context) :
    RecyclerView.Adapter<Profile2Adapter.ViewHolder>() {

    var datas = mutableListOf<profileItem>() // 데이터 리스트 초기화

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.kakao_friends_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val profile_name: TextView = itemView.findViewById(R.id.profile_name)
        private val profile_message: TextView = itemView.findViewById(R.id.profile_message)
        private val profile_iv: ImageView = itemView.findViewById(R.id.profile_iv)
        private val friend_detail_LL: LinearLayout = itemView.findViewById(R.id.friend_detail_LL)

        init {
            friend_detail_LL.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedProfile = datas[position]

                    if (selectedProfile.name != "임영웅") {
                        return@setOnClickListener
                    }

                    // 클릭할 수 있는 항목에 대한 처리
                    val intent = Intent(context, PracticeKakao2Activity::class.java)
                    intent.putExtra(
                        "profile_detail",
                        selectedProfile
                    ) // 데이터를 전달합니다. profileItem이 Parcelable이어야 합니다.
                    context.startActivity(intent)
                }
            }
        }

        fun bind(item: profileItem) {
            profile_name.text = item.name
            profile_message.text = item.message
            profile_iv.setImageResource(item.image)
        }
    }
}
