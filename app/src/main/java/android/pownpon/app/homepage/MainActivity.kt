package android.pownpon.app.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.pownpon.app.databinding.ActivityMainBinding
import android.pownpon.app.global.double_click_time_interval
import android.pownpon.app.global.showToast
import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

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

    /**
     * 初始化视图内容
     */
    private fun initView() {
        binding.rvActMainMenu.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
        binding.rvActMainMenu.adapter = MainTabAdapter(this@MainActivity)
    }

    private fun initData(){
        getAdapter().refreshData(listOf(1,33,8))
    }

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