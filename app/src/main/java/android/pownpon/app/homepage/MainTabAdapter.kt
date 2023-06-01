package android.pownpon.app.homepage

import android.content.Context
import android.pownpon.app.base.BaseRecyclerViewAdapter
import android.pownpon.app.base.BaseRecyclerViewTopAdapter
import android.pownpon.app.base.BaseRecyclerViewHolder
import android.pownpon.app.databinding.ItemMainTabBinding
import android.view.LayoutInflater
import android.view.ViewGroup

class MainTabAdapter(context: Context) :
    BaseRecyclerViewAdapter<Int,ItemMainTabBinding>(context){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ItemMainTabBinding> {
        val binding = ItemMainTabBinding.inflate(inflater)
        return  BaseRecyclerViewHolder(binding)
    }

    override fun onBind(
        holder: BaseRecyclerViewHolder<ItemMainTabBinding>,
        position: Int,
        item: Int
    ) {
        holder.binding.tvItemMainTab.text = "第${item}项"
    }




}