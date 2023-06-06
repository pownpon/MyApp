package android.pownpon.app.homepage

import android.os.Bundle
import android.pownpon.app.base.BaseActivity
import android.pownpon.app.databinding.ActivityMainBinding
import android.pownpon.app.global.double_click_time_interval
import android.pownpon.app.global.showLog
import android.pownpon.app.global.showToast
import android.pownpon.app.listener.OnRecyclerViewItemClickListener
import android.view.KeyEvent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var clickBackCodeTime = 0L

    private val model: MainViewModel by lazy {
        ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    private fun changFragment(position: Int) {
        showLog(this, "$position")
    }

    /**
     * 初始化视图内容
     */
    private fun initView() {
        binding.rvActMainMenu.layoutManager =
            GridLayoutManager(this@MainActivity, 1)
        binding.rvActMainMenu.adapter = MainTabAdapter(this@MainActivity)
        binding.rvActMainMenu.addOnItemTouchListener(
            OnRecyclerViewItemClickListener(
                this@MainActivity, null
            ) {
                changFragment(it)
            }
        )
    }

    private fun initData() {
        model.tabs.observe(this) {
            getLayoutManager().spanCount = it.count()
            getAdapter().refreshData(it.asIterable())
        }
        model.initTabs(this@MainActivity)
    }

    private fun getLayoutManager() = binding.rvActMainMenu.layoutManager as GridLayoutManager

    private fun getAdapter() = binding.rvActMainMenu.adapter as MainTabAdapter


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val curTime = System.currentTimeMillis()
            if (curTime - clickBackCodeTime > double_click_time_interval) {
                showToast(this@MainActivity, "双击退出应用")
                clickBackCodeTime = curTime
            } else {
                clickBackCodeTime = 0L
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}