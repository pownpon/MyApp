package android.pownpon.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var model: VM

    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initObserve(savedInstanceState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGeneric()
        setContentView(binding.root)
        initView(savedInstanceState)
        initObserve(savedInstanceState)
        initData(savedInstanceState)
    }

    private fun initGeneric() {
        //获取KClass<*>
        val actCls = this@BaseActivity::class
        //列出所有父类的类型
        for (item in actCls.supertypes) {
            //列出类型参数
            for (arg in item.arguments) {
                val cf = arg.type?.classifier
                if (cf is KClass<*>) {
                    if (cf.toString().endsWith("ViewModel")) {
                        val vmCls = cf as KClass<VM>
                        model = ViewModelProvider(this@BaseActivity)[vmCls.java]
                    } else if (cf.toString().endsWith("Binding")) {
                        for (fct in cf.staticFunctions) {
                            if (fct.name == "inflate" && fct.parameters.size == 1) {
                                binding = fct.call(layoutInflater) as VB
                            }
                        }
                    }
                }
            }
        }
    }
}