package android.pownpon.appmodel.resdata

import android.pownpon.appmodel.bean.GoodsBean

data class ListGoodsResponse(
    val ClassTitle: String?,
    val ListAuctionP: List<GoodsBean>?,
)
