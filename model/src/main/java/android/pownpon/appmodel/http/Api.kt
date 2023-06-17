package android.pownpon.appmodel.http

import android.pownpon.appmodel.bean.GoodsBean
import android.pownpon.appmodel.reqparams.ListGoodsParams
import android.pownpon.appmodel.resdata.ListGoodsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object Api {

    fun listHmGoods(param: ListGoodsParams): Flow<List<GoodsBean>?> {
        return get(
            HttpConfig.generateGetUrl(Urls.listGoods, param),
            ListGoodsResponse::class.java
        ).map {
            it.ListAuctionP
        }
    }

}