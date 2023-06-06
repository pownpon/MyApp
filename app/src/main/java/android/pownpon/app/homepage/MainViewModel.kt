package android.pownpon.app.homepage

import android.content.Context
import android.pownpon.app.R
import android.pownpon.app.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel : BaseViewModel() {

    private val _tabs = MutableLiveData<Iterable<String>>()
    val tabs: LiveData<Iterable<String>> = _tabs

    fun initTabs(context: Context) {
        val tabRes: Array<String> = context.resources.getStringArray(R.array.main_tab)
        _tabs.value = tabRes.asIterable()
    }
}