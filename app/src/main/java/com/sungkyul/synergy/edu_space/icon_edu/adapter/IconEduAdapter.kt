package com.sungkyul.synergy.edu_space.icon_edu.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.utils.Utils.init
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.edu_space.icon_edu.data.IconInfo
import com.sungkyul.synergy.databinding.FragmentIconListBinding
import com.sungkyul.synergy.edu_space.icon_edu.activity.IconDetailActivity
import com.sungkyul.synergy.utils.GalaxyButton

/** 교육공간 속 아이콘 리사이클러뷰 어뎁터 */
class IconEduAdapter(
    private val context: Context,
    private val iconList: ArrayList<Icon>,
    private val standardSize_X: Int // 추가된 부분
) : RecyclerView.Adapter<IconEduAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentIconListBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(iconList[position])
    }


    override fun getItemCount(): Int {
        return iconList.size
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class CustomViewHolder(private val binding: FragmentIconListBinding) : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.eduButton.post {
                binding.eduButton.clipToRoundRect(27.0f)
            }

            // 교육 버튼의 터치 이벤트 설정
            binding.eduButton.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        (view as GalaxyButton).startTouchDownAnimation(event.x, event.y, 100.0f)
                    }
                    MotionEvent.ACTION_UP -> {
                        (view as GalaxyButton).startTouchUpAnimation()

                        val iconInfo = when (binding.iconTv.text.toString()) {
                            "와이파이" -> IconInfo(
                                R.drawable.ic_word_wifi,
                                "와이파이",
                                "제한된 공간에서 무료로 사용 가능한 인터넷"
                            )
                            "모바일 데이터" -> IconInfo(
                                R.drawable.ic_word_data,
                                "모바일 데이터",
                                "와이파이와 같은 기능으로 제한된 공간 없이 유료로 사용하는 인터넷"
                            )
                            "블루투스" -> IconInfo(
                                R.drawable.ic_word_bluetooth,
                                "블루투스",
                                "디지털 기기에 선 없이 연결할 때 사용되는 기능"
                            )
                            "모드" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "모드",
                                "특정한 작업을 할 수 있는 어떠한 상태"
                            )
                            "비행기 탑승 모드" -> IconInfo(
                                R.drawable.ic_word_airplane,
                                "비행기 탑승 모드",
                                "비행기에 탑승할 때 사용하는 모드로 휴대폰의 통신 기능이 꺼져 전화나 문자, 인터넷 기능을 사용할 수 없는 상채"
                            )
                            "절전 모드" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "절전모드",
                                "블필요한 기능을 제한하여 핸드폰 배터리나 에너지 소모를 줄이는 기능"
                            )
                            "캘린더" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "캘린더",
                                "달력을 보거나 일정을 저장하는 등의 역할을 하는 앱"
                            )
                            "QR코드" -> IconInfo(
                                R.drawable.ic_word_qrcode,
                                "QR코드",
                                "디지털 세상에서 사용되는 고유한 문양으로 이 문양을 카메라로 비추면 문양에 담긴 정보를 볼 수 있는 기능"
                            )
                            "이모티콘" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "이모티콘",
                                "감정이나 느낌을 전달할 때 사용하며, 스티커와 비슷한 그림 기호"
                            )
                            "GPS" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "GPS",
                                "사용자의 위치를 파악해 경로를 파악하는 기능 <hr> 내 위치, 목적지 경로를 찾아주는데 사용"
                            )
                            "캡처" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "캡처",
                                "휴대폰에서 화면에 보이는 문자나 이미지를 저장하는 것"
                            )
                            "볼륨" -> IconInfo(
                                R.drawable.ic_word_volume,
                                "볼륨",
                                "소리의 크기를 조절하는 것"
                            )
                            "셀카" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "셀카",
                                "카메라로 자신의 얼굴을 스스로 찍는 사진"
                            )
                            "메일(이메일)" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "메일(이메일)",
                                "인터넷을 통해 편지를 보내고 받는 것 <hr><hr> 예를 들어, 예전에 우편으로 편지를 보내듯이 이제는 컴퓨터나 휴대폰을 통해 메일을 보내고 받음"
                            )
                            "이메일 주소" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "이메일 주소",
                                "인터넷에서 메일을 주고받을 때 쓰는 주소<hr> '아이디@naver.com'와 같은 형태"
                            )
                            "프로필" -> IconInfo(
                                R.drawable.ic_word_profile,
                                "프로필",
                                "이름이나 사진 등을 넣어 자신을 소개하는 공간"
                            )
                            "상태 메세지" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "상태 메세지",
                                "짧게 자신의 감정이나 상태를 말하는 메세지"
                            )
                            "폴더" -> IconInfo(
                                R.drawable.ic_word_folder,
                                "폴더",
                                "파일들을 정리하고 모아놓는 곳 <hr><hr> 앨범에서 저장된 경로에 따라 사진이 분류되는 것도 폴더로 분류된다고 함"
                            )
                            "맵(지도)" -> IconInfo(
                                R.drawable.ic_word_map,
                                "맵(지도)",
                                "길을 찾거나 위치를 확인할 때 사용하는 지도"
                            )
                            "다운로드(설치)" -> IconInfo(
                                R.drawable.ic_word_download,
                                "다운로드(설치)",
                                "이넡넷으로 자료나 앱을 휴대폰으로 가져오는 것"
                            )
                            "채팅" -> IconInfo(
                                R.drawable.ic_word_chatting,
                                "채팅",
                                "문자를 주고 받으며 소통하는 것"
                            )
                            "키보드" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "키보드",
                                "자판 입력 장치로 휴대폰에서 글자나 기호를 입력할 수 있는 창"
                            )
                            "업데이트" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "업데이트",
                                "기존의 것을 최신으로 수정, 추가, 보안하는 것"
                            )
                            "앱(어플)" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "앱(어플)",
                                "어플리케이션의 줄임말<hr>카메라, 문자와 같이 다영한 작업을 할 수 있음"
                            )
                            "모바일뱅킹" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "모바일 뱅킹",
                                "휴대폰에서 은행 업무를 볼 수 있는 기능"
                            )
                            "업로드" -> IconInfo(
                                R.drawable.ic_word_upload,
                                "업로드",
                                "자료를 전송하는 일<hr>특정 자료를 '올리다'와 같은 맥락으로 사용"
                            )
                            "푸쉬 알림" -> IconInfo(
                                R.drawable.ic_word_alarm,
                                "푸쉬 알림",
                                "앱에서 보내는 실시간 알림"
                            )
                            "음성 메세지" -> IconInfo(
                                R.drawable.ic_wifi_black,
                                "음성 메세지",
                                "통화 기능과는 별개로 짧은 음성 정보를 보낼 수 있는 기능"
                            )
                            // 다른 아이콘 정보 추가...
                            else -> IconInfo(
                                R.drawable.ic_sound_black,
                                "?",
                                "상세 설명이 없습니다."
                            )
                        }
                        val intent = Intent(context, IconDetailActivity::class.java)
                        intent.putExtra("iconInfo", iconInfo)
                        context.startActivity(intent)
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        (view as GalaxyButton).startTouchUpAnimation()
                    }
                }
                true
            }
        }

        fun bind(icon: Icon) {
            binding.apply {
                iconIv.setImageResource(icon.iconImage)
                iconTv.text = icon.iconText

                // 텍스트 크기 설정
                iconTv.textSize = (standardSize_X / 15).toFloat()

                // 아이콘 크기 조정
                // standardSize_X를 기준으로 이미지의 크기를 조정하여 모든 기기에서 동일한 비율로 보이게 함
                val newWidth = standardSize_X / 5.5 // 기기의 가로 크기의 5.5
                val newHeight = standardSize_X / 5.5 // 기기의 가로 크기의 5.5 (세로도 동일하게 설정)

                // 레이아웃 파라미터를 ViewGroup.LayoutParams로 캐스팅
                val params = iconIv.layoutParams as LayoutParams
                params.width = (newWidth * context.resources.displayMetrics.density).toInt()
                params.height = (newHeight * context.resources.displayMetrics.density).toInt()
                iconIv.layoutParams = params


                // 박스 크기 조정
                val displayMetrics: DisplayMetrics = context.resources.displayMetrics
                val boxHeight = standardSize_X / 3.5 // 기기의 가로 크기의 1/10
                val layoutParams = itemView.layoutParams
                layoutParams.height = (boxHeight * displayMetrics.density).toInt()
                itemView.layoutParams = layoutParams

                iconTv.textSize = (standardSize_X / 20).toFloat()
            }
        }
    }
}
