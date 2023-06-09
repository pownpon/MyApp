package android.pownpon.app.homepage

import android.os.Bundle
import android.pownpon.app.base.BaseFragment
import android.pownpon.app.base.BaseViewModel
import android.pownpon.app.databinding.FragmentMainMyBinding


class MainMyFragment : BaseFragment<FragmentMainMyBinding, BaseViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initObserve(savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            MainMyFragment()
    }
}