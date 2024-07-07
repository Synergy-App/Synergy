package com.sungkyul.synergy.edu_space.icon_edu.activity

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungkyul.synergy.R
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GALAXY_NOTE9
import com.sungkyul.synergy.com.sungkyul.synergy.utils.GalaxyNote9
import com.sungkyul.synergy.edu_space.icon_edu.adapter.IconEduAdapter
import com.sungkyul.synergy.edu_space.icon_edu.data.Icon
import com.sungkyul.synergy.databinding.ActivityIconEduBinding
import com.sungkyul.synergy.databinding.ActivityMoveEduBinding
import com.sungkyul.synergy.utils.DisplayUtils

/** 교육공간 속 아이콘 설명 액티비티 */
class IconEduFragment : Fragment() {
    private var _binding: ActivityIconEduBinding? = null
    private val binding get() = _binding!!
    private lateinit var iconAdapter: IconEduAdapter // IconEduAdapter에 해당하는 부분입니다.
    private var standardSize_X = 0
    private var standardSize_Y = 0
    private var density = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityIconEduBinding.inflate(inflater, container, false)


        // 화면의 기준 사이즈를 계산
        getStandardSize()

        // 텍스트 크기를 기준 사이즈를 이용해 설정
        binding.iconeduTool.textSize = (standardSize_X / 12).toFloat()
        binding.iconeduTool2.textSize = (standardSize_X / 20).toFloat()
        binding.searchEditText.textSize = (standardSize_X / 20).toFloat()

        val recyclerView: RecyclerView = binding.iconRv // XML에서 정의한 RecyclerView의 ID를 가져옵니다.

        // RecyclerView에 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // LinearLayoutManager 또는 필요한 레이아웃 매니저를 사용합니다.

        val iconSearchDict = listOf(
            Triple(R.drawable.ic_word_wifi, "와이파이", "제한된 공간에서 무료로 사용 가능한 인터넷"),
            Triple(R.drawable.ic_word_data, "모바일 데이터", "와이파이와 같은 기능으로 제한된 공간 없이 유료로 사용하는 인터넷"),
            Triple(R.drawable.ic_word_bluetooth, "블루투스", "디지털 기기에 선 없이 연결할 때 사용되는 기능"),
            Triple(R.drawable.sebook_no_icon, "모드", "특정한 작업을 할 수 있는 어떠한 상태"),
            Triple(R.drawable.ic_word_airplane, "비행기 탑승 모드", "비행기에 탑승할 때 사용하는 모드로 휴대폰의 통신 기능이 꺼져 전화나 문자, 인터넷 기능을 사용할 수 없는 상태"),
            Triple(R.drawable.ic_word_subtract, "절전 모드", "불필요한 기능을 제한하여 핸드폰 배터리나 에너지 소모를 줄이는 기능"),
            Triple(R.drawable.ic_word_calender, "캘린더", "달력을 보거나 일정을 저장하는 등의 역할을 하는 앱"),
            Triple(R.drawable.ic_word_qrcode, "QR코드", "디지털 세상에서 사용되는 고유한 문양으로 이 문양을 카메라로 비추면 문양에 담긴 정보를 볼 수 있는 기능"),
            Triple(R.drawable.ic_word_emoji, "이모티콘", "감정이나 느낌을 전달할 때 사용하며, 스티커와 비슷한 그림 기호"),
            Triple(R.drawable.ic_word_location, "GPS", "사용자의 위치를 파악해 경로를 파악하는 기능\n\n 내 위치, 목적지 경로를 찾아주는 데 사용"),
            Triple(R.drawable.sebook_no_icon, "캡처", "휴대폰에서 화면에 보이는 문자나 이미지를 저장하는 것"),
            Triple(R.drawable.ic_sound_black, "볼륨", "소리의 크기를 조절하는 것"),
            Triple(R.drawable.sebook_no_icon, "셀카", "카메라로 자신의 얼굴을 스스로 찍는 사진"),
            Triple(R.drawable.ic_word_email, "메일(이메일)", "인터넷을 통해 편지를 보내고 받는 것\n\n예를 들어, 예전에 우편으로 편지를 보내듯이 이제는 컴퓨터나 휴대폰을 통해 메일을 보내고 받음"),
            Triple(R.drawable.sebook_no_icon, "이메일 주소", "인터넷에서 메일을 주고받을 때 쓰는 주소\n\n'아이디@naver.com'와 같은 형태"),
            Triple(R.drawable.ic_word_profile, "프로필", "이름이나 사진 등을 넣어 자신을 소개하는 공간"),
            Triple(R.drawable.sebook_no_icon, "상태 메세지", "짧게 자신의 감정이나 상태를 말하는 메세지"),
            Triple(R.drawable.ic_word_folder, "폴더", "파일들을 정리하고 모아놓는 곳\n\n앨범에서 저장된 경로에 따라 사진이 분류되는 것도 폴더로 분류된다고 함"),
            Triple(R.drawable.ic_word_map, "맵(지도)", "길을 찾거나 위치를 확인할 때 사용하는 지도"),
            Triple(R.drawable.ic_word_download, "다운로드(설치)", "인터넷으로 자료나 앱을 휴대폰으로 가져오는 것"),
            Triple(R.drawable.ic_word_chatting, "채팅", "문자를 주고 받으며 소통하는 것"),
            Triple(R.drawable.ic_word_keyboard, "키보드", "자판 입력 장치로 휴대폰에서 글자나 기호를 입력할 수 있는 창"),
            Triple(R.drawable.ic_word_update, "업데이트", "기존의 것을 최신으로 수정, 추가, 보안하는 것"),
            Triple(R.drawable.sebook_no_icon, "앱(어플)", "어플리케이션의 줄임말\n\n카메라, 문자와 같이 다양한 작업을 할 수 있음"),
            Triple(R.drawable.sebook_no_icon, "모바일뱅킹", "휴대폰에서 은행 업무를 볼 수 있는 기능"),
            Triple(R.drawable.ic_word_upload, "업로드", "자료를 전송하는 일\n\n특정 자료를 '올리다'와 같은 맥락으로 사용"),
            Triple(R.drawable.ic_word_alarm, "푸쉬 알림", "앱에서 보내는 실시간 알림"),
            Triple(R.drawable.ic_word_voicemessage, "음성 메세지", "통화 기능과는 별개로 짧은 음성 정보를 보낼 수 있는 기능")
        )

