package android.pownpon.app.homepage

import android.os.Bundle
import android.pownpon.app.base.BaseActivity
import android.pownpon.app.databinding.ActivityMainBinding
import android.pownpon.app.global.double_click_time_interval
import android.pownpon.app.global.showLog
import android.pownpon.app.global.showToast
import android.pownpon.app.listener.OnRecyclerViewItemClickListener
import android.view.KeyEvent
import androidx.recyclerview.widget.GridLayoutManager

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var clickBackCodeTime = 0L

    private fun changFragment(position: Int) {
        showLog(this, "$position")
    }

    /**
     * 初始化视图内容
     */
    override fun initView(savedInstanceState: Bundle?) {
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

    override fun initObserve(savedInstanceState: Bundle?) {
        model.tabs.observe(this) {
            getLayoutManager().spanCount = it.count()
            getAdapter().refreshData(it.asIterable())
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
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