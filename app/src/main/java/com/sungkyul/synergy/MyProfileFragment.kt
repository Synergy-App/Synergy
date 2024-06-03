import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentMyProfileBinding
import com.sungkyul.synergy.my_profile.CheckMyResultActivity
import com.sungkyul.synergy.my_profile.MyExamResultActivity

// Add other necessary imports

class MyProfileFragment : Fragment() {

    // Declare binding variable
    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using view binding
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.examResultCardView.setOnClickListener {
            val intent = Intent(requireActivity(), MyExamResultActivity::class.java)
            startActivity(intent)
        }

        binding.CheckResultCardView.setOnClickListener {
            val intent = Intent(requireActivity(), CheckMyResultActivity::class.java)
            startActivity(intent)
        }
    }
}
