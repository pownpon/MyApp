package android.pownpon.appmodel.reqparams

data class ListGoodsParams(
    var page :Int = 1,
    var pageSize:Int = 15,
    //是否当天结束或未结束，online表示全部未结束的商品，today表示当天结束的商品
    var KeyTime: String? = null,
    //分类ID
    var ClassId: String? = null,
    //小分类
    var OrchidBigSort: String? = null,
    //是否是兰花:true是 false不是
    var AucIsOrchid: String? = null,
    //关建字
    var KeyWord: String? = null,
    //物品ID
    var AucId: String? = null,
    //卖家名
    var SellerName: String? = null,
    //卖家ID
    var SellerId: String? = null,
    //所在地
    var Loc: String? = null,
    //开始价格
    var StartPrice: String? = null,
    //结束价格
    var EndPrice: String? = null,
    //加价幅度
    var ExtPrice: String? = null,
    //出价方式，空值表示索引全部，0表示拍卖，1表示一口价，2表示一口价和拍卖，3表示订购
    var ChujiaSort: String? = null,
    //活动分类，jianlou 表示0元捡漏
    var HuoDongSort: String? = null,
    //是否免邮，false/true
    var IsMianyou: String? = null,
    //是否推荐，false/true
    var Tui: String? = null,
    //是否出价，false/true
    var IschuJia: String? = null,
    //是否带花，false/true
    var DaiHua: String? = null,
    /***
     * 付款方式： 0见详细说明 1款到发货 2货到付款
     ***/
    var TraPayMethod: String? = null,

    /***
     * 是否作删除处理:0正常 1已被删除
     ***/
    var AucIsdel: String? = null,

    /***
     * 是否已成交:0待上架中 1正常 2已成交
     ***/
    var AucIsSell: String? = null,

    /***
     * 待上架类别:
     * 0表示从未上架的
     * 1表示定时上架的
     * 2表示表示我下架的
     * 3表示没有卖出的
     ***/
    var AucGoodsType: String? = null,

    /***
     * 是否被网站强行下架:0正常 1已被强行下架
     ***/
    var AucIsEvacuate: String? = null,

    /***
     * 是否支持交易通交易
     * 0不支持
     * 1支持
     ***/
    var AucIsPay: String? = null,

    /***
     * 是否提供退货服务:0不提供 1提供
     ***/
    var IsReturnSer: String? = null,

    /***
     * 是否已重拍过:0没有,如果有的话就每次重拍加1
     ***/
    var AucIsRepeat: String? = null,

    /***
     * 0表示结束时间少的排前
     * 1表示结束时间多的排前
     * 2表示刚刚上架的排前
     * 3表示价格由低到高
     * 4表示价格由高到低
     * 5表示出价数多的排前
     * 6表示出价数少的排前
     * 7表示点击数多的排前
     ***/
    var OrderStyle: String? = null,

    /**
     * 卖家类型，1 散户，2 品牌
     */
    var UserSellerType: Int? = null,

    var IsMaxPageSize: Int = 0,
)
