package android.pownpon.app.homepage

import android.pownpon.app.base.BaseViewModel
import android.pownpon.app.global.showLog
import android.pownpon.appmodel.bean.GoodsBean
import android.pownpon.appmodel.http.Api
import android.pownpon.appmodel.reqparams.ListGoodsParams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainHomeViewModel : BaseViewModel() {

    //val listGoods = MutableLiveData<Iterable<GoodsBean>>()
    val goodsParams = ListGoodsParams()

    fun getListGoods() {
        viewModelScope.launch {
            Api.listHmGoods(goodsParams).collect {
                if (it.isNullOrEmpty()) {
                    showLog(this@MainHomeViewModel, "空数据")
                } else {
                    showLog(this@MainHomeViewModel, "${it[0]}")
                }

            }
        }

    }
}