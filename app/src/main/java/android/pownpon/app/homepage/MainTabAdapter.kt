package android.pownpon.app.homepage

import android.content.Context
import android.pownpon.app.base.BaseRecyclerViewAdapter
import android.pownpon.app.base.BaseRecyclerViewHolder
import android.pownpon.app.databinding.ItemMainTabBinding
import android.pownpon.app.global.showLog
import android.view.ViewGroup

class MainTabAdapter(context: Context) :
    BaseRecyclerViewAdapter<String,ItemMainTabBinding>(context){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ItemMainTabBinding> {
        val binding = ItemMainTabBinding.inflate(inflater)
        return  BaseRecyclerViewHolder(binding)
    }

    override fun onBind(
        binding: ItemMainTabBinding,
        position: Int,
        item: String
    ) {
        binding.let {
            it.tvItemMainTab.text = item
            it.ivItemMainTabIcon.setOnClickListener {
                showLog(this@MainTabAdapter,"点击了")
            }
        }
    }




}