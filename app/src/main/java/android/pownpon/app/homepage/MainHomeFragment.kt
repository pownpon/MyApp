package android.pownpon.app.homepage

import android.os.Bundle
import android.pownpon.app.base.BaseFragment
import android.pownpon.app.base.BaseViewModel
import androidx.fragment.app.Fragment
import android.pownpon.app.databinding.FragmentMainHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHomeFragment : BaseFragment<FragmentMainHomeBinding, BaseViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取参数
        arguments?.let {

        }
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initObserve(savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainHomeFragment()
    }
}