package com.sungkyul.synergy.training_space.kakao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.learning_space.kakaotalk.data.ChatMessage
import com.sungkyul.synergy.training_space.kakao.data.ChatMessage2
class Chat2Adapter(private val chatList: MutableList<ChatMessage2>) : RecyclerView.Adapter<Chat2Adapter.ChatViewHolder>() {

    // ViewHolder 설정
    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatMessageTextView: TextView = itemView.findViewById(R.id.chatMessageTextView)
        val chatTimestampTextView: TextView = itemView.findViewById(R.id.chatTimestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatList[position]
        holder.chatMessageTextView.text = chatMessage.message
        holder.chatTimestampTextView.text = chatMessage.timestamp
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    // 새로운 메시지를 추가하는 메서드
    fun addMessage(message: ChatMessage2) {
        chatList.add(message)
        notifyItemInserted(chatList.size - 1)  // 데이터 변경 알림
    }
}
