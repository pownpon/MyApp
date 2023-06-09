package android.pownpon.app.base

import android.os.Bundle
import android.pownpon.app.global.showLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private lateinit var _binding: VB
    private lateinit var _model: VM

    protected val binding: VB
        get() = _binding
    protected val model: VM
        get() = _model

    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initObserve(savedInstanceState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)

    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initGeneric(container)
        initView(savedInstanceState)
        initObserve(savedInstanceState)
        initData(savedInstanceState)
        showLog(this,"1111111")
        return _binding.root
    }

    private fun initGeneric(container: ViewGroup?) {
        //获取KClass<*>
        val actCls = this@BaseFragment::class
        //列出所有父类的类型
        for (item in actCls.supertypes) {
            //列出类型参数
            for (arg in item.arguments) {
                val cf = arg.type?.classifier
                if (cf is KClass<*>) {
                    if (cf.toString().endsWith("ViewModel")) {
                        @Suppress("UNCHECKED_CAST")
                        val vmCls = cf as KClass<VM>
                        _model = ViewModelProvider(this@BaseFragment)[vmCls.java]
                    } else if (cf.toString().endsWith("Binding")) {
                        for (fct in cf.staticFunctions) {
                            if (fct.name == "inflate" && fct.parameters.size == 3) {
                                @Suppress("UNCHECKED_CAST")
                                _binding = fct.call(layoutInflater, container, false) as VB
                            }
                        }
                    }
                }
            }
        }
    }

}