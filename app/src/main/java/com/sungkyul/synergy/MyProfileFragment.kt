
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sungkyul.synergy.LoginActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.databinding.FragmentMyProfileBinding
import com.sungkyul.synergy.my_profile.CheckMyResultActivity
import com.sungkyul.synergy.my_profile.MyExamResultActivity

class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }

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

        val sharedPreferences =
            requireContext().getSharedPreferences("SynergyPrefs", Context.MODE_PRIVATE)
        val nickname = sharedPreferences.getString("Nickname", "사용자")

        binding.textViewName.text = nickname
    }
    fun handleOnBackPressed(): Boolean {
        if (backPressedOnce) {
            activity?.finish()
            return true
        }

        this.backPressedOnce = true
        Toast.makeText(requireContext(), "뒤로가기를 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressHandler.postDelayed(backPressRunnable, 2000)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressHandler.removeCallbacks(backPressRunnable)
    }
}