        val iconList = ArrayList<Icon>()
        for (i in iconSearchDict) {
            iconList.add(Icon(i.first, i.second, i.third)) // iconDescription 추가
        }

        iconAdapter = IconEduAdapter(requireContext(), iconList,  standardSize_X) // 여기에 생성한 아이템 리스트를 넣어줍니다.
        recyclerView.adapter = iconAdapter

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapter = recyclerView.adapter as IconEduAdapter

                // 모든 아이템들을 삭제한다.
                adapter.notifyItemRangeRemoved(0, iconList.size)

                // 자동 완성
                iconList.clear()
                if (s.toString().isNotEmpty()) {
                    for (i in iconSearchDict.filter { it.second.contains(s.toString()) }) {
                        iconList.add(Icon(i.first, i.second, i.third)) // iconDescription 추가
                    }
                } else {
                    // 검색 창이 비어 있으면, 모든 아이템들을 채운다.
                    for (i in iconSearchDict) {
                        iconList.add(Icon(i.first, i.second, i.third)) // iconDescription 추가
                    }
                }

                // 아이템들을 추가한다.
                adapter.notifyItemRangeInserted(0, iconList.size)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        if(Build.MODEL == GALAXY_NOTE9) {
            GalaxyNote9.setTitleSize(binding.iconeduTool)
            GalaxyNote9.setSubtitleSize(binding.iconeduTool2)
            GalaxyNote9.setSubtitleSize(binding.searchEditText)
            binding.iconGreen.layoutParams.apply {
                binding.iconGreen.layoutParams.height = DisplayUtils.dpToPx(requireContext(), 250.0f).toInt()
            }
        }

        return binding.root
    }

    // 디스플레이 사이즈를 반환하는 메서드
    private fun getScreenSize(): Point {
        val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    // 기기의 해상도와 밀도를 기준으로 기준 사이즈를 계산하는 메서드
    private fun getStandardSize() {
        val screenSize = getScreenSize()
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        density = displayMetrics.density

        standardSize_X = (screenSize.x / density).toInt()
        standardSize_Y = (screenSize.y / density).toInt()
    }
}
