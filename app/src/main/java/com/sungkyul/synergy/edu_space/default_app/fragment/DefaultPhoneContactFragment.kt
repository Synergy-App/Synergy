import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentDefaultPhoneContactBinding
import com.sungkyul.synergy.edu_space.default_app.adapter.ContactAdapter
import com.sungkyul.synergy.edu_space.default_app.adapter.ContactData

// 연락처
class DefaultPhoneContactFragment : Fragment() {
    private lateinit var binding: FragmentDefaultPhoneContactBinding
    private lateinit var contactAdapter: ContactAdapter
    private val contactArray = ArrayList<ContactData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultPhoneContactBinding.inflate(inflater, container, false)

        // 기존 연락처 목록 추가
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "대장님", "휴대전화: 010-1234-1234"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "UX/UI 디자이너","휴대전화: 010-1234-1234"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, "기획자","휴대전화: 010-1234-1234"))
        contactArray.add(ContactData(R.drawable.ic_person_black_24dp, ":fearful:","휴대전화: 010-1234-1234"))

        // 어댑터 설정
        contactAdapter = ContactAdapter(contactArray)
        binding.contactList.layoutManager = LinearLayoutManager(requireContext())
        binding.contactList.adapter = contactAdapter


        return binding.root
    }
    /*

    // 연락처를 목록에 추가하는 메서드
    public fun addContact(contact: ContactData) {
        contactArray.add(contact)
        contactAdapter.notifyItemInserted(contactArray.size - 1)
    }*/

}
