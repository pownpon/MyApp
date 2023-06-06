package android.pownpon.app.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.pownpon.app.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取参数
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainHomeFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainHomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}