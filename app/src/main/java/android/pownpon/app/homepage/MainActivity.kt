package android.pownpon.app.homepage

import android.os.Bundle
import android.pownpon.app.R
import androidx.appcompat.app.AppCompatActivity
import android.pownpon.app.databinding.ActivityMainBinding
import android.pownpon.app.global.double_click_time_interval
import android.pownpon.app.global.showLog
import android.pownpon.app.global.showToast
import android.pownpon.app.listener.OnRecyclerViewItemClickListener
import android.view.KeyEvent
import androidx.recyclerview.widget.GridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var clickBackCodeTime = 0L


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
        val tabs: Array<String> = resources.getStringArray(R.array.main_tab)
        getLayoutManager().spanCount = tabs.size
        getAdapter().refreshData(tabs.asIterable())

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