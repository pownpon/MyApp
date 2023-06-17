package android.pownpon.app.homepage

import android.os.Bundle
import android.pownpon.app.base.BaseActivity
import android.pownpon.app.databinding.ActivityMainBinding
import android.pownpon.app.global.double_click_time_interval
import android.pownpon.app.global.showToast
import android.pownpon.app.listener.OnRecyclerViewItemClickListener
import android.view.KeyEvent
import android.pownpon.app.R
import android.pownpon.app.global.showLog
import androidx.recyclerview.widget.GridLayoutManager

class MainActivity : BaseActivity<ActivityMainBinding, MainActViewModel>() {

    private var mClickBackCodeTime = 0L
    private var mCurFragmentIndex = 0
    private val mFragments = arrayOf(MainHomeFragment.newInstance(), MainMyFragment.newInstance())

    private fun changFragment(index: Int) {
        if (mCurFragmentIndex != index && index in mFragments.indices) {
            supportFragmentManager
                .beginTransaction()
                .hide(mFragments[mCurFragmentIndex])
                .show(mFragments[index])
                .commit()
            mCurFragmentIndex = index
        }
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

        supportFragmentManager.beginTransaction().apply {
            mFragments.forEach {
                add(R.id.fcv_act_main_content, it).hide(it)
            }
            show(mFragments.first())
            commit()
        }
    }

    override fun initObserve(savedInstanceState: Bundle?) {
        model.tabs.observe(this) {
            getLayoutManager().spanCount = it.count()
            getAdapter().refreshData(it.asIterable())
        }
        model.testStr.observe(this) {
            showLog(this, it)
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
            if (curTime - mClickBackCodeTime > double_click_time_interval) {
                showToast(this@MainActivity, "双击退出应用")
                mClickBackCodeTime = curTime
            } else {
                mClickBackCodeTime = 0L
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}