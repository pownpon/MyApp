package android.pownpon.app.homepage

import android.content.Context
import android.pownpon.app.base.BaseRecyclerViewAdapter
import android.pownpon.app.base.BaseRecyclerViewHolder
import android.pownpon.app.databinding.ItemMainTabBinding
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
        holder: BaseRecyclerViewHolder<ItemMainTabBinding>,
        position: Int,
        item: String
    ) {
        holder.binding.tvItemMainTab.text = item
    }




}